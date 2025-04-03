import requests
import config
import json
from bs4 import BeautifulSoup
from bs4.element import Tag

def getPage(page_num:int):
    #local data test
    with open("data/page.html", "r", encoding="utf-8") as f:
        html = f.read()
        soup = BeautifulSoup(html, "lxml")
    return soup

    #real online data
    url = config.COMMUNITY_URL+str(page_num)
    response = requests.get(url)
    response.raise_for_status()
    soup = BeautifulSoup(response.text, "lxml")

    return soup

def convertPage2List(soup):
    contents = dict()
    ul = list(el for el in soup.find('ul', attrs={'class': 'photo2-list gallery-ui-list'}) if isinstance(el, Tag))
    print(f'page converting... find {len(ul)} contents')
    for li in ul:
        title_wrap = li.select('div.title>a')[0]
        href = title_wrap['href']
        title = title_wrap['title']

        common_info_list = li.select('ul.common-info-list>li')
        wr_name = common_info_list[0].find('button')['data-wr_name']
        date = common_info_list[1].text.strip()

        option_list = list(el.text.strip() for el in li.select('ul.options>li'))
        contents[href.split('/')[-1]] = {
            '게시물 url': href,
            '제목': title,
            '닉네임': wr_name,
            '날짜': date,
            '옵션': option_list
            }
    return contents

def getContent(content_id:int):
    #local data test
    with open("data/content.html", "r", encoding="utf-8") as f:
        html = f.read()
        soup = BeautifulSoup(html, "lxml")
    return soup
    #real online data
    url = config.CONTENT_URL+str(content_id)
    response = requests.get(url)
    response.raise_for_status()
    soup = BeautifulSoup(response.text, "lxml")
    return soup

def convertContent2Json(content_id, soup:BeautifulSoup):
    title_wrap = soup.select_one('div.title_wrap')
    hospital = title_wrap.select_one('a.hospital')['title']
    title = title_wrap.select_one('h2').text
    wr_name = title_wrap.select_one('button.btn_usermenu')['data-wr_name']
    date = title_wrap.select_one('li.date>p').text
    view = title_wrap.select('li.view>div')[1].text

    photo2_detail_info = soup.select_one('div.photo2-detail-info')
    method = photo2_detail_info.select('div.text li>strong')[0].text
    value1 = photo2_detail_info.select('div.text li>strong')[1].text.split()[0][:-1]
    value2 = photo2_detail_info.select('div.text li>strong')[1].text.split()[1][1:-3]
    after = photo2_detail_info.select('div.text li>strong')[2].text
    age = photo2_detail_info.select('div.text li>strong')[3].text
    score = photo2_detail_info.select('div.tags li>div.review-score>strong')[0].text
    type_list = [i.text.strip() for i in photo2_detail_info.select('div.tags li.type')]

    content_view = soup.select_one('div.content-view-detail')
    text = [i.text.strip() for i in content_view.select('span')]
    images = [i['src'] for i in content_view.select('img')]

    content = {
        '게시물 url': config.CONTENT_URL+str(content_id),
        '병원명': hospital,
        '제목': title,
        '닉네임': wr_name,
        '수정시간': date,
        '조회수': view,
        '수술방식': method,
        '이식량(모)': value1,
        '이식량(모낭)': value2,
        '수술경과(일)': after,
        '연령대': age,
        '수술만족도': score,
        '수술범위': type_list,
        '본문': text,
        '사진': images
    }
    with open(f'data/{content_id}.json', 'w') as json_file:
        json.dump(content, json_file)
    return {content_id: content}
    
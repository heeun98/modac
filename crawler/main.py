from utils import getPage, convertPage2List, getContent, convertContent2Json

page = getPage(-1)
print(convertPage2List(page))

content = getContent(-1)
print(convertContent2Json(-1, content))
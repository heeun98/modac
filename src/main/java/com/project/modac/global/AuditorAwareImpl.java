package com.project.modac.global;

import com.project.modac.global.login.dto.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // SecurityContextHolder에서 인증 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("AuditorAware - Authentication: {}", authentication);


        // 인증 정보가 없거나, 인증되지 않았거나, 익명 사용자인 경우
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return Optional.of("system"); // 필요 시 Optional.of("system")으로 기본값 설정 가능
        }
        log.info("IsAuthenticated: {}, Principal: {}", authentication.isAuthenticated(), authentication.getPrincipal());

        // principal이 CustomUserDetails인지 확인
        if (authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            log.info("Found CustomUserDetails: {}", userDetails.getUsername());
            return Optional.of(userDetails.getUsername()); // username 반환
        }

        // principal이 CustomUserDetails가 아닌 경우
        return Optional.empty();
    }
}
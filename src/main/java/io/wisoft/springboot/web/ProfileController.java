package io.wisoft.springboot.web;

import lombok.RequiredArgsConstructor;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfileController {

    private final Environment env;

    // profile get 요청이 들어오면
    @GetMapping("/profile")

    public String profile() {
        // 1. 설정된 profile 가져옴
        List<String> profiles = Arrays.asList(env.getActiveProfiles());
        // 2. 실제 배포 환경에 적용할 real profile 확인
        List<String> realProfiles = Arrays.asList("real", "real1", "real2");
        // 3. defaultProfile 은 만약 profiles 에 프로필이 비어있다면 default 아니면 해당 프로필에서 불러옴
        String defaultProfile = profiles.isEmpty() ? "default" : profiles.get(0);

        return profiles.stream()
                // realProfiles 이 포함되어 있으면 그것을 반환
                .filter(realProfiles::contains)
                .findAny()
                .orElse(defaultProfile);
    }
}

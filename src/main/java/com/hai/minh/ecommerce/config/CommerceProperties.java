package com.hai.minh.ecommerce.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "commerce")
public class CommerceProperties {

    @NotNull
    private Jwt jwt;

    @NotNull
    private Rest rest;

    @NotNull
    private InitialData initialData;

    @Getter
    @Setter
    public static class Jwt {

        @NotBlank
        private String secret;

        @NotBlank
        private long accessTokenValidityInSeconds;

    }

    @Getter
    @Setter
    public static class Rest {

        @NotNull
        private Template template;

        @Getter
        @Setter
        public static class Template {

            @NotBlank
            private int connectTimeout;

            @NotBlank
            private int connectRequestTimeout;
        }
    }

    @Getter
    @Setter
    public static class InitialData {

        @NotBlank
        private boolean autoImport;

    }

}

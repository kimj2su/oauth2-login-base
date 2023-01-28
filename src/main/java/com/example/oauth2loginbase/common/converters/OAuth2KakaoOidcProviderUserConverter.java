package com.example.oauth2loginbase.common.converters;

import com.example.oauth2loginbase.common.enums.OAuth2Config;
import com.example.oauth2loginbase.common.util.OAuth2Utils;
import com.example.oauth2loginbase.model.ProviderUser;
import com.example.oauth2loginbase.model.social.KakaoOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class OAuth2KakaoOidcProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {

    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
        if (!OAuth2Config.SocialType.KAKAO.getSocialName().equals(providerUserRequest.clientRegistration().getRegistrationId())) {
            return null;
        }

        if (!(providerUserRequest.oAuth2User() instanceof OidcUser)) {
            return null;
        }

        return new KakaoOidcUser(OAuth2Utils.getMainAttributes(providerUserRequest.oAuth2User()),
                providerUserRequest.oAuth2User(),
                providerUserRequest.clientRegistration()
        );
    }
}

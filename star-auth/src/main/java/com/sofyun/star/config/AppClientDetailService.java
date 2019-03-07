package com.sofyun.star.config;

import com.sofyun.common.dto.auth.AuthUser;
import com.sofyun.star.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

public class AppClientDetailService implements ClientDetailsService {

    @Autowired
    private UserClient userClient;

    @Override
    public ClientDetails loadClientByClientId(String applyName) throws ClientRegistrationException {
        AuthUser user = userClient.findByCode(applyName).getData();
        ClientDetails clientDetails = new AppDetails(user.getCode(), user.getPwd());
        return clientDetails;
    }
}

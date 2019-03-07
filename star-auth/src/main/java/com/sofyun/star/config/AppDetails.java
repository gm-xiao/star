package com.sofyun.star.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

public class AppDetails implements ClientDetails {

    private static final long serialVersionUID = 2559033745576153420L;

    private final String clientId;

    private final String secret;

    private Set<String> authorizedGrantTypes;

    private Set<String> scope;

    private  Collection<GrantedAuthority> authorities;

    public AppDetails(String clientId, String secret) {
        this.clientId = clientId;
        this.secret = secret;
        Set<String> authorizedGrantTypes = new HashSet<>();
        authorizedGrantTypes.add("client_credentials");
        authorizedGrantTypes.add("refresh_token");
        this.authorizedGrantTypes = authorizedGrantTypes;
        Set<String> scope = new HashSet<>();
        scope.add("trade");
        this.scope = scope;
        this.authorities = new ArrayList<>();
    }

    public String getSecret() {
        return secret;
    }

    @Override
    public String getClientId() {
        return this.clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return this.secret;
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        return this.scope;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return this.authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return null;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return null;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}


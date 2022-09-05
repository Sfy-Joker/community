package com.hong.cummunity.controller;

import com.hong.cummunity.dto.AccessTokenDTO;
import com.hong.cummunity.dto.GithubUser;
import com.hong.cummunity.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.redirect.uri}")
    private String redirect_uri;

    @Value("${github.client.secret}")
    private String client_secret;

    @GetMapping("/callback")
    public String getCallback(@RequestParam("code") String code,
                              @RequestParam("state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        String token = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(token);
        System.out.println(user.getName());
        return "index";
    }
}

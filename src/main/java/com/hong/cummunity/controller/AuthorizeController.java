package com.hong.cummunity.controller;

import com.hong.cummunity.dto.AccessTokenDTO;
import com.hong.cummunity.dto.GithubUser;
import com.hong.cummunity.mapper.UserMapper;
import com.hong.cummunity.model.User;
import com.hong.cummunity.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.client.secret}")
    private String client_secret;

    @Value("${github.redirect.uri}")
    private String redirect_uri;

    @GetMapping("/callback")
    public String getCallback(@RequestParam("code") String code,
                              @RequestParam("state") String state,
                              HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(this.client_id);
        accessTokenDTO.setClient_secret(this.client_secret);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(this.redirect_uri);
        String token = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(token);
        if(githubUser != null){
            //登录成功 获取cookie和session
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.addUser(user);

            response.addCookie(new Cookie("token", user.getToken()));
            return "redirect:/";
        }else{
            //登录失败
            return "redirect:/";
        }
    }
}

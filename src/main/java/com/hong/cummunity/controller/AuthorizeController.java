package com.hong.cummunity.controller;

import com.hong.cummunity.dto.AccessTokenDTO;
import com.hong.cummunity.dto.GithubUser;
import com.hong.cummunity.model.User;
import com.hong.cummunity.provider.GithubProvider;
import com.hong.cummunity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    private UserService userService;

    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.client.secret}")
    private String client_secret;

    @Value("${github.redirect.uri}")
    private String redirect_uri;

    @GetMapping("/callback")
    public String getCallback(@RequestParam("code") String code,
                              @RequestParam("state") String state,
                              HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(this.client_id);
        accessTokenDTO.setClient_secret(this.client_secret);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(this.redirect_uri);
        String githubToken = githubProvider.getAccessToken(accessTokenDTO);

        GithubUser githubUser = githubProvider.getUser(githubToken);

        if (githubUser != null && githubUser.getId() != null) {
            //登录成功 获取cookie和session
            User user = new User();
            String token = UUID.randomUUID().toString();

            user.setToken(token);
            user.setName(githubUser.getName().trim());
            user.setAccountId(githubUser.getId().trim());
            user.setAvatarUrl(githubUser.getAvatar_url());

            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token", token));
            return "redirect:/";
        } else {
            //登录失败
            return "redirect:/";
        }
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/";
    }
}

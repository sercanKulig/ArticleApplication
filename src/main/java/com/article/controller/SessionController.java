package com.article.controller;

import com.article.dto.SessionDTO;
import com.article.entity.session.SessionItem;
import com.article.entity.user.Login;
import com.article.entity.user.User;
import com.article.enumerations.ResponseMessageStatus;
import com.article.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(tags = {"Authentication"})
public class SessionController {

    @Autowired
    private UserRepository userRepo;

    @ApiResponses(value = { @ApiResponse(code = 200, message = "Will return a security token, which must be passed in every request", response = SessionDTO.class) })
    @RequestMapping(value = "/session", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SessionDTO newSession(@RequestBody Login login, HttpServletRequest request, HttpServletResponse response) {
        User user = userRepo.findOneByUserIdAndPassword(login.getUsername(), login.getPassword()).orElse(null);
        SessionDTO resp = new SessionDTO();
        SessionItem sessionItem = new SessionItem();
        if (user != null){
            sessionItem.setToken("xxx.xxx.xxx");
            sessionItem.setUserId(user.getUserId());
            sessionItem.setFirstName(user.getFirstName());
            sessionItem.setLastName(user.getLastName());
            sessionItem.setEmail(user.getEmail());
            //sessionItem.setRole(user.getRole());

            resp.setResponseMessageStatus(ResponseMessageStatus.SUCCESS);
            resp.setMessage("Dummy Login Success");
            resp.setItem(sessionItem);
        }
        else{
            resp.setResponseMessageStatus(ResponseMessageStatus.ERROR);
            resp.setMessage("Login Failed");
        }
        return resp;
    }

}

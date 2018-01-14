package com.pavoindus.authentication.controller;

import com.pavoindus.authentication.autoconfigue.ModelTrainingProperties;
import com.pavoindus.authentication.autoconfigue.PredictiveProperties;
import com.pavoindus.authentication.context.ApplicationContext;
import com.pavoindus.authentication.form.AuthenticationValidationForm;
import com.pavoindus.authentication.form.LoginForm;
import com.pavoindus.authentication.form.LogoutForm;
import com.pavoindus.authentication.form.SignupForm;
import com.pavoindus.authentication.response.APIResponse;
import com.pavoindus.authentication.response.Failure;
import com.pavoindus.authentication.response.ResponseStatus;
import com.pavoindus.authentication.response.Success;
import com.pavoindus.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created 12/11/2017 21:22
 *
 * @author Deepankar Sharma
 */
@Controller
public class ApplicationController {

    private static final String AUTH_TOKEN = "authToken";
    private final ModelTrainingProperties modelTrainingProperties;
    private final PredictiveProperties predictiveProperties;
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    public ApplicationController(ModelTrainingProperties modelTrainingProperties, PredictiveProperties predictiveProperties) {
        this.modelTrainingProperties = modelTrainingProperties;
        this.predictiveProperties = predictiveProperties;
    }

    @PostMapping("/signup")
    public @ResponseBody
    APIResponse processSignup(@RequestBody SignupForm signupForm) {
        if (!authenticationService.validateNewSignup(signupForm.getUsername(), signupForm.getPassword())) {
            return new Failure(ResponseStatus.Status.MEMBER_ALREADY_EXISTS);
        }
        String authToken = authenticationService.signup(signupForm.getUsername(), signupForm.getPassword(), signupForm.getFirstName(), signupForm.getLastName());
        Map<String, Object> data = new HashMap<>();
        data.put(AUTH_TOKEN, authToken);
        return new Success(data);
    }

    @PostMapping("/login")
    public @ResponseBody
    APIResponse processLogin(@RequestBody LoginForm loginForm) {
        String authToken = authenticationService.login(loginForm.getUsername(), loginForm.getPassword());
        if (authToken == null) {
            return new Failure(ResponseStatus.Status.INVALID_CREDENTIALS);
        }
        Map<String, Object> data = new HashMap<>();
        data.put(AUTH_TOKEN, authToken);
        return new Success(data);
    }

    @PostMapping("/validateToken")
    public @ResponseBody
    APIResponse processTokenValidation(@RequestBody AuthenticationValidationForm authenticationValidationForm) {
        String authToken = authenticationService.validateAuthenticationToken(authenticationValidationForm.getAuthToken());
        if (authToken == null) {
            return new Failure(ResponseStatus.Status.INVALID_AUTH_TOKEN);
        }
        if(ApplicationContext.get().getApiKey().getApplicationName().contains("Client")) {
            return new Failure(ResponseStatus.Status.INVALID_APPLICATION);
        }
        Map<String, Object> data = new HashMap<>();
        data.put(AUTH_TOKEN, authToken);
        return new Success(data);
    }

    @PostMapping("/logout")
    public @ResponseBody
    APIResponse processLogout(@RequestBody LogoutForm logoutForm) {
        if (authenticationService.logout(logoutForm.getAuthToken())) {
            return new Success();
        }
        return new Failure();
    }
}

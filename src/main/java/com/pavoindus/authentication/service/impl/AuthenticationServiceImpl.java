package com.pavoindus.authentication.service.impl;

import com.pavoindus.authentication.context.ApplicationContext;
import com.pavoindus.authentication.model.Authentication;
import com.pavoindus.authentication.model.Member;
import com.pavoindus.authentication.repository.AuthenticationRepository;
import com.pavoindus.authentication.repository.MemberRepository;
import com.pavoindus.authentication.service.AuthenticationService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Component
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Autowired
    private MemberRepository memberRepository;

    private String generateNewAuthToken() {
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }

    private Date generateAuthTokenValidity() {
        DateTime authTokenValidity = new DateTime(new Date());
        return authTokenValidity.plusMinutes(ApplicationContext.get().getApiKey().getTokenValidity()).toDate();
    }

    @Override
    public String signup(String username, String password, String firstName, String lastName) {
        String authToken = generateNewAuthToken();
        Date authTokenValidity = generateAuthTokenValidity();
        Authentication authentication = new Authentication(username, password, authToken, authTokenValidity);
        authenticationRepository.save(authentication);
        Member member = new Member(authentication, firstName, lastName);
        memberRepository.save(member);
        return authToken;
    }

    @Override
    public String login(String username, String password) {
        Authentication authentication = authenticationRepository.findByUsernameAndPassword(username, password);
        if(authentication != null) {
            authentication.setAuthToken(generateNewAuthToken());
            authentication.setAuthTokenValidity(generateAuthTokenValidity());
            authenticationRepository.save(authentication);
            return authentication.getAuthToken();
        }
        return null;
    }

    @Override
    public String validateAuthenticationToken(String authToken) {
        List<Authentication> authenticationResults = authenticationRepository.findByAuthToken(authToken);
        if(authenticationResults != null && !authenticationResults.isEmpty()) {
            Authentication auth = authenticationResults.get(0);
            if(auth.getAuthTokenValidity().after(new Date())) {
                auth.setAuthTokenValidity(generateAuthTokenValidity());
                authenticationRepository.save(auth);
                return authToken;
            }
        }
        return null;
    }

    @Override
    public boolean validateNewSignup(String username, String password) {
        return authenticationRepository.findByUsernameAndPassword(username, password) == null;
    }

    @Override
    public boolean logout(String authToken) {
        List<Authentication> authentications = authenticationRepository.findByAuthToken(authToken);
        if(authentications != null && !authentications.isEmpty()) {
            Authentication authentication = authentications.get(0);
            authentication.setAuthTokenValidity(new Date());
            authenticationRepository.save(authentication);
            return true;
        }
        return false;
    }
}

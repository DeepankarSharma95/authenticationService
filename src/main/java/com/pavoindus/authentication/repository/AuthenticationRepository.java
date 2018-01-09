package com.pavoindus.authentication.repository;

import com.pavoindus.authentication.model.Authentication;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AuthenticationRepository extends PagingAndSortingRepository<Authentication, Long> {

    Authentication findByUsernameAndPassword(String username, String password);
    List<Authentication> findByAuthToken(String authToken);
}

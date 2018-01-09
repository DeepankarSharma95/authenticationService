package com.pavoindus.authentication.repository;

import com.pavoindus.authentication.model.Member;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {

}

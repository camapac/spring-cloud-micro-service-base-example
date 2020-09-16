package com.paytech.vita.oauth2.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.paytech.vita.oauth2.entity.User;

@Repository
@Transactional
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	User findByUsername(String username);

	User findByEmail(String email);
	
	User findByPhoneNumber(String phoneNumber);
}

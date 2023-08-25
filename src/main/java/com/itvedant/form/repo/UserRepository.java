package com.itvedant.form.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itvedant.form.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}

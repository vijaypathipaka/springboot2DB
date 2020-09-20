package com.example.Repo.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.user.User;

@Repository
public interface UserRepo extends  CrudRepository<User, Integer> {

}

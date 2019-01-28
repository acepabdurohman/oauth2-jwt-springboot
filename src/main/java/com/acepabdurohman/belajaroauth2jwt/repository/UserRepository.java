package com.acepabdurohman.belajaroauth2jwt.repository;

import com.acepabdurohman.belajaroauth2jwt.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findTopByUsername(String username);
}

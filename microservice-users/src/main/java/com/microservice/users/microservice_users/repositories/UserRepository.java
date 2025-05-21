package com.microservice.users.microservice_users.repositories;

import com.microservice.users.microservice_users.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long>
{
    User findByEmail(String email);
}

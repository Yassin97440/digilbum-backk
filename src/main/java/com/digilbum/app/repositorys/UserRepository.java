package com.digilbum.app.repositorys;

import com.digilbum.app.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
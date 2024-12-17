package com.digilbum.app.repositorys;

import com.digilbum.app.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    public Group findByJoinCode(String joinCode);

    boolean existsByJoinCode(String joinCode);
}
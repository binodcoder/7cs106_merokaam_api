package com.binodcoder.merokaamapi.repository;

import com.binodcoder.merokaamapi.entity.UsersType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTypeRepository extends JpaRepository<UsersType, Long> {
    Optional<UsersType> findByUserTypeName(String userTypeName);
}

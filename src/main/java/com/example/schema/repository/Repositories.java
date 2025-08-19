package com.example.schema.repository;

import com.example.schema.entity.UserDefault;
import com.example.schema.entity.UserCustom;
import com.example.schema.entity.UserMixed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDefaultRepo extends JpaRepository<UserDefault, Long> {}
public interface UserCustomRepo extends JpaRepository<UserCustom, Long> {}
public interface UserMixedRepo extends JpaRepository<UserMixed, Long> {}

package com.example.BTL.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BTL.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
	public boolean existsByUsername(String username);
	public Users  findByUsername(String username);
}

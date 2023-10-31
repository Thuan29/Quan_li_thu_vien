package com.example.BTL.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BTL.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer>{

}

package com.example.fooddelivery.repository;

import com.example.fooddelivery.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Page<Category> findAllByDeletedFalse(Pageable pageable);
}

package com.BlogApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BlogApplication.Entity.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

}

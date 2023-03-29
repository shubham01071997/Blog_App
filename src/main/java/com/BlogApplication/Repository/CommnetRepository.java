package com.BlogApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BlogApplication.Entity.Comment;
@Repository
public interface CommnetRepository extends JpaRepository<Comment,Integer> {

}

package com.example.myschool.teacher.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myschool.teacher.domain.Teacher;

// 쿼리를 알아서 다 짜준다던데 흠냐릥
// extends JpaRepository<Teacher, Integer> 이거 때메 int 안 쓰고 Integer 쓴다고 함
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
	
	 public List<Teacher> findByNameContainsIgnoreCase(String name);
	 
}

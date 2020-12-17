package com.odx.test.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.odx.test.entity.Demo;

public interface DemoRepository extends JpaRepository<Demo, Integer> {
	
	public List<Demo> findAllByName(String name);
	
	

}

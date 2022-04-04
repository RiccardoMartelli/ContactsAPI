package com.owtchallenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.owtchallenge.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	public List<Contact> findBySkills_Id(Long id);
	
	public List<Contact> findBySkills_Name(String name);

}

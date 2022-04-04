package com.owtchallenge.service;

import java.util.List;

import com.owtchallenge.entity.Contact;

public interface ContactService {
	
	public List<Contact> findAll();
	
	public Contact get(Long id);

	public Contact insert(Contact contact);

	public Contact update(Contact newContact, Long id);

	public void delete(Long id);

	public List<Contact> getAllbySkillId(Long id);

	public List<Contact> getAllbySkillName(String name);
	
	public Contact skillToContacr(Long skillId, Long contactId);

}

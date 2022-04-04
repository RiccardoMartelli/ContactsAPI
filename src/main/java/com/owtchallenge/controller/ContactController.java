package com.owtchallenge.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.owtchallenge.entity.Contact;
import com.owtchallenge.service.ContactService;

@RestController
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping("/contacts")
	public List<Contact> list() {
	    return contactService.findAll();
	}
	
	@GetMapping("/contacts/{id}")
	public Contact get(@PathVariable Long id) {
		return contactService.get(id);
	}
	
	@PostMapping("/contacts")
	public Contact newContact(@Valid @RequestBody Contact contact) {
	    return contactService.insert(contact);
	}
	
	@PutMapping("/contacts/{id}")
	public Contact replaceContact(@Valid @RequestBody Contact newContact, @PathVariable Long id) {
	    return contactService.update(newContact,id);
	}

	@DeleteMapping("/contacts/{id}")
	public void deleteContact(@PathVariable Long id) {
		contactService.delete(id);
	}
	
	@GetMapping("/contacts/skills/{id}")
	public List<Contact> getAllbySkillId(@PathVariable Long id) {
		return contactService.getAllbySkillId(id);
	}
	
	@GetMapping("/contacts?skillsname={name}")
	public List<Contact> getAllbySkillName(@PathVariable String name) {
		return contactService.getAllbySkillName(name);
	}
	
}

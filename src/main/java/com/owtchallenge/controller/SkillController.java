package com.owtchallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.owtchallenge.entity.Contact;
import com.owtchallenge.entity.Skill;
import com.owtchallenge.service.ContactService;
import com.owtchallenge.service.SkillService;

@RestController
public class SkillController {
	
	@Autowired
	private SkillService skillService;
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping("/skills")
	public List<Skill> list() {
	    return skillService.findAll();
	}
	
	@GetMapping("/skills/{id}")
	public Skill get(@PathVariable Long id) {
		return skillService.get(id);
	}
	
	@PostMapping("/skills")
	public Skill newSkill(@RequestBody Skill skill) {
	    return skillService.insert(skill);
	}
	
	@PutMapping("/skills/{id}")
	public Skill replaceSkill(@RequestBody Skill newSkill, @PathVariable Long id) {
	    return skillService.update(newSkill,id);
	}

	@DeleteMapping("/skills/{id}")
	public void deleteSkill(@PathVariable Long id) {
		skillService.delete(id);
	}
	
	@PutMapping("/skills/{skillId}/contact/{contactId}")
	public Contact existingSkillToCotact(@PathVariable Long skillId,@PathVariable Long contactId) {
	    return contactService.skillToContacr(skillId,contactId);
	}
	
}

package com.owtchallenge.service.impl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.owtchallenge.entity.Contact;
import com.owtchallenge.entity.Skill;
import com.owtchallenge.exception.ContactAlreadyHasSkillException;
import com.owtchallenge.exception.ContactDuplicatedException;
import com.owtchallenge.exception.ContactNotFoundException;
import com.owtchallenge.repository.ContactRepository;
import com.owtchallenge.service.ContactService;
import com.owtchallenge.service.SkillService;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private SkillService skillService;

	@Override
	public List<Contact> findAll() {
		return contactRepository.findAll();
	}

	@Override
	public Contact get(Long id) {
		return contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException(id));
	}

	@Override
	public Contact insert(Contact contact) {
		try {
			Set<Skill> skills=skillService.insertMany(contact.getSkills());
			contact.setSkills(skills);
			return contactRepository.save(contact);
		}catch (Exception e) {
			if (e instanceof SQLIntegrityConstraintViolationException) {
				throw new ContactDuplicatedException();
			}else {
				throw e;
			}
		}
	}

	@Override
	public Contact update(Contact newContact, Long id) {
		return contactRepository.findById(id).map(contact -> {
			contact = cloneAndSave(contact, newContact);
			return contact;
		}).orElseGet(() -> {
			newContact.setId(id);
			return insert(newContact);
		});
	}

	@Override
	public void delete(Long id) {
		try {
			contactRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ContactNotFoundException(id);
		}
	}

	private Contact cloneAndSave(Contact contact, Contact newContact) {
		contact.setAddress(newContact.getAddress());
		contact.setEmail(newContact.getEmail());
		contact.setFirstname(newContact.getFirstname());
		contact.setFullname(newContact.getFullname());
		contact.setLastname(newContact.getLastname());
		contact.setPhone_number(newContact.getPhone_number());
		contact.setSkills(newContact.getSkills());
		return insert(contact);
	}

	@Override
	public List<Contact> getAllbySkillId(Long id) {
		return contactRepository.findBySkills_Id(id);
	}

	@Override
	public List<Contact> getAllbySkillName(String name) {
		return contactRepository.findBySkills_Name(name);
	}

	@Override
	public Contact skillToContacr(Long skillId, Long contactId) {
		Contact contact=get(contactId);
		Skill skill=skillService.get(skillId);
		List<String> skillsNames= contact.getSkills().stream().map(Skill::getName).collect(Collectors.toList());
		if(skillsNames.contains(skill.getName())) {
			throw new ContactAlreadyHasSkillException(skill.getName(),contactId);
		}else {
			contact.getSkills().add(skill);
		}
		return update(contact, contactId);
	}
}

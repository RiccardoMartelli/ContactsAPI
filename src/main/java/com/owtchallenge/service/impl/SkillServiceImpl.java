package com.owtchallenge.service.impl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.owtchallenge.entity.Skill;
import com.owtchallenge.exception.SkillDuplicatedException;
import com.owtchallenge.exception.SkillNotFoundException;
import com.owtchallenge.repository.SkillRepository;
import com.owtchallenge.service.SkillService;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {

	@Autowired
	private SkillRepository skillRepository;

	@Override
	public List<Skill> findAll() {
		return skillRepository.findAll();
	}

	@Override
	public Skill get(Long id) {
		return skillRepository.findById(id).orElseThrow(() -> new SkillNotFoundException(id));
	}

	@Override
	public Skill insert(Skill skill) {
		try {
			return skillRepository.save(skill);
		}catch (Exception e) {
			if (e instanceof SQLIntegrityConstraintViolationException) {
				throw new SkillDuplicatedException();
			}else {
				throw e;
			}
		}
	}

	@Override
	public Skill update(Skill newSkill, Long id) {
		return skillRepository.findById(id).map(skill -> {
			skill = cloneAndSave(skill, newSkill);
			return skill;
		}).orElseGet(() -> {
			newSkill.setId(id);
			return insert(newSkill);
		});
	}

	@Override
	public void delete(Long id) {
		try {
			skillRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new SkillNotFoundException(id);
		}
	}

	private Skill cloneAndSave(Skill skill, Skill newSkill) {
		skill.setLevel(newSkill.getLevel());
		skill.setName(newSkill.getName());
		return insert(skill);
	}

	@Override
	public Set<Skill> insertMany(Set<Skill> skills) {
		Set<Skill> newSkills=new HashSet<Skill>();
		for(Skill skill:skills) {
			newSkills.add(insert(skill));
		}
		return newSkills;
	}

}

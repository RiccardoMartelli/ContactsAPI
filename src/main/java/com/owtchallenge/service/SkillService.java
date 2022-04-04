package com.owtchallenge.service;

import java.util.List;
import java.util.Set;

import com.owtchallenge.entity.Skill;

public interface SkillService {
	
	public List<Skill> findAll();
	
	public Skill get(Long id);

	public Skill insert(Skill skill);

	public Skill update(Skill newSkill, Long id);

	public void delete(Long id);

	public Set<Skill> insertMany(Set<Skill> skills);

}

package com.example.springbootproject;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/people")
public class PeopleController {

	@Autowired
	private PeopleRepo peopleRepo;
	
	@PostMapping("/save")
	public People savePeople(@RequestBody People people) {
		return peopleRepo.save(people);
	}
	
	@PutMapping("/{id}")
	public People updatePeople(@PathVariable Integer id, @RequestBody People people) {
		people.setId(id);
		return peopleRepo.save(people);
	}
	
	@DeleteMapping("/{id}")
	public void deletePeople(@PathVariable Integer id) {
		peopleRepo.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public Optional<People> getById(@PathVariable Integer id) {
		return peopleRepo.findById(id);
	}
	
	@GetMapping("/page")
	public Page<People> getByPage(@RequestParam int page, @RequestParam int size) {
		Pageable pageable = PageRequest.of(page, size);
		return peopleRepo.findAll(pageable);
	}
	
	@GetMapping("/sortby")
	public List<People> getsortBy(@RequestParam String sortby) {
		return peopleRepo.findAll(Sort.by(sortby));
	}
}

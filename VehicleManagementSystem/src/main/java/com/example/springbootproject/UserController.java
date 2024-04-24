package com.example.springbootproject;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepo userRepo;
	
	@PostMapping("/save")
	public User saveUser(@RequestBody User user) {
		return userRepo.save(user);
	}
	
	@PutMapping("/{id}")
	public User updateUser(@PathVariable Integer id, @RequestBody User user) {
		user.setId(id);
		return userRepo.save(user);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Integer id) {
		userRepo.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public Optional<User> getById(@PathVariable Integer id) {
		return userRepo.findById(id);
	}
	
	@GetMapping("/pageno")
	public Page<User> getById(@RequestParam int page, @RequestParam int size) {
		Pageable pageable = PageRequest.of(page, size);
		return userRepo.findAll(pageable);
	}
	
	@GetMapping("/sortby")
	public List<User> getBySort(@RequestParam String sortBy) {
		return userRepo.findAll(Sort.by(sortBy));
	}
}

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
@RequestMapping("/bank")
public class BankController {

	@Autowired
	private BankRepo bankRepo;
	
	@PostMapping("/save")
	public Bank savebank(@RequestBody Bank bank) {
		return bankRepo.save(bank);
	}
	
	@PutMapping("/{id}")
	public Bank updatebank(@PathVariable Integer id, @RequestBody Bank bank) {
		bank.setId(id);
		return bankRepo.save(bank);
	}
	
	@DeleteMapping("/{id}")
	public void deletebank(@PathVariable Integer id) {
		bankRepo.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public Optional<Bank> getById(@RequestParam Integer id) {
		return bankRepo.findById(id);
	}
	
	@GetMapping("/page")
	public Page<Bank> getByPage(@RequestParam int page, @RequestParam int size) {
		Pageable pageable = PageRequest.of(page, size);
		return bankRepo.findAll(pageable);
	}
	
	@GetMapping("/sortby")
	public List<Bank> getsortBy(@RequestParam String sortby) {
		return bankRepo.findAll(Sort.by(sortby));
	}
}
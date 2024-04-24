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
@RequestMapping("/vehicle")
public class VehicleController {

	@Autowired
	private VehicleRepo vehicleRepo;
	
	@PostMapping("/save")
	public Vehicle savevehicle(@RequestBody Vehicle vehicle) {
		return vehicleRepo.save(vehicle);
	}
	
	@PutMapping("/{id}")
	public Vehicle updatevehicle(@PathVariable Integer id, @RequestBody Vehicle vehicle) {
		vehicle.setId(id);
		return vehicleRepo.save(vehicle);
	}
	
	@DeleteMapping("/{id}")
	public void deletevehicle(@PathVariable Integer id) {
		vehicleRepo.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public Optional<Vehicle> getById(@PathVariable Integer id) {
		return vehicleRepo.findById(id);
	}
	
	@GetMapping("/pageno")
	public Page<Vehicle> getById(@RequestParam int page, @RequestParam int size) {
		Pageable pageable = PageRequest.of(page, size);
		return vehicleRepo.findAll(pageable);
	}
	
	@GetMapping("/sortby")
	public List<Vehicle> getBySort(@RequestParam String sortBy) {
		return vehicleRepo.findAll(Sort.by(sortBy));
	}
}

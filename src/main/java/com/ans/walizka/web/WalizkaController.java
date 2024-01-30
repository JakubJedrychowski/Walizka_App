package com.ans.walizka.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ans.walizka.domain.WalizkaItem;
import com.ans.walizka.service.WalizkaService;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class WalizkaController {
	
	@Autowired
	private WalizkaService walizkaService;
	
	// Fetch Todo Items
	@GetMapping("/api/walizka")
	public ResponseEntity<?> fetchAllTodos(){
		List<WalizkaItem> walizkaItems = walizkaService.fetchAllTodos();
		return ResponseEntity.ok(walizkaItems);
		
	}
	
	@PostMapping("/api/walizka")
	public ResponseEntity<?> createNewTodoItem(){
		// calling the service for addition
		WalizkaItem walizkaItem = walizkaService.createNewTodoItem();
		//Return
		return ResponseEntity.ok(walizkaItem);
	}
	
	@PutMapping("/api/walizka/{id}")
	public ResponseEntity<?> updateTodoItem(@PathVariable Integer id, @RequestBody WalizkaItem walizkaItem){
		// Calling service for updation
		WalizkaItem updatedWalizkaItem = walizkaService.updateTodoItem(id, walizkaItem);
		// Return
		return ResponseEntity.ok(updatedWalizkaItem);
		
	}
	
	@DeleteMapping("/api/walizka/{id}")
	public ResponseEntity<?> deleteTodoItem(@PathVariable Integer id){
		// Calling service for deletion
		walizkaService.deleteTodoItem(id);
		// Return
		return ResponseEntity.ok("ok");
		
	}

}

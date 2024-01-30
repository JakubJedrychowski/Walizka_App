package com.ans.walizka.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ans.walizka.domain.WalizkaItem;
import com.ans.walizka.repository.WalizkaRepository;

@Service
public class WalizkaService {
	
	@Autowired
	private WalizkaRepository todoRepo;

	public List<WalizkaItem> fetchAllTodos(){
		return todoRepo.fetchAllTodos(); 
	}
	
	public WalizkaItem updateTodoItem(Integer id, WalizkaItem walizkaItem) {
		
		Optional<WalizkaItem> todoOpt = todoRepo.fetchAllTodos()
				.stream()
				.filter(item -> item.getId().equals(id))
				.findAny();
		
		if (todoOpt.isPresent()) {
			WalizkaItem item = todoOpt.get();
			item.setIsDone(walizkaItem.getIsDone());
			item.setTask(walizkaItem.getTask());
			return item;
		}
		
		return null;
		
	}
	
	public WalizkaItem createNewTodoItem() {
		WalizkaItem walizkaItem = new WalizkaItem();
		walizkaItem.setIsDone(false);
		walizkaItem = todoRepo.save(walizkaItem);
		walizkaItem.setTask("Kliknij aby zmienić nazwę przedmiotu");
		return walizkaItem;
	}
	
	public void deleteTodoItem(Integer id) {
		todoRepo.delete(id);
	}
}

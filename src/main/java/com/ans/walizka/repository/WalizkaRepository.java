package com.ans.walizka.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.ans.walizka.domain.WalizkaItem;

@Repository
public class WalizkaRepository {

	private Integer idCounter =0;
	private List<WalizkaItem> walizkaItems = new ArrayList<>();
	
	public List<WalizkaItem> fetchAllTodos(){
		if (walizkaItems.size() == 0) {
			WalizkaItem item1 = new WalizkaItem();
			item1.setIsDone(false);
			item1.setTask("Kliknij aby zmienić nazwę przedmiotu");
			item1.setId(idCounter++);

			walizkaItems.add(item1);
			
		}
		return walizkaItems;
		 
	}
	
	public WalizkaItem save (WalizkaItem walizkaItem) {
		walizkaItem.setId(idCounter++);
		walizkaItems.add(walizkaItem);
		return walizkaItem;
		
	}
	
	public void delete(Integer id) {
		walizkaItems = walizkaItems.stream()
				 .filter(walizkaItem -> !walizkaItem.getId().equals(id))
				 .collect(Collectors.toList());
	}
}

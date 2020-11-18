package com.examples.springboot.web.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.examples.springboot.web.app.models.entity.Word;
import com.examples.springboot.web.app.models.services.IWordService;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/api")
public class WordRestController {

	@Autowired
	private IWordService wordService;
	
	@GetMapping("/words")
	public ResponseEntity<Map<String, Object>> getWords(){
		List<Word> wordTemp = null;
		Map<String, Object> response = new HashMap<>();
		try {
			wordTemp = wordService.getWords();
			response.put("ok", true);
			response.put("data", wordTemp);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}catch(DataAccessException e) {
			response.put("ok", false);
			response.put("message", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/words")
	public ResponseEntity<Map<String, Object>> createWords(@RequestBody Word w){
		Word wordTemp = null;
		Map<String, Object> response = new HashMap<>();
		try {
			wordTemp = wordService.createWord(w);
			response.put("ok", true);
			response.put("data", wordTemp);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}catch(DataAccessException e) {
			response.put("ok", false);
			response.put("message", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/words/{id}")
	public ResponseEntity<Map<String, Object>> deleteWord(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		try {
			wordService.deleteWord(id);
			response.put("ok", true);
			response.put("data", "Word deleted.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("ok", false);
			response.put("message", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}

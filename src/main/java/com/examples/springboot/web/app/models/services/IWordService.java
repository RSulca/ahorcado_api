package com.examples.springboot.web.app.models.services;

import java.util.List;

import com.examples.springboot.web.app.models.entity.Word;

public interface IWordService {

	public List<Word> getWords();
	public void deleteWord(Long id);
	public Word createWord(Word word);
	
}

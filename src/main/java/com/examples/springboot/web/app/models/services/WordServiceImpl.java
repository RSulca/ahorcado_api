package com.examples.springboot.web.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examples.springboot.web.app.models.dao.IWordDao;
import com.examples.springboot.web.app.models.entity.Word;

@Service
public class WordServiceImpl implements IWordService {
	
	@Autowired
	private IWordDao wordDao;
	
	@Override
	public List<Word> getWords (){
		return (List<Word>) wordDao.findAll();
	}
	
	@Override
	public Word createWord(Word word) {
		return wordDao.save(word);
	}
	
	@Override
	public void deleteWord(Long id) {
		wordDao.deleteById(id); ;
	}
	
}

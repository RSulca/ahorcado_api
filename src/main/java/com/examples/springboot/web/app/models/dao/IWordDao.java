package com.examples.springboot.web.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.examples.springboot.web.app.models.entity.Word;

public interface IWordDao extends CrudRepository<Word, Long> {

}

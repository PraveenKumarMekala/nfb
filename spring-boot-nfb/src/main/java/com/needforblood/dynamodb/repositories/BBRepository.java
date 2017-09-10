package com.needforblood.dynamodb.repositories;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.needforblood.dynamodb.model.BBDetialsTO;

@EnableScan
public interface BBRepository extends CrudRepository<BBDetialsTO, String> {

	List<BBDetialsTO> findById(String id);
	
}

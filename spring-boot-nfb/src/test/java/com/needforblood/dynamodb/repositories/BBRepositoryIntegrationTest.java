/*package com.needforblood.dynamodb.repositories;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.needforblood.SpringBootNfbApplication;
import com.needforblood.dynamodb.model.BBDetialsTO;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootNfbApplication.class)
@WebAppConfiguration
@ActiveProfiles("local")
@TestPropertySource(properties = { 
  "amazon.dynamodb.endpoint=http://localhost:8000/", 
  "amazon.aws.accesskey=test1", 
  "amazon.aws.secretkey=test1" })
public class BBRepositoryIntegrationTest {

	 private DynamoDBMapper dynamoDBMapper;
	 
	    @Autowired
	    private AmazonDynamoDB amazonDynamoDB;
	 
	    @Autowired
	    BBRepository repository;
	 
	    private static final String NAME = "BB1";
	    private static final String PLACE = "E-CITY";
	    
	    private static final String STATE = "KA";
	    private static final String COUNTRY = "BNG";

	    @Before
	    public void setup() throws Exception {
	        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
	         
	        CreateTableRequest tableRequest = dynamoDBMapper
	          .generateCreateTableRequest(BBDetialsTO.class);
	        tableRequest.setProvisionedThroughput(
	          new ProvisionedThroughput(1L, 1L));
//	        amazonDynamoDB.createTable(tableRequest);
	         
	        //...
	 
	        dynamoDBMapper.batchDelete(
	          (List<BBDetialsTO>)repository.findAll());
	    }
	 
	    @Test
	    public void sampleTestCase() {
	    	BBDetialsTO dave = new BBDetialsTO(NAME, PLACE, STATE, COUNTRY);
	    	repository.save(dave);
	 
	        List<BBDetialsTO> result 
	          = (List<BBDetialsTO>) repository.findAll();
	         
	        assertTrue("Not empty", result.size() > 0);
	        assertTrue("Contains item with expected cost", 
	          result.get(0).getName().equals(NAME));
	    }

}
*/
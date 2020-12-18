package com.odx.test.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odx.test.dao.DemoRepository;
import com.odx.test.entity.Demo;
import com.odx.test.exception.InvalidInputException;

@Service
public class DemoDataProviderService {
 private static final Logger logger= LoggerFactory.getLogger(DemoDataProviderService.class);	
 private DemoRepository demoRepository;

 @Autowired
public DemoDataProviderService(DemoRepository demoRepository) {	
	this.demoRepository = demoRepository;
}
 /**
  * This method returns all demo data
  * @return
  */
 public List<Demo> getAllDemoData(){
	 return demoRepository.findAll();
 }
 /**
  * This methods returns all demo data having the given name
  * @param name
  * @return
  * 
  */
 public List<Demo> getDemoDataGivenName(String name){
	if(StringUtils.isBlank(name)){
		throw new InvalidInputException("Demo name is invalid ");
	} 
	return demoRepository.findAllByName(name);
 }
 /**
  * This method creates demo object and saves it to database
  * @param id
  * @param name
  * @return
  * 
  */
	public Demo createDemo(Integer id, String name)  {
		if (id == null || id == 0) {
			throw new InvalidInputException("Demo id is invalid ");
		}
		if(StringUtils.isBlank(name)){
			throw new InvalidInputException("Demo name is invalid ");
		} 
		Demo demo = new Demo(id, name);
		return demoRepository.save(demo);
	}
	/**
	 * This method deletes a demo given id
	 * @param id
	 * @return
	 * 
	 */
	public String deleteDemo(Integer demoId) {
		try {	
			if(demoRepository.existsById(demoId)){				
				demoRepository.deleteById(demoId);				
			}			
			return "DELETED";
		} catch (Exception e) {
			logger.error("Error while deleting demo with id {}",demoId);
			return "ERROR";
		}
	}
}

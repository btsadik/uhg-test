/**
 * 
 */
package com.odx.test.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import org.springframework.boot.test.context.SpringBootTest;

import com.odx.test.dao.DemoRepository;
import com.odx.test.entity.Demo;
import com.odx.test.exception.InvalidInputException;


@SpringBootTest
class DemoDataProviderServiceTest {
	@InjectMocks
	private DemoDataProviderService demoDataProviderService;
	
	@Mock
	private DemoRepository demoRepository;
	
	private Demo testDemoData;
	String demoName="test demo";
	Integer demoId=1;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test_getAllDemoData_case_when_demo_data_exist() {
		testDemoData= new Demo(demoId,demoName);
		when(demoRepository.findAll()).thenReturn(Arrays.asList(testDemoData));
		List<Demo> allDemo=demoDataProviderService.getAllDemoData();
		assertNotNull(allDemo);
		assertTrue(allDemo.size() == 1);
		allDemo.forEach(d-> {
			assertTrue(d.getName().equals("test demo"));
			assertTrue(d.getId() == 1);
		});
	}
	
	@Test
	void test_getAllDemoData_case_when_demo_data_not_exist() {
		when(demoRepository.findAll()).thenReturn(Collections.emptyList());
		List<Demo> allDemo=demoDataProviderService.getAllDemoData();
		assertNotNull(allDemo);
		assertTrue(allDemo.size() == 0);		
	}
	
	@Test
	void test_getDemoDataGivenName_case_when_name_is_null() {
		String name = null;
		assertThrows(InvalidInputException.class, ()->{
			demoDataProviderService.getDemoDataGivenName(name);
		});				
	}
	@Test
	void test_getDemoDataGivenName_case_when_name_is_empty() {
		String name = "";
		assertThrows(InvalidInputException.class, ()->{
			demoDataProviderService.getDemoDataGivenName(name);
		});				
	}
	@Test
	void test_getDemoDataGivenName_case_when_demo_data_exist() {
		testDemoData= new Demo(demoId,demoName);
		when(demoRepository.findAllByName(anyString())).thenReturn(Arrays.asList(testDemoData));
		List<Demo> allDemo=demoDataProviderService.getDemoDataGivenName(demoName);
		assertNotNull(allDemo);
		assertTrue(allDemo.size() == 1);
		allDemo.forEach(d-> {
			assertTrue(d.getName().equals(demoName));
			assertTrue(d.getId() == demoId);
		});
	}
	
	@Test
	void test_getDemoDataGivenName_case_when_demo_data_not_exist() {
		String name = "test";
		when(demoRepository.findAllByName(anyString())).thenReturn(Collections.emptyList());
		List<Demo> allDemo=demoDataProviderService.getDemoDataGivenName(name);
		assertNotNull(allDemo);
		assertTrue(allDemo.size() == 0);		
	}
	
	@Test
	void test_createDemo_case_when_demo_id_is_null() {
		String name = "test";
		Integer id=null;
		assertThrows(InvalidInputException.class, ()->{
			demoDataProviderService.createDemo(id, name);
		});	
	}
	
	@Test
	void test_createDemo_case_when_demo_id_is_0() {
		String name = "test";
		Integer id=0;
		assertThrows(InvalidInputException.class, ()->{
			demoDataProviderService.createDemo(id, name);
		});	
	}
	
	@Test
	void test_createDemo_case_when_demo_name_is_null() {
		String name = null;
		Integer id=2;
		assertThrows(InvalidInputException.class, ()->{
			demoDataProviderService.createDemo(id, name);
		});	
	}
	
	@Test
	void test_createDemo_case_when_demo_name_is_empty() {
		String name = "";
		Integer id=2;
		assertThrows(InvalidInputException.class, ()->{
			demoDataProviderService.createDemo(id, name);
		});	
	}
	
	@Test
	void test_createDemo_case_positive() {
		String name = "test";
		Integer id=2;
		testDemoData= new Demo(id,name);
		when(demoRepository.save(any())).thenReturn(testDemoData);
		Demo savedDemo=	demoDataProviderService.createDemo(id, name);
		assertNotNull(savedDemo);
		assertTrue(savedDemo.getName().equals(name));
		assertTrue(savedDemo.getId() == id);	
	}
	
	@Test
	void test_deleteDemo_case_demo_data_exist() {
	    Integer demoId=1; 
		when(demoRepository.existsById(anyInt())).thenReturn(true);
		doNothing().when(demoRepository).deleteById(anyInt());
		String result = demoDataProviderService.deleteDemo(demoId);
		assertNotNull(result);
		assertTrue(result.equals("DELETED"));
			
	}
	
	@Test
	void test_deleteDemo_case_demo_data_not_exist() {
		Integer demoId = 1;
		when(demoRepository.existsById(anyInt())).thenReturn(false);
		doNothing().when(demoRepository).deleteById(anyInt());
		String result = demoDataProviderService.deleteDemo(demoId);
		assertNotNull(result);
		assertTrue(result.equals("DELETED"));
	}
	
	@Test
	void test_deleteDemo_case_exception_thrown() {
		Integer demoId = 1;
		when(demoRepository.existsById(anyInt())).thenReturn(true);
		doThrow(new IllegalArgumentException()).when(demoRepository).deleteById(anyInt());
		String result = demoDataProviderService.deleteDemo(demoId);
		assertNotNull(result);
		assertTrue(result.equals("ERROR"));
	}
	
	
}

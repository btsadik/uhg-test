package com.odx.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.odx.test.entity.Demo;
import com.odx.test.service.DemoDataProviderService;

@RestController
@RequestMapping("/demo")
public class DemoController {
	
	private DemoDataProviderService demoDataProviderService;

	@Autowired
	public DemoController(DemoDataProviderService demoDataProviderService) {		
		this.demoDataProviderService = demoDataProviderService;
	}
	
    @GetMapping("/allDemo")
	public ResponseEntity<List<Demo>> getAllDemoData(){
		return ResponseEntity.ok(demoDataProviderService.getAllDemoData());
	}
    @GetMapping("/someDemo")
   	public ResponseEntity<List<Demo>> getDamoDataGivenName(@RequestParam String name) {
   		return ResponseEntity.ok(demoDataProviderService.getDemoDataGivenName(name));
   	}
    
    @PostMapping("/createDemo")
   	public ResponseEntity<Demo> createDemoData(@RequestParam Integer id,@RequestParam String name) {
   		return ResponseEntity.ok(demoDataProviderService.createDemo(id, name));
   	}
    
    @DeleteMapping("/deleteDemo")
   	public ResponseEntity<String> deleteDemoData(@RequestParam Integer id) {
   		return ResponseEntity.ok(demoDataProviderService.deleteDemo(id));
   	}
}

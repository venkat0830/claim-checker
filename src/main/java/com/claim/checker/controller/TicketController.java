package com.claim.checker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.claim.checker.model.BoTable;
import com.claim.checker.model.ProcessHpcClaimsListReq;
import com.claim.checker.response.BoTableResponse;
import com.claim.checker.service.RecordService;
import com.claim.checker.util.ErrorTranslationUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class TicketController {

	@Autowired
	private RecordService recordService;
	
	@Autowired
	ErrorTranslationUtility utility;
	
	String boTableUrl;
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/hpcSchedule", produces = "application/json")
	@ResponseBody
	public void startScheduler() throws Exception {
	ObjectMapper mapper = new ObjectMapper();
	long processedRecordCount = recordService.processHpcClaims();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/hpcSchedule", produces = "application/json")
	@ResponseBody
	public void hpcSchedulerV2(@RequestBody ProcessHpcClaimsListReq claimsList) throws Exception {
	if(null != claimsList) {
		recordService.processHpcClaimsV2(claimsList);
	} else {
		System.out.println("RECEIVED Empty list");
	}
	}
	
	@RequestMapping(value = "/trackitBoTable", method = RequestMethod.GET)
	public ResponseEntity<Object> trackItBoTableReq(){
		try {
			RestTemplate restTemplate = new RestTemplate();
			BoTable boTable = restTemplate.getForObject("www.boTableUrl.com", BoTable.class);
			BoTableResponse boTableResponse = new BoTableResponse();
			if (null != boTable) {
				boTableResponse.setStatus("Success 200");
				boTableResponse.setBoTable(boTable);
			}
			return new ResponseEntity<Object>(boTableResponse, HttpStatus.OK);
		} catch (Exception ex) {
			BoTableResponse boTableResponse = utility.generateBotableErrorresponse("TITACHE", "Internal Server error", ex.getMessage());
			return new ResponseEntity<Object>(boTableResponse.getErrorResponse(), boTableResponse.getHeaderMap(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

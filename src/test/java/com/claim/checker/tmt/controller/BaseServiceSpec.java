package com.claim.checker.tmt.controller;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.client.RestTemplate;

import com.claim.checker.controller.TicketController;
import com.claim.checker.dao.RecordDao;
import com.claim.checker.dao.RecordDaoImpl;
import com.claim.checker.service.RecordService;
import com.claim.checker.service.RecordServiceImpl;
import com.claim.checker.util.RestUtil;

import spock.lang.Specification;

class BaseServiceSpec extends Specification {
	
	TicketController ticketController
	RecordServiceImpl recordServiceImpl
	
	RecordService recordService
	
	RecordDao recordDao
	RecordDaoImpl recordDaoImpl
	
	ClinkTokenGenerator clinkTokenGenerator
	RestUtil restUtil
	String cLinkEndPoint
	RestTemplate restTemplate
	MongoTemplate mongoTemplate
	
	def setup() {
		recordservice = Mock()
		cLinkTokenGenerator =  Stub()
		cLinkEndPoint = "https:link-health-stage.com"
		
		recordServiceImpl =  new RecordServiceImpl(reconRecordDao:reconRecordDao, clinkTokenGenerator:clinkTokenGenerator, cLinkEndPoint:cLinkEndPoint, RestUtil: restUtil)
		
	}

}

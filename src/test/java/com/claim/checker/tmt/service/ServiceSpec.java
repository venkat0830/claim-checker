package com.claim.checker.tmt.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;

import com.claim.checker.consumer.clinkGeneratedFile.ClaimResponse;
import com.claim.checker.dto.RecordDto;
import com.claim.checker.service.RecordServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ServiceSpec extends BaseServiceSpec {

	@Shared
	ObjectMapper mapper def
	@Shared
	MultiValueMap<Strin, String> respEntity def
	@Shared
	ResponseEntity<String> repo def
	@Shared
	ResponseEntity<ClaimResponse> repo1
	def claimResponse
	HttpStatusCodeException ex

	def setup() {

	}

	def'cLink-

	valid ICN Search'(){
		given:
			String reqString = ""
			RecordDto recordDto = mapper.readValue(reqString, RecordDto.class)
			long startTime = System.currentTimeMillis()
			
			when: 'we invoke'
				RecordDto resp =  RecordServiceImpl.cLinkCall(recordDto, startTime)
				then:
					resp != null
					and:
						restUtil.getRestTemplate()>> restTemplate
						restTemplate.exchange(*_) >> repo
	}
}

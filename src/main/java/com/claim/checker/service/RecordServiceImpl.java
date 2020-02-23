package com.claim.checker.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.claim.checker.consumer.clinkGeneratedFile.ClaimResponse;
import com.claim.checker.consumer.clinkGeneratedFile.Claims;
import com.claim.checker.consumer.clinkGeneratedFile.ClaimsRequest;
import com.claim.checker.dao.RecordDao;
import com.claim.checker.dto.RecordDto;
import com.claim.checker.model.BoTable;
import com.claim.checker.model.ClaimDetails;
import com.claim.checker.model.MemberDetails;
import com.claim.checker.model.ProcessHpcClaimsListReq;
import com.claim.checker.model.ProviderDetails;
import com.claim.checker.model.Record;
import com.claim.checker.response.BoTableResponse;
import com.claim.checker.response.HpcErrorResponse;
import com.claim.checker.util.Constants;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBObject;

public class RecordServiceImpl implements RecordService {

	@Autowired
	private RecordDao reconRecordDao;

	private String cLinkEndPoint = "https://api-stage-linkhealth.com/clink-api/api/claim/summary/byicn/v1.0?tin= {tin}&icn= {icn},&payerId= {payerId}";

	@Override
	public long processHpcClaims() throws Exception {
		long processRecordscount = 0;
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<DBObject> claimsList = reconRecordDao.getHpcReceivedrecords();
			if (null != claimsList) {
				for (DBObject claim : claimsList) {
					String claimString = null;
					long starttime = System.currentTimeMillis();
					try {
						mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
						claimString = mapper.writeValueAsString(claim);
						RecordDto unProcessedRecord = mapper.readValue(claimString, RecordDto.class);
						RecordDto processedRecord = claimsHighwayCall(unProcessedRecord, processedRecordsCount,
								startTime, isTOPSIncluded);
						if (null != processedRecord) {
							saveUnProcessedRecords(unProcessedRecord, processedRecord);
							processedRecordsCount++;
						}
					} catch (Exception ex) {
						ex.getMessage();
					}

				}
			}
		} catch (Exception ex) {
			ex.getMessage();
		}
		return processRecordscount;
	}

	private void saveUnProcessedRecords(RecordDto unProcessedRecord, RecordDto processedRecord) {
		Record reconRecord = new Record();
		processedRecord.getRecordInfo().setRecordType("HPC");
		processedRecord.getRecordInfo().setRecordID(unProcessedRecord.getRecordInfo().getRecordID());
		processedRecord.getRecordInfo().setRecordLastUpdatedDate(dateUtility.getCDT());
		processedRecord.getRecordInfo().setPredictionDate(unProcessedRecord.getRecordInfo().getPredictionStartDate());
		processedRecord.getRecordInfo().setPredictionEndDate(unProcessedRecord.getRecordInfo().getPredictionEndDate());
		BeanUtils.copyProperties(processedRecord, reconRecord, "tmtEventId", "recordLastUpdateDate");
		reconRecordDao.saveHpcProcessedRecord(reconRecord, startTime);
	}

	private boolean clinkCall(RecordDto unProcessedRecord) throws Exception {
		ClaimResponse claimRes = getClinkResponse(unProcessedRecord.getProviderDetails().getProvidertin(),
				unProcessedRecord.getClaimDetails().getIcn(), "87726");
		boolean isClaimFoundInClink = false;
		if (claimRes != null && claimRes.getClaims() != null) {
			List<Claims> claims = claimRes.getClaims();
			for (Claims claims2 : claims) {
				if (claims2.getMemberInfo().getCliamServiceId().equals("TOPS")
						&& Constants.CLAIM_FINALIZESTATUS_LIST.contains(claims2.getClaimStatusCode())) {
					RecordDto processed = convertClamsToRecordDto(claims2);
					saveUnProcessedRecords(unProcessedRecord, processed);
					isClaimFoundInClink = true;

				} else {
					saveClinkErrorResponse(hpcRequest, request, response, httpStatusCode, cLinkCode, appErrorCode,
							startTime, platform);
				}
			}
		}

		return isClaimFoundInClink;
	}

	private RecordDto convertClamsToRecordDto(Claims claims2) {
		RecordDto recordDto = new RecordDto();
		ClaimDetails claimDetails = new ClaimDetails();
		claimDetails.setBiiledAmount(claims2.getClaimSummary().getDeductibleAmt());
		MemberDetails memberDetails = new MemberDetails();
		ProviderDetails providerDetails =  new ProviderDetails();
		recordDto.setClaimDetails(claimDetails);
		recordDto.setMemberDetails(memberDetai
				ls);
		recordDto.setProviderDetails(providerDetails);
		return recordDto;
	}

	@Override
	public long processHpcClaimsV2(ProcessHpcClaimsListReq claimsList) throws Exception {
		long processRecordscount = 0;
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<RecordDto> claimsList = reconRecordDao.getHpcReceivedrecords();
			if (null != claimsList) {
				for (RecordDto claim : claimsList) {
					String claimString = null;
					long startTime = System.currentTimeMillis();
					try {
						boolean isTOPSIncluded = false;
						if(isTrackItBoTableReq()) {
						isTOPSIncluded = clinkCall(claim, processRecordscount);
						}
						RecordDto processedRecord = claimsHighwayCall(claim, processRecordscount, startTime);
						if (null != processedRecord) {
							Record reconRecord = new Record();
							processedRecord.getRecordInfo().setRecordType("HPC");
							processedRecord.getRecordInfo()
									.setRecordID(unProcessedRecord.getRecordInfo().getRecordID());
							processedRecord.getRecordInfo().setRecordLastUpdatedDate(dateUtility.getCDT());
							processedRecord.getRecordInfo()
									.setPredictionDate(unProcessedRecord.getRecordInfo().getPredictionStartDate());
							processedRecord.getRecordInfo()
									.setPredictionEndDate(unProcessedRecord.getRecordInfo().getPredictionEndDate());
							BeanUtils.copyProperties(processedRecord, reconRecord, "tmtEventId",
									"recordLastUpdateDate");
							reconRecordDao.saveHpcProcessedRecord(reconRecord, startTime);
							processedRecordsCount++;
						}
					} catch (Exception ex) {
						ex.getMessage();
					}
				}
			}
		} catch (Exception ex) {
			ex.getMessage();
		}
		return processRecordscount;
	}

	private RecordDto claimsHighwayCall(RecordDto reconRecordDtoReq, long startTime, boolean isTOPSInclude)
			throws Exception {
		return null;
	}

	private void saveClinkErrorResponse(RecordDto hpcRequest, ClaimsRequest request, ClaimResponse response,
			String httpStatusCode, String cLinkCode, String appErrorCode, long startTime, String platform) {
		HpcErrorResponse Eresponse = new HpcErrorResponse();
		Eresponse.setHpcRequest(hpcRequest);
		Eresponse.setClinkRequest(clinkRequest);
		Eresponse.setHttpStatusCode(httpStatusCode);
		Eresponse.setClResponse(clResponse);
		Eresponse.setcLinkErrorCode(cLinkErrorCode);
		Eresponse.setAppErrorCode(appErrorCode);
		Eresponse.setPlatform(platform);
		reconRecordDao.saveHpcErrorResponse(Eresponse, startTime);
	}

	private ClaimResponse getClinkResponse(String tin, String icn, String payerId) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		String token = cLinkTokenGenerator.getToken();
		Map<String, String> uriParam = new HashMap<>();
		headers.set("Authorization", "Bearer " + token);
		uriParam.put("tin", tin);
		uriParam.put("icn", icn);
		uriParam.put("payerId", payerId);
		HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<ClaimResponse> claimRespone = restTemplate.exchange(cLinkEndPoint, HttpMethod.GET, httpEntity,
				ClaimResponse.class, uriParam);
		return claimRespone.getBody();
	}

	private boolean isTrackItBoTableReq() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		BoTable boTable = restTemplate.getForObject("www.boTableUrl.com", BoTable.class);
		if (null != boTable && boTable.getcLinkCall() != null) {
			if (boTable.getcLinkCall().equals("true")) {
				return true;
			}
		}
		return false;
	}

}

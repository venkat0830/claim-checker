package com.claim.checker.dao;

import java.util.List;

import com.claim.checker.model.Record;
import com.claim.checker.response.HpcErrorResponse;
import com.mongodb.DBObject;

public interface RecordDao {
	
	public void saveHpcErrorResponse(HpcErrorResponse errorresponse, long startTime);
	public void saveHpcProcessedRecord(Record reconRecord, long startTime);
	public List<DBObject> getHpcReceivedrecords() throws Exception;

}

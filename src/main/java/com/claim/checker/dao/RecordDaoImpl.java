package com.claim.checker.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.claim.checker.model.Record;
import com.claim.checker.response.HpcErrorResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class RecordDaoImpl implements RecordDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	private String hpcRawCollection = "hpcReceivedCollection";
	private String hpcProcessed = "hpcProcessed";
	private String hpcError = "hpcError";

	@Override
	public void saveHpcErrorResponse(HpcErrorResponse errorresponse, long startTime) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String recordString = mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
					.writeValueAsString(errorresponse);
			BasicDBObject recDbObject = BasicDBObject.parse(recordString);
			DBCollection dbCollection = mongoTemplate.getCollection(this.hpcError);
			dbCollection.save(recDbObject);
			DBCollection receivedCollection = mongoTemplate.getCollection(this.hpcRawCollection);
			BasicDBObject document = new BasicDBObject();
			document.put("recordInfo.recordID", errorresponse.getHpcRequest.getRecordInfo.getRecordID());
			receivedCollection.remove(document);
		} catch (Exception e) {
			e.getMessage();
		}

	}

	@Override
	public void saveHpcProcessedRecord(Record reconRecord, long startTime) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String recordString = mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
					.writeValueAsString(reconRecord);
			BasicDBObject recDbObject = BasicDBObject.parse(recordString);
			DBCollection dbCollection = mongoTemplate.getCollection(this.hpcProcessed);
			dbCollection.save(recDbObject);
			DBCollection receivedCollection = mongoTemplate.getCollection(this.hpcRawCollection);
			BasicDBObject document = new BasicDBObject();
			document.put("recordInfo.recordID", reconRecord.getRecordInfo.getRecordID());
			receivedCollection.remove(document);
		} catch (Exception e) {
			e.getMessage();
		}

	}

	@Override
	public List<DBObject> getHpcReceivedrecords() throws Exception {
		List<DBObject> result = null;
		try {
			DBCollection dbCollection = mongoTemplate.getCollection(this.hpcRawCollection);
			result = dbCollection.find().toArray();

		} catch (Exception ex) {
			ex.getMessage();
		}
		return null;

	}
}

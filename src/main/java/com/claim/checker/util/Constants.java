package com.claim.checker.util;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.assertj.core.util.Arrays;

public class Constants {

	public static final String HPC = "HPC";

	public static final List<String> CLAIM_FINALIZESTATUS_LIST = Collections
			.unmodifiableList(Arrays.asList("A", "D", "F", "P", "U"));

	public static final List<String> CLAIM_STATUS_CODE_LIST = Collections
			.unmodifiableList(Arrays.asList("205", "206", "211", "212", "213"));
	public static final Map<String, String> INVALID_CLAIMS_MAP = 
			Collections.unmodifiableSortedMap(new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER) {{
				put("Z","301");
				put("C", "302");
				put("I","303");
				put("M", "304");
			}});
	
	
public static final String fault_hpc_not_finalized_claim= "HPCNFC";
public static final String fault_hpc_invalid_resp_structure= "HPCIRS";
public static final String fault_hpc_invalid_status_code= "HPCISC";
public static final String fault_hpc_invalid_claim_request = "HPCICR";
public static final String fault_hpc_claim_processing_exception = "HPCCPE";
public static final String SUCCESS_200 = "200";
}

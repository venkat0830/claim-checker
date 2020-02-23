package com.claim.checker.util;

import java.util.Collections;
import java.util.List;

import org.assertj.core.util.Arrays;

public class Constants {
	
	public static final String HPC = "HPC";
	
	public static final List<String> CLAIM_FINALIZESTATUS_LIST = Collections.unmodifiableList(Arrays.asList("A","D","F","P","U"));
	
	public static final List<String> CLAIM_STATUS_CODE_LIST = Collections.unmodifiableList(Arrays.asList("205","206","211","212","213"));

}

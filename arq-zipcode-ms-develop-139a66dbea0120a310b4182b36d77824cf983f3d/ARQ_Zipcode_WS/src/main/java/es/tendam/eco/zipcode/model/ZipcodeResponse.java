package es.tendam.eco.zipcode.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ZipcodeResponse {
	private static final String VERIFIED_NONE = "None";
	private static final String VERIFIED = "Verified";
	private static final String VERIFIED_MULTIPLE = "Multiple";
	
	private String verifyLevel;
	private int count;
	
	@JsonInclude(Include.NON_NULL)
	private List<ZipcodeResult> results;
	
	public ZipcodeResponse() {
		this.verifyLevel=VERIFIED_NONE;
		this.count=0;
	}
	
	public ZipcodeResponse(List<ZipcodeResult> zipcodeList) {
		this.count=zipcodeList.size();	
		this.verifyLevel=this.count==1?VERIFIED:VERIFIED_MULTIPLE;
		this.results=zipcodeList;
	}
	
	public int getCount() {
		return count;
	}
	
	public List<ZipcodeResult> getResults() {
		return results;
	}
	
	public String getVerifyLevel() {
		return verifyLevel;
	}

}

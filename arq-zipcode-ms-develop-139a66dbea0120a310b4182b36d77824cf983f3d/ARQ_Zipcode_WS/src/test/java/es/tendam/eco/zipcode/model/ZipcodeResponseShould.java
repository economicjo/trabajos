package es.tendam.eco.zipcode.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import es.tendam.eco.zipcode.model.ZipcodeResponse;
import es.tendam.eco.zipcode.model.ZipcodeResult;

public class ZipcodeResponseShould {
	
	@Test
	public void manage_single_result() {
		List<ZipcodeResult> responseList = new ArrayList<ZipcodeResult>();
		responseList.add(new ZipcodeResult());
		
		ZipcodeResponse response = new ZipcodeResponse(responseList);

		assertThat(response.getCount()).isEqualTo(1);
		assertThat(response.getVerifyLevel()).isEqualTo("Verified");
	}

	@Test
	public void manage_multiple_result() {
		List<ZipcodeResult> responseList = new ArrayList<ZipcodeResult>();
		responseList.add(new ZipcodeResult());
		responseList.add(new ZipcodeResult());
		
		ZipcodeResponse response = new ZipcodeResponse(responseList);

		assertThat(response.getCount()).isEqualTo(2);
		assertThat(response.getVerifyLevel()).isEqualTo("Multiple");
	}

	@Test
	public void manage_no_results() {
		ZipcodeResponse response = new ZipcodeResponse();

		assertThat(response.getCount()).isEqualTo(0);
		assertThat(response.getVerifyLevel()).isEqualTo("None");
	}

}

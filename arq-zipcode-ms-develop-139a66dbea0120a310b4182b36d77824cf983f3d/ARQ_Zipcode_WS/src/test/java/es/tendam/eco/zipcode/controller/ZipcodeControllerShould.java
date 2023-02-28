package es.tendam.eco.zipcode.controller;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.tendam.eco.zipcode.model.ZipcodeCriteria;

@SpringBootTest
public class ZipcodeControllerShould {
	
	@Autowired
	private ZipcodeController zipcodeController;
	
	@Test
	public void set_like_criteria() {
		ZipcodeCriteria criteria = new ZipcodeCriteria();
		criteria.setCountry("PT");
		criteria.setZipcode("28914123");

		zipcodeController.getZipcode(criteria);
				
		assertThat(criteria.isLikeQuery()).isTrue();
	}
	
	
	@Test
	public void set_equal_criteria() {
		ZipcodeCriteria criteria = new ZipcodeCriteria();
		criteria.setCountry("ES");
		criteria.setZipcode("28914123");
		
		zipcodeController.getZipcode(criteria);
		
		assertThat(criteria.isLikeQuery()).isFalse();
	}
	
}

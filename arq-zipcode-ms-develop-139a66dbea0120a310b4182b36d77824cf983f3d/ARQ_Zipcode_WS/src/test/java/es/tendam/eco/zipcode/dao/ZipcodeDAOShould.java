package es.tendam.eco.zipcode.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.tendam.eco.zipcode.dao.ZipcodeDAO;
import es.tendam.eco.zipcode.model.ZipcodeCriteria;
import es.tendam.eco.zipcode.model.ZipcodeResult;

@SpringBootTest
public class ZipcodeDAOShould {
	
	@Autowired
	private ZipcodeDAO zipcodeDAO;
	
	@Test
	public void find_single_zipcode() {
		ZipcodeCriteria criteria = new ZipcodeCriteria();
		criteria.setCountry("ES");
		criteria.setZipcode("01165");
		
		List<ZipcodeResult> zipcodeInfo = zipcodeDAO.getZipcodeInfo(criteria);
		
		assertThat(zipcodeInfo).size().isEqualTo(1);
	}
	
	@Test
	public void find_multiple_zipcode() {
		ZipcodeCriteria criteria = new ZipcodeCriteria();
		criteria.setCountry("ES");
		criteria.setZipcode("28914");
		
		List<ZipcodeResult> zipcodeInfo = zipcodeDAO.getZipcodeInfo(criteria);
		
		assertThat(zipcodeInfo).size().isGreaterThan(1);
	}
	
	
	@Test
	public void find_no_zipcode() {
		ZipcodeCriteria criteria = new ZipcodeCriteria();
		criteria.setCountry("ES");
		criteria.setZipcode("28914123");
		
		List<ZipcodeResult> zipcodeInfo = zipcodeDAO.getZipcodeInfo(criteria);
		
		assertThat(zipcodeInfo).isEmpty();
	}
	
}

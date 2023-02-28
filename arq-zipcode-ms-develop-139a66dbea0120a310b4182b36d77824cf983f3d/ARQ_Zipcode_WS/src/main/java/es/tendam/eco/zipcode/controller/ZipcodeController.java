package es.tendam.eco.zipcode.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import es.tendam.eco.zipcode.dao.ZipcodeDAO;
import es.tendam.eco.zipcode.model.ZipcodeCriteria;
import es.tendam.eco.zipcode.model.ZipcodeResponse;
import es.tendam.eco.zipcode.model.ZipcodeResult;

@RestController
public class ZipcodeController {
	
	@Autowired
	private ZipcodeDAO zipcodeDAO;
	
	@Value("${app.query.like.countryList}")
	private List<String> countryList;
	
	@GetMapping(value = "/verify")
	public ZipcodeResponse getZipcode(@Valid ZipcodeCriteria criteria) {
		criteria.setLikeQuery(countryList.contains(criteria.getCountry().toUpperCase()));
		List<ZipcodeResult> zipcodeList  = zipcodeDAO.getZipcodeInfo(criteria);
		
		if (zipcodeList.isEmpty()) {
			return new ZipcodeResponse();
		} else {
			return new ZipcodeResponse(zipcodeList);
		}
	};
	
}

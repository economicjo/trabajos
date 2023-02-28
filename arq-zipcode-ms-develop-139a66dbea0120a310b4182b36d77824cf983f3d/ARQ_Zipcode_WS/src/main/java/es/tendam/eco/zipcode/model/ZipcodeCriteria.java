package es.tendam.eco.zipcode.model;

import javax.validation.constraints.NotNull;

public class ZipcodeCriteria {
	
	@NotNull(message = "'country' parameter is required")
	private String country;
	
	@NotNull(message = "'zipcode' parameter is required")
	private String zipcode;
	
	private String source;
	
	private boolean likeQuery;
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public boolean isLikeQuery() {
		return likeQuery;
	}
	public void setLikeQuery(boolean likeQuery) {
		this.likeQuery = likeQuery;
	}
	
}

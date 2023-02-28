package es.tendam.eco.zipcode.dao;

import java.util.List;

import es.tendam.eco.zipcode.model.ZipcodeCriteria;
import es.tendam.eco.zipcode.model.ZipcodeResult;

public interface ZipcodeDAO {
	public List<ZipcodeResult> getZipcodeInfo(ZipcodeCriteria criteria);
}

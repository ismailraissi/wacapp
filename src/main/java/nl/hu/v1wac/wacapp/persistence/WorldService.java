package nl.hu.v1wac.wacapp.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import nl.hu.v1wac.wacapp.persistence.Country;
import nl.hu.v1wac.wacapp.persistence.CountryPostgresDaoImpl;

public class WorldService {
	CountryDao dao = new CountryPostgresDaoImpl();	
	
	public List<Country> getAllCountries() {
		return dao.findall();
	}
	
	public List<nl.hu.v1wac.wacapp.persistence.Country> get10LargestPopulations() {
		return dao.find10LargestPopulations();
	}

	public List<nl.hu.v1wac.wacapp.persistence.Country> get10LargestSurfaces() {
		return dao.find10LargestSurfaces();
	}
	
	public nl.hu.v1wac.wacapp.persistence.Country getCountryByCode(String code) {
		return dao.findByCode(code);
	}
}

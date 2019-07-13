package nl.hu.v1wac.wacapp.webservices;

import java.util.List;

import nl.hu.v1wac.wacapp.persistence.Country;
import nl.hu.v1wac.wacapp.persistence.CountryDao;
import nl.hu.v1wac.wacapp.persistence.CountryPostgresDaoImpl;

public class WorldService {
	CountryDao dao = new CountryPostgresDaoImpl();	
	
	public List<Country> getAllCountries() {
		return dao.findall();
	}
	
	public List<Country> get10LargestPopulations() {
		return dao.find10LargestPopulations();
	}

	public List<Country> get10LargestSurfaces() {
		return dao.find10LargestSurfaces();
	}
	
	public Country getCountryByCode(String code) {
		return dao.findByCode(code);
	}
	
	public boolean delete(String code) {
		return dao.delete(code);
	}
	
	public boolean updateCountry(String code, String land, String hoofdstad, String regio, int oppervlakte, int inwoners) {
		return dao.updateCountry(code, land, hoofdstad, regio, oppervlakte, inwoners);
	}
}

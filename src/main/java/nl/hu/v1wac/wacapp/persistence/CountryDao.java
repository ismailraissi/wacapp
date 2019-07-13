package nl.hu.v1wac.wacapp.persistence;

import java.util.List;

public interface CountryDao {
	public boolean save(Country country);
	public List<Country> findall();
	public Country findByCode(String code);
	public List<Country> find10LargestPopulations();
	public List<Country> find10LargestSurfaces();
	public boolean update(Country country);
	public boolean delete(String code);
	public boolean updateCountry(String code, String land, String hoofdstad, String regio, int oppervlakte, int inwoners);
}

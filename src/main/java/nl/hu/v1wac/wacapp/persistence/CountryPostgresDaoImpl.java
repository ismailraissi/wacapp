package nl.hu.v1wac.wacapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryPostgresDaoImpl extends PostgresBaseDao implements CountryDao {

	public boolean save(Country country) {
		try (Connection con = super.getConnection()) {
			String query = "INSERT INTO country(code, iso3, name, capital, continent, region, surfacearea, population, governmentform) VALUES('";
			query += country.getCode() + "','" + country.getIso3() + "','" + country.getName() + "','" + country.getCapital() + "','" + country.getContinent() + "','";
			query += country.getRegion() + "','" + country.getSurface() + "','" + country.getPopulation() + "','" + country.getGovernment() + "')";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}

	public List<Country> findall() {
		List<Country> allCountries = new ArrayList<Country>();
		try (Connection con = super.getConnection()){
			String query = "SELECT code, iso3, name, capital, continent, region, surfacearea, population, governmentform, latitude, longitude FROM country";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet dbResultSet = pstmt.executeQuery();
			while (dbResultSet.next()) {
				String code = dbResultSet.getString("code");
				String iso3 = dbResultSet.getString("iso3");
				String name = dbResultSet.getString("name");
				String capital = dbResultSet.getString("capital");
				String continent = dbResultSet.getString("continent");
				String region = dbResultSet.getString("region");
				double surface = dbResultSet.getDouble("surfacearea");
				int population = dbResultSet.getInt("population");
				String government = dbResultSet.getString("governmentform");
				double latitude = dbResultSet.getDouble("latitude");
				double longitude = dbResultSet.getDouble("longitude");
				Country land = new Country(code, iso3, name, capital, continent, region, surface, population, government, latitude, longitude);
				allCountries.add(land);
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
		return allCountries;
	}

	public Country findByCode(String codeland) {
		Country land = new Country();
		try (Connection con = super.getConnection()){
			String query = "SELECT code, iso3, name, capital, continent, region, surfacearea, population, governmentform, latitude, longitude FROM country";
			query += " WHERE code = '" + codeland.toUpperCase() + "'";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet dbResultSet = pstmt.executeQuery();
			while (dbResultSet.next()) {
				String code2 = dbResultSet.getString("code");
				String iso3 = dbResultSet.getString("iso3");
				String name = dbResultSet.getString("name");
				String capital = dbResultSet.getString("capital");
				String continent = dbResultSet.getString("continent");
				String region = dbResultSet.getString("region");
				double surface = dbResultSet.getDouble("surfacearea");
				int population = dbResultSet.getInt("population");
				String government = dbResultSet.getString("governmentform");
				double latitude = dbResultSet.getDouble("latitude");
				double longitude = dbResultSet.getDouble("longitude");
				land = new Country(code2, iso3, name, capital, continent, region, surface, population, government, latitude, longitude);
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
		return land;
	}

	public List<Country> find10LargestPopulations() {
		List<Country> allCountries = new ArrayList<Country>();
		try (Connection con = super.getConnection()){
			String query = "SELECT code, iso3, name, capital, continent, region, surfacearea, population, governmentform, latitude, longitude FROM country";
			query += " ORDER BY population DESC LIMIT 10";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet dbResultSet = pstmt.executeQuery();
			while (dbResultSet.next()) {
				String code = dbResultSet.getString("code");
				String iso3 = dbResultSet.getString("iso3");
				String name = dbResultSet.getString("name");
				String capital = dbResultSet.getString("capital");
				String continent = dbResultSet.getString("continent");
				String region = dbResultSet.getString("region");
				double surface = dbResultSet.getDouble("surfacearea");
				int population = dbResultSet.getInt("population");
				String government = dbResultSet.getString("governmentform");
				double latitude = dbResultSet.getDouble("latitude");
				double longitude = dbResultSet.getDouble("longitude");
				Country land = new Country(code, iso3, name, capital, continent, region, surface, population, government, latitude, longitude);
				allCountries.add(land);
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
		return allCountries;
	}

	public List<Country> find10LargestSurfaces() {
		List<Country> allCountries = new ArrayList<Country>();
		try (Connection con = super.getConnection()){
			String query = "SELECT code, iso3, name, capital, continent, region, surfacearea, population, governmentform, latitude, longitude FROM country";
			query += " ORDER BY surfacearea DESC LIMIT 10";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet dbResultSet = pstmt.executeQuery();
			while (dbResultSet.next()) {
				String code = dbResultSet.getString("code");
				String iso3 = dbResultSet.getString("iso3");
				String name = dbResultSet.getString("name");
				String capital = dbResultSet.getString("capital");
				String continent = dbResultSet.getString("continent");
				String region = dbResultSet.getString("region");
				double surface = dbResultSet.getDouble("surfacearea");
				int population = dbResultSet.getInt("population");
				String government = dbResultSet.getString("governmentform");
				double latitude = dbResultSet.getDouble("latitude");
				double longitude = dbResultSet.getDouble("longitude");
				Country land = new Country(code, iso3, name, capital, continent, region, surface, population, government, latitude, longitude);
				allCountries.add(land);
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
		return allCountries;
	}
	public boolean update(Country country) {
		try (Connection con = super.getConnection()) {
			String query = "UPDATE country(code, iso3, name, capital, continent, region, surfacearea, population, governmentform, latitude, longitude) VALUES(";
			query += "WHERE code = '" + country.getCode() + "'";
			query += country.getCode() + "," + country.getIso3() + "," + country.getName() + "," + country.getCapital() + "," + country.getContinent() + ",";
			query += country.getRegion() + "," + country.getSurface() + "," + country.getPopulation() + "," + country.getGovernment() + ",";
			query += country.getLatitude() + "," + country.getLongitude() + ")";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}

	public boolean delete(String code) {
		try (Connection con = super.getConnection()) {
			String query = "DELETE FROM country WHERE code ='" + code + "' ";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	public boolean updateCountry(String code, String land, String hoofdstad, String regio, int oppervlakte, int inwoners) {
		try (Connection con = super.getConnection()){
			String query = "UPDATE country SET name='" + land + "', capital='"+hoofdstad+"', region='"+regio+"', surfacearea='"+oppervlakte+"', population='"+inwoners+"' ";
			query += "WHERE code='"+ code + "'";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
			return true;			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}

}

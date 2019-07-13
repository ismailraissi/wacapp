package nl.hu.v1wac.wacapp.webservices;

import javax.annotation.security.RolesAllowed;	
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import nl.hu.v1wac.wacapp.persistence.Country;
import nl.hu.v1wac.wacapp.persistence.CountryDao;
import nl.hu.v1wac.wacapp.persistence.CountryPostgresDaoImpl;

@Path("/countries")
public class ServiceProvider {
	private static WorldService worldService = new WorldService();
	private static CountryDao dao = new CountryPostgresDaoImpl();

	public static WorldService getWorldService() {
		return worldService;
	}

	@GET
	@Produces("application/json")
	public String getCounties() {
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Country c : worldService.getAllCountries()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("code", c.getCode());
			job.add("name", c.getName());
			job.add("capital", c.getCapital());
			job.add("surface", c.getSurface());
			job.add("government", c.getGovernment());
			job.add("region", c.getRegion());
			job.add("population", c.getPopulation());
			job.add("lat", c.getLatitude());
			job.add("lng", c.getLongitude());

			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("/largestpopulations")
	@Produces("application/json")
	public String getCountiesLargesPopulation() {
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Country c : worldService.get10LargestPopulations()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("code", c.getCode());
			job.add("name", c.getName());
			job.add("capital", c.getCapital());
			job.add("surface", c.getSurface());
			job.add("government", c.getGovernment());
			job.add("lat", c.getLatitude());

			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("/largestsurfaces")
	@Produces("application/json")
	public String getCountiesLargesSurface() {
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Country c : worldService.get10LargestSurfaces()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("code", c.getCode());
			job.add("name", c.getName());
			job.add("capital", c.getCapital());
			job.add("surface", c.getSurface());
			job.add("government", c.getGovernment());
			job.add("lat", c.getLatitude());

			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("{code}")
	@Produces("application/json")
	public String getCounty(@PathParam("code") String code) {
		Country c = worldService.getCountryByCode(code);

		if (c == null) {
			throw new WebApplicationException("Geen land met deze code!");
		}

		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("code", c.getCode());
		job.add("name", c.getName());
		job.add("capital", c.getCapital());
		job.add("surface", c.getSurface());
		job.add("government", c.getGovernment());
		job.add("lat", c.getLatitude());

		return job.build().toString();
	}
	
	@DELETE
	@RolesAllowed("user")
	@Path("{code}")
	@Produces("application/json")
	public Response deleteCountry(@PathParam("code") String code) {
		if(worldService.delete(code)) {
			return Response.ok().build();
		}
		return Response.status(404).build();
	}
	
	@POST
	@RolesAllowed("user")
	@Path("/toevoegen")
	@Produces("application/json")
	public Response toevoegenCountry(@FormParam("code_in_toe") String codeLand,
									@FormParam("iso_in_toe") String iso,
									@FormParam("land_in_toe") String land,
									@FormParam("regering_in_toe") String regering,
									@FormParam("continent_in_toe") String continent,
									@FormParam("hoofdstad_in_toe") String hoofdstad,
									@FormParam("regio_in_toe") String regio,
									@FormParam("oppervlakte_in_toe") double oppervlakte,
									@FormParam("inwoners_in_toe") int inwoners) {
		Country newLand = new Country(codeLand, iso, land, hoofdstad, continent, regio, oppervlakte, inwoners, regering);
		if(dao.save(newLand)) {
			return Response.ok().build();
		} else { return Response.status(400).build();
		}
	}
	
	@PUT
	@RolesAllowed("user")
	@Path("{code}")
	@Produces("application/json")
	public Response updateCountry(@PathParam("code") String code,
								  @FormParam("land_in") String land,
								  @FormParam("hoofdstad_in") String hoofdstad,
								  @FormParam("regio_in") String regio,
								  @FormParam("oppervlakte_in") int oppervlakte,
								  @FormParam("inwoners_in") int inwoners) {
	if(worldService.updateCountry(code, land, hoofdstad, regio, oppervlakte, inwoners)) {
		return Response.ok().build();
	} else {
		return Response.status(400).build();
	}
	}
	
}


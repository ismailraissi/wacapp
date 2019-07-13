package nl.hu.v1wac.wacapp.persistence;

public interface UserDao {
	public String findRoleForUser(String name, String pass);
}

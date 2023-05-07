package edu.uptc.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.uptc.entity.Person;
import edu.uptc.entity.Roles;

public class TestInsertarPersona {
	
	private static EntityManager em;
	private static EntityManagerFactory emf;

	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory("properties");	
		em = emf.createEntityManager();		
		Person person = new Person();
		person.setNumberID(123);
		person.setName("PRUEBA");
		person.setRol(Roles.ADMIN);
		person.setLastName("PRUEBA");
		person.setPhoneNumber("PRUEBA");
		person.setUsername("PRUEBA");
		person.setPassword("PRUEBA");
		em.getTransaction().begin();
		em.persist(person);
		em.getTransaction().commit();
	}

}

package com.training.cg.model;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@SuppressWarnings("unused")
public class JPADemo {
	
	private static final EntityManagerFactory emFactoryObj;
	
	private static final String PERSISTENCE_UNIT_NAME = "JPADemo";
	
	static {
		emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}
	
	public static void main(String[] args) {
		
		
		EntityManager  entityMgr = getEntityManager();
		User u1 = new User("Anthony","letmepass");
		User u2 = new User("Bruce","iamgoingin");
		User u3 = new User("Charlie","dontstopme");
		entityMgr.getTransaction().begin();
		entityMgr.persist(u1);
		entityMgr.persist(u2);
		entityMgr.persist(u3);
		entityMgr.getTransaction().commit();
		//create(entityMgr);
		//delete(entityMgr);
		//retrieve(entityMgr);
		//update(entityMgr);
		
		//getFromJpql(entityMgr);
		
		//getFromNamedQuery(entityMgr);
		
		//Criteria API
		
		//getFromCriteria(entityMgr);
		entityMgr.close();
		
	}
	
	public static EntityManager getEntityManager() {
		return emFactoryObj.createEntityManager();
	}
	
	private static void create(EntityManager  entityMgr) {
		
		entityMgr.getTransaction().begin();
		Student stud1 = new Student();
		
		stud1.setFirstName("Mark21");
		
		stud1.setLastName("Dennis71");
		
		Person pers1 = new Person();
		
		pers1.setAge(21);
		
		pers1.setName("Mahesh21");
		
		pers1.setAddress("Mumbai");
		
		pers1.setPhone("123-456-7111");
		
		pers1.setEmail("abs@ksdd.com");
		
		validatePerson(pers1);
		
		entityMgr.persist(stud1);
		
		entityMgr.persist(pers1);
		
		entityMgr.getTransaction().commit();
		
		entityMgr.clear();
		
		
		System.out.println("Record Successfully inserted in database table");
		
		System.out.println("Table(s) created in database");
		entityMgr.close();
	}
	
	private static void delete(EntityManager  entityMgr) {
		entityMgr.getTransaction().begin();
		Integer key = Integer.valueOf(4);
		Student s = entityMgr.find(Student.class, key);
		entityMgr.remove(s);
		entityMgr.getTransaction().commit();
		System.out.println("Record successfully removed..");
		//entityMgr.close();
	}
	
	private static void update(EntityManager  entityMgr) {
		entityMgr.getTransaction().begin();
		Integer key = Integer.valueOf(2);
		Student s = entityMgr.find(Student.class, key);
		s.setLastName("Mennis");
		entityMgr.getTransaction().commit();
		System.out.println("Updated Successfully");
		entityMgr.close();
	}
	
	private static void retrieve(EntityManager  entityMgr) {
		entityMgr.getTransaction().begin();
		
		
		//Student stud = new Student();
		
		//stud.setId(2);
		//stud.setFirstName("Mark8");
		
		Integer key = Integer.valueOf(4);
		
		Student s = entityMgr.find(Student.class, key);
		
		if(s != null)
			System.out.println("Name of Student: "+s.getLastName());
		else
			System.out.println("No Record found!");
		entityMgr.getTransaction().commit();
		entityMgr.close();
	}
	
	private  static void validatePerson(Person pers1) {
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		
		Validator validator = factory.getValidator();
		Set <ConstraintViolation<Person>> violations =  validator.validate(pers1);
		
		for(ConstraintViolation<Person> violation :  violations) {
			System.out.println(violation.getMessage());
		}
	}
	
	private static void getFromCriteria(EntityManager  entityMgr) {
		CriteriaBuilder cb = entityMgr.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery = cb.createQuery();
		Root<Student> from =criteriaQuery.from(Student.class);
		//select all records
		System.out.println("Select all records");
		CriteriaQuery<Object> select =  criteriaQuery.select(from);
		TypedQuery<Object> typedQuery = entityMgr.createQuery(select);
		
		List<Object> studList =typedQuery.getResultList();
				
		for(Object stud: studList) {
			Student s = (Student)stud;
				System.out.println("User Name: "+s.getFirstName()+" "+s.getLastName());
		}		
	}
	
	private static void getFromNamedQuery(EntityManager  entityMgr) {
		Query query = entityMgr.createNamedQuery("find student by id");
		query.setParameter("id", 6);
		List<Student> list = (List<Student>)query.getResultList();
		for(Student stud: list) {
			System.out.println("User Name: "+stud.getFirstName()+" "+stud.getLastName());
		}
	}
	
	private static void getFromJpql(EntityManager  entityMgr) {
		
		String getData = "select s from Student s where s.lastName like 'D%'";
		Query query = entityMgr.createQuery(getData);
		
		List<Student> studList = (List<Student>)query.getResultList();
		
		for(Student s:studList) {
			System.out.println("First Name: "+s.getFirstName());
		}
	}

}

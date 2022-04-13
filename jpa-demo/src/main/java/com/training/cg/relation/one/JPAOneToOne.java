package com.training.cg.relation.one;

import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


public class JPAOneToOne {
	
	//	Creating EntityManagerFactory object and using it to
	//	create EntityManager objects through a public static method
	private static final EntityManagerFactory emFactoryObj;
	private static final String PERSISTENCE_UNIT_NAME = "JPADemo";
	static {
		emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}
	public static EntityManager entityMgr = emFactoryObj.createEntityManager();
	
	
	//	Main method for various operations
	public static void main(String[] args) {
//		createEmployee("Anthony",30000,"Analyst");
//		createDepartment("HR");
		
		entityMgr.getTransaction().begin();
		Department dept = new Department();
		dept.setDeptName("Marketing");
		entityMgr.persist(dept);
		Employee emp = new Employee();
		emp.setEmpName("Anthony");
		emp.setEmpSalary(50000);
		emp.setDesignation("Director");
		emp.setDept(dept);
		validateEmployee(emp);
		entityMgr.persist(emp);
		entityMgr.getTransaction().commit();
		
		System.out.println("Program Completed");
	}
	
	
	//	Method to create and persist an object of Employee class
	public static void createEmployee(String empName, int empSalary, String designation) {
		entityMgr.getTransaction().begin();
		Employee emp = new Employee();
		emp.setEmpName(empName);
		emp.setEmpSalary(empSalary);
		emp.setDesignation(designation);
		validateEmployee(emp);
		entityMgr.persist(emp);
		entityMgr.getTransaction().commit();
	}
	
	//	Method to create and persist an object of Department class
	public static void createDepartment(String deptName) {
		entityMgr.getTransaction().begin();
		Department dept = new Department();
		dept.setDeptName(deptName);
		entityMgr.persist(dept);
		entityMgr.getTransaction().commit();
	}
	
	private  static void validateEmployee(Employee emp) {
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		
		Validator validator = factory.getValidator();
		Set <ConstraintViolation<Employee>> violations =  validator.validate(emp);
		
		for(ConstraintViolation<Employee> violation :  violations) {
			System.out.println(violation.getMessage());
		}
	}
}


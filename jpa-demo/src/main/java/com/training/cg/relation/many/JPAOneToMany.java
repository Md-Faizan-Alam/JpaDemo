package com.training.cg.relation.many;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


public class JPAOneToMany {
	
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
		Department1 dept = new Department1();
		dept.setDeptName("Marketing");
		
		Employee1 emp1 = new Employee1();
		emp1.setEmpName("Anthony");
		emp1.setEmpSalary(50000);
		emp1.setDesignation("Director");
		
		Employee1 emp2 = new Employee1();
		emp2.setEmpName("Bruce");
		emp2.setEmpSalary(50000);
		emp2.setDesignation("Consultant");
		
		List<Employee1> empList = new ArrayList<>();
		empList.add(emp1);
		empList.add(emp2);		
		validateEmployee(emp1);
		validateEmployee(emp2);
		entityMgr.persist(emp1);
		entityMgr.persist(emp2);
		
		dept.setEmpList(empList);
		
		entityMgr.persist(dept);
		entityMgr.getTransaction().commit();
		
		System.out.println("Program Completed");
	}
	
	private  static void validateEmployee(Employee1 emp) {
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		
		Validator validator = factory.getValidator();
		Set <ConstraintViolation<Employee1>> violations =  validator.validate(emp);
		
		for(ConstraintViolation<Employee1> violation :  violations) {
			System.out.println(violation.getMessage());
		}
	}
}


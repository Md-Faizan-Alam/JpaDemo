package com.training.cg.relation.mo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="DEPARTMENT1")
public class Department2 {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="dept_id")
	private int deptID;
	@Column(name="dept_name")
	private String deptName;
	@OneToMany(targetEntity = Employee2.class)
	private List<Employee2> empList;
	
	public List<Employee2> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee2> empList) {
		this.empList = empList;
	}

	public Department2() {
	}
	
	public int getDeptID() {
		return deptID;
	}
	public void setDeptID(int deptID) {
		this.deptID = deptID;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
}

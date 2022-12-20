package com.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
//어떤 테이블과 맵핑시킬지
@Table(name="DEPT_A")
public class Department {
	// 테이블의 컬럼명과 동일해야 한다
	//@Id - 기본키임을 나타냅니다
	@Id
	//시퀀스 생성
	//@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int deptno;
	private String dname;
	private String loc;
	
	@OneToMany(mappedBy ="dept", cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Employee> employeeList = new ArrayList<Employee>();

}

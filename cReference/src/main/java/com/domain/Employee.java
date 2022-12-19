package com.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="EMP_A")
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer empno;
	
	private String ename;
	private String job;
	private Integer mgr;
	private Date hiredate;
	private Integer sal;
	private Integer comm;
	
	//****************
	@ManyToOne
	//@ManyToOne - 다른 Entity클래스와의 외래키 다대일(N:1)관계를 명시합니다. 
	@JoinColumn(name="deptno")
	//@JoinColumn - name에 명시한 category_id라는 컬럼명으로Category에 대한 외래키 설정이 됩니다
	private Department dept;

}

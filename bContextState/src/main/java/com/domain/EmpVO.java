package com.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
//*******
// 객체와 테이블 매핑 
@Entity
//@Entity가 붙은 클래스는 JPA가 관리하는 것으로, 엔티티라고 불림
@Table(name ="EMP_C")
//엔티티와 매핑할 테이블을 지정
public class EmpVO {
	@Id
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private Date hiredate;

	private int sal;
	private int comm;
	private int deptno;


}

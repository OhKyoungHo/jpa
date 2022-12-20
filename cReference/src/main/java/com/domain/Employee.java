package com.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	//@ManyToOne(optional = false, fetch = FetchType.EAGER)
	//optional을 false로 하면 inner join(둘다 있는 정보만), true로 하면 outer join(한쪽에 값이 없어도 출력)
	//fetch가 FetchType.EAGER인 경우 연관된 엔티티를 즉시 조회(즉시 로딩)(해당 경우는 가능하면 SQL 조인(OUTER JOIN)을 사용해서 한번에 조회)
	//fetch가 FetchType.LAZY인 경우 연관된 엔티티를 프록시로 조회(지연로딩)
	
	//@ManyToOne - 다른 Entity클래스와의 외래키 다대일(N:1)관계를 명시합니다. 
	@JoinColumn(name="deptno")
	//@JoinColumn - name에 명시한 category_id라는 컬럼명으로Category에 대한 외래키 설정이 됩니다
	private Department dept;

}

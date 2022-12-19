package com.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MainApp {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cReference");
		
		try {
			//[1] 연관관계를 이용한 데이터 검색
			selectData(emf);
			//[2] 연관관계를 이용한 데이터 입력
			insertData(emf);
		}catch(Exception ex){
			System.out.println("예외:" + ex.getMessage());
		}finally {
			emf.close();
		}

	}//end of main
	
	//[1] 연관관계를 이용한 데이터 검색
	static void selectData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		
		Employee e1 = em.find(Employee.class, 7788);
		System.out.println(e1);
		System.out.println(e1.getEname() + "님 정보");
		System.out.println("부서:" + e1.getDept().getDname());
		
		
		em.close();
	}//end of selectData
	
	static void insertData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		//커밋할것이기 때문에 EntityTransaction 필요
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Employee emp1 = new Employee();
		emp1.setEname("홍씨3");
		//Department dept = em.find(Department.class, 40);
		Department dept =  new Department();
		dept.setDeptno(50);
		dept.setDname("아이티");
		dept.setLoc("인천");
		//Department dept를 한번 커밋
		//영속컨텍스트에 저장
		em.persist(dept);
		emp1.setDept(dept);
		//Employee emp1을 커밋
		em.persist(emp1);
		
		tx.commit();
		em.close();
		
	}

}

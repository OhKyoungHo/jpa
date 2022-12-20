import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;



public class Main2Parameter {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dJpql");

		try {
			// board 테이블에서 1번 레코드를 화면에 출력
			selectData(emf);
			deleteData(emf);
			updateData(emf);

		}catch(Exception ex){
			System.out.println("예외:" + ex.getMessage());
		}finally {
			emf.close();
		}

	}//end of main
	
	static void updateData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		//JPQL
		String jpql ="UPDATE Employee e SET e.sal = 2000 WHERE e.empno =:emp_no";
		Query query = em.createQuery(jpql);
		query.setParameter("emp_no",7788 );
		//실행
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		int result = query.executeUpdate();
		System.out.println(result + "행을 수행");
		tx.commit();
		em.close();
	}

	static void deleteData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		//JPQL
		String jpql ="DELETE FROM Employee e WHERE e.empno=:emp_no";
		Query query = em.createQuery(jpql);
		query.setParameter("emp_no",7 );
		//실행
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		int result = query.executeUpdate();
		System.out.println(result + "행을 수행");
		tx.commit();
		em.close();
	
	}

	//[1] 연관관계를 이용한 데이터 검색
	static void selectData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		

		//[1] 파라메터 바인딩(위치인자)
		//		String jpql = "SELECT seq,writer,title FROM Board WHERE seq=?1 AND title=?2";
		//		Query query = em.createQuery(jpql);
		//		query.setParameter(1, 1);
		//		query.setParameter(2, "안녕");
		//		/*
		//		 Query 객체
		//		SELECT 절에서 여러 엔티티나 컬럼을 선택 시 반환 타입이 명확하지 않으므로 사용
		//		조회 대상의 갯수에 따라 반환 타입이 달라짐
		//		둘 이상 : Object[]
		//		하나 : Object*/
		//		//query로 하면 값들이 오브젝트 배열로 잡히기 때문에 오브젝트 배열을 제네틱 값으로 넣어서 값을 찾는다.
		//		List<Object[]> list = query.getResultList();	
		//		for(Object[] result : list) {
		//			// tostring 써야 값들이 출력된다.
		//			System.out.println(">>" + Arrays.toString(result));
		//		}

		//[2] 파라메터 바인딩(키워드인자)
		String jpql = "SELECT seq,writer,title FROM Board WHERE seq=:seqkw AND title=:titlekw";
		Query query = em.createQuery(jpql);
		query.setParameter("seqkw", 1);
		query.setParameter("titlekw", "안녕");
		/*
				 Query 객체
				SELECT 절에서 여러 엔티티나 컬럼을 선택 시 반환 타입이 명확하지 않으므로 사용
				조회 대상의 갯수에 따라 반환 타입이 달라짐
				둘 이상 : Object[]
				하나 : Object*/
		//query로 하면 값들이 오브젝트 배열로 잡히기 때문에 오브젝트 배열을 제네틱 값으로 넣어서 값을 찾는다.
		List<Object[]> list = query.getResultList();	
		for(Object[] result : list) {
			// tostring 써야 값들이 출력된다.
			System.out.println(">>" + Arrays.toString(result));
		}
		
		List<Object[]> list2 = query.getResultList();	
		for(Object[] result : list2) {
			// tostring 써야 값들이 출력된다.
			System.out.println(">>" + Arrays.toString(result));
		}
		
		if(list==list2) System.out.println("동일객체");
		else System.out.println("다른객체");

		em.close();
	}//end of selectData
	
	
	

}

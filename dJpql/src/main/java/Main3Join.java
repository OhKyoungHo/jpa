
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;



public class Main3Join {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dJpql");

		try {
			// board 테이블에서 1번 레코드를 화면에 출력
			selectData(emf);

		}catch(Exception ex){
			System.out.println("예외:" + ex.getMessage());
		}finally {
			emf.close();
		}

	}//end of main


	//[1] 연관관계를 이용한 데이터 검색
	static void selectData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();

		//JPQL
		//[1] 묵시적인 조인
		//String jpql = "SELECT e, e.dept FROM Employee e";
		//[2] 명시적인 조인
		String jpql = "SELECT e, d FROM Employee e INNER JOIN e.dept d";
		Query query = em.createQuery(jpql);
		//[3] 페이징 처리
		int pageNumber=3;
		int pageSize = 3;
		int startNum =  pageNumber * pageSize - pageSize;
		query.setFirstResult(startNum);
		query.setMaxResults(pageSize);
		
		
		List<Object[]> list = query.getResultList();	
		for(Object[] result : list) {
			// tostring 써야 값들이 출력된다.
			System.out.println(">>" + result[0]);
		}

		em.close();
	}//end of selectData

}

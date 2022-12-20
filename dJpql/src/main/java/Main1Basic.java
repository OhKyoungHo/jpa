import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;



public class Main1Basic {

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

		//Board b1 = em.find(Board.class, 1);
		//System.out.println(b1);

		//JPQL
		//[1] 검색결과를 특정할 수 있는 경우 : TypedQuery
//		String jpql = "SELECT b FROM Board AS b";
//		TypedQuery<Board> query = em.createQuery(jpql, Board.class);
//		List<Board> list = query.getResultList();	
//		for(Board result : list) {
//			System.out.println(">>" + result);
//		}

		//[2] 검색결과를 특정할 수 없는 경우 : Query
		String jpql = "SELECT seq,writer,title FROM Board";
		Query query = em.createQuery(jpql);
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

		em.close();
	}//end of selectData

}

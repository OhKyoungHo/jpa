import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.domain.EmpVO;

public class EmpDetachedMain {

	public static void main(String[] args) {
		//지속성이 보장되고 있지만 세션과 현재 분리된 상태
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bContextState");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			//persistence Context에 entity(테이블의 레코드)를 올려놓기
			//== 엔티티를 Managed 상태로 만들기
			EmpVO emp1 = em.find(EmpVO.class, 1299);
			System.out.println("검색결과:" + emp1);

			//엔티티 수정
			tx.begin();
			em.detach(emp1); //영속상태의 엔티티가 영속성 컨텍스트에서 분리된 것
			emp1.setEname("홍돌이");
			tx.commit();

			EmpVO emp2 = em.find(EmpVO.class, 1299);
			System.out.println("검색결과:" + emp2);


		}catch(Exception e){
			System.out.println("예외:" + e);
		}finally {
			em.close();
			emf.close();
		}
	}

}

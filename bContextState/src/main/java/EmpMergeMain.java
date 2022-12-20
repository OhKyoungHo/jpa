import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.domain.EmpVO;

public class EmpMergeMain {

	public static void main(String[] args) {
		//지속성이 보장되고 있지만 세션과 현재 분리된 상태
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bContextState");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			//persistence Context에 entity(테이블의 레코드)를 올려놓기
			//== 엔티티를 Managed 상태로 만들기
			EmpVO vo = new EmpVO();
			vo.setEmpno(1999);
			vo.setEname("맹맹이");
			tx.begin();
			// merge(): 준영속 상태의 엔티티를 받아서 그 정보로 새로운 영속 상태의 엔티티를 반환 
			// merge() 메서드를 호출할 때 넘겨준 엔티티는 여전히 detached 상태에 있습니다.
			// 그리고 merge() 메서드를 호출할 때 넘겨준 엔티티의 값을 새로운 엔티티의 값으로 채워 넣습니다.
			// merge를 쓰면 select를 해서 만약 값이 없는 경우는 insert, 있는 경우는 update
			em.merge(vo);
			tx.commit();


		}catch(Exception e){
			System.out.println("예외:" + e);
		}finally {
			em.close();
			emf.close();
		}

	}

}

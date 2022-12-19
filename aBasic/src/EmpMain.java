import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.domain.EmpVO;

public class EmpMain {

	public static void main(String[] args) {
		// 1. 엔티티매니저팩토리  생성(팩토리 패턴이다...)(영속성 유닛에서 지은 이름을 넣어줘야 한다)
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("aBasic");
		// 2. 엔티티매니저 
		EntityManager em = emf.createEntityManager();
		// 4. 엔티티트랜잭션 생성(insert, update, delete인 경우)
		EntityTransaction tx = em.getTransaction();
		
		try {
			// 3.  엔티티 생성
			EmpVO vo = new EmpVO();
			vo.setEmpno(9997);
			vo.setEname("오파이");
			vo.setHiredate(new Date());
			vo.setDeptno(99);
			//4. 트랜잭션 처리
			tx.begin();
			em.persist(vo);
			//성공시 커밋
			tx.commit();
		}catch(Exception ex) {
			System.out.println("실패:" + ex.getMessage());
			//실패시 롤백
			tx.rollback();
		}finally {
			em.close();
			emf.close();
		}
		

	}

}

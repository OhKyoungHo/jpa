import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.domain.EmpVO;

public class EmpSelectMain {

	public static void main(String[] args) {
		//지속성이 보장되고 있지만 세션과 현재 분리된 상태
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bContextState");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			//persistence Context에 entity(테이블의 레코드)를 올려놓기
			//== 엔티티를 Managed 상태로 만들기
			//[1] find()
			//가지고 오는데 없으면 null출력
			//데이터베이스를 통해서 실제 엔티티 객체 조회
			//find 할때 select 쿼리문이 나간다
			//EmpVO emp1 = em.find(EmpVO.class, 1297);
			//System.out.println("검색결과:" + emp1);

			//[2] getReference() 
			//가지고 오는데 없으면 예외발생
			//데이터베이스 조회를 미루는 가짜 엔티티 객체 조회
			//getReference할때 가 아니라 값이 사용될때 select 쿼리문이 나간다
			//EmpVO emp2 = em.getReference(EmpVO.class, 1298);
			//System.out.println("검색결과:" + emp2);
			
			//[3] JPQL(Java Persistence Query Language)
			// JPQL은 엔티티 객체를 대상으로 쿼리를 질의(테이블이 아닌 객체를 대상으로 검색하는 객체지향 쿼리)
			// createQuery()
			// =>테이블명이 아니라 엔티티 명임(대소문자 구별)
			//SQL문장이 아니라  JPQL이다
			String jpql="SELECT e FROM EmpVO e ORDER BY e.empno DESC";
			List<EmpVO> list =  em.createQuery(jpql, EmpVO.class).getResultList();
			for(EmpVO vo: list) {
				System.out.println(">>>>" + vo);
			}
		}catch(Exception e){
			System.out.println("예외:" + e);
		}finally {
			em.close();
			emf.close();
		}

	}

}

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.domain.EmpVO;

public class EmpManagedMain {

	public static void main(String[] args) {
		// 1299 사번의 홍길숙님 정보를 입력하기
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bContextState");
		
		try {
			//연관관계를 이용한 데이터 입력
			insertData(emf);
		}catch(Exception ex){
			System.out.println("예외:" + ex.getMessage());
		}finally {
			emf.close();
		}

	}//end of main
	
	static void insertData(EntityManagerFactory emf) {
		//EntitiyManager 객체 생성
		EntityManager em = emf.createEntityManager();
		// 커밋할것이기 때문에 EntityTransaction 필요
		EntityTransaction tx = em.getTransaction();
		tx.begin();
//		EmpVO vo = new EmpVO();
//		vo.setEname("홍길동");
//		vo.setEmpno(1297);
//		//영속컨텍스트에 저장
//		em.persist(vo);
		//select 기능 설정(사번 1299로 찾기)
		//1297넣고 1297찾으면 영속컨텍스트 구역에서 insert만하고 select하지 않음
		//각각 다른 변수 이름으로 1297을 찾으면 같은 주소값을 보고 있기 때문에 한번만 작동한다.(한번만 받아와서 값을 준다.) 
		EmpVO emp1 = em.find(EmpVO.class,1297);
		System.out.println("검색결과:" + emp1);
		
		EmpVO emp2 = em.find(EmpVO.class,1298);
		System.out.println("검색결과:" + emp2);
		
		if(emp1 == emp2)System.out.println("동일객체");
		else System.out.println("다른객체");
		
		//EmpVO를 커밋
		tx.commit();
		em.close();
		
	}//end of insertData

}

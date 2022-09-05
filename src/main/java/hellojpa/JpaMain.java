package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        // EntityManagerFactory 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // EntityManager 생성
        EntityManager em = emf.createEntityManager();
        // EntityTransaction 생성
        EntityTransaction tx = em.getTransaction();
        // 트랜잭션 실행
        tx.begin();

        try {

            // 비영속 엔티티 객체 생성
            Member member = new Member();
//            member.setId("ID_A");
            member.setName("C");

            Member member2 = new Member();
//            member.setId("ID_A");
            member2.setName("D");

            // 영속성 컨텍스트 저장
            em.persist(member);
            em.persist(member2);

            // 트랜잭션 커밋(내부적으로 flush)
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            // EntityManager 닫기
            em.close();
            // EntityManagerFactory 닫기
            emf.close();
        }
    }

}

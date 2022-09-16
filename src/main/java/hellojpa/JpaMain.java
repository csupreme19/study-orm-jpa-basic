package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

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

            Member member = new Member();
            member.setUsername("test");
            member.setHomeAddress(new Address("city", "street", "zipcode"));
            member.setWorkAddress(new Address("work_city", "work_street", "work_zipcode"));
            member.setWorkPeriod(new Period(LocalDateTime.now(), LocalDateTime.now()));
            member.setWorkPeriod(null);

            em.persist(member);

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

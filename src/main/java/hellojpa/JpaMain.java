package hellojpa;

import org.hibernate.Hibernate;
import org.hibernate.jpa.internal.PersistenceUnitUtilImpl;

import javax.persistence.*;

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

            Member member1 = new Member();
            member1.setUsername("member1");

            em.persist(member1);

            em.flush();
            em.clear();

            Member reference1 = em.getReference(Member.class, member1.getId());
            System.out.println("reference1.getClass(): " + reference1.getClass());

            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(reference1));
            Hibernate.initialize(reference1);
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(reference1));

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

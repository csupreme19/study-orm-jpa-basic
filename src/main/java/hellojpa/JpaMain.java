package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
            // 엔티티 생성(비영속 상태)
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloA");

            // 엔티티 조회
//            Member findMember = em.find(Member.class, 1L);
            // 엔티티 수정
//            findMember.setName("HelloJPA");
//            System.out.printf("Member(%d, %s)\n", findMember.getId(), findMember.getName());
            // 엔티티 삭제(삭제 상태)
//            em.remove(findMember);

            // JPQL(대상이 테이블이 아니라 객체)
            // 객체지향적인 쿼리라고 생각할 수 있음
            List<Member> resultList = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)  // offset (pagination)
                    .setMaxResults(8)   // limit (pagination)
                    .getResultList();

            for(Member m : resultList) {
                System.out.printf("Member(%d, %s)\n", m.getId(), m.getName());
            }

            // 영속성 컨텍스트에 엔티티 저장(영속 상태)
            System.out.println("=== BEFORE ===");
            em.persist(member);
            System.out.println("=== AFTER ===");

            // 엔티티 영속성 컨텍스트에서 분리(준영속 상태)
            em.detach(member);

            // 트랜잭션 커밋(DB에 반영)
            // 이 시점에 실제 영속성 컨텍스트에 저장된 객체 쿼리가 DB에 날라간다.
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

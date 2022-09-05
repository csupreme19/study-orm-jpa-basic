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
            // 엔티티 생성
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("HelloA");

            // 엔티티 조회
//            Member findMember = em.find(Member.class, 1L);
            // 엔티티 수정
//            findMember.setName("HelloJPA");
//            System.out.printf("Member(%d, %s)\n", findMember.getId(), findMember.getName());
            // 엔티티 삭제
//            em.remove(findMember);

            // JPQL(대상이 테이블이 아니라 객체)
            // 객체지향적인 쿼리라고 생각할 수 있음
            List<Member> resultList = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)  // offset (pagination)
                    .setMaxResults(8)   // limit (pagination)
                    .getResultList();

            for(Member member : resultList) {
                System.out.printf("Member(%d, %s)\n", member.getId(), member.getName());
            }

            // 엔티티 매니저에 엔티티 영속
//            em.persist(member);
            // 트랜잭션 커밋(DB에 반영)
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

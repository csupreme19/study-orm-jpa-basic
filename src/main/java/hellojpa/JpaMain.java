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
//            Member member = new Member();
//            member.setId(3L);
//            member.setName("HelloB");

            // 엔티티 조회
//            Member findMember = em.find(Member.class, 1L);
            // 엔티티 수정
//            findMember.setName("HelloJPA");
//            System.out.printf("Member(%d, %s)\n", findMember.getId(), findMember.getName());
            // 엔티티 삭제(삭제 상태)
//            em.remove(findMember);

            // JPQL(대상이 테이블이 아니라 객체)
            // 객체지향적인 쿼리라고 생각할 수 있음
//            List<Member> resultList = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5)  // offset (pagination)
//                    .setMaxResults(8)   // limit (pagination)
//                    .getResultList();

//            for(Member m : resultList) {
//                System.out.printf("Member(%d, %s)\n", m.getId(), m.getName());
//            }

            // 영속성 컨텍스트에 엔티티 저장(영속 상태)
//            System.out.println("=== BEFORE ===");
//            em.persist(member);

            // Select 쿼리가 한번만 실행됨(1차 캐시)
//            Member member2 = em.find(Member.class, 1L);
            // 이후 같은 객체 조회시 1차 캐시에서 가져오므로 쿼리 실행 X
//            em.clear();
//            Member member3 = em.find(Member.class, 1L);
            // 1차 캐시에는 항상 같은 객체가 존재하므로 엔티티의 동일성 보장
//            System.out.println("result = " + (member2 == member3));

//            System.out.printf("Member(%d, %s)\n", member2.getId(), member2.getName());
//            em.createQuery("select m from Member as m where m.name = 'helloB'", Member.class);
//            System.out.println("=== AFTER ===");

            // 엔티티 영속성 컨텍스트에서 분리(준영속 상태)
//            em.detach(member);

//            Member member = em.find(Member.class, 150L);
            // 수정 작업은 persist가 필요 없음(더티 체킹)
//            member.setName("ZZZZ");
//            em.persist(member); // 업데이트 수행시에는 필요하지 않다!

//            Member member4 = new Member(150L, "A");
//            Member member5 = new Member(160L, "B");
//            em.persist(member4);
//            em.persist(member5);

//            Member member = new Member(210L, "member210");
//            em.persist(member);

            // flush 호출시 commit 수행 이전에 DB에 반영
//            em.flush();
//            em.detach(member);
//            member.setName("hi");

            // JPQL 호출시 flush
//            em.createQuery("select m from Member as m", Member.class)
//                    .getResultList();

            // 트랜잭션 커밋(DB에 반영)
            // 이 시점에 실제 영속성 컨텍스트에 저장된 객체 쿼리가 DB에 날라간다.
//            System.out.println("===BEFORE COMMIT===");
            tx.commit();
//            System.out.println("===AFTER COMMIT===");
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

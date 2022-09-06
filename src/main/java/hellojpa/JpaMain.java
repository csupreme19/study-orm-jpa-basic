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
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            // 양방향 매핑의 경우 양쪽에 데이터를 넣어주는 것이 좋다.
            // 연관관계 편의 메소드로 대체
//            team.getMembers().add(member);

//            member.changeTeam(team);
            team.addMember(member);

//            em.flush();
//            em.clear();

            Team team1 = em.find(Team.class, team.getId()); // 1차 캐시
            List<Member> members = team1.getMembers();

            for(Member m : members) {
                System.out.println("m=" +  m.getUsername());
            }

            // 트랜잭션 커밋(내부적으로 flush)
            System.out.println("=====BEFORE COMMIT===");
            tx.commit();
            System.out.println("=====AFTER COMMIT===");
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

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
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
//            member.setTeamId(team.getId());
            member.setTeam(team);
            em.persist(member);

//            em.flush();
//            em.clear();
            Member findMember = em.find(Member.class, member.getId());

            // 테이블 관점 모델링
//            Long findTeamId = findMember.getTeamId();
//            Team findTeam = em.find(Team.class, findTeamId);

            // 객체 관점 모델링
            Team findTeam = findMember.getTeam();
            findTeam.setName("TeamB");

            System.out.println("team("  + findTeam.getId() + ", " + findTeam.getName() + ")");

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

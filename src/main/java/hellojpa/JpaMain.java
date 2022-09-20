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

            Address address = new Address("homeCity", "street", "zipcode");

            Member member = new Member();
            member.setUsername("member1");
//            member.setHomeAddress(address);

//            member.getFavoriteFoods().add("치킨");
//            member.getFavoriteFoods().add("피자");
//            member.getFavoriteFoods().add("족발");
            member.getAddressHistroy().add(new AddressEntity("old1", "street", "zipcode"));
//            member.getAddressHistroy().add(new AddressEntity("old2", "street", "zipcode"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("========== START ==========");
            Member findMember = em.find(Member.class, member.getId());
            // 지연 로딩
//            findMember.getAddressHistroy().forEach(addr -> System.out.println("address = " + addr.getCity()));
            // 지연 로딩
//            findMember.getFavoriteFoods().forEach(i -> System.out.println("foods = " + i));

            // 불변 객체로 설정하여 값을 바꿀 때 값을 바꾸는 것이 아닌 새로운 객체를 넣어줘야함
//            findMember.getHomeAddress().setCity("newCity");
//            Address a = new Address("newCity", "street", "zipcode");
//            findMember.setHomeAddress(a);

            // 불변 객체가 아니더라도 값을 바꾸지 않고 새로운 객체를 넣어줘야함
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("한식");

            // java collection은 equals 비교를 통해 객체를 찾기 때문에 주의
//            findMember.getAddressHistroy().remove(new AddressEntity("old1", "street", "zipcode"));
//            findMember.getAddressHistroy().add(new AddressEntity("newCity1", "street", "zipcode"));

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

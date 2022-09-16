package hellojpa;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "MEMBER_SEQ"
        , sequenceName = "MEMBER_SEQ"
        ,allocationSize = 50
        ,initialValue = 1)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "MEMBER_SEQ")
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Embedded
    private Period workPeriod;

    @Embedded
    private Address homeAddress;

    // 같은 임베디드 값 타입 사용시 중복 컬럼 매핑으로 오버라이딩 필요
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city",
                    column=@Column(name = "WORK_CITY")),
            @AttributeOverride(name="street",
                    column=@Column(name = "WORK_STREET")),
            @AttributeOverride(name="zipcode",
                    column=@Column(name = "WORK_ZIPCODE"))
    })
    private Address workAddress;

    public Address getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(Address workAddress) {
        this.workAddress = workAddress;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}

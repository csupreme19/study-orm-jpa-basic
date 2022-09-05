package hellojpa;

import javax.persistence.*;

@Entity
@Table(name = "Member")
@TableGenerator(name = "MEMBER_SEQ_GENERATOR"
        , table = "MY_SEQUENCES"
        , allocationSize = 1)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE
            , generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

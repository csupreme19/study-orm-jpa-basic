package hellojpa;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESS_ENTITY")
public class AddressEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String city;
    private String street;
    private String zipcode;

    @ManyToOne
    @JoinColumn(name = "ADDRESS_ENTITY_ID")
    private Member member;

    public AddressEntity() {
    }

    public AddressEntity(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}

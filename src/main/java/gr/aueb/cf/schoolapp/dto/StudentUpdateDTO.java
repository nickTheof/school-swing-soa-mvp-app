package gr.aueb.cf.schoolapp.dto;

public class StudentUpdateDTO extends BasePersonDTO {
    private Long id;

    public StudentUpdateDTO() {

    }

    public StudentUpdateDTO(Long id, String firstname, String lastname, String vat, String fatherName, String phoneNum, String email, String street, String streetNum, Integer cityId, String zipcode) {
        super(firstname, lastname, vat, fatherName, phoneNum, email, street, streetNum, cityId, zipcode);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

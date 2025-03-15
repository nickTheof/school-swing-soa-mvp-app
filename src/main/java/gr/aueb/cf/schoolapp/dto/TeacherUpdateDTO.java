package gr.aueb.cf.schoolapp.dto;

public class TeacherUpdateDTO extends BasePersonDTO {
    private Long id;

    public TeacherUpdateDTO() {

    }

    public TeacherUpdateDTO(Long id, String firstname, String lastname, String vat, String fatherName, String phoneNum, String email, String street, String streetNum, Integer cityId, String zipcode) {
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

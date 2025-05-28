package ArogyaSetuPlus.app.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CitizenRegistrationInputs {

    private String fullName;
    private String phoneNumber;
    private String gender;
    private String email;
    private String chiiNumber; // Citizen Health Insurance Identity Number
    private LocalDate Dob;
    private String address;
    private String planCode;
    
    

}

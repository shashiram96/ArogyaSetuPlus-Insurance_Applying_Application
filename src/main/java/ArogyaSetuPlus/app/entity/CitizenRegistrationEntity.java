package ArogyaSetuPlus.app.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Citizen_Registration")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitizenRegistrationEntity {
    @Id
    @Column(name = "application_Id")
    @SequenceGenerator(name = "gen01", sequenceName = "appIdGen01", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "gen01", strategy = GenerationType.SEQUENCE)
    private Integer appId;
    @Column(name = "Full_Name", length = 50)
    private String fullName;
    @Column(name = "phone_Number", length = 10)
    private String phoneNumber;
    @Column(name = "Gender", length = 6)
    private String gender;
    @Column(name = "email_Id", length = 50)
    private String email;
    @Column(name = "CHIINumber", length = 15)
    private String chiiNumber; // Citizen Health Insurance Identity Number
    @Column(name = "Address", length = 50)
    private String address;
    @Column(name = "DateofBirth")
    private LocalDate Dob;
    @Column(name="planCode")
    private String planCode;
    @Column(name = "state_Name", length = 20)
    private String StateName;
    @Column(name = "created_By", length = 30)
    private String createdBy;
    @Column(name = "updated_By", length = 30)
    private String updatedBy;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdOn;
    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updatedOn;

}

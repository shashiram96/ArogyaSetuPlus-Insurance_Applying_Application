package ArogyaSetuPlus.app.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "DC_CHILDREN")
@Data
public class DcChildrenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer childrenId;

    private Integer appId;  // Foreign key reference

    @Column(length = 30, nullable = false)
    private String childName;

    private LocalDate dateofBirth;

    private String childCHII;  
}

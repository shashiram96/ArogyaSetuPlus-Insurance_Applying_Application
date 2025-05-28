package ArogyaSetuPlus.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="Co_TRIGGERS")
public class CoTriggersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer triggerId;
    private Integer appId;
    @Lob	
    private byte[] coNoticePdf;
    @Column(length=30)
    private String triggerStatus="pending";
    
    
}

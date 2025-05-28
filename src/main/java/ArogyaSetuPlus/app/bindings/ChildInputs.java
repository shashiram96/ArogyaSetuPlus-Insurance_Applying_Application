package ArogyaSetuPlus.app.bindings;

import java.time.LocalDate;

import lombok.Data;
@Data
public class ChildInputs {
    private Integer appId;
    private String childName;
    private LocalDate dateofBirth;
    private String childCHII;
}

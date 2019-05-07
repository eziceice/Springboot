package com.example.springboot.core.mongodb.mongotemplate.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ValidatorPojo {

    @NotNull(message = "id can't be null")
    private Long id;

    @Future(message = "Need a date on future") //only valid for future date
    //@Past - only valid for past date
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date date;

    @NotNull
    @DecimalMin(value = "0.1")
    @DecimalMax(value = "10000.00")
    private Double value;

    @Min(value = 1, message = "Minimum 1")
    @Max(value = 88, message = "Maximum 88")
    @NotNull
    private Integer integer;

    @Range(min = 1, max = 888, message = "range from 1 to 888")
    private Long range;

    @Email
    private String email;

    @Size(min = 20, max = 30, message = "size from 20 to 30")
    private String size;

}

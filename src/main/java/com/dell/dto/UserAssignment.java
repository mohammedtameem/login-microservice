package com.dell.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;



@Data
public class UserAssignment implements Serializable {
    
    private String emailId;
    private String assisgnment;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date assesment_date;
    private String type;

}
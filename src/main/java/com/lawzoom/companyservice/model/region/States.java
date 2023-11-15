package com.lawzoom.companyservice.model.region;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table
public class States {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String stateName;

    @Column
    private boolean isEnable;

}

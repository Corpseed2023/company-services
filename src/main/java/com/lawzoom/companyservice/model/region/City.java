package com.lawzoom.companyservice.model.region;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "states_id")
    private States states;

    @Column
    private String cityName;

    @Column
    private String cityCode;

    @Column
    private boolean isEnable;


}

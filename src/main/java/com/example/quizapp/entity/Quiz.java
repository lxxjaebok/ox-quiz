package com.example.quizapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.type.descriptor.jdbc.TinyIntJdbcType;

@Data
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "quiz", length = 40, nullable = false)
    private String quiz;

    @Column(name="answer", columnDefinition = "TINYINT", length=1)
    private Boolean answer;

    private String user;
}

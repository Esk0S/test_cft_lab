package ru.cft.test_cft.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class IntervalLetter {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "start_interval")
    private String start;
    @Column(name = "end_interval")
    private String end;

    public IntervalLetter(String startInterval, String endInterval) {
        this.start = startInterval;
        this.end = endInterval;
    }
}

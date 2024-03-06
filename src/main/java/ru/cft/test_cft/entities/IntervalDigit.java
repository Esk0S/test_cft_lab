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
public class IntervalDigit {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "start_interval")
    private Integer start;
    @Column(name = "end_interval")
    private Integer end;

    public IntervalDigit(Integer startInterval, Integer endInterval) {
        this.start = startInterval;
        this.end = endInterval;
    }
}

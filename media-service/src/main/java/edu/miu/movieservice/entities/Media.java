package edu.miu.movieservice.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@DiscriminatorColumn(name = "mediaType", discriminatorType = DiscriminatorType.STRING)
@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int releasedYear;
    private String name;
    @Column(insertable = false, updatable = false)
    private String mediaType;
    private String genre;
    private String director;
    private String actor;
    private double avgRating;
    private double duration;

    @Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Basic(optional = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date postedDate;
}

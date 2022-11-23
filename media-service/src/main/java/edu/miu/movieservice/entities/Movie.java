package edu.miu.movieservice.entities;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("movie")
@Data
public class Movie extends Media {
    private Long grossIncome;
}

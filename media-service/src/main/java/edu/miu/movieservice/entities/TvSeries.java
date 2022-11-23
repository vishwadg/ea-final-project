package edu.miu.movieservice.entities;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("tvseries")
@Data
public class TvSeries extends Media{
    private int seasons;
}

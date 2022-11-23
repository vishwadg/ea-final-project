package edu.miu.movieservice.services.specification;

import edu.miu.movieservice.entities.Media_;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MediaSpecification {

    public static Specification mediaHasReleasedYear(int year) {
        return new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(Media_.releasedYear), year);
            }
        };
    }

    public static Specification mediaHasRating(double avgRating) {
        return new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(Media_.avgRating), avgRating);
            }
        };
    }

    public static Specification mediaHasGenre(String genre) {
        return new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get(Media_.genre), "%" + genre + "%");
            }
        };
    }

    public static Specification mediaHasDirector(String director) {
        return new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get(Media_.director), "%" + director + "%");
            }
        };
    }

    public static Specification mediaHasActor(String actor) {
        return new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get(Media_.actor), "%" + actor + "%");
            }
        };
    }

    public static Specification mediaHasDuration(double duration) {
        return new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(Media_.duration), duration);
            }
        };
    }
}

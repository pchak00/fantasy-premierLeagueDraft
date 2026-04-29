package com.pl.prem_league_data.Repository;

import com.pl.prem_league_data.Entity.Player;
import com.pl.prem_league_data.Entity.Position;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class PlayerSpecification {
    public static Specification<Player> byName(String name) {
        return (root, query, criteriaBuilder) ->  {
            if (name == null || name.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }
    public static Specification<Player> byPosition(Position position) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (position == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("position"), position);
        };
    }
    public static Specification<Player> byMinPrice(BigDecimal minPrice) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (minPrice == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
        };
    }
    public static Specification<Player> byMaxPrice(BigDecimal maxPrice) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (maxPrice == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
        };
    }
    public static Specification<Player> byMinTotalPoints(Integer minTotalPoints) {

        return (root, criteriaQuery, criteriaBuilder) -> {
            if (minTotalPoints == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("totalPoints"), minTotalPoints);
        };
    }
}

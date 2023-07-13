package bg.softuni.funfit.repository;

import bg.softuni.funfit.model.Workout;
import bg.softuni.funfit.model.dto.SearchWorkoutDTO;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class WorkoutSpecification implements Specification<Workout> {

    private final SearchWorkoutDTO searchWorkoutDTO;

    public WorkoutSpecification(SearchWorkoutDTO searchWorkoutDTO) {
        this.searchWorkoutDTO = searchWorkoutDTO;
    }

    //TODO fix workout level to work and check search by name to work properly also fix invalid data input
    @Override
    public Predicate toPredicate(Root<Workout> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate p = cb.conjunction();

        if(searchWorkoutDTO.getName().isEmpty()){
            p.getExpressions().add(
                    cb.and(cb.equal(root.get("name"),searchWorkoutDTO.getName()))
            );
        }
        if(searchWorkoutDTO.getDaysPerWeek() != null){
            p.getExpressions().add(
                    cb.and(cb.greaterThanOrEqualTo(root.get("daysPerWeek"), searchWorkoutDTO.getDaysPerWeek()))
            );
        }
        if(searchWorkoutDTO.getWorkoutLevel() != null){
            p.getExpressions().add(
                    cb.and(cb.equal(root.join("workoutLevel"),searchWorkoutDTO.getWorkoutLevel()))
            );
        }

        return p;
    }
}

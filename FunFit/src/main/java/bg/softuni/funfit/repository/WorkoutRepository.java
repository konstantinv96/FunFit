package bg.softuni.funfit.repository;

import bg.softuni.funfit.model.Workout;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout,Long>,
        JpaSpecificationExecutor<Workout> {

}

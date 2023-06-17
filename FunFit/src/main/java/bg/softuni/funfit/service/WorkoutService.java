package bg.softuni.funfit.service;

import bg.softuni.funfit.model.views.WorkoutIndexView;
import bg.softuni.funfit.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutService {
    private WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public List<WorkoutIndexView> getAllWorkouts(){
        //TODO Use model mapper !
       return  workoutRepository.findAll().stream().map(workout -> new WorkoutIndexView(
                workout.getId(),
                 workout.getName(),
                 workout.getLevel(),
                 workout.getMainGoal(),
                 workout.getDuration(),
                 workout.getDaysPerWeek(),
                 workout.getPictureURL()
        )).collect(Collectors.toList());
    }
}

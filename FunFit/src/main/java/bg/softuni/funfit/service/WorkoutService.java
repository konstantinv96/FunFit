package bg.softuni.funfit.service;

import bg.softuni.funfit.exceptions.WorkoutNotFoundException;
import bg.softuni.funfit.model.User;
import bg.softuni.funfit.model.Workout;
import bg.softuni.funfit.model.dto.AddWorkoutDTO;
import bg.softuni.funfit.model.dto.SearchWorkoutDTO;
import bg.softuni.funfit.model.enums.UserRoles;
import bg.softuni.funfit.model.views.WorkoutDetailsView;
import bg.softuni.funfit.model.views.WorkoutIndexView;
import bg.softuni.funfit.repository.UserRepository;
import bg.softuni.funfit.repository.WorkoutRepository;
import bg.softuni.funfit.repository.WorkoutSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkoutService {
    private WorkoutRepository workoutRepository;
    private UserRepository userRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository, UserRepository userRepository) {
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
    }

    public void addWorkout(AddWorkoutDTO addWorkoutDTO, UserDetails userDetails){
        User workoutAuthor = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();

        //TODO: fix it
        Workout workout = new Workout(addWorkoutDTO.getName(),
                addWorkoutDTO.getMainGoal(),
                addWorkoutDTO.getSessionTime(),
                addWorkoutDTO.getLevel(),
                addWorkoutDTO.getDuration(),
                addWorkoutDTO.getDaysPerWeek(),
                addWorkoutDTO.getDescription(),
                addWorkoutDTO.getImageURL());
        workout.setAuthor(workoutAuthor);

        workoutRepository.save(workout);
    }

    //TODO: User model mapper or extract the workout to addWorkoutDTO method , maybe change the addWorkoutDTO to addOrEditDTO

//    public Optional<AddWorkoutDTO> getWorkoutEditDetails(Long workoutId){
//        return workoutRepository.findById(workoutId).map(add);
//    }

    public void deleteWorkoutById(Long workoutId){
        workoutRepository.deleteById(workoutId);
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

    public WorkoutDetailsView getWorkout(Long id){
        return workoutRepository.findById(id).map(workout -> new WorkoutDetailsView(
           workout.getId(),workout.getName(), workout.getMainGoal(),
                workout.getSessionTime(),workout.getLevel().name(),
                workout.getDuration(),workout.getDaysPerWeek(),
                workout.getDescription(),workout.getPictureURL(),
                workout.getVideoURL(),workout.getAuthor().getFullName()
        )).orElseThrow(WorkoutNotFoundException::new);
    }

    //TODO should check this method!
    public List<WorkoutIndexView> searchWorkout(SearchWorkoutDTO searchWorkoutDTO){

        return this.workoutRepository.findAll(new WorkoutSpecification(searchWorkoutDTO)).stream()
                .map(WorkoutService::getWorkoutIndexView).collect(Collectors.toList());
    }

    private static WorkoutIndexView getWorkoutIndexView(Workout workout){
        return new WorkoutIndexView(workout.getId(),
                workout.getName(),
                workout.getLevel(),
                workout.getMainGoal(),
                workout.getDuration(),
                workout.getDaysPerWeek(),
                workout.getPictureURL());
    }

    public boolean isAuthor(String userName,Long workoutId){

        boolean isAuthor = workoutRepository.
                findById(workoutId).
                filter(w -> w.getAuthor().getUsername().equals(userName)).
                isPresent();

        if(isAuthor){
            return true;
        }

        return  userRepository.
                findByUsername(userName).
                filter(this::isAdmin).isPresent();

    }

    private boolean isAdmin(User user){
       return user.getRoles().stream().anyMatch(r -> r.getName() == UserRoles.ADMIN);
    }
}

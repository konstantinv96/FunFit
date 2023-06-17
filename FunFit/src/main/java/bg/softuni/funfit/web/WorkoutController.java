package bg.softuni.funfit.web;

import bg.softuni.funfit.model.views.WorkoutIndexView;
import bg.softuni.funfit.service.UserService;
import bg.softuni.funfit.service.WorkoutService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/workouts")
public class WorkoutController {
    private WorkoutService workoutService;
    private UserService userService;

    public WorkoutController(UserService userService, WorkoutService workoutService) {
        this.workoutService = workoutService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public String getAllWorkouts(Model model){

        List<WorkoutIndexView> workouts = workoutService.getAllWorkouts();
        model.addAttribute("workouts",workouts);

        return "workouts";
    }
}

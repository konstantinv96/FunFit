package bg.softuni.funfit.web;

import bg.softuni.funfit.model.dto.AddWorkoutDTO;
import bg.softuni.funfit.model.views.WorkoutDetailsView;
import bg.softuni.funfit.model.views.WorkoutIndexView;
import bg.softuni.funfit.service.UserService;
import bg.softuni.funfit.service.WorkoutService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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

    @GetMapping("/add")
    public String addWorkout(Model model){
            if(!model.containsAttribute("addWorkoutDTO")){
                model.addAttribute("addWorkoutDTO", new AddWorkoutDTO());
            }

        return "workout-add";
    }

    @PostMapping("/add")
    public String addWorkout(@Valid AddWorkoutDTO addWorkoutDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal UserDetails userDetails){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addWorkoutDTO",addWorkoutDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addWorkoutDTO",bindingResult);

            return "redirect:/workouts/add";
        }

        workoutService.addWorkout(addWorkoutDTO,userDetails);
        return "redirect:/";
    }



    @GetMapping("/all")
    public String getAllWorkouts(Model model){

        List<WorkoutIndexView> workouts = workoutService.getAllWorkouts();
        model.addAttribute("workouts",workouts);

        return "workouts";
    }

    @GetMapping("/details/{id}")
    public String getWorkout(@PathVariable("id") Long workoutId,Model model){
        WorkoutDetailsView workout = workoutService.getWorkout(workoutId);

        model.addAttribute("workout", workout);

        //TODO Create a comment section for each workout
        return "workout-details";
    }
}

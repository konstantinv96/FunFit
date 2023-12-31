package bg.softuni.funfit.web;

import bg.softuni.funfit.model.dto.AddWorkoutDTO;
import bg.softuni.funfit.model.dto.SearchWorkoutDTO;
import bg.softuni.funfit.model.views.WorkoutDetailsView;
import bg.softuni.funfit.model.views.WorkoutIndexView;
import bg.softuni.funfit.service.UserService;
import bg.softuni.funfit.service.WorkoutService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
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

    //TODO: add Model and put it in edit page
//    @GetMapping("{id}/edit")
//    public String edit(@PathVariable("id") Long workoutId){
//        workoutService.getWorkout(workoutId);
//    }

    @GetMapping("/details/{id}")
    public String getWorkout(@PathVariable("id") Long workoutId,Model model,Principal principal){
        WorkoutDetailsView workout = workoutService.getWorkout(workoutId);

        model.addAttribute("workout", workout);
        boolean isAuthorOrAdmin = workoutService.isAuthor(principal.getName(),workoutId);
        model.addAttribute("isAuthorOrAdmin", isAuthorOrAdmin);

        //TODO Create a comment section for each workout
        return "workout-details";
    }

    @PreAuthorize("@workoutService.isAuthor(#principal.name, #id)")
    @DeleteMapping("/details/{id}")
    public String deleteWorkout(
            Principal principal,
            @PathVariable("id") Long id,Model model){
        workoutService.deleteWorkoutById(id);

         return "redirect:/workouts/all";
    }


    @GetMapping("/search")
    public String searchQuery(@Valid SearchWorkoutDTO searchWorkoutDTO, BindingResult bindingResult
            ,RedirectAttributes redirectAttributes,Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("searchWorkoutModel", searchWorkoutDTO);
            model.addAttribute("org.springframework.validation.BindingResult.searchWorkoutModel", bindingResult);

            return "workout-search";
        }

        if(!model.containsAttribute("searchWorkoutModel")){
            model.addAttribute("searchWorkoutModel", searchWorkoutDTO);
        }

        if(!searchWorkoutDTO.isEmpty()){
            model.addAttribute("workouts", workoutService.searchWorkout(searchWorkoutDTO));
        }

        return "workout-search";

    }

}

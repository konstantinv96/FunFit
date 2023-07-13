package bg.softuni.funfit.model.dto;

import bg.softuni.funfit.model.enums.Level;

public class SearchWorkoutDTO {

    private String name;
    private Integer daysPerWeek;
    private Level workoutLevel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDaysPerWeek() {
        return daysPerWeek;
    }

    public void setDaysPerWeek(Integer daysPerWeek) {
        this.daysPerWeek = daysPerWeek;
    }

    public Level getWorkoutLevel() {
        return workoutLevel;
    }

    public void setWorkoutLevel(Level workoutLevel) {
        this.workoutLevel = workoutLevel;
    }

    @Override
    public String toString() {
        return "SearchWorkoutDTO{" +
                "name='" + name + '\'' +
                ", daysPerWeek=" + daysPerWeek +
                ", workoutLevel=" + workoutLevel +
                '}';
    }

    public boolean isEmpty(){
        return (name == null || name.isEmpty()) && daysPerWeek == null && workoutLevel == null;
    }
}

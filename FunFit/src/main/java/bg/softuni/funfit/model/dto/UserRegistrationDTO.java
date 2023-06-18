package bg.softuni.funfit.model.dto;


import bg.softuni.funfit.model.validation.FieldMatch;
import bg.softuni.funfit.model.validation.UniqueUserEmail;
import bg.softuni.funfit.model.validation.UniqueUserName;

import javax.persistence.*;
import javax.validation.constraints.*;
@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Passwords do not match."
)
public class UserRegistrationDTO {


        @NotBlank(message = "Username should be provided.")
        @Size(min = 5 , max = 20 , message = "Username must be between 5 and 20 characters")
        @UniqueUserName(message = "Username should be unique.")
        private String username;

        @NotBlank
        @Size(min = 5 , max = 30)
        private String fullName;

        @NotBlank(message = "User email should be provided.")
        @Email(message = "User email should be valid.")
        @UniqueUserEmail(message = "User email should be unique.")
        private String email;

        @Min(1)
        @Max(90)
        private int age;

        @NotBlank
        @Size(min = 5 , max = 20)
        private String password;

        @NotBlank
        @Size(min = 5 , max = 20)
        private String confirmPassword;

        public UserRegistrationDTO(){

        }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "UserRegistrationDTO{" +
                "username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}

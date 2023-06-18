package bg.softuni.funfit.model.validation;

import bg.softuni.funfit.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName,String> {

    private UserRepository userRepository;

    public UniqueUserNameValidator(UserRepository userRepository){

        this.userRepository = userRepository;
    }
    @Override
    public void initialize(UniqueUserName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findByUsername(value).isEmpty();
    }
}

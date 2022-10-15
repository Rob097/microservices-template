package com.myprojects.myportfolio.auth.security;

import com.myprojects.myportfolio.auth.dto.SignUPRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class    PasswordMatchesValidator implements ConstraintValidator<SignUPRequest.PasswordMatches, Object> {

        @Override
        public void initialize(SignUPRequest.PasswordMatches constraintAnnotation) {
        }
        @Override
        public boolean isValid(Object obj, ConstraintValidatorContext context){
            SignUPRequest user = (SignUPRequest) obj;
            return user.getPassword().equals(user.getMatchingPassword());
        }
}

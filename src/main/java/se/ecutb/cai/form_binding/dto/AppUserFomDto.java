package se.ecutb.cai.form_binding.dto;

import javax.validation.constraints.*;

import static se.ecutb.cai.form_binding.constants.RegexStorage.EMAIL_REGEX;
import static se.ecutb.cai.form_binding.constants.RegexStorage.PASSWORD_REGEX;
import static se.ecutb.cai.form_binding.constants.ValidationMessages.*;

public class AppUserFomDto {



    @NotBlank(message = "Field email is required!")
    @Email(regexp = EMAIL_REGEX, flags = Pattern.Flag.CASE_INSENSITIVE, message = EMAIL_FORMAT_MESSAGE)
    private String email;

    @NotBlank(message = "Field first name is required ")
    @Size(min = 2, max = 100, message = SIZE_ERROR_FIRST_NAME)
    private String firstName;

    @NotBlank(message = "Field last name is required")
    @Size(min = 2, max = 100, message = LAST_NAME_SIZE_ERROR)
    private String lastName;

    @NotBlank(message = "Field password is required")
    @Pattern(regexp = PASSWORD_REGEX, flags = Pattern.Flag.CASE_INSENSITIVE, message = "Password must contain at least one letter, at least one number, and be longer than six characters" )
    private String password;

    @NotBlank(message = "You need to confirm your password")
    private String passwordConfirm;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}

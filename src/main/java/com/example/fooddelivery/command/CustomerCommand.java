package com.example.fooddelivery.command;




import com.example.fooddelivery.enums.Role;
import com.example.fooddelivery.util.AssertValidation;
import com.example.fooddelivery.util.Validate;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CustomerCommand implements Validate {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Override
    public void validate() {
        AssertValidation.isEmpty(firstName);
        AssertValidation.isEmpty(lastName);
        AssertValidation.isEmail(email);
        AssertValidation.isPassword(password);
    }
}

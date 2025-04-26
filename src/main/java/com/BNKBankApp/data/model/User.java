package com.BNKBankApp.data.model;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="User")
@Data
public class User {

    @Id
    private String id;
    @Indexed(unique = true)
    @NotBlank(message = "This field is required")
    private String username;
    @NotBlank(message = "This field is required")
    @NotEmpty
    @Indexed(unique = true)
    @Email
    private String email;
    @NotBlank(message= "This field is required")
    @NotEmpty
    private String password;
    @NotBlank
    @NotEmpty
    private String fullName;
    @NotBlank(message="This field is required")
    private String phone;

}

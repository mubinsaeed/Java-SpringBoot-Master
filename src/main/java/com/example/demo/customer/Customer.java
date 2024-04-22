package com.example.demo.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Customer {

    @Id
    private Long id;

    @NotBlank(message = "name must be not empty")
    private String name;

    @NotBlank(message = "password must be not empty")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // This allows to send the password as body but not read it as we later use
    // for get function @JSON Ignore
    private String password;

    @NotBlank(message = "email must be not empty")
    @Email // all these are from starter validation no need to self implement parameter check in the api body
    // we can also use REGEX in the @Email(Regex = "") to have pattern detection
    private String email;

//Jackson serializable allows to send the data using the functions
    @JsonProperty("customer_id")  //if we dont use that it would return as {id:1,...} now it would return as {customer_id:1,...}
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @JsonIgnore  //This allows not to send the attribute data to the viewer/client
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

}

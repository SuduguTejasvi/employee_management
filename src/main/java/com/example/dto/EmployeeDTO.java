package com.example.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@ToString
public class EmployeeDTO {

    private Long employeeId;

    @NotNull(message = "First name is required")
    @Size(max = 50, message = "First name must be less than 50 characters")
    private String firstName;

    @NotNull(message = "Last name is required")
    @Size(max = 50, message = "Last name must be less than 50 characters")
    private String lastName;

    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Hire date is required")
    private Date hireDate;

    private String projectResult;

    @NotEmpty(message = "At least one project must be selected")
    private Set<Integer> projectIds;

    public EmployeeDTO() {
        this.projectIds = new HashSet<>();
    }

    public EmployeeDTO(Long employeeId, String firstName, String lastName, String email, Date hireDate, Set<Integer> projectIds) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hireDate = hireDate;
        this.projectIds = projectIds != null ? projectIds : new HashSet<>();
    }
}

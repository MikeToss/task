package com.task.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author Mikhail Terletskyi
 * @Create 10/26/2020
 * Implementation of {@link Serializable} interface.
 */

public class Employee implements Serializable {
    private static final long serialVersionUID = 3968944660015916386L;

    @NotNull
    private Long id;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotEmpty
    @NotNull
    @Email
    private String email;

    @NotEmpty
    @NotNull
    private BigDecimal salaryPerHour;

    @NotNull
    private LocalDate dateOfBirth;

    public Employee() {
    }

    public Employee(@NotNull @NotEmpty String firstName, @NotNull @NotEmpty String lastName,
                    @NotEmpty @NotNull @Email String email, @NotEmpty @NotNull BigDecimal salaryPerHour,
                    @NotNull LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salaryPerHour = salaryPerHour;
        this.dateOfBirth = dateOfBirth;
    }

    public Employee(@NotNull Long id, @NotNull @NotEmpty String firstName, @NotNull @NotEmpty String lastName,
                    @NotEmpty @NotNull @Email String email, @NotEmpty @NotNull BigDecimal salaryPerHour,
                    @NotNull LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salaryPerHour = salaryPerHour;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(BigDecimal salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return (int) ChronoUnit.YEARS.between(getDateOfBirth(), LocalDate.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        if (getId() != null ? !getId().equals(employee.getId()) : employee.getId() != null) return false;
        if (getFirstName() != null ? !getFirstName().equals(employee.getFirstName()) : employee.getFirstName() != null) return false;
        if (getLastName() != null ? !getLastName().equals(employee.getLastName()) : employee.getLastName() != null) return false;
        if (getEmail() != null ? !getEmail().equals(employee.getEmail()) : employee.getEmail() != null) return false;
        if (getSalaryPerHour() != null ? !getSalaryPerHour().equals(employee.getSalaryPerHour()) : employee.getSalaryPerHour() != null) return false;
        return getDateOfBirth() != null ? getDateOfBirth().equals(employee.getDateOfBirth()) : employee.getDateOfBirth() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getSalaryPerHour() != null ? getSalaryPerHour().hashCode() : 0);
        result = 31 * result + (getDateOfBirth() != null ? getDateOfBirth().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", salaryPerHour=" + salaryPerHour +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
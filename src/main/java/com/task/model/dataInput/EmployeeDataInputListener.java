package com.task.model.dataInput;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Mikhail Terletskyi
 * @Create 10/29/2020
 * Implementation of {@link Serializable} interface.
 */

public class EmployeeDataInputListener implements Serializable {
    private static final long serialVersionUID = 4834620955444895589L;

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String salaryPerHour;
    private String dateOfBirth;
    private boolean isValidationFailed;
    private String reasonOfFailedValidation;
    private String controllerOFListener;
    private LocalDate dateOfInput;
    private LocalTime timeOfInput;

    public EmployeeDataInputListener() {
    }

    public EmployeeDataInputListener(String firstName, String lastName, String email, String salaryPerHour,
                                     String dateOfBirth, boolean isValidationFailed, String reasonOfFailedValidation,
                                     String controllerOFListener, LocalDate dateOfInput, LocalTime timeOfInput) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salaryPerHour = salaryPerHour;
        this.dateOfBirth = dateOfBirth;
        this.isValidationFailed = isValidationFailed;
        this.reasonOfFailedValidation = reasonOfFailedValidation;
        this.controllerOFListener = controllerOFListener;
        this.dateOfInput = dateOfInput;
        this.timeOfInput = timeOfInput;
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

    public String getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(String salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isValidationFailed() {
        return isValidationFailed;
    }

    public void setValidationFailed(boolean validationFailed) {
        isValidationFailed = validationFailed;
    }

    public String getReasonOfFailedValidation() {
        return reasonOfFailedValidation;
    }

    public void setReasonOfFailedValidation(String reasonOfFailedValidation) {
        this.reasonOfFailedValidation = reasonOfFailedValidation;
    }

    public String getControllerOFListener() {
        return controllerOFListener;
    }

    public void setControllerOFListener(String controllerOFListener) {
        this.controllerOFListener = controllerOFListener;
    }

    public LocalDate getDateOfInput() {
        return dateOfInput;
    }

    public void setDateOfInput(LocalDate dateOfInput) {
        this.dateOfInput = dateOfInput;
    }

    public LocalTime getTimeOfInput() {
        return timeOfInput;
    }

    public void setTimeOfInput(LocalTime timeOfInput) {
        this.timeOfInput = timeOfInput;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeDataInputListener)) return false;

        EmployeeDataInputListener that = (EmployeeDataInputListener) o;

        if (isValidationFailed() != that.isValidationFailed()) return false;
        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getFirstName() != null ? !getFirstName().equals(that.getFirstName()) : that.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(that.getLastName()) : that.getLastName() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(that.getEmail()) : that.getEmail() != null) return false;
        if (getSalaryPerHour() != null ? !getSalaryPerHour().equals(that.getSalaryPerHour()) : that.getSalaryPerHour() != null)
            return false;
        if (getDateOfBirth() != null ? !getDateOfBirth().equals(that.getDateOfBirth()) : that.getDateOfBirth() != null)
            return false;
        if (getReasonOfFailedValidation() != null ? !getReasonOfFailedValidation().equals(that.getReasonOfFailedValidation()) : that.getReasonOfFailedValidation() != null)
            return false;
        if (getControllerOFListener() != null ? !getControllerOFListener().equals(that.getControllerOFListener()) : that.getControllerOFListener() != null)
            return false;
        if (getDateOfInput() != null ? !getDateOfInput().equals(that.getDateOfInput()) : that.getDateOfInput() != null)
            return false;
        return getTimeOfInput() != null ? getTimeOfInput().equals(that.getTimeOfInput()) : that.getTimeOfInput() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getSalaryPerHour() != null ? getSalaryPerHour().hashCode() : 0);
        result = 31 * result + (getDateOfBirth() != null ? getDateOfBirth().hashCode() : 0);
        result = 31 * result + (isValidationFailed() ? 1 : 0);
        result = 31 * result + (getReasonOfFailedValidation() != null ? getReasonOfFailedValidation().hashCode() : 0);
        result = 31 * result + (getControllerOFListener() != null ? getControllerOFListener().hashCode() : 0);
        result = 31 * result + (getDateOfInput() != null ? getDateOfInput().hashCode() : 0);
        result = 31 * result + (getTimeOfInput() != null ? getTimeOfInput().hashCode() : 0);
        return result;
    }
}
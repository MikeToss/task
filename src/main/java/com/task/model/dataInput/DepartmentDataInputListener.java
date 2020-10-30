package com.task.model.dataInput;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Mikhail Terletskyi
 * @Create 10/28/2020
 * Implementation of {@link Serializable} interface.
 */

public class DepartmentDataInputListener implements Serializable{
    private static final long serialVersionUID = 9157492716542685005L;

    private Long id;
    private String departmentTitle;
    private boolean isValidationFailed;
    private String reasonOfFailedValidation;
    private String controllerOFListener;
    private LocalDate dateOfInput;
    private LocalTime timeOfInput;

    public DepartmentDataInputListener() {
    }

    public DepartmentDataInputListener(String departmentTitle, boolean isValidationFailed,
                                       String reasonOfFailedValidation, String controllerOFListener,
                                       LocalDate dateOfInput, LocalTime timeOfInput) {
        this.departmentTitle = departmentTitle;
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

    public String getDepartmentTitle() {
        return departmentTitle;
    }

    public void setDepartmentTitle(String departmentTitle) {
        this.departmentTitle = departmentTitle;
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
        if (!(o instanceof DepartmentDataInputListener)) return false;

        DepartmentDataInputListener that = (DepartmentDataInputListener) o;

        if (isValidationFailed() != that.isValidationFailed()) return false;
        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getDepartmentTitle() != null ? !getDepartmentTitle().equals(that.getDepartmentTitle())
                : that.getDepartmentTitle() != null) return false;
        if (getReasonOfFailedValidation() != null
                ? !getReasonOfFailedValidation().equals(that.getReasonOfFailedValidation())
                : that.getReasonOfFailedValidation() != null) return false;
        if (getControllerOFListener() != null
                ? !getControllerOFListener().equals(that.getControllerOFListener())
                : that.getControllerOFListener() != null) return false;
        if (getDateOfInput() != null ? !getDateOfInput().equals(that.getDateOfInput())
                : that.getDateOfInput() != null) return false;
        return getTimeOfInput() != null ? getTimeOfInput().equals(that.getTimeOfInput()) : that.getTimeOfInput() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getDepartmentTitle() != null ? getDepartmentTitle().hashCode() : 0);
        result = 31 * result + (isValidationFailed() ? 1 : 0);
        result = 31 * result + (getReasonOfFailedValidation() != null ? getReasonOfFailedValidation().hashCode() : 0);
        result = 31 * result + (getControllerOFListener() != null ? getControllerOFListener().hashCode() : 0);
        result = 31 * result + (getDateOfInput() != null ? getDateOfInput().hashCode() : 0);
        result = 31 * result + (getTimeOfInput() != null ? getTimeOfInput().hashCode() : 0);
        return result;
    }
}

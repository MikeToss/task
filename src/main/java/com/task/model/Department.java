package com.task.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Mikhail Terletskyi
 * @Create 10/26/2020
 * Implementation of {@link Serializable} interface.
 */

public class Department implements Serializable {
    private static final long serialVersionUID = 4045259830246062209L;

    @NotEmpty
    private Long id;

    @NotNull
    private String title;

    private Set<Employee> departmentEmployees;

    public Department() {
    }

    public Department(@NotNull String title) {
        this.title = title;
    }

    public Department(@NotEmpty Long id, @NotNull String title) {
        this.id = id;
        this.title = title;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Employee> getDepartmentEmployees() {
        return departmentEmployees;
    }

    public void setDepartmentEmployees(Set<Employee> departmentEmployees) {
        this.departmentEmployees = departmentEmployees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;

        Department department = (Department) o;

        if (getId() != null ? !getId().equals(department.getId()) : department.getId() != null) return false;
        if (getTitle() != null ? !getTitle().equals(department.getTitle()) : department.getTitle() != null) return false;
        return getDepartmentEmployees() != null
                ? getDepartmentEmployees().equals(department.getDepartmentEmployees())
                : department.getDepartmentEmployees() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getDepartmentEmployees() != null ? getDepartmentEmployees().hashCode() : 0);
        return result;
    }
}
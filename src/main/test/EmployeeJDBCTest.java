import com.task.dao.jdbc.impl.EmployeeJDBCImpl;
import com.task.exeptions.DataProcessingException;
import com.task.model.Employee;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

import static com.task.dao.jdbc.impl.EmployeeJDBCImpl.EXCEPTION_COULD_NOT_GET_EMPLOYEE_BY_ID_FROM_DATABASE;
import static org.junit.Assert.*;

/**
 * @author Mikhail Terletskyi
 * @Create 10/26/2020
 * By default, JUnit runs tests using a deterministic, but unpredictable order. I solved this problem with {@link MethodSorters}
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeJDBCTest {

    private EmployeeJDBCImpl employeeJDBC = new EmployeeJDBCImpl();
    private static Long TEST_EMPLOYEE_ID;
    private static String TEST_EMPLOYEE_EMAIL = "brockelesnar@gmail.com";

    @Test
    public void A_createEmployeeTest() {

        // Here we will create a new test employee

        assertFalse(employeeJDBC.getAll().stream().anyMatch(employee -> TEST_EMPLOYEE_EMAIL.equals(employee.getEmail())));

        employeeJDBC.create(new Employee("Brock", "Lesnar",
                TEST_EMPLOYEE_EMAIL, BigDecimal.valueOf(123.0), LocalDate.of(1977, 7, 12)));

        Employee testEmployee = employeeJDBC.getAll().stream().filter(employee
                -> TEST_EMPLOYEE_EMAIL.equals(employee.getEmail())).findFirst().orElseThrow(()
                -> new DataProcessingException(EXCEPTION_COULD_NOT_GET_EMPLOYEE_BY_ID_FROM_DATABASE, new SQLException()));
        TEST_EMPLOYEE_ID = testEmployee.getId();

        assertTrue(employeeJDBC.getAll().stream().anyMatch(employee -> TEST_EMPLOYEE_EMAIL.equals(employee.getEmail())));
    }

    @Test
    public void B_getEmployeeByIdTest() throws SQLException {

        // We will get a created employee from  from test_task_db.employee which was create in A_createEmployeeTest()

        Employee testEmployeeById = employeeJDBC.getById(TEST_EMPLOYEE_ID)
                .orElseThrow(() -> new DataProcessingException(EXCEPTION_COULD_NOT_GET_EMPLOYEE_BY_ID_FROM_DATABASE,
                        new SQLException()));

        assertEquals("Brock", testEmployeeById.getFirstName());
        assertEquals("Lesnar", testEmployeeById.getLastName());
        assertEquals(TEST_EMPLOYEE_EMAIL, testEmployeeById.getEmail());
        assertEquals(BigDecimal.valueOf(123.0), testEmployeeById.getSalaryPerHour());
        assertEquals(LocalDate.of(1977, 7, 12), testEmployeeById.getDateOfBirth());
    }

    @Test
    public void E_deleteEmployeeTest() {
        assertTrue(employeeJDBC.getAll().stream().anyMatch(employee -> TEST_EMPLOYEE_EMAIL.equals(employee.getEmail())));

        employeeJDBC.delete(TEST_EMPLOYEE_ID);

        assertFalse(employeeJDBC.getAll().stream().anyMatch(employee -> TEST_EMPLOYEE_EMAIL.equals(employee.getEmail())));
    }
}
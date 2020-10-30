import com.task.dao.jdbc.DepartmentDAO;
import com.task.dao.jdbc.EmployeeDAO;
import com.task.dao.jdbc.impl.DepartmentJDBCImpl;
import com.task.dao.jdbc.impl.EmployeeJDBCImpl;
import com.task.exeptions.DataProcessingException;
import com.task.model.Department;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;

import static com.task.dao.jdbc.impl.DepartmentJDBCImpl.EXCEPTION_COULD_NOT_GET_DEPARTMENT_BY_ID_FROM_DATABASE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Mikhail Terletskyi
 * @Create 10/26/2020
 * By default, JUnit runs tests using a deterministic, but unpredictable order. I solved this problem with {@link MethodSorters}
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentJDBCTest {

    private DepartmentDAO departments = new DepartmentJDBCImpl();
    private static final String TITLE_OF_TEST_DEPARTMENT = "Oracle Cloud department";
    private static Long ID_OF_TEST_DEPARTMENT;

    /**
     * DEPARTMENT OPERATIONS BLOCK test
     */

    @Test
    public void A_createDepartmentTest() throws SQLException {
        int departments_amount_before_create_test = departments.getAll().size();
        departments.create(new Department(TITLE_OF_TEST_DEPARTMENT));
        int departments_amount_after_create_test = departments.getAll().size();
        assertEquals((1 + departments_amount_before_create_test), departments_amount_after_create_test);

        try {
            ID_OF_TEST_DEPARTMENT = departments.getAll().
                    stream().filter(department -> TITLE_OF_TEST_DEPARTMENT.equals(department.getTitle()))
                    .findFirst().orElseThrow(() ->
                            new DataProcessingException(EXCEPTION_COULD_NOT_GET_DEPARTMENT_BY_ID_FROM_DATABASE,
                                    new SQLException())).getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void B_getDepartmentByIdTest() throws SQLException {
        Department department = departments.getById(ID_OF_TEST_DEPARTMENT)
                .orElseThrow(() -> new DataProcessingException(EXCEPTION_COULD_NOT_GET_DEPARTMENT_BY_ID_FROM_DATABASE,
                        new SQLException()));

        assertEquals(department.getTitle(), TITLE_OF_TEST_DEPARTMENT);
    }

    @Test
    public void C_getAllDepartmentsTest() throws SQLException {

        // Pay attention we are get all 'starting' departments already database initialization + one new from A_createDepartmentTest()

        assertEquals(7, departments.getAll().size());
    }


    @Test
    public void D_deleteDepartmentTest() throws SQLException {

        // Here we can delete Department by id

        Long testDepartmentID = departments.getAll()
                .stream().filter(department -> TITLE_OF_TEST_DEPARTMENT.equals(department.getTitle()))
                .findFirst()
                .orElseThrow(() -> new DataProcessingException(EXCEPTION_COULD_NOT_GET_DEPARTMENT_BY_ID_FROM_DATABASE,
                        new SQLException())).getId();
        departments.delete(testDepartmentID);

        try {
            boolean isDeletedDepartmentPresent = departments.getAll().
                    stream().anyMatch(department -> TITLE_OF_TEST_DEPARTMENT.equals(department.getTitle()));
            assertFalse(isDeletedDepartmentPresent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
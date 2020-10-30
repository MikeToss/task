import com.task.core.Helper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * @author Mikhail Terletskyi
 * @Create 10/29/2020
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmailValidatorTest {

    @Test
    public void randomEmailsTest(){
        assertTrue(Helper.isEmail("mike@gmail.com"));
        assertTrue(Helper.isEmail("david123@mail.ru"));

        assertFalse(Helper.isEmail("13d14ae4d"));
        assertFalse(Helper.isEmail("dfsfdsds@dsgffdsf"));
        assertFalse(Helper.isEmail("12123fdsds@dsgffdddsf"));
        assertFalse(Helper.isEmail("@1212s@dsgffdddsf"));
    }
    @Test
    public void emailsFromDatabaseTest(){
        assertTrue(Helper.isEmail("laudespeed@gmail.com"));
        assertTrue(Helper.isEmail("tonicipriani@gmail.com"));
        assertTrue(Helper.isEmail("donaldlove1960@gmail.com"));
        assertTrue(Helper.isEmail("salvatoreleone@yahoo.com"));
        assertTrue(Helper.isEmail("kasenkenji@gmail.com"));
        assertTrue(Helper.isEmail("tommyvercetti@gmail.com"));
        assertTrue(Helper.isEmail("lancevance@gmail.com"));
        assertTrue(Helper.isEmail("kenrosenberg@mail.ru"));
        assertTrue(Helper.isEmail("sonnyforellig@gmail.com"));
        assertTrue(Helper.isEmail("ricardodiaz@yahoo.com"));
        assertTrue(Helper.isEmail("carljohnson1968@gmail.com"));
        assertTrue(Helper.isEmail("seanjohnson@yandex.ru"));
        assertTrue(Helper.isEmail("melvinharris@yahoo.com"));
        assertTrue(Helper.isEmail("lancewilson@gmail.com"));
        assertTrue(Helper.isEmail("franktenpenny@gmail.com"));
        assertTrue(Helper.isEmail("jeffreycross1973@mail.ru"));
        assertTrue(Helper.isEmail("cesarvialpando@gmail.com"));
        assertTrue(Helper.isEmail("nikobellic@yandex.ru"));
        assertTrue(Helper.isEmail("jonathanjohnny@yahoo.com"));
        assertTrue(Helper.isEmail("michaeldesanta@gmail.com"));
        assertTrue(Helper.isEmail("trevor@gmail.com"));
        assertTrue(Helper.isEmail("franklinclinton1988@yahoo.com"));
    }
}
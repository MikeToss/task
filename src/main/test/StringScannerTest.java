import com.task.core.Helper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Mikhail Terletskyi
 * @Create 10/26/2020
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StringScannerTest {

    @Test
    public void stringHasDigitsTest(){
        assertTrue(Helper.stringHasDigits("some word 123"));
        assertTrue(Helper.stringHasDigits("some1S4t44ing"));
        assertTrue(Helper.stringHasDigits("dffdsdsdss43424dfdfdf"));
        assertFalse(Helper.stringHasDigits("cat"));
        assertFalse(Helper.stringHasDigits("Google"));
        assertFalse(Helper.stringHasDigits("How are you ?"));
    }

    @Test
    public void stringHasLettersTest(){
        assertTrue(Helper.stringHasLetters("some word 123"));
        assertTrue(Helper.stringHasLetters("some1S4t44ing"));
        assertTrue(Helper.stringHasLetters("cat"));
        assertFalse(Helper.stringHasLetters("234324234"));
    }
}

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IntermedioTest
{
    Intermedio i = new Intermedio(8, 9);
    @Test
    public void testRiesgoHorizontal() {
        i.tablaLogica[7][0] = 1;
        i.tablaLogica[7][1] = 1;
        i.tablaLogica[7][2] = 1;
        assertEquals(3, i.hayRiesgoHorizontal());
    }
}

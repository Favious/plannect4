
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DificilTest
{
    Dificil d = new Dificil(9, 10);
    @Test
    public void testVentajaVertical() {
        d.tablaLogica[8][0] = 2;
        d.tablaLogica[7][0] = 2;
        d.tablaLogica[6][0] = 2;
        d.tablaLogica[5][0] = 0;
        assertEquals(0, d.ventajaVertical());
    }
}

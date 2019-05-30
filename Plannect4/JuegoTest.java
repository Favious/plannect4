
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JuegoTest
{
    Juego j = new Juego(7,8);
    @Test
    public void testPosicionDisponible() {
        assertEquals(6, j.posicionDisponible(7));
        j.tablaLogica[6][7] = 1;
        assertEquals(-1, j.posicionDisponible(7));
        j.tablaLogica[0][0] = 0;
        assertEquals(0, j.posicionDisponible(0));
    }
}

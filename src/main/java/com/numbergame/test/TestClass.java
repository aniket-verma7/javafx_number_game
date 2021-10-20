import com.numbergame.model.Cell;
import com.numbergame.model.Game;
import com.numbergame.model.Player;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestClass {
    @Test
    public void testGameClass()
    {
        assertFalse(Game.isLegal(5,5,2,2));
        assertTrue(Game.isLegal(2,3,2,2));
        assertTrue(Game.move(2,3,2,2));
        assertFalse(Game.move(5,5,2,2));

        Cell[][] cell = new Cell[5][5];
        for(int i=0;i<5;i++)
        {
            for(int j=0;j<5;j++)
            {
                cell[i][j] = new Cell(i,j,30,30);
            }
        }
       assertFalse(Game.isWinner(cell,2,3));
    }


    @Test
    public void testGridClass()
    {
        Cell grid = new Cell(3,4,30,30);
        assertEquals(3,grid.getX());
        assertEquals(4,grid.getY());
        assertEquals(30,grid.getWidth());
        assertEquals(30,grid.getHeight());
        assertEquals(grid.getRectangle().getFill(), Color.LIGHTYELLOW);

        grid.setX(5);
        grid.setY(1);
        grid.setWidth(20);
        grid.setHeight(50);

        assertEquals(5,grid.getI());
        assertEquals(1,grid.getJ());
        assertEquals(20,grid.getW());
        assertEquals(50,grid.getH());
        assertTrue(grid.isClicked());
    }


    @Test
    public void testPlayerClass()
    {
        Player player = new Player("John",false);
        assertEquals("John",player.getName());
        assertFalse(player.isChance());
        player.setChance(true);
        assertTrue(player.isChance());
    }
}

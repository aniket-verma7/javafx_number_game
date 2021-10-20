package com.numbergame.controller;

import com.numbergame.model.Cell;
import com.numbergame.model.Game;
import com.numbergame.model.Player;
import com.numbergame.model.Utils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameController
{
    @FXML
    private TextField result,player1,player2;

    @FXML
    private Pane board;

    @FXML
    Label label1, label2 ; // player1 and player2

    private int currentNumber = 1;
    private Player objPlayer1,objPlayer2;
    private int x = -1,y = -1;

    public static Cell[][] grid = new Cell[Utils.girdSize][Utils.girdSize];

    public void initialize()
    {
        objPlayer1 = new Player("",true);
        objPlayer2 = new Player("",false);
        label1.setText(Utils.player1Chance);


        player1.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable,String oldValue, String newValue)
            {
                objPlayer1.setName(newValue);
            }
        });

        player2.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable,String oldValue, String newValue)
            {
                objPlayer2.setName(newValue);
                displayBoard();
            }
        });

    }

    public static Cell[][] getGrid() {
        return grid;
    }

    public void displayBoard()
    {
        int girdSize = Utils.girdSize;

        if (girdSize < 3 || girdSize > 12)
            girdSize = 8;

        int windowWidth = (int) board.getWidth();
        int windowHeight = (int) board.getHeight();

        int cellWidth = windowWidth / girdSize;
        int cellHeight = windowHeight / girdSize;

        for(int i=0;i<girdSize;i++)
            for(int j=0;j<girdSize;j++)
            {
                grid[i][j] = new Cell(i,j,cellWidth,cellHeight);
                grid[i][j].setRectangle(makeRectangle(i*cellWidth,j*cellHeight,cellWidth,cellHeight));
                board.getChildren().add(grid[i][j].getRectangle());
            }
    }

    private Rectangle makeRectangle(int x, int y, int cellWidth, int cellHeight)
    {
        Rectangle rectangle = new Rectangle(x,y,cellWidth,cellHeight);
        rectangle.setFill(Color.LIGHTYELLOW);
        rectangle.setStroke(Color.BLACK);
        setRectangleCallback(rectangle);
        return rectangle;
    }

    private void setRectangleCallback(Rectangle rectangle)
    {
        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                int i = (int) (rectangle.getX() / rectangle.getWidth());
                int j = (int) (rectangle.getY() / rectangle.getHeight());
                Rectangle clickRectangle = grid[i][j].getRectangle();

                if(objPlayer1.isChance())
                {
                    player1Chance(i,j,clickRectangle);
                }
                else if(objPlayer2.isChance() && Game.move(i,j,x,y) && grid[i][j].colorEquals())
                {
                    player2Played(clickRectangle);
                    x=i;y=j;
                }

                if(Game.isWinner(grid, x, y))
                {
                    setWinner();
                }
            }
        });
    }

    private void setWinner()
    {
        String message = "";
        if(!objPlayer1.isChance())
            message = objPlayer1.getName() + " has won in " +( currentNumber-1) + " moves!";
        else
            message = objPlayer2.getName() + " has won in " +( currentNumber-1) + " moves!";

        result.setText(message);
    }

    private void player1Chance(int i,int j,Rectangle clickRectangle)
    {
        if(x == -1 && y == -1)
        {
            x = i;y = j;
            player1Played(clickRectangle);
        }
        else if(Game.move(i,j,x,y) && grid[i][j].colorEquals())
        {
            player1Played(clickRectangle);
            x=i;y=j;
        }
    }

    private void addNumber(Rectangle rectangle)
    {
        int x = (int) (rectangle.getX() + rectangle.getWidth() / 2);
        int y = (int) (rectangle.getY() + rectangle.getHeight() / 2);

        Text text = new Text(x, y, currentNumber + "");
        text.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR,15));
        text.setFill(Color.WHITE);

        board.getChildren().add(text);
        currentNumber += 1;
    }

    private void player1Played(Rectangle rectangle)
    {
        rectangle.setFill(Utils.player1);
        addNumber(rectangle);
        setPlayerChance();
    }

    private void player2Played(Rectangle rectangle)
    {
        rectangle.setFill(Utils.player2);
        addNumber(rectangle);
        setPlayerChance();
    }


    private void setPlayerChance()
    {
        if(objPlayer1.isChance())
        {
            objPlayer1.setChance(false);
            objPlayer2.setChance(true);
            label1.setText(Utils.player1Normal);
            label2.setText(Utils.player2Chance);
        }
        else if(objPlayer2.isChance())
        {
            objPlayer1.setChance(true);
            objPlayer2.setChance(false);
            label1.setText(Utils.player1Chance);
            label2.setText(Utils.player2Normal);
        }
    }

    public void playAgain(ActionEvent actionEvent)
    {
        currentNumber = 1;
        x = y = -1;
        label1.setText(Utils.player1Chance);
        label2.setText(Utils.player2Normal);
        result.setText("");
        board.getChildren().removeAll();
        displayBoard();
    }
}
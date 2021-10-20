package com.numbergame.model;

public class Game {

    public static boolean isWinner(Cell[][] grid, int i, int j)
    {
        if(i-1>=0 && j< grid.length && grid[i-1][j].colorEquals())
            return false;
        else if(j-1>=0 && i< grid.length && grid[i][j-1].colorEquals())
            return false;
        else if(i+1< grid.length && j< grid.length && grid[i+1][j].colorEquals())
            return false;
        else if(j+1<grid.length && i< grid.length && grid[i][j+1].colorEquals())
            return false;
        else
            return true;
    }

    public static boolean move(int i,int j,int x,int y)
    {
        if(isLegal(i,j,x,y)){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isLegal(int i,int j,int x,int y)
    {
        if(x-1 >=0 && i==x-1 && y==j)
            return true;
        else if(x+1<= Utils.girdSize && x+1==i && y==j)
            return true;
        else if( y-1>=0 && x==i && j == y-1)
            return true;
        else if(y+1<= Utils.girdSize && x==i && j == y+1)
            return true;
        else
            return false;
    }

    public boolean player2canWin(int i, int j)
    {
        int cnt = 0;
        for(int l=i-1 ;l<=i+1;l++)
        {
            for(int k =j-1;k<=j+1;k++)
            {
                if(l+k%2==0)cnt+=1;
            }
        }
        if(cnt%2==0)
        {
            return true;
        }
        return false;
    }

}

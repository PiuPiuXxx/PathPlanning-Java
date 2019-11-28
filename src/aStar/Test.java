package aStar;

/**
 * 
 * @className:Test
 * 
 * @description:主程序测试入口
 * @author chengweishao
 */

import java.io.File;

import excel.ReadExcel;

public class Test
{

	public static void main(String[] args)
	{
		
		File file=new File("src/lab.xls");
		ReadExcel e=new ReadExcel(file);
		
		int[][] map = e.getCellinfo();
		int row = map.length;
		int line = map[0].length;
		
		MapInfo info=new MapInfo(map, line, row, new Node(41, 14), new Node(51, 25));
		Coord[] path = new AStar().start(info);
		printPath(path);
		printMap(map);
	}
	
	/**
	 * @description: 打印地图
	 */
	public static void printMap(int[][] maps)
	{
		for (int i = 0; i < maps.length; i++)
		{
			for (int j = 0; j < maps[i].length; j++)
			{
				System.out.print(maps[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	/**
	 * @description: 打印路径
	 */
	public static void printPath(Coord[] path)
	{
		for (int i = 0; i < path.length; i++)
		{
			System.out.println("[" + path[i].height + ", " + path[i].width + "]");
		}
	}

}

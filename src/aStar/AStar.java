package aStar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
 * ClassName: AStar 
 * @Description: A星算法
 * @author chengweishao
 */
public class AStar
{
	public final static int BAR = 1; // 障碍值
	public final static int PATH = 2; // 路径
	public final static int DIRECT_VALUE = 10; // 横竖移动代价
	public final static int OBLIQUE_VALUE = 14; // 斜移动代价
	
	Queue<Node> openList = new PriorityQueue<Node>(); // 优先队列(升序)
	List<Node> closeList = new ArrayList<Node>();
	List<Coord> pathList = new ArrayList<Coord>();
	Coord[] pathArr;
	
	/**
	 * 开始算法
	 */
	public Coord[] start(MapInfo mapInfo)
	{
		if(mapInfo==null) return null;
		// clean
		openList.clear();
		closeList.clear();
		// 开始搜索
		openList.add(mapInfo.start);
		moveNodes(mapInfo);
		// 返回路径
		Collections.reverse(pathList);
		pathArr = new Coord[pathList.size()];
		
		return pathList.toArray(pathArr);
	}

	/**
	 * 移动当前结点
	 */
	private void moveNodes(MapInfo mapInfo)
	{
		while (!openList.isEmpty())
		{
			if (isCoordInClose(mapInfo.end.coord))
			{
				drawPath(mapInfo.maps, mapInfo.end);
				break;
			}
			Node current = openList.poll();
			closeList.add(current);
			addNeighborNodeInOpen(mapInfo,current);
		}
	}
	
	/**
	 * 在二维数组中绘制路径
	 */
	private void drawPath(int[][] maps, Node end)
	{
		if(end==null||maps==null) return;
		System.out.println("总代价：" + end.G);
		while (end != null)
		{
			Coord c = end.coord;
			maps[c.height][c.width] = PATH;
			pathList.add(c);
			end = end.parent;
		}
	}

	/**
	 * 添加所有邻结点到open表
	 */
	private void addNeighborNodeInOpen(MapInfo mapInfo,Node current)
	{
		int height = current.coord.height;
		int width = current.coord.width;
		// 左
		addNeighborNodeInOpen(mapInfo,current, width - 1, height, DIRECT_VALUE);
		// 上
		addNeighborNodeInOpen(mapInfo,current, width, height - 1, DIRECT_VALUE);
		// 右
		addNeighborNodeInOpen(mapInfo,current, width + 1, height, DIRECT_VALUE);
		// 下
		addNeighborNodeInOpen(mapInfo,current, width, height + 1, DIRECT_VALUE);
		// 左上
		addNeighborNodeInOpen(mapInfo,current, width - 1, height - 1, OBLIQUE_VALUE);
		// 右上
		addNeighborNodeInOpen(mapInfo,current, width + 1, height - 1, OBLIQUE_VALUE);
		// 右下
		addNeighborNodeInOpen(mapInfo,current, width + 1, height + 1, OBLIQUE_VALUE);
		// 左下
		addNeighborNodeInOpen(mapInfo,current, width - 1, height + 1, OBLIQUE_VALUE);
	}

	/**
	 * 添加一个邻结点到open表
	 */
	private void addNeighborNodeInOpen(MapInfo mapInfo,Node current, int width, int height, int value)
	{
		if (canAddNodeToOpen(mapInfo,width, height))
		{
			Node end=mapInfo.end;
			Coord coord = new Coord(width, height);
			int G = current.G + value; // 计算邻结点的G值
			Node child = findNodeInOpen(coord);
			if (child == null)
			{
				int H=calcH(end.coord,coord); // 计算H值
				if(isEndNode(end.coord,coord))
				{
					child=end;
					child.parent=current;
					child.G=G;
					child.H=H;
				}
				else
				{
					child = new Node(coord, current, G, H);
				}
				openList.add(child);
			}
			else if (child.G > G)
			{
				child.G = G;
				child.parent = current;
				openList.add(child);
			}
		}
	}

	/**
	 * 从Open列表中查找结点
	 */
	private Node findNodeInOpen(Coord coord)
	{
		if (coord == null || openList.isEmpty()) return null;
		for (Node node : openList)
		{
			if (node.coord.equals(coord))
			{
				return node;
			}
		}
		return null;
	}


	/**
	 * 计算H的估值：“曼哈顿”法，坐标分别取差值相加
	 */
	private int calcH(Coord end,Coord coord)
	{
		return Math.abs(end.width - coord.width)
				+ Math.abs(end.height - coord.height);
	}
	
	/**
	 * 判断结点是否是最终结点
	 */
	private boolean isEndNode(Coord end,Coord coord)
	{
		return coord != null && end.equals(coord);
	}

	/**
	 * 判断结点能否放入Open列表
	 */
	private boolean canAddNodeToOpen(MapInfo mapInfo,int width, int height)
	{
		// 是否在地图中
		if (width < 0 || width >= mapInfo.width || height < 0 || height >= mapInfo.hight) return false;
		// 判断是否是不可通过的结点
		if (mapInfo.maps[height][width] == BAR) return false;
		// 判断结点是否存在close表
		if (isCoordInClose(width, height)) return false;

		return true;
	}

	/**
	 * 判断坐标是否在close表中
	 */
	private boolean isCoordInClose(Coord coord)
	{
		return coord!=null&&isCoordInClose(coord.width, coord.height);
	}

	/**
	 * 判断坐标是否在close表中
	 */
	private boolean isCoordInClose(int width, int height)
	{
		if (closeList.isEmpty()) return false;
		for (Node node : closeList)
		{
			if (node.coord.width == width && node.coord.height == height)
			{
				return true;
			}
		}
		return false;
	}
}

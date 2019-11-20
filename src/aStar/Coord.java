package aStar;
/**
 * 
 * ClassName: Coord
 * 
 * @Description: 坐标
 * @author chengweishao
 */
public class Coord
{

	public int width;
	public int height;

	public Coord(int width, int height)
	{
		this.width = width;
		this.height = height;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null) return false;
		if (obj instanceof Coord)
		{
			Coord c = (Coord) obj;
			return width == c.width && height == c.height;
		}
		return false;
	}
}

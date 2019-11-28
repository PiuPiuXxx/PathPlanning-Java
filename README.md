# PathPlanning-Java

## 使用AStar算法在Java中读取excel格式的地图并且进行路径规划

### 文件结构
   * bin：不用管，可忽略，eclipse编译后放二进制文件的地方
   * lib：jxl包，即Java读取excel文件的依赖包
   * src：核心代码包
      * ReadExcel
         * ReadExcel: 读取Excel文件，并且返回通过get方法返回一个地图的二维数组
      * aStar：
         * Astar: Astar寻路算法
         * coord: 自定义的坐标类，采用(width,height)的地图表示方法，所以要特别注意和(x,y)坐标间的关系）
         * Node: 路径结点, 包括路径的父节点(寻路轨迹),  以及Astar算法里计算路径代价的G(起点到当前结点的代价), H(当前结点到目的结点的估计代价)
         * MapInfo: 包含地图所需的所有输入数据, 地图及其width, height, 以及起始点
         * Test: 主程序测试入口
      * lab.xls: 实验室地图的excel文件
### 怎么使用
   直接运行src/aStar/Test.java文件即可，可以通过调用printPath()或者printMap()函数查看路径和地图(已经注释在了主程序里，直接打开注释即可)


# Cheapest Cell Tower using Quadtrees

Quadtrees are an efficient data-structure used to represent multi-dimensional spatial data. Quadtrees come in many flavours, we shall be looking at one of them, namely Point Quadtrees, in this assignment to represent points on a 2-dimensional cartesian plane.

A Point Quadtree contains a point in the cartesian plane at every node. Further, a point (x0, y0) in it has 4 children, each representing a quadrant obtained by lines x = x0 and y = y0. (See Figure below). Note that, the structure of the Quadtree is dependent on the order of insertion. In the example below, the order of insertion is (5,0), (6,2), (2,2) and (3,0).

<img src = assets/01.png>

## Class `CellTower`

We will use Point Quadtrees to model the map of cell towers in a city. The class `CellTower`
that is used to represent individual cell towers, has the following components.


#### Instance Variable

1. `int x`: The x-coordinate of the cell tower.
2. `int y`: The y-coordinate of the cell tower.
3. `int cost`: A non-negative integer representing the cost of the services offered by
the cell tower

#### Member Function

`public double distance(int b, int c)`: Calculate the distance (L2-norm)
between the cell tower’s location and the point (b,c).

## Class `PointQuadtree`

The class PointQuadtree has the following components.

#### Instance Variable

`PointQuadtreeNode root`: The root of the `PointQuadTree`.

#### Member Function

1. `public boolean insert(CellTower a)`: Insert the cell tower `a` into the Quadtree. Return `true` if successful and `false` if a duplicate exists.

2. `public boolean cellTowerAt(int x, int y)`: Check if a cell tower ex-
ists at location `(x,y)`. Return `true` or `false` accordingly.

3. `public CellTower chooseCellTower(int x, int y, int r)`: Return
the cell tower that offers cheapest services, that is, has minimum cost, and lies within
distance `r` from point `(x,y)`. Return `null` if no such cell tower exists.

## Class `PointQuadtreeNode`

`PointQuadtree` is built using class `PointQuadtreeNode` which contains a `CellTower` and a quadrant array

#### Instance Variable

1. `CellTower c`: The cell tower at this node.
2. `PointQuadtreeNode[] quadrants`: Array of children each representing a quadrant in the order `NW, NE, SW, SE`. You can also find an enum in the starter code you can make use of for this purpose.
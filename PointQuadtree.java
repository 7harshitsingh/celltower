import java.util.ArrayList;

public class PointQuadtree {

    enum Quad {
        NW,
        NE,
        SW,
        SE
    }

    public PointQuadtreeNode root;

    public PointQuadtree() {
        this.root = null;
    }

    public boolean insert(CellTower a) {
        if (cellTowerAt(a.x, a.y)) {
            return false;
        } else {
            root = recursiveInsert(root, a);
            return true;
        }
    }

    private PointQuadtreeNode recursiveInsert(PointQuadtreeNode node, CellTower c) {
        if (node == null) {
            PointQuadtreeNode n = new PointQuadtreeNode(c);
            node = n;
        }

        if (c.x > node.celltower.x && c.y > node.celltower.y) {
            node.quadrants[Quad.NE.ordinal()] = recursiveInsert(node.quadrants[Quad.NE.ordinal()], c);
        }

        if (c.x == node.celltower.x && c.y > node.celltower.y) {
            node.quadrants[Quad.NE.ordinal()] = recursiveInsert(node.quadrants[Quad.NE.ordinal()], c);
        }

        if (c.x > node.celltower.x && c.y == node.celltower.y) {
            node.quadrants[Quad.NE.ordinal()] = recursiveInsert(node.quadrants[Quad.NE.ordinal()], c);
        }

        if (c.x > node.celltower.x && c.y < node.celltower.y) {
            node.quadrants[Quad.SE.ordinal()] = recursiveInsert(node.quadrants[Quad.SE.ordinal()], c);
        }

        if (c.x == node.celltower.x && c.y < node.celltower.y) {
            node.quadrants[Quad.SE.ordinal()] = recursiveInsert(node.quadrants[Quad.SE.ordinal()], c);
        }

        if (c.x < node.celltower.x && c.y > node.celltower.y) {
            node.quadrants[Quad.NW.ordinal()] = recursiveInsert(node.quadrants[Quad.NW.ordinal()], c);
        }

        if (c.x < node.celltower.x && c.y == node.celltower.y) {
            node.quadrants[Quad.NW.ordinal()] = recursiveInsert(node.quadrants[Quad.NW.ordinal()], c);
        }

        if (c.x < node.celltower.x && c.y < node.celltower.y) {
            node.quadrants[Quad.SW.ordinal()] = recursiveInsert(node.quadrants[Quad.SW.ordinal()], c);
        }

        return node;
    }

    public boolean cellTowerAt(int x, int y) {
        if (recursiveSearch(root, x, y) == null) {
            return false;
        } else {
            return true;
        }
    }

    private PointQuadtreeNode recursiveSearch(PointQuadtreeNode node, int x, int y) {
        if (node == null) {
            return node;
        }

        if ((x == node.celltower.x && y == node.celltower.y)) {
            return node;
        }

        if (x > node.celltower.x && y > node.celltower.y) {
            return recursiveSearch(node.quadrants[Quad.NE.ordinal()], x, y);
        }

        if (x == node.celltower.x && y > node.celltower.y) {
            return recursiveSearch(node.quadrants[Quad.NE.ordinal()], x, y);
        }

        if (x > node.celltower.x && y == node.celltower.y) {
            return recursiveSearch(node.quadrants[Quad.NE.ordinal()], x, y);
        }

        if (x > node.celltower.x && y < node.celltower.y) {
            return recursiveSearch(node.quadrants[Quad.SE.ordinal()], x, y);
        }

        if (x == node.celltower.x && y < node.celltower.y) {
            return recursiveSearch(node.quadrants[Quad.SE.ordinal()], x, y);
        }

        if (x < node.celltower.x && y > node.celltower.y) {
            return recursiveSearch(node.quadrants[Quad.NW.ordinal()], x, y);
        }

        if (x < node.celltower.x && y == node.celltower.y) {
            return recursiveSearch(node.quadrants[Quad.NW.ordinal()], x, y);
        }

        else {
            return recursiveSearch(node.quadrants[Quad.SW.ordinal()], x, y);
        }

    }

    public CellTower chooseCellTower(int x, int y, int r) {

        ArrayList<PointQuadtreeNode> arr = new ArrayList<PointQuadtreeNode>();
        helper(root, x, y, r, arr);
        int minCost = arr.get(Quad.NW.ordinal()).celltower.cost;
        int index = Quad.NW.ordinal();

        for (int i = Quad.NW.ordinal(); i < arr.size(); i++) {
            if (arr.get(i).celltower.cost < minCost) {
                minCost = arr.get(i).celltower.cost;
                index = i;
            }
            continue;
        }

        CellTower c = arr.get(index).celltower;
        return c;
    }

    private void helper(PointQuadtreeNode node, int x, int y, int r, ArrayList<PointQuadtreeNode> arr) {
        if (node == null)
            return;
        if (node.celltower.distance(x, y) <= r) {
            arr.add(node);
        }
        helper(node.quadrants[Quad.NW.ordinal()], x, y, r, arr);
        helper(node.quadrants[Quad.NE.ordinal()], x, y, r, arr);
        helper(node.quadrants[Quad.SW.ordinal()], x, y, r, arr);
        helper(node.quadrants[Quad.SE.ordinal()], x, y, r, arr);
    }

}

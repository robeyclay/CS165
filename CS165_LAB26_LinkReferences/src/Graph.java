/* Graph class
 * Template made by Toby Patterson
 * With the help of: https://www.cs.colostate.edu/~cs165/.Spring20/assignments/P8/doc/
 * Completed by: your name here
 * 6/2/2020
 * For cs165 at CSU
 *
 * A basic graph which has a depth first search print method. It uses
 * link references to keep track of edges.
 * */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Graph<E extends Comparable<E>> extends GraphAbstract<E> {
    public Graph() {
        nodes = new ArrayList<GraphNode>();
    }

    /* addEdge
     * Params: data1 & data2, both data items to be added and connected
     * If the either of the data items are not in the nodes ArrayList,
     * add them as new nodes. Otherwise, find the nodes in the ArrayList so
     * you can make them point to each other. Implement so there are no duplicates
     * added to either of the nodes neighbors ArrayList.
     * TODO: implement this method.
     * */
    @Override
    public void addEdge(E data1, E data2) {
        //YOUR CODE HERE
        GraphNode newNodeData1 = new GraphNode(data1);
        GraphNode newNodeData2 = new GraphNode(data2);
        if(!nodes.contains(newNodeData1)) {
            nodes.add(newNodeData1);
            if(!nodes.contains(newNodeData2)) {
                newNodeData1.neighbors.add(newNodeData2);
                newNodeData2.neighbors.add(newNodeData1);
                nodes.add(newNodeData2);
            } else {
                int index = nodes.indexOf(data2);
                GraphNode data2Node = nodes.get(index);
                newNodeData1.neighbors.add(data2Node);
                data2Node.neighbors.add(newNodeData1);
            }
        } else if (nodes.contains(newNodeData1) && !nodes.contains(newNodeData2)) {
            int index = nodes.indexOf(newNodeData1);
            GraphNode data1Node = nodes.get(index);
            newNodeData2.neighbors.add(data1Node);
            data1Node.neighbors.add(newNodeData2);
            nodes.add(newNodeData2);
        } else {
            int indexData1 = nodes.indexOf(newNodeData1);
            int indexData2 = nodes.indexOf(newNodeData2);
            GraphNode data1Node = nodes.get(indexData1);
            GraphNode data2Node = nodes.get(indexData2);

            data1Node.neighbors.add(data2Node);
            data2Node.neighbors.add(data1Node);
        }
        for(GraphNode p: nodes) {
            System.out.println("nodes array after addEdge: " + p.data);
        }
        System.out.println();

    }




    /* depthFirst
     * Param: startNode, the node to start the traversal at
     *
     * First, find the startNode based off of startItem (hint: indexOf())
     * I recommend having an ArrayList of GraphNodes called visisted to keep
     * track of the nodes you've visited.
     *
     * Prints all of the nodes in the graph in depth first order (with a space between nodes)
     * TODO: implement this method.
     * */
    @Override
    public void depthFirst(E startItem) {
        //YOUR CODE HERE
        GraphNode temp = new GraphNode(startItem);
        int indexStart = nodes.indexOf(temp);
        GraphNode startNode = nodes.get(indexStart);
        ArrayList<GraphNode> visited = new ArrayList<>();
        depthFirst(startNode, visited);
    }

    // Recursive helper method for depthFirst
    private void depthFirst(GraphNode curr, ArrayList<GraphNode> visited) {
        //YOUR CODE HERE
        int counter = 0;
        for(int i = 0; i < curr.neighbors.size(); i++) {
            if(!visited.contains(curr.neighbors.get(i))) {
                counter = i;
                break;
            }
        }
        if(visited.contains(curr.neighbors.get(counter))) {
            System.out.print(curr.data + " ");
        } else {
            System.out.print(curr.data + " ");
            visited.add(curr);
            depthFirst(curr.neighbors.get(counter), visited);
        }
    }


}

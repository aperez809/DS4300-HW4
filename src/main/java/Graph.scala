import Redis.Redis

import scala.collection.mutable
import scala.collection.mutable.{HashMap, HashSet, ListBuffer, Queue}


class Graph(redis: Redis) {
    def addNode(v: String): Int = {
        this.redis.rpush("GraphNodes", v);
    }

    def addEdge(u: String, v: String): Unit = {
        if (this.redis.stringToListMap.get(u).contains(v) || this.redis.stringToListMap.get(v).contains(u)) {}
        else {
            this.redis.rpush("GraphEdges-" + u, v);
            this.redis.rpush("GraphEdges-" + v, u);
        }
    }
    def adjacent(v: String): ListBuffer[String] = {
        return this.redis.lrange("GraphEdges-" + v, 0, -1);
    }

    // Uses BFS method to search and store the shortest path from u to v
    def shortestPath(u: String, v: String): ListBuffer[String] = {
        var previousNodes = mutable.HashMap[String, ListBuffer[String]]()
        var distances = mutable.HashMap[String, Int]()

        BFS(u, v, previousNodes, distances);
        return previousNodes.getOrElse(v, ListBuffer[String]());
    }
    
    def BFS(begin: String,
            end: String,
            previousNodes: mutable.HashMap[String, ListBuffer[String]],
            distances: mutable.HashMap[String, Int]): Boolean = {
        // queue of next vertices to search
        var frontier: mutable.Queue[String] = mutable.Queue[String]();
        // set of nodes that have been seen
        var seen: mutable.HashSet[String] = mutable.HashSet[String]();
        // Each node in the graph
        var nodes: ListBuffer[String] = this.redis.getList("GraphNodes");

        // For each node, initialize in `distances` map with value -1 
        // and intialize in `previousNodes` with empty list
        nodes.foreach(node => {
            distances.put(node, -1);
            previousNodes.put(node, ListBuffer[String]());
        });

        seen.add(begin);
        distances.put(begin, 0);
        frontier.enqueue(begin);

        while(frontier.nonEmpty) {
            var currNode = frontier.dequeue();
            this.adjacent(currNode).foreach(neighbor => {
                if(!seen.contains(neighbor)) {
                    seen.add(neighbor);
                    val old_val: Int = distances.getOrElse(neighbor, 0);
                    distances.put(neighbor, old_val + 1);
                    previousNodes.put(neighbor, previousNodes.getOrElse(neighbor, ListBuffer[String]()) += currNode);
                    frontier.enqueue(neighbor);
                    if(neighbor == end) { return true;}
                }
            })
                
        }
        return false; 
    } 
}
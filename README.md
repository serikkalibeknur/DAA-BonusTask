# MST Edge Removal & Replacement

A Java implementation of **Minimum Spanning Tree (MST)** using **Kruskal's Algorithm**, with the ability to:
- Build the MST
- Remove a specified edge
- Identify disconnected components
- Find and add the **minimum-weight replacement edge** to restore connectivity

## Project Structure
src/
└── mst/
├── Edge.java           → Edge with equals/hashCode
├── UnionFind.java      → Disjoint Set Union (DSU)
├── Graph.java          → Graph with edge list
├── MSTHandler.java     → Core logic: build, remove, replace
└── Main.java           → Demo with hardcoded graph

## How to Run
1. Clone the repo: `git clone https://github.com/serikkalibeknur/DAA-BonusTask.git`
2. Open in IntelliJ IDEA.
3. Ensure Java JDK 17+ is set as SDK.
4. Run `Main.java`.
5. Output appears in console, showing original MST, removal, components, replacement, and new MST.

# Output:
Original MST edges:
0 -- 1 (weight: 2)
1 -- 2 (weight: 3)
1 -- 4 (weight: 5)
0 -- 3 (weight: 6)

Removing edge: 1 -- 4 (weight: 5)
Edges in MST after removal:
0 -- 1 (weight: 2)
1 -- 2 (weight: 3)
0 -- 3 (weight: 6)


MST after removing edge 1 -- 4 (weight: 5):
Components after removal:
Component 1: [0, 1, 2, 3]
Component 2: [4]

Replacement edge added: 2 -- 4 (weight: 7)
New MST edges:
0 -- 1 (weight: 2)
1 -- 2 (weight: 3)
0 -- 3 (weight: 6)
2 -- 4 (weight: 7)
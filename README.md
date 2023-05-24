# dungeons-and-dragons
Dungeons and Dragons simulates a small game of DnD using queues. The program navigates through a generated board game space with hexagonal spaces. The objective of the game is to find the hexagonal space containing the exit to the dungeon, whilst finding the most optimal path from the entrance to the exit. Each hexagon can fit one of four categories: empty, wall, dragon and the exit itself. The walls are non-traversable, and the dragon space restricts the player from accessing neighbouring hexagons, hence the program must find the shortest path to the exit while avoiding walls and dragon controlled hexagons. To do this, a doubly-linked list of hexagons are formed then added to a queue. As it runs, it removes hexagons with the lowest priority from the queue (the hexagons at the top of the queue) until it finally creates the path with the lowest priority hexagons.

DLinkedNode, DLPriorityQueue and FindShortestPath were all made by me, while the rest were all provided files

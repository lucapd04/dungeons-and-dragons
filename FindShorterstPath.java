/*
 * FindShortestPath runs a text file that represents the dungeon layout and finds the most optimal path to traverse it
 */
public class FindShorterstPath {

	public static void main(String[] args) {
		boolean failure = false;
		try {
			if (args.length < 1)
				throw new Exception("No input file specified");

			/*
			 * Variables containing the priority queue and dungeon text file are initialized
			 */
			String dungeonFileName = args[0];
			DLPriorityQueue<Hexagon> prioQueue = new DLPriorityQueue<Hexagon>();
			Dungeon level = new Dungeon(dungeonFileName);
			prioQueue.add(level.getStart(), 0);
			level.getStart().markEnqueued();
			boolean exitFound = false;
			boolean dragon;
			Hexagon currChamber;
			int d;

			/*
			 * The while loop keeps the code running through each hexagon until the exit is
			 * found or the queue runs out of hexagon options
			 */
			while (!prioQueue.isEmpty() && exitFound == false) {
				/*
				 * Hexagon with lowest priority removed from queue and becomes the current
				 * hexagon location
				 */
				currChamber = prioQueue.removeMin();
				currChamber.markDequeued();

				if (currChamber.isExit()) {
					exitFound = true;
				}

				/*
				 * For loop checks type of each neighbouring hexagon from the current location,
				 * and adds to the queue if the location is traverseable
				 */
				for (int i = 0; i < 6; i++) {
					dragon = false;
					if (currChamber.getNeighbour(i) != null) {
						if (!currChamber.getNeighbour(i).isWall() && !currChamber.getNeighbour(i).isMarkedDequeued()
								&& !currChamber.getNeighbour(i).isDragon()) {

							d = 1 + currChamber.getDistanceToStart();

							/*
							 * Changes distance of hexagon from start based on its value compared to d
							 */
							if (currChamber.getNeighbour(i).getDistanceToStart() > d) {
								currChamber.getNeighbour(i).setDistanceToStart(d);
								currChamber.getNeighbour(i).setPredecessor(currChamber);
							}

							/*
							 * Updates the priority of the chamber based on if it is already on the queue
							 * and its distance
							 */
							if (currChamber.getNeighbour(i).isMarkedEnqueued()
									&& currChamber.getNeighbour(i).getDistanceToStart() == d) {

								prioQueue.updatePriority(currChamber.getNeighbour(i),
										currChamber.getNeighbour(i).getDistanceToStart()
												+ currChamber.getNeighbour(i).getDistanceToExit(level));

							}

							/*
							 * Checks if any of the neighbouring hexagons is adjacent to a dragon, in which
							 * case it is not added to the queue
							 */
							for (int j = 0; j < 6; j++) {
								if (currChamber.getNeighbour(i).getNeighbour(j) != null) {
									if (currChamber.getNeighbour(i).getNeighbour(j).isDragon())
										dragon = true;

								}

							}

							if (dragon == false && !currChamber.getNeighbour(i).isMarkedEnqueued()) {

								prioQueue.add(currChamber.getNeighbour(i),
										currChamber.getNeighbour(i).getDistanceToStart()
												+ currChamber.getNeighbour(i).getDistanceToExit(level));
								currChamber.getNeighbour(i).markEnqueued();

							}
						}
					}
				}
			}

			/*
			 * Throws an exception if queue is empty, in which case the dungeon would have
			 * no possible path to the exit
			 */
			if (prioQueue.isEmpty())
				throw new EmptyPriorityQueueException("Queue is empty: there is no solution to this dungeon.");

			/*
			 * All exceptions are caught by the catch statements
			 */
		} catch (InvalidDungeonCharacterException e) {
			System.out.println(e.getMessage());
		} catch (EmptyPriorityQueueException e) {
			System.out.println(e.getMessage());
		} catch (InvalidNeighbourIndexException e) {
			System.out.println(e.getMessage());
		} catch (InvalidElementException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}

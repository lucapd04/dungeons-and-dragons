import java.io.FileNotFoundException;
import java.io.IOException;

public class FindShorterstPath2 {

	public static void main(String[] args) {
		try {
			if (args.length < 1)
				throw new Exception("No input file specified");
			String dungeonFileName = args[0];
			DLPriorityQueue<Hexagon> prioQueue = new DLPriorityQueue<Hexagon>();
			Dungeon path = new Dungeon(dungeonFileName);
			Hexagon start = path.getStart();
			prioQueue.add(start, 0);
			start.markEnqueued();
			boolean exitFound = false;
			Hexagon currChamber = start;
			Hexagon neighbourChamber;
			while (!prioQueue.isEmpty() && exitFound == false) {
				int d = 1 + currChamber.getDistanceToStart();

				if (currChamber.isExit()) {
					exitFound = true;
				}

				if (currChamber.isDragon()) {
					currChamber.markDequeued();
					currChamber = currChamber.getPredecessor();
				}

				for (int i = 0; i < 6; i++) {
					neighbourChamber = currChamber.getNeighbour(i);
					for (int j = 0; j < 6; j++) {
						if (neighbourChamber.getNeighbour(j).isDragon())
							neighbourChamber.markDequeued();
					}
					if (!neighbourChamber.isEmpty() && !neighbourChamber.isWall()
							&& !neighbourChamber.isMarkedDequeued()) {

						if (neighbourChamber.isMarkedEnqueued() && neighbourChamber.getDistanceToStart() == d) {
							prioQueue.updatePriority(neighbourChamber,
									neighbourChamber.getDistanceToStart() + neighbourChamber.getDistanceToExit(path));
						}

						else if (!neighbourChamber.isMarkedEnqueued()) {
							if (neighbourChamber.getDistanceToStart() > d) {
								neighbourChamber.setDistanceToStart(d);
							}
							neighbourChamber.setPredecessor(currChamber);
							prioQueue.add(neighbourChamber,
									neighbourChamber.getDistanceToStart() + neighbourChamber.getDistanceToExit(path));
							neighbourChamber.markEnqueued();
						}

					}

				}

				currChamber = prioQueue.removeMin();
				currChamber.markDequeued();
			}

		} catch (InvalidDungeonCharacterException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}

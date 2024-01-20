import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * HackerRank Create a comparator of Player the sorts in descending order by score. If two Players have
 * the same score, sort by name (ascending).
 */
public class Player {
	
	String name;
	
	int score;

	Player(String name, int score) {
		
		this.name = name;
		this.score = score;
	}
	
	@Override
	public String toString() {
		
		return this.name + " " + this.score;
	}

	static class ComparatorSort implements Comparator<Player> {

		@Override
		public int compare(Player a, Player b) {

			if (a.score < b.score)
				return 1;
			else if (a.score == b.score) {
				if (a.name.compareToIgnoreCase(b.name) >= 1)
					return 1;
				else if (a.name.compareToIgnoreCase(b.name) == 0)
					return 0;
				else
					return -1;
			} else
				return -1;
		}
	}

	public static void main(String[] args) {
		
		Player p1 = new Player("Jones", 1);
		Player p2 = new Player("Mike", 20);
		Player p3 = new Player("David", 45);
		Player p4 = new Player("Jones", 100);
		Player p5 = new Player("Chris", 45);
		
		List<Player> player = new ArrayList<>();
		player.add(p1);
		player.add(p2);
		player.add(p3);
		player.add(p4);
		player.add(p5);
			
		Collections.sort(player, new ComparatorSort());
		Stream.of(player).forEach(System.out::println);		
	}

}

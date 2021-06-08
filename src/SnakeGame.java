
import java.util.Scanner;

public class SnakeGame {

	public static void main(String[] args) {
		Scanner WOW = new Scanner(System.in);
		System.out.println("請輸入長");
		int n = WOW.nextInt();
		System.out.println("請輸入寬");
		int m = WOW.nextInt();

		Map map = new Map(m, n);
		map.printMap();

		do {
			String Move = WOW.next();
			try {
				switch (Move) {

				case "w":
					map.snakeUp();
					break;

				case "s":
					map.snakeDown();
					break;

				case "a":
					map.snakeLeft();
					break;

				case "d":
					map.snakeRight();
					break;
				}
				map.printMap();

			} catch (CollisionWallException e) {
				System.out.println(e.getMessage());
				break;
			} catch (CollisionBodyException e) {
				System.out.println(e.getMessage());
				break;
			} catch (EatFruitException e) {
				System.out.println(e.getMessage());
				map.printMap();

			}
		} while (true);
		WOW.close();
	}

}

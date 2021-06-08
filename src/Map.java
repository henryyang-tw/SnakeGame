
public class Map {
	private int[][] grid;
	private int N;
	private int M;
	PointList snake = new PointList();
	Direction dir = new Direction();

	/**
	 * 地圖的建構子
	 * 
	 * @param m 寬的格數
	 * @param n 高的格數
	 */
	public Map(int m, int n) {
		N = n + 2;
		M = m + 2;
		grid = new int[N][M];
		// 產生N X M格數的地圖
		generalMap();

		// TODO 未來將蛇的長度改為1
		// 產生測試用的蛇
		snake.add(new Point(3, 2));
		snake.add(new Point(3, 3));
		snake.add(new Point(3, 4));
		snake.add(new Point(3, 5));
		snake.add(new Point(3, 6));
		// 產生水果
		// generateFruit();
		this.grid[2][2] = 2;
	}

	public void clearSnake() {
		for (int i = 0; i < snake.size(); i++) {
			Point p = snake.get(i);
			grid[p.getY()][p.getX()] = 0;
		}
	}

	public void moveSnake() {
		this.clearSnake();
		for (int i = snake.size() - 1; i >= 1; i--) {
			Point p2 = snake.get(i);
			Point p1 = snake.get(i - 1);
			p2.setX(p1.getX());
			p2.setY(p1.getY());
		}
	}

	public void addSnakeToMap() {
		// 將蛇的身體設為1
		for (int i = 0; i < snake.size(); i++) {
			Point p = snake.get(i);
			grid[p.getY()][p.getX()] = 1;
		}
	}

	private void generateFruit() {
		int minX = 1;
		int maxX = M - 2;
		int minY = 1;
		int maxY = N - 2;
		int i;
		do {
			// Generate random int value from 1 to M-2
			int randomX = (int) Math.floor(Math.random() * (maxX - minX + 1) + minX);

			// Generate random int value from 1 to N-2
			int randomY = (int) Math.floor(Math.random() * (maxY - minY + 1) + minY);
			i = grid[randomY][randomX];
			grid[randomY][randomX] = 2;
			break;
		} while (i != 1 && i != 2);
	}

	public void snakeGrow() {
		Point tail = snake.get(snake.size() - 1);
		int x = tail.getX();
		int y = tail.getY();
		// 若蛇向上則down=false，新增蛇的尾巴座標(x,y+1)
		if (dir.isUp()) {
			snake.add(new Point(x, y + 1));
		}

		if (dir.isDown()) {
			snake.add(new Point(x, y - 1));
		}

		if (dir.isLeft()) {
			snake.add(new Point(x - 1, y));
		}

		if (dir.isRight()) {
			snake.add(new Point(x + 1, y));
		}
	}

	public void checkCollision(int gridvalue) throws CollisionWallException, CollisionBodyException, EatFruitException {
		switch (gridvalue) {
		case -1:
			throw new CollisionWallException("撞到牆");

		case 1:
			throw new CollisionBodyException("撞到身體");

		case 2:
			// 吃到水果(2)，所以蛇加長
			snakeGrow();
			// {throw new EatFruitException("吃到果實加分");}
			break;
		}
	}

	/**
	 * 蛇往上移
	 * 
	 * @throws CollisionWallException 撞到牆的例外
	 * @throws CollisionBodyException 撞到身體的例外
	 * @throws EatFruitException      吃到水果的例外
	 */
	public void snakeUp() throws CollisionWallException, CollisionBodyException, EatFruitException {
		if (dir.isUp()) {
			// 取PointList中的第0項當頭
			Point head = snake.get(0);

			// 檢查要前往的方向是否發生碰撞事件
			int x = head.getX() + 0;
			int y = head.getY() - 1;
			checkCollision(grid[y][x]);
			// 移動蛇的身體
			moveSnake();
			// 移動蛇的頭
			head.setY(head.getY() - 1);
		}
	}

	public void snakeDown() throws CollisionWallException, CollisionBodyException, EatFruitException {
		if (dir.isDown()) {
			Point head = snake.get(0);

			int x = head.getX() + 0;
			int y = head.getY() + 1;
			checkCollision(grid[y][x]);

			moveSnake();
			head.setY(head.getY() + 1);
		}
	}

	public void snakeLeft() throws CollisionWallException, CollisionBodyException, EatFruitException {
		if (dir.isLeft()) {
			Point head = snake.get(0);

			int x = head.getX() - 1;
			int y = head.getY() + 0;
			checkCollision(grid[y][x]);

			moveSnake();
			head.setX(head.getX() - 1);
		}
	}

	public void snakeRight() throws CollisionWallException, CollisionBodyException, EatFruitException {
		if (dir.isRight()) {
			Point head = snake.get(0);

			int x = head.getX() + 1;
			int y = head.getY() + 0;
			checkCollision(grid[y][x]);

			moveSnake();
			head.setX(head.getX() + 1);
		}
	}

	/**
	 * 產生N X M格數的地圖
	 */
	public void generalMap() {
		for (int i = 0; i < N; i++) {
			grid[i][0] = -1;
			grid[i][M - 1] = -1;
		}
		for (int i = 0; i < M; i++) {
			grid[0][i] = -1;
			grid[N - 1][i] = -1;
		}

	}

	public void printMap() {
		addSnakeToMap();
		// 印出地圖
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.printf("%4d", grid[i][j]);
			}
			System.out.println();
		}

	}

}

/**
 * 此類別是解決當蛇頭行進方向與玩家移動方向剛好相反時，所以造成蛇無法照蛇頭行徑方向的情形。
 * 
 * @author HenryYang
 *
 */
public class Direction {
	

	// 一開始四個方向都可移動
	private boolean up = true;
	private boolean down = true;
	private boolean left = true;
	private boolean right = true;

	// 判斷可不可以朝那個方向
	// 若回傳值為true執行下行的程式
	// 若回傳值為false則不執行下行程式
	public boolean isUp() {
		if (up) {
			down = !up;// 與上不同boolean值
			left = up; // 與上同boolean值
			right = up;
		}
		return up;
	}

	public boolean isDown() {
		if (down) {
			up = !down;
			left = down;
			right = down;
		}
		return down;
	}

	public boolean isLeft() {
		if (left) {
			up = left;
			down = left;
			right = !left;
		}
		return left;
	}

	public boolean isRight() {
		if (right) {
			up = right;
			down = right;
			left = !right;
		}
		return right;
	}

	public boolean getUp() {
		return this.up;
	}

	public boolean getDown() {
		return this.down;
	}

	public boolean getLeft() {
		return this.left;
	}

	public boolean getRight() {
		return this.right;
	}
}

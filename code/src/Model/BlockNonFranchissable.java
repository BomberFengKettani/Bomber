package Model;

public class BlockNonFranchissable extends Block{
	
	public BlockNonFranchissable(float X, float Y) {
		super(X, Y, 0, "BlockNonFranchissable");
	}

	public boolean isObstacle() {
		return true;
	}
}

public abstract class Organism {
	Vector2fNeural position;

	public Organism() {
		position = new Vector2fNeural(0, 0);
	}
	
	public float getX() {
		return position.getX();
	}

	public void setX(float x) {
		position.setX(x);
	}

	public float getY() {
		return position.getY();
	}

	public void setY(float y) {
		position.setY(y);
	}
}
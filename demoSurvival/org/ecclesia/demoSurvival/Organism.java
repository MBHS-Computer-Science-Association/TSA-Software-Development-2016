package org.ecclesia.demoSurvival;
public abstract class Organism {
	CreatureVector position;

	public Organism(float angle, float x, float y) {
		position = new CreatureVector(angle);
		position.setX(x);
		position.setY(y);
	}

	public Organism(float x, float y) {
	}

	public float getX() {
		return position.getX();
	}

	public float getY() {
		return position.getY();
	}

	public void setX(float x) {
		position.setX(x);
	}

	public void setY(float y) {
		position.setY(y);
	}

}
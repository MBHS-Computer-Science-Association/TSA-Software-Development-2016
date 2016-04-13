public abstract class Animal extends Organism {
	float health;
	private CreatureVector velocity;

	public Animal() {
		velocity = new CreatureVector((float) Math.random(), (float) Math.random());
	}

	public float getAngle() {
		return velocity.getAngle();
	}

	public void adjustAngle(float theta) {
		velocity.adjustAngle(theta);
	}

	public void move(boolean forwards) {
		super.setX((forwards ? position.getX() + velocity.getX() : position.getX() - velocity.getX()));
		super.setY((forwards ? position.getY() + velocity.getY() : position.getY() - velocity.getY()));

	}

	public CreatureVector getVelocity() {
		return velocity;
	}
	
	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	/**
	 * Depletes health
	 * 
	 * @param amount
	 *            of health depletion
	 * @return boolean: True if Dead, False if Alive
	 */
	public boolean depleteHealth(float amount) {
		health -= amount;
		return health < 0;
	}
}

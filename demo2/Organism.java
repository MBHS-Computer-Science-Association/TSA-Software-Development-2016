public abstract class Organism {
	CreatureVector position;

	public Organism() {
		position = new CreatureVector(0, 0);
	}
	
	public float getX() 
	{
		return position.getX();
	}

	public float getY() 
	{
		return position.getY();
	}
	
	public void setX(float x) 
	{
		position.setX(x);
	}

	public void setY(float y)
	{
		position.setY(y);
	}
	
}
package sdrmath;


public class Noise
{
	protected float lastOutput_;

	public float tick()
	{
	  lastOutput_ = (float)(2 * Math.random());
	  lastOutput_ -= 1.0f;
	  return lastOutput_;
	}

}
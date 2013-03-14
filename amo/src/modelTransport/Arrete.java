package modelTransport;

public class Arrete {
	private Lieux depart;
	private Lieux arrive;
	private int distance;
	private String intro;
	public Arrete()
	{

	}
	public Arrete(Lieux depart,Lieux arrive,int distance,String intro)
	{
		this.depart=depart;
		this.arrive=arrive;
		this.distance=distance;
		this.intro=intro;
	}
	public Lieux getDepart() {
		return depart;
	}
	public void setDepart(Lieux depart) {
		this.depart = depart;
	}
	public Lieux getArrive() {
		return arrive;
	}
	public void setArrive(Lieux arrive) {
		this.arrive = arrive;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}




}

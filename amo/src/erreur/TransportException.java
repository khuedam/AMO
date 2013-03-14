package erreur;
// la classe qui gere les erreurs 
public class TransportException extends Exception{
	private int code;
	public TransportException()  {
		super();
	}
	public TransportException(String msg){
		super(msg);
	}
	public TransportException(int code, String msg ){
		super(""+code+" "+msg);
		this.code=code;
	}
	public TransportException(String msg,int code ){
		super(msg);
		this.code=code;
	}
	public TransportException(int code ,String msg ,Throwable e){
		super(""+code+" "+msg ,e );
	}
	public TransportException(String msg ,Throwable e){
		super(msg,e );
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
}

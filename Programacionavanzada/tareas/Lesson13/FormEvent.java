package Clases;
import java.util.EventObject;

public class FormEvent extends EventObject{
	
	private String name;
	private String occupation;

	public FormEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	
	public FormEvent(Object source, String name, String occupation) {
		super(source);
		// TODO Auto-generated constructor stub
		this.name = name;
		this.occupation = occupation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occuption) {
		this.occupation = occuption;
	}
	
	
	
}
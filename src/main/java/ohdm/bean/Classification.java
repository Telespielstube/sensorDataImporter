package ohdm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name= "classification")
public class Classification {
	private int id;
	private String clazz;
	private String subClassName;
	
	@Column(name = "id")
	public int getId() {
		return id;
	}
	
	@Column(name = "name")
	public String getClazz() {
		return clazz;
	}
	
	@Column
	public String getSubClassName() {
		return subClassName;
	}
	
	
}

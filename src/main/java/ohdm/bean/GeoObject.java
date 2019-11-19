package ohdm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name= "geoobject")
public class GeoObject {
	private int id;
	private String name;
	private int sourceUserId;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getSourceUserId() {
		return sourceUserId;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSourceUserId(int sourceUserId) {
		this.sourceUserId = sourceUserId;
	}
}

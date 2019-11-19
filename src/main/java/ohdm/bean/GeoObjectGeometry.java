package ohdm.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "geoobject_geometry")
public class GeoObjectGeometry {
	private int id;
	private int classificationId;
	private Date validSince;
	private Date validUntil;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	public int getId() {
		return id;
	}
	
	@Column(name = "classification_id")
	public int getClassificationId() {
		return classificationId;
	}
}

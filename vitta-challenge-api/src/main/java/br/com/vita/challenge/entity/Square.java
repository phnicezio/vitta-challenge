package br.com.vita.challenge.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "square", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"x", "y"}, name = "UK_SQUARE_X_Y")
})
public class Square {

	@Id
	@GeneratedValue
	private Long id;
	
	@Embedded
	private Point point;
	
	@JsonIgnore
	@ManyToOne
	private Territory territory;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Transient
	private Boolean painted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Territory getTerritory() {
		return territory;
	}

	public void setTerritory(Territory territory) {
		this.territory = territory;
	}

	public Boolean getPainted() {
		return painted;
	}

	public void setPainted(Boolean painted) {
		this.painted = painted;
	}
	
}

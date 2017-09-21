package br.com.vita.challenge.entity;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author paulo nicezio
 *
 */
@Entity
@Table(name = "territory")
public class Territory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message="Name is required.")
	private String name;
	
	@NotNull(message="Start is required.")
	@Embedded
	@AttributeOverrides({ 
		@AttributeOverride(name="x", column = @Column(name="start_x")),
		@AttributeOverride(name="y", column = @Column(name="start_y")),
	})
	private Point start;
	
	@NotNull(message="End is required.")
	@Embedded
	@AttributeOverrides({ 
		@AttributeOverride(name="x", column = @Column(name="end_x")),
		@AttributeOverride(name="y", column = @Column(name="end_y")),
	})
	private Point end;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="territory", orphanRemoval = true)
    private List<Square> squares;
	
	@Transient
	private int paintedArea;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public Point getEnd() {
		return end;
	}

	public void setEnd(Point end) {
		this.end = end;
	}
	
	public List<Square> getSquares() {
		return squares;
	}

	public void setSquares(List<Square> squares) {
		this.squares = squares;
	}

	public int getPaintedArea() {
		return paintedArea;
	}

	public void setPaintedArea(int paintedArea) {
		this.paintedArea = paintedArea;
	}

	@Override
	public String toString() {
		return "Territory [id=" + id + ", name=" + name + ", start=" + start + ", end=" + end + "]";
	}
	
}


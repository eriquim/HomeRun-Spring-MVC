package br.ufrn.imd.residencia.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.ufrn.imd.residencia.exception.BussinessException;
import lombok.Data;

@Entity
@javax.persistence.Table(schema="public",name="modelo")
@Data
public class Modelo implements PersistenceDB{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4770571406554985036L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private int ano;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name = "id_marca")
	private Marca marca;
	
	public Modelo() {
		marca = new Marca();
	}
	
	@Override
	public void validate() throws BussinessException {
		if(nome == null || nome.isEmpty())
			throw new BussinessException("Atributo Nome Vazio");
		
	}
}

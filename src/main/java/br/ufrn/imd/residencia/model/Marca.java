package br.ufrn.imd.residencia.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.ufrn.imd.residencia.exception.BussinessException;
import lombok.Data;

@Entity
@javax.persistence.Table(schema="public",name="marca")
@Data
public class Marca implements PersistenceDB{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3667316394750413117L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private String sigla;
	
	@OneToMany(mappedBy="marca",fetch=FetchType.LAZY,cascade=CascadeType.DETACH)
	private List<Modelo> modelos;
	
	@Override
	public void validate() throws BussinessException {
		if(nome == null || nome.isEmpty())
			throw new BussinessException("Atributo Nome Vazio");
		
	}
	
}

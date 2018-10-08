package br.ufrn.imd.residencia.model;

import javax.persistence.Entity;

import br.ufrn.imd.residencia.exception.BussinessException;
import lombok.Data;

@Entity(name="public.marca")
@Data
public class Marca implements PersistenceDB{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3667316394750413117L;
	
	private Integer id;
	
	private String nome;
	
	private String sigla;
	
	@Override
	public void validate() throws BussinessException {
		if(nome == null || nome.isEmpty())
			throw new BussinessException("Atributo Nome Vazio");
		
	}
	
}

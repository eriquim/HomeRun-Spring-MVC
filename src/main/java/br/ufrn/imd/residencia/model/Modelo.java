package br.ufrn.imd.residencia.model;

import br.ufrn.imd.residencia.exception.BussinessException;
import lombok.Data;

@Data
public class Modelo implements PersistenceDB{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4770571406554985036L;
	
	private Integer id;
	private String nome;
	private int ano;
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

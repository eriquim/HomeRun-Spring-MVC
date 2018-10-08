/**
 * 
 */
package br.ufrn.imd.residencia.model;

import java.io.Serializable;

import br.ufrn.imd.residencia.exception.BussinessException;

/**
 * @author eriquim
 *
 */
public interface PersistenceDB extends Serializable {
	
	/**
	 * 
	 */
	public Integer getId();
	
	public void setId(Integer id) ;
	
	public abstract void validate() throws BussinessException;

}

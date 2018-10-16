package br.ufrn.imd.residencia.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.imd.residencia.exception.BussinessException;
import br.ufrn.imd.residencia.model.Marca;
import br.ufrn.imd.residencia.repository.MarcaRepository;


@Service
public class MarcaServiceImpl {
	
	@Autowired
	MarcaRepository marcaRepository;
	
	
	@Transactional(readOnly = true)
	public List<Marca> findAll() throws SQLException {
		List<Marca> retorno;
			retorno = marcaRepository.findAll();
		return retorno;
	}
	@Transactional(readOnly=false)
	public Marca inserir(Marca marca) throws BussinessException {
		try{
			marca.validate();
			return marcaRepository.save(marca);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BussinessException("Erro ao realizar o insert na base.");
		} 
	}
	
	@Transactional(readOnly=false)
	public Marca update(Marca marca) throws BussinessException {
		try {
			marca.validate();
			return marcaRepository.save(marca);
		} catch (Exception e) {
			throw new BussinessException("Erro ao realizar o update na base.");
		}
	}

	@Transactional(readOnly=false)
	public void remove(Marca marca) throws SQLException {
		marcaRepository.delete(marca);
	}
	
	@Transactional(readOnly = true)
	public Optional<Marca> findById( Integer id) throws SQLException {
		return marcaRepository.findById(id);
	}
}


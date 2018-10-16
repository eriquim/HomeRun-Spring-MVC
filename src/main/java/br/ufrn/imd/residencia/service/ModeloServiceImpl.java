package br.ufrn.imd.residencia.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.imd.residencia.exception.BussinessException;
import br.ufrn.imd.residencia.model.Modelo;
import br.ufrn.imd.residencia.repository.ModeloRepository;

@Service
public class ModeloServiceImpl {
	@Autowired
	ModeloRepository modeloRepository;
	
	
	@Transactional(readOnly = true)
	public List<Modelo> findAll() throws SQLException {
		List<Modelo> retorno;
			retorno = modeloRepository.findAll();
		return retorno;
	}
	
	@Transactional(readOnly = false)
	public Modelo inserir(Modelo modelo) throws BussinessException{
		try {
			modelo.validate();
			return modeloRepository.save(modelo);
		} catch (Exception e) {
			throw new BussinessException("Erro ao realizar o insert na base.");
		}
	}
	
	@Transactional(readOnly = false)
	public Modelo update(Modelo modelo) throws BussinessException {
		try {
			modelo.validate();
			return modeloRepository.save(modelo);
		} catch (Exception e) {
			throw new BussinessException("Erro ao realizar o update na base.");
		}
	}

	@Transactional(readOnly = false)
	public void remove(Modelo modelo) throws SQLException {
		modeloRepository.delete(modelo);
	}
	
	@Transactional(readOnly = true)
	public Optional<Modelo> findById(Integer id) throws SQLException {
		return modeloRepository.findById(id);
	}

}

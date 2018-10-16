package br.ufrn.imd.residencia.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufrn.imd.residencia.model.Marca;
import br.ufrn.imd.residencia.model.Modelo;
import br.ufrn.imd.residencia.service.MarcaServiceImpl;
import br.ufrn.imd.residencia.service.ModeloServiceImpl;

@Controller
@RequestMapping("/modelos")
public class ModeloController {
	
	private static final String MSG_SUCESS_INSERT = "Modelo inserido com sucesso.";
	private static final String MSG_SUCESS_UPDATE = "Modelo atualizado com sucesso.";
	private static final String MSG_SUCESS_DELETE = "Modelo apagado com sucesso.";
	private static final String MSG_ERROR = "Erro ao executar a operação.";

	
	@Autowired
	private ModeloServiceImpl modeloService;

	@Autowired
	private MarcaServiceImpl marcaService;
	

	@GetMapping
	public String index(Model model) throws SQLException {
		List<Modelo> all = modeloService.findAll();
		model.addAttribute("listModelos", all);
		return "modelo/index";
	}
	
	// Tela de Show Student
	@GetMapping("/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		if (id != null) {
			Modelo modelo = null;
			try {
				modelo = modeloService.findById(id).get();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			model.addAttribute("modelo", modelo);
		}
		return "modelo/show";
	}

	// Tela com Formulario de New Student
	@GetMapping(value = "/new")
	public String create(Model model, @ModelAttribute Modelo entityModelo, 
			             @ModelAttribute Marca entityMarca) {
		List<Marca> all = null;
		try {
			all = marcaService.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		model.addAttribute("modelo",entityModelo);
		model.addAttribute("marca",entityMarca);
		model.addAttribute("marcas", all);
		
		return "modelo/form";
	}
	
	@PostMapping
	public String create(@Valid @ModelAttribute Modelo entityModelo, 
			             @Valid @ModelAttribute Marca entityMarca,
			             BindingResult result, RedirectAttributes redirectAttributes) {
		Modelo modelo = null;
	
		try {
			entityModelo.setMarca(entityMarca);
			modelo = modeloService.inserir(entityModelo);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_INSERT);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/modelos/" + modelo.getId();
	}
	
	@GetMapping("/{id}/edit")
	public String update(Model model, @PathVariable("id") Integer id) {
		
		try {
			if (id != null) {
				List<Marca> all = marcaService.findAll();
				model.addAttribute("marcas", all);
				
				Modelo entity = modeloService.findById(id).get();
				model.addAttribute("modelo", entity);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return "modelo/form";
	}
	
	@PutMapping
	public String update(@Valid @ModelAttribute Modelo entity, BindingResult result, 
			             RedirectAttributes redirectAttributes) {
		Modelo modelo= null;
		try {
			modelo = modeloService.update(entity);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_UPDATE);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/modelos/" + modelo.getId();
	}
	
	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		try {
			if (id != null) {
				Modelo entity = modeloService.findById(id).get();
				modeloService.remove(entity);
				redirectAttributes.addFlashAttribute("success", MSG_SUCESS_DELETE);
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			throw new ServiceException(e.getMessage());
		}
		return "redirect:/modelos/";
	}
}

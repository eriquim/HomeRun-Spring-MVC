package br.ufrn.imd.residencia.controller;

import java.sql.SQLException;
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

import com.fasterxml.jackson.databind.Module;

import br.ufrn.imd.residencia.model.Marca;
import br.ufrn.imd.residencia.service.MarcaServiceImpl;

@Controller
@RequestMapping("/marcas")
public class MarcaController {

	private static final String MSG_SUCESS_INSERT = "Marca inserida com sucesso.";
	private static final String MSG_SUCESS_UPDATE = "Marca Atualizada com sucesso.";
	private static final String MSG_SUCESS_DELETE = "Marca apagada com sucesso.";
	private static final String MSG_ERROR = "Erro ao executar a operação.";

	@Autowired
	private MarcaServiceImpl marcaService;
	

	@GetMapping
	public String index(Model model) {
		List<Marca> all;
		try {
			all = marcaService.findAll();
		} catch (SQLException e) {
			all= null;
			e.printStackTrace();
		}
		model.addAttribute("listMarcas", all);
		return "marca/index";
	}
	
	@GetMapping("/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		if (id != null) {
			Marca marca = null;
			try {
				marca = marcaService.findById(id).get();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			model.addAttribute("marca", marca);
		}
		return "marca/show";
	}

	@GetMapping(value = "/new")
	public String create(Model model, @ModelAttribute Marca entityMarca) {
		model.addAttribute("marca", entityMarca);
		
		return "marca/form";
	}
	
	@PostMapping
	public String create(@Valid @ModelAttribute Marca entity, BindingResult result, RedirectAttributes redirectAttributes) {
		Marca marca = null;
		try {
			marca = marcaService.inserir(entity);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_INSERT);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/marcas/" + marca.getId();
	}
	
	@GetMapping("/{id}/edit")
	public String update(Model model, @PathVariable("id") Integer id) {
		try {
			if (id != null) {
				Marca entity = marcaService.findById(id).get();
				model.addAttribute("marca", entity);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return "marca/form";
	}
	
	@PutMapping
	public String update(@Valid @ModelAttribute Marca entity, BindingResult result, RedirectAttributes redirectAttributes) {
		Marca marca = null;
		try {
			marca = marcaService.inserir(entity);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_UPDATE);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/marcas/" + marca.getId();
	}
	
	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		try {
			if (id != null) {
				Marca entity = marcaService.findById(id).get();
				marcaService.remove(entity);
				redirectAttributes.addFlashAttribute("success", MSG_SUCESS_DELETE);
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			throw new ServiceException(e.getMessage());
		}
		return "redirect:/marcas/";
	}

}

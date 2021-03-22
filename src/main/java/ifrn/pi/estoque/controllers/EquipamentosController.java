package ifrn.pi.estoque.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ifrn.pi.estoque.models.Categoria;
import ifrn.pi.estoque.models.Equipamento;
import ifrn.pi.estoque.repositories.CategoriaRepository;
import ifrn.pi.estoque.repositories.EquipamentoRepository;

@Controller
@RequestMapping("/equipamentos")
public class EquipamentosController {

	@Autowired
	private EquipamentoRepository er;
	@Autowired
	private CategoriaRepository cr;
	@GetMapping("/equipamentos/form")
	public String form(Equipamento equipamento) {
		return "equipamentos/formEquipamento";
	}

	@PostMapping
	public String salvar(Equipamento equipamento) {

		System.out.println(equipamento);
		er.save(equipamento);
		return "redirect:/equipamentos";

	}

	@GetMapping
	public ModelAndView listar() {
		List<Equipamento> equipamentos = er.findAll();
		ModelAndView mv = new ModelAndView("equipamentos/lista");
		mv.addObject("equipamentos", equipamentos);
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id, Categoria categoria) {
		ModelAndView md = new ModelAndView();
		java.util.Optional<Equipamento> opt = er.findById(id);
		if (opt.isEmpty()) {
			md.setViewName("redirect:/equipamentos");
			return md;
		}

		md.setViewName("equipamentos/detalhes");
		Equipamento equipamento = opt.get();

		md.addObject("equipamento", equipamento);

		List<Categoria> categorias = cr.findByEquipamento(equipamento);
		md.addObject("categorias", categorias);
		
		return md;
	}

	@PostMapping("/{idEquipamento}")
	public String salvarCategoria(@PathVariable Long idEquipamento, Categoria categoria) {
	
		System.out.println("Id do equipamento: " + idEquipamento);
		System.out.println(categoria);
		
		Optional<Equipamento> opt = er.findById(idEquipamento);
		if(opt.isEmpty()) {
			return "redirect:/equipamentos";
		}
		
		Equipamento equipamento = opt.get();
		categoria.setEquipamento(equipamento);
		
		cr.save(categoria);
		
		return "redirect:/equipamentos/{idEquipamento}";
	}

	@GetMapping("{id}/selecionar")
	public ModelAndView selecionarEquipamento(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Equipamento> opt = er.findById(id);
		if(opt.isEmpty()) {
			md.setViewName("redirect:/equipamentos");
			return md;
		}
		
		Equipamento equipamento = opt.get();
		md.setViewName("equipamentos/formEquipamento");
		md.addObject("equipamento", equipamento);
		
		return md;
		
	}
	
	@GetMapping("{idEquipamento}/categotias/{idCategoria}/selecionar")
	public ModelAndView selecionarCategoria(@PathVariable Long idEvento, @PathVariable Long idCategoria) {
		ModelAndView md = new ModelAndView();
		
		Optional<Equipamento> optEquipamento = er.findById(idCategoria);
		Optional<Categoria> optCategoria = cr.findById(idCategoria);
		
		if(optEquipamento.isEmpty() || optCategoria.isEmpty()) {
			md.setViewName("redirect:/equipamentos");
			return md;
		}
		
		Equipamento equipamento = optEquipamento.get();
		Categoria categoria = optCategoria.get();
		
		if(equipamento.getId() != categoria.getEquipamento().getId()) {
			md.setViewName("redirect:/equipamentos");
			return md;
		
		}
		
		md.setViewName("equipamentos/detalhes");
		md.addObject("categoria", categoria);
		md.addObject("equipamento", equipamento);
		md.addObject("categorias", cr.findByEquipamento(equipamento));
		
		
		return md;
	}
	
	@GetMapping("/{id}/remover")
	public String apagarEquipamento(@PathVariable Long id) {
	
	Optional<Equipamento> opt = er.findById(id);
	
	if(!opt.isEmpty()) {
		Equipamento equipamento = opt.get();
		
		List<Categoria> categorias = cr.findByEquipamento(equipamento);
		
		cr.deleteAll(categorias);
		er.delete(equipamento);
	}
	
	return "redirect:/equipamentos";
}


}

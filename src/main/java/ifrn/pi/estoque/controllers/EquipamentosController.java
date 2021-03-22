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
	public String form() {
		return "equipamentos/formEquipamento";
	}

	@PostMapping
	public String adicionar(Equipamento equipamento) {

		System.out.println(equipamento);
		er.save(equipamento);
		return "equipamentos/equipamento-adicionado";

	}

	@GetMapping
	public ModelAndView listar() {
		List<Equipamento> equipamentos = er.findAll();
		ModelAndView mv = new ModelAndView("equipamentos/lista");
		mv.addObject("equipamentos", equipamentos);
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id) {
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
}

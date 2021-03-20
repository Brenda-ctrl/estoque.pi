package ifrn.pi.estoque.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ifrn.pi.estoque.models.Equipamento;
import ifrn.pi.estoque.repositories.EquipamentoRepository;

@Controller
@RequestMapping("/equipamentos")
public class EquipamentosController {

	@Autowired
	private EquipamentoRepository er;

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
}

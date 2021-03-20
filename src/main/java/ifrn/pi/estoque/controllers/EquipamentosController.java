package ifrn.pi.estoque.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ifrn.pi.estoque.models.Equipamento;
import ifrn.pi.estoque.repositories.EquipamentoRepository;

@Controller
public class EquipamentosController {

	@Autowired
	private EquipamentoRepository er;
	
	@RequestMapping("/equipamentos/form")
	public String form() {
		return "equipamentos/formEquipamento";
	}
	
	@PostMapping("/equipamentos")
	public String adicionar(Equipamento equipamento) {
		
		System.out.println(equipamento);
		er.save(equipamento);
		return "equipamentos/equipamento-adicionado";
		

	}
}

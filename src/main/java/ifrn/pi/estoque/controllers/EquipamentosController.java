package ifrn.pi.estoque.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ifrn.pi.estoque.models.Equipamento;

@Controller
public class EquipamentosController {

	@RequestMapping("/equipamentos/form")
	public String form() {
		return "formEquipamento";
	}
	
	@PostMapping("/equipamentos")
	public String adicionar(Equipamento equipamento) {
		
		System.out.println(equipamento);
		
		return "equipamento-adicionado";
		

	}
}

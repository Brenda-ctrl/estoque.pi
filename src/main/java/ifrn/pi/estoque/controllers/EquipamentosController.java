package ifrn.pi.estoque.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EquipamentosController {

	@RequestMapping("/equipamentos/form")
	public String form() {
		return "formEquipamento";
	}
	
}

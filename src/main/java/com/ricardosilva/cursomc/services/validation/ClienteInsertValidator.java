package com.ricardosilva.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ricardosilva.cursomc.domain.Cliente;
import com.ricardosilva.cursomc.domain.enums.TipoCliente;
import com.ricardosilva.cursomc.dto.ClienteNewDTO;
import com.ricardosilva.cursomc.repositories.ClienteRepository;
import com.ricardosilva.cursomc.resources.exception.FieldMessage;
import com.ricardosilva.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if (objDto.getTipo() == TipoCliente.PESSOAFISICA.getCod() &&
				! BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}

		if (objDto.getTipo() == TipoCliente.PESSOAJURIDICA.getCod() &&
				! BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}

		Cliente cli = repo.findByEmail(objDto.getEmail());
		if (cli != null) {
			list.add(new FieldMessage("email", "E-mail já existente"));
		}		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}

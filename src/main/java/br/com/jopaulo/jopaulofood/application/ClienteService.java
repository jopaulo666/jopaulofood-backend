package br.com.jopaulo.jopaulofood.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jopaulo.jopaulofood.domain.cliente.ClienteRepository;
import br.com.jopaulo.jopaulofood.domain.cliente.Cliente;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	public void saveCliente(Cliente cliente) throws ValidationException {		
		if (!validateEmail(cliente.getEmail(), cliente.getId())) {
			throw new ValidationException("E-mail já cadastrado");
		}
		
		if (cliente.getId() != null) {
			Cliente clienteDB = clienteRepository.findById(cliente.getId()).orElseThrow();
			cliente.setSenha(clienteDB.getSenha());
		} else {
			cliente.encrypPassword();
		}
		
		clienteRepository.save(cliente);
	}
	
	private boolean validateEmail(String email, Integer id) {
		Cliente cliente = clienteRepository.findByEmail(email);
		
		if (cliente != null) {
			if (id == null) {
				return false;
			}
			
			if (!cliente.getId().equals(id)) {
				return false;
			}
		}
		
		return true;
	}
	
}

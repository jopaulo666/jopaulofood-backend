package br.com.jopaulo.jopaulofood.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jopaulo.jopaulofood.domain.cliente.ClienteRepository;
import br.com.jopaulo.jopaulofood.domain.restaurante.Restaurante;
import br.com.jopaulo.jopaulofood.domain.restaurante.RestauranteRepository;
import br.com.jopaulo.jopaulofood.domain.cliente.Cliente;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;

	@Transactional
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
		Restaurante restaurante = restauranteRepository.findByEmail(email);
		
		if (restaurante != null) {
			return false;
		}
		
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

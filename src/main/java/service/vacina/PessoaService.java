package service.vacina;

import model.entity.x1.Pessoa;
import model.repository.x1.PessoaRepository;

public class PessoaService {
	
	private PessoaRepository repository = new PessoaRepository();
	
	public Pessoa salvar (Pessoa novaPessoa) {
		
		if(repository.verificarExistenciaRegistroPorCpf(novaPessoa)) {
			System.out.println("\nUsuário já cadastrado.");
			
		}else {
			novaPessoa = repository.salvar(novaPessoa);
		}
		
		return novaPessoa;
	}
	

}

package service.vacina;

import java.util.List;
import java.util.Objects;

import exception.vacina.ControleVacinasException;
import model.entity.vacina.Pessoa;
import model.repository.vacina.PessoaRepository;

public class PessoaService {
	
	private PessoaRepository repository = new PessoaRepository();
	
	public Pessoa salvar(Pessoa novaPessoa) throws ControleVacinasException {
		
		if(repository.verificarExistenciaRegistroPorCpf(novaPessoa)) {
			 throw new ControleVacinasException("\nPessoa já cadastrado.");
		}else {
			this.validarPessoa(novaPessoa);
			novaPessoa = repository.salvar(novaPessoa);
		}
		
		return novaPessoa;
	}
	
	private void validarPessoa(Pessoa novaPessoa) throws ControleVacinasException {
		String mensagemErro = "";
		
		if (Objects.requireNonNullElse(novaPessoa.getNome(), "").isEmpty()) {
			mensagemErro += "Erro: o campo nome nao pode estar vazio ou nulo.";
	    }
	    if (Objects.requireNonNullElse(novaPessoa.getCpf(), "").isEmpty()) {
	    	mensagemErro += "Erro: o campo cpf nao pode estar vazio ou nulo.";
	    }
	    if (Objects.requireNonNullElse(novaPessoa.getSexo(), "").isEmpty()) {
	    	mensagemErro += "Erro: o campo sexo nao pode estar vazio ou nulo.";
	    }
	    if (novaPessoa.getDataNascimento() == null) {
	    	mensagemErro += "Erro: o campo dataNascimento nao pode ser nulo.";
	    }
	    if (Objects.requireNonNullElse(novaPessoa.getTipo().toString(), "").isEmpty()) {
	    	mensagemErro += "Erro: o campo tipo nao pode estar vazio ou nulo.";
	    }
	    
	    if(!mensagemErro.isEmpty()) {
	    	throw new ControleVacinasException(mensagemErro);
	    }
	}

	public boolean excluir(int id) {
		return repository.excluir(id);
	}

	public List<Pessoa> consultarTodas() {
		return repository.consultarTodos();
	}
	

}

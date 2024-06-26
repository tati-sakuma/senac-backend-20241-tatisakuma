package service.vacina;

import java.util.List;
import java.util.Objects;

import exception.vacina.ControleVacinasException;
import model.entity.vacina.Pessoa;
import model.entity.vacina.Vacinacao;
import model.repository.vacina.PessoaRepository;
import model.repository.vacina.VacinacaoRepository;

public class PessoaService {
	
	private PessoaRepository repository = new PessoaRepository();
	
	public Pessoa salvar(Pessoa novaPessoa) throws ControleVacinasException {
		
		if(repository.verificarExistenciaRegistroPorCpf(novaPessoa)) {
			 throw new ControleVacinasException("\nPessoa já cadastrada.");
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
	    if (novaPessoa.getPais() == null) {
	    	mensagemErro += "Erro: o campo Pais nao pode ser nulo.";
	    }
	    
	    if(!mensagemErro.isEmpty()) {
	    	throw new ControleVacinasException(mensagemErro);
	    }
	}

	private void verificarSeJaVacinado (int idPessoa) throws ControleVacinasException  {
		VacinacaoRepository vacinacaoRepository = new VacinacaoRepository();
		if (!vacinacaoRepository.vacinacoesPorIdPessoa(idPessoa).isEmpty()) {
			throw new ControleVacinasException("Não é possível excluir pessoa que já foi vacinada!");
		}
	}
	
	public boolean excluir(int id) throws ControleVacinasException {
		this.verificarSeJaVacinado(id);
		return repository.excluir(id);
	}

	public Pessoa consultarPorId(int id) {
		
		return repository.consultarPorId(id);
	}
	
	public List<Pessoa> consultarTodas() {
		return repository.consultarTodos();
	}
	
	public List<Vacinacao> consultarVacinacoes(Integer id){
				VacinacaoRepository vacinacaoRepository = new VacinacaoRepository();
		return vacinacaoRepository.vacinacoesPorIdPessoa(id);
	}
	
	public List<Pessoa> consultarPesquisadores(){
		return repository.consultarPesquisadores();
	}
}

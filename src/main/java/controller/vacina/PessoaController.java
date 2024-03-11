package controller.vacina;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.x1.Pessoa;
import model.repository.x1.PessoaRepository;

@Path("/pessoa")
public class PessoaController {
	
	private PessoaRepository repository = new PessoaRepository();
	
	@POST
	@Path("/salvar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Pessoa salvar(Pessoa novaPessoa) {
		 return repository.salvar(novaPessoa);
	}
}

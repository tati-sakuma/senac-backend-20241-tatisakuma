package controller.carros;

import java.util.ArrayList;

import exception.vacina.CarrosException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.carros.Carro;
import model.seletor.carros.CarroSeletor;
import service.carros.CarroService;

@Path("/carro")
public class CarroController {
	//Comentário Final resolvido
	private CarroService service = new CarroService();
	
	@POST
	@Path("/filtro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Carro> consultarComFiltros(CarroSeletor seletor) throws CarrosException {
		return service.consultarComFiltros(seletor);
	}
	
}

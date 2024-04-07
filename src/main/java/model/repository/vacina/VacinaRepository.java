package model.repository.vacina;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.entity.vacina.Pessoa;
import model.entity.vacina.Vacina;
import model.repository.x1.Banco;
import model.repository.x1.BaseRepository;

public class VacinaRepository implements BaseRepository<Vacina> {

	@Override
	public Vacina salvar(Vacina novaVacina) {
		String query = "INSERT INTO Vacina (nome, ID_PAIS, id_pesquisador, DATA_INICIO_PESQUISA, estagio, media_avaliacao) VALUES (?,?, ?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setString(1, novaVacina.getNome());
			pstmt.setInt(2, novaVacina.getPaisOrigem().getIdPais());
			pstmt.setInt(3, novaVacina.getPesquisadorResponsavel().getId());
			pstmt.setDate(4, Date.valueOf(novaVacina.getDataInicioPesquisa()));
			pstmt.setInt(5, novaVacina.getEstagio());
			pstmt.setDouble(6, novaVacina.getMediaAvaliacao());
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();

			if (resultado.next()) {
				novaVacina.setId(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao salvar nova vacina");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}

		return novaVacina;
	}

	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM vacina WHERE id = " + id;
		try {
			if (stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir Vacina.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}

	@Override
	public boolean alterar(Vacina vacinaEditada) {
		boolean alterou = false;
		String query = "UPDATE exemplos.vacina SET NOME = ?, ID_PAIS = ? ,ID_PESQUISADOR = ?, "
				+ " DATA_INICIO_PESQUISA = ?, estagio = ?, media_avaliacao = ? WHERE ID = ?;";

		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setString(1, vacinaEditada.getNome());
			pstmt.setInt(2, vacinaEditada.getPaisOrigem().getIdPais());
			pstmt.setInt(3, vacinaEditada.getPesquisadorResponsavel().getId());
			pstmt.setDate(4, Date.valueOf(vacinaEditada.getDataInicioPesquisa()));
			pstmt.setInt(5, vacinaEditada.getEstagio());
			pstmt.setDouble(6, vacinaEditada.getMediaAvaliacao());

			pstmt.setInt(7, vacinaEditada.getId()); //where

			alterou = pstmt.executeUpdate() > 0;

		} catch (SQLException erro) {
			System.out.println("Erro ao editar vacina");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return alterou;
	}

	@Override
	public Vacina consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;
		Vacina vacina = new Vacina();
		String query = " SELECT * FROM vacina WHERE id = " + id;

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				VacinaRepository vacinaRepository = new VacinaRepository();
				PaisRepository paisRepository = new PaisRepository();

				vacina.setId(id);
				vacina.setNome(resultado.getString("NOME"));
				vacina.setPaisOrigem(paisRepository.consultarPorId(resultado.getInt("ID_PAIS")));
				vacina.setPesquisadorResponsavel(
						vacinaRepository.buscarPesquisadorID(resultado.getInt("ID_PESQUISADOR")));
				vacina.setDataInicioPesquisa(resultado.getDate("DATA_INICIO_PESQUISA").toLocalDate());
				vacina.setEstagio(resultado.getInt("estagio"));
				vacina.setMediaAvaliacao(resultado.getDouble("media_avaliacao"));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar consultar vacina com id (" + id + ")");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return vacina;
	}

	private Pessoa buscarPesquisadorID(int id) {
		PessoaRepository pessoaRepository = new PessoaRepository();
		return pessoaRepository.consultarPorId(id);
	}

	@Override
	public ArrayList<Vacina> consultarTodos() {
		ArrayList<Vacina> vacinas = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;
		String query = "SELECT * FROM vacina;";

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				Vacina vacina = new Vacina();
				VacinaRepository vacinaRepository = new VacinaRepository();
				PaisRepository paisRepository = new PaisRepository();

				vacina.setId(resultado.getInt("ID"));
				vacina.setNome(resultado.getString("NOME"));
				vacina.setPaisOrigem(paisRepository.consultarPorId(resultado.getInt("ID_PAIS")));
				vacina.setPesquisadorResponsavel(vacinaRepository.buscarPesquisadorID(resultado.getInt("ID_PESQUISADOR")));
				vacina.setEstagio(resultado.getInt("ESTAGIO"));
				vacina.setDataInicioPesquisa(resultado.getDate("DATA_INICIO_PESQUISA").toLocalDate());
				vacina.setMediaAvaliacao(resultado.getDouble("media_avaliacao"));

				vacinas.add(vacina);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar consultar todas as vacinas");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return vacinas;
	}

	public void atualizarMediaVacina(int idVacina, double media) {
		String query = " UPDATE vacina SET media_avaliacao = ? WHERE ID = ?; ";
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, query);
		ResultSet resultado = null;

		try {
			stmt.setDouble(1, media);
			stmt.setInt(2, idVacina);
			stmt.executeUpdate();

		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar média da vacina");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
	}
}

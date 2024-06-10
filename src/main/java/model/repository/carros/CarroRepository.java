package model.repository.carros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.carros.Carro;
import model.repository.Banco;
import model.repository.BaseRepository;
import model.seletor.carros.CarroSeletor;

public class CarroRepository implements BaseRepository<Carro> {

	@Override
	public Carro salvar(Carro novoCarro) {

		String query = " INSERT INTO carro " + " (ID_MONTADORA, MODELO, PLACA, ANO, VALOR) "
				+ " VALUES(?, ?, ?, ?, ?) ";

		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setInt(1, novoCarro.getMontadora().getId());
			pstmt.setString(2, novoCarro.getModelo());
			pstmt.setString(3, novoCarro.getPlaca());
			pstmt.setInt(4, novoCarro.getAno());
			pstmt.setDouble(5, novoCarro.getValor());

			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();

			if (resultado.next()) {
				novoCarro.setId(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao salvar novo carro");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}

		return novoCarro;
	}

	@Override
	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Carro entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Carro consultarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Carro> consultarTodos() {
		ArrayList<Carro> carros = new ArrayList<>();

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;
		String query = " select c.* from carro c ";

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				Carro carro = new Carro();
				MontadoraRepository montadoraRepository = new MontadoraRepository();

				carro.setId(resultado.getInt("ID"));
				carro.setModelo(resultado.getString("MODELO"));
				carro.setPlaca(resultado.getString("PLACA"));
				carro.setMontadora(montadoraRepository.consultarPorId(resultado.getInt("ID_MONTADORA")));
				carro.setAno(resultado.getInt("ANO"));
				carro.setValor(resultado.getDouble("VALOR"));

				carros.add(carro);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar todos com filtro");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return carros;
	}

	public ArrayList<Carro> consultarComFiltro(CarroSeletor seletor) {
		ArrayList<Carro> carros = new ArrayList<>();

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;
		String query = " select c.* from carro c " + "	inner join montadora m on m.ID = c.ID_MONTADORA ";

		boolean primeiro = true;

		if (seletor.getNomeMarca() != null && seletor.getNomeMarca().trim().length() > 0) {
			if (primeiro) {
				query += " WHERE ";
			} else {
				query += " AND ";
			}
			query += " UPPER(m.nome) LIKE UPPER('%" + seletor.getNomeMarca() + "%') ";
			primeiro = false;
		}

		if (seletor.getModelo() != null && seletor.getModelo().trim().length() > 0) {
			if (primeiro) {
				query += " WHERE ";
			} else {
				query += " AND ";
			}
			query += " UPPER(c.MODELO) LIKE UPPER('%" + seletor.getModelo() + "%') ";
			primeiro = false;

		}
		
		if (seletor.getAnoInicial() != null && seletor.getAnoFinal() != null) {
			if (seletor.getAnoInicial() > 0 && seletor.getAnoFinal() > 0) {
				if (primeiro) {
					query += " WHERE ";
				} else {
					query += " AND ";
				}

				query += " c.ANO BETWEEN " + seletor.getAnoInicial() + " AND " + seletor.getAnoFinal();
				primeiro = false;
			}
		}

		query += " ORDER BY c.MODELO ";

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				Carro carro = new Carro();
				MontadoraRepository montadoraRepository = new MontadoraRepository();

				carro.setId(resultado.getInt("ID"));
				carro.setModelo(resultado.getString("MODELO"));
				carro.setPlaca(resultado.getString("PLACA"));
				carro.setMontadora(montadoraRepository.consultarPorId(resultado.getInt("ID_MONTADORA")));
				carro.setAno(resultado.getInt("ANO"));
				carro.setValor(resultado.getDouble("VALOR"));

				carros.add(carro);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar consultar com filtro");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return carros;
	}
}

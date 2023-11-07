package br.com.fiap.megafarma.model.repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.megafarma.model.entity.Remedio;

public class RemedioRepository extends Repository {

	public static ArrayList<Remedio> findAllRemedio(){
		ArrayList<Remedio> remedios = new ArrayList<Remedio>();
		String sql = "select * from tb_remedios";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Remedio remedio = new Remedio();
					remedio.setId(rs.getLong("id"));
					remedio.setNome(rs.getString("nome"));
					remedio.setPreco(rs.getDouble("preco"));
					remedio.setDataDeFabricacao(rs.getDate("data_de_fabricacao").toLocalDate());
					remedio.setDataDeValidade(rs.getDate("data_de_validade").toLocalDate());
					remedios.add(remedio);
				}
				return remedios;
			} 
		} catch (SQLException e) {
			System.out.println("Erro ao listar remedios: " + e.getMessage());
		} finally {
			closeConnection();
		}
		return null;
	}
	
	public static Remedio findAllByIdRemedio(Long id) {
		String sql = "select * from tb_remedios where id=?";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
                Remedio remedio = new Remedio();
                remedio.setId(rs.getLong("id"));
                remedio.setNome(rs.getString("nome"));
                remedio.setPreco(rs.getDouble("preco"));
                remedio.setDataDeFabricacao(rs.getDate("data_de_fabricacao").toLocalDate());
                remedio.setDataDeValidade(rs.getDate("data_de_validade").toLocalDate());
                return remedio;
            }
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			closeConnection();
		}
		return null;
	}
	
	public static Remedio save(Remedio remedio) {
		String sql = "insert into tb_remedios(id,nome,preco,data_de_fabricacao,data_de_validade) "
				+ "values(null,?,?,?,?)";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, remedio.getNome());
			ps.setDouble(2, remedio.getPreco());
			ps.setDate(3, Date.valueOf(remedio.getDataDeFabricacao()));
			ps.setDate(4, Date.valueOf(remedio.getDataDeValidade()));
			if (ps.executeUpdate() > 0) {
				return remedio;
			}
		} catch (Exception e) {
			System.out.println("Erro ao inserir remedio: " + e.getMessage());
		} finally {
			closeConnection();
		}
		
		return null;
	}
	
	public static boolean delete(Long id) {
		String sql = "delete from tb_remedios where id=?";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setLong(1, id);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			closeConnection();
		}
		
		return false;
	}
	
	public static Remedio update(Remedio remedio) {
		String sql = "update tb_remedios "
				+ "set nome=?, preco=?, data_de_fabricacao=?, data_de_validade=? "
				+ "where id=?";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, remedio.getNome());
			ps.setDouble(2, remedio.getPreco());
			ps.setDate(3, Date.valueOf(remedio.getDataDeFabricacao()));
			ps.setDate(4, Date.valueOf(remedio.getDataDeValidade()));
			ps.setLong(5, remedio.getId());
			if(ps.executeUpdate() > 0) {
				return remedio;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			closeConnection();
		}
		return null;
	}
}

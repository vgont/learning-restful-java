package br.com.fiap.megafarma.model.repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.megafarma.model.entity.Cliente;

public class ClienteRepository extends Repository{
	public static ArrayList<Cliente> findAllClientes(){
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		String sql = "select * from tb_clientes";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setId(rs.getLong("id"));
					cliente.setNome(rs.getString("nome"));
					cliente.setCpf(rs.getString("cpf"));
					cliente.setDataDeNascimento(rs.getDate("data_de_nascimento").toLocalDate());					
					clientes.add(cliente);
				}
				return clientes;
			} 
		} catch (SQLException e) {
			System.out.println("Erro ao listar clientes: " + e.getMessage());
		} finally {
			closeConnection();
		}
		return null;
	}
	
	public static Cliente findAllByIdCliente(Long id) {
		String sql = "select * from tb_clientes where id=?";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getLong("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setDataDeNascimento(rs.getDate("data_de_nascimento").toLocalDate());
				return cliente;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			closeConnection();
		}
		
		return null;
	}
	
	public static Cliente save(Cliente cliente) {
		String sql = "insert into tb_clientes(id,nome,cpf,email,data_de_nascimento) "
				+ "values(null,?,?,?,?)";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getEmail());
			ps.setDate(4, Date.valueOf(cliente.getDataDeNascimento()));
			if (ps.executeUpdate() > 0) {
				return cliente;
			}
		} catch (Exception e) {
			System.out.println("Erro ao inserir cliente: " + e.getMessage());
		} finally {
			closeConnection();
		}
		
		return null;
	}
	
	public static boolean delete(Long id) {
		String sql = "delete from tb_clientes where id=?";
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
	
	public static Cliente update(Cliente cliente) {
		String sql = "update tb_clientes "
				+ "set nome=?, cpf=?, email=?, data_de_nascimento=? "
				+ "where id=?";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getEmail());
			ps.setDate(4, Date.valueOf(cliente.getDataDeNascimento()));
			ps.setLong(5, cliente.getId());
			if(ps.executeUpdate() > 0) {
				return cliente;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			closeConnection();
		}
		return null;
	}
	
	
}

package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RuaDAO {

    public void create(Rua rua) throws SQLException {
        String sql = "INSERT INTO rua (nome, bairro) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, rua.getNome());
            stmt.setString(2, rua.getBairro());
            stmt.executeUpdate();
        }
    }

    public List<Rua> readAll() throws SQLException {
        List<Rua> ruas = new ArrayList<>();
        String sql = "SELECT id, nome, bairro FROM rua";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Rua rua = new Rua();
                rua.setNome(rs.getString("nome"));
                rua.setBairro(rs.getString("bairro"));
                ruas.add(rua);
            }
        }
        return ruas;
    }

    public void update(long id, String novoBairro) throws SQLException {
        String sql = "UPDATE rua SET bairro = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoBairro);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        }
    }

    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM rua WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}

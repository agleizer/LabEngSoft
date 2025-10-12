package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalcadaDAO {

    public void create(Calcada calcada, long ruaId) throws SQLException {
        String sql = """
            INSERT INTO calcada (nome, avaliacao_media, latitude_ini, longitude_ini,
            latitude_fim, longitude_fim, rua_id, bairro)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, calcada.getNome());
            stmt.setDouble(2, calcada.getAvaliacaoMedia());
            stmt.setDouble(3, calcada.getLatitudeIni());
            stmt.setDouble(4, calcada.getLongitudeIni());
            stmt.setDouble(5, calcada.getLatitudeFim());
            stmt.setDouble(6, calcada.getLongitudeFim());
            stmt.setLong(7, ruaId);
            stmt.setString(8, calcada.getRua().getBairro());
            stmt.executeUpdate();
        }
    }

    public List<Calcada> readAll() throws SQLException {
        List<Calcada> calcadas = new ArrayList<>();
        String sql = "SELECT id, nome, avaliacao_media, bairro FROM calcada";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Calcada c = new Calcada();
                c.setNome(rs.getString("nome"));
                c.setAvaliacaoMedia(rs.getFloat("avaliacao_media"));
                calcadas.add(c);
            }
        }
        return calcadas;
    }

    public void updateNome(long id, String novoNome) throws SQLException {
        String sql = "UPDATE calcada SET nome = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoNome);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        }
    }

    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM calcada WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}

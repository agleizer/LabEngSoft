package app.repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import app.model.DatabaseConnection;
import app.model.Relatorio;

public class RelatorioDAO {

    public void create(Relatorio r) throws SQLException {
        String sql = "INSERT INTO relatorio (data_geracao_relatorio, area, autor, media_notas) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(r.getDataGeracaoRelatorio()));
            stmt.setString(2, r.getArea());
            stmt.setString(3, r.getAutor());
            stmt.setString(4, r.getMediaNotas());
            stmt.executeUpdate();
        }
    }

    public List<Relatorio> readAll() throws SQLException {
        List<Relatorio> relatorios = new ArrayList<>();
        String sql = "SELECT * FROM relatorio";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Relatorio r = new Relatorio();
                r.setArea(rs.getString("area"));
                r.setAutor(rs.getString("autor"));
                r.setMediaNotas(rs.getString("media_notas"));
                relatorios.add(r);
            }
        }
        return relatorios;
    }

    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM relatorio WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}

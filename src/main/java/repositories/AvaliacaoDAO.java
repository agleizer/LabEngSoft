package repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Avaliacao;
import model.DatabaseConnection;

public class AvaliacaoDAO {

    public void create(Avaliacao a, long calcadaId) throws SQLException {
        String sql = """
            INSERT INTO avaliacao (calcada_id, nota_geral, nota_idoso, nota_cego,
            nota_cadeirante, nota_carrinho, data_aval, comentario,
            presenca_piso_tatil, rebaixamento_guia, sem_obstaculos, iluminacao_noturna)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, calcadaId);
            stmt.setFloat(2, a.getNotaGeral());
            stmt.setFloat(3, a.getNotaIdoso());
            stmt.setFloat(4, a.getNotaCego());
            stmt.setFloat(5, a.getNotaCadeirante());
            stmt.setFloat(6, a.getNotaCarrinho());
            stmt.setDate(7, Date.valueOf(a.getDataAval()));
            stmt.setString(8, a.getComentario());
            stmt.setBoolean(9, a.isPresencaPisoTatil());
            stmt.setBoolean(10, a.isRebaixamentoGuia());
            stmt.setBoolean(11, a.isSemObstaculos());
            stmt.setBoolean(12, a.isIluminacaoNoturna());
            stmt.executeUpdate();
        }
    }

    public List<Avaliacao> readAll() throws SQLException {
        List<Avaliacao> list = new ArrayList<>();
        String sql = "SELECT id, nota_geral, comentario FROM avaliacao";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Avaliacao a = new Avaliacao();
                a.setNotaGeral(rs.getFloat("nota_geral"));
                a.setComentario(rs.getString("comentario"));
                list.add(a);
            }
        }
        return list;
    }

    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM avaliacao WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}

package app.repositories;

import app.model.DatabaseConnection;
import app.model.Rua;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RuaDAOTest {

    private RuaDAO dao;

    @BeforeEach
    void setup() {
        dao = new RuaDAO();
    }

    // ------------------------------------------------------------------
    // CREATE
    // ------------------------------------------------------------------
    @Test
    void deveCriarRuaComParametrosCorretos() throws Exception {
        Rua rua = new Rua("Paulista", "Bela Vista");

        Connection conn = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);

        when(conn.prepareStatement("INSERT INTO rua (nome, bairro) VALUES (?, ?)"))
                .thenReturn(stmt);

        try (var mocked = mockStatic(DatabaseConnection.class)) {
            mocked.when(DatabaseConnection::getConnection).thenReturn(conn);

            dao.create(rua);

            verify(conn).prepareStatement("INSERT INTO rua (nome, bairro) VALUES (?, ?)");
            verify(stmt).setString(1, "Paulista");
            verify(stmt).setString(2, "Bela Vista");
            verify(stmt).executeUpdate();
        }
    }

    // ------------------------------------------------------------------
    // READALL
    // ------------------------------------------------------------------
    @Test
    void deveLerTodasAsRuas() throws Exception {
        Connection conn = mock(Connection.class);
        Statement stmt = mock(Statement.class);
        ResultSet rs = mock(ResultSet.class);

        when(conn.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery("SELECT id, nome, bairro FROM rua")).thenReturn(rs);

        when(rs.next()).thenReturn(true, true, false);
        when(rs.getString("nome")).thenReturn("Rua A", "Rua B");
        when(rs.getString("bairro")).thenReturn("Centro", "Jardins");

        try (var mocked = mockStatic(DatabaseConnection.class)) {
            mocked.when(DatabaseConnection::getConnection).thenReturn(conn);

            var resultado = dao.readAll();

            assertEquals(2, resultado.size());
            assertEquals("Rua A", resultado.get(0).getNome());
            assertEquals("Centro", resultado.get(0).getBairro());
            assertEquals("Rua B", resultado.get(1).getNome());
            assertEquals("Jardins", resultado.get(1).getBairro());
        }
    }

    // ------------------------------------------------------------------
    // UPDATE
    // ------------------------------------------------------------------
    @Test
    void deveAtualizarBairroDaRua() throws Exception {
        Connection conn = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);

        when(conn.prepareStatement("UPDATE rua SET bairro = ? WHERE id = ?"))
                .thenReturn(stmt);

        try (var mocked = mockStatic(DatabaseConnection.class)) {
            mocked.when(DatabaseConnection::getConnection).thenReturn(conn);

            dao.update(10L, "Novo Bairro");

            verify(stmt).setString(1, "Novo Bairro");
            verify(stmt).setLong(2, 10L);
            verify(stmt).executeUpdate();
        }
    }

    // ------------------------------------------------------------------
    // DELETE
    // ------------------------------------------------------------------
    @Test
    void deveDeletarRuaPorId() throws Exception {
        Connection conn = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);

        when(conn.prepareStatement("DELETE FROM rua WHERE id = ?"))
                .thenReturn(stmt);

        try (var mocked = mockStatic(DatabaseConnection.class)) {
            mocked.when(DatabaseConnection::getConnection).thenReturn(conn);

            dao.delete(5L);

            verify(stmt).setLong(1, 5L);
            verify(stmt).executeUpdate();
        }
    }
}

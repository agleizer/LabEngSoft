package app.repositories;

import app.model.Avaliacao;
import app.model.DatabaseConnection;

import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AvaliacaoDAOTest {

    private AvaliacaoDAO dao;

    @BeforeEach
    void setup() {
        dao = new AvaliacaoDAO();
    }

    @AfterEach
    void cleanup() {
        // garante que nenhum static mock ficou pendurado
        try {
            MockedStatic<DatabaseConnection> m = mockStatic(DatabaseConnection.class);
            m.close();
        } catch (Throwable ignored) {}
    }

    // ------------------------------------------------------------
    // TESTE DO MÉTODO create()
    // ------------------------------------------------------------
    @Test
    void deveCriarAvaliacao() throws Exception {
        Avaliacao a = new Avaliacao();
        a.setNotaGeral(5f);
        a.setNotaIdoso(4f);
        a.setNotaCego(3f);
        a.setNotaCadeirante(2f);
        a.setNotaCarrinho(1f);
        a.setComentario("ok");
        a.setPresencaPisoTatil(true);
        a.setRebaixamentoGuia(false);
        a.setSemObstaculos(true);
        a.setIluminacaoNoturna(false);
        a.setDataAval(LocalDate.of(2024, 1, 1));

        Connection conn = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);

        try (MockedStatic<DatabaseConnection> dbMock = mockStatic(DatabaseConnection.class)) {

            dbMock.when(DatabaseConnection::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(stmt);

            dao.create(a, 99L);

            verify(conn).prepareStatement(startsWith("INSERT INTO avaliacao"));
            verify(stmt).setLong(1, 99L);
            verify(stmt).setFloat(2, 5f);
            verify(stmt).setFloat(3, 4f);
            verify(stmt).setFloat(4, 3f);
            verify(stmt).setFloat(5, 2f);
            verify(stmt).setFloat(6, 1f);
            verify(stmt).setDate(7, Date.valueOf("2024-01-01"));
            verify(stmt).setString(8, "ok");
            verify(stmt).setBoolean(9, true);
            verify(stmt).setBoolean(10, false);
            verify(stmt).setBoolean(11, true);
            verify(stmt).setBoolean(12, false);

            verify(stmt).executeUpdate();
        }
    }

    // ------------------------------------------------------------
    // TESTE DO readAll()
    // ------------------------------------------------------------
    @Test
    void deveLerTodasAvaliacoes() throws Exception {

        Connection conn = mock(Connection.class);
        Statement st = mock(Statement.class);
        ResultSet rs = mock(ResultSet.class);

        try (MockedStatic<DatabaseConnection> dbMock = mockStatic(DatabaseConnection.class)) {

            dbMock.when(DatabaseConnection::getConnection).thenReturn(conn);

            when(conn.createStatement()).thenReturn(st);
            when(st.executeQuery(anyString())).thenReturn(rs);

            when(rs.next()).thenReturn(true, true, false);
            when(rs.getFloat("nota_geral")).thenReturn(5f, 3f);
            when(rs.getString("comentario")).thenReturn("boa", "ok");

            List<Avaliacao> out = dao.readAll();

            assertEquals(2, out.size());
            assertEquals(5f, out.get(0).getNotaGeral());
            assertEquals("boa", out.get(0).getComentario());
            assertEquals(3f, out.get(1).getNotaGeral());
            assertEquals("ok", out.get(1).getComentario());
        }
    }

    // ------------------------------------------------------------
    // TESTE DO delete()
    // ------------------------------------------------------------
    @Test
    void deveDeletarAvaliacaoPorId() throws Exception {

        Connection conn = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);

        try (MockedStatic<DatabaseConnection> dbMock = mockStatic(DatabaseConnection.class)) {

            dbMock.when(DatabaseConnection::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(stmt);

            dao.delete(77L);

            verify(stmt).setLong(1, 77L);
            verify(stmt).executeUpdate();
        }
    }
}

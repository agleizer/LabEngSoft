package app.repositories;

import app.model.Calcada;
import app.model.DatabaseConnection;

import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalcadaDAOTest {

    private CalcadaDAO dao;

    @BeforeEach
    void setup() {
        dao = new CalcadaDAO();
    }

    // ------------------------------------------------------------
    // TESTE DO MÉTODO create()
    // ------------------------------------------------------------
    @Test
    void deveCriarCalcada() throws Exception {
        Calcada c = new Calcada();
        c.setNome("Trecho 1");
        c.setAvaliacaoMedia(4.5f);
        c.setLatitudeIni(1.1);
        c.setLongitudeIni(2.2);
        c.setLatitudeFim(3.3);
        c.setLongitudeFim(4.4);

        // rua necessária porque o DAO usa calcada.getRua().getBairro()
        var rua = new app.model.Rua();
        rua.setBairro("Centro");
        c.setRua(rua);

        Connection conn = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);

        try (MockedStatic<DatabaseConnection> dbMock = mockStatic(DatabaseConnection.class)) {

            dbMock.when(DatabaseConnection::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(stmt);

            dao.create(c, 77L);

            verify(conn).prepareStatement(startsWith("INSERT INTO calcada"));
            verify(stmt).setString(1, "Trecho 1");
            verify(stmt).setDouble(2, 4.5f);
            verify(stmt).setDouble(3, 1.1);
            verify(stmt).setDouble(4, 2.2);
            verify(stmt).setDouble(5, 3.3);
            verify(stmt).setDouble(6, 4.4);
            verify(stmt).setLong(7, 77L);
            verify(stmt).setString(8, "Centro");

            verify(stmt).executeUpdate();
        }
    }

    // ------------------------------------------------------------
    // TESTE DO readAll()
    // ------------------------------------------------------------
    @Test
    void deveLerTodasCalcadas() throws Exception {

        Connection conn = mock(Connection.class);
        Statement st = mock(Statement.class);
        ResultSet rs = mock(ResultSet.class);

        try (MockedStatic<DatabaseConnection> dbMock = mockStatic(DatabaseConnection.class)) {

            dbMock.when(DatabaseConnection::getConnection).thenReturn(conn);

            when(conn.createStatement()).thenReturn(st);
            when(st.executeQuery(anyString())).thenReturn(rs);

            // simula duas linhas
            when(rs.next()).thenReturn(true, true, false);
            when(rs.getString("nome")).thenReturn("A", "B");
            when(rs.getFloat("avaliacao_media")).thenReturn(4.0f, 3.0f);

            List<Calcada> out = dao.readAll();

            assertEquals(2, out.size());
            assertEquals("A", out.get(0).getNome());
            assertEquals(4.0f, out.get(0).getAvaliacaoMedia());
            assertEquals("B", out.get(1).getNome());
            assertEquals(3.0f, out.get(1).getAvaliacaoMedia());
        }
    }

    // ------------------------------------------------------------
    // TESTE DO updateNome()
    // ------------------------------------------------------------
    @Test
    void deveAtualizarNomeDaCalcada() throws Exception {

        Connection conn = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);

        try (MockedStatic<DatabaseConnection> dbMock = mockStatic(DatabaseConnection.class)) {

            dbMock.when(DatabaseConnection::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(stmt);

            dao.updateNome(99L, "Nova Calcada");

            verify(stmt).setString(1, "Nova Calcada");
            verify(stmt).setLong(2, 99L);
            verify(stmt).executeUpdate();
        }
    }

    // ------------------------------------------------------------
    // TESTE DO delete()
    // ------------------------------------------------------------
    @Test
    void deveDeletarCalcadaPorId() throws Exception {

        Connection conn = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);

        try (MockedStatic<DatabaseConnection> dbMock = mockStatic(DatabaseConnection.class)) {

            dbMock.when(DatabaseConnection::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(stmt);

            dao.delete(44L);

            verify(stmt).setLong(1, 44L);
            verify(stmt).executeUpdate();
        }
    }
}

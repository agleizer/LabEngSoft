package app.repositories;

import app.model.DatabaseConnection;
import app.model.Relatorio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RelatorioDAOTest {

    private RelatorioDAO dao;

    @BeforeEach
    void setup() {
        dao = new RelatorioDAO();
    }

    // ============================================================
    // TESTE DO MÉTODO create()
    // ============================================================
    @Test
    void deveCriarRelatorio() throws Exception {
        Relatorio r = new Relatorio();
        r.setDataGeracaoRelatorio(LocalDate.of(2024, 1, 1));
        r.setArea("Mobilidade");
        r.setAutor("Gilberto");
        r.setMediaNotas("4.5");

        Connection conn = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);

        try (MockedStatic<DatabaseConnection> dbMock = mockStatic(DatabaseConnection.class)) {

            dbMock.when(DatabaseConnection::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(stmt);

            dao.create(r);

            verify(conn).prepareStatement(startsWith("INSERT INTO relatorio"));
            verify(stmt).setDate(1, Date.valueOf("2024-01-01"));
            verify(stmt).setString(2, "Mobilidade");
            verify(stmt).setString(3, "Gilberto");
            verify(stmt).setString(4, "4.5");

            verify(stmt).executeUpdate();
        }
    }

    // ============================================================
    // TESTE DO readAll()
    // ============================================================
    @Test
    void deveLerTodosRelatorios() throws Exception {

        Connection conn = mock(Connection.class);
        Statement stmt = mock(Statement.class);
        ResultSet rs = mock(ResultSet.class);

        try (MockedStatic<DatabaseConnection> dbMock = mockStatic(DatabaseConnection.class)) {

            dbMock.when(DatabaseConnection::getConnection).thenReturn(conn);

            when(conn.createStatement()).thenReturn(stmt);
            when(stmt.executeQuery(anyString())).thenReturn(rs);

            // simula duas linhas
            when(rs.next()).thenReturn(true, true, false);
            when(rs.getString("area")).thenReturn("Transporte", "Saúde");
            when(rs.getString("autor")).thenReturn("João", "Maria");
            when(rs.getString("media_notas")).thenReturn("4.0", "3.7");

            List<Relatorio> out = dao.readAll();

            assertEquals(2, out.size());

            assertEquals("Transporte", out.get(0).getArea());
            assertEquals("João", out.get(0).getAutor());
            assertEquals("4.0", out.get(0).getMediaNotas());

            assertEquals("Saúde", out.get(1).getArea());
            assertEquals("Maria", out.get(1).getAutor());
            assertEquals("3.7", out.get(1).getMediaNotas());
        }
    }

    // ============================================================
    // TESTE DO delete()
    // ============================================================
    @Test
    void deveDeletarRelatorioPorId() throws Exception {

        Connection conn = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);

        try (MockedStatic<DatabaseConnection> dbMock = mockStatic(DatabaseConnection.class)) {

            dbMock.when(DatabaseConnection::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(stmt);

            dao.delete(55L);

            verify(stmt).setLong(1, 55L);
            verify(stmt).executeUpdate();
        }
    }
}

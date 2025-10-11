package model;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("Connected to PostgreSQL!");

            // --- CREATE Rua ---
            String insertRua = "INSERT INTO rua(nome, bairro) VALUES (?, ?)";
            long ruaId = 0;
            try (PreparedStatement pstmt = conn.prepareStatement(insertRua, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, "Rua das Flores");
                pstmt.setString(2, "Centro");
                int rows = pstmt.executeUpdate();
                System.out.println("Rows inserted into rua: " + rows);

                ResultSet keys = pstmt.getGeneratedKeys();
                if (keys.next()) ruaId = keys.getLong(1);
            }

            // --- CREATE Calcada ---
            String insertCalcada = "INSERT INTO calcada(nome, avaliacao_media, latitude_ini, longitude_ini, latitude_fim, longitude_fim, rua_id, bairro) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmtCalc = conn.prepareStatement(insertCalcada)) {
                pstmtCalc.setString(1, "Calçada Central");
                pstmtCalc.setFloat(2, 4.5f);
                pstmtCalc.setDouble(3, -23.5);
                pstmtCalc.setDouble(4, -46.6);
                pstmtCalc.setDouble(5, -23.6);
                pstmtCalc.setDouble(6, -46.7);
                pstmtCalc.setLong(7, ruaId);
                pstmtCalc.setString(8, "Centro");
                int calcRows = pstmtCalc.executeUpdate();
                System.out.println("Rows inserted into calcada: " + calcRows);
            }

            // --- READ Calcadas ---
            String selectCalcadas = "SELECT id, nome, avaliacao_media, bairro FROM calcada";
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(selectCalcadas);
                while (rs.next()) {
                    System.out.println("Calcada ID: " + rs.getLong("id") +
                                       ", Nome: " + rs.getString("nome") +
                                       ", Avaliacao Media: " + rs.getFloat("avaliacao_media") +
                                       ", Bairro: " + rs.getString("bairro"));
                }
            }

            // --- UPDATE Rua ---
            String updateRua = "UPDATE rua SET bairro = ? WHERE nome = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateRua)) {
                pstmt.setString(1, "Bairro Atualizado");
                pstmt.setString(2, "Rua das Flores");
                int updated = pstmt.executeUpdate();
                System.out.println("Rows updated in rua: " + updated);
            }

            // --- DELETE Rua ---
            String deleteRua = "DELETE FROM rua WHERE nome = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteRua)) {
                pstmt.setString(1, "Rua das Flores");
                int deleted = pstmt.executeUpdate();
                System.out.println("Rows deleted in rua: " + deleted);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

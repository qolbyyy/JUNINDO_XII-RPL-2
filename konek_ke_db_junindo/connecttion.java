import java.sql.*;
import java.util.Scanner;

public class DatabaseConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/kelola_siswa_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            System.out.println("Koneksi ke database berhasil.");

            int choice;
            do {
                displayMenu();
                System.out.print("Masukkan nomor tindakan: ");
                choice = scanner.nextInt();
                scanner.nextLine();  // Mengonsumsi newline

                switch (choice) {
                    case 1:
                        tambahSiswa(connection);
                        break;
                    case 2:
                        perbaruiDataSiswa(connection);
                        break;
                    case 3:
                        hapusSiswa(connection);
                        break;
                    case 4:
                        tampilkanDaftarSiswa(connection);
                        break;
                    case 5:
                        System.out.println("Keluar dari aplikasi.");
                        break;
                    default:
                        System.out.println("Anda ngacapruk. Silakan coba lagi.");
                }
            } while (choice != 5);
        } catch (SQLException e) {
            System.err.println("gabisa konek mas gara gara " + e.getMessage());
        }
    }

    private static void displayMenu() {
        System.out.println("Pilih tindakan:");
        System.out.println(" 1. Tambah Siswa");
        System.out.println(" 2. Perbarui Data Siswa");
        System.out.println(" 3. Hapus Siswa");
        System.out.println(" 4. Tampilkan Daftar Siswa");
        System.out.println(" 5. Keluar");
    }

    private static void tambahSiswa(Connection connection) {
        try {
            System.out.println("==================");
            System.out.println("| Menambah Siswa |");
            System.out.println("==================");
            System.out.print("Masukkan Nama Siswa: ");
            String nama = scanner.nextLine();
            System.out.print("Masukkan NIS Siswa: ");
            String nis = scanner.nextLine();
            System.out.print("Masukkan Jurusan Siswa: ");
            String jurusan = scanner.nextLine();

            String query = "INSERT INTO siswa (nama, nis, jurusan) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nama);
                preparedStatement.setString(2, nis);
                preparedStatement.setString(3, jurusan);
                preparedStatement.executeUpdate();
                System.out.println("# Data siswa berhasil ditambahkan. #");
            }
        } catch (SQLException e) {
            System.err.println("Gagal menambahkan data siswa: " + e.getMessage());
        }
    }

    private static void perbaruiDataSiswa(Connection connection) {
        try {
            System.out.println("### Perbarui Data Siswa ###");
            System.out.print("Masukkan NIS siswa yang ingin diperbarui: ");
            String nisToUpdate = scanner.nextLine();

            // Meminta input pengguna untuk data baru
            System.out.print("Masukkan Nama Baru: ");
            String newNama = scanner.nextLine();
            System.out.print("Masukkan NIS Baru: ");
            String newNis = scanner.nextLine();
            System.out.print("Masukkan Jurusan Baru: ");
            String newJurusan = scanner.nextLine();

            String updateQuery = "UPDATE siswa SET nama = ?, nis = ?, jurusan = ? WHERE nis = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, newNama);
                preparedStatement.setString(2, newNis);
                preparedStatement.setString(3, newJurusan);
                preparedStatement.setString(4, nisToUpdate);

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Data siswa berhasil diperbarui.");
                } else {
                    System.out.println("Gagal memperbarui data siswa. NIS tidak ditemukan.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Gagal memperbarui data siswa: " + e.getMessage());
        }
    }

    private static void hapusSiswa(Connection connection) {
        try {
            System.out.println("### Hapus Data Siswa ###");
            System.out.print("Masukkan NIS siswa yang ingin dihapus: ");
            String nisToDelete = scanner.nextLine();

            String deleteQuery = "DELETE FROM siswa WHERE nis = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, nisToDelete);

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Data siswa dengan NIS " + nisToDelete + " berhasil dihapus.");
                } else {
                    System.out.println("Gagal menghapus data siswa. NIS tidak ditemukan.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Gagal menghapus data siswa: " + e.getMessage());
        }
    }

    private static void tampilkanDaftarSiswa(Connection connection) {
        try {
            System.out.println("### Daftar Siswa ###");

            String query = "SELECT * FROM siswa";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                System.out.println("-----------------------------------------------------------");
                System.out.printf("%-5s | %-15s | %-10s | %-15s%n", "ID", "NIS", "Nama", "Jurusan");
                System.out.println("-----------------------------------------------------------");

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nis = resultSet.getString("nis");
                    String nama = resultSet.getString("nama");
                    String jurusan = resultSet.getString("jurusan");

                    System.out.printf("%-5d | %-15s | %-10s | %-15s%n", id, nis, nama, jurusan);
                }
            }
        } catch (SQLException e) {
            System.err.println("Gagal menampilkan daftar siswa: " + e.getMessage());
        }
    }
}

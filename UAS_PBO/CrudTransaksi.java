import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class CrudTransaksi extends JFrame {
    private JComboBox<String> cbKonsumen, cbBarang;
    private JTextField tfJumlah, tfTanggal;
    private JButton btnTambah, btnUpdate, btnHapus, btnTampilkan;
    private JTable table;
    private DefaultTableModel tableModel;

    public CrudTransaksi() {
        setTitle("CRUD Transaksi");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel Input
        JPanel panelInput = new JPanel(new GridLayout(5, 2, 5, 5));
        panelInput.add(new JLabel("Konsumen:"));
        cbKonsumen = new JComboBox<>();
        panelInput.add(cbKonsumen);

        panelInput.add(new JLabel("Barang:"));
        cbBarang = new JComboBox<>();
        panelInput.add(cbBarang);

        panelInput.add(new JLabel("Jumlah:"));
        tfJumlah = new JTextField();
        panelInput.add(tfJumlah);

        panelInput.add(new JLabel("Tanggal (YYYY-MM-DD):"));
        tfTanggal = new JTextField();
        panelInput.add(tfTanggal);

        // Panel Tombol
        JPanel panelButton = new JPanel(new GridLayout(1, 4, 5, 5));
        btnTambah = new JButton("Tambah");
        btnUpdate = new JButton("Update");
        btnHapus = new JButton("Hapus");
        btnTampilkan = new JButton("Tampilkan");

        panelButton.add(btnTambah);
        panelButton.add(btnUpdate);
        panelButton.add(btnHapus);
        panelButton.add(btnTampilkan);

        add(panelInput, BorderLayout.NORTH);
        add(panelButton, BorderLayout.SOUTH);

        // Tabel
        tableModel = new DefaultTableModel(new String[]{"ID Transaksi", "Konsumen", "Barang", "Jumlah", "Tanggal"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Tambahkan Event Listener
        btnTambah.addActionListener(e -> tambahTransaksi());
        btnUpdate.addActionListener(e -> updateTransaksi());
        btnHapus.addActionListener(e -> hapusTransaksi());
        btnTampilkan.addActionListener(e -> tampilkanTransaksi());

        // Muat Data Konsumen dan Barang
        loadKonsumen();
        loadBarang();

        // Tampilkan data transaksi saat aplikasi dibuka
        tampilkanTransaksi();
    }

    // Fungsi untuk menghubungkan ke database
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/toko", "root", ""); // Ganti dengan kredensial Anda
    }

    // Memuat data konsumen ke dalam comboBox
    private void loadKonsumen() {
        try (Connection conn = getConnection()) {
            String sql = "SELECT id_konsumen, nama_konsumen FROM data_konsumen";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                cbKonsumen.addItem(rs.getInt("id_konsumen") + " - " + rs.getString("nama_konsumen"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Memuat data barang ke dalam comboBox
    private void loadBarang() {
        try (Connection conn = getConnection()) {
            String sql = "SELECT id_barang, nama_barang FROM data_barang";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                cbBarang.addItem(rs.getInt("id_barang") + " - " + rs.getString("nama_barang"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Menambah transaksi ke dalam database
    private void tambahTransaksi() {
        try (Connection conn = getConnection()) {
            String selectedKonsumen = (String) cbKonsumen.getSelectedItem();
            String selectedBarang = (String) cbBarang.getSelectedItem();
            String jumlahText = tfJumlah.getText();
            String tanggal = tfTanggal.getText();

            if (selectedKonsumen == null || selectedBarang == null || jumlahText.isEmpty() || tanggal.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua data harus diisi!");
                return;
            }

            // Mengambil ID dari pilihan di comboBox
            int idKonsumen = Integer.parseInt(selectedKonsumen.split(" - ")[0]);
            int idBarang = Integer.parseInt(selectedBarang.split(" - ")[0]);
            int jumlah = Integer.parseInt(jumlahText);

            // Menyimpan transaksi ke database
            String sql = "INSERT INTO data_transaksi (id_konsumen, id_barang, jumlah, tanggal_transaksi) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idKonsumen);
            stmt.setInt(2, idBarang);
            stmt.setInt(3, jumlah);
            stmt.setString(4, tanggal);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
            tampilkanTransaksi(); // Menampilkan ulang transaksi setelah penambahan
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menambahkan transaksi!");
        }
    }

    // Menampilkan semua transaksi dari database ke dalam tabel
    private void tampilkanTransaksi() {
        try (Connection conn = getConnection()) {
            String sql = "SELECT t.id_transaksi, k.nama_konsumen AS konsumen, b.nama_barang AS barang, t.jumlah, t.tanggal_transaksi " +
                         "FROM data_transaksi t " +
                         "JOIN data_konsumen k ON t.id_konsumen = k.id_konsumen " +
                         "JOIN data_barang b ON t.id_barang = b.id_barang";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Kosongkan tabel yang ada
            tableModel.setRowCount(0);

            // Tambahkan data transaksi ke dalam tabel
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("id_transaksi"),
                        rs.getString("konsumen"),
                        rs.getString("barang"),
                        rs.getInt("jumlah"),
                        rs.getString("tanggal_transaksi")
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menampilkan transaksi!");
        }
    }

    // Update transaksi (belum diimplementasikan)
    private void updateTransaksi() {
        // Implementasi untuk update transaksi
        JOptionPane.showMessageDialog(this, "Fitur Update belum diimplementasikan!");
    }

    // Hapus transaksi berdasarkan ID yang dipilih (belum diimplementasikan)
    private void hapusTransaksi() {
        // Implementasi untuk hapus transaksi
        JOptionPane.showMessageDialog(this, "Fitur Hapus belum diimplementasikan!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CrudTransaksi().setVisible(true));
    }
}
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class kontak {
    public static void main(String[] args) {
        ArrayList<Contact> contactList = loadContactsFromFile(); // Memuat kontak dari berkas saat aplikasi dimulai
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Pilih operasi yang ingin Anda lakukan:");
            System.out.println("1. Tambah Kontak");
            System.out.println("2. Hapus Kontak");
            System.out.println("3. Tampilkan Kontak");
            System.out.println("4. Keluar");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Masukkan Nama:");
                    String name = scanner.next();
                    System.out.println("Masukkan Email:");
                    String email = scanner.next();
                    System.out.println("Masukkan Nomor Telepon:");
                    String phoneNumber = scanner.next();

                    Contact newContact = new Contact(name, email, phoneNumber);
                    contactList.add(newContact);
                    System.out.println("Kontak berhasil ditambahkan!");
                    break;
                case 2:
                    System.out.println("Masukkan nama kontak yang ingin dihapus:");
                    String nameToDelete = scanner.next();

                    Iterator<Contact> iterator = contactList.iterator();
                    while (iterator.hasNext()) {
                        Contact contact = iterator.next();
                        if (contact.getName().equals(nameToDelete)) {
                            iterator.remove();
                            System.out.println("Kontak berhasil dihapus!");
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("Daftar Kontak:");
                    for (Contact contact : contactList) {
                        System.out.println("Nama: " + contact.getName());
                        System.out.println("Email: " + contact.getEmail());
                        System.out.println("Nomor Telepon: " + contact.getPhoneNumber());
                        System.out.println();
                    }
                    break;
                case 4:
                    saveContactsToFile(contactList); // Menyimpan kontak ke berkas sebelum keluar
                    exit = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }

        scanner.close();
    }

    private static ArrayList<Contact> loadContactsFromFile() {
        ArrayList<Contact> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("contacts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    Contact contact = new Contact(data[0], data[1], data[2]);
                    contacts.add(contact);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    private static void saveContactsToFile(ArrayList<Contact> contacts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contacts.txt"))) {
            for (Contact contact : contacts) {
                writer.write(contact.getName() + "," + contact.getEmail() + "," + contact.getPhoneNumber());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Contact {
    private String name;
    private String email;
    private String phoneNumber;

    public Contact(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

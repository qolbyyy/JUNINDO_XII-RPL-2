package Kalkulator;
import java.util.Scanner;

public class kalkulator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int a;
        float a1, a2, hasil;
        String text;
        boolean ContinueCalculating = true;

        while (ContinueCalculating) {
            System.out.println("-- KALKULATOR SEDERHANA --");
            System.out.println("");
            System.out.println("1. (+) Tambah");
            System.out.println("2. (-) Kurang");
            System.out.println("3. (x) Kali");
            System.out.println("4. (/) Bagi");
            System.out.println("5. (%) Sisa Hasil Bagi");
            System.out.print("Masukkan pilihan anda : ");
            a = input.nextInt();

            if (a == 1) {
                text = "Tambah";
            } else if (a == 2) {
                text = "Kurang";
            } else if (a == 3) {
                text = "Kali";
            } else if (a == 4) {
                text = "Bagi";
            } else if (a == 5) {
                text = "Sisa Hasil Bagi";
            } else {
                text = "Tidak ada";
            }

            System.out.println("anda memilih aritmatika " + text);

            System.out.print("Masukkan angka pertama : ");
            a1 = input.nextFloat();
            System.out.print("Masukkan angka kedua : ");
            a2 = input.nextFloat();

            if (text.equals("Tambah")) {
                hasil = a1 + a2;
            } else if (text.equals("Kurang")) {
                hasil = a1 - a2;
            } else if (text.equals("Kali")) {
                hasil = a1 * a2;
            } else if (text.equals("Bagi")) {
                hasil = a1 / a2;
            } else if (text.equals("Sisa Hasil Bagi")) {
                hasil = a1 % a2;
            } else {
                hasil = 0;
            }

            System.out.println("Hasil: " + hasil);

            System.out.println("Apakah ingin kembali (y/n) : ");
            char choice = input.next().charAt(0);
            if (choice != 'y') {
                ContinueCalculating = false;
            }
        }

        input.close();
    }
}
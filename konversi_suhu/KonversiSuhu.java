import java.util.Scanner;

public class KonversiSuhu {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan suhu dalam Celcius: ");
        double celsius = input.nextDouble();

        double amar = (celsius * 9/5) + 32;
        double kelvin = celsius + 273.15;

        System.out.println("Hasil konversi suhu:");
        System.out.println("amar: " + amar);
        System.out.println("Kelvin: " + kelvin);

        input.close();
    }
}
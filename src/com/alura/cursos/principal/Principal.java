package com.alura.cursos.principal;

import com.alura.cursos.calculos.Conversor;

import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    ********************************************
                    Sea bienvenido/a al Conversor de Moneda =)

                    1) Dólar =>> Peso argentino
                    2) Peso argentino =>> Dólar
                    3) Dólar =>> Real brasileño
                    4) Real brasileño =>> Dólar
                    5) Dólar =>> Peso colombiano
                    6) Peso colombiano =>> Dólar
                    7) Salir
                    Elija una opción válida
                    ********************************************
                    """);

            int opcion = scanner.nextInt();
            if (opcion == 7) {
                System.out.println("Gracias por usar el Conversor de Monedas. ¡Hasta pronto!");
                break;
            }

            System.out.println("Ingrese el valor que deseas convertir: ");
            double valor = scanner.nextDouble();

            // Variables para los códigos de moneda
            String codigoMonetario,codigoCambio ;

            // Configuración de los códigos de moneda según la opción seleccionada
            switch (opcion) {
                case 1 -> {
                    codigoMonetario = "USD";
                    codigoCambio = "ARS";
                }
                case 2 -> {
                    codigoMonetario = "ARS";
                    codigoCambio = "USD";
                }
                case 3 -> {
                    codigoMonetario = "USD";
                    codigoCambio = "BRL";
                }
                case 4 -> {
                    codigoMonetario = "BRL";
                    codigoCambio = "USD";
                }
                case 5 -> {
                    codigoMonetario = "USD";
                    codigoCambio = "COP";
                }
                case 6 -> {
                    codigoMonetario = "COP";
                    codigoCambio = "USD";
                }
                default -> {
                    System.out.println("Opción inválida. Por favor, elija una opción válida.");
                    continue;
                }
            }

            // Llamar a la clase Conversor y realizar la conversión
            Conversor conversor = new Conversor(codigoMonetario, codigoCambio);
            conversor.obtenerCambio(); // Obtener la tasa de cambio desde la API
            double valorConvertido = valor * conversor.getValorCambio();

            // Mostrar el resultado final
            System.out.printf("El valor %.2f [%s] corresponde al valor final de =>>> %.2f [%s]%n",
                    valor, codigoMonetario, valorConvertido, codigoCambio);
        }

        scanner.close();
    }
}

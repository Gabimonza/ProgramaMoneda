package com.desafioapi;

import java.util.Map;
import java.util.Scanner;

import com.desafioapi.conversion.ConversionMoneda;
import com.desafioapi.conversion.RespuestaMonedas;
import com.desafioapi.service.ApiService;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        ApiService api = new ApiService();
        String monedaBase = "USD";

        while (true) {
            System.out.println("""
                #####################################################
                Ingrese su consulta
                #####################################################
                1) Ver la conversión del dólar con todas las demás
                2) Comparar su moneda con una en específico
                9) Salir
                #####################################################
                """);

            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    RespuestaMonedas data = api.obtenerConversiones(monedaBase);
                    System.out.println("Tasas de cambio del dólar (USD):");
                    for (Map.Entry<String, Double> entry : data.conversion_rates.entrySet()) {
                        System.out.println(monedaBase + " => " + entry.getKey() + ": " + entry.getValue());
                    }
                }
                case 2 -> {
                    System.out.print("Ingrese su moneda (ej: EUR): ");
                    String moneda = sc.nextLine().toUpperCase();
                    System.out.print("Ingrese la moneda de destino (ej: JPY): ");
                    String moneda2 = sc.nextLine().toUpperCase();
                    System.out.print("Ingrese la cantidad: ");
                    double cantidad = sc.nextDouble();
                    sc.nextLine();

                    ConversionMoneda conversion = api.convertirMoneda(moneda, moneda2, cantidad);
                    System.out.println(cantidad + " " + moneda + " equivale a " + conversion.conversion_result + " " + moneda2);
                }
                case 9 -> {
                    System.out.println("Saliendo...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }
}

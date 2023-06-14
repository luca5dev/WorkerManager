package application;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Digite o nome do departamento: ");
        String departmentName = sc.nextLine();
        System.out.println("Digite as informações do trabalhador:");
        System.out.print("Nome: ");
        String workerName = sc.nextLine();
        System.out.print("Level: ");
        String workerLevel = sc.nextLine();
        System.out.print("Salário base: ");
        double baseSalary = sc.nextDouble();
        Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), baseSalary, new Department(departmentName));

        System.out.print("Quantos contratos o trabalhador vai ter? ");
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            System.out.println("Digite os dados do contrato número #"+ i +": ");
            System.out.print("Data (DD/MM/AAAA): ");
            Date contractDate = sdf.parse(sc.next());
            System.out.print("Valor por Hora: ");
            double valuePerHour = sc.nextDouble();
            System.out.print("Duração (Em horas): ");
            int hours = sc.nextInt();
            HourContract contract = new HourContract(contractDate, valuePerHour,hours);
            worker.addContract(contract);
        }

        System.out.println();
        System.out.print("Diite o mês e ano para calcular o salário (MM/AAAA): ");
        String monthAndYear = sc.next();
        int month = Integer.parseInt(monthAndYear.substring(0,2));
        int year = Integer.parseInt(monthAndYear.substring(3));

        System.out.println("Nome: " + worker.getName());
        System.out.println("Departamento: " + worker.getDepartment().getName());
        System.out.println("Valor salário final do mês e ano informado" + monthAndYear + ": " + String.format("%.2f", worker.income(year, month)));

        sc.close();
    }
}
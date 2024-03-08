package ru.croc.javaschool2024.soloveva.task1;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите координату х вершины №1: ");
        double xA = sc.nextDouble();
        System.out.println("Введите координату y вершины №1: ");
        double yA = sc.nextDouble();
        System.out.println("Введите координату х вершины №2: ");
        double xB = sc.nextDouble();
        System.out.println("Введите координату y вершины №2: ");
        double yB = sc.nextDouble();
        System.out.println("Введите координату x вершины №3: ");
        double xC = sc.nextDouble();
        System.out.println("Введите координату y вершины №3: ");
        double yC = sc.nextDouble();

        Point A = new Point(xA,yA);
        Point B = new Point(xB,yB);
        Point C = new Point(xC,yC);
        double area = triangleArea(A, B, C);

        System.out.println("Площадь треугольника: " + area);
    }
    public static double triangleArea(Point A, Point B, Point C) {
        return Math.abs((A.x()*(B.y()-C.y()) + B.x()*(C.y()-A.y()) + C.x()*(A.y()-B.y()))/2);
    }
}
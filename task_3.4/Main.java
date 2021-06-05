/*
Задание: Сделайте функцию, которая параметрами принимает 2 числа. Если их сумма больше 10 - пусть функция вернет true, а если нет - false.

*/


public class Main {
    public static boolean sumover10(int a, int b) {
        if ((a+b)>10) return true;
        else return false;
    }

    public static void main(String[] args) {
        int a=5,b=14;
        System.out.println(sumover10(a,b));

    }
}

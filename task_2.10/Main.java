/*
Задание: Дан массив с числами. Проверьте, есть ли в нем два одинаковых числа подряд. Если есть - выведите 'да', а если нет - выведите 'нет'.
*/

public class Main {
    public static void main(String[] args) {
        int[] myarray={2,5,8,9,9,3};
        boolean result = false;

        for (int i = 1; i < myarray.length; i++) {
            if (myarray[i]==myarray[i-1]) result=true;
        }
        if (result) System.out.println("да");
        else System.out.println("нет");

    }
}

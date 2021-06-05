/*
Задание: Дан массив с числами. Проверьте, что в этом массиве есть число 5. Если есть - выведите 'да', а если нет - выведите 'нет'.
*/

public class Main {
    public static void main(String[] args) {
        int[] myarray = {2,4,5,9};
        boolean result = false;
        for (int i = 0; i < myarray.length; i++) {
            if (myarray[i]==5) result=true;
        }
        if (result) System.out.println("да");
        else System.out.println("нет");

    }
}

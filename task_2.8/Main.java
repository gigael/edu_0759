/*
Задание: Дан массив с элементами [2, 3, 4, 5]. С помощью цикла for найдите произведение элементов этого массива.
*/

public class Main {
    public static void main(String[] args) {
        int[] myarray = {2,3,4,5};
        int result=1;
        for (int i = 0; i < myarray.length; i++) {
            result=result*myarray[i];
        }
        System.out.println("Произведение элементов массива равно "+result);

    }
}

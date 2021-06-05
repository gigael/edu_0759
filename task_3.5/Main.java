/*
Задание: Дан массив с числами. Выведите последовательно его элементы используя рекурсию и не используя цикл.
*/


public class Main {
    public static void elements(int[] num, int length) {
        System.out.println(num[num.length-length]);
        if (length<2) return            ;
        else elements(num,length-1);
    }
    public static void main(String[] args) {

        int[] num = {4,2,6,2,65};
        int length=num.length;
        elements(num, length);

    }
}

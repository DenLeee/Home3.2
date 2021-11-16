import java.util.Arrays;
import java.util.Random;

/*      число от 1 до 20 - поле живое
        0 - поле мертвое
*/

public class Home32 {
    public static void main(String[] args) {
        int[][] life = new int[10][10];   // начальное игровое поле
        int[][] pokolenie;   // поле где будет отображаться после прохода поколения
        int iter = new Random().nextInt(19) + 1;   // количество итераций

        for (int i = 0; i < life.length; i++) {    // заполняем массив случаными числами
            for (int j = 0; j < life[i].length; j++) {
                life[i][j] = new Random().nextInt(20);
            }
        }

        Arrays.stream(life).map(Arrays::toString).forEach(System.out::println);
        System.out.println();
        System.out.println("Количество шагов " + iter);
        System.out.println();

        do {                    // цикл для прохождения поколений
           pokolenie = iterArray(life);
           iter--;
        } while (iter > 0);

        Arrays.stream(pokolenie).map(Arrays::toString).forEach(System.out::println);
    }

    static int countAround(int i, int j, int[][] array) {          //метод подсчёта живых клеток у выбраной ячейки массива
        int alive = 0;           //  счётчик живых клеток
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (!((x == 0) & (y == 0))) {
                    if (array[(i + x + array.length) % array.length][(j + y + array.length) % array.length] != 0)
                        alive++;
                }
            }
        }
        return alive;
    }

    static int[][] iterArray(int[][] array) {                   // метод итерации, прохождения одного поколения
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if ((array[i][j] == 0) & (countAround(i,j,array) == 3))    // если вокруг мертвой клетки 3 живуе она становиться живой
                    array[i][j] = new Random().nextInt(19) + 1;
                if ( !((array[i][j] != 0) & ((countAround(i,j,array) == 2) | (countAround(i,j,array) == 3))))  // если вокруг живой клетки 2 или 3 живые она дальше вижёт
                    array[i][j] = 0;                                                                           // иначе умирает
            }
        }
        return array;
    }
}


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static boolean b=true;
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите номиналы (через пробел):  ");
        String nominal[] = input.nextLine().split(" ");
        int nominalArr[] = new int[nominal.length];
        for (int i = 0; i < nominal.length; i++) {
            try {
                nominalArr[i] = Integer.parseInt(nominal[i]);
                if (nominalArr[i]<=0) {
                    System.out.println("Неверно введён номинал");System.exit(0);
                }
            }catch (NumberFormatException e){
                System.out.println("Неверно введён номинал");System.exit(0);
            }
        }
        int nominals[] = transform(nominalArr);
        int value=0;
        try{
            System.out.println("Введите купюру:  ");
            value = input.nextInt();
            if (value<=0) {
                System.out.println("Неверная купюра");System.exit(0);
            }
        }catch (NumberFormatException e){
            System.out.println("Неверная купюра");System.exit(0);
        }
        exchange(nominals, value, 0, " ");
        if (b) System.out.println("Нет размена");
    }

    static int[] transform(int[] nominalArr){
        Arrays.sort(nominalArr);
        List<Integer> list = new ArrayList<>();
        list.add(nominalArr[0]);
        for (int i=1; i < nominalArr.length; i++){
            if (nominalArr[i-1]!=nominalArr[i]){
                list.add(nominalArr[i]);
            }
        }
        int result[] = new int[list.size()];
        for (int i=0; i<result.length; i++){
            result[i]=list.get(i);
        }
        return result;
    }

    static void exchange(int[] nominals, int sum, int i, String line) {
        if (sum==0) {
            if (b) System.out.println("Варианты размена:");
            System.out.println(line);
            b=false;
        }
        else {
            while (i<nominals.length && nominals[i] <= sum) {
                exchange(nominals, sum-nominals[i], i, line + String.valueOf(nominals[i]) + " ");
                i++;
            }
        }
    }
}
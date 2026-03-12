import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.util.HashMap;

class Coordinate {

    double x;
    double y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

public class Lab01 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ban kinh r: ");
        double r = sc.nextDouble();

        double dientich = bai1_2(r);
        double PI = bai1_2(1);

        System.out.println("\n=====Bai 1=========");
        System.out.println("Sap xi dien tich hinh tron: " + dientich);

        System.out.println("\n========Bai 2========");
        System.out.println("Sap xi so pi: " + PI);

        //bai3();

        bai4();
    }

    public static double bai1_2(double r) {

        Random rand = new Random();

        int N = 1000000;
        int M = 0;

        for (int i = 0; i < N; i++) {

            double x = -r + 2 * r * rand.nextDouble();
            double y = -r + 2 * r * rand.nextDouble();

            if (x * x + y * y <= r * r) {
                M++;
            }
        }

        return (double) M / N * 4 * r * r;
    }

    public static void bai3() {

        System.out.println("\n=========Bai3=======");
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so diem: ");

        int n = sc.nextInt();
        Coordinate[] a = new Coordinate[n];

        for (int i = 0; i < n; i++) {

            System.out.print("Nhap x: ");
            double x = sc.nextDouble();

            System.out.print("Nhap y: ");
            double y = sc.nextDouble();

            a[i] = new Coordinate(x, y);
        }

        // tìm điểm có y nhỏ nhất
        Coordinate lowest = a[0];
        int lowestIndex = 0;

        for (int i = 1; i < n; i++) {
            if (a[i].y < lowest.y || (a[i].y == lowest.y && a[i].x < lowest.x)) {
                lowest = a[i];
                lowestIndex = i;
            }
        }

        // đưa điểm lowest lên đầu mảng
        Coordinate tmp = a[0];
        a[0] = a[lowestIndex];
        a[lowestIndex] = tmp;

        // sắp xếp theo góc
        sap_xep_goc(a, a[0]);

        // tìm bao đóng
        Stack<Coordinate> hull = bao_dong(a);

        System.out.println("\nConvex Hull:");

        for (Coordinate p : hull) {
            System.out.println(p.x + " " + p.y);
        }
    }

    public static double tinh_goc_bai3(Coordinate base, Coordinate p) {

        double dx = p.x - base.x;
        double dy = p.y - base.y;

        return Math.atan2(dy, dx);
    }

    public static void sap_xep_goc(Coordinate[] a, Coordinate base) {

        for (int i = 1; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {

                double g1 = tinh_goc_bai3(base, a[i]);
                double g2 = tinh_goc_bai3(base, a[j]);

                if (g1 > g2) {

                    Coordinate tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }
    }

    // tìm bao đóng
    public static Stack<Coordinate> bao_dong(Coordinate[] a) {

        Stack<Coordinate> baodong = new Stack<>();

        baodong.push(a[0]);
        baodong.push(a[1]);

        for (int i = 2; i < a.length; i++) {

            Coordinate top = baodong.pop();

            while (!baodong.isEmpty() &&
                    cross(baodong.peek(), top, a[i]) <= 0) {

                top = baodong.pop();
            }

            baodong.push(top);
            baodong.push(a[i]);
        }

        return baodong;
    }

    // rẽ trái / rẽ phải
    // >0 → rẽ trái
    //<0 → rẽ phải
    //=0 → thẳng hàng
    public static double cross(Coordinate a, Coordinate b, Coordinate c) {

        return (b.x - a.x) * (c.y - a.y) -
                (b.y - a.y) * (c.x - a.x);
    }

    public static void bai4(){

        System.out.println("\n========Bai 4========");

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] a = new int[n];

        for(int i = 0; i < n; i++){
            a[i] = sc.nextInt();
        }

        HashMap<Integer,Integer> map = new HashMap<>();

        int sum = 0;

        int maxLen = 0;
        int start = -1;
        int end = -1;

        map.put(0,-1);

        for(int i = 0; i < n; i++){

            sum += a[i];

            if(map.containsKey(sum - m)){

                int j = map.get(sum - m);

                int len = i - j;

                if(len > maxLen){
                    maxLen = len;
                    start = j + 1;
                    end = i;
                }
            }

            if(!map.containsKey(sum)){
                map.put(sum,i);
            }
        }

        if(maxLen == 0){
            System.out.println("Khong co day con nao co tong = " + m);
        }else{

            System.out.println("Day con dai nhat:");

            for(int i = start; i <= end; i++){
                System.out.print(a[i] + " ");
            }

            System.out.println("\nDo dai: " + maxLen);
        }
    }
}
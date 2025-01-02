import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] foo;
    static int cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        String[] tmp = br.readLine().split(" ");
        
        for(int i = 0 ; i < N; i++){
            foo[i] = Integer.parseInt(tmp[i]);
        }

        cnt = 0;
        
        for(int j = 0; j < N; j++){
            if (isPrime(foo[j])) 
                cnt++;
        }

        System.out.println(cnt);
    }

    public static boolean isPrime(int n){
        if (n == 1) return false;

        for (int i = 2; i <= n; i++){
            if((n % i) == 0) return false;
        }

        return true;
    }

}





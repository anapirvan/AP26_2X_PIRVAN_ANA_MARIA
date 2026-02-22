import java.util.Scanner;

public class tema {
    public static void main(String args[]){

        int n=Integer.parseInt(args[0]);
        String forma=args[1];

        if(n<10000) {
            if (forma.equals("dreptunghi")) {
                dreptunghi1(n);
                System.out.println();
                dreptunghi2(n);
            }

            if (forma.equals("cerc")) {
                cerc1(n);
                System.out.println();
                cerc2(n);
                System.out.println();
            }
        }
        else{
            if(forma.equals("dreptunghi")){
                long t1=System.currentTimeMillis();

                StringBuilder s=new StringBuilder();
                int lungime=n/2+1,latime=lungime-1;
                for(int i=0;i<n;i++) {
                    for (int j = 0; j < n; j++) {
                        if (j >= 1 && j <= lungime && i >= 1 && i <= latime) {
                            s.append('*');
                        } else {
                            s.append('#');
                        }
                        s.append(' ');
                    }
                    s.append('\n');}

                long t2=System.currentTimeMillis();
                System.out.println(t2-t1);

            } else if (forma.equals("cerc")) {
                long t1=System.currentTimeMillis();

                StringBuilder s=new StringBuilder();
                int c1=n/2,c2=n/2,r=n/4;
                for(int i=0;i<n;i++){
                    for(int j=0;j<n;j++) {
                        if ((j - c1) * (j - c1) + (i - c2) * (i - c2) <= r * r) {
                            s.append('#');
                        } else {
                            s.append('*');
                        }
                        s.append(' ');}
                    s.append('\n');
                }

                long t2=System.currentTimeMillis();
                System.out.println(t2-t1);

            }
        }

    }

    public static void dreptunghi1(int n){
        int[][] m=new int[n][n];
        int lungime=n/2+1,latime=lungime-1;
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++){
                if(j>=1 && j<=lungime && i>=1 && i<=latime){
                    m[i][j]=0;
                }
                else{
                    m[i][j]=255;
                }
            }
        for(int i=0;i<n;i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%5d", m[i][j]);
            }
            System.out.println();
        }
    }

    public static void cerc1(int n){
        int[][] m=new int[n][n];
        int c1=n/2,c2=n/2,r=n/4;
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++){
                if((j-c1)*(j-c1)+(i-c2)*(i-c2)<=r*r){
                    m[i][j]=255;
                }
                else{
                    m[i][j]=0;
                }
            }
        for(int i=0;i<n;i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%5d", m[i][j]);
            }
            System.out.println();
        }
    }


    public static void dreptunghi2(int n){
        StringBuilder s=new StringBuilder();
        int lungime=n/2+1,latime=lungime-1;
        for(int i=0;i<n;i++) {
            for (int j = 0; j < n; j++) {
                if (j >= 1 && j <= lungime && i >= 1 && i <= latime) {
                    s.append('*');
                } else {
                    s.append('#');
                }
                s.append(' ');
            }
            s.append('\n');
        }
        System.out.println(s);
    }

    public static void cerc2(int n){
        StringBuilder s=new StringBuilder();
        int c1=n/2,c2=n/2,r=n/4;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                if ((j - c1) * (j - c1) + (i - c2) * (i - c2) <= r * r) {
                    s.append('#');
                } else {
                    s.append('*');
                }
                s.append(' ');}
                s.append('\n');
            }

        System.out.println(s);
        }
}

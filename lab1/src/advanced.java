public class advanced {
    public static void main(String[] args){
        int[][] mat=new int[7][9];
        for(int i=0;i<7;i++)
            for(int j=0;j<9;j++){
                if(i%3==0 && j%5==1){
                    mat[i][j]=1;
                }
                else{
                    mat[i][j]=0;
                }
            }

        boundingBox(mat);

    }

    public static void boundingBox(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int colMin = m, colMax = -1;
        int linMin = n, linMax = -1;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1) {
                    if (i < linMin) {
                        linMin = i;
                    }
                    if (i > linMax) {
                        linMax = i;
                    }
                    if (j < colMin) {
                        colMin = j;
                    }
                    if (j > colMax) {
                        colMax = j;
                    }
                }
            }
        System.out.println("Extremitatile sunt: " + linMin + "(sus), " + linMax + "(jos), " + colMin + "(stanga), " + colMax + "(dreapta)");
        int lungime = colMax - colMin + 1, latime = linMax - linMin + 1;
        if (lungime < latime) {
            int aux = lungime;
            lungime = latime;
            latime = aux;
        }
        System.out.println("Lungime: " + lungime);
        System.out.println("Latime: " + latime);
    }
}

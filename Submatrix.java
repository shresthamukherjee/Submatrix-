class Main
{
    public static int[][] preprocess(int[][] mat)
    {
        // `M × N` matrix
        int M = mat.length;
        int N = mat[0].length;
 
        // preprocess the matrix `mat` such that `sum[i][j]` stores
        // sum of elements in the matrix from (0, 0) to (i, j)
        int[][] sum = new int[mat.length][mat[0].length];
        sum[0][0] = mat[0][0];
 
        // preprocess the first row
        for (int j = 1; j < mat[0].length; j++) {
            sum[0][j] = mat[0][j] + sum[0][j - 1];
        }
 
        // preprocess the first column
        for (int i = 1; i < mat.length; i++) {
            sum[i][0] = mat[i][0] + sum[i - 1][0];
        }
 
        // preprocess the rest of the matrix
        for (int i = 1; i < mat.length; i++)
        {
            for (int j = 1; j < mat[0].length; j++)
            {
                sum[i][j] = mat[i][j] + sum[i - 1][j] + sum[i][j - 1]
                        - sum[i - 1][j - 1];
            }
        }
        return sum;
    }
 
    // Calculate the sum of all elements in a submatrix in constant time
    public static int findSubmatrixSum(int[][] mat, int p, int q, int r, int s)
    {
        // base case
        if (mat == null || mat.length == 0) {
            return 0;
        }
 
        // preprocess the matrix
        int[][] sum = preprocess(mat);
 
        /* `total` is `sum[r][s] - sum[r][q-1] - sum[p-1][s] + sum[p-1][q-1]` */
        int total = sum[r][s];
 
        if (q - 1 >= 0) {
            total -= sum[r][q - 1];
        }
 
        if (p - 1 >= 0) {
            total -= sum[p - 1][s];
        }
 
        if (p - 1 >= 0 && q - 1 >= 0) {
            total += sum[p - 1][q - 1];
        }
 
        return total;
    }
 
    public static void main(String[] args)
    {
        int[][] mat =
        {
            { 0, 2, 5, 4, 1 },
            { 4, 8, 2, 3, 7 },
            { 6, 3, 4, 6, 2 },
            { 7, 3, 1, 8, 3 },
            { 1, 5, 7, 9, 4 }
        };
 
        // (p, q) and (r, s) represent top-left and bottom-right
        // coordinates of the submatrix
        int p = 1, q = 1, r = 3, s = 3;
 
        // calculate the submatrix sum
        System.out.print(findSubmatrixSum(mat, p, q, r, s));
    }
}
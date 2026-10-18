class Solution {
      static final int MOD = 1_000_000_007;
      long [][] choose;
    public int numOfWays(int[] nums) {
        int n = nums.length;
       choose = new long[n+1][n+1];
       for(int i=0;i<=n;i++){
        choose[i][0]=1;
        choose[i][i]=1;
        for(int j=1;j<i;j++){
            choose[i][j]=(choose[i-1][j-1]+choose[i-1][j])%MOD;
        }
       }
       List<Integer> list = new ArrayList<>();
       for(int num:nums){
        list.add(num);
       }
       return (int)((slove(list)-1+MOD)%MOD);
    }
    private long slove(List<Integer>nums){
        int n=nums.size();
        if(n<=1){
            return 1;
        }
        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
         List<Integer> right = new ArrayList<>();
         for(int i = 1;i<n;i++){
            if(nums.get(i)<root){
                left.add(nums.get(i));
            }
            else{
                right.add(nums.get(i));
            }
         }
            long ways =  choose[n-1][left.size()];
            ways = ways*slove(left) % MOD;
            ways = ways * slove(right) % MOD;
            return ways;
        
    }
}
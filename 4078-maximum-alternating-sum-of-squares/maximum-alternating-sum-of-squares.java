class Solution {
    public long maxAlternatingSum(int[] nums) {
        long[] sq = new long[nums.length];
        for(int i=0;i<nums.length;i++){
            sq[i]=nums[i]*nums[i];
        }
        Arrays.sort(sq);
        long score =0;
        int n = nums.length;
        for(int i=0;i<n/2;i++){
            score-=sq[i];
        }
        for(int i=n/2;i<n;i++){
            score+=sq[i];
        }
        return score;
    }
}
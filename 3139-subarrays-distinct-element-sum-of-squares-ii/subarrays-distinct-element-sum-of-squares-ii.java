
import java.util.*;

class Solution {
    static final long MOD = 1_000_000_007L;
    class SegmentTree {
        long[] sum;
        long[] sq;
        long[] lazy;
        int n;
        SegmentTree(int n) {
            this.n = n;
            sum = new long[4 * n];
            sq = new long[4 * n];
            lazy = new long[4 * n];
        }
        void apply(int node, int l, int r, long val) {
            long len = r - l + 1L;
            sq[node] = (sq[node]
                    + 2L * val % MOD * sum[node] % MOD
                    + len % MOD * val % MOD * val % MOD) % MOD;

            sum[node] = (sum[node] + len * val) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
        }
        void push(int node, int l, int r) {
            if (lazy[node] == 0 || l == r) {
                return;
            }
            int mid = (l + r) / 2;
            apply(node * 2, l, mid, lazy[node]);
            apply(node * 2 + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
        void update(int node, int l, int r,
                    int ql, int qr, long val) {
            if (ql > r || qr < l) {
                return;
            }
            if (ql <= l && r <= qr) {
                apply(node, l, r, val);
                return;
            }
            push(node, l, r);
            int mid = (l + r) / 2;
            update(node * 2, l, mid, ql, qr, val);
            update(node * 2 + 1, mid + 1, r, ql, qr, val);
            sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
            sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
        }
        void update(int l, int r, long val) {
            if (l <= r) {
                update(1, 0, n - 1, l, r, val);
            }
        }
        long querySquares() {
            return sq[1];
        }
    }
    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegmentTree st = new SegmentTree(n);
        int[] last = new int[100001];
        Arrays.fill(last, -1);
        long ans = 0;
        for (int r = 0; r < n; r++) {
            int prev = last[nums[r]];
            st.update(prev + 1, r, 1);
            last[nums[r]] = r;
            ans = (ans + st.querySquares()) % MOD;
        }

        return (int) ans;
    }
}

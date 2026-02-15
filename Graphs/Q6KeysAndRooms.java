import java.util.List;
import java.util.Stack;

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int vis[] = new int[rooms.size()];
        vis[0] = 1;
        Stack<Integer> st = new Stack<>();
        st.push(0);

        while (!st.isEmpty()) {
            int node = st.pop();
            for (int ele : rooms.get(node)) {
                if (vis[ele] == 0) {
                    vis[ele] = 1;
                    st.push(ele);
                }

            }
        }
        for (int i = 0; i < vis.length; i++) {
            if (vis[i] == 0)
                return false;
        }
        return true;
    }
}
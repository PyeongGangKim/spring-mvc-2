import java.util.*;
/*
1부터 n까지 번호
철로는 양방향 통행 가능
서로 다른 두 역 사이의 이동 경로는 딱 한가지
출발역과 종착역 사이를 왕복하는 열차를 운행하려 합니다.
출발역은 1번 역. 종착역을 정해야됨.
모든 역을 방문할 필요는 없고, 같은 역을 두 번 이상 방문하면 안된다.
종착역을 정하기 위해 각 역의 일일 이용객 수를 조사. 열차가 방문하는 역의 일일 이용객 수의
합이 최대가 되도록 종착역 지정. 큰 역의 번호를 선택하면 됨.
그냥 dfs 쓰면 됨
*/

class Solution {


    public static void main(String[] args) {
        int[] passenger = new int[6];
        int[][] train = new int[6][2];
        passenger[0] = 1;
        passenger[1] = 1;
        passenger[2] = 1;
        passenger[3] = 1;
        passenger[4] = 1;
        passenger[5] = 1;
        train[0][0] = 1;
        train[0][1] = 2;
        train[1][0] = 1;
        train[1][1] = 3;
        train[2][0] = 1;
        train[2][1] = 4;
        train[3][0] = 3;
        train[3][1] = 5;
        train[4][0] = 3;
        train[4][1] = 6;
        int[] answer = {0,0};
        boolean[] isVisited = new boolean[100001];
        Arrays.fill(isVisited, false);
        System.out.println(solution(6,passenger,train, answer, isVisited));
    }
    public static int[] solution(int n, int[] passenger, int[][] train, int[] answer, boolean[] isVisited) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int[] t : train){
            List<Integer> dir = map.get(t[0]);
            List<Integer> rDir = map.get(t[1]);
            if(dir == null) {
                dir = new ArrayList<>();
                map.put(t[0], dir);
            }
            else(rDir == null){
                rDir = new ArrayList<>();
                map.put(t[1], rDir);
            }
            dir.add(t[1]);
            rDir.add(t[0]);
        }


        isVisited[1] = true;
        dfs(1, passenger[0], passenger, map, isVisited, answer);


        return answer;
    }

    static void dfs(int cur, int cnt, int[] passenger, Map<Integer,List<Integer>> map, boolean[] isVisited, int[] answer){
        if(map.get(cur) != null) {
            for (int next : map.get(cur)) {
                //System.out.println(next);
                if (isVisited[next]) continue;
                isVisited[next] = true;
                dfs(next, cnt + passenger[next - 1], passenger, map, isVisited, answer);
                isVisited[next] = false;
            }
        }
        if(answer[1] < cnt){
            answer[0] = cur;
            answer[1] = cnt;
        } else if(answer[1] == cnt) {
            if(answer[0] < cur){
                answer[0] = cur;
            }
        }

    }
}
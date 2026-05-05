import java.util.*;

class Solution {
    
    private static int POINT_COUNT;
    private static int MAX_ROW = -1;
    private static int MAX_COL = -1;
    
    private static Map<String, Integer> isVisited;
    private static int answer = 0;
    
    public int solution(int[][] points, int[][] routes) {
        POINT_COUNT = routes[0].length;
        
        for (int[] point : points) {
            int y = point[0];
            int x = point[1];
            MAX_ROW = Math.max(MAX_ROW, y);
            MAX_COL = Math.max(MAX_COL, x);
        }
        
        isVisited = new HashMap<>();
        
        for (int[] route : routes) {
            move(route, points);
        }
        
        return answer;
    }
    
    private void move(int[] route, int[][] points) {
        int currTime = 0;
        int y = points[route[0] - 1][0] - 1;
        int x = points[route[0] - 1][1] - 1;
        for (int i = 1; i < POINT_COUNT; i++) {
            int nextPoint = route[i] - 1;
            int nextY = points[nextPoint][0] - 1;
            int nextX = points[nextPoint][1] - 1;
            
            if (y < nextY) {
                while (y < nextY) {
                    check(currTime, y, x);
                    currTime++;
                    y++;
                }   
            } else {
                while (nextY < y) {
                    check(currTime, y, x);
                    currTime++;
                    y--;
                }   
            }
            
            if (x < nextX) {
                while (x < nextX) {
                    check(currTime, y, x);
                    currTime++;
                    x++;
                }   
            } else {
                while (nextX < x) {
                    check(currTime, y, x);
                    currTime++;
                    x--;
                }   
            }
            
        }
        check(currTime, y, x);
    }
   
    private void check(int time, int y, int x) {
        String key = makeKey(time, y, x);
        if (!isVisited.containsKey(key)) {
           	isVisited.put(key, 0);
        } else if (isVisited.get(key) == 0) {
            answer++;
            isVisited.put(key, 1);
        }
    }
    
    private String makeKey(int time, int y, int x) {
        return time + "," + y + "," + x;
    }
}
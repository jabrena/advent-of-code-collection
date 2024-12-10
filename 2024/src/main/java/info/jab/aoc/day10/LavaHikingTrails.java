package info.jab.aoc.day10;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import com.putoet.grid.Grid;

public class LavaHikingTrails {

    public static int sumTrailheadScores(Grid grid) {
        var points = grid.findAll(c -> c == '0');
        return points.stream()
            .map(p -> calculateTrailheadScore(grid.grid(), p.y(), p.x()))
            .reduce(0, Integer::sum);
    }

    public static int sumTrailheadScoresBFS(Grid grid) {
        var points = grid.findAll(c -> c == '0');
        return points.stream()
            .map(p -> calculateTrailheadScoreBFS(grid.grid(), p.y(), p.x()))
            .reduce(0, Integer::sum);
    }

    public static int sumTrailheadRatings(Grid grid) {
        var points = grid.findAll(c -> c == '0');
        return points.stream()
            .map(p -> calculateTrailheadRating(grid.grid(), p.y(), p.x()))
            .reduce(0, Integer::sum);
    }

    public static int sumTrailheadRatingsBFS(Grid grid) {
        var points = grid.findAll(c -> c == '0');
        return points.stream()
            .map(p -> calculateTrailheadRating(grid.grid(), p.y(), p.x()))
            .reduce(0, Integer::sum);
    }

    private static int calculateTrailheadScore(char[][] map, int startRow, int startCol) {
        Set<String> visited = new HashSet<>();
        Set<String> reachableNines = new HashSet<>();
        dfsScore(map, startRow, startCol, 0, visited, reachableNines);
        return reachableNines.size();
    }

    private static int calculateTrailheadRating(char[][] map, int startRow, int startCol) {
        Set<String> visited = new HashSet<>();
        return dfsRating(map, startRow, startCol, 0, visited);
    }

    private static boolean isValidPosition(char[][] map, int row, int col) {
        return row >= 0 && row < map.length && col >= 0 && col < map[0].length;
    }

    private static void dfsScore(char[][] map, int row, int col, int currentHeight, Set<String> visited, Set<String> reachableNines) {
        String positionKey = row + "," + col;
        if (!isValidPosition(map, row, col) || visited.contains(positionKey)) {
            return;
        }

        int height = map[row][col] - '0';
        if (height != currentHeight) {
            return;
        }

        visited.add(positionKey);

        if (height == 9) {
            reachableNines.add(positionKey);
            return;
        }

        // Explore all four directions
        dfsScore(map, row + 1, col, height + 1, visited, reachableNines);
        dfsScore(map, row - 1, col, height + 1, visited, reachableNines);
        dfsScore(map, row, col + 1, height + 1, visited, reachableNines);
        dfsScore(map, row, col - 1, height + 1, visited, reachableNines);
    }

    private static int dfsRating(char[][] map, int row, int col, int currentHeight, Set<String> visited) {
        String positionKey = row + "," + col;
        if (!isValidPosition(map, row, col) || visited.contains(positionKey)) {
            return 0;
        }

        int height = map[row][col] - '0';
        if (height != currentHeight) {
            return 0;
        }

        visited.add(positionKey);

        if (height == 9) {
            return 1; // Count this path
        }

        int totalPaths = 0;

        // Explore all four directions
        totalPaths += dfsRating(map, row + 1, col, height + 1, new HashSet<>(visited));
        totalPaths += dfsRating(map, row - 1, col, height + 1, new HashSet<>(visited));
        totalPaths += dfsRating(map, row, col + 1, height + 1, new HashSet<>(visited));
        totalPaths += dfsRating(map, row, col - 1, height + 1, new HashSet<>(visited));

        return totalPaths;
    }

    private static int calculateTrailheadScoreBFS(char[][] map, int startRow, int startCol) {
        Set<String> reachableNines = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        // Start BFS from the trailhead
        queue.add(new int[]{startRow, startCol, 0});  // {row, col, current height}
        visited.add(startRow + "," + startCol);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int height = current[2];

            // If we are at a position where the height is not matching, skip it
            if (map[row][col] - '0' != height) {
                continue;
            }

            // If height is 9, add this position to the reachable set
            if (height == 9) {
                reachableNines.add(row + "," + col);
                continue;
            }

            // Explore neighboring positions in all four directions
            for (int[] direction : getDirections()) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (isValidPosition(map, newRow, newCol) && !visited.contains(newRow + "," + newCol)) {
                    // Ensure the next height is exactly 1 more than the current height
                    if (map[newRow][newCol] - '0' == height + 1) {
                        queue.add(new int[]{newRow, newCol, height + 1});
                        visited.add(newRow + "," + newCol);
                    }
                }
            }
        }

        return reachableNines.size();
    }

    private static int calculateTrailheadRatingBFS(char[][] map, int startRow, int startCol) {
        Queue<int[]> queue = new LinkedList<>();
        int distinctTrailCount = 0;

        queue.add(new int[]{startRow, startCol, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int height = current[2];

            if (map[row][col] - '0' != height) {
                continue;
            }
            if (height == 9) {
                distinctTrailCount++;
                continue;
            }
            for (int[] direction : getDirections()) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (isValidPosition(map, newRow, newCol)) {
                    queue.add(new int[]{newRow, newCol, height + 1});
                }
            }
        }
        return distinctTrailCount;
    }

    //TODO Refactor with Points
    private static int[][] getDirections() {
        return new int[][]{
                {1, 0},  // Down
                {-1, 0}, // Up
                {0, 1},  // Right
                {0, -1}  // Left
        };
    }
}

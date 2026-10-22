class Solution {
    private boolean[][] rows, cols, boxes;

    public void solveSudoku(char[][] board) {
        rows = new boolean[9][10];
        cols = new boolean[9][10];
        boxes = new boolean[9][10];

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] != '.') {
                    int d = board[r][c] - '0';
                    rows[r][d] = cols[c][d] = boxes[(r / 3) * 3 + c / 3][d] = true;
                }
            }
        }
        solve(board, 0);
    }

    private boolean solve(char[][] board, int pos) {
        while (pos < 81 && board[pos / 9][pos % 9] != '.') pos++;
        if (pos == 81) return true;

        int r = pos / 9, c = pos % 9, b = (r / 3) * 3 + c / 3;

        for (int d = 1; d <= 9; d++) {
            if (!rows[r][d] && !cols[c][d] && !boxes[b][d]) {
                board[r][c] = (char) ('0' + d);
                rows[r][d] = cols[c][d] = boxes[b][d] = true;
                if (solve(board, pos + 1)) return true;
                board[r][c] = '.';
                rows[r][d] = cols[c][d] = boxes[b][d] = false;
            }
        }
        return false;
    }
}

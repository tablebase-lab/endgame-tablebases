package dev.michalrelich.tablebase.code;

import dev.michalrelich.engine.backend.positioncheck.PositionCheck;

public class EGTBConstants {

    public static final int MAX_MOVES_IN_A_POSITION = 97; // 27 * 3 for queens, 8 * 2 for kings, probably less but just to be sure

    public static final int TOTAL_THREE_PIECE_ENDGAMES = three_piece_endgame_count();

    public static int three_piece_endgame_count() {
        int count = 0;

        for (int pieceInt = 1; pieceInt <= 5; pieceInt++) {
            for (int turn = 1; turn <= 2; turn++) {
                for (int kingTwo = 0; kingTwo <= 63; kingTwo++) {
                    for (int piece = 0; piece <= 63; piece++) {
                        for (int kingOne = 0; kingOne <= 63; kingOne++) { // king HAS to be here cuz of cached indexes
                            int[] pieces = {turn, 8, kingOne, kingTwo, 1, pieceInt * 100 + piece};

                            if (PositionCheck.checkPosition(pieces)) {
                                // at the end
                                count++;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
}

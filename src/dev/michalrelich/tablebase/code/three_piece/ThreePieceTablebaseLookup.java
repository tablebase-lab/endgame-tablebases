package dev.michalrelich.tablebase.code.three_piece;

import dev.michalrelich.engine.backend.Constants;
import dev.michalrelich.engine.backend.positioncheck.PositionCheck;
import dev.michalrelich.tablebase.code.Helpers;

import java.util.Arrays;

public class ThreePieceTablebaseLookup {

    public static int retrieveIndexByPosition(int[] position, int pieceInt) { // meant for lookups by humans, doesn't use precomputed indices
        int index = 0;
        for (int turn = 1; turn <= 2; turn++) {
            for (int kingTwo = 0; kingTwo <= 63; kingTwo++) {
                for (int piece = 0; piece <= 63; piece++) {
                    for (int kingOne = 0; kingOne <= 63; kingOne++) { // king HAS to be here cuz of cached indexes
                        boolean proceed = pieceInt == 5 ? Helpers.isLeftHalfKingPosition(kingOne) :
                                Helpers.isEighthKingPosition(kingOne);

                        if (!proceed) continue;
                        int[] pieces = {turn, 8, kingOne, kingTwo, 1, pieceInt * 100 + piece};

                        if (PositionCheck.checkPosition(pieces)) {
                            if (Arrays.equals(pieces, position)) return index;

                            index++;
                        }
                    }
                }
            }
        }

        return -1;
    }

    public static int retrievePrecomputedIndexByPosition(int[] position, int pieceInt) {
        int turn = position[Constants.TURN_INDEX];
        int blackKingPos = position[Constants.BLACK_KING_INDEX];

        int piecePos = position[Constants.DELIMITER_INDEX + 1] % 100;

        int index = ThreePieceConstants.returnIndexFromArray(pieceInt, turn, blackKingPos, piecePos);
        for (int kingOne = 0; kingOne <= 63; kingOne++) {
            boolean proceed = pieceInt == 5 ? Helpers.isLeftHalfKingPosition(kingOne) :
                    Helpers.isEighthKingPosition(kingOne);

            if (!proceed) continue;

            int[] arr = {turn, 8, kingOne, blackKingPos, 1, pieceInt * 100 + piecePos};
            if (PositionCheck.checkPosition(arr)) {
                if (Arrays.equals(position, arr)) {
                    return index;
                }
                index++;
            }
        }

        return -1;
    }

    public static int retrieveResultByPosition(int[] position, int pieceInt, boolean usePreComputedIndices) {
        int index = usePreComputedIndices ? retrievePrecomputedIndexByPosition(position, pieceInt) : retrieveIndexByPosition(position, pieceInt);
        return ThreePieceAlgorithm.retrieveResultByIndex(index, pieceInt, ThreePieceAlgorithm.assignMBB(pieceInt));
    }
}
package Level_1.Hash;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;

public class SHA_1 {
    private final int BLOCK_SIZE = 512;
    private final int WORD_SIZE = 32;

    private List<List<Long>> divideIntoWords(String padded) {
        List<List<Long>> words = new ArrayList<>(padded.length() / BLOCK_SIZE);

        for (int i = 0; i < padded.length() / BLOCK_SIZE; i++) {
            words.add(new ArrayList<>(16));
            for (int j = 0; j < 16; j++) {
                int shift = i * BLOCK_SIZE + j * WORD_SIZE;
                words.get(i).add(Long.parseLong(Long.toHexString(Long.parseLong(padded.substring(shift, shift + WORD_SIZE), 2)), 16));
            }
        }

        return words;
    }

    private String padMessage(String message, int messageLengthInBits) {
        StringBuilder padded = new StringBuilder();

        for (char c : message.toCharArray()) {
            padded.append(String.format("%08d", Long.parseLong(Long.toBinaryString(c))));
        }

        padded.append('1');

        int k = 0;

        for (k = 0; k < BLOCK_SIZE; k++) {
            if ((messageLengthInBits + 1 + k) % BLOCK_SIZE == 448) {
                break;
            }
            padded.append('0');
        }

        padded.append(String.format("%064d", Long.parseLong(Long.toBinaryString(messageLengthInBits))));

        return padded.toString();
    }



    public static void main(String[] args) {



    }
}

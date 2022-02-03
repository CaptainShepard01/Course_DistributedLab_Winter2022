package Level_1.Hash;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;

public class SHA_1 {
    private final int BLOCK_SIZE = 512;
    private final int WORD_SIZE = 32;
    private final long MODULO = (long) pow(2, 32);


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

    private Long ROTL(long x, int n) {
        return ((x << n) | (x >> (WORD_SIZE - n))) % MODULO;
    }

    private Long f_t(long x, long y, long z, int t) {
        if (0 <= t && t < 20) {
            return (x & y) ^ (~x & z);
        }
        if (20 <= t && t < 40) {
            return x ^ y ^ z;
        }
        if (40 <= t && t < 60) {
            return (x & y) ^ (x & z) ^ (y & z);
        } else {
            return x ^ y ^ z;
        }
    }

    private Long K_t(int t) {
        if (0 <= t && t < 20) {
            return 0x5a827999L;
        }
        if (20 <= t && t < 40) {
            return 0x6ed9eba1L;
        }
        if (40 <= t && t < 60) {
            return 0x8f1bbcdcL;
        } else {
            return 0xca62c1d6L;
        }
    }

    public static void main(String[] args) {



    }
}

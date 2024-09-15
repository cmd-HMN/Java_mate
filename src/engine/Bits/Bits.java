package src.engine.Bits;

import java.util.HashMap;
import java.util.Map;

public class Bits {

    public static int int_64 = 64;

    public static final long A1 = 1L << 0;
    public static final long B1 = 1L << 1;
    public static final long C1 = 1L << 2;
    public static final long D1 = 1L << 3;
    public static final long E1 = 1L << 4;
    public static final long F1 = 1L << 5;
    public static final long G1 = 1L << 6;
    public static final long H1 = 1L << 7;

    public static final long A2 = 1L << 8;
    public static final long B2 = 1L << 9;
    public static final long C2 = 1L << 10;
    public static final long D2 = 1L << 11;
    public static final long E2 = 1L << 12;
    public static final long F2 = 1L << 13;
    public static final long G2 = 1L << 14;
    public static final long H2 = 1L << 15;

    public static final long A3 = 1L << 16;
    public static final long B3 = 1L << 17;
    public static final long C3 = 1L << 18;
    public static final long D3 = 1L << 19;
    public static final long E3 = 1L << 20;
    public static final long F3 = 1L << 21;
    public static final long G3 = 1L << 22;
    public static final long H3 = 1L << 23;

    public static final long A4 = 1L << 24;
    public static final long B4 = 1L << 25;
    public static final long C4 = 1L << 26;
    public static final long D4 = 1L << 27;
    public static final long E4 = 1L << 28;
    public static final long F4 = 1L << 29;
    public static final long G4 = 1L << 30;
    public static final long H4 = 1L << 31;

    public static final long A5 = 1L << 32;
    public static final long B5 = 1L << 33;
    public static final long C5 = 1L << 34;
    public static final long D5 = 1L << 35;
    public static final long E5 = 1L << 36;
    public static final long F5 = 1L << 37;
    public static final long G5 = 1L << 38;
    public static final long H5 = 1L << 39;

    public static final long A6 = 1L << 40;
    public static final long B6 = 1L << 41;
    public static final long C6 = 1L << 42;
    public static final long D6 = 1L << 43;
    public static final long E6 = 1L << 44;
    public static final long F6 = 1L << 45;
    public static final long G6 = 1L << 46;
    public static final long H6 = 1L << 47;

    public static final long A7 = 1L << 48;
    public static final long B7 = 1L << 49;
    public static final long C7 = 1L << 50;
    public static final long D7 = 1L << 51;
    public static final long E7 = 1L << 52;
    public static final long F7 = 1L << 53;
    public static final long G7 = 1L << 54;
    public static final long H7 = 1L << 55;

    public static final long A8 = 1L << 56;
    public static final long B8 = 1L << 57;
    public static final long C8 = 1L << 58;
    public static final long D8 = 1L << 59;
    public static final long E8 = 1L << 60;
    public static final long F8 = 1L << 61;
    public static final long G8 = 1L << 62;
    public static final long H8 = 1L << 63;


    public static final long[] SQUARES = new long[int_64];

    private static final Map<String, Long> bits_sequence = new HashMap<>();

    static{
        bits_sequence.put("a1", A1);
        bits_sequence.put("b1", B1);
        bits_sequence.put("c1", C1);
        bits_sequence.put("d1", D1);
        bits_sequence.put("e1", E1);
        bits_sequence.put("f1", F1);
        bits_sequence.put("g1", G1);
        bits_sequence.put("h1", H1);

        bits_sequence.put("a2", A2);
        bits_sequence.put("b2", B2);
        bits_sequence.put("c2", C2);
        bits_sequence.put("d2", D2);
        bits_sequence.put("e2", E2);
        bits_sequence.put("f2", F2);
        bits_sequence.put("g2", G2);
        bits_sequence.put("h2", H2);

        bits_sequence.put("a3", A3);
        bits_sequence.put("b3", B3);
        bits_sequence.put("c3", C3);
        bits_sequence.put("d3", D3);
        bits_sequence.put("e3", E3);
        bits_sequence.put("f3", F3);
        bits_sequence.put("g3", G3);
        bits_sequence.put("h3", H3);

        bits_sequence.put("a4", A4);
        bits_sequence.put("b4", B4);
        bits_sequence.put("c4", C4);
        bits_sequence.put("d4", D4);
        bits_sequence.put("e4", E4);
        bits_sequence.put("f4", F4);
        bits_sequence.put("g4", G4);
        bits_sequence.put("h4", H4);

        bits_sequence.put("a5", A5);
        bits_sequence.put("b5", B5);
        bits_sequence.put("c5", C5);
        bits_sequence.put("d5", D5);
        bits_sequence.put("e5", E5);
        bits_sequence.put("f5", F5);
        bits_sequence.put("g5", G5);
        bits_sequence.put("h5", H5);

        bits_sequence.put("a6", A6);
        bits_sequence.put("b6", B6);
        bits_sequence.put("c6", C6);
        bits_sequence.put("d6", D6);
        bits_sequence.put("e6", E6);
        bits_sequence.put("f6", F6);
        bits_sequence.put("g6", G6);
        bits_sequence.put("h6", H6);

        bits_sequence.put("a7", A7);
        bits_sequence.put("b7", B7);
        bits_sequence.put("c7", C7);
        bits_sequence.put("d7", D7);
        bits_sequence.put("e7", E7);
        bits_sequence.put("f7", F7);
        bits_sequence.put("g7", G7);
        bits_sequence.put("h7", H7);

        bits_sequence.put("a8", A8);
        bits_sequence.put("b8", B8);
        bits_sequence.put("c8", C8);
        bits_sequence.put("d8", D8);
        bits_sequence.put("e8", E8);
        bits_sequence.put("f8", F8);
        bits_sequence.put("g8", G8);
        bits_sequence.put("h8", H8);
    }
    public long getBits(String str){
        return bits_sequence.getOrDefault(str, 0L);
    }
}

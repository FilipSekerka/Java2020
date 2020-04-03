import LISTTestScoring.LISTTestScoring;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestFaceHookMY {
    private static LISTTestScoring scoring = null;
    @BeforeClass
    public static void initScoring() {
        scoring = new LISTTestScoring();
        scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
    }

    private FaceHook<String> generTest(int level) {
        FaceHook<String> f = new FaceHook<>();
        for (int i = 0; i < level; i++) {
            String s = Integer.toBinaryString(i);
            f.blizkiPriatelia(s, s+"0");
            f.blizkiPriatelia(s, s+"1");
        }
        return f;
    }
    private Objekt nObjekt(String s) {
        return new Objekt(s);
        //return new Objekt(Integer.valueOf(s));

//        return new Objekt(Integer.valueOf(s), s, s, 0);
    }
    private FaceHook<Objekt> generTest1(int level) {
        FaceHook<Objekt> f = new FaceHook<>();
        for (int i = 0; i < level; i++) {
            String s = Integer.toBinaryString(i);
            f.blizkiPriatelia(nObjekt(s), nObjekt(s+"0"));
            f.blizkiPriatelia(nObjekt(s), nObjekt(s+"1"));
        }
        return f;
    }

    @Test
    public void testVsetci() {
        {
            FaceHook<String> f = generTest(1);
            assertEquals("vsetci ", 3, f.vsetci().size());
        }
        {
            FaceHook<String> f = generTest(4);
            assertEquals("vsetci ", 10, f.vsetci().size());
        }
        {
            FaceHook<String> f = generTest(16);
            assertEquals("vsetci ", 34, f.vsetci().size());
        }
        scoring.setScore("lang:common_list_test_scoring_name", 0, 25);
    }
    @Test
    public void testPocetPriatelov() {
        {
            FaceHook<String> f = generTest(1);
            assertEquals("pocetPriatelov ", 2, f.pocetPriatelov("0"));
            assertEquals("pocetPriatelov ", 0, f.pocetPriatelov("1"));
        }
        {
            FaceHook<String> f = generTest(4);
            assertEquals("pocetPriatelov ", 3, f.pocetPriatelov("10"));
        }
        {
            FaceHook<String> f = generTest(16);
            assertEquals("pocetPriatelov ", 3, f.pocetPriatelov("110"));
        }
        scoring.setScore("lang:common_list_test_scoring_name", 0, 25);
    }
    @Test
    public void testspolocniPriatelia() {
        {
            FaceHook<String> f = generTest(16);
           // System.out.println(f);
            assertEquals("spolocniPriatelia ", 1, f.spolocniPriatelia("01", "00"));
            assertEquals("spolocniPriatelia ", 1, f.spolocniPriatelia("11", "10"));
            assertEquals("spolocniPriatelia ", 1, f.spolocniPriatelia("110", "111"));
            assertEquals("spolocniPriatelia ", 0, f.spolocniPriatelia("1000", "1010"));
            assertEquals("spolocniPriatelia ", 3, f.spolocniPriatelia("1010", "1010"));
            assertEquals("spolocniPriatelia ", 1, f.spolocniPriatelia("1010", "1011"));
            assertEquals("spolocniPriatelia ", 0, f.spolocniPriatelia("1110", "1011"));
            assertEquals("spolocniPriatelia ", 0, f.spolocniPriatelia("0", "1"));
        }
        {
            FaceHook<Objekt> f = generTest1(16);
           // System.out.println(f);
            assertEquals("spolocniPriatelia ", 1, f.spolocniPriatelia(nObjekt("01"), nObjekt("00")));
            assertEquals("spolocniPriatelia ", 1, f.spolocniPriatelia(nObjekt("11"), nObjekt("10")));
            assertEquals("spolocniPriatelia ", 1, f.spolocniPriatelia(nObjekt("110"), nObjekt("111")));
            assertEquals("spolocniPriatelia ", 0, f.spolocniPriatelia(nObjekt("1000"), nObjekt("1010")));
            assertEquals("spolocniPriatelia ", 3, f.spolocniPriatelia(nObjekt("1010"), nObjekt("1010")));
            assertEquals("spolocniPriatelia ", 1, f.spolocniPriatelia(nObjekt("1010"), nObjekt("1011")));
            assertEquals("spolocniPriatelia ", 0, f.spolocniPriatelia(nObjekt("1110"), nObjekt("1011")));
            assertEquals("spolocniPriatelia ", 0, f.spolocniPriatelia(nObjekt("0"), nObjekt("1")));
        }
        scoring.setScore("lang:common_list_test_scoring_name", 0, 25);
    }
    @Test
    public void testVzdialenyPriatel() {
        {
            FaceHook<String> f = generTest(16);
            assertTrue("su priatelia ", f.vzdialenyPriatel("100", "111"));
            assertTrue("su priatelia ", f.vzdialenyPriatel("100", "110"));
            assertFalse("nie su priatelia ", f.vzdialenyPriatel("000", "110"));
            assertFalse("nie su priatelia ", f.vzdialenyPriatel("000", "011"));
            assertFalse("nie su priatelia ", f.vzdialenyPriatel("0000", "0111"));
        }
        {
            FaceHook<Objekt> f = generTest1(16);
            assertTrue("su priatelia ", f.vzdialenyPriatel(nObjekt("100"), nObjekt("111")));
            assertTrue("su priatelia ", f.vzdialenyPriatel(nObjekt("100"), nObjekt("110")));
            assertTrue("su priatelia ", f.vzdialenyPriatel(nObjekt("101"), nObjekt("10")));
            assertTrue("su priatelia ", f.vzdialenyPriatel(nObjekt("10"), nObjekt("11")));
           // assertTrue("su priatelia ", f.vzdialenyPriatel(nObjekt("000"), nObjekt("111")));
        }
        scoring.setScore("lang:common_list_test_scoring_name", 0, 30);
    }
}
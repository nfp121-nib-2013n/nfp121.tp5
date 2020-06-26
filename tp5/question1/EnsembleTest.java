package question1;

public class EnsembleTest extends junit.framework.TestCase {

    public void testUnion() {
        question1.Ensemble<Integer> e1, e2;
        e1 = new question1.Ensemble<Integer>();
        assertEquals(true, e1.add(2));
        assertEquals(true, e1.add(3));

        e2 = new question1.Ensemble<Integer>();
        assertEquals(true, e2.add(3));
        assertEquals(true, e2.add(4));

        question1.Ensemble<Integer> union = e1.union(e2);
        assertEquals(3, union.size());
        assertTrue(union.contains(2));
        assertTrue(union.contains(3));
        assertTrue(union.contains(4));
    }

    // Un test pour la méthode add
    public void testAdd(){
        question1.Ensemble<Integer> e1 = new question1.Ensemble<Integer>();
        assertTrue(e1.add(113));
        assertTrue(e1.add(261));
        assertTrue(e1.add(383));

        assertEquals(3, e1.size());

        assertFalse(e1.add(261));

        assertEquals(3, e1.size());
    }

    // Un test pour la méthode inter
    public void testInter() {
        question1.Ensemble<Integer> e1, e2;
        e1 = new question1.Ensemble<Integer>();
        assertEquals(true, e1.add(10));
        assertEquals(true, e1.add(11));

        e2 = new question1.Ensemble<Integer>();
        assertEquals(true, e2.add(10));
        assertEquals(true, e2.add(11));
        assertEquals(true, e2.add(12));

        question1.Ensemble<Integer> inter = e1.inter(e2);
        assertEquals(2, inter.size());
        assertTrue(inter.contains(10));
        assertTrue(inter.contains(11));
        assertFalse(inter.contains(12));
    }

    // Un test pour la méthode diff
    public void testDiff() {
        question1.Ensemble<Integer> e1, e2;
        e1 = new question1.Ensemble<Integer>();
        assertEquals(true, e1.add(30));
        assertEquals(true, e1.add(33));
        assertEquals(true, e1.add(43));

        e2 = new question1.Ensemble<Integer>();
        assertEquals(true, e2.add(33));
        assertEquals(true, e2.add(53));

        question1.Ensemble<Integer> diff = e1.diff(e2);
        assertEquals(2, diff.size());
        assertTrue(diff.contains(30));
        assertTrue(diff.contains(43));
        assertFalse(diff.contains(33));
        assertFalse(diff.contains(53));
    }

    // Un test pour la méthode diffSym
    public void testDiffSym() {
        question1.Ensemble<Integer> e1, e2;
        e1 = new question1.Ensemble<Integer>();
        assertEquals(true, e1.add(80));
        assertEquals(true, e1.add(81));

        e2 = new question1.Ensemble<Integer>();
        assertEquals(true, e2.add(80));
        assertEquals(true, e2.add(103));

        question1.Ensemble<Integer> diffSym = e1.diffSym(e2);
        assertEquals(2, diffSym.size());
        assertTrue(diffSym.contains(81));
        assertTrue(diffSym.contains(103));
        assertFalse(diffSym.contains(80));
    }

    // Un test pour la méthode toString
    public void testToString(){
        question1.Ensemble<Integer> e1;
        e1 = new question1.Ensemble<Integer>();
        assertEquals(true, e1.add(18));
        assertEquals(true, e1.add(81));
        assertEquals("[18, 81]", e1.toString());
    }

}

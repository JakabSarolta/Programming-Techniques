package ro.tuc.pt;

import org.junit.Test;
import ro.tuc.pt.logic.Operations;
import ro.tuc.pt.model.Monomial;
import ro.tuc.pt.model.Polynomial;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubtractOperationsTest {
    Operations operations = new Operations();

    @Test
    public void subtractTest(){
        Monomial monomial1 = new Monomial(3, 4);
        Monomial monomial2 = new Monomial(2, 4);
        Monomial monomial3 = new Monomial(1, 4);

        ArrayList<Monomial> monomials1 = new ArrayList<>();
        ArrayList<Monomial> monomials2 = new ArrayList<>();
        ArrayList<Monomial> monomials3 = new ArrayList<>();
        monomials1.add(monomial1);
        monomials2.add(monomial2);
        monomials3.add(monomial3);
        Polynomial p1 = new Polynomial(monomials1);
        Polynomial p2 = new Polynomial(monomials2);
        Polynomial p3 = new Polynomial(monomials3);

        assertTrue(operations.subtract(p1, p2).equals(p3), "Correct subtraction");
    }
}

package ro.tuc.pt;

import org.junit.Test;
import ro.tuc.pt.logic.Operations;
import ro.tuc.pt.model.Monomial;
import ro.tuc.pt.model.Polynomial;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InputTest {
    Operations operations = new Operations();

    @Test
    public void testInput(){
        Monomial monomial1 = new Monomial(3, 4);
        Monomial monomial2 = new Monomial(-2, 2);
        Monomial monomial3 = new Monomial(1, 1);

        ArrayList<Monomial> monomials1 = new ArrayList<>();
        monomials1.add(monomial1);
        monomials1.add(monomial2);
        monomials1.add(monomial3);
        Polynomial p1 = new Polynomial(monomials1);

        assertTrue(operations.transformStringToPolynomial("3x^4-2x^2+x^1").equals(p1), "Correct input");
    }
}

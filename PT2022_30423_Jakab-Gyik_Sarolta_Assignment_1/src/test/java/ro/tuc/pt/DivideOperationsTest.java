package ro.tuc.pt;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ro.tuc.pt.logic.Operations;
import ro.tuc.pt.model.Monomial;
import ro.tuc.pt.model.Polynomial;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DivideOperationsTest {
    Operations operations = new Operations();

    @ParameterizedTest
    @MethodSource("provideInput")
    void testDivide(Polynomial p1, Polynomial p2, Polynomial p3){
        assertTrue(operations.divide(p1, p2).equals(p3), "Correct divide");
    }


    private static ArrayList<Arguments> provideInput(){
        ArrayList<Arguments> argumentList = new ArrayList<>();

        //x^3-2x^2+6^x-5
        //x^2-1
        //x-2
        Monomial monomial1 = new Monomial(1, 3);
        Monomial monomial2 = new Monomial(-2, 2);
        Monomial monomial3 = new Monomial(6,1);
        Monomial monomial4 = new Monomial(-5, 0);
        Monomial monomial5 = new Monomial(1, 2);
        Monomial monomial6 = new Monomial(-1, 0);
        Monomial monomial7 = new Monomial(1, 1);
        Monomial monomial8 = new Monomial(-2, 0);

        ArrayList<Monomial> monomials1 = new ArrayList<>();
        ArrayList<Monomial> monomials2 = new ArrayList<>();
        ArrayList<Monomial> monomials3 = new ArrayList<>();
        monomials1.add(monomial1);
        monomials1.add(monomial2);
        monomials1.add(monomial3);
        monomials1.add(monomial4);
        monomials2.add(monomial5);
        monomials2.add(monomial6);
        monomials3.add(monomial7);
        monomials3.add(monomial8);
        Polynomial p1 = new Polynomial(monomials1);
        Polynomial p2 = new Polynomial(monomials2);
        Polynomial p3 = new Polynomial(monomials3);

        argumentList.add(Arguments.of(p1, p2, p3));
        return argumentList;
    }
}

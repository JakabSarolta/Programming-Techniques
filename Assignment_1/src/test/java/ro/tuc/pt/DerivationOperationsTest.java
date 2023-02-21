package ro.tuc.pt;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ro.tuc.pt.logic.Operations;
import ro.tuc.pt.model.Monomial;
import ro.tuc.pt.model.Polynomial;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DerivationOperationsTest {
    Operations operations = new Operations();

    @ParameterizedTest
    @MethodSource("provideInput")
    void testDerivation(Polynomial p1, Polynomial p2){
        assertTrue(operations.derivation(p1).equals(p2), "Correct derivation");
    }


    private static ArrayList<Arguments> provideInput(){
        ArrayList<Arguments> argumentList = new ArrayList<>();

        Monomial monomial1 = new Monomial(3, 4);
        Monomial monomial2 = new Monomial(12, 3);

        ArrayList<Monomial> monomials1 = new ArrayList<>();
        ArrayList<Monomial> monomials2 = new ArrayList<>();
        monomials1.add(monomial1);
        monomials2.add(monomial2);
        Polynomial p1 = new Polynomial(monomials1);
        Polynomial p2 = new Polynomial(monomials2);
        argumentList.add(Arguments.of(p1, p2));
        return argumentList;
    }
}

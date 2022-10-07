package ro.tuc.pt;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ro.tuc.pt.logic.Operations;
import ro.tuc.pt.model.Monomial;
import ro.tuc.pt.model.Polynomial;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddOperationsTest {
    Operations operations = new Operations();

    @ParameterizedTest
    @MethodSource("provideInput")
    void testAddition(Polynomial p1, Polynomial p2, Polynomial p3){
        assertTrue(operations.add(p1, p2).equals(p3), "Correct addition");
    }


    private static ArrayList<Arguments> provideInput(){
        ArrayList<Arguments> argumentList = new ArrayList<>();

        Monomial monomial1 = new Monomial(2, 2);
        Monomial monomial2 = new Monomial(3, 4);


        ArrayList<Monomial> monomials1 = new ArrayList<>();
        ArrayList<Monomial> monomials2 = new ArrayList<>();
        ArrayList<Monomial> monomials3 = new ArrayList<>();
        monomials1.add(monomial1);
        monomials2.add(monomial2);
        monomials3.add(monomial1);
        monomials3.add(monomial2);
        Polynomial p1 = new Polynomial(monomials1);
        Polynomial p2 = new Polynomial(monomials2);
        Polynomial p3 = new Polynomial(monomials3);

        argumentList.add(Arguments.of(p1, p2, p3));
        return argumentList;
    }

    /*@Test
    public void addTest(){
        Monomial monomial1 = new Monomial(2, 2);
        Monomial monomial2 = new Monomial(3, 4);


        ArrayList<Monomial> monomials1 = new ArrayList<>();
        ArrayList<Monomial> monomials2 = new ArrayList<>();
        ArrayList<Monomial> monomials3 = new ArrayList<>();
        monomials1.add(monomial1);
        monomials2.add(monomial2);
        monomials3.add(monomial1);
        monomials3.add(monomial2);
        Polynomial p1 = new Polynomial(monomials1);
        Polynomial p2 = new Polynomial(monomials2);
        Polynomial p3 = new Polynomial(monomials3);

        assertTrue(operations.add(p1, p2).equals(p3), "Correct addition");
    }*/
}

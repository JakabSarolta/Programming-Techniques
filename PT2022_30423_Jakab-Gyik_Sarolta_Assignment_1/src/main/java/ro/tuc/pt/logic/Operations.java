package ro.tuc.pt.logic;

import ro.tuc.pt.model.Monomial;
import ro.tuc.pt.model.Polynomial;

import javax.swing.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Operations {
    //for 2 input operations
    public void validateInput(Polynomial firstPolynomial, Polynomial secondPolynomial){
        if (firstPolynomial.monomials.size() == 0 || secondPolynomial.monomials.size() == 0){
            JOptionPane.showMessageDialog(null, "Missing argument");
        }
    }
    //for 1 input operation
    public void validateInput2(Polynomial firstPolynomial){
        if (firstPolynomial.monomials.size() == 0){
            JOptionPane.showMessageDialog(null, "Missing argument");
        }
    }
    public Polynomial add(Polynomial firstPolynomial, Polynomial secondPolynomial) {
        validateInput(firstPolynomial, secondPolynomial);
        if (firstPolynomial.higherDegree(secondPolynomial) == secondPolynomial){
            Polynomial p = firstPolynomial;
            firstPolynomial = secondPolynomial;
            secondPolynomial = p;
        }
        Polynomial sum = new Polynomial(firstPolynomial.getMonomials());
        for (Monomial m : secondPolynomial.monomials){
            boolean found = false;
            for (Monomial m1 : firstPolynomial.monomials){
                if (m.getPower().equals(m1.getPower())){
                    found = true;
                    sum.addCoefficient(m.getPower(), m.getCoefficient());
                }
            }
            if (!found){
                sum.addMonomial(m);
            }
        }
        return sum;
    }

    public Polynomial subtract(Polynomial firstPolynomial, Polynomial secondPolynomial) {
        validateInput(firstPolynomial, secondPolynomial);
        if (firstPolynomial.higherDegree(secondPolynomial) == secondPolynomial){
            Polynomial p = firstPolynomial;
            firstPolynomial = secondPolynomial;
            secondPolynomial = p;
        }
        Polynomial sum = new Polynomial(firstPolynomial.getMonomials());
        for (int i = 0; i < secondPolynomial.monomials.size(); i++){
            boolean found = false;
            for (int j = 0; j < firstPolynomial.monomials.size(); j++){
                if (secondPolynomial.monomials.get(i).getPower().equals(firstPolynomial.monomials.get(j).getPower())){
                    found = true;
                    sum.addCoefficient(secondPolynomial.monomials.get(i).getPower(), (-1) * secondPolynomial.monomials.get(i).getCoefficient());
                }
            }
            if (!found){
                Monomial newMonomial = new Monomial(-1 * secondPolynomial.monomials.get(i).getCoefficient(), secondPolynomial.monomials.get(i).getPower());
                sum.addMonomial(newMonomial);
            }
        }
        return sum;
    }

    public Polynomial multiply(Polynomial firstPolynomial, Polynomial secondPolynomial) {
        validateInput(firstPolynomial, secondPolynomial);
        Polynomial product = new Polynomial();
        ArrayList<Monomial> monomials = new ArrayList<>();
        for (Monomial m: firstPolynomial.monomials){
            for (Monomial m1: secondPolynomial.monomials){
                if (product.powerExists(m.getPower() + m1.getPower())){
                    product.addCoefficient(m.getPower() + m1.getPower(),m.getCoefficient() * m1.getCoefficient());
                } else{
                    Monomial newMonomial = new Monomial(m.getCoefficient() * m1.getCoefficient(), m.getPower() + m1.getPower());
                    product.addMonomial(newMonomial);
                }
            }
        }
        return product;
    }

    public Polynomial divide(Polynomial firstPolynomial, Polynomial secondPolynomial) {
        validateInput(firstPolynomial, secondPolynomial);
        Polynomial quotient = new Polynomial();
        Polynomial remainder;
        if (firstPolynomial.higherDegree(secondPolynomial) != firstPolynomial){
            Polynomial p = firstPolynomial;
            firstPolynomial = secondPolynomial;
            secondPolynomial = p;
        }
        Monomial m = firstPolynomial.monomials.get(0);
        while (m.getPower() >= secondPolynomial.monomials.get(0).getPower()){
            Monomial newMonomial = new Monomial();
            newMonomial.setPower(m.getPower() - secondPolynomial.monomials.get(0).getPower());
            float c = m.getCoefficient() / secondPolynomial.monomials.get(0).getCoefficient();
            newMonomial.setCoefficient(c);
            quotient.addMonomial(newMonomial);
            Polynomial p = new Polynomial();
            p.addMonomial(newMonomial);
            Polynomial p1 = multiply(p, secondPolynomial);
            remainder = subtract(firstPolynomial, p1);
            if(remainder.monomials.size() == 0){
                break;
            } else{
                m = remainder.monomials.get(0);
            }
        }
        return quotient;
    }

    public Polynomial derivation(Polynomial polynomial) {
        validateInput2(polynomial);
        Polynomial res = new Polynomial();
        ArrayList<Monomial> monomials = new ArrayList<>();

        for (Monomial m: polynomial.monomials){
            Monomial newMonomial = new Monomial();
            newMonomial.setPower(m.getPower() - 1);
            newMonomial.setCoefficient(m.getCoefficient() * m.getPower());
            monomials.add(newMonomial);
        }
        res.setMonomials(monomials);
        return res;
    }

    public Polynomial integrate(Polynomial polynomial) {
        validateInput2(polynomial);
        Polynomial res = new Polynomial();
        ArrayList<Monomial> monomials = new ArrayList<>();

        for (Monomial m: polynomial.monomials){
            Monomial newMonomial = new Monomial();
            newMonomial.setPower(m.getPower() + 1);
            newMonomial.setCoefficient(m.getCoefficient() / newMonomial.getPower());
            monomials.add(newMonomial);
        }
        res.setMonomials(monomials);
        return res;
    }

    public Monomial transformStringToMonomial(String monomial){
        Monomial newMonomial = new Monomial();
        String coeff;
        String power;
        boolean neg = false;
        int j = 0, jj = 0;
        if(monomial.charAt(0) == '-')
        {
            neg = true;
            jj = j;
            j++;
        }
        while (Character.isDigit(monomial.charAt(j)) && (j != monomial.length() - 1)){
            j++;
        }

        if (neg){
            if (j != 1 && (j != monomial.length() - 1)){
                coeff = monomial.substring(0, j);
            }
            else if (j != monomial.length() - 1){
                coeff = "-1";
            } else{
                coeff = monomial.substring(jj, j+1);
            }
            Integer co = Integer.parseInt(coeff);
            System.out.println("The coefficient is: " + co);
            newMonomial.setCoefficient(co);
        } else{
            if (j != 0){
                coeff = monomial.substring(0, j);
            }
            else {
                coeff = "1";
            }
            System.out.println("The coefficient is: " + Integer.parseInt(coeff));
            newMonomial.setCoefficient(Integer.parseInt(coeff));
        }
        if (j == monomial.length() - 1){
            newMonomial.setPower(0);
        } else{
            j += 2;
            power = monomial.substring(j);
            System.out.println("The power is: " + Integer.parseInt(power));
            newMonomial.setPower(Integer.parseInt(power));
        }
        return newMonomial;
    }

    public Polynomial transformStringToPolynomial(String polynomial){
        polynomial = polynomial.replaceAll("\\s", "");
        polynomial = polynomial.replaceAll("-", "+-");
        StringTokenizer st = new StringTokenizer(polynomial,"+");
        String newMonomial;
        ArrayList<Monomial> monomials = new ArrayList<>();
        while (st.hasMoreTokens()) {
            newMonomial = st.nextToken();
            monomials.add(transformStringToMonomial(newMonomial));
        }
        /*if(polynomial.charAt(0) == '+'){
            polynomial = polynomial.substring(1);
        }
        String[] str = polynomial.split("\\+");
        System.out.println("length: " + str.length);
        for (int i = 0; i < str.length; i++){
            System.out.println(i + "th word: " + str[i]);
        }
        ArrayList<Monomial> monomials = new ArrayList<>();
        int curr = 0;
        while(curr != str.length){
            monomials.add(transformStringToMonomial(str[curr]));
            curr++;
        }*/
        return new Polynomial(monomials);
    }

    public String transformPolynomialToString(Polynomial polynomial){
        String poly = "";
        for(Monomial m : polynomial.monomials){
            if (m.getCoefficient() != 0){
                if(m.getCoefficient() != 1 && m.getCoefficient() != -1){
                    if(m.getCoefficient() > 0){
                        poly += " + ";
                    }
                    poly += String.format("%.2f", m.getCoefficient());
                }
                if(m.getPower() != 0){
                    if(m.getCoefficient() == -1){
                        poly += "- x^";
                    } else if(m.getCoefficient() == 1){
                        poly += " + x^";
                    } else{
                        poly += " x^";
                    }
                    poly += Integer.toString(m.getPower());
                }
                if(m.getCoefficient() == 1 && m.getPower() == 0){
                    poly += " + 1";
                }
                if(m.getCoefficient() == -1 && m.getPower() == 0){
                    poly += "- 1";
                }
            }
        }
        //System.out.println("The polynomial is: " + poly.substring(0, poly.length() - 1));
        if (poly.length() != 0 && poly.substring(0, 3).equals(" + "))
        return poly.substring(3);
        else if(poly.length() != 0)
            return poly;
        else return "0";
    }

}

package ro.tuc.pt.model;

import java.util.ArrayList;

public class Polynomial {
    public ArrayList<Monomial> monomials = new ArrayList<>();

    public Polynomial() {
    }

    public Polynomial(ArrayList<Monomial> monomials) {
        this.monomials = monomials;
    }

    public ArrayList<Monomial> getMonomials() {
        return monomials;
    }

    public void setMonomials(ArrayList<Monomial> monomials) {
        this.monomials = monomials;
    }

    public void addMonomial(Monomial monomial){
        if (monomial != null && monomial.getCoefficient() != 0){
            if (monomials != null){
                for (Monomial m : monomials){
                    if (m.equals(monomial)){
                        m.setCoefficient(m.getCoefficient() + monomial.getCoefficient());
                    }
                }

            }
            monomials.add(monomial);
        } else {
            System.out.println("The monomial is not defined");
        }
    }

    public void addCoefficient(Integer power, float coeff){
        boolean found = false;
        int i = 0;
        while(i < monomials.size() && !found) {
            if (monomials.get(i).getPower() == power) {
                monomials.get(i).setCoefficient(monomials.get(i).getCoefficient() + coeff);
                if (monomials.get(i).getCoefficient() == 0) {
                    monomials.remove(monomials.get(i));
                }
                found = true;
            }
            i++;
        }
        if(!found){
            System.out.println("Monomial not found in the polynomial :((");
        }
    }

    public boolean powerExists(Integer power){
        if (monomials != null){
            for(Monomial m : monomials){
                if (m.getPower().equals(power)){
                    return true;
                }
            }
        }
        return false;
    }

    public Polynomial higherDegree(Polynomial p){
        if (this == null || p == null){
            return null;
        }
        if(this.monomials.get(0).getPower() >= p.monomials.get(0).getPower()){
            return this;
        }
        return p;
    }

    @Override
    public boolean equals(Object polynomial) {
        for (int i = 0; i < this.getMonomials().size(); i++){
            if (!((Polynomial)polynomial).getMonomials().get(i).equals(this.getMonomials().get(i))) return false;
        }
        return true;
    }

}

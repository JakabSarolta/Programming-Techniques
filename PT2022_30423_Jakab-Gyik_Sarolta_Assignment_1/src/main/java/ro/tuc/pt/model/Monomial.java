package ro.tuc.pt.model;

import java.util.Objects;

public class Monomial {
    public float coefficient;
    public Integer power;

    public Monomial() {
        coefficient = 0;
        power = 0;
    }

    public Monomial(float coefficient, Integer power) {
        this.coefficient = coefficient;
        this.power = power;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this.getCoefficient() == ((Monomial)o).getCoefficient() && this.getPower() == ((Monomial)o).getPower()) return true;
        return false;
    }
}

package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.RealFieldElement;

public interface FieldButcherArrayProvider {
   RealFieldElement[] getC();

   RealFieldElement[][] getA();

   RealFieldElement[] getB();
}

package org.apache.commons.math3.fraction;

import java.io.Serializable;
import org.apache.commons.math3.Field;

public class FractionField implements Field, Serializable {
   private static final long serialVersionUID = -1257768487499119313L;

   private FractionField() {
   }

   public static FractionField getInstance() {
      return FractionField.LazyHolder.INSTANCE;
   }

   public Fraction getOne() {
      return Fraction.ONE;
   }

   public Fraction getZero() {
      return Fraction.ZERO;
   }

   public Class getRuntimeClass() {
      return Fraction.class;
   }

   private Object readResolve() {
      return FractionField.LazyHolder.INSTANCE;
   }

   private static class LazyHolder {
      private static final FractionField INSTANCE = new FractionField();
   }
}

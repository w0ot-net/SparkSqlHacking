package org.apache.commons.math3.util;

import org.apache.commons.math3.Field;

public class Decimal64Field implements Field {
   private static final Decimal64Field INSTANCE = new Decimal64Field();

   private Decimal64Field() {
   }

   public static final Decimal64Field getInstance() {
      return INSTANCE;
   }

   public Decimal64 getZero() {
      return Decimal64.ZERO;
   }

   public Decimal64 getOne() {
      return Decimal64.ONE;
   }

   public Class getRuntimeClass() {
      return Decimal64.class;
   }
}

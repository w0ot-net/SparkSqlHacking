package breeze.optimize.proximal;

import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

public final class Constraint$ extends Enumeration {
   public static final Constraint$ MODULE$ = new Constraint$();
   private static final Enumeration.Value IDENTITY;
   private static final Enumeration.Value SMOOTH;
   private static final Enumeration.Value POSITIVE;
   private static final Enumeration.Value BOX;
   private static final Enumeration.Value SPARSE;
   private static final Enumeration.Value EQUALITY;
   private static final Enumeration.Value PROBABILITYSIMPLEX;

   static {
      IDENTITY = MODULE$.Value();
      SMOOTH = MODULE$.Value();
      POSITIVE = MODULE$.Value();
      BOX = MODULE$.Value();
      SPARSE = MODULE$.Value();
      EQUALITY = MODULE$.Value();
      PROBABILITYSIMPLEX = MODULE$.Value();
   }

   public Enumeration.Value IDENTITY() {
      return IDENTITY;
   }

   public Enumeration.Value SMOOTH() {
      return SMOOTH;
   }

   public Enumeration.Value POSITIVE() {
      return POSITIVE;
   }

   public Enumeration.Value BOX() {
      return BOX;
   }

   public Enumeration.Value SPARSE() {
      return SPARSE;
   }

   public Enumeration.Value EQUALITY() {
      return EQUALITY;
   }

   public Enumeration.Value PROBABILITYSIMPLEX() {
      return PROBABILITYSIMPLEX;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Constraint$.class);
   }

   private Constraint$() {
   }
}

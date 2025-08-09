package org.glassfish.jersey.internal.guava;

import java.io.Serializable;

public abstract class Equivalence {
   public static Equivalence equals() {
      return Equivalence.Equals.INSTANCE;
   }

   public static Equivalence identity() {
      return Equivalence.Identity.INSTANCE;
   }

   public final boolean equivalent(Object a, Object b) {
      if (a == b) {
         return true;
      } else {
         return a != null && b != null ? this.doEquivalent(a, b) : false;
      }
   }

   protected abstract boolean doEquivalent(Object var1, Object var2);

   public final int hash(Object t) {
      return t == null ? 0 : this.doHash(t);
   }

   protected abstract int doHash(Object var1);

   static final class Equals extends Equivalence implements Serializable {
      static final Equals INSTANCE = new Equals();
      private static final long serialVersionUID = 1L;

      protected boolean doEquivalent(Object a, Object b) {
         return a.equals(b);
      }

      protected int doHash(Object o) {
         return o.hashCode();
      }

      private Object readResolve() {
         return INSTANCE;
      }
   }

   static final class Identity extends Equivalence implements Serializable {
      static final Identity INSTANCE = new Identity();
      private static final long serialVersionUID = 1L;

      protected boolean doEquivalent(Object a, Object b) {
         return false;
      }

      protected int doHash(Object o) {
         return System.identityHashCode(o);
      }

      private Object readResolve() {
         return INSTANCE;
      }
   }
}

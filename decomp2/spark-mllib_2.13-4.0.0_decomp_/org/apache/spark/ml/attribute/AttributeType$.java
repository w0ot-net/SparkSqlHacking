package org.apache.spark.ml.attribute;

import scala.runtime.LazyRef;

public final class AttributeType$ {
   public static final AttributeType$ MODULE$ = new AttributeType$();
   private static final AttributeType Numeric;
   private static final AttributeType Nominal;
   private static final AttributeType Binary;
   private static final AttributeType Unresolved;

   static {
      LazyRef Numeric$module = new LazyRef();
      Numeric = MODULE$.Numeric$2(Numeric$module);
      LazyRef Nominal$module = new LazyRef();
      Nominal = MODULE$.Nominal$2(Nominal$module);
      LazyRef Binary$module = new LazyRef();
      Binary = MODULE$.Binary$2(Binary$module);
      LazyRef Unresolved$module = new LazyRef();
      Unresolved = MODULE$.Unresolved$2(Unresolved$module);
   }

   public AttributeType Numeric() {
      return Numeric;
   }

   public AttributeType Nominal() {
      return Nominal;
   }

   public AttributeType Binary() {
      return Binary;
   }

   public AttributeType Unresolved() {
      return Unresolved;
   }

   public AttributeType fromName(final String name) {
      String var2 = this.Numeric().name();
      if (name == null) {
         if (var2 == null) {
            return this.Numeric();
         }
      } else if (name.equals(var2)) {
         return this.Numeric();
      }

      String var3 = this.Nominal().name();
      if (name == null) {
         if (var3 == null) {
            return this.Nominal();
         }
      } else if (name.equals(var3)) {
         return this.Nominal();
      }

      String var4 = this.Binary().name();
      if (name == null) {
         if (var4 == null) {
            return this.Binary();
         }
      } else if (name.equals(var4)) {
         return this.Binary();
      }

      String var5 = this.Unresolved().name();
      if (name == null) {
         if (var5 == null) {
            return this.Unresolved();
         }
      } else if (name.equals(var5)) {
         return this.Unresolved();
      }

      throw new IllegalArgumentException("Cannot recognize type " + name + ".");
   }

   // $FF: synthetic method
   private static final AttributeType$Numeric$1$ Numeric$lzycompute$1(final LazyRef Numeric$module$1) {
      synchronized(Numeric$module$1){}

      AttributeType$Numeric$1$ var2;
      try {
         var2 = Numeric$module$1.initialized() ? (AttributeType$Numeric$1$)Numeric$module$1.value() : (AttributeType$Numeric$1$)Numeric$module$1.initialize(new AttributeType$Numeric$1$());
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   private final AttributeType$Numeric$1$ Numeric$2(final LazyRef Numeric$module$1) {
      return Numeric$module$1.initialized() ? (AttributeType$Numeric$1$)Numeric$module$1.value() : Numeric$lzycompute$1(Numeric$module$1);
   }

   // $FF: synthetic method
   private static final AttributeType$Nominal$1$ Nominal$lzycompute$1(final LazyRef Nominal$module$1) {
      synchronized(Nominal$module$1){}

      AttributeType$Nominal$1$ var2;
      try {
         var2 = Nominal$module$1.initialized() ? (AttributeType$Nominal$1$)Nominal$module$1.value() : (AttributeType$Nominal$1$)Nominal$module$1.initialize(new AttributeType$Nominal$1$());
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   private final AttributeType$Nominal$1$ Nominal$2(final LazyRef Nominal$module$1) {
      return Nominal$module$1.initialized() ? (AttributeType$Nominal$1$)Nominal$module$1.value() : Nominal$lzycompute$1(Nominal$module$1);
   }

   // $FF: synthetic method
   private static final AttributeType$Binary$1$ Binary$lzycompute$1(final LazyRef Binary$module$1) {
      synchronized(Binary$module$1){}

      AttributeType$Binary$1$ var2;
      try {
         var2 = Binary$module$1.initialized() ? (AttributeType$Binary$1$)Binary$module$1.value() : (AttributeType$Binary$1$)Binary$module$1.initialize(new AttributeType$Binary$1$());
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   private final AttributeType$Binary$1$ Binary$2(final LazyRef Binary$module$1) {
      return Binary$module$1.initialized() ? (AttributeType$Binary$1$)Binary$module$1.value() : Binary$lzycompute$1(Binary$module$1);
   }

   // $FF: synthetic method
   private static final AttributeType$Unresolved$1$ Unresolved$lzycompute$1(final LazyRef Unresolved$module$1) {
      synchronized(Unresolved$module$1){}

      AttributeType$Unresolved$1$ var2;
      try {
         var2 = Unresolved$module$1.initialized() ? (AttributeType$Unresolved$1$)Unresolved$module$1.value() : (AttributeType$Unresolved$1$)Unresolved$module$1.initialize(new AttributeType$Unresolved$1$());
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   private final AttributeType$Unresolved$1$ Unresolved$2(final LazyRef Unresolved$module$1) {
      return Unresolved$module$1.initialized() ? (AttributeType$Unresolved$1$)Unresolved$module$1.value() : Unresolved$lzycompute$1(Unresolved$module$1);
   }

   private AttributeType$() {
   }
}

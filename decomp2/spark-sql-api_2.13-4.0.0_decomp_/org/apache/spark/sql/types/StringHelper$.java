package org.apache.spark.sql.types;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.internal.SqlApiConf$;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Iterator;
import scala.math.PartialOrdering;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

public final class StringHelper$ implements PartialOrdering, Product {
   public static final StringHelper$ MODULE$ = new StringHelper$();

   static {
      PartialOrdering.$init$(MODULE$);
      Product.$init$(MODULE$);
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean lt(final Object x, final Object y) {
      return PartialOrdering.lt$(this, x, y);
   }

   public boolean gt(final Object x, final Object y) {
      return PartialOrdering.gt$(this, x, y);
   }

   public PartialOrdering reverse() {
      return PartialOrdering.reverse$(this);
   }

   public Option tryCompare(final StringConstraint x, final StringConstraint y) {
      Tuple2 var4 = new Tuple2(x, y);
      if (var4 != null) {
         StringConstraint var5 = (StringConstraint)var4._1();
         StringConstraint var6 = (StringConstraint)var4._2();
         if (NoConstraint$.MODULE$.equals(var5) && NoConstraint$.MODULE$.equals(var6)) {
            return new Some(BoxesRunTime.boxToInteger(0));
         }
      }

      if (var4 != null) {
         StringConstraint var7 = (StringConstraint)var4._1();
         if (NoConstraint$.MODULE$.equals(var7)) {
            return new Some(BoxesRunTime.boxToInteger(-1));
         }
      }

      if (var4 != null) {
         StringConstraint var8 = (StringConstraint)var4._2();
         if (NoConstraint$.MODULE$.equals(var8)) {
            return new Some(BoxesRunTime.boxToInteger(1));
         }
      }

      if (var4 != null) {
         StringConstraint var9 = (StringConstraint)var4._1();
         StringConstraint var10 = (StringConstraint)var4._2();
         if (var9 instanceof FixedLength) {
            FixedLength var11 = (FixedLength)var9;
            int l1 = var11.length();
            if (var10 instanceof FixedLength) {
               FixedLength var13 = (FixedLength)var10;
               int l2 = var13.length();
               return new Some(BoxesRunTime.boxToInteger(.MODULE$.int2Integer(l2).compareTo(.MODULE$.int2Integer(l1))));
            }
         }
      }

      if (var4 != null) {
         StringConstraint var15 = (StringConstraint)var4._1();
         StringConstraint var16 = (StringConstraint)var4._2();
         if (var15 instanceof FixedLength) {
            FixedLength var17 = (FixedLength)var15;
            int l1 = var17.length();
            if (var16 instanceof MaxLength) {
               MaxLength var19 = (MaxLength)var16;
               int l2 = var19.length();
               if (l1 <= l2) {
                  return new Some(BoxesRunTime.boxToInteger(1));
               }
            }
         }
      }

      if (var4 != null) {
         StringConstraint var21 = (StringConstraint)var4._1();
         StringConstraint var22 = (StringConstraint)var4._2();
         if (var21 instanceof MaxLength) {
            MaxLength var23 = (MaxLength)var21;
            int l1 = var23.length();
            if (var22 instanceof FixedLength) {
               FixedLength var25 = (FixedLength)var22;
               int l2 = var25.length();
               if (l1 >= l2) {
                  return new Some(BoxesRunTime.boxToInteger(-1));
               }
            }
         }
      }

      if (var4 != null) {
         StringConstraint var27 = (StringConstraint)var4._1();
         StringConstraint var28 = (StringConstraint)var4._2();
         if (var27 instanceof MaxLength) {
            MaxLength var29 = (MaxLength)var27;
            int l1 = var29.length();
            if (var28 instanceof MaxLength) {
               MaxLength var31 = (MaxLength)var28;
               int l2 = var31.length();
               return new Some(BoxesRunTime.boxToInteger(.MODULE$.int2Integer(l2).compareTo(.MODULE$.int2Integer(l1))));
            }
         }
      }

      return scala.None..MODULE$;
   }

   public boolean lteq(final StringConstraint x, final StringConstraint y) {
      return this.tryCompare(x, y).exists((JFunction1.mcZI.sp)(x$1) -> x$1 <= 0);
   }

   public boolean gteq(final StringConstraint x, final StringConstraint y) {
      return this.tryCompare(x, y).exists((JFunction1.mcZI.sp)(x$2) -> x$2 >= 0);
   }

   public boolean equiv(final StringConstraint x, final StringConstraint y) {
      return this.tryCompare(x, y).contains(BoxesRunTime.boxToInteger(0));
   }

   public boolean isPlainString(final StringType s) {
      boolean var3;
      label23: {
         StringConstraint var10000 = s.constraint();
         NoConstraint$ var2 = NoConstraint$.MODULE$;
         if (var10000 == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (var10000.equals(var2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   public boolean isMoreConstrained(final StringType a, final StringType b) {
      return this.gteq(a.constraint(), b.constraint());
   }

   public Option tightestCommonString(final StringType s1, final StringType s2) {
      if (s1.collationId() != s2.collationId()) {
         return scala.None..MODULE$;
      } else if (!SqlApiConf$.MODULE$.get().preserveCharVarcharTypeInfo()) {
         return new Some(StringType$.MODULE$.apply(s1.collationId()));
      } else {
         Some var10000;
         Object var10002;
         label64: {
            var10000 = new Some;
            Tuple2 var4 = new Tuple2(s1.constraint(), s2.constraint());
            if (var4 != null) {
               StringConstraint var5 = (StringConstraint)var4._1();
               StringConstraint var6 = (StringConstraint)var4._2();
               if (var5 instanceof FixedLength) {
                  FixedLength var7 = (FixedLength)var5;
                  int l1 = var7.length();
                  if (var6 instanceof FixedLength) {
                     FixedLength var9 = (FixedLength)var6;
                     int l2 = var9.length();
                     var10002 = new CharType(scala.runtime.RichInt..MODULE$.max$extension(.MODULE$.intWrapper(l1), l2));
                     break label64;
                  }
               }
            }

            if (var4 != null) {
               StringConstraint var11 = (StringConstraint)var4._1();
               StringConstraint var12 = (StringConstraint)var4._2();
               if (var11 instanceof MaxLength) {
                  MaxLength var13 = (MaxLength)var11;
                  int l1 = var13.length();
                  if (var12 instanceof FixedLength) {
                     FixedLength var15 = (FixedLength)var12;
                     int l2 = var15.length();
                     var10002 = new VarcharType(scala.runtime.RichInt..MODULE$.max$extension(.MODULE$.intWrapper(l1), l2));
                     break label64;
                  }
               }
            }

            if (var4 != null) {
               StringConstraint var17 = (StringConstraint)var4._1();
               StringConstraint var18 = (StringConstraint)var4._2();
               if (var17 instanceof FixedLength) {
                  FixedLength var19 = (FixedLength)var17;
                  int l1 = var19.length();
                  if (var18 instanceof MaxLength) {
                     MaxLength var21 = (MaxLength)var18;
                     int l2 = var21.length();
                     var10002 = new VarcharType(scala.runtime.RichInt..MODULE$.max$extension(.MODULE$.intWrapper(l1), l2));
                     break label64;
                  }
               }
            }

            if (var4 != null) {
               StringConstraint var23 = (StringConstraint)var4._1();
               StringConstraint var24 = (StringConstraint)var4._2();
               if (var23 instanceof MaxLength) {
                  MaxLength var25 = (MaxLength)var23;
                  int l1 = var25.length();
                  if (var24 instanceof MaxLength) {
                     MaxLength var27 = (MaxLength)var24;
                     int l2 = var27.length();
                     var10002 = new VarcharType(scala.runtime.RichInt..MODULE$.max$extension(.MODULE$.intWrapper(l1), l2));
                     break label64;
                  }
               }
            }

            var10002 = StringType$.MODULE$.apply(s1.collationId());
         }

         var10000.<init>(var10002);
         return var10000;
      }
   }

   public StringType removeCollation(final StringType s) {
      if (s instanceof CharType var4) {
         int length = var4.length();
         return new CharType(length);
      } else if (s instanceof VarcharType var6) {
         int length = var6.length();
         return new VarcharType(length);
      } else if (s != null) {
         return StringType$.MODULE$;
      } else {
         throw new MatchError(s);
      }
   }

   public String productPrefix() {
      return "StringHelper";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      return Statics.ioobe(x$1);
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof StringHelper$;
   }

   public int hashCode() {
      return -83779777;
   }

   public String toString() {
      return "StringHelper";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StringHelper$.class);
   }

   private StringHelper$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

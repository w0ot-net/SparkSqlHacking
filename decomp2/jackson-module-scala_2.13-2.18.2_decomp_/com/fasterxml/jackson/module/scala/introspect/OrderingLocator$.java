package com.fasterxml.jackson.module.scala.introspect;

import com.fasterxml.jackson.databind.JavaType;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.math.Ordering;
import scala.math.PartialOrdering;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class OrderingLocator$ {
   public static final OrderingLocator$ MODULE$ = new OrderingLocator$();
   private static final Map ORDERINGS;

   static {
      ORDERINGS = (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(BoxedUnit.TYPE), scala.math.Ordering.Unit..MODULE$), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(Boolean.TYPE), scala.math.Ordering.Boolean..MODULE$), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(Byte.TYPE), scala.math.Ordering.Byte..MODULE$), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(Character.TYPE), scala.math.Ordering.Char..MODULE$), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(Short.TYPE), scala.math.Ordering.Short..MODULE$), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(Integer.TYPE), scala.math.Ordering.Int..MODULE$), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(Long.TYPE), scala.math.Ordering.Long..MODULE$), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(Float.TYPE), .MODULE$.implicitly(scala.math.Ordering.DeprecatedFloatOrdering..MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(Double.TYPE), .MODULE$.implicitly(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(BigInt.class), scala.math.Ordering.BigInt..MODULE$), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(BigDecimal.class), scala.math.Ordering.BigDecimal..MODULE$), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(String.class), scala.math.Ordering.String..MODULE$)})));
   }

   public Map ORDERINGS() {
      return ORDERINGS;
   }

   public Ordering locate(final JavaType javaType) {
      Option found = this.ORDERINGS().find((x$1) -> BoxesRunTime.boxToBoolean($anonfun$locate$1(javaType, x$1))).map((x$2) -> (Ordering)x$2._2());
      Ordering ordering = (Ordering)found.getOrElse(() -> {
         if (matches$1(Option.class, javaType)) {
            Ordering delegate = MODULE$.locate(javaType.getContentType());
            return scala.package..MODULE$.Ordering().Option(delegate);
         } else if (matches$1(Comparable.class, javaType)) {
            return new Ordering() {
               public Some tryCompare(final Object x, final Object y) {
                  return Ordering.tryCompare$(this, x, y);
               }

               public boolean lteq(final Object x, final Object y) {
                  return Ordering.lteq$(this, x, y);
               }

               public boolean gteq(final Object x, final Object y) {
                  return Ordering.gteq$(this, x, y);
               }

               public boolean lt(final Object x, final Object y) {
                  return Ordering.lt$(this, x, y);
               }

               public boolean gt(final Object x, final Object y) {
                  return Ordering.gt$(this, x, y);
               }

               public boolean equiv(final Object x, final Object y) {
                  return Ordering.equiv$(this, x, y);
               }

               public Object max(final Object x, final Object y) {
                  return Ordering.max$(this, x, y);
               }

               public Object min(final Object x, final Object y) {
                  return Ordering.min$(this, x, y);
               }

               public Ordering reverse() {
                  return Ordering.reverse$(this);
               }

               public boolean isReverseOf(final Ordering other) {
                  return Ordering.isReverseOf$(this, other);
               }

               public Ordering on(final Function1 f) {
                  return Ordering.on$(this, f);
               }

               public Ordering orElse(final Ordering other) {
                  return Ordering.orElse$(this, other);
               }

               public Ordering orElseBy(final Function1 f, final Ordering ord) {
                  return Ordering.orElseBy$(this, f, ord);
               }

               public Ordering.OrderingOps mkOrderingOps(final Object lhs) {
                  return Ordering.mkOrderingOps$(this, lhs);
               }

               public int compare(final Object x, final Object y) {
                  return ((Comparable)x).compareTo(y);
               }

               public {
                  PartialOrdering.$init$(this);
                  Ordering.$init$(this);
               }
            };
         } else {
            throw new IllegalArgumentException((new StringBuilder(24)).append("Unsupported value type: ").append(javaType.getRawClass().getCanonicalName()).toString());
         }
      });
      return ordering;
   }

   private static final boolean matches$1(final Class other, final JavaType javaType$1) {
      return other.isAssignableFrom(javaType$1.getRawClass());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$locate$1(final JavaType javaType$1, final Tuple2 x$1) {
      return ((Class)x$1._1()).isAssignableFrom(javaType$1.getRawClass());
   }

   private OrderingLocator$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

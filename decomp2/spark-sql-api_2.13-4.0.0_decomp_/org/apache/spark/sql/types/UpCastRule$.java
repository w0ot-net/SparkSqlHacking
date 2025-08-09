package org.apache.spark.sql.types;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.IndexedSeq;
import scala.package.;
import scala.runtime.BoxesRunTime;

public final class UpCastRule$ {
   public static final UpCastRule$ MODULE$ = new UpCastRule$();
   private static final IndexedSeq numericPrecedence;

   static {
      numericPrecedence = (IndexedSeq).MODULE$.IndexedSeq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new NumericType[]{ByteType$.MODULE$, ShortType$.MODULE$, IntegerType$.MODULE$, LongType$.MODULE$, FloatType$.MODULE$, DoubleType$.MODULE$}));
   }

   public IndexedSeq numericPrecedence() {
      return numericPrecedence;
   }

   public boolean canUpCast(final DataType from, final DataType to) {
      while(true) {
         Tuple2 var5 = new Tuple2(from, to);
         if (from == null) {
            if (to == null) {
               break;
            }
         } else if (from.equals(to)) {
            break;
         }

         if (var5 != null) {
            DataType var7 = (DataType)var5._1();
            if (VariantType$.MODULE$.equals(var7)) {
               return false;
            }
         }

         if (var5 != null) {
            DataType from = (DataType)var5._1();
            DataType to = (DataType)var5._2();
            if (from instanceof NumericType) {
               NumericType var10 = (NumericType)from;
               if (to instanceof DecimalType) {
                  DecimalType var11 = (DecimalType)to;
                  if (var11.isWiderThan(var10)) {
                     return true;
                  }
               }
            }
         }

         if (var5 != null) {
            DataType from = (DataType)var5._1();
            DataType to = (DataType)var5._2();
            if (from instanceof DecimalType) {
               DecimalType var14 = (DecimalType)from;
               if (to instanceof NumericType) {
                  NumericType var15 = (NumericType)to;
                  if (var14.isTighterThan(var15)) {
                     return true;
                  }
               }
            }
         }

         if (var5 != null) {
            DataType f = (DataType)var5._1();
            DataType t = (DataType)var5._2();
            if (this.legalNumericPrecedence(f, t)) {
               return true;
            }
         }

         if (var5 != null) {
            DataType var18 = (DataType)var5._1();
            DataType var19 = (DataType)var5._2();
            if (DateType$.MODULE$.equals(var18) && TimestampType$.MODULE$.equals(var19)) {
               return true;
            }
         }

         if (var5 != null) {
            DataType var20 = (DataType)var5._1();
            DataType var21 = (DataType)var5._2();
            if (DateType$.MODULE$.equals(var20) && TimestampNTZType$.MODULE$.equals(var21)) {
               return true;
            }
         }

         if (var5 != null) {
            DataType var22 = (DataType)var5._1();
            DataType var23 = (DataType)var5._2();
            if (TimestampNTZType$.MODULE$.equals(var22) && TimestampType$.MODULE$.equals(var23)) {
               return true;
            }
         }

         if (var5 != null) {
            DataType var24 = (DataType)var5._1();
            DataType var25 = (DataType)var5._2();
            if (TimestampType$.MODULE$.equals(var24) && TimestampNTZType$.MODULE$.equals(var25)) {
               return true;
            }
         }

         if (var5 != null) {
            DataType s1 = (DataType)var5._1();
            DataType s2 = (DataType)var5._2();
            if (s1 instanceof StringType) {
               StringType var28 = (StringType)s1;
               if (s2 instanceof StringType) {
                  StringType var29 = (StringType)s2;
                  return StringHelper$.MODULE$.isMoreConstrained(var28, var29);
               }
            }
         }

         if (var5 != null) {
            DataType s = (DataType)var5._2();
            if (var5._1() instanceof AtomicType && s instanceof StringType) {
               StringType var31 = (StringType)s;
               return StringHelper$.MODULE$.isPlainString(var31);
            }
         }

         if (var5 != null) {
            DataType s = (DataType)var5._2();
            if (var5._1() instanceof CalendarIntervalType && s instanceof StringType) {
               StringType var33 = (StringType)s;
               return StringHelper$.MODULE$.isPlainString(var33);
            }
         }

         if (var5 != null) {
            DataType var34 = (DataType)var5._1();
            if (NullType$.MODULE$.equals(var34)) {
               return true;
            }
         }

         if (var5 != null) {
            DataType var35 = (DataType)var5._1();
            DataType var36 = (DataType)var5._2();
            if (TimestampType$.MODULE$.equals(var35) && LongType$.MODULE$.equals(var36)) {
               return true;
            }
         }

         if (var5 != null) {
            DataType var37 = (DataType)var5._1();
            DataType var38 = (DataType)var5._2();
            if (LongType$.MODULE$.equals(var37) && TimestampType$.MODULE$.equals(var38)) {
               return true;
            }
         }

         if (var5 != null) {
            DataType var39 = (DataType)var5._1();
            DataType var40 = (DataType)var5._2();
            if (var39 instanceof ArrayType) {
               ArrayType var41 = (ArrayType)var39;
               DataType fromType = var41.elementType();
               boolean fn = var41.containsNull();
               if (var40 instanceof ArrayType) {
                  ArrayType var44 = (ArrayType)var40;
                  DataType toType = var44.elementType();
                  boolean tn = var44.containsNull();
                  if (this.resolvableNullability(fn, tn)) {
                     to = toType;
                     from = fromType;
                     continue;
                  }

                  return false;
               }
            }
         }

         if (var5 != null) {
            DataType var47 = (DataType)var5._1();
            DataType var48 = (DataType)var5._2();
            if (var47 instanceof MapType) {
               MapType var49 = (MapType)var47;
               DataType fromKey = var49.keyType();
               DataType fromValue = var49.valueType();
               boolean fn = var49.valueContainsNull();
               if (var48 instanceof MapType) {
                  MapType var53 = (MapType)var48;
                  DataType toKey = var53.keyType();
                  DataType toValue = var53.valueType();
                  boolean tn = var53.valueContainsNull();
                  if (this.resolvableNullability(fn, tn) && this.canUpCast(fromKey, toKey)) {
                     to = toValue;
                     from = fromValue;
                     continue;
                  }

                  return false;
               }
            }
         }

         if (var5 != null) {
            DataType var57 = (DataType)var5._1();
            DataType var58 = (DataType)var5._2();
            if (var57 instanceof StructType) {
               StructType var59 = (StructType)var57;
               StructField[] fromFields = var59.fields();
               if (var58 instanceof StructType) {
                  StructType var61 = (StructType)var58;
                  StructField[] toFields = var61.fields();
                  return fromFields.length == toFields.length && scala.collection.ArrayOps..MODULE$.forall$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps(fromFields), scala.Predef..MODULE$.wrapRefArray(toFields))), (x0$1) -> BoxesRunTime.boxToBoolean($anonfun$canUpCast$1(x0$1)));
               }
            }
         }

         if (var5 != null && var5._1() instanceof DayTimeIntervalType && var5._2() instanceof DayTimeIntervalType) {
            return true;
         }

         if (var5 != null && var5._1() instanceof YearMonthIntervalType && var5._2() instanceof YearMonthIntervalType) {
            return true;
         }

         if (var5 != null) {
            DataType from = (DataType)var5._1();
            DataType to = (DataType)var5._2();
            if (from instanceof UserDefinedType) {
               UserDefinedType var65 = (UserDefinedType)from;
               if (to instanceof UserDefinedType) {
                  UserDefinedType var66 = (UserDefinedType)to;
                  if (var66.acceptsType(var65)) {
                     return true;
                  }
               }
            }
         }

         return false;
      }

      return true;
   }

   public boolean legalNumericPrecedence(final DataType from, final DataType to) {
      int fromPrecedence = this.numericPrecedence().indexOf(from);
      int toPrecedence = this.numericPrecedence().indexOf(to);
      return fromPrecedence >= 0 && fromPrecedence < toPrecedence;
   }

   private boolean resolvableNullability(final boolean from, final boolean to) {
      return !from || to;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$canUpCast$1(final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         StructField f1 = (StructField)x0$1._1();
         StructField f2 = (StructField)x0$1._2();
         return MODULE$.resolvableNullability(f1.nullable(), f2.nullable()) && MODULE$.canUpCast(f1.dataType(), f2.dataType());
      }
   }

   private UpCastRule$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

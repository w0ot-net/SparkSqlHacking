package org.apache.spark.sql.types;

import java.io.Serializable;
import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.errors.DataTypeErrors$;
import org.apache.spark.sql.internal.SqlApiConf$;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.math.package.;
import scala.runtime.ModuleSerializationProxy;

@Stable
public final class DecimalType$ extends AbstractDataType implements Serializable {
   public static final DecimalType$ MODULE$ = new DecimalType$();
   private static final int MAX_PRECISION = 38;
   private static final int MAX_SCALE = 38;
   private static final int DEFAULT_SCALE = 18;
   private static final DecimalType SYSTEM_DEFAULT;
   private static final DecimalType USER_DEFAULT;
   private static final int MINIMUM_ADJUSTED_SCALE;
   private static final DecimalType BooleanDecimal;
   private static final DecimalType ByteDecimal;
   private static final DecimalType ShortDecimal;
   private static final DecimalType IntDecimal;
   private static final DecimalType LongDecimal;
   private static final DecimalType FloatDecimal;
   private static final DecimalType DoubleDecimal;
   private static final DecimalType BigIntDecimal;

   static {
      SYSTEM_DEFAULT = new DecimalType(MODULE$.MAX_PRECISION(), MODULE$.DEFAULT_SCALE());
      USER_DEFAULT = new DecimalType(10, 0);
      MINIMUM_ADJUSTED_SCALE = 6;
      BooleanDecimal = new DecimalType(1, 0);
      ByteDecimal = new DecimalType(3, 0);
      ShortDecimal = new DecimalType(5, 0);
      IntDecimal = new DecimalType(10, 0);
      LongDecimal = new DecimalType(20, 0);
      FloatDecimal = new DecimalType(14, 7);
      DoubleDecimal = new DecimalType(30, 15);
      BigIntDecimal = new DecimalType(38, 0);
   }

   public int MAX_PRECISION() {
      return MAX_PRECISION;
   }

   public int MAX_SCALE() {
      return MAX_SCALE;
   }

   public int DEFAULT_SCALE() {
      return DEFAULT_SCALE;
   }

   public DecimalType SYSTEM_DEFAULT() {
      return SYSTEM_DEFAULT;
   }

   public DecimalType USER_DEFAULT() {
      return USER_DEFAULT;
   }

   public int MINIMUM_ADJUSTED_SCALE() {
      return MINIMUM_ADJUSTED_SCALE;
   }

   public DecimalType BooleanDecimal() {
      return BooleanDecimal;
   }

   public DecimalType ByteDecimal() {
      return ByteDecimal;
   }

   public DecimalType ShortDecimal() {
      return ShortDecimal;
   }

   public DecimalType IntDecimal() {
      return IntDecimal;
   }

   public DecimalType LongDecimal() {
      return LongDecimal;
   }

   public DecimalType FloatDecimal() {
      return FloatDecimal;
   }

   public DecimalType DoubleDecimal() {
      return DoubleDecimal;
   }

   public DecimalType BigIntDecimal() {
      return BigIntDecimal;
   }

   public DecimalType forType(final DataType dataType) {
      if (ByteType$.MODULE$.equals(dataType)) {
         return this.ByteDecimal();
      } else if (ShortType$.MODULE$.equals(dataType)) {
         return this.ShortDecimal();
      } else if (IntegerType$.MODULE$.equals(dataType)) {
         return this.IntDecimal();
      } else if (LongType$.MODULE$.equals(dataType)) {
         return this.LongDecimal();
      } else if (FloatType$.MODULE$.equals(dataType)) {
         return this.FloatDecimal();
      } else if (DoubleType$.MODULE$.equals(dataType)) {
         return this.DoubleDecimal();
      } else {
         throw new MatchError(dataType);
      }
   }

   public DecimalType fromDecimal(final Decimal d) {
      return new DecimalType(d.precision(), d.scale());
   }

   public DecimalType bounded(final int precision, final int scale) {
      return new DecimalType(.MODULE$.min(precision, this.MAX_PRECISION()), .MODULE$.min(scale, this.MAX_SCALE()));
   }

   public DecimalType boundedPreferIntegralDigits(final int precision, final int scale) {
      if (precision <= this.MAX_PRECISION()) {
         return new DecimalType(precision, scale);
      } else {
         int diff = precision - this.MAX_PRECISION();
         return new DecimalType(this.MAX_PRECISION(), .MODULE$.max(0, scale - diff));
      }
   }

   public void checkNegativeScale(final int scale) {
      if (scale < 0 && !SqlApiConf$.MODULE$.get().allowNegativeScaleOfDecimalEnabled()) {
         throw DataTypeErrors$.MODULE$.negativeScaleNotAllowedError(scale);
      }
   }

   public DecimalType adjustPrecisionScale(final int precision, final int scale) {
      this.checkNegativeScale(scale);
      scala.Predef..MODULE$.assert(precision >= scale);
      if (precision <= this.MAX_PRECISION()) {
         return new DecimalType(precision, scale);
      } else if (scale < 0) {
         return new DecimalType(this.MAX_PRECISION(), scale);
      } else {
         int intDigits = precision - scale;
         int minScaleValue = Math.min(scale, this.MINIMUM_ADJUSTED_SCALE());
         int adjustedScale = Math.max(this.MAX_PRECISION() - intDigits, minScaleValue);
         return new DecimalType(this.MAX_PRECISION(), adjustedScale);
      }
   }

   public DataType defaultConcreteType() {
      return this.SYSTEM_DEFAULT();
   }

   public boolean acceptsType(final DataType other) {
      return other instanceof DecimalType;
   }

   public String simpleString() {
      return "decimal";
   }

   public boolean is32BitDecimalType(final DataType dt) {
      if (dt instanceof DecimalType var4) {
         return var4.precision() <= Decimal$.MODULE$.MAX_INT_DIGITS();
      } else {
         return false;
      }
   }

   public boolean is64BitDecimalType(final DataType dt) {
      if (dt instanceof DecimalType var4) {
         return var4.precision() <= Decimal$.MODULE$.MAX_LONG_DIGITS();
      } else {
         return false;
      }
   }

   public boolean isByteArrayDecimalType(final DataType dt) {
      if (dt instanceof DecimalType var4) {
         return var4.precision() > Decimal$.MODULE$.MAX_LONG_DIGITS();
      } else {
         return false;
      }
   }

   public boolean unapply(final DataType t) {
      return t instanceof DecimalType;
   }

   public DecimalType apply(final int precision, final int scale) {
      return new DecimalType(precision, scale);
   }

   public Option unapply(final DecimalType x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2.mcII.sp(x$0.precision(), x$0.scale())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DecimalType$.class);
   }

   private DecimalType$() {
   }
}

package org.apache.spark.sql.types;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.Unstable;
import org.apache.spark.sql.errors.DataTypeErrors$;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@Unstable
public final class DayTimeIntervalType$ extends AbstractDataType implements Product, Serializable {
   public static final DayTimeIntervalType$ MODULE$ = new DayTimeIntervalType$();
   private static final byte DAY;
   private static final byte HOUR;
   private static final byte MINUTE;
   private static final byte SECOND;
   private static final Seq dayTimeFields;
   private static final Map stringToField;
   private static final DayTimeIntervalType DEFAULT;

   static {
      Product.$init$(MODULE$);
      DAY = 0;
      HOUR = 1;
      MINUTE = 2;
      SECOND = 3;
      dayTimeFields = (Seq).MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapByteArray(new byte[]{MODULE$.DAY(), MODULE$.HOUR(), MODULE$.MINUTE(), MODULE$.SECOND()}));
      stringToField = ((IterableOnceOps)MODULE$.dayTimeFields().map((i) -> $anonfun$stringToField$1(BoxesRunTime.unboxToByte(i)))).toMap(scala..less.colon.less..MODULE$.refl());
      DEFAULT = new DayTimeIntervalType(MODULE$.DAY(), MODULE$.SECOND());
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public byte DAY() {
      return DAY;
   }

   public byte HOUR() {
      return HOUR;
   }

   public byte MINUTE() {
      return MINUTE;
   }

   public byte SECOND() {
      return SECOND;
   }

   public Seq dayTimeFields() {
      return dayTimeFields;
   }

   public String fieldToString(final byte field) {
      if (this.DAY() == field) {
         return "day";
      } else if (this.HOUR() == field) {
         return "hour";
      } else if (this.MINUTE() == field) {
         return "minute";
      } else if (this.SECOND() == field) {
         return "second";
      } else {
         Seq supportedIds = (Seq)this.dayTimeFields().map((i) -> $anonfun$fieldToString$1(BoxesRunTime.unboxToByte(i)));
         throw DataTypeErrors$.MODULE$.invalidDayTimeField(field, supportedIds);
      }
   }

   public Map stringToField() {
      return stringToField;
   }

   public DayTimeIntervalType DEFAULT() {
      return DEFAULT;
   }

   public DayTimeIntervalType apply() {
      return this.DEFAULT();
   }

   public DayTimeIntervalType apply(final byte field) {
      return new DayTimeIntervalType(field, field);
   }

   public DataType defaultConcreteType() {
      return this.DEFAULT();
   }

   public boolean acceptsType(final DataType other) {
      return other instanceof DayTimeIntervalType;
   }

   public String simpleString() {
      return this.defaultConcreteType().simpleString();
   }

   public DayTimeIntervalType apply(final byte startField, final byte endField) {
      return new DayTimeIntervalType(startField, endField);
   }

   public Option unapply(final DayTimeIntervalType x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToByte(x$0.startField()), BoxesRunTime.boxToByte(x$0.endField()))));
   }

   public String productPrefix() {
      return "DayTimeIntervalType";
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
      return x$1 instanceof DayTimeIntervalType$;
   }

   public int hashCode() {
      return 218607784;
   }

   public String toString() {
      return "DayTimeIntervalType";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DayTimeIntervalType$.class);
   }

   // $FF: synthetic method
   public static final String $anonfun$fieldToString$1(final byte i) {
      return i + " (" + MODULE$.fieldToString(i) + ")";
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$stringToField$1(final byte i) {
      return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(MODULE$.fieldToString(i)), BoxesRunTime.boxToByte(i));
   }

   private DayTimeIntervalType$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

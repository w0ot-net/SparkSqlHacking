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
public final class YearMonthIntervalType$ extends AbstractDataType implements Product, Serializable {
   public static final YearMonthIntervalType$ MODULE$ = new YearMonthIntervalType$();
   private static final byte YEAR;
   private static final byte MONTH;
   private static final Seq yearMonthFields;
   private static final Map stringToField;
   private static final YearMonthIntervalType DEFAULT;

   static {
      Product.$init$(MODULE$);
      YEAR = 0;
      MONTH = 1;
      yearMonthFields = (Seq).MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapByteArray(new byte[]{MODULE$.YEAR(), MODULE$.MONTH()}));
      stringToField = ((IterableOnceOps)MODULE$.yearMonthFields().map((i) -> $anonfun$stringToField$1(BoxesRunTime.unboxToByte(i)))).toMap(scala..less.colon.less..MODULE$.refl());
      DEFAULT = new YearMonthIntervalType(MODULE$.YEAR(), MODULE$.MONTH());
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public byte YEAR() {
      return YEAR;
   }

   public byte MONTH() {
      return MONTH;
   }

   public Seq yearMonthFields() {
      return yearMonthFields;
   }

   public String fieldToString(final byte field) {
      if (this.YEAR() == field) {
         return "year";
      } else if (this.MONTH() == field) {
         return "month";
      } else {
         Seq supportedIds = (Seq)this.yearMonthFields().map((i) -> $anonfun$fieldToString$1(BoxesRunTime.unboxToByte(i)));
         throw DataTypeErrors$.MODULE$.invalidYearMonthField(field, supportedIds);
      }
   }

   public Map stringToField() {
      return stringToField;
   }

   public YearMonthIntervalType DEFAULT() {
      return DEFAULT;
   }

   public YearMonthIntervalType apply() {
      return this.DEFAULT();
   }

   public YearMonthIntervalType apply(final byte field) {
      return new YearMonthIntervalType(field, field);
   }

   public DataType defaultConcreteType() {
      return this.DEFAULT();
   }

   public boolean acceptsType(final DataType other) {
      return other instanceof YearMonthIntervalType;
   }

   public String simpleString() {
      return this.defaultConcreteType().simpleString();
   }

   public YearMonthIntervalType apply(final byte startField, final byte endField) {
      return new YearMonthIntervalType(startField, endField);
   }

   public Option unapply(final YearMonthIntervalType x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToByte(x$0.startField()), BoxesRunTime.boxToByte(x$0.endField()))));
   }

   public String productPrefix() {
      return "YearMonthIntervalType";
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
      return x$1 instanceof YearMonthIntervalType$;
   }

   public int hashCode() {
      return -769745566;
   }

   public String toString() {
      return "YearMonthIntervalType";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(YearMonthIntervalType$.class);
   }

   // $FF: synthetic method
   public static final String $anonfun$fieldToString$1(final byte i) {
      return i + " (" + MODULE$.fieldToString(i) + ")";
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$stringToField$1(final byte i) {
      return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(MODULE$.fieldToString(i)), BoxesRunTime.boxToByte(i));
   }

   private YearMonthIntervalType$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

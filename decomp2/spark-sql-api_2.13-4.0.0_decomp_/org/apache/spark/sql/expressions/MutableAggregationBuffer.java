package org.apache.spark.sql.expressions;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import org.apache.spark.annotation.Stable;
import org.apache.spark.annotation.Unstable;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructType;
import org.json4s.JValue;
import scala.collection.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005Q2Qa\u0001\u0003\u0002\u0002=AQA\u0007\u0001\u0005\u0002mAQA\b\u0001\u0007\u0002}\u0011\u0001$T;uC\ndW-Q4he\u0016<\u0017\r^5p]\n+hMZ3s\u0015\t)a!A\u0006fqB\u0014Xm]:j_:\u001c(BA\u0004\t\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003\u0013)\tQa\u001d9be.T!a\u0003\u0007\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005i\u0011aA8sO\u000e\u00011c\u0001\u0001\u0011-A\u0011\u0011\u0003F\u0007\u0002%)\t1#A\u0003tG\u0006d\u0017-\u0003\u0002\u0016%\t1\u0011I\\=SK\u001a\u0004\"a\u0006\r\u000e\u0003\u0019I!!\u0007\u0004\u0003\u0007I{w/\u0001\u0004=S:LGO\u0010\u000b\u00029A\u0011Q\u0004A\u0007\u0002\t\u00051Q\u000f\u001d3bi\u0016$2\u0001I\u0012)!\t\t\u0012%\u0003\u0002#%\t!QK\\5u\u0011\u0015!#\u00011\u0001&\u0003\u0005I\u0007CA\t'\u0013\t9#CA\u0002J]RDQ!\u000b\u0002A\u0002)\nQA^1mk\u0016\u0004\"!E\u0016\n\u00051\u0012\"aA!os\"\u0012\u0001A\f\t\u0003_Ij\u0011\u0001\r\u0006\u0003c!\t!\"\u00198o_R\fG/[8o\u0013\t\u0019\u0004G\u0001\u0004Ti\u0006\u0014G.\u001a"
)
public abstract class MutableAggregationBuffer implements Row {
   public int size() {
      return Row.size$(this);
   }

   public StructType schema() {
      return Row.schema$(this);
   }

   public Object apply(final int i) {
      return Row.apply$(this, i);
   }

   public boolean isNullAt(final int i) {
      return Row.isNullAt$(this, i);
   }

   public boolean getBoolean(final int i) {
      return Row.getBoolean$(this, i);
   }

   public byte getByte(final int i) {
      return Row.getByte$(this, i);
   }

   public short getShort(final int i) {
      return Row.getShort$(this, i);
   }

   public int getInt(final int i) {
      return Row.getInt$(this, i);
   }

   public long getLong(final int i) {
      return Row.getLong$(this, i);
   }

   public float getFloat(final int i) {
      return Row.getFloat$(this, i);
   }

   public double getDouble(final int i) {
      return Row.getDouble$(this, i);
   }

   public String getString(final int i) {
      return Row.getString$(this, i);
   }

   public BigDecimal getDecimal(final int i) {
      return Row.getDecimal$(this, i);
   }

   public Date getDate(final int i) {
      return Row.getDate$(this, i);
   }

   public LocalDate getLocalDate(final int i) {
      return Row.getLocalDate$(this, i);
   }

   public Timestamp getTimestamp(final int i) {
      return Row.getTimestamp$(this, i);
   }

   public Instant getInstant(final int i) {
      return Row.getInstant$(this, i);
   }

   public Seq getSeq(final int i) {
      return Row.getSeq$(this, i);
   }

   public List getList(final int i) {
      return Row.getList$(this, i);
   }

   public Map getMap(final int i) {
      return Row.getMap$(this, i);
   }

   public java.util.Map getJavaMap(final int i) {
      return Row.getJavaMap$(this, i);
   }

   public Row getStruct(final int i) {
      return Row.getStruct$(this, i);
   }

   public Object getAs(final int i) {
      return Row.getAs$(this, i);
   }

   public Object getAs(final String fieldName) {
      return Row.getAs$(this, fieldName);
   }

   public int fieldIndex(final String name) {
      return Row.fieldIndex$(this, name);
   }

   public scala.collection.immutable.Map getValuesMap(final Seq fieldNames) {
      return Row.getValuesMap$(this, fieldNames);
   }

   public String toString() {
      return Row.toString$(this);
   }

   public boolean anyNull() {
      return Row.anyNull$(this);
   }

   public boolean equals(final Object o) {
      return Row.equals$(this, o);
   }

   public int hashCode() {
      return Row.hashCode$(this);
   }

   public Seq toSeq() {
      return Row.toSeq$(this);
   }

   public String mkString() {
      return Row.mkString$(this);
   }

   public String mkString(final String sep) {
      return Row.mkString$(this, sep);
   }

   public String mkString(final String start, final String sep, final String end) {
      return Row.mkString$(this, start, sep, end);
   }

   @Unstable
   public String json() {
      return Row.json$(this);
   }

   @Unstable
   public String prettyJson() {
      return Row.prettyJson$(this);
   }

   public JValue jsonValue() {
      return Row.jsonValue$(this);
   }

   public abstract void update(final int i, final Object value);

   public MutableAggregationBuffer() {
      Row.$init$(this);
   }
}

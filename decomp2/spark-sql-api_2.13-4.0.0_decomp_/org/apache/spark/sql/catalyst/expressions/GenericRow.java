package org.apache.spark.sql.catalyst.expressions;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import org.apache.spark.annotation.Unstable;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.util.ArrayImplicits.;
import org.json4s.JValue;
import scala.collection.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000553AAC\u0006\u00011!I1\u0005\u0001BC\u0002\u0013Eq\u0002\n\u0005\tW\u0001\u0011\t\u0011)A\u0005K!)A\u0006\u0001C\u0001[!)A\u0006\u0001C\tc!)A\u0006\u0001C\u0001e!)\u0001\b\u0001C!s!)!\b\u0001C!w!)a\b\u0001C!\u007f!)A\n\u0001C!c\tQq)\u001a8fe&\u001c'k\\<\u000b\u00051i\u0011aC3yaJ,7o]5p]NT!AD\b\u0002\u0011\r\fG/\u00197zgRT!\u0001E\t\u0002\u0007M\fHN\u0003\u0002\u0013'\u0005)1\u000f]1sW*\u0011A#F\u0001\u0007CB\f7\r[3\u000b\u0003Y\t1a\u001c:h\u0007\u0001\u00192\u0001A\r !\tQR$D\u0001\u001c\u0015\u0005a\u0012!B:dC2\f\u0017B\u0001\u0010\u001c\u0005\u0019\te.\u001f*fMB\u0011\u0001%I\u0007\u0002\u001f%\u0011!e\u0004\u0002\u0004%><\u0018A\u0002<bYV,7/F\u0001&!\rQb\u0005K\u0005\u0003Om\u0011Q!\u0011:sCf\u0004\"AG\u0015\n\u0005)Z\"aA!os\u00069a/\u00197vKN\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002/aA\u0011q\u0006A\u0007\u0002\u0017!)1e\u0001a\u0001KQ\ta\u0006\u0006\u0002/g!)A'\u0002a\u0001k\u0005!1/\u001b>f!\tQb'\u0003\u000287\t\u0019\u0011J\u001c;\u0002\r1,gn\u001a;i+\u0005)\u0014aA4fiR\u0011\u0001\u0006\u0010\u0005\u0006{\u001d\u0001\r!N\u0001\u0002S\u0006)Ao\\*fcV\t\u0001\tE\u0002B\u0013\"r!AQ$\u000f\u0005\r3U\"\u0001#\u000b\u0005\u0015;\u0012A\u0002\u001fs_>$h(C\u0001\u001d\u0013\tA5$A\u0004qC\u000e\\\u0017mZ3\n\u0005)[%aA*fc*\u0011\u0001jG\u0001\u0005G>\u0004\u0018\u0010"
)
public class GenericRow implements Row {
   private final Object[] values;

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

   public Object[] values() {
      return this.values;
   }

   public int length() {
      return this.values().length;
   }

   public Object get(final int i) {
      return this.values()[i];
   }

   public Seq toSeq() {
      return .MODULE$.SparkArrayOps(this.values().clone()).toImmutableArraySeq();
   }

   public GenericRow copy() {
      return this;
   }

   public GenericRow(final Object[] values) {
      this.values = values;
      Row.$init$(this);
   }

   public GenericRow() {
      this((Object[])null);
   }

   public GenericRow(final int size) {
      this(new Object[size]);
   }
}

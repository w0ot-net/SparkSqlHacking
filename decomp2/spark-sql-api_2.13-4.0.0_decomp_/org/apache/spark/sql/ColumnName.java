package org.apache.spark.sql;

import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.types.ArrayType$;
import org.apache.spark.sql.types.BinaryType$;
import org.apache.spark.sql.types.BooleanType$;
import org.apache.spark.sql.types.ByteType$;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DateType$;
import org.apache.spark.sql.types.DecimalType;
import org.apache.spark.sql.types.DecimalType$;
import org.apache.spark.sql.types.DoubleType$;
import org.apache.spark.sql.types.FloatType$;
import org.apache.spark.sql.types.IntegerType$;
import org.apache.spark.sql.types.LongType$;
import org.apache.spark.sql.types.MapType;
import org.apache.spark.sql.types.MapType$;
import org.apache.spark.sql.types.ShortType$;
import org.apache.spark.sql.types.StringType$;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructField$;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.StructType$;
import org.apache.spark.sql.types.TimestampType$;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005e4A!\u0006\f\u0001?!AA\u0005\u0001B\u0001B\u0003%Q\u0005C\u00033\u0001\u0011\u00051\u0007C\u00037\u0001\u0011\u0005q\u0007C\u0003?\u0001\u0011\u0005q\u0007C\u0003@\u0001\u0011\u0005q\u0007C\u0003A\u0001\u0011\u0005q\u0007C\u0003B\u0001\u0011\u0005q\u0007C\u0003C\u0001\u0011\u0005q\u0007C\u0003D\u0001\u0011\u0005q\u0007C\u0003E\u0001\u0011\u0005q\u0007C\u0003F\u0001\u0011\u0005q\u0007C\u0003G\u0001\u0011\u0005q\u0007C\u0003G\u0001\u0011\u0005q\tC\u0003Q\u0001\u0011\u0005q\u0007C\u0003R\u0001\u0011\u0005q\u0007C\u0003S\u0001\u0011\u00051\u000bC\u0003Z\u0001\u0011\u0005!\fC\u0003Z\u0001\u0011\u0005q\fC\u0003f\u0001\u0011\u0005a\rC\u0003f\u0001\u0011\u0005AN\u0001\u0006D_2,XN\u001c(b[\u0016T!a\u0006\r\u0002\u0007M\fHN\u0003\u0002\u001a5\u0005)1\u000f]1sW*\u00111\u0004H\u0001\u0007CB\f7\r[3\u000b\u0003u\t1a\u001c:h\u0007\u0001\u0019\"\u0001\u0001\u0011\u0011\u0005\u0005\u0012S\"\u0001\f\n\u0005\r2\"AB\"pYVlg.\u0001\u0003oC6,\u0007C\u0001\u00140\u001d\t9S\u0006\u0005\u0002)W5\t\u0011F\u0003\u0002+=\u00051AH]8pizR\u0011\u0001L\u0001\u0006g\u000e\fG.Y\u0005\u0003]-\na\u0001\u0015:fI\u00164\u0017B\u0001\u00192\u0005\u0019\u0019FO]5oO*\u0011afK\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005Q*\u0004CA\u0011\u0001\u0011\u0015!#\u00011\u0001&\u0003\u001d\u0011wn\u001c7fC:,\u0012\u0001\u000f\t\u0003sqj\u0011A\u000f\u0006\u0003wY\tQ\u0001^=qKNL!!\u0010\u001e\u0003\u0017M#(/^2u\r&,G\u000eZ\u0001\u0005Ef$X-A\u0003tQ>\u0014H/A\u0002j]R\fA\u0001\\8oO\u0006)a\r\\8bi\u00061Am\\;cY\u0016\faa\u001d;sS:<\u0017\u0001\u00023bi\u0016\fq\u0001Z3dS6\fG\u000eF\u00029\u0011:CQ!S\u0007A\u0002)\u000b\u0011\u0002\u001d:fG&\u001c\u0018n\u001c8\u0011\u0005-cU\"A\u0016\n\u00055[#aA%oi\")q*\u0004a\u0001\u0015\u0006)1oY1mK\u0006IA/[7fgR\fW\u000e]\u0001\u0007E&t\u0017M]=\u0002\u000b\u0005\u0014(/Y=\u0015\u0005a\"\u0006\"B+\u0011\u0001\u00041\u0016\u0001\u00033bi\u0006$\u0016\u0010]3\u0011\u0005e:\u0016B\u0001-;\u0005!!\u0015\r^1UsB,\u0017aA7baR\u0019\u0001hW/\t\u000bq\u000b\u0002\u0019\u0001,\u0002\u000f-,\u0017\u0010V=qK\")a,\u0005a\u0001-\u0006Ia/\u00197vKRK\b/\u001a\u000b\u0003q\u0001DQ!\u0019\nA\u0002\t\fq!\\1q)f\u0004X\r\u0005\u0002:G&\u0011AM\u000f\u0002\b\u001b\u0006\u0004H+\u001f9f\u0003\u0019\u0019HO];diR\u0011\u0001h\u001a\u0005\u0006QN\u0001\r![\u0001\u0007M&,G\u000eZ:\u0011\u0007-S\u0007(\u0003\u0002lW\tQAH]3qK\u0006$X\r\u001a \u0015\u0005aj\u0007\"\u00028\u0015\u0001\u0004y\u0017AC:ueV\u001cG\u000fV=qKB\u0011\u0011\b]\u0005\u0003cj\u0012!b\u0015;sk\u000e$H+\u001f9fQ\t\u00011\u000f\u0005\u0002uo6\tQO\u0003\u0002w1\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005a,(AB*uC\ndW\r"
)
public class ColumnName extends Column {
   private final String name;

   public StructField boolean() {
      return new StructField(this.name, BooleanType$.MODULE$, StructField$.MODULE$.apply$default$3(), StructField$.MODULE$.apply$default$4());
   }

   public StructField byte() {
      return new StructField(this.name, ByteType$.MODULE$, StructField$.MODULE$.apply$default$3(), StructField$.MODULE$.apply$default$4());
   }

   public StructField short() {
      return new StructField(this.name, ShortType$.MODULE$, StructField$.MODULE$.apply$default$3(), StructField$.MODULE$.apply$default$4());
   }

   public StructField int() {
      return new StructField(this.name, IntegerType$.MODULE$, StructField$.MODULE$.apply$default$3(), StructField$.MODULE$.apply$default$4());
   }

   public StructField long() {
      return new StructField(this.name, LongType$.MODULE$, StructField$.MODULE$.apply$default$3(), StructField$.MODULE$.apply$default$4());
   }

   public StructField float() {
      return new StructField(this.name, FloatType$.MODULE$, StructField$.MODULE$.apply$default$3(), StructField$.MODULE$.apply$default$4());
   }

   public StructField double() {
      return new StructField(this.name, DoubleType$.MODULE$, StructField$.MODULE$.apply$default$3(), StructField$.MODULE$.apply$default$4());
   }

   public StructField string() {
      return new StructField(this.name, StringType$.MODULE$, StructField$.MODULE$.apply$default$3(), StructField$.MODULE$.apply$default$4());
   }

   public StructField date() {
      return new StructField(this.name, DateType$.MODULE$, StructField$.MODULE$.apply$default$3(), StructField$.MODULE$.apply$default$4());
   }

   public StructField decimal() {
      return new StructField(this.name, DecimalType$.MODULE$.USER_DEFAULT(), StructField$.MODULE$.apply$default$3(), StructField$.MODULE$.apply$default$4());
   }

   public StructField decimal(final int precision, final int scale) {
      return new StructField(this.name, new DecimalType(precision, scale), StructField$.MODULE$.apply$default$3(), StructField$.MODULE$.apply$default$4());
   }

   public StructField timestamp() {
      return new StructField(this.name, TimestampType$.MODULE$, StructField$.MODULE$.apply$default$3(), StructField$.MODULE$.apply$default$4());
   }

   public StructField binary() {
      return new StructField(this.name, BinaryType$.MODULE$, StructField$.MODULE$.apply$default$3(), StructField$.MODULE$.apply$default$4());
   }

   public StructField array(final DataType dataType) {
      return new StructField(this.name, ArrayType$.MODULE$.apply(dataType), StructField$.MODULE$.apply$default$3(), StructField$.MODULE$.apply$default$4());
   }

   public StructField map(final DataType keyType, final DataType valueType) {
      return this.map(MapType$.MODULE$.apply(keyType, valueType));
   }

   public StructField map(final MapType mapType) {
      return new StructField(this.name, mapType, StructField$.MODULE$.apply$default$3(), StructField$.MODULE$.apply$default$4());
   }

   public StructField struct(final Seq fields) {
      return this.struct(StructType$.MODULE$.apply(fields));
   }

   public StructField struct(final StructType structType) {
      return new StructField(this.name, structType, StructField$.MODULE$.apply$default$3(), StructField$.MODULE$.apply$default$4());
   }

   public ColumnName(final String name) {
      super(name);
      this.name = name;
   }
}

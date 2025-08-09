package org.apache.spark.sql;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.rdd.RDD;
import scala.StringContext;
import scala.Symbol;
import scala.StringContext.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.TypeTags;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\ra!\u0002\u0007\u000e\u0003\u00031\u0002\"B\u0017\u0001\t\u0003q\u0003\"\u0002\u0019\u0001\r#\td\u0001B\u001b\u0001\u0003YB\u0001bN\u0002\u0003\u0006\u0004%\t\u0001\u000f\u0005\ty\r\u0011\t\u0011)A\u0005s!)Qf\u0001C\u0001{!)\u0011i\u0001C\u0001\u0005\"9a\nAA\u0001\n\u0007y\u0005\"B)\u0001\r\u0007\u0011\u0006\"\u00026\u0001\r\u0007Y\u0007\"B>\u0001\t\u0007a(\u0001D*R\u0019&k\u0007\u000f\\5dSR\u001c(B\u0001\b\u0010\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003!E\tQa\u001d9be.T!AE\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005!\u0012aA8sO\u000e\u00011\u0003\u0002\u0001\u0018;\u0005\u0002\"\u0001G\u000e\u000e\u0003eQ\u0011AG\u0001\u0006g\u000e\fG.Y\u0005\u00039e\u0011a!\u00118z%\u00164\u0007C\u0001\u0010 \u001b\u0005i\u0011B\u0001\u0011\u000e\u0005A)enY8eKJLU\u000e\u001d7jG&$8\u000f\u0005\u0002#U9\u00111\u0005\u000b\b\u0003I\u001dj\u0011!\n\u0006\u0003MU\ta\u0001\u0010:p_Rt\u0014\"\u0001\u000e\n\u0005%J\u0012a\u00029bG.\fw-Z\u0005\u0003W1\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!!K\r\u0002\rqJg.\u001b;?)\u0005y\u0003C\u0001\u0010\u0001\u0003\u001d\u0019Xm]:j_:,\u0012A\r\t\u0003=MJ!\u0001N\u0007\u0003\u0019M\u0003\u0018M]6TKN\u001c\u0018n\u001c8\u0003\u001dM#(/\u001b8h)>\u001cu\u000e\\;n]N\u00111aF\u0001\u0003g\u000e,\u0012!\u000f\t\u00031iJ!aO\r\u0003\u001bM#(/\u001b8h\u0007>tG/\u001a=u\u0003\r\u00198\r\t\u000b\u0003}\u0001\u0003\"aP\u0002\u000e\u0003\u0001AQa\u000e\u0004A\u0002e\n\u0011\u0001\n\u000b\u0003\u0007\u001a\u0003\"A\b#\n\u0005\u0015k!AC\"pYVlgNT1nK\")qi\u0002a\u0001\u0011\u0006!\u0011M]4t!\rA\u0012jS\u0005\u0003\u0015f\u0011!\u0002\u0010:fa\u0016\fG/\u001a3?!\tAB*\u0003\u0002N3\t\u0019\u0011I\\=\u0002\u001dM#(/\u001b8h)>\u001cu\u000e\\;n]R\u0011a\b\u0015\u0005\u0006o!\u0001\r!O\u0001\u0018Y>\u001c\u0017\r\\*fcR{G)\u0019;bg\u0016$\bj\u001c7eKJ,\"a\u0015.\u0015\u0005Q+GCA+a!\rqb\u000bW\u0005\u0003/6\u0011Q\u0002R1uCN,G\u000fS8mI\u0016\u0014\bCA-[\u0019\u0001!QaW\u0005C\u0002q\u0013\u0011\u0001V\t\u0003;.\u0003\"\u0001\u00070\n\u0005}K\"a\u0002(pi\"Lgn\u001a\u0005\bC&\t\t\u0011q\u0001c\u0003))g/\u001b3f]\u000e,G%\r\t\u0004=\rD\u0016B\u00013\u000e\u0005\u001d)enY8eKJDQAZ\u0005A\u0002\u001d\f\u0011a\u001d\t\u0004E!D\u0016BA5-\u0005\r\u0019V-]\u0001\u0013e\u0012$Gk\u001c#bi\u0006\u001cX\r\u001e%pY\u0012,'/\u0006\u0002maR\u0011Q\u000e\u001e\u000b\u0003]F\u00042A\b,p!\tI\u0006\u000fB\u0003\\\u0015\t\u0007A\fC\u0004s\u0015\u0005\u0005\t9A:\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007E\u0002\u001fG>DQ!\u001e\u0006A\u0002Y\f1A\u001d3e!\r9\u0018p\\\u0007\u0002q*\u0011QoD\u0005\u0003ub\u00141A\u0015#E\u00039\u0019\u00180\u001c2pYR{7i\u001c7v[:$\"aQ?\t\u000b\u0019\\\u0001\u0019\u0001@\u0011\u0005ay\u0018bAA\u00013\t11+_7c_2\u0004"
)
public abstract class SQLImplicits implements EncoderImplicits {
   /** @deprecated */
   private Encoder newIntSeqEncoder;
   /** @deprecated */
   private Encoder newLongSeqEncoder;
   /** @deprecated */
   private Encoder newDoubleSeqEncoder;
   /** @deprecated */
   private Encoder newFloatSeqEncoder;
   /** @deprecated */
   private Encoder newByteSeqEncoder;
   /** @deprecated */
   private Encoder newShortSeqEncoder;
   /** @deprecated */
   private Encoder newBooleanSeqEncoder;
   /** @deprecated */
   private Encoder newStringSeqEncoder;
   private Encoder newIntArrayEncoder;
   private Encoder newLongArrayEncoder;
   private Encoder newDoubleArrayEncoder;
   private Encoder newFloatArrayEncoder;
   private Encoder newByteArrayEncoder;
   private Encoder newShortArrayEncoder;
   private Encoder newBooleanArrayEncoder;
   private Encoder newStringArrayEncoder;

   public Encoder newIntEncoder() {
      return EncoderImplicits.newIntEncoder$(this);
   }

   public Encoder newLongEncoder() {
      return EncoderImplicits.newLongEncoder$(this);
   }

   public Encoder newDoubleEncoder() {
      return EncoderImplicits.newDoubleEncoder$(this);
   }

   public Encoder newFloatEncoder() {
      return EncoderImplicits.newFloatEncoder$(this);
   }

   public Encoder newByteEncoder() {
      return EncoderImplicits.newByteEncoder$(this);
   }

   public Encoder newShortEncoder() {
      return EncoderImplicits.newShortEncoder$(this);
   }

   public Encoder newBooleanEncoder() {
      return EncoderImplicits.newBooleanEncoder$(this);
   }

   public Encoder newStringEncoder() {
      return EncoderImplicits.newStringEncoder$(this);
   }

   public Encoder newJavaDecimalEncoder() {
      return EncoderImplicits.newJavaDecimalEncoder$(this);
   }

   public Encoder newScalaDecimalEncoder() {
      return EncoderImplicits.newScalaDecimalEncoder$(this);
   }

   public Encoder newDateEncoder() {
      return EncoderImplicits.newDateEncoder$(this);
   }

   public Encoder newLocalDateEncoder() {
      return EncoderImplicits.newLocalDateEncoder$(this);
   }

   public Encoder newLocalDateTimeEncoder() {
      return EncoderImplicits.newLocalDateTimeEncoder$(this);
   }

   public Encoder newTimeStampEncoder() {
      return EncoderImplicits.newTimeStampEncoder$(this);
   }

   public Encoder newInstantEncoder() {
      return EncoderImplicits.newInstantEncoder$(this);
   }

   public Encoder newDurationEncoder() {
      return EncoderImplicits.newDurationEncoder$(this);
   }

   public Encoder newPeriodEncoder() {
      return EncoderImplicits.newPeriodEncoder$(this);
   }

   public Encoder newJavaEnumEncoder(final TypeTags.TypeTag evidence$3) {
      return EncoderImplicits.newJavaEnumEncoder$(this, evidence$3);
   }

   public Encoder newBoxedIntEncoder() {
      return EncoderImplicits.newBoxedIntEncoder$(this);
   }

   public Encoder newBoxedLongEncoder() {
      return EncoderImplicits.newBoxedLongEncoder$(this);
   }

   public Encoder newBoxedDoubleEncoder() {
      return EncoderImplicits.newBoxedDoubleEncoder$(this);
   }

   public Encoder newBoxedFloatEncoder() {
      return EncoderImplicits.newBoxedFloatEncoder$(this);
   }

   public Encoder newBoxedByteEncoder() {
      return EncoderImplicits.newBoxedByteEncoder$(this);
   }

   public Encoder newBoxedShortEncoder() {
      return EncoderImplicits.newBoxedShortEncoder$(this);
   }

   public Encoder newBoxedBooleanEncoder() {
      return EncoderImplicits.newBoxedBooleanEncoder$(this);
   }

   /** @deprecated */
   public Encoder newProductSeqEncoder(final TypeTags.TypeTag evidence$4) {
      return EncoderImplicits.newProductSeqEncoder$(this, evidence$4);
   }

   public Encoder newSequenceEncoder(final TypeTags.TypeTag evidence$5) {
      return EncoderImplicits.newSequenceEncoder$(this, evidence$5);
   }

   public Encoder newMapEncoder(final TypeTags.TypeTag evidence$6) {
      return EncoderImplicits.newMapEncoder$(this, evidence$6);
   }

   public Encoder newSetEncoder(final TypeTags.TypeTag evidence$7) {
      return EncoderImplicits.newSetEncoder$(this, evidence$7);
   }

   public Encoder newProductArrayEncoder(final TypeTags.TypeTag evidence$8) {
      return EncoderImplicits.newProductArrayEncoder$(this, evidence$8);
   }

   public Encoder newProductEncoder(final TypeTags.TypeTag evidence$9) {
      return LowPrioritySQLImplicits.newProductEncoder$(this, evidence$9);
   }

   /** @deprecated */
   public Encoder newIntSeqEncoder() {
      return this.newIntSeqEncoder;
   }

   /** @deprecated */
   public Encoder newLongSeqEncoder() {
      return this.newLongSeqEncoder;
   }

   /** @deprecated */
   public Encoder newDoubleSeqEncoder() {
      return this.newDoubleSeqEncoder;
   }

   /** @deprecated */
   public Encoder newFloatSeqEncoder() {
      return this.newFloatSeqEncoder;
   }

   /** @deprecated */
   public Encoder newByteSeqEncoder() {
      return this.newByteSeqEncoder;
   }

   /** @deprecated */
   public Encoder newShortSeqEncoder() {
      return this.newShortSeqEncoder;
   }

   /** @deprecated */
   public Encoder newBooleanSeqEncoder() {
      return this.newBooleanSeqEncoder;
   }

   /** @deprecated */
   public Encoder newStringSeqEncoder() {
      return this.newStringSeqEncoder;
   }

   public Encoder newIntArrayEncoder() {
      return this.newIntArrayEncoder;
   }

   public Encoder newLongArrayEncoder() {
      return this.newLongArrayEncoder;
   }

   public Encoder newDoubleArrayEncoder() {
      return this.newDoubleArrayEncoder;
   }

   public Encoder newFloatArrayEncoder() {
      return this.newFloatArrayEncoder;
   }

   public Encoder newByteArrayEncoder() {
      return this.newByteArrayEncoder;
   }

   public Encoder newShortArrayEncoder() {
      return this.newShortArrayEncoder;
   }

   public Encoder newBooleanArrayEncoder() {
      return this.newBooleanArrayEncoder;
   }

   public Encoder newStringArrayEncoder() {
      return this.newStringArrayEncoder;
   }

   public void org$apache$spark$sql$EncoderImplicits$_setter_$newIntSeqEncoder_$eq(final Encoder x$1) {
      this.newIntSeqEncoder = x$1;
   }

   public void org$apache$spark$sql$EncoderImplicits$_setter_$newLongSeqEncoder_$eq(final Encoder x$1) {
      this.newLongSeqEncoder = x$1;
   }

   public void org$apache$spark$sql$EncoderImplicits$_setter_$newDoubleSeqEncoder_$eq(final Encoder x$1) {
      this.newDoubleSeqEncoder = x$1;
   }

   public void org$apache$spark$sql$EncoderImplicits$_setter_$newFloatSeqEncoder_$eq(final Encoder x$1) {
      this.newFloatSeqEncoder = x$1;
   }

   public void org$apache$spark$sql$EncoderImplicits$_setter_$newByteSeqEncoder_$eq(final Encoder x$1) {
      this.newByteSeqEncoder = x$1;
   }

   public void org$apache$spark$sql$EncoderImplicits$_setter_$newShortSeqEncoder_$eq(final Encoder x$1) {
      this.newShortSeqEncoder = x$1;
   }

   public void org$apache$spark$sql$EncoderImplicits$_setter_$newBooleanSeqEncoder_$eq(final Encoder x$1) {
      this.newBooleanSeqEncoder = x$1;
   }

   public void org$apache$spark$sql$EncoderImplicits$_setter_$newStringSeqEncoder_$eq(final Encoder x$1) {
      this.newStringSeqEncoder = x$1;
   }

   public void org$apache$spark$sql$EncoderImplicits$_setter_$newIntArrayEncoder_$eq(final Encoder x$1) {
      this.newIntArrayEncoder = x$1;
   }

   public void org$apache$spark$sql$EncoderImplicits$_setter_$newLongArrayEncoder_$eq(final Encoder x$1) {
      this.newLongArrayEncoder = x$1;
   }

   public void org$apache$spark$sql$EncoderImplicits$_setter_$newDoubleArrayEncoder_$eq(final Encoder x$1) {
      this.newDoubleArrayEncoder = x$1;
   }

   public void org$apache$spark$sql$EncoderImplicits$_setter_$newFloatArrayEncoder_$eq(final Encoder x$1) {
      this.newFloatArrayEncoder = x$1;
   }

   public void org$apache$spark$sql$EncoderImplicits$_setter_$newByteArrayEncoder_$eq(final Encoder x$1) {
      this.newByteArrayEncoder = x$1;
   }

   public void org$apache$spark$sql$EncoderImplicits$_setter_$newShortArrayEncoder_$eq(final Encoder x$1) {
      this.newShortArrayEncoder = x$1;
   }

   public void org$apache$spark$sql$EncoderImplicits$_setter_$newBooleanArrayEncoder_$eq(final Encoder x$1) {
      this.newBooleanArrayEncoder = x$1;
   }

   public void org$apache$spark$sql$EncoderImplicits$_setter_$newStringArrayEncoder_$eq(final Encoder x$1) {
      this.newStringArrayEncoder = x$1;
   }

   public abstract SparkSession session();

   public StringToColumn StringToColumn(final StringContext sc) {
      return new StringToColumn(sc);
   }

   public abstract DatasetHolder localSeqToDatasetHolder(final Seq s, final Encoder evidence$1);

   public abstract DatasetHolder rddToDatasetHolder(final RDD rdd, final Encoder evidence$2);

   public ColumnName symbolToColumn(final Symbol s) {
      return new ColumnName(s.name());
   }

   public SQLImplicits() {
      LowPrioritySQLImplicits.$init$(this);
      EncoderImplicits.$init$(this);
      Statics.releaseFence();
   }

   public class StringToColumn {
      private final StringContext sc;
      // $FF: synthetic field
      public final SQLImplicits $outer;

      public StringContext sc() {
         return this.sc;
      }

      public ColumnName $(final Seq args) {
         StringContext sc = this.sc();
         return new ColumnName(.MODULE$.standardInterpolator((str) -> .MODULE$.processEscapes(str), (scala.collection.Seq)scala.collection.immutable.List..MODULE$.apply(args), sc.parts()));
      }

      // $FF: synthetic method
      public SQLImplicits org$apache$spark$sql$SQLImplicits$StringToColumn$$$outer() {
         return this.$outer;
      }

      public StringToColumn(final StringContext sc) {
         this.sc = sc;
         if (SQLImplicits.this == null) {
            throw null;
         } else {
            this.$outer = SQLImplicits.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}

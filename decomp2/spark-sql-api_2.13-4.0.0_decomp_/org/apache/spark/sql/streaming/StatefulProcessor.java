package org.apache.spark.sql.streaming;

import java.io.Serializable;
import org.apache.spark.annotation.Evolving;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.EncoderImplicits;
import org.apache.spark.sql.LowPrioritySQLImplicits;
import org.apache.spark.sql.errors.ExecutionErrors$;
import scala.collection.Iterator;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.reflect.api.TypeTags;
import scala.runtime.Statics;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rb!\u0002\b\u0010\u0003\u0003Q\u0002\"\u0002\u0016\u0001\t\u0003Ys!B \u0001\u0011\u0003\u0001e!\u0002\"\u0001\u0011\u0003\u0019\u0005\"\u0002\u0016\u0004\t\u0003A\u0005bB%\u0001\u0001\u0004%IA\u0013\u0005\b\u001d\u0002\u0001\r\u0011\"\u0003P\u0011\u0019)\u0006\u0001)Q\u0005\u0017\")a\u000b\u0001D\u0001/\")!\r\u0001D\u0001G\")!\u0010\u0001C\u0001w\"9\u0011q\u0001\u0001\u0005\u0002\u0005%\u0001bBA\u0006\u0001\u0011\u0015\u0011Q\u0002\u0005\u0007\u0003'\u0001AQ\u0001&\u0003#M#\u0018\r^3gk2\u0004&o\\2fgN|'O\u0003\u0002\u0011#\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u0003%M\t1a]9m\u0015\t!R#A\u0003ta\u0006\u00148N\u0003\u0002\u0017/\u00051\u0011\r]1dQ\u0016T\u0011\u0001G\u0001\u0004_J<7\u0001A\u000b\u00057ARThE\u0002\u00019\t\u0002\"!\b\u0011\u000e\u0003yQ\u0011aH\u0001\u0006g\u000e\fG.Y\u0005\u0003Cy\u0011a!\u00118z%\u00164\u0007CA\u0012)\u001b\u0005!#BA\u0013'\u0003\tIwNC\u0001(\u0003\u0011Q\u0017M^1\n\u0005%\"#\u0001D*fe&\fG.\u001b>bE2,\u0017A\u0002\u001fj]&$h\bF\u0001-!\u0015i\u0003AL\u001d=\u001b\u0005y\u0001CA\u00181\u0019\u0001!Q!\r\u0001C\u0002I\u0012\u0011aS\t\u0003gY\u0002\"!\b\u001b\n\u0005Ur\"a\u0002(pi\"Lgn\u001a\t\u0003;]J!\u0001\u000f\u0010\u0003\u0007\u0005s\u0017\u0010\u0005\u00020u\u0011)1\b\u0001b\u0001e\t\t\u0011\n\u0005\u00020{\u0011)a\b\u0001b\u0001e\t\tq*A\u0005j[Bd\u0017nY5ugB\u0011\u0011iA\u0007\u0002\u0001\tI\u0011.\u001c9mS\u000eLGo]\n\u0004\u0007q!\u0005CA#G\u001b\u0005\t\u0012BA$\u0012\u0005A)enY8eKJLU\u000e\u001d7jG&$8\u000fF\u0001A\u0003]\u0019H/\u0019;fMVd\u0007K]8dKN\u001cxN\u001d%b]\u0012dW-F\u0001L!\tiC*\u0003\u0002N\u001f\t92\u000b^1uK\u001a,H\u000e\u0015:pG\u0016\u001c8o\u001c:IC:$G.Z\u0001\u001cgR\fG/\u001a4vYB\u0013xnY3tg>\u0014\b*\u00198eY\u0016|F%Z9\u0015\u0005A\u001b\u0006CA\u000fR\u0013\t\u0011fD\u0001\u0003V]&$\bb\u0002+\u0007\u0003\u0003\u0005\raS\u0001\u0004q\u0012\n\u0014\u0001G:uCR,g-\u001e7Qe>\u001cWm]:pe\"\u000bg\u000e\u001a7fA\u0005!\u0011N\\5u)\r\u0001\u0006,\u0018\u0005\u00063\"\u0001\rAW\u0001\u000b_V$\b/\u001e;N_\u0012,\u0007CA\u0017\\\u0013\tavB\u0001\u0006PkR\u0004X\u000f^'pI\u0016DQA\u0018\u0005A\u0002}\u000b\u0001\u0002^5nK6{G-\u001a\t\u0003[\u0001L!!Y\b\u0003\u0011QKW.Z'pI\u0016\fq\u0002[1oI2,\u0017J\u001c9viJ{wo\u001d\u000b\u0005IB\u0014X\u000fE\u0002f[rr!AZ6\u000f\u0005\u001dTW\"\u00015\u000b\u0005%L\u0012A\u0002\u001fs_>$h(C\u0001 \u0013\tag$A\u0004qC\u000e\\\u0017mZ3\n\u00059|'\u0001C%uKJ\fGo\u001c:\u000b\u00051t\u0002\"B9\n\u0001\u0004q\u0013aA6fs\")1/\u0003a\u0001i\u0006I\u0011N\u001c9viJ{wo\u001d\t\u0004K6L\u0004\"\u0002<\n\u0001\u00049\u0018a\u0003;j[\u0016\u0014h+\u00197vKN\u0004\"!\f=\n\u0005e|!a\u0003+j[\u0016\u0014h+\u00197vKN\f!\u0003[1oI2,W\t\u001f9je\u0016$G+[7feR!A\r`?\u007f\u0011\u0015\t(\u00021\u0001/\u0011\u00151(\u00021\u0001x\u0011\u0019y(\u00021\u0001\u0002\u0002\u0005\u0001R\r\u001f9je\u0016$G+[7fe&sgm\u001c\t\u0004[\u0005\r\u0011bAA\u0003\u001f\t\u0001R\t\u001f9je\u0016$G+[7fe&sgm\\\u0001\u0006G2|7/\u001a\u000b\u0002!\u0006I1/\u001a;IC:$G.\u001a\u000b\u0004!\u0006=\u0001BBA\t\u0019\u0001\u00071*\u0001\u0004iC:$G.Z\u0001\nO\u0016$\b*\u00198eY\u0016D3\u0001AA\f!\u0011\tI\"a\b\u000e\u0005\u0005m!bAA\u000f'\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005\u0005\u00121\u0004\u0002\t\u000bZ|GN^5oO\u0002"
)
public abstract class StatefulProcessor implements Serializable {
   private volatile implicits$ implicits$module;
   private StatefulProcessorHandle statefulProcessorHandle = null;

   public implicits$ implicits() {
      if (this.implicits$module == null) {
         this.implicits$lzycompute$1();
      }

      return this.implicits$module;
   }

   private StatefulProcessorHandle statefulProcessorHandle() {
      return this.statefulProcessorHandle;
   }

   private void statefulProcessorHandle_$eq(final StatefulProcessorHandle x$1) {
      this.statefulProcessorHandle = x$1;
   }

   public abstract void init(final OutputMode outputMode, final TimeMode timeMode);

   public abstract Iterator handleInputRows(final Object key, final Iterator inputRows, final TimerValues timerValues);

   public Iterator handleExpiredTimer(final Object key, final TimerValues timerValues, final ExpiredTimerInfo expiredTimerInfo) {
      return .MODULE$.Iterator().empty();
   }

   public void close() {
   }

   public final void setHandle(final StatefulProcessorHandle handle) {
      this.statefulProcessorHandle_$eq(handle);
   }

   public final StatefulProcessorHandle getHandle() {
      if (this.statefulProcessorHandle() == null) {
         throw ExecutionErrors$.MODULE$.stateStoreHandleNotInitialized();
      } else {
         return this.statefulProcessorHandle();
      }
   }

   private final void implicits$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.implicits$module == null) {
            this.implicits$module = new implicits$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   public class implicits$ implements EncoderImplicits {
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

      public implicits$() {
         LowPrioritySQLImplicits.$init$(this);
         EncoderImplicits.$init$(this);
         Statics.releaseFence();
      }
   }
}

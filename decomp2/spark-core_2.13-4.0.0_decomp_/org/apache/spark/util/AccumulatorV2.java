package org.apache.spark.util;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ContextCleaner;
import org.apache.spark.InternalAccumulator$;
import org.apache.spark.SparkContext;
import org.apache.spark.TaskContext;
import org.apache.spark.TaskContext$;
import org.apache.spark.scheduler.AccumulableInfo;
import org.apache.spark.scheduler.AccumulableInfo$;
import scala.None;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}d!\u0002\u0010 \u0003\u0003A\u0003\"\u0002\u001f\u0001\t\u0003i\u0004B\u0003(\u0001\u0001\u0004\u0005\r\u0011\"\u0001\"\u001f\"Q1\u000b\u0001a\u0001\u0002\u0004%\t!\t+\t\u0013i\u0003\u0001\u0019!A!B\u0013\u0001\u0006BB.\u0001A\u0003&A\fC\u0003`\u0001\u0011\u0005\u0001\r\u0003\u0004b\u0001\u0011\u0005\u0011E\u0019\u0005\tq\u0002\t\n\u0011\"\u0001\"s\"Q\u0011\u0011\u0002\u0001\u0012\u0002\u0013\u0005\u0011%a\u0003\t\r\u0005=\u0001\u0001\"\u0002a\u0011\u001d\t\t\u0002\u0001C\u0005\u0003'Aq!!\u0006\u0001\t\u000b\t9\u0002\u0003\u0004k\u0001\u0011\u0015\u0011q\u0004\u0005\u0007o\u0002!)!\t1\t\r\u0005\u0005\u0002\u0001\"\u0003a\u0011!\t\u0019\u0003\u0001C\u0001C\u0005\u0015\u0002\u0002CA\u001f\u0001\u0011\u0005\u0011%a\u0010\t\u000f\u0005\u0005\u0003\u0001\"\u0002\"A\"1\u00111\t\u0001\u0007\u0002\u0001Da!!\u0012\u0001\t\u0003i\u0004BBA$\u0001\u0019\u0005Q\bC\u0004\u0002J\u00011\t!a\u0005\t\u000f\u0005-\u0003A\"\u0001\u0002N!9\u00111\u000b\u0001\u0007\u0002\u0005U\u0003bBA\u001e\u0001\u0019\u0005\u00111\f\u0005\u0007\u0003;\u0002A\u0011C\u001f\t\u000f\u0005}\u0003\u0001\"\u0006\u0002b!9\u00111\r\u0001\u0005\n\u0005\u0015\u0004bBA>\u0001\u0011\u0005\u0013Q\u0010\u0002\u000e\u0003\u000e\u001cW/\\;mCR|'O\u0016\u001a\u000b\u0005\u0001\n\u0013\u0001B;uS2T!AI\u0012\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0011*\u0013AB1qC\u000eDWMC\u0001'\u0003\ry'oZ\u0002\u0001+\rI#\tT\n\u0004\u0001)\u0002\u0004CA\u0016/\u001b\u0005a#\"A\u0017\u0002\u000bM\u001c\u0017\r\\1\n\u0005=b#AB!osJ+g\r\u0005\u00022s9\u0011!g\u000e\b\u0003gYj\u0011\u0001\u000e\u0006\u0003k\u001d\na\u0001\u0010:p_Rt\u0014\"A\u0017\n\u0005ab\u0013a\u00029bG.\fw-Z\u0005\u0003um\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001\u000f\u0017\u0002\rqJg.\u001b;?)\u0005q\u0004\u0003B \u0001\u0001.k\u0011a\b\t\u0003\u0003\nc\u0001\u0001B\u0003D\u0001\t\u0007AI\u0001\u0002J\u001dF\u0011Q\t\u0013\t\u0003W\u0019K!a\u0012\u0017\u0003\u000f9{G\u000f[5oOB\u00111&S\u0005\u0003\u00152\u00121!\u00118z!\t\tE\nB\u0003N\u0001\t\u0007AIA\u0002P+R\u000b\u0001\"\\3uC\u0012\fG/Y\u000b\u0002!B\u0011q(U\u0005\u0003%~\u00111#Q2dk6,H.\u0019;pe6+G/\u00193bi\u0006\fA\"\\3uC\u0012\fG/Y0%KF$\"!\u0016-\u0011\u0005-2\u0016BA,-\u0005\u0011)f.\u001b;\t\u000fe\u001b\u0011\u0011!a\u0001!\u0006\u0019\u0001\u0010J\u0019\u0002\u00135,G/\u00193bi\u0006\u0004\u0013\u0001D1u\tJLg/\u001a:TS\u0012,\u0007CA\u0016^\u0013\tqFFA\u0004C_>dW-\u00198\u0002)\u0015D8\r\\;eK\u001a\u0013x.\u001c%fCJ$(-Z1u+\u0005a\u0016\u0001\u0003:fO&\u001cH/\u001a:\u0015\tU\u001b\u0017N\u001e\u0005\u0006I\u001e\u0001\r!Z\u0001\u0003g\u000e\u0004\"AZ4\u000e\u0003\u0005J!\u0001[\u0011\u0003\u0019M\u0003\u0018M]6D_:$X\r\u001f;\t\u000f)<\u0001\u0013!a\u0001W\u0006!a.Y7f!\rYCN\\\u0005\u0003[2\u0012aa\u00149uS>t\u0007CA8t\u001d\t\u0001\u0018\u000f\u0005\u00024Y%\u0011!\u000fL\u0001\u0007!J,G-\u001a4\n\u0005Q,(AB*ue&twM\u0003\u0002sY!9qo\u0002I\u0001\u0002\u0004a\u0016!E2pk:$h)Y5mK\u00124\u0016\r\\;fg\u0006\u0011\"/Z4jgR,'\u000f\n3fM\u0006,H\u000e\u001e\u00133+\u0005Q(FA6|W\u0005a\bcA?\u0002\u00065\taPC\u0002\u0000\u0003\u0003\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005\rA&\u0001\u0006b]:|G/\u0019;j_:L1!a\u0002\u007f\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u0013e\u0016<\u0017n\u001d;fe\u0012\"WMZ1vYR$3'\u0006\u0002\u0002\u000e)\u0012Al_\u0001\rSN\u0014VmZ5ti\u0016\u0014X\rZ\u0001\u0016CN\u001cXM\u001d;NKR\fG-\u0019;b\u001d>$h*\u001e7m)\u0005)\u0016AA5e+\t\tI\u0002E\u0002,\u00037I1!!\b-\u0005\u0011auN\\4\u0016\u0003-\f!\"[:J]R,'O\\1m\u0003\u0019!x.\u00138g_R1\u0011qEA\u001a\u0003s\u0001B!!\u000b\u000205\u0011\u00111\u0006\u0006\u0004\u0003[\t\u0013!C:dQ\u0016$W\u000f\\3s\u0013\u0011\t\t$a\u000b\u0003\u001f\u0005\u001b7-^7vY\u0006\u0014G.Z%oM>Dq!!\u000e\u0011\u0001\u0004\t9$\u0001\u0004va\u0012\fG/\u001a\t\u0004W1D\u0005bBA\u001e!\u0001\u0007\u0011qG\u0001\u0006m\u0006dW/Z\u0001\ri>LeNZ8Va\u0012\fG/Z\u000b\u0003\u0003O\ta\"[:Bi\u0012\u0013\u0018N^3s'&$W-\u0001\u0004jgj+'o\\\u0001\rG>\u0004\u00180\u00118e%\u0016\u001cX\r^\u0001\u0005G>\u0004\u00180A\u0003sKN,G/A\u0002bI\u0012$2!VA(\u0011\u0019\t\tf\u0006a\u0001\u0001\u0006\ta/A\u0003nKJ<W\rF\u0002V\u0003/Ba!!\u0017\u0019\u0001\u0004q\u0014!B8uQ\u0016\u0014X#A&\u0002)]LG\u000f\u001b\"vM\u001a,'oU3sS\u0006d\u0017N_3e\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005A\u0015A\u0003:fC\u0012|%M[3diR\u0019Q+a\u001a\t\u000f\u0005%D\u00041\u0001\u0002l\u0005\u0011\u0011N\u001c\t\u0005\u0003[\n9(\u0004\u0002\u0002p)!\u0011\u0011OA:\u0003\tIwN\u0003\u0002\u0002v\u0005!!.\u0019<b\u0013\u0011\tI(a\u001c\u0003#=\u0013'.Z2u\u0013:\u0004X\u000f^*ue\u0016\fW.\u0001\u0005u_N#(/\u001b8h)\u0005q\u0007"
)
public abstract class AccumulatorV2 implements Serializable {
   private AccumulatorMetadata metadata;
   private boolean atDriverSide = true;

   public AccumulatorMetadata metadata() {
      return this.metadata;
   }

   public void metadata_$eq(final AccumulatorMetadata x$1) {
      this.metadata = x$1;
   }

   public boolean excludeFromHeartbeat() {
      return false;
   }

   public void register(final SparkContext sc, final Option name, final boolean countFailedValues) {
      if (this.metadata() != null) {
         throw new IllegalStateException("Cannot register an Accumulator twice.");
      } else {
         this.metadata_$eq(new AccumulatorMetadata(AccumulatorContext$.MODULE$.newId(), name, countFailedValues));
         AccumulatorContext$.MODULE$.register(this);
         sc.cleaner().foreach((x$2) -> {
            $anonfun$register$1(this, x$2);
            return BoxedUnit.UNIT;
         });
      }
   }

   public Option register$default$2() {
      return .MODULE$;
   }

   public boolean register$default$3() {
      return false;
   }

   public final boolean isRegistered() {
      return this.metadata() != null && AccumulatorContext$.MODULE$.get(this.metadata().id()).isDefined();
   }

   private void assertMetadataNotNull() {
      if (this.metadata() == null) {
         throw new IllegalStateException("The metadata of this accumulator has not been assigned yet.");
      }
   }

   public final long id() {
      this.assertMetadataNotNull();
      return this.metadata().id();
   }

   public final Option name() {
      this.assertMetadataNotNull();
      return this.atDriverSide ? this.metadata().name().orElse(() -> AccumulatorContext$.MODULE$.get(this.id()).flatMap((x$3) -> x$3.metadata().name())) : this.metadata().name();
   }

   public final boolean countFailedValues() {
      this.assertMetadataNotNull();
      return this.metadata().countFailedValues();
   }

   private boolean isInternal() {
      return this.name().exists((x$4) -> BoxesRunTime.boxToBoolean($anonfun$isInternal$1(x$4)));
   }

   public AccumulableInfo toInfo(final Option update, final Option value) {
      return AccumulableInfo$.MODULE$.apply(this.id(), this.name(), AccumulatorContext$.MODULE$.internOption(update), AccumulatorContext$.MODULE$.internOption(value), this.isInternal(), this.countFailedValues(), AccumulableInfo$.MODULE$.apply$default$7());
   }

   public AccumulableInfo toInfoUpdate() {
      return AccumulableInfo$.MODULE$.apply(this.id(), this.name(), AccumulatorContext$.MODULE$.internOption(new Some(this.value())), .MODULE$, this.isInternal(), this.countFailedValues(), AccumulableInfo$.MODULE$.apply$default$7());
   }

   public final boolean isAtDriverSide() {
      return this.atDriverSide;
   }

   public abstract boolean isZero();

   public AccumulatorV2 copyAndReset() {
      AccumulatorV2 copyAcc = this.copy();
      copyAcc.reset();
      return copyAcc;
   }

   public abstract AccumulatorV2 copy();

   public abstract void reset();

   public abstract void add(final Object v);

   public abstract void merge(final AccumulatorV2 other);

   public abstract Object value();

   public AccumulatorV2 withBufferSerialized() {
      return this;
   }

   public final Object writeReplace() {
      if (this.atDriverSide) {
         if (!this.isRegistered()) {
            throw new UnsupportedOperationException("Accumulator must be registered before send to executor");
         } else {
            AccumulatorV2 copyAcc = this.copyAndReset();
            scala.Predef..MODULE$.assert(copyAcc.isZero(), () -> "copyAndReset must return a zero value copy");
            boolean isInternalAcc = this.name().isDefined() && ((String)this.name().get()).startsWith(InternalAccumulator$.MODULE$.METRICS_PREFIX());
            if (isInternalAcc) {
               AccumulatorMetadata qual$1 = this.metadata();
               None x$1 = .MODULE$;
               long x$2 = qual$1.copy$default$1();
               boolean x$3 = qual$1.copy$default$3();
               copyAcc.metadata_$eq(qual$1.copy(x$2, x$1, x$3));
            } else {
               copyAcc.metadata_$eq(this.metadata());
            }

            return copyAcc;
         }
      } else {
         return this.withBufferSerialized();
      }
   }

   private void readObject(final ObjectInputStream in) {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         in.defaultReadObject();
         if (this.atDriverSide) {
            this.atDriverSide = false;
            TaskContext taskContext = TaskContext$.MODULE$.get();
            if (taskContext != null) {
               taskContext.registerAccumulator(this);
            }
         } else {
            this.atDriverSide = true;
         }
      });
   }

   public String toString() {
      if (this.metadata() == null) {
         Utils$ var1 = Utils$.MODULE$;
         return "Un-registered Accumulator: " + var1.getSimpleName(this.getClass());
      } else {
         String var10000 = Utils$.MODULE$.getSimpleName(this.getClass());
         return var10000 + "(id: " + this.id() + ", name: " + this.name() + ", value: " + this.value() + ")";
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$register$1(final AccumulatorV2 $this, final ContextCleaner x$2) {
      x$2.registerAccumulatorForCleanup($this);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isInternal$1(final String x$4) {
      return x$4.startsWith(InternalAccumulator$.MODULE$.METRICS_PREFIX());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

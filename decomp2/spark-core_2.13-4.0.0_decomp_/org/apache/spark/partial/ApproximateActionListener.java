package org.apache.spark.partial;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.rdd.RDD;
import org.apache.spark.scheduler.JobListener;
import scala.Function2;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mb!\u0002\f\u0018\u0001ey\u0002\u0002C\u0017\u0001\u0005\u0003\u0005\u000b\u0011B\u0018\t\u0011}\u0002!\u0011!Q\u0001\n\u0001C\u0001B\u0016\u0001\u0003\u0002\u0003\u0006Ia\u0016\u0005\t=\u0002\u0011\t\u0011)A\u0005?\")!\r\u0001C\u0001G\"9\u0011\u000e\u0001b\u0001\n\u0003Q\u0007BB6\u0001A\u0003%q\fC\u0004m\u0001\t\u0007I\u0011A7\t\rE\u0004\u0001\u0015!\u0003o\u0011\u001d\u0011\b\u00011A\u0005\u00025Dqa\u001d\u0001A\u0002\u0013\u0005A\u000f\u0003\u0004{\u0001\u0001\u0006KA\u001c\u0005\bw\u0002\u0001\r\u0011\"\u0001}\u0011%\t9\u0001\u0001a\u0001\n\u0003\tI\u0001C\u0004\u0002\u000e\u0001\u0001\u000b\u0015B?\t\u0013\u0005=\u0001\u00011A\u0005\u0002\u0005E\u0001\"CA\u000e\u0001\u0001\u0007I\u0011AA\u000f\u0011!\t\t\u0003\u0001Q!\n\u0005M\u0001bBA\u0012\u0001\u0011\u0005\u0013Q\u0005\u0005\b\u0003_\u0001A\u0011IA\u0019\u0011\u001d\t9\u0004\u0001C\u0001\u0003s\u0011\u0011$\u00119qe>D\u0018.\\1uK\u0006\u001bG/[8o\u0019&\u001cH/\u001a8fe*\u0011\u0001$G\u0001\ba\u0006\u0014H/[1m\u0015\tQ2$A\u0003ta\u0006\u00148N\u0003\u0002\u001d;\u00051\u0011\r]1dQ\u0016T\u0011AH\u0001\u0004_J<W\u0003\u0002\u00117)r\u001b2\u0001A\u0011(!\t\u0011S%D\u0001$\u0015\u0005!\u0013!B:dC2\f\u0017B\u0001\u0014$\u0005\u0019\te.\u001f*fMB\u0011\u0001fK\u0007\u0002S)\u0011!&G\u0001\ng\u000eDW\rZ;mKJL!\u0001L\u0015\u0003\u0017){'\rT5ti\u0016tWM]\u0001\u0004e\u0012$7\u0001\u0001\t\u0004aI\"T\"A\u0019\u000b\u00055J\u0012BA\u001a2\u0005\r\u0011F\t\u0012\t\u0003kYb\u0001\u0001B\u00038\u0001\t\u0007\u0001HA\u0001U#\tID\b\u0005\u0002#u%\u00111h\t\u0002\b\u001d>$\b.\u001b8h!\t\u0011S(\u0003\u0002?G\t\u0019\u0011I\\=\u0002\t\u0019,hn\u0019\t\u0006E\u0005\u001buiU\u0005\u0003\u0005\u000e\u0012\u0011BR;oGRLwN\u001c\u001a\u0011\u0005\u0011+U\"A\r\n\u0005\u0019K\"a\u0003+bg.\u001cuN\u001c;fqR\u00042\u0001\u0013)5\u001d\tIeJ\u0004\u0002K\u001b6\t1J\u0003\u0002M]\u00051AH]8pizJ\u0011\u0001J\u0005\u0003\u001f\u000e\nq\u0001]1dW\u0006<W-\u0003\u0002R%\nA\u0011\n^3sCR|'O\u0003\u0002PGA\u0011Q\u0007\u0016\u0003\u0006+\u0002\u0011\r\u0001\u000f\u0002\u0002+\u0006IQM^1mk\u0006$xN\u001d\t\u00051f\u001b6,D\u0001\u0018\u0013\tQvC\u0001\u000bBaB\u0014x\u000e_5nCR,WI^1mk\u0006$xN\u001d\t\u0003kq#Q!\u0018\u0001C\u0002a\u0012\u0011AU\u0001\bi&lWm\\;u!\t\u0011\u0003-\u0003\u0002bG\t!Aj\u001c8h\u0003\u0019a\u0014N\\5u}Q)A-\u001a4hQB)\u0001\f\u0001\u001bT7\")Q&\u0002a\u0001_!)q(\u0002a\u0001\u0001\")a+\u0002a\u0001/\")a,\u0002a\u0001?\u0006I1\u000f^1siRKW.Z\u000b\u0002?\u0006Q1\u000f^1siRKW.\u001a\u0011\u0002\u0015Q|G/\u00197UCN\\7/F\u0001o!\t\u0011s.\u0003\u0002qG\t\u0019\u0011J\u001c;\u0002\u0017Q|G/\u00197UCN\\7\u000fI\u0001\u000eM&t\u0017n\u001d5fIR\u000b7o[:\u0002#\u0019Lg.[:iK\u0012$\u0016m]6t?\u0012*\u0017\u000f\u0006\u0002vqB\u0011!E^\u0005\u0003o\u000e\u0012A!\u00168ji\"9\u0011pCA\u0001\u0002\u0004q\u0017a\u0001=%c\u0005qa-\u001b8jg\",G\rV1tWN\u0004\u0013a\u00024bS2,(/Z\u000b\u0002{B!!E`A\u0001\u0013\ty8E\u0001\u0004PaRLwN\u001c\t\u0004\u0011\u0006\r\u0011bAA\u0003%\nIQ\t_2faRLwN\\\u0001\fM\u0006LG.\u001e:f?\u0012*\u0017\u000fF\u0002v\u0003\u0017Aq!\u001f\b\u0002\u0002\u0003\u0007Q0\u0001\u0005gC&dWO]3!\u00031\u0011Xm];mi>\u0013'.Z2u+\t\t\u0019\u0002\u0005\u0003#}\u0006U\u0001\u0003\u0002-\u0002\u0018mK1!!\u0007\u0018\u00055\u0001\u0016M\u001d;jC2\u0014Vm];mi\u0006\u0001\"/Z:vYR|%M[3di~#S-\u001d\u000b\u0004k\u0006}\u0001\u0002C=\u0012\u0003\u0003\u0005\r!a\u0005\u0002\u001bI,7/\u001e7u\u001f\nTWm\u0019;!\u00035!\u0018m]6Tk\u000e\u001cW-\u001a3fIR)Q/a\n\u0002,!1\u0011\u0011F\nA\u00029\fQ!\u001b8eKbDa!!\f\u0014\u0001\u0004a\u0014A\u0002:fgVdG/A\u0005k_\n4\u0015-\u001b7fIR\u0019Q/a\r\t\u000f\u0005UB\u00031\u0001\u0002\u0002\u0005IQ\r_2faRLwN\\\u0001\fC^\f\u0017\u000e\u001e*fgVdG\u000f\u0006\u0002\u0002\u0016\u0001"
)
public class ApproximateActionListener implements JobListener {
   private final ApproximateEvaluator evaluator;
   private final long timeout;
   private final long startTime;
   private final int totalTasks;
   private int finishedTasks;
   private Option failure;
   private Option resultObject;

   public long startTime() {
      return this.startTime;
   }

   public int totalTasks() {
      return this.totalTasks;
   }

   public int finishedTasks() {
      return this.finishedTasks;
   }

   public void finishedTasks_$eq(final int x$1) {
      this.finishedTasks = x$1;
   }

   public Option failure() {
      return this.failure;
   }

   public void failure_$eq(final Option x$1) {
      this.failure = x$1;
   }

   public Option resultObject() {
      return this.resultObject;
   }

   public void resultObject_$eq(final Option x$1) {
      this.resultObject = x$1;
   }

   public synchronized void taskSucceeded(final int index, final Object result) {
      this.evaluator.merge(index, result);
      this.finishedTasks_$eq(this.finishedTasks() + 1);
      if (this.finishedTasks() == this.totalTasks()) {
         this.resultObject().foreach((r) -> {
            $anonfun$taskSucceeded$1(this, r);
            return BoxedUnit.UNIT;
         });
         this.notifyAll();
      }
   }

   public synchronized void jobFailed(final Exception exception) {
      this.failure_$eq(new Some(exception));
      this.notifyAll();
   }

   public synchronized PartialResult awaitResult() {
      long finishTime = this.startTime() + this.timeout;

      while(true) {
         long time = System.currentTimeMillis();
         if (this.failure().isDefined()) {
            throw (Throwable)this.failure().get();
         }

         if (this.finishedTasks() == this.totalTasks()) {
            return new PartialResult(this.evaluator.currentResult(), true);
         }

         if (time >= finishTime) {
            this.resultObject_$eq(new Some(new PartialResult(this.evaluator.currentResult(), false)));
            return (PartialResult)this.resultObject().get();
         }

         this.wait(finishTime - time);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$taskSucceeded$1(final ApproximateActionListener $this, final PartialResult r) {
      r.setFinalValue($this.evaluator.currentResult());
   }

   public ApproximateActionListener(final RDD rdd, final Function2 func, final ApproximateEvaluator evaluator, final long timeout) {
      this.evaluator = evaluator;
      this.timeout = timeout;
      this.startTime = System.currentTimeMillis();
      this.totalTasks = rdd.partitions().length;
      this.finishedTasks = 0;
      this.failure = .MODULE$;
      this.resultObject = .MODULE$;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

package org.apache.spark;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.apache.spark.api.java.JavaFutureAction;
import org.apache.spark.util.ThreadUtils$;
import scala.Function1;
import scala.MatchError;
import scala.collection.IterableOnceOps;
import scala.concurrent.duration.Duration;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.util.Failure;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0005I4Qa\u0003\u0007\u0001\u0019IA\u0001B\r\u0001\u0003\u0002\u0003\u0006Ia\r\u0005\tu\u0001\u0011\t\u0011)A\u0005w!)a\b\u0001C\u0001\u007f!)1\t\u0001C!\t\")\u0001\n\u0001C!\t\")\u0011\n\u0001C!\u0015\")A\u000b\u0001C\u0005+\")\u0001\r\u0001C!C\")\u0001\r\u0001C!E\")a\u000e\u0001C!_\n9\"*\u0019<b\rV$XO]3BGRLwN\\,sCB\u0004XM\u001d\u0006\u0003\u001b9\tQa\u001d9be.T!a\u0004\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\t\u0012aA8sOV\u00191\u0003O\u0013\u0014\u0007\u0001!B\u0004\u0005\u0002\u001655\taC\u0003\u0002\u00181\u0005!A.\u00198h\u0015\u0005I\u0012\u0001\u00026bm\u0006L!a\u0007\f\u0003\r=\u0013'.Z2u!\ri\u0012eI\u0007\u0002=)\u0011\u0011d\b\u0006\u0003A1\t1!\u00199j\u0013\t\u0011cD\u0001\tKCZ\fg)\u001e;ve\u0016\f5\r^5p]B\u0011A%\n\u0007\u0001\t\u00151\u0003A1\u0001)\u0005\u0005!6\u0001A\t\u0003S=\u0002\"AK\u0017\u000e\u0003-R\u0011\u0001L\u0001\u0006g\u000e\fG.Y\u0005\u0003]-\u0012qAT8uQ&tw\r\u0005\u0002+a%\u0011\u0011g\u000b\u0002\u0004\u0003:L\u0018\u0001\u00044viV\u0014X-Q2uS>t\u0007c\u0001\u001b6o5\tA\"\u0003\u00027\u0019\taa)\u001e;ve\u0016\f5\r^5p]B\u0011A\u0005\u000f\u0003\u0006s\u0001\u0011\r\u0001\u000b\u0002\u0002'\u0006I1m\u001c8wKJ$XM\u001d\t\u0005Uq:4%\u0003\u0002>W\tIa)\u001e8di&|g.M\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007\u0001\u000b%\t\u0005\u00035\u0001]\u001a\u0003\"\u0002\u001a\u0004\u0001\u0004\u0019\u0004\"\u0002\u001e\u0004\u0001\u0004Y\u0014aC5t\u0007\u0006t7-\u001a7mK\u0012$\u0012!\u0012\t\u0003U\u0019K!aR\u0016\u0003\u000f\t{w\u000e\\3b]\u00061\u0011n\u001d#p]\u0016\faA[8c\u0013\u0012\u001cH#A&\u0011\u00071{\u0015+D\u0001N\u0015\tq\u0005$\u0001\u0003vi&d\u0017B\u0001)N\u0005\u0011a\u0015n\u001d;\u0011\u0005U\u0011\u0016BA*\u0017\u0005\u001dIe\u000e^3hKJ\fqaZ3u\u00136\u0004H\u000e\u0006\u0002$-\")qk\u0002a\u00011\u00069A/[7f_V$\bCA-_\u001b\u0005Q&BA.]\u0003!!WO]1uS>t'BA/,\u0003)\u0019wN\\2veJ,g\u000e^\u0005\u0003?j\u0013\u0001\u0002R;sCRLwN\\\u0001\u0004O\u0016$H#A\u0012\u0015\u0007\r\u001aw\rC\u0003X\u0013\u0001\u0007A\r\u0005\u0002+K&\u0011am\u000b\u0002\u0005\u0019>tw\rC\u0003i\u0013\u0001\u0007\u0011.\u0001\u0003v]&$\bC\u00016m\u001b\u0005Y'BA/N\u0013\ti7N\u0001\u0005US6,WK\\5u\u0003\u0019\u0019\u0017M\\2fYR\u0011Q\t\u001d\u0005\u0006c*\u0001\r!R\u0001\u0016[\u0006L\u0018J\u001c;feJ,\b\u000f^%g%Vtg.\u001b8h\u0001"
)
public class JavaFutureActionWrapper implements JavaFutureAction {
   private final FutureAction futureAction;
   private final Function1 converter;

   public boolean isCancelled() {
      return this.futureAction.isCancelled();
   }

   public boolean isDone() {
      return this.futureAction.isCancelled() || this.futureAction.isCompleted();
   }

   public List jobIds() {
      return List.of(((IterableOnceOps)this.futureAction.jobIds().map((x$1) -> $anonfun$jobIds$2(BoxesRunTime.unboxToInt(x$1)))).toArray(.MODULE$.apply(Integer.class)));
   }

   private Object getImpl(final Duration timeout) {
      ThreadUtils$.MODULE$.awaitReady(this.futureAction, timeout);
      Try var3 = (Try)this.futureAction.value().get();
      if (var3 instanceof scala.util.Success var4) {
         Object value = var4.value();
         return this.converter.apply(value);
      } else if (var3 instanceof Failure var6) {
         Throwable exception = var6.exception();
         if (this.isCancelled()) {
            throw (new CancellationException("Job cancelled")).initCause(exception);
         } else {
            throw new ExecutionException("Exception thrown by job", exception);
         }
      } else {
         throw new MatchError(var3);
      }
   }

   public Object get() {
      return this.getImpl(scala.concurrent.duration.Duration..MODULE$.Inf());
   }

   public Object get(final long timeout, final TimeUnit unit) {
      return this.getImpl(scala.concurrent.duration.Duration..MODULE$.fromNanos(unit.toNanos(timeout)));
   }

   public synchronized boolean cancel(final boolean mayInterruptIfRunning) {
      if (this.isDone()) {
         return false;
      } else {
         this.futureAction.cancel();
         return true;
      }
   }

   // $FF: synthetic method
   public static final Integer $anonfun$jobIds$2(final int x$1) {
      return x$1;
   }

   public JavaFutureActionWrapper(final FutureAction futureAction, final Function1 converter) {
      this.futureAction = futureAction;
      this.converter = converter;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

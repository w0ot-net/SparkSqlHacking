package org.apache.spark.ml.util;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkException;
import scala.Option;
import scala.None.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)3\u0001b\u0002\u0005\u0011\u0002\u0007\u0005AB\u0005\u0005\u00065\u0001!\t\u0001\b\u0005\tA\u0001\u0001\r\u0011\"\u0002\u000bC!A\u0001\u0007\u0001a\u0001\n\u000bQ\u0011\u0007C\u00035\u0001\u0011\u0005Q\u0007C\u0003C\u0001\u0011\u00051\t\u0003\u0004F\u0001\u0011\u0005!B\u0012\u0002\u0013\u0011\u0006\u001cHK]1j]&twmU;n[\u0006\u0014\u0018P\u0003\u0002\n\u0015\u0005!Q\u000f^5m\u0015\tYA\"\u0001\u0002nY*\u0011QBD\u0001\u0006gB\f'o\u001b\u0006\u0003\u001fA\ta!\u00199bG\",'\"A\t\u0002\u0007=\u0014x-\u0006\u0002\u0014OM\u0011\u0001\u0001\u0006\t\u0003+ai\u0011A\u0006\u0006\u0002/\u0005)1oY1mC&\u0011\u0011D\u0006\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012!\b\t\u0003+yI!a\b\f\u0003\tUs\u0017\u000e^\u0001\u0010iJ\f\u0017N\\5oON+X.\\1ssV\t!\u0005E\u0002\u0016G\u0015J!\u0001\n\f\u0003\r=\u0003H/[8o!\t1s\u0005\u0004\u0001\u0005\u000b!\u0002!\u0019A\u0015\u0003\u0003Q\u000b\"AK\u0017\u0011\u0005UY\u0013B\u0001\u0017\u0017\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\u0006\u0018\n\u0005=2\"aA!os\u0006\u0019BO]1j]&twmU;n[\u0006\u0014\u0018p\u0018\u0013fcR\u0011QD\r\u0005\bg\r\t\t\u00111\u0001#\u0003\rAH%M\u0001\u000bQ\u0006\u001c8+^7nCJLX#\u0001\u001c\u0011\u0005U9\u0014B\u0001\u001d\u0017\u0005\u001d\u0011un\u001c7fC:D3\u0001\u0002\u001eA!\tYd(D\u0001=\u0015\tiD\"\u0001\u0006b]:|G/\u0019;j_:L!a\u0010\u001f\u0003\u000bMKgnY3\"\u0003\u0005\u000bQa\r\u00181]A\nqa];n[\u0006\u0014\u00180F\u0001&Q\r)!\bQ\u0001\u000bg\u0016$8+^7nCJLHCA$I\u001b\u0005\u0001\u0001\"\u0002\"\u0007\u0001\u0004\u0011\u0003f\u0001\u0001;\u0001\u0002"
)
public interface HasTrainingSummary {
   Option trainingSummary();

   void trainingSummary_$eq(final Option x$1);

   // $FF: synthetic method
   static boolean hasSummary$(final HasTrainingSummary $this) {
      return $this.hasSummary();
   }

   default boolean hasSummary() {
      return this.trainingSummary().isDefined();
   }

   // $FF: synthetic method
   static Object summary$(final HasTrainingSummary $this) {
      return $this.summary();
   }

   default Object summary() {
      return this.trainingSummary().getOrElse(() -> {
         throw new SparkException("No training summary available for this " + this.getClass().getSimpleName());
      });
   }

   // $FF: synthetic method
   static HasTrainingSummary setSummary$(final HasTrainingSummary $this, final Option summary) {
      return $this.setSummary(summary);
   }

   default HasTrainingSummary setSummary(final Option summary) {
      this.trainingSummary_$eq(summary);
      return this;
   }

   static void $init$(final HasTrainingSummary $this) {
      $this.trainingSummary_$eq(.MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

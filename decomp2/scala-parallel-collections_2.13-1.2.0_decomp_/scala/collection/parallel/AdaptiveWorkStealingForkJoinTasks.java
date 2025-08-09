package scala.collection.parallel;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.RecursiveAction;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014q\u0001C\u0005\u0011\u0002\u0007\u0005\u0001\u0003C\u0003\u001d\u0001\u0011\u0005QD\u0002\u0003\"\u0001\u0001\u0011\u0003\u0002C\"\u0003\u0005\u000b\u0007I\u0011\u0001#\t\u0011!\u0013!\u0011!Q\u0001\n\u0015CQ!\u0013\u0002\u0005\u0002)CQ!\u0014\u0002\u0005\u00029CQ!\u0016\u0001\u0005\u0002Y\u0013\u0011%\u00113baRLg/Z,pe.\u001cF/Z1mS:<gi\u001c:l\u0015>Lg\u000eV1tWNT!AC\u0006\u0002\u0011A\f'/\u00197mK2T!\u0001D\u0007\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\u000f\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019B\u0001A\t\u00163A\u0011!cE\u0007\u0002\u001b%\u0011A#\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0005Y9R\"A\u0005\n\u0005aI!!\u0004$pe.Tu.\u001b8UCN\\7\u000f\u0005\u0002\u00175%\u00111$\u0003\u0002\u001a\u0003\u0012\f\u0007\u000f^5wK^{'o[*uK\u0006d\u0017N\\4UCN\\7/\u0001\u0004%S:LG\u000f\n\u000b\u0002=A\u0011!cH\u0005\u0003A5\u0011A!\u00168ji\n\t\u0012iV*G\u0015R;&/\u00199qK\u0012$\u0016m]6\u0016\u0007\r\"dh\u0005\u0003\u0003I9\u0002\u0005CA\u0013-\u001b\u00051#BA\u0014)\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0003S)\nA!\u001e;jY*\t1&\u0001\u0003kCZ\f\u0017BA\u0017'\u0005=\u0011VmY;sg&4X-Q2uS>t\u0007\u0003B\u00181euj\u0011\u0001A\u0005\u0003c]\u0011aB\u0012&U/J\f\u0007\u000f]3e)\u0006\u001c8\u000e\u0005\u00024i1\u0001A!B\u001b\u0003\u0005\u00041$!\u0001*\u0012\u0005]R\u0004C\u0001\n9\u0013\tITBA\u0004O_RD\u0017N\\4\u0011\u0005IY\u0014B\u0001\u001f\u000e\u0005\r\te.\u001f\t\u0003gy\"Qa\u0010\u0002C\u0002Y\u0012!\u0001\u00169\u0011\t=\n%'P\u0005\u0003\u0005j\u0011q\"Q,T)^\u0013\u0018\r\u001d9fIR\u000b7o[\u0001\u0005E>$\u00170F\u0001F!\u00111bIM\u001f\n\u0005\u001dK!\u0001\u0002+bg.\fQAY8es\u0002\na\u0001P5oSRtDCA&M!\u0011y#AM\u001f\t\u000b\r+\u0001\u0019A#\u0002\u000bM\u0004H.\u001b;\u0016\u0003=\u00032\u0001U*L\u001b\u0005\t&B\u0001*\f\u0003%IW.\\;uC\ndW-\u0003\u0002U#\n\u00191+Z9\u0002\u001d9,wo\u0016:baB,G\rV1tWV\u0019qK\u0017/\u0015\u0005ak\u0006\u0003B\u0018\u00033n\u0003\"a\r.\u0005\u000bU:!\u0019\u0001\u001c\u0011\u0005MbF!B \b\u0005\u00041\u0004\"\u00020\b\u0001\u0004y\u0016!\u00012\u0011\tY1\u0015l\u0017"
)
public interface AdaptiveWorkStealingForkJoinTasks extends ForkJoinTasks, AdaptiveWorkStealingTasks {
   // $FF: synthetic method
   static AWSFJTWrappedTask newWrappedTask$(final AdaptiveWorkStealingForkJoinTasks $this, final Task b) {
      return $this.newWrappedTask(b);
   }

   default AWSFJTWrappedTask newWrappedTask(final Task b) {
      return new AWSFJTWrappedTask(b);
   }

   static void $init$(final AdaptiveWorkStealingForkJoinTasks $this) {
   }

   public class AWSFJTWrappedTask extends RecursiveAction implements ForkJoinTasks.FJTWrappedTask, AdaptiveWorkStealingTasks.AWSTWrappedTask {
      private final Task body;
      private volatile AdaptiveWorkStealingTasks.AWSTWrappedTask next;
      private volatile boolean shouldWaitFor;
      // $FF: synthetic field
      public final AdaptiveWorkStealingForkJoinTasks $outer;

      public void compute() {
         AdaptiveWorkStealingTasks.AWSTWrappedTask.compute$(this);
      }

      public void internal() {
         AdaptiveWorkStealingTasks.AWSTWrappedTask.internal$(this);
      }

      public AdaptiveWorkStealingTasks.AWSTWrappedTask spawnSubtasks() {
         return AdaptiveWorkStealingTasks.AWSTWrappedTask.spawnSubtasks$(this);
      }

      public void printChain() {
         AdaptiveWorkStealingTasks.AWSTWrappedTask.printChain$(this);
      }

      public void start() {
         ForkJoinTasks.FJTWrappedTask.start$(this);
      }

      public void sync() {
         ForkJoinTasks.FJTWrappedTask.sync$(this);
      }

      public boolean tryCancel() {
         return ForkJoinTasks.FJTWrappedTask.tryCancel$(this);
      }

      public void release() {
         Tasks.WrappedTask.release$(this);
      }

      public AdaptiveWorkStealingTasks.AWSTWrappedTask next() {
         return this.next;
      }

      public void next_$eq(final AdaptiveWorkStealingTasks.AWSTWrappedTask x$1) {
         this.next = x$1;
      }

      public boolean shouldWaitFor() {
         return this.shouldWaitFor;
      }

      public void shouldWaitFor_$eq(final boolean x$1) {
         this.shouldWaitFor = x$1;
      }

      public Task body() {
         return this.body;
      }

      public Seq split() {
         return (Seq)this.body().split().map((b) -> this.scala$collection$parallel$AdaptiveWorkStealingForkJoinTasks$AWSFJTWrappedTask$$$outer().newWrappedTask(b));
      }

      // $FF: synthetic method
      public AdaptiveWorkStealingForkJoinTasks scala$collection$parallel$AdaptiveWorkStealingForkJoinTasks$AWSFJTWrappedTask$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public AdaptiveWorkStealingTasks scala$collection$parallel$AdaptiveWorkStealingTasks$AWSTWrappedTask$$$outer() {
         return this.scala$collection$parallel$AdaptiveWorkStealingForkJoinTasks$AWSFJTWrappedTask$$$outer();
      }

      // $FF: synthetic method
      public ForkJoinTasks scala$collection$parallel$ForkJoinTasks$FJTWrappedTask$$$outer() {
         return this.scala$collection$parallel$AdaptiveWorkStealingForkJoinTasks$AWSFJTWrappedTask$$$outer();
      }

      // $FF: synthetic method
      public Tasks scala$collection$parallel$Tasks$WrappedTask$$$outer() {
         return this.scala$collection$parallel$AdaptiveWorkStealingForkJoinTasks$AWSFJTWrappedTask$$$outer();
      }

      public AWSFJTWrappedTask(final Task body) {
         this.body = body;
         if (AdaptiveWorkStealingForkJoinTasks.this == null) {
            throw null;
         } else {
            this.$outer = AdaptiveWorkStealingForkJoinTasks.this;
            super();
            Tasks.WrappedTask.$init$(this);
            ForkJoinTasks.FJTWrappedTask.$init$(this);
            AdaptiveWorkStealingTasks.AWSTWrappedTask.$init$(this);
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}

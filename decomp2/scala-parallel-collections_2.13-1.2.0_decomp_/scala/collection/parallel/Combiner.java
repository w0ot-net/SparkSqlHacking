package scala.collection.parallel;

import java.lang.invoke.SerializedLambda;
import scala.collection.IterableOnce;
import scala.collection.Parallel;
import scala.collection.generic.Sizing;
import scala.collection.mutable.Builder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I4qAC\u0006\u0011\u0002\u0007\u0005!\u0003C\u00037\u0001\u0011\u0005q\u0007C\u0004<\u0001\u0001\u0007I\u0011\u0001\u001f\t\u000f%\u0003\u0001\u0019!C\u0001\u0015\")Q\n\u0001C\u0001y!)a\n\u0001C\u0001\u001f\")!\u000b\u0001D\u0001'\")\u0001\r\u0001C\u0001C\")Q\r\u0001C\u0001M\")q\r\u0001C\u0001Q\nA1i\\7cS:,'O\u0003\u0002\r\u001b\u0005A\u0001/\u0019:bY2,GN\u0003\u0002\u000f\u001f\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003A\tQa]2bY\u0006\u001c\u0001!F\u0002\u0014A)\u001aR\u0001\u0001\u000b\u0019YI\u0002\"!\u0006\f\u000e\u0003=I!aF\b\u0003\r\u0005s\u0017PU3g!\u0011IBDH\u0015\u000e\u0003iQ!aG\u0007\u0002\u000f5,H/\u00192mK&\u0011QD\u0007\u0002\b\u0005VLG\u000eZ3s!\ty\u0002\u0005\u0004\u0001\u0005\r\u0005\u0002\u0001R1\u0001#\u0005\u0011)E.Z7\u0012\u0005\r2\u0003CA\u000b%\u0013\t)sBA\u0004O_RD\u0017N\\4\u0011\u0005U9\u0013B\u0001\u0015\u0010\u0005\r\te.\u001f\t\u0003?)\"aa\u000b\u0001\u0005\u0006\u0004\u0011#A\u0001+p!\ti\u0003'D\u0001/\u0015\tyS\"A\u0004hK:,'/[2\n\u0005Er#AB*ju&tw\r\u0005\u00024i5\tQ\"\u0003\u00026\u001b\tA\u0001+\u0019:bY2,G.\u0001\u0004%S:LG\u000f\n\u000b\u0002qA\u0011Q#O\u0005\u0003u=\u0011A!\u00168ji\u0006!rlY8nE&tWM\u001d+bg.\u001cV\u000f\u001d9peR,\u0012!\u0010\t\u0003}}j\u0011aC\u0005\u0003\u0001.\u00111\u0002V1tWN+\b\u000f]8si\"\u0012!A\u0011\t\u0003+\rK!\u0001R\b\u0003\u0013Q\u0014\u0018M\\:jK:$\bF\u0001\u0002G!\t)r)\u0003\u0002I\u001f\tAao\u001c7bi&dW-\u0001\r`G>l'-\u001b8feR\u000b7o[*vaB|'\u000f^0%KF$\"\u0001O&\t\u000f1\u001b\u0011\u0011!a\u0001{\u0005\u0019\u0001\u0010J\u0019\u0002'\r|WNY5oKJ$\u0016m]6TkB\u0004xN\u001d;\u0002/\r|WNY5oKJ$\u0016m]6TkB\u0004xN\u001d;`I\u0015\fHC\u0001\u001dQ\u0011\u0015\tV\u00011\u0001>\u0003\r\u0019Go]\u0001\bG>l'-\u001b8f+\r!vk\u0017\u000b\u0003+z\u0003BA\u0010\u0001W5B\u0011qd\u0016\u0003\u00061\u001a\u0011\r!\u0017\u0002\u0002\u001dF\u00111E\b\t\u0003?m#Q\u0001\u0018\u0004C\u0002u\u0013QAT3x)>\f\"!\u000b\u0014\t\u000b}3\u0001\u0019A+\u0002\u000b=$\b.\u001a:\u0002\u0017\r\fgNQ3TQ\u0006\u0014X\rZ\u000b\u0002EB\u0011QcY\u0005\u0003I>\u0011qAQ8pY\u0016\fg.A\u000bsKN,H\u000e^,ji\"$\u0016m]6TkB\u0004xN\u001d;\u0016\u0003%\naB\u001a:p[N+\u0017/^3oi&\fG\u000e\u0006\u0002*S\")!.\u0003a\u0001W\u0006\u00191/Z9\u0011\u00071|gD\u0004\u0002\u0016[&\u0011anD\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0001\u0018O\u0001\u0007Ji\u0016\u0014\u0018M\u00197f\u001f:\u001cWM\u0003\u0002o\u001f\u0001"
)
public interface Combiner extends Builder, Sizing, Parallel {
   TaskSupport _combinerTaskSupport();

   void _combinerTaskSupport_$eq(final TaskSupport x$1);

   // $FF: synthetic method
   static TaskSupport combinerTaskSupport$(final Combiner $this) {
      return $this.combinerTaskSupport();
   }

   default TaskSupport combinerTaskSupport() {
      TaskSupport cts = this._combinerTaskSupport();
      if (cts == null) {
         this._combinerTaskSupport_$eq(package$.MODULE$.defaultTaskSupport());
         return package$.MODULE$.defaultTaskSupport();
      } else {
         return cts;
      }
   }

   // $FF: synthetic method
   static void combinerTaskSupport_$eq$(final Combiner $this, final TaskSupport cts) {
      $this.combinerTaskSupport_$eq(cts);
   }

   default void combinerTaskSupport_$eq(final TaskSupport cts) {
      this._combinerTaskSupport_$eq(cts);
   }

   Combiner combine(final Combiner other);

   // $FF: synthetic method
   static boolean canBeShared$(final Combiner $this) {
      return $this.canBeShared();
   }

   default boolean canBeShared() {
      return false;
   }

   // $FF: synthetic method
   static Object resultWithTaskSupport$(final Combiner $this) {
      return $this.resultWithTaskSupport();
   }

   default Object resultWithTaskSupport() {
      Object res = this.result();
      return package$.MODULE$.setTaskSupport(res, this.combinerTaskSupport());
   }

   // $FF: synthetic method
   static Object fromSequential$(final Combiner $this, final IterableOnce seq) {
      return $this.fromSequential(seq);
   }

   default Object fromSequential(final IterableOnce seq) {
      seq.iterator().foreach((x) -> (Combiner)this.$plus$eq(x));
      return this.result();
   }

   static void $init$(final Combiner $this) {
      $this._combinerTaskSupport_$eq(package$.MODULE$.defaultTaskSupport());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

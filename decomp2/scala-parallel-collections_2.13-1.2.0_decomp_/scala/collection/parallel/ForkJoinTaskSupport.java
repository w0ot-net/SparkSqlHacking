package scala.collection.parallel;

import java.util.concurrent.ForkJoinPool;
import scala.Function0;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005u2A\u0001C\u0005\u0001!!AA\u0004\u0001BC\u0002\u0013\u0005Q\u0004\u0003\u0005)\u0001\t\u0005\t\u0015!\u0003\u001f\u0011\u0015I\u0003\u0001\"\u0001+\u000f\u001di\u0013\"!A\t\u000292q\u0001C\u0005\u0002\u0002#\u0005q\u0006C\u0003*\u000b\u0011\u0005\u0001\u0007C\u00042\u000bE\u0005I\u0011\u0001\u001a\u0003'\u0019{'o\u001b&pS:$\u0016m]6TkB\u0004xN\u001d;\u000b\u0005)Y\u0011\u0001\u00039be\u0006dG.\u001a7\u000b\u00051i\u0011AC2pY2,7\r^5p]*\ta\"A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\t\u0001\tR#\u0007\t\u0003%Mi\u0011!D\u0005\u0003)5\u0011a!\u00118z%\u00164\u0007C\u0001\f\u0018\u001b\u0005I\u0011B\u0001\r\n\u0005-!\u0016m]6TkB\u0004xN\u001d;\u0011\u0005YQ\u0012BA\u000e\n\u0005\u0005\nE-\u00199uSZ,wk\u001c:l'R,\u0017\r\\5oO\u001a{'o\u001b&pS:$\u0016m]6t\u0003-)gN^5s_:lWM\u001c;\u0016\u0003y\u0001\"a\b\u0014\u000e\u0003\u0001R!!\t\u0012\u0002\u0015\r|gnY;se\u0016tGO\u0003\u0002$I\u0005!Q\u000f^5m\u0015\u0005)\u0013\u0001\u00026bm\u0006L!a\n\u0011\u0003\u0019\u0019{'o\u001b&pS:\u0004vn\u001c7\u0002\u0019\u0015tg/\u001b:p]6,g\u000e\u001e\u0011\u0002\rqJg.\u001b;?)\tYC\u0006\u0005\u0002\u0017\u0001!9Ad\u0001I\u0001\u0002\u0004q\u0012a\u0005$pe.Tu.\u001b8UCN\\7+\u001e9q_J$\bC\u0001\f\u0006'\t)\u0011\u0003F\u0001/\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%cU\t1G\u000b\u0002\u001fi-\nQ\u0007\u0005\u00027w5\tqG\u0003\u00029s\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0003u5\t!\"\u00198o_R\fG/[8o\u0013\tatGA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\u0004"
)
public class ForkJoinTaskSupport implements TaskSupport, AdaptiveWorkStealingForkJoinTasks {
   private final ForkJoinPool environment;
   private ArrayBuffer debugMessages;

   public static ForkJoinPool $lessinit$greater$default$1() {
      return ForkJoinTaskSupport$.MODULE$.$lessinit$greater$default$1();
   }

   public AdaptiveWorkStealingForkJoinTasks.AWSFJTWrappedTask newWrappedTask(final Task b) {
      return AdaptiveWorkStealingForkJoinTasks.newWrappedTask$(this, b);
   }

   public ForkJoinPool forkJoinPool() {
      return ForkJoinTasks.forkJoinPool$(this);
   }

   public Function0 execute(final Task task) {
      return ForkJoinTasks.execute$(this, task);
   }

   public Object executeAndWaitResult(final Task task) {
      return ForkJoinTasks.executeAndWaitResult$(this, task);
   }

   public int parallelismLevel() {
      return ForkJoinTasks.parallelismLevel$(this);
   }

   public ArrayBuffer debuglog(final String s) {
      return Tasks.debuglog$(this, s);
   }

   public ArrayBuffer debugMessages() {
      return this.debugMessages;
   }

   public void scala$collection$parallel$Tasks$_setter_$debugMessages_$eq(final ArrayBuffer x$1) {
      this.debugMessages = x$1;
   }

   public ForkJoinPool environment() {
      return this.environment;
   }

   public ForkJoinTaskSupport(final ForkJoinPool environment) {
      this.environment = environment;
      Tasks.$init$(this);
      ForkJoinTasks.$init$(this);
      AdaptiveWorkStealingTasks.$init$(this);
      AdaptiveWorkStealingForkJoinTasks.$init$(this);
      Statics.releaseFence();
   }
}

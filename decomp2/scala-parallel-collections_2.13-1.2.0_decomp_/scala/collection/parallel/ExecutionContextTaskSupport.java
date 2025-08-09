package scala.collection.parallel;

import scala.Function0;
import scala.collection.mutable.ArrayBuffer;
import scala.concurrent.ExecutionContext;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005e2A\u0001C\u0005\u0001!!AA\u0004\u0001BC\u0002\u0013\u0005Q\u0004\u0003\u0005%\u0001\t\u0005\t\u0015!\u0003\u001f\u0011\u0015)\u0003\u0001\"\u0001'\u000f\u001dI\u0013\"!A\t\u0002)2q\u0001C\u0005\u0002\u0002#\u00051\u0006C\u0003&\u000b\u0011\u0005A\u0006C\u0004.\u000bE\u0005I\u0011\u0001\u0018\u00037\u0015CXmY;uS>t7i\u001c8uKb$H+Y:l'V\u0004\bo\u001c:u\u0015\tQ1\"\u0001\u0005qCJ\fG\u000e\\3m\u0015\taQ\"\u0001\u0006d_2dWm\u0019;j_:T\u0011AD\u0001\u0006g\u000e\fG.Y\u0002\u0001'\u0011\u0001\u0011#F\r\u0011\u0005I\u0019R\"A\u0007\n\u0005Qi!AB!osJ+g\r\u0005\u0002\u0017/5\t\u0011\"\u0003\u0002\u0019\u0013\tYA+Y:l'V\u0004\bo\u001c:u!\t1\"$\u0003\u0002\u001c\u0013\t)R\t_3dkRLwN\\\"p]R,\u0007\u0010\u001e+bg.\u001c\u0018aC3om&\u0014xN\\7f]R,\u0012A\b\t\u0003?\tj\u0011\u0001\t\u0006\u0003C5\t!bY8oGV\u0014(/\u001a8u\u0013\t\u0019\u0003E\u0001\tFq\u0016\u001cW\u000f^5p]\u000e{g\u000e^3yi\u0006aQM\u001c<je>tW.\u001a8uA\u00051A(\u001b8jiz\"\"a\n\u0015\u0011\u0005Y\u0001\u0001b\u0002\u000f\u0004!\u0003\u0005\rAH\u0001\u001c\u000bb,7-\u001e;j_:\u001cuN\u001c;fqR$\u0016m]6TkB\u0004xN\u001d;\u0011\u0005Y)1CA\u0003\u0012)\u0005Q\u0013a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0013'F\u00010U\tq\u0002gK\u00012!\t\u0011t'D\u00014\u0015\t!T'A\u0005v]\u000eDWmY6fI*\u0011a'D\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001\u001d4\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a"
)
public class ExecutionContextTaskSupport implements TaskSupport, ExecutionContextTasks {
   private final ExecutionContext environment;
   private Tasks scala$collection$parallel$ExecutionContextTasks$$driver;
   private ArrayBuffer debugMessages;

   public static ExecutionContext $lessinit$greater$default$1() {
      return ExecutionContextTaskSupport$.MODULE$.$lessinit$greater$default$1();
   }

   public ExecutionContext executionContext() {
      return ExecutionContextTasks.executionContext$(this);
   }

   public Function0 execute(final Task task) {
      return ExecutionContextTasks.execute$(this, task);
   }

   public Object executeAndWaitResult(final Task task) {
      return ExecutionContextTasks.executeAndWaitResult$(this, task);
   }

   public int parallelismLevel() {
      return ExecutionContextTasks.parallelismLevel$(this);
   }

   public ArrayBuffer debuglog(final String s) {
      return Tasks.debuglog$(this, s);
   }

   public Tasks scala$collection$parallel$ExecutionContextTasks$$driver() {
      return this.scala$collection$parallel$ExecutionContextTasks$$driver;
   }

   public final void scala$collection$parallel$ExecutionContextTasks$_setter_$scala$collection$parallel$ExecutionContextTasks$$driver_$eq(final Tasks x$1) {
      this.scala$collection$parallel$ExecutionContextTasks$$driver = x$1;
   }

   public ArrayBuffer debugMessages() {
      return this.debugMessages;
   }

   public void scala$collection$parallel$Tasks$_setter_$debugMessages_$eq(final ArrayBuffer x$1) {
      this.debugMessages = x$1;
   }

   public ExecutionContext environment() {
      return this.environment;
   }

   public ExecutionContextTaskSupport(final ExecutionContext environment) {
      this.environment = environment;
      Tasks.$init$(this);
      ExecutionContextTasks.$init$(this);
      Statics.releaseFence();
   }
}

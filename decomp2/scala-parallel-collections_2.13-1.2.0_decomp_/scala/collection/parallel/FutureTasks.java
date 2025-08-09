package scala.collection.parallel;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.Await.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Statics;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014Qa\u0003\u0007\u0003\u0019IA\u0001b\u0007\u0001\u0003\u0002\u0003\u0006I!\b\u0005\u0006G\u0001!\t\u0001\n\u0005\bO\u0001\u0011\r\u0011\"\u0003)\u0011\u0019a\u0003\u0001)A\u0005S!9Q\u0006\u0001b\u0001\n\u0003q\u0003BB\u0018\u0001A\u0003%Q\u0004C\u00031\u0001\u0011%\u0011\u0007C\u0003J\u0001\u0011\u0005!\nC\u0003W\u0001\u0011\u0005q\u000bC\u0003`\u0001\u0011\u0005\u0001FA\u0006GkR,(/\u001a+bg.\u001c(BA\u0007\u000f\u0003!\u0001\u0018M]1mY\u0016d'BA\b\u0011\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002#\u0005)1oY1mCN\u0019\u0001aE\f\u0011\u0005Q)R\"\u0001\t\n\u0005Y\u0001\"AB!osJ+g\r\u0005\u0002\u001935\tA\"\u0003\u0002\u001b\u0019\t)A+Y:lg\u0006AQ\r_3dkR|'o\u0001\u0001\u0011\u0005y\tS\"A\u0010\u000b\u0005\u0001\u0002\u0012AC2p]\u000e,(O]3oi&\u0011!e\b\u0002\u0011\u000bb,7-\u001e;j_:\u001cuN\u001c;fqR\fa\u0001P5oSRtDCA\u0013'!\tA\u0002\u0001C\u0003\u001c\u0005\u0001\u0007Q$\u0001\u0005nCb$W\r\u001d;i+\u0005I\u0003C\u0001\u000b+\u0013\tY\u0003CA\u0002J]R\f\u0011\"\\1yI\u0016\u0004H\u000f\u001b\u0011\u0002\u0017\u0015tg/\u001b:p]6,g\u000e^\u000b\u0002;\u0005aQM\u001c<je>tW.\u001a8uA\u0005!Q\r_3d+\r\u0011\u0004h\u0012\u000b\u0003g\u0005\u00032A\b\u001b7\u0013\t)tD\u0001\u0004GkR,(/\u001a\t\u0003oab\u0001\u0001B\u0003:\u000f\t\u0007!HA\u0001S#\tYd\b\u0005\u0002\u0015y%\u0011Q\b\u0005\u0002\b\u001d>$\b.\u001b8h!\t!r(\u0003\u0002A!\t\u0019\u0011I\\=\t\u000b\t;\u0001\u0019A\"\u0002\u0019Q|\u0007\u000fT3wK2$\u0016m]6\u0011\ta!eGR\u0005\u0003\u000b2\u0011A\u0001V1tWB\u0011qg\u0012\u0003\u0006\u0011\u001e\u0011\rA\u000f\u0002\u0003)B\fq!\u001a=fGV$X-F\u0002L!V#\"\u0001T)\u0011\u0007Qiu*\u0003\u0002O!\tIa)\u001e8di&|g\u000e\r\t\u0003oA#Q!\u000f\u0005C\u0002iBQA\u0015\u0005A\u0002M\u000bA\u0001^1tWB!\u0001\u0004R(U!\t9T\u000bB\u0003I\u0011\t\u0007!(\u0001\u000bfq\u0016\u001cW\u000f^3B]\u0012<\u0016-\u001b;SKN,H\u000e^\u000b\u00041jsFCA-\\!\t9$\fB\u0003:\u0013\t\u0007!\bC\u0003S\u0013\u0001\u0007A\f\u0005\u0003\u0019\tfk\u0006CA\u001c_\t\u0015A\u0015B1\u0001;\u0003A\u0001\u0018M]1mY\u0016d\u0017n]7MKZ,G\u000e"
)
public final class FutureTasks implements Tasks {
   private final int maxdepth;
   private final ExecutionContext environment;
   private ArrayBuffer debugMessages;

   public ArrayBuffer debuglog(final String s) {
      return Tasks.debuglog$(this, s);
   }

   public ArrayBuffer debugMessages() {
      return this.debugMessages;
   }

   public void scala$collection$parallel$Tasks$_setter_$debugMessages_$eq(final ArrayBuffer x$1) {
      this.debugMessages = x$1;
   }

   private int maxdepth() {
      return this.maxdepth;
   }

   public ExecutionContext environment() {
      return this.environment;
   }

   private Future exec(final Task topLevelTask) {
      ExecutionContext ec = this.environment();
      return this.compute$1(topLevelTask, 0, ec).map((t) -> {
         t.forwardThrowable();
         return t.result();
      }, ec);
   }

   public Function0 execute(final Task task) {
      Future future = this.exec(task);
      Function0 callback = () -> .MODULE$.result(future, scala.concurrent.duration.Duration..MODULE$.Inf());
      return callback;
   }

   public Object executeAndWaitResult(final Task task) {
      return this.execute(task).apply();
   }

   public int parallelismLevel() {
      return Runtime.getRuntime().availableProcessors();
   }

   private final Future compute$1(final Task task, final int depth, final ExecutionContext ec$1) {
      if (task.shouldSplitFurther() && depth < this.maxdepth()) {
         Seq subtasks = task.split();
         Iterator subfutures = subtasks.iterator().map((subtask) -> this.compute$1(subtask, depth + 1, ec$1));
         return ((Future)subfutures.reduceLeft((firstFuture, nextFuture) -> firstFuture.flatMap((firstTask) -> nextFuture.map((nextTask) -> {
                  firstTask.tryMerge(nextTask.repr());
                  return firstTask;
               }, ec$1), ec$1))).andThen(new Serializable(task) {
            private static final long serialVersionUID = 0L;
            private final Task task$1;

            public final Object applyOrElse(final Try x1, final Function1 default) {
               if (x1 instanceof Success) {
                  Success var5 = (Success)x1;
                  Task firstTask = (Task)var5.value();
                  this.task$1.throwable_$eq(firstTask.throwable());
                  this.task$1.result_$eq(firstTask.result());
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof Failure) {
                  Failure var7 = (Failure)x1;
                  Throwable exception = var7.exception();
                  this.task$1.throwable_$eq(exception);
                  return BoxedUnit.UNIT;
               } else {
                  return default.apply(x1);
               }
            }

            public final boolean isDefinedAt(final Try x1) {
               if (x1 instanceof Success) {
                  return true;
               } else {
                  return x1 instanceof Failure;
               }
            }

            public {
               this.task$1 = task$1;
            }
         }, ec$1);
      } else {
         return scala.concurrent.Future..MODULE$.apply(() -> {
            task.tryLeaf(scala.None..MODULE$);
            return task;
         }, ec$1);
      }
   }

   public FutureTasks(final ExecutionContext executor) {
      Tasks.$init$(this);
      this.maxdepth = (int)(scala.math.package..MODULE$.log((double)this.parallelismLevel()) / scala.math.package..MODULE$.log((double)2.0F) + (double)1);
      this.environment = executor;
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

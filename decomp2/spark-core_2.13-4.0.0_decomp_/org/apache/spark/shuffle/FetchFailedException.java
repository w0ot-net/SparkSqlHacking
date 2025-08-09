package org.apache.spark.shuffle;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.FetchFailed;
import org.apache.spark.TaskContext;
import org.apache.spark.TaskContext$;
import org.apache.spark.TaskFailedReason;
import org.apache.spark.storage.BlockManagerId;
import org.apache.spark.util.Utils$;
import scala.Option.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%a!\u0002\t\u0012\u0001MI\u0002\u0002C\u0015\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0016\t\u0011A\u0002!\u0011!Q\u0001\nEB\u0001\"\u000e\u0001\u0003\u0002\u0003\u0006IA\u000e\u0005\ts\u0001\u0011\t\u0011)A\u0005c!A!\b\u0001B\u0001B\u0003%\u0011\u0007\u0003\u0005<\u0001\t\u0005\t\u0015!\u0003=\u0011!!\u0005A!A!\u0002\u0013)\u0005\"\u0002%\u0001\t\u0003I\u0005\"\u0002%\u0001\t\u0003\u0019\u0006\"B.\u0001\t\u0003av\u0001C1\u0012\u0003\u0003E\ta\u00052\u0007\u0011A\t\u0012\u0011!E\u0001'\rDQ\u0001\u0013\u0007\u0005\u0002=Dq\u0001\u001d\u0007\u0012\u0002\u0013\u0005\u0011\u000fC\u0004}\u0019\u0005\u0005I\u0011B?\u0003)\u0019+Go\u00195GC&dW\rZ#yG\u0016\u0004H/[8o\u0015\t\u00112#A\u0004tQV4g\r\\3\u000b\u0005Q)\u0012!B:qCJ\\'B\u0001\f\u0018\u0003\u0019\t\u0007/Y2iK*\t\u0001$A\u0002pe\u001e\u001c\"\u0001\u0001\u000e\u0011\u0005m1cB\u0001\u000f$\u001d\ti\u0012%D\u0001\u001f\u0015\ty\u0002%\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005\u0011\u0013!B:dC2\f\u0017B\u0001\u0013&\u0003\u001d\u0001\u0018mY6bO\u0016T\u0011AI\u0005\u0003O!\u0012\u0011\"\u0012=dKB$\u0018n\u001c8\u000b\u0005\u0011*\u0013!\u00032n\u0003\u0012$'/Z:t!\tYc&D\u0001-\u0015\ti3#A\u0004ti>\u0014\u0018mZ3\n\u0005=b#A\u0004\"m_\u000e\\W*\u00198bO\u0016\u0014\u0018\nZ\u0001\ng\",hM\u001a7f\u0013\u0012\u0004\"AM\u001a\u000e\u0003\u0015J!\u0001N\u0013\u0003\u0007%sG/A\u0003nCBLE\r\u0005\u00023o%\u0011\u0001(\n\u0002\u0005\u0019>tw-\u0001\u0005nCBLe\u000eZ3y\u0003!\u0011X\rZ;dK&#\u0017aB7fgN\fw-\u001a\t\u0003{\u0005s!AP \u0011\u0005u)\u0013B\u0001!&\u0003\u0019\u0001&/\u001a3fM&\u0011!i\u0011\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0001+\u0013!B2bkN,\u0007CA\u000eG\u0013\t9\u0005FA\u0005UQJ|w/\u00192mK\u00061A(\u001b8jiz\"\u0002B\u0013'N\u001d>\u0003\u0016K\u0015\t\u0003\u0017\u0002i\u0011!\u0005\u0005\u0006S!\u0001\rA\u000b\u0005\u0006a!\u0001\r!\r\u0005\u0006k!\u0001\rA\u000e\u0005\u0006s!\u0001\r!\r\u0005\u0006u!\u0001\r!\r\u0005\u0006w!\u0001\r\u0001\u0010\u0005\b\t\"\u0001\n\u00111\u0001F)\u001dQE+\u0016,Y3jCQ!K\u0005A\u0002)BQ\u0001M\u0005A\u0002EBQaV\u0005A\u0002Y\n\u0011\"\\1q)\u0006\u001c8.\u00133\t\u000beJ\u0001\u0019A\u0019\t\u000biJ\u0001\u0019A\u0019\t\u000b\u0011K\u0001\u0019A#\u0002%Q|G+Y:l\r\u0006LG.\u001a3SK\u0006\u001cxN\\\u000b\u0002;B\u0011alX\u0007\u0002'%\u0011\u0001m\u0005\u0002\u0011)\u0006\u001c8NR1jY\u0016$'+Z1t_:\fACR3uG\"4\u0015-\u001b7fI\u0016C8-\u001a9uS>t\u0007CA&\r'\raAm\u001a\t\u0003e\u0015L!AZ\u0013\u0003\r\u0005s\u0017PU3g!\tAW.D\u0001j\u0015\tQ7.\u0001\u0002j_*\tA.\u0001\u0003kCZ\f\u0017B\u00018j\u00051\u0019VM]5bY&T\u0018M\u00197f)\u0005\u0011\u0017a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$s'F\u0001sU\t)5oK\u0001u!\t)(0D\u0001w\u0015\t9\b0A\u0005v]\u000eDWmY6fI*\u0011\u00110J\u0001\u000bC:tw\u000e^1uS>t\u0017BA>w\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0002}B\u0019q0!\u0002\u000e\u0005\u0005\u0005!bAA\u0002W\u0006!A.\u00198h\u0013\u0011\t9!!\u0001\u0003\r=\u0013'.Z2u\u0001"
)
public class FetchFailedException extends Exception {
   private final BlockManagerId bmAddress;
   private final int shuffleId;
   private final long mapId;
   private final int mapIndex;
   private final int reduceId;

   public static Throwable $lessinit$greater$default$7() {
      return FetchFailedException$.MODULE$.$lessinit$greater$default$7();
   }

   public TaskFailedReason toTaskFailedReason() {
      return new FetchFailed(this.bmAddress, this.shuffleId, this.mapId, this.mapIndex, this.reduceId, Utils$.MODULE$.exceptionString(this));
   }

   // $FF: synthetic method
   public static final void $anonfun$new$1(final FetchFailedException $this, final TaskContext x$1) {
      x$1.setFetchFailed($this);
   }

   public FetchFailedException(final BlockManagerId bmAddress, final int shuffleId, final long mapId, final int mapIndex, final int reduceId, final String message, final Throwable cause) {
      super(message, cause);
      this.bmAddress = bmAddress;
      this.shuffleId = shuffleId;
      this.mapId = mapId;
      this.mapIndex = mapIndex;
      this.reduceId = reduceId;
      .MODULE$.apply(TaskContext$.MODULE$.get()).foreach((x$1) -> {
         $anonfun$new$1(this, x$1);
         return BoxedUnit.UNIT;
      });
   }

   public FetchFailedException(final BlockManagerId bmAddress, final int shuffleId, final long mapTaskId, final int mapIndex, final int reduceId, final Throwable cause) {
      this(bmAddress, shuffleId, mapTaskId, mapIndex, reduceId, cause.getMessage(), cause);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

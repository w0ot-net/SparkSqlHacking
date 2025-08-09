package org.apache.spark.ml.param.shared;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Params;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u000512q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007IQA\u0012\t\u000b\u001d\u0002AQ\u0001\u0015\u0003+!\u000b7o\u00115fG.\u0004x.\u001b8u\u0013:$XM\u001d<bY*\u0011aaB\u0001\u0007g\"\f'/\u001a3\u000b\u0005!I\u0011!\u00029be\u0006l'B\u0001\u0006\f\u0003\tiGN\u0003\u0002\r\u001b\u0005)1\u000f]1sW*\u0011abD\u0001\u0007CB\f7\r[3\u000b\u0003A\t1a\u001c:h\u0007\u0001\u00192\u0001A\n\u001a!\t!r#D\u0001\u0016\u0015\u00051\u0012!B:dC2\f\u0017B\u0001\r\u0016\u0005\u0019\te.\u001f*fMB\u0011!dG\u0007\u0002\u000f%\u0011Ad\u0002\u0002\u0007!\u0006\u0014\u0018-\\:\u0002\r\u0011Jg.\u001b;%)\u0005y\u0002C\u0001\u000b!\u0013\t\tSC\u0001\u0003V]&$\u0018AE2iK\u000e\\\u0007o\\5oi&sG/\u001a:wC2,\u0012\u0001\n\t\u00035\u0015J!AJ\u0004\u0003\u0011%sG\u000fU1sC6\fQcZ3u\u0007\",7m\u001b9pS:$\u0018J\u001c;feZ\fG.F\u0001*!\t!\"&\u0003\u0002,+\t\u0019\u0011J\u001c;"
)
public interface HasCheckpointInterval extends Params {
   void org$apache$spark$ml$param$shared$HasCheckpointInterval$_setter_$checkpointInterval_$eq(final IntParam x$1);

   IntParam checkpointInterval();

   // $FF: synthetic method
   static int getCheckpointInterval$(final HasCheckpointInterval $this) {
      return $this.getCheckpointInterval();
   }

   default int getCheckpointInterval() {
      return BoxesRunTime.unboxToInt(this.$(this.checkpointInterval()));
   }

   static void $init$(final HasCheckpointInterval $this) {
      $this.org$apache$spark$ml$param$shared$HasCheckpointInterval$_setter_$checkpointInterval_$eq(new IntParam($this, "checkpointInterval", "set checkpoint interval (>= 1) or disable checkpoint (-1). E.g. 10 means that the cache will get checkpointed every 10 iterations. Note: this setting will be ignored if the checkpoint directory is not set in the SparkContext", (JFunction1.mcZI.sp)(interval) -> interval == -1 || interval >= 1));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

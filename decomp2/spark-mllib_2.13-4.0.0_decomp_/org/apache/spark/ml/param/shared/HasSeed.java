package org.apache.spark.ml.param.shared;

import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Params;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000512q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007IQA\u0012\t\u000b\u001d\u0002AQ\u0001\u0015\u0003\u000f!\u000b7oU3fI*\u0011aaB\u0001\u0007g\"\f'/\u001a3\u000b\u0005!I\u0011!\u00029be\u0006l'B\u0001\u0006\f\u0003\tiGN\u0003\u0002\r\u001b\u0005)1\u000f]1sW*\u0011abD\u0001\u0007CB\f7\r[3\u000b\u0003A\t1a\u001c:h\u0007\u0001\u00192\u0001A\n\u001a!\t!r#D\u0001\u0016\u0015\u00051\u0012!B:dC2\f\u0017B\u0001\r\u0016\u0005\u0019\te.\u001f*fMB\u0011!dG\u0007\u0002\u000f%\u0011Ad\u0002\u0002\u0007!\u0006\u0014\u0018-\\:\u0002\r\u0011Jg.\u001b;%)\u0005y\u0002C\u0001\u000b!\u0013\t\tSC\u0001\u0003V]&$\u0018\u0001B:fK\u0012,\u0012\u0001\n\t\u00035\u0015J!AJ\u0004\u0003\u00131{gn\u001a)be\u0006l\u0017aB4fiN+W\rZ\u000b\u0002SA\u0011ACK\u0005\u0003WU\u0011A\u0001T8oO\u0002"
)
public interface HasSeed extends Params {
   void org$apache$spark$ml$param$shared$HasSeed$_setter_$seed_$eq(final LongParam x$1);

   LongParam seed();

   // $FF: synthetic method
   static long getSeed$(final HasSeed $this) {
      return $this.getSeed();
   }

   default long getSeed() {
      return BoxesRunTime.unboxToLong(this.$(this.seed()));
   }

   static void $init$(final HasSeed $this) {
      $this.org$apache$spark$ml$param$shared$HasSeed$_setter_$seed_$eq(new LongParam($this, "seed", "random seed"));
      $this.setDefault($this.seed(), BoxesRunTime.boxToLong((long)$this.getClass().getName().hashCode()));
   }
}

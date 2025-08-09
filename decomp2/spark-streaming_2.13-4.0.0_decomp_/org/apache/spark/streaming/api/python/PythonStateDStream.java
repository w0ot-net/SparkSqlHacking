package org.apache.spark.streaming.api.python;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.storage.StorageLevel.;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.dstream.DStream;
import scala.Option;
import scala.Some;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u3QAC\u0006\u0001\u0017]A\u0001\u0002\b\u0001\u0003\u0002\u0003\u0006IA\b\u0005\t[\u0001\u0011\t\u0011)A\u0005]!A\u0011\u0007\u0001B\u0001B\u0003%!\u0007C\u0003<\u0001\u0011\u0005A\bC\u0003<\u0001\u0011\u0005\u0011\tC\u0003<\u0001\u0011\u0005A\tC\u0004P\u0001\t\u0007I\u0011\t)\t\rQ\u0003\u0001\u0015!\u0003R\u0011\u0015)\u0006\u0001\"\u0011W\u0005I\u0001\u0016\u0010\u001e5p]N#\u0018\r^3E'R\u0014X-Y7\u000b\u00051i\u0011A\u00029zi\"|gN\u0003\u0002\u000f\u001f\u0005\u0019\u0011\r]5\u000b\u0005A\t\u0012!C:ue\u0016\fW.\u001b8h\u0015\t\u00112#A\u0003ta\u0006\u00148N\u0003\u0002\u0015+\u00051\u0011\r]1dQ\u0016T\u0011AF\u0001\u0004_J<7C\u0001\u0001\u0019!\tI\"$D\u0001\f\u0013\tY2BA\u0007QsRDwN\u001c#TiJ,\u0017-\\\u0001\u0007a\u0006\u0014XM\u001c;\u0004\u0001A\u0019qD\t\u0013\u000e\u0003\u0001R!!I\b\u0002\u000f\u0011\u001cHO]3b[&\u00111\u0005\t\u0002\b\tN#(/Z1n!\r)\u0003FK\u0007\u0002M)\tq%A\u0003tG\u0006d\u0017-\u0003\u0002*M\t)\u0011I\u001d:bsB\u0011QeK\u0005\u0003Y\u0019\u0012AAQ=uK\u0006Q!/\u001a3vG\u00164UO\\2\u0011\u0005ey\u0013B\u0001\u0019\f\u0005]\u0001\u0016\u0010\u001e5p]R\u0013\u0018M\\:g_Jlg)\u001e8di&|g.\u0001\u0006j]&$\u0018.\u00197S\t\u0012\u00032!J\u001a6\u0013\t!dE\u0001\u0004PaRLwN\u001c\t\u0004me\"S\"A\u001c\u000b\u0005a\n\u0012a\u0001:eI&\u0011!h\u000e\u0002\u0004%\u0012#\u0015A\u0002\u001fj]&$h\b\u0006\u0003>}}\u0002\u0005CA\r\u0001\u0011\u0015aB\u00011\u0001\u001f\u0011\u0015iC\u00011\u0001/\u0011\u0015\tD\u00011\u00013)\ri$i\u0011\u0005\u00069\u0015\u0001\rA\b\u0005\u0006[\u0015\u0001\rA\f\u000b\u0005{\u00153u\tC\u0003\u001d\r\u0001\u0007a\u0004C\u0003.\r\u0001\u0007a\u0006C\u00032\r\u0001\u0007\u0001\nE\u0002J\u001b\u0012j\u0011A\u0013\u0006\u0003\u00172\u000bAA[1wC*\u0011a\"E\u0005\u0003\u001d*\u0013qAS1wCJ#E)\u0001\bnkN$8\t[3dWB|\u0017N\u001c;\u0016\u0003E\u0003\"!\n*\n\u0005M3#a\u0002\"p_2,\u0017M\\\u0001\u0010[V\u001cHo\u00115fG.\u0004x.\u001b8uA\u000591m\\7qkR,GC\u0001\u001aX\u0011\u0015A\u0016\u00021\u0001Z\u0003%1\u0018\r\\5e)&lW\r\u0005\u0002[76\tq\"\u0003\u0002]\u001f\t!A+[7f\u0001"
)
public class PythonStateDStream extends PythonDStream {
   private final DStream parent;
   private final Option initialRDD;
   private final boolean mustCheckpoint;

   public boolean mustCheckpoint() {
      return this.mustCheckpoint;
   }

   public Option compute(final Time validTime) {
      Option lastState = this.getOrCompute(validTime.$minus(this.slideDuration()));
      Option rdd = this.parent.getOrCompute(validTime);
      return rdd.isDefined() ? this.func().apply(lastState.orElse(() -> this.initialRDD), rdd, validTime) : lastState;
   }

   public PythonStateDStream(final DStream parent, final PythonTransformFunction reduceFunc, final Option initialRDD) {
      super(parent, reduceFunc);
      this.parent = parent;
      this.initialRDD = initialRDD;
      super.persist(.MODULE$.MEMORY_ONLY());
      this.mustCheckpoint = true;
   }

   public PythonStateDStream(final DStream parent, final PythonTransformFunction reduceFunc) {
      this(parent, reduceFunc, (Option)scala.None..MODULE$);
   }

   public PythonStateDStream(final DStream parent, final PythonTransformFunction reduceFunc, final JavaRDD initialRDD) {
      this(parent, reduceFunc, (Option)(new Some(initialRDD.rdd())));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

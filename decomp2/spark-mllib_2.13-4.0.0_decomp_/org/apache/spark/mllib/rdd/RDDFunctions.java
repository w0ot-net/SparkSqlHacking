package org.apache.spark.mllib.rdd;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.rdd.RDD;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a4Aa\u0003\u0007\u0001/!A1\u0006\u0001B\u0001B\u0003%A\u0006\u0003\u0005=\u0001\t\r\t\u0015a\u0003>\u0011\u0015\u0019\u0005\u0001\"\u0001E\u0011\u0015Q\u0005\u0001\"\u0001L\u0011\u0015Q\u0005\u0001\"\u0001X\u000f\u0015IF\u0002#\u0001[\r\u0015YA\u0002#\u0001\\\u0011\u0015\u0019u\u0001\"\u0001d\u0011\u0015!w\u0001b\u0001f\u0011\u001d\u0001x!!A\u0005\nE\u0014AB\u0015#E\rVt7\r^5p]NT!!\u0004\b\u0002\u0007I$GM\u0003\u0002\u0010!\u0005)Q\u000e\u001c7jE*\u0011\u0011CE\u0001\u0006gB\f'o\u001b\u0006\u0003'Q\ta!\u00199bG\",'\"A\u000b\u0002\u0007=\u0014xm\u0001\u0001\u0016\u0005a\u00194c\u0001\u0001\u001a?A\u0011!$H\u0007\u00027)\tA$A\u0003tG\u0006d\u0017-\u0003\u0002\u001f7\t1\u0011I\\=SK\u001a\u0004\"\u0001\t\u0015\u000f\u0005\u00052cB\u0001\u0012&\u001b\u0005\u0019#B\u0001\u0013\u0017\u0003\u0019a$o\\8u}%\tA$\u0003\u0002(7\u00059\u0001/Y2lC\u001e,\u0017BA\u0015+\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t93$\u0001\u0003tK24\u0007cA\u00170c5\taF\u0003\u0002\u000e!%\u0011\u0001G\f\u0002\u0004%\u0012#\u0005C\u0001\u001a4\u0019\u0001!Q\u0001\u000e\u0001C\u0002U\u0012\u0011\u0001V\t\u0003me\u0002\"AG\u001c\n\u0005aZ\"a\u0002(pi\"Lgn\u001a\t\u00035iJ!aO\u000e\u0003\u0007\u0005s\u00170\u0001\u0006fm&$WM\\2fIE\u00022AP!2\u001b\u0005y$B\u0001!\u001c\u0003\u001d\u0011XM\u001a7fGRL!AQ \u0003\u0011\rc\u0017m]:UC\u001e\fa\u0001P5oSRtDCA#J)\t1\u0005\nE\u0002H\u0001Ej\u0011\u0001\u0004\u0005\u0006y\r\u0001\u001d!\u0010\u0005\u0006W\r\u0001\r\u0001L\u0001\bg2LG-\u001b8h)\ra\u0005+\u0016\t\u0004[=j\u0005c\u0001\u000eOc%\u0011qj\u0007\u0002\u0006\u0003J\u0014\u0018-\u001f\u0005\u0006#\u0012\u0001\rAU\u0001\u000bo&tGm\\<TSj,\u0007C\u0001\u000eT\u0013\t!6DA\u0002J]RDQA\u0016\u0003A\u0002I\u000bAa\u001d;faR\u0011A\n\u0017\u0005\u0006#\u0016\u0001\rAU\u0001\r%\u0012#e)\u001e8di&|gn\u001d\t\u0003\u000f\u001e\u00192aB\r]!\ti&-D\u0001_\u0015\ty\u0006-\u0001\u0002j_*\t\u0011-\u0001\u0003kCZ\f\u0017BA\u0015_)\u0005Q\u0016a\u00024s_6\u0014F\tR\u000b\u0003M*$\"a\u001a8\u0015\u0005!\\\u0007cA$\u0001SB\u0011!G\u001b\u0003\u0006i%\u0011\r!\u000e\u0005\bY&\t\t\u0011q\u0001n\u0003))g/\u001b3f]\u000e,GE\r\t\u0004}\u0005K\u0007\"B\u0007\n\u0001\u0004y\u0007cA\u00170S\u0006aqO]5uKJ+\u0007\u000f\\1dKR\t!\u000f\u0005\u0002tm6\tAO\u0003\u0002vA\u0006!A.\u00198h\u0013\t9HO\u0001\u0004PE*,7\r\u001e"
)
public class RDDFunctions implements Serializable {
   private final RDD self;
   private final ClassTag evidence$1;

   public static RDDFunctions fromRDD(final RDD rdd, final ClassTag evidence$2) {
      return RDDFunctions$.MODULE$.fromRDD(rdd, evidence$2);
   }

   public RDD sliding(final int windowSize, final int step) {
      .MODULE$.require(windowSize > 0, () -> "Sliding window size must be positive, but got " + windowSize + ".");
      return (RDD)(windowSize == 1 && step == 1 ? this.self.map((x$1) -> {
         Object var2 = this.evidence$1.newArray(1);
         scala.runtime.ScalaRunTime..MODULE$.array_update(var2, 0, x$1);
         return var2;
      }, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(this.evidence$1.runtimeClass()))) : new SlidingRDD(this.self, windowSize, step, this.evidence$1));
   }

   public RDD sliding(final int windowSize) {
      return this.sliding(windowSize, 1);
   }

   public RDDFunctions(final RDD self, final ClassTag evidence$1) {
      this.self = self;
      this.evidence$1 = evidence$1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

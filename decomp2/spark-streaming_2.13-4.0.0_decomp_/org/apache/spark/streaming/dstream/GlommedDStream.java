package org.apache.spark.streaming.dstream;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Time;
import scala.Option;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t4Qa\u0002\u0005\u0001\u0015IA\u0001B\u000b\u0001\u0003\u0002\u0003\u0006Ia\u000b\u0005\tY\u0001\u0011\u0019\u0011)A\u0006[!)1\u0007\u0001C\u0001i!)\u0011\b\u0001C!u!)A\n\u0001C!\u001b\")!\u000b\u0001C!'\nqq\t\\8n[\u0016$Gi\u0015;sK\u0006l'BA\u0005\u000b\u0003\u001d!7\u000f\u001e:fC6T!a\u0003\u0007\u0002\u0013M$(/Z1nS:<'BA\u0007\u000f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0001#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002#\u0005\u0019qN]4\u0016\u0005M\u00013C\u0001\u0001\u0015!\r)b\u0003G\u0007\u0002\u0011%\u0011q\u0003\u0003\u0002\b\tN#(/Z1n!\rIBDH\u0007\u00025)\t1$A\u0003tG\u0006d\u0017-\u0003\u0002\u001e5\t)\u0011I\u001d:bsB\u0011q\u0004\t\u0007\u0001\t\u0015\t\u0003A1\u0001$\u0005\u0005!6\u0001A\t\u0003I\u001d\u0002\"!G\u0013\n\u0005\u0019R\"a\u0002(pi\"Lgn\u001a\t\u00033!J!!\u000b\u000e\u0003\u0007\u0005s\u00170\u0001\u0004qCJ,g\u000e\u001e\t\u0004+Yq\u0012AC3wS\u0012,gnY3%cA\u0019a&\r\u0010\u000e\u0003=R!\u0001\r\u000e\u0002\u000fI,g\r\\3di&\u0011!g\f\u0002\t\u00072\f7o\u001d+bO\u00061A(\u001b8jiz\"\"!\u000e\u001d\u0015\u0005Y:\u0004cA\u000b\u0001=!)Af\u0001a\u0002[!)!f\u0001a\u0001W\u0005aA-\u001a9f]\u0012,gnY5fgV\t1\bE\u0002=\t\u001es!!\u0010\"\u000f\u0005y\nU\"A \u000b\u0005\u0001\u0013\u0013A\u0002\u001fs_>$h(C\u0001\u001c\u0013\t\u0019%$A\u0004qC\u000e\\\u0017mZ3\n\u0005\u00153%\u0001\u0002'jgRT!a\u0011\u000e1\u0005!S\u0005cA\u000b\u0017\u0013B\u0011qD\u0013\u0003\n\u0017\u0012\t\t\u0011!A\u0003\u0002\r\u00121a\u0018\u00132\u00035\u0019H.\u001b3f\tV\u0014\u0018\r^5p]V\ta\n\u0005\u0002P!6\t!\"\u0003\u0002R\u0015\tAA)\u001e:bi&|g.A\u0004d_6\u0004X\u000f^3\u0015\u0005Qk\u0006cA\rV/&\u0011aK\u0007\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0007a[\u0006$D\u0001Z\u0015\tQF\"A\u0002sI\u0012L!\u0001X-\u0003\u0007I#E\tC\u0003_\r\u0001\u0007q,A\u0005wC2LG\rV5nKB\u0011q\nY\u0005\u0003C*\u0011A\u0001V5nK\u0002"
)
public class GlommedDStream extends DStream {
   private final DStream parent;

   public List dependencies() {
      return new .colon.colon(this.parent, scala.collection.immutable.Nil..MODULE$);
   }

   public Duration slideDuration() {
      return this.parent.slideDuration();
   }

   public Option compute(final Time validTime) {
      return this.parent.getOrCompute(validTime).map((x$1) -> x$1.glom());
   }

   public GlommedDStream(final DStream parent, final ClassTag evidence$1) {
      super(parent.ssc(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(evidence$1.runtimeClass())));
      this.parent = parent;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

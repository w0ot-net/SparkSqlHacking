package org.apache.spark.streaming.dstream;

import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.rdd.RDD;
import org.apache.spark.rdd.UnionRDD;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.Time;
import scala.Option;
import scala.Some;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Queue;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005m4Q!\u0004\b\u0001!aA\u0001\"\f\u0001\u0003\u0002\u0003\u0006IA\f\u0005\te\u0001\u0011)\u0019!C\u0001g!A!\t\u0001B\u0001B\u0003%A\u0007\u0003\u0005D\u0001\t\u0005\t\u0015!\u0003E\u0011!9\u0005A!A!\u0002\u0013a\u0004\u0002\u0003%\u0001\u0005\u0007\u0005\u000b1B%\t\u000b=\u0003A\u0011\u0001)\t\u000ba\u0003A\u0011I-\t\u000bu\u0003A\u0011I-\t\u000by\u0003A\u0011B0\t\u000b)\u0004A\u0011B6\t\u000bE\u0004A\u0011\t:\u0003#E+X-^3J]B,H\u000fR*ue\u0016\fWN\u0003\u0002\u0010!\u00059Am\u001d;sK\u0006l'BA\t\u0013\u0003%\u0019HO]3b[&twM\u0003\u0002\u0014)\u0005)1\u000f]1sW*\u0011QCF\u0001\u0007CB\f7\r[3\u000b\u0003]\t1a\u001c:h+\tI\u0002e\u0005\u0002\u00015A\u00191\u0004\b\u0010\u000e\u00039I!!\b\b\u0003\u0019%s\u0007/\u001e;E'R\u0014X-Y7\u0011\u0005}\u0001C\u0002\u0001\u0003\u0006C\u0001\u0011\ra\t\u0002\u0002)\u000e\u0001\u0011C\u0001\u0013+!\t)\u0003&D\u0001'\u0015\u00059\u0013!B:dC2\f\u0017BA\u0015'\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!J\u0016\n\u000512#aA!os\u0006\u00191o]2\u0011\u0005=\u0002T\"\u0001\t\n\u0005E\u0002\"\u0001E*ue\u0016\fW.\u001b8h\u0007>tG/\u001a=u\u0003\u0015\tX/Z;f+\u0005!\u0004cA\u001b;y5\taG\u0003\u00028q\u00059Q.\u001e;bE2,'BA\u001d'\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003wY\u0012Q!U;fk\u0016\u00042!\u0010!\u001f\u001b\u0005q$BA \u0013\u0003\r\u0011H\rZ\u0005\u0003\u0003z\u00121A\u0015#E\u0003\u0019\tX/Z;fA\u0005QqN\\3Bi\u0006#\u0016.\\3\u0011\u0005\u0015*\u0015B\u0001$'\u0005\u001d\u0011un\u001c7fC:\f!\u0002Z3gCVdGO\u0015#E\u0003))g/\u001b3f]\u000e,G%\r\t\u0004\u00156sR\"A&\u000b\u000513\u0013a\u0002:fM2,7\r^\u0005\u0003\u001d.\u0013\u0001b\u00117bgN$\u0016mZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000bE#VKV,\u0015\u0005I\u001b\u0006cA\u000e\u0001=!)\u0001j\u0002a\u0002\u0013\")Qf\u0002a\u0001]!)!g\u0002a\u0001i!)1i\u0002a\u0001\t\")qi\u0002a\u0001y\u0005)1\u000f^1siR\t!\f\u0005\u0002&7&\u0011AL\n\u0002\u0005+:LG/\u0001\u0003ti>\u0004\u0018A\u0003:fC\u0012|%M[3diR\u0011!\f\u0019\u0005\u0006C*\u0001\rAY\u0001\u0003S:\u0004\"a\u00195\u000e\u0003\u0011T!!\u001a4\u0002\u0005%|'\"A4\u0002\t)\fg/Y\u0005\u0003S\u0012\u0014\u0011c\u00142kK\u000e$\u0018J\u001c9viN#(/Z1n\u0003-9(/\u001b;f\u001f\nTWm\u0019;\u0015\u0005ic\u0007\"B7\f\u0001\u0004q\u0017aA8pgB\u00111m\\\u0005\u0003a\u0012\u0014!c\u00142kK\u000e$x*\u001e;qkR\u001cFO]3b[\u000691m\\7qkR,GCA:w!\r)C\u000fP\u0005\u0003k\u001a\u0012aa\u00149uS>t\u0007\"B<\r\u0001\u0004A\u0018!\u0003<bY&$G+[7f!\ty\u00130\u0003\u0002{!\t!A+[7f\u0001"
)
public class QueueInputDStream extends InputDStream {
   private final StreamingContext ssc;
   private final Queue queue;
   private final boolean oneAtATime;
   private final RDD defaultRDD;
   private final ClassTag evidence$1;

   public Queue queue() {
      return this.queue;
   }

   public void start() {
   }

   public void stop() {
   }

   private void readObject(final ObjectInputStream in) {
      throw new NotSerializableException("queueStream doesn't support checkpointing. Please don't use queueStream when checkpointing is enabled.");
   }

   private void writeObject(final ObjectOutputStream oos) {
      this.logWarning(() -> "queueStream doesn't support checkpointing");
   }

   public Option compute(final Time validTime) {
      ArrayBuffer buffer = new ArrayBuffer();
      synchronized(this.queue()){}

      try {
         if (this.oneAtATime && this.queue().nonEmpty()) {
            buffer.$plus$eq(this.queue().dequeue());
         } else {
            buffer.$plus$plus$eq(this.queue());
            this.queue().clear();
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } catch (Throwable var5) {
         throw var5;
      }

      if (buffer.nonEmpty()) {
         return this.oneAtATime ? new Some(buffer.head()) : new Some(new UnionRDD(this.context().sc(), buffer.toSeq(), this.evidence$1));
      } else {
         return this.defaultRDD != null ? new Some(this.defaultRDD) : new Some(this.ssc.sparkContext().emptyRDD(this.evidence$1));
      }
   }

   public QueueInputDStream(final StreamingContext ssc, final Queue queue, final boolean oneAtATime, final RDD defaultRDD, final ClassTag evidence$1) {
      super(ssc, evidence$1);
      this.ssc = ssc;
      this.queue = queue;
      this.oneAtATime = oneAtATime;
      this.defaultRDD = defaultRDD;
      this.evidence$1 = evidence$1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

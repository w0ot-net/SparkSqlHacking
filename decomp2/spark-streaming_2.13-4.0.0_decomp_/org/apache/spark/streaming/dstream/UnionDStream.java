package org.apache.spark.streaming.dstream;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkException;
import org.apache.spark.rdd.RDD;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.Time;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Predef.;
import scala.collection.immutable.List;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054Qa\u0002\u0005\u0001\u0015IA\u0001b\n\u0001\u0003\u0002\u0003\u0006I\u0001\u000b\u0005\tW\u0001\u0011\u0019\u0011)A\u0006Y!)!\u0007\u0001C\u0001g!)\u0001\b\u0001C!s!)1\n\u0001C!\u0019\")\u0011\u000b\u0001C!%\naQK\\5p]\u0012\u001bFO]3b[*\u0011\u0011BC\u0001\bIN$(/Z1n\u0015\tYA\"A\u0005tiJ,\u0017-\\5oO*\u0011QBD\u0001\u0006gB\f'o\u001b\u0006\u0003\u001fA\ta!\u00199bG\",'\"A\t\u0002\u0007=\u0014x-\u0006\u0002\u00145M\u0011\u0001\u0001\u0006\t\u0004+YAR\"\u0001\u0005\n\u0005]A!a\u0002#TiJ,\u0017-\u001c\t\u00033ia\u0001\u0001B\u0003\u001c\u0001\t\u0007QDA\u0001U\u0007\u0001\t\"A\b\u0013\u0011\u0005}\u0011S\"\u0001\u0011\u000b\u0003\u0005\nQa]2bY\u0006L!a\t\u0011\u0003\u000f9{G\u000f[5oOB\u0011q$J\u0005\u0003M\u0001\u00121!\u00118z\u0003\u001d\u0001\u0018M]3oiN\u00042aH\u0015\u0015\u0013\tQ\u0003EA\u0003BeJ\f\u00170\u0001\u0006fm&$WM\\2fIE\u00022!\f\u0019\u0019\u001b\u0005q#BA\u0018!\u0003\u001d\u0011XM\u001a7fGRL!!\r\u0018\u0003\u0011\rc\u0017m]:UC\u001e\fa\u0001P5oSRtDC\u0001\u001b8)\t)d\u0007E\u0002\u0016\u0001aAQaK\u0002A\u00041BQaJ\u0002A\u0002!\nA\u0002Z3qK:$WM\\2jKN,\u0012A\u000f\t\u0004w\r3eB\u0001\u001fB\u001d\ti\u0004)D\u0001?\u0015\tyD$\u0001\u0004=e>|GOP\u0005\u0002C%\u0011!\tI\u0001\ba\u0006\u001c7.Y4f\u0013\t!UI\u0001\u0003MSN$(B\u0001\"!a\t9\u0015\nE\u0002\u0016-!\u0003\"!G%\u0005\u0013)#\u0011\u0011!A\u0001\u0006\u0003i\"aA0%c\u0005i1\u000f\\5eK\u0012+(/\u0019;j_:,\u0012!\u0014\t\u0003\u001d>k\u0011AC\u0005\u0003!*\u0011\u0001\u0002R;sCRLwN\\\u0001\bG>l\u0007/\u001e;f)\t\u0019F\fE\u0002 )ZK!!\u0016\u0011\u0003\r=\u0003H/[8o!\r9&\fG\u0007\u00021*\u0011\u0011\fD\u0001\u0004e\u0012$\u0017BA.Y\u0005\r\u0011F\t\u0012\u0005\u0006;\u001a\u0001\rAX\u0001\nm\u0006d\u0017\u000e\u001a+j[\u0016\u0004\"AT0\n\u0005\u0001T!\u0001\u0002+j[\u0016\u0004"
)
public class UnionDStream extends DStream {
   private final DStream[] parents;
   private final ClassTag evidence$1;

   public List dependencies() {
      return .MODULE$.wrapRefArray(this.parents).toList();
   }

   public Duration slideDuration() {
      return ((DStream)scala.collection.ArrayOps..MODULE$.head$extension(.MODULE$.refArrayOps(this.parents))).slideDuration();
   }

   public Option compute(final Time validTime) {
      ArrayBuffer rdds = new ArrayBuffer();
      scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(this.parents), (x$3) -> x$3.getOrCompute(validTime), scala.reflect.ClassTag..MODULE$.apply(Option.class))), (x0$1) -> {
         if (x0$1 instanceof Some var5) {
            RDD rdd = (RDD)var5.value();
            return (ArrayBuffer)rdds.$plus$eq(rdd);
         } else if (scala.None..MODULE$.equals(x0$1)) {
            throw new SparkException("Could not generate RDD from a parent for unifying at time " + validTime);
         } else {
            throw new MatchError(x0$1);
         }
      });
      return (Option)(rdds.nonEmpty() ? new Some(this.ssc().sc().union(rdds.toSeq(), this.evidence$1)) : scala.None..MODULE$);
   }

   public UnionDStream(final DStream[] parents, final ClassTag evidence$1) {
      super(((DStream)scala.collection.ArrayOps..MODULE$.head$extension(.MODULE$.refArrayOps(parents))).ssc(), evidence$1);
      this.parents = parents;
      this.evidence$1 = evidence$1;
      .MODULE$.require(parents.length > 0, () -> "List of DStreams to union is empty");
      .MODULE$.require(((StreamingContext[])scala.collection.ArrayOps..MODULE$.distinct$extension(.MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(parents), (x$1) -> x$1.ssc(), scala.reflect.ClassTag..MODULE$.apply(StreamingContext.class))))).length == 1, () -> "Some of the DStreams have different contexts");
      .MODULE$.require(((Duration[])scala.collection.ArrayOps..MODULE$.distinct$extension(.MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(parents), (x$2) -> x$2.slideDuration(), scala.reflect.ClassTag..MODULE$.apply(Duration.class))))).length == 1, () -> "Some of the DStreams have different slide durations");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

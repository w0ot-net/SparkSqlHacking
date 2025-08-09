package org.apache.spark.streaming.dstream;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.rdd.RDD;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.scheduler.Job;
import scala.Function0;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005m4QAC\u0006\u0001\u001bUA\u0001\"\t\u0001\u0003\u0002\u0003\u0006Ia\t\u0005\t_\u0001\u0011\t\u0011)A\u0005a!AQ\b\u0001B\u0001B\u0003%a\b\u0003\u0005B\u0001\t\r\t\u0015a\u0003C\u0011\u0015A\u0005\u0001\"\u0001J\u0011\u0015\u0001\u0006\u0001\"\u0011R\u0011\u0015\u0019\u0007\u0001\"\u0011e\u0011\u0015A\u0007\u0001\"\u0011j\u0011\u0015\u0001\b\u0001\"\u0011r\u000591uN]#bG\"$5\u000b\u001e:fC6T!\u0001D\u0007\u0002\u000f\u0011\u001cHO]3b[*\u0011abD\u0001\ngR\u0014X-Y7j]\u001eT!\u0001E\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005I\u0019\u0012AB1qC\u000eDWMC\u0001\u0015\u0003\ry'oZ\u000b\u0003-\u0019\u001a\"\u0001A\f\u0011\u0007aI2$D\u0001\f\u0013\tQ2BA\u0004E'R\u0014X-Y7\u0011\u0005qyR\"A\u000f\u000b\u0003y\tQa]2bY\u0006L!\u0001I\u000f\u0003\tUs\u0017\u000e^\u0001\u0007a\u0006\u0014XM\u001c;\u0004\u0001A\u0019\u0001$\u0007\u0013\u0011\u0005\u00152C\u0002\u0001\u0003\u0006O\u0001\u0011\r\u0001\u000b\u0002\u0002)F\u0011\u0011\u0006\f\t\u00039)J!aK\u000f\u0003\u000f9{G\u000f[5oOB\u0011A$L\u0005\u0003]u\u00111!\u00118z\u0003-1wN]3bG\"4UO\\2\u0011\u000bq\t4'O\u000e\n\u0005Ij\"!\u0003$v]\u000e$\u0018n\u001c83!\r!t\u0007J\u0007\u0002k)\u0011agD\u0001\u0004e\u0012$\u0017B\u0001\u001d6\u0005\r\u0011F\t\u0012\t\u0003umj\u0011!D\u0005\u0003y5\u0011A\u0001V5nK\u0006\u0011B-[:qY\u0006L\u0018J\u001c8feJ#Ei\u00149t!\tar(\u0003\u0002A;\t9!i\\8mK\u0006t\u0017AC3wS\u0012,gnY3%cA\u00191I\u0012\u0013\u000e\u0003\u0011S!!R\u000f\u0002\u000fI,g\r\\3di&\u0011q\t\u0012\u0002\t\u00072\f7o\u001d+bO\u00061A(\u001b8jiz\"BAS'O\u001fR\u00111\n\u0014\t\u00041\u0001!\u0003\"B!\u0006\u0001\b\u0011\u0005\"B\u0011\u0006\u0001\u0004\u0019\u0003\"B\u0018\u0006\u0001\u0004\u0001\u0004\"B\u001f\u0006\u0001\u0004q\u0014\u0001\u00043fa\u0016tG-\u001a8dS\u0016\u001cX#\u0001*\u0011\u0007M[fL\u0004\u0002U3:\u0011Q\u000bW\u0007\u0002-*\u0011qKI\u0001\u0007yI|w\u000e\u001e \n\u0003yI!AW\u000f\u0002\u000fA\f7m[1hK&\u0011A,\u0018\u0002\u0005\u0019&\u001cHO\u0003\u0002[;A\u0012q,\u0019\t\u00041e\u0001\u0007CA\u0013b\t%\u0011g!!A\u0001\u0002\u000b\u0005\u0001FA\u0002`IE\nQb\u001d7jI\u0016$UO]1uS>tW#A3\u0011\u0005i2\u0017BA4\u000e\u0005!!UO]1uS>t\u0017aB2p[B,H/\u001a\u000b\u0003U:\u00042\u0001H6n\u0013\taWD\u0001\u0004PaRLwN\u001c\t\u0004i]Z\u0002\"B8\t\u0001\u0004I\u0014!\u0003<bY&$G+[7f\u0003-9WM\\3sCR,'j\u001c2\u0015\u0005IL\bc\u0001\u000flgB\u0011Ao^\u0007\u0002k*\u0011a/D\u0001\ng\u000eDW\rZ;mKJL!\u0001_;\u0003\u0007){'\rC\u0003{\u0013\u0001\u0007\u0011(\u0001\u0003uS6,\u0007"
)
public class ForEachDStream extends DStream {
   private final DStream parent;
   private final Function2 foreachFunc;
   private final boolean displayInnerRDDOps;

   public List dependencies() {
      return new .colon.colon(this.parent, scala.collection.immutable.Nil..MODULE$);
   }

   public Duration slideDuration() {
      return this.parent.slideDuration();
   }

   public Option compute(final Time validTime) {
      return scala.None..MODULE$;
   }

   public Option generateJob(final Time time) {
      Option var3 = this.parent.getOrCompute(time);
      if (var3 instanceof Some var4) {
         RDD rdd = (RDD)var4.value();
         Function0 jobFunc = () -> this.createRDDWithLocalProperties(time, this.displayInnerRDDOps, (JFunction0.mcV.sp)() -> this.foreachFunc.apply(rdd, time));
         return new Some(new Job(time, jobFunc));
      } else if (scala.None..MODULE$.equals(var3)) {
         return scala.None..MODULE$;
      } else {
         throw new MatchError(var3);
      }
   }

   public ForEachDStream(final DStream parent, final Function2 foreachFunc, final boolean displayInnerRDDOps, final ClassTag evidence$1) {
      super(parent.ssc(), scala.reflect.ClassTag..MODULE$.Unit());
      this.parent = parent;
      this.foreachFunc = foreachFunc;
      this.displayInnerRDDOps = displayInnerRDDOps;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

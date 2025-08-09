package org.apache.spark.streaming.api.python;

import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Interval;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.dstream.DStream;
import scala.Option;
import scala.Some;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u000514Q\u0001E\t\u0001#uA\u0001B\t\u0001\u0003\u0002\u0003\u0006I\u0001\n\u0005\tg\u0001\u0011\t\u0011)A\u0005i!Aq\u0007\u0001BC\u0002\u0013%\u0001\b\u0003\u0005:\u0001\t\u0005\t\u0015!\u00035\u0011!q\u0004A!A!\u0002\u0013y\u0004\u0002C\"\u0001\u0005\u0003\u0005\u000b\u0011B \t\u000b\u0011\u0003A\u0011A#\t\u000f1\u0003!\u0019!C!\u001b\"1\u0011\u000b\u0001Q\u0001\n9CqA\u0015\u0001C\u0002\u0013\u00051\u000b\u0003\u0004X\u0001\u0001\u0006I\u0001\u0016\u0005\u00061\u0002!\t!\u0017\u0005\u00065\u0002!\t%\u0017\u0005\u00067\u0002!\t%\u0017\u0005\u00069\u0002!\t%\u0018\u0002\u001d!f$\bn\u001c8SK\u0012,8-\u001a3XS:$wn^3e\tN#(/Z1n\u0015\t\u00112#\u0001\u0004qsRDwN\u001c\u0006\u0003)U\t1!\u00199j\u0015\t1r#A\u0005tiJ,\u0017-\\5oO*\u0011\u0001$G\u0001\u0006gB\f'o\u001b\u0006\u00035m\ta!\u00199bG\",'\"\u0001\u000f\u0002\u0007=\u0014xm\u0005\u0002\u0001=A\u0011q\u0004I\u0007\u0002#%\u0011\u0011%\u0005\u0002\u000e!f$\bn\u001c8E'R\u0014X-Y7\u0002\rA\f'/\u001a8u\u0007\u0001\u00012!\n\u0015+\u001b\u00051#BA\u0014\u0016\u0003\u001d!7\u000f\u001e:fC6L!!\u000b\u0014\u0003\u000f\u0011\u001bFO]3b[B\u00191F\f\u0019\u000e\u00031R\u0011!L\u0001\u0006g\u000e\fG.Y\u0005\u0003_1\u0012Q!\u0011:sCf\u0004\"aK\u0019\n\u0005Ib#\u0001\u0002\"zi\u0016\f1\u0002\u001d:fIV\u001cWMR;oGB\u0011q$N\u0005\u0003mE\u0011q\u0003U=uQ>tGK]1og\u001a|'/\u001c$v]\u000e$\u0018n\u001c8\u0002\u001dALgN\u001e*fIV\u001cWMR;oGV\tA'A\bqS:4(+\u001a3vG\u00164UO\\2!Q\t!1\b\u0005\u0002,y%\u0011Q\b\f\u0002\niJ\fgn]5f]R\fqbX<j]\u0012|w\u000fR;sCRLwN\u001c\t\u0003\u0001\u0006k\u0011!F\u0005\u0003\u0005V\u0011\u0001\u0002R;sCRLwN\\\u0001\u000f?Nd\u0017\u000eZ3EkJ\fG/[8o\u0003\u0019a\u0014N\\5u}Q1ai\u0012%J\u0015.\u0003\"a\b\u0001\t\u000b\t:\u0001\u0019\u0001\u0013\t\u000bM:\u0001\u0019\u0001\u001b\t\u000b]:\u0001\u0019\u0001\u001b\t\u000by:\u0001\u0019A \t\u000b\r;\u0001\u0019A \u0002\u001d5,8\u000f^\"iK\u000e\\\u0007o\\5oiV\ta\n\u0005\u0002,\u001f&\u0011\u0001\u000b\f\u0002\b\u0005>|G.Z1o\u0003=iWo\u001d;DQ\u0016\u001c7\u000e]8j]R\u0004\u0013!D5omJ+G-^2f\rVt7-F\u0001U!\tyR+\u0003\u0002W#\t\tBK]1og\u001a|'/\u001c$v]\u000e$\u0018n\u001c8\u0002\u001d%tgOU3ek\u000e,g)\u001e8dA\u0005qq/\u001b8e_^$UO]1uS>tW#A \u0002\u001bMd\u0017\u000eZ3EkJ\fG/[8o\u0003Y\u0001\u0018M]3oiJ+W.Z7cKJ$UO]1uS>t\u0017aB2p[B,H/\u001a\u000b\u0003=\u001e\u00042aK0b\u0013\t\u0001GF\u0001\u0004PaRLwN\u001c\t\u0004E\u0016TS\"A2\u000b\u0005\u0011<\u0012a\u0001:eI&\u0011am\u0019\u0002\u0004%\u0012#\u0005\"\u00025\u0010\u0001\u0004I\u0017!\u0003<bY&$G+[7f!\t\u0001%.\u0003\u0002l+\t!A+[7f\u0001"
)
public class PythonReducedWindowedDStream extends PythonDStream {
   private final DStream parent;
   private final transient PythonTransformFunction pinvReduceFunc;
   private final Duration _windowDuration;
   private final Duration _slideDuration;
   private final boolean mustCheckpoint;
   private final TransformFunction invReduceFunc;

   private PythonTransformFunction pinvReduceFunc() {
      return this.pinvReduceFunc;
   }

   public boolean mustCheckpoint() {
      return this.mustCheckpoint;
   }

   public TransformFunction invReduceFunc() {
      return this.invReduceFunc;
   }

   public Duration windowDuration() {
      return this._windowDuration;
   }

   public Duration slideDuration() {
      return this._slideDuration;
   }

   public Duration parentRememberDuration() {
      return this.rememberDuration().$plus(this.windowDuration());
   }

   public Option compute(final Time validTime) {
      Interval current = new Interval(validTime.$minus(this.windowDuration()), validTime);
      Interval previous = current.$minus(this.slideDuration());
      Option previousRDD = this.getOrCompute(previous.endTime());
      if (this.pinvReduceFunc() != null && previousRDD.isDefined() && this.windowDuration().$greater$eq(this.slideDuration().$times(5))) {
         Seq oldRDDs = this.parent.slice(previous.beginTime().$plus(this.parent.slideDuration()), current.beginTime());
         Option subtracted = oldRDDs.size() > 0 ? this.invReduceFunc().apply(previousRDD, new Some(this.ssc().sc().union(oldRDDs, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)))), validTime) : previousRDD;
         Seq newRDDs = this.parent.slice(previous.endTime().$plus(this.parent.slideDuration()), current.endTime());
         return newRDDs.size() > 0 ? this.func().apply(subtracted, new Some(this.ssc().sc().union(newRDDs, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)))), validTime) : subtracted;
      } else {
         Seq currentRDDs = this.parent.slice(current.beginTime().$plus(this.parent.slideDuration()), current.endTime());
         return (Option)(currentRDDs.size() > 0 ? this.func().apply(scala.None..MODULE$, new Some(this.ssc().sc().union(currentRDDs, .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)))), validTime) : scala.None..MODULE$);
      }
   }

   public PythonReducedWindowedDStream(final DStream parent, final PythonTransformFunction preduceFunc, final PythonTransformFunction pinvReduceFunc, final Duration _windowDuration, final Duration _slideDuration) {
      super(parent, preduceFunc);
      this.parent = parent;
      this.pinvReduceFunc = pinvReduceFunc;
      this._windowDuration = _windowDuration;
      this._slideDuration = _slideDuration;
      super.persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_ONLY());
      this.mustCheckpoint = true;
      this.invReduceFunc = new TransformFunction(pinvReduceFunc);
   }
}

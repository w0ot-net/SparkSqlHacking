package org.apache.spark.streaming.dstream;

import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Interval;
import org.apache.spark.streaming.Time;
import scala.Option;
import scala.Some;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000594Q\u0001D\u0007\u0001\u001f]A\u0001\u0002\f\u0001\u0003\u0002\u0003\u0006I!\u0007\u0005\t[\u0001\u0011\t\u0011)A\u0005]!A!\u0007\u0001B\u0001B\u0003%a\u0006\u0003\u00054\u0001\t\r\t\u0015a\u00035\u0011\u0015Q\u0004\u0001\"\u0001<\u0011\u0015\u0011\u0005\u0001\"\u0001D\u0011\u0015!\u0005\u0001\"\u0011F\u0011\u0015\u0011\u0006\u0001\"\u0011D\u0011\u0015\u0019\u0006\u0001\"\u0011D\u0011\u0015!\u0006\u0001\"\u0011V\u0011\u0015q\u0006\u0001\"\u0011`\u0005=9\u0016N\u001c3po\u0016$Gi\u0015;sK\u0006l'B\u0001\b\u0010\u0003\u001d!7\u000f\u001e:fC6T!\u0001E\t\u0002\u0013M$(/Z1nS:<'B\u0001\n\u0014\u0003\u0015\u0019\b/\u0019:l\u0015\t!R#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002-\u0005\u0019qN]4\u0016\u0005ay2C\u0001\u0001\u001a!\rQ2$H\u0007\u0002\u001b%\u0011A$\u0004\u0002\b\tN#(/Z1n!\tqr\u0004\u0004\u0001\u0005\u000b\u0001\u0002!\u0019\u0001\u0012\u0003\u0003Q\u001b\u0001!\u0005\u0002$SA\u0011AeJ\u0007\u0002K)\ta%A\u0003tG\u0006d\u0017-\u0003\u0002)K\t9aj\u001c;iS:<\u0007C\u0001\u0013+\u0013\tYSEA\u0002B]f\fa\u0001]1sK:$\u0018aD0xS:$wn\u001e#ve\u0006$\u0018n\u001c8\u0011\u0005=\u0002T\"A\b\n\u0005Ez!\u0001\u0003#ve\u0006$\u0018n\u001c8\u0002\u001d}\u001bH.\u001b3f\tV\u0014\u0018\r^5p]\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\u0007UBT$D\u00017\u0015\t9T%A\u0004sK\u001adWm\u0019;\n\u0005e2$\u0001C\"mCN\u001cH+Y4\u0002\rqJg.\u001b;?)\u0011at\bQ!\u0015\u0005ur\u0004c\u0001\u000e\u0001;!)1'\u0002a\u0002i!)A&\u0002a\u00013!)Q&\u0002a\u0001]!)!'\u0002a\u0001]\u0005qq/\u001b8e_^$UO]1uS>tW#\u0001\u0018\u0002\u0019\u0011,\u0007/\u001a8eK:\u001c\u0017.Z:\u0016\u0003\u0019\u00032aR(\u001a\u001d\tAUJ\u0004\u0002J\u00196\t!J\u0003\u0002LC\u00051AH]8pizJ\u0011AJ\u0005\u0003\u001d\u0016\nq\u0001]1dW\u0006<W-\u0003\u0002Q#\n!A*[:u\u0015\tqU%A\u0007tY&$W\rR;sCRLwN\\\u0001\u0017a\u0006\u0014XM\u001c;SK6,WNY3s\tV\u0014\u0018\r^5p]\u00069\u0001/\u001a:tSN$HCA\rW\u0011\u00159&\u00021\u0001Y\u0003\u0015aWM^3m!\tIF,D\u0001[\u0015\tY\u0016#A\u0004ti>\u0014\u0018mZ3\n\u0005uS&\u0001D*u_J\fw-\u001a'fm\u0016d\u0017aB2p[B,H/\u001a\u000b\u0003A&\u00042\u0001J1d\u0013\t\u0011WE\u0001\u0004PaRLwN\u001c\t\u0004I\u001elR\"A3\u000b\u0005\u0019\f\u0012a\u0001:eI&\u0011\u0001.\u001a\u0002\u0004%\u0012#\u0005\"\u00026\f\u0001\u0004Y\u0017!\u0003<bY&$G+[7f!\tyC.\u0003\u0002n\u001f\t!A+[7f\u0001"
)
public class WindowedDStream extends DStream {
   private final DStream parent;
   private final Duration _windowDuration;
   private final Duration _slideDuration;
   private final ClassTag evidence$1;

   public Duration windowDuration() {
      return this._windowDuration;
   }

   public List dependencies() {
      return new .colon.colon(this.parent, scala.collection.immutable.Nil..MODULE$);
   }

   public Duration slideDuration() {
      return this._slideDuration;
   }

   public Duration parentRememberDuration() {
      return this.rememberDuration().$plus(this.windowDuration());
   }

   public DStream persist(final StorageLevel level) {
      this.parent.persist(level);
      return this;
   }

   public Option compute(final Time validTime) {
      Interval currentWindow = new Interval(validTime.$minus(this.windowDuration()).$plus(this.parent.slideDuration()), validTime);
      Seq rddsInWindow = this.parent.slice(currentWindow);
      return new Some(this.ssc().sc().union(rddsInWindow, this.evidence$1));
   }

   public WindowedDStream(final DStream parent, final Duration _windowDuration, final Duration _slideDuration, final ClassTag evidence$1) {
      super(parent.ssc(), evidence$1);
      this.parent = parent;
      this._windowDuration = _windowDuration;
      this._slideDuration = _slideDuration;
      this.evidence$1 = evidence$1;
      if (!_windowDuration.isMultipleOf(parent.slideDuration())) {
         throw new Exception("The window duration of windowed DStream (" + _windowDuration + ") must be a multiple of the slide duration of parent DStream (" + parent.slideDuration() + ")");
      } else if (!_slideDuration.isMultipleOf(parent.slideDuration())) {
         throw new Exception("The slide duration of windowed DStream (" + _slideDuration + ") must be a multiple of the slide duration of parent DStream (" + parent.slideDuration() + ")");
      } else {
         parent.persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_ONLY_SER());
      }
   }
}

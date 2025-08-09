package org.apache.spark.streaming;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.Experimental;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;

@Experimental
@ScalaSignature(
   bytes = "\u0006\u0005q3Q!\u0003\u0006\u0002\"MAQa\u0007\u0001\u0005\u0002qAQA\u000b\u0001\u0007\u0002-BQa\f\u0001\u0007\u0002ABQ!\r\u0001\u0007\u0002IBQ\u0001\u000f\u0001\u0007\u0002eBQA\u000f\u0001\u0007\u0002-BQa\u000f\u0001\u0005\u0006qBQ\u0001\u0012\u0001\u0005F\u0015\u0013Qa\u0015;bi\u0016T!a\u0003\u0007\u0002\u0013M$(/Z1nS:<'BA\u0007\u000f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0001#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002#\u0005\u0019qN]4\u0004\u0001U\u0011A#I\n\u0003\u0001U\u0001\"AF\r\u000e\u0003]Q\u0011\u0001G\u0001\u0006g\u000e\fG.Y\u0005\u00035]\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001\u001e!\rq\u0002aH\u0007\u0002\u0015A\u0011\u0001%\t\u0007\u0001\t\u0015\u0011\u0003A1\u0001$\u0005\u0005\u0019\u0016C\u0001\u0013(!\t1R%\u0003\u0002'/\t9aj\u001c;iS:<\u0007C\u0001\f)\u0013\tIsCA\u0002B]f\fa!\u001a=jgR\u001cH#\u0001\u0017\u0011\u0005Yi\u0013B\u0001\u0018\u0018\u0005\u001d\u0011un\u001c7fC:\f1aZ3u)\u0005y\u0012AB;qI\u0006$X\r\u0006\u00024mA\u0011a\u0003N\u0005\u0003k]\u0011A!\u00168ji\")q\u0007\u0002a\u0001?\u0005Aa.Z<Ti\u0006$X-\u0001\u0004sK6|g/\u001a\u000b\u0002g\u0005Y\u0011n\u001d+j[&twmT;u\u0003%9W\r^(qi&|g\u000eF\u0001>!\r1bhH\u0005\u0003\u007f]\u0011aa\u00149uS>t\u0007FA\u0004B!\t1\")\u0003\u0002D/\t1\u0011N\u001c7j]\u0016\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002\rB\u0011qI\u0014\b\u0003\u00112\u0003\"!S\f\u000e\u0003)S!a\u0013\n\u0002\rq\u0012xn\u001c;?\u0013\tiu#\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u001fB\u0013aa\u0015;sS:<'BA'\u0018Q\tA\u0011)\u000b\u0002\u0001'&\u0011AK\u0003\u0002\n'R\fG/Z%na2D#\u0001\u0001,\u0011\u0005]SV\"\u0001-\u000b\u0005ec\u0011AC1o]>$\u0018\r^5p]&\u00111\f\u0017\u0002\r\u000bb\u0004XM]5nK:$\u0018\r\u001c"
)
public abstract class State {
   public abstract boolean exists();

   public abstract Object get();

   public abstract void update(final Object newState);

   public abstract void remove();

   public abstract boolean isTimingOut();

   public final Option getOption() {
      return (Option)(this.exists() ? new Some(this.get()) : .MODULE$);
   }

   public final String toString() {
      return (String)this.getOption().map((x$1) -> x$1.toString()).getOrElse(() -> "<state not set>");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

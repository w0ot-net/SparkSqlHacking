package org.apache.spark.streaming.dstream;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Time;
import scala.Function1;
import scala.Option;
import scala.Tuple2;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Null;

@ScalaSignature(
   bytes = "\u0006\u0005i4QAC\u0006\u0001\u001bUA\u0001\u0002\r\u0001\u0003\u0002\u0003\u0006I!\r\u0005\tm\u0001\u0011\t\u0011)A\u0005o!Aa\t\u0001B\u0002B\u0003-q\t\u0003\u0005N\u0001\t\r\t\u0015a\u0003O\u0011!y\u0005AaA!\u0002\u0017\u0001\u0006\"B)\u0001\t\u0003\u0011\u0006\"\u0002.\u0001\t\u0003Z\u0006\"\u00023\u0001\t\u0003*\u0007\"\u00026\u0001\t\u0003Z'\u0001\u0006$mCRl\u0015\r\u001d,bYV,G\rR*ue\u0016\fWN\u0003\u0002\r\u001b\u00059Am\u001d;sK\u0006l'B\u0001\b\u0010\u0003%\u0019HO]3b[&twM\u0003\u0002\u0011#\u0005)1\u000f]1sW*\u0011!cE\u0001\u0007CB\f7\r[3\u000b\u0003Q\t1a\u001c:h+\u001112\u0005\u000e\u0018\u0014\u0005\u00019\u0002c\u0001\r\u001a75\t1\"\u0003\u0002\u001b\u0017\t9Ai\u0015;sK\u0006l\u0007\u0003\u0002\u000f C5j\u0011!\b\u0006\u0002=\u0005)1oY1mC&\u0011\u0001%\b\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0005\t\u001aC\u0002\u0001\u0003\u0006I\u0001\u0011\rA\n\u0002\u0002\u0017\u000e\u0001\u0011CA\u0014+!\ta\u0002&\u0003\u0002*;\t9aj\u001c;iS:<\u0007C\u0001\u000f,\u0013\taSDA\u0002B]f\u0004\"A\t\u0018\u0005\u000b=\u0002!\u0019\u0001\u0014\u0003\u0003U\u000ba\u0001]1sK:$\bc\u0001\r\u001aeA!AdH\u00114!\t\u0011C\u0007B\u00036\u0001\t\u0007aEA\u0001W\u0003A1G.\u0019;NCB4\u0016\r\\;f\rVt7\r\u0005\u0003\u001dqMR\u0014BA\u001d\u001e\u0005%1UO\\2uS>t\u0017\u0007E\u0002<\u00076r!\u0001P!\u000f\u0005u\u0002U\"\u0001 \u000b\u0005}*\u0013A\u0002\u001fs_>$h(C\u0001\u001f\u0013\t\u0011U$A\u0004qC\u000e\\\u0017mZ3\n\u0005\u0011+%\u0001D%uKJ\f'\r\\3P]\u000e,'B\u0001\"\u001e\u0003))g/\u001b3f]\u000e,G%\r\t\u0004\u0011.\u000bS\"A%\u000b\u0005)k\u0012a\u0002:fM2,7\r^\u0005\u0003\u0019&\u0013\u0001b\u00117bgN$\u0016mZ\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004c\u0001%Lg\u0005QQM^5eK:\u001cW\rJ\u001a\u0011\u0007![U&\u0001\u0004=S:LGO\u0010\u000b\u0004'bKF\u0003\u0002+V-^\u0003R\u0001\u0007\u0001\"g5BQA\u0012\u0004A\u0004\u001dCQ!\u0014\u0004A\u00049CQa\u0014\u0004A\u0004ACQ\u0001\r\u0004A\u0002EBQA\u000e\u0004A\u0002]\nA\u0002Z3qK:$WM\\2jKN,\u0012\u0001\u0018\t\u0004wu{\u0016B\u00010F\u0005\u0011a\u0015n\u001d;1\u0005\u0001\u0014\u0007c\u0001\r\u001aCB\u0011!E\u0019\u0003\nG\u001e\t\t\u0011!A\u0003\u0002\u0019\u00121a\u0018\u00132\u00035\u0019H.\u001b3f\tV\u0014\u0018\r^5p]V\ta\r\u0005\u0002hQ6\tQ\"\u0003\u0002j\u001b\tAA)\u001e:bi&|g.A\u0004d_6\u0004X\u000f^3\u0015\u00051,\bc\u0001\u000fn_&\u0011a.\b\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0007A\u001c8$D\u0001r\u0015\t\u0011x\"A\u0002sI\u0012L!\u0001^9\u0003\u0007I#E\tC\u0003w\u0013\u0001\u0007q/A\u0005wC2LG\rV5nKB\u0011q\r_\u0005\u0003s6\u0011A\u0001V5nK\u0002"
)
public class FlatMapValuedDStream extends DStream {
   private final DStream parent;
   private final Function1 flatMapValueFunc;
   private final ClassTag evidence$1;
   private final ClassTag evidence$2;

   public List dependencies() {
      return new .colon.colon(this.parent, scala.collection.immutable.Nil..MODULE$);
   }

   public Duration slideDuration() {
      return this.parent.slideDuration();
   }

   public Option compute(final Time validTime) {
      return this.parent.getOrCompute(validTime).map((x$1) -> {
         ClassTag x$2 = this.evidence$1;
         ClassTag x$3 = this.evidence$2;
         Null x$4 = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions$default$4(x$1);
         return org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(x$1, x$2, x$3, (Ordering)null).flatMapValues(this.flatMapValueFunc);
      });
   }

   public FlatMapValuedDStream(final DStream parent, final Function1 flatMapValueFunc, final ClassTag evidence$1, final ClassTag evidence$2, final ClassTag evidence$3) {
      super(parent.ssc(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      this.parent = parent;
      this.flatMapValueFunc = flatMapValueFunc;
      this.evidence$1 = evidence$1;
      this.evidence$2 = evidence$2;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

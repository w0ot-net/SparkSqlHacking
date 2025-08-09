package org.apache.spark.util;

import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.collection.immutable.Map.;
import scala.collection.mutable.Builder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005)3\u0001BB\u0004\u0011\u0002\u0007\u0005\u0011b\u0004\u0005\u0006-\u0001!\t\u0001\u0007\u0005\u00069\u0001!\t!H\u0004\u0007\u0007\u001eA\t!\u0003#\u0007\r\u00199\u0001\u0012A\u0005G\u0011\u0015AE\u0001\"\u0001J\u0005Q\u0019\u0006/\u0019:l\u0007>dG.Z2uS>tW\u000b^5mg*\u0011\u0001\"C\u0001\u0005kRLGN\u0003\u0002\u000b\u0017\u0005)1\u000f]1sW*\u0011A\"D\u0001\u0007CB\f7\r[3\u000b\u00039\t1a\u001c:h'\t\u0001\u0001\u0003\u0005\u0002\u0012)5\t!CC\u0001\u0014\u0003\u0015\u00198-\u00197b\u0013\t)\"C\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\t\u0011\u0004\u0005\u0002\u00125%\u00111D\u0005\u0002\u0005+:LG/\u0001\bu_6\u000b\u0007oV5uQ&sG-\u001a=\u0016\u0005yaCCA\u00109!\u0011\u0001sEK\u001b\u000f\u0005\u0005*\u0003C\u0001\u0012\u0013\u001b\u0005\u0019#B\u0001\u0013\u0018\u0003\u0019a$o\\8u}%\u0011aEE\u0001\u0007!J,G-\u001a4\n\u0005!J#aA'ba*\u0011aE\u0005\t\u0003W1b\u0001\u0001B\u0003.\u0005\t\u0007aFA\u0001L#\ty#\u0007\u0005\u0002\u0012a%\u0011\u0011G\u0005\u0002\b\u001d>$\b.\u001b8h!\t\t2'\u0003\u00025%\t\u0019\u0011I\\=\u0011\u0005E1\u0014BA\u001c\u0013\u0005\rIe\u000e\u001e\u0005\u0006s\t\u0001\rAO\u0001\u0005W\u0016L8\u000fE\u0002<\u0001*r!\u0001\u0010 \u000f\u0005\tj\u0014\"A\n\n\u0005}\u0012\u0012a\u00029bG.\fw-Z\u0005\u0003\u0003\n\u0013\u0001\"\u0013;fe\u0006\u0014G.\u001a\u0006\u0003\u007fI\tAc\u00159be.\u001cu\u000e\u001c7fGRLwN\\+uS2\u001c\bCA#\u0005\u001b\u000591c\u0001\u0003\u0011\u000fB\u0011Q\tA\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0011\u0003"
)
public interface SparkCollectionUtils {
   // $FF: synthetic method
   static Map toMapWithIndex$(final SparkCollectionUtils $this, final Iterable keys) {
      return $this.toMapWithIndex(keys);
   }

   default Map toMapWithIndex(final Iterable keys) {
      Builder builder = .MODULE$.newBuilder();
      Iterator keyIter = keys.iterator();

      for(int idx = 0; keyIter.hasNext(); ++idx) {
         builder.$plus$eq(new Tuple2(keyIter.next(), BoxesRunTime.boxToInteger(idx)));
      }

      return (Map)builder.result();
   }

   static void $init$(final SparkCollectionUtils $this) {
   }
}

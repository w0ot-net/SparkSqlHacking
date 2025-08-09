package org.apache.spark.util.random;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Random;
import org.apache.spark.annotation.DeveloperApi;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005-aa\u0002\t\u0012!\u0003\r\t\u0001\b\u0005\u0006o\u0001!\t\u0001\u000f\u0005\u0006y\u0001!\t!\u0010\u0005\u0006y\u00011\tA\u0015\u0005\u0006-\u0002!\teV\u0004\u0007AFA\t!F1\u0007\rA\t\u0002\u0012A\u000bc\u0011\u0015Qg\u0001\"\u0001l\u0011\u0015ag\u0001\"\u0001n\u0011\u001d\u0019hA1A\u0005\u0002QDa\u0001\u001f\u0004!\u0002\u0013)\bbB=\u0007\u0005\u0004%\t\u0001\u001e\u0005\u0007u\u001a\u0001\u000b\u0011B;\t\u000fm4!\u0019!C\u0001i\"1AP\u0002Q\u0001\nUDq! \u0004\u0002\u0002\u0013%aPA\u0007SC:$w.\\*b[BdWM\u001d\u0006\u0003%M\taA]1oI>l'B\u0001\u000b\u0016\u0003\u0011)H/\u001b7\u000b\u0005Y9\u0012!B:qCJ\\'B\u0001\r\u001a\u0003\u0019\t\u0007/Y2iK*\t!$A\u0002pe\u001e\u001c\u0001!F\u0002\u001e!\u000e\u001bR\u0001\u0001\u0010%QQ\u0002\"a\b\u0012\u000e\u0003\u0001R\u0011!I\u0001\u0006g\u000e\fG.Y\u0005\u0003G\u0001\u0012a!\u00118z%\u00164\u0007CA\u0013'\u001b\u0005\t\u0012BA\u0014\u0012\u00051\u00016/Z;e_J\fg\u000eZ8n!\tI\u0013G\u0004\u0002+_9\u00111FL\u0007\u0002Y)\u0011QfG\u0001\u0007yI|w\u000e\u001e \n\u0003\u0005J!\u0001\r\u0011\u0002\u000fA\f7m[1hK&\u0011!g\r\u0002\n\u00072|g.Z1cY\u0016T!\u0001\r\u0011\u0011\u0005%*\u0014B\u0001\u001c4\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u0019!\u0013N\\5uIQ\t\u0011\b\u0005\u0002 u%\u00111\b\t\u0002\u0005+:LG/\u0001\u0004tC6\u0004H.\u001a\u000b\u0003}1\u00032!K B\u0013\t\u00015G\u0001\u0005Ji\u0016\u0014\u0018\r^8s!\t\u00115\t\u0004\u0001\u0005\u000b\u0011\u0003!\u0019A#\u0003\u0003U\u000b\"AR%\u0011\u0005}9\u0015B\u0001%!\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"a\b&\n\u0005-\u0003#aA!os\")QJ\u0001a\u0001\u001d\u0006)\u0011\u000e^3ngB\u0019\u0011fP(\u0011\u0005\t\u0003F!B)\u0001\u0005\u0004)%!\u0001+\u0015\u0003M\u0003\"a\b+\n\u0005U\u0003#aA%oi\u0006)1\r\\8oKR\t\u0001\f\u0005\u0003&\u0001=\u000b\u0005F\u0001\u0001[!\tYf,D\u0001]\u0015\tiV#\u0001\u0006b]:|G/\u0019;j_:L!a\u0018/\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5\u0002\u001bI\u000bg\u000eZ8n'\u0006l\u0007\u000f\\3s!\t)caE\u0002\u0007=\r\u0004\"\u0001Z5\u000e\u0003\u0015T!AZ4\u0002\u0005%|'\"\u00015\u0002\t)\fg/Y\u0005\u0003m\u0015\fa\u0001P5oSRtD#A1\u0002\u001b9,w\u000fR3gCVdGO\u0015(H+\u0005q\u0007CA8r\u001b\u0005\u0001(B\u0001\u000bh\u0013\t\u0011\bO\u0001\u0004SC:$w.\\\u0001\u001eI\u00164\u0017-\u001e7u\u001b\u0006Dx)\u00199TC6\u0004H.\u001b8h\rJ\f7\r^5p]V\tQ\u000f\u0005\u0002 m&\u0011q\u000f\t\u0002\u0007\t>,(\r\\3\u0002=\u0011,g-Y;mi6\u000b\u0007pR1q'\u0006l\u0007\u000f\\5oO\u001a\u0013\u0018m\u0019;j_:\u0004\u0013A\u0003:oO\u0016\u00038/\u001b7p]\u0006Y!O\\4FaNLGn\u001c8!\u0003=\u0011x.\u001e8eS:<W\t]:jY>t\u0017\u0001\u0005:pk:$\u0017N\\4FaNLGn\u001c8!\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005y\b\u0003BA\u0001\u0003\u000fi!!a\u0001\u000b\u0007\u0005\u0015q-\u0001\u0003mC:<\u0017\u0002BA\u0005\u0003\u0007\u0011aa\u00142kK\u000e$\b"
)
public interface RandomSampler extends Pseudorandom, Cloneable, Serializable {
   static double roundingEpsilon() {
      return RandomSampler$.MODULE$.roundingEpsilon();
   }

   static double rngEpsilon() {
      return RandomSampler$.MODULE$.rngEpsilon();
   }

   static double defaultMaxGapSamplingFraction() {
      return RandomSampler$.MODULE$.defaultMaxGapSamplingFraction();
   }

   static Random newDefaultRNG() {
      return RandomSampler$.MODULE$.newDefaultRNG();
   }

   // $FF: synthetic method
   static Iterator sample$(final RandomSampler $this, final Iterator items) {
      return $this.sample(items);
   }

   default Iterator sample(final Iterator items) {
      return items.filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$sample$1(this, x$1)));
   }

   int sample();

   // $FF: synthetic method
   static RandomSampler clone$(final RandomSampler $this) {
      return $this.clone();
   }

   default RandomSampler clone() {
      throw new UnsupportedOperationException("clone() is not implemented.");
   }

   // $FF: synthetic method
   static boolean $anonfun$sample$1(final RandomSampler $this, final Object x$1) {
      return $this.sample() > 0;
   }

   static void $init$(final RandomSampler $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

package org.apache.spark.graphx;

import scala.Some;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%4QAD\b\u0002\u0002aAQ\u0001\t\u0001\u0005\u0002\u0005BQ!\u000e\u0001\u0007\u0002YBQA\u0010\u0001\u0007\u0002YBQa\u0010\u0001\u0007\u0002\u0001CQ!\u0011\u0001\u0007\u0002\u0001CQA\u0011\u0001\u0007\u0002\rCQ\u0001\u0012\u0001\u0007\u0002\u0015CQa\u0013\u0001\u0007\u00021CQA\u0014\u0001\u0005\u0002=;QaU\b\t\u0002Q3QAD\b\t\u0002UCQ\u0001I\u0006\u0005\u0002YCQaV\u0006\u0005\u0002a\u00131\"\u00123hK\u000e{g\u000e^3yi*\u0011\u0001#E\u0001\u0007OJ\f\u0007\u000f\u001b=\u000b\u0005I\u0019\u0012!B:qCJ\\'B\u0001\u000b\u0016\u0003\u0019\t\u0007/Y2iK*\ta#A\u0002pe\u001e\u001c\u0001!\u0006\u0003\u001aMA\u001a4C\u0001\u0001\u001b!\tYb$D\u0001\u001d\u0015\u0005i\u0012!B:dC2\f\u0017BA\u0010\u001d\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012A\t\t\u0006G\u0001!sFM\u0007\u0002\u001fA\u0011QE\n\u0007\u0001\t\u00159\u0003A1\u0001)\u0005\t1F)\u0005\u0002*YA\u00111DK\u0005\u0003Wq\u0011qAT8uQ&tw\r\u0005\u0002\u001c[%\u0011a\u0006\b\u0002\u0004\u0003:L\bCA\u00131\t\u0015\t\u0004A1\u0001)\u0005\t)E\t\u0005\u0002&g\u0011)A\u0007\u0001b\u0001Q\t\t\u0011)A\u0003te\u000eLE-F\u00018!\tA4H\u0004\u0002$s%\u0011!hD\u0001\ba\u0006\u001c7.Y4f\u0013\taTH\u0001\u0005WKJ$X\r_%e\u0015\tQt\"A\u0003egRLE-A\u0004te\u000e\fE\u000f\u001e:\u0016\u0003\u0011\nq\u0001Z:u\u0003R$(/\u0001\u0003biR\u0014X#A\u0018\u0002\u0013M,g\u000e\u001a+p'J\u001cGC\u0001$J!\tYr)\u0003\u0002I9\t!QK\\5u\u0011\u0015Qu\u00011\u00013\u0003\ri7oZ\u0001\ng\u0016tG\rV8EgR$\"AR'\t\u000b)C\u0001\u0019\u0001\u001a\u0002\u001bQ|W\tZ4f)JL\u0007\u000f\\3u+\u0005\u0001\u0006\u0003B\u0012RI=J!AU\b\u0003\u0017\u0015#w-\u001a+sSBdW\r^\u0001\f\u000b\u0012<WmQ8oi\u0016DH\u000f\u0005\u0002$\u0017M\u00111B\u0007\u000b\u0002)\u00069QO\\1qa2LX\u0003B-bG\"$\"A\u00173\u0011\u0007mYV,\u0003\u0002]9\t!1k\\7f!\u001dYblN\u001caA\nL!a\u0018\u000f\u0003\rQ+\b\u000f\\36!\t)\u0013\rB\u0003(\u001b\t\u0007\u0001\u0006\u0005\u0002&G\u0012)\u0011'\u0004b\u0001Q!)Q-\u0004a\u0001M\u0006!Q\rZ4f!\u0015\u0019\u0003\u0001\u00192h!\t)\u0003\u000eB\u00035\u001b\t\u0007\u0001\u0006"
)
public abstract class EdgeContext {
   public static Some unapply(final EdgeContext edge) {
      return EdgeContext$.MODULE$.unapply(edge);
   }

   public abstract long srcId();

   public abstract long dstId();

   public abstract Object srcAttr();

   public abstract Object dstAttr();

   public abstract Object attr();

   public abstract void sendToSrc(final Object msg);

   public abstract void sendToDst(final Object msg);

   public EdgeTriplet toEdgeTriplet() {
      EdgeTriplet et = new EdgeTriplet();
      et.srcId_$eq(this.srcId());
      et.srcAttr_$eq(this.srcAttr());
      et.dstId_$eq(this.dstId());
      et.dstAttr_$eq(this.dstAttr());
      et.attr_$eq(this.attr());
      return et;
   }
}

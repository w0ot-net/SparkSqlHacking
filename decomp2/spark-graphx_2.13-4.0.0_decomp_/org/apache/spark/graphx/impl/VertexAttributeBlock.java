package org.apache.spark.graphx.impl;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t4Q\u0001C\u0005\u0001\u0017MA\u0001\u0002\u000b\u0001\u0003\u0006\u0004%\t!\u000b\u0005\tw\u0001\u0011\t\u0011)A\u0005U!AA\b\u0001BC\u0002\u0013\u0005Q\b\u0003\u0005K\u0001\t\u0005\t\u0015!\u0003?\u0011!Y\u0005AaA!\u0002\u0017a\u0005\"\u0002*\u0001\t\u0003\u0019\u0006\"\u0002.\u0001\t\u0003Y&\u0001\u0006,feR,\u00070\u0011;ue&\u0014W\u000f^3CY>\u001c7N\u0003\u0002\u000b\u0017\u0005!\u0011.\u001c9m\u0015\taQ\"\u0001\u0004he\u0006\u0004\b\u000e\u001f\u0006\u0003\u001d=\tQa\u001d9be.T!\u0001E\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0011\u0012aA8sOV\u0011A#Q\n\u0004\u0001UY\u0002C\u0001\f\u001a\u001b\u00059\"\"\u0001\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005i9\"AB!osJ+g\r\u0005\u0002\u001dK9\u0011Qd\t\b\u0003=\tj\u0011a\b\u0006\u0003A\u0005\na\u0001\u0010:p_Rt4\u0001A\u0005\u00021%\u0011AeF\u0001\ba\u0006\u001c7.Y4f\u0013\t1sE\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002%/\u0005!a/\u001b3t+\u0005Q\u0003c\u0001\f,[%\u0011Af\u0006\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003]ar!aL\u001c\u000f\u0005A2dBA\u00196\u001d\t\u0011DG\u0004\u0002\u001fg%\t!#\u0003\u0002\u0011#%\u0011abD\u0005\u0003\u00195I!\u0001J\u0006\n\u0005eR$\u0001\u0003,feR,\u00070\u00133\u000b\u0005\u0011Z\u0011!\u0002<jIN\u0004\u0013!B1uiJ\u001cX#\u0001 \u0011\u0007YYs\b\u0005\u0002A\u00032\u0001A!\u0002\"\u0001\u0005\u0004\u0019%A\u0001,E#\t!u\t\u0005\u0002\u0017\u000b&\u0011ai\u0006\u0002\b\u001d>$\b.\u001b8h!\t1\u0002*\u0003\u0002J/\t\u0019\u0011I\\=\u0002\r\u0005$HO]:!\u0003))g/\u001b3f]\u000e,G%\r\t\u0004\u001bB{T\"\u0001(\u000b\u0005=;\u0012a\u0002:fM2,7\r^\u0005\u0003#:\u0013\u0001b\u00117bgN$\u0016mZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007QC\u0016\f\u0006\u0002V/B\u0019a\u000bA \u000e\u0003%AQa\u0013\u0004A\u00041CQ\u0001\u000b\u0004A\u0002)BQ\u0001\u0010\u0004A\u0002y\n\u0001\"\u001b;fe\u0006$xN]\u000b\u00029B\u0019A$X0\n\u0005y;#\u0001C%uKJ\fGo\u001c:\u0011\tY\u0001WfP\u0005\u0003C^\u0011a\u0001V;qY\u0016\u0014\u0004"
)
public class VertexAttributeBlock implements Serializable {
   private final long[] vids;
   private final Object attrs;

   public long[] vids() {
      return this.vids;
   }

   public Object attrs() {
      return this.attrs;
   }

   public Iterator iterator() {
      return .MODULE$.indices$extension(scala.Predef..MODULE$.longArrayOps(this.vids())).iterator().map((i) -> $anonfun$iterator$1(this, BoxesRunTime.unboxToInt(i)));
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$iterator$1(final VertexAttributeBlock $this, final int i) {
      return new Tuple2(BoxesRunTime.boxToLong($this.vids()[i]), scala.runtime.ScalaRunTime..MODULE$.array_apply($this.attrs(), i));
   }

   public VertexAttributeBlock(final long[] vids, final Object attrs, final ClassTag evidence$1) {
      this.vids = vids;
      this.attrs = attrs;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

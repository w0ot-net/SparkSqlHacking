package org.apache.spark.streaming.util;

import org.apache.spark.SparkContext;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t<a!\u0003\u0006\t\u00021!bA\u0002\f\u000b\u0011\u0003aq\u0003C\u0003\u001f\u0003\u0011\u0005\u0001\u0005C\u0003\"\u0003\u0011\u0005!\u0005C\u0003A\u0003\u0011\u0005\u0011\tC\u0003J\u0003\u0011\u0005!\nC\u0003U\u0003\u0011\u0005Q\u000bC\u0003[\u0003\u0011\u00051\fC\u0003_\u0003\u0011\u0005q,A\u0007SC^$V\r\u001f;IK2\u0004XM\u001d\u0006\u0003\u00171\tA!\u001e;jY*\u0011QBD\u0001\ngR\u0014X-Y7j]\u001eT!a\u0004\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005E\u0011\u0012AB1qC\u000eDWMC\u0001\u0014\u0003\ry'o\u001a\t\u0003+\u0005i\u0011A\u0003\u0002\u000e%\u0006<H+\u001a=u\u0011\u0016d\u0007/\u001a:\u0014\u0005\u0005A\u0002CA\r\u001d\u001b\u0005Q\"\"A\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005uQ\"AB!osJ+g-\u0001\u0004=S:LGOP\u0002\u0001)\u0005!\u0012aF:qY&$\u0018I\u001c3D_VtG\u000fU1si&$\u0018n\u001c8t)\t\u0019S\bE\u0002%Y=r!!\n\u0016\u000f\u0005\u0019JS\"A\u0014\u000b\u0005!z\u0012A\u0002\u001fs_>$h(C\u0001\u001c\u0013\tY#$A\u0004qC\u000e\\\u0017mZ3\n\u00055r#\u0001C%uKJ\fGo\u001c:\u000b\u0005-R\u0002\u0003B\r1eiJ!!\r\u000e\u0003\rQ+\b\u000f\\33!\t\u0019tG\u0004\u00025kA\u0011aEG\u0005\u0003mi\ta\u0001\u0015:fI\u00164\u0017B\u0001\u001d:\u0005\u0019\u0019FO]5oO*\u0011aG\u0007\t\u00033mJ!\u0001\u0010\u000e\u0003\t1{gn\u001a\u0005\u0006}\r\u0001\raP\u0001\u0005SR,'\u000fE\u0002%YI\nA\u0001^8q\u0017R\u00191E\u0011#\t\u000b\r#\u0001\u0019A\u0012\u0002\t\u0011\fG/\u0019\u0005\u0006\u000b\u0012\u0001\rAR\u0001\u0002WB\u0011\u0011dR\u0005\u0003\u0011j\u00111!\u00138u\u0003\u00199\u0018M]7VaR\u00111J\u0014\t\u000331K!!\u0014\u000e\u0003\tUs\u0017\u000e\u001e\u0005\u0006\u001f\u0016\u0001\r\u0001U\u0001\u0003g\u000e\u0004\"!\u0015*\u000e\u00039I!a\u0015\b\u0003\u0019M\u0003\u0018M]6D_:$X\r\u001f;\u0002\u0007\u0005$G\rF\u0002;-bCQa\u0016\u0004A\u0002i\n!A^\u0019\t\u000be3\u0001\u0019\u0001\u001e\u0002\u0005Y\u0014\u0014\u0001C:vER\u0014\u0018m\u0019;\u0015\u0007ibV\fC\u0003X\u000f\u0001\u0007!\bC\u0003Z\u000f\u0001\u0007!(A\u0002nCb$2A\u000f1b\u0011\u00159\u0006\u00021\u0001;\u0011\u0015I\u0006\u00021\u0001;\u0001"
)
public final class RawTextHelper {
   public static long max(final long v1, final long v2) {
      return RawTextHelper$.MODULE$.max(v1, v2);
   }

   public static long subtract(final long v1, final long v2) {
      return RawTextHelper$.MODULE$.subtract(v1, v2);
   }

   public static long add(final long v1, final long v2) {
      return RawTextHelper$.MODULE$.add(v1, v2);
   }

   public static void warmUp(final SparkContext sc) {
      RawTextHelper$.MODULE$.warmUp(sc);
   }

   public static Iterator topK(final Iterator data, final int k) {
      return RawTextHelper$.MODULE$.topK(data, k);
   }

   public static Iterator splitAndCountPartitions(final Iterator iter) {
      return RawTextHelper$.MODULE$.splitAndCountPartitions(iter);
   }
}

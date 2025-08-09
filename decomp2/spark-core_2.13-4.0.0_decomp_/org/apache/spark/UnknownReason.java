package org.apache.spark;

import org.apache.spark.annotation.DeveloperApi;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005A<Q\u0001D\u0007\t\u0002R1QAF\u0007\t\u0002^AQ\u0001M\u0001\u0005\u0002EBQAM\u0001\u0005BMBq\u0001P\u0001\u0002\u0002\u0013\u0005S\bC\u0004F\u0003\u0005\u0005I\u0011\u0001$\t\u000f)\u000b\u0011\u0011!C\u0001\u0017\"9\u0011+AA\u0001\n\u0003\u0012\u0006bB-\u0002\u0003\u0003%\tA\u0017\u0005\b?\u0006\t\t\u0011\"\u0011a\u0011\u001d\t\u0017!!A\u0005B\tDqaY\u0001\u0002\u0002\u0013%A-A\u0007V].twn\u001e8SK\u0006\u001cxN\u001c\u0006\u0003\u001d=\tQa\u001d9be.T!\u0001E\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0011\u0012aA8sO\u000e\u0001\u0001CA\u000b\u0002\u001b\u0005i!!D+oW:|wO\u001c*fCN|gnE\u0003\u00021y\tC\u0005\u0005\u0002\u001a95\t!DC\u0001\u001c\u0003\u0015\u00198-\u00197b\u0013\ti\"D\u0001\u0004B]f\u0014VM\u001a\t\u0003+}I!\u0001I\u0007\u0003!Q\u000b7o\u001b$bS2,GMU3bg>t\u0007CA\r#\u0013\t\u0019#DA\u0004Qe>$Wo\u0019;\u0011\u0005\u0015jcB\u0001\u0014,\u001d\t9#&D\u0001)\u0015\tI3#\u0001\u0004=e>|GOP\u0005\u00027%\u0011AFG\u0001\ba\u0006\u001c7.Y4f\u0013\tqsF\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002-5\u00051A(\u001b8jiz\"\u0012\u0001F\u0001\u000ei>,%O]8s'R\u0014\u0018N\\4\u0016\u0003Q\u0002\"!N\u001d\u000f\u0005Y:\u0004CA\u0014\u001b\u0013\tA$$\u0001\u0004Qe\u0016$WMZ\u0005\u0003um\u0012aa\u0015;sS:<'B\u0001\u001d\u001b\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\ta\b\u0005\u0002@\t6\t\u0001I\u0003\u0002B\u0005\u0006!A.\u00198h\u0015\u0005\u0019\u0015\u0001\u00026bm\u0006L!A\u000f!\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003\u001d\u0003\"!\u0007%\n\u0005%S\"aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u0001'P!\tIR*\u0003\u0002O5\t\u0019\u0011I\\=\t\u000fA3\u0011\u0011!a\u0001\u000f\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012a\u0015\t\u0004)^cU\"A+\u000b\u0005YS\u0012AC2pY2,7\r^5p]&\u0011\u0001,\u0016\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002\\=B\u0011\u0011\u0004X\u0005\u0003;j\u0011qAQ8pY\u0016\fg\u000eC\u0004Q\u0011\u0005\u0005\t\u0019\u0001'\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012aR\u0001\ti>\u001cFO]5oOR\ta(\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001f!\tyd-\u0003\u0002h\u0001\n1qJ\u00196fGRD#!A5\u0011\u0005)lW\"A6\u000b\u00051l\u0011AC1o]>$\u0018\r^5p]&\u0011an\u001b\u0002\r\t\u00164X\r\\8qKJ\f\u0005/\u001b\u0015\u0003\u0001%\u0004"
)
public final class UnknownReason {
   public static String toString() {
      return UnknownReason$.MODULE$.toString();
   }

   public static int hashCode() {
      return UnknownReason$.MODULE$.hashCode();
   }

   public static boolean canEqual(final Object x$1) {
      return UnknownReason$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return UnknownReason$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return UnknownReason$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return UnknownReason$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return UnknownReason$.MODULE$.productPrefix();
   }

   public static String toErrorString() {
      return UnknownReason$.MODULE$.toErrorString();
   }

   public static Iterator productElementNames() {
      return UnknownReason$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return UnknownReason$.MODULE$.productElementName(n);
   }

   public static boolean countTowardsTaskFailures() {
      return UnknownReason$.MODULE$.countTowardsTaskFailures();
   }
}

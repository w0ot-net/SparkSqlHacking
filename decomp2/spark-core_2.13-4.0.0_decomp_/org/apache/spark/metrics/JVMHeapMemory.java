package org.apache.spark.metrics;

import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u00059<Q\u0001D\u0007\t\u0002Z1Q\u0001G\u0007\t\u0002fAQAM\u0001\u0005\u0002MBa\u0001N\u0001\u0005B=)\u0004bB!\u0002\u0003\u0003%\tE\u0011\u0005\b\u0017\u0006\t\t\u0011\"\u0001M\u0011\u001d\u0001\u0016!!A\u0005\u0002ECqaV\u0001\u0002\u0002\u0013\u0005\u0003\fC\u0004`\u0003\u0005\u0005I\u0011\u00011\t\u000f\u0015\f\u0011\u0011!C!M\"9q-AA\u0001\n\u0003B\u0007bB5\u0002\u0003\u0003%IA[\u0001\u000e\u0015Zk\u0005*Z1q\u001b\u0016lwN]=\u000b\u00059y\u0011aB7fiJL7m\u001d\u0006\u0003!E\tQa\u001d9be.T!AE\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005!\u0012aA8sO\u000e\u0001\u0001CA\f\u0002\u001b\u0005i!!\u0004&W\u001b\"+\u0017\r]'f[>\u0014\u0018pE\u0003\u00025\u0001\u001ac\u0005\u0005\u0002\u001c=5\tADC\u0001\u001e\u0003\u0015\u00198-\u00197b\u0013\tyBD\u0001\u0004B]f\u0014VM\u001a\t\u0003/\u0005J!AI\u0007\u0003;MKgn\u001a7f-\u0006dW/Z#yK\u000e,Ho\u001c:NKR\u0014\u0018n\u0019+za\u0016\u0004\"a\u0007\u0013\n\u0005\u0015b\"a\u0002)s_\u0012,8\r\u001e\t\u0003O=r!\u0001K\u0017\u000f\u0005%bS\"\u0001\u0016\u000b\u0005-*\u0012A\u0002\u001fs_>$h(C\u0001\u001e\u0013\tqC$A\u0004qC\u000e\\\u0017mZ3\n\u0005A\n$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u0018\u001d\u0003\u0019a\u0014N\\5u}Q\ta#\u0001\bhKRlU\r\u001e:jGZ\u000bG.^3\u0015\u0005YJ\u0004CA\u000e8\u0013\tADD\u0001\u0003M_:<\u0007\"\u0002\u001e\u0004\u0001\u0004Y\u0014!D7f[>\u0014\u00180T1oC\u001e,'\u000f\u0005\u0002=\u007f5\tQH\u0003\u0002?\u001f\u00051Q.Z7pefL!\u0001Q\u001f\u0003\u001b5+Wn\u001c:z\u001b\u0006t\u0017mZ3s\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t1\t\u0005\u0002E\u00136\tQI\u0003\u0002G\u000f\u0006!A.\u00198h\u0015\u0005A\u0015\u0001\u00026bm\u0006L!AS#\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005i\u0005CA\u000eO\u0013\tyEDA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002S+B\u00111dU\u0005\u0003)r\u00111!\u00118z\u0011\u001d1f!!AA\u00025\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A-\u0011\u0007ik&+D\u0001\\\u0015\taF$\u0001\u0006d_2dWm\u0019;j_:L!AX.\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0003C\u0012\u0004\"a\u00072\n\u0005\rd\"a\u0002\"p_2,\u0017M\u001c\u0005\b-\"\t\t\u00111\u0001S\u0003!A\u0017m\u001d5D_\u0012,G#A'\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012aQ\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0002WB\u0011A\t\\\u0005\u0003[\u0016\u0013aa\u00142kK\u000e$\b"
)
public final class JVMHeapMemory {
   public static String toString() {
      return JVMHeapMemory$.MODULE$.toString();
   }

   public static int hashCode() {
      return JVMHeapMemory$.MODULE$.hashCode();
   }

   public static boolean canEqual(final Object x$1) {
      return JVMHeapMemory$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return JVMHeapMemory$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return JVMHeapMemory$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return JVMHeapMemory$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return JVMHeapMemory$.MODULE$.productPrefix();
   }

   public static Iterator productElementNames() {
      return JVMHeapMemory$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return JVMHeapMemory$.MODULE$.productElementName(n);
   }
}

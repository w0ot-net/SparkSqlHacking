package org.apache.spark.metrics;

import org.apache.spark.internal.Logging;
import scala.StringContext;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=s!B\r\u001b\u0011\u0003\u001bc!B\u0013\u001b\u0011\u00033\u0003\"B#\u0002\t\u00031\u0005bB$\u0002\u0001\u0004%I\u0001\u0013\u0005\b)\u0006\u0001\r\u0011\"\u0003V\u0011\u0019Y\u0016\u0001)Q\u0005\u0013\"9A,\u0001b\u0001\n\u0003j\u0006B\u00027\u0002A\u0003%a\f\u0003\u0005n\u0003\t\u0007I\u0011\u0001\u000f^\u0011\u0019q\u0017\u0001)A\u0005=\"Aq.\u0001b\u0001\n\u0003aR\f\u0003\u0004q\u0003\u0001\u0006IA\u0018\u0005\tc\u0006\u0011\r\u0011\"\u0001\u001de\"11/\u0001Q\u0001\n\u0015D\u0001\u0002^\u0001\t\u0006\u0004%I\u0001\u0013\u0005\tk\u0006A)\u0019!C\u0005\u0011\"1a/\u0001C!9]D\u0001\"!\u0004\u0002\u0003\u0003%\tE\u001d\u0005\n\u0003\u001f\t\u0011\u0011!C\u0001\u0003#A\u0011\"!\u0007\u0002\u0003\u0003%\t!a\u0007\t\u0013\u0005\u0015\u0012!!A\u0005B\u0005\u001d\u0002\"CA\u0019\u0003\u0005\u0005I\u0011AA\u001a\u0011%\ti$AA\u0001\n\u0003\ny\u0004C\u0005\u0002B\u0005\t\t\u0011\"\u0011\u0002D!I\u0011QI\u0001\u0002\u0002\u0013%\u0011qI\u0001\u0019\u000f\u0006\u0014(-Y4f\u0007>dG.Z2uS>tW*\u001a;sS\u000e\u001c(BA\u000e\u001d\u0003\u001diW\r\u001e:jGNT!!\b\u0010\u0002\u000bM\u0004\u0018M]6\u000b\u0005}\u0001\u0013AB1qC\u000eDWMC\u0001\"\u0003\ry'oZ\u0002\u0001!\t!\u0013!D\u0001\u001b\u0005a9\u0015M\u001d2bO\u0016\u001cu\u000e\u001c7fGRLwN\\'fiJL7m]\n\u0007\u0003\u001dj\u0003GN\u001d\u0011\u0005!ZS\"A\u0015\u000b\u0003)\nQa]2bY\u0006L!\u0001L\u0015\u0003\r\u0005s\u0017PU3g!\t!c&\u0003\u000205\t\u0011R\t_3dkR|'/T3ue&\u001cG+\u001f9f!\t\tD'D\u00013\u0015\t\u0019D$\u0001\u0005j]R,'O\\1m\u0013\t)$GA\u0004M_\u001e<\u0017N\\4\u0011\u0005!:\u0014B\u0001\u001d*\u0005\u001d\u0001&o\u001c3vGR\u0004\"A\u000f\"\u000f\u0005m\u0002eB\u0001\u001f@\u001b\u0005i$B\u0001 #\u0003\u0019a$o\\8u}%\t!&\u0003\u0002BS\u00059\u0001/Y2lC\u001e,\u0017BA\"E\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\t\u0015&\u0001\u0004=S:LGO\u0010\u000b\u0002G\u0005!bn\u001c8Ck&dG/\u00138D_2dWm\u0019;peN,\u0012!\u0013\t\u0004u)c\u0015BA&E\u0005\r\u0019V-\u001d\t\u0003\u001bFs!AT(\u0011\u0005qJ\u0013B\u0001)*\u0003\u0019\u0001&/\u001a3fM&\u0011!k\u0015\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005AK\u0013\u0001\u00078p]\n+\u0018\u000e\u001c;J]\u000e{G\u000e\\3di>\u00148o\u0018\u0013fcR\u0011a+\u0017\t\u0003Q]K!\u0001W\u0015\u0003\tUs\u0017\u000e\u001e\u0005\b5\u0012\t\t\u00111\u0001J\u0003\rAH%M\u0001\u0016]>t')^5mi&s7i\u001c7mK\u000e$xN]:!\u0003\u0015q\u0017-\\3t+\u0005q\u0006cA0eK6\t\u0001M\u0003\u0002bE\u0006I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003G&\n!bY8mY\u0016\u001cG/[8o\u0013\tY\u0005\r\u0005\u0002gW6\tqM\u0003\u0002iS\u0006!A.\u00198h\u0015\u0005Q\u0017\u0001\u00026bm\u0006L!AU4\u0002\r9\fW.Z:!\u0003-Jv*\u0016(H?\u001e+e*\u0012*B)&{ej\u0018\"V\u00132#\u0016JT0H\u0003J\u0013\u0015iR#`\u0007>cE*R\"U\u001fJ\u001b\u0016\u0001L-P+:;ulR#O\u000bJ\u000bE+S(O?\n+\u0016\n\u0014+J\u001d~;\u0015I\u0015\"B\u000f\u0016{6i\u0014'M\u000b\u000e#vJU*!\u0003%zE\nR0H\u000b:+%+\u0011+J\u001f:{&)V%M)&sulR!S\u0005\u0006;UiX\"P\u00192+5\tV(S'\u0006Qs\n\u0014#`\u000f\u0016sUIU!U\u0013>suLQ+J\u0019RKejX$B%\n\u000bu)R0D\u001f2cUi\u0011+P%N\u0003\u0013\u0001\n\"V\u00132#\u0016JT0D\u001f:\u001bUK\u0015*F\u001dR{v)\u0011*C\u0003\u001e+ulQ(M\u0019\u0016\u001bEk\u0014*\u0016\u0003\u0015\fQEQ+J\u0019RKejX\"P\u001d\u000e+&KU#O)~;\u0015I\u0015\"B\u000f\u0016{6i\u0014'M\u000b\u000e#vJ\u0015\u0011\u0002?e|WO\\4HK:,'/\u0019;j_:<\u0015M\u001d2bO\u0016\u001cu\u000e\u001c7fGR|'/A\u000fpY\u0012<UM\\3sCRLwN\\$be\n\fw-Z\"pY2,7\r^8s\u0003=9W\r^'fiJL7MV1mk\u0016\u001cHC\u0001=\u007f!\rA\u0013p_\u0005\u0003u&\u0012Q!\u0011:sCf\u0004\"\u0001\u000b?\n\u0005uL#\u0001\u0002'p]\u001eDaa \tA\u0002\u0005\u0005\u0011!D7f[>\u0014\u00180T1oC\u001e,'\u000f\u0005\u0003\u0002\u0004\u0005%QBAA\u0003\u0015\r\t9\u0001H\u0001\u0007[\u0016lwN]=\n\t\u0005-\u0011Q\u0001\u0002\u000e\u001b\u0016lwN]=NC:\fw-\u001a:\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\t\u0019\u0002E\u0002)\u0003+I1!a\u0006*\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\ti\"a\t\u0011\u0007!\ny\"C\u0002\u0002\"%\u00121!\u00118z\u0011!Q6#!AA\u0002\u0005M\u0011a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005%\u0002CBA\u0016\u0003[\ti\"D\u0001c\u0013\r\tyC\u0019\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u00026\u0005m\u0002c\u0001\u0015\u00028%\u0019\u0011\u0011H\u0015\u0003\u000f\t{w\u000e\\3b]\"A!,FA\u0001\u0002\u0004\ti\"\u0001\u0005iCND7i\u001c3f)\t\t\u0019\"\u0001\u0005u_N#(/\u001b8h)\u0005)\u0017\u0001D<sSR,'+\u001a9mC\u000e,GCAA%!\r1\u00171J\u0005\u0004\u0003\u001b:'AB(cU\u0016\u001cG\u000f"
)
public final class GarbageCollectionMetrics {
   public static String toString() {
      return GarbageCollectionMetrics$.MODULE$.toString();
   }

   public static int hashCode() {
      return GarbageCollectionMetrics$.MODULE$.hashCode();
   }

   public static boolean canEqual(final Object x$1) {
      return GarbageCollectionMetrics$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return GarbageCollectionMetrics$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return GarbageCollectionMetrics$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return GarbageCollectionMetrics$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return GarbageCollectionMetrics$.MODULE$.productPrefix();
   }

   public static Seq names() {
      return GarbageCollectionMetrics$.MODULE$.names();
   }

   public static Iterator productElementNames() {
      return GarbageCollectionMetrics$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return GarbageCollectionMetrics$.MODULE$.productElementName(n);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return GarbageCollectionMetrics$.MODULE$.LogStringContext(sc);
   }
}

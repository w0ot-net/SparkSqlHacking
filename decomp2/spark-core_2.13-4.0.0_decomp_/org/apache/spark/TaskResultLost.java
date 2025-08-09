package org.apache.spark;

import org.apache.spark.annotation.DeveloperApi;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005A<Q\u0001D\u0007\t\u0002R1QAF\u0007\t\u0002^AQ\u0001M\u0001\u0005\u0002EBQAM\u0001\u0005BMBq\u0001P\u0001\u0002\u0002\u0013\u0005S\bC\u0004F\u0003\u0005\u0005I\u0011\u0001$\t\u000f)\u000b\u0011\u0011!C\u0001\u0017\"9\u0011+AA\u0001\n\u0003\u0012\u0006bB-\u0002\u0003\u0003%\tA\u0017\u0005\b?\u0006\t\t\u0011\"\u0011a\u0011\u001d\t\u0017!!A\u0005B\tDqaY\u0001\u0002\u0002\u0013%A-\u0001\bUCN\\'+Z:vYRdun\u001d;\u000b\u00059y\u0011!B:qCJ\\'B\u0001\t\u0012\u0003\u0019\t\u0007/Y2iK*\t!#A\u0002pe\u001e\u001c\u0001\u0001\u0005\u0002\u0016\u00035\tQB\u0001\bUCN\\'+Z:vYRdun\u001d;\u0014\u000b\u0005Ab$\t\u0013\u0011\u0005eaR\"\u0001\u000e\u000b\u0003m\tQa]2bY\u0006L!!\b\u000e\u0003\r\u0005s\u0017PU3g!\t)r$\u0003\u0002!\u001b\t\u0001B+Y:l\r\u0006LG.\u001a3SK\u0006\u001cxN\u001c\t\u00033\tJ!a\t\u000e\u0003\u000fA\u0013x\u000eZ;diB\u0011Q%\f\b\u0003M-r!a\n\u0016\u000e\u0003!R!!K\n\u0002\rq\u0012xn\u001c;?\u0013\u0005Y\u0012B\u0001\u0017\u001b\u0003\u001d\u0001\u0018mY6bO\u0016L!AL\u0018\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u00051R\u0012A\u0002\u001fj]&$h\bF\u0001\u0015\u00035!x.\u0012:s_J\u001cFO]5oOV\tA\u0007\u0005\u00026s9\u0011ag\u000e\t\u0003OiI!\u0001\u000f\u000e\u0002\rA\u0013X\rZ3g\u0013\tQ4H\u0001\u0004TiJLgn\u001a\u0006\u0003qi\tQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u0001 \u0011\u0005}\"U\"\u0001!\u000b\u0005\u0005\u0013\u0015\u0001\u00027b]\u001eT\u0011aQ\u0001\u0005U\u00064\u0018-\u0003\u0002;\u0001\u0006a\u0001O]8ek\u000e$\u0018I]5usV\tq\t\u0005\u0002\u001a\u0011&\u0011\u0011J\u0007\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003\u0019>\u0003\"!G'\n\u00059S\"aA!os\"9\u0001KBA\u0001\u0002\u00049\u0015a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001T!\r!v\u000bT\u0007\u0002+*\u0011aKG\u0001\u000bG>dG.Z2uS>t\u0017B\u0001-V\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0005ms\u0006CA\r]\u0013\ti&DA\u0004C_>dW-\u00198\t\u000fAC\u0011\u0011!a\u0001\u0019\u0006A\u0001.Y:i\u0007>$W\rF\u0001H\u0003!!xn\u0015;sS:<G#\u0001 \u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0003\u0015\u0004\"a\u00104\n\u0005\u001d\u0004%AB(cU\u0016\u001cG\u000f\u000b\u0002\u0002SB\u0011!.\\\u0007\u0002W*\u0011A.D\u0001\u000bC:tw\u000e^1uS>t\u0017B\u00018l\u00051!UM^3m_B,'/\u00119jQ\t\u0001\u0011\u000e"
)
public final class TaskResultLost {
   public static String toString() {
      return TaskResultLost$.MODULE$.toString();
   }

   public static int hashCode() {
      return TaskResultLost$.MODULE$.hashCode();
   }

   public static boolean canEqual(final Object x$1) {
      return TaskResultLost$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return TaskResultLost$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return TaskResultLost$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return TaskResultLost$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return TaskResultLost$.MODULE$.productPrefix();
   }

   public static String toErrorString() {
      return TaskResultLost$.MODULE$.toErrorString();
   }

   public static Iterator productElementNames() {
      return TaskResultLost$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return TaskResultLost$.MODULE$.productElementName(n);
   }

   public static boolean countTowardsTaskFailures() {
      return TaskResultLost$.MODULE$.countTowardsTaskFailures();
   }
}

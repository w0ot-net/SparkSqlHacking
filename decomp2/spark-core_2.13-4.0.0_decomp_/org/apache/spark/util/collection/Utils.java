package org.apache.spark.util.collection;

import java.util.Map;
import scala.Option;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015rA\u0002\u0005\n\u0011\u0003i1C\u0002\u0004\u0016\u0013!\u0005QB\u0006\u0005\u0006C\u0005!\ta\t\u0005\u0006I\u0005!\t!\n\u0005\u0006\u0017\u0006!\t\u0001\u0014\u0005\u00069\u0006!\t!\u0018\u0005\u0006U\u0006!\ta\u001b\u0005\b\u0003\u0007\tA\u0011AA\u0003\u0003\u0015)F/\u001b7t\u0015\tQ1\"\u0001\u0006d_2dWm\u0019;j_:T!\u0001D\u0007\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u001d=\tQa\u001d9be.T!\u0001E\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0011\u0012aA8sOB\u0011A#A\u0007\u0002\u0013\t)Q\u000b^5mgN\u0019\u0011aF\u000f\u0011\u0005aYR\"A\r\u000b\u0003i\tQa]2bY\u0006L!\u0001H\r\u0003\r\u0005s\u0017PU3g!\tqr$D\u0001\f\u0013\t\u00013B\u0001\u000bTa\u0006\u00148nQ8mY\u0016\u001cG/[8o+RLGn]\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\t1#A\u0006uC.,wJ\u001d3fe\u0016$WC\u0001\u00147)\r9CI\u0012\u000b\u0003Q}\u00022!K\u00195\u001d\tQsF\u0004\u0002,]5\tAF\u0003\u0002.E\u00051AH]8pizJ\u0011AG\u0005\u0003ae\tq\u0001]1dW\u0006<W-\u0003\u00023g\tA\u0011\n^3sCR|'O\u0003\u000213A\u0011QG\u000e\u0007\u0001\t\u001594A1\u00019\u0005\u0005!\u0016CA\u001d=!\tA\"(\u0003\u0002<3\t9aj\u001c;iS:<\u0007C\u0001\r>\u0013\tq\u0014DA\u0002B]fDQ\u0001Q\u0002A\u0004\u0005\u000b1a\u001c:e!\rI#\tN\u0005\u0003\u0007N\u0012\u0001b\u0014:eKJLgn\u001a\u0005\u0006\u000b\u000e\u0001\r\u0001K\u0001\u0006S:\u0004X\u000f\u001e\u0005\u0006\u000f\u000e\u0001\r\u0001S\u0001\u0004]Vl\u0007C\u0001\rJ\u0013\tQ\u0015DA\u0002J]R\fA\"\\3sO\u0016|%\u000fZ3sK\u0012,\"!T)\u0015\u00059#FCA(S!\rI\u0013\u0007\u0015\t\u0003kE#Qa\u000e\u0003C\u0002aBQ\u0001\u0011\u0003A\u0004M\u00032!\u000b\"Q\u0011\u0015)F\u00011\u0001W\u0003\u0019Ig\u000e];ugB\u0019\u0011fV-\n\u0005a\u001b$\u0001C%uKJ\f'\r\\3\u0011\u0007%R\u0006+\u0003\u0002\\g\ta\u0011\n^3sC\ndWm\u00148dK\u0006\u00012/Z9vK:\u001cW\rV8PaRLwN\\\u000b\u0003=\u001a$\"aX4\u0011\u0007a\u0001'-\u0003\u0002b3\t1q\n\u001d;j_:\u00042!K2f\u0013\t!7GA\u0002TKF\u0004\"!\u000e4\u0005\u000b]*!\u0019\u0001\u001d\t\u000b\u0015+\u0001\u0019\u00015\u0011\u0007%\u001a\u0017\u000eE\u0002\u0019A\u0016\fQ\u0001^8NCB,2\u0001\u001c<z)\ri7P \t\u0005]J,\bP\u0004\u0002paB\u00111&G\u0005\u0003cf\ta\u0001\u0015:fI\u00164\u0017BA:u\u0005\ri\u0015\r\u001d\u0006\u0003cf\u0001\"!\u000e<\u0005\u000b]4!\u0019\u0001\u001d\u0003\u0003-\u0003\"!N=\u0005\u000bi4!\u0019\u0001\u001d\u0003\u0003YCQ\u0001 \u0004A\u0002u\fAa[3zgB\u0019\u0011fV;\t\r}4\u0001\u0019AA\u0001\u0003\u00191\u0018\r\\;fgB\u0019\u0011f\u0016=\u0002\u0013Q|'*\u0019<b\u001b\u0006\u0004XCBA\u0004\u0003/\tY\u0002\u0006\u0004\u0002\n\u0005u\u0011\u0011\u0005\t\t\u0003\u0017\t\u0019\"!\u0006\u0002\u001a5\u0011\u0011Q\u0002\u0006\u0004\u0019\u0005=!BAA\t\u0003\u0011Q\u0017M^1\n\u0007M\fi\u0001E\u00026\u0003/!Qa^\u0004C\u0002a\u00022!NA\u000e\t\u0015QxA1\u00019\u0011\u0019ax\u00011\u0001\u0002 A!\u0011fVA\u000b\u0011\u0019yx\u00011\u0001\u0002$A!\u0011fVA\r\u0001"
)
public final class Utils {
   public static Map toJavaMap(final Iterable keys, final Iterable values) {
      return Utils$.MODULE$.toJavaMap(keys, values);
   }

   public static scala.collection.immutable.Map toMap(final Iterable keys, final Iterable values) {
      return Utils$.MODULE$.toMap(keys, values);
   }

   public static Option sequenceToOption(final Seq input) {
      return Utils$.MODULE$.sequenceToOption(input);
   }

   public static Iterator mergeOrdered(final Iterable inputs, final Ordering ord) {
      return Utils$.MODULE$.mergeOrdered(inputs, ord);
   }

   public static Iterator takeOrdered(final Iterator input, final int num, final Ordering ord) {
      return Utils$.MODULE$.takeOrdered(input, num, ord);
   }

   public static scala.collection.immutable.Map toMapWithIndex(final Iterable keys) {
      return Utils$.MODULE$.toMapWithIndex(keys);
   }
}

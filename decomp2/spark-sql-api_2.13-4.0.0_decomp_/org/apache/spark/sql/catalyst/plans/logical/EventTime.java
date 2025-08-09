package org.apache.spark.sql.catalyst.plans.logical;

import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019<Qa\u0003\u0007\t\u0002n1Q!\b\u0007\t\u0002zAQaN\u0001\u0005\u0002aBq!O\u0001\u0002\u0002\u0013\u0005#\bC\u0004D\u0003\u0005\u0005I\u0011\u0001#\t\u000f!\u000b\u0011\u0011!C\u0001\u0013\"9q*AA\u0001\n\u0003\u0002\u0006bB,\u0002\u0003\u0003%\t\u0001\u0017\u0005\b;\u0006\t\t\u0011\"\u0011_\u0011\u001dy\u0016!!A\u0005B\u0001Dq!Y\u0001\u0002\u0002\u0013%!-A\u0005Fm\u0016tG\u000fV5nK*\u0011QBD\u0001\bY><\u0017nY1m\u0015\ty\u0001#A\u0003qY\u0006t7O\u0003\u0002\u0012%\u0005A1-\u0019;bYf\u001cHO\u0003\u0002\u0014)\u0005\u00191/\u001d7\u000b\u0005U1\u0012!B:qCJ\\'BA\f\u0019\u0003\u0019\t\u0007/Y2iK*\t\u0011$A\u0002pe\u001e\u001c\u0001\u0001\u0005\u0002\u001d\u00035\tABA\u0005Fm\u0016tG\u000fV5nKN!\u0011aH\u0013,!\t\u00013%D\u0001\"\u0015\t\u0011##A\u0005tiJ,\u0017-\\5oO&\u0011A%\t\u0002\t)&lW-T8eKB\u0011a%K\u0007\u0002O)\t\u0001&A\u0003tG\u0006d\u0017-\u0003\u0002+O\t9\u0001K]8ek\u000e$\bC\u0001\u00175\u001d\ti#G\u0004\u0002/c5\tqF\u0003\u000215\u00051AH]8pizJ\u0011\u0001K\u0005\u0003g\u001d\nq\u0001]1dW\u0006<W-\u0003\u00026m\ta1+\u001a:jC2L'0\u00192mK*\u00111gJ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003m\tQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A\u001e\u0011\u0005q\nU\"A\u001f\u000b\u0005yz\u0014\u0001\u00027b]\u001eT\u0011\u0001Q\u0001\u0005U\u00064\u0018-\u0003\u0002C{\t11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012!\u0012\t\u0003M\u0019K!aR\u0014\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005)k\u0005C\u0001\u0014L\u0013\tauEA\u0002B]fDqAT\u0003\u0002\u0002\u0003\u0007Q)A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002#B\u0019!+\u0016&\u000e\u0003MS!\u0001V\u0014\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002W'\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\tIF\f\u0005\u0002'5&\u00111l\n\u0002\b\u0005>|G.Z1o\u0011\u001dqu!!AA\u0002)\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002\u000b\u0006AAo\\*ue&tw\rF\u0001<\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005\u0019\u0007C\u0001\u001fe\u0013\t)WH\u0001\u0004PE*,7\r\u001e"
)
public final class EventTime {
   public static String toString() {
      return EventTime$.MODULE$.toString();
   }

   public static int hashCode() {
      return EventTime$.MODULE$.hashCode();
   }

   public static boolean canEqual(final Object x$1) {
      return EventTime$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return EventTime$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return EventTime$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return EventTime$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return EventTime$.MODULE$.productPrefix();
   }

   public static Iterator productElementNames() {
      return EventTime$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return EventTime$.MODULE$.productElementName(n);
   }
}

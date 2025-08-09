package org.apache.spark.sql.types;

import scala.Option;
import scala.collection.Iterator;
import scala.math.PartialOrdering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mr!B\n\u0015\u0011\u0003{b!B\u0011\u0015\u0011\u0003\u0013\u0003\"\u0002 \u0002\t\u0003y\u0004\"\u0002!\u0002\t\u0003\n\u0005\"\u0002'\u0002\t\u0003j\u0005\"B*\u0002\t\u0003\"\u0006\"B,\u0002\t\u0003B\u0006\"B.\u0002\t\u0003a\u0006\"\u00022\u0002\t\u0003\u0019\u0007\"\u00025\u0002\t\u0003I\u0007\"B8\u0002\t\u0003\u0001\bb\u0002:\u0002\u0003\u0003%\te\u001d\u0005\by\u0006\t\t\u0011\"\u0001~\u0011\u001dq\u0018!!A\u0005\u0002}D\u0011\"a\u0003\u0002\u0003\u0003%\t%!\u0004\t\u0013\u0005m\u0011!!A\u0005\u0002\u0005u\u0001\"CA\u0011\u0003\u0005\u0005I\u0011IA\u0012\u0011%\t)#AA\u0001\n\u0003\n9\u0003C\u0005\u0002*\u0005\t\t\u0011\"\u0003\u0002,\u0005a1\u000b\u001e:j]\u001eDU\r\u001c9fe*\u0011QCF\u0001\u0006if\u0004Xm\u001d\u0006\u0003/a\t1a]9m\u0015\tI\"$A\u0003ta\u0006\u00148N\u0003\u0002\u001c9\u00051\u0011\r]1dQ\u0016T\u0011!H\u0001\u0004_J<7\u0001\u0001\t\u0003A\u0005i\u0011\u0001\u0006\u0002\r'R\u0014\u0018N\\4IK2\u0004XM]\n\u0006\u0003\rJ\u0003h\u000f\t\u0003I\u001dj\u0011!\n\u0006\u0002M\u0005)1oY1mC&\u0011\u0001&\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0007)\u0012TG\u0004\u0002,a9\u0011AfL\u0007\u0002[)\u0011aFH\u0001\u0007yI|w\u000e\u001e \n\u0003\u0019J!!M\u0013\u0002\u000fA\f7m[1hK&\u00111\u0007\u000e\u0002\u0010!\u0006\u0014H/[1m\u001fJ$WM]5oO*\u0011\u0011'\n\t\u0003AYJ!a\u000e\u000b\u0003!M#(/\u001b8h\u0007>t7\u000f\u001e:bS:$\bC\u0001\u0013:\u0013\tQTEA\u0004Qe>$Wo\u0019;\u0011\u0005)b\u0014BA\u001f5\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u0019a\u0014N\\5u}Q\tq$\u0001\u0006uef\u001cu.\u001c9be\u0016$2A\u0011%K!\r!3)R\u0005\u0003\t\u0016\u0012aa\u00149uS>t\u0007C\u0001\u0013G\u0013\t9UEA\u0002J]RDQ!S\u0002A\u0002U\n\u0011\u0001\u001f\u0005\u0006\u0017\u000e\u0001\r!N\u0001\u0002s\u0006!A\u000e^3r)\rq\u0015K\u0015\t\u0003I=K!\u0001U\u0013\u0003\u000f\t{w\u000e\\3b]\")\u0011\n\u0002a\u0001k!)1\n\u0002a\u0001k\u0005!q\r^3r)\rqUK\u0016\u0005\u0006\u0013\u0016\u0001\r!\u000e\u0005\u0006\u0017\u0016\u0001\r!N\u0001\u0006KF,\u0018N\u001e\u000b\u0004\u001dfS\u0006\"B%\u0007\u0001\u0004)\u0004\"B&\u0007\u0001\u0004)\u0014!D5t!2\f\u0017N\\*ue&tw\r\u0006\u0002O;\")al\u0002a\u0001?\u0006\t1\u000f\u0005\u0002!A&\u0011\u0011\r\u0006\u0002\u000b'R\u0014\u0018N\\4UsB,\u0017!E5t\u001b>\u0014XmQ8ogR\u0014\u0018-\u001b8fIR\u0019a\n\u001a4\t\u000b\u0015D\u0001\u0019A0\u0002\u0003\u0005DQa\u001a\u0005A\u0002}\u000b\u0011AY\u0001\u0015i&<\u0007\u000e^3ti\u000e{W.\\8o'R\u0014\u0018N\\4\u0015\u0007)\\W\u000eE\u0002%\u0007~CQ\u0001\\\u0005A\u0002}\u000b!a]\u0019\t\u000b9L\u0001\u0019A0\u0002\u0005M\u0014\u0014a\u0004:f[>4XmQ8mY\u0006$\u0018n\u001c8\u0015\u0005}\u000b\b\"\u00020\u000b\u0001\u0004y\u0016!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001u!\t)(0D\u0001w\u0015\t9\b0\u0001\u0003mC:<'\"A=\u0002\t)\fg/Y\u0005\u0003wZ\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#A#\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011\u0011AA\u0004!\r!\u00131A\u0005\u0004\u0003\u000b)#aA!os\"A\u0011\u0011B\u0007\u0002\u0002\u0003\u0007Q)A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u001f\u0001b!!\u0005\u0002\u0018\u0005\u0005QBAA\n\u0015\r\t)\"J\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\r\u0003'\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0019a*a\b\t\u0013\u0005%q\"!AA\u0002\u0005\u0005\u0011\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003\u0015\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0002i\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011Q\u0006\t\u0004k\u0006=\u0012bAA\u0019m\n1qJ\u00196fGR\u0004"
)
public final class StringHelper {
   public static String toString() {
      return StringHelper$.MODULE$.toString();
   }

   public static int hashCode() {
      return StringHelper$.MODULE$.hashCode();
   }

   public static boolean canEqual(final Object x$1) {
      return StringHelper$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return StringHelper$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return StringHelper$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return StringHelper$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return StringHelper$.MODULE$.productPrefix();
   }

   public static StringType removeCollation(final StringType s) {
      return StringHelper$.MODULE$.removeCollation(s);
   }

   public static Option tightestCommonString(final StringType s1, final StringType s2) {
      return StringHelper$.MODULE$.tightestCommonString(s1, s2);
   }

   public static boolean isMoreConstrained(final StringType a, final StringType b) {
      return StringHelper$.MODULE$.isMoreConstrained(a, b);
   }

   public static boolean isPlainString(final StringType s) {
      return StringHelper$.MODULE$.isPlainString(s);
   }

   public static boolean equiv(final StringConstraint x, final StringConstraint y) {
      return StringHelper$.MODULE$.equiv(x, y);
   }

   public static boolean gteq(final StringConstraint x, final StringConstraint y) {
      return StringHelper$.MODULE$.gteq(x, y);
   }

   public static boolean lteq(final StringConstraint x, final StringConstraint y) {
      return StringHelper$.MODULE$.lteq(x, y);
   }

   public static Option tryCompare(final StringConstraint x, final StringConstraint y) {
      return StringHelper$.MODULE$.tryCompare(x, y);
   }

   public static Iterator productElementNames() {
      return StringHelper$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return StringHelper$.MODULE$.productElementName(n);
   }

   public static PartialOrdering reverse() {
      return StringHelper$.MODULE$.reverse();
   }

   public static boolean gt(final Object x, final Object y) {
      return StringHelper$.MODULE$.gt(x, y);
   }

   public static boolean lt(final Object x, final Object y) {
      return StringHelper$.MODULE$.lt(x, y);
   }
}

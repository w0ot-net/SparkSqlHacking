package org.apache.spark.sql.catalyst.util;

import org.apache.spark.QueryContext;
import scala.Function0;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mu!\u0002\u000f\u001e\u0011\u0003Qc!\u0002\u0017\u001e\u0011\u0003i\u0003\"\u0002\u001b\u0002\t\u0003)\u0004\"\u0002\u001c\u0002\t\u00039\u0004\"\u0002\u001c\u0002\t\u0003y\u0004\"\u0002\u001c\u0002\t\u0003A\u0005\"\u0002\u001c\u0002\t\u0003q\u0005\"\u0002*\u0002\t\u0003\u0019\u0006\"\u0002*\u0002\t\u00031\u0006\"\u0002*\u0002\t\u0003Q\u0006\"\u0002*\u0002\t\u0003i\u0006\"B1\u0002\t\u0003\u0011\u0007\"B1\u0002\t\u0003)\u0007\"B1\u0002\t\u0003I\u0007\"B1\u0002\t\u0003a\u0007\"\u00029\u0002\t\u0003\t\b\"\u00029\u0002\t\u00031\b\"\u00029\u0002\t\u0003Y\b\"\u00029\u0002\t\u0003i\bBB@\u0002\t\u0003\t\t\u0001C\u0004\u0002\u0006\u0005!\t!a\u0002\t\u000f\u0005\u0015\u0011\u0001\"\u0001\u0002\u000e!9\u00111C\u0001\u0005\u0002\u0005U\u0001bBA\n\u0003\u0011\u0005\u00111\u0004\u0005\b\u0003C\tA\u0011AA\u0012\u0011%\t\u0019'AI\u0001\n\u0003\t)\u0007C\u0005\u0002\u0000\u0005\t\n\u0011\"\u0001\u0002\u0002\"9\u0011\u0011R\u0001\u0005\u0002\u0005-\u0015!C'bi\",F/\u001b7t\u0015\tqr$\u0001\u0003vi&d'B\u0001\u0011\"\u0003!\u0019\u0017\r^1msN$(B\u0001\u0012$\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003I\u0015\nQa\u001d9be.T!AJ\u0014\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005A\u0013aA8sO\u000e\u0001\u0001CA\u0016\u0002\u001b\u0005i\"!C'bi\",F/\u001b7t'\t\ta\u0006\u0005\u00020e5\t\u0001GC\u00012\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0004G\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003)\n\u0001\"\u00193e\u000bb\f7\r\u001e\u000b\u0004qmj\u0004CA\u0018:\u0013\tQ\u0004GA\u0002J]RDQ\u0001P\u0002A\u0002a\n\u0011!\u0019\u0005\u0006}\r\u0001\r\u0001O\u0001\u0002ER!\u0001\bQ!C\u0011\u0015aD\u00011\u00019\u0011\u0015qD\u00011\u00019\u0011\u0015\u0019E\u00011\u0001E\u0003\u001d\u0019wN\u001c;fqR\u0004\"!\u0012$\u000e\u0003\rJ!aR\u0012\u0003\u0019E+XM]=D_:$X\r\u001f;\u0015\u0007%cU\n\u0005\u00020\u0015&\u00111\n\r\u0002\u0005\u0019>tw\rC\u0003=\u000b\u0001\u0007\u0011\nC\u0003?\u000b\u0001\u0007\u0011\n\u0006\u0003J\u001fB\u000b\u0006\"\u0002\u001f\u0007\u0001\u0004I\u0005\"\u0002 \u0007\u0001\u0004I\u0005\"B\"\u0007\u0001\u0004!\u0015!D:vER\u0014\u0018m\u0019;Fq\u0006\u001cG\u000fF\u00029)VCQ\u0001P\u0004A\u0002aBQAP\u0004A\u0002a\"B\u0001O,Y3\")A\b\u0003a\u0001q!)a\b\u0003a\u0001q!)1\t\u0003a\u0001\tR\u0019\u0011j\u0017/\t\u000bqJ\u0001\u0019A%\t\u000byJ\u0001\u0019A%\u0015\t%sv\f\u0019\u0005\u0006y)\u0001\r!\u0013\u0005\u0006})\u0001\r!\u0013\u0005\u0006\u0007*\u0001\r\u0001R\u0001\u000e[VdG/\u001b9ms\u0016C\u0018m\u0019;\u0015\u0007a\u001aG\rC\u0003=\u0017\u0001\u0007\u0001\bC\u0003?\u0017\u0001\u0007\u0001\b\u0006\u00039M\u001eD\u0007\"\u0002\u001f\r\u0001\u0004A\u0004\"\u0002 \r\u0001\u0004A\u0004\"B\"\r\u0001\u0004!EcA%kW\")A(\u0004a\u0001\u0013\")a(\u0004a\u0001\u0013R!\u0011*\u001c8p\u0011\u0015ad\u00021\u0001J\u0011\u0015qd\u00021\u0001J\u0011\u0015\u0019e\u00021\u0001E\u0003-qWmZ1uK\u0016C\u0018m\u0019;\u0015\u0005I,\bCA\u0018t\u0013\t!\bG\u0001\u0003CsR,\u0007\"\u0002\u001f\u0010\u0001\u0004\u0011HCA<{!\ty\u00030\u0003\u0002za\t)1\u000b[8si\")A\b\u0005a\u0001oR\u0011\u0001\b \u0005\u0006yE\u0001\r\u0001\u000f\u000b\u0003\u0013zDQ\u0001\u0010\nA\u0002%\u000b!\u0002^8J]R,\u00050Y2u)\rA\u00141\u0001\u0005\u0006yM\u0001\r!S\u0001\tM2|wN\u001d#jmR)\u0001(!\u0003\u0002\f!)A\b\u0006a\u0001q!)a\b\u0006a\u0001qQ)\u0011*a\u0004\u0002\u0012!)A(\u0006a\u0001\u0013\")a(\u0006a\u0001\u0013\u0006Aa\r\\8pe6{G\rF\u00039\u0003/\tI\u0002C\u0003=-\u0001\u0007\u0001\bC\u0003?-\u0001\u0007\u0001\bF\u0003J\u0003;\ty\u0002C\u0003=/\u0001\u0007\u0011\nC\u0003?/\u0001\u0007\u0011*\u0001\u0007xSRDwJ^3sM2|w/\u0006\u0003\u0002&\u0005-B\u0003CA\u0014\u0003{\t9%!\u0019\u0011\t\u0005%\u00121\u0006\u0007\u0001\t\u001d\ti\u0003\u0007b\u0001\u0003_\u0011\u0011!Q\t\u0005\u0003c\t9\u0004E\u00020\u0003gI1!!\u000e1\u0005\u001dqu\u000e\u001e5j]\u001e\u00042aLA\u001d\u0013\r\tY\u0004\r\u0002\u0004\u0003:L\b\u0002CA 1\u0011\u0005\r!!\u0011\u0002\u0003\u0019\u0004RaLA\"\u0003OI1!!\u00121\u0005!a$-\u001f8b[\u0016t\u0004\"CA%1A\u0005\t\u0019AA&\u0003\u0011A\u0017N\u001c;\u0011\t\u00055\u00131\f\b\u0005\u0003\u001f\n9\u0006E\u0002\u0002RAj!!a\u0015\u000b\u0007\u0005U\u0013&\u0001\u0004=e>|GOP\u0005\u0004\u00033\u0002\u0014A\u0002)sK\u0012,g-\u0003\u0003\u0002^\u0005}#AB*ue&twMC\u0002\u0002ZABqa\u0011\r\u0011\u0002\u0003\u0007A)\u0001\fxSRDwJ^3sM2|w\u000f\n3fM\u0006,H\u000e\u001e\u00133+\u0011\t9'! \u0016\u0005\u0005%$\u0006BA&\u0003WZ#!!\u001c\u0011\t\u0005=\u0014\u0011P\u0007\u0003\u0003cRA!a\u001d\u0002v\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003o\u0002\u0014AC1o]>$\u0018\r^5p]&!\u00111PA9\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\b\u0003[I\"\u0019AA\u0018\u0003Y9\u0018\u000e\u001e5Pm\u0016\u0014h\r\\8xI\u0011,g-Y;mi\u0012\u001aT\u0003BAB\u0003\u000f+\"!!\"+\u0007\u0011\u000bY\u0007B\u0004\u0002.i\u0011\r!a\f\u0002!]LG\u000f[(wKJ4Gn\\<D_\u0012,GCBA&\u0003\u001b\u000b\t\nC\u0004\u0002\u0010n\u0001\r!a\u0013\u0002\u0011\u00154\u0018\r\\\"pI\u0016DaaQ\u000eA\u0002\u0005-\u0003"
)
public final class MathUtils {
   public static String withOverflowCode(final String evalCode, final String context) {
      return MathUtils$.MODULE$.withOverflowCode(evalCode, context);
   }

   public static QueryContext withOverflow$default$3() {
      return MathUtils$.MODULE$.withOverflow$default$3();
   }

   public static String withOverflow$default$2() {
      return MathUtils$.MODULE$.withOverflow$default$2();
   }

   public static Object withOverflow(final Function0 f, final String hint, final QueryContext context) {
      return MathUtils$.MODULE$.withOverflow(f, hint, context);
   }

   public static long floorMod(final long a, final long b) {
      return MathUtils$.MODULE$.floorMod(a, b);
   }

   public static int floorMod(final int a, final int b) {
      return MathUtils$.MODULE$.floorMod(a, b);
   }

   public static long floorDiv(final long a, final long b) {
      return MathUtils$.MODULE$.floorDiv(a, b);
   }

   public static int floorDiv(final int a, final int b) {
      return MathUtils$.MODULE$.floorDiv(a, b);
   }

   public static int toIntExact(final long a) {
      return MathUtils$.MODULE$.toIntExact(a);
   }

   public static long negateExact(final long a) {
      return MathUtils$.MODULE$.negateExact(a);
   }

   public static int negateExact(final int a) {
      return MathUtils$.MODULE$.negateExact(a);
   }

   public static short negateExact(final short a) {
      return MathUtils$.MODULE$.negateExact(a);
   }

   public static byte negateExact(final byte a) {
      return MathUtils$.MODULE$.negateExact(a);
   }

   public static long multiplyExact(final long a, final long b, final QueryContext context) {
      return MathUtils$.MODULE$.multiplyExact(a, b, context);
   }

   public static long multiplyExact(final long a, final long b) {
      return MathUtils$.MODULE$.multiplyExact(a, b);
   }

   public static int multiplyExact(final int a, final int b, final QueryContext context) {
      return MathUtils$.MODULE$.multiplyExact(a, b, context);
   }

   public static int multiplyExact(final int a, final int b) {
      return MathUtils$.MODULE$.multiplyExact(a, b);
   }

   public static long subtractExact(final long a, final long b, final QueryContext context) {
      return MathUtils$.MODULE$.subtractExact(a, b, context);
   }

   public static long subtractExact(final long a, final long b) {
      return MathUtils$.MODULE$.subtractExact(a, b);
   }

   public static int subtractExact(final int a, final int b, final QueryContext context) {
      return MathUtils$.MODULE$.subtractExact(a, b, context);
   }

   public static int subtractExact(final int a, final int b) {
      return MathUtils$.MODULE$.subtractExact(a, b);
   }

   public static long addExact(final long a, final long b, final QueryContext context) {
      return MathUtils$.MODULE$.addExact(a, b, context);
   }

   public static long addExact(final long a, final long b) {
      return MathUtils$.MODULE$.addExact(a, b);
   }

   public static int addExact(final int a, final int b, final QueryContext context) {
      return MathUtils$.MODULE$.addExact(a, b, context);
   }

   public static int addExact(final int a, final int b) {
      return MathUtils$.MODULE$.addExact(a, b);
   }
}

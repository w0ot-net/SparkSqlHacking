package org.apache.spark.sql.catalyst.parser;

import java.util.BitSet;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dr!\u0002\u0007\u000e\u0011\u0003Sb!\u0002\u000f\u000e\u0011\u0003k\u0002\"\u0002\u001e\u0002\t\u0003Y\u0004\"\u0002\u001f\u0002\t\u0003j\u0004bB7\u0002\u0003\u0003%\tE\u001c\u0005\bm\u0006\t\t\u0011\"\u0001x\u0011\u001dA\u0018!!A\u0005\u0002eDq\u0001`\u0001\u0002\u0002\u0013\u0005S\u0010C\u0005\u0002\n\u0005\t\t\u0011\"\u0001\u0002\f!I\u0011QC\u0001\u0002\u0002\u0013\u0005\u0013q\u0003\u0005\n\u00033\t\u0011\u0011!C!\u00037A\u0011\"!\b\u0002\u0003\u0003%I!a\b\u0002%A\u000b'o]3FeJ|'\u000fT5ti\u0016tWM\u001d\u0006\u0003\u001d=\ta\u0001]1sg\u0016\u0014(B\u0001\t\u0012\u0003!\u0019\u0017\r^1msN$(B\u0001\n\u0014\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003)U\tQa\u001d9be.T!AF\f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005A\u0012aA8sO\u000e\u0001\u0001CA\u000e\u0002\u001b\u0005i!A\u0005)beN,WI\u001d:pe2K7\u000f^3oKJ\u001cB!\u0001\u0010)]A\u0011qDJ\u0007\u0002A)\u0011\u0011EI\u0001\beVtG/[7f\u0015\t\u0019C%\u0001\u0002wi)\u0011QeF\u0001\u0006C:$HN]\u0005\u0003O\u0001\u0012\u0011CQ1tK\u0016\u0013(o\u001c:MSN$XM\\3s!\tIC&D\u0001+\u0015\u0005Y\u0013!B:dC2\f\u0017BA\u0017+\u0005\u001d\u0001&o\u001c3vGR\u0004\"aL\u001c\u000f\u0005A*dBA\u00195\u001b\u0005\u0011$BA\u001a\u001a\u0003\u0019a$o\\8u}%\t1&\u0003\u00027U\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001d:\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t1$&\u0001\u0004=S:LGO\u0010\u000b\u00025\u0005Y1/\u001f8uCb,%O]8s)\u001dq\u0014)V,]=\"\u0004\"!K \n\u0005\u0001S#\u0001B+oSRDQAQ\u0002A\u0002\r\u000b!B]3d_\u001et\u0017N_3sa\r!\u0015j\u0015\t\u0005?\u0015;%+\u0003\u0002GA\tQ!+Z2pO:L'0\u001a:\u0011\u0005!KE\u0002\u0001\u0003\n\u0015\u0006\u000b\t\u0011!A\u0003\u0002-\u00131a\u0018\u00132#\tau\n\u0005\u0002*\u001b&\u0011aJ\u000b\u0002\b\u001d>$\b.\u001b8h!\tI\u0003+\u0003\u0002RU\t\u0019\u0011I\\=\u0011\u0005!\u001bF!\u0003+B\u0003\u0003\u0005\tQ!\u0001L\u0005\ryFE\r\u0005\u0006-\u000e\u0001\raT\u0001\u0010_\u001a4WM\u001c3j]\u001e\u001c\u00160\u001c2pY\")\u0001l\u0001a\u00013\u0006!A.\u001b8f!\tI#,\u0003\u0002\\U\t\u0019\u0011J\u001c;\t\u000bu\u001b\u0001\u0019A-\u0002%\rD\u0017M\u001d)pg&$\u0018n\u001c8J]2Kg.\u001a\u0005\u0006?\u000e\u0001\r\u0001Y\u0001\u0004[N<\u0007CA1f\u001d\t\u00117\r\u0005\u00022U%\u0011AMK\u0001\u0007!J,G-\u001a4\n\u0005\u0019<'AB*ue&twM\u0003\u0002eU!)\u0011n\u0001a\u0001U\u0006\tQ\r\u0005\u0002 W&\u0011A\u000e\t\u0002\u0015%\u0016\u001cwn\u001a8ji&|g.\u0012=dKB$\u0018n\u001c8\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005y\u0007C\u00019v\u001b\u0005\t(B\u0001:t\u0003\u0011a\u0017M\\4\u000b\u0003Q\fAA[1wC&\u0011a-]\u0001\raJ|G-^2u\u0003JLG/_\u000b\u00023\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HCA({\u0011\u001dYh!!AA\u0002e\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#\u0001@\u0011\t}\f)aT\u0007\u0003\u0003\u0003Q1!a\u0001+\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003\u000f\t\tA\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u0007\u0003'\u00012!KA\b\u0013\r\t\tB\u000b\u0002\b\u0005>|G.Z1o\u0011\u001dY\b\"!AA\u0002=\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u00023\u0006AAo\\*ue&tw\rF\u0001p\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\t\u0003E\u0002q\u0003GI1!!\nr\u0005\u0019y%M[3di\u0002"
)
public final class ParseErrorListener {
   public static String toString() {
      return ParseErrorListener$.MODULE$.toString();
   }

   public static int hashCode() {
      return ParseErrorListener$.MODULE$.hashCode();
   }

   public static boolean canEqual(final Object x$1) {
      return ParseErrorListener$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return ParseErrorListener$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return ParseErrorListener$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return ParseErrorListener$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return ParseErrorListener$.MODULE$.productPrefix();
   }

   public static void syntaxError(final Recognizer recognizer, final Object offendingSymbol, final int line, final int charPositionInLine, final String msg, final RecognitionException e) {
      ParseErrorListener$.MODULE$.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);
   }

   public static Iterator productElementNames() {
      return ParseErrorListener$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return ParseErrorListener$.MODULE$.productElementName(n);
   }

   public static void reportContextSensitivity(final Parser x$1, final DFA x$2, final int x$3, final int x$4, final int x$5, final ATNConfigSet x$6) {
      ParseErrorListener$.MODULE$.reportContextSensitivity(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static void reportAttemptingFullContext(final Parser x$1, final DFA x$2, final int x$3, final int x$4, final BitSet x$5, final ATNConfigSet x$6) {
      ParseErrorListener$.MODULE$.reportAttemptingFullContext(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static void reportAmbiguity(final Parser x$1, final DFA x$2, final int x$3, final int x$4, final boolean x$5, final BitSet x$6, final ATNConfigSet x$7) {
      ParseErrorListener$.MODULE$.reportAmbiguity(x$1, x$2, x$3, x$4, x$5, x$6, x$7);
   }
}

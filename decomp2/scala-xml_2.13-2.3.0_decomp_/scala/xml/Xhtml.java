package scala.xml;

import scala.collection.Seq;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mq!B\u000b\u0017\u0011\u0003Yb!B\u000f\u0017\u0011\u0003q\u0002\"B\u0012\u0002\t\u0003!\u0003\"B\u0013\u0002\t\u00031\u0003\"B\u0013\u0002\t\u00039\u0004bB\u001f\u0002\u0005\u0004%IA\u0010\u0005\u0007\r\u0006\u0001\u000b\u0011B \t\u000b\u0015\nA\u0011A$\t\u000f\t\f\u0011\u0013!C\u0001G\"9a.AI\u0001\n\u0003y\u0007bB9\u0002#\u0003%\tA\u001d\u0005\bi\u0006\t\n\u0011\"\u0001s\u0011\u001d)\u0018!%A\u0005\u0002IDqA^\u0001\u0012\u0002\u0013\u0005!\u000fC\u0003x\u0003\u0011\u0005\u0001\u0010\u0003\u0005\u0002\u0010\u0005\t\n\u0011\"\u0001d\u0011!\t\t\"AI\u0001\n\u0003y\u0007\u0002CA\n\u0003E\u0005I\u0011\u0001:\t\u0011\u0005U\u0011!%A\u0005\u0002ID\u0001\"a\u0006\u0002#\u0003%\tA\u001d\u0005\t\u00033\t\u0011\u0013!C\u0001e\u0006)\u0001\f\u001b;nY*\u0011q\u0003G\u0001\u0004q6d'\"A\r\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001A\u0011A$A\u0007\u0002-\t)\u0001\f\u001b;nYN\u0011\u0011a\b\t\u0003A\u0005j\u0011\u0001G\u0005\u0003Ea\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001\u001c\u0003\u001d!x\u000e\u00175u[2$\"a\n\u001a\u0011\u0005!zcBA\u0015.!\tQ\u0003$D\u0001,\u0015\ta#$\u0001\u0004=e>|GOP\u0005\u0003]a\ta\u0001\u0015:fI\u00164\u0017B\u0001\u00192\u0005\u0019\u0019FO]5oO*\u0011a\u0006\u0007\u0005\u0006g\r\u0001\r\u0001N\u0001\u0005]>$W\r\u0005\u0002\u001dk%\u0011aG\u0006\u0002\u0005\u001d>$W\r\u0006\u0002(q!)\u0011\b\u0002a\u0001u\u00059an\u001c3f'\u0016\f\bC\u0001\u000f<\u0013\tadCA\u0004O_\u0012,7+Z9\u0002'5Lg.[7ju\u0006\u0014G.Z#mK6,g\u000e^:\u0016\u0003}\u00022\u0001Q\"(\u001d\t\u0001\u0013)\u0003\u0002C1\u00059\u0001/Y2lC\u001e,\u0017B\u0001#F\u0005\u0011a\u0015n\u001d;\u000b\u0005\tC\u0012\u0001F7j]&l\u0017N_1cY\u0016,E.Z7f]R\u001c\b\u0005\u0006\u0005I\u00176\u0013v\u000b\u00180a!\t\u0001\u0013*\u0003\u0002K1\t!QK\\5u\u0011\u0015au\u00011\u00015\u0003\u0005A\bb\u0002(\b!\u0003\u0005\raT\u0001\u0007aN\u001cw\u000e]3\u0011\u0005q\u0001\u0016BA)\u0017\u0005Aq\u0015-\\3ta\u0006\u001cWMQ5oI&tw\rC\u0004T\u000fA\u0005\t\u0019\u0001+\u0002\u0005M\u0014\u0007C\u0001!V\u0013\t1VIA\u0007TiJLgn\u001a\"vS2$WM\u001d\u0005\b1\u001e\u0001\n\u00111\u0001Z\u00035\u0019HO]5q\u0007>lW.\u001a8ugB\u0011\u0001EW\u0005\u00037b\u0011qAQ8pY\u0016\fg\u000eC\u0004^\u000fA\u0005\t\u0019A-\u0002\u001d\u0011,7m\u001c3f\u000b:$\u0018\u000e^5fg\"9ql\u0002I\u0001\u0002\u0004I\u0016A\u00059sKN,'O^3XQ&$Xm\u001d9bG\u0016Dq!Y\u0004\u0011\u0002\u0003\u0007\u0011,\u0001\u0007nS:LW.\u001b>f)\u0006<7/A\tu_bCG/\u001c7%I\u00164\u0017-\u001e7uII*\u0012\u0001\u001a\u0016\u0003\u001f\u0016\\\u0013A\u001a\t\u0003O2l\u0011\u0001\u001b\u0006\u0003S*\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005-D\u0012AC1o]>$\u0018\r^5p]&\u0011Q\u000e\u001b\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u0005;p1\"$X\u000e\u001c\u0013eK\u001a\fW\u000f\u001c;%gU\t\u0001O\u000b\u0002UK\u0006\tBo\u001c-ii6dG\u0005Z3gCVdG\u000f\n\u001b\u0016\u0003MT#!W3\u0002#Q|\u0007\f\u001b;nY\u0012\"WMZ1vYR$S'A\tu_bCG/\u001c7%I\u00164\u0017-\u001e7uIY\n\u0011\u0003^8YQRlG\u000e\n3fM\u0006,H\u000e\u001e\u00138\u00035\u0019X-];f]\u000e,Gk\u001c-N\u0019Rq\u0001*_A\u0002\u0003\u000b\t9!!\u0003\u0002\f\u00055\u0001\"\u0002>\u000f\u0001\u0004Y\u0018\u0001C2iS2$'/\u001a8\u0011\u0007q|H'D\u0001~\u0015\tq\b$\u0001\u0006d_2dWm\u0019;j_:L1!!\u0001~\u0005\r\u0019V-\u001d\u0005\b\u001d:\u0001\n\u00111\u0001P\u0011\u001d\u0019f\u0002%AA\u0002QCq\u0001\u0017\b\u0011\u0002\u0003\u0007\u0011\fC\u0004^\u001dA\u0005\t\u0019A-\t\u000f}s\u0001\u0013!a\u00013\"9\u0011M\u0004I\u0001\u0002\u0004I\u0016aF:fcV,gnY3U_bkE\n\n3fM\u0006,H\u000e\u001e\u00133\u0003]\u0019X-];f]\u000e,Gk\u001c-N\u0019\u0012\"WMZ1vYR$3'A\ftKF,XM\\2f)>DV\n\u0014\u0013eK\u001a\fW\u000f\u001c;%i\u000592/Z9vK:\u001cW\rV8Y\u001b2#C-\u001a4bk2$H%N\u0001\u0018g\u0016\fX/\u001a8dKR{\u0007,\u0014'%I\u00164\u0017-\u001e7uIY\nqc]3rk\u0016t7-\u001a+p16cE\u0005Z3gCVdG\u000fJ\u001c"
)
public final class Xhtml {
   public static boolean sequenceToXML$default$7() {
      return Xhtml$.MODULE$.sequenceToXML$default$7();
   }

   public static boolean sequenceToXML$default$6() {
      return Xhtml$.MODULE$.sequenceToXML$default$6();
   }

   public static boolean sequenceToXML$default$5() {
      return Xhtml$.MODULE$.sequenceToXML$default$5();
   }

   public static boolean sequenceToXML$default$4() {
      return Xhtml$.MODULE$.sequenceToXML$default$4();
   }

   public static StringBuilder sequenceToXML$default$3() {
      return Xhtml$.MODULE$.sequenceToXML$default$3();
   }

   public static NamespaceBinding sequenceToXML$default$2() {
      return Xhtml$.MODULE$.sequenceToXML$default$2();
   }

   public static void sequenceToXML(final Seq children, final NamespaceBinding pscope, final StringBuilder sb, final boolean stripComments, final boolean decodeEntities, final boolean preserveWhitespace, final boolean minimizeTags) {
      Xhtml$.MODULE$.sequenceToXML(children, pscope, sb, stripComments, decodeEntities, preserveWhitespace, minimizeTags);
   }

   public static boolean toXhtml$default$7() {
      return Xhtml$.MODULE$.toXhtml$default$7();
   }

   public static boolean toXhtml$default$6() {
      return Xhtml$.MODULE$.toXhtml$default$6();
   }

   public static boolean toXhtml$default$5() {
      return Xhtml$.MODULE$.toXhtml$default$5();
   }

   public static boolean toXhtml$default$4() {
      return Xhtml$.MODULE$.toXhtml$default$4();
   }

   public static StringBuilder toXhtml$default$3() {
      return Xhtml$.MODULE$.toXhtml$default$3();
   }

   public static NamespaceBinding toXhtml$default$2() {
      return Xhtml$.MODULE$.toXhtml$default$2();
   }

   public static void toXhtml(final Node x, final NamespaceBinding pscope, final StringBuilder sb, final boolean stripComments, final boolean decodeEntities, final boolean preserveWhitespace, final boolean minimizeTags) {
      Xhtml$.MODULE$.toXhtml(x, pscope, sb, stripComments, decodeEntities, preserveWhitespace, minimizeTags);
   }

   public static String toXhtml(final NodeSeq nodeSeq) {
      return Xhtml$.MODULE$.toXhtml(nodeSeq);
   }

   public static String toXhtml(final Node node) {
      return Xhtml$.MODULE$.toXhtml(node);
   }
}

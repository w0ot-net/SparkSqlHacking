package breeze.io;

import java.io.Reader;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q<QAE\n\t\u0002a1QAG\n\t\u0002mAQAI\u0001\u0005\u0002\rBQ\u0001J\u0001\u0005\u0002\u0015BqAU\u0001\u0012\u0002\u0013\u00051\u000bC\u0004_\u0003E\u0005I\u0011A*\t\u000f}\u000b\u0011\u0013!C\u0001'\"9\u0001-AI\u0001\n\u0003\t\u0007\"B2\u0002\t\u0003!\u0007b\u00026\u0002#\u0003%\ta\u0015\u0005\bW\u0006\t\n\u0011\"\u0001T\u0011\u001da\u0017!%A\u0005\u0002MCq!\\\u0001\u0012\u0002\u0013\u0005\u0011\rC\u0003o\u0003\u0011\u0005q\u000eC\u0004y\u0003E\u0005I\u0011A*\t\u000fe\f\u0011\u0013!C\u0001'\"9!0AI\u0001\n\u0003\u0019\u0006bB>\u0002#\u0003%\t!Y\u0001\n\u0007N3&+Z1eKJT!\u0001F\u000b\u0002\u0005%|'\"\u0001\f\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0001\"!G\u0001\u000e\u0003M\u0011\u0011bQ*W%\u0016\fG-\u001a:\u0014\u0005\u0005a\u0002CA\u000f!\u001b\u0005q\"\"A\u0010\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0005r\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u00021\u0005!!/Z1e)\u001913\bR%L\u001bB\u0019qe\f\u001a\u000f\u0005!jcBA\u0015-\u001b\u0005Q#BA\u0016\u0018\u0003\u0019a$o\\8u}%\tq$\u0003\u0002/=\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u00192\u0005)Ie\u000eZ3yK\u0012\u001cV-\u001d\u0006\u0003]y\u00012aJ\u00184!\t!\u0004H\u0004\u00026mA\u0011\u0011FH\u0005\u0003oy\ta\u0001\u0015:fI\u00164\u0017BA\u001d;\u0005\u0019\u0019FO]5oO*\u0011qG\b\u0005\u0006y\r\u0001\r!P\u0001\u0006S:\u0004X\u000f\u001e\t\u0003}\tk\u0011a\u0010\u0006\u0003)\u0001S\u0011!Q\u0001\u0005U\u00064\u0018-\u0003\u0002D\u007f\t1!+Z1eKJDq!R\u0002\u0011\u0002\u0003\u0007a)A\u0005tKB\f'/\u0019;peB\u0011QdR\u0005\u0003\u0011z\u0011Aa\u00115be\"9!j\u0001I\u0001\u0002\u00041\u0015!B9v_R,\u0007b\u0002'\u0004!\u0003\u0005\rAR\u0001\u0007KN\u001c\u0017\r]3\t\u000f9\u001b\u0001\u0013!a\u0001\u001f\u0006I1o[5q\u0019&tWm\u001d\t\u0003;AK!!\u0015\u0010\u0003\u0007%sG/\u0001\bsK\u0006$G\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003QS#AR+,\u0003Y\u0003\"a\u0016/\u000e\u0003aS!!\u0017.\u0002\u0013Ut7\r[3dW\u0016$'BA.\u001f\u0003)\tgN\\8uCRLwN\\\u0005\u0003;b\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0011X-\u00193%I\u00164\u0017-\u001e7uIM\naB]3bI\u0012\"WMZ1vYR$C'\u0001\bsK\u0006$G\u0005Z3gCVdG\u000fJ\u001b\u0016\u0003\tT#aT+\u0002\u000bA\f'o]3\u0015\r\u0019*gm\u001a5j\u0011\u0015a\u0004\u00021\u00014\u0011\u001d)\u0005\u0002%AA\u0002\u0019CqA\u0013\u0005\u0011\u0002\u0003\u0007a\tC\u0004M\u0011A\u0005\t\u0019\u0001$\t\u000f9C\u0001\u0013!a\u0001\u001f\u0006y\u0001/\u0019:tK\u0012\"WMZ1vYR$#'A\bqCJ\u001cX\r\n3fM\u0006,H\u000e\u001e\u00134\u0003=\u0001\u0018M]:fI\u0011,g-Y;mi\u0012\"\u0014a\u00049beN,G\u0005Z3gCVdG\u000fJ\u001b\u0002\u0011%$XM]1u_J$b\u0001]:ukZ<\bcA\u0014re%\u0011!/\r\u0002\t\u0013R,'/\u0019;pe\")A(\u0004a\u0001{!9Q)\u0004I\u0001\u0002\u00041\u0005b\u0002&\u000e!\u0003\u0005\rA\u0012\u0005\b\u00196\u0001\n\u00111\u0001G\u0011\u001dqU\u0002%AA\u0002=\u000b!#\u001b;fe\u0006$xN\u001d\u0013eK\u001a\fW\u000f\u001c;%e\u0005\u0011\u0012\u000e^3sCR|'\u000f\n3fM\u0006,H\u000e\u001e\u00134\u0003IIG/\u001a:bi>\u0014H\u0005Z3gCVdG\u000f\n\u001b\u0002%%$XM]1u_J$C-\u001a4bk2$H%\u000e"
)
public final class CSVReader {
   public static int iterator$default$5() {
      return CSVReader$.MODULE$.iterator$default$5();
   }

   public static char iterator$default$4() {
      return CSVReader$.MODULE$.iterator$default$4();
   }

   public static char iterator$default$3() {
      return CSVReader$.MODULE$.iterator$default$3();
   }

   public static char iterator$default$2() {
      return CSVReader$.MODULE$.iterator$default$2();
   }

   public static Iterator iterator(final Reader input, final char separator, final char quote, final char escape, final int skipLines) {
      return CSVReader$.MODULE$.iterator(input, separator, quote, escape, skipLines);
   }

   public static int parse$default$5() {
      return CSVReader$.MODULE$.parse$default$5();
   }

   public static char parse$default$4() {
      return CSVReader$.MODULE$.parse$default$4();
   }

   public static char parse$default$3() {
      return CSVReader$.MODULE$.parse$default$3();
   }

   public static char parse$default$2() {
      return CSVReader$.MODULE$.parse$default$2();
   }

   public static IndexedSeq parse(final String input, final char separator, final char quote, final char escape, final int skipLines) {
      return CSVReader$.MODULE$.parse(input, separator, quote, escape, skipLines);
   }

   public static int read$default$5() {
      return CSVReader$.MODULE$.read$default$5();
   }

   public static char read$default$4() {
      return CSVReader$.MODULE$.read$default$4();
   }

   public static char read$default$3() {
      return CSVReader$.MODULE$.read$default$3();
   }

   public static char read$default$2() {
      return CSVReader$.MODULE$.read$default$2();
   }

   public static IndexedSeq read(final Reader input, final char separator, final char quote, final char escape, final int skipLines) {
      return CSVReader$.MODULE$.read(input, separator, quote, escape, skipLines);
   }
}

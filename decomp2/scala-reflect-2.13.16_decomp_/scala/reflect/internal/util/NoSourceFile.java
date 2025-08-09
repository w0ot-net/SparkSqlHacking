package scala.reflect.internal.util;

import scala.Function1;
import scala.Option;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t<QAD\b\t\u0002a1QAG\b\t\u0002mAQaH\u0001\u0005\u0002\u0001BQ!I\u0001\u0005\u0002\tBQAK\u0001\u0005\u0002-BQaL\u0001\u0005\u0002ABQ!O\u0001\u0005\u0002iBQ\u0001P\u0001\u0005\u0002uBQAP\u0001\u0005\u0002}BQ\u0001Q\u0001\u0005\u0002}BQ!Q\u0001\u0005\u0002\tCQ!R\u0001\u0005\u0002\u0019CQ!S\u0001\u0005\u0002)CQ\u0001W\u0001\u0005Be\u000bABT8T_V\u00148-\u001a$jY\u0016T!\u0001E\t\u0002\tU$\u0018\u000e\u001c\u0006\u0003%M\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003)U\tqA]3gY\u0016\u001cGOC\u0001\u0017\u0003\u0015\u00198-\u00197b\u0007\u0001\u0001\"!G\u0001\u000e\u0003=\u0011ABT8T_V\u00148-\u001a$jY\u0016\u001c\"!\u0001\u000f\u0011\u0005ei\u0012B\u0001\u0010\u0010\u0005)\u0019v.\u001e:dK\u001aKG.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003a\tqaY8oi\u0016tG/F\u0001$!\r!SeJ\u0007\u0002+%\u0011a%\u0006\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003I!J!!K\u000b\u0003\t\rC\u0017M]\u0001\u0005M&dW-F\u0001-!\tIRF\u0003\u0002/\u001f\u00051aj\u001c$jY\u0016\f1\"[:MS:,'I]3bWR\u0011\u0011\u0007\u000e\t\u0003IIJ!aM\u000b\u0003\u000f\t{w\u000e\\3b]\")Q'\u0002a\u0001m\u0005\u0019\u0011\u000e\u001a=\u0011\u0005\u0011:\u0014B\u0001\u001d\u0016\u0005\rIe\u000e^\u0001\fSN,e\u000eZ(g\u0019&tW\r\u0006\u00022w!)QG\u0002a\u0001m\u0005y\u0011n]*fY\u001a\u001cuN\u001c;bS:,G-F\u00012\u0003\u0019aWM\\4uQV\ta'A\u0005mS:,7i\\;oi\u0006aqN\u001a4tKR$v\u000eT5oKR\u0011ag\u0011\u0005\u0006\t*\u0001\rAN\u0001\u0007_\u001a47/\u001a;\u0002\u00191Lg.\u001a+p\u001f\u001a47/\u001a;\u0015\u0005Y:\u0005\"\u0002%\f\u0001\u00041\u0014!B5oI\u0016D\u0018!\u00027j]\u0016\u001cHcA&U-B\u0019AjT)\u000e\u00035S!AT\u000b\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002Q\u001b\nA\u0011\n^3sCR|'\u000f\u0005\u0002%%&\u00111+\u0006\u0002\b\u001d>$\b.\u001b8h\u0011\u001d)F\u0002%AA\u0002Y\nQa\u001d;beRDqa\u0016\u0007\u0011\u0002\u0003\u0007a'A\u0002f]\u0012\f\u0001\u0002^8TiJLgn\u001a\u000b\u00025B\u00111\fY\u0007\u00029*\u0011QLX\u0001\u0005Y\u0006twMC\u0001`\u0003\u0011Q\u0017M^1\n\u0005\u0005d&AB*ue&tw\r"
)
public final class NoSourceFile {
   public static String toString() {
      return NoSourceFile$.MODULE$.toString();
   }

   public static Iterator lines(final int start, final int end) {
      return NoSourceFile$.MODULE$.lines(start, end);
   }

   public static int lineToOffset(final int index) {
      return NoSourceFile$.MODULE$.lineToOffset(index);
   }

   public static int offsetToLine(final int offset) {
      return NoSourceFile$.MODULE$.offsetToLine(offset);
   }

   public static int lineCount() {
      return NoSourceFile$.MODULE$.lineCount();
   }

   public static int length() {
      return NoSourceFile$.MODULE$.length();
   }

   public static boolean isSelfContained() {
      return NoSourceFile$.MODULE$.isSelfContained();
   }

   public static boolean isEndOfLine(final int idx) {
      return NoSourceFile$.MODULE$.isEndOfLine(idx);
   }

   public static boolean isLineBreak(final int idx) {
      return NoSourceFile$.MODULE$.isLineBreak(idx);
   }

   public static NoFile$ file() {
      return NoSourceFile$.MODULE$.file();
   }

   public static char[] content() {
      return NoSourceFile$.MODULE$.content();
   }

   public static boolean isJava() {
      return NoSourceFile$.MODULE$.isJava();
   }

   public static int lines$default$2() {
      return NoSourceFile$.MODULE$.lines$default$2();
   }

   public static int lines$default$1() {
      return NoSourceFile$.MODULE$.lines$default$1();
   }

   public static int indexWhere$default$3() {
      return NoSourceFile$.MODULE$.indexWhere$default$3();
   }

   public static int indexWhere(final Function1 p, final int start, final int step) {
      return NoSourceFile$.MODULE$.indexWhere(p, start, step);
   }

   public static String sourceAt(final Position pos) {
      return NoSourceFile$.MODULE$.sourceAt(pos);
   }

   public static Option identFrom(final Position pos) {
      return NoSourceFile$.MODULE$.identFrom(pos);
   }

   public static int skipWhitespace(final int offset) {
      return NoSourceFile$.MODULE$.skipWhitespace(offset);
   }

   public static String lineToString(final int index) {
      return NoSourceFile$.MODULE$.lineToString(index);
   }

   public static String path() {
      return NoSourceFile$.MODULE$.path();
   }

   public static Position positionInUltimateSource(final Position position) {
      return NoSourceFile$.MODULE$.positionInUltimateSource(position);
   }

   public static Position position(final int offset) {
      return NoSourceFile$.MODULE$.position(offset);
   }
}

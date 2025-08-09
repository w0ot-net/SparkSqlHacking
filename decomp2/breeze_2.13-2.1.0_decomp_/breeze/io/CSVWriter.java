package breeze.io;

import java.io.File;
import java.io.Writer;
import scala.collection.IterableOnce;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]<Qa\u0004\t\t\u0002U1Qa\u0006\t\t\u0002aAQaH\u0001\u0005\u0002\u0001BQ!I\u0001\u0005\u0002\tBq!U\u0001\u0012\u0002\u0013\u0005!\u000bC\u0004^\u0003E\u0005I\u0011\u0001*\t\u000fy\u000b\u0011\u0013!C\u0001%\")q,\u0001C\u0001A\"91.AI\u0001\n\u0003\u0011\u0006b\u00027\u0002#\u0003%\tA\u0015\u0005\b[\u0006\t\n\u0011\"\u0001S\u0011\u0015q\u0017\u0001\"\u0001p\u0011\u001d!\u0018!%A\u0005\u0002ICq!^\u0001\u0012\u0002\u0013\u0005!\u000bC\u0004w\u0003E\u0005I\u0011\u0001*\u0002\u0013\r\u001bfk\u0016:ji\u0016\u0014(BA\t\u0013\u0003\tIwNC\u0001\u0014\u0003\u0019\u0011'/Z3{K\u000e\u0001\u0001C\u0001\f\u0002\u001b\u0005\u0001\"!C\"T-^\u0013\u0018\u000e^3s'\t\t\u0011\u0004\u0005\u0002\u001b;5\t1DC\u0001\u001d\u0003\u0015\u00198-\u00197b\u0013\tq2D\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003U\tQa\u001e:ji\u0016$ba\t\u00140\u00116{\u0005C\u0001\u000e%\u0013\t)3D\u0001\u0003V]&$\b\"B\u0014\u0004\u0001\u0004A\u0013AB8viB,H\u000f\u0005\u0002*[5\t!F\u0003\u0002\u0012W)\tA&\u0001\u0003kCZ\f\u0017B\u0001\u0018+\u0005\u00199&/\u001b;fe\")\u0001g\u0001a\u0001c\u0005\u0019Q.\u0019;\u0011\u0007IRTH\u0004\u00024q9\u0011AgN\u0007\u0002k)\u0011a\u0007F\u0001\u0007yI|w\u000e\u001e \n\u0003qI!!O\u000e\u0002\u000fA\f7m[1hK&\u00111\b\u0010\u0002\u0010)J\fg/\u001a:tC\ndWm\u00148dK*\u0011\u0011h\u0007\t\u0004ey\u0002\u0015BA =\u0005)Ie\u000eZ3yK\u0012\u001cV-\u001d\t\u0003\u0003\u0016s!AQ\"\u0011\u0005QZ\u0012B\u0001#\u001c\u0003\u0019\u0001&/\u001a3fM&\u0011ai\u0012\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0011[\u0002bB%\u0004!\u0003\u0005\rAS\u0001\ng\u0016\u0004\u0018M]1u_J\u0004\"AG&\n\u00051[\"\u0001B\"iCJDqAT\u0002\u0011\u0002\u0003\u0007!*A\u0003rk>$X\rC\u0004Q\u0007A\u0005\t\u0019\u0001&\u0002\r\u0015\u001c8-\u00199f\u0003=9(/\u001b;fI\u0011,g-Y;mi\u0012\u001aT#A*+\u0005)#6&A+\u0011\u0005Y[V\"A,\u000b\u0005aK\u0016!C;oG\",7m[3e\u0015\tQ6$\u0001\u0006b]:|G/\u0019;j_:L!\u0001X,\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\bxe&$X\r\n3fM\u0006,H\u000e\u001e\u00135\u0003=9(/\u001b;fI\u0011,g-Y;mi\u0012*\u0014!C<sSR,g)\u001b7f)\u0019\u0019\u0013M\u001a5jU\")!m\u0002a\u0001G\u0006!a-\u001b7f!\tIC-\u0003\u0002fU\t!a)\u001b7f\u0011\u0015\u0001t\u00011\u0001h!\r\u0011d(\u0010\u0005\b\u0013\u001e\u0001\n\u00111\u0001K\u0011\u001dqu\u0001%AA\u0002)Cq\u0001U\u0004\u0011\u0002\u0003\u0007!*A\nxe&$XMR5mK\u0012\"WMZ1vYR$3'A\nxe&$XMR5mK\u0012\"WMZ1vYR$C'A\nxe&$XMR5mK\u0012\"WMZ1vYR$S'\u0001\u0005nWN#(/\u001b8h)\u0015\u0001\u0005/\u001d:t\u0011\u0015\u00014\u00021\u0001h\u0011\u001dI5\u0002%AA\u0002)CqAT\u0006\u0011\u0002\u0003\u0007!\nC\u0004Q\u0017A\u0005\t\u0019\u0001&\u0002%5\\7\u000b\u001e:j]\u001e$C-\u001a4bk2$HEM\u0001\u0013[.\u001cFO]5oO\u0012\"WMZ1vYR$3'\u0001\nnWN#(/\u001b8hI\u0011,g-Y;mi\u0012\"\u0004"
)
public final class CSVWriter {
   public static char mkString$default$4() {
      return CSVWriter$.MODULE$.mkString$default$4();
   }

   public static char mkString$default$3() {
      return CSVWriter$.MODULE$.mkString$default$3();
   }

   public static char mkString$default$2() {
      return CSVWriter$.MODULE$.mkString$default$2();
   }

   public static String mkString(final IndexedSeq mat, final char separator, final char quote, final char escape) {
      return CSVWriter$.MODULE$.mkString(mat, separator, quote, escape);
   }

   public static char writeFile$default$5() {
      return CSVWriter$.MODULE$.writeFile$default$5();
   }

   public static char writeFile$default$4() {
      return CSVWriter$.MODULE$.writeFile$default$4();
   }

   public static char writeFile$default$3() {
      return CSVWriter$.MODULE$.writeFile$default$3();
   }

   public static void writeFile(final File file, final IndexedSeq mat, final char separator, final char quote, final char escape) {
      CSVWriter$.MODULE$.writeFile(file, mat, separator, quote, escape);
   }

   public static char write$default$5() {
      return CSVWriter$.MODULE$.write$default$5();
   }

   public static char write$default$4() {
      return CSVWriter$.MODULE$.write$default$4();
   }

   public static char write$default$3() {
      return CSVWriter$.MODULE$.write$default$3();
   }

   public static void write(final Writer output, final IterableOnce mat, final char separator, final char quote, final char escape) {
      CSVWriter$.MODULE$.write(output, mat, separator, quote, escape);
   }
}

package breeze.numerics.constants;

import scala.collection.immutable.HashMap;
import scala.collection.immutable.Vector;
import scala.reflect.ScalaSignature;
import scala.util.matching.Regex;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005<Qa\u0003\u0007\t\u0002M1Q!\u0006\u0007\t\u0002YAQ!H\u0001\u0005\u0002yAQaH\u0001\u0005\u0002\u0001BQ\u0001N\u0001\u0005\u0002UBQ\u0001N\u0001\u0005\u0002mBQ\u0001U\u0001\u0005\u0002ECQ\u0001U\u0001\u0005\u0002MCQ!V\u0001\u0005\u0002YCQ!V\u0001\u0005\u0002aC\u0001bW\u0001\t\u0006\u0004%I\u0001X\u0001\t\t\u0006$\u0018MY1tK*\u0011QBD\u0001\nG>t7\u000f^1oiNT!a\u0004\t\u0002\u00119,X.\u001a:jGNT\u0011!E\u0001\u0007EJ,WM_3\u0004\u0001A\u0011A#A\u0007\u0002\u0019\tAA)\u0019;bE\u0006\u001cXm\u0005\u0002\u0002/A\u0011\u0001dG\u0007\u00023)\t!$A\u0003tG\u0006d\u0017-\u0003\u0002\u001d3\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#A\n\u0002\t1L7\u000f\u001e\u000b\u0002CA\u0019!eJ\u0015\u000e\u0003\rR!\u0001J\u0013\u0002\u0013%lW.\u001e;bE2,'B\u0001\u0014\u001a\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003Q\r\u0012aAV3di>\u0014\bC\u0001\u00162\u001d\tYs\u0006\u0005\u0002-35\tQF\u0003\u0002/%\u00051AH]8pizJ!\u0001M\r\u0002\rA\u0013X\rZ3g\u0013\t\u00114G\u0001\u0004TiJLgn\u001a\u0006\u0003ae\tQA^1mk\u0016$\"AN\u001d\u0011\u0005a9\u0014B\u0001\u001d\u001a\u0005\u0019!u.\u001e2mK\")!\b\u0002a\u0001S\u000511\u000f\u001e:j]\u001e$\"\u0001\u0010$\u0011\t\tjtHN\u0005\u0003}\r\u0012q\u0001S1tQ6\u000b\u0007\u000f\u0005\u0002A\u000b6\t\u0011I\u0003\u0002C\u0007\u0006!A.\u00198h\u0015\u0005!\u0015\u0001\u00026bm\u0006L!AM!\t\u000b\u001d+\u0001\u0019\u0001%\u0002\u000bI,w-\u001a=\u0011\u0005%sU\"\u0001&\u000b\u0005-c\u0015\u0001C7bi\u000eD\u0017N\\4\u000b\u00055K\u0012\u0001B;uS2L!a\u0014&\u0003\u000bI+w-\u001a=\u0002\u0017Ut7-\u001a:uC&tG/\u001f\u000b\u0003mICQA\u000f\u0004A\u0002%\"\"\u0001\u0010+\t\u000b\u001d;\u0001\u0019\u0001%\u0002\tUt\u0017\u000e\u001e\u000b\u0003S]CQA\u000f\u0005A\u0002%\"\"!\u0017.\u0011\t\tjth\u0010\u0005\u0006\u000f&\u0001\r\u0001S\u0001\u000bI\u0006$\u0018MY1tK\"kU#A/\u0011\t\tj\u0014F\u0018\t\u00061}3d'K\u0005\u0003Af\u0011a\u0001V;qY\u0016\u001c\u0004"
)
public final class Database {
   public static HashMap unit(final Regex regex) {
      return Database$.MODULE$.unit(regex);
   }

   public static String unit(final String string) {
      return Database$.MODULE$.unit(string);
   }

   public static HashMap uncertainty(final Regex regex) {
      return Database$.MODULE$.uncertainty(regex);
   }

   public static double uncertainty(final String string) {
      return Database$.MODULE$.uncertainty(string);
   }

   public static HashMap value(final Regex regex) {
      return Database$.MODULE$.value(regex);
   }

   public static double value(final String string) {
      return Database$.MODULE$.value(string);
   }

   public static Vector list() {
      return Database$.MODULE$.list();
   }
}

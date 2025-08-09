package breeze.linalg;

import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y;Qa\u0002\u0005\t\n51Qa\u0004\u0005\t\nAAQaF\u0001\u0005\u0002aAQ!G\u0001\u0005\u0002iAQ\u0001Q\u0001\u0005\u0002\u0005CQaR\u0001\u0005\u0002!CQaT\u0001\u0005\u0002A\u000b!b\u00157jG\u0016,F/\u001b7t\u0015\tI!\"\u0001\u0004mS:\fGn\u001a\u0006\u0002\u0017\u00051!M]3fu\u0016\u001c\u0001\u0001\u0005\u0002\u000f\u00035\t\u0001B\u0001\u0006TY&\u001cW-\u0016;jYN\u001c\"!A\t\u0011\u0005I)R\"A\n\u000b\u0003Q\tQa]2bY\u0006L!AF\n\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\tQ\"\u0001\u0007nCB\u001cu\u000e\\;n]N+\u0017/\u0006\u0002\u001coQ\u0019AdJ\u001b\u0011\u0007u\u0011C%D\u0001\u001f\u0015\ty\u0002%A\u0005j[6,H/\u00192mK*\u0011\u0011eE\u0001\u000bG>dG.Z2uS>t\u0017BA\u0012\u001f\u0005)Ie\u000eZ3yK\u0012\u001cV-\u001d\t\u0003%\u0015J!AJ\n\u0003\u0007%sG\u000fC\u0003)\u0007\u0001\u0007\u0011&\u0001\u0003d_2\u001c\bc\u0001\u00163I9\u00111\u0006\r\b\u0003Y=j\u0011!\f\u0006\u0003]1\ta\u0001\u0010:p_Rt\u0014\"\u0001\u000b\n\u0005E\u001a\u0012a\u00029bG.\fw-Z\u0005\u0003gQ\u00121aU3r\u0015\t\t4\u0003C\u00037\u0007\u0001\u0007A%A\u0003o\u0007>d7\u000fB\u00039\u0007\t\u0007\u0011HA\u0001W#\tQT\b\u0005\u0002\u0013w%\u0011Ah\u0005\u0002\b\u001d>$\b.\u001b8h!\t\u0011b(\u0003\u0002@'\t\u0019\u0011I\\=\u0002\u00135\f\u0007oQ8mk6tWC\u0001\"G)\r!3)\u0012\u0005\u0006\t\u0012\u0001\r\u0001J\u0001\u0004G>d\u0007\"\u0002\u001c\u0005\u0001\u0004!C!\u0002\u001d\u0005\u0005\u0004I\u0014!C7baJ{woU3r+\tIe\nF\u0002\u001d\u00152CQaS\u0003A\u0002%\nAA]8xg\")Q*\u0002a\u0001I\u0005)aNU8xg\u0012)\u0001(\u0002b\u0001s\u00051Q.\u00199S_^,\"!U+\u0015\u0007\u0011\u0012F\u000bC\u0003T\r\u0001\u0007A%A\u0002s_^DQ!\u0014\u0004A\u0002\u0011\"Q\u0001\u000f\u0004C\u0002e\u0002"
)
public final class SliceUtils {
   public static int mapRow(final int row, final int nRows) {
      return SliceUtils$.MODULE$.mapRow(row, nRows);
   }

   public static IndexedSeq mapRowSeq(final Seq rows, final int nRows) {
      return SliceUtils$.MODULE$.mapRowSeq(rows, nRows);
   }

   public static int mapColumn(final int col, final int nCols) {
      return SliceUtils$.MODULE$.mapColumn(col, nCols);
   }

   public static IndexedSeq mapColumnSeq(final Seq cols, final int nCols) {
      return SliceUtils$.MODULE$.mapColumnSeq(cols, nCols);
   }
}

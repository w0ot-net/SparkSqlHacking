package org.apache.spark.ml;

import org.apache.spark.sql.Column;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-;QAB\u0004\t\u0002A1QAE\u0004\t\u0002MAQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0002uAqaM\u0001\u0012\u0002\u0013\u0005A\u0007C\u0003@\u0003\u0011\u0005\u0001)A\u0005gk:\u001cG/[8og*\u0011\u0001\"C\u0001\u0003[2T!AC\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u00051i\u0011AB1qC\u000eDWMC\u0001\u000f\u0003\ry'oZ\u0002\u0001!\t\t\u0012!D\u0001\b\u0005%1WO\\2uS>t7o\u0005\u0002\u0002)A\u0011Q\u0003G\u0007\u0002-)\tq#A\u0003tG\u0006d\u0017-\u0003\u0002\u001a-\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#\u0001\t\u0002\u001fY,7\r^8s?R|w,\u0019:sCf$2A\b\u0013'!\ty\"%D\u0001!\u0015\t\t\u0013\"A\u0002tc2L!a\t\u0011\u0003\r\r{G.^7o\u0011\u0015)3\u00011\u0001\u001f\u0003\u00051\bbB\u0014\u0004!\u0003\u0005\r\u0001K\u0001\u0006IRL\b/\u001a\t\u0003SAr!A\u000b\u0018\u0011\u0005-2R\"\u0001\u0017\u000b\u00055z\u0011A\u0002\u001fs_>$h(\u0003\u00020-\u00051\u0001K]3eK\u001aL!!\r\u001a\u0003\rM#(/\u001b8h\u0015\tyc#A\rwK\u000e$xN]0u_~\u000b'O]1zI\u0011,g-Y;mi\u0012\u0012T#A\u001b+\u0005!24&A\u001c\u0011\u0005ajT\"A\u001d\u000b\u0005iZ\u0014!C;oG\",7m[3e\u0015\tad#\u0001\u0006b]:|G/\u0019;j_:L!AP\u001d\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\bbeJ\f\u0017p\u0018;p?Z,7\r^8s)\tq\u0012\tC\u0003&\u000b\u0001\u0007a\u0004K\u0002\u0002\u0007\"\u0003\"\u0001\u0012$\u000e\u0003\u0015S!\u0001P\u0005\n\u0005\u001d+%!B*j]\u000e,\u0017%A%\u0002\u000bMr\u0003G\f\u0019)\u0007\u0001\u0019\u0005\n"
)
public final class functions {
   public static Column array_to_vector(final Column v) {
      return functions$.MODULE$.array_to_vector(v);
   }

   public static String vector_to_array$default$2() {
      return functions$.MODULE$.vector_to_array$default$2();
   }

   public static Column vector_to_array(final Column v, final String dtype) {
      return functions$.MODULE$.vector_to_array(v, dtype);
   }
}

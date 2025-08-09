package org.apache.spark.streaming;

import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.Logging;
import scala.Option;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e;aAB\u0004\t\u0002\u001dyaAB\t\b\u0011\u00039!\u0003C\u0003 \u0003\u0011\u0005\u0011\u0005C\u0003#\u0003\u0011\u00051\u0005C\u0003#\u0003\u0011\u0005q\u0007C\u0004N\u0003E\u0005I\u0011\u0001(\u0002!\rCWmY6q_&tGOU3bI\u0016\u0014(B\u0001\u0005\n\u0003%\u0019HO]3b[&twM\u0003\u0002\u000b\u0017\u0005)1\u000f]1sW*\u0011A\"D\u0001\u0007CB\f7\r[3\u000b\u00039\t1a\u001c:h!\t\u0001\u0012!D\u0001\b\u0005A\u0019\u0005.Z2la>Lg\u000e\u001e*fC\u0012,'oE\u0002\u0002'e\u0001\"\u0001F\f\u000e\u0003UQ\u0011AF\u0001\u0006g\u000e\fG.Y\u0005\u00031U\u0011a!\u00118z%\u00164\u0007C\u0001\u000e\u001e\u001b\u0005Y\"B\u0001\u000f\n\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001\u0010\u001c\u0005\u001daunZ4j]\u001e\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002\u001f\u0005!!/Z1e)\t!#\u0006E\u0002\u0015K\u001dJ!AJ\u000b\u0003\r=\u0003H/[8o!\t\u0001\u0002&\u0003\u0002*\u000f\tQ1\t[3dWB|\u0017N\u001c;\t\u000b-\u001a\u0001\u0019\u0001\u0017\u0002\u001b\rDWmY6q_&tG\u000fR5s!\tiCG\u0004\u0002/eA\u0011q&F\u0007\u0002a)\u0011\u0011\u0007I\u0001\u0007yI|w\u000e\u001e \n\u0005M*\u0012A\u0002)sK\u0012,g-\u0003\u00026m\t11\u000b\u001e:j]\u001eT!aM\u000b\u0015\u000b\u0011B\u0014h\u0010%\t\u000b-\"\u0001\u0019\u0001\u0017\t\u000bi\"\u0001\u0019A\u001e\u0002\t\r|gN\u001a\t\u0003yuj\u0011!C\u0005\u0003}%\u0011\u0011b\u00159be.\u001cuN\u001c4\t\u000b\u0001#\u0001\u0019A!\u0002\u0015!\fGm\\8q\u0007>tg\r\u0005\u0002C\r6\t1I\u0003\u0002;\t*\u0011QiC\u0001\u0007Q\u0006$wn\u001c9\n\u0005\u001d\u001b%!D\"p]\u001aLw-\u001e:bi&|g\u000eC\u0004J\tA\u0005\t\u0019\u0001&\u0002\u001f%<gn\u001c:f%\u0016\fG-\u0012:s_J\u0004\"\u0001F&\n\u00051+\"a\u0002\"p_2,\u0017M\\\u0001\u000fe\u0016\fG\r\n3fM\u0006,H\u000e\u001e\u00135+\u0005y%F\u0001&QW\u0005\t\u0006C\u0001*X\u001b\u0005\u0019&B\u0001+V\u0003%)hn\u00195fG.,GM\u0003\u0002W+\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005a\u001b&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0002"
)
public final class CheckpointReader {
   public static boolean read$default$4() {
      return CheckpointReader$.MODULE$.read$default$4();
   }

   public static Option read(final String checkpointDir, final SparkConf conf, final Configuration hadoopConf, final boolean ignoreReadError) {
      return CheckpointReader$.MODULE$.read(checkpointDir, conf, hadoopConf, ignoreReadError);
   }

   public static Option read(final String checkpointDir) {
      return CheckpointReader$.MODULE$.read(checkpointDir);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return CheckpointReader$.MODULE$.LogStringContext(sc);
   }
}

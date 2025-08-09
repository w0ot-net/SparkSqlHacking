package org.apache.spark.sql.hive.orc;

import org.apache.hadoop.conf.Configuration;
import org.apache.spark.internal.Logging;
import scala.Option;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005sA\u0002\u0006\f\u0011\u0003iqC\u0002\u0004\u001a\u0017!\u0005QB\u0007\u0005\u0006O\u0005!\t!\u000b\u0005\u0006U\u0005!\ta\u000b\u0005\b-\u0006\t\n\u0011\"\u0001X\u0011\u001d\u0011\u0017!%A\u0005\u0002\rDQ!Z\u0001\u0005\u0002\u0019DQa_\u0001\u0005\u0002qDq!a\u0005\u0002\t\u0003\t)\u0002C\u0004\u00020\u0005!\t!!\r\u0002\u001f=\u00138MR5mK>\u0003XM]1u_JT!\u0001D\u0007\u0002\u0007=\u00148M\u0003\u0002\u000f\u001f\u0005!\u0001.\u001b<f\u0015\t\u0001\u0012#A\u0002tc2T!AE\n\u0002\u000bM\u0004\u0018M]6\u000b\u0005Q)\u0012AB1qC\u000eDWMC\u0001\u0017\u0003\ry'o\u001a\t\u00031\u0005i\u0011a\u0003\u0002\u0010\u001fJ\u001cg)\u001b7f\u001fB,'/\u0019;peN\u0019\u0011aG\u0011\u0011\u0005qyR\"A\u000f\u000b\u0003y\tQa]2bY\u0006L!\u0001I\u000f\u0003\r\u0005s\u0017PU3g!\t\u0011S%D\u0001$\u0015\t!\u0013#\u0001\u0005j]R,'O\\1m\u0013\t13EA\u0004M_\u001e<\u0017N\\4\u0002\rqJg.\u001b;?\u0007\u0001!\u0012aF\u0001\u000eO\u0016$h)\u001b7f%\u0016\fG-\u001a:\u0015\t1Z\u0004*\u0015\t\u000495z\u0013B\u0001\u0018\u001e\u0005\u0019y\u0005\u000f^5p]B\u0011\u0001'O\u0007\u0002c)\u0011AB\r\u0006\u0003gQ\n!![8\u000b\u0005U2\u0014AA9m\u0015\tqqG\u0003\u00029'\u00051\u0001.\u00193p_BL!AO\u0019\u0003\rI+\u0017\rZ3s\u0011\u0015a4\u00011\u0001>\u0003!\u0011\u0017m]3QCRD\u0007C\u0001 F\u001d\ty4\t\u0005\u0002A;5\t\u0011I\u0003\u0002CQ\u00051AH]8pizJ!\u0001R\u000f\u0002\rA\u0013X\rZ3g\u0013\t1uI\u0001\u0004TiJLgn\u001a\u0006\u0003\tvAq!S\u0002\u0011\u0002\u0003\u0007!*\u0001\u0004d_:4\u0017n\u001a\t\u000495Z\u0005C\u0001'P\u001b\u0005i%B\u0001(8\u0003\u0011\u0019wN\u001c4\n\u0005Ak%!D\"p]\u001aLw-\u001e:bi&|g\u000eC\u0004S\u0007A\u0005\t\u0019A*\u0002%%<gn\u001c:f\u0007>\u0014(/\u001e9u\r&dWm\u001d\t\u00039QK!!V\u000f\u0003\u000f\t{w\u000e\\3b]\u00069r-\u001a;GS2,'+Z1eKJ$C-\u001a4bk2$HEM\u000b\u00021*\u0012!*W\u0016\u00025B\u00111\fY\u0007\u00029*\u0011QLX\u0001\nk:\u001c\u0007.Z2lK\u0012T!aX\u000f\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002b9\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002/\u001d,GOR5mKJ+\u0017\rZ3sI\u0011,g-Y;mi\u0012\u001aT#\u00013+\u0005MK\u0016A\u0003:fC\u0012\u001c6\r[3nCR!qM\\={!\raR\u0006\u001b\t\u0003S2l\u0011A\u001b\u0006\u0003W>\tQ\u0001^=qKNL!!\u001c6\u0003\u0015M#(/^2u)f\u0004X\rC\u0003p\r\u0001\u0007\u0001/A\u0003qCRD7\u000fE\u0002rmvr!A\u001d;\u000f\u0005\u0001\u001b\u0018\"\u0001\u0010\n\u0005Ul\u0012a\u00029bG.\fw-Z\u0005\u0003ob\u00141aU3r\u0015\t)X\u0004C\u0003O\r\u0001\u0007!\nC\u0003S\r\u0001\u00071+\u0001\rsK\u0006$wJ]2TG\",W.Y:J]B\u000b'/\u00197mK2$b! @\u0002\u0010\u0005E\u0001cA9wQ\"1qp\u0002a\u0001\u0003\u0003\t\u0011\u0002]1si\u001aKG.Z:\u0011\tE4\u00181\u0001\t\u0005\u0003\u000b\tY!\u0004\u0002\u0002\b)\u0019\u0011\u0011B\u001c\u0002\u0005\u0019\u001c\u0018\u0002BA\u0007\u0003\u000f\u0011!BR5mKN#\u0018\r^;t\u0011\u0015qu\u00011\u0001L\u0011\u0015\u0011v\u00011\u0001T\u0003I9W\r^(cU\u0016\u001cG/\u00138ta\u0016\u001cGo\u001c:\u0015\r\u0005]\u0011\u0011FA\u0017!\u0011aR&!\u0007\u0011\t\u0005m\u0011QE\u0007\u0003\u0003;QA!a\b\u0002\"\u0005yqN\u00196fGRLgn\u001d9fGR|'OC\u0002\u0002$Y\naa]3sI\u0016\u0014\u0014\u0002BA\u0014\u0003;\u0011Qc\u0015;sk\u000e$xJ\u00196fGRLen\u001d9fGR|'\u000f\u0003\u0004\u0002,!\u0001\r!P\u0001\u0005a\u0006$\b\u000eC\u0003O\u0011\u0001\u0007!*\u0001\u0007mSN$xJ]2GS2,7\u000f\u0006\u0004\u00024\u0005m\u0012q\b\t\u0005cZ\f)\u0004\u0005\u0003\u0002\u0006\u0005]\u0012\u0002BA\u001d\u0003\u000f\u0011A\u0001U1uQ\"1\u0011QH\u0005A\u0002u\nq\u0001]1uQN#(\u000fC\u0003O\u0013\u0001\u00071\n"
)
public final class OrcFileOperator {
   public static Seq listOrcFiles(final String pathStr, final Configuration conf) {
      return OrcFileOperator$.MODULE$.listOrcFiles(pathStr, conf);
   }

   public static Option getObjectInspector(final String path, final Option conf) {
      return OrcFileOperator$.MODULE$.getObjectInspector(path, conf);
   }

   public static Seq readOrcSchemasInParallel(final Seq partFiles, final Configuration conf, final boolean ignoreCorruptFiles) {
      return OrcFileOperator$.MODULE$.readOrcSchemasInParallel(partFiles, conf, ignoreCorruptFiles);
   }

   public static Option readSchema(final Seq paths, final Option conf, final boolean ignoreCorruptFiles) {
      return OrcFileOperator$.MODULE$.readSchema(paths, conf, ignoreCorruptFiles);
   }

   public static boolean getFileReader$default$3() {
      return OrcFileOperator$.MODULE$.getFileReader$default$3();
   }

   public static Option getFileReader$default$2() {
      return OrcFileOperator$.MODULE$.getFileReader$default$2();
   }

   public static Option getFileReader(final String basePath, final Option config, final boolean ignoreCorruptFiles) {
      return OrcFileOperator$.MODULE$.getFileReader(basePath, config, ignoreCorruptFiles);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return OrcFileOperator$.MODULE$.LogStringContext(sc);
   }
}

package org.apache.spark.ml.util;

import org.apache.spark.annotation.Unstable;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.sql.SparkSession;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;

@Unstable
@ScalaSignature(
   bytes = "\u0006\u000553qAA\u0002\u0011\u0002G\u0005a\u0002C\u0003\u0016\u0001\u0019\u0005aC\u0001\bN\u0019^\u0013\u0018\u000e^3s\r>\u0014X.\u0019;\u000b\u0005\u0011)\u0011\u0001B;uS2T!AB\u0004\u0002\u00055d'B\u0001\u0005\n\u0003\u0015\u0019\b/\u0019:l\u0015\tQ1\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0019\u0005\u0019qN]4\u0004\u0001M\u0011\u0001a\u0004\t\u0003!Mi\u0011!\u0005\u0006\u0002%\u0005)1oY1mC&\u0011A#\u0005\u0002\u0007\u0003:L(+\u001a4\u0002\u000b]\u0014\u0018\u000e^3\u0015\u000b]QreL\u001d\u0011\u0005AA\u0012BA\r\u0012\u0005\u0011)f.\u001b;\t\u000bm\t\u0001\u0019\u0001\u000f\u0002\tA\fG\u000f\u001b\t\u0003;\u0011r!A\b\u0012\u0011\u0005}\tR\"\u0001\u0011\u000b\u0005\u0005j\u0011A\u0002\u001fs_>$h(\u0003\u0002$#\u00051\u0001K]3eK\u001aL!!\n\u0014\u0003\rM#(/\u001b8h\u0015\t\u0019\u0013\u0003C\u0003)\u0003\u0001\u0007\u0011&A\u0004tKN\u001c\u0018n\u001c8\u0011\u0005)jS\"A\u0016\u000b\u00051:\u0011aA:rY&\u0011af\u000b\u0002\r'B\f'o[*fgNLwN\u001c\u0005\u0006a\u0005\u0001\r!M\u0001\n_B$\u0018n\u001c8NCB\u0004BAM\u001c\u001d95\t1G\u0003\u00025k\u00059Q.\u001e;bE2,'B\u0001\u001c\u0012\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003qM\u00121!T1q\u0011\u0015Q\u0014\u00011\u0001<\u0003\u0015\u0019H/Y4f!\taT(D\u0001\u0006\u0013\tqTAA\u0007QSB,G.\u001b8f'R\fw-\u001a\u0015\u0004\u0003\u00013\u0005CA!E\u001b\u0005\u0011%BA\"\b\u0003)\tgN\\8uCRLwN\\\u0005\u0003\u000b\n\u0013QaU5oG\u0016\f\u0013aR\u0001\u0006e9\"d\u0006\r\u0015\u0003\u0001%\u0003\"!\u0011&\n\u0005-\u0013%\u0001C+ogR\f'\r\\3)\u0007\u0001\u0001e\t"
)
public interface MLWriterFormat {
   void write(final String path, final SparkSession session, final Map optionMap, final PipelineStage stage);
}

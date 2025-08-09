package org.apache.spark.sql.catalyst.analysis;

import org.apache.spark.QueryContext;
import org.apache.spark.SparkThrowableHelper.;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.AnalysisException$;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=3A\u0001C\u0005\u0001-!A1\u0004\u0001B\u0001B\u0003%A\u0004\u0003\u0005*\u0001\t\u0005\t\u0015!\u0003+\u0011!9\u0004A!A!\u0002\u0013A\u0004\u0002C\u001d\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001e\t\u000bu\u0002A\u0011\u0002 \t\u000bu\u0002A\u0011A#\t\u000bu\u0002A\u0011A%\u0003)9{7+^2i\u0013:$W\r_#yG\u0016\u0004H/[8o\u0015\tQ1\"\u0001\u0005b]\u0006d\u0017p]5t\u0015\taQ\"\u0001\u0005dCR\fG._:u\u0015\tqq\"A\u0002tc2T!\u0001E\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005I\u0019\u0012AB1qC\u000eDWMC\u0001\u0015\u0003\ry'oZ\u0002\u0001'\t\u0001q\u0003\u0005\u0002\u001935\tQ\"\u0003\u0002\u001b\u001b\t\t\u0012I\\1msNL7/\u0012=dKB$\u0018n\u001c8\u0002\u000f5,7o]1hKB\u0011QD\n\b\u0003=\u0011\u0002\"a\b\u0012\u000e\u0003\u0001R!!I\u000b\u0002\rq\u0012xn\u001c;?\u0015\u0005\u0019\u0013!B:dC2\f\u0017BA\u0013#\u0003\u0019\u0001&/\u001a3fM&\u0011q\u0005\u000b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0015\u0012\u0013!B2bkN,\u0007cA\u0016-]5\t!%\u0003\u0002.E\t1q\n\u001d;j_:\u0004\"a\f\u001b\u000f\u0005A\u0012dBA\u00102\u0013\u0005\u0019\u0013BA\u001a#\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u000e\u001c\u0003\u0013QC'o\\<bE2,'BA\u001a#\u0003))'O]8s\u00072\f7o\u001d\t\u0004W1b\u0012!E7fgN\fw-\u001a)be\u0006lW\r^3sgB!Qd\u000f\u000f\u001d\u0013\ta\u0004FA\u0002NCB\fa\u0001P5oSRtD#B B\u0005\u000e#\u0005C\u0001!\u0001\u001b\u0005I\u0001\"B\u000e\u0006\u0001\u0004a\u0002\"B\u0015\u0006\u0001\u0004Q\u0003\"B\u001c\u0006\u0001\u0004A\u0004\"B\u001d\u0006\u0001\u0004QD\u0003B G\u000f\"CQa\u000e\u0004A\u0002qAQ!\u000f\u0004A\u0002iBQ!\u000b\u0004A\u0002)\"Ba\u0010&M\u001d\")1j\u0002a\u00019\u0005I\u0011N\u001c3fq:\u000bW.\u001a\u0005\u0006\u001b\u001e\u0001\r\u0001H\u0001\ni\u0006\u0014G.\u001a(b[\u0016DQ!K\u0004A\u0002)\u0002"
)
public class NoSuchIndexException extends AnalysisException {
   private NoSuchIndexException(final String message, final Option cause, final Option errorClass, final Map messageParameters) {
      Option x$5 = AnalysisException$.MODULE$.$lessinit$greater$default$2();
      Option x$6 = AnalysisException$.MODULE$.$lessinit$greater$default$3();
      QueryContext[] x$7 = AnalysisException$.MODULE$.$lessinit$greater$default$7();
      super(message, x$5, x$6, cause, errorClass, messageParameters, x$7);
   }

   public NoSuchIndexException(final String errorClass, final Map messageParameters, final Option cause) {
      this(.MODULE$.getMessage(errorClass, messageParameters), cause, new Some(errorClass), messageParameters);
   }

   public NoSuchIndexException(final String indexName, final String tableName, final Option cause) {
      this("INDEX_NOT_FOUND", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("indexName"), indexName), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("tableName"), tableName)}))), cause);
   }
}

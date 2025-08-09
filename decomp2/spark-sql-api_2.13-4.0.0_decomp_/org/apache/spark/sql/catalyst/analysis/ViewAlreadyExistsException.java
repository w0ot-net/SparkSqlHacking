package org.apache.spark.sql.catalyst.analysis;

import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.catalyst.util.QuotingUtils$;
import org.apache.spark.sql.connector.catalog.Identifier;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m2A!\u0002\u0004\u0001'!A\u0001\u0004\u0001B\u0001B\u0003%\u0011\u0004\u0003\u0005'\u0001\t\u0005\t\u0015!\u0003(\u0011\u0015Q\u0003\u0001\"\u0001,\u0011\u0015Q\u0003\u0001\"\u00011\u0005i1\u0016.Z<BYJ,\u0017\rZ=Fq&\u001cHo]#yG\u0016\u0004H/[8o\u0015\t9\u0001\"\u0001\u0005b]\u0006d\u0017p]5t\u0015\tI!\"\u0001\u0005dCR\fG._:u\u0015\tYA\"A\u0002tc2T!!\u0004\b\u0002\u000bM\u0004\u0018M]6\u000b\u0005=\u0001\u0012AB1qC\u000eDWMC\u0001\u0012\u0003\ry'oZ\u0002\u0001'\t\u0001A\u0003\u0005\u0002\u0016-5\t!\"\u0003\u0002\u0018\u0015\t\t\u0012I\\1msNL7/\u0012=dKB$\u0018n\u001c8\u0002\u0015\u0015\u0014(o\u001c:DY\u0006\u001c8\u000f\u0005\u0002\u001bG9\u00111$\t\t\u00039}i\u0011!\b\u0006\u0003=I\ta\u0001\u0010:p_Rt$\"\u0001\u0011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\tz\u0012A\u0002)sK\u0012,g-\u0003\u0002%K\t11\u000b\u001e:j]\u001eT!AI\u0010\u0002#5,7o]1hKB\u000b'/Y7fi\u0016\u00148\u000f\u0005\u0003\u001bQeI\u0012BA\u0015&\u0005\ri\u0015\r]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00071rs\u0006\u0005\u0002.\u00015\ta\u0001C\u0003\u0019\u0007\u0001\u0007\u0011\u0004C\u0003'\u0007\u0001\u0007q\u0005\u0006\u0002-c!)!\u0007\u0002a\u0001g\u0005)\u0011\u000eZ3oiB\u0011A'O\u0007\u0002k)\u0011agN\u0001\bG\u0006$\u0018\r\\8h\u0015\tA$\"A\u0005d_:tWm\u0019;pe&\u0011!(\u000e\u0002\u000b\u0013\u0012,g\u000e^5gS\u0016\u0014\b"
)
public class ViewAlreadyExistsException extends AnalysisException {
   public ViewAlreadyExistsException(final String errorClass, final Map messageParameters) {
      super(errorClass, messageParameters);
   }

   public ViewAlreadyExistsException(final Identifier ident) {
      this("VIEW_ALREADY_EXISTS", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("relationName"), QuotingUtils$.MODULE$.quoted(ident))}))));
   }
}

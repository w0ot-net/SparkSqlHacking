package org.apache.spark.sql.catalyst.analysis;

import org.apache.spark.QueryContext;
import org.apache.spark.SparkThrowableHelper.;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.AnalysisException$;
import org.apache.spark.sql.catalyst.util.QuotingUtils$;
import org.apache.spark.sql.connector.catalog.Identifier;
import scala.Option;
import scala.Predef;
import scala.Some;
import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054AAC\u0006\u00011!AQ\u0004\u0001B\u0001B\u0003%a\u0004\u0003\u0005,\u0001\t\u0005\t\u0015!\u0003-\u0011!I\u0004A!A!\u0002\u0013Q\u0004\u0002C\u001e\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001f\t\u000b}\u0002A\u0011\u0002!\t\u000b}\u0002A\u0011A$\t\u000b}\u0002A\u0011A&\t\u000b}\u0002A\u0011\u0001)\t\u000b}\u0002A\u0011\u0001,\u0003)9{7+^2i)\u0006\u0014G.Z#yG\u0016\u0004H/[8o\u0015\taQ\"\u0001\u0005b]\u0006d\u0017p]5t\u0015\tqq\"\u0001\u0005dCR\fG._:u\u0015\t\u0001\u0012#A\u0002tc2T!AE\n\u0002\u000bM\u0004\u0018M]6\u000b\u0005Q)\u0012AB1qC\u000eDWMC\u0001\u0017\u0003\ry'oZ\u0002\u0001'\t\u0001\u0011\u0004\u0005\u0002\u001b75\tq\"\u0003\u0002\u001d\u001f\t\t\u0012I\\1msNL7/\u0012=dKB$\u0018n\u001c8\u0002\u000f5,7o]1hKB\u0011q\u0004\u000b\b\u0003A\u0019\u0002\"!\t\u0013\u000e\u0003\tR!aI\f\u0002\rq\u0012xn\u001c;?\u0015\u0005)\u0013!B:dC2\f\u0017BA\u0014%\u0003\u0019\u0001&/\u001a3fM&\u0011\u0011F\u000b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u001d\"\u0013!B2bkN,\u0007cA\u0017/a5\tA%\u0003\u00020I\t1q\n\u001d;j_:\u0004\"!\r\u001c\u000f\u0005I\"dBA\u00114\u0013\u0005)\u0013BA\u001b%\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u000e\u001d\u0003\u0013QC'o\\<bE2,'BA\u001b%\u0003))'O]8s\u00072\f7o\u001d\t\u0004[9r\u0012!E7fgN\fw-\u001a)be\u0006lW\r^3sgB!q$\u0010\u0010\u001f\u0013\tq$FA\u0002NCB\fa\u0001P5oSRtD#B!D\t\u00163\u0005C\u0001\"\u0001\u001b\u0005Y\u0001\"B\u000f\u0006\u0001\u0004q\u0002\"B\u0016\u0006\u0001\u0004a\u0003\"B\u001d\u0006\u0001\u0004Q\u0004\"B\u001e\u0006\u0001\u0004aD\u0003B!I\u0013*CQ!\u000f\u0004A\u0002yAQa\u000f\u0004A\u0002qBQa\u000b\u0004A\u00021\"2!\u0011'O\u0011\u0015iu\u00011\u0001\u001f\u0003\t!'\rC\u0003P\u000f\u0001\u0007a$A\u0003uC\ndW\r\u0006\u0002B#\")!\u000b\u0003a\u0001'\u0006!a.Y7f!\r\tDKH\u0005\u0003+b\u00121aU3r)\t\tu\u000bC\u0003Y\u0013\u0001\u0007\u0011,\u0001\u0006uC\ndW-\u00133f]R\u0004\"AW0\u000e\u0003mS!\u0001X/\u0002\u000f\r\fG/\u00197pO*\u0011alD\u0001\nG>tg.Z2u_JL!\u0001Y.\u0003\u0015%#WM\u001c;jM&,'\u000f"
)
public class NoSuchTableException extends AnalysisException {
   private NoSuchTableException(final String message, final Option cause, final Option errorClass, final Map messageParameters) {
      Option x$5 = AnalysisException$.MODULE$.$lessinit$greater$default$2();
      Option x$6 = AnalysisException$.MODULE$.$lessinit$greater$default$3();
      QueryContext[] x$7 = AnalysisException$.MODULE$.$lessinit$greater$default$7();
      super(message, x$5, x$6, cause, errorClass, messageParameters, x$7);
   }

   public NoSuchTableException(final String errorClass, final Map messageParameters, final Option cause) {
      this(.MODULE$.getMessage(errorClass, messageParameters), cause, new Some(errorClass), messageParameters);
   }

   public NoSuchTableException(final String db, final String table) {
      Map var10002 = scala.Predef..MODULE$.Map();
      ScalaRunTime var10003 = scala.runtime.ScalaRunTime..MODULE$;
      Tuple2[] var10004 = new Tuple2[1];
      Predef.ArrowAssoc var10007 = scala.Predef.ArrowAssoc..MODULE$;
      Object var10008 = scala.Predef..MODULE$.ArrowAssoc("relationName");
      String var10009 = QuotingUtils$.MODULE$.quoteIdentifier(db);
      var10004[0] = var10007.$minus$greater$extension(var10008, var10009 + "." + QuotingUtils$.MODULE$.quoteIdentifier(table));
      this("TABLE_OR_VIEW_NOT_FOUND", (Map)var10002.apply(var10003.wrapRefArray((Object[])var10004)), scala.None..MODULE$);
   }

   public NoSuchTableException(final Seq name) {
      this("TABLE_OR_VIEW_NOT_FOUND", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("relationName"), QuotingUtils$.MODULE$.quoteNameParts(name))}))), scala.None..MODULE$);
   }

   public NoSuchTableException(final Identifier tableIdent) {
      this("TABLE_OR_VIEW_NOT_FOUND", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("relationName"), QuotingUtils$.MODULE$.quoted(tableIdent))}))), scala.None..MODULE$);
   }
}

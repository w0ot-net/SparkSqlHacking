package org.apache.spark.sql.catalyst.analysis;

import org.apache.spark.QueryContext;
import org.apache.spark.SparkThrowableHelper.;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.AnalysisException$;
import org.apache.spark.sql.catalyst.util.AttributeNameParser$;
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
   bytes = "\u0006\u0005\r4Aa\u0003\u0007\u00013!Aa\u0004\u0001B\u0001B\u0003%q\u0004\u0003\u0005-\u0001\t\u0005\t\u0015!\u0003.\u0011!Q\u0004A!A!\u0002\u0013Y\u0004\u0002\u0003\u001f\u0001\u0005\u0003\u0005\u000b\u0011B\u001f\t\u000b\u0001\u0003A\u0011B!\t\u000b\u0001\u0003A\u0011\u0001%\t\u000b\u0001\u0003A\u0011\u0001'\t\u000b\u0001\u0003A\u0011A)\t\u000b\u0001\u0003A\u0011A*\t\u000b\u0001\u0003A\u0011\u0001-\u00037Q\u000b'\r\\3BYJ,\u0017\rZ=Fq&\u001cHo]#yG\u0016\u0004H/[8o\u0015\tia\"\u0001\u0005b]\u0006d\u0017p]5t\u0015\ty\u0001#\u0001\u0005dCR\fG._:u\u0015\t\t\"#A\u0002tc2T!a\u0005\u000b\u0002\u000bM\u0004\u0018M]6\u000b\u0005U1\u0012AB1qC\u000eDWMC\u0001\u0018\u0003\ry'oZ\u0002\u0001'\t\u0001!\u0004\u0005\u0002\u001c95\t\u0001#\u0003\u0002\u001e!\t\t\u0012I\\1msNL7/\u0012=dKB$\u0018n\u001c8\u0002\u000f5,7o]1hKB\u0011\u0001%\u000b\b\u0003C\u001d\u0002\"AI\u0013\u000e\u0003\rR!\u0001\n\r\u0002\rq\u0012xn\u001c;?\u0015\u00051\u0013!B:dC2\f\u0017B\u0001\u0015&\u0003\u0019\u0001&/\u001a3fM&\u0011!f\u000b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005!*\u0013!B2bkN,\u0007c\u0001\u00180c5\tQ%\u0003\u00021K\t1q\n\u001d;j_:\u0004\"AM\u001c\u000f\u0005M*dB\u0001\u00125\u0013\u00051\u0013B\u0001\u001c&\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001O\u001d\u0003\u0013QC'o\\<bE2,'B\u0001\u001c&\u0003))'O]8s\u00072\f7o\u001d\t\u0004]=z\u0012!E7fgN\fw-\u001a)be\u0006lW\r^3sgB!\u0001EP\u0010 \u0013\ty4FA\u0002NCB\fa\u0001P5oSRtD#\u0002\"E\u000b\u001a;\u0005CA\"\u0001\u001b\u0005a\u0001\"\u0002\u0010\u0006\u0001\u0004y\u0002\"\u0002\u0017\u0006\u0001\u0004i\u0003\"\u0002\u001e\u0006\u0001\u0004Y\u0004\"\u0002\u001f\u0006\u0001\u0004iD\u0003\u0002\"J\u0015.CQA\u000f\u0004A\u0002}AQ\u0001\u0010\u0004A\u0002uBQ\u0001\f\u0004A\u00025\"2AQ'P\u0011\u0015qu\u00011\u0001 \u0003\t!'\rC\u0003Q\u000f\u0001\u0007q$A\u0003uC\ndW\r\u0006\u0002C%\")\u0001\u000b\u0003a\u0001?Q\u0011!\t\u0016\u0005\u0006!&\u0001\r!\u0016\t\u0004eY{\u0012BA,:\u0005\r\u0019V-\u001d\u000b\u0003\u0005fCQA\u0017\u0006A\u0002m\u000b!\u0002^1cY\u0016LE-\u001a8u!\ta\u0016-D\u0001^\u0015\tqv,A\u0004dCR\fGn\\4\u000b\u0005\u0001\u0004\u0012!C2p]:,7\r^8s\u0013\t\u0011WL\u0001\u0006JI\u0016tG/\u001b4jKJ\u0004"
)
public class TableAlreadyExistsException extends AnalysisException {
   private TableAlreadyExistsException(final String message, final Option cause, final Option errorClass, final Map messageParameters) {
      Option x$5 = AnalysisException$.MODULE$.$lessinit$greater$default$2();
      Option x$6 = AnalysisException$.MODULE$.$lessinit$greater$default$3();
      QueryContext[] x$7 = AnalysisException$.MODULE$.$lessinit$greater$default$7();
      super(message, x$5, x$6, cause, errorClass, messageParameters, x$7);
   }

   public TableAlreadyExistsException(final String errorClass, final Map messageParameters, final Option cause) {
      this(.MODULE$.getMessage(errorClass, messageParameters), cause, new Some(errorClass), messageParameters);
   }

   public TableAlreadyExistsException(final String db, final String table) {
      Map var10002 = scala.Predef..MODULE$.Map();
      ScalaRunTime var10003 = scala.runtime.ScalaRunTime..MODULE$;
      Tuple2[] var10004 = new Tuple2[1];
      Predef.ArrowAssoc var10007 = scala.Predef.ArrowAssoc..MODULE$;
      Object var10008 = scala.Predef..MODULE$.ArrowAssoc("relationName");
      String var10009 = QuotingUtils$.MODULE$.quoteIdentifier(db);
      var10004[0] = var10007.$minus$greater$extension(var10008, var10009 + "." + QuotingUtils$.MODULE$.quoteIdentifier(table));
      this("TABLE_OR_VIEW_ALREADY_EXISTS", (Map)var10002.apply(var10003.wrapRefArray((Object[])var10004)), scala.None..MODULE$);
   }

   public TableAlreadyExistsException(final String table) {
      this("TABLE_OR_VIEW_ALREADY_EXISTS", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("relationName"), QuotingUtils$.MODULE$.quoteNameParts(AttributeNameParser$.MODULE$.parseAttributeName(table)))}))), scala.None..MODULE$);
   }

   public TableAlreadyExistsException(final Seq table) {
      this("TABLE_OR_VIEW_ALREADY_EXISTS", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("relationName"), QuotingUtils$.MODULE$.quoteNameParts(table))}))), scala.None..MODULE$);
   }

   public TableAlreadyExistsException(final Identifier tableIdent) {
      this("TABLE_OR_VIEW_ALREADY_EXISTS", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("relationName"), QuotingUtils$.MODULE$.quoted(tableIdent))}))), scala.None..MODULE$);
   }
}

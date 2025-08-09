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
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005e3A!\u0003\u0006\u0001/!AA\u0004\u0001B\u0001B\u0003%Q\u0004\u0003\u0005+\u0001\t\u0005\t\u0015!\u0003,\u0011!A\u0004A!A!\u0002\u0013I\u0004\u0002\u0003\u001e\u0001\u0005\u0003\u0005\u000b\u0011B\u001e\t\u000by\u0002A\u0011B \t\u000by\u0002A\u0011\u0001$\t\u000by\u0002A\u0011A%\t\u000by\u0002A\u0011\u0001(\u0003/9{7+^2i\rVt7\r^5p]\u0016C8-\u001a9uS>t'BA\u0006\r\u0003!\tg.\u00197zg&\u001c(BA\u0007\u000f\u0003!\u0019\u0017\r^1msN$(BA\b\u0011\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003#I\tQa\u001d9be.T!a\u0005\u000b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005)\u0012aA8sO\u000e\u00011C\u0001\u0001\u0019!\tI\"$D\u0001\u000f\u0013\tYbBA\tB]\u0006d\u0017p]5t\u000bb\u001cW\r\u001d;j_:\fq!\\3tg\u0006<W\r\u0005\u0002\u001fO9\u0011q$\n\t\u0003A\rj\u0011!\t\u0006\u0003EY\ta\u0001\u0010:p_Rt$\"\u0001\u0013\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0019\u001a\u0013A\u0002)sK\u0012,g-\u0003\u0002)S\t11\u000b\u001e:j]\u001eT!AJ\u0012\u0002\u000b\r\fWo]3\u0011\u00071js&D\u0001$\u0013\tq3E\u0001\u0004PaRLwN\u001c\t\u0003aUr!!M\u001a\u000f\u0005\u0001\u0012\u0014\"\u0001\u0013\n\u0005Q\u001a\u0013a\u00029bG.\fw-Z\u0005\u0003m]\u0012\u0011\u0002\u00165s_^\f'\r\\3\u000b\u0005Q\u001a\u0013AC3se>\u00148\t\\1tgB\u0019A&L\u000f\u0002#5,7o]1hKB\u000b'/Y7fi\u0016\u00148\u000f\u0005\u0003\u001fyui\u0012BA\u001f*\u0005\ri\u0015\r]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000b\u0001\u00135\tR#\u0011\u0005\u0005\u0003Q\"\u0001\u0006\t\u000bq)\u0001\u0019A\u000f\t\u000b)*\u0001\u0019A\u0016\t\u000ba*\u0001\u0019A\u001d\t\u000bi*\u0001\u0019A\u001e\u0015\u0007\u0001;\u0005\nC\u00039\r\u0001\u0007Q\u0004C\u0003;\r\u0001\u00071\bF\u0002A\u00152CQaS\u0004A\u0002u\t!\u0001\u001a2\t\u000b5;\u0001\u0019A\u000f\u0002\t\u0019,hn\u0019\u000b\u0003\u0001>CQ\u0001\u0015\u0005A\u0002E\u000b!\"\u001b3f]RLg-[3s!\t\u0011v+D\u0001T\u0015\t!V+A\u0004dCR\fGn\\4\u000b\u0005Ys\u0011!C2p]:,7\r^8s\u0013\tA6K\u0001\u0006JI\u0016tG/\u001b4jKJ\u0004"
)
public class NoSuchFunctionException extends AnalysisException {
   private NoSuchFunctionException(final String message, final Option cause, final Option errorClass, final Map messageParameters) {
      Option x$5 = AnalysisException$.MODULE$.$lessinit$greater$default$2();
      Option x$6 = AnalysisException$.MODULE$.$lessinit$greater$default$3();
      QueryContext[] x$7 = AnalysisException$.MODULE$.$lessinit$greater$default$7();
      super(message, x$5, x$6, cause, errorClass, messageParameters, x$7);
   }

   public NoSuchFunctionException(final String errorClass, final Map messageParameters) {
      this(.MODULE$.getMessage(errorClass, messageParameters), scala.None..MODULE$, new Some(errorClass), messageParameters);
   }

   public NoSuchFunctionException(final String db, final String func) {
      Map var10002 = scala.Predef..MODULE$.Map();
      ScalaRunTime var10003 = scala.runtime.ScalaRunTime..MODULE$;
      Tuple2[] var10004 = new Tuple2[1];
      Predef.ArrowAssoc var10007 = scala.Predef.ArrowAssoc..MODULE$;
      Object var10008 = scala.Predef..MODULE$.ArrowAssoc("routineName");
      String var10009 = QuotingUtils$.MODULE$.quoteIdentifier(db);
      var10004[0] = var10007.$minus$greater$extension(var10008, var10009 + "." + QuotingUtils$.MODULE$.quoteIdentifier(func));
      this("ROUTINE_NOT_FOUND", (Map)var10002.apply(var10003.wrapRefArray((Object[])var10004)));
   }

   public NoSuchFunctionException(final Identifier identifier) {
      this("ROUTINE_NOT_FOUND", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("routineName"), QuotingUtils$.MODULE$.quoted(identifier))}))));
   }
}

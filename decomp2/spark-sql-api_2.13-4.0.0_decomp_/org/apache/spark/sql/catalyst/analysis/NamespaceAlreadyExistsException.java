package org.apache.spark.sql.catalyst.analysis;

import org.apache.spark.QueryContext;
import org.apache.spark.SparkThrowableHelper.;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.AnalysisException$;
import org.apache.spark.sql.catalyst.util.QuotingUtils$;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053Aa\u0002\u0005\u0001+!A!\u0004\u0001B\u0001B\u0003%1\u0004\u0003\u0005)\u0001\t\u0005\t\u0015!\u0003*\u0011!i\u0003A!A!\u0002\u0013q\u0003\"B\u0019\u0001\t\u0013\u0011\u0004\"B\u0019\u0001\t\u0003A\u0004\"B\u0019\u0001\t\u0003Y$a\b(b[\u0016\u001c\b/Y2f\u00032\u0014X-\u00193z\u000bbL7\u000f^:Fq\u000e,\u0007\u000f^5p]*\u0011\u0011BC\u0001\tC:\fG._:jg*\u00111\u0002D\u0001\tG\u0006$\u0018\r\\=ti*\u0011QBD\u0001\u0004gFd'BA\b\u0011\u0003\u0015\u0019\b/\u0019:l\u0015\t\t\"#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002'\u0005\u0019qN]4\u0004\u0001M\u0011\u0001A\u0006\t\u0003/ai\u0011\u0001D\u0005\u000331\u0011\u0011#\u00118bYf\u001c\u0018n]#yG\u0016\u0004H/[8o\u0003\u001diWm]:bO\u0016\u0004\"\u0001H\u0013\u000f\u0005u\u0019\u0003C\u0001\u0010\"\u001b\u0005y\"B\u0001\u0011\u0015\u0003\u0019a$o\\8u})\t!%A\u0003tG\u0006d\u0017-\u0003\u0002%C\u00051\u0001K]3eK\u001aL!AJ\u0014\u0003\rM#(/\u001b8h\u0015\t!\u0013%\u0001\u0006feJ|'o\u00117bgN\u00042AK\u0016\u001c\u001b\u0005\t\u0013B\u0001\u0017\"\u0005\u0019y\u0005\u000f^5p]\u0006\tR.Z:tC\u001e,\u0007+\u0019:b[\u0016$XM]:\u0011\tqy3dG\u0005\u0003a\u001d\u00121!T1q\u0003\u0019a\u0014N\\5u}Q!1'\u000e\u001c8!\t!\u0004!D\u0001\t\u0011\u0015QB\u00011\u0001\u001c\u0011\u0015AC\u00011\u0001*\u0011\u0015iC\u00011\u0001/)\r\u0019\u0014H\u000f\u0005\u0006Q\u0015\u0001\ra\u0007\u0005\u0006[\u0015\u0001\rA\f\u000b\u0003gqBQ!\u0010\u0004A\u0002y\n\u0011B\\1nKN\u0004\u0018mY3\u0011\u0007)z4$\u0003\u0002AC\t)\u0011I\u001d:bs\u0002"
)
public class NamespaceAlreadyExistsException extends AnalysisException {
   private NamespaceAlreadyExistsException(final String message, final Option errorClass, final Map messageParameters) {
      Option x$4 = AnalysisException$.MODULE$.$lessinit$greater$default$2();
      Option x$5 = AnalysisException$.MODULE$.$lessinit$greater$default$3();
      Option x$6 = AnalysisException$.MODULE$.$lessinit$greater$default$4();
      QueryContext[] x$7 = AnalysisException$.MODULE$.$lessinit$greater$default$7();
      super(message, x$4, x$5, x$6, errorClass, messageParameters, x$7);
   }

   public NamespaceAlreadyExistsException(final String errorClass, final Map messageParameters) {
      this(.MODULE$.getMessage(errorClass, messageParameters), new Some(errorClass), messageParameters);
   }

   public NamespaceAlreadyExistsException(final String[] namespace) {
      this("SCHEMA_ALREADY_EXISTS", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("schemaName"), QuotingUtils$.MODULE$.quoteNameParts(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(namespace).toImmutableArraySeq()))}))));
   }
}

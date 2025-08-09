package org.apache.spark.sql.catalyst.analysis;

import org.apache.spark.SparkThrowableHelper.;
import org.apache.spark.sql.catalyst.util.QuotingUtils$;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M3A!\u0003\u0006\u0001/!AA\u0004\u0001B\u0001B\u0003%Q\u0004\u0003\u0005+\u0001\t\u0005\t\u0015!\u0003,\u0011!A\u0004A!A!\u0002\u0013I\u0004\u0002\u0003\u001e\u0001\u0005\u0003\u0005\u000b\u0011B\u001e\t\u000by\u0002A\u0011B \t\u000by\u0002A\u0011A#\t\u000by\u0002A\u0011\u0001%\t\u000by\u0002A\u0011\u0001(\u000319{7+^2i\u001d\u0006lWm\u001d9bG\u0016,\u0005pY3qi&|gN\u0003\u0002\f\u0019\u0005A\u0011M\\1msNL7O\u0003\u0002\u000e\u001d\u0005A1-\u0019;bYf\u001cHO\u0003\u0002\u0010!\u0005\u00191/\u001d7\u000b\u0005E\u0011\u0012!B:qCJ\\'BA\n\u0015\u0003\u0019\t\u0007/Y2iK*\tQ#A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u00011A\u0011\u0011DG\u0007\u0002\u0015%\u00111D\u0003\u0002\u0018\u001d>\u001cVo\u00195ECR\f'-Y:f\u000bb\u001cW\r\u001d;j_:\fq!\\3tg\u0006<W\r\u0005\u0002\u001fO9\u0011q$\n\t\u0003A\rj\u0011!\t\u0006\u0003EY\ta\u0001\u0010:p_Rt$\"\u0001\u0013\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0019\u001a\u0013A\u0002)sK\u0012,g-\u0003\u0002)S\t11\u000b\u001e:j]\u001eT!AJ\u0012\u0002\u000b\r\fWo]3\u0011\u00071js&D\u0001$\u0013\tq3E\u0001\u0004PaRLwN\u001c\t\u0003aUr!!M\u001a\u000f\u0005\u0001\u0012\u0014\"\u0001\u0013\n\u0005Q\u001a\u0013a\u00029bG.\fw-Z\u0005\u0003m]\u0012\u0011\u0002\u00165s_^\f'\r\\3\u000b\u0005Q\u001a\u0013AC3se>\u00148\t\\1tgB\u0019A&L\u000f\u0002#5,7o]1hKB\u000b'/Y7fi\u0016\u00148\u000f\u0005\u0003\u001fyui\u0012BA\u001f*\u0005\ri\u0015\r]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000b\u0001\u000b%i\u0011#\u0011\u0005e\u0001\u0001\"\u0002\u000f\u0006\u0001\u0004i\u0002\"\u0002\u0016\u0006\u0001\u0004Y\u0003\"\u0002\u001d\u0006\u0001\u0004I\u0004\"\u0002\u001e\u0006\u0001\u0004YDc\u0001!G\u000f\")\u0001H\u0002a\u0001;!)!H\u0002a\u0001wQ\u0011\u0001)\u0013\u0005\u0006\u0015\u001e\u0001\raS\u0001\n]\u0006lWm\u001d9bG\u0016\u00042\u0001\r'\u001e\u0013\tiuGA\u0002TKF$\"\u0001Q(\t\u000b)C\u0001\u0019\u0001)\u0011\u00071\nV$\u0003\u0002SG\t)\u0011I\u001d:bs\u0002"
)
public class NoSuchNamespaceException extends NoSuchDatabaseException {
   private NoSuchNamespaceException(final String message, final Option cause, final Option errorClass, final Map messageParameters) {
      super(message, cause, errorClass, messageParameters);
   }

   public NoSuchNamespaceException(final String errorClass, final Map messageParameters) {
      this(.MODULE$.getMessage(errorClass, messageParameters), scala.None..MODULE$, new Some(errorClass), messageParameters);
   }

   public NoSuchNamespaceException(final Seq namespace) {
      this("SCHEMA_NOT_FOUND", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("schemaName"), QuotingUtils$.MODULE$.quoteNameParts(namespace))}))));
   }

   public NoSuchNamespaceException(final String[] namespace) {
      this("SCHEMA_NOT_FOUND", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("schemaName"), QuotingUtils$.MODULE$.quoteNameParts(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(namespace).toImmutableArraySeq()))}))));
   }
}

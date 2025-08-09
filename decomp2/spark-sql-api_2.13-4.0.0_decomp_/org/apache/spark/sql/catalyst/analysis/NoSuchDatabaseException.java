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
   bytes = "\u0006\u000513A\u0001C\u0005\u0001-!A1\u0004\u0001B\u0001B\u0003%A\u0004\u0003\u0005*\u0001\t\u0005\t\u0015!\u0003+\u0011!9\u0004A!A!\u0002\u0013A\u0004\u0002C\u001d\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001e\t\ru\u0002A\u0011A\u0005?\u0011\u0015i\u0004\u0001\"\u0001F\u0011\u0015i\u0004\u0001\"\u0001J\u0005]qunU;dQ\u0012\u000bG/\u00192bg\u0016,\u0005pY3qi&|gN\u0003\u0002\u000b\u0017\u0005A\u0011M\\1msNL7O\u0003\u0002\r\u001b\u0005A1-\u0019;bYf\u001cHO\u0003\u0002\u000f\u001f\u0005\u00191/\u001d7\u000b\u0005A\t\u0012!B:qCJ\\'B\u0001\n\u0014\u0003\u0019\t\u0007/Y2iK*\tA#A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001/A\u0011\u0001$G\u0007\u0002\u001b%\u0011!$\u0004\u0002\u0012\u0003:\fG._:jg\u0016C8-\u001a9uS>t\u0017aB7fgN\fw-\u001a\t\u0003;\u0019r!A\b\u0013\u0011\u0005}\u0011S\"\u0001\u0011\u000b\u0005\u0005*\u0012A\u0002\u001fs_>$hHC\u0001$\u0003\u0015\u00198-\u00197b\u0013\t)#%\u0001\u0004Qe\u0016$WMZ\u0005\u0003O!\u0012aa\u0015;sS:<'BA\u0013#\u0003\u0015\u0019\u0017-^:f!\rYCFL\u0007\u0002E%\u0011QF\t\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005=\"dB\u0001\u00193\u001d\ty\u0012'C\u0001$\u0013\t\u0019$%A\u0004qC\u000e\\\u0017mZ3\n\u0005U2$!\u0003+ie><\u0018M\u00197f\u0015\t\u0019$%\u0001\u0006feJ|'o\u00117bgN\u00042a\u000b\u0017\u001d\u0003EiWm]:bO\u0016\u0004\u0016M]1nKR,'o\u001d\t\u0005;mbB$\u0003\u0002=Q\t\u0019Q*\u00199\u0002\rqJg.\u001b;?)\u0015y\u0014IQ\"E!\t\u0001\u0005!D\u0001\n\u0011\u0015YR\u00011\u0001\u001d\u0011\u0015IS\u00011\u0001+\u0011\u00159T\u00011\u00019\u0011\u0015IT\u00011\u0001;)\u0011ydi\u0012%\t\u000b]2\u0001\u0019\u0001\u000f\t\u000be2\u0001\u0019\u0001\u001e\t\u000b%2\u0001\u0019\u0001\u0016\u0015\u0005}R\u0005\"B&\b\u0001\u0004a\u0012A\u00013c\u0001"
)
public class NoSuchDatabaseException extends AnalysisException {
   public NoSuchDatabaseException(final String message, final Option cause, final Option errorClass, final Map messageParameters) {
      Option x$5 = AnalysisException$.MODULE$.$lessinit$greater$default$2();
      Option x$6 = AnalysisException$.MODULE$.$lessinit$greater$default$3();
      QueryContext[] x$7 = AnalysisException$.MODULE$.$lessinit$greater$default$7();
      super(message, x$5, x$6, cause, errorClass, messageParameters, x$7);
   }

   public NoSuchDatabaseException(final String errorClass, final Map messageParameters, final Option cause) {
      this(.MODULE$.getMessage(errorClass, messageParameters), cause, new Some(errorClass), messageParameters);
   }

   public NoSuchDatabaseException(final String db) {
      this("SCHEMA_NOT_FOUND", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("schemaName"), QuotingUtils$.MODULE$.quoteIdentifier(db))}))), scala.None..MODULE$);
   }
}

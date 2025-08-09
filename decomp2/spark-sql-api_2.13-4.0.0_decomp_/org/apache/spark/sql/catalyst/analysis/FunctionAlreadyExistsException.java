package org.apache.spark.sql.catalyst.analysis;

import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.catalyst.util.QuotingUtils$;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t3AAB\u0004\u0001)!A\u0011\u0004\u0001B\u0001B\u0003%!\u0004\u0003\u0005(\u0001\t\u0005\t\u0015!\u0003)\u0011\u0015Y\u0003\u0001\"\u0001-\u0011\u0015Y\u0003\u0001\"\u00012\u0011\u0015Y\u0003\u0001\"\u0001>\u0005y1UO\\2uS>t\u0017\t\u001c:fC\u0012LX\t_5tiN,\u0005pY3qi&|gN\u0003\u0002\t\u0013\u0005A\u0011M\\1msNL7O\u0003\u0002\u000b\u0017\u0005A1-\u0019;bYf\u001cHO\u0003\u0002\r\u001b\u0005\u00191/\u001d7\u000b\u00059y\u0011!B:qCJ\\'B\u0001\t\u0012\u0003\u0019\t\u0007/Y2iK*\t!#A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001+A\u0011acF\u0007\u0002\u0017%\u0011\u0001d\u0003\u0002\u0012\u0003:\fG._:jg\u0016C8-\u001a9uS>t\u0017AC3se>\u00148\t\\1tgB\u00111\u0004\n\b\u00039\t\u0002\"!\b\u0011\u000e\u0003yQ!aH\n\u0002\rq\u0012xn\u001c;?\u0015\u0005\t\u0013!B:dC2\f\u0017BA\u0012!\u0003\u0019\u0001&/\u001a3fM&\u0011QE\n\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\r\u0002\u0013!E7fgN\fw-\u001a)be\u0006lW\r^3sgB!1$\u000b\u000e\u001b\u0013\tQcEA\u0002NCB\fa\u0001P5oSRtDcA\u00170aA\u0011a\u0006A\u0007\u0002\u000f!)\u0011d\u0001a\u00015!)qe\u0001a\u0001QQ\u0011QF\r\u0005\u0006g\u0011\u0001\r\u0001N\u0001\tMVt7\r^5p]B\u0019QG\u000f\u000e\u000f\u0005YBdBA\u000f8\u0013\u0005\t\u0013BA\u001d!\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u000f\u001f\u0003\u0007M+\u0017O\u0003\u0002:AQ\u0019QF\u0010!\t\u000b}*\u0001\u0019\u0001\u000e\u0002\u0005\u0011\u0014\u0007\"B!\u0006\u0001\u0004Q\u0012\u0001\u00024v]\u000e\u0004"
)
public class FunctionAlreadyExistsException extends AnalysisException {
   public FunctionAlreadyExistsException(final String errorClass, final Map messageParameters) {
      super(errorClass, messageParameters);
   }

   public FunctionAlreadyExistsException(final Seq function) {
      this("ROUTINE_ALREADY_EXISTS", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("routineName"), QuotingUtils$.MODULE$.quoteNameParts(function)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("newRoutineType"), "routine"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("existingRoutineType"), "routine")}))));
   }

   public FunctionAlreadyExistsException(final String db, final String func) {
      this(new scala.collection.immutable..colon.colon(db, new scala.collection.immutable..colon.colon(func, scala.collection.immutable.Nil..MODULE$)));
   }
}

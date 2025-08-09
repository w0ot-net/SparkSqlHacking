package org.apache.spark.sql.catalyst.analysis;

import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.catalyst.util.QuotingUtils$;
import scala.Predef;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime;

@ScalaSignature(
   bytes = "\u0006\u000512A\u0001B\u0003\u0001%!Aq\u0003\u0001B\u0001B\u0003%\u0001\u0004\u0003\u0005&\u0001\t\u0005\t\u0015!\u0003\u0019\u0011\u00151\u0003\u0001\"\u0001(\u0005\u0001runU;dQB+'/\\1oK:$h)\u001e8di&|g.\u0012=dKB$\u0018n\u001c8\u000b\u0005\u00199\u0011\u0001C1oC2L8/[:\u000b\u0005!I\u0011\u0001C2bi\u0006d\u0017p\u001d;\u000b\u0005)Y\u0011aA:rY*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001\u0019\u0002C\u0001\u000b\u0016\u001b\u0005I\u0011B\u0001\f\n\u0005E\te.\u00197zg&\u001cX\t_2faRLwN\\\u0001\u0003I\n\u0004\"!\u0007\u0012\u000f\u0005i\u0001\u0003CA\u000e\u001f\u001b\u0005a\"BA\u000f\u0012\u0003\u0019a$o\\8u})\tq$A\u0003tG\u0006d\u0017-\u0003\u0002\"=\u00051\u0001K]3eK\u001aL!a\t\u0013\u0003\rM#(/\u001b8h\u0015\t\tc$\u0001\u0003gk:\u001c\u0017A\u0002\u001fj]&$h\bF\u0002)U-\u0002\"!\u000b\u0001\u000e\u0003\u0015AQaF\u0002A\u0002aAQ!J\u0002A\u0002a\u0001"
)
public class NoSuchPermanentFunctionException extends AnalysisException {
   public NoSuchPermanentFunctionException(final String db, final String func) {
      Map var10002 = .MODULE$.Map();
      ScalaRunTime var10003 = scala.runtime.ScalaRunTime..MODULE$;
      Tuple2[] var10004 = new Tuple2[1];
      Predef.ArrowAssoc var10007 = scala.Predef.ArrowAssoc..MODULE$;
      Object var10008 = .MODULE$.ArrowAssoc("routineName");
      String var10009 = QuotingUtils$.MODULE$.quoteIdentifier(db);
      var10004[0] = var10007.$minus$greater$extension(var10008, var10009 + "." + QuotingUtils$.MODULE$.quoteIdentifier(func));
      super("ROUTINE_NOT_FOUND", (Map)var10002.apply(var10003.wrapRefArray((Object[])var10004)));
   }
}

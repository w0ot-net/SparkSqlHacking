package org.apache.spark.sql.catalyst.analysis;

import org.apache.spark.sql.AnalysisException;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%2Aa\u0001\u0003\u0001#!Aa\u0003\u0001B\u0001B\u0003%q\u0003C\u0003%\u0001\u0011\u0005QEA\u000eO_N+8\r\u001b+f[B4UO\\2uS>tW\t_2faRLwN\u001c\u0006\u0003\u000b\u0019\t\u0001\"\u00198bYf\u001c\u0018n\u001d\u0006\u0003\u000f!\t\u0001bY1uC2L8\u000f\u001e\u0006\u0003\u0013)\t1a]9m\u0015\tYA\"A\u0003ta\u0006\u00148N\u0003\u0002\u000e\u001d\u00051\u0011\r]1dQ\u0016T\u0011aD\u0001\u0004_J<7\u0001A\n\u0003\u0001I\u0001\"a\u0005\u000b\u000e\u0003!I!!\u0006\u0005\u0003#\u0005s\u0017\r\\=tSN,\u0005pY3qi&|g.\u0001\u0003gk:\u001c\u0007C\u0001\r\"\u001d\tIr\u0004\u0005\u0002\u001b;5\t1D\u0003\u0002\u001d!\u00051AH]8pizR\u0011AH\u0001\u0006g\u000e\fG.Y\u0005\u0003Au\ta\u0001\u0015:fI\u00164\u0017B\u0001\u0012$\u0005\u0019\u0019FO]5oO*\u0011\u0001%H\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u0019B\u0003CA\u0014\u0001\u001b\u0005!\u0001\"\u0002\f\u0003\u0001\u00049\u0002"
)
public class NoSuchTempFunctionException extends AnalysisException {
   public NoSuchTempFunctionException(final String func) {
      super("ROUTINE_NOT_FOUND", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("routineName"), "`" + func + "`")}))));
   }
}

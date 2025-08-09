package org.apache.spark.sql.internal;

import org.apache.spark.util.SparkClassUtils.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!2\u0001b\u0001\u0003\u0011\u0002\u0007\u0005aA\u0004\u0005\u0006+\u0001!\ta\u0006\u0005\u00067\u0001!\t\u0001\b\u0002\u0018+N,'\u000fR3gS:,GMR;oGRLwN\u001c'jW\u0016T!!\u0002\u0004\u0002\u0011%tG/\u001a:oC2T!a\u0002\u0005\u0002\u0007M\fHN\u0003\u0002\n\u0015\u0005)1\u000f]1sW*\u00111\u0002D\u0001\u0007CB\f7\r[3\u000b\u00035\t1a\u001c:h'\t\u0001q\u0002\u0005\u0002\u0011'5\t\u0011CC\u0001\u0013\u0003\u0015\u00198-\u00197b\u0013\t!\u0012C\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\t\u0001\u0004\u0005\u0002\u00113%\u0011!$\u0005\u0002\u0005+:LG/\u0001\u0003oC6,W#A\u000f\u0011\u0005y)cBA\u0010$!\t\u0001\u0013#D\u0001\"\u0015\t\u0011c#\u0001\u0004=e>|GOP\u0005\u0003IE\ta\u0001\u0015:fI\u00164\u0017B\u0001\u0014(\u0005\u0019\u0019FO]5oO*\u0011A%\u0005"
)
public interface UserDefinedFunctionLike {
   // $FF: synthetic method
   static String name$(final UserDefinedFunctionLike $this) {
      return $this.name();
   }

   default String name() {
      return .MODULE$.getFormattedClassName(this);
   }

   static void $init$(final UserDefinedFunctionLike $this) {
   }
}

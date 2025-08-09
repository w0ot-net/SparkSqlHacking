package org.apache.spark.deploy.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r3a\u0001B\u0003\u0002\u0002\u0015y\u0001\"\u0002\u000b\u0001\t\u00031\u0002\"\u0002\r\u0001\t#J\u0002\"B\u0019\u0001\r#\u0011$AE&jY2\u0014V-];fgR\u001cVM\u001d<mKRT!AB\u0004\u0002\tI,7\u000f\u001e\u0006\u0003\u0011%\ta\u0001Z3qY>L(B\u0001\u0006\f\u0003\u0015\u0019\b/\u0019:l\u0015\taQ\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001d\u0005\u0019qN]4\u0014\u0005\u0001\u0001\u0002CA\t\u0013\u001b\u0005)\u0011BA\n\u0006\u0005-\u0011Vm\u001d;TKJ4H.\u001a;\u0002\rqJg.\u001b;?\u0007\u0001!\u0012a\u0006\t\u0003#\u0001\ta\u0001Z8Q_N$Hc\u0001\u000e!YA\u00111DH\u0007\u00029)\tQ$A\u0003tG\u0006d\u0017-\u0003\u0002 9\t!QK\\5u\u0011\u0015\t#\u00011\u0001#\u0003\u001d\u0011X-];fgR\u0004\"a\t\u0016\u000e\u0003\u0011R!!\n\u0014\u0002\t!$H\u000f\u001d\u0006\u0003O!\nqa]3sm2,GOC\u0001*\u0003\u001dQ\u0017m[1si\u0006L!a\u000b\u0013\u0003%!#H\u000f]*feZdW\r\u001e*fcV,7\u000f\u001e\u0005\u0006[\t\u0001\rAL\u0001\te\u0016\u001c\bo\u001c8tKB\u00111eL\u0005\u0003a\u0011\u00121\u0003\u0013;uaN+'O\u001e7fiJ+7\u000f]8og\u0016\f!\u0002[1oI2,7*\u001b7m)\t\u0019d\u0007\u0005\u0002\u0012i%\u0011Q'\u0002\u0002\u0017\u0017&dGnU;c[&\u001c8/[8o%\u0016\u001c\bo\u001c8tK\")qg\u0001a\u0001q\u0005a1/\u001e2nSN\u001c\u0018n\u001c8JIB\u0011\u0011\b\u0011\b\u0003uy\u0002\"a\u000f\u000f\u000e\u0003qR!!P\u000b\u0002\rq\u0012xn\u001c;?\u0013\tyD$\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u0003\n\u0013aa\u0015;sS:<'BA \u001d\u0001"
)
public abstract class KillRequestServlet extends RestServlet {
   public void doPost(final HttpServletRequest request, final HttpServletResponse response) {
      Option submissionId = this.parseSubmissionId(request.getPathInfo());
      SubmitRestProtocolResponse responseMessage = (SubmitRestProtocolResponse)submissionId.map((submissionIdx) -> this.handleKill(submissionIdx)).getOrElse(() -> {
         response.setStatus(400);
         return this.handleError("Submission ID is missing in kill request.");
      });
      this.sendResponse(responseMessage, response);
   }

   public abstract KillSubmissionResponse handleKill(final String submissionId);

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

package org.apache.spark.deploy.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y2a\u0001B\u0003\u0002\u0002\u0015y\u0001\"\u0002\u000b\u0001\t\u00031\u0002\"\u0002\r\u0001\t#J\u0002\"B\u0019\u0001\r#\u0011$\u0001\u0006*fC\u0012L(PU3rk\u0016\u001cHoU3sm2,GO\u0003\u0002\u0007\u000f\u0005!!/Z:u\u0015\tA\u0011\"\u0001\u0004eKBdw.\u001f\u0006\u0003\u0015-\tQa\u001d9be.T!\u0001D\u0007\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0011aA8sON\u0011\u0001\u0001\u0005\t\u0003#Ii\u0011!B\u0005\u0003'\u0015\u00111BU3tiN+'O\u001e7fi\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u0018!\t\t\u0002!A\u0003e_\u001e+G\u000fF\u0002\u001bA1\u0002\"a\u0007\u0010\u000e\u0003qQ\u0011!H\u0001\u0006g\u000e\fG.Y\u0005\u0003?q\u0011A!\u00168ji\")\u0011E\u0001a\u0001E\u00059!/Z9vKN$\bCA\u0012+\u001b\u0005!#BA\u0013'\u0003\u0011AG\u000f\u001e9\u000b\u0005\u001dB\u0013aB:feZdW\r\u001e\u0006\u0002S\u00059!.Y6beR\f\u0017BA\u0016%\u0005IAE\u000f\u001e9TKJ4H.\u001a;SKF,Xm\u001d;\t\u000b5\u0012\u0001\u0019\u0001\u0018\u0002\u0011I,7\u000f]8og\u0016\u0004\"aI\u0018\n\u0005A\"#a\u0005%uiB\u001cVM\u001d<mKR\u0014Vm\u001d9p]N,\u0017\u0001\u00045b]\u0012dWMU3bIfTH#A\u001a\u0011\u0005E!\u0014BA\u001b\u0006\u00059\u0011V-\u00193zuJ+7\u000f]8og\u0016\u0004"
)
public abstract class ReadyzRequestServlet extends RestServlet {
   public void doGet(final HttpServletRequest request, final HttpServletResponse response) {
      ReadyzResponse readyzResponse = this.handleReadyz();
      Object var10000;
      if (.MODULE$.Boolean2boolean(readyzResponse.success())) {
         response.setStatus(200);
         var10000 = readyzResponse;
      } else {
         response.setStatus(503);
         var10000 = this.handleError("Master is not ready.");
      }

      SubmitRestProtocolResponse responseMessage = (SubmitRestProtocolResponse)var10000;
      this.sendResponse(responseMessage, response);
   }

   public abstract ReadyzResponse handleReadyz();
}

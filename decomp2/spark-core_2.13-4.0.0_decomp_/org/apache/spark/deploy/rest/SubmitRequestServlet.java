package org.apache.spark.deploy.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import scala.io.Source.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%3a\u0001B\u0003\u0002\u0002\u0015y\u0001\"\u0002\u000b\u0001\t\u00031\u0002\"\u0002\r\u0001\t#J\u0002\"B\u0019\u0001\r#\u0011$\u0001F*vE6LGOU3rk\u0016\u001cHoU3sm2,GO\u0003\u0002\u0007\u000f\u0005!!/Z:u\u0015\tA\u0011\"\u0001\u0004eKBdw.\u001f\u0006\u0003\u0015-\tQa\u001d9be.T!\u0001D\u0007\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0011aA8sON\u0011\u0001\u0001\u0005\t\u0003#Ii\u0011!B\u0005\u0003'\u0015\u00111BU3tiN+'O\u001e7fi\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u0018!\t\t\u0002!\u0001\u0004e_B{7\u000f\u001e\u000b\u00045\u0001b\u0003CA\u000e\u001f\u001b\u0005a\"\"A\u000f\u0002\u000bM\u001c\u0017\r\\1\n\u0005}a\"\u0001B+oSRDQ!\t\u0002A\u0002\t\naB]3rk\u0016\u001cHoU3sm2,G\u000f\u0005\u0002$U5\tAE\u0003\u0002&M\u0005!\u0001\u000e\u001e;q\u0015\t9\u0003&A\u0004tKJ4H.\u001a;\u000b\u0003%\nqA[1lCJ$\u0018-\u0003\u0002,I\t\u0011\u0002\n\u001e;q'\u0016\u0014h\u000f\\3u%\u0016\fX/Z:u\u0011\u0015i#\u00011\u0001/\u0003=\u0011Xm\u001d9p]N,7+\u001a:wY\u0016$\bCA\u00120\u0013\t\u0001DEA\nIiR\u00048+\u001a:wY\u0016$(+Z:q_:\u001cX-\u0001\u0007iC:$G.Z*vE6LG\u000f\u0006\u00034m\rC\u0005CA\t5\u0013\t)TA\u0001\u000eTk\nl\u0017\u000e\u001e*fgR\u0004&o\u001c;pG>d'+Z:q_:\u001cX\rC\u00038\u0007\u0001\u0007\u0001(\u0001\nsKF,Xm\u001d;NKN\u001c\u0018mZ3Kg>t\u0007CA\u001dA\u001d\tQd\b\u0005\u0002<95\tAH\u0003\u0002>+\u00051AH]8pizJ!a\u0010\u000f\u0002\rA\u0013X\rZ3g\u0013\t\t%I\u0001\u0004TiJLgn\u001a\u0006\u0003\u007fqAQ\u0001R\u0002A\u0002\u0015\u000baB]3rk\u0016\u001cH/T3tg\u0006<W\r\u0005\u0002\u0012\r&\u0011q)\u0002\u0002\u001a'V\u0014W.\u001b;SKN$\bK]8u_\u000e|G.T3tg\u0006<W\rC\u0003.\u0007\u0001\u0007a\u0006"
)
public abstract class SubmitRequestServlet extends RestServlet {
   public void doPost(final HttpServletRequest requestServlet, final HttpServletResponse responseServlet) {
      Object var10000;
      try {
         String requestMessageJson = .MODULE$.fromInputStream(requestServlet.getInputStream(), scala.io.Codec..MODULE$.fallbackSystemCodec()).mkString();
         SubmitRestProtocolMessage requestMessage = SubmitRestProtocolMessage$.MODULE$.fromJson(requestMessageJson);
         requestMessage.validate();
         var10000 = this.handleSubmit(requestMessageJson, requestMessage, responseServlet);
      } catch (Throwable var10) {
         if (!(var10 instanceof JsonProcessingException ? true : var10 instanceof SubmitRestProtocolException)) {
            throw var10;
         }

         responseServlet.setStatus(400);
         var10000 = this.handleError("Malformed request: " + this.formatException(var10));
      }

      SubmitRestProtocolResponse responseMessage = (SubmitRestProtocolResponse)var10000;
      this.sendResponse(responseMessage, responseServlet);
   }

   public abstract SubmitRestProtocolResponse handleSubmit(final String requestMessageJson, final SubmitRestProtocolMessage requestMessage, final HttpServletResponse responseServlet);
}

package org.apache.spark.deploy.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y2a\u0001B\u0003\u0002\u0002\u0015y\u0001\"\u0002\u000b\u0001\t\u00031\u0002\"\u0002\r\u0001\t#J\u0002\"B\u0019\u0001\r#\u0011$aE\"mK\u0006\u0014(+Z9vKN$8+\u001a:wY\u0016$(B\u0001\u0004\b\u0003\u0011\u0011Xm\u001d;\u000b\u0005!I\u0011A\u00023fa2|\u0017P\u0003\u0002\u000b\u0017\u0005)1\u000f]1sW*\u0011A\"D\u0001\u0007CB\f7\r[3\u000b\u00039\t1a\u001c:h'\t\u0001\u0001\u0003\u0005\u0002\u0012%5\tQ!\u0003\u0002\u0014\u000b\tY!+Z:u'\u0016\u0014h\u000f\\3u\u0003\u0019a\u0014N\\5u}\r\u0001A#A\f\u0011\u0005E\u0001\u0011A\u00023p!>\u001cH\u000fF\u0002\u001bA1\u0002\"a\u0007\u0010\u000e\u0003qQ\u0011!H\u0001\u0006g\u000e\fG.Y\u0005\u0003?q\u0011A!\u00168ji\")\u0011E\u0001a\u0001E\u00059!/Z9vKN$\bCA\u0012+\u001b\u0005!#BA\u0013'\u0003\u0011AG\u000f\u001e9\u000b\u0005\u001dB\u0013aB:feZdW\r\u001e\u0006\u0002S\u00059!.Y6beR\f\u0017BA\u0016%\u0005IAE\u000f\u001e9TKJ4H.\u001a;SKF,Xm\u001d;\t\u000b5\u0012\u0001\u0019\u0001\u0018\u0002\u0011I,7\u000f]8og\u0016\u0004\"aI\u0018\n\u0005A\"#a\u0005%uiB\u001cVM\u001d<mKR\u0014Vm\u001d9p]N,\u0017a\u00035b]\u0012dWm\u00117fCJ$\u0012a\r\t\u0003#QJ!!N\u0003\u0003\u001b\rcW-\u0019:SKN\u0004xN\\:f\u0001"
)
public abstract class ClearRequestServlet extends RestServlet {
   public void doPost(final HttpServletRequest request, final HttpServletResponse response) {
      ClearResponse responseMessage = this.handleClear();
      this.sendResponse(responseMessage, response);
   }

   public abstract ClearResponse handleClear();
}

package org.apache.spark.deploy.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y2a\u0001B\u0003\u0002\u0002\u0015y\u0001\"\u0002\u000b\u0001\t\u00031\u0002\"\u0002\r\u0001\t#J\u0002\"B\u0019\u0001\r#\u0011$!F&jY2\fE\u000e\u001c*fcV,7\u000f^*feZdW\r\u001e\u0006\u0003\r\u001d\tAA]3ti*\u0011\u0001\"C\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005)Y\u0011!B:qCJ\\'B\u0001\u0007\u000e\u0003\u0019\t\u0007/Y2iK*\ta\"A\u0002pe\u001e\u001c\"\u0001\u0001\t\u0011\u0005E\u0011R\"A\u0003\n\u0005M)!a\u0003*fgR\u001cVM\u001d<mKR\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002/A\u0011\u0011\u0003A\u0001\u0007I>\u0004vn\u001d;\u0015\u0007i\u0001C\u0006\u0005\u0002\u001c=5\tADC\u0001\u001e\u0003\u0015\u00198-\u00197b\u0013\tyBD\u0001\u0003V]&$\b\"B\u0011\u0003\u0001\u0004\u0011\u0013a\u0002:fcV,7\u000f\u001e\t\u0003G)j\u0011\u0001\n\u0006\u0003K\u0019\nA\u0001\u001b;ua*\u0011q\u0005K\u0001\bg\u0016\u0014h\u000f\\3u\u0015\u0005I\u0013a\u00026bW\u0006\u0014H/Y\u0005\u0003W\u0011\u0012!\u0003\u0013;uaN+'O\u001e7fiJ+\u0017/^3ti\")QF\u0001a\u0001]\u0005A!/Z:q_:\u001cX\r\u0005\u0002$_%\u0011\u0001\u0007\n\u0002\u0014\u0011R$\boU3sm2,GOU3ta>t7/Z\u0001\u000eQ\u0006tG\r\\3LS2d\u0017\t\u001c7\u0015\u0003M\u0002\"!\u0005\u001b\n\u0005U*!!G&jY2\fE\u000e\\*vE6L7o]5p]J+7\u000f]8og\u0016\u0004"
)
public abstract class KillAllRequestServlet extends RestServlet {
   public void doPost(final HttpServletRequest request, final HttpServletResponse response) {
      KillAllSubmissionResponse responseMessage = this.handleKillAll();
      this.sendResponse(responseMessage, response);
   }

   public abstract KillAllSubmissionResponse handleKillAll();
}

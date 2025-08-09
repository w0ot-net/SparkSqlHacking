package org.apache.spark.status.api.v1;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000513\u0001b\u0002\u0005\u0011\u0002\u0007\u0005\u0001\u0002\u0006\u0005\u00067\u0001!\t!\b\u0005\nC\u0001\u0001\r\u00111A\u0005\u0012\tB\u0011B\u000e\u0001A\u0002\u0003\u0007I\u0011C\u001c\t\u0013i\u0002\u0001\u0019!a\u0001\n#Y\u0004\"C\"\u0001\u0001\u0004\u0005\r\u0011\"\u0005E\u0011\u00151\u0005\u0001\"\u0001H\u0005E\t\u0005/\u001b*fcV,7\u000f^\"p]R,\u0007\u0010\u001e\u0006\u0003\u0013)\t!A^\u0019\u000b\u0005-a\u0011aA1qS*\u0011QBD\u0001\u0007gR\fG/^:\u000b\u0005=\u0001\u0012!B:qCJ\\'BA\t\u0013\u0003\u0019\t\u0007/Y2iK*\t1#A\u0002pe\u001e\u001c\"\u0001A\u000b\u0011\u0005YIR\"A\f\u000b\u0003a\tQa]2bY\u0006L!AG\f\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uI\r\u0001A#\u0001\u0010\u0011\u0005Yy\u0012B\u0001\u0011\u0018\u0005\u0011)f.\u001b;\u0002\u001dM,'O\u001e7fi\u000e{g\u000e^3yiV\t1\u0005\u0005\u0002%S5\tQE\u0003\u0002'O\u000591/\u001a:wY\u0016$(\"\u0001\u0015\u0002\u000f)\f7.\u0019:uC&\u0011!&\n\u0002\u000f'\u0016\u0014h\u000f\\3u\u0007>tG/\u001a=uQ\t\u0011A\u0006\u0005\u0002.i5\taF\u0003\u00020a\u0005!1m\u001c:f\u0015\t\t$'\u0001\u0002sg*\u00111gJ\u0001\u0003oNL!!\u000e\u0018\u0003\u000f\r{g\u000e^3yi\u0006\u00112/\u001a:wY\u0016$8i\u001c8uKb$x\fJ3r)\tq\u0002\bC\u0004:\u0007\u0005\u0005\t\u0019A\u0012\u0002\u0007a$\u0013'A\u0006iiR\u0004(+Z9vKN$X#\u0001\u001f\u0011\u0005u\u0002U\"\u0001 \u000b\u0005}*\u0013\u0001\u00025uiBL!!\u0011 \u0003%!#H\u000f]*feZdW\r\u001e*fcV,7\u000f\u001e\u0015\u0003\t1\nq\u0002\u001b;uaJ+\u0017/^3ti~#S-\u001d\u000b\u0003=\u0015Cq!O\u0003\u0002\u0002\u0003\u0007A(\u0001\u0004vSJ{w\u000e^\u000b\u0002\u0011B\u0011\u0011JS\u0007\u0002\u0011%\u00111\n\u0003\u0002\u0007+&\u0013vn\u001c;"
)
public interface ApiRequestContext {
   ServletContext servletContext();

   void servletContext_$eq(final ServletContext x$1);

   HttpServletRequest httpRequest();

   void httpRequest_$eq(final HttpServletRequest x$1);

   // $FF: synthetic method
   static UIRoot uiRoot$(final ApiRequestContext $this) {
      return $this.uiRoot();
   }

   default UIRoot uiRoot() {
      return UIRootFromServletContext$.MODULE$.getUiRoot(this.servletContext());
   }

   static void $init$(final ApiRequestContext $this) {
   }
}

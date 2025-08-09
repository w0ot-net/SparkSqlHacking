package org.apache.spark.status.api.v1;

import jakarta.servlet.ServletContext;
import org.sparkproject.jetty.server.handler.ContextHandler;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A;aa\u0002\u0005\t\u0002!!bA\u0002\f\t\u0011\u0003Aq\u0003C\u0003\u001f\u0003\u0011\u0005\u0001\u0005C\u0004\"\u0003\t\u0007I\u0011\u0002\u0012\t\r-\n\u0001\u0015!\u0003$\u0011\u0015a\u0013\u0001\"\u0001.\u0011\u0015!\u0015\u0001\"\u0001F\u0003a)\u0016JU8pi\u001a\u0013x.\\*feZdW\r^\"p]R,\u0007\u0010\u001e\u0006\u0003\u0013)\t!A^\u0019\u000b\u0005-a\u0011aA1qS*\u0011QBD\u0001\u0007gR\fG/^:\u000b\u0005=\u0001\u0012!B:qCJ\\'BA\t\u0013\u0003\u0019\t\u0007/Y2iK*\t1#A\u0002pe\u001e\u0004\"!F\u0001\u000e\u0003!\u0011\u0001$V%S_>$hI]8n'\u0016\u0014h\u000f\\3u\u0007>tG/\u001a=u'\t\t\u0001\u0004\u0005\u0002\u001a95\t!DC\u0001\u001c\u0003\u0015\u00198-\u00197b\u0013\ti\"D\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tA#A\u0005biR\u0014\u0018NY;uKV\t1\u0005\u0005\u0002%S5\tQE\u0003\u0002'O\u0005!A.\u00198h\u0015\u0005A\u0013\u0001\u00026bm\u0006L!AK\u0013\u0003\rM#(/\u001b8h\u0003)\tG\u000f\u001e:jEV$X\rI\u0001\ng\u0016$X+\u001b*p_R$2AL\u0019@!\tIr&\u0003\u000215\t!QK\\5u\u0011\u0015\u0011T\u00011\u00014\u00039\u0019wN\u001c;fqRD\u0015M\u001c3mKJ\u0004\"\u0001N\u001f\u000e\u0003UR!AN\u001c\u0002\u000f!\fg\u000e\u001a7fe*\u0011\u0001(O\u0001\u0007g\u0016\u0014h/\u001a:\u000b\u0005iZ\u0014!\u00026fiRL(B\u0001\u001f\u0013\u0003\u001d)7\r\\5qg\u0016L!AP\u001b\u0003\u001d\r{g\u000e^3yi\"\u000bg\u000e\u001a7fe\")\u0001)\u0002a\u0001\u0003\u00061Q/\u001b*p_R\u0004\"!\u0006\"\n\u0005\rC!AB+J%>|G/A\u0005hKR,\u0016NU8piR\u0011\u0011I\u0012\u0005\u0006\u000f\u001a\u0001\r\u0001S\u0001\bG>tG/\u001a=u!\tIe*D\u0001K\u0015\tYE*A\u0004tKJ4H.\u001a;\u000b\u00035\u000bqA[1lCJ$\u0018-\u0003\u0002P\u0015\nq1+\u001a:wY\u0016$8i\u001c8uKb$\b"
)
public final class UIRootFromServletContext {
   public static UIRoot getUiRoot(final ServletContext context) {
      return UIRootFromServletContext$.MODULE$.getUiRoot(context);
   }

   public static void setUiRoot(final ContextHandler contextHandler, final UIRoot uiRoot) {
      UIRootFromServletContext$.MODULE$.setUiRoot(contextHandler, uiRoot);
   }
}

package org.apache.spark.status.api.v1.streaming;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import org.apache.spark.status.api.v1.ApiRequestContext;
import org.apache.spark.status.api.v1.UIRoot;
import scala.reflect.ScalaSignature;

@Path("/v1")
@ScalaSignature(
   bytes = "\u0006\u0005a3Q\u0001B\u0003\u0001\u000fMAQA\b\u0001\u0005\u0002\u0001BQa\t\u0001\u0005\u0002\u0011BQa\t\u0001\u0005\u0002-\u0013q\"\u00119j'R\u0014X-Y7j]\u001e\f\u0005\u000f\u001d\u0006\u0003\r\u001d\t\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u0005!I\u0011A\u0001<2\u0015\tQ1\"A\u0002ba&T!\u0001D\u0007\u0002\rM$\u0018\r^;t\u0015\tqq\"A\u0003ta\u0006\u00148N\u0003\u0002\u0011#\u00051\u0011\r]1dQ\u0016T\u0011AE\u0001\u0004_J<7c\u0001\u0001\u00155A\u0011Q\u0003G\u0007\u0002-)\tq#A\u0003tG\u0006d\u0017-\u0003\u0002\u001a-\t1\u0011I\\=SK\u001a\u0004\"a\u0007\u000f\u000e\u0003\u001dI!!H\u0004\u0003#\u0005\u0003\u0018NU3rk\u0016\u001cHoQ8oi\u0016DH/\u0001\u0004=S:LGOP\u0002\u0001)\u0005\t\u0003C\u0001\u0012\u0001\u001b\u0005)\u0011\u0001E4fiN#(/Z1nS:<'k\\8u)\t)3\u0007E\u0002'[Ar!aJ\u0016\u0011\u0005!2R\"A\u0015\u000b\u0005)z\u0012A\u0002\u001fs_>$h(\u0003\u0002--\u00051\u0001K]3eK\u001aL!AL\u0018\u0003\u000b\rc\u0017m]:\u000b\u000512\u0002C\u0001\u00122\u0013\t\u0011TA\u0001\rBa&\u001cFO]3b[&twMU8piJ+7o\\;sG\u0016DQ\u0001\u000e\u0002A\u0002U\nQ!\u00199q\u0013\u0012\u0004\"A\n\u001c\n\u0005]z#AB*ue&tw\r\u000b\u00034s\r#\u0005C\u0001\u001eB\u001b\u0005Y$B\u0001\u001f>\u0003\t\u00118O\u0003\u0002?\u007f\u0005\u0011qo\u001d\u0006\u0002\u0001\u00069!.Y6beR\f\u0017B\u0001\"<\u0005%\u0001\u0016\r\u001e5QCJ\fW.A\u0003wC2,X-I\u00015Q\u0011\u0011aiQ%\u0011\u0005i:\u0015B\u0001%<\u0005\u0011\u0001\u0016\r\u001e5\"\u0003)\u000ba$\u00199qY&\u001c\u0017\r^5p]N|30\u00199q\u0013\u0012lxf\u001d;sK\u0006l\u0017N\\4\u0015\u0007\u0015be\nC\u00035\u0007\u0001\u0007Q\u0007\u000b\u0003Ms\r#\u0005\"B(\u0004\u0001\u0004)\u0014!C1ui\u0016l\u0007\u000f^%eQ\u0011q\u0015hQ)\"\u0003=CCa\u0001$D'\u0006\nA+\u0001\u0016baBd\u0017nY1uS>t7oL>baBLE-`\u0018|CR$X-\u001c9u\u0013\u0012lxf\u001d;sK\u0006l\u0017N\\4)\t\u000115IV\u0011\u0002/\u0006\u0019qF^\u0019"
)
public class ApiStreamingApp implements ApiRequestContext {
   @Context
   private ServletContext servletContext;
   @Context
   private HttpServletRequest httpRequest;

   public UIRoot uiRoot() {
      return ApiRequestContext.uiRoot$(this);
   }

   public ServletContext servletContext() {
      return this.servletContext;
   }

   public void servletContext_$eq(final ServletContext x$1) {
      this.servletContext = x$1;
   }

   public HttpServletRequest httpRequest() {
      return this.httpRequest;
   }

   public void httpRequest_$eq(final HttpServletRequest x$1) {
      this.httpRequest = x$1;
   }

   @Path("applications/{appId}/streaming")
   public Class getStreamingRoot(@PathParam("appId") final String appId) {
      return ApiStreamingRootResource.class;
   }

   @Path("applications/{appId}/{attemptId}/streaming")
   public Class getStreamingRoot(@PathParam("appId") final String appId, @PathParam("attemptId") final String attemptId) {
      return ApiStreamingRootResource.class;
   }

   public ApiStreamingApp() {
      ApiRequestContext.$init$(this);
   }
}

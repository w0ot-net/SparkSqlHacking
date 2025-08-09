package org.apache.spark.status.api.v1;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import org.apache.spark.package$;
import org.sparkproject.jetty.servlet.ServletContextHandler;
import scala.reflect.ScalaSignature;

@Path("/v1")
@ScalaSignature(
   bytes = "\u0006\u0005=4Q!\u0003\u0006\u0001\u0015YAQ!\t\u0001\u0005\u0002\rBQ!\n\u0001\u0005\u0002\u0019BQa\u0011\u0001\u0005\u0002\u0011CQ\u0001\u0014\u0001\u0005\u00025;aA\u0017\u0006\t\u0002AYfAB\u0005\u000b\u0011\u0003\u0001B\fC\u0003\"\r\u0011\u0005Q\fC\u0003_\r\u0011\u0005qLA\bBa&\u0014vn\u001c;SKN|WO]2f\u0015\tYA\"\u0001\u0002wc)\u0011QBD\u0001\u0004CBL'BA\b\u0011\u0003\u0019\u0019H/\u0019;vg*\u0011\u0011CE\u0001\u0006gB\f'o\u001b\u0006\u0003'Q\ta!\u00199bG\",'\"A\u000b\u0002\u0007=\u0014xmE\u0002\u0001/u\u0001\"\u0001G\u000e\u000e\u0003eQ\u0011AG\u0001\u0006g\u000e\fG.Y\u0005\u00039e\u0011a!\u00118z%\u00164\u0007C\u0001\u0010 \u001b\u0005Q\u0011B\u0001\u0011\u000b\u0005E\t\u0005/\u001b*fcV,7\u000f^\"p]R,\u0007\u0010^\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tA\u0005\u0005\u0002\u001f\u0001\u0005y\u0011\r\u001d9mS\u000e\fG/[8o\u0019&\u001cH\u000fF\u0001(!\rAsF\r\b\u0003S5\u0002\"AK\r\u000e\u0003-R!\u0001\f\u0012\u0002\rq\u0012xn\u001c;?\u0013\tq\u0013$\u0001\u0004Qe\u0016$WMZ\u0005\u0003aE\u0012Qa\u00117bgNT!AL\r\u0011\u0005y\u0019\u0014B\u0001\u001b\u000b\u0005]\t\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8MSN$(+Z:pkJ\u001cW\r\u000b\u0003\u0003m\u0001\u000b\u0005CA\u001c?\u001b\u0005A$BA\u001d;\u0003\t\u00118O\u0003\u0002<y\u0005\u0011qo\u001d\u0006\u0002{\u00059!.Y6beR\f\u0017BA 9\u0005\u0011\u0001\u0016\r\u001e5\u0002\u000bY\fG.^3\"\u0003\t\u000bA\"\u00199qY&\u001c\u0017\r^5p]N\f1\"\u00199qY&\u001c\u0017\r^5p]R\tQ\tE\u0002)_\u0019\u0003\"AH$\n\u0005!S!AF(oK\u0006\u0003\b\u000f\\5dCRLwN\u001c*fg>,(oY3)\t\r1\u0004IS\u0011\u0002\u0017\u0006!\u0012\r\u001d9mS\u000e\fG/[8og>Z\u0018\r\u001d9JIv\fqA^3sg&|g\u000eF\u0001O!\tqr*\u0003\u0002Q\u0015\tYa+\u001a:tS>t\u0017J\u001c4pQ\t!!\u000b\u0005\u00028'&\u0011A\u000b\u000f\u0002\u0004\u000f\u0016#\u0006\u0006\u0002\u00037\u0001Z\u000b\u0013\u0001\u0014\u0015\u0005\u0001Y\u0002\u0005,I\u0001Z\u0003\ryc/M\u0001\u0010\u0003BL'k\\8u%\u0016\u001cx.\u001e:dKB\u0011aDB\n\u0003\r]!\u0012aW\u0001\u0012O\u0016$8+\u001a:wY\u0016$\b*\u00198eY\u0016\u0014HC\u00011k!\t\t\u0007.D\u0001c\u0015\t\u0019G-A\u0004tKJ4H.\u001a;\u000b\u0005\u00154\u0017!\u00026fiRL(BA4\u0015\u0003\u001d)7\r\\5qg\u0016L!!\u001b2\u0003+M+'O\u001e7fi\u000e{g\u000e^3yi\"\u000bg\u000e\u001a7fe\")1\u000e\u0003a\u0001Y\u00061Q/\u001b*p_R\u0004\"AH7\n\u00059T!AB+J%>|G\u000f"
)
public class ApiRootResource implements ApiRequestContext {
   @Context
   private ServletContext servletContext;
   @Context
   private HttpServletRequest httpRequest;

   public static ServletContextHandler getServletHandler(final UIRoot uiRoot) {
      return ApiRootResource$.MODULE$.getServletHandler(uiRoot);
   }

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

   @Path("applications")
   public Class applicationList() {
      return ApplicationListResource.class;
   }

   @Path("applications/{appId}")
   public Class application() {
      return OneApplicationResource.class;
   }

   @GET
   @Path("version")
   public VersionInfo version() {
      return new VersionInfo(package$.MODULE$.SPARK_VERSION());
   }

   public ApiRootResource() {
      ApiRequestContext.$init$(this);
   }
}

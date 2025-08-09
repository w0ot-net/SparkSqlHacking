package org.apache.spark.ui;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.UI$;
import scala.Option;
import scala.Option.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;

@ScalaSignature(
   bytes = "\u0006\u0005\u00193A!\u0002\u0004\u0005\u001f!A\u0001\u0005\u0001B\u0001B\u0003%\u0011\u0005\u0003\u0005&\u0001\t\u0005\t\u0015!\u0003'\u0011\u0015I\u0003\u0001\"\u0001+\u0011\u0015y\u0003\u0001\"\u00111\u0005IAE\u000f\u001e9TK\u000e,(/\u001b;z\r&dG/\u001a:\u000b\u0005\u001dA\u0011AA;j\u0015\tI!\"A\u0003ta\u0006\u00148N\u0003\u0002\f\u0019\u00051\u0011\r]1dQ\u0016T\u0011!D\u0001\u0004_J<7\u0001A\n\u0004\u0001AA\u0002CA\t\u0017\u001b\u0005\u0011\"BA\n\u0015\u0003\u0011a\u0017M\\4\u000b\u0003U\tAA[1wC&\u0011qC\u0005\u0002\u0007\u001f\nTWm\u0019;\u0011\u0005eqR\"\u0001\u000e\u000b\u0005ma\u0012aB:feZdW\r\u001e\u0006\u0002;\u00059!.Y6beR\f\u0017BA\u0010\u001b\u0005\u00191\u0015\u000e\u001c;fe\u0006!1m\u001c8g!\t\u00113%D\u0001\t\u0013\t!\u0003BA\u0005Ta\u0006\u00148nQ8oM\u0006Y1/Z2ve&$\u00180T4s!\t\u0011s%\u0003\u0002)\u0011\ty1+Z2ve&$\u00180T1oC\u001e,'/\u0001\u0004=S:LGO\u0010\u000b\u0004W5r\u0003C\u0001\u0017\u0001\u001b\u00051\u0001\"\u0002\u0011\u0004\u0001\u0004\t\u0003\"B\u0013\u0004\u0001\u00041\u0013\u0001\u00033p\r&dG/\u001a:\u0015\tE:D(\u0011\t\u0003eUj\u0011a\r\u0006\u0002i\u0005)1oY1mC&\u0011ag\r\u0002\u0005+:LG\u000fC\u00039\t\u0001\u0007\u0011(A\u0002sKF\u0004\"!\u0007\u001e\n\u0005mR\"AD*feZdW\r\u001e*fcV,7\u000f\u001e\u0005\u0006{\u0011\u0001\rAP\u0001\u0004e\u0016\u001c\bCA\r@\u0013\t\u0001%DA\bTKJ4H.\u001a;SKN\u0004xN\\:f\u0011\u0015\u0011E\u00011\u0001D\u0003\u0015\u0019\u0007.Y5o!\tIB)\u0003\u0002F5\tYa)\u001b7uKJ\u001c\u0005.Y5o\u0001"
)
public class HttpSecurityFilter implements Filter {
   private final SparkConf conf;
   private final SecurityManager securityMgr;

   public void init(final FilterConfig x$1) throws ServletException {
      super.init(x$1);
   }

   public void destroy() {
      super.destroy();
   }

   public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) {
      Object var4 = new Object();

      try {
         HttpServletRequest hreq = (HttpServletRequest)req;
         HttpServletResponse hres = (HttpServletResponse)res;
         hres.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
         String requestUser = hreq.getRemoteUser();
         String effectiveUser = (String).MODULE$.apply(hreq.getParameter("doAs")).map((proxy) -> {
            if (requestUser == null) {
               if (proxy == null) {
                  return proxy;
               }
            } else if (requestUser.equals(proxy)) {
               return proxy;
            }

            if (!this.securityMgr.checkAdminPermissions(requestUser)) {
               hres.sendError(403, "User " + requestUser + " is not allowed to impersonate others.");
               throw new NonLocalReturnControl.mcV.sp(var4, BoxedUnit.UNIT);
            } else {
               return proxy;
            }
         }).getOrElse(() -> requestUser);
         if (!this.securityMgr.checkUIViewPermissions(effectiveUser)) {
            hres.sendError(403, "User " + effectiveUser + " is not authorized to access this page.");
            return;
         }

         String xFrameOptionsValue = (String)this.conf.getOption("spark.ui.allowFramingFrom").map((uri) -> "ALLOW-FROM " + uri).getOrElse(() -> "SAMEORIGIN");
         hres.setHeader("X-Frame-Options", xFrameOptionsValue);
         hres.setHeader("X-XSS-Protection", (String)this.conf.get(UI$.MODULE$.UI_X_XSS_PROTECTION()));
         if (BoxesRunTime.unboxToBoolean(this.conf.get(UI$.MODULE$.UI_X_CONTENT_TYPE_OPTIONS()))) {
            hres.setHeader("X-Content-Type-Options", "nosniff");
         }

         label32: {
            String var10000 = hreq.getScheme();
            String var10 = "https";
            if (var10000 == null) {
               if (var10 != null) {
                  break label32;
               }
            } else if (!var10000.equals(var10)) {
               break label32;
            }

            ((Option)this.conf.get((ConfigEntry)UI$.MODULE$.UI_STRICT_TRANSPORT_SECURITY())).foreach((x$1) -> {
               $anonfun$doFilter$5(hres, x$1);
               return BoxedUnit.UNIT;
            });
         }

         chain.doFilter(new XssSafeRequest(hreq, effectiveUser), res);
      } catch (NonLocalReturnControl var12) {
         if (var12.key() != var4) {
            throw var12;
         }

         var12.value$mcV$sp();
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$doFilter$5(final HttpServletResponse hres$1, final String x$1) {
      hres$1.setHeader("Strict-Transport-Security", x$1);
   }

   public HttpSecurityFilter(final SparkConf conf, final SecurityManager securityMgr) {
      this.conf = conf;
      this.securityMgr = securityMgr;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

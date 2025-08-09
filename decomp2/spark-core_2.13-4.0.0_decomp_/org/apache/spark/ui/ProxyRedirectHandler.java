package org.apache.spark.ui;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.net.URI;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.server.handler.HandlerWrapper;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000594Aa\u0003\u0007\u0005+!A!\u0005\u0001B\u0001B\u0003%1\u0005C\u00031\u0001\u0011\u0005\u0011\u0007C\u00046\u0001\t\u0007I\u0011\u0002\u001c\t\ry\u0002\u0001\u0015!\u00038\u0011\u0015y\u0004\u0001\"\u0011A\r\u0011q\u0006\u0001B0\t\u0011\r4!\u0011!Q\u0001\n=C\u0001\u0002\u001a\u0004\u0003\u0002\u0003\u0006Ia\u0017\u0005\u0006a\u0019!\t!\u001a\u0005\u0006U\u001a!\te\u001b\u0002\u0015!J|\u00070\u001f*fI&\u0014Xm\u0019;IC:$G.\u001a:\u000b\u00055q\u0011AA;j\u0015\ty\u0001#A\u0003ta\u0006\u00148N\u0003\u0002\u0012%\u00051\u0011\r]1dQ\u0016T\u0011aE\u0001\u0004_J<7\u0001A\n\u0003\u0001Y\u0001\"a\u0006\u0011\u000e\u0003aQ!!\u0007\u000e\u0002\u000f!\fg\u000e\u001a7fe*\u00111\u0004H\u0001\u0007g\u0016\u0014h/\u001a:\u000b\u0005uq\u0012!\u00026fiRL(BA\u0010\u0013\u0003\u001d)7\r\\5qg\u0016L!!\t\r\u0003\u001d!\u000bg\u000e\u001a7fe^\u0013\u0018\r\u001d9fe\u0006Iq\f\u001d:pqf,&/\u001b\t\u0003I5r!!J\u0016\u0011\u0005\u0019JS\"A\u0014\u000b\u0005!\"\u0012A\u0002\u001fs_>$hHC\u0001+\u0003\u0015\u00198-\u00197b\u0013\ta\u0013&\u0001\u0004Qe\u0016$WMZ\u0005\u0003]=\u0012aa\u0015;sS:<'B\u0001\u0017*\u0003\u0019a\u0014N\\5u}Q\u0011!\u0007\u000e\t\u0003g\u0001i\u0011\u0001\u0004\u0005\u0006E\t\u0001\raI\u0001\taJ|\u00070_+sSV\tq\u0007\u0005\u00029{5\t\u0011H\u0003\u0002;w\u0005!A.\u00198h\u0015\u0005a\u0014\u0001\u00026bm\u0006L!AL\u001d\u0002\u0013A\u0014x\u000e_=Ve&\u0004\u0013A\u00025b]\u0012dW\rF\u0003B\u000b\u001ek\u0015\f\u0005\u0002C\u00076\t\u0011&\u0003\u0002ES\t!QK\\5u\u0011\u00151U\u00011\u0001$\u0003\u0019!\u0018M]4fi\")\u0001*\u0002a\u0001\u0013\u0006Y!-Y:f%\u0016\fX/Z:u!\tQ5*D\u0001\u001b\u0013\ta%DA\u0004SKF,Xm\u001d;\t\u000b9+\u0001\u0019A(\u0002\u000fI,\u0017/^3tiB\u0011\u0001kV\u0007\u0002#*\u0011!kU\u0001\u0005QR$\bO\u0003\u0002U+\u000691/\u001a:wY\u0016$(\"\u0001,\u0002\u000f)\f7.\u0019:uC&\u0011\u0001,\u0015\u0002\u0013\u0011R$\boU3sm2,GOU3rk\u0016\u001cH\u000fC\u0003[\u000b\u0001\u00071,\u0001\u0005sKN\u0004xN\\:f!\t\u0001F,\u0003\u0002^#\n\u0019\u0002\n\u001e;q'\u0016\u0014h\u000f\\3u%\u0016\u001c\bo\u001c8tK\ny!+Z:q_:\u001cXm\u0016:baB,'o\u0005\u0002\u0007AB\u0011\u0001+Y\u0005\u0003EF\u0013!\u0004\u0013;uaN+'O\u001e7fiJ+7\u000f]8og\u0016<&/\u00199qKJ\f1A]3r\u0003\r\u0011Xm\u001d\u000b\u0004M\"L\u0007CA4\u0007\u001b\u0005\u0001\u0001\"B2\n\u0001\u0004y\u0005\"\u00023\n\u0001\u0004Y\u0016\u0001D:f]\u0012\u0014V\rZ5sK\u000e$HCA!m\u0011\u0015i'\u00021\u0001$\u0003!awnY1uS>t\u0007"
)
public class ProxyRedirectHandler extends HandlerWrapper {
   private final String org$apache$spark$ui$ProxyRedirectHandler$$proxyUri;

   public String org$apache$spark$ui$ProxyRedirectHandler$$proxyUri() {
      return this.org$apache$spark$ui$ProxyRedirectHandler$$proxyUri;
   }

   public void handle(final String target, final Request baseRequest, final HttpServletRequest request, final HttpServletResponse response) {
      super.handle(target, baseRequest, request, new ResponseWrapper(request, response));
   }

   public ProxyRedirectHandler(final String _proxyUri) {
      this.org$apache$spark$ui$ProxyRedirectHandler$$proxyUri = .MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(_proxyUri), "/");
   }

   private class ResponseWrapper extends HttpServletResponseWrapper {
      private final HttpServletRequest req;
      private final HttpServletResponse res;
      // $FF: synthetic field
      public final ProxyRedirectHandler $outer;

      public void sendRedirect(final String location) {
         String var10000;
         if (location != null) {
            URI target = new URI(location);
            String proxyBase = UIUtils$.MODULE$.uiRoot(this.req);
            String proxyPrefix = .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(proxyBase)) ? this.org$apache$spark$ui$ProxyRedirectHandler$ResponseWrapper$$$outer().org$apache$spark$ui$ProxyRedirectHandler$$proxyUri() + proxyBase : this.org$apache$spark$ui$ProxyRedirectHandler$ResponseWrapper$$$outer().org$apache$spark$ui$ProxyRedirectHandler$$proxyUri();
            var10000 = this.res.encodeURL(proxyPrefix) + target.getPath();
         } else {
            var10000 = null;
         }

         String newTarget = var10000;
         super.sendRedirect(newTarget);
      }

      // $FF: synthetic method
      public ProxyRedirectHandler org$apache$spark$ui$ProxyRedirectHandler$ResponseWrapper$$$outer() {
         return this.$outer;
      }

      public ResponseWrapper(final HttpServletRequest req, final HttpServletResponse res) {
         this.req = req;
         this.res = res;
         if (ProxyRedirectHandler.this == null) {
            throw null;
         } else {
            this.$outer = ProxyRedirectHandler.this;
            super(res);
         }
      }
   }
}

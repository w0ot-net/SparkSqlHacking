package org.apache.spark.ui;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.crypto.SecretKey;
import scala.Option;
import scala.StringContext;
import scala.collection.SeqOps;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005m3A!\u0003\u0006\u0005'!)A\u0005\u0001C\u0001K!9\u0001\u0006\u0001b\u0001\n\u0013I\u0003BB\u0017\u0001A\u0003%!\u0006C\u0004/\u0001\u0001\u0007I\u0011B\u0018\t\u000fa\u0002\u0001\u0019!C\u0005s!1!\t\u0001Q!\nABQa\u0011\u0001\u0005B\u0011CQA\u0013\u0001\u0005B-\u0013\u0011BS,T\r&dG/\u001a:\u000b\u0005-a\u0011AA;j\u0015\tia\"A\u0003ta\u0006\u00148N\u0003\u0002\u0010!\u00051\u0011\r]1dQ\u0016T\u0011!E\u0001\u0004_J<7\u0001A\n\u0004\u0001Qa\u0002CA\u000b\u001b\u001b\u00051\"BA\f\u0019\u0003\u0011a\u0017M\\4\u000b\u0003e\tAA[1wC&\u00111D\u0006\u0002\u0007\u001f\nTWm\u0019;\u0011\u0005u\u0011S\"\u0001\u0010\u000b\u0005}\u0001\u0013aB:feZdW\r\u001e\u0006\u0002C\u00059!.Y6beR\f\u0017BA\u0012\u001f\u0005\u00191\u0015\u000e\u001c;fe\u00061A(\u001b8jiz\"\u0012A\n\t\u0003O\u0001i\u0011AC\u0001\u000e\u0003V#\u0006j\u0014*J5\u0006#\u0016j\u0014(\u0016\u0003)\u0002\"!F\u0016\n\u000512\"AB*ue&tw-\u0001\bB+RCuJU%[\u0003RKuJ\u0014\u0011\u0002\u0007-,\u00170F\u00011!\t\td'D\u00013\u0015\t\u0019D'\u0001\u0004def\u0004Ho\u001c\u0006\u0002k\u0005)!.\u0019<bq&\u0011qG\r\u0002\n'\u0016\u001c'/\u001a;LKf\fqa[3z?\u0012*\u0017\u000f\u0006\u0002;\u0001B\u00111HP\u0007\u0002y)\tQ(A\u0003tG\u0006d\u0017-\u0003\u0002@y\t!QK\\5u\u0011\u001d\tU!!AA\u0002A\n1\u0001\u001f\u00132\u0003\u0011YW-\u001f\u0011\u0002\t%t\u0017\u000e\u001e\u000b\u0003u\u0015CQAR\u0004A\u0002\u001d\u000baaY8oM&<\u0007CA\u000fI\u0013\tIeD\u0001\u0007GS2$XM]\"p]\u001aLw-\u0001\u0005e_\u001aKG\u000e^3s)\u0011QD*\u0015,\t\u000b5C\u0001\u0019\u0001(\u0002\u0007I,\u0017\u000f\u0005\u0002\u001e\u001f&\u0011\u0001K\b\u0002\u000f'\u0016\u0014h\u000f\\3u%\u0016\fX/Z:u\u0011\u0015\u0011\u0006\u00021\u0001T\u0003\r\u0011Xm\u001d\t\u0003;QK!!\u0016\u0010\u0003\u001fM+'O\u001e7fiJ+7\u000f]8og\u0016DQa\u0016\u0005A\u0002a\u000bQa\u00195bS:\u0004\"!H-\n\u0005is\"a\u0003$jYR,'o\u00115bS:\u0004"
)
public class JWSFilter implements Filter {
   private final String AUTHORIZATION = "Authorization";
   private SecretKey key = null;

   public void destroy() {
      super.destroy();
   }

   private String AUTHORIZATION() {
      return this.AUTHORIZATION;
   }

   private SecretKey key() {
      return this.key;
   }

   private void key_$eq(final SecretKey x$1) {
      this.key = x$1;
   }

   public void init(final FilterConfig config) {
      this.key_$eq(Keys.hmacShaKeyFor((byte[])Decoders.BASE64URL.decode(config.getInitParameter("secretKey"))));
   }

   public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) {
      HttpServletRequest hreq = (HttpServletRequest)req;
      HttpServletResponse hres = (HttpServletResponse)res;
      hres.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

      try {
         String header = hreq.getHeader(this.AUTHORIZATION());
         if (header == null) {
            hres.sendError(403, this.AUTHORIZATION() + " header is missing.");
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            if (header != null) {
               Option var9 = (new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Bearer ", ""})))).s().unapplySeq(header);
               if (!var9.isEmpty() && var9.get() != null && ((SeqOps)var9.get()).lengthCompare(1) == 0) {
                  String token = (String)((SeqOps)var9.get()).apply(0);
                  Jws claims = Jwts.parser().verifyWith(this.key()).build().parseSignedClaims(token);
                  chain.doFilter(req, res);
                  BoxedUnit var15 = BoxedUnit.UNIT;
                  return;
               }
            }

            hres.sendError(403, "Malformed " + this.AUTHORIZATION() + " header.");
            BoxedUnit var14 = BoxedUnit.UNIT;
         }
      } catch (JwtException var13) {
         hres.sendError(403, "JWT Validate Fail");
      }

   }
}

package org.apache.spark.status.api.v1;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q2QAB\u0004\u0001\u000fMA\u0001B\u0007\u0001\u0003\u0006\u0004%\t\u0001\b\u0005\tQ\u0001\u0011\t\u0011)A\u0005;!)\u0011\u0006\u0001C\u0001U!9a\u0006\u0001b\u0001\n\u0003y\u0003BB\u001a\u0001A\u0003%\u0001GA\bTS6\u0004H.\u001a#bi\u0016\u0004\u0016M]1n\u0015\tA\u0011\"\u0001\u0002wc)\u0011!bC\u0001\u0004CBL'B\u0001\u0007\u000e\u0003\u0019\u0019H/\u0019;vg*\u0011abD\u0001\u0006gB\f'o\u001b\u0006\u0003!E\ta!\u00199bG\",'\"\u0001\n\u0002\u0007=\u0014xm\u0005\u0002\u0001)A\u0011Q\u0003G\u0007\u0002-)\tq#A\u0003tG\u0006d\u0017-\u0003\u0002\u001a-\t1\u0011I\\=SK\u001a\fQb\u001c:jO&t\u0017\r\u001c,bYV,7\u0001A\u000b\u0002;A\u0011a$\n\b\u0003?\r\u0002\"\u0001\t\f\u000e\u0003\u0005R!AI\u000e\u0002\rq\u0012xn\u001c;?\u0013\t!c#\u0001\u0004Qe\u0016$WMZ\u0005\u0003M\u001d\u0012aa\u0015;sS:<'B\u0001\u0013\u0017\u00039y'/[4j]\u0006dg+\u00197vK\u0002\na\u0001P5oSRtDCA\u0016.!\ta\u0003!D\u0001\b\u0011\u0015Q2\u00011\u0001\u001e\u0003%!\u0018.\\3ti\u0006l\u0007/F\u00011!\t)\u0012'\u0003\u00023-\t!Aj\u001c8h\u0003)!\u0018.\\3ti\u0006l\u0007\u000f\t"
)
public class SimpleDateParam {
   private final String originalValue;
   private final long timestamp;

   public String originalValue() {
      return this.originalValue;
   }

   public long timestamp() {
      return this.timestamp;
   }

   // $FF: synthetic method
   private final long liftedTree1$1(final SimpleDateFormat format$1) {
      long var10000;
      try {
         var10000 = format$1.parse(this.originalValue()).getTime();
      } catch (ParseException var4) {
         SimpleDateFormat gmtDay = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
         gmtDay.setTimeZone(TimeZone.getTimeZone("GMT"));

         try {
            var10000 = gmtDay.parse(this.originalValue()).getTime();
         } catch (ParseException var3) {
            throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity("Couldn't parse date: " + this.originalValue()).build());
         }
      }

      return var10000;
   }

   public SimpleDateParam(final String originalValue) {
      this.originalValue = originalValue;
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz", Locale.US);
      this.timestamp = this.liftedTree1$1(format);
   }
}

package org.apache.spark.ui;

import jakarta.servlet.http.HttpServletRequest;
import org.json4s.JValue;
import org.json4s.JsonAST.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}3aa\u0002\u0005\u0002\u0002)\u0001\u0002\u0002C\f\u0001\u0005\u0003\u0007I\u0011A\r\t\u0011\u0015\u0002!\u00111A\u0005\u0002\u0019B\u0001\u0002\f\u0001\u0003\u0002\u0003\u0006KA\u0007\u0005\u0006[\u0001!\tA\f\u0005\u0006e\u00011\ta\r\u0005\u0006\u001f\u0002!\t\u0001\u0015\u0002\n/\u0016\u0014W+\u0013)bO\u0016T!!\u0003\u0006\u0002\u0005UL'BA\u0006\r\u0003\u0015\u0019\b/\u0019:l\u0015\tia\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001f\u0005\u0019qN]4\u0014\u0005\u0001\t\u0002C\u0001\n\u0016\u001b\u0005\u0019\"\"\u0001\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u0019\"AB!osJ+g-\u0001\u0004qe\u00164\u0017\u000e_\u0002\u0001+\u0005Q\u0002CA\u000e#\u001d\ta\u0002\u0005\u0005\u0002\u001e'5\taD\u0003\u0002 1\u00051AH]8pizJ!!I\n\u0002\rA\u0013X\rZ3g\u0013\t\u0019CE\u0001\u0004TiJLgn\u001a\u0006\u0003CM\t!\u0002\u001d:fM&Dx\fJ3r)\t9#\u0006\u0005\u0002\u0013Q%\u0011\u0011f\u0005\u0002\u0005+:LG\u000fC\u0004,\u0005\u0005\u0005\t\u0019\u0001\u000e\u0002\u0007a$\u0013'A\u0004qe\u00164\u0017\u000e\u001f\u0011\u0002\rqJg.\u001b;?)\ty\u0013\u0007\u0005\u00021\u00015\t\u0001\u0002C\u0003\u0018\t\u0001\u0007!$\u0001\u0004sK:$WM\u001d\u000b\u0003i\r\u00032!\u000e\u001e>\u001d\t1\u0004H\u0004\u0002\u001eo%\tA#\u0003\u0002:'\u00059\u0001/Y2lC\u001e,\u0017BA\u001e=\u0005\r\u0019V-\u001d\u0006\u0003sM\u0001\"AP!\u000e\u0003}R!\u0001Q\n\u0002\u0007alG.\u0003\u0002C\u007f\t!aj\u001c3f\u0011\u0015!U\u00011\u0001F\u0003\u001d\u0011X-];fgR\u0004\"AR'\u000e\u0003\u001dS!\u0001S%\u0002\t!$H\u000f\u001d\u0006\u0003\u0015.\u000bqa]3sm2,GOC\u0001M\u0003\u001dQ\u0017m[1si\u0006L!AT$\u0003%!#H\u000f]*feZdW\r\u001e*fcV,7\u000f^\u0001\u000be\u0016tG-\u001a:Kg>tGCA)_!\t\u00116L\u0004\u0002T1:\u0011AK\u0016\b\u0003;UK\u0011aD\u0005\u0003/:\taA[:p]R\u001a\u0018BA-[\u0003\u001dQ5o\u001c8B'RS!a\u0016\b\n\u0005qk&A\u0002&WC2,XM\u0003\u0002Z5\")AI\u0002a\u0001\u000b\u0002"
)
public abstract class WebUIPage {
   private String prefix;

   public String prefix() {
      return this.prefix;
   }

   public void prefix_$eq(final String x$1) {
      this.prefix = x$1;
   }

   public abstract Seq render(final HttpServletRequest request);

   public JValue renderJson(final HttpServletRequest request) {
      return .MODULE$.JNothing();
   }

   public WebUIPage(final String prefix) {
      this.prefix = prefix;
      super();
   }
}

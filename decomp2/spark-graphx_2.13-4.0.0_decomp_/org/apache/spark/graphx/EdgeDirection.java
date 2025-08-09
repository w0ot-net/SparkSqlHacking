package org.apache.spark.graphx;

import java.io.Serializable;
import scala.MatchError;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=4A\u0001F\u000b\u0001=!A\u0011\u0007\u0001BC\u0002\u0013%!\u0007\u0003\u0005<\u0001\t\u0005\t\u0015!\u00034\u0011\u0015a\u0004\u0001\"\u0003>\u0011\u0015\t\u0005\u0001\"\u0001C\u0011\u0015\u0019\u0005\u0001\"\u0011E\u0011\u0015)\u0005\u0001\"\u0011G\u0011\u0015y\u0005\u0001\"\u0011Q\u000f\u0015!V\u0003#\u0001V\r\u0015!R\u0003#\u0001W\u0011\u0015a\u0014\u0002\"\u0001_\u0011\u001dy\u0016B1A\u0005\u0006\tCa\u0001Y\u0005!\u0002\u001bq\u0004bB1\n\u0005\u0004%)A\u0011\u0005\u0007E&\u0001\u000bQ\u0002 \t\u000f\rL!\u0019!C\u0003\u0005\"1A-\u0003Q\u0001\u000eyBq!Z\u0005C\u0002\u0013\u0015!\t\u0003\u0004g\u0013\u0001\u0006iA\u0010\u0005\bO&\t\t\u0011\"\u0003i\u00055)EmZ3ESJ,7\r^5p]*\u0011acF\u0001\u0007OJ\f\u0007\u000f\u001b=\u000b\u0005aI\u0012!B:qCJ\\'B\u0001\u000e\u001c\u0003\u0019\t\u0007/Y2iK*\tA$A\u0002pe\u001e\u001c\u0001aE\u0002\u0001?\u0015\u0002\"\u0001I\u0012\u000e\u0003\u0005R\u0011AI\u0001\u0006g\u000e\fG.Y\u0005\u0003I\u0005\u0012a!\u00118z%\u00164\u0007C\u0001\u0014/\u001d\t9CF\u0004\u0002)W5\t\u0011F\u0003\u0002+;\u00051AH]8pizJ\u0011AI\u0005\u0003[\u0005\nq\u0001]1dW\u0006<W-\u0003\u00020a\ta1+\u001a:jC2L'0\u00192mK*\u0011Q&I\u0001\u0005]\u0006lW-F\u00014!\t!\u0004H\u0004\u00026mA\u0011\u0001&I\u0005\u0003o\u0005\na\u0001\u0015:fI\u00164\u0017BA\u001d;\u0005\u0019\u0019FO]5oO*\u0011q'I\u0001\u0006]\u0006lW\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005y\u0002\u0005CA \u0001\u001b\u0005)\u0002\"B\u0019\u0004\u0001\u0004\u0019\u0014a\u0002:fm\u0016\u00148/Z\u000b\u0002}\u0005AAo\\*ue&tw\rF\u00014\u0003\u0019)\u0017/^1mgR\u0011qI\u0013\t\u0003A!K!!S\u0011\u0003\u000f\t{w\u000e\\3b]\")1J\u0002a\u0001\u0019\u0006\tq\u000e\u0005\u0002!\u001b&\u0011a*\t\u0002\u0004\u0003:L\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003E\u0003\"\u0001\t*\n\u0005M\u000b#aA%oi\u0006iQ\tZ4f\t&\u0014Xm\u0019;j_:\u0004\"aP\u0005\u0014\u0007%yr\u000b\u0005\u0002Y;6\t\u0011L\u0003\u0002[7\u0006\u0011\u0011n\u001c\u0006\u00029\u0006!!.\u0019<b\u0013\ty\u0013\fF\u0001V\u0003\tIe.A\u0002J]\u0002\n1aT;u\u0003\u0011yU\u000f\u001e\u0011\u0002\r\u0015KG\u000f[3s\u0003\u001d)\u0015\u000e\u001e5fe\u0002\nAAQ8uQ\u0006)!i\u001c;iA\u0005aqO]5uKJ+\u0007\u000f\\1dKR\t\u0011\u000e\u0005\u0002k[6\t1N\u0003\u0002m7\u0006!A.\u00198h\u0013\tq7N\u0001\u0004PE*,7\r\u001e"
)
public class EdgeDirection implements Serializable {
   private final String name;

   public static EdgeDirection Both() {
      return EdgeDirection$.MODULE$.Both();
   }

   public static EdgeDirection Either() {
      return EdgeDirection$.MODULE$.Either();
   }

   public static EdgeDirection Out() {
      return EdgeDirection$.MODULE$.Out();
   }

   public static EdgeDirection In() {
      return EdgeDirection$.MODULE$.In();
   }

   private String name() {
      return this.name;
   }

   public EdgeDirection reverse() {
      EdgeDirection var10000 = EdgeDirection$.MODULE$.In();
      if (var10000 == null) {
         if (this == null) {
            return EdgeDirection$.MODULE$.Out();
         }
      } else if (var10000.equals(this)) {
         return EdgeDirection$.MODULE$.Out();
      }

      var10000 = EdgeDirection$.MODULE$.Out();
      if (var10000 == null) {
         if (this == null) {
            return EdgeDirection$.MODULE$.In();
         }
      } else if (var10000.equals(this)) {
         return EdgeDirection$.MODULE$.In();
      }

      var10000 = EdgeDirection$.MODULE$.Either();
      if (var10000 == null) {
         if (this == null) {
            return EdgeDirection$.MODULE$.Either();
         }
      } else if (var10000.equals(this)) {
         return EdgeDirection$.MODULE$.Either();
      }

      var10000 = EdgeDirection$.MODULE$.Both();
      if (var10000 == null) {
         if (this == null) {
            return EdgeDirection$.MODULE$.Both();
         }
      } else if (var10000.equals(this)) {
         return EdgeDirection$.MODULE$.Both();
      }

      throw new MatchError(this);
   }

   public String toString() {
      return "EdgeDirection." + this.name();
   }

   public boolean equals(final Object o) {
      if (!(o instanceof EdgeDirection var4)) {
         return false;
      } else {
         boolean var6;
         label30: {
            String var10000 = var4.name();
            String var5 = this.name();
            if (var10000 == null) {
               if (var5 == null) {
                  break label30;
               }
            } else if (var10000.equals(var5)) {
               break label30;
            }

            var6 = false;
            return var6;
         }

         var6 = true;
         return var6;
      }
   }

   public int hashCode() {
      return this.name().hashCode();
   }

   public EdgeDirection(final String name) {
      this.name = name;
   }
}

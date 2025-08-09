package org.apache.spark.resource;

import java.io.Serializable;
import org.apache.spark.annotation.Evolving;
import org.json4s.JValue;
import scala.Predef.;
import scala.collection.immutable.ArraySeq;
import scala.reflect.ScalaSignature;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005}4A!\u0005\n\u00017!Aa\u0006\u0001BC\u0002\u0013\u0005q\u0006\u0003\u00059\u0001\t\u0005\t\u0015!\u00031\u0011!I\u0004A!b\u0001\n\u0003Q\u0004\u0002\u0003 \u0001\u0005\u0003\u0005\u000b\u0011B\u001e\t\u000b}\u0002A\u0011\u0001!\t\u000b\u0015\u0003A\u0011\t$\t\u000b\u001d\u0003A\u0011\t%\t\u000bE\u0003A\u0011\t*\t\u000bY\u0003AQA,\b\r\u0015\u0014\u0002\u0012\u0001\u000bg\r\u0019\t\"\u0003#\u0001\u0015O\")qh\u0003C\u0001_\"A\u0001o\u0003EC\u0002\u0013%q\u0006C\u0003r\u0017\u0011\u0005!\u000fC\u0003r\u0017\u0011\u0005Q\u000fC\u0004x\u0017\u0005\u0005I\u0011\u0002=\u0003'I+7o\\;sG\u0016LeNZ8s[\u0006$\u0018n\u001c8\u000b\u0005M!\u0012\u0001\u0003:fg>,(oY3\u000b\u0005U1\u0012!B:qCJ\\'BA\f\u0019\u0003\u0019\t\u0007/Y2iK*\t\u0011$A\u0002pe\u001e\u001c\u0001aE\u0002\u00019\t\u0002\"!\b\u0011\u000e\u0003yQ\u0011aH\u0001\u0006g\u000e\fG.Y\u0005\u0003Cy\u0011a!\u00118z%\u00164\u0007CA\u0012,\u001d\t!\u0013F\u0004\u0002&Q5\taE\u0003\u0002(5\u00051AH]8pizJ\u0011aH\u0005\u0003Uy\tq\u0001]1dW\u0006<W-\u0003\u0002-[\ta1+\u001a:jC2L'0\u00192mK*\u0011!FH\u0001\u0005]\u0006lW-F\u00011!\t\tTG\u0004\u00023gA\u0011QEH\u0005\u0003iy\ta\u0001\u0015:fI\u00164\u0017B\u0001\u001c8\u0005\u0019\u0019FO]5oO*\u0011AGH\u0001\u0006]\u0006lW\rI\u0001\nC\u0012$'/Z:tKN,\u0012a\u000f\t\u0004;q\u0002\u0014BA\u001f\u001f\u0005\u0015\t%O]1z\u0003)\tG\r\u001a:fgN,7\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007\u0005\u001bE\t\u0005\u0002C\u00015\t!\u0003C\u0003/\u000b\u0001\u0007\u0001\u0007C\u0003:\u000b\u0001\u00071(\u0001\u0005u_N#(/\u001b8h)\u0005\u0001\u0014AB3rk\u0006d7\u000f\u0006\u0002J\u0019B\u0011QDS\u0005\u0003\u0017z\u0011qAQ8pY\u0016\fg\u000eC\u0003N\u000f\u0001\u0007a*A\u0002pE*\u0004\"!H(\n\u0005As\"aA!os\u0006A\u0001.Y:i\u0007>$W\rF\u0001T!\tiB+\u0003\u0002V=\t\u0019\u0011J\u001c;\u0002\rQ|'j]8o)\u0005A\u0006CA-]\u001b\u0005Q&BA.\u0019\u0003\u0019Q7o\u001c85g&\u0011QL\u0017\u0002\u0007\u0015Z\u000bG.^3)\u0005\u0001y\u0006C\u00011d\u001b\u0005\t'B\u00012\u0015\u0003)\tgN\\8uCRLwN\\\u0005\u0003I\u0006\u0014\u0001\"\u0012<pYZLgnZ\u0001\u0014%\u0016\u001cx.\u001e:dK&sgm\u001c:nCRLwN\u001c\t\u0003\u0005.\u00192a\u0003\u000fi!\tIg.D\u0001k\u0015\tYG.\u0001\u0002j_*\tQ.\u0001\u0003kCZ\f\u0017B\u0001\u0017k)\u00051\u0017aC3yC6\u0004H.\u001a&t_:\f\u0011\u0002]1sg\u0016T5o\u001c8\u0015\u0005\u0005\u001b\b\"\u0002;\u000f\u0001\u0004\u0001\u0014\u0001\u00026t_:$\"!\u0011<\t\u000bQ|\u0001\u0019\u0001-\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0003e\u0004\"A_?\u000e\u0003mT!\u0001 7\u0002\t1\fgnZ\u0005\u0003}n\u0014aa\u00142kK\u000e$\b"
)
public class ResourceInformation implements Serializable {
   private final String name;
   private final String[] addresses;

   public static ResourceInformation parseJson(final JValue json) {
      return ResourceInformation$.MODULE$.parseJson(json);
   }

   public static ResourceInformation parseJson(final String json) {
      return ResourceInformation$.MODULE$.parseJson(json);
   }

   public String name() {
      return this.name;
   }

   public String[] addresses() {
      return this.addresses;
   }

   public String toString() {
      String var10000 = this.name();
      return "[name: " + var10000 + ", addresses: " + .MODULE$.wrapRefArray((Object[])this.addresses()).mkString(",") + "]";
   }

   public boolean equals(final Object obj) {
      if (!(obj instanceof ResourceInformation var4)) {
         return false;
      } else {
         boolean var10;
         label48: {
            label42: {
               Class var10000 = var4.getClass();
               Class var5 = this.getClass();
               if (var10000 == null) {
                  if (var5 != null) {
                     break label42;
                  }
               } else if (!var10000.equals(var5)) {
                  break label42;
               }

               String var8 = var4.name();
               String var6 = this.name();
               if (var8 == null) {
                  if (var6 != null) {
                     break label42;
                  }
               } else if (!var8.equals(var6)) {
                  break label42;
               }

               ArraySeq var9 = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(var4.addresses()).toImmutableArraySeq();
               ArraySeq var7 = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.addresses()).toImmutableArraySeq();
               if (var9 == null) {
                  if (var7 == null) {
                     break label48;
                  }
               } else if (var9.equals(var7)) {
                  break label48;
               }
            }

            var10 = false;
            return var10;
         }

         var10 = true;
         return var10;
      }
   }

   public int hashCode() {
      return (new scala.collection.immutable..colon.colon(this.name(), new scala.collection.immutable..colon.colon(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.addresses()).toImmutableArraySeq(), scala.collection.immutable.Nil..MODULE$))).hashCode();
   }

   public final JValue toJson() {
      return (new ResourceInformationJson(this.name(), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.addresses()).toImmutableArraySeq())).toJValue();
   }

   public ResourceInformation(final String name, final String[] addresses) {
      this.name = name;
      this.addresses = addresses;
   }
}

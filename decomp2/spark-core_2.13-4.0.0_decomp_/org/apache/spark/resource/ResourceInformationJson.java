package org.apache.spark.resource;

import java.io.Serializable;
import org.json4s.JValue;
import org.json4s.Extraction.;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\re\u0001B\u000e\u001d\t\u0016B\u0001b\u000f\u0001\u0003\u0016\u0004%\t\u0001\u0010\u0005\t\u000b\u0002\u0011\t\u0012)A\u0005{!Aa\t\u0001BK\u0002\u0013\u0005q\t\u0003\u0005L\u0001\tE\t\u0015!\u0003I\u0011\u0015a\u0005\u0001\"\u0001N\u0011\u0015\u0011\u0006\u0001\"\u0001T\u0011\u0015Q\u0006\u0001\"\u0001\\\u0011\u001dy\u0006!!A\u0005\u0002\u0001Dqa\u0019\u0001\u0012\u0002\u0013\u0005A\rC\u0004p\u0001E\u0005I\u0011\u00019\t\u000fI\u0004\u0011\u0011!C!g\"91\u0010AA\u0001\n\u0003a\b\"CA\u0001\u0001\u0005\u0005I\u0011AA\u0002\u0011%\ty\u0001AA\u0001\n\u0003\n\t\u0002C\u0005\u0002 \u0001\t\t\u0011\"\u0001\u0002\"!I\u00111\u0006\u0001\u0002\u0002\u0013\u0005\u0013Q\u0006\u0005\n\u0003c\u0001\u0011\u0011!C!\u0003gA\u0011\"!\u000e\u0001\u0003\u0003%\t%a\u000e\t\u0013\u0005e\u0002!!A\u0005B\u0005mr!CA 9\u0005\u0005\t\u0012BA!\r!YB$!A\t\n\u0005\r\u0003B\u0002'\u0016\t\u0003\tY\u0006C\u0005\u00026U\t\t\u0011\"\u0012\u00028!I\u0011QL\u000b\u0002\u0002\u0013\u0005\u0015q\f\u0005\n\u0003K*\u0012\u0011!CA\u0003OB\u0011\"!\u001f\u0016\u0003\u0003%I!a\u001f\u0003/I+7o\\;sG\u0016LeNZ8s[\u0006$\u0018n\u001c8Kg>t'BA\u000f\u001f\u0003!\u0011Xm]8ve\u000e,'BA\u0010!\u0003\u0015\u0019\b/\u0019:l\u0015\t\t#%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002G\u0005\u0019qN]4\u0004\u0001M!\u0001A\n\u00170!\t9#&D\u0001)\u0015\u0005I\u0013!B:dC2\f\u0017BA\u0016)\u0005\u0019\te.\u001f*fMB\u0011q%L\u0005\u0003]!\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00021q9\u0011\u0011G\u000e\b\u0003eUj\u0011a\r\u0006\u0003i\u0011\na\u0001\u0010:p_Rt\u0014\"A\u0015\n\u0005]B\u0013a\u00029bG.\fw-Z\u0005\u0003si\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!a\u000e\u0015\u0002\t9\fW.Z\u000b\u0002{A\u0011aH\u0011\b\u0003\u007f\u0001\u0003\"A\r\u0015\n\u0005\u0005C\u0013A\u0002)sK\u0012,g-\u0003\u0002D\t\n11\u000b\u001e:j]\u001eT!!\u0011\u0015\u0002\u000b9\fW.\u001a\u0011\u0002\u0013\u0005$GM]3tg\u0016\u001cX#\u0001%\u0011\u0007AJU(\u0003\u0002Ku\t\u00191+Z9\u0002\u0015\u0005$GM]3tg\u0016\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u0004\u001dB\u000b\u0006CA(\u0001\u001b\u0005a\u0002\"B\u001e\u0006\u0001\u0004i\u0004\"\u0002$\u0006\u0001\u0004A\u0015\u0001\u0003;p\u0015Z\u000bG.^3\u0016\u0003Q\u0003\"!\u0016-\u000e\u0003YS!a\u0016\u0012\u0002\r)\u001cxN\u001c\u001bt\u0013\tIfK\u0001\u0004K-\u0006dW/Z\u0001\u0016i>\u0014Vm]8ve\u000e,\u0017J\u001c4pe6\fG/[8o+\u0005a\u0006CA(^\u0013\tqFDA\nSKN|WO]2f\u0013:4wN]7bi&|g.\u0001\u0003d_BLHc\u0001(bE\"91\b\u0003I\u0001\u0002\u0004i\u0004b\u0002$\t!\u0003\u0005\r\u0001S\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005)'FA\u001fgW\u00059\u0007C\u00015n\u001b\u0005I'B\u00016l\u0003%)hn\u00195fG.,GM\u0003\u0002mQ\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u00059L'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#A9+\u0005!3\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001u!\t)(0D\u0001w\u0015\t9\b0\u0001\u0003mC:<'\"A=\u0002\t)\fg/Y\u0005\u0003\u0007Z\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012! \t\u0003OyL!a \u0015\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005\u0015\u00111\u0002\t\u0004O\u0005\u001d\u0011bAA\u0005Q\t\u0019\u0011I\\=\t\u0011\u00055Q\"!AA\u0002u\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\n!\u0019\t)\"a\u0007\u0002\u00065\u0011\u0011q\u0003\u0006\u0004\u00033A\u0013AC2pY2,7\r^5p]&!\u0011QDA\f\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005\r\u0012\u0011\u0006\t\u0004O\u0005\u0015\u0012bAA\u0014Q\t9!i\\8mK\u0006t\u0007\"CA\u0007\u001f\u0005\u0005\t\u0019AA\u0003\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007Q\fy\u0003\u0003\u0005\u0002\u000eA\t\t\u00111\u0001~\u0003!A\u0017m\u001d5D_\u0012,G#A?\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012\u0001^\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005\r\u0012Q\b\u0005\n\u0003\u001b\u0019\u0012\u0011!a\u0001\u0003\u000b\tqCU3t_V\u00148-Z%oM>\u0014X.\u0019;j_:T5o\u001c8\u0011\u0005=+2#B\u000b\u0002F\u0005E\u0003cBA$\u0003\u001bj\u0004JT\u0007\u0003\u0003\u0013R1!a\u0013)\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\u0014\u0002J\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0011\t\u0005M\u0013\u0011L\u0007\u0003\u0003+R1!a\u0016y\u0003\tIw.C\u0002:\u0003+\"\"!!\u0011\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000b9\u000b\t'a\u0019\t\u000bmB\u0002\u0019A\u001f\t\u000b\u0019C\u0002\u0019\u0001%\u0002\u000fUt\u0017\r\u001d9msR!\u0011\u0011NA;!\u00159\u00131NA8\u0013\r\ti\u0007\u000b\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000b\u001d\n\t(\u0010%\n\u0007\u0005M\u0004F\u0001\u0004UkBdWM\r\u0005\t\u0003oJ\u0012\u0011!a\u0001\u001d\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005u\u0004cA;\u0002\u0000%\u0019\u0011\u0011\u0011<\u0003\r=\u0013'.Z2u\u0001"
)
public class ResourceInformationJson implements Product, Serializable {
   private final String name;
   private final Seq addresses;

   public static Option unapply(final ResourceInformationJson x$0) {
      return ResourceInformationJson$.MODULE$.unapply(x$0);
   }

   public static ResourceInformationJson apply(final String name, final Seq addresses) {
      return ResourceInformationJson$.MODULE$.apply(name, addresses);
   }

   public static Function1 tupled() {
      return ResourceInformationJson$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ResourceInformationJson$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String name() {
      return this.name;
   }

   public Seq addresses() {
      return this.addresses;
   }

   public JValue toJValue() {
      return .MODULE$.decompose(this, org.json4s.DefaultFormats..MODULE$);
   }

   public ResourceInformation toResourceInformation() {
      return new ResourceInformation(this.name(), (String[])this.addresses().toArray(scala.reflect.ClassTag..MODULE$.apply(String.class)));
   }

   public ResourceInformationJson copy(final String name, final Seq addresses) {
      return new ResourceInformationJson(name, addresses);
   }

   public String copy$default$1() {
      return this.name();
   }

   public Seq copy$default$2() {
      return this.addresses();
   }

   public String productPrefix() {
      return "ResourceInformationJson";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.name();
         }
         case 1 -> {
            return this.addresses();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ResourceInformationJson;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "name";
         }
         case 1 -> {
            return "addresses";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof ResourceInformationJson) {
               label48: {
                  ResourceInformationJson var4 = (ResourceInformationJson)x$1;
                  String var10000 = this.name();
                  String var5 = var4.name();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  Seq var7 = this.addresses();
                  Seq var6 = var4.addresses();
                  if (var7 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var7.equals(var6)) {
                     break label48;
                  }

                  if (var4.canEqual(this)) {
                     break label55;
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public ResourceInformationJson(final String name, final Seq addresses) {
      this.name = name;
      this.addresses = addresses;
      Product.$init$(this);
   }
}

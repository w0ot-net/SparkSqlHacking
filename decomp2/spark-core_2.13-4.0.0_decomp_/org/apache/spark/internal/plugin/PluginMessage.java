package org.apache.spark.internal.plugin;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rd\u0001B\r\u001b\u0001\u0016B\u0001b\u000f\u0001\u0003\u0016\u0004%\t\u0001\u0010\u0005\t\u000b\u0002\u0011\t\u0012)A\u0005{!Aa\t\u0001BK\u0002\u0013\u0005q\t\u0003\u0005I\u0001\tE\t\u0015!\u0003'\u0011\u0015I\u0005\u0001\"\u0001K\u0011\u001dy\u0005!!A\u0005\u0002ACqa\u0015\u0001\u0012\u0002\u0013\u0005A\u000bC\u0004`\u0001E\u0005I\u0011\u00011\t\u000f\t\u0004\u0011\u0011!C!G\"91\u000eAA\u0001\n\u0003a\u0007b\u00029\u0001\u0003\u0003%\t!\u001d\u0005\bo\u0002\t\t\u0011\"\u0011y\u0011!y\b!!A\u0005\u0002\u0005\u0005\u0001\"CA\u0006\u0001\u0005\u0005I\u0011IA\u0007\u0011%\t\t\u0002AA\u0001\n\u0003\n\u0019\u0002C\u0005\u0002\u0016\u0001\t\t\u0011\"\u0011\u0002\u0018!I\u0011\u0011\u0004\u0001\u0002\u0002\u0013\u0005\u00131D\u0004\n\u0003?Q\u0012\u0011!E\u0001\u0003C1\u0001\"\u0007\u000e\u0002\u0002#\u0005\u00111\u0005\u0005\u0007\u0013N!\t!a\u000f\t\u0013\u0005U1#!A\u0005F\u0005]\u0001\"CA\u001f'\u0005\u0005I\u0011QA \u0011%\t)eEA\u0001\n\u0003\u000b9\u0005C\u0005\u0002ZM\t\t\u0011\"\u0003\u0002\\\ti\u0001\u000b\\;hS:lUm]:bO\u0016T!a\u0007\u000f\u0002\rAdWoZ5o\u0015\tib$\u0001\u0005j]R,'O\\1m\u0015\ty\u0002%A\u0003ta\u0006\u00148N\u0003\u0002\"E\u00051\u0011\r]1dQ\u0016T\u0011aI\u0001\u0004_J<7\u0001A\n\u0005\u0001\u0019bs\u0006\u0005\u0002(U5\t\u0001FC\u0001*\u0003\u0015\u00198-\u00197b\u0013\tY\u0003F\u0001\u0004B]f\u0014VM\u001a\t\u0003O5J!A\f\u0015\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0001\u0007\u000f\b\u0003cYr!AM\u001b\u000e\u0003MR!\u0001\u000e\u0013\u0002\rq\u0012xn\u001c;?\u0013\u0005I\u0013BA\u001c)\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u000f\u001e\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005]B\u0013A\u00039mk\u001eLgNT1nKV\tQ\b\u0005\u0002?\u0005:\u0011q\b\u0011\t\u0003e!J!!\u0011\u0015\u0002\rA\u0013X\rZ3g\u0013\t\u0019EI\u0001\u0004TiJLgn\u001a\u0006\u0003\u0003\"\n1\u0002\u001d7vO&tg*Y7fA\u00059Q.Z:tC\u001e,W#\u0001\u0014\u0002\u00115,7o]1hK\u0002\na\u0001P5oSRtDcA&N\u001dB\u0011A\nA\u0007\u00025!)1(\u0002a\u0001{!)a)\u0002a\u0001M\u0005!1m\u001c9z)\rY\u0015K\u0015\u0005\bw\u0019\u0001\n\u00111\u0001>\u0011\u001d1e\u0001%AA\u0002\u0019\nabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001VU\tidkK\u0001X!\tAV,D\u0001Z\u0015\tQ6,A\u0005v]\u000eDWmY6fI*\u0011A\fK\u0001\u000bC:tw\u000e^1uS>t\u0017B\u00010Z\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0005\t'F\u0001\u0014W\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tA\r\u0005\u0002fU6\taM\u0003\u0002hQ\u0006!A.\u00198h\u0015\u0005I\u0017\u0001\u00026bm\u0006L!a\u00114\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u00035\u0004\"a\n8\n\u0005=D#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u0001:v!\t93/\u0003\u0002uQ\t\u0019\u0011I\\=\t\u000fY\\\u0011\u0011!a\u0001[\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012!\u001f\t\u0004uv\u0014X\"A>\u000b\u0005qD\u0013AC2pY2,7\r^5p]&\u0011ap\u001f\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\u0004\u0005%\u0001cA\u0014\u0002\u0006%\u0019\u0011q\u0001\u0015\u0003\u000f\t{w\u000e\\3b]\"9a/DA\u0001\u0002\u0004\u0011\u0018A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2\u0001ZA\b\u0011\u001d1h\"!AA\u00025\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002[\u0006AAo\\*ue&tw\rF\u0001e\u0003\u0019)\u0017/^1mgR!\u00111AA\u000f\u0011\u001d1\u0018#!AA\u0002I\fQ\u0002\u00157vO&tW*Z:tC\u001e,\u0007C\u0001'\u0014'\u0015\u0019\u0012QEA\u0019!\u001d\t9#!\f>M-k!!!\u000b\u000b\u0007\u0005-\u0002&A\u0004sk:$\u0018.\\3\n\t\u0005=\u0012\u0011\u0006\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003BA\u001a\u0003si!!!\u000e\u000b\u0007\u0005]\u0002.\u0001\u0002j_&\u0019\u0011(!\u000e\u0015\u0005\u0005\u0005\u0012!B1qa2LH#B&\u0002B\u0005\r\u0003\"B\u001e\u0017\u0001\u0004i\u0004\"\u0002$\u0017\u0001\u00041\u0013aB;oCB\u0004H.\u001f\u000b\u0005\u0003\u0013\n)\u0006E\u0003(\u0003\u0017\ny%C\u0002\u0002N!\u0012aa\u00149uS>t\u0007#B\u0014\u0002Ru2\u0013bAA*Q\t1A+\u001e9mKJB\u0001\"a\u0016\u0018\u0003\u0003\u0005\raS\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA/!\r)\u0017qL\u0005\u0004\u0003C2'AB(cU\u0016\u001cG\u000f"
)
public class PluginMessage implements Product, Serializable {
   private final String pluginName;
   private final Object message;

   public static Option unapply(final PluginMessage x$0) {
      return PluginMessage$.MODULE$.unapply(x$0);
   }

   public static PluginMessage apply(final String pluginName, final Object message) {
      return PluginMessage$.MODULE$.apply(pluginName, message);
   }

   public static Function1 tupled() {
      return PluginMessage$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return PluginMessage$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String pluginName() {
      return this.pluginName;
   }

   public Object message() {
      return this.message;
   }

   public PluginMessage copy(final String pluginName, final Object message) {
      return new PluginMessage(pluginName, message);
   }

   public String copy$default$1() {
      return this.pluginName();
   }

   public Object copy$default$2() {
      return this.message();
   }

   public String productPrefix() {
      return "PluginMessage";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.pluginName();
         }
         case 1 -> {
            return this.message();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof PluginMessage;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "pluginName";
         }
         case 1 -> {
            return "message";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label49: {
            if (x$1 instanceof PluginMessage) {
               label42: {
                  PluginMessage var4 = (PluginMessage)x$1;
                  String var10000 = this.pluginName();
                  String var5 = var4.pluginName();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label42;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label42;
                  }

                  if (BoxesRunTime.equals(this.message(), var4.message()) && var4.canEqual(this)) {
                     break label49;
                  }
               }
            }

            var6 = false;
            return var6;
         }
      }

      var6 = true;
      return var6;
   }

   public PluginMessage(final String pluginName, final Object message) {
      this.pluginName = pluginName;
      this.message = message;
      Product.$init$(this);
   }
}

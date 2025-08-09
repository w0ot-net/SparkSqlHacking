package scala.xml.dtd;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.xml.Utility$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\re\u0001B\u000f\u001f\u0001\u0016B\u0001\"\u000f\u0001\u0003\u0016\u0004%\tA\u000f\u0005\t\u0007\u0002\u0011\t\u0012)A\u0005w!AA\t\u0001BK\u0002\u0013\u0005!\b\u0003\u0005F\u0001\tE\t\u0015!\u0003<\u0011!1\u0005A!f\u0001\n\u00039\u0005\u0002\u0003'\u0001\u0005#\u0005\u000b\u0011\u0002%\t\u000b5\u0003A\u0011\u0001(\t\u000bM\u0003A\u0011\t+\t\u000bU\u0003A\u0011\u0001,\t\u000fu\u0003\u0011\u0011!C\u0001=\"9!\rAI\u0001\n\u0003\u0019\u0007b\u00028\u0001#\u0003%\ta\u0019\u0005\b_\u0002\t\n\u0011\"\u0001q\u0011\u001d\u0011\b!!A\u0005BMDqa\u001f\u0001\u0002\u0002\u0013\u0005A\u0010C\u0005\u0002\u0002\u0001\t\t\u0011\"\u0001\u0002\u0004!I\u0011q\u0002\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0003\u0005\n\u0003?\u0001\u0011\u0011!C\u0001\u0003CA\u0011\"a\u000b\u0001\u0003\u0003%\t%!\f\t\u0013\u0005E\u0002!!A\u0005B\u0005M\u0002\"CA\u001b\u0001\u0005\u0005I\u0011IA\u001c\u000f%\tYDHA\u0001\u0012\u0003\tiD\u0002\u0005\u001e=\u0005\u0005\t\u0012AA \u0011\u0019iu\u0003\"\u0001\u0002X!A1kFA\u0001\n\u000b\nI\u0006C\u0005\u0002\\]\t\t\u0011\"!\u0002^!I\u0011QM\f\u0002\u0002\u0013\u0005\u0015q\r\u0005\n\u0003s:\u0012\u0011!C\u0005\u0003w\u0012\u0001\"\u0011;ue\u0012+7\r\u001c\u0006\u0003?\u0001\n1\u0001\u001a;e\u0015\t\t#%A\u0002y[2T\u0011aI\u0001\u0006g\u000e\fG.Y\u0002\u0001'\u0011\u0001aEK\u0017\u0011\u0005\u001dBS\"\u0001\u0012\n\u0005%\u0012#AB!osJ+g\r\u0005\u0002(W%\u0011AF\t\u0002\b!J|G-^2u!\tqcG\u0004\u00020i9\u0011\u0001gM\u0007\u0002c)\u0011!\u0007J\u0001\u0007yI|w\u000e\u001e \n\u0003\rJ!!\u000e\u0012\u0002\u000fA\f7m[1hK&\u0011q\u0007\u000f\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003k\t\nAA\\1nKV\t1\b\u0005\u0002=\u0001:\u0011QH\u0010\t\u0003a\tJ!a\u0010\u0012\u0002\rA\u0013X\rZ3g\u0013\t\t%I\u0001\u0004TiJLgn\u001a\u0006\u0003\u007f\t\nQA\\1nK\u0002\n1\u0001\u001e9f\u0003\u0011!\b/\u001a\u0011\u0002\u000f\u0011,g-Y;miV\t\u0001\n\u0005\u0002J\u00156\ta$\u0003\u0002L=\tYA)\u001a4bk2$H)Z2m\u0003!!WMZ1vYR\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0003P!F\u0013\u0006CA%\u0001\u0011\u0015It\u00011\u0001<\u0011\u0015!u\u00011\u0001<\u0011\u00151u\u00011\u0001I\u0003!!xn\u0015;sS:<G#A\u001e\u0002\u0017\t,\u0018\u000e\u001c3TiJLgn\u001a\u000b\u0003/n\u0003\"\u0001W-\u000f\u0005\u001d\"\u0014B\u0001.9\u00055\u0019FO]5oO\n+\u0018\u000e\u001c3fe\")A,\u0003a\u0001/\u0006\u00111OY\u0001\u0005G>\u0004\u0018\u0010\u0006\u0003P?\u0002\f\u0007bB\u001d\u000b!\u0003\u0005\ra\u000f\u0005\b\t*\u0001\n\u00111\u0001<\u0011\u001d1%\u0002%AA\u0002!\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001eU\tYTmK\u0001g!\t9G.D\u0001i\u0015\tI'.A\u0005v]\u000eDWmY6fI*\u00111NI\u0001\u000bC:tw\u000e^1uS>t\u0017BA7i\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM*\u0012!\u001d\u0016\u0003\u0011\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u0001;\u0011\u0005UTX\"\u0001<\u000b\u0005]D\u0018\u0001\u00027b]\u001eT\u0011!_\u0001\u0005U\u00064\u0018-\u0003\u0002Bm\u0006a\u0001O]8ek\u000e$\u0018I]5usV\tQ\u0010\u0005\u0002(}&\u0011qP\t\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003\u000b\tY\u0001E\u0002(\u0003\u000fI1!!\u0003#\u0005\r\te.\u001f\u0005\t\u0003\u001b\u0001\u0012\u0011!a\u0001{\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a\u0005\u0011\r\u0005U\u00111DA\u0003\u001b\t\t9BC\u0002\u0002\u001a\t\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\ti\"a\u0006\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003G\tI\u0003E\u0002(\u0003KI1!a\n#\u0005\u001d\u0011un\u001c7fC:D\u0011\"!\u0004\u0013\u0003\u0003\u0005\r!!\u0002\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004i\u0006=\u0002\u0002CA\u0007'\u0005\u0005\t\u0019A?\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012!`\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005\r\u0012\u0011\b\u0005\n\u0003\u001b)\u0012\u0011!a\u0001\u0003\u000b\t\u0001\"\u0011;ue\u0012+7\r\u001c\t\u0003\u0013^\u0019RaFA!\u0003\u001b\u0002\u0002\"a\u0011\u0002JmZ\u0004jT\u0007\u0003\u0003\u000bR1!a\u0012#\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\u0013\u0002F\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u001a\u0011\t\u0005=\u0013QK\u0007\u0003\u0003#R1!a\u0015y\u0003\tIw.C\u00028\u0003#\"\"!!\u0010\u0015\u0003Q\fQ!\u00199qYf$raTA0\u0003C\n\u0019\u0007C\u0003:5\u0001\u00071\bC\u0003E5\u0001\u00071\bC\u0003G5\u0001\u0007\u0001*A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005%\u0014Q\u000f\t\u0006O\u0005-\u0014qN\u0005\u0004\u0003[\u0012#AB(qi&|g\u000e\u0005\u0004(\u0003cZ4\bS\u0005\u0004\u0003g\u0012#A\u0002+va2,7\u0007\u0003\u0005\u0002xm\t\t\u00111\u0001P\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003{\u00022!^A@\u0013\r\t\tI\u001e\u0002\u0007\u001f\nTWm\u0019;"
)
public class AttrDecl implements Product, Serializable {
   private final String name;
   private final String tpe;
   private final DefaultDecl default;

   public static Option unapply(final AttrDecl x$0) {
      return AttrDecl$.MODULE$.unapply(x$0);
   }

   public static AttrDecl apply(final String name, final String tpe, final DefaultDecl default) {
      return AttrDecl$.MODULE$.apply(name, tpe, default);
   }

   public static Function1 tupled() {
      return AttrDecl$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return AttrDecl$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String name() {
      return this.name;
   }

   public String tpe() {
      return this.tpe;
   }

   public DefaultDecl default() {
      return this.default;
   }

   public String toString() {
      return Utility$.MODULE$.sbToString((sb) -> {
         $anonfun$toString$2(this, sb);
         return BoxedUnit.UNIT;
      });
   }

   public StringBuilder buildString(final StringBuilder sb) {
      sb.append((new java.lang.StringBuilder(4)).append("  ").append(this.name()).append(" ").append(this.tpe()).append(" ").toString());
      return this.default().buildString(sb);
   }

   public AttrDecl copy(final String name, final String tpe, final DefaultDecl default) {
      return new AttrDecl(name, tpe, default);
   }

   public String copy$default$1() {
      return this.name();
   }

   public String copy$default$2() {
      return this.tpe();
   }

   public DefaultDecl copy$default$3() {
      return this.default();
   }

   public String productPrefix() {
      return "AttrDecl";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.name();
         case 1:
            return this.tpe();
         case 2:
            return this.default();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof AttrDecl;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "name";
         case 1:
            return "tpe";
         case 2:
            return "default";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label63: {
            if (x$1 instanceof AttrDecl) {
               label56: {
                  AttrDecl var4 = (AttrDecl)x$1;
                  String var10000 = this.name();
                  String var5 = var4.name();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  var10000 = this.tpe();
                  String var6 = var4.tpe();
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label56;
                  }

                  DefaultDecl var9 = this.default();
                  DefaultDecl var7 = var4.default();
                  if (var9 == null) {
                     if (var7 != null) {
                        break label56;
                     }
                  } else if (!var9.equals(var7)) {
                     break label56;
                  }

                  if (var4.canEqual(this)) {
                     break label63;
                  }
               }
            }

            var10 = false;
            return var10;
         }
      }

      var10 = true;
      return var10;
   }

   // $FF: synthetic method
   public static final void $anonfun$toString$2(final AttrDecl $this, final StringBuilder sb) {
      $this.buildString(sb);
   }

   public AttrDecl(final String name, final String tpe, final DefaultDecl default) {
      this.name = name;
      this.tpe = tpe;
      this.default = default;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

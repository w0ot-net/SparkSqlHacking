package scala.xml.dtd;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055d\u0001B\r\u001b\u0001\u0006B\u0001B\u000e\u0001\u0003\u0016\u0004%\ta\u000e\u0005\t\u0001\u0002\u0011\t\u0012)A\u0005q!A\u0011\t\u0001BK\u0002\u0013\u0005!\t\u0003\u0005G\u0001\tE\t\u0015!\u0003D\u0011\u00159\u0005\u0001\"\u0001I\u0011\u0015a\u0005\u0001\"\u0011N\u0011\u001d!\u0006!!A\u0005\u0002UCq\u0001\u0017\u0001\u0012\u0002\u0013\u0005\u0011\fC\u0004e\u0001E\u0005I\u0011A3\t\u000f\u001d\u0004\u0011\u0011!C!Q\"9\u0001\u000fAA\u0001\n\u0003\t\bbB;\u0001\u0003\u0003%\tA\u001e\u0005\by\u0002\t\t\u0011\"\u0011~\u0011%\tI\u0001AA\u0001\n\u0003\tY\u0001C\u0005\u0002\u0016\u0001\t\t\u0011\"\u0011\u0002\u0018!I\u00111\u0004\u0001\u0002\u0002\u0013\u0005\u0013Q\u0004\u0005\n\u0003?\u0001\u0011\u0011!C!\u0003C9\u0011\"!\n\u001b\u0003\u0003E\t!a\n\u0007\u0011eQ\u0012\u0011!E\u0001\u0003SAaaR\n\u0005\u0002\u0005\u0005\u0003\"CA\"'\u0005\u0005IQIA#\u0011%\t9eEA\u0001\n\u0003\u000bI\u0005C\u0005\u0002PM\t\t\u0011\"!\u0002R!I\u00111M\n\u0002\u0002\u0013%\u0011Q\r\u0002\u0014!\u0006\u0014\u0018-\\3uKJ,e\u000e^5us\u0012+7\r\u001c\u0006\u00037q\t1\u0001\u001a;e\u0015\tib$A\u0002y[2T\u0011aH\u0001\u0006g\u000e\fG.Y\u0002\u0001'\u0011\u0001!E\n\u0016\u0011\u0005\r\"S\"\u0001\u000e\n\u0005\u0015R\"AC#oi&$\u0018\u0010R3dYB\u0011q\u0005K\u0007\u0002=%\u0011\u0011F\b\u0002\b!J|G-^2u!\tY3G\u0004\u0002-c9\u0011Q\u0006M\u0007\u0002])\u0011q\u0006I\u0001\u0007yI|w\u000e\u001e \n\u0003}I!A\r\u0010\u0002\u000fA\f7m[1hK&\u0011A'\u000e\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003ey\tAA\\1nKV\t\u0001\b\u0005\u0002:{9\u0011!h\u000f\t\u0003[yI!\u0001\u0010\u0010\u0002\rA\u0013X\rZ3g\u0013\tqtH\u0001\u0004TiJLgn\u001a\u0006\u0003yy\tQA\\1nK\u0002\na!\u001a8uI\u00164W#A\"\u0011\u0005\r\"\u0015BA#\u001b\u0005%)e\u000e^5us\u0012+g-A\u0004f]R$WM\u001a\u0011\u0002\rqJg.\u001b;?)\rI%j\u0013\t\u0003G\u0001AQAN\u0003A\u0002aBQ!Q\u0003A\u0002\r\u000b1BY;jY\u0012\u001cFO]5oOR\u0011aJ\u0015\t\u0003\u001fBs!aJ\u0019\n\u0005E+$!D*ue&twMQ;jY\u0012,'\u000fC\u0003T\r\u0001\u0007a*\u0001\u0002tE\u0006!1m\u001c9z)\rIek\u0016\u0005\bm\u001d\u0001\n\u00111\u00019\u0011\u001d\tu\u0001%AA\u0002\r\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001[U\tA4lK\u0001]!\ti&-D\u0001_\u0015\ty\u0006-A\u0005v]\u000eDWmY6fI*\u0011\u0011MH\u0001\u000bC:tw\u000e^1uS>t\u0017BA2_\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u00051'FA\"\\\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t\u0011\u000e\u0005\u0002k_6\t1N\u0003\u0002m[\u0006!A.\u00198h\u0015\u0005q\u0017\u0001\u00026bm\u0006L!AP6\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003I\u0004\"aJ:\n\u0005Qt\"aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HCA<{!\t9\u00030\u0003\u0002z=\t\u0019\u0011I\\=\t\u000fmd\u0011\u0011!a\u0001e\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012A \t\u0005\u007f\u0006\u0015q/\u0004\u0002\u0002\u0002)\u0019\u00111\u0001\u0010\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\b\u0005\u0005!\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!\u0004\u0002\u0014A\u0019q%a\u0004\n\u0007\u0005EaDA\u0004C_>dW-\u00198\t\u000fmt\u0011\u0011!a\u0001o\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\rI\u0017\u0011\u0004\u0005\bw>\t\t\u00111\u0001s\u0003!A\u0017m\u001d5D_\u0012,G#\u0001:\u0002\r\u0015\fX/\u00197t)\u0011\ti!a\t\t\u000fm\f\u0012\u0011!a\u0001o\u0006\u0019\u0002+\u0019:b[\u0016$XM]#oi&$\u0018\u0010R3dYB\u00111eE\n\u0006'\u0005-\u0012q\u0007\t\b\u0003[\t\u0019\u0004O\"J\u001b\t\tyCC\u0002\u00022y\tqA];oi&lW-\u0003\u0003\u00026\u0005=\"!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeA!\u0011\u0011HA \u001b\t\tYDC\u0002\u0002>5\f!![8\n\u0007Q\nY\u0004\u0006\u0002\u0002(\u0005AAo\\*ue&tw\rF\u0001j\u0003\u0015\t\u0007\u000f\u001d7z)\u0015I\u00151JA'\u0011\u00151d\u00031\u00019\u0011\u0015\te\u00031\u0001D\u0003\u001d)h.\u00199qYf$B!a\u0015\u0002`A)q%!\u0016\u0002Z%\u0019\u0011q\u000b\u0010\u0003\r=\u0003H/[8o!\u00159\u00131\f\u001dD\u0013\r\tiF\b\u0002\u0007)V\u0004H.\u001a\u001a\t\u0011\u0005\u0005t#!AA\u0002%\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t9\u0007E\u0002k\u0003SJ1!a\u001bl\u0005\u0019y%M[3di\u0002"
)
public class ParameterEntityDecl extends EntityDecl implements Product, Serializable {
   private final String name;
   private final EntityDef entdef;

   public static Option unapply(final ParameterEntityDecl x$0) {
      return ParameterEntityDecl$.MODULE$.unapply(x$0);
   }

   public static ParameterEntityDecl apply(final String name, final EntityDef entdef) {
      return ParameterEntityDecl$.MODULE$.apply(name, entdef);
   }

   public static Function1 tupled() {
      return ParameterEntityDecl$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ParameterEntityDecl$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String name() {
      return this.name;
   }

   public EntityDef entdef() {
      return this.entdef;
   }

   public StringBuilder buildString(final StringBuilder sb) {
      sb.append((new java.lang.StringBuilder(12)).append("<!ENTITY % ").append(this.name()).append(" ").toString());
      return this.entdef().buildString(sb).append('>');
   }

   public ParameterEntityDecl copy(final String name, final EntityDef entdef) {
      return new ParameterEntityDecl(name, entdef);
   }

   public String copy$default$1() {
      return this.name();
   }

   public EntityDef copy$default$2() {
      return this.entdef();
   }

   public String productPrefix() {
      return "ParameterEntityDecl";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.name();
         case 1:
            return this.entdef();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ParameterEntityDecl;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "name";
         case 1:
            return "entdef";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof ParameterEntityDecl) {
               label48: {
                  ParameterEntityDecl var4 = (ParameterEntityDecl)x$1;
                  String var10000 = this.name();
                  String var5 = var4.name();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  EntityDef var7 = this.entdef();
                  EntityDef var6 = var4.entdef();
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

   public ParameterEntityDecl(final String name, final EntityDef entdef) {
      this.name = name;
      this.entdef = entdef;
      Product.$init$(this);
   }
}

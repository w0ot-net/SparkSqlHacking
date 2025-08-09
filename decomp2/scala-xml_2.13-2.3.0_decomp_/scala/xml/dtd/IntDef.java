package scala.xml.dtd;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.xml.Utility$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]c\u0001\u0002\r\u001a\u0001\u0002B\u0001\"\u000e\u0001\u0003\u0016\u0004%\tA\u000e\u0005\t\u007f\u0001\u0011\t\u0012)A\u0005o!)\u0001\t\u0001C\u0001\u0003\")A\t\u0001C\u0005\u000b\")\u0011\n\u0001C!\u0015\"9\u0011\u000bAA\u0001\n\u0003\u0011\u0006b\u0002+\u0001#\u0003%\t!\u0016\u0005\bA\u0002\t\t\u0011\"\u0011b\u0011\u001dI\u0007!!A\u0005\u0002)DqA\u001c\u0001\u0002\u0002\u0013\u0005q\u000eC\u0004v\u0001\u0005\u0005I\u0011\t<\t\u000fu\u0004\u0011\u0011!C\u0001}\"I\u0011q\u0001\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0002\u0005\n\u0003\u001b\u0001\u0011\u0011!C!\u0003\u001fA\u0011\"!\u0005\u0001\u0003\u0003%\t%a\u0005\t\u0013\u0005U\u0001!!A\u0005B\u0005]q!CA\u000e3\u0005\u0005\t\u0012AA\u000f\r!A\u0012$!A\t\u0002\u0005}\u0001B\u0002!\u0013\t\u0003\t9\u0004C\u0005\u0002\u0012I\t\t\u0011\"\u0012\u0002\u0014!I\u0011\u0011\b\n\u0002\u0002\u0013\u0005\u00151\b\u0005\n\u0003\u007f\u0011\u0012\u0011!CA\u0003\u0003B\u0011\"!\u0014\u0013\u0003\u0003%I!a\u0014\u0003\r%sG\u000fR3g\u0015\tQ2$A\u0002ei\u0012T!\u0001H\u000f\u0002\u0007alGNC\u0001\u001f\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019B\u0001A\u0011&SA\u0011!eI\u0007\u00023%\u0011A%\u0007\u0002\n\u000b:$\u0018\u000e^=EK\u001a\u0004\"AJ\u0014\u000e\u0003uI!\u0001K\u000f\u0003\u000fA\u0013x\u000eZ;diB\u0011!F\r\b\u0003WAr!\u0001L\u0018\u000e\u00035R!AL\u0010\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0012BA\u0019\u001e\u0003\u001d\u0001\u0018mY6bO\u0016L!a\r\u001b\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005Ej\u0012!\u0002<bYV,W#A\u001c\u0011\u0005abdBA\u001d;!\taS$\u0003\u0002<;\u00051\u0001K]3eK\u001aL!!\u0010 \u0003\rM#(/\u001b8h\u0015\tYT$\u0001\u0004wC2,X\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\t\u001b\u0005C\u0001\u0012\u0001\u0011\u0015)4\u00011\u00018\u000351\u0018\r\\5eCR,g+\u00197vKR\ta\t\u0005\u0002'\u000f&\u0011\u0001*\b\u0002\u0005+:LG/A\u0006ck&dGm\u0015;sS:<GCA&P!\taUJ\u0004\u0002'a%\u0011a\n\u000e\u0002\u000e'R\u0014\u0018N\\4Ck&dG-\u001a:\t\u000bA+\u0001\u0019A&\u0002\u0005M\u0014\u0017\u0001B2paf$\"AQ*\t\u000fU2\u0001\u0013!a\u0001o\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u0001,+\u0005]:6&\u0001-\u0011\u0005esV\"\u0001.\u000b\u0005mc\u0016!C;oG\",7m[3e\u0015\tiV$\u0001\u0006b]:|G/\u0019;j_:L!a\u0018.\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002EB\u00111\r[\u0007\u0002I*\u0011QMZ\u0001\u0005Y\u0006twMC\u0001h\u0003\u0011Q\u0017M^1\n\u0005u\"\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#A6\u0011\u0005\u0019b\u0017BA7\u001e\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\t\u00018\u000f\u0005\u0002'c&\u0011!/\b\u0002\u0004\u0003:L\bb\u0002;\u000b\u0003\u0003\u0005\ra[\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003]\u00042\u0001_>q\u001b\u0005I(B\u0001>\u001e\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003yf\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0019q0!\u0002\u0011\u0007\u0019\n\t!C\u0002\u0002\u0004u\u0011qAQ8pY\u0016\fg\u000eC\u0004u\u0019\u0005\u0005\t\u0019\u00019\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004E\u0006-\u0001b\u0002;\u000e\u0003\u0003\u0005\ra[\u0001\tQ\u0006\u001c\bnQ8eKR\t1.\u0001\u0005u_N#(/\u001b8h)\u0005\u0011\u0017AB3rk\u0006d7\u000fF\u0002\u0000\u00033Aq\u0001\u001e\t\u0002\u0002\u0003\u0007\u0001/\u0001\u0004J]R$UM\u001a\t\u0003EI\u0019RAEA\u0011\u0003[\u0001b!a\t\u0002*]\u0012UBAA\u0013\u0015\r\t9#H\u0001\beVtG/[7f\u0013\u0011\tY#!\n\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u00020\u0005URBAA\u0019\u0015\r\t\u0019DZ\u0001\u0003S>L1aMA\u0019)\t\ti\"A\u0003baBd\u0017\u0010F\u0002C\u0003{AQ!N\u000bA\u0002]\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002D\u0005%\u0003\u0003\u0002\u0014\u0002F]J1!a\u0012\u001e\u0005\u0019y\u0005\u000f^5p]\"A\u00111\n\f\u0002\u0002\u0003\u0007!)A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u0015\u0011\u0007\r\f\u0019&C\u0002\u0002V\u0011\u0014aa\u00142kK\u000e$\b"
)
public class IntDef extends EntityDef implements Product, Serializable {
   private final String value;

   public static Option unapply(final IntDef x$0) {
      return IntDef$.MODULE$.unapply(x$0);
   }

   public static IntDef apply(final String value) {
      return IntDef$.MODULE$.apply(value);
   }

   public static Function1 andThen(final Function1 g) {
      return IntDef$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return IntDef$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String value() {
      return this.value;
   }

   private void validateValue() {
      String tmp = this.value();

      for(int ix = tmp.indexOf(37); ix != -1; ix = tmp.indexOf(37)) {
         int iz = tmp.indexOf(59, ix);
         if (iz == -1 && iz == ix + 1) {
            throw new IllegalArgumentException("no % allowed in entity value, except for parameter-entity-references");
         }

         String n = tmp.substring(ix, iz);
         if (!Utility$.MODULE$.isName(n)) {
            throw new IllegalArgumentException((new StringBuilder(43)).append("internal entity def: \"").append(n).append("\" must be an XML Name").toString());
         }

         tmp = tmp.substring(iz + 1, tmp.length());
      }

   }

   public scala.collection.mutable.StringBuilder buildString(final scala.collection.mutable.StringBuilder sb) {
      return Utility$.MODULE$.appendQuoted(this.value(), sb);
   }

   public IntDef copy(final String value) {
      return new IntDef(value);
   }

   public String copy$default$1() {
      return this.value();
   }

   public String productPrefix() {
      return "IntDef";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.value();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof IntDef;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "value";
         default:
            return (String)Statics.ioobe(x$1);
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
         label47: {
            if (x$1 instanceof IntDef) {
               label40: {
                  IntDef var4 = (IntDef)x$1;
                  String var10000 = this.value();
                  String var5 = var4.value();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label40;
                  }

                  if (var4.canEqual(this)) {
                     break label47;
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

   public IntDef(final String value) {
      this.value = value;
      Product.$init$(this);
      this.validateValue();
   }
}

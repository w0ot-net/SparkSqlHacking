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
import scala.xml.dtd.impl.Base;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=c\u0001\u0002\f\u0018\u0001zA\u0001b\r\u0001\u0003\u0016\u0004%\t\u0005\u000e\u0005\t\u007f\u0001\u0011\t\u0012)A\u0005k!)\u0001\t\u0001C\u0001\u0003\")A\t\u0001C!\u000b\"9A\nAA\u0001\n\u0003i\u0005bB(\u0001#\u0003%\t\u0001\u0015\u0005\b7\u0002\t\t\u0011\"\u0011]\u0011\u001d)\u0007!!A\u0005\u0002\u0019DqA\u001b\u0001\u0002\u0002\u0013\u00051\u000eC\u0004r\u0001\u0005\u0005I\u0011\t:\t\u000fe\u0004\u0011\u0011!C\u0001u\"Aq\u0010AA\u0001\n\u0003\n\t\u0001C\u0005\u0002\u0006\u0001\t\t\u0011\"\u0011\u0002\b!I\u0011\u0011\u0002\u0001\u0002\u0002\u0013\u0005\u00131B\u0004\n\u0003\u001f9\u0012\u0011!E\u0001\u0003#1\u0001BF\f\u0002\u0002#\u0005\u00111\u0003\u0005\u0007\u0001B!\t!a\u000b\t\u0013\u00055\u0002#!A\u0005F\u0005=\u0002\"CA\u0019!\u0005\u0005I\u0011QA\u001a\u0011%\t9\u0004EA\u0001\n\u0003\u000bI\u0004C\u0005\u0002FA\t\t\u0011\"\u0003\u0002H\tAQ\tT#N\u000b:#6K\u0003\u0002\u00193\u0005\u0019A\r\u001e3\u000b\u0005iY\u0012a\u0001=nY*\tA$A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\t\u0001y2e\n\t\u0003A\u0005j\u0011aF\u0005\u0003E]\u0011q\u0002\u0012$B\u0007>tG/\u001a8u\u001b>$W\r\u001c\t\u0003I\u0015j\u0011aG\u0005\u0003Mm\u0011q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002)a9\u0011\u0011F\f\b\u0003U5j\u0011a\u000b\u0006\u0003Yu\ta\u0001\u0010:p_Rt\u0014\"\u0001\u000f\n\u0005=Z\u0012a\u00029bG.\fw-Z\u0005\u0003cI\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!aL\u000e\u0002\u0003I,\u0012!\u000e\t\u0003mer!\u0001I\u001c\n\u0005a:\u0012\u0001D\"p]R,g\u000e^'pI\u0016d\u0017B\u0001\u001e<\u0005\u0019\u0011VmZ#ya&\u0011A(\u0010\u0002\u0005\u0005\u0006\u001cXM\u0003\u0002?/\u0005!\u0011.\u001c9m\u0003\t\u0011\b%\u0001\u0004=S:LGO\u0010\u000b\u0003\u0005\u000e\u0003\"\u0001\t\u0001\t\u000bM\u001a\u0001\u0019A\u001b\u0002\u0017\t,\u0018\u000e\u001c3TiJLgn\u001a\u000b\u0003\r*\u0003\"a\u0012%\u000f\u0005\u0011r\u0013BA%3\u00055\u0019FO]5oO\n+\u0018\u000e\u001c3fe\")1\n\u0002a\u0001\r\u0006\u00111OY\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002C\u001d\"91'\u0002I\u0001\u0002\u0004)\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u0002#*\u0012QGU\u0016\u0002'B\u0011A+W\u0007\u0002+*\u0011akV\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001W\u000e\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002[+\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005i\u0006C\u00010d\u001b\u0005y&B\u00011b\u0003\u0011a\u0017M\\4\u000b\u0003\t\fAA[1wC&\u0011Am\u0018\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003\u001d\u0004\"\u0001\n5\n\u0005%\\\"aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u00017p!\t!S.\u0003\u0002o7\t\u0019\u0011I\\=\t\u000fAL\u0011\u0011!a\u0001O\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012a\u001d\t\u0004i^dW\"A;\u000b\u0005Y\\\u0012AC2pY2,7\r^5p]&\u0011\u00010\u001e\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002|}B\u0011A\u0005`\u0005\u0003{n\u0011qAQ8pY\u0016\fg\u000eC\u0004q\u0017\u0005\u0005\t\u0019\u00017\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004;\u0006\r\u0001b\u00029\r\u0003\u0003\u0005\raZ\u0001\tQ\u0006\u001c\bnQ8eKR\tq-\u0001\u0004fcV\fGn\u001d\u000b\u0004w\u00065\u0001b\u00029\u000f\u0003\u0003\u0005\r\u0001\\\u0001\t\u000b2+U*\u0012(U'B\u0011\u0001\u0005E\n\u0006!\u0005U\u0011\u0011\u0005\t\u0007\u0003/\ti\"\u000e\"\u000e\u0005\u0005e!bAA\u000e7\u00059!/\u001e8uS6,\u0017\u0002BA\u0010\u00033\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82!\u0011\t\u0019#!\u000b\u000e\u0005\u0005\u0015\"bAA\u0014C\u0006\u0011\u0011n\\\u0005\u0004c\u0005\u0015BCAA\t\u0003!!xn\u0015;sS:<G#A/\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007\t\u000b)\u0004C\u00034'\u0001\u0007Q'A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005m\u0012\u0011\t\t\u0005I\u0005uR'C\u0002\u0002@m\u0011aa\u00149uS>t\u0007\u0002CA\")\u0005\u0005\t\u0019\u0001\"\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002JA\u0019a,a\u0013\n\u0007\u00055sL\u0001\u0004PE*,7\r\u001e"
)
public class ELEMENTS extends DFAContentModel implements Product, Serializable {
   private final Base.RegExp r;

   public static Option unapply(final ELEMENTS x$0) {
      return ELEMENTS$.MODULE$.unapply(x$0);
   }

   public static ELEMENTS apply(final Base.RegExp r) {
      return ELEMENTS$.MODULE$.apply(r);
   }

   public static Function1 andThen(final Function1 g) {
      return ELEMENTS$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ELEMENTS$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Base.RegExp r() {
      return this.r;
   }

   public StringBuilder buildString(final StringBuilder sb) {
      return ContentModel$.MODULE$.buildString(this.r(), sb);
   }

   public ELEMENTS copy(final Base.RegExp r) {
      return new ELEMENTS(r);
   }

   public Base.RegExp copy$default$1() {
      return this.r();
   }

   public String productPrefix() {
      return "ELEMENTS";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.r();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ELEMENTS;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "r";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof ELEMENTS) {
               label40: {
                  ELEMENTS var4 = (ELEMENTS)x$1;
                  Base.RegExp var10000 = this.r();
                  Base.RegExp var5 = var4.r();
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

   public ELEMENTS(final Base.RegExp r) {
      this.r = r;
      Product.$init$(this);
   }
}

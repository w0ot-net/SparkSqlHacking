package scala.xml.dtd;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Md\u0001B\r\u001b\u0001\u0006B\u0001B\u000e\u0001\u0003\u0016\u0004%\ta\u000e\u0005\t\u0001\u0002\u0011\t\u0012)A\u0005q!A\u0011\t\u0001BK\u0002\u0013\u0005!\t\u0003\u0005K\u0001\tE\t\u0015!\u0003D\u0011\u0015Y\u0005\u0001\"\u0001M\u0011\u0015\u0001\u0006\u0001\"\u0011R\u0011\u001d9\u0006!!A\u0005\u0002aCqa\u0017\u0001\u0012\u0002\u0013\u0005A\fC\u0004h\u0001E\u0005I\u0011\u00015\t\u000f)\u0004\u0011\u0011!C!W\"91\u000fAA\u0001\n\u0003!\bb\u0002=\u0001\u0003\u0003%\t!\u001f\u0005\t\u007f\u0002\t\t\u0011\"\u0011\u0002\u0002!I\u0011q\u0002\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u0003\u0005\n\u00037\u0001\u0011\u0011!C!\u0003;A\u0011\"!\t\u0001\u0003\u0003%\t%a\t\t\u0013\u0005\u0015\u0002!!A\u0005B\u0005\u001dr!CA\u00165\u0005\u0005\t\u0012AA\u0017\r!I\"$!A\t\u0002\u0005=\u0002BB&\u0014\t\u0003\t9\u0005C\u0005\u0002JM\t\t\u0011\"\u0012\u0002L!I\u0011QJ\n\u0002\u0002\u0013\u0005\u0015q\n\u0005\n\u0003+\u001a\u0012\u0011!CA\u0003/B\u0011\"!\u001b\u0014\u0003\u0003%I!a\u001b\u0003\u0017\u0005#H\u000fT5ti\u0012+7\r\u001c\u0006\u00037q\t1\u0001\u001a;e\u0015\tib$A\u0002y[2T\u0011aH\u0001\u0006g\u000e\fG.Y\u0002\u0001'\u0011\u0001!E\n\u0016\u0011\u0005\r\"S\"\u0001\u000e\n\u0005\u0015R\"AC'be.,\b\u000fR3dYB\u0011q\u0005K\u0007\u0002=%\u0011\u0011F\b\u0002\b!J|G-^2u!\tY3G\u0004\u0002-c9\u0011Q\u0006M\u0007\u0002])\u0011q\u0006I\u0001\u0007yI|w\u000e\u001e \n\u0003}I!A\r\u0010\u0002\u000fA\f7m[1hK&\u0011A'\u000e\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003ey\tAA\\1nKV\t\u0001\b\u0005\u0002:{9\u0011!h\u000f\t\u0003[yI!\u0001\u0010\u0010\u0002\rA\u0013X\rZ3g\u0013\tqtH\u0001\u0004TiJLgn\u001a\u0006\u0003yy\tQA\\1nK\u0002\nQ!\u0019;ueN,\u0012a\u0011\t\u0004\t\u0016;eBA\u00142\u0013\t1UG\u0001\u0003MSN$\bCA\u0012I\u0013\tI%D\u0001\u0005BiR\u0014H)Z2m\u0003\u0019\tG\u000f\u001e:tA\u00051A(\u001b8jiz\"2!\u0014(P!\t\u0019\u0003\u0001C\u00037\u000b\u0001\u0007\u0001\bC\u0003B\u000b\u0001\u00071)A\u0006ck&dGm\u0015;sS:<GC\u0001*V!\t!5+\u0003\u0002Uk\ti1\u000b\u001e:j]\u001e\u0014U/\u001b7eKJDQA\u0016\u0004A\u0002I\u000b!a\u001d2\u0002\t\r|\u0007/\u001f\u000b\u0004\u001bfS\u0006b\u0002\u001c\b!\u0003\u0005\r\u0001\u000f\u0005\b\u0003\u001e\u0001\n\u00111\u0001D\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012!\u0018\u0016\u0003qy[\u0013a\u0018\t\u0003A\u0016l\u0011!\u0019\u0006\u0003E\u000e\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005\u0011t\u0012AC1o]>$\u0018\r^5p]&\u0011a-\u0019\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002S*\u00121IX\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u00031\u0004\"!\u001c:\u000e\u00039T!a\u001c9\u0002\t1\fgn\u001a\u0006\u0002c\u0006!!.\u0019<b\u0013\tqd.\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001v!\t9c/\u0003\u0002x=\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011!0 \t\u0003OmL!\u0001 \u0010\u0003\u0007\u0005s\u0017\u0010C\u0004\u007f\u0019\u0005\u0005\t\u0019A;\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t\u0019\u0001E\u0003\u0002\u0006\u0005-!0\u0004\u0002\u0002\b)\u0019\u0011\u0011\u0002\u0010\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\u000e\u0005\u001d!\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u0005\u0002\u001aA\u0019q%!\u0006\n\u0007\u0005]aDA\u0004C_>dW-\u00198\t\u000fyt\u0011\u0011!a\u0001u\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\ra\u0017q\u0004\u0005\b}>\t\t\u00111\u0001v\u0003!A\u0017m\u001d5D_\u0012,G#A;\u0002\r\u0015\fX/\u00197t)\u0011\t\u0019\"!\u000b\t\u000fy\f\u0012\u0011!a\u0001u\u0006Y\u0011\t\u001e;MSN$H)Z2m!\t\u00193cE\u0003\u0014\u0003c\ti\u0004E\u0004\u00024\u0005e\u0002hQ'\u000e\u0005\u0005U\"bAA\u001c=\u00059!/\u001e8uS6,\u0017\u0002BA\u001e\u0003k\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83!\u0011\ty$!\u0012\u000e\u0005\u0005\u0005#bAA\"a\u0006\u0011\u0011n\\\u0005\u0004i\u0005\u0005CCAA\u0017\u0003!!xn\u0015;sS:<G#\u00017\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000b5\u000b\t&a\u0015\t\u000bY2\u0002\u0019\u0001\u001d\t\u000b\u00053\u0002\u0019A\"\u0002\u000fUt\u0017\r\u001d9msR!\u0011\u0011LA3!\u00159\u00131LA0\u0013\r\tiF\b\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000b\u001d\n\t\u0007O\"\n\u0007\u0005\rdD\u0001\u0004UkBdWM\r\u0005\t\u0003O:\u0012\u0011!a\u0001\u001b\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u00055\u0004cA7\u0002p%\u0019\u0011\u0011\u000f8\u0003\r=\u0013'.Z2u\u0001"
)
public class AttListDecl extends MarkupDecl implements Product, Serializable {
   private final String name;
   private final List attrs;

   public static Option unapply(final AttListDecl x$0) {
      return AttListDecl$.MODULE$.unapply(x$0);
   }

   public static AttListDecl apply(final String name, final List attrs) {
      return AttListDecl$.MODULE$.apply(name, attrs);
   }

   public static Function1 tupled() {
      return AttListDecl$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return AttListDecl$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String name() {
      return this.name;
   }

   public List attrs() {
      return this.attrs;
   }

   public StringBuilder buildString(final StringBuilder sb) {
      return sb.append((new java.lang.StringBuilder(12)).append("<!ATTLIST ").append(this.name()).append("\n").append(this.attrs().mkString("\n")).append(">").toString());
   }

   public AttListDecl copy(final String name, final List attrs) {
      return new AttListDecl(name, attrs);
   }

   public String copy$default$1() {
      return this.name();
   }

   public List copy$default$2() {
      return this.attrs();
   }

   public String productPrefix() {
      return "AttListDecl";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.name();
         case 1:
            return this.attrs();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof AttListDecl;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "name";
         case 1:
            return "attrs";
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
            if (x$1 instanceof AttListDecl) {
               label48: {
                  AttListDecl var4 = (AttListDecl)x$1;
                  String var10000 = this.name();
                  String var5 = var4.name();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  List var7 = this.attrs();
                  List var6 = var4.attrs();
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

   public AttListDecl(final String name, final List attrs) {
      this.name = name;
      this.attrs = attrs;
      Product.$init$(this);
   }
}

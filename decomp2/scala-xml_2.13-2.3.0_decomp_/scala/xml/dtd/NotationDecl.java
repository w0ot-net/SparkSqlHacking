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
   bytes = "\u0006\u0005\u00055d\u0001B\r\u001b\u0001\u0006B\u0001B\u000e\u0001\u0003\u0016\u0004%\ta\u000e\u0005\t\u0001\u0002\u0011\t\u0012)A\u0005q!A\u0011\t\u0001BK\u0002\u0013\u0005!\t\u0003\u0005G\u0001\tE\t\u0015!\u0003D\u0011\u00159\u0005\u0001\"\u0001I\u0011\u0015a\u0005\u0001\"\u0011N\u0011\u001d!\u0006!!A\u0005\u0002UCq\u0001\u0017\u0001\u0012\u0002\u0013\u0005\u0011\fC\u0004e\u0001E\u0005I\u0011A3\t\u000f\u001d\u0004\u0011\u0011!C!Q\"9\u0001\u000fAA\u0001\n\u0003\t\bbB;\u0001\u0003\u0003%\tA\u001e\u0005\by\u0002\t\t\u0011\"\u0011~\u0011%\tI\u0001AA\u0001\n\u0003\tY\u0001C\u0005\u0002\u0016\u0001\t\t\u0011\"\u0011\u0002\u0018!I\u00111\u0004\u0001\u0002\u0002\u0013\u0005\u0013Q\u0004\u0005\n\u0003?\u0001\u0011\u0011!C!\u0003C9\u0011\"!\n\u001b\u0003\u0003E\t!a\n\u0007\u0011eQ\u0012\u0011!E\u0001\u0003SAaaR\n\u0005\u0002\u0005\u0005\u0003\"CA\"'\u0005\u0005IQIA#\u0011%\t9eEA\u0001\n\u0003\u000bI\u0005C\u0005\u0002PM\t\t\u0011\"!\u0002R!I\u00111M\n\u0002\u0002\u0013%\u0011Q\r\u0002\r\u001d>$\u0018\r^5p]\u0012+7\r\u001c\u0006\u00037q\t1\u0001\u001a;e\u0015\tib$A\u0002y[2T\u0011aH\u0001\u0006g\u000e\fG.Y\u0002\u0001'\u0011\u0001!E\n\u0016\u0011\u0005\r\"S\"\u0001\u000e\n\u0005\u0015R\"AC'be.,\b\u000fR3dYB\u0011q\u0005K\u0007\u0002=%\u0011\u0011F\b\u0002\b!J|G-^2u!\tY3G\u0004\u0002-c9\u0011Q\u0006M\u0007\u0002])\u0011q\u0006I\u0001\u0007yI|w\u000e\u001e \n\u0003}I!A\r\u0010\u0002\u000fA\f7m[1hK&\u0011A'\u000e\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003ey\tAA\\1nKV\t\u0001\b\u0005\u0002:{9\u0011!h\u000f\t\u0003[yI!\u0001\u0010\u0010\u0002\rA\u0013X\rZ3g\u0013\tqtH\u0001\u0004TiJLgn\u001a\u0006\u0003yy\tQA\\1nK\u0002\nQ!\u001a=u\u0013\u0012+\u0012a\u0011\t\u0003G\u0011K!!\u0012\u000e\u0003\u0015\u0015CH/\u001a:oC2LE)\u0001\u0004fqRLE\tI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007%S5\n\u0005\u0002$\u0001!)a'\u0002a\u0001q!)\u0011)\u0002a\u0001\u0007\u0006Y!-^5mIN#(/\u001b8h)\tq%\u000b\u0005\u0002P!:\u0011q%M\u0005\u0003#V\u0012Qb\u0015;sS:<')^5mI\u0016\u0014\b\"B*\u0007\u0001\u0004q\u0015AA:c\u0003\u0011\u0019w\u000e]=\u0015\u0007%3v\u000bC\u00047\u000fA\u0005\t\u0019\u0001\u001d\t\u000f\u0005;\u0001\u0013!a\u0001\u0007\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u0001.+\u0005aZ6&\u0001/\u0011\u0005u\u0013W\"\u00010\u000b\u0005}\u0003\u0017!C;oG\",7m[3e\u0015\t\tg$\u0001\u0006b]:|G/\u0019;j_:L!a\u00190\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003\u0019T#aQ.\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005I\u0007C\u00016p\u001b\u0005Y'B\u00017n\u0003\u0011a\u0017M\\4\u000b\u00039\fAA[1wC&\u0011ah[\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002eB\u0011qe]\u0005\u0003iz\u00111!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"a\u001e>\u0011\u0005\u001dB\u0018BA=\u001f\u0005\r\te.\u001f\u0005\bw2\t\t\u00111\u0001s\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\ta\u0010\u0005\u0003\u0000\u0003\u000b9XBAA\u0001\u0015\r\t\u0019AH\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\u0004\u0003\u0003\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011QBA\n!\r9\u0013qB\u0005\u0004\u0003#q\"a\u0002\"p_2,\u0017M\u001c\u0005\bw:\t\t\u00111\u0001x\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007%\fI\u0002C\u0004|\u001f\u0005\u0005\t\u0019\u0001:\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012A]\u0001\u0007KF,\u0018\r\\:\u0015\t\u00055\u00111\u0005\u0005\bwF\t\t\u00111\u0001x\u00031qu\u000e^1uS>tG)Z2m!\t\u00193cE\u0003\u0014\u0003W\t9\u0004E\u0004\u0002.\u0005M\u0002hQ%\u000e\u0005\u0005=\"bAA\u0019=\u00059!/\u001e8uS6,\u0017\u0002BA\u001b\u0003_\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83!\u0011\tI$a\u0010\u000e\u0005\u0005m\"bAA\u001f[\u0006\u0011\u0011n\\\u0005\u0004i\u0005mBCAA\u0014\u0003!!xn\u0015;sS:<G#A5\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000b%\u000bY%!\u0014\t\u000bY2\u0002\u0019\u0001\u001d\t\u000b\u00053\u0002\u0019A\"\u0002\u000fUt\u0017\r\u001d9msR!\u00111KA0!\u00159\u0013QKA-\u0013\r\t9F\b\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000b\u001d\nY\u0006O\"\n\u0007\u0005ucD\u0001\u0004UkBdWM\r\u0005\t\u0003C:\u0012\u0011!a\u0001\u0013\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005\u001d\u0004c\u00016\u0002j%\u0019\u00111N6\u0003\r=\u0013'.Z2u\u0001"
)
public class NotationDecl extends MarkupDecl implements Product, Serializable {
   private final String name;
   private final ExternalID extID;

   public static Option unapply(final NotationDecl x$0) {
      return NotationDecl$.MODULE$.unapply(x$0);
   }

   public static NotationDecl apply(final String name, final ExternalID extID) {
      return NotationDecl$.MODULE$.apply(name, extID);
   }

   public static Function1 tupled() {
      return NotationDecl$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return NotationDecl$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String name() {
      return this.name;
   }

   public ExternalID extID() {
      return this.extID;
   }

   public StringBuilder buildString(final StringBuilder sb) {
      sb.append((new java.lang.StringBuilder(12)).append("<!NOTATION ").append(this.name()).append(" ").toString());
      return this.extID().buildString(sb).append('>');
   }

   public NotationDecl copy(final String name, final ExternalID extID) {
      return new NotationDecl(name, extID);
   }

   public String copy$default$1() {
      return this.name();
   }

   public ExternalID copy$default$2() {
      return this.extID();
   }

   public String productPrefix() {
      return "NotationDecl";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.name();
         case 1:
            return this.extID();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof NotationDecl;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "name";
         case 1:
            return "extID";
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
            if (x$1 instanceof NotationDecl) {
               label48: {
                  NotationDecl var4 = (NotationDecl)x$1;
                  String var10000 = this.name();
                  String var5 = var4.name();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  ExternalID var7 = this.extID();
                  ExternalID var6 = var4.extID();
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

   public NotationDecl(final String name, final ExternalID extID) {
      this.name = name;
      this.extID = extID;
      Product.$init$(this);
   }
}

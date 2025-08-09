package scala.xml.dtd;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.xml.Utility$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ed\u0001\u0002\u000f\u001e\u0001\u0012B\u0001\u0002\u000f\u0001\u0003\u0016\u0004%\t!\u000f\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005u!A1\t\u0001BK\u0002\u0013\u0005A\t\u0003\u0005J\u0001\tE\t\u0015!\u0003F\u0011!Q\u0005A!f\u0001\n\u0003Y\u0005\u0002C+\u0001\u0005#\u0005\u000b\u0011\u0002'\t\u000bY\u0003A\u0011A,\t\u000bq\u0003AQI/\t\u000fy\u0003\u0011\u0011!C\u0001?\"91\rAI\u0001\n\u0003!\u0007bB8\u0001#\u0003%\t\u0001\u001d\u0005\be\u0002\t\n\u0011\"\u0001t\u0011\u001d)\b!!A\u0005BYDqA \u0001\u0002\u0002\u0013\u0005q\u0010C\u0005\u0002\b\u0001\t\t\u0011\"\u0001\u0002\n!I\u0011Q\u0003\u0001\u0002\u0002\u0013\u0005\u0013q\u0003\u0005\n\u0003?\u0001\u0011\u0011!C\u0001\u0003CA\u0011\"a\u000b\u0001\u0003\u0003%\t%!\f\t\u0013\u0005E\u0002!!A\u0005B\u0005M\u0002\"CA\u001b\u0001\u0005\u0005I\u0011IA\u001c\u000f\u001d\tY$\bE\u0001\u0003{1a\u0001H\u000f\t\u0002\u0005}\u0002B\u0002,\u0017\t\u0003\tY\u0005C\u0004\u0002NY!\t!a\u0014\t\u0013\u00055c#!A\u0005\u0002\u0006M\u0003\"CA.-\u0005\u0005I\u0011QA/\u0011%\tyGFA\u0001\n\u0013\t\tHA\u0004E_\u000e$\u0016\u0010]3\u000b\u0005yy\u0012a\u00013uI*\u0011\u0001%I\u0001\u0004q6d'\"\u0001\u0012\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M!\u0001!J\u0015-!\t1s%D\u0001\"\u0013\tA\u0013E\u0001\u0004B]f\u0014VM\u001a\t\u0003M)J!aK\u0011\u0003\u000fA\u0013x\u000eZ;diB\u0011Q&\u000e\b\u0003]Mr!a\f\u001a\u000e\u0003AR!!M\u0012\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0011\u0013B\u0001\u001b\"\u0003\u001d\u0001\u0018mY6bO\u0016L!AN\u001c\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005Q\n\u0013\u0001\u00028b[\u0016,\u0012A\u000f\t\u0003w}r!\u0001P\u001f\u0011\u0005=\n\u0013B\u0001 \"\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001)\u0011\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005y\n\u0013!\u00028b[\u0016\u0004\u0013!B3yi&#U#A#\u0011\u0005\u0019;U\"A\u000f\n\u0005!k\"AC#yi\u0016\u0014h.\u00197J\t\u00061Q\r\u001f;J\t\u0002\n\u0011\"\u001b8u'V\u00147/\u001a;\u0016\u00031\u00032!\u0014)S\u001b\u0005q%BA(\"\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003#:\u00131aU3r!\t15+\u0003\u0002U;\t!A)Z2m\u0003)Ig\u000e^*vEN,G\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\taK&l\u0017\t\u0003\r\u0002AQ\u0001O\u0004A\u0002iBQaQ\u0004A\u0002\u0015CQAS\u0004A\u00021\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0002u\u0005!1m\u001c9z)\u0011A\u0006-\u00192\t\u000faJ\u0001\u0013!a\u0001u!91)\u0003I\u0001\u0002\u0004)\u0005b\u0002&\n!\u0003\u0005\r\u0001T\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005)'F\u0001\u001egW\u00059\u0007C\u00015n\u001b\u0005I'B\u00016l\u0003%)hn\u00195fG.,GM\u0003\u0002mC\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u00059L'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#A9+\u0005\u00153\u0017AD2paf$C-\u001a4bk2$HeM\u000b\u0002i*\u0012AJZ\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003]\u0004\"\u0001_?\u000e\u0003eT!A_>\u0002\t1\fgn\u001a\u0006\u0002y\u0006!!.\u0019<b\u0013\t\u0001\u00150\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002\u0002A\u0019a%a\u0001\n\u0007\u0005\u0015\u0011EA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002\f\u0005E\u0001c\u0001\u0014\u0002\u000e%\u0019\u0011qB\u0011\u0003\u0007\u0005s\u0017\u0010C\u0005\u0002\u0014=\t\t\u00111\u0001\u0002\u0002\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!\u0007\u0011\u000b5\u000bY\"a\u0003\n\u0007\u0005uaJ\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u0012\u0003S\u00012AJA\u0013\u0013\r\t9#\t\u0002\b\u0005>|G.Z1o\u0011%\t\u0019\"EA\u0001\u0002\u0004\tY!\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,GcA<\u00020!I\u00111\u0003\n\u0002\u0002\u0003\u0007\u0011\u0011A\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011\u0011A\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005\r\u0012\u0011\b\u0005\n\u0003'!\u0012\u0011!a\u0001\u0003\u0017\tq\u0001R8d)f\u0004X\r\u0005\u0002G-M!a#JA!!\u0011\t\u0019%!\u0013\u000e\u0005\u0005\u0015#bAA$w\u0006\u0011\u0011n\\\u0005\u0004m\u0005\u0015CCAA\u001f\u0003\u0015\t\u0007\u000f\u001d7z)\rA\u0016\u0011\u000b\u0005\u0006qa\u0001\rA\u000f\u000b\b1\u0006U\u0013qKA-\u0011\u0015A\u0014\u00041\u0001;\u0011\u0015\u0019\u0015\u00041\u0001F\u0011\u0015Q\u0015\u00041\u0001M\u0003\u001d)h.\u00199qYf$B!a\u0018\u0002lA)a%!\u0019\u0002f%\u0019\u00111M\u0011\u0003\r=\u0003H/[8o!\u00191\u0013q\r\u001eF\u0019&\u0019\u0011\u0011N\u0011\u0003\rQ+\b\u000f\\34\u0011!\tiGGA\u0001\u0002\u0004A\u0016a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u000f\t\u0004q\u0006U\u0014bAA<s\n1qJ\u00196fGR\u0004"
)
public class DocType implements Product, Serializable {
   private final String name;
   private final ExternalID extID;
   private final Seq intSubset;

   public static Option unapply(final DocType x$0) {
      return DocType$.MODULE$.unapply(x$0);
   }

   public static DocType apply(final String name, final ExternalID extID, final Seq intSubset) {
      return DocType$.MODULE$.apply(name, extID, intSubset);
   }

   public static DocType apply(final String name) {
      return DocType$.MODULE$.apply(name);
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

   public Seq intSubset() {
      return this.intSubset;
   }

   public final String toString() {
      return (new StringBuilder(12)).append("<!DOCTYPE ").append(this.name()).append(" ").append(this.extID()).append(this.intString$1()).append(">").toString();
   }

   public DocType copy(final String name, final ExternalID extID, final Seq intSubset) {
      return new DocType(name, extID, intSubset);
   }

   public String copy$default$1() {
      return this.name();
   }

   public ExternalID copy$default$2() {
      return this.extID();
   }

   public Seq copy$default$3() {
      return this.intSubset();
   }

   public String productPrefix() {
      return "DocType";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.name();
         case 1:
            return this.extID();
         case 2:
            return this.intSubset();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof DocType;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "name";
         case 1:
            return "extID";
         case 2:
            return "intSubset";
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
            if (x$1 instanceof DocType) {
               label56: {
                  DocType var4 = (DocType)x$1;
                  String var10000 = this.name();
                  String var5 = var4.name();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  ExternalID var8 = this.extID();
                  ExternalID var6 = var4.extID();
                  if (var8 == null) {
                     if (var6 != null) {
                        break label56;
                     }
                  } else if (!var8.equals(var6)) {
                     break label56;
                  }

                  Seq var9 = this.intSubset();
                  Seq var7 = var4.intSubset();
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

   private final String intString$1() {
      return this.intSubset().isEmpty() ? "" : this.intSubset().mkString("[", "", "]");
   }

   public DocType(final String name, final ExternalID extID, final Seq intSubset) {
      this.name = name;
      this.extID = extID;
      this.intSubset = intSubset;
      Product.$init$(this);
      if (!Utility$.MODULE$.isName(name)) {
         throw new IllegalArgumentException((new StringBuilder(20)).append(name).append(" must be an XML Name").toString());
      }
   }
}

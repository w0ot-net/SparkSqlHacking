package scala.xml.dtd;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Nil;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.xml.MetaData;
import scala.xml.Node$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]d\u0001B\u000e\u001d\u0001\u000eB\u0001\u0002\u000f\u0001\u0003\u0016\u0004%\t%\u000f\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005u!A1\t\u0001BK\u0002\u0013\u0005\u0013\b\u0003\u0005E\u0001\tE\t\u0015!\u0003;\u0011\u0015)\u0005\u0001\"\u0001G\u0011\u0015Q\u0005\u0001\"\u0001:\u0011\u0015Y\u0005\u0001\"\u0001M\u0011\u0015)\u0006\u0001\"\u0001W\u0011\u001dY\u0006!!A\u0005\u0002qCqa\u0018\u0001\u0012\u0002\u0013\u0005\u0001\rC\u0004l\u0001E\u0005I\u0011\u00011\t\u000f1\u0004\u0011\u0011!C![\"9Q\u000fAA\u0001\n\u00031\bb\u0002>\u0001\u0003\u0003%\ta\u001f\u0005\n\u0003\u0007\u0001\u0011\u0011!C!\u0003\u000bA\u0011\"a\u0005\u0001\u0003\u0003%\t!!\u0006\t\u0013\u0005}\u0001!!A\u0005B\u0005\u0005\u0002\"CA\u0013\u0001\u0005\u0005I\u0011IA\u0014\u0011%\tI\u0003AA\u0001\n\u0003\nYcB\u0005\u00020q\t\t\u0011#\u0001\u00022\u0019A1\u0004HA\u0001\u0012\u0003\t\u0019\u0004\u0003\u0004F+\u0011\u0005\u00111\n\u0005\n\u0003\u001b*\u0012\u0011!C#\u0003\u001fB\u0011\"!\u0015\u0016\u0003\u0003%\t)a\u0015\t\u0013\u0005eS#!A\u0005\u0002\u0006m\u0003\"CA7+\u0005\u0005I\u0011BA8\u0005!\u0001VO\u00197jG&#%BA\u000f\u001f\u0003\r!G\u000f\u001a\u0006\u0003?\u0001\n1\u0001_7m\u0015\u0005\t\u0013!B:dC2\f7\u0001A\n\u0005\u0001\u0011BC\u0006\u0005\u0002&M5\tA$\u0003\u0002(9\tQQ\t\u001f;fe:\fG.\u0013#\u0011\u0005%RS\"\u0001\u0011\n\u0005-\u0002#a\u0002)s_\u0012,8\r\u001e\t\u0003[Ur!AL\u001a\u000f\u0005=\u0012T\"\u0001\u0019\u000b\u0005E\u0012\u0013A\u0002\u001fs_>$h(C\u0001\"\u0013\t!\u0004%A\u0004qC\u000e\\\u0017mZ3\n\u0005Y:$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001b!\u0003!\u0001XO\u00197jG&#W#\u0001\u001e\u0011\u0005mzdB\u0001\u001f>!\ty\u0003%\u0003\u0002?A\u00051\u0001K]3eK\u001aL!\u0001Q!\u0003\rM#(/\u001b8h\u0015\tq\u0004%A\u0005qk\nd\u0017nY%eA\u0005A1/_:uK6LE-A\u0005tsN$X-\\%eA\u00051A(\u001b8jiz\"2a\u0012%J!\t)\u0003\u0001C\u00039\u000b\u0001\u0007!\bC\u0003D\u000b\u0001\u0007!(A\u0003mC\n,G.A\u0005biR\u0014\u0018NY;uKV\tQ\n\u0005\u0002O%:\u0011q\nU\u0007\u0002=%\u0011\u0011KH\u0001 '\u000e\fG.\u0019,feNLwN\\*qK\u000eLg-[2SKR,(O\u001c+za\u0016\u001c\u0018BA*U\u0005M)\u0005\u0010^3s]\u0006d\u0017\nR!uiJL'-\u001e;f\u0015\t\tf$A\u0003dQ&dG-F\u0001X\u001d\tA\u0016L\u0004\u0002*g%\u0011!lN\u0001\u0004\u001d&d\u0017\u0001B2paf$2aR/_\u0011\u001dA\u0014\u0002%AA\u0002iBqaQ\u0005\u0011\u0002\u0003\u0007!(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003\u0005T#A\u000f2,\u0003\r\u0004\"\u0001Z5\u000e\u0003\u0015T!AZ4\u0002\u0013Ut7\r[3dW\u0016$'B\u00015!\u0003)\tgN\\8uCRLwN\\\u0005\u0003U\u0016\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u00018\u0011\u0005=$X\"\u00019\u000b\u0005E\u0014\u0018\u0001\u00027b]\u001eT\u0011a]\u0001\u0005U\u00064\u0018-\u0003\u0002Aa\u0006a\u0001O]8ek\u000e$\u0018I]5usV\tq\u000f\u0005\u0002*q&\u0011\u0011\u0010\t\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003y~\u0004\"!K?\n\u0005y\u0004#aA!os\"A\u0011\u0011\u0001\b\u0002\u0002\u0003\u0007q/A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u000f\u0001R!!\u0003\u0002\u0010ql!!a\u0003\u000b\u0007\u00055\u0001%\u0001\u0006d_2dWm\u0019;j_:LA!!\u0005\u0002\f\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t9\"!\b\u0011\u0007%\nI\"C\u0002\u0002\u001c\u0001\u0012qAQ8pY\u0016\fg\u000e\u0003\u0005\u0002\u0002A\t\t\u00111\u0001}\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u00079\f\u0019\u0003\u0003\u0005\u0002\u0002E\t\t\u00111\u0001x\u0003!A\u0017m\u001d5D_\u0012,G#A<\u0002\r\u0015\fX/\u00197t)\u0011\t9\"!\f\t\u0011\u0005\u00051#!AA\u0002q\f\u0001\u0002U;cY&\u001c\u0017\n\u0012\t\u0003KU\u0019R!FA\u001b\u0003\u0003\u0002r!a\u000e\u0002>iRt)\u0004\u0002\u0002:)\u0019\u00111\b\u0011\u0002\u000fI,h\u000e^5nK&!\u0011qHA\u001d\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\t\u0005\u0003\u0007\nI%\u0004\u0002\u0002F)\u0019\u0011q\t:\u0002\u0005%|\u0017b\u0001\u001c\u0002FQ\u0011\u0011\u0011G\u0001\ti>\u001cFO]5oOR\ta.A\u0003baBd\u0017\u0010F\u0003H\u0003+\n9\u0006C\u000391\u0001\u0007!\bC\u0003D1\u0001\u0007!(A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005u\u0013\u0011\u000e\t\u0006S\u0005}\u00131M\u0005\u0004\u0003C\u0002#AB(qi&|g\u000eE\u0003*\u0003KR$(C\u0002\u0002h\u0001\u0012a\u0001V;qY\u0016\u0014\u0004\u0002CA63\u0005\u0005\t\u0019A$\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002rA\u0019q.a\u001d\n\u0007\u0005U\u0004O\u0001\u0004PE*,7\r\u001e"
)
public class PublicID extends ExternalID implements Product, Serializable {
   private final String publicId;
   private final String systemId;

   public static Option unapply(final PublicID x$0) {
      return PublicID$.MODULE$.unapply(x$0);
   }

   public static PublicID apply(final String publicId, final String systemId) {
      return PublicID$.MODULE$.apply(publicId, systemId);
   }

   public static Function1 tupled() {
      return PublicID$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return PublicID$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String publicId() {
      return this.publicId;
   }

   public String systemId() {
      return this.systemId;
   }

   public String label() {
      return "#PI";
   }

   public MetaData attribute() {
      return Node$.MODULE$.NoAttributes();
   }

   public Nil child() {
      return .MODULE$;
   }

   public PublicID copy(final String publicId, final String systemId) {
      return new PublicID(publicId, systemId);
   }

   public String copy$default$1() {
      return this.publicId();
   }

   public String copy$default$2() {
      return this.systemId();
   }

   public String productPrefix() {
      return "PublicID";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.publicId();
         case 1:
            return this.systemId();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof PublicID;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "publicId";
         case 1:
            return "systemId";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof PublicID) {
               label48: {
                  PublicID var4 = (PublicID)x$1;
                  String var10000 = this.publicId();
                  String var5 = var4.publicId();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  var10000 = this.systemId();
                  String var6 = var4.systemId();
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var6)) {
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

   public PublicID(final String publicId, final String systemId) {
      this.publicId = publicId;
      this.systemId = systemId;
      Product.$init$(this);
      if (!this.checkPubID(publicId)) {
         throw new IllegalArgumentException("publicId must consist of PubidChars");
      } else if (systemId != null && !this.checkSysID(systemId)) {
         throw new IllegalArgumentException("can't use both \" and ' in systemId");
      }
   }
}

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
   bytes = "\u0006\u0005\u0005\rc\u0001B\f\u0019\u0001~A\u0001\u0002\u000e\u0001\u0003\u0016\u0004%\t!\u000e\u0005\ts\u0001\u0011\t\u0012)A\u0005m!)!\b\u0001C\u0001w!)a\b\u0001C!\u007f!9a\tAA\u0001\n\u00039\u0005bB%\u0001#\u0003%\tA\u0013\u0005\b+\u0002\t\t\u0011\"\u0011W\u0011\u001dy\u0006!!A\u0005\u0002\u0001Dq\u0001\u001a\u0001\u0002\u0002\u0013\u0005Q\rC\u0004l\u0001\u0005\u0005I\u0011\t7\t\u000fM\u0004\u0011\u0011!C\u0001i\"9\u0011\u0010AA\u0001\n\u0003R\bb\u0002?\u0001\u0003\u0003%\t% \u0005\b}\u0002\t\t\u0011\"\u0011\u0000\u0011%\t\t\u0001AA\u0001\n\u0003\n\u0019aB\u0005\u0002\ba\t\t\u0011#\u0001\u0002\n\u0019Aq\u0003GA\u0001\u0012\u0003\tY\u0001\u0003\u0004;#\u0011\u0005\u00111\u0005\u0005\b}F\t\t\u0011\"\u0012\u0000\u0011%\t)#EA\u0001\n\u0003\u000b9\u0003C\u0005\u0002,E\t\t\u0011\"!\u0002.!I\u0011\u0011H\t\u0002\u0002\u0013%\u00111\b\u0002\u0007\u000bb$H)\u001a4\u000b\u0005eQ\u0012a\u00013uI*\u00111\u0004H\u0001\u0004q6d'\"A\u000f\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M!\u0001\u0001\t\u0013)!\t\t#%D\u0001\u0019\u0013\t\u0019\u0003DA\u0005F]RLG/\u001f#fMB\u0011QEJ\u0007\u00029%\u0011q\u0005\b\u0002\b!J|G-^2u!\tI\u0013G\u0004\u0002+_9\u00111FL\u0007\u0002Y)\u0011QFH\u0001\u0007yI|w\u000e\u001e \n\u0003uI!\u0001\r\u000f\u0002\u000fA\f7m[1hK&\u0011!g\r\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003aq\tQ!\u001a=u\u0013\u0012+\u0012A\u000e\t\u0003C]J!\u0001\u000f\r\u0003\u0015\u0015CH/\u001a:oC2LE)\u0001\u0004fqRLE\tI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005qj\u0004CA\u0011\u0001\u0011\u0015!4\u00011\u00017\u0003-\u0011W/\u001b7e'R\u0014\u0018N\\4\u0015\u0005\u0001#\u0005CA!C\u001d\t)s&\u0003\u0002Dg\ti1\u000b\u001e:j]\u001e\u0014U/\u001b7eKJDQ!\u0012\u0003A\u0002\u0001\u000b!a\u001d2\u0002\t\r|\u0007/\u001f\u000b\u0003y!Cq\u0001N\u0003\u0011\u0002\u0003\u0007a'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003-S#A\u000e',\u00035\u0003\"AT*\u000e\u0003=S!\u0001U)\u0002\u0013Ut7\r[3dW\u0016$'B\u0001*\u001d\u0003)\tgN\\8uCRLwN\\\u0005\u0003)>\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tq\u000b\u0005\u0002Y;6\t\u0011L\u0003\u0002[7\u0006!A.\u00198h\u0015\u0005a\u0016\u0001\u00026bm\u0006L!AX-\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005\t\u0007CA\u0013c\u0013\t\u0019GDA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002gSB\u0011QeZ\u0005\u0003Qr\u00111!\u00118z\u0011\u001dQ\u0017\"!AA\u0002\u0005\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A7\u0011\u00079\fh-D\u0001p\u0015\t\u0001H$\u0001\u0006d_2dWm\u0019;j_:L!A]8\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0003kb\u0004\"!\n<\n\u0005]d\"a\u0002\"p_2,\u0017M\u001c\u0005\bU.\t\t\u00111\u0001g\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0005][\bb\u00026\r\u0003\u0003\u0005\r!Y\u0001\tQ\u0006\u001c\bnQ8eKR\t\u0011-\u0001\u0005u_N#(/\u001b8h)\u00059\u0016AB3rk\u0006d7\u000fF\u0002v\u0003\u000bAqA[\b\u0002\u0002\u0003\u0007a-\u0001\u0004FqR$UM\u001a\t\u0003CE\u0019R!EA\u0007\u00033\u0001b!a\u0004\u0002\u0016YbTBAA\t\u0015\r\t\u0019\u0002H\u0001\beVtG/[7f\u0013\u0011\t9\"!\u0005\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002\u001c\u0005\u0005RBAA\u000f\u0015\r\tybW\u0001\u0003S>L1AMA\u000f)\t\tI!A\u0003baBd\u0017\u0010F\u0002=\u0003SAQ\u0001\u000e\u000bA\u0002Y\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u00020\u0005U\u0002\u0003B\u0013\u00022YJ1!a\r\u001d\u0005\u0019y\u0005\u000f^5p]\"A\u0011qG\u000b\u0002\u0002\u0003\u0007A(A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u0010\u0011\u0007a\u000by$C\u0002\u0002Be\u0013aa\u00142kK\u000e$\b"
)
public class ExtDef extends EntityDef implements Product, Serializable {
   private final ExternalID extID;

   public static Option unapply(final ExtDef x$0) {
      return ExtDef$.MODULE$.unapply(x$0);
   }

   public static ExtDef apply(final ExternalID extID) {
      return ExtDef$.MODULE$.apply(extID);
   }

   public static Function1 andThen(final Function1 g) {
      return ExtDef$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ExtDef$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public ExternalID extID() {
      return this.extID;
   }

   public StringBuilder buildString(final StringBuilder sb) {
      return this.extID().buildString(sb);
   }

   public ExtDef copy(final ExternalID extID) {
      return new ExtDef(extID);
   }

   public ExternalID copy$default$1() {
      return this.extID();
   }

   public String productPrefix() {
      return "ExtDef";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.extID();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ExtDef;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "extID";
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
            if (x$1 instanceof ExtDef) {
               label40: {
                  ExtDef var4 = (ExtDef)x$1;
                  ExternalID var10000 = this.extID();
                  ExternalID var5 = var4.extID();
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

   public ExtDef(final ExternalID extID) {
      this.extID = extID;
      Product.$init$(this);
   }
}

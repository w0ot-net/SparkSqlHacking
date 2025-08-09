package scala.xml;

import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ub\u0001B\u000b\u0017\u0001nA\u0001\u0002\r\u0001\u0003\u0016\u0004%\t!\r\u0005\tu\u0001\u0011\t\u0012)A\u0005e!)1\b\u0001C\u0001y!9\u0001\tAA\u0001\n\u0003\t\u0005bB\"\u0001#\u0003%\t\u0001\u0012\u0005\b\u001f\u0002\t\t\u0011\"\u0011Q\u0011\u001dA\u0006!!A\u0005\u0002eCq!\u0018\u0001\u0002\u0002\u0013\u0005a\fC\u0004e\u0001\u0005\u0005I\u0011I3\t\u000f1\u0004\u0011\u0011!C\u0001[\"9!\u000fAA\u0001\n\u0003\u001a\bbB;\u0001\u0003\u0003%\tE\u001e\u0005\bo\u0002\t\t\u0011\"\u0011y\u000f\u001dQh#!A\t\u0002m4q!\u0006\f\u0002\u0002#\u0005A\u0010\u0003\u0004<\u001f\u0011\u0005\u0011\u0011\u0003\u0005\n\u0003'y\u0011\u0011!C#\u0003+A\u0011\"a\u0006\u0010\u0003\u0003%\t)!\u0007\t\u0013\u0005uq\"!A\u0005\u0002\u0006}\u0001\"CA\u0016\u001f\u0005\u0005I\u0011BA\u0017\u0005mi\u0015\r\u001c4pe6,G-\u0011;ue&\u0014W\u000f^3Fq\u000e,\u0007\u000f^5p]*\u0011q\u0003G\u0001\u0004q6d'\"A\r\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M!\u0001\u0001\b\u0013(!\ti\u0012E\u0004\u0002\u001f?5\t\u0001$\u0003\u0002!1\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u0012$\u0005A\u0011VO\u001c;j[\u0016,\u0005pY3qi&|gN\u0003\u0002!1A\u0011a$J\u0005\u0003Ma\u0011q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002)]9\u0011\u0011f\b\b\u0003U5j\u0011a\u000b\u0006\u0003Yi\ta\u0001\u0010:p_Rt\u0014\"A\r\n\u0005=\u001a#\u0001D*fe&\fG.\u001b>bE2,\u0017aA7tOV\t!\u0007\u0005\u00024o9\u0011A'\u000e\t\u0003UaI!A\u000e\r\u0002\rA\u0013X\rZ3g\u0013\tA\u0014H\u0001\u0004TiJLgn\u001a\u0006\u0003ma\tA!\\:hA\u00051A(\u001b8jiz\"\"!P \u0011\u0005y\u0002Q\"\u0001\f\t\u000bA\u001a\u0001\u0019\u0001\u001a\u0002\t\r|\u0007/\u001f\u000b\u0003{\tCq\u0001\r\u0003\u0011\u0002\u0003\u0007!'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003\u0015S#A\r$,\u0003\u001d\u0003\"\u0001S'\u000e\u0003%S!AS&\u0002\u0013Ut7\r[3dW\u0016$'B\u0001'\u0019\u0003)\tgN\\8uCRLwN\\\u0005\u0003\u001d&\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t\u0011\u000b\u0005\u0002S/6\t1K\u0003\u0002U+\u0006!A.\u00198h\u0015\u00051\u0016\u0001\u00026bm\u0006L!\u0001O*\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003i\u0003\"AH.\n\u0005qC\"aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HCA0c!\tq\u0002-\u0003\u0002b1\t\u0019\u0011I\\=\t\u000f\rD\u0011\u0011!a\u00015\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012A\u001a\t\u0004O*|V\"\u00015\u000b\u0005%D\u0012AC2pY2,7\r^5p]&\u00111\u000e\u001b\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002ocB\u0011ad\\\u0005\u0003ab\u0011qAQ8pY\u0016\fg\u000eC\u0004d\u0015\u0005\u0005\t\u0019A0\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0003#RDqaY\u0006\u0002\u0002\u0003\u0007!,\u0001\u0005iCND7i\u001c3f)\u0005Q\u0016AB3rk\u0006d7\u000f\u0006\u0002os\"91-DA\u0001\u0002\u0004y\u0016aG'bY\u001a|'/\\3e\u0003R$(/\u001b2vi\u0016,\u0005pY3qi&|g\u000e\u0005\u0002?\u001fM!q\"`A\u0004!\u0015q\u00181\u0001\u001a>\u001b\u0005y(bAA\u00011\u00059!/\u001e8uS6,\u0017bAA\u0003\u007f\n\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0011\t\u0005%\u0011qB\u0007\u0003\u0003\u0017Q1!!\u0004V\u0003\tIw.C\u00020\u0003\u0017!\u0012a_\u0001\ti>\u001cFO]5oOR\t\u0011+A\u0003baBd\u0017\u0010F\u0002>\u00037AQ\u0001\r\nA\u0002I\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002\"\u0005\u001d\u0002\u0003\u0002\u0010\u0002$IJ1!!\n\u0019\u0005\u0019y\u0005\u000f^5p]\"A\u0011\u0011F\n\u0002\u0002\u0003\u0007Q(A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\f\u0011\u0007I\u000b\t$C\u0002\u00024M\u0013aa\u00142kK\u000e$\b"
)
public class MalformedAttributeException extends RuntimeException implements Product {
   private final String msg;

   public static Option unapply(final MalformedAttributeException x$0) {
      return MalformedAttributeException$.MODULE$.unapply(x$0);
   }

   public static MalformedAttributeException apply(final String msg) {
      return MalformedAttributeException$.MODULE$.apply(msg);
   }

   public static Function1 andThen(final Function1 g) {
      return MalformedAttributeException$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return MalformedAttributeException$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String msg() {
      return this.msg;
   }

   public MalformedAttributeException copy(final String msg) {
      return new MalformedAttributeException(msg);
   }

   public String copy$default$1() {
      return this.msg();
   }

   public String productPrefix() {
      return "MalformedAttributeException";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.msg();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof MalformedAttributeException;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "msg";
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
            if (x$1 instanceof MalformedAttributeException) {
               label40: {
                  MalformedAttributeException var4 = (MalformedAttributeException)x$1;
                  String var10000 = this.msg();
                  String var5 = var4.msg();
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

   public MalformedAttributeException(final String msg) {
      super(msg);
      this.msg = msg;
      Product.$init$(this);
   }
}

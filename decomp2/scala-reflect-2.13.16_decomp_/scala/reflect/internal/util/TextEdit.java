package scala.reflect.internal.util;

import java.io.Serializable;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;
import scala.util.hashing.MurmurHash3.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015d\u0001\u0002\u000e\u001c\u0001\u0012B\u0001\u0002\u000f\u0001\u0003\u0016\u0004%\t!\u000f\u0005\t}\u0001\u0011\t\u0012)A\u0005u!Aq\b\u0001BK\u0002\u0013\u0005\u0001\t\u0003\u0005J\u0001\tE\t\u0015!\u0003B\u0011\u0015Q\u0005\u0001\"\u0001L\u0011\u0015y\u0005\u0001\"\u0001Q\u0011\u001d!\u0006!!A\u0005\u0002UCq\u0001\u0017\u0001\u0012\u0002\u0013\u0005\u0011\fC\u0004e\u0001E\u0005I\u0011A3\t\u000f\u001d\u0004\u0011\u0011!C!Q\"9\u0001\u000fAA\u0001\n\u0003\u0001\u0006bB9\u0001\u0003\u0003%\tA\u001d\u0005\bq\u0002\t\t\u0011\"\u0011z\u0011%\t\t\u0001AA\u0001\n\u0003\t\u0019\u0001C\u0005\u0002\u000e\u0001\t\t\u0011\"\u0011\u0002\u0010!I\u00111\u0003\u0001\u0002\u0002\u0013\u0005\u0013Q\u0003\u0005\n\u0003/\u0001\u0011\u0011!C!\u00033A\u0011\"a\u0007\u0001\u0003\u0003%\t%!\b\b\u0013\u0005\u00052$!A\t\u0002\u0005\rb\u0001\u0003\u000e\u001c\u0003\u0003E\t!!\n\t\r)#B\u0011AA\u001f\u0011%\t9\u0002FA\u0001\n\u000b\nI\u0002C\u0005\u0002@Q\t\t\u0011\"!\u0002B!I\u0011q\t\u000b\u0002\u0002\u0013\u0005\u0015\u0011\n\u0005\n\u00037\"\u0012\u0011!C\u0005\u0003;\u0012\u0001\u0002V3yi\u0016#\u0017\u000e\u001e\u0006\u00039u\tA!\u001e;jY*\u0011adH\u0001\tS:$XM\u001d8bY*\u0011\u0001%I\u0001\be\u00164G.Z2u\u0015\u0005\u0011\u0013!B:dC2\f7\u0001A\n\u0005\u0001\u0015JC\u0006\u0005\u0002'O5\t\u0011%\u0003\u0002)C\t1\u0011I\\=SK\u001a\u0004\"A\n\u0016\n\u0005-\n#a\u0002)s_\u0012,8\r\u001e\t\u0003[Ur!AL\u001a\u000f\u0005=\u0012T\"\u0001\u0019\u000b\u0005E\u001a\u0013A\u0002\u001fs_>$h(C\u0001#\u0013\t!\u0014%A\u0004qC\u000e\\\u0017mZ3\n\u0005Y:$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001b\"\u0003!\u0001xn]5uS>tW#\u0001\u001e\u0011\u0005mbT\"A\u000e\n\u0005uZ\"\u0001\u0003)pg&$\u0018n\u001c8\u0002\u0013A|7/\u001b;j_:\u0004\u0013a\u00028foR+\u0007\u0010^\u000b\u0002\u0003B\u0011!I\u0012\b\u0003\u0007\u0012\u0003\"aL\u0011\n\u0005\u0015\u000b\u0013A\u0002)sK\u0012,g-\u0003\u0002H\u0011\n11\u000b\u001e:j]\u001eT!!R\u0011\u0002\u00119,w\u000fV3yi\u0002\na\u0001P5oSRtDc\u0001'N\u001dB\u00111\b\u0001\u0005\u0006q\u0015\u0001\rA\u000f\u0005\u0006\u007f\u0015\u0001\r!Q\u0001\u0006I\u0016dG/Y\u000b\u0002#B\u0011aEU\u0005\u0003'\u0006\u00121!\u00138u\u0003\u0011\u0019w\u000e]=\u0015\u000713v\u000bC\u00049\u000fA\u0005\t\u0019\u0001\u001e\t\u000f}:\u0001\u0013!a\u0001\u0003\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u0001.+\u0005iZ6&\u0001/\u0011\u0005u\u0013W\"\u00010\u000b\u0005}\u0003\u0017!C;oG\",7m[3e\u0015\t\t\u0017%\u0001\u0006b]:|G/\u0019;j_:L!a\u00190\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003\u0019T#!Q.\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005I\u0007C\u00016p\u001b\u0005Y'B\u00017n\u0003\u0011a\u0017M\\4\u000b\u00039\fAA[1wC&\u0011qi[\u0001\raJ|G-^2u\u0003JLG/_\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\t\u0019h\u000f\u0005\u0002'i&\u0011Q/\t\u0002\u0004\u0003:L\bbB<\r\u0003\u0003\u0005\r!U\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003i\u00042a\u001f@t\u001b\u0005a(BA?\"\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003\u007fr\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011QAA\u0006!\r1\u0013qA\u0005\u0004\u0003\u0013\t#a\u0002\"p_2,\u0017M\u001c\u0005\bo:\t\t\u00111\u0001t\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007%\f\t\u0002C\u0004x\u001f\u0005\u0005\t\u0019A)\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012!U\u0001\ti>\u001cFO]5oOR\t\u0011.\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003\u000b\ty\u0002C\u0004x%\u0005\u0005\t\u0019A:\u0002\u0011Q+\u0007\u0010^#eSR\u0004\"a\u000f\u000b\u0014\u000bQ\t9#a\r\u0011\u000f\u0005%\u0012q\u0006\u001eB\u00196\u0011\u00111\u0006\u0006\u0004\u0003[\t\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003c\tYCA\tBEN$(/Y2u\rVt7\r^5p]J\u0002B!!\u000e\u0002<5\u0011\u0011q\u0007\u0006\u0004\u0003si\u0017AA5p\u0013\r1\u0014q\u0007\u000b\u0003\u0003G\tQ!\u00199qYf$R\u0001TA\"\u0003\u000bBQ\u0001O\fA\u0002iBQaP\fA\u0002\u0005\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002L\u0005]\u0003#\u0002\u0014\u0002N\u0005E\u0013bAA(C\t1q\n\u001d;j_:\u0004RAJA*u\u0005K1!!\u0016\"\u0005\u0019!V\u000f\u001d7fe!A\u0011\u0011\f\r\u0002\u0002\u0003\u0007A*A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\u0018\u0011\u0007)\f\t'C\u0002\u0002d-\u0014aa\u00142kK\u000e$\b"
)
public class TextEdit implements Product, Serializable {
   private final Position position;
   private final String newText;

   public static Option unapply(final TextEdit x$0) {
      return TextEdit$.MODULE$.unapply(x$0);
   }

   public static TextEdit apply(final Position position, final String newText) {
      TextEdit$ var10000 = TextEdit$.MODULE$;
      return new TextEdit(position, newText);
   }

   public static Function1 tupled() {
      return Function2.tupled$(TextEdit$.MODULE$);
   }

   public static Function1 curried() {
      return Function2.curried$(TextEdit$.MODULE$);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Position position() {
      return this.position;
   }

   public String newText() {
      return this.newText;
   }

   public int delta() {
      return this.newText().length() - (this.position().end() - this.position().start());
   }

   public TextEdit copy(final Position position, final String newText) {
      return new TextEdit(position, newText);
   }

   public Position copy$default$1() {
      return this.position();
   }

   public String copy$default$2() {
      return this.newText();
   }

   public String productPrefix() {
      return "TextEdit";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.position();
         case 1:
            return this.newText();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return new ScalaRunTime..anon.1(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof TextEdit;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "position";
         case 1:
            return "newText";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      return .MODULE$.productHash(this, -889275714, false);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      if (this != x$1) {
         if (x$1 instanceof TextEdit) {
            TextEdit var2 = (TextEdit)x$1;
            Position var10000 = this.position();
            Position var3 = var2.position();
            if (var10000 == null) {
               if (var3 != null) {
                  return false;
               }
            } else if (!var10000.equals(var3)) {
               return false;
            }

            String var5 = this.newText();
            String var4 = var2.newText();
            if (var5 == null) {
               if (var4 != null) {
                  return false;
               }
            } else if (!var5.equals(var4)) {
               return false;
            }

            if (var2.canEqual(this)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public TextEdit(final Position position, final String newText) {
      this.position = position;
      this.newText = newText;
   }
}

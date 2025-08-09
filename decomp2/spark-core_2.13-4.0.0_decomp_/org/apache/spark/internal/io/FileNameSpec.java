package org.apache.spark.internal.io;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mc\u0001B\r\u001b\u0005\u0016B\u0001b\u000f\u0001\u0003\u0016\u0004%\t\u0001\u0010\u0005\t\u000b\u0002\u0011\t\u0012)A\u0005{!Aa\t\u0001BK\u0002\u0013\u0005A\b\u0003\u0005H\u0001\tE\t\u0015!\u0003>\u0011\u0015A\u0005\u0001\"\u0001J\u0011\u001dq\u0005!!A\u0005\u0002=CqA\u0015\u0001\u0012\u0002\u0013\u00051\u000bC\u0004_\u0001E\u0005I\u0011A*\t\u000f}\u0003\u0011\u0011!C!A\"9\u0001\u000eAA\u0001\n\u0003I\u0007bB7\u0001\u0003\u0003%\tA\u001c\u0005\bi\u0002\t\t\u0011\"\u0011v\u0011\u001da\b!!A\u0005\u0002uD\u0011\"!\u0002\u0001\u0003\u0003%\t%a\u0002\t\u0013\u0005-\u0001!!A\u0005B\u00055\u0001\"CA\b\u0001\u0005\u0005I\u0011IA\t\u0011%\t\u0019\u0002AA\u0001\n\u0003\n)bB\u0005\u0002\u001ai\t\t\u0011#\u0001\u0002\u001c\u0019A\u0011DGA\u0001\u0012\u0003\ti\u0002\u0003\u0004I'\u0011\u0005\u00111\u0007\u0005\n\u0003\u001f\u0019\u0012\u0011!C#\u0003#A\u0011\"!\u000e\u0014\u0003\u0003%\t)a\u000e\t\u0013\u0005u2#!A\u0005\u0002\u0006}\u0002\"CA)'\u0005\u0005I\u0011BA*\u000511\u0015\u000e\\3OC6,7\u000b]3d\u0015\tYB$\u0001\u0002j_*\u0011QDH\u0001\tS:$XM\u001d8bY*\u0011q\u0004I\u0001\u0006gB\f'o\u001b\u0006\u0003C\t\na!\u00199bG\",'\"A\u0012\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u00011Cf\f\t\u0003O)j\u0011\u0001\u000b\u0006\u0002S\u0005)1oY1mC&\u00111\u0006\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u001dj\u0013B\u0001\u0018)\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001\r\u001d\u000f\u0005E2dB\u0001\u001a6\u001b\u0005\u0019$B\u0001\u001b%\u0003\u0019a$o\\8u}%\t\u0011&\u0003\u00028Q\u00059\u0001/Y2lC\u001e,\u0017BA\u001d;\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t9\u0004&\u0001\u0004qe\u00164\u0017\u000e_\u000b\u0002{A\u0011aH\u0011\b\u0003\u007f\u0001\u0003\"A\r\u0015\n\u0005\u0005C\u0013A\u0002)sK\u0012,g-\u0003\u0002D\t\n11\u000b\u001e:j]\u001eT!!\u0011\u0015\u0002\u000fA\u0014XMZ5yA\u000511/\u001e4gSb\fqa];gM&D\b%\u0001\u0004=S:LGO\u0010\u000b\u0004\u00152k\u0005CA&\u0001\u001b\u0005Q\u0002\"B\u001e\u0006\u0001\u0004i\u0004\"\u0002$\u0006\u0001\u0004i\u0014\u0001B2paf$2A\u0013)R\u0011\u001dYd\u0001%AA\u0002uBqA\u0012\u0004\u0011\u0002\u0003\u0007Q(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003QS#!P+,\u0003Y\u0003\"a\u0016/\u000e\u0003aS!!\u0017.\u0002\u0013Ut7\r[3dW\u0016$'BA.)\u0003)\tgN\\8uCRLwN\\\u0005\u0003;b\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A1\u0011\u0005\t<W\"A2\u000b\u0005\u0011,\u0017\u0001\u00027b]\u001eT\u0011AZ\u0001\u0005U\u00064\u0018-\u0003\u0002DG\u0006a\u0001O]8ek\u000e$\u0018I]5usV\t!\u000e\u0005\u0002(W&\u0011A\u000e\u000b\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003_J\u0004\"a\n9\n\u0005ED#aA!os\"91oCA\u0001\u0002\u0004Q\u0017a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001w!\r9(p\\\u0007\u0002q*\u0011\u0011\u0010K\u0001\u000bG>dG.Z2uS>t\u0017BA>y\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0007y\f\u0019\u0001\u0005\u0002(\u007f&\u0019\u0011\u0011\u0001\u0015\u0003\u000f\t{w\u000e\\3b]\"91/DA\u0001\u0002\u0004y\u0017A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2!YA\u0005\u0011\u001d\u0019h\"!AA\u0002)\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002U\u0006AAo\\*ue&tw\rF\u0001b\u0003\u0019)\u0017/^1mgR\u0019a0a\u0006\t\u000fM\f\u0012\u0011!a\u0001_\u0006aa)\u001b7f\u001d\u0006lWm\u00159fGB\u00111jE\n\u0006'\u0005}\u00111\u0006\t\b\u0003C\t9#P\u001fK\u001b\t\t\u0019CC\u0002\u0002&!\nqA];oi&lW-\u0003\u0003\u0002*\u0005\r\"!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeA!\u0011QFA\u0019\u001b\t\tyC\u0003\u0002\u001cK&\u0019\u0011(a\f\u0015\u0005\u0005m\u0011!B1qa2LH#\u0002&\u0002:\u0005m\u0002\"B\u001e\u0017\u0001\u0004i\u0004\"\u0002$\u0017\u0001\u0004i\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003\u0003\ni\u0005E\u0003(\u0003\u0007\n9%C\u0002\u0002F!\u0012aa\u00149uS>t\u0007#B\u0014\u0002Juj\u0014bAA&Q\t1A+\u001e9mKJB\u0001\"a\u0014\u0018\u0003\u0003\u0005\rAS\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA+!\r\u0011\u0017qK\u0005\u0004\u00033\u001a'AB(cU\u0016\u001cG\u000f"
)
public final class FileNameSpec implements Product, Serializable {
   private final String prefix;
   private final String suffix;

   public static Option unapply(final FileNameSpec x$0) {
      return FileNameSpec$.MODULE$.unapply(x$0);
   }

   public static FileNameSpec apply(final String prefix, final String suffix) {
      return FileNameSpec$.MODULE$.apply(prefix, suffix);
   }

   public static Function1 tupled() {
      return FileNameSpec$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return FileNameSpec$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String prefix() {
      return this.prefix;
   }

   public String suffix() {
      return this.suffix;
   }

   public FileNameSpec copy(final String prefix, final String suffix) {
      return new FileNameSpec(prefix, suffix);
   }

   public String copy$default$1() {
      return this.prefix();
   }

   public String copy$default$2() {
      return this.suffix();
   }

   public String productPrefix() {
      return "FileNameSpec";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.prefix();
         }
         case 1 -> {
            return this.suffix();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof FileNameSpec;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "prefix";
         }
         case 1 -> {
            return "suffix";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label52: {
            if (x$1 instanceof FileNameSpec) {
               label45: {
                  FileNameSpec var4 = (FileNameSpec)x$1;
                  String var10000 = this.prefix();
                  String var5 = var4.prefix();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label45;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label45;
                  }

                  var10000 = this.suffix();
                  String var6 = var4.suffix();
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label52;
                     }
                  } else if (var10000.equals(var6)) {
                     break label52;
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

   public FileNameSpec(final String prefix, final String suffix) {
      this.prefix = prefix;
      this.suffix = suffix;
      Product.$init$(this);
   }
}

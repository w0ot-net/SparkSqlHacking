package org.apache.spark.ui;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005e!B\r\u001b\u0001j\u0011\u0003\u0002\u0003\u001e\u0001\u0005+\u0007I\u0011A\u001e\t\u0011}\u0002!\u0011#Q\u0001\nqB\u0001\u0002\u0011\u0001\u0003\u0016\u0004%\t!\u0011\u0005\t'\u0002\u0011\t\u0012)A\u0005\u0005\")A\u000b\u0001C\u0001+\"9!\fAA\u0001\n\u0003Y\u0006bB2\u0001#\u0003%\t\u0001\u001a\u0005\bc\u0002\t\n\u0011\"\u0001s\u0011\u001d1\b!!A\u0005B]D\u0001\"!\u0001\u0001\u0003\u0003%\ta\u000f\u0005\n\u0003\u0007\u0001\u0011\u0011!C\u0001\u0003\u000bA\u0011\"a\u0003\u0001\u0003\u0003%\t%!\u0004\t\u0013\u0005U\u0001!!A\u0005\u0002\u0005]\u0001\"CA\u0011\u0001\u0005\u0005I\u0011IA\u0012\u0011%\t9\u0003AA\u0001\n\u0003\nI\u0003C\u0005\u0002,\u0001\t\t\u0011\"\u0011\u0002.!I\u0011q\u0006\u0001\u0002\u0002\u0013\u0005\u0013\u0011G\u0004\u000b\u0003kQ\u0012\u0011!E\u00015\u0005]b!C\r\u001b\u0003\u0003E\tAGA\u001d\u0011\u0019!6\u0003\"\u0001\u0002F!I\u00111F\n\u0002\u0002\u0013\u0015\u0013Q\u0006\u0005\n\u0003\u000f\u001a\u0012\u0011!CA\u0003\u0013B\u0011\"!\u0017\u0014\u0003\u0003%\t)a\u0017\t\u0013\u0005]4#!A\u0005\n\u0005e$\u0001\u0003)bO\u0016$\u0015\r^1\u000b\u0005ma\u0012AA;j\u0015\tib$A\u0003ta\u0006\u00148N\u0003\u0002 A\u00051\u0011\r]1dQ\u0016T\u0011!I\u0001\u0004_J<WCA\u0012K'\u0011\u0001AEK\u0017\u0011\u0005\u0015BS\"\u0001\u0014\u000b\u0003\u001d\nQa]2bY\u0006L!!\u000b\u0014\u0003\r\u0005s\u0017PU3g!\t)3&\u0003\u0002-M\t9\u0001K]8ek\u000e$\bC\u0001\u00188\u001d\tySG\u0004\u00021i5\t\u0011G\u0003\u00023g\u00051AH]8piz\u001a\u0001!C\u0001(\u0013\t1d%A\u0004qC\u000e\\\u0017mZ3\n\u0005aJ$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001c'\u0003%!x\u000e^1m!\u0006<W-F\u0001=!\t)S(\u0003\u0002?M\t\u0019\u0011J\u001c;\u0002\u0015Q|G/\u00197QC\u001e,\u0007%\u0001\u0003eCR\fW#\u0001\"\u0011\u0007\r3\u0005*D\u0001E\u0015\t)e%\u0001\u0006d_2dWm\u0019;j_:L!a\u0012#\u0003\u0007M+\u0017\u000f\u0005\u0002J\u00152\u0001A!B&\u0001\u0005\u0004a%!\u0001+\u0012\u00055\u0003\u0006CA\u0013O\u0013\tyeEA\u0004O_RD\u0017N\\4\u0011\u0005\u0015\n\u0016B\u0001*'\u0005\r\te._\u0001\u0006I\u0006$\u0018\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007YC\u0016\fE\u0002X\u0001!k\u0011A\u0007\u0005\u0006u\u0015\u0001\r\u0001\u0010\u0005\u0006\u0001\u0016\u0001\rAQ\u0001\u0005G>\u0004\u00180\u0006\u0002]?R\u0019Q\fY1\u0011\u0007]\u0003a\f\u0005\u0002J?\u0012)1J\u0002b\u0001\u0019\"9!H\u0002I\u0001\u0002\u0004a\u0004b\u0002!\u0007!\u0003\u0005\rA\u0019\t\u0004\u0007\u001as\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0003KB,\u0012A\u001a\u0016\u0003y\u001d\\\u0013\u0001\u001b\t\u0003S:l\u0011A\u001b\u0006\u0003W2\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u000554\u0013AC1o]>$\u0018\r^5p]&\u0011qN\u001b\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,G!B&\b\u0005\u0004a\u0015AD2paf$C-\u001a4bk2$HEM\u000b\u0003gV,\u0012\u0001\u001e\u0016\u0003\u0005\u001e$Qa\u0013\u0005C\u00021\u000bQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u0001=\u0011\u0005etX\"\u0001>\u000b\u0005md\u0018\u0001\u00027b]\u001eT\u0011!`\u0001\u0005U\u00064\u0018-\u0003\u0002\u0000u\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000fF\u0002Q\u0003\u000fA\u0001\"!\u0003\f\u0003\u0003\u0005\r\u0001P\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005=\u0001\u0003B\"\u0002\u0012AK1!a\u0005E\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005e\u0011q\u0004\t\u0004K\u0005m\u0011bAA\u000fM\t9!i\\8mK\u0006t\u0007\u0002CA\u0005\u001b\u0005\u0005\t\u0019\u0001)\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004q\u0006\u0015\u0002\u0002CA\u0005\u001d\u0005\u0005\t\u0019\u0001\u001f\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012\u0001P\u0001\ti>\u001cFO]5oOR\t\u00010\u0001\u0004fcV\fGn\u001d\u000b\u0005\u00033\t\u0019\u0004\u0003\u0005\u0002\nE\t\t\u00111\u0001Q\u0003!\u0001\u0016mZ3ECR\f\u0007CA,\u0014'\u0011\u0019B%a\u000f\u0011\t\u0005u\u00121I\u0007\u0003\u0003\u007fQ1!!\u0011}\u0003\tIw.C\u00029\u0003\u007f!\"!a\u000e\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\t\u0005-\u0013\u0011\u000b\u000b\u0007\u0003\u001b\n\u0019&!\u0016\u0011\t]\u0003\u0011q\n\t\u0004\u0013\u0006EC!B&\u0017\u0005\u0004a\u0005\"\u0002\u001e\u0017\u0001\u0004a\u0004B\u0002!\u0017\u0001\u0004\t9\u0006\u0005\u0003D\r\u0006=\u0013aB;oCB\u0004H._\u000b\u0005\u0003;\ny\u0007\u0006\u0003\u0002`\u0005E\u0004#B\u0013\u0002b\u0005\u0015\u0014bAA2M\t1q\n\u001d;j_:\u0004b!JA4y\u0005-\u0014bAA5M\t1A+\u001e9mKJ\u0002Ba\u0011$\u0002nA\u0019\u0011*a\u001c\u0005\u000b-;\"\u0019\u0001'\t\u0013\u0005Mt#!AA\u0002\u0005U\u0014a\u0001=%aA!q\u000bAA7\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tY\bE\u0002z\u0003{J1!a {\u0005\u0019y%M[3di\u0002"
)
public class PageData implements Product, Serializable {
   private final int totalPage;
   private final Seq data;

   public static Option unapply(final PageData x$0) {
      return PageData$.MODULE$.unapply(x$0);
   }

   public static PageData apply(final int totalPage, final Seq data) {
      return PageData$.MODULE$.apply(totalPage, data);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int totalPage() {
      return this.totalPage;
   }

   public Seq data() {
      return this.data;
   }

   public PageData copy(final int totalPage, final Seq data) {
      return new PageData(totalPage, data);
   }

   public int copy$default$1() {
      return this.totalPage();
   }

   public Seq copy$default$2() {
      return this.data();
   }

   public String productPrefix() {
      return "PageData";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.totalPage());
         }
         case 1 -> {
            return this.data();
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
      return x$1 instanceof PageData;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "totalPage";
         }
         case 1 -> {
            return "data";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.totalPage());
      var1 = Statics.mix(var1, Statics.anyHash(this.data()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof PageData) {
               PageData var4 = (PageData)x$1;
               if (this.totalPage() == var4.totalPage()) {
                  label44: {
                     Seq var10000 = this.data();
                     Seq var5 = var4.data();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label44;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label44;
                     }

                     if (var4.canEqual(this)) {
                        break label51;
                     }
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

   public PageData(final int totalPage, final Seq data) {
      this.totalPage = totalPage;
      this.data = data;
      Product.$init$(this);
   }
}

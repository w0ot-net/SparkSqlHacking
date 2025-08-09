package org.apache.spark.mllib.feature;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ug\u0001B\u0014)\tNB\u0001\"\u0013\u0001\u0003\u0012\u0004%\tA\u0013\u0005\t'\u0002\u0011\t\u0019!C\u0001)\"A!\f\u0001B\tB\u0003&1\n\u0003\u0005\\\u0001\tE\r\u0011\"\u0001]\u0011!\u0001\u0007A!a\u0001\n\u0003\t\u0007\u0002C2\u0001\u0005#\u0005\u000b\u0015B/\t\u0011\u0011\u0004!\u00113A\u0005\u0002\u0015D\u0001\u0002\u001c\u0001\u0003\u0002\u0004%\t!\u001c\u0005\t_\u0002\u0011\t\u0012)Q\u0005M\"A\u0001\u000f\u0001BI\u0002\u0013\u0005Q\r\u0003\u0005r\u0001\t\u0005\r\u0011\"\u0001s\u0011!!\bA!E!B\u00131\u0007\u0002C;\u0001\u0005#\u0007I\u0011\u0001<\t\u0011]\u0004!\u00111A\u0005\u0002aD\u0001B\u001f\u0001\u0003\u0012\u0003\u0006K!\u001b\u0005\u0006w\u0002!\t\u0001 \u0005\n\u0003\u0013\u0001\u0011\u0011!C\u0001\u0003\u0017A\u0011\"a\u0006\u0001#\u0003%\t!!\u0007\t\u0013\u0005=\u0002!%A\u0005\u0002\u0005E\u0002\"CA\u001b\u0001E\u0005I\u0011AA\u001c\u0011%\tY\u0004AI\u0001\n\u0003\t9\u0004C\u0005\u0002>\u0001\t\n\u0011\"\u0001\u0002@!I\u00111\t\u0001\u0002\u0002\u0013\u0005\u0013Q\t\u0005\t\u0003+\u0002\u0011\u0011!C\u0001m\"I\u0011q\u000b\u0001\u0002\u0002\u0013\u0005\u0011\u0011\f\u0005\n\u0003G\u0002\u0011\u0011!C!\u0003KB\u0011\"a\u001d\u0001\u0003\u0003%\t!!\u001e\t\u0013\u0005}\u0004!!A\u0005B\u0005\u0005\u0005\"CAC\u0001\u0005\u0005I\u0011IAD\u0011%\tI\tAA\u0001\n\u0003\nY\tC\u0005\u0002\u000e\u0002\t\t\u0011\"\u0011\u0002\u0010\u001eI\u00111\u0013\u0015\u0002\u0002#%\u0011Q\u0013\u0004\tO!\n\t\u0011#\u0003\u0002\u0018\"110\tC\u0001\u0003_C\u0011\"!#\"\u0003\u0003%)%a#\t\u0013\u0005E\u0016%!A\u0005\u0002\u0006M\u0006\"CA`C\u0005\u0005I\u0011QAa\u0011%\t\u0019.IA\u0001\n\u0013\t)NA\u0005W_\u000e\f'mV8sI*\u0011\u0011FK\u0001\bM\u0016\fG/\u001e:f\u0015\tYC&A\u0003nY2L'M\u0003\u0002.]\u0005)1\u000f]1sW*\u0011q\u0006M\u0001\u0007CB\f7\r[3\u000b\u0003E\n1a\u001c:h\u0007\u0001\u0019B\u0001\u0001\u001b;{A\u0011Q\u0007O\u0007\u0002m)\tq'A\u0003tG\u0006d\u0017-\u0003\u0002:m\t1\u0011I\\=SK\u001a\u0004\"!N\u001e\n\u0005q2$a\u0002)s_\u0012,8\r\u001e\t\u0003}\u0019s!a\u0010#\u000f\u0005\u0001\u001bU\"A!\u000b\u0005\t\u0013\u0014A\u0002\u001fs_>$h(C\u00018\u0013\t)e'A\u0004qC\u000e\\\u0017mZ3\n\u0005\u001dC%\u0001D*fe&\fG.\u001b>bE2,'BA#7\u0003\u00119xN\u001d3\u0016\u0003-\u0003\"\u0001\u0014)\u000f\u00055s\u0005C\u0001!7\u0013\tye'\u0001\u0004Qe\u0016$WMZ\u0005\u0003#J\u0013aa\u0015;sS:<'BA(7\u0003!9xN\u001d3`I\u0015\fHCA+Y!\t)d+\u0003\u0002Xm\t!QK\\5u\u0011\u001dI&!!AA\u0002-\u000b1\u0001\u001f\u00132\u0003\u00159xN\u001d3!\u0003\t\u0019g.F\u0001^!\t)d,\u0003\u0002`m\t!Aj\u001c8h\u0003\u0019\u0019gn\u0018\u0013fcR\u0011QK\u0019\u0005\b3\u0016\t\t\u00111\u0001^\u0003\r\u0019g\u000eI\u0001\u0006a>Lg\u000e^\u000b\u0002MB\u0019QgZ5\n\u0005!4$!B!se\u0006L\bCA\u001bk\u0013\tYgGA\u0002J]R\f\u0011\u0002]8j]R|F%Z9\u0015\u0005Us\u0007bB-\t\u0003\u0003\u0005\rAZ\u0001\u0007a>Lg\u000e\u001e\u0011\u0002\t\r|G-Z\u0001\tG>$Wm\u0018\u0013fcR\u0011Qk\u001d\u0005\b3.\t\t\u00111\u0001g\u0003\u0015\u0019w\u000eZ3!\u0003\u001d\u0019w\u000eZ3MK:,\u0012![\u0001\fG>$W\rT3o?\u0012*\u0017\u000f\u0006\u0002Vs\"9\u0011LDA\u0001\u0002\u0004I\u0017\u0001C2pI\u0016dUM\u001c\u0011\u0002\rqJg.\u001b;?))ix0!\u0001\u0002\u0004\u0005\u0015\u0011q\u0001\t\u0003}\u0002i\u0011\u0001\u000b\u0005\u0006\u0013B\u0001\ra\u0013\u0005\u00067B\u0001\r!\u0018\u0005\u0006IB\u0001\rA\u001a\u0005\u0006aB\u0001\rA\u001a\u0005\u0006kB\u0001\r![\u0001\u0005G>\u0004\u0018\u0010F\u0006~\u0003\u001b\ty!!\u0005\u0002\u0014\u0005U\u0001bB%\u0012!\u0003\u0005\ra\u0013\u0005\b7F\u0001\n\u00111\u0001^\u0011\u001d!\u0017\u0003%AA\u0002\u0019Dq\u0001]\t\u0011\u0002\u0003\u0007a\rC\u0004v#A\u0005\t\u0019A5\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u00111\u0004\u0016\u0004\u0017\u0006u1FAA\u0010!\u0011\t\t#a\u000b\u000e\u0005\u0005\r\"\u0002BA\u0013\u0003O\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005%b'\u0001\u0006b]:|G/\u0019;j_:LA!!\f\u0002$\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u00111\u0007\u0016\u0004;\u0006u\u0011AD2paf$C-\u001a4bk2$HeM\u000b\u0003\u0003sQ3AZA\u000f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIQ\nabY8qs\u0012\"WMZ1vYR$S'\u0006\u0002\u0002B)\u001a\u0011.!\b\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\t9\u0005\u0005\u0003\u0002J\u0005MSBAA&\u0015\u0011\ti%a\u0014\u0002\t1\fgn\u001a\u0006\u0003\u0003#\nAA[1wC&\u0019\u0011+a\u0013\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u00111LA1!\r)\u0014QL\u0005\u0004\u0003?2$aA!os\"9\u0011,GA\u0001\u0002\u0004I\u0017a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005\u001d\u0004CBA5\u0003_\nY&\u0004\u0002\u0002l)\u0019\u0011Q\u000e\u001c\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002r\u0005-$\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u001e\u0002~A\u0019Q'!\u001f\n\u0007\u0005mdGA\u0004C_>dW-\u00198\t\u0011e[\u0012\u0011!a\u0001\u00037\n!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u0011qIAB\u0011\u001dIF$!AA\u0002%\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002S\u0006AAo\\*ue&tw\r\u0006\u0002\u0002H\u00051Q-];bYN$B!a\u001e\u0002\u0012\"A\u0011lHA\u0001\u0002\u0004\tY&A\u0005W_\u000e\f'mV8sIB\u0011a0I\n\u0006C\u0005e\u0015Q\u0015\t\u000b\u00037\u000b\tkS/gM&lXBAAO\u0015\r\tyJN\u0001\beVtG/[7f\u0013\u0011\t\u0019+!(\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>tW\u0007\u0005\u0003\u0002(\u00065VBAAU\u0015\u0011\tY+a\u0014\u0002\u0005%|\u0017bA$\u0002*R\u0011\u0011QS\u0001\u0006CB\u0004H.\u001f\u000b\f{\u0006U\u0016qWA]\u0003w\u000bi\fC\u0003JI\u0001\u00071\nC\u0003\\I\u0001\u0007Q\fC\u0003eI\u0001\u0007a\rC\u0003qI\u0001\u0007a\rC\u0003vI\u0001\u0007\u0011.A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005\r\u0017q\u001a\t\u0006k\u0005\u0015\u0017\u0011Z\u0005\u0004\u0003\u000f4$AB(qi&|g\u000e\u0005\u00056\u0003\u0017\\UL\u001a4j\u0013\r\tiM\u000e\u0002\u0007)V\u0004H.Z\u001b\t\u0011\u0005EW%!AA\u0002u\f1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t9\u000e\u0005\u0003\u0002J\u0005e\u0017\u0002BAn\u0003\u0017\u0012aa\u00142kK\u000e$\b"
)
public class VocabWord implements Product, Serializable {
   private String word;
   private long cn;
   private int[] point;
   private int[] code;
   private int codeLen;

   public static Option unapply(final VocabWord x$0) {
      return VocabWord$.MODULE$.unapply(x$0);
   }

   public static VocabWord apply(final String word, final long cn, final int[] point, final int[] code, final int codeLen) {
      return VocabWord$.MODULE$.apply(word, cn, point, code, codeLen);
   }

   public static Function1 tupled() {
      return VocabWord$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return VocabWord$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String word() {
      return this.word;
   }

   public void word_$eq(final String x$1) {
      this.word = x$1;
   }

   public long cn() {
      return this.cn;
   }

   public void cn_$eq(final long x$1) {
      this.cn = x$1;
   }

   public int[] point() {
      return this.point;
   }

   public void point_$eq(final int[] x$1) {
      this.point = x$1;
   }

   public int[] code() {
      return this.code;
   }

   public void code_$eq(final int[] x$1) {
      this.code = x$1;
   }

   public int codeLen() {
      return this.codeLen;
   }

   public void codeLen_$eq(final int x$1) {
      this.codeLen = x$1;
   }

   public VocabWord copy(final String word, final long cn, final int[] point, final int[] code, final int codeLen) {
      return new VocabWord(word, cn, point, code, codeLen);
   }

   public String copy$default$1() {
      return this.word();
   }

   public long copy$default$2() {
      return this.cn();
   }

   public int[] copy$default$3() {
      return this.point();
   }

   public int[] copy$default$4() {
      return this.code();
   }

   public int copy$default$5() {
      return this.codeLen();
   }

   public String productPrefix() {
      return "VocabWord";
   }

   public int productArity() {
      return 5;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.word();
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.cn());
         }
         case 2 -> {
            return this.point();
         }
         case 3 -> {
            return this.code();
         }
         case 4 -> {
            return BoxesRunTime.boxToInteger(this.codeLen());
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
      return x$1 instanceof VocabWord;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "word";
         }
         case 1 -> {
            return "cn";
         }
         case 2 -> {
            return "point";
         }
         case 3 -> {
            return "code";
         }
         case 4 -> {
            return "codeLen";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.word()));
      var1 = Statics.mix(var1, Statics.longHash(this.cn()));
      var1 = Statics.mix(var1, Statics.anyHash(this.point()));
      var1 = Statics.mix(var1, Statics.anyHash(this.code()));
      var1 = Statics.mix(var1, this.codeLen());
      return Statics.finalizeHash(var1, 5);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label59: {
            if (x$1 instanceof VocabWord) {
               VocabWord var4 = (VocabWord)x$1;
               if (this.cn() == var4.cn() && this.codeLen() == var4.codeLen()) {
                  label52: {
                     String var10000 = this.word();
                     String var5 = var4.word();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label52;
                     }

                     if (this.point() == var4.point() && this.code() == var4.code() && var4.canEqual(this)) {
                        break label59;
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

   public VocabWord(final String word, final long cn, final int[] point, final int[] code, final int codeLen) {
      this.word = word;
      this.cn = cn;
      this.point = point;
      this.code = code;
      this.codeLen = codeLen;
      super();
      Product.$init$(this);
   }
}

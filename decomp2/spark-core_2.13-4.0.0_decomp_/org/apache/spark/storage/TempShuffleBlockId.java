package org.apache.spark.storage;

import java.io.Serializable;
import java.util.UUID;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ec!\u0002\f\u0018\u0001fy\u0002\u0002C\u001c\u0001\u0005+\u0007I\u0011\u0001\u001d\t\u0011\u0005\u0003!\u0011#Q\u0001\neBQA\u0011\u0001\u0005\u0002\rCQA\u0012\u0001\u0005B\u001dCq\u0001\u0015\u0001\u0002\u0002\u0013\u0005\u0011\u000bC\u0004T\u0001E\u0005I\u0011\u0001+\t\u000f}\u0003\u0011\u0011!C!A\"9a\rAA\u0001\n\u00039\u0007bB6\u0001\u0003\u0003%\t\u0001\u001c\u0005\be\u0002\t\t\u0011\"\u0011t\u0011\u001dQ\b!!A\u0005\u0002mD\u0011\"!\u0001\u0001\u0003\u0003%\t%a\u0001\t\u0013\u0005\u001d\u0001!!A\u0005B\u0005%\u0001\"CA\u0006\u0001\u0005\u0005I\u0011IA\u0007\u000f)\t\tbFA\u0001\u0012\u0003I\u00121\u0003\u0004\n-]\t\t\u0011#\u0001\u001a\u0003+AaA\u0011\t\u0005\u0002\u00055\u0002\"CA\u0018!\u0005\u0005IQIA\u0019\u0011%\t\u0019\u0004EA\u0001\n\u0003\u000b)\u0004C\u0005\u0002:A\t\t\u0011\"!\u0002<!I\u0011q\t\t\u0002\u0002\u0013%\u0011\u0011\n\u0002\u0013)\u0016l\u0007o\u00155vM\u001adWM\u00117pG.LEM\u0003\u0002\u00193\u000591\u000f^8sC\u001e,'B\u0001\u000e\u001c\u0003\u0015\u0019\b/\u0019:l\u0015\taR$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002=\u0005\u0019qN]4\u0014\t\u0001\u0001CE\u000b\t\u0003C\tj\u0011aF\u0005\u0003G]\u0011qA\u00117pG.LE\r\u0005\u0002&Q5\taEC\u0001(\u0003\u0015\u00198-\u00197b\u0013\tIcEA\u0004Qe>$Wo\u0019;\u0011\u0005-\"dB\u0001\u00173\u001d\ti\u0013'D\u0001/\u0015\ty\u0003'\u0001\u0004=e>|GOP\u0002\u0001\u0013\u00059\u0013BA\u001a'\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u000e\u001c\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005M2\u0013AA5e+\u0005I\u0004C\u0001\u001e@\u001b\u0005Y$B\u0001\u001f>\u0003\u0011)H/\u001b7\u000b\u0003y\nAA[1wC&\u0011\u0001i\u000f\u0002\u0005+VKE)A\u0002jI\u0002\na\u0001P5oSRtDC\u0001#F!\t\t\u0003\u0001C\u00038\u0007\u0001\u0007\u0011(\u0001\u0003oC6,W#\u0001%\u0011\u0005%keB\u0001&L!\tic%\u0003\u0002MM\u00051\u0001K]3eK\u001aL!AT(\u0003\rM#(/\u001b8h\u0015\tae%\u0001\u0003d_BLHC\u0001#S\u0011\u001d9T\u0001%AA\u0002e\nabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001VU\tIdkK\u0001X!\tAV,D\u0001Z\u0015\tQ6,A\u0005v]\u000eDWmY6fI*\u0011ALJ\u0001\u000bC:tw\u000e^1uS>t\u0017B\u00010Z\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003\u0005\u0004\"AY3\u000e\u0003\rT!\u0001Z\u001f\u0002\t1\fgnZ\u0005\u0003\u001d\u000e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012\u0001\u001b\t\u0003K%L!A\u001b\u0014\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u00055\u0004\bCA\u0013o\u0013\tygEA\u0002B]fDq!]\u0005\u0002\u0002\u0003\u0007\u0001.A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002iB\u0019Q\u000f_7\u000e\u0003YT!a\u001e\u0014\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002zm\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\tax\u0010\u0005\u0002&{&\u0011aP\n\u0002\b\u0005>|G.Z1o\u0011\u001d\t8\"!AA\u00025\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019\u0011-!\u0002\t\u000fEd\u0011\u0011!a\u0001Q\u0006A\u0001.Y:i\u0007>$W\rF\u0001i\u0003\u0019)\u0017/^1mgR\u0019A0a\u0004\t\u000fEt\u0011\u0011!a\u0001[\u0006\u0011B+Z7q'\",hM\u001a7f\u00052|7m[%e!\t\t\u0003cE\u0003\u0011\u0003/\t\u0019\u0003\u0005\u0004\u0002\u001a\u0005}\u0011\bR\u0007\u0003\u00037Q1!!\b'\u0003\u001d\u0011XO\u001c;j[\u0016LA!!\t\u0002\u001c\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0011\t\u0005\u0015\u00121F\u0007\u0003\u0003OQ1!!\u000b>\u0003\tIw.C\u00026\u0003O!\"!a\u0005\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012!Y\u0001\u0006CB\u0004H.\u001f\u000b\u0004\t\u0006]\u0002\"B\u001c\u0014\u0001\u0004I\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003{\t\u0019\u0005\u0005\u0003&\u0003\u007fI\u0014bAA!M\t1q\n\u001d;j_:D\u0001\"!\u0012\u0015\u0003\u0003\u0005\r\u0001R\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA&!\r\u0011\u0017QJ\u0005\u0004\u0003\u001f\u001a'AB(cU\u0016\u001cG\u000f"
)
public class TempShuffleBlockId extends BlockId implements Product, Serializable {
   private final UUID id;

   public static Option unapply(final TempShuffleBlockId x$0) {
      return TempShuffleBlockId$.MODULE$.unapply(x$0);
   }

   public static TempShuffleBlockId apply(final UUID id) {
      return TempShuffleBlockId$.MODULE$.apply(id);
   }

   public static Function1 andThen(final Function1 g) {
      return TempShuffleBlockId$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return TempShuffleBlockId$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public UUID id() {
      return this.id;
   }

   public String name() {
      return "temp_shuffle_" + this.id();
   }

   public TempShuffleBlockId copy(final UUID id) {
      return new TempShuffleBlockId(id);
   }

   public UUID copy$default$1() {
      return this.id();
   }

   public String productPrefix() {
      return "TempShuffleBlockId";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.id();
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
      return x$1 instanceof TempShuffleBlockId;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "id";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof TempShuffleBlockId) {
               label40: {
                  TempShuffleBlockId var4 = (TempShuffleBlockId)x$1;
                  UUID var10000 = this.id();
                  UUID var5 = var4.id();
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

   public TempShuffleBlockId(final UUID id) {
      this.id = id;
      Product.$init$(this);
   }
}

package org.apache.spark.scheduler;

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
   bytes = "\u0006\u0005\u00055d\u0001B\u0010!\t&B\u0001b\u0010\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t\t\u0002\u0011\t\u0012)A\u0005\u0003\"AQ\t\u0001BK\u0002\u0013\u0005\u0001\t\u0003\u0005G\u0001\tE\t\u0015!\u0003B\u0011!9\u0005A!f\u0001\n\u0003\u0001\u0005\u0002\u0003%\u0001\u0005#\u0005\u000b\u0011B!\t\u0011%\u0003!Q3A\u0005\u0002\u0001C\u0001B\u0013\u0001\u0003\u0012\u0003\u0006I!\u0011\u0005\u0006\u0017\u0002!\t\u0001\u0014\u0005\b'\u0002\t\t\u0011\"\u0001U\u0011\u001dI\u0006!%A\u0005\u0002iCq!\u001a\u0001\u0012\u0002\u0013\u0005!\fC\u0004g\u0001E\u0005I\u0011\u0001.\t\u000f\u001d\u0004\u0011\u0013!C\u00015\"9\u0001\u000eAA\u0001\n\u0003J\u0007b\u0002:\u0001\u0003\u0003%\t\u0001\u0011\u0005\bg\u0002\t\t\u0011\"\u0001u\u0011\u001dQ\b!!A\u0005BmD\u0011\"!\u0002\u0001\u0003\u0003%\t!a\u0002\t\u0013\u0005E\u0001!!A\u0005B\u0005M\u0001\"CA\f\u0001\u0005\u0005I\u0011IA\r\u0011%\tY\u0002AA\u0001\n\u0003\ni\u0002C\u0005\u0002 \u0001\t\t\u0011\"\u0011\u0002\"\u001dI\u0011Q\u0005\u0011\u0002\u0002#%\u0011q\u0005\u0004\t?\u0001\n\t\u0011#\u0003\u0002*!11*\u0007C\u0001\u0003\u0003B\u0011\"a\u0007\u001a\u0003\u0003%)%!\b\t\u0013\u0005\r\u0013$!A\u0005\u0002\u0006\u0015\u0003\"CA(3\u0005\u0005I\u0011QA)\u0011%\t\u0019'GA\u0001\n\u0013\t)GA\u000eBg.\u0004VM]7jgNLwN\u001c+p\u0007>lW.\u001b;PkR\u0004X\u000f\u001e\u0006\u0003C\t\n\u0011b]2iK\u0012,H.\u001a:\u000b\u0005\r\"\u0013!B:qCJ\\'BA\u0013'\u0003\u0019\t\u0007/Y2iK*\tq%A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001UA\u001a\u0004CA\u0016/\u001b\u0005a#\"A\u0017\u0002\u000bM\u001c\u0017\r\\1\n\u0005=b#AB!osJ+g\r\u0005\u0002,c%\u0011!\u0007\f\u0002\b!J|G-^2u!\t!DH\u0004\u00026u9\u0011a'O\u0007\u0002o)\u0011\u0001\bK\u0001\u0007yI|w\u000e\u001e \n\u00035J!a\u000f\u0017\u0002\u000fA\f7m[1hK&\u0011QH\u0010\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003w1\nQa\u001d;bO\u0016,\u0012!\u0011\t\u0003W\tK!a\u0011\u0017\u0003\u0007%sG/\u0001\u0004ti\u0006<W\rI\u0001\rgR\fw-Z!ui\u0016l\u0007\u000f^\u0001\u000egR\fw-Z!ui\u0016l\u0007\u000f\u001e\u0011\u0002\u0013A\f'\u000f^5uS>t\u0017A\u00039beRLG/[8oA\u0005i\u0011\r\u001e;f[B$h*^7cKJ\fa\"\u0019;uK6\u0004HOT;nE\u0016\u0014\b%\u0001\u0004=S:LGO\u0010\u000b\u0006\u001b>\u0003\u0016K\u0015\t\u0003\u001d\u0002i\u0011\u0001\t\u0005\u0006\u007f%\u0001\r!\u0011\u0005\u0006\u000b&\u0001\r!\u0011\u0005\u0006\u000f&\u0001\r!\u0011\u0005\u0006\u0013&\u0001\r!Q\u0001\u0005G>\u0004\u0018\u0010F\u0003N+Z;\u0006\fC\u0004@\u0015A\u0005\t\u0019A!\t\u000f\u0015S\u0001\u0013!a\u0001\u0003\"9qI\u0003I\u0001\u0002\u0004\t\u0005bB%\u000b!\u0003\u0005\r!Q\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005Y&FA!]W\u0005i\u0006C\u00010d\u001b\u0005y&B\u00011b\u0003%)hn\u00195fG.,GM\u0003\u0002cY\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\u0011|&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012\u0014AD2paf$C-\u001a4bk2$HeM\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t!\u000e\u0005\u0002la6\tAN\u0003\u0002n]\u0006!A.\u00198h\u0015\u0005y\u0017\u0001\u00026bm\u0006L!!\u001d7\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"!\u001e=\u0011\u0005-2\u0018BA<-\u0005\r\te.\u001f\u0005\bsF\t\t\u00111\u0001B\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\tA\u0010\u0005\u0003~\u0003\u0003)X\"\u0001@\u000b\u0005}d\u0013AC2pY2,7\r^5p]&\u0019\u00111\u0001@\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u0013\ty\u0001E\u0002,\u0003\u0017I1!!\u0004-\u0005\u001d\u0011un\u001c7fC:Dq!_\n\u0002\u0002\u0003\u0007Q/\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,Gc\u00016\u0002\u0016!9\u0011\u0010FA\u0001\u0002\u0004\t\u0015\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003\u0005\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0002U\u00061Q-];bYN$B!!\u0003\u0002$!9\u0011pFA\u0001\u0002\u0004)\u0018aG!tWB+'/\\5tg&|g\u000eV8D_6l\u0017\u000e^(viB,H\u000f\u0005\u0002O3M)\u0011$a\u000b\u00028AI\u0011QFA\u001a\u0003\u0006\u000b\u0015)T\u0007\u0003\u0003_Q1!!\r-\u0003\u001d\u0011XO\u001c;j[\u0016LA!!\u000e\u00020\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001b\u0011\t\u0005e\u0012qH\u0007\u0003\u0003wQ1!!\u0010o\u0003\tIw.C\u0002>\u0003w!\"!a\n\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u00135\u000b9%!\u0013\u0002L\u00055\u0003\"B \u001d\u0001\u0004\t\u0005\"B#\u001d\u0001\u0004\t\u0005\"B$\u001d\u0001\u0004\t\u0005\"B%\u001d\u0001\u0004\t\u0015aB;oCB\u0004H.\u001f\u000b\u0005\u0003'\ny\u0006E\u0003,\u0003+\nI&C\u0002\u0002X1\u0012aa\u00149uS>t\u0007cB\u0016\u0002\\\u0005\u000b\u0015)Q\u0005\u0004\u0003;b#A\u0002+va2,G\u0007\u0003\u0005\u0002bu\t\t\u00111\u0001N\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003O\u00022a[A5\u0013\r\tY\u0007\u001c\u0002\u0007\u001f\nTWm\u0019;"
)
public class AskPermissionToCommitOutput implements Product, Serializable {
   private final int stage;
   private final int stageAttempt;
   private final int partition;
   private final int attemptNumber;

   public static Option unapply(final AskPermissionToCommitOutput x$0) {
      return AskPermissionToCommitOutput$.MODULE$.unapply(x$0);
   }

   public static AskPermissionToCommitOutput apply(final int stage, final int stageAttempt, final int partition, final int attemptNumber) {
      return AskPermissionToCommitOutput$.MODULE$.apply(stage, stageAttempt, partition, attemptNumber);
   }

   public static Function1 tupled() {
      return AskPermissionToCommitOutput$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return AskPermissionToCommitOutput$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int stage() {
      return this.stage;
   }

   public int stageAttempt() {
      return this.stageAttempt;
   }

   public int partition() {
      return this.partition;
   }

   public int attemptNumber() {
      return this.attemptNumber;
   }

   public AskPermissionToCommitOutput copy(final int stage, final int stageAttempt, final int partition, final int attemptNumber) {
      return new AskPermissionToCommitOutput(stage, stageAttempt, partition, attemptNumber);
   }

   public int copy$default$1() {
      return this.stage();
   }

   public int copy$default$2() {
      return this.stageAttempt();
   }

   public int copy$default$3() {
      return this.partition();
   }

   public int copy$default$4() {
      return this.attemptNumber();
   }

   public String productPrefix() {
      return "AskPermissionToCommitOutput";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.stage());
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.stageAttempt());
         }
         case 2 -> {
            return BoxesRunTime.boxToInteger(this.partition());
         }
         case 3 -> {
            return BoxesRunTime.boxToInteger(this.attemptNumber());
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
      return x$1 instanceof AskPermissionToCommitOutput;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "stage";
         }
         case 1 -> {
            return "stageAttempt";
         }
         case 2 -> {
            return "partition";
         }
         case 3 -> {
            return "attemptNumber";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.stage());
      var1 = Statics.mix(var1, this.stageAttempt());
      var1 = Statics.mix(var1, this.partition());
      var1 = Statics.mix(var1, this.attemptNumber());
      return Statics.finalizeHash(var1, 4);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label42: {
            if (x$1 instanceof AskPermissionToCommitOutput) {
               AskPermissionToCommitOutput var4 = (AskPermissionToCommitOutput)x$1;
               if (this.stage() == var4.stage() && this.stageAttempt() == var4.stageAttempt() && this.partition() == var4.partition() && this.attemptNumber() == var4.attemptNumber() && var4.canEqual(this)) {
                  break label42;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public AskPermissionToCommitOutput(final int stage, final int stageAttempt, final int partition, final int attemptNumber) {
      this.stage = stage;
      this.stageAttempt = stageAttempt;
      this.partition = partition;
      this.attemptNumber = attemptNumber;
      Product.$init$(this);
   }
}

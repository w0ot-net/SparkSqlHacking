package org.apache.spark;

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
   bytes = "\u0006\u0005\u0005Uc\u0001B\r\u001b\t\u0006B\u0001b\u000e\u0001\u0003\u0016\u0004%\t\u0001\u000f\u0005\ty\u0001\u0011\t\u0012)A\u0005s!AQ\b\u0001BK\u0002\u0013\u0005\u0001\b\u0003\u0005?\u0001\tE\t\u0015!\u0003:\u0011\u0015y\u0004\u0001\"\u0001A\u0011\u0015)\u0005\u0001\"\u0011G\u0011\u001dy\u0005!!A\u0005\u0002ACqa\u0015\u0001\u0012\u0002\u0013\u0005A\u000bC\u0004`\u0001E\u0005I\u0011\u0001+\t\u000f\u0001\u0004\u0011\u0011!C!C\"9\u0011\u000eAA\u0001\n\u0003A\u0004b\u00026\u0001\u0003\u0003%\ta\u001b\u0005\bc\u0002\t\t\u0011\"\u0011s\u0011\u001dI\b!!A\u0005\u0002iD\u0001b \u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0001\u0005\n\u0003\u000b\u0001\u0011\u0011!C!\u0003\u000fA\u0011\"!\u0003\u0001\u0003\u0003%\t%a\u0003\b\u0013\u0005=!$!A\t\n\u0005Ea\u0001C\r\u001b\u0003\u0003EI!a\u0005\t\r}\u001aB\u0011AA\u0016\u0011!)5#!A\u0005F\u00055\u0002\"CA\u0018'\u0005\u0005I\u0011QA\u0019\u0011%\t9dEA\u0001\n\u0003\u000bI\u0004C\u0005\u0002LM\t\t\u0011\"\u0003\u0002N\t\u00012i\u001c8uKb$()\u0019:sS\u0016\u0014\u0018\n\u001a\u0006\u00037q\tQa\u001d9be.T!!\b\u0010\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0012aA8sO\u000e\u00011\u0003\u0002\u0001#Q-\u0002\"a\t\u0014\u000e\u0003\u0011R\u0011!J\u0001\u0006g\u000e\fG.Y\u0005\u0003O\u0011\u0012a!\u00118z%\u00164\u0007CA\u0012*\u0013\tQCEA\u0004Qe>$Wo\u0019;\u0011\u00051\"dBA\u00173\u001d\tq\u0013'D\u00010\u0015\t\u0001\u0004%\u0001\u0004=e>|GOP\u0005\u0002K%\u00111\u0007J\u0001\ba\u0006\u001c7.Y4f\u0013\t)dG\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00024I\u000591\u000f^1hK&#W#A\u001d\u0011\u0005\rR\u0014BA\u001e%\u0005\rIe\u000e^\u0001\tgR\fw-Z%eA\u0005q1\u000f^1hK\u0006#H/Z7qi&#\u0017aD:uC\u001e,\u0017\t\u001e;f[B$\u0018\n\u001a\u0011\u0002\rqJg.\u001b;?)\r\t5\t\u0012\t\u0003\u0005\u0002i\u0011A\u0007\u0005\u0006o\u0015\u0001\r!\u000f\u0005\u0006{\u0015\u0001\r!O\u0001\ti>\u001cFO]5oOR\tq\t\u0005\u0002I\u0019:\u0011\u0011J\u0013\t\u0003]\u0011J!a\u0013\u0013\u0002\rA\u0013X\rZ3g\u0013\tieJ\u0001\u0004TiJLgn\u001a\u0006\u0003\u0017\u0012\nAaY8qsR\u0019\u0011)\u0015*\t\u000f]:\u0001\u0013!a\u0001s!9Qh\u0002I\u0001\u0002\u0004I\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u0002+*\u0012\u0011HV\u0016\u0002/B\u0011\u0001,X\u0007\u00023*\u0011!lW\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001\u0018\u0013\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002_3\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%e\u0005i\u0001O]8ek\u000e$\bK]3gSb,\u0012A\u0019\t\u0003G\"l\u0011\u0001\u001a\u0006\u0003K\u001a\fA\u0001\\1oO*\tq-\u0001\u0003kCZ\f\u0017BA'e\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"\u0001\\8\u0011\u0005\rj\u0017B\u00018%\u0005\r\te.\u001f\u0005\ba2\t\t\u00111\u0001:\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\t1\u000fE\u0002uo2l\u0011!\u001e\u0006\u0003m\u0012\n!bY8mY\u0016\u001cG/[8o\u0013\tAXO\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGCA>\u007f!\t\u0019C0\u0003\u0002~I\t9!i\\8mK\u0006t\u0007b\u00029\u000f\u0003\u0003\u0005\r\u0001\\\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002c\u0003\u0007Aq\u0001]\b\u0002\u0002\u0003\u0007\u0011(\u0001\u0005iCND7i\u001c3f)\u0005I\u0014AB3rk\u0006d7\u000fF\u0002|\u0003\u001bAq\u0001]\t\u0002\u0002\u0003\u0007A.\u0001\tD_:$X\r\u001f;CCJ\u0014\u0018.\u001a:JIB\u0011!iE\n\u0006'\u0005U\u0011\u0011\u0005\t\b\u0003/\ti\"O\u001dB\u001b\t\tIBC\u0002\u0002\u001c\u0011\nqA];oi&lW-\u0003\u0003\u0002 \u0005e!!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeA!\u00111EA\u0015\u001b\t\t)CC\u0002\u0002(\u0019\f!![8\n\u0007U\n)\u0003\u0006\u0002\u0002\u0012Q\t!-A\u0003baBd\u0017\u0010F\u0003B\u0003g\t)\u0004C\u00038-\u0001\u0007\u0011\bC\u0003>-\u0001\u0007\u0011(A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005m\u0012q\t\t\u0006G\u0005u\u0012\u0011I\u0005\u0004\u0003\u007f!#AB(qi&|g\u000eE\u0003$\u0003\u0007J\u0014(C\u0002\u0002F\u0011\u0012a\u0001V;qY\u0016\u0014\u0004\u0002CA%/\u0005\u0005\t\u0019A!\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002PA\u00191-!\u0015\n\u0007\u0005MCM\u0001\u0004PE*,7\r\u001e"
)
public class ContextBarrierId implements Product, Serializable {
   private final int stageId;
   private final int stageAttemptId;

   public static Option unapply(final ContextBarrierId x$0) {
      return ContextBarrierId$.MODULE$.unapply(x$0);
   }

   public static ContextBarrierId apply(final int stageId, final int stageAttemptId) {
      return ContextBarrierId$.MODULE$.apply(stageId, stageAttemptId);
   }

   public static Function1 tupled() {
      return ContextBarrierId$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ContextBarrierId$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int stageId() {
      return this.stageId;
   }

   public int stageAttemptId() {
      return this.stageAttemptId;
   }

   public String toString() {
      int var10000 = this.stageId();
      return "Stage " + var10000 + " (Attempt " + this.stageAttemptId() + ")";
   }

   public ContextBarrierId copy(final int stageId, final int stageAttemptId) {
      return new ContextBarrierId(stageId, stageAttemptId);
   }

   public int copy$default$1() {
      return this.stageId();
   }

   public int copy$default$2() {
      return this.stageAttemptId();
   }

   public String productPrefix() {
      return "ContextBarrierId";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.stageId());
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.stageAttemptId());
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
      return x$1 instanceof ContextBarrierId;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "stageId";
         }
         case 1 -> {
            return "stageAttemptId";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.stageId());
      var1 = Statics.mix(var1, this.stageAttemptId());
      return Statics.finalizeHash(var1, 2);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label38: {
            if (x$1 instanceof ContextBarrierId) {
               ContextBarrierId var4 = (ContextBarrierId)x$1;
               if (this.stageId() == var4.stageId() && this.stageAttemptId() == var4.stageAttemptId() && var4.canEqual(this)) {
                  break label38;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public ContextBarrierId(final int stageId, final int stageAttemptId) {
      this.stageId = stageId;
      this.stageAttemptId = stageAttemptId;
      Product.$init$(this);
   }
}

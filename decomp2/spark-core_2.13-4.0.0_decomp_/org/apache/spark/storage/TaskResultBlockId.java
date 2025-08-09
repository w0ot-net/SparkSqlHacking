package org.apache.spark.storage;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005]c\u0001\u0002\f\u0018\u0001\u0002B\u0001b\u000e\u0001\u0003\u0016\u0004%\t\u0001\u000f\u0005\ty\u0001\u0011\t\u0012)A\u0005s!)Q\b\u0001C\u0001}!)\u0011\t\u0001C!\u0005\"91\nAA\u0001\n\u0003a\u0005b\u0002(\u0001#\u0003%\ta\u0014\u0005\b5\u0002\t\t\u0011\"\u0011\\\u0011\u001d\u0019\u0007!!A\u0005\u0002\u0011Dq\u0001\u001b\u0001\u0002\u0002\u0013\u0005\u0011\u000eC\u0004p\u0001\u0005\u0005I\u0011\t9\t\u000f]\u0004\u0011\u0011!C\u0001q\"9Q\u0010AA\u0001\n\u0003r\b\"CA\u0001\u0001\u0005\u0005I\u0011IA\u0002\u0011%\t)\u0001AA\u0001\n\u0003\n9aB\u0005\u0002\u0018]\t\t\u0011#\u0001\u0002\u001a\u0019AacFA\u0001\u0012\u0003\tY\u0002\u0003\u0004>!\u0011\u0005\u00111\u0007\u0005\n\u0003k\u0001\u0012\u0011!C#\u0003oA\u0011\"!\u000f\u0011\u0003\u0003%\t)a\u000f\t\u0013\u0005}\u0002#!A\u0005\u0002\u0006\u0005\u0003\"CA'!\u0005\u0005I\u0011BA(\u0005E!\u0016m]6SKN,H\u000e\u001e\"m_\u000e\\\u0017\n\u001a\u0006\u00031e\tqa\u001d;pe\u0006<WM\u0003\u0002\u001b7\u0005)1\u000f]1sW*\u0011A$H\u0001\u0007CB\f7\r[3\u000b\u0003y\t1a\u001c:h\u0007\u0001\u0019B\u0001A\u0011&WA\u0011!eI\u0007\u0002/%\u0011Ae\u0006\u0002\b\u00052|7m[%e!\t1\u0013&D\u0001(\u0015\u0005A\u0013!B:dC2\f\u0017B\u0001\u0016(\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001\f\u001b\u000f\u00055\u0012dB\u0001\u00182\u001b\u0005y#B\u0001\u0019 \u0003\u0019a$o\\8u}%\t\u0001&\u0003\u00024O\u00059\u0001/Y2lC\u001e,\u0017BA\u001b7\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u0019t%\u0001\u0004uCN\\\u0017\nZ\u000b\u0002sA\u0011aEO\u0005\u0003w\u001d\u0012A\u0001T8oO\u00069A/Y:l\u0013\u0012\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002@\u0001B\u0011!\u0005\u0001\u0005\u0006o\r\u0001\r!O\u0001\u0005]\u0006lW-F\u0001D!\t!\u0005J\u0004\u0002F\rB\u0011afJ\u0005\u0003\u000f\u001e\na\u0001\u0015:fI\u00164\u0017BA%K\u0005\u0019\u0019FO]5oO*\u0011qiJ\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002@\u001b\"9q'\u0002I\u0001\u0002\u0004I\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u0002!*\u0012\u0011(U\u0016\u0002%B\u00111\u000bW\u0007\u0002)*\u0011QKV\u0001\nk:\u001c\u0007.Z2lK\u0012T!aV\u0014\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002Z)\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005a\u0006CA/c\u001b\u0005q&BA0a\u0003\u0011a\u0017M\\4\u000b\u0003\u0005\fAA[1wC&\u0011\u0011JX\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002KB\u0011aEZ\u0005\u0003O\u001e\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"A[7\u0011\u0005\u0019Z\u0017B\u00017(\u0005\r\te.\u001f\u0005\b]&\t\t\u00111\u0001f\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\t\u0011\u000fE\u0002sk*l\u0011a\u001d\u0006\u0003i\u001e\n!bY8mY\u0016\u001cG/[8o\u0013\t18O\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGCA=}!\t1#0\u0003\u0002|O\t9!i\\8mK\u0006t\u0007b\u00028\f\u0003\u0003\u0005\rA[\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0002]\u007f\"9a\u000eDA\u0001\u0002\u0004)\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003\u0015\fa!Z9vC2\u001cHcA=\u0002\n!9aNDA\u0001\u0002\u0004Q\u0007f\u0001\u0001\u0002\u000eA!\u0011qBA\n\u001b\t\t\tB\u0003\u0002X3%!\u0011QCA\t\u00051!UM^3m_B,'/\u00119j\u0003E!\u0016m]6SKN,H\u000e\u001e\"m_\u000e\\\u0017\n\u001a\t\u0003EA\u0019R\u0001EA\u000f\u0003S\u0001b!a\b\u0002&ezTBAA\u0011\u0015\r\t\u0019cJ\u0001\beVtG/[7f\u0013\u0011\t9#!\t\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002,\u0005ERBAA\u0017\u0015\r\ty\u0003Y\u0001\u0003S>L1!NA\u0017)\t\tI\"\u0001\u0005u_N#(/\u001b8h)\u0005a\u0016!B1qa2LHcA \u0002>!)qg\u0005a\u0001s\u00059QO\\1qa2LH\u0003BA\"\u0003\u0013\u0002BAJA#s%\u0019\u0011qI\u0014\u0003\r=\u0003H/[8o\u0011!\tY\u0005FA\u0001\u0002\u0004y\u0014a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011\u0011\u000b\t\u0004;\u0006M\u0013bAA+=\n1qJ\u00196fGR\u0004"
)
public class TaskResultBlockId extends BlockId implements Product, Serializable {
   private final long taskId;

   public static Option unapply(final TaskResultBlockId x$0) {
      return TaskResultBlockId$.MODULE$.unapply(x$0);
   }

   public static TaskResultBlockId apply(final long taskId) {
      return TaskResultBlockId$.MODULE$.apply(taskId);
   }

   public static Function1 andThen(final Function1 g) {
      return TaskResultBlockId$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return TaskResultBlockId$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long taskId() {
      return this.taskId;
   }

   public String name() {
      return "taskresult_" + this.taskId();
   }

   public TaskResultBlockId copy(final long taskId) {
      return new TaskResultBlockId(taskId);
   }

   public long copy$default$1() {
      return this.taskId();
   }

   public String productPrefix() {
      return "TaskResultBlockId";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.taskId());
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
      return x$1 instanceof TaskResultBlockId;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "taskId";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.taskId()));
      return Statics.finalizeHash(var1, 1);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label36: {
            if (x$1 instanceof TaskResultBlockId) {
               TaskResultBlockId var4 = (TaskResultBlockId)x$1;
               if (this.taskId() == var4.taskId() && var4.canEqual(this)) {
                  break label36;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public TaskResultBlockId(final long taskId) {
      this.taskId = taskId;
      Product.$init$(this);
   }
}

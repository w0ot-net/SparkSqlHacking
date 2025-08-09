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
   bytes = "\u0006\u0005\u0005]e\u0001B\u0010!\u0001&B\u0001\u0002\u0011\u0001\u0003\u0016\u0004%\t!\u0011\u0005\t\u000b\u0002\u0011\t\u0012)A\u0005\u0005\"Aa\t\u0001BK\u0002\u0013\u0005q\t\u0003\u0005L\u0001\tE\t\u0015!\u0003I\u0011!a\u0005A!f\u0001\n\u0003\t\u0005\u0002C'\u0001\u0005#\u0005\u000b\u0011\u0002\"\t\u00119\u0003!Q3A\u0005\u0002\u0005C\u0001b\u0014\u0001\u0003\u0012\u0003\u0006IA\u0011\u0005\u0006!\u0002!\t!\u0015\u0005\u0006/\u0002!\t\u0005\u0017\u0005\bC\u0002\t\t\u0011\"\u0001c\u0011\u001d9\u0007!%A\u0005\u0002!Dqa\u001d\u0001\u0012\u0002\u0013\u0005A\u000fC\u0004w\u0001E\u0005I\u0011\u00015\t\u000f]\u0004\u0011\u0013!C\u0001Q\"9\u0001\u0010AA\u0001\n\u0003J\b\u0002CA\u0002\u0001\u0005\u0005I\u0011A!\t\u0013\u0005\u0015\u0001!!A\u0005\u0002\u0005\u001d\u0001\"CA\n\u0001\u0005\u0005I\u0011IA\u000b\u0011%\t\u0019\u0003AA\u0001\n\u0003\t)\u0003C\u0005\u00020\u0001\t\t\u0011\"\u0011\u00022!I\u0011Q\u0007\u0001\u0002\u0002\u0013\u0005\u0013q\u0007\u0005\n\u0003s\u0001\u0011\u0011!C!\u0003w9\u0011\"a\u0013!\u0003\u0003E\t!!\u0014\u0007\u0011}\u0001\u0013\u0011!E\u0001\u0003\u001fBa\u0001U\r\u0005\u0002\u0005\u001d\u0004\"CA53\u0005\u0005IQIA6\u0011%\ti'GA\u0001\n\u0003\u000by\u0007C\u0005\u0002ze\t\t\u0011\"!\u0002|!I\u0011QR\r\u0002\u0002\u0013%\u0011q\u0012\u0002\u0014'\",hM\u001a7f\u00052|7m\u001b\"bi\u000eD\u0017\n\u001a\u0006\u0003C\t\nqa\u001d;pe\u0006<WM\u0003\u0002$I\u0005)1\u000f]1sW*\u0011QEJ\u0001\u0007CB\f7\r[3\u000b\u0003\u001d\n1a\u001c:h\u0007\u0001\u0019B\u0001\u0001\u0016/iA\u00111\u0006L\u0007\u0002A%\u0011Q\u0006\t\u0002\b\u00052|7m[%e!\ty#'D\u00011\u0015\u0005\t\u0014!B:dC2\f\u0017BA\u001a1\u0005\u001d\u0001&o\u001c3vGR\u0004\"!N\u001f\u000f\u0005YZdBA\u001c;\u001b\u0005A$BA\u001d)\u0003\u0019a$o\\8u}%\t\u0011'\u0003\u0002=a\u00059\u0001/Y2lC\u001e,\u0017B\u0001 @\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\ta\u0004'A\u0005tQV4g\r\\3JIV\t!\t\u0005\u00020\u0007&\u0011A\t\r\u0002\u0004\u0013:$\u0018AC:ik\u001a4G.Z%eA\u0005)Q.\u00199JIV\t\u0001\n\u0005\u00020\u0013&\u0011!\n\r\u0002\u0005\u0019>tw-\u0001\u0004nCBLE\rI\u0001\u000egR\f'\u000f\u001e*fIV\u001cW-\u00133\u0002\u001dM$\u0018M\u001d;SK\u0012,8-Z%eA\u0005YQM\u001c3SK\u0012,8-Z%e\u00031)g\u000e\u001a*fIV\u001cW-\u00133!\u0003\u0019a\u0014N\\5u}Q)!k\u0015+V-B\u00111\u0006\u0001\u0005\u0006\u0001&\u0001\rA\u0011\u0005\u0006\r&\u0001\r\u0001\u0013\u0005\u0006\u0019&\u0001\rA\u0011\u0005\u0006\u001d&\u0001\rAQ\u0001\u0005]\u0006lW-F\u0001Z!\tQfL\u0004\u0002\\9B\u0011q\u0007M\u0005\u0003;B\na\u0001\u0015:fI\u00164\u0017BA0a\u0005\u0019\u0019FO]5oO*\u0011Q\fM\u0001\u0005G>\u0004\u0018\u0010F\u0003SG\u0012,g\rC\u0004A\u0017A\u0005\t\u0019\u0001\"\t\u000f\u0019[\u0001\u0013!a\u0001\u0011\"9Aj\u0003I\u0001\u0002\u0004\u0011\u0005b\u0002(\f!\u0003\u0005\rAQ\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005I'F\u0001\"kW\u0005Y\u0007C\u00017r\u001b\u0005i'B\u00018p\u0003%)hn\u00195fG.,GM\u0003\u0002qa\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005Il'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#A;+\u0005!S\u0017AD2paf$C-\u001a4bk2$HeM\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t!\u0010E\u0002|\u0003\u0003i\u0011\u0001 \u0006\u0003{z\fA\u0001\\1oO*\tq0\u0001\u0003kCZ\f\u0017BA0}\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!!\u0003\u0002\u0010A\u0019q&a\u0003\n\u0007\u00055\u0001GA\u0002B]fD\u0001\"!\u0005\u0013\u0003\u0003\u0005\rAQ\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005]\u0001CBA\r\u0003?\tI!\u0004\u0002\u0002\u001c)\u0019\u0011Q\u0004\u0019\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\"\u0005m!\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\n\u0002.A\u0019q&!\u000b\n\u0007\u0005-\u0002GA\u0004C_>dW-\u00198\t\u0013\u0005EA#!AA\u0002\u0005%\u0011A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2A_A\u001a\u0011!\t\t\"FA\u0001\u0002\u0004\u0011\u0015\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003\t\u000ba!Z9vC2\u001cH\u0003BA\u0014\u0003{A\u0011\"!\u0005\u0018\u0003\u0003\u0005\r!!\u0003)\u0007\u0001\t\t\u0005\u0005\u0003\u0002D\u0005\u001dSBAA#\u0015\t\u0001(%\u0003\u0003\u0002J\u0005\u0015#\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0017aE*ik\u001a4G.\u001a\"m_\u000e\\')\u0019;dQ&#\u0007CA\u0016\u001a'\u0015I\u0012\u0011KA/!%\t\u0019&!\u0017C\u0011\n\u0013%+\u0004\u0002\u0002V)\u0019\u0011q\u000b\u0019\u0002\u000fI,h\u000e^5nK&!\u00111LA+\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g\u000e\u000e\t\u0005\u0003?\n)'\u0004\u0002\u0002b)\u0019\u00111\r@\u0002\u0005%|\u0017b\u0001 \u0002bQ\u0011\u0011QJ\u0001\ti>\u001cFO]5oOR\t!0A\u0003baBd\u0017\u0010F\u0005S\u0003c\n\u0019(!\u001e\u0002x!)\u0001\t\ba\u0001\u0005\")a\t\ba\u0001\u0011\")A\n\ba\u0001\u0005\")a\n\ba\u0001\u0005\u00069QO\\1qa2LH\u0003BA?\u0003\u0013\u0003RaLA@\u0003\u0007K1!!!1\u0005\u0019y\u0005\u000f^5p]B9q&!\"C\u0011\n\u0013\u0015bAADa\t1A+\u001e9mKRB\u0001\"a#\u001e\u0003\u0003\u0005\rAU\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAAI!\rY\u00181S\u0005\u0004\u0003+c(AB(cU\u0016\u001cG\u000f"
)
public class ShuffleBlockBatchId extends BlockId implements Product, Serializable {
   private final int shuffleId;
   private final long mapId;
   private final int startReduceId;
   private final int endReduceId;

   public static Option unapply(final ShuffleBlockBatchId x$0) {
      return ShuffleBlockBatchId$.MODULE$.unapply(x$0);
   }

   public static ShuffleBlockBatchId apply(final int shuffleId, final long mapId, final int startReduceId, final int endReduceId) {
      return ShuffleBlockBatchId$.MODULE$.apply(shuffleId, mapId, startReduceId, endReduceId);
   }

   public static Function1 tupled() {
      return ShuffleBlockBatchId$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ShuffleBlockBatchId$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int shuffleId() {
      return this.shuffleId;
   }

   public long mapId() {
      return this.mapId;
   }

   public int startReduceId() {
      return this.startReduceId;
   }

   public int endReduceId() {
      return this.endReduceId;
   }

   public String name() {
      int var10000 = this.shuffleId();
      return "shuffle_" + var10000 + "_" + this.mapId() + "_" + this.startReduceId() + "_" + this.endReduceId();
   }

   public ShuffleBlockBatchId copy(final int shuffleId, final long mapId, final int startReduceId, final int endReduceId) {
      return new ShuffleBlockBatchId(shuffleId, mapId, startReduceId, endReduceId);
   }

   public int copy$default$1() {
      return this.shuffleId();
   }

   public long copy$default$2() {
      return this.mapId();
   }

   public int copy$default$3() {
      return this.startReduceId();
   }

   public int copy$default$4() {
      return this.endReduceId();
   }

   public String productPrefix() {
      return "ShuffleBlockBatchId";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.shuffleId());
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.mapId());
         }
         case 2 -> {
            return BoxesRunTime.boxToInteger(this.startReduceId());
         }
         case 3 -> {
            return BoxesRunTime.boxToInteger(this.endReduceId());
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
      return x$1 instanceof ShuffleBlockBatchId;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "shuffleId";
         }
         case 1 -> {
            return "mapId";
         }
         case 2 -> {
            return "startReduceId";
         }
         case 3 -> {
            return "endReduceId";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.shuffleId());
      var1 = Statics.mix(var1, Statics.longHash(this.mapId()));
      var1 = Statics.mix(var1, this.startReduceId());
      var1 = Statics.mix(var1, this.endReduceId());
      return Statics.finalizeHash(var1, 4);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label42: {
            if (x$1 instanceof ShuffleBlockBatchId) {
               ShuffleBlockBatchId var4 = (ShuffleBlockBatchId)x$1;
               if (this.shuffleId() == var4.shuffleId() && this.mapId() == var4.mapId() && this.startReduceId() == var4.startReduceId() && this.endReduceId() == var4.endReduceId() && var4.canEqual(this)) {
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

   public ShuffleBlockBatchId(final int shuffleId, final long mapId, final int startReduceId, final int endReduceId) {
      this.shuffleId = shuffleId;
      this.mapId = mapId;
      this.startReduceId = startReduceId;
      this.endReduceId = endReduceId;
      Product.$init$(this);
   }
}

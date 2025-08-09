package org.apache.spark.sql.execution.streaming;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import org.apache.spark.sql.streaming.Trigger;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.concurrent.duration.Duration;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%e\u0001B\r\u001b\u0001\u001eB\u0001b\u0010\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t\t\u0002\u0011\t\u0012)A\u0005\u0003\")Q\t\u0001C\u0001\r\"9!\nAA\u0001\n\u0003Y\u0005bB'\u0001#\u0003%\tA\u0014\u0005\b3\u0002\t\t\u0011\"\u0011[\u0011\u001d\u0019\u0007!!A\u0005\u0002\u0011Dq\u0001\u001b\u0001\u0002\u0002\u0013\u0005\u0011\u000eC\u0004p\u0001\u0005\u0005I\u0011\t9\t\u000f]\u0004\u0011\u0011!C\u0001q\"9Q\u0010AA\u0001\n\u0003r\b\"CA\u0001\u0001\u0005\u0005I\u0011IA\u0002\u0011%\t)\u0001AA\u0001\n\u0003\n9\u0001C\u0005\u0002\n\u0001\t\t\u0011\"\u0011\u0002\f\u001d9\u0011q\u0002\u000e\t\u0002\u0005EaAB\r\u001b\u0011\u0003\t\u0019\u0002\u0003\u0004F!\u0011\u0005\u0011Q\u0005\u0005\b\u0003O\u0001B\u0011AA\u0015\u0011\u001d\t9\u0003\u0005C\u0001\u0003{Aq!!\u0015\u0011\t\u0003\t\u0019\u0006C\u0004\u0002RA!\t!a\u0016\t\u0013\u0005\u001d\u0002#!A\u0005\u0002\u00065\u0004\"CA9!\u0005\u0005I\u0011QA:\u0011%\ty\bEA\u0001\n\u0013\t\tIA\tD_:$\u0018N\\;pkN$&/[4hKJT!a\u0007\u000f\u0002\u0013M$(/Z1nS:<'BA\u000f\u001f\u0003%)\u00070Z2vi&|gN\u0003\u0002 A\u0005\u00191/\u001d7\u000b\u0005\u0005\u0012\u0013!B:qCJ\\'BA\u0012%\u0003\u0019\t\u0007/Y2iK*\tQ%A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001Q5\u001a\u0004CA\u0015,\u001b\u0005Q#BA\u000e\u001f\u0013\ta#FA\u0004Ue&<w-\u001a:\u0011\u00059\nT\"A\u0018\u000b\u0003A\nQa]2bY\u0006L!AM\u0018\u0003\u000fA\u0013x\u000eZ;diB\u0011A\u0007\u0010\b\u0003kir!AN\u001d\u000e\u0003]R!\u0001\u000f\u0014\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0001\u0014BA\u001e0\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0010 \u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005mz\u0013AC5oi\u0016\u0014h/\u00197NgV\t\u0011\t\u0005\u0002/\u0005&\u00111i\f\u0002\u0005\u0019>tw-A\u0006j]R,'O^1m\u001bN\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002H\u0013B\u0011\u0001\nA\u0007\u00025!)qh\u0001a\u0001\u0003\u0006!1m\u001c9z)\t9E\nC\u0004@\tA\u0005\t\u0019A!\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\tqJ\u000b\u0002B!.\n\u0011\u000b\u0005\u0002S/6\t1K\u0003\u0002U+\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003->\n!\"\u00198o_R\fG/[8o\u0013\tA6KA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A.\u0011\u0005q\u000bW\"A/\u000b\u0005y{\u0016\u0001\u00027b]\u001eT\u0011\u0001Y\u0001\u0005U\u00064\u0018-\u0003\u0002c;\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012!\u001a\t\u0003]\u0019L!aZ\u0018\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005)l\u0007C\u0001\u0018l\u0013\tawFA\u0002B]fDqA\u001c\u0005\u0002\u0002\u0003\u0007Q-A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002cB\u0019!/\u001e6\u000e\u0003MT!\u0001^\u0018\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002wg\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\tIH\u0010\u0005\u0002/u&\u00111p\f\u0002\b\u0005>|G.Z1o\u0011\u001dq'\"!AA\u0002)\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u00111l \u0005\b].\t\t\u00111\u0001f\u0003!A\u0017m\u001d5D_\u0012,G#A3\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012aW\u0001\u0007KF,\u0018\r\\:\u0015\u0007e\fi\u0001C\u0004o\u001d\u0005\u0005\t\u0019\u00016\u0002#\r{g\u000e^5ok>,8\u000f\u0016:jO\u001e,'\u000f\u0005\u0002I!M)\u0001#!\u0006\u0002\u001cA\u0019a&a\u0006\n\u0007\u0005eqF\u0001\u0004B]f\u0014VM\u001a\t\u0005\u0003;\t\u0019#\u0004\u0002\u0002 )\u0019\u0011\u0011E0\u0002\u0005%|\u0017bA\u001f\u0002 Q\u0011\u0011\u0011C\u0001\u0006CB\u0004H.\u001f\u000b\u0004\u000f\u0006-\u0002bBA\u0017%\u0001\u0007\u0011qF\u0001\tS:$XM\u001d<bYB!\u0011\u0011GA\u001d\u001d\u0011\t\u0019$!\u000e\u0011\u0005Yz\u0013bAA\u001c_\u00051\u0001K]3eK\u001aL1AYA\u001e\u0015\r\t9d\f\u000b\u0004\u000f\u0006}\u0002bBA\u0017'\u0001\u0007\u0011\u0011\t\t\u0005\u0003\u0007\ni%\u0004\u0002\u0002F)!\u0011qIA%\u0003!!WO]1uS>t'bAA&_\u0005Q1m\u001c8dkJ\u0014XM\u001c;\n\t\u0005=\u0013Q\t\u0002\t\tV\u0014\u0018\r^5p]\u000611M]3bi\u0016$2aRA+\u0011\u001d\ti\u0003\u0006a\u0001\u0003_!RaRA-\u00037Ba!!\f\u0016\u0001\u0004\t\u0005bBA/+\u0001\u0007\u0011qL\u0001\u0005k:LG\u000f\u0005\u0003\u0002b\u0005%TBAA2\u0015\u0011\tY%!\u001a\u000b\u0007\u0005\u001dt,\u0001\u0003vi&d\u0017\u0002BA6\u0003G\u0012\u0001\u0002V5nKVs\u0017\u000e\u001e\u000b\u0004\u000f\u0006=\u0004\"B \u0017\u0001\u0004\t\u0015aB;oCB\u0004H.\u001f\u000b\u0005\u0003k\nY\b\u0005\u0003/\u0003o\n\u0015bAA=_\t1q\n\u001d;j_:D\u0001\"! \u0018\u0003\u0003\u0005\raR\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAAB!\ra\u0016QQ\u0005\u0004\u0003\u000fk&AB(cU\u0016\u001cG\u000f"
)
public class ContinuousTrigger extends Trigger implements Product, Serializable {
   private final long intervalMs;

   public static Option unapply(final ContinuousTrigger x$0) {
      return ContinuousTrigger$.MODULE$.unapply(x$0);
   }

   public static ContinuousTrigger apply(final long intervalMs) {
      return ContinuousTrigger$.MODULE$.apply(intervalMs);
   }

   public static ContinuousTrigger create(final long interval, final TimeUnit unit) {
      return ContinuousTrigger$.MODULE$.create(interval, unit);
   }

   public static ContinuousTrigger create(final String interval) {
      return ContinuousTrigger$.MODULE$.create(interval);
   }

   public static ContinuousTrigger apply(final Duration interval) {
      return ContinuousTrigger$.MODULE$.apply(interval);
   }

   public static ContinuousTrigger apply(final String interval) {
      return ContinuousTrigger$.MODULE$.apply(interval);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long intervalMs() {
      return this.intervalMs;
   }

   public ContinuousTrigger copy(final long intervalMs) {
      return new ContinuousTrigger(intervalMs);
   }

   public long copy$default$1() {
      return this.intervalMs();
   }

   public String productPrefix() {
      return "ContinuousTrigger";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.intervalMs());
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
      return x$1 instanceof ContinuousTrigger;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "intervalMs";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.intervalMs()));
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label36: {
            if (x$1 instanceof ContinuousTrigger) {
               ContinuousTrigger var4 = (ContinuousTrigger)x$1;
               if (this.intervalMs() == var4.intervalMs() && var4.canEqual(this)) {
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

   public ContinuousTrigger(final long intervalMs) {
      this.intervalMs = intervalMs;
      Product.$init$(this);
      Triggers$.MODULE$.validate(intervalMs);
   }
}

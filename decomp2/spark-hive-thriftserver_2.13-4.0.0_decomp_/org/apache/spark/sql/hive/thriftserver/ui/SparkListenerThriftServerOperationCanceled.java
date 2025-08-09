package org.apache.spark.sql.hive.thriftserver.ui;

import java.io.Serializable;
import org.apache.spark.scheduler.SparkListenerEvent;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ud!B\r\u001b\u0001rA\u0003\u0002C#\u0001\u0005+\u0007I\u0011\u0001$\t\u0011=\u0003!\u0011#Q\u0001\n\u001dC\u0001\u0002\u0015\u0001\u0003\u0016\u0004%\t!\u0015\u0005\t+\u0002\u0011\t\u0012)A\u0005%\")a\u000b\u0001C\u0001/\"9A\fAA\u0001\n\u0003i\u0006b\u00021\u0001#\u0003%\t!\u0019\u0005\bY\u0002\t\n\u0011\"\u0001n\u0011\u001dy\u0007!!A\u0005BADq\u0001\u001f\u0001\u0002\u0002\u0013\u0005\u0011\u0010C\u0004~\u0001\u0005\u0005I\u0011\u0001@\t\u0013\u0005%\u0001!!A\u0005B\u0005-\u0001\"CA\r\u0001\u0005\u0005I\u0011AA\u000e\u0011%\t)\u0003AA\u0001\n\u0003\n9\u0003C\u0005\u0002,\u0001\t\t\u0011\"\u0011\u0002.!I\u0011q\u0006\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0007\u0005\n\u0003g\u0001\u0011\u0011!C!\u0003k9!\"!\u000f\u001b\u0003\u0003E\t\u0001HA\u001e\r%I\"$!A\t\u0002q\ti\u0004\u0003\u0004W'\u0011\u0005\u0011Q\u000b\u0005\n\u0003_\u0019\u0012\u0011!C#\u0003cA\u0011\"a\u0016\u0014\u0003\u0003%\t)!\u0017\t\u0013\u0005}3#!A\u0005\u0002\u0006\u0005\u0004\"CA:'\u0005\u0005I\u0011BA;\u0005)\u001a\u0006/\u0019:l\u0019&\u001cH/\u001a8feRC'/\u001b4u'\u0016\u0014h/\u001a:Pa\u0016\u0014\u0018\r^5p]\u000e\u000bgnY3mK\u0012T!a\u0007\u000f\u0002\u0005UL'BA\u000f\u001f\u00031!\bN]5giN,'O^3s\u0015\ty\u0002%\u0001\u0003iSZ,'BA\u0011#\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003G\u0011\nQa\u001d9be.T!!\n\u0014\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00059\u0013aA8sON)\u0001!K\u00186qA\u0011!&L\u0007\u0002W)\tA&A\u0003tG\u0006d\u0017-\u0003\u0002/W\t1\u0011I\\=SK\u001a\u0004\"\u0001M\u001a\u000e\u0003ER!A\r\u0012\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014\u0018B\u0001\u001b2\u0005I\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u00163XM\u001c;\u0011\u0005)2\u0014BA\u001c,\u0005\u001d\u0001&o\u001c3vGR\u0004\"!\u000f\"\u000f\u0005i\u0002eBA\u001e@\u001b\u0005a$BA\u001f?\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\u0017\n\u0005\u0005[\u0013a\u00029bG.\fw-Z\u0005\u0003\u0007\u0012\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!!Q\u0016\u0002\u0005%$W#A$\u0011\u0005!ceBA%K!\tY4&\u0003\u0002LW\u00051\u0001K]3eK\u001aL!!\u0014(\u0003\rM#(/\u001b8h\u0015\tY5&A\u0002jI\u0002\n!BZ5oSNDG+[7f+\u0005\u0011\u0006C\u0001\u0016T\u0013\t!6F\u0001\u0003M_:<\u0017a\u00034j]&\u001c\b\u000eV5nK\u0002\na\u0001P5oSRtDc\u0001-[7B\u0011\u0011\fA\u0007\u00025!)Q)\u0002a\u0001\u000f\")\u0001+\u0002a\u0001%\u0006!1m\u001c9z)\rAfl\u0018\u0005\b\u000b\u001a\u0001\n\u00111\u0001H\u0011\u001d\u0001f\u0001%AA\u0002I\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001cU\t95mK\u0001e!\t)'.D\u0001g\u0015\t9\u0007.A\u0005v]\u000eDWmY6fI*\u0011\u0011nK\u0001\u000bC:tw\u000e^1uS>t\u0017BA6g\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0005q'F\u0001*d\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t\u0011\u000f\u0005\u0002so6\t1O\u0003\u0002uk\u0006!A.\u00198h\u0015\u00051\u0018\u0001\u00026bm\u0006L!!T:\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003i\u0004\"AK>\n\u0005q\\#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HcA@\u0002\u0006A\u0019!&!\u0001\n\u0007\u0005\r1FA\u0002B]fD\u0001\"a\u0002\f\u0003\u0003\u0005\rA_\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u00055\u0001#BA\b\u0003+yXBAA\t\u0015\r\t\u0019bK\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\f\u0003#\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011QDA\u0012!\rQ\u0013qD\u0005\u0004\u0003CY#a\u0002\"p_2,\u0017M\u001c\u0005\t\u0003\u000fi\u0011\u0011!a\u0001\u007f\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\r\t\u0018\u0011\u0006\u0005\t\u0003\u000fq\u0011\u0011!a\u0001u\u0006A\u0001.Y:i\u0007>$W\rF\u0001{\u0003!!xn\u0015;sS:<G#A9\u0002\r\u0015\fX/\u00197t)\u0011\ti\"a\u000e\t\u0011\u0005\u001d\u0011#!AA\u0002}\f!f\u00159be.d\u0015n\u001d;f]\u0016\u0014H\u000b\u001b:jMR\u001cVM\u001d<fe>\u0003XM]1uS>t7)\u00198dK2,G\r\u0005\u0002Z'M)1#a\u0010\u0002LA9\u0011\u0011IA$\u000fJCVBAA\"\u0015\r\t)eK\u0001\beVtG/[7f\u0013\u0011\tI%a\u0011\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t'\u0007\u0005\u0003\u0002N\u0005MSBAA(\u0015\r\t\t&^\u0001\u0003S>L1aQA()\t\tY$A\u0003baBd\u0017\u0010F\u0003Y\u00037\ni\u0006C\u0003F-\u0001\u0007q\tC\u0003Q-\u0001\u0007!+A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005\r\u0014q\u000e\t\u0006U\u0005\u0015\u0014\u0011N\u0005\u0004\u0003OZ#AB(qi&|g\u000eE\u0003+\u0003W:%+C\u0002\u0002n-\u0012a\u0001V;qY\u0016\u0014\u0004\u0002CA9/\u0005\u0005\t\u0019\u0001-\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002xA\u0019!/!\u001f\n\u0007\u0005m4O\u0001\u0004PE*,7\r\u001e"
)
public class SparkListenerThriftServerOperationCanceled implements SparkListenerEvent, Product, Serializable {
   private final String id;
   private final long finishTime;

   public static Option unapply(final SparkListenerThriftServerOperationCanceled x$0) {
      return SparkListenerThriftServerOperationCanceled$.MODULE$.unapply(x$0);
   }

   public static SparkListenerThriftServerOperationCanceled apply(final String id, final long finishTime) {
      return SparkListenerThriftServerOperationCanceled$.MODULE$.apply(id, finishTime);
   }

   public static Function1 tupled() {
      return SparkListenerThriftServerOperationCanceled$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerThriftServerOperationCanceled$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public String id() {
      return this.id;
   }

   public long finishTime() {
      return this.finishTime;
   }

   public SparkListenerThriftServerOperationCanceled copy(final String id, final long finishTime) {
      return new SparkListenerThriftServerOperationCanceled(id, finishTime);
   }

   public String copy$default$1() {
      return this.id();
   }

   public long copy$default$2() {
      return this.finishTime();
   }

   public String productPrefix() {
      return "SparkListenerThriftServerOperationCanceled";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.id();
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.finishTime());
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
      return x$1 instanceof SparkListenerThriftServerOperationCanceled;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "id";
         }
         case 1 -> {
            return "finishTime";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.id()));
      var1 = Statics.mix(var1, Statics.longHash(this.finishTime()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof SparkListenerThriftServerOperationCanceled) {
               SparkListenerThriftServerOperationCanceled var4 = (SparkListenerThriftServerOperationCanceled)x$1;
               if (this.finishTime() == var4.finishTime()) {
                  label44: {
                     String var10000 = this.id();
                     String var5 = var4.id();
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

   public SparkListenerThriftServerOperationCanceled(final String id, final long finishTime) {
      this.id = id;
      this.finishTime = finishTime;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}

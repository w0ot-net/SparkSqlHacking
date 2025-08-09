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
   bytes = "\u0006\u0005\u0005ud!B\r\u001b\u0001rA\u0003\u0002C#\u0001\u0005+\u0007I\u0011\u0001$\t\u0011=\u0003!\u0011#Q\u0001\n\u001dC\u0001\u0002\u0015\u0001\u0003\u0016\u0004%\t!\u0015\u0005\t+\u0002\u0011\t\u0012)A\u0005%\")a\u000b\u0001C\u0001/\"9A\fAA\u0001\n\u0003i\u0006b\u00021\u0001#\u0003%\t!\u0019\u0005\bY\u0002\t\n\u0011\"\u0001n\u0011\u001dy\u0007!!A\u0005BADq\u0001\u001f\u0001\u0002\u0002\u0013\u0005\u0011\u0010C\u0004~\u0001\u0005\u0005I\u0011\u0001@\t\u0013\u0005%\u0001!!A\u0005B\u0005-\u0001\"CA\r\u0001\u0005\u0005I\u0011AA\u000e\u0011%\t)\u0003AA\u0001\n\u0003\n9\u0003C\u0005\u0002,\u0001\t\t\u0011\"\u0011\u0002.!I\u0011q\u0006\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0007\u0005\n\u0003g\u0001\u0011\u0011!C!\u0003k9!\"!\u000f\u001b\u0003\u0003E\t\u0001HA\u001e\r%I\"$!A\t\u0002q\ti\u0004\u0003\u0004W'\u0011\u0005\u0011Q\u000b\u0005\n\u0003_\u0019\u0012\u0011!C#\u0003cA\u0011\"a\u0016\u0014\u0003\u0003%\t)!\u0017\t\u0013\u0005}3#!A\u0005\u0002\u0006\u0005\u0004\"CA:'\u0005\u0005I\u0011BA;\u0005!\u001a\u0006/\u0019:l\u0019&\u001cH/\u001a8feRC'/\u001b4u'\u0016\u0014h/\u001a:Pa\u0016\u0014\u0018\r^5p]\u001aKg.[:i\u0015\tYB$\u0001\u0002vS*\u0011QDH\u0001\ri\"\u0014\u0018N\u001a;tKJ4XM\u001d\u0006\u0003?\u0001\nA\u0001[5wK*\u0011\u0011EI\u0001\u0004gFd'BA\u0012%\u0003\u0015\u0019\b/\u0019:l\u0015\t)c%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002O\u0005\u0019qN]4\u0014\u000b\u0001Is&\u000e\u001d\u0011\u0005)jS\"A\u0016\u000b\u00031\nQa]2bY\u0006L!AL\u0016\u0003\r\u0005s\u0017PU3g!\t\u00014'D\u00012\u0015\t\u0011$%A\u0005tG\",G-\u001e7fe&\u0011A'\r\u0002\u0013'B\f'o\u001b'jgR,g.\u001a:Fm\u0016tG\u000f\u0005\u0002+m%\u0011qg\u000b\u0002\b!J|G-^2u!\tI$I\u0004\u0002;\u0001:\u00111hP\u0007\u0002y)\u0011QHP\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tA&\u0003\u0002BW\u00059\u0001/Y2lC\u001e,\u0017BA\"E\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\t5&\u0001\u0002jIV\tq\t\u0005\u0002I\u0019:\u0011\u0011J\u0013\t\u0003w-J!aS\u0016\u0002\rA\u0013X\rZ3g\u0013\tieJ\u0001\u0004TiJLgn\u001a\u0006\u0003\u0017.\n1!\u001b3!\u0003)1\u0017N\\5tQRKW.Z\u000b\u0002%B\u0011!fU\u0005\u0003).\u0012A\u0001T8oO\u0006Ya-\u001b8jg\"$\u0016.\\3!\u0003\u0019a\u0014N\\5u}Q\u0019\u0001LW.\u0011\u0005e\u0003Q\"\u0001\u000e\t\u000b\u0015+\u0001\u0019A$\t\u000bA+\u0001\u0019\u0001*\u0002\t\r|\u0007/\u001f\u000b\u00041z{\u0006bB#\u0007!\u0003\u0005\ra\u0012\u0005\b!\u001a\u0001\n\u00111\u0001S\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012A\u0019\u0016\u0003\u000f\u000e\\\u0013\u0001\u001a\t\u0003K*l\u0011A\u001a\u0006\u0003O\"\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005%\\\u0013AC1o]>$\u0018\r^5p]&\u00111N\u001a\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002]*\u0012!kY\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003E\u0004\"A]<\u000e\u0003MT!\u0001^;\u0002\t1\fgn\u001a\u0006\u0002m\u0006!!.\u0019<b\u0013\ti5/\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001{!\tQ30\u0003\u0002}W\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0019q0!\u0002\u0011\u0007)\n\t!C\u0002\u0002\u0004-\u00121!\u00118z\u0011!\t9aCA\u0001\u0002\u0004Q\u0018a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002\u000eA)\u0011qBA\u000b\u007f6\u0011\u0011\u0011\u0003\u0006\u0004\u0003'Y\u0013AC2pY2,7\r^5p]&!\u0011qCA\t\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005u\u00111\u0005\t\u0004U\u0005}\u0011bAA\u0011W\t9!i\\8mK\u0006t\u0007\u0002CA\u0004\u001b\u0005\u0005\t\u0019A@\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004c\u0006%\u0002\u0002CA\u0004\u001d\u0005\u0005\t\u0019\u0001>\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012A_\u0001\ti>\u001cFO]5oOR\t\u0011/\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003;\t9\u0004\u0003\u0005\u0002\bE\t\t\u00111\u0001\u0000\u0003!\u001a\u0006/\u0019:l\u0019&\u001cH/\u001a8feRC'/\u001b4u'\u0016\u0014h/\u001a:Pa\u0016\u0014\u0018\r^5p]\u001aKg.[:i!\tI6cE\u0003\u0014\u0003\u007f\tY\u0005E\u0004\u0002B\u0005\u001dsI\u0015-\u000e\u0005\u0005\r#bAA#W\u00059!/\u001e8uS6,\u0017\u0002BA%\u0003\u0007\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83!\u0011\ti%a\u0015\u000e\u0005\u0005=#bAA)k\u0006\u0011\u0011n\\\u0005\u0004\u0007\u0006=CCAA\u001e\u0003\u0015\t\u0007\u000f\u001d7z)\u0015A\u00161LA/\u0011\u0015)e\u00031\u0001H\u0011\u0015\u0001f\u00031\u0001S\u0003\u001d)h.\u00199qYf$B!a\u0019\u0002pA)!&!\u001a\u0002j%\u0019\u0011qM\u0016\u0003\r=\u0003H/[8o!\u0015Q\u00131N$S\u0013\r\tig\u000b\u0002\u0007)V\u0004H.\u001a\u001a\t\u0011\u0005Et#!AA\u0002a\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t9\bE\u0002s\u0003sJ1!a\u001ft\u0005\u0019y%M[3di\u0002"
)
public class SparkListenerThriftServerOperationFinish implements SparkListenerEvent, Product, Serializable {
   private final String id;
   private final long finishTime;

   public static Option unapply(final SparkListenerThriftServerOperationFinish x$0) {
      return SparkListenerThriftServerOperationFinish$.MODULE$.unapply(x$0);
   }

   public static SparkListenerThriftServerOperationFinish apply(final String id, final long finishTime) {
      return SparkListenerThriftServerOperationFinish$.MODULE$.apply(id, finishTime);
   }

   public static Function1 tupled() {
      return SparkListenerThriftServerOperationFinish$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerThriftServerOperationFinish$.MODULE$.curried();
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

   public SparkListenerThriftServerOperationFinish copy(final String id, final long finishTime) {
      return new SparkListenerThriftServerOperationFinish(id, finishTime);
   }

   public String copy$default$1() {
      return this.id();
   }

   public long copy$default$2() {
      return this.finishTime();
   }

   public String productPrefix() {
      return "SparkListenerThriftServerOperationFinish";
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
      return x$1 instanceof SparkListenerThriftServerOperationFinish;
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
            if (x$1 instanceof SparkListenerThriftServerOperationFinish) {
               SparkListenerThriftServerOperationFinish var4 = (SparkListenerThriftServerOperationFinish)x$1;
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

   public SparkListenerThriftServerOperationFinish(final String id, final long finishTime) {
      this.id = id;
      this.finishTime = finishTime;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}

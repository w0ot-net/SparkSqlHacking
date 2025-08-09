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
   bytes = "\u0006\u0005\u0005ud!B\r\u001b\u0001rA\u0003\u0002C#\u0001\u0005+\u0007I\u0011\u0001$\t\u0011=\u0003!\u0011#Q\u0001\n\u001dC\u0001\u0002\u0015\u0001\u0003\u0016\u0004%\t!\u0015\u0005\t+\u0002\u0011\t\u0012)A\u0005%\")a\u000b\u0001C\u0001/\"9A\fAA\u0001\n\u0003i\u0006b\u00021\u0001#\u0003%\t!\u0019\u0005\bY\u0002\t\n\u0011\"\u0001n\u0011\u001dy\u0007!!A\u0005BADq\u0001\u001f\u0001\u0002\u0002\u0013\u0005\u0011\u0010C\u0004~\u0001\u0005\u0005I\u0011\u0001@\t\u0013\u0005%\u0001!!A\u0005B\u0005-\u0001\"CA\r\u0001\u0005\u0005I\u0011AA\u000e\u0011%\t)\u0003AA\u0001\n\u0003\n9\u0003C\u0005\u0002,\u0001\t\t\u0011\"\u0011\u0002.!I\u0011q\u0006\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0007\u0005\n\u0003g\u0001\u0011\u0011!C!\u0003k9!\"!\u000f\u001b\u0003\u0003E\t\u0001HA\u001e\r%I\"$!A\t\u0002q\ti\u0004\u0003\u0004W'\u0011\u0005\u0011Q\u000b\u0005\n\u0003_\u0019\u0012\u0011!C#\u0003cA\u0011\"a\u0016\u0014\u0003\u0003%\t)!\u0017\t\u0013\u0005}3#!A\u0005\u0002\u0006\u0005\u0004\"CA:'\u0005\u0005I\u0011BA;\u0005%\u001a\u0006/\u0019:l\u0019&\u001cH/\u001a8feRC'/\u001b4u'\u0016\u0014h/\u001a:Pa\u0016\u0014\u0018\r^5p]RKW.Z8vi*\u00111\u0004H\u0001\u0003k&T!!\b\u0010\u0002\u0019QD'/\u001b4ug\u0016\u0014h/\u001a:\u000b\u0005}\u0001\u0013\u0001\u00025jm\u0016T!!\t\u0012\u0002\u0007M\fHN\u0003\u0002$I\u0005)1\u000f]1sW*\u0011QEJ\u0001\u0007CB\f7\r[3\u000b\u0003\u001d\n1a\u001c:h'\u0015\u0001\u0011fL\u001b9!\tQS&D\u0001,\u0015\u0005a\u0013!B:dC2\f\u0017B\u0001\u0018,\u0005\u0019\te.\u001f*fMB\u0011\u0001gM\u0007\u0002c)\u0011!GI\u0001\ng\u000eDW\rZ;mKJL!\u0001N\u0019\u0003%M\u0003\u0018M]6MSN$XM\\3s\u000bZ,g\u000e\u001e\t\u0003UYJ!aN\u0016\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0011H\u0011\b\u0003u\u0001s!aO \u000e\u0003qR!!\u0010 \u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011\u0001L\u0005\u0003\u0003.\nq\u0001]1dW\u0006<W-\u0003\u0002D\t\na1+\u001a:jC2L'0\u00192mK*\u0011\u0011iK\u0001\u0003S\u0012,\u0012a\u0012\t\u0003\u00112s!!\u0013&\u0011\u0005mZ\u0013BA&,\u0003\u0019\u0001&/\u001a3fM&\u0011QJ\u0014\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005-[\u0013aA5eA\u0005Qa-\u001b8jg\"$\u0016.\\3\u0016\u0003I\u0003\"AK*\n\u0005Q[#\u0001\u0002'p]\u001e\f1BZ5oSNDG+[7fA\u00051A(\u001b8jiz\"2\u0001\u0017.\\!\tI\u0006!D\u0001\u001b\u0011\u0015)U\u00011\u0001H\u0011\u0015\u0001V\u00011\u0001S\u0003\u0011\u0019w\u000e]=\u0015\u0007asv\fC\u0004F\rA\u0005\t\u0019A$\t\u000fA3\u0001\u0013!a\u0001%\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u00012+\u0005\u001d\u001b7&\u00013\u0011\u0005\u0015TW\"\u00014\u000b\u0005\u001dD\u0017!C;oG\",7m[3e\u0015\tI7&\u0001\u0006b]:|G/\u0019;j_:L!a\u001b4\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u00039T#AU2\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005\t\bC\u0001:x\u001b\u0005\u0019(B\u0001;v\u0003\u0011a\u0017M\\4\u000b\u0003Y\fAA[1wC&\u0011Qj]\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002uB\u0011!f_\u0005\u0003y.\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$2a`A\u0003!\rQ\u0013\u0011A\u0005\u0004\u0003\u0007Y#aA!os\"A\u0011qA\u0006\u0002\u0002\u0003\u0007!0A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u001b\u0001R!a\u0004\u0002\u0016}l!!!\u0005\u000b\u0007\u0005M1&\u0001\u0006d_2dWm\u0019;j_:LA!a\u0006\u0002\u0012\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\ti\"a\t\u0011\u0007)\ny\"C\u0002\u0002\"-\u0012qAQ8pY\u0016\fg\u000e\u0003\u0005\u0002\b5\t\t\u00111\u0001\u0000\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007E\fI\u0003\u0003\u0005\u0002\b9\t\t\u00111\u0001{\u0003!A\u0017m\u001d5D_\u0012,G#\u0001>\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012!]\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005u\u0011q\u0007\u0005\t\u0003\u000f\t\u0012\u0011!a\u0001\u007f\u0006I3\u000b]1sW2K7\u000f^3oKJ$\u0006N]5giN+'O^3s\u001fB,'/\u0019;j_:$\u0016.\\3pkR\u0004\"!W\n\u0014\u000bM\ty$a\u0013\u0011\u000f\u0005\u0005\u0013qI$S16\u0011\u00111\t\u0006\u0004\u0003\u000bZ\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003\u0013\n\u0019EA\tBEN$(/Y2u\rVt7\r^5p]J\u0002B!!\u0014\u0002T5\u0011\u0011q\n\u0006\u0004\u0003#*\u0018AA5p\u0013\r\u0019\u0015q\n\u000b\u0003\u0003w\tQ!\u00199qYf$R\u0001WA.\u0003;BQ!\u0012\fA\u0002\u001dCQ\u0001\u0015\fA\u0002I\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002d\u0005=\u0004#\u0002\u0016\u0002f\u0005%\u0014bAA4W\t1q\n\u001d;j_:\u0004RAKA6\u000fJK1!!\u001c,\u0005\u0019!V\u000f\u001d7fe!A\u0011\u0011O\f\u0002\u0002\u0003\u0007\u0001,A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\u001e\u0011\u0007I\fI(C\u0002\u0002|M\u0014aa\u00142kK\u000e$\b"
)
public class SparkListenerThriftServerOperationTimeout implements SparkListenerEvent, Product, Serializable {
   private final String id;
   private final long finishTime;

   public static Option unapply(final SparkListenerThriftServerOperationTimeout x$0) {
      return SparkListenerThriftServerOperationTimeout$.MODULE$.unapply(x$0);
   }

   public static SparkListenerThriftServerOperationTimeout apply(final String id, final long finishTime) {
      return SparkListenerThriftServerOperationTimeout$.MODULE$.apply(id, finishTime);
   }

   public static Function1 tupled() {
      return SparkListenerThriftServerOperationTimeout$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerThriftServerOperationTimeout$.MODULE$.curried();
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

   public SparkListenerThriftServerOperationTimeout copy(final String id, final long finishTime) {
      return new SparkListenerThriftServerOperationTimeout(id, finishTime);
   }

   public String copy$default$1() {
      return this.id();
   }

   public long copy$default$2() {
      return this.finishTime();
   }

   public String productPrefix() {
      return "SparkListenerThriftServerOperationTimeout";
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
      return x$1 instanceof SparkListenerThriftServerOperationTimeout;
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
            if (x$1 instanceof SparkListenerThriftServerOperationTimeout) {
               SparkListenerThriftServerOperationTimeout var4 = (SparkListenerThriftServerOperationTimeout)x$1;
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

   public SparkListenerThriftServerOperationTimeout(final String id, final long finishTime) {
      this.id = id;
      this.finishTime = finishTime;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}

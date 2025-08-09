package org.apache.spark.scheduler;

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
   bytes = "\u0006\u0005\u0005\u001dd\u0001B\r\u001b\u0001\u000eB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005\u007f!A1\t\u0001BK\u0002\u0013\u0005a\b\u0003\u0005E\u0001\tE\t\u0015!\u0003@\u0011\u0015)\u0005\u0001\"\u0001G\u0011\u001dQ\u0005!!A\u0005\u0002-CqA\u0014\u0001\u0012\u0002\u0013\u0005q\nC\u0004[\u0001E\u0005I\u0011A(\t\u000fm\u0003\u0011\u0011!C!9\"9Q\rAA\u0001\n\u0003q\u0004b\u00024\u0001\u0003\u0003%\ta\u001a\u0005\b[\u0002\t\t\u0011\"\u0011o\u0011\u001d)\b!!A\u0005\u0002YDqa\u001f\u0001\u0002\u0002\u0013\u0005C\u0010C\u0004\u007f\u0001\u0005\u0005I\u0011I@\t\u0013\u0005\u0005\u0001!!A\u0005B\u0005\r\u0001\"CA\u0003\u0001\u0005\u0005I\u0011IA\u0004\u000f%\t\u0019CGA\u0001\u0012\u0003\t)C\u0002\u0005\u001a5\u0005\u0005\t\u0012AA\u0014\u0011\u0019)5\u0003\"\u0001\u0002@!I\u0011\u0011A\n\u0002\u0002\u0013\u0015\u00131\u0001\u0005\n\u0003\u0003\u001a\u0012\u0011!CA\u0003\u0007B\u0011\"!\u0013\u0014\u0003\u0003%\t)a\u0013\t\u0013\u0005u3#!A\u0005\n\u0005}#AJ*qCJ\\G*[:uK:,'/\u00168tG\",G-\u001e7bE2,G+Y:l'\u0016$\u0018\t\u001a3fI*\u00111\u0004H\u0001\ng\u000eDW\rZ;mKJT!!\b\u0010\u0002\u000bM\u0004\u0018M]6\u000b\u0005}\u0001\u0013AB1qC\u000eDWMC\u0001\"\u0003\ry'oZ\u0002\u0001'\u0015\u0001AE\u000b\u00182!\t)\u0003&D\u0001'\u0015\u00059\u0013!B:dC2\f\u0017BA\u0015'\u0005\u0019\te.\u001f*fMB\u00111\u0006L\u0007\u00025%\u0011QF\u0007\u0002\u0013'B\f'o\u001b'jgR,g.\u001a:Fm\u0016tG\u000f\u0005\u0002&_%\u0011\u0001G\n\u0002\b!J|G-^2u!\t\u0011$H\u0004\u00024q9\u0011AgN\u0007\u0002k)\u0011aGI\u0001\u0007yI|w\u000e\u001e \n\u0003\u001dJ!!\u000f\u0014\u0002\u000fA\f7m[1hK&\u00111\b\u0010\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003s\u0019\nqa\u001d;bO\u0016LE-F\u0001@!\t)\u0003)\u0003\u0002BM\t\u0019\u0011J\u001c;\u0002\u0011M$\u0018mZ3JI\u0002\nab\u001d;bO\u0016\fE\u000f^3naRLE-A\bti\u0006<W-\u0011;uK6\u0004H/\u00133!\u0003\u0019a\u0014N\\5u}Q\u0019q\tS%\u0011\u0005-\u0002\u0001\"B\u001f\u0006\u0001\u0004y\u0004\"B\"\u0006\u0001\u0004y\u0014\u0001B2paf$2a\u0012'N\u0011\u001did\u0001%AA\u0002}Bqa\u0011\u0004\u0011\u0002\u0003\u0007q(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003AS#aP),\u0003I\u0003\"a\u0015-\u000e\u0003QS!!\u0016,\u0002\u0013Ut7\r[3dW\u0016$'BA,'\u0003)\tgN\\8uCRLwN\\\u0005\u00033R\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A/\u0011\u0005y\u001bW\"A0\u000b\u0005\u0001\f\u0017\u0001\u00027b]\u001eT\u0011AY\u0001\u0005U\u00064\u0018-\u0003\u0002e?\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002iWB\u0011Q%[\u0005\u0003U\u001a\u00121!\u00118z\u0011\u001da7\"!AA\u0002}\n1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A8\u0011\u0007A\u001c\b.D\u0001r\u0015\t\u0011h%\u0001\u0006d_2dWm\u0019;j_:L!\u0001^9\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0003oj\u0004\"!\n=\n\u0005e4#a\u0002\"p_2,\u0017M\u001c\u0005\bY6\t\t\u00111\u0001i\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0005uk\bb\u00027\u000f\u0003\u0003\u0005\raP\u0001\tQ\u0006\u001c\bnQ8eKR\tq(\u0001\u0005u_N#(/\u001b8h)\u0005i\u0016AB3rk\u0006d7\u000fF\u0002x\u0003\u0013Aq\u0001\\\t\u0002\u0002\u0003\u0007\u0001\u000eK\u0002\u0001\u0003\u001b\u0001B!a\u0004\u0002\u00145\u0011\u0011\u0011\u0003\u0006\u0003/rIA!!\u0006\u0002\u0012\taA)\u001a<fY>\u0004XM]!qS\"*\u0001!!\u0007\u0002 A!\u0011qBA\u000e\u0013\u0011\ti\"!\u0005\u0003\u000bMKgnY3\"\u0005\u0005\u0005\u0012!B\u001a/c9\u0002\u0014AJ*qCJ\\G*[:uK:,'/\u00168tG\",G-\u001e7bE2,G+Y:l'\u0016$\u0018\t\u001a3fIB\u00111fE\n\u0006'\u0005%\u0012Q\u0007\t\b\u0003W\t\tdP H\u001b\t\tiCC\u0002\u00020\u0019\nqA];oi&lW-\u0003\u0003\u00024\u00055\"!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeA!\u0011qGA\u001f\u001b\t\tIDC\u0002\u0002<\u0005\f!![8\n\u0007m\nI\u0004\u0006\u0002\u0002&\u0005)\u0011\r\u001d9msR)q)!\u0012\u0002H!)QH\u0006a\u0001\u007f!)1I\u0006a\u0001\u007f\u00059QO\\1qa2LH\u0003BA'\u00033\u0002R!JA(\u0003'J1!!\u0015'\u0005\u0019y\u0005\u000f^5p]B)Q%!\u0016@\u007f%\u0019\u0011q\u000b\u0014\u0003\rQ+\b\u000f\\33\u0011!\tYfFA\u0001\u0002\u00049\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011\u0011\r\t\u0004=\u0006\r\u0014bAA3?\n1qJ\u00196fGR\u0004"
)
public class SparkListenerUnschedulableTaskSetAdded implements SparkListenerEvent, Product, Serializable {
   private final int stageId;
   private final int stageAttemptId;

   public static Option unapply(final SparkListenerUnschedulableTaskSetAdded x$0) {
      return SparkListenerUnschedulableTaskSetAdded$.MODULE$.unapply(x$0);
   }

   public static SparkListenerUnschedulableTaskSetAdded apply(final int stageId, final int stageAttemptId) {
      return SparkListenerUnschedulableTaskSetAdded$.MODULE$.apply(stageId, stageAttemptId);
   }

   public static Function1 tupled() {
      return SparkListenerUnschedulableTaskSetAdded$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerUnschedulableTaskSetAdded$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public int stageId() {
      return this.stageId;
   }

   public int stageAttemptId() {
      return this.stageAttemptId;
   }

   public SparkListenerUnschedulableTaskSetAdded copy(final int stageId, final int stageAttemptId) {
      return new SparkListenerUnschedulableTaskSetAdded(stageId, stageAttemptId);
   }

   public int copy$default$1() {
      return this.stageId();
   }

   public int copy$default$2() {
      return this.stageAttemptId();
   }

   public String productPrefix() {
      return "SparkListenerUnschedulableTaskSetAdded";
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
      return x$1 instanceof SparkListenerUnschedulableTaskSetAdded;
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

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label38: {
            if (x$1 instanceof SparkListenerUnschedulableTaskSetAdded) {
               SparkListenerUnschedulableTaskSetAdded var4 = (SparkListenerUnschedulableTaskSetAdded)x$1;
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

   public SparkListenerUnschedulableTaskSetAdded(final int stageId, final int stageAttemptId) {
      this.stageId = stageId;
      this.stageAttemptId = stageAttemptId;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}

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
   bytes = "\u0006\u0005\u0005\u001dd\u0001B\r\u001b\u0001\u000eB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005\u007f!A1\t\u0001BK\u0002\u0013\u0005a\b\u0003\u0005E\u0001\tE\t\u0015!\u0003@\u0011\u0015)\u0005\u0001\"\u0001G\u0011\u001dQ\u0005!!A\u0005\u0002-CqA\u0014\u0001\u0012\u0002\u0013\u0005q\nC\u0004[\u0001E\u0005I\u0011A(\t\u000fm\u0003\u0011\u0011!C!9\"9Q\rAA\u0001\n\u0003q\u0004b\u00024\u0001\u0003\u0003%\ta\u001a\u0005\b[\u0002\t\t\u0011\"\u0011o\u0011\u001d)\b!!A\u0005\u0002YDqa\u001f\u0001\u0002\u0002\u0013\u0005C\u0010C\u0004\u007f\u0001\u0005\u0005I\u0011I@\t\u0013\u0005\u0005\u0001!!A\u0005B\u0005\r\u0001\"CA\u0003\u0001\u0005\u0005I\u0011IA\u0004\u000f%\t\u0019CGA\u0001\u0012\u0003\t)C\u0002\u0005\u001a5\u0005\u0005\t\u0012AA\u0014\u0011\u0019)5\u0003\"\u0001\u0002@!I\u0011\u0011A\n\u0002\u0002\u0013\u0015\u00131\u0001\u0005\n\u0003\u0003\u001a\u0012\u0011!CA\u0003\u0007B\u0011\"!\u0013\u0014\u0003\u0003%\t)a\u0013\t\u0013\u0005u3#!A\u0005\n\u0005}#\u0001K*qCJ\\G*[:uK:,'/\u00168tG\",G-\u001e7bE2,G+Y:l'\u0016$(+Z7pm\u0016$'BA\u000e\u001d\u0003%\u00198\r[3ek2,'O\u0003\u0002\u001e=\u0005)1\u000f]1sW*\u0011q\u0004I\u0001\u0007CB\f7\r[3\u000b\u0003\u0005\n1a\u001c:h\u0007\u0001\u0019R\u0001\u0001\u0013+]E\u0002\"!\n\u0015\u000e\u0003\u0019R\u0011aJ\u0001\u0006g\u000e\fG.Y\u0005\u0003S\u0019\u0012a!\u00118z%\u00164\u0007CA\u0016-\u001b\u0005Q\u0012BA\u0017\u001b\u0005I\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u00163XM\u001c;\u0011\u0005\u0015z\u0013B\u0001\u0019'\u0005\u001d\u0001&o\u001c3vGR\u0004\"A\r\u001e\u000f\u0005MBdB\u0001\u001b8\u001b\u0005)$B\u0001\u001c#\u0003\u0019a$o\\8u}%\tq%\u0003\u0002:M\u00059\u0001/Y2lC\u001e,\u0017BA\u001e=\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tId%A\u0004ti\u0006<W-\u00133\u0016\u0003}\u0002\"!\n!\n\u0005\u00053#aA%oi\u0006A1\u000f^1hK&#\u0007%\u0001\bti\u0006<W-\u0011;uK6\u0004H/\u00133\u0002\u001fM$\u0018mZ3BiR,W\u000e\u001d;JI\u0002\na\u0001P5oSRtDcA$I\u0013B\u00111\u0006\u0001\u0005\u0006{\u0015\u0001\ra\u0010\u0005\u0006\u0007\u0016\u0001\raP\u0001\u0005G>\u0004\u0018\u0010F\u0002H\u00196Cq!\u0010\u0004\u0011\u0002\u0003\u0007q\bC\u0004D\rA\u0005\t\u0019A \u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\t\u0001K\u000b\u0002@#.\n!\u000b\u0005\u0002T16\tAK\u0003\u0002V-\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003/\u001a\n!\"\u00198o_R\fG/[8o\u0013\tIFKA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002;B\u0011alY\u0007\u0002?*\u0011\u0001-Y\u0001\u0005Y\u0006twMC\u0001c\u0003\u0011Q\u0017M^1\n\u0005\u0011|&AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005!\\\u0007CA\u0013j\u0013\tQgEA\u0002B]fDq\u0001\\\u0006\u0002\u0002\u0003\u0007q(A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002_B\u0019\u0001o\u001d5\u000e\u0003ET!A\u001d\u0014\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002uc\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\t9(\u0010\u0005\u0002&q&\u0011\u0011P\n\u0002\b\u0005>|G.Z1o\u0011\u001daW\"!AA\u0002!\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0011Q, \u0005\bY:\t\t\u00111\u0001@\u0003!A\u0017m\u001d5D_\u0012,G#A \u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012!X\u0001\u0007KF,\u0018\r\\:\u0015\u0007]\fI\u0001C\u0004m#\u0005\u0005\t\u0019\u00015)\u0007\u0001\ti\u0001\u0005\u0003\u0002\u0010\u0005MQBAA\t\u0015\t9F$\u0003\u0003\u0002\u0016\u0005E!\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0007&\u0002\u0001\u0002\u001a\u0005}\u0001\u0003BA\b\u00037IA!!\b\u0002\u0012\t)1+\u001b8dK\u0006\u0012\u0011\u0011E\u0001\u0006g9\nd\u0006M\u0001)'B\f'o\u001b'jgR,g.\u001a:V]N\u001c\u0007.\u001a3vY\u0006\u0014G.\u001a+bg.\u001cV\r\u001e*f[>4X\r\u001a\t\u0003WM\u0019RaEA\u0015\u0003k\u0001r!a\u000b\u00022}zt)\u0004\u0002\u0002.)\u0019\u0011q\u0006\u0014\u0002\u000fI,h\u000e^5nK&!\u00111GA\u0017\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\t\u0005\u0003o\ti$\u0004\u0002\u0002:)\u0019\u00111H1\u0002\u0005%|\u0017bA\u001e\u0002:Q\u0011\u0011QE\u0001\u0006CB\u0004H.\u001f\u000b\u0006\u000f\u0006\u0015\u0013q\t\u0005\u0006{Y\u0001\ra\u0010\u0005\u0006\u0007Z\u0001\raP\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\ti%!\u0017\u0011\u000b\u0015\ny%a\u0015\n\u0007\u0005EcE\u0001\u0004PaRLwN\u001c\t\u0006K\u0005UshP\u0005\u0004\u0003/2#A\u0002+va2,'\u0007\u0003\u0005\u0002\\]\t\t\u00111\u0001H\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003C\u00022AXA2\u0013\r\t)g\u0018\u0002\u0007\u001f\nTWm\u0019;"
)
public class SparkListenerUnschedulableTaskSetRemoved implements SparkListenerEvent, Product, Serializable {
   private final int stageId;
   private final int stageAttemptId;

   public static Option unapply(final SparkListenerUnschedulableTaskSetRemoved x$0) {
      return SparkListenerUnschedulableTaskSetRemoved$.MODULE$.unapply(x$0);
   }

   public static SparkListenerUnschedulableTaskSetRemoved apply(final int stageId, final int stageAttemptId) {
      return SparkListenerUnschedulableTaskSetRemoved$.MODULE$.apply(stageId, stageAttemptId);
   }

   public static Function1 tupled() {
      return SparkListenerUnschedulableTaskSetRemoved$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerUnschedulableTaskSetRemoved$.MODULE$.curried();
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

   public SparkListenerUnschedulableTaskSetRemoved copy(final int stageId, final int stageAttemptId) {
      return new SparkListenerUnschedulableTaskSetRemoved(stageId, stageAttemptId);
   }

   public int copy$default$1() {
      return this.stageId();
   }

   public int copy$default$2() {
      return this.stageAttemptId();
   }

   public String productPrefix() {
      return "SparkListenerUnschedulableTaskSetRemoved";
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
      return x$1 instanceof SparkListenerUnschedulableTaskSetRemoved;
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
            if (x$1 instanceof SparkListenerUnschedulableTaskSetRemoved) {
               SparkListenerUnschedulableTaskSetRemoved var4 = (SparkListenerUnschedulableTaskSetRemoved)x$1;
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

   public SparkListenerUnschedulableTaskSetRemoved(final int stageId, final int stageAttemptId) {
      this.stageId = stageId;
      this.stageAttemptId = stageAttemptId;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}

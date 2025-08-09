package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import org.apache.spark.streaming.Time;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%d!B\r\u001b\u0001r!\u0003\u0002C \u0001\u0005+\u0007I\u0011\u0001!\t\u0011\u0015\u0003!\u0011#Q\u0001\n\u0005C\u0001B\u0012\u0001\u0003\u0016\u0004%\ta\u0012\u0005\t\u0017\u0002\u0011\t\u0012)A\u0005\u0011\")A\n\u0001C\u0001\u001b\"9\u0011\u000bAA\u0001\n\u0003\u0011\u0006bB+\u0001#\u0003%\tA\u0016\u0005\bC\u0002\t\n\u0011\"\u0001c\u0011\u001d!\u0007!!A\u0005B\u0015DqA\u001c\u0001\u0002\u0002\u0013\u0005q\u000eC\u0004t\u0001\u0005\u0005I\u0011\u0001;\t\u000fi\u0004\u0011\u0011!C!w\"I\u0011Q\u0001\u0001\u0002\u0002\u0013\u0005\u0011q\u0001\u0005\n\u0003#\u0001\u0011\u0011!C!\u0003'A\u0011\"a\u0006\u0001\u0003\u0003%\t%!\u0007\t\u0013\u0005m\u0001!!A\u0005B\u0005u\u0001\"CA\u0010\u0001\u0005\u0005I\u0011IA\u0011\u000f)\t)CGA\u0001\u0012\u0003a\u0012q\u0005\u0004\n3i\t\t\u0011#\u0001\u001d\u0003SAa\u0001T\n\u0005\u0002\u0005\u0005\u0003\"CA\u000e'\u0005\u0005IQIA\u000f\u0011%\t\u0019eEA\u0001\n\u0003\u000b)\u0005C\u0005\u0002LM\t\t\u0011\"!\u0002N!I\u0011qL\n\u0002\u0002\u0013%\u0011\u0011\r\u0002\u0015\u0005\u0006$8\r[!mY>\u001c\u0017\r^5p]\u00163XM\u001c;\u000b\u0005ma\u0012!C:dQ\u0016$W\u000f\\3s\u0015\tib$A\u0005tiJ,\u0017-\\5oO*\u0011q\u0004I\u0001\u0006gB\f'o\u001b\u0006\u0003C\t\na!\u00199bG\",'\"A\u0012\u0002\u0007=\u0014xmE\u0003\u0001K-z#\u0007\u0005\u0002'S5\tqEC\u0001)\u0003\u0015\u00198-\u00197b\u0013\tQsE\u0001\u0004B]f\u0014VM\u001a\t\u0003Y5j\u0011AG\u0005\u0003]i\u0011ADU3dK&4X\r\u001a\"m_\u000e\\GK]1dW\u0016\u0014Hj\\4Fm\u0016tG\u000f\u0005\u0002'a%\u0011\u0011g\n\u0002\b!J|G-^2u!\t\u0019DH\u0004\u00025u9\u0011Q'O\u0007\u0002m)\u0011q\u0007O\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\t\u0001&\u0003\u0002<O\u00059\u0001/Y2lC\u001e,\u0017BA\u001f?\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tYt%\u0001\u0003uS6,W#A!\u0011\u0005\t\u001bU\"\u0001\u000f\n\u0005\u0011c\"\u0001\u0002+j[\u0016\fQ\u0001^5nK\u0002\nq\"\u00197m_\u000e\fG/\u001a3CY>\u001c7n]\u000b\u0002\u0011B\u0011A&S\u0005\u0003\u0015j\u0011q\"\u00117m_\u000e\fG/\u001a3CY>\u001c7n]\u0001\u0011C2dwnY1uK\u0012\u0014En\\2lg\u0002\na\u0001P5oSRtDc\u0001(P!B\u0011A\u0006\u0001\u0005\u0006\u007f\u0015\u0001\r!\u0011\u0005\u0006\r\u0016\u0001\r\u0001S\u0001\u0005G>\u0004\u0018\u0010F\u0002O'RCqa\u0010\u0004\u0011\u0002\u0003\u0007\u0011\tC\u0004G\rA\u0005\t\u0019\u0001%\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\tqK\u000b\u0002B1.\n\u0011\f\u0005\u0002[?6\t1L\u0003\u0002];\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003=\u001e\n!\"\u00198o_R\fG/[8o\u0013\t\u00017LA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'F\u0001dU\tA\u0005,A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002MB\u0011q\r\\\u0007\u0002Q*\u0011\u0011N[\u0001\u0005Y\u0006twMC\u0001l\u0003\u0011Q\u0017M^1\n\u00055D'AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001q!\t1\u0013/\u0003\u0002sO\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011Q\u000f\u001f\t\u0003MYL!a^\u0014\u0003\u0007\u0005s\u0017\u0010C\u0004z\u0017\u0005\u0005\t\u0019\u00019\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005a\b\u0003B?\u0002\u0002Ul\u0011A \u0006\u0003\u007f\u001e\n!bY8mY\u0016\u001cG/[8o\u0013\r\t\u0019A \u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\n\u0005=\u0001c\u0001\u0014\u0002\f%\u0019\u0011QB\u0014\u0003\u000f\t{w\u000e\\3b]\"9\u00110DA\u0001\u0002\u0004)\u0018A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2AZA\u000b\u0011\u001dIh\"!AA\u0002A\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002a\u0006AAo\\*ue&tw\rF\u0001g\u0003\u0019)\u0017/^1mgR!\u0011\u0011BA\u0012\u0011\u001dI\u0018#!AA\u0002U\fACQ1uG\"\fE\u000e\\8dCRLwN\\#wK:$\bC\u0001\u0017\u0014'\u0015\u0019\u00121FA\u001c!\u001d\ti#a\rB\u0011:k!!a\f\u000b\u0007\u0005Er%A\u0004sk:$\u0018.\\3\n\t\u0005U\u0012q\u0006\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003BA\u001d\u0003\u007fi!!a\u000f\u000b\u0007\u0005u\".\u0001\u0002j_&\u0019Q(a\u000f\u0015\u0005\u0005\u001d\u0012!B1qa2LH#\u0002(\u0002H\u0005%\u0003\"B \u0017\u0001\u0004\t\u0005\"\u0002$\u0017\u0001\u0004A\u0015aB;oCB\u0004H.\u001f\u000b\u0005\u0003\u001f\nY\u0006E\u0003'\u0003#\n)&C\u0002\u0002T\u001d\u0012aa\u00149uS>t\u0007#\u0002\u0014\u0002X\u0005C\u0015bAA-O\t1A+\u001e9mKJB\u0001\"!\u0018\u0018\u0003\u0003\u0005\rAT\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA2!\r9\u0017QM\u0005\u0004\u0003OB'AB(cU\u0016\u001cG\u000f"
)
public class BatchAllocationEvent implements ReceivedBlockTrackerLogEvent, Product, Serializable {
   private final Time time;
   private final AllocatedBlocks allocatedBlocks;

   public static Option unapply(final BatchAllocationEvent x$0) {
      return BatchAllocationEvent$.MODULE$.unapply(x$0);
   }

   public static BatchAllocationEvent apply(final Time time, final AllocatedBlocks allocatedBlocks) {
      return BatchAllocationEvent$.MODULE$.apply(time, allocatedBlocks);
   }

   public static Function1 tupled() {
      return BatchAllocationEvent$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return BatchAllocationEvent$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Time time() {
      return this.time;
   }

   public AllocatedBlocks allocatedBlocks() {
      return this.allocatedBlocks;
   }

   public BatchAllocationEvent copy(final Time time, final AllocatedBlocks allocatedBlocks) {
      return new BatchAllocationEvent(time, allocatedBlocks);
   }

   public Time copy$default$1() {
      return this.time();
   }

   public AllocatedBlocks copy$default$2() {
      return this.allocatedBlocks();
   }

   public String productPrefix() {
      return "BatchAllocationEvent";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.time();
         }
         case 1 -> {
            return this.allocatedBlocks();
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
      return x$1 instanceof BatchAllocationEvent;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "time";
         }
         case 1 -> {
            return "allocatedBlocks";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof BatchAllocationEvent) {
               label48: {
                  BatchAllocationEvent var4 = (BatchAllocationEvent)x$1;
                  Time var10000 = this.time();
                  Time var5 = var4.time();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  AllocatedBlocks var7 = this.allocatedBlocks();
                  AllocatedBlocks var6 = var4.allocatedBlocks();
                  if (var7 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var7.equals(var6)) {
                     break label48;
                  }

                  if (var4.canEqual(this)) {
                     break label55;
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public BatchAllocationEvent(final Time time, final AllocatedBlocks allocatedBlocks) {
      this.time = time;
      this.allocatedBlocks = allocatedBlocks;
      Product.$init$(this);
   }
}

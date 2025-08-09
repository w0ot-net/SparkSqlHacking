package org.apache.spark.resource;

import java.util.Optional;
import org.apache.spark.annotation.DeveloperApi;
import scala.collection.Seq;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005}3A\u0001D\u0007\u0001-!AQ\u0004\u0001BC\u0002\u0013\u0005a\u0004\u0003\u0005$\u0001\t\u0005\t\u0015!\u0003 \u0011!!\u0003A!b\u0001\n\u0003)\u0003\u0002C\u0015\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0014\t\u0011)\u0002!Q1A\u0005\u0002-B\u0001b\u0010\u0001\u0003\u0002\u0003\u0006I\u0001\f\u0005\t\u0001\u0002\u0011)\u0019!C\u0001W!A\u0011\t\u0001B\u0001B\u0003%A\u0006C\u0003C\u0001\u0011\u00051\tC\u0003J\u0001\u0011\u0005#\nC\u0003T\u0001\u0011\u0005CKA\bSKN|WO]2f%\u0016\fX/Z:u\u0015\tqq\"\u0001\u0005sKN|WO]2f\u0015\t\u0001\u0012#A\u0003ta\u0006\u00148N\u0003\u0002\u0013'\u00051\u0011\r]1dQ\u0016T\u0011\u0001F\u0001\u0004_J<7\u0001A\n\u0003\u0001]\u0001\"\u0001G\u000e\u000e\u0003eQ\u0011AG\u0001\u0006g\u000e\fG.Y\u0005\u00039e\u0011a!\u00118z%\u00164\u0017AA5e+\u0005y\u0002C\u0001\u0011\"\u001b\u0005i\u0011B\u0001\u0012\u000e\u0005)\u0011Vm]8ve\u000e,\u0017\nR\u0001\u0004S\u0012\u0004\u0013AB1n_VtG/F\u0001'!\tAr%\u0003\u0002)3\t!Aj\u001c8h\u0003\u001d\tWn\\;oi\u0002\nq\u0002Z5tG>4XM]=TGJL\u0007\u000f^\u000b\u0002YA\u0019QF\r\u001b\u000e\u00039R!a\f\u0019\u0002\tU$\u0018\u000e\u001c\u0006\u0002c\u0005!!.\u0019<b\u0013\t\u0019dF\u0001\u0005PaRLwN\\1m!\t)DH\u0004\u00027uA\u0011q'G\u0007\u0002q)\u0011\u0011(F\u0001\u0007yI|w\u000e\u001e \n\u0005mJ\u0012A\u0002)sK\u0012,g-\u0003\u0002>}\t11\u000b\u001e:j]\u001eT!aO\r\u0002!\u0011L7oY8wKJL8k\u0019:jaR\u0004\u0013A\u0002<f]\u0012|'/A\u0004wK:$wN\u001d\u0011\u0002\rqJg.\u001b;?)\u0015!UIR$I!\t\u0001\u0003\u0001C\u0003\u001e\u0013\u0001\u0007q\u0004C\u0003%\u0013\u0001\u0007a\u0005C\u0003+\u0013\u0001\u0007A\u0006C\u0003A\u0013\u0001\u0007A&\u0001\u0004fcV\fGn\u001d\u000b\u0003\u0017:\u0003\"\u0001\u0007'\n\u00055K\"a\u0002\"p_2,\u0017M\u001c\u0005\u0006\u001f*\u0001\r\u0001U\u0001\u0004_\nT\u0007C\u0001\rR\u0013\t\u0011\u0016DA\u0002B]f\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002+B\u0011\u0001DV\u0005\u0003/f\u00111!\u00138uQ\t\u0001\u0011\f\u0005\u0002[;6\t1L\u0003\u0002]\u001f\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005y[&\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0007"
)
public class ResourceRequest {
   private final ResourceID id;
   private final long amount;
   private final Optional discoveryScript;
   private final Optional vendor;

   public ResourceID id() {
      return this.id;
   }

   public long amount() {
      return this.amount;
   }

   public Optional discoveryScript() {
      return this.discoveryScript;
   }

   public Optional vendor() {
      return this.vendor;
   }

   public boolean equals(final Object obj) {
      if (!(obj instanceof ResourceRequest var4)) {
         return false;
      } else {
         boolean var12;
         label60: {
            label53: {
               Class var10000 = var4.getClass();
               Class var5 = this.getClass();
               if (var10000 == null) {
                  if (var5 != null) {
                     break label53;
                  }
               } else if (!var10000.equals(var5)) {
                  break label53;
               }

               ResourceID var9 = var4.id();
               ResourceID var6 = this.id();
               if (var9 == null) {
                  if (var6 != null) {
                     break label53;
                  }
               } else if (!var9.equals(var6)) {
                  break label53;
               }

               if (var4.amount() == this.amount()) {
                  label55: {
                     Optional var10 = var4.discoveryScript();
                     Optional var7 = this.discoveryScript();
                     if (var10 == null) {
                        if (var7 != null) {
                           break label55;
                        }
                     } else if (!var10.equals(var7)) {
                        break label55;
                     }

                     var10 = var4.vendor();
                     Optional var8 = this.vendor();
                     if (var10 == null) {
                        if (var8 == null) {
                           break label60;
                        }
                     } else if (var10.equals(var8)) {
                        break label60;
                     }
                  }
               }
            }

            var12 = false;
            return var12;
         }

         var12 = true;
         return var12;
      }
   }

   public int hashCode() {
      return ((Seq).MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.id(), BoxesRunTime.boxToLong(this.amount()), this.discoveryScript(), this.vendor()}))).hashCode();
   }

   public ResourceRequest(final ResourceID id, final long amount, final Optional discoveryScript, final Optional vendor) {
      this.id = id;
      this.amount = amount;
      this.discoveryScript = discoveryScript;
      this.vendor = vendor;
   }
}

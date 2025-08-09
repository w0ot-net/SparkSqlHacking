package org.apache.spark.resource;

import java.io.Serializable;
import org.apache.spark.annotation.Evolving;
import scala.collection.Seq;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ua\u0001B\n\u0015\u0001uA\u0001\u0002\r\u0001\u0003\u0006\u0004%\t!\r\u0005\tu\u0001\u0011\t\u0011)A\u0005e!A1\b\u0001BC\u0002\u0013\u0005A\b\u0003\u0005A\u0001\t\u0005\t\u0015!\u0003>\u0011!\t\u0005A!b\u0001\n\u0003\t\u0004\u0002\u0003\"\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001a\t\u0011\r\u0003!Q1A\u0005\u0002EB\u0001\u0002\u0012\u0001\u0003\u0002\u0003\u0006IA\r\u0005\u0006\u000b\u0002!\tA\u0012\u0005\u0006\u001b\u0002!\tE\u0014\u0005\u0006/\u0002!\t\u0005\u0017\u0005\u00069\u0002!\t%X\u0004\bWR\t\t\u0011#\u0001m\r\u001d\u0019B#!A\t\u00025DQ!\u0012\b\u0005\u0002UDqA\u001e\b\u0012\u0002\u0013\u0005q\u000f\u0003\u0005\u0002\u00049\t\n\u0011\"\u0001x\u0011%\t)ADA\u0001\n\u0013\t9AA\fFq\u0016\u001cW\u000f^8s%\u0016\u001cx.\u001e:dKJ+\u0017/^3ti*\u0011QCF\u0001\te\u0016\u001cx.\u001e:dK*\u0011q\u0003G\u0001\u0006gB\f'o\u001b\u0006\u00033i\ta!\u00199bG\",'\"A\u000e\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001qB\u0005\u0005\u0002 E5\t\u0001EC\u0001\"\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0003E\u0001\u0004B]f\u0014VM\u001a\t\u0003K5r!AJ\u0016\u000f\u0005\u001dRS\"\u0001\u0015\u000b\u0005%b\u0012A\u0002\u001fs_>$h(C\u0001\"\u0013\ta\u0003%A\u0004qC\u000e\\\u0017mZ3\n\u00059z#\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u0017!\u00031\u0011Xm]8ve\u000e,g*Y7f+\u0005\u0011\u0004CA\u001a8\u001d\t!T\u0007\u0005\u0002(A%\u0011a\u0007I\u0001\u0007!J,G-\u001a4\n\u0005aJ$AB*ue&twM\u0003\u00027A\u0005i!/Z:pkJ\u001cWMT1nK\u0002\na!Y7pk:$X#A\u001f\u0011\u0005}q\u0014BA !\u0005\u0011auN\\4\u0002\u000f\u0005lw.\u001e8uA\u0005yA-[:d_Z,'/_*de&\u0004H/\u0001\teSN\u001cwN^3ssN\u001b'/\u001b9uA\u00051a/\u001a8e_J\fqA^3oI>\u0014\b%\u0001\u0004=S:LGO\u0010\u000b\u0006\u000f&S5\n\u0014\t\u0003\u0011\u0002i\u0011\u0001\u0006\u0005\u0006a%\u0001\rA\r\u0005\u0006w%\u0001\r!\u0010\u0005\b\u0003&\u0001\n\u00111\u00013\u0011\u001d\u0019\u0015\u0002%AA\u0002I\na!Z9vC2\u001cHCA(S!\ty\u0002+\u0003\u0002RA\t9!i\\8mK\u0006t\u0007\"B*\u000b\u0001\u0004!\u0016aA8cUB\u0011q$V\u0005\u0003-\u0002\u00121!\u00118z\u0003!A\u0017m\u001d5D_\u0012,G#A-\u0011\u0005}Q\u0016BA.!\u0005\rIe\u000e^\u0001\ti>\u001cFO]5oOR\t!\u0007\u000b\u0002\u0001?B\u0011\u0001mY\u0007\u0002C*\u0011!MF\u0001\u000bC:tw\u000e^1uS>t\u0017B\u00013b\u0005!)eo\u001c7wS:<\u0007f\u0001\u0001gSB\u0011\u0001mZ\u0005\u0003Q\u0006\u0014QaU5oG\u0016\f\u0013A[\u0001\u0006g9\nd\u0006M\u0001\u0018\u000bb,7-\u001e;peJ+7o\\;sG\u0016\u0014V-];fgR\u0004\"\u0001\u0013\b\u0014\u00079qb\u000e\u0005\u0002pi6\t\u0001O\u0003\u0002re\u0006\u0011\u0011n\u001c\u0006\u0002g\u0006!!.\u0019<b\u0013\tq\u0003\u000fF\u0001m\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%gU\t\u0001P\u000b\u00023s.\n!\u0010\u0005\u0002|\u007f6\tAP\u0003\u0002~}\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003E\u0002J1!!\u0001}\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001b\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005%\u0001\u0003BA\u0006\u0003#i!!!\u0004\u000b\u0007\u0005=!/\u0001\u0003mC:<\u0017\u0002BA\n\u0003\u001b\u0011aa\u00142kK\u000e$\b"
)
public class ExecutorResourceRequest implements Serializable {
   private final String resourceName;
   private final long amount;
   private final String discoveryScript;
   private final String vendor;

   public static String $lessinit$greater$default$4() {
      return ExecutorResourceRequest$.MODULE$.$lessinit$greater$default$4();
   }

   public static String $lessinit$greater$default$3() {
      return ExecutorResourceRequest$.MODULE$.$lessinit$greater$default$3();
   }

   public String resourceName() {
      return this.resourceName;
   }

   public long amount() {
      return this.amount;
   }

   public String discoveryScript() {
      return this.discoveryScript;
   }

   public String vendor() {
      return this.vendor;
   }

   public boolean equals(final Object obj) {
      if (!(obj instanceof ExecutorResourceRequest var4)) {
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

               String var9 = var4.resourceName();
               String var6 = this.resourceName();
               if (var9 == null) {
                  if (var6 != null) {
                     break label53;
                  }
               } else if (!var9.equals(var6)) {
                  break label53;
               }

               if (var4.amount() == this.amount()) {
                  label55: {
                     var9 = var4.discoveryScript();
                     String var7 = this.discoveryScript();
                     if (var9 == null) {
                        if (var7 != null) {
                           break label55;
                        }
                     } else if (!var9.equals(var7)) {
                        break label55;
                     }

                     var9 = var4.vendor();
                     String var8 = this.vendor();
                     if (var9 == null) {
                        if (var8 == null) {
                           break label60;
                        }
                     } else if (var9.equals(var8)) {
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
      return ((Seq).MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.resourceName(), BoxesRunTime.boxToLong(this.amount()), this.discoveryScript(), this.vendor()}))).hashCode();
   }

   public String toString() {
      String var10000 = this.resourceName();
      return "name: " + var10000 + ", amount: " + this.amount() + ", script: " + this.discoveryScript() + ", vendor: " + this.vendor();
   }

   public ExecutorResourceRequest(final String resourceName, final long amount, final String discoveryScript, final String vendor) {
      this.resourceName = resourceName;
      this.amount = amount;
      this.discoveryScript = discoveryScript;
      this.vendor = vendor;
   }
}

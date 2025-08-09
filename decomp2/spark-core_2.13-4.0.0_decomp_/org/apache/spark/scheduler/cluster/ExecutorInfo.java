package org.apache.spark.scheduler.cluster;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.resource.ResourceProfile$;
import scala.Option;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction2;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005=b\u0001B\r\u001b\u0001\u0015B\u0001\u0002\f\u0001\u0003\u0006\u0004%\t!\f\u0005\ts\u0001\u0011\t\u0011)A\u0005]!A!\b\u0001BC\u0002\u0013\u00051\b\u0003\u0005@\u0001\t\u0005\t\u0015!\u0003=\u0011!\u0001\u0005A!b\u0001\n\u0003\t\u0005\u0002C#\u0001\u0005\u0003\u0005\u000b\u0011\u0002\"\t\u0011\u0019\u0003!Q1A\u0005\u0002\u0005C\u0001b\u0012\u0001\u0003\u0002\u0003\u0006IA\u0011\u0005\t\u0011\u0002\u0011)\u0019!C\u0001\u0013\"A\u0011\u000b\u0001B\u0001B\u0003%!\n\u0003\u0005S\u0001\t\u0015\r\u0011\"\u0001<\u0011!\u0019\u0006A!A!\u0002\u0013a\u0004\u0002\u0003+\u0001\u0005\u000b\u0007I\u0011A+\t\u0011q\u0003!\u0011!Q\u0001\nYC\u0001\"\u0018\u0001\u0003\u0006\u0004%\t!\u0016\u0005\t=\u0002\u0011\t\u0011)A\u0005-\")q\f\u0001C\u0001A\")q\f\u0001C\u0001W\")q\f\u0001C\u0001e\")q\f\u0001C\u0001m\")q\f\u0001C\u0001w\"9\u00111\u0001\u0001\u0005\u0002\u0005\u0015\u0001bBA\f\u0001\u0011\u0005\u0013\u0011\u0004\u0005\b\u0003;\u0001A\u0011IA\u0010\u00051)\u00050Z2vi>\u0014\u0018J\u001c4p\u0015\tYB$A\u0004dYV\u001cH/\u001a:\u000b\u0005uq\u0012!C:dQ\u0016$W\u000f\\3s\u0015\ty\u0002%A\u0003ta\u0006\u00148N\u0003\u0002\"E\u00051\u0011\r]1dQ\u0016T\u0011aI\u0001\u0004_J<7\u0001A\n\u0003\u0001\u0019\u0002\"a\n\u0016\u000e\u0003!R\u0011!K\u0001\u0006g\u000e\fG.Y\u0005\u0003W!\u0012a!\u00118z%\u00164\u0017\u0001D3yK\u000e,Ho\u001c:I_N$X#\u0001\u0018\u0011\u0005=2dB\u0001\u00195!\t\t\u0004&D\u00013\u0015\t\u0019D%\u0001\u0004=e>|GOP\u0005\u0003k!\na\u0001\u0015:fI\u00164\u0017BA\u001c9\u0005\u0019\u0019FO]5oO*\u0011Q\u0007K\u0001\u000eKb,7-\u001e;pe\"{7\u000f\u001e\u0011\u0002\u0015Q|G/\u00197D_J,7/F\u0001=!\t9S(\u0003\u0002?Q\t\u0019\u0011J\u001c;\u0002\u0017Q|G/\u00197D_J,7\u000fI\u0001\nY><WK\u001d7NCB,\u0012A\u0011\t\u0005_\rsc&\u0003\u0002Eq\t\u0019Q*\u00199\u0002\u00151|w-\u0016:m\u001b\u0006\u0004\b%\u0001\u0006biR\u0014\u0018NY;uKN\f1\"\u0019;ue&\u0014W\u000f^3tA\u0005i!/Z:pkJ\u001cWm]%oM>,\u0012A\u0013\t\u0005_\rs3\n\u0005\u0002M\u001f6\tQJ\u0003\u0002O=\u0005A!/Z:pkJ\u001cW-\u0003\u0002Q\u001b\n\u0019\"+Z:pkJ\u001cW-\u00138g_Jl\u0017\r^5p]\u0006q!/Z:pkJ\u001cWm]%oM>\u0004\u0013!\u0005:fg>,(oY3Qe>4\u0017\u000e\\3JI\u0006\u0011\"/Z:pkJ\u001cW\r\u0015:pM&dW-\u00133!\u0003A\u0011XmZ5tiJ\fG/[8o)&lW-F\u0001W!\r9s+W\u0005\u00031\"\u0012aa\u00149uS>t\u0007CA\u0014[\u0013\tY\u0006F\u0001\u0003M_:<\u0017!\u0005:fO&\u001cHO]1uS>tG+[7fA\u0005Y!/Z9vKN$H+[7f\u00031\u0011X-];fgR$\u0016.\\3!\u0003\u0019a\u0014N\\5u}QI\u0011m\u00193fM\u001eD\u0017N\u001b\t\u0003E\u0002i\u0011A\u0007\u0005\u0006YE\u0001\rA\f\u0005\u0006uE\u0001\r\u0001\u0010\u0005\u0006\u0001F\u0001\rA\u0011\u0005\u0006\rF\u0001\rA\u0011\u0005\u0006\u0011F\u0001\rA\u0013\u0005\u0006%F\u0001\r\u0001\u0010\u0005\u0006)F\u0001\rA\u0016\u0005\u0006;F\u0001\rA\u0016\u000b\bC2lgn\u001c9r\u0011\u0015a#\u00031\u0001/\u0011\u0015Q$\u00031\u0001=\u0011\u0015\u0001%\u00031\u0001C\u0011\u00151%\u00031\u0001C\u0011\u0015A%\u00031\u0001K\u0011\u0015\u0011&\u00031\u0001=)\u0011\t7\u000f^;\t\u000b1\u001a\u0002\u0019\u0001\u0018\t\u000bi\u001a\u0002\u0019\u0001\u001f\t\u000b\u0001\u001b\u0002\u0019\u0001\"\u0015\u000b\u0005<\b0\u001f>\t\u000b1\"\u0002\u0019\u0001\u0018\t\u000bi\"\u0002\u0019\u0001\u001f\t\u000b\u0001#\u0002\u0019\u0001\"\t\u000b\u0019#\u0002\u0019\u0001\"\u0015\u000f\u0005dXP`@\u0002\u0002!)A&\u0006a\u0001]!)!(\u0006a\u0001y!)\u0001)\u0006a\u0001\u0005\")a)\u0006a\u0001\u0005\")\u0001*\u0006a\u0001\u0015\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\b\u00055\u0001cA\u0014\u0002\n%\u0019\u00111\u0002\u0015\u0003\u000f\t{w\u000e\\3b]\"9\u0011q\u0002\fA\u0002\u0005E\u0011!B8uQ\u0016\u0014\bcA\u0014\u0002\u0014%\u0019\u0011Q\u0003\u0015\u0003\u0007\u0005s\u00170\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003\u000f\tY\u0002C\u0004\u0002\u0010]\u0001\r!!\u0005\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012\u0001\u0010\u0015\u0004\u0001\u0005\r\u0002\u0003BA\u0013\u0003Wi!!a\n\u000b\u0007\u0005%b$\u0001\u0006b]:|G/\u0019;j_:LA!!\f\u0002(\taA)\u001a<fY>\u0004XM]!qS\u0002"
)
public class ExecutorInfo {
   private final String executorHost;
   private final int totalCores;
   private final Map logUrlMap;
   private final Map attributes;
   private final Map resourcesInfo;
   private final int resourceProfileId;
   private final Option registrationTime;
   private final Option requestTime;

   public String executorHost() {
      return this.executorHost;
   }

   public int totalCores() {
      return this.totalCores;
   }

   public Map logUrlMap() {
      return this.logUrlMap;
   }

   public Map attributes() {
      return this.attributes;
   }

   public Map resourcesInfo() {
      return this.resourcesInfo;
   }

   public int resourceProfileId() {
      return this.resourceProfileId;
   }

   public Option registrationTime() {
      return this.registrationTime;
   }

   public Option requestTime() {
      return this.requestTime;
   }

   public boolean canEqual(final Object other) {
      return other instanceof ExecutorInfo;
   }

   public boolean equals(final Object other) {
      if (!(other instanceof ExecutorInfo var4)) {
         return false;
      } else {
         boolean var12;
         if (var4.canEqual(this)) {
            label52: {
               String var10000 = this.executorHost();
               String var5 = var4.executorHost();
               if (var10000 == null) {
                  if (var5 != null) {
                     break label52;
                  }
               } else if (!var10000.equals(var5)) {
                  break label52;
               }

               if (this.totalCores() == var4.totalCores()) {
                  label58: {
                     Map var9 = this.logUrlMap();
                     Map var6 = var4.logUrlMap();
                     if (var9 == null) {
                        if (var6 != null) {
                           break label58;
                        }
                     } else if (!var9.equals(var6)) {
                        break label58;
                     }

                     var9 = this.attributes();
                     Map var7 = var4.attributes();
                     if (var9 == null) {
                        if (var7 != null) {
                           break label58;
                        }
                     } else if (!var9.equals(var7)) {
                        break label58;
                     }

                     var9 = this.resourcesInfo();
                     Map var8 = var4.resourcesInfo();
                     if (var9 == null) {
                        if (var8 != null) {
                           break label58;
                        }
                     } else if (!var9.equals(var8)) {
                        break label58;
                     }

                     if (this.resourceProfileId() == var4.resourceProfileId()) {
                        var12 = true;
                        return var12;
                     }
                  }
               }
            }
         }

         var12 = false;
         return var12;
      }
   }

   public int hashCode() {
      Seq state = (Seq).MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.executorHost(), BoxesRunTime.boxToInteger(this.totalCores()), this.logUrlMap(), this.attributes(), this.resourcesInfo(), BoxesRunTime.boxToInteger(this.resourceProfileId())}));
      return BoxesRunTime.unboxToInt(((IterableOnceOps)((IterableOps)state.filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$hashCode$1(x$1)))).map((x$2) -> BoxesRunTime.boxToInteger($anonfun$hashCode$2(x$2)))).foldLeft(BoxesRunTime.boxToInteger(0), (JFunction2.mcIII.sp)(a, b) -> 31 * a + b));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$hashCode$1(final Object x$1) {
      return x$1 != null;
   }

   // $FF: synthetic method
   public static final int $anonfun$hashCode$2(final Object x$2) {
      return x$2.hashCode();
   }

   public ExecutorInfo(final String executorHost, final int totalCores, final Map logUrlMap, final Map attributes, final Map resourcesInfo, final int resourceProfileId, final Option registrationTime, final Option requestTime) {
      this.executorHost = executorHost;
      this.totalCores = totalCores;
      this.logUrlMap = logUrlMap;
      this.attributes = attributes;
      this.resourcesInfo = resourcesInfo;
      this.resourceProfileId = resourceProfileId;
      this.registrationTime = registrationTime;
      this.requestTime = requestTime;
   }

   public ExecutorInfo(final String executorHost, final int totalCores, final Map logUrlMap, final Map attributes, final Map resourcesInfo, final int resourceProfileId) {
      this(executorHost, totalCores, logUrlMap, attributes, resourcesInfo, resourceProfileId, scala.None..MODULE$, scala.None..MODULE$);
   }

   public ExecutorInfo(final String executorHost, final int totalCores, final Map logUrlMap) {
      this(executorHost, totalCores, logUrlMap, scala.Predef..MODULE$.Map().empty(), scala.Predef..MODULE$.Map().empty(), ResourceProfile$.MODULE$.DEFAULT_RESOURCE_PROFILE_ID(), scala.None..MODULE$, scala.None..MODULE$);
   }

   public ExecutorInfo(final String executorHost, final int totalCores, final Map logUrlMap, final Map attributes) {
      this(executorHost, totalCores, logUrlMap, attributes, scala.Predef..MODULE$.Map().empty(), ResourceProfile$.MODULE$.DEFAULT_RESOURCE_PROFILE_ID(), scala.None..MODULE$, scala.None..MODULE$);
   }

   public ExecutorInfo(final String executorHost, final int totalCores, final Map logUrlMap, final Map attributes, final Map resourcesInfo) {
      this(executorHost, totalCores, logUrlMap, attributes, resourcesInfo, ResourceProfile$.MODULE$.DEFAULT_RESOURCE_PROFILE_ID(), scala.None..MODULE$, scala.None..MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

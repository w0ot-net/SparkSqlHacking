package org.apache.spark.deploy;

import java.io.Serializable;
import scala.Enumeration;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005i3Qa\u0004\t\u0001!aA\u0001\u0002\f\u0001\u0003\u0006\u0004%\t!\f\u0005\tm\u0001\u0011\t\u0011)A\u0005]!Aq\u0007\u0001BC\u0002\u0013\u0005\u0001\b\u0003\u0005=\u0001\t\u0005\t\u0015!\u0003:\u0011!i\u0004A!b\u0001\n\u0003A\u0004\u0002\u0003 \u0001\u0005\u0003\u0005\u000b\u0011B\u001d\t\u0011}\u0002!Q1A\u0005\u0002aB\u0001\u0002\u0011\u0001\u0003\u0002\u0003\u0006I!\u000f\u0005\t\u0003\u0002\u0011)\u0019!C\u0001q!A!\t\u0001B\u0001B\u0003%\u0011\b\u0003\u0005D\u0001\t\u0015\r\u0011\"\u0001E\u0011!q\u0005A!A!\u0002\u0013)\u0005\"B(\u0001\t\u0003\u0001\u0006\"\u0002-\u0001\t\u0003J&aE#yK\u000e,Ho\u001c:EKN\u001c'/\u001b9uS>t'BA\t\u0013\u0003\u0019!W\r\u001d7ps*\u00111\u0003F\u0001\u0006gB\f'o\u001b\u0006\u0003+Y\ta!\u00199bG\",'\"A\f\u0002\u0007=\u0014xmE\u0002\u00013}\u0001\"AG\u000f\u000e\u0003mQ\u0011\u0001H\u0001\u0006g\u000e\fG.Y\u0005\u0003=m\u0011a!\u00118z%\u00164\u0007C\u0001\u0011*\u001d\t\tsE\u0004\u0002#M5\t1E\u0003\u0002%K\u00051AH]8piz\u001a\u0001!C\u0001\u001d\u0013\tA3$A\u0004qC\u000e\\\u0017mZ3\n\u0005)Z#\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u0015\u001c\u0003\u0015\t\u0007\u000f]%e+\u0005q\u0003CA\u00184\u001d\t\u0001\u0014\u0007\u0005\u0002#7%\u0011!gG\u0001\u0007!J,G-\u001a4\n\u0005Q*$AB*ue&twM\u0003\u000237\u00051\u0011\r\u001d9JI\u0002\na!\u001a=fG&#W#A\u001d\u0011\u0005iQ\u0014BA\u001e\u001c\u0005\rIe\u000e^\u0001\bKb,7-\u00133!\u0003\u0011\u0011\b/\u00133\u0002\u000bI\u0004\u0018\n\u001a\u0011\u0002\u000b\r|'/Z:\u0002\r\r|'/Z:!\u0003!iW-\\8ss6\u0013\u0017!C7f[>\u0014\u00180\u00142!\u0003\u0015\u0019H/\u0019;f+\u0005)\u0005C\u0001$K\u001d\t9\u0005*D\u0001\u0011\u0013\tI\u0005#A\u0007Fq\u0016\u001cW\u000f^8s'R\fG/Z\u0005\u0003\u00172\u0013QAV1mk\u0016L!!T\u000e\u0003\u0017\u0015sW/\\3sCRLwN\\\u0001\u0007gR\fG/\u001a\u0011\u0002\rqJg.\u001b;?)\u001d\t&k\u0015+V-^\u0003\"a\u0012\u0001\t\u000b1j\u0001\u0019\u0001\u0018\t\u000b]j\u0001\u0019A\u001d\t\u000buj\u0001\u0019A\u001d\t\u000b}j\u0001\u0019A\u001d\t\u000b\u0005k\u0001\u0019A\u001d\t\u000b\rk\u0001\u0019A#\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012A\f"
)
public class ExecutorDescription implements Serializable {
   private final String appId;
   private final int execId;
   private final int rpId;
   private final int cores;
   private final int memoryMb;
   private final Enumeration.Value state;

   public String appId() {
      return this.appId;
   }

   public int execId() {
      return this.execId;
   }

   public int rpId() {
      return this.rpId;
   }

   public int cores() {
      return this.cores;
   }

   public int memoryMb() {
      return this.memoryMb;
   }

   public Enumeration.Value state() {
      return this.state;
   }

   public String toString() {
      return .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("ExecutorState(appId=%s, execId=%d, rpId=%d, cores=%d, memoryMb=%d state=%s)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.appId(), BoxesRunTime.boxToInteger(this.execId()), BoxesRunTime.boxToInteger(this.rpId()), BoxesRunTime.boxToInteger(this.cores()), BoxesRunTime.boxToInteger(this.memoryMb()), this.state()}));
   }

   public ExecutorDescription(final String appId, final int execId, final int rpId, final int cores, final int memoryMb, final Enumeration.Value state) {
      this.appId = appId;
      this.execId = execId;
      this.rpId = rpId;
      this.cores = cores;
      this.memoryMb = memoryMb;
      this.state = state;
   }
}

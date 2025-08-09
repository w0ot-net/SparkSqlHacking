package org.apache.spark.status;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.lang.invoke.SerializedLambda;
import java.util.Date;
import org.apache.spark.status.api.v1.StageData;
import org.apache.spark.status.api.v1.StageStatus;
import org.apache.spark.util.kvstore.KVIndex;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ua!\u0002\u0007\u000e\u0001=)\u0002\u0002\u0003\u000f\u0001\u0005\u000b\u0007I\u0011\u0001\u0010\t\u0011\u001d\u0002!\u0011!Q\u0001\n}A\u0001\u0002\u000b\u0001\u0003\u0006\u0004%\t!\u000b\u0005\tq\u0001\u0011\t\u0011)A\u0005U!A\u0011\b\u0001BC\u0002\u0013\u0005!\b\u0003\u0005E\u0001\t\u0005\t\u0015!\u0003<\u0011\u0015)\u0005\u0001\"\u0001G\u0011\u0019!\u0007\u0001)A\u0005K\")q\u000f\u0001C\u0005q\")Q\u0010\u0001C\u0005}\"9\u00111\u0002\u0001\u0005\u0002\u00055!\u0001E*uC\u001e,G)\u0019;b/J\f\u0007\u000f]3s\u0015\tqq\"\u0001\u0004ti\u0006$Xo\u001d\u0006\u0003!E\tQa\u001d9be.T!AE\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005!\u0012aA8sON\u0011\u0001A\u0006\t\u0003/ii\u0011\u0001\u0007\u0006\u00023\u0005)1oY1mC&\u00111\u0004\u0007\u0002\u0007\u0003:L(+\u001a4\u0002\t%tgm\\\u0002\u0001+\u0005y\u0002C\u0001\u0011&\u001b\u0005\t#B\u0001\u0012$\u0003\t1\u0018G\u0003\u0002%\u001b\u0005\u0019\u0011\r]5\n\u0005\u0019\n#!C*uC\u001e,G)\u0019;b\u0003\u0015IgNZ8!\u0003\u0019QwNY%egV\t!\u0006E\u0002,eUr!\u0001\f\u0019\u0011\u00055BR\"\u0001\u0018\u000b\u0005=j\u0012A\u0002\u001fs_>$h(\u0003\u000221\u00051\u0001K]3eK\u001aL!a\r\u001b\u0003\u0007M+GO\u0003\u000221A\u0011qCN\u0005\u0003oa\u00111!\u00138u\u0003\u001dQwNY%eg\u0002\n\u0001\u0002\\8dC2LG/_\u000b\u0002wA!1\u0006\u0010 B\u0013\tiDGA\u0002NCB\u0004\"aK \n\u0005\u0001#$AB*ue&tw\r\u0005\u0002\u0018\u0005&\u00111\t\u0007\u0002\u0005\u0019>tw-A\u0005m_\u000e\fG.\u001b;zA\u00051A(\u001b8jiz\"BaR%K\u0017B\u0011\u0001\nA\u0007\u0002\u001b!)Ad\u0002a\u0001?!)\u0001f\u0002a\u0001U!)\u0011h\u0002a\u0001w!\"1*T.]!\tq\u0015,D\u0001P\u0015\t\u0001\u0016+\u0001\u0006b]:|G/\u0019;j_:T!AU*\u0002\u0011\u0011\fG/\u00192j]\u0012T!\u0001V+\u0002\u000f)\f7m[:p]*\u0011akV\u0001\nM\u0006\u001cH/\u001a:y[2T\u0011\u0001W\u0001\u0004G>l\u0017B\u0001.P\u0005=Q5o\u001c8EKN,'/[1mSj,\u0017!C2p]R,g\u000e^!tG\u0005i\u0006C\u00010d\u001b\u0005y&B\u00011b\u0003\u0011a\u0017M\\4\u000b\u0003\t\fAA[1wC&\u00111iX\u0001\u0003S\u0012\u00042a\u000646\u0013\t9\u0007DA\u0003BeJ\f\u0017\u0010\u000b\u0002\tSB\u0011!\u000e\\\u0007\u0002W*\u0011\u0001kU\u0005\u0003[.\u0014!BS:p]&;gn\u001c:fQ\tAq\u000e\u0005\u0002qk6\t\u0011O\u0003\u0002sg\u000691N^:u_J,'B\u0001;\u0010\u0003\u0011)H/\u001b7\n\u0005Y\f(aB&W\u0013:$W\r_\u0001\bgR\fw-Z%e+\u0005)\u0004FA\u0005jQ\u0011Iqn\u001f?\u0002\u000bY\fG.^3\"\u0003]\fa!Y2uSZ,W#A@\u0011\u0007]\t\t!C\u0002\u0002\u0004a\u0011qAQ8pY\u0016\fg\u000e\u000b\u0002\u000bS\"*!b\\>\u0002\n\u0005\nQ0\u0001\bd_6\u0004H.\u001a;j_:$\u0016.\\3\u0016\u0003\u0005C#aC5)\u000b-y70a\u0005\"\u0005\u0005-\u0001"
)
public class StageDataWrapper {
   private final StageData info;
   private final Set jobIds;
   private final Map locality;
   @JsonIgnore
   @KVIndex
   private final int[] id;

   public StageData info() {
      return this.info;
   }

   public Set jobIds() {
      return this.jobIds;
   }

   public Map locality() {
      return this.locality;
   }

   @JsonIgnore
   @KVIndex("stageId")
   private int stageId() {
      return this.info().stageId();
   }

   @JsonIgnore
   @KVIndex("active")
   private boolean active() {
      boolean var2;
      label23: {
         StageStatus var10000 = this.info().status();
         StageStatus var1 = StageStatus.ACTIVE;
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   @JsonIgnore
   @KVIndex("completionTime")
   public long completionTime() {
      return BoxesRunTime.unboxToLong(this.info().completionTime().map((x$2) -> BoxesRunTime.boxToLong($anonfun$completionTime$3(x$2))).getOrElse((JFunction0.mcJ.sp)() -> -1L));
   }

   // $FF: synthetic method
   public static final long $anonfun$completionTime$3(final Date x$2) {
      return x$2.getTime();
   }

   public StageDataWrapper(final StageData info, final Set jobIds, @JsonDeserialize(contentAs = Long.class) final Map locality) {
      this.info = info;
      this.jobIds = jobIds;
      this.locality = locality;
      this.id = new int[]{info.stageId(), info.attemptId()};
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

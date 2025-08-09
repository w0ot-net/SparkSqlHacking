package org.apache.spark.status.protobuf;

import java.lang.invoke.SerializedLambda;
import java.util.Date;
import org.apache.spark.status.ProcessSummaryWrapper;
import org.apache.spark.status.api.v1.ProcessSummary;
import scala.MatchError;
import scala.Option;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3QAB\u0004\u0001\u000fEAQ\u0001\t\u0001\u0005\u0002\tBQ\u0001\n\u0001\u0005B\u0015BQA\f\u0001\u0005\u0002=BQA\r\u0001\u0005\nMBQ\u0001\u0012\u0001\u0005\n\u0015\u0013q\u0004\u0015:pG\u0016\u001c8oU;n[\u0006\u0014\u0018p\u0016:baB,'oU3sS\u0006d\u0017N_3s\u0015\tA\u0011\"\u0001\u0005qe>$xNY;g\u0015\tQ1\"\u0001\u0004ti\u0006$Xo\u001d\u0006\u0003\u00195\tQa\u001d9be.T!AD\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0012aA8sON\u0019\u0001A\u0005\r\u0011\u0005M1R\"\u0001\u000b\u000b\u0003U\tQa]2bY\u0006L!a\u0006\u000b\u0003\r\u0005s\u0017PU3g!\rI\"\u0004H\u0007\u0002\u000f%\u00111d\u0002\u0002\u000e!J|Go\u001c2vMN+'\u000fR3\u0011\u0005uqR\"A\u0005\n\u0005}I!!\u0006)s_\u000e,7o]*v[6\f'/_,sCB\u0004XM]\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\t1\u0005\u0005\u0002\u001a\u0001\u0005I1/\u001a:jC2L'0\u001a\u000b\u0003M1\u00022aE\u0014*\u0013\tACCA\u0003BeJ\f\u0017\u0010\u0005\u0002\u0014U%\u00111\u0006\u0006\u0002\u0005\u0005f$X\rC\u0003.\u0005\u0001\u0007A$A\u0003j]B,H/A\u0006eKN,'/[1mSj,GC\u0001\u000f1\u0011\u0015\t4\u00011\u0001'\u0003\u0015\u0011\u0017\u0010^3t\u0003]\u0019XM]5bY&TX\r\u0015:pG\u0016\u001c8oU;n[\u0006\u0014\u0018\u0010\u0006\u00025wA\u0011Q\u0007\u000f\b\u00033YJ!aN\u0004\u0002\u0015M#xN]3UsB,7/\u0003\u0002:u\tq\u0001K]8dKN\u001c8+^7nCJL(BA\u001c\b\u0011\u0015aD\u00011\u0001>\u0003\u0011IgNZ8\u0011\u0005y\u001aU\"A \u000b\u0005\u0001\u000b\u0015A\u0001<2\u0015\t\u0011\u0015\"A\u0002ba&L!!O \u00023\u0011,7/\u001a:jC2L'0\u001a)s_\u000e,7o]*v[6\f'/\u001f\u000b\u0003{\u0019CQ\u0001P\u0003A\u0002Q\u0002"
)
public class ProcessSummaryWrapperSerializer implements ProtobufSerDe {
   public byte[] serialize(final ProcessSummaryWrapper input) {
      StoreTypes.ProcessSummaryWrapper.Builder builder = StoreTypes.ProcessSummaryWrapper.newBuilder();
      builder.setInfo(this.serializeProcessSummary(input.info()));
      return builder.build().toByteArray();
   }

   public ProcessSummaryWrapper deserialize(final byte[] bytes) {
      StoreTypes.ProcessSummaryWrapper wrapper = StoreTypes.ProcessSummaryWrapper.parseFrom(bytes);
      return new ProcessSummaryWrapper(this.deserializeProcessSummary(wrapper.getInfo()));
   }

   private StoreTypes.ProcessSummary serializeProcessSummary(final ProcessSummary info) {
      StoreTypes.ProcessSummary.Builder builder = StoreTypes.ProcessSummary.newBuilder();
      Utils$.MODULE$.setStringField(info.id(), (value) -> builder.setId(value));
      Utils$.MODULE$.setStringField(info.hostPort(), (value) -> builder.setHostPort(value));
      builder.setIsActive(info.isActive());
      builder.setTotalCores(info.totalCores());
      builder.setAddTime(info.addTime().getTime());
      info.removeTime().foreach((d) -> builder.setRemoveTime(d.getTime()));
      info.processLogs().foreach((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            String v = (String)x0$1._2();
            return builder.putProcessLogs(k, v);
         } else {
            throw new MatchError(x0$1);
         }
      });
      return builder.build();
   }

   private ProcessSummary deserializeProcessSummary(final StoreTypes.ProcessSummary info) {
      Option removeTime = Utils$.MODULE$.getOptional(info.hasRemoveTime(), () -> new Date(info.getRemoveTime()));
      return new ProcessSummary(Utils$.MODULE$.getStringField(info.hasId(), () -> info.getId()), Utils$.MODULE$.getStringField(info.hasHostPort(), () -> info.getHostPort()), info.getIsActive(), info.getTotalCores(), new Date(info.getAddTime()), removeTime, .MODULE$.MapHasAsScala(info.getProcessLogsMap()).asScala().toMap(scala..less.colon.less..MODULE$.refl()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

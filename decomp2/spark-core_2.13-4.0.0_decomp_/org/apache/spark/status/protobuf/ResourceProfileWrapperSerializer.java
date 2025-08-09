package org.apache.spark.status.protobuf;

import org.apache.spark.status.ResourceProfileWrapper;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a2QAB\u0004\u0001\u000fEAQ\u0001\t\u0001\u0005\u0002\tBq\u0001\n\u0001C\u0002\u0013%Q\u0005\u0003\u0004*\u0001\u0001\u0006IA\n\u0005\u0006U\u0001!\te\u000b\u0005\u0006i\u0001!\t!\u000e\u0002!%\u0016\u001cx.\u001e:dKB\u0013xNZ5mK^\u0013\u0018\r\u001d9feN+'/[1mSj,'O\u0003\u0002\t\u0013\u0005A\u0001O]8u_\n,hM\u0003\u0002\u000b\u0017\u000511\u000f^1ukNT!\u0001D\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00059y\u0011AB1qC\u000eDWMC\u0001\u0011\u0003\ry'oZ\n\u0004\u0001IA\u0002CA\n\u0017\u001b\u0005!\"\"A\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005]!\"AB!osJ+g\rE\u0002\u001a5qi\u0011aB\u0005\u00037\u001d\u0011Q\u0002\u0015:pi>\u0014WOZ*fe\u0012+\u0007CA\u000f\u001f\u001b\u0005I\u0011BA\u0010\n\u0005Y\u0011Vm]8ve\u000e,\u0007K]8gS2,wK]1qa\u0016\u0014\u0018A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003\r\u0002\"!\u0007\u0001\u0002!\u0005\u0004\b/\u00128w'\u0016\u0014\u0018.\u00197ju\u0016\u0014X#\u0001\u0014\u0011\u0005e9\u0013B\u0001\u0015\b\u0005-\n\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8F]ZL'o\u001c8nK:$\u0018J\u001c4p/J\f\u0007\u000f]3s'\u0016\u0014\u0018.\u00197ju\u0016\u0014\u0018!E1qa\u0016sgoU3sS\u0006d\u0017N_3sA\u0005I1/\u001a:jC2L'0\u001a\u000b\u0003YI\u00022aE\u00170\u0013\tqCCA\u0003BeJ\f\u0017\u0010\u0005\u0002\u0014a%\u0011\u0011\u0007\u0006\u0002\u0005\u0005f$X\rC\u00034\t\u0001\u0007A$A\u0003j]B,H/A\u0006eKN,'/[1mSj,GC\u0001\u000f7\u0011\u00159T\u00011\u0001-\u0003\u0015\u0011\u0017\u0010^3t\u0001"
)
public class ResourceProfileWrapperSerializer implements ProtobufSerDe {
   private final ApplicationEnvironmentInfoWrapperSerializer appEnvSerializer = new ApplicationEnvironmentInfoWrapperSerializer();

   private ApplicationEnvironmentInfoWrapperSerializer appEnvSerializer() {
      return this.appEnvSerializer;
   }

   public byte[] serialize(final ResourceProfileWrapper input) {
      StoreTypes.ResourceProfileWrapper.Builder builder = StoreTypes.ResourceProfileWrapper.newBuilder();
      builder.setRpInfo(this.appEnvSerializer().serializeResourceProfileInfo(input.rpInfo()));
      return builder.build().toByteArray();
   }

   public ResourceProfileWrapper deserialize(final byte[] bytes) {
      StoreTypes.ResourceProfileWrapper wrapper = StoreTypes.ResourceProfileWrapper.parseFrom(bytes);
      return new ResourceProfileWrapper(this.appEnvSerializer().deserializeResourceProfileInfo(wrapper.getRpInfo()));
   }
}

package org.apache.spark.scheduler;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import org.apache.spark.annotation.DeveloperApi;
import scala.reflect.ScalaSignature;

@DeveloperApi
@JsonTypeInfo(
   use = Id.CLASS,
   include = As.PROPERTY,
   property = "Event"
)
@ScalaSignature(
   bytes = "\u0006\u0005\t3qa\u0001\u0003\u0011\u0002\u0007\u0005Q\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003\u0003\u0004\u001a\u0001\u0011EaA\u0007\u0002\u0013'B\f'o\u001b'jgR,g.\u001a:Fm\u0016tGO\u0003\u0002\u0006\r\u0005I1o\u00195fIVdWM\u001d\u0006\u0003\u000f!\tQa\u001d9be.T!!\u0003\u0006\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005Y\u0011aA8sO\u000e\u00011C\u0001\u0001\u000f!\ty!#D\u0001\u0011\u0015\u0005\t\u0012!B:dC2\f\u0017BA\n\u0011\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012A\u0006\t\u0003\u001f]I!\u0001\u0007\t\u0003\tUs\u0017\u000e^\u0001\tY><WI^3oiV\t1\u0004\u0005\u0002\u00109%\u0011Q\u0004\u0005\u0002\b\u0005>|G.Z1oQ\t\u0001q\u0004\u0005\u0002!G5\t\u0011E\u0003\u0002#\r\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\u0011\n#\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0007\u0006\u0003\u0001'cIJ$h\u0010!\u0011\u0005\u001dzS\"\u0001\u0015\u000b\u0005\tJ#B\u0001\u0016,\u0003\u001dQ\u0017mY6t_:T!\u0001L\u0017\u0002\u0013\u0019\f7\u000f^3sq6d'\"\u0001\u0018\u0002\u0007\r|W.\u0003\u00021Q\ta!j]8o)f\u0004X-\u00138g_\u0006\u0019Qo]3%\u0003MJ!\u0001N\u001b\u0002\u000b\rc\u0015iU*\u000b\u0005Y:\u0014AA%e\u0015\tA\u0004&\u0001\u0007Kg>tG+\u001f9f\u0013:4w.A\u0004j]\u000edW\u000fZ3%\u0003mJ!\u0001P\u001f\u0002\u0011A\u0013v\nU#S)fS!AP\u001c\u0002\u0005\u0005\u001b\u0018\u0001\u00039s_B,'\u000f^=\"\u0003\u0005\u000bQ!\u0012<f]R\u0004"
)
public interface SparkListenerEvent {
   // $FF: synthetic method
   static boolean logEvent$(final SparkListenerEvent $this) {
      return $this.logEvent();
   }

   default boolean logEvent() {
      return true;
   }

   static void $init$(final SparkListenerEvent $this) {
   }
}

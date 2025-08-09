package org.apache.spark.scheduler;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-3\u0001b\u0003\u0007\u0011\u0002G\u0005b\u0002\u0006\u0005\u00067\u00011\t!H\u0004\u0007a1A\tAD\u0019\u0007\r-a\u0001\u0012\u0001\b4\u0011\u0015!4\u0001\"\u00016\u0011\u001d14A1A\u0005\u0002]BaaP\u0002!\u0002\u0013A\u0004b\u0002!\u0004\u0005\u0004%\ta\u000e\u0005\u0007\u0003\u000e\u0001\u000b\u0011\u0002\u001d\t\u000b\t\u001bA\u0011A\"\t\u000b\t\u001bA\u0011\u0001%\u0003\u0019Q\u000b7o\u001b'pG\u0006$\u0018n\u001c8\u000b\u00055q\u0011!C:dQ\u0016$W\u000f\\3s\u0015\ty\u0001#A\u0003ta\u0006\u00148N\u0003\u0002\u0012%\u00051\u0011\r]1dQ\u0016T\u0011aE\u0001\u0004_J<7C\u0001\u0001\u0016!\t1\u0012$D\u0001\u0018\u0015\u0005A\u0012!B:dC2\f\u0017B\u0001\u000e\u0018\u0005\u0019\te.\u001f*fM\u0006!\u0001n\\:u\u0007\u0001)\u0012A\b\t\u0003?\u0019r!\u0001\t\u0013\u0011\u0005\u0005:R\"\u0001\u0012\u000b\u0005\rb\u0012A\u0002\u001fs_>$h(\u0003\u0002&/\u00051\u0001K]3eK\u001aL!a\n\u0015\u0003\rM#(/\u001b8h\u0015\t)s#\u000b\u0003\u0001U1r\u0013BA\u0016\r\u0005e)\u00050Z2vi>\u00148)Y2iKR\u000b7o\u001b'pG\u0006$\u0018n\u001c8\n\u00055b!!\u0006%E\rN\u001b\u0015m\u00195f)\u0006\u001c8\u000eT8dCRLwN\\\u0005\u0003_1\u0011\u0001\u0003S8tiR\u000b7o\u001b'pG\u0006$\u0018n\u001c8\u0002\u0019Q\u000b7o\u001b'pG\u0006$\u0018n\u001c8\u0011\u0005I\u001aQ\"\u0001\u0007\u0014\u0005\r)\u0012A\u0002\u001fj]&$h\bF\u00012\u0003MIg.T3n_JLHj\\2bi&|g\u000eV1h+\u0005A\u0004CA\u001d?\u001b\u0005Q$BA\u001e=\u0003\u0011a\u0017M\\4\u000b\u0003u\nAA[1wC&\u0011qEO\u0001\u0015S:lU-\\8ss2{7-\u0019;j_:$\u0016m\u001a\u0011\u0002'\u0015DXmY;u_JdunY1uS>tG+Y4\u0002)\u0015DXmY;u_JdunY1uS>tG+Y4!\u0003\u0015\t\u0007\u000f\u001d7z)\r!UI\u0012\t\u0003e\u0001AQaG\u0005A\u0002yAQaR\u0005A\u0002y\t!\"\u001a=fGV$xN]%e)\t!\u0015\nC\u0003K\u0015\u0001\u0007a$A\u0002tiJ\u0004"
)
public interface TaskLocation {
   static TaskLocation apply(final String str) {
      return TaskLocation$.MODULE$.apply(str);
   }

   static TaskLocation apply(final String host, final String executorId) {
      return TaskLocation$.MODULE$.apply(host, executorId);
   }

   static String executorLocationTag() {
      return TaskLocation$.MODULE$.executorLocationTag();
   }

   static String inMemoryLocationTag() {
      return TaskLocation$.MODULE$.inMemoryLocationTag();
   }

   String host();
}

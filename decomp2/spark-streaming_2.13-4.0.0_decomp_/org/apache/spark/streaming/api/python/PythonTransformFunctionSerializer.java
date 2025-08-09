package org.apache.spark.streaming.api.python;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q3\u0001\"\u0004\b\u0011\u0002G\u0005aB\u0007\u0005\u0006C\u00011\ta\t\u0005\u0006o\u00011\t\u0001\u000f\u0005\u0006\u007f\u00011\t\u0001Q\u0004\u0007\u0003:A\tA\u0004\"\u0007\r5q\u0001\u0012\u0001\bD\u0011\u0015!U\u0001\"\u0001F\u0011%1U\u00011AA\u0002\u0013%q\tC\u0005J\u000b\u0001\u0007\t\u0019!C\u0005\u0015\"I\u0001+\u0002a\u0001\u0002\u0003\u0006K\u0001\u0013\u0005\u0006#\u0016!\tA\u0015\u0005\u0006+\u0016!\tA\u0016\u0005\u00063\u0016!\tA\u0017\u0002\"!f$\bn\u001c8Ue\u0006t7OZ8s[\u001a+hn\u0019;j_:\u001cVM]5bY&TXM\u001d\u0006\u0003\u001fA\ta\u0001]=uQ>t'BA\t\u0013\u0003\r\t\u0007/\u001b\u0006\u0003'Q\t\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u0005U1\u0012!B:qCJ\\'BA\f\u0019\u0003\u0019\t\u0007/Y2iK*\t\u0011$A\u0002pe\u001e\u001c\"\u0001A\u000e\u0011\u0005qyR\"A\u000f\u000b\u0003y\tQa]2bY\u0006L!\u0001I\u000f\u0003\r\u0005s\u0017PU3g\u0003\u0015!W/\u001c9t\u0007\u0001!\"\u0001\n\u0016\u0011\u0007q)s%\u0003\u0002';\t)\u0011I\u001d:bsB\u0011A\u0004K\u0005\u0003Su\u0011AAQ=uK\")1&\u0001a\u0001Y\u0005\u0011\u0011\u000e\u001a\t\u0003[Qr!A\f\u001a\u0011\u0005=jR\"\u0001\u0019\u000b\u0005E\u0012\u0013A\u0002\u001fs_>$h(\u0003\u00024;\u00051\u0001K]3eK\u001aL!!\u000e\u001c\u0003\rM#(/\u001b8h\u0015\t\u0019T$A\u0003m_\u0006$7\u000f\u0006\u0002:{A\u0011!hO\u0007\u0002\u001d%\u0011AH\u0004\u0002\u0018!f$\bn\u001c8Ue\u0006t7OZ8s[\u001a+hn\u0019;j_:DQA\u0010\u0002A\u0002\u0011\nQAY=uKN\fabZ3u\u0019\u0006\u001cHOR1jYV\u0014X-F\u0001-\u0003\u0005\u0002\u0016\u0010\u001e5p]R\u0013\u0018M\\:g_Jlg)\u001e8di&|gnU3sS\u0006d\u0017N_3s!\tQTa\u0005\u0002\u00067\u00051A(\u001b8jiz\"\u0012AQ\u0001\u000bg\u0016\u0014\u0018.\u00197ju\u0016\u0014X#\u0001%\u0011\u0005i\u0002\u0011AD:fe&\fG.\u001b>fe~#S-\u001d\u000b\u0003\u0017:\u0003\"\u0001\b'\n\u00055k\"\u0001B+oSRDqa\u0014\u0005\u0002\u0002\u0003\u0007\u0001*A\u0002yIE\n1b]3sS\u0006d\u0017N_3sA\u0005A!/Z4jgR,'\u000f\u0006\u0002L'\")AK\u0003a\u0001\u0011\u0006\u00191/\u001a:\u0002\u0013M,'/[1mSj,GC\u0001\u0013X\u0011\u0015A6\u00021\u0001:\u0003\u00111WO\\2\u0002\u0017\u0011,7/\u001a:jC2L'0\u001a\u000b\u0003smCQA\u0010\u0007A\u0002\u0011\u0002"
)
public interface PythonTransformFunctionSerializer {
   static PythonTransformFunction deserialize(final byte[] bytes) {
      return PythonTransformFunctionSerializer$.MODULE$.deserialize(bytes);
   }

   static byte[] serialize(final PythonTransformFunction func) {
      return PythonTransformFunctionSerializer$.MODULE$.serialize(func);
   }

   static void register(final PythonTransformFunctionSerializer ser) {
      PythonTransformFunctionSerializer$.MODULE$.register(ser);
   }

   byte[] dumps(final String id);

   PythonTransformFunction loads(final byte[] bytes);

   String getLastFailure();
}

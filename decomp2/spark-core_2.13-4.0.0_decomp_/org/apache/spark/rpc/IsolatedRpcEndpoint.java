package org.apache.spark.rpc;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q1\u0001BA\u0002\u0011\u0002G\u0005Qa\u0003\u0005\u0006-\u00011\t\u0001\u0007\u0002\u0014\u0013N|G.\u0019;fIJ\u00038-\u00128ea>Lg\u000e\u001e\u0006\u0003\t\u0015\t1A\u001d9d\u0015\t1q!A\u0003ta\u0006\u00148N\u0003\u0002\t\u0013\u00051\u0011\r]1dQ\u0016T\u0011AC\u0001\u0004_J<7c\u0001\u0001\r%A\u0011Q\u0002E\u0007\u0002\u001d)\tq\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0012\u001d\t1\u0011I\\=SK\u001a\u0004\"a\u0005\u000b\u000e\u0003\rI!!F\u0002\u0003\u0017I\u00038-\u00128ea>Lg\u000e^\u0001\fi\"\u0014X-\u00193D_VtGo\u0001\u0001\u0015\u0003e\u0001\"!\u0004\u000e\n\u0005mq!aA%oi\u0002"
)
public interface IsolatedRpcEndpoint extends RpcEndpoint {
   int threadCount();
}

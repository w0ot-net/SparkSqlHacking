package org.apache.spark.util;

import java.util.EventListener;
import org.apache.spark.TaskContext;
import org.apache.spark.annotation.DeveloperApi;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005u2qAA\u0002\u0011\u0002G\u0005A\u0002C\u0003\u001b\u0001\u0019\u00051DA\nUCN\\g)Y5mkJ,G*[:uK:,'O\u0003\u0002\u0005\u000b\u0005!Q\u000f^5m\u0015\t1q!A\u0003ta\u0006\u00148N\u0003\u0002\t\u0013\u00051\u0011\r]1dQ\u0016T\u0011AC\u0001\u0004_J<7\u0001A\n\u0004\u00015)\u0002C\u0001\b\u0014\u001b\u0005y!B\u0001\t\u0012\u0003\u0011a\u0017M\\4\u000b\u0003I\tAA[1wC&\u0011Ac\u0004\u0002\u0007\u001f\nTWm\u0019;\u0011\u0005YAR\"A\f\u000b\u0005\u0011\t\u0012BA\r\u0018\u00055)e/\u001a8u\u0019&\u001cH/\u001a8fe\u0006iqN\u001c+bg.4\u0015-\u001b7ve\u0016$2\u0001\b\u0012)!\ti\u0002%D\u0001\u001f\u0015\u0005y\u0012!B:dC2\f\u0017BA\u0011\u001f\u0005\u0011)f.\u001b;\t\u000b\r\n\u0001\u0019\u0001\u0013\u0002\u000f\r|g\u000e^3yiB\u0011QEJ\u0007\u0002\u000b%\u0011q%\u0002\u0002\f)\u0006\u001c8nQ8oi\u0016DH\u000fC\u0003*\u0003\u0001\u0007!&A\u0003feJ|'\u000f\u0005\u0002,g9\u0011A&\r\b\u0003[Aj\u0011A\f\u0006\u0003_-\ta\u0001\u0010:p_Rt\u0014\"A\u0010\n\u0005Ir\u0012a\u00029bG.\fw-Z\u0005\u0003iU\u0012\u0011\u0002\u00165s_^\f'\r\\3\u000b\u0005Ir\u0002F\u0001\u00018!\tA4(D\u0001:\u0015\tQT!\u0001\u0006b]:|G/\u0019;j_:L!\u0001P\u001d\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5"
)
public interface TaskFailureListener extends EventListener {
   void onTaskFailure(final TaskContext context, final Throwable error);
}

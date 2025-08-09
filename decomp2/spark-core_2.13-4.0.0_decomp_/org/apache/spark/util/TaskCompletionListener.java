package org.apache.spark.util;

import java.util.EventListener;
import org.apache.spark.TaskContext;
import org.apache.spark.annotation.DeveloperApi;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005=2qAA\u0002\u0011\u0002G\u0005A\u0002C\u0003\u001b\u0001\u0019\u00051D\u0001\fUCN\\7i\\7qY\u0016$\u0018n\u001c8MSN$XM\\3s\u0015\t!Q!\u0001\u0003vi&d'B\u0001\u0004\b\u0003\u0015\u0019\b/\u0019:l\u0015\tA\u0011\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0015\u0005\u0019qN]4\u0004\u0001M\u0019\u0001!D\u000b\u0011\u00059\u0019R\"A\b\u000b\u0005A\t\u0012\u0001\u00027b]\u001eT\u0011AE\u0001\u0005U\u00064\u0018-\u0003\u0002\u0015\u001f\t1qJ\u00196fGR\u0004\"A\u0006\r\u000e\u0003]Q!\u0001B\t\n\u0005e9\"!D#wK:$H*[:uK:,'/\u0001\tp]R\u000b7o[\"p[BdW\r^5p]R\u0011AD\t\t\u0003;\u0001j\u0011A\b\u0006\u0002?\u0005)1oY1mC&\u0011\u0011E\b\u0002\u0005+:LG\u000fC\u0003$\u0003\u0001\u0007A%A\u0004d_:$X\r\u001f;\u0011\u0005\u00152S\"A\u0003\n\u0005\u001d*!a\u0003+bg.\u001cuN\u001c;fqRD#\u0001A\u0015\u0011\u0005)jS\"A\u0016\u000b\u00051*\u0011AC1o]>$\u0018\r^5p]&\u0011af\u000b\u0002\r\t\u00164X\r\\8qKJ\f\u0005/\u001b"
)
public interface TaskCompletionListener extends EventListener {
   void onTaskCompletion(final TaskContext context);
}

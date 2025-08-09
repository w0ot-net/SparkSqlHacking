package org.apache.spark.sql.streaming;

import java.io.Serializable;
import org.apache.spark.annotation.Evolving;
import scala.reflect.ScalaSignature;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005%2qAA\u0002\u0011\u0002G\u0005a\u0002C\u0003\u001e\u0001\u0019\u0005aD\u0001\tFqBL'/\u001a3US6,'/\u00138g_*\u0011A!B\u0001\ngR\u0014X-Y7j]\u001eT!AB\u0004\u0002\u0007M\fHN\u0003\u0002\t\u0013\u0005)1\u000f]1sW*\u0011!bC\u0001\u0007CB\f7\r[3\u000b\u00031\t1a\u001c:h\u0007\u0001\u00192\u0001A\b\u0016!\t\u00012#D\u0001\u0012\u0015\u0005\u0011\u0012!B:dC2\f\u0017B\u0001\u000b\u0012\u0005\u0019\te.\u001f*fMB\u0011acG\u0007\u0002/)\u0011\u0001$G\u0001\u0003S>T\u0011AG\u0001\u0005U\u00064\u0018-\u0003\u0002\u001d/\ta1+\u001a:jC2L'0\u00192mK\u0006\tr-\u001a;FqBL'/\u001f+j[\u0016Le.T:\u0015\u0003}\u0001\"\u0001\u0005\u0011\n\u0005\u0005\n\"\u0001\u0002'p]\u001eD#\u0001A\u0012\u0011\u0005\u0011:S\"A\u0013\u000b\u0005\u0019:\u0011AC1o]>$\u0018\r^5p]&\u0011\u0001&\n\u0002\t\u000bZ|GN^5oO\u0002"
)
public interface ExpiredTimerInfo extends Serializable {
   long getExpiryTimeInMs();
}

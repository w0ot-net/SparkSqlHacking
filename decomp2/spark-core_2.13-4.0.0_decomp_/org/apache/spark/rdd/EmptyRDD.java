package org.apache.spark.rdd;

import org.apache.spark.Partition;
import org.apache.spark.SparkContext;
import org.apache.spark.TaskContext;
import org.apache.spark.errors.SparkCoreErrors$;
import scala.Array.;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M3QAB\u0004\u0001\u0013=A\u0001\u0002\n\u0001\u0003\u0002\u0003\u0006I!\n\u0005\tS\u0001\u0011\u0019\u0011)A\u0006U!)\u0001\u0007\u0001C\u0001c!)a\u0007\u0001C!o!)a\b\u0001C!\u007f\tAQ)\u001c9usJ#EI\u0003\u0002\t\u0013\u0005\u0019!\u000f\u001a3\u000b\u0005)Y\u0011!B:qCJ\\'B\u0001\u0007\u000e\u0003\u0019\t\u0007/Y2iK*\ta\"A\u0002pe\u001e,\"\u0001E\f\u0014\u0005\u0001\t\u0002c\u0001\n\u0014+5\tq!\u0003\u0002\u0015\u000f\t\u0019!\u000b\u0012#\u0011\u0005Y9B\u0002\u0001\u0003\u00061\u0001\u0011\rA\u0007\u0002\u0002)\u000e\u0001\u0011CA\u000e\"!\tar$D\u0001\u001e\u0015\u0005q\u0012!B:dC2\f\u0017B\u0001\u0011\u001e\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\b\u0012\n\u0005\rj\"aA!os\u0006\u00111o\u0019\t\u0003M\u001dj\u0011!C\u0005\u0003Q%\u0011Ab\u00159be.\u001cuN\u001c;fqR\f!\"\u001a<jI\u0016t7-\u001a\u00132!\rYc&F\u0007\u0002Y)\u0011Q&H\u0001\be\u00164G.Z2u\u0013\tyCF\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003\u0019a\u0014N\\5u}Q\u0011!'\u000e\u000b\u0003gQ\u00022A\u0005\u0001\u0016\u0011\u0015I3\u0001q\u0001+\u0011\u0015!3\u00011\u0001&\u000359W\r\u001e)beRLG/[8ogV\t\u0001\bE\u0002\u001dsmJ!AO\u000f\u0003\u000b\u0005\u0013(/Y=\u0011\u0005\u0019b\u0014BA\u001f\n\u0005%\u0001\u0016M\u001d;ji&|g.A\u0004d_6\u0004X\u000f^3\u0015\u0007\u0001ce\nE\u0002B\u0013Vq!AQ$\u000f\u0005\r3U\"\u0001#\u000b\u0005\u0015K\u0012A\u0002\u001fs_>$h(C\u0001\u001f\u0013\tAU$A\u0004qC\u000e\\\u0017mZ3\n\u0005)[%\u0001C%uKJ\fGo\u001c:\u000b\u0005!k\u0002\"B'\u0006\u0001\u0004Y\u0014!B:qY&$\b\"B(\u0006\u0001\u0004\u0001\u0016aB2p]R,\u0007\u0010\u001e\t\u0003MEK!AU\u0005\u0003\u0017Q\u000b7o[\"p]R,\u0007\u0010\u001e"
)
public class EmptyRDD extends RDD {
   public Partition[] getPartitions() {
      return (Partition[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(Partition.class));
   }

   public Iterator compute(final Partition split, final TaskContext context) {
      throw SparkCoreErrors$.MODULE$.emptyRDDError();
   }

   public EmptyRDD(final SparkContext sc, final ClassTag evidence$1) {
      super(sc, scala.collection.immutable.Nil..MODULE$, evidence$1);
   }
}

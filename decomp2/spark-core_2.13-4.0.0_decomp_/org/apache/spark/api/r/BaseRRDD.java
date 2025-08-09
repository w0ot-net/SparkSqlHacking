package org.apache.spark.api.r;

import org.apache.spark.Partition;
import org.apache.spark.TaskContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.rdd.RDD;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001db!B\u0007\u000f\u0003\u0013I\u0002\u0002C\u001b\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001c\t\u0011i\u0002!\u0011!Q\u0001\nmB\u0001B\u0010\u0001\u0003\u0002\u0003\u0006Ia\u0010\u0005\t\u000b\u0002\u0011\t\u0011)A\u0005\r\"A\u0011\u000b\u0001B\u0001B\u0003%a\t\u0003\u0005S\u0001\t\u0005\t\u0015!\u0003@\u0011!\u0019\u0006A!A!\u0002\u0013!\u0006\u0002C2\u0001\u0005\u0007\u0005\u000b1\u00023\t\u0011)\u0004!1!Q\u0001\f-DQ\u0001\u001c\u0001\u0005\u00025DQA\u001f\u0001\u0005BmDq!a\u0001\u0001\t\u0003\n)A\u0001\u0005CCN,'K\u0015#E\u0015\ty\u0001#A\u0001s\u0015\t\t\"#A\u0002ba&T!a\u0005\u000b\u0002\u000bM\u0004\u0018M]6\u000b\u0005U1\u0012AB1qC\u000eDWMC\u0001\u0018\u0003\ry'oZ\u0002\u0001+\rQ\u0002hI\n\u0004\u0001my\u0003c\u0001\u000f C5\tQD\u0003\u0002\u001f%\u0005\u0019!\u000f\u001a3\n\u0005\u0001j\"a\u0001*E\tB\u0011!e\t\u0007\u0001\t\u0015!\u0003A1\u0001&\u0005\u0005)\u0016C\u0001\u0014-!\t9#&D\u0001)\u0015\u0005I\u0013!B:dC2\f\u0017BA\u0016)\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aJ\u0017\n\u00059B#aA!osB\u0011\u0001gM\u0007\u0002c)\u0011!GE\u0001\tS:$XM\u001d8bY&\u0011A'\r\u0002\b\u0019><w-\u001b8h\u0003\u0019\u0001\u0018M]3oiB\u0019AdH\u001c\u0011\u0005\tBD!B\u001d\u0001\u0005\u0004)#!\u0001+\u0002\u001b9,X\u000eU1si&$\u0018n\u001c8t!\t9C(\u0003\u0002>Q\t\u0019\u0011J\u001c;\u0002\t\u0019,hn\u0019\t\u0004O\u0001\u0013\u0015BA!)\u0005\u0015\t%O]1z!\t93)\u0003\u0002EQ\t!!)\u001f;f\u00031!Wm]3sS\u0006d\u0017N_3s!\t9eJ\u0004\u0002I\u0019B\u0011\u0011\nK\u0007\u0002\u0015*\u00111\nG\u0001\u0007yI|w\u000e\u001e \n\u00055C\u0013A\u0002)sK\u0012,g-\u0003\u0002P!\n11\u000b\u001e:j]\u001eT!!\u0014\u0015\u0002\u0015M,'/[1mSj,'/\u0001\u0007qC\u000e\\\u0017mZ3OC6,7/A\u0007ce>\fGmY1tiZ\u000b'o\u001d\t\u0004O\u0001+\u0006c\u0001,Z76\tqK\u0003\u0002Y%\u0005I!M]8bI\u000e\f7\u000f^\u0005\u00035^\u0013\u0011B\u0011:pC\u0012\u001c\u0017m\u001d;\u0011\u0005q\u000bW\"A/\u000b\u0005y{\u0016\u0001\u00027b]\u001eT\u0011\u0001Y\u0001\u0005U\u00064\u0018-\u0003\u0002c;\n1qJ\u00196fGR\f!\"\u001a<jI\u0016t7-\u001a\u00132!\r)\u0007nN\u0007\u0002M*\u0011q\rK\u0001\be\u00164G.Z2u\u0013\tIgM\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003))g/\u001b3f]\u000e,GE\r\t\u0004K\"\f\u0013A\u0002\u001fj]&$h\b\u0006\u0005ogR,ho\u001e=z)\ry\u0017O\u001d\t\u0005a\u00029\u0014%D\u0001\u000f\u0011\u0015\u0019'\u0002q\u0001e\u0011\u0015Q'\u0002q\u0001l\u0011\u0015)$\u00021\u00017\u0011\u0015Q$\u00021\u0001<\u0011\u0015q$\u00021\u0001@\u0011\u0015)%\u00021\u0001G\u0011\u0015\t&\u00021\u0001G\u0011\u0015\u0011&\u00021\u0001@\u0011\u0015\u0019&\u00021\u0001U\u000359W\r\u001e)beRLG/[8ogV\tA\u0010E\u0002(\u0001v\u0004\"A`@\u000e\u0003II1!!\u0001\u0013\u0005%\u0001\u0016M\u001d;ji&|g.A\u0004d_6\u0004X\u000f^3\u0015\r\u0005\u001d\u0011\u0011DA\u000f!\u0015\tI!a\u0005\"\u001d\u0011\tY!a\u0004\u000f\u0007%\u000bi!C\u0001*\u0013\r\t\t\u0002K\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t)\"a\u0006\u0003\u0011%#XM]1u_JT1!!\u0005)\u0011\u0019\tY\u0002\u0004a\u0001{\u0006I\u0001/\u0019:uSRLwN\u001c\u0005\b\u0003?a\u0001\u0019AA\u0011\u0003\u001d\u0019wN\u001c;fqR\u00042A`A\u0012\u0013\r\t)C\u0005\u0002\f)\u0006\u001c8nQ8oi\u0016DH\u000f"
)
public abstract class BaseRRDD extends RDD {
   private final RDD parent;
   private final int numPartitions;
   private final byte[] func;
   private final String deserializer;
   private final String serializer;
   private final byte[] packageNames;
   private final Broadcast[] broadcastVars;
   private final ClassTag evidence$1;

   public Partition[] getPartitions() {
      return this.parent.partitions();
   }

   public Iterator compute(final Partition partition, final TaskContext context) {
      RRunner runner = new RRunner(this.func, this.deserializer, this.serializer, this.packageNames, this.broadcastVars, this.numPartitions, RRunner$.MODULE$.$lessinit$greater$default$7(), RRunner$.MODULE$.$lessinit$greater$default$8(), RRunner$.MODULE$.$lessinit$greater$default$9());
      Iterator parentIterator = this.firstParent(this.evidence$1).iterator(partition, context);
      return runner.compute(parentIterator, partition.index());
   }

   public BaseRRDD(final RDD parent, final int numPartitions, final byte[] func, final String deserializer, final String serializer, final byte[] packageNames, final Broadcast[] broadcastVars, final ClassTag evidence$1, final ClassTag evidence$2) {
      super(parent, evidence$2);
      this.parent = parent;
      this.numPartitions = numPartitions;
      this.func = func;
      this.deserializer = deserializer;
      this.serializer = serializer;
      this.packageNames = packageNames;
      this.broadcastVars = broadcastVars;
      this.evidence$1 = evidence$1;
   }
}

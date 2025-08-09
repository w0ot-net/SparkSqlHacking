package org.apache.spark.util.collection;

import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q3Qa\u0002\u0005\u0001\u0019IA\u0001B\u0007\u0001\u0003\u0002\u0003\u0006I\u0001\b\u0005\u0006{\u0001!\tA\u0010\u0005\u0007\u0005\u0002\u0001\u000b\u0015\u0002\u0015\t\u000b\r\u0003A\u0011\u0001#\t\u000b5\u0003A\u0011\u0001(\t\u000bI\u0003A\u0011A*\u00037]\u0013\u0018\u000e^1cY\u0016\u0004\u0016M\u001d;ji&|g.\u001a3Ji\u0016\u0014\u0018\r^8s\u0015\tI!\"\u0001\u0006d_2dWm\u0019;j_:T!a\u0003\u0007\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u001b9\tQa\u001d9be.T!a\u0004\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\t\u0012aA8sOV\u00191#M\u001e\u0014\u0005\u0001!\u0002CA\u000b\u0019\u001b\u00051\"\"A\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005e1\"AB!osJ+g-\u0001\u0002ji\u000e\u0001\u0001cA\u000f&Q9\u0011ad\t\b\u0003?\tj\u0011\u0001\t\u0006\u0003Cm\ta\u0001\u0010:p_Rt\u0014\"A\f\n\u0005\u00112\u0012a\u00029bG.\fw-Z\u0005\u0003M\u001d\u0012\u0001\"\u0013;fe\u0006$xN\u001d\u0006\u0003IY\u0001B!F\u0015,u%\u0011!F\u0006\u0002\u0007)V\u0004H.\u001a\u001a\u0011\tUICf\f\t\u0003+5J!A\f\f\u0003\u0007%sG\u000f\u0005\u00021c1\u0001A!\u0002\u001a\u0001\u0005\u0004\u0019$!A&\u0012\u0005Q:\u0004CA\u000b6\u0013\t1dCA\u0004O_RD\u0017N\\4\u0011\u0005UA\u0014BA\u001d\u0017\u0005\r\te.\u001f\t\u0003am\"Q\u0001\u0010\u0001C\u0002M\u0012\u0011AV\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005}\n\u0005\u0003\u0002!\u0001_ij\u0011\u0001\u0003\u0005\u00065\t\u0001\r\u0001H\u0001\u0004GV\u0014\u0018!C<sSR,g*\u001a=u)\t)\u0005\n\u0005\u0002\u0016\r&\u0011qI\u0006\u0002\u0005+:LG\u000fC\u0003J\t\u0001\u0007!*\u0001\u0004xe&$XM\u001d\t\u0003\u0001.K!\u0001\u0014\u0005\u0003\u0017A\u000b\u0017N]:Xe&$XM]\u0001\bQ\u0006\u001ch*\u001a=u+\u0005y\u0005CA\u000bQ\u0013\t\tfCA\u0004C_>dW-\u00198\u0002\u001b9,\u0007\u0010\u001e)beRLG/[8o)\u0005a\u0003"
)
public class WritablePartitionedIterator {
   private final Iterator it;
   private Tuple2 cur;

   public void writeNext(final PairsWriter writer) {
      writer.write(((Tuple2)this.cur._1())._2(), this.cur._2());
      this.cur = this.it.hasNext() ? (Tuple2)this.it.next() : null;
   }

   public boolean hasNext() {
      return this.cur != null;
   }

   public int nextPartition() {
      return ((Tuple2)this.cur._1())._1$mcI$sp();
   }

   public WritablePartitionedIterator(final Iterator it) {
      this.it = it;
      this.cur = it.hasNext() ? (Tuple2)it.next() : null;
   }
}

package org.apache.spark.broadcast;

import org.apache.spark.SparkConf;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-4Qa\u0002\u0005\u0001\u0015AAQa\u0007\u0001\u0005\u0002uAQa\b\u0001\u0005B\u0001BQa\f\u0001\u0005BABq\u0001\u0016\u0001\u0012\u0002\u0013\u0005Q\u000bC\u0003c\u0001\u0011\u00053\rC\u0003e\u0001\u0011\u0005SMA\fU_J\u0014XM\u001c;Ce>\fGmY1ti\u001a\u000b7\r^8ss*\u0011\u0011BC\u0001\nEJ|\u0017\rZ2bgRT!a\u0003\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00055q\u0011AB1qC\u000eDWMC\u0001\u0010\u0003\ry'oZ\n\u0004\u0001E9\u0002C\u0001\n\u0016\u001b\u0005\u0019\"\"\u0001\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u0019\"AB!osJ+g\r\u0005\u0002\u001935\t\u0001\"\u0003\u0002\u001b\u0011\t\u0001\"I]8bI\u000e\f7\u000f\u001e$bGR|'/_\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\ta\u0004\u0005\u0002\u0019\u0001\u0005Q\u0011N\\5uS\u0006d\u0017N_3\u0015\u0007\u0005\"\u0013\u0006\u0005\u0002\u0013E%\u00111e\u0005\u0002\u0005+:LG\u000fC\u0003&\u0005\u0001\u0007a%\u0001\u0005jg\u0012\u0013\u0018N^3s!\t\u0011r%\u0003\u0002)'\t9!i\\8mK\u0006t\u0007\"\u0002\u0016\u0003\u0001\u0004Y\u0013\u0001B2p]\u001a\u0004\"\u0001L\u0017\u000e\u0003)I!A\f\u0006\u0003\u0013M\u0003\u0018M]6D_:4\u0017\u0001\u00048fo\n\u0013x.\u00193dCN$XCA\u00199)\u0015\u0011\u0014jS'S)\t\u0019\u0014\tE\u0002\u0019iYJ!!\u000e\u0005\u0003\u0013\t\u0013x.\u00193dCN$\bCA\u001c9\u0019\u0001!Q!O\u0002C\u0002i\u0012\u0011\u0001V\t\u0003wy\u0002\"A\u0005\u001f\n\u0005u\u001a\"a\u0002(pi\"Lgn\u001a\t\u0003%}J!\u0001Q\n\u0003\u0007\u0005s\u0017\u0010C\u0004C\u0007\u0005\u0005\t9A\"\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0002E\u000fZj\u0011!\u0012\u0006\u0003\rN\tqA]3gY\u0016\u001cG/\u0003\u0002I\u000b\nA1\t\\1tgR\u000bw\rC\u0003K\u0007\u0001\u0007a'\u0001\u0004wC2,Xm\u0018\u0005\u0006\u0019\u000e\u0001\rAJ\u0001\bSNdunY1m\u0011\u0015q5\u00011\u0001P\u0003\tIG\r\u0005\u0002\u0013!&\u0011\u0011k\u0005\u0002\u0005\u0019>tw\rC\u0004T\u0007A\u0005\t\u0019\u0001\u0014\u0002\u001dM,'/[1mSj,Gm\u00148ms\u00061b.Z<Ce>\fGmY1ti\u0012\"WMZ1vYR$C'\u0006\u0002WCV\tqK\u000b\u0002'1.\n\u0011\f\u0005\u0002[?6\t1L\u0003\u0002];\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003=N\t!\"\u00198o_R\fG/[8o\u0013\t\u00017LA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016$Q!\u000f\u0003C\u0002i\nAa\u001d;paR\t\u0011%A\u0006v]\n\u0014x.\u00193dCN$H\u0003B\u0011gO&DQA\u0014\u0004A\u0002=CQ\u0001\u001b\u0004A\u0002\u0019\n\u0001C]3n_Z,gI]8n\tJLg/\u001a:\t\u000b)4\u0001\u0019\u0001\u0014\u0002\u0011\tdwnY6j]\u001e\u0004"
)
public class TorrentBroadcastFactory implements BroadcastFactory {
   public void initialize(final boolean isDriver, final SparkConf conf) {
   }

   public Broadcast newBroadcast(final Object value_, final boolean isLocal, final long id, final boolean serializedOnly, final ClassTag evidence$1) {
      return new TorrentBroadcast(value_, id, serializedOnly, evidence$1);
   }

   public boolean newBroadcast$default$4() {
      return false;
   }

   public void stop() {
   }

   public void unbroadcast(final long id, final boolean removeFromDriver, final boolean blocking) {
      TorrentBroadcast$.MODULE$.unpersist(id, removeFromDriver, blocking);
   }
}

package org.apache.spark.broadcast;

import org.apache.spark.SparkConf;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00114\u0001BB\u0004\u0011\u0002\u0007\u0005\u0011b\u0004\u0005\u0006-\u00011\t\u0001\u0007\u0005\u0006O\u00011\t\u0001\u000b\u0005\b\u001b\u0002\t\n\u0011\"\u0001O\u0011\u0015Y\u0006A\"\u0001]\u0011\u0015\u0011\u0007A\"\u0001d\u0005A\u0011%o\\1eG\u0006\u001cHOR1di>\u0014\u0018P\u0003\u0002\t\u0013\u0005I!M]8bI\u000e\f7\u000f\u001e\u0006\u0003\u0015-\tQa\u001d9be.T!\u0001D\u0007\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0011aA8sON\u0011\u0001\u0001\u0005\t\u0003#Qi\u0011A\u0005\u0006\u0002'\u0005)1oY1mC&\u0011QC\u0005\u0002\u0007\u0003:L(+\u001a4\u0002\u0015%t\u0017\u000e^5bY&TXm\u0001\u0001\u0015\u0007ea\u0012\u0005\u0005\u0002\u00125%\u00111D\u0005\u0002\u0005+:LG\u000fC\u0003\u001e\u0003\u0001\u0007a$\u0001\u0005jg\u0012\u0013\u0018N^3s!\t\tr$\u0003\u0002!%\t9!i\\8mK\u0006t\u0007\"\u0002\u0012\u0002\u0001\u0004\u0019\u0013\u0001B2p]\u001a\u0004\"\u0001J\u0013\u000e\u0003%I!AJ\u0005\u0003\u0013M\u0003\u0018M]6D_:4\u0017\u0001\u00048fo\n\u0013x.\u00193dCN$XCA\u00152)\u0015Q#\t\u0012$L)\tY#\bE\u0002-[=j\u0011aB\u0005\u0003]\u001d\u0011\u0011B\u0011:pC\u0012\u001c\u0017m\u001d;\u0011\u0005A\nD\u0002\u0001\u0003\u0006e\t\u0011\ra\r\u0002\u0002)F\u0011Ag\u000e\t\u0003#UJ!A\u000e\n\u0003\u000f9{G\u000f[5oOB\u0011\u0011\u0003O\u0005\u0003sI\u00111!\u00118z\u0011\u001dY$!!AA\u0004q\n!\"\u001a<jI\u0016t7-\u001a\u00132!\ri\u0004iL\u0007\u0002})\u0011qHE\u0001\be\u00164G.Z2u\u0013\t\teH\u0001\u0005DY\u0006\u001c8\u000fV1h\u0011\u0015\u0019%\u00011\u00010\u0003\u00151\u0018\r\\;f\u0011\u0015)%\u00011\u0001\u001f\u0003\u001dI7\u000fT8dC2DQa\u0012\u0002A\u0002!\u000b!!\u001b3\u0011\u0005EI\u0015B\u0001&\u0013\u0005\u0011auN\\4\t\u000f1\u0013\u0001\u0013!a\u0001=\u0005q1/\u001a:jC2L'0\u001a3P]2L\u0018A\u00068fo\n\u0013x.\u00193dCN$H\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005=SV#\u0001)+\u0005y\t6&\u0001*\u0011\u0005MCV\"\u0001+\u000b\u0005U3\u0016!C;oG\",7m[3e\u0015\t9&#\u0001\u0006b]:|G/\u0019;j_:L!!\u0017+\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rB\u00033\u0007\t\u00071'A\u0006v]\n\u0014x.\u00193dCN$H\u0003B\r^=\u0002DQa\u0012\u0003A\u0002!CQa\u0018\u0003A\u0002y\t\u0001C]3n_Z,gI]8n\tJLg/\u001a:\t\u000b\u0005$\u0001\u0019\u0001\u0010\u0002\u0011\tdwnY6j]\u001e\fAa\u001d;paR\t\u0011\u0004"
)
public interface BroadcastFactory {
   void initialize(final boolean isDriver, final SparkConf conf);

   Broadcast newBroadcast(final Object value, final boolean isLocal, final long id, final boolean serializedOnly, final ClassTag evidence$1);

   // $FF: synthetic method
   static boolean newBroadcast$default$4$(final BroadcastFactory $this) {
      return $this.newBroadcast$default$4();
   }

   default boolean newBroadcast$default$4() {
      return false;
   }

   void unbroadcast(final long id, final boolean removeFromDriver, final boolean blocking);

   void stop();
}

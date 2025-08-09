package org.apache.spark.deploy.master;

import org.apache.spark.SparkConf;
import org.apache.spark.serializer.Serializer;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U2QAB\u0004\u0001\u000fEA\u0001B\u0006\u0001\u0003\u0002\u0003\u0006I\u0001\u0007\u0005\t9\u0001\u0011\t\u0011)A\u0005;!)!\u0005\u0001C\u0001G!)q\u0005\u0001C\u0001Q!)A\u0006\u0001C\u0001[\ta\"l\\8LK\u0016\u0004XM\u001d*fG>4XM]=N_\u0012,g)Y2u_JL(B\u0001\u0005\n\u0003\u0019i\u0017m\u001d;fe*\u0011!bC\u0001\u0007I\u0016\u0004Hn\\=\u000b\u00051i\u0011!B:qCJ\\'B\u0001\b\u0010\u0003\u0019\t\u0007/Y2iK*\t\u0001#A\u0002pe\u001e\u001c\"\u0001\u0001\n\u0011\u0005M!R\"A\u0004\n\u0005U9!!H*uC:$\u0017\r\\8oKJ+7m\u001c<feflu\u000eZ3GC\u000e$xN]=\u0002\t\r|gNZ\u0002\u0001!\tI\"$D\u0001\f\u0013\tY2BA\u0005Ta\u0006\u00148nQ8oM\u0006Q1/\u001a:jC2L'0\u001a:\u0011\u0005y\u0001S\"A\u0010\u000b\u0005qY\u0011BA\u0011 \u0005)\u0019VM]5bY&TXM]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007\u0011*c\u0005\u0005\u0002\u0014\u0001!)ac\u0001a\u00011!)Ad\u0001a\u0001;\u000592M]3bi\u0016\u0004VM]:jgR,gnY3F]\u001eLg.\u001a\u000b\u0002SA\u00111CK\u0005\u0003W\u001d\u0011\u0011\u0003U3sg&\u001cH/\u001a8dK\u0016sw-\u001b8f\u0003e\u0019'/Z1uK2+\u0017\rZ3s\u000b2,7\r^5p]\u0006;WM\u001c;\u0015\u00059\n\u0004CA\n0\u0013\t\u0001tAA\nMK\u0006$WM]#mK\u000e$\u0018n\u001c8BO\u0016tG\u000fC\u0003\t\u000b\u0001\u0007!\u0007\u0005\u0002\u0014g%\u0011Ag\u0002\u0002\u0010\u0019\u0016\fG-\u001a:FY\u0016\u001cG/\u00192mK\u0002"
)
public class ZooKeeperRecoveryModeFactory extends StandaloneRecoveryModeFactory {
   private final SparkConf conf;
   private final Serializer serializer;

   public PersistenceEngine createPersistenceEngine() {
      return new ZooKeeperPersistenceEngine(this.conf, this.serializer);
   }

   public LeaderElectionAgent createLeaderElectionAgent(final LeaderElectable master) {
      return new ZooKeeperLeaderElectionAgent(master, this.conf);
   }

   public ZooKeeperRecoveryModeFactory(final SparkConf conf, final Serializer serializer) {
      super(conf, serializer);
      this.conf = conf;
      this.serializer = serializer;
   }
}

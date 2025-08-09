package org.apache.spark.deploy.master;

import org.apache.spark.SparkConf;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.serializer.Serializer;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005}2QAB\u0004\u0002\u0002IA\u0001\"\u0007\u0001\u0003\u0002\u0003\u0006IA\u0007\u0005\t=\u0001\u0011\t\u0011)A\u0005?!)A\u0005\u0001C\u0001K!)!\u0006\u0001D\u0001W!)q\u0006\u0001D\u0001a\ti2\u000b^1oI\u0006dwN\\3SK\u000e|g/\u001a:z\u001b>$WMR1di>\u0014\u0018P\u0003\u0002\t\u0013\u00051Q.Y:uKJT!AC\u0006\u0002\r\u0011,\u0007\u000f\\8z\u0015\taQ\"A\u0003ta\u0006\u00148N\u0003\u0002\u000f\u001f\u00051\u0011\r]1dQ\u0016T\u0011\u0001E\u0001\u0004_J<7\u0001A\n\u0003\u0001M\u0001\"\u0001F\f\u000e\u0003UQ\u0011AF\u0001\u0006g\u000e\fG.Y\u0005\u00031U\u0011a!\u00118z%\u00164\u0017\u0001B2p]\u001a\u0004\"a\u0007\u000f\u000e\u0003-I!!H\u0006\u0003\u0013M\u0003\u0018M]6D_:4\u0017AC:fe&\fG.\u001b>feB\u0011\u0001EI\u0007\u0002C)\u0011adC\u0005\u0003G\u0005\u0012!bU3sS\u0006d\u0017N_3s\u0003\u0019a\u0014N\\5u}Q\u0019a\u0005K\u0015\u0011\u0005\u001d\u0002Q\"A\u0004\t\u000be\u0019\u0001\u0019\u0001\u000e\t\u000by\u0019\u0001\u0019A\u0010\u0002/\r\u0014X-\u0019;f!\u0016\u00148/[:uK:\u001cW-\u00128hS:,G#\u0001\u0017\u0011\u0005\u001dj\u0013B\u0001\u0018\b\u0005E\u0001VM]:jgR,gnY3F]\u001eLg.Z\u0001\u001aGJ,\u0017\r^3MK\u0006$WM]#mK\u000e$\u0018n\u001c8BO\u0016tG\u000f\u0006\u00022iA\u0011qEM\u0005\u0003g\u001d\u00111\u0003T3bI\u0016\u0014X\t\\3di&|g.Q4f]RDQ\u0001C\u0003A\u0002U\u0002\"a\n\u001c\n\u0005]:!a\u0004'fC\u0012,'/\u00127fGR\f'\r\\3)\u0005\u0001I\u0004C\u0001\u001e>\u001b\u0005Y$B\u0001\u001f\f\u0003)\tgN\\8uCRLwN\\\u0005\u0003}m\u0012A\u0002R3wK2|\u0007/\u001a:Ba&\u0004"
)
public abstract class StandaloneRecoveryModeFactory {
   public abstract PersistenceEngine createPersistenceEngine();

   public abstract LeaderElectionAgent createLeaderElectionAgent(final LeaderElectable master);

   public StandaloneRecoveryModeFactory(final SparkConf conf, final Serializer serializer) {
   }
}

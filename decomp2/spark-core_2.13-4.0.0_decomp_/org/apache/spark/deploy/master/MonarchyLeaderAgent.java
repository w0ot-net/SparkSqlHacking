package org.apache.spark.deploy.master;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00152Q\u0001B\u0003\u0001\u0013=A\u0001B\u0007\u0001\u0003\u0006\u0004%\t\u0001\b\u0005\tA\u0001\u0011\t\u0011)A\u0005;!)\u0011\u0005\u0001C\u0001E\t\u0019Rj\u001c8be\u000eD\u0017\u0010T3bI\u0016\u0014\u0018iZ3oi*\u0011aaB\u0001\u0007[\u0006\u001cH/\u001a:\u000b\u0005!I\u0011A\u00023fa2|\u0017P\u0003\u0002\u000b\u0017\u0005)1\u000f]1sW*\u0011A\"D\u0001\u0007CB\f7\r[3\u000b\u00039\t1a\u001c:h'\r\u0001\u0001C\u0006\t\u0003#Qi\u0011A\u0005\u0006\u0002'\u0005)1oY1mC&\u0011QC\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0005]AR\"A\u0003\n\u0005e)!a\u0005'fC\u0012,'/\u00127fGRLwN\\!hK:$\u0018AD7bgR,'/\u00138ti\u0006t7-Z\u0002\u0001+\u0005i\u0002CA\f\u001f\u0013\tyRAA\bMK\u0006$WM]#mK\u000e$\u0018M\u00197f\u0003=i\u0017m\u001d;fe&s7\u000f^1oG\u0016\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002$IA\u0011q\u0003\u0001\u0005\u00065\r\u0001\r!\b"
)
public class MonarchyLeaderAgent implements LeaderElectionAgent {
   private final LeaderElectable masterInstance;

   public void stop() {
      LeaderElectionAgent.stop$(this);
   }

   public LeaderElectable masterInstance() {
      return this.masterInstance;
   }

   public MonarchyLeaderAgent(final LeaderElectable masterInstance) {
      this.masterInstance = masterInstance;
      LeaderElectionAgent.$init$(this);
      masterInstance.electedLeader();
   }
}

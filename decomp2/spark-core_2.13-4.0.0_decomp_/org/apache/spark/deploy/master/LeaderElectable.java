package org.apache.spark.deploy.master;

import org.apache.spark.annotation.DeveloperApi;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\r2qa\u0001\u0003\u0011\u0002G\u0005q\u0002C\u0003\u0017\u0001\u0019\u0005q\u0003C\u0003\u001c\u0001\u0019\u0005qCA\bMK\u0006$WM]#mK\u000e$\u0018M\u00197f\u0015\t)a!\u0001\u0004nCN$XM\u001d\u0006\u0003\u000f!\ta\u0001Z3qY>L(BA\u0005\u000b\u0003\u0015\u0019\b/\u0019:l\u0015\tYA\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001b\u0005\u0019qN]4\u0004\u0001M\u0011\u0001\u0001\u0005\t\u0003#Qi\u0011A\u0005\u0006\u0002'\u0005)1oY1mC&\u0011QC\u0005\u0002\u0007\u0003:L(+\u001a4\u0002\u001b\u0015dWm\u0019;fI2+\u0017\rZ3s)\u0005A\u0002CA\t\u001a\u0013\tQ\"C\u0001\u0003V]&$\u0018!\u0005:fm>\\W\r\u001a'fC\u0012,'o\u001d5ja\"\u0012\u0001!\b\t\u0003=\u0005j\u0011a\b\u0006\u0003A!\t!\"\u00198o_R\fG/[8o\u0013\t\u0011sD\u0001\u0007EKZ,Gn\u001c9fe\u0006\u0003\u0018\u000e"
)
public interface LeaderElectable {
   void electedLeader();

   void revokedLeadership();
}

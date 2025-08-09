package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005A3A\u0001C\u0005\u0001%!AQ\u0005\u0001BC\u0002\u0013\u0005a\u0005\u0003\u00050\u0001\t\u0005\t\u0015!\u0003(\u0011!\u0001\u0004A!b\u0001\n\u0003\t\u0004\u0002C\u001b\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001a\t\u0011Y\u0002!Q1A\u0005\u0002]B\u0001b\u000f\u0001\u0003\u0002\u0003\u0006I\u0001\u000f\u0005\u0006y\u0001!\t!\u0010\u0002\u001c\u001b&\u001c8-\u001a7mC:,w.^:Qe>\u001cWm]:EKR\f\u0017\u000e\\:\u000b\u0005)Y\u0011!C:dQ\u0016$W\u000f\\3s\u0015\taQ\"A\u0003ta\u0006\u00148N\u0003\u0002\u000f\u001f\u00051\u0011\r]1dQ\u0016T\u0011\u0001E\u0001\u0004_J<7\u0001A\n\u0004\u0001MI\u0002C\u0001\u000b\u0018\u001b\u0005)\"\"\u0001\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005a)\"AB!osJ+g\r\u0005\u0002\u001bE9\u00111\u0004\t\b\u00039}i\u0011!\b\u0006\u0003=E\ta\u0001\u0010:p_Rt\u0014\"\u0001\f\n\u0005\u0005*\u0012a\u00029bG.\fw-Z\u0005\u0003G\u0011\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!!I\u000b\u0002\u0011!|7\u000f\u001e)peR,\u0012a\n\t\u0003Q1r!!\u000b\u0016\u0011\u0005q)\u0012BA\u0016\u0016\u0003\u0019\u0001&/\u001a3fM&\u0011QF\f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005-*\u0012!\u00035pgR\u0004vN\u001d;!\u0003\u0015\u0019wN]3t+\u0005\u0011\u0004C\u0001\u000b4\u0013\t!TCA\u0002J]R\faaY8sKN\u0004\u0013A\u00037pOV\u0013H.\u00138g_V\t\u0001\b\u0005\u0003)s\u001d:\u0013B\u0001\u001e/\u0005\ri\u0015\r]\u0001\fY><WK\u001d7J]\u001a|\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0005}\u0001\u000b%\t\u0005\u0002@\u00015\t\u0011\u0002C\u0003&\u000f\u0001\u0007q\u0005C\u00031\u000f\u0001\u0007!\u0007C\u00037\u000f\u0001\u0007\u0001\b\u000b\u0002\u0001\tB\u0011Q\tS\u0007\u0002\r*\u0011qiC\u0001\u000bC:tw\u000e^1uS>t\u0017BA%G\u00051!UM^3m_B,'/\u00119jQ\r\u00011J\u0014\t\u0003\u000b2K!!\u0014$\u0003\u000bMKgnY3\"\u0003=\u000bQa\r\u00183]A\u0002"
)
public class MiscellaneousProcessDetails implements Serializable {
   private final String hostPort;
   private final int cores;
   private final Map logUrlInfo;

   public String hostPort() {
      return this.hostPort;
   }

   public int cores() {
      return this.cores;
   }

   public Map logUrlInfo() {
      return this.logUrlInfo;
   }

   public MiscellaneousProcessDetails(final String hostPort, final int cores, final Map logUrlInfo) {
      this.hostPort = hostPort;
      this.cores = cores;
      this.logUrlInfo = logUrlInfo;
   }
}

package org.apache.spark.deploy.client;

import org.apache.spark.scheduler.ExecutorDecommissionInfo;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014\u0001\u0002C\u0005\u0011\u0002G\u0005Qb\u0005\u0005\u00065\u00011\t\u0001\b\u0005\u0006[\u00011\tA\f\u0005\u0006_\u00011\t\u0001\r\u0005\u0006g\u00011\t\u0001\u000e\u0005\u0006\u0005\u00021\ta\u0011\u0005\u0006\u001f\u00021\t\u0001\u0015\u0005\u00065\u00021\ta\u0017\u0002\u001c'R\fg\u000eZ1m_:,\u0017\t\u001d9DY&,g\u000e\u001e'jgR,g.\u001a:\u000b\u0005)Y\u0011AB2mS\u0016tGO\u0003\u0002\r\u001b\u00051A-\u001a9m_fT!AD\b\u0002\u000bM\u0004\u0018M]6\u000b\u0005A\t\u0012AB1qC\u000eDWMC\u0001\u0013\u0003\ry'oZ\n\u0003\u0001Q\u0001\"!\u0006\r\u000e\u0003YQ\u0011aF\u0001\u0006g\u000e\fG.Y\u0005\u00033Y\u0011a!\u00118z%\u00164\u0017!C2p]:,7\r^3e\u0007\u0001!\"!\b\u0011\u0011\u0005Uq\u0012BA\u0010\u0017\u0005\u0011)f.\u001b;\t\u000b\u0005\n\u0001\u0019\u0001\u0012\u0002\u000b\u0005\u0004\b/\u00133\u0011\u0005\rRcB\u0001\u0013)!\t)c#D\u0001'\u0015\t93$\u0001\u0004=e>|GOP\u0005\u0003SY\ta\u0001\u0015:fI\u00164\u0017BA\u0016-\u0005\u0019\u0019FO]5oO*\u0011\u0011FF\u0001\rI&\u001c8m\u001c8oK\u000e$X\r\u001a\u000b\u0002;\u0005!A-Z1e)\ti\u0012\u0007C\u00033\u0007\u0001\u0007!%\u0001\u0004sK\u0006\u001cxN\\\u0001\u000eKb,7-\u001e;pe\u0006#G-\u001a3\u0015\ru)t'O\u001eA\u0011\u00151D\u00011\u0001#\u0003\u00191W\u000f\u001c7JI\")\u0001\b\u0002a\u0001E\u0005Aqo\u001c:lKJLE\rC\u0003;\t\u0001\u0007!%\u0001\u0005i_N$\bk\u001c:u\u0011\u0015aD\u00011\u0001>\u0003\u0015\u0019wN]3t!\t)b(\u0003\u0002@-\t\u0019\u0011J\u001c;\t\u000b\u0005#\u0001\u0019A\u001f\u0002\r5,Wn\u001c:z\u0003=)\u00070Z2vi>\u0014(+Z7pm\u0016$G#B\u000fE\u000b\u001ec\u0005\"\u0002\u001c\u0006\u0001\u0004\u0011\u0003\"\u0002$\u0006\u0001\u0004\u0011\u0013aB7fgN\fw-\u001a\u0005\u0006\u0011\u0016\u0001\r!S\u0001\u000bKbLGo\u0015;biV\u001c\bcA\u000bK{%\u00111J\u0006\u0002\u0007\u001fB$\u0018n\u001c8\t\u000b5+\u0001\u0019\u0001(\u0002\u0015]|'o[3s\u0011>\u001cH\u000fE\u0002\u0016\u0015\n\na#\u001a=fGV$xN\u001d#fG>lW.[:tS>tW\r\u001a\u000b\u0004;E\u0013\u0006\"\u0002\u001c\u0007\u0001\u0004\u0011\u0003\"B*\u0007\u0001\u0004!\u0016\u0001\u00053fG>lW.[:tS>t\u0017J\u001c4p!\t)\u0006,D\u0001W\u0015\t9V\"A\u0005tG\",G-\u001e7fe&\u0011\u0011L\u0016\u0002\u0019\u000bb,7-\u001e;pe\u0012+7m\\7nSN\u001c\u0018n\u001c8J]\u001a|\u0017!D<pe.,'OU3n_Z,G\r\u0006\u0003\u001e9v{\u0006\"\u0002\u001d\b\u0001\u0004\u0011\u0003\"\u00020\b\u0001\u0004\u0011\u0013\u0001\u00025pgRDQAR\u0004A\u0002\t\u0002"
)
public interface StandaloneAppClientListener {
   void connected(final String appId);

   void disconnected();

   void dead(final String reason);

   void executorAdded(final String fullId, final String workerId, final String hostPort, final int cores, final int memory);

   void executorRemoved(final String fullId, final String message, final Option exitStatus, final Option workerHost);

   void executorDecommissioned(final String fullId, final ExecutorDecommissionInfo decommissionInfo);

   void workerRemoved(final String workerId, final String host, final String message);
}

package org.apache.spark.deploy.rest;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q2aAB\u0004\u0002\u0002\u001d\t\u0002\"\u0002\f\u0001\t\u0003A\u0002b\u0002\u000e\u0001\u0001\u0004%\ta\u0007\u0005\bS\u0001\u0001\r\u0011\"\u0001+\u0011\u0019\t\u0004\u0001)Q\u00059!)!\u0007\u0001C)g\tI2+\u001e2nSR\u0014Vm\u001d;Qe>$xnY8m%\u0016\fX/Z:u\u0015\tA\u0011\"\u0001\u0003sKN$(B\u0001\u0006\f\u0003\u0019!W\r\u001d7ps*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014xm\u0005\u0002\u0001%A\u00111\u0003F\u0007\u0002\u000f%\u0011Qc\u0002\u0002\u001a'V\u0014W.\u001b;SKN$\bK]8u_\u000e|G.T3tg\u0006<W-\u0001\u0004=S:LGOP\u0002\u0001)\u0005I\u0002CA\n\u0001\u0003I\u0019G.[3oiN\u0003\u0018M]6WKJ\u001c\u0018n\u001c8\u0016\u0003q\u0001\"!\b\u0014\u000f\u0005y!\u0003CA\u0010#\u001b\u0005\u0001#BA\u0011\u0018\u0003\u0019a$o\\8u})\t1%A\u0003tG\u0006d\u0017-\u0003\u0002&E\u00051\u0001K]3eK\u001aL!a\n\u0015\u0003\rM#(/\u001b8h\u0015\t)#%\u0001\fdY&,g\u000e^*qCJ\\g+\u001a:tS>tw\fJ3r)\tYs\u0006\u0005\u0002-[5\t!%\u0003\u0002/E\t!QK\\5u\u0011\u001d\u00014!!AA\u0002q\t1\u0001\u001f\u00132\u0003M\u0019G.[3oiN\u0003\u0018M]6WKJ\u001c\u0018n\u001c8!\u0003)!wNV1mS\u0012\fG/\u001a\u000b\u0002W\u0001"
)
public abstract class SubmitRestProtocolRequest extends SubmitRestProtocolMessage {
   private String clientSparkVersion = null;

   public String clientSparkVersion() {
      return this.clientSparkVersion;
   }

   public void clientSparkVersion_$eq(final String x$1) {
      this.clientSparkVersion = x$1;
   }

   public void doValidate() {
      super.doValidate();
      this.assertFieldIsSet(this.clientSparkVersion(), "clientSparkVersion");
   }
}

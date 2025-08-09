package org.apache.spark.deploy.rest;

import org.apache.spark.SparkConf;
import org.apache.spark.rpc.RpcEndpointRef;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M4Qa\u0005\u000b\u0001-yA\u0011b\t\u0001\u0003\u0002\u0003\u0006I!\n\u001a\t\u0013M\u0002!\u0011!Q\u0001\nQB\u0004\"C\u001d\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001e?\u0011!y\u0004A!A!\u0002\u0013\u0001\u0005\u0002\u0003$\u0001\u0005\u0003\u0005\u000b\u0011B\u0013\t\u000b\u001d\u0003A\u0011\u0001%\t\u000f=\u0003!\u0019!C)!\"1A\u000b\u0001Q\u0001\nECq!\u0016\u0001C\u0002\u0013Ec\u000b\u0003\u0004[\u0001\u0001\u0006Ia\u0016\u0005\b7\u0002\u0011\r\u0011\"\u0015]\u0011\u0019\u0001\u0007\u0001)A\u0005;\"9\u0011\r\u0001b\u0001\n#\u0012\u0007B\u00024\u0001A\u0003%1\rC\u0004h\u0001\t\u0007I\u0011\u000b5\t\r1\u0004\u0001\u0015!\u0003j\u0011\u001di\u0007A1A\u0005R9DaA\u001d\u0001!\u0002\u0013y'\u0001F*uC:$\u0017\r\\8oKJ+7\u000f^*feZ,'O\u0003\u0002\u0016-\u0005!!/Z:u\u0015\t9\u0002$\u0001\u0004eKBdw.\u001f\u0006\u00033i\tQa\u001d9be.T!a\u0007\u000f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005i\u0012aA8sON\u0011\u0001a\b\t\u0003A\u0005j\u0011\u0001F\u0005\u0003EQ\u0011ACU3tiN+(-\\5tg&|gnU3sm\u0016\u0014\u0018\u0001\u00025pgR\u001c\u0001\u0001\u0005\u0002'_9\u0011q%\f\t\u0003Q-j\u0011!\u000b\u0006\u0003U\u0011\na\u0001\u0010:p_Rt$\"\u0001\u0017\u0002\u000bM\u001c\u0017\r\\1\n\u00059Z\u0013A\u0002)sK\u0012,g-\u0003\u00021c\t11\u000b\u001e:j]\u001eT!AL\u0016\n\u0005\r\n\u0013!\u0004:fcV,7\u000f^3e!>\u0014H\u000f\u0005\u00026m5\t1&\u0003\u00028W\t\u0019\u0011J\u001c;\n\u0005M\n\u0013AC7bgR,'oQ8oMB\u00111\bP\u0007\u00021%\u0011Q\b\u0007\u0002\n'B\f'o[\"p]\u001aL!!O\u0011\u0002\u001d5\f7\u000f^3s\u000b:$\u0007o\\5oiB\u0011\u0011\tR\u0007\u0002\u0005*\u00111\tG\u0001\u0004eB\u001c\u0017BA#C\u00059\u0011\u0006oY#oIB|\u0017N\u001c;SK\u001a\f\u0011\"\\1ti\u0016\u0014XK\u001d7\u0002\rqJg.\u001b;?)\u0019I%j\u0013'N\u001dB\u0011\u0001\u0005\u0001\u0005\u0006G\u0019\u0001\r!\n\u0005\u0006g\u0019\u0001\r\u0001\u000e\u0005\u0006s\u0019\u0001\rA\u000f\u0005\u0006\u007f\u0019\u0001\r\u0001\u0011\u0005\u0006\r\u001a\u0001\r!J\u0001\u0015gV\u0014W.\u001b;SKF,Xm\u001d;TKJ4H.\u001a;\u0016\u0003E\u0003\"\u0001\t*\n\u0005M#\"AH*uC:$\u0017\r\\8oKN+(-\\5u%\u0016\fX/Z:u'\u0016\u0014h\u000f\\3u\u0003U\u0019XOY7jiJ+\u0017/^3tiN+'O\u001e7fi\u0002\n!c[5mYJ+\u0017/^3tiN+'O\u001e7fiV\tq\u000b\u0005\u0002!1&\u0011\u0011\f\u0006\u0002\u001d'R\fg\u000eZ1m_:,7*\u001b7m%\u0016\fX/Z:u'\u0016\u0014h\u000f\\3u\u0003MY\u0017\u000e\u001c7SKF,Xm\u001d;TKJ4H.\u001a;!\u0003UY\u0017\u000e\u001c7BY2\u0014V-];fgR\u001cVM\u001d<mKR,\u0012!\u0018\t\u0003AyK!a\u0018\u000b\u0003?M#\u0018M\u001c3bY>tWmS5mY\u0006cGNU3rk\u0016\u001cHoU3sm2,G/\u0001\flS2d\u0017\t\u001c7SKF,Xm\u001d;TKJ4H.\u001a;!\u0003Q\u0019H/\u0019;vgJ+\u0017/^3tiN+'O\u001e7fiV\t1\r\u0005\u0002!I&\u0011Q\r\u0006\u0002\u001f'R\fg\u000eZ1m_:,7\u000b^1ukN\u0014V-];fgR\u001cVM\u001d<mKR\fQc\u001d;biV\u001c(+Z9vKN$8+\u001a:wY\u0016$\b%A\ndY\u0016\f'OU3rk\u0016\u001cHoU3sm2,G/F\u0001j!\t\u0001#.\u0003\u0002l)\ti2\u000b^1oI\u0006dwN\\3DY\u0016\f'OU3rk\u0016\u001cHoU3sm2,G/\u0001\u000bdY\u0016\f'OU3rk\u0016\u001cHoU3sm2,G\u000fI\u0001\u0015e\u0016\fG-\u001f>SKF,Xm\u001d;TKJ4H.\u001a;\u0016\u0003=\u0004\"\u0001\t9\n\u0005E$\"AH*uC:$\u0017\r\\8oKJ+\u0017\rZ={%\u0016\fX/Z:u'\u0016\u0014h\u000f\\3u\u0003U\u0011X-\u00193zuJ+\u0017/^3tiN+'O\u001e7fi\u0002\u0002"
)
public class StandaloneRestServer extends RestSubmissionServer {
   private final StandaloneSubmitRequestServlet submitRequestServlet;
   private final StandaloneKillRequestServlet killRequestServlet;
   private final StandaloneKillAllRequestServlet killAllRequestServlet;
   private final StandaloneStatusRequestServlet statusRequestServlet;
   private final StandaloneClearRequestServlet clearRequestServlet;
   private final StandaloneReadyzRequestServlet readyzRequestServlet;

   public StandaloneSubmitRequestServlet submitRequestServlet() {
      return this.submitRequestServlet;
   }

   public StandaloneKillRequestServlet killRequestServlet() {
      return this.killRequestServlet;
   }

   public StandaloneKillAllRequestServlet killAllRequestServlet() {
      return this.killAllRequestServlet;
   }

   public StandaloneStatusRequestServlet statusRequestServlet() {
      return this.statusRequestServlet;
   }

   public StandaloneClearRequestServlet clearRequestServlet() {
      return this.clearRequestServlet;
   }

   public StandaloneReadyzRequestServlet readyzRequestServlet() {
      return this.readyzRequestServlet;
   }

   public StandaloneRestServer(final String host, final int requestedPort, final SparkConf masterConf, final RpcEndpointRef masterEndpoint, final String masterUrl) {
      super(host, requestedPort, masterConf);
      this.submitRequestServlet = new StandaloneSubmitRequestServlet(masterEndpoint, masterUrl, super.masterConf());
      this.killRequestServlet = new StandaloneKillRequestServlet(masterEndpoint, super.masterConf());
      this.killAllRequestServlet = new StandaloneKillAllRequestServlet(masterEndpoint, super.masterConf());
      this.statusRequestServlet = new StandaloneStatusRequestServlet(masterEndpoint, super.masterConf());
      this.clearRequestServlet = new StandaloneClearRequestServlet(masterEndpoint, super.masterConf());
      this.readyzRequestServlet = new StandaloneReadyzRequestServlet(masterEndpoint, super.masterConf());
   }
}

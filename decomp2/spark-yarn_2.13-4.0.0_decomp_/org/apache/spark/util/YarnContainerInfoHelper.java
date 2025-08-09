package org.apache.spark.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.yarn.api.records.ContainerId;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.spark.internal.Logging;
import scala.Option;
import scala.StringContext;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%rA\u0002\t\u0012\u0011\u0003\u0019\u0012D\u0002\u0004\u001c#!\u00051\u0003\b\u0005\u0006S\u0005!\ta\u000b\u0005\u0007Y\u0005\u0001\u000b\u0011B\u0017\t\ru\n\u0001\u0015!\u0003?\u0011\u0015\t\u0015\u0001\"\u0001C\u0011\u0015\u0011\u0016\u0001\"\u0001T\u0011\u0015i\u0017\u0001\"\u0001o\u0011\u0015\t\u0018\u0001\"\u0001s\u0011\u0015A\u0018\u0001\"\u0001z\u0011\u0015Y\u0018\u0001\"\u0001}\u0011\u001d\tY!\u0001C\u0001\u0003\u001bAq!!\u0005\u0002\t\u0003\t\u0019\u0002C\u0004\u0002\u0018\u0005!\t!!\u0007\t\u000f\u0005u\u0011\u0001\"\u0001\u0002 !9\u00111E\u0001\u0005\u0002\u0005\u0015\u0012aF-be:\u001cuN\u001c;bS:,'/\u00138g_\"+G\u000e]3s\u0015\t\u00112#\u0001\u0003vi&d'B\u0001\u000b\u0016\u0003\u0015\u0019\b/\u0019:l\u0015\t1r#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00021\u0005\u0019qN]4\u0011\u0005i\tQ\"A\t\u0003/e\u000b'O\\\"p]R\f\u0017N\\3s\u0013:4w\u000eS3ma\u0016\u00148cA\u0001\u001eGA\u0011a$I\u0007\u0002?)\t\u0001%A\u0003tG\u0006d\u0017-\u0003\u0002#?\t1\u0011I\\=SK\u001a\u0004\"\u0001J\u0014\u000e\u0003\u0015R!AJ\n\u0002\u0011%tG/\u001a:oC2L!\u0001K\u0013\u0003\u000f1{wmZ5oO\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u001a\u0003U!%+\u0013,F%~cujR0G\u00132+uLT!N\u000bN\u00032AL\u001a6\u001b\u0005y#B\u0001\u00192\u0003%IW.\\;uC\ndWM\u0003\u00023?\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005Qz#aA*fcB\u0011agO\u0007\u0002o)\u0011\u0001(O\u0001\u0005Y\u0006twMC\u0001;\u0003\u0011Q\u0017M^1\n\u0005q:$AB*ue&tw-A\fE%&3VIU0M\u001f\u001e{6\u000bV!S)~{eIR*F)B\u0011adP\u0005\u0003\u0001~\u00111!\u00138u\u0003U9W\r\u001e'pOV\u0013Hn\u001d$s_6\u0014\u0015m]3Ve2$\"a\u0011)\u0011\t\u0011[eJ\u0014\b\u0003\u000b&\u0003\"AR\u0010\u000e\u0003\u001dS!\u0001\u0013\u0016\u0002\rq\u0012xn\u001c;?\u0013\tQu$\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u00196\u00131!T1q\u0015\tQu\u0004\u0005\u0002E\u001f&\u0011A(\u0014\u0005\u0006#\u0016\u0001\rAT\u0001\bE\u0006\u001cX-\u0016:m\u0003)9W\r\u001e'pOV\u0013Hn\u001d\u000b\u0004)^\u0003\u0007c\u0001\u0010V\u0007&\u0011ak\b\u0002\u0007\u001fB$\u0018n\u001c8\t\u000ba3\u0001\u0019A-\u0002\t\r|gN\u001a\t\u00035zk\u0011a\u0017\u0006\u00031rS!!X\u000b\u0002\r!\fGm\\8q\u0013\ty6LA\u0007D_:4\u0017nZ;sCRLwN\u001c\u0005\u0006C\u001a\u0001\rAY\u0001\nG>tG/Y5oKJ\u00042AH+d!\t!7.D\u0001f\u0015\t1w-A\u0004sK\u000e|'\u000fZ:\u000b\u0005!L\u0017aA1qS*\u0011!\u000eX\u0001\u0005s\u0006\u0014h.\u0003\u0002mK\nI1i\u001c8uC&tWM]\u0001\u000eO\u0016$\u0018\t\u001e;sS\n,H/Z:\u0015\u0007Q{\u0007\u000fC\u0003Y\u000f\u0001\u0007\u0011\fC\u0003b\u000f\u0001\u0007!-A\bd_:4XM\u001d;U_N#(/\u001b8h)\tq5\u000fC\u0003u\u0011\u0001\u0007Q/A\u0006d_:$\u0018-\u001b8fe&#\u0007C\u00013w\u0013\t9XMA\u0006D_:$\u0018-\u001b8fe&#\u0017AD4fi\u000e{g\u000e^1j]\u0016\u0014\u0018\n\u001a\u000b\u0003kjDQ!Y\u0005A\u0002\t\fAbZ3u\u00072,8\u000f^3s\u0013\u0012$\"! @\u0011\u0007y)f\n\u0003\u0004\u0000\u0015\u0001\u0007\u0011\u0011A\u0001\ts\u0006\u0014hnQ8oMB!\u00111AA\u0004\u001b\t\t)A\u0003\u0002YS&!\u0011\u0011BA\u0003\u0005EI\u0016M\u001d8D_:4\u0017nZ;sCRLwN\\\u0001\u0012O\u0016$\u0018,\u0019:o\u0011R$\boU2iK6,Gc\u0001(\u0002\u0010!1qp\u0003a\u0001\u0003\u0003\t\u0011dZ3u\u001d>$W-T1oC\u001e,'\u000f\u0013;ua\u0006#GM]3tgR\u0019a*!\u0006\t\u000b\u0005d\u0001\u0019\u00012\u0002%\u001d,GOT8eK6\u000bg.Y4fe\"{7\u000f\u001e\u000b\u0004\u001d\u0006m\u0001\"B1\u000e\u0001\u0004\u0011\u0017AF4fi:{G-Z'b]\u0006<WM\u001d%uiB\u0004vN\u001d;\u0015\u00079\u000b\t\u0003C\u0003b\u001d\u0001\u0007!-\u0001\nhKRtu\u000eZ3NC:\fw-\u001a:Q_J$Hc\u0001(\u0002(!)\u0011m\u0004a\u0001E\u0002"
)
public final class YarnContainerInfoHelper {
   public static String getNodeManagerPort(final Option container) {
      return YarnContainerInfoHelper$.MODULE$.getNodeManagerPort(container);
   }

   public static String getNodeManagerHttpPort(final Option container) {
      return YarnContainerInfoHelper$.MODULE$.getNodeManagerHttpPort(container);
   }

   public static String getNodeManagerHost(final Option container) {
      return YarnContainerInfoHelper$.MODULE$.getNodeManagerHost(container);
   }

   public static String getNodeManagerHttpAddress(final Option container) {
      return YarnContainerInfoHelper$.MODULE$.getNodeManagerHttpAddress(container);
   }

   public static String getYarnHttpScheme(final YarnConfiguration yarnConf) {
      return YarnContainerInfoHelper$.MODULE$.getYarnHttpScheme(yarnConf);
   }

   public static Option getClusterId(final YarnConfiguration yarnConf) {
      return YarnContainerInfoHelper$.MODULE$.getClusterId(yarnConf);
   }

   public static ContainerId getContainerId(final Option container) {
      return YarnContainerInfoHelper$.MODULE$.getContainerId(container);
   }

   public static String convertToString(final ContainerId containerId) {
      return YarnContainerInfoHelper$.MODULE$.convertToString(containerId);
   }

   public static Option getAttributes(final Configuration conf, final Option container) {
      return YarnContainerInfoHelper$.MODULE$.getAttributes(conf, container);
   }

   public static Option getLogUrls(final Configuration conf, final Option container) {
      return YarnContainerInfoHelper$.MODULE$.getLogUrls(conf, container);
   }

   public static Map getLogUrlsFromBaseUrl(final String baseUrl) {
      return YarnContainerInfoHelper$.MODULE$.getLogUrlsFromBaseUrl(baseUrl);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return YarnContainerInfoHelper$.MODULE$.LogStringContext(sc);
   }
}

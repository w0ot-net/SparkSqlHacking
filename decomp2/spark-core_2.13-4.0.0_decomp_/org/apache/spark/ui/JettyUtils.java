package org.apache.spark.ui;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import org.apache.spark.SSLOptions;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.Logging;
import org.sparkproject.jetty.servlet.ServletContextHandler;
import scala.Function1;
import scala.StringContext;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\tuxAB\u0016-\u0011\u0003qCG\u0002\u00047Y!\u0005af\u000e\u0005\u0006\t\u0006!\tA\u0012\u0005\b\u000f\u0006\u0011\r\u0011\"\u0001I\u0011\u0019\t\u0016\u0001)A\u0005\u0013\"9!+\u0001b\u0001\n\u0003A\u0005BB*\u0002A\u0003%\u0011*\u0002\u0003U\u0003\u0001)f\u0001\u00028\u0002\u0001=D\u0001\"\u001d\u0005\u0003\u0006\u0004%\tA\u001d\u0005\tq\"\u0011\t\u0011)A\u0005g\"A\u0011\u0010\u0003BC\u0002\u0013\u0005!\u0010C\u0005\u0002\f!\u0011\t\u0011)A\u0005w\"Q\u0011Q\u0002\u0005\u0003\u0006\u0004%\t!a\u0004\t\u0015\u0005M\u0001B!A!\u0002\u0013\t\t\u0002\u0003\u0004E\u0011\u0011\u0005\u0011QC\u0004\n\u0003?\t\u0011\u0011!E\u0001\u0003C1\u0001B\\\u0001\u0002\u0002#\u0005\u00111\u0005\u0005\u0007\tF!\t!!\n\t\u0013\u0005\u001d\u0012#%A\u0005\u0002\u0005%\u0002bBA#\u0003\u0011\r\u0011q\t\u0005\b\u00037\nA1AA/\u0011\u001d\t\u0019)\u0001C\u0002\u0003\u000bCq!!$\u0002\t\u0013\ty\tC\u0004\u00020\u0006!\t!!-\t\u0013\u0005e\u0017!%A\u0005\u0002\u0005m\u0007bBAX\u0003\u0011\u0005\u00111\u001d\u0005\b\u0003W\fA\u0011AAw\u0011%\u0011y!AI\u0001\n\u0003\u0011\t\u0002C\u0005\u0003\u0016\u0005\t\n\u0011\"\u0001\u0002^\"I!qC\u0001\u0012\u0002\u0013\u0005!\u0011\u0004\u0005\b\u0005;\tA\u0011\u0001B\u0010\u0011\u001d\u00119#\u0001C\u0001\u0005SAqAa\u000e\u0002\t\u0003\u0011I\u0004C\u0005\u0003d\u0005\t\n\u0011\"\u0001\u0002^\"I!QM\u0001\u0012\u0002\u0013\u0005!q\r\u0005\b\u0005W\nA\u0011\u0002B7\u0011\u001d\u00119)\u0001C\u0001\u0005\u0013CqA!*\u0002\t\u0003\u00119\u000bC\u0004\u00036\u0006!\tAa.\t\u000f\t%\u0017\u0001\"\u0003\u0003L\"9!Q[\u0001\u0005\n\t]\u0007b\u0002Bu\u0003\u0011\u0005!1^\u0001\u000b\u0015\u0016$H/_+uS2\u001c(BA\u0017/\u0003\t)\u0018N\u0003\u00020a\u0005)1\u000f]1sW*\u0011\u0011GM\u0001\u0007CB\f7\r[3\u000b\u0003M\n1a\u001c:h!\t)\u0014!D\u0001-\u0005)QU\r\u001e;z+RLGn]\n\u0004\u0003ar\u0004CA\u001d=\u001b\u0005Q$\"A\u001e\u0002\u000bM\u001c\u0017\r\\1\n\u0005uR$AB!osJ+g\r\u0005\u0002@\u00056\t\u0001I\u0003\u0002B]\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002D\u0001\n9Aj\\4hS:<\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003Q\nAc\u0015)B%.{6i\u0014(O\u000b\u000e#vJU0O\u00036+U#A%\u0011\u0005){U\"A&\u000b\u00051k\u0015\u0001\u00027b]\u001eT\u0011AT\u0001\u0005U\u00064\u0018-\u0003\u0002Q\u0017\n11\u000b\u001e:j]\u001e\fQc\u0015)B%.{6i\u0014(O\u000b\u000e#vJU0O\u00036+\u0005%A\fS\u000b\u0012K%+R\"U?\u000e{eJT#D)>\u0013vLT!N\u000b\u0006A\"+\u0012#J%\u0016\u001bEkX\"P\u001d:+5\tV(S?:\u000bU*\u0012\u0011\u0003\u0013I+7\u000f]8oI\u0016\u0014XC\u0001,f!\u0011It+W2\n\u0005aS$!\u0003$v]\u000e$\u0018n\u001c82!\tQ\u0016-D\u0001\\\u0015\taV,\u0001\u0003iiR\u0004(B\u00010`\u0003\u001d\u0019XM\u001d<mKRT\u0011\u0001Y\u0001\bU\u0006\\\u0017M\u001d;b\u0013\t\u00117L\u0001\nIiR\u00048+\u001a:wY\u0016$(+Z9vKN$\bC\u00013f\u0019\u0001!QAZ\u0004C\u0002\u001d\u0014\u0011\u0001V\t\u0003Q.\u0004\"!O5\n\u0005)T$a\u0002(pi\"Lgn\u001a\t\u0003s1L!!\u001c\u001e\u0003\u0007\u0005s\u0017PA\u0007TKJ4H.\u001a;QCJ\fWn]\u000b\u0003aZ\u001c\"\u0001\u0003\u001d\u0002\u0013I,7\u000f]8oI\u0016\u0014X#A:\u0011\u0007Q<Q/D\u0001\u0002!\t!g\u000fB\u0003g\u0011\t\u0007q/\u0005\u0002iq\u0005Q!/Z:q_:$WM\u001d\u0011\u0002\u0017\r|g\u000e^3oiRK\b/Z\u000b\u0002wB\u0019A0a\u0002\u000f\u0007u\f\u0019\u0001\u0005\u0002\u007fu5\tqPC\u0002\u0002\u0002\u0015\u000ba\u0001\u0010:p_Rt\u0014bAA\u0003u\u00051\u0001K]3eK\u001aL1\u0001UA\u0005\u0015\r\t)AO\u0001\rG>tG/\u001a8u)f\u0004X\rI\u0001\nKb$(/Y2u\r:,\"!!\u0005\u0011\te:Vo_\u0001\u000bKb$(/Y2u\r:\u0004C\u0003CA\f\u00033\tY\"!\b\u0011\u0007QDQ\u000fC\u0003r\u001f\u0001\u00071\u000fC\u0003z\u001f\u0001\u00071\u0010C\u0005\u0002\u000e=\u0001\n\u00111\u0001\u0002\u0012\u0005i1+\u001a:wY\u0016$\b+\u0019:b[N\u0004\"\u0001^\t\u0014\u0005EADCAA\u0011\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%gU!\u00111FA\"+\t\tiC\u000b\u0003\u00020\u0005E\u0002\u0003B\u001dXW&[#!a\r\u0011\t\u0005U\u0012qH\u0007\u0003\u0003oQA!!\u000f\u0002<\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003{Q\u0014AC1o]>$\u0018\r^5p]&!\u0011\u0011IA\u001c\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\u0006MN\u0011\ra^\u0001\u0017UN|gNU3ta>tG-\u001a:U_N+'O\u001e7fiR!\u0011\u0011JA,!\u0011!\b\"a\u0013\u0011\t\u00055\u00131K\u0007\u0003\u0003\u001fR1!!\u00153\u0003\u0019Q7o\u001c85g&!\u0011QKA(\u0005\u0019Qe+\u00197vK\"1\u0011\u000f\u0006a\u0001\u00033\u0002B\u0001^\u0004\u0002L\u00051\u0002\u000e^7m%\u0016\u001c\bo\u001c8eKJ$vnU3sm2,G\u000f\u0006\u0003\u0002`\u0005}\u0004\u0003\u0002;\t\u0003C\u0002b!a\u0019\u0002n\u0005Md\u0002BA3\u0003Sr1A`A4\u0013\u0005Y\u0014bAA6u\u00059\u0001/Y2lC\u001e,\u0017\u0002BA8\u0003c\u00121aU3r\u0015\r\tYG\u000f\t\u0005\u0003k\nY(\u0004\u0002\u0002x)\u0019\u0011\u0011\u0010\u001e\u0002\u0007alG.\u0003\u0003\u0002~\u0005]$\u0001\u0002(pI\u0016Da!]\u000bA\u0002\u0005\u0005\u0005\u0003\u0002;\b\u0003C\na\u0003^3yiJ+7\u000f]8oI\u0016\u0014Hk\\*feZdW\r\u001e\u000b\u0005\u0003\u000f\u000bI\tE\u0002u\u0011mDa!\u001d\fA\u0002\u0005-\u0005c\u0001;\bw\u0006i1M]3bi\u0016\u001cVM\u001d<mKR,B!!%\u0002\"R1\u00111SAM\u0003G\u00032AWAK\u0013\r\t9j\u0017\u0002\f\u0011R$\boU3sm2,G\u000fC\u0004\u0002\u001c^\u0001\r!!(\u0002\u001bM,'O\u001e7fiB\u000b'/Y7t!\u0011!\b\"a(\u0011\u0007\u0011\f\t\u000bB\u0003g/\t\u0007q\u000fC\u0004\u0002&^\u0001\r!a*\u0002\t\r|gN\u001a\t\u0005\u0003S\u000bY+D\u0001/\u0013\r\tiK\f\u0002\n'B\f'o[\"p]\u001a\fAc\u0019:fCR,7+\u001a:wY\u0016$\b*\u00198eY\u0016\u0014X\u0003BAZ\u0003#$\"\"!.\u0002H\u0006-\u00171[Ak!\u0011\t9,a1\u000e\u0005\u0005e&b\u00010\u0002<*!\u0011QXA`\u0003\u0015QW\r\u001e;z\u0015\r\t\tMM\u0001\bK\u000ed\u0017\u000e]:f\u0013\u0011\t)-!/\u0003+M+'O\u001e7fi\u000e{g\u000e^3yi\"\u000bg\u000e\u001a7fe\"1\u0011\u0011\u001a\rA\u0002m\fA\u0001]1uQ\"9\u00111\u0014\rA\u0002\u00055\u0007\u0003\u0002;\t\u0003\u001f\u00042\u0001ZAi\t\u00151\u0007D1\u0001x\u0011\u001d\t)\u000b\u0007a\u0001\u0003OC\u0001\"a6\u0019!\u0003\u0005\ra_\u0001\tE\u0006\u001cX\rU1uQ\u0006q2M]3bi\u0016\u001cVM\u001d<mKRD\u0015M\u001c3mKJ$C-\u001a4bk2$H\u0005N\u000b\u0005\u0003;\f\t/\u0006\u0002\u0002`*\u001a10!\r\u0005\u000b\u0019L\"\u0019A<\u0015\u0011\u0005U\u0016Q]At\u0003SDa!!3\u001b\u0001\u0004Y\bB\u00020\u001b\u0001\u0004\t\u0019\n\u0003\u0004\u0002Xj\u0001\ra_\u0001\u0016GJ,\u0017\r^3SK\u0012L'/Z2u\u0011\u0006tG\r\\3s)1\t),a<\u0002t\u0006](1\u0001B\u0003\u0011\u0019\t\tp\u0007a\u0001w\u000691O]2QCRD\u0007BBA{7\u0001\u000710\u0001\u0005eKN$\b+\u0019;i\u0011%\tIp\u0007I\u0001\u0002\u0004\tY0\u0001\bcK\u001a|'/\u001a*fI&\u0014Xm\u0019;\u0011\u000be:\u0016,!@\u0011\u0007e\ny0C\u0002\u0003\u0002i\u0012A!\u00168ji\"A\u0011q[\u000e\u0011\u0002\u0003\u00071\u0010C\u0005\u0003\bm\u0001\n\u00111\u0001\u0003\n\u0005Y\u0001\u000e\u001e;q\u001b\u0016$\bn\u001c3t!\u0011a(1B>\n\t\t5\u0011\u0011\u0002\u0002\u0004'\u0016$\u0018aH2sK\u0006$XMU3eSJ,7\r\u001e%b]\u0012dWM\u001d\u0013eK\u001a\fW\u000f\u001c;%gU\u0011!1\u0003\u0016\u0005\u0003w\f\t$A\u0010de\u0016\fG/\u001a*fI&\u0014Xm\u0019;IC:$G.\u001a:%I\u00164\u0017-\u001e7uIQ\nqd\u0019:fCR,'+\u001a3je\u0016\u001cG\u000fS1oI2,'\u000f\n3fM\u0006,H\u000e\u001e\u00136+\t\u0011YB\u000b\u0003\u0003\n\u0005E\u0012aE2sK\u0006$Xm\u0015;bi&\u001c\u0007*\u00198eY\u0016\u0014HCBA[\u0005C\u0011)\u0003\u0003\u0004\u0003$}\u0001\ra_\u0001\re\u0016\u001cx.\u001e:dK\n\u000b7/\u001a\u0005\u0007\u0003\u0013|\u0002\u0019A>\u0002%\r\u0014X-\u0019;f!J|\u00070\u001f%b]\u0012dWM\u001d\u000b\u0005\u0003k\u0013Y\u0003C\u0004\u0003.\u0001\u0002\rAa\f\u0002\u001b%$Gk\\+j\u0003\u0012$'/Z:t!\u0015Itk\u001fB\u0019!\u0011I$1G>\n\u0007\tU\"H\u0001\u0004PaRLwN\\\u0001\u0011gR\f'\u000f\u001e&fiRL8+\u001a:wKJ$bBa\u000f\u0003B\t\u0015#q\nB-\u00057\u0012y\u0006E\u00026\u0005{I1Aa\u0010-\u0005)\u0019VM\u001d<fe&sgm\u001c\u0005\u0007\u0005\u0007\n\u0003\u0019A>\u0002\u0011!|7\u000f\u001e(b[\u0016DqAa\u0012\"\u0001\u0004\u0011I%\u0001\u0003q_J$\bcA\u001d\u0003L%\u0019!Q\n\u001e\u0003\u0007%sG\u000fC\u0004\u0003R\u0005\u0002\rAa\u0015\u0002\u0015M\u001cHn\u00149uS>t7\u000f\u0005\u0003\u0002*\nU\u0013b\u0001B,]\tQ1k\u0015'PaRLwN\\:\t\u000f\u0005\u0015\u0016\u00051\u0001\u0002(\"A!QL\u0011\u0011\u0002\u0003\u000710\u0001\u0006tKJ4XM\u001d(b[\u0016D\u0011B!\u0019\"!\u0003\u0005\rA!\u0013\u0002\u0011A|w\u000e\\*ju\u0016\f!d\u001d;beRTU\r\u001e;z'\u0016\u0014h/\u001a:%I\u00164\u0017-\u001e7uIU\n!d\u001d;beRTU\r\u001e;z'\u0016\u0014h/\u001a:%I\u00164\u0017-\u001e7uIY*\"A!\u001b+\t\t%\u0013\u0011G\u0001\u001bGJ,\u0017\r^3SK\u0012L'/Z2u\u0011R$\bo\u001d%b]\u0012dWM\u001d\u000b\u0007\u0005_\u0012yHa!\u0011\t\tE$1P\u0007\u0003\u0005gRAA!\u001e\u0003x\u00059\u0001.\u00198eY\u0016\u0014(\u0002\u0002B=\u0003w\u000baa]3sm\u0016\u0014\u0018\u0002\u0002B?\u0005g\u0012abQ8oi\u0016DH\u000fS1oI2,'\u000fC\u0004\u0003\u0002\u0012\u0002\rA!\u0013\u0002\u0015M,7-\u001e:f!>\u0014H\u000f\u0003\u0004\u0003\u0006\u0012\u0002\ra_\u0001\u0007g\u000eDW-\\3\u0002\u001d\r\u0014X-\u0019;f!J|\u00070_+S\u0013RQ!1\u0012BL\u00057\u0013yJ!)\u0011\t\t5%1S\u0007\u0003\u0005\u001fS1A!%N\u0003\rqW\r^\u0005\u0005\u0005+\u0013yIA\u0002V%&CaA!'&\u0001\u0004Y\u0018A\u00029sK\u001aL\u0007\u0010\u0003\u0004\u0003\u001e\u0016\u0002\ra_\u0001\u0007i\u0006\u0014x-\u001a;\t\r\u0005%W\u00051\u0001|\u0011\u0019\u0011\u0019+\na\u0001w\u0006)\u0011/^3ss\u0006I2M]3bi\u0016\u0004&o\u001c=z\u0019>\u001c\u0017\r^5p]\"+\u0017\rZ3s)\u001dY(\u0011\u0016BW\u0005cCaAa+'\u0001\u0004Y\u0018a\u00035fC\u0012,'OV1mk\u0016DaAa,'\u0001\u0004I\u0016!D2mS\u0016tGOU3rk\u0016\u001cH\u000fC\u0004\u00034\u001a\u0002\rAa#\u0002\u0013Q\f'oZ3u+JL\u0017!C1eI\u001aKG\u000e^3s)!\tiP!/\u0003<\n}\u0006b\u0002B;O\u0001\u0007\u0011Q\u0017\u0005\u0007\u0005{;\u0003\u0019A>\u0002\r\u0019LG\u000e^3s\u0011\u001d\u0011\tm\na\u0001\u0005\u0007\fa\u0001]1sC6\u001c\b#\u0002?\u0003Fn\\\u0018\u0002\u0002Bd\u0003\u0013\u00111!T1q\u0003%!WmY8eKV\u0013F\nF\u0003|\u0005\u001b\u0014\t\u000e\u0003\u0004\u0003P\"\u0002\ra_\u0001\u0004kJd\u0007B\u0002BjQ\u0001\u000710\u0001\u0005f]\u000e|G-\u001b8h\u0003E\u0019'/Z1uKJ+G-\u001b:fGR,&+\u0013\u000b\bw\ne'1\u001cBo\u0011\u0019\u0011))\u000ba\u0001w\"9!qI\u0015A\u0002\t%\u0003b\u0002BpS\u0001\u0007!\u0011]\u0001\be\u0016\fX/Z:u!\u0011\u0011\u0019O!:\u000e\u0005\t]\u0014\u0002\u0002Bt\u0005o\u0012qAU3rk\u0016\u001cH/\u0001\bu_ZK'\u000f^;bY\"{7\u000f^:\u0015\t\t5(1\u001f\t\u0005s\t=80C\u0002\u0003rj\u0012Q!\u0011:sCfDqA!>+\u0001\u0004\u001190\u0001\u0006d_:tWm\u0019;peN\u0004B!\u000fB}w&\u0019!1 \u001e\u0003\u0015q\u0012X\r]3bi\u0016$g\b"
)
public final class JettyUtils {
   public static String[] toVirtualHosts(final Seq connectors) {
      return JettyUtils$.MODULE$.toVirtualHosts(connectors);
   }

   public static void addFilter(final ServletContextHandler handler, final String filter, final Map params) {
      JettyUtils$.MODULE$.addFilter(handler, filter, params);
   }

   public static String createProxyLocationHeader(final String headerValue, final HttpServletRequest clientRequest, final URI targetUri) {
      return JettyUtils$.MODULE$.createProxyLocationHeader(headerValue, clientRequest, targetUri);
   }

   public static URI createProxyURI(final String prefix, final String target, final String path, final String query) {
      return JettyUtils$.MODULE$.createProxyURI(prefix, target, path, query);
   }

   public static int startJettyServer$default$6() {
      return JettyUtils$.MODULE$.startJettyServer$default$6();
   }

   public static String startJettyServer$default$5() {
      return JettyUtils$.MODULE$.startJettyServer$default$5();
   }

   public static ServerInfo startJettyServer(final String hostName, final int port, final SSLOptions sslOptions, final SparkConf conf, final String serverName, final int poolSize) {
      return JettyUtils$.MODULE$.startJettyServer(hostName, port, sslOptions, conf, serverName, poolSize);
   }

   public static ServletContextHandler createProxyHandler(final Function1 idToUiAddress) {
      return JettyUtils$.MODULE$.createProxyHandler(idToUiAddress);
   }

   public static ServletContextHandler createStaticHandler(final String resourceBase, final String path) {
      return JettyUtils$.MODULE$.createStaticHandler(resourceBase, path);
   }

   public static Set createRedirectHandler$default$5() {
      return JettyUtils$.MODULE$.createRedirectHandler$default$5();
   }

   public static String createRedirectHandler$default$4() {
      return JettyUtils$.MODULE$.createRedirectHandler$default$4();
   }

   public static Function1 createRedirectHandler$default$3() {
      return JettyUtils$.MODULE$.createRedirectHandler$default$3();
   }

   public static ServletContextHandler createRedirectHandler(final String srcPath, final String destPath, final Function1 beforeRedirect, final String basePath, final Set httpMethods) {
      return JettyUtils$.MODULE$.createRedirectHandler(srcPath, destPath, beforeRedirect, basePath, httpMethods);
   }

   public static ServletContextHandler createServletHandler(final String path, final HttpServlet servlet, final String basePath) {
      return JettyUtils$.MODULE$.createServletHandler(path, servlet, basePath);
   }

   public static String createServletHandler$default$4() {
      return JettyUtils$.MODULE$.createServletHandler$default$4();
   }

   public static ServletContextHandler createServletHandler(final String path, final ServletParams servletParams, final SparkConf conf, final String basePath) {
      return JettyUtils$.MODULE$.createServletHandler(path, servletParams, conf, basePath);
   }

   public static ServletParams textResponderToServlet(final Function1 responder) {
      return JettyUtils$.MODULE$.textResponderToServlet(responder);
   }

   public static ServletParams htmlResponderToServlet(final Function1 responder) {
      return JettyUtils$.MODULE$.htmlResponderToServlet(responder);
   }

   public static ServletParams jsonResponderToServlet(final Function1 responder) {
      return JettyUtils$.MODULE$.jsonResponderToServlet(responder);
   }

   public static String REDIRECT_CONNECTOR_NAME() {
      return JettyUtils$.MODULE$.REDIRECT_CONNECTOR_NAME();
   }

   public static String SPARK_CONNECTOR_NAME() {
      return JettyUtils$.MODULE$.SPARK_CONNECTOR_NAME();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return JettyUtils$.MODULE$.LogStringContext(sc);
   }

   public static class ServletParams {
      private final Function1 responder;
      private final String contentType;
      private final Function1 extractFn;

      public Function1 responder() {
         return this.responder;
      }

      public String contentType() {
         return this.contentType;
      }

      public Function1 extractFn() {
         return this.extractFn;
      }

      public ServletParams(final Function1 responder, final String contentType, final Function1 extractFn) {
         this.responder = responder;
         this.contentType = contentType;
         this.extractFn = extractFn;
      }
   }

   public static class ServletParams$ {
      public static final ServletParams$ MODULE$ = new ServletParams$();

      public Function1 $lessinit$greater$default$3() {
         return (in) -> in.toString();
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}

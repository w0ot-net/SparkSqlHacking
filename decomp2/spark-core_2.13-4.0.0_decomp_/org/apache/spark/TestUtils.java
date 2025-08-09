package org.apache.spark;

import java.io.File;
import java.net.URL;
import org.apache.spark.scheduler.SparkListener;
import org.apache.spark.util.SparkTestUtils;
import org.json4s.JValue;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\tmxAB\u0016-\u0011\u0003a#G\u0002\u00045Y!\u0005A&\u000e\u0005\u0006\u0005\u0006!\t\u0001\u0012\u0005\u0006\u000b\u0006!\tA\u0012\u0005\ba\u0006\t\n\u0011\"\u0001r\u0011\u001da\u0018!%A\u0005\u0002uD\u0001b`\u0001\u0012\u0002\u0013\u0005\u0011\u0011\u0001\u0005\b\u0003\u000b\tA\u0011AA\u0004\u0011%\t\u0019#AI\u0001\n\u0003\t)\u0003C\u0004\u0002*\u0005!\t!a\u000b\t\u0013\u0005\r\u0013!%A\u0005\u0002\u0005\u0015\u0003\"CA%\u0003E\u0005I\u0011AA#\u0011\u001d\tY%\u0001C\u0001\u0003\u001bBq!a\u001c\u0002\t\u0003\t\t\bC\u0004\u0002|\u0005!\t!! \t\u0013\u0005m\u0016!%A\u0005\u0002\u0005u\u0006bBAc\u0003\u0011\u0005\u0011q\u0019\u0005\n\u0003\u001b\f!\u0019!C\u0001\u0003\u001fDq!!5\u0002A\u0003%Q\fC\u0004\u0002T\u0006!\t!!6\t\u000f\u0005]\u0017\u0001\"\u0003\u0002Z\"9\u0011Q^\u0001\u0005\u0002\u0005=\bbBA{\u0003\u0011\u0005\u0011q\u001f\u0005\t\u0005\u000b\t\u0011\u0013!C\u0001c\"A!qA\u0001\u0012\u0002\u0013\u0005Q\u0010C\u0004\u0003\n\u0005!\tAa\u0003\t\u0011\tM\u0011!%A\u0005\u0002ED\u0001B!\u0006\u0002#\u0003%\t! \u0005\b\u0005/\tA\u0011\u0001B\r\u0011!\u0011\t#AI\u0001\n\u0003\t\b\u0002\u0003B\u0012\u0003E\u0005I\u0011A?\t\u000f\t\u0015\u0012\u0001\"\u0001\u0003(!I!\u0011K\u0001\u0012\u0002\u0013\u0005!1\u000b\u0005\n\u0005/\n\u0011\u0013!C\u0001\u00053BqA!\u0018\u0002\t\u0003\u0011y\u0006C\u0004\u0003\u0004\u0006!\tA!\"\t\u0011\tE\u0015!%A\u0005\u0002ED\u0001Ba%\u0002\t\u0003a#Q\u0013\u0005\b\u0005O\u000bA\u0011\u0001BU\u0011\u001d\u0011y+\u0001C\u0001\u0005cCqA!0\u0002\t\u0003\u0011y\fC\u0004\u0003H\u0006!\tA!3\t\u000f\t=\u0018\u0001\"\u0001\u0003r\u0006IA+Z:u+RLGn\u001d\u0006\u0003[9\nQa\u001d9be.T!a\f\u0019\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\t\u0014aA8sOB\u00111'A\u0007\u0002Y\tIA+Z:u+RLGn]\n\u0004\u0003Yb\u0004CA\u001c;\u001b\u0005A$\"A\u001d\u0002\u000bM\u001c\u0017\r\\1\n\u0005mB$AB!osJ+g\r\u0005\u0002>\u00016\taH\u0003\u0002@Y\u0005!Q\u000f^5m\u0013\t\teH\u0001\bTa\u0006\u00148\u000eV3tiV#\u0018\u000e\\:\u0002\rqJg.\u001b;?\u0007\u0001!\u0012AM\u0001\u0015GJ,\u0017\r^3KCJ<\u0016\u000e\u001e5DY\u0006\u001c8/Z:\u0015\u000b\u001d{UmZ7\u0011\u0005!kU\"A%\u000b\u0005)[\u0015a\u00018fi*\tA*\u0001\u0003kCZ\f\u0017B\u0001(J\u0005\r)&\u000b\u0014\u0005\u0006!\u000e\u0001\r!U\u0001\u000bG2\f7o\u001d(b[\u0016\u001c\bc\u0001*[;:\u00111\u000b\u0017\b\u0003)^k\u0011!\u0016\u0006\u0003-\u000e\u000ba\u0001\u0010:p_Rt\u0014\"A\u001d\n\u0005eC\u0014a\u00029bG.\fw-Z\u0005\u00037r\u00131aU3r\u0015\tI\u0006\b\u0005\u0002_E:\u0011q\f\u0019\t\u0003)bJ!!\u0019\u001d\u0002\rA\u0013X\rZ3g\u0013\t\u0019GM\u0001\u0004TiJLgn\u001a\u0006\u0003CbBqAZ\u0002\u0011\u0002\u0003\u0007Q,A\u0007u_N#(/\u001b8h-\u0006dW/\u001a\u0005\bQ\u000e\u0001\n\u00111\u0001j\u0003I\u0019G.Y:t\u001d\u0006lWm],ji\"\u0014\u0015m]3\u0011\u0007IS&\u000e\u0005\u00038Wvk\u0016B\u000179\u0005\u0019!V\u000f\u001d7fe!9an\u0001I\u0001\u0002\u0004y\u0017!D2mCN\u001c\b/\u0019;i+Jd7\u000fE\u0002S5\u001e\u000bad\u0019:fCR,'*\u0019:XSRD7\t\\1tg\u0016\u001cH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003IT#!X:,\u0003Q\u0004\"!\u001e>\u000e\u0003YT!a\u001e=\u0002\u0013Ut7\r[3dW\u0016$'BA=9\u0003)\tgN\\8uCRLwN\\\u0005\u0003wZ\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003y\u0019'/Z1uK*\u000b'oV5uQ\u000ec\u0017m]:fg\u0012\"WMZ1vYR$3'F\u0001\u007fU\tI7/\u0001\u0010de\u0016\fG/\u001a&be^KG\u000f[\"mCN\u001cXm\u001d\u0013eK\u001a\fW\u000f\u001c;%iU\u0011\u00111\u0001\u0016\u0003_N\f!c\u0019:fCR,'*\u0019:XSRDg)\u001b7fgR)q)!\u0003\u0002\u0014!9\u00111B\u0004A\u0002\u00055\u0011!\u00024jY\u0016\u001c\b#\u00020\u0002\u0010uk\u0016bAA\tI\n\u0019Q*\u00199\t\u0013\u0005Uq\u0001%AA\u0002\u0005]\u0011a\u00013jeB!\u0011\u0011DA\u0010\u001b\t\tYBC\u0002\u0002\u001e-\u000b!![8\n\t\u0005\u0005\u00121\u0004\u0002\u0005\r&dW-\u0001\u000fde\u0016\fG/\u001a&be^KG\u000f\u001b$jY\u0016\u001cH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u0005\u001d\"fAA\fg\u0006I1M]3bi\u0016T\u0015M\u001d\u000b\n\u000f\u00065\u0012\u0011GA\u001b\u0003\u007fAq!a\u0003\n\u0001\u0004\ty\u0003\u0005\u0003S5\u0006]\u0001bBA\u001a\u0013\u0001\u0007\u0011qC\u0001\bU\u0006\u0014h)\u001b7f\u0011%\t9$\u0003I\u0001\u0002\u0004\tI$A\beSJ,7\r^8ssB\u0013XMZ5y!\u00119\u00141H/\n\u0007\u0005u\u0002H\u0001\u0004PaRLwN\u001c\u0005\n\u0003\u0003J\u0001\u0013!a\u0001\u0003s\t\u0011\"\\1j]\u000ec\u0017m]:\u0002'\r\u0014X-\u0019;f\u0015\u0006\u0014H\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\u0005\u001d#fAA\u001dg\u0006\u00192M]3bi\u0016T\u0015M\u001d\u0013eK\u001a\fW\u000f\u001c;%i\u0005i\u0011m]:feR\u001c\u0006/\u001b7mK\u0012$b!a\u0014\u0002b\u0005-D\u0003BA)\u0003/\u00022aNA*\u0013\r\t)\u0006\u000f\u0002\u0005+:LG\u000f\u0003\u0005\u0002Z1!\t\u0019AA.\u0003\u0011\u0011w\u000eZ=\u0011\u000b]\ni&!\u0015\n\u0007\u0005}\u0003H\u0001\u0005=Eft\u0017-\\3?\u0011\u001d\t\u0019\u0007\u0004a\u0001\u0003K\n!a]2\u0011\u0007M\n9'C\u0002\u0002j1\u0012Ab\u00159be.\u001cuN\u001c;fqRDa!!\u001c\r\u0001\u0004i\u0016AC5eK:$\u0018NZ5fe\u0006\u0001\u0012m]:feRtu\u000e^*qS2dW\r\u001a\u000b\u0007\u0003g\n9(!\u001f\u0015\t\u0005E\u0013Q\u000f\u0005\t\u00033jA\u00111\u0001\u0002\\!9\u00111M\u0007A\u0002\u0005\u0015\u0004BBA7\u001b\u0001\u0007Q,\u0001\nbgN,'\u000f^#yG\u0016\u0004H/[8o\u001bN<W\u0003BA@\u0003/#\u0002\"!!\u0002*\u00065\u0016\u0011\u0017\u000b\u0005\u0003#\n\u0019\tC\u0005\u0002\u0006:\t\t\u0011q\u0001\u0002\b\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\r\u0005%\u0015qRAJ\u001b\t\tYIC\u0002\u0002\u000eb\nqA]3gY\u0016\u001cG/\u0003\u0003\u0002\u0012\u0006-%\u0001C\"mCN\u001cH+Y4\u0011\t\u0005U\u0015q\u0013\u0007\u0001\t\u001d\tIJ\u0004b\u0001\u00037\u0013\u0011!R\t\u0005\u0003;\u000b\u0019\u000bE\u00028\u0003?K1!!)9\u0005\u001dqu\u000e\u001e5j]\u001e\u00042AUAS\u0013\r\t9\u000b\u0018\u0002\n)\"\u0014xn^1cY\u0016Dq!a+\u000f\u0001\u0004\t\u0019+A\u0005fq\u000e,\u0007\u000f^5p]\"1\u0011q\u0016\bA\u0002u\u000b1!\\:h\u0011%\t\u0019L\u0004I\u0001\u0002\u0004\t),\u0001\u0006jO:|'/Z\"bg\u0016\u00042aNA\\\u0013\r\tI\f\u000f\u0002\b\u0005>|G.Z1o\u0003q\t7o]3si\u0016C8-\u001a9uS>tWj]4%I\u00164\u0017-\u001e7uIM*B!a0\u0002DV\u0011\u0011\u0011\u0019\u0016\u0004\u0003k\u001bHaBAM\u001f\t\u0007\u00111T\u0001\u0015i\u0016\u001cHoQ8n[\u0006tG-\u0011<bS2\f'\r\\3\u0015\t\u0005U\u0016\u0011\u001a\u0005\u0007\u0003\u0017\u0004\u0002\u0019A/\u0002\u000f\r|W.\\1oI\u0006iR.\u001b8j[Vl\u0007+\u001f;i_:\u001cV\u000f\u001d9peR,GMV3sg&|g.F\u0001^\u0003yi\u0017N\\5nk6\u0004\u0016\u0010\u001e5p]N+\b\u000f]8si\u0016$g+\u001a:tS>t\u0007%\u0001\rjgBKH\u000f[8o-\u0016\u00148/[8o\u0003Z\f\u0017\u000e\\1cY\u0016,\"!!.\u0002-%\u001c\b+\u001f;i_:4VM]:j_:\fE\u000fT3bgR$\u0002\"!.\u0002\\\u0006\u0015\u0018\u0011\u001e\u0005\b\u0003;$\u0002\u0019AAp\u0003\u0015i\u0017M[8s!\r9\u0014\u0011]\u0005\u0004\u0003GD$aA%oi\"9\u0011q\u001d\u000bA\u0002\u0005}\u0017!B7j]>\u0014\bbBAv)\u0001\u0007\u0011q\\\u0001\ne\u00164XM]:j_:\fQdZ3u\u0003\n\u001cx\u000e\\;uKB\u000bG\u000f\u001b$s_6,\u00050Z2vi\u0006\u0014G.\u001a\u000b\u0005\u0003s\t\t\u0010\u0003\u0004\u0002tV\u0001\r!X\u0001\u000bKb,7-\u001e;bE2,\u0017\u0001\u00055uiB\u0014Vm\u001d9p]N,7i\u001c3f)!\ty.!?\u0002~\n\u0005\u0001BBA~-\u0001\u0007q)A\u0002ve2D\u0001\"a@\u0017!\u0003\u0005\r!X\u0001\u0007[\u0016$\bn\u001c3\t\u0011\t\ra\u0003%AA\u0002%\fq\u0001[3bI\u0016\u00148/\u0001\u000eiiR\u0004(+Z:q_:\u001cXmQ8eK\u0012\"WMZ1vYR$#'\u0001\u000eiiR\u0004(+Z:q_:\u001cXmQ8eK\u0012\"WMZ1vYR$3'A\u0006sK\u0012L'/Z2u+JdGcB/\u0003\u000e\t=!\u0011\u0003\u0005\u0007\u0003wL\u0002\u0019A$\t\u0011\u0005}\u0018\u0004%AA\u0002uC\u0001Ba\u0001\u001a!\u0003\u0005\r![\u0001\u0016e\u0016$\u0017N]3diV\u0013H\u000e\n3fM\u0006,H\u000e\u001e\u00133\u0003U\u0011X\rZ5sK\u000e$XK\u001d7%I\u00164\u0017-\u001e7uIM\n1\u0003\u001b;uaJ+7\u000f]8og\u0016lUm]:bO\u0016$r!\u0018B\u000e\u0005;\u0011y\u0002\u0003\u0004\u0002|r\u0001\ra\u0012\u0005\t\u0003\u007fd\u0002\u0013!a\u0001;\"A!1\u0001\u000f\u0011\u0002\u0003\u0007\u0011.A\u000fiiR\u0004(+Z:q_:\u001cX-T3tg\u0006<W\r\n3fM\u0006,H\u000e\u001e\u00133\u0003uAG\u000f\u001e9SKN\u0004xN\\:f\u001b\u0016\u001c8/Y4fI\u0011,g-Y;mi\u0012\u001a\u0014AE<ji\"DE\u000f\u001e9D_:tWm\u0019;j_:,BA!\u000b\u00030QA!1\u0006B&\u0005\u001b\u0012y\u0005\u0006\u0003\u0003.\tm\u0002\u0003BAK\u0005_!qA!\r \u0005\u0004\u0011\u0019DA\u0001U#\u0011\tiJ!\u000e\u0011\u0007]\u00129$C\u0002\u0003:a\u00121!\u00118z\u0011\u001d\u0011id\ba\u0001\u0005\u007f\t!A\u001a8\u0011\u000f]\u0012\tE!\u0012\u0003.%\u0019!1\t\u001d\u0003\u0013\u0019+hn\u0019;j_:\f\u0004c\u0001%\u0003H%\u0019!\u0011J%\u0003#!#H\u000f]+S\u0019\u000e{gN\\3di&|g\u000e\u0003\u0004\u0002|~\u0001\ra\u0012\u0005\t\u0003\u007f|\u0002\u0013!a\u0001;\"A!1A\u0010\u0011\u0002\u0003\u0007\u0011.\u0001\u000fxSRD\u0007\n\u001e;q\u0007>tg.Z2uS>tG\u0005Z3gCVdG\u000f\n\u001a\u0016\u0007E\u0014)\u0006B\u0004\u00032\u0001\u0012\rAa\r\u00029]LG\u000f\u001b%uiB\u001cuN\u001c8fGRLwN\u001c\u0013eK\u001a\fW\u000f\u001c;%gU\u0019QPa\u0017\u0005\u000f\tE\u0012E1\u0001\u00034\u0005aq/\u001b;i\u0019&\u001cH/\u001a8feV!!\u0011\rB6)\u0019\u0011\u0019G! \u0003\u0000Q!\u0011\u0011\u000bB3\u0011\u001d\tIF\ta\u0001\u0005O\u0002ra\u000eB!\u0005S\n\t\u0006\u0005\u0003\u0002\u0016\n-Da\u0002B7E\t\u0007!q\u000e\u0002\u0002\u0019F!\u0011Q\u0014B9!\u0011\u0011\u0019H!\u001f\u000e\u0005\tU$b\u0001B<Y\u0005I1o\u00195fIVdWM]\u0005\u0005\u0005w\u0012)HA\u0007Ta\u0006\u00148\u000eT5ti\u0016tWM\u001d\u0005\b\u0003G\u0012\u0003\u0019AA3\u0011\u001d\u0011\tI\ta\u0001\u0005S\n\u0001\u0002\\5ti\u0016tWM]\u0001\u000fo&$\b\u000e\u0013;uaN+'O^3s)\u0011\u00119I!$\u0015\t\u0005E#\u0011\u0012\u0005\b\u00033\u001a\u0003\u0019\u0001BF!\u00199$\u0011I$\u0002R!A!qR\u0012\u0011\u0002\u0003\u0007Q,\u0001\u0006sKN\u0014\u0015m]3ESJ\f\u0001d^5uQ\"#H\u000f]*feZ,'\u000f\n3fM\u0006,H\u000e\u001e\u00132\u0003Q9\u0018-\u001b;V]RLG.\u0012=fGV$xN]:VaRA\u0011\u0011\u000bBL\u00053\u0013i\nC\u0004\u0002d\u0015\u0002\r!!\u001a\t\u000f\tmU\u00051\u0001\u0002`\u0006aa.^7Fq\u0016\u001cW\u000f^8sg\"9!qT\u0013A\u0002\t\u0005\u0016a\u0002;j[\u0016|W\u000f\u001e\t\u0004o\t\r\u0016b\u0001BSq\t!Aj\u001c8h\u0003A\u0019wN\u001c4jOR+7\u000f\u001e'pORR'\u0007\u0006\u0003\u0002R\t-\u0006B\u0002BWM\u0001\u0007Q,A\u0003mKZ,G.A\u0007sK\u000e,(o]5wK2K7\u000f\u001e\u000b\u0005\u0005g\u0013I\fE\u00038\u0005k\u000b9\"C\u0002\u00038b\u0012Q!\u0011:sCfDqAa/(\u0001\u0004\t9\"A\u0001g\u00035a\u0017n\u001d;ESJ,7\r^8ssR!!\u0011\u0019Bb!\u00119$QW/\t\u000f\t\u0015\u0007\u00061\u0001\u0002\u0018\u0005!\u0001/\u0019;i\u0003I\u0019'/Z1uKR+W\u000e\u001d&t_:4\u0015\u000e\\3\u0015\u000fu\u0013YM!4\u0003R\"9\u0011QC\u0015A\u0002\u0005]\u0001B\u0002BhS\u0001\u0007Q,\u0001\u0004qe\u00164\u0017\u000e\u001f\u0005\b\u0005'L\u0003\u0019\u0001Bk\u0003%Q7o\u001c8WC2,X\r\u0005\u0003\u0003X\n%h\u0002\u0002Bm\u0005GtAAa7\u0003`:\u0019AK!8\n\u0003EJ1A!91\u0003\u0019Q7o\u001c85g&!!Q\u001dBt\u0003\u001dQ5o\u001c8B'RS1A!91\u0013\u0011\u0011YO!<\u0003\r)3\u0016\r\\;f\u0015\u0011\u0011)Oa:\u0002E\r\u0014X-\u0019;f)\u0016l\u0007oU2sSB$x+\u001b;i\u000bb\u0004Xm\u0019;fI>+H\u000f];u)\u001di&1\u001fB{\u0005oDq!!\u0006+\u0001\u0004\t9\u0002\u0003\u0004\u0003P*\u0002\r!\u0018\u0005\u0007\u0005sT\u0003\u0019A/\u0002\r=,H\u000f];u\u0001"
)
public final class TestUtils {
   public static String createTempScriptWithExpectedOutput(final File dir, final String prefix, final String output) {
      return TestUtils$.MODULE$.createTempScriptWithExpectedOutput(dir, prefix, output);
   }

   public static String createTempJsonFile(final File dir, final String prefix, final JValue jsonValue) {
      return TestUtils$.MODULE$.createTempJsonFile(dir, prefix, jsonValue);
   }

   public static String[] listDirectory(final File path) {
      return TestUtils$.MODULE$.listDirectory(path);
   }

   public static File[] recursiveList(final File f) {
      return TestUtils$.MODULE$.recursiveList(f);
   }

   public static void configTestLog4j2(final String level) {
      TestUtils$.MODULE$.configTestLog4j2(level);
   }

   public static String withHttpServer$default$1() {
      return TestUtils$.MODULE$.withHttpServer$default$1();
   }

   public static void withHttpServer(final String resBaseDir, final Function1 body) {
      TestUtils$.MODULE$.withHttpServer(resBaseDir, body);
   }

   public static void withListener(final SparkContext sc, final SparkListener listener, final Function1 body) {
      TestUtils$.MODULE$.withListener(sc, listener, body);
   }

   public static Seq withHttpConnection$default$3() {
      return TestUtils$.MODULE$.withHttpConnection$default$3();
   }

   public static String withHttpConnection$default$2() {
      return TestUtils$.MODULE$.withHttpConnection$default$2();
   }

   public static Object withHttpConnection(final URL url, final String method, final Seq headers, final Function1 fn) {
      return TestUtils$.MODULE$.withHttpConnection(url, method, headers, fn);
   }

   public static Seq httpResponseMessage$default$3() {
      return TestUtils$.MODULE$.httpResponseMessage$default$3();
   }

   public static String httpResponseMessage$default$2() {
      return TestUtils$.MODULE$.httpResponseMessage$default$2();
   }

   public static String httpResponseMessage(final URL url, final String method, final Seq headers) {
      return TestUtils$.MODULE$.httpResponseMessage(url, method, headers);
   }

   public static Seq redirectUrl$default$3() {
      return TestUtils$.MODULE$.redirectUrl$default$3();
   }

   public static String redirectUrl$default$2() {
      return TestUtils$.MODULE$.redirectUrl$default$2();
   }

   public static String redirectUrl(final URL url, final String method, final Seq headers) {
      return TestUtils$.MODULE$.redirectUrl(url, method, headers);
   }

   public static Seq httpResponseCode$default$3() {
      return TestUtils$.MODULE$.httpResponseCode$default$3();
   }

   public static String httpResponseCode$default$2() {
      return TestUtils$.MODULE$.httpResponseCode$default$2();
   }

   public static int httpResponseCode(final URL url, final String method, final Seq headers) {
      return TestUtils$.MODULE$.httpResponseCode(url, method, headers);
   }

   public static Option getAbsolutePathFromExecutable(final String executable) {
      return TestUtils$.MODULE$.getAbsolutePathFromExecutable(executable);
   }

   public static boolean isPythonVersionAvailable() {
      return TestUtils$.MODULE$.isPythonVersionAvailable();
   }

   public static String minimumPythonSupportedVersion() {
      return TestUtils$.MODULE$.minimumPythonSupportedVersion();
   }

   public static boolean testCommandAvailable(final String command) {
      return TestUtils$.MODULE$.testCommandAvailable(command);
   }

   public static boolean assertExceptionMsg$default$3() {
      return TestUtils$.MODULE$.assertExceptionMsg$default$3();
   }

   public static void assertExceptionMsg(final Throwable exception, final String msg, final boolean ignoreCase, final ClassTag evidence$1) {
      TestUtils$.MODULE$.assertExceptionMsg(exception, msg, ignoreCase, evidence$1);
   }

   public static void assertNotSpilled(final SparkContext sc, final String identifier, final Function0 body) {
      TestUtils$.MODULE$.assertNotSpilled(sc, identifier, body);
   }

   public static void assertSpilled(final SparkContext sc, final String identifier, final Function0 body) {
      TestUtils$.MODULE$.assertSpilled(sc, identifier, body);
   }

   public static Option createJar$default$4() {
      return TestUtils$.MODULE$.createJar$default$4();
   }

   public static Option createJar$default$3() {
      return TestUtils$.MODULE$.createJar$default$3();
   }

   public static URL createJar(final Seq files, final File jarFile, final Option directoryPrefix, final Option mainClass) {
      return TestUtils$.MODULE$.createJar(files, jarFile, directoryPrefix, mainClass);
   }

   public static File createJarWithFiles$default$2() {
      return TestUtils$.MODULE$.createJarWithFiles$default$2();
   }

   public static URL createJarWithFiles(final Map files, final File dir) {
      return TestUtils$.MODULE$.createJarWithFiles(files, dir);
   }

   public static Seq createJarWithClasses$default$4() {
      return TestUtils$.MODULE$.createJarWithClasses$default$4();
   }

   public static Seq createJarWithClasses$default$3() {
      return TestUtils$.MODULE$.createJarWithClasses$default$3();
   }

   public static String createJarWithClasses$default$2() {
      return TestUtils$.MODULE$.createJarWithClasses$default$2();
   }

   public static URL createJarWithClasses(final Seq classNames, final String toStringValue, final Seq classNamesWithBase, final Seq classpathUrls) {
      return TestUtils$.MODULE$.createJarWithClasses(classNames, toStringValue, classNamesWithBase, classpathUrls);
   }

   public static Option createCompiledClass$default$8() {
      return TestUtils$.MODULE$.createCompiledClass$default$8();
   }

   public static String createCompiledClass$default$7() {
      return TestUtils$.MODULE$.createCompiledClass$default$7();
   }

   public static Seq createCompiledClass$default$6() {
      return TestUtils$.MODULE$.createCompiledClass$default$6();
   }

   public static Seq createCompiledClass$default$5() {
      return TestUtils$.MODULE$.createCompiledClass$default$5();
   }

   public static String createCompiledClass$default$4() {
      return TestUtils$.MODULE$.createCompiledClass$default$4();
   }

   public static String createCompiledClass$default$3() {
      return TestUtils$.MODULE$.createCompiledClass$default$3();
   }

   public static File createCompiledClass(final String className, final File destDir, final String toStringValue, final String baseClass, final Seq classpathUrls, final Seq implementsClasses, final String extraCodeBody, final Option packageName) {
      return TestUtils$.MODULE$.createCompiledClass(className, destDir, toStringValue, baseClass, classpathUrls, implementsClasses, extraCodeBody, packageName);
   }

   public static File createCompiledClass(final String className, final File destDir, final SparkTestUtils.JavaSourceFromString sourceFile, final Seq classpathUrls) {
      return TestUtils$.MODULE$.createCompiledClass(className, destDir, sourceFile, classpathUrls);
   }
}

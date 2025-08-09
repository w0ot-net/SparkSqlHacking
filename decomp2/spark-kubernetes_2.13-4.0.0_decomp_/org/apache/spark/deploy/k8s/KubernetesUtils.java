package org.apache.spark.deploy.k8s;

import io.fabric8.kubernetes.api.model.ContainerStatus;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.apache.spark.SparkConf;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Unstable;
import org.apache.spark.internal.Logging;
import org.apache.spark.util.Clock;
import scala.Option;
import scala.StringContext;
import scala.collection.Iterable;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@Unstable
@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\r%t!\u0002\u0014(\u0011\u0003\u0011d!\u0002\u001b(\u0011\u0003)\u0004\"\u0002\"\u0002\t\u0003\u0019\u0005b\u0002#\u0002\u0005\u0004%I!\u0012\u0005\u0007\u0019\u0006\u0001\u000b\u0011\u0002$\t\u00115\u000b\u0001R1A\u0005\n9CQaV\u0001\u0005\u0002aCQ\u0001_\u0001\u0005\u0002eDq!!\u000f\u0002\t\u0003\tY\u0004C\u0004\u0002Z\u0005!\t!a\u0017\t\u000f\u0005m\u0014\u0001\"\u0001\u0002~!9\u0011QW\u0001\u0005\u0002\u0005]\u0006bBAi\u0003\u0011\u0005\u00111\u001b\u0005\b\u0003?\fA\u0011AAq\u0011%\u0011Y!AI\u0001\n\u0003\u0011i\u0001C\u0004\u0003\"\u0005!\tAa\t\t\u000f\t%\u0012\u0001\"\u0001\u0003,!I!QG\u0001\u0012\u0002\u0013\u0005!Q\u0002\u0005\b\u0005o\tA\u0011\u0001B\u001d\u0011\u001d\u00119%\u0001C\u0001\u0005\u0013BqA!\u0015\u0002\t\u0003\u0011\u0019\u0006C\u0005\u0003b\u0005\t\n\u0011\"\u0001\u0003d!9!qM\u0001\u0005\u0002\t%\u0004b\u0002B>\u0003\u0011\u0005!Q\u0010\u0005\n\u0005\u001f\u000b\u0011\u0013!C\u0001\u0005#CqA!&\u0002\t\u0013\u00119\nC\u0004\u00030\u0006!\tA!-\t\u000f\te\u0016\u0001\"\u0001\u0003<\"I!1Z\u0001\u0012\u0002\u0013\u0005!\u0011\u0013\u0005\b\u0005\u001b\fA\u0011\u0001Bh\u0011%\u00119.AI\u0001\n\u0003\u0011\t\nC\u0004\u0003Z\u0006!IAa7\t\u0013\r\u0015\u0011!%A\u0005\n\r\u001d\u0001\"CB\u0006\u0003E\u0005I\u0011BB\u0004\u0011\u001d\u0019i!\u0001C\u0001\u0007\u001fAqaa\u0007\u0002\t\u0003\u0019i\u0002C\u0004\u00040\u0005!\ta!\r\t\u000f\r\u0015\u0013\u0001\"\u0001\u0004H\u0005y1*\u001e2fe:,G/Z:Vi&d7O\u0003\u0002)S\u0005\u00191\u000eO:\u000b\u0005)Z\u0013A\u00023fa2|\u0017P\u0003\u0002-[\u0005)1\u000f]1sW*\u0011afL\u0001\u0007CB\f7\r[3\u000b\u0003A\n1a\u001c:h\u0007\u0001\u0001\"aM\u0001\u000e\u0003\u001d\u0012qbS;cKJtW\r^3t+RLGn]\n\u0004\u0003Yb\u0004CA\u001c;\u001b\u0005A$\"A\u001d\u0002\u000bM\u001c\u0017\r\\1\n\u0005mB$AB!osJ+g\r\u0005\u0002>\u00016\taH\u0003\u0002@W\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002B}\t9Aj\\4hS:<\u0017A\u0002\u001fj]&$h\bF\u00013\u0003-\u0019\u0018p\u001d;f[\u000ecwnY6\u0016\u0003\u0019\u0003\"a\u0012&\u000e\u0003!S!!S\u0016\u0002\tU$\u0018\u000e\\\u0005\u0003\u0017\"\u00131bU=ti\u0016l7\t\\8dW\u0006a1/_:uK6\u001cEn\\2lA\u0005\u0019!KT$\u0016\u0003=\u0003\"\u0001U+\u000e\u0003ES!AU*\u0002\u0011M,7-\u001e:jifT\u0011\u0001V\u0001\u0005U\u00064\u0018-\u0003\u0002W#\na1+Z2ve\u0016\u0014\u0016M\u001c3p[\u0006Q\u0002/\u0019:tKB\u0013XMZ5yK\u0012\\U-\u001f,bYV,\u0007+Y5sgR\u0019\u0011lZ7\u0011\ti\u000bG\r\u001a\b\u00037~\u0003\"\u0001\u0018\u001d\u000e\u0003uS!AX\u0019\u0002\rq\u0012xn\u001c;?\u0013\t\u0001\u0007(\u0001\u0004Qe\u0016$WMZ\u0005\u0003E\u000e\u00141!T1q\u0015\t\u0001\u0007\b\u0005\u0002[K&\u0011am\u0019\u0002\u0007'R\u0014\u0018N\\4\t\u000b!4\u0001\u0019A5\u0002\u0013M\u0004\u0018M]6D_:4\u0007C\u00016l\u001b\u0005Y\u0013B\u00017,\u0005%\u0019\u0006/\u0019:l\u0007>tg\rC\u0003o\r\u0001\u0007A-\u0001\u0004qe\u00164\u0017\u000e\u001f\u0015\u0004\rA4\bCA9u\u001b\u0005\u0011(BA:,\u0003)\tgN\\8uCRLwN\\\u0005\u0003kJ\u0014QaU5oG\u0016\f\u0013a^\u0001\u0006e9\u001ad\u0006M\u0001\u001ce\u0016\fX/\u001b:f\u0005>$\bn\u0014:OK&$\b.\u001a:EK\u001aLg.\u001a3\u0015\u0011il\u0018QDA\u0016\u0003_\u0001\"aN>\n\u0005qD$\u0001B+oSRDQA`\u0004A\u0002}\fAa\u001c9ucA\"\u0011\u0011AA\u0006!\u00159\u00141AA\u0004\u0013\r\t)\u0001\u000f\u0002\u0007\u001fB$\u0018n\u001c8\u0011\t\u0005%\u00111\u0002\u0007\u0001\t-\ti!`A\u0001\u0002\u0003\u0015\t!a\u0004\u0003\u0007}#\u0013'\u0005\u0003\u0002\u0012\u0005]\u0001cA\u001c\u0002\u0014%\u0019\u0011Q\u0003\u001d\u0003\u000f9{G\u000f[5oOB\u0019q'!\u0007\n\u0007\u0005m\u0001HA\u0002B]fDq!a\b\b\u0001\u0004\t\t#\u0001\u0003paR\u0014\u0004\u0007BA\u0012\u0003O\u0001RaNA\u0002\u0003K\u0001B!!\u0003\u0002(\u0011a\u0011\u0011FA\u000f\u0003\u0003\u0005\tQ!\u0001\u0002\u0010\t\u0019q\f\n\u001a\t\r\u00055r\u00011\u0001e\u0003q)'O]'fgN\fw-Z,iK:4\u0015N]:u\u0013Nl\u0015n]:j]\u001eDa!!\r\b\u0001\u0004!\u0017!H3se6+7o]1hK^CWM\\*fG>tG-S:NSN\u001c\u0018N\\4)\t\u001d\u0001\u0018QG\u0011\u0003\u0003o\tQa\r\u00181]A\nQD]3rk&\u0014XmU3d_:$\u0017J\u001a$jeN$\u0018j\u001d#fM&tW\r\u001a\u000b\bu\u0006u\u0012\u0011JA+\u0011\u0019q\b\u00021\u0001\u0002@A\"\u0011\u0011IA#!\u00159\u00141AA\"!\u0011\tI!!\u0012\u0005\u0019\u0005\u001d\u0013QHA\u0001\u0002\u0003\u0015\t!a\u0004\u0003\u0007}#3\u0007C\u0004\u0002 !\u0001\r!a\u00131\t\u00055\u0013\u0011\u000b\t\u0006o\u0005\r\u0011q\n\t\u0005\u0003\u0013\t\t\u0006\u0002\u0007\u0002T\u0005%\u0013\u0011!A\u0001\u0006\u0003\tyAA\u0002`IQBa!!\r\t\u0001\u0004!\u0007\u0006\u0002\u0005q\u0003k\t!C]3rk&\u0014XMT1oI\u0012+g-\u001b8fIR9!0!\u0018\u0002j\u0005U\u0004B\u0002@\n\u0001\u0004\ty\u0006\r\u0003\u0002b\u0005\u0015\u0004#B\u001c\u0002\u0004\u0005\r\u0004\u0003BA\u0005\u0003K\"A\"a\u001a\u0002^\u0005\u0005\t\u0011!B\u0001\u0003\u001f\u00111a\u0018\u00136\u0011\u001d\ty\"\u0003a\u0001\u0003W\u0002D!!\u001c\u0002rA)q'a\u0001\u0002pA!\u0011\u0011BA9\t1\t\u0019(!\u001b\u0002\u0002\u0003\u0005)\u0011AA\b\u0005\ryFE\u000e\u0005\u0007\u0003oJ\u0001\u0019\u00013\u0002\u0015\u0015\u0014(/T3tg\u0006<W\rK\u0002\naZ\f1\u0003\\8bIB{GM\u0012:p[R+W\u000e\u001d7bi\u0016$\"\"a \u0002\u0006\u0006\u0005\u0016QUAV!\r\u0019\u0014\u0011Q\u0005\u0004\u0003\u0007;#\u0001C*qCJ\\\u0007k\u001c3\t\u000f\u0005\u001d%\u00021\u0001\u0002\n\u0006\u00012.\u001e2fe:,G/Z:DY&,g\u000e\u001e\t\u0005\u0003\u0017\u000bi*\u0004\u0002\u0002\u000e*!\u0011qRAI\u0003\u0019\u0019G.[3oi*!\u00111SAK\u0003)YWOY3s]\u0016$Xm\u001d\u0006\u0005\u0003/\u000bI*A\u0004gC\n\u0014\u0018n\u0019\u001d\u000b\u0005\u0005m\u0015AA5p\u0013\u0011\ty*!$\u0003!-+(-\u001a:oKR,7o\u00117jK:$\bBBAR\u0015\u0001\u0007A-\u0001\tuK6\u0004H.\u0019;f\r&dWMT1nK\"9\u0011q\u0015\u0006A\u0002\u0005%\u0016!D2p]R\f\u0017N\\3s\u001d\u0006lW\r\u0005\u00038\u0003\u0007!\u0007BBAW\u0015\u0001\u0007\u0011.\u0001\u0003d_:4\u0007\u0006\u0002\u0006q\u0003c\u000b#!a-\u0002\u000bMr#G\f\u0019\u0002)M,G.Z2u'B\f'o[\"p]R\f\u0017N\\3s)\u0019\ty(!/\u0002N\"9\u00111X\u0006A\u0002\u0005u\u0016a\u00019pIB!\u0011qXAe\u001b\t\t\tM\u0003\u0003\u0002D\u0006\u0015\u0017!B7pI\u0016d'\u0002BAd\u0003#\u000b1!\u00199j\u0013\u0011\tY-!1\u0003\u0007A{G\rC\u0004\u0002(.\u0001\r!!+)\t-\u0001\u0018QG\u0001\u000fa\u0006\u00148/Z'bgR,'/\u0016:m)\r!\u0017Q\u001b\u0005\u0007\u0003/d\u0001\u0019\u00013\u0002\u0007U\u0014H\u000e\u000b\u0003\ra\u0006m\u0017EAAo\u0003\u0015\u0011d\u0006\u000e\u00181\u0003E1wN]7biB\u000b\u0017N]:Ck:$G.\u001a\u000b\u0006I\u0006\r\u0018q \u0005\b\u0003Kl\u0001\u0019AAt\u0003\u0015\u0001\u0018-\u001b:t!\u0019\tI/a=\u0002z:!\u00111^Ax\u001d\ra\u0016Q^\u0005\u0002s%\u0019\u0011\u0011\u001f\u001d\u0002\u000fA\f7m[1hK&!\u0011Q_A|\u0005\r\u0019V-\u001d\u0006\u0004\u0003cD\u0004#B\u001c\u0002|\u0012$\u0017bAA\u007fq\t1A+\u001e9mKJB\u0011B!\u0001\u000e!\u0003\u0005\rAa\u0001\u0002\r%tG-\u001a8u!\r9$QA\u0005\u0004\u0005\u000fA$aA%oi\"\"Q\u0002]A\u001b\u0003m1wN]7biB\u000b\u0017N]:Ck:$G.\u001a\u0013eK\u001a\fW\u000f\u001c;%eU\u0011!q\u0002\u0016\u0005\u0005\u0007\u0011\tb\u000b\u0002\u0003\u0014A!!Q\u0003B\u000f\u001b\t\u00119B\u0003\u0003\u0003\u001a\tm\u0011!C;oG\",7m[3e\u0015\t\u0019\b(\u0003\u0003\u0003 \t]!!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006qam\u001c:nCR\u0004v\u000eZ*uCR,Gc\u00013\u0003&!9\u00111X\bA\u0002\u0005u\u0006\u0006B\bq\u0003k\tQcY8oi\u0006Lg.\u001a:t\t\u0016\u001c8M]5qi&|g\u000eF\u0003e\u0005[\u0011\t\u0004C\u0004\u00030A\u0001\r!!0\u0002\u0003AD\u0011B!\u0001\u0011!\u0003\u0005\rAa\u0001)\tA\u0001\u0018QG\u0001 G>tG/Y5oKJ\u001cH)Z:de&\u0004H/[8oI\u0011,g-Y;mi\u0012\u0012\u0014AG2p]R\f\u0017N\\3s'R\fG/^:EKN\u001c'/\u001b9uS>tG\u0003BAt\u0005wAqA!\u0010\u0013\u0001\u0004\u0011y$A\bd_:$\u0018-\u001b8feN#\u0018\r^;t!\u0011\tyL!\u0011\n\t\t\r\u0013\u0011\u0019\u0002\u0010\u0007>tG/Y5oKJ\u001cF/\u0019;vg\"\"!\u0003]A\u001b\u0003)1wN]7biRKW.\u001a\u000b\u0004I\n-\u0003B\u0002B''\u0001\u0007A-\u0001\u0003uS6,\u0007\u0006B\nq\u0003k\t\u0001\"\u001e8jcV,\u0017\n\u0012\u000b\u0004I\nU\u0003\"\u0003B,)A\u0005\t\u0019\u0001B-\u0003\u0015\u0019Gn\\2l!\r9%1L\u0005\u0004\u0005;B%!B\"m_\u000e\\\u0007\u0006\u0002\u000bq\u0003k\t!#\u001e8jcV,\u0017\n\u0012\u0013eK\u001a\fW\u000f\u001c;%cU\u0011!Q\r\u0016\u0005\u00053\u0012\t\"\u0001\rck&dGMU3t_V\u00148-Z:Rk\u0006tG/\u001b;jKN$bAa\u001b\u0003t\t]\u0004#\u0002.bI\n5\u0004\u0003BA`\u0005_JAA!\u001d\u0002B\nA\u0011+^1oi&$\u0018\u0010\u0003\u0004\u0003vY\u0001\r\u0001Z\u0001\u000eG>l\u0007o\u001c8f]Rt\u0015-\\3\t\u000b!4\u0002\u0019A5)\tY\u0001\u0018QG\u0001\u001bkBdw.\u00193B]\u0012$&/\u00198tM>\u0014XNR5mKV\u0013\u0018n\u001d\u000b\u0007\u0005\u007f\u0012)I!#\u0011\u000b\u0005%(\u0011\u00113\n\t\t\r\u0015q\u001f\u0002\t\u0013R,'/\u00192mK\"9!qQ\fA\u0002\t}\u0014\u0001\u00034jY\u0016,&/[:\t\u0013\u00055v\u0003%AA\u0002\t-\u0005\u0003B\u001c\u0002\u0004%DCa\u00069\u00026\u0005!S\u000f\u001d7pC\u0012\fe\u000e\u001a+sC:\u001chm\u001c:n\r&dW-\u0016:jg\u0012\"WMZ1vYR$#'\u0006\u0002\u0003\u0014*\"!1\u0012B\t\u0003EI7\u000fT8dC2$U\r]3oI\u0016t7-\u001f\u000b\u0005\u00053\u0013y\nE\u00028\u00057K1A!(9\u0005\u001d\u0011un\u001c7fC:DqA!)\u001a\u0001\u0004\u0011\u0019+A\u0002ve&\u0004BA!*\u0003,6\u0011!q\u0015\u0006\u0004\u0005S\u001b\u0016a\u00018fi&!!Q\u0016BT\u0005\r)&+S\u0001\u0015SNdunY1m\u0003:$'+Z:pYZ\f'\r\\3\u0015\t\te%1\u0017\u0005\u0007\u0005kS\u0002\u0019\u00013\u0002\u0011I,7o\\;sG\u0016DCA\u00079\u00026\u0005)\"/\u001a8b[\u0016l\u0015-\u001b8BaB\u0014Vm]8ve\u000e,Gc\u00023\u0003>\n}&\u0011\u0019\u0005\u0007\u0005k[\u0002\u0019\u00013\t\u0013\u000556\u0004%AA\u0002\t-\u0005b\u0002Bb7\u0001\u0007!\u0011T\u0001\u0012g\"|W\u000f\u001c3Va2|\u0017\r\u001a'pG\u0006d\u0007\u0006B\u000eq\u0005\u000f\f#A!3\u0002\u000bMr\u0013GL\u0019\u0002?I,g.Y7f\u001b\u0006Lg.\u00119q%\u0016\u001cx.\u001e:dK\u0012\"WMZ1vYR$#'A\u0007va2|\u0017\r\u001a$jY\u0016,&/\u001b\u000b\u0006I\nE'1\u001b\u0005\u0007\u0005Ck\u0002\u0019\u00013\t\u0013\u00055V\u0004%AA\u0002\t-\u0005\u0006B\u000fq\u0003k\tq#\u001e9m_\u0006$g)\u001b7f+JLG\u0005Z3gCVdG\u000f\n\u001a\u0002=U\u0004Hn\\1e\r&dW\rV8IC\u0012|w\u000e]\"p[B\fG/\u001b2mK\u001a\u001bFc\u0003>\u0003^\nE(Q\u001fB\u007f\u0007\u0003AqAa8 \u0001\u0004\u0011\t/A\u0002te\u000e\u0004BAa9\u0003n6\u0011!Q\u001d\u0006\u0005\u0005O\u0014I/\u0001\u0002gg*\u0019!1^\u0017\u0002\r!\fGm\\8q\u0013\u0011\u0011yO!:\u0003\tA\u000bG\u000f\u001b\u0005\b\u0005g|\u0002\u0019\u0001Bq\u0003\u0011!Wm\u001d;\t\u000f\t\u001dx\u00041\u0001\u0003xB!!1\u001dB}\u0013\u0011\u0011YP!:\u0003\u0015\u0019KG.Z*zgR,W\u000eC\u0005\u0003\u0000~\u0001\n\u00111\u0001\u0003\u001a\u00061A-\u001a7Te\u000eD\u0011ba\u0001 !\u0003\u0005\rA!'\u0002\u0013=4XM]<sSR,\u0017\u0001K;qY>\fGMR5mKR{\u0007*\u00193p_B\u001cu.\u001c9bi&\u0014G.\u001a$TI\u0011,g-Y;mi\u0012\"TCAB\u0005U\u0011\u0011IJ!\u0005\u0002QU\u0004Hn\\1e\r&dW\rV8IC\u0012|w\u000e]\"p[B\fG/\u001b2mK\u001a\u001bF\u0005Z3gCVdG\u000fJ\u001b\u00025\t,\u0018\u000e\u001c3Q_\u0012<\u0016\u000e\u001e5TKJ4\u0018nY3BG\u000e|WO\u001c;\u0015\r\rE11CB\f!\u00159\u00141AA_\u0011\u001d\u0019)B\ta\u0001\u0003S\u000bab]3sm&\u001cW-Q2d_VtG\u000fC\u0004\u0002<\n\u0002\r!a )\t\t\u0002\u0018QG\u0001\u0012C\u0012$wj\u001e8feJ+g-\u001a:f]\u000e,G#\u0002>\u0004 \r\u0005\u0002bBA^G\u0001\u0007\u0011Q\u0018\u0005\b\u0007G\u0019\u0003\u0019AB\u0013\u0003%\u0011Xm]8ve\u000e,7\u000f\u0005\u0004\u0002j\u0006M8q\u0005\t\u0005\u0003\u007f\u001bI#\u0003\u0003\u0004,\u0005\u0005'a\u0003%bg6+G/\u00193bi\u0006DCa\t9\u0003H\u0006a!-^5mI\u0016sgOV1sgR!11GB\u001e!\u0019\tI/a=\u00046A!\u0011qXB\u001c\u0013\u0011\u0019I$!1\u0003\r\u0015sgOV1s\u0011\u001d\u0019i\u0004\na\u0001\u0003O\f1!\u001a8wQ\u0011!\u0003o!\u0011\"\u0005\r\r\u0013!B\u001a/i9\u0002\u0014\u0001\u00072vS2$WI\u001c<WCJ\u001cx+\u001b;i\r&,G\u000e\u001a*fMR!11GB%\u0011\u001d\u0019i$\na\u0001\u0007\u0017\u0002b!!;\u0002t\u000e5\u0003CB\u001c\u0004P\u0011$G-C\u0002\u0004Ra\u0012a\u0001V;qY\u0016\u001c\u0004\u0006B\u0013q\u0007\u0003B3!AB,!\r\t8\u0011L\u0005\u0004\u00077\u0012(\u0001C+ogR\f'\r\\3)\u0007\u0005\u0019y\u0006E\u0002r\u0007CJ1aa\u0019s\u00051!UM^3m_B,'/\u00119jQ\r\u00011q\u000b\u0015\u0004\u0001\r}\u0003"
)
public final class KubernetesUtils {
   public static Seq buildEnvVarsWithFieldRef(final Seq env) {
      return KubernetesUtils$.MODULE$.buildEnvVarsWithFieldRef(env);
   }

   public static Seq buildEnvVars(final Seq env) {
      return KubernetesUtils$.MODULE$.buildEnvVars(env);
   }

   public static void addOwnerReference(final Pod pod, final Seq resources) {
      KubernetesUtils$.MODULE$.addOwnerReference(pod, resources);
   }

   public static Option buildPodWithServiceAccount(final Option serviceAccount, final SparkPod pod) {
      return KubernetesUtils$.MODULE$.buildPodWithServiceAccount(serviceAccount, pod);
   }

   public static Option uploadFileUri$default$2() {
      return KubernetesUtils$.MODULE$.uploadFileUri$default$2();
   }

   public static String uploadFileUri(final String uri, final Option conf) {
      return KubernetesUtils$.MODULE$.uploadFileUri(uri, conf);
   }

   public static Option renameMainAppResource$default$2() {
      return KubernetesUtils$.MODULE$.renameMainAppResource$default$2();
   }

   public static String renameMainAppResource(final String resource, final Option conf, final boolean shouldUploadLocal) {
      return KubernetesUtils$.MODULE$.renameMainAppResource(resource, conf, shouldUploadLocal);
   }

   public static boolean isLocalAndResolvable(final String resource) {
      return KubernetesUtils$.MODULE$.isLocalAndResolvable(resource);
   }

   public static Option uploadAndTransformFileUris$default$2() {
      return KubernetesUtils$.MODULE$.uploadAndTransformFileUris$default$2();
   }

   public static Iterable uploadAndTransformFileUris(final Iterable fileUris, final Option conf) {
      return KubernetesUtils$.MODULE$.uploadAndTransformFileUris(fileUris, conf);
   }

   public static Map buildResourcesQuantities(final String componentName, final SparkConf sparkConf) {
      return KubernetesUtils$.MODULE$.buildResourcesQuantities(componentName, sparkConf);
   }

   public static Clock uniqueID$default$1() {
      return KubernetesUtils$.MODULE$.uniqueID$default$1();
   }

   public static String uniqueID(final Clock clock) {
      return KubernetesUtils$.MODULE$.uniqueID(clock);
   }

   public static String formatTime(final String time) {
      return KubernetesUtils$.MODULE$.formatTime(time);
   }

   public static Seq containerStatusDescription(final ContainerStatus containerStatus) {
      return KubernetesUtils$.MODULE$.containerStatusDescription(containerStatus);
   }

   public static int containersDescription$default$2() {
      return KubernetesUtils$.MODULE$.containersDescription$default$2();
   }

   public static String containersDescription(final Pod p, final int indent) {
      return KubernetesUtils$.MODULE$.containersDescription(p, indent);
   }

   public static String formatPodState(final Pod pod) {
      return KubernetesUtils$.MODULE$.formatPodState(pod);
   }

   public static int formatPairsBundle$default$2() {
      return KubernetesUtils$.MODULE$.formatPairsBundle$default$2();
   }

   public static String formatPairsBundle(final Seq pairs, final int indent) {
      return KubernetesUtils$.MODULE$.formatPairsBundle(pairs, indent);
   }

   public static String parseMasterUrl(final String url) {
      return KubernetesUtils$.MODULE$.parseMasterUrl(url);
   }

   public static SparkPod selectSparkContainer(final Pod pod, final Option containerName) {
      return KubernetesUtils$.MODULE$.selectSparkContainer(pod, containerName);
   }

   public static SparkPod loadPodFromTemplate(final KubernetesClient kubernetesClient, final String templateFileName, final Option containerName, final SparkConf conf) {
      return KubernetesUtils$.MODULE$.loadPodFromTemplate(kubernetesClient, templateFileName, containerName, conf);
   }

   public static void requireNandDefined(final Option opt1, final Option opt2, final String errMessage) {
      KubernetesUtils$.MODULE$.requireNandDefined(opt1, opt2, errMessage);
   }

   public static void requireSecondIfFirstIsDefined(final Option opt1, final Option opt2, final String errMessageWhenSecondIsMissing) {
      KubernetesUtils$.MODULE$.requireSecondIfFirstIsDefined(opt1, opt2, errMessageWhenSecondIsMissing);
   }

   public static void requireBothOrNeitherDefined(final Option opt1, final Option opt2, final String errMessageWhenFirstIsMissing, final String errMessageWhenSecondIsMissing) {
      KubernetesUtils$.MODULE$.requireBothOrNeitherDefined(opt1, opt2, errMessageWhenFirstIsMissing, errMessageWhenSecondIsMissing);
   }

   public static Map parsePrefixedKeyValuePairs(final SparkConf sparkConf, final String prefix) {
      return KubernetesUtils$.MODULE$.parsePrefixedKeyValuePairs(sparkConf, prefix);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return KubernetesUtils$.MODULE$.LogStringContext(sc);
   }
}

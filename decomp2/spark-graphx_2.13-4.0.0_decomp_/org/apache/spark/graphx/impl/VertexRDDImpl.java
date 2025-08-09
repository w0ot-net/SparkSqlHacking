package org.apache.spark.graphx.impl;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.OneToOneDependency;
import org.apache.spark.Partition;
import org.apache.spark.Partitioner;
import org.apache.spark.graphx.EdgeRDD;
import org.apache.spark.graphx.VertexRDD;
import org.apache.spark.graphx.VertexRDD$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Option;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\rMe\u0001B\u0017/\u0001eB\u0001\"\u0014\u0001\u0003\u0006\u0004%\tA\u0014\u0005\t3\u0002\u0011\t\u0011)A\u0005\u001f\"Aa\f\u0001BC\u0002\u0013\u0005q\f\u0003\u0005g\u0001\t\u0005\t\u0015!\u0003a\u0011!9\u0007A!b\u0001\n'B\u0007\u0002C8\u0001\u0005\u0003\u0005\u000b\u0011B5\t\rA\u0004A\u0011\u0001\u0019r\u0011\u00159\b\u0001\"\u0011y\u0011\u001dI\bA1A\u0005BiDq!!\u0002\u0001A\u0003%1\u0010C\u0004\u0002\b\u0001!\t&!\u0003\t\u000f\u0005u\u0002\u0001\"\u0011\u0002@!9\u0011q\t\u0001\u0005B\u0005%\u0003bBA(\u0001\u0011\u0005\u0013\u0011\u000b\u0005\n\u0003;\u0002\u0011\u0013!C\u0001\u0003?Bq!!\u001e\u0001\t\u0003\n9\b\u0003\u0004\u0002z\u0001!\te\u0018\u0005\b\u0003w\u0002A\u0011IA?\u0011\u001d\t)\t\u0001C!\u0003\u000fCq!!#\u0001\t\u0003\nY\tC\u0004\u0002\u0010\u0002!\t%!%\t\u0011\u0005e\u0005\u0001\"\u00111\u00037Cq!a/\u0001\t\u0003\ni\fC\u0004\u0002<\u0002!\t%a5\t\u000f\t%\u0001\u0001\"\u0011\u0003\f!9!\u0011\u0002\u0001\u0005B\te\u0001b\u0002B\u000f\u0001\u0011\u0005#q\u0004\u0005\b\u0005;\u0001A\u0011\tB\u0012\u0011\u001d\u00119\u0003\u0001C!\u0005SAqAa\u0016\u0001\t\u0003\u0012I\u0006C\u0004\u0003\u0004\u0002!\tE!\"\t\u000f\t5\u0006\u0001\"\u0011\u00030\"9!q\u001b\u0001\u0005B\te\u0007B\u0002B}\u0001\u0011\u0005\u0003\u0010C\u0004\u0003|\u0002!\tE!@\t\u0011\rE\u0001\u0001\"\u00111\u0007'A\u0001ba\u000b\u0001\t\u0003\u00024Q\u0006\u0005\t\u0007c\u0001A\u0011\t\u0019\u00044!A1Q\n\u0001\u0005BA\u001ayeB\u0005\u0004\\9\n\t\u0011#\u0001\u0004^\u0019AQFLA\u0001\u0012\u0003\u0019y\u0006\u0003\u0004qS\u0011\u00051q\u000f\u0005\u000b\u0007sJ\u0013\u0013!C\u0001a\rm\u0004\"CBBS\u0005\u0005I\u0011BBC\u000551VM\u001d;fqJ#E)S7qY*\u0011q\u0006M\u0001\u0005S6\u0004HN\u0003\u00022e\u00051qM]1qQbT!a\r\u001b\u0002\u000bM\u0004\u0018M]6\u000b\u0005U2\u0014AB1qC\u000eDWMC\u00018\u0003\ry'oZ\u0002\u0001+\tQ\u0014i\u0005\u0002\u0001wA\u0019A(P \u000e\u0003AJ!A\u0010\u0019\u0003\u0013Y+'\u000f^3y%\u0012#\u0005C\u0001!B\u0019\u0001!QA\u0011\u0001C\u0002\r\u0013!A\u0016#\u0012\u0005\u0011S\u0005CA#I\u001b\u00051%\"A$\u0002\u000bM\u001c\u0017\r\\1\n\u0005%3%a\u0002(pi\"Lgn\u001a\t\u0003\u000b.K!\u0001\u0014$\u0003\u0007\u0005s\u00170A\u0007qCJ$\u0018\u000e^5p]N\u0014F\tR\u000b\u0002\u001fB\u0019\u0001kU+\u000e\u0003ES!A\u0015\u001a\u0002\u0007I$G-\u0003\u0002U#\n\u0019!\u000b\u0012#\u0011\u0007Y;v(D\u0001/\u0013\tAfF\u0001\rTQ&\u0004\b/\u00192mKZ+'\u000f^3y!\u0006\u0014H/\u001b;j_:\fa\u0002]1si&$\u0018n\u001c8t%\u0012#\u0005\u0005\u000b\u0002\u00037B\u0011Q\tX\u0005\u0003;\u001a\u0013\u0011\u0002\u001e:b]NLWM\u001c;\u0002%Q\f'oZ3u'R|'/Y4f\u0019\u00164X\r\\\u000b\u0002AB\u0011\u0011\rZ\u0007\u0002E*\u00111MM\u0001\bgR|'/Y4f\u0013\t)'M\u0001\u0007Ti>\u0014\u0018mZ3MKZ,G.A\nuCJ<W\r^*u_J\fw-\u001a'fm\u0016d\u0007%A\u0003wIR\u000bw-F\u0001j!\rQWnP\u0007\u0002W*\u0011ANR\u0001\be\u00164G.Z2u\u0013\tq7N\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003\u00191H\rV1hA\u00051A(\u001b8jiz\"2A];w)\t\u0019H\u000fE\u0002W\u0001}BQaZ\u0004A\u0004%DQ!T\u0004A\u0002=CqAX\u0004\u0011\u0002\u0003\u0007\u0001-A\u0004sK&tG-\u001a=\u0015\u0003m\n1\u0002]1si&$\u0018n\u001c8feV\t1\u0010E\u0002FyzL!! $\u0003\r=\u0003H/[8o!\ry\u0018\u0011A\u0007\u0002e%\u0019\u00111\u0001\u001a\u0003\u0017A\u000b'\u000f^5uS>tWM]\u0001\ra\u0006\u0014H/\u001b;j_:,'\u000fI\u0001\u0016O\u0016$\bK]3gKJ\u0014X\r\u001a'pG\u0006$\u0018n\u001c8t)\u0011\tY!a\r\u0011\r\u00055\u0011QDA\u0012\u001d\u0011\ty!!\u0007\u000f\t\u0005E\u0011qC\u0007\u0003\u0003'Q1!!\u00069\u0003\u0019a$o\\8u}%\tq)C\u0002\u0002\u001c\u0019\u000bq\u0001]1dW\u0006<W-\u0003\u0003\u0002 \u0005\u0005\"aA*fc*\u0019\u00111\u0004$\u0011\t\u0005\u0015\u0012Q\u0006\b\u0005\u0003O\tI\u0003E\u0002\u0002\u0012\u0019K1!a\u000bG\u0003\u0019\u0001&/\u001a3fM&!\u0011qFA\u0019\u0005\u0019\u0019FO]5oO*\u0019\u00111\u0006$\t\u000f\u0005U2\u00021\u0001\u00028\u0005\t1\u000fE\u0002\u0000\u0003sI1!a\u000f3\u0005%\u0001\u0016M\u001d;ji&|g.A\u0004tKRt\u0015-\\3\u0015\t\u0005\u0005\u00131I\u0007\u0002\u0001!9\u0011Q\t\u0007A\u0002\u0005\r\u0012!B0oC6,\u0017a\u00029feNL7\u000f\u001e\u000b\u0005\u0003\u0003\nY\u0005\u0003\u0004\u0002N5\u0001\r\u0001Y\u0001\t]\u0016<H*\u001a<fY\u0006IQO\u001c9feNL7\u000f\u001e\u000b\u0005\u0003\u0003\n\u0019\u0006C\u0005\u0002V9\u0001\n\u00111\u0001\u0002X\u0005A!\r\\8dW&tw\rE\u0002F\u00033J1!a\u0017G\u0005\u001d\u0011un\u001c7fC:\f1#\u001e8qKJ\u001c\u0018n\u001d;%I\u00164\u0017-\u001e7uIE*\"!!\u0019+\t\u0005]\u00131M\u0016\u0003\u0003K\u0002B!a\u001a\u0002r5\u0011\u0011\u0011\u000e\u0006\u0005\u0003W\ni'A\u0005v]\u000eDWmY6fI*\u0019\u0011q\u000e$\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002t\u0005%$!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006)1-Y2iKR\u0011\u0011\u0011I\u0001\u0010O\u0016$8\u000b^8sC\u001e,G*\u001a<fY\u0006Q1\r[3dWB|\u0017N\u001c;\u0015\u0005\u0005}\u0004cA#\u0002\u0002&\u0019\u00111\u0011$\u0003\tUs\u0017\u000e^\u0001\u000fSN\u001c\u0005.Z2la>Lg\u000e^3e+\t\t9&A\thKR\u001c\u0005.Z2la>Lg\u000e\u001e$jY\u0016,\"!!$\u0011\t\u0015c\u00181E\u0001\u0006G>,h\u000e\u001e\u000b\u0003\u0003'\u00032!RAK\u0013\r\t9J\u0012\u0002\u0005\u0019>tw-A\nnCB4VM\u001d;fqB\u000b'\u000f^5uS>t7/\u0006\u0003\u0002\u001e\u0006\u0015F\u0003BAP\u0003_#B!!)\u0002*B!A(PAR!\r\u0001\u0015Q\u0015\u0003\u0007\u0003O3\"\u0019A\"\u0003\u0007Y#%\u0007C\u0005\u0002,Z\t\t\u0011q\u0001\u0002.\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\t)l\u00171\u0015\u0005\b\u0003c3\u0002\u0019AAZ\u0003\u00051\u0007CB#\u00026V\u000bI,C\u0002\u00028\u001a\u0013\u0011BR;oGRLwN\\\u0019\u0011\tY;\u00161U\u0001\n[\u0006\u0004h+\u00197vKN,B!a0\u0002HR!\u0011\u0011YAh)\u0011\t\u0019-!3\u0011\tqj\u0014Q\u0019\t\u0004\u0001\u0006\u001dGABAT/\t\u00071\tC\u0005\u0002L^\t\t\u0011q\u0001\u0002N\u0006QQM^5eK:\u001cW\r\n\u001a\u0011\t)l\u0017Q\u0019\u0005\b\u0003c;\u0002\u0019AAi!\u0019)\u0015QW \u0002FV!\u0011Q[Ao)\u0011\t9.!:\u0015\t\u0005e\u0017q\u001c\t\u0005yu\nY\u000eE\u0002A\u0003;$a!a*\u0019\u0005\u0004\u0019\u0005\"CAq1\u0005\u0005\t9AAr\u0003))g/\u001b3f]\u000e,Ge\r\t\u0005U6\fY\u000eC\u0004\u00022b\u0001\r!a:\u0011\u0011\u0015\u000bI/!<@\u00037L1!a;G\u0005%1UO\\2uS>t'\u0007\u0005\u0003\u0002p\n\ra\u0002BAy\u0005\u0003qA!a=\u0002\u0000:!\u0011Q_A\u007f\u001d\u0011\t90a?\u000f\t\u0005E\u0011\u0011`\u0005\u0002o%\u0011QGN\u0005\u0003gQJ!!\r\u001a\n\u0007\u0005m\u0001'\u0003\u0003\u0003\u0006\t\u001d!\u0001\u0003,feR,\u00070\u00133\u000b\u0007\u0005m\u0001'A\u0003nS:,8\u000fF\u0002<\u0005\u001bAqAa\u0004\u001a\u0001\u0004\u0011\t\"A\u0003pi\",'\u000f\u0005\u0003Q'\nM\u0001CB#\u0003\u0016\u00055x(C\u0002\u0003\u0018\u0019\u0013a\u0001V;qY\u0016\u0014DcA\u001e\u0003\u001c!1!q\u0002\u000eA\u0002m\nA\u0001Z5gMR\u00191H!\t\t\u000f\t=1\u00041\u0001\u0003\u0012Q\u00191H!\n\t\r\t=A\u00041\u0001<\u0003-aWM\u001a;[SBTu.\u001b8\u0016\r\t-\"\u0011\tB\u001b)\u0011\u0011iCa\u0015\u0015\t\t=\"\u0011\n\u000b\u0007\u0005c\u0011IDa\u0011\u0011\tqj$1\u0007\t\u0004\u0001\nUBA\u0002B\u001c;\t\u00071IA\u0002W\tNB\u0011Ba\u000f\u001e\u0003\u0003\u0005\u001dA!\u0010\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$C\u0007\u0005\u0003k[\n}\u0002c\u0001!\u0003B\u00111\u0011qU\u000fC\u0002\rC\u0011B!\u0012\u001e\u0003\u0003\u0005\u001dAa\u0012\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$S\u0007\u0005\u0003k[\nM\u0002bBAY;\u0001\u0007!1\n\t\u000b\u000b\n5\u0013Q^ \u0003R\tM\u0012b\u0001B(\r\nIa)\u001e8di&|gn\r\t\u0005\u000br\u0014y\u0004C\u0004\u0003\u0010u\u0001\rA!\u0016\u0011\tqj$qH\u0001\tY\u00164GOS8j]V1!1\fB8\u0005K\"BA!\u0018\u0003~Q!!q\fB<)\u0019\u0011\tGa\u001a\u0003rA!A(\u0010B2!\r\u0001%Q\r\u0003\u0007\u0005oq\"\u0019A\"\t\u0013\t%d$!AA\u0004\t-\u0014AC3wS\u0012,gnY3%mA!!.\u001cB7!\r\u0001%q\u000e\u0003\u0007\u0003Os\"\u0019A\"\t\u0013\tMd$!AA\u0004\tU\u0014AC3wS\u0012,gnY3%oA!!.\u001cB2\u0011\u001d\t\tL\ba\u0001\u0005s\u0002\"\"\u0012B'\u0003[|$1\u0010B2!\u0011)EP!\u001c\t\u000f\t=a\u00041\u0001\u0003\u0000A!\u0001k\u0015BA!\u001d)%QCAw\u0005[\nA\"\u001b8oKJT\u0016\u000e\u001d&pS:,bAa\"\u0003\u001c\nEE\u0003\u0002BE\u0005S#BAa#\u0003&R1!Q\u0012BJ\u0005?\u0003B\u0001P\u001f\u0003\u0010B\u0019\u0001I!%\u0005\r\u0005\u001dvD1\u0001D\u0011%\u0011)jHA\u0001\u0002\b\u00119*\u0001\u0006fm&$WM\\2fIa\u0002BA[7\u0003\u001aB\u0019\u0001Ia'\u0005\r\tuuD1\u0001D\u0005\u0005)\u0006\"\u0003BQ?\u0005\u0005\t9\u0001BR\u0003))g/\u001b3f]\u000e,G%\u000f\t\u0005U6\u0014y\tC\u0004\u00022~\u0001\rAa*\u0011\u0015\u0015\u0013i%!<@\u00053\u0013y\tC\u0004\u0003\u0010}\u0001\rAa+\u0011\tqj$\u0011T\u0001\nS:tWM\u001d&pS:,bA!-\u0003F\nmF\u0003\u0002BZ\u0005#$BA!.\u0003NR1!q\u0017B_\u0005\u000f\u0004B\u0001P\u001f\u0003:B\u0019\u0001Ia/\u0005\r\u0005\u001d\u0006E1\u0001D\u0011%\u0011y\fIA\u0001\u0002\b\u0011\t-A\u0006fm&$WM\\2fIE\u0002\u0004\u0003\u00026n\u0005\u0007\u00042\u0001\u0011Bc\t\u0019\u0011i\n\tb\u0001\u0007\"I!\u0011\u001a\u0011\u0002\u0002\u0003\u000f!1Z\u0001\fKZLG-\u001a8dK\u0012\n\u0014\u0007\u0005\u0003k[\ne\u0006bBAYA\u0001\u0007!q\u001a\t\u000b\u000b\n5\u0013Q^ \u0003D\ne\u0006b\u0002B\bA\u0001\u0007!1\u001b\t\u0005!N\u0013)\u000eE\u0004F\u0005+\tiOa1\u0002'\u0005<wM]3hCR,Wk]5oO&sG-\u001a=\u0016\t\tm'1\u001d\u000b\u0007\u0005;\u0014YOa=\u0015\t\t}'Q\u001d\t\u0005yu\u0012\t\u000fE\u0002A\u0005G$a!a*\"\u0005\u0004\u0019\u0005\"\u0003BtC\u0005\u0005\t9\u0001Bu\u0003-)g/\u001b3f]\u000e,G%\r\u001a\u0011\t)l'\u0011\u001d\u0005\b\u0005[\f\u0003\u0019\u0001Bx\u0003!iWm]:bO\u0016\u001c\b\u0003\u0002)T\u0005c\u0004r!\u0012B\u000b\u0003[\u0014\t\u000fC\u0004\u0003v\u0006\u0002\rAa>\u0002\u0015I,G-^2f\rVt7\rE\u0005F\u0003S\u0014\tO!9\u0003b\u0006!\"/\u001a<feN,'k\\;uS:<G+\u00192mKN\f\u0011b^5uQ\u0016#w-Z:\u0015\u0007m\u0012y\u0010C\u0004\u0004\u0002\r\u0002\raa\u0001\u0002\u000b\u0015$w-Z:1\t\r\u00151Q\u0002\t\u0006y\r\u001d11B\u0005\u0004\u0007\u0013\u0001$aB#eO\u0016\u0014F\t\u0012\t\u0004\u0001\u000e5AaCB\b\u0005\u007f\f\t\u0011!A\u0003\u0002\r\u00131a\u0018\u00132\u0003E9\u0018\u000e\u001e5QCJ$\u0018\u000e^5p]N\u0014F\tR\u000b\u0005\u0007+\u0019i\u0002\u0006\u0003\u0004\u0018\r\u0015B\u0003BB\r\u0007?\u0001B\u0001P\u001f\u0004\u001cA\u0019\u0001i!\b\u0005\r\u0005\u001dFE1\u0001D\u0011%\u0019\t\u0003JA\u0001\u0002\b\u0019\u0019#A\u0006fm&$WM\\2fIE\u001a\u0004\u0003\u00026n\u00077Aa!\u0014\u0013A\u0002\r\u001d\u0002\u0003\u0002)T\u0007S\u0001BAV,\u0004\u001c\u00051r/\u001b;i)\u0006\u0014x-\u001a;Ti>\u0014\u0018mZ3MKZ,G\u000eF\u0002<\u0007_AQAX\u0013A\u0002\u0001\fAc\u001d5jaZ+'\u000f^3y\u0003R$(/\u001b2vi\u0016\u001cHCBB\u001b\u0007\u000b\u001aI\u0005\u0005\u0003Q'\u000e]\u0002cB#\u0003\u0016\re2q\b\t\u0005\u0003_\u001cY$\u0003\u0003\u0004>\t\u001d!a\u0003)beRLG/[8o\u0013\u0012\u0003BAVB!\u007f%\u001911\t\u0018\u0003)Y+'\u000f^3y\u0003R$(/\u001b2vi\u0016\u0014En\\2l\u0011\u001d\u00199E\na\u0001\u0003/\nqa\u001d5jaN\u00138\rC\u0004\u0004L\u0019\u0002\r!a\u0016\u0002\u000fMD\u0017\u000e\u001d#ti\u0006i1\u000f[5q-\u0016\u0014H/\u001a=JIN$\"a!\u0015\u0011\tA\u001b61\u000b\t\b\u000b\nU1\u0011HB+!\u0015)5qKAw\u0013\r\u0019IF\u0012\u0002\u0006\u0003J\u0014\u0018-_\u0001\u000e-\u0016\u0014H/\u001a=S\t\u0012KU\u000e\u001d7\u0011\u0005YK3#B\u0015\u0004b\r\u001d\u0004cA#\u0004d%\u00191Q\r$\u0003\r\u0005s\u0017PU3g!\u0011\u0019Iga\u001d\u000e\u0005\r-$\u0002BB7\u0007_\n!![8\u000b\u0005\rE\u0014\u0001\u00026bm\u0006LAa!\u001e\u0004l\ta1+\u001a:jC2L'0\u00192mKR\u00111QL\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001a\u0016\t\ru4\u0011Q\u000b\u0003\u0007\u007fR3\u0001YA2\t\u0015\u00115F1\u0001D\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u00199\t\u0005\u0003\u0004\n\u000e=UBABF\u0015\u0011\u0019iia\u001c\u0002\t1\fgnZ\u0005\u0005\u0007#\u001bYI\u0001\u0004PE*,7\r\u001e"
)
public class VertexRDDImpl extends VertexRDD {
   private final transient RDD partitionsRDD;
   private final StorageLevel targetStorageLevel;
   private final ClassTag vdTag;
   private final Option partitioner;

   public RDD partitionsRDD() {
      return this.partitionsRDD;
   }

   public StorageLevel targetStorageLevel() {
      return this.targetStorageLevel;
   }

   public ClassTag vdTag() {
      return this.vdTag;
   }

   public VertexRDD reindex() {
      return this.withPartitionsRDD(this.partitionsRDD().map((x$1) -> (ShippableVertexPartition)ShippableVertexPartition$.MODULE$.shippablePartitionToOps(x$1, this.vdTag()).reindex(), .MODULE$.apply(ShippableVertexPartition.class)), this.vdTag());
   }

   public Option partitioner() {
      return this.partitioner;
   }

   public Seq getPreferredLocations(final Partition s) {
      return this.partitionsRDD().preferredLocations(s);
   }

   public VertexRDDImpl setName(final String _name) {
      if (this.partitionsRDD().name() != null) {
         RDD var10000 = this.partitionsRDD();
         String var10001 = this.partitionsRDD().name();
         var10000.setName(var10001 + ", " + _name);
      } else {
         this.partitionsRDD().setName(_name);
      }

      return this;
   }

   public VertexRDDImpl persist(final StorageLevel newLevel) {
      this.partitionsRDD().persist(newLevel);
      return this;
   }

   public VertexRDDImpl unpersist(final boolean blocking) {
      this.partitionsRDD().unpersist(blocking);
      return this;
   }

   public boolean unpersist$default$1() {
      return false;
   }

   public VertexRDDImpl cache() {
      this.partitionsRDD().persist(this.targetStorageLevel());
      return this;
   }

   public StorageLevel getStorageLevel() {
      return this.partitionsRDD().getStorageLevel();
   }

   public void checkpoint() {
      this.partitionsRDD().checkpoint();
   }

   public boolean isCheckpointed() {
      return this.firstParent(.MODULE$.apply(ShippableVertexPartition.class)).isCheckpointed();
   }

   public Option getCheckpointFile() {
      return this.partitionsRDD().getCheckpointFile();
   }

   public long count() {
      return BoxesRunTime.unboxToLong(this.partitionsRDD().map((x$2) -> BoxesRunTime.boxToLong($anonfun$count$1(x$2)), .MODULE$.Long()).fold(BoxesRunTime.boxToLong(0L), (JFunction2.mcJJJ.sp)(x$3, x$4) -> x$3 + x$4));
   }

   public VertexRDD mapVertexPartitions(final Function1 f, final ClassTag evidence$1) {
      RDD newPartitionsRDD = this.partitionsRDD().mapPartitions((x$5) -> x$5.map(f), true, .MODULE$.apply(ShippableVertexPartition.class));
      return this.withPartitionsRDD(newPartitionsRDD, evidence$1);
   }

   public VertexRDD mapValues(final Function1 f, final ClassTag evidence$2) {
      return this.mapVertexPartitions((x$6) -> (ShippableVertexPartition)ShippableVertexPartition$.MODULE$.shippablePartitionToOps(x$6, this.vdTag()).map((vid, attr) -> $anonfun$mapValues$2(f, BoxesRunTime.unboxToLong(vid), attr), evidence$2), evidence$2);
   }

   public VertexRDD mapValues(final Function2 f, final ClassTag evidence$3) {
      return this.mapVertexPartitions((x$7) -> (ShippableVertexPartition)ShippableVertexPartition$.MODULE$.shippablePartitionToOps(x$7, this.vdTag()).map(f, evidence$3), evidence$3);
   }

   public VertexRDD minus(final RDD other) {
      return this.minus(this.aggregateUsingIndex(other, (a, b) -> a, this.vdTag()));
   }

   public VertexRDD minus(final VertexRDD other) {
      if (other != null) {
         Option var10000 = this.partitioner();
         Option var5 = other.partitioner();
         if (var10000 == null) {
            if (var5 == null) {
               return this.withPartitionsRDD(this.partitionsRDD().zipPartitions(other.partitionsRDD(), true, (thisIter, otherIter) -> {
                  ShippableVertexPartition thisPart = (ShippableVertexPartition)thisIter.next();
                  ShippableVertexPartition otherPart = (ShippableVertexPartition)otherIter.next();
                  return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ShippableVertexPartition[]{(ShippableVertexPartition)ShippableVertexPartition$.MODULE$.shippablePartitionToOps(thisPart, this.vdTag()).minus(otherPart)}));
               }, .MODULE$.apply(ShippableVertexPartition.class), .MODULE$.apply(ShippableVertexPartition.class)), this.vdTag());
            }
         } else if (var10000.equals(var5)) {
            return this.withPartitionsRDD(this.partitionsRDD().zipPartitions(other.partitionsRDD(), true, (thisIter, otherIter) -> {
               ShippableVertexPartition thisPart = (ShippableVertexPartition)thisIter.next();
               ShippableVertexPartition otherPart = (ShippableVertexPartition)otherIter.next();
               return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ShippableVertexPartition[]{(ShippableVertexPartition)ShippableVertexPartition$.MODULE$.shippablePartitionToOps(thisPart, this.vdTag()).minus(otherPart)}));
            }, .MODULE$.apply(ShippableVertexPartition.class), .MODULE$.apply(ShippableVertexPartition.class)), this.vdTag());
         }
      }

      return this.withPartitionsRDD(this.partitionsRDD().zipPartitions(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(other, .MODULE$.apply(Long.TYPE), this.vdTag(), scala.math.Ordering.Long..MODULE$).partitionBy((Partitioner)this.partitioner().get()), true, (partIter, msgs) -> partIter.map((x$8) -> (ShippableVertexPartition)ShippableVertexPartition$.MODULE$.shippablePartitionToOps(x$8, this.vdTag()).minus(msgs)), .MODULE$.apply(Tuple2.class), .MODULE$.apply(ShippableVertexPartition.class)), this.vdTag());
   }

   public VertexRDD diff(final RDD other) {
      return this.diff(this.aggregateUsingIndex(other, (a, b) -> a, this.vdTag()));
   }

   public VertexRDD diff(final VertexRDD other) {
      RDD var8;
      label21: {
         label20: {
            if (other != null) {
               Option var10000 = this.partitioner();
               Option var6 = other.partitioner();
               if (var10000 == null) {
                  if (var6 == null) {
                     break label20;
                  }
               } else if (var10000.equals(var6)) {
                  break label20;
               }
            }

            var8 = VertexRDD$.MODULE$.apply(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(other, .MODULE$.apply(Long.TYPE), this.vdTag(), scala.math.Ordering.Long..MODULE$).partitionBy((Partitioner)this.partitioner().get()), this.vdTag()).partitionsRDD();
            break label21;
         }

         var8 = other.partitionsRDD();
      }

      RDD otherPartition = var8;
      RDD newPartitionsRDD = this.partitionsRDD().zipPartitions(otherPartition, true, (thisIter, otherIter) -> {
         ShippableVertexPartition thisPart = (ShippableVertexPartition)thisIter.next();
         ShippableVertexPartition otherPart = (ShippableVertexPartition)otherIter.next();
         return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ShippableVertexPartition[]{(ShippableVertexPartition)ShippableVertexPartition$.MODULE$.shippablePartitionToOps(thisPart, this.vdTag()).diff(otherPart)}));
      }, .MODULE$.apply(ShippableVertexPartition.class), .MODULE$.apply(ShippableVertexPartition.class));
      return this.withPartitionsRDD(newPartitionsRDD, this.vdTag());
   }

   public VertexRDD leftZipJoin(final VertexRDD other, final Function3 f, final ClassTag evidence$4, final ClassTag evidence$5) {
      RDD newPartitionsRDD = this.partitionsRDD().zipPartitions(other.partitionsRDD(), true, (thisIter, otherIter) -> {
         ShippableVertexPartition thisPart = (ShippableVertexPartition)thisIter.next();
         ShippableVertexPartition otherPart = (ShippableVertexPartition)otherIter.next();
         return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ShippableVertexPartition[]{(ShippableVertexPartition)ShippableVertexPartition$.MODULE$.shippablePartitionToOps(thisPart, this.vdTag()).leftJoin(otherPart, f, evidence$4, evidence$5)}));
      }, .MODULE$.apply(ShippableVertexPartition.class), .MODULE$.apply(ShippableVertexPartition.class));
      return this.withPartitionsRDD(newPartitionsRDD, evidence$5);
   }

   public VertexRDD leftJoin(final RDD other, final Function3 f, final ClassTag evidence$6, final ClassTag evidence$7) {
      if (other instanceof VertexRDD var7) {
         Option var10000 = this.partitioner();
         Option var8 = var7.partitioner();
         if (var10000 == null) {
            if (var8 == null) {
               return this.leftZipJoin(var7, f, evidence$6, evidence$7);
            }
         } else if (var10000.equals(var8)) {
            return this.leftZipJoin(var7, f, evidence$6, evidence$7);
         }
      }

      return this.withPartitionsRDD(this.partitionsRDD().zipPartitions(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(other, .MODULE$.apply(Long.TYPE), evidence$6, scala.math.Ordering.Long..MODULE$).partitionBy((Partitioner)this.partitioner().get()), true, (partIter, msgs) -> partIter.map((x$9) -> (ShippableVertexPartition)ShippableVertexPartition$.MODULE$.shippablePartitionToOps(x$9, this.vdTag()).leftJoin(msgs, f, evidence$6, evidence$7)), .MODULE$.apply(Tuple2.class), .MODULE$.apply(ShippableVertexPartition.class)), evidence$7);
   }

   public VertexRDD innerZipJoin(final VertexRDD other, final Function3 f, final ClassTag evidence$8, final ClassTag evidence$9) {
      RDD newPartitionsRDD = this.partitionsRDD().zipPartitions(other.partitionsRDD(), true, (thisIter, otherIter) -> {
         ShippableVertexPartition thisPart = (ShippableVertexPartition)thisIter.next();
         ShippableVertexPartition otherPart = (ShippableVertexPartition)otherIter.next();
         return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ShippableVertexPartition[]{(ShippableVertexPartition)ShippableVertexPartition$.MODULE$.shippablePartitionToOps(thisPart, this.vdTag()).innerJoin(otherPart, f, evidence$8, evidence$9)}));
      }, .MODULE$.apply(ShippableVertexPartition.class), .MODULE$.apply(ShippableVertexPartition.class));
      return this.withPartitionsRDD(newPartitionsRDD, evidence$9);
   }

   public VertexRDD innerJoin(final RDD other, final Function3 f, final ClassTag evidence$10, final ClassTag evidence$11) {
      if (other instanceof VertexRDD var7) {
         Option var10000 = this.partitioner();
         Option var8 = var7.partitioner();
         if (var10000 == null) {
            if (var8 == null) {
               return this.innerZipJoin(var7, f, evidence$10, evidence$11);
            }
         } else if (var10000.equals(var8)) {
            return this.innerZipJoin(var7, f, evidence$10, evidence$11);
         }
      }

      return this.withPartitionsRDD(this.partitionsRDD().zipPartitions(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(other, .MODULE$.apply(Long.TYPE), evidence$10, scala.math.Ordering.Long..MODULE$).partitionBy((Partitioner)this.partitioner().get()), true, (partIter, msgs) -> partIter.map((x$10) -> (ShippableVertexPartition)ShippableVertexPartition$.MODULE$.shippablePartitionToOps(x$10, this.vdTag()).innerJoin(msgs, f, evidence$10, evidence$11)), .MODULE$.apply(Tuple2.class), .MODULE$.apply(ShippableVertexPartition.class)), evidence$11);
   }

   public VertexRDD aggregateUsingIndex(final RDD messages, final Function2 reduceFunc, final ClassTag evidence$12) {
      RDD shuffled = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(messages, .MODULE$.apply(Long.TYPE), evidence$12, scala.math.Ordering.Long..MODULE$).partitionBy((Partitioner)this.partitioner().get());
      RDD parts = this.partitionsRDD().zipPartitions(shuffled, true, (thisIter, msgIter) -> thisIter.map((x$11) -> (ShippableVertexPartition)ShippableVertexPartition$.MODULE$.shippablePartitionToOps(x$11, this.vdTag()).aggregateUsingIndex(msgIter, reduceFunc, evidence$12)), .MODULE$.apply(Tuple2.class), .MODULE$.apply(ShippableVertexPartition.class));
      return this.withPartitionsRDD(parts, evidence$12);
   }

   public VertexRDD reverseRoutingTables() {
      return this.mapVertexPartitions((vPart) -> vPart.withRoutingTable(vPart.routingTable().reverse()), this.vdTag());
   }

   public VertexRDD withEdges(final EdgeRDD edges) {
      RDD routingTables = VertexRDD$.MODULE$.createRoutingTables(edges, (Partitioner)this.partitioner().get());
      RDD vertexPartitions = this.partitionsRDD().zipPartitions(routingTables, true, (partIter, routingTableIter) -> {
         RoutingTablePartition routingTable = routingTableIter.hasNext() ? (RoutingTablePartition)routingTableIter.next() : RoutingTablePartition$.MODULE$.empty();
         return partIter.map((x$12) -> x$12.withRoutingTable(routingTable));
      }, .MODULE$.apply(RoutingTablePartition.class), .MODULE$.apply(ShippableVertexPartition.class));
      return this.withPartitionsRDD(vertexPartitions, this.vdTag());
   }

   public VertexRDD withPartitionsRDD(final RDD partitionsRDD, final ClassTag evidence$13) {
      return new VertexRDDImpl(partitionsRDD, this.targetStorageLevel(), evidence$13);
   }

   public VertexRDD withTargetStorageLevel(final StorageLevel targetStorageLevel) {
      return new VertexRDDImpl(this.partitionsRDD(), targetStorageLevel, this.vdTag());
   }

   public RDD shipVertexAttributes(final boolean shipSrc, final boolean shipDst) {
      return this.partitionsRDD().mapPartitions((x$13) -> x$13.flatMap((x$14) -> x$14.shipVertexAttributes(shipSrc, shipDst)), this.partitionsRDD().mapPartitions$default$2(), .MODULE$.apply(Tuple2.class));
   }

   public RDD shipVertexIds() {
      return this.partitionsRDD().mapPartitions((x$15) -> x$15.flatMap((x$16) -> x$16.shipVertexIds()), this.partitionsRDD().mapPartitions$default$2(), .MODULE$.apply(Tuple2.class));
   }

   // $FF: synthetic method
   public static final long $anonfun$count$1(final ShippableVertexPartition x$2) {
      return (long)x$2.size();
   }

   // $FF: synthetic method
   public static final Object $anonfun$mapValues$2(final Function1 f$2, final long vid, final Object attr) {
      return f$2.apply(attr);
   }

   public VertexRDDImpl(final RDD partitionsRDD, final StorageLevel targetStorageLevel, final ClassTag vdTag) {
      super(partitionsRDD.context(), new scala.collection.immutable..colon.colon(new OneToOneDependency(partitionsRDD), scala.collection.immutable.Nil..MODULE$));
      this.partitionsRDD = partitionsRDD;
      this.targetStorageLevel = targetStorageLevel;
      this.vdTag = vdTag;
      scala.Predef..MODULE$.require(partitionsRDD.partitioner().isDefined());
      this.partitioner = partitionsRDD.partitioner();
      this.setName("VertexRDD");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

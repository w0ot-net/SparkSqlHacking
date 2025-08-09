package scala.collection.mutable;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.NoSuchElementException;
import scala.DummyImplicit;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractIterator;
import scala.collection.BuildFrom;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.View;
import scala.collection.generic.DefaultSerializationProxy;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Nothing$;
import scala.runtime.Statics;

/** @deprecated */
@ScalaSignature(
   bytes = "\u0006\u0005\u0019md!B>}\u0001\u0005\u001d\u0001BCA5\u0001\t\u0005\t\u0015!\u0003\u0002l!Q\u0011\u0011\u000f\u0001\u0003\u0002\u0003\u0006I!a\u001d\t\u0015\u0005e\u0004A!A!\u0002\u0013\tY\b\u0003\u0005\u0002\u0002\u0002!\tA`AB\u0011\u001d\t\t\t\u0001C\u0001\u0003\u0017Cq!!!\u0001\t\u0003\ti\tC\u0004\u0002\u0002\u0002!\t!!%\t\u000f\u0005\u0005\u0005\u0001\"\u0001\u0002\u0016\"q\u00111\u0014\u0001\u0005\u0002\u0003\u0015\t\u0011!Q!\n\u0005M\u0004\u0002CAO\u0001\u0001\u0006K!a\u001d\t\u0011\u0005}\u0005\u0001)Q\u0005\u0003gBa\"!)\u0001\t\u0003\u0005)\u0011!A!B\u0013\t\u0019\u000b\u0003\b\u0002*\u0002!\t\u0011!B\u0001\u0002\u0003\u0006K!a+\t\u0011\u00055\u0006\u0001)Q\u0005\u0003WC\u0001\"a,\u0001A\u0013%\u0011\u0011\u0017\u0005\t\u0003{\u0003A\u0011\u0001@\u0002@\"9\u0011\u0011\u001c\u0001\u0005R\u0005m\u0007bBAt\u0001\u0011E\u0013\u0011\u001e\u0005\b\u0003c\u0004A\u0011IAz\u0011\u001d\t)\u0010\u0001C!\u0003gDq!a>\u0001\t\u0003\nI\u0010C\u0004\u0002|\u0002!\t%!@\t\u000f\u0005}\b\u0001\"\u0003\u0002z\"9!\u0011\u0001\u0001\u0005\n\t\r\u0001b\u0002B\u0005\u0001\u0011%!1\u0002\u0005\b\u0005+\u0001A\u0011\u0002B\f\u0011\u001d\u0011)\u0003\u0001C!\u0005OAqAa\u000b\u0001\t\u0003\u0012i\u0003C\u0004\u00038\u0001!\tE!\u000f\t\u000f\tE\u0003\u0001\"\u0011\u0003T!9!Q\f\u0001\u0005\u0002\t}\u0003b\u0002B2\u0001\u0011\u0005#Q\r\u0005\b\u0005\u0013\u0002A\u0011\tB5\u0011\u001d\u0011i\u0007\u0001C\u0005\u0005_BqA!\u001c\u0001\t\u0003\u0011)\bC\u0004\u0003x\u0001!\tE!\u001f\t\u000f\t\u0005\u0005\u0001\"\u0011\u0003\u0004\"9!\u0011\u0012\u0001\u0005\u0002\t-\u0005b\u0002BT\u0001\u0011\u0015!\u0011\u0016\u0005\b\u0005O\u0003AQ\tBY\u0011\u001d\u0011I\f\u0001C\u0001\u0005wCqAa0\u0001\t\u0003\u0011\t\rC\u0004\u0003J\u0002!\tEa3\t\u000f\t=\u0007\u0001\"\u0011\u0003R\u001a9!Q\u001b\u0001\u0002\n\t]\u0007bBAA[\u0011\u0005!q\u001d\u0005\t\u0003\u001fl\u0003\u0015!\u0003\u0002$\"A\u00111[\u0017!\u0002\u0013\tY\u000b\u0003\u0005\u0002X6\u0002\u000b\u0011BAV\u0011!\u0011Y/\fQ!\n\u0005M\u0004b\u0002Bw[\u0011\u0005\u0011\u0011 \u0005\b\u0005_lC\u0011\u0001By\u0011\u001d\u0011\u00190\fD\t\u0005kDqA!@\u0001\t\u0003\u0012y\u0010C\u0004\u0004\u0010\u0001!\te!\u0005\t\u000f\r\u0005\u0002\u0001\"\u0011\u0002\f\"911\u0005\u0001\u0005B\r\u0015\u0002bBB\u0012\u0001\u0011\u00053Q\b\u0005\b\u0007C\u0002A\u0011IB2\u0011\u001d\u00199\b\u0001C!\u0007sBqa!#\u0001\t\u0003\u001aY\t\u0003\u0005\u0004 \u0002\u0001K\u0011BBQ\u0011\u001d\u0019)\f\u0001C\u0001\u0007oCqaa1\u0001\t\u0003\u0019)\rC\u0004\u0004R\u0002!\taa5\t\u000f\r\u0005\b\u0001\"\u0002\u0004d\"91\u0011\u001f\u0001\u0005\u0002\rM\bbBB|\u0001\u0011\u00051\u0011 \u0005\b\t7\u0001A\u0011\u0001C\u000f\u0011\u001d!9\u0004\u0001C\u0001\tsAq\u0001b\u0016\u0001\t\u0003\u0012)\b\u0003\u0005\u0005Z\u0001\u0001K\u0011\u0003C.\u0011!!i\u0006\u0001Q\u0005R\u0011}sa\u0002CHy\"\u0005A\u0011\u0013\u0004\u0007wrD\t\u0001b%\t\u000f\u0005\u00055\n\"\u0001\u0005 \"IA\u0011U&C\u0002\u00135A1\u0015\u0005\t\tS[\u0005\u0015!\u0004\u0005&\"IA1V&C\u0002\u00135AQ\u0016\u0005\t\tg[\u0005\u0015!\u0004\u00050\"IAQW&C\u0002\u00135Aq\u0017\u0005\t\t{[\u0005\u0015!\u0004\u0005:\"IAqX&C\u0002\u00135A\u0011\u0019\u0005\t\t\u000f\\\u0005\u0015!\u0004\u0005D\u001a1A\u0011Z&\u0005\t\u0017Dq!!!V\t\u0003!y\rC\u0004\u0003dU#\t\u0001\"6\t\u0013\u0011e7J1A\u0005\n\u0011m\u0007\u0002\u0003Co\u0017\u0002\u0006I\u0001\"5\u0007\r\u0011}7J\u0001Cq\u0011\u001d\t\tI\u0017C\u0001\toD!ba\u0015[\u0001\u0004%\tA C~\u0011)!iP\u0017a\u0001\n\u0003qHq \u0005\t\u000b\u000bQ\u0006\u0015)\u0003\u0005v\"9!q\u0015.\u0005\u0002\u0015\u001d\u0001b\u0002C,5\u0012\u0005!Q\u000f\u0005\b\u000b\u001fQF\u0011AC\t\u0011\u001d\t)P\u0017C!\u0003gDqAa\u0019L\t\u0003)\u0019\u0002C\u0004\u0006(-#\t!\"\u000b\t\u000f\u0015m2\n\"\u0003\u0006>!9\u00111`&\u0005\u0002\u0015E\u0003bBC0\u0017\u0012\u0005Q\u0011\r\u0005\b\u000bgZE\u0011AC;\u0011\u001d)Yi\u0013C\u0001\u000b\u001bCq!b#L\t\u0003)9\u000bC\u0004\u0006>.#\u0019!b0\b\u0011\u0015e7\n)E\u0005\u000b74\u0001\"\"8LA#%Qq\u001c\u0005\b\u0003\u0003kG\u0011ACt\u0011\u001d\tI.\u001cC\u0001\u000bSDq!b\nn\t\u0003)\t\u0010C\u0005\u0005Z5\f\t\u0011\"\u0003\u0006v\"9a\u0011B&\u0005\u0004\u0019-q\u0001\u0003D\u0013\u0017\u0002FIAb\n\u0007\u0011\u0019%2\n)E\u0005\rWAq!!!u\t\u00031y\u0003C\u0004\u0002ZR$\tA\"\r\t\u000f\u0015\u001dB\u000f\"\u0001\u0007:!9aqH&\u0005\u0004\u0019\u0005\u0003b\u0002D*\u0017\u0012\raQ\u000b\u0005\n\t3Z\u0015\u0011!C\u0005\u000bk\u0014\u0011\"\u00118z%\u00164W*\u00199\u000b\u0005ut\u0018aB7vi\u0006\u0014G.\u001a\u0006\u0004\u007f\u0006\u0005\u0011AC2pY2,7\r^5p]*\u0011\u00111A\u0001\u0006g\u000e\fG.Y\u0002\u0001+\u0019\tI!a\u0006\u0002.MI\u0001!a\u0003\u0002:\u0005\u001d\u00131\f\t\t\u0003\u001b\ty!a\u0005\u0002,5\tA0C\u0002\u0002\u0012q\u00141\"\u00112tiJ\f7\r^'baB!\u0011QCA\f\u0019\u0001!q!!\u0007\u0001\u0005\u0004\tYBA\u0001L#\u0011\ti\"!\n\u0011\t\u0005}\u0011\u0011E\u0007\u0003\u0003\u0003IA!a\t\u0002\u0002\t9aj\u001c;iS:<\u0007\u0003BA\u0010\u0003OIA!!\u000b\u0002\u0002\t1\u0011I\\=SK\u001a\u0004B!!\u0006\u0002.\u00119\u0011q\u0006\u0001C\u0002\u0005E\"!\u0001,\u0012\t\u0005u\u00111\u0007\t\u0005\u0003?\t)$\u0003\u0003\u00028\u0005\u0005!aA!osBa\u0011QBA\u001e\u0003'\tY#a\u0010\u0002F%\u0019\u0011Q\b?\u0003\r5\u000b\u0007o\u00149t!\u0011\ti!!\u0011\n\u0007\u0005\rCPA\u0002NCB\u0004r!!\u0004\u0001\u0003'\tY\u0003\u0005\u0006\u0002J\u0005-\u0013qJA+\u0003\u000bj\u0011A`\u0005\u0004\u0003\u001br(AG*ue&\u001cGo\u00149uS6L'0\u001a3Ji\u0016\u0014\u0018M\u00197f\u001fB\u001c\b\u0003CA\u0010\u0003#\n\u0019\"a\u000b\n\t\u0005M\u0013\u0011\u0001\u0002\u0007)V\u0004H.\u001a\u001a\u0011\t\u00055\u0011qK\u0005\u0004\u00033b(\u0001C%uKJ\f'\r\\3\u0011\t\u0005u\u00131\r\b\u0005\u0003?\ty&\u0003\u0003\u0002b\u0005\u0005\u0011a\u00029bG.\fw-Z\u0005\u0005\u0003K\n9G\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0003\u0002b\u0005\u0005\u0011\u0001\u00043fM\u0006,H\u000e^#oiJL\b\u0003CA\u0010\u0003[\n\u0019\"a\u000b\n\t\u0005=\u0014\u0011\u0001\u0002\n\rVt7\r^5p]F\n\u0011#\u001b8ji&\fGNQ;gM\u0016\u00148+\u001b>f!\u0011\ty\"!\u001e\n\t\u0005]\u0014\u0011\u0001\u0002\u0004\u0013:$\u0018!C5oSR\u0014E.\u00198l!\u0011\ty\"! \n\t\u0005}\u0014\u0011\u0001\u0002\b\u0005>|G.Z1o\u0003\u0019a\u0014N\\5u}QA\u0011QIAC\u0003\u000f\u000bI\tC\u0004\u0002j\u0011\u0001\r!a\u001b\t\u000f\u0005ED\u00011\u0001\u0002t!9\u0011\u0011\u0010\u0003A\u0002\u0005mDCAA#)\u0011\t)%a$\t\u000f\u0005%d\u00011\u0001\u0002lQ!\u0011QIAJ\u0011\u001d\t\th\u0002a\u0001\u0003g\"b!!\u0012\u0002\u0018\u0006e\u0005bBA5\u0011\u0001\u0007\u00111\u000e\u0005\b\u0003cB\u0001\u0019AA:\u0003!\u001a8-\u00197bI\r|G\u000e\\3di&|g\u000eJ7vi\u0006\u0014G.\u001a\u0013B]f\u0014VMZ'ba\u0012\"S.Y:l\u0003\u0015y6/\u001b>f\u0003\u001dyf/Y2b]R\f1f]2bY\u0006$3m\u001c7mK\u000e$\u0018n\u001c8%[V$\u0018M\u00197fI\u0005s\u0017PU3g\u001b\u0006\u0004H\u0005J0iCNDWm\u001d\t\u0007\u0003?\t)+a\u001d\n\t\u0005\u001d\u0016\u0011\u0001\u0002\u0006\u0003J\u0014\u0018-_\u0001*g\u000e\fG.\u0019\u0013d_2dWm\u0019;j_:$S.\u001e;bE2,G%\u00118z%\u00164W*\u00199%I}[W-_:\u0011\r\u0005}\u0011QUA\u0013\u0003\u001dyf/\u00197vKN\f\u0011\u0003Z3gCVdG/\u00138ji&\fG.\u001b>f)\u0011\t\u0019,!/\u0011\t\u0005}\u0011QW\u0005\u0005\u0003o\u000b\tA\u0001\u0003V]&$\bbBA^\u001f\u0001\u0007\u00111O\u0001\u0002]\u0006a\u0011N\\5uS\u0006d\u0017N_3U_Rq\u00111WAa\u0003\u000b\fI-!4\u0002R\u0006U\u0007bBAb!\u0001\u0007\u00111O\u0001\u0002[\"9\u0011q\u0019\tA\u0002\u0005M\u0014AA:{\u0011\u001d\tY\r\u0005a\u0001\u0003g\n!A^2\t\u000f\u0005=\u0007\u00031\u0001\u0002$\u0006\u0011\u0001N\u001f\u0005\b\u0003'\u0004\u0002\u0019AAV\u0003\tY'\u0010C\u0004\u0002XB\u0001\r!a+\u0002\u0005YT\u0018\u0001\u00044s_6\u001c\u0006/Z2jM&\u001cG\u0003BA#\u0003;Dq!a8\u0012\u0001\u0004\t\t/\u0001\u0003d_2d\u0007CBA%\u0003G\fy%C\u0002\u0002fz\u0014A\"\u0013;fe\u0006\u0014G.Z(oG\u0016\f!C\\3x'B,7-\u001b4jG\n+\u0018\u000e\u001c3feV\u0011\u00111\u001e\t\t\u0003\u001b\ti/a\u0014\u0002F%\u0019\u0011q\u001e?\u0003\u000f\t+\u0018\u000e\u001c3fe\u0006!1/\u001b>f+\t\t\u0019(A\u0005l]><hnU5{K\u00069\u0011n]#naRLXCAA>\u0003\u0015)W\u000e\u001d;z+\t\t)%\u0001\u0006j[\n\fG.\u00198dK\u0012\fa\u0001[1tQ>3G\u0003BA:\u0005\u000bAqAa\u0002\u0019\u0001\u0004\t\u0019\"A\u0002lKf\f\u0011b]3fW\u0016sGO]=\u0015\r\u0005M$Q\u0002B\t\u0011\u001d\u0011y!\u0007a\u0001\u0003g\n\u0011\u0001\u001b\u0005\b\u0005'I\u0002\u0019AA\u0013\u0003\u0005Y\u0017aD:fK.,e\u000e\u001e:z\u001fJ|\u0005/\u001a8\u0015\r\u0005M$\u0011\u0004B\u000e\u0011\u001d\u0011yA\u0007a\u0001\u0003gBqAa\u0005\u001b\u0001\u0004\t)\u0003K\u0002\u001b\u0005?\u0001B!a\b\u0003\"%!!1EA\u0001\u0005\u0019Ig\u000e\\5oK\u0006A1m\u001c8uC&t7\u000f\u0006\u0003\u0002|\t%\u0002b\u0002B\u00047\u0001\u0007\u00111C\u0001\u0004O\u0016$H\u0003\u0002B\u0018\u0005k\u0001b!a\b\u00032\u0005-\u0012\u0002\u0002B\u001a\u0003\u0003\u0011aa\u00149uS>t\u0007b\u0002B\u00049\u0001\u0007\u00111C\u0001\nO\u0016$xJ]#mg\u0016,BAa\u000f\u0003@Q1!Q\bB#\u0005\u000f\u0002B!!\u0006\u0003@\u00119!\u0011I\u000fC\u0002\t\r#A\u0001,2#\u0011\tY#a\r\t\u000f\t\u001dQ\u00041\u0001\u0002\u0014!A!\u0011J\u000f\u0005\u0002\u0004\u0011Y%A\u0004eK\u001a\fW\u000f\u001c;\u0011\r\u0005}!Q\nB\u001f\u0013\u0011\u0011y%!\u0001\u0003\u0011q\u0012\u0017P\\1nKz\nqbZ3u\u001fJ,En]3Va\u0012\fG/\u001a\u000b\u0007\u0003W\u0011)Fa\u0016\t\u000f\t\u001da\u00041\u0001\u0002\u0014!A!\u0011\f\u0010\u0005\u0002\u0004\u0011Y&\u0001\u0007eK\u001a\fW\u000f\u001c;WC2,X\r\u0005\u0004\u0002 \t5\u00131F\u0001\nO\u0016$xJ\u001d(vY2$B!a\u000b\u0003b!9!qA\u0010A\u0002\u0005M\u0011!B1qa2LH\u0003BA\u0016\u0005OBqAa\u0002!\u0001\u0004\t\u0019\u0002\u0006\u0003\u0002,\t-\u0004b\u0002B\u0004C\u0001\u0007\u00111C\u0001\u0007e\u0016\u0004\u0018mY6\u0015\t\u0005M&\u0011\u000f\u0005\b\u0005g\u0012\u0003\u0019AA:\u0003\u001dqWm^'bg.$\"!a-\u0002\u0007A,H\u000f\u0006\u0004\u00030\tm$Q\u0010\u0005\b\u0005\u000f!\u0003\u0019AA\n\u0011\u001d\u0011y\b\na\u0001\u0003W\tQA^1mk\u0016\fa!\u001e9eCR,GCBAZ\u0005\u000b\u00139\tC\u0004\u0003\b\u0015\u0002\r!a\u0005\t\u000f\t}T\u00051\u0001\u0002,\u0005AA\u0005\u001d7vg\u0012*\u0017\u000f\u0006\u0004\u0003\u000e\n=%\u0011S\u0007\u0002\u0001!9!q\u0001\u0014A\u0002\u0005M\u0001b\u0002B@M\u0001\u0007\u00111\u0006\u0015\fM\tU%1\u0014BO\u0005C\u0013\u0019\u000b\u0005\u0003\u0002 \t]\u0015\u0002\u0002BM\u0003\u0003\u0011!\u0002Z3qe\u0016\u001c\u0017\r^3e\u0003\u001diWm]:bO\u0016\f#Aa(\u0002MV\u001bX\r\t1bI\u0012|e.\u001a1!_J\u0004\u0003-\u001e9eCR,\u0007\rI5ogR,\u0017\rZ\u001e!S:4\u0017\u000e\u001f\u0011pa\u0016\u0014\u0018\r^5p]N\u0004s/\u001b;iA\u0005t\u0007e\u001c9fe\u0006tG\rI8gA5,H\u000e^5qY\u0016\u0004\u0013M]4tA]LG\u000e\u001c\u0011cK\u0002\"W\r\u001d:fG\u0006$X\rZ\u0001\u0006g&t7-Z\u0011\u0003\u0005K\u000baA\r\u00182g9\u001a\u0014AB1eI>sW\r\u0006\u0004\u0003\u000e\n-&Q\u0016\u0005\b\u0005\u000f9\u0003\u0019AA\n\u0011\u001d\u0011yh\na\u0001\u0003WA3a\nB\u0010)\u0011\u0011iIa-\t\u000f\tU\u0006\u00061\u0001\u0002P\u0005\u00111N\u001e\u0015\u0004Q\t}\u0011aC:vER\u0014\u0018m\u0019;P]\u0016$BA!$\u0003>\"9!qA\u0015A\u0002\u0005M\u0011\u0001C5uKJ\fGo\u001c:\u0016\u0005\t\r\u0007CBA%\u0005\u000b\fy%C\u0002\u0003Hz\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\rW\u0016L8/\u0013;fe\u0006$xN]\u000b\u0003\u0005\u001b\u0004b!!\u0013\u0003F\u0006M\u0011A\u0004<bYV,7/\u0013;fe\u0006$xN]\u000b\u0003\u0005'\u0004b!!\u0013\u0003F\u0006-\"!E!osJ+g-T1q\u0013R,'/\u0019;peV!!\u0011\u001cBr'\ri#1\u001c\t\u0007\u0003\u0013\u0012iN!9\n\u0007\t}gP\u0001\tBEN$(/Y2u\u0013R,'/\u0019;peB!\u0011Q\u0003Br\t\u001d\u0011)/\fb\u0001\u0003c\u0011\u0011!\u0011\u000b\u0003\u0005S\u0004RA!$.\u0005C\fQ!\u001b8eKb\fq\u0001[1t\u001d\u0016DH/\u0001\u0003oKb$HC\u0001Bq\u0003)qW\r\u001f;SKN,H\u000e\u001e\u000b\u0007\u0005C\u00149P!?\t\u000f\tMQ\u00071\u0001\u0002\u0014!9!1`\u001bA\u0002\u0005-\u0012!\u0001<\u0002\u000f\u0019|'/Z1dQV!1\u0011AB\u0006)\u0011\t\u0019la\u0001\t\u000f\r\u0015a\u00071\u0001\u0004\b\u0005\ta\r\u0005\u0005\u0002 \u00055\u0014qJB\u0005!\u0011\t)ba\u0003\u0005\u000f\r5aG1\u0001\u00022\t\tQ+\u0001\u0007g_J,\u0017m\u00195F]R\u0014\u00180\u0006\u0003\u0004\u0014\r}A\u0003BAZ\u0007+Aqa!\u00028\u0001\u0004\u00199\u0002\u0005\u0006\u0002 \re\u00111CA\u0016\u0007;IAaa\u0007\u0002\u0002\tIa)\u001e8di&|gN\r\t\u0005\u0003+\u0019y\u0002B\u0004\u0004\u000e]\u0012\r!!\r\u0002\u000b\rdwN\\3\u0002\u000b\u0011\u0002H.^:\u0016\t\r\u001d2Q\u0006\u000b\u0005\u0007S\u0019y\u0003E\u0004\u0002\u000e\u0001\t\u0019ba\u000b\u0011\t\u0005U1Q\u0006\u0003\b\u0005\u0003J$\u0019\u0001B\"\u0011\u001d\u0011),\u000fa\u0001\u0007c\u0001\u0002\"a\b\u0002R\u0005M11\u0006\u0015\fs\tU%1TB\u001b\u0005C\u001bI$\t\u0002\u00048\u0005q4i\u001c8tS\u0012,'\u000f\t:fcVL'/\u001b8hA\u0005t\u0007%[7nkR\f'\r\\3!\u001b\u0006\u0004\be\u001c:!M\u0006dG\u000e\t2bG.\u0004Co\u001c\u0011NCBt3m\u001c8dCR\f#aa\u000f\u0002\rIr\u0013g\r\u00181+\u0011\u0019yd!\u0012\u0015\u0011\r\u00053qIB'\u0007#\u0002r!!\u0004\u0001\u0003'\u0019\u0019\u0005\u0005\u0003\u0002\u0016\r\u0015Ca\u0002B!u\t\u0007!1\t\u0005\b\u0007\u0013R\u0004\u0019AB&\u0003\u0015)G.Z72!!\ty\"!\u0015\u0002\u0014\r\r\u0003bBB(u\u0001\u000711J\u0001\u0006K2,WN\r\u0005\b\u0007'R\u0004\u0019AB+\u0003\u0015)G.Z7t!\u0019\tyba\u0016\u0004L%!1\u0011LA\u0001\u0005)a$/\u001a9fCR,GM\u0010\u0015\fu\tU%1TB/\u0005C\u001bI$\t\u0002\u0004`\u0005)Uk]3!W-\u0002s/\u001b;iA\u0005t\u0007%\u001a=qY&\u001c\u0017\u000e\u001e\u0011d_2dWm\u0019;j_:\u0004\u0013M]4v[\u0016tG\u000fI5ogR,\u0017\r\u001a\u0011pM\u0002Z\u0003e^5uQ\u00022\u0018M]1sON\faaY8oG\u0006$X\u0003BB3\u0007W\"Baa\u001a\u0004pA9\u0011Q\u0002\u0001\u0002\u0014\r%\u0004\u0003BA\u000b\u0007W\"qa!\u001c<\u0005\u0004\u0011\u0019E\u0001\u0002We!91\u0011O\u001eA\u0002\rM\u0014A\u0001=t!\u0019\tI%a9\u0004vAA\u0011qDA)\u0003'\u0019I'\u0001\u0006%a2,8\u000f\n9mkN,Baa\u001f\u0004\u0002R!1QPBB!\u001d\ti\u0001AA\n\u0007\u007f\u0002B!!\u0006\u0004\u0002\u001291Q\u000e\u001fC\u0002\t\r\u0003bBB9y\u0001\u00071Q\u0011\t\u0007\u0003\u0013\n\u0019oa\"\u0011\u0011\u0005}\u0011\u0011KA\n\u0007\u007f\nq!\u001e9eCR,G-\u0006\u0003\u0004\u000e\u000eMECBBH\u0007+\u001b9\nE\u0004\u0002\u000e\u0001\t\u0019b!%\u0011\t\u0005U11\u0013\u0003\b\u0005\u0003j$\u0019\u0001B\"\u0011\u001d\u00119!\u0010a\u0001\u0003'AqAa >\u0001\u0004\u0019\t\nK\u0006>\u0005+\u0013Yja'\u0003\"\u000ee\u0012EABO\u0003Q*6/\u001a\u0011n]\rdwN\\3)S9\nG\rZ(oK\"ZGF^\u0015!S:\u001cH/Z1eA=4\u0007%\u001c\u0018va\u0012\fG/\u001a3)W2\u0002c/K\u0001\u000fM>\u0014X-Y2i\u000b2,W.\u001a8u+\u0019\u0019\u0019k!,\u00042R1\u00111WBS\u0007OCqaa\u0015?\u0001\u0004\tY\u000bC\u0004\u0004\u0006y\u0002\ra!+\u0011\u0011\u0005}\u0011QNBV\u0007_\u0003B!!\u0006\u0004.\u00129!Q\u001d C\u0002\u0005E\u0002\u0003BA\u000b\u0007c#qaa-?\u0005\u0004\t\tDA\u0001C\u0003)1wN]3bG\"\\U-_\u000b\u0005\u0007s\u001b\t\r\u0006\u0003\u00024\u000em\u0006bBB\u0003\u007f\u0001\u00071Q\u0018\t\t\u0003?\ti'a\u0005\u0004@B!\u0011QCBa\t\u001d\u0011)o\u0010b\u0001\u0003c\tABZ8sK\u0006\u001c\u0007NV1mk\u0016,Baa2\u0004PR!\u00111WBe\u0011\u001d\u0019)\u0001\u0011a\u0001\u0007\u0017\u0004\u0002\"a\b\u0002n\u0005-2Q\u001a\t\u0005\u0003+\u0019y\rB\u0004\u0003f\u0002\u0013\r!!\r\u0002\u00195\f\u0007OV1mk\u0016\u001chj\\<\u0016\t\rU71\u001c\u000b\u0005\u0007/\u001ci\u000eE\u0004\u0002\u000e\u0001\t\u0019b!7\u0011\t\u0005U11\u001c\u0003\b\u0005\u0003\n%\u0019AA\u0019\u0011\u001d\u0019)!\u0011a\u0001\u0007?\u0004\u0002\"a\b\u0002n\u0005-2\u0011\\\u0001\u0010iJ\fgn\u001d4pe64\u0016\r\\;fgR!!QRBs\u0011\u001d\u0019)A\u0011a\u0001\u0007O\u0004\u0002\"a\b\u0002n\u0005-\u00121\u0006\u0015\f\u0005\nU%1TBv\u0005C\u001bI$\t\u0002\u0004n\u0006)Tk]3!iJ\fgn\u001d4pe64\u0016\r\\;fg&s\u0007\u000b\\1dK\u0002Jgn\u001d;fC\u0012\u0004sN\u001a\u0011ue\u0006t7OZ8s[Z\u000bG.^3tQ\r\u0011%qD\u0001\u0017iJ\fgn\u001d4pe64\u0016\r\\;fg&s\u0007\u000b\\1dKR!!QRB{\u0011\u001d\u0019)a\u0011a\u0001\u0007O\f1!\\1q+\u0019\u0019Y\u0010b\u0001\u0005\nQ!1Q C\u000b)\u0011\u0019y\u0010b\u0003\u0011\u000f\u00055\u0001\u0001\"\u0001\u0005\bA!\u0011Q\u0003C\u0002\t\u001d!)\u0001\u0012b\u0001\u00037\u0011!a\u0013\u001a\u0011\t\u0005UA\u0011\u0002\u0003\b\u0007[\"%\u0019AA\u0019\u0011\u001d!i\u0001\u0012a\u0002\t\u001f\tQ\u0001Z;n[f\u0004B!a\b\u0005\u0012%!A1CA\u0001\u00055!U/\\7z\u00136\u0004H.[2ji\"91Q\u0001#A\u0002\u0011]\u0001\u0003CA\u0010\u0003[\ny\u0005\"\u0007\u0011\u0011\u0005}\u0011\u0011\u000bC\u0001\t\u000f\tqA\u001a7bi6\u000b\u0007/\u0006\u0004\u0005 \u0011\u001dB1\u0006\u000b\u0005\tC!y\u0003\u0006\u0003\u0005$\u00115\u0002cBA\u0007\u0001\u0011\u0015B\u0011\u0006\t\u0005\u0003+!9\u0003B\u0004\u0005\u0006\u0015\u0013\r!a\u0007\u0011\t\u0005UA1\u0006\u0003\b\u0007[*%\u0019AA\u0019\u0011\u001d!i!\u0012a\u0002\t\u001fAqa!\u0002F\u0001\u0004!\t\u0004\u0005\u0005\u0002 \u00055\u0014q\nC\u001a!\u0019\tI%a9\u00056AA\u0011qDA)\tK!I#A\u0004d_2dWm\u0019;\u0016\r\u0011mB1\tC$)\u0011!i\u0004b\u0013\u0015\t\u0011}B\u0011\n\t\b\u0003\u001b\u0001A\u0011\tC#!\u0011\t)\u0002b\u0011\u0005\u000f\u0011\u0015aI1\u0001\u0002\u001cA!\u0011Q\u0003C$\t\u001d\u0019iG\u0012b\u0001\u0003cAq\u0001\"\u0004G\u0001\b!y\u0001C\u0004\u0005N\u0019\u0003\r\u0001b\u0014\u0002\u0005A4\u0007\u0003CA\u0010\t#\ny\u0005\"\u0016\n\t\u0011M\u0013\u0011\u0001\u0002\u0010!\u0006\u0014H/[1m\rVt7\r^5p]BA\u0011qDA)\t\u0003\")%A\u0003dY\u0016\f'/\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002&\u0005a1\u000f\u001e:j]\u001e\u0004&/\u001a4jqV\u0011A\u0011\r\t\u0005\tG\"i'\u0004\u0002\u0005f)!Aq\rC5\u0003\u0011a\u0017M\\4\u000b\u0005\u0011-\u0014\u0001\u00026bm\u0006LA\u0001b\u001c\u0005f\t11\u000b\u001e:j]\u001eD3\u0002\u0001C:\u00057#9I!)\u0005\f*\"!Q\u0013C;W\t!9\b\u0005\u0003\u0005z\u0011\rUB\u0001C>\u0015\u0011!i\bb \u0002\t5,G/\u0019\u0006\u0005\t\u0003\u000b\t!\u0001\u0006b]:|G/\u0019;j_:LA\u0001\"\"\u0005|\tq1m\\7qC:LwN\\\"mCN\u001c\u0018E\u0001CE\u0003\u0019+6/\u001a\u0011ag\u000e\fG.\u0019\u0018d_2dWm\u0019;j_:tS.\u001e;bE2,g\u0006S1tQ6\u000b\u0007\u000f\u0019\u0011j]N$X-\u00193!M>\u0014\bEY3ui\u0016\u0014\b\u0005]3sM>\u0014X.\u00198dK:\n#\u0001\"$\u0002\u000fIr\u0013g\r\u00182m\u0005I\u0011I\\=SK\u001al\u0015\r\u001d\t\u0004\u0003\u001bY5#B&\u0002&\u0011U\u0005\u0003\u0002CL\t;k!\u0001\"'\u000b\t\u0011mE\u0011N\u0001\u0003S>LA!!\u001a\u0005\u001aR\u0011A\u0011S\u0001\n\u0013:$W\r_'bg.,\"\u0001\"*\u0010\u0005\u0011\u001dV\u0004B \u0000\u0000\u0000\u0010!\"\u00138eKbl\u0015m]6!\u0003)i\u0015n]:j]\u001e\u0014\u0015\u000e^\u000b\u0003\t_{!\u0001\"-\u001e\t\u0001\u0005\u0001\u0001A\u0001\f\u001b&\u001c8/\u001b8h\u0005&$\b%A\u0005WC\u000e\fg\u000e\u001e\"jiV\u0011A\u0011X\b\u0003\twkB\u0001\u0011\u0001\u0001\u0001\u0005Qa+Y2b]R\u0014\u0015\u000e\u001e\u0011\u0002\u00155K7o\u001d,bG\u0006tG/\u0006\u0002\u0005D>\u0011AQY\u000f\u0005\u0001\u0004\u0001\u0001!A\u0006NSN\u001ch+Y2b]R\u0004#\u0001E#yG\u0016\u0004H/[8o\t\u00164\u0017-\u001e7u'\u001d)\u0016Q\u0005Cg\u00037\u0002\u0002\"a\b\u0002n\u0005M\u0012Q\u0004\u000b\u0003\t#\u00042\u0001b5V\u001b\u0005YE\u0003BA\u000f\t/DqAa\u0005X\u0001\u0004\t\u0019$\u0001\tfq\u000e,\u0007\u000f^5p]\u0012+g-Y;miV\u0011A\u0011[\u0001\u0012Kb\u001cW\r\u001d;j_:$UMZ1vYR\u0004#\u0001E!osJ+g-T1q\u0005VLG\u000eZ3s+\u0019!\u0019\u000fb<\u0005tN)!,!\n\u0005fBA\u0011Q\u0002Ct\tW$)0C\u0002\u0005jr\u0014qBU3vg\u0006\u0014G.\u001a\"vS2$WM\u001d\t\t\u0003?\t\t\u0006\"<\u0005rB!\u0011Q\u0003Cx\t\u001d\tIB\u0017b\u0001\u00037\u0001B!!\u0006\u0005t\u00129\u0011q\u0006.C\u0002\u0005E\u0002cBA\u0007\u0001\u00115H\u0011\u001f\u000b\u0003\ts\u0004r\u0001b5[\t[$\t0\u0006\u0002\u0005v\u0006IQ\r\\3ng~#S-\u001d\u000b\u0005\u0003g+\t\u0001C\u0005\u0006\u0004u\u000b\t\u00111\u0001\u0005v\u0006\u0019\u0001\u0010J\u0019\u0002\r\u0015dW-\\:!)\u0011)I!b\u0003\u000e\u0003iCq!\"\u0004`\u0001\u0004!Y/A\u0003f]R\u0014\u00180\u0001\u0004sKN,H\u000e\u001e\u000b\u0003\tk,b!\"\u0006\u0006\u001c\u0015}A\u0003BC\f\u000bC\u0001r!!\u0004\u0001\u000b3)i\u0002\u0005\u0003\u0002\u0016\u0015mAaBA\rG\n\u0007\u00111\u0004\t\u0005\u0003+)y\u0002B\u0004\u00020\r\u0014\r!!\r\t\u000f\rM3\r1\u0001\u0006$A1\u0011qDB,\u000bK\u0001\u0002\"a\b\u0002R\u0015eQQD\u0001\u000b]\u0016<()^5mI\u0016\u0014XCBC\u0016\u000bg)9$\u0006\u0002\u0006.AA\u0011Q\u0002Ct\u000b_)I\u0004\u0005\u0005\u0002 \u0005ES\u0011GC\u001b!\u0011\t)\"b\r\u0005\u000f\u0005eAM1\u0001\u0002\u001cA!\u0011QCC\u001c\t\u001d\ty\u0003\u001ab\u0001\u0003c\u0001r!!\u0004\u0001\u000bc))$A\u000bck&dGM\u0012:p[&#XM]1cY\u0016|enY3\u0016\r\u0015}RQIC%)\u0011)\t%b\u0013\u0011\u000f\u00055\u0001!b\u0011\u0006HA!\u0011QCC#\t\u001d\tI\"\u001ab\u0001\u00037\u0001B!!\u0006\u0006J\u00119\u0011qF3C\u0002\u0005E\u0002bBB*K\u0002\u0007QQ\n\t\u0007\u0003\u0013\n\u0019/b\u0014\u0011\u0011\u0005}\u0011\u0011KC\"\u000b\u000f*b!b\u0015\u0006Z\u0015uSCAC+!\u001d\ti\u0001AC,\u000b7\u0002B!!\u0006\u0006Z\u00119\u0011\u0011\u00044C\u0002\u0005m\u0001\u0003BA\u000b\u000b;\"q!a\fg\u0005\u0004\t\t$A\u0006xSRDG)\u001a4bk2$XCBC2\u000bS*i\u0007\u0006\u0003\u0006f\u0015=\u0004cBA\u0007\u0001\u0015\u001dT1\u000e\t\u0005\u0003+)I\u0007B\u0004\u0002\u001a\u001d\u0014\r!a\u0007\u0011\t\u0005UQQ\u000e\u0003\b\u0003_9'\u0019AA\u0019\u0011\u001d\u0011Ie\u001aa\u0001\u000bc\u0002\u0002\"a\b\u0002n\u0015\u001dT1N\u0001\u0005MJ|W.\u0006\u0004\u0006x\u0015uT\u0011\u0011\u000b\u0005\u000bs*\u0019\tE\u0004\u0002\u000e\u0001)Y(b \u0011\t\u0005UQQ\u0010\u0003\b\u00033A'\u0019AA\u000e!\u0011\t)\"\"!\u0005\u000f\u0005=\u0002N1\u0001\u00022!9QQ\u00115A\u0002\u0015\u001d\u0015AB:pkJ\u001cW\r\u0005\u0004\u0002J\u0005\rX\u0011\u0012\t\t\u0003?\t\t&b\u001f\u0006\u0000\u00059aM]8n5&\u0004XCBCH\u000b++I\n\u0006\u0004\u0006\u0012\u0016mU\u0011\u0015\t\b\u0003\u001b\u0001Q1SCL!\u0011\t)\"\"&\u0005\u000f\u0005e\u0011N1\u0001\u0002\u001cA!\u0011QCCM\t\u001d\ty#\u001bb\u0001\u0003cAq!\"(j\u0001\u0004)y*\u0001\u0003lKf\u001c\bCBA\u0010\u0003K+\u0019\nC\u0004\u0006$&\u0004\r!\"*\u0002\rY\fG.^3t!\u0019\ty\"!*\u0006\u0018V1Q\u0011VCX\u000bg#b!b+\u00066\u0016e\u0006cBA\u0007\u0001\u00155V\u0011\u0017\t\u0005\u0003+)y\u000bB\u0004\u0002\u001a)\u0014\r!a\u0007\u0011\t\u0005UQ1\u0017\u0003\b\u0003_Q'\u0019AA\u0019\u0011\u001d)iJ\u001ba\u0001\u000bo\u0003b!!\u0004\u0002X\u00155\u0006bBCRU\u0002\u0007Q1\u0018\t\u0007\u0003\u001b\t9&\"-\u0002\u0013Q|g)Y2u_JLXCBCa\u000b\u001b,\t\u000e\u0006\u0003\u0006D\u0016U\u0007\u0003CA%\u000b\u000b,I-b5\n\u0007\u0015\u001dgPA\u0004GC\u000e$xN]=\u0011\u0011\u0005}\u0011\u0011KCf\u000b\u001f\u0004B!!\u0006\u0006N\u00129\u0011\u0011D6C\u0002\u0005m\u0001\u0003BA\u000b\u000b#$q!a\fl\u0005\u0004\t\t\u0004E\u0004\u0002\u000e\u0001)Y-b4\t\u000f\u001151\u000e1\u0001\u0006X:\u0019\u0011Q\u0002&\u0002\u0013Q{g)Y2u_JL\bc\u0001Cj[\nIAk\u001c$bGR|'/_\n\b[\u0006\u0015R\u0011]A.!!\tI%\"2\u0006d\u0016\u0015\b\u0003CA\u0010\u0003#\n)#!\n\u0011\u000f\u00055\u0001!!\n\u0002&Q\u0011Q1\u001c\u000b\u0005\u000bK,Y\u000fC\u0004\u0006n>\u0004\r!b<\u0002\u0005%$\bCBA%\u0003G,\u0019/\u0006\u0002\u0006tBA\u0011QBAw\u000bG,)\u000f\u0006\u0002\u0006xB!A1MC}\u0013\u0011)Y\u0010\"\u001a\u0003\r=\u0013'.Z2uQ\u001diWq B@\r\u000b\u0001B!a\b\u0007\u0002%!a1AA\u0001\u0005A\u0019VM]5bYZ+'o]5p]VKEIH\u0001\u0004Q\u001daWq B@\r\u000b\t1\u0002^8Ck&dGM\u0012:p[V1aQ\u0002D\r\r;!BAb\u0004\u0007\"AQ\u0011\u0011\nD\t\u0003g1)Bb\b\n\u0007\u0019MaPA\u0005Ck&dGM\u0012:p[BA\u0011qDA)\r/1Y\u0002\u0005\u0003\u0002\u0016\u0019eAaBA\re\n\u0007\u00111\u0004\t\u0005\u0003+1i\u0002B\u0004\u00020I\u0014\r!!\r\u0011\u000f\u00055\u0001Ab\u0006\u0007\u001c!9a1\u0005:A\u0002\u0015]\u0017a\u00024bGR|'/_\u0001\f)>\u0014U/\u001b7e\rJ|W\u000eE\u0002\u0005TR\u00141\u0002V8Ck&dGM\u0012:p[N)A/!\n\u0007.AQ\u0011\u0011\nD\t\u0003g)\u0019/\":\u0015\u0005\u0019\u001dB\u0003\u0002D\u001a\ro!B!\":\u00076!9QQ\u001e<A\u0002\u0015=\bbBC:m\u0002\u0007\u00111\u0007\u000b\u0005\rw1i\u0004\u0005\u0005\u0002\u000e\u0011\u001dX1]Cs\u0011\u001d)\u0019h\u001ea\u0001\u0003g\tq\"\u001b;fe\u0006\u0014G.\u001a$bGR|'/_\u000b\u0007\r\u00072YEb\u0014\u0016\u0005\u0019\u0015\u0003\u0003CA%\u000b\u000b49E\"\u0015\u0011\u0011\u0005}\u0011\u0011\u000bD%\r\u001b\u0002B!!\u0006\u0007L\u00119\u0011\u0011\u0004=C\u0002\u0005m\u0001\u0003BA\u000b\r\u001f\"q!a\fy\u0005\u0004\t\t\u0004E\u0004\u0002\u000e\u00011IE\"\u0014\u0002%\t,\u0018\u000e\u001c3Ge>l\u0017I\\=SK\u001al\u0015\r]\u000b\u0007\r/2yGb\u001d\u0016\u0005\u0019e\u0003CCA%\r#1YFb\u001b\u0007vA2aQ\fD1\rO\u0002r!!\u0004\u0001\r?2)\u0007\u0005\u0003\u0002\u0016\u0019\u0005Da\u0003D2s\u0006\u0005\t\u0011!B\u0001\u0003c\u00111a\u0018\u00132!\u0011\t)Bb\u001a\u0005\u0017\u0019%\u00140!A\u0001\u0002\u000b\u0005\u0011\u0011\u0007\u0002\u0004?\u0012\u0012\u0004\u0003CA\u0010\u0003#2iG\"\u001d\u0011\t\u0005Uaq\u000e\u0003\b\u00033I(\u0019AA\u000e!\u0011\t)Bb\u001d\u0005\u000f\u0005=\u0012P1\u0001\u00022A9\u0011Q\u0002\u0001\u0007n\u0019E\u0004fC&\u0003\u0016\nmEq\u0011BQ\t\u0017C3B\u0013BK\u00057#9I!)\u0005\f\u0002"
)
public class AnyRefMap extends AbstractMap implements StrictOptimizedIterableOps, Serializable {
   private final Function1 defaultEntry;
   public int scala$collection$mutable$AnyRefMap$$mask;
   private int _size;
   private int _vacant;
   public int[] scala$collection$mutable$AnyRefMap$$_hashes;
   public Object[] scala$collection$mutable$AnyRefMap$$_keys;
   public Object[] scala$collection$mutable$AnyRefMap$$_values;

   public static BuildFrom buildFromAnyRefMap() {
      AnyRefMap$ var10000 = AnyRefMap$.MODULE$;
      return AnyRefMap.ToBuildFrom$.MODULE$;
   }

   public static BuildFrom toBuildFrom(final AnyRefMap$ factory) {
      AnyRefMap$ var10000 = AnyRefMap$.MODULE$;
      return AnyRefMap.ToBuildFrom$.MODULE$;
   }

   public static Factory toFactory(final AnyRefMap$ dummy) {
      AnyRefMap$ var10000 = AnyRefMap$.MODULE$;
      return AnyRefMap.ToFactory$.MODULE$;
   }

   public static AnyRefMap fromZip(final Iterable keys, final Iterable values) {
      return AnyRefMap$.MODULE$.fromZip(keys, values);
   }

   public static AnyRefMap fromZip(final Object[] keys, final Object values) {
      return AnyRefMap$.MODULE$.fromZip(keys, values);
   }

   public static AnyRefMap from(final IterableOnce source) {
      return AnyRefMap$.MODULE$.from(source);
   }

   public static ReusableBuilder newBuilder() {
      AnyRefMap$ var10000 = AnyRefMap$.MODULE$;
      return new AnyRefMapBuilder();
   }

   public Tuple2 partition(final Function1 p) {
      return StrictOptimizedIterableOps.partition$(this, p);
   }

   public Tuple2 span(final Function1 p) {
      return StrictOptimizedIterableOps.span$(this, p);
   }

   public Tuple2 unzip(final Function1 asPair) {
      return StrictOptimizedIterableOps.unzip$(this, asPair);
   }

   public Tuple3 unzip3(final Function1 asTriple) {
      return StrictOptimizedIterableOps.unzip3$(this, asTriple);
   }

   public Object map(final Function1 f) {
      return StrictOptimizedIterableOps.map$(this, f);
   }

   public final Object strictOptimizedMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedMap$(this, b, f);
   }

   public Object flatMap(final Function1 f) {
      return StrictOptimizedIterableOps.flatMap$(this, f);
   }

   public final Object strictOptimizedFlatMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedFlatMap$(this, b, f);
   }

   public final Object strictOptimizedConcat(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedConcat$(this, that, b);
   }

   public Object collect(final PartialFunction pf) {
      return StrictOptimizedIterableOps.collect$(this, pf);
   }

   public final Object strictOptimizedCollect(final Builder b, final PartialFunction pf) {
      return StrictOptimizedIterableOps.strictOptimizedCollect$(this, b, pf);
   }

   public Object flatten(final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.flatten$(this, toIterableOnce);
   }

   public final Object strictOptimizedFlatten(final Builder b, final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.strictOptimizedFlatten$(this, b, toIterableOnce);
   }

   public Object zip(final IterableOnce that) {
      return StrictOptimizedIterableOps.zip$(this, that);
   }

   public final Object strictOptimizedZip(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedZip$(this, that, b);
   }

   public Object zipWithIndex() {
      return StrictOptimizedIterableOps.zipWithIndex$(this);
   }

   public Object scanLeft(final Object z, final Function2 op) {
      return StrictOptimizedIterableOps.scanLeft$(this, z, op);
   }

   public Object filter(final Function1 pred) {
      return StrictOptimizedIterableOps.filter$(this, pred);
   }

   public Object filterNot(final Function1 pred) {
      return StrictOptimizedIterableOps.filterNot$(this, pred);
   }

   public Object filterImpl(final Function1 pred, final boolean isFlipped) {
      return StrictOptimizedIterableOps.filterImpl$(this, pred, isFlipped);
   }

   public Tuple2 partitionMap(final Function1 f) {
      return StrictOptimizedIterableOps.partitionMap$(this, f);
   }

   public Object tapEach(final Function1 f) {
      return StrictOptimizedIterableOps.tapEach$(this, f);
   }

   public Object takeRight(final int n) {
      return StrictOptimizedIterableOps.takeRight$(this, n);
   }

   public Object dropRight(final int n) {
      return StrictOptimizedIterableOps.dropRight$(this, n);
   }

   private void defaultInitialize(final int n) {
      this.scala$collection$mutable$AnyRefMap$$mask = n < 0 ? 7 : (1 << 32 - Integer.numberOfLeadingZeros(n - 1)) - 1 & 1073741823 | 7;
      this.scala$collection$mutable$AnyRefMap$$_hashes = new int[this.scala$collection$mutable$AnyRefMap$$mask + 1];
      this.scala$collection$mutable$AnyRefMap$$_keys = new Object[this.scala$collection$mutable$AnyRefMap$$mask + 1];
      this.scala$collection$mutable$AnyRefMap$$_values = new Object[this.scala$collection$mutable$AnyRefMap$$mask + 1];
   }

   public void initializeTo(final int m, final int sz, final int vc, final int[] hz, final Object[] kz, final Object[] vz) {
      this.scala$collection$mutable$AnyRefMap$$mask = m;
      this._size = sz;
      this._vacant = vc;
      this.scala$collection$mutable$AnyRefMap$$_hashes = hz;
      this.scala$collection$mutable$AnyRefMap$$_keys = kz;
      this.scala$collection$mutable$AnyRefMap$$_values = vz;
   }

   public AnyRefMap fromSpecific(final IterableOnce coll) {
      int sz = coll.knownSize();
      if (sz < 0) {
         sz = 4;
      }

      AnyRefMap arm = new AnyRefMap(sz * 2);
      coll.iterator().foreach((x0$1) -> {
         $anonfun$fromSpecific$1(arm, x0$1);
         return BoxedUnit.UNIT;
      });
      if (arm.size() < sz >> 3) {
         arm.repack();
      }

      return arm;
   }

   public Builder newSpecificBuilder() {
      return new AnyRefMapBuilder();
   }

   public int size() {
      return this._size;
   }

   public int knownSize() {
      return this.size();
   }

   public boolean isEmpty() {
      return this._size == 0;
   }

   public AnyRefMap empty() {
      return new AnyRefMap(this.defaultEntry);
   }

   private boolean imbalanced() {
      return (double)(this._size + this._vacant) > (double)0.5F * (double)this.scala$collection$mutable$AnyRefMap$$mask || this._vacant > this._size;
   }

   private int hashOf(final Object key) {
      if (key == null) {
         return 1091049865;
      } else {
         int h = key.hashCode();
         int i = (h ^ h >>> 16) * -2048144789;
         int j = (i ^ i >>> 13) & Integer.MAX_VALUE;
         return j == 0 ? 1091049865 : j;
      }
   }

   private int seekEntry(final int h, final Object k) {
      int e = h & this.scala$collection$mutable$AnyRefMap$$mask;
      int x = 0;
      int[] hashes = this.scala$collection$mutable$AnyRefMap$$_hashes;
      Object[] keys = this.scala$collection$mutable$AnyRefMap$$_keys;

      while(true) {
         int g = hashes[e];
         if (g == 0) {
            return e | Integer.MIN_VALUE;
         }

         if (g == h) {
            Object q = keys[e];
            if (q == k || q != null && q.equals(k)) {
               return e;
            }
         }

         ++x;
         e = e + 2 * (x + 1) * x - 3 & this.scala$collection$mutable$AnyRefMap$$mask;
      }
   }

   private int seekEntryOrOpen(final int h, final Object k) {
      int e = h & this.scala$collection$mutable$AnyRefMap$$mask;
      int x = 0;
      int o = -1;

      while(true) {
         int g = this.scala$collection$mutable$AnyRefMap$$_hashes[e];
         if (g == 0) {
            if (o >= 0) {
               return o | -1073741824;
            }

            return e | Integer.MIN_VALUE;
         }

         if (g == h) {
            Object q = this.scala$collection$mutable$AnyRefMap$$_keys[e];
            if (q == k || q != null && q.equals(k)) {
               return e;
            }
         }

         if (o == -1 && g + g == 0) {
            o = e;
         }

         ++x;
         e = e + 2 * (x + 1) * x - 3 & this.scala$collection$mutable$AnyRefMap$$mask;
      }
   }

   public boolean contains(final Object key) {
      return this.seekEntry(this.hashOf(key), key) >= 0;
   }

   public Option get(final Object key) {
      int i = this.seekEntry(this.hashOf(key), key);
      return (Option)(i < 0 ? None$.MODULE$ : new Some(this.scala$collection$mutable$AnyRefMap$$_values[i]));
   }

   public Object getOrElse(final Object key, final Function0 default) {
      int i = this.seekEntry(this.hashOf(key), key);
      return i < 0 ? default.apply() : this.scala$collection$mutable$AnyRefMap$$_values[i];
   }

   public Object getOrElseUpdate(final Object key, final Function0 defaultValue) {
      int h = this.hashOf(key);
      int seekEntryOrOpen_e = h & this.scala$collection$mutable$AnyRefMap$$mask;
      int seekEntryOrOpen_x = 0;
      int seekEntryOrOpen_o = -1;

      int var10000;
      while(true) {
         int seekEntryOrOpen_g = this.scala$collection$mutable$AnyRefMap$$_hashes[seekEntryOrOpen_e];
         if (seekEntryOrOpen_g == 0) {
            var10000 = seekEntryOrOpen_o >= 0 ? seekEntryOrOpen_o | -1073741824 : seekEntryOrOpen_e | Integer.MIN_VALUE;
            break;
         }

         if (seekEntryOrOpen_g == h) {
            Object seekEntryOrOpen_q = this.scala$collection$mutable$AnyRefMap$$_keys[seekEntryOrOpen_e];
            if (seekEntryOrOpen_q == key || seekEntryOrOpen_q != null && seekEntryOrOpen_q.equals(key)) {
               var10000 = seekEntryOrOpen_e;
               break;
            }
         }

         if (seekEntryOrOpen_o == -1 && seekEntryOrOpen_g + seekEntryOrOpen_g == 0) {
            seekEntryOrOpen_o = seekEntryOrOpen_e;
         }

         ++seekEntryOrOpen_x;
         seekEntryOrOpen_e = seekEntryOrOpen_e + 2 * (seekEntryOrOpen_x + 1) * seekEntryOrOpen_x - 3 & this.scala$collection$mutable$AnyRefMap$$mask;
      }

      Object var20 = null;
      int i = var10000;
      if (i >= 0) {
         return this.scala$collection$mutable$AnyRefMap$$_values[i];
      } else {
         int[] ohs = this.scala$collection$mutable$AnyRefMap$$_hashes;
         int j = i & 1073741823;
         int oh = ohs[j];
         Object ans = defaultValue.apply();
         if (ohs != this.scala$collection$mutable$AnyRefMap$$_hashes || oh != this.scala$collection$mutable$AnyRefMap$$_hashes[j]) {
            int seekEntryOrOpen_e = h & this.scala$collection$mutable$AnyRefMap$$mask;
            int seekEntryOrOpen_x = 0;
            int seekEntryOrOpen_o = -1;

            while(true) {
               int seekEntryOrOpen_g = this.scala$collection$mutable$AnyRefMap$$_hashes[seekEntryOrOpen_e];
               if (seekEntryOrOpen_g == 0) {
                  var10000 = seekEntryOrOpen_o >= 0 ? seekEntryOrOpen_o | -1073741824 : seekEntryOrOpen_e | Integer.MIN_VALUE;
                  break;
               }

               if (seekEntryOrOpen_g == h) {
                  Object seekEntryOrOpen_q = this.scala$collection$mutable$AnyRefMap$$_keys[seekEntryOrOpen_e];
                  if (seekEntryOrOpen_q == key || seekEntryOrOpen_q != null && seekEntryOrOpen_q.equals(key)) {
                     var10000 = seekEntryOrOpen_e;
                     break;
                  }
               }

               if (seekEntryOrOpen_o == -1 && seekEntryOrOpen_g + seekEntryOrOpen_g == 0) {
                  seekEntryOrOpen_o = seekEntryOrOpen_e;
               }

               ++seekEntryOrOpen_x;
               seekEntryOrOpen_e = seekEntryOrOpen_e + 2 * (seekEntryOrOpen_x + 1) * seekEntryOrOpen_x - 3 & this.scala$collection$mutable$AnyRefMap$$mask;
            }

            Object var21 = null;
            i = var10000;
            if (i >= 0) {
               --this._size;
            }
         }

         ++this._size;
         int j = i & 1073741823;
         this.scala$collection$mutable$AnyRefMap$$_hashes[j] = h;
         this.scala$collection$mutable$AnyRefMap$$_keys[j] = key;
         this.scala$collection$mutable$AnyRefMap$$_values[j] = ans;
         if ((i & 1073741824) != 0) {
            --this._vacant;
         } else if (this.imbalanced()) {
            this.repack();
         }

         return ans;
      }
   }

   public Object getOrNull(final Object key) {
      int i = this.seekEntry(this.hashOf(key), key);
      return i < 0 ? null : this.scala$collection$mutable$AnyRefMap$$_values[i];
   }

   public Object apply(final Object key) {
      int i = this.seekEntry(this.hashOf(key), key);
      return i < 0 ? this.defaultEntry.apply(key) : this.scala$collection$mutable$AnyRefMap$$_values[i];
   }

   public Object default(final Object key) {
      return this.defaultEntry.apply(key);
   }

   private void repack(final int newMask) {
      int[] oh = this.scala$collection$mutable$AnyRefMap$$_hashes;
      Object[] ok = this.scala$collection$mutable$AnyRefMap$$_keys;
      Object[] ov = this.scala$collection$mutable$AnyRefMap$$_values;
      this.scala$collection$mutable$AnyRefMap$$mask = newMask;
      this.scala$collection$mutable$AnyRefMap$$_hashes = new int[this.scala$collection$mutable$AnyRefMap$$mask + 1];
      this.scala$collection$mutable$AnyRefMap$$_keys = new Object[this.scala$collection$mutable$AnyRefMap$$mask + 1];
      this.scala$collection$mutable$AnyRefMap$$_values = new Object[this.scala$collection$mutable$AnyRefMap$$mask + 1];
      this._vacant = 0;

      for(int i = 0; i < oh.length; ++i) {
         int h = oh[i];
         if (h + h != 0) {
            int e = h & this.scala$collection$mutable$AnyRefMap$$mask;

            for(int x = 0; this.scala$collection$mutable$AnyRefMap$$_hashes[e] != 0; e = e + 2 * (x + 1) * x - 3 & this.scala$collection$mutable$AnyRefMap$$mask) {
               ++x;
            }

            this.scala$collection$mutable$AnyRefMap$$_hashes[e] = h;
            this.scala$collection$mutable$AnyRefMap$$_keys[e] = ok[i];
            this.scala$collection$mutable$AnyRefMap$$_values[e] = ov[i];
         }
      }

   }

   public void repack() {
      int m = this.scala$collection$mutable$AnyRefMap$$mask;
      if ((double)(this._size + this._vacant) >= (double)0.5F * (double)this.scala$collection$mutable$AnyRefMap$$mask && !((double)this._vacant > 0.2 * (double)this.scala$collection$mutable$AnyRefMap$$mask)) {
         m = (m << 1) + 1 & 1073741823;
      }

      while(m > 8 && 8 * this._size < m) {
         m >>>= 1;
      }

      this.repack(m);
   }

   public Option put(final Object key, final Object value) {
      int h = this.hashOf(key);
      int seekEntryOrOpen_e = h & this.scala$collection$mutable$AnyRefMap$$mask;
      int seekEntryOrOpen_x = 0;
      int seekEntryOrOpen_o = -1;

      int var10000;
      while(true) {
         int seekEntryOrOpen_g = this.scala$collection$mutable$AnyRefMap$$_hashes[seekEntryOrOpen_e];
         if (seekEntryOrOpen_g == 0) {
            var10000 = seekEntryOrOpen_o >= 0 ? seekEntryOrOpen_o | -1073741824 : seekEntryOrOpen_e | Integer.MIN_VALUE;
            break;
         }

         if (seekEntryOrOpen_g == h) {
            Object seekEntryOrOpen_q = this.scala$collection$mutable$AnyRefMap$$_keys[seekEntryOrOpen_e];
            if (seekEntryOrOpen_q == key || seekEntryOrOpen_q != null && seekEntryOrOpen_q.equals(key)) {
               var10000 = seekEntryOrOpen_e;
               break;
            }
         }

         if (seekEntryOrOpen_o == -1 && seekEntryOrOpen_g + seekEntryOrOpen_g == 0) {
            seekEntryOrOpen_o = seekEntryOrOpen_e;
         }

         ++seekEntryOrOpen_x;
         seekEntryOrOpen_e = seekEntryOrOpen_e + 2 * (seekEntryOrOpen_x + 1) * seekEntryOrOpen_x - 3 & this.scala$collection$mutable$AnyRefMap$$mask;
      }

      Object var12 = null;
      int i = var10000;
      if (i < 0) {
         int j = i & 1073741823;
         this.scala$collection$mutable$AnyRefMap$$_hashes[j] = h;
         this.scala$collection$mutable$AnyRefMap$$_keys[j] = key;
         this.scala$collection$mutable$AnyRefMap$$_values[j] = value;
         ++this._size;
         if ((i & 1073741824) != 0) {
            --this._vacant;
         } else if (this.imbalanced()) {
            this.repack();
         }

         return None$.MODULE$;
      } else {
         Some ans = new Some(this.scala$collection$mutable$AnyRefMap$$_values[i]);
         this.scala$collection$mutable$AnyRefMap$$_hashes[i] = h;
         this.scala$collection$mutable$AnyRefMap$$_values[i] = value;
         return ans;
      }
   }

   public void update(final Object key, final Object value) {
      int h = this.hashOf(key);
      int seekEntryOrOpen_e = h & this.scala$collection$mutable$AnyRefMap$$mask;
      int seekEntryOrOpen_x = 0;
      int seekEntryOrOpen_o = -1;

      int var10000;
      while(true) {
         int seekEntryOrOpen_g = this.scala$collection$mutable$AnyRefMap$$_hashes[seekEntryOrOpen_e];
         if (seekEntryOrOpen_g == 0) {
            var10000 = seekEntryOrOpen_o >= 0 ? seekEntryOrOpen_o | -1073741824 : seekEntryOrOpen_e | Integer.MIN_VALUE;
            break;
         }

         if (seekEntryOrOpen_g == h) {
            Object seekEntryOrOpen_q = this.scala$collection$mutable$AnyRefMap$$_keys[seekEntryOrOpen_e];
            if (seekEntryOrOpen_q == key || seekEntryOrOpen_q != null && seekEntryOrOpen_q.equals(key)) {
               var10000 = seekEntryOrOpen_e;
               break;
            }
         }

         if (seekEntryOrOpen_o == -1 && seekEntryOrOpen_g + seekEntryOrOpen_g == 0) {
            seekEntryOrOpen_o = seekEntryOrOpen_e;
         }

         ++seekEntryOrOpen_x;
         seekEntryOrOpen_e = seekEntryOrOpen_e + 2 * (seekEntryOrOpen_x + 1) * seekEntryOrOpen_x - 3 & this.scala$collection$mutable$AnyRefMap$$mask;
      }

      Object var11 = null;
      int i = var10000;
      if (i < 0) {
         int j = i & 1073741823;
         this.scala$collection$mutable$AnyRefMap$$_hashes[j] = h;
         this.scala$collection$mutable$AnyRefMap$$_keys[j] = key;
         this.scala$collection$mutable$AnyRefMap$$_values[j] = value;
         ++this._size;
         if ((i & 1073741824) != 0) {
            --this._vacant;
         } else if (this.imbalanced()) {
            this.repack();
         }
      } else {
         this.scala$collection$mutable$AnyRefMap$$_hashes[i] = h;
         this.scala$collection$mutable$AnyRefMap$$_values[i] = value;
      }
   }

   /** @deprecated */
   public AnyRefMap $plus$eq(final Object key, final Object value) {
      this.update(key, value);
      return this;
   }

   public final AnyRefMap addOne(final Object key, final Object value) {
      this.update(key, value);
      return this;
   }

   public final AnyRefMap addOne(final Tuple2 kv) {
      this.update(kv._1(), kv._2());
      return this;
   }

   public AnyRefMap subtractOne(final Object key) {
      int i = this.seekEntry(this.hashOf(key), key);
      if (i >= 0) {
         --this._size;
         ++this._vacant;
         this.scala$collection$mutable$AnyRefMap$$_hashes[i] = Integer.MIN_VALUE;
         this.scala$collection$mutable$AnyRefMap$$_keys[i] = null;
         this.scala$collection$mutable$AnyRefMap$$_values[i] = null;
      }

      return this;
   }

   public Iterator iterator() {
      return new AnyRefMapIterator() {
         public Tuple2 nextResult(final Object k, final Object v) {
            return new Tuple2(k, v);
         }
      };
   }

   public Iterator keysIterator() {
      return new AnyRefMapIterator() {
         public Object nextResult(final Object k, final Object v) {
            return k;
         }
      };
   }

   public Iterator valuesIterator() {
      return new AnyRefMapIterator() {
         public Object nextResult(final Object k, final Object v) {
            return v;
         }
      };
   }

   public void foreach(final Function1 f) {
      int i = 0;

      for(int e = this._size; e > 0; --e) {
         while(i < this.scala$collection$mutable$AnyRefMap$$_hashes.length) {
            int h = this.scala$collection$mutable$AnyRefMap$$_hashes[i];
            if (h + h != 0 || i >= this.scala$collection$mutable$AnyRefMap$$_hashes.length) {
               break;
            }

            ++i;
         }

         if (i >= this.scala$collection$mutable$AnyRefMap$$_hashes.length) {
            return;
         }

         f.apply(new Tuple2(this.scala$collection$mutable$AnyRefMap$$_keys[i], this.scala$collection$mutable$AnyRefMap$$_values[i]));
         ++i;
      }

   }

   public void foreachEntry(final Function2 f) {
      int i = 0;

      for(int e = this._size; e > 0; --e) {
         while(i < this.scala$collection$mutable$AnyRefMap$$_hashes.length) {
            int h = this.scala$collection$mutable$AnyRefMap$$_hashes[i];
            if (h + h != 0 || i >= this.scala$collection$mutable$AnyRefMap$$_hashes.length) {
               break;
            }

            ++i;
         }

         if (i >= this.scala$collection$mutable$AnyRefMap$$_hashes.length) {
            return;
         }

         f.apply(this.scala$collection$mutable$AnyRefMap$$_keys[i], this.scala$collection$mutable$AnyRefMap$$_values[i]);
         ++i;
      }

   }

   public AnyRefMap clone() {
      int[] hz = Arrays.copyOf(this.scala$collection$mutable$AnyRefMap$$_hashes, this.scala$collection$mutable$AnyRefMap$$_hashes.length);
      Object[] kz = Arrays.copyOf(this.scala$collection$mutable$AnyRefMap$$_keys, this.scala$collection$mutable$AnyRefMap$$_keys.length);
      Object[] vz = Arrays.copyOf(this.scala$collection$mutable$AnyRefMap$$_values, this.scala$collection$mutable$AnyRefMap$$_values.length);
      AnyRefMap arm = new AnyRefMap(this.defaultEntry, 1, false);
      arm.initializeTo(this.scala$collection$mutable$AnyRefMap$$mask, this._size, this._vacant, hz, kz, vz);
      return arm;
   }

   /** @deprecated */
   public AnyRefMap $plus(final Tuple2 kv) {
      return AnyRefMap$.MODULE$.from(new View.Appended(this, kv));
   }

   /** @deprecated */
   public AnyRefMap $plus(final Tuple2 elem1, final Tuple2 elem2, final scala.collection.immutable.Seq elems) {
      AnyRefMap m = this.$plus(elem1).$plus(elem2);
      return elems.isEmpty() ? m : m.concat(elems);
   }

   public AnyRefMap concat(final IterableOnce xs) {
      AnyRefMap arm = this.clone();
      xs.iterator().foreach((kv) -> (AnyRefMap)arm.$plus$eq(kv));
      return arm;
   }

   public AnyRefMap $plus$plus(final IterableOnce xs) {
      return this.concat(xs);
   }

   /** @deprecated */
   public AnyRefMap updated(final Object key, final Object value) {
      AnyRefMap var10000 = this.clone();
      if (var10000 == null) {
         throw null;
      } else {
         AnyRefMap addOne_this = var10000;
         addOne_this.update(key, value);
         return addOne_this;
      }
   }

   private void foreachElement(final Object[] elems, final Function1 f) {
      int i = 0;

      for(int j = 0; i < this.scala$collection$mutable$AnyRefMap$$_hashes.length & j < this._size; ++i) {
         int h = this.scala$collection$mutable$AnyRefMap$$_hashes[i];
         if (h + h != 0) {
            ++j;
            f.apply(elems[i]);
         }
      }

   }

   public void foreachKey(final Function1 f) {
      Object[] foreachElement_elems = this.scala$collection$mutable$AnyRefMap$$_keys;
      int foreachElement_i = 0;

      for(int foreachElement_j = 0; foreachElement_i < this.scala$collection$mutable$AnyRefMap$$_hashes.length & foreachElement_j < this._size; ++foreachElement_i) {
         int foreachElement_h = this.scala$collection$mutable$AnyRefMap$$_hashes[foreachElement_i];
         if (foreachElement_h + foreachElement_h != 0) {
            ++foreachElement_j;
            f.apply(foreachElement_elems[foreachElement_i]);
         }
      }

   }

   public void foreachValue(final Function1 f) {
      Object[] foreachElement_elems = this.scala$collection$mutable$AnyRefMap$$_values;
      int foreachElement_i = 0;

      for(int foreachElement_j = 0; foreachElement_i < this.scala$collection$mutable$AnyRefMap$$_hashes.length & foreachElement_j < this._size; ++foreachElement_i) {
         int foreachElement_h = this.scala$collection$mutable$AnyRefMap$$_hashes[foreachElement_i];
         if (foreachElement_h + foreachElement_h != 0) {
            ++foreachElement_j;
            f.apply(foreachElement_elems[foreachElement_i]);
         }
      }

   }

   public AnyRefMap mapValuesNow(final Function1 f) {
      AnyRefMap arm = new AnyRefMap(AnyRefMap$.MODULE$.scala$collection$mutable$AnyRefMap$$exceptionDefault(), 1, false);
      int[] hz = Arrays.copyOf(this.scala$collection$mutable$AnyRefMap$$_hashes, this.scala$collection$mutable$AnyRefMap$$_hashes.length);
      Object[] kz = Arrays.copyOf(this.scala$collection$mutable$AnyRefMap$$_keys, this.scala$collection$mutable$AnyRefMap$$_keys.length);
      Object[] vz = new Object[this.scala$collection$mutable$AnyRefMap$$_values.length];
      int i = 0;

      for(int j = 0; i < this.scala$collection$mutable$AnyRefMap$$_hashes.length & j < this._size; ++i) {
         int h = this.scala$collection$mutable$AnyRefMap$$_hashes[i];
         if (h + h != 0) {
            ++j;
            vz[i] = f.apply(this.scala$collection$mutable$AnyRefMap$$_values[i]);
         }
      }

      arm.initializeTo(this.scala$collection$mutable$AnyRefMap$$mask, this._size, this._vacant, hz, kz, vz);
      return arm;
   }

   /** @deprecated */
   public final AnyRefMap transformValues(final Function1 f) {
      return this.transformValuesInPlace(f);
   }

   public AnyRefMap transformValuesInPlace(final Function1 f) {
      int i = 0;

      for(int j = 0; i < this.scala$collection$mutable$AnyRefMap$$_hashes.length & j < this._size; ++i) {
         int h = this.scala$collection$mutable$AnyRefMap$$_hashes[i];
         if (h + h != 0) {
            ++j;
            this.scala$collection$mutable$AnyRefMap$$_values[i] = f.apply(this.scala$collection$mutable$AnyRefMap$$_values[i]);
         }
      }

      return this;
   }

   public AnyRefMap map(final Function1 f, final DummyImplicit dummy) {
      return AnyRefMap$.MODULE$.from(new View.Map(this, f));
   }

   public AnyRefMap flatMap(final Function1 f, final DummyImplicit dummy) {
      return AnyRefMap$.MODULE$.from(new View.FlatMap(this, f));
   }

   public AnyRefMap collect(final PartialFunction pf, final DummyImplicit dummy) {
      AnyRefMap$ var10000 = AnyRefMap$.MODULE$;
      Builder strictOptimizedCollect_b = new AnyRefMapBuilder();
      Object strictOptimizedCollect_marker = Statics.pfMarker;
      Iterator strictOptimizedCollect_it = this.iterator();

      while(strictOptimizedCollect_it.hasNext()) {
         Object strictOptimizedCollect_elem = strictOptimizedCollect_it.next();
         Object strictOptimizedCollect_v = pf.applyOrElse(strictOptimizedCollect_elem, StrictOptimizedIterableOps::$anonfun$strictOptimizedCollect$1);
         if (strictOptimizedCollect_marker != strictOptimizedCollect_v) {
            ((AnyRefMapBuilder)strictOptimizedCollect_b).addOne((Tuple2)strictOptimizedCollect_v);
         }
      }

      return ((AnyRefMapBuilder)strictOptimizedCollect_b).elems();
   }

   public void clear() {
      Arrays.fill(this.scala$collection$mutable$AnyRefMap$$_keys, (Object)null);
      Arrays.fill(this.scala$collection$mutable$AnyRefMap$$_values, (Object)null);
      Arrays.fill(this.scala$collection$mutable$AnyRefMap$$_hashes, 0);
      this._size = 0;
      this._vacant = 0;
   }

   public Object writeReplace() {
      AnyRefMap$ var10002 = AnyRefMap$.MODULE$;
      var10002 = AnyRefMap$.MODULE$;
      return new DefaultSerializationProxy(AnyRefMap.ToFactory$.MODULE$, this);
   }

   public String stringPrefix() {
      return "AnyRefMap";
   }

   // $FF: synthetic method
   public static final void $anonfun$fromSpecific$1(final AnyRefMap arm$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Object k = x0$1._1();
         Object v = x0$1._2();
         arm$1.update(k, v);
      } else {
         throw new MatchError((Object)null);
      }
   }

   public AnyRefMap(final Function1 defaultEntry, final int initialBufferSize, final boolean initBlank) {
      this.defaultEntry = defaultEntry;
      this.scala$collection$mutable$AnyRefMap$$mask = 0;
      this._size = 0;
      this._vacant = 0;
      this.scala$collection$mutable$AnyRefMap$$_hashes = null;
      this.scala$collection$mutable$AnyRefMap$$_keys = null;
      this.scala$collection$mutable$AnyRefMap$$_values = null;
      if (initBlank) {
         this.defaultInitialize(initialBufferSize);
      }

   }

   public AnyRefMap() {
      this(AnyRefMap$.MODULE$.scala$collection$mutable$AnyRefMap$$exceptionDefault(), 16, true);
   }

   public AnyRefMap(final Function1 defaultEntry) {
      this(defaultEntry, 16, true);
   }

   public AnyRefMap(final int initialBufferSize) {
      this(AnyRefMap$.MODULE$.scala$collection$mutable$AnyRefMap$$exceptionDefault(), initialBufferSize, true);
   }

   public AnyRefMap(final Function1 defaultEntry, final int initialBufferSize) {
      this(defaultEntry, initialBufferSize, true);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private abstract class AnyRefMapIterator extends AbstractIterator {
      private final int[] hz;
      private final Object[] kz;
      private final Object[] vz;
      private int index;
      // $FF: synthetic field
      public final AnyRefMap $outer;

      public boolean hasNext() {
         if (this.index < this.hz.length) {
            for(int h = this.hz[this.index]; h + h == 0; h = this.hz[this.index]) {
               ++this.index;
               if (this.index >= this.hz.length) {
                  return false;
               }
            }

            return true;
         } else {
            return false;
         }
      }

      public Object next() {
         if (this.hasNext()) {
            Object ans = this.nextResult(this.kz[this.index], this.vz[this.index]);
            ++this.index;
            return ans;
         } else {
            throw new NoSuchElementException("next");
         }
      }

      public abstract Object nextResult(final Object k, final Object v);

      // $FF: synthetic method
      public AnyRefMap scala$collection$mutable$AnyRefMap$AnyRefMapIterator$$$outer() {
         return this.$outer;
      }

      public AnyRefMapIterator() {
         if (AnyRefMap.this == null) {
            throw null;
         } else {
            this.$outer = AnyRefMap.this;
            super();
            this.hz = AnyRefMap.this.scala$collection$mutable$AnyRefMap$$_hashes;
            this.kz = AnyRefMap.this.scala$collection$mutable$AnyRefMap$$_keys;
            this.vz = AnyRefMap.this.scala$collection$mutable$AnyRefMap$$_values;
            this.index = 0;
         }
      }
   }

   private static class ExceptionDefault implements Function1, Serializable {
      public boolean apply$mcZD$sp(final double v1) {
         return Function1.apply$mcZD$sp$(this, v1);
      }

      public double apply$mcDD$sp(final double v1) {
         return Function1.apply$mcDD$sp$(this, v1);
      }

      public float apply$mcFD$sp(final double v1) {
         return Function1.apply$mcFD$sp$(this, v1);
      }

      public int apply$mcID$sp(final double v1) {
         return Function1.apply$mcID$sp$(this, v1);
      }

      public long apply$mcJD$sp(final double v1) {
         return Function1.apply$mcJD$sp$(this, v1);
      }

      public void apply$mcVD$sp(final double v1) {
         Function1.apply$mcVD$sp$(this, v1);
      }

      public boolean apply$mcZF$sp(final float v1) {
         return Function1.apply$mcZF$sp$(this, v1);
      }

      public double apply$mcDF$sp(final float v1) {
         return Function1.apply$mcDF$sp$(this, v1);
      }

      public float apply$mcFF$sp(final float v1) {
         return Function1.apply$mcFF$sp$(this, v1);
      }

      public int apply$mcIF$sp(final float v1) {
         return Function1.apply$mcIF$sp$(this, v1);
      }

      public long apply$mcJF$sp(final float v1) {
         return Function1.apply$mcJF$sp$(this, v1);
      }

      public void apply$mcVF$sp(final float v1) {
         Function1.apply$mcVF$sp$(this, v1);
      }

      public boolean apply$mcZI$sp(final int v1) {
         return Function1.apply$mcZI$sp$(this, v1);
      }

      public double apply$mcDI$sp(final int v1) {
         return Function1.apply$mcDI$sp$(this, v1);
      }

      public float apply$mcFI$sp(final int v1) {
         return Function1.apply$mcFI$sp$(this, v1);
      }

      public int apply$mcII$sp(final int v1) {
         return Function1.apply$mcII$sp$(this, v1);
      }

      public long apply$mcJI$sp(final int v1) {
         return Function1.apply$mcJI$sp$(this, v1);
      }

      public void apply$mcVI$sp(final int v1) {
         Function1.apply$mcVI$sp$(this, v1);
      }

      public boolean apply$mcZJ$sp(final long v1) {
         return Function1.apply$mcZJ$sp$(this, v1);
      }

      public double apply$mcDJ$sp(final long v1) {
         return Function1.apply$mcDJ$sp$(this, v1);
      }

      public float apply$mcFJ$sp(final long v1) {
         return Function1.apply$mcFJ$sp$(this, v1);
      }

      public int apply$mcIJ$sp(final long v1) {
         return Function1.apply$mcIJ$sp$(this, v1);
      }

      public long apply$mcJJ$sp(final long v1) {
         return Function1.apply$mcJJ$sp$(this, v1);
      }

      public void apply$mcVJ$sp(final long v1) {
         Function1.apply$mcVJ$sp$(this, v1);
      }

      public Function1 compose(final Function1 g) {
         return Function1.compose$(this, g);
      }

      public Function1 andThen(final Function1 g) {
         return Function1.andThen$(this, g);
      }

      public String toString() {
         return Function1.toString$(this);
      }

      public Nothing$ apply(final Object k) {
         throw new NoSuchElementException(k == null ? "(null)" : k.toString());
      }

      public ExceptionDefault() {
      }
   }

   public static final class AnyRefMapBuilder implements ReusableBuilder {
      private AnyRefMap elems = new AnyRefMap();

      public void sizeHint(final int size) {
         Builder.sizeHint$(this, size);
      }

      public final void sizeHint(final IterableOnce coll, final int delta) {
         Builder.sizeHint$(this, coll, delta);
      }

      public final int sizeHint$default$2() {
         return Builder.sizeHint$default$2$(this);
      }

      public final void sizeHintBounded(final int size, final scala.collection.Iterable boundingColl) {
         Builder.sizeHintBounded$(this, size, boundingColl);
      }

      public Builder mapResult(final Function1 f) {
         return Builder.mapResult$(this, f);
      }

      public final Growable $plus$eq(final Object elem) {
         return Growable.$plus$eq$(this, elem);
      }

      /** @deprecated */
      public final Growable $plus$eq(final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
         return Growable.$plus$eq$(this, elem1, elem2, elems);
      }

      public Growable addAll(final IterableOnce elems) {
         return Growable.addAll$(this, elems);
      }

      public final Growable $plus$plus$eq(final IterableOnce elems) {
         return Growable.$plus$plus$eq$(this, elems);
      }

      public AnyRefMap elems() {
         return this.elems;
      }

      public void elems_$eq(final AnyRefMap x$1) {
         this.elems = x$1;
      }

      public AnyRefMapBuilder addOne(final Tuple2 entry) {
         AnyRefMap var10000 = this.elems();
         if (var10000 == null) {
            throw null;
         } else {
            var10000.addOne(entry);
            return this;
         }
      }

      public void clear() {
         this.elems_$eq(new AnyRefMap());
      }

      public AnyRefMap result() {
         return this.elems();
      }

      public int knownSize() {
         return this.elems().knownSize();
      }
   }

   private static class ToFactory$ implements Factory, Serializable {
      public static final ToFactory$ MODULE$ = new ToFactory$();
      private static final long serialVersionUID = 3L;

      public AnyRefMap fromSpecific(final IterableOnce it) {
         return AnyRefMap$.MODULE$.from(it);
      }

      public Builder newBuilder() {
         AnyRefMap$ var10000 = AnyRefMap$.MODULE$;
         return new AnyRefMapBuilder();
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ToFactory$.class);
      }

      public ToFactory$() {
      }
   }

   private static class ToBuildFrom$ implements BuildFrom {
      public static final ToBuildFrom$ MODULE$ = new ToBuildFrom$();

      static {
         ToBuildFrom$ var10000 = MODULE$;
      }

      /** @deprecated */
      public Builder apply(final Object from) {
         return BuildFrom.apply$(this, from);
      }

      public Factory toFactory(final Object from) {
         return BuildFrom.toFactory$(this, from);
      }

      public AnyRefMap fromSpecific(final Object from, final IterableOnce it) {
         return AnyRefMap$.MODULE$.from(it);
      }

      public ReusableBuilder newBuilder(final Object from) {
         AnyRefMap$ var10000 = AnyRefMap$.MODULE$;
         return new AnyRefMapBuilder();
      }

      public ToBuildFrom$() {
      }
   }
}

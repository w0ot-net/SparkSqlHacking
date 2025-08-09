package cats.kernel.instances;

import cats.kernel.Comparison;
import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Tuple1;
import scala.Tuple10;
import scala.Tuple11;
import scala.Tuple12;
import scala.Tuple13;
import scala.Tuple14;
import scala.Tuple15;
import scala.Tuple16;
import scala.Tuple17;
import scala.Tuple18;
import scala.Tuple19;
import scala.Tuple2;
import scala.Tuple20;
import scala.Tuple21;
import scala.Tuple22;
import scala.Tuple3;
import scala.Tuple4;
import scala.Tuple5;
import scala.Tuple6;
import scala.Tuple7;
import scala.Tuple8;
import scala.Tuple9;
import scala.collection.ArrayOps.;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005%-h\u0001\u0003\r\u001a!\u0003\r\taG\u0010\t\u000b)\u0002A\u0011\u0001\u0017\t\u000bA\u0002A1A\u0019\t\u000b!\u0003A1A%\t\u000be\u0003A1\u0001.\t\u000b9\u0004A1A8\t\u000f\u0005=\u0001\u0001b\u0001\u0002\u0012!9\u0011\u0011\n\u0001\u0005\u0004\u0005-\u0003bBAF\u0001\u0011\r\u0011Q\u0012\u0005\b\u0003+\u0004A1AAl\u0011\u001d\u00119\u0003\u0001C\u0002\u0005SAqA!!\u0001\t\u0007\u0011\u0019\tC\u0004\u0003d\u0002!\u0019A!:\t\u000f\r5\u0003\u0001b\u0001\u0004P!91q\u0018\u0001\u0005\u0004\r\u0005\u0007b\u0002C\u001d\u0001\u0011\rA1\b\u0005\b\tw\u0003A1\u0001C_\u0011\u001d))\u0005\u0001C\u0002\u000b\u000fBq!b6\u0001\t\u0007)I\u000eC\u0004\u0007r\u0001!\u0019Ab\u001d\t\u000f\u001dM\u0001\u0001b\u0001\b\u0016!9qQ\u0018\u0001\u0005\u0004\u001d}\u0006b\u0002E8\u0001\u0011\r\u0001\u0012\u000f\u0005\b\u0013S\u0001A1AE\u0016\u0005M!V\u000f\u001d7f\u001fJ$WM]%ogR\fgnY3t\u0015\tQ2$A\u0005j]N$\u0018M\\2fg*\u0011A$H\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003y\tAaY1ugN\u0019\u0001\u0001\t\u0014\u0011\u0005\u0005\"S\"\u0001\u0012\u000b\u0003\r\nQa]2bY\u0006L!!\n\u0012\u0003\r\u0005s\u0017PU3g!\t9\u0003&D\u0001\u001a\u0013\tI\u0013D\u0001\u000eUkBdW\rU1si&\fGn\u0014:eKJLen\u001d;b]\u000e,7/\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005i\u0003CA\u0011/\u0013\ty#E\u0001\u0003V]&$\u0018\u0001G2biN\\UM\u001d8fY>\u0013H-\u001a:G_J$V\u000f\u001d7fcU\u0011!\u0007\u0010\u000b\u0003g\u0015\u00032\u0001N\u001b8\u001b\u0005Y\u0012B\u0001\u001c\u001c\u0005\u0015y%\u000fZ3s!\r\t\u0003HO\u0005\u0003s\t\u0012a\u0001V;qY\u0016\f\u0004CA\u001e=\u0019\u0001!Q!\u0010\u0002C\u0002y\u0012!!\u0011\u0019\u0012\u0005}\u0012\u0005CA\u0011A\u0013\t\t%EA\u0004O_RD\u0017N\\4\u0011\u0005\u0005\u001a\u0015B\u0001##\u0005\r\te.\u001f\u0005\u0006\r\n\u0001\u001daR\u0001\u0003\u0003B\u00022\u0001N\u001b;\u0003a\u0019\u0017\r^:LKJtW\r\\(sI\u0016\u0014hi\u001c:UkBdWMM\u000b\u0004\u0015B\u0013FcA&U-B\u0019A'\u000e'\u0011\t\u0005ju*U\u0005\u0003\u001d\n\u0012a\u0001V;qY\u0016\u0014\u0004CA\u001eQ\t\u0015i4A1\u0001?!\tY$\u000bB\u0003T\u0007\t\u0007aH\u0001\u0002Bc!)ai\u0001a\u0002+B\u0019A'N(\t\u000b]\u001b\u00019\u0001-\u0002\u0005\u0005\u000b\u0004c\u0001\u001b6#\u0006A2-\u0019;t\u0017\u0016\u0014h.\u001a7Pe\u0012,'OR8s)V\u0004H.Z\u001a\u0016\tm\u000b7-\u001a\u000b\u00059\u001eL7\u000eE\u00025ku\u0003R!\t0aE\u0012L!a\u0018\u0012\u0003\rQ+\b\u000f\\34!\tY\u0014\rB\u0003>\t\t\u0007a\b\u0005\u0002<G\u0012)1\u000b\u0002b\u0001}A\u00111(\u001a\u0003\u0006M\u0012\u0011\rA\u0010\u0002\u0003\u0003JBQA\u0012\u0003A\u0004!\u00042\u0001N\u001ba\u0011\u00159F\u0001q\u0001k!\r!TG\u0019\u0005\u0006Y\u0012\u0001\u001d!\\\u0001\u0003\u0003J\u00022\u0001N\u001be\u0003a\u0019\u0017\r^:LKJtW\r\\(sI\u0016\u0014hi\u001c:UkBdW\rN\u000b\u0006aZD(\u0010 \u000b\tcz\f\t!!\u0002\u0002\nA\u0019A'\u000e:\u0011\r\u0005\u001aXo^=|\u0013\t!(E\u0001\u0004UkBdW\r\u000e\t\u0003wY$Q!P\u0003C\u0002y\u0002\"a\u000f=\u0005\u000bM+!\u0019\u0001 \u0011\u0005mRH!\u00024\u0006\u0005\u0004q\u0004CA\u001e}\t\u0015iXA1\u0001?\u0005\t\t5\u0007C\u0003G\u000b\u0001\u000fq\u0010E\u00025kUDaaV\u0003A\u0004\u0005\r\u0001c\u0001\u001b6o\"1A.\u0002a\u0002\u0003\u000f\u00012\u0001N\u001bz\u0011\u001d\tY!\u0002a\u0002\u0003\u001b\t!!Q\u001a\u0011\u0007Q*40\u0001\rdCR\u001c8*\u001a:oK2|%\u000fZ3s\r>\u0014H+\u001e9mKV*B\"a\u0005\u0002 \u0005\r\u0012qEA\u0016\u0003_!B\"!\u0006\u00024\u0005]\u00121HA \u0003\u0007\u0002B\u0001N\u001b\u0002\u0018Ai\u0011%!\u0007\u0002\u001e\u0005\u0005\u0012QEA\u0015\u0003[I1!a\u0007#\u0005\u0019!V\u000f\u001d7fkA\u00191(a\b\u0005\u000bu2!\u0019\u0001 \u0011\u0007m\n\u0019\u0003B\u0003T\r\t\u0007a\bE\u0002<\u0003O!QA\u001a\u0004C\u0002y\u00022aOA\u0016\t\u0015ihA1\u0001?!\rY\u0014q\u0006\u0003\u0007\u0003c1!\u0019\u0001 \u0003\u0005\u0005#\u0004B\u0002$\u0007\u0001\b\t)\u0004\u0005\u00035k\u0005u\u0001BB,\u0007\u0001\b\tI\u0004\u0005\u00035k\u0005\u0005\u0002B\u00027\u0007\u0001\b\ti\u0004\u0005\u00035k\u0005\u0015\u0002bBA\u0006\r\u0001\u000f\u0011\u0011\t\t\u0005iU\nI\u0003C\u0004\u0002F\u0019\u0001\u001d!a\u0012\u0002\u0005\u0005#\u0004\u0003\u0002\u001b6\u0003[\t\u0001dY1ug.+'O\\3m\u001fJ$WM\u001d$peR+\b\u000f\\37+9\ti%!\u0017\u0002^\u0005\u0005\u0014QMA5\u0003[\"b\"a\u0014\u0002r\u0005U\u0014\u0011PA?\u0003\u0003\u000b)\t\u0005\u00035k\u0005E\u0003cD\u0011\u0002T\u0005]\u00131LA0\u0003G\n9'a\u001b\n\u0007\u0005U#E\u0001\u0004UkBdWM\u000e\t\u0004w\u0005eC!B\u001f\b\u0005\u0004q\u0004cA\u001e\u0002^\u0011)1k\u0002b\u0001}A\u00191(!\u0019\u0005\u000b\u0019<!\u0019\u0001 \u0011\u0007m\n)\u0007B\u0003~\u000f\t\u0007a\bE\u0002<\u0003S\"a!!\r\b\u0005\u0004q\u0004cA\u001e\u0002n\u00111\u0011qN\u0004C\u0002y\u0012!!Q\u001b\t\r\u0019;\u00019AA:!\u0011!T'a\u0016\t\r];\u00019AA<!\u0011!T'a\u0017\t\r1<\u00019AA>!\u0011!T'a\u0018\t\u000f\u0005-q\u0001q\u0001\u0002\u0000A!A'NA2\u0011\u001d\t)e\u0002a\u0002\u0003\u0007\u0003B\u0001N\u001b\u0002h!9\u0011qQ\u0004A\u0004\u0005%\u0015AA!6!\u0011!T'a\u001b\u00021\r\fGo]&fe:,Gn\u0014:eKJ4uN\u001d+va2,w'\u0006\t\u0002\u0010\u0006m\u0015qTAR\u0003O\u000bY+a,\u00024R\u0001\u0012\u0011SA\\\u0003w\u000by,a1\u0002H\u0006-\u0017q\u001a\t\u0005iU\n\u0019\nE\t\"\u0003+\u000bI*!(\u0002\"\u0006\u0015\u0016\u0011VAW\u0003cK1!a&#\u0005\u0019!V\u000f\u001d7foA\u00191(a'\u0005\u000buB!\u0019\u0001 \u0011\u0007m\ny\nB\u0003T\u0011\t\u0007a\bE\u0002<\u0003G#QA\u001a\u0005C\u0002y\u00022aOAT\t\u0015i\bB1\u0001?!\rY\u00141\u0016\u0003\u0007\u0003cA!\u0019\u0001 \u0011\u0007m\ny\u000b\u0002\u0004\u0002p!\u0011\rA\u0010\t\u0004w\u0005MFABA[\u0011\t\u0007aH\u0001\u0002Bm!1a\t\u0003a\u0002\u0003s\u0003B\u0001N\u001b\u0002\u001a\"1q\u000b\u0003a\u0002\u0003{\u0003B\u0001N\u001b\u0002\u001e\"1A\u000e\u0003a\u0002\u0003\u0003\u0004B\u0001N\u001b\u0002\"\"9\u00111\u0002\u0005A\u0004\u0005\u0015\u0007\u0003\u0002\u001b6\u0003KCq!!\u0012\t\u0001\b\tI\r\u0005\u00035k\u0005%\u0006bBAD\u0011\u0001\u000f\u0011Q\u001a\t\u0005iU\ni\u000bC\u0004\u0002R\"\u0001\u001d!a5\u0002\u0005\u00053\u0004\u0003\u0002\u001b6\u0003c\u000b\u0001dY1ug.+'O\\3m\u001fJ$WM\u001d$peR+\b\u000f\\39+I\tI.!:\u0002j\u00065\u0018\u0011_A{\u0003s\fiP!\u0001\u0015%\u0005m'Q\u0001B\u0005\u0005\u001b\u0011\tB!\u0006\u0003\u001a\tu!\u0011\u0005\t\u0005iU\ni\u000eE\n\"\u0003?\f\u0019/a:\u0002l\u0006=\u00181_A|\u0003w\fy0C\u0002\u0002b\n\u0012a\u0001V;qY\u0016D\u0004cA\u001e\u0002f\u0012)Q(\u0003b\u0001}A\u00191(!;\u0005\u000bMK!\u0019\u0001 \u0011\u0007m\ni\u000fB\u0003g\u0013\t\u0007a\bE\u0002<\u0003c$Q!`\u0005C\u0002y\u00022aOA{\t\u0019\t\t$\u0003b\u0001}A\u00191(!?\u0005\r\u0005=\u0014B1\u0001?!\rY\u0014Q \u0003\u0007\u0003kK!\u0019\u0001 \u0011\u0007m\u0012\t\u0001\u0002\u0004\u0003\u0004%\u0011\rA\u0010\u0002\u0003\u0003^BaAR\u0005A\u0004\t\u001d\u0001\u0003\u0002\u001b6\u0003GDaaV\u0005A\u0004\t-\u0001\u0003\u0002\u001b6\u0003ODa\u0001\\\u0005A\u0004\t=\u0001\u0003\u0002\u001b6\u0003WDq!a\u0003\n\u0001\b\u0011\u0019\u0002\u0005\u00035k\u0005=\bbBA#\u0013\u0001\u000f!q\u0003\t\u0005iU\n\u0019\u0010C\u0004\u0002\b&\u0001\u001dAa\u0007\u0011\tQ*\u0014q\u001f\u0005\b\u0003#L\u00019\u0001B\u0010!\u0011!T'a?\t\u000f\t\r\u0012\u0002q\u0001\u0003&\u0005\u0011\u0011i\u000e\t\u0005iU\ny0\u0001\rdCR\u001c8*\u001a:oK2|%\u000fZ3s\r>\u0014H+\u001e9mKf*BCa\u000b\u00038\tm\"q\bB\"\u0005\u000f\u0012YEa\u0014\u0003T\t]C\u0003\u0006B\u0017\u00057\u0012yFa\u0019\u0003h\t-$q\u000eB:\u0005o\u0012Y\b\u0005\u00035k\t=\u0002#F\u0011\u00032\tU\"\u0011\bB\u001f\u0005\u0003\u0012)E!\u0013\u0003N\tE#QK\u0005\u0004\u0005g\u0011#A\u0002+va2,\u0017\bE\u0002<\u0005o!Q!\u0010\u0006C\u0002y\u00022a\u000fB\u001e\t\u0015\u0019&B1\u0001?!\rY$q\b\u0003\u0006M*\u0011\rA\u0010\t\u0004w\t\rC!B?\u000b\u0005\u0004q\u0004cA\u001e\u0003H\u00111\u0011\u0011\u0007\u0006C\u0002y\u00022a\u000fB&\t\u0019\tyG\u0003b\u0001}A\u00191Ha\u0014\u0005\r\u0005U&B1\u0001?!\rY$1\u000b\u0003\u0007\u0005\u0007Q!\u0019\u0001 \u0011\u0007m\u00129\u0006\u0002\u0004\u0003Z)\u0011\rA\u0010\u0002\u0003\u0003bBaA\u0012\u0006A\u0004\tu\u0003\u0003\u0002\u001b6\u0005kAaa\u0016\u0006A\u0004\t\u0005\u0004\u0003\u0002\u001b6\u0005sAa\u0001\u001c\u0006A\u0004\t\u0015\u0004\u0003\u0002\u001b6\u0005{Aq!a\u0003\u000b\u0001\b\u0011I\u0007\u0005\u00035k\t\u0005\u0003bBA#\u0015\u0001\u000f!Q\u000e\t\u0005iU\u0012)\u0005C\u0004\u0002\b*\u0001\u001dA!\u001d\u0011\tQ*$\u0011\n\u0005\b\u0003#T\u00019\u0001B;!\u0011!TG!\u0014\t\u000f\t\r\"\u0002q\u0001\u0003zA!A'\u000eB)\u0011\u001d\u0011iH\u0003a\u0002\u0005\u007f\n!!\u0011\u001d\u0011\tQ*$QK\u0001\u001aG\u0006$8oS3s]\u0016dwJ\u001d3fe\u001a{'\u000fV;qY\u0016\f\u0004'\u0006\f\u0003\u0006\nE%Q\u0013BM\u0005;\u0013\tK!*\u0003*\n5&\u0011\u0017B[)Y\u00119I!/\u0003>\n\u0005'Q\u0019Be\u0005\u001b\u0014\tN!6\u0003Z\nu\u0007\u0003\u0002\u001b6\u0005\u0013\u0003r#\tBF\u0005\u001f\u0013\u0019Ja&\u0003\u001c\n}%1\u0015BT\u0005W\u0013yKa-\n\u0007\t5%EA\u0004UkBdW-\r\u0019\u0011\u0007m\u0012\t\nB\u0003>\u0017\t\u0007a\bE\u0002<\u0005+#QaU\u0006C\u0002y\u00022a\u000fBM\t\u001517B1\u0001?!\rY$Q\u0014\u0003\u0006{.\u0011\rA\u0010\t\u0004w\t\u0005FABA\u0019\u0017\t\u0007a\bE\u0002<\u0005K#a!a\u001c\f\u0005\u0004q\u0004cA\u001e\u0003*\u00121\u0011QW\u0006C\u0002y\u00022a\u000fBW\t\u0019\u0011\u0019a\u0003b\u0001}A\u00191H!-\u0005\r\te3B1\u0001?!\rY$Q\u0017\u0003\u0007\u0005o[!\u0019\u0001 \u0003\u0005\u0005K\u0004B\u0002$\f\u0001\b\u0011Y\f\u0005\u00035k\t=\u0005BB,\f\u0001\b\u0011y\f\u0005\u00035k\tM\u0005B\u00027\f\u0001\b\u0011\u0019\r\u0005\u00035k\t]\u0005bBA\u0006\u0017\u0001\u000f!q\u0019\t\u0005iU\u0012Y\nC\u0004\u0002F-\u0001\u001dAa3\u0011\tQ*$q\u0014\u0005\b\u0003\u000f[\u00019\u0001Bh!\u0011!TGa)\t\u000f\u0005E7\u0002q\u0001\u0003TB!A'\u000eBT\u0011\u001d\u0011\u0019c\u0003a\u0002\u0005/\u0004B\u0001N\u001b\u0003,\"9!QP\u0006A\u0004\tm\u0007\u0003\u0002\u001b6\u0005_CqAa8\f\u0001\b\u0011\t/\u0001\u0002BsA!A'\u000eBZ\u0003e\u0019\u0017\r^:LKJtW\r\\(sI\u0016\u0014hi\u001c:UkBdW-M\u0019\u00161\t\u001d(1\u001fB|\u0005w\u0014ypa\u0001\u0004\b\r-1qBB\n\u0007/\u0019Y\u0002\u0006\r\u0003j\u000e}11EB\u0014\u0007W\u0019yca\r\u00048\rm2qHB\"\u0007\u000f\u0002B\u0001N\u001b\u0003lBI\u0012E!<\u0003r\nU(\u0011 B\u007f\u0007\u0003\u0019)a!\u0003\u0004\u000e\rE1QCB\r\u0013\r\u0011yO\t\u0002\b)V\u0004H.Z\u00192!\rY$1\u001f\u0003\u0006{1\u0011\rA\u0010\t\u0004w\t]H!B*\r\u0005\u0004q\u0004cA\u001e\u0003|\u0012)a\r\u0004b\u0001}A\u00191Ha@\u0005\u000bud!\u0019\u0001 \u0011\u0007m\u001a\u0019\u0001\u0002\u0004\u000221\u0011\rA\u0010\t\u0004w\r\u001dAABA8\u0019\t\u0007a\bE\u0002<\u0007\u0017!a!!.\r\u0005\u0004q\u0004cA\u001e\u0004\u0010\u00111!1\u0001\u0007C\u0002y\u00022aOB\n\t\u0019\u0011I\u0006\u0004b\u0001}A\u00191ha\u0006\u0005\r\t]FB1\u0001?!\rY41\u0004\u0003\u0007\u0007;a!\u0019\u0001 \u0003\u0007\u0005\u000b\u0004\u0007\u0003\u0004G\u0019\u0001\u000f1\u0011\u0005\t\u0005iU\u0012\t\u0010\u0003\u0004X\u0019\u0001\u000f1Q\u0005\t\u0005iU\u0012)\u0010\u0003\u0004m\u0019\u0001\u000f1\u0011\u0006\t\u0005iU\u0012I\u0010C\u0004\u0002\f1\u0001\u001da!\f\u0011\tQ*$Q \u0005\b\u0003\u000bb\u00019AB\u0019!\u0011!Tg!\u0001\t\u000f\u0005\u001dE\u0002q\u0001\u00046A!A'NB\u0003\u0011\u001d\t\t\u000e\u0004a\u0002\u0007s\u0001B\u0001N\u001b\u0004\n!9!1\u0005\u0007A\u0004\ru\u0002\u0003\u0002\u001b6\u0007\u001bAqA! \r\u0001\b\u0019\t\u0005\u0005\u00035k\rE\u0001b\u0002Bp\u0019\u0001\u000f1Q\t\t\u0005iU\u001a)\u0002C\u0004\u0004J1\u0001\u001daa\u0013\u0002\u0007\u0005\u000b\u0004\u0007\u0005\u00035k\re\u0011!G2biN\\UM\u001d8fY>\u0013H-\u001a:G_J$V\u000f\u001d7fcI*\"d!\u0015\u0004^\r\u00054QMB5\u0007[\u001a\th!\u001e\u0004z\ru4\u0011QBC\u0007\u0013#\"da\u0015\u0004\u000e\u000eE5QSBM\u0007;\u001b\tk!*\u0004*\u000e56\u0011WB[\u0007s\u0003B\u0001N\u001b\u0004VAY\u0012ea\u0016\u0004\\\r}31MB4\u0007W\u001ayga\u001d\u0004x\rm4qPBB\u0007\u000fK1a!\u0017#\u0005\u001d!V\u000f\u001d7fcI\u00022aOB/\t\u0015iTB1\u0001?!\rY4\u0011\r\u0003\u0006'6\u0011\rA\u0010\t\u0004w\r\u0015D!\u00024\u000e\u0005\u0004q\u0004cA\u001e\u0004j\u0011)Q0\u0004b\u0001}A\u00191h!\u001c\u0005\r\u0005ERB1\u0001?!\rY4\u0011\u000f\u0003\u0007\u0003_j!\u0019\u0001 \u0011\u0007m\u001a)\b\u0002\u0004\u000266\u0011\rA\u0010\t\u0004w\reDA\u0002B\u0002\u001b\t\u0007a\bE\u0002<\u0007{\"aA!\u0017\u000e\u0005\u0004q\u0004cA\u001e\u0004\u0002\u00121!qW\u0007C\u0002y\u00022aOBC\t\u0019\u0019i\"\u0004b\u0001}A\u00191h!#\u0005\r\r-UB1\u0001?\u0005\r\t\u0015'\r\u0005\u0007\r6\u0001\u001daa$\u0011\tQ*41\f\u0005\u0007/6\u0001\u001daa%\u0011\tQ*4q\f\u0005\u0007Y6\u0001\u001daa&\u0011\tQ*41\r\u0005\b\u0003\u0017i\u00019ABN!\u0011!Tga\u001a\t\u000f\u0005\u0015S\u0002q\u0001\u0004 B!A'NB6\u0011\u001d\t9)\u0004a\u0002\u0007G\u0003B\u0001N\u001b\u0004p!9\u0011\u0011[\u0007A\u0004\r\u001d\u0006\u0003\u0002\u001b6\u0007gBqAa\t\u000e\u0001\b\u0019Y\u000b\u0005\u00035k\r]\u0004b\u0002B?\u001b\u0001\u000f1q\u0016\t\u0005iU\u001aY\bC\u0004\u0003`6\u0001\u001daa-\u0011\tQ*4q\u0010\u0005\b\u0007\u0013j\u00019AB\\!\u0011!Tga!\t\u000f\rmV\u0002q\u0001\u0004>\u0006\u0019\u0011)M\u0019\u0011\tQ*4qQ\u0001\u001aG\u0006$8oS3s]\u0016dwJ\u001d3fe\u001a{'\u000fV;qY\u0016\f4'\u0006\u000f\u0004D\u000e=71[Bl\u00077\u001cyna9\u0004h\u000e-8q^Bz\u0007o\u001cYpa@\u00159\r\u0015G1\u0001C\u0004\t\u0017!y\u0001b\u0005\u0005\u0018\u0011mAq\u0004C\u0012\tO!Y\u0003b\f\u00054A!A'NBd!u\t3\u0011ZBg\u0007#\u001c)n!7\u0004^\u000e\u00058Q]Bu\u0007[\u001c\tp!>\u0004z\u000eu\u0018bABfE\t9A+\u001e9mKF\u001a\u0004cA\u001e\u0004P\u0012)QH\u0004b\u0001}A\u00191ha5\u0005\u000bMs!\u0019\u0001 \u0011\u0007m\u001a9\u000eB\u0003g\u001d\t\u0007a\bE\u0002<\u00077$Q! \bC\u0002y\u00022aOBp\t\u0019\t\tD\u0004b\u0001}A\u00191ha9\u0005\r\u0005=dB1\u0001?!\rY4q\u001d\u0003\u0007\u0003ks!\u0019\u0001 \u0011\u0007m\u001aY\u000f\u0002\u0004\u0003\u00049\u0011\rA\u0010\t\u0004w\r=HA\u0002B-\u001d\t\u0007a\bE\u0002<\u0007g$aAa.\u000f\u0005\u0004q\u0004cA\u001e\u0004x\u001211Q\u0004\bC\u0002y\u00022aOB~\t\u0019\u0019YI\u0004b\u0001}A\u00191ha@\u0005\r\u0011\u0005aB1\u0001?\u0005\r\t\u0015G\r\u0005\u0007\r:\u0001\u001d\u0001\"\u0002\u0011\tQ*4Q\u001a\u0005\u0007/:\u0001\u001d\u0001\"\u0003\u0011\tQ*4\u0011\u001b\u0005\u0007Y:\u0001\u001d\u0001\"\u0004\u0011\tQ*4Q\u001b\u0005\b\u0003\u0017q\u00019\u0001C\t!\u0011!Tg!7\t\u000f\u0005\u0015c\u0002q\u0001\u0005\u0016A!A'NBo\u0011\u001d\t9I\u0004a\u0002\t3\u0001B\u0001N\u001b\u0004b\"9\u0011\u0011\u001b\bA\u0004\u0011u\u0001\u0003\u0002\u001b6\u0007KDqAa\t\u000f\u0001\b!\t\u0003\u0005\u00035k\r%\bb\u0002B?\u001d\u0001\u000fAQ\u0005\t\u0005iU\u001ai\u000fC\u0004\u0003`:\u0001\u001d\u0001\"\u000b\u0011\tQ*4\u0011\u001f\u0005\b\u0007\u0013r\u00019\u0001C\u0017!\u0011!Tg!>\t\u000f\rmf\u0002q\u0001\u00052A!A'NB}\u0011\u001d!)D\u0004a\u0002\to\t1!Q\u00193!\u0011!Tg!@\u00023\r\fGo]&fe:,Gn\u0014:eKJ4uN\u001d+va2,\u0017\u0007N\u000b\u001f\t{!I\u0005\"\u0014\u0005R\u0011UC\u0011\fC/\tC\")\u0007\"\u001b\u0005n\u0011EDQ\u000fC=\t{\"b\u0004b\u0010\u0005\u0002\u0012\u0015E\u0011\u0012CG\t##)\n\"'\u0005\u001e\u0012\u0005FQ\u0015CU\t[#\t\f\".\u0011\tQ*D\u0011\t\t C\u0011\rCq\tC&\t\u001f\"\u0019\u0006b\u0016\u0005\\\u0011}C1\rC4\tW\"y\u0007b\u001d\u0005x\u0011m\u0014b\u0001C#E\t9A+\u001e9mKF\"\u0004cA\u001e\u0005J\u0011)Qh\u0004b\u0001}A\u00191\b\"\u0014\u0005\u000bM{!\u0019\u0001 \u0011\u0007m\"\t\u0006B\u0003g\u001f\t\u0007a\bE\u0002<\t+\"Q!`\bC\u0002y\u00022a\u000fC-\t\u0019\t\td\u0004b\u0001}A\u00191\b\"\u0018\u0005\r\u0005=tB1\u0001?!\rYD\u0011\r\u0003\u0007\u0003k{!\u0019\u0001 \u0011\u0007m\")\u0007\u0002\u0004\u0003\u0004=\u0011\rA\u0010\t\u0004w\u0011%DA\u0002B-\u001f\t\u0007a\bE\u0002<\t[\"aAa.\u0010\u0005\u0004q\u0004cA\u001e\u0005r\u001111QD\bC\u0002y\u00022a\u000fC;\t\u0019\u0019Yi\u0004b\u0001}A\u00191\b\"\u001f\u0005\r\u0011\u0005qB1\u0001?!\rYDQ\u0010\u0003\u0007\t\u007fz!\u0019\u0001 \u0003\u0007\u0005\u000b4\u0007\u0003\u0004G\u001f\u0001\u000fA1\u0011\t\u0005iU\"9\u0005\u0003\u0004X\u001f\u0001\u000fAq\u0011\t\u0005iU\"Y\u0005\u0003\u0004m\u001f\u0001\u000fA1\u0012\t\u0005iU\"y\u0005C\u0004\u0002\f=\u0001\u001d\u0001b$\u0011\tQ*D1\u000b\u0005\b\u0003\u000bz\u00019\u0001CJ!\u0011!T\u0007b\u0016\t\u000f\u0005\u001du\u0002q\u0001\u0005\u0018B!A'\u000eC.\u0011\u001d\t\tn\u0004a\u0002\t7\u0003B\u0001N\u001b\u0005`!9!1E\bA\u0004\u0011}\u0005\u0003\u0002\u001b6\tGBqA! \u0010\u0001\b!\u0019\u000b\u0005\u00035k\u0011\u001d\u0004b\u0002Bp\u001f\u0001\u000fAq\u0015\t\u0005iU\"Y\u0007C\u0004\u0004J=\u0001\u001d\u0001b+\u0011\tQ*Dq\u000e\u0005\b\u0007w{\u00019\u0001CX!\u0011!T\u0007b\u001d\t\u000f\u0011Ur\u0002q\u0001\u00054B!A'\u000eC<\u0011\u001d!9l\u0004a\u0002\ts\u000b1!Q\u00194!\u0011!T\u0007b\u001f\u00023\r\fGo]&fe:,Gn\u0014:eKJ4uN\u001d+va2,\u0017'N\u000b!\t\u007f#Y\rb4\u0005T\u0012]G1\u001cCp\tG$9\u000fb;\u0005p\u0012MHq\u001fC~\t\u007f,\u0019\u0001\u0006\u0011\u0005B\u0016\u001dQ1BC\b\u000b')9\"b\u0007\u0006 \u0015\rRqEC\u0016\u000b_)\u0019$b\u000e\u0006<\u0015}\u0002\u0003\u0002\u001b6\t\u0007\u0004\u0012%\tCc\t\u0013$i\r\"5\u0005V\u0012eGQ\u001cCq\tK$I\u000f\"<\u0005r\u0012UH\u0011 C\u007f\u000b\u0003I1\u0001b2#\u0005\u001d!V\u000f\u001d7fcU\u00022a\u000fCf\t\u0015i\u0004C1\u0001?!\rYDq\u001a\u0003\u0006'B\u0011\rA\u0010\t\u0004w\u0011MG!\u00024\u0011\u0005\u0004q\u0004cA\u001e\u0005X\u0012)Q\u0010\u0005b\u0001}A\u00191\bb7\u0005\r\u0005E\u0002C1\u0001?!\rYDq\u001c\u0003\u0007\u0003_\u0002\"\u0019\u0001 \u0011\u0007m\"\u0019\u000f\u0002\u0004\u00026B\u0011\rA\u0010\t\u0004w\u0011\u001dHA\u0002B\u0002!\t\u0007a\bE\u0002<\tW$aA!\u0017\u0011\u0005\u0004q\u0004cA\u001e\u0005p\u00121!q\u0017\tC\u0002y\u00022a\u000fCz\t\u0019\u0019i\u0002\u0005b\u0001}A\u00191\bb>\u0005\r\r-\u0005C1\u0001?!\rYD1 \u0003\u0007\t\u0003\u0001\"\u0019\u0001 \u0011\u0007m\"y\u0010\u0002\u0004\u0005\u0000A\u0011\rA\u0010\t\u0004w\u0015\rAABC\u0003!\t\u0007aHA\u0002BcQBaA\u0012\tA\u0004\u0015%\u0001\u0003\u0002\u001b6\t\u0013Daa\u0016\tA\u0004\u00155\u0001\u0003\u0002\u001b6\t\u001bDa\u0001\u001c\tA\u0004\u0015E\u0001\u0003\u0002\u001b6\t#Dq!a\u0003\u0011\u0001\b))\u0002\u0005\u00035k\u0011U\u0007bBA#!\u0001\u000fQ\u0011\u0004\t\u0005iU\"I\u000eC\u0004\u0002\bB\u0001\u001d!\"\b\u0011\tQ*DQ\u001c\u0005\b\u0003#\u0004\u00029AC\u0011!\u0011!T\u0007\"9\t\u000f\t\r\u0002\u0003q\u0001\u0006&A!A'\u000eCs\u0011\u001d\u0011i\b\u0005a\u0002\u000bS\u0001B\u0001N\u001b\u0005j\"9!q\u001c\tA\u0004\u00155\u0002\u0003\u0002\u001b6\t[Dqa!\u0013\u0011\u0001\b)\t\u0004\u0005\u00035k\u0011E\bbBB^!\u0001\u000fQQ\u0007\t\u0005iU\")\u0010C\u0004\u00056A\u0001\u001d!\"\u000f\u0011\tQ*D\u0011 \u0005\b\to\u0003\u00029AC\u001f!\u0011!T\u0007\"@\t\u000f\u0015\u0005\u0003\u0003q\u0001\u0006D\u0005\u0019\u0011)\r\u001b\u0011\tQ*T\u0011A\u0001\u001aG\u0006$8oS3s]\u0016dwJ\u001d3fe\u001a{'\u000fV;qY\u0016\fd'\u0006\u0012\u0006J\u0015US\u0011LC/\u000bC*)'\"\u001b\u0006n\u0015ETQOC=\u000b{*\t)\"\"\u0006\n\u00165U\u0011\u0013\u000b#\u000b\u0017*)*\"'\u0006\u001e\u0016\u0005VQUCU\u000b[+\t,\".\u0006:\u0016uV\u0011YCc\u000b\u0013,i-\"5\u0011\tQ*TQ\n\t$C\u0015=S1KC,\u000b7*y&b\u0019\u0006h\u0015-TqNC:\u000bo*Y(b \u0006\u0004\u0016\u001dU1RCH\u0013\r)\tF\t\u0002\b)V\u0004H.Z\u00197!\rYTQ\u000b\u0003\u0006{E\u0011\rA\u0010\t\u0004w\u0015eC!B*\u0012\u0005\u0004q\u0004cA\u001e\u0006^\u0011)a-\u0005b\u0001}A\u00191(\"\u0019\u0005\u000bu\f\"\u0019\u0001 \u0011\u0007m*)\u0007\u0002\u0004\u00022E\u0011\rA\u0010\t\u0004w\u0015%DABA8#\t\u0007a\bE\u0002<\u000b[\"a!!.\u0012\u0005\u0004q\u0004cA\u001e\u0006r\u00111!1A\tC\u0002y\u00022aOC;\t\u0019\u0011I&\u0005b\u0001}A\u00191(\"\u001f\u0005\r\t]\u0016C1\u0001?!\rYTQ\u0010\u0003\u0007\u0007;\t\"\u0019\u0001 \u0011\u0007m*\t\t\u0002\u0004\u0004\fF\u0011\rA\u0010\t\u0004w\u0015\u0015EA\u0002C\u0001#\t\u0007a\bE\u0002<\u000b\u0013#a\u0001b \u0012\u0005\u0004q\u0004cA\u001e\u0006\u000e\u00121QQA\tC\u0002y\u00022aOCI\t\u0019)\u0019*\u0005b\u0001}\t\u0019\u0011)M\u001b\t\r\u0019\u000b\u00029ACL!\u0011!T'b\u0015\t\r]\u000b\u00029ACN!\u0011!T'b\u0016\t\r1\f\u00029ACP!\u0011!T'b\u0017\t\u000f\u0005-\u0011\u0003q\u0001\u0006$B!A'NC0\u0011\u001d\t)%\u0005a\u0002\u000bO\u0003B\u0001N\u001b\u0006d!9\u0011qQ\tA\u0004\u0015-\u0006\u0003\u0002\u001b6\u000bOBq!!5\u0012\u0001\b)y\u000b\u0005\u00035k\u0015-\u0004b\u0002B\u0012#\u0001\u000fQ1\u0017\t\u0005iU*y\u0007C\u0004\u0003~E\u0001\u001d!b.\u0011\tQ*T1\u000f\u0005\b\u0005?\f\u00029AC^!\u0011!T'b\u001e\t\u000f\r%\u0013\u0003q\u0001\u0006@B!A'NC>\u0011\u001d\u0019Y,\u0005a\u0002\u000b\u0007\u0004B\u0001N\u001b\u0006\u0000!9AQG\tA\u0004\u0015\u001d\u0007\u0003\u0002\u001b6\u000b\u0007Cq\u0001b.\u0012\u0001\b)Y\r\u0005\u00035k\u0015\u001d\u0005bBC!#\u0001\u000fQq\u001a\t\u0005iU*Y\tC\u0004\u0006TF\u0001\u001d!\"6\u0002\u0007\u0005\u000bT\u0007\u0005\u00035k\u0015=\u0015!G2biN\\UM\u001d8fY>\u0013H-\u001a:G_J$V\u000f\u001d7fc]*B%b7\u0006h\u0016-Xq^Cz\u000bo,Y0b@\u0007\u0004\u0019\u001da1\u0002D\b\r'19Bb\u0007\u0007 \u0019\rbq\u0005\u000b%\u000b;4YCb\f\u00074\u0019]b1\bD \r\u000729Eb\u0013\u0007P\u0019Mcq\u000bD.\r?2\u0019Gb\u001a\u0007lA!A'NCp!\u0015\nS\u0011]Cs\u000bS,i/\"=\u0006v\u0016eXQ D\u0001\r\u000b1IA\"\u0004\u0007\u0012\u0019Ua\u0011\u0004D\u000f\rC1)#C\u0002\u0006d\n\u0012q\u0001V;qY\u0016\ft\u0007E\u0002<\u000bO$Q!\u0010\nC\u0002y\u00022aOCv\t\u0015\u0019&C1\u0001?!\rYTq\u001e\u0003\u0006MJ\u0011\rA\u0010\t\u0004w\u0015MH!B?\u0013\u0005\u0004q\u0004cA\u001e\u0006x\u00121\u0011\u0011\u0007\nC\u0002y\u00022aOC~\t\u0019\tyG\u0005b\u0001}A\u00191(b@\u0005\r\u0005U&C1\u0001?!\rYd1\u0001\u0003\u0007\u0005\u0007\u0011\"\u0019\u0001 \u0011\u0007m29\u0001\u0002\u0004\u0003ZI\u0011\rA\u0010\t\u0004w\u0019-AA\u0002B\\%\t\u0007a\bE\u0002<\r\u001f!aa!\b\u0013\u0005\u0004q\u0004cA\u001e\u0007\u0014\u0011111\u0012\nC\u0002y\u00022a\u000fD\f\t\u0019!\tA\u0005b\u0001}A\u00191Hb\u0007\u0005\r\u0011}$C1\u0001?!\rYdq\u0004\u0003\u0007\u000b\u000b\u0011\"\u0019\u0001 \u0011\u0007m2\u0019\u0003\u0002\u0004\u0006\u0014J\u0011\rA\u0010\t\u0004w\u0019\u001dBA\u0002D\u0015%\t\u0007aHA\u0002BcYBaA\u0012\nA\u0004\u00195\u0002\u0003\u0002\u001b6\u000bKDaa\u0016\nA\u0004\u0019E\u0002\u0003\u0002\u001b6\u000bSDa\u0001\u001c\nA\u0004\u0019U\u0002\u0003\u0002\u001b6\u000b[Dq!a\u0003\u0013\u0001\b1I\u0004\u0005\u00035k\u0015E\bbBA#%\u0001\u000faQ\b\t\u0005iU*)\u0010C\u0004\u0002\bJ\u0001\u001dA\"\u0011\u0011\tQ*T\u0011 \u0005\b\u0003#\u0014\u00029\u0001D#!\u0011!T'\"@\t\u000f\t\r\"\u0003q\u0001\u0007JA!A'\u000eD\u0001\u0011\u001d\u0011iH\u0005a\u0002\r\u001b\u0002B\u0001N\u001b\u0007\u0006!9!q\u001c\nA\u0004\u0019E\u0003\u0003\u0002\u001b6\r\u0013Aqa!\u0013\u0013\u0001\b1)\u0006\u0005\u00035k\u00195\u0001bBB^%\u0001\u000fa\u0011\f\t\u0005iU2\t\u0002C\u0004\u00056I\u0001\u001dA\"\u0018\u0011\tQ*dQ\u0003\u0005\b\to\u0013\u00029\u0001D1!\u0011!TG\"\u0007\t\u000f\u0015\u0005#\u0003q\u0001\u0007fA!A'\u000eD\u000f\u0011\u001d)\u0019N\u0005a\u0002\rS\u0002B\u0001N\u001b\u0007\"!9aQ\u000e\nA\u0004\u0019=\u0014aA!2mA!A'\u000eD\u0013\u0003e\u0019\u0017\r^:LKJtW\r\\(sI\u0016\u0014hi\u001c:UkBdW-\r\u001d\u0016M\u0019Ud\u0011\u0011DC\r\u00133iI\"%\u0007\u0016\u001aeeQ\u0014DQ\rK3IK\",\u00072\u001aUf\u0011\u0018D_\r\u00034)\r\u0006\u0014\u0007x\u0019%gQ\u001aDi\r+4IN\"8\u0007b\u001a\u0015h\u0011\u001eDw\rc4)P\"?\u0007~\u001e\u0005qQAD\u0005\u000f\u001b\u0001B\u0001N\u001b\u0007zA9\u0013Eb\u001f\u0007\u0000\u0019\req\u0011DF\r\u001f3\u0019Jb&\u0007\u001c\u001a}e1\u0015DT\rW3yKb-\u00078\u001amfq\u0018Db\u0013\r1iH\t\u0002\b)V\u0004H.Z\u00199!\rYd\u0011\u0011\u0003\u0006{M\u0011\rA\u0010\t\u0004w\u0019\u0015E!B*\u0014\u0005\u0004q\u0004cA\u001e\u0007\n\u0012)am\u0005b\u0001}A\u00191H\"$\u0005\u000bu\u001c\"\u0019\u0001 \u0011\u0007m2\t\n\u0002\u0004\u00022M\u0011\rA\u0010\t\u0004w\u0019UEABA8'\t\u0007a\bE\u0002<\r3#a!!.\u0014\u0005\u0004q\u0004cA\u001e\u0007\u001e\u00121!1A\nC\u0002y\u00022a\u000fDQ\t\u0019\u0011If\u0005b\u0001}A\u00191H\"*\u0005\r\t]6C1\u0001?!\rYd\u0011\u0016\u0003\u0007\u0007;\u0019\"\u0019\u0001 \u0011\u0007m2i\u000b\u0002\u0004\u0004\fN\u0011\rA\u0010\t\u0004w\u0019EFA\u0002C\u0001'\t\u0007a\bE\u0002<\rk#a\u0001b \u0014\u0005\u0004q\u0004cA\u001e\u0007:\u00121QQA\nC\u0002y\u00022a\u000fD_\t\u0019)\u0019j\u0005b\u0001}A\u00191H\"1\u0005\r\u0019%2C1\u0001?!\rYdQ\u0019\u0003\u0007\r\u000f\u001c\"\u0019\u0001 \u0003\u0007\u0005\u000bt\u0007\u0003\u0004G'\u0001\u000fa1\u001a\t\u0005iU2y\b\u0003\u0004X'\u0001\u000faq\u001a\t\u0005iU2\u0019\t\u0003\u0004m'\u0001\u000fa1\u001b\t\u0005iU29\tC\u0004\u0002\fM\u0001\u001dAb6\u0011\tQ*d1\u0012\u0005\b\u0003\u000b\u001a\u00029\u0001Dn!\u0011!TGb$\t\u000f\u0005\u001d5\u0003q\u0001\u0007`B!A'\u000eDJ\u0011\u001d\t\tn\u0005a\u0002\rG\u0004B\u0001N\u001b\u0007\u0018\"9!1E\nA\u0004\u0019\u001d\b\u0003\u0002\u001b6\r7CqA! \u0014\u0001\b1Y\u000f\u0005\u00035k\u0019}\u0005b\u0002Bp'\u0001\u000faq\u001e\t\u0005iU2\u0019\u000bC\u0004\u0004JM\u0001\u001dAb=\u0011\tQ*dq\u0015\u0005\b\u0007w\u001b\u00029\u0001D|!\u0011!TGb+\t\u000f\u0011U2\u0003q\u0001\u0007|B!A'\u000eDX\u0011\u001d!9l\u0005a\u0002\r\u007f\u0004B\u0001N\u001b\u00074\"9Q\u0011I\nA\u0004\u001d\r\u0001\u0003\u0002\u001b6\roCq!b5\u0014\u0001\b99\u0001\u0005\u00035k\u0019m\u0006b\u0002D7'\u0001\u000fq1\u0002\t\u0005iU2y\fC\u0004\b\u0010M\u0001\u001da\"\u0005\u0002\u0007\u0005\u000bt\u0007\u0005\u00035k\u0019\r\u0017!G2biN\\UM\u001d8fY>\u0013H-\u001a:G_J$V\u000f\u001d7fce*\u0002fb\u0006\b$\u001d\u001dr1FD\u0018\u000fg99db\u000f\b@\u001d\rsqID&\u000f\u001f:\u0019fb\u0016\b\\\u001d}s1MD4\u000fW\"\u0002f\"\u0007\bp\u001dMtqOD>\u000f\u007f:\u0019ib\"\b\f\u001e=u1SDL\u000f7;yjb)\b(\u001e-vqVDZ\u000fo\u0003B\u0001N\u001b\b\u001cAI\u0013e\"\b\b\"\u001d\u0015r\u0011FD\u0017\u000fc9)d\"\u000f\b>\u001d\u0005sQID%\u000f\u001b:\tf\"\u0016\bZ\u001dus\u0011MD3\u000fSJ1ab\b#\u0005\u001d!V\u000f\u001d7fce\u00022aOD\u0012\t\u0015iDC1\u0001?!\rYtq\u0005\u0003\u0006'R\u0011\rA\u0010\t\u0004w\u001d-B!\u00024\u0015\u0005\u0004q\u0004cA\u001e\b0\u0011)Q\u0010\u0006b\u0001}A\u00191hb\r\u0005\r\u0005EBC1\u0001?!\rYtq\u0007\u0003\u0007\u0003_\"\"\u0019\u0001 \u0011\u0007m:Y\u0004\u0002\u0004\u00026R\u0011\rA\u0010\t\u0004w\u001d}BA\u0002B\u0002)\t\u0007a\bE\u0002<\u000f\u0007\"aA!\u0017\u0015\u0005\u0004q\u0004cA\u001e\bH\u00111!q\u0017\u000bC\u0002y\u00022aOD&\t\u0019\u0019i\u0002\u0006b\u0001}A\u00191hb\u0014\u0005\r\r-EC1\u0001?!\rYt1\u000b\u0003\u0007\t\u0003!\"\u0019\u0001 \u0011\u0007m:9\u0006\u0002\u0004\u0005\u0000Q\u0011\rA\u0010\t\u0004w\u001dmCABC\u0003)\t\u0007a\bE\u0002<\u000f?\"a!b%\u0015\u0005\u0004q\u0004cA\u001e\bd\u00111a\u0011\u0006\u000bC\u0002y\u00022aOD4\t\u001919\r\u0006b\u0001}A\u00191hb\u001b\u0005\r\u001d5DC1\u0001?\u0005\r\t\u0015\u0007\u000f\u0005\u0007\rR\u0001\u001da\"\u001d\u0011\tQ*t\u0011\u0005\u0005\u0007/R\u0001\u001da\"\u001e\u0011\tQ*tQ\u0005\u0005\u0007YR\u0001\u001da\"\u001f\u0011\tQ*t\u0011\u0006\u0005\b\u0003\u0017!\u00029AD?!\u0011!Tg\"\f\t\u000f\u0005\u0015C\u0003q\u0001\b\u0002B!A'ND\u0019\u0011\u001d\t9\t\u0006a\u0002\u000f\u000b\u0003B\u0001N\u001b\b6!9\u0011\u0011\u001b\u000bA\u0004\u001d%\u0005\u0003\u0002\u001b6\u000fsAqAa\t\u0015\u0001\b9i\t\u0005\u00035k\u001du\u0002b\u0002B?)\u0001\u000fq\u0011\u0013\t\u0005iU:\t\u0005C\u0004\u0003`R\u0001\u001da\"&\u0011\tQ*tQ\t\u0005\b\u0007\u0013\"\u00029ADM!\u0011!Tg\"\u0013\t\u000f\rmF\u0003q\u0001\b\u001eB!A'ND'\u0011\u001d!)\u0004\u0006a\u0002\u000fC\u0003B\u0001N\u001b\bR!9Aq\u0017\u000bA\u0004\u001d\u0015\u0006\u0003\u0002\u001b6\u000f+Bq!\"\u0011\u0015\u0001\b9I\u000b\u0005\u00035k\u001de\u0003bBCj)\u0001\u000fqQ\u0016\t\u0005iU:i\u0006C\u0004\u0007nQ\u0001\u001da\"-\u0011\tQ*t\u0011\r\u0005\b\u000f\u001f!\u00029AD[!\u0011!Tg\"\u001a\t\u000f\u001deF\u0003q\u0001\b<\u0006\u0019\u0011)\r\u001d\u0011\tQ*t\u0011N\u0001\u001aG\u0006$8oS3s]\u0016dwJ\u001d3fe\u001a{'\u000fV;qY\u0016\u0014\u0004'\u0006\u0016\bB\u001e5w\u0011[Dk\u000f3<in\"9\bf\u001e%xQ^Dy\u000fk<Ip\"@\t\u0002!\u0015\u0001\u0012\u0002E\u0007\u0011#A)\u0002#\u0007\u0015U\u001d\r\u0007R\u0004E\u0011\u0011KAI\u0003#\f\t2!U\u0002\u0012\bE\u001f\u0011\u0003B)\u0005#\u0013\tN!E\u0003R\u000bE-\u0011;B\t\u0007#\u001a\tjA!A'NDc!-\nsqYDf\u000f\u001f<\u0019nb6\b\\\u001e}w1]Dt\u000fW<yob=\bx\u001emxq E\u0002\u0011\u000fAY\u0001c\u0004\t\u0014!]\u0011bADeE\t9A+\u001e9mKJ\u0002\u0004cA\u001e\bN\u0012)Q(\u0006b\u0001}A\u00191h\"5\u0005\u000bM+\"\u0019\u0001 \u0011\u0007m:)\u000eB\u0003g+\t\u0007a\bE\u0002<\u000f3$Q!`\u000bC\u0002y\u00022aODo\t\u0019\t\t$\u0006b\u0001}A\u00191h\"9\u0005\r\u0005=TC1\u0001?!\rYtQ\u001d\u0003\u0007\u0003k+\"\u0019\u0001 \u0011\u0007m:I\u000f\u0002\u0004\u0003\u0004U\u0011\rA\u0010\t\u0004w\u001d5HA\u0002B-+\t\u0007a\bE\u0002<\u000fc$aAa.\u0016\u0005\u0004q\u0004cA\u001e\bv\u001211QD\u000bC\u0002y\u00022aOD}\t\u0019\u0019Y)\u0006b\u0001}A\u00191h\"@\u0005\r\u0011\u0005QC1\u0001?!\rY\u0004\u0012\u0001\u0003\u0007\t\u007f*\"\u0019\u0001 \u0011\u0007mB)\u0001\u0002\u0004\u0006\u0006U\u0011\rA\u0010\t\u0004w!%AABCJ+\t\u0007a\bE\u0002<\u0011\u001b!aA\"\u000b\u0016\u0005\u0004q\u0004cA\u001e\t\u0012\u00111aqY\u000bC\u0002y\u00022a\u000fE\u000b\t\u00199i'\u0006b\u0001}A\u00191\b#\u0007\u0005\r!mQC1\u0001?\u0005\r\t\u0015'\u000f\u0005\u0007\rV\u0001\u001d\u0001c\b\u0011\tQ*t1\u001a\u0005\u0007/V\u0001\u001d\u0001c\t\u0011\tQ*tq\u001a\u0005\u0007YV\u0001\u001d\u0001c\n\u0011\tQ*t1\u001b\u0005\b\u0003\u0017)\u00029\u0001E\u0016!\u0011!Tgb6\t\u000f\u0005\u0015S\u0003q\u0001\t0A!A'NDn\u0011\u001d\t9)\u0006a\u0002\u0011g\u0001B\u0001N\u001b\b`\"9\u0011\u0011[\u000bA\u0004!]\u0002\u0003\u0002\u001b6\u000fGDqAa\t\u0016\u0001\bAY\u0004\u0005\u00035k\u001d\u001d\bb\u0002B?+\u0001\u000f\u0001r\b\t\u0005iU:Y\u000fC\u0004\u0003`V\u0001\u001d\u0001c\u0011\u0011\tQ*tq\u001e\u0005\b\u0007\u0013*\u00029\u0001E$!\u0011!Tgb=\t\u000f\rmV\u0003q\u0001\tLA!A'ND|\u0011\u001d!)$\u0006a\u0002\u0011\u001f\u0002B\u0001N\u001b\b|\"9AqW\u000bA\u0004!M\u0003\u0003\u0002\u001b6\u000f\u007fDq!\"\u0011\u0016\u0001\bA9\u0006\u0005\u00035k!\r\u0001bBCj+\u0001\u000f\u00012\f\t\u0005iUB9\u0001C\u0004\u0007nU\u0001\u001d\u0001c\u0018\u0011\tQ*\u00042\u0002\u0005\b\u000f\u001f)\u00029\u0001E2!\u0011!T\u0007c\u0004\t\u000f\u001deV\u0003q\u0001\thA!A'\u000eE\n\u0011\u001dAY'\u0006a\u0002\u0011[\n1!Q\u0019:!\u0011!T\u0007c\u0006\u00023\r\fGo]&fe:,Gn\u0014:eKJ4uN\u001d+va2,''M\u000b-\u0011gBy\bc!\t\b\"-\u0005r\u0012EJ\u0011/CY\nc(\t$\"\u001d\u00062\u0016EX\u0011gC9\fc/\t@\"\r\u0007r\u0019Ef\u0011\u001f$B\u0006#\u001e\tT\"]\u00072\u001cEp\u0011GD9\u000fc;\tp\"M\br\u001fE~\u0011\u007fL\u0019!c\u0002\n\f%=\u00112CE\f\u00137Iy\"c\t\u0011\tQ*\u0004r\u000f\t.C!e\u0004R\u0010EA\u0011\u000bCI\t#$\t\u0012\"U\u0005\u0012\u0014EO\u0011CC)\u000b#+\t.\"E\u0006R\u0017E]\u0011{C\t\r#2\tJ\"5\u0017b\u0001E>E\t9A+\u001e9mKJ\n\u0004cA\u001e\t\u0000\u0011)QH\u0006b\u0001}A\u00191\bc!\u0005\u000bM3\"\u0019\u0001 \u0011\u0007mB9\tB\u0003g-\t\u0007a\bE\u0002<\u0011\u0017#Q! \fC\u0002y\u00022a\u000fEH\t\u0019\t\tD\u0006b\u0001}A\u00191\bc%\u0005\r\u0005=dC1\u0001?!\rY\u0004r\u0013\u0003\u0007\u0003k3\"\u0019\u0001 \u0011\u0007mBY\n\u0002\u0004\u0003\u0004Y\u0011\rA\u0010\t\u0004w!}EA\u0002B--\t\u0007a\bE\u0002<\u0011G#aAa.\u0017\u0005\u0004q\u0004cA\u001e\t(\u001211Q\u0004\fC\u0002y\u00022a\u000fEV\t\u0019\u0019YI\u0006b\u0001}A\u00191\bc,\u0005\r\u0011\u0005aC1\u0001?!\rY\u00042\u0017\u0003\u0007\t\u007f2\"\u0019\u0001 \u0011\u0007mB9\f\u0002\u0004\u0006\u0006Y\u0011\rA\u0010\t\u0004w!mFABCJ-\t\u0007a\bE\u0002<\u0011\u007f#aA\"\u000b\u0017\u0005\u0004q\u0004cA\u001e\tD\u00121aq\u0019\fC\u0002y\u00022a\u000fEd\t\u00199iG\u0006b\u0001}A\u00191\bc3\u0005\r!maC1\u0001?!\rY\u0004r\u001a\u0003\u0007\u0011#4\"\u0019\u0001 \u0003\u0007\u0005\u0013\u0004\u0007\u0003\u0004G-\u0001\u000f\u0001R\u001b\t\u0005iUBi\b\u0003\u0004X-\u0001\u000f\u0001\u0012\u001c\t\u0005iUB\t\t\u0003\u0004m-\u0001\u000f\u0001R\u001c\t\u0005iUB)\tC\u0004\u0002\fY\u0001\u001d\u0001#9\u0011\tQ*\u0004\u0012\u0012\u0005\b\u0003\u000b2\u00029\u0001Es!\u0011!T\u0007#$\t\u000f\u0005\u001de\u0003q\u0001\tjB!A'\u000eEI\u0011\u001d\t\tN\u0006a\u0002\u0011[\u0004B\u0001N\u001b\t\u0016\"9!1\u0005\fA\u0004!E\b\u0003\u0002\u001b6\u00113CqA! \u0017\u0001\bA)\u0010\u0005\u00035k!u\u0005b\u0002Bp-\u0001\u000f\u0001\u0012 \t\u0005iUB\t\u000bC\u0004\u0004JY\u0001\u001d\u0001#@\u0011\tQ*\u0004R\u0015\u0005\b\u0007w3\u00029AE\u0001!\u0011!T\u0007#+\t\u000f\u0011Ub\u0003q\u0001\n\u0006A!A'\u000eEW\u0011\u001d!9L\u0006a\u0002\u0013\u0013\u0001B\u0001N\u001b\t2\"9Q\u0011\t\fA\u0004%5\u0001\u0003\u0002\u001b6\u0011kCq!b5\u0017\u0001\bI\t\u0002\u0005\u00035k!e\u0006b\u0002D7-\u0001\u000f\u0011R\u0003\t\u0005iUBi\fC\u0004\b\u0010Y\u0001\u001d!#\u0007\u0011\tQ*\u0004\u0012\u0019\u0005\b\u000fs3\u00029AE\u000f!\u0011!T\u0007#2\t\u000f!-d\u0003q\u0001\n\"A!A'\u000eEe\u0011\u001dI)C\u0006a\u0002\u0013O\t1!\u0011\u001a1!\u0011!T\u0007#4\u00023\r\fGo]&fe:,Gn\u0014:eKJ4uN\u001d+va2,'GM\u000b/\u0013[II$#\u0010\nB%\u0015\u0013\u0012JE'\u0013#J)&#\u0017\n^%\u0005\u0014RME5\u0013[J\t(#\u001e\nz%u\u0014\u0012QEC\u0013\u0013Ki\t\u0006\u0018\n0%E\u0015RSEM\u0013;K\t+#*\n*&5\u0016\u0012WE[\u0013sKi,#1\nF&%\u0017RZEi\u0013+LI.#8\nb&\u0015\b\u0003\u0002\u001b6\u0013c\u0001r&IE\u001a\u0013oIY$c\u0010\nD%\u001d\u00132JE(\u0013'J9&c\u0017\n`%\r\u0014rME6\u0013_J\u0019(c\u001e\n|%}\u00142QED\u0013\u0017K1!#\u000e#\u0005\u001d!V\u000f\u001d7feI\u00022aOE\u001d\t\u0015itC1\u0001?!\rY\u0014R\b\u0003\u0006'^\u0011\rA\u0010\t\u0004w%\u0005C!\u00024\u0018\u0005\u0004q\u0004cA\u001e\nF\u0011)Qp\u0006b\u0001}A\u00191(#\u0013\u0005\r\u0005ErC1\u0001?!\rY\u0014R\n\u0003\u0007\u0003_:\"\u0019\u0001 \u0011\u0007mJ\t\u0006\u0002\u0004\u00026^\u0011\rA\u0010\t\u0004w%UCA\u0002B\u0002/\t\u0007a\bE\u0002<\u00133\"aA!\u0017\u0018\u0005\u0004q\u0004cA\u001e\n^\u00111!qW\fC\u0002y\u00022aOE1\t\u0019\u0019ib\u0006b\u0001}A\u00191(#\u001a\u0005\r\r-uC1\u0001?!\rY\u0014\u0012\u000e\u0003\u0007\t\u00039\"\u0019\u0001 \u0011\u0007mJi\u0007\u0002\u0004\u0005\u0000]\u0011\rA\u0010\t\u0004w%EDABC\u0003/\t\u0007a\bE\u0002<\u0013k\"a!b%\u0018\u0005\u0004q\u0004cA\u001e\nz\u00111a\u0011F\fC\u0002y\u00022aOE?\t\u001919m\u0006b\u0001}A\u00191(#!\u0005\r\u001d5tC1\u0001?!\rY\u0014R\u0011\u0003\u0007\u001179\"\u0019\u0001 \u0011\u0007mJI\t\u0002\u0004\tR^\u0011\rA\u0010\t\u0004w%5EABEH/\t\u0007aHA\u0002BeEBaAR\fA\u0004%M\u0005\u0003\u0002\u001b6\u0013oAaaV\fA\u0004%]\u0005\u0003\u0002\u001b6\u0013wAa\u0001\\\fA\u0004%m\u0005\u0003\u0002\u001b6\u0013\u007fAq!a\u0003\u0018\u0001\bIy\n\u0005\u00035k%\r\u0003bBA#/\u0001\u000f\u00112\u0015\t\u0005iUJ9\u0005C\u0004\u0002\b^\u0001\u001d!c*\u0011\tQ*\u00142\n\u0005\b\u0003#<\u00029AEV!\u0011!T'c\u0014\t\u000f\t\rr\u0003q\u0001\n0B!A'NE*\u0011\u001d\u0011ih\u0006a\u0002\u0013g\u0003B\u0001N\u001b\nX!9!q\\\fA\u0004%]\u0006\u0003\u0002\u001b6\u00137Bqa!\u0013\u0018\u0001\bIY\f\u0005\u00035k%}\u0003bBB^/\u0001\u000f\u0011r\u0018\t\u0005iUJ\u0019\u0007C\u0004\u00056]\u0001\u001d!c1\u0011\tQ*\u0014r\r\u0005\b\to;\u00029AEd!\u0011!T'c\u001b\t\u000f\u0015\u0005s\u0003q\u0001\nLB!A'NE8\u0011\u001d)\u0019n\u0006a\u0002\u0013\u001f\u0004B\u0001N\u001b\nt!9aQN\fA\u0004%M\u0007\u0003\u0002\u001b6\u0013oBqab\u0004\u0018\u0001\bI9\u000e\u0005\u00035k%m\u0004bBD]/\u0001\u000f\u00112\u001c\t\u0005iUJy\bC\u0004\tl]\u0001\u001d!c8\u0011\tQ*\u00142\u0011\u0005\b\u0013K9\u00029AEr!\u0011!T'c\"\t\u000f%\u001dx\u0003q\u0001\nj\u0006\u0019\u0011IM\u0019\u0011\tQ*\u00142\u0012"
)
public interface TupleOrderInstances extends TuplePartialOrderInstances {
   // $FF: synthetic method
   static Order catsKernelOrderForTuple1$(final TupleOrderInstances $this, final Order A0) {
      return $this.catsKernelOrderForTuple1(A0);
   }

   default Order catsKernelOrderForTuple1(final Order A0) {
      return new Order(A0) {
         private final Order A0$485;

         public int compare$mcZ$sp(final boolean x, final boolean y) {
            return Order.compare$mcZ$sp$(this, x, y);
         }

         public int compare$mcB$sp(final byte x, final byte y) {
            return Order.compare$mcB$sp$(this, x, y);
         }

         public int compare$mcC$sp(final char x, final char y) {
            return Order.compare$mcC$sp$(this, x, y);
         }

         public int compare$mcD$sp(final double x, final double y) {
            return Order.compare$mcD$sp$(this, x, y);
         }

         public int compare$mcF$sp(final float x, final float y) {
            return Order.compare$mcF$sp$(this, x, y);
         }

         public int compare$mcI$sp(final int x, final int y) {
            return Order.compare$mcI$sp$(this, x, y);
         }

         public int compare$mcJ$sp(final long x, final long y) {
            return Order.compare$mcJ$sp$(this, x, y);
         }

         public int compare$mcS$sp(final short x, final short y) {
            return Order.compare$mcS$sp$(this, x, y);
         }

         public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.compare$mcV$sp$(this, x, y);
         }

         public Comparison comparison(final Object x, final Object y) {
            return Order.comparison$(this, x, y);
         }

         public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
            return Order.comparison$mcZ$sp$(this, x, y);
         }

         public Comparison comparison$mcB$sp(final byte x, final byte y) {
            return Order.comparison$mcB$sp$(this, x, y);
         }

         public Comparison comparison$mcC$sp(final char x, final char y) {
            return Order.comparison$mcC$sp$(this, x, y);
         }

         public Comparison comparison$mcD$sp(final double x, final double y) {
            return Order.comparison$mcD$sp$(this, x, y);
         }

         public Comparison comparison$mcF$sp(final float x, final float y) {
            return Order.comparison$mcF$sp$(this, x, y);
         }

         public Comparison comparison$mcI$sp(final int x, final int y) {
            return Order.comparison$mcI$sp$(this, x, y);
         }

         public Comparison comparison$mcJ$sp(final long x, final long y) {
            return Order.comparison$mcJ$sp$(this, x, y);
         }

         public Comparison comparison$mcS$sp(final short x, final short y) {
            return Order.comparison$mcS$sp$(this, x, y);
         }

         public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.comparison$mcV$sp$(this, x, y);
         }

         public double partialCompare(final Object x, final Object y) {
            return Order.partialCompare$(this, x, y);
         }

         public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
            return Order.partialCompare$mcZ$sp$(this, x, y);
         }

         public double partialCompare$mcB$sp(final byte x, final byte y) {
            return Order.partialCompare$mcB$sp$(this, x, y);
         }

         public double partialCompare$mcC$sp(final char x, final char y) {
            return Order.partialCompare$mcC$sp$(this, x, y);
         }

         public double partialCompare$mcD$sp(final double x, final double y) {
            return Order.partialCompare$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return Order.partialCompare$mcF$sp$(this, x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            return Order.partialCompare$mcI$sp$(this, x, y);
         }

         public double partialCompare$mcJ$sp(final long x, final long y) {
            return Order.partialCompare$mcJ$sp$(this, x, y);
         }

         public double partialCompare$mcS$sp(final short x, final short y) {
            return Order.partialCompare$mcS$sp$(this, x, y);
         }

         public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.partialCompare$mcV$sp$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Order.min$(this, x, y);
         }

         public boolean min$mcZ$sp(final boolean x, final boolean y) {
            return Order.min$mcZ$sp$(this, x, y);
         }

         public byte min$mcB$sp(final byte x, final byte y) {
            return Order.min$mcB$sp$(this, x, y);
         }

         public char min$mcC$sp(final char x, final char y) {
            return Order.min$mcC$sp$(this, x, y);
         }

         public double min$mcD$sp(final double x, final double y) {
            return Order.min$mcD$sp$(this, x, y);
         }

         public float min$mcF$sp(final float x, final float y) {
            return Order.min$mcF$sp$(this, x, y);
         }

         public int min$mcI$sp(final int x, final int y) {
            return Order.min$mcI$sp$(this, x, y);
         }

         public long min$mcJ$sp(final long x, final long y) {
            return Order.min$mcJ$sp$(this, x, y);
         }

         public short min$mcS$sp(final short x, final short y) {
            return Order.min$mcS$sp$(this, x, y);
         }

         public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.min$mcV$sp$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Order.max$(this, x, y);
         }

         public boolean max$mcZ$sp(final boolean x, final boolean y) {
            return Order.max$mcZ$sp$(this, x, y);
         }

         public byte max$mcB$sp(final byte x, final byte y) {
            return Order.max$mcB$sp$(this, x, y);
         }

         public char max$mcC$sp(final char x, final char y) {
            return Order.max$mcC$sp$(this, x, y);
         }

         public double max$mcD$sp(final double x, final double y) {
            return Order.max$mcD$sp$(this, x, y);
         }

         public float max$mcF$sp(final float x, final float y) {
            return Order.max$mcF$sp$(this, x, y);
         }

         public int max$mcI$sp(final int x, final int y) {
            return Order.max$mcI$sp$(this, x, y);
         }

         public long max$mcJ$sp(final long x, final long y) {
            return Order.max$mcJ$sp$(this, x, y);
         }

         public short max$mcS$sp(final short x, final short y) {
            return Order.max$mcS$sp$(this, x, y);
         }

         public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.max$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return Order.eqv$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Order.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Order.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Order.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Order.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Order.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Order.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Order.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Order.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Order.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Order.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Order.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Order.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Order.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Order.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Order.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.neqv$mcV$sp$(this, x, y);
         }

         public boolean lteqv(final Object x, final Object y) {
            return Order.lteqv$(this, x, y);
         }

         public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.lteqv$mcZ$sp$(this, x, y);
         }

         public boolean lteqv$mcB$sp(final byte x, final byte y) {
            return Order.lteqv$mcB$sp$(this, x, y);
         }

         public boolean lteqv$mcC$sp(final char x, final char y) {
            return Order.lteqv$mcC$sp$(this, x, y);
         }

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return Order.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return Order.lteqv$mcF$sp$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return Order.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv$mcJ$sp(final long x, final long y) {
            return Order.lteqv$mcJ$sp$(this, x, y);
         }

         public boolean lteqv$mcS$sp(final short x, final short y) {
            return Order.lteqv$mcS$sp$(this, x, y);
         }

         public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lteqv$mcV$sp$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Order.lt$(this, x, y);
         }

         public boolean lt$mcZ$sp(final boolean x, final boolean y) {
            return Order.lt$mcZ$sp$(this, x, y);
         }

         public boolean lt$mcB$sp(final byte x, final byte y) {
            return Order.lt$mcB$sp$(this, x, y);
         }

         public boolean lt$mcC$sp(final char x, final char y) {
            return Order.lt$mcC$sp$(this, x, y);
         }

         public boolean lt$mcD$sp(final double x, final double y) {
            return Order.lt$mcD$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return Order.lt$mcF$sp$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return Order.lt$mcI$sp$(this, x, y);
         }

         public boolean lt$mcJ$sp(final long x, final long y) {
            return Order.lt$mcJ$sp$(this, x, y);
         }

         public boolean lt$mcS$sp(final short x, final short y) {
            return Order.lt$mcS$sp$(this, x, y);
         }

         public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lt$mcV$sp$(this, x, y);
         }

         public boolean gteqv(final Object x, final Object y) {
            return Order.gteqv$(this, x, y);
         }

         public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.gteqv$mcZ$sp$(this, x, y);
         }

         public boolean gteqv$mcB$sp(final byte x, final byte y) {
            return Order.gteqv$mcB$sp$(this, x, y);
         }

         public boolean gteqv$mcC$sp(final char x, final char y) {
            return Order.gteqv$mcC$sp$(this, x, y);
         }

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return Order.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return Order.gteqv$mcF$sp$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return Order.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gteqv$mcJ$sp(final long x, final long y) {
            return Order.gteqv$mcJ$sp$(this, x, y);
         }

         public boolean gteqv$mcS$sp(final short x, final short y) {
            return Order.gteqv$mcS$sp$(this, x, y);
         }

         public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gteqv$mcV$sp$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Order.gt$(this, x, y);
         }

         public boolean gt$mcZ$sp(final boolean x, final boolean y) {
            return Order.gt$mcZ$sp$(this, x, y);
         }

         public boolean gt$mcB$sp(final byte x, final byte y) {
            return Order.gt$mcB$sp$(this, x, y);
         }

         public boolean gt$mcC$sp(final char x, final char y) {
            return Order.gt$mcC$sp$(this, x, y);
         }

         public boolean gt$mcD$sp(final double x, final double y) {
            return Order.gt$mcD$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return Order.gt$mcF$sp$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return Order.gt$mcI$sp$(this, x, y);
         }

         public boolean gt$mcJ$sp(final long x, final long y) {
            return Order.gt$mcJ$sp$(this, x, y);
         }

         public boolean gt$mcS$sp(final short x, final short y) {
            return Order.gt$mcS$sp$(this, x, y);
         }

         public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gt$mcV$sp$(this, x, y);
         }

         public Ordering toOrdering() {
            return Order.toOrdering$(this);
         }

         public Option partialComparison(final Object x, final Object y) {
            return PartialOrder.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
         }

         public Option partialComparison$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialComparison$mcB$sp$(this, x, y);
         }

         public Option partialComparison$mcC$sp(final char x, final char y) {
            return PartialOrder.partialComparison$mcC$sp$(this, x, y);
         }

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder.partialComparison$mcD$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder.partialComparison$mcI$sp$(this, x, y);
         }

         public Option partialComparison$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
         }

         public Option partialComparison$mcS$sp(final short x, final short y) {
            return PartialOrder.partialComparison$mcS$sp$(this, x, y);
         }

         public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialComparison$mcV$sp$(this, x, y);
         }

         public Option tryCompare(final Object x, final Object y) {
            return PartialOrder.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
         }

         public Option tryCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.tryCompare$mcB$sp$(this, x, y);
         }

         public Option tryCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.tryCompare$mcC$sp$(this, x, y);
         }

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.tryCompare$mcD$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.tryCompare$mcI$sp$(this, x, y);
         }

         public Option tryCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
         }

         public Option tryCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.tryCompare$mcS$sp$(this, x, y);
         }

         public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.tryCompare$mcV$sp$(this, x, y);
         }

         public Option pmin(final Object x, final Object y) {
            return PartialOrder.pmin$(this, x, y);
         }

         public Option pmin$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmin$mcZ$sp$(this, x, y);
         }

         public Option pmin$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmin$mcB$sp$(this, x, y);
         }

         public Option pmin$mcC$sp(final char x, final char y) {
            return PartialOrder.pmin$mcC$sp$(this, x, y);
         }

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder.pmin$mcD$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder.pmin$mcI$sp$(this, x, y);
         }

         public Option pmin$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmin$mcJ$sp$(this, x, y);
         }

         public Option pmin$mcS$sp(final short x, final short y) {
            return PartialOrder.pmin$mcS$sp$(this, x, y);
         }

         public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmin$mcV$sp$(this, x, y);
         }

         public Option pmax(final Object x, final Object y) {
            return PartialOrder.pmax$(this, x, y);
         }

         public Option pmax$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmax$mcZ$sp$(this, x, y);
         }

         public Option pmax$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmax$mcB$sp$(this, x, y);
         }

         public Option pmax$mcC$sp(final char x, final char y) {
            return PartialOrder.pmax$mcC$sp$(this, x, y);
         }

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder.pmax$mcD$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder.pmax$mcI$sp$(this, x, y);
         }

         public Option pmax$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmax$mcJ$sp$(this, x, y);
         }

         public Option pmax$mcS$sp(final short x, final short y) {
            return PartialOrder.pmax$mcS$sp$(this, x, y);
         }

         public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmax$mcV$sp$(this, x, y);
         }

         public int compare(final Tuple1 x, final Tuple1 y) {
            return BoxesRunTime.unboxToInt(.MODULE$.find$extension(scala.Predef..MODULE$.intArrayOps(new int[]{this.A0$485.compare(x._1(), y._1())}), (JFunction1.mcZI.sp)(x$45) -> x$45 != 0).getOrElse((JFunction0.mcI.sp)() -> 0));
         }

         public {
            this.A0$485 = A0$485;
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static Order catsKernelOrderForTuple2$(final TupleOrderInstances $this, final Order A0, final Order A1) {
      return $this.catsKernelOrderForTuple2(A0, A1);
   }

   default Order catsKernelOrderForTuple2(final Order A0, final Order A1) {
      return new Order(A0, A1) {
         private final Order A0$486;
         private final Order A1$463;

         public int compare$mcZ$sp(final boolean x, final boolean y) {
            return Order.compare$mcZ$sp$(this, x, y);
         }

         public int compare$mcB$sp(final byte x, final byte y) {
            return Order.compare$mcB$sp$(this, x, y);
         }

         public int compare$mcC$sp(final char x, final char y) {
            return Order.compare$mcC$sp$(this, x, y);
         }

         public int compare$mcD$sp(final double x, final double y) {
            return Order.compare$mcD$sp$(this, x, y);
         }

         public int compare$mcF$sp(final float x, final float y) {
            return Order.compare$mcF$sp$(this, x, y);
         }

         public int compare$mcI$sp(final int x, final int y) {
            return Order.compare$mcI$sp$(this, x, y);
         }

         public int compare$mcJ$sp(final long x, final long y) {
            return Order.compare$mcJ$sp$(this, x, y);
         }

         public int compare$mcS$sp(final short x, final short y) {
            return Order.compare$mcS$sp$(this, x, y);
         }

         public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.compare$mcV$sp$(this, x, y);
         }

         public Comparison comparison(final Object x, final Object y) {
            return Order.comparison$(this, x, y);
         }

         public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
            return Order.comparison$mcZ$sp$(this, x, y);
         }

         public Comparison comparison$mcB$sp(final byte x, final byte y) {
            return Order.comparison$mcB$sp$(this, x, y);
         }

         public Comparison comparison$mcC$sp(final char x, final char y) {
            return Order.comparison$mcC$sp$(this, x, y);
         }

         public Comparison comparison$mcD$sp(final double x, final double y) {
            return Order.comparison$mcD$sp$(this, x, y);
         }

         public Comparison comparison$mcF$sp(final float x, final float y) {
            return Order.comparison$mcF$sp$(this, x, y);
         }

         public Comparison comparison$mcI$sp(final int x, final int y) {
            return Order.comparison$mcI$sp$(this, x, y);
         }

         public Comparison comparison$mcJ$sp(final long x, final long y) {
            return Order.comparison$mcJ$sp$(this, x, y);
         }

         public Comparison comparison$mcS$sp(final short x, final short y) {
            return Order.comparison$mcS$sp$(this, x, y);
         }

         public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.comparison$mcV$sp$(this, x, y);
         }

         public double partialCompare(final Object x, final Object y) {
            return Order.partialCompare$(this, x, y);
         }

         public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
            return Order.partialCompare$mcZ$sp$(this, x, y);
         }

         public double partialCompare$mcB$sp(final byte x, final byte y) {
            return Order.partialCompare$mcB$sp$(this, x, y);
         }

         public double partialCompare$mcC$sp(final char x, final char y) {
            return Order.partialCompare$mcC$sp$(this, x, y);
         }

         public double partialCompare$mcD$sp(final double x, final double y) {
            return Order.partialCompare$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return Order.partialCompare$mcF$sp$(this, x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            return Order.partialCompare$mcI$sp$(this, x, y);
         }

         public double partialCompare$mcJ$sp(final long x, final long y) {
            return Order.partialCompare$mcJ$sp$(this, x, y);
         }

         public double partialCompare$mcS$sp(final short x, final short y) {
            return Order.partialCompare$mcS$sp$(this, x, y);
         }

         public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.partialCompare$mcV$sp$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Order.min$(this, x, y);
         }

         public boolean min$mcZ$sp(final boolean x, final boolean y) {
            return Order.min$mcZ$sp$(this, x, y);
         }

         public byte min$mcB$sp(final byte x, final byte y) {
            return Order.min$mcB$sp$(this, x, y);
         }

         public char min$mcC$sp(final char x, final char y) {
            return Order.min$mcC$sp$(this, x, y);
         }

         public double min$mcD$sp(final double x, final double y) {
            return Order.min$mcD$sp$(this, x, y);
         }

         public float min$mcF$sp(final float x, final float y) {
            return Order.min$mcF$sp$(this, x, y);
         }

         public int min$mcI$sp(final int x, final int y) {
            return Order.min$mcI$sp$(this, x, y);
         }

         public long min$mcJ$sp(final long x, final long y) {
            return Order.min$mcJ$sp$(this, x, y);
         }

         public short min$mcS$sp(final short x, final short y) {
            return Order.min$mcS$sp$(this, x, y);
         }

         public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.min$mcV$sp$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Order.max$(this, x, y);
         }

         public boolean max$mcZ$sp(final boolean x, final boolean y) {
            return Order.max$mcZ$sp$(this, x, y);
         }

         public byte max$mcB$sp(final byte x, final byte y) {
            return Order.max$mcB$sp$(this, x, y);
         }

         public char max$mcC$sp(final char x, final char y) {
            return Order.max$mcC$sp$(this, x, y);
         }

         public double max$mcD$sp(final double x, final double y) {
            return Order.max$mcD$sp$(this, x, y);
         }

         public float max$mcF$sp(final float x, final float y) {
            return Order.max$mcF$sp$(this, x, y);
         }

         public int max$mcI$sp(final int x, final int y) {
            return Order.max$mcI$sp$(this, x, y);
         }

         public long max$mcJ$sp(final long x, final long y) {
            return Order.max$mcJ$sp$(this, x, y);
         }

         public short max$mcS$sp(final short x, final short y) {
            return Order.max$mcS$sp$(this, x, y);
         }

         public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.max$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return Order.eqv$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Order.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Order.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Order.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Order.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Order.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Order.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Order.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Order.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Order.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Order.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Order.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Order.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Order.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Order.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Order.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.neqv$mcV$sp$(this, x, y);
         }

         public boolean lteqv(final Object x, final Object y) {
            return Order.lteqv$(this, x, y);
         }

         public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.lteqv$mcZ$sp$(this, x, y);
         }

         public boolean lteqv$mcB$sp(final byte x, final byte y) {
            return Order.lteqv$mcB$sp$(this, x, y);
         }

         public boolean lteqv$mcC$sp(final char x, final char y) {
            return Order.lteqv$mcC$sp$(this, x, y);
         }

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return Order.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return Order.lteqv$mcF$sp$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return Order.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv$mcJ$sp(final long x, final long y) {
            return Order.lteqv$mcJ$sp$(this, x, y);
         }

         public boolean lteqv$mcS$sp(final short x, final short y) {
            return Order.lteqv$mcS$sp$(this, x, y);
         }

         public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lteqv$mcV$sp$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Order.lt$(this, x, y);
         }

         public boolean lt$mcZ$sp(final boolean x, final boolean y) {
            return Order.lt$mcZ$sp$(this, x, y);
         }

         public boolean lt$mcB$sp(final byte x, final byte y) {
            return Order.lt$mcB$sp$(this, x, y);
         }

         public boolean lt$mcC$sp(final char x, final char y) {
            return Order.lt$mcC$sp$(this, x, y);
         }

         public boolean lt$mcD$sp(final double x, final double y) {
            return Order.lt$mcD$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return Order.lt$mcF$sp$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return Order.lt$mcI$sp$(this, x, y);
         }

         public boolean lt$mcJ$sp(final long x, final long y) {
            return Order.lt$mcJ$sp$(this, x, y);
         }

         public boolean lt$mcS$sp(final short x, final short y) {
            return Order.lt$mcS$sp$(this, x, y);
         }

         public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lt$mcV$sp$(this, x, y);
         }

         public boolean gteqv(final Object x, final Object y) {
            return Order.gteqv$(this, x, y);
         }

         public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.gteqv$mcZ$sp$(this, x, y);
         }

         public boolean gteqv$mcB$sp(final byte x, final byte y) {
            return Order.gteqv$mcB$sp$(this, x, y);
         }

         public boolean gteqv$mcC$sp(final char x, final char y) {
            return Order.gteqv$mcC$sp$(this, x, y);
         }

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return Order.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return Order.gteqv$mcF$sp$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return Order.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gteqv$mcJ$sp(final long x, final long y) {
            return Order.gteqv$mcJ$sp$(this, x, y);
         }

         public boolean gteqv$mcS$sp(final short x, final short y) {
            return Order.gteqv$mcS$sp$(this, x, y);
         }

         public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gteqv$mcV$sp$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Order.gt$(this, x, y);
         }

         public boolean gt$mcZ$sp(final boolean x, final boolean y) {
            return Order.gt$mcZ$sp$(this, x, y);
         }

         public boolean gt$mcB$sp(final byte x, final byte y) {
            return Order.gt$mcB$sp$(this, x, y);
         }

         public boolean gt$mcC$sp(final char x, final char y) {
            return Order.gt$mcC$sp$(this, x, y);
         }

         public boolean gt$mcD$sp(final double x, final double y) {
            return Order.gt$mcD$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return Order.gt$mcF$sp$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return Order.gt$mcI$sp$(this, x, y);
         }

         public boolean gt$mcJ$sp(final long x, final long y) {
            return Order.gt$mcJ$sp$(this, x, y);
         }

         public boolean gt$mcS$sp(final short x, final short y) {
            return Order.gt$mcS$sp$(this, x, y);
         }

         public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gt$mcV$sp$(this, x, y);
         }

         public Ordering toOrdering() {
            return Order.toOrdering$(this);
         }

         public Option partialComparison(final Object x, final Object y) {
            return PartialOrder.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
         }

         public Option partialComparison$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialComparison$mcB$sp$(this, x, y);
         }

         public Option partialComparison$mcC$sp(final char x, final char y) {
            return PartialOrder.partialComparison$mcC$sp$(this, x, y);
         }

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder.partialComparison$mcD$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder.partialComparison$mcI$sp$(this, x, y);
         }

         public Option partialComparison$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
         }

         public Option partialComparison$mcS$sp(final short x, final short y) {
            return PartialOrder.partialComparison$mcS$sp$(this, x, y);
         }

         public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialComparison$mcV$sp$(this, x, y);
         }

         public Option tryCompare(final Object x, final Object y) {
            return PartialOrder.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
         }

         public Option tryCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.tryCompare$mcB$sp$(this, x, y);
         }

         public Option tryCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.tryCompare$mcC$sp$(this, x, y);
         }

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.tryCompare$mcD$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.tryCompare$mcI$sp$(this, x, y);
         }

         public Option tryCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
         }

         public Option tryCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.tryCompare$mcS$sp$(this, x, y);
         }

         public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.tryCompare$mcV$sp$(this, x, y);
         }

         public Option pmin(final Object x, final Object y) {
            return PartialOrder.pmin$(this, x, y);
         }

         public Option pmin$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmin$mcZ$sp$(this, x, y);
         }

         public Option pmin$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmin$mcB$sp$(this, x, y);
         }

         public Option pmin$mcC$sp(final char x, final char y) {
            return PartialOrder.pmin$mcC$sp$(this, x, y);
         }

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder.pmin$mcD$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder.pmin$mcI$sp$(this, x, y);
         }

         public Option pmin$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmin$mcJ$sp$(this, x, y);
         }

         public Option pmin$mcS$sp(final short x, final short y) {
            return PartialOrder.pmin$mcS$sp$(this, x, y);
         }

         public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmin$mcV$sp$(this, x, y);
         }

         public Option pmax(final Object x, final Object y) {
            return PartialOrder.pmax$(this, x, y);
         }

         public Option pmax$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmax$mcZ$sp$(this, x, y);
         }

         public Option pmax$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmax$mcB$sp$(this, x, y);
         }

         public Option pmax$mcC$sp(final char x, final char y) {
            return PartialOrder.pmax$mcC$sp$(this, x, y);
         }

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder.pmax$mcD$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder.pmax$mcI$sp$(this, x, y);
         }

         public Option pmax$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmax$mcJ$sp$(this, x, y);
         }

         public Option pmax$mcS$sp(final short x, final short y) {
            return PartialOrder.pmax$mcS$sp$(this, x, y);
         }

         public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmax$mcV$sp$(this, x, y);
         }

         public int compare(final Tuple2 x, final Tuple2 y) {
            return BoxesRunTime.unboxToInt(.MODULE$.find$extension(scala.Predef..MODULE$.intArrayOps(new int[]{this.A0$486.compare(x._1(), y._1()), this.A1$463.compare(x._2(), y._2())}), (JFunction1.mcZI.sp)(x$46) -> x$46 != 0).getOrElse((JFunction0.mcI.sp)() -> 0));
         }

         public {
            this.A0$486 = A0$486;
            this.A1$463 = A1$463;
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static Order catsKernelOrderForTuple3$(final TupleOrderInstances $this, final Order A0, final Order A1, final Order A2) {
      return $this.catsKernelOrderForTuple3(A0, A1, A2);
   }

   default Order catsKernelOrderForTuple3(final Order A0, final Order A1, final Order A2) {
      return new Order(A0, A1, A2) {
         private final Order A0$487;
         private final Order A1$464;
         private final Order A2$441;

         public int compare$mcZ$sp(final boolean x, final boolean y) {
            return Order.compare$mcZ$sp$(this, x, y);
         }

         public int compare$mcB$sp(final byte x, final byte y) {
            return Order.compare$mcB$sp$(this, x, y);
         }

         public int compare$mcC$sp(final char x, final char y) {
            return Order.compare$mcC$sp$(this, x, y);
         }

         public int compare$mcD$sp(final double x, final double y) {
            return Order.compare$mcD$sp$(this, x, y);
         }

         public int compare$mcF$sp(final float x, final float y) {
            return Order.compare$mcF$sp$(this, x, y);
         }

         public int compare$mcI$sp(final int x, final int y) {
            return Order.compare$mcI$sp$(this, x, y);
         }

         public int compare$mcJ$sp(final long x, final long y) {
            return Order.compare$mcJ$sp$(this, x, y);
         }

         public int compare$mcS$sp(final short x, final short y) {
            return Order.compare$mcS$sp$(this, x, y);
         }

         public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.compare$mcV$sp$(this, x, y);
         }

         public Comparison comparison(final Object x, final Object y) {
            return Order.comparison$(this, x, y);
         }

         public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
            return Order.comparison$mcZ$sp$(this, x, y);
         }

         public Comparison comparison$mcB$sp(final byte x, final byte y) {
            return Order.comparison$mcB$sp$(this, x, y);
         }

         public Comparison comparison$mcC$sp(final char x, final char y) {
            return Order.comparison$mcC$sp$(this, x, y);
         }

         public Comparison comparison$mcD$sp(final double x, final double y) {
            return Order.comparison$mcD$sp$(this, x, y);
         }

         public Comparison comparison$mcF$sp(final float x, final float y) {
            return Order.comparison$mcF$sp$(this, x, y);
         }

         public Comparison comparison$mcI$sp(final int x, final int y) {
            return Order.comparison$mcI$sp$(this, x, y);
         }

         public Comparison comparison$mcJ$sp(final long x, final long y) {
            return Order.comparison$mcJ$sp$(this, x, y);
         }

         public Comparison comparison$mcS$sp(final short x, final short y) {
            return Order.comparison$mcS$sp$(this, x, y);
         }

         public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.comparison$mcV$sp$(this, x, y);
         }

         public double partialCompare(final Object x, final Object y) {
            return Order.partialCompare$(this, x, y);
         }

         public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
            return Order.partialCompare$mcZ$sp$(this, x, y);
         }

         public double partialCompare$mcB$sp(final byte x, final byte y) {
            return Order.partialCompare$mcB$sp$(this, x, y);
         }

         public double partialCompare$mcC$sp(final char x, final char y) {
            return Order.partialCompare$mcC$sp$(this, x, y);
         }

         public double partialCompare$mcD$sp(final double x, final double y) {
            return Order.partialCompare$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return Order.partialCompare$mcF$sp$(this, x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            return Order.partialCompare$mcI$sp$(this, x, y);
         }

         public double partialCompare$mcJ$sp(final long x, final long y) {
            return Order.partialCompare$mcJ$sp$(this, x, y);
         }

         public double partialCompare$mcS$sp(final short x, final short y) {
            return Order.partialCompare$mcS$sp$(this, x, y);
         }

         public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.partialCompare$mcV$sp$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Order.min$(this, x, y);
         }

         public boolean min$mcZ$sp(final boolean x, final boolean y) {
            return Order.min$mcZ$sp$(this, x, y);
         }

         public byte min$mcB$sp(final byte x, final byte y) {
            return Order.min$mcB$sp$(this, x, y);
         }

         public char min$mcC$sp(final char x, final char y) {
            return Order.min$mcC$sp$(this, x, y);
         }

         public double min$mcD$sp(final double x, final double y) {
            return Order.min$mcD$sp$(this, x, y);
         }

         public float min$mcF$sp(final float x, final float y) {
            return Order.min$mcF$sp$(this, x, y);
         }

         public int min$mcI$sp(final int x, final int y) {
            return Order.min$mcI$sp$(this, x, y);
         }

         public long min$mcJ$sp(final long x, final long y) {
            return Order.min$mcJ$sp$(this, x, y);
         }

         public short min$mcS$sp(final short x, final short y) {
            return Order.min$mcS$sp$(this, x, y);
         }

         public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.min$mcV$sp$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Order.max$(this, x, y);
         }

         public boolean max$mcZ$sp(final boolean x, final boolean y) {
            return Order.max$mcZ$sp$(this, x, y);
         }

         public byte max$mcB$sp(final byte x, final byte y) {
            return Order.max$mcB$sp$(this, x, y);
         }

         public char max$mcC$sp(final char x, final char y) {
            return Order.max$mcC$sp$(this, x, y);
         }

         public double max$mcD$sp(final double x, final double y) {
            return Order.max$mcD$sp$(this, x, y);
         }

         public float max$mcF$sp(final float x, final float y) {
            return Order.max$mcF$sp$(this, x, y);
         }

         public int max$mcI$sp(final int x, final int y) {
            return Order.max$mcI$sp$(this, x, y);
         }

         public long max$mcJ$sp(final long x, final long y) {
            return Order.max$mcJ$sp$(this, x, y);
         }

         public short max$mcS$sp(final short x, final short y) {
            return Order.max$mcS$sp$(this, x, y);
         }

         public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.max$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return Order.eqv$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Order.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Order.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Order.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Order.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Order.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Order.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Order.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Order.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Order.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Order.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Order.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Order.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Order.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Order.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Order.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.neqv$mcV$sp$(this, x, y);
         }

         public boolean lteqv(final Object x, final Object y) {
            return Order.lteqv$(this, x, y);
         }

         public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.lteqv$mcZ$sp$(this, x, y);
         }

         public boolean lteqv$mcB$sp(final byte x, final byte y) {
            return Order.lteqv$mcB$sp$(this, x, y);
         }

         public boolean lteqv$mcC$sp(final char x, final char y) {
            return Order.lteqv$mcC$sp$(this, x, y);
         }

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return Order.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return Order.lteqv$mcF$sp$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return Order.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv$mcJ$sp(final long x, final long y) {
            return Order.lteqv$mcJ$sp$(this, x, y);
         }

         public boolean lteqv$mcS$sp(final short x, final short y) {
            return Order.lteqv$mcS$sp$(this, x, y);
         }

         public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lteqv$mcV$sp$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Order.lt$(this, x, y);
         }

         public boolean lt$mcZ$sp(final boolean x, final boolean y) {
            return Order.lt$mcZ$sp$(this, x, y);
         }

         public boolean lt$mcB$sp(final byte x, final byte y) {
            return Order.lt$mcB$sp$(this, x, y);
         }

         public boolean lt$mcC$sp(final char x, final char y) {
            return Order.lt$mcC$sp$(this, x, y);
         }

         public boolean lt$mcD$sp(final double x, final double y) {
            return Order.lt$mcD$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return Order.lt$mcF$sp$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return Order.lt$mcI$sp$(this, x, y);
         }

         public boolean lt$mcJ$sp(final long x, final long y) {
            return Order.lt$mcJ$sp$(this, x, y);
         }

         public boolean lt$mcS$sp(final short x, final short y) {
            return Order.lt$mcS$sp$(this, x, y);
         }

         public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lt$mcV$sp$(this, x, y);
         }

         public boolean gteqv(final Object x, final Object y) {
            return Order.gteqv$(this, x, y);
         }

         public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.gteqv$mcZ$sp$(this, x, y);
         }

         public boolean gteqv$mcB$sp(final byte x, final byte y) {
            return Order.gteqv$mcB$sp$(this, x, y);
         }

         public boolean gteqv$mcC$sp(final char x, final char y) {
            return Order.gteqv$mcC$sp$(this, x, y);
         }

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return Order.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return Order.gteqv$mcF$sp$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return Order.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gteqv$mcJ$sp(final long x, final long y) {
            return Order.gteqv$mcJ$sp$(this, x, y);
         }

         public boolean gteqv$mcS$sp(final short x, final short y) {
            return Order.gteqv$mcS$sp$(this, x, y);
         }

         public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gteqv$mcV$sp$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Order.gt$(this, x, y);
         }

         public boolean gt$mcZ$sp(final boolean x, final boolean y) {
            return Order.gt$mcZ$sp$(this, x, y);
         }

         public boolean gt$mcB$sp(final byte x, final byte y) {
            return Order.gt$mcB$sp$(this, x, y);
         }

         public boolean gt$mcC$sp(final char x, final char y) {
            return Order.gt$mcC$sp$(this, x, y);
         }

         public boolean gt$mcD$sp(final double x, final double y) {
            return Order.gt$mcD$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return Order.gt$mcF$sp$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return Order.gt$mcI$sp$(this, x, y);
         }

         public boolean gt$mcJ$sp(final long x, final long y) {
            return Order.gt$mcJ$sp$(this, x, y);
         }

         public boolean gt$mcS$sp(final short x, final short y) {
            return Order.gt$mcS$sp$(this, x, y);
         }

         public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gt$mcV$sp$(this, x, y);
         }

         public Ordering toOrdering() {
            return Order.toOrdering$(this);
         }

         public Option partialComparison(final Object x, final Object y) {
            return PartialOrder.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
         }

         public Option partialComparison$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialComparison$mcB$sp$(this, x, y);
         }

         public Option partialComparison$mcC$sp(final char x, final char y) {
            return PartialOrder.partialComparison$mcC$sp$(this, x, y);
         }

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder.partialComparison$mcD$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder.partialComparison$mcI$sp$(this, x, y);
         }

         public Option partialComparison$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
         }

         public Option partialComparison$mcS$sp(final short x, final short y) {
            return PartialOrder.partialComparison$mcS$sp$(this, x, y);
         }

         public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialComparison$mcV$sp$(this, x, y);
         }

         public Option tryCompare(final Object x, final Object y) {
            return PartialOrder.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
         }

         public Option tryCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.tryCompare$mcB$sp$(this, x, y);
         }

         public Option tryCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.tryCompare$mcC$sp$(this, x, y);
         }

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.tryCompare$mcD$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.tryCompare$mcI$sp$(this, x, y);
         }

         public Option tryCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
         }

         public Option tryCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.tryCompare$mcS$sp$(this, x, y);
         }

         public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.tryCompare$mcV$sp$(this, x, y);
         }

         public Option pmin(final Object x, final Object y) {
            return PartialOrder.pmin$(this, x, y);
         }

         public Option pmin$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmin$mcZ$sp$(this, x, y);
         }

         public Option pmin$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmin$mcB$sp$(this, x, y);
         }

         public Option pmin$mcC$sp(final char x, final char y) {
            return PartialOrder.pmin$mcC$sp$(this, x, y);
         }

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder.pmin$mcD$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder.pmin$mcI$sp$(this, x, y);
         }

         public Option pmin$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmin$mcJ$sp$(this, x, y);
         }

         public Option pmin$mcS$sp(final short x, final short y) {
            return PartialOrder.pmin$mcS$sp$(this, x, y);
         }

         public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmin$mcV$sp$(this, x, y);
         }

         public Option pmax(final Object x, final Object y) {
            return PartialOrder.pmax$(this, x, y);
         }

         public Option pmax$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmax$mcZ$sp$(this, x, y);
         }

         public Option pmax$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmax$mcB$sp$(this, x, y);
         }

         public Option pmax$mcC$sp(final char x, final char y) {
            return PartialOrder.pmax$mcC$sp$(this, x, y);
         }

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder.pmax$mcD$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder.pmax$mcI$sp$(this, x, y);
         }

         public Option pmax$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmax$mcJ$sp$(this, x, y);
         }

         public Option pmax$mcS$sp(final short x, final short y) {
            return PartialOrder.pmax$mcS$sp$(this, x, y);
         }

         public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmax$mcV$sp$(this, x, y);
         }

         public int compare(final Tuple3 x, final Tuple3 y) {
            return BoxesRunTime.unboxToInt(.MODULE$.find$extension(scala.Predef..MODULE$.intArrayOps(new int[]{this.A0$487.compare(x._1(), y._1()), this.A1$464.compare(x._2(), y._2()), this.A2$441.compare(x._3(), y._3())}), (JFunction1.mcZI.sp)(x$47) -> x$47 != 0).getOrElse((JFunction0.mcI.sp)() -> 0));
         }

         public {
            this.A0$487 = A0$487;
            this.A1$464 = A1$464;
            this.A2$441 = A2$441;
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static Order catsKernelOrderForTuple4$(final TupleOrderInstances $this, final Order A0, final Order A1, final Order A2, final Order A3) {
      return $this.catsKernelOrderForTuple4(A0, A1, A2, A3);
   }

   default Order catsKernelOrderForTuple4(final Order A0, final Order A1, final Order A2, final Order A3) {
      return new Order(A0, A1, A2, A3) {
         private final Order A0$488;
         private final Order A1$465;
         private final Order A2$442;
         private final Order A3$419;

         public int compare$mcZ$sp(final boolean x, final boolean y) {
            return Order.compare$mcZ$sp$(this, x, y);
         }

         public int compare$mcB$sp(final byte x, final byte y) {
            return Order.compare$mcB$sp$(this, x, y);
         }

         public int compare$mcC$sp(final char x, final char y) {
            return Order.compare$mcC$sp$(this, x, y);
         }

         public int compare$mcD$sp(final double x, final double y) {
            return Order.compare$mcD$sp$(this, x, y);
         }

         public int compare$mcF$sp(final float x, final float y) {
            return Order.compare$mcF$sp$(this, x, y);
         }

         public int compare$mcI$sp(final int x, final int y) {
            return Order.compare$mcI$sp$(this, x, y);
         }

         public int compare$mcJ$sp(final long x, final long y) {
            return Order.compare$mcJ$sp$(this, x, y);
         }

         public int compare$mcS$sp(final short x, final short y) {
            return Order.compare$mcS$sp$(this, x, y);
         }

         public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.compare$mcV$sp$(this, x, y);
         }

         public Comparison comparison(final Object x, final Object y) {
            return Order.comparison$(this, x, y);
         }

         public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
            return Order.comparison$mcZ$sp$(this, x, y);
         }

         public Comparison comparison$mcB$sp(final byte x, final byte y) {
            return Order.comparison$mcB$sp$(this, x, y);
         }

         public Comparison comparison$mcC$sp(final char x, final char y) {
            return Order.comparison$mcC$sp$(this, x, y);
         }

         public Comparison comparison$mcD$sp(final double x, final double y) {
            return Order.comparison$mcD$sp$(this, x, y);
         }

         public Comparison comparison$mcF$sp(final float x, final float y) {
            return Order.comparison$mcF$sp$(this, x, y);
         }

         public Comparison comparison$mcI$sp(final int x, final int y) {
            return Order.comparison$mcI$sp$(this, x, y);
         }

         public Comparison comparison$mcJ$sp(final long x, final long y) {
            return Order.comparison$mcJ$sp$(this, x, y);
         }

         public Comparison comparison$mcS$sp(final short x, final short y) {
            return Order.comparison$mcS$sp$(this, x, y);
         }

         public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.comparison$mcV$sp$(this, x, y);
         }

         public double partialCompare(final Object x, final Object y) {
            return Order.partialCompare$(this, x, y);
         }

         public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
            return Order.partialCompare$mcZ$sp$(this, x, y);
         }

         public double partialCompare$mcB$sp(final byte x, final byte y) {
            return Order.partialCompare$mcB$sp$(this, x, y);
         }

         public double partialCompare$mcC$sp(final char x, final char y) {
            return Order.partialCompare$mcC$sp$(this, x, y);
         }

         public double partialCompare$mcD$sp(final double x, final double y) {
            return Order.partialCompare$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return Order.partialCompare$mcF$sp$(this, x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            return Order.partialCompare$mcI$sp$(this, x, y);
         }

         public double partialCompare$mcJ$sp(final long x, final long y) {
            return Order.partialCompare$mcJ$sp$(this, x, y);
         }

         public double partialCompare$mcS$sp(final short x, final short y) {
            return Order.partialCompare$mcS$sp$(this, x, y);
         }

         public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.partialCompare$mcV$sp$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Order.min$(this, x, y);
         }

         public boolean min$mcZ$sp(final boolean x, final boolean y) {
            return Order.min$mcZ$sp$(this, x, y);
         }

         public byte min$mcB$sp(final byte x, final byte y) {
            return Order.min$mcB$sp$(this, x, y);
         }

         public char min$mcC$sp(final char x, final char y) {
            return Order.min$mcC$sp$(this, x, y);
         }

         public double min$mcD$sp(final double x, final double y) {
            return Order.min$mcD$sp$(this, x, y);
         }

         public float min$mcF$sp(final float x, final float y) {
            return Order.min$mcF$sp$(this, x, y);
         }

         public int min$mcI$sp(final int x, final int y) {
            return Order.min$mcI$sp$(this, x, y);
         }

         public long min$mcJ$sp(final long x, final long y) {
            return Order.min$mcJ$sp$(this, x, y);
         }

         public short min$mcS$sp(final short x, final short y) {
            return Order.min$mcS$sp$(this, x, y);
         }

         public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.min$mcV$sp$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Order.max$(this, x, y);
         }

         public boolean max$mcZ$sp(final boolean x, final boolean y) {
            return Order.max$mcZ$sp$(this, x, y);
         }

         public byte max$mcB$sp(final byte x, final byte y) {
            return Order.max$mcB$sp$(this, x, y);
         }

         public char max$mcC$sp(final char x, final char y) {
            return Order.max$mcC$sp$(this, x, y);
         }

         public double max$mcD$sp(final double x, final double y) {
            return Order.max$mcD$sp$(this, x, y);
         }

         public float max$mcF$sp(final float x, final float y) {
            return Order.max$mcF$sp$(this, x, y);
         }

         public int max$mcI$sp(final int x, final int y) {
            return Order.max$mcI$sp$(this, x, y);
         }

         public long max$mcJ$sp(final long x, final long y) {
            return Order.max$mcJ$sp$(this, x, y);
         }

         public short max$mcS$sp(final short x, final short y) {
            return Order.max$mcS$sp$(this, x, y);
         }

         public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.max$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return Order.eqv$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Order.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Order.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Order.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Order.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Order.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Order.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Order.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Order.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Order.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Order.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Order.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Order.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Order.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Order.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Order.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.neqv$mcV$sp$(this, x, y);
         }

         public boolean lteqv(final Object x, final Object y) {
            return Order.lteqv$(this, x, y);
         }

         public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.lteqv$mcZ$sp$(this, x, y);
         }

         public boolean lteqv$mcB$sp(final byte x, final byte y) {
            return Order.lteqv$mcB$sp$(this, x, y);
         }

         public boolean lteqv$mcC$sp(final char x, final char y) {
            return Order.lteqv$mcC$sp$(this, x, y);
         }

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return Order.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return Order.lteqv$mcF$sp$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return Order.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv$mcJ$sp(final long x, final long y) {
            return Order.lteqv$mcJ$sp$(this, x, y);
         }

         public boolean lteqv$mcS$sp(final short x, final short y) {
            return Order.lteqv$mcS$sp$(this, x, y);
         }

         public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lteqv$mcV$sp$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Order.lt$(this, x, y);
         }

         public boolean lt$mcZ$sp(final boolean x, final boolean y) {
            return Order.lt$mcZ$sp$(this, x, y);
         }

         public boolean lt$mcB$sp(final byte x, final byte y) {
            return Order.lt$mcB$sp$(this, x, y);
         }

         public boolean lt$mcC$sp(final char x, final char y) {
            return Order.lt$mcC$sp$(this, x, y);
         }

         public boolean lt$mcD$sp(final double x, final double y) {
            return Order.lt$mcD$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return Order.lt$mcF$sp$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return Order.lt$mcI$sp$(this, x, y);
         }

         public boolean lt$mcJ$sp(final long x, final long y) {
            return Order.lt$mcJ$sp$(this, x, y);
         }

         public boolean lt$mcS$sp(final short x, final short y) {
            return Order.lt$mcS$sp$(this, x, y);
         }

         public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lt$mcV$sp$(this, x, y);
         }

         public boolean gteqv(final Object x, final Object y) {
            return Order.gteqv$(this, x, y);
         }

         public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.gteqv$mcZ$sp$(this, x, y);
         }

         public boolean gteqv$mcB$sp(final byte x, final byte y) {
            return Order.gteqv$mcB$sp$(this, x, y);
         }

         public boolean gteqv$mcC$sp(final char x, final char y) {
            return Order.gteqv$mcC$sp$(this, x, y);
         }

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return Order.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return Order.gteqv$mcF$sp$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return Order.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gteqv$mcJ$sp(final long x, final long y) {
            return Order.gteqv$mcJ$sp$(this, x, y);
         }

         public boolean gteqv$mcS$sp(final short x, final short y) {
            return Order.gteqv$mcS$sp$(this, x, y);
         }

         public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gteqv$mcV$sp$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Order.gt$(this, x, y);
         }

         public boolean gt$mcZ$sp(final boolean x, final boolean y) {
            return Order.gt$mcZ$sp$(this, x, y);
         }

         public boolean gt$mcB$sp(final byte x, final byte y) {
            return Order.gt$mcB$sp$(this, x, y);
         }

         public boolean gt$mcC$sp(final char x, final char y) {
            return Order.gt$mcC$sp$(this, x, y);
         }

         public boolean gt$mcD$sp(final double x, final double y) {
            return Order.gt$mcD$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return Order.gt$mcF$sp$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return Order.gt$mcI$sp$(this, x, y);
         }

         public boolean gt$mcJ$sp(final long x, final long y) {
            return Order.gt$mcJ$sp$(this, x, y);
         }

         public boolean gt$mcS$sp(final short x, final short y) {
            return Order.gt$mcS$sp$(this, x, y);
         }

         public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gt$mcV$sp$(this, x, y);
         }

         public Ordering toOrdering() {
            return Order.toOrdering$(this);
         }

         public Option partialComparison(final Object x, final Object y) {
            return PartialOrder.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
         }

         public Option partialComparison$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialComparison$mcB$sp$(this, x, y);
         }

         public Option partialComparison$mcC$sp(final char x, final char y) {
            return PartialOrder.partialComparison$mcC$sp$(this, x, y);
         }

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder.partialComparison$mcD$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder.partialComparison$mcI$sp$(this, x, y);
         }

         public Option partialComparison$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
         }

         public Option partialComparison$mcS$sp(final short x, final short y) {
            return PartialOrder.partialComparison$mcS$sp$(this, x, y);
         }

         public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialComparison$mcV$sp$(this, x, y);
         }

         public Option tryCompare(final Object x, final Object y) {
            return PartialOrder.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
         }

         public Option tryCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.tryCompare$mcB$sp$(this, x, y);
         }

         public Option tryCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.tryCompare$mcC$sp$(this, x, y);
         }

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.tryCompare$mcD$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.tryCompare$mcI$sp$(this, x, y);
         }

         public Option tryCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
         }

         public Option tryCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.tryCompare$mcS$sp$(this, x, y);
         }

         public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.tryCompare$mcV$sp$(this, x, y);
         }

         public Option pmin(final Object x, final Object y) {
            return PartialOrder.pmin$(this, x, y);
         }

         public Option pmin$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmin$mcZ$sp$(this, x, y);
         }

         public Option pmin$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmin$mcB$sp$(this, x, y);
         }

         public Option pmin$mcC$sp(final char x, final char y) {
            return PartialOrder.pmin$mcC$sp$(this, x, y);
         }

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder.pmin$mcD$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder.pmin$mcI$sp$(this, x, y);
         }

         public Option pmin$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmin$mcJ$sp$(this, x, y);
         }

         public Option pmin$mcS$sp(final short x, final short y) {
            return PartialOrder.pmin$mcS$sp$(this, x, y);
         }

         public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmin$mcV$sp$(this, x, y);
         }

         public Option pmax(final Object x, final Object y) {
            return PartialOrder.pmax$(this, x, y);
         }

         public Option pmax$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmax$mcZ$sp$(this, x, y);
         }

         public Option pmax$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmax$mcB$sp$(this, x, y);
         }

         public Option pmax$mcC$sp(final char x, final char y) {
            return PartialOrder.pmax$mcC$sp$(this, x, y);
         }

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder.pmax$mcD$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder.pmax$mcI$sp$(this, x, y);
         }

         public Option pmax$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmax$mcJ$sp$(this, x, y);
         }

         public Option pmax$mcS$sp(final short x, final short y) {
            return PartialOrder.pmax$mcS$sp$(this, x, y);
         }

         public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmax$mcV$sp$(this, x, y);
         }

         public int compare(final Tuple4 x, final Tuple4 y) {
            return BoxesRunTime.unboxToInt(.MODULE$.find$extension(scala.Predef..MODULE$.intArrayOps(new int[]{this.A0$488.compare(x._1(), y._1()), this.A1$465.compare(x._2(), y._2()), this.A2$442.compare(x._3(), y._3()), this.A3$419.compare(x._4(), y._4())}), (JFunction1.mcZI.sp)(x$48) -> x$48 != 0).getOrElse((JFunction0.mcI.sp)() -> 0));
         }

         public {
            this.A0$488 = A0$488;
            this.A1$465 = A1$465;
            this.A2$442 = A2$442;
            this.A3$419 = A3$419;
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static Order catsKernelOrderForTuple5$(final TupleOrderInstances $this, final Order A0, final Order A1, final Order A2, final Order A3, final Order A4) {
      return $this.catsKernelOrderForTuple5(A0, A1, A2, A3, A4);
   }

   default Order catsKernelOrderForTuple5(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4) {
      return new Order(A0, A1, A2, A3, A4) {
         private final Order A0$489;
         private final Order A1$466;
         private final Order A2$443;
         private final Order A3$420;
         private final Order A4$397;

         public int compare$mcZ$sp(final boolean x, final boolean y) {
            return Order.compare$mcZ$sp$(this, x, y);
         }

         public int compare$mcB$sp(final byte x, final byte y) {
            return Order.compare$mcB$sp$(this, x, y);
         }

         public int compare$mcC$sp(final char x, final char y) {
            return Order.compare$mcC$sp$(this, x, y);
         }

         public int compare$mcD$sp(final double x, final double y) {
            return Order.compare$mcD$sp$(this, x, y);
         }

         public int compare$mcF$sp(final float x, final float y) {
            return Order.compare$mcF$sp$(this, x, y);
         }

         public int compare$mcI$sp(final int x, final int y) {
            return Order.compare$mcI$sp$(this, x, y);
         }

         public int compare$mcJ$sp(final long x, final long y) {
            return Order.compare$mcJ$sp$(this, x, y);
         }

         public int compare$mcS$sp(final short x, final short y) {
            return Order.compare$mcS$sp$(this, x, y);
         }

         public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.compare$mcV$sp$(this, x, y);
         }

         public Comparison comparison(final Object x, final Object y) {
            return Order.comparison$(this, x, y);
         }

         public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
            return Order.comparison$mcZ$sp$(this, x, y);
         }

         public Comparison comparison$mcB$sp(final byte x, final byte y) {
            return Order.comparison$mcB$sp$(this, x, y);
         }

         public Comparison comparison$mcC$sp(final char x, final char y) {
            return Order.comparison$mcC$sp$(this, x, y);
         }

         public Comparison comparison$mcD$sp(final double x, final double y) {
            return Order.comparison$mcD$sp$(this, x, y);
         }

         public Comparison comparison$mcF$sp(final float x, final float y) {
            return Order.comparison$mcF$sp$(this, x, y);
         }

         public Comparison comparison$mcI$sp(final int x, final int y) {
            return Order.comparison$mcI$sp$(this, x, y);
         }

         public Comparison comparison$mcJ$sp(final long x, final long y) {
            return Order.comparison$mcJ$sp$(this, x, y);
         }

         public Comparison comparison$mcS$sp(final short x, final short y) {
            return Order.comparison$mcS$sp$(this, x, y);
         }

         public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.comparison$mcV$sp$(this, x, y);
         }

         public double partialCompare(final Object x, final Object y) {
            return Order.partialCompare$(this, x, y);
         }

         public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
            return Order.partialCompare$mcZ$sp$(this, x, y);
         }

         public double partialCompare$mcB$sp(final byte x, final byte y) {
            return Order.partialCompare$mcB$sp$(this, x, y);
         }

         public double partialCompare$mcC$sp(final char x, final char y) {
            return Order.partialCompare$mcC$sp$(this, x, y);
         }

         public double partialCompare$mcD$sp(final double x, final double y) {
            return Order.partialCompare$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return Order.partialCompare$mcF$sp$(this, x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            return Order.partialCompare$mcI$sp$(this, x, y);
         }

         public double partialCompare$mcJ$sp(final long x, final long y) {
            return Order.partialCompare$mcJ$sp$(this, x, y);
         }

         public double partialCompare$mcS$sp(final short x, final short y) {
            return Order.partialCompare$mcS$sp$(this, x, y);
         }

         public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.partialCompare$mcV$sp$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Order.min$(this, x, y);
         }

         public boolean min$mcZ$sp(final boolean x, final boolean y) {
            return Order.min$mcZ$sp$(this, x, y);
         }

         public byte min$mcB$sp(final byte x, final byte y) {
            return Order.min$mcB$sp$(this, x, y);
         }

         public char min$mcC$sp(final char x, final char y) {
            return Order.min$mcC$sp$(this, x, y);
         }

         public double min$mcD$sp(final double x, final double y) {
            return Order.min$mcD$sp$(this, x, y);
         }

         public float min$mcF$sp(final float x, final float y) {
            return Order.min$mcF$sp$(this, x, y);
         }

         public int min$mcI$sp(final int x, final int y) {
            return Order.min$mcI$sp$(this, x, y);
         }

         public long min$mcJ$sp(final long x, final long y) {
            return Order.min$mcJ$sp$(this, x, y);
         }

         public short min$mcS$sp(final short x, final short y) {
            return Order.min$mcS$sp$(this, x, y);
         }

         public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.min$mcV$sp$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Order.max$(this, x, y);
         }

         public boolean max$mcZ$sp(final boolean x, final boolean y) {
            return Order.max$mcZ$sp$(this, x, y);
         }

         public byte max$mcB$sp(final byte x, final byte y) {
            return Order.max$mcB$sp$(this, x, y);
         }

         public char max$mcC$sp(final char x, final char y) {
            return Order.max$mcC$sp$(this, x, y);
         }

         public double max$mcD$sp(final double x, final double y) {
            return Order.max$mcD$sp$(this, x, y);
         }

         public float max$mcF$sp(final float x, final float y) {
            return Order.max$mcF$sp$(this, x, y);
         }

         public int max$mcI$sp(final int x, final int y) {
            return Order.max$mcI$sp$(this, x, y);
         }

         public long max$mcJ$sp(final long x, final long y) {
            return Order.max$mcJ$sp$(this, x, y);
         }

         public short max$mcS$sp(final short x, final short y) {
            return Order.max$mcS$sp$(this, x, y);
         }

         public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.max$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return Order.eqv$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Order.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Order.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Order.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Order.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Order.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Order.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Order.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Order.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Order.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Order.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Order.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Order.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Order.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Order.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Order.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.neqv$mcV$sp$(this, x, y);
         }

         public boolean lteqv(final Object x, final Object y) {
            return Order.lteqv$(this, x, y);
         }

         public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.lteqv$mcZ$sp$(this, x, y);
         }

         public boolean lteqv$mcB$sp(final byte x, final byte y) {
            return Order.lteqv$mcB$sp$(this, x, y);
         }

         public boolean lteqv$mcC$sp(final char x, final char y) {
            return Order.lteqv$mcC$sp$(this, x, y);
         }

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return Order.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return Order.lteqv$mcF$sp$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return Order.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv$mcJ$sp(final long x, final long y) {
            return Order.lteqv$mcJ$sp$(this, x, y);
         }

         public boolean lteqv$mcS$sp(final short x, final short y) {
            return Order.lteqv$mcS$sp$(this, x, y);
         }

         public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lteqv$mcV$sp$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Order.lt$(this, x, y);
         }

         public boolean lt$mcZ$sp(final boolean x, final boolean y) {
            return Order.lt$mcZ$sp$(this, x, y);
         }

         public boolean lt$mcB$sp(final byte x, final byte y) {
            return Order.lt$mcB$sp$(this, x, y);
         }

         public boolean lt$mcC$sp(final char x, final char y) {
            return Order.lt$mcC$sp$(this, x, y);
         }

         public boolean lt$mcD$sp(final double x, final double y) {
            return Order.lt$mcD$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return Order.lt$mcF$sp$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return Order.lt$mcI$sp$(this, x, y);
         }

         public boolean lt$mcJ$sp(final long x, final long y) {
            return Order.lt$mcJ$sp$(this, x, y);
         }

         public boolean lt$mcS$sp(final short x, final short y) {
            return Order.lt$mcS$sp$(this, x, y);
         }

         public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lt$mcV$sp$(this, x, y);
         }

         public boolean gteqv(final Object x, final Object y) {
            return Order.gteqv$(this, x, y);
         }

         public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.gteqv$mcZ$sp$(this, x, y);
         }

         public boolean gteqv$mcB$sp(final byte x, final byte y) {
            return Order.gteqv$mcB$sp$(this, x, y);
         }

         public boolean gteqv$mcC$sp(final char x, final char y) {
            return Order.gteqv$mcC$sp$(this, x, y);
         }

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return Order.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return Order.gteqv$mcF$sp$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return Order.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gteqv$mcJ$sp(final long x, final long y) {
            return Order.gteqv$mcJ$sp$(this, x, y);
         }

         public boolean gteqv$mcS$sp(final short x, final short y) {
            return Order.gteqv$mcS$sp$(this, x, y);
         }

         public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gteqv$mcV$sp$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Order.gt$(this, x, y);
         }

         public boolean gt$mcZ$sp(final boolean x, final boolean y) {
            return Order.gt$mcZ$sp$(this, x, y);
         }

         public boolean gt$mcB$sp(final byte x, final byte y) {
            return Order.gt$mcB$sp$(this, x, y);
         }

         public boolean gt$mcC$sp(final char x, final char y) {
            return Order.gt$mcC$sp$(this, x, y);
         }

         public boolean gt$mcD$sp(final double x, final double y) {
            return Order.gt$mcD$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return Order.gt$mcF$sp$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return Order.gt$mcI$sp$(this, x, y);
         }

         public boolean gt$mcJ$sp(final long x, final long y) {
            return Order.gt$mcJ$sp$(this, x, y);
         }

         public boolean gt$mcS$sp(final short x, final short y) {
            return Order.gt$mcS$sp$(this, x, y);
         }

         public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gt$mcV$sp$(this, x, y);
         }

         public Ordering toOrdering() {
            return Order.toOrdering$(this);
         }

         public Option partialComparison(final Object x, final Object y) {
            return PartialOrder.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
         }

         public Option partialComparison$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialComparison$mcB$sp$(this, x, y);
         }

         public Option partialComparison$mcC$sp(final char x, final char y) {
            return PartialOrder.partialComparison$mcC$sp$(this, x, y);
         }

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder.partialComparison$mcD$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder.partialComparison$mcI$sp$(this, x, y);
         }

         public Option partialComparison$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
         }

         public Option partialComparison$mcS$sp(final short x, final short y) {
            return PartialOrder.partialComparison$mcS$sp$(this, x, y);
         }

         public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialComparison$mcV$sp$(this, x, y);
         }

         public Option tryCompare(final Object x, final Object y) {
            return PartialOrder.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
         }

         public Option tryCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.tryCompare$mcB$sp$(this, x, y);
         }

         public Option tryCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.tryCompare$mcC$sp$(this, x, y);
         }

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.tryCompare$mcD$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.tryCompare$mcI$sp$(this, x, y);
         }

         public Option tryCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
         }

         public Option tryCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.tryCompare$mcS$sp$(this, x, y);
         }

         public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.tryCompare$mcV$sp$(this, x, y);
         }

         public Option pmin(final Object x, final Object y) {
            return PartialOrder.pmin$(this, x, y);
         }

         public Option pmin$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmin$mcZ$sp$(this, x, y);
         }

         public Option pmin$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmin$mcB$sp$(this, x, y);
         }

         public Option pmin$mcC$sp(final char x, final char y) {
            return PartialOrder.pmin$mcC$sp$(this, x, y);
         }

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder.pmin$mcD$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder.pmin$mcI$sp$(this, x, y);
         }

         public Option pmin$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmin$mcJ$sp$(this, x, y);
         }

         public Option pmin$mcS$sp(final short x, final short y) {
            return PartialOrder.pmin$mcS$sp$(this, x, y);
         }

         public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmin$mcV$sp$(this, x, y);
         }

         public Option pmax(final Object x, final Object y) {
            return PartialOrder.pmax$(this, x, y);
         }

         public Option pmax$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmax$mcZ$sp$(this, x, y);
         }

         public Option pmax$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmax$mcB$sp$(this, x, y);
         }

         public Option pmax$mcC$sp(final char x, final char y) {
            return PartialOrder.pmax$mcC$sp$(this, x, y);
         }

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder.pmax$mcD$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder.pmax$mcI$sp$(this, x, y);
         }

         public Option pmax$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmax$mcJ$sp$(this, x, y);
         }

         public Option pmax$mcS$sp(final short x, final short y) {
            return PartialOrder.pmax$mcS$sp$(this, x, y);
         }

         public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmax$mcV$sp$(this, x, y);
         }

         public int compare(final Tuple5 x, final Tuple5 y) {
            return BoxesRunTime.unboxToInt(.MODULE$.find$extension(scala.Predef..MODULE$.intArrayOps(new int[]{this.A0$489.compare(x._1(), y._1()), this.A1$466.compare(x._2(), y._2()), this.A2$443.compare(x._3(), y._3()), this.A3$420.compare(x._4(), y._4()), this.A4$397.compare(x._5(), y._5())}), (JFunction1.mcZI.sp)(x$49) -> x$49 != 0).getOrElse((JFunction0.mcI.sp)() -> 0));
         }

         public {
            this.A0$489 = A0$489;
            this.A1$466 = A1$466;
            this.A2$443 = A2$443;
            this.A3$420 = A3$420;
            this.A4$397 = A4$397;
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static Order catsKernelOrderForTuple6$(final TupleOrderInstances $this, final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5) {
      return $this.catsKernelOrderForTuple6(A0, A1, A2, A3, A4, A5);
   }

   default Order catsKernelOrderForTuple6(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5) {
      return new Order(A0, A1, A2, A3, A4, A5) {
         private final Order A0$490;
         private final Order A1$467;
         private final Order A2$444;
         private final Order A3$421;
         private final Order A4$398;
         private final Order A5$375;

         public int compare$mcZ$sp(final boolean x, final boolean y) {
            return Order.compare$mcZ$sp$(this, x, y);
         }

         public int compare$mcB$sp(final byte x, final byte y) {
            return Order.compare$mcB$sp$(this, x, y);
         }

         public int compare$mcC$sp(final char x, final char y) {
            return Order.compare$mcC$sp$(this, x, y);
         }

         public int compare$mcD$sp(final double x, final double y) {
            return Order.compare$mcD$sp$(this, x, y);
         }

         public int compare$mcF$sp(final float x, final float y) {
            return Order.compare$mcF$sp$(this, x, y);
         }

         public int compare$mcI$sp(final int x, final int y) {
            return Order.compare$mcI$sp$(this, x, y);
         }

         public int compare$mcJ$sp(final long x, final long y) {
            return Order.compare$mcJ$sp$(this, x, y);
         }

         public int compare$mcS$sp(final short x, final short y) {
            return Order.compare$mcS$sp$(this, x, y);
         }

         public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.compare$mcV$sp$(this, x, y);
         }

         public Comparison comparison(final Object x, final Object y) {
            return Order.comparison$(this, x, y);
         }

         public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
            return Order.comparison$mcZ$sp$(this, x, y);
         }

         public Comparison comparison$mcB$sp(final byte x, final byte y) {
            return Order.comparison$mcB$sp$(this, x, y);
         }

         public Comparison comparison$mcC$sp(final char x, final char y) {
            return Order.comparison$mcC$sp$(this, x, y);
         }

         public Comparison comparison$mcD$sp(final double x, final double y) {
            return Order.comparison$mcD$sp$(this, x, y);
         }

         public Comparison comparison$mcF$sp(final float x, final float y) {
            return Order.comparison$mcF$sp$(this, x, y);
         }

         public Comparison comparison$mcI$sp(final int x, final int y) {
            return Order.comparison$mcI$sp$(this, x, y);
         }

         public Comparison comparison$mcJ$sp(final long x, final long y) {
            return Order.comparison$mcJ$sp$(this, x, y);
         }

         public Comparison comparison$mcS$sp(final short x, final short y) {
            return Order.comparison$mcS$sp$(this, x, y);
         }

         public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.comparison$mcV$sp$(this, x, y);
         }

         public double partialCompare(final Object x, final Object y) {
            return Order.partialCompare$(this, x, y);
         }

         public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
            return Order.partialCompare$mcZ$sp$(this, x, y);
         }

         public double partialCompare$mcB$sp(final byte x, final byte y) {
            return Order.partialCompare$mcB$sp$(this, x, y);
         }

         public double partialCompare$mcC$sp(final char x, final char y) {
            return Order.partialCompare$mcC$sp$(this, x, y);
         }

         public double partialCompare$mcD$sp(final double x, final double y) {
            return Order.partialCompare$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return Order.partialCompare$mcF$sp$(this, x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            return Order.partialCompare$mcI$sp$(this, x, y);
         }

         public double partialCompare$mcJ$sp(final long x, final long y) {
            return Order.partialCompare$mcJ$sp$(this, x, y);
         }

         public double partialCompare$mcS$sp(final short x, final short y) {
            return Order.partialCompare$mcS$sp$(this, x, y);
         }

         public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.partialCompare$mcV$sp$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Order.min$(this, x, y);
         }

         public boolean min$mcZ$sp(final boolean x, final boolean y) {
            return Order.min$mcZ$sp$(this, x, y);
         }

         public byte min$mcB$sp(final byte x, final byte y) {
            return Order.min$mcB$sp$(this, x, y);
         }

         public char min$mcC$sp(final char x, final char y) {
            return Order.min$mcC$sp$(this, x, y);
         }

         public double min$mcD$sp(final double x, final double y) {
            return Order.min$mcD$sp$(this, x, y);
         }

         public float min$mcF$sp(final float x, final float y) {
            return Order.min$mcF$sp$(this, x, y);
         }

         public int min$mcI$sp(final int x, final int y) {
            return Order.min$mcI$sp$(this, x, y);
         }

         public long min$mcJ$sp(final long x, final long y) {
            return Order.min$mcJ$sp$(this, x, y);
         }

         public short min$mcS$sp(final short x, final short y) {
            return Order.min$mcS$sp$(this, x, y);
         }

         public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.min$mcV$sp$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Order.max$(this, x, y);
         }

         public boolean max$mcZ$sp(final boolean x, final boolean y) {
            return Order.max$mcZ$sp$(this, x, y);
         }

         public byte max$mcB$sp(final byte x, final byte y) {
            return Order.max$mcB$sp$(this, x, y);
         }

         public char max$mcC$sp(final char x, final char y) {
            return Order.max$mcC$sp$(this, x, y);
         }

         public double max$mcD$sp(final double x, final double y) {
            return Order.max$mcD$sp$(this, x, y);
         }

         public float max$mcF$sp(final float x, final float y) {
            return Order.max$mcF$sp$(this, x, y);
         }

         public int max$mcI$sp(final int x, final int y) {
            return Order.max$mcI$sp$(this, x, y);
         }

         public long max$mcJ$sp(final long x, final long y) {
            return Order.max$mcJ$sp$(this, x, y);
         }

         public short max$mcS$sp(final short x, final short y) {
            return Order.max$mcS$sp$(this, x, y);
         }

         public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.max$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return Order.eqv$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Order.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Order.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Order.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Order.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Order.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Order.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Order.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Order.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Order.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Order.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Order.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Order.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Order.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Order.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Order.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.neqv$mcV$sp$(this, x, y);
         }

         public boolean lteqv(final Object x, final Object y) {
            return Order.lteqv$(this, x, y);
         }

         public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.lteqv$mcZ$sp$(this, x, y);
         }

         public boolean lteqv$mcB$sp(final byte x, final byte y) {
            return Order.lteqv$mcB$sp$(this, x, y);
         }

         public boolean lteqv$mcC$sp(final char x, final char y) {
            return Order.lteqv$mcC$sp$(this, x, y);
         }

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return Order.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return Order.lteqv$mcF$sp$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return Order.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv$mcJ$sp(final long x, final long y) {
            return Order.lteqv$mcJ$sp$(this, x, y);
         }

         public boolean lteqv$mcS$sp(final short x, final short y) {
            return Order.lteqv$mcS$sp$(this, x, y);
         }

         public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lteqv$mcV$sp$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Order.lt$(this, x, y);
         }

         public boolean lt$mcZ$sp(final boolean x, final boolean y) {
            return Order.lt$mcZ$sp$(this, x, y);
         }

         public boolean lt$mcB$sp(final byte x, final byte y) {
            return Order.lt$mcB$sp$(this, x, y);
         }

         public boolean lt$mcC$sp(final char x, final char y) {
            return Order.lt$mcC$sp$(this, x, y);
         }

         public boolean lt$mcD$sp(final double x, final double y) {
            return Order.lt$mcD$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return Order.lt$mcF$sp$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return Order.lt$mcI$sp$(this, x, y);
         }

         public boolean lt$mcJ$sp(final long x, final long y) {
            return Order.lt$mcJ$sp$(this, x, y);
         }

         public boolean lt$mcS$sp(final short x, final short y) {
            return Order.lt$mcS$sp$(this, x, y);
         }

         public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lt$mcV$sp$(this, x, y);
         }

         public boolean gteqv(final Object x, final Object y) {
            return Order.gteqv$(this, x, y);
         }

         public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.gteqv$mcZ$sp$(this, x, y);
         }

         public boolean gteqv$mcB$sp(final byte x, final byte y) {
            return Order.gteqv$mcB$sp$(this, x, y);
         }

         public boolean gteqv$mcC$sp(final char x, final char y) {
            return Order.gteqv$mcC$sp$(this, x, y);
         }

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return Order.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return Order.gteqv$mcF$sp$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return Order.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gteqv$mcJ$sp(final long x, final long y) {
            return Order.gteqv$mcJ$sp$(this, x, y);
         }

         public boolean gteqv$mcS$sp(final short x, final short y) {
            return Order.gteqv$mcS$sp$(this, x, y);
         }

         public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gteqv$mcV$sp$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Order.gt$(this, x, y);
         }

         public boolean gt$mcZ$sp(final boolean x, final boolean y) {
            return Order.gt$mcZ$sp$(this, x, y);
         }

         public boolean gt$mcB$sp(final byte x, final byte y) {
            return Order.gt$mcB$sp$(this, x, y);
         }

         public boolean gt$mcC$sp(final char x, final char y) {
            return Order.gt$mcC$sp$(this, x, y);
         }

         public boolean gt$mcD$sp(final double x, final double y) {
            return Order.gt$mcD$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return Order.gt$mcF$sp$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return Order.gt$mcI$sp$(this, x, y);
         }

         public boolean gt$mcJ$sp(final long x, final long y) {
            return Order.gt$mcJ$sp$(this, x, y);
         }

         public boolean gt$mcS$sp(final short x, final short y) {
            return Order.gt$mcS$sp$(this, x, y);
         }

         public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gt$mcV$sp$(this, x, y);
         }

         public Ordering toOrdering() {
            return Order.toOrdering$(this);
         }

         public Option partialComparison(final Object x, final Object y) {
            return PartialOrder.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
         }

         public Option partialComparison$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialComparison$mcB$sp$(this, x, y);
         }

         public Option partialComparison$mcC$sp(final char x, final char y) {
            return PartialOrder.partialComparison$mcC$sp$(this, x, y);
         }

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder.partialComparison$mcD$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder.partialComparison$mcI$sp$(this, x, y);
         }

         public Option partialComparison$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
         }

         public Option partialComparison$mcS$sp(final short x, final short y) {
            return PartialOrder.partialComparison$mcS$sp$(this, x, y);
         }

         public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialComparison$mcV$sp$(this, x, y);
         }

         public Option tryCompare(final Object x, final Object y) {
            return PartialOrder.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
         }

         public Option tryCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.tryCompare$mcB$sp$(this, x, y);
         }

         public Option tryCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.tryCompare$mcC$sp$(this, x, y);
         }

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.tryCompare$mcD$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.tryCompare$mcI$sp$(this, x, y);
         }

         public Option tryCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
         }

         public Option tryCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.tryCompare$mcS$sp$(this, x, y);
         }

         public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.tryCompare$mcV$sp$(this, x, y);
         }

         public Option pmin(final Object x, final Object y) {
            return PartialOrder.pmin$(this, x, y);
         }

         public Option pmin$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmin$mcZ$sp$(this, x, y);
         }

         public Option pmin$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmin$mcB$sp$(this, x, y);
         }

         public Option pmin$mcC$sp(final char x, final char y) {
            return PartialOrder.pmin$mcC$sp$(this, x, y);
         }

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder.pmin$mcD$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder.pmin$mcI$sp$(this, x, y);
         }

         public Option pmin$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmin$mcJ$sp$(this, x, y);
         }

         public Option pmin$mcS$sp(final short x, final short y) {
            return PartialOrder.pmin$mcS$sp$(this, x, y);
         }

         public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmin$mcV$sp$(this, x, y);
         }

         public Option pmax(final Object x, final Object y) {
            return PartialOrder.pmax$(this, x, y);
         }

         public Option pmax$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmax$mcZ$sp$(this, x, y);
         }

         public Option pmax$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmax$mcB$sp$(this, x, y);
         }

         public Option pmax$mcC$sp(final char x, final char y) {
            return PartialOrder.pmax$mcC$sp$(this, x, y);
         }

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder.pmax$mcD$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder.pmax$mcI$sp$(this, x, y);
         }

         public Option pmax$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmax$mcJ$sp$(this, x, y);
         }

         public Option pmax$mcS$sp(final short x, final short y) {
            return PartialOrder.pmax$mcS$sp$(this, x, y);
         }

         public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmax$mcV$sp$(this, x, y);
         }

         public int compare(final Tuple6 x, final Tuple6 y) {
            return BoxesRunTime.unboxToInt(.MODULE$.find$extension(scala.Predef..MODULE$.intArrayOps(new int[]{this.A0$490.compare(x._1(), y._1()), this.A1$467.compare(x._2(), y._2()), this.A2$444.compare(x._3(), y._3()), this.A3$421.compare(x._4(), y._4()), this.A4$398.compare(x._5(), y._5()), this.A5$375.compare(x._6(), y._6())}), (JFunction1.mcZI.sp)(x$50) -> x$50 != 0).getOrElse((JFunction0.mcI.sp)() -> 0));
         }

         public {
            this.A0$490 = A0$490;
            this.A1$467 = A1$467;
            this.A2$444 = A2$444;
            this.A3$421 = A3$421;
            this.A4$398 = A4$398;
            this.A5$375 = A5$375;
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static Order catsKernelOrderForTuple7$(final TupleOrderInstances $this, final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6) {
      return $this.catsKernelOrderForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   default Order catsKernelOrderForTuple7(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6) {
      return new Order(A0, A1, A2, A3, A4, A5, A6) {
         private final Order A0$491;
         private final Order A1$468;
         private final Order A2$445;
         private final Order A3$422;
         private final Order A4$399;
         private final Order A5$376;
         private final Order A6$353;

         public int compare$mcZ$sp(final boolean x, final boolean y) {
            return Order.compare$mcZ$sp$(this, x, y);
         }

         public int compare$mcB$sp(final byte x, final byte y) {
            return Order.compare$mcB$sp$(this, x, y);
         }

         public int compare$mcC$sp(final char x, final char y) {
            return Order.compare$mcC$sp$(this, x, y);
         }

         public int compare$mcD$sp(final double x, final double y) {
            return Order.compare$mcD$sp$(this, x, y);
         }

         public int compare$mcF$sp(final float x, final float y) {
            return Order.compare$mcF$sp$(this, x, y);
         }

         public int compare$mcI$sp(final int x, final int y) {
            return Order.compare$mcI$sp$(this, x, y);
         }

         public int compare$mcJ$sp(final long x, final long y) {
            return Order.compare$mcJ$sp$(this, x, y);
         }

         public int compare$mcS$sp(final short x, final short y) {
            return Order.compare$mcS$sp$(this, x, y);
         }

         public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.compare$mcV$sp$(this, x, y);
         }

         public Comparison comparison(final Object x, final Object y) {
            return Order.comparison$(this, x, y);
         }

         public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
            return Order.comparison$mcZ$sp$(this, x, y);
         }

         public Comparison comparison$mcB$sp(final byte x, final byte y) {
            return Order.comparison$mcB$sp$(this, x, y);
         }

         public Comparison comparison$mcC$sp(final char x, final char y) {
            return Order.comparison$mcC$sp$(this, x, y);
         }

         public Comparison comparison$mcD$sp(final double x, final double y) {
            return Order.comparison$mcD$sp$(this, x, y);
         }

         public Comparison comparison$mcF$sp(final float x, final float y) {
            return Order.comparison$mcF$sp$(this, x, y);
         }

         public Comparison comparison$mcI$sp(final int x, final int y) {
            return Order.comparison$mcI$sp$(this, x, y);
         }

         public Comparison comparison$mcJ$sp(final long x, final long y) {
            return Order.comparison$mcJ$sp$(this, x, y);
         }

         public Comparison comparison$mcS$sp(final short x, final short y) {
            return Order.comparison$mcS$sp$(this, x, y);
         }

         public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.comparison$mcV$sp$(this, x, y);
         }

         public double partialCompare(final Object x, final Object y) {
            return Order.partialCompare$(this, x, y);
         }

         public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
            return Order.partialCompare$mcZ$sp$(this, x, y);
         }

         public double partialCompare$mcB$sp(final byte x, final byte y) {
            return Order.partialCompare$mcB$sp$(this, x, y);
         }

         public double partialCompare$mcC$sp(final char x, final char y) {
            return Order.partialCompare$mcC$sp$(this, x, y);
         }

         public double partialCompare$mcD$sp(final double x, final double y) {
            return Order.partialCompare$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return Order.partialCompare$mcF$sp$(this, x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            return Order.partialCompare$mcI$sp$(this, x, y);
         }

         public double partialCompare$mcJ$sp(final long x, final long y) {
            return Order.partialCompare$mcJ$sp$(this, x, y);
         }

         public double partialCompare$mcS$sp(final short x, final short y) {
            return Order.partialCompare$mcS$sp$(this, x, y);
         }

         public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.partialCompare$mcV$sp$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Order.min$(this, x, y);
         }

         public boolean min$mcZ$sp(final boolean x, final boolean y) {
            return Order.min$mcZ$sp$(this, x, y);
         }

         public byte min$mcB$sp(final byte x, final byte y) {
            return Order.min$mcB$sp$(this, x, y);
         }

         public char min$mcC$sp(final char x, final char y) {
            return Order.min$mcC$sp$(this, x, y);
         }

         public double min$mcD$sp(final double x, final double y) {
            return Order.min$mcD$sp$(this, x, y);
         }

         public float min$mcF$sp(final float x, final float y) {
            return Order.min$mcF$sp$(this, x, y);
         }

         public int min$mcI$sp(final int x, final int y) {
            return Order.min$mcI$sp$(this, x, y);
         }

         public long min$mcJ$sp(final long x, final long y) {
            return Order.min$mcJ$sp$(this, x, y);
         }

         public short min$mcS$sp(final short x, final short y) {
            return Order.min$mcS$sp$(this, x, y);
         }

         public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.min$mcV$sp$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Order.max$(this, x, y);
         }

         public boolean max$mcZ$sp(final boolean x, final boolean y) {
            return Order.max$mcZ$sp$(this, x, y);
         }

         public byte max$mcB$sp(final byte x, final byte y) {
            return Order.max$mcB$sp$(this, x, y);
         }

         public char max$mcC$sp(final char x, final char y) {
            return Order.max$mcC$sp$(this, x, y);
         }

         public double max$mcD$sp(final double x, final double y) {
            return Order.max$mcD$sp$(this, x, y);
         }

         public float max$mcF$sp(final float x, final float y) {
            return Order.max$mcF$sp$(this, x, y);
         }

         public int max$mcI$sp(final int x, final int y) {
            return Order.max$mcI$sp$(this, x, y);
         }

         public long max$mcJ$sp(final long x, final long y) {
            return Order.max$mcJ$sp$(this, x, y);
         }

         public short max$mcS$sp(final short x, final short y) {
            return Order.max$mcS$sp$(this, x, y);
         }

         public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.max$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return Order.eqv$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Order.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Order.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Order.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Order.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Order.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Order.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Order.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Order.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Order.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Order.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Order.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Order.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Order.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Order.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Order.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.neqv$mcV$sp$(this, x, y);
         }

         public boolean lteqv(final Object x, final Object y) {
            return Order.lteqv$(this, x, y);
         }

         public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.lteqv$mcZ$sp$(this, x, y);
         }

         public boolean lteqv$mcB$sp(final byte x, final byte y) {
            return Order.lteqv$mcB$sp$(this, x, y);
         }

         public boolean lteqv$mcC$sp(final char x, final char y) {
            return Order.lteqv$mcC$sp$(this, x, y);
         }

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return Order.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return Order.lteqv$mcF$sp$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return Order.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv$mcJ$sp(final long x, final long y) {
            return Order.lteqv$mcJ$sp$(this, x, y);
         }

         public boolean lteqv$mcS$sp(final short x, final short y) {
            return Order.lteqv$mcS$sp$(this, x, y);
         }

         public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lteqv$mcV$sp$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Order.lt$(this, x, y);
         }

         public boolean lt$mcZ$sp(final boolean x, final boolean y) {
            return Order.lt$mcZ$sp$(this, x, y);
         }

         public boolean lt$mcB$sp(final byte x, final byte y) {
            return Order.lt$mcB$sp$(this, x, y);
         }

         public boolean lt$mcC$sp(final char x, final char y) {
            return Order.lt$mcC$sp$(this, x, y);
         }

         public boolean lt$mcD$sp(final double x, final double y) {
            return Order.lt$mcD$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return Order.lt$mcF$sp$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return Order.lt$mcI$sp$(this, x, y);
         }

         public boolean lt$mcJ$sp(final long x, final long y) {
            return Order.lt$mcJ$sp$(this, x, y);
         }

         public boolean lt$mcS$sp(final short x, final short y) {
            return Order.lt$mcS$sp$(this, x, y);
         }

         public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lt$mcV$sp$(this, x, y);
         }

         public boolean gteqv(final Object x, final Object y) {
            return Order.gteqv$(this, x, y);
         }

         public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.gteqv$mcZ$sp$(this, x, y);
         }

         public boolean gteqv$mcB$sp(final byte x, final byte y) {
            return Order.gteqv$mcB$sp$(this, x, y);
         }

         public boolean gteqv$mcC$sp(final char x, final char y) {
            return Order.gteqv$mcC$sp$(this, x, y);
         }

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return Order.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return Order.gteqv$mcF$sp$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return Order.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gteqv$mcJ$sp(final long x, final long y) {
            return Order.gteqv$mcJ$sp$(this, x, y);
         }

         public boolean gteqv$mcS$sp(final short x, final short y) {
            return Order.gteqv$mcS$sp$(this, x, y);
         }

         public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gteqv$mcV$sp$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Order.gt$(this, x, y);
         }

         public boolean gt$mcZ$sp(final boolean x, final boolean y) {
            return Order.gt$mcZ$sp$(this, x, y);
         }

         public boolean gt$mcB$sp(final byte x, final byte y) {
            return Order.gt$mcB$sp$(this, x, y);
         }

         public boolean gt$mcC$sp(final char x, final char y) {
            return Order.gt$mcC$sp$(this, x, y);
         }

         public boolean gt$mcD$sp(final double x, final double y) {
            return Order.gt$mcD$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return Order.gt$mcF$sp$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return Order.gt$mcI$sp$(this, x, y);
         }

         public boolean gt$mcJ$sp(final long x, final long y) {
            return Order.gt$mcJ$sp$(this, x, y);
         }

         public boolean gt$mcS$sp(final short x, final short y) {
            return Order.gt$mcS$sp$(this, x, y);
         }

         public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gt$mcV$sp$(this, x, y);
         }

         public Ordering toOrdering() {
            return Order.toOrdering$(this);
         }

         public Option partialComparison(final Object x, final Object y) {
            return PartialOrder.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
         }

         public Option partialComparison$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialComparison$mcB$sp$(this, x, y);
         }

         public Option partialComparison$mcC$sp(final char x, final char y) {
            return PartialOrder.partialComparison$mcC$sp$(this, x, y);
         }

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder.partialComparison$mcD$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder.partialComparison$mcI$sp$(this, x, y);
         }

         public Option partialComparison$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
         }

         public Option partialComparison$mcS$sp(final short x, final short y) {
            return PartialOrder.partialComparison$mcS$sp$(this, x, y);
         }

         public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialComparison$mcV$sp$(this, x, y);
         }

         public Option tryCompare(final Object x, final Object y) {
            return PartialOrder.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
         }

         public Option tryCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.tryCompare$mcB$sp$(this, x, y);
         }

         public Option tryCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.tryCompare$mcC$sp$(this, x, y);
         }

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.tryCompare$mcD$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.tryCompare$mcI$sp$(this, x, y);
         }

         public Option tryCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
         }

         public Option tryCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.tryCompare$mcS$sp$(this, x, y);
         }

         public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.tryCompare$mcV$sp$(this, x, y);
         }

         public Option pmin(final Object x, final Object y) {
            return PartialOrder.pmin$(this, x, y);
         }

         public Option pmin$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmin$mcZ$sp$(this, x, y);
         }

         public Option pmin$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmin$mcB$sp$(this, x, y);
         }

         public Option pmin$mcC$sp(final char x, final char y) {
            return PartialOrder.pmin$mcC$sp$(this, x, y);
         }

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder.pmin$mcD$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder.pmin$mcI$sp$(this, x, y);
         }

         public Option pmin$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmin$mcJ$sp$(this, x, y);
         }

         public Option pmin$mcS$sp(final short x, final short y) {
            return PartialOrder.pmin$mcS$sp$(this, x, y);
         }

         public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmin$mcV$sp$(this, x, y);
         }

         public Option pmax(final Object x, final Object y) {
            return PartialOrder.pmax$(this, x, y);
         }

         public Option pmax$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmax$mcZ$sp$(this, x, y);
         }

         public Option pmax$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmax$mcB$sp$(this, x, y);
         }

         public Option pmax$mcC$sp(final char x, final char y) {
            return PartialOrder.pmax$mcC$sp$(this, x, y);
         }

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder.pmax$mcD$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder.pmax$mcI$sp$(this, x, y);
         }

         public Option pmax$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmax$mcJ$sp$(this, x, y);
         }

         public Option pmax$mcS$sp(final short x, final short y) {
            return PartialOrder.pmax$mcS$sp$(this, x, y);
         }

         public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmax$mcV$sp$(this, x, y);
         }

         public int compare(final Tuple7 x, final Tuple7 y) {
            return BoxesRunTime.unboxToInt(.MODULE$.find$extension(scala.Predef..MODULE$.intArrayOps(new int[]{this.A0$491.compare(x._1(), y._1()), this.A1$468.compare(x._2(), y._2()), this.A2$445.compare(x._3(), y._3()), this.A3$422.compare(x._4(), y._4()), this.A4$399.compare(x._5(), y._5()), this.A5$376.compare(x._6(), y._6()), this.A6$353.compare(x._7(), y._7())}), (JFunction1.mcZI.sp)(x$51) -> x$51 != 0).getOrElse((JFunction0.mcI.sp)() -> 0));
         }

         public {
            this.A0$491 = A0$491;
            this.A1$468 = A1$468;
            this.A2$445 = A2$445;
            this.A3$422 = A3$422;
            this.A4$399 = A4$399;
            this.A5$376 = A5$376;
            this.A6$353 = A6$353;
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static Order catsKernelOrderForTuple8$(final TupleOrderInstances $this, final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7) {
      return $this.catsKernelOrderForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   default Order catsKernelOrderForTuple8(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7) {
      return new Order(A0, A1, A2, A3, A4, A5, A6, A7) {
         private final Order A0$492;
         private final Order A1$469;
         private final Order A2$446;
         private final Order A3$423;
         private final Order A4$400;
         private final Order A5$377;
         private final Order A6$354;
         private final Order A7$331;

         public int compare$mcZ$sp(final boolean x, final boolean y) {
            return Order.compare$mcZ$sp$(this, x, y);
         }

         public int compare$mcB$sp(final byte x, final byte y) {
            return Order.compare$mcB$sp$(this, x, y);
         }

         public int compare$mcC$sp(final char x, final char y) {
            return Order.compare$mcC$sp$(this, x, y);
         }

         public int compare$mcD$sp(final double x, final double y) {
            return Order.compare$mcD$sp$(this, x, y);
         }

         public int compare$mcF$sp(final float x, final float y) {
            return Order.compare$mcF$sp$(this, x, y);
         }

         public int compare$mcI$sp(final int x, final int y) {
            return Order.compare$mcI$sp$(this, x, y);
         }

         public int compare$mcJ$sp(final long x, final long y) {
            return Order.compare$mcJ$sp$(this, x, y);
         }

         public int compare$mcS$sp(final short x, final short y) {
            return Order.compare$mcS$sp$(this, x, y);
         }

         public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.compare$mcV$sp$(this, x, y);
         }

         public Comparison comparison(final Object x, final Object y) {
            return Order.comparison$(this, x, y);
         }

         public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
            return Order.comparison$mcZ$sp$(this, x, y);
         }

         public Comparison comparison$mcB$sp(final byte x, final byte y) {
            return Order.comparison$mcB$sp$(this, x, y);
         }

         public Comparison comparison$mcC$sp(final char x, final char y) {
            return Order.comparison$mcC$sp$(this, x, y);
         }

         public Comparison comparison$mcD$sp(final double x, final double y) {
            return Order.comparison$mcD$sp$(this, x, y);
         }

         public Comparison comparison$mcF$sp(final float x, final float y) {
            return Order.comparison$mcF$sp$(this, x, y);
         }

         public Comparison comparison$mcI$sp(final int x, final int y) {
            return Order.comparison$mcI$sp$(this, x, y);
         }

         public Comparison comparison$mcJ$sp(final long x, final long y) {
            return Order.comparison$mcJ$sp$(this, x, y);
         }

         public Comparison comparison$mcS$sp(final short x, final short y) {
            return Order.comparison$mcS$sp$(this, x, y);
         }

         public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.comparison$mcV$sp$(this, x, y);
         }

         public double partialCompare(final Object x, final Object y) {
            return Order.partialCompare$(this, x, y);
         }

         public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
            return Order.partialCompare$mcZ$sp$(this, x, y);
         }

         public double partialCompare$mcB$sp(final byte x, final byte y) {
            return Order.partialCompare$mcB$sp$(this, x, y);
         }

         public double partialCompare$mcC$sp(final char x, final char y) {
            return Order.partialCompare$mcC$sp$(this, x, y);
         }

         public double partialCompare$mcD$sp(final double x, final double y) {
            return Order.partialCompare$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return Order.partialCompare$mcF$sp$(this, x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            return Order.partialCompare$mcI$sp$(this, x, y);
         }

         public double partialCompare$mcJ$sp(final long x, final long y) {
            return Order.partialCompare$mcJ$sp$(this, x, y);
         }

         public double partialCompare$mcS$sp(final short x, final short y) {
            return Order.partialCompare$mcS$sp$(this, x, y);
         }

         public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.partialCompare$mcV$sp$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Order.min$(this, x, y);
         }

         public boolean min$mcZ$sp(final boolean x, final boolean y) {
            return Order.min$mcZ$sp$(this, x, y);
         }

         public byte min$mcB$sp(final byte x, final byte y) {
            return Order.min$mcB$sp$(this, x, y);
         }

         public char min$mcC$sp(final char x, final char y) {
            return Order.min$mcC$sp$(this, x, y);
         }

         public double min$mcD$sp(final double x, final double y) {
            return Order.min$mcD$sp$(this, x, y);
         }

         public float min$mcF$sp(final float x, final float y) {
            return Order.min$mcF$sp$(this, x, y);
         }

         public int min$mcI$sp(final int x, final int y) {
            return Order.min$mcI$sp$(this, x, y);
         }

         public long min$mcJ$sp(final long x, final long y) {
            return Order.min$mcJ$sp$(this, x, y);
         }

         public short min$mcS$sp(final short x, final short y) {
            return Order.min$mcS$sp$(this, x, y);
         }

         public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.min$mcV$sp$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Order.max$(this, x, y);
         }

         public boolean max$mcZ$sp(final boolean x, final boolean y) {
            return Order.max$mcZ$sp$(this, x, y);
         }

         public byte max$mcB$sp(final byte x, final byte y) {
            return Order.max$mcB$sp$(this, x, y);
         }

         public char max$mcC$sp(final char x, final char y) {
            return Order.max$mcC$sp$(this, x, y);
         }

         public double max$mcD$sp(final double x, final double y) {
            return Order.max$mcD$sp$(this, x, y);
         }

         public float max$mcF$sp(final float x, final float y) {
            return Order.max$mcF$sp$(this, x, y);
         }

         public int max$mcI$sp(final int x, final int y) {
            return Order.max$mcI$sp$(this, x, y);
         }

         public long max$mcJ$sp(final long x, final long y) {
            return Order.max$mcJ$sp$(this, x, y);
         }

         public short max$mcS$sp(final short x, final short y) {
            return Order.max$mcS$sp$(this, x, y);
         }

         public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.max$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return Order.eqv$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Order.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Order.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Order.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Order.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Order.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Order.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Order.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Order.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Order.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Order.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Order.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Order.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Order.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Order.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Order.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.neqv$mcV$sp$(this, x, y);
         }

         public boolean lteqv(final Object x, final Object y) {
            return Order.lteqv$(this, x, y);
         }

         public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.lteqv$mcZ$sp$(this, x, y);
         }

         public boolean lteqv$mcB$sp(final byte x, final byte y) {
            return Order.lteqv$mcB$sp$(this, x, y);
         }

         public boolean lteqv$mcC$sp(final char x, final char y) {
            return Order.lteqv$mcC$sp$(this, x, y);
         }

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return Order.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return Order.lteqv$mcF$sp$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return Order.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv$mcJ$sp(final long x, final long y) {
            return Order.lteqv$mcJ$sp$(this, x, y);
         }

         public boolean lteqv$mcS$sp(final short x, final short y) {
            return Order.lteqv$mcS$sp$(this, x, y);
         }

         public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lteqv$mcV$sp$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Order.lt$(this, x, y);
         }

         public boolean lt$mcZ$sp(final boolean x, final boolean y) {
            return Order.lt$mcZ$sp$(this, x, y);
         }

         public boolean lt$mcB$sp(final byte x, final byte y) {
            return Order.lt$mcB$sp$(this, x, y);
         }

         public boolean lt$mcC$sp(final char x, final char y) {
            return Order.lt$mcC$sp$(this, x, y);
         }

         public boolean lt$mcD$sp(final double x, final double y) {
            return Order.lt$mcD$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return Order.lt$mcF$sp$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return Order.lt$mcI$sp$(this, x, y);
         }

         public boolean lt$mcJ$sp(final long x, final long y) {
            return Order.lt$mcJ$sp$(this, x, y);
         }

         public boolean lt$mcS$sp(final short x, final short y) {
            return Order.lt$mcS$sp$(this, x, y);
         }

         public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lt$mcV$sp$(this, x, y);
         }

         public boolean gteqv(final Object x, final Object y) {
            return Order.gteqv$(this, x, y);
         }

         public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.gteqv$mcZ$sp$(this, x, y);
         }

         public boolean gteqv$mcB$sp(final byte x, final byte y) {
            return Order.gteqv$mcB$sp$(this, x, y);
         }

         public boolean gteqv$mcC$sp(final char x, final char y) {
            return Order.gteqv$mcC$sp$(this, x, y);
         }

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return Order.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return Order.gteqv$mcF$sp$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return Order.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gteqv$mcJ$sp(final long x, final long y) {
            return Order.gteqv$mcJ$sp$(this, x, y);
         }

         public boolean gteqv$mcS$sp(final short x, final short y) {
            return Order.gteqv$mcS$sp$(this, x, y);
         }

         public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gteqv$mcV$sp$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Order.gt$(this, x, y);
         }

         public boolean gt$mcZ$sp(final boolean x, final boolean y) {
            return Order.gt$mcZ$sp$(this, x, y);
         }

         public boolean gt$mcB$sp(final byte x, final byte y) {
            return Order.gt$mcB$sp$(this, x, y);
         }

         public boolean gt$mcC$sp(final char x, final char y) {
            return Order.gt$mcC$sp$(this, x, y);
         }

         public boolean gt$mcD$sp(final double x, final double y) {
            return Order.gt$mcD$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return Order.gt$mcF$sp$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return Order.gt$mcI$sp$(this, x, y);
         }

         public boolean gt$mcJ$sp(final long x, final long y) {
            return Order.gt$mcJ$sp$(this, x, y);
         }

         public boolean gt$mcS$sp(final short x, final short y) {
            return Order.gt$mcS$sp$(this, x, y);
         }

         public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gt$mcV$sp$(this, x, y);
         }

         public Ordering toOrdering() {
            return Order.toOrdering$(this);
         }

         public Option partialComparison(final Object x, final Object y) {
            return PartialOrder.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
         }

         public Option partialComparison$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialComparison$mcB$sp$(this, x, y);
         }

         public Option partialComparison$mcC$sp(final char x, final char y) {
            return PartialOrder.partialComparison$mcC$sp$(this, x, y);
         }

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder.partialComparison$mcD$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder.partialComparison$mcI$sp$(this, x, y);
         }

         public Option partialComparison$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
         }

         public Option partialComparison$mcS$sp(final short x, final short y) {
            return PartialOrder.partialComparison$mcS$sp$(this, x, y);
         }

         public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialComparison$mcV$sp$(this, x, y);
         }

         public Option tryCompare(final Object x, final Object y) {
            return PartialOrder.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
         }

         public Option tryCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.tryCompare$mcB$sp$(this, x, y);
         }

         public Option tryCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.tryCompare$mcC$sp$(this, x, y);
         }

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.tryCompare$mcD$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.tryCompare$mcI$sp$(this, x, y);
         }

         public Option tryCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
         }

         public Option tryCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.tryCompare$mcS$sp$(this, x, y);
         }

         public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.tryCompare$mcV$sp$(this, x, y);
         }

         public Option pmin(final Object x, final Object y) {
            return PartialOrder.pmin$(this, x, y);
         }

         public Option pmin$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmin$mcZ$sp$(this, x, y);
         }

         public Option pmin$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmin$mcB$sp$(this, x, y);
         }

         public Option pmin$mcC$sp(final char x, final char y) {
            return PartialOrder.pmin$mcC$sp$(this, x, y);
         }

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder.pmin$mcD$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder.pmin$mcI$sp$(this, x, y);
         }

         public Option pmin$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmin$mcJ$sp$(this, x, y);
         }

         public Option pmin$mcS$sp(final short x, final short y) {
            return PartialOrder.pmin$mcS$sp$(this, x, y);
         }

         public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmin$mcV$sp$(this, x, y);
         }

         public Option pmax(final Object x, final Object y) {
            return PartialOrder.pmax$(this, x, y);
         }

         public Option pmax$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmax$mcZ$sp$(this, x, y);
         }

         public Option pmax$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmax$mcB$sp$(this, x, y);
         }

         public Option pmax$mcC$sp(final char x, final char y) {
            return PartialOrder.pmax$mcC$sp$(this, x, y);
         }

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder.pmax$mcD$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder.pmax$mcI$sp$(this, x, y);
         }

         public Option pmax$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmax$mcJ$sp$(this, x, y);
         }

         public Option pmax$mcS$sp(final short x, final short y) {
            return PartialOrder.pmax$mcS$sp$(this, x, y);
         }

         public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmax$mcV$sp$(this, x, y);
         }

         public int compare(final Tuple8 x, final Tuple8 y) {
            return BoxesRunTime.unboxToInt(.MODULE$.find$extension(scala.Predef..MODULE$.intArrayOps(new int[]{this.A0$492.compare(x._1(), y._1()), this.A1$469.compare(x._2(), y._2()), this.A2$446.compare(x._3(), y._3()), this.A3$423.compare(x._4(), y._4()), this.A4$400.compare(x._5(), y._5()), this.A5$377.compare(x._6(), y._6()), this.A6$354.compare(x._7(), y._7()), this.A7$331.compare(x._8(), y._8())}), (JFunction1.mcZI.sp)(x$52) -> x$52 != 0).getOrElse((JFunction0.mcI.sp)() -> 0));
         }

         public {
            this.A0$492 = A0$492;
            this.A1$469 = A1$469;
            this.A2$446 = A2$446;
            this.A3$423 = A3$423;
            this.A4$400 = A4$400;
            this.A5$377 = A5$377;
            this.A6$354 = A6$354;
            this.A7$331 = A7$331;
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static Order catsKernelOrderForTuple9$(final TupleOrderInstances $this, final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8) {
      return $this.catsKernelOrderForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   default Order catsKernelOrderForTuple9(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8) {
      return new Order(A0, A1, A2, A3, A4, A5, A6, A7, A8) {
         private final Order A0$493;
         private final Order A1$470;
         private final Order A2$447;
         private final Order A3$424;
         private final Order A4$401;
         private final Order A5$378;
         private final Order A6$355;
         private final Order A7$332;
         private final Order A8$309;

         public int compare$mcZ$sp(final boolean x, final boolean y) {
            return Order.compare$mcZ$sp$(this, x, y);
         }

         public int compare$mcB$sp(final byte x, final byte y) {
            return Order.compare$mcB$sp$(this, x, y);
         }

         public int compare$mcC$sp(final char x, final char y) {
            return Order.compare$mcC$sp$(this, x, y);
         }

         public int compare$mcD$sp(final double x, final double y) {
            return Order.compare$mcD$sp$(this, x, y);
         }

         public int compare$mcF$sp(final float x, final float y) {
            return Order.compare$mcF$sp$(this, x, y);
         }

         public int compare$mcI$sp(final int x, final int y) {
            return Order.compare$mcI$sp$(this, x, y);
         }

         public int compare$mcJ$sp(final long x, final long y) {
            return Order.compare$mcJ$sp$(this, x, y);
         }

         public int compare$mcS$sp(final short x, final short y) {
            return Order.compare$mcS$sp$(this, x, y);
         }

         public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.compare$mcV$sp$(this, x, y);
         }

         public Comparison comparison(final Object x, final Object y) {
            return Order.comparison$(this, x, y);
         }

         public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
            return Order.comparison$mcZ$sp$(this, x, y);
         }

         public Comparison comparison$mcB$sp(final byte x, final byte y) {
            return Order.comparison$mcB$sp$(this, x, y);
         }

         public Comparison comparison$mcC$sp(final char x, final char y) {
            return Order.comparison$mcC$sp$(this, x, y);
         }

         public Comparison comparison$mcD$sp(final double x, final double y) {
            return Order.comparison$mcD$sp$(this, x, y);
         }

         public Comparison comparison$mcF$sp(final float x, final float y) {
            return Order.comparison$mcF$sp$(this, x, y);
         }

         public Comparison comparison$mcI$sp(final int x, final int y) {
            return Order.comparison$mcI$sp$(this, x, y);
         }

         public Comparison comparison$mcJ$sp(final long x, final long y) {
            return Order.comparison$mcJ$sp$(this, x, y);
         }

         public Comparison comparison$mcS$sp(final short x, final short y) {
            return Order.comparison$mcS$sp$(this, x, y);
         }

         public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.comparison$mcV$sp$(this, x, y);
         }

         public double partialCompare(final Object x, final Object y) {
            return Order.partialCompare$(this, x, y);
         }

         public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
            return Order.partialCompare$mcZ$sp$(this, x, y);
         }

         public double partialCompare$mcB$sp(final byte x, final byte y) {
            return Order.partialCompare$mcB$sp$(this, x, y);
         }

         public double partialCompare$mcC$sp(final char x, final char y) {
            return Order.partialCompare$mcC$sp$(this, x, y);
         }

         public double partialCompare$mcD$sp(final double x, final double y) {
            return Order.partialCompare$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return Order.partialCompare$mcF$sp$(this, x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            return Order.partialCompare$mcI$sp$(this, x, y);
         }

         public double partialCompare$mcJ$sp(final long x, final long y) {
            return Order.partialCompare$mcJ$sp$(this, x, y);
         }

         public double partialCompare$mcS$sp(final short x, final short y) {
            return Order.partialCompare$mcS$sp$(this, x, y);
         }

         public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.partialCompare$mcV$sp$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Order.min$(this, x, y);
         }

         public boolean min$mcZ$sp(final boolean x, final boolean y) {
            return Order.min$mcZ$sp$(this, x, y);
         }

         public byte min$mcB$sp(final byte x, final byte y) {
            return Order.min$mcB$sp$(this, x, y);
         }

         public char min$mcC$sp(final char x, final char y) {
            return Order.min$mcC$sp$(this, x, y);
         }

         public double min$mcD$sp(final double x, final double y) {
            return Order.min$mcD$sp$(this, x, y);
         }

         public float min$mcF$sp(final float x, final float y) {
            return Order.min$mcF$sp$(this, x, y);
         }

         public int min$mcI$sp(final int x, final int y) {
            return Order.min$mcI$sp$(this, x, y);
         }

         public long min$mcJ$sp(final long x, final long y) {
            return Order.min$mcJ$sp$(this, x, y);
         }

         public short min$mcS$sp(final short x, final short y) {
            return Order.min$mcS$sp$(this, x, y);
         }

         public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.min$mcV$sp$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Order.max$(this, x, y);
         }

         public boolean max$mcZ$sp(final boolean x, final boolean y) {
            return Order.max$mcZ$sp$(this, x, y);
         }

         public byte max$mcB$sp(final byte x, final byte y) {
            return Order.max$mcB$sp$(this, x, y);
         }

         public char max$mcC$sp(final char x, final char y) {
            return Order.max$mcC$sp$(this, x, y);
         }

         public double max$mcD$sp(final double x, final double y) {
            return Order.max$mcD$sp$(this, x, y);
         }

         public float max$mcF$sp(final float x, final float y) {
            return Order.max$mcF$sp$(this, x, y);
         }

         public int max$mcI$sp(final int x, final int y) {
            return Order.max$mcI$sp$(this, x, y);
         }

         public long max$mcJ$sp(final long x, final long y) {
            return Order.max$mcJ$sp$(this, x, y);
         }

         public short max$mcS$sp(final short x, final short y) {
            return Order.max$mcS$sp$(this, x, y);
         }

         public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.max$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return Order.eqv$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Order.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Order.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Order.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Order.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Order.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Order.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Order.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Order.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Order.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Order.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Order.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Order.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Order.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Order.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Order.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.neqv$mcV$sp$(this, x, y);
         }

         public boolean lteqv(final Object x, final Object y) {
            return Order.lteqv$(this, x, y);
         }

         public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.lteqv$mcZ$sp$(this, x, y);
         }

         public boolean lteqv$mcB$sp(final byte x, final byte y) {
            return Order.lteqv$mcB$sp$(this, x, y);
         }

         public boolean lteqv$mcC$sp(final char x, final char y) {
            return Order.lteqv$mcC$sp$(this, x, y);
         }

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return Order.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return Order.lteqv$mcF$sp$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return Order.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv$mcJ$sp(final long x, final long y) {
            return Order.lteqv$mcJ$sp$(this, x, y);
         }

         public boolean lteqv$mcS$sp(final short x, final short y) {
            return Order.lteqv$mcS$sp$(this, x, y);
         }

         public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lteqv$mcV$sp$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Order.lt$(this, x, y);
         }

         public boolean lt$mcZ$sp(final boolean x, final boolean y) {
            return Order.lt$mcZ$sp$(this, x, y);
         }

         public boolean lt$mcB$sp(final byte x, final byte y) {
            return Order.lt$mcB$sp$(this, x, y);
         }

         public boolean lt$mcC$sp(final char x, final char y) {
            return Order.lt$mcC$sp$(this, x, y);
         }

         public boolean lt$mcD$sp(final double x, final double y) {
            return Order.lt$mcD$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return Order.lt$mcF$sp$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return Order.lt$mcI$sp$(this, x, y);
         }

         public boolean lt$mcJ$sp(final long x, final long y) {
            return Order.lt$mcJ$sp$(this, x, y);
         }

         public boolean lt$mcS$sp(final short x, final short y) {
            return Order.lt$mcS$sp$(this, x, y);
         }

         public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lt$mcV$sp$(this, x, y);
         }

         public boolean gteqv(final Object x, final Object y) {
            return Order.gteqv$(this, x, y);
         }

         public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.gteqv$mcZ$sp$(this, x, y);
         }

         public boolean gteqv$mcB$sp(final byte x, final byte y) {
            return Order.gteqv$mcB$sp$(this, x, y);
         }

         public boolean gteqv$mcC$sp(final char x, final char y) {
            return Order.gteqv$mcC$sp$(this, x, y);
         }

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return Order.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return Order.gteqv$mcF$sp$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return Order.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gteqv$mcJ$sp(final long x, final long y) {
            return Order.gteqv$mcJ$sp$(this, x, y);
         }

         public boolean gteqv$mcS$sp(final short x, final short y) {
            return Order.gteqv$mcS$sp$(this, x, y);
         }

         public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gteqv$mcV$sp$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Order.gt$(this, x, y);
         }

         public boolean gt$mcZ$sp(final boolean x, final boolean y) {
            return Order.gt$mcZ$sp$(this, x, y);
         }

         public boolean gt$mcB$sp(final byte x, final byte y) {
            return Order.gt$mcB$sp$(this, x, y);
         }

         public boolean gt$mcC$sp(final char x, final char y) {
            return Order.gt$mcC$sp$(this, x, y);
         }

         public boolean gt$mcD$sp(final double x, final double y) {
            return Order.gt$mcD$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return Order.gt$mcF$sp$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return Order.gt$mcI$sp$(this, x, y);
         }

         public boolean gt$mcJ$sp(final long x, final long y) {
            return Order.gt$mcJ$sp$(this, x, y);
         }

         public boolean gt$mcS$sp(final short x, final short y) {
            return Order.gt$mcS$sp$(this, x, y);
         }

         public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gt$mcV$sp$(this, x, y);
         }

         public Ordering toOrdering() {
            return Order.toOrdering$(this);
         }

         public Option partialComparison(final Object x, final Object y) {
            return PartialOrder.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
         }

         public Option partialComparison$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialComparison$mcB$sp$(this, x, y);
         }

         public Option partialComparison$mcC$sp(final char x, final char y) {
            return PartialOrder.partialComparison$mcC$sp$(this, x, y);
         }

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder.partialComparison$mcD$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder.partialComparison$mcI$sp$(this, x, y);
         }

         public Option partialComparison$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
         }

         public Option partialComparison$mcS$sp(final short x, final short y) {
            return PartialOrder.partialComparison$mcS$sp$(this, x, y);
         }

         public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialComparison$mcV$sp$(this, x, y);
         }

         public Option tryCompare(final Object x, final Object y) {
            return PartialOrder.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
         }

         public Option tryCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.tryCompare$mcB$sp$(this, x, y);
         }

         public Option tryCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.tryCompare$mcC$sp$(this, x, y);
         }

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.tryCompare$mcD$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.tryCompare$mcI$sp$(this, x, y);
         }

         public Option tryCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
         }

         public Option tryCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.tryCompare$mcS$sp$(this, x, y);
         }

         public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.tryCompare$mcV$sp$(this, x, y);
         }

         public Option pmin(final Object x, final Object y) {
            return PartialOrder.pmin$(this, x, y);
         }

         public Option pmin$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmin$mcZ$sp$(this, x, y);
         }

         public Option pmin$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmin$mcB$sp$(this, x, y);
         }

         public Option pmin$mcC$sp(final char x, final char y) {
            return PartialOrder.pmin$mcC$sp$(this, x, y);
         }

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder.pmin$mcD$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder.pmin$mcI$sp$(this, x, y);
         }

         public Option pmin$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmin$mcJ$sp$(this, x, y);
         }

         public Option pmin$mcS$sp(final short x, final short y) {
            return PartialOrder.pmin$mcS$sp$(this, x, y);
         }

         public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmin$mcV$sp$(this, x, y);
         }

         public Option pmax(final Object x, final Object y) {
            return PartialOrder.pmax$(this, x, y);
         }

         public Option pmax$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmax$mcZ$sp$(this, x, y);
         }

         public Option pmax$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmax$mcB$sp$(this, x, y);
         }

         public Option pmax$mcC$sp(final char x, final char y) {
            return PartialOrder.pmax$mcC$sp$(this, x, y);
         }

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder.pmax$mcD$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder.pmax$mcI$sp$(this, x, y);
         }

         public Option pmax$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmax$mcJ$sp$(this, x, y);
         }

         public Option pmax$mcS$sp(final short x, final short y) {
            return PartialOrder.pmax$mcS$sp$(this, x, y);
         }

         public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmax$mcV$sp$(this, x, y);
         }

         public int compare(final Tuple9 x, final Tuple9 y) {
            return BoxesRunTime.unboxToInt(.MODULE$.find$extension(scala.Predef..MODULE$.intArrayOps(new int[]{this.A0$493.compare(x._1(), y._1()), this.A1$470.compare(x._2(), y._2()), this.A2$447.compare(x._3(), y._3()), this.A3$424.compare(x._4(), y._4()), this.A4$401.compare(x._5(), y._5()), this.A5$378.compare(x._6(), y._6()), this.A6$355.compare(x._7(), y._7()), this.A7$332.compare(x._8(), y._8()), this.A8$309.compare(x._9(), y._9())}), (JFunction1.mcZI.sp)(x$53) -> x$53 != 0).getOrElse((JFunction0.mcI.sp)() -> 0));
         }

         public {
            this.A0$493 = A0$493;
            this.A1$470 = A1$470;
            this.A2$447 = A2$447;
            this.A3$424 = A3$424;
            this.A4$401 = A4$401;
            this.A5$378 = A5$378;
            this.A6$355 = A6$355;
            this.A7$332 = A7$332;
            this.A8$309 = A8$309;
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static Order catsKernelOrderForTuple10$(final TupleOrderInstances $this, final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9) {
      return $this.catsKernelOrderForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   default Order catsKernelOrderForTuple10(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9) {
      return new Order(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9) {
         private final Order A0$494;
         private final Order A1$471;
         private final Order A2$448;
         private final Order A3$425;
         private final Order A4$402;
         private final Order A5$379;
         private final Order A6$356;
         private final Order A7$333;
         private final Order A8$310;
         private final Order A9$287;

         public int compare$mcZ$sp(final boolean x, final boolean y) {
            return Order.compare$mcZ$sp$(this, x, y);
         }

         public int compare$mcB$sp(final byte x, final byte y) {
            return Order.compare$mcB$sp$(this, x, y);
         }

         public int compare$mcC$sp(final char x, final char y) {
            return Order.compare$mcC$sp$(this, x, y);
         }

         public int compare$mcD$sp(final double x, final double y) {
            return Order.compare$mcD$sp$(this, x, y);
         }

         public int compare$mcF$sp(final float x, final float y) {
            return Order.compare$mcF$sp$(this, x, y);
         }

         public int compare$mcI$sp(final int x, final int y) {
            return Order.compare$mcI$sp$(this, x, y);
         }

         public int compare$mcJ$sp(final long x, final long y) {
            return Order.compare$mcJ$sp$(this, x, y);
         }

         public int compare$mcS$sp(final short x, final short y) {
            return Order.compare$mcS$sp$(this, x, y);
         }

         public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.compare$mcV$sp$(this, x, y);
         }

         public Comparison comparison(final Object x, final Object y) {
            return Order.comparison$(this, x, y);
         }

         public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
            return Order.comparison$mcZ$sp$(this, x, y);
         }

         public Comparison comparison$mcB$sp(final byte x, final byte y) {
            return Order.comparison$mcB$sp$(this, x, y);
         }

         public Comparison comparison$mcC$sp(final char x, final char y) {
            return Order.comparison$mcC$sp$(this, x, y);
         }

         public Comparison comparison$mcD$sp(final double x, final double y) {
            return Order.comparison$mcD$sp$(this, x, y);
         }

         public Comparison comparison$mcF$sp(final float x, final float y) {
            return Order.comparison$mcF$sp$(this, x, y);
         }

         public Comparison comparison$mcI$sp(final int x, final int y) {
            return Order.comparison$mcI$sp$(this, x, y);
         }

         public Comparison comparison$mcJ$sp(final long x, final long y) {
            return Order.comparison$mcJ$sp$(this, x, y);
         }

         public Comparison comparison$mcS$sp(final short x, final short y) {
            return Order.comparison$mcS$sp$(this, x, y);
         }

         public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.comparison$mcV$sp$(this, x, y);
         }

         public double partialCompare(final Object x, final Object y) {
            return Order.partialCompare$(this, x, y);
         }

         public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
            return Order.partialCompare$mcZ$sp$(this, x, y);
         }

         public double partialCompare$mcB$sp(final byte x, final byte y) {
            return Order.partialCompare$mcB$sp$(this, x, y);
         }

         public double partialCompare$mcC$sp(final char x, final char y) {
            return Order.partialCompare$mcC$sp$(this, x, y);
         }

         public double partialCompare$mcD$sp(final double x, final double y) {
            return Order.partialCompare$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return Order.partialCompare$mcF$sp$(this, x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            return Order.partialCompare$mcI$sp$(this, x, y);
         }

         public double partialCompare$mcJ$sp(final long x, final long y) {
            return Order.partialCompare$mcJ$sp$(this, x, y);
         }

         public double partialCompare$mcS$sp(final short x, final short y) {
            return Order.partialCompare$mcS$sp$(this, x, y);
         }

         public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.partialCompare$mcV$sp$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Order.min$(this, x, y);
         }

         public boolean min$mcZ$sp(final boolean x, final boolean y) {
            return Order.min$mcZ$sp$(this, x, y);
         }

         public byte min$mcB$sp(final byte x, final byte y) {
            return Order.min$mcB$sp$(this, x, y);
         }

         public char min$mcC$sp(final char x, final char y) {
            return Order.min$mcC$sp$(this, x, y);
         }

         public double min$mcD$sp(final double x, final double y) {
            return Order.min$mcD$sp$(this, x, y);
         }

         public float min$mcF$sp(final float x, final float y) {
            return Order.min$mcF$sp$(this, x, y);
         }

         public int min$mcI$sp(final int x, final int y) {
            return Order.min$mcI$sp$(this, x, y);
         }

         public long min$mcJ$sp(final long x, final long y) {
            return Order.min$mcJ$sp$(this, x, y);
         }

         public short min$mcS$sp(final short x, final short y) {
            return Order.min$mcS$sp$(this, x, y);
         }

         public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.min$mcV$sp$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Order.max$(this, x, y);
         }

         public boolean max$mcZ$sp(final boolean x, final boolean y) {
            return Order.max$mcZ$sp$(this, x, y);
         }

         public byte max$mcB$sp(final byte x, final byte y) {
            return Order.max$mcB$sp$(this, x, y);
         }

         public char max$mcC$sp(final char x, final char y) {
            return Order.max$mcC$sp$(this, x, y);
         }

         public double max$mcD$sp(final double x, final double y) {
            return Order.max$mcD$sp$(this, x, y);
         }

         public float max$mcF$sp(final float x, final float y) {
            return Order.max$mcF$sp$(this, x, y);
         }

         public int max$mcI$sp(final int x, final int y) {
            return Order.max$mcI$sp$(this, x, y);
         }

         public long max$mcJ$sp(final long x, final long y) {
            return Order.max$mcJ$sp$(this, x, y);
         }

         public short max$mcS$sp(final short x, final short y) {
            return Order.max$mcS$sp$(this, x, y);
         }

         public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.max$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return Order.eqv$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Order.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Order.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Order.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Order.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Order.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Order.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Order.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Order.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Order.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Order.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Order.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Order.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Order.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Order.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Order.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.neqv$mcV$sp$(this, x, y);
         }

         public boolean lteqv(final Object x, final Object y) {
            return Order.lteqv$(this, x, y);
         }

         public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.lteqv$mcZ$sp$(this, x, y);
         }

         public boolean lteqv$mcB$sp(final byte x, final byte y) {
            return Order.lteqv$mcB$sp$(this, x, y);
         }

         public boolean lteqv$mcC$sp(final char x, final char y) {
            return Order.lteqv$mcC$sp$(this, x, y);
         }

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return Order.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return Order.lteqv$mcF$sp$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return Order.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv$mcJ$sp(final long x, final long y) {
            return Order.lteqv$mcJ$sp$(this, x, y);
         }

         public boolean lteqv$mcS$sp(final short x, final short y) {
            return Order.lteqv$mcS$sp$(this, x, y);
         }

         public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lteqv$mcV$sp$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Order.lt$(this, x, y);
         }

         public boolean lt$mcZ$sp(final boolean x, final boolean y) {
            return Order.lt$mcZ$sp$(this, x, y);
         }

         public boolean lt$mcB$sp(final byte x, final byte y) {
            return Order.lt$mcB$sp$(this, x, y);
         }

         public boolean lt$mcC$sp(final char x, final char y) {
            return Order.lt$mcC$sp$(this, x, y);
         }

         public boolean lt$mcD$sp(final double x, final double y) {
            return Order.lt$mcD$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return Order.lt$mcF$sp$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return Order.lt$mcI$sp$(this, x, y);
         }

         public boolean lt$mcJ$sp(final long x, final long y) {
            return Order.lt$mcJ$sp$(this, x, y);
         }

         public boolean lt$mcS$sp(final short x, final short y) {
            return Order.lt$mcS$sp$(this, x, y);
         }

         public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lt$mcV$sp$(this, x, y);
         }

         public boolean gteqv(final Object x, final Object y) {
            return Order.gteqv$(this, x, y);
         }

         public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.gteqv$mcZ$sp$(this, x, y);
         }

         public boolean gteqv$mcB$sp(final byte x, final byte y) {
            return Order.gteqv$mcB$sp$(this, x, y);
         }

         public boolean gteqv$mcC$sp(final char x, final char y) {
            return Order.gteqv$mcC$sp$(this, x, y);
         }

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return Order.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return Order.gteqv$mcF$sp$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return Order.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gteqv$mcJ$sp(final long x, final long y) {
            return Order.gteqv$mcJ$sp$(this, x, y);
         }

         public boolean gteqv$mcS$sp(final short x, final short y) {
            return Order.gteqv$mcS$sp$(this, x, y);
         }

         public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gteqv$mcV$sp$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Order.gt$(this, x, y);
         }

         public boolean gt$mcZ$sp(final boolean x, final boolean y) {
            return Order.gt$mcZ$sp$(this, x, y);
         }

         public boolean gt$mcB$sp(final byte x, final byte y) {
            return Order.gt$mcB$sp$(this, x, y);
         }

         public boolean gt$mcC$sp(final char x, final char y) {
            return Order.gt$mcC$sp$(this, x, y);
         }

         public boolean gt$mcD$sp(final double x, final double y) {
            return Order.gt$mcD$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return Order.gt$mcF$sp$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return Order.gt$mcI$sp$(this, x, y);
         }

         public boolean gt$mcJ$sp(final long x, final long y) {
            return Order.gt$mcJ$sp$(this, x, y);
         }

         public boolean gt$mcS$sp(final short x, final short y) {
            return Order.gt$mcS$sp$(this, x, y);
         }

         public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gt$mcV$sp$(this, x, y);
         }

         public Ordering toOrdering() {
            return Order.toOrdering$(this);
         }

         public Option partialComparison(final Object x, final Object y) {
            return PartialOrder.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
         }

         public Option partialComparison$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialComparison$mcB$sp$(this, x, y);
         }

         public Option partialComparison$mcC$sp(final char x, final char y) {
            return PartialOrder.partialComparison$mcC$sp$(this, x, y);
         }

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder.partialComparison$mcD$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder.partialComparison$mcI$sp$(this, x, y);
         }

         public Option partialComparison$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
         }

         public Option partialComparison$mcS$sp(final short x, final short y) {
            return PartialOrder.partialComparison$mcS$sp$(this, x, y);
         }

         public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialComparison$mcV$sp$(this, x, y);
         }

         public Option tryCompare(final Object x, final Object y) {
            return PartialOrder.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
         }

         public Option tryCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.tryCompare$mcB$sp$(this, x, y);
         }

         public Option tryCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.tryCompare$mcC$sp$(this, x, y);
         }

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.tryCompare$mcD$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.tryCompare$mcI$sp$(this, x, y);
         }

         public Option tryCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
         }

         public Option tryCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.tryCompare$mcS$sp$(this, x, y);
         }

         public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.tryCompare$mcV$sp$(this, x, y);
         }

         public Option pmin(final Object x, final Object y) {
            return PartialOrder.pmin$(this, x, y);
         }

         public Option pmin$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmin$mcZ$sp$(this, x, y);
         }

         public Option pmin$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmin$mcB$sp$(this, x, y);
         }

         public Option pmin$mcC$sp(final char x, final char y) {
            return PartialOrder.pmin$mcC$sp$(this, x, y);
         }

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder.pmin$mcD$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder.pmin$mcI$sp$(this, x, y);
         }

         public Option pmin$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmin$mcJ$sp$(this, x, y);
         }

         public Option pmin$mcS$sp(final short x, final short y) {
            return PartialOrder.pmin$mcS$sp$(this, x, y);
         }

         public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmin$mcV$sp$(this, x, y);
         }

         public Option pmax(final Object x, final Object y) {
            return PartialOrder.pmax$(this, x, y);
         }

         public Option pmax$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmax$mcZ$sp$(this, x, y);
         }

         public Option pmax$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmax$mcB$sp$(this, x, y);
         }

         public Option pmax$mcC$sp(final char x, final char y) {
            return PartialOrder.pmax$mcC$sp$(this, x, y);
         }

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder.pmax$mcD$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder.pmax$mcI$sp$(this, x, y);
         }

         public Option pmax$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmax$mcJ$sp$(this, x, y);
         }

         public Option pmax$mcS$sp(final short x, final short y) {
            return PartialOrder.pmax$mcS$sp$(this, x, y);
         }

         public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmax$mcV$sp$(this, x, y);
         }

         public int compare(final Tuple10 x, final Tuple10 y) {
            return BoxesRunTime.unboxToInt(.MODULE$.find$extension(scala.Predef..MODULE$.intArrayOps(new int[]{this.A0$494.compare(x._1(), y._1()), this.A1$471.compare(x._2(), y._2()), this.A2$448.compare(x._3(), y._3()), this.A3$425.compare(x._4(), y._4()), this.A4$402.compare(x._5(), y._5()), this.A5$379.compare(x._6(), y._6()), this.A6$356.compare(x._7(), y._7()), this.A7$333.compare(x._8(), y._8()), this.A8$310.compare(x._9(), y._9()), this.A9$287.compare(x._10(), y._10())}), (JFunction1.mcZI.sp)(x$54) -> x$54 != 0).getOrElse((JFunction0.mcI.sp)() -> 0));
         }

         public {
            this.A0$494 = A0$494;
            this.A1$471 = A1$471;
            this.A2$448 = A2$448;
            this.A3$425 = A3$425;
            this.A4$402 = A4$402;
            this.A5$379 = A5$379;
            this.A6$356 = A6$356;
            this.A7$333 = A7$333;
            this.A8$310 = A8$310;
            this.A9$287 = A9$287;
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static Order catsKernelOrderForTuple11$(final TupleOrderInstances $this, final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10) {
      return $this.catsKernelOrderForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   default Order catsKernelOrderForTuple11(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10) {
      return new Order(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) {
         private final Order A0$495;
         private final Order A1$472;
         private final Order A2$449;
         private final Order A3$426;
         private final Order A4$403;
         private final Order A5$380;
         private final Order A6$357;
         private final Order A7$334;
         private final Order A8$311;
         private final Order A9$288;
         private final Order A10$265;

         public int compare$mcZ$sp(final boolean x, final boolean y) {
            return Order.compare$mcZ$sp$(this, x, y);
         }

         public int compare$mcB$sp(final byte x, final byte y) {
            return Order.compare$mcB$sp$(this, x, y);
         }

         public int compare$mcC$sp(final char x, final char y) {
            return Order.compare$mcC$sp$(this, x, y);
         }

         public int compare$mcD$sp(final double x, final double y) {
            return Order.compare$mcD$sp$(this, x, y);
         }

         public int compare$mcF$sp(final float x, final float y) {
            return Order.compare$mcF$sp$(this, x, y);
         }

         public int compare$mcI$sp(final int x, final int y) {
            return Order.compare$mcI$sp$(this, x, y);
         }

         public int compare$mcJ$sp(final long x, final long y) {
            return Order.compare$mcJ$sp$(this, x, y);
         }

         public int compare$mcS$sp(final short x, final short y) {
            return Order.compare$mcS$sp$(this, x, y);
         }

         public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.compare$mcV$sp$(this, x, y);
         }

         public Comparison comparison(final Object x, final Object y) {
            return Order.comparison$(this, x, y);
         }

         public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
            return Order.comparison$mcZ$sp$(this, x, y);
         }

         public Comparison comparison$mcB$sp(final byte x, final byte y) {
            return Order.comparison$mcB$sp$(this, x, y);
         }

         public Comparison comparison$mcC$sp(final char x, final char y) {
            return Order.comparison$mcC$sp$(this, x, y);
         }

         public Comparison comparison$mcD$sp(final double x, final double y) {
            return Order.comparison$mcD$sp$(this, x, y);
         }

         public Comparison comparison$mcF$sp(final float x, final float y) {
            return Order.comparison$mcF$sp$(this, x, y);
         }

         public Comparison comparison$mcI$sp(final int x, final int y) {
            return Order.comparison$mcI$sp$(this, x, y);
         }

         public Comparison comparison$mcJ$sp(final long x, final long y) {
            return Order.comparison$mcJ$sp$(this, x, y);
         }

         public Comparison comparison$mcS$sp(final short x, final short y) {
            return Order.comparison$mcS$sp$(this, x, y);
         }

         public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.comparison$mcV$sp$(this, x, y);
         }

         public double partialCompare(final Object x, final Object y) {
            return Order.partialCompare$(this, x, y);
         }

         public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
            return Order.partialCompare$mcZ$sp$(this, x, y);
         }

         public double partialCompare$mcB$sp(final byte x, final byte y) {
            return Order.partialCompare$mcB$sp$(this, x, y);
         }

         public double partialCompare$mcC$sp(final char x, final char y) {
            return Order.partialCompare$mcC$sp$(this, x, y);
         }

         public double partialCompare$mcD$sp(final double x, final double y) {
            return Order.partialCompare$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return Order.partialCompare$mcF$sp$(this, x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            return Order.partialCompare$mcI$sp$(this, x, y);
         }

         public double partialCompare$mcJ$sp(final long x, final long y) {
            return Order.partialCompare$mcJ$sp$(this, x, y);
         }

         public double partialCompare$mcS$sp(final short x, final short y) {
            return Order.partialCompare$mcS$sp$(this, x, y);
         }

         public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.partialCompare$mcV$sp$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Order.min$(this, x, y);
         }

         public boolean min$mcZ$sp(final boolean x, final boolean y) {
            return Order.min$mcZ$sp$(this, x, y);
         }

         public byte min$mcB$sp(final byte x, final byte y) {
            return Order.min$mcB$sp$(this, x, y);
         }

         public char min$mcC$sp(final char x, final char y) {
            return Order.min$mcC$sp$(this, x, y);
         }

         public double min$mcD$sp(final double x, final double y) {
            return Order.min$mcD$sp$(this, x, y);
         }

         public float min$mcF$sp(final float x, final float y) {
            return Order.min$mcF$sp$(this, x, y);
         }

         public int min$mcI$sp(final int x, final int y) {
            return Order.min$mcI$sp$(this, x, y);
         }

         public long min$mcJ$sp(final long x, final long y) {
            return Order.min$mcJ$sp$(this, x, y);
         }

         public short min$mcS$sp(final short x, final short y) {
            return Order.min$mcS$sp$(this, x, y);
         }

         public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.min$mcV$sp$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Order.max$(this, x, y);
         }

         public boolean max$mcZ$sp(final boolean x, final boolean y) {
            return Order.max$mcZ$sp$(this, x, y);
         }

         public byte max$mcB$sp(final byte x, final byte y) {
            return Order.max$mcB$sp$(this, x, y);
         }

         public char max$mcC$sp(final char x, final char y) {
            return Order.max$mcC$sp$(this, x, y);
         }

         public double max$mcD$sp(final double x, final double y) {
            return Order.max$mcD$sp$(this, x, y);
         }

         public float max$mcF$sp(final float x, final float y) {
            return Order.max$mcF$sp$(this, x, y);
         }

         public int max$mcI$sp(final int x, final int y) {
            return Order.max$mcI$sp$(this, x, y);
         }

         public long max$mcJ$sp(final long x, final long y) {
            return Order.max$mcJ$sp$(this, x, y);
         }

         public short max$mcS$sp(final short x, final short y) {
            return Order.max$mcS$sp$(this, x, y);
         }

         public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.max$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return Order.eqv$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Order.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Order.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Order.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Order.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Order.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Order.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Order.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Order.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Order.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Order.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Order.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Order.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Order.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Order.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Order.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.neqv$mcV$sp$(this, x, y);
         }

         public boolean lteqv(final Object x, final Object y) {
            return Order.lteqv$(this, x, y);
         }

         public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.lteqv$mcZ$sp$(this, x, y);
         }

         public boolean lteqv$mcB$sp(final byte x, final byte y) {
            return Order.lteqv$mcB$sp$(this, x, y);
         }

         public boolean lteqv$mcC$sp(final char x, final char y) {
            return Order.lteqv$mcC$sp$(this, x, y);
         }

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return Order.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return Order.lteqv$mcF$sp$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return Order.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv$mcJ$sp(final long x, final long y) {
            return Order.lteqv$mcJ$sp$(this, x, y);
         }

         public boolean lteqv$mcS$sp(final short x, final short y) {
            return Order.lteqv$mcS$sp$(this, x, y);
         }

         public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lteqv$mcV$sp$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Order.lt$(this, x, y);
         }

         public boolean lt$mcZ$sp(final boolean x, final boolean y) {
            return Order.lt$mcZ$sp$(this, x, y);
         }

         public boolean lt$mcB$sp(final byte x, final byte y) {
            return Order.lt$mcB$sp$(this, x, y);
         }

         public boolean lt$mcC$sp(final char x, final char y) {
            return Order.lt$mcC$sp$(this, x, y);
         }

         public boolean lt$mcD$sp(final double x, final double y) {
            return Order.lt$mcD$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return Order.lt$mcF$sp$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return Order.lt$mcI$sp$(this, x, y);
         }

         public boolean lt$mcJ$sp(final long x, final long y) {
            return Order.lt$mcJ$sp$(this, x, y);
         }

         public boolean lt$mcS$sp(final short x, final short y) {
            return Order.lt$mcS$sp$(this, x, y);
         }

         public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lt$mcV$sp$(this, x, y);
         }

         public boolean gteqv(final Object x, final Object y) {
            return Order.gteqv$(this, x, y);
         }

         public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.gteqv$mcZ$sp$(this, x, y);
         }

         public boolean gteqv$mcB$sp(final byte x, final byte y) {
            return Order.gteqv$mcB$sp$(this, x, y);
         }

         public boolean gteqv$mcC$sp(final char x, final char y) {
            return Order.gteqv$mcC$sp$(this, x, y);
         }

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return Order.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return Order.gteqv$mcF$sp$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return Order.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gteqv$mcJ$sp(final long x, final long y) {
            return Order.gteqv$mcJ$sp$(this, x, y);
         }

         public boolean gteqv$mcS$sp(final short x, final short y) {
            return Order.gteqv$mcS$sp$(this, x, y);
         }

         public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gteqv$mcV$sp$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Order.gt$(this, x, y);
         }

         public boolean gt$mcZ$sp(final boolean x, final boolean y) {
            return Order.gt$mcZ$sp$(this, x, y);
         }

         public boolean gt$mcB$sp(final byte x, final byte y) {
            return Order.gt$mcB$sp$(this, x, y);
         }

         public boolean gt$mcC$sp(final char x, final char y) {
            return Order.gt$mcC$sp$(this, x, y);
         }

         public boolean gt$mcD$sp(final double x, final double y) {
            return Order.gt$mcD$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return Order.gt$mcF$sp$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return Order.gt$mcI$sp$(this, x, y);
         }

         public boolean gt$mcJ$sp(final long x, final long y) {
            return Order.gt$mcJ$sp$(this, x, y);
         }

         public boolean gt$mcS$sp(final short x, final short y) {
            return Order.gt$mcS$sp$(this, x, y);
         }

         public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gt$mcV$sp$(this, x, y);
         }

         public Ordering toOrdering() {
            return Order.toOrdering$(this);
         }

         public Option partialComparison(final Object x, final Object y) {
            return PartialOrder.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
         }

         public Option partialComparison$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialComparison$mcB$sp$(this, x, y);
         }

         public Option partialComparison$mcC$sp(final char x, final char y) {
            return PartialOrder.partialComparison$mcC$sp$(this, x, y);
         }

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder.partialComparison$mcD$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder.partialComparison$mcI$sp$(this, x, y);
         }

         public Option partialComparison$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
         }

         public Option partialComparison$mcS$sp(final short x, final short y) {
            return PartialOrder.partialComparison$mcS$sp$(this, x, y);
         }

         public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialComparison$mcV$sp$(this, x, y);
         }

         public Option tryCompare(final Object x, final Object y) {
            return PartialOrder.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
         }

         public Option tryCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.tryCompare$mcB$sp$(this, x, y);
         }

         public Option tryCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.tryCompare$mcC$sp$(this, x, y);
         }

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.tryCompare$mcD$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.tryCompare$mcI$sp$(this, x, y);
         }

         public Option tryCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
         }

         public Option tryCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.tryCompare$mcS$sp$(this, x, y);
         }

         public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.tryCompare$mcV$sp$(this, x, y);
         }

         public Option pmin(final Object x, final Object y) {
            return PartialOrder.pmin$(this, x, y);
         }

         public Option pmin$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmin$mcZ$sp$(this, x, y);
         }

         public Option pmin$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmin$mcB$sp$(this, x, y);
         }

         public Option pmin$mcC$sp(final char x, final char y) {
            return PartialOrder.pmin$mcC$sp$(this, x, y);
         }

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder.pmin$mcD$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder.pmin$mcI$sp$(this, x, y);
         }

         public Option pmin$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmin$mcJ$sp$(this, x, y);
         }

         public Option pmin$mcS$sp(final short x, final short y) {
            return PartialOrder.pmin$mcS$sp$(this, x, y);
         }

         public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmin$mcV$sp$(this, x, y);
         }

         public Option pmax(final Object x, final Object y) {
            return PartialOrder.pmax$(this, x, y);
         }

         public Option pmax$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmax$mcZ$sp$(this, x, y);
         }

         public Option pmax$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmax$mcB$sp$(this, x, y);
         }

         public Option pmax$mcC$sp(final char x, final char y) {
            return PartialOrder.pmax$mcC$sp$(this, x, y);
         }

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder.pmax$mcD$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder.pmax$mcI$sp$(this, x, y);
         }

         public Option pmax$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmax$mcJ$sp$(this, x, y);
         }

         public Option pmax$mcS$sp(final short x, final short y) {
            return PartialOrder.pmax$mcS$sp$(this, x, y);
         }

         public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmax$mcV$sp$(this, x, y);
         }

         public int compare(final Tuple11 x, final Tuple11 y) {
            return BoxesRunTime.unboxToInt(.MODULE$.find$extension(scala.Predef..MODULE$.intArrayOps(new int[]{this.A0$495.compare(x._1(), y._1()), this.A1$472.compare(x._2(), y._2()), this.A2$449.compare(x._3(), y._3()), this.A3$426.compare(x._4(), y._4()), this.A4$403.compare(x._5(), y._5()), this.A5$380.compare(x._6(), y._6()), this.A6$357.compare(x._7(), y._7()), this.A7$334.compare(x._8(), y._8()), this.A8$311.compare(x._9(), y._9()), this.A9$288.compare(x._10(), y._10()), this.A10$265.compare(x._11(), y._11())}), (JFunction1.mcZI.sp)(x$55) -> x$55 != 0).getOrElse((JFunction0.mcI.sp)() -> 0));
         }

         public {
            this.A0$495 = A0$495;
            this.A1$472 = A1$472;
            this.A2$449 = A2$449;
            this.A3$426 = A3$426;
            this.A4$403 = A4$403;
            this.A5$380 = A5$380;
            this.A6$357 = A6$357;
            this.A7$334 = A7$334;
            this.A8$311 = A8$311;
            this.A9$288 = A9$288;
            this.A10$265 = A10$265;
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static Order catsKernelOrderForTuple12$(final TupleOrderInstances $this, final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11) {
      return $this.catsKernelOrderForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   default Order catsKernelOrderForTuple12(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11) {
      return new Order(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) {
         private final Order A0$496;
         private final Order A1$473;
         private final Order A2$450;
         private final Order A3$427;
         private final Order A4$404;
         private final Order A5$381;
         private final Order A6$358;
         private final Order A7$335;
         private final Order A8$312;
         private final Order A9$289;
         private final Order A10$266;
         private final Order A11$243;

         public int compare$mcZ$sp(final boolean x, final boolean y) {
            return Order.compare$mcZ$sp$(this, x, y);
         }

         public int compare$mcB$sp(final byte x, final byte y) {
            return Order.compare$mcB$sp$(this, x, y);
         }

         public int compare$mcC$sp(final char x, final char y) {
            return Order.compare$mcC$sp$(this, x, y);
         }

         public int compare$mcD$sp(final double x, final double y) {
            return Order.compare$mcD$sp$(this, x, y);
         }

         public int compare$mcF$sp(final float x, final float y) {
            return Order.compare$mcF$sp$(this, x, y);
         }

         public int compare$mcI$sp(final int x, final int y) {
            return Order.compare$mcI$sp$(this, x, y);
         }

         public int compare$mcJ$sp(final long x, final long y) {
            return Order.compare$mcJ$sp$(this, x, y);
         }

         public int compare$mcS$sp(final short x, final short y) {
            return Order.compare$mcS$sp$(this, x, y);
         }

         public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.compare$mcV$sp$(this, x, y);
         }

         public Comparison comparison(final Object x, final Object y) {
            return Order.comparison$(this, x, y);
         }

         public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
            return Order.comparison$mcZ$sp$(this, x, y);
         }

         public Comparison comparison$mcB$sp(final byte x, final byte y) {
            return Order.comparison$mcB$sp$(this, x, y);
         }

         public Comparison comparison$mcC$sp(final char x, final char y) {
            return Order.comparison$mcC$sp$(this, x, y);
         }

         public Comparison comparison$mcD$sp(final double x, final double y) {
            return Order.comparison$mcD$sp$(this, x, y);
         }

         public Comparison comparison$mcF$sp(final float x, final float y) {
            return Order.comparison$mcF$sp$(this, x, y);
         }

         public Comparison comparison$mcI$sp(final int x, final int y) {
            return Order.comparison$mcI$sp$(this, x, y);
         }

         public Comparison comparison$mcJ$sp(final long x, final long y) {
            return Order.comparison$mcJ$sp$(this, x, y);
         }

         public Comparison comparison$mcS$sp(final short x, final short y) {
            return Order.comparison$mcS$sp$(this, x, y);
         }

         public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.comparison$mcV$sp$(this, x, y);
         }

         public double partialCompare(final Object x, final Object y) {
            return Order.partialCompare$(this, x, y);
         }

         public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
            return Order.partialCompare$mcZ$sp$(this, x, y);
         }

         public double partialCompare$mcB$sp(final byte x, final byte y) {
            return Order.partialCompare$mcB$sp$(this, x, y);
         }

         public double partialCompare$mcC$sp(final char x, final char y) {
            return Order.partialCompare$mcC$sp$(this, x, y);
         }

         public double partialCompare$mcD$sp(final double x, final double y) {
            return Order.partialCompare$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return Order.partialCompare$mcF$sp$(this, x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            return Order.partialCompare$mcI$sp$(this, x, y);
         }

         public double partialCompare$mcJ$sp(final long x, final long y) {
            return Order.partialCompare$mcJ$sp$(this, x, y);
         }

         public double partialCompare$mcS$sp(final short x, final short y) {
            return Order.partialCompare$mcS$sp$(this, x, y);
         }

         public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.partialCompare$mcV$sp$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Order.min$(this, x, y);
         }

         public boolean min$mcZ$sp(final boolean x, final boolean y) {
            return Order.min$mcZ$sp$(this, x, y);
         }

         public byte min$mcB$sp(final byte x, final byte y) {
            return Order.min$mcB$sp$(this, x, y);
         }

         public char min$mcC$sp(final char x, final char y) {
            return Order.min$mcC$sp$(this, x, y);
         }

         public double min$mcD$sp(final double x, final double y) {
            return Order.min$mcD$sp$(this, x, y);
         }

         public float min$mcF$sp(final float x, final float y) {
            return Order.min$mcF$sp$(this, x, y);
         }

         public int min$mcI$sp(final int x, final int y) {
            return Order.min$mcI$sp$(this, x, y);
         }

         public long min$mcJ$sp(final long x, final long y) {
            return Order.min$mcJ$sp$(this, x, y);
         }

         public short min$mcS$sp(final short x, final short y) {
            return Order.min$mcS$sp$(this, x, y);
         }

         public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.min$mcV$sp$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Order.max$(this, x, y);
         }

         public boolean max$mcZ$sp(final boolean x, final boolean y) {
            return Order.max$mcZ$sp$(this, x, y);
         }

         public byte max$mcB$sp(final byte x, final byte y) {
            return Order.max$mcB$sp$(this, x, y);
         }

         public char max$mcC$sp(final char x, final char y) {
            return Order.max$mcC$sp$(this, x, y);
         }

         public double max$mcD$sp(final double x, final double y) {
            return Order.max$mcD$sp$(this, x, y);
         }

         public float max$mcF$sp(final float x, final float y) {
            return Order.max$mcF$sp$(this, x, y);
         }

         public int max$mcI$sp(final int x, final int y) {
            return Order.max$mcI$sp$(this, x, y);
         }

         public long max$mcJ$sp(final long x, final long y) {
            return Order.max$mcJ$sp$(this, x, y);
         }

         public short max$mcS$sp(final short x, final short y) {
            return Order.max$mcS$sp$(this, x, y);
         }

         public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.max$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return Order.eqv$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Order.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Order.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Order.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Order.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Order.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Order.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Order.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Order.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Order.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Order.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Order.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Order.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Order.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Order.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Order.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.neqv$mcV$sp$(this, x, y);
         }

         public boolean lteqv(final Object x, final Object y) {
            return Order.lteqv$(this, x, y);
         }

         public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.lteqv$mcZ$sp$(this, x, y);
         }

         public boolean lteqv$mcB$sp(final byte x, final byte y) {
            return Order.lteqv$mcB$sp$(this, x, y);
         }

         public boolean lteqv$mcC$sp(final char x, final char y) {
            return Order.lteqv$mcC$sp$(this, x, y);
         }

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return Order.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return Order.lteqv$mcF$sp$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return Order.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv$mcJ$sp(final long x, final long y) {
            return Order.lteqv$mcJ$sp$(this, x, y);
         }

         public boolean lteqv$mcS$sp(final short x, final short y) {
            return Order.lteqv$mcS$sp$(this, x, y);
         }

         public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lteqv$mcV$sp$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Order.lt$(this, x, y);
         }

         public boolean lt$mcZ$sp(final boolean x, final boolean y) {
            return Order.lt$mcZ$sp$(this, x, y);
         }

         public boolean lt$mcB$sp(final byte x, final byte y) {
            return Order.lt$mcB$sp$(this, x, y);
         }

         public boolean lt$mcC$sp(final char x, final char y) {
            return Order.lt$mcC$sp$(this, x, y);
         }

         public boolean lt$mcD$sp(final double x, final double y) {
            return Order.lt$mcD$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return Order.lt$mcF$sp$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return Order.lt$mcI$sp$(this, x, y);
         }

         public boolean lt$mcJ$sp(final long x, final long y) {
            return Order.lt$mcJ$sp$(this, x, y);
         }

         public boolean lt$mcS$sp(final short x, final short y) {
            return Order.lt$mcS$sp$(this, x, y);
         }

         public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lt$mcV$sp$(this, x, y);
         }

         public boolean gteqv(final Object x, final Object y) {
            return Order.gteqv$(this, x, y);
         }

         public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.gteqv$mcZ$sp$(this, x, y);
         }

         public boolean gteqv$mcB$sp(final byte x, final byte y) {
            return Order.gteqv$mcB$sp$(this, x, y);
         }

         public boolean gteqv$mcC$sp(final char x, final char y) {
            return Order.gteqv$mcC$sp$(this, x, y);
         }

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return Order.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return Order.gteqv$mcF$sp$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return Order.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gteqv$mcJ$sp(final long x, final long y) {
            return Order.gteqv$mcJ$sp$(this, x, y);
         }

         public boolean gteqv$mcS$sp(final short x, final short y) {
            return Order.gteqv$mcS$sp$(this, x, y);
         }

         public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gteqv$mcV$sp$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Order.gt$(this, x, y);
         }

         public boolean gt$mcZ$sp(final boolean x, final boolean y) {
            return Order.gt$mcZ$sp$(this, x, y);
         }

         public boolean gt$mcB$sp(final byte x, final byte y) {
            return Order.gt$mcB$sp$(this, x, y);
         }

         public boolean gt$mcC$sp(final char x, final char y) {
            return Order.gt$mcC$sp$(this, x, y);
         }

         public boolean gt$mcD$sp(final double x, final double y) {
            return Order.gt$mcD$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return Order.gt$mcF$sp$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return Order.gt$mcI$sp$(this, x, y);
         }

         public boolean gt$mcJ$sp(final long x, final long y) {
            return Order.gt$mcJ$sp$(this, x, y);
         }

         public boolean gt$mcS$sp(final short x, final short y) {
            return Order.gt$mcS$sp$(this, x, y);
         }

         public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gt$mcV$sp$(this, x, y);
         }

         public Ordering toOrdering() {
            return Order.toOrdering$(this);
         }

         public Option partialComparison(final Object x, final Object y) {
            return PartialOrder.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
         }

         public Option partialComparison$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialComparison$mcB$sp$(this, x, y);
         }

         public Option partialComparison$mcC$sp(final char x, final char y) {
            return PartialOrder.partialComparison$mcC$sp$(this, x, y);
         }

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder.partialComparison$mcD$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder.partialComparison$mcI$sp$(this, x, y);
         }

         public Option partialComparison$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
         }

         public Option partialComparison$mcS$sp(final short x, final short y) {
            return PartialOrder.partialComparison$mcS$sp$(this, x, y);
         }

         public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialComparison$mcV$sp$(this, x, y);
         }

         public Option tryCompare(final Object x, final Object y) {
            return PartialOrder.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
         }

         public Option tryCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.tryCompare$mcB$sp$(this, x, y);
         }

         public Option tryCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.tryCompare$mcC$sp$(this, x, y);
         }

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.tryCompare$mcD$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.tryCompare$mcI$sp$(this, x, y);
         }

         public Option tryCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
         }

         public Option tryCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.tryCompare$mcS$sp$(this, x, y);
         }

         public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.tryCompare$mcV$sp$(this, x, y);
         }

         public Option pmin(final Object x, final Object y) {
            return PartialOrder.pmin$(this, x, y);
         }

         public Option pmin$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmin$mcZ$sp$(this, x, y);
         }

         public Option pmin$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmin$mcB$sp$(this, x, y);
         }

         public Option pmin$mcC$sp(final char x, final char y) {
            return PartialOrder.pmin$mcC$sp$(this, x, y);
         }

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder.pmin$mcD$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder.pmin$mcI$sp$(this, x, y);
         }

         public Option pmin$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmin$mcJ$sp$(this, x, y);
         }

         public Option pmin$mcS$sp(final short x, final short y) {
            return PartialOrder.pmin$mcS$sp$(this, x, y);
         }

         public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmin$mcV$sp$(this, x, y);
         }

         public Option pmax(final Object x, final Object y) {
            return PartialOrder.pmax$(this, x, y);
         }

         public Option pmax$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmax$mcZ$sp$(this, x, y);
         }

         public Option pmax$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmax$mcB$sp$(this, x, y);
         }

         public Option pmax$mcC$sp(final char x, final char y) {
            return PartialOrder.pmax$mcC$sp$(this, x, y);
         }

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder.pmax$mcD$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder.pmax$mcI$sp$(this, x, y);
         }

         public Option pmax$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmax$mcJ$sp$(this, x, y);
         }

         public Option pmax$mcS$sp(final short x, final short y) {
            return PartialOrder.pmax$mcS$sp$(this, x, y);
         }

         public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmax$mcV$sp$(this, x, y);
         }

         public int compare(final Tuple12 x, final Tuple12 y) {
            return BoxesRunTime.unboxToInt(.MODULE$.find$extension(scala.Predef..MODULE$.intArrayOps(new int[]{this.A0$496.compare(x._1(), y._1()), this.A1$473.compare(x._2(), y._2()), this.A2$450.compare(x._3(), y._3()), this.A3$427.compare(x._4(), y._4()), this.A4$404.compare(x._5(), y._5()), this.A5$381.compare(x._6(), y._6()), this.A6$358.compare(x._7(), y._7()), this.A7$335.compare(x._8(), y._8()), this.A8$312.compare(x._9(), y._9()), this.A9$289.compare(x._10(), y._10()), this.A10$266.compare(x._11(), y._11()), this.A11$243.compare(x._12(), y._12())}), (JFunction1.mcZI.sp)(x$56) -> x$56 != 0).getOrElse((JFunction0.mcI.sp)() -> 0));
         }

         public {
            this.A0$496 = A0$496;
            this.A1$473 = A1$473;
            this.A2$450 = A2$450;
            this.A3$427 = A3$427;
            this.A4$404 = A4$404;
            this.A5$381 = A5$381;
            this.A6$358 = A6$358;
            this.A7$335 = A7$335;
            this.A8$312 = A8$312;
            this.A9$289 = A9$289;
            this.A10$266 = A10$266;
            this.A11$243 = A11$243;
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static Order catsKernelOrderForTuple13$(final TupleOrderInstances $this, final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12) {
      return $this.catsKernelOrderForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   default Order catsKernelOrderForTuple13(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12) {
      return new Order(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) {
         private final Order A0$497;
         private final Order A1$474;
         private final Order A2$451;
         private final Order A3$428;
         private final Order A4$405;
         private final Order A5$382;
         private final Order A6$359;
         private final Order A7$336;
         private final Order A8$313;
         private final Order A9$290;
         private final Order A10$267;
         private final Order A11$244;
         private final Order A12$221;

         public int compare$mcZ$sp(final boolean x, final boolean y) {
            return Order.compare$mcZ$sp$(this, x, y);
         }

         public int compare$mcB$sp(final byte x, final byte y) {
            return Order.compare$mcB$sp$(this, x, y);
         }

         public int compare$mcC$sp(final char x, final char y) {
            return Order.compare$mcC$sp$(this, x, y);
         }

         public int compare$mcD$sp(final double x, final double y) {
            return Order.compare$mcD$sp$(this, x, y);
         }

         public int compare$mcF$sp(final float x, final float y) {
            return Order.compare$mcF$sp$(this, x, y);
         }

         public int compare$mcI$sp(final int x, final int y) {
            return Order.compare$mcI$sp$(this, x, y);
         }

         public int compare$mcJ$sp(final long x, final long y) {
            return Order.compare$mcJ$sp$(this, x, y);
         }

         public int compare$mcS$sp(final short x, final short y) {
            return Order.compare$mcS$sp$(this, x, y);
         }

         public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.compare$mcV$sp$(this, x, y);
         }

         public Comparison comparison(final Object x, final Object y) {
            return Order.comparison$(this, x, y);
         }

         public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
            return Order.comparison$mcZ$sp$(this, x, y);
         }

         public Comparison comparison$mcB$sp(final byte x, final byte y) {
            return Order.comparison$mcB$sp$(this, x, y);
         }

         public Comparison comparison$mcC$sp(final char x, final char y) {
            return Order.comparison$mcC$sp$(this, x, y);
         }

         public Comparison comparison$mcD$sp(final double x, final double y) {
            return Order.comparison$mcD$sp$(this, x, y);
         }

         public Comparison comparison$mcF$sp(final float x, final float y) {
            return Order.comparison$mcF$sp$(this, x, y);
         }

         public Comparison comparison$mcI$sp(final int x, final int y) {
            return Order.comparison$mcI$sp$(this, x, y);
         }

         public Comparison comparison$mcJ$sp(final long x, final long y) {
            return Order.comparison$mcJ$sp$(this, x, y);
         }

         public Comparison comparison$mcS$sp(final short x, final short y) {
            return Order.comparison$mcS$sp$(this, x, y);
         }

         public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.comparison$mcV$sp$(this, x, y);
         }

         public double partialCompare(final Object x, final Object y) {
            return Order.partialCompare$(this, x, y);
         }

         public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
            return Order.partialCompare$mcZ$sp$(this, x, y);
         }

         public double partialCompare$mcB$sp(final byte x, final byte y) {
            return Order.partialCompare$mcB$sp$(this, x, y);
         }

         public double partialCompare$mcC$sp(final char x, final char y) {
            return Order.partialCompare$mcC$sp$(this, x, y);
         }

         public double partialCompare$mcD$sp(final double x, final double y) {
            return Order.partialCompare$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return Order.partialCompare$mcF$sp$(this, x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            return Order.partialCompare$mcI$sp$(this, x, y);
         }

         public double partialCompare$mcJ$sp(final long x, final long y) {
            return Order.partialCompare$mcJ$sp$(this, x, y);
         }

         public double partialCompare$mcS$sp(final short x, final short y) {
            return Order.partialCompare$mcS$sp$(this, x, y);
         }

         public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.partialCompare$mcV$sp$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Order.min$(this, x, y);
         }

         public boolean min$mcZ$sp(final boolean x, final boolean y) {
            return Order.min$mcZ$sp$(this, x, y);
         }

         public byte min$mcB$sp(final byte x, final byte y) {
            return Order.min$mcB$sp$(this, x, y);
         }

         public char min$mcC$sp(final char x, final char y) {
            return Order.min$mcC$sp$(this, x, y);
         }

         public double min$mcD$sp(final double x, final double y) {
            return Order.min$mcD$sp$(this, x, y);
         }

         public float min$mcF$sp(final float x, final float y) {
            return Order.min$mcF$sp$(this, x, y);
         }

         public int min$mcI$sp(final int x, final int y) {
            return Order.min$mcI$sp$(this, x, y);
         }

         public long min$mcJ$sp(final long x, final long y) {
            return Order.min$mcJ$sp$(this, x, y);
         }

         public short min$mcS$sp(final short x, final short y) {
            return Order.min$mcS$sp$(this, x, y);
         }

         public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.min$mcV$sp$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Order.max$(this, x, y);
         }

         public boolean max$mcZ$sp(final boolean x, final boolean y) {
            return Order.max$mcZ$sp$(this, x, y);
         }

         public byte max$mcB$sp(final byte x, final byte y) {
            return Order.max$mcB$sp$(this, x, y);
         }

         public char max$mcC$sp(final char x, final char y) {
            return Order.max$mcC$sp$(this, x, y);
         }

         public double max$mcD$sp(final double x, final double y) {
            return Order.max$mcD$sp$(this, x, y);
         }

         public float max$mcF$sp(final float x, final float y) {
            return Order.max$mcF$sp$(this, x, y);
         }

         public int max$mcI$sp(final int x, final int y) {
            return Order.max$mcI$sp$(this, x, y);
         }

         public long max$mcJ$sp(final long x, final long y) {
            return Order.max$mcJ$sp$(this, x, y);
         }

         public short max$mcS$sp(final short x, final short y) {
            return Order.max$mcS$sp$(this, x, y);
         }

         public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.max$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return Order.eqv$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Order.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Order.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Order.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Order.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Order.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Order.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Order.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Order.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Order.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Order.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Order.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Order.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Order.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Order.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Order.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.neqv$mcV$sp$(this, x, y);
         }

         public boolean lteqv(final Object x, final Object y) {
            return Order.lteqv$(this, x, y);
         }

         public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.lteqv$mcZ$sp$(this, x, y);
         }

         public boolean lteqv$mcB$sp(final byte x, final byte y) {
            return Order.lteqv$mcB$sp$(this, x, y);
         }

         public boolean lteqv$mcC$sp(final char x, final char y) {
            return Order.lteqv$mcC$sp$(this, x, y);
         }

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return Order.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return Order.lteqv$mcF$sp$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return Order.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv$mcJ$sp(final long x, final long y) {
            return Order.lteqv$mcJ$sp$(this, x, y);
         }

         public boolean lteqv$mcS$sp(final short x, final short y) {
            return Order.lteqv$mcS$sp$(this, x, y);
         }

         public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lteqv$mcV$sp$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Order.lt$(this, x, y);
         }

         public boolean lt$mcZ$sp(final boolean x, final boolean y) {
            return Order.lt$mcZ$sp$(this, x, y);
         }

         public boolean lt$mcB$sp(final byte x, final byte y) {
            return Order.lt$mcB$sp$(this, x, y);
         }

         public boolean lt$mcC$sp(final char x, final char y) {
            return Order.lt$mcC$sp$(this, x, y);
         }

         public boolean lt$mcD$sp(final double x, final double y) {
            return Order.lt$mcD$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return Order.lt$mcF$sp$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return Order.lt$mcI$sp$(this, x, y);
         }

         public boolean lt$mcJ$sp(final long x, final long y) {
            return Order.lt$mcJ$sp$(this, x, y);
         }

         public boolean lt$mcS$sp(final short x, final short y) {
            return Order.lt$mcS$sp$(this, x, y);
         }

         public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lt$mcV$sp$(this, x, y);
         }

         public boolean gteqv(final Object x, final Object y) {
            return Order.gteqv$(this, x, y);
         }

         public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.gteqv$mcZ$sp$(this, x, y);
         }

         public boolean gteqv$mcB$sp(final byte x, final byte y) {
            return Order.gteqv$mcB$sp$(this, x, y);
         }

         public boolean gteqv$mcC$sp(final char x, final char y) {
            return Order.gteqv$mcC$sp$(this, x, y);
         }

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return Order.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return Order.gteqv$mcF$sp$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return Order.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gteqv$mcJ$sp(final long x, final long y) {
            return Order.gteqv$mcJ$sp$(this, x, y);
         }

         public boolean gteqv$mcS$sp(final short x, final short y) {
            return Order.gteqv$mcS$sp$(this, x, y);
         }

         public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gteqv$mcV$sp$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Order.gt$(this, x, y);
         }

         public boolean gt$mcZ$sp(final boolean x, final boolean y) {
            return Order.gt$mcZ$sp$(this, x, y);
         }

         public boolean gt$mcB$sp(final byte x, final byte y) {
            return Order.gt$mcB$sp$(this, x, y);
         }

         public boolean gt$mcC$sp(final char x, final char y) {
            return Order.gt$mcC$sp$(this, x, y);
         }

         public boolean gt$mcD$sp(final double x, final double y) {
            return Order.gt$mcD$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return Order.gt$mcF$sp$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return Order.gt$mcI$sp$(this, x, y);
         }

         public boolean gt$mcJ$sp(final long x, final long y) {
            return Order.gt$mcJ$sp$(this, x, y);
         }

         public boolean gt$mcS$sp(final short x, final short y) {
            return Order.gt$mcS$sp$(this, x, y);
         }

         public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gt$mcV$sp$(this, x, y);
         }

         public Ordering toOrdering() {
            return Order.toOrdering$(this);
         }

         public Option partialComparison(final Object x, final Object y) {
            return PartialOrder.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
         }

         public Option partialComparison$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialComparison$mcB$sp$(this, x, y);
         }

         public Option partialComparison$mcC$sp(final char x, final char y) {
            return PartialOrder.partialComparison$mcC$sp$(this, x, y);
         }

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder.partialComparison$mcD$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder.partialComparison$mcI$sp$(this, x, y);
         }

         public Option partialComparison$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
         }

         public Option partialComparison$mcS$sp(final short x, final short y) {
            return PartialOrder.partialComparison$mcS$sp$(this, x, y);
         }

         public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialComparison$mcV$sp$(this, x, y);
         }

         public Option tryCompare(final Object x, final Object y) {
            return PartialOrder.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
         }

         public Option tryCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.tryCompare$mcB$sp$(this, x, y);
         }

         public Option tryCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.tryCompare$mcC$sp$(this, x, y);
         }

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.tryCompare$mcD$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.tryCompare$mcI$sp$(this, x, y);
         }

         public Option tryCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
         }

         public Option tryCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.tryCompare$mcS$sp$(this, x, y);
         }

         public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.tryCompare$mcV$sp$(this, x, y);
         }

         public Option pmin(final Object x, final Object y) {
            return PartialOrder.pmin$(this, x, y);
         }

         public Option pmin$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmin$mcZ$sp$(this, x, y);
         }

         public Option pmin$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmin$mcB$sp$(this, x, y);
         }

         public Option pmin$mcC$sp(final char x, final char y) {
            return PartialOrder.pmin$mcC$sp$(this, x, y);
         }

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder.pmin$mcD$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder.pmin$mcI$sp$(this, x, y);
         }

         public Option pmin$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmin$mcJ$sp$(this, x, y);
         }

         public Option pmin$mcS$sp(final short x, final short y) {
            return PartialOrder.pmin$mcS$sp$(this, x, y);
         }

         public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmin$mcV$sp$(this, x, y);
         }

         public Option pmax(final Object x, final Object y) {
            return PartialOrder.pmax$(this, x, y);
         }

         public Option pmax$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmax$mcZ$sp$(this, x, y);
         }

         public Option pmax$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmax$mcB$sp$(this, x, y);
         }

         public Option pmax$mcC$sp(final char x, final char y) {
            return PartialOrder.pmax$mcC$sp$(this, x, y);
         }

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder.pmax$mcD$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder.pmax$mcI$sp$(this, x, y);
         }

         public Option pmax$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmax$mcJ$sp$(this, x, y);
         }

         public Option pmax$mcS$sp(final short x, final short y) {
            return PartialOrder.pmax$mcS$sp$(this, x, y);
         }

         public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmax$mcV$sp$(this, x, y);
         }

         public int compare(final Tuple13 x, final Tuple13 y) {
            return BoxesRunTime.unboxToInt(.MODULE$.find$extension(scala.Predef..MODULE$.intArrayOps(new int[]{this.A0$497.compare(x._1(), y._1()), this.A1$474.compare(x._2(), y._2()), this.A2$451.compare(x._3(), y._3()), this.A3$428.compare(x._4(), y._4()), this.A4$405.compare(x._5(), y._5()), this.A5$382.compare(x._6(), y._6()), this.A6$359.compare(x._7(), y._7()), this.A7$336.compare(x._8(), y._8()), this.A8$313.compare(x._9(), y._9()), this.A9$290.compare(x._10(), y._10()), this.A10$267.compare(x._11(), y._11()), this.A11$244.compare(x._12(), y._12()), this.A12$221.compare(x._13(), y._13())}), (JFunction1.mcZI.sp)(x$57) -> x$57 != 0).getOrElse((JFunction0.mcI.sp)() -> 0));
         }

         public {
            this.A0$497 = A0$497;
            this.A1$474 = A1$474;
            this.A2$451 = A2$451;
            this.A3$428 = A3$428;
            this.A4$405 = A4$405;
            this.A5$382 = A5$382;
            this.A6$359 = A6$359;
            this.A7$336 = A7$336;
            this.A8$313 = A8$313;
            this.A9$290 = A9$290;
            this.A10$267 = A10$267;
            this.A11$244 = A11$244;
            this.A12$221 = A12$221;
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static Order catsKernelOrderForTuple14$(final TupleOrderInstances $this, final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13) {
      return $this.catsKernelOrderForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   default Order catsKernelOrderForTuple14(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13) {
      return new Order(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) {
         private final Order A0$498;
         private final Order A1$475;
         private final Order A2$452;
         private final Order A3$429;
         private final Order A4$406;
         private final Order A5$383;
         private final Order A6$360;
         private final Order A7$337;
         private final Order A8$314;
         private final Order A9$291;
         private final Order A10$268;
         private final Order A11$245;
         private final Order A12$222;
         private final Order A13$199;

         public int compare$mcZ$sp(final boolean x, final boolean y) {
            return Order.compare$mcZ$sp$(this, x, y);
         }

         public int compare$mcB$sp(final byte x, final byte y) {
            return Order.compare$mcB$sp$(this, x, y);
         }

         public int compare$mcC$sp(final char x, final char y) {
            return Order.compare$mcC$sp$(this, x, y);
         }

         public int compare$mcD$sp(final double x, final double y) {
            return Order.compare$mcD$sp$(this, x, y);
         }

         public int compare$mcF$sp(final float x, final float y) {
            return Order.compare$mcF$sp$(this, x, y);
         }

         public int compare$mcI$sp(final int x, final int y) {
            return Order.compare$mcI$sp$(this, x, y);
         }

         public int compare$mcJ$sp(final long x, final long y) {
            return Order.compare$mcJ$sp$(this, x, y);
         }

         public int compare$mcS$sp(final short x, final short y) {
            return Order.compare$mcS$sp$(this, x, y);
         }

         public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.compare$mcV$sp$(this, x, y);
         }

         public Comparison comparison(final Object x, final Object y) {
            return Order.comparison$(this, x, y);
         }

         public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
            return Order.comparison$mcZ$sp$(this, x, y);
         }

         public Comparison comparison$mcB$sp(final byte x, final byte y) {
            return Order.comparison$mcB$sp$(this, x, y);
         }

         public Comparison comparison$mcC$sp(final char x, final char y) {
            return Order.comparison$mcC$sp$(this, x, y);
         }

         public Comparison comparison$mcD$sp(final double x, final double y) {
            return Order.comparison$mcD$sp$(this, x, y);
         }

         public Comparison comparison$mcF$sp(final float x, final float y) {
            return Order.comparison$mcF$sp$(this, x, y);
         }

         public Comparison comparison$mcI$sp(final int x, final int y) {
            return Order.comparison$mcI$sp$(this, x, y);
         }

         public Comparison comparison$mcJ$sp(final long x, final long y) {
            return Order.comparison$mcJ$sp$(this, x, y);
         }

         public Comparison comparison$mcS$sp(final short x, final short y) {
            return Order.comparison$mcS$sp$(this, x, y);
         }

         public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.comparison$mcV$sp$(this, x, y);
         }

         public double partialCompare(final Object x, final Object y) {
            return Order.partialCompare$(this, x, y);
         }

         public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
            return Order.partialCompare$mcZ$sp$(this, x, y);
         }

         public double partialCompare$mcB$sp(final byte x, final byte y) {
            return Order.partialCompare$mcB$sp$(this, x, y);
         }

         public double partialCompare$mcC$sp(final char x, final char y) {
            return Order.partialCompare$mcC$sp$(this, x, y);
         }

         public double partialCompare$mcD$sp(final double x, final double y) {
            return Order.partialCompare$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return Order.partialCompare$mcF$sp$(this, x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            return Order.partialCompare$mcI$sp$(this, x, y);
         }

         public double partialCompare$mcJ$sp(final long x, final long y) {
            return Order.partialCompare$mcJ$sp$(this, x, y);
         }

         public double partialCompare$mcS$sp(final short x, final short y) {
            return Order.partialCompare$mcS$sp$(this, x, y);
         }

         public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.partialCompare$mcV$sp$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Order.min$(this, x, y);
         }

         public boolean min$mcZ$sp(final boolean x, final boolean y) {
            return Order.min$mcZ$sp$(this, x, y);
         }

         public byte min$mcB$sp(final byte x, final byte y) {
            return Order.min$mcB$sp$(this, x, y);
         }

         public char min$mcC$sp(final char x, final char y) {
            return Order.min$mcC$sp$(this, x, y);
         }

         public double min$mcD$sp(final double x, final double y) {
            return Order.min$mcD$sp$(this, x, y);
         }

         public float min$mcF$sp(final float x, final float y) {
            return Order.min$mcF$sp$(this, x, y);
         }

         public int min$mcI$sp(final int x, final int y) {
            return Order.min$mcI$sp$(this, x, y);
         }

         public long min$mcJ$sp(final long x, final long y) {
            return Order.min$mcJ$sp$(this, x, y);
         }

         public short min$mcS$sp(final short x, final short y) {
            return Order.min$mcS$sp$(this, x, y);
         }

         public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.min$mcV$sp$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Order.max$(this, x, y);
         }

         public boolean max$mcZ$sp(final boolean x, final boolean y) {
            return Order.max$mcZ$sp$(this, x, y);
         }

         public byte max$mcB$sp(final byte x, final byte y) {
            return Order.max$mcB$sp$(this, x, y);
         }

         public char max$mcC$sp(final char x, final char y) {
            return Order.max$mcC$sp$(this, x, y);
         }

         public double max$mcD$sp(final double x, final double y) {
            return Order.max$mcD$sp$(this, x, y);
         }

         public float max$mcF$sp(final float x, final float y) {
            return Order.max$mcF$sp$(this, x, y);
         }

         public int max$mcI$sp(final int x, final int y) {
            return Order.max$mcI$sp$(this, x, y);
         }

         public long max$mcJ$sp(final long x, final long y) {
            return Order.max$mcJ$sp$(this, x, y);
         }

         public short max$mcS$sp(final short x, final short y) {
            return Order.max$mcS$sp$(this, x, y);
         }

         public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.max$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return Order.eqv$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Order.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Order.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Order.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Order.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Order.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Order.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Order.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Order.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Order.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Order.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Order.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Order.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Order.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Order.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Order.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.neqv$mcV$sp$(this, x, y);
         }

         public boolean lteqv(final Object x, final Object y) {
            return Order.lteqv$(this, x, y);
         }

         public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.lteqv$mcZ$sp$(this, x, y);
         }

         public boolean lteqv$mcB$sp(final byte x, final byte y) {
            return Order.lteqv$mcB$sp$(this, x, y);
         }

         public boolean lteqv$mcC$sp(final char x, final char y) {
            return Order.lteqv$mcC$sp$(this, x, y);
         }

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return Order.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return Order.lteqv$mcF$sp$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return Order.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv$mcJ$sp(final long x, final long y) {
            return Order.lteqv$mcJ$sp$(this, x, y);
         }

         public boolean lteqv$mcS$sp(final short x, final short y) {
            return Order.lteqv$mcS$sp$(this, x, y);
         }

         public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lteqv$mcV$sp$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Order.lt$(this, x, y);
         }

         public boolean lt$mcZ$sp(final boolean x, final boolean y) {
            return Order.lt$mcZ$sp$(this, x, y);
         }

         public boolean lt$mcB$sp(final byte x, final byte y) {
            return Order.lt$mcB$sp$(this, x, y);
         }

         public boolean lt$mcC$sp(final char x, final char y) {
            return Order.lt$mcC$sp$(this, x, y);
         }

         public boolean lt$mcD$sp(final double x, final double y) {
            return Order.lt$mcD$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return Order.lt$mcF$sp$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return Order.lt$mcI$sp$(this, x, y);
         }

         public boolean lt$mcJ$sp(final long x, final long y) {
            return Order.lt$mcJ$sp$(this, x, y);
         }

         public boolean lt$mcS$sp(final short x, final short y) {
            return Order.lt$mcS$sp$(this, x, y);
         }

         public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lt$mcV$sp$(this, x, y);
         }

         public boolean gteqv(final Object x, final Object y) {
            return Order.gteqv$(this, x, y);
         }

         public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.gteqv$mcZ$sp$(this, x, y);
         }

         public boolean gteqv$mcB$sp(final byte x, final byte y) {
            return Order.gteqv$mcB$sp$(this, x, y);
         }

         public boolean gteqv$mcC$sp(final char x, final char y) {
            return Order.gteqv$mcC$sp$(this, x, y);
         }

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return Order.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return Order.gteqv$mcF$sp$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return Order.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gteqv$mcJ$sp(final long x, final long y) {
            return Order.gteqv$mcJ$sp$(this, x, y);
         }

         public boolean gteqv$mcS$sp(final short x, final short y) {
            return Order.gteqv$mcS$sp$(this, x, y);
         }

         public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gteqv$mcV$sp$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Order.gt$(this, x, y);
         }

         public boolean gt$mcZ$sp(final boolean x, final boolean y) {
            return Order.gt$mcZ$sp$(this, x, y);
         }

         public boolean gt$mcB$sp(final byte x, final byte y) {
            return Order.gt$mcB$sp$(this, x, y);
         }

         public boolean gt$mcC$sp(final char x, final char y) {
            return Order.gt$mcC$sp$(this, x, y);
         }

         public boolean gt$mcD$sp(final double x, final double y) {
            return Order.gt$mcD$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return Order.gt$mcF$sp$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return Order.gt$mcI$sp$(this, x, y);
         }

         public boolean gt$mcJ$sp(final long x, final long y) {
            return Order.gt$mcJ$sp$(this, x, y);
         }

         public boolean gt$mcS$sp(final short x, final short y) {
            return Order.gt$mcS$sp$(this, x, y);
         }

         public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gt$mcV$sp$(this, x, y);
         }

         public Ordering toOrdering() {
            return Order.toOrdering$(this);
         }

         public Option partialComparison(final Object x, final Object y) {
            return PartialOrder.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
         }

         public Option partialComparison$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialComparison$mcB$sp$(this, x, y);
         }

         public Option partialComparison$mcC$sp(final char x, final char y) {
            return PartialOrder.partialComparison$mcC$sp$(this, x, y);
         }

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder.partialComparison$mcD$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder.partialComparison$mcI$sp$(this, x, y);
         }

         public Option partialComparison$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
         }

         public Option partialComparison$mcS$sp(final short x, final short y) {
            return PartialOrder.partialComparison$mcS$sp$(this, x, y);
         }

         public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialComparison$mcV$sp$(this, x, y);
         }

         public Option tryCompare(final Object x, final Object y) {
            return PartialOrder.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
         }

         public Option tryCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.tryCompare$mcB$sp$(this, x, y);
         }

         public Option tryCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.tryCompare$mcC$sp$(this, x, y);
         }

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.tryCompare$mcD$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.tryCompare$mcI$sp$(this, x, y);
         }

         public Option tryCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
         }

         public Option tryCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.tryCompare$mcS$sp$(this, x, y);
         }

         public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.tryCompare$mcV$sp$(this, x, y);
         }

         public Option pmin(final Object x, final Object y) {
            return PartialOrder.pmin$(this, x, y);
         }

         public Option pmin$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmin$mcZ$sp$(this, x, y);
         }

         public Option pmin$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmin$mcB$sp$(this, x, y);
         }

         public Option pmin$mcC$sp(final char x, final char y) {
            return PartialOrder.pmin$mcC$sp$(this, x, y);
         }

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder.pmin$mcD$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder.pmin$mcI$sp$(this, x, y);
         }

         public Option pmin$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmin$mcJ$sp$(this, x, y);
         }

         public Option pmin$mcS$sp(final short x, final short y) {
            return PartialOrder.pmin$mcS$sp$(this, x, y);
         }

         public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmin$mcV$sp$(this, x, y);
         }

         public Option pmax(final Object x, final Object y) {
            return PartialOrder.pmax$(this, x, y);
         }

         public Option pmax$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmax$mcZ$sp$(this, x, y);
         }

         public Option pmax$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmax$mcB$sp$(this, x, y);
         }

         public Option pmax$mcC$sp(final char x, final char y) {
            return PartialOrder.pmax$mcC$sp$(this, x, y);
         }

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder.pmax$mcD$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder.pmax$mcI$sp$(this, x, y);
         }

         public Option pmax$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmax$mcJ$sp$(this, x, y);
         }

         public Option pmax$mcS$sp(final short x, final short y) {
            return PartialOrder.pmax$mcS$sp$(this, x, y);
         }

         public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmax$mcV$sp$(this, x, y);
         }

         public int compare(final Tuple14 x, final Tuple14 y) {
            return BoxesRunTime.unboxToInt(.MODULE$.find$extension(scala.Predef..MODULE$.intArrayOps(new int[]{this.A0$498.compare(x._1(), y._1()), this.A1$475.compare(x._2(), y._2()), this.A2$452.compare(x._3(), y._3()), this.A3$429.compare(x._4(), y._4()), this.A4$406.compare(x._5(), y._5()), this.A5$383.compare(x._6(), y._6()), this.A6$360.compare(x._7(), y._7()), this.A7$337.compare(x._8(), y._8()), this.A8$314.compare(x._9(), y._9()), this.A9$291.compare(x._10(), y._10()), this.A10$268.compare(x._11(), y._11()), this.A11$245.compare(x._12(), y._12()), this.A12$222.compare(x._13(), y._13()), this.A13$199.compare(x._14(), y._14())}), (JFunction1.mcZI.sp)(x$58) -> x$58 != 0).getOrElse((JFunction0.mcI.sp)() -> 0));
         }

         public {
            this.A0$498 = A0$498;
            this.A1$475 = A1$475;
            this.A2$452 = A2$452;
            this.A3$429 = A3$429;
            this.A4$406 = A4$406;
            this.A5$383 = A5$383;
            this.A6$360 = A6$360;
            this.A7$337 = A7$337;
            this.A8$314 = A8$314;
            this.A9$291 = A9$291;
            this.A10$268 = A10$268;
            this.A11$245 = A11$245;
            this.A12$222 = A12$222;
            this.A13$199 = A13$199;
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static Order catsKernelOrderForTuple15$(final TupleOrderInstances $this, final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14) {
      return $this.catsKernelOrderForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   default Order catsKernelOrderForTuple15(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14) {
      return new Order(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) {
         private final Order A0$499;
         private final Order A1$476;
         private final Order A2$453;
         private final Order A3$430;
         private final Order A4$407;
         private final Order A5$384;
         private final Order A6$361;
         private final Order A7$338;
         private final Order A8$315;
         private final Order A9$292;
         private final Order A10$269;
         private final Order A11$246;
         private final Order A12$223;
         private final Order A13$200;
         private final Order A14$177;

         public int compare$mcZ$sp(final boolean x, final boolean y) {
            return Order.compare$mcZ$sp$(this, x, y);
         }

         public int compare$mcB$sp(final byte x, final byte y) {
            return Order.compare$mcB$sp$(this, x, y);
         }

         public int compare$mcC$sp(final char x, final char y) {
            return Order.compare$mcC$sp$(this, x, y);
         }

         public int compare$mcD$sp(final double x, final double y) {
            return Order.compare$mcD$sp$(this, x, y);
         }

         public int compare$mcF$sp(final float x, final float y) {
            return Order.compare$mcF$sp$(this, x, y);
         }

         public int compare$mcI$sp(final int x, final int y) {
            return Order.compare$mcI$sp$(this, x, y);
         }

         public int compare$mcJ$sp(final long x, final long y) {
            return Order.compare$mcJ$sp$(this, x, y);
         }

         public int compare$mcS$sp(final short x, final short y) {
            return Order.compare$mcS$sp$(this, x, y);
         }

         public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.compare$mcV$sp$(this, x, y);
         }

         public Comparison comparison(final Object x, final Object y) {
            return Order.comparison$(this, x, y);
         }

         public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
            return Order.comparison$mcZ$sp$(this, x, y);
         }

         public Comparison comparison$mcB$sp(final byte x, final byte y) {
            return Order.comparison$mcB$sp$(this, x, y);
         }

         public Comparison comparison$mcC$sp(final char x, final char y) {
            return Order.comparison$mcC$sp$(this, x, y);
         }

         public Comparison comparison$mcD$sp(final double x, final double y) {
            return Order.comparison$mcD$sp$(this, x, y);
         }

         public Comparison comparison$mcF$sp(final float x, final float y) {
            return Order.comparison$mcF$sp$(this, x, y);
         }

         public Comparison comparison$mcI$sp(final int x, final int y) {
            return Order.comparison$mcI$sp$(this, x, y);
         }

         public Comparison comparison$mcJ$sp(final long x, final long y) {
            return Order.comparison$mcJ$sp$(this, x, y);
         }

         public Comparison comparison$mcS$sp(final short x, final short y) {
            return Order.comparison$mcS$sp$(this, x, y);
         }

         public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.comparison$mcV$sp$(this, x, y);
         }

         public double partialCompare(final Object x, final Object y) {
            return Order.partialCompare$(this, x, y);
         }

         public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
            return Order.partialCompare$mcZ$sp$(this, x, y);
         }

         public double partialCompare$mcB$sp(final byte x, final byte y) {
            return Order.partialCompare$mcB$sp$(this, x, y);
         }

         public double partialCompare$mcC$sp(final char x, final char y) {
            return Order.partialCompare$mcC$sp$(this, x, y);
         }

         public double partialCompare$mcD$sp(final double x, final double y) {
            return Order.partialCompare$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return Order.partialCompare$mcF$sp$(this, x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            return Order.partialCompare$mcI$sp$(this, x, y);
         }

         public double partialCompare$mcJ$sp(final long x, final long y) {
            return Order.partialCompare$mcJ$sp$(this, x, y);
         }

         public double partialCompare$mcS$sp(final short x, final short y) {
            return Order.partialCompare$mcS$sp$(this, x, y);
         }

         public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.partialCompare$mcV$sp$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Order.min$(this, x, y);
         }

         public boolean min$mcZ$sp(final boolean x, final boolean y) {
            return Order.min$mcZ$sp$(this, x, y);
         }

         public byte min$mcB$sp(final byte x, final byte y) {
            return Order.min$mcB$sp$(this, x, y);
         }

         public char min$mcC$sp(final char x, final char y) {
            return Order.min$mcC$sp$(this, x, y);
         }

         public double min$mcD$sp(final double x, final double y) {
            return Order.min$mcD$sp$(this, x, y);
         }

         public float min$mcF$sp(final float x, final float y) {
            return Order.min$mcF$sp$(this, x, y);
         }

         public int min$mcI$sp(final int x, final int y) {
            return Order.min$mcI$sp$(this, x, y);
         }

         public long min$mcJ$sp(final long x, final long y) {
            return Order.min$mcJ$sp$(this, x, y);
         }

         public short min$mcS$sp(final short x, final short y) {
            return Order.min$mcS$sp$(this, x, y);
         }

         public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.min$mcV$sp$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Order.max$(this, x, y);
         }

         public boolean max$mcZ$sp(final boolean x, final boolean y) {
            return Order.max$mcZ$sp$(this, x, y);
         }

         public byte max$mcB$sp(final byte x, final byte y) {
            return Order.max$mcB$sp$(this, x, y);
         }

         public char max$mcC$sp(final char x, final char y) {
            return Order.max$mcC$sp$(this, x, y);
         }

         public double max$mcD$sp(final double x, final double y) {
            return Order.max$mcD$sp$(this, x, y);
         }

         public float max$mcF$sp(final float x, final float y) {
            return Order.max$mcF$sp$(this, x, y);
         }

         public int max$mcI$sp(final int x, final int y) {
            return Order.max$mcI$sp$(this, x, y);
         }

         public long max$mcJ$sp(final long x, final long y) {
            return Order.max$mcJ$sp$(this, x, y);
         }

         public short max$mcS$sp(final short x, final short y) {
            return Order.max$mcS$sp$(this, x, y);
         }

         public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.max$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return Order.eqv$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Order.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Order.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Order.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Order.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Order.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Order.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Order.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Order.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Order.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Order.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Order.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Order.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Order.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Order.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Order.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.neqv$mcV$sp$(this, x, y);
         }

         public boolean lteqv(final Object x, final Object y) {
            return Order.lteqv$(this, x, y);
         }

         public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.lteqv$mcZ$sp$(this, x, y);
         }

         public boolean lteqv$mcB$sp(final byte x, final byte y) {
            return Order.lteqv$mcB$sp$(this, x, y);
         }

         public boolean lteqv$mcC$sp(final char x, final char y) {
            return Order.lteqv$mcC$sp$(this, x, y);
         }

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return Order.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return Order.lteqv$mcF$sp$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return Order.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv$mcJ$sp(final long x, final long y) {
            return Order.lteqv$mcJ$sp$(this, x, y);
         }

         public boolean lteqv$mcS$sp(final short x, final short y) {
            return Order.lteqv$mcS$sp$(this, x, y);
         }

         public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lteqv$mcV$sp$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Order.lt$(this, x, y);
         }

         public boolean lt$mcZ$sp(final boolean x, final boolean y) {
            return Order.lt$mcZ$sp$(this, x, y);
         }

         public boolean lt$mcB$sp(final byte x, final byte y) {
            return Order.lt$mcB$sp$(this, x, y);
         }

         public boolean lt$mcC$sp(final char x, final char y) {
            return Order.lt$mcC$sp$(this, x, y);
         }

         public boolean lt$mcD$sp(final double x, final double y) {
            return Order.lt$mcD$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return Order.lt$mcF$sp$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return Order.lt$mcI$sp$(this, x, y);
         }

         public boolean lt$mcJ$sp(final long x, final long y) {
            return Order.lt$mcJ$sp$(this, x, y);
         }

         public boolean lt$mcS$sp(final short x, final short y) {
            return Order.lt$mcS$sp$(this, x, y);
         }

         public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lt$mcV$sp$(this, x, y);
         }

         public boolean gteqv(final Object x, final Object y) {
            return Order.gteqv$(this, x, y);
         }

         public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.gteqv$mcZ$sp$(this, x, y);
         }

         public boolean gteqv$mcB$sp(final byte x, final byte y) {
            return Order.gteqv$mcB$sp$(this, x, y);
         }

         public boolean gteqv$mcC$sp(final char x, final char y) {
            return Order.gteqv$mcC$sp$(this, x, y);
         }

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return Order.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return Order.gteqv$mcF$sp$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return Order.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gteqv$mcJ$sp(final long x, final long y) {
            return Order.gteqv$mcJ$sp$(this, x, y);
         }

         public boolean gteqv$mcS$sp(final short x, final short y) {
            return Order.gteqv$mcS$sp$(this, x, y);
         }

         public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gteqv$mcV$sp$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Order.gt$(this, x, y);
         }

         public boolean gt$mcZ$sp(final boolean x, final boolean y) {
            return Order.gt$mcZ$sp$(this, x, y);
         }

         public boolean gt$mcB$sp(final byte x, final byte y) {
            return Order.gt$mcB$sp$(this, x, y);
         }

         public boolean gt$mcC$sp(final char x, final char y) {
            return Order.gt$mcC$sp$(this, x, y);
         }

         public boolean gt$mcD$sp(final double x, final double y) {
            return Order.gt$mcD$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return Order.gt$mcF$sp$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return Order.gt$mcI$sp$(this, x, y);
         }

         public boolean gt$mcJ$sp(final long x, final long y) {
            return Order.gt$mcJ$sp$(this, x, y);
         }

         public boolean gt$mcS$sp(final short x, final short y) {
            return Order.gt$mcS$sp$(this, x, y);
         }

         public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gt$mcV$sp$(this, x, y);
         }

         public Ordering toOrdering() {
            return Order.toOrdering$(this);
         }

         public Option partialComparison(final Object x, final Object y) {
            return PartialOrder.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
         }

         public Option partialComparison$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialComparison$mcB$sp$(this, x, y);
         }

         public Option partialComparison$mcC$sp(final char x, final char y) {
            return PartialOrder.partialComparison$mcC$sp$(this, x, y);
         }

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder.partialComparison$mcD$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder.partialComparison$mcI$sp$(this, x, y);
         }

         public Option partialComparison$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
         }

         public Option partialComparison$mcS$sp(final short x, final short y) {
            return PartialOrder.partialComparison$mcS$sp$(this, x, y);
         }

         public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialComparison$mcV$sp$(this, x, y);
         }

         public Option tryCompare(final Object x, final Object y) {
            return PartialOrder.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
         }

         public Option tryCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.tryCompare$mcB$sp$(this, x, y);
         }

         public Option tryCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.tryCompare$mcC$sp$(this, x, y);
         }

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.tryCompare$mcD$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.tryCompare$mcI$sp$(this, x, y);
         }

         public Option tryCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
         }

         public Option tryCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.tryCompare$mcS$sp$(this, x, y);
         }

         public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.tryCompare$mcV$sp$(this, x, y);
         }

         public Option pmin(final Object x, final Object y) {
            return PartialOrder.pmin$(this, x, y);
         }

         public Option pmin$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmin$mcZ$sp$(this, x, y);
         }

         public Option pmin$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmin$mcB$sp$(this, x, y);
         }

         public Option pmin$mcC$sp(final char x, final char y) {
            return PartialOrder.pmin$mcC$sp$(this, x, y);
         }

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder.pmin$mcD$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder.pmin$mcI$sp$(this, x, y);
         }

         public Option pmin$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmin$mcJ$sp$(this, x, y);
         }

         public Option pmin$mcS$sp(final short x, final short y) {
            return PartialOrder.pmin$mcS$sp$(this, x, y);
         }

         public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmin$mcV$sp$(this, x, y);
         }

         public Option pmax(final Object x, final Object y) {
            return PartialOrder.pmax$(this, x, y);
         }

         public Option pmax$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmax$mcZ$sp$(this, x, y);
         }

         public Option pmax$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmax$mcB$sp$(this, x, y);
         }

         public Option pmax$mcC$sp(final char x, final char y) {
            return PartialOrder.pmax$mcC$sp$(this, x, y);
         }

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder.pmax$mcD$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder.pmax$mcI$sp$(this, x, y);
         }

         public Option pmax$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmax$mcJ$sp$(this, x, y);
         }

         public Option pmax$mcS$sp(final short x, final short y) {
            return PartialOrder.pmax$mcS$sp$(this, x, y);
         }

         public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmax$mcV$sp$(this, x, y);
         }

         public int compare(final Tuple15 x, final Tuple15 y) {
            return BoxesRunTime.unboxToInt(.MODULE$.find$extension(scala.Predef..MODULE$.intArrayOps(new int[]{this.A0$499.compare(x._1(), y._1()), this.A1$476.compare(x._2(), y._2()), this.A2$453.compare(x._3(), y._3()), this.A3$430.compare(x._4(), y._4()), this.A4$407.compare(x._5(), y._5()), this.A5$384.compare(x._6(), y._6()), this.A6$361.compare(x._7(), y._7()), this.A7$338.compare(x._8(), y._8()), this.A8$315.compare(x._9(), y._9()), this.A9$292.compare(x._10(), y._10()), this.A10$269.compare(x._11(), y._11()), this.A11$246.compare(x._12(), y._12()), this.A12$223.compare(x._13(), y._13()), this.A13$200.compare(x._14(), y._14()), this.A14$177.compare(x._15(), y._15())}), (JFunction1.mcZI.sp)(x$59) -> x$59 != 0).getOrElse((JFunction0.mcI.sp)() -> 0));
         }

         public {
            this.A0$499 = A0$499;
            this.A1$476 = A1$476;
            this.A2$453 = A2$453;
            this.A3$430 = A3$430;
            this.A4$407 = A4$407;
            this.A5$384 = A5$384;
            this.A6$361 = A6$361;
            this.A7$338 = A7$338;
            this.A8$315 = A8$315;
            this.A9$292 = A9$292;
            this.A10$269 = A10$269;
            this.A11$246 = A11$246;
            this.A12$223 = A12$223;
            this.A13$200 = A13$200;
            this.A14$177 = A14$177;
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static Order catsKernelOrderForTuple16$(final TupleOrderInstances $this, final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15) {
      return $this.catsKernelOrderForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   default Order catsKernelOrderForTuple16(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15) {
      return new Order(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) {
         private final Order A0$500;
         private final Order A1$477;
         private final Order A2$454;
         private final Order A3$431;
         private final Order A4$408;
         private final Order A5$385;
         private final Order A6$362;
         private final Order A7$339;
         private final Order A8$316;
         private final Order A9$293;
         private final Order A10$270;
         private final Order A11$247;
         private final Order A12$224;
         private final Order A13$201;
         private final Order A14$178;
         private final Order A15$155;

         public int compare$mcZ$sp(final boolean x, final boolean y) {
            return Order.compare$mcZ$sp$(this, x, y);
         }

         public int compare$mcB$sp(final byte x, final byte y) {
            return Order.compare$mcB$sp$(this, x, y);
         }

         public int compare$mcC$sp(final char x, final char y) {
            return Order.compare$mcC$sp$(this, x, y);
         }

         public int compare$mcD$sp(final double x, final double y) {
            return Order.compare$mcD$sp$(this, x, y);
         }

         public int compare$mcF$sp(final float x, final float y) {
            return Order.compare$mcF$sp$(this, x, y);
         }

         public int compare$mcI$sp(final int x, final int y) {
            return Order.compare$mcI$sp$(this, x, y);
         }

         public int compare$mcJ$sp(final long x, final long y) {
            return Order.compare$mcJ$sp$(this, x, y);
         }

         public int compare$mcS$sp(final short x, final short y) {
            return Order.compare$mcS$sp$(this, x, y);
         }

         public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.compare$mcV$sp$(this, x, y);
         }

         public Comparison comparison(final Object x, final Object y) {
            return Order.comparison$(this, x, y);
         }

         public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
            return Order.comparison$mcZ$sp$(this, x, y);
         }

         public Comparison comparison$mcB$sp(final byte x, final byte y) {
            return Order.comparison$mcB$sp$(this, x, y);
         }

         public Comparison comparison$mcC$sp(final char x, final char y) {
            return Order.comparison$mcC$sp$(this, x, y);
         }

         public Comparison comparison$mcD$sp(final double x, final double y) {
            return Order.comparison$mcD$sp$(this, x, y);
         }

         public Comparison comparison$mcF$sp(final float x, final float y) {
            return Order.comparison$mcF$sp$(this, x, y);
         }

         public Comparison comparison$mcI$sp(final int x, final int y) {
            return Order.comparison$mcI$sp$(this, x, y);
         }

         public Comparison comparison$mcJ$sp(final long x, final long y) {
            return Order.comparison$mcJ$sp$(this, x, y);
         }

         public Comparison comparison$mcS$sp(final short x, final short y) {
            return Order.comparison$mcS$sp$(this, x, y);
         }

         public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.comparison$mcV$sp$(this, x, y);
         }

         public double partialCompare(final Object x, final Object y) {
            return Order.partialCompare$(this, x, y);
         }

         public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
            return Order.partialCompare$mcZ$sp$(this, x, y);
         }

         public double partialCompare$mcB$sp(final byte x, final byte y) {
            return Order.partialCompare$mcB$sp$(this, x, y);
         }

         public double partialCompare$mcC$sp(final char x, final char y) {
            return Order.partialCompare$mcC$sp$(this, x, y);
         }

         public double partialCompare$mcD$sp(final double x, final double y) {
            return Order.partialCompare$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return Order.partialCompare$mcF$sp$(this, x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            return Order.partialCompare$mcI$sp$(this, x, y);
         }

         public double partialCompare$mcJ$sp(final long x, final long y) {
            return Order.partialCompare$mcJ$sp$(this, x, y);
         }

         public double partialCompare$mcS$sp(final short x, final short y) {
            return Order.partialCompare$mcS$sp$(this, x, y);
         }

         public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.partialCompare$mcV$sp$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Order.min$(this, x, y);
         }

         public boolean min$mcZ$sp(final boolean x, final boolean y) {
            return Order.min$mcZ$sp$(this, x, y);
         }

         public byte min$mcB$sp(final byte x, final byte y) {
            return Order.min$mcB$sp$(this, x, y);
         }

         public char min$mcC$sp(final char x, final char y) {
            return Order.min$mcC$sp$(this, x, y);
         }

         public double min$mcD$sp(final double x, final double y) {
            return Order.min$mcD$sp$(this, x, y);
         }

         public float min$mcF$sp(final float x, final float y) {
            return Order.min$mcF$sp$(this, x, y);
         }

         public int min$mcI$sp(final int x, final int y) {
            return Order.min$mcI$sp$(this, x, y);
         }

         public long min$mcJ$sp(final long x, final long y) {
            return Order.min$mcJ$sp$(this, x, y);
         }

         public short min$mcS$sp(final short x, final short y) {
            return Order.min$mcS$sp$(this, x, y);
         }

         public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.min$mcV$sp$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Order.max$(this, x, y);
         }

         public boolean max$mcZ$sp(final boolean x, final boolean y) {
            return Order.max$mcZ$sp$(this, x, y);
         }

         public byte max$mcB$sp(final byte x, final byte y) {
            return Order.max$mcB$sp$(this, x, y);
         }

         public char max$mcC$sp(final char x, final char y) {
            return Order.max$mcC$sp$(this, x, y);
         }

         public double max$mcD$sp(final double x, final double y) {
            return Order.max$mcD$sp$(this, x, y);
         }

         public float max$mcF$sp(final float x, final float y) {
            return Order.max$mcF$sp$(this, x, y);
         }

         public int max$mcI$sp(final int x, final int y) {
            return Order.max$mcI$sp$(this, x, y);
         }

         public long max$mcJ$sp(final long x, final long y) {
            return Order.max$mcJ$sp$(this, x, y);
         }

         public short max$mcS$sp(final short x, final short y) {
            return Order.max$mcS$sp$(this, x, y);
         }

         public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.max$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return Order.eqv$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Order.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Order.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Order.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Order.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Order.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Order.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Order.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Order.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Order.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Order.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Order.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Order.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Order.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Order.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Order.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.neqv$mcV$sp$(this, x, y);
         }

         public boolean lteqv(final Object x, final Object y) {
            return Order.lteqv$(this, x, y);
         }

         public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.lteqv$mcZ$sp$(this, x, y);
         }

         public boolean lteqv$mcB$sp(final byte x, final byte y) {
            return Order.lteqv$mcB$sp$(this, x, y);
         }

         public boolean lteqv$mcC$sp(final char x, final char y) {
            return Order.lteqv$mcC$sp$(this, x, y);
         }

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return Order.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return Order.lteqv$mcF$sp$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return Order.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv$mcJ$sp(final long x, final long y) {
            return Order.lteqv$mcJ$sp$(this, x, y);
         }

         public boolean lteqv$mcS$sp(final short x, final short y) {
            return Order.lteqv$mcS$sp$(this, x, y);
         }

         public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lteqv$mcV$sp$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Order.lt$(this, x, y);
         }

         public boolean lt$mcZ$sp(final boolean x, final boolean y) {
            return Order.lt$mcZ$sp$(this, x, y);
         }

         public boolean lt$mcB$sp(final byte x, final byte y) {
            return Order.lt$mcB$sp$(this, x, y);
         }

         public boolean lt$mcC$sp(final char x, final char y) {
            return Order.lt$mcC$sp$(this, x, y);
         }

         public boolean lt$mcD$sp(final double x, final double y) {
            return Order.lt$mcD$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return Order.lt$mcF$sp$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return Order.lt$mcI$sp$(this, x, y);
         }

         public boolean lt$mcJ$sp(final long x, final long y) {
            return Order.lt$mcJ$sp$(this, x, y);
         }

         public boolean lt$mcS$sp(final short x, final short y) {
            return Order.lt$mcS$sp$(this, x, y);
         }

         public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lt$mcV$sp$(this, x, y);
         }

         public boolean gteqv(final Object x, final Object y) {
            return Order.gteqv$(this, x, y);
         }

         public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.gteqv$mcZ$sp$(this, x, y);
         }

         public boolean gteqv$mcB$sp(final byte x, final byte y) {
            return Order.gteqv$mcB$sp$(this, x, y);
         }

         public boolean gteqv$mcC$sp(final char x, final char y) {
            return Order.gteqv$mcC$sp$(this, x, y);
         }

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return Order.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return Order.gteqv$mcF$sp$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return Order.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gteqv$mcJ$sp(final long x, final long y) {
            return Order.gteqv$mcJ$sp$(this, x, y);
         }

         public boolean gteqv$mcS$sp(final short x, final short y) {
            return Order.gteqv$mcS$sp$(this, x, y);
         }

         public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gteqv$mcV$sp$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Order.gt$(this, x, y);
         }

         public boolean gt$mcZ$sp(final boolean x, final boolean y) {
            return Order.gt$mcZ$sp$(this, x, y);
         }

         public boolean gt$mcB$sp(final byte x, final byte y) {
            return Order.gt$mcB$sp$(this, x, y);
         }

         public boolean gt$mcC$sp(final char x, final char y) {
            return Order.gt$mcC$sp$(this, x, y);
         }

         public boolean gt$mcD$sp(final double x, final double y) {
            return Order.gt$mcD$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return Order.gt$mcF$sp$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return Order.gt$mcI$sp$(this, x, y);
         }

         public boolean gt$mcJ$sp(final long x, final long y) {
            return Order.gt$mcJ$sp$(this, x, y);
         }

         public boolean gt$mcS$sp(final short x, final short y) {
            return Order.gt$mcS$sp$(this, x, y);
         }

         public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gt$mcV$sp$(this, x, y);
         }

         public Ordering toOrdering() {
            return Order.toOrdering$(this);
         }

         public Option partialComparison(final Object x, final Object y) {
            return PartialOrder.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
         }

         public Option partialComparison$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialComparison$mcB$sp$(this, x, y);
         }

         public Option partialComparison$mcC$sp(final char x, final char y) {
            return PartialOrder.partialComparison$mcC$sp$(this, x, y);
         }

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder.partialComparison$mcD$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder.partialComparison$mcI$sp$(this, x, y);
         }

         public Option partialComparison$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
         }

         public Option partialComparison$mcS$sp(final short x, final short y) {
            return PartialOrder.partialComparison$mcS$sp$(this, x, y);
         }

         public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialComparison$mcV$sp$(this, x, y);
         }

         public Option tryCompare(final Object x, final Object y) {
            return PartialOrder.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
         }

         public Option tryCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.tryCompare$mcB$sp$(this, x, y);
         }

         public Option tryCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.tryCompare$mcC$sp$(this, x, y);
         }

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.tryCompare$mcD$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.tryCompare$mcI$sp$(this, x, y);
         }

         public Option tryCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
         }

         public Option tryCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.tryCompare$mcS$sp$(this, x, y);
         }

         public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.tryCompare$mcV$sp$(this, x, y);
         }

         public Option pmin(final Object x, final Object y) {
            return PartialOrder.pmin$(this, x, y);
         }

         public Option pmin$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmin$mcZ$sp$(this, x, y);
         }

         public Option pmin$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmin$mcB$sp$(this, x, y);
         }

         public Option pmin$mcC$sp(final char x, final char y) {
            return PartialOrder.pmin$mcC$sp$(this, x, y);
         }

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder.pmin$mcD$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder.pmin$mcI$sp$(this, x, y);
         }

         public Option pmin$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmin$mcJ$sp$(this, x, y);
         }

         public Option pmin$mcS$sp(final short x, final short y) {
            return PartialOrder.pmin$mcS$sp$(this, x, y);
         }

         public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmin$mcV$sp$(this, x, y);
         }

         public Option pmax(final Object x, final Object y) {
            return PartialOrder.pmax$(this, x, y);
         }

         public Option pmax$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmax$mcZ$sp$(this, x, y);
         }

         public Option pmax$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmax$mcB$sp$(this, x, y);
         }

         public Option pmax$mcC$sp(final char x, final char y) {
            return PartialOrder.pmax$mcC$sp$(this, x, y);
         }

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder.pmax$mcD$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder.pmax$mcI$sp$(this, x, y);
         }

         public Option pmax$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmax$mcJ$sp$(this, x, y);
         }

         public Option pmax$mcS$sp(final short x, final short y) {
            return PartialOrder.pmax$mcS$sp$(this, x, y);
         }

         public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmax$mcV$sp$(this, x, y);
         }

         public int compare(final Tuple16 x, final Tuple16 y) {
            return BoxesRunTime.unboxToInt(.MODULE$.find$extension(scala.Predef..MODULE$.intArrayOps(new int[]{this.A0$500.compare(x._1(), y._1()), this.A1$477.compare(x._2(), y._2()), this.A2$454.compare(x._3(), y._3()), this.A3$431.compare(x._4(), y._4()), this.A4$408.compare(x._5(), y._5()), this.A5$385.compare(x._6(), y._6()), this.A6$362.compare(x._7(), y._7()), this.A7$339.compare(x._8(), y._8()), this.A8$316.compare(x._9(), y._9()), this.A9$293.compare(x._10(), y._10()), this.A10$270.compare(x._11(), y._11()), this.A11$247.compare(x._12(), y._12()), this.A12$224.compare(x._13(), y._13()), this.A13$201.compare(x._14(), y._14()), this.A14$178.compare(x._15(), y._15()), this.A15$155.compare(x._16(), y._16())}), (JFunction1.mcZI.sp)(x$60) -> x$60 != 0).getOrElse((JFunction0.mcI.sp)() -> 0));
         }

         public {
            this.A0$500 = A0$500;
            this.A1$477 = A1$477;
            this.A2$454 = A2$454;
            this.A3$431 = A3$431;
            this.A4$408 = A4$408;
            this.A5$385 = A5$385;
            this.A6$362 = A6$362;
            this.A7$339 = A7$339;
            this.A8$316 = A8$316;
            this.A9$293 = A9$293;
            this.A10$270 = A10$270;
            this.A11$247 = A11$247;
            this.A12$224 = A12$224;
            this.A13$201 = A13$201;
            this.A14$178 = A14$178;
            this.A15$155 = A15$155;
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static Order catsKernelOrderForTuple17$(final TupleOrderInstances $this, final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16) {
      return $this.catsKernelOrderForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   default Order catsKernelOrderForTuple17(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16) {
      return new Order(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) {
         private final Order A0$501;
         private final Order A1$478;
         private final Order A2$455;
         private final Order A3$432;
         private final Order A4$409;
         private final Order A5$386;
         private final Order A6$363;
         private final Order A7$340;
         private final Order A8$317;
         private final Order A9$294;
         private final Order A10$271;
         private final Order A11$248;
         private final Order A12$225;
         private final Order A13$202;
         private final Order A14$179;
         private final Order A15$156;
         private final Order A16$133;

         public int compare$mcZ$sp(final boolean x, final boolean y) {
            return Order.compare$mcZ$sp$(this, x, y);
         }

         public int compare$mcB$sp(final byte x, final byte y) {
            return Order.compare$mcB$sp$(this, x, y);
         }

         public int compare$mcC$sp(final char x, final char y) {
            return Order.compare$mcC$sp$(this, x, y);
         }

         public int compare$mcD$sp(final double x, final double y) {
            return Order.compare$mcD$sp$(this, x, y);
         }

         public int compare$mcF$sp(final float x, final float y) {
            return Order.compare$mcF$sp$(this, x, y);
         }

         public int compare$mcI$sp(final int x, final int y) {
            return Order.compare$mcI$sp$(this, x, y);
         }

         public int compare$mcJ$sp(final long x, final long y) {
            return Order.compare$mcJ$sp$(this, x, y);
         }

         public int compare$mcS$sp(final short x, final short y) {
            return Order.compare$mcS$sp$(this, x, y);
         }

         public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.compare$mcV$sp$(this, x, y);
         }

         public Comparison comparison(final Object x, final Object y) {
            return Order.comparison$(this, x, y);
         }

         public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
            return Order.comparison$mcZ$sp$(this, x, y);
         }

         public Comparison comparison$mcB$sp(final byte x, final byte y) {
            return Order.comparison$mcB$sp$(this, x, y);
         }

         public Comparison comparison$mcC$sp(final char x, final char y) {
            return Order.comparison$mcC$sp$(this, x, y);
         }

         public Comparison comparison$mcD$sp(final double x, final double y) {
            return Order.comparison$mcD$sp$(this, x, y);
         }

         public Comparison comparison$mcF$sp(final float x, final float y) {
            return Order.comparison$mcF$sp$(this, x, y);
         }

         public Comparison comparison$mcI$sp(final int x, final int y) {
            return Order.comparison$mcI$sp$(this, x, y);
         }

         public Comparison comparison$mcJ$sp(final long x, final long y) {
            return Order.comparison$mcJ$sp$(this, x, y);
         }

         public Comparison comparison$mcS$sp(final short x, final short y) {
            return Order.comparison$mcS$sp$(this, x, y);
         }

         public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.comparison$mcV$sp$(this, x, y);
         }

         public double partialCompare(final Object x, final Object y) {
            return Order.partialCompare$(this, x, y);
         }

         public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
            return Order.partialCompare$mcZ$sp$(this, x, y);
         }

         public double partialCompare$mcB$sp(final byte x, final byte y) {
            return Order.partialCompare$mcB$sp$(this, x, y);
         }

         public double partialCompare$mcC$sp(final char x, final char y) {
            return Order.partialCompare$mcC$sp$(this, x, y);
         }

         public double partialCompare$mcD$sp(final double x, final double y) {
            return Order.partialCompare$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return Order.partialCompare$mcF$sp$(this, x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            return Order.partialCompare$mcI$sp$(this, x, y);
         }

         public double partialCompare$mcJ$sp(final long x, final long y) {
            return Order.partialCompare$mcJ$sp$(this, x, y);
         }

         public double partialCompare$mcS$sp(final short x, final short y) {
            return Order.partialCompare$mcS$sp$(this, x, y);
         }

         public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.partialCompare$mcV$sp$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Order.min$(this, x, y);
         }

         public boolean min$mcZ$sp(final boolean x, final boolean y) {
            return Order.min$mcZ$sp$(this, x, y);
         }

         public byte min$mcB$sp(final byte x, final byte y) {
            return Order.min$mcB$sp$(this, x, y);
         }

         public char min$mcC$sp(final char x, final char y) {
            return Order.min$mcC$sp$(this, x, y);
         }

         public double min$mcD$sp(final double x, final double y) {
            return Order.min$mcD$sp$(this, x, y);
         }

         public float min$mcF$sp(final float x, final float y) {
            return Order.min$mcF$sp$(this, x, y);
         }

         public int min$mcI$sp(final int x, final int y) {
            return Order.min$mcI$sp$(this, x, y);
         }

         public long min$mcJ$sp(final long x, final long y) {
            return Order.min$mcJ$sp$(this, x, y);
         }

         public short min$mcS$sp(final short x, final short y) {
            return Order.min$mcS$sp$(this, x, y);
         }

         public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.min$mcV$sp$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Order.max$(this, x, y);
         }

         public boolean max$mcZ$sp(final boolean x, final boolean y) {
            return Order.max$mcZ$sp$(this, x, y);
         }

         public byte max$mcB$sp(final byte x, final byte y) {
            return Order.max$mcB$sp$(this, x, y);
         }

         public char max$mcC$sp(final char x, final char y) {
            return Order.max$mcC$sp$(this, x, y);
         }

         public double max$mcD$sp(final double x, final double y) {
            return Order.max$mcD$sp$(this, x, y);
         }

         public float max$mcF$sp(final float x, final float y) {
            return Order.max$mcF$sp$(this, x, y);
         }

         public int max$mcI$sp(final int x, final int y) {
            return Order.max$mcI$sp$(this, x, y);
         }

         public long max$mcJ$sp(final long x, final long y) {
            return Order.max$mcJ$sp$(this, x, y);
         }

         public short max$mcS$sp(final short x, final short y) {
            return Order.max$mcS$sp$(this, x, y);
         }

         public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.max$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return Order.eqv$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Order.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Order.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Order.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Order.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Order.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Order.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Order.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Order.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Order.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Order.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Order.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Order.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Order.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Order.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Order.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.neqv$mcV$sp$(this, x, y);
         }

         public boolean lteqv(final Object x, final Object y) {
            return Order.lteqv$(this, x, y);
         }

         public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.lteqv$mcZ$sp$(this, x, y);
         }

         public boolean lteqv$mcB$sp(final byte x, final byte y) {
            return Order.lteqv$mcB$sp$(this, x, y);
         }

         public boolean lteqv$mcC$sp(final char x, final char y) {
            return Order.lteqv$mcC$sp$(this, x, y);
         }

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return Order.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return Order.lteqv$mcF$sp$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return Order.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv$mcJ$sp(final long x, final long y) {
            return Order.lteqv$mcJ$sp$(this, x, y);
         }

         public boolean lteqv$mcS$sp(final short x, final short y) {
            return Order.lteqv$mcS$sp$(this, x, y);
         }

         public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lteqv$mcV$sp$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Order.lt$(this, x, y);
         }

         public boolean lt$mcZ$sp(final boolean x, final boolean y) {
            return Order.lt$mcZ$sp$(this, x, y);
         }

         public boolean lt$mcB$sp(final byte x, final byte y) {
            return Order.lt$mcB$sp$(this, x, y);
         }

         public boolean lt$mcC$sp(final char x, final char y) {
            return Order.lt$mcC$sp$(this, x, y);
         }

         public boolean lt$mcD$sp(final double x, final double y) {
            return Order.lt$mcD$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return Order.lt$mcF$sp$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return Order.lt$mcI$sp$(this, x, y);
         }

         public boolean lt$mcJ$sp(final long x, final long y) {
            return Order.lt$mcJ$sp$(this, x, y);
         }

         public boolean lt$mcS$sp(final short x, final short y) {
            return Order.lt$mcS$sp$(this, x, y);
         }

         public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lt$mcV$sp$(this, x, y);
         }

         public boolean gteqv(final Object x, final Object y) {
            return Order.gteqv$(this, x, y);
         }

         public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.gteqv$mcZ$sp$(this, x, y);
         }

         public boolean gteqv$mcB$sp(final byte x, final byte y) {
            return Order.gteqv$mcB$sp$(this, x, y);
         }

         public boolean gteqv$mcC$sp(final char x, final char y) {
            return Order.gteqv$mcC$sp$(this, x, y);
         }

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return Order.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return Order.gteqv$mcF$sp$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return Order.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gteqv$mcJ$sp(final long x, final long y) {
            return Order.gteqv$mcJ$sp$(this, x, y);
         }

         public boolean gteqv$mcS$sp(final short x, final short y) {
            return Order.gteqv$mcS$sp$(this, x, y);
         }

         public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gteqv$mcV$sp$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Order.gt$(this, x, y);
         }

         public boolean gt$mcZ$sp(final boolean x, final boolean y) {
            return Order.gt$mcZ$sp$(this, x, y);
         }

         public boolean gt$mcB$sp(final byte x, final byte y) {
            return Order.gt$mcB$sp$(this, x, y);
         }

         public boolean gt$mcC$sp(final char x, final char y) {
            return Order.gt$mcC$sp$(this, x, y);
         }

         public boolean gt$mcD$sp(final double x, final double y) {
            return Order.gt$mcD$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return Order.gt$mcF$sp$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return Order.gt$mcI$sp$(this, x, y);
         }

         public boolean gt$mcJ$sp(final long x, final long y) {
            return Order.gt$mcJ$sp$(this, x, y);
         }

         public boolean gt$mcS$sp(final short x, final short y) {
            return Order.gt$mcS$sp$(this, x, y);
         }

         public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gt$mcV$sp$(this, x, y);
         }

         public Ordering toOrdering() {
            return Order.toOrdering$(this);
         }

         public Option partialComparison(final Object x, final Object y) {
            return PartialOrder.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
         }

         public Option partialComparison$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialComparison$mcB$sp$(this, x, y);
         }

         public Option partialComparison$mcC$sp(final char x, final char y) {
            return PartialOrder.partialComparison$mcC$sp$(this, x, y);
         }

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder.partialComparison$mcD$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder.partialComparison$mcI$sp$(this, x, y);
         }

         public Option partialComparison$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
         }

         public Option partialComparison$mcS$sp(final short x, final short y) {
            return PartialOrder.partialComparison$mcS$sp$(this, x, y);
         }

         public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialComparison$mcV$sp$(this, x, y);
         }

         public Option tryCompare(final Object x, final Object y) {
            return PartialOrder.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
         }

         public Option tryCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.tryCompare$mcB$sp$(this, x, y);
         }

         public Option tryCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.tryCompare$mcC$sp$(this, x, y);
         }

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.tryCompare$mcD$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.tryCompare$mcI$sp$(this, x, y);
         }

         public Option tryCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
         }

         public Option tryCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.tryCompare$mcS$sp$(this, x, y);
         }

         public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.tryCompare$mcV$sp$(this, x, y);
         }

         public Option pmin(final Object x, final Object y) {
            return PartialOrder.pmin$(this, x, y);
         }

         public Option pmin$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmin$mcZ$sp$(this, x, y);
         }

         public Option pmin$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmin$mcB$sp$(this, x, y);
         }

         public Option pmin$mcC$sp(final char x, final char y) {
            return PartialOrder.pmin$mcC$sp$(this, x, y);
         }

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder.pmin$mcD$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder.pmin$mcI$sp$(this, x, y);
         }

         public Option pmin$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmin$mcJ$sp$(this, x, y);
         }

         public Option pmin$mcS$sp(final short x, final short y) {
            return PartialOrder.pmin$mcS$sp$(this, x, y);
         }

         public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmin$mcV$sp$(this, x, y);
         }

         public Option pmax(final Object x, final Object y) {
            return PartialOrder.pmax$(this, x, y);
         }

         public Option pmax$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmax$mcZ$sp$(this, x, y);
         }

         public Option pmax$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmax$mcB$sp$(this, x, y);
         }

         public Option pmax$mcC$sp(final char x, final char y) {
            return PartialOrder.pmax$mcC$sp$(this, x, y);
         }

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder.pmax$mcD$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder.pmax$mcI$sp$(this, x, y);
         }

         public Option pmax$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmax$mcJ$sp$(this, x, y);
         }

         public Option pmax$mcS$sp(final short x, final short y) {
            return PartialOrder.pmax$mcS$sp$(this, x, y);
         }

         public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmax$mcV$sp$(this, x, y);
         }

         public int compare(final Tuple17 x, final Tuple17 y) {
            return BoxesRunTime.unboxToInt(.MODULE$.find$extension(scala.Predef..MODULE$.intArrayOps(new int[]{this.A0$501.compare(x._1(), y._1()), this.A1$478.compare(x._2(), y._2()), this.A2$455.compare(x._3(), y._3()), this.A3$432.compare(x._4(), y._4()), this.A4$409.compare(x._5(), y._5()), this.A5$386.compare(x._6(), y._6()), this.A6$363.compare(x._7(), y._7()), this.A7$340.compare(x._8(), y._8()), this.A8$317.compare(x._9(), y._9()), this.A9$294.compare(x._10(), y._10()), this.A10$271.compare(x._11(), y._11()), this.A11$248.compare(x._12(), y._12()), this.A12$225.compare(x._13(), y._13()), this.A13$202.compare(x._14(), y._14()), this.A14$179.compare(x._15(), y._15()), this.A15$156.compare(x._16(), y._16()), this.A16$133.compare(x._17(), y._17())}), (JFunction1.mcZI.sp)(x$61) -> x$61 != 0).getOrElse((JFunction0.mcI.sp)() -> 0));
         }

         public {
            this.A0$501 = A0$501;
            this.A1$478 = A1$478;
            this.A2$455 = A2$455;
            this.A3$432 = A3$432;
            this.A4$409 = A4$409;
            this.A5$386 = A5$386;
            this.A6$363 = A6$363;
            this.A7$340 = A7$340;
            this.A8$317 = A8$317;
            this.A9$294 = A9$294;
            this.A10$271 = A10$271;
            this.A11$248 = A11$248;
            this.A12$225 = A12$225;
            this.A13$202 = A13$202;
            this.A14$179 = A14$179;
            this.A15$156 = A15$156;
            this.A16$133 = A16$133;
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static Order catsKernelOrderForTuple18$(final TupleOrderInstances $this, final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17) {
      return $this.catsKernelOrderForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   default Order catsKernelOrderForTuple18(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17) {
      return new Order(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) {
         private final Order A0$502;
         private final Order A1$479;
         private final Order A2$456;
         private final Order A3$433;
         private final Order A4$410;
         private final Order A5$387;
         private final Order A6$364;
         private final Order A7$341;
         private final Order A8$318;
         private final Order A9$295;
         private final Order A10$272;
         private final Order A11$249;
         private final Order A12$226;
         private final Order A13$203;
         private final Order A14$180;
         private final Order A15$157;
         private final Order A16$134;
         private final Order A17$111;

         public int compare$mcZ$sp(final boolean x, final boolean y) {
            return Order.compare$mcZ$sp$(this, x, y);
         }

         public int compare$mcB$sp(final byte x, final byte y) {
            return Order.compare$mcB$sp$(this, x, y);
         }

         public int compare$mcC$sp(final char x, final char y) {
            return Order.compare$mcC$sp$(this, x, y);
         }

         public int compare$mcD$sp(final double x, final double y) {
            return Order.compare$mcD$sp$(this, x, y);
         }

         public int compare$mcF$sp(final float x, final float y) {
            return Order.compare$mcF$sp$(this, x, y);
         }

         public int compare$mcI$sp(final int x, final int y) {
            return Order.compare$mcI$sp$(this, x, y);
         }

         public int compare$mcJ$sp(final long x, final long y) {
            return Order.compare$mcJ$sp$(this, x, y);
         }

         public int compare$mcS$sp(final short x, final short y) {
            return Order.compare$mcS$sp$(this, x, y);
         }

         public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.compare$mcV$sp$(this, x, y);
         }

         public Comparison comparison(final Object x, final Object y) {
            return Order.comparison$(this, x, y);
         }

         public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
            return Order.comparison$mcZ$sp$(this, x, y);
         }

         public Comparison comparison$mcB$sp(final byte x, final byte y) {
            return Order.comparison$mcB$sp$(this, x, y);
         }

         public Comparison comparison$mcC$sp(final char x, final char y) {
            return Order.comparison$mcC$sp$(this, x, y);
         }

         public Comparison comparison$mcD$sp(final double x, final double y) {
            return Order.comparison$mcD$sp$(this, x, y);
         }

         public Comparison comparison$mcF$sp(final float x, final float y) {
            return Order.comparison$mcF$sp$(this, x, y);
         }

         public Comparison comparison$mcI$sp(final int x, final int y) {
            return Order.comparison$mcI$sp$(this, x, y);
         }

         public Comparison comparison$mcJ$sp(final long x, final long y) {
            return Order.comparison$mcJ$sp$(this, x, y);
         }

         public Comparison comparison$mcS$sp(final short x, final short y) {
            return Order.comparison$mcS$sp$(this, x, y);
         }

         public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.comparison$mcV$sp$(this, x, y);
         }

         public double partialCompare(final Object x, final Object y) {
            return Order.partialCompare$(this, x, y);
         }

         public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
            return Order.partialCompare$mcZ$sp$(this, x, y);
         }

         public double partialCompare$mcB$sp(final byte x, final byte y) {
            return Order.partialCompare$mcB$sp$(this, x, y);
         }

         public double partialCompare$mcC$sp(final char x, final char y) {
            return Order.partialCompare$mcC$sp$(this, x, y);
         }

         public double partialCompare$mcD$sp(final double x, final double y) {
            return Order.partialCompare$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return Order.partialCompare$mcF$sp$(this, x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            return Order.partialCompare$mcI$sp$(this, x, y);
         }

         public double partialCompare$mcJ$sp(final long x, final long y) {
            return Order.partialCompare$mcJ$sp$(this, x, y);
         }

         public double partialCompare$mcS$sp(final short x, final short y) {
            return Order.partialCompare$mcS$sp$(this, x, y);
         }

         public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.partialCompare$mcV$sp$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Order.min$(this, x, y);
         }

         public boolean min$mcZ$sp(final boolean x, final boolean y) {
            return Order.min$mcZ$sp$(this, x, y);
         }

         public byte min$mcB$sp(final byte x, final byte y) {
            return Order.min$mcB$sp$(this, x, y);
         }

         public char min$mcC$sp(final char x, final char y) {
            return Order.min$mcC$sp$(this, x, y);
         }

         public double min$mcD$sp(final double x, final double y) {
            return Order.min$mcD$sp$(this, x, y);
         }

         public float min$mcF$sp(final float x, final float y) {
            return Order.min$mcF$sp$(this, x, y);
         }

         public int min$mcI$sp(final int x, final int y) {
            return Order.min$mcI$sp$(this, x, y);
         }

         public long min$mcJ$sp(final long x, final long y) {
            return Order.min$mcJ$sp$(this, x, y);
         }

         public short min$mcS$sp(final short x, final short y) {
            return Order.min$mcS$sp$(this, x, y);
         }

         public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.min$mcV$sp$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Order.max$(this, x, y);
         }

         public boolean max$mcZ$sp(final boolean x, final boolean y) {
            return Order.max$mcZ$sp$(this, x, y);
         }

         public byte max$mcB$sp(final byte x, final byte y) {
            return Order.max$mcB$sp$(this, x, y);
         }

         public char max$mcC$sp(final char x, final char y) {
            return Order.max$mcC$sp$(this, x, y);
         }

         public double max$mcD$sp(final double x, final double y) {
            return Order.max$mcD$sp$(this, x, y);
         }

         public float max$mcF$sp(final float x, final float y) {
            return Order.max$mcF$sp$(this, x, y);
         }

         public int max$mcI$sp(final int x, final int y) {
            return Order.max$mcI$sp$(this, x, y);
         }

         public long max$mcJ$sp(final long x, final long y) {
            return Order.max$mcJ$sp$(this, x, y);
         }

         public short max$mcS$sp(final short x, final short y) {
            return Order.max$mcS$sp$(this, x, y);
         }

         public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.max$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return Order.eqv$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Order.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Order.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Order.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Order.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Order.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Order.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Order.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Order.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Order.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Order.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Order.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Order.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Order.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Order.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Order.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.neqv$mcV$sp$(this, x, y);
         }

         public boolean lteqv(final Object x, final Object y) {
            return Order.lteqv$(this, x, y);
         }

         public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.lteqv$mcZ$sp$(this, x, y);
         }

         public boolean lteqv$mcB$sp(final byte x, final byte y) {
            return Order.lteqv$mcB$sp$(this, x, y);
         }

         public boolean lteqv$mcC$sp(final char x, final char y) {
            return Order.lteqv$mcC$sp$(this, x, y);
         }

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return Order.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return Order.lteqv$mcF$sp$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return Order.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv$mcJ$sp(final long x, final long y) {
            return Order.lteqv$mcJ$sp$(this, x, y);
         }

         public boolean lteqv$mcS$sp(final short x, final short y) {
            return Order.lteqv$mcS$sp$(this, x, y);
         }

         public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lteqv$mcV$sp$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Order.lt$(this, x, y);
         }

         public boolean lt$mcZ$sp(final boolean x, final boolean y) {
            return Order.lt$mcZ$sp$(this, x, y);
         }

         public boolean lt$mcB$sp(final byte x, final byte y) {
            return Order.lt$mcB$sp$(this, x, y);
         }

         public boolean lt$mcC$sp(final char x, final char y) {
            return Order.lt$mcC$sp$(this, x, y);
         }

         public boolean lt$mcD$sp(final double x, final double y) {
            return Order.lt$mcD$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return Order.lt$mcF$sp$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return Order.lt$mcI$sp$(this, x, y);
         }

         public boolean lt$mcJ$sp(final long x, final long y) {
            return Order.lt$mcJ$sp$(this, x, y);
         }

         public boolean lt$mcS$sp(final short x, final short y) {
            return Order.lt$mcS$sp$(this, x, y);
         }

         public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lt$mcV$sp$(this, x, y);
         }

         public boolean gteqv(final Object x, final Object y) {
            return Order.gteqv$(this, x, y);
         }

         public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.gteqv$mcZ$sp$(this, x, y);
         }

         public boolean gteqv$mcB$sp(final byte x, final byte y) {
            return Order.gteqv$mcB$sp$(this, x, y);
         }

         public boolean gteqv$mcC$sp(final char x, final char y) {
            return Order.gteqv$mcC$sp$(this, x, y);
         }

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return Order.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return Order.gteqv$mcF$sp$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return Order.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gteqv$mcJ$sp(final long x, final long y) {
            return Order.gteqv$mcJ$sp$(this, x, y);
         }

         public boolean gteqv$mcS$sp(final short x, final short y) {
            return Order.gteqv$mcS$sp$(this, x, y);
         }

         public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gteqv$mcV$sp$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Order.gt$(this, x, y);
         }

         public boolean gt$mcZ$sp(final boolean x, final boolean y) {
            return Order.gt$mcZ$sp$(this, x, y);
         }

         public boolean gt$mcB$sp(final byte x, final byte y) {
            return Order.gt$mcB$sp$(this, x, y);
         }

         public boolean gt$mcC$sp(final char x, final char y) {
            return Order.gt$mcC$sp$(this, x, y);
         }

         public boolean gt$mcD$sp(final double x, final double y) {
            return Order.gt$mcD$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return Order.gt$mcF$sp$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return Order.gt$mcI$sp$(this, x, y);
         }

         public boolean gt$mcJ$sp(final long x, final long y) {
            return Order.gt$mcJ$sp$(this, x, y);
         }

         public boolean gt$mcS$sp(final short x, final short y) {
            return Order.gt$mcS$sp$(this, x, y);
         }

         public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gt$mcV$sp$(this, x, y);
         }

         public Ordering toOrdering() {
            return Order.toOrdering$(this);
         }

         public Option partialComparison(final Object x, final Object y) {
            return PartialOrder.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
         }

         public Option partialComparison$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialComparison$mcB$sp$(this, x, y);
         }

         public Option partialComparison$mcC$sp(final char x, final char y) {
            return PartialOrder.partialComparison$mcC$sp$(this, x, y);
         }

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder.partialComparison$mcD$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder.partialComparison$mcI$sp$(this, x, y);
         }

         public Option partialComparison$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
         }

         public Option partialComparison$mcS$sp(final short x, final short y) {
            return PartialOrder.partialComparison$mcS$sp$(this, x, y);
         }

         public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialComparison$mcV$sp$(this, x, y);
         }

         public Option tryCompare(final Object x, final Object y) {
            return PartialOrder.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
         }

         public Option tryCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.tryCompare$mcB$sp$(this, x, y);
         }

         public Option tryCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.tryCompare$mcC$sp$(this, x, y);
         }

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.tryCompare$mcD$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.tryCompare$mcI$sp$(this, x, y);
         }

         public Option tryCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
         }

         public Option tryCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.tryCompare$mcS$sp$(this, x, y);
         }

         public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.tryCompare$mcV$sp$(this, x, y);
         }

         public Option pmin(final Object x, final Object y) {
            return PartialOrder.pmin$(this, x, y);
         }

         public Option pmin$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmin$mcZ$sp$(this, x, y);
         }

         public Option pmin$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmin$mcB$sp$(this, x, y);
         }

         public Option pmin$mcC$sp(final char x, final char y) {
            return PartialOrder.pmin$mcC$sp$(this, x, y);
         }

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder.pmin$mcD$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder.pmin$mcI$sp$(this, x, y);
         }

         public Option pmin$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmin$mcJ$sp$(this, x, y);
         }

         public Option pmin$mcS$sp(final short x, final short y) {
            return PartialOrder.pmin$mcS$sp$(this, x, y);
         }

         public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmin$mcV$sp$(this, x, y);
         }

         public Option pmax(final Object x, final Object y) {
            return PartialOrder.pmax$(this, x, y);
         }

         public Option pmax$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmax$mcZ$sp$(this, x, y);
         }

         public Option pmax$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmax$mcB$sp$(this, x, y);
         }

         public Option pmax$mcC$sp(final char x, final char y) {
            return PartialOrder.pmax$mcC$sp$(this, x, y);
         }

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder.pmax$mcD$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder.pmax$mcI$sp$(this, x, y);
         }

         public Option pmax$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmax$mcJ$sp$(this, x, y);
         }

         public Option pmax$mcS$sp(final short x, final short y) {
            return PartialOrder.pmax$mcS$sp$(this, x, y);
         }

         public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmax$mcV$sp$(this, x, y);
         }

         public int compare(final Tuple18 x, final Tuple18 y) {
            return BoxesRunTime.unboxToInt(.MODULE$.find$extension(scala.Predef..MODULE$.intArrayOps(new int[]{this.A0$502.compare(x._1(), y._1()), this.A1$479.compare(x._2(), y._2()), this.A2$456.compare(x._3(), y._3()), this.A3$433.compare(x._4(), y._4()), this.A4$410.compare(x._5(), y._5()), this.A5$387.compare(x._6(), y._6()), this.A6$364.compare(x._7(), y._7()), this.A7$341.compare(x._8(), y._8()), this.A8$318.compare(x._9(), y._9()), this.A9$295.compare(x._10(), y._10()), this.A10$272.compare(x._11(), y._11()), this.A11$249.compare(x._12(), y._12()), this.A12$226.compare(x._13(), y._13()), this.A13$203.compare(x._14(), y._14()), this.A14$180.compare(x._15(), y._15()), this.A15$157.compare(x._16(), y._16()), this.A16$134.compare(x._17(), y._17()), this.A17$111.compare(x._18(), y._18())}), (JFunction1.mcZI.sp)(x$62) -> x$62 != 0).getOrElse((JFunction0.mcI.sp)() -> 0));
         }

         public {
            this.A0$502 = A0$502;
            this.A1$479 = A1$479;
            this.A2$456 = A2$456;
            this.A3$433 = A3$433;
            this.A4$410 = A4$410;
            this.A5$387 = A5$387;
            this.A6$364 = A6$364;
            this.A7$341 = A7$341;
            this.A8$318 = A8$318;
            this.A9$295 = A9$295;
            this.A10$272 = A10$272;
            this.A11$249 = A11$249;
            this.A12$226 = A12$226;
            this.A13$203 = A13$203;
            this.A14$180 = A14$180;
            this.A15$157 = A15$157;
            this.A16$134 = A16$134;
            this.A17$111 = A17$111;
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static Order catsKernelOrderForTuple19$(final TupleOrderInstances $this, final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17, final Order A18) {
      return $this.catsKernelOrderForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   default Order catsKernelOrderForTuple19(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17, final Order A18) {
      return new Order(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) {
         private final Order A0$503;
         private final Order A1$480;
         private final Order A2$457;
         private final Order A3$434;
         private final Order A4$411;
         private final Order A5$388;
         private final Order A6$365;
         private final Order A7$342;
         private final Order A8$319;
         private final Order A9$296;
         private final Order A10$273;
         private final Order A11$250;
         private final Order A12$227;
         private final Order A13$204;
         private final Order A14$181;
         private final Order A15$158;
         private final Order A16$135;
         private final Order A17$112;
         private final Order A18$89;

         public int compare$mcZ$sp(final boolean x, final boolean y) {
            return Order.compare$mcZ$sp$(this, x, y);
         }

         public int compare$mcB$sp(final byte x, final byte y) {
            return Order.compare$mcB$sp$(this, x, y);
         }

         public int compare$mcC$sp(final char x, final char y) {
            return Order.compare$mcC$sp$(this, x, y);
         }

         public int compare$mcD$sp(final double x, final double y) {
            return Order.compare$mcD$sp$(this, x, y);
         }

         public int compare$mcF$sp(final float x, final float y) {
            return Order.compare$mcF$sp$(this, x, y);
         }

         public int compare$mcI$sp(final int x, final int y) {
            return Order.compare$mcI$sp$(this, x, y);
         }

         public int compare$mcJ$sp(final long x, final long y) {
            return Order.compare$mcJ$sp$(this, x, y);
         }

         public int compare$mcS$sp(final short x, final short y) {
            return Order.compare$mcS$sp$(this, x, y);
         }

         public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.compare$mcV$sp$(this, x, y);
         }

         public Comparison comparison(final Object x, final Object y) {
            return Order.comparison$(this, x, y);
         }

         public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
            return Order.comparison$mcZ$sp$(this, x, y);
         }

         public Comparison comparison$mcB$sp(final byte x, final byte y) {
            return Order.comparison$mcB$sp$(this, x, y);
         }

         public Comparison comparison$mcC$sp(final char x, final char y) {
            return Order.comparison$mcC$sp$(this, x, y);
         }

         public Comparison comparison$mcD$sp(final double x, final double y) {
            return Order.comparison$mcD$sp$(this, x, y);
         }

         public Comparison comparison$mcF$sp(final float x, final float y) {
            return Order.comparison$mcF$sp$(this, x, y);
         }

         public Comparison comparison$mcI$sp(final int x, final int y) {
            return Order.comparison$mcI$sp$(this, x, y);
         }

         public Comparison comparison$mcJ$sp(final long x, final long y) {
            return Order.comparison$mcJ$sp$(this, x, y);
         }

         public Comparison comparison$mcS$sp(final short x, final short y) {
            return Order.comparison$mcS$sp$(this, x, y);
         }

         public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.comparison$mcV$sp$(this, x, y);
         }

         public double partialCompare(final Object x, final Object y) {
            return Order.partialCompare$(this, x, y);
         }

         public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
            return Order.partialCompare$mcZ$sp$(this, x, y);
         }

         public double partialCompare$mcB$sp(final byte x, final byte y) {
            return Order.partialCompare$mcB$sp$(this, x, y);
         }

         public double partialCompare$mcC$sp(final char x, final char y) {
            return Order.partialCompare$mcC$sp$(this, x, y);
         }

         public double partialCompare$mcD$sp(final double x, final double y) {
            return Order.partialCompare$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return Order.partialCompare$mcF$sp$(this, x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            return Order.partialCompare$mcI$sp$(this, x, y);
         }

         public double partialCompare$mcJ$sp(final long x, final long y) {
            return Order.partialCompare$mcJ$sp$(this, x, y);
         }

         public double partialCompare$mcS$sp(final short x, final short y) {
            return Order.partialCompare$mcS$sp$(this, x, y);
         }

         public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.partialCompare$mcV$sp$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Order.min$(this, x, y);
         }

         public boolean min$mcZ$sp(final boolean x, final boolean y) {
            return Order.min$mcZ$sp$(this, x, y);
         }

         public byte min$mcB$sp(final byte x, final byte y) {
            return Order.min$mcB$sp$(this, x, y);
         }

         public char min$mcC$sp(final char x, final char y) {
            return Order.min$mcC$sp$(this, x, y);
         }

         public double min$mcD$sp(final double x, final double y) {
            return Order.min$mcD$sp$(this, x, y);
         }

         public float min$mcF$sp(final float x, final float y) {
            return Order.min$mcF$sp$(this, x, y);
         }

         public int min$mcI$sp(final int x, final int y) {
            return Order.min$mcI$sp$(this, x, y);
         }

         public long min$mcJ$sp(final long x, final long y) {
            return Order.min$mcJ$sp$(this, x, y);
         }

         public short min$mcS$sp(final short x, final short y) {
            return Order.min$mcS$sp$(this, x, y);
         }

         public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.min$mcV$sp$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Order.max$(this, x, y);
         }

         public boolean max$mcZ$sp(final boolean x, final boolean y) {
            return Order.max$mcZ$sp$(this, x, y);
         }

         public byte max$mcB$sp(final byte x, final byte y) {
            return Order.max$mcB$sp$(this, x, y);
         }

         public char max$mcC$sp(final char x, final char y) {
            return Order.max$mcC$sp$(this, x, y);
         }

         public double max$mcD$sp(final double x, final double y) {
            return Order.max$mcD$sp$(this, x, y);
         }

         public float max$mcF$sp(final float x, final float y) {
            return Order.max$mcF$sp$(this, x, y);
         }

         public int max$mcI$sp(final int x, final int y) {
            return Order.max$mcI$sp$(this, x, y);
         }

         public long max$mcJ$sp(final long x, final long y) {
            return Order.max$mcJ$sp$(this, x, y);
         }

         public short max$mcS$sp(final short x, final short y) {
            return Order.max$mcS$sp$(this, x, y);
         }

         public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.max$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return Order.eqv$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Order.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Order.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Order.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Order.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Order.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Order.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Order.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Order.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Order.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Order.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Order.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Order.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Order.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Order.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Order.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.neqv$mcV$sp$(this, x, y);
         }

         public boolean lteqv(final Object x, final Object y) {
            return Order.lteqv$(this, x, y);
         }

         public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.lteqv$mcZ$sp$(this, x, y);
         }

         public boolean lteqv$mcB$sp(final byte x, final byte y) {
            return Order.lteqv$mcB$sp$(this, x, y);
         }

         public boolean lteqv$mcC$sp(final char x, final char y) {
            return Order.lteqv$mcC$sp$(this, x, y);
         }

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return Order.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return Order.lteqv$mcF$sp$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return Order.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv$mcJ$sp(final long x, final long y) {
            return Order.lteqv$mcJ$sp$(this, x, y);
         }

         public boolean lteqv$mcS$sp(final short x, final short y) {
            return Order.lteqv$mcS$sp$(this, x, y);
         }

         public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lteqv$mcV$sp$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Order.lt$(this, x, y);
         }

         public boolean lt$mcZ$sp(final boolean x, final boolean y) {
            return Order.lt$mcZ$sp$(this, x, y);
         }

         public boolean lt$mcB$sp(final byte x, final byte y) {
            return Order.lt$mcB$sp$(this, x, y);
         }

         public boolean lt$mcC$sp(final char x, final char y) {
            return Order.lt$mcC$sp$(this, x, y);
         }

         public boolean lt$mcD$sp(final double x, final double y) {
            return Order.lt$mcD$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return Order.lt$mcF$sp$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return Order.lt$mcI$sp$(this, x, y);
         }

         public boolean lt$mcJ$sp(final long x, final long y) {
            return Order.lt$mcJ$sp$(this, x, y);
         }

         public boolean lt$mcS$sp(final short x, final short y) {
            return Order.lt$mcS$sp$(this, x, y);
         }

         public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lt$mcV$sp$(this, x, y);
         }

         public boolean gteqv(final Object x, final Object y) {
            return Order.gteqv$(this, x, y);
         }

         public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.gteqv$mcZ$sp$(this, x, y);
         }

         public boolean gteqv$mcB$sp(final byte x, final byte y) {
            return Order.gteqv$mcB$sp$(this, x, y);
         }

         public boolean gteqv$mcC$sp(final char x, final char y) {
            return Order.gteqv$mcC$sp$(this, x, y);
         }

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return Order.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return Order.gteqv$mcF$sp$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return Order.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gteqv$mcJ$sp(final long x, final long y) {
            return Order.gteqv$mcJ$sp$(this, x, y);
         }

         public boolean gteqv$mcS$sp(final short x, final short y) {
            return Order.gteqv$mcS$sp$(this, x, y);
         }

         public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gteqv$mcV$sp$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Order.gt$(this, x, y);
         }

         public boolean gt$mcZ$sp(final boolean x, final boolean y) {
            return Order.gt$mcZ$sp$(this, x, y);
         }

         public boolean gt$mcB$sp(final byte x, final byte y) {
            return Order.gt$mcB$sp$(this, x, y);
         }

         public boolean gt$mcC$sp(final char x, final char y) {
            return Order.gt$mcC$sp$(this, x, y);
         }

         public boolean gt$mcD$sp(final double x, final double y) {
            return Order.gt$mcD$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return Order.gt$mcF$sp$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return Order.gt$mcI$sp$(this, x, y);
         }

         public boolean gt$mcJ$sp(final long x, final long y) {
            return Order.gt$mcJ$sp$(this, x, y);
         }

         public boolean gt$mcS$sp(final short x, final short y) {
            return Order.gt$mcS$sp$(this, x, y);
         }

         public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gt$mcV$sp$(this, x, y);
         }

         public Ordering toOrdering() {
            return Order.toOrdering$(this);
         }

         public Option partialComparison(final Object x, final Object y) {
            return PartialOrder.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
         }

         public Option partialComparison$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialComparison$mcB$sp$(this, x, y);
         }

         public Option partialComparison$mcC$sp(final char x, final char y) {
            return PartialOrder.partialComparison$mcC$sp$(this, x, y);
         }

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder.partialComparison$mcD$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder.partialComparison$mcI$sp$(this, x, y);
         }

         public Option partialComparison$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
         }

         public Option partialComparison$mcS$sp(final short x, final short y) {
            return PartialOrder.partialComparison$mcS$sp$(this, x, y);
         }

         public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialComparison$mcV$sp$(this, x, y);
         }

         public Option tryCompare(final Object x, final Object y) {
            return PartialOrder.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
         }

         public Option tryCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.tryCompare$mcB$sp$(this, x, y);
         }

         public Option tryCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.tryCompare$mcC$sp$(this, x, y);
         }

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.tryCompare$mcD$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.tryCompare$mcI$sp$(this, x, y);
         }

         public Option tryCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
         }

         public Option tryCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.tryCompare$mcS$sp$(this, x, y);
         }

         public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.tryCompare$mcV$sp$(this, x, y);
         }

         public Option pmin(final Object x, final Object y) {
            return PartialOrder.pmin$(this, x, y);
         }

         public Option pmin$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmin$mcZ$sp$(this, x, y);
         }

         public Option pmin$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmin$mcB$sp$(this, x, y);
         }

         public Option pmin$mcC$sp(final char x, final char y) {
            return PartialOrder.pmin$mcC$sp$(this, x, y);
         }

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder.pmin$mcD$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder.pmin$mcI$sp$(this, x, y);
         }

         public Option pmin$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmin$mcJ$sp$(this, x, y);
         }

         public Option pmin$mcS$sp(final short x, final short y) {
            return PartialOrder.pmin$mcS$sp$(this, x, y);
         }

         public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmin$mcV$sp$(this, x, y);
         }

         public Option pmax(final Object x, final Object y) {
            return PartialOrder.pmax$(this, x, y);
         }

         public Option pmax$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmax$mcZ$sp$(this, x, y);
         }

         public Option pmax$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmax$mcB$sp$(this, x, y);
         }

         public Option pmax$mcC$sp(final char x, final char y) {
            return PartialOrder.pmax$mcC$sp$(this, x, y);
         }

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder.pmax$mcD$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder.pmax$mcI$sp$(this, x, y);
         }

         public Option pmax$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmax$mcJ$sp$(this, x, y);
         }

         public Option pmax$mcS$sp(final short x, final short y) {
            return PartialOrder.pmax$mcS$sp$(this, x, y);
         }

         public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmax$mcV$sp$(this, x, y);
         }

         public int compare(final Tuple19 x, final Tuple19 y) {
            return BoxesRunTime.unboxToInt(.MODULE$.find$extension(scala.Predef..MODULE$.intArrayOps(new int[]{this.A0$503.compare(x._1(), y._1()), this.A1$480.compare(x._2(), y._2()), this.A2$457.compare(x._3(), y._3()), this.A3$434.compare(x._4(), y._4()), this.A4$411.compare(x._5(), y._5()), this.A5$388.compare(x._6(), y._6()), this.A6$365.compare(x._7(), y._7()), this.A7$342.compare(x._8(), y._8()), this.A8$319.compare(x._9(), y._9()), this.A9$296.compare(x._10(), y._10()), this.A10$273.compare(x._11(), y._11()), this.A11$250.compare(x._12(), y._12()), this.A12$227.compare(x._13(), y._13()), this.A13$204.compare(x._14(), y._14()), this.A14$181.compare(x._15(), y._15()), this.A15$158.compare(x._16(), y._16()), this.A16$135.compare(x._17(), y._17()), this.A17$112.compare(x._18(), y._18()), this.A18$89.compare(x._19(), y._19())}), (JFunction1.mcZI.sp)(x$63) -> x$63 != 0).getOrElse((JFunction0.mcI.sp)() -> 0));
         }

         public {
            this.A0$503 = A0$503;
            this.A1$480 = A1$480;
            this.A2$457 = A2$457;
            this.A3$434 = A3$434;
            this.A4$411 = A4$411;
            this.A5$388 = A5$388;
            this.A6$365 = A6$365;
            this.A7$342 = A7$342;
            this.A8$319 = A8$319;
            this.A9$296 = A9$296;
            this.A10$273 = A10$273;
            this.A11$250 = A11$250;
            this.A12$227 = A12$227;
            this.A13$204 = A13$204;
            this.A14$181 = A14$181;
            this.A15$158 = A15$158;
            this.A16$135 = A16$135;
            this.A17$112 = A17$112;
            this.A18$89 = A18$89;
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static Order catsKernelOrderForTuple20$(final TupleOrderInstances $this, final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17, final Order A18, final Order A19) {
      return $this.catsKernelOrderForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   default Order catsKernelOrderForTuple20(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17, final Order A18, final Order A19) {
      return new Order(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) {
         private final Order A0$504;
         private final Order A1$481;
         private final Order A2$458;
         private final Order A3$435;
         private final Order A4$412;
         private final Order A5$389;
         private final Order A6$366;
         private final Order A7$343;
         private final Order A8$320;
         private final Order A9$297;
         private final Order A10$274;
         private final Order A11$251;
         private final Order A12$228;
         private final Order A13$205;
         private final Order A14$182;
         private final Order A15$159;
         private final Order A16$136;
         private final Order A17$113;
         private final Order A18$90;
         private final Order A19$67;

         public int compare$mcZ$sp(final boolean x, final boolean y) {
            return Order.compare$mcZ$sp$(this, x, y);
         }

         public int compare$mcB$sp(final byte x, final byte y) {
            return Order.compare$mcB$sp$(this, x, y);
         }

         public int compare$mcC$sp(final char x, final char y) {
            return Order.compare$mcC$sp$(this, x, y);
         }

         public int compare$mcD$sp(final double x, final double y) {
            return Order.compare$mcD$sp$(this, x, y);
         }

         public int compare$mcF$sp(final float x, final float y) {
            return Order.compare$mcF$sp$(this, x, y);
         }

         public int compare$mcI$sp(final int x, final int y) {
            return Order.compare$mcI$sp$(this, x, y);
         }

         public int compare$mcJ$sp(final long x, final long y) {
            return Order.compare$mcJ$sp$(this, x, y);
         }

         public int compare$mcS$sp(final short x, final short y) {
            return Order.compare$mcS$sp$(this, x, y);
         }

         public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.compare$mcV$sp$(this, x, y);
         }

         public Comparison comparison(final Object x, final Object y) {
            return Order.comparison$(this, x, y);
         }

         public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
            return Order.comparison$mcZ$sp$(this, x, y);
         }

         public Comparison comparison$mcB$sp(final byte x, final byte y) {
            return Order.comparison$mcB$sp$(this, x, y);
         }

         public Comparison comparison$mcC$sp(final char x, final char y) {
            return Order.comparison$mcC$sp$(this, x, y);
         }

         public Comparison comparison$mcD$sp(final double x, final double y) {
            return Order.comparison$mcD$sp$(this, x, y);
         }

         public Comparison comparison$mcF$sp(final float x, final float y) {
            return Order.comparison$mcF$sp$(this, x, y);
         }

         public Comparison comparison$mcI$sp(final int x, final int y) {
            return Order.comparison$mcI$sp$(this, x, y);
         }

         public Comparison comparison$mcJ$sp(final long x, final long y) {
            return Order.comparison$mcJ$sp$(this, x, y);
         }

         public Comparison comparison$mcS$sp(final short x, final short y) {
            return Order.comparison$mcS$sp$(this, x, y);
         }

         public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.comparison$mcV$sp$(this, x, y);
         }

         public double partialCompare(final Object x, final Object y) {
            return Order.partialCompare$(this, x, y);
         }

         public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
            return Order.partialCompare$mcZ$sp$(this, x, y);
         }

         public double partialCompare$mcB$sp(final byte x, final byte y) {
            return Order.partialCompare$mcB$sp$(this, x, y);
         }

         public double partialCompare$mcC$sp(final char x, final char y) {
            return Order.partialCompare$mcC$sp$(this, x, y);
         }

         public double partialCompare$mcD$sp(final double x, final double y) {
            return Order.partialCompare$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return Order.partialCompare$mcF$sp$(this, x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            return Order.partialCompare$mcI$sp$(this, x, y);
         }

         public double partialCompare$mcJ$sp(final long x, final long y) {
            return Order.partialCompare$mcJ$sp$(this, x, y);
         }

         public double partialCompare$mcS$sp(final short x, final short y) {
            return Order.partialCompare$mcS$sp$(this, x, y);
         }

         public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.partialCompare$mcV$sp$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Order.min$(this, x, y);
         }

         public boolean min$mcZ$sp(final boolean x, final boolean y) {
            return Order.min$mcZ$sp$(this, x, y);
         }

         public byte min$mcB$sp(final byte x, final byte y) {
            return Order.min$mcB$sp$(this, x, y);
         }

         public char min$mcC$sp(final char x, final char y) {
            return Order.min$mcC$sp$(this, x, y);
         }

         public double min$mcD$sp(final double x, final double y) {
            return Order.min$mcD$sp$(this, x, y);
         }

         public float min$mcF$sp(final float x, final float y) {
            return Order.min$mcF$sp$(this, x, y);
         }

         public int min$mcI$sp(final int x, final int y) {
            return Order.min$mcI$sp$(this, x, y);
         }

         public long min$mcJ$sp(final long x, final long y) {
            return Order.min$mcJ$sp$(this, x, y);
         }

         public short min$mcS$sp(final short x, final short y) {
            return Order.min$mcS$sp$(this, x, y);
         }

         public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.min$mcV$sp$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Order.max$(this, x, y);
         }

         public boolean max$mcZ$sp(final boolean x, final boolean y) {
            return Order.max$mcZ$sp$(this, x, y);
         }

         public byte max$mcB$sp(final byte x, final byte y) {
            return Order.max$mcB$sp$(this, x, y);
         }

         public char max$mcC$sp(final char x, final char y) {
            return Order.max$mcC$sp$(this, x, y);
         }

         public double max$mcD$sp(final double x, final double y) {
            return Order.max$mcD$sp$(this, x, y);
         }

         public float max$mcF$sp(final float x, final float y) {
            return Order.max$mcF$sp$(this, x, y);
         }

         public int max$mcI$sp(final int x, final int y) {
            return Order.max$mcI$sp$(this, x, y);
         }

         public long max$mcJ$sp(final long x, final long y) {
            return Order.max$mcJ$sp$(this, x, y);
         }

         public short max$mcS$sp(final short x, final short y) {
            return Order.max$mcS$sp$(this, x, y);
         }

         public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.max$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return Order.eqv$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Order.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Order.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Order.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Order.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Order.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Order.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Order.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Order.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Order.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Order.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Order.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Order.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Order.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Order.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Order.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.neqv$mcV$sp$(this, x, y);
         }

         public boolean lteqv(final Object x, final Object y) {
            return Order.lteqv$(this, x, y);
         }

         public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.lteqv$mcZ$sp$(this, x, y);
         }

         public boolean lteqv$mcB$sp(final byte x, final byte y) {
            return Order.lteqv$mcB$sp$(this, x, y);
         }

         public boolean lteqv$mcC$sp(final char x, final char y) {
            return Order.lteqv$mcC$sp$(this, x, y);
         }

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return Order.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return Order.lteqv$mcF$sp$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return Order.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv$mcJ$sp(final long x, final long y) {
            return Order.lteqv$mcJ$sp$(this, x, y);
         }

         public boolean lteqv$mcS$sp(final short x, final short y) {
            return Order.lteqv$mcS$sp$(this, x, y);
         }

         public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lteqv$mcV$sp$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Order.lt$(this, x, y);
         }

         public boolean lt$mcZ$sp(final boolean x, final boolean y) {
            return Order.lt$mcZ$sp$(this, x, y);
         }

         public boolean lt$mcB$sp(final byte x, final byte y) {
            return Order.lt$mcB$sp$(this, x, y);
         }

         public boolean lt$mcC$sp(final char x, final char y) {
            return Order.lt$mcC$sp$(this, x, y);
         }

         public boolean lt$mcD$sp(final double x, final double y) {
            return Order.lt$mcD$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return Order.lt$mcF$sp$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return Order.lt$mcI$sp$(this, x, y);
         }

         public boolean lt$mcJ$sp(final long x, final long y) {
            return Order.lt$mcJ$sp$(this, x, y);
         }

         public boolean lt$mcS$sp(final short x, final short y) {
            return Order.lt$mcS$sp$(this, x, y);
         }

         public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lt$mcV$sp$(this, x, y);
         }

         public boolean gteqv(final Object x, final Object y) {
            return Order.gteqv$(this, x, y);
         }

         public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.gteqv$mcZ$sp$(this, x, y);
         }

         public boolean gteqv$mcB$sp(final byte x, final byte y) {
            return Order.gteqv$mcB$sp$(this, x, y);
         }

         public boolean gteqv$mcC$sp(final char x, final char y) {
            return Order.gteqv$mcC$sp$(this, x, y);
         }

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return Order.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return Order.gteqv$mcF$sp$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return Order.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gteqv$mcJ$sp(final long x, final long y) {
            return Order.gteqv$mcJ$sp$(this, x, y);
         }

         public boolean gteqv$mcS$sp(final short x, final short y) {
            return Order.gteqv$mcS$sp$(this, x, y);
         }

         public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gteqv$mcV$sp$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Order.gt$(this, x, y);
         }

         public boolean gt$mcZ$sp(final boolean x, final boolean y) {
            return Order.gt$mcZ$sp$(this, x, y);
         }

         public boolean gt$mcB$sp(final byte x, final byte y) {
            return Order.gt$mcB$sp$(this, x, y);
         }

         public boolean gt$mcC$sp(final char x, final char y) {
            return Order.gt$mcC$sp$(this, x, y);
         }

         public boolean gt$mcD$sp(final double x, final double y) {
            return Order.gt$mcD$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return Order.gt$mcF$sp$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return Order.gt$mcI$sp$(this, x, y);
         }

         public boolean gt$mcJ$sp(final long x, final long y) {
            return Order.gt$mcJ$sp$(this, x, y);
         }

         public boolean gt$mcS$sp(final short x, final short y) {
            return Order.gt$mcS$sp$(this, x, y);
         }

         public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gt$mcV$sp$(this, x, y);
         }

         public Ordering toOrdering() {
            return Order.toOrdering$(this);
         }

         public Option partialComparison(final Object x, final Object y) {
            return PartialOrder.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
         }

         public Option partialComparison$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialComparison$mcB$sp$(this, x, y);
         }

         public Option partialComparison$mcC$sp(final char x, final char y) {
            return PartialOrder.partialComparison$mcC$sp$(this, x, y);
         }

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder.partialComparison$mcD$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder.partialComparison$mcI$sp$(this, x, y);
         }

         public Option partialComparison$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
         }

         public Option partialComparison$mcS$sp(final short x, final short y) {
            return PartialOrder.partialComparison$mcS$sp$(this, x, y);
         }

         public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialComparison$mcV$sp$(this, x, y);
         }

         public Option tryCompare(final Object x, final Object y) {
            return PartialOrder.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
         }

         public Option tryCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.tryCompare$mcB$sp$(this, x, y);
         }

         public Option tryCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.tryCompare$mcC$sp$(this, x, y);
         }

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.tryCompare$mcD$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.tryCompare$mcI$sp$(this, x, y);
         }

         public Option tryCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
         }

         public Option tryCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.tryCompare$mcS$sp$(this, x, y);
         }

         public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.tryCompare$mcV$sp$(this, x, y);
         }

         public Option pmin(final Object x, final Object y) {
            return PartialOrder.pmin$(this, x, y);
         }

         public Option pmin$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmin$mcZ$sp$(this, x, y);
         }

         public Option pmin$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmin$mcB$sp$(this, x, y);
         }

         public Option pmin$mcC$sp(final char x, final char y) {
            return PartialOrder.pmin$mcC$sp$(this, x, y);
         }

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder.pmin$mcD$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder.pmin$mcI$sp$(this, x, y);
         }

         public Option pmin$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmin$mcJ$sp$(this, x, y);
         }

         public Option pmin$mcS$sp(final short x, final short y) {
            return PartialOrder.pmin$mcS$sp$(this, x, y);
         }

         public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmin$mcV$sp$(this, x, y);
         }

         public Option pmax(final Object x, final Object y) {
            return PartialOrder.pmax$(this, x, y);
         }

         public Option pmax$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmax$mcZ$sp$(this, x, y);
         }

         public Option pmax$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmax$mcB$sp$(this, x, y);
         }

         public Option pmax$mcC$sp(final char x, final char y) {
            return PartialOrder.pmax$mcC$sp$(this, x, y);
         }

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder.pmax$mcD$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder.pmax$mcI$sp$(this, x, y);
         }

         public Option pmax$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmax$mcJ$sp$(this, x, y);
         }

         public Option pmax$mcS$sp(final short x, final short y) {
            return PartialOrder.pmax$mcS$sp$(this, x, y);
         }

         public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmax$mcV$sp$(this, x, y);
         }

         public int compare(final Tuple20 x, final Tuple20 y) {
            return BoxesRunTime.unboxToInt(.MODULE$.find$extension(scala.Predef..MODULE$.intArrayOps(new int[]{this.A0$504.compare(x._1(), y._1()), this.A1$481.compare(x._2(), y._2()), this.A2$458.compare(x._3(), y._3()), this.A3$435.compare(x._4(), y._4()), this.A4$412.compare(x._5(), y._5()), this.A5$389.compare(x._6(), y._6()), this.A6$366.compare(x._7(), y._7()), this.A7$343.compare(x._8(), y._8()), this.A8$320.compare(x._9(), y._9()), this.A9$297.compare(x._10(), y._10()), this.A10$274.compare(x._11(), y._11()), this.A11$251.compare(x._12(), y._12()), this.A12$228.compare(x._13(), y._13()), this.A13$205.compare(x._14(), y._14()), this.A14$182.compare(x._15(), y._15()), this.A15$159.compare(x._16(), y._16()), this.A16$136.compare(x._17(), y._17()), this.A17$113.compare(x._18(), y._18()), this.A18$90.compare(x._19(), y._19()), this.A19$67.compare(x._20(), y._20())}), (JFunction1.mcZI.sp)(x$64) -> x$64 != 0).getOrElse((JFunction0.mcI.sp)() -> 0));
         }

         public {
            this.A0$504 = A0$504;
            this.A1$481 = A1$481;
            this.A2$458 = A2$458;
            this.A3$435 = A3$435;
            this.A4$412 = A4$412;
            this.A5$389 = A5$389;
            this.A6$366 = A6$366;
            this.A7$343 = A7$343;
            this.A8$320 = A8$320;
            this.A9$297 = A9$297;
            this.A10$274 = A10$274;
            this.A11$251 = A11$251;
            this.A12$228 = A12$228;
            this.A13$205 = A13$205;
            this.A14$182 = A14$182;
            this.A15$159 = A15$159;
            this.A16$136 = A16$136;
            this.A17$113 = A17$113;
            this.A18$90 = A18$90;
            this.A19$67 = A19$67;
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static Order catsKernelOrderForTuple21$(final TupleOrderInstances $this, final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17, final Order A18, final Order A19, final Order A20) {
      return $this.catsKernelOrderForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   default Order catsKernelOrderForTuple21(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17, final Order A18, final Order A19, final Order A20) {
      return new Order(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) {
         private final Order A0$505;
         private final Order A1$482;
         private final Order A2$459;
         private final Order A3$436;
         private final Order A4$413;
         private final Order A5$390;
         private final Order A6$367;
         private final Order A7$344;
         private final Order A8$321;
         private final Order A9$298;
         private final Order A10$275;
         private final Order A11$252;
         private final Order A12$229;
         private final Order A13$206;
         private final Order A14$183;
         private final Order A15$160;
         private final Order A16$137;
         private final Order A17$114;
         private final Order A18$91;
         private final Order A19$68;
         private final Order A20$45;

         public int compare$mcZ$sp(final boolean x, final boolean y) {
            return Order.compare$mcZ$sp$(this, x, y);
         }

         public int compare$mcB$sp(final byte x, final byte y) {
            return Order.compare$mcB$sp$(this, x, y);
         }

         public int compare$mcC$sp(final char x, final char y) {
            return Order.compare$mcC$sp$(this, x, y);
         }

         public int compare$mcD$sp(final double x, final double y) {
            return Order.compare$mcD$sp$(this, x, y);
         }

         public int compare$mcF$sp(final float x, final float y) {
            return Order.compare$mcF$sp$(this, x, y);
         }

         public int compare$mcI$sp(final int x, final int y) {
            return Order.compare$mcI$sp$(this, x, y);
         }

         public int compare$mcJ$sp(final long x, final long y) {
            return Order.compare$mcJ$sp$(this, x, y);
         }

         public int compare$mcS$sp(final short x, final short y) {
            return Order.compare$mcS$sp$(this, x, y);
         }

         public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.compare$mcV$sp$(this, x, y);
         }

         public Comparison comparison(final Object x, final Object y) {
            return Order.comparison$(this, x, y);
         }

         public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
            return Order.comparison$mcZ$sp$(this, x, y);
         }

         public Comparison comparison$mcB$sp(final byte x, final byte y) {
            return Order.comparison$mcB$sp$(this, x, y);
         }

         public Comparison comparison$mcC$sp(final char x, final char y) {
            return Order.comparison$mcC$sp$(this, x, y);
         }

         public Comparison comparison$mcD$sp(final double x, final double y) {
            return Order.comparison$mcD$sp$(this, x, y);
         }

         public Comparison comparison$mcF$sp(final float x, final float y) {
            return Order.comparison$mcF$sp$(this, x, y);
         }

         public Comparison comparison$mcI$sp(final int x, final int y) {
            return Order.comparison$mcI$sp$(this, x, y);
         }

         public Comparison comparison$mcJ$sp(final long x, final long y) {
            return Order.comparison$mcJ$sp$(this, x, y);
         }

         public Comparison comparison$mcS$sp(final short x, final short y) {
            return Order.comparison$mcS$sp$(this, x, y);
         }

         public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.comparison$mcV$sp$(this, x, y);
         }

         public double partialCompare(final Object x, final Object y) {
            return Order.partialCompare$(this, x, y);
         }

         public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
            return Order.partialCompare$mcZ$sp$(this, x, y);
         }

         public double partialCompare$mcB$sp(final byte x, final byte y) {
            return Order.partialCompare$mcB$sp$(this, x, y);
         }

         public double partialCompare$mcC$sp(final char x, final char y) {
            return Order.partialCompare$mcC$sp$(this, x, y);
         }

         public double partialCompare$mcD$sp(final double x, final double y) {
            return Order.partialCompare$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return Order.partialCompare$mcF$sp$(this, x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            return Order.partialCompare$mcI$sp$(this, x, y);
         }

         public double partialCompare$mcJ$sp(final long x, final long y) {
            return Order.partialCompare$mcJ$sp$(this, x, y);
         }

         public double partialCompare$mcS$sp(final short x, final short y) {
            return Order.partialCompare$mcS$sp$(this, x, y);
         }

         public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.partialCompare$mcV$sp$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Order.min$(this, x, y);
         }

         public boolean min$mcZ$sp(final boolean x, final boolean y) {
            return Order.min$mcZ$sp$(this, x, y);
         }

         public byte min$mcB$sp(final byte x, final byte y) {
            return Order.min$mcB$sp$(this, x, y);
         }

         public char min$mcC$sp(final char x, final char y) {
            return Order.min$mcC$sp$(this, x, y);
         }

         public double min$mcD$sp(final double x, final double y) {
            return Order.min$mcD$sp$(this, x, y);
         }

         public float min$mcF$sp(final float x, final float y) {
            return Order.min$mcF$sp$(this, x, y);
         }

         public int min$mcI$sp(final int x, final int y) {
            return Order.min$mcI$sp$(this, x, y);
         }

         public long min$mcJ$sp(final long x, final long y) {
            return Order.min$mcJ$sp$(this, x, y);
         }

         public short min$mcS$sp(final short x, final short y) {
            return Order.min$mcS$sp$(this, x, y);
         }

         public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.min$mcV$sp$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Order.max$(this, x, y);
         }

         public boolean max$mcZ$sp(final boolean x, final boolean y) {
            return Order.max$mcZ$sp$(this, x, y);
         }

         public byte max$mcB$sp(final byte x, final byte y) {
            return Order.max$mcB$sp$(this, x, y);
         }

         public char max$mcC$sp(final char x, final char y) {
            return Order.max$mcC$sp$(this, x, y);
         }

         public double max$mcD$sp(final double x, final double y) {
            return Order.max$mcD$sp$(this, x, y);
         }

         public float max$mcF$sp(final float x, final float y) {
            return Order.max$mcF$sp$(this, x, y);
         }

         public int max$mcI$sp(final int x, final int y) {
            return Order.max$mcI$sp$(this, x, y);
         }

         public long max$mcJ$sp(final long x, final long y) {
            return Order.max$mcJ$sp$(this, x, y);
         }

         public short max$mcS$sp(final short x, final short y) {
            return Order.max$mcS$sp$(this, x, y);
         }

         public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.max$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return Order.eqv$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Order.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Order.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Order.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Order.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Order.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Order.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Order.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Order.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Order.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Order.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Order.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Order.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Order.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Order.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Order.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.neqv$mcV$sp$(this, x, y);
         }

         public boolean lteqv(final Object x, final Object y) {
            return Order.lteqv$(this, x, y);
         }

         public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.lteqv$mcZ$sp$(this, x, y);
         }

         public boolean lteqv$mcB$sp(final byte x, final byte y) {
            return Order.lteqv$mcB$sp$(this, x, y);
         }

         public boolean lteqv$mcC$sp(final char x, final char y) {
            return Order.lteqv$mcC$sp$(this, x, y);
         }

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return Order.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return Order.lteqv$mcF$sp$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return Order.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv$mcJ$sp(final long x, final long y) {
            return Order.lteqv$mcJ$sp$(this, x, y);
         }

         public boolean lteqv$mcS$sp(final short x, final short y) {
            return Order.lteqv$mcS$sp$(this, x, y);
         }

         public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lteqv$mcV$sp$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Order.lt$(this, x, y);
         }

         public boolean lt$mcZ$sp(final boolean x, final boolean y) {
            return Order.lt$mcZ$sp$(this, x, y);
         }

         public boolean lt$mcB$sp(final byte x, final byte y) {
            return Order.lt$mcB$sp$(this, x, y);
         }

         public boolean lt$mcC$sp(final char x, final char y) {
            return Order.lt$mcC$sp$(this, x, y);
         }

         public boolean lt$mcD$sp(final double x, final double y) {
            return Order.lt$mcD$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return Order.lt$mcF$sp$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return Order.lt$mcI$sp$(this, x, y);
         }

         public boolean lt$mcJ$sp(final long x, final long y) {
            return Order.lt$mcJ$sp$(this, x, y);
         }

         public boolean lt$mcS$sp(final short x, final short y) {
            return Order.lt$mcS$sp$(this, x, y);
         }

         public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lt$mcV$sp$(this, x, y);
         }

         public boolean gteqv(final Object x, final Object y) {
            return Order.gteqv$(this, x, y);
         }

         public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.gteqv$mcZ$sp$(this, x, y);
         }

         public boolean gteqv$mcB$sp(final byte x, final byte y) {
            return Order.gteqv$mcB$sp$(this, x, y);
         }

         public boolean gteqv$mcC$sp(final char x, final char y) {
            return Order.gteqv$mcC$sp$(this, x, y);
         }

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return Order.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return Order.gteqv$mcF$sp$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return Order.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gteqv$mcJ$sp(final long x, final long y) {
            return Order.gteqv$mcJ$sp$(this, x, y);
         }

         public boolean gteqv$mcS$sp(final short x, final short y) {
            return Order.gteqv$mcS$sp$(this, x, y);
         }

         public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gteqv$mcV$sp$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Order.gt$(this, x, y);
         }

         public boolean gt$mcZ$sp(final boolean x, final boolean y) {
            return Order.gt$mcZ$sp$(this, x, y);
         }

         public boolean gt$mcB$sp(final byte x, final byte y) {
            return Order.gt$mcB$sp$(this, x, y);
         }

         public boolean gt$mcC$sp(final char x, final char y) {
            return Order.gt$mcC$sp$(this, x, y);
         }

         public boolean gt$mcD$sp(final double x, final double y) {
            return Order.gt$mcD$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return Order.gt$mcF$sp$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return Order.gt$mcI$sp$(this, x, y);
         }

         public boolean gt$mcJ$sp(final long x, final long y) {
            return Order.gt$mcJ$sp$(this, x, y);
         }

         public boolean gt$mcS$sp(final short x, final short y) {
            return Order.gt$mcS$sp$(this, x, y);
         }

         public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gt$mcV$sp$(this, x, y);
         }

         public Ordering toOrdering() {
            return Order.toOrdering$(this);
         }

         public Option partialComparison(final Object x, final Object y) {
            return PartialOrder.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
         }

         public Option partialComparison$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialComparison$mcB$sp$(this, x, y);
         }

         public Option partialComparison$mcC$sp(final char x, final char y) {
            return PartialOrder.partialComparison$mcC$sp$(this, x, y);
         }

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder.partialComparison$mcD$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder.partialComparison$mcI$sp$(this, x, y);
         }

         public Option partialComparison$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
         }

         public Option partialComparison$mcS$sp(final short x, final short y) {
            return PartialOrder.partialComparison$mcS$sp$(this, x, y);
         }

         public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialComparison$mcV$sp$(this, x, y);
         }

         public Option tryCompare(final Object x, final Object y) {
            return PartialOrder.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
         }

         public Option tryCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.tryCompare$mcB$sp$(this, x, y);
         }

         public Option tryCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.tryCompare$mcC$sp$(this, x, y);
         }

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.tryCompare$mcD$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.tryCompare$mcI$sp$(this, x, y);
         }

         public Option tryCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
         }

         public Option tryCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.tryCompare$mcS$sp$(this, x, y);
         }

         public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.tryCompare$mcV$sp$(this, x, y);
         }

         public Option pmin(final Object x, final Object y) {
            return PartialOrder.pmin$(this, x, y);
         }

         public Option pmin$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmin$mcZ$sp$(this, x, y);
         }

         public Option pmin$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmin$mcB$sp$(this, x, y);
         }

         public Option pmin$mcC$sp(final char x, final char y) {
            return PartialOrder.pmin$mcC$sp$(this, x, y);
         }

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder.pmin$mcD$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder.pmin$mcI$sp$(this, x, y);
         }

         public Option pmin$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmin$mcJ$sp$(this, x, y);
         }

         public Option pmin$mcS$sp(final short x, final short y) {
            return PartialOrder.pmin$mcS$sp$(this, x, y);
         }

         public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmin$mcV$sp$(this, x, y);
         }

         public Option pmax(final Object x, final Object y) {
            return PartialOrder.pmax$(this, x, y);
         }

         public Option pmax$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmax$mcZ$sp$(this, x, y);
         }

         public Option pmax$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmax$mcB$sp$(this, x, y);
         }

         public Option pmax$mcC$sp(final char x, final char y) {
            return PartialOrder.pmax$mcC$sp$(this, x, y);
         }

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder.pmax$mcD$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder.pmax$mcI$sp$(this, x, y);
         }

         public Option pmax$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmax$mcJ$sp$(this, x, y);
         }

         public Option pmax$mcS$sp(final short x, final short y) {
            return PartialOrder.pmax$mcS$sp$(this, x, y);
         }

         public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmax$mcV$sp$(this, x, y);
         }

         public int compare(final Tuple21 x, final Tuple21 y) {
            return BoxesRunTime.unboxToInt(.MODULE$.find$extension(scala.Predef..MODULE$.intArrayOps(new int[]{this.A0$505.compare(x._1(), y._1()), this.A1$482.compare(x._2(), y._2()), this.A2$459.compare(x._3(), y._3()), this.A3$436.compare(x._4(), y._4()), this.A4$413.compare(x._5(), y._5()), this.A5$390.compare(x._6(), y._6()), this.A6$367.compare(x._7(), y._7()), this.A7$344.compare(x._8(), y._8()), this.A8$321.compare(x._9(), y._9()), this.A9$298.compare(x._10(), y._10()), this.A10$275.compare(x._11(), y._11()), this.A11$252.compare(x._12(), y._12()), this.A12$229.compare(x._13(), y._13()), this.A13$206.compare(x._14(), y._14()), this.A14$183.compare(x._15(), y._15()), this.A15$160.compare(x._16(), y._16()), this.A16$137.compare(x._17(), y._17()), this.A17$114.compare(x._18(), y._18()), this.A18$91.compare(x._19(), y._19()), this.A19$68.compare(x._20(), y._20()), this.A20$45.compare(x._21(), y._21())}), (JFunction1.mcZI.sp)(x$65) -> x$65 != 0).getOrElse((JFunction0.mcI.sp)() -> 0));
         }

         public {
            this.A0$505 = A0$505;
            this.A1$482 = A1$482;
            this.A2$459 = A2$459;
            this.A3$436 = A3$436;
            this.A4$413 = A4$413;
            this.A5$390 = A5$390;
            this.A6$367 = A6$367;
            this.A7$344 = A7$344;
            this.A8$321 = A8$321;
            this.A9$298 = A9$298;
            this.A10$275 = A10$275;
            this.A11$252 = A11$252;
            this.A12$229 = A12$229;
            this.A13$206 = A13$206;
            this.A14$183 = A14$183;
            this.A15$160 = A15$160;
            this.A16$137 = A16$137;
            this.A17$114 = A17$114;
            this.A18$91 = A18$91;
            this.A19$68 = A19$68;
            this.A20$45 = A20$45;
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static Order catsKernelOrderForTuple22$(final TupleOrderInstances $this, final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17, final Order A18, final Order A19, final Order A20, final Order A21) {
      return $this.catsKernelOrderForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   default Order catsKernelOrderForTuple22(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17, final Order A18, final Order A19, final Order A20, final Order A21) {
      return new Order(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) {
         private final Order A0$506;
         private final Order A1$483;
         private final Order A2$460;
         private final Order A3$437;
         private final Order A4$414;
         private final Order A5$391;
         private final Order A6$368;
         private final Order A7$345;
         private final Order A8$322;
         private final Order A9$299;
         private final Order A10$276;
         private final Order A11$253;
         private final Order A12$230;
         private final Order A13$207;
         private final Order A14$184;
         private final Order A15$161;
         private final Order A16$138;
         private final Order A17$115;
         private final Order A18$92;
         private final Order A19$69;
         private final Order A20$46;
         private final Order A21$23;

         public int compare$mcZ$sp(final boolean x, final boolean y) {
            return Order.compare$mcZ$sp$(this, x, y);
         }

         public int compare$mcB$sp(final byte x, final byte y) {
            return Order.compare$mcB$sp$(this, x, y);
         }

         public int compare$mcC$sp(final char x, final char y) {
            return Order.compare$mcC$sp$(this, x, y);
         }

         public int compare$mcD$sp(final double x, final double y) {
            return Order.compare$mcD$sp$(this, x, y);
         }

         public int compare$mcF$sp(final float x, final float y) {
            return Order.compare$mcF$sp$(this, x, y);
         }

         public int compare$mcI$sp(final int x, final int y) {
            return Order.compare$mcI$sp$(this, x, y);
         }

         public int compare$mcJ$sp(final long x, final long y) {
            return Order.compare$mcJ$sp$(this, x, y);
         }

         public int compare$mcS$sp(final short x, final short y) {
            return Order.compare$mcS$sp$(this, x, y);
         }

         public int compare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.compare$mcV$sp$(this, x, y);
         }

         public Comparison comparison(final Object x, final Object y) {
            return Order.comparison$(this, x, y);
         }

         public Comparison comparison$mcZ$sp(final boolean x, final boolean y) {
            return Order.comparison$mcZ$sp$(this, x, y);
         }

         public Comparison comparison$mcB$sp(final byte x, final byte y) {
            return Order.comparison$mcB$sp$(this, x, y);
         }

         public Comparison comparison$mcC$sp(final char x, final char y) {
            return Order.comparison$mcC$sp$(this, x, y);
         }

         public Comparison comparison$mcD$sp(final double x, final double y) {
            return Order.comparison$mcD$sp$(this, x, y);
         }

         public Comparison comparison$mcF$sp(final float x, final float y) {
            return Order.comparison$mcF$sp$(this, x, y);
         }

         public Comparison comparison$mcI$sp(final int x, final int y) {
            return Order.comparison$mcI$sp$(this, x, y);
         }

         public Comparison comparison$mcJ$sp(final long x, final long y) {
            return Order.comparison$mcJ$sp$(this, x, y);
         }

         public Comparison comparison$mcS$sp(final short x, final short y) {
            return Order.comparison$mcS$sp$(this, x, y);
         }

         public Comparison comparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.comparison$mcV$sp$(this, x, y);
         }

         public double partialCompare(final Object x, final Object y) {
            return Order.partialCompare$(this, x, y);
         }

         public double partialCompare$mcZ$sp(final boolean x, final boolean y) {
            return Order.partialCompare$mcZ$sp$(this, x, y);
         }

         public double partialCompare$mcB$sp(final byte x, final byte y) {
            return Order.partialCompare$mcB$sp$(this, x, y);
         }

         public double partialCompare$mcC$sp(final char x, final char y) {
            return Order.partialCompare$mcC$sp$(this, x, y);
         }

         public double partialCompare$mcD$sp(final double x, final double y) {
            return Order.partialCompare$mcD$sp$(this, x, y);
         }

         public double partialCompare$mcF$sp(final float x, final float y) {
            return Order.partialCompare$mcF$sp$(this, x, y);
         }

         public double partialCompare$mcI$sp(final int x, final int y) {
            return Order.partialCompare$mcI$sp$(this, x, y);
         }

         public double partialCompare$mcJ$sp(final long x, final long y) {
            return Order.partialCompare$mcJ$sp$(this, x, y);
         }

         public double partialCompare$mcS$sp(final short x, final short y) {
            return Order.partialCompare$mcS$sp$(this, x, y);
         }

         public double partialCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.partialCompare$mcV$sp$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Order.min$(this, x, y);
         }

         public boolean min$mcZ$sp(final boolean x, final boolean y) {
            return Order.min$mcZ$sp$(this, x, y);
         }

         public byte min$mcB$sp(final byte x, final byte y) {
            return Order.min$mcB$sp$(this, x, y);
         }

         public char min$mcC$sp(final char x, final char y) {
            return Order.min$mcC$sp$(this, x, y);
         }

         public double min$mcD$sp(final double x, final double y) {
            return Order.min$mcD$sp$(this, x, y);
         }

         public float min$mcF$sp(final float x, final float y) {
            return Order.min$mcF$sp$(this, x, y);
         }

         public int min$mcI$sp(final int x, final int y) {
            return Order.min$mcI$sp$(this, x, y);
         }

         public long min$mcJ$sp(final long x, final long y) {
            return Order.min$mcJ$sp$(this, x, y);
         }

         public short min$mcS$sp(final short x, final short y) {
            return Order.min$mcS$sp$(this, x, y);
         }

         public void min$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.min$mcV$sp$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Order.max$(this, x, y);
         }

         public boolean max$mcZ$sp(final boolean x, final boolean y) {
            return Order.max$mcZ$sp$(this, x, y);
         }

         public byte max$mcB$sp(final byte x, final byte y) {
            return Order.max$mcB$sp$(this, x, y);
         }

         public char max$mcC$sp(final char x, final char y) {
            return Order.max$mcC$sp$(this, x, y);
         }

         public double max$mcD$sp(final double x, final double y) {
            return Order.max$mcD$sp$(this, x, y);
         }

         public float max$mcF$sp(final float x, final float y) {
            return Order.max$mcF$sp$(this, x, y);
         }

         public int max$mcI$sp(final int x, final int y) {
            return Order.max$mcI$sp$(this, x, y);
         }

         public long max$mcJ$sp(final long x, final long y) {
            return Order.max$mcJ$sp$(this, x, y);
         }

         public short max$mcS$sp(final short x, final short y) {
            return Order.max$mcS$sp$(this, x, y);
         }

         public void max$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            Order.max$mcV$sp$(this, x, y);
         }

         public boolean eqv(final Object x, final Object y) {
            return Order.eqv$(this, x, y);
         }

         public boolean eqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.eqv$mcZ$sp$(this, x, y);
         }

         public boolean eqv$mcB$sp(final byte x, final byte y) {
            return Order.eqv$mcB$sp$(this, x, y);
         }

         public boolean eqv$mcC$sp(final char x, final char y) {
            return Order.eqv$mcC$sp$(this, x, y);
         }

         public boolean eqv$mcD$sp(final double x, final double y) {
            return Order.eqv$mcD$sp$(this, x, y);
         }

         public boolean eqv$mcF$sp(final float x, final float y) {
            return Order.eqv$mcF$sp$(this, x, y);
         }

         public boolean eqv$mcI$sp(final int x, final int y) {
            return Order.eqv$mcI$sp$(this, x, y);
         }

         public boolean eqv$mcJ$sp(final long x, final long y) {
            return Order.eqv$mcJ$sp$(this, x, y);
         }

         public boolean eqv$mcS$sp(final short x, final short y) {
            return Order.eqv$mcS$sp$(this, x, y);
         }

         public boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.eqv$mcV$sp$(this, x, y);
         }

         public boolean neqv(final Object x, final Object y) {
            return Order.neqv$(this, x, y);
         }

         public boolean neqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.neqv$mcZ$sp$(this, x, y);
         }

         public boolean neqv$mcB$sp(final byte x, final byte y) {
            return Order.neqv$mcB$sp$(this, x, y);
         }

         public boolean neqv$mcC$sp(final char x, final char y) {
            return Order.neqv$mcC$sp$(this, x, y);
         }

         public boolean neqv$mcD$sp(final double x, final double y) {
            return Order.neqv$mcD$sp$(this, x, y);
         }

         public boolean neqv$mcF$sp(final float x, final float y) {
            return Order.neqv$mcF$sp$(this, x, y);
         }

         public boolean neqv$mcI$sp(final int x, final int y) {
            return Order.neqv$mcI$sp$(this, x, y);
         }

         public boolean neqv$mcJ$sp(final long x, final long y) {
            return Order.neqv$mcJ$sp$(this, x, y);
         }

         public boolean neqv$mcS$sp(final short x, final short y) {
            return Order.neqv$mcS$sp$(this, x, y);
         }

         public boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.neqv$mcV$sp$(this, x, y);
         }

         public boolean lteqv(final Object x, final Object y) {
            return Order.lteqv$(this, x, y);
         }

         public boolean lteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.lteqv$mcZ$sp$(this, x, y);
         }

         public boolean lteqv$mcB$sp(final byte x, final byte y) {
            return Order.lteqv$mcB$sp$(this, x, y);
         }

         public boolean lteqv$mcC$sp(final char x, final char y) {
            return Order.lteqv$mcC$sp$(this, x, y);
         }

         public boolean lteqv$mcD$sp(final double x, final double y) {
            return Order.lteqv$mcD$sp$(this, x, y);
         }

         public boolean lteqv$mcF$sp(final float x, final float y) {
            return Order.lteqv$mcF$sp$(this, x, y);
         }

         public boolean lteqv$mcI$sp(final int x, final int y) {
            return Order.lteqv$mcI$sp$(this, x, y);
         }

         public boolean lteqv$mcJ$sp(final long x, final long y) {
            return Order.lteqv$mcJ$sp$(this, x, y);
         }

         public boolean lteqv$mcS$sp(final short x, final short y) {
            return Order.lteqv$mcS$sp$(this, x, y);
         }

         public boolean lteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lteqv$mcV$sp$(this, x, y);
         }

         public boolean lt(final Object x, final Object y) {
            return Order.lt$(this, x, y);
         }

         public boolean lt$mcZ$sp(final boolean x, final boolean y) {
            return Order.lt$mcZ$sp$(this, x, y);
         }

         public boolean lt$mcB$sp(final byte x, final byte y) {
            return Order.lt$mcB$sp$(this, x, y);
         }

         public boolean lt$mcC$sp(final char x, final char y) {
            return Order.lt$mcC$sp$(this, x, y);
         }

         public boolean lt$mcD$sp(final double x, final double y) {
            return Order.lt$mcD$sp$(this, x, y);
         }

         public boolean lt$mcF$sp(final float x, final float y) {
            return Order.lt$mcF$sp$(this, x, y);
         }

         public boolean lt$mcI$sp(final int x, final int y) {
            return Order.lt$mcI$sp$(this, x, y);
         }

         public boolean lt$mcJ$sp(final long x, final long y) {
            return Order.lt$mcJ$sp$(this, x, y);
         }

         public boolean lt$mcS$sp(final short x, final short y) {
            return Order.lt$mcS$sp$(this, x, y);
         }

         public boolean lt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.lt$mcV$sp$(this, x, y);
         }

         public boolean gteqv(final Object x, final Object y) {
            return Order.gteqv$(this, x, y);
         }

         public boolean gteqv$mcZ$sp(final boolean x, final boolean y) {
            return Order.gteqv$mcZ$sp$(this, x, y);
         }

         public boolean gteqv$mcB$sp(final byte x, final byte y) {
            return Order.gteqv$mcB$sp$(this, x, y);
         }

         public boolean gteqv$mcC$sp(final char x, final char y) {
            return Order.gteqv$mcC$sp$(this, x, y);
         }

         public boolean gteqv$mcD$sp(final double x, final double y) {
            return Order.gteqv$mcD$sp$(this, x, y);
         }

         public boolean gteqv$mcF$sp(final float x, final float y) {
            return Order.gteqv$mcF$sp$(this, x, y);
         }

         public boolean gteqv$mcI$sp(final int x, final int y) {
            return Order.gteqv$mcI$sp$(this, x, y);
         }

         public boolean gteqv$mcJ$sp(final long x, final long y) {
            return Order.gteqv$mcJ$sp$(this, x, y);
         }

         public boolean gteqv$mcS$sp(final short x, final short y) {
            return Order.gteqv$mcS$sp$(this, x, y);
         }

         public boolean gteqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gteqv$mcV$sp$(this, x, y);
         }

         public boolean gt(final Object x, final Object y) {
            return Order.gt$(this, x, y);
         }

         public boolean gt$mcZ$sp(final boolean x, final boolean y) {
            return Order.gt$mcZ$sp$(this, x, y);
         }

         public boolean gt$mcB$sp(final byte x, final byte y) {
            return Order.gt$mcB$sp$(this, x, y);
         }

         public boolean gt$mcC$sp(final char x, final char y) {
            return Order.gt$mcC$sp$(this, x, y);
         }

         public boolean gt$mcD$sp(final double x, final double y) {
            return Order.gt$mcD$sp$(this, x, y);
         }

         public boolean gt$mcF$sp(final float x, final float y) {
            return Order.gt$mcF$sp$(this, x, y);
         }

         public boolean gt$mcI$sp(final int x, final int y) {
            return Order.gt$mcI$sp$(this, x, y);
         }

         public boolean gt$mcJ$sp(final long x, final long y) {
            return Order.gt$mcJ$sp$(this, x, y);
         }

         public boolean gt$mcS$sp(final short x, final short y) {
            return Order.gt$mcS$sp$(this, x, y);
         }

         public boolean gt$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return Order.gt$mcV$sp$(this, x, y);
         }

         public Ordering toOrdering() {
            return Order.toOrdering$(this);
         }

         public Option partialComparison(final Object x, final Object y) {
            return PartialOrder.partialComparison$(this, x, y);
         }

         public Option partialComparison$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.partialComparison$mcZ$sp$(this, x, y);
         }

         public Option partialComparison$mcB$sp(final byte x, final byte y) {
            return PartialOrder.partialComparison$mcB$sp$(this, x, y);
         }

         public Option partialComparison$mcC$sp(final char x, final char y) {
            return PartialOrder.partialComparison$mcC$sp$(this, x, y);
         }

         public Option partialComparison$mcD$sp(final double x, final double y) {
            return PartialOrder.partialComparison$mcD$sp$(this, x, y);
         }

         public Option partialComparison$mcF$sp(final float x, final float y) {
            return PartialOrder.partialComparison$mcF$sp$(this, x, y);
         }

         public Option partialComparison$mcI$sp(final int x, final int y) {
            return PartialOrder.partialComparison$mcI$sp$(this, x, y);
         }

         public Option partialComparison$mcJ$sp(final long x, final long y) {
            return PartialOrder.partialComparison$mcJ$sp$(this, x, y);
         }

         public Option partialComparison$mcS$sp(final short x, final short y) {
            return PartialOrder.partialComparison$mcS$sp$(this, x, y);
         }

         public Option partialComparison$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.partialComparison$mcV$sp$(this, x, y);
         }

         public Option tryCompare(final Object x, final Object y) {
            return PartialOrder.tryCompare$(this, x, y);
         }

         public Option tryCompare$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.tryCompare$mcZ$sp$(this, x, y);
         }

         public Option tryCompare$mcB$sp(final byte x, final byte y) {
            return PartialOrder.tryCompare$mcB$sp$(this, x, y);
         }

         public Option tryCompare$mcC$sp(final char x, final char y) {
            return PartialOrder.tryCompare$mcC$sp$(this, x, y);
         }

         public Option tryCompare$mcD$sp(final double x, final double y) {
            return PartialOrder.tryCompare$mcD$sp$(this, x, y);
         }

         public Option tryCompare$mcF$sp(final float x, final float y) {
            return PartialOrder.tryCompare$mcF$sp$(this, x, y);
         }

         public Option tryCompare$mcI$sp(final int x, final int y) {
            return PartialOrder.tryCompare$mcI$sp$(this, x, y);
         }

         public Option tryCompare$mcJ$sp(final long x, final long y) {
            return PartialOrder.tryCompare$mcJ$sp$(this, x, y);
         }

         public Option tryCompare$mcS$sp(final short x, final short y) {
            return PartialOrder.tryCompare$mcS$sp$(this, x, y);
         }

         public Option tryCompare$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.tryCompare$mcV$sp$(this, x, y);
         }

         public Option pmin(final Object x, final Object y) {
            return PartialOrder.pmin$(this, x, y);
         }

         public Option pmin$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmin$mcZ$sp$(this, x, y);
         }

         public Option pmin$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmin$mcB$sp$(this, x, y);
         }

         public Option pmin$mcC$sp(final char x, final char y) {
            return PartialOrder.pmin$mcC$sp$(this, x, y);
         }

         public Option pmin$mcD$sp(final double x, final double y) {
            return PartialOrder.pmin$mcD$sp$(this, x, y);
         }

         public Option pmin$mcF$sp(final float x, final float y) {
            return PartialOrder.pmin$mcF$sp$(this, x, y);
         }

         public Option pmin$mcI$sp(final int x, final int y) {
            return PartialOrder.pmin$mcI$sp$(this, x, y);
         }

         public Option pmin$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmin$mcJ$sp$(this, x, y);
         }

         public Option pmin$mcS$sp(final short x, final short y) {
            return PartialOrder.pmin$mcS$sp$(this, x, y);
         }

         public Option pmin$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmin$mcV$sp$(this, x, y);
         }

         public Option pmax(final Object x, final Object y) {
            return PartialOrder.pmax$(this, x, y);
         }

         public Option pmax$mcZ$sp(final boolean x, final boolean y) {
            return PartialOrder.pmax$mcZ$sp$(this, x, y);
         }

         public Option pmax$mcB$sp(final byte x, final byte y) {
            return PartialOrder.pmax$mcB$sp$(this, x, y);
         }

         public Option pmax$mcC$sp(final char x, final char y) {
            return PartialOrder.pmax$mcC$sp$(this, x, y);
         }

         public Option pmax$mcD$sp(final double x, final double y) {
            return PartialOrder.pmax$mcD$sp$(this, x, y);
         }

         public Option pmax$mcF$sp(final float x, final float y) {
            return PartialOrder.pmax$mcF$sp$(this, x, y);
         }

         public Option pmax$mcI$sp(final int x, final int y) {
            return PartialOrder.pmax$mcI$sp$(this, x, y);
         }

         public Option pmax$mcJ$sp(final long x, final long y) {
            return PartialOrder.pmax$mcJ$sp$(this, x, y);
         }

         public Option pmax$mcS$sp(final short x, final short y) {
            return PartialOrder.pmax$mcS$sp$(this, x, y);
         }

         public Option pmax$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
            return PartialOrder.pmax$mcV$sp$(this, x, y);
         }

         public int compare(final Tuple22 x, final Tuple22 y) {
            return BoxesRunTime.unboxToInt(.MODULE$.find$extension(scala.Predef..MODULE$.intArrayOps(new int[]{this.A0$506.compare(x._1(), y._1()), this.A1$483.compare(x._2(), y._2()), this.A2$460.compare(x._3(), y._3()), this.A3$437.compare(x._4(), y._4()), this.A4$414.compare(x._5(), y._5()), this.A5$391.compare(x._6(), y._6()), this.A6$368.compare(x._7(), y._7()), this.A7$345.compare(x._8(), y._8()), this.A8$322.compare(x._9(), y._9()), this.A9$299.compare(x._10(), y._10()), this.A10$276.compare(x._11(), y._11()), this.A11$253.compare(x._12(), y._12()), this.A12$230.compare(x._13(), y._13()), this.A13$207.compare(x._14(), y._14()), this.A14$184.compare(x._15(), y._15()), this.A15$161.compare(x._16(), y._16()), this.A16$138.compare(x._17(), y._17()), this.A17$115.compare(x._18(), y._18()), this.A18$92.compare(x._19(), y._19()), this.A19$69.compare(x._20(), y._20()), this.A20$46.compare(x._21(), y._21()), this.A21$23.compare(x._22(), y._22())}), (JFunction1.mcZI.sp)(x$66) -> x$66 != 0).getOrElse((JFunction0.mcI.sp)() -> 0));
         }

         public {
            this.A0$506 = A0$506;
            this.A1$483 = A1$483;
            this.A2$460 = A2$460;
            this.A3$437 = A3$437;
            this.A4$414 = A4$414;
            this.A5$391 = A5$391;
            this.A6$368 = A6$368;
            this.A7$345 = A7$345;
            this.A8$322 = A8$322;
            this.A9$299 = A9$299;
            this.A10$276 = A10$276;
            this.A11$253 = A11$253;
            this.A12$230 = A12$230;
            this.A13$207 = A13$207;
            this.A14$184 = A14$184;
            this.A15$161 = A15$161;
            this.A16$138 = A16$138;
            this.A17$115 = A17$115;
            this.A18$92 = A18$92;
            this.A19$69 = A19$69;
            this.A20$46 = A20$46;
            this.A21$23 = A21$23;
            Eq.$init$(this);
            PartialOrder.$init$(this);
            Order.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   static void $init$(final TupleOrderInstances $this) {
   }
}

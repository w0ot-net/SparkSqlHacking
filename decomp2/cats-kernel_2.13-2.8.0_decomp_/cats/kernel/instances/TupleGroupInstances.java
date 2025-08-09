package cats.kernel.instances;

import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
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
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%-h\u0001\u0003\r\u001a!\u0003\r\taG\u0010\t\u000b)\u0002A\u0011\u0001\u0017\t\u000bA\u0002A1A\u0019\t\u000b!\u0003A1A%\t\u000be\u0003A1\u0001.\t\u000b9\u0004A1A8\t\u000f\u0005=\u0001\u0001b\u0001\u0002\u0012!9\u0011\u0011\n\u0001\u0005\u0004\u0005-\u0003bBAF\u0001\u0011\r\u0011Q\u0012\u0005\b\u0003+\u0004A1AAl\u0011\u001d\u00119\u0003\u0001C\u0002\u0005SAqA!!\u0001\t\u0007\u0011\u0019\tC\u0004\u0003d\u0002!\u0019A!:\t\u000f\r5\u0003\u0001b\u0001\u0004P!91q\u0018\u0001\u0005\u0004\r\u0005\u0007b\u0002C\u001d\u0001\u0011\rA1\b\u0005\b\tw\u0003A1\u0001C_\u0011\u001d))\u0005\u0001C\u0002\u000b\u000fBq!b6\u0001\t\u0007)I\u000eC\u0004\u0007r\u0001!\u0019Ab\u001d\t\u000f\u001dM\u0001\u0001b\u0001\b\u0016!9qQ\u0018\u0001\u0005\u0004\u001d}\u0006b\u0002E8\u0001\u0011\r\u0001\u0012\u000f\u0005\b\u0013S\u0001A1AE\u0016\u0005M!V\u000f\u001d7f\u000fJ|W\u000f]%ogR\fgnY3t\u0015\tQ2$A\u0005j]N$\u0018M\\2fg*\u0011A$H\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003y\tAaY1ugN\u0019\u0001\u0001\t\u0014\u0011\u0005\u0005\"S\"\u0001\u0012\u000b\u0003\r\nQa]2bY\u0006L!!\n\u0012\u0003\r\u0005s\u0017PU3g!\t9\u0003&D\u0001\u001a\u0013\tI\u0013DA\u0010UkBdWmQ8n[V$\u0018\r^5wK6{gn\\5e\u0013:\u001cH/\u00198dKN\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002[A\u0011\u0011EL\u0005\u0003_\t\u0012A!\u00168ji\u0006A2-\u0019;t\u0017\u0016\u0014h.\u001a7He>,\bOR8s)V\u0004H.Z\u0019\u0016\u0005IbDCA\u001aF!\r!TgN\u0007\u00027%\u0011ag\u0007\u0002\u0006\u000fJ|W\u000f\u001d\t\u0004CaR\u0014BA\u001d#\u0005\u0019!V\u000f\u001d7fcA\u00111\b\u0010\u0007\u0001\t\u0015i$A1\u0001?\u0005\t\t\u0005'\u0005\u0002@\u0005B\u0011\u0011\u0005Q\u0005\u0003\u0003\n\u0012qAT8uQ&tw\r\u0005\u0002\"\u0007&\u0011AI\t\u0002\u0004\u0003:L\b\"\u0002$\u0003\u0001\b9\u0015AA!1!\r!TGO\u0001\u0019G\u0006$8oS3s]\u0016dwI]8va\u001a{'\u000fV;qY\u0016\u0014Tc\u0001&Q%R\u00191\n\u0016,\u0011\u0007Q*D\n\u0005\u0003\"\u001b>\u000b\u0016B\u0001(#\u0005\u0019!V\u000f\u001d7feA\u00111\b\u0015\u0003\u0006{\r\u0011\rA\u0010\t\u0003wI#QaU\u0002C\u0002y\u0012!!Q\u0019\t\u000b\u0019\u001b\u00019A+\u0011\u0007Q*t\nC\u0003X\u0007\u0001\u000f\u0001,\u0001\u0002BcA\u0019A'N)\u00021\r\fGo]&fe:,Gn\u0012:pkB4uN\u001d+va2,7'\u0006\u0003\\C\u000e,G\u0003\u0002/hS.\u00042\u0001N\u001b^!\u0015\tc\f\u00192e\u0013\ty&E\u0001\u0004UkBdWm\r\t\u0003w\u0005$Q!\u0010\u0003C\u0002y\u0002\"aO2\u0005\u000bM#!\u0019\u0001 \u0011\u0005m*G!\u00024\u0005\u0005\u0004q$AA!3\u0011\u00151E\u0001q\u0001i!\r!T\u0007\u0019\u0005\u0006/\u0012\u0001\u001dA\u001b\t\u0004iU\u0012\u0007\"\u00027\u0005\u0001\bi\u0017AA!3!\r!T\u0007Z\u0001\u0019G\u0006$8oS3s]\u0016dwI]8va\u001a{'\u000fV;qY\u0016$T#\u00029wqjdH\u0003C9\u007f\u0003\u0003\t)!!\u0003\u0011\u0007Q*$\u000f\u0005\u0004\"gV<\u0018p_\u0005\u0003i\n\u0012a\u0001V;qY\u0016$\u0004CA\u001ew\t\u0015iTA1\u0001?!\tY\u0004\u0010B\u0003T\u000b\t\u0007a\b\u0005\u0002<u\u0012)a-\u0002b\u0001}A\u00111\b \u0003\u0006{\u0016\u0011\rA\u0010\u0002\u0003\u0003NBQAR\u0003A\u0004}\u00042\u0001N\u001bv\u0011\u00199V\u0001q\u0001\u0002\u0004A\u0019A'N<\t\r1,\u00019AA\u0004!\r!T'\u001f\u0005\b\u0003\u0017)\u00019AA\u0007\u0003\t\t5\u0007E\u00025km\f\u0001dY1ug.+'O\\3m\u000fJ|W\u000f\u001d$peR+\b\u000f\\36+1\t\u0019\"a\b\u0002$\u0005\u001d\u00121FA\u0018)1\t)\"a\r\u00028\u0005m\u0012qHA\"!\u0011!T'a\u0006\u0011\u001b\u0005\nI\"!\b\u0002\"\u0005\u0015\u0012\u0011FA\u0017\u0013\r\tYB\t\u0002\u0007)V\u0004H.Z\u001b\u0011\u0007m\ny\u0002B\u0003>\r\t\u0007a\bE\u0002<\u0003G!Qa\u0015\u0004C\u0002y\u00022aOA\u0014\t\u00151gA1\u0001?!\rY\u00141\u0006\u0003\u0006{\u001a\u0011\rA\u0010\t\u0004w\u0005=BABA\u0019\r\t\u0007aH\u0001\u0002Bi!1aI\u0002a\u0002\u0003k\u0001B\u0001N\u001b\u0002\u001e!1qK\u0002a\u0002\u0003s\u0001B\u0001N\u001b\u0002\"!1AN\u0002a\u0002\u0003{\u0001B\u0001N\u001b\u0002&!9\u00111\u0002\u0004A\u0004\u0005\u0005\u0003\u0003\u0002\u001b6\u0003SAq!!\u0012\u0007\u0001\b\t9%\u0001\u0002BiA!A'NA\u0017\u0003a\u0019\u0017\r^:LKJtW\r\\$s_V\u0004hi\u001c:UkBdWMN\u000b\u000f\u0003\u001b\nI&!\u0018\u0002b\u0005\u0015\u0014\u0011NA7)9\ty%!\u001d\u0002v\u0005e\u0014QPAA\u0003\u000b\u0003B\u0001N\u001b\u0002RAy\u0011%a\u0015\u0002X\u0005m\u0013qLA2\u0003O\nY'C\u0002\u0002V\t\u0012a\u0001V;qY\u00164\u0004cA\u001e\u0002Z\u0011)Qh\u0002b\u0001}A\u00191(!\u0018\u0005\u000bM;!\u0019\u0001 \u0011\u0007m\n\t\u0007B\u0003g\u000f\t\u0007a\bE\u0002<\u0003K\"Q!`\u0004C\u0002y\u00022aOA5\t\u0019\t\td\u0002b\u0001}A\u00191(!\u001c\u0005\r\u0005=tA1\u0001?\u0005\t\tU\u0007\u0003\u0004G\u000f\u0001\u000f\u00111\u000f\t\u0005iU\n9\u0006\u0003\u0004X\u000f\u0001\u000f\u0011q\u000f\t\u0005iU\nY\u0006\u0003\u0004m\u000f\u0001\u000f\u00111\u0010\t\u0005iU\ny\u0006C\u0004\u0002\f\u001d\u0001\u001d!a \u0011\tQ*\u00141\r\u0005\b\u0003\u000b:\u00019AAB!\u0011!T'a\u001a\t\u000f\u0005\u001du\u0001q\u0001\u0002\n\u0006\u0011\u0011)\u000e\t\u0005iU\nY'\u0001\rdCR\u001c8*\u001a:oK2<%o\\;q\r>\u0014H+\u001e9mK^*\u0002#a$\u0002\u001c\u0006}\u00151UAT\u0003W\u000by+a-\u0015!\u0005E\u0015qWA^\u0003\u007f\u000b\u0019-a2\u0002L\u0006=\u0007\u0003\u0002\u001b6\u0003'\u0003\u0012#IAK\u00033\u000bi*!)\u0002&\u0006%\u0016QVAY\u0013\r\t9J\t\u0002\u0007)V\u0004H.Z\u001c\u0011\u0007m\nY\nB\u0003>\u0011\t\u0007a\bE\u0002<\u0003?#Qa\u0015\u0005C\u0002y\u00022aOAR\t\u00151\u0007B1\u0001?!\rY\u0014q\u0015\u0003\u0006{\"\u0011\rA\u0010\t\u0004w\u0005-FABA\u0019\u0011\t\u0007a\bE\u0002<\u0003_#a!a\u001c\t\u0005\u0004q\u0004cA\u001e\u00024\u00121\u0011Q\u0017\u0005C\u0002y\u0012!!\u0011\u001c\t\r\u0019C\u00019AA]!\u0011!T'!'\t\r]C\u00019AA_!\u0011!T'!(\t\r1D\u00019AAa!\u0011!T'!)\t\u000f\u0005-\u0001\u0002q\u0001\u0002FB!A'NAS\u0011\u001d\t)\u0005\u0003a\u0002\u0003\u0013\u0004B\u0001N\u001b\u0002*\"9\u0011q\u0011\u0005A\u0004\u00055\u0007\u0003\u0002\u001b6\u0003[Cq!!5\t\u0001\b\t\u0019.\u0001\u0002BmA!A'NAY\u0003a\u0019\u0017\r^:LKJtW\r\\$s_V\u0004hi\u001c:UkBdW\rO\u000b\u0013\u00033\f)/!;\u0002n\u0006E\u0018Q_A}\u0003{\u0014\t\u0001\u0006\n\u0002\\\n\u0015!\u0011\u0002B\u0007\u0005#\u0011)B!\u0007\u0003\u001e\t\u0005\u0002\u0003\u0002\u001b6\u0003;\u00042#IAp\u0003G\f9/a;\u0002p\u0006M\u0018q_A~\u0003\u007fL1!!9#\u0005\u0019!V\u000f\u001d7fqA\u00191(!:\u0005\u000buJ!\u0019\u0001 \u0011\u0007m\nI\u000fB\u0003T\u0013\t\u0007a\bE\u0002<\u0003[$QAZ\u0005C\u0002y\u00022aOAy\t\u0015i\u0018B1\u0001?!\rY\u0014Q\u001f\u0003\u0007\u0003cI!\u0019\u0001 \u0011\u0007m\nI\u0010\u0002\u0004\u0002p%\u0011\rA\u0010\t\u0004w\u0005uHABA[\u0013\t\u0007a\bE\u0002<\u0005\u0003!aAa\u0001\n\u0005\u0004q$AA!8\u0011\u00191\u0015\u0002q\u0001\u0003\bA!A'NAr\u0011\u00199\u0016\u0002q\u0001\u0003\fA!A'NAt\u0011\u0019a\u0017\u0002q\u0001\u0003\u0010A!A'NAv\u0011\u001d\tY!\u0003a\u0002\u0005'\u0001B\u0001N\u001b\u0002p\"9\u0011QI\u0005A\u0004\t]\u0001\u0003\u0002\u001b6\u0003gDq!a\"\n\u0001\b\u0011Y\u0002\u0005\u00035k\u0005]\bbBAi\u0013\u0001\u000f!q\u0004\t\u0005iU\nY\u0010C\u0004\u0003$%\u0001\u001dA!\n\u0002\u0005\u0005;\u0004\u0003\u0002\u001b6\u0003\u007f\f\u0001dY1ug.+'O\\3m\u000fJ|W\u000f\u001d$peR+\b\u000f\\3:+Q\u0011YCa\u000e\u0003<\t}\"1\tB$\u0005\u0017\u0012yEa\u0015\u0003XQ!\"Q\u0006B.\u0005?\u0012\u0019Ga\u001a\u0003l\t=$1\u000fB<\u0005w\u0002B\u0001N\u001b\u00030A)\u0012E!\r\u00036\te\"Q\bB!\u0005\u000b\u0012IE!\u0014\u0003R\tU\u0013b\u0001B\u001aE\t1A+\u001e9mKf\u00022a\u000fB\u001c\t\u0015i$B1\u0001?!\rY$1\b\u0003\u0006'*\u0011\rA\u0010\t\u0004w\t}B!\u00024\u000b\u0005\u0004q\u0004cA\u001e\u0003D\u0011)QP\u0003b\u0001}A\u00191Ha\u0012\u0005\r\u0005E\"B1\u0001?!\rY$1\n\u0003\u0007\u0003_R!\u0019\u0001 \u0011\u0007m\u0012y\u0005\u0002\u0004\u00026*\u0011\rA\u0010\t\u0004w\tMCA\u0002B\u0002\u0015\t\u0007a\bE\u0002<\u0005/\"aA!\u0017\u000b\u0005\u0004q$AA!9\u0011\u00191%\u0002q\u0001\u0003^A!A'\u000eB\u001b\u0011\u00199&\u0002q\u0001\u0003bA!A'\u000eB\u001d\u0011\u0019a'\u0002q\u0001\u0003fA!A'\u000eB\u001f\u0011\u001d\tYA\u0003a\u0002\u0005S\u0002B\u0001N\u001b\u0003B!9\u0011Q\t\u0006A\u0004\t5\u0004\u0003\u0002\u001b6\u0005\u000bBq!a\"\u000b\u0001\b\u0011\t\b\u0005\u00035k\t%\u0003bBAi\u0015\u0001\u000f!Q\u000f\t\u0005iU\u0012i\u0005C\u0004\u0003$)\u0001\u001dA!\u001f\u0011\tQ*$\u0011\u000b\u0005\b\u0005{R\u00019\u0001B@\u0003\t\t\u0005\b\u0005\u00035k\tU\u0013!G2biN\\UM\u001d8fY\u001e\u0013x.\u001e9G_J$V\u000f\u001d7fcA*bC!\"\u0003\u0012\nU%\u0011\u0014BO\u0005C\u0013)K!+\u0003.\nE&Q\u0017\u000b\u0017\u0005\u000f\u0013IL!0\u0003B\n\u0015'\u0011\u001aBg\u0005#\u0014)N!7\u0003^B!A'\u000eBE!]\t#1\u0012BH\u0005'\u00139Ja'\u0003 \n\r&q\u0015BV\u0005_\u0013\u0019,C\u0002\u0003\u000e\n\u0012q\u0001V;qY\u0016\f\u0004\u0007E\u0002<\u0005##Q!P\u0006C\u0002y\u00022a\u000fBK\t\u0015\u00196B1\u0001?!\rY$\u0011\u0014\u0003\u0006M.\u0011\rA\u0010\t\u0004w\tuE!B?\f\u0005\u0004q\u0004cA\u001e\u0003\"\u00121\u0011\u0011G\u0006C\u0002y\u00022a\u000fBS\t\u0019\tyg\u0003b\u0001}A\u00191H!+\u0005\r\u0005U6B1\u0001?!\rY$Q\u0016\u0003\u0007\u0005\u0007Y!\u0019\u0001 \u0011\u0007m\u0012\t\f\u0002\u0004\u0003Z-\u0011\rA\u0010\t\u0004w\tUFA\u0002B\\\u0017\t\u0007aH\u0001\u0002Bs!1ai\u0003a\u0002\u0005w\u0003B\u0001N\u001b\u0003\u0010\"1qk\u0003a\u0002\u0005\u007f\u0003B\u0001N\u001b\u0003\u0014\"1An\u0003a\u0002\u0005\u0007\u0004B\u0001N\u001b\u0003\u0018\"9\u00111B\u0006A\u0004\t\u001d\u0007\u0003\u0002\u001b6\u00057Cq!!\u0012\f\u0001\b\u0011Y\r\u0005\u00035k\t}\u0005bBAD\u0017\u0001\u000f!q\u001a\t\u0005iU\u0012\u0019\u000bC\u0004\u0002R.\u0001\u001dAa5\u0011\tQ*$q\u0015\u0005\b\u0005GY\u00019\u0001Bl!\u0011!TGa+\t\u000f\tu4\u0002q\u0001\u0003\\B!A'\u000eBX\u0011\u001d\u0011yn\u0003a\u0002\u0005C\f!!Q\u001d\u0011\tQ*$1W\u0001\u001aG\u0006$8oS3s]\u0016dwI]8va\u001a{'\u000fV;qY\u0016\f\u0014'\u0006\r\u0003h\nM(q\u001fB~\u0005\u007f\u001c\u0019aa\u0002\u0004\f\r=11CB\f\u00077!\u0002D!;\u0004 \r\r2qEB\u0016\u0007_\u0019\u0019da\u000e\u0004<\r}21IB$!\u0011!TGa;\u00113\u0005\u0012iO!=\u0003v\ne(Q`B\u0001\u0007\u000b\u0019Ia!\u0004\u0004\u0012\rU1\u0011D\u0005\u0004\u0005_\u0014#a\u0002+va2,\u0017'\r\t\u0004w\tMH!B\u001f\r\u0005\u0004q\u0004cA\u001e\u0003x\u0012)1\u000b\u0004b\u0001}A\u00191Ha?\u0005\u000b\u0019d!\u0019\u0001 \u0011\u0007m\u0012y\u0010B\u0003~\u0019\t\u0007a\bE\u0002<\u0007\u0007!a!!\r\r\u0005\u0004q\u0004cA\u001e\u0004\b\u00111\u0011q\u000e\u0007C\u0002y\u00022aOB\u0006\t\u0019\t)\f\u0004b\u0001}A\u00191ha\u0004\u0005\r\t\rAB1\u0001?!\rY41\u0003\u0003\u0007\u00053b!\u0019\u0001 \u0011\u0007m\u001a9\u0002\u0002\u0004\u000382\u0011\rA\u0010\t\u0004w\rmAABB\u000f\u0019\t\u0007aHA\u0002BcABaA\u0012\u0007A\u0004\r\u0005\u0002\u0003\u0002\u001b6\u0005cDaa\u0016\u0007A\u0004\r\u0015\u0002\u0003\u0002\u001b6\u0005kDa\u0001\u001c\u0007A\u0004\r%\u0002\u0003\u0002\u001b6\u0005sDq!a\u0003\r\u0001\b\u0019i\u0003\u0005\u00035k\tu\bbBA#\u0019\u0001\u000f1\u0011\u0007\t\u0005iU\u001a\t\u0001C\u0004\u0002\b2\u0001\u001da!\u000e\u0011\tQ*4Q\u0001\u0005\b\u0003#d\u00019AB\u001d!\u0011!Tg!\u0003\t\u000f\t\rB\u0002q\u0001\u0004>A!A'NB\u0007\u0011\u001d\u0011i\b\u0004a\u0002\u0007\u0003\u0002B\u0001N\u001b\u0004\u0012!9!q\u001c\u0007A\u0004\r\u0015\u0003\u0003\u0002\u001b6\u0007+Aqa!\u0013\r\u0001\b\u0019Y%A\u0002BcA\u0002B\u0001N\u001b\u0004\u001a\u0005I2-\u0019;t\u0017\u0016\u0014h.\u001a7He>,\bOR8s)V\u0004H.Z\u00193+i\u0019\tf!\u0018\u0004b\r\u00154\u0011NB7\u0007c\u001a)h!\u001f\u0004~\r\u00055QQBE)i\u0019\u0019f!$\u0004\u0012\u000eU5\u0011TBO\u0007C\u001b)k!+\u0004.\u000eE6QWB]!\u0011!Tg!\u0016\u00117\u0005\u001a9fa\u0017\u0004`\r\r4qMB6\u0007_\u001a\u0019ha\u001e\u0004|\r}41QBD\u0013\r\u0019IF\t\u0002\b)V\u0004H.Z\u00193!\rY4Q\f\u0003\u0006{5\u0011\rA\u0010\t\u0004w\r\u0005D!B*\u000e\u0005\u0004q\u0004cA\u001e\u0004f\u0011)a-\u0004b\u0001}A\u00191h!\u001b\u0005\u000bul!\u0019\u0001 \u0011\u0007m\u001ai\u0007\u0002\u0004\u000225\u0011\rA\u0010\t\u0004w\rEDABA8\u001b\t\u0007a\bE\u0002<\u0007k\"a!!.\u000e\u0005\u0004q\u0004cA\u001e\u0004z\u00111!1A\u0007C\u0002y\u00022aOB?\t\u0019\u0011I&\u0004b\u0001}A\u00191h!!\u0005\r\t]VB1\u0001?!\rY4Q\u0011\u0003\u0007\u0007;i!\u0019\u0001 \u0011\u0007m\u001aI\t\u0002\u0004\u0004\f6\u0011\rA\u0010\u0002\u0004\u0003F\n\u0004B\u0002$\u000e\u0001\b\u0019y\t\u0005\u00035k\rm\u0003BB,\u000e\u0001\b\u0019\u0019\n\u0005\u00035k\r}\u0003B\u00027\u000e\u0001\b\u00199\n\u0005\u00035k\r\r\u0004bBA\u0006\u001b\u0001\u000f11\u0014\t\u0005iU\u001a9\u0007C\u0004\u0002F5\u0001\u001daa(\u0011\tQ*41\u000e\u0005\b\u0003\u000fk\u00019ABR!\u0011!Tga\u001c\t\u000f\u0005EW\u0002q\u0001\u0004(B!A'NB:\u0011\u001d\u0011\u0019#\u0004a\u0002\u0007W\u0003B\u0001N\u001b\u0004x!9!QP\u0007A\u0004\r=\u0006\u0003\u0002\u001b6\u0007wBqAa8\u000e\u0001\b\u0019\u0019\f\u0005\u00035k\r}\u0004bBB%\u001b\u0001\u000f1q\u0017\t\u0005iU\u001a\u0019\tC\u0004\u0004<6\u0001\u001da!0\u0002\u0007\u0005\u000b\u0014\u0007\u0005\u00035k\r\u001d\u0015!G2biN\\UM\u001d8fY\u001e\u0013x.\u001e9G_J$V\u000f\u001d7fcM*Bda1\u0004P\u000eM7q[Bn\u0007?\u001c\u0019oa:\u0004l\u000e=81_B|\u0007w\u001cy\u0010\u0006\u000f\u0004F\u0012\rAq\u0001C\u0006\t\u001f!\u0019\u0002b\u0006\u0005\u001c\u0011}A1\u0005C\u0014\tW!y\u0003b\r\u0011\tQ*4q\u0019\t\u001eC\r%7QZBi\u0007+\u001cIn!8\u0004b\u000e\u00158\u0011^Bw\u0007c\u001c)p!?\u0004~&\u001911\u001a\u0012\u0003\u000fQ+\b\u000f\\32gA\u00191ha4\u0005\u000bur!\u0019\u0001 \u0011\u0007m\u001a\u0019\u000eB\u0003T\u001d\t\u0007a\bE\u0002<\u0007/$QA\u001a\bC\u0002y\u00022aOBn\t\u0015ihB1\u0001?!\rY4q\u001c\u0003\u0007\u0003cq!\u0019\u0001 \u0011\u0007m\u001a\u0019\u000f\u0002\u0004\u0002p9\u0011\rA\u0010\t\u0004w\r\u001dHABA[\u001d\t\u0007a\bE\u0002<\u0007W$aAa\u0001\u000f\u0005\u0004q\u0004cA\u001e\u0004p\u00121!\u0011\f\bC\u0002y\u00022aOBz\t\u0019\u00119L\u0004b\u0001}A\u00191ha>\u0005\r\ruaB1\u0001?!\rY41 \u0003\u0007\u0007\u0017s!\u0019\u0001 \u0011\u0007m\u001ay\u0010\u0002\u0004\u0005\u00029\u0011\rA\u0010\u0002\u0004\u0003F\u0012\u0004B\u0002$\u000f\u0001\b!)\u0001\u0005\u00035k\r5\u0007BB,\u000f\u0001\b!I\u0001\u0005\u00035k\rE\u0007B\u00027\u000f\u0001\b!i\u0001\u0005\u00035k\rU\u0007bBA\u0006\u001d\u0001\u000fA\u0011\u0003\t\u0005iU\u001aI\u000eC\u0004\u0002F9\u0001\u001d\u0001\"\u0006\u0011\tQ*4Q\u001c\u0005\b\u0003\u000fs\u00019\u0001C\r!\u0011!Tg!9\t\u000f\u0005Eg\u0002q\u0001\u0005\u001eA!A'NBs\u0011\u001d\u0011\u0019C\u0004a\u0002\tC\u0001B\u0001N\u001b\u0004j\"9!Q\u0010\bA\u0004\u0011\u0015\u0002\u0003\u0002\u001b6\u0007[DqAa8\u000f\u0001\b!I\u0003\u0005\u00035k\rE\bbBB%\u001d\u0001\u000fAQ\u0006\t\u0005iU\u001a)\u0010C\u0004\u0004<:\u0001\u001d\u0001\"\r\u0011\tQ*4\u0011 \u0005\b\tkq\u00019\u0001C\u001c\u0003\r\t\u0015G\r\t\u0005iU\u001ai0A\rdCR\u001c8*\u001a:oK2<%o\\;q\r>\u0014H+\u001e9mKF\"TC\bC\u001f\t\u0013\"i\u0005\"\u0015\u0005V\u0011eCQ\fC1\tK\"I\u0007\"\u001c\u0005r\u0011UD\u0011\u0010C?)y!y\u0004\"!\u0005\u0006\u0012%EQ\u0012CI\t+#I\n\"(\u0005\"\u0012\u0015F\u0011\u0016CW\tc#)\f\u0005\u00035k\u0011\u0005\u0003cH\u0011\u0005D\u0011\u001dC1\nC(\t'\"9\u0006b\u0017\u0005`\u0011\rDq\rC6\t_\"\u0019\bb\u001e\u0005|%\u0019AQ\t\u0012\u0003\u000fQ+\b\u000f\\32iA\u00191\b\"\u0013\u0005\u000buz!\u0019\u0001 \u0011\u0007m\"i\u0005B\u0003T\u001f\t\u0007a\bE\u0002<\t#\"QAZ\bC\u0002y\u00022a\u000fC+\t\u0015ixB1\u0001?!\rYD\u0011\f\u0003\u0007\u0003cy!\u0019\u0001 \u0011\u0007m\"i\u0006\u0002\u0004\u0002p=\u0011\rA\u0010\t\u0004w\u0011\u0005DABA[\u001f\t\u0007a\bE\u0002<\tK\"aAa\u0001\u0010\u0005\u0004q\u0004cA\u001e\u0005j\u00111!\u0011L\bC\u0002y\u00022a\u000fC7\t\u0019\u00119l\u0004b\u0001}A\u00191\b\"\u001d\u0005\r\ruqB1\u0001?!\rYDQ\u000f\u0003\u0007\u0007\u0017{!\u0019\u0001 \u0011\u0007m\"I\b\u0002\u0004\u0005\u0002=\u0011\rA\u0010\t\u0004w\u0011uDA\u0002C@\u001f\t\u0007aHA\u0002BcMBaAR\bA\u0004\u0011\r\u0005\u0003\u0002\u001b6\t\u000fBaaV\bA\u0004\u0011\u001d\u0005\u0003\u0002\u001b6\t\u0017Ba\u0001\\\bA\u0004\u0011-\u0005\u0003\u0002\u001b6\t\u001fBq!a\u0003\u0010\u0001\b!y\t\u0005\u00035k\u0011M\u0003bBA#\u001f\u0001\u000fA1\u0013\t\u0005iU\"9\u0006C\u0004\u0002\b>\u0001\u001d\u0001b&\u0011\tQ*D1\f\u0005\b\u0003#|\u00019\u0001CN!\u0011!T\u0007b\u0018\t\u000f\t\rr\u0002q\u0001\u0005 B!A'\u000eC2\u0011\u001d\u0011ih\u0004a\u0002\tG\u0003B\u0001N\u001b\u0005h!9!q\\\bA\u0004\u0011\u001d\u0006\u0003\u0002\u001b6\tWBqa!\u0013\u0010\u0001\b!Y\u000b\u0005\u00035k\u0011=\u0004bBB^\u001f\u0001\u000fAq\u0016\t\u0005iU\"\u0019\bC\u0004\u00056=\u0001\u001d\u0001b-\u0011\tQ*Dq\u000f\u0005\b\to{\u00019\u0001C]\u0003\r\t\u0015g\r\t\u0005iU\"Y(A\rdCR\u001c8*\u001a:oK2<%o\\;q\r>\u0014H+\u001e9mKF*T\u0003\tC`\t\u0017$y\rb5\u0005X\u0012mGq\u001cCr\tO$Y\u000fb<\u0005t\u0012]H1 C\u0000\u000b\u0007!\u0002\u0005\"1\u0006\b\u0015-QqBC\n\u000b/)Y\"b\b\u0006$\u0015\u001dR1FC\u0018\u000bg)9$b\u000f\u0006@A!A'\u000eCb!\u0005\nCQ\u0019Ce\t\u001b$\t\u000e\"6\u0005Z\u0012uG\u0011\u001dCs\tS$i\u000f\"=\u0005v\u0012eHQ`C\u0001\u0013\r!9M\t\u0002\b)V\u0004H.Z\u00196!\rYD1\u001a\u0003\u0006{A\u0011\rA\u0010\t\u0004w\u0011=G!B*\u0011\u0005\u0004q\u0004cA\u001e\u0005T\u0012)a\r\u0005b\u0001}A\u00191\bb6\u0005\u000bu\u0004\"\u0019\u0001 \u0011\u0007m\"Y\u000e\u0002\u0004\u00022A\u0011\rA\u0010\t\u0004w\u0011}GABA8!\t\u0007a\bE\u0002<\tG$a!!.\u0011\u0005\u0004q\u0004cA\u001e\u0005h\u00121!1\u0001\tC\u0002y\u00022a\u000fCv\t\u0019\u0011I\u0006\u0005b\u0001}A\u00191\bb<\u0005\r\t]\u0006C1\u0001?!\rYD1\u001f\u0003\u0007\u0007;\u0001\"\u0019\u0001 \u0011\u0007m\"9\u0010\u0002\u0004\u0004\fB\u0011\rA\u0010\t\u0004w\u0011mHA\u0002C\u0001!\t\u0007a\bE\u0002<\t\u007f$a\u0001b \u0011\u0005\u0004q\u0004cA\u001e\u0006\u0004\u00111QQ\u0001\tC\u0002y\u00121!Q\u00195\u0011\u00191\u0005\u0003q\u0001\u0006\nA!A'\u000eCe\u0011\u00199\u0006\u0003q\u0001\u0006\u000eA!A'\u000eCg\u0011\u0019a\u0007\u0003q\u0001\u0006\u0012A!A'\u000eCi\u0011\u001d\tY\u0001\u0005a\u0002\u000b+\u0001B\u0001N\u001b\u0005V\"9\u0011Q\t\tA\u0004\u0015e\u0001\u0003\u0002\u001b6\t3Dq!a\"\u0011\u0001\b)i\u0002\u0005\u00035k\u0011u\u0007bBAi!\u0001\u000fQ\u0011\u0005\t\u0005iU\"\t\u000fC\u0004\u0003$A\u0001\u001d!\"\n\u0011\tQ*DQ\u001d\u0005\b\u0005{\u0002\u00029AC\u0015!\u0011!T\u0007\";\t\u000f\t}\u0007\u0003q\u0001\u0006.A!A'\u000eCw\u0011\u001d\u0019I\u0005\u0005a\u0002\u000bc\u0001B\u0001N\u001b\u0005r\"911\u0018\tA\u0004\u0015U\u0002\u0003\u0002\u001b6\tkDq\u0001\"\u000e\u0011\u0001\b)I\u0004\u0005\u00035k\u0011e\bb\u0002C\\!\u0001\u000fQQ\b\t\u0005iU\"i\u0010C\u0004\u0006BA\u0001\u001d!b\u0011\u0002\u0007\u0005\u000bD\u0007\u0005\u00035k\u0015\u0005\u0011!G2biN\\UM\u001d8fY\u001e\u0013x.\u001e9G_J$V\u000f\u001d7fcY*\"%\"\u0013\u0006V\u0015eSQLC1\u000bK*I'\"\u001c\u0006r\u0015UT\u0011PC?\u000b\u0003+))\"#\u0006\u000e\u0016EECIC&\u000b++I*\"(\u0006\"\u0016\u0015V\u0011VCW\u000bc+),\"/\u0006>\u0016\u0005WQYCe\u000b\u001b,\t\u000e\u0005\u00035k\u00155\u0003cI\u0011\u0006P\u0015MSqKC.\u000b?*\u0019'b\u001a\u0006l\u0015=T1OC<\u000bw*y(b!\u0006\b\u0016-UqR\u0005\u0004\u000b#\u0012#a\u0002+va2,\u0017G\u000e\t\u0004w\u0015UC!B\u001f\u0012\u0005\u0004q\u0004cA\u001e\u0006Z\u0011)1+\u0005b\u0001}A\u00191(\"\u0018\u0005\u000b\u0019\f\"\u0019\u0001 \u0011\u0007m*\t\u0007B\u0003~#\t\u0007a\bE\u0002<\u000bK\"a!!\r\u0012\u0005\u0004q\u0004cA\u001e\u0006j\u00111\u0011qN\tC\u0002y\u00022aOC7\t\u0019\t),\u0005b\u0001}A\u00191(\"\u001d\u0005\r\t\r\u0011C1\u0001?!\rYTQ\u000f\u0003\u0007\u00053\n\"\u0019\u0001 \u0011\u0007m*I\b\u0002\u0004\u00038F\u0011\rA\u0010\t\u0004w\u0015uDABB\u000f#\t\u0007a\bE\u0002<\u000b\u0003#aaa#\u0012\u0005\u0004q\u0004cA\u001e\u0006\u0006\u00121A\u0011A\tC\u0002y\u00022aOCE\t\u0019!y(\u0005b\u0001}A\u00191(\"$\u0005\r\u0015\u0015\u0011C1\u0001?!\rYT\u0011\u0013\u0003\u0007\u000b'\u000b\"\u0019\u0001 \u0003\u0007\u0005\u000bT\u0007\u0003\u0004G#\u0001\u000fQq\u0013\t\u0005iU*\u0019\u0006\u0003\u0004X#\u0001\u000fQ1\u0014\t\u0005iU*9\u0006\u0003\u0004m#\u0001\u000fQq\u0014\t\u0005iU*Y\u0006C\u0004\u0002\fE\u0001\u001d!b)\u0011\tQ*Tq\f\u0005\b\u0003\u000b\n\u00029ACT!\u0011!T'b\u0019\t\u000f\u0005\u001d\u0015\u0003q\u0001\u0006,B!A'NC4\u0011\u001d\t\t.\u0005a\u0002\u000b_\u0003B\u0001N\u001b\u0006l!9!1E\tA\u0004\u0015M\u0006\u0003\u0002\u001b6\u000b_BqA! \u0012\u0001\b)9\f\u0005\u00035k\u0015M\u0004b\u0002Bp#\u0001\u000fQ1\u0018\t\u0005iU*9\bC\u0004\u0004JE\u0001\u001d!b0\u0011\tQ*T1\u0010\u0005\b\u0007w\u000b\u00029ACb!\u0011!T'b \t\u000f\u0011U\u0012\u0003q\u0001\u0006HB!A'NCB\u0011\u001d!9,\u0005a\u0002\u000b\u0017\u0004B\u0001N\u001b\u0006\b\"9Q\u0011I\tA\u0004\u0015=\u0007\u0003\u0002\u001b6\u000b\u0017Cq!b5\u0012\u0001\b)).A\u0002BcU\u0002B\u0001N\u001b\u0006\u0010\u0006I2-\u0019;t\u0017\u0016\u0014h.\u001a7He>,\bOR8s)V\u0004H.Z\u00198+\u0011*Y.b:\u0006l\u0016=X1_C|\u000bw,yPb\u0001\u0007\b\u0019-aq\u0002D\n\r/1YBb\b\u0007$\u0019\u001dB\u0003JCo\rW1yCb\r\u00078\u0019mbq\bD\"\r\u000f2YEb\u0014\u0007T\u0019]c1\fD0\rG29Gb\u001b\u0011\tQ*Tq\u001c\t&C\u0015\u0005XQ]Cu\u000b[,\t0\">\u0006z\u0016uh\u0011\u0001D\u0003\r\u00131iA\"\u0005\u0007\u0016\u0019eaQ\u0004D\u0011\rKI1!b9#\u0005\u001d!V\u000f\u001d7fc]\u00022aOCt\t\u0015i$C1\u0001?!\rYT1\u001e\u0003\u0006'J\u0011\rA\u0010\t\u0004w\u0015=H!\u00024\u0013\u0005\u0004q\u0004cA\u001e\u0006t\u0012)QP\u0005b\u0001}A\u00191(b>\u0005\r\u0005E\"C1\u0001?!\rYT1 \u0003\u0007\u0003_\u0012\"\u0019\u0001 \u0011\u0007m*y\u0010\u0002\u0004\u00026J\u0011\rA\u0010\t\u0004w\u0019\rAA\u0002B\u0002%\t\u0007a\bE\u0002<\r\u000f!aA!\u0017\u0013\u0005\u0004q\u0004cA\u001e\u0007\f\u00111!q\u0017\nC\u0002y\u00022a\u000fD\b\t\u0019\u0019iB\u0005b\u0001}A\u00191Hb\u0005\u0005\r\r-%C1\u0001?!\rYdq\u0003\u0003\u0007\t\u0003\u0011\"\u0019\u0001 \u0011\u0007m2Y\u0002\u0002\u0004\u0005\u0000I\u0011\rA\u0010\t\u0004w\u0019}AABC\u0003%\t\u0007a\bE\u0002<\rG!a!b%\u0013\u0005\u0004q\u0004cA\u001e\u0007(\u00111a\u0011\u0006\nC\u0002y\u00121!Q\u00197\u0011\u00191%\u0003q\u0001\u0007.A!A'NCs\u0011\u00199&\u0003q\u0001\u00072A!A'NCu\u0011\u0019a'\u0003q\u0001\u00076A!A'NCw\u0011\u001d\tYA\u0005a\u0002\rs\u0001B\u0001N\u001b\u0006r\"9\u0011Q\t\nA\u0004\u0019u\u0002\u0003\u0002\u001b6\u000bkDq!a\"\u0013\u0001\b1\t\u0005\u0005\u00035k\u0015e\bbBAi%\u0001\u000faQ\t\t\u0005iU*i\u0010C\u0004\u0003$I\u0001\u001dA\"\u0013\u0011\tQ*d\u0011\u0001\u0005\b\u0005{\u0012\u00029\u0001D'!\u0011!TG\"\u0002\t\u000f\t}'\u0003q\u0001\u0007RA!A'\u000eD\u0005\u0011\u001d\u0019IE\u0005a\u0002\r+\u0002B\u0001N\u001b\u0007\u000e!911\u0018\nA\u0004\u0019e\u0003\u0003\u0002\u001b6\r#Aq\u0001\"\u000e\u0013\u0001\b1i\u0006\u0005\u00035k\u0019U\u0001b\u0002C\\%\u0001\u000fa\u0011\r\t\u0005iU2I\u0002C\u0004\u0006BI\u0001\u001dA\"\u001a\u0011\tQ*dQ\u0004\u0005\b\u000b'\u0014\u00029\u0001D5!\u0011!TG\"\t\t\u000f\u00195$\u0003q\u0001\u0007p\u0005\u0019\u0011)\r\u001c\u0011\tQ*dQE\u0001\u001aG\u0006$8oS3s]\u0016dwI]8va\u001a{'\u000fV;qY\u0016\f\u0004(\u0006\u0014\u0007v\u0019\u0005eQ\u0011DE\r\u001b3\tJ\"&\u0007\u001a\u001aue\u0011\u0015DS\rS3iK\"-\u00076\u001aefQ\u0018Da\r\u000b$bEb\u001e\u0007J\u001a5g\u0011\u001bDk\r34iN\"9\u0007f\u001a%hQ\u001eDy\rk4IP\"@\b\u0002\u001d\u0015q\u0011BD\u0007!\u0011!TG\"\u001f\u0011O\u00052YHb \u0007\u0004\u001a\u001de1\u0012DH\r'39Jb'\u0007 \u001a\rfq\u0015DV\r_3\u0019Lb.\u0007<\u001a}f1Y\u0005\u0004\r{\u0012#a\u0002+va2,\u0017\u0007\u000f\t\u0004w\u0019\u0005E!B\u001f\u0014\u0005\u0004q\u0004cA\u001e\u0007\u0006\u0012)1k\u0005b\u0001}A\u00191H\"#\u0005\u000b\u0019\u001c\"\u0019\u0001 \u0011\u0007m2i\tB\u0003~'\t\u0007a\bE\u0002<\r##a!!\r\u0014\u0005\u0004q\u0004cA\u001e\u0007\u0016\u00121\u0011qN\nC\u0002y\u00022a\u000fDM\t\u0019\t)l\u0005b\u0001}A\u00191H\"(\u0005\r\t\r1C1\u0001?!\rYd\u0011\u0015\u0003\u0007\u00053\u001a\"\u0019\u0001 \u0011\u0007m2)\u000b\u0002\u0004\u00038N\u0011\rA\u0010\t\u0004w\u0019%FABB\u000f'\t\u0007a\bE\u0002<\r[#aaa#\u0014\u0005\u0004q\u0004cA\u001e\u00072\u00121A\u0011A\nC\u0002y\u00022a\u000fD[\t\u0019!yh\u0005b\u0001}A\u00191H\"/\u0005\r\u0015\u00151C1\u0001?!\rYdQ\u0018\u0003\u0007\u000b'\u001b\"\u0019\u0001 \u0011\u0007m2\t\r\u0002\u0004\u0007*M\u0011\rA\u0010\t\u0004w\u0019\u0015GA\u0002Dd'\t\u0007aHA\u0002Bc]BaAR\nA\u0004\u0019-\u0007\u0003\u0002\u001b6\r\u007fBaaV\nA\u0004\u0019=\u0007\u0003\u0002\u001b6\r\u0007Ca\u0001\\\nA\u0004\u0019M\u0007\u0003\u0002\u001b6\r\u000fCq!a\u0003\u0014\u0001\b19\u000e\u0005\u00035k\u0019-\u0005bBA#'\u0001\u000fa1\u001c\t\u0005iU2y\tC\u0004\u0002\bN\u0001\u001dAb8\u0011\tQ*d1\u0013\u0005\b\u0003#\u001c\u00029\u0001Dr!\u0011!TGb&\t\u000f\t\r2\u0003q\u0001\u0007hB!A'\u000eDN\u0011\u001d\u0011ih\u0005a\u0002\rW\u0004B\u0001N\u001b\u0007 \"9!q\\\nA\u0004\u0019=\b\u0003\u0002\u001b6\rGCqa!\u0013\u0014\u0001\b1\u0019\u0010\u0005\u00035k\u0019\u001d\u0006bBB^'\u0001\u000faq\u001f\t\u0005iU2Y\u000bC\u0004\u00056M\u0001\u001dAb?\u0011\tQ*dq\u0016\u0005\b\to\u001b\u00029\u0001D\u0000!\u0011!TGb-\t\u000f\u0015\u00053\u0003q\u0001\b\u0004A!A'\u000eD\\\u0011\u001d)\u0019n\u0005a\u0002\u000f\u000f\u0001B\u0001N\u001b\u0007<\"9aQN\nA\u0004\u001d-\u0001\u0003\u0002\u001b6\r\u007fCqab\u0004\u0014\u0001\b9\t\"A\u0002Bc]\u0002B\u0001N\u001b\u0007D\u0006I2-\u0019;t\u0017\u0016\u0014h.\u001a7He>,\bOR8s)V\u0004H.Z\u0019:+!:9bb\t\b(\u001d-rqFD\u001a\u000fo9Ydb\u0010\bD\u001d\u001ds1JD(\u000f':9fb\u0017\b`\u001d\rtqMD6)!:Ibb\u001c\bt\u001d]t1PD@\u000f\u0007;9ib#\b\u0010\u001eMuqSDN\u000f?;\u0019kb*\b,\u001e=v1WD\\!\u0011!Tgb\u0007\u0011S\u0005:ib\"\t\b&\u001d%rQFD\u0019\u000fk9Id\"\u0010\bB\u001d\u0015s\u0011JD'\u000f#:)f\"\u0017\b^\u001d\u0005tQMD5\u0013\r9yB\t\u0002\b)V\u0004H.Z\u0019:!\rYt1\u0005\u0003\u0006{Q\u0011\rA\u0010\t\u0004w\u001d\u001dB!B*\u0015\u0005\u0004q\u0004cA\u001e\b,\u0011)a\r\u0006b\u0001}A\u00191hb\f\u0005\u000bu$\"\u0019\u0001 \u0011\u0007m:\u0019\u0004\u0002\u0004\u00022Q\u0011\rA\u0010\t\u0004w\u001d]BABA8)\t\u0007a\bE\u0002<\u000fw!a!!.\u0015\u0005\u0004q\u0004cA\u001e\b@\u00111!1\u0001\u000bC\u0002y\u00022aOD\"\t\u0019\u0011I\u0006\u0006b\u0001}A\u00191hb\u0012\u0005\r\t]FC1\u0001?!\rYt1\n\u0003\u0007\u0007;!\"\u0019\u0001 \u0011\u0007m:y\u0005\u0002\u0004\u0004\fR\u0011\rA\u0010\t\u0004w\u001dMCA\u0002C\u0001)\t\u0007a\bE\u0002<\u000f/\"a\u0001b \u0015\u0005\u0004q\u0004cA\u001e\b\\\u00111QQ\u0001\u000bC\u0002y\u00022aOD0\t\u0019)\u0019\n\u0006b\u0001}A\u00191hb\u0019\u0005\r\u0019%BC1\u0001?!\rYtq\r\u0003\u0007\r\u000f$\"\u0019\u0001 \u0011\u0007m:Y\u0007\u0002\u0004\bnQ\u0011\rA\u0010\u0002\u0004\u0003FB\u0004B\u0002$\u0015\u0001\b9\t\b\u0005\u00035k\u001d\u0005\u0002BB,\u0015\u0001\b9)\b\u0005\u00035k\u001d\u0015\u0002B\u00027\u0015\u0001\b9I\b\u0005\u00035k\u001d%\u0002bBA\u0006)\u0001\u000fqQ\u0010\t\u0005iU:i\u0003C\u0004\u0002FQ\u0001\u001da\"!\u0011\tQ*t\u0011\u0007\u0005\b\u0003\u000f#\u00029ADC!\u0011!Tg\"\u000e\t\u000f\u0005EG\u0003q\u0001\b\nB!A'ND\u001d\u0011\u001d\u0011\u0019\u0003\u0006a\u0002\u000f\u001b\u0003B\u0001N\u001b\b>!9!Q\u0010\u000bA\u0004\u001dE\u0005\u0003\u0002\u001b6\u000f\u0003BqAa8\u0015\u0001\b9)\n\u0005\u00035k\u001d\u0015\u0003bBB%)\u0001\u000fq\u0011\u0014\t\u0005iU:I\u0005C\u0004\u0004<R\u0001\u001da\"(\u0011\tQ*tQ\n\u0005\b\tk!\u00029ADQ!\u0011!Tg\"\u0015\t\u000f\u0011]F\u0003q\u0001\b&B!A'ND+\u0011\u001d)\t\u0005\u0006a\u0002\u000fS\u0003B\u0001N\u001b\bZ!9Q1\u001b\u000bA\u0004\u001d5\u0006\u0003\u0002\u001b6\u000f;BqA\"\u001c\u0015\u0001\b9\t\f\u0005\u00035k\u001d\u0005\u0004bBD\b)\u0001\u000fqQ\u0017\t\u0005iU:)\u0007C\u0004\b:R\u0001\u001dab/\u0002\u0007\u0005\u000b\u0004\b\u0005\u00035k\u001d%\u0014!G2biN\\UM\u001d8fY\u001e\u0013x.\u001e9G_J$V\u000f\u001d7feA*\"f\"1\bN\u001eEwQ[Dm\u000f;<\to\":\bj\u001e5x\u0011_D{\u000fs<i\u0010#\u0001\t\u0006!%\u0001R\u0002E\t\u0011+AI\u0002\u0006\u0016\bD\"u\u0001\u0012\u0005E\u0013\u0011SAi\u0003#\r\t6!e\u0002R\bE!\u0011\u000bBI\u0005#\u0014\tR!U\u0003\u0012\fE/\u0011CB)\u0007#\u001b\u0011\tQ*tQ\u0019\t,C\u001d\u001dw1ZDh\u000f'<9nb7\b`\u001e\rxq]Dv\u000f_<\u0019pb>\b|\u001e}\b2\u0001E\u0004\u0011\u0017Ay\u0001c\u0005\t\u0018%\u0019q\u0011\u001a\u0012\u0003\u000fQ+\b\u000f\\33aA\u00191h\"4\u0005\u000bu*\"\u0019\u0001 \u0011\u0007m:\t\u000eB\u0003T+\t\u0007a\bE\u0002<\u000f+$QAZ\u000bC\u0002y\u00022aODm\t\u0015iXC1\u0001?!\rYtQ\u001c\u0003\u0007\u0003c)\"\u0019\u0001 \u0011\u0007m:\t\u000f\u0002\u0004\u0002pU\u0011\rA\u0010\t\u0004w\u001d\u0015HABA[+\t\u0007a\bE\u0002<\u000fS$aAa\u0001\u0016\u0005\u0004q\u0004cA\u001e\bn\u00121!\u0011L\u000bC\u0002y\u00022aODy\t\u0019\u00119,\u0006b\u0001}A\u00191h\">\u0005\r\ruQC1\u0001?!\rYt\u0011 \u0003\u0007\u0007\u0017+\"\u0019\u0001 \u0011\u0007m:i\u0010\u0002\u0004\u0005\u0002U\u0011\rA\u0010\t\u0004w!\u0005AA\u0002C@+\t\u0007a\bE\u0002<\u0011\u000b!a!\"\u0002\u0016\u0005\u0004q\u0004cA\u001e\t\n\u00111Q1S\u000bC\u0002y\u00022a\u000fE\u0007\t\u00191I#\u0006b\u0001}A\u00191\b#\u0005\u0005\r\u0019\u001dWC1\u0001?!\rY\u0004R\u0003\u0003\u0007\u000f[*\"\u0019\u0001 \u0011\u0007mBI\u0002\u0002\u0004\t\u001cU\u0011\rA\u0010\u0002\u0004\u0003FJ\u0004B\u0002$\u0016\u0001\bAy\u0002\u0005\u00035k\u001d-\u0007BB,\u0016\u0001\bA\u0019\u0003\u0005\u00035k\u001d=\u0007B\u00027\u0016\u0001\bA9\u0003\u0005\u00035k\u001dM\u0007bBA\u0006+\u0001\u000f\u00012\u0006\t\u0005iU:9\u000eC\u0004\u0002FU\u0001\u001d\u0001c\f\u0011\tQ*t1\u001c\u0005\b\u0003\u000f+\u00029\u0001E\u001a!\u0011!Tgb8\t\u000f\u0005EW\u0003q\u0001\t8A!A'NDr\u0011\u001d\u0011\u0019#\u0006a\u0002\u0011w\u0001B\u0001N\u001b\bh\"9!QP\u000bA\u0004!}\u0002\u0003\u0002\u001b6\u000fWDqAa8\u0016\u0001\bA\u0019\u0005\u0005\u00035k\u001d=\bbBB%+\u0001\u000f\u0001r\t\t\u0005iU:\u0019\u0010C\u0004\u0004<V\u0001\u001d\u0001c\u0013\u0011\tQ*tq\u001f\u0005\b\tk)\u00029\u0001E(!\u0011!Tgb?\t\u000f\u0011]V\u0003q\u0001\tTA!A'ND\u0000\u0011\u001d)\t%\u0006a\u0002\u0011/\u0002B\u0001N\u001b\t\u0004!9Q1[\u000bA\u0004!m\u0003\u0003\u0002\u001b6\u0011\u000fAqA\"\u001c\u0016\u0001\bAy\u0006\u0005\u00035k!-\u0001bBD\b+\u0001\u000f\u00012\r\t\u0005iUBy\u0001C\u0004\b:V\u0001\u001d\u0001c\u001a\u0011\tQ*\u00042\u0003\u0005\b\u0011W*\u00029\u0001E7\u0003\r\t\u0015'\u000f\t\u0005iUB9\"A\rdCR\u001c8*\u001a:oK2<%o\\;q\r>\u0014H+\u001e9mKJ\nT\u0003\fE:\u0011\u007fB\u0019\tc\"\t\f\"=\u00052\u0013EL\u00117Cy\nc)\t(\"-\u0006r\u0016EZ\u0011oCY\fc0\tD\"\u001d\u00072\u001aEh)1B)\bc5\tX\"m\u0007r\u001cEr\u0011ODY\u000fc<\tt\"]\b2 E\u0000\u0013\u0007I9!c\u0003\n\u0010%M\u0011rCE\u000e\u0013?I\u0019\u0003\u0005\u00035k!]\u0004#L\u0011\tz!u\u0004\u0012\u0011EC\u0011\u0013Ci\t#%\t\u0016\"e\u0005R\u0014EQ\u0011KCI\u000b#,\t2\"U\u0006\u0012\u0018E_\u0011\u0003D)\r#3\tN&\u0019\u00012\u0010\u0012\u0003\u000fQ+\b\u000f\\33cA\u00191\bc \u0005\u000bu2\"\u0019\u0001 \u0011\u0007mB\u0019\tB\u0003T-\t\u0007a\bE\u0002<\u0011\u000f#QA\u001a\fC\u0002y\u00022a\u000fEF\t\u0015ihC1\u0001?!\rY\u0004r\u0012\u0003\u0007\u0003c1\"\u0019\u0001 \u0011\u0007mB\u0019\n\u0002\u0004\u0002pY\u0011\rA\u0010\t\u0004w!]EABA[-\t\u0007a\bE\u0002<\u00117#aAa\u0001\u0017\u0005\u0004q\u0004cA\u001e\t \u00121!\u0011\f\fC\u0002y\u00022a\u000fER\t\u0019\u00119L\u0006b\u0001}A\u00191\bc*\u0005\r\ruaC1\u0001?!\rY\u00042\u0016\u0003\u0007\u0007\u00173\"\u0019\u0001 \u0011\u0007mBy\u000b\u0002\u0004\u0005\u0002Y\u0011\rA\u0010\t\u0004w!MFA\u0002C@-\t\u0007a\bE\u0002<\u0011o#a!\"\u0002\u0017\u0005\u0004q\u0004cA\u001e\t<\u00121Q1\u0013\fC\u0002y\u00022a\u000fE`\t\u00191IC\u0006b\u0001}A\u00191\bc1\u0005\r\u0019\u001dgC1\u0001?!\rY\u0004r\u0019\u0003\u0007\u000f[2\"\u0019\u0001 \u0011\u0007mBY\r\u0002\u0004\t\u001cY\u0011\rA\u0010\t\u0004w!=GA\u0002Ei-\t\u0007aHA\u0002BeABaA\u0012\fA\u0004!U\u0007\u0003\u0002\u001b6\u0011{Baa\u0016\fA\u0004!e\u0007\u0003\u0002\u001b6\u0011\u0003Ca\u0001\u001c\fA\u0004!u\u0007\u0003\u0002\u001b6\u0011\u000bCq!a\u0003\u0017\u0001\bA\t\u000f\u0005\u00035k!%\u0005bBA#-\u0001\u000f\u0001R\u001d\t\u0005iUBi\tC\u0004\u0002\bZ\u0001\u001d\u0001#;\u0011\tQ*\u0004\u0012\u0013\u0005\b\u0003#4\u00029\u0001Ew!\u0011!T\u0007#&\t\u000f\t\rb\u0003q\u0001\trB!A'\u000eEM\u0011\u001d\u0011iH\u0006a\u0002\u0011k\u0004B\u0001N\u001b\t\u001e\"9!q\u001c\fA\u0004!e\b\u0003\u0002\u001b6\u0011CCqa!\u0013\u0017\u0001\bAi\u0010\u0005\u00035k!\u0015\u0006bBB^-\u0001\u000f\u0011\u0012\u0001\t\u0005iUBI\u000bC\u0004\u00056Y\u0001\u001d!#\u0002\u0011\tQ*\u0004R\u0016\u0005\b\to3\u00029AE\u0005!\u0011!T\u0007#-\t\u000f\u0015\u0005c\u0003q\u0001\n\u000eA!A'\u000eE[\u0011\u001d)\u0019N\u0006a\u0002\u0013#\u0001B\u0001N\u001b\t:\"9aQ\u000e\fA\u0004%U\u0001\u0003\u0002\u001b6\u0011{Cqab\u0004\u0017\u0001\bII\u0002\u0005\u00035k!\u0005\u0007bBD]-\u0001\u000f\u0011R\u0004\t\u0005iUB)\rC\u0004\tlY\u0001\u001d!#\t\u0011\tQ*\u0004\u0012\u001a\u0005\b\u0013K1\u00029AE\u0014\u0003\r\t%\u0007\r\t\u0005iUBi-A\rdCR\u001c8*\u001a:oK2<%o\\;q\r>\u0014H+\u001e9mKJ\u0012TCLE\u0017\u0013sIi$#\u0011\nF%%\u0013RJE)\u0013+JI&#\u0018\nb%\u0015\u0014\u0012NE7\u0013cJ)(#\u001f\n~%\u0005\u0015RQEE\u0013\u001b#b&c\f\n\u0012&U\u0015\u0012TEO\u0013CK)+#+\n.&E\u0016RWE]\u0013{K\t-#2\nJ&5\u0017\u0012[Ek\u00133Li.#9\nfB!A'NE\u0019!=\n\u00132GE\u001c\u0013wIy$c\u0011\nH%-\u0013rJE*\u0013/JY&c\u0018\nd%\u001d\u00142NE8\u0013gJ9(c\u001f\n\u0000%\r\u0015rQEF\u0013\rI)D\t\u0002\b)V\u0004H.\u001a\u001a3!\rY\u0014\u0012\b\u0003\u0006{]\u0011\rA\u0010\t\u0004w%uB!B*\u0018\u0005\u0004q\u0004cA\u001e\nB\u0011)am\u0006b\u0001}A\u00191(#\u0012\u0005\u000bu<\"\u0019\u0001 \u0011\u0007mJI\u0005\u0002\u0004\u00022]\u0011\rA\u0010\t\u0004w%5CABA8/\t\u0007a\bE\u0002<\u0013#\"a!!.\u0018\u0005\u0004q\u0004cA\u001e\nV\u00111!1A\fC\u0002y\u00022aOE-\t\u0019\u0011If\u0006b\u0001}A\u00191(#\u0018\u0005\r\t]vC1\u0001?!\rY\u0014\u0012\r\u0003\u0007\u0007;9\"\u0019\u0001 \u0011\u0007mJ)\u0007\u0002\u0004\u0004\f^\u0011\rA\u0010\t\u0004w%%DA\u0002C\u0001/\t\u0007a\bE\u0002<\u0013[\"a\u0001b \u0018\u0005\u0004q\u0004cA\u001e\nr\u00111QQA\fC\u0002y\u00022aOE;\t\u0019)\u0019j\u0006b\u0001}A\u00191(#\u001f\u0005\r\u0019%rC1\u0001?!\rY\u0014R\u0010\u0003\u0007\r\u000f<\"\u0019\u0001 \u0011\u0007mJ\t\t\u0002\u0004\bn]\u0011\rA\u0010\t\u0004w%\u0015EA\u0002E\u000e/\t\u0007a\bE\u0002<\u0013\u0013#a\u0001#5\u0018\u0005\u0004q\u0004cA\u001e\n\u000e\u00121\u0011rR\fC\u0002y\u00121!\u0011\u001a2\u0011\u00191u\u0003q\u0001\n\u0014B!A'NE\u001c\u0011\u00199v\u0003q\u0001\n\u0018B!A'NE\u001e\u0011\u0019aw\u0003q\u0001\n\u001cB!A'NE \u0011\u001d\tYa\u0006a\u0002\u0013?\u0003B\u0001N\u001b\nD!9\u0011QI\fA\u0004%\r\u0006\u0003\u0002\u001b6\u0013\u000fBq!a\"\u0018\u0001\bI9\u000b\u0005\u00035k%-\u0003bBAi/\u0001\u000f\u00112\u0016\t\u0005iUJy\u0005C\u0004\u0003$]\u0001\u001d!c,\u0011\tQ*\u00142\u000b\u0005\b\u0005{:\u00029AEZ!\u0011!T'c\u0016\t\u000f\t}w\u0003q\u0001\n8B!A'NE.\u0011\u001d\u0019Ie\u0006a\u0002\u0013w\u0003B\u0001N\u001b\n`!911X\fA\u0004%}\u0006\u0003\u0002\u001b6\u0013GBq\u0001\"\u000e\u0018\u0001\bI\u0019\r\u0005\u00035k%\u001d\u0004b\u0002C\\/\u0001\u000f\u0011r\u0019\t\u0005iUJY\u0007C\u0004\u0006B]\u0001\u001d!c3\u0011\tQ*\u0014r\u000e\u0005\b\u000b'<\u00029AEh!\u0011!T'c\u001d\t\u000f\u00195t\u0003q\u0001\nTB!A'NE<\u0011\u001d9ya\u0006a\u0002\u0013/\u0004B\u0001N\u001b\n|!9q\u0011X\fA\u0004%m\u0007\u0003\u0002\u001b6\u0013\u007fBq\u0001c\u001b\u0018\u0001\bIy\u000e\u0005\u00035k%\r\u0005bBE\u0013/\u0001\u000f\u00112\u001d\t\u0005iUJ9\tC\u0004\nh^\u0001\u001d!#;\u0002\u0007\u0005\u0013\u0014\u0007\u0005\u00035k%-\u0005"
)
public interface TupleGroupInstances extends TupleCommutativeMonoidInstances {
   // $FF: synthetic method
   static Group catsKernelGroupForTuple1$(final TupleGroupInstances $this, final Group A0) {
      return $this.catsKernelGroupForTuple1(A0);
   }

   default Group catsKernelGroupForTuple1(final Group A0) {
      return new Group(A0) {
         private final Group A0$419;

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public Object remove(final Object a, final Object b) {
            return Group.remove$(this, a, b);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public Monoid reverse() {
            return Monoid.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Tuple1 combine(final Tuple1 x, final Tuple1 y) {
            return new Tuple1(this.A0$419.combine(x._1(), y._1()));
         }

         public Tuple1 empty() {
            return new Tuple1(this.A0$419.empty());
         }

         public Tuple1 inverse(final Tuple1 x) {
            return new Tuple1(this.A0$419.inverse(x._1()));
         }

         public {
            this.A0$419 = A0$419;
            Semigroup.$init$(this);
            Monoid.$init$(this);
            Group.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Group catsKernelGroupForTuple2$(final TupleGroupInstances $this, final Group A0, final Group A1) {
      return $this.catsKernelGroupForTuple2(A0, A1);
   }

   default Group catsKernelGroupForTuple2(final Group A0, final Group A1) {
      return new Group(A0, A1) {
         private final Group A0$420;
         private final Group A1$400;

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public Object remove(final Object a, final Object b) {
            return Group.remove$(this, a, b);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public Monoid reverse() {
            return Monoid.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Tuple2 combine(final Tuple2 x, final Tuple2 y) {
            return new Tuple2(this.A0$420.combine(x._1(), y._1()), this.A1$400.combine(x._2(), y._2()));
         }

         public Tuple2 empty() {
            return new Tuple2(this.A0$420.empty(), this.A1$400.empty());
         }

         public Tuple2 inverse(final Tuple2 x) {
            return new Tuple2(this.A0$420.inverse(x._1()), this.A1$400.inverse(x._2()));
         }

         public {
            this.A0$420 = A0$420;
            this.A1$400 = A1$400;
            Semigroup.$init$(this);
            Monoid.$init$(this);
            Group.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Group catsKernelGroupForTuple3$(final TupleGroupInstances $this, final Group A0, final Group A1, final Group A2) {
      return $this.catsKernelGroupForTuple3(A0, A1, A2);
   }

   default Group catsKernelGroupForTuple3(final Group A0, final Group A1, final Group A2) {
      return new Group(A0, A1, A2) {
         private final Group A0$421;
         private final Group A1$401;
         private final Group A2$381;

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public Object remove(final Object a, final Object b) {
            return Group.remove$(this, a, b);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public Monoid reverse() {
            return Monoid.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Tuple3 combine(final Tuple3 x, final Tuple3 y) {
            return new Tuple3(this.A0$421.combine(x._1(), y._1()), this.A1$401.combine(x._2(), y._2()), this.A2$381.combine(x._3(), y._3()));
         }

         public Tuple3 empty() {
            return new Tuple3(this.A0$421.empty(), this.A1$401.empty(), this.A2$381.empty());
         }

         public Tuple3 inverse(final Tuple3 x) {
            return new Tuple3(this.A0$421.inverse(x._1()), this.A1$401.inverse(x._2()), this.A2$381.inverse(x._3()));
         }

         public {
            this.A0$421 = A0$421;
            this.A1$401 = A1$401;
            this.A2$381 = A2$381;
            Semigroup.$init$(this);
            Monoid.$init$(this);
            Group.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Group catsKernelGroupForTuple4$(final TupleGroupInstances $this, final Group A0, final Group A1, final Group A2, final Group A3) {
      return $this.catsKernelGroupForTuple4(A0, A1, A2, A3);
   }

   default Group catsKernelGroupForTuple4(final Group A0, final Group A1, final Group A2, final Group A3) {
      return new Group(A0, A1, A2, A3) {
         private final Group A0$422;
         private final Group A1$402;
         private final Group A2$382;
         private final Group A3$362;

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public Object remove(final Object a, final Object b) {
            return Group.remove$(this, a, b);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public Monoid reverse() {
            return Monoid.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Tuple4 combine(final Tuple4 x, final Tuple4 y) {
            return new Tuple4(this.A0$422.combine(x._1(), y._1()), this.A1$402.combine(x._2(), y._2()), this.A2$382.combine(x._3(), y._3()), this.A3$362.combine(x._4(), y._4()));
         }

         public Tuple4 empty() {
            return new Tuple4(this.A0$422.empty(), this.A1$402.empty(), this.A2$382.empty(), this.A3$362.empty());
         }

         public Tuple4 inverse(final Tuple4 x) {
            return new Tuple4(this.A0$422.inverse(x._1()), this.A1$402.inverse(x._2()), this.A2$382.inverse(x._3()), this.A3$362.inverse(x._4()));
         }

         public {
            this.A0$422 = A0$422;
            this.A1$402 = A1$402;
            this.A2$382 = A2$382;
            this.A3$362 = A3$362;
            Semigroup.$init$(this);
            Monoid.$init$(this);
            Group.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Group catsKernelGroupForTuple5$(final TupleGroupInstances $this, final Group A0, final Group A1, final Group A2, final Group A3, final Group A4) {
      return $this.catsKernelGroupForTuple5(A0, A1, A2, A3, A4);
   }

   default Group catsKernelGroupForTuple5(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4) {
      return new Group(A0, A1, A2, A3, A4) {
         private final Group A0$423;
         private final Group A1$403;
         private final Group A2$383;
         private final Group A3$363;
         private final Group A4$343;

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public Object remove(final Object a, final Object b) {
            return Group.remove$(this, a, b);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public Monoid reverse() {
            return Monoid.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Tuple5 combine(final Tuple5 x, final Tuple5 y) {
            return new Tuple5(this.A0$423.combine(x._1(), y._1()), this.A1$403.combine(x._2(), y._2()), this.A2$383.combine(x._3(), y._3()), this.A3$363.combine(x._4(), y._4()), this.A4$343.combine(x._5(), y._5()));
         }

         public Tuple5 empty() {
            return new Tuple5(this.A0$423.empty(), this.A1$403.empty(), this.A2$383.empty(), this.A3$363.empty(), this.A4$343.empty());
         }

         public Tuple5 inverse(final Tuple5 x) {
            return new Tuple5(this.A0$423.inverse(x._1()), this.A1$403.inverse(x._2()), this.A2$383.inverse(x._3()), this.A3$363.inverse(x._4()), this.A4$343.inverse(x._5()));
         }

         public {
            this.A0$423 = A0$423;
            this.A1$403 = A1$403;
            this.A2$383 = A2$383;
            this.A3$363 = A3$363;
            this.A4$343 = A4$343;
            Semigroup.$init$(this);
            Monoid.$init$(this);
            Group.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Group catsKernelGroupForTuple6$(final TupleGroupInstances $this, final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5) {
      return $this.catsKernelGroupForTuple6(A0, A1, A2, A3, A4, A5);
   }

   default Group catsKernelGroupForTuple6(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5) {
      return new Group(A0, A1, A2, A3, A4, A5) {
         private final Group A0$424;
         private final Group A1$404;
         private final Group A2$384;
         private final Group A3$364;
         private final Group A4$344;
         private final Group A5$324;

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public Object remove(final Object a, final Object b) {
            return Group.remove$(this, a, b);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public Monoid reverse() {
            return Monoid.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Tuple6 combine(final Tuple6 x, final Tuple6 y) {
            return new Tuple6(this.A0$424.combine(x._1(), y._1()), this.A1$404.combine(x._2(), y._2()), this.A2$384.combine(x._3(), y._3()), this.A3$364.combine(x._4(), y._4()), this.A4$344.combine(x._5(), y._5()), this.A5$324.combine(x._6(), y._6()));
         }

         public Tuple6 empty() {
            return new Tuple6(this.A0$424.empty(), this.A1$404.empty(), this.A2$384.empty(), this.A3$364.empty(), this.A4$344.empty(), this.A5$324.empty());
         }

         public Tuple6 inverse(final Tuple6 x) {
            return new Tuple6(this.A0$424.inverse(x._1()), this.A1$404.inverse(x._2()), this.A2$384.inverse(x._3()), this.A3$364.inverse(x._4()), this.A4$344.inverse(x._5()), this.A5$324.inverse(x._6()));
         }

         public {
            this.A0$424 = A0$424;
            this.A1$404 = A1$404;
            this.A2$384 = A2$384;
            this.A3$364 = A3$364;
            this.A4$344 = A4$344;
            this.A5$324 = A5$324;
            Semigroup.$init$(this);
            Monoid.$init$(this);
            Group.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Group catsKernelGroupForTuple7$(final TupleGroupInstances $this, final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6) {
      return $this.catsKernelGroupForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   default Group catsKernelGroupForTuple7(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6) {
      return new Group(A0, A1, A2, A3, A4, A5, A6) {
         private final Group A0$425;
         private final Group A1$405;
         private final Group A2$385;
         private final Group A3$365;
         private final Group A4$345;
         private final Group A5$325;
         private final Group A6$305;

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public Object remove(final Object a, final Object b) {
            return Group.remove$(this, a, b);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public Monoid reverse() {
            return Monoid.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Tuple7 combine(final Tuple7 x, final Tuple7 y) {
            return new Tuple7(this.A0$425.combine(x._1(), y._1()), this.A1$405.combine(x._2(), y._2()), this.A2$385.combine(x._3(), y._3()), this.A3$365.combine(x._4(), y._4()), this.A4$345.combine(x._5(), y._5()), this.A5$325.combine(x._6(), y._6()), this.A6$305.combine(x._7(), y._7()));
         }

         public Tuple7 empty() {
            return new Tuple7(this.A0$425.empty(), this.A1$405.empty(), this.A2$385.empty(), this.A3$365.empty(), this.A4$345.empty(), this.A5$325.empty(), this.A6$305.empty());
         }

         public Tuple7 inverse(final Tuple7 x) {
            return new Tuple7(this.A0$425.inverse(x._1()), this.A1$405.inverse(x._2()), this.A2$385.inverse(x._3()), this.A3$365.inverse(x._4()), this.A4$345.inverse(x._5()), this.A5$325.inverse(x._6()), this.A6$305.inverse(x._7()));
         }

         public {
            this.A0$425 = A0$425;
            this.A1$405 = A1$405;
            this.A2$385 = A2$385;
            this.A3$365 = A3$365;
            this.A4$345 = A4$345;
            this.A5$325 = A5$325;
            this.A6$305 = A6$305;
            Semigroup.$init$(this);
            Monoid.$init$(this);
            Group.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Group catsKernelGroupForTuple8$(final TupleGroupInstances $this, final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7) {
      return $this.catsKernelGroupForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   default Group catsKernelGroupForTuple8(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7) {
      return new Group(A0, A1, A2, A3, A4, A5, A6, A7) {
         private final Group A0$426;
         private final Group A1$406;
         private final Group A2$386;
         private final Group A3$366;
         private final Group A4$346;
         private final Group A5$326;
         private final Group A6$306;
         private final Group A7$286;

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public Object remove(final Object a, final Object b) {
            return Group.remove$(this, a, b);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public Monoid reverse() {
            return Monoid.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Tuple8 combine(final Tuple8 x, final Tuple8 y) {
            return new Tuple8(this.A0$426.combine(x._1(), y._1()), this.A1$406.combine(x._2(), y._2()), this.A2$386.combine(x._3(), y._3()), this.A3$366.combine(x._4(), y._4()), this.A4$346.combine(x._5(), y._5()), this.A5$326.combine(x._6(), y._6()), this.A6$306.combine(x._7(), y._7()), this.A7$286.combine(x._8(), y._8()));
         }

         public Tuple8 empty() {
            return new Tuple8(this.A0$426.empty(), this.A1$406.empty(), this.A2$386.empty(), this.A3$366.empty(), this.A4$346.empty(), this.A5$326.empty(), this.A6$306.empty(), this.A7$286.empty());
         }

         public Tuple8 inverse(final Tuple8 x) {
            return new Tuple8(this.A0$426.inverse(x._1()), this.A1$406.inverse(x._2()), this.A2$386.inverse(x._3()), this.A3$366.inverse(x._4()), this.A4$346.inverse(x._5()), this.A5$326.inverse(x._6()), this.A6$306.inverse(x._7()), this.A7$286.inverse(x._8()));
         }

         public {
            this.A0$426 = A0$426;
            this.A1$406 = A1$406;
            this.A2$386 = A2$386;
            this.A3$366 = A3$366;
            this.A4$346 = A4$346;
            this.A5$326 = A5$326;
            this.A6$306 = A6$306;
            this.A7$286 = A7$286;
            Semigroup.$init$(this);
            Monoid.$init$(this);
            Group.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Group catsKernelGroupForTuple9$(final TupleGroupInstances $this, final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8) {
      return $this.catsKernelGroupForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   default Group catsKernelGroupForTuple9(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8) {
      return new Group(A0, A1, A2, A3, A4, A5, A6, A7, A8) {
         private final Group A0$427;
         private final Group A1$407;
         private final Group A2$387;
         private final Group A3$367;
         private final Group A4$347;
         private final Group A5$327;
         private final Group A6$307;
         private final Group A7$287;
         private final Group A8$267;

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public Object remove(final Object a, final Object b) {
            return Group.remove$(this, a, b);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public Monoid reverse() {
            return Monoid.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Tuple9 combine(final Tuple9 x, final Tuple9 y) {
            return new Tuple9(this.A0$427.combine(x._1(), y._1()), this.A1$407.combine(x._2(), y._2()), this.A2$387.combine(x._3(), y._3()), this.A3$367.combine(x._4(), y._4()), this.A4$347.combine(x._5(), y._5()), this.A5$327.combine(x._6(), y._6()), this.A6$307.combine(x._7(), y._7()), this.A7$287.combine(x._8(), y._8()), this.A8$267.combine(x._9(), y._9()));
         }

         public Tuple9 empty() {
            return new Tuple9(this.A0$427.empty(), this.A1$407.empty(), this.A2$387.empty(), this.A3$367.empty(), this.A4$347.empty(), this.A5$327.empty(), this.A6$307.empty(), this.A7$287.empty(), this.A8$267.empty());
         }

         public Tuple9 inverse(final Tuple9 x) {
            return new Tuple9(this.A0$427.inverse(x._1()), this.A1$407.inverse(x._2()), this.A2$387.inverse(x._3()), this.A3$367.inverse(x._4()), this.A4$347.inverse(x._5()), this.A5$327.inverse(x._6()), this.A6$307.inverse(x._7()), this.A7$287.inverse(x._8()), this.A8$267.inverse(x._9()));
         }

         public {
            this.A0$427 = A0$427;
            this.A1$407 = A1$407;
            this.A2$387 = A2$387;
            this.A3$367 = A3$367;
            this.A4$347 = A4$347;
            this.A5$327 = A5$327;
            this.A6$307 = A6$307;
            this.A7$287 = A7$287;
            this.A8$267 = A8$267;
            Semigroup.$init$(this);
            Monoid.$init$(this);
            Group.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Group catsKernelGroupForTuple10$(final TupleGroupInstances $this, final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9) {
      return $this.catsKernelGroupForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   default Group catsKernelGroupForTuple10(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9) {
      return new Group(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9) {
         private final Group A0$428;
         private final Group A1$408;
         private final Group A2$388;
         private final Group A3$368;
         private final Group A4$348;
         private final Group A5$328;
         private final Group A6$308;
         private final Group A7$288;
         private final Group A8$268;
         private final Group A9$248;

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public Object remove(final Object a, final Object b) {
            return Group.remove$(this, a, b);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public Monoid reverse() {
            return Monoid.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Tuple10 combine(final Tuple10 x, final Tuple10 y) {
            return new Tuple10(this.A0$428.combine(x._1(), y._1()), this.A1$408.combine(x._2(), y._2()), this.A2$388.combine(x._3(), y._3()), this.A3$368.combine(x._4(), y._4()), this.A4$348.combine(x._5(), y._5()), this.A5$328.combine(x._6(), y._6()), this.A6$308.combine(x._7(), y._7()), this.A7$288.combine(x._8(), y._8()), this.A8$268.combine(x._9(), y._9()), this.A9$248.combine(x._10(), y._10()));
         }

         public Tuple10 empty() {
            return new Tuple10(this.A0$428.empty(), this.A1$408.empty(), this.A2$388.empty(), this.A3$368.empty(), this.A4$348.empty(), this.A5$328.empty(), this.A6$308.empty(), this.A7$288.empty(), this.A8$268.empty(), this.A9$248.empty());
         }

         public Tuple10 inverse(final Tuple10 x) {
            return new Tuple10(this.A0$428.inverse(x._1()), this.A1$408.inverse(x._2()), this.A2$388.inverse(x._3()), this.A3$368.inverse(x._4()), this.A4$348.inverse(x._5()), this.A5$328.inverse(x._6()), this.A6$308.inverse(x._7()), this.A7$288.inverse(x._8()), this.A8$268.inverse(x._9()), this.A9$248.inverse(x._10()));
         }

         public {
            this.A0$428 = A0$428;
            this.A1$408 = A1$408;
            this.A2$388 = A2$388;
            this.A3$368 = A3$368;
            this.A4$348 = A4$348;
            this.A5$328 = A5$328;
            this.A6$308 = A6$308;
            this.A7$288 = A7$288;
            this.A8$268 = A8$268;
            this.A9$248 = A9$248;
            Semigroup.$init$(this);
            Monoid.$init$(this);
            Group.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Group catsKernelGroupForTuple11$(final TupleGroupInstances $this, final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10) {
      return $this.catsKernelGroupForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   default Group catsKernelGroupForTuple11(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10) {
      return new Group(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) {
         private final Group A0$429;
         private final Group A1$409;
         private final Group A2$389;
         private final Group A3$369;
         private final Group A4$349;
         private final Group A5$329;
         private final Group A6$309;
         private final Group A7$289;
         private final Group A8$269;
         private final Group A9$249;
         private final Group A10$229;

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public Object remove(final Object a, final Object b) {
            return Group.remove$(this, a, b);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public Monoid reverse() {
            return Monoid.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Tuple11 combine(final Tuple11 x, final Tuple11 y) {
            return new Tuple11(this.A0$429.combine(x._1(), y._1()), this.A1$409.combine(x._2(), y._2()), this.A2$389.combine(x._3(), y._3()), this.A3$369.combine(x._4(), y._4()), this.A4$349.combine(x._5(), y._5()), this.A5$329.combine(x._6(), y._6()), this.A6$309.combine(x._7(), y._7()), this.A7$289.combine(x._8(), y._8()), this.A8$269.combine(x._9(), y._9()), this.A9$249.combine(x._10(), y._10()), this.A10$229.combine(x._11(), y._11()));
         }

         public Tuple11 empty() {
            return new Tuple11(this.A0$429.empty(), this.A1$409.empty(), this.A2$389.empty(), this.A3$369.empty(), this.A4$349.empty(), this.A5$329.empty(), this.A6$309.empty(), this.A7$289.empty(), this.A8$269.empty(), this.A9$249.empty(), this.A10$229.empty());
         }

         public Tuple11 inverse(final Tuple11 x) {
            return new Tuple11(this.A0$429.inverse(x._1()), this.A1$409.inverse(x._2()), this.A2$389.inverse(x._3()), this.A3$369.inverse(x._4()), this.A4$349.inverse(x._5()), this.A5$329.inverse(x._6()), this.A6$309.inverse(x._7()), this.A7$289.inverse(x._8()), this.A8$269.inverse(x._9()), this.A9$249.inverse(x._10()), this.A10$229.inverse(x._11()));
         }

         public {
            this.A0$429 = A0$429;
            this.A1$409 = A1$409;
            this.A2$389 = A2$389;
            this.A3$369 = A3$369;
            this.A4$349 = A4$349;
            this.A5$329 = A5$329;
            this.A6$309 = A6$309;
            this.A7$289 = A7$289;
            this.A8$269 = A8$269;
            this.A9$249 = A9$249;
            this.A10$229 = A10$229;
            Semigroup.$init$(this);
            Monoid.$init$(this);
            Group.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Group catsKernelGroupForTuple12$(final TupleGroupInstances $this, final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11) {
      return $this.catsKernelGroupForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   default Group catsKernelGroupForTuple12(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11) {
      return new Group(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) {
         private final Group A0$430;
         private final Group A1$410;
         private final Group A2$390;
         private final Group A3$370;
         private final Group A4$350;
         private final Group A5$330;
         private final Group A6$310;
         private final Group A7$290;
         private final Group A8$270;
         private final Group A9$250;
         private final Group A10$230;
         private final Group A11$210;

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public Object remove(final Object a, final Object b) {
            return Group.remove$(this, a, b);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public Monoid reverse() {
            return Monoid.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Tuple12 combine(final Tuple12 x, final Tuple12 y) {
            return new Tuple12(this.A0$430.combine(x._1(), y._1()), this.A1$410.combine(x._2(), y._2()), this.A2$390.combine(x._3(), y._3()), this.A3$370.combine(x._4(), y._4()), this.A4$350.combine(x._5(), y._5()), this.A5$330.combine(x._6(), y._6()), this.A6$310.combine(x._7(), y._7()), this.A7$290.combine(x._8(), y._8()), this.A8$270.combine(x._9(), y._9()), this.A9$250.combine(x._10(), y._10()), this.A10$230.combine(x._11(), y._11()), this.A11$210.combine(x._12(), y._12()));
         }

         public Tuple12 empty() {
            return new Tuple12(this.A0$430.empty(), this.A1$410.empty(), this.A2$390.empty(), this.A3$370.empty(), this.A4$350.empty(), this.A5$330.empty(), this.A6$310.empty(), this.A7$290.empty(), this.A8$270.empty(), this.A9$250.empty(), this.A10$230.empty(), this.A11$210.empty());
         }

         public Tuple12 inverse(final Tuple12 x) {
            return new Tuple12(this.A0$430.inverse(x._1()), this.A1$410.inverse(x._2()), this.A2$390.inverse(x._3()), this.A3$370.inverse(x._4()), this.A4$350.inverse(x._5()), this.A5$330.inverse(x._6()), this.A6$310.inverse(x._7()), this.A7$290.inverse(x._8()), this.A8$270.inverse(x._9()), this.A9$250.inverse(x._10()), this.A10$230.inverse(x._11()), this.A11$210.inverse(x._12()));
         }

         public {
            this.A0$430 = A0$430;
            this.A1$410 = A1$410;
            this.A2$390 = A2$390;
            this.A3$370 = A3$370;
            this.A4$350 = A4$350;
            this.A5$330 = A5$330;
            this.A6$310 = A6$310;
            this.A7$290 = A7$290;
            this.A8$270 = A8$270;
            this.A9$250 = A9$250;
            this.A10$230 = A10$230;
            this.A11$210 = A11$210;
            Semigroup.$init$(this);
            Monoid.$init$(this);
            Group.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Group catsKernelGroupForTuple13$(final TupleGroupInstances $this, final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12) {
      return $this.catsKernelGroupForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   default Group catsKernelGroupForTuple13(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12) {
      return new Group(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) {
         private final Group A0$431;
         private final Group A1$411;
         private final Group A2$391;
         private final Group A3$371;
         private final Group A4$351;
         private final Group A5$331;
         private final Group A6$311;
         private final Group A7$291;
         private final Group A8$271;
         private final Group A9$251;
         private final Group A10$231;
         private final Group A11$211;
         private final Group A12$191;

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public Object remove(final Object a, final Object b) {
            return Group.remove$(this, a, b);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public Monoid reverse() {
            return Monoid.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Tuple13 combine(final Tuple13 x, final Tuple13 y) {
            return new Tuple13(this.A0$431.combine(x._1(), y._1()), this.A1$411.combine(x._2(), y._2()), this.A2$391.combine(x._3(), y._3()), this.A3$371.combine(x._4(), y._4()), this.A4$351.combine(x._5(), y._5()), this.A5$331.combine(x._6(), y._6()), this.A6$311.combine(x._7(), y._7()), this.A7$291.combine(x._8(), y._8()), this.A8$271.combine(x._9(), y._9()), this.A9$251.combine(x._10(), y._10()), this.A10$231.combine(x._11(), y._11()), this.A11$211.combine(x._12(), y._12()), this.A12$191.combine(x._13(), y._13()));
         }

         public Tuple13 empty() {
            return new Tuple13(this.A0$431.empty(), this.A1$411.empty(), this.A2$391.empty(), this.A3$371.empty(), this.A4$351.empty(), this.A5$331.empty(), this.A6$311.empty(), this.A7$291.empty(), this.A8$271.empty(), this.A9$251.empty(), this.A10$231.empty(), this.A11$211.empty(), this.A12$191.empty());
         }

         public Tuple13 inverse(final Tuple13 x) {
            return new Tuple13(this.A0$431.inverse(x._1()), this.A1$411.inverse(x._2()), this.A2$391.inverse(x._3()), this.A3$371.inverse(x._4()), this.A4$351.inverse(x._5()), this.A5$331.inverse(x._6()), this.A6$311.inverse(x._7()), this.A7$291.inverse(x._8()), this.A8$271.inverse(x._9()), this.A9$251.inverse(x._10()), this.A10$231.inverse(x._11()), this.A11$211.inverse(x._12()), this.A12$191.inverse(x._13()));
         }

         public {
            this.A0$431 = A0$431;
            this.A1$411 = A1$411;
            this.A2$391 = A2$391;
            this.A3$371 = A3$371;
            this.A4$351 = A4$351;
            this.A5$331 = A5$331;
            this.A6$311 = A6$311;
            this.A7$291 = A7$291;
            this.A8$271 = A8$271;
            this.A9$251 = A9$251;
            this.A10$231 = A10$231;
            this.A11$211 = A11$211;
            this.A12$191 = A12$191;
            Semigroup.$init$(this);
            Monoid.$init$(this);
            Group.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Group catsKernelGroupForTuple14$(final TupleGroupInstances $this, final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13) {
      return $this.catsKernelGroupForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   default Group catsKernelGroupForTuple14(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13) {
      return new Group(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) {
         private final Group A0$432;
         private final Group A1$412;
         private final Group A2$392;
         private final Group A3$372;
         private final Group A4$352;
         private final Group A5$332;
         private final Group A6$312;
         private final Group A7$292;
         private final Group A8$272;
         private final Group A9$252;
         private final Group A10$232;
         private final Group A11$212;
         private final Group A12$192;
         private final Group A13$172;

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public Object remove(final Object a, final Object b) {
            return Group.remove$(this, a, b);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public Monoid reverse() {
            return Monoid.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Tuple14 combine(final Tuple14 x, final Tuple14 y) {
            return new Tuple14(this.A0$432.combine(x._1(), y._1()), this.A1$412.combine(x._2(), y._2()), this.A2$392.combine(x._3(), y._3()), this.A3$372.combine(x._4(), y._4()), this.A4$352.combine(x._5(), y._5()), this.A5$332.combine(x._6(), y._6()), this.A6$312.combine(x._7(), y._7()), this.A7$292.combine(x._8(), y._8()), this.A8$272.combine(x._9(), y._9()), this.A9$252.combine(x._10(), y._10()), this.A10$232.combine(x._11(), y._11()), this.A11$212.combine(x._12(), y._12()), this.A12$192.combine(x._13(), y._13()), this.A13$172.combine(x._14(), y._14()));
         }

         public Tuple14 empty() {
            return new Tuple14(this.A0$432.empty(), this.A1$412.empty(), this.A2$392.empty(), this.A3$372.empty(), this.A4$352.empty(), this.A5$332.empty(), this.A6$312.empty(), this.A7$292.empty(), this.A8$272.empty(), this.A9$252.empty(), this.A10$232.empty(), this.A11$212.empty(), this.A12$192.empty(), this.A13$172.empty());
         }

         public Tuple14 inverse(final Tuple14 x) {
            return new Tuple14(this.A0$432.inverse(x._1()), this.A1$412.inverse(x._2()), this.A2$392.inverse(x._3()), this.A3$372.inverse(x._4()), this.A4$352.inverse(x._5()), this.A5$332.inverse(x._6()), this.A6$312.inverse(x._7()), this.A7$292.inverse(x._8()), this.A8$272.inverse(x._9()), this.A9$252.inverse(x._10()), this.A10$232.inverse(x._11()), this.A11$212.inverse(x._12()), this.A12$192.inverse(x._13()), this.A13$172.inverse(x._14()));
         }

         public {
            this.A0$432 = A0$432;
            this.A1$412 = A1$412;
            this.A2$392 = A2$392;
            this.A3$372 = A3$372;
            this.A4$352 = A4$352;
            this.A5$332 = A5$332;
            this.A6$312 = A6$312;
            this.A7$292 = A7$292;
            this.A8$272 = A8$272;
            this.A9$252 = A9$252;
            this.A10$232 = A10$232;
            this.A11$212 = A11$212;
            this.A12$192 = A12$192;
            this.A13$172 = A13$172;
            Semigroup.$init$(this);
            Monoid.$init$(this);
            Group.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Group catsKernelGroupForTuple15$(final TupleGroupInstances $this, final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14) {
      return $this.catsKernelGroupForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   default Group catsKernelGroupForTuple15(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14) {
      return new Group(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) {
         private final Group A0$433;
         private final Group A1$413;
         private final Group A2$393;
         private final Group A3$373;
         private final Group A4$353;
         private final Group A5$333;
         private final Group A6$313;
         private final Group A7$293;
         private final Group A8$273;
         private final Group A9$253;
         private final Group A10$233;
         private final Group A11$213;
         private final Group A12$193;
         private final Group A13$173;
         private final Group A14$153;

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public Object remove(final Object a, final Object b) {
            return Group.remove$(this, a, b);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public Monoid reverse() {
            return Monoid.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Tuple15 combine(final Tuple15 x, final Tuple15 y) {
            return new Tuple15(this.A0$433.combine(x._1(), y._1()), this.A1$413.combine(x._2(), y._2()), this.A2$393.combine(x._3(), y._3()), this.A3$373.combine(x._4(), y._4()), this.A4$353.combine(x._5(), y._5()), this.A5$333.combine(x._6(), y._6()), this.A6$313.combine(x._7(), y._7()), this.A7$293.combine(x._8(), y._8()), this.A8$273.combine(x._9(), y._9()), this.A9$253.combine(x._10(), y._10()), this.A10$233.combine(x._11(), y._11()), this.A11$213.combine(x._12(), y._12()), this.A12$193.combine(x._13(), y._13()), this.A13$173.combine(x._14(), y._14()), this.A14$153.combine(x._15(), y._15()));
         }

         public Tuple15 empty() {
            return new Tuple15(this.A0$433.empty(), this.A1$413.empty(), this.A2$393.empty(), this.A3$373.empty(), this.A4$353.empty(), this.A5$333.empty(), this.A6$313.empty(), this.A7$293.empty(), this.A8$273.empty(), this.A9$253.empty(), this.A10$233.empty(), this.A11$213.empty(), this.A12$193.empty(), this.A13$173.empty(), this.A14$153.empty());
         }

         public Tuple15 inverse(final Tuple15 x) {
            return new Tuple15(this.A0$433.inverse(x._1()), this.A1$413.inverse(x._2()), this.A2$393.inverse(x._3()), this.A3$373.inverse(x._4()), this.A4$353.inverse(x._5()), this.A5$333.inverse(x._6()), this.A6$313.inverse(x._7()), this.A7$293.inverse(x._8()), this.A8$273.inverse(x._9()), this.A9$253.inverse(x._10()), this.A10$233.inverse(x._11()), this.A11$213.inverse(x._12()), this.A12$193.inverse(x._13()), this.A13$173.inverse(x._14()), this.A14$153.inverse(x._15()));
         }

         public {
            this.A0$433 = A0$433;
            this.A1$413 = A1$413;
            this.A2$393 = A2$393;
            this.A3$373 = A3$373;
            this.A4$353 = A4$353;
            this.A5$333 = A5$333;
            this.A6$313 = A6$313;
            this.A7$293 = A7$293;
            this.A8$273 = A8$273;
            this.A9$253 = A9$253;
            this.A10$233 = A10$233;
            this.A11$213 = A11$213;
            this.A12$193 = A12$193;
            this.A13$173 = A13$173;
            this.A14$153 = A14$153;
            Semigroup.$init$(this);
            Monoid.$init$(this);
            Group.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Group catsKernelGroupForTuple16$(final TupleGroupInstances $this, final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15) {
      return $this.catsKernelGroupForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   default Group catsKernelGroupForTuple16(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15) {
      return new Group(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) {
         private final Group A0$434;
         private final Group A1$414;
         private final Group A2$394;
         private final Group A3$374;
         private final Group A4$354;
         private final Group A5$334;
         private final Group A6$314;
         private final Group A7$294;
         private final Group A8$274;
         private final Group A9$254;
         private final Group A10$234;
         private final Group A11$214;
         private final Group A12$194;
         private final Group A13$174;
         private final Group A14$154;
         private final Group A15$134;

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public Object remove(final Object a, final Object b) {
            return Group.remove$(this, a, b);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public Monoid reverse() {
            return Monoid.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Tuple16 combine(final Tuple16 x, final Tuple16 y) {
            return new Tuple16(this.A0$434.combine(x._1(), y._1()), this.A1$414.combine(x._2(), y._2()), this.A2$394.combine(x._3(), y._3()), this.A3$374.combine(x._4(), y._4()), this.A4$354.combine(x._5(), y._5()), this.A5$334.combine(x._6(), y._6()), this.A6$314.combine(x._7(), y._7()), this.A7$294.combine(x._8(), y._8()), this.A8$274.combine(x._9(), y._9()), this.A9$254.combine(x._10(), y._10()), this.A10$234.combine(x._11(), y._11()), this.A11$214.combine(x._12(), y._12()), this.A12$194.combine(x._13(), y._13()), this.A13$174.combine(x._14(), y._14()), this.A14$154.combine(x._15(), y._15()), this.A15$134.combine(x._16(), y._16()));
         }

         public Tuple16 empty() {
            return new Tuple16(this.A0$434.empty(), this.A1$414.empty(), this.A2$394.empty(), this.A3$374.empty(), this.A4$354.empty(), this.A5$334.empty(), this.A6$314.empty(), this.A7$294.empty(), this.A8$274.empty(), this.A9$254.empty(), this.A10$234.empty(), this.A11$214.empty(), this.A12$194.empty(), this.A13$174.empty(), this.A14$154.empty(), this.A15$134.empty());
         }

         public Tuple16 inverse(final Tuple16 x) {
            return new Tuple16(this.A0$434.inverse(x._1()), this.A1$414.inverse(x._2()), this.A2$394.inverse(x._3()), this.A3$374.inverse(x._4()), this.A4$354.inverse(x._5()), this.A5$334.inverse(x._6()), this.A6$314.inverse(x._7()), this.A7$294.inverse(x._8()), this.A8$274.inverse(x._9()), this.A9$254.inverse(x._10()), this.A10$234.inverse(x._11()), this.A11$214.inverse(x._12()), this.A12$194.inverse(x._13()), this.A13$174.inverse(x._14()), this.A14$154.inverse(x._15()), this.A15$134.inverse(x._16()));
         }

         public {
            this.A0$434 = A0$434;
            this.A1$414 = A1$414;
            this.A2$394 = A2$394;
            this.A3$374 = A3$374;
            this.A4$354 = A4$354;
            this.A5$334 = A5$334;
            this.A6$314 = A6$314;
            this.A7$294 = A7$294;
            this.A8$274 = A8$274;
            this.A9$254 = A9$254;
            this.A10$234 = A10$234;
            this.A11$214 = A11$214;
            this.A12$194 = A12$194;
            this.A13$174 = A13$174;
            this.A14$154 = A14$154;
            this.A15$134 = A15$134;
            Semigroup.$init$(this);
            Monoid.$init$(this);
            Group.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Group catsKernelGroupForTuple17$(final TupleGroupInstances $this, final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16) {
      return $this.catsKernelGroupForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   default Group catsKernelGroupForTuple17(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16) {
      return new Group(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) {
         private final Group A0$435;
         private final Group A1$415;
         private final Group A2$395;
         private final Group A3$375;
         private final Group A4$355;
         private final Group A5$335;
         private final Group A6$315;
         private final Group A7$295;
         private final Group A8$275;
         private final Group A9$255;
         private final Group A10$235;
         private final Group A11$215;
         private final Group A12$195;
         private final Group A13$175;
         private final Group A14$155;
         private final Group A15$135;
         private final Group A16$115;

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public Object remove(final Object a, final Object b) {
            return Group.remove$(this, a, b);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public Monoid reverse() {
            return Monoid.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Tuple17 combine(final Tuple17 x, final Tuple17 y) {
            return new Tuple17(this.A0$435.combine(x._1(), y._1()), this.A1$415.combine(x._2(), y._2()), this.A2$395.combine(x._3(), y._3()), this.A3$375.combine(x._4(), y._4()), this.A4$355.combine(x._5(), y._5()), this.A5$335.combine(x._6(), y._6()), this.A6$315.combine(x._7(), y._7()), this.A7$295.combine(x._8(), y._8()), this.A8$275.combine(x._9(), y._9()), this.A9$255.combine(x._10(), y._10()), this.A10$235.combine(x._11(), y._11()), this.A11$215.combine(x._12(), y._12()), this.A12$195.combine(x._13(), y._13()), this.A13$175.combine(x._14(), y._14()), this.A14$155.combine(x._15(), y._15()), this.A15$135.combine(x._16(), y._16()), this.A16$115.combine(x._17(), y._17()));
         }

         public Tuple17 empty() {
            return new Tuple17(this.A0$435.empty(), this.A1$415.empty(), this.A2$395.empty(), this.A3$375.empty(), this.A4$355.empty(), this.A5$335.empty(), this.A6$315.empty(), this.A7$295.empty(), this.A8$275.empty(), this.A9$255.empty(), this.A10$235.empty(), this.A11$215.empty(), this.A12$195.empty(), this.A13$175.empty(), this.A14$155.empty(), this.A15$135.empty(), this.A16$115.empty());
         }

         public Tuple17 inverse(final Tuple17 x) {
            return new Tuple17(this.A0$435.inverse(x._1()), this.A1$415.inverse(x._2()), this.A2$395.inverse(x._3()), this.A3$375.inverse(x._4()), this.A4$355.inverse(x._5()), this.A5$335.inverse(x._6()), this.A6$315.inverse(x._7()), this.A7$295.inverse(x._8()), this.A8$275.inverse(x._9()), this.A9$255.inverse(x._10()), this.A10$235.inverse(x._11()), this.A11$215.inverse(x._12()), this.A12$195.inverse(x._13()), this.A13$175.inverse(x._14()), this.A14$155.inverse(x._15()), this.A15$135.inverse(x._16()), this.A16$115.inverse(x._17()));
         }

         public {
            this.A0$435 = A0$435;
            this.A1$415 = A1$415;
            this.A2$395 = A2$395;
            this.A3$375 = A3$375;
            this.A4$355 = A4$355;
            this.A5$335 = A5$335;
            this.A6$315 = A6$315;
            this.A7$295 = A7$295;
            this.A8$275 = A8$275;
            this.A9$255 = A9$255;
            this.A10$235 = A10$235;
            this.A11$215 = A11$215;
            this.A12$195 = A12$195;
            this.A13$175 = A13$175;
            this.A14$155 = A14$155;
            this.A15$135 = A15$135;
            this.A16$115 = A16$115;
            Semigroup.$init$(this);
            Monoid.$init$(this);
            Group.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Group catsKernelGroupForTuple18$(final TupleGroupInstances $this, final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17) {
      return $this.catsKernelGroupForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   default Group catsKernelGroupForTuple18(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17) {
      return new Group(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) {
         private final Group A0$436;
         private final Group A1$416;
         private final Group A2$396;
         private final Group A3$376;
         private final Group A4$356;
         private final Group A5$336;
         private final Group A6$316;
         private final Group A7$296;
         private final Group A8$276;
         private final Group A9$256;
         private final Group A10$236;
         private final Group A11$216;
         private final Group A12$196;
         private final Group A13$176;
         private final Group A14$156;
         private final Group A15$136;
         private final Group A16$116;
         private final Group A17$96;

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public Object remove(final Object a, final Object b) {
            return Group.remove$(this, a, b);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public Monoid reverse() {
            return Monoid.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Tuple18 combine(final Tuple18 x, final Tuple18 y) {
            return new Tuple18(this.A0$436.combine(x._1(), y._1()), this.A1$416.combine(x._2(), y._2()), this.A2$396.combine(x._3(), y._3()), this.A3$376.combine(x._4(), y._4()), this.A4$356.combine(x._5(), y._5()), this.A5$336.combine(x._6(), y._6()), this.A6$316.combine(x._7(), y._7()), this.A7$296.combine(x._8(), y._8()), this.A8$276.combine(x._9(), y._9()), this.A9$256.combine(x._10(), y._10()), this.A10$236.combine(x._11(), y._11()), this.A11$216.combine(x._12(), y._12()), this.A12$196.combine(x._13(), y._13()), this.A13$176.combine(x._14(), y._14()), this.A14$156.combine(x._15(), y._15()), this.A15$136.combine(x._16(), y._16()), this.A16$116.combine(x._17(), y._17()), this.A17$96.combine(x._18(), y._18()));
         }

         public Tuple18 empty() {
            return new Tuple18(this.A0$436.empty(), this.A1$416.empty(), this.A2$396.empty(), this.A3$376.empty(), this.A4$356.empty(), this.A5$336.empty(), this.A6$316.empty(), this.A7$296.empty(), this.A8$276.empty(), this.A9$256.empty(), this.A10$236.empty(), this.A11$216.empty(), this.A12$196.empty(), this.A13$176.empty(), this.A14$156.empty(), this.A15$136.empty(), this.A16$116.empty(), this.A17$96.empty());
         }

         public Tuple18 inverse(final Tuple18 x) {
            return new Tuple18(this.A0$436.inverse(x._1()), this.A1$416.inverse(x._2()), this.A2$396.inverse(x._3()), this.A3$376.inverse(x._4()), this.A4$356.inverse(x._5()), this.A5$336.inverse(x._6()), this.A6$316.inverse(x._7()), this.A7$296.inverse(x._8()), this.A8$276.inverse(x._9()), this.A9$256.inverse(x._10()), this.A10$236.inverse(x._11()), this.A11$216.inverse(x._12()), this.A12$196.inverse(x._13()), this.A13$176.inverse(x._14()), this.A14$156.inverse(x._15()), this.A15$136.inverse(x._16()), this.A16$116.inverse(x._17()), this.A17$96.inverse(x._18()));
         }

         public {
            this.A0$436 = A0$436;
            this.A1$416 = A1$416;
            this.A2$396 = A2$396;
            this.A3$376 = A3$376;
            this.A4$356 = A4$356;
            this.A5$336 = A5$336;
            this.A6$316 = A6$316;
            this.A7$296 = A7$296;
            this.A8$276 = A8$276;
            this.A9$256 = A9$256;
            this.A10$236 = A10$236;
            this.A11$216 = A11$216;
            this.A12$196 = A12$196;
            this.A13$176 = A13$176;
            this.A14$156 = A14$156;
            this.A15$136 = A15$136;
            this.A16$116 = A16$116;
            this.A17$96 = A17$96;
            Semigroup.$init$(this);
            Monoid.$init$(this);
            Group.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Group catsKernelGroupForTuple19$(final TupleGroupInstances $this, final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17, final Group A18) {
      return $this.catsKernelGroupForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   default Group catsKernelGroupForTuple19(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17, final Group A18) {
      return new Group(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) {
         private final Group A0$437;
         private final Group A1$417;
         private final Group A2$397;
         private final Group A3$377;
         private final Group A4$357;
         private final Group A5$337;
         private final Group A6$317;
         private final Group A7$297;
         private final Group A8$277;
         private final Group A9$257;
         private final Group A10$237;
         private final Group A11$217;
         private final Group A12$197;
         private final Group A13$177;
         private final Group A14$157;
         private final Group A15$137;
         private final Group A16$117;
         private final Group A17$97;
         private final Group A18$77;

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public Object remove(final Object a, final Object b) {
            return Group.remove$(this, a, b);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public Monoid reverse() {
            return Monoid.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Tuple19 combine(final Tuple19 x, final Tuple19 y) {
            return new Tuple19(this.A0$437.combine(x._1(), y._1()), this.A1$417.combine(x._2(), y._2()), this.A2$397.combine(x._3(), y._3()), this.A3$377.combine(x._4(), y._4()), this.A4$357.combine(x._5(), y._5()), this.A5$337.combine(x._6(), y._6()), this.A6$317.combine(x._7(), y._7()), this.A7$297.combine(x._8(), y._8()), this.A8$277.combine(x._9(), y._9()), this.A9$257.combine(x._10(), y._10()), this.A10$237.combine(x._11(), y._11()), this.A11$217.combine(x._12(), y._12()), this.A12$197.combine(x._13(), y._13()), this.A13$177.combine(x._14(), y._14()), this.A14$157.combine(x._15(), y._15()), this.A15$137.combine(x._16(), y._16()), this.A16$117.combine(x._17(), y._17()), this.A17$97.combine(x._18(), y._18()), this.A18$77.combine(x._19(), y._19()));
         }

         public Tuple19 empty() {
            return new Tuple19(this.A0$437.empty(), this.A1$417.empty(), this.A2$397.empty(), this.A3$377.empty(), this.A4$357.empty(), this.A5$337.empty(), this.A6$317.empty(), this.A7$297.empty(), this.A8$277.empty(), this.A9$257.empty(), this.A10$237.empty(), this.A11$217.empty(), this.A12$197.empty(), this.A13$177.empty(), this.A14$157.empty(), this.A15$137.empty(), this.A16$117.empty(), this.A17$97.empty(), this.A18$77.empty());
         }

         public Tuple19 inverse(final Tuple19 x) {
            return new Tuple19(this.A0$437.inverse(x._1()), this.A1$417.inverse(x._2()), this.A2$397.inverse(x._3()), this.A3$377.inverse(x._4()), this.A4$357.inverse(x._5()), this.A5$337.inverse(x._6()), this.A6$317.inverse(x._7()), this.A7$297.inverse(x._8()), this.A8$277.inverse(x._9()), this.A9$257.inverse(x._10()), this.A10$237.inverse(x._11()), this.A11$217.inverse(x._12()), this.A12$197.inverse(x._13()), this.A13$177.inverse(x._14()), this.A14$157.inverse(x._15()), this.A15$137.inverse(x._16()), this.A16$117.inverse(x._17()), this.A17$97.inverse(x._18()), this.A18$77.inverse(x._19()));
         }

         public {
            this.A0$437 = A0$437;
            this.A1$417 = A1$417;
            this.A2$397 = A2$397;
            this.A3$377 = A3$377;
            this.A4$357 = A4$357;
            this.A5$337 = A5$337;
            this.A6$317 = A6$317;
            this.A7$297 = A7$297;
            this.A8$277 = A8$277;
            this.A9$257 = A9$257;
            this.A10$237 = A10$237;
            this.A11$217 = A11$217;
            this.A12$197 = A12$197;
            this.A13$177 = A13$177;
            this.A14$157 = A14$157;
            this.A15$137 = A15$137;
            this.A16$117 = A16$117;
            this.A17$97 = A17$97;
            this.A18$77 = A18$77;
            Semigroup.$init$(this);
            Monoid.$init$(this);
            Group.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Group catsKernelGroupForTuple20$(final TupleGroupInstances $this, final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17, final Group A18, final Group A19) {
      return $this.catsKernelGroupForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   default Group catsKernelGroupForTuple20(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17, final Group A18, final Group A19) {
      return new Group(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) {
         private final Group A0$438;
         private final Group A1$418;
         private final Group A2$398;
         private final Group A3$378;
         private final Group A4$358;
         private final Group A5$338;
         private final Group A6$318;
         private final Group A7$298;
         private final Group A8$278;
         private final Group A9$258;
         private final Group A10$238;
         private final Group A11$218;
         private final Group A12$198;
         private final Group A13$178;
         private final Group A14$158;
         private final Group A15$138;
         private final Group A16$118;
         private final Group A17$98;
         private final Group A18$78;
         private final Group A19$58;

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public Object remove(final Object a, final Object b) {
            return Group.remove$(this, a, b);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public Monoid reverse() {
            return Monoid.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Tuple20 combine(final Tuple20 x, final Tuple20 y) {
            return new Tuple20(this.A0$438.combine(x._1(), y._1()), this.A1$418.combine(x._2(), y._2()), this.A2$398.combine(x._3(), y._3()), this.A3$378.combine(x._4(), y._4()), this.A4$358.combine(x._5(), y._5()), this.A5$338.combine(x._6(), y._6()), this.A6$318.combine(x._7(), y._7()), this.A7$298.combine(x._8(), y._8()), this.A8$278.combine(x._9(), y._9()), this.A9$258.combine(x._10(), y._10()), this.A10$238.combine(x._11(), y._11()), this.A11$218.combine(x._12(), y._12()), this.A12$198.combine(x._13(), y._13()), this.A13$178.combine(x._14(), y._14()), this.A14$158.combine(x._15(), y._15()), this.A15$138.combine(x._16(), y._16()), this.A16$118.combine(x._17(), y._17()), this.A17$98.combine(x._18(), y._18()), this.A18$78.combine(x._19(), y._19()), this.A19$58.combine(x._20(), y._20()));
         }

         public Tuple20 empty() {
            return new Tuple20(this.A0$438.empty(), this.A1$418.empty(), this.A2$398.empty(), this.A3$378.empty(), this.A4$358.empty(), this.A5$338.empty(), this.A6$318.empty(), this.A7$298.empty(), this.A8$278.empty(), this.A9$258.empty(), this.A10$238.empty(), this.A11$218.empty(), this.A12$198.empty(), this.A13$178.empty(), this.A14$158.empty(), this.A15$138.empty(), this.A16$118.empty(), this.A17$98.empty(), this.A18$78.empty(), this.A19$58.empty());
         }

         public Tuple20 inverse(final Tuple20 x) {
            return new Tuple20(this.A0$438.inverse(x._1()), this.A1$418.inverse(x._2()), this.A2$398.inverse(x._3()), this.A3$378.inverse(x._4()), this.A4$358.inverse(x._5()), this.A5$338.inverse(x._6()), this.A6$318.inverse(x._7()), this.A7$298.inverse(x._8()), this.A8$278.inverse(x._9()), this.A9$258.inverse(x._10()), this.A10$238.inverse(x._11()), this.A11$218.inverse(x._12()), this.A12$198.inverse(x._13()), this.A13$178.inverse(x._14()), this.A14$158.inverse(x._15()), this.A15$138.inverse(x._16()), this.A16$118.inverse(x._17()), this.A17$98.inverse(x._18()), this.A18$78.inverse(x._19()), this.A19$58.inverse(x._20()));
         }

         public {
            this.A0$438 = A0$438;
            this.A1$418 = A1$418;
            this.A2$398 = A2$398;
            this.A3$378 = A3$378;
            this.A4$358 = A4$358;
            this.A5$338 = A5$338;
            this.A6$318 = A6$318;
            this.A7$298 = A7$298;
            this.A8$278 = A8$278;
            this.A9$258 = A9$258;
            this.A10$238 = A10$238;
            this.A11$218 = A11$218;
            this.A12$198 = A12$198;
            this.A13$178 = A13$178;
            this.A14$158 = A14$158;
            this.A15$138 = A15$138;
            this.A16$118 = A16$118;
            this.A17$98 = A17$98;
            this.A18$78 = A18$78;
            this.A19$58 = A19$58;
            Semigroup.$init$(this);
            Monoid.$init$(this);
            Group.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Group catsKernelGroupForTuple21$(final TupleGroupInstances $this, final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17, final Group A18, final Group A19, final Group A20) {
      return $this.catsKernelGroupForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   default Group catsKernelGroupForTuple21(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17, final Group A18, final Group A19, final Group A20) {
      return new Group(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) {
         private final Group A0$439;
         private final Group A1$419;
         private final Group A2$399;
         private final Group A3$379;
         private final Group A4$359;
         private final Group A5$339;
         private final Group A6$319;
         private final Group A7$299;
         private final Group A8$279;
         private final Group A9$259;
         private final Group A10$239;
         private final Group A11$219;
         private final Group A12$199;
         private final Group A13$179;
         private final Group A14$159;
         private final Group A15$139;
         private final Group A16$119;
         private final Group A17$99;
         private final Group A18$79;
         private final Group A19$59;
         private final Group A20$39;

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public Object remove(final Object a, final Object b) {
            return Group.remove$(this, a, b);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public Monoid reverse() {
            return Monoid.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Tuple21 combine(final Tuple21 x, final Tuple21 y) {
            return new Tuple21(this.A0$439.combine(x._1(), y._1()), this.A1$419.combine(x._2(), y._2()), this.A2$399.combine(x._3(), y._3()), this.A3$379.combine(x._4(), y._4()), this.A4$359.combine(x._5(), y._5()), this.A5$339.combine(x._6(), y._6()), this.A6$319.combine(x._7(), y._7()), this.A7$299.combine(x._8(), y._8()), this.A8$279.combine(x._9(), y._9()), this.A9$259.combine(x._10(), y._10()), this.A10$239.combine(x._11(), y._11()), this.A11$219.combine(x._12(), y._12()), this.A12$199.combine(x._13(), y._13()), this.A13$179.combine(x._14(), y._14()), this.A14$159.combine(x._15(), y._15()), this.A15$139.combine(x._16(), y._16()), this.A16$119.combine(x._17(), y._17()), this.A17$99.combine(x._18(), y._18()), this.A18$79.combine(x._19(), y._19()), this.A19$59.combine(x._20(), y._20()), this.A20$39.combine(x._21(), y._21()));
         }

         public Tuple21 empty() {
            return new Tuple21(this.A0$439.empty(), this.A1$419.empty(), this.A2$399.empty(), this.A3$379.empty(), this.A4$359.empty(), this.A5$339.empty(), this.A6$319.empty(), this.A7$299.empty(), this.A8$279.empty(), this.A9$259.empty(), this.A10$239.empty(), this.A11$219.empty(), this.A12$199.empty(), this.A13$179.empty(), this.A14$159.empty(), this.A15$139.empty(), this.A16$119.empty(), this.A17$99.empty(), this.A18$79.empty(), this.A19$59.empty(), this.A20$39.empty());
         }

         public Tuple21 inverse(final Tuple21 x) {
            return new Tuple21(this.A0$439.inverse(x._1()), this.A1$419.inverse(x._2()), this.A2$399.inverse(x._3()), this.A3$379.inverse(x._4()), this.A4$359.inverse(x._5()), this.A5$339.inverse(x._6()), this.A6$319.inverse(x._7()), this.A7$299.inverse(x._8()), this.A8$279.inverse(x._9()), this.A9$259.inverse(x._10()), this.A10$239.inverse(x._11()), this.A11$219.inverse(x._12()), this.A12$199.inverse(x._13()), this.A13$179.inverse(x._14()), this.A14$159.inverse(x._15()), this.A15$139.inverse(x._16()), this.A16$119.inverse(x._17()), this.A17$99.inverse(x._18()), this.A18$79.inverse(x._19()), this.A19$59.inverse(x._20()), this.A20$39.inverse(x._21()));
         }

         public {
            this.A0$439 = A0$439;
            this.A1$419 = A1$419;
            this.A2$399 = A2$399;
            this.A3$379 = A3$379;
            this.A4$359 = A4$359;
            this.A5$339 = A5$339;
            this.A6$319 = A6$319;
            this.A7$299 = A7$299;
            this.A8$279 = A8$279;
            this.A9$259 = A9$259;
            this.A10$239 = A10$239;
            this.A11$219 = A11$219;
            this.A12$199 = A12$199;
            this.A13$179 = A13$179;
            this.A14$159 = A14$159;
            this.A15$139 = A15$139;
            this.A16$119 = A16$119;
            this.A17$99 = A17$99;
            this.A18$79 = A18$79;
            this.A19$59 = A19$59;
            this.A20$39 = A20$39;
            Semigroup.$init$(this);
            Monoid.$init$(this);
            Group.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Group catsKernelGroupForTuple22$(final TupleGroupInstances $this, final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17, final Group A18, final Group A19, final Group A20, final Group A21) {
      return $this.catsKernelGroupForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   default Group catsKernelGroupForTuple22(final Group A0, final Group A1, final Group A2, final Group A3, final Group A4, final Group A5, final Group A6, final Group A7, final Group A8, final Group A9, final Group A10, final Group A11, final Group A12, final Group A13, final Group A14, final Group A15, final Group A16, final Group A17, final Group A18, final Group A19, final Group A20, final Group A21) {
      return new Group(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) {
         private final Group A0$440;
         private final Group A1$420;
         private final Group A2$400;
         private final Group A3$380;
         private final Group A4$360;
         private final Group A5$340;
         private final Group A6$320;
         private final Group A7$300;
         private final Group A8$280;
         private final Group A9$260;
         private final Group A10$240;
         private final Group A11$220;
         private final Group A12$200;
         private final Group A13$180;
         private final Group A14$160;
         private final Group A15$140;
         private final Group A16$120;
         private final Group A17$100;
         private final Group A18$80;
         private final Group A19$60;
         private final Group A20$40;
         private final Group A21$20;

         public double inverse$mcD$sp(final double a) {
            return Group.inverse$mcD$sp$(this, a);
         }

         public float inverse$mcF$sp(final float a) {
            return Group.inverse$mcF$sp$(this, a);
         }

         public int inverse$mcI$sp(final int a) {
            return Group.inverse$mcI$sp$(this, a);
         }

         public long inverse$mcJ$sp(final long a) {
            return Group.inverse$mcJ$sp$(this, a);
         }

         public Object remove(final Object a, final Object b) {
            return Group.remove$(this, a, b);
         }

         public double remove$mcD$sp(final double a, final double b) {
            return Group.remove$mcD$sp$(this, a, b);
         }

         public float remove$mcF$sp(final float a, final float b) {
            return Group.remove$mcF$sp$(this, a, b);
         }

         public int remove$mcI$sp(final int a, final int b) {
            return Group.remove$mcI$sp$(this, a, b);
         }

         public long remove$mcJ$sp(final long a, final long b) {
            return Group.remove$mcJ$sp$(this, a, b);
         }

         public Object combineN(final Object a, final int n) {
            return Group.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Group.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Group.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Group.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Group.combineN$mcJ$sp$(this, a, n);
         }

         public double empty$mcD$sp() {
            return Monoid.empty$mcD$sp$(this);
         }

         public float empty$mcF$sp() {
            return Monoid.empty$mcF$sp$(this);
         }

         public int empty$mcI$sp() {
            return Monoid.empty$mcI$sp$(this);
         }

         public long empty$mcJ$sp() {
            return Monoid.empty$mcJ$sp$(this);
         }

         public boolean isEmpty(final Object a, final Eq ev) {
            return Monoid.isEmpty$(this, a, ev);
         }

         public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
            return Monoid.isEmpty$mcD$sp$(this, a, ev);
         }

         public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
            return Monoid.isEmpty$mcF$sp$(this, a, ev);
         }

         public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
            return Monoid.isEmpty$mcI$sp$(this, a, ev);
         }

         public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
            return Monoid.isEmpty$mcJ$sp$(this, a, ev);
         }

         public Object combineAll(final IterableOnce as) {
            return Monoid.combineAll$(this, as);
         }

         public double combineAll$mcD$sp(final IterableOnce as) {
            return Monoid.combineAll$mcD$sp$(this, as);
         }

         public float combineAll$mcF$sp(final IterableOnce as) {
            return Monoid.combineAll$mcF$sp$(this, as);
         }

         public int combineAll$mcI$sp(final IterableOnce as) {
            return Monoid.combineAll$mcI$sp$(this, as);
         }

         public long combineAll$mcJ$sp(final IterableOnce as) {
            return Monoid.combineAll$mcJ$sp$(this, as);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Monoid.combineAllOption$(this, as);
         }

         public Monoid reverse() {
            return Monoid.reverse$(this);
         }

         public Monoid reverse$mcD$sp() {
            return Monoid.reverse$mcD$sp$(this);
         }

         public Monoid reverse$mcF$sp() {
            return Monoid.reverse$mcF$sp$(this);
         }

         public Monoid reverse$mcI$sp() {
            return Monoid.reverse$mcI$sp$(this);
         }

         public Monoid reverse$mcJ$sp() {
            return Monoid.reverse$mcJ$sp$(this);
         }

         public double combine$mcD$sp(final double x, final double y) {
            return Semigroup.combine$mcD$sp$(this, x, y);
         }

         public float combine$mcF$sp(final float x, final float y) {
            return Semigroup.combine$mcF$sp$(this, x, y);
         }

         public int combine$mcI$sp(final int x, final int y) {
            return Semigroup.combine$mcI$sp$(this, x, y);
         }

         public long combine$mcJ$sp(final long x, final long y) {
            return Semigroup.combine$mcJ$sp$(this, x, y);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Semigroup.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
         }

         public Semigroup intercalate(final Object middle) {
            return Semigroup.intercalate$(this, middle);
         }

         public Semigroup intercalate$mcD$sp(final double middle) {
            return Semigroup.intercalate$mcD$sp$(this, middle);
         }

         public Semigroup intercalate$mcF$sp(final float middle) {
            return Semigroup.intercalate$mcF$sp$(this, middle);
         }

         public Semigroup intercalate$mcI$sp(final int middle) {
            return Semigroup.intercalate$mcI$sp$(this, middle);
         }

         public Semigroup intercalate$mcJ$sp(final long middle) {
            return Semigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Tuple22 combine(final Tuple22 x, final Tuple22 y) {
            return new Tuple22(this.A0$440.combine(x._1(), y._1()), this.A1$420.combine(x._2(), y._2()), this.A2$400.combine(x._3(), y._3()), this.A3$380.combine(x._4(), y._4()), this.A4$360.combine(x._5(), y._5()), this.A5$340.combine(x._6(), y._6()), this.A6$320.combine(x._7(), y._7()), this.A7$300.combine(x._8(), y._8()), this.A8$280.combine(x._9(), y._9()), this.A9$260.combine(x._10(), y._10()), this.A10$240.combine(x._11(), y._11()), this.A11$220.combine(x._12(), y._12()), this.A12$200.combine(x._13(), y._13()), this.A13$180.combine(x._14(), y._14()), this.A14$160.combine(x._15(), y._15()), this.A15$140.combine(x._16(), y._16()), this.A16$120.combine(x._17(), y._17()), this.A17$100.combine(x._18(), y._18()), this.A18$80.combine(x._19(), y._19()), this.A19$60.combine(x._20(), y._20()), this.A20$40.combine(x._21(), y._21()), this.A21$20.combine(x._22(), y._22()));
         }

         public Tuple22 empty() {
            return new Tuple22(this.A0$440.empty(), this.A1$420.empty(), this.A2$400.empty(), this.A3$380.empty(), this.A4$360.empty(), this.A5$340.empty(), this.A6$320.empty(), this.A7$300.empty(), this.A8$280.empty(), this.A9$260.empty(), this.A10$240.empty(), this.A11$220.empty(), this.A12$200.empty(), this.A13$180.empty(), this.A14$160.empty(), this.A15$140.empty(), this.A16$120.empty(), this.A17$100.empty(), this.A18$80.empty(), this.A19$60.empty(), this.A20$40.empty(), this.A21$20.empty());
         }

         public Tuple22 inverse(final Tuple22 x) {
            return new Tuple22(this.A0$440.inverse(x._1()), this.A1$420.inverse(x._2()), this.A2$400.inverse(x._3()), this.A3$380.inverse(x._4()), this.A4$360.inverse(x._5()), this.A5$340.inverse(x._6()), this.A6$320.inverse(x._7()), this.A7$300.inverse(x._8()), this.A8$280.inverse(x._9()), this.A9$260.inverse(x._10()), this.A10$240.inverse(x._11()), this.A11$220.inverse(x._12()), this.A12$200.inverse(x._13()), this.A13$180.inverse(x._14()), this.A14$160.inverse(x._15()), this.A15$140.inverse(x._16()), this.A16$120.inverse(x._17()), this.A17$100.inverse(x._18()), this.A18$80.inverse(x._19()), this.A19$60.inverse(x._20()), this.A20$40.inverse(x._21()), this.A21$20.inverse(x._22()));
         }

         public {
            this.A0$440 = A0$440;
            this.A1$420 = A1$420;
            this.A2$400 = A2$400;
            this.A3$380 = A3$380;
            this.A4$360 = A4$360;
            this.A5$340 = A5$340;
            this.A6$320 = A6$320;
            this.A7$300 = A7$300;
            this.A8$280 = A8$280;
            this.A9$260 = A9$260;
            this.A10$240 = A10$240;
            this.A11$220 = A11$220;
            this.A12$200 = A12$200;
            this.A13$180 = A13$180;
            this.A14$160 = A14$160;
            this.A15$140 = A15$140;
            this.A16$120 = A16$120;
            this.A17$100 = A17$100;
            this.A18$80 = A18$80;
            this.A19$60 = A19$60;
            this.A20$40 = A20$40;
            this.A21$20 = A21$20;
            Semigroup.$init$(this);
            Monoid.$init$(this);
            Group.$init$(this);
         }
      };
   }

   static void $init$(final TupleGroupInstances $this) {
   }
}

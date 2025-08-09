package cats.kernel.instances;

import cats.kernel.Band;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import cats.kernel.Semilattice;
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
   bytes = "\u0006\u0005%-h\u0001\u0003\r\u001a!\u0003\r\taG\u0010\t\u000b)\u0002A\u0011\u0001\u0017\t\u000bA\u0002A1A\u0019\t\u000b!\u0003A1A%\t\u000be\u0003A1\u0001.\t\u000b9\u0004A1A8\t\u000f\u0005=\u0001\u0001b\u0001\u0002\u0012!9\u0011\u0011\n\u0001\u0005\u0004\u0005-\u0003bBAF\u0001\u0011\r\u0011Q\u0012\u0005\b\u0003+\u0004A1AAl\u0011\u001d\u00119\u0003\u0001C\u0002\u0005SAqA!!\u0001\t\u0007\u0011\u0019\tC\u0004\u0003d\u0002!\u0019A!:\t\u000f\r5\u0003\u0001b\u0001\u0004P!91q\u0018\u0001\u0005\u0004\r\u0005\u0007b\u0002C\u001d\u0001\u0011\rA1\b\u0005\b\tw\u0003A1\u0001C_\u0011\u001d))\u0005\u0001C\u0002\u000b\u000fBq!b6\u0001\t\u0007)I\u000eC\u0004\u0007r\u0001!\u0019Ab\u001d\t\u000f\u001dM\u0001\u0001b\u0001\b\u0016!9qQ\u0018\u0001\u0005\u0004\u001d}\u0006b\u0002E8\u0001\u0011\r\u0001\u0012\u000f\u0005\b\u0013S\u0001A1AE\u0016\u0005e!V\u000f\u001d7f'\u0016l\u0017\u000e\\1ui&\u001cW-\u00138ti\u0006t7-Z:\u000b\u0005iY\u0012!C5ogR\fgnY3t\u0015\taR$\u0001\u0004lKJtW\r\u001c\u0006\u0002=\u0005!1-\u0019;t'\r\u0001\u0001E\n\t\u0003C\u0011j\u0011A\t\u0006\u0002G\u0005)1oY1mC&\u0011QE\t\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u001dBS\"A\r\n\u0005%J\"\u0001\u0006+va2,Wj\u001c8pS\u0012Len\u001d;b]\u000e,7/\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005i\u0003CA\u0011/\u0013\ty#E\u0001\u0003V]&$\u0018AH2biN\\UM\u001d8fYN+W.\u001b7biRL7-\u001a$peR+\b\u000f\\32+\t\u0011D\b\u0006\u00024\u000bB\u0019A'N\u001c\u000e\u0003mI!AN\u000e\u0003\u0017M+W.\u001b7biRL7-\u001a\t\u0004CaR\u0014BA\u001d#\u0005\u0019!V\u000f\u001d7fcA\u00111\b\u0010\u0007\u0001\t\u0015i$A1\u0001?\u0005\t\t\u0005'\u0005\u0002@\u0005B\u0011\u0011\u0005Q\u0005\u0003\u0003\n\u0012qAT8uQ&tw\r\u0005\u0002\"\u0007&\u0011AI\t\u0002\u0004\u0003:L\b\"\u0002$\u0003\u0001\b9\u0015AA!1!\r!TGO\u0001\u001fG\u0006$8oS3s]\u0016d7+Z7jY\u0006$H/[2f\r>\u0014H+\u001e9mKJ*2A\u0013)S)\rYEK\u0016\t\u0004iUb\u0005\u0003B\u0011N\u001fFK!A\u0014\u0012\u0003\rQ+\b\u000f\\33!\tY\u0004\u000bB\u0003>\u0007\t\u0007a\b\u0005\u0002<%\u0012)1k\u0001b\u0001}\t\u0011\u0011)\r\u0005\u0006\r\u000e\u0001\u001d!\u0016\t\u0004iUz\u0005\"B,\u0004\u0001\bA\u0016AA!2!\r!T'U\u0001\u001fG\u0006$8oS3s]\u0016d7+Z7jY\u0006$H/[2f\r>\u0014H+\u001e9mKN*BaW1dKR!AlZ5l!\r!T'\u0018\t\u0006Cy\u0003'\rZ\u0005\u0003?\n\u0012a\u0001V;qY\u0016\u001c\u0004CA\u001eb\t\u0015iDA1\u0001?!\tY4\rB\u0003T\t\t\u0007a\b\u0005\u0002<K\u0012)a\r\u0002b\u0001}\t\u0011\u0011I\r\u0005\u0006\r\u0012\u0001\u001d\u0001\u001b\t\u0004iU\u0002\u0007\"B,\u0005\u0001\bQ\u0007c\u0001\u001b6E\")A\u000e\u0002a\u0002[\u0006\u0011\u0011I\r\t\u0004iU\"\u0017AH2biN\\UM\u001d8fYN+W.\u001b7biRL7-\u001a$peR+\b\u000f\\35+\u0015\u0001h\u000f\u001f>})!\th0!\u0001\u0002\u0006\u0005%\u0001c\u0001\u001b6eB1\u0011e];xsnL!\u0001\u001e\u0012\u0003\rQ+\b\u000f\\35!\tYd\u000fB\u0003>\u000b\t\u0007a\b\u0005\u0002<q\u0012)1+\u0002b\u0001}A\u00111H\u001f\u0003\u0006M\u0016\u0011\rA\u0010\t\u0003wq$Q!`\u0003C\u0002y\u0012!!Q\u001a\t\u000b\u0019+\u00019A@\u0011\u0007Q*T\u000f\u0003\u0004X\u000b\u0001\u000f\u00111\u0001\t\u0004iU:\bB\u00027\u0006\u0001\b\t9\u0001E\u00025keDq!a\u0003\u0006\u0001\b\ti!\u0001\u0002BgA\u0019A'N>\u0002=\r\fGo]&fe:,GnU3nS2\fG\u000f^5dK\u001a{'\u000fV;qY\u0016,T\u0003DA\n\u0003?\t\u0019#a\n\u0002,\u0005=B\u0003DA\u000b\u0003g\t9$a\u000f\u0002@\u0005\r\u0003\u0003\u0002\u001b6\u0003/\u0001R\"IA\r\u0003;\t\t#!\n\u0002*\u00055\u0012bAA\u000eE\t1A+\u001e9mKV\u00022aOA\u0010\t\u0015idA1\u0001?!\rY\u00141\u0005\u0003\u0006'\u001a\u0011\rA\u0010\t\u0004w\u0005\u001dB!\u00024\u0007\u0005\u0004q\u0004cA\u001e\u0002,\u0011)QP\u0002b\u0001}A\u00191(a\f\u0005\r\u0005EbA1\u0001?\u0005\t\tE\u0007\u0003\u0004G\r\u0001\u000f\u0011Q\u0007\t\u0005iU\ni\u0002\u0003\u0004X\r\u0001\u000f\u0011\u0011\b\t\u0005iU\n\t\u0003\u0003\u0004m\r\u0001\u000f\u0011Q\b\t\u0005iU\n)\u0003C\u0004\u0002\f\u0019\u0001\u001d!!\u0011\u0011\tQ*\u0014\u0011\u0006\u0005\b\u0003\u000b2\u00019AA$\u0003\t\tE\u0007\u0005\u00035k\u00055\u0012AH2biN\\UM\u001d8fYN+W.\u001b7biRL7-\u001a$peR+\b\u000f\\37+9\ti%!\u0017\u0002^\u0005\u0005\u0014QMA5\u0003[\"b\"a\u0014\u0002r\u0005U\u0014\u0011PA?\u0003\u0003\u000b)\t\u0005\u00035k\u0005E\u0003cD\u0011\u0002T\u0005]\u00131LA0\u0003G\n9'a\u001b\n\u0007\u0005U#E\u0001\u0004UkBdWM\u000e\t\u0004w\u0005eC!B\u001f\b\u0005\u0004q\u0004cA\u001e\u0002^\u0011)1k\u0002b\u0001}A\u00191(!\u0019\u0005\u000b\u0019<!\u0019\u0001 \u0011\u0007m\n)\u0007B\u0003~\u000f\t\u0007a\bE\u0002<\u0003S\"a!!\r\b\u0005\u0004q\u0004cA\u001e\u0002n\u00111\u0011qN\u0004C\u0002y\u0012!!Q\u001b\t\r\u0019;\u00019AA:!\u0011!T'a\u0016\t\r];\u00019AA<!\u0011!T'a\u0017\t\r1<\u00019AA>!\u0011!T'a\u0018\t\u000f\u0005-q\u0001q\u0001\u0002\u0000A!A'NA2\u0011\u001d\t)e\u0002a\u0002\u0003\u0007\u0003B\u0001N\u001b\u0002h!9\u0011qQ\u0004A\u0004\u0005%\u0015AA!6!\u0011!T'a\u001b\u0002=\r\fGo]&fe:,GnU3nS2\fG\u000f^5dK\u001a{'\u000fV;qY\u0016<T\u0003EAH\u00037\u000by*a)\u0002(\u0006-\u0016qVAZ)A\t\t*a.\u0002<\u0006}\u00161YAd\u0003\u0017\fy\r\u0005\u00035k\u0005M\u0005#E\u0011\u0002\u0016\u0006e\u0015QTAQ\u0003K\u000bI+!,\u00022&\u0019\u0011q\u0013\u0012\u0003\rQ+\b\u000f\\38!\rY\u00141\u0014\u0003\u0006{!\u0011\rA\u0010\t\u0004w\u0005}E!B*\t\u0005\u0004q\u0004cA\u001e\u0002$\u0012)a\r\u0003b\u0001}A\u00191(a*\u0005\u000buD!\u0019\u0001 \u0011\u0007m\nY\u000b\u0002\u0004\u00022!\u0011\rA\u0010\t\u0004w\u0005=FABA8\u0011\t\u0007a\bE\u0002<\u0003g#a!!.\t\u0005\u0004q$AA!7\u0011\u00191\u0005\u0002q\u0001\u0002:B!A'NAM\u0011\u00199\u0006\u0002q\u0001\u0002>B!A'NAO\u0011\u0019a\u0007\u0002q\u0001\u0002BB!A'NAQ\u0011\u001d\tY\u0001\u0003a\u0002\u0003\u000b\u0004B\u0001N\u001b\u0002&\"9\u0011Q\t\u0005A\u0004\u0005%\u0007\u0003\u0002\u001b6\u0003SCq!a\"\t\u0001\b\ti\r\u0005\u00035k\u00055\u0006bBAi\u0011\u0001\u000f\u00111[\u0001\u0003\u0003Z\u0002B\u0001N\u001b\u00022\u0006q2-\u0019;t\u0017\u0016\u0014h.\u001a7TK6LG.\u0019;uS\u000e,gi\u001c:UkBdW\rO\u000b\u0013\u00033\f)/!;\u0002n\u0006E\u0018Q_A}\u0003{\u0014\t\u0001\u0006\n\u0002\\\n\u0015!\u0011\u0002B\u0007\u0005#\u0011)B!\u0007\u0003\u001e\t\u0005\u0002\u0003\u0002\u001b6\u0003;\u00042#IAp\u0003G\f9/a;\u0002p\u0006M\u0018q_A~\u0003\u007fL1!!9#\u0005\u0019!V\u000f\u001d7fqA\u00191(!:\u0005\u000buJ!\u0019\u0001 \u0011\u0007m\nI\u000fB\u0003T\u0013\t\u0007a\bE\u0002<\u0003[$QAZ\u0005C\u0002y\u00022aOAy\t\u0015i\u0018B1\u0001?!\rY\u0014Q\u001f\u0003\u0007\u0003cI!\u0019\u0001 \u0011\u0007m\nI\u0010\u0002\u0004\u0002p%\u0011\rA\u0010\t\u0004w\u0005uHABA[\u0013\t\u0007a\bE\u0002<\u0005\u0003!aAa\u0001\n\u0005\u0004q$AA!8\u0011\u00191\u0015\u0002q\u0001\u0003\bA!A'NAr\u0011\u00199\u0016\u0002q\u0001\u0003\fA!A'NAt\u0011\u0019a\u0017\u0002q\u0001\u0003\u0010A!A'NAv\u0011\u001d\tY!\u0003a\u0002\u0005'\u0001B\u0001N\u001b\u0002p\"9\u0011QI\u0005A\u0004\t]\u0001\u0003\u0002\u001b6\u0003gDq!a\"\n\u0001\b\u0011Y\u0002\u0005\u00035k\u0005]\bbBAi\u0013\u0001\u000f!q\u0004\t\u0005iU\nY\u0010C\u0004\u0003$%\u0001\u001dA!\n\u0002\u0005\u0005;\u0004\u0003\u0002\u001b6\u0003\u007f\fadY1ug.+'O\\3m'\u0016l\u0017\u000e\\1ui&\u001cWMR8s)V\u0004H.Z\u001d\u0016)\t-\"q\u0007B\u001e\u0005\u007f\u0011\u0019Ea\u0012\u0003L\t=#1\u000bB,)Q\u0011iCa\u0017\u0003`\t\r$q\rB6\u0005_\u0012\u0019Ha\u001e\u0003|A!A'\u000eB\u0018!U\t#\u0011\u0007B\u001b\u0005s\u0011iD!\u0011\u0003F\t%#Q\nB)\u0005+J1Aa\r#\u0005\u0019!V\u000f\u001d7fsA\u00191Ha\u000e\u0005\u000buR!\u0019\u0001 \u0011\u0007m\u0012Y\u0004B\u0003T\u0015\t\u0007a\bE\u0002<\u0005\u007f!QA\u001a\u0006C\u0002y\u00022a\u000fB\"\t\u0015i(B1\u0001?!\rY$q\t\u0003\u0007\u0003cQ!\u0019\u0001 \u0011\u0007m\u0012Y\u0005\u0002\u0004\u0002p)\u0011\rA\u0010\t\u0004w\t=CABA[\u0015\t\u0007a\bE\u0002<\u0005'\"aAa\u0001\u000b\u0005\u0004q\u0004cA\u001e\u0003X\u00111!\u0011\f\u0006C\u0002y\u0012!!\u0011\u001d\t\r\u0019S\u00019\u0001B/!\u0011!TG!\u000e\t\r]S\u00019\u0001B1!\u0011!TG!\u000f\t\r1T\u00019\u0001B3!\u0011!TG!\u0010\t\u000f\u0005-!\u0002q\u0001\u0003jA!A'\u000eB!\u0011\u001d\t)E\u0003a\u0002\u0005[\u0002B\u0001N\u001b\u0003F!9\u0011q\u0011\u0006A\u0004\tE\u0004\u0003\u0002\u001b6\u0005\u0013Bq!!5\u000b\u0001\b\u0011)\b\u0005\u00035k\t5\u0003b\u0002B\u0012\u0015\u0001\u000f!\u0011\u0010\t\u0005iU\u0012\t\u0006C\u0004\u0003~)\u0001\u001dAa \u0002\u0005\u0005C\u0004\u0003\u0002\u001b6\u0005+\nqdY1ug.+'O\\3m'\u0016l\u0017\u000e\\1ui&\u001cWMR8s)V\u0004H.Z\u00191+Y\u0011)I!%\u0003\u0016\ne%Q\u0014BQ\u0005K\u0013IK!,\u00032\nUFC\u0006BD\u0005s\u0013iL!1\u0003F\n%'Q\u001aBi\u0005+\u0014IN!8\u0011\tQ*$\u0011\u0012\t\u0018C\t-%q\u0012BJ\u0005/\u0013YJa(\u0003$\n\u001d&1\u0016BX\u0005gK1A!$#\u0005\u001d!V\u000f\u001d7fcA\u00022a\u000fBI\t\u0015i4B1\u0001?!\rY$Q\u0013\u0003\u0006'.\u0011\rA\u0010\t\u0004w\teE!\u00024\f\u0005\u0004q\u0004cA\u001e\u0003\u001e\u0012)Qp\u0003b\u0001}A\u00191H!)\u0005\r\u0005E2B1\u0001?!\rY$Q\u0015\u0003\u0007\u0003_Z!\u0019\u0001 \u0011\u0007m\u0012I\u000b\u0002\u0004\u00026.\u0011\rA\u0010\t\u0004w\t5FA\u0002B\u0002\u0017\t\u0007a\bE\u0002<\u0005c#aA!\u0017\f\u0005\u0004q\u0004cA\u001e\u00036\u00121!qW\u0006C\u0002y\u0012!!Q\u001d\t\r\u0019[\u00019\u0001B^!\u0011!TGa$\t\r][\u00019\u0001B`!\u0011!TGa%\t\r1\\\u00019\u0001Bb!\u0011!TGa&\t\u000f\u0005-1\u0002q\u0001\u0003HB!A'\u000eBN\u0011\u001d\t)e\u0003a\u0002\u0005\u0017\u0004B\u0001N\u001b\u0003 \"9\u0011qQ\u0006A\u0004\t=\u0007\u0003\u0002\u001b6\u0005GCq!!5\f\u0001\b\u0011\u0019\u000e\u0005\u00035k\t\u001d\u0006b\u0002B\u0012\u0017\u0001\u000f!q\u001b\t\u0005iU\u0012Y\u000bC\u0004\u0003~-\u0001\u001dAa7\u0011\tQ*$q\u0016\u0005\b\u0005?\\\u00019\u0001Bq\u0003\t\t\u0015\b\u0005\u00035k\tM\u0016aH2biN\\UM\u001d8fYN+W.\u001b7biRL7-\u001a$peR+\b\u000f\\32cUA\"q\u001dBz\u0005o\u0014YPa@\u0004\u0004\r\u001d11BB\b\u0007'\u00199ba\u0007\u00151\t%8qDB\u0012\u0007O\u0019Yca\f\u00044\r]21HB \u0007\u0007\u001a9\u0005\u0005\u00035k\t-\b#G\u0011\u0003n\nE(Q\u001fB}\u0005{\u001c\ta!\u0002\u0004\n\r51\u0011CB\u000b\u00073I1Aa<#\u0005\u001d!V\u000f\u001d7fcE\u00022a\u000fBz\t\u0015iDB1\u0001?!\rY$q\u001f\u0003\u0006'2\u0011\rA\u0010\t\u0004w\tmH!\u00024\r\u0005\u0004q\u0004cA\u001e\u0003\u0000\u0012)Q\u0010\u0004b\u0001}A\u00191ha\u0001\u0005\r\u0005EBB1\u0001?!\rY4q\u0001\u0003\u0007\u0003_b!\u0019\u0001 \u0011\u0007m\u001aY\u0001\u0002\u0004\u000262\u0011\rA\u0010\t\u0004w\r=AA\u0002B\u0002\u0019\t\u0007a\bE\u0002<\u0007'!aA!\u0017\r\u0005\u0004q\u0004cA\u001e\u0004\u0018\u00111!q\u0017\u0007C\u0002y\u00022aOB\u000e\t\u0019\u0019i\u0002\u0004b\u0001}\t\u0019\u0011)\r\u0019\t\r\u0019c\u00019AB\u0011!\u0011!TG!=\t\r]c\u00019AB\u0013!\u0011!TG!>\t\r1d\u00019AB\u0015!\u0011!TG!?\t\u000f\u0005-A\u0002q\u0001\u0004.A!A'\u000eB\u007f\u0011\u001d\t)\u0005\u0004a\u0002\u0007c\u0001B\u0001N\u001b\u0004\u0002!9\u0011q\u0011\u0007A\u0004\rU\u0002\u0003\u0002\u001b6\u0007\u000bAq!!5\r\u0001\b\u0019I\u0004\u0005\u00035k\r%\u0001b\u0002B\u0012\u0019\u0001\u000f1Q\b\t\u0005iU\u001ai\u0001C\u0004\u0003~1\u0001\u001da!\u0011\u0011\tQ*4\u0011\u0003\u0005\b\u0005?d\u00019AB#!\u0011!Tg!\u0006\t\u000f\r%C\u0002q\u0001\u0004L\u0005\u0019\u0011)\r\u0019\u0011\tQ*4\u0011D\u0001 G\u0006$8oS3s]\u0016d7+Z7jY\u0006$H/[2f\r>\u0014H+\u001e9mKF\u0012TCGB)\u0007;\u001a\tg!\u001a\u0004j\r54\u0011OB;\u0007s\u001aih!!\u0004\u0006\u000e%ECGB*\u0007\u001b\u001b\tj!&\u0004\u001a\u000eu5\u0011UBS\u0007S\u001bik!-\u00046\u000ee\u0006\u0003\u0002\u001b6\u0007+\u00022$IB,\u00077\u001ayfa\u0019\u0004h\r-4qNB:\u0007o\u001aYha \u0004\u0004\u000e\u001d\u0015bAB-E\t9A+\u001e9mKF\u0012\u0004cA\u001e\u0004^\u0011)Q(\u0004b\u0001}A\u00191h!\u0019\u0005\u000bMk!\u0019\u0001 \u0011\u0007m\u001a)\u0007B\u0003g\u001b\t\u0007a\bE\u0002<\u0007S\"Q!`\u0007C\u0002y\u00022aOB7\t\u0019\t\t$\u0004b\u0001}A\u00191h!\u001d\u0005\r\u0005=TB1\u0001?!\rY4Q\u000f\u0003\u0007\u0003kk!\u0019\u0001 \u0011\u0007m\u001aI\b\u0002\u0004\u0003\u00045\u0011\rA\u0010\t\u0004w\ruDA\u0002B-\u001b\t\u0007a\bE\u0002<\u0007\u0003#aAa.\u000e\u0005\u0004q\u0004cA\u001e\u0004\u0006\u001211QD\u0007C\u0002y\u00022aOBE\t\u0019\u0019Y)\u0004b\u0001}\t\u0019\u0011)M\u0019\t\r\u0019k\u00019ABH!\u0011!Tga\u0017\t\r]k\u00019ABJ!\u0011!Tga\u0018\t\r1l\u00019ABL!\u0011!Tga\u0019\t\u000f\u0005-Q\u0002q\u0001\u0004\u001cB!A'NB4\u0011\u001d\t)%\u0004a\u0002\u0007?\u0003B\u0001N\u001b\u0004l!9\u0011qQ\u0007A\u0004\r\r\u0006\u0003\u0002\u001b6\u0007_Bq!!5\u000e\u0001\b\u00199\u000b\u0005\u00035k\rM\u0004b\u0002B\u0012\u001b\u0001\u000f11\u0016\t\u0005iU\u001a9\bC\u0004\u0003~5\u0001\u001daa,\u0011\tQ*41\u0010\u0005\b\u0005?l\u00019ABZ!\u0011!Tga \t\u000f\r%S\u0002q\u0001\u00048B!A'NBB\u0011\u001d\u0019Y,\u0004a\u0002\u0007{\u000b1!Q\u00192!\u0011!Tga\"\u0002?\r\fGo]&fe:,GnU3nS2\fG\u000f^5dK\u001a{'\u000fV;qY\u0016\f4'\u0006\u000f\u0004D\u000e=71[Bl\u00077\u001cyna9\u0004h\u000e-8q^Bz\u0007o\u001cYpa@\u00159\r\u0015G1\u0001C\u0004\t\u0017!y\u0001b\u0005\u0005\u0018\u0011mAq\u0004C\u0012\tO!Y\u0003b\f\u00054A!A'NBd!u\t3\u0011ZBg\u0007#\u001c)n!7\u0004^\u000e\u00058Q]Bu\u0007[\u001c\tp!>\u0004z\u000eu\u0018bABfE\t9A+\u001e9mKF\u001a\u0004cA\u001e\u0004P\u0012)QH\u0004b\u0001}A\u00191ha5\u0005\u000bMs!\u0019\u0001 \u0011\u0007m\u001a9\u000eB\u0003g\u001d\t\u0007a\bE\u0002<\u00077$Q! \bC\u0002y\u00022aOBp\t\u0019\t\tD\u0004b\u0001}A\u00191ha9\u0005\r\u0005=dB1\u0001?!\rY4q\u001d\u0003\u0007\u0003ks!\u0019\u0001 \u0011\u0007m\u001aY\u000f\u0002\u0004\u0003\u00049\u0011\rA\u0010\t\u0004w\r=HA\u0002B-\u001d\t\u0007a\bE\u0002<\u0007g$aAa.\u000f\u0005\u0004q\u0004cA\u001e\u0004x\u001211Q\u0004\bC\u0002y\u00022aOB~\t\u0019\u0019YI\u0004b\u0001}A\u00191ha@\u0005\r\u0011\u0005aB1\u0001?\u0005\r\t\u0015G\r\u0005\u0007\r:\u0001\u001d\u0001\"\u0002\u0011\tQ*4Q\u001a\u0005\u0007/:\u0001\u001d\u0001\"\u0003\u0011\tQ*4\u0011\u001b\u0005\u0007Y:\u0001\u001d\u0001\"\u0004\u0011\tQ*4Q\u001b\u0005\b\u0003\u0017q\u00019\u0001C\t!\u0011!Tg!7\t\u000f\u0005\u0015c\u0002q\u0001\u0005\u0016A!A'NBo\u0011\u001d\t9I\u0004a\u0002\t3\u0001B\u0001N\u001b\u0004b\"9\u0011\u0011\u001b\bA\u0004\u0011u\u0001\u0003\u0002\u001b6\u0007KDqAa\t\u000f\u0001\b!\t\u0003\u0005\u00035k\r%\bb\u0002B?\u001d\u0001\u000fAQ\u0005\t\u0005iU\u001ai\u000fC\u0004\u0003`:\u0001\u001d\u0001\"\u000b\u0011\tQ*4\u0011\u001f\u0005\b\u0007\u0013r\u00019\u0001C\u0017!\u0011!Tg!>\t\u000f\rmf\u0002q\u0001\u00052A!A'NB}\u0011\u001d!)D\u0004a\u0002\to\t1!Q\u00193!\u0011!Tg!@\u0002?\r\fGo]&fe:,GnU3nS2\fG\u000f^5dK\u001a{'\u000fV;qY\u0016\fD'\u0006\u0010\u0005>\u0011%CQ\nC)\t+\"I\u0006\"\u0018\u0005b\u0011\u0015D\u0011\u000eC7\tc\")\b\"\u001f\u0005~QqBq\bCA\t\u000b#I\t\"$\u0005\u0012\u0012UE\u0011\u0014CO\tC#)\u000b\"+\u0005.\u0012EFQ\u0017\t\u0005iU\"\t\u0005E\u0010\"\t\u0007\"9\u0005b\u0013\u0005P\u0011MCq\u000bC.\t?\"\u0019\u0007b\u001a\u0005l\u0011=D1\u000fC<\twJ1\u0001\"\u0012#\u0005\u001d!V\u000f\u001d7fcQ\u00022a\u000fC%\t\u0015itB1\u0001?!\rYDQ\n\u0003\u0006'>\u0011\rA\u0010\t\u0004w\u0011EC!\u00024\u0010\u0005\u0004q\u0004cA\u001e\u0005V\u0011)Qp\u0004b\u0001}A\u00191\b\"\u0017\u0005\r\u0005ErB1\u0001?!\rYDQ\f\u0003\u0007\u0003_z!\u0019\u0001 \u0011\u0007m\"\t\u0007\u0002\u0004\u00026>\u0011\rA\u0010\t\u0004w\u0011\u0015DA\u0002B\u0002\u001f\t\u0007a\bE\u0002<\tS\"aA!\u0017\u0010\u0005\u0004q\u0004cA\u001e\u0005n\u00111!qW\bC\u0002y\u00022a\u000fC9\t\u0019\u0019ib\u0004b\u0001}A\u00191\b\"\u001e\u0005\r\r-uB1\u0001?!\rYD\u0011\u0010\u0003\u0007\t\u0003y!\u0019\u0001 \u0011\u0007m\"i\b\u0002\u0004\u0005\u0000=\u0011\rA\u0010\u0002\u0004\u0003F\u001a\u0004B\u0002$\u0010\u0001\b!\u0019\t\u0005\u00035k\u0011\u001d\u0003BB,\u0010\u0001\b!9\t\u0005\u00035k\u0011-\u0003B\u00027\u0010\u0001\b!Y\t\u0005\u00035k\u0011=\u0003bBA\u0006\u001f\u0001\u000fAq\u0012\t\u0005iU\"\u0019\u0006C\u0004\u0002F=\u0001\u001d\u0001b%\u0011\tQ*Dq\u000b\u0005\b\u0003\u000f{\u00019\u0001CL!\u0011!T\u0007b\u0017\t\u000f\u0005Ew\u0002q\u0001\u0005\u001cB!A'\u000eC0\u0011\u001d\u0011\u0019c\u0004a\u0002\t?\u0003B\u0001N\u001b\u0005d!9!QP\bA\u0004\u0011\r\u0006\u0003\u0002\u001b6\tOBqAa8\u0010\u0001\b!9\u000b\u0005\u00035k\u0011-\u0004bBB%\u001f\u0001\u000fA1\u0016\t\u0005iU\"y\u0007C\u0004\u0004<>\u0001\u001d\u0001b,\u0011\tQ*D1\u000f\u0005\b\tky\u00019\u0001CZ!\u0011!T\u0007b\u001e\t\u000f\u0011]v\u0002q\u0001\u0005:\u0006\u0019\u0011)M\u001a\u0011\tQ*D1P\u0001 G\u0006$8oS3s]\u0016d7+Z7jY\u0006$H/[2f\r>\u0014H+\u001e9mKF*T\u0003\tC`\t\u0017$y\rb5\u0005X\u0012mGq\u001cCr\tO$Y\u000fb<\u0005t\u0012]H1 C\u0000\u000b\u0007!\u0002\u0005\"1\u0006\b\u0015-QqBC\n\u000b/)Y\"b\b\u0006$\u0015\u001dR1FC\u0018\u000bg)9$b\u000f\u0006@A!A'\u000eCb!\u0005\nCQ\u0019Ce\t\u001b$\t\u000e\"6\u0005Z\u0012uG\u0011\u001dCs\tS$i\u000f\"=\u0005v\u0012eHQ`C\u0001\u0013\r!9M\t\u0002\b)V\u0004H.Z\u00196!\rYD1\u001a\u0003\u0006{A\u0011\rA\u0010\t\u0004w\u0011=G!B*\u0011\u0005\u0004q\u0004cA\u001e\u0005T\u0012)a\r\u0005b\u0001}A\u00191\bb6\u0005\u000bu\u0004\"\u0019\u0001 \u0011\u0007m\"Y\u000e\u0002\u0004\u00022A\u0011\rA\u0010\t\u0004w\u0011}GABA8!\t\u0007a\bE\u0002<\tG$a!!.\u0011\u0005\u0004q\u0004cA\u001e\u0005h\u00121!1\u0001\tC\u0002y\u00022a\u000fCv\t\u0019\u0011I\u0006\u0005b\u0001}A\u00191\bb<\u0005\r\t]\u0006C1\u0001?!\rYD1\u001f\u0003\u0007\u0007;\u0001\"\u0019\u0001 \u0011\u0007m\"9\u0010\u0002\u0004\u0004\fB\u0011\rA\u0010\t\u0004w\u0011mHA\u0002C\u0001!\t\u0007a\bE\u0002<\t\u007f$a\u0001b \u0011\u0005\u0004q\u0004cA\u001e\u0006\u0004\u00111QQ\u0001\tC\u0002y\u00121!Q\u00195\u0011\u00191\u0005\u0003q\u0001\u0006\nA!A'\u000eCe\u0011\u00199\u0006\u0003q\u0001\u0006\u000eA!A'\u000eCg\u0011\u0019a\u0007\u0003q\u0001\u0006\u0012A!A'\u000eCi\u0011\u001d\tY\u0001\u0005a\u0002\u000b+\u0001B\u0001N\u001b\u0005V\"9\u0011Q\t\tA\u0004\u0015e\u0001\u0003\u0002\u001b6\t3Dq!a\"\u0011\u0001\b)i\u0002\u0005\u00035k\u0011u\u0007bBAi!\u0001\u000fQ\u0011\u0005\t\u0005iU\"\t\u000fC\u0004\u0003$A\u0001\u001d!\"\n\u0011\tQ*DQ\u001d\u0005\b\u0005{\u0002\u00029AC\u0015!\u0011!T\u0007\";\t\u000f\t}\u0007\u0003q\u0001\u0006.A!A'\u000eCw\u0011\u001d\u0019I\u0005\u0005a\u0002\u000bc\u0001B\u0001N\u001b\u0005r\"911\u0018\tA\u0004\u0015U\u0002\u0003\u0002\u001b6\tkDq\u0001\"\u000e\u0011\u0001\b)I\u0004\u0005\u00035k\u0011e\bb\u0002C\\!\u0001\u000fQQ\b\t\u0005iU\"i\u0010C\u0004\u0006BA\u0001\u001d!b\u0011\u0002\u0007\u0005\u000bD\u0007\u0005\u00035k\u0015\u0005\u0011aH2biN\\UM\u001d8fYN+W.\u001b7biRL7-\u001a$peR+\b\u000f\\32mU\u0011S\u0011JC+\u000b3*i&\"\u0019\u0006f\u0015%TQNC9\u000bk*I(\" \u0006\u0002\u0016\u0015U\u0011RCG\u000b##\"%b\u0013\u0006\u0016\u0016eUQTCQ\u000bK+I+\",\u00062\u0016UV\u0011XC_\u000b\u0003,)-\"3\u0006N\u0016E\u0007\u0003\u0002\u001b6\u000b\u001b\u00022%IC(\u000b'*9&b\u0017\u0006`\u0015\rTqMC6\u000b_*\u0019(b\u001e\u0006|\u0015}T1QCD\u000b\u0017+y)C\u0002\u0006R\t\u0012q\u0001V;qY\u0016\fd\u0007E\u0002<\u000b+\"Q!P\tC\u0002y\u00022aOC-\t\u0015\u0019\u0016C1\u0001?!\rYTQ\f\u0003\u0006MF\u0011\rA\u0010\t\u0004w\u0015\u0005D!B?\u0012\u0005\u0004q\u0004cA\u001e\u0006f\u00111\u0011\u0011G\tC\u0002y\u00022aOC5\t\u0019\ty'\u0005b\u0001}A\u00191(\"\u001c\u0005\r\u0005U\u0016C1\u0001?!\rYT\u0011\u000f\u0003\u0007\u0005\u0007\t\"\u0019\u0001 \u0011\u0007m*)\b\u0002\u0004\u0003ZE\u0011\rA\u0010\t\u0004w\u0015eDA\u0002B\\#\t\u0007a\bE\u0002<\u000b{\"aa!\b\u0012\u0005\u0004q\u0004cA\u001e\u0006\u0002\u0012111R\tC\u0002y\u00022aOCC\t\u0019!\t!\u0005b\u0001}A\u00191(\"#\u0005\r\u0011}\u0014C1\u0001?!\rYTQ\u0012\u0003\u0007\u000b\u000b\t\"\u0019\u0001 \u0011\u0007m*\t\n\u0002\u0004\u0006\u0014F\u0011\rA\u0010\u0002\u0004\u0003F*\u0004B\u0002$\u0012\u0001\b)9\n\u0005\u00035k\u0015M\u0003BB,\u0012\u0001\b)Y\n\u0005\u00035k\u0015]\u0003B\u00027\u0012\u0001\b)y\n\u0005\u00035k\u0015m\u0003bBA\u0006#\u0001\u000fQ1\u0015\t\u0005iU*y\u0006C\u0004\u0002FE\u0001\u001d!b*\u0011\tQ*T1\r\u0005\b\u0003\u000f\u000b\u00029ACV!\u0011!T'b\u001a\t\u000f\u0005E\u0017\u0003q\u0001\u00060B!A'NC6\u0011\u001d\u0011\u0019#\u0005a\u0002\u000bg\u0003B\u0001N\u001b\u0006p!9!QP\tA\u0004\u0015]\u0006\u0003\u0002\u001b6\u000bgBqAa8\u0012\u0001\b)Y\f\u0005\u00035k\u0015]\u0004bBB%#\u0001\u000fQq\u0018\t\u0005iU*Y\bC\u0004\u0004<F\u0001\u001d!b1\u0011\tQ*Tq\u0010\u0005\b\tk\t\u00029ACd!\u0011!T'b!\t\u000f\u0011]\u0016\u0003q\u0001\u0006LB!A'NCD\u0011\u001d)\t%\u0005a\u0002\u000b\u001f\u0004B\u0001N\u001b\u0006\f\"9Q1[\tA\u0004\u0015U\u0017aA!2kA!A'NCH\u0003}\u0019\u0017\r^:LKJtW\r\\*f[&d\u0017\r\u001e;jG\u00164uN\u001d+va2,\u0017gN\u000b%\u000b7,9/b;\u0006p\u0016MXq_C~\u000b\u007f4\u0019Ab\u0002\u0007\f\u0019=a1\u0003D\f\r71yBb\t\u0007(Q!SQ\u001cD\u0016\r_1\u0019Db\u000e\u0007<\u0019}b1\tD$\r\u00172yEb\u0015\u0007X\u0019mcq\fD2\rO2Y\u0007\u0005\u00035k\u0015}\u0007#J\u0011\u0006b\u0016\u0015X\u0011^Cw\u000bc,)0\"?\u0006~\u001a\u0005aQ\u0001D\u0005\r\u001b1\tB\"\u0006\u0007\u001a\u0019ua\u0011\u0005D\u0013\u0013\r)\u0019O\t\u0002\b)V\u0004H.Z\u00198!\rYTq\u001d\u0003\u0006{I\u0011\rA\u0010\t\u0004w\u0015-H!B*\u0013\u0005\u0004q\u0004cA\u001e\u0006p\u0012)aM\u0005b\u0001}A\u00191(b=\u0005\u000bu\u0014\"\u0019\u0001 \u0011\u0007m*9\u0010\u0002\u0004\u00022I\u0011\rA\u0010\t\u0004w\u0015mHABA8%\t\u0007a\bE\u0002<\u000b\u007f$a!!.\u0013\u0005\u0004q\u0004cA\u001e\u0007\u0004\u00111!1\u0001\nC\u0002y\u00022a\u000fD\u0004\t\u0019\u0011IF\u0005b\u0001}A\u00191Hb\u0003\u0005\r\t]&C1\u0001?!\rYdq\u0002\u0003\u0007\u0007;\u0011\"\u0019\u0001 \u0011\u0007m2\u0019\u0002\u0002\u0004\u0004\fJ\u0011\rA\u0010\t\u0004w\u0019]AA\u0002C\u0001%\t\u0007a\bE\u0002<\r7!a\u0001b \u0013\u0005\u0004q\u0004cA\u001e\u0007 \u00111QQ\u0001\nC\u0002y\u00022a\u000fD\u0012\t\u0019)\u0019J\u0005b\u0001}A\u00191Hb\n\u0005\r\u0019%\"C1\u0001?\u0005\r\t\u0015G\u000e\u0005\u0007\rJ\u0001\u001dA\"\f\u0011\tQ*TQ\u001d\u0005\u0007/J\u0001\u001dA\"\r\u0011\tQ*T\u0011\u001e\u0005\u0007YJ\u0001\u001dA\"\u000e\u0011\tQ*TQ\u001e\u0005\b\u0003\u0017\u0011\u00029\u0001D\u001d!\u0011!T'\"=\t\u000f\u0005\u0015#\u0003q\u0001\u0007>A!A'NC{\u0011\u001d\t9I\u0005a\u0002\r\u0003\u0002B\u0001N\u001b\u0006z\"9\u0011\u0011\u001b\nA\u0004\u0019\u0015\u0003\u0003\u0002\u001b6\u000b{DqAa\t\u0013\u0001\b1I\u0005\u0005\u00035k\u0019\u0005\u0001b\u0002B?%\u0001\u000faQ\n\t\u0005iU2)\u0001C\u0004\u0003`J\u0001\u001dA\"\u0015\u0011\tQ*d\u0011\u0002\u0005\b\u0007\u0013\u0012\u00029\u0001D+!\u0011!TG\"\u0004\t\u000f\rm&\u0003q\u0001\u0007ZA!A'\u000eD\t\u0011\u001d!)D\u0005a\u0002\r;\u0002B\u0001N\u001b\u0007\u0016!9Aq\u0017\nA\u0004\u0019\u0005\u0004\u0003\u0002\u001b6\r3Aq!\"\u0011\u0013\u0001\b1)\u0007\u0005\u00035k\u0019u\u0001bBCj%\u0001\u000fa\u0011\u000e\t\u0005iU2\t\u0003C\u0004\u0007nI\u0001\u001dAb\u001c\u0002\u0007\u0005\u000bd\u0007\u0005\u00035k\u0019\u0015\u0012aH2biN\\UM\u001d8fYN+W.\u001b7biRL7-\u001a$peR+\b\u000f\\32qU1cQ\u000fDA\r\u000b3II\"$\u0007\u0012\u001aUe\u0011\u0014DO\rC3)K\"+\u0007.\u001aEfQ\u0017D]\r{3\tM\"2\u0015M\u0019]d\u0011\u001aDg\r#4)N\"7\u0007^\u001a\u0005hQ\u001dDu\r[4\tP\">\u0007z\u001aux\u0011AD\u0003\u000f\u00139i\u0001\u0005\u00035k\u0019e\u0004cJ\u0011\u0007|\u0019}d1\u0011DD\r\u00173yIb%\u0007\u0018\u001ameq\u0014DR\rO3YKb,\u00074\u001a]f1\u0018D`\r\u0007L1A\" #\u0005\u001d!V\u000f\u001d7fca\u00022a\u000fDA\t\u0015i4C1\u0001?!\rYdQ\u0011\u0003\u0006'N\u0011\rA\u0010\t\u0004w\u0019%E!\u00024\u0014\u0005\u0004q\u0004cA\u001e\u0007\u000e\u0012)Qp\u0005b\u0001}A\u00191H\"%\u0005\r\u0005E2C1\u0001?!\rYdQ\u0013\u0003\u0007\u0003_\u001a\"\u0019\u0001 \u0011\u0007m2I\n\u0002\u0004\u00026N\u0011\rA\u0010\t\u0004w\u0019uEA\u0002B\u0002'\t\u0007a\bE\u0002<\rC#aA!\u0017\u0014\u0005\u0004q\u0004cA\u001e\u0007&\u00121!qW\nC\u0002y\u00022a\u000fDU\t\u0019\u0019ib\u0005b\u0001}A\u00191H\",\u0005\r\r-5C1\u0001?!\rYd\u0011\u0017\u0003\u0007\t\u0003\u0019\"\u0019\u0001 \u0011\u0007m2)\f\u0002\u0004\u0005\u0000M\u0011\rA\u0010\t\u0004w\u0019eFABC\u0003'\t\u0007a\bE\u0002<\r{#a!b%\u0014\u0005\u0004q\u0004cA\u001e\u0007B\u00121a\u0011F\nC\u0002y\u00022a\u000fDc\t\u001919m\u0005b\u0001}\t\u0019\u0011)M\u001c\t\r\u0019\u001b\u00029\u0001Df!\u0011!TGb \t\r]\u001b\u00029\u0001Dh!\u0011!TGb!\t\r1\u001c\u00029\u0001Dj!\u0011!TGb\"\t\u000f\u0005-1\u0003q\u0001\u0007XB!A'\u000eDF\u0011\u001d\t)e\u0005a\u0002\r7\u0004B\u0001N\u001b\u0007\u0010\"9\u0011qQ\nA\u0004\u0019}\u0007\u0003\u0002\u001b6\r'Cq!!5\u0014\u0001\b1\u0019\u000f\u0005\u00035k\u0019]\u0005b\u0002B\u0012'\u0001\u000faq\u001d\t\u0005iU2Y\nC\u0004\u0003~M\u0001\u001dAb;\u0011\tQ*dq\u0014\u0005\b\u0005?\u001c\u00029\u0001Dx!\u0011!TGb)\t\u000f\r%3\u0003q\u0001\u0007tB!A'\u000eDT\u0011\u001d\u0019Yl\u0005a\u0002\ro\u0004B\u0001N\u001b\u0007,\"9AQG\nA\u0004\u0019m\b\u0003\u0002\u001b6\r_Cq\u0001b.\u0014\u0001\b1y\u0010\u0005\u00035k\u0019M\u0006bBC!'\u0001\u000fq1\u0001\t\u0005iU29\fC\u0004\u0006TN\u0001\u001dab\u0002\u0011\tQ*d1\u0018\u0005\b\r[\u001a\u00029AD\u0006!\u0011!TGb0\t\u000f\u001d=1\u0003q\u0001\b\u0012\u0005\u0019\u0011)M\u001c\u0011\tQ*d1Y\u0001 G\u0006$8oS3s]\u0016d7+Z7jY\u0006$H/[2f\r>\u0014H+\u001e9mKFJT\u0003KD\f\u000fG99cb\u000b\b0\u001dMrqGD\u001e\u000f\u007f9\u0019eb\u0012\bL\u001d=s1KD,\u000f7:yfb\u0019\bh\u001d-D\u0003KD\r\u000f_:\u0019hb\u001e\b|\u001d}t1QDD\u000f\u0017;yib%\b\u0018\u001emuqTDR\u000fO;Ykb,\b4\u001e]\u0006\u0003\u0002\u001b6\u000f7\u0001\u0012&ID\u000f\u000fC9)c\"\u000b\b.\u001dErQGD\u001d\u000f{9\te\"\u0012\bJ\u001d5s\u0011KD+\u000f3:if\"\u0019\bf\u001d%\u0014bAD\u0010E\t9A+\u001e9mKFJ\u0004cA\u001e\b$\u0011)Q\b\u0006b\u0001}A\u00191hb\n\u0005\u000bM#\"\u0019\u0001 \u0011\u0007m:Y\u0003B\u0003g)\t\u0007a\bE\u0002<\u000f_!Q! \u000bC\u0002y\u00022aOD\u001a\t\u0019\t\t\u0004\u0006b\u0001}A\u00191hb\u000e\u0005\r\u0005=DC1\u0001?!\rYt1\b\u0003\u0007\u0003k#\"\u0019\u0001 \u0011\u0007m:y\u0004\u0002\u0004\u0003\u0004Q\u0011\rA\u0010\t\u0004w\u001d\rCA\u0002B-)\t\u0007a\bE\u0002<\u000f\u000f\"aAa.\u0015\u0005\u0004q\u0004cA\u001e\bL\u001111Q\u0004\u000bC\u0002y\u00022aOD(\t\u0019\u0019Y\t\u0006b\u0001}A\u00191hb\u0015\u0005\r\u0011\u0005AC1\u0001?!\rYtq\u000b\u0003\u0007\t\u007f\"\"\u0019\u0001 \u0011\u0007m:Y\u0006\u0002\u0004\u0006\u0006Q\u0011\rA\u0010\t\u0004w\u001d}CABCJ)\t\u0007a\bE\u0002<\u000fG\"aA\"\u000b\u0015\u0005\u0004q\u0004cA\u001e\bh\u00111aq\u0019\u000bC\u0002y\u00022aOD6\t\u00199i\u0007\u0006b\u0001}\t\u0019\u0011)\r\u001d\t\r\u0019#\u00029AD9!\u0011!Tg\"\t\t\r]#\u00029AD;!\u0011!Tg\"\n\t\r1$\u00029AD=!\u0011!Tg\"\u000b\t\u000f\u0005-A\u0003q\u0001\b~A!A'ND\u0017\u0011\u001d\t)\u0005\u0006a\u0002\u000f\u0003\u0003B\u0001N\u001b\b2!9\u0011q\u0011\u000bA\u0004\u001d\u0015\u0005\u0003\u0002\u001b6\u000fkAq!!5\u0015\u0001\b9I\t\u0005\u00035k\u001de\u0002b\u0002B\u0012)\u0001\u000fqQ\u0012\t\u0005iU:i\u0004C\u0004\u0003~Q\u0001\u001da\"%\u0011\tQ*t\u0011\t\u0005\b\u0005?$\u00029ADK!\u0011!Tg\"\u0012\t\u000f\r%C\u0003q\u0001\b\u001aB!A'ND%\u0011\u001d\u0019Y\f\u0006a\u0002\u000f;\u0003B\u0001N\u001b\bN!9AQ\u0007\u000bA\u0004\u001d\u0005\u0006\u0003\u0002\u001b6\u000f#Bq\u0001b.\u0015\u0001\b9)\u000b\u0005\u00035k\u001dU\u0003bBC!)\u0001\u000fq\u0011\u0016\t\u0005iU:I\u0006C\u0004\u0006TR\u0001\u001da\",\u0011\tQ*tQ\f\u0005\b\r[\"\u00029ADY!\u0011!Tg\"\u0019\t\u000f\u001d=A\u0003q\u0001\b6B!A'ND3\u0011\u001d9I\f\u0006a\u0002\u000fw\u000b1!Q\u00199!\u0011!Tg\"\u001b\u0002?\r\fGo]&fe:,GnU3nS2\fG\u000f^5dK\u001a{'\u000fV;qY\u0016\u0014\u0004'\u0006\u0016\bB\u001e5w\u0011[Dk\u000f3<in\"9\bf\u001e%xQ^Dy\u000fk<Ip\"@\t\u0002!\u0015\u0001\u0012\u0002E\u0007\u0011#A)\u0002#\u0007\u0015U\u001d\r\u0007R\u0004E\u0011\u0011KAI\u0003#\f\t2!U\u0002\u0012\bE\u001f\u0011\u0003B)\u0005#\u0013\tN!E\u0003R\u000bE-\u0011;B\t\u0007#\u001a\tjA!A'NDc!-\nsqYDf\u000f\u001f<\u0019nb6\b\\\u001e}w1]Dt\u000fW<yob=\bx\u001emxq E\u0002\u0011\u000fAY\u0001c\u0004\t\u0014!]\u0011bADeE\t9A+\u001e9mKJ\u0002\u0004cA\u001e\bN\u0012)Q(\u0006b\u0001}A\u00191h\"5\u0005\u000bM+\"\u0019\u0001 \u0011\u0007m:)\u000eB\u0003g+\t\u0007a\bE\u0002<\u000f3$Q!`\u000bC\u0002y\u00022aODo\t\u0019\t\t$\u0006b\u0001}A\u00191h\"9\u0005\r\u0005=TC1\u0001?!\rYtQ\u001d\u0003\u0007\u0003k+\"\u0019\u0001 \u0011\u0007m:I\u000f\u0002\u0004\u0003\u0004U\u0011\rA\u0010\t\u0004w\u001d5HA\u0002B-+\t\u0007a\bE\u0002<\u000fc$aAa.\u0016\u0005\u0004q\u0004cA\u001e\bv\u001211QD\u000bC\u0002y\u00022aOD}\t\u0019\u0019Y)\u0006b\u0001}A\u00191h\"@\u0005\r\u0011\u0005QC1\u0001?!\rY\u0004\u0012\u0001\u0003\u0007\t\u007f*\"\u0019\u0001 \u0011\u0007mB)\u0001\u0002\u0004\u0006\u0006U\u0011\rA\u0010\t\u0004w!%AABCJ+\t\u0007a\bE\u0002<\u0011\u001b!aA\"\u000b\u0016\u0005\u0004q\u0004cA\u001e\t\u0012\u00111aqY\u000bC\u0002y\u00022a\u000fE\u000b\t\u00199i'\u0006b\u0001}A\u00191\b#\u0007\u0005\r!mQC1\u0001?\u0005\r\t\u0015'\u000f\u0005\u0007\rV\u0001\u001d\u0001c\b\u0011\tQ*t1\u001a\u0005\u0007/V\u0001\u001d\u0001c\t\u0011\tQ*tq\u001a\u0005\u0007YV\u0001\u001d\u0001c\n\u0011\tQ*t1\u001b\u0005\b\u0003\u0017)\u00029\u0001E\u0016!\u0011!Tgb6\t\u000f\u0005\u0015S\u0003q\u0001\t0A!A'NDn\u0011\u001d\t9)\u0006a\u0002\u0011g\u0001B\u0001N\u001b\b`\"9\u0011\u0011[\u000bA\u0004!]\u0002\u0003\u0002\u001b6\u000fGDqAa\t\u0016\u0001\bAY\u0004\u0005\u00035k\u001d\u001d\bb\u0002B?+\u0001\u000f\u0001r\b\t\u0005iU:Y\u000fC\u0004\u0003`V\u0001\u001d\u0001c\u0011\u0011\tQ*tq\u001e\u0005\b\u0007\u0013*\u00029\u0001E$!\u0011!Tgb=\t\u000f\rmV\u0003q\u0001\tLA!A'ND|\u0011\u001d!)$\u0006a\u0002\u0011\u001f\u0002B\u0001N\u001b\b|\"9AqW\u000bA\u0004!M\u0003\u0003\u0002\u001b6\u000f\u007fDq!\"\u0011\u0016\u0001\bA9\u0006\u0005\u00035k!\r\u0001bBCj+\u0001\u000f\u00012\f\t\u0005iUB9\u0001C\u0004\u0007nU\u0001\u001d\u0001c\u0018\u0011\tQ*\u00042\u0002\u0005\b\u000f\u001f)\u00029\u0001E2!\u0011!T\u0007c\u0004\t\u000f\u001deV\u0003q\u0001\thA!A'\u000eE\n\u0011\u001dAY'\u0006a\u0002\u0011[\n1!Q\u0019:!\u0011!T\u0007c\u0006\u0002?\r\fGo]&fe:,GnU3nS2\fG\u000f^5dK\u001a{'\u000fV;qY\u0016\u0014\u0014'\u0006\u0017\tt!}\u00042\u0011ED\u0011\u0017Cy\tc%\t\u0018\"m\u0005r\u0014ER\u0011OCY\u000bc,\t4\"]\u00062\u0018E`\u0011\u0007D9\rc3\tPRa\u0003R\u000fEj\u0011/DY\u000ec8\td\"\u001d\b2\u001eEx\u0011gD9\u0010c?\t\u0000&\r\u0011rAE\u0006\u0013\u001fI\u0019\"c\u0006\n\u001c%}\u00112\u0005\t\u0005iUB9\bE\u0017\"\u0011sBi\b#!\t\u0006\"%\u0005R\u0012EI\u0011+CI\n#(\t\"\"\u0015\u0006\u0012\u0016EW\u0011cC)\f#/\t>\"\u0005\u0007R\u0019Ee\u0011\u001bL1\u0001c\u001f#\u0005\u001d!V\u000f\u001d7feE\u00022a\u000fE@\t\u0015idC1\u0001?!\rY\u00042\u0011\u0003\u0006'Z\u0011\rA\u0010\t\u0004w!\u001dE!\u00024\u0017\u0005\u0004q\u0004cA\u001e\t\f\u0012)QP\u0006b\u0001}A\u00191\bc$\u0005\r\u0005EbC1\u0001?!\rY\u00042\u0013\u0003\u0007\u0003_2\"\u0019\u0001 \u0011\u0007mB9\n\u0002\u0004\u00026Z\u0011\rA\u0010\t\u0004w!mEA\u0002B\u0002-\t\u0007a\bE\u0002<\u0011?#aA!\u0017\u0017\u0005\u0004q\u0004cA\u001e\t$\u00121!q\u0017\fC\u0002y\u00022a\u000fET\t\u0019\u0019iB\u0006b\u0001}A\u00191\bc+\u0005\r\r-eC1\u0001?!\rY\u0004r\u0016\u0003\u0007\t\u00031\"\u0019\u0001 \u0011\u0007mB\u0019\f\u0002\u0004\u0005\u0000Y\u0011\rA\u0010\t\u0004w!]FABC\u0003-\t\u0007a\bE\u0002<\u0011w#a!b%\u0017\u0005\u0004q\u0004cA\u001e\t@\u00121a\u0011\u0006\fC\u0002y\u00022a\u000fEb\t\u001919M\u0006b\u0001}A\u00191\bc2\u0005\r\u001d5dC1\u0001?!\rY\u00042\u001a\u0003\u0007\u001171\"\u0019\u0001 \u0011\u0007mBy\r\u0002\u0004\tRZ\u0011\rA\u0010\u0002\u0004\u0003J\u0002\u0004B\u0002$\u0017\u0001\bA)\u000e\u0005\u00035k!u\u0004BB,\u0017\u0001\bAI\u000e\u0005\u00035k!\u0005\u0005B\u00027\u0017\u0001\bAi\u000e\u0005\u00035k!\u0015\u0005bBA\u0006-\u0001\u000f\u0001\u0012\u001d\t\u0005iUBI\tC\u0004\u0002FY\u0001\u001d\u0001#:\u0011\tQ*\u0004R\u0012\u0005\b\u0003\u000f3\u00029\u0001Eu!\u0011!T\u0007#%\t\u000f\u0005Eg\u0003q\u0001\tnB!A'\u000eEK\u0011\u001d\u0011\u0019C\u0006a\u0002\u0011c\u0004B\u0001N\u001b\t\u001a\"9!Q\u0010\fA\u0004!U\b\u0003\u0002\u001b6\u0011;CqAa8\u0017\u0001\bAI\u0010\u0005\u00035k!\u0005\u0006bBB%-\u0001\u000f\u0001R \t\u0005iUB)\u000bC\u0004\u0004<Z\u0001\u001d!#\u0001\u0011\tQ*\u0004\u0012\u0016\u0005\b\tk1\u00029AE\u0003!\u0011!T\u0007#,\t\u000f\u0011]f\u0003q\u0001\n\nA!A'\u000eEY\u0011\u001d)\tE\u0006a\u0002\u0013\u001b\u0001B\u0001N\u001b\t6\"9Q1\u001b\fA\u0004%E\u0001\u0003\u0002\u001b6\u0011sCqA\"\u001c\u0017\u0001\bI)\u0002\u0005\u00035k!u\u0006bBD\b-\u0001\u000f\u0011\u0012\u0004\t\u0005iUB\t\rC\u0004\b:Z\u0001\u001d!#\b\u0011\tQ*\u0004R\u0019\u0005\b\u0011W2\u00029AE\u0011!\u0011!T\u0007#3\t\u000f%\u0015b\u0003q\u0001\n(\u0005\u0019\u0011I\r\u0019\u0011\tQ*\u0004RZ\u0001 G\u0006$8oS3s]\u0016d7+Z7jY\u0006$H/[2f\r>\u0014H+\u001e9mKJ\u0012TCLE\u0017\u0013sIi$#\u0011\nF%%\u0013RJE)\u0013+JI&#\u0018\nb%\u0015\u0014\u0012NE7\u0013cJ)(#\u001f\n~%\u0005\u0015RQEE\u0013\u001b#b&c\f\n\u0012&U\u0015\u0012TEO\u0013CK)+#+\n.&E\u0016RWE]\u0013{K\t-#2\nJ&5\u0017\u0012[Ek\u00133Li.#9\nfB!A'NE\u0019!=\n\u00132GE\u001c\u0013wIy$c\u0011\nH%-\u0013rJE*\u0013/JY&c\u0018\nd%\u001d\u00142NE8\u0013gJ9(c\u001f\n\u0000%\r\u0015rQEF\u0013\rI)D\t\u0002\b)V\u0004H.\u001a\u001a3!\rY\u0014\u0012\b\u0003\u0006{]\u0011\rA\u0010\t\u0004w%uB!B*\u0018\u0005\u0004q\u0004cA\u001e\nB\u0011)am\u0006b\u0001}A\u00191(#\u0012\u0005\u000bu<\"\u0019\u0001 \u0011\u0007mJI\u0005\u0002\u0004\u00022]\u0011\rA\u0010\t\u0004w%5CABA8/\t\u0007a\bE\u0002<\u0013#\"a!!.\u0018\u0005\u0004q\u0004cA\u001e\nV\u00111!1A\fC\u0002y\u00022aOE-\t\u0019\u0011If\u0006b\u0001}A\u00191(#\u0018\u0005\r\t]vC1\u0001?!\rY\u0014\u0012\r\u0003\u0007\u0007;9\"\u0019\u0001 \u0011\u0007mJ)\u0007\u0002\u0004\u0004\f^\u0011\rA\u0010\t\u0004w%%DA\u0002C\u0001/\t\u0007a\bE\u0002<\u0013[\"a\u0001b \u0018\u0005\u0004q\u0004cA\u001e\nr\u00111QQA\fC\u0002y\u00022aOE;\t\u0019)\u0019j\u0006b\u0001}A\u00191(#\u001f\u0005\r\u0019%rC1\u0001?!\rY\u0014R\u0010\u0003\u0007\r\u000f<\"\u0019\u0001 \u0011\u0007mJ\t\t\u0002\u0004\bn]\u0011\rA\u0010\t\u0004w%\u0015EA\u0002E\u000e/\t\u0007a\bE\u0002<\u0013\u0013#a\u0001#5\u0018\u0005\u0004q\u0004cA\u001e\n\u000e\u00121\u0011rR\fC\u0002y\u00121!\u0011\u001a2\u0011\u00191u\u0003q\u0001\n\u0014B!A'NE\u001c\u0011\u00199v\u0003q\u0001\n\u0018B!A'NE\u001e\u0011\u0019aw\u0003q\u0001\n\u001cB!A'NE \u0011\u001d\tYa\u0006a\u0002\u0013?\u0003B\u0001N\u001b\nD!9\u0011QI\fA\u0004%\r\u0006\u0003\u0002\u001b6\u0013\u000fBq!a\"\u0018\u0001\bI9\u000b\u0005\u00035k%-\u0003bBAi/\u0001\u000f\u00112\u0016\t\u0005iUJy\u0005C\u0004\u0003$]\u0001\u001d!c,\u0011\tQ*\u00142\u000b\u0005\b\u0005{:\u00029AEZ!\u0011!T'c\u0016\t\u000f\t}w\u0003q\u0001\n8B!A'NE.\u0011\u001d\u0019Ie\u0006a\u0002\u0013w\u0003B\u0001N\u001b\n`!911X\fA\u0004%}\u0006\u0003\u0002\u001b6\u0013GBq\u0001\"\u000e\u0018\u0001\bI\u0019\r\u0005\u00035k%\u001d\u0004b\u0002C\\/\u0001\u000f\u0011r\u0019\t\u0005iUJY\u0007C\u0004\u0006B]\u0001\u001d!c3\u0011\tQ*\u0014r\u000e\u0005\b\u000b'<\u00029AEh!\u0011!T'c\u001d\t\u000f\u00195t\u0003q\u0001\nTB!A'NE<\u0011\u001d9ya\u0006a\u0002\u0013/\u0004B\u0001N\u001b\n|!9q\u0011X\fA\u0004%m\u0007\u0003\u0002\u001b6\u0013\u007fBq\u0001c\u001b\u0018\u0001\bIy\u000e\u0005\u00035k%\r\u0005bBE\u0013/\u0001\u000f\u00112\u001d\t\u0005iUJ9\tC\u0004\nh^\u0001\u001d!#;\u0002\u0007\u0005\u0013\u0014\u0007\u0005\u00035k%-\u0005"
)
public interface TupleSemilatticeInstances extends TupleMonoidInstances {
   // $FF: synthetic method
   static Semilattice catsKernelSemilatticeForTuple1$(final TupleSemilatticeInstances $this, final Semilattice A0) {
      return $this.catsKernelSemilatticeForTuple1(A0);
   }

   default Semilattice catsKernelSemilatticeForTuple1(final Semilattice A0) {
      return new Semilattice(A0) {
         private final Semilattice A0$551;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
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

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Tuple1 combine(final Tuple1 x, final Tuple1 y) {
            return new Tuple1(this.A0$551.combine(x._1(), y._1()));
         }

         public {
            this.A0$551 = A0$551;
            Semigroup.$init$(this);
            Band.$init$(this);
            CommutativeSemigroup.$init$(this);
            Semilattice.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Semilattice catsKernelSemilatticeForTuple2$(final TupleSemilatticeInstances $this, final Semilattice A0, final Semilattice A1) {
      return $this.catsKernelSemilatticeForTuple2(A0, A1);
   }

   default Semilattice catsKernelSemilatticeForTuple2(final Semilattice A0, final Semilattice A1) {
      return new Semilattice(A0, A1) {
         private final Semilattice A0$552;
         private final Semilattice A1$526;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
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

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Tuple2 combine(final Tuple2 x, final Tuple2 y) {
            return new Tuple2(this.A0$552.combine(x._1(), y._1()), this.A1$526.combine(x._2(), y._2()));
         }

         public {
            this.A0$552 = A0$552;
            this.A1$526 = A1$526;
            Semigroup.$init$(this);
            Band.$init$(this);
            CommutativeSemigroup.$init$(this);
            Semilattice.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Semilattice catsKernelSemilatticeForTuple3$(final TupleSemilatticeInstances $this, final Semilattice A0, final Semilattice A1, final Semilattice A2) {
      return $this.catsKernelSemilatticeForTuple3(A0, A1, A2);
   }

   default Semilattice catsKernelSemilatticeForTuple3(final Semilattice A0, final Semilattice A1, final Semilattice A2) {
      return new Semilattice(A0, A1, A2) {
         private final Semilattice A0$553;
         private final Semilattice A1$527;
         private final Semilattice A2$501;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
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

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Tuple3 combine(final Tuple3 x, final Tuple3 y) {
            return new Tuple3(this.A0$553.combine(x._1(), y._1()), this.A1$527.combine(x._2(), y._2()), this.A2$501.combine(x._3(), y._3()));
         }

         public {
            this.A0$553 = A0$553;
            this.A1$527 = A1$527;
            this.A2$501 = A2$501;
            Semigroup.$init$(this);
            Band.$init$(this);
            CommutativeSemigroup.$init$(this);
            Semilattice.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Semilattice catsKernelSemilatticeForTuple4$(final TupleSemilatticeInstances $this, final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3) {
      return $this.catsKernelSemilatticeForTuple4(A0, A1, A2, A3);
   }

   default Semilattice catsKernelSemilatticeForTuple4(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3) {
      return new Semilattice(A0, A1, A2, A3) {
         private final Semilattice A0$554;
         private final Semilattice A1$528;
         private final Semilattice A2$502;
         private final Semilattice A3$476;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
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

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Tuple4 combine(final Tuple4 x, final Tuple4 y) {
            return new Tuple4(this.A0$554.combine(x._1(), y._1()), this.A1$528.combine(x._2(), y._2()), this.A2$502.combine(x._3(), y._3()), this.A3$476.combine(x._4(), y._4()));
         }

         public {
            this.A0$554 = A0$554;
            this.A1$528 = A1$528;
            this.A2$502 = A2$502;
            this.A3$476 = A3$476;
            Semigroup.$init$(this);
            Band.$init$(this);
            CommutativeSemigroup.$init$(this);
            Semilattice.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Semilattice catsKernelSemilatticeForTuple5$(final TupleSemilatticeInstances $this, final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4) {
      return $this.catsKernelSemilatticeForTuple5(A0, A1, A2, A3, A4);
   }

   default Semilattice catsKernelSemilatticeForTuple5(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4) {
      return new Semilattice(A0, A1, A2, A3, A4) {
         private final Semilattice A0$555;
         private final Semilattice A1$529;
         private final Semilattice A2$503;
         private final Semilattice A3$477;
         private final Semilattice A4$451;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
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

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Tuple5 combine(final Tuple5 x, final Tuple5 y) {
            return new Tuple5(this.A0$555.combine(x._1(), y._1()), this.A1$529.combine(x._2(), y._2()), this.A2$503.combine(x._3(), y._3()), this.A3$477.combine(x._4(), y._4()), this.A4$451.combine(x._5(), y._5()));
         }

         public {
            this.A0$555 = A0$555;
            this.A1$529 = A1$529;
            this.A2$503 = A2$503;
            this.A3$477 = A3$477;
            this.A4$451 = A4$451;
            Semigroup.$init$(this);
            Band.$init$(this);
            CommutativeSemigroup.$init$(this);
            Semilattice.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Semilattice catsKernelSemilatticeForTuple6$(final TupleSemilatticeInstances $this, final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5) {
      return $this.catsKernelSemilatticeForTuple6(A0, A1, A2, A3, A4, A5);
   }

   default Semilattice catsKernelSemilatticeForTuple6(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5) {
      return new Semilattice(A0, A1, A2, A3, A4, A5) {
         private final Semilattice A0$556;
         private final Semilattice A1$530;
         private final Semilattice A2$504;
         private final Semilattice A3$478;
         private final Semilattice A4$452;
         private final Semilattice A5$426;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
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

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Tuple6 combine(final Tuple6 x, final Tuple6 y) {
            return new Tuple6(this.A0$556.combine(x._1(), y._1()), this.A1$530.combine(x._2(), y._2()), this.A2$504.combine(x._3(), y._3()), this.A3$478.combine(x._4(), y._4()), this.A4$452.combine(x._5(), y._5()), this.A5$426.combine(x._6(), y._6()));
         }

         public {
            this.A0$556 = A0$556;
            this.A1$530 = A1$530;
            this.A2$504 = A2$504;
            this.A3$478 = A3$478;
            this.A4$452 = A4$452;
            this.A5$426 = A5$426;
            Semigroup.$init$(this);
            Band.$init$(this);
            CommutativeSemigroup.$init$(this);
            Semilattice.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Semilattice catsKernelSemilatticeForTuple7$(final TupleSemilatticeInstances $this, final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6) {
      return $this.catsKernelSemilatticeForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   default Semilattice catsKernelSemilatticeForTuple7(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6) {
      return new Semilattice(A0, A1, A2, A3, A4, A5, A6) {
         private final Semilattice A0$557;
         private final Semilattice A1$531;
         private final Semilattice A2$505;
         private final Semilattice A3$479;
         private final Semilattice A4$453;
         private final Semilattice A5$427;
         private final Semilattice A6$401;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
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

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Tuple7 combine(final Tuple7 x, final Tuple7 y) {
            return new Tuple7(this.A0$557.combine(x._1(), y._1()), this.A1$531.combine(x._2(), y._2()), this.A2$505.combine(x._3(), y._3()), this.A3$479.combine(x._4(), y._4()), this.A4$453.combine(x._5(), y._5()), this.A5$427.combine(x._6(), y._6()), this.A6$401.combine(x._7(), y._7()));
         }

         public {
            this.A0$557 = A0$557;
            this.A1$531 = A1$531;
            this.A2$505 = A2$505;
            this.A3$479 = A3$479;
            this.A4$453 = A4$453;
            this.A5$427 = A5$427;
            this.A6$401 = A6$401;
            Semigroup.$init$(this);
            Band.$init$(this);
            CommutativeSemigroup.$init$(this);
            Semilattice.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Semilattice catsKernelSemilatticeForTuple8$(final TupleSemilatticeInstances $this, final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7) {
      return $this.catsKernelSemilatticeForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   default Semilattice catsKernelSemilatticeForTuple8(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7) {
      return new Semilattice(A0, A1, A2, A3, A4, A5, A6, A7) {
         private final Semilattice A0$558;
         private final Semilattice A1$532;
         private final Semilattice A2$506;
         private final Semilattice A3$480;
         private final Semilattice A4$454;
         private final Semilattice A5$428;
         private final Semilattice A6$402;
         private final Semilattice A7$376;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
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

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Tuple8 combine(final Tuple8 x, final Tuple8 y) {
            return new Tuple8(this.A0$558.combine(x._1(), y._1()), this.A1$532.combine(x._2(), y._2()), this.A2$506.combine(x._3(), y._3()), this.A3$480.combine(x._4(), y._4()), this.A4$454.combine(x._5(), y._5()), this.A5$428.combine(x._6(), y._6()), this.A6$402.combine(x._7(), y._7()), this.A7$376.combine(x._8(), y._8()));
         }

         public {
            this.A0$558 = A0$558;
            this.A1$532 = A1$532;
            this.A2$506 = A2$506;
            this.A3$480 = A3$480;
            this.A4$454 = A4$454;
            this.A5$428 = A5$428;
            this.A6$402 = A6$402;
            this.A7$376 = A7$376;
            Semigroup.$init$(this);
            Band.$init$(this);
            CommutativeSemigroup.$init$(this);
            Semilattice.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Semilattice catsKernelSemilatticeForTuple9$(final TupleSemilatticeInstances $this, final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8) {
      return $this.catsKernelSemilatticeForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   default Semilattice catsKernelSemilatticeForTuple9(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8) {
      return new Semilattice(A0, A1, A2, A3, A4, A5, A6, A7, A8) {
         private final Semilattice A0$559;
         private final Semilattice A1$533;
         private final Semilattice A2$507;
         private final Semilattice A3$481;
         private final Semilattice A4$455;
         private final Semilattice A5$429;
         private final Semilattice A6$403;
         private final Semilattice A7$377;
         private final Semilattice A8$351;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
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

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Tuple9 combine(final Tuple9 x, final Tuple9 y) {
            return new Tuple9(this.A0$559.combine(x._1(), y._1()), this.A1$533.combine(x._2(), y._2()), this.A2$507.combine(x._3(), y._3()), this.A3$481.combine(x._4(), y._4()), this.A4$455.combine(x._5(), y._5()), this.A5$429.combine(x._6(), y._6()), this.A6$403.combine(x._7(), y._7()), this.A7$377.combine(x._8(), y._8()), this.A8$351.combine(x._9(), y._9()));
         }

         public {
            this.A0$559 = A0$559;
            this.A1$533 = A1$533;
            this.A2$507 = A2$507;
            this.A3$481 = A3$481;
            this.A4$455 = A4$455;
            this.A5$429 = A5$429;
            this.A6$403 = A6$403;
            this.A7$377 = A7$377;
            this.A8$351 = A8$351;
            Semigroup.$init$(this);
            Band.$init$(this);
            CommutativeSemigroup.$init$(this);
            Semilattice.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Semilattice catsKernelSemilatticeForTuple10$(final TupleSemilatticeInstances $this, final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9) {
      return $this.catsKernelSemilatticeForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   default Semilattice catsKernelSemilatticeForTuple10(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9) {
      return new Semilattice(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9) {
         private final Semilattice A0$560;
         private final Semilattice A1$534;
         private final Semilattice A2$508;
         private final Semilattice A3$482;
         private final Semilattice A4$456;
         private final Semilattice A5$430;
         private final Semilattice A6$404;
         private final Semilattice A7$378;
         private final Semilattice A8$352;
         private final Semilattice A9$326;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
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

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Tuple10 combine(final Tuple10 x, final Tuple10 y) {
            return new Tuple10(this.A0$560.combine(x._1(), y._1()), this.A1$534.combine(x._2(), y._2()), this.A2$508.combine(x._3(), y._3()), this.A3$482.combine(x._4(), y._4()), this.A4$456.combine(x._5(), y._5()), this.A5$430.combine(x._6(), y._6()), this.A6$404.combine(x._7(), y._7()), this.A7$378.combine(x._8(), y._8()), this.A8$352.combine(x._9(), y._9()), this.A9$326.combine(x._10(), y._10()));
         }

         public {
            this.A0$560 = A0$560;
            this.A1$534 = A1$534;
            this.A2$508 = A2$508;
            this.A3$482 = A3$482;
            this.A4$456 = A4$456;
            this.A5$430 = A5$430;
            this.A6$404 = A6$404;
            this.A7$378 = A7$378;
            this.A8$352 = A8$352;
            this.A9$326 = A9$326;
            Semigroup.$init$(this);
            Band.$init$(this);
            CommutativeSemigroup.$init$(this);
            Semilattice.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Semilattice catsKernelSemilatticeForTuple11$(final TupleSemilatticeInstances $this, final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10) {
      return $this.catsKernelSemilatticeForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   default Semilattice catsKernelSemilatticeForTuple11(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10) {
      return new Semilattice(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10) {
         private final Semilattice A0$561;
         private final Semilattice A1$535;
         private final Semilattice A2$509;
         private final Semilattice A3$483;
         private final Semilattice A4$457;
         private final Semilattice A5$431;
         private final Semilattice A6$405;
         private final Semilattice A7$379;
         private final Semilattice A8$353;
         private final Semilattice A9$327;
         private final Semilattice A10$301;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
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

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Tuple11 combine(final Tuple11 x, final Tuple11 y) {
            return new Tuple11(this.A0$561.combine(x._1(), y._1()), this.A1$535.combine(x._2(), y._2()), this.A2$509.combine(x._3(), y._3()), this.A3$483.combine(x._4(), y._4()), this.A4$457.combine(x._5(), y._5()), this.A5$431.combine(x._6(), y._6()), this.A6$405.combine(x._7(), y._7()), this.A7$379.combine(x._8(), y._8()), this.A8$353.combine(x._9(), y._9()), this.A9$327.combine(x._10(), y._10()), this.A10$301.combine(x._11(), y._11()));
         }

         public {
            this.A0$561 = A0$561;
            this.A1$535 = A1$535;
            this.A2$509 = A2$509;
            this.A3$483 = A3$483;
            this.A4$457 = A4$457;
            this.A5$431 = A5$431;
            this.A6$405 = A6$405;
            this.A7$379 = A7$379;
            this.A8$353 = A8$353;
            this.A9$327 = A9$327;
            this.A10$301 = A10$301;
            Semigroup.$init$(this);
            Band.$init$(this);
            CommutativeSemigroup.$init$(this);
            Semilattice.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Semilattice catsKernelSemilatticeForTuple12$(final TupleSemilatticeInstances $this, final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11) {
      return $this.catsKernelSemilatticeForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   default Semilattice catsKernelSemilatticeForTuple12(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11) {
      return new Semilattice(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11) {
         private final Semilattice A0$562;
         private final Semilattice A1$536;
         private final Semilattice A2$510;
         private final Semilattice A3$484;
         private final Semilattice A4$458;
         private final Semilattice A5$432;
         private final Semilattice A6$406;
         private final Semilattice A7$380;
         private final Semilattice A8$354;
         private final Semilattice A9$328;
         private final Semilattice A10$302;
         private final Semilattice A11$276;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
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

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Tuple12 combine(final Tuple12 x, final Tuple12 y) {
            return new Tuple12(this.A0$562.combine(x._1(), y._1()), this.A1$536.combine(x._2(), y._2()), this.A2$510.combine(x._3(), y._3()), this.A3$484.combine(x._4(), y._4()), this.A4$458.combine(x._5(), y._5()), this.A5$432.combine(x._6(), y._6()), this.A6$406.combine(x._7(), y._7()), this.A7$380.combine(x._8(), y._8()), this.A8$354.combine(x._9(), y._9()), this.A9$328.combine(x._10(), y._10()), this.A10$302.combine(x._11(), y._11()), this.A11$276.combine(x._12(), y._12()));
         }

         public {
            this.A0$562 = A0$562;
            this.A1$536 = A1$536;
            this.A2$510 = A2$510;
            this.A3$484 = A3$484;
            this.A4$458 = A4$458;
            this.A5$432 = A5$432;
            this.A6$406 = A6$406;
            this.A7$380 = A7$380;
            this.A8$354 = A8$354;
            this.A9$328 = A9$328;
            this.A10$302 = A10$302;
            this.A11$276 = A11$276;
            Semigroup.$init$(this);
            Band.$init$(this);
            CommutativeSemigroup.$init$(this);
            Semilattice.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Semilattice catsKernelSemilatticeForTuple13$(final TupleSemilatticeInstances $this, final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12) {
      return $this.catsKernelSemilatticeForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   default Semilattice catsKernelSemilatticeForTuple13(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12) {
      return new Semilattice(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12) {
         private final Semilattice A0$563;
         private final Semilattice A1$537;
         private final Semilattice A2$511;
         private final Semilattice A3$485;
         private final Semilattice A4$459;
         private final Semilattice A5$433;
         private final Semilattice A6$407;
         private final Semilattice A7$381;
         private final Semilattice A8$355;
         private final Semilattice A9$329;
         private final Semilattice A10$303;
         private final Semilattice A11$277;
         private final Semilattice A12$251;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
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

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Tuple13 combine(final Tuple13 x, final Tuple13 y) {
            return new Tuple13(this.A0$563.combine(x._1(), y._1()), this.A1$537.combine(x._2(), y._2()), this.A2$511.combine(x._3(), y._3()), this.A3$485.combine(x._4(), y._4()), this.A4$459.combine(x._5(), y._5()), this.A5$433.combine(x._6(), y._6()), this.A6$407.combine(x._7(), y._7()), this.A7$381.combine(x._8(), y._8()), this.A8$355.combine(x._9(), y._9()), this.A9$329.combine(x._10(), y._10()), this.A10$303.combine(x._11(), y._11()), this.A11$277.combine(x._12(), y._12()), this.A12$251.combine(x._13(), y._13()));
         }

         public {
            this.A0$563 = A0$563;
            this.A1$537 = A1$537;
            this.A2$511 = A2$511;
            this.A3$485 = A3$485;
            this.A4$459 = A4$459;
            this.A5$433 = A5$433;
            this.A6$407 = A6$407;
            this.A7$381 = A7$381;
            this.A8$355 = A8$355;
            this.A9$329 = A9$329;
            this.A10$303 = A10$303;
            this.A11$277 = A11$277;
            this.A12$251 = A12$251;
            Semigroup.$init$(this);
            Band.$init$(this);
            CommutativeSemigroup.$init$(this);
            Semilattice.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Semilattice catsKernelSemilatticeForTuple14$(final TupleSemilatticeInstances $this, final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13) {
      return $this.catsKernelSemilatticeForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   default Semilattice catsKernelSemilatticeForTuple14(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13) {
      return new Semilattice(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13) {
         private final Semilattice A0$564;
         private final Semilattice A1$538;
         private final Semilattice A2$512;
         private final Semilattice A3$486;
         private final Semilattice A4$460;
         private final Semilattice A5$434;
         private final Semilattice A6$408;
         private final Semilattice A7$382;
         private final Semilattice A8$356;
         private final Semilattice A9$330;
         private final Semilattice A10$304;
         private final Semilattice A11$278;
         private final Semilattice A12$252;
         private final Semilattice A13$226;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
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

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Tuple14 combine(final Tuple14 x, final Tuple14 y) {
            return new Tuple14(this.A0$564.combine(x._1(), y._1()), this.A1$538.combine(x._2(), y._2()), this.A2$512.combine(x._3(), y._3()), this.A3$486.combine(x._4(), y._4()), this.A4$460.combine(x._5(), y._5()), this.A5$434.combine(x._6(), y._6()), this.A6$408.combine(x._7(), y._7()), this.A7$382.combine(x._8(), y._8()), this.A8$356.combine(x._9(), y._9()), this.A9$330.combine(x._10(), y._10()), this.A10$304.combine(x._11(), y._11()), this.A11$278.combine(x._12(), y._12()), this.A12$252.combine(x._13(), y._13()), this.A13$226.combine(x._14(), y._14()));
         }

         public {
            this.A0$564 = A0$564;
            this.A1$538 = A1$538;
            this.A2$512 = A2$512;
            this.A3$486 = A3$486;
            this.A4$460 = A4$460;
            this.A5$434 = A5$434;
            this.A6$408 = A6$408;
            this.A7$382 = A7$382;
            this.A8$356 = A8$356;
            this.A9$330 = A9$330;
            this.A10$304 = A10$304;
            this.A11$278 = A11$278;
            this.A12$252 = A12$252;
            this.A13$226 = A13$226;
            Semigroup.$init$(this);
            Band.$init$(this);
            CommutativeSemigroup.$init$(this);
            Semilattice.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Semilattice catsKernelSemilatticeForTuple15$(final TupleSemilatticeInstances $this, final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14) {
      return $this.catsKernelSemilatticeForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   default Semilattice catsKernelSemilatticeForTuple15(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14) {
      return new Semilattice(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14) {
         private final Semilattice A0$565;
         private final Semilattice A1$539;
         private final Semilattice A2$513;
         private final Semilattice A3$487;
         private final Semilattice A4$461;
         private final Semilattice A5$435;
         private final Semilattice A6$409;
         private final Semilattice A7$383;
         private final Semilattice A8$357;
         private final Semilattice A9$331;
         private final Semilattice A10$305;
         private final Semilattice A11$279;
         private final Semilattice A12$253;
         private final Semilattice A13$227;
         private final Semilattice A14$201;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
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

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Tuple15 combine(final Tuple15 x, final Tuple15 y) {
            return new Tuple15(this.A0$565.combine(x._1(), y._1()), this.A1$539.combine(x._2(), y._2()), this.A2$513.combine(x._3(), y._3()), this.A3$487.combine(x._4(), y._4()), this.A4$461.combine(x._5(), y._5()), this.A5$435.combine(x._6(), y._6()), this.A6$409.combine(x._7(), y._7()), this.A7$383.combine(x._8(), y._8()), this.A8$357.combine(x._9(), y._9()), this.A9$331.combine(x._10(), y._10()), this.A10$305.combine(x._11(), y._11()), this.A11$279.combine(x._12(), y._12()), this.A12$253.combine(x._13(), y._13()), this.A13$227.combine(x._14(), y._14()), this.A14$201.combine(x._15(), y._15()));
         }

         public {
            this.A0$565 = A0$565;
            this.A1$539 = A1$539;
            this.A2$513 = A2$513;
            this.A3$487 = A3$487;
            this.A4$461 = A4$461;
            this.A5$435 = A5$435;
            this.A6$409 = A6$409;
            this.A7$383 = A7$383;
            this.A8$357 = A8$357;
            this.A9$331 = A9$331;
            this.A10$305 = A10$305;
            this.A11$279 = A11$279;
            this.A12$253 = A12$253;
            this.A13$227 = A13$227;
            this.A14$201 = A14$201;
            Semigroup.$init$(this);
            Band.$init$(this);
            CommutativeSemigroup.$init$(this);
            Semilattice.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Semilattice catsKernelSemilatticeForTuple16$(final TupleSemilatticeInstances $this, final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15) {
      return $this.catsKernelSemilatticeForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   default Semilattice catsKernelSemilatticeForTuple16(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15) {
      return new Semilattice(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15) {
         private final Semilattice A0$566;
         private final Semilattice A1$540;
         private final Semilattice A2$514;
         private final Semilattice A3$488;
         private final Semilattice A4$462;
         private final Semilattice A5$436;
         private final Semilattice A6$410;
         private final Semilattice A7$384;
         private final Semilattice A8$358;
         private final Semilattice A9$332;
         private final Semilattice A10$306;
         private final Semilattice A11$280;
         private final Semilattice A12$254;
         private final Semilattice A13$228;
         private final Semilattice A14$202;
         private final Semilattice A15$176;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
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

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Tuple16 combine(final Tuple16 x, final Tuple16 y) {
            return new Tuple16(this.A0$566.combine(x._1(), y._1()), this.A1$540.combine(x._2(), y._2()), this.A2$514.combine(x._3(), y._3()), this.A3$488.combine(x._4(), y._4()), this.A4$462.combine(x._5(), y._5()), this.A5$436.combine(x._6(), y._6()), this.A6$410.combine(x._7(), y._7()), this.A7$384.combine(x._8(), y._8()), this.A8$358.combine(x._9(), y._9()), this.A9$332.combine(x._10(), y._10()), this.A10$306.combine(x._11(), y._11()), this.A11$280.combine(x._12(), y._12()), this.A12$254.combine(x._13(), y._13()), this.A13$228.combine(x._14(), y._14()), this.A14$202.combine(x._15(), y._15()), this.A15$176.combine(x._16(), y._16()));
         }

         public {
            this.A0$566 = A0$566;
            this.A1$540 = A1$540;
            this.A2$514 = A2$514;
            this.A3$488 = A3$488;
            this.A4$462 = A4$462;
            this.A5$436 = A5$436;
            this.A6$410 = A6$410;
            this.A7$384 = A7$384;
            this.A8$358 = A8$358;
            this.A9$332 = A9$332;
            this.A10$306 = A10$306;
            this.A11$280 = A11$280;
            this.A12$254 = A12$254;
            this.A13$228 = A13$228;
            this.A14$202 = A14$202;
            this.A15$176 = A15$176;
            Semigroup.$init$(this);
            Band.$init$(this);
            CommutativeSemigroup.$init$(this);
            Semilattice.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Semilattice catsKernelSemilatticeForTuple17$(final TupleSemilatticeInstances $this, final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16) {
      return $this.catsKernelSemilatticeForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   default Semilattice catsKernelSemilatticeForTuple17(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16) {
      return new Semilattice(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16) {
         private final Semilattice A0$567;
         private final Semilattice A1$541;
         private final Semilattice A2$515;
         private final Semilattice A3$489;
         private final Semilattice A4$463;
         private final Semilattice A5$437;
         private final Semilattice A6$411;
         private final Semilattice A7$385;
         private final Semilattice A8$359;
         private final Semilattice A9$333;
         private final Semilattice A10$307;
         private final Semilattice A11$281;
         private final Semilattice A12$255;
         private final Semilattice A13$229;
         private final Semilattice A14$203;
         private final Semilattice A15$177;
         private final Semilattice A16$151;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
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

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Tuple17 combine(final Tuple17 x, final Tuple17 y) {
            return new Tuple17(this.A0$567.combine(x._1(), y._1()), this.A1$541.combine(x._2(), y._2()), this.A2$515.combine(x._3(), y._3()), this.A3$489.combine(x._4(), y._4()), this.A4$463.combine(x._5(), y._5()), this.A5$437.combine(x._6(), y._6()), this.A6$411.combine(x._7(), y._7()), this.A7$385.combine(x._8(), y._8()), this.A8$359.combine(x._9(), y._9()), this.A9$333.combine(x._10(), y._10()), this.A10$307.combine(x._11(), y._11()), this.A11$281.combine(x._12(), y._12()), this.A12$255.combine(x._13(), y._13()), this.A13$229.combine(x._14(), y._14()), this.A14$203.combine(x._15(), y._15()), this.A15$177.combine(x._16(), y._16()), this.A16$151.combine(x._17(), y._17()));
         }

         public {
            this.A0$567 = A0$567;
            this.A1$541 = A1$541;
            this.A2$515 = A2$515;
            this.A3$489 = A3$489;
            this.A4$463 = A4$463;
            this.A5$437 = A5$437;
            this.A6$411 = A6$411;
            this.A7$385 = A7$385;
            this.A8$359 = A8$359;
            this.A9$333 = A9$333;
            this.A10$307 = A10$307;
            this.A11$281 = A11$281;
            this.A12$255 = A12$255;
            this.A13$229 = A13$229;
            this.A14$203 = A14$203;
            this.A15$177 = A15$177;
            this.A16$151 = A16$151;
            Semigroup.$init$(this);
            Band.$init$(this);
            CommutativeSemigroup.$init$(this);
            Semilattice.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Semilattice catsKernelSemilatticeForTuple18$(final TupleSemilatticeInstances $this, final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17) {
      return $this.catsKernelSemilatticeForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   default Semilattice catsKernelSemilatticeForTuple18(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17) {
      return new Semilattice(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17) {
         private final Semilattice A0$568;
         private final Semilattice A1$542;
         private final Semilattice A2$516;
         private final Semilattice A3$490;
         private final Semilattice A4$464;
         private final Semilattice A5$438;
         private final Semilattice A6$412;
         private final Semilattice A7$386;
         private final Semilattice A8$360;
         private final Semilattice A9$334;
         private final Semilattice A10$308;
         private final Semilattice A11$282;
         private final Semilattice A12$256;
         private final Semilattice A13$230;
         private final Semilattice A14$204;
         private final Semilattice A15$178;
         private final Semilattice A16$152;
         private final Semilattice A17$126;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
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

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Tuple18 combine(final Tuple18 x, final Tuple18 y) {
            return new Tuple18(this.A0$568.combine(x._1(), y._1()), this.A1$542.combine(x._2(), y._2()), this.A2$516.combine(x._3(), y._3()), this.A3$490.combine(x._4(), y._4()), this.A4$464.combine(x._5(), y._5()), this.A5$438.combine(x._6(), y._6()), this.A6$412.combine(x._7(), y._7()), this.A7$386.combine(x._8(), y._8()), this.A8$360.combine(x._9(), y._9()), this.A9$334.combine(x._10(), y._10()), this.A10$308.combine(x._11(), y._11()), this.A11$282.combine(x._12(), y._12()), this.A12$256.combine(x._13(), y._13()), this.A13$230.combine(x._14(), y._14()), this.A14$204.combine(x._15(), y._15()), this.A15$178.combine(x._16(), y._16()), this.A16$152.combine(x._17(), y._17()), this.A17$126.combine(x._18(), y._18()));
         }

         public {
            this.A0$568 = A0$568;
            this.A1$542 = A1$542;
            this.A2$516 = A2$516;
            this.A3$490 = A3$490;
            this.A4$464 = A4$464;
            this.A5$438 = A5$438;
            this.A6$412 = A6$412;
            this.A7$386 = A7$386;
            this.A8$360 = A8$360;
            this.A9$334 = A9$334;
            this.A10$308 = A10$308;
            this.A11$282 = A11$282;
            this.A12$256 = A12$256;
            this.A13$230 = A13$230;
            this.A14$204 = A14$204;
            this.A15$178 = A15$178;
            this.A16$152 = A16$152;
            this.A17$126 = A17$126;
            Semigroup.$init$(this);
            Band.$init$(this);
            CommutativeSemigroup.$init$(this);
            Semilattice.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Semilattice catsKernelSemilatticeForTuple19$(final TupleSemilatticeInstances $this, final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17, final Semilattice A18) {
      return $this.catsKernelSemilatticeForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   default Semilattice catsKernelSemilatticeForTuple19(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17, final Semilattice A18) {
      return new Semilattice(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18) {
         private final Semilattice A0$569;
         private final Semilattice A1$543;
         private final Semilattice A2$517;
         private final Semilattice A3$491;
         private final Semilattice A4$465;
         private final Semilattice A5$439;
         private final Semilattice A6$413;
         private final Semilattice A7$387;
         private final Semilattice A8$361;
         private final Semilattice A9$335;
         private final Semilattice A10$309;
         private final Semilattice A11$283;
         private final Semilattice A12$257;
         private final Semilattice A13$231;
         private final Semilattice A14$205;
         private final Semilattice A15$179;
         private final Semilattice A16$153;
         private final Semilattice A17$127;
         private final Semilattice A18$101;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
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

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Tuple19 combine(final Tuple19 x, final Tuple19 y) {
            return new Tuple19(this.A0$569.combine(x._1(), y._1()), this.A1$543.combine(x._2(), y._2()), this.A2$517.combine(x._3(), y._3()), this.A3$491.combine(x._4(), y._4()), this.A4$465.combine(x._5(), y._5()), this.A5$439.combine(x._6(), y._6()), this.A6$413.combine(x._7(), y._7()), this.A7$387.combine(x._8(), y._8()), this.A8$361.combine(x._9(), y._9()), this.A9$335.combine(x._10(), y._10()), this.A10$309.combine(x._11(), y._11()), this.A11$283.combine(x._12(), y._12()), this.A12$257.combine(x._13(), y._13()), this.A13$231.combine(x._14(), y._14()), this.A14$205.combine(x._15(), y._15()), this.A15$179.combine(x._16(), y._16()), this.A16$153.combine(x._17(), y._17()), this.A17$127.combine(x._18(), y._18()), this.A18$101.combine(x._19(), y._19()));
         }

         public {
            this.A0$569 = A0$569;
            this.A1$543 = A1$543;
            this.A2$517 = A2$517;
            this.A3$491 = A3$491;
            this.A4$465 = A4$465;
            this.A5$439 = A5$439;
            this.A6$413 = A6$413;
            this.A7$387 = A7$387;
            this.A8$361 = A8$361;
            this.A9$335 = A9$335;
            this.A10$309 = A10$309;
            this.A11$283 = A11$283;
            this.A12$257 = A12$257;
            this.A13$231 = A13$231;
            this.A14$205 = A14$205;
            this.A15$179 = A15$179;
            this.A16$153 = A16$153;
            this.A17$127 = A17$127;
            this.A18$101 = A18$101;
            Semigroup.$init$(this);
            Band.$init$(this);
            CommutativeSemigroup.$init$(this);
            Semilattice.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Semilattice catsKernelSemilatticeForTuple20$(final TupleSemilatticeInstances $this, final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17, final Semilattice A18, final Semilattice A19) {
      return $this.catsKernelSemilatticeForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   default Semilattice catsKernelSemilatticeForTuple20(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17, final Semilattice A18, final Semilattice A19) {
      return new Semilattice(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19) {
         private final Semilattice A0$570;
         private final Semilattice A1$544;
         private final Semilattice A2$518;
         private final Semilattice A3$492;
         private final Semilattice A4$466;
         private final Semilattice A5$440;
         private final Semilattice A6$414;
         private final Semilattice A7$388;
         private final Semilattice A8$362;
         private final Semilattice A9$336;
         private final Semilattice A10$310;
         private final Semilattice A11$284;
         private final Semilattice A12$258;
         private final Semilattice A13$232;
         private final Semilattice A14$206;
         private final Semilattice A15$180;
         private final Semilattice A16$154;
         private final Semilattice A17$128;
         private final Semilattice A18$102;
         private final Semilattice A19$76;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
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

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Tuple20 combine(final Tuple20 x, final Tuple20 y) {
            return new Tuple20(this.A0$570.combine(x._1(), y._1()), this.A1$544.combine(x._2(), y._2()), this.A2$518.combine(x._3(), y._3()), this.A3$492.combine(x._4(), y._4()), this.A4$466.combine(x._5(), y._5()), this.A5$440.combine(x._6(), y._6()), this.A6$414.combine(x._7(), y._7()), this.A7$388.combine(x._8(), y._8()), this.A8$362.combine(x._9(), y._9()), this.A9$336.combine(x._10(), y._10()), this.A10$310.combine(x._11(), y._11()), this.A11$284.combine(x._12(), y._12()), this.A12$258.combine(x._13(), y._13()), this.A13$232.combine(x._14(), y._14()), this.A14$206.combine(x._15(), y._15()), this.A15$180.combine(x._16(), y._16()), this.A16$154.combine(x._17(), y._17()), this.A17$128.combine(x._18(), y._18()), this.A18$102.combine(x._19(), y._19()), this.A19$76.combine(x._20(), y._20()));
         }

         public {
            this.A0$570 = A0$570;
            this.A1$544 = A1$544;
            this.A2$518 = A2$518;
            this.A3$492 = A3$492;
            this.A4$466 = A4$466;
            this.A5$440 = A5$440;
            this.A6$414 = A6$414;
            this.A7$388 = A7$388;
            this.A8$362 = A8$362;
            this.A9$336 = A9$336;
            this.A10$310 = A10$310;
            this.A11$284 = A11$284;
            this.A12$258 = A12$258;
            this.A13$232 = A13$232;
            this.A14$206 = A14$206;
            this.A15$180 = A15$180;
            this.A16$154 = A16$154;
            this.A17$128 = A17$128;
            this.A18$102 = A18$102;
            this.A19$76 = A19$76;
            Semigroup.$init$(this);
            Band.$init$(this);
            CommutativeSemigroup.$init$(this);
            Semilattice.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Semilattice catsKernelSemilatticeForTuple21$(final TupleSemilatticeInstances $this, final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17, final Semilattice A18, final Semilattice A19, final Semilattice A20) {
      return $this.catsKernelSemilatticeForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   default Semilattice catsKernelSemilatticeForTuple21(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17, final Semilattice A18, final Semilattice A19, final Semilattice A20) {
      return new Semilattice(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20) {
         private final Semilattice A0$571;
         private final Semilattice A1$545;
         private final Semilattice A2$519;
         private final Semilattice A3$493;
         private final Semilattice A4$467;
         private final Semilattice A5$441;
         private final Semilattice A6$415;
         private final Semilattice A7$389;
         private final Semilattice A8$363;
         private final Semilattice A9$337;
         private final Semilattice A10$311;
         private final Semilattice A11$285;
         private final Semilattice A12$259;
         private final Semilattice A13$233;
         private final Semilattice A14$207;
         private final Semilattice A15$181;
         private final Semilattice A16$155;
         private final Semilattice A17$129;
         private final Semilattice A18$103;
         private final Semilattice A19$77;
         private final Semilattice A20$51;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
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

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Tuple21 combine(final Tuple21 x, final Tuple21 y) {
            return new Tuple21(this.A0$571.combine(x._1(), y._1()), this.A1$545.combine(x._2(), y._2()), this.A2$519.combine(x._3(), y._3()), this.A3$493.combine(x._4(), y._4()), this.A4$467.combine(x._5(), y._5()), this.A5$441.combine(x._6(), y._6()), this.A6$415.combine(x._7(), y._7()), this.A7$389.combine(x._8(), y._8()), this.A8$363.combine(x._9(), y._9()), this.A9$337.combine(x._10(), y._10()), this.A10$311.combine(x._11(), y._11()), this.A11$285.combine(x._12(), y._12()), this.A12$259.combine(x._13(), y._13()), this.A13$233.combine(x._14(), y._14()), this.A14$207.combine(x._15(), y._15()), this.A15$181.combine(x._16(), y._16()), this.A16$155.combine(x._17(), y._17()), this.A17$129.combine(x._18(), y._18()), this.A18$103.combine(x._19(), y._19()), this.A19$77.combine(x._20(), y._20()), this.A20$51.combine(x._21(), y._21()));
         }

         public {
            this.A0$571 = A0$571;
            this.A1$545 = A1$545;
            this.A2$519 = A2$519;
            this.A3$493 = A3$493;
            this.A4$467 = A4$467;
            this.A5$441 = A5$441;
            this.A6$415 = A6$415;
            this.A7$389 = A7$389;
            this.A8$363 = A8$363;
            this.A9$337 = A9$337;
            this.A10$311 = A10$311;
            this.A11$285 = A11$285;
            this.A12$259 = A12$259;
            this.A13$233 = A13$233;
            this.A14$207 = A14$207;
            this.A15$181 = A15$181;
            this.A16$155 = A16$155;
            this.A17$129 = A17$129;
            this.A18$103 = A18$103;
            this.A19$77 = A19$77;
            this.A20$51 = A20$51;
            Semigroup.$init$(this);
            Band.$init$(this);
            CommutativeSemigroup.$init$(this);
            Semilattice.$init$(this);
         }
      };
   }

   // $FF: synthetic method
   static Semilattice catsKernelSemilatticeForTuple22$(final TupleSemilatticeInstances $this, final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17, final Semilattice A18, final Semilattice A19, final Semilattice A20, final Semilattice A21) {
      return $this.catsKernelSemilatticeForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   default Semilattice catsKernelSemilatticeForTuple22(final Semilattice A0, final Semilattice A1, final Semilattice A2, final Semilattice A3, final Semilattice A4, final Semilattice A5, final Semilattice A6, final Semilattice A7, final Semilattice A8, final Semilattice A9, final Semilattice A10, final Semilattice A11, final Semilattice A12, final Semilattice A13, final Semilattice A14, final Semilattice A15, final Semilattice A16, final Semilattice A17, final Semilattice A18, final Semilattice A19, final Semilattice A20, final Semilattice A21) {
      return new Semilattice(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21) {
         private final Semilattice A0$572;
         private final Semilattice A1$546;
         private final Semilattice A2$520;
         private final Semilattice A3$494;
         private final Semilattice A4$468;
         private final Semilattice A5$442;
         private final Semilattice A6$416;
         private final Semilattice A7$390;
         private final Semilattice A8$364;
         private final Semilattice A9$338;
         private final Semilattice A10$312;
         private final Semilattice A11$286;
         private final Semilattice A12$260;
         private final Semilattice A13$234;
         private final Semilattice A14$208;
         private final Semilattice A15$182;
         private final Semilattice A16$156;
         private final Semilattice A17$130;
         private final Semilattice A18$104;
         private final Semilattice A19$78;
         private final Semilattice A20$52;
         private final Semilattice A21$26;

         public PartialOrder asMeetPartialOrder(final Eq ev) {
            return Semilattice.asMeetPartialOrder$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asMeetPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asMeetPartialOrder$mcJ$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder(final Eq ev) {
            return Semilattice.asJoinPartialOrder$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcD$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcD$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcF$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcF$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcI$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcI$sp$(this, ev);
         }

         public PartialOrder asJoinPartialOrder$mcJ$sp(final Eq ev) {
            return Semilattice.asJoinPartialOrder$mcJ$sp$(this, ev);
         }

         public CommutativeSemigroup reverse() {
            return CommutativeSemigroup.reverse$(this);
         }

         public CommutativeSemigroup reverse$mcD$sp() {
            return CommutativeSemigroup.reverse$mcD$sp$(this);
         }

         public CommutativeSemigroup reverse$mcF$sp() {
            return CommutativeSemigroup.reverse$mcF$sp$(this);
         }

         public CommutativeSemigroup reverse$mcI$sp() {
            return CommutativeSemigroup.reverse$mcI$sp$(this);
         }

         public CommutativeSemigroup reverse$mcJ$sp() {
            return CommutativeSemigroup.reverse$mcJ$sp$(this);
         }

         public CommutativeSemigroup intercalate(final Object middle) {
            return CommutativeSemigroup.intercalate$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
            return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
            return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
            return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
         }

         public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
            return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
         }

         public Object repeatedCombineN(final Object a, final int n) {
            return Band.repeatedCombineN$(this, a, n);
         }

         public double repeatedCombineN$mcD$sp(final double a, final int n) {
            return Band.repeatedCombineN$mcD$sp$(this, a, n);
         }

         public float repeatedCombineN$mcF$sp(final float a, final int n) {
            return Band.repeatedCombineN$mcF$sp$(this, a, n);
         }

         public int repeatedCombineN$mcI$sp(final int a, final int n) {
            return Band.repeatedCombineN$mcI$sp$(this, a, n);
         }

         public long repeatedCombineN$mcJ$sp(final long a, final int n) {
            return Band.repeatedCombineN$mcJ$sp$(this, a, n);
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

         public Object combineN(final Object a, final int n) {
            return Semigroup.combineN$(this, a, n);
         }

         public double combineN$mcD$sp(final double a, final int n) {
            return Semigroup.combineN$mcD$sp$(this, a, n);
         }

         public float combineN$mcF$sp(final float a, final int n) {
            return Semigroup.combineN$mcF$sp$(this, a, n);
         }

         public int combineN$mcI$sp(final int a, final int n) {
            return Semigroup.combineN$mcI$sp$(this, a, n);
         }

         public long combineN$mcJ$sp(final long a, final int n) {
            return Semigroup.combineN$mcJ$sp$(this, a, n);
         }

         public Option combineAllOption(final IterableOnce as) {
            return Semigroup.combineAllOption$(this, as);
         }

         public Tuple22 combine(final Tuple22 x, final Tuple22 y) {
            return new Tuple22(this.A0$572.combine(x._1(), y._1()), this.A1$546.combine(x._2(), y._2()), this.A2$520.combine(x._3(), y._3()), this.A3$494.combine(x._4(), y._4()), this.A4$468.combine(x._5(), y._5()), this.A5$442.combine(x._6(), y._6()), this.A6$416.combine(x._7(), y._7()), this.A7$390.combine(x._8(), y._8()), this.A8$364.combine(x._9(), y._9()), this.A9$338.combine(x._10(), y._10()), this.A10$312.combine(x._11(), y._11()), this.A11$286.combine(x._12(), y._12()), this.A12$260.combine(x._13(), y._13()), this.A13$234.combine(x._14(), y._14()), this.A14$208.combine(x._15(), y._15()), this.A15$182.combine(x._16(), y._16()), this.A16$156.combine(x._17(), y._17()), this.A17$130.combine(x._18(), y._18()), this.A18$104.combine(x._19(), y._19()), this.A19$78.combine(x._20(), y._20()), this.A20$52.combine(x._21(), y._21()), this.A21$26.combine(x._22(), y._22()));
         }

         public {
            this.A0$572 = A0$572;
            this.A1$546 = A1$546;
            this.A2$520 = A2$520;
            this.A3$494 = A3$494;
            this.A4$468 = A4$468;
            this.A5$442 = A5$442;
            this.A6$416 = A6$416;
            this.A7$390 = A7$390;
            this.A8$364 = A8$364;
            this.A9$338 = A9$338;
            this.A10$312 = A10$312;
            this.A11$286 = A11$286;
            this.A12$260 = A12$260;
            this.A13$234 = A13$234;
            this.A14$208 = A14$208;
            this.A15$182 = A15$182;
            this.A16$156 = A16$156;
            this.A17$130 = A17$130;
            this.A18$104 = A18$104;
            this.A19$78 = A19$78;
            this.A20$52 = A20$52;
            this.A21$26 = A21$26;
            Semigroup.$init$(this);
            Band.$init$(this);
            CommutativeSemigroup.$init$(this);
            Semilattice.$init$(this);
         }
      };
   }

   static void $init$(final TupleSemilatticeInstances $this) {
   }
}

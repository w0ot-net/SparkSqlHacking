package cats.kernel;

import java.io.Serializable;
import scala.Function1;
import scala.Function2;
import scala.math.Equiv;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011]baB\u0016-!\u0003\r\t!\r\u0005\u0006\u000b\u0002!\tA\u0012\u0005\u0006\u0015\u00021\ta\u0013\u0005\u0006?\u0002!\t\u0001Y\u0004\u0006G2B\t\u0001\u001a\u0004\u0006W1B\tA\u001a\u0005\b\u0003\u0007)A\u0011AA\u0003\u0011\u001d\t9!\u0002C\u0003\u0003\u0013Aq!a\b\u0006\t\u0003\t\t\u0003C\u0004\u0002F\u0015!\t!a\u0012\t\u000f\u0005mS\u0001\"\u0001\u0002^!9\u0011QN\u0003\u0005\u0002\u0005=\u0004bBAA\u000b\u0011\u0005\u00111\u0011\u0005\b\u0003\u001b+A\u0011AAH\u0011\u001d\tI*\u0002C\u0001\u00037Cq!a+\u0006\t\u0003\ti\u000bC\u0004\u0002>\u0016!\u0019!a0\t\u000f\u0005\rX\u0001b\u0001\u0002f\"9\u0011q`\u0003\u0005\u0004\t\u0005\u0001b\u0002B\u0013\u000b\u0011\r!q\u0005\u0005\b\u0005c)A1\u0001B\u001a\u0011\u001d\u0011i$\u0002C\u0002\u0005\u007fAqAa\u0014\u0006\t\u0007\u0011\t\u0006C\u0004\u0003b\u0015!\u0019Aa\u0019\t\u000f\tMT\u0001b\u0001\u0003v!9!QQ\u0003\u0005\u0004\t\u001d\u0005b\u0002BL\u000b\u0011\r!\u0011\u0014\u0005\b\u0005S+A1\u0001BV\u0011\u001d\u0011)-\u0002C\u0002\u0005\u000fDqAa6\u0006\t\u0007\u0011I\u000eC\u0004\u0003j\u0016!\u0019Aa;\t\u000f\tmX\u0001b\u0001\u0003~\"91QB\u0003\u0005\u0004\r=\u0001bBB\u0013\u000b\u0011\r1q\u0005\u0005\b\u0007o)A1AB\u001d\u0011\u001d\u0019I%\u0002C\u0002\u0007\u0017Bqa!\u0019\u0006\t\u0007\u0019\u0019\u0007C\u0004\u0004z\u0015!\u0019aa\u001f\t\u000f\rEU\u0001b\u0001\u0004\u0014\"91\u0011V\u0003\u0005\u0004\r-\u0006bBBa\u000b\u0011\r11\u0019\u0005\b\u00073,A1ABn\u0011%!)!BA\u0001\n\u0013!9A\u0001\u0002Fc*\u0011QFL\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003=\nAaY1ug\u000e\u0001QC\u0001\u001aT'\r\u00011'\u000f\t\u0003i]j\u0011!\u000e\u0006\u0002m\u0005)1oY1mC&\u0011\u0001(\u000e\u0002\u0004\u0003:L\bC\u0001\u001eC\u001d\tY\u0004I\u0004\u0002=\u007f5\tQH\u0003\u0002?a\u00051AH]8pizJ\u0011AN\u0005\u0003\u0003V\nq\u0001]1dW\u0006<W-\u0003\u0002D\t\na1+\u001a:jC2L'0\u00192mK*\u0011\u0011)N\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\u001d\u0003\"\u0001\u000e%\n\u0005%+$\u0001B+oSR\f1!Z9w)\rau*\u0018\t\u0003i5K!AT\u001b\u0003\u000f\t{w\u000e\\3b]\")\u0001K\u0001a\u0001#\u0006\t\u0001\u0010\u0005\u0002S'2\u0001A!\u0003+\u0001A\u0003\u0005\tQ1\u0001V\u0005\u0005\t\u0015C\u0001,4!\t!t+\u0003\u0002Yk\t9aj\u001c;iS:<\u0007FA*[!\t!4,\u0003\u0002]k\tY1\u000f]3dS\u0006d\u0017N_3e\u0011\u0015q&\u00011\u0001R\u0003\u0005I\u0018\u0001\u00028fcZ$2\u0001T1c\u0011\u0015\u00016\u00011\u0001R\u0011\u0015q6\u00011\u0001R\u0003\t)\u0015\u000f\u0005\u0002f\u000b5\tAfE\u0004\u0006O.t\u0017o\u001e>\u0011\u0007\u0015D'.\u0003\u0002jY\tYQ)\u001d$v]\u000e$\u0018n\u001c8t!\t)\u0007\u0001\u0005\u0002fY&\u0011Q\u000e\f\u0002\u0014\u000bF$v.R9vSZ\u001cuN\u001c<feNLwN\u001c\t\u0003K>L!\u0001\u001d\u0017\u0003EM\u001b\u0017\r\\1WKJ\u001c\u0018n\u001c8Ta\u0016\u001c\u0017NZ5d\u001fJ$WM]%ogR\fgnY3t!\t\u0011X/D\u0001t\u0015\t!H&A\u0005j]N$\u0018M\\2fg&\u0011ao\u001d\u0002\u0014)V\u0004H.Z(sI\u0016\u0014\u0018J\\:uC:\u001cWm\u001d\t\u0003KbL!!\u001f\u0017\u0003\u001f=\u0013H-\u001a:J]N$\u0018M\\2fgF\u00022a_A\u0001\u001b\u0005a(BA?\u007f\u0003\tIwNC\u0001\u0000\u0003\u0011Q\u0017M^1\n\u0005\rc\u0018A\u0002\u001fj]&$h\bF\u0001e\u0003\u0015\t\u0007\u000f\u001d7z+\u0011\tY!!\u0005\u0015\t\u00055\u00111\u0003\t\u0005K\u0002\ty\u0001E\u0002S\u0003#!Q\u0001V\u0004C\u0002UCq!!\u0006\b\u0001\b\ti!\u0001\u0002fm\"\u001aq!!\u0007\u0011\u0007Q\nY\"C\u0002\u0002\u001eU\u0012a!\u001b8mS:,\u0017A\u00012z+\u0019\t\u0019#a\u000b\u00026Q!\u0011QEA\u001e)\u0011\t9#a\f\u0011\t\u0015\u0004\u0011\u0011\u0006\t\u0004%\u0006-B!\u0003+\tA\u0003\u0005\tQ1\u0001VQ\r\tYC\u0017\u0005\b\u0003+A\u00019AA\u0019!\u0011)\u0007!a\r\u0011\u0007I\u000b)\u0004\u0002\u0006\u00028!\u0001\u000b\u0011!AC\u0002U\u0013\u0011A\u0011\u0015\u0004\u0003kQ\u0006bBA\u001f\u0011\u0001\u0007\u0011qH\u0001\u0002MB9A'!\u0011\u0002*\u0005M\u0012bAA\"k\tIa)\u001e8di&|g.M\u0001\u0004C:$W\u0003BA%\u0003\u001f\"b!a\u0013\u0002T\u0005]\u0003\u0003B3\u0001\u0003\u001b\u00022AUA(\t%!\u0016\u0002)A\u0001\u0002\u000b\u0007Q\u000bK\u0002\u0002PiCq!!\u0016\n\u0001\u0004\tY%A\u0002fcFBq!!\u0017\n\u0001\u0004\tY%A\u0002fcJ\n!a\u001c:\u0016\t\u0005}\u0013Q\r\u000b\u0007\u0003C\nI'a\u001b\u0011\t\u0015\u0004\u00111\r\t\u0004%\u0006\u0015D!\u0003+\u000bA\u0003\u0005\tQ1\u0001VQ\r\t)G\u0017\u0005\b\u0003+R\u0001\u0019AA1\u0011\u001d\tIF\u0003a\u0001\u0003C\n\u0001\"\u001b8ti\u0006t7-Z\u000b\u0005\u0003c\n9\b\u0006\u0003\u0002t\u0005e\u0004\u0003B3\u0001\u0003k\u00022AUA<\t\u0015!6B1\u0001V\u0011\u001d\tid\u0003a\u0001\u0003w\u0002\u0002\u0002NA?\u0003k\n)\bT\u0005\u0004\u0003\u007f*$!\u0003$v]\u000e$\u0018n\u001c83\u0003M1'o\\7V]&4XM]:bY\u0016\u000bX/\u00197t+\u0011\t))a#\u0016\u0005\u0005\u001d\u0005\u0003B3\u0001\u0003\u0013\u00032AUAF\t\u0015!FB1\u0001V\u0003!\tG\u000e\\#rk\u0006dW\u0003BAI\u0003/+\"!a%\u0011\t\u0015\u0004\u0011Q\u0013\t\u0004%\u0006]E!\u0002+\u000e\u0005\u0004)\u0016AG1mY\u0016\u000bX/\u00197C_VtG-\u001a3TK6LG.\u0019;uS\u000e,W\u0003BAO\u0003S+\"!a(\u0011\u000b\u0015\f\t+!*\n\u0007\u0005\rFF\u0001\nC_VtG-\u001a3TK6LG.\u0019;uS\u000e,\u0007\u0003B3\u0001\u0003O\u00032AUAU\t\u0015!fB1\u0001V\u0003M\tg._#rk\u0006d7+Z7jY\u0006$H/[2f+\u0011\ty+a/\u0016\u0005\u0005E\u0006#B3\u00024\u0006]\u0016bAA[Y\tY1+Z7jY\u0006$H/[2f!\u0011)\u0007!!/\u0011\u0007I\u000bY\fB\u0003U\u001f\t\u0007Q+\u0001\u000fdCR\u001c8*\u001a:oK2Len\u001d;b]\u000e,7OR8s\u0005&$8+\u001a;\u0016\u0005\u0005\u0005'CBAb\u0003\u000f\fiN\u0002\u0004\u0002F\u0016\u0001\u0011\u0011\u0019\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0006K\u0006%\u0017QZ\u0005\u0004\u0003\u0017d#\u0001\u0004)beRL\u0017\r\\(sI\u0016\u0014\b\u0003BAh\u00033l!!!5\u000b\t\u0005M\u0017Q[\u0001\nS6lW\u000f^1cY\u0016T1!a66\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u00037\f\tN\u0001\u0004CSR\u001cV\r\u001e\t\u0006K\u0006}\u0017QZ\u0005\u0004\u0003Cd#\u0001\u0002%bg\"\fAdY1ug.+'O\\3m!\u0006\u0014H/[1m\u001fJ$WM\u001d$peN+G/\u0006\u0003\u0002h\u0006uXCAAu!\u0015)\u0017\u0011ZAv!\u0019\ti/!>\u0002|:!\u0011q^Ay!\taT'C\u0002\u0002tV\na\u0001\u0015:fI\u00164\u0017\u0002BA|\u0003s\u00141aU3u\u0015\r\t\u00190\u000e\t\u0004%\u0006uH!\u0002+\u0012\u0005\u0004)\u0016\u0001G2biN\\UM\u001d8fY>\u0013H-\u001a:G_J,\u0015\u000e\u001e5feV1!1\u0001B\n\u0005/!bA!\u0002\u0003\u001a\t}\u0001#B3\u0003\b\t-\u0011b\u0001B\u0005Y\t)qJ\u001d3feB9!H!\u0004\u0003\u0012\tU\u0011b\u0001B\b\t\n1Q)\u001b;iKJ\u00042A\u0015B\n\t\u0015!&C1\u0001V!\r\u0011&q\u0003\u0003\u0007\u0003o\u0011\"\u0019A+\t\u0013\tm!#!AA\u0004\tu\u0011AC3wS\u0012,gnY3%cA)QMa\u0002\u0003\u0012!I!\u0011\u0005\n\u0002\u0002\u0003\u000f!1E\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004#B3\u0003\b\tU\u0011AG2biN\\UM\u001d8fY&s7\u000f^1oG\u0016\u001chi\u001c:V]&$XC\u0001B\u0015%\u0019\u0011YC!\f\u00030\u00191\u0011QY\u0003\u0001\u0005S\u0001B!\u001aB\u0004\u000fB!Q-a8H\u0003u\u0019\u0017\r^:LKJtW\r\\%ogR\fgnY3t\r>\u0014(i\\8mK\u0006tWC\u0001B\u001b%\u0019\u00119D!\u000f\u0003<\u00191\u0011QY\u0003\u0001\u0005k\u0001B!\u001aB\u0004\u0019B!Q-a8M\u0003i\u0019\u0017\r^:LKJtW\r\\%ogR\fgnY3t\r>\u0014()\u001f;f+\t\u0011\tE\u0005\u0004\u0003D\t\u0015#Q\n\u0004\u0007\u0003\u000b,\u0001A!\u0011\u0011\u000b\u0015\u00149Aa\u0012\u0011\u0007Q\u0012I%C\u0002\u0003LU\u0012AAQ=uKB)Q-a8\u0003H\u0005Y2-\u0019;t\u0017\u0016\u0014h.\u001a7J]N$\u0018M\\2fg\u001a{'o\u00155peR,\"Aa\u0015\u0013\r\tU#q\u000bB0\r\u0019\t)-\u0002\u0001\u0003TA)QMa\u0002\u0003ZA\u0019AGa\u0017\n\u0007\tuSGA\u0003TQ>\u0014H\u000fE\u0003f\u0003?\u0014I&A\rdCR\u001c8*\u001a:oK2Len\u001d;b]\u000e,7OR8s\u0013:$XC\u0001B3%\u0019\u00119G!\u001b\u0003r\u00191\u0011QY\u0003\u0001\u0005K\u0002R!\u001aB\u0004\u0005W\u00022\u0001\u000eB7\u0013\r\u0011y'\u000e\u0002\u0004\u0013:$\b#B3\u0002`\n-\u0014AG2biN\\UM\u001d8fY&s7\u000f^1oG\u0016\u001chi\u001c:M_:<WC\u0001B<%\u0019\u0011IHa\u001f\u0003\u0004\u001a1\u0011QY\u0003\u0001\u0005o\u0002R!\u001aB\u0004\u0005{\u00022\u0001\u000eB@\u0013\r\u0011\t)\u000e\u0002\u0005\u0019>tw\rE\u0003f\u0003?\u0014i(\u0001\u000fdCR\u001c8*\u001a:oK2Len\u001d;b]\u000e,7OR8s\u0005&<\u0017J\u001c;\u0016\u0005\t%%C\u0002BF\u0005\u001b\u0013)J\u0002\u0004\u0002F\u0016\u0001!\u0011\u0012\t\u0006K\n\u001d!q\u0012\t\u0004u\tE\u0015b\u0001BJ\t\n1!)[4J]R\u0004R!ZAp\u0005\u001f\u000b\u0001eY1ug.+'O\\3m\u0013:\u001cH/\u00198dKN4uN\u001d\"jO\u0012+7-[7bYV\u0011!1\u0014\n\u0007\u0005;\u0013yJa*\u0007\r\u0005\u0015W\u0001\u0001BN!\u0015)'q\u0001BQ!\rQ$1U\u0005\u0004\u0005K#%A\u0003\"jO\u0012+7-[7bYB)Q-a8\u0003\"\u0006q2-\u0019;t\u0017\u0016\u0014h.\u001a7J]N$\u0018M\\2fg\u001a{'\u000fR;sCRLwN\\\u000b\u0003\u0005[\u0013bAa,\u00032\n\rgABAc\u000b\u0001\u0011i\u000bE\u0003f\u0005\u000f\u0011\u0019\f\u0005\u0003\u00036\n}VB\u0001B\\\u0015\u0011\u0011ILa/\u0002\u0011\u0011,(/\u0019;j_:T1A!06\u0003)\u0019wN\\2veJ,g\u000e^\u0005\u0005\u0005\u0003\u00149L\u0001\u0005EkJ\fG/[8o!\u0015)\u0017q\u001cBZ\u0003\u0011\u001a\u0017\r^:LKJtW\r\\%ogR\fgnY3t\r>\u0014h)\u001b8ji\u0016$UO]1uS>tWC\u0001Be%\u0019\u0011YM!4\u0003V\u001a1\u0011QY\u0003\u0001\u0005\u0013\u0004R!\u001aB\u0004\u0005\u001f\u0004BA!.\u0003R&!!1\u001bB\\\u000591\u0015N\\5uK\u0012+(/\u0019;j_:\u0004R!ZAp\u0005\u001f\f!dY1ug.+'O\\3m\u0013:\u001cH/\u00198dKN4uN]\"iCJ,\"Aa7\u0013\r\tu'q\u001cBt\r\u0019\t)-\u0002\u0001\u0003\\B)QMa\u0002\u0003bB\u0019AGa9\n\u0007\t\u0015XG\u0001\u0003DQ\u0006\u0014\b#B3\u0002`\n\u0005\u0018\u0001H2biN\\UM\u001d8fY&s7\u000f^1oG\u0016\u001chi\u001c:Ts6\u0014w\u000e\\\u000b\u0003\u0005[\u0014bAa<\u0003r\nehABAc\u000b\u0001\u0011i\u000fE\u0003f\u0005\u000f\u0011\u0019\u0010E\u00025\u0005kL1Aa>6\u0005\u0019\u0019\u00160\u001c2pYB)Q-a8\u0003t\u0006a2-\u0019;t\u0017\u0016\u0014h.\u001a7J]N$\u0018M\\2fg\u001a{'o\u0015;sS:<WC\u0001B\u0000%\u0019\u0019\taa\u0001\u0004\f\u00191\u0011QY\u0003\u0001\u0005\u007f\u0004R!\u001aB\u0004\u0007\u000b\u0001B!!<\u0004\b%!1\u0011BA}\u0005\u0019\u0019FO]5oOB)Q-a8\u0004\u0006\u0005Q2-\u0019;t\u0017\u0016\u0014h.\u001a7J]N$\u0018M\\2fg\u001a{'/V+J\tV\u00111\u0011\u0003\n\u0007\u0007'\u0019)ba\t\u0007\r\u0005\u0015W\u0001AB\t!\u0015)'qAB\f!\u0011\u0019Iba\b\u000e\u0005\rm!bAB\u000f}\u0006!Q\u000f^5m\u0013\u0011\u0019\tca\u0007\u0003\tU+\u0016\n\u0012\t\u0006K\u0006}7qC\u0001\u001dG\u0006$8oS3s]\u0016d\u0017J\\:uC:\u001cWm\u001d$pe\u0012{WO\u00197f+\t\u0019IC\u0005\u0004\u0004,\r52Q\u0007\u0004\u0007\u0003\u000b,\u0001a!\u000b\u0011\u000b\u0015\u00149aa\f\u0011\u0007Q\u001a\t$C\u0002\u00044U\u0012a\u0001R8vE2,\u0007#B3\u0002`\u000e=\u0012aG2biN\\UM\u001d8fY&s7\u000f^1oG\u0016\u001chi\u001c:GY>\fG/\u0006\u0002\u0004<I11QHB \u0007\u000f2a!!2\u0006\u0001\rm\u0002#B3\u0003\b\r\u0005\u0003c\u0001\u001b\u0004D%\u00191QI\u001b\u0003\u000b\u0019cw.\u0019;\u0011\u000b\u0015\fyn!\u0011\u00021\r\fGo]&fe:,Gn\u0014:eKJ4uN](qi&|g.\u0006\u0003\u0004N\reC\u0003BB(\u00077\u0002R!\u001aB\u0004\u0007#\u0002R\u0001NB*\u0007/J1a!\u00166\u0005\u0019y\u0005\u000f^5p]B\u0019!k!\u0017\u0005\u000bQ\u001b#\u0019A+\t\u0013\ru3%!AA\u0004\r}\u0013AC3wS\u0012,gnY3%gA)QMa\u0002\u0004X\u000512-\u0019;t\u0017\u0016\u0014h.\u001a7Pe\u0012,'OR8s\u0019&\u001cH/\u0006\u0003\u0004f\rED\u0003BB4\u0007g\u0002R!\u001aB\u0004\u0007S\u0002RAOB6\u0007_J1a!\u001cE\u0005\u0011a\u0015n\u001d;\u0011\u0007I\u001b\t\bB\u0003UI\t\u0007Q\u000bC\u0005\u0004v\u0011\n\t\u0011q\u0001\u0004x\u0005QQM^5eK:\u001cW\r\n\u001b\u0011\u000b\u0015\u00149aa\u001c\u00021\r\fGo]&fe:,Gn\u0014:eKJ4uN\u001d,fGR|'/\u0006\u0003\u0004~\r%E\u0003BB@\u0007\u0017\u0003R!\u001aB\u0004\u0007\u0003\u0003RAOBB\u0007\u000fK1a!\"E\u0005\u00191Vm\u0019;peB\u0019!k!#\u0005\u000bQ+#\u0019A+\t\u0013\r5U%!AA\u0004\r=\u0015AC3wS\u0012,gnY3%kA)QMa\u0002\u0004\b\u000692-\u0019;t\u0017\u0016\u0014h.\u001a7Pe\u0012,'OR8s#V,W/Z\u000b\u0005\u0007+\u001b\t\u000b\u0006\u0003\u0004\u0018\u000e\r\u0006#B3\u0003\b\re\u0005CBAh\u00077\u001by*\u0003\u0003\u0004\u001e\u0006E'!B)vKV,\u0007c\u0001*\u0004\"\u0012)AK\nb\u0001+\"I1Q\u0015\u0014\u0002\u0002\u0003\u000f1qU\u0001\u000bKZLG-\u001a8dK\u00122\u0004#B3\u0003\b\r}\u0015aG2biN\\UM\u001d8fY>\u0013H-\u001a:G_J\u001cvN\u001d;fIN+G/\u0006\u0003\u0004.\u000eeF\u0003BBX\u0007w\u0003R!\u001aB\u0004\u0007c\u0003b!a4\u00044\u000e]\u0016\u0002BB[\u0003#\u0014\u0011bU8si\u0016$7+\u001a;\u0011\u0007I\u001bI\fB\u0003UO\t\u0007Q\u000bC\u0005\u0004>\u001e\n\t\u0011q\u0001\u0004@\u0006QQM^5eK:\u001cW\rJ\u001c\u0011\u000b\u0015\u00149aa.\u00027\r\fGo]&fe:,Gn\u0014:eKJ4uN\u001d$v]\u000e$\u0018n\u001c81+\u0011\u0019)m!5\u0015\t\r\u001d71\u001b\t\u0006K\n\u001d1\u0011\u001a\t\u0006i\r-7qZ\u0005\u0004\u0007\u001b,$!\u0003$v]\u000e$\u0018n\u001c81!\r\u00116\u0011\u001b\u0003\u0006)\"\u0012\r!\u0016\u0005\n\u0007+D\u0013\u0011!a\u0002\u0007/\f!\"\u001a<jI\u0016t7-\u001a\u00139!\u0015)'qABh\u0003=\u0019\u0017\r^:Ti\u0012,\u0015OR8s)JLXCBBo\u0007[$\t\u0001\u0006\u0004\u0004`\u000e=8Q\u001f\t\u0005K\u0002\u0019\t\u000f\u0005\u0004\u0004d\u000e\u001d81^\u0007\u0003\u0007KT1a!\b6\u0013\u0011\u0019Io!:\u0003\u0007Q\u0013\u0018\u0010E\u0002S\u0007[$Q\u0001V\u0015C\u0002UCqa!=*\u0001\b\u0019\u00190A\u0001B!\u0011)\u0007aa;\t\u000f\r]\u0018\u0006q\u0001\u0004z\u0006\tA\u000b\u0005\u0003f\u0001\rm\bc\u0001\u001e\u0004~&\u00191q #\u0003\u0013QC'o\\<bE2,GA\u0002C\u0002S\t\u0007QKA\u0001U\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t!I\u0001\u0005\u0003\u0005\f\u0011EQB\u0001C\u0007\u0015\r!yA`\u0001\u0005Y\u0006tw-\u0003\u0003\u0005\u0014\u00115!AB(cU\u0016\u001cG\u000fK\u0002\u0006\t/\u0001B\u0001\"\u0007\u000509!A1\u0004C\u0015\u001d\u0011!i\u0002\"\n\u000f\t\u0011}A1\u0005\b\u0004y\u0011\u0005\u0012\"A\u0018\n\u00055r\u0013b\u0001C\u0014Y\u000511m\\7qCRLA\u0001b\u000b\u0005.\u0005!2oY1mCZ+'o]5p]N\u0003XmY5gS\u000eT1\u0001b\n-\u0013\u0011!\t\u0004b\r\u0003eM,\b\u000f\u001d:fgN,f.^:fI&k\u0007o\u001c:u/\u0006\u0014h.\u001b8h\r>\u00148kY1mCZ+'o]5p]N\u0003XmY5gS\u000eTA\u0001b\u000b\u0005.!\u001aA\u0001b\u0006"
)
public interface Eq extends Serializable {
   static Eq catsStdEqForTry(final Eq A, final Eq T) {
      return Eq$.MODULE$.catsStdEqForTry(A, T);
   }

   static Order catsKernelOrderForFunction0(final Order evidence$8) {
      return Eq$.MODULE$.catsKernelOrderForFunction0(evidence$8);
   }

   static Order catsKernelOrderForSortedSet(final Order evidence$7) {
      return Eq$.MODULE$.catsKernelOrderForSortedSet(evidence$7);
   }

   static Order catsKernelOrderForQueue(final Order evidence$6) {
      return Eq$.MODULE$.catsKernelOrderForQueue(evidence$6);
   }

   static Order catsKernelOrderForVector(final Order evidence$5) {
      return Eq$.MODULE$.catsKernelOrderForVector(evidence$5);
   }

   static Order catsKernelOrderForList(final Order evidence$4) {
      return Eq$.MODULE$.catsKernelOrderForList(evidence$4);
   }

   static Order catsKernelOrderForOption(final Order evidence$3) {
      return Eq$.MODULE$.catsKernelOrderForOption(evidence$3);
   }

   static Order catsKernelInstancesForFloat() {
      return Eq$.MODULE$.catsKernelInstancesForFloat();
   }

   static Order catsKernelInstancesForDouble() {
      return Eq$.MODULE$.catsKernelInstancesForDouble();
   }

   static Order catsKernelInstancesForUUID() {
      return Eq$.MODULE$.catsKernelInstancesForUUID();
   }

   static Order catsKernelInstancesForString() {
      return Eq$.MODULE$.catsKernelInstancesForString();
   }

   static Order catsKernelInstancesForSymbol() {
      return Eq$.MODULE$.catsKernelInstancesForSymbol();
   }

   static Order catsKernelInstancesForChar() {
      return Eq$.MODULE$.catsKernelInstancesForChar();
   }

   static Order catsKernelInstancesForFiniteDuration() {
      return Eq$.MODULE$.catsKernelInstancesForFiniteDuration();
   }

   static Order catsKernelInstancesForDuration() {
      return Eq$.MODULE$.catsKernelInstancesForDuration();
   }

   static Order catsKernelInstancesForBigDecimal() {
      return Eq$.MODULE$.catsKernelInstancesForBigDecimal();
   }

   static Order catsKernelInstancesForBigInt() {
      return Eq$.MODULE$.catsKernelInstancesForBigInt();
   }

   static Order catsKernelInstancesForLong() {
      return Eq$.MODULE$.catsKernelInstancesForLong();
   }

   static Order catsKernelInstancesForInt() {
      return Eq$.MODULE$.catsKernelInstancesForInt();
   }

   static Order catsKernelInstancesForShort() {
      return Eq$.MODULE$.catsKernelInstancesForShort();
   }

   static Order catsKernelInstancesForByte() {
      return Eq$.MODULE$.catsKernelInstancesForByte();
   }

   static Order catsKernelInstancesForBoolean() {
      return Eq$.MODULE$.catsKernelInstancesForBoolean();
   }

   static Order catsKernelInstancesForUnit() {
      return Eq$.MODULE$.catsKernelInstancesForUnit();
   }

   static Order catsKernelOrderForEither(final Order evidence$1, final Order evidence$2) {
      return Eq$.MODULE$.catsKernelOrderForEither(evidence$1, evidence$2);
   }

   static PartialOrder catsKernelPartialOrderForSet() {
      return Eq$.MODULE$.catsKernelPartialOrderForSet();
   }

   static PartialOrder catsKernelInstancesForBitSet() {
      return Eq$.MODULE$.catsKernelInstancesForBitSet();
   }

   static Semilattice anyEqualSemilattice() {
      return Eq$.MODULE$.anyEqualSemilattice();
   }

   static BoundedSemilattice allEqualBoundedSemilattice() {
      return Eq$.MODULE$.allEqualBoundedSemilattice();
   }

   static Eq allEqual() {
      return Eq$.MODULE$.allEqual();
   }

   static Eq fromUniversalEquals() {
      return Eq$.MODULE$.fromUniversalEquals();
   }

   static Eq instance(final Function2 f) {
      return Eq$.MODULE$.instance(f);
   }

   static Eq or(final Eq eq1, final Eq eq2) {
      return Eq$.MODULE$.or(eq1, eq2);
   }

   static Eq and(final Eq eq1, final Eq eq2) {
      return Eq$.MODULE$.and(eq1, eq2);
   }

   static Eq by(final Function1 f, final Eq ev) {
      return Eq$.MODULE$.by(f, ev);
   }

   static Eq apply(final Eq ev) {
      return Eq$.MODULE$.apply(ev);
   }

   static Order catsKernelOrderForSortedMap(final Order evidence$10) {
      return Eq$.MODULE$.catsKernelOrderForSortedMap(evidence$10);
   }

   static Order catsKernelOrderForSeq(final Order evidence$9) {
      return Eq$.MODULE$.catsKernelOrderForSeq(evidence$9);
   }

   static PartialOrder catsKernelPartialOrderForFunction0(final PartialOrder evidence$15) {
      return Eq$.MODULE$.catsKernelPartialOrderForFunction0(evidence$15);
   }

   static PartialOrder catsKernelPartialOrderForQueue(final PartialOrder evidence$14) {
      return Eq$.MODULE$.catsKernelPartialOrderForQueue(evidence$14);
   }

   static PartialOrder catsKernelPartialOrderForVector(final PartialOrder evidence$13) {
      return Eq$.MODULE$.catsKernelPartialOrderForVector(evidence$13);
   }

   static PartialOrder catsKernelPartialOrderForList(final PartialOrder evidence$12) {
      return Eq$.MODULE$.catsKernelPartialOrderForList(evidence$12);
   }

   static PartialOrder catsKernelPartialOrderForOption(final PartialOrder evidence$11) {
      return Eq$.MODULE$.catsKernelPartialOrderForOption(evidence$11);
   }

   static PartialOrder catsKernelPartialOrderForSortedMap(final PartialOrder evidence$17) {
      return Eq$.MODULE$.catsKernelPartialOrderForSortedMap(evidence$17);
   }

   static PartialOrder catsKernelPartialOrderForSeq(final PartialOrder evidence$16) {
      return Eq$.MODULE$.catsKernelPartialOrderForSeq(evidence$16);
   }

   static Hash catsKernelHashForEither(final Hash evidence$28, final Hash evidence$29) {
      return Eq$.MODULE$.catsKernelHashForEither(evidence$28, evidence$29);
   }

   static Hash catsKernelHashForSortedMap(final Hash evidence$26, final Hash evidence$27) {
      return Eq$.MODULE$.catsKernelHashForSortedMap(evidence$26, evidence$27);
   }

   static Hash catsKernelHashForMap(final Hash evidence$24, final Hash evidence$25) {
      return Eq$.MODULE$.catsKernelHashForMap(evidence$24, evidence$25);
   }

   static Hash catsKernelHashForFunction0(final Hash evidence$23) {
      return Eq$.MODULE$.catsKernelHashForFunction0(evidence$23);
   }

   static Hash catsKernelHashForSortedSet(final Hash evidence$22) {
      return Eq$.MODULE$.catsKernelHashForSortedSet(evidence$22);
   }

   static Hash catsKernelHashForQueue(final Hash evidence$21) {
      return Eq$.MODULE$.catsKernelHashForQueue(evidence$21);
   }

   static Hash catsKernelHashForVector(final Hash evidence$20) {
      return Eq$.MODULE$.catsKernelHashForVector(evidence$20);
   }

   static Hash catsKernelHashForList(final Hash evidence$19) {
      return Eq$.MODULE$.catsKernelHashForList(evidence$19);
   }

   static Hash catsKernelHashForOption(final Hash evidence$18) {
      return Eq$.MODULE$.catsKernelHashForOption(evidence$18);
   }

   static Hash catsKernelHashForSet() {
      return Eq$.MODULE$.catsKernelHashForSet();
   }

   static Hash catsKernelHashForSeq(final Hash evidence$30) {
      return Eq$.MODULE$.catsKernelHashForSeq(evidence$30);
   }

   static Eq catsKernelEqForEither(final Eq evidence$38, final Eq evidence$39) {
      return Eq$.MODULE$.catsKernelEqForEither(evidence$38, evidence$39);
   }

   static Eq catsKernelEqForSortedMap(final Eq evidence$37) {
      return Eq$.MODULE$.catsKernelEqForSortedMap(evidence$37);
   }

   static Eq catsKernelEqForMap(final Eq evidence$36) {
      return Eq$.MODULE$.catsKernelEqForMap(evidence$36);
   }

   static Eq catsKernelEqForFunction0(final Eq evidence$35) {
      return Eq$.MODULE$.catsKernelEqForFunction0(evidence$35);
   }

   static Eq catsKernelEqForQueue(final Eq evidence$34) {
      return Eq$.MODULE$.catsKernelEqForQueue(evidence$34);
   }

   static Eq catsKernelEqForVector(final Eq evidence$33) {
      return Eq$.MODULE$.catsKernelEqForVector(evidence$33);
   }

   static Eq catsKernelEqForList(final Eq evidence$32) {
      return Eq$.MODULE$.catsKernelEqForList(evidence$32);
   }

   static Eq catsKernelEqForOption(final Eq evidence$31) {
      return Eq$.MODULE$.catsKernelEqForOption(evidence$31);
   }

   static Eq catsKernelEqForSeq(final Eq evidence$40) {
      return Eq$.MODULE$.catsKernelEqForSeq(evidence$40);
   }

   static Order catsKernelOrderForTuple22(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17, final Order A18, final Order A19, final Order A20, final Order A21) {
      return Eq$.MODULE$.catsKernelOrderForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   static Order catsKernelOrderForTuple21(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17, final Order A18, final Order A19, final Order A20) {
      return Eq$.MODULE$.catsKernelOrderForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   static Order catsKernelOrderForTuple20(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17, final Order A18, final Order A19) {
      return Eq$.MODULE$.catsKernelOrderForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   static Order catsKernelOrderForTuple19(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17, final Order A18) {
      return Eq$.MODULE$.catsKernelOrderForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   static Order catsKernelOrderForTuple18(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16, final Order A17) {
      return Eq$.MODULE$.catsKernelOrderForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   static Order catsKernelOrderForTuple17(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15, final Order A16) {
      return Eq$.MODULE$.catsKernelOrderForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   static Order catsKernelOrderForTuple16(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14, final Order A15) {
      return Eq$.MODULE$.catsKernelOrderForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   static Order catsKernelOrderForTuple15(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13, final Order A14) {
      return Eq$.MODULE$.catsKernelOrderForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   static Order catsKernelOrderForTuple14(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12, final Order A13) {
      return Eq$.MODULE$.catsKernelOrderForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   static Order catsKernelOrderForTuple13(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11, final Order A12) {
      return Eq$.MODULE$.catsKernelOrderForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   static Order catsKernelOrderForTuple12(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10, final Order A11) {
      return Eq$.MODULE$.catsKernelOrderForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   static Order catsKernelOrderForTuple11(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9, final Order A10) {
      return Eq$.MODULE$.catsKernelOrderForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   static Order catsKernelOrderForTuple10(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8, final Order A9) {
      return Eq$.MODULE$.catsKernelOrderForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   static Order catsKernelOrderForTuple9(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7, final Order A8) {
      return Eq$.MODULE$.catsKernelOrderForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   static Order catsKernelOrderForTuple8(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6, final Order A7) {
      return Eq$.MODULE$.catsKernelOrderForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   static Order catsKernelOrderForTuple7(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5, final Order A6) {
      return Eq$.MODULE$.catsKernelOrderForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   static Order catsKernelOrderForTuple6(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4, final Order A5) {
      return Eq$.MODULE$.catsKernelOrderForTuple6(A0, A1, A2, A3, A4, A5);
   }

   static Order catsKernelOrderForTuple5(final Order A0, final Order A1, final Order A2, final Order A3, final Order A4) {
      return Eq$.MODULE$.catsKernelOrderForTuple5(A0, A1, A2, A3, A4);
   }

   static Order catsKernelOrderForTuple4(final Order A0, final Order A1, final Order A2, final Order A3) {
      return Eq$.MODULE$.catsKernelOrderForTuple4(A0, A1, A2, A3);
   }

   static Order catsKernelOrderForTuple3(final Order A0, final Order A1, final Order A2) {
      return Eq$.MODULE$.catsKernelOrderForTuple3(A0, A1, A2);
   }

   static Order catsKernelOrderForTuple2(final Order A0, final Order A1) {
      return Eq$.MODULE$.catsKernelOrderForTuple2(A0, A1);
   }

   static Order catsKernelOrderForTuple1(final Order A0) {
      return Eq$.MODULE$.catsKernelOrderForTuple1(A0);
   }

   static PartialOrder catsKernelPartialOrderForTuple22(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15, final PartialOrder A16, final PartialOrder A17, final PartialOrder A18, final PartialOrder A19, final PartialOrder A20, final PartialOrder A21) {
      return Eq$.MODULE$.catsKernelPartialOrderForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   static PartialOrder catsKernelPartialOrderForTuple21(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15, final PartialOrder A16, final PartialOrder A17, final PartialOrder A18, final PartialOrder A19, final PartialOrder A20) {
      return Eq$.MODULE$.catsKernelPartialOrderForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   static PartialOrder catsKernelPartialOrderForTuple20(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15, final PartialOrder A16, final PartialOrder A17, final PartialOrder A18, final PartialOrder A19) {
      return Eq$.MODULE$.catsKernelPartialOrderForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   static PartialOrder catsKernelPartialOrderForTuple19(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15, final PartialOrder A16, final PartialOrder A17, final PartialOrder A18) {
      return Eq$.MODULE$.catsKernelPartialOrderForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   static PartialOrder catsKernelPartialOrderForTuple18(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15, final PartialOrder A16, final PartialOrder A17) {
      return Eq$.MODULE$.catsKernelPartialOrderForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   static PartialOrder catsKernelPartialOrderForTuple17(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15, final PartialOrder A16) {
      return Eq$.MODULE$.catsKernelPartialOrderForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   static PartialOrder catsKernelPartialOrderForTuple16(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14, final PartialOrder A15) {
      return Eq$.MODULE$.catsKernelPartialOrderForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   static PartialOrder catsKernelPartialOrderForTuple15(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13, final PartialOrder A14) {
      return Eq$.MODULE$.catsKernelPartialOrderForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   static PartialOrder catsKernelPartialOrderForTuple14(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12, final PartialOrder A13) {
      return Eq$.MODULE$.catsKernelPartialOrderForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   static PartialOrder catsKernelPartialOrderForTuple13(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11, final PartialOrder A12) {
      return Eq$.MODULE$.catsKernelPartialOrderForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   static PartialOrder catsKernelPartialOrderForTuple12(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10, final PartialOrder A11) {
      return Eq$.MODULE$.catsKernelPartialOrderForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   static PartialOrder catsKernelPartialOrderForTuple11(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9, final PartialOrder A10) {
      return Eq$.MODULE$.catsKernelPartialOrderForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   static PartialOrder catsKernelPartialOrderForTuple10(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8, final PartialOrder A9) {
      return Eq$.MODULE$.catsKernelPartialOrderForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   static PartialOrder catsKernelPartialOrderForTuple9(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7, final PartialOrder A8) {
      return Eq$.MODULE$.catsKernelPartialOrderForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   static PartialOrder catsKernelPartialOrderForTuple8(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6, final PartialOrder A7) {
      return Eq$.MODULE$.catsKernelPartialOrderForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   static PartialOrder catsKernelPartialOrderForTuple7(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5, final PartialOrder A6) {
      return Eq$.MODULE$.catsKernelPartialOrderForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   static PartialOrder catsKernelPartialOrderForTuple6(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4, final PartialOrder A5) {
      return Eq$.MODULE$.catsKernelPartialOrderForTuple6(A0, A1, A2, A3, A4, A5);
   }

   static PartialOrder catsKernelPartialOrderForTuple5(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3, final PartialOrder A4) {
      return Eq$.MODULE$.catsKernelPartialOrderForTuple5(A0, A1, A2, A3, A4);
   }

   static PartialOrder catsKernelPartialOrderForTuple4(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2, final PartialOrder A3) {
      return Eq$.MODULE$.catsKernelPartialOrderForTuple4(A0, A1, A2, A3);
   }

   static PartialOrder catsKernelPartialOrderForTuple3(final PartialOrder A0, final PartialOrder A1, final PartialOrder A2) {
      return Eq$.MODULE$.catsKernelPartialOrderForTuple3(A0, A1, A2);
   }

   static PartialOrder catsKernelPartialOrderForTuple2(final PartialOrder A0, final PartialOrder A1) {
      return Eq$.MODULE$.catsKernelPartialOrderForTuple2(A0, A1);
   }

   static PartialOrder catsKernelPartialOrderForTuple1(final PartialOrder A0) {
      return Eq$.MODULE$.catsKernelPartialOrderForTuple1(A0);
   }

   static Hash catsKernelHashForTuple22(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15, final Hash A16, final Hash A17, final Hash A18, final Hash A19, final Hash A20, final Hash A21) {
      return Eq$.MODULE$.catsKernelHashForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   static Hash catsKernelHashForTuple21(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15, final Hash A16, final Hash A17, final Hash A18, final Hash A19, final Hash A20) {
      return Eq$.MODULE$.catsKernelHashForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   static Hash catsKernelHashForTuple20(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15, final Hash A16, final Hash A17, final Hash A18, final Hash A19) {
      return Eq$.MODULE$.catsKernelHashForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   static Hash catsKernelHashForTuple19(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15, final Hash A16, final Hash A17, final Hash A18) {
      return Eq$.MODULE$.catsKernelHashForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   static Hash catsKernelHashForTuple18(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15, final Hash A16, final Hash A17) {
      return Eq$.MODULE$.catsKernelHashForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   static Hash catsKernelHashForTuple17(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15, final Hash A16) {
      return Eq$.MODULE$.catsKernelHashForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   static Hash catsKernelHashForTuple16(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14, final Hash A15) {
      return Eq$.MODULE$.catsKernelHashForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   static Hash catsKernelHashForTuple15(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13, final Hash A14) {
      return Eq$.MODULE$.catsKernelHashForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   static Hash catsKernelHashForTuple14(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12, final Hash A13) {
      return Eq$.MODULE$.catsKernelHashForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   static Hash catsKernelHashForTuple13(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11, final Hash A12) {
      return Eq$.MODULE$.catsKernelHashForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   static Hash catsKernelHashForTuple12(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10, final Hash A11) {
      return Eq$.MODULE$.catsKernelHashForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   static Hash catsKernelHashForTuple11(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9, final Hash A10) {
      return Eq$.MODULE$.catsKernelHashForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   static Hash catsKernelHashForTuple10(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8, final Hash A9) {
      return Eq$.MODULE$.catsKernelHashForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   static Hash catsKernelHashForTuple9(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7, final Hash A8) {
      return Eq$.MODULE$.catsKernelHashForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   static Hash catsKernelHashForTuple8(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6, final Hash A7) {
      return Eq$.MODULE$.catsKernelHashForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   static Hash catsKernelHashForTuple7(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5, final Hash A6) {
      return Eq$.MODULE$.catsKernelHashForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   static Hash catsKernelHashForTuple6(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4, final Hash A5) {
      return Eq$.MODULE$.catsKernelHashForTuple6(A0, A1, A2, A3, A4, A5);
   }

   static Hash catsKernelHashForTuple5(final Hash A0, final Hash A1, final Hash A2, final Hash A3, final Hash A4) {
      return Eq$.MODULE$.catsKernelHashForTuple5(A0, A1, A2, A3, A4);
   }

   static Hash catsKernelHashForTuple4(final Hash A0, final Hash A1, final Hash A2, final Hash A3) {
      return Eq$.MODULE$.catsKernelHashForTuple4(A0, A1, A2, A3);
   }

   static Hash catsKernelHashForTuple3(final Hash A0, final Hash A1, final Hash A2) {
      return Eq$.MODULE$.catsKernelHashForTuple3(A0, A1, A2);
   }

   static Hash catsKernelHashForTuple2(final Hash A0, final Hash A1) {
      return Eq$.MODULE$.catsKernelHashForTuple2(A0, A1);
   }

   static Hash catsKernelHashForTuple1(final Hash A0) {
      return Eq$.MODULE$.catsKernelHashForTuple1(A0);
   }

   static Eq catsKernelEqForTuple22(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15, final Eq A16, final Eq A17, final Eq A18, final Eq A19, final Eq A20, final Eq A21) {
      return Eq$.MODULE$.catsKernelEqForTuple22(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21);
   }

   static Eq catsKernelEqForTuple21(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15, final Eq A16, final Eq A17, final Eq A18, final Eq A19, final Eq A20) {
      return Eq$.MODULE$.catsKernelEqForTuple21(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20);
   }

   static Eq catsKernelEqForTuple20(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15, final Eq A16, final Eq A17, final Eq A18, final Eq A19) {
      return Eq$.MODULE$.catsKernelEqForTuple20(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19);
   }

   static Eq catsKernelEqForTuple19(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15, final Eq A16, final Eq A17, final Eq A18) {
      return Eq$.MODULE$.catsKernelEqForTuple19(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18);
   }

   static Eq catsKernelEqForTuple18(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15, final Eq A16, final Eq A17) {
      return Eq$.MODULE$.catsKernelEqForTuple18(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17);
   }

   static Eq catsKernelEqForTuple17(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15, final Eq A16) {
      return Eq$.MODULE$.catsKernelEqForTuple17(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16);
   }

   static Eq catsKernelEqForTuple16(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14, final Eq A15) {
      return Eq$.MODULE$.catsKernelEqForTuple16(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15);
   }

   static Eq catsKernelEqForTuple15(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13, final Eq A14) {
      return Eq$.MODULE$.catsKernelEqForTuple15(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14);
   }

   static Eq catsKernelEqForTuple14(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12, final Eq A13) {
      return Eq$.MODULE$.catsKernelEqForTuple14(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13);
   }

   static Eq catsKernelEqForTuple13(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11, final Eq A12) {
      return Eq$.MODULE$.catsKernelEqForTuple13(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12);
   }

   static Eq catsKernelEqForTuple12(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10, final Eq A11) {
      return Eq$.MODULE$.catsKernelEqForTuple12(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11);
   }

   static Eq catsKernelEqForTuple11(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9, final Eq A10) {
      return Eq$.MODULE$.catsKernelEqForTuple11(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10);
   }

   static Eq catsKernelEqForTuple10(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8, final Eq A9) {
      return Eq$.MODULE$.catsKernelEqForTuple10(A0, A1, A2, A3, A4, A5, A6, A7, A8, A9);
   }

   static Eq catsKernelEqForTuple9(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7, final Eq A8) {
      return Eq$.MODULE$.catsKernelEqForTuple9(A0, A1, A2, A3, A4, A5, A6, A7, A8);
   }

   static Eq catsKernelEqForTuple8(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6, final Eq A7) {
      return Eq$.MODULE$.catsKernelEqForTuple8(A0, A1, A2, A3, A4, A5, A6, A7);
   }

   static Eq catsKernelEqForTuple7(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5, final Eq A6) {
      return Eq$.MODULE$.catsKernelEqForTuple7(A0, A1, A2, A3, A4, A5, A6);
   }

   static Eq catsKernelEqForTuple6(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4, final Eq A5) {
      return Eq$.MODULE$.catsKernelEqForTuple6(A0, A1, A2, A3, A4, A5);
   }

   static Eq catsKernelEqForTuple5(final Eq A0, final Eq A1, final Eq A2, final Eq A3, final Eq A4) {
      return Eq$.MODULE$.catsKernelEqForTuple5(A0, A1, A2, A3, A4);
   }

   static Eq catsKernelEqForTuple4(final Eq A0, final Eq A1, final Eq A2, final Eq A3) {
      return Eq$.MODULE$.catsKernelEqForTuple4(A0, A1, A2, A3);
   }

   static Eq catsKernelEqForTuple3(final Eq A0, final Eq A1, final Eq A2) {
      return Eq$.MODULE$.catsKernelEqForTuple3(A0, A1, A2);
   }

   static Eq catsKernelEqForTuple2(final Eq A0, final Eq A1) {
      return Eq$.MODULE$.catsKernelEqForTuple2(A0, A1);
   }

   static Eq catsKernelEqForTuple1(final Eq A0) {
      return Eq$.MODULE$.catsKernelEqForTuple1(A0);
   }

   static Order catsKernelOrderForArraySeq(final Order evidence$3) {
      return Eq$.MODULE$.catsKernelOrderForArraySeq(evidence$3);
   }

   static Order catsKernelOrderForLazyList(final Order evidence$2) {
      return Eq$.MODULE$.catsKernelOrderForLazyList(evidence$2);
   }

   /** @deprecated */
   static Order catsKernelOrderForStream(final Order evidence$1) {
      return Eq$.MODULE$.catsKernelOrderForStream(evidence$1);
   }

   static PartialOrder catsKernelPartialOrderForArraySeq(final PartialOrder evidence$6) {
      return Eq$.MODULE$.catsKernelPartialOrderForArraySeq(evidence$6);
   }

   static PartialOrder catsKernelPartialOrderForLazyList(final PartialOrder evidence$5) {
      return Eq$.MODULE$.catsKernelPartialOrderForLazyList(evidence$5);
   }

   /** @deprecated */
   static PartialOrder catsKernelPartialOrderForStream(final PartialOrder evidence$4) {
      return Eq$.MODULE$.catsKernelPartialOrderForStream(evidence$4);
   }

   static Hash catsKernelHashForArraySeq(final Hash evidence$9) {
      return Eq$.MODULE$.catsKernelHashForArraySeq(evidence$9);
   }

   static Hash catsKernelHashForLazyList(final Hash evidence$8) {
      return Eq$.MODULE$.catsKernelHashForLazyList(evidence$8);
   }

   /** @deprecated */
   static Hash catsKernelHashForStream(final Hash evidence$7) {
      return Eq$.MODULE$.catsKernelHashForStream(evidence$7);
   }

   static Eq catsKernelEqForArraySeq(final Eq evidence$12) {
      return Eq$.MODULE$.catsKernelEqForArraySeq(evidence$12);
   }

   static Eq catsKernelEqForLazyList(final Eq evidence$11) {
      return Eq$.MODULE$.catsKernelEqForLazyList(evidence$11);
   }

   /** @deprecated */
   static Eq catsKernelEqForStream(final Eq evidence$10) {
      return Eq$.MODULE$.catsKernelEqForStream(evidence$10);
   }

   static Equiv catsKernelEquivForEq(final Eq ev) {
      return Eq$.MODULE$.catsKernelEquivForEq(ev);
   }

   boolean eqv(final Object x, final Object y);

   // $FF: synthetic method
   static boolean neqv$(final Eq $this, final Object x, final Object y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final Object x, final Object y) {
      return !this.eqv(x, y);
   }

   // $FF: synthetic method
   static boolean eqv$mcZ$sp$(final Eq $this, final boolean x, final boolean y) {
      return $this.eqv$mcZ$sp(x, y);
   }

   default boolean eqv$mcZ$sp(final boolean x, final boolean y) {
      return this.eqv(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y));
   }

   // $FF: synthetic method
   static boolean eqv$mcB$sp$(final Eq $this, final byte x, final byte y) {
      return $this.eqv$mcB$sp(x, y);
   }

   default boolean eqv$mcB$sp(final byte x, final byte y) {
      return this.eqv(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y));
   }

   // $FF: synthetic method
   static boolean eqv$mcC$sp$(final Eq $this, final char x, final char y) {
      return $this.eqv$mcC$sp(x, y);
   }

   default boolean eqv$mcC$sp(final char x, final char y) {
      return this.eqv(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y));
   }

   // $FF: synthetic method
   static boolean eqv$mcD$sp$(final Eq $this, final double x, final double y) {
      return $this.eqv$mcD$sp(x, y);
   }

   default boolean eqv$mcD$sp(final double x, final double y) {
      return this.eqv(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y));
   }

   // $FF: synthetic method
   static boolean eqv$mcF$sp$(final Eq $this, final float x, final float y) {
      return $this.eqv$mcF$sp(x, y);
   }

   default boolean eqv$mcF$sp(final float x, final float y) {
      return this.eqv(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y));
   }

   // $FF: synthetic method
   static boolean eqv$mcI$sp$(final Eq $this, final int x, final int y) {
      return $this.eqv$mcI$sp(x, y);
   }

   default boolean eqv$mcI$sp(final int x, final int y) {
      return this.eqv(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y));
   }

   // $FF: synthetic method
   static boolean eqv$mcJ$sp$(final Eq $this, final long x, final long y) {
      return $this.eqv$mcJ$sp(x, y);
   }

   default boolean eqv$mcJ$sp(final long x, final long y) {
      return this.eqv(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y));
   }

   // $FF: synthetic method
   static boolean eqv$mcS$sp$(final Eq $this, final short x, final short y) {
      return $this.eqv$mcS$sp(x, y);
   }

   default boolean eqv$mcS$sp(final short x, final short y) {
      return this.eqv(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y));
   }

   // $FF: synthetic method
   static boolean eqv$mcV$sp$(final Eq $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.eqv$mcV$sp(x, y);
   }

   default boolean eqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.eqv(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$mcZ$sp$(final Eq $this, final boolean x, final boolean y) {
      return $this.neqv$mcZ$sp(x, y);
   }

   default boolean neqv$mcZ$sp(final boolean x, final boolean y) {
      return this.neqv(BoxesRunTime.boxToBoolean(x), BoxesRunTime.boxToBoolean(y));
   }

   // $FF: synthetic method
   static boolean neqv$mcB$sp$(final Eq $this, final byte x, final byte y) {
      return $this.neqv$mcB$sp(x, y);
   }

   default boolean neqv$mcB$sp(final byte x, final byte y) {
      return this.neqv(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y));
   }

   // $FF: synthetic method
   static boolean neqv$mcC$sp$(final Eq $this, final char x, final char y) {
      return $this.neqv$mcC$sp(x, y);
   }

   default boolean neqv$mcC$sp(final char x, final char y) {
      return this.neqv(BoxesRunTime.boxToCharacter(x), BoxesRunTime.boxToCharacter(y));
   }

   // $FF: synthetic method
   static boolean neqv$mcD$sp$(final Eq $this, final double x, final double y) {
      return $this.neqv$mcD$sp(x, y);
   }

   default boolean neqv$mcD$sp(final double x, final double y) {
      return this.neqv(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y));
   }

   // $FF: synthetic method
   static boolean neqv$mcF$sp$(final Eq $this, final float x, final float y) {
      return $this.neqv$mcF$sp(x, y);
   }

   default boolean neqv$mcF$sp(final float x, final float y) {
      return this.neqv(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y));
   }

   // $FF: synthetic method
   static boolean neqv$mcI$sp$(final Eq $this, final int x, final int y) {
      return $this.neqv$mcI$sp(x, y);
   }

   default boolean neqv$mcI$sp(final int x, final int y) {
      return this.neqv(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y));
   }

   // $FF: synthetic method
   static boolean neqv$mcJ$sp$(final Eq $this, final long x, final long y) {
      return $this.neqv$mcJ$sp(x, y);
   }

   default boolean neqv$mcJ$sp(final long x, final long y) {
      return this.neqv(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y));
   }

   // $FF: synthetic method
   static boolean neqv$mcS$sp$(final Eq $this, final short x, final short y) {
      return $this.neqv$mcS$sp(x, y);
   }

   default boolean neqv$mcS$sp(final short x, final short y) {
      return this.neqv(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y));
   }

   // $FF: synthetic method
   static boolean neqv$mcV$sp$(final Eq $this, final BoxedUnit x, final BoxedUnit y) {
      return $this.neqv$mcV$sp(x, y);
   }

   default boolean neqv$mcV$sp(final BoxedUnit x, final BoxedUnit y) {
      return this.neqv(x, y);
   }

   static void $init$(final Eq $this) {
   }
}

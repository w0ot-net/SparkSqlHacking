package org.apache.spark.graphx;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Predef.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\rmh\u0001\u0002\u0015*\u0001JB\u0001\"\u0013\u0001\u0003\u0012\u0004%\tA\u0013\u0005\t%\u0002\u0011\t\u0019!C\u0001'\"A\u0011\f\u0001B\tB\u0003&1\n\u0003\u0005[\u0001\tE\r\u0011\"\u0001K\u0011!Y\u0006A!a\u0001\n\u0003a\u0006\u0002\u00030\u0001\u0005#\u0005\u000b\u0015B&\t\u0011}\u0003!\u00113A\u0005\u0002\u0001D!\"a\n\u0001\u0005\u0003\u0007I\u0011AA\u0015\u0011%\ti\u0003\u0001B\tB\u0003&\u0011\rC\u0004\u00020\u0001!\t!!\r\t\u000f\u0005m\u0002\u0001\"\u0001\u0002>!9\u00111\t\u0001\u0005\u0002\u0005\u0015\u0003\"CA(\u0001\u0005\u0005I\u0011AA)\u0011%\ty\bAI\u0001\n\u0003\t\t\tC\u0005\u0002:\u0002\t\n\u0011\"\u0001\u0002<\"I\u0011Q\u001c\u0001\u0012\u0002\u0013\u0005\u0011q\u001c\u0005\n\u0005\u000b\u0001\u0011\u0011!C!\u0005\u000fA\u0011B!\u0007\u0001\u0003\u0003%\tAa\u0007\t\u0013\t\r\u0002!!A\u0005\u0002\t\u0015\u0002\"\u0003B\u0015\u0001\u0005\u0005I\u0011\tB\u0016\u0011%\u0011I\u0004AA\u0001\n\u0003\u0011Y\u0004C\u0005\u0003F\u0001\t\t\u0011\"\u0011\u0003H!I!1\n\u0001\u0002\u0002\u0013\u0005#Q\n\u0005\n\u0005\u001f\u0002\u0011\u0011!C!\u0005#B\u0011Ba\u0015\u0001\u0003\u0003%\tE!\u0016\b\u000f\te\u0013\u0006#\u0001\u0003\\\u00191\u0001&\u000bE\u0001\u0005;Bq!a\f\u001c\t\u0003\u0011I\u0007\u0003\u0005\u0003lm!\t!\u000bB7\u0011!\u0011Ii\u0007C\u0001S\t-\u0005\"\u0003BU7\u0005\u0005I\u0011\u0011BV\u0011%\u0011InGI\u0001\n\u0003\u0011Y\u000eC\u0005\u0003~n\t\n\u0011\"\u0001\u0003\u0000\"I1\u0011E\u000e\u0012\u0002\u0013\u000511\u0005\u0005\n\u0007\u0017Z\u0012\u0011!CA\u0007\u001bB\u0011b!\"\u001c#\u0003%\taa\"\t\u0013\r%6$%A\u0005\u0002\r-\u0006\"CBg7E\u0005I\u0011ABh\u0011%\u00199pGA\u0001\n\u0013\u0019IP\u0001\u0003FI\u001e,'B\u0001\u0016,\u0003\u00199'/\u00199iq*\u0011A&L\u0001\u0006gB\f'o\u001b\u0006\u0003]=\na!\u00199bG\",'\"\u0001\u0019\u0002\u0007=\u0014xm\u0001\u0001\u0016\u0005M\u001a7\u0003\u0002\u00015u\u0019\u0003\"!\u000e\u001d\u000e\u0003YR\u0011aN\u0001\u0006g\u000e\fG.Y\u0005\u0003sY\u0012a!\u00118z%\u00164\u0007CA\u001eD\u001d\ta\u0014I\u0004\u0002>\u00016\taH\u0003\u0002@c\u00051AH]8pizJ\u0011aN\u0005\u0003\u0005Z\nq\u0001]1dW\u0006<W-\u0003\u0002E\u000b\na1+\u001a:jC2L'0\u00192mK*\u0011!I\u000e\t\u0003k\u001dK!\u0001\u0013\u001c\u0003\u000fA\u0013x\u000eZ;di\u0006)1O]2JIV\t1\n\u0005\u0002M\u001f:\u0011QJT\u0007\u0002S%\u0011!)K\u0005\u0003!F\u0013\u0001BV3si\u0016D\u0018\n\u001a\u0006\u0003\u0005&\n\u0011b\u001d:d\u0013\u0012|F%Z9\u0015\u0005Q;\u0006CA\u001bV\u0013\t1fG\u0001\u0003V]&$\bb\u0002-\u0003\u0003\u0003\u0005\raS\u0001\u0004q\u0012\n\u0014AB:sG&#\u0007%A\u0003egRLE-A\u0005egRLEm\u0018\u0013fcR\u0011A+\u0018\u0005\b1\u0016\t\t\u00111\u0001L\u0003\u0019!7\u000f^%eA\u0005!\u0011\r\u001e;s+\u0005\t\u0007C\u00012d\u0019\u0001!\u0011\u0002\u001a\u0001!\u0002\u0003\u0005)\u0019A3\u0003\u0005\u0015#\u0015C\u00014j!\t)t-\u0003\u0002im\t9aj\u001c;iS:<\u0007CA\u001bk\u0013\tYgGA\u0002B]fDCbY7qkj|\u0018\u0011BA\n\u0003;\u0001\"!\u000e8\n\u0005=4$aC:qK\u000eL\u0017\r\\5{K\u0012\fTaI9siNt!!\u000e:\n\u0005M4\u0014\u0001B\"iCJ\fD\u0001\n\u001fAoE*1E^<zq:\u0011Qg^\u0005\u0003qZ\n1!\u00138uc\u0011!C\bQ\u001c2\u000b\rZHP`?\u000f\u0005Ub\u0018BA?7\u0003\u001d\u0011un\u001c7fC:\fD\u0001\n\u001fAoEJ1%!\u0001\u0002\u0004\u0005\u001d\u0011Q\u0001\b\u0004k\u0005\r\u0011bAA\u0003m\u0005!!)\u001f;fc\u0011!C\bQ\u001c2\u0013\r\nY!!\u0004\u0002\u0012\u0005=abA\u001b\u0002\u000e%\u0019\u0011q\u0002\u001c\u0002\t1{gnZ\u0019\u0005Iq\u0002u'M\u0005$\u0003+\t9\"a\u0007\u0002\u001a9\u0019Q'a\u0006\n\u0007\u0005ea'A\u0003GY>\fG/\r\u0003%y\u0001;\u0014'C\u0012\u0002 \u0005\u0005\u0012QEA\u0012\u001d\r)\u0014\u0011E\u0005\u0004\u0003G1\u0014A\u0002#pk\ndW-\r\u0003%y\u0001;\u0014\u0001C1uiJ|F%Z9\u0015\u0007Q\u000bY\u0003C\u0004Y\u0011\u0005\u0005\t\u0019A1\u0002\u000b\u0005$HO\u001d\u0011\u0002\rqJg.\u001b;?)!\t\u0019$!\u000e\u00028\u0005e\u0002cA'\u0001C\"9\u0011J\u0003I\u0001\u0002\u0004Y\u0005b\u0002.\u000b!\u0003\u0005\ra\u0013\u0005\b?*\u0001\n\u00111\u0001b\u00035yG\u000f[3s-\u0016\u0014H/\u001a=JIR\u00191*a\u0010\t\r\u0005\u00053\u00021\u0001L\u0003\r1\u0018\u000eZ\u0001\u0012e\u0016d\u0017\r^5wK\u0012K'/Z2uS>tG\u0003BA$\u0003\u001b\u00022!TA%\u0013\r\tY%\u000b\u0002\u000e\u000b\u0012<W\rR5sK\u000e$\u0018n\u001c8\t\r\u0005\u0005C\u00021\u0001L\u0003\u0011\u0019w\u000e]=\u0016\t\u0005M\u0013\u0011\f\u000b\t\u0003+\nI(a\u001f\u0002~A!Q\nAA,!\r\u0011\u0017\u0011\f\u0003\nI6\u0001\u000b\u0011!AC\u0002\u0015D\u0013#!\u0017n\u0003;\n\t'!\u001a\u0002j\u00055\u0014\u0011OA;c\u0019\u0019\u0013O]A0gF\"A\u0005\u0010!8c\u0019\u0019co^A2qF\"A\u0005\u0010!8c\u0019\u00193\u0010`A4{F\"A\u0005\u0010!8c%\u0019\u0013\u0011AA\u0002\u0003W\n)!\r\u0003%y\u0001;\u0014'C\u0012\u0002\f\u00055\u0011qNA\bc\u0011!C\bQ\u001c2\u0013\r\n)\"a\u0006\u0002t\u0005e\u0011\u0007\u0002\u0013=\u0001^\n\u0014bIA\u0010\u0003C\t9(a\t2\t\u0011b\u0004i\u000e\u0005\b\u00136\u0001\n\u00111\u0001L\u0011\u001dQV\u0002%AA\u0002-C\u0001bX\u0007\u0011\u0002\u0003\u0007\u0011qK\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0011\t\u0019)!'\u0016\u0005\u0005\u0015%fA&\u0002\b.\u0012\u0011\u0011\u0012\t\u0005\u0003\u0017\u000b)*\u0004\u0002\u0002\u000e*!\u0011qRAI\u0003%)hn\u00195fG.,GMC\u0002\u0002\u0014Z\n!\"\u00198o_R\fG/[8o\u0013\u0011\t9*!$\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rB\u0005e\u001d\u0001\u0006\t\u0011!b\u0001K\"\n\u0012\u0011T7\u0002\u001e\u0006\u0005\u0016QUAU\u0003[\u000b\t,!.2\r\r\n(/a(tc\u0011!C\bQ\u001c2\r\r2x/a)yc\u0011!C\bQ\u001c2\r\rZH0a*~c\u0011!C\bQ\u001c2\u0013\r\n\t!a\u0001\u0002,\u0006\u0015\u0011\u0007\u0002\u0013=\u0001^\n\u0014bIA\u0006\u0003\u001b\ty+a\u00042\t\u0011b\u0004iN\u0019\nG\u0005U\u0011qCAZ\u00033\tD\u0001\n\u001fAoEJ1%a\b\u0002\"\u0005]\u00161E\u0019\u0005Iq\u0002u'\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\t\u0005\r\u0015Q\u0018\u0003\nI>\u0001\u000b\u0011!AC\u0002\u0015D\u0013#!0n\u0003\u0003\f)-!3\u0002N\u0006E\u0017Q[Amc\u0019\u0019\u0013O]AbgF\"A\u0005\u0010!8c\u0019\u0019co^AdqF\"A\u0005\u0010!8c\u0019\u00193\u0010`Af{F\"A\u0005\u0010!8c%\u0019\u0013\u0011AA\u0002\u0003\u001f\f)!\r\u0003%y\u0001;\u0014'C\u0012\u0002\f\u00055\u00111[A\bc\u0011!C\bQ\u001c2\u0013\r\n)\"a\u0006\u0002X\u0006e\u0011\u0007\u0002\u0013=\u0001^\n\u0014bIA\u0010\u0003C\tY.a\t2\t\u0011b\u0004iN\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+\u0011\t\t/!:\u0016\u0005\u0005\r(fA1\u0002\b\u0012IA\r\u0005Q\u0001\u0002\u0003\u0015\r!\u001a\u0015\u0012\u0003Kl\u0017\u0011^Aw\u0003c\f)0!?\u0002~\n\u0005\u0011GB\u0012re\u0006-8/\r\u0003%y\u0001;\u0014GB\u0012wo\u0006=\b0\r\u0003%y\u0001;\u0014GB\u0012|y\u0006MX0\r\u0003%y\u0001;\u0014'C\u0012\u0002\u0002\u0005\r\u0011q_A\u0003c\u0011!C\bQ\u001c2\u0013\r\nY!!\u0004\u0002|\u0006=\u0011\u0007\u0002\u0013=\u0001^\n\u0014bIA\u000b\u0003/\ty0!\u00072\t\u0011b\u0004iN\u0019\nG\u0005}\u0011\u0011\u0005B\u0002\u0003G\tD\u0001\n\u001fAo\u0005i\u0001O]8ek\u000e$\bK]3gSb,\"A!\u0003\u0011\t\t-!QC\u0007\u0003\u0005\u001bQAAa\u0004\u0003\u0012\u0005!A.\u00198h\u0015\t\u0011\u0019\"\u0001\u0003kCZ\f\u0017\u0002\u0002B\f\u0005\u001b\u0011aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLXC\u0001B\u000f!\r)$qD\u0005\u0004\u0005C1$aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HcA5\u0003(!A\u0001lEA\u0001\u0002\u0004\u0011i\"A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\u0011i\u0003E\u0003\u00030\tU\u0012.\u0004\u0002\u00032)\u0019!1\u0007\u001c\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u00038\tE\"\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$BA!\u0010\u0003DA\u0019QGa\u0010\n\u0007\t\u0005cGA\u0004C_>dW-\u00198\t\u000fa+\u0012\u0011!a\u0001S\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\u0011IA!\u0013\t\u0011a3\u0012\u0011!a\u0001\u0005;\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0005;\t\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0005\u0013\ta!Z9vC2\u001cH\u0003\u0002B\u001f\u0005/Bq\u0001W\r\u0002\u0002\u0003\u0007\u0011.\u0001\u0003FI\u001e,\u0007CA'\u001c'\u0011YBGa\u0018\u0011\t\t\u0005$qM\u0007\u0003\u0005GRAA!\u001a\u0003\u0012\u0005\u0011\u0011n\\\u0005\u0004\t\n\rDC\u0001B.\u0003UaW\r_5d_\u001e\u0014\u0018\r\u001d5jG>\u0013H-\u001a:j]\u001e,BAa\u001c\u0003\bV\u0011!\u0011\u000f\n\u0007\u0005g\u00129H! \u0007\r\tUT\u0004\u0001B9\u00051a$/\u001a4j]\u0016lWM\u001c;?!\u0011\u0011YA!\u001f\n\t\tm$Q\u0002\u0002\u0007\u001f\nTWm\u0019;\u0011\u000bm\u0012yHa!\n\u0007\t\u0005UI\u0001\u0005Pe\u0012,'/\u001b8h!\u0011i\u0005A!\"\u0011\u0007\t\u00149\tB\u0003e;\t\u0007Q-A\ffI\u001e,\u0017I\u001d:bsN{'\u000f\u001e#bi\u00064uN]7biV!!Q\u0012BQ+\t\u0011y\t\u0005\u0005\u0003\u0012\ne%Q\u0014BR\u001b\t\u0011\u0019J\u0003\u0003\u00034\tU%b\u0001BLW\u0005!Q\u000f^5m\u0013\u0011\u0011YJa%\u0003\u001dM{'\u000f\u001e#bi\u00064uN]7biB!Q\n\u0001BP!\r\u0011'\u0011\u0015\u0003\u0006Iz\u0011\r!\u001a\t\u0006k\t\u0015&QT\u0005\u0004\u0005O3$!B!se\u0006L\u0018!B1qa2LX\u0003\u0002BW\u0005g#\u0002Ba,\u0003T\nU'q\u001b\t\u0005\u001b\u0002\u0011\t\fE\u0002c\u0005g#\u0011\u0002Z\u0010!\u0002\u0003\u0005)\u0019A3)#\tMVNa.\u0003<\n}&1\u0019Bd\u0005\u0017\u0014y-\r\u0004$cJ\u0014Il]\u0019\u0005Iq\u0002u'\r\u0004$m^\u0014i\f_\u0019\u0005Iq\u0002u'\r\u0004$wr\u0014\t-`\u0019\u0005Iq\u0002u'M\u0005$\u0003\u0003\t\u0019A!2\u0002\u0006E\"A\u0005\u0010!8c%\u0019\u00131BA\u0007\u0005\u0013\fy!\r\u0003%y\u0001;\u0014'C\u0012\u0002\u0016\u0005]!QZA\rc\u0011!C\bQ\u001c2\u0013\r\ny\"!\t\u0003R\u0006\r\u0012\u0007\u0002\u0013=\u0001^Bq!S\u0010\u0011\u0002\u0003\u00071\nC\u0004[?A\u0005\t\u0019A&\t\u0011}{\u0002\u0013!a\u0001\u0005c\u000bq\"\u00199qYf$C-\u001a4bk2$H%M\u000b\u0005\u0003\u0007\u0013i\u000eB\u0005eA\u0001\u0006\t\u0011!b\u0001K\"\n\"Q\\7\u0003b\n\u0015(\u0011\u001eBw\u0005c\u0014)P!?2\r\r\n(Oa9tc\u0011!C\bQ\u001c2\r\r2xOa:yc\u0011!C\bQ\u001c2\r\rZHPa;~c\u0011!C\bQ\u001c2\u0013\r\n\t!a\u0001\u0003p\u0006\u0015\u0011\u0007\u0002\u0013=\u0001^\n\u0014bIA\u0006\u0003\u001b\u0011\u00190a\u00042\t\u0011b\u0004iN\u0019\nG\u0005U\u0011q\u0003B|\u00033\tD\u0001\n\u001fAoEJ1%a\b\u0002\"\tm\u00181E\u0019\u0005Iq\u0002u'A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0011\t\u0019i!\u0001\u0005\u0013\u0011\f\u0003\u0015!A\u0001\u0006\u0004)\u0007&EB\u0001[\u000e\u00151\u0011BB\u0007\u0007#\u0019)b!\u0007\u0004\u001eE21%\u001d:\u0004\bM\fD\u0001\n\u001fAoE21E^<\u0004\fa\fD\u0001\n\u001fAoE21e\u001f?\u0004\u0010u\fD\u0001\n\u001fAoEJ1%!\u0001\u0002\u0004\rM\u0011QA\u0019\u0005Iq\u0002u'M\u0005$\u0003\u0017\tiaa\u0006\u0002\u0010E\"A\u0005\u0010!8c%\u0019\u0013QCA\f\u00077\tI\"\r\u0003%y\u0001;\u0014'C\u0012\u0002 \u0005\u00052qDA\u0012c\u0011!C\bQ\u001c\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uIM*Ba!\n\u0004,U\u00111q\u0005\u0016\u0005\u0007S\t9\tE\u0002c\u0007W!\u0011\u0002\u001a\u0012!\u0002\u0003\u0005)\u0019A3)#\r-Rna\f\u00044\r]21HB \u0007\u0007\u001a9%\r\u0004$cJ\u001c\td]\u0019\u0005Iq\u0002u'\r\u0004$m^\u001c)\u0004_\u0019\u0005Iq\u0002u'\r\u0004$wr\u001cI$`\u0019\u0005Iq\u0002u'M\u0005$\u0003\u0003\t\u0019a!\u0010\u0002\u0006E\"A\u0005\u0010!8c%\u0019\u00131BA\u0007\u0007\u0003\ny!\r\u0003%y\u0001;\u0014'C\u0012\u0002\u0016\u0005]1QIA\rc\u0011!C\bQ\u001c2\u0013\r\ny\"!\t\u0004J\u0005\r\u0012\u0007\u0002\u0013=\u0001^\nq!\u001e8baBd\u00170\u0006\u0003\u0004P\r}C\u0003BB)\u0007\u007f\u0002R!NB*\u0007/J1a!\u00167\u0005\u0019y\u0005\u000f^5p]B9Qg!\u0017L\u0017\u000eu\u0013bAB.m\t1A+\u001e9mKN\u00022AYB0\t%!7\u0005)A\u0001\u0002\u000b\u0007Q\rK\t\u0004`5\u001c\u0019ga\u001a\u0004l\r=41OB<\u0007w\ndaI9s\u0007K\u001a\u0018\u0007\u0002\u0013=\u0001^\nda\t<x\u0007SB\u0018\u0007\u0002\u0013=\u0001^\ndaI>}\u0007[j\u0018\u0007\u0002\u0013=\u0001^\n\u0014bIA\u0001\u0003\u0007\u0019\t(!\u00022\t\u0011b\u0004iN\u0019\nG\u0005-\u0011QBB;\u0003\u001f\tD\u0001\n\u001fAoEJ1%!\u0006\u0002\u0018\re\u0014\u0011D\u0019\u0005Iq\u0002u'M\u0005$\u0003?\t\tc! \u0002$E\"A\u0005\u0010!8\u0011%\u0019\tiIA\u0001\u0002\u0004\u0019\u0019)A\u0002yIA\u0002B!\u0014\u0001\u0004^\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIE*B!a!\u0004\n\u0012IA\r\nQ\u0001\u0002\u0003\u0015\r!\u001a\u0015\u0012\u0007\u0013k7QRBI\u0007+\u001bIj!(\u0004\"\u000e\u0015\u0016GB\u0012re\u000e=5/\r\u0003%y\u0001;\u0014GB\u0012wo\u000eM\u00050\r\u0003%y\u0001;\u0014GB\u0012|y\u000e]U0\r\u0003%y\u0001;\u0014'C\u0012\u0002\u0002\u0005\r11TA\u0003c\u0011!C\bQ\u001c2\u0013\r\nY!!\u0004\u0004 \u0006=\u0011\u0007\u0002\u0013=\u0001^\n\u0014bIA\u000b\u0003/\u0019\u0019+!\u00072\t\u0011b\u0004iN\u0019\nG\u0005}\u0011\u0011EBT\u0003G\tD\u0001\n\u001fAo\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uII*B!a!\u0004.\u0012IA-\nQ\u0001\u0002\u0003\u0015\r!\u001a\u0015\u0012\u0007[k7\u0011WB[\u0007s\u001bil!1\u0004F\u000e%\u0017GB\u0012re\u000eM6/\r\u0003%y\u0001;\u0014GB\u0012wo\u000e]\u00060\r\u0003%y\u0001;\u0014GB\u0012|y\u000emV0\r\u0003%y\u0001;\u0014'C\u0012\u0002\u0002\u0005\r1qXA\u0003c\u0011!C\bQ\u001c2\u0013\r\nY!!\u0004\u0004D\u0006=\u0011\u0007\u0002\u0013=\u0001^\n\u0014bIA\u000b\u0003/\u00199-!\u00072\t\u0011b\u0004iN\u0019\nG\u0005}\u0011\u0011EBf\u0003G\tD\u0001\n\u001fAo\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIM*Ba!5\u0004XV\u001111\u001b\u0016\u0005\u0007+\f9\tE\u0002c\u0007/$\u0011\u0002\u001a\u0014!\u0002\u0003\u0005)\u0019A3)#\r]Wna7\u0004`\u000e\r8q]Bv\u0007_\u001c\u00190\r\u0004$cJ\u001cin]\u0019\u0005Iq\u0002u'\r\u0004$m^\u001c\t\u000f_\u0019\u0005Iq\u0002u'\r\u0004$wr\u001c)/`\u0019\u0005Iq\u0002u'M\u0005$\u0003\u0003\t\u0019a!;\u0002\u0006E\"A\u0005\u0010!8c%\u0019\u00131BA\u0007\u0007[\fy!\r\u0003%y\u0001;\u0014'C\u0012\u0002\u0016\u0005]1\u0011_A\rc\u0011!C\bQ\u001c2\u0013\r\ny\"!\t\u0004v\u0006\r\u0012\u0007\u0002\u0013=\u0001^\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"Aa\u001e"
)
public class Edge implements Serializable, Product {
   private long srcId;
   private long dstId;
   public Object attr;

   public static Object $lessinit$greater$default$3() {
      return Edge$.MODULE$.$lessinit$greater$default$3();
   }

   public static long $lessinit$greater$default$2() {
      return Edge$.MODULE$.$lessinit$greater$default$2();
   }

   public static long $lessinit$greater$default$1() {
      return Edge$.MODULE$.$lessinit$greater$default$1();
   }

   public static Option unapply(final Edge x$0) {
      return Edge$.MODULE$.unapply(x$0);
   }

   public static Object apply$default$3() {
      return Edge$.MODULE$.apply$default$3();
   }

   public static long apply$default$2() {
      return Edge$.MODULE$.apply$default$2();
   }

   public static long apply$default$1() {
      return Edge$.MODULE$.apply$default$1();
   }

   public static Edge apply(final long srcId, final long dstId, final Object attr) {
      return Edge$.MODULE$.apply(srcId, dstId, attr);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long srcId() {
      return this.srcId;
   }

   public void srcId_$eq(final long x$1) {
      this.srcId = x$1;
   }

   public long dstId() {
      return this.dstId;
   }

   public void dstId_$eq(final long x$1) {
      this.dstId = x$1;
   }

   public Object attr() {
      return this.attr;
   }

   public void attr_$eq(final Object x$1) {
      this.attr = x$1;
   }

   public long otherVertexId(final long vid) {
      if (this.srcId() == vid) {
         return this.dstId();
      } else {
         .MODULE$.assert(this.dstId() == vid);
         return this.srcId();
      }
   }

   public EdgeDirection relativeDirection(final long vid) {
      if (vid == this.srcId()) {
         return EdgeDirection$.MODULE$.Out();
      } else {
         .MODULE$.assert(vid == this.dstId());
         return EdgeDirection$.MODULE$.In();
      }
   }

   public Edge copy(final long srcId, final long dstId, final Object attr) {
      return new Edge(srcId, dstId, attr);
   }

   public long copy$default$1() {
      return this.srcId();
   }

   public long copy$default$2() {
      return this.dstId();
   }

   public Object copy$default$3() {
      return this.attr();
   }

   public String productPrefix() {
      return "Edge";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.srcId());
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.dstId());
         }
         case 2 -> {
            return this.attr();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof Edge;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "srcId";
         }
         case 1 -> {
            return "dstId";
         }
         case 2 -> {
            return "attr";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.srcId()));
      var1 = Statics.mix(var1, Statics.longHash(this.dstId()));
      var1 = Statics.mix(var1, Statics.anyHash(this.attr()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label40: {
            if (x$1 instanceof Edge) {
               Edge var4 = (Edge)x$1;
               if (this.srcId() == var4.srcId() && this.dstId() == var4.dstId() && BoxesRunTime.equals(this.attr(), var4.attr()) && var4.canEqual(this)) {
                  break label40;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public boolean attr$mcZ$sp() {
      return BoxesRunTime.unboxToBoolean(this.attr());
   }

   public byte attr$mcB$sp() {
      return BoxesRunTime.unboxToByte(this.attr());
   }

   public char attr$mcC$sp() {
      return BoxesRunTime.unboxToChar(this.attr());
   }

   public double attr$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.attr());
   }

   public float attr$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.attr());
   }

   public int attr$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.attr());
   }

   public long attr$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.attr());
   }

   public void attr$mcZ$sp_$eq(final boolean x$1) {
      this.attr_$eq(BoxesRunTime.boxToBoolean(x$1));
   }

   public void attr$mcB$sp_$eq(final byte x$1) {
      this.attr_$eq(BoxesRunTime.boxToByte(x$1));
   }

   public void attr$mcC$sp_$eq(final char x$1) {
      this.attr_$eq(BoxesRunTime.boxToCharacter(x$1));
   }

   public void attr$mcD$sp_$eq(final double x$1) {
      this.attr_$eq(BoxesRunTime.boxToDouble(x$1));
   }

   public void attr$mcF$sp_$eq(final float x$1) {
      this.attr_$eq(BoxesRunTime.boxToFloat(x$1));
   }

   public void attr$mcI$sp_$eq(final int x$1) {
      this.attr_$eq(BoxesRunTime.boxToInteger(x$1));
   }

   public void attr$mcJ$sp_$eq(final long x$1) {
      this.attr_$eq(BoxesRunTime.boxToLong(x$1));
   }

   public Edge copy$mZc$sp(final long srcId, final long dstId, final boolean attr) {
      return new Edge$mcZ$sp(srcId, dstId, attr);
   }

   public Edge copy$mBc$sp(final long srcId, final long dstId, final byte attr) {
      return new Edge$mcB$sp(srcId, dstId, attr);
   }

   public Edge copy$mCc$sp(final long srcId, final long dstId, final char attr) {
      return new Edge$mcC$sp(srcId, dstId, attr);
   }

   public Edge copy$mDc$sp(final long srcId, final long dstId, final double attr) {
      return new Edge$mcD$sp(srcId, dstId, attr);
   }

   public Edge copy$mFc$sp(final long srcId, final long dstId, final float attr) {
      return new Edge$mcF$sp(srcId, dstId, attr);
   }

   public Edge copy$mIc$sp(final long srcId, final long dstId, final int attr) {
      return new Edge$mcI$sp(srcId, dstId, attr);
   }

   public Edge copy$mJc$sp(final long srcId, final long dstId, final long attr) {
      return new Edge$mcJ$sp(srcId, dstId, attr);
   }

   public boolean copy$default$3$mcZ$sp() {
      return BoxesRunTime.unboxToBoolean(this.copy$default$3());
   }

   public byte copy$default$3$mcB$sp() {
      return BoxesRunTime.unboxToByte(this.copy$default$3());
   }

   public char copy$default$3$mcC$sp() {
      return BoxesRunTime.unboxToChar(this.copy$default$3());
   }

   public double copy$default$3$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.copy$default$3());
   }

   public float copy$default$3$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.copy$default$3());
   }

   public int copy$default$3$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.copy$default$3());
   }

   public long copy$default$3$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.copy$default$3());
   }

   public boolean specInstance$() {
      return false;
   }

   public Edge(final long srcId, final long dstId, final Object attr) {
      this.srcId = srcId;
      this.dstId = dstId;
      this.attr = attr;
      super();
      Product.$init$(this);
   }
}

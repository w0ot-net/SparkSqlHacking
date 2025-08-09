package scala.reflect.internal;

import java.lang.invoke.SerializedLambda;
import java.util.stream.IntStream;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.math.package;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015\u0005eACA1\u0003G\u0002\n1!\u0001\u0002r!9\u0011Q\u0011\u0001\u0005\u0002\u0005\u001d\u0005\"CAH\u0001\t\u0007IQBAI\u0011%\t9\n\u0001b\u0001\n\u001b\tI\nC\u0005\u0002 \u0002\u0011\r\u0011\"\u0004\u0002\"\"I\u0011q\u0015\u0001C\u0002\u0013\u0015\u0011\u0011\u0016\u0005\b\u0003_\u0003A\u0011CAY\u0011%\tI\f\u0001b!\n\u0013\tY\fC\u0005\u0002N\u0002\u0001\r\u0015\"\u0003\u0002P\"I\u0011Q\u001c\u0001AB\u0013%\u0011q\u001c\u0005\b\u0003K\u0004A\u0011AAh\u0011\u001d\tY\u0010\u0001C\u0001\u0003{D\u0011B!\u0002\u0001\u0001\u0004&IAa\u0002\t\u0013\t=\u0001\u00011Q\u0005\n\tE\u0001b\u0002B\u000b\u0001\u0011\u0015!q\u0001\u0005\n\u0005/\u0001!\u0019)C\u0005\u00053A\u0011\u0002\"$\u0001\u0005\u0004&I\u0001b$\t\u000f\u0011M\u0005\u0001\"\u0002\u0005\u0016\"9AQ\u0014\u0001\u0005\n\u0011}\u0005b\u0002CT\u0001\u0011%A\u0011\u0016\u0005\b\tg\u0003A\u0011\u0002C[\u0011\u001d!i\f\u0001C\u0003\t\u007fCq\u0001\"0\u0001\t\u000b!9\rC\u0004\u0005L\u0002!)\u0001\"4\t\u000f\u0011u\u0006\u0001\"\u0002\u0005R\"9A1\u001a\u0001\u0005\u0006\u0011m\u0007b\u0002C_\u0001\u0011\u0005AQ\u001d\u0005\b\t\u0017\u0004A\u0011\u0001C}\u0011\u001d!i\f\u0001C\u0003\t\u007fDq!\"\u0005\u0001\t\u000b)\u0019\u0002C\u0004\u0006\u0018\u0001!)!\"\u0007\t\u000f\u0011-\u0007\u0001\"\u0002\u0006\u001e!9A1\u001a\u0001\u0005\u0006\u0015\u0015\u0002bBC\u0017\u0001\u0011\u0015Qq\u0006\u0004\n\u0005g\u0001\u0001\u0013aI\u0011\u0005kAqAa\u000e#\r\u0003\t\tLB\u0004\u0003(\u0001\t\tC!\u000b\t\u0015\t\u0005CE!b\u0001\n#\u00119\u0001\u0003\u0006\u0003D\u0011\u0012\t\u0011)A\u0005\u0005\u0013A!B!\u0012%\u0005\u000b\u0007I\u0011\u0003B\u0004\u0011)\u00119\u0005\nB\u0001B\u0003%!\u0011\u0002\u0005\u000b\u0005\u0013\"#Q1A\u0005\u0012\t-\u0003B\u0003B2I\t\u0005\t\u0015!\u0003\u0003N!9!Q\r\u0013\u0005\u0002\t\u001dDa\u0002B8I\t\u0005!\u0011\u000f\u0005\t\u0005s\"\u0003U\"\u0005\u0003|!9!\u0011\u0011\u0013\u0005\u0006\t\u001d\u0001b\u0002BBI\u0019\u0005!1\u0010\u0005\b\u0005\u000b#CQ\u0001BD\u0011\u001d\u0011I\t\nC\u0003\u0003cCqAa\u000e%\t\u000b\n\t\fC\u0004\u0003\f\u00122\tAa\u0013\t\u000f\t5EE\"\u0001\u00022\"9!q\u0012\u0013\u0007\u0002\u0005E\u0006b\u0002BII\u0019\u0005!1\u0013\u0005\b\u0005+#c\u0011\u0001BL\u0011\u001d\u0011i\f\nD\u0001\u0005'DqA!6%\t\u000b\u00119\u000eC\u0004\u0003@\u00122\tAa<\t\u000f\tUH\u0005\"\u0011\u0003x\"9!Q\u0017\u0013\u0007\u0002\tu\bbBB\u0001I\u0011\u000511\u0001\u0005\b\u0007\u001f!CQAB\t\u0011\u001d\u0019I\u0002\nC\u0003\u0003\u001fDqaa\u0007%\t\u000b\u00129\tC\u0004\u0004\u001e\u0011\"\taa\b\t\u000f\ruA\u0005\"\u0001\u0004&!91\u0011\u0006\u0013\u0005\u0006\r-\u0002bBB\u0019I\u0011\u001511\u0007\u0005\b\u0007c!CQAB\u001d\u0011\u001d\u0019\t\u0004\nC\u0003\u0007\u007fAqa!\r%\t\u000b\u0019)\u0005C\u0004\u0004L\u0011\")a!\u0014\t\u000f\r-C\u0005\"\u0002\u0004R!91q\u000b\u0013\u0005\u0006\re\u0003bBB,I\u0011\u00151q\f\u0005\b\u0007/\"CQAB3\u0011\u001d\u0019Y\u0007\nC\u0003\u0007[Bqaa\u001b%\t\u000b\u0019\u0019\bC\u0004\u0004l\u0011\")aa\u001f\t\u000f\r\u0005E\u0005\"\u0002\u0004\u0004\"91\u0011\u0011\u0013\u0005\u0006\r%\u0005bBBGI\u0011\u00151q\u0012\u0005\b\u0007+#C\u0011ABL\u0011\u001d\u0019Y\n\nC\u0003\u0007;Cqaa(%\t\u000b\u0019i\nC\u0004\u0004X\u0011\")a!)\t\u000f\r]C\u0005\"\u0002\u0004(\"911\u000e\u0013\u0005\u0006\r5\u0006bBB6I\u0011\u00151\u0011\u0017\u0005\b\u0007k#C\u0011BB\\\u0011\u001d\u0019i\f\nC\u0001\u0007\u007fCqa!0%\t\u0003\u0019\u0019\rC\u0004\u0004>\u0012\"\taa3\t\u000f\rUE\u0005\"\u0001\u0004P\"911\u001b\u0013\u0005\u0002\rU\u0007bBBnI\u0011\u0005!1\n\u0005\b\u0007;$C\u0011\u0001B&\u0011\u001d\u0019y\u000e\nC\u0001\u0005wBqa!9%\t\u0003\u0011Y\bC\u0004\u0003R\u0012\"\tAa\u0013\t\u000f\r\rH\u0005\"\u0001\u0004f\"911\u001d\u0013\u0005\u0002\r%\bbBBrI\u0011\u00051Q\u001e\u0005\b\u0007G$C\u0011ABy\u0011\u001d\u0019I\u0010\nC\u0001\u0007wDqaa@%\t\u0003!\t\u0001C\u0004\u0004\u0000\u0012\"\t\u0001\"\u0002\t\u000f\u0011%A\u0005\"\u0001\u0005\f!9A\u0011\u0003\u0013\u0005\u0002\u0011M\u0001b\u0002C\fI\u0011\u0005A\u0011\u0004\u0005\b\t;!C\u0011\u0001BJ\u0011\u001d!y\u0002\nC\u0001\u0005'Cq\u0001\"\t%\t\u0003\u0011Y\bC\u0004\u0005$\u0011\"\tAa%\t\u000f\u0011\u0015B\u0005\"\u0001\u0003\u0014\"9Aq\u0005\u0013\u0005\u0002\tM\u0005b\u0002C\u0015I\u0011\u0005!1\u0013\u0005\b\tW!C\u0011\u0002BJ\u0011\u001d!i\u0003\nC\u0001\u0005wBq\u0001b\f%\t\u0003\t\t\fC\u0004\u00052\u0011\"\tAa\u0013\t\u000f\u0011MB\u0005\"\u0001\u0003L\"9AQ\u0007\u0013\u0005\u0006\u0011]\u0002b\u0002C\u001eI\u0011\u0015CQ\b\u0005\b\t\u007f!CQ\u0001C!\u0011\u001d!y\u0004\nC\u0003\t#B\u0011\"b\r\u0001\u0005\u0004%\u0019!\"\u000e\u0007\r\t\u0005\u0002A\u0001B\u0012\u00111\u0011)+!\u0001\u0003\u0002\u0003\u0006IA!\u0003&\u00111\u00119+!\u0001\u0003\u0002\u0003\u0006IA!\u0003(\u0011-\u0011\u0019)!\u0001\u0003\u0006\u0004%\tAa%\t\u0017\t%\u0016\u0011\u0001B\u0001B\u0003%!Q\u0004\u0005\r\u0005\u0013\n\tA!A!\u0002\u0013\u0011i%\u000b\u0005\t\u0005K\n\t\u0001\"\u0001\u0005t\u00159!qNA\u0001\u0001\tu\u0001\"\u0003B=\u0003\u0003\u0001K\u0011\u0003BJ\u0011!\u0011i)!\u0001\u0005\u0002\u0005E\u0006\u0002\u0003BH\u0003\u0003!\t!!-\t\u0011\tE\u0015\u0011\u0001C\u0001\u0005'C\u0001B!&\u0002\u0002\u0011\u0005!q\u0013\u0005\t\u0005k\u000b\t\u0001\"\u0001\u0005~!A!QXA\u0001\t\u0003\u00119\n\u0003\u0005\u0003@\u0006\u0005A\u0011\u0001CA\u0011!\u0011Y)!\u0001\u0005\u0002\t-\u0007\u0002\u0003CD\u0003\u0003!I\u0001\"#\t\u0013\u0015}\u0002A1A\u0005\u0004\u0015\u0005saBC#\u0001!\u0005Qq\t\u0004\b\u0005C\u0001\u0001\u0012AC%\u0011!\u0011)'!\u000b\u0005\u0002\u0015E\u0003\u0002CC*\u0003S!\t!\"\u0016\t\u0011\u0015e\u0013\u0011\u0006C\u0001\u000b72aAa'\u0001\u0005\tu\u0005\u0002\u0004BS\u0003c\u0011\t\u0011)A\u0005\u0005\u0013)\u0003\u0002\u0004BT\u0003c\u0011\t\u0011)A\u0005\u0005\u00139\u0003b\u0003BB\u0003c\u0011)\u0019!C\u0001\u0005/C1B!+\u00022\t\u0005\t\u0015!\u0003\u0003\u001a\"a!\u0011JA\u0019\u0005\u0003\u0005\u000b\u0011\u0002B'S!A!QMA\u0019\t\u0003\u0011Y+B\u0004\u0003p\u0005E\u0002A!'\t\u0013\te\u0014\u0011\u0007Q\u0005\u0012\t]\u0005\u0002\u0003BG\u0003c!\t!!-\t\u0011\t=\u0015\u0011\u0007C\u0001\u0003cC\u0001B!%\u00022\u0011\u0005!1\u0013\u0005\t\u0005+\u000b\t\u0004\"\u0001\u0003\u0018\"A!QWA\u0019\t\u0003\u00119\f\u0003\u0005\u0003>\u0006EB\u0011\u0001BJ\u0011!\u0011y,!\r\u0005\u0002\t\u0005\u0007\u0002\u0003BF\u0003c!\tAa3\t\u0011\tE\u0017\u0011\u0007C!\u0005\u0017B\u0011\"\"\u001a\u0001\u0005\u0004%\u0019!b\u001a\b\u000f\u0015-\u0004\u0001#\u0001\u0006n\u00199!1\u0014\u0001\t\u0002\u0015=\u0004\u0002\u0003B3\u00033\"\t!b\u001e\t\u0011\u0015M\u0013\u0011\fC\u0001\u000bsB\u0001\"\"\u0017\u0002Z\u0011\u0005QQ\u0010\u0002\u0006\u001d\u0006lWm\u001d\u0006\u0005\u0003K\n9'\u0001\u0005j]R,'O\\1m\u0015\u0011\tI'a\u001b\u0002\u000fI,g\r\\3di*\u0011\u0011QN\u0001\u0006g\u000e\fG.Y\u0002\u0001'\u0015\u0001\u00111OA>!\u0011\t)(a\u001e\u000e\u0005\u0005-\u0014\u0002BA=\u0003W\u0012a!\u00118z%\u00164\u0007\u0003BA?\u0003\u0007k!!a \u000b\t\u0005\u0005\u0015qM\u0001\u0004CBL\u0017\u0002BA1\u0003\u007f\na\u0001J5oSR$CCAAE!\u0011\t)(a#\n\t\u00055\u00151\u000e\u0002\u0005+:LG/A\u0005I\u0003NCulU%[\u000bV\u0011\u00111S\b\u0003\u0003+k2\u0001\u0001A\u0001\u0003%A\u0015i\u0015%`\u001b\u0006\u001b6*\u0006\u0002\u0002\u001c>\u0011\u0011QT\u000f\u0003\u007f~\u0010\u0011BT!N\u000b~\u001b\u0016JW#\u0016\u0005\u0005\rvBAAS;\r\u0011\u0001\u0001A\u0001\n]\u0006lW\rR3ck\u001e,\"!a+\u0010\u0005\u00055\u0016$\u0001\u0001\u0002!MLhn\u00195s_:L'0\u001a(b[\u0016\u001cXCAAZ!\u0011\t)(!.\n\t\u0005]\u00161\u000e\u0002\b\u0005>|G.Z1o\u0003!q\u0017-\\3M_\u000e\\WCAA_!\u0011\ty,!3\u000e\u0005\u0005\u0005'\u0002BAb\u0003\u000b\fA\u0001\\1oO*\u0011\u0011qY\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002L\u0006\u0005'AB(cU\u0016\u001cG/A\u0003`G\"\u00148/\u0006\u0002\u0002RB1\u0011QOAj\u0003/LA!!6\u0002l\t)\u0011I\u001d:bsB!\u0011QOAm\u0013\u0011\tY.a\u001b\u0003\t\rC\u0017M]\u0001\n?\u000eD'o]0%KF$B!!#\u0002b\"I\u00111]\u0005\u0002\u0002\u0003\u0007\u0011\u0011[\u0001\u0004q\u0012\n\u0014\u0001B2ieND3BCAu\u0003_\f\t0!>\u0002xB!\u0011QOAv\u0013\u0011\ti/a\u001b\u0003\u0015\u0011,\u0007O]3dCR,G-A\u0004nKN\u001c\u0018mZ3\"\u0005\u0005M\u0018A\u000b#p]\u001e\"\b%Y2dKN\u001c\bE\\1nK\u0002\"\u0018M\u00197fA\r|g\u000e^3oiN\u0004C-\u001b:fGRd\u0017PL\u0001\u0006g&t7-Z\u0011\u0003\u0003s\faA\r\u00182e9J\u0014\u0001C2ieN|F%Z9\u0015\t\u0005%\u0015q \u0005\b\u0005\u0003Y\u0001\u0019AAi\u0003\t\u00197\u000fK\u0006\f\u0003S\fy/!=\u0002v\u0006]\u0018A\u00018d+\t\u0011I\u0001\u0005\u0003\u0002v\t-\u0011\u0002\u0002B\u0007\u0003W\u00121!\u00138u\u0003\u0019q7m\u0018\u0013fcR!\u0011\u0011\u0012B\n\u0011%\t\u0019/DA\u0001\u0002\u0004\u0011I!A\u0007oC6,G+\u00192mKNK'0Z\u0001\u000ei\u0016\u0014X\u000eS1tQR\f'\r\\3\u0016\u0005\tm\u0001CBA;\u0003'\u0014i\u0002\u0005\u0003\u0003 \u0005\u0005Q\"\u0001\u0001\u0003\u0011Q+'/\u001c(b[\u0016\u001cb!!\u0001\u0003&\u00115\u0004c\u0001B\u0010I\t!a*Y7f'\u001d!#1\u0006B\u0019\u0005w\u0001BAa\b\u0003.%!!qFAB\u0005\u001dq\u0015-\\3Ba&\u00042Aa\b#\u00059q\u0015-\\3ICNL5/R7qif\u001c2AIA:\u0003\u001dI7/R7qifL#A\t\u0013\u0011\t\u0005}&QH\u0005\u0005\u0005\u007f\t\tM\u0001\u0007DQ\u0006\u00148+Z9vK:\u001cW-A\u0003j]\u0012,\u00070\u0001\u0004j]\u0012,\u0007\u0010I\u0001\u0004Y\u0016t\u0017\u0001\u00027f]\u0002\nAbY1dQ\u0016$7\u000b\u001e:j]\u001e,\"A!\u0014\u0011\t\t=#Q\f\b\u0005\u0005#\u0012I\u0006\u0005\u0003\u0003T\u0005-TB\u0001B+\u0015\u0011\u00119&a\u001c\u0002\rq\u0012xn\u001c;?\u0013\u0011\u0011Y&a\u001b\u0002\rA\u0013X\rZ3g\u0013\u0011\u0011yF!\u0019\u0003\rM#(/\u001b8h\u0015\u0011\u0011Y&a\u001b\u0002\u001b\r\f7\r[3e'R\u0014\u0018N\\4!\u0003\u0019a\u0014N\\5u}QA!Q\u0005B5\u0005W\u0012i\u0007C\u0004\u0003B-\u0002\rA!\u0003\t\u000f\t\u00153\u00061\u0001\u0003\n!9!\u0011J\u0016A\u0002\t5#\u0001\u0004+iSNt\u0015-\\3UsB,\u0017\u0003\u0002B:\u0005K\u0001B!!\u001e\u0003v%!!qOA6\u0005\u0011qU\u000f\u001c7\u0002\u0011QD\u0017n\u001d(b[\u0016,\"A! \u0011\u0007\t}D&D\u0001%\u0003\u0015\u0019H/\u0019:u\u0003\u0011qW\r\u001f;\u0002\r1,gn\u001a;i)\t\u0011I!\u0001\u0005o_:,U\u000e\u001d;z\u0003!q\u0017-\\3LS:$\u0017AC5t)\u0016\u0014XNT1nK\u0006Q\u0011n\u001d+za\u0016t\u0015-\\3\u0002\u0015Q|G+\u001a:n\u001d\u0006lW-\u0006\u0002\u0003\u001e\u0005QAo\u001c+za\u0016t\u0015-\\3\u0016\u0005\te\u0005\u0003\u0002B\u0010\u0003c\u0011\u0001\u0002V=qK:\u000bW.Z\n\u0007\u0003c\u0011)Ca(\u0011\t\t}!\u0011U\u0005\u0005\u0005G\u000b\u0019IA\u0006UsB,g*Y7f\u0003BL\u0017AB5oI\u0016D\b'\u0001\u0003mK:\u0004\u0014!\u00028fqR\u0004CC\u0003BM\u0005[\u0013yK!-\u00034\"A!QUA\u001f\u0001\u0004\u0011I\u0001\u0003\u0005\u0003(\u0006u\u0002\u0019\u0001B\u0005\u0011!\u0011\u0019)!\u0010A\u0002\te\u0005\u0002\u0003B%\u0003{\u0001\rA!\u0014\u0002\u000f9,wOT1nKR!!\u0011\u0014B]\u0011!\u0011Y,a\u0013A\u0002\t5\u0013aA:ue\u0006i1m\\7qC:LwN\u001c(b[\u0016\fqa];c\u001d\u0006lW\r\u0006\u0004\u0003\u001a\n\r'q\u0019\u0005\t\u0005\u000b\fy\u00051\u0001\u0003\n\u0005!aM]8n\u0011!\u0011I-a\u0014A\u0002\t%\u0011A\u0001;p+\t\u0011i\r\u0005\u0003\u0002@\n=\u0017\u0002\u0002B0\u0003\u0003\fa\u0001Z3d_\u0012,WC\u0001B\u0013\u0003!\t7\u000fV=qK>3W\u0003\u0002Bm\u0005?$BAa7\u0003lB!!Q\u001cBp\u0019\u0001!qA!9:\u0005\u0004\u0011\u0019OA\u0001O#\u0011\u0011)O!\n\u0011\t\u0005U$q]\u0005\u0005\u0005S\fYGA\u0004O_RD\u0017N\\4\t\u000f\t5\u0018\b1\u0001\u0003\\\u0006)q\u000e\u001e5feR1!Q\u0010By\u0005gDqA!2;\u0001\u0004\u0011I\u0001C\u0004\u0003Jj\u0002\rA!\u0003\u0002\u0017M,(mU3rk\u0016t7-\u001a\u000b\u0007\u0005w\u0011IPa?\t\u000f\t\u00157\b1\u0001\u0003\n!9!\u0011Z\u001eA\u0002\t%A\u0003\u0002B?\u0005\u007fDqAa/=\u0001\u0004\u0011i%A\u0004nCBt\u0015-\\3\u0015\t\tu4Q\u0001\u0005\b\u0007\u000fi\u0004\u0019AB\u0005\u0003\u00051\u0007\u0003CA;\u0007\u0017\u0011iE!\u0014\n\t\r5\u00111\u000e\u0002\n\rVt7\r^5p]F\n\u0011bY8qs\u000eC\u0017M]:\u0015\r\u0005%51CB\u000b\u0011\u001d\u0011\tA\u0010a\u0001\u0003#Dqaa\u0006?\u0001\u0004\u0011I!\u0001\u0004pM\u001a\u001cX\r^\u0001\bi>\u001c\u0005.\u0019:t\u0003!A\u0017m\u001d5D_\u0012,\u0017!D:ue&twm\u0018\u0013fc\u0012*\u0017\u000f\u0006\u0003\u00024\u000e\u0005\u0002bBB\u0012\u0003\u0002\u0007!QE\u0001\u0005i\"\fG\u000f\u0006\u0003\u00024\u000e\u001d\u0002bBB\u0012\u0005\u0002\u0007!QJ\u0001\u0007G\"\f'/\u0011;\u0015\t\u0005]7Q\u0006\u0005\b\u0007_\u0019\u0005\u0019\u0001B\u0005\u0003\u0005I\u0017a\u00019pgR!!\u0011BB\u001b\u0011\u001d\u00199\u0004\u0012a\u0001\u0003/\f\u0011a\u0019\u000b\u0005\u0005\u0013\u0019Y\u0004C\u0004\u0004>\u0015\u0003\rA!\u0014\u0002\u0003M$bA!\u0003\u0004B\r\r\u0003bBB\u001c\r\u0002\u0007\u0011q\u001b\u0005\b\u0005\u00033\u0005\u0019\u0001B\u0005)\u0019\u0011Iaa\u0012\u0004J!91QH$A\u0002\t5\u0003b\u0002BA\u000f\u0002\u0007!\u0011B\u0001\bY\u0006\u001cH\u000fU8t)\u0011\u0011Iaa\u0014\t\u000f\r]\u0002\n1\u0001\u0002XR1!\u0011BB*\u0007+Bqaa\u000eJ\u0001\u0004\t9\u000eC\u0004\u0003\u0002&\u0003\rA!\u0003\u0002\u0015M$\u0018M\u001d;t/&$\b\u000e\u0006\u0003\u00024\u000em\u0003bBB/\u0015\u0002\u0007!QE\u0001\u0007aJ,g-\u001b=\u0015\r\u0005M6\u0011MB2\u0011\u001d\u0019if\u0013a\u0001\u0005KAqA!!L\u0001\u0004\u0011I\u0001\u0006\u0004\u00024\u000e\u001d4\u0011\u000e\u0005\b\u0007;b\u0005\u0019\u0001B'\u0011\u001d\u0011\t\t\u0014a\u0001\u0005\u0013\t\u0001\"\u001a8eg^KG\u000f\u001b\u000b\u0005\u0003g\u001by\u0007C\u0004\u0004r5\u0003\rA!\n\u0002\rM,hMZ5y)\u0019\t\u0019l!\u001e\u0004x!91\u0011\u000f(A\u0002\t\u0015\u0002bBB=\u001d\u0002\u0007!\u0011B\u0001\u0004K:$GCBAZ\u0007{\u001ay\bC\u0004\u0004r=\u0003\rA!\u0014\t\u000f\ret\n1\u0001\u0003\n\u0005a1m\u001c8uC&t7OT1nKR!\u00111WBC\u0011\u001d\u00199\t\u0015a\u0001\u0005\u001b\nqa];c]\u0006lW\r\u0006\u0003\u00024\u000e-\u0005bBBD#\u0002\u0007!QE\u0001\rG>tG/Y5og\u000eC\u0017M\u001d\u000b\u0005\u0003g\u001b\t\nC\u0004\u0004\u0014J\u0003\r!a6\u0002\u0005\rD\u0017a\u00037bgRLe\u000eZ3y\u001f\u001a$BA!\u0003\u0004\u001a\"91QH*A\u0002\t5\u0013!C:uCJ$8\t[1s+\t\t9.A\u0004f]\u0012\u001c\u0005.\u0019:\u0015\t\u0005M61\u0015\u0005\b\u0007K3\u0006\u0019AAl\u0003\u0011\u0019\u0007.\u0019:\u0015\t\u0005M6\u0011\u0016\u0005\b\u0007W;\u0006\u0019\u0001B'\u0003\u0011q\u0017-\\3\u0015\t\u0005M6q\u0016\u0005\b\u0007KC\u0006\u0019AAl)\u0011\t\u0019la-\t\u000f\r-\u0016\f1\u0001\u0003N\u0005Qa-\u001b=J]\u0012,\u0007p\u00144\u0015\t\t%1\u0011\u0018\u0005\b\u0007wS\u0006\u0019\u0001B\u0005\u0003\rIG\r_\u0001\bS:$W\r_(g)\u0011\u0011Ia!1\t\u000f\rM5\f1\u0001\u0002XR1!\u0011BBc\u0007\u000fDqaa%]\u0001\u0004\t9\u000eC\u0004\u0004Jr\u0003\rA!\u0003\u0002\u0013\u0019\u0014x.\\%oI\u0016DH\u0003\u0002B\u0005\u0007\u001bDqa!\u0010^\u0001\u0004\u0011i\u0005\u0006\u0003\u0003\n\rE\u0007bBBJ=\u0002\u0007\u0011q[\u0001\be\u0016\u0004H.Y2f)\u0019\u0011)ca6\u0004Z\"9!QY0A\u0002\u0005]\u0007b\u0002Be?\u0002\u0007\u0011q[\u0001\bI\u0016\u001cw\u000eZ3e\u0003\u001d)gnY8eK\u0012\f1\"\u001a8d_\u0012,GMT1nK\u00061QM\\2pI\u0016\fa!\u00199qK:$G\u0003\u0002B?\u0007ODqaa%f\u0001\u0004\t9\u000e\u0006\u0003\u0003~\r-\bbBB9M\u0002\u0007!Q\n\u000b\u0005\u0005{\u001ay\u000fC\u0004\u0004r\u001d\u0004\rA!\n\u0015\r\tu41_B|\u0011\u001d\u0019)\u0010\u001ba\u0001\u0003/\f\u0011b]3qCJ\fGo\u001c:\t\u000f\rE\u0004\u000e1\u0001\u0003&\u00059\u0001O]3qK:$G\u0003\u0002B?\u0007{Dqa!\u0018j\u0001\u0004\u0011i%A\u0006tiJL\u0007oU;gM&DH\u0003\u0002B?\t\u0007Aqa!\u001dk\u0001\u0004\u0011i\u0005\u0006\u0003\u0003~\u0011\u001d\u0001bBB9W\u0002\u0007!QE\u0001\u0005i\u0006\\W\r\u0006\u0003\u0003~\u00115\u0001b\u0002C\bY\u0002\u0007!\u0011B\u0001\u0002]\u0006!AM]8q)\u0011\u0011i\b\"\u0006\t\u000f\u0011=Q\u000e1\u0001\u0003\n\u0005IAM]8q%&<\u0007\u000e\u001e\u000b\u0005\u0005{\"Y\u0002C\u0004\u0005\u00109\u0004\rA!\u0003\u0002\u0013\u0011\u0014x\u000e\u001d'pG\u0006d\u0017A\u00033s_B\u001cV\r\u001e;fe\u0006QAM]8q\u001b>$W\u000f\\3\u0002\u00131|7-\u00197OC6,\u0017AC:fiR,'OT1nK\u0006Qq-\u001a;uKJt\u0015-\\3\u0002\u001b\u0015DH/\u001a8tS>tg*Y7f\u0003a!'o\u001c9Ue\u0006LGoU3ui\u0016\u00148+\u001a9be\u0006$xN]\u0001\fI\u0016\u001cw\u000eZ3e\u001d\u0006lW-\u0001\bjg>\u0003XM]1u_Jt\u0015-\\3\u0002\u00151|gnZ*ue&tw-A\u0006eK\n,xm\u0015;sS:<\u0017A\u0005;p'R\u0014\u0018N\\4XSRD7+\u001e4gSb$BA!\u0014\u0005:!91\u0011O>A\u0002\t5\u0013\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\t5\u0013\u0001C1qa\u0016tG\rV8\u0015\u0011\u0005%E1\tC'\t\u001fBq\u0001\"\u0012~\u0001\u0004!9%\u0001\u0004ck\u001a4WM\u001d\t\u0005\u0003\u007f#I%\u0003\u0003\u0005L\u0005\u0005'\u0001D*ue&twMQ;gM\u0016\u0014\bb\u0002BA{\u0002\u0007!\u0011\u0002\u0005\b\u0005\u000bk\b\u0019\u0001B\u0005)!!\u0019\u0006\"\u0016\u0005h\u0011%d\u0002\u0002Bo\t+Bq\u0001b\u0016\u007f\u0001\u0004!I&\u0001\u0002tEB!A1\fC1\u001d\u0011\t)\b\"\u0018\n\t\u0011}\u00131N\u0001\ba\u0006\u001c7.Y4f\u0013\u0011!\u0019\u0007\"\u001a\u0003\u001bM#(/\u001b8h\u0005VLG\u000eZ3s\u0015\u0011!y&a\u001b\t\u000f\t\u0005e\u00101\u0001\u0003\n!9!Q\u0011@A\u0002\t%\u0011&\u0002\u0013\u0002\u0002\u0005E\u0002\u0003\u0002B\u0010\t_JA\u0001\"\u001d\u0002\u0004\nYA+\u001a:n\u001d\u0006lW-\u00119j))\u0011i\u0002\"\u001e\u0005x\u0011eD1\u0010\u0005\t\u0005K\u000bi\u00011\u0001\u0003\n!A!qUA\u0007\u0001\u0004\u0011I\u0001\u0003\u0005\u0003\u0004\u00065\u0001\u0019\u0001B\u000f\u0011!\u0011I%!\u0004A\u0002\t5C\u0003\u0002B\u000f\t\u007fB\u0001Ba/\u0002\u001c\u0001\u0007!Q\n\u000b\u0007\u0005;!\u0019\t\"\"\t\u0011\t\u0015\u0017q\u0004a\u0001\u0005\u0013A\u0001B!3\u0002 \u0001\u0007!\u0011B\u0001\u0014GJ,\u0017\r^3D_6\u0004\u0018M\\5p]:\u000bW.\u001a\u000b\u0005\u00053#Y\t\u0003\u0005\u0003\u0004\u0006\r\u0002\u0019\u0001BM\u00035!\u0018\u0010]3ICNDG/\u00192mKV\u0011A\u0011\u0013\t\u0007\u0003k\n\u0019N!'\u0002\u0011\u0005dGNT1nKN$\"\u0001b&\u0011\r\u0011mC\u0011\u0014B\u000f\u0013\u0011!Y\n\"\u001a\u0003\u0011%#XM]1u_J\f\u0011\u0002[1tQZ\u000bG.^3\u0015\u0011\t%A\u0011\u0015CR\tKCqA!\u0001\u0013\u0001\u0004\t\t\u000eC\u0004\u0004\u0018I\u0001\rA!\u0003\t\u000f\t\u0015#\u00031\u0001\u0003\n\u00051Q-];bYN$\"\"a-\u0005,\u00125Fq\u0016CY\u0011\u001d\u0011\te\u0005a\u0001\u0005\u0013AqA!\u0001\u0014\u0001\u0004\t\t\u000eC\u0004\u0004\u0018M\u0001\rA!\u0003\t\u000f\t\u00153\u00031\u0001\u0003\n\u0005QQM\u001c;fe\u000eC\u0017M]:\u0015\u0011\u0005%Eq\u0017C]\twCqA!\u0001\u0015\u0001\u0004\t\t\u000eC\u0004\u0004\u0018Q\u0001\rA!\u0003\t\u000f\t\u0015C\u00031\u0001\u0003\n\u0005Ya.Z<UKJlg*Y7f)!\u0011i\u0002\"1\u0005D\u0012\u0015\u0007b\u0002B\u0001+\u0001\u0007\u0011\u0011\u001b\u0005\b\u0007/)\u0002\u0019\u0001B\u0005\u0011\u001d\u0011)%\u0006a\u0001\u0005\u0013!BA!\b\u0005J\"9!\u0011\u0001\fA\u0002\u0005E\u0017a\u00038foRK\b/\u001a(b[\u0016$BA!'\u0005P\"9!\u0011A\fA\u0002\u0005EGC\u0003B\u000f\t'$)\u000eb6\u0005Z\"9!\u0011\u0001\rA\u0002\u0005E\u0007bBB\f1\u0001\u0007!\u0011\u0002\u0005\b\u0005OC\u0002\u0019\u0001B\u0005\u0011\u001d\u0011I\u0005\u0007a\u0001\u0005\u001b\"\"B!'\u0005^\u0012}G\u0011\u001dCr\u0011\u001d\u0011\t!\u0007a\u0001\u0003#Dqaa\u0006\u001a\u0001\u0004\u0011I\u0001C\u0004\u0003Fe\u0001\rA!\u0003\t\u000f\t%\u0013\u00041\u0001\u0003NQ!!Q\u0004Ct\u0011\u001d\u0019iD\u0007a\u0001\u0005\u001bB3B\u0007Cv\u0003_$\t0!>\u0005vB!\u0011Q\u000fCw\u0013\u0011!y/a\u001b\u0003)\u0011,\u0007O]3dCR,Gm\u0014<feJLG-\u001b8hC\t!\u00190\u0001\u001eU_\u0002\u001a\u0018P\\2ie>t\u0017N_3-AU\u001cX\r\t1pm\u0016\u0014(/\u001b3fA\u0011,g\rI:z]\u000eD'o\u001c8ju\u0016t\u0015-\\3tAu\u0002CO];fA\u0006\u0012Aq_\u0001\u0007e9\n\u0014G\f\u0019\u0015\t\teE1 \u0005\b\u0007{Y\u0002\u0019\u0001B'Q-YB1^Ax\tc\f)\u0010\">\u0015\u0011\tuQ\u0011AC\u0007\u000b\u001fAq!b\u0001\u001d\u0001\u0004))!\u0001\u0002cgB1\u0011QOAj\u000b\u000f\u0001B!!\u001e\u0006\n%!Q1BA6\u0005\u0011\u0011\u0015\u0010^3\t\u000f\r]A\u00041\u0001\u0003\n!9!Q\t\u000fA\u0002\t%\u0011!\u00058foR+'/\u001c(b[\u0016\u001c\u0015m\u00195fIR!!QDC\u000b\u0011\u001d\u0019i$\ba\u0001\u0005\u001b\n\u0011C\\3x)f\u0004XMT1nK\u000e\u000b7\r[3e)\u0011\u0011I*b\u0007\t\u000f\rub\u00041\u0001\u0003NQA!\u0011TC\u0010\u000bC)\u0019\u0003C\u0004\u0003\u0002}\u0001\r!!5\t\u000f\r]q\u00041\u0001\u0003\n!9!QI\u0010A\u0002\t%A\u0003\u0003BM\u000bO)I#b\u000b\t\u000f\u0015\r\u0001\u00051\u0001\u0006\u0006!91q\u0003\u0011A\u0002\t%\u0001b\u0002B#A\u0001\u0007!\u0011B\u0001\u000fY>|7.\u001e9UsB,g*Y7f)\u0011\u0011I*\"\r\t\u000f\t\u0005\u0011\u00051\u0001\u0002R\u00069a*Y7f)\u0006<WCAC\u001c!\u0019)I$b\u000f\u0003&5\u0011\u0011qM\u0005\u0005\u000b{\t9G\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003-!VM]7OC6,G+Y4\u0016\u0005\u0015\r\u0003CBC\u001d\u000bw\u0011i\"\u0001\u0005UKJlg*Y7f!\u0011\u0011y\"!\u000b\u0014\t\u0005%R1\n\t\u0005\u0005?)i%\u0003\u0003\u0006P\u0005\r%!\u0005+fe6t\u0015-\\3FqR\u0014\u0018m\u0019;peR\u0011QqI\u0001\u0006CB\u0004H.\u001f\u000b\u0005\u0005;)9\u0006\u0003\u0005\u0004>\u00055\u0002\u0019\u0001B'\u0003\u001d)h.\u00199qYf$B!\"\u0018\u0006dA1\u0011QOC0\u0005\u001bJA!\"\u0019\u0002l\t1q\n\u001d;j_:D\u0001ba+\u00020\u0001\u0007!QD\u0001\f)f\u0004XMT1nKR\u000bw-\u0006\u0002\u0006jA1Q\u0011HC\u001e\u00053\u000b\u0001\u0002V=qK:\u000bW.\u001a\t\u0005\u0005?\tIf\u0005\u0003\u0002Z\u0015E\u0004\u0003\u0002B\u0010\u000bgJA!\"\u001e\u0002\u0004\n\tB+\u001f9f\u001d\u0006lW-\u0012=ue\u0006\u001cGo\u001c:\u0015\u0005\u00155D\u0003\u0002BM\u000bwB\u0001b!\u0010\u0002^\u0001\u0007!Q\n\u000b\u0005\u000b;*y\b\u0003\u0005\u0004,\u0006}\u0003\u0019\u0001BM\u0001"
)
public interface Names extends scala.reflect.api.Names {
   TermName$ TermName();

   TypeName$ TypeName();

   void scala$reflect$internal$Names$_setter_$scala$reflect$internal$Names$$nameLock_$eq(final Object x$1);

   void scala$reflect$internal$Names$_setter_$scala$reflect$internal$Names$$termHashtable_$eq(final TermName[] x$1);

   void scala$reflect$internal$Names$_setter_$scala$reflect$internal$Names$$typeHashtable_$eq(final TypeName[] x$1);

   void scala$reflect$internal$Names$_setter_$NameTag_$eq(final ClassTag x$1);

   void scala$reflect$internal$Names$_setter_$TermNameTag_$eq(final ClassTag x$1);

   void scala$reflect$internal$Names$_setter_$TypeNameTag_$eq(final ClassTag x$1);

   private int HASH_SIZE() {
      return 32768;
   }

   private int HASH_MASK() {
      return 32767;
   }

   private int NAME_SIZE() {
      return 131072;
   }

   // $FF: synthetic method
   static boolean nameDebug$(final Names $this) {
      return $this.nameDebug();
   }

   default boolean nameDebug() {
      return false;
   }

   // $FF: synthetic method
   static boolean synchronizeNames$(final Names $this) {
      return $this.synchronizeNames();
   }

   default boolean synchronizeNames() {
      return false;
   }

   Object scala$reflect$internal$Names$$nameLock();

   char[] scala$reflect$internal$Names$$_chrs();

   void scala$reflect$internal$Names$$_chrs_$eq(final char[] x$1);

   // $FF: synthetic method
   static char[] chrs$(final Names $this) {
      return $this.chrs();
   }

   /** @deprecated */
   default char[] chrs() {
      return this.scala$reflect$internal$Names$$_chrs();
   }

   // $FF: synthetic method
   static void chrs_$eq$(final Names $this, final char[] cs) {
      $this.chrs_$eq(cs);
   }

   /** @deprecated */
   default void chrs_$eq(final char[] cs) {
      this.scala$reflect$internal$Names$$_chrs_$eq(cs);
   }

   int scala$reflect$internal$Names$$nc();

   void scala$reflect$internal$Names$$nc_$eq(final int x$1);

   // $FF: synthetic method
   static int nameTableSize$(final Names $this) {
      return $this.nameTableSize();
   }

   default int nameTableSize() {
      return this.scala$reflect$internal$Names$$nc();
   }

   TermName[] scala$reflect$internal$Names$$termHashtable();

   TypeName[] scala$reflect$internal$Names$$typeHashtable();

   // $FF: synthetic method
   static Iterator allNames$(final Names $this) {
      return $this.allNames();
   }

   default Iterator allNames() {
      return .MODULE$.iterator$extension(this.scala$reflect$internal$Names$$termHashtable()).filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$allNames$1(x$1))).flatMap((n) -> {
         Iterator var10000 = scala.package..MODULE$.Iterator();
         Function1 iterate_f = (x$2) -> x$2.next();
         if (var10000 == null) {
            throw null;
         } else {
            Iterator..anon.26 var3 = new Iterator..anon.26(n, iterate_f);
            Object var2 = null;
            return var3.takeWhile((x$3) -> BoxesRunTime.boxToBoolean($anonfun$allNames$4(x$3)));
         }
      });
   }

   // $FF: synthetic method
   static int scala$reflect$internal$Names$$hashValue$(final Names $this, final char[] cs, final int offset, final int len) {
      return $this.scala$reflect$internal$Names$$hashValue(cs, offset, len);
   }

   default int scala$reflect$internal$Names$$hashValue(final char[] cs, final int offset, final int len) {
      int h = 0;

      for(int i = 0; i < len; ++i) {
         h = 31 * h + cs[i + offset];
      }

      return h;
   }

   private boolean equals(final int index, final char[] cs, final int offset, final int len) {
      int i = 0;

      for(char[] chrs = this.scala$reflect$internal$Names$$_chrs(); i < len && chrs[index + i] == cs[offset + i]; ++i) {
      }

      return i == len;
   }

   private void enterChars(final char[] cs, final int offset, final int len) {
      for(int i = 0; i < len; ++i) {
         if (this.scala$reflect$internal$Names$$nc() + i == this.scala$reflect$internal$Names$$_chrs().length) {
            char[] newchrs = new char[this.scala$reflect$internal$Names$$_chrs().length * 2];
            System.arraycopy(this.scala$reflect$internal$Names$$_chrs(), 0, newchrs, 0, this.scala$reflect$internal$Names$$_chrs().length);
            this.scala$reflect$internal$Names$$_chrs_$eq(newchrs);
         }

         this.scala$reflect$internal$Names$$_chrs()[this.scala$reflect$internal$Names$$nc() + i] = cs[offset + i];
      }

      if (len == 0) {
         this.scala$reflect$internal$Names$$nc_$eq(this.scala$reflect$internal$Names$$nc() + 1);
      } else {
         this.scala$reflect$internal$Names$$nc_$eq(this.scala$reflect$internal$Names$$nc() + len);
      }
   }

   // $FF: synthetic method
   static TermName newTermName$(final Names $this, final char[] cs, final int offset, final int len) {
      return $this.newTermName(cs, offset, len);
   }

   default TermName newTermName(final char[] cs, final int offset, final int len) {
      return this.newTermName(cs, offset, len, (String)null);
   }

   // $FF: synthetic method
   static TermName newTermName$(final Names $this, final char[] cs) {
      return $this.newTermName(cs);
   }

   default TermName newTermName(final char[] cs) {
      int newTermName_len = cs.length;
      int newTermName_offset = 0;
      return this.newTermName(cs, newTermName_offset, newTermName_len, (String)null);
   }

   // $FF: synthetic method
   static TypeName newTypeName$(final Names $this, final char[] cs) {
      return $this.newTypeName(cs);
   }

   default TypeName newTypeName(final char[] cs) {
      return this.newTypeName((char[])cs, 0, cs.length);
   }

   // $FF: synthetic method
   static TermName newTermName$(final Names $this, final char[] cs, final int offset, final int len0, final String cachedString) {
      return $this.newTermName(cs, offset, len0, cachedString);
   }

   default TermName newTermName(final char[] cs, final int offset, final int len0, final String cachedString) {
      if (this.synchronizeNames()) {
         synchronized(this.scala$reflect$internal$Names$$nameLock()){}

         TermName var6;
         try {
            var6 = this.body$1(offset, len0, cs, cachedString);
         } catch (Throwable var8) {
            throw var8;
         }

         return var6;
      } else {
         return this.body$1(offset, len0, cs, cachedString);
      }
   }

   // $FF: synthetic method
   static TypeName newTypeName$(final Names $this, final char[] cs, final int offset, final int len, final String cachedString) {
      return $this.newTypeName(cs, offset, len, cachedString);
   }

   default TypeName newTypeName(final char[] cs, final int offset, final int len, final String cachedString) {
      return this.newTermName(cs, offset, len, cachedString).toTypeName();
   }

   // $FF: synthetic method
   static TermName newTermName$(final Names $this, final String s) {
      return $this.newTermName(s);
   }

   default TermName newTermName(final String s) {
      return this.newTermName(s.toCharArray(), 0, s.length(), (String)null);
   }

   // $FF: synthetic method
   static TypeName newTypeName$(final Names $this, final String s) {
      return $this.newTypeName(s);
   }

   default TypeName newTypeName(final String s) {
      return this.newTermName(s).toTypeName();
   }

   // $FF: synthetic method
   static TermName newTermName$(final Names $this, final byte[] bs, final int offset, final int len) {
      return $this.newTermName(bs, offset, len);
   }

   default TermName newTermName(final byte[] bs, final int offset, final int len) {
      char[] chars = scala.io.Codec..MODULE$.fromUTF8(bs, offset, len);
      int newTermName_len = chars.length;
      int newTermName_offset = 0;
      return this.newTermName(chars, newTermName_offset, newTermName_len, (String)null);
   }

   // $FF: synthetic method
   static TermName newTermNameCached$(final Names $this, final String s) {
      return $this.newTermNameCached(s);
   }

   default TermName newTermNameCached(final String s) {
      return this.newTermName(s.toCharArray(), 0, s.length(), s);
   }

   // $FF: synthetic method
   static TypeName newTypeNameCached$(final Names $this, final String s) {
      return $this.newTypeNameCached(s);
   }

   default TypeName newTypeNameCached(final String s) {
      return this.newTypeName(s.toCharArray(), 0, s.length(), s);
   }

   // $FF: synthetic method
   static TypeName newTypeName$(final Names $this, final char[] cs, final int offset, final int len) {
      return $this.newTypeName(cs, offset, len);
   }

   default TypeName newTypeName(final char[] cs, final int offset, final int len) {
      return this.newTermName(cs, offset, len, (String)null).toTypeName();
   }

   // $FF: synthetic method
   static TypeName newTypeName$(final Names $this, final byte[] bs, final int offset, final int len) {
      return $this.newTypeName(bs, offset, len);
   }

   default TypeName newTypeName(final byte[] bs, final int offset, final int len) {
      return this.newTermName(bs, offset, len).toTypeName();
   }

   // $FF: synthetic method
   static TypeName lookupTypeName$(final Names $this, final char[] cs) {
      return $this.lookupTypeName(cs);
   }

   default TypeName lookupTypeName(final char[] cs) {
      int hash = this.scala$reflect$internal$Names$$hashValue(cs, 0, cs.length) & 32767;

      TypeName typeName;
      for(typeName = this.scala$reflect$internal$Names$$typeHashtable()[hash]; typeName != null && (typeName.length() != cs.length || !this.equals(typeName.start(), cs, 0, cs.length)); typeName = typeName.next()) {
      }

      if (typeName == null) {
         throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append($anonfun$lookupTypeName$1(cs)).toString());
      } else {
         return typeName;
      }
   }

   ClassTag NameTag();

   ClassTag TermNameTag();

   ClassTag TypeNameTag();

   // $FF: synthetic method
   static boolean $anonfun$allNames$1(final TermName x$1) {
      return x$1 != null;
   }

   // $FF: synthetic method
   static boolean $anonfun$allNames$4(final TermName x$3) {
      return x$3 != null;
   }

   // $FF: synthetic method
   static String $anonfun$newTermName$1(final int offset$1) {
      return (new StringBuilder(33)).append("offset must be non-negative, got ").append(offset$1).toString();
   }

   private TermName body$1(final int offset$1, final int len0$1, final char[] cs$1, final String cachedString$1) {
      if (offset$1 < 0) {
         throw new IllegalArgumentException((new StringBuilder(20)).append("requirement failed: ").append($anonfun$newTermName$1(offset$1)).toString());
      } else {
         package var10000 = scala.math.package..MODULE$;
         int max_y = 0;
         int len = Math.max(len0$1, max_y);
         int h = this.scala$reflect$internal$Names$$hashValue(cs$1, offset$1, len) & 32767;

         TermName n;
         for(n = this.scala$reflect$internal$Names$$termHashtable()[h]; n != null && (n.length() != len || !this.equals(n.start(), cs$1, offset$1, len)); n = n.next()) {
         }

         if (n != null) {
            return n;
         } else {
            int startIndex;
            if (cs$1 == this.scala$reflect$internal$Names$$_chrs()) {
               startIndex = offset$1;
            } else {
               startIndex = this.scala$reflect$internal$Names$$nc();
               this.enterChars(cs$1, offset$1, len);
            }

            TermName next = this.scala$reflect$internal$Names$$termHashtable()[h];
            TermName termName = new TermName(startIndex, len, next, cachedString$1);
            this.scala$reflect$internal$Names$$termHashtable()[h] = termName;
            return termName;
         }
      }
   }

   // $FF: synthetic method
   static String $anonfun$lookupTypeName$1(final char[] cs$2) {
      return (new StringBuilder(26)).append("TypeName ").append(new String(cs$2)).append(" not yet created.").toString();
   }

   static void $init$(final Names $this) {
      $this.scala$reflect$internal$Names$_setter_$scala$reflect$internal$Names$$nameLock_$eq(new Object());
      $this.scala$reflect$internal$Names$$_chrs_$eq(new char[131072]);
      $this.scala$reflect$internal$Names$$nc_$eq(0);
      $this.scala$reflect$internal$Names$_setter_$scala$reflect$internal$Names$$termHashtable_$eq(new TermName['耀']);
      $this.scala$reflect$internal$Names$_setter_$scala$reflect$internal$Names$$typeHashtable_$eq(new TypeName['耀']);
      $this.scala$reflect$internal$Names$_setter_$NameTag_$eq(scala.reflect.ClassTag..MODULE$.apply(Name.class));
      $this.scala$reflect$internal$Names$_setter_$TermNameTag_$eq(scala.reflect.ClassTag..MODULE$.apply(TermName.class));
      $this.scala$reflect$internal$Names$_setter_$TypeNameTag_$eq(scala.reflect.ClassTag..MODULE$.apply(TypeName.class));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public abstract class Name extends scala.reflect.api.Names.NameApi implements NameHasIsEmpty, CharSequence {
      private final int index;
      private final int len;
      private final String cachedString;

      public IntStream chars() {
         return super.chars();
      }

      public IntStream codePoints() {
         return super.codePoints();
      }

      public int index() {
         return this.index;
      }

      public int len() {
         return this.len;
      }

      public String cachedString() {
         return this.cachedString;
      }

      public abstract Name thisName();

      public final int start() {
         return this.index();
      }

      public abstract Name next();

      public final int length() {
         return this.len();
      }

      public final boolean nonEmpty() {
         return !this.isEmpty();
      }

      public final boolean isEmpty() {
         return this.len() == 0;
      }

      public abstract String nameKind();

      public abstract boolean isTermName();

      public abstract boolean isTypeName();

      public abstract TermName toTermName();

      public abstract TypeName toTypeName();

      public abstract Name companionName();

      public final Name asTypeOf(final Name other) {
         return (Name)(other.isTermName() ? this.toTermName() : this.toTypeName());
      }

      public abstract Name subName(final int from, final int to);

      public CharSequence subSequence(final int from, final int to) {
         return this.subName(from, to);
      }

      public abstract Name newName(final String str);

      public Name mapName(final Function1 f) {
         return this.newName((String)f.apply(this.toString()));
      }

      public final void copyChars(final char[] cs, final int offset) {
         System.arraycopy(this.scala$reflect$internal$Names$Name$$$outer().scala$reflect$internal$Names$$_chrs(), this.index(), cs, offset, this.len());
      }

      public final char[] toChars() {
         char[] cs = new char[this.len()];
         this.copyChars(cs, 0);
         return cs;
      }

      public final int hashCode() {
         return this.index();
      }

      public boolean string_$eq$eq(final Name that) {
         if (that != null) {
            String var10000 = this.toString();
            String var2 = that.toString();
            if (var10000 == null) {
               if (var2 == null) {
                  return true;
               }
            } else if (var10000.equals(var2)) {
               return true;
            }
         }

         return false;
      }

      public boolean string_$eq$eq(final String that) {
         if (that != null) {
            String var10000 = this.toString();
            if (var10000 != null) {
               if (var10000.equals(that)) {
                  return true;
               }
            }
         }

         return false;
      }

      public final char charAt(final int i) {
         return this.scala$reflect$internal$Names$Name$$$outer().scala$reflect$internal$Names$$_chrs()[this.index() + i];
      }

      public final int pos(final char c) {
         return this.pos(c, 0);
      }

      public final int pos(final String s) {
         return this.pos(s, 0);
      }

      public final int pos(final char c, final int start) {
         int i = start;

         for(char[] chrs = this.scala$reflect$internal$Names$Name$$$outer().scala$reflect$internal$Names$$_chrs(); i < this.len() && chrs[this.index() + i] != c; ++i) {
         }

         return i;
      }

      public final int pos(final String s, final int start) {
         int i = this.pos(s.charAt(0), start);
         int sLen = s.length();
         if (sLen == 1) {
            return i;
         } else {
            for(char[] chrs = this.scala$reflect$internal$Names$Name$$$outer().scala$reflect$internal$Names$$_chrs(); i + sLen <= this.len(); i = this.pos(s.charAt(0), i + 1)) {
               int j = 1;

               while(s.charAt(j) == chrs[this.index() + i + j]) {
                  ++j;
                  if (j == sLen) {
                     return i;
                  }
               }
            }

            return this.len();
         }
      }

      public final int lastPos(final char c) {
         return this.lastPos(c, this.len() - 1);
      }

      public final int lastPos(final char c, final int start) {
         int i = start;

         for(char[] chrs = this.scala$reflect$internal$Names$Name$$$outer().scala$reflect$internal$Names$$_chrs(); i >= 0 && chrs[this.index() + i] != c; --i) {
         }

         return i;
      }

      public final boolean startsWith(final Name prefix) {
         return this.startsWith((Name)prefix, 0);
      }

      public final boolean startsWith(final Name prefix, final int start) {
         char[] chrs = this.scala$reflect$internal$Names$Name$$$outer().scala$reflect$internal$Names$$_chrs();

         for(int i = 0; prefix != null; ++i) {
            if (i >= prefix.len() || start + i >= this.len() || chrs[this.index() + start + i] != chrs[prefix.index() + i]) {
               return i == prefix.len();
            }
         }

         throw null;
      }

      public final boolean startsWith(final String prefix, final int start) {
         char[] chrs = this.scala$reflect$internal$Names$Name$$$outer().scala$reflect$internal$Names$$_chrs();

         int i;
         for(i = 0; i < prefix.length() && start + i < this.len() && chrs[this.index() + start + i] == prefix.charAt(i); ++i) {
         }

         return i == prefix.length();
      }

      public final boolean endsWith(final Name suffix) {
         return this.endsWith(suffix, this.len());
      }

      public final boolean endsWith(final Name suffix, final int end) {
         int i = 1;

         for(char[] chrs = this.scala$reflect$internal$Names$Name$$$outer().scala$reflect$internal$Names$$_chrs(); suffix != null; ++i) {
            if (i > suffix.len() || i > end || chrs[this.index() + end - i] != chrs[suffix.index() + suffix.len() - i]) {
               return i > suffix.len();
            }
         }

         throw null;
      }

      public final boolean endsWith(final String suffix, final int end) {
         int i = 1;

         for(char[] chrs = this.scala$reflect$internal$Names$Name$$$outer().scala$reflect$internal$Names$$_chrs(); i <= suffix.length() && i <= end && chrs[this.index() + end - i] == suffix.charAt(suffix.length() - i); ++i) {
         }

         return i > suffix.length();
      }

      public final boolean containsName(final String subname) {
         return this.containsName((Name)this.scala$reflect$internal$Names$Name$$$outer().newTermName(subname));
      }

      public final boolean containsName(final Name subname) {
         int start = 0;
         int var10000 = this.len();
         if (subname == null) {
            throw null;
         } else {
            int last;
            for(last = var10000 - subname.len(); start <= last && !this.startsWith(subname, start); ++start) {
            }

            return start <= last;
         }
      }

      public final boolean containsChar(final char ch) {
         int i = this.index();
         int max = this.index() + this.len();

         for(char[] chrs = this.scala$reflect$internal$Names$Name$$$outer().scala$reflect$internal$Names$$_chrs(); i < max; ++i) {
            if (chrs[i] == ch) {
               return true;
            }
         }

         return false;
      }

      public int lastIndexOf(final String s) {
         if (s.isEmpty()) {
            return this.len();
         } else {
            int lastIndex = s.length() - 1;
            char lastChar = s.charAt(lastIndex);
            char[] contents = this.scala$reflect$internal$Names$Name$$$outer().scala$reflect$internal$Names$$_chrs();
            int base = this.index();
            int min = base + lastIndex;

            for(int end = base + this.len() - 1; end >= min; --end) {
               if (contents[end] == lastChar) {
                  int i = end - 1;
                  int i0 = i - lastIndex;

                  for(int at = lastIndex - 1; i > i0 && contents[i] == s.charAt(at); --at) {
                     --i;
                  }

                  if (i == i0) {
                     return i0 + 1 - base;
                  }
               }
            }

            return -1;
         }
      }

      public final char startChar() {
         return this.charAt(0);
      }

      public final char endChar() {
         return this.charAt(this.len() - 1);
      }

      public final boolean startsWith(final char char) {
         return this.len() > 0 && this.charAt(0) == char;
      }

      public final boolean startsWith(final String name) {
         return this.startsWith((String)name, 0);
      }

      public final boolean endsWith(final char char) {
         return this.len() > 0 && this.endChar() == char;
      }

      public final boolean endsWith(final String name) {
         return this.endsWith(name, this.len());
      }

      private int fixIndexOf(final int idx) {
         return idx == this.len() ? -1 : idx;
      }

      public int indexOf(final char ch) {
         return this.fixIndexOf(this.pos(ch, 0));
      }

      public int indexOf(final char ch, final int fromIndex) {
         return this.fixIndexOf(this.pos(ch, fromIndex));
      }

      public int indexOf(final String s) {
         return this.fixIndexOf(this.pos(s, 0));
      }

      public int lastIndexOf(final char ch) {
         return this.lastPos(ch);
      }

      public Name replace(final char from, final char to) {
         char[] cs = new char[this.len()];

         for(int i = 0; i < this.len(); ++i) {
            char ch = this.charAt(i);
            cs[i] = ch == from ? to : ch;
         }

         Names var10000 = this.scala$reflect$internal$Names$Name$$$outer();
         int newTermName_len = this.len();
         byte newTermName_offset = 0;
         if (var10000 == null) {
            throw null;
         } else {
            return var10000.newTermName(cs, newTermName_offset, newTermName_len, (String)null);
         }
      }

      public String decoded() {
         return this.decode();
      }

      public String encoded() {
         return String.valueOf(this.encode());
      }

      public Name encodedName() {
         return this.encode();
      }

      public Name encode() {
         String str = this.toString();
         String res = scala.reflect.NameTransformer..MODULE$.encode(str);
         if (res == null) {
            if (str == null) {
               return this.thisName();
            }
         } else if (res.equals(str)) {
            return this.thisName();
         }

         return this.newName(res);
      }

      public String decode() {
         if (!this.containsChar('$')) {
            return this.toString();
         } else {
            String str = this.toString();
            String res = scala.reflect.NameTransformer..MODULE$.decode(str);
            if (res == null) {
               if (str == null) {
                  return str;
               }
            } else if (res.equals(str)) {
               return str;
            }

            return res;
         }
      }

      public Name append(final char ch) {
         return this.newName((new StringBuilder(0)).append(this.toString()).append(ch).toString());
      }

      public Name append(final String suffix) {
         return this.newName((new StringBuilder(0)).append(this.toString()).append(suffix).toString());
      }

      public Name append(final Name suffix) {
         return this.newName((new StringBuilder(0)).append(this.toString()).append(suffix).toString());
      }

      public Name append(final char separator, final Name suffix) {
         return this.newName((new StringBuilder(0)).append(this.toString()).append(separator).append(suffix).toString());
      }

      public Name prepend(final String prefix) {
         return this.newName((new StringBuilder(0)).append(prefix).append(this).toString());
      }

      public Name stripSuffix(final String suffix) {
         return this.endsWith(suffix) ? this.dropRight(suffix.length()) : this.thisName();
      }

      public Name stripSuffix(final Name suffix) {
         if (this.endsWith(suffix)) {
            if (suffix == null) {
               throw null;
            } else {
               return this.dropRight(suffix.len());
            }
         } else {
            return this.thisName();
         }
      }

      public Name take(final int n) {
         return this.subName(0, n);
      }

      public Name drop(final int n) {
         return this.subName(n, this.len());
      }

      public Name dropRight(final int n) {
         return this.subName(0, this.len() - n);
      }

      public TermName dropLocal() {
         return (TermName)this.toTermName().stripSuffix(" ");
      }

      public TermName dropSetter() {
         return (TermName)this.toTermName().stripSuffix("_$eq");
      }

      public Name dropModule() {
         return this.stripSuffix("$");
      }

      public TermName localName() {
         return (TermName)this.getterName().append(" ");
      }

      public TermName setterName() {
         return (TermName)this.getterName().append("_$eq");
      }

      public TermName getterName() {
         return this.dropTraitSetterSeparator().dropSetter().dropLocal();
      }

      public TermName extensionName() {
         return this.append("$extension").toTermName();
      }

      private TermName dropTraitSetterSeparator() {
         int var1 = this.indexOf("$_setter_$");
         switch (var1) {
            case -1:
               return this.toTermName();
            default:
               return (TermName)this.toTermName().drop(var1 + "$_setter_$".length());
         }
      }

      public Name decodedName() {
         return this.newName(this.decode());
      }

      public boolean isOperatorName() {
         String var10000 = this.decode();
         String var1 = this.toString();
         if (var10000 == null) {
            if (var1 != null) {
               return true;
            }
         } else if (!var10000.equals(var1)) {
            return true;
         }

         return false;
      }

      public String longString() {
         return (new StringBuilder(1)).append(this.nameKind()).append(" ").append(this.decode()).toString();
      }

      public String debugString() {
         return this.isTypeName() ? (new StringBuilder(1)).append(this.decode()).append("!").toString() : this.decode();
      }

      public final String toStringWithSuffix(final String suffix) {
         StringBuilder builder = new StringBuilder(this.len() + suffix.length());
         builder.append(this);
         builder.append(suffix);
         return builder.toString();
      }

      public final String toString() {
         return this.cachedString() == null ? new String(this.scala$reflect$internal$Names$Name$$$outer().scala$reflect$internal$Names$$_chrs(), this.index(), this.len()) : this.cachedString();
      }

      public final void appendTo(final StringBuffer buffer, final int start, final int length) {
         buffer.append(this.scala$reflect$internal$Names$Name$$$outer().scala$reflect$internal$Names$$_chrs(), this.index() + start, length);
      }

      public final scala.collection.mutable.StringBuilder appendTo(final scala.collection.mutable.StringBuilder sb, final int start, final int length) {
         return sb.appendAll(this.scala$reflect$internal$Names$Name$$$outer().scala$reflect$internal$Names$$_chrs(), this.index() + start, length);
      }

      // $FF: synthetic method
      public Names scala$reflect$internal$Names$Name$$$outer() {
         return (Names)this.$outer;
      }

      public Name(final int index, final int len, final String cachedString) {
         this.index = index;
         this.len = len;
         this.cachedString = cachedString;
      }
   }

   public final class TermName extends Name implements scala.reflect.api.Names.TermNameApi {
      private final TermName next;

      public TermName next() {
         return this.next;
      }

      public TermName thisName() {
         return this;
      }

      public boolean isTermName() {
         return true;
      }

      public boolean isTypeName() {
         return false;
      }

      public TermName toTermName() {
         return this;
      }

      public TypeName toTypeName() {
         if (this.scala$reflect$internal$Names$TermName$$$outer().synchronizeNames()) {
            synchronized(this.scala$reflect$internal$Names$TermName$$$outer().scala$reflect$internal$Names$$nameLock()){}

            TypeName var2;
            try {
               var2 = this.body$2();
            } catch (Throwable var4) {
               throw var4;
            }

            return var2;
         } else {
            return this.body$2();
         }
      }

      public TermName newName(final String str) {
         return this.scala$reflect$internal$Names$TermName$$$outer().newTermName(str);
      }

      public TypeName companionName() {
         return this.toTypeName();
      }

      public TermName subName(final int from, final int to) {
         Names var10000 = this.scala$reflect$internal$Names$TermName$$$outer();
         char[] var10001 = this.scala$reflect$internal$Names$TermName$$$outer().scala$reflect$internal$Names$$_chrs();
         int var10002 = ((Name)this).index() + from;
         int newTermName_len = to - from;
         int newTermName_offset = var10002;
         char[] newTermName_cs = var10001;
         if (var10000 == null) {
            throw null;
         } else {
            return var10000.newTermName(newTermName_cs, newTermName_offset, newTermName_len, (String)null);
         }
      }

      public String nameKind() {
         return "term";
      }

      private TypeName createCompanionName(final TypeName next) {
         return this.scala$reflect$internal$Names$TermName$$$outer().new TypeName(this.index(), this.len(), next, super.cachedString());
      }

      // $FF: synthetic method
      public Names scala$reflect$internal$Names$TermName$$$outer() {
         return (Names)this.$outer;
      }

      private final TypeName body$2() {
         int h = this.scala$reflect$internal$Names$TermName$$$outer().scala$reflect$internal$Names$$hashValue(this.scala$reflect$internal$Names$TermName$$$outer().scala$reflect$internal$Names$$_chrs(), this.index(), this.len()) & 32767;

         TypeName n;
         for(n = this.scala$reflect$internal$Names$TermName$$$outer().scala$reflect$internal$Names$$typeHashtable()[h]; n != null && n.start() != this.index(); n = n.next()) {
         }

         if (n != null) {
            return n;
         } else {
            TypeName next = this.scala$reflect$internal$Names$TermName$$$outer().scala$reflect$internal$Names$$typeHashtable()[h];
            TypeName typeName = this.createCompanionName(next);
            this.scala$reflect$internal$Names$TermName$$$outer().scala$reflect$internal$Names$$typeHashtable()[h] = typeName;
            return typeName;
         }
      }

      public TermName(final int index0, final int len0, final TermName next, final String cachedString) {
         super(index0, len0, cachedString);
         this.next = next;
      }
   }

   public class TermName$ extends scala.reflect.api.Names.TermNameExtractor {
      public TermName apply(final String s) {
         return this.scala$reflect$internal$Names$TermName$$$outer().newTermName(s);
      }

      public Option unapply(final TermName name) {
         return new Some(name.toString());
      }

      // $FF: synthetic method
      public Names scala$reflect$internal$Names$TermName$$$outer() {
         return (Names)this.$outer;
      }
   }

   public final class TypeName extends Name implements scala.reflect.api.Names.TypeNameApi {
      private final TypeName next;

      public TypeName next() {
         return this.next;
      }

      public TypeName thisName() {
         return this;
      }

      public boolean isTermName() {
         return false;
      }

      public boolean isTypeName() {
         return true;
      }

      public TermName toTermName() {
         if (this.scala$reflect$internal$Names$TypeName$$$outer().synchronizeNames()) {
            synchronized(this.scala$reflect$internal$Names$TypeName$$$outer().scala$reflect$internal$Names$$nameLock()){}

            TermName var2;
            try {
               var2 = this.body$3();
            } catch (Throwable var4) {
               throw var4;
            }

            return var2;
         } else {
            return this.body$3();
         }
      }

      public TypeName toTypeName() {
         return this;
      }

      public TypeName newName(final String str) {
         return this.scala$reflect$internal$Names$TypeName$$$outer().newTypeName(str);
      }

      public TermName companionName() {
         return this.toTermName();
      }

      public TypeName subName(final int from, final int to) {
         return this.scala$reflect$internal$Names$TypeName$$$outer().newTypeName(this.scala$reflect$internal$Names$TypeName$$$outer().scala$reflect$internal$Names$$_chrs(), ((Name)this).index() + from, to - from);
      }

      public String nameKind() {
         return "type";
      }

      public String decode() {
         return super.decode();
      }

      // $FF: synthetic method
      public Names scala$reflect$internal$Names$TypeName$$$outer() {
         return (Names)this.$outer;
      }

      // $FF: synthetic method
      public static final String $anonfun$toTermName$1(final TypeName $this) {
         return (new StringBuilder(38)).append("TypeName ").append($this).append(" is missing its correspondent").toString();
      }

      private final TermName body$3() {
         int h = this.scala$reflect$internal$Names$TypeName$$$outer().scala$reflect$internal$Names$$hashValue(this.scala$reflect$internal$Names$TypeName$$$outer().scala$reflect$internal$Names$$_chrs(), this.index(), this.len()) & 32767;

         TermName n;
         for(n = this.scala$reflect$internal$Names$TypeName$$$outer().scala$reflect$internal$Names$$termHashtable()[h]; n != null && n.start() != this.index(); n = n.next()) {
         }

         if (n == null) {
            throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append($anonfun$toTermName$1(this)).toString());
         } else {
            return n;
         }
      }

      public TypeName(final int index0, final int len0, final TypeName next, final String cachedString) {
         super(index0, len0, cachedString);
         this.next = next;
      }
   }

   public class TypeName$ extends scala.reflect.api.Names.TypeNameExtractor {
      public TypeName apply(final String s) {
         return this.scala$reflect$internal$Names$TypeName$$$outer().newTypeName(s);
      }

      public Option unapply(final TypeName name) {
         return new Some(name.toString());
      }

      // $FF: synthetic method
      public Names scala$reflect$internal$Names$TypeName$$$outer() {
         return (Names)this.$outer;
      }
   }

   public interface NameHasIsEmpty {
      boolean isEmpty();
   }
}

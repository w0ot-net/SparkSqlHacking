package scala.reflect.internal;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.LinearSeqOps;
import scala.collection.MapFactory;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.immutable.List;
import scala.collection.immutable.ListMap;
import scala.collection.immutable.Nil.;
import scala.collection.mutable.ArraySeq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Annotations;
import scala.reflect.internal.util.Position;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015\u0015g\u0001DA:\u0003k\u0002\n1!\u0001\u0002\u0004\u0016u\u0006bBAM\u0001\u0011\u0005\u00111\u0014\u0004\n\u0003G\u0003\u0001\u0013aA\u0001\u0003KCq!!'\u0003\t\u0003\tY\nC\u0004\u0002*\n1\t!a+\t\u000f\u00115!A\"\u0001\u0005\u0010!9A1\u0004\u0002\u0007\u0002\u0011u\u0001b\u0002C\u0011\u0005\u0019\u0005A1\u0005\u0005\b\tS\u0011a\u0011\u0001C\u0016\u0011\u001d!iC\u0001C\u0001\t_Aq\u0001b\r\u0003\t\u0003!)\u0004C\u0004\u0005<\t!\t\u0001\"\u0010\t\u000f\u0011\r#\u0001\"\u0001\u0005F!9A1\n\u0002\u0005\u0002\u00115\u0003b\u0002C)\u0005\u0019\u0005A1\u000b\u0005\b\t3\u0012A\u0011\u0002C.\r\u001d\u0011\u0019\u0001AA\u0011\u0005\u000bAq!!3\u0011\t\u0003\u0011\u0019\"\u0002\u0004\u0005n\u0001\u0001!\u0011\u0001\u0005\n\t_\u0002!\u0019!C\u0002\tc:q\u0001b\u001f\u0001\u0011\u0003\u001b9CB\u0004\u0004\"\u0001A\tia\t\t\u000f\u0005%W\u0003\"\u0001\u0004&!I!qN\u000b\u0002\u0002\u0013\u0005#\u0011\u000f\u0005\n\u0005\u0003+\u0012\u0011!C\u0001\u0005\u0007C\u0011Ba#\u0016\u0003\u0003%\ta!\u000b\t\u0013\teU#!A\u0005B\tm\u0005\"\u0003BU+\u0005\u0005I\u0011AB\u0017\u0011%\u0011Y,FA\u0001\n\u0003\u0012i\fC\u0005\u0003>U\t\t\u0011\"\u0011\u0004\b\u00191!Q\u0019\u0001A\u0005\u000fD!B!3\u001f\u0005+\u0007I\u0011\u0001Bf\u0011)\u00119N\bB\tB\u0003%!Q\u001a\u0005\b\u0003\u0013tB\u0011\u0001Bm\u0011\u001d\u0011iD\bC!\u0005\u007fA\u0011B!\u0015\u001f\u0003\u0003%\tAa8\t\u0013\t]c$%A\u0005\u0002\t\r\b\"\u0003B8=\u0005\u0005I\u0011\tB9\u0011%\u0011\tIHA\u0001\n\u0003\u0011\u0019\tC\u0005\u0003\fz\t\t\u0011\"\u0001\u0003h\"I!\u0011\u0014\u0010\u0002\u0002\u0013\u0005#1\u0014\u0005\n\u0005Ss\u0012\u0011!C\u0001\u0005WD\u0011B!.\u001f\u0003\u0003%\tEa<\t\u0013\tmf$!A\u0005B\tu\u0006\"\u0003B`=\u0005\u0005I\u0011\tBz\u000f%!i\bAA\u0001\u0012\u0003!yHB\u0005\u0003F\u0002\t\t\u0011#\u0001\u0005\u0002\"9\u0011\u0011\u001a\u0018\u0005\u0002\u0011e\u0005\"\u0003B\u001f]\u0005\u0005IQIB\u0004\u0011%!YJLA\u0001\n\u0003#i\nC\u0005\u0005\":\n\t\u0011\"!\u0005$\u001a1!q\u0003\u0001A\u00053A!\"a74\u0005+\u0007I\u0011\u0001B\u0017\u0011)\u0011)d\rB\tB\u0003%!q\u0006\u0005\b\u0003\u0013\u001cD\u0011\u0001B\u001c\u0011\u001d\u0011id\rC!\u0005\u007fA\u0011B!\u00154\u0003\u0003%\tAa\u0015\t\u0013\t]3'%A\u0005\u0002\te\u0003\"\u0003B8g\u0005\u0005I\u0011\tB9\u0011%\u0011\tiMA\u0001\n\u0003\u0011\u0019\tC\u0005\u0003\fN\n\t\u0011\"\u0001\u0003\u000e\"I!\u0011T\u001a\u0002\u0002\u0013\u0005#1\u0014\u0005\n\u0005S\u001b\u0014\u0011!C\u0001\u0005WC\u0011B!.4\u0003\u0003%\tEa.\t\u0013\tm6'!A\u0005B\tu\u0006\"\u0003B`g\u0005\u0005I\u0011\tBa\u000f%!I\u000bAA\u0001\u0012\u0003!YKB\u0005\u0003\u0018\u0001\t\t\u0011#\u0001\u0005.\"9\u0011\u0011Z\"\u0005\u0002\u0011E\u0006\"\u0003B\u001f\u0007\u0006\u0005IQIB\u0004\u0011%!YjQA\u0001\n\u0003#\u0019\fC\u0005\u0005\"\u000e\u000b\t\u0011\"!\u00058\u001a1!q\u001f\u0001A\u0005sD!Ba?I\u0005+\u0007I\u0011\u0001B\u007f\u0011)\u0011y\u0010\u0013B\tB\u0003%\u00111\u0018\u0005\b\u0003\u0013DE\u0011AB\u0001\u0011\u001d\u0011i\u0004\u0013C!\u0007\u000fA\u0011B!\u0015I\u0003\u0003%\ta!\u0003\t\u0013\t]\u0003*%A\u0005\u0002\r5\u0001\"\u0003B8\u0011\u0006\u0005I\u0011\tB9\u0011%\u0011\t\tSA\u0001\n\u0003\u0011\u0019\tC\u0005\u0003\f\"\u000b\t\u0011\"\u0001\u0004\u0012!I!\u0011\u0014%\u0002\u0002\u0013\u0005#1\u0014\u0005\n\u0005SC\u0015\u0011!C\u0001\u0007+A\u0011B!.I\u0003\u0003%\te!\u0007\t\u0013\tm\u0006*!A\u0005B\tu\u0006\"\u0003B`\u0011\u0006\u0005I\u0011IB\u000f\u000f%!i\fAA\u0001\u0012\u0003!yLB\u0005\u0003x\u0002\t\t\u0011#\u0001\u0005B\"9\u0011\u0011\u001a-\u0005\u0002\u0011\u0015\u0007\"\u0003B\u001f1\u0006\u0005IQIB\u0004\u0011%!Y\nWA\u0001\n\u0003#9\rC\u0005\u0005\"b\u000b\t\u0011\"!\u0005L\u001e9Aq\u001a\u0001\t\u0002\u0011EgaBA`\u0001!\u0005A1\u001b\u0005\b\u0003\u0013tF\u0011\u0001Ck\u0011\u001d!9N\u0018C\u0001\t3Dq\u0001\"8_\t\u0003!y\u000eC\u0004\u0005^z#\t!\"\u0001\t\u000f\u0011me\f\"\u0001\u0006\u001a!9A\u0011\u00150\u0005\u0002\u0015\u0005\u0002bBC\u001a=\u0012\u0005QQ\u0007\u0004\u0007\u000b\u000b\u0002\u0001!b\u0012\t\u0015\u00055gM!b\u0001\n\u0003\ty\r\u0003\u0006\u0006J\u0019\u0014\t\u0011)A\u0005\u0003#D!\"a7g\u0005\u000b\u0007I\u0011AAo\u0011)\u0011)D\u001aB\u0001B\u0003%\u0011q\u001c\u0005\u000b\u0003W4'Q1A\u0005\u0002\u00055\bBCC&M\n\u0005\t\u0015!\u0003\u0002p\"9\u0011\u0011\u001a4\u0005\u0002\u00155\u0003\u0002CC,M\u0002\u0006K!!9\t\u000f\r\u0015c\r\"\u0001\u0004H!91\u0011\n4\u0005\u0002\u0015e\u0003b\u0002B\u001fM\u0012\u00053q\u0001\u0005\n\u000b?\u0002A\u0011AA?\u000bC2a\u0001b9\u0001\u0001\u0011\u0015\bB\u0003Ctg\n\u0005I\u0015!\u0003\u0005j\"9\u0011\u0011Z:\u0005\u0002\u0011=\b\u0002\u0003Czg\u0002\u0006KA!,\t\u000f\u0011U8\u000f\"\u0005\u0004V!QAq_:\t\u0006\u0004%IA!@\t\u000f\u000557\u000f\"\u0001\u0002P\"9\u00111\\:\u0005\u0002\u0005u\u0007bBAvg\u0012\u0005\u0011Q\u001e\u0005\b\u0007\u000b\u001aH\u0011AB$\u0011\u001d\u0019Ie\u001dC\u0001\tsDqA!\u0010t\t\u0003\u001a9\u0001C\u0004\u0004dM$\te!\u001a\t\u000f\r54\u000f\"\u0011\u0002\u001c\u001a1QQ\u0001\u0001\u0003\u000b\u000fA1ba*\u0002\u0004\t\u0005I\u0015!\u0003\u0006\n!YAq]A\u0002\u0005\u0003%\u000b\u0011\u0002Cu\u0011!\tI-a\u0001\u0005\u0002\u0015-\u0001bCC\t\u0003\u0007A)\u0019)C\u0005\u0007cB\u0001ba\u001c\u0002\u0004\u0011\u00053\u0011\u000f\u0004\b\u0003\u007f\u0003\u0011\u0011AAa\u0011!\tI-a\u0004\u0005\u0002\u0005-\u0007\u0002CAg\u0003\u001f1\t!a4\t\u0011\u0005m\u0017q\u0002D\u0001\u0003;D\u0001\"a;\u0002\u0010\u0019\u0005\u0011Q\u001e\u0005\t\u0007c\ty\u0001\"\u0001\u0002P\"A11GA\b\t\u0003\ti\u000e\u0003\u0005\u00046\u0005=A\u0011AB\u001c\u0011!\u0019)%a\u0004\u0007\u0002\r\u001d\u0003\u0002CB%\u0003\u001f1\taa\u0013\t\u0017\rM\u0013q\u0002EC\u0002\u0013\u00051Q\u000b\u0005\n\u0007/\ny\u0001)Q\u0005\u00073B\u0001ba\u0019\u0002\u0010\u0011\u00051Q\r\u0005\t\u0007O\ny\u0001\"\u0001\u0004j!A1QNA\b\t\u0003\tY\n\u0003\u0005\u0004p\u0005=A\u0011AB9\u0011!\u0019i(a\u0004\u0005\u0002\u0005-\u0006\u0002CB@\u0003\u001f!\ta!!\t\u0011\r\u001d\u0015q\u0002C\u0001\u0007\u0013C\u0001ba$\u0002\u0010\u0011\u00051\u0011\u0013\u0005\t\u0007;\u000by\u0001\"\u0001\u0004V!A1qTA\b\t\u000b\u0019)\u0006\u0003\u0005\u0004\"\u0006=A\u0011ABR\u0011!\u0019I+a\u0004\u0005\u0002\r-\u0006\u0002CB\\\u0003\u001f!\ta!/\t\u0011\r}\u0016q\u0002C\u0001\u0007\u0003D\u0001ba2\u0002\u0010\u0011\u00051\u0011\u001a\u0005\t\u0007+\fy\u0001\"\u0001\u0004X\"A1Q\\A\b\t\u0003\u0019y\u000e\u0003\u0005\u0004~\u0006=A\u0011AB\u0000\u0011!\u0011Y,a\u0004\u0005B\tu\u0006\u0002\u0003B`\u0003\u001f!\t\u0005b\u0002\u0006\r\u0015\u0015\u0004\u0001AA^\u000f\u001d)9\u0007\u0001E\u0001\u000bS2q!\"\u001a\u0001\u0011\u0003)Y\u0007\u0003\u0005\u0002J\u0006MC\u0011AC:\u0011!!Y*a\u0015\u0005\u0002\u0015U\u0004\u0002\u0003CQ\u0003'\"\t!b \t\u0013\u0015\u001d\u0005A1A\u0005\u0004\u0015%\u0005\"CCG\u0001\u0011E\u0011QPCH\u0011%)\u0019\n\u0001C\t\u0003{*)jB\u0004\u0006\u001c\u0002A\t!\"(\u0007\u000f\u0015}\u0005\u0001#\u0001\u0006\"\"A\u0011\u0011ZA2\t\u0003)\u0019K\u0002\u0004\u0006&\u0002\u0001Qq\u0015\u0005\t\u0003\u0013\f9\u0007\"\u0001\u0006*\u001e9QQ\u0016\u0001\t\u0002\u0015=faBCY\u0001!\u0005Q1\u0017\u0005\t\u0003\u0013\fi\u0007\"\u0001\u00066\"AA\u0011UA7\t\u0003)9LA\bB]:|G/\u0019;j_:LeNZ8t\u0015\u0011\t9(!\u001f\u0002\u0011%tG/\u001a:oC2TA!a\u001f\u0002~\u00059!/\u001a4mK\u000e$(BAA@\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019R\u0001AAC\u0003\u001b\u0003B!a\"\u0002\n6\u0011\u0011QP\u0005\u0005\u0003\u0017\u000biH\u0001\u0004B]f\u0014VM\u001a\t\u0005\u0003\u001f\u000b)*\u0004\u0002\u0002\u0012*!\u00111SA=\u0003\r\t\u0007/[\u0005\u0005\u0003/\u000b\tJA\u0006B]:|G/\u0019;j_:\u001c\u0018A\u0002\u0013j]&$H\u0005\u0006\u0002\u0002\u001eB!\u0011qQAP\u0013\u0011\t\t+! \u0003\tUs\u0017\u000e\u001e\u0002\f\u0003:tw\u000e^1uC\ndW-\u0006\u0003\u0002(\u0012M1c\u0001\u0002\u0002\u0006\u0006Y\u0011M\u001c8pi\u0006$\u0018n\u001c8t+\t\ti\u000b\u0005\u0004\u00020\u0006U\u00161\u0018\b\u0005\u0003\u000f\u000b\t,\u0003\u0003\u00024\u0006u\u0014a\u00029bG.\fw-Z\u0005\u0005\u0003o\u000bIL\u0001\u0003MSN$(\u0002BAZ\u0003{\u0002B!!0\u0002\u00105\t\u0001A\u0001\bB]:|G/\u0019;j_:LeNZ8\u0014\r\u0005=\u0011QQAb!\u0011\ti,!2\n\t\u0005\u001d\u0017Q\u0013\u0002\u000e\u0003:tw\u000e^1uS>t\u0017\t]5\u0002\rqJg.\u001b;?)\t\tY,A\u0002biB,\"!!5\u0011\t\u0005u\u00161[\u0005\u0005\u0003+\f9N\u0001\u0003UsB,\u0017\u0002BAm\u0003k\u0012Q\u0001V=qKN\fA!\u0019:hgV\u0011\u0011q\u001c\t\u0007\u0003_\u000b),!9\u0011\t\u0005u\u00161]\u0005\u0005\u0003K\f9O\u0001\u0003Ue\u0016,\u0017\u0002BAu\u0003k\u0012Q\u0001\u0016:fKN\fa!Y:t_\u000e\u001cXCAAx!\u0019\ty+!.\u0002rBA\u0011qQAz\u0003o\u0014\t!\u0003\u0003\u0002v\u0006u$A\u0002+va2,'\u0007\u0005\u0003\u0002>\u0006e\u0018\u0002BA~\u0003{\u0014AAT1nK&!\u0011q`A;\u0005\u0015q\u0015-\\3t!\r\ti\f\u0005\u0002\u0012\u00072\f7o\u001d4jY\u0016\feN\\8u\u0003J<7c\u0002\t\u0002\u0006\n\u001d!Q\u0002\t\u0005\u0003\u000f\u0013I!\u0003\u0003\u0003\f\u0005u$a\u0002)s_\u0012,8\r\u001e\t\u0005\u0003{\u0013y!\u0003\u0003\u0003\u0012\u0005U%a\u0004&bm\u0006\f%oZ;nK:$\u0018\t]5\u0015\u0005\t\u0005\u0011&\u0002\t4=!+\"!D!se\u0006L\u0018I\u001c8pi\u0006\u0013xmE\u00044\u0005\u0003\u00119Aa\u0007\u0011\t\tu!\u0011\u0006\b\u0005\u0005?\t\tL\u0004\u0003\u0003\"\t\u001dRB\u0001B\u0012\u0015\u0011\u0011)#!!\u0002\rq\u0012xn\u001c;?\u0013\t\ty(\u0003\u0003\u0003,\u0005e&\u0001D*fe&\fG.\u001b>bE2,WC\u0001B\u0018!\u0019\t9I!\r\u0003\u0002%!!1GA?\u0005\u0015\t%O]1z\u0003\u0015\t'oZ:!)\u0011\u0011IDa\u000f\u0011\u0007\u0005u6\u0007C\u0004\u0002\\Z\u0002\rAa\f\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"A!\u0011\u0011\t\t\r#1\n\b\u0005\u0005\u000b\u00129\u0005\u0005\u0003\u0003\"\u0005u\u0014\u0002\u0002B%\u0003{\na\u0001\u0015:fI\u00164\u0017\u0002\u0002B'\u0005\u001f\u0012aa\u0015;sS:<'\u0002\u0002B%\u0003{\nAaY8qsR!!\u0011\bB+\u0011%\tY\u000e\u000fI\u0001\u0002\u0004\u0011y#\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\tm#\u0006\u0002B\u0018\u0005;Z#Aa\u0018\u0011\t\t\u0005$1N\u0007\u0003\u0005GRAA!\u001a\u0003h\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0005\u0005S\ni(\u0001\u0006b]:|G/\u0019;j_:LAA!\u001c\u0003d\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\u0011\u0019\b\u0005\u0003\u0003v\t}TB\u0001B<\u0015\u0011\u0011IHa\u001f\u0002\t1\fgn\u001a\u0006\u0003\u0005{\nAA[1wC&!!Q\nB<\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\u0011)\t\u0005\u0003\u0002\b\n\u001d\u0015\u0002\u0002BE\u0003{\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$BAa$\u0003\u0016B!\u0011q\u0011BI\u0013\u0011\u0011\u0019*! \u0003\u0007\u0005s\u0017\u0010C\u0005\u0003\u0018r\n\t\u00111\u0001\u0003\u0006\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"A!(\u0011\r\t}%Q\u0015BH\u001b\t\u0011\tK\u0003\u0003\u0003$\u0006u\u0014AC2pY2,7\r^5p]&!!q\u0015BQ\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\t5&1\u0017\t\u0005\u0003\u000f\u0013y+\u0003\u0003\u00032\u0006u$a\u0002\"p_2,\u0017M\u001c\u0005\n\u0005/s\u0014\u0011!a\u0001\u0005\u001f\u000b!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!!1\u000fB]\u0011%\u00119jPA\u0001\u0002\u0004\u0011))\u0001\u0005iCND7i\u001c3f)\t\u0011))\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0005[\u0013\u0019\rC\u0005\u0003\u0018\u0006\u000b\t\u00111\u0001\u0003\u0010\nyA*\u001b;fe\u0006d\u0017I\u001c8pi\u0006\u0013xmE\u0004\u001f\u0005\u0003\u00119Aa\u0007\u0002\u000b\r|gn\u001d;\u0016\u0005\t5\u0007\u0003BA_\u0005\u001fLAA!5\u0003T\nA1i\u001c8ti\u0006tG/\u0003\u0003\u0003V\u0006U$!C\"p]N$\u0018M\u001c;t\u0003\u0019\u0019wN\\:uAQ!!1\u001cBo!\r\tiL\b\u0005\b\u0005\u0013\f\u0003\u0019\u0001Bg)\u0011\u0011YN!9\t\u0013\t%7\u0005%AA\u0002\t5WC\u0001BsU\u0011\u0011iM!\u0018\u0015\t\t=%\u0011\u001e\u0005\n\u0005/;\u0013\u0011!a\u0001\u0005\u000b#BA!,\u0003n\"I!qS\u0015\u0002\u0002\u0003\u0007!q\u0012\u000b\u0005\u0005g\u0012\t\u0010C\u0005\u0003\u0018*\n\t\u00111\u0001\u0003\u0006R!!Q\u0016B{\u0011%\u00119\nLA\u0001\u0002\u0004\u0011yI\u0001\bOKN$X\rZ!o]>$\u0018I]4\u0014\u000f!\u0013\tAa\u0002\u0003\u001c\u00059\u0011M\u001c8J]\u001a|WCAA^\u0003!\tgN\\%oM>\u0004C\u0003BB\u0002\u0007\u000b\u00012!!0I\u0011\u001d\u0011Yp\u0013a\u0001\u0003w#\"Aa\u001d\u0015\t\r\r11\u0002\u0005\n\u0005wl\u0005\u0013!a\u0001\u0003w+\"aa\u0004+\t\u0005m&Q\f\u000b\u0005\u0005\u001f\u001b\u0019\u0002C\u0005\u0003\u0018F\u000b\t\u00111\u0001\u0003\u0006R!!QVB\f\u0011%\u00119jUA\u0001\u0002\u0004\u0011y\t\u0006\u0003\u0003t\rm\u0001\"\u0003BL)\u0006\u0005\t\u0019\u0001BC)\u0011\u0011ika\b\t\u0013\t]e+!AA\u0002\t=%AE+o[\u0006\u0004\b/\u00192mK\u0006sgn\u001c;Be\u001e\u001cr!\u0006B\u0001\u0005\u000f\u0011Y\u0002\u0006\u0002\u0004(A\u0019\u0011QX\u000b\u0015\t\t=51\u0006\u0005\n\u0005/K\u0012\u0011!a\u0001\u0005\u000b#BA!,\u00040!I!qS\u000e\u0002\u0002\u0003\u0007!qR\u0001\u0004iB,\u0017!C:dC2\f\u0017I]4t\u0003!Q\u0017M^1Be\u001e\u001cXCAB\u001d!!\u0019Yd!\u0011\u0002x\n\u0005QBAB\u001f\u0015\u0011\u0019yD!)\u0002\u0013%lW.\u001e;bE2,\u0017\u0002BB\"\u0007{\u0011q\u0001T5ti6\u000b\u0007/\u0001\u0005pe&<\u0017N\\1m+\t\t\t/A\u0006tKR|%/[4j]\u0006dG\u0003BB'\u0007\u001fj!!a\u0004\t\u0011\rE\u0013\u0011\u0005a\u0001\u0003C\f\u0011\u0001^\u0001\nSN$&/\u001b<jC2,\"A!,\u0002\rI\fw\u000f]8t!\u0011\tila\u0017\n\t\ru3q\f\u0002\t!>\u001c\u0018\u000e^5p]&!1\u0011MA;\u0005%\u0001vn]5uS>t7/A\u0002q_N,\"a!\u0017\u0002\rM,G\u000fU8t)\u0011\u0019iea\u001b\t\u0011\r\r\u0014\u0011\u0006a\u0001\u00073\nAbY8na2,G/Z%oM>\faa]=nE>dWCAB:!\u0011\til!\u001e\n\t\r]4\u0011\u0010\u0002\u0007'fl'm\u001c7\n\t\rm\u0014Q\u000f\u0002\b'fl'm\u001c7t\u0003=iW\r^1B]:|G/\u0019;j_:\u001c\u0018A\u00043fM\u0006,H\u000e\u001e+be\u001e,Go]\u000b\u0003\u0007\u0007\u0003baa\u000f\u0004\u0006\u000eM\u0014\u0002BA\\\u0007{\tq!\\1uG\",7\u000f\u0006\u0003\u0003.\u000e-\u0005\u0002CBG\u0003g\u0001\raa\u001d\u0002\u000b\rd\u0017M\u001f>\u0002\u0017!\f7/\u0011:h/\"L7\r\u001b\u000b\u0005\u0005[\u001b\u0019\n\u0003\u0005\u0004\u0016\u0006U\u0002\u0019ABL\u0003\u0005\u0001\b\u0003CAD\u00073\u000b\tO!,\n\t\rm\u0015Q\u0010\u0002\n\rVt7\r^5p]F\n1\"[:FeJ|g.Z8vg\u0006A\u0011n]*uCRL7-\u0001\u0006sK\u001a\u001c8+_7c_2$BA!,\u0004&\"A1qUA\u001e\u0001\u0004\u0019\u0019(A\u0002ts6\f\u0011b\u001d;sS:<\u0017I]4\u0015\t\r561\u0017\t\u0007\u0003\u000f\u001byK!\u0011\n\t\rE\u0016Q\u0010\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\rU\u0016Q\ba\u0001\u0005\u000b\u000bQ!\u001b8eKb\fa!\u001b8u\u0003J<G\u0003BB^\u0007{\u0003b!a\"\u00040\n\u0015\u0005\u0002CB[\u0003\u007f\u0001\rA!\"\u0002\u0015\t|w\u000e\\3b]\u0006\u0013x\r\u0006\u0003\u0004D\u000e\u0015\u0007CBAD\u0007_\u0013i\u000b\u0003\u0005\u00046\u0006\u0005\u0003\u0019\u0001BC\u0003%\u0019\u00180\u001c2pY\u0006\u0013x\r\u0006\u0003\u0004L\u000eM\u0007CBAD\u0007_\u001bi\r\u0005\u0003\u0002>\u000e=\u0017\u0002BBi\u0003{\u0014\u0001\u0002V3s[:\u000bW.\u001a\u0005\t\u0007k\u000b\u0019\u00051\u0001\u0003\u0006\u0006y1m\u001c8ti\u0006tG/\u0011;J]\u0012,\u0007\u0010\u0006\u0003\u0004Z\u000em\u0007CBAD\u0007_\u0013i\r\u0003\u0005\u00046\u0006\u0015\u0003\u0019\u0001BC\u0003)\t'oZ!u\u0013:$W\r_\u000b\u0005\u0007C\u001cI\u000f\u0006\u0004\u0004d\u000eU81 \t\u0007\u0003\u000f\u001byk!:\u0011\t\r\u001d8\u0011\u001e\u0007\u0001\t!\u0019Y/a\u0012C\u0002\r5(!\u0001+\u0012\t\r=(q\u0012\t\u0005\u0003\u000f\u001b\t0\u0003\u0003\u0004t\u0006u$a\u0002(pi\"Lgn\u001a\u0005\t\u0007o\f9\u00051\u0001\u0004z\u0006\tA\u000e\u0005\u0004\u00020\u0006U6Q\u001d\u0005\t\u0007k\u000b9\u00051\u0001\u0003\u0006\u0006iAO]1og\u001a|'/\\!sON$B!a/\u0005\u0002!AA1AA%\u0001\u0004!)!A\u0001g!!\t9i!'\u0002`\u0006}G\u0003\u0002BW\t\u0013A\u0001\u0002b\u0003\u0002N\u0001\u0007!qR\u0001\u0006_RDWM]\u0001\u000fg\u0016$\u0018I\u001c8pi\u0006$\u0018n\u001c8t)\u0011!\t\u0002b\u0006\u0011\t\r\u001dH1\u0003\u0003\b\t+\u0011!\u0019ABw\u0005\u0011\u0019V\r\u001c4\t\u000f\u0011eQ\u00011\u0001\u0002.\u00061\u0011M\u001c8piN\fqb^5uQ\u0006sgn\u001c;bi&|gn\u001d\u000b\u0005\t#!y\u0002C\u0004\u0005\u001a\u0019\u0001\r!!,\u0002#\u0019LG\u000e^3s\u0003:tw\u000e^1uS>t7\u000f\u0006\u0003\u0005\u0012\u0011\u0015\u0002bBBK\u000f\u0001\u0007Aq\u0005\t\t\u0003\u000f\u001bI*a/\u0003.\u0006\u0011r/\u001b;i_V$\u0018I\u001c8pi\u0006$\u0018n\u001c8t+\t!\t\"A\tti\u0006$\u0018nY!o]>$\u0018\r^5p]N,\"\u0001\"\r\u0011\r\rm2QQA^\u0003M\tG\r\u001a+ie><8/\u00118o_R\fG/[8o)\u0011!\t\u0002b\u000e\t\u000f\u0011e\"\u00021\u0001\u0004t\u0005aA\u000f\u001b:po\u0006\u0014G.Z*z[\u0006i\u0001.Y:B]:|G/\u0019;j_:$BA!,\u0005@!9A\u0011I\u0006A\u0002\rM\u0014aA2mg\u0006iq-\u001a;B]:|G/\u0019;j_:$B\u0001b\u0012\u0005JA1\u0011qQBX\u0003wCq\u0001\"\u0011\r\u0001\u0004\u0019\u0019(\u0001\tsK6|g/Z!o]>$\u0018\r^5p]R!A\u0011\u0003C(\u0011\u001d!\t%\u0004a\u0001\u0007g\nab^5uQ\u0006sgn\u001c;bi&|g\u000e\u0006\u0003\u0005\u0012\u0011U\u0003b\u0002C,\u001d\u0001\u0007\u00111X\u0001\u0006C:tw\u000e^\u0001\u0015IJ|\u0007o\u0014;iKJ\feN\\8uCRLwN\\:\u0015\r\u00055FQ\fC1\u0011\u001d!yf\u0004a\u0001\u0003[\u000bA!\u00198og\"9A\u0011I\bA\u0002\rM\u0004fA\b\u0005fA!Aq\rC5\u001b\t\u00119'\u0003\u0003\u0005l\t\u001d$a\u0002;bS2\u0014Xm\u0019\u0002\r\u0015\u00064\u0018-\u0011:hk6,g\u000e^\u0001\u0010\u0015\u00064\u0018-\u0011:hk6,g\u000e\u001e+bOV\u0011A1\u000f\t\u0007\tk\"9H!\u0001\u000e\u0005\u0005e\u0014\u0002\u0002C=\u0003s\u0012\u0001b\u00117bgN$\u0016mZ\u0001\u0013+:l\u0017\r\u001d9bE2,\u0017I\u001c8pi\u0006\u0013x-A\bMSR,'/\u00197B]:|G/\u0011:h!\r\tiLL\n\u0006]\u0011\rEq\u0012\t\t\t\u000b#YI!4\u0003\\6\u0011Aq\u0011\u0006\u0005\t\u0013\u000bi(A\u0004sk:$\u0018.\\3\n\t\u00115Eq\u0011\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003\u0002CI\t/k!\u0001b%\u000b\t\u0011U%1P\u0001\u0003S>LAAa\u000b\u0005\u0014R\u0011AqP\u0001\u0006CB\u0004H.\u001f\u000b\u0005\u00057$y\nC\u0004\u0003JF\u0002\rA!4\u0002\u000fUt\u0017\r\u001d9msR!1\u0011\u001cCS\u0011%!9KMA\u0001\u0002\u0004\u0011Y.A\u0002yIA\nQ\"\u0011:sCf\feN\\8u\u0003J<\u0007cAA_\u0007N)1\tb,\u0005\u0010BAAQ\u0011CF\u0005_\u0011I\u0004\u0006\u0002\u0005,R!!\u0011\bC[\u0011\u001d\tYN\u0012a\u0001\u0005_!B\u0001\"/\u0005<B1\u0011qQBX\u0005_A\u0011\u0002b*H\u0003\u0003\u0005\rA!\u000f\u0002\u001d9+7\u000f^3e\u0003:tw\u000e^!sOB\u0019\u0011Q\u0018-\u0014\u000ba#\u0019\rb$\u0011\u0011\u0011\u0015E1RA^\u0007\u0007!\"\u0001b0\u0015\t\r\rA\u0011\u001a\u0005\b\u0005w\\\u0006\u0019AA^)\u0011!9\u0005\"4\t\u0013\u0011\u001dF,!AA\u0002\r\r\u0011AD!o]>$\u0018\r^5p]&sgm\u001c\t\u0004\u0003{s6c\u00010\u0002\u0006R\u0011A\u0011[\u0001\u0007[\u0006\u00148.\u001a:\u0015\t\u0005mF1\u001c\u0005\b\u0003\u001b\u0004\u0007\u0019AAi\u0003\u0019a\u0017M_5msR!A\u0011\u001dC\u0000!\r\til\u001d\u0002\u0013\u0019\u0006T\u00180\u00118o_R\fG/[8o\u0013:4wnE\u0002t\u0003w\u000b\u0001\u0002\\1{s&sgm\u001c\t\u0007\u0003\u000f#Y/a/\n\t\u00115\u0018Q\u0010\u0002\ty\tLh.Y7f}Q!A\u0011\u001dCy\u0011!!9/\u001eCA\u0002\u0011%\u0018aB0g_J\u001cW\rZ\u0001\u0007M>\u00148-\u001a3\u0002\u0015\u0019|'oY3e\u0013:4w\u000e\u0006\u0003\u0005|\u0012uX\"A:\t\u000f\rES\u00101\u0001\u0002b\"AAq]1\u0005\u0002\u0004!I\u000f\u0006\u0004\u0006\u0004\u0015MQq\u0003\t\u0005\u0003{\u000b\u0019AA\fFqR\u0014\u0018\rT1{s\u0006sgn\u001c;bi&|g.\u00138g_N!\u00111\u0001Cq!\u0019\t9\tb;\u0004tQ1Q1AC\u0007\u000b\u001fA\u0011ba*\u0002\n\u0011\u0005\r!\"\u0003\t\u0013\u0011\u001d\u0018\u0011\u0002CA\u0002\u0011%\u0018A\u0003;za\u0016\u001c\u00160\u001c2pY\"AQQ\u00032\u0005\u0002\u0004)I!\u0001\u0006mCjL8+_7c_2D\u0001\u0002b:c\t\u0003\u0007A\u0011\u001e\u000b\t\u0003w+Y\"\"\b\u0006 !9\u0011QZ2A\u0002\u0005E\u0007bBAnG\u0002\u0007\u0011q\u001c\u0005\b\u0003W\u001c\u0007\u0019AAx)\u0011)\u0019#b\f\u0011\r\u0005\u001dUQEC\u0015\u0013\u0011)9#! \u0003\tM{W.\u001a\t\u000b\u0003\u000f+Y#!5\u0002`\u0006=\u0018\u0002BC\u0017\u0003{\u0012a\u0001V;qY\u0016\u001c\u0004bBC\u0019I\u0002\u0007\u00111X\u0001\u0005S:4w.\u0001\u0005nW\u001aKG\u000e^3s)\u0019)9$\"\u0010\u0006BQ!!QVC\u001d\u0011\u001d)Y$\u001aa\u0001\u0003w\u000b1!\u00198o\u0011\u001d)y$\u001aa\u0001\u0007g\n\u0001bY1uK\u001e|'/\u001f\u0005\b\u000b\u0007*\u0007\u0019\u0001BW\u0003A!WMZ1vYR\u0014V\r^3oi&|gN\u0001\fD_6\u0004H.\u001a;f\u0003:tw\u000e^1uS>t\u0017J\u001c4p'\r1\u00171X\u0001\u0005CR\u0004\b%A\u0004bgN|7m\u001d\u0011\u0015\u0011\u0015=S\u0011KC*\u000b+\u00022!!0g\u0011\u001d\ti-\u001ca\u0001\u0003#Dq!a7n\u0001\u0004\ty\u000eC\u0004\u0002l6\u0004\r!a<\u0002\t=\u0014\u0018n\u001a\u000b\u0005\u000b7*i&D\u0001g\u0011\u001d\u0019\t\u0006\u001da\u0001\u0003C\f!dY8na2,G/Z!o]>$\u0018\r^5p]R{7\u000b\u001e:j]\u001e$BAa\u001d\u0006d!9!1 :A\u0002\u0005m&AC!o]>$\u0018\r^5p]\u0006Q\u0011I\u001c8pi\u0006$\u0018n\u001c8\u0011\t\u0005u\u00161K\n\u0005\u0003'*i\u0007\u0005\u0003\u0002>\u0016=\u0014\u0002BC9\u0003+\u00131#\u00118o_R\fG/[8o\u000bb$(/Y2u_J$\"!\"\u001b\u0015\u0011\u0015]T\u0011PC>\u000b{\u0002B!!0\u0002P!A1\u0011GA,\u0001\u0004\t\t\u000e\u0003\u0005\u00044\u0005]\u0003\u0019AAp\u0011!\u0019)$a\u0016A\u0002\reB\u0003BCA\u000b\u000b\u0003b!a\"\u0006&\u0015\r\u0005CCAD\u000bW\t\t.a8\u0004:!A!\u0011NA-\u0001\u0004)9(A\u0007B]:|G/\u0019;j_:$\u0016mZ\u000b\u0003\u000b\u0017\u0003b\u0001\"\u001e\u0005x\u0005m\u0016\u0001E1o]>$\u0018\r^5p]R{GK]3f)\u0011\t\t/\"%\t\u0011\u0015m\u0012Q\fa\u0001\u000bo\n\u0001\u0003\u001e:fKR{\u0017I\u001c8pi\u0006$\u0018n\u001c8\u0015\t\u0015]Tq\u0013\u0005\t\u000b3\u000by\u00061\u0001\u0002b\u0006!AO]3f\u0003Q)f.\\1qa\u0006\u0014G.Z!o]>$\u0018\r^5p]B!\u0011QXA2\u0005Q)f.\\1qa\u0006\u0014G.Z!o]>$\u0018\r^5p]N!\u00111MC()\t)iJA\nFeJ|g.Z8vg\u0006sgn\u001c;bi&|gn\u0005\u0003\u0002h\u0015=CCACV!\u0011\ti,a\u001a\u0002\u001fQC'o\\<o\u000bb\u001cW\r\u001d;j_:\u0004B!!0\u0002n\tyA\u000b\u001b:po:,\u0005pY3qi&|gn\u0005\u0003\u0002n\u0005\u0015ECACX)\u0011)I,b/\u0011\r\u0005\u001d5qVAi\u0011!)Y$!\u001dA\u0002\u0005m\u0006\u0003BC`\u000b\u0003l!!!\u001e\n\t\u0015\r\u0017Q\u000f\u0002\f'fl'm\u001c7UC\ndW\r"
)
public interface AnnotationInfos extends Annotations {
   UnmappableAnnotArg$ UnmappableAnnotArg();

   LiteralAnnotArg$ LiteralAnnotArg();

   ArrayAnnotArg$ ArrayAnnotArg();

   NestedAnnotArg$ NestedAnnotArg();

   AnnotationInfo$ AnnotationInfo();

   Annotation$ Annotation();

   UnmappableAnnotation$ UnmappableAnnotation();

   ThrownException$ ThrownException();

   void scala$reflect$internal$AnnotationInfos$_setter_$JavaArgumentTag_$eq(final ClassTag x$1);

   void scala$reflect$internal$AnnotationInfos$_setter_$AnnotationTag_$eq(final ClassTag x$1);

   ClassTag JavaArgumentTag();

   // $FF: synthetic method
   static String completeAnnotationToString$(final AnnotationInfos $this, final AnnotationInfo annInfo) {
      return $this.completeAnnotationToString(annInfo);
   }

   default String completeAnnotationToString(final AnnotationInfo annInfo) {
      String var20;
      if (!annInfo.args().isEmpty()) {
         List var10000 = annInfo.args();
         String mkString_end = ")";
         String mkString_sep = ", ";
         String mkString_start = "(";
         if (var10000 == null) {
            throw null;
         }

         var20 = IterableOnceOps.mkString$(var10000, mkString_start, mkString_sep, mkString_end);
         Object var12 = null;
         Object var13 = null;
         Object var14 = null;
      } else {
         var20 = "";
      }

      String s_args = var20;
      if (!annInfo.assocs().isEmpty()) {
         List var21 = annInfo.assocs();
         if (var21 == null) {
            throw null;
         }

         List map_this = var21;
         Object var22;
         if (map_this == .MODULE$) {
            var22 = .MODULE$;
         } else {
            scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$completeAnnotationToString$1((Tuple2)map_this.head()), .MODULE$);
            scala.collection.immutable..colon.colon map_t = map_h;

            for(List map_rest = (List)map_this.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
               scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$completeAnnotationToString$1((Tuple2)map_rest.head()), .MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            var22 = map_h;
         }

         Object var15 = null;
         Object var16 = null;
         Object var17 = null;
         Object var18 = null;
         Object var19 = null;
         var20 = IterableOnceOps.mkString$((IterableOnceOps)var22, "(", ", ", ")");
      } else {
         var20 = "";
      }

      String s_assocs = var20;
      return (new StringBuilder(0)).append(annInfo.atp()).append(s_args).append(s_assocs).toString();
   }

   ClassTag AnnotationTag();

   // $FF: synthetic method
   static Trees.Tree annotationToTree$(final AnnotationInfos $this, final AnnotationInfo ann) {
      return $this.annotationToTree(ann);
   }

   default Trees.Tree annotationToTree(final AnnotationInfo ann) {
      Trees.Select ctorSelection = (SymbolTable)this.new Select((SymbolTable)this.new New(((Trees)this).TypeTree(ann.atp())), ((StdNames)this).nme().CONSTRUCTOR());
      return ((SymbolTable)this.new Apply(ctorSelection, this.reverseEngineerArgs$1(ann))).setType(ann.atp());
   }

   // $FF: synthetic method
   static AnnotationInfo treeToAnnotation$(final AnnotationInfos $this, final Trees.Tree tree) {
      return $this.treeToAnnotation(tree);
   }

   default AnnotationInfo treeToAnnotation(final Trees.Tree tree) {
      if (tree instanceof Trees.Apply) {
         Trees.Apply var2 = (Trees.Apply)tree;
         Trees.Tree var3 = var2.fun();
         List args = var2.args();
         if (var3 instanceof Trees.Select) {
            Trees.Select var5 = (Trees.Select)var3;
            Trees.Tree var6 = var5.qualifier();
            Names.Name var7 = var5.name();
            if (var6 instanceof Trees.New) {
               Trees.Tree tpt = ((Trees.New)var6).tpt();
               Names.TermName var10000 = ((StdNames)this).nme().CONSTRUCTOR();
               if (var10000 == null) {
                  if (var7 != null) {
                     throw new Exception("unexpected tree shape: only q\"new $annType(..$args)\" is supported");
                  }
               } else if (!var10000.equals(var7)) {
                  throw new Exception("unexpected tree shape: only q\"new $annType(..$args)\" is supported");
               }

               Types.Type atp = tpt.tpe();
               if (atp != null && atp.typeSymbol().isNonBottomSubClass(((Definitions)this).definitions().StaticAnnotationClass())) {
                  return this.AnnotationInfo().apply(atp, args, .MODULE$);
               }

               if (atp == null || !atp.typeSymbol().isJavaDefined() && !atp.typeSymbol().isNonBottomSubClass(((Definitions)this).definitions().ConstantAnnotationClass())) {
                  throw new Exception((new StringBuilder(102)).append("unexpected annotation type ").append(atp).append(": only subclasses of StaticAnnotation and ClassfileAnnotation are supported").toString());
               }

               return this.AnnotationInfo().apply(atp, .MODULE$, this.encodeJavaArgs$1(args));
            }
         }
      }

      throw new Exception("unexpected tree shape: only q\"new $annType(..$args)\" is supported");
   }

   // $FF: synthetic method
   static String $anonfun$completeAnnotationToString$1(final Tuple2 x0$1) {
      if (x0$1 != null) {
         Names.Name x = (Names.Name)x0$1._1();
         ClassfileAnnotArg y = (ClassfileAnnotArg)x0$1._2();
         return (new StringBuilder(3)).append(x).append(" = ").append(y).toString();
      } else {
         throw new MatchError((Object)null);
      }
   }

   // $FF: synthetic method
   static Trees.Tree $anonfun$annotationToTree$1(final AnnotationInfos $this, final ClassfileAnnotArg jarg) {
      return $this.reverseEngineerArg$1(jarg);
   }

   private Trees.Tree reverseEngineerArg$1(final ClassfileAnnotArg jarg) {
      if (jarg instanceof LiteralAnnotArg) {
         Constants.Constant var2 = ((LiteralAnnotArg)jarg).const();
         Types.Type tpe = (Types.Type)(var2.tag() == 1 ? ((Definitions)this).definitions().UnitTpe() : ((Types)this).ConstantType().apply(var2));
         return ((SymbolTable)this.new Literal(var2)).setType(tpe);
      } else if (!(jarg instanceof ArrayAnnotArg)) {
         if (jarg instanceof NestedAnnotArg) {
            AnnotationInfo jarg = ((NestedAnnotArg)jarg).annInfo();
            if (jarg != null) {
               return this.annotationToTree(jarg);
            }
         }

         return ((Trees)this).EmptyTree();
      } else {
         ClassfileAnnotArg[] var10000 = ((ArrayAnnotArg)jarg).args();
         ClassTag map$extension_ct = ((Trees)this).TreeTag();
         Object map$extension_$this = var10000;
         int map$extension_len = ((Object[])map$extension_$this).length;
         Object map$extension_ys = map$extension_ct.newArray(map$extension_len);
         if (map$extension_len > 0) {
            for(int map$extension_i = 0; map$extension_i < map$extension_len; ++map$extension_i) {
               Object var11 = ((Object[])map$extension_$this)[map$extension_i];
               scala.runtime.ScalaRunTime..MODULE$.array_update(map$extension_ys, map$extension_i, $anonfun$annotationToTree$1(this, (ClassfileAnnotArg)var11));
            }
         }

         map$extension_$this = null;
         Object var13 = null;
         Object var14 = null;
         Trees.Tree[] args = (Trees.Tree[])map$extension_ys;
         Trees.Apply var16 = new Trees.Apply;
         SymbolTable var10002 = (SymbolTable)this;
         Trees.Ident var10003 = ((Trees)this).Ident((Symbols.Symbol)((Definitions)this).definitions().ArrayModule());
         ArraySeq.ofRef var10004 = scala.Predef..MODULE$.wrapRefArray(args);
         if (var10004 == null) {
            throw null;
         } else {
            var16.<init>(var10003, IterableOnceOps.toList$(var10004));
            return var16;
         }
      }
   }

   private List reverseEngineerArgs$2(final List jargs) {
      if (jargs instanceof scala.collection.immutable..colon.colon) {
         scala.collection.immutable..colon.colon var2 = (scala.collection.immutable..colon.colon)jargs;
         Tuple2 var3 = (Tuple2)var2.head();
         List rest = var2.next$access$1();
         if (var3 != null) {
            Names.Name name = (Names.Name)var3._1();
            ClassfileAnnotArg jarg = (ClassfileAnnotArg)var3._2();
            Trees.NamedArg var7 = (SymbolTable)this.new NamedArg((SymbolTable)this.new Ident(name), this.reverseEngineerArg$1(jarg));
            List var10000 = this.reverseEngineerArgs$2(rest);
            if (var10000 == null) {
               throw null;
            }

            List $colon$colon_this = var10000;
            return new scala.collection.immutable..colon.colon(var7, $colon$colon_this);
         }
      }

      if (.MODULE$.equals(jargs)) {
         return .MODULE$;
      } else {
         throw new MatchError(jargs);
      }
   }

   private List reverseEngineerArgs$1(final AnnotationInfo ann$1) {
      return ann$1.assocs().isEmpty() ? ann$1.args() : this.reverseEngineerArgs$2(ann$1.assocs());
   }

   // $FF: synthetic method
   static ClassfileAnnotArg $anonfun$treeToAnnotation$1(final AnnotationInfos $this, final Trees.Tree arg) {
      return $this.encodeJavaArg$1(arg);
   }

   private ClassfileAnnotArg encodeJavaArg$1(final Trees.Tree arg) {
      boolean var2 = false;
      Trees.Apply var3 = null;
      if (arg instanceof Trees.Literal) {
         Constants.Constant var4 = ((Trees.Literal)arg).value();
         return (SymbolTable)this.new LiteralAnnotArg(var4);
      } else {
         List args;
         label68: {
            if (arg instanceof Trees.Apply) {
               var2 = true;
               var3 = (Trees.Apply)arg;
               Trees.Tree var5 = var3.fun();
               args = var3.args();
               Symbols.ModuleSymbol var10000 = ((Definitions)this).definitions().ArrayModule();
               if (var10000 == null) {
                  if (var5 == null) {
                     break label68;
                  }
               } else if (var10000.equals(var5)) {
                  break label68;
               }
            }

            if (var2) {
               Trees.Tree var7 = var3.fun();
               if (var7 instanceof Trees.Select) {
                  Trees.Select var8 = (Trees.Select)var7;
                  Trees.Tree var9 = var8.qualifier();
                  Names.Name var10 = var8.name();
                  if (var9 instanceof Trees.New) {
                     Names.TermName var21 = ((StdNames)this).nme().CONSTRUCTOR();
                     if (var21 == null) {
                        if (var10 == null) {
                           return (SymbolTable)this.new NestedAnnotArg(this.treeToAnnotation(arg));
                        }
                     } else if (var21.equals(var10)) {
                        return (SymbolTable)this.new NestedAnnotArg(this.treeToAnnotation(arg));
                     }
                  }
               }
            }

            throw new Exception((new StringBuilder(86)).append("unexpected java argument shape ").append(arg).append(": literals, arrays and nested annotations are supported").toString());
         }

         ArrayAnnotArg var22 = new ArrayAnnotArg;
         SymbolTable var10002 = (SymbolTable)this;
         if (args == null) {
            throw null;
         } else {
            Object var10003;
            if (args == .MODULE$) {
               var10003 = .MODULE$;
            } else {
               Trees.Tree var15 = (Trees.Tree)args.head();
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$treeToAnnotation$1(this, var15), .MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)args.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                  var15 = (Trees.Tree)map_rest.head();
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$treeToAnnotation$1(this, var15), .MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var10003 = map_h;
            }

            Object var16 = null;
            Object var17 = null;
            Object var18 = null;
            Object var19 = null;
            var22.<init>((ClassfileAnnotArg[])((List)var10003).toArray(this.JavaArgumentTag()));
            return var22;
         }
      }
   }

   private List encodeJavaArgs$1(final List args) {
      boolean var2 = false;
      scala.collection.immutable..colon.colon var3 = null;
      if (args instanceof scala.collection.immutable..colon.colon) {
         var2 = true;
         var3 = (scala.collection.immutable..colon.colon)args;
         Trees.Tree var4 = (Trees.Tree)var3.head();
         List rest = var3.next$access$1();
         if (var4 instanceof Trees.NamedArg) {
            Trees.NamedArg var6 = (Trees.NamedArg)var4;
            Trees.Tree var7 = var6.lhs();
            Trees.Tree arg = var6.rhs();
            if (var7 instanceof Trees.Ident) {
               Names.Name name = ((Trees.Ident)var7).name();
               ClassfileAnnotArg var13 = this.encodeJavaArg$1(arg);
               Tuple2 var10 = new Tuple2(name, var13);
               List var10000 = this.encodeJavaArgs$1(rest);
               if (var10000 == null) {
                  throw null;
               }

               List $colon$colon_this = var10000;
               return new scala.collection.immutable..colon.colon(var10, $colon$colon_this);
            }
         }
      }

      if (var2) {
         Trees.Tree arg = (Trees.Tree)var3.head();
         throw new Exception((new StringBuilder(66)).append("unexpected java argument shape ").append(arg).append(": only NamedArg trees are supported").toString());
      } else if (.MODULE$.equals(args)) {
         return .MODULE$;
      } else {
         throw new MatchError(args);
      }
   }

   static void $init$(final AnnotationInfos $this) {
      $this.scala$reflect$internal$AnnotationInfos$_setter_$JavaArgumentTag_$eq(scala.reflect.ClassTag..MODULE$.apply(ClassfileAnnotArg.class));
      $this.scala$reflect$internal$AnnotationInfos$_setter_$AnnotationTag_$eq(scala.reflect.ClassTag..MODULE$.apply(AnnotationInfo.class));
   }

   public interface Annotatable {
      List annotations();

      Object setAnnotations(final List annots);

      Object withAnnotations(final List annots);

      Object filterAnnotations(final Function1 p);

      Object withoutAnnotations();

      // $FF: synthetic method
      static List staticAnnotations$(final Annotatable $this) {
         return $this.staticAnnotations();
      }

      default List staticAnnotations() {
         List var10000 = this.annotations();
         if (var10000 == null) {
            throw null;
         } else {
            List filter_this = var10000;
            boolean filter_filterCommon_isFlipped = false;
            List filter_filterCommon_noneIn$1_l = filter_this;

            while(true) {
               if (filter_filterCommon_noneIn$1_l.isEmpty()) {
                  var48 = .MODULE$;
                  break;
               }

               Object filter_filterCommon_noneIn$1_h = filter_filterCommon_noneIn$1_l.head();
               List filter_filterCommon_noneIn$1_t = (List)filter_filterCommon_noneIn$1_l.tail();
               if (((AnnotationInfo)filter_filterCommon_noneIn$1_h).isStatic() != filter_filterCommon_isFlipped) {
                  List filter_filterCommon_noneIn$1_allIn$1_remaining = filter_filterCommon_noneIn$1_t;

                  while(true) {
                     if (filter_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                        var48 = filter_filterCommon_noneIn$1_l;
                        break;
                     }

                     Object filter_filterCommon_noneIn$1_allIn$1_x = filter_filterCommon_noneIn$1_allIn$1_remaining.head();
                     if (((AnnotationInfo)filter_filterCommon_noneIn$1_allIn$1_x).isStatic() == filter_filterCommon_isFlipped) {
                        scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_l.head(), .MODULE$);
                        List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filter_filterCommon_noneIn$1_l.tail();

                        scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                        for(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filter_filterCommon_noneIn$1_allIn$1_remaining; filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                           scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), .MODULE$);
                           filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                           filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                        }

                        List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_remaining.tail();
                        List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                        while(!filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                           Object filter_filterCommon_noneIn$1_allIn$1_partialFill$1_head = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                           if (((AnnotationInfo)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_head).isStatic() != filter_filterCommon_isFlipped) {
                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                           } else {
                              while(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                 scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), .MODULE$);
                                 filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                 filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                                 filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                              }

                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                           }
                        }

                        if (!filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                           filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                        }

                        var48 = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                        Object var24 = null;
                        Object var27 = null;
                        Object var30 = null;
                        Object var33 = null;
                        Object var36 = null;
                        Object var39 = null;
                        Object var42 = null;
                        Object var45 = null;
                        break;
                     }

                     filter_filterCommon_noneIn$1_allIn$1_remaining = (List)filter_filterCommon_noneIn$1_allIn$1_remaining.tail();
                  }

                  Object var20 = null;
                  Object var22 = null;
                  Object var25 = null;
                  Object var28 = null;
                  Object var31 = null;
                  Object var34 = null;
                  Object var37 = null;
                  Object var40 = null;
                  Object var43 = null;
                  Object var46 = null;
                  break;
               }

               filter_filterCommon_noneIn$1_l = filter_filterCommon_noneIn$1_t;
            }

            Object var17 = null;
            Object var18 = null;
            Object var19 = null;
            Object var21 = null;
            Object var23 = null;
            Object var26 = null;
            Object var29 = null;
            Object var32 = null;
            Object var35 = null;
            Object var38 = null;
            Object var41 = null;
            Object var44 = null;
            Object var47 = null;
            List filter_filterCommon_result = (List)var48;
            Statics.releaseFence();
            return filter_filterCommon_result;
         }
      }

      // $FF: synthetic method
      static Object addThrowsAnnotation$(final Annotatable $this, final Symbols.Symbol throwableSym) {
         return $this.addThrowsAnnotation(throwableSym);
      }

      default Object addThrowsAnnotation(final Symbols.Symbol throwableSym) {
         Types.Type var10000;
         if (throwableSym.isMonomorphicType()) {
            var10000 = throwableSym.tpe_$times();
         } else {
            ((SymbolTable)this.scala$reflect$internal$AnnotationInfos$Annotatable$$$outer()).debuglog(() -> (new StringBuilder(62)).append("Encountered polymorphic exception `").append(throwableSym.fullName('.')).append("` while parsing class file.").toString());
            var10000 = ((Types)this.scala$reflect$internal$AnnotationInfos$Annotatable$$$outer()).existentialAbstraction(throwableSym.typeParams(), throwableSym.tpe_$times(), ((Types)this.scala$reflect$internal$AnnotationInfos$Annotatable$$$outer()).existentialAbstraction$default$3());
         }

         Types.Type throwableTpe = var10000;
         AnnotationInfo$ var10001 = this.scala$reflect$internal$AnnotationInfos$Annotatable$$$outer().AnnotationInfo();
         Types var10002 = (Types)this.scala$reflect$internal$AnnotationInfos$Annotatable$$$outer();
         Symbols.ClassSymbol var10003 = ((Definitions)this.scala$reflect$internal$AnnotationInfos$Annotatable$$$outer()).definitions().ThrowsClass();
         List $colon$colon_this = .MODULE$;
         scala.collection.immutable..colon.colon var10004 = new scala.collection.immutable..colon.colon(throwableTpe, $colon$colon_this);
         $colon$colon_this = null;
         return this.withAnnotation(var10001.apply(var10002.appliedType((Symbols.Symbol)var10003, (List)var10004), new scala.collection.immutable..colon.colon((SymbolTable)this.scala$reflect$internal$AnnotationInfos$Annotatable$$$outer().new Literal((SymbolTable)this.scala$reflect$internal$AnnotationInfos$Annotatable$$$outer().new Constant(throwableTpe)), .MODULE$), .MODULE$));
      }

      // $FF: synthetic method
      static boolean hasAnnotation$(final Annotatable $this, final Symbols.Symbol cls) {
         return $this.hasAnnotation(cls);
      }

      default boolean hasAnnotation(final Symbols.Symbol cls) {
         return this.dropOtherAnnotations(this.annotations(), cls) != .MODULE$;
      }

      // $FF: synthetic method
      static Option getAnnotation$(final Annotatable $this, final Symbols.Symbol cls) {
         return $this.getAnnotation(cls);
      }

      default Option getAnnotation(final Symbols.Symbol cls) {
         List var2 = this.dropOtherAnnotations(this.annotations(), cls);
         if (var2 instanceof scala.collection.immutable..colon.colon) {
            AnnotationInfo ann = (AnnotationInfo)((scala.collection.immutable..colon.colon)var2).head();
            return new Some(ann);
         } else {
            return scala.None..MODULE$;
         }
      }

      // $FF: synthetic method
      static Object removeAnnotation$(final Annotatable $this, final Symbols.Symbol cls) {
         return $this.removeAnnotation(cls);
      }

      default Object removeAnnotation(final Symbols.Symbol cls) {
         return this.filterAnnotations((ann) -> BoxesRunTime.boxToBoolean($anonfun$removeAnnotation$1(cls, ann)));
      }

      Object withAnnotation(final AnnotationInfo annot);

      private List dropOtherAnnotations(final List anns, final Symbols.Symbol cls) {
         while(anns instanceof scala.collection.immutable..colon.colon) {
            scala.collection.immutable..colon.colon var3 = (scala.collection.immutable..colon.colon)anns;
            AnnotationInfo ann = (AnnotationInfo)var3.head();
            List rest = var3.next$access$1();
            if (ann.matches(cls)) {
               return anns;
            }

            anns = rest;
         }

         if (.MODULE$.equals(anns)) {
            return .MODULE$;
         } else {
            throw new MatchError(anns);
         }
      }

      // $FF: synthetic method
      AnnotationInfos scala$reflect$internal$AnnotationInfos$Annotatable$$$outer();

      // $FF: synthetic method
      static boolean $anonfun$staticAnnotations$1(final AnnotationInfo x$1) {
         return x$1.isStatic();
      }

      // $FF: synthetic method
      static boolean $anonfun$removeAnnotation$1(final Symbols.Symbol cls$1, final AnnotationInfo ann) {
         return !ann.matches(cls$1);
      }

      static void $init$(final Annotatable $this) {
      }

      // $FF: synthetic method
      static Object $anonfun$staticAnnotations$1$adapted(final AnnotationInfo x$1) {
         return BoxesRunTime.boxToBoolean($anonfun$staticAnnotations$1(x$1));
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public abstract class ClassfileAnnotArg implements Product, Annotations.JavaArgumentApi {
      // $FF: synthetic field
      public final SymbolTable $outer;

      public Iterator productIterator() {
         return Product.productIterator$(this);
      }

      public String productPrefix() {
         return Product.productPrefix$(this);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$AnnotationInfos$ClassfileAnnotArg$$$outer() {
         return this.$outer;
      }

      public ClassfileAnnotArg() {
         if (AnnotationInfos.this == null) {
            throw null;
         } else {
            this.$outer = AnnotationInfos.this;
            super();
         }
      }
   }

   public class UnmappableAnnotArg$ extends ClassfileAnnotArg implements Serializable {
      public String productPrefix() {
         return "UnmappableAnnotArg";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof UnmappableAnnotArg$;
      }

      public int hashCode() {
         return -2080392777;
      }

      public String toString() {
         return "UnmappableAnnotArg";
      }
   }

   public class LiteralAnnotArg extends ClassfileAnnotArg implements Serializable {
      private final Constants.Constant const;

      public Constants.Constant const() {
         return this.const;
      }

      public String toString() {
         return this.const().escapedStringValue();
      }

      public LiteralAnnotArg copy(final Constants.Constant const) {
         return this.scala$reflect$internal$AnnotationInfos$LiteralAnnotArg$$$outer().new LiteralAnnotArg(const);
      }

      public Constants.Constant copy$default$1() {
         return this.const();
      }

      public String productPrefix() {
         return "LiteralAnnotArg";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.const();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof LiteralAnnotArg;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "const";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return scala.util.hashing.MurmurHash3..MODULE$.productHash(this, -889275714, false);
      }

      public boolean equals(final Object x$1) {
         if (this != x$1) {
            if (x$1 instanceof LiteralAnnotArg && ((LiteralAnnotArg)x$1).scala$reflect$internal$AnnotationInfos$LiteralAnnotArg$$$outer() == this.scala$reflect$internal$AnnotationInfos$LiteralAnnotArg$$$outer()) {
               LiteralAnnotArg var2 = (LiteralAnnotArg)x$1;
               Constants.Constant var10000 = this.const();
               Constants.Constant var3 = var2.const();
               if (var10000 == null) {
                  if (var3 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var3)) {
                  return false;
               }

               if (var2.canEqual(this)) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$AnnotationInfos$LiteralAnnotArg$$$outer() {
         return this.$outer;
      }

      public LiteralAnnotArg(final Constants.Constant const) {
         this.const = const;
      }
   }

   public class LiteralAnnotArg$ extends AbstractFunction1 implements Serializable {
      // $FF: synthetic field
      private final SymbolTable $outer;

      public final String toString() {
         return "LiteralAnnotArg";
      }

      public LiteralAnnotArg apply(final Constants.Constant const) {
         return this.$outer.new LiteralAnnotArg(const);
      }

      public Option unapply(final LiteralAnnotArg x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.const()));
      }

      public LiteralAnnotArg$() {
         if (AnnotationInfos.this == null) {
            throw null;
         } else {
            this.$outer = AnnotationInfos.this;
            super();
         }
      }
   }

   public class ArrayAnnotArg extends ClassfileAnnotArg implements Serializable {
      private final ClassfileAnnotArg[] args;

      public ClassfileAnnotArg[] args() {
         return this.args;
      }

      public String toString() {
         ArraySeq.ofRef var10000 = scala.Predef..MODULE$.wrapRefArray(this.args());
         String mkString_end = "]";
         String mkString_sep = ", ";
         String mkString_start = "[";
         if (var10000 == null) {
            throw null;
         } else {
            return IterableOnceOps.mkString$(var10000, mkString_start, mkString_sep, mkString_end);
         }
      }

      public ArrayAnnotArg copy(final ClassfileAnnotArg[] args) {
         return this.scala$reflect$internal$AnnotationInfos$ArrayAnnotArg$$$outer().new ArrayAnnotArg(args);
      }

      public ClassfileAnnotArg[] copy$default$1() {
         return this.args();
      }

      public String productPrefix() {
         return "ArrayAnnotArg";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.args();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof ArrayAnnotArg;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "args";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return scala.util.hashing.MurmurHash3..MODULE$.productHash(this, -889275714, false);
      }

      public boolean equals(final Object x$1) {
         if (this != x$1) {
            if (x$1 instanceof ArrayAnnotArg && ((ArrayAnnotArg)x$1).scala$reflect$internal$AnnotationInfos$ArrayAnnotArg$$$outer() == this.scala$reflect$internal$AnnotationInfos$ArrayAnnotArg$$$outer()) {
               ArrayAnnotArg var2 = (ArrayAnnotArg)x$1;
               if (this.args() == var2.args() && var2.canEqual(this)) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$AnnotationInfos$ArrayAnnotArg$$$outer() {
         return this.$outer;
      }

      public ArrayAnnotArg(final ClassfileAnnotArg[] args) {
         this.args = args;
      }
   }

   public class ArrayAnnotArg$ extends AbstractFunction1 implements Serializable {
      // $FF: synthetic field
      private final SymbolTable $outer;

      public final String toString() {
         return "ArrayAnnotArg";
      }

      public ArrayAnnotArg apply(final ClassfileAnnotArg[] args) {
         return this.$outer.new ArrayAnnotArg(args);
      }

      public Option unapply(final ArrayAnnotArg x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.args()));
      }

      public ArrayAnnotArg$() {
         if (AnnotationInfos.this == null) {
            throw null;
         } else {
            this.$outer = AnnotationInfos.this;
            super();
         }
      }
   }

   public class NestedAnnotArg extends ClassfileAnnotArg implements Serializable {
      private final AnnotationInfo annInfo;

      public AnnotationInfo annInfo() {
         return this.annInfo;
      }

      public String toString() {
         return this.annInfo().toString();
      }

      public NestedAnnotArg copy(final AnnotationInfo annInfo) {
         return this.scala$reflect$internal$AnnotationInfos$NestedAnnotArg$$$outer().new NestedAnnotArg(annInfo);
      }

      public AnnotationInfo copy$default$1() {
         return this.annInfo();
      }

      public String productPrefix() {
         return "NestedAnnotArg";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.annInfo();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof NestedAnnotArg;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "annInfo";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return scala.util.hashing.MurmurHash3..MODULE$.productHash(this, -889275714, false);
      }

      public boolean equals(final Object x$1) {
         if (this != x$1) {
            if (x$1 instanceof NestedAnnotArg && ((NestedAnnotArg)x$1).scala$reflect$internal$AnnotationInfos$NestedAnnotArg$$$outer() == this.scala$reflect$internal$AnnotationInfos$NestedAnnotArg$$$outer()) {
               NestedAnnotArg var2 = (NestedAnnotArg)x$1;
               AnnotationInfo var10000 = this.annInfo();
               AnnotationInfo var3 = var2.annInfo();
               if (var10000 == null) {
                  if (var3 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var3)) {
                  return false;
               }

               if (var2.canEqual(this)) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$AnnotationInfos$NestedAnnotArg$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final List $anonfun$new$1(final NestedAnnotArg $this) {
         return $this.annInfo().args();
      }

      public NestedAnnotArg(final AnnotationInfo annInfo) {
         this.annInfo = annInfo;
         boolean assert_assertion = annInfo.args().isEmpty();
         if (AnnotationInfos.this == null) {
            throw null;
         } else if (!assert_assertion) {
            throw AnnotationInfos.this.throwAssertionError($anonfun$new$1(this));
         }
      }
   }

   public class NestedAnnotArg$ extends AbstractFunction1 implements Serializable {
      // $FF: synthetic field
      private final SymbolTable $outer;

      public final String toString() {
         return "NestedAnnotArg";
      }

      public NestedAnnotArg apply(final AnnotationInfo annInfo) {
         return this.$outer.new NestedAnnotArg(annInfo);
      }

      public Option unapply(final NestedAnnotArg x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.annInfo()));
      }

      public NestedAnnotArg$() {
         if (AnnotationInfos.this == null) {
            throw null;
         } else {
            this.$outer = AnnotationInfos.this;
            super();
         }
      }
   }

   public class AnnotationInfo$ {
      // $FF: synthetic field
      private final SymbolTable $outer;

      public AnnotationInfo marker(final Types.Type atp) {
         return this.apply(atp, .MODULE$, .MODULE$);
      }

      public LazyAnnotationInfo lazily(final Function0 lazyInfo) {
         return this.$outer.new LazyAnnotationInfo(lazyInfo);
      }

      public ExtraLazyAnnotationInfo lazily(final Function0 lazySymbol, final Function0 lazyInfo) {
         return this.$outer.new ExtraLazyAnnotationInfo(lazySymbol, lazyInfo);
      }

      public AnnotationInfo apply(final Types.Type atp, final List args, final List assocs) {
         return this.$outer.new CompleteAnnotationInfo(atp, args, assocs);
      }

      public Some unapply(final AnnotationInfo info) {
         return new Some(new Tuple3(info.atp(), info.args(), info.assocs()));
      }

      public boolean mkFilter(final Symbols.Symbol category, final boolean defaultRetention, final AnnotationInfo ann) {
         List var10000 = ann.metaAnnotations();
         List var6 = ann.defaultTargets();
         List var5 = var10000;
         if (.MODULE$.equals(var5) && .MODULE$.equals(var6)) {
            return defaultRetention;
         } else if (.MODULE$.equals(var5)) {
            return var6.contains(category);
         } else if (var5 == null) {
            throw null;
         } else {
            for(List exists_these = var5; !exists_these.isEmpty(); exists_these = (List)exists_these.tail()) {
               if (((AnnotationInfo)exists_these.head()).matches(category)) {
                  return true;
               }
            }

            return false;
         }
      }

      // $FF: synthetic method
      public static final boolean $anonfun$mkFilter$1(final Symbols.Symbol category$1, final AnnotationInfo x$2) {
         return x$2.matches(category$1);
      }

      public AnnotationInfo$() {
         if (AnnotationInfos.this == null) {
            throw null;
         } else {
            this.$outer = AnnotationInfos.this;
            super();
         }
      }

      // $FF: synthetic method
      public static final Object $anonfun$mkFilter$1$adapted(final Symbols.Symbol category$1, final AnnotationInfo x$2) {
         return BoxesRunTime.boxToBoolean($anonfun$mkFilter$1(category$1, x$2));
      }
   }

   public class CompleteAnnotationInfo extends AnnotationInfo {
      private final Types.Type atp;
      private final List args;
      private final List assocs;
      private Trees.Tree orig;

      public Types.Type atp() {
         return this.atp;
      }

      public List args() {
         return this.args;
      }

      public List assocs() {
         return this.assocs;
      }

      public Trees.Tree original() {
         return this.orig;
      }

      public CompleteAnnotationInfo setOriginal(final Trees.Tree t) {
         this.orig = t;
         return (CompleteAnnotationInfo)this.setPos(t.pos());
      }

      public String toString() {
         return this.scala$reflect$internal$AnnotationInfos$CompleteAnnotationInfo$$$outer().completeAnnotationToString(this);
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$AnnotationInfos$CompleteAnnotationInfo$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final Types.Type $anonfun$new$2(final CompleteAnnotationInfo $this) {
         return $this.atp();
      }

      public CompleteAnnotationInfo(final Types.Type atp, final List args, final List assocs) {
         this.atp = atp;
         this.args = args;
         this.assocs = assocs;
         boolean assert_assertion = args.isEmpty() || assocs.isEmpty();
         if (AnnotationInfos.this == null) {
            throw null;
         } else if (!assert_assertion) {
            throw AnnotationInfos.this.throwAssertionError(this.atp());
         } else {
            this.orig = AnnotationInfos.this.EmptyTree();
         }
      }
   }

   public class LazyAnnotationInfo extends AnnotationInfo {
      private AnnotationInfo forcedInfo;
      private Function0 lazyInfo;
      private boolean _forced;
      private volatile boolean bitmap$0;

      public boolean forced() {
         return this._forced;
      }

      private AnnotationInfo forcedInfo$lzycompute() {
         synchronized(this){}

         try {
            if (!this.bitmap$0) {
               AnnotationInfo var10001;
               try {
                  var10001 = (AnnotationInfo)this.lazyInfo.apply();
               } finally {
                  this._forced = true;
               }

               this.forcedInfo = var10001;
               this.bitmap$0 = true;
            }
         } catch (Throwable var7) {
            throw var7;
         }

         this.lazyInfo = null;
         return this.forcedInfo;
      }

      private AnnotationInfo forcedInfo() {
         return !this.bitmap$0 ? this.forcedInfo$lzycompute() : this.forcedInfo;
      }

      public Types.Type atp() {
         return this.forcedInfo().atp();
      }

      public List args() {
         return this.forcedInfo().args();
      }

      public List assocs() {
         return this.forcedInfo().assocs();
      }

      public Trees.Tree original() {
         return this.forcedInfo().original();
      }

      public LazyAnnotationInfo setOriginal(final Trees.Tree t) {
         this.forcedInfo().setOriginal(t);
         return this;
      }

      public String toString() {
         return this._forced ? this.forcedInfo().toString() : "@<?>";
      }

      public Position pos() {
         return (Position)(this._forced ? this.forcedInfo().pos() : this.scala$reflect$internal$AnnotationInfos$LazyAnnotationInfo$$$outer().NoPosition());
      }

      public void completeInfo() {
         this.forcedInfo();
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$AnnotationInfos$LazyAnnotationInfo$$$outer() {
         return this.$outer;
      }

      public LazyAnnotationInfo(final Function0 lazyInfo) {
         this.lazyInfo = lazyInfo;
         super();
         this._forced = false;
      }
   }

   public final class ExtraLazyAnnotationInfo extends LazyAnnotationInfo {
      private Symbols.Symbol typeSymbol;
      private Function0 sym;
      private volatile boolean bitmap$0;

      private Symbols.Symbol typeSymbol$lzycompute() {
         synchronized(this){}

         try {
            if (!this.bitmap$0) {
               this.typeSymbol = (Symbols.Symbol)this.sym.apply();
               this.bitmap$0 = true;
            }
         } catch (Throwable var2) {
            throw var2;
         }

         this.sym = null;
         return this.typeSymbol;
      }

      private Symbols.Symbol typeSymbol() {
         return !this.bitmap$0 ? this.typeSymbol$lzycompute() : this.typeSymbol;
      }

      public Symbols.Symbol symbol() {
         return this.forced() ? super.symbol() : this.typeSymbol();
      }

      public ExtraLazyAnnotationInfo(final Function0 sym, final Function0 lazyInfo) {
         this.sym = sym;
         super(lazyInfo);
      }
   }

   public abstract class AnnotationInfo implements Annotations.AnnotationApi {
      private boolean isTrivial;
      private Position rawpos;
      private volatile boolean bitmap$0;
      // $FF: synthetic field
      public final SymbolTable $outer;

      public scala.reflect.api.Trees.TreeApi tree() {
         return Annotations.AnnotationApi.tree$(this);
      }

      public abstract Types.Type atp();

      public abstract List args();

      public abstract List assocs();

      public Types.Type tpe() {
         return this.atp();
      }

      public List scalaArgs() {
         return this.args();
      }

      public ListMap javaArgs() {
         return (ListMap)MapFactory.apply$(scala.collection.immutable.ListMap..MODULE$, this.assocs());
      }

      public abstract Trees.Tree original();

      public abstract AnnotationInfo setOriginal(final Trees.Tree t);

      private boolean isTrivial$lzycompute() {
         synchronized(this){}

         try {
            if (!this.bitmap$0) {
               this.isTrivial = this.atp().isTrivial() && !this.hasArgWhich((x$3) -> BoxesRunTime.boxToBoolean($anonfun$isTrivial$1(x$3)));
               this.bitmap$0 = true;
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.isTrivial;
      }

      public boolean isTrivial() {
         return !this.bitmap$0 ? this.isTrivial$lzycompute() : this.isTrivial;
      }

      public Position pos() {
         return this.rawpos;
      }

      public AnnotationInfo setPos(final Position pos) {
         this.rawpos = pos;
         return this;
      }

      public void completeInfo() {
      }

      public Symbols.Symbol symbol() {
         return this.atp().typeSymbol();
      }

      public List metaAnnotations() {
         Types.Type var1 = this.atp();
         return (List)(var1 instanceof Types.AnnotatedType ? ((Types.AnnotatedType)var1).annotations() : .MODULE$);
      }

      public List defaultTargets() {
         List var10000 = this.symbol().initialize().annotations();
         if (var10000 == null) {
            throw null;
         } else {
            List map_this = var10000;
            Object var61;
            if (map_this == .MODULE$) {
               var61 = .MODULE$;
            } else {
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(((AnnotationInfo)map_this.head()).symbol(), .MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(((AnnotationInfo)map_rest.head()).symbol(), .MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var61 = map_h;
            }

            Object var23 = null;
            Object var24 = null;
            Object var25 = null;
            Object var26 = null;
            Object var27 = null;
            List filter_this = (List)var61;
            boolean filter_filterCommon_isFlipped = false;
            List filter_filterCommon_noneIn$1_l = filter_this;

            while(true) {
               if (filter_filterCommon_noneIn$1_l.isEmpty()) {
                  var61 = .MODULE$;
                  break;
               }

               Object filter_filterCommon_noneIn$1_h = filter_filterCommon_noneIn$1_l.head();
               List filter_filterCommon_noneIn$1_t = (List)filter_filterCommon_noneIn$1_l.tail();
               Symbols.Symbol var22 = (Symbols.Symbol)filter_filterCommon_noneIn$1_h;
               if ($anonfun$defaultTargets$2(this, var22) != filter_filterCommon_isFlipped) {
                  List filter_filterCommon_noneIn$1_allIn$1_remaining = filter_filterCommon_noneIn$1_t;

                  while(true) {
                     if (filter_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                        var61 = filter_filterCommon_noneIn$1_l;
                        break;
                     }

                     Object filter_filterCommon_noneIn$1_allIn$1_x = filter_filterCommon_noneIn$1_allIn$1_remaining.head();
                     var22 = (Symbols.Symbol)filter_filterCommon_noneIn$1_allIn$1_x;
                     if ($anonfun$defaultTargets$2(this, var22) == filter_filterCommon_isFlipped) {
                        scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_l.head(), .MODULE$);
                        List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filter_filterCommon_noneIn$1_l.tail();

                        scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                        for(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filter_filterCommon_noneIn$1_allIn$1_remaining; filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                           scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), .MODULE$);
                           filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                           filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                        }

                        List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_remaining.tail();
                        List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                        while(!filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                           Object filter_filterCommon_noneIn$1_allIn$1_partialFill$1_head = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                           var22 = (Symbols.Symbol)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_head;
                           if ($anonfun$defaultTargets$2(this, var22) != filter_filterCommon_isFlipped) {
                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                           } else {
                              while(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                 scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), .MODULE$);
                                 filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                 filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                                 filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                              }

                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                           }
                        }

                        if (!filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                           filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                        }

                        var61 = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                        Object var35 = null;
                        Object var38 = null;
                        Object var41 = null;
                        Object var44 = null;
                        Object var47 = null;
                        Object var50 = null;
                        Object var53 = null;
                        Object var56 = null;
                        break;
                     }

                     filter_filterCommon_noneIn$1_allIn$1_remaining = (List)filter_filterCommon_noneIn$1_allIn$1_remaining.tail();
                  }

                  Object var31 = null;
                  Object var33 = null;
                  Object var36 = null;
                  Object var39 = null;
                  Object var42 = null;
                  Object var45 = null;
                  Object var48 = null;
                  Object var51 = null;
                  Object var54 = null;
                  Object var57 = null;
                  break;
               }

               filter_filterCommon_noneIn$1_l = filter_filterCommon_noneIn$1_t;
            }

            filter_filterCommon_noneIn$1_l = null;
            Object var29 = null;
            Object var30 = null;
            Object var32 = null;
            Object var34 = null;
            Object var37 = null;
            Object var40 = null;
            Object var43 = null;
            Object var46 = null;
            Object var49 = null;
            Object var52 = null;
            Object var55 = null;
            Object var58 = null;
            List filter_filterCommon_result = (List)var61;
            Statics.releaseFence();
            return filter_filterCommon_result;
         }
      }

      public boolean matches(final Symbols.Symbol clazz) {
         return !(this.symbol() instanceof Symbols.StubSymbol) && this.symbol().isNonBottomSubClass(clazz);
      }

      public boolean hasArgWhich(final Function1 p) {
         List var10000 = this.args();
         if (var10000 == null) {
            throw null;
         } else {
            for(List exists_these = var10000; !exists_these.isEmpty(); exists_these = (List)exists_these.tail()) {
               if (((Trees.Tree)exists_these.head()).exists(p)) {
                  return true;
               }
            }

            return false;
         }
      }

      public boolean isErroneous() {
         if (!this.atp().isErroneous()) {
            List var10000 = this.args();
            if (var10000 == null) {
               throw null;
            }

            List exists_these = var10000;

            while(true) {
               if (exists_these.isEmpty()) {
                  var3 = false;
                  break;
               }

               if (((Trees.Tree)exists_these.head()).isErroneous()) {
                  var3 = true;
                  break;
               }

               exists_these = (List)exists_these.tail();
            }

            Object var2 = null;
            if (!var3) {
               return false;
            }
         }

         return true;
      }

      public final boolean isStatic() {
         return this.symbol().isStaticAnnotation();
      }

      public boolean refsSymbol(final Symbols.Symbol sym) {
         return this.hasArgWhich((x$7) -> BoxesRunTime.boxToBoolean($anonfun$refsSymbol$1(sym, x$7)));
      }

      public Option stringArg(final int index) {
         Option var10000 = this.constantAtIndex(index);
         if (var10000 == null) {
            throw null;
         } else {
            Option map_this = var10000;
            return (Option)(map_this.isEmpty() ? scala.None..MODULE$ : new Some(((Constants.Constant)map_this.get()).stringValue()));
         }
      }

      public Option intArg(final int index) {
         Option var10000 = this.constantAtIndex(index);
         if (var10000 == null) {
            throw null;
         } else {
            Option map_this = var10000;
            return (Option)(map_this.isEmpty() ? scala.None..MODULE$ : new Some(((Constants.Constant)map_this.get()).intValue()));
         }
      }

      public Option booleanArg(final int index) {
         Option var10000 = this.constantAtIndex(index);
         if (var10000 == null) {
            throw null;
         } else {
            Option map_this = var10000;
            return (Option)(map_this.isEmpty() ? scala.None..MODULE$ : new Some(((Constants.Constant)map_this.get()).booleanValue()));
         }
      }

      public Option symbolArg(final int index) {
         Option var10000 = this.argAtIndex(this.args(), index);
         Serializable collect_pf = new Serializable() {
            private static final long serialVersionUID = 0L;
            // $FF: synthetic field
            private final AnnotationInfo $outer;

            public final Object applyOrElse(final Trees.Tree x1, final Function1 default) {
               if (x1 instanceof Trees.Apply) {
                  Trees.Apply var3 = (Trees.Apply)x1;
                  Trees.Tree fun = var3.fun();
                  List var5 = var3.args();
                  if (var5 instanceof scala.collection.immutable..colon.colon) {
                     scala.collection.immutable..colon.colon var6 = (scala.collection.immutable..colon.colon)var5;
                     Trees.Tree var7 = (Trees.Tree)var6.head();
                     List var8 = var6.next$access$1();
                     if (var7 instanceof Trees.Literal) {
                        Constants.Constant str = ((Trees.Literal)var7).value();
                        if (.MODULE$.equals(var8)) {
                           Symbols.Symbol var10000 = fun.symbol();
                           Symbols.TermSymbol var10 = this.$outer.scala$reflect$internal$AnnotationInfos$AnnotationInfo$$$outer().definitions().Symbol_apply();
                           if (var10000 == null) {
                              if (var10 == null) {
                                 return this.$outer.scala$reflect$internal$AnnotationInfos$AnnotationInfo$$$outer().newTermName(str.stringValue());
                              }
                           } else if (var10000.equals(var10)) {
                              return this.$outer.scala$reflect$internal$AnnotationInfos$AnnotationInfo$$$outer().newTermName(str.stringValue());
                           }
                        }
                     }
                  }
               }

               return default.apply(x1);
            }

            public final boolean isDefinedAt(final Trees.Tree x1) {
               if (x1 instanceof Trees.Apply) {
                  Trees.Apply var2 = (Trees.Apply)x1;
                  Trees.Tree fun = var2.fun();
                  List var4 = var2.args();
                  if (var4 instanceof scala.collection.immutable..colon.colon) {
                     scala.collection.immutable..colon.colon var5 = (scala.collection.immutable..colon.colon)var4;
                     Trees.Tree var6 = (Trees.Tree)var5.head();
                     List var7 = var5.next$access$1();
                     if (var6 instanceof Trees.Literal && .MODULE$.equals(var7)) {
                        Symbols.Symbol var10000 = fun.symbol();
                        Symbols.TermSymbol var8 = this.$outer.scala$reflect$internal$AnnotationInfos$AnnotationInfo$$$outer().definitions().Symbol_apply();
                        if (var10000 == null) {
                           if (var8 == null) {
                              return true;
                           }
                        } else if (var10000.equals(var8)) {
                           return true;
                        }
                     }
                  }
               }

               return false;
            }

            public {
               if (AnnotationInfo.this == null) {
                  throw null;
               } else {
                  this.$outer = AnnotationInfo.this;
               }
            }
         };
         if (var10000 == null) {
            throw null;
         } else {
            Option collect_this = var10000;
            return (Option)(!collect_this.isEmpty() ? (Option)collect_pf.lift().apply(collect_this.get()) : scala.None..MODULE$);
         }
      }

      public Option constantAtIndex(final int index) {
         if (this.args().nonEmpty()) {
            Option var6 = this.argAtIndex(this.args(), index);
            if (var6 == null) {
               throw null;
            } else {
               Option flatMap_this = var6;
               if (flatMap_this.isEmpty()) {
                  return scala.None..MODULE$;
               } else {
                  Trees.Tree var5 = (Trees.Tree)flatMap_this.get();
                  return $anonfun$constantAtIndex$1(this, var5);
               }
            }
         } else if (this.assocs().nonEmpty()) {
            Option var10000 = this.argAtIndex(this.assocs(), index);
            Serializable collect_pf = new Serializable() {
               private static final long serialVersionUID = 0L;

               public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
                  if (x1 != null) {
                     ClassfileAnnotArg var3 = (ClassfileAnnotArg)x1._2();
                     if (var3 instanceof LiteralAnnotArg) {
                        return ((LiteralAnnotArg)var3).const();
                     }
                  }

                  return default.apply(x1);
               }

               public final boolean isDefinedAt(final Tuple2 x1) {
                  return x1 != null && (ClassfileAnnotArg)x1._2() instanceof LiteralAnnotArg;
               }
            };
            if (var10000 == null) {
               throw null;
            } else {
               Option collect_this = var10000;
               return (Option)(!collect_this.isEmpty() ? (Option)collect_pf.lift().apply(collect_this.get()) : scala.None..MODULE$);
            }
         } else {
            return scala.None..MODULE$;
         }
      }

      public Option argAtIndex(final List l, final int index) {
         if (l == null) {
            throw null;
         } else {
            return (Option)(index < SeqOps.size$(l) ? new Some(LinearSeqOps.apply$(l, index)) : scala.None..MODULE$);
         }
      }

      public AnnotationInfo transformArgs(final Function1 f) {
         return this.scala$reflect$internal$AnnotationInfos$AnnotationInfo$$$outer().new CompleteAnnotationInfo(this.atp(), (List)f.apply(this.args()), this.assocs());
      }

      public int hashCode() {
         return Statics.anyHash(this.atp()) + Statics.anyHash(this.args()) + Statics.anyHash(this.assocs());
      }

      public boolean equals(final Object other) {
         if (other instanceof AnnotationInfo && ((AnnotationInfo)other).scala$reflect$internal$AnnotationInfos$AnnotationInfo$$$outer() == this.scala$reflect$internal$AnnotationInfos$AnnotationInfo$$$outer()) {
            AnnotationInfo var2 = (AnnotationInfo)other;
            Types.Type var10000 = this.atp();
            Types.Type var3 = var2.atp();
            if (var10000 == null) {
               if (var3 != null) {
                  return false;
               }
            } else if (!var10000.equals(var3)) {
               return false;
            }

            List var6 = this.args();
            List var4 = var2.args();
            if (var6 == null) {
               if (var4 != null) {
                  return false;
               }
            } else if (!var6.equals(var4)) {
               return false;
            }

            var6 = this.assocs();
            List var5 = var2.assocs();
            if (var6 == null) {
               if (var5 == null) {
                  return true;
               }
            } else if (var6.equals(var5)) {
               return true;
            }

            return false;
         } else {
            return false;
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$AnnotationInfos$AnnotationInfo$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public Annotations scala$reflect$api$Annotations$AnnotationApi$$$outer() {
         return this.scala$reflect$internal$AnnotationInfos$AnnotationInfo$$$outer();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$isTrivial$1(final Trees.Tree x$3) {
         return x$3 instanceof Trees.This;
      }

      // $FF: synthetic method
      public static final Symbols.Symbol $anonfun$defaultTargets$1(final AnnotationInfo x$4) {
         return x$4.symbol();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$defaultTargets$2(final AnnotationInfo $this, final Symbols.Symbol sym) {
         return $this.scala$reflect$internal$AnnotationInfos$AnnotationInfo$$$outer().definitions().isMetaAnnotation(sym);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$hasArgWhich$1(final Function1 p$1, final Trees.Tree x$5) {
         return x$5.exists(p$1);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$isErroneous$1(final Trees.Tree x$6) {
         return x$6.isErroneous();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$refsSymbol$1(final Symbols.Symbol sym$1, final Trees.Tree x$7) {
         Symbols.Symbol var10000 = x$7.symbol();
         if (var10000 == null) {
            if (sym$1 == null) {
               return true;
            }
         } else if (var10000.equals(sym$1)) {
            return true;
         }

         return false;
      }

      // $FF: synthetic method
      public static final String $anonfun$stringArg$1(final Constants.Constant x$8) {
         return x$8.stringValue();
      }

      // $FF: synthetic method
      public static final int $anonfun$intArg$1(final Constants.Constant x$9) {
         return x$9.intValue();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$booleanArg$1(final Constants.Constant x$10) {
         return x$10.booleanValue();
      }

      private final Option lit$1(final Trees.Tree tree) {
         while(!(tree instanceof Trees.Literal)) {
            if (tree instanceof Trees.Typed) {
               tree = ((Trees.Typed)tree).expr();
            } else {
               if (!(tree instanceof Trees.Annotated)) {
                  return scala.None..MODULE$;
               }

               tree = ((Trees.Annotated)tree).arg();
            }
         }

         Constants.Constant c = ((Trees.Literal)tree).value();
         return new Some(c);
      }

      // $FF: synthetic method
      public static final Option $anonfun$constantAtIndex$1(final AnnotationInfo $this, final Trees.Tree tree) {
         return $this.lit$1(tree);
      }

      public AnnotationInfo() {
         if (AnnotationInfos.this == null) {
            throw null;
         } else {
            this.$outer = AnnotationInfos.this;
            super();
            this.rawpos = AnnotationInfos.this.NoPosition();
         }
      }

      // $FF: synthetic method
      public static final Object $anonfun$defaultTargets$2$adapted(final AnnotationInfo $this, final Symbols.Symbol sym) {
         return BoxesRunTime.boxToBoolean($anonfun$defaultTargets$2($this, sym));
      }

      // $FF: synthetic method
      public static final Object $anonfun$hasArgWhich$1$adapted(final Function1 p$1, final Trees.Tree x$5) {
         return BoxesRunTime.boxToBoolean($anonfun$hasArgWhich$1(p$1, x$5));
      }

      // $FF: synthetic method
      public static final Object $anonfun$isErroneous$1$adapted(final Trees.Tree x$6) {
         return BoxesRunTime.boxToBoolean($anonfun$isErroneous$1(x$6));
      }

      // $FF: synthetic method
      public static final Object $anonfun$intArg$1$adapted(final Constants.Constant x$9) {
         return BoxesRunTime.boxToInteger($anonfun$intArg$1(x$9));
      }

      // $FF: synthetic method
      public static final Object $anonfun$booleanArg$1$adapted(final Constants.Constant x$10) {
         return BoxesRunTime.boxToBoolean($anonfun$booleanArg$1(x$10));
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class Annotation$ extends Annotations.AnnotationExtractor {
      public AnnotationInfo apply(final Types.Type tpe, final List scalaArgs, final ListMap javaArgs) {
         return this.scala$reflect$internal$AnnotationInfos$Annotation$$$outer().AnnotationInfo().apply(tpe, scalaArgs, javaArgs.toList());
      }

      public Some unapply(final AnnotationInfo annotation) {
         return new Some(new Tuple3(annotation.tpe(), annotation.scalaArgs(), annotation.javaArgs()));
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$AnnotationInfos$Annotation$$$outer() {
         return (SymbolTable)this.$outer;
      }
   }

   public class UnmappableAnnotation$ extends CompleteAnnotationInfo {
      public UnmappableAnnotation$() {
         super(AnnotationInfos.this.NoType(), .MODULE$, .MODULE$);
      }
   }

   public class ErroneousAnnotation extends CompleteAnnotationInfo {
      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$AnnotationInfos$ErroneousAnnotation$$$outer() {
         return this.$outer;
      }

      public ErroneousAnnotation() {
         super(AnnotationInfos.this.ErrorType(), .MODULE$, .MODULE$);
      }
   }

   public class ThrownException$ {
      // $FF: synthetic field
      private final SymbolTable $outer;

      public Option unapply(final AnnotationInfo ann) {
         if (ann != null) {
            Some var2 = this.$outer.AnnotationInfo().unapply(ann);
            if (!var2.isEmpty()) {
               Symbols.Symbol var10000 = ((Types.Type)((Tuple3)var2.value())._1()).typeSymbol();
               Symbols.ClassSymbol var3 = this.$outer.definitions().ThrowsClass();
               if (var10000 == null) {
                  if (var3 != null) {
                     return scala.None..MODULE$;
                  }
               } else if (!var10000.equals(var3)) {
                  return scala.None..MODULE$;
               }
            }
         }

         if (ann != null) {
            Some var4 = this.$outer.AnnotationInfo().unapply(ann);
            if (!var4.isEmpty()) {
               List var5 = (List)((Tuple3)var4.value())._2();
               if (var5 != null) {
                  List var22 = scala.package..MODULE$.List();
                  if (var22 == null) {
                     throw null;
                  }

                  List unapplySeq_this = var22;
                  SeqOps var23 = SeqFactory.unapplySeq$(unapplySeq_this, var5);
                  Object var21 = null;
                  SeqOps var6 = var23;
                  SeqFactory.UnapplySeqWrapper var24 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  SeqFactory.UnapplySeqWrapper var10001 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  new SeqFactory.UnapplySeqWrapper(var6);
                  var24 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  var24 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  int lengthCompare$extension_len = 1;
                  if (var6.lengthCompare(lengthCompare$extension_len) == 0) {
                     var24 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     var24 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                     int apply$extension_i = 0;
                     Trees.Tree var7 = (Trees.Tree)var6.apply(apply$extension_i);
                     if (var7 instanceof Trees.Literal) {
                        Constants.Constant var8 = ((Trees.Literal)var7).value();
                        if (var8 != null) {
                           Object tpe = var8.value();
                           if (tpe instanceof Types.Type && ((Types.Type)tpe).scala$reflect$internal$Types$Type$$$outer() == this.$outer) {
                              Types.Type var10 = (Types.Type)tpe;
                              return new Some(var10);
                           }
                        }
                     }
                  }
               }
            }
         }

         if (ann != null) {
            Some var11 = this.$outer.AnnotationInfo().unapply(ann);
            if (!var11.isEmpty()) {
               Types.Type var12 = (Types.Type)((Tuple3)var11.value())._1();
               if (var12 instanceof Types.TypeRef) {
                  List var13 = ((Types.TypeRef)var12).args();
                  if (var13 instanceof scala.collection.immutable..colon.colon) {
                     Types.Type arg = (Types.Type)((scala.collection.immutable..colon.colon)var13).head();
                     return new Some(arg);
                  }
               }
            }
         }

         if (ann != null) {
            Some var15 = this.$outer.AnnotationInfo().unapply(ann);
            if (!var15.isEmpty()) {
               Types.Type var16 = (Types.Type)((Tuple3)var15.value())._1();
               if (var16 instanceof Types.TypeRef) {
                  List var17 = ((Types.TypeRef)var16).args();
                  if (.MODULE$.equals(var17)) {
                     return new Some(this.$outer.definitions().ThrowableTpe());
                  }
               }
            }
         }

         return scala.None..MODULE$;
      }

      public ThrownException$() {
         if (AnnotationInfos.this == null) {
            throw null;
         } else {
            this.$outer = AnnotationInfos.this;
            super();
         }
      }
   }
}

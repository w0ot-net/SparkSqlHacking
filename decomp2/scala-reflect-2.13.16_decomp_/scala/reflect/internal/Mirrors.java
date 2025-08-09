package scala.reflect.internal;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.ScalaReflectionException;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.package;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeTags;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.internal.util.StatisticsStatics;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing;

@ScalaSignature(
   bytes = "\u0006\u0005\r]e!\u0003-Z!\u0003\r\t\u0001YBH\u0011\u0015Q\u0007\u0001\"\u0001l\t\u0015y\u0007A!\u0011q\r%\u0019y\u0002\u0001I\u0001$\u0003\u0019\t\u0003C\u0004\u0004$\r1\ta!\n\u0007\u000bY\u0004\u0011\u0011A<\t\u0011i,!\u0011!Q\u0001\nmDq!!\u0001\u0006\t\u0003\t\u0019\u0001\u0003\u0005\u0002\b\u0015\u0001\u000b\u0015BA\u0005\u0011\u001d\ty!\u0002C\u0001\u0003#A\u0001\"a\u0005\u0006\r#i\u0016Q\u0003\u0005\n\u0003C)!\u0019!D\u0001\u0003GA\u0011\"a\u000b\u0006\u0005\u00045\t!!\f\t\u0013\u0005URA1A\u0007\u0002\u0005\r\u0002\"CA\u001c\u000b\t\u0007i\u0011AA\u0017\u0011\u001d\tI$\u0002C\u0001\u0003wAq!!\u001d\u0006\t\u0003\t\u0019\bC\u0004\u0002\u0004\u0016!I!!\"\t\u000f\u0005\rU\u0001\"\u0003\u0002$\"9\u00111Q\u0003\u0005\n\u0005%\u0007bBAh\u000b\u0011%\u0011\u0011\u001b\u0005\b\u0003/,A\u0011CAm\u0011\u001d\t\u0019/\u0002C\t\u0003KD\u0001\"a;\u0006\t\u0003i\u0016Q\u001e\u0005\b\u0003g,A\u0011BA{\u0011\u001d\ty0\u0002C\u0001\u0005\u0003Aq!a@\u0006\t\u0003\u0011I\u0002C\u0004\u0003\u001e\u0015!\tAa\b\t\u000f\tuQ\u0001\"\u0001\u0003&!9!\u0011F\u0003\u0005\u0002\t-\u0002b\u0002B \u000b\u0011\u0005!\u0011\t\u0005\b\u0005\u007f)A\u0011\u0001B#\u0011\u001d\u0011y$\u0002C\u0001\u0005\u0017BqA!\u0015\u0006\t\u0003\u0012\u0019\u0006C\u0004\u0003X\u0015!IA!\u0017\t\u000f\t\rT\u0001\"\u0001\u0003f!9!1M\u0003\u0005\u0002\t-\u0004b\u0002B8\u000b\u0011\u0005!\u0011\u000f\u0005\b\u0005k*A\u0011\u0001B<\u0011\u001d\u0011))\u0002C\u0001\u0005\u000fCqA!\"\u0006\t\u0003\u0011Y\tC\u0004\u0003\u0012\u0016!\tEa%\t\u000f\t]U\u0001\"\u0003\u0003\u001a\"9!1U\u0003\u0005\u0002\t\u0015\u0006b\u0002BR\u000b\u0011\u0005!Q\u0017\u0005\b\u0005s+A\u0011\u0001B^\u0011\u001d\u0011I,\u0002C\u0001\u0005\u000bDqA!3\u0006\t\u0003\u0011Y\rC\u0004\u0003Z\u0016!\tAa7\t\u000f\teW\u0001\"\u0001\u0003b\"9!Q]\u0003\u0005\u0002\t\u001d\bb\u0002Bs\u000b\u0011\u0005!Q\u001e\u0005\b\u0005c,A\u0011\tBz\u0011\u001d\u001190\u0002C\u0001\u0005sDqaa\u0002\u0006\t\u000b\u0019I\u0001\u0003\u0004\u0004\u001e\u0015!\ta\u001b\u0004\b\u0007S\u0001\u0011\u0011AB\u0016\u0011!Q\bH!A!\u0002\u0013Y\bbBA\u0001q\u0011\u00051Q\u0006\u0004\n\u0007gA\u0004\u0013aA\u0011\u0007kAQA[\u001e\u0005\u0002-4\u0011ba\b9!\u0003\r\tc!\u0018\t\u000b)lD\u0011A6\t\u000f\r\u0005T\b\"\u0012\u0002\u0012!9\u0011Q\\\u001f\u0005B\r\r\u0004bBB3{\u0011\u00053q\r\u0005\b\u0007GiD\u0011AB\u0013\r\u0019\u0019\u0019\t\u000f\u0001\u0004\u0006\"9\u0011\u0011A\"\u0005\u0002\r\u001d\u0005bBBE\u0007\u0012\u0005\u0013\u0011\u0003\u0005\u000b\u0003WA\u0004R1A\u0005\u0002\r}dABB9q\u0001\u0019\u0019\bC\u0004\u0002\u0002\u001d#\taa\u001e\t\u000f\rmt\t\"\u0011\u0002\u0012!91QK$\u0005B\u0005E\u0001bBB?\u000f\u0012\u0005\u0013\u0011\u0003\u0005\b\u00073:E\u0011IB@\u0011)\t\t\u0003\u000fEC\u0002\u0013\u000511\u0012\u0004\u0007\u0007sA\u0004aa\u000f\t\u000f\u0005\u0005a\n\"\u0001\u0004B!91Q\t(\u0005B\u0005E\u0001BCA\u001cq!\u0015\r\u0011\"\u0001\u0004\\\u001911q\t\u001d\u0001\u0007\u0013Bq!!\u0001S\t\u0003\u0019\t\u0006C\u0004\u0004VI#\t%!\u0005\t\u000f\r]#\u000b\"\u0011\u0002\u0012!91\u0011\f*\u0005B\rm\u0003BCA\u001bq!\u0015\r\u0011\"\u0001\u0004\u000e\n9Q*\u001b:s_J\u001c(B\u0001.\\\u0003!Ig\u000e^3s]\u0006d'B\u0001/^\u0003\u001d\u0011XM\u001a7fGRT\u0011AX\u0001\u0006g\u000e\fG.Y\u0002\u0001'\r\u0001\u0011-\u001a\t\u0003E\u000el\u0011!X\u0005\u0003Iv\u0013a!\u00118z%\u00164\u0007C\u00014j\u001b\u00059'B\u00015\\\u0003\r\t\u0007/[\u0005\u00031\u001e\fa\u0001J5oSR$C#\u00017\u0011\u0005\tl\u0017B\u00018^\u0005\u0011)f.\u001b;\u0003\r5K'O]8s#\t\tH\u000f\u0005\u0002ce&\u00111/\u0018\u0002\u0005\u001dVdG\u000e\u0005\u0002v\u000b5\t\u0001AA\u0005S_>$8OQ1tKN\u0011Q\u0001\u001f\t\u0004Mf,\u0018BA8h\u0003%\u0011xn\u001c;Po:,'\u000f\u0005\u0002vy&\u0011QP \u0002\u0007'fl'm\u001c7\n\u0005}L&aB*z[\n|Gn]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007Q\f)\u0001C\u0003{\u000f\u0001\u000710A\u0006j]&$\u0018.\u00197ju\u0016$\u0007c\u00012\u0002\f%\u0019\u0011QB/\u0003\u000f\t{w\u000e\\3b]\u0006\u0019\u0012n]'jeJ|'/\u00138ji&\fG.\u001b>fIV\u0011\u0011\u0011B\u0001\u000be>|G\u000fT8bI\u0016\u0014XCAA\f!\r)\u0018\u0011D\u0005\u0005\u00037\tiB\u0001\u0005MCjLH+\u001f9f\u0013\r\ty\"\u0017\u0002\u0006)f\u0004Xm]\u0001\n%>|Go\u00117bgN,\"!!\n\u0011\u0007U\f9#C\u0002\u0002*y\u00141b\u00117bgN\u001c\u00160\u001c2pY\u0006Y!k\\8u!\u0006\u001c7.Y4f+\t\ty\u0003E\u0002v\u0003cI1!a\r\u007f\u00051iu\u000eZ;mKNKXNY8m\u0003E)U\u000e\u001d;z!\u0006\u001c7.Y4f\u00072\f7o]\u0001\r\u000b6\u0004H/\u001f)bG.\fw-Z\u0001\tgfl'm\u001c7PMV!\u0011QHA0)\u0011\ty$!\u0014\u0011\t\u0005\u0005\u0013\u0011\n\b\u0005\u0003\u0007\n)%D\u0001\u0006\u0013\r\t9%_\u0001\tk:Lg/\u001a:tK&\u0019\u00111\n@\u0003\u0015QK\b/Z*z[\n|G\u000eC\u0005\u0002P=\t\t\u0011q\u0001\u0002R\u0005QQM^5eK:\u001cW\rJ\u0019\u0011\r\u0005\u0005\u00131KA.\u0013\u0011\t)&a\u0016\u0003\u0017]+\u0017m\u001b+za\u0016$\u0016mZ\u0005\u0004\u00033:'\u0001\u0003+za\u0016$\u0016mZ:\u0011\t\u0005u\u0013q\f\u0007\u0001\t\u001d\t\tg\u0004b\u0001\u0003G\u0012\u0011\u0001V\t\u0005\u0003K\nY\u0007E\u0002c\u0003OJ1!!\u001b^\u0005\u001dqu\u000e\u001e5j]\u001e\u00042AYA7\u0013\r\ty'\u0018\u0002\u0004\u0003:L\u0018A\u00054j]\u0012lU-\u001c2fe\u001a\u0013x.\u001c*p_R$2a_A;\u0011\u001d\t9\b\u0005a\u0001\u0003s\n\u0001BZ;mY:\u000bW.\u001a\t\u0004k\u0006m\u0014\u0002BA?\u0003\u007f\u0012AAT1nK&\u0019\u0011\u0011Q-\u0003\u000b9\u000bW.Z:\u0002!\u001d,G/T8ek2,wJ]\"mCN\u001cH#B>\u0002\b\u0006-\u0005bBAE#\u0001\u0007\u0011\u0011P\u0001\u0005a\u0006$\b\u000eC\u0004\u0002\u000eF\u0001\r!a$\u0002\u00071,g\u000eE\u0002c\u0003#K1!a%^\u0005\rIe\u000e\u001e\u0015\u0004#\u0005]\u0005\u0003BAM\u0003?k!!a'\u000b\u0007\u0005uU,\u0001\u0006b]:|G/\u0019;j_:LA!!)\u0002\u001c\n1QO\\;tK\u0012$ra_AS\u0003{\u000by\fC\u0004\u0002\nJ\u0001\r!a*\u0011\t\u0005%\u0016q\u0017\b\u0005\u0003W\u000b\u0019\fE\u0002\u0002.vk!!a,\u000b\u0007\u0005Ev,\u0001\u0004=e>|GOP\u0005\u0004\u0003kk\u0016A\u0002)sK\u0012,g-\u0003\u0003\u0002:\u0006m&AB*ue&twMC\u0002\u00026vCq!!$\u0013\u0001\u0004\ty\tC\u0004\u0002BJ\u0001\r!a1\u0002\rQ|g*Y7f!\u001d\u0011\u0017QYAT\u0003sJ1!a2^\u0005%1UO\\2uS>t\u0017\u0007F\u0003|\u0003\u0017\fi\rC\u0004\u0002\nN\u0001\r!a*\t\u000f\u0005\u00057\u00031\u0001\u0002D\u0006\u00192\u000f^1uS\u000elu\u000eZ;mK>\u00138\t\\1tgR)10a5\u0002V\"9\u0011\u0011\u0012\u000bA\u0002\u0005\u001d\u0006bBAa)\u0001\u0007\u00111Y\u0001\u0012[&\u0014(o\u001c:NSN\u001c\u0018N\\4I_>\\G#B>\u0002\\\u0006}\u0007BBAo+\u0001\u000710A\u0003po:,'\u000fC\u0004\u0002bV\u0001\r!!\u001f\u0002\t9\fW.Z\u0001\u0014k:Lg/\u001a:tK6K7o]5oO\"{wn\u001b\u000b\u0006w\u0006\u001d\u0018\u0011\u001e\u0005\u0007\u0003;4\u0002\u0019A>\t\u000f\u0005\u0005h\u00031\u0001\u0002z\u0005YQ.[:tS:<\u0007j\\8l)\u0015Y\u0018q^Ay\u0011\u0019\tin\u0006a\u0001w\"9\u0011\u0011]\fA\u0002\u0005e\u0014!E3ogV\u0014Xm\u00117bgN\u001c\u00160\u001c2pYR1\u0011QEA|\u0003wDq!!?\u0019\u0001\u0004\t9+\u0001\u0005gk2dg.Y7f\u0011\u0019\ti\u0010\u0007a\u0001w\u0006\u00191/_7\u0002\u001d\u001d,Go\u00117bgN\u0014\u0015PT1nKR!\u0011Q\u0005B\u0002\u0011\u001d\tI0\u0007a\u0001\u0003sB3\"\u0007B\u0004\u0005\u001b\u0011yAa\u0005\u0003\u0016A\u0019!M!\u0003\n\u0007\t-QL\u0001\u0006eKB\u0014XmY1uK\u0012\fq!\\3tg\u0006<W-\t\u0002\u0003\u0012\u0005\u0019Sk]3!_Z,'\u000f\\8bI\u0002\"\b.\u0019;!C\u000e\u001cW\r\u001d;tA\u0005\u00043\u000b\u001e:j]\u001et\u0013!B:j]\u000e,\u0017E\u0001B\f\u0003\u0019\u0011d&M\u001a/aQ!\u0011Q\u0005B\u000e\u0011\u001d\tIP\u0007a\u0001\u0003O\u000b\u0001cZ3u%\u0016\fX/\u001b:fI\u000ec\u0017m]:\u0015\r\u0005\u0015\"\u0011\u0005B\u0012\u0011\u001d\tIp\u0007a\u0001\u0003OCq!!1\u001c\u0001\u0004\t\u0019\r\u0006\u0003\u0002&\t\u001d\u0002bBA}9\u0001\u0007\u0011qU\u0001\u000ee\u0016\fX/\u001b:fI\u000ec\u0017m]:\u0016\t\t5\"Q\b\u000b\u0005\u0003K\u0011y\u0003C\u0005\u00032u\t\t\u0011q\u0001\u00034\u0005QQM^5eK:\u001cW\r\n\u001a\u0011\r\tU\"q\u0007B\u001e\u001b\u0005Y\u0016b\u0001B\u001d7\nA1\t\\1tgR\u000bw\r\u0005\u0003\u0002^\tuBaBA1;\t\u0007\u00111M\u0001\u0012O\u0016$8\t\\1tg&3G)\u001a4j]\u0016$GcA>\u0003D!9\u0011\u0011 \u0010A\u0002\u0005\u001dFcA>\u0003H!9\u0011\u0011`\u0010A\u0002\u0005e\u0004fC\u0010\u0003\b\t5!q\u0002B\n\u0005+!Ra\u001fB'\u0005\u001fBq!!?!\u0001\u0004\t9\u000bC\u0004\u0002B\u0002\u0002\r!a1\u0002\u0017M$\u0018\r^5d\u00072\f7o\u001d\u000b\u0005\u0003K\u0011)\u0006C\u0004\u0002z\u0006\u0002\r!a*\u0002%\u0015t7/\u001e:f\u001b>$W\u000f\\3Ts6\u0014w\u000e\u001c\u000b\t\u0003_\u0011YF!\u0018\u0003`!9\u0011\u0011 \u0012A\u0002\u0005\u001d\u0006BBA\u007fE\u0001\u00071\u0010C\u0004\u0003b\t\u0002\r!!\u0003\u0002\u001b\u0005dGn\\<QC\u000e\\\u0017mZ3t\u0003=9W\r^'pIVdWMQ=OC6,G\u0003BA\u0018\u0005OBq!!?$\u0001\u0004\tI\bK\u0006$\u0005\u000f\u0011iAa\u0004\u0003\u0014\tUA\u0003BA\u0018\u0005[Bq!!?%\u0001\u0004\t9+A\thKR\u0014V-];je\u0016$Wj\u001c3vY\u0016$B!a\f\u0003t!9\u0011\u0011`\u0013A\u0002\u0005\u001d\u0016A\u0004:fcVL'/\u001a3N_\u0012,H.Z\u000b\u0005\u0005s\u0012\u0019\t\u0006\u0003\u00020\tm\u0004\"\u0003B?M\u0005\u0005\t9\u0001B@\u0003))g/\u001b3f]\u000e,Ge\r\t\u0007\u0005k\u00119D!!\u0011\t\u0005u#1\u0011\u0003\b\u0003C2#\u0019AA2\u0003I9W\r^'pIVdW-\u00134EK\u001aLg.\u001a3\u0015\u0007m\u0014I\tC\u0004\u0002z\u001e\u0002\r!a*\u0015\u0007m\u0014i\tC\u0004\u0002z\"\u0002\r!!\u001f)\u0017!\u00129A!\u0004\u0003\u0010\tM!QC\u0001\rgR\fG/[2N_\u0012,H.\u001a\u000b\u0005\u0003_\u0011)\nC\u0004\u0002z&\u0002\r!a*\u0002'\u0015t7/\u001e:f!\u0006\u001c7.Y4f'fl'm\u001c7\u0015\u0011\u0005=\"1\u0014BO\u0005?Cq!!?+\u0001\u0004\t9\u000b\u0003\u0004\u0002~*\u0002\ra\u001f\u0005\b\u0005CS\u0003\u0019AA\u0005\u00031\tG\u000e\\8x\u001b>$W\u000f\\3t\u0003)9W\r\u001e)bG.\fw-\u001a\u000b\u0005\u0003_\u00119\u000bC\u0004\u0002z.\u0002\rA!+\u0011\u0007U\u0014Y+\u0003\u0003\u0003.\u0006}$\u0001\u0003+fe6t\u0015-\\3)\u0017-\u00129A!\u0004\u00032\nM!QC\u0011\u0003\u0005g\u000b1%^:fA=4XM\u001d7pC\u0012\u0004C\u000f[1uA\u0005\u001c7-\u001a9ug\u0002\n\u0007e\u0015;sS:<g\u0006\u0006\u0003\u00020\t]\u0006bBA}Y\u0001\u0007\u0011qU\u0001\u0014O\u0016$\b+Y2lC\u001e,\u0017J\u001a#fM&tW\r\u001a\u000b\u0004w\nu\u0006bBA}[\u0001\u0007!\u0011\u0016\u0015\f[\t\u001d!Q\u0002BY\u0005'\u0011\t-\t\u0002\u0003D\u00069!GL\u00193]E\nDcA>\u0003H\"9\u0011\u0011 \u0018A\u0002\u0005\u001d\u0016AE4fiJ+\u0017/^5sK\u0012\u0004\u0016mY6bO\u0016$B!a\f\u0003N\"9\u0011\u0011`\u0018A\u0002\u0005\u001d\u0006fC\u0018\u0003\b\t5!\u0011\u001bB\n\u0005+\f#Aa5\u0002\u001dU\u001cX\rI4fiB\u000b7m[1hK\u0006\u0012!q[\u0001\u0007e9\n\u0014G\f\u0019\u0002!\u001d,G\u000fU1dW\u0006<Wm\u00142kK\u000e$H\u0003BA\u0018\u0005;Dq!!?1\u0001\u0004\u0011I\u000bK\u00061\u0005\u000f\u0011iA!-\u0003\u0014\t\u0005G\u0003BA\u0018\u0005GDq!!?2\u0001\u0004\t9+A\rhKR\u0004\u0016mY6bO\u0016|%M[3di&3G)\u001a4j]\u0016$GcA>\u0003j\"9\u0011\u0011 \u001aA\u0002\t%\u0006f\u0003\u001a\u0003\b\t5!\u0011\u0017B\n\u0005\u0003$2a\u001fBx\u0011\u001d\tIp\ra\u0001\u0003O\u000bQb\u001d;bi&\u001c\u0007+Y2lC\u001e,G\u0003BA\u0018\u0005kDq!!?5\u0001\u0004\t9+A\u0006fe\u0006\u001cXO]3OC6,W\u0003\u0002B~\u0007\u000b!B!a*\u0003~\"I!q`\u001b\u0002\u0002\u0003\u000f1\u0011A\u0001\u000bKZLG-\u001a8dK\u0012\"\u0004C\u0002B\u001b\u0005o\u0019\u0019\u0001\u0005\u0003\u0002^\r\u0015AaBA1k\t\u0007\u00111M\u0001\foJ\f\u0007/T5tg&tw\rF\u0002|\u0007\u0017A\u0001b!\u00047\t\u0003\u00071qB\u0001\u0005E>$\u0017\u0010\u0005\u0003c\u0007#Y\u0018bAB\n;\nAAHY=oC6,g\bK\u00027\u0007/\u00012AYB\r\u0013\r\u0019Y\"\u0018\u0002\u0007S:d\u0017N\\3\u0002\t%t\u0017\u000e\u001e\u0002\u000b%>|GoU=nE>d7CA\u0002|\u0003\u0019i\u0017N\u001d:peV\u00111q\u0005\t\u0003k\n\u0011QAU8piN\u001c\"\u0001\u000f;\u0015\t\r=2\u0011\u0007\t\u0003kbBQA\u001f\u001eA\u0002m\u0014qbV3mY.swn\u001e8Ts6\u0014w\u000e\\\n\u0003wmLCa\u000f(S{\taQ)\u001c9usB\u000b7m[1hKN)a*a\f\u0004>A\u00191qH\u001e\u000e\u0003a\"\"aa\u0011\u0011\u0007\r}b*\u0001\bjg\u0016k\u0007\u000f^=QC\u000e\\\u0017mZ3\u0003#\u0015k\u0007\u000f^=QC\u000e\\\u0017mZ3DY\u0006\u001c8oE\u0003S\u0007\u0017\u001ai\u0004E\u0002v\u0007\u001bJ1aa\u0014\u007f\u0005I\u0001\u0016mY6bO\u0016\u001cE.Y:t'fl'm\u001c7\u0015\u0005\rM\u0003cAB %\u0006y\u0011n]#gM\u0016\u001cG/\u001b<f%>|G/A\njg\u0016k\u0007\u000f^=QC\u000e\\\u0017mZ3DY\u0006\u001c8/\u0001\u0007t_V\u00148-Z'pIVdW-\u0006\u0002\u0004DM1Qh_B\u001f\u0007?\u0002\"!^\u0002\u0002\u0019%\u001c(k\\8u'fl'm\u001c7\u0016\u0003m\f!\u0002^=qK>3G\u000b[5t+\t\u0019I\u0007E\u0002v\u0007WJAa!\u001c\u0002\u001e\t!A+\u001f9fS\riti\u0011\u0002\n%>|Go\u00117bgN\u001cRaRB&\u0007k\u00022aa\u0010>)\t\u0019I\bE\u0002\u0004@\u001d\u000ba![:S_>$\u0018!D5t\u001d\u0016\u001cH/\u001a3DY\u0006\u001c8/\u0006\u0002\u0004\u0002B\u00191qH\"\u0003\u0017I{w\u000e\u001e)bG.\fw-Z\n\u0006\u0007\u0006=2Q\u000f\u000b\u0003\u0007\u0003\u000bQ\"[:S_>$\b+Y2lC\u001e,WCAB=+\t\u0019\u0019\u0006\u0005\u0003\u0004\u0012\u000eMU\"A-\n\u0007\rU\u0015LA\u0006Ts6\u0014w\u000e\u001c+bE2,\u0007"
)
public interface Mirrors extends scala.reflect.api.Mirrors {
   static void $init$(final Mirrors $this) {
   }

   public abstract class RootsBase extends Mirror {
      private final Symbols.Symbol rootOwner;
      private boolean initialized;
      // $FF: synthetic field
      public final SymbolTable $outer;

      public boolean isMirrorInitialized() {
         return this.initialized;
      }

      public abstract Types.LazyType rootLoader();

      public abstract Symbols.ClassSymbol RootClass();

      public abstract Symbols.ModuleSymbol RootPackage();

      public abstract Symbols.ClassSymbol EmptyPackageClass();

      public abstract Symbols.ModuleSymbol EmptyPackage();

      public Symbols.TypeSymbol symbolOf(final TypeTags.WeakTypeTag evidence$1) {
         return (Symbols.TypeSymbol)((Types.Type)this.universe().weakTypeTag(evidence$1).in(this).tpe()).typeSymbolDirect().asType();
      }

      public Symbols.Symbol findMemberFromRoot(final Names.Name fullName) {
         List segs = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().nme().segments(fullName.toString(), fullName.isTermName());
         return (Symbols.Symbol)(segs.isEmpty() ? this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol() : this.scala$reflect$internal$Mirrors$RootsBase$$$outer().definitions().findNamedMember((List)segs.tail(), this.RootClass().info().member((Names.Name)segs.head())));
      }

      private Symbols.Symbol getModuleOrClass(final Names.Name path, final int len) {
         String getModuleOrClass_path = path.toString();
         int getModuleOrClass_point = getModuleOrClass_path.lastIndexOf(46, len - 1);
         Symbols.Symbol getModuleOrClass_owner = (Symbols.Symbol)(getModuleOrClass_point > 0 ? this.getModuleOrClass(getModuleOrClass_path, getModuleOrClass_point, (x$2) -> this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTermName(x$2)) : this.RootClass());
         String var13 = getModuleOrClass_path.substring(getModuleOrClass_point + 1, len);
         Names.Name getModuleOrClass_name = path.newName(var13);
         Symbols.Symbol getModuleOrClass_sym = getModuleOrClass_owner.info().member(getModuleOrClass_name);
         Symbols.Symbol getModuleOrClass_result = getModuleOrClass_name.isTermName() ? getModuleOrClass_sym.suchThat((x$3) -> BoxesRunTime.boxToBoolean($anonfun$getModuleOrClass$3(x$3))) : getModuleOrClass_sym;
         Symbols.NoSymbol var9 = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
         if (getModuleOrClass_result == null) {
            if (var9 != null) {
               return getModuleOrClass_result;
            }
         } else if (!getModuleOrClass_result.equals(var9)) {
            return getModuleOrClass_result;
         }

         MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var16 = MutableSettings$.MODULE$;
         MutableSettings getModuleOrClass_SettingsOps_settings = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().settings();
         MutableSettings var17 = getModuleOrClass_SettingsOps_settings;
         getModuleOrClass_SettingsOps_settings = null;
         MutableSettings getModuleOrClass_isDebug$extension_$this = var17;
         boolean var18 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(getModuleOrClass_isDebug$extension_$this.debug().value());
         getModuleOrClass_isDebug$extension_$this = null;
         if (var18) {
            this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info());
            this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info().members());
         }

         Symbols.Symbol var19 = this.missingHook(getModuleOrClass_owner, getModuleOrClass_name);
         if (var19 == null) {
            throw null;
         } else {
            Symbols.Symbol getModuleOrClass_orElse_this = var19;
            if (getModuleOrClass_orElse_this != getModuleOrClass_orElse_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
               return getModuleOrClass_orElse_this;
            } else {
               throw $anonfun$getModuleOrClass$6(this, getModuleOrClass_name, getModuleOrClass_path);
            }
         }
      }

      private Symbols.Symbol getModuleOrClass(final String path, final int len, final Function1 toName) {
         int point = path.lastIndexOf(46, len - 1);
         Symbols.Symbol owner = (Symbols.Symbol)(point > 0 ? this.getModuleOrClass(path, point, (x$2) -> this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTermName(x$2)) : this.RootClass());
         Names.Name name = (Names.Name)toName.apply(path.substring(point + 1, len));
         Symbols.Symbol sym = owner.info().member(name);
         Symbols.Symbol result = name.isTermName() ? sym.suchThat((x$3) -> BoxesRunTime.boxToBoolean($anonfun$getModuleOrClass$3(x$3))) : sym;
         Symbols.NoSymbol var9 = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
         if (result == null) {
            if (var9 != null) {
               return result;
            }
         } else if (!result.equals(var9)) {
            return result;
         }

         MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var15 = MutableSettings$.MODULE$;
         MutableSettings SettingsOps_settings = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().settings();
         MutableSettings var16 = SettingsOps_settings;
         SettingsOps_settings = null;
         MutableSettings isDebug$extension_$this = var16;
         boolean var17 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(isDebug$extension_$this.debug().value());
         isDebug$extension_$this = null;
         if (var17) {
            this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info());
            this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info().members());
         }

         Symbols.Symbol var18 = this.missingHook(owner, name);
         if (var18 == null) {
            throw null;
         } else {
            Symbols.Symbol orElse_this = var18;
            if (orElse_this != orElse_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
               return orElse_this;
            } else {
               throw $anonfun$getModuleOrClass$6(this, name, path);
            }
         }
      }

      private Symbols.Symbol getModuleOrClass(final String path, final Function1 toName) {
         int getModuleOrClass_len = path.length();
         int getModuleOrClass_point = path.lastIndexOf(46, getModuleOrClass_len - 1);
         Symbols.Symbol getModuleOrClass_owner = (Symbols.Symbol)(getModuleOrClass_point > 0 ? this.getModuleOrClass(path, getModuleOrClass_point, (x$2) -> this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTermName(x$2)) : this.RootClass());
         Names.Name getModuleOrClass_name = (Names.Name)toName.apply(path.substring(getModuleOrClass_point + 1, getModuleOrClass_len));
         Symbols.Symbol getModuleOrClass_sym = getModuleOrClass_owner.info().member(getModuleOrClass_name);
         Symbols.Symbol getModuleOrClass_result = getModuleOrClass_name.isTermName() ? getModuleOrClass_sym.suchThat((x$3) -> BoxesRunTime.boxToBoolean($anonfun$getModuleOrClass$3(x$3))) : getModuleOrClass_sym;
         Symbols.NoSymbol var9 = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
         if (getModuleOrClass_result == null) {
            if (var9 != null) {
               return getModuleOrClass_result;
            }
         } else if (!getModuleOrClass_result.equals(var9)) {
            return getModuleOrClass_result;
         }

         MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var15 = MutableSettings$.MODULE$;
         MutableSettings getModuleOrClass_SettingsOps_settings = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().settings();
         MutableSettings var16 = getModuleOrClass_SettingsOps_settings;
         getModuleOrClass_SettingsOps_settings = null;
         MutableSettings getModuleOrClass_isDebug$extension_$this = var16;
         boolean var17 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(getModuleOrClass_isDebug$extension_$this.debug().value());
         getModuleOrClass_isDebug$extension_$this = null;
         if (var17) {
            this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info());
            this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info().members());
         }

         Symbols.Symbol var18 = this.missingHook(getModuleOrClass_owner, getModuleOrClass_name);
         if (var18 == null) {
            throw null;
         } else {
            Symbols.Symbol getModuleOrClass_orElse_this = var18;
            if (getModuleOrClass_orElse_this != getModuleOrClass_orElse_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
               return getModuleOrClass_orElse_this;
            } else {
               throw $anonfun$getModuleOrClass$6(this, getModuleOrClass_name, path);
            }
         }
      }

      private Symbols.Symbol staticModuleOrClass(final String path, final Function1 toName) {
         if (!.MODULE$.contains$extension(path, '.')) {
            return this.EmptyPackageClass().info().decl((Names.Name)toName.apply(path));
         } else {
            int getModuleOrClass_getModuleOrClass_len = path.length();
            int getModuleOrClass_getModuleOrClass_point = path.lastIndexOf(46, getModuleOrClass_getModuleOrClass_len - 1);
            Object var40;
            if (getModuleOrClass_getModuleOrClass_point > 0) {
               label97: {
                  Symbols.Symbol getModuleOrClass_result;
                  label120: {
                     int getModuleOrClass_point = path.lastIndexOf(46, getModuleOrClass_getModuleOrClass_point - 1);
                     Symbols.Symbol getModuleOrClass_owner = (Symbols.Symbol)(getModuleOrClass_point > 0 ? this.getModuleOrClass(path, getModuleOrClass_point, (x$2) -> this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTermName(x$2)) : this.RootClass());
                     String var22 = path.substring(getModuleOrClass_point + 1, getModuleOrClass_getModuleOrClass_point);
                     Names.Name getModuleOrClass_name = $anonfun$getModuleOrClass$2(this, var22);
                     Symbols.Symbol getModuleOrClass_sym = getModuleOrClass_owner.info().member(getModuleOrClass_name);
                     getModuleOrClass_result = getModuleOrClass_name.isTermName() ? getModuleOrClass_sym.suchThat((x$3) -> BoxesRunTime.boxToBoolean($anonfun$getModuleOrClass$3(x$3))) : getModuleOrClass_sym;
                     Symbols.NoSymbol var18 = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
                     if (getModuleOrClass_result == null) {
                        if (var18 != null) {
                           break label120;
                        }
                     } else if (!getModuleOrClass_result.equals(var18)) {
                        break label120;
                     }

                     MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
                     MutableSettings$ var36 = MutableSettings$.MODULE$;
                     MutableSettings getModuleOrClass_SettingsOps_settings = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().settings();
                     MutableSettings var37 = getModuleOrClass_SettingsOps_settings;
                     getModuleOrClass_SettingsOps_settings = null;
                     MutableSettings getModuleOrClass_isDebug$extension_$this = var37;
                     boolean var38 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(getModuleOrClass_isDebug$extension_$this.debug().value());
                     getModuleOrClass_isDebug$extension_$this = null;
                     if (var38) {
                        this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info());
                        this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info().members());
                     }

                     Symbols.Symbol var39 = this.missingHook(getModuleOrClass_owner, getModuleOrClass_name);
                     if (var39 == null) {
                        throw null;
                     }

                     Symbols.Symbol getModuleOrClass_orElse_this = var39;
                     if (getModuleOrClass_orElse_this == getModuleOrClass_orElse_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                        throw $anonfun$getModuleOrClass$6(this, getModuleOrClass_name, path);
                     }

                     var40 = getModuleOrClass_orElse_this;
                     getModuleOrClass_orElse_this = null;
                     break label97;
                  }

                  var40 = getModuleOrClass_result;
               }

               Object var25 = null;
               Object var26 = null;
               Object var27 = null;
               Object var28 = null;
               Object var29 = null;
               Object var31 = null;
               Object var33 = null;
               Object var35 = null;
            } else {
               var40 = this.RootClass();
            }

            Symbols.Symbol getModuleOrClass_getModuleOrClass_owner = (Symbols.Symbol)var40;
            Names.Name getModuleOrClass_getModuleOrClass_name = (Names.Name)toName.apply(path.substring(getModuleOrClass_getModuleOrClass_point + 1, getModuleOrClass_getModuleOrClass_len));
            Symbols.Symbol getModuleOrClass_getModuleOrClass_sym = getModuleOrClass_getModuleOrClass_owner.info().member(getModuleOrClass_getModuleOrClass_name);
            Symbols.Symbol getModuleOrClass_getModuleOrClass_result = getModuleOrClass_getModuleOrClass_name.isTermName() ? getModuleOrClass_getModuleOrClass_sym.suchThat((x$3) -> BoxesRunTime.boxToBoolean($anonfun$getModuleOrClass$3(x$3))) : getModuleOrClass_getModuleOrClass_sym;
            Symbols.NoSymbol var9 = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
            if (getModuleOrClass_getModuleOrClass_result == null) {
               if (var9 != null) {
                  return getModuleOrClass_getModuleOrClass_result;
               }
            } else if (!getModuleOrClass_getModuleOrClass_result.equals(var9)) {
               return getModuleOrClass_getModuleOrClass_result;
            }

            MutableSettings.SettingsOps$ var41 = MutableSettings.SettingsOps$.MODULE$;
            MutableSettings$ var42 = MutableSettings$.MODULE$;
            MutableSettings getModuleOrClass_getModuleOrClass_SettingsOps_settings = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().settings();
            MutableSettings var43 = getModuleOrClass_getModuleOrClass_SettingsOps_settings;
            getModuleOrClass_getModuleOrClass_SettingsOps_settings = null;
            MutableSettings getModuleOrClass_getModuleOrClass_isDebug$extension_$this = var43;
            boolean var44 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(getModuleOrClass_getModuleOrClass_isDebug$extension_$this.debug().value());
            getModuleOrClass_getModuleOrClass_isDebug$extension_$this = null;
            if (var44) {
               this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info());
               this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info().members());
            }

            Symbols.Symbol var45 = this.missingHook(getModuleOrClass_getModuleOrClass_owner, getModuleOrClass_getModuleOrClass_name);
            if (var45 == null) {
               throw null;
            } else {
               Symbols.Symbol getModuleOrClass_getModuleOrClass_orElse_this = var45;
               if (getModuleOrClass_getModuleOrClass_orElse_this != getModuleOrClass_getModuleOrClass_orElse_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                  return getModuleOrClass_getModuleOrClass_orElse_this;
               } else {
                  throw $anonfun$getModuleOrClass$6(this, getModuleOrClass_getModuleOrClass_name, path);
               }
            }
         }
      }

      public Symbols.Symbol mirrorMissingHook(final Symbols.Symbol owner, final Names.Name name) {
         return this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
      }

      public Symbols.Symbol universeMissingHook(final Symbols.Symbol owner, final Names.Name name) {
         return this.scala$reflect$internal$Mirrors$RootsBase$$$outer().missingHook(owner, name);
      }

      public Symbols.Symbol missingHook(final Symbols.Symbol owner, final Names.Name name) {
         SymbolTable var10000 = this.scala$reflect$internal$Mirrors$RootsBase$$$outer();
         Function0 var10001 = () -> (new StringBuilder(15)).append("missingHook(").append(owner).append(", ").append(name).append(")").toString();
         Symbols.Symbol var10002 = this.mirrorMissingHook(owner, name);
         if (var10002 == null) {
            throw null;
         } else {
            Symbols.Symbol orElse_this = var10002;
            var10002 = orElse_this != orElse_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol() ? orElse_this : this.universeMissingHook(owner, name);
            orElse_this = null;
            Symbols.Symbol logResult_result = var10002;
            Function0 logResult_msg = var10001;
            if (var10000 == null) {
               throw null;
            } else {
               var10000.log(SymbolTable::$anonfun$logResult$1);
               return logResult_result;
            }
         }
      }

      private Symbols.ClassSymbol ensureClassSymbol(final String fullname, final Symbols.Symbol sym) {
         if (sym instanceof Symbols.ClassSymbol) {
            return (Symbols.ClassSymbol)sym;
         } else {
            throw MissingRequirementError$.MODULE$.notFound((new StringBuilder(6)).append("class ").append(fullname).toString());
         }
      }

      /** @deprecated */
      public Symbols.ClassSymbol getClassByName(final Names.Name fullname) {
         Symbols.Symbol var31;
         String var10001;
         label48: {
            Symbols.Symbol getModuleOrClass_result;
            label59: {
               var10001 = fullname.toString();
               String var10002 = fullname.toString();
               int getModuleOrClass_len = fullname.len();
               String getModuleOrClass_path = var10002;
               int getModuleOrClass_point = getModuleOrClass_path.lastIndexOf(46, getModuleOrClass_len - 1);
               Symbols.Symbol getModuleOrClass_owner = (Symbols.Symbol)(getModuleOrClass_point > 0 ? this.getModuleOrClass(getModuleOrClass_path, getModuleOrClass_point, (x$2) -> this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTermName(x$2)) : this.RootClass());
               String var13 = getModuleOrClass_path.substring(getModuleOrClass_point + 1, getModuleOrClass_len);
               Names.Name getModuleOrClass_name = $anonfun$getClassByName$1(this, var13);
               Symbols.Symbol getModuleOrClass_sym = getModuleOrClass_owner.info().member(getModuleOrClass_name);
               getModuleOrClass_result = getModuleOrClass_name.isTermName() ? getModuleOrClass_sym.suchThat((x$3) -> BoxesRunTime.boxToBoolean($anonfun$getModuleOrClass$3(x$3))) : getModuleOrClass_sym;
               Symbols.NoSymbol var9 = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
               if (getModuleOrClass_result == null) {
                  if (var9 != null) {
                     break label59;
                  }
               } else if (!getModuleOrClass_result.equals(var9)) {
                  break label59;
               }

               MutableSettings.SettingsOps$ var26 = MutableSettings.SettingsOps$.MODULE$;
               MutableSettings$ var27 = MutableSettings$.MODULE$;
               MutableSettings getModuleOrClass_SettingsOps_settings = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().settings();
               MutableSettings var28 = getModuleOrClass_SettingsOps_settings;
               getModuleOrClass_SettingsOps_settings = null;
               MutableSettings getModuleOrClass_isDebug$extension_$this = var28;
               boolean var29 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(getModuleOrClass_isDebug$extension_$this.debug().value());
               getModuleOrClass_isDebug$extension_$this = null;
               if (var29) {
                  this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info());
                  this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info().members());
               }

               Symbols.Symbol var30 = this.missingHook(getModuleOrClass_owner, getModuleOrClass_name);
               if (var30 == null) {
                  throw null;
               }

               Symbols.Symbol getModuleOrClass_orElse_this = var30;
               if (getModuleOrClass_orElse_this == getModuleOrClass_orElse_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                  throw $anonfun$getModuleOrClass$6(this, getModuleOrClass_name, getModuleOrClass_path);
               }

               var31 = getModuleOrClass_orElse_this;
               getModuleOrClass_orElse_this = null;
               break label48;
            }

            var31 = getModuleOrClass_result;
         }

         Object var14 = null;
         Object var15 = null;
         Object var16 = null;
         Object var17 = null;
         Object var18 = null;
         Object var19 = null;
         Object var21 = null;
         Object var23 = null;
         Object var25 = null;
         return this.ensureClassSymbol(var10001, var31);
      }

      public Symbols.ClassSymbol getClassByName(final String fullname) {
         return this.getRequiredClass(fullname);
      }

      public Symbols.ClassSymbol getRequiredClass(final String fullname, final Function1 toName) {
         Symbols.Symbol var28;
         label48: {
            Symbols.Symbol getModuleOrClass_result;
            label59: {
               int getModuleOrClass_len = fullname.length();
               int getModuleOrClass_point = fullname.lastIndexOf(46, getModuleOrClass_len - 1);
               Symbols.Symbol getModuleOrClass_owner = (Symbols.Symbol)(getModuleOrClass_point > 0 ? this.getModuleOrClass(fullname, getModuleOrClass_point, (x$2) -> this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTermName(x$2)) : this.RootClass());
               Names.Name getModuleOrClass_name = (Names.Name)toName.apply(fullname.substring(getModuleOrClass_point + 1, getModuleOrClass_len));
               Symbols.Symbol getModuleOrClass_sym = getModuleOrClass_owner.info().member(getModuleOrClass_name);
               getModuleOrClass_result = getModuleOrClass_name.isTermName() ? getModuleOrClass_sym.suchThat((x$3) -> BoxesRunTime.boxToBoolean($anonfun$getModuleOrClass$3(x$3))) : getModuleOrClass_sym;
               Symbols.NoSymbol var9 = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
               if (getModuleOrClass_result == null) {
                  if (var9 != null) {
                     break label59;
                  }
               } else if (!getModuleOrClass_result.equals(var9)) {
                  break label59;
               }

               MutableSettings.SettingsOps$ var10002 = MutableSettings.SettingsOps$.MODULE$;
               MutableSettings$ var24 = MutableSettings$.MODULE$;
               MutableSettings getModuleOrClass_SettingsOps_settings = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().settings();
               MutableSettings var25 = getModuleOrClass_SettingsOps_settings;
               getModuleOrClass_SettingsOps_settings = null;
               MutableSettings getModuleOrClass_isDebug$extension_$this = var25;
               boolean var26 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(getModuleOrClass_isDebug$extension_$this.debug().value());
               getModuleOrClass_isDebug$extension_$this = null;
               if (var26) {
                  this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info());
                  this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info().members());
               }

               Symbols.Symbol var27 = this.missingHook(getModuleOrClass_owner, getModuleOrClass_name);
               if (var27 == null) {
                  throw null;
               }

               Symbols.Symbol getModuleOrClass_orElse_this = var27;
               if (getModuleOrClass_orElse_this == getModuleOrClass_orElse_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                  throw $anonfun$getModuleOrClass$6(this, getModuleOrClass_name, fullname);
               }

               var28 = getModuleOrClass_orElse_this;
               getModuleOrClass_orElse_this = null;
               break label48;
            }

            var28 = getModuleOrClass_result;
         }

         Object var13 = null;
         Object var14 = null;
         Object var15 = null;
         Object var16 = null;
         Object var17 = null;
         Object var19 = null;
         Object var21 = null;
         Object var23 = null;
         return this.ensureClassSymbol(fullname, var28);
      }

      public Symbols.ClassSymbol getRequiredClass(final String fullname) {
         Symbols.Symbol var28;
         label48: {
            Symbols.Symbol getModuleOrClass_result;
            label59: {
               int getModuleOrClass_len = fullname.length();
               int getModuleOrClass_point = fullname.lastIndexOf(46, getModuleOrClass_len - 1);
               Symbols.Symbol getModuleOrClass_owner = (Symbols.Symbol)(getModuleOrClass_point > 0 ? this.getModuleOrClass(fullname, getModuleOrClass_point, (x$2) -> this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTermName(x$2)) : this.RootClass());
               String var12 = fullname.substring(getModuleOrClass_point + 1, getModuleOrClass_len);
               Names.Name getModuleOrClass_name = $anonfun$getRequiredClass$1(this, var12);
               Symbols.Symbol getModuleOrClass_sym = getModuleOrClass_owner.info().member(getModuleOrClass_name);
               getModuleOrClass_result = getModuleOrClass_name.isTermName() ? getModuleOrClass_sym.suchThat((x$3) -> BoxesRunTime.boxToBoolean($anonfun$getModuleOrClass$3(x$3))) : getModuleOrClass_sym;
               Symbols.NoSymbol var8 = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
               if (getModuleOrClass_result == null) {
                  if (var8 != null) {
                     break label59;
                  }
               } else if (!getModuleOrClass_result.equals(var8)) {
                  break label59;
               }

               MutableSettings.SettingsOps$ var10002 = MutableSettings.SettingsOps$.MODULE$;
               MutableSettings$ var24 = MutableSettings$.MODULE$;
               MutableSettings getModuleOrClass_SettingsOps_settings = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().settings();
               MutableSettings var25 = getModuleOrClass_SettingsOps_settings;
               getModuleOrClass_SettingsOps_settings = null;
               MutableSettings getModuleOrClass_isDebug$extension_$this = var25;
               boolean var26 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(getModuleOrClass_isDebug$extension_$this.debug().value());
               getModuleOrClass_isDebug$extension_$this = null;
               if (var26) {
                  this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info());
                  this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info().members());
               }

               Symbols.Symbol var27 = this.missingHook(getModuleOrClass_owner, getModuleOrClass_name);
               if (var27 == null) {
                  throw null;
               }

               Symbols.Symbol getModuleOrClass_orElse_this = var27;
               if (getModuleOrClass_orElse_this == getModuleOrClass_orElse_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                  throw $anonfun$getModuleOrClass$6(this, getModuleOrClass_name, fullname);
               }

               var28 = getModuleOrClass_orElse_this;
               getModuleOrClass_orElse_this = null;
               break label48;
            }

            var28 = getModuleOrClass_result;
         }

         Object var13 = null;
         Object var14 = null;
         Object var15 = null;
         Object var16 = null;
         Object var17 = null;
         Object var19 = null;
         Object var21 = null;
         Object var23 = null;
         return this.ensureClassSymbol(fullname, var28);
      }

      public Symbols.ClassSymbol requiredClass(final ClassTag evidence$2) {
         return this.getRequiredClass(this.erasureName(evidence$2), (x$6) -> this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTypeName(x$6));
      }

      public Symbols.Symbol getClassIfDefined(final String fullname) {
         return this.getClassIfDefined(fullname, (x$7) -> this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTypeName(x$7));
      }

      /** @deprecated */
      public Symbols.Symbol getClassIfDefined(final Names.Name fullname) {
         try {
            return $anonfun$getClassIfDefined$2(this, fullname);
         } catch (MissingRequirementError var2) {
            return this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
         }
      }

      public Symbols.Symbol getClassIfDefined(final String fullname, final Function1 toName) {
         try {
            return this.getRequiredClass(fullname, toName);
         } catch (MissingRequirementError var3) {
            return this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
         }
      }

      public Symbols.ClassSymbol staticClass(final String fullname) {
         try {
            Symbols.Symbol var10002;
            if (!.MODULE$.contains$extension(fullname, '.')) {
               var10002 = this.EmptyPackageClass().info().decl((Names.Name)$anonfun$staticClass$1(this, fullname));
            } else {
               int staticModuleOrClass_getModuleOrClass_getModuleOrClass_len = fullname.length();
               int staticModuleOrClass_getModuleOrClass_getModuleOrClass_point = fullname.lastIndexOf(46, staticModuleOrClass_getModuleOrClass_getModuleOrClass_len - 1);
               if (staticModuleOrClass_getModuleOrClass_getModuleOrClass_point > 0) {
                  label104: {
                     Symbols.Symbol getModuleOrClass_result;
                     label132: {
                        int getModuleOrClass_point = fullname.lastIndexOf(46, staticModuleOrClass_getModuleOrClass_getModuleOrClass_point - 1);
                        Symbols.Symbol getModuleOrClass_owner = (Symbols.Symbol)(getModuleOrClass_point > 0 ? this.getModuleOrClass(fullname, getModuleOrClass_point, (x$2) -> this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTermName(x$2)) : this.RootClass());
                        String var23 = fullname.substring(getModuleOrClass_point + 1, staticModuleOrClass_getModuleOrClass_getModuleOrClass_point);
                        Names.Name getModuleOrClass_name = $anonfun$getModuleOrClass$2(this, var23);
                        Symbols.Symbol getModuleOrClass_sym = getModuleOrClass_owner.info().member(getModuleOrClass_name);
                        getModuleOrClass_result = getModuleOrClass_name.isTermName() ? getModuleOrClass_sym.suchThat((x$3) -> BoxesRunTime.boxToBoolean($anonfun$getModuleOrClass$3(x$3))) : getModuleOrClass_sym;
                        Symbols.NoSymbol var18 = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
                        if (getModuleOrClass_result == null) {
                           if (var18 != null) {
                              break label132;
                           }
                        } else if (!getModuleOrClass_result.equals(var18)) {
                           break label132;
                        }

                        MutableSettings.SettingsOps$ var56 = MutableSettings.SettingsOps$.MODULE$;
                        MutableSettings$ var57 = MutableSettings$.MODULE$;
                        MutableSettings getModuleOrClass_SettingsOps_settings = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().settings();
                        MutableSettings var58 = getModuleOrClass_SettingsOps_settings;
                        getModuleOrClass_SettingsOps_settings = null;
                        MutableSettings getModuleOrClass_isDebug$extension_$this = var58;
                        boolean var59 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(getModuleOrClass_isDebug$extension_$this.debug().value());
                        getModuleOrClass_isDebug$extension_$this = null;
                        if (var59) {
                           this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info());
                           this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info().members());
                        }

                        Symbols.Symbol var60 = this.missingHook(getModuleOrClass_owner, getModuleOrClass_name);
                        if (var60 == null) {
                           throw null;
                        }

                        Symbols.Symbol getModuleOrClass_orElse_this = var60;
                        if (getModuleOrClass_orElse_this == getModuleOrClass_orElse_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                           throw $anonfun$getModuleOrClass$6(this, getModuleOrClass_name, fullname);
                        }

                        var10002 = getModuleOrClass_orElse_this;
                        getModuleOrClass_orElse_this = null;
                        break label104;
                     }

                     var10002 = getModuleOrClass_result;
                  }

                  Object var44 = null;
                  Object var45 = null;
                  Object var46 = null;
                  Object var47 = null;
                  Object var48 = null;
                  Object var50 = null;
                  Object var52 = null;
                  Object var54 = null;
               } else {
                  var10002 = this.RootClass();
               }

               label89: {
                  Symbols.Symbol staticModuleOrClass_getModuleOrClass_getModuleOrClass_result;
                  label133: {
                     Symbols.Symbol staticModuleOrClass_getModuleOrClass_getModuleOrClass_owner = var10002;
                     String var22 = fullname.substring(staticModuleOrClass_getModuleOrClass_getModuleOrClass_point + 1, staticModuleOrClass_getModuleOrClass_getModuleOrClass_len);
                     Names.Name staticModuleOrClass_getModuleOrClass_getModuleOrClass_name = $anonfun$staticClass$1(this, var22);
                     Symbols.Symbol staticModuleOrClass_getModuleOrClass_getModuleOrClass_sym = staticModuleOrClass_getModuleOrClass_getModuleOrClass_owner.info().member(staticModuleOrClass_getModuleOrClass_getModuleOrClass_name);
                     staticModuleOrClass_getModuleOrClass_getModuleOrClass_result = staticModuleOrClass_getModuleOrClass_getModuleOrClass_name.isTermName() ? staticModuleOrClass_getModuleOrClass_getModuleOrClass_sym.suchThat((x$3) -> BoxesRunTime.boxToBoolean($anonfun$getModuleOrClass$3(x$3))) : staticModuleOrClass_getModuleOrClass_getModuleOrClass_sym;
                     Symbols.NoSymbol var9 = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
                     if (staticModuleOrClass_getModuleOrClass_getModuleOrClass_result == null) {
                        if (var9 != null) {
                           break label133;
                        }
                     } else if (!staticModuleOrClass_getModuleOrClass_getModuleOrClass_result.equals(var9)) {
                        break label133;
                     }

                     MutableSettings.SettingsOps$ var61 = MutableSettings.SettingsOps$.MODULE$;
                     MutableSettings$ var62 = MutableSettings$.MODULE$;
                     MutableSettings staticModuleOrClass_getModuleOrClass_getModuleOrClass_SettingsOps_settings = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().settings();
                     MutableSettings var63 = staticModuleOrClass_getModuleOrClass_getModuleOrClass_SettingsOps_settings;
                     staticModuleOrClass_getModuleOrClass_getModuleOrClass_SettingsOps_settings = null;
                     MutableSettings staticModuleOrClass_getModuleOrClass_getModuleOrClass_isDebug$extension_$this = var63;
                     boolean var64 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(staticModuleOrClass_getModuleOrClass_getModuleOrClass_isDebug$extension_$this.debug().value());
                     staticModuleOrClass_getModuleOrClass_getModuleOrClass_isDebug$extension_$this = null;
                     if (var64) {
                        this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info());
                        this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info().members());
                     }

                     Symbols.Symbol var65 = this.missingHook(staticModuleOrClass_getModuleOrClass_getModuleOrClass_owner, staticModuleOrClass_getModuleOrClass_getModuleOrClass_name);
                     if (var65 == null) {
                        throw null;
                     }

                     Symbols.Symbol staticModuleOrClass_getModuleOrClass_getModuleOrClass_orElse_this = var65;
                     if (staticModuleOrClass_getModuleOrClass_getModuleOrClass_orElse_this == staticModuleOrClass_getModuleOrClass_getModuleOrClass_orElse_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                        throw $anonfun$getModuleOrClass$6(this, staticModuleOrClass_getModuleOrClass_getModuleOrClass_name, fullname);
                     }

                     var10002 = staticModuleOrClass_getModuleOrClass_getModuleOrClass_orElse_this;
                     staticModuleOrClass_getModuleOrClass_getModuleOrClass_orElse_this = null;
                     break label89;
                  }

                  var10002 = staticModuleOrClass_getModuleOrClass_getModuleOrClass_result;
               }

               Object var25 = null;
               Object var27 = null;
               Object var29 = null;
               Object var31 = null;
               Object var33 = null;
               Object var36 = null;
               Object var39 = null;
               Object var42 = null;
            }

            Object var26 = null;
            Object var28 = null;
            Object var30 = null;
            Object var32 = null;
            Object var34 = null;
            Object var37 = null;
            Object var40 = null;
            Object var43 = null;
            return this.ensureClassSymbol(fullname, var10002);
         } catch (MissingRequirementError var24) {
            throw new ScalaReflectionException(var24.msg());
         }
      }

      private Symbols.ModuleSymbol ensureModuleSymbol(final String fullname, final Symbols.Symbol sym, final boolean allowPackages) {
         if (sym instanceof Symbols.ModuleSymbol) {
            Symbols.ModuleSymbol var4 = (Symbols.ModuleSymbol)sym;
            if (allowPackages || !var4.hasPackageFlag()) {
               return var4;
            }
         }

         throw MissingRequirementError$.MODULE$.notFound((new StringBuilder(7)).append("object ").append(fullname).toString());
      }

      /** @deprecated */
      public Symbols.ModuleSymbol getModuleByName(final Names.Name fullname) {
         return this.getModuleByName(fullname.toString());
      }

      public Symbols.ModuleSymbol getModuleByName(final String fullname) {
         Symbols.Symbol var28;
         label48: {
            Symbols.Symbol getModuleOrClass_result;
            label59: {
               int getModuleOrClass_len = fullname.length();
               int getModuleOrClass_point = fullname.lastIndexOf(46, getModuleOrClass_len - 1);
               Symbols.Symbol getModuleOrClass_owner = (Symbols.Symbol)(getModuleOrClass_point > 0 ? this.getModuleOrClass(fullname, getModuleOrClass_point, (x$2) -> this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTermName(x$2)) : this.RootClass());
               String var12 = fullname.substring(getModuleOrClass_point + 1, getModuleOrClass_len);
               Names.Name getModuleOrClass_name = $anonfun$getModuleByName$1(this, var12);
               Symbols.Symbol getModuleOrClass_sym = getModuleOrClass_owner.info().member(getModuleOrClass_name);
               getModuleOrClass_result = getModuleOrClass_name.isTermName() ? getModuleOrClass_sym.suchThat((x$3) -> BoxesRunTime.boxToBoolean($anonfun$getModuleOrClass$3(x$3))) : getModuleOrClass_sym;
               Symbols.NoSymbol var8 = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
               if (getModuleOrClass_result == null) {
                  if (var8 != null) {
                     break label59;
                  }
               } else if (!getModuleOrClass_result.equals(var8)) {
                  break label59;
               }

               MutableSettings.SettingsOps$ var10002 = MutableSettings.SettingsOps$.MODULE$;
               MutableSettings$ var24 = MutableSettings$.MODULE$;
               MutableSettings getModuleOrClass_SettingsOps_settings = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().settings();
               MutableSettings var25 = getModuleOrClass_SettingsOps_settings;
               getModuleOrClass_SettingsOps_settings = null;
               MutableSettings getModuleOrClass_isDebug$extension_$this = var25;
               boolean var26 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(getModuleOrClass_isDebug$extension_$this.debug().value());
               getModuleOrClass_isDebug$extension_$this = null;
               if (var26) {
                  this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info());
                  this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info().members());
               }

               Symbols.Symbol var27 = this.missingHook(getModuleOrClass_owner, getModuleOrClass_name);
               if (var27 == null) {
                  throw null;
               }

               Symbols.Symbol getModuleOrClass_orElse_this = var27;
               if (getModuleOrClass_orElse_this == getModuleOrClass_orElse_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                  throw $anonfun$getModuleOrClass$6(this, getModuleOrClass_name, fullname);
               }

               var28 = getModuleOrClass_orElse_this;
               getModuleOrClass_orElse_this = null;
               break label48;
            }

            var28 = getModuleOrClass_result;
         }

         Object var13 = null;
         Object var14 = null;
         Object var15 = null;
         Object var16 = null;
         Object var17 = null;
         Object var19 = null;
         Object var21 = null;
         Object var23 = null;
         return this.ensureModuleSymbol(fullname, var28, true);
      }

      public Symbols.ModuleSymbol getRequiredModule(final String fullname) {
         return this.getModuleByName(fullname);
      }

      public Symbols.ModuleSymbol requiredModule(final ClassTag evidence$3) {
         return this.getRequiredModule(.MODULE$.stripSuffix$extension(this.erasureName(evidence$3), "$"));
      }

      public Symbols.Symbol getModuleIfDefined(final String fullname) {
         try {
            return this.getModuleByName(fullname);
         } catch (MissingRequirementError var2) {
            return this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
         }
      }

      /** @deprecated */
      public Symbols.Symbol getModuleIfDefined(final Names.Name fullname) {
         return this.getModuleIfDefined(fullname.toString());
      }

      public Symbols.ModuleSymbol staticModule(final String fullname) {
         try {
            Symbols.Symbol var10002;
            if (!.MODULE$.contains$extension(fullname, '.')) {
               var10002 = this.EmptyPackageClass().info().decl((Names.Name)$anonfun$staticModule$1(this, fullname));
            } else {
               int staticModuleOrClass_getModuleOrClass_getModuleOrClass_len = fullname.length();
               int staticModuleOrClass_getModuleOrClass_getModuleOrClass_point = fullname.lastIndexOf(46, staticModuleOrClass_getModuleOrClass_getModuleOrClass_len - 1);
               if (staticModuleOrClass_getModuleOrClass_getModuleOrClass_point > 0) {
                  label104: {
                     Symbols.Symbol getModuleOrClass_result;
                     label132: {
                        int getModuleOrClass_point = fullname.lastIndexOf(46, staticModuleOrClass_getModuleOrClass_getModuleOrClass_point - 1);
                        Symbols.Symbol getModuleOrClass_owner = (Symbols.Symbol)(getModuleOrClass_point > 0 ? this.getModuleOrClass(fullname, getModuleOrClass_point, (x$2) -> this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTermName(x$2)) : this.RootClass());
                        String var23 = fullname.substring(getModuleOrClass_point + 1, staticModuleOrClass_getModuleOrClass_getModuleOrClass_point);
                        Names.Name getModuleOrClass_name = $anonfun$getModuleOrClass$2(this, var23);
                        Symbols.Symbol getModuleOrClass_sym = getModuleOrClass_owner.info().member(getModuleOrClass_name);
                        getModuleOrClass_result = getModuleOrClass_name.isTermName() ? getModuleOrClass_sym.suchThat((x$3) -> BoxesRunTime.boxToBoolean($anonfun$getModuleOrClass$3(x$3))) : getModuleOrClass_sym;
                        Symbols.NoSymbol var18 = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
                        if (getModuleOrClass_result == null) {
                           if (var18 != null) {
                              break label132;
                           }
                        } else if (!getModuleOrClass_result.equals(var18)) {
                           break label132;
                        }

                        MutableSettings.SettingsOps$ var56 = MutableSettings.SettingsOps$.MODULE$;
                        MutableSettings$ var57 = MutableSettings$.MODULE$;
                        MutableSettings getModuleOrClass_SettingsOps_settings = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().settings();
                        MutableSettings var58 = getModuleOrClass_SettingsOps_settings;
                        getModuleOrClass_SettingsOps_settings = null;
                        MutableSettings getModuleOrClass_isDebug$extension_$this = var58;
                        boolean var59 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(getModuleOrClass_isDebug$extension_$this.debug().value());
                        getModuleOrClass_isDebug$extension_$this = null;
                        if (var59) {
                           this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info());
                           this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info().members());
                        }

                        Symbols.Symbol var60 = this.missingHook(getModuleOrClass_owner, getModuleOrClass_name);
                        if (var60 == null) {
                           throw null;
                        }

                        Symbols.Symbol getModuleOrClass_orElse_this = var60;
                        if (getModuleOrClass_orElse_this == getModuleOrClass_orElse_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                           throw $anonfun$getModuleOrClass$6(this, getModuleOrClass_name, fullname);
                        }

                        var10002 = getModuleOrClass_orElse_this;
                        getModuleOrClass_orElse_this = null;
                        break label104;
                     }

                     var10002 = getModuleOrClass_result;
                  }

                  Object var44 = null;
                  Object var45 = null;
                  Object var46 = null;
                  Object var47 = null;
                  Object var48 = null;
                  Object var50 = null;
                  Object var52 = null;
                  Object var54 = null;
               } else {
                  var10002 = this.RootClass();
               }

               label89: {
                  Symbols.Symbol staticModuleOrClass_getModuleOrClass_getModuleOrClass_result;
                  label133: {
                     Symbols.Symbol staticModuleOrClass_getModuleOrClass_getModuleOrClass_owner = var10002;
                     String var22 = fullname.substring(staticModuleOrClass_getModuleOrClass_getModuleOrClass_point + 1, staticModuleOrClass_getModuleOrClass_getModuleOrClass_len);
                     Names.Name staticModuleOrClass_getModuleOrClass_getModuleOrClass_name = $anonfun$staticModule$1(this, var22);
                     Symbols.Symbol staticModuleOrClass_getModuleOrClass_getModuleOrClass_sym = staticModuleOrClass_getModuleOrClass_getModuleOrClass_owner.info().member(staticModuleOrClass_getModuleOrClass_getModuleOrClass_name);
                     staticModuleOrClass_getModuleOrClass_getModuleOrClass_result = staticModuleOrClass_getModuleOrClass_getModuleOrClass_name.isTermName() ? staticModuleOrClass_getModuleOrClass_getModuleOrClass_sym.suchThat((x$3) -> BoxesRunTime.boxToBoolean($anonfun$getModuleOrClass$3(x$3))) : staticModuleOrClass_getModuleOrClass_getModuleOrClass_sym;
                     Symbols.NoSymbol var9 = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
                     if (staticModuleOrClass_getModuleOrClass_getModuleOrClass_result == null) {
                        if (var9 != null) {
                           break label133;
                        }
                     } else if (!staticModuleOrClass_getModuleOrClass_getModuleOrClass_result.equals(var9)) {
                        break label133;
                     }

                     MutableSettings.SettingsOps$ var61 = MutableSettings.SettingsOps$.MODULE$;
                     MutableSettings$ var62 = MutableSettings$.MODULE$;
                     MutableSettings staticModuleOrClass_getModuleOrClass_getModuleOrClass_SettingsOps_settings = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().settings();
                     MutableSettings var63 = staticModuleOrClass_getModuleOrClass_getModuleOrClass_SettingsOps_settings;
                     staticModuleOrClass_getModuleOrClass_getModuleOrClass_SettingsOps_settings = null;
                     MutableSettings staticModuleOrClass_getModuleOrClass_getModuleOrClass_isDebug$extension_$this = var63;
                     boolean var64 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(staticModuleOrClass_getModuleOrClass_getModuleOrClass_isDebug$extension_$this.debug().value());
                     staticModuleOrClass_getModuleOrClass_getModuleOrClass_isDebug$extension_$this = null;
                     if (var64) {
                        this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info());
                        this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info().members());
                     }

                     Symbols.Symbol var65 = this.missingHook(staticModuleOrClass_getModuleOrClass_getModuleOrClass_owner, staticModuleOrClass_getModuleOrClass_getModuleOrClass_name);
                     if (var65 == null) {
                        throw null;
                     }

                     Symbols.Symbol staticModuleOrClass_getModuleOrClass_getModuleOrClass_orElse_this = var65;
                     if (staticModuleOrClass_getModuleOrClass_getModuleOrClass_orElse_this == staticModuleOrClass_getModuleOrClass_getModuleOrClass_orElse_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                        throw $anonfun$getModuleOrClass$6(this, staticModuleOrClass_getModuleOrClass_getModuleOrClass_name, fullname);
                     }

                     var10002 = staticModuleOrClass_getModuleOrClass_getModuleOrClass_orElse_this;
                     staticModuleOrClass_getModuleOrClass_getModuleOrClass_orElse_this = null;
                     break label89;
                  }

                  var10002 = staticModuleOrClass_getModuleOrClass_getModuleOrClass_result;
               }

               Object var25 = null;
               Object var27 = null;
               Object var29 = null;
               Object var31 = null;
               Object var33 = null;
               Object var36 = null;
               Object var39 = null;
               Object var42 = null;
            }

            Object var26 = null;
            Object var28 = null;
            Object var30 = null;
            Object var32 = null;
            Object var34 = null;
            Object var37 = null;
            Object var40 = null;
            Object var43 = null;
            return this.ensureModuleSymbol(fullname, var10002, false);
         } catch (MissingRequirementError var24) {
            throw new ScalaReflectionException(var24.msg());
         }
      }

      private Symbols.ModuleSymbol ensurePackageSymbol(final String fullname, final Symbols.Symbol sym, final boolean allowModules) {
         if (sym instanceof Symbols.ModuleSymbol) {
            Symbols.ModuleSymbol var4 = (Symbols.ModuleSymbol)sym;
            if (allowModules || var4.hasPackageFlag()) {
               return var4;
            }
         }

         throw MissingRequirementError$.MODULE$.notFound((new StringBuilder(8)).append("package ").append(fullname).toString());
      }

      /** @deprecated */
      public Symbols.ModuleSymbol getPackage(final Names.TermName fullname) {
         return this.getPackage(fullname.toString());
      }

      public Symbols.ModuleSymbol getPackage(final String fullname) {
         int getModuleOrClass_getModuleOrClass_len = fullname.length();
         int getModuleOrClass_getModuleOrClass_point = fullname.lastIndexOf(46, getModuleOrClass_getModuleOrClass_len - 1);
         Object var49;
         if (getModuleOrClass_getModuleOrClass_point > 0) {
            label92: {
               Symbols.Symbol getModuleOrClass_result;
               label111: {
                  int getModuleOrClass_point = fullname.lastIndexOf(46, getModuleOrClass_getModuleOrClass_point - 1);
                  Symbols.Symbol getModuleOrClass_owner = (Symbols.Symbol)(getModuleOrClass_point > 0 ? this.getModuleOrClass(fullname, getModuleOrClass_point, (x$2) -> this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTermName(x$2)) : this.RootClass());
                  String var22 = fullname.substring(getModuleOrClass_point + 1, getModuleOrClass_getModuleOrClass_point);
                  Names.Name getModuleOrClass_name = $anonfun$getModuleOrClass$2(this, var22);
                  Symbols.Symbol getModuleOrClass_sym = getModuleOrClass_owner.info().member(getModuleOrClass_name);
                  getModuleOrClass_result = getModuleOrClass_name.isTermName() ? getModuleOrClass_sym.suchThat((x$3) -> BoxesRunTime.boxToBoolean($anonfun$getModuleOrClass$3(x$3))) : getModuleOrClass_sym;
                  Symbols.NoSymbol var17 = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
                  if (getModuleOrClass_result == null) {
                     if (var17 != null) {
                        break label111;
                     }
                  } else if (!getModuleOrClass_result.equals(var17)) {
                     break label111;
                  }

                  MutableSettings.SettingsOps$ var10002 = MutableSettings.SettingsOps$.MODULE$;
                  MutableSettings$ var45 = MutableSettings$.MODULE$;
                  MutableSettings getModuleOrClass_SettingsOps_settings = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().settings();
                  MutableSettings var46 = getModuleOrClass_SettingsOps_settings;
                  getModuleOrClass_SettingsOps_settings = null;
                  MutableSettings getModuleOrClass_isDebug$extension_$this = var46;
                  boolean var47 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(getModuleOrClass_isDebug$extension_$this.debug().value());
                  getModuleOrClass_isDebug$extension_$this = null;
                  if (var47) {
                     this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info());
                     this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info().members());
                  }

                  Symbols.Symbol var48 = this.missingHook(getModuleOrClass_owner, getModuleOrClass_name);
                  if (var48 == null) {
                     throw null;
                  }

                  Symbols.Symbol getModuleOrClass_orElse_this = var48;
                  if (getModuleOrClass_orElse_this == getModuleOrClass_orElse_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                     throw $anonfun$getModuleOrClass$6(this, getModuleOrClass_name, fullname);
                  }

                  var49 = getModuleOrClass_orElse_this;
                  getModuleOrClass_orElse_this = null;
                  break label92;
               }

               var49 = getModuleOrClass_result;
            }

            Object var34 = null;
            Object var35 = null;
            Object var36 = null;
            Object var37 = null;
            Object var38 = null;
            Object var40 = null;
            Object var42 = null;
            Object var44 = null;
         } else {
            var49 = this.RootClass();
         }

         label78: {
            Symbols.Symbol getModuleOrClass_getModuleOrClass_result;
            label112: {
               Symbols.Symbol getModuleOrClass_getModuleOrClass_owner = (Symbols.Symbol)var49;
               String var21 = fullname.substring(getModuleOrClass_getModuleOrClass_point + 1, getModuleOrClass_getModuleOrClass_len);
               Names.Name getModuleOrClass_getModuleOrClass_name = $anonfun$getPackage$1(this, var21);
               Symbols.Symbol getModuleOrClass_getModuleOrClass_sym = getModuleOrClass_getModuleOrClass_owner.info().member(getModuleOrClass_getModuleOrClass_name);
               getModuleOrClass_getModuleOrClass_result = getModuleOrClass_getModuleOrClass_name.isTermName() ? getModuleOrClass_getModuleOrClass_sym.suchThat((x$3) -> BoxesRunTime.boxToBoolean($anonfun$getModuleOrClass$3(x$3))) : getModuleOrClass_getModuleOrClass_sym;
               Symbols.NoSymbol var8 = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
               if (getModuleOrClass_getModuleOrClass_result == null) {
                  if (var8 != null) {
                     break label112;
                  }
               } else if (!getModuleOrClass_getModuleOrClass_result.equals(var8)) {
                  break label112;
               }

               MutableSettings.SettingsOps$ var50 = MutableSettings.SettingsOps$.MODULE$;
               MutableSettings$ var51 = MutableSettings$.MODULE$;
               MutableSettings getModuleOrClass_getModuleOrClass_SettingsOps_settings = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().settings();
               MutableSettings var52 = getModuleOrClass_getModuleOrClass_SettingsOps_settings;
               getModuleOrClass_getModuleOrClass_SettingsOps_settings = null;
               MutableSettings getModuleOrClass_getModuleOrClass_isDebug$extension_$this = var52;
               boolean var53 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(getModuleOrClass_getModuleOrClass_isDebug$extension_$this.debug().value());
               getModuleOrClass_getModuleOrClass_isDebug$extension_$this = null;
               if (var53) {
                  this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info());
                  this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info().members());
               }

               Symbols.Symbol var54 = this.missingHook(getModuleOrClass_getModuleOrClass_owner, getModuleOrClass_getModuleOrClass_name);
               if (var54 == null) {
                  throw null;
               }

               Symbols.Symbol getModuleOrClass_getModuleOrClass_orElse_this = var54;
               if (getModuleOrClass_getModuleOrClass_orElse_this == getModuleOrClass_getModuleOrClass_orElse_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                  throw $anonfun$getModuleOrClass$6(this, getModuleOrClass_getModuleOrClass_name, fullname);
               }

               var55 = getModuleOrClass_getModuleOrClass_orElse_this;
               getModuleOrClass_getModuleOrClass_orElse_this = null;
               break label78;
            }

            var55 = getModuleOrClass_getModuleOrClass_result;
         }

         Object var23 = null;
         Object var24 = null;
         Object var25 = null;
         Object var26 = null;
         Object var27 = null;
         Object var29 = null;
         Object var31 = null;
         Object var33 = null;
         return this.ensurePackageSymbol(fullname, var55, true);
      }

      /** @deprecated */
      public Symbols.Symbol getPackageIfDefined(final Names.TermName fullname) {
         return this.getPackageIfDefined(fullname.toString());
      }

      public Symbols.Symbol getPackageIfDefined(final String fullname) {
         try {
            return this.getPackage(fullname);
         } catch (MissingRequirementError var2) {
            return this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
         }
      }

      /** @deprecated */
      public Symbols.ModuleSymbol getRequiredPackage(final String fullname) {
         return this.getPackage(fullname);
      }

      /** @deprecated */
      public Symbols.ModuleSymbol getPackageObject(final Names.TermName fullname) {
         return this.getPackageObject(fullname.toString());
      }

      public Symbols.ModuleSymbol getPackageObject(final String fullname) {
         Symbols.Symbol var2 = this.getPackage(fullname).packageObject();
         if (var2 instanceof Symbols.ModuleSymbol) {
            return (Symbols.ModuleSymbol)var2;
         } else {
            throw MissingRequirementError$.MODULE$.notFound((new StringBuilder(15)).append("package object ").append(fullname).toString());
         }
      }

      /** @deprecated */
      public Symbols.Symbol getPackageObjectIfDefined(final Names.TermName fullname) {
         return this.getPackageObjectIfDefined(fullname.toString());
      }

      public Symbols.Symbol getPackageObjectIfDefined(final String fullname) {
         try {
            return this.getPackageObject(fullname);
         } catch (MissingRequirementError var2) {
            return this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
         }
      }

      public Symbols.ModuleSymbol staticPackage(final String fullname) {
         try {
            Symbols.Symbol var30;
            String var10001;
            label52: {
               Symbols.Symbol getModuleOrClass_result;
               label64: {
                  var10001 = fullname.toString();
                  int getModuleOrClass_len = fullname.length();
                  int getModuleOrClass_point = fullname.lastIndexOf(46, getModuleOrClass_len - 1);
                  Symbols.Symbol getModuleOrClass_owner = (Symbols.Symbol)(getModuleOrClass_point > 0 ? this.getModuleOrClass(fullname, getModuleOrClass_point, (x$2) -> this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTermName(x$2)) : this.RootClass());
                  String var13 = fullname.substring(getModuleOrClass_point + 1, getModuleOrClass_len);
                  Names.Name getModuleOrClass_name = $anonfun$staticPackage$1(this, var13);
                  Symbols.Symbol getModuleOrClass_sym = getModuleOrClass_owner.info().member(getModuleOrClass_name);
                  getModuleOrClass_result = getModuleOrClass_name.isTermName() ? getModuleOrClass_sym.suchThat((x$3) -> BoxesRunTime.boxToBoolean($anonfun$getModuleOrClass$3(x$3))) : getModuleOrClass_sym;
                  Symbols.NoSymbol var9 = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
                  if (getModuleOrClass_result == null) {
                     if (var9 != null) {
                        break label64;
                     }
                  } else if (!getModuleOrClass_result.equals(var9)) {
                     break label64;
                  }

                  MutableSettings.SettingsOps$ var10002 = MutableSettings.SettingsOps$.MODULE$;
                  MutableSettings$ var26 = MutableSettings$.MODULE$;
                  MutableSettings getModuleOrClass_SettingsOps_settings = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().settings();
                  MutableSettings var27 = getModuleOrClass_SettingsOps_settings;
                  getModuleOrClass_SettingsOps_settings = null;
                  MutableSettings getModuleOrClass_isDebug$extension_$this = var27;
                  boolean var28 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(getModuleOrClass_isDebug$extension_$this.debug().value());
                  getModuleOrClass_isDebug$extension_$this = null;
                  if (var28) {
                     this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info());
                     this.scala$reflect$internal$Mirrors$RootsBase$$$outer().log(() -> getModuleOrClass_sym.info().members());
                  }

                  Symbols.Symbol var29 = this.missingHook(getModuleOrClass_owner, getModuleOrClass_name);
                  if (var29 == null) {
                     throw null;
                  }

                  Symbols.Symbol getModuleOrClass_orElse_this = var29;
                  if (getModuleOrClass_orElse_this == getModuleOrClass_orElse_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                     throw $anonfun$getModuleOrClass$6(this, getModuleOrClass_name, fullname);
                  }

                  var30 = getModuleOrClass_orElse_this;
                  getModuleOrClass_orElse_this = null;
                  break label52;
               }

               var30 = getModuleOrClass_result;
            }

            Object var15 = null;
            Object var16 = null;
            Object var17 = null;
            Object var18 = null;
            Object var19 = null;
            Object var21 = null;
            Object var23 = null;
            Object var25 = null;
            return this.ensurePackageSymbol(var10001, var30, false);
         } catch (MissingRequirementError var14) {
            throw new ScalaReflectionException(var14.msg());
         }
      }

      public String erasureName(final ClassTag evidence$4) {
         package var10000 = scala.reflect.package..MODULE$;
         return erasureString$1(evidence$4.runtimeClass());
      }

      public final Symbols.Symbol wrapMissing(final Function0 body) {
         try {
            return (Symbols.Symbol)body.apply();
         } catch (MissingRequirementError var2) {
            return this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
         }
      }

      public void init() {
         if (!this.initialized) {
            this.EmptyPackageClass().setInfo(this.rootLoader());
            Symbols.ModuleSymbol var10000 = this.EmptyPackage();
            Symbols.ClassSymbol var10001 = this.EmptyPackageClass();
            if (var10001 == null) {
               throw null;
            } else {
               label36: {
                  var10000.setInfo(((Symbols.Symbol)var10001).tpe_$times());
                  this.scala$reflect$internal$Mirrors$RootsBase$$$outer().connectModuleToClass(this.EmptyPackage(), this.EmptyPackageClass());
                  this.scala$reflect$internal$Mirrors$RootsBase$$$outer().connectModuleToClass(this.RootPackage(), this.RootClass());
                  this.RootClass().info().decls().enter(this.EmptyPackage());
                  this.RootClass().info().decls().enter(this.RootPackage());
                  Symbols.Symbol var4 = this.rootOwner;
                  Symbols.NoSymbol var1 = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().NoSymbol();
                  if (var4 == null) {
                     if (var1 == null) {
                        break label36;
                     }
                  } else if (var4.equals(var1)) {
                     break label36;
                  }

                  List var5 = this.scala$reflect$internal$Mirrors$RootsBase$$$outer().definitions().syntheticCoreClasses();
                  if (var5 == null) {
                     throw null;
                  }

                  for(List foreach_these = var5; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
                     Symbols.TypeSymbol var3 = (Symbols.TypeSymbol)foreach_these.head();
                     $anonfun$init$1(this, var3);
                  }
               }

               this.initialized = true;
            }
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Mirrors$RootsBase$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final Names.Name $anonfun$getModuleOrClass$1(final Names.Name path$1, final String x$1) {
         return path$1.newName(x$1);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$getModuleOrClass$3(final Symbols.Symbol x$3) {
         return x$3.hasFlag(256L);
      }

      // $FF: synthetic method
      public static final Nothing $anonfun$getModuleOrClass$6(final RootsBase $this, final Names.Name name$1, final String path$2) {
         return MissingRequirementError$.MODULE$.notFound((new StringBuilder(5)).append(name$1.isTermName() ? "object" : "class").append(" ").append(path$2).append(" in ").append($this).toString());
      }

      // $FF: synthetic method
      public static final Symbols.Symbol $anonfun$missingHook$2(final RootsBase $this, final Symbols.Symbol owner$1, final Names.Name name$2) {
         return $this.universeMissingHook(owner$1, name$2);
      }

      // $FF: synthetic method
      public static final Names.TypeName $anonfun$getClassByName$1(final RootsBase $this, final String x$4) {
         return $this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTypeName(x$4);
      }

      // $FF: synthetic method
      public static final Names.TypeName $anonfun$getRequiredClass$1(final RootsBase $this, final String x$5) {
         return $this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTypeName(x$5);
      }

      // $FF: synthetic method
      public static final Symbols.ClassSymbol $anonfun$getClassIfDefined$2(final RootsBase $this, final Names.Name fullname$1) {
         return $this.getClassByName((Names.Name)fullname$1.toTypeName());
      }

      // $FF: synthetic method
      public static final Symbols.ClassSymbol $anonfun$getClassIfDefined$3(final RootsBase $this, final String fullname$2, final Function1 toName$1) {
         return $this.getRequiredClass(fullname$2, toName$1);
      }

      // $FF: synthetic method
      public static final Names.TypeName $anonfun$staticClass$1(final RootsBase $this, final String x$8) {
         return $this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTypeName(x$8);
      }

      // $FF: synthetic method
      public static final Names.TermName $anonfun$getModuleByName$1(final RootsBase $this, final String x$9) {
         return $this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTermName(x$9);
      }

      // $FF: synthetic method
      public static final Symbols.ModuleSymbol $anonfun$getModuleIfDefined$1(final RootsBase $this, final String fullname$3) {
         return $this.getModuleByName(fullname$3);
      }

      // $FF: synthetic method
      public static final Names.TermName $anonfun$staticModule$1(final RootsBase $this, final String x$10) {
         return $this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTermName(x$10);
      }

      // $FF: synthetic method
      public static final Names.TermName $anonfun$getPackage$1(final RootsBase $this, final String x$11) {
         return $this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTermName(x$11);
      }

      // $FF: synthetic method
      public static final Symbols.ModuleSymbol $anonfun$getPackageIfDefined$1(final RootsBase $this, final String fullname$4) {
         return $this.getPackage(fullname$4);
      }

      // $FF: synthetic method
      public static final Symbols.ModuleSymbol $anonfun$getPackageObjectIfDefined$1(final RootsBase $this, final String fullname$5) {
         return $this.getPackageObject(fullname$5);
      }

      // $FF: synthetic method
      public static final Names.TermName $anonfun$staticPackage$1(final RootsBase $this, final String x$12) {
         return $this.scala$reflect$internal$Mirrors$RootsBase$$$outer().newTermName(x$12);
      }

      private static final String erasureString$1(final Class clazz) {
         return clazz.isArray() ? (new StringBuilder(7)).append("Array[").append(erasureString$1(clazz.getComponentType())).append("]").toString() : clazz.getName();
      }

      // $FF: synthetic method
      public static final String $anonfun$init$2(final Symbols.TypeSymbol theirSym$1, final Symbols.Symbol theirOwner$1) {
         return (new StringBuilder(26)).append("theirSym = ").append(theirSym$1).append(", theirOwner = ").append(theirOwner$1).toString();
      }

      // $FF: synthetic method
      public static final Symbols.TypeSymbol $anonfun$init$1(final RootsBase $this, final Symbols.TypeSymbol theirSym) {
         Symbols.Symbol theirOwner = theirSym.owner();
         SymbolTable var10000 = $this.scala$reflect$internal$Mirrors$RootsBase$$$outer();
         boolean assert_assertion = theirOwner.isPackageClass();
         if (var10000 == null) {
            throw null;
         } else {
            SymbolTable assert_this = var10000;
            if (!assert_assertion) {
               throw assert_this.throwAssertionError($anonfun$init$2(theirSym, theirOwner));
            } else {
               assert_this = null;
               return (Symbols.TypeSymbol)$this.staticPackage(theirOwner.fullName('.')).moduleClass().info().decls().enterIfNew(theirSym);
            }
         }
      }

      public RootsBase(final Symbols.Symbol rootOwner) {
         this.rootOwner = rootOwner;
         if (Mirrors.this == null) {
            throw null;
         } else {
            this.$outer = Mirrors.this;
            super();
            this.initialized = false;
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public abstract class Roots extends RootsBase {
      private RootPackage RootPackage;
      private RootClass RootClass;
      private EmptyPackage EmptyPackage;
      private EmptyPackageClass EmptyPackageClass;
      public final Symbols.Symbol scala$reflect$internal$Mirrors$Roots$$rootOwner;
      private volatile byte bitmap$0;

      private RootPackage RootPackage$lzycompute() {
         synchronized(this){}

         try {
            if ((byte)(this.bitmap$0 & 1) == 0) {
               this.RootPackage = new RootPackage();
               this.bitmap$0 = (byte)(this.bitmap$0 | 1);
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.RootPackage;
      }

      public RootPackage RootPackage() {
         return (byte)(this.bitmap$0 & 1) == 0 ? this.RootPackage$lzycompute() : this.RootPackage;
      }

      private RootClass RootClass$lzycompute() {
         synchronized(this){}

         try {
            if ((byte)(this.bitmap$0 & 2) == 0) {
               this.RootClass = new RootClass();
               this.bitmap$0 = (byte)(this.bitmap$0 | 2);
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.RootClass;
      }

      public RootClass RootClass() {
         return (byte)(this.bitmap$0 & 2) == 0 ? this.RootClass$lzycompute() : this.RootClass;
      }

      private EmptyPackage EmptyPackage$lzycompute() {
         synchronized(this){}

         try {
            if ((byte)(this.bitmap$0 & 4) == 0) {
               this.EmptyPackage = new EmptyPackage();
               this.bitmap$0 = (byte)(this.bitmap$0 | 4);
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.EmptyPackage;
      }

      public EmptyPackage EmptyPackage() {
         return (byte)(this.bitmap$0 & 4) == 0 ? this.EmptyPackage$lzycompute() : this.EmptyPackage;
      }

      private EmptyPackageClass EmptyPackageClass$lzycompute() {
         synchronized(this){}

         try {
            if ((byte)(this.bitmap$0 & 8) == 0) {
               this.EmptyPackageClass = new EmptyPackageClass();
               this.bitmap$0 = (byte)(this.bitmap$0 | 8);
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.EmptyPackageClass;
      }

      public EmptyPackageClass EmptyPackageClass() {
         return (byte)(this.bitmap$0 & 8) == 0 ? this.EmptyPackageClass$lzycompute() : this.EmptyPackageClass;
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Mirrors$Roots$$$outer() {
         return this.$outer;
      }

      public Roots(final Symbols.Symbol rootOwner) {
         super(rootOwner);
         this.scala$reflect$internal$Mirrors$Roots$$rootOwner = rootOwner;
      }

      public interface WellKnownSymbol {
         // $FF: synthetic method
         Roots scala$reflect$internal$Mirrors$Roots$WellKnownSymbol$$$outer();

         static void $init$(final WellKnownSymbol $this) {
            ((Symbols.Symbol)$this).initFlags(9453856L);
         }
      }

      public interface RootSymbol extends WellKnownSymbol, RootSymbol {
         default boolean isRootSymbol() {
            return true;
         }

         default Symbols.Symbol owner() {
            return this.scala$reflect$internal$Mirrors$Roots$RootSymbol$$$outer().scala$reflect$internal$Mirrors$Roots$$rootOwner;
         }

         default Types.Type typeOfThis() {
            Symbols.Symbol var10000 = ((Symbols.Symbol)this).thisSym();
            if (var10000 == null) {
               throw null;
            } else {
               return var10000.tpe_$times();
            }
         }

         default RootsBase mirror() {
            return this.scala$reflect$internal$Mirrors$Roots$RootSymbol$$$outer();
         }

         // $FF: synthetic method
         Roots scala$reflect$internal$Mirrors$Roots$RootSymbol$$$outer();

         static void $init$(final RootSymbol $this) {
         }
      }

      public class RootPackage extends Symbols.ModuleSymbol implements RootSymbol {
         // $FF: synthetic field
         public final Roots $outer;

         public final boolean isRootSymbol() {
            return Mirrors.Roots.RootSymbol.super.isRootSymbol();
         }

         public Symbols.Symbol owner() {
            return Mirrors.Roots.RootSymbol.super.owner();
         }

         public Types.Type typeOfThis() {
            return Mirrors.Roots.RootSymbol.super.typeOfThis();
         }

         public RootsBase mirror() {
            return Mirrors.Roots.RootSymbol.super.mirror();
         }

         public boolean isRootPackage() {
            return true;
         }

         // $FF: synthetic method
         public Roots scala$reflect$internal$Mirrors$Roots$RootPackage$$$outer() {
            return this.$outer;
         }

         // $FF: synthetic method
         public Roots scala$reflect$internal$Mirrors$Roots$RootSymbol$$$outer() {
            return this.scala$reflect$internal$Mirrors$Roots$RootPackage$$$outer();
         }

         // $FF: synthetic method
         public Roots scala$reflect$internal$Mirrors$Roots$WellKnownSymbol$$$outer() {
            return this.scala$reflect$internal$Mirrors$Roots$RootPackage$$$outer();
         }

         public RootPackage() {
            if (Roots.this == null) {
               throw null;
            } else {
               this.$outer = Roots.this;
               super(Roots.this.scala$reflect$internal$Mirrors$Roots$$rootOwner, Roots.this.scala$reflect$internal$Mirrors$Roots$$$outer().NoPosition(), Roots.this.scala$reflect$internal$Mirrors$Roots$$$outer().nme().ROOTPKG());
               ((Symbols.Symbol)this).initFlags(9453856L);
               Types.NullaryMethodType var10001 = new Types.NullaryMethodType;
               SymbolTable var10003 = Roots.this.scala$reflect$internal$Mirrors$Roots$$$outer();
               RootClass var10004 = Roots.this.RootClass();
               if (var10004 == null) {
                  throw null;
               } else {
                  var10001.<init>(((Symbols.Symbol)var10004).tpe_$times());
                  this.setInfo(var10001);
               }
            }
         }
      }

      public class RootClass extends Symbols.PackageClassSymbol implements RootSymbol {
         // $FF: synthetic field
         public final Roots $outer;

         public final boolean isRootSymbol() {
            return Mirrors.Roots.RootSymbol.super.isRootSymbol();
         }

         public Symbols.Symbol owner() {
            return Mirrors.Roots.RootSymbol.super.owner();
         }

         public Types.Type typeOfThis() {
            return Mirrors.Roots.RootSymbol.super.typeOfThis();
         }

         public RootsBase mirror() {
            return Mirrors.Roots.RootSymbol.super.mirror();
         }

         public boolean isRoot() {
            return true;
         }

         public boolean isEffectiveRoot() {
            return true;
         }

         public boolean isNestedClass() {
            return false;
         }

         public RootPackage sourceModule() {
            return this.scala$reflect$internal$Mirrors$Roots$RootClass$$$outer().RootPackage();
         }

         // $FF: synthetic method
         public Roots scala$reflect$internal$Mirrors$Roots$RootClass$$$outer() {
            return this.$outer;
         }

         // $FF: synthetic method
         public Roots scala$reflect$internal$Mirrors$Roots$RootSymbol$$$outer() {
            return this.scala$reflect$internal$Mirrors$Roots$RootClass$$$outer();
         }

         // $FF: synthetic method
         public Roots scala$reflect$internal$Mirrors$Roots$WellKnownSymbol$$$outer() {
            return this.scala$reflect$internal$Mirrors$Roots$RootClass$$$outer();
         }

         public RootClass() {
            if (Roots.this == null) {
               throw null;
            } else {
               this.$outer = Roots.this;
               super(Roots.this.scala$reflect$internal$Mirrors$Roots$$rootOwner, Roots.this.scala$reflect$internal$Mirrors$Roots$$$outer().NoPosition(), (Names.TypeName)Roots.this.scala$reflect$internal$Mirrors$Roots$$$outer().tpnme().ROOT());
               ((Symbols.Symbol)this).initFlags(9453856L);
               this.setInfo(Roots.this.rootLoader());
            }
         }
      }

      public class EmptyPackage extends Symbols.ModuleSymbol implements WellKnownSymbol {
         // $FF: synthetic field
         public final Roots $outer;

         public boolean isEmptyPackage() {
            return true;
         }

         // $FF: synthetic method
         public Roots scala$reflect$internal$Mirrors$Roots$EmptyPackage$$$outer() {
            return this.$outer;
         }

         // $FF: synthetic method
         public Roots scala$reflect$internal$Mirrors$Roots$WellKnownSymbol$$$outer() {
            return this.scala$reflect$internal$Mirrors$Roots$EmptyPackage$$$outer();
         }

         public EmptyPackage() {
            if (Roots.this == null) {
               throw null;
            } else {
               this.$outer = Roots.this;
               super(Roots.this.RootClass(), Roots.this.scala$reflect$internal$Mirrors$Roots$$$outer().NoPosition(), (Names.TermName)Roots.this.scala$reflect$internal$Mirrors$Roots$$$outer().nme().EMPTY_PACKAGE_NAME());
               ((Symbols.Symbol)this).initFlags(9453856L);
            }
         }
      }

      public class EmptyPackageClass extends Symbols.PackageClassSymbol implements WellKnownSymbol {
         // $FF: synthetic field
         public final Roots $outer;

         public boolean isEffectiveRoot() {
            return true;
         }

         public boolean isEmptyPackageClass() {
            return true;
         }

         public EmptyPackage sourceModule() {
            return this.scala$reflect$internal$Mirrors$Roots$EmptyPackageClass$$$outer().EmptyPackage();
         }

         // $FF: synthetic method
         public Roots scala$reflect$internal$Mirrors$Roots$EmptyPackageClass$$$outer() {
            return this.$outer;
         }

         // $FF: synthetic method
         public Roots scala$reflect$internal$Mirrors$Roots$WellKnownSymbol$$$outer() {
            return this.scala$reflect$internal$Mirrors$Roots$EmptyPackageClass$$$outer();
         }

         public EmptyPackageClass() {
            if (Roots.this == null) {
               throw null;
            } else {
               this.$outer = Roots.this;
               super(Roots.this.RootClass(), Roots.this.scala$reflect$internal$Mirrors$Roots$$$outer().NoPosition(), (Names.TypeName)Roots.this.scala$reflect$internal$Mirrors$Roots$$$outer().tpnme().EMPTY_PACKAGE_NAME());
               ((Symbols.Symbol)this).initFlags(9453856L);
            }
         }
      }
   }

   public interface RootSymbol {
      RootsBase mirror();
   }
}

package scala.collection;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Array;
import java.util.NoSuchElementException;
import scala.Array$;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Some;
import scala.Tuple2;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.ArraySeq$;
import scala.collection.mutable.ArrayBuffer$;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.ArrayBuilder$;
import scala.collection.mutable.Builder;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.HashSet$;
import scala.collection.mutable.ImmutableBuilder;
import scala.collection.mutable.Queue;
import scala.collection.mutable.Queue$;
import scala.math.Integral;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.RichInt$;
import scala.runtime.ScalaRunTime$;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019\u0005fACA\u0007\u0003\u001f\u0001\n1!\u0001\u0002\u001a!9\u0011Q\n\u0001\u0005\u0002\u0005=\u0003bBA,\u0001\u0019\u0005\u0011\u0011\f\u0005\b\u0003C\u0002AQIA-\u0011\u001d\ty\b\u0001D\u0001\u0003\u0003Cq!!8\u0001\t\u000b\ty\u000eC\u0004\u0002d\u0002!\t!!:\t\u000f\u00055\b\u0001\"\u0001\u0002p\"9\u0011Q\u001f\u0001\u0005\u0002\u0005]hABA\u0000\u0001\u0001\u0011\t\u0001\u0003\u0006\u0003 %\u0011\t\u0011)A\u0005\u0005CA!Ba\t\n\u0005\u0003\u0005\u000b\u0011\u0002B\u0013\u0011)\u0011Y#\u0003B\u0001B\u0003%!Q\u0005\u0005\b\u0005[IA\u0011\u0001B\u0018\u0011!\u0011Y$\u0003Q!\n\tu\u0002\u0002\u0003B\"\u0013\u0001\u0006KA!\u0010\t\u0011\t\u0015\u0013\u0002)Q\u0005\u00037B\u0001Ba\u0012\nA\u0003&\u00111\f\u0005\t\u0005\u0013J\u0001\u0015)\u0003\u0002\\!A!1J\u0005!B\u0013\u0011i\u0005\u0003\u0005\u0003T%\u0001K\u0011BA-\u0011!\u0011)&\u0003Q\u0005\n\t]\u0003b\u0002B3\u0013\u0011\u0005!q\r\u0005\b\u0005kJA\u0011\u0001B<\u0011\u001d\u0011Y(\u0003C\u0005\u0005{BqAa \n\t\u0013\u0011i\bC\u0004\u0002X%!\t!!\u0017\t\u000f\u0005}\u0014\u0002\"\u0001\u0003\u0002\"9!q\u0012\u0001\u0005\u0002\tE\u0005b\u0002BQ\u0001\u0011\u0005!1\u0015\u0005\b\u0005k\u0003A\u0011\u0001B\\\u0011\u001d\u0011\u0019\r\u0001C\u0001\u0005\u000bD\u0011Ba5\u0001#\u0003%\tA!6\t\u000f\t=\b\u0001\"\u0001\u0003r\"911\u0002\u0001\u0005\u0002\r5\u0001bBB\u0013\u0001\u0011\u00051q\u0005\u0005\n\u0007_\u0001\u0011\u0013!C\u0001\u0005/Dqa!\r\u0001\t\u0003\u0019\u0019\u0004C\u0004\u00042\u0001!\ta!\u0010\t\u000f\r%\u0003\u0001\"\u0002\u0004L!91q\n\u0001\u0005B\u0005e\u0003bBB/\u0001\u0011\u00051q\f\u0005\b\u0007G\u0002A\u0011AB3\u0011%\u0019I\u0007\u0001C\u0001\u0003\u001f\u0019Y\u0007C\u0004\u0004t\u0001!\ta!\u001e\t\u000f\re\u0004\u0001\"\u0001\u0004|!91q\u0012\u0001\u0005\u0002\u0005}\u0007bBBI\u0001\u0011\u000511\u0013\u0005\b\u0007C\u0003A\u0011ABR\u0011\u001d\u0019\t\f\u0001C\u0001\u0007gCqaa1\u0001\t\u0003\u0019)\rC\u0004\u0004X\u0002!\ta!7\t\u000f\r-\b\u0001\"\u0002\u0004n\"91q \u0001\u0005\u0002\u0011\u0005\u0001b\u0002C\u0004\u0001\u0011\u0005A\u0011\u0002\u0005\b\t\u001b\u0001A\u0011\u0001C\b\u0011\u001d!\u0019\u0002\u0001C\u0001\t+Aq\u0001\"\u0007\u0001\t\u0003!Y\u0002C\u0004\u0005 \u0001!\t\u0001\"\t\t\u000f\u0011%\u0002\u0001\"\u0005\u0005,!9A\u0011\u0007\u0001\u0005\u0002\u0011M\u0002b\u0002C#\u0001\u0011\u0005Aq\t\u0005\b\tK\u0002A\u0011\u0001C4\u0011\u001d!i\u0007\u0001C\u0001\t_Bq\u0001b\u001f\u0001\t\u0003!i\bC\u0004\u0005\u0000\u0001!\t\u0001\"!\t\u000f\u0011U\u0005\u0001\"\u0011\u0005\u0018\"9AQ\u0015\u0001\u0005B\u0011\u001d\u0006b\u0002C\\\u0001\u0011\u0005A\u0011X\u0004\t\t\u0003\fy\u0001#\u0001\u0005D\u001aA\u0011QBA\b\u0011\u0003!)\rC\u0004\u0003.\u0019#\t\u0001\"4\t\u001d\u0011=g\t\"A\u0001\u0006\u0003\u0005\t\u0015!\u0003\u0005R\"91Q\u0006$\u0005B\u0011M\u0007b\u0002Cr\r\u0012\u0015AQ\u001d\u0005\b\tc4E\u0011\u0001Cz\u0011\u001d)\tA\u0012C!\u000b\u0007AqA!\u0016G\t\u0003))\u0002C\u0004\u0003\u0000\u0019#\t%\"\n\t\u000f\u0015]b\t\"\u0011\u0006:!91Q\u0006$\u0005\u0002\u00155\u0003bBB\u0017\r\u0012\u0005QQ\u000b\u0005\b\u000b72E\u0011AC/\u0011\u001d)YF\u0012C\u0001\u000bGBq!b\u001bG\t\u0003)i\u0007C\u0004\u0006\u0000\u0019#\t%\"!\t\u000f\u0015}e\t\"\u0001\u0006\"\u001a1Qq\u0016$\u0007\u000bcC!\"b/X\u0005\u0003\u0007I\u0011BC_\u0011))\u0019m\u0016BA\u0002\u0013%QQ\u0019\u0005\u000b\u000b\u0017<&\u0011!Q!\n\u0015}\u0006b\u0002B\u0017/\u0012\u0005QQ\u001a\u0005\n\u000b+<\u0006\u0019!C\u0005\u000b/D\u0011\"b<X\u0001\u0004%IAb\u0001\t\u0011\u0015Ux\u000b)Q\u0005\u000b3D\u0011Bb\u0002X\u0001\u0004%I!b6\t\u0013\u0019%q\u000b1A\u0005\n\u0019-\u0001\u0002\u0003D\b/\u0002\u0006K!\"7\t\u0013\u0019Eq\u000b1A\u0005\n\u0005e\u0003\"\u0003D\n/\u0002\u0007I\u0011\u0002D\u000b\u0011!1Ib\u0016Q!\n\u0005m\u0003bBA,/\u0012\u0005\u0011\u0011\f\u0005\b\u0003\u007f:F\u0011\u0001D\u000e\u0011\u001d\u00199n\u0016C!\r;1\u0001\"b7GA\u00035QQ\u001c\u0005\u000b\u000bCD'\u0011!S\u0001\n\u0015\r\bBCCkQ\n\u0005\r\u0011\"\u0001\u0006l\"QQq\u001e5\u0003\u0002\u0004%\t!\"=\t\u0015\u0015U\bN!A!B\u0013)i\u000fC\u0004\u0003.!$\t!b>\t\u000f\u0015u\b\u000e\"\u0001\u0006\u0000\u001aAaq\u0006$\u0003\u0003'1\t\u0004\u0003\u0006\u0007<=\u0014)\u0019!C\u0001\r{A!B\"\u0011p\u0005\u0003\u0005\u000b\u0011\u0002D \u0011))\u0019f\u001cB\u0001B\u0003%!Q\u0005\u0005\u000b\r\u0007z'\u0011!Q\u0001\n\t\u0015\u0002b\u0002B\u0017_\u0012\u0005aQ\t\u0005\u000f\r\u001fzG\u0011!A\u0003\u0002\u0003\u0005\u000b\u0015\u0002B\u0013\u0011!1\tf\u001cQ!\n\t\u0015\u0002b\u0002D*_\u0012%\u0011\u0011\f\u0005\b\r/zG\u0011BA(\u0011\u001d1If\u001cC!\u0007\u0017Bq!a\u0016p\t\u0003\tI\u0006C\u0004\u0002\u0000=$\tAb\u0017\t\u000f\u0011%r\u000e\"\u0015\u0007^\u00191a1\r$\u0007\rKB!\"\"(~\u0005\u0003\u0005\u000b\u0011\u0002D8\u0011)\u0019I* B\u0001B\u0003%a1\u000f\u0005\b\u0005[iH\u0011\u0001D=\u0011!1\u0019) Q!\n\u0019=\u0004\u0002\u0003DC{\u0002\u0006KA\"\u001e\t\u000f\u0005]S\u0010\"\u0011\u0002Z!9\u0011qP?\u0005B\u0019\u001d\u0005\"\u0003DE\r\u0006\u0005I\u0011\u0002DF\u0005!IE/\u001a:bi>\u0014(\u0002BA\t\u0003'\t!bY8mY\u0016\u001cG/[8o\u0015\t\t)\"A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\t\u0005m\u0011\u0011G\n\b\u0001\u0005u\u0011QEA\"!\u0011\ty\"!\t\u000e\u0005\u0005M\u0011\u0002BA\u0012\u0003'\u0011a!\u00118z%\u00164\u0007CBA\u0014\u0003S\ti#\u0004\u0002\u0002\u0010%!\u00111FA\b\u00051IE/\u001a:bE2,wJ\\2f!\u0011\ty#!\r\r\u0001\u0011A\u00111\u0007\u0001\u0005\u0006\u0004\t)DA\u0001B#\u0011\t9$!\u0010\u0011\t\u0005}\u0011\u0011H\u0005\u0005\u0003w\t\u0019BA\u0004O_RD\u0017N\\4\u0011\t\u0005}\u0011qH\u0005\u0005\u0003\u0003\n\u0019BA\u0002B]f\u0004\"\"a\n\u0002F\u00055\u0012\u0011JA&\u0013\u0011\t9%a\u0004\u0003\u001f%#XM]1cY\u0016|enY3PaN\u00042!a\n\u0001!\u0015\t9\u0003AA\u0017\u0003\u0019!\u0013N\\5uIQ\u0011\u0011\u0011\u000b\t\u0005\u0003?\t\u0019&\u0003\u0003\u0002V\u0005M!\u0001B+oSR\fq\u0001[1t\u001d\u0016DH/\u0006\u0002\u0002\\A!\u0011qDA/\u0013\u0011\ty&a\u0005\u0003\u000f\t{w\u000e\\3b]\u0006y\u0001.Y:EK\u001aLg.\u001b;f'&TX\rK\u0006\u0004\u0003K\nY'!\u001c\u0002r\u0005M\u0004\u0003BA\u0010\u0003OJA!!\u001b\u0002\u0014\tQA-\u001a9sK\u000e\fG/\u001a3\u0002\u000f5,7o]1hK\u0006\u0012\u0011qN\u00013Q\u0006\u001cH)\u001a4j]&$XmU5{K\u0002zg\u000eI%uKJ\fGo\u001c:!SN\u0004C\u000f[3!g\u0006lW\rI1tA%\u001cX)\u001c9us\u0006)1/\u001b8dK\u0006\u0012\u0011QO\u0001\u0007e9\n4G\f\u0019)\u0007\r\tI\b\u0005\u0003\u0002 \u0005m\u0014\u0002BA?\u0003'\u0011a!\u001b8mS:,\u0017\u0001\u00028fqR$\"!!\f)\u000b\u0011\t))a)\u0011\r\u0005}\u0011qQAF\u0013\u0011\tI)a\u0005\u0003\rQD'o\\<t!\u0011\ti)!(\u000f\t\u0005=\u0015\u0011\u0014\b\u0005\u0003#\u000b9*\u0004\u0002\u0002\u0014*!\u0011QSA\f\u0003\u0019a$o\\8u}%\u0011\u0011QC\u0005\u0005\u00037\u000b\u0019\"A\u0004qC\u000e\\\u0017mZ3\n\t\u0005}\u0015\u0011\u0015\u0002\u0017\u001d>\u001cVo\u00195FY\u0016lWM\u001c;Fq\u000e,\u0007\u000f^5p]*!\u00111TA\nc\u001dq\u0012QUA[\u00037\u0004B!a*\u00020:!\u0011\u0011VAV!\u0011\t\t*a\u0005\n\t\u00055\u00161C\u0001\u0007!J,G-\u001a4\n\t\u0005E\u00161\u0017\u0002\u0007'R\u0014\u0018N\\4\u000b\t\u00055\u00161C\u0019\nG\u0005]\u0016qXAi\u0003\u0003,B!!/\u0002<V\u0011\u0011Q\u0015\u0003\t\u0003{\u000b9B1\u0001\u0002H\n\tA+\u0003\u0003\u0002B\u0006\r\u0017a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0013G\u0003\u0003\u0002F\u0006M\u0011A\u0002;ie><8/\u0005\u0003\u00028\u0005%\u0007\u0003BAf\u0003\u001btA!a\b\u0002\u001a&!\u0011qZAQ\u0005%!\u0006N]8xC\ndW-M\u0005$\u0003'\f).a6\u0002F:!\u0011qDAk\u0013\u0011\t)-a\u00052\u000f\t\ny\"a\u0005\u0002Z\n)1oY1mCF\u001aa%a#\u0002\u0011%$XM]1u_J,\"!a\u0013)\u0007\u0015\tI(\u0001\u0006oKb$x\n\u001d;j_:$\"!a:\u0011\r\u0005}\u0011\u0011^A\u0017\u0013\u0011\tY/a\u0005\u0003\r=\u0003H/[8o\u0003!\u0019wN\u001c;bS:\u001cH\u0003BA.\u0003cDq!a=\b\u0001\u0004\ti$\u0001\u0003fY\u0016l\u0017\u0001\u00032vM\u001a,'/\u001a3\u0016\u0005\u0005e\bCBA\u0014\u0003w\fi#\u0003\u0003\u0002~\u0006=!\u0001\u0005\"vM\u001a,'/\u001a3Ji\u0016\u0014\u0018\r^8s\u0005=9%o\\;qK\u0012LE/\u001a:bi>\u0014X\u0003\u0002B\u0002\u00053\u00192!\u0003B\u0003!\u0019\t9Ca\u0002\u0003\f%!!\u0011BA\b\u0005A\t%m\u001d;sC\u000e$\u0018\n^3sCR|'\u000f\u0005\u0004\u0003\u000e\tM!qC\u0007\u0003\u0005\u001fQAA!\u0005\u0002\u0010\u0005I\u0011.\\7vi\u0006\u0014G.Z\u0005\u0005\u0005+\u0011yAA\u0002TKF\u0004B!a\f\u0003\u001a\u00119!1D\u0005C\u0002\tu!!\u0001\"\u0012\t\u00055\u0012QH\u0001\u0005g\u0016dg\rE\u0003\u0002(\u0001\u00119\"\u0001\u0003tSj,\u0007\u0003BA\u0010\u0005OIAA!\u000b\u0002\u0014\t\u0019\u0011J\u001c;\u0002\tM$X\r]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0011\tE\"Q\u0007B\u001c\u0005s\u0001RAa\r\n\u0005/i\u0011\u0001\u0001\u0005\b\u0005?i\u0001\u0019\u0001B\u0011\u0011\u001d\u0011\u0019#\u0004a\u0001\u0005KAqAa\u000b\u000e\u0001\u0004\u0011)#\u0001\u0004ck\u001a4WM\u001d\t\u0007\u0003?\u0011yDa\u0006\n\t\t\u0005\u00131\u0003\u0002\u0006\u0003J\u0014\u0018-_\u0001\u0005aJ,g/A\u0003gSJ\u001cH/\u0001\u0004gS2dW\rZ\u0001\ba\u0006\u0014H/[1m\u0003\u001d\u0001\u0018\r\u001a3j]\u001e\u0004b!a\b\u0003P\t]\u0011\u0002\u0002B)\u0003'\u0011\u0011BR;oGRLwN\u001c\u0019\u0002\u0007A\fG-\u0001\u0006oK^\u0014U/\u001b7eKJ,\"A!\u0017\u0011\r\tm#\u0011MA\u001f\u001b\t\u0011iF\u0003\u0003\u0003`\u0005=\u0011aB7vi\u0006\u0014G.Z\u0005\u0005\u0005G\u0012iF\u0001\u0007BeJ\f\u0017PQ;jY\u0012,'/A\u0006xSRD\u0007+\u00193eS:<G\u0003\u0002B5\u0005Wj\u0011!\u0003\u0005\t\u0005[2B\u00111\u0001\u0003p\u0005\t\u0001\u0010\u0005\u0004\u0002 \tE$qC\u0005\u0005\u0005g\n\u0019B\u0001\u0005=Eft\u0017-\\3?\u0003-9\u0018\u000e\u001e5QCJ$\u0018.\u00197\u0015\t\t%$\u0011\u0010\u0005\b\u0005[:\u0002\u0019AA.\u0003\u001d1W\u000f\u001c4jY2$\"!a\u0017\u0002\t\u0019LG\u000e\u001c\u000b\u0003\u0005\u0017ASaGAC\u0005\u000b\u000btAHAS\u0005\u000f\u0013i)M\u0005$\u0003o\u000byL!#\u0002BFJ1%a5\u0002V\n-\u0015QY\u0019\bE\u0005}\u00111CAmc\r1\u00131R\u0001\u0006a\u0006$Gk\\\u000b\u0005\u0005'\u0013I\n\u0006\u0004\u0003\u0016\nm%q\u0014\t\u0006\u0003O\u0001!q\u0013\t\u0005\u0003_\u0011I\nB\u0004\u0003\u001cq\u0011\rA!\b\t\u000f\tuE\u00041\u0001\u0003&\u0005\u0019A.\u001a8\t\u000f\u0005MH\u00041\u0001\u0003\u0018\u0006I\u0001/\u0019:uSRLwN\u001c\u000b\u0005\u0005K\u0013Y\u000b\u0005\u0005\u0002 \t\u001d\u00161JA&\u0013\u0011\u0011I+a\u0005\u0003\rQ+\b\u000f\\33\u0011\u001d\u0011i+\ba\u0001\u0005_\u000b\u0011\u0001\u001d\t\t\u0003?\u0011\t,!\f\u0002\\%!!1WA\n\u0005%1UO\\2uS>t\u0017'A\u0004he>,\b/\u001a3\u0016\t\te&q\u0018\u000b\u0005\u0005w\u0013\t\rE\u0003\u00034%\u0011i\f\u0005\u0003\u00020\t}Fa\u0002B\u000e=\t\u0007!Q\u0004\u0005\b\u0005Gq\u0002\u0019\u0001B\u0013\u0003\u001d\u0019H.\u001b3j]\u001e,BAa2\u0003NR1!\u0011\u001aBh\u0005#\u0004RAa\r\n\u0005\u0017\u0004B!a\f\u0003N\u00129!1D\u0010C\u0002\tu\u0001b\u0002B\u0012?\u0001\u0007!Q\u0005\u0005\n\u0005Wy\u0002\u0013!a\u0001\u0005K\t\u0011c\u001d7jI&tw\r\n3fM\u0006,H\u000e\u001e\u00133+\u0011\u00119N!<\u0016\u0005\te'\u0006\u0002B\u0013\u00057\\#A!8\u0011\t\t}'\u0011^\u0007\u0003\u0005CTAAa9\u0003f\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0005\u0005O\f\u0019\"\u0001\u0006b]:|G/\u0019;j_:LAAa;\u0003b\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000f\tm\u0001E1\u0001\u0003\u001e\u0005A1oY1o\u0019\u00164G/\u0006\u0003\u0003t\nmH\u0003\u0002B{\u0007\u000f!BAa>\u0003~B)\u0011q\u0005\u0001\u0003zB!\u0011q\u0006B~\t\u001d\u0011Y\"\tb\u0001\u0003kAqAa@\"\u0001\u0004\u0019\t!\u0001\u0002paBQ\u0011qDB\u0002\u0005s\fiC!?\n\t\r\u0015\u00111\u0003\u0002\n\rVt7\r^5p]JBqa!\u0003\"\u0001\u0004\u0011I0A\u0001{\u0003%\u00198-\u00198SS\u001eDG/\u0006\u0003\u0004\u0010\r]A\u0003BB\t\u0007;!Baa\u0005\u0004\u001aA)\u0011q\u0005\u0001\u0004\u0016A!\u0011qFB\f\t\u001d\u0011YB\tb\u0001\u0003kAqAa@#\u0001\u0004\u0019Y\u0002\u0005\u0006\u0002 \r\r\u0011QFB\u000b\u0007+Aqa!\u0003#\u0001\u0004\u0019)\u0002K\u0006#\u0003K\nYg!\t\u0002r\u0005M\u0014EAB\u0012\u0003\u0019\u001a\u0015\r\u001c7!g\u000e\fgNU5hQR\u0004sN\u001c\u0011b]\u0002JE/\u001a:bE2,\u0007%\u001b8ti\u0016\fGML\u0001\u000bS:$W\r_,iKJ,GC\u0002B\u0013\u0007S\u0019Y\u0003C\u0004\u0003.\u000e\u0002\rAa,\t\u0013\r52\u0005%AA\u0002\t\u0015\u0012\u0001\u00024s_6\fA#\u001b8eKb<\u0006.\u001a:fI\u0011,g-Y;mi\u0012\u0012\u0014aB5oI\u0016DxJZ\u000b\u0005\u0007k\u0019Y\u0004\u0006\u0003\u0003&\r]\u0002bBAzK\u0001\u00071\u0011\b\t\u0005\u0003_\u0019Y\u0004B\u0004\u0003\u001c\u0015\u0012\rA!\b\u0016\t\r}2Q\t\u000b\u0007\u0005K\u0019\tea\u0012\t\u000f\u0005Mh\u00051\u0001\u0004DA!\u0011qFB#\t\u001d\u0011YB\nb\u0001\u0005;Aqa!\f'\u0001\u0004\u0011)#\u0001\u0004mK:<G\u000f[\u000b\u0003\u0005KA3aJA=\u0003\u001dI7/R7qifD3\u0002KB*\u0003W\u001aI&!\u001d\u0002tA!\u0011qDB+\u0013\u0011\u00199&a\u0005\u0003)\u0011,\u0007O]3dCR,Gm\u0014<feJLG-\u001b8hC\t\u0019Y&\u0001\u001djg\u0016k\u0007\u000f^=!SN\u0004C-\u001a4j]\u0016$\u0007%Y:!C!\f7OT3yin\u0002sN^3se&$W\r\t5bg:+\u0007\u0010\u001e\u0011j]N$X-\u00193\u0002\r\u0019LG\u000e^3s)\u0011\tYe!\u0019\t\u000f\t5\u0016\u00061\u0001\u00030\u0006Ia-\u001b7uKJtu\u000e\u001e\u000b\u0005\u0003\u0017\u001a9\u0007C\u0004\u0003.*\u0002\rAa,\u0002\u0015\u0019LG\u000e^3s\u00136\u0004H\u000e\u0006\u0004\u0002L\r54q\u000e\u0005\b\u0005[[\u0003\u0019\u0001BX\u0011\u001d\u0019\th\u000ba\u0001\u00037\n\u0011\"[:GY&\u0004\b/\u001a3\u0002\u0015]LG\u000f\u001b$jYR,'\u000f\u0006\u0003\u0002L\r]\u0004b\u0002BWY\u0001\u0007!qV\u0001\bG>dG.Z2u+\u0011\u0019iha!\u0015\t\r}4Q\u0011\t\u0006\u0003O\u00011\u0011\u0011\t\u0005\u0003_\u0019\u0019\tB\u0004\u0003\u001c5\u0012\r!!\u000e\t\u000f\r\u001dU\u00061\u0001\u0004\n\u0006\u0011\u0001O\u001a\t\t\u0003?\u0019Y)!\f\u0004\u0002&!1QRA\n\u0005=\u0001\u0016M\u001d;jC24UO\\2uS>t\u0017\u0001\u00033jgRLgn\u0019;\u0002\u0015\u0011L7\u000f^5oGR\u0014\u00150\u0006\u0003\u0004\u0016\u000e}E\u0003BA&\u0007/Cqa!'0\u0001\u0004\u0019Y*A\u0001g!!\tyB!-\u0002.\ru\u0005\u0003BA\u0018\u0007?#qAa\u00070\u0005\u0004\t)$A\u0002nCB,Ba!*\u0004,R!1qUBW!\u0015\t9\u0003ABU!\u0011\tyca+\u0005\u000f\tm\u0001G1\u0001\u00026!91\u0011\u0014\u0019A\u0002\r=\u0006\u0003CA\u0010\u0005c\u000bic!+\u0002\u000f\u0019d\u0017\r^'baV!1QWB^)\u0011\u00199l!0\u0011\u000b\u0005\u001d\u0002a!/\u0011\t\u0005=21\u0018\u0003\b\u00057\t$\u0019AA\u001b\u0011\u001d\u0019I*\ra\u0001\u0007\u007f\u0003\u0002\"a\b\u00032\u000652\u0011\u0019\t\u0007\u0003O\tIc!/\u0002\u000f\u0019d\u0017\r\u001e;f]V!1qYBg)\u0011\u0019Ima4\u0011\u000b\u0005\u001d\u0002aa3\u0011\t\u0005=2Q\u001a\u0003\b\u00057\u0011$\u0019AA\u001b\u0011\u001d\u0019\tN\ra\u0002\u0007'\f!!\u001a<\u0011\u0011\u0005}!\u0011WA\u0017\u0007+\u0004b!a\n\u0002*\r-\u0017AB2p]\u000e\fG/\u0006\u0003\u0004\\\u000e\u0005H\u0003BBo\u0007G\u0004R!a\n\u0001\u0007?\u0004B!a\f\u0004b\u00129!1D\u001aC\u0002\tu\u0001\u0002CBsg\u0011\u0005\raa:\u0002\u0005a\u001c\bCBA\u0010\u0005c\u001aI\u000f\u0005\u0004\u0002(\u0005%2q\\\u0001\u000bIAdWo\u001d\u0013qYV\u001cX\u0003BBx\u0007k$Ba!=\u0004xB)\u0011q\u0005\u0001\u0004tB!\u0011qFB{\t\u001d\u0011Y\u0002\u000eb\u0001\u0005;A\u0001b!:5\t\u0003\u00071\u0011 \t\u0007\u0003?\u0011\tha?\u0011\r\u0005\u001d\u0012\u0011FBzQ\r!\u0014\u0011P\u0001\u0005i\u0006\\W\r\u0006\u0003\u0002L\u0011\r\u0001b\u0002C\u0003k\u0001\u0007!QE\u0001\u0002]\u0006IA/Y6f/\"LG.\u001a\u000b\u0005\u0003\u0017\"Y\u0001C\u0004\u0003.Z\u0002\rAa,\u0002\t\u0011\u0014x\u000e\u001d\u000b\u0005\u0003\u0017\"\t\u0002C\u0004\u0005\u0006]\u0002\rA!\n\u0002\u0013\u0011\u0014x\u000e],iS2,G\u0003BA&\t/AqA!,9\u0001\u0004\u0011y+\u0001\u0003ta\u0006tG\u0003\u0002BS\t;AqA!,:\u0001\u0004\u0011y+A\u0003tY&\u001cW\r\u0006\u0004\u0002L\u0011\rBQ\u0005\u0005\b\u0007[Q\u0004\u0019\u0001B\u0013\u0011\u001d!9C\u000fa\u0001\u0005K\tQ!\u001e8uS2\fQb\u001d7jG\u0016LE/\u001a:bi>\u0014HCBA&\t[!y\u0003C\u0004\u0004.m\u0002\rA!\n\t\u000f\u0011\u001d2\b1\u0001\u0003&\u0005\u0019!0\u001b9\u0016\t\u0011UBQ\b\u000b\u0005\to!y\u0004E\u0003\u0002(\u0001!I\u0004\u0005\u0005\u0002 \t\u001d\u0016Q\u0006C\u001e!\u0011\ty\u0003\"\u0010\u0005\u000f\tmAH1\u0001\u00026!9A\u0011\t\u001fA\u0002\u0011\r\u0013\u0001\u0002;iCR\u0004b!a\n\u0002*\u0011m\u0012A\u0002>ja\u0006cG.\u0006\u0004\u0005J\u0011ECq\u000b\u000b\t\t\u0017\"I\u0006\"\u0018\u0005bA)\u0011q\u0005\u0001\u0005NAA\u0011q\u0004BT\t\u001f\")\u0006\u0005\u0003\u00020\u0011ECa\u0002C*{\t\u0007!Q\u0004\u0002\u0003\u0003F\u0002B!a\f\u0005X\u00119!1D\u001fC\u0002\u0005U\u0002b\u0002C!{\u0001\u0007A1\f\t\u0007\u0003O\tI\u0003\"\u0016\t\u000f\u0011}S\b1\u0001\u0005P\u0005AA\u000f[5t\u000b2,W\u000eC\u0004\u0005du\u0002\r\u0001\"\u0016\u0002\u0011QD\u0017\r^#mK6\fAB_5q/&$\b.\u00138eKb,\"\u0001\"\u001b\u0011\u000b\u0005\u001d\u0002\u0001b\u001b\u0011\u0011\u0005}!qUA\u0017\u0005K\tAb]1nK\u0016cW-\\3oiN,B\u0001\"\u001d\u0005zQ!\u00111\fC:\u0011\u001d!\te\u0010a\u0001\tk\u0002b!a\n\u0002*\u0011]\u0004\u0003BA\u0018\ts\"qAa\u0007@\u0005\u0004\u0011i\"A\u0005ekBd\u0017nY1uKV\u0011!QU\u0001\u0006a\u0006$8\r[\u000b\u0005\t\u0007#I\t\u0006\u0005\u0005\u0006\u0012-EQ\u0012CI!\u0015\t9\u0003\u0001CD!\u0011\ty\u0003\"#\u0005\u000f\tm\u0011I1\u0001\u0003\u001e!91QF!A\u0002\t\u0015\u0002b\u0002CH\u0003\u0002\u0007AQQ\u0001\u000ba\u0006$8\r[#mK6\u001c\bb\u0002CJ\u0003\u0002\u0007!QE\u0001\te\u0016\u0004H.Y2fI\u00069A/\u00199FC\u000eDW\u0003\u0002CM\tC#B!a\u0013\u0005\u001c\"91\u0011\u0014\"A\u0002\u0011u\u0005\u0003CA\u0010\u0005c\u000bi\u0003b(\u0011\t\u0005=B\u0011\u0015\u0003\b\tG\u0013%\u0019AA\u001b\u0005\u0005)\u0016\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0011%\u0006\u0003\u0002CV\tkk!\u0001\",\u000b\t\u0011=F\u0011W\u0001\u0005Y\u0006twM\u0003\u0002\u00054\u0006!!.\u0019<b\u0013\u0011\t\t\f\",\u0002\u0007M,\u0017/\u0006\u0002\u00034!ZA)!\u001a\u0002l\u0011u\u0016\u0011OA:C\t!y,A\u0018Ji\u0016\u0014\u0018\r^8s]M,\u0017\u000fI1mo\u0006L8\u000f\t:fiV\u0014hn\u001d\u0011uQ\u0016\u0004\u0013\u000e^3sCR|'\u000fI5ug\u0016dg-\u0001\u0005Ji\u0016\u0014\u0018\r^8s!\r\t9CR\n\u0006\r\u0006uAq\u0019\t\u0007\u0003O!I-!\u0013\n\t\u0011-\u0017q\u0002\u0002\u0010\u0013R,'/\u00192mK\u001a\u000b7\r^8ssR\u0011A1Y\u0001\"g\u000e\fG.\u0019\u0013d_2dWm\u0019;j_:$\u0013\n^3sCR|'\u000f\n\u0013`K6\u0004H/\u001f\t\u0006\u0003O\u0001\u0011qG\u000b\u0005\t+$Y\u000e\u0006\u0003\u0005X\u0012u\u0007#BA\u0014\u0001\u0011e\u0007\u0003BA\u0018\t7$q!a\rJ\u0005\u0004\t)\u0004C\u0004\u0005`&\u0003\r\u0001\"9\u0002\rM|WO]2f!\u0019\t9#!\u000b\u0005Z\u0006)Q-\u001c9usV!Aq\u001dCw+\t!I\u000fE\u0003\u0002(\u0001!Y\u000f\u0005\u0003\u00020\u00115HaBA_\u0015\n\u0007\u0011Q\u0007\u0015\u0004\u0015\u0006e\u0014AB:j]\u001edW-\u0006\u0003\u0005v\u0012mH\u0003\u0002C|\t{\u0004R!a\n\u0001\ts\u0004B!a\f\u0005|\u00129\u00111G&C\u0002\u0005U\u0002b\u0002C\u0000\u0017\u0002\u0007A\u0011`\u0001\u0002C\u0006)\u0011\r\u001d9msV!QQAC\u0006)\u0011)9!\"\u0004\u0011\u000b\u0005\u001d\u0002!\"\u0003\u0011\t\u0005=R1\u0002\u0003\b\u0003ga%\u0019AA\u001b\u0011\u001d\u0019)\u000f\u0014a\u0001\u000b\u001f\u0001b!a\b\u0006\u0012\u0015%\u0011\u0002BC\n\u0003'\u0011!\u0002\u0010:fa\u0016\fG/\u001a3?+\u0011)9\"\"\t\u0016\u0005\u0015e\u0001\u0003\u0003B.\u000b7)y\"b\t\n\t\u0015u!Q\f\u0002\b\u0005VLG\u000eZ3s!\u0011\ty#\"\t\u0005\u000f\u0005MRJ1\u0001\u00026A)\u0011q\u0005\u0001\u0006 U!QqEC\u0018)\u0011)I#\"\u000e\u0015\t\u0015-R\u0011\u0007\t\u0006\u0003O\u0001QQ\u0006\t\u0005\u0003_)y\u0003B\u0004\u000249\u0013\r!!\u000e\t\u0011\u0005Mh\n\"a\u0001\u000bg\u0001b!a\b\u0003r\u00155\u0002b\u0002BO\u001d\u0002\u0007!QE\u0001\ti\u0006\u0014W\u000f\\1uKV!Q1HC\")\u0011)i$\"\u0013\u0015\t\u0015}RQ\t\t\u0006\u0003O\u0001Q\u0011\t\t\u0005\u0003_)\u0019\u0005B\u0004\u00024=\u0013\r!!\u000e\t\u000f\reu\n1\u0001\u0006HAA\u0011q\u0004BY\u0005K)\t\u0005C\u0004\u0006L=\u0003\rA!\n\u0002\u0007\u0015tG\r\u0006\u0003\u0006P\u0015E\u0003#BA\u0014\u0001\t\u0015\u0002bBC*!\u0002\u0007!QE\u0001\u0006gR\f'\u000f\u001e\u000b\u0007\u000b\u001f*9&\"\u0017\t\u000f\u0015M\u0013\u000b1\u0001\u0003&!9!1F)A\u0002\t\u0015\u0012!\u0002:b]\u001e,GCBC(\u000b?*\t\u0007C\u0004\u0006TI\u0003\rA!\n\t\u000f\u0015-#\u000b1\u0001\u0003&QAQqJC3\u000bO*I\u0007C\u0004\u0006TM\u0003\rA!\n\t\u000f\u0015-3\u000b1\u0001\u0003&!9!1F*A\u0002\t\u0015\u0012aB5uKJ\fG/Z\u000b\u0005\u000b_*9\b\u0006\u0003\u0006r\u0015uD\u0003BC:\u000bs\u0002R!a\n\u0001\u000bk\u0002B!a\f\u0006x\u00119\u0011Q\u0018+C\u0002\u0005U\u0002bBBM)\u0002\u0007Q1\u0010\t\t\u0003?\u0011\t,\"\u001e\u0006v!9Q1\u000b+A\u0002\u0015U\u0014AB;oM>dG-\u0006\u0004\u0006\u0004\u0016-U1\u0013\u000b\u0005\u000b\u000b+Y\n\u0006\u0003\u0006\b\u00165\u0005#BA\u0014\u0001\u0015%\u0005\u0003BA\u0018\u000b\u0017#q!a\rV\u0005\u0004\t)\u0004C\u0004\u0004\u001aV\u0003\r!b$\u0011\u0011\u0005}!\u0011WCI\u000b/\u0003B!a\f\u0006\u0014\u00129QQS+C\u0002\u0005U\"!A*\u0011\r\u0005}\u0011\u0011^CM!!\tyBa*\u0006\n\u0016E\u0005bBCO+\u0002\u0007Q\u0011S\u0001\u0005S:LG/A\u0006d_:$\u0018N\\;bY2LX\u0003BCR\u000bS#B!\"*\u0006,B)\u0011q\u0005\u0001\u0006(B!\u0011qFCU\t\u001d\t\u0019D\u0016b\u0001\u0003kA\u0001\"a=W\t\u0003\u0007QQ\u0016\t\u0007\u0003?\u0011\t(b*\u0003\u001d\r{gnY1u\u0013R,'/\u0019;peV!Q1WC]'\r9VQ\u0017\t\u0007\u0003O\u00119!b.\u0011\t\u0005=R\u0011\u0018\u0003\t\u0003g9FQ1\u0001\u00026\u000591-\u001e:sK:$XCAC`!\u0015\t9\u0003ACaU\u0011)9La7\u0002\u0017\r,(O]3oi~#S-\u001d\u000b\u0005\u0003#*9\rC\u0005\u0006Jf\u000b\t\u00111\u0001\u0006@\u0006\u0019\u0001\u0010J\u0019\u0002\u0011\r,(O]3oi\u0002\"B!b4\u0006TB)Q\u0011[,\u000686\ta\tC\u0004\u0006<n\u0003\r!b0\u0002\tQ\f\u0017\u000e\\\u000b\u0003\u000b3\u0004R!\"5i\u000b\u0003\u0014!cQ8oG\u0006$\u0018\n^3sCR|'oQ3mYV!Qq\\Cu'\rA\u0017QD\u0001\u0005Q\u0016\fG\r\u0005\u0004\u0002 \tETQ\u001d\t\u0007\u0003O\tI#b:\u0011\t\u0005=R\u0011\u001e\u0003\b\u0003gA'\u0019AA\u001b+\t)i\u000fE\u0003\u0006R\",9/\u0001\u0005uC&dw\fJ3r)\u0011\t\t&b=\t\u0013\u0015%7.!AA\u0002\u00155\u0018!\u0002;bS2\u0004CCBCw\u000bs,Y\u0010\u0003\u0005\u0006b6$\t\u0019ACr\u0011\u001d)).\u001ca\u0001\u000b[\fA\u0002[3bI&#XM]1u_J,\"A\"\u0001\u0011\u000b\u0005\u001d\u0002!b:\u0015\t\u0005EcQ\u0001\u0005\n\u000b\u0013l\u0016\u0011!a\u0001\u000b3\fA\u0001\\1ti\u0006AA.Y:u?\u0012*\u0017\u000f\u0006\u0003\u0002R\u00195\u0001\"CCeA\u0006\u0005\t\u0019ACm\u0003\u0015a\u0017m\u001d;!\u0003U\u0019WO\u001d:f]RD\u0015m\u001d(fqR\u001c\u0005.Z2lK\u0012\f\u0011dY;se\u0016tG\u000fS1t\u001d\u0016DHo\u00115fG.,Gm\u0018\u0013fcR!\u0011\u0011\u000bD\f\u0011%)ImYA\u0001\u0002\u0004\tY&\u0001\fdkJ\u0014XM\u001c;ICNtU\r\u001f;DQ\u0016\u001c7.\u001a3!)\t)9,\u0006\u0003\u0007 \u0019\u0015B\u0003\u0002D\u0011\rS\u0001R!a\n\u0001\rG\u0001B!a\f\u0007&\u00119!1D4C\u0002\u0019\u001d\u0012\u0003BC\\\u0003{A\u0001\u0002\"\u0011h\t\u0003\u0007a1\u0006\t\u0007\u0003?\u0011\tH\"\f\u0011\r\u0005\u001d\u0012\u0011\u0006D\u0012\u00055\u0019F.[2f\u0013R,'/\u0019;peV!a1\u0007D\u001d'\rygQ\u0007\t\u0007\u0003O\u00119Ab\u000e\u0011\t\u0005=b\u0011\b\u0003\b\u0003gy'\u0019AA\u001b\u0003))h\u000eZ3sYfLgnZ\u000b\u0003\r\u007f\u0001R!a\n\u0001\ro\t1\"\u001e8eKJd\u00170\u001b8hA\u0005)A.[7jiRAaq\tD%\r\u00172i\u0005E\u0003\u0006R>49\u0004C\u0004\u0007<Q\u0004\rAb\u0010\t\u000f\u0015MC\u000f1\u0001\u0003&!9a1\t;A\u0002\t\u0015\u0012AM:dC2\fGeY8mY\u0016\u001cG/[8oI%#XM]1u_J$3\u000b\\5dK&#XM]1u_J$CE]3nC&t\u0017N\\4\u0002\u0011\u0011\u0014x\u000e\u001d9j]\u001e\f\u0011\"\u001e8c_VtG-\u001a3)\u0007]\fI(\u0001\u0003tW&\u0004\u0018!C6o_^t7+\u001b>f)\t19\u0004\u0006\u0004\u0007@\u0019}c\u0011\r\u0005\b\u0007[a\b\u0019\u0001B\u0013\u0011\u001d!9\u0003 a\u0001\u0005K\u0011a\"\u00168g_2$\u0017\n^3sCR|'/\u0006\u0004\u0007h\u00195d\u0011O\n\u0004{\u001a%\u0004CBA\u0014\u0005\u000f1Y\u0007\u0005\u0003\u00020\u00195DaBA\u001a{\n\u0007\u0011Q\u0007\t\u0005\u0003_1\t\bB\u0004\u0006\u0016v\u0014\r!!\u000e\u0011\u0011\u0005}!\u0011\u0017D8\rk\u0002b!a\b\u0002j\u001a]\u0004\u0003CA\u0010\u0005O3YGb\u001c\u0015\t\u0019md\u0011\u0011\u000b\u0005\r{2y\bE\u0004\u0006Rv4YGb\u001c\t\u0011\re\u0015\u0011\u0001a\u0001\rgB\u0001\"\"(\u0002\u0002\u0001\u0007aqN\u0001\u0006gR\fG/Z\u0001\u000b]\u0016DHOU3tk2$HC\u0001D6\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t1i\t\u0005\u0003\u0005,\u001a=\u0015\u0002\u0002DI\t[\u0013aa\u00142kK\u000e$\bf\u0002$\u0007\u0016\u001ameQ\u0014\t\u0005\u0003?19*\u0003\u0003\u0007\u001a\u0006M!\u0001E*fe&\fGNV3sg&|g.V%E\u0003\u00151\u0018\r\\;f=\u0005\u0019\u0001fB#\u0007\u0016\u001ameQ\u0014"
)
public interface Iterator extends IterableOnce, IterableOnceOps {
   static Iterator continually(final Function0 elem) {
      Iterator$ var10000 = Iterator$.MODULE$;
      return new AbstractIterator(elem) {
         private final Function0 elem$5;

         public boolean hasNext() {
            return true;
         }

         public Object next() {
            return this.elem$5.apply();
         }

         public {
            this.elem$5 = elem$5;
         }
      };
   }

   static Iterator unfold(final Object init, final Function1 f) {
      Iterator$ var10000 = Iterator$.MODULE$;
      return new UnfoldIterator(init, f);
   }

   static Iterator iterate(final Object start, final Function1 f) {
      Iterator$ var10000 = Iterator$.MODULE$;
      return new AbstractIterator(start, f) {
         private boolean first;
         private Object acc;
         private final Function1 f$6;

         public boolean hasNext() {
            return true;
         }

         public Object next() {
            if (this.first) {
               this.first = false;
            } else {
               this.acc = this.f$6.apply(this.acc);
            }

            return this.acc;
         }

         public {
            this.f$6 = f$6;
            this.first = true;
            this.acc = start$3;
         }
      };
   }

   static Iterator range(final int start, final int end, final int step) {
      Iterator$ var10000 = Iterator$.MODULE$;
      return new AbstractIterator(step, start, end) {
         private int i;
         private boolean hasOverflowed;
         private final int end$2;
         private final int step$2;

         public int knownSize() {
            scala.math.package$ var10000 = scala.math.package$.MODULE$;
            double size = Math.ceil((double)((long)this.end$2 - (long)this.i) / (double)this.step$2);
            if (size < (double)0) {
               return 0;
            } else {
               return size > (double)Integer.MAX_VALUE ? -1 : (int)size;
            }
         }

         public boolean hasNext() {
            return (this.step$2 <= 0 || this.i < this.end$2) && (this.step$2 >= 0 || this.i > this.end$2) && !this.hasOverflowed;
         }

         public int next() {
            if (this.hasNext()) {
               int result = this.i;
               int nextValue = this.i + this.step$2;
               this.hasOverflowed = this.step$2 > 0 == nextValue < this.i;
               this.i = nextValue;
               return result;
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return BoxesRunTime.unboxToInt(Iterator$.scala$collection$Iterator$$_empty.next());
            }
         }

         public {
            this.end$2 = end$2;
            this.step$2 = step$2;
            if (step$2 == 0) {
               throw new IllegalArgumentException("zero step");
            } else {
               this.i = start$2;
               this.hasOverflowed = false;
            }
         }
      };
   }

   static Iterator range(final int start, final int end) {
      Iterator$ var10000 = Iterator$.MODULE$;
      int range_range_step = 1;
      return new AbstractIterator(range_range_step, start, end) {
         private int i;
         private boolean hasOverflowed;
         private final int end$2;
         private final int step$2;

         public int knownSize() {
            scala.math.package$ var10000 = scala.math.package$.MODULE$;
            double size = Math.ceil((double)((long)this.end$2 - (long)this.i) / (double)this.step$2);
            if (size < (double)0) {
               return 0;
            } else {
               return size > (double)Integer.MAX_VALUE ? -1 : (int)size;
            }
         }

         public boolean hasNext() {
            return (this.step$2 <= 0 || this.i < this.end$2) && (this.step$2 >= 0 || this.i > this.end$2) && !this.hasOverflowed;
         }

         public int next() {
            if (this.hasNext()) {
               int result = this.i;
               int nextValue = this.i + this.step$2;
               this.hasOverflowed = this.step$2 > 0 == nextValue < this.i;
               this.i = nextValue;
               return result;
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return BoxesRunTime.unboxToInt(Iterator$.scala$collection$Iterator$$_empty.next());
            }
         }

         public {
            this.end$2 = end$2;
            this.step$2 = step$2;
            if (step$2 == 0) {
               throw new IllegalArgumentException("zero step");
            } else {
               this.i = start$2;
               this.hasOverflowed = false;
            }
         }
      };
   }

   static Iterator from(final int start, final int step) {
      Iterator$ var10000 = Iterator$.MODULE$;
      return new AbstractIterator(start, step) {
         private int i;
         private final int step$1;

         public boolean hasNext() {
            return true;
         }

         public int next() {
            int result = this.i;
            this.i += this.step$1;
            return result;
         }

         public {
            this.step$1 = step$1;
            this.i = start$1;
         }
      };
   }

   static Iterator from(final int start) {
      Iterator$ var10000 = Iterator$.MODULE$;
      int from_from_step = 1;
      return new AbstractIterator(start, from_from_step) {
         private int i;
         private final int step$1;

         public boolean hasNext() {
            return true;
         }

         public int next() {
            int result = this.i;
            this.i += this.step$1;
            return result;
         }

         public {
            this.step$1 = step$1;
            this.i = start$1;
         }
      };
   }

   static Iterator tabulate(final int end, final Function1 f) {
      Iterator$ var10000 = Iterator$.MODULE$;
      return new AbstractIterator(end, f) {
         private int i;
         private final int end$1;
         private final Function1 f$5;

         public int knownSize() {
            RichInt$ var10000 = RichInt$.MODULE$;
            int var1 = this.end$1 - this.i;
            int max$extension_that = 0;
            scala.math.package$ var3 = scala.math.package$.MODULE$;
            return Math.max(var1, max$extension_that);
         }

         public boolean hasNext() {
            return this.i < this.end$1;
         }

         public Object next() {
            if (this.hasNext()) {
               Object result = this.f$5.apply(this.i);
               ++this.i;
               return result;
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty.next();
            }
         }

         public {
            this.end$1 = end$1;
            this.f$5 = f$5;
            this.i = 0;
         }
      };
   }

   static Iterator fill(final int len, final Function0 elem) {
      Iterator$ var10000 = Iterator$.MODULE$;
      return new AbstractIterator(len, elem) {
         private int i;
         private final int len$2;
         private final Function0 elem$4;

         public int knownSize() {
            RichInt$ var10000 = RichInt$.MODULE$;
            int var1 = this.len$2 - this.i;
            int max$extension_that = 0;
            scala.math.package$ var3 = scala.math.package$.MODULE$;
            return Math.max(var1, max$extension_that);
         }

         public boolean hasNext() {
            return this.i < this.len$2;
         }

         public Object next() {
            if (this.hasNext()) {
               ++this.i;
               return this.elem$4.apply();
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty.next();
            }
         }

         public {
            this.len$2 = len$2;
            this.elem$4 = elem$4;
            this.i = 0;
         }
      };
   }

   static Builder newBuilder() {
      Iterator$ var10000 = Iterator$.MODULE$;
      return new ImmutableBuilder() {
         public <undefinedtype> addOne(final Object elem) {
            Iterator var10001 = (Iterator)this.elems();
            Function0 $plus$plus_xs = () -> Iterator$.MODULE$.single(elem);
            if (var10001 == null) {
               throw null;
            } else {
               var10001 = var10001.concat($plus$plus_xs);
               $plus$plus_xs = null;
               this.elems_$eq(var10001);
               return this;
            }
         }

         public {
            Iterator$ var10001 = Iterator$.MODULE$;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   static Iterator apply(final scala.collection.immutable.Seq xs) {
      Iterator$ var10000 = Iterator$.MODULE$;
      return xs.iterator();
   }

   static Iterator single(final Object a) {
      Iterator$ var10000 = Iterator$.MODULE$;
      return new AbstractIterator(a) {
         private boolean consumed;
         private final Object a$1;

         public boolean hasNext() {
            return !this.consumed;
         }

         public Object next() {
            if (this.consumed) {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty.next();
            } else {
               this.consumed = true;
               return this.a$1;
            }
         }

         public Iterator sliceIterator(final int from, final int until) {
            if (!this.consumed && from <= 0 && until != 0) {
               return this;
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty;
            }
         }

         public {
            this.a$1 = a$1;
            this.consumed = false;
         }
      };
   }

   static Iterator empty() {
      Iterator$ var10000 = Iterator$.MODULE$;
      return Iterator$.scala$collection$Iterator$$_empty;
   }

   static Iterator from(final IterableOnce source) {
      Iterator$ var10000 = Iterator$.MODULE$;
      return source.iterator();
   }

   static Factory iterableFactory() {
      return IterableFactory.iterableFactory$(Iterator$.MODULE$);
   }

   static Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      Function1 tabulate_f = IterableFactory::$anonfun$tabulate$7$adapted;
      return new AbstractIterator(n1, tabulate_f) {
         private int i;
         private final int end$1;
         private final Function1 f$5;

         public int knownSize() {
            RichInt$ var10000 = RichInt$.MODULE$;
            int var1 = this.end$1 - this.i;
            int max$extension_that = 0;
            scala.math.package$ var3 = scala.math.package$.MODULE$;
            return Math.max(var1, max$extension_that);
         }

         public boolean hasNext() {
            return this.i < this.end$1;
         }

         public Object next() {
            if (this.hasNext()) {
               Object result = this.f$5.apply(this.i);
               ++this.i;
               return result;
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty.next();
            }
         }

         public {
            this.end$1 = end$1;
            this.f$5 = f$5;
            this.i = 0;
         }
      };
   }

   static Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      Function1 tabulate_f = IterableFactory::$anonfun$tabulate$5$adapted;
      return new AbstractIterator(n1, tabulate_f) {
         private int i;
         private final int end$1;
         private final Function1 f$5;

         public int knownSize() {
            RichInt$ var10000 = RichInt$.MODULE$;
            int var1 = this.end$1 - this.i;
            int max$extension_that = 0;
            scala.math.package$ var3 = scala.math.package$.MODULE$;
            return Math.max(var1, max$extension_that);
         }

         public boolean hasNext() {
            return this.i < this.end$1;
         }

         public Object next() {
            if (this.hasNext()) {
               Object result = this.f$5.apply(this.i);
               ++this.i;
               return result;
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty.next();
            }
         }

         public {
            this.end$1 = end$1;
            this.f$5 = f$5;
            this.i = 0;
         }
      };
   }

   static Object tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      Function1 tabulate_f = IterableFactory::$anonfun$tabulate$3$adapted;
      return new AbstractIterator(n1, tabulate_f) {
         private int i;
         private final int end$1;
         private final Function1 f$5;

         public int knownSize() {
            RichInt$ var10000 = RichInt$.MODULE$;
            int var1 = this.end$1 - this.i;
            int max$extension_that = 0;
            scala.math.package$ var3 = scala.math.package$.MODULE$;
            return Math.max(var1, max$extension_that);
         }

         public boolean hasNext() {
            return this.i < this.end$1;
         }

         public Object next() {
            if (this.hasNext()) {
               Object result = this.f$5.apply(this.i);
               ++this.i;
               return result;
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty.next();
            }
         }

         public {
            this.end$1 = end$1;
            this.f$5 = f$5;
            this.i = 0;
         }
      };
   }

   static Object tabulate(final int n1, final int n2, final Function2 f) {
      Function1 tabulate_f = IterableFactory::$anonfun$tabulate$1$adapted;
      return new AbstractIterator(n1, tabulate_f) {
         private int i;
         private final int end$1;
         private final Function1 f$5;

         public int knownSize() {
            RichInt$ var10000 = RichInt$.MODULE$;
            int var1 = this.end$1 - this.i;
            int max$extension_that = 0;
            scala.math.package$ var3 = scala.math.package$.MODULE$;
            return Math.max(var1, max$extension_that);
         }

         public boolean hasNext() {
            return this.i < this.end$1;
         }

         public Object next() {
            if (this.hasNext()) {
               Object result = this.f$5.apply(this.i);
               ++this.i;
               return result;
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty.next();
            }
         }

         public {
            this.end$1 = end$1;
            this.f$5 = f$5;
            this.i = 0;
         }
      };
   }

   static Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      Function0 fill_elem = IterableFactory::$anonfun$fill$4;
      return new AbstractIterator(n1, fill_elem) {
         private int i;
         private final int len$2;
         private final Function0 elem$4;

         public int knownSize() {
            RichInt$ var10000 = RichInt$.MODULE$;
            int var1 = this.len$2 - this.i;
            int max$extension_that = 0;
            scala.math.package$ var3 = scala.math.package$.MODULE$;
            return Math.max(var1, max$extension_that);
         }

         public boolean hasNext() {
            return this.i < this.len$2;
         }

         public Object next() {
            if (this.hasNext()) {
               ++this.i;
               return this.elem$4.apply();
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty.next();
            }
         }

         public {
            this.len$2 = len$2;
            this.elem$4 = elem$4;
            this.i = 0;
         }
      };
   }

   static Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      Function0 fill_elem = IterableFactory::$anonfun$fill$3;
      return new AbstractIterator(n1, fill_elem) {
         private int i;
         private final int len$2;
         private final Function0 elem$4;

         public int knownSize() {
            RichInt$ var10000 = RichInt$.MODULE$;
            int var1 = this.len$2 - this.i;
            int max$extension_that = 0;
            scala.math.package$ var3 = scala.math.package$.MODULE$;
            return Math.max(var1, max$extension_that);
         }

         public boolean hasNext() {
            return this.i < this.len$2;
         }

         public Object next() {
            if (this.hasNext()) {
               ++this.i;
               return this.elem$4.apply();
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty.next();
            }
         }

         public {
            this.len$2 = len$2;
            this.elem$4 = elem$4;
            this.i = 0;
         }
      };
   }

   static Object fill(final int n1, final int n2, final int n3, final Function0 elem) {
      Function0 fill_elem = IterableFactory::$anonfun$fill$2;
      return new AbstractIterator(n1, fill_elem) {
         private int i;
         private final int len$2;
         private final Function0 elem$4;

         public int knownSize() {
            RichInt$ var10000 = RichInt$.MODULE$;
            int var1 = this.len$2 - this.i;
            int max$extension_that = 0;
            scala.math.package$ var3 = scala.math.package$.MODULE$;
            return Math.max(var1, max$extension_that);
         }

         public boolean hasNext() {
            return this.i < this.len$2;
         }

         public Object next() {
            if (this.hasNext()) {
               ++this.i;
               return this.elem$4.apply();
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty.next();
            }
         }

         public {
            this.len$2 = len$2;
            this.elem$4 = elem$4;
            this.i = 0;
         }
      };
   }

   static Object fill(final int n1, final int n2, final Function0 elem) {
      Function0 fill_elem = IterableFactory::$anonfun$fill$1;
      return new AbstractIterator(n1, fill_elem) {
         private int i;
         private final int len$2;
         private final Function0 elem$4;

         public int knownSize() {
            RichInt$ var10000 = RichInt$.MODULE$;
            int var1 = this.len$2 - this.i;
            int max$extension_that = 0;
            scala.math.package$ var3 = scala.math.package$.MODULE$;
            return Math.max(var1, max$extension_that);
         }

         public boolean hasNext() {
            return this.i < this.len$2;
         }

         public Object next() {
            if (this.hasNext()) {
               ++this.i;
               return this.elem$4.apply();
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty.next();
            }
         }

         public {
            this.len$2 = len$2;
            this.elem$4 = elem$4;
            this.i = 0;
         }
      };
   }

   static Object range(final Object start, final Object end, final Object step, final Integral evidence$4) {
      return IterableFactory.range$(Iterator$.MODULE$, start, end, step, evidence$4);
   }

   static Object range(final Object start, final Object end, final Integral evidence$3) {
      return IterableFactory.range$(Iterator$.MODULE$, start, end, evidence$3);
   }

   static Object iterate(final Object start, final int len, final Function1 f) {
      Iterator$ var10000 = Iterator$.MODULE$;
      return (new View.Iterate(start, len, f)).iterator();
   }

   boolean hasNext();

   // $FF: synthetic method
   static boolean hasDefiniteSize$(final Iterator $this) {
      return $this.hasDefiniteSize();
   }

   /** @deprecated */
   default boolean hasDefiniteSize() {
      return this.isEmpty();
   }

   Object next() throws NoSuchElementException;

   // $FF: synthetic method
   static Iterator iterator$(final Iterator $this) {
      return $this.iterator();
   }

   default Iterator iterator() {
      return this;
   }

   // $FF: synthetic method
   static Option nextOption$(final Iterator $this) {
      return $this.nextOption();
   }

   default Option nextOption() {
      return (Option)(this.hasNext() ? new Some(this.next()) : None$.MODULE$);
   }

   // $FF: synthetic method
   static boolean contains$(final Iterator $this, final Object elem) {
      return $this.contains(elem);
   }

   default boolean contains(final Object elem) {
      return this.exists((x$1) -> BoxesRunTime.boxToBoolean($anonfun$contains$1(elem, x$1)));
   }

   // $FF: synthetic method
   static BufferedIterator buffered$(final Iterator $this) {
      return $this.buffered();
   }

   default BufferedIterator buffered() {
      return new BufferedIterator() {
         private Object hd;
         private boolean hdDefined;
         // $FF: synthetic field
         private final Iterator $outer;

         public Option headOption() {
            return BufferedIterator.headOption$(this);
         }

         public BufferedIterator buffered() {
            return BufferedIterator.buffered$(this);
         }

         public Object head() {
            if (!this.hdDefined) {
               this.hd = this.next();
               this.hdDefined = true;
            }

            return this.hd;
         }

         public int knownSize() {
            int thisSize = this.$outer.knownSize();
            return thisSize >= 0 && this.hdDefined ? thisSize + 1 : thisSize;
         }

         public boolean hasNext() {
            return this.hdDefined || this.$outer.hasNext();
         }

         public Object next() {
            if (this.hdDefined) {
               this.hdDefined = false;
               return this.hd;
            } else {
               return this.$outer.next();
            }
         }

         public {
            if (Iterator.this == null) {
               throw null;
            } else {
               this.$outer = Iterator.this;
               this.hdDefined = false;
            }
         }
      };
   }

   // $FF: synthetic method
   static Iterator padTo$(final Iterator $this, final int len, final Object elem) {
      return $this.padTo(len, elem);
   }

   default Iterator padTo(final int len, final Object elem) {
      return new AbstractIterator(len, elem) {
         private int i;
         // $FF: synthetic field
         private final Iterator $outer;
         private final int len$1;
         private final Object elem$2;

         public int knownSize() {
            int thisSize = this.$outer.knownSize();
            if (thisSize < 0) {
               return -1;
            } else {
               RichInt$ var10000 = RichInt$.MODULE$;
               int max$extension_that = this.len$1 - this.i;
               scala.math.package$ var3 = scala.math.package$.MODULE$;
               return Math.max(thisSize, max$extension_that);
            }
         }

         public Object next() {
            Object var10000;
            if (this.$outer.hasNext()) {
               var10000 = this.$outer.next();
            } else if (this.i < this.len$1) {
               var10000 = this.elem$2;
            } else {
               Iterator$ var2 = Iterator$.MODULE$;
               var10000 = Iterator$.scala$collection$Iterator$$_empty.next();
            }

            Object b = var10000;
            ++this.i;
            return b;
         }

         public boolean hasNext() {
            return this.$outer.hasNext() || this.i < this.len$1;
         }

         public {
            if (Iterator.this == null) {
               throw null;
            } else {
               this.$outer = Iterator.this;
               this.len$1 = len$1;
               this.elem$2 = elem$2;
               this.i = 0;
            }
         }
      };
   }

   // $FF: synthetic method
   static Tuple2 partition$(final Iterator $this, final Function1 p) {
      return $this.partition(p);
   }

   default Tuple2 partition(final Function1 p) {
      Tuple2 var2 = this.duplicate();
      if (var2 != null) {
         Iterator a = (Iterator)var2._1();
         Iterator b = (Iterator)var2._2();
         return new Tuple2(a.filter(p), b.filterNot(p));
      } else {
         throw new MatchError((Object)null);
      }
   }

   // $FF: synthetic method
   static GroupedIterator grouped$(final Iterator $this, final int size) {
      return $this.grouped(size);
   }

   default GroupedIterator grouped(final int size) {
      return new GroupedIterator(this, size, size);
   }

   // $FF: synthetic method
   static GroupedIterator sliding$(final Iterator $this, final int size, final int step) {
      return $this.sliding(size, step);
   }

   default GroupedIterator sliding(final int size, final int step) {
      return new GroupedIterator(this, size, step);
   }

   // $FF: synthetic method
   static int sliding$default$2$(final Iterator $this) {
      return $this.sliding$default$2();
   }

   default int sliding$default$2() {
      return 1;
   }

   // $FF: synthetic method
   static Iterator scanLeft$(final Iterator $this, final Object z, final Function2 op) {
      return $this.scanLeft(z, op);
   }

   default Iterator scanLeft(final Object z, final Function2 op) {
      return new AbstractIterator(z, op) {
         public Iterator scala$collection$Iterator$$anon$$current;
         // $FF: synthetic field
         private final Iterator $outer;
         public final Object z$1;
         public final Function2 op$1;

         public int knownSize() {
            return this.scala$collection$Iterator$$anon$$current.knownSize();
         }

         public Object next() {
            return this.scala$collection$Iterator$$anon$$current.next();
         }

         public boolean hasNext() {
            return this.scala$collection$Iterator$$anon$$current.hasNext();
         }

         // $FF: synthetic method
         public Iterator scala$collection$Iterator$$anon$$$outer() {
            return this.$outer;
         }

         public {
            if (Iterator.this == null) {
               throw null;
            } else {
               this.$outer = Iterator.this;
               this.z$1 = z$1;
               this.op$1 = op$1;
               this.scala$collection$Iterator$$anon$$current = new AbstractIterator() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public int knownSize() {
                     int thisSize = this.$outer.scala$collection$Iterator$$anon$$$outer().knownSize();
                     return thisSize < 0 ? -1 : thisSize + 1;
                  }

                  public boolean hasNext() {
                     return true;
                  }

                  public Object next() {
                     this.$outer.scala$collection$Iterator$$anon$$current = new AbstractIterator() {
                        private Object acc;
                        // $FF: synthetic field
                        private final <undefinedtype> $outer;

                        public Object next() {
                           this.acc = this.$outer.scala$collection$Iterator$$anon$$anon$$$outer().op$1.apply(this.acc, this.$outer.scala$collection$Iterator$$anon$$anon$$$outer().scala$collection$Iterator$$anon$$$outer().next());
                           return this.acc;
                        }

                        public boolean hasNext() {
                           return this.$outer.scala$collection$Iterator$$anon$$anon$$$outer().scala$collection$Iterator$$anon$$$outer().hasNext();
                        }

                        public int knownSize() {
                           return this.$outer.scala$collection$Iterator$$anon$$anon$$$outer().scala$collection$Iterator$$anon$$$outer().knownSize();
                        }

                        public {
                           if (<VAR_NAMELESS_ENCLOSURE> == null) {
                              throw null;
                           } else {
                              this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                              this.acc = scala$collection$Iterator$$anon$$anon$$$outer().z$1;
                           }
                        }
                     };
                     return this.$outer.z$1;
                  }

                  // $FF: synthetic method
                  public <undefinedtype> scala$collection$Iterator$$anon$$anon$$$outer() {
                     return this.$outer;
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }
               };
            }
         }
      };
   }

   // $FF: synthetic method
   static Iterator scanRight$(final Iterator $this, final Object z, final Function2 op) {
      return $this.scanRight(z, op);
   }

   /** @deprecated */
   default Iterator scanRight(final Object z, final Function2 op) {
      return ((IndexedSeqOps)ArrayBuffer$.MODULE$.from(this).scanRight(z, op)).iterator();
   }

   // $FF: synthetic method
   static int indexWhere$(final Iterator $this, final Function1 p, final int from) {
      return $this.indexWhere(p, from);
   }

   default int indexWhere(final Function1 p, final int from) {
      scala.math.package$ var10000 = scala.math.package$.MODULE$;
      int max_y = 0;
      int i = Math.max(from, max_y);

      for(Iterator dropped = this.drop(from); dropped.hasNext(); ++i) {
         if (BoxesRunTime.unboxToBoolean(p.apply(dropped.next()))) {
            return i;
         }
      }

      return -1;
   }

   // $FF: synthetic method
   static int indexWhere$default$2$(final Iterator $this) {
      return $this.indexWhere$default$2();
   }

   default int indexWhere$default$2() {
      return 0;
   }

   // $FF: synthetic method
   static int indexOf$(final Iterator $this, final Object elem) {
      return $this.indexOf(elem);
   }

   default int indexOf(final Object elem) {
      return this.indexOf(elem, 0);
   }

   // $FF: synthetic method
   static int indexOf$(final Iterator $this, final Object elem, final int from) {
      return $this.indexOf(elem, from);
   }

   default int indexOf(final Object elem, final int from) {
      int i;
      for(i = 0; i < from && this.hasNext(); ++i) {
         this.next();
      }

      while(this.hasNext()) {
         if (BoxesRunTime.equals(this.next(), elem)) {
            return i;
         }

         ++i;
      }

      return -1;
   }

   // $FF: synthetic method
   static int length$(final Iterator $this) {
      return $this.length();
   }

   default int length() {
      return this.size();
   }

   // $FF: synthetic method
   static boolean isEmpty$(final Iterator $this) {
      return $this.isEmpty();
   }

   default boolean isEmpty() {
      return !this.hasNext();
   }

   // $FF: synthetic method
   static Iterator filter$(final Iterator $this, final Function1 p) {
      return $this.filter(p);
   }

   default Iterator filter(final Function1 p) {
      return this.filterImpl(p, false);
   }

   // $FF: synthetic method
   static Iterator filterNot$(final Iterator $this, final Function1 p) {
      return $this.filterNot(p);
   }

   default Iterator filterNot(final Function1 p) {
      return this.filterImpl(p, true);
   }

   // $FF: synthetic method
   static Iterator filterImpl$(final Iterator $this, final Function1 p, final boolean isFlipped) {
      return $this.filterImpl(p, isFlipped);
   }

   default Iterator filterImpl(final Function1 p, final boolean isFlipped) {
      return new AbstractIterator(p, isFlipped) {
         private Object hd;
         private boolean hdDefined;
         // $FF: synthetic field
         private final Iterator $outer;
         private final Function1 p$1;
         private final boolean isFlipped$1;

         public boolean hasNext() {
            if (!this.hdDefined) {
               if (!this.$outer.hasNext()) {
                  return false;
               }

               for(this.hd = this.$outer.next(); BoxesRunTime.unboxToBoolean(this.p$1.apply(this.hd)) == this.isFlipped$1; this.hd = this.$outer.next()) {
                  if (!this.$outer.hasNext()) {
                     return false;
                  }
               }

               this.hdDefined = true;
            }

            return true;
         }

         public Object next() {
            if (this.hasNext()) {
               this.hdDefined = false;
               return this.hd;
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty.next();
            }
         }

         public {
            if (Iterator.this == null) {
               throw null;
            } else {
               this.$outer = Iterator.this;
               this.p$1 = p$1;
               this.isFlipped$1 = isFlipped$1;
               this.hdDefined = false;
            }
         }
      };
   }

   // $FF: synthetic method
   static Iterator withFilter$(final Iterator $this, final Function1 p) {
      return $this.withFilter(p);
   }

   default Iterator withFilter(final Function1 p) {
      return this.filter(p);
   }

   // $FF: synthetic method
   static Iterator collect$(final Iterator $this, final PartialFunction pf) {
      return $this.collect(pf);
   }

   default Iterator collect(final PartialFunction pf) {
      return new Function1(pf) {
         private Object hd;
         private int status;
         // $FF: synthetic field
         private final Iterator $outer;
         private final PartialFunction pf$1;

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

         public Object apply(final Object value) {
            return Statics.pfMarker;
         }

         public boolean hasNext() {
            Object marker = Statics.pfMarker;

            while(this.status == 0) {
               if (this.$outer.hasNext()) {
                  Object x = this.$outer.next();
                  Object v = this.pf$1.applyOrElse(x, this);
                  if (marker != v) {
                     this.hd = v;
                     this.status = 1;
                  }
               } else {
                  this.status = -1;
               }
            }

            if (this.status == 1) {
               return true;
            } else {
               return false;
            }
         }

         public Object next() {
            if (this.hasNext()) {
               this.status = 0;
               return this.hd;
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty.next();
            }
         }

         public {
            if (Iterator.this == null) {
               throw null;
            } else {
               this.$outer = Iterator.this;
               this.pf$1 = pf$1;
               this.status = 0;
            }
         }
      };
   }

   // $FF: synthetic method
   static Iterator distinct$(final Iterator $this) {
      return $this.distinct();
   }

   default Iterator distinct() {
      return this.distinctBy((x) -> Predef$.MODULE$.identity(x));
   }

   // $FF: synthetic method
   static Iterator distinctBy$(final Iterator $this, final Function1 f) {
      return $this.distinctBy(f);
   }

   default Iterator distinctBy(final Function1 f) {
      return new AbstractIterator(f) {
         private final HashSet traversedValues;
         private boolean nextElementDefined;
         private Object nextElement;
         // $FF: synthetic field
         private final Iterator $outer;
         private final Function1 f$1;

         public boolean hasNext() {
            while(true) {
               if (!this.nextElementDefined) {
                  if (!this.$outer.hasNext()) {
                     return false;
                  }

                  Object a = this.$outer.next();
                  if (!this.traversedValues.add(this.f$1.apply(a))) {
                     continue;
                  }

                  this.nextElement = a;
                  this.nextElementDefined = true;
               }

               return true;
            }
         }

         public Object next() {
            if (this.hasNext()) {
               this.nextElementDefined = false;
               return this.nextElement;
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty.next();
            }
         }

         public {
            if (Iterator.this == null) {
               throw null;
            } else {
               this.$outer = Iterator.this;
               this.f$1 = f$1;
               HashSet$ var10001 = HashSet$.MODULE$;
               this.traversedValues = new HashSet();
               this.nextElementDefined = false;
            }
         }
      };
   }

   // $FF: synthetic method
   static Iterator map$(final Iterator $this, final Function1 f) {
      return $this.map(f);
   }

   default Iterator map(final Function1 f) {
      return new AbstractIterator(f) {
         // $FF: synthetic field
         private final Iterator $outer;
         private final Function1 f$2;

         public int knownSize() {
            return this.$outer.knownSize();
         }

         public boolean hasNext() {
            return this.$outer.hasNext();
         }

         public Object next() {
            return this.f$2.apply(this.$outer.next());
         }

         public {
            if (Iterator.this == null) {
               throw null;
            } else {
               this.$outer = Iterator.this;
               this.f$2 = f$2;
            }
         }
      };
   }

   // $FF: synthetic method
   static Iterator flatMap$(final Iterator $this, final Function1 f) {
      return $this.flatMap(f);
   }

   default Iterator flatMap(final Function1 f) {
      return new AbstractIterator(f) {
         private Iterator cur;
         private int _hasNext;
         // $FF: synthetic field
         private final Iterator $outer;
         private final Function1 f$3;

         private void nextCur() {
            Iterator$ var10001 = Iterator$.MODULE$;
            this.cur = Iterator$.scala$collection$Iterator$$_empty;
            this.cur = ((IterableOnce)this.f$3.apply(this.$outer.next())).iterator();
            this._hasNext = -1;
         }

         public boolean hasNext() {
            if (this._hasNext == -1) {
               while(!this.cur.hasNext()) {
                  if (!this.$outer.hasNext()) {
                     this._hasNext = 0;
                     Iterator$ var10001 = Iterator$.MODULE$;
                     this.cur = Iterator$.scala$collection$Iterator$$_empty;
                     return false;
                  }

                  this.nextCur();
               }

               this._hasNext = 1;
               return true;
            } else {
               return this._hasNext == 1;
            }
         }

         public Object next() {
            if (this.hasNext()) {
               this._hasNext = -1;
            }

            return this.cur.next();
         }

         public {
            if (Iterator.this == null) {
               throw null;
            } else {
               this.$outer = Iterator.this;
               this.f$3 = f$3;
               Iterator$ var10001 = Iterator$.MODULE$;
               this.cur = Iterator$.scala$collection$Iterator$$_empty;
               this._hasNext = -1;
            }
         }
      };
   }

   // $FF: synthetic method
   static Iterator flatten$(final Iterator $this, final Function1 ev) {
      return $this.flatten(ev);
   }

   default Iterator flatten(final Function1 ev) {
      return this.flatMap(ev);
   }

   // $FF: synthetic method
   static Iterator concat$(final Iterator $this, final Function0 xs) {
      return $this.concat(xs);
   }

   default Iterator concat(final Function0 xs) {
      return (new ConcatIterator(this)).concat(xs);
   }

   // $FF: synthetic method
   static Iterator $plus$plus$(final Iterator $this, final Function0 xs) {
      return $this.$plus$plus(xs);
   }

   default Iterator $plus$plus(final Function0 xs) {
      return this.concat(xs);
   }

   // $FF: synthetic method
   static Iterator take$(final Iterator $this, final int n) {
      return $this.take(n);
   }

   default Iterator take(final int n) {
      RichInt$ var10002 = RichInt$.MODULE$;
      int max$extension_that = 0;
      scala.math.package$ var3 = scala.math.package$.MODULE$;
      return this.sliceIterator(0, Math.max(n, max$extension_that));
   }

   // $FF: synthetic method
   static Iterator takeWhile$(final Iterator $this, final Function1 p) {
      return $this.takeWhile(p);
   }

   default Iterator takeWhile(final Function1 p) {
      return new AbstractIterator(p) {
         private Object hd;
         private boolean hdDefined;
         private Iterator tail;
         private final Function1 p$2;

         public boolean hasNext() {
            if (!this.hdDefined) {
               if (this.tail.hasNext()) {
                  this.hd = this.tail.next();
                  if (BoxesRunTime.unboxToBoolean(this.p$2.apply(this.hd))) {
                     this.hdDefined = true;
                  } else {
                     Iterator$ var10001 = Iterator$.MODULE$;
                     this.tail = Iterator$.scala$collection$Iterator$$_empty;
                  }

                  if (this.hdDefined) {
                     return true;
                  }
               }

               return false;
            } else {
               return true;
            }
         }

         public Object next() {
            if (this.hasNext()) {
               this.hdDefined = false;
               return this.hd;
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty.next();
            }
         }

         public {
            this.p$2 = p$2;
            this.hdDefined = false;
            this.tail = Iterator.this;
         }
      };
   }

   // $FF: synthetic method
   static Iterator drop$(final Iterator $this, final int n) {
      return $this.drop(n);
   }

   default Iterator drop(final int n) {
      return this.sliceIterator(n, -1);
   }

   // $FF: synthetic method
   static Iterator dropWhile$(final Iterator $this, final Function1 p) {
      return $this.dropWhile(p);
   }

   default Iterator dropWhile(final Function1 p) {
      return new AbstractIterator(p) {
         private int status;
         private Object fst;
         // $FF: synthetic field
         private final Iterator $outer;
         private final Function1 p$3;

         public boolean hasNext() {
            if (this.status == 1) {
               return this.$outer.hasNext();
            } else if (this.status == 0) {
               return true;
            } else {
               while(this.$outer.hasNext()) {
                  Object a = this.$outer.next();
                  if (!BoxesRunTime.unboxToBoolean(this.p$3.apply(a))) {
                     this.fst = a;
                     this.status = 0;
                     return true;
                  }
               }

               this.status = 1;
               return false;
            }
         }

         public Object next() {
            if (this.hasNext()) {
               if (this.status == 1) {
                  return this.$outer.next();
               } else {
                  this.status = 1;
                  return this.fst;
               }
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty.next();
            }
         }

         public {
            if (Iterator.this == null) {
               throw null;
            } else {
               this.$outer = Iterator.this;
               this.p$3 = p$3;
               this.status = -1;
            }
         }
      };
   }

   // $FF: synthetic method
   static Tuple2 span$(final Iterator $this, final Function1 p) {
      return $this.span(p);
   }

   default Tuple2 span(final Function1 p) {
      final class Leading$1 extends AbstractIterator {
         private Queue lookahead;
         private Object hd;
         private int status;
         // $FF: synthetic field
         private final Iterator $outer;
         private final Function1 p$4;

         private void store(final Object a) {
            if (this.lookahead == null) {
               Queue$ var10003 = Queue$.MODULE$;
               this.lookahead = new Queue(16);
            }

            Queue var10000 = this.lookahead;
            if (var10000 == null) {
               throw null;
            } else {
               var10000.addOne(a);
            }
         }

         public boolean hasNext() {
            if (this.status < 0) {
               return this.lookahead != null && this.lookahead.nonEmpty();
            } else if (this.status > 0) {
               return true;
            } else {
               if (this.$outer.hasNext()) {
                  this.hd = this.$outer.next();
                  this.status = BoxesRunTime.unboxToBoolean(this.p$4.apply(this.hd)) ? 1 : -2;
               } else {
                  this.status = -1;
               }

               return this.status > 0;
            }
         }

         public Object next() {
            if (this.hasNext()) {
               if (this.status == 1) {
                  this.status = 0;
                  return this.hd;
               } else {
                  return this.lookahead.dequeue();
               }
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty.next();
            }
         }

         public boolean finish() {
            while(true) {
               int var1 = this.status;
               switch (var1) {
                  case -2:
                     this.status = -1;
                     return true;
                  case -1:
                     return false;
                  case 0:
                     this.status = -1;

                     while(this.$outer.hasNext()) {
                        Object a = this.$outer.next();
                        if (!BoxesRunTime.unboxToBoolean(this.p$4.apply(a))) {
                           this.hd = a;
                           return true;
                        }

                        this.store(a);
                     }

                     return false;
                  case 1:
                     this.store(this.hd);
                     this.status = 0;
                     break;
                  default:
                     throw new MatchError(var1);
               }
            }
         }

         public Object trailer() {
            return this.hd;
         }

         public Leading$1(final Function1 p$4) {
            if (Iterator.this == null) {
               throw null;
            } else {
               this.$outer = Iterator.this;
               this.p$4 = p$4;
               super();
               this.lookahead = null;
               this.status = 0;
            }
         }
      }

      Leading$1 leading = new Leading$1(p);
      AbstractIterator trailing = new AbstractIterator(leading) {
         private Leading$1 myLeading;
         private int status;
         // $FF: synthetic field
         private final Iterator $outer;

         public boolean hasNext() {
            while(true) {
               switch (this.status) {
                  case 0:
                     return true;
                  case 1:
                     if (this.$outer.hasNext()) {
                        this.status = 2;
                        return true;
                     }

                     this.status = 3;
                     return false;
                  case 2:
                     return true;
                  case 3:
                     return false;
                  default:
                     if (this.myLeading.finish()) {
                        this.status = 0;
                        return true;
                     }

                     this.status = 1;
                     this.myLeading = null;
               }
            }
         }

         public Object next() {
            if (this.hasNext()) {
               if (this.status == 0) {
                  this.status = 1;
                  Object res = this.myLeading.trailer();
                  this.myLeading = null;
                  return res;
               } else {
                  this.status = 1;
                  return this.$outer.next();
               }
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty.next();
            }
         }

         public {
            if (Iterator.this == null) {
               throw null;
            } else {
               this.$outer = Iterator.this;
               this.myLeading = leading$1;
               this.status = -1;
            }
         }
      };
      return new Tuple2(leading, trailing);
   }

   // $FF: synthetic method
   static Iterator slice$(final Iterator $this, final int from, final int until) {
      return $this.slice(from, until);
   }

   default Iterator slice(final int from, final int until) {
      RichInt$ var10002 = RichInt$.MODULE$;
      int max$extension_that = 0;
      scala.math.package$ var4 = scala.math.package$.MODULE$;
      return this.sliceIterator(from, Math.max(until, max$extension_that));
   }

   // $FF: synthetic method
   static Iterator sliceIterator$(final Iterator $this, final int from, final int until) {
      return $this.sliceIterator(from, until);
   }

   default Iterator sliceIterator(final int from, final int until) {
      RichInt$ var10000 = RichInt$.MODULE$;
      int max$extension_that = 0;
      scala.math.package$ var6 = scala.math.package$.MODULE$;
      int lo = Math.max(from, max$extension_that);
      int rest = until < 0 ? -1 : (until <= lo ? 0 : until - lo);
      if (rest == 0) {
         Iterator$ var7 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         return new SliceIterator(this, lo, rest);
      }
   }

   // $FF: synthetic method
   static Iterator zip$(final Iterator $this, final IterableOnce that) {
      return $this.zip(that);
   }

   default Iterator zip(final IterableOnce that) {
      return new AbstractIterator(that) {
         private final Iterator thatIterator;
         // $FF: synthetic field
         private final Iterator $outer;

         private Iterator thatIterator() {
            return this.thatIterator;
         }

         public int knownSize() {
            RichInt$ var10000 = RichInt$.MODULE$;
            int var1 = this.$outer.knownSize();
            int min$extension_that = this.thatIterator().knownSize();
            scala.math.package$ var3 = scala.math.package$.MODULE$;
            return Math.min(var1, min$extension_that);
         }

         public boolean hasNext() {
            return this.$outer.hasNext() && this.thatIterator().hasNext();
         }

         public Tuple2 next() {
            return new Tuple2(this.$outer.next(), this.thatIterator().next());
         }

         public {
            if (Iterator.this == null) {
               throw null;
            } else {
               this.$outer = Iterator.this;
               this.thatIterator = that$1.iterator();
            }
         }
      };
   }

   // $FF: synthetic method
   static Iterator zipAll$(final Iterator $this, final IterableOnce that, final Object thisElem, final Object thatElem) {
      return $this.zipAll(that, thisElem, thatElem);
   }

   default Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
      return new AbstractIterator(that, thisElem, thatElem) {
         private final Iterator thatIterator;
         // $FF: synthetic field
         private final Iterator $outer;
         private final Object thisElem$1;
         private final Object thatElem$1;

         private Iterator thatIterator() {
            return this.thatIterator;
         }

         public int knownSize() {
            int thisSize = this.$outer.knownSize();
            int thatSize = this.thatIterator().knownSize();
            if (thisSize >= 0 && thatSize >= 0) {
               RichInt$ var10000 = RichInt$.MODULE$;
               scala.math.package$ var3 = scala.math.package$.MODULE$;
               return Math.max(thisSize, thatSize);
            } else {
               return -1;
            }
         }

         public boolean hasNext() {
            return this.$outer.hasNext() || this.thatIterator().hasNext();
         }

         public Tuple2 next() {
            boolean next1 = this.$outer.hasNext();
            boolean next2 = this.thatIterator().hasNext();
            if (!next1 && !next2) {
               throw new NoSuchElementException();
            } else {
               return new Tuple2(next1 ? this.$outer.next() : this.thisElem$1, next2 ? this.thatIterator().next() : this.thatElem$1);
            }
         }

         public {
            if (Iterator.this == null) {
               throw null;
            } else {
               this.$outer = Iterator.this;
               this.thisElem$1 = thisElem$1;
               this.thatElem$1 = thatElem$1;
               this.thatIterator = that$2.iterator();
            }
         }
      };
   }

   // $FF: synthetic method
   static Iterator zipWithIndex$(final Iterator $this) {
      return $this.zipWithIndex();
   }

   default Iterator zipWithIndex() {
      return new AbstractIterator() {
         private int idx;
         // $FF: synthetic field
         private final Iterator $outer;

         private int idx() {
            return this.idx;
         }

         private void idx_$eq(final int x$1) {
            this.idx = x$1;
         }

         public int knownSize() {
            return this.$outer.knownSize();
         }

         public boolean hasNext() {
            return this.$outer.hasNext();
         }

         public Tuple2 next() {
            Tuple2 ret = new Tuple2(this.$outer.next(), this.idx());
            this.idx_$eq(this.idx() + 1);
            return ret;
         }

         public {
            if (Iterator.this == null) {
               throw null;
            } else {
               this.$outer = Iterator.this;
               this.idx = 0;
            }
         }
      };
   }

   // $FF: synthetic method
   static boolean sameElements$(final Iterator $this, final IterableOnce that) {
      return $this.sameElements(that);
   }

   default boolean sameElements(final IterableOnce that) {
      Iterator those = that.iterator();

      while(this.hasNext() && those.hasNext()) {
         if (!BoxesRunTime.equals(this.next(), those.next())) {
            return false;
         }
      }

      return this.hasNext() == those.hasNext();
   }

   // $FF: synthetic method
   static Tuple2 duplicate$(final Iterator $this) {
      return $this.duplicate();
   }

   default Tuple2 duplicate() {
      Queue$ var10002 = Queue$.MODULE$;
      Queue gap = new Queue(16);
      ObjectRef ahead = new ObjectRef((Object)null);

      class Partner$1 extends AbstractIterator {
         // $FF: synthetic field
         private final Iterator $outer;
         private final ObjectRef ahead$1;
         private final Queue gap$1;

         public int knownSize() {
            synchronized(this.$outer){}

            int var2;
            try {
               int thisSize = this.$outer.knownSize();
               var2 = this == (Iterator)this.ahead$1.elem ? thisSize : (thisSize >= 0 && this.gap$1.knownSize() >= 0 ? thisSize + this.gap$1.knownSize() : -1);
            } catch (Throwable var5) {
               throw var5;
            }

            return var2;
         }

         public boolean hasNext() {
            synchronized(this.$outer){}

            boolean var2;
            try {
               var2 = this != (Iterator)this.ahead$1.elem && !this.gap$1.isEmpty() || this.$outer.hasNext();
            } catch (Throwable var4) {
               throw var4;
            }

            return var2;
         }

         public Object next() {
            synchronized(this.$outer){}

            Object var2;
            try {
               if (this.gap$1.isEmpty()) {
                  this.ahead$1.elem = this;
               }

               Object var10000;
               if (this == (Iterator)this.ahead$1.elem) {
                  Object e = this.$outer.next();
                  this.gap$1.enqueue(e);
                  var10000 = e;
               } else {
                  var10000 = this.gap$1.dequeue();
               }

               var2 = var10000;
            } catch (Throwable var5) {
               throw var5;
            }

            return var2;
         }

         private boolean compareGap(final Queue queue) {
            return this.gap$1 == queue;
         }

         public int hashCode() {
            return this.gap$1.hashCode();
         }

         public boolean equals(final Object other) {
            if (other instanceof Partner$1) {
               return ((Partner$1)other).compareGap(this.gap$1) && this.gap$1.isEmpty();
            } else {
               return super.equals(other);
            }
         }

         public Partner$1(final ObjectRef ahead$1, final Queue gap$1) {
            if (Iterator.this == null) {
               throw null;
            } else {
               this.$outer = Iterator.this;
               this.ahead$1 = ahead$1;
               this.gap$1 = gap$1;
               super();
            }
         }
      }

      return new Tuple2(new Partner$1(ahead, gap), new Partner$1(ahead, gap));
   }

   // $FF: synthetic method
   static Iterator patch$(final Iterator $this, final int from, final Iterator patchElems, final int replaced) {
      return $this.patch(from, patchElems, replaced);
   }

   default Iterator patch(final int from, final Iterator patchElems, final int replaced) {
      return new AbstractIterator(from, replaced, patchElems) {
         public Iterator scala$collection$Iterator$$anon$$origElems;
         public int scala$collection$Iterator$$anon$$state;
         private final int replaced$1;
         private final Iterator patchElems$1;

         private void switchToPatchIfNeeded() {
            if (this.scala$collection$Iterator$$anon$$state == 0) {
               this.scala$collection$Iterator$$anon$$origElems = this.scala$collection$Iterator$$anon$$origElems.drop(this.replaced$1);
               this.scala$collection$Iterator$$anon$$state = -1;
            }
         }

         public boolean hasNext() {
            if (this.scala$collection$Iterator$$anon$$state == 0) {
               this.scala$collection$Iterator$$anon$$origElems = this.scala$collection$Iterator$$anon$$origElems.drop(this.replaced$1);
               this.scala$collection$Iterator$$anon$$state = -1;
            }

            return this.scala$collection$Iterator$$anon$$origElems.hasNext() || this.patchElems$1.hasNext();
         }

         public Object next() {
            if (this.scala$collection$Iterator$$anon$$state == 0) {
               this.scala$collection$Iterator$$anon$$origElems = this.scala$collection$Iterator$$anon$$origElems.drop(this.replaced$1);
               this.scala$collection$Iterator$$anon$$state = -1;
            }

            if (this.scala$collection$Iterator$$anon$$state < 0) {
               return this.patchElems$1.hasNext() ? this.patchElems$1.next() : this.scala$collection$Iterator$$anon$$origElems.next();
            } else if (this.scala$collection$Iterator$$anon$$origElems.hasNext()) {
               --this.scala$collection$Iterator$$anon$$state;
               return this.scala$collection$Iterator$$anon$$origElems.next();
            } else {
               this.scala$collection$Iterator$$anon$$state = -1;
               return this.patchElems$1.next();
            }
         }

         public {
            this.replaced$1 = replaced$1;
            this.patchElems$1 = patchElems$1;
            this.scala$collection$Iterator$$anon$$origElems = Iterator.this;
            this.scala$collection$Iterator$$anon$$state = from$1 > 0 ? from$1 : 0;
         }
      };
   }

   // $FF: synthetic method
   static Iterator tapEach$(final Iterator $this, final Function1 f) {
      return $this.tapEach(f);
   }

   default Iterator tapEach(final Function1 f) {
      return new AbstractIterator(f) {
         // $FF: synthetic field
         private final Iterator $outer;
         private final Function1 f$4;

         public int knownSize() {
            return this.$outer.knownSize();
         }

         public boolean hasNext() {
            return this.$outer.hasNext();
         }

         public Object next() {
            Object _next = this.$outer.next();
            this.f$4.apply(_next);
            return _next;
         }

         public {
            if (Iterator.this == null) {
               throw null;
            } else {
               this.$outer = Iterator.this;
               this.f$4 = f$4;
            }
         }
      };
   }

   // $FF: synthetic method
   static String toString$(final Iterator $this) {
      return $this.toString();
   }

   default String toString() {
      return "<iterator>";
   }

   // $FF: synthetic method
   static Iterator seq$(final Iterator $this) {
      return $this.seq();
   }

   /** @deprecated */
   default Iterator seq() {
      return this;
   }

   // $FF: synthetic method
   static boolean $anonfun$contains$1(final Object elem$1, final Object x$1) {
      return BoxesRunTime.equals(x$1, elem$1);
   }

   static void $init$(final Iterator $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class GroupedIterator extends AbstractIterator {
      private final Iterator self;
      private final int size;
      private final int step;
      private Object buffer;
      private Object prev;
      private boolean first;
      private boolean filled;
      private boolean partial;
      private Function0 padding;
      // $FF: synthetic field
      public final Iterator $outer;

      private boolean pad() {
         return this.padding != null;
      }

      private ArrayBuilder newBuilder() {
         Object var17;
         label117: {
            label120: {
               ArrayBuilder$ var10000 = ArrayBuilder$.MODULE$;
               ClassTag make_evidence$1 = ClassTag$.MODULE$.Any();
               Class var4 = make_evidence$1.runtimeClass();
               Class var8 = Byte.TYPE;
               if (var8 == null) {
                  if (var4 == null) {
                     break label120;
                  }
               } else if (var8.equals(var4)) {
                  break label120;
               }

               label121: {
                  var8 = Short.TYPE;
                  if (var8 == null) {
                     if (var4 == null) {
                        break label121;
                     }
                  } else if (var8.equals(var4)) {
                     break label121;
                  }

                  label122: {
                     var8 = Character.TYPE;
                     if (var8 == null) {
                        if (var4 == null) {
                           break label122;
                        }
                     } else if (var8.equals(var4)) {
                        break label122;
                     }

                     label123: {
                        var8 = Integer.TYPE;
                        if (var8 == null) {
                           if (var4 == null) {
                              break label123;
                           }
                        } else if (var8.equals(var4)) {
                           break label123;
                        }

                        label124: {
                           var8 = Long.TYPE;
                           if (var8 == null) {
                              if (var4 == null) {
                                 break label124;
                              }
                           } else if (var8.equals(var4)) {
                              break label124;
                           }

                           label125: {
                              var8 = Float.TYPE;
                              if (var8 == null) {
                                 if (var4 == null) {
                                    break label125;
                                 }
                              } else if (var8.equals(var4)) {
                                 break label125;
                              }

                              label126: {
                                 var8 = Double.TYPE;
                                 if (var8 == null) {
                                    if (var4 == null) {
                                       break label126;
                                    }
                                 } else if (var8.equals(var4)) {
                                    break label126;
                                 }

                                 label127: {
                                    var8 = Boolean.TYPE;
                                    if (var8 == null) {
                                       if (var4 == null) {
                                          break label127;
                                       }
                                    } else if (var8.equals(var4)) {
                                       break label127;
                                    }

                                    label60: {
                                       var8 = Void.TYPE;
                                       if (var8 == null) {
                                          if (var4 == null) {
                                             break label60;
                                          }
                                       } else if (var8.equals(var4)) {
                                          break label60;
                                       }

                                       var17 = new ArrayBuilder.ofRef(make_evidence$1);
                                       break label117;
                                    }

                                    var17 = new ArrayBuilder.ofUnit();
                                    break label117;
                                 }

                                 var17 = new ArrayBuilder.ofBoolean();
                                 break label117;
                              }

                              var17 = new ArrayBuilder.ofDouble();
                              break label117;
                           }

                           var17 = new ArrayBuilder.ofFloat();
                           break label117;
                        }

                        var17 = new ArrayBuilder.ofLong();
                        break label117;
                     }

                     var17 = new ArrayBuilder.ofInt();
                     break label117;
                  }

                  var17 = new ArrayBuilder.ofChar();
                  break label117;
               }

               var17 = new ArrayBuilder.ofShort();
               break label117;
            }

            var17 = new ArrayBuilder.ofByte();
         }

         Object var6 = null;
         Object var7 = null;
         ArrayBuilder b = (ArrayBuilder)var17;
         int k = this.self.knownSize();
         if (k > 0) {
            RichInt$ var10001 = RichInt$.MODULE$;
            int min$extension_that = this.size;
            scala.math.package$ var18 = scala.math.package$.MODULE$;
            b.sizeHint(Math.min(k, min$extension_that));
         }

         return b;
      }

      public GroupedIterator withPadding(final Function0 x) {
         this.padding = x;
         this.partial = true;
         return this;
      }

      public GroupedIterator withPartial(final boolean x) {
         this.partial = x;
         this.padding = null;
         return this;
      }

      private boolean fulfill() {
         ArrayBuilder builder = this.newBuilder();
         boolean done = false;
         if (this.prev != null) {
            builder.addAll(this.prev);
         }

         if (!this.first && this.step > this.size) {
            int dropping;
            for(dropping = this.step - this.size; dropping > 0 && this.self.hasNext(); --dropping) {
               this.self.next();
            }

            done = dropping > 0;
         }

         int index = builder.length();
         if (!done) {
            while(index < this.size && this.self.hasNext()) {
               builder.addOne(this.self.next());
               ++index;
            }

            if (index < this.size && this.pad()) {
               builder.sizeHint(this.size);

               while(index < this.size) {
                  builder.addOne(this.padding.apply());
                  ++index;
               }
            }
         }

         boolean ok = index > 0 && (this.partial || index == this.size);
         if (ok) {
            this.buffer = builder.result();
         } else {
            this.prev = null;
         }

         return ok;
      }

      private boolean fill() {
         if (!this.filled) {
            this.filled = this.self.hasNext() && this.fulfill();
            if (!this.filled) {
               return false;
            }
         }

         return true;
      }

      public boolean hasNext() {
         return this.fill();
      }

      public scala.collection.immutable.Seq next() throws NoSuchElementException {
         if (!this.fill()) {
            Iterator$ var10000 = Iterator$.MODULE$;
            return (scala.collection.immutable.Seq)Iterator$.scala$collection$Iterator$$_empty.next();
         } else {
            this.filled = false;
            if (this.step < this.size) {
               if (this.first) {
                  this.prev = ArrayOps$.MODULE$.drop$extension(this.buffer, this.step);
               } else if (Array.getLength(this.buffer) == this.size) {
                  Array$.MODULE$.copy(this.buffer, this.step, this.prev, 0, this.size - this.step);
               } else {
                  this.prev = null;
               }
            }

            ArraySeq res = ArraySeq$.MODULE$.unsafeWrapArray(this.buffer);
            this.buffer = null;
            this.first = false;
            return res;
         }
      }

      // $FF: synthetic method
      public Iterator scala$collection$Iterator$GroupedIterator$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final String $anonfun$new$1(final GroupedIterator $this) {
         return StringOps$.MODULE$.format$extension("size=%d and step=%d, but both must be positive", ScalaRunTime$.MODULE$.genericWrapArray(new Object[]{$this.size, $this.step}));
      }

      public GroupedIterator(final Iterator self, final int size, final int step) {
         this.self = self;
         this.size = size;
         this.step = step;
         if (Iterator.this == null) {
            throw null;
         } else {
            this.$outer = Iterator.this;
            super();
            if (size < 1 || step < 1) {
               throw new IllegalArgumentException((new StringBuilder(20)).append("requirement failed: ").append($anonfun$new$1(this)).toString());
            } else {
               this.buffer = null;
               this.prev = null;
               this.first = true;
               this.filled = false;
               this.partial = true;
               this.padding = null;
            }
         }
      }
   }

   private static final class ConcatIterator extends AbstractIterator {
      private Iterator current;
      private ConcatIteratorCell tail;
      private ConcatIteratorCell last;
      private boolean currentHasNextChecked;

      private Iterator current() {
         return this.current;
      }

      private void current_$eq(final Iterator x$1) {
         this.current = x$1;
      }

      private ConcatIteratorCell tail() {
         return this.tail;
      }

      private void tail_$eq(final ConcatIteratorCell x$1) {
         this.tail = x$1;
      }

      private ConcatIteratorCell last() {
         return this.last;
      }

      private void last_$eq(final ConcatIteratorCell x$1) {
         this.last = x$1;
      }

      private boolean currentHasNextChecked() {
         return this.currentHasNextChecked;
      }

      private void currentHasNextChecked_$eq(final boolean x$1) {
         this.currentHasNextChecked = x$1;
      }

      public boolean hasNext() {
         if (this.currentHasNextChecked()) {
            return true;
         } else if (this.current() == null) {
            return false;
         } else if (this.current().hasNext()) {
            this.currentHasNextChecked_$eq(true);
            return true;
         } else {
            return this.advance$1();
         }
      }

      public Object next() {
         if (this.hasNext()) {
            this.currentHasNextChecked_$eq(false);
            return this.current().next();
         } else {
            Iterator$ var10000 = Iterator$.MODULE$;
            return Iterator$.scala$collection$Iterator$$_empty.next();
         }
      }

      public Iterator concat(final Function0 that) {
         ConcatIteratorCell c = new ConcatIteratorCell(that, (ConcatIteratorCell)null);
         if (this.tail() == null) {
            this.tail_$eq(c);
            this.last_$eq(c);
         } else {
            this.last().tail_$eq(c);
            this.last_$eq(c);
         }

         if (this.current() == null) {
            Iterator$ var10001 = Iterator$.MODULE$;
            this.current_$eq(Iterator$.scala$collection$Iterator$$_empty);
         }

         return this;
      }

      private final void merge$1() {
         while(this.current() instanceof ConcatIterator) {
            ConcatIterator c = (ConcatIterator)this.current();
            this.current_$eq(c.current());
            this.currentHasNextChecked_$eq(c.currentHasNextChecked());
            if (c.tail() != null) {
               if (this.last() == null) {
                  this.last_$eq(c.last());
               }

               c.last().tail_$eq(this.tail());
               this.tail_$eq(c.tail());
            }
         }

      }

      private final boolean advance$1() {
         while(this.tail() != null) {
            this.current_$eq(this.tail().headIterator());
            if (this.last() == this.tail()) {
               this.last_$eq(this.last().tail());
            }

            this.tail_$eq(this.tail().tail());
            this.merge$1();
            if (this.currentHasNextChecked()) {
               return true;
            }

            if (this.current() != null && this.current().hasNext()) {
               this.currentHasNextChecked_$eq(true);
               return true;
            }
         }

         this.current_$eq((Iterator)null);
         this.last_$eq((ConcatIteratorCell)null);
         return false;
      }

      public ConcatIterator(final Iterator current) {
         this.current = current;
         super();
         this.tail = null;
         this.last = null;
         this.currentHasNextChecked = false;
      }
   }

   private static final class ConcatIteratorCell {
      private final Function0 head;
      private ConcatIteratorCell tail;

      public ConcatIteratorCell tail() {
         return this.tail;
      }

      public void tail_$eq(final ConcatIteratorCell x$1) {
         this.tail = x$1;
      }

      public Iterator headIterator() {
         return ((IterableOnce)this.head.apply()).iterator();
      }

      public ConcatIteratorCell(final Function0 head, final ConcatIteratorCell tail) {
         this.head = head;
         this.tail = tail;
         super();
      }
   }

   public static final class SliceIterator extends AbstractIterator {
      private final Iterator underlying;
      public int scala$collection$Iterator$SliceIterator$$remaining;
      private int dropping;

      public Iterator underlying() {
         return this.underlying;
      }

      private boolean unbounded() {
         return this.scala$collection$Iterator$SliceIterator$$remaining < 0;
      }

      private void skip() {
         while(this.dropping > 0) {
            if (this.underlying().hasNext()) {
               this.underlying().next();
               --this.dropping;
            } else {
               this.dropping = 0;
            }
         }

      }

      public int knownSize() {
         int size = this.underlying().knownSize();
         if (size < 0) {
            return -1;
         } else {
            RichInt$ var10000 = RichInt$.MODULE$;
            byte var3 = 0;
            int max$extension_that = size - this.dropping;
            scala.math.package$ var6 = scala.math.package$.MODULE$;
            int dropSize = Math.max(var3, max$extension_that);
            if (this.scala$collection$Iterator$SliceIterator$$remaining < 0) {
               return dropSize;
            } else {
               RichInt$ var7 = RichInt$.MODULE$;
               int var4 = this.scala$collection$Iterator$SliceIterator$$remaining;
               scala.math.package$ var8 = scala.math.package$.MODULE$;
               return Math.min(var4, dropSize);
            }
         }
      }

      public boolean hasNext() {
         this.skip();
         return this.scala$collection$Iterator$SliceIterator$$remaining != 0 && this.underlying().hasNext();
      }

      public Object next() {
         this.skip();
         if (this.scala$collection$Iterator$SliceIterator$$remaining > 0) {
            --this.scala$collection$Iterator$SliceIterator$$remaining;
            return this.underlying().next();
         } else if (this.scala$collection$Iterator$SliceIterator$$remaining < 0) {
            return this.underlying().next();
         } else {
            Iterator$ var10000 = Iterator$.MODULE$;
            return Iterator$.scala$collection$Iterator$$_empty.next();
         }
      }

      public Iterator sliceIterator(final int from, final int until) {
         RichInt$ var10000 = RichInt$.MODULE$;
         int max$extension_that = 0;
         scala.math.package$ var10 = scala.math.package$.MODULE$;
         int lo = Math.max(from, max$extension_that);
         int var11;
         if (until < 0) {
            var11 = this.adjustedBound$1(lo);
         } else if (until <= lo) {
            var11 = 0;
         } else if (this.scala$collection$Iterator$SliceIterator$$remaining < 0) {
            var11 = until - lo;
         } else {
            RichInt$ var12 = RichInt$.MODULE$;
            int var6 = this.adjustedBound$1(lo);
            int min$extension_that = until - lo;
            scala.math.package$ var13 = scala.math.package$.MODULE$;
            var11 = Math.min(var6, min$extension_that);
         }

         int rest = var11;
         int sum = this.dropping + lo;
         if (rest == 0) {
            Iterator$ var14 = Iterator$.MODULE$;
            return Iterator$.scala$collection$Iterator$$_empty;
         } else if (sum < 0) {
            this.dropping = Integer.MAX_VALUE;
            this.scala$collection$Iterator$SliceIterator$$remaining = 0;
            Function0 concat_xs = () -> new SliceIterator(this.underlying(), sum - Integer.MAX_VALUE, rest);
            return (new ConcatIterator(this)).concat(concat_xs);
         } else {
            this.dropping = sum;
            this.scala$collection$Iterator$SliceIterator$$remaining = rest;
            return this;
         }
      }

      private final int adjustedBound$1(final int lo$1) {
         if (this.scala$collection$Iterator$SliceIterator$$remaining < 0) {
            return -1;
         } else {
            RichInt$ var10000 = RichInt$.MODULE$;
            byte var2 = 0;
            int max$extension_that = this.scala$collection$Iterator$SliceIterator$$remaining - lo$1;
            scala.math.package$ var4 = scala.math.package$.MODULE$;
            return Math.max(var2, max$extension_that);
         }
      }

      public SliceIterator(final Iterator underlying, final int start, final int limit) {
         this.underlying = underlying;
         this.scala$collection$Iterator$SliceIterator$$remaining = limit;
         this.dropping = start;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private static final class UnfoldIterator extends AbstractIterator {
      private final Function1 f;
      private Object state;
      private Option nextResult;

      public boolean hasNext() {
         if (this.nextResult == null) {
            Option res = (Option)this.f.apply(this.state);
            if (res == null) {
               throw new NullPointerException("null during unfold");
            }

            this.nextResult = res;
            this.state = null;
         }

         return this.nextResult.isDefined();
      }

      public Object next() {
         if (this.hasNext()) {
            Tuple2 var1 = (Tuple2)this.nextResult.get();
            if (var1 != null) {
               Object value = var1._1();
               Object newState = var1._2();
               this.state = newState;
               this.nextResult = null;
               return value;
            } else {
               throw new MatchError((Object)null);
            }
         } else {
            Iterator$ var10000 = Iterator$.MODULE$;
            return Iterator$.scala$collection$Iterator$$_empty.next();
         }
      }

      public UnfoldIterator(final Object init, final Function1 f) {
         this.f = f;
         this.state = init;
         this.nextResult = null;
      }
   }
}

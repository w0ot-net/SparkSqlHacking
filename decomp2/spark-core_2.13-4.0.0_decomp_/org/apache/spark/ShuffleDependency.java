package org.apache.spark;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.rdd.RDD;
import org.apache.spark.serializer.Serializer;
import org.apache.spark.shuffle.ShuffleHandle;
import org.apache.spark.shuffle.ShuffleWriteProcessor;
import org.apache.spark.util.Utils$;
import org.roaringbitmap.RoaringBitmap;
import org.slf4j.Logger;
import scala.Function0;
import scala.None;
import scala.Option;
import scala.StringContext;
import scala.Predef.;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\r]a\u0001\u0002 @\u0001\u0019C\u0001B\u001a\u0001\u0003\u0006\u0004%Ia\u001a\u0005\te\u0002\u0011\t\u0011)A\u0005Q\"A\u0001\u0010\u0001BC\u0002\u0013\u0005\u0011\u0010\u0003\u0005~\u0001\t\u0005\t\u0015!\u0003{\u0011!q\bA!b\u0001\n\u0003y\bBCA\u0006\u0001\t\u0005\t\u0015!\u0003\u0002\u0002!Q\u0011Q\u0002\u0001\u0003\u0006\u0004%\t!a\u0004\t\u0015\u0005=\u0002A!A!\u0002\u0013\t\t\u0002\u0003\u0006\u00022\u0001\u0011)\u0019!C\u0001\u0003gA!\"a\u0011\u0001\u0005\u0003\u0005\u000b\u0011BA\u001b\u0011)\t)\u0005\u0001BC\u0002\u0013\u0005\u0011q\t\u0005\u000b\u0003\u001f\u0002!\u0011!Q\u0001\n\u0005%\u0003BCA)\u0001\t\u0015\r\u0011\"\u0001\u0002T!Q\u0011\u0011\r\u0001\u0003\u0002\u0003\u0006I!!\u0016\t\u0015\u0005\r\u0004AaA!\u0002\u0017\t)\u0007\u0003\u0006\u0002r\u0001\u0011\u0019\u0011)A\u0006\u0003gB!\"!\u001e\u0001\u0005\u0007\u0005\u000b1BA<\u0011\u001d\tI\b\u0001C\u0001\u0003wBa\u0001\u001c\u0001\u0005B\u0005u\u0005BCAQ\u0001\t\u0007I\u0011A \u0002$\"A\u0011Q\u0017\u0001!\u0002\u0013\t)\u000b\u0003\u0006\u00028\u0002\u0011\r\u0011\"\u0001@\u0003GC\u0001\"!/\u0001A\u0003%\u0011Q\u0015\u0005\u000b\u0003w\u0003!\u0019!C\u0001\u007f\u0005u\u0006\u0002CAa\u0001\u0001\u0006I!a0\t\u0013\u0005\r\u0007A1A\u0005\u0002\u0005\u0015\u0007\u0002CAg\u0001\u0001\u0006I!a2\t\u0013\u0005=\u0007A1A\u0005\u0002\u0005E\u0007\u0002CAm\u0001\u0001\u0006I!a5\t\u0011\u0005m\u0007\u0001)A\u0005\u0003\u000fD\u0001\"!8\u0001A\u0003&\u0011\u0011\n\u0005\t\u0003?\u0004A\u0011A \u0002b\"9\u0011Q\u001e\u0001\u0005\u0002\u0005\u001d\u0003bBAv\u0001\u0011\u0005\u0011q\t\u0005\u000b\u0003_\u0004\u0001\u0019!C\u0001\u007f\u0005E\bB\u0003B\u0003\u0001\u0001\u0007I\u0011A \u0003\b!A!Q\u0002\u0001!B\u0013\t\u0019\u0010\u0003\u0005\u0003\u0010\u0001\u0001\u000b\u0015BA%\u0011!\u0011\t\u0002\u0001Q!\n\u0005\u001d\u0007b\u0002B\n\u0001\u0011\u0005\u0011Q\u0019\u0005\b\u0005+\u0001A\u0011\u0001B\f\u0011\u001d\u0011Y\u0002\u0001C\u0001\u0003cD\u0001B!\b\u0001\t\u0003y$q\u0004\u0005\t\u0005C\u0001A\u0011A \u0002H!9!1\u0005\u0001\u0005\u0002\u0005\u001d\u0003b\u0002B\u0013\u0001\u0011\u0005!q\u0004\u0005\b\u0005O\u0001A\u0011\u0002B\u0015\u0011!\u0011Y\u0003\u0001Q\u0001\n\t5\u0002\u0002\u0003B\u001e\u0001\u0011\u0005qH!\u0010\t\u0011\t\r\u0003\u0001)Q\u0005\u0005\u000bB\u0001B!\u001a\u0001\t\u0003y$q\r\u0005\t\u0005k\u0002A\u0011A \u0003x\u001dI!QS \u0002\u0002#\u0005!q\u0013\u0004\t}}\n\t\u0011#\u0001\u0003\u001a\"9\u0011\u0011\u0010\u001c\u0005\u0002\t5\u0006\"\u0003BXmE\u0005I\u0011\u0001BY\u0011%\u0011iMNI\u0001\n\u0003\u0011y\rC\u0005\u0003bZ\n\n\u0011\"\u0001\u0003d\"I!1\u001e\u001c\u0012\u0002\u0013\u0005!Q\u001e\u0005\n\u0005s4\u0014\u0013!C\u0001\u0005wD\u0011ba\u00027\u0003\u0003%Ia!\u0003\u0003#MCWO\u001a4mK\u0012+\u0007/\u001a8eK:\u001c\u0017P\u0003\u0002A\u0003\u0006)1\u000f]1sW*\u0011!iQ\u0001\u0007CB\f7\r[3\u000b\u0003\u0011\u000b1a\u001c:h\u0007\u0001)Ra\u0012+_\u0003\u007f\u00192\u0001\u0001%a!\rI%\nT\u0007\u0002\u007f%\u00111j\u0010\u0002\u000b\t\u0016\u0004XM\u001c3f]\u000eL\b\u0003B'Q%vk\u0011A\u0014\u0006\u0002\u001f\u0006)1oY1mC&\u0011\u0011K\u0014\u0002\t!J|G-^2ueA\u00111\u000b\u0016\u0007\u0001\t\u0015)\u0006A1\u0001W\u0005\u0005Y\u0015CA,[!\ti\u0005,\u0003\u0002Z\u001d\n9aj\u001c;iS:<\u0007CA'\\\u0013\tafJA\u0002B]f\u0004\"a\u00150\u0005\u000b}\u0003!\u0019\u0001,\u0003\u0003Y\u0003\"!\u00193\u000e\u0003\tT!aY \u0002\u0011%tG/\u001a:oC2L!!\u001a2\u0003\u000f1{wmZ5oO\u0006!qL\u001d3e+\u0005A\u0007GA5q!\rQWn\\\u0007\u0002W*\u0011AnP\u0001\u0004e\u0012$\u0017B\u00018l\u0005\r\u0011F\t\u0012\t\u0003'B$\u0011\"\u001d\u0002\u0002\u0002\u0003\u0005)\u0011A<\u0003\u0007}#\u0013'A\u0003`e\u0012$\u0007\u0005\u000b\u0002\u0003iB\u0011Q*^\u0005\u0003m:\u0013\u0011\u0002\u001e:b]NLWM\u001c;\u0012\u0005]c\u0015a\u00039beRLG/[8oKJ,\u0012A\u001f\t\u0003\u0013nL!\u0001` \u0003\u0017A\u000b'\u000f^5uS>tWM]\u0001\ra\u0006\u0014H/\u001b;j_:,'\u000fI\u0001\u000bg\u0016\u0014\u0018.\u00197ju\u0016\u0014XCAA\u0001!\u0011\t\u0019!a\u0002\u000e\u0005\u0005\u0015!B\u0001@@\u0013\u0011\tI!!\u0002\u0003\u0015M+'/[1mSj,'/A\u0006tKJL\u0017\r\\5{KJ\u0004\u0013aC6fs>\u0013H-\u001a:j]\u001e,\"!!\u0005\u0011\u000b5\u000b\u0019\"a\u0006\n\u0007\u0005UaJ\u0001\u0004PaRLwN\u001c\t\u0006\u00033\tIC\u0015\b\u0005\u00037\t)C\u0004\u0003\u0002\u001e\u0005\rRBAA\u0010\u0015\r\t\t#R\u0001\u0007yI|w\u000e\u001e \n\u0003=K1!a\nO\u0003\u001d\u0001\u0018mY6bO\u0016LA!a\u000b\u0002.\tAqJ\u001d3fe&twMC\u0002\u0002(9\u000bAb[3z\u001fJ$WM]5oO\u0002\n!\"Y4he\u0016<\u0017\r^8s+\t\t)\u0004E\u0003N\u0003'\t9\u0004E\u0004J\u0003s\u0011V,!\u0010\n\u0007\u0005mrH\u0001\u0006BO\u001e\u0014XmZ1u_J\u00042aUA \t\u0019\t\t\u0005\u0001b\u0001-\n\t1)A\u0006bO\u001e\u0014XmZ1u_J\u0004\u0013AD7baNKG-Z\"p[\nLg.Z\u000b\u0003\u0003\u0013\u00022!TA&\u0013\r\tiE\u0014\u0002\b\u0005>|G.Z1o\u0003=i\u0017\r]*jI\u0016\u001cu.\u001c2j]\u0016\u0004\u0013AF:ik\u001a4G.Z,sSR,'\u000f\u0015:pG\u0016\u001c8o\u001c:\u0016\u0005\u0005U\u0003\u0003BA,\u0003;j!!!\u0017\u000b\u0007\u0005ms(A\u0004tQV4g\r\\3\n\t\u0005}\u0013\u0011\f\u0002\u0016'\",hM\u001a7f/JLG/\u001a)s_\u000e,7o]8s\u0003]\u0019\b.\u001e4gY\u0016<&/\u001b;feB\u0013xnY3tg>\u0014\b%\u0001\u0006fm&$WM\\2fIE\u0002R!a\u001a\u0002nIk!!!\u001b\u000b\u0007\u0005-d*A\u0004sK\u001adWm\u0019;\n\t\u0005=\u0014\u0011\u000e\u0002\t\u00072\f7o\u001d+bO\u0006QQM^5eK:\u001cW\r\n\u001a\u0011\u000b\u0005\u001d\u0014QN/\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$3\u0007\u0005\u0004\u0002h\u00055\u0014QH\u0001\u0007y%t\u0017\u000e\u001e \u0015!\u0005u\u0014qQAI\u0003'\u000b)*a&\u0002\u001a\u0006mE\u0003CA@\u0003\u0003\u000b\u0019)!\"\u0011\r%\u0003!+XA\u001f\u0011\u001d\t\u0019G\u0005a\u0002\u0003KBq!!\u001d\u0013\u0001\b\t\u0019\bC\u0004\u0002vI\u0001\u001d!a\u001e\t\r\u0019\u0014\u0002\u0019AAEa\u0011\tY)a$\u0011\t)l\u0017Q\u0012\t\u0004'\u0006=EAC9\u0002\b\u0006\u0005\t\u0011!B\u0001o\")\u0001P\u0005a\u0001u\"AaP\u0005I\u0001\u0002\u0004\t\t\u0001C\u0005\u0002\u000eI\u0001\n\u00111\u0001\u0002\u0012!I\u0011\u0011\u0007\n\u0011\u0002\u0003\u0007\u0011Q\u0007\u0005\n\u0003\u000b\u0012\u0002\u0013!a\u0001\u0003\u0013B\u0011\"!\u0015\u0013!\u0003\u0005\r!!\u0016\u0016\u0005\u0005}\u0005c\u00016n\u0019\u0006a1.Z=DY\u0006\u001c8OT1nKV\u0011\u0011Q\u0015\t\u0005\u0003O\u000byK\u0004\u0003\u0002*\u0006-\u0006cAA\u000f\u001d&\u0019\u0011Q\u0016(\u0002\rA\u0013X\rZ3g\u0013\u0011\t\t,a-\u0003\rM#(/\u001b8h\u0015\r\tiKT\u0001\u000eW\u0016L8\t\\1tg:\u000bW.\u001a\u0011\u0002\u001dY\fG.^3DY\u0006\u001c8OT1nK\u0006ya/\u00197vK\u000ec\u0017m]:OC6,\u0007%A\td_6\u0014\u0017N\\3s\u00072\f7o\u001d(b[\u0016,\"!a0\u0011\u000b5\u000b\u0019\"!*\u0002%\r|WNY5oKJ\u001cE.Y:t\u001d\u0006lW\rI\u0001\ng\",hM\u001a7f\u0013\u0012,\"!a2\u0011\u00075\u000bI-C\u0002\u0002L:\u00131!\u00138u\u0003)\u0019\b.\u001e4gY\u0016LE\rI\u0001\u000eg\",hM\u001a7f\u0011\u0006tG\r\\3\u0016\u0005\u0005M\u0007\u0003BA,\u0003+LA!a6\u0002Z\ti1\u000b[;gM2,\u0007*\u00198eY\u0016\fab\u001d5vM\u001adW\rS1oI2,\u0007%A\u0007ok6\u0004\u0016M\u001d;ji&|gn]\u0001\u0015?NDWO\u001a4mK6+'oZ3BY2|w/\u001a3\u0002-M,Go\u00155vM\u001adW-T3sO\u0016\fE\u000e\\8xK\u0012$B!a9\u0002jB\u0019Q*!:\n\u0007\u0005\u001dhJ\u0001\u0003V]&$\bbBAvA\u0001\u0007\u0011\u0011J\u0001\u0014g\",hM\u001a7f\u001b\u0016\u0014x-Z!mY><X\rZ\u0001\u0014g\",hM\u001a7f\u001b\u0016\u0014x-Z#oC\ndW\rZ\u0001\u000b[\u0016\u0014x-\u001a:M_\u000e\u001cXCAAz!\u0019\tI\"!>\u0002z&!\u0011q_A\u0017\u0005\r\u0019V-\u001d\t\u0005\u0003w\u0014\t!\u0004\u0002\u0002~*\u0019\u0011q` \u0002\u000fM$xN]1hK&!!1AA\u007f\u00059\u0011En\\2l\u001b\u0006t\u0017mZ3s\u0013\u0012\fa\"\\3sO\u0016\u0014Hj\\2t?\u0012*\u0017\u000f\u0006\u0003\u0002d\n%\u0001\"\u0003B\u0006I\u0005\u0005\t\u0019AAz\u0003\rAH%M\u0001\f[\u0016\u0014x-\u001a:M_\u000e\u001c\b%\u0001\f`g\",hM\u001a7f\u001b\u0016\u0014x-\u001a$j]\u0006d\u0017N_3e\u0003=y6\u000f[;gM2,W*\u001a:hK&#\u0017AD:ik\u001a4G.Z'fe\u001e,\u0017\nZ\u0001\u000eg\u0016$X*\u001a:hKJdunY:\u0015\t\u0005\r(\u0011\u0004\u0005\b\u0003_L\u0003\u0019AAz\u000359W\r^'fe\u001e,'\u000fT8dg\u0006IR.\u0019:l'\",hM\u001a7f\u001b\u0016\u0014x-\u001a$j]\u0006d\u0017N_3e)\t\t\u0019/A\u000fjgNCWO\u001a4mK6+'oZ3GS:\fG.\u001b>fI6\u000b'o[3e\u0003U\u0019\b.\u001e4gY\u0016lUM]4f\r&t\u0017\r\\5{K\u0012\fAC\\3x'\",hM\u001a7f\u001b\u0016\u0014x-Z*uCR,\u0017\u0001G2b]NCWO\u001a4mK6+'oZ3CK\u0016s\u0017M\u00197fIR\u0011\u0011\u0011J\u0001\u0015g\",hM\u001a7f!V\u001c\bnQ8na2,G/\u001a3\u0011\t\t=\"QG\u0007\u0003\u0005cQ1Aa\rD\u00035\u0011x.\u0019:j]\u001e\u0014\u0017\u000e^7ba&!!q\u0007B\u0019\u00055\u0011v.\u0019:j]\u001e\u0014\u0015\u000e^7ba\"\u0012\u0001\u0007^\u0001\u0011S:\u001c\u0007+^:i\u0007>l\u0007\u000f\\3uK\u0012$B!a2\u0003@!9!\u0011I\u0019A\u0002\u0005\u001d\u0017\u0001C7ba&sG-\u001a=\u0002\u0019\u0019Lg.\u00197ju\u0016$\u0016m]6\u0011\u000b5\u000b\u0019Ba\u00121\t\t%#q\f\t\u0007\u0005\u0017\u0012IF!\u0018\u000e\u0005\t5#\u0002\u0002B(\u0005#\n!bY8oGV\u0014(/\u001a8u\u0015\u0011\u0011\u0019F!\u0016\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u0005/\nAA[1wC&!!1\fB'\u0005=\u00196\r[3ek2,GMR;ukJ,\u0007cA*\u0003`\u0011Q!\u0011\r\u001a\u0002\u0002\u0003\u0005)\u0011\u0001,\u0003\u0007}##\u0007\u000b\u00023i\u0006yq-\u001a;GS:\fG.\u001b>f)\u0006\u001c8.\u0006\u0002\u0003jA)Q*a\u0005\u0003lA\"!Q\u000eB9!\u0019\u0011YE!\u0017\u0003pA\u00191K!\u001d\u0005\u0015\tM4'!A\u0001\u0002\u000b\u0005aKA\u0002`IM\nqb]3u\r&t\u0017\r\\5{KR\u000b7o\u001b\u000b\u0005\u0003G\u0014I\bC\u0004\u0003|Q\u0002\rA! \u0002\tQ\f7o\u001b\u0019\u0005\u0005\u007f\u0012\u0019\t\u0005\u0004\u0003L\te#\u0011\u0011\t\u0004'\n\rEa\u0003BC\u0005s\n\t\u0011!A\u0003\u0002Y\u00131a\u0018\u00135Q\r\u0001!\u0011\u0012\t\u0005\u0005\u0017\u0013\t*\u0004\u0002\u0003\u000e*\u0019!qR \u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0003\u0014\n5%\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0017!E*ik\u001a4G.\u001a#fa\u0016tG-\u001a8dsB\u0011\u0011JN\n\u0006m\tm%\u0011\u0015\t\u0004\u001b\nu\u0015b\u0001BP\u001d\n1\u0011I\\=SK\u001a\u0004BAa)\u0003*6\u0011!Q\u0015\u0006\u0005\u0005O\u0013)&\u0001\u0002j_&!!1\u0016BS\u00051\u0019VM]5bY&T\u0018M\u00197f)\t\u00119*A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HeM\u000b\t\u0005g\u00139M!3\u0003LV\u0011!Q\u0017\u0016\u0005\u0003\u0003\u00119l\u000b\u0002\u0003:B!!1\u0018Bb\u001b\t\u0011iL\u0003\u0003\u0003@\n\u0005\u0017!C;oG\",7m[3e\u0015\r\u0011yIT\u0005\u0005\u0005\u000b\u0014iLA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016$Q!\u0016\u001dC\u0002Y#Qa\u0018\u001dC\u0002Y#a!!\u00119\u0005\u00041\u0016a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$C'\u0006\u0005\u0003R\nm'Q\u001cBp+\t\u0011\u0019N\u000b\u0003\u0003V\n]fbA'\u0003X&\u0019!\u0011\u001c(\u0002\t9{g.\u001a\u0003\u0006+f\u0012\rA\u0016\u0003\u0006?f\u0012\rA\u0016\u0003\u0007\u0003\u0003J$\u0019\u0001,\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00136+!\u0011\tN!:\u0003h\n%H!B+;\u0005\u00041F!B0;\u0005\u00041FABA!u\t\u0007a+A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEN\u000b\t\u0005_\u0014\u0019P!>\u0003xV\u0011!\u0011\u001f\u0016\u0005\u0003\u0013\u00129\fB\u0003Vw\t\u0007a\u000bB\u0003`w\t\u0007a\u000b\u0002\u0004\u0002Bm\u0012\rAV\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001c\u0016\u0011\tu8\u0011AB\u0002\u0007\u000b)\"Aa@+\t\u0005U#q\u0017\u0003\u0006+r\u0012\rA\u0016\u0003\u0006?r\u0012\rA\u0016\u0003\u0007\u0003\u0003b$\u0019\u0001,\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\r-\u0001\u0003BB\u0007\u0007'i!aa\u0004\u000b\t\rE!QK\u0001\u0005Y\u0006tw-\u0003\u0003\u0004\u0016\r=!AB(cU\u0016\u001cG\u000f"
)
public class ShuffleDependency extends Dependency implements Logging {
   private final transient RDD _rdd;
   private final Partitioner partitioner;
   private final Serializer serializer;
   private final Option keyOrdering;
   private final Option aggregator;
   private final boolean mapSideCombine;
   private final ShuffleWriteProcessor shuffleWriterProcessor;
   private final String keyClassName;
   private final String valueClassName;
   private final Option combinerClassName;
   private final int shuffleId;
   private final ShuffleHandle shuffleHandle;
   private final int numPartitions;
   private boolean _shuffleMergeAllowed;
   private Seq mergerLocs;
   private boolean _shuffleMergeFinalized;
   private int _shuffleMergeId;
   private final transient RoaringBitmap shufflePushCompleted;
   private transient Option finalizeTask;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static ShuffleWriteProcessor $lessinit$greater$default$7() {
      return ShuffleDependency$.MODULE$.$lessinit$greater$default$7();
   }

   public static boolean $lessinit$greater$default$6() {
      return ShuffleDependency$.MODULE$.$lessinit$greater$default$6();
   }

   public static None $lessinit$greater$default$5() {
      return ShuffleDependency$.MODULE$.$lessinit$greater$default$5();
   }

   public static None $lessinit$greater$default$4() {
      return ShuffleDependency$.MODULE$.$lessinit$greater$default$4();
   }

   public static Serializer $lessinit$greater$default$3() {
      return ShuffleDependency$.MODULE$.$lessinit$greater$default$3();
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private RDD _rdd() {
      return this._rdd;
   }

   public Partitioner partitioner() {
      return this.partitioner;
   }

   public Serializer serializer() {
      return this.serializer;
   }

   public Option keyOrdering() {
      return this.keyOrdering;
   }

   public Option aggregator() {
      return this.aggregator;
   }

   public boolean mapSideCombine() {
      return this.mapSideCombine;
   }

   public ShuffleWriteProcessor shuffleWriterProcessor() {
      return this.shuffleWriterProcessor;
   }

   public RDD rdd() {
      return this._rdd();
   }

   public String keyClassName() {
      return this.keyClassName;
   }

   public String valueClassName() {
      return this.valueClassName;
   }

   public Option combinerClassName() {
      return this.combinerClassName;
   }

   public int shuffleId() {
      return this.shuffleId;
   }

   public ShuffleHandle shuffleHandle() {
      return this.shuffleHandle;
   }

   public void setShuffleMergeAllowed(final boolean shuffleMergeAllowed) {
      this._shuffleMergeAllowed = shuffleMergeAllowed;
   }

   public boolean shuffleMergeEnabled() {
      return this.shuffleMergeAllowed() && this.mergerLocs().nonEmpty();
   }

   public boolean shuffleMergeAllowed() {
      return this._shuffleMergeAllowed;
   }

   public Seq mergerLocs() {
      return this.mergerLocs;
   }

   public void mergerLocs_$eq(final Seq x$1) {
      this.mergerLocs = x$1;
   }

   public int shuffleMergeId() {
      return this._shuffleMergeId;
   }

   public void setMergerLocs(final Seq mergerLocs) {
      .MODULE$.assert(this.shuffleMergeAllowed());
      this.mergerLocs_$eq(mergerLocs);
   }

   public Seq getMergerLocs() {
      return this.mergerLocs();
   }

   public void markShuffleMergeFinalized() {
      this._shuffleMergeFinalized = true;
   }

   public boolean isShuffleMergeFinalizedMarked() {
      return this._shuffleMergeFinalized;
   }

   public boolean shuffleMergeFinalized() {
      return this.shuffleMergeEnabled() ? this.isShuffleMergeFinalizedMarked() : true;
   }

   public void newShuffleMergeState() {
      this._shuffleMergeFinalized = false;
      this.mergerLocs_$eq(scala.collection.immutable.Nil..MODULE$);
      ++this._shuffleMergeId;
      this.finalizeTask = scala.None..MODULE$;
      this.shufflePushCompleted.clear();
   }

   private boolean canShuffleMergeBeEnabled() {
      boolean isPushShuffleEnabled = Utils$.MODULE$.isPushBasedShuffleEnabled(this.rdd().sparkContext().conf(), true, Utils$.MODULE$.isPushBasedShuffleEnabled$default$3());
      if (isPushShuffleEnabled && this.rdd().isBarrier()) {
         this.logWarning((Function0)(() -> "Push-based shuffle is currently not supported for barrier stages"));
      }

      return isPushShuffleEnabled && this.numPartitions > 0 && !this.rdd().isBarrier();
   }

   public int incPushCompleted(final int mapIndex) {
      this.shufflePushCompleted.add(mapIndex);
      return this.shufflePushCompleted.getCardinality();
   }

   public Option getFinalizeTask() {
      return this.finalizeTask;
   }

   public void setFinalizeTask(final ScheduledFuture task) {
      this.finalizeTask = scala.Option..MODULE$.apply(task);
   }

   // $FF: synthetic method
   public static final void $anonfun$new$3(final ShuffleDependency $this, final ContextCleaner x$2) {
      x$2.registerShuffleForCleanup($this);
   }

   public ShuffleDependency(final RDD _rdd, final Partitioner partitioner, final Serializer serializer, final Option keyOrdering, final Option aggregator, final boolean mapSideCombine, final ShuffleWriteProcessor shuffleWriterProcessor, final ClassTag evidence$1, final ClassTag evidence$2, final ClassTag evidence$3) {
      this._rdd = _rdd;
      this.partitioner = partitioner;
      this.serializer = serializer;
      this.keyOrdering = keyOrdering;
      this.aggregator = aggregator;
      this.mapSideCombine = mapSideCombine;
      this.shuffleWriterProcessor = shuffleWriterProcessor;
      Logging.$init$(this);
      if (mapSideCombine) {
         .MODULE$.require(aggregator.isDefined(), () -> "Map-side combine without Aggregator specified!");
      }

      this.keyClassName = scala.reflect.package..MODULE$.classTag(evidence$1).runtimeClass().getName();
      this.valueClassName = scala.reflect.package..MODULE$.classTag(evidence$2).runtimeClass().getName();
      this.combinerClassName = scala.Option..MODULE$.apply(scala.reflect.package..MODULE$.classTag(evidence$3)).map((x$1) -> x$1.runtimeClass().getName());
      this.shuffleId = _rdd.context().newShuffleId();
      this.shuffleHandle = _rdd.context().env().shuffleManager().registerShuffle(this.shuffleId(), this);
      this.numPartitions = this.rdd().partitions().length;
      this._shuffleMergeAllowed = this.canShuffleMergeBeEnabled();
      this.mergerLocs = scala.collection.immutable.Nil..MODULE$;
      this._shuffleMergeFinalized = false;
      this._shuffleMergeId = 0;
      this.shufflePushCompleted = new RoaringBitmap();
      this.finalizeTask = scala.None..MODULE$;
      if ((long)this.numPartitions * (long)partitioner.numPartitions() > 1073741824L) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"The number of shuffle blocks "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_PARTITIONS..MODULE$, BoxesRunTime.boxToLong((long)this.numPartitions * (long)this.partitioner().numPartitions()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" for shuffleId ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, BoxesRunTime.boxToInteger(this.shuffleId()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"for ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RDD_DESCRIPTION..MODULE$, this._rdd())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"with ", " partitions"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_PARTITIONS2..MODULE$, BoxesRunTime.boxToInteger(this.numPartitions))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" is possibly too large, which could cause the driver to crash with an out-of-memory"})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" error. Consider decreasing the number of partitions in this shuffle stage."})))).log(scala.collection.immutable.Nil..MODULE$))));
      }

      _rdd.sparkContext().cleaner().foreach((x$2) -> {
         $anonfun$new$3(this, x$2);
         return BoxedUnit.UNIT;
      });
      _rdd.sparkContext().shuffleDriverComponents().registerShuffle(this.shuffleId());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

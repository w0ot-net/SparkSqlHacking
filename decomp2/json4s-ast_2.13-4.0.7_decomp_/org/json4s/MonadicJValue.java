package org.json4s;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.collection.LinearSeqOps;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.util.matching.Regex;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011Uu!\u00020`\u0011\u0003!g!\u00024`\u0011\u00039\u0007\"\u00028\u0002\t\u0003y\u0007\"\u00029\u0002\t\u0007\txa\u0002Bq\u0003!%!1\u001d\u0004\b\u0005K\f\u0001\u0012\u0002Bt\u0011\u0019qW\u0001\"\u0001\u0003j\"A!1^\u0003!\u0002\u0013\u0011i\u000fC\u0004\u0003~\u0016!\tAa@\b\u000f\r5\u0011\u0001#\u0003\u0004\u0010\u001991\u0011C\u0001\t\n\rM\u0001B\u00028\u000b\t\u0003\u0019)\u0002\u0003\u0005\u0003l*\u0001\u000b\u0011\u0002Bw\u0011\u001d\u0011iP\u0003C\u0001\u0007/1aAa\u0013\u0002\u0005\t5\u0003\"\u0003B(\u001d\t\u0005\t\u0015!\u0003z\u0011)\t)E\u0004B\u0001B\u0003%\u0011q\t\u0005\u0007]:!\tA!\u0015\t\u000f\u00055h\u0002\"\u0001\u0003\\!9!1\u000e\b\u0005\u0002\t5\u0004b\u0002B>\u001d\u0011\u0005!Q\u0010\u0005\b\u0005\u0007rA\u0011\u0001BE\u0011\u001d\u0019i\"\u0001C\u0003\u0007?Aqa!\u000b\u0002\t\u000b\u0019Y\u0003C\u0004\u00046\u0005!)aa\u000e\t\u000f\r\u0005\u0013\u0001\"\u0002\u0004D!91QD\u0001\u0005\u0006\r-\u0003bBB!\u0003\u0011\u00151q\f\u0005\b\u0007g\nAQAB;\u0011\u001d\u0019I)\u0001C\u0003\u0007\u0017Cqaa(\u0002\t\u000b\u0019\t\u000bC\u0004\u00046\u0006!)aa.\t\u000f\r}\u0016\u0001\"\u0002\u0004B\"91\u0011Z\u0001\u0005\u0006\r-\u0007bBBj\u0003\u0011\u00151Q\u001b\u0005\b\u0007;\fAQABp\u0011\u001d\u0019I/\u0001C\u0003\u0007WDqaa=\u0002\t\u000b\u0019)\u0010C\u0004\u0004~\u0006!)aa@\t\u000f\u0011\u001d\u0011\u0001\"\u0002\u0005\n!9A\u0011C\u0001\u0005\u0006\u0011M\u0001b\u0002C\u000e\u0003\u0011\u0015AQ\u0004\u0005\b\tK\tAQ\u0001C\u0014\u0011\u001d!y#\u0001C\u0003\tcAq\u0001\"\u000f\u0002\t\u000b!Y\u0004C\u0004\u0005D\u0005!)\u0001\"\u0012\t\u000f\u00115\u0013\u0001\"\u0002\u0005P!9AqK\u0001\u0005\u0006\u0011e\u0003b\u0002C/\u0003\u0011\u0015Aq\f\u0005\b\tG\nAQ\u0001C3\u0011\u001d!I'\u0001C\u0003\tWBq\u0001b\u001c\u0002\t\u000b!\t\bC\u0004\u0005v\u0005!)\u0001b\u001e\t\u000f\u0011}\u0014\u0001\"\u0002\u0005\u0002\"IAQQ\u0001\u0002\u0002\u0013\u0015Aq\u0011\u0005\n\t\u0017\u000b\u0011\u0011!C\u0003\t\u001b3AAZ0\u0003g\"aq\u000f\u000fC\u0001\u0002\u000b\u0015)\u0019!C\u0005q\"IA\u0010\u000fB\u0003\u0002\u0003\u0006I!\u001f\u0005\u0006]b\"\t! \u0005\b\u0003\u0003AD\u0011AA\u0002\u0011!\ty\u0002\u000fQ\u0005\n\u0005\u0005\u0002\u0002CA\u001fq\u0001&I!a\u0010\t\u000f\u0005M\u0003\b\"\u0001\u0002V!9\u0011\u0011\u0001\u001d\u0005\u0002\u0005e\u0003bBA*q\u0011\u0005\u0011q\u0010\u0005\b\u0003\u001fCD\u0011BAI\u0011\u001d\t\u0019\u000b\u000fC\u0001\u0003KCq!!29\t\u0003\t9\rC\u0004\u0002nb\"\t!a<\t\u000f\u0005U\b\b\"\u0001\u0002x\"9\u0011Q \u001d\u0005\u0002\u0005}\bb\u0002B\u0005q\u0011\u0005!1\u0002\u0005\b\u0005#AD\u0011\u0001B\n\u0011\u001d\u0011y\u0002\u000fC\u0001\u0005CAqA!\f9\t\u0003\u0011y\u0003C\u0004\u00036a\"\tAa\u000e\t\u000f\tu\u0002\b\"\u0001\u0003@!9!1\t\u001d\u0005\u0002\t\u0015\u0003b\u0002BIq\u0011\u0005!1\u0013\u0005\b\u0005/CD\u0011\u0001BM\u0011!\u0011i\n\u000fQ\u0005\n\t}\u0005\u0002\u0003BSq\u0001&IAa*\t\u0011\t-\u0006\b)C\u0005\u0005[C\u0001B!-9A\u0013%!1\u0017\u0005\u0007\u0005oCD\u0011\u0001=\t\r\te\u0006\b\"\u0001y\u0011\u0019\u0011Y\f\u000fC\u0001q\"1!Q\u0018\u001d\u0005\u0002aDaAa09\t\u0003A\b\u0002\u0003Baq\u0001&IAa1\t\r\t-\u0007\b\"\u0001y\u0011%\u0011i\rOA\u0001\n\u0003\u0012y\rC\u0005\u0003Xb\n\t\u0011\"\u0011\u0003Z\u0006iQj\u001c8bI&\u001c'JV1mk\u0016T!\u0001Y1\u0002\r)\u001cxN\u001c\u001bt\u0015\u0005\u0011\u0017aA8sO\u000e\u0001\u0001CA3\u0002\u001b\u0005y&!D'p]\u0006$\u0017n\u0019&WC2,Xm\u0005\u0002\u0002QB\u0011\u0011\u000e\\\u0007\u0002U*\t1.A\u0003tG\u0006d\u0017-\u0003\u0002nU\n1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#\u00013\u0002\u001f)4\u0018\r\\;f)>luN\\1eS\u000e$2A\u001dBp!\t)\u0007h\u0005\u00029iB\u0011\u0011.^\u0005\u0003m*\u0014a!\u00118z-\u0006d\u0017\u0001H8sO\u0012R7o\u001c85g\u0012juN\\1eS\u000eTe+\u00197vK\u0012\"#N^\u000b\u0002sB\u0011QM_\u0005\u0003w~\u0013aA\u0013,bYV,\u0017!H8sO\u0012R7o\u001c85g\u0012juN\\1eS\u000eTe+\u00197vK\u0012\"#N\u001e\u0011\u0015\u0005It\b\"B@<\u0001\u0004I\u0018A\u00016w\u0003\u001d!#m\u001d7bg\"$2!_A\u0003\u0011\u001d\t9\u0001\u0010a\u0001\u0003\u0013\t!B\\1nKR{g)\u001b8e!\u0011\tY!!\u0007\u000f\t\u00055\u0011Q\u0003\t\u0004\u0003\u001fQWBAA\t\u0015\r\t\u0019bY\u0001\u0007yI|w\u000e\u001e \n\u0007\u0005]!.\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u00037\tiB\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003/Q\u0017\u0001\u00054j]\u0012$\u0015N]3di\nKh*Y7f)\u0019\t\u0019#!\u000e\u0002:A)\u0011QEA\u0018s:!\u0011qEA\u0016\u001d\u0011\ty!!\u000b\n\u0003-L1!!\fk\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\r\u00024\t!A*[:u\u0015\r\tiC\u001b\u0005\b\u0003oi\u0004\u0019AA\u0012\u0003\tA8\u000fC\u0004\u0002<u\u0002\r!!\u0003\u0002\t9\fW.Z\u0001\u000bM&tG\rR5sK\u000e$HCBA\u0012\u0003\u0003\n\u0019\u0005C\u0004\u00028y\u0002\r!a\t\t\u000f\u0005\u0015c\b1\u0001\u0002H\u0005\t\u0001\u000f\u0005\u0004j\u0003\u0013J\u0018QJ\u0005\u0004\u0003\u0017R'!\u0003$v]\u000e$\u0018n\u001c82!\rI\u0017qJ\u0005\u0004\u0003#R'a\u0002\"p_2,\u0017M\\\u0001\u000fI\t\u001cH.Y:iI\t\u001cH.Y:i)\rI\u0018q\u000b\u0005\b\u0003\u000fy\u0004\u0019AA\u0005+\u0011\tY&!\u001a\u0015\t\u0005u\u0013Q\u000f\t\u0007\u0003K\ty#a\u0018\u0011\t\u0005\u0005\u0014\u0011\u000f\t\u0005\u0003G\n)\u0007\u0004\u0001\u0005\u000f\u0005\u001d\u0004I1\u0001\u0002j\t\t\u0011)E\u0002\u0002le\u00042![A7\u0013\r\tyG\u001b\u0002\b\u001d>$\b.\u001b8h\u0013\r\t\u0019H\u001f\u0002\u0007-\u0006dW/Z:\t\u000f\u0005]\u0004\t1\u0001\u0002z\u0005)1\r\\1{uB1\u00111BA>\u0003CJA!! \u0002\u001e\t)1\t\\1tgV!\u0011\u0011QAE)\u0011\t\u0019)a#\u0011\r\u0005\u0015\u0012qFAC!\u0011\t9)!\u001d\u0011\t\u0005\r\u0014\u0011\u0012\u0003\b\u0003O\n%\u0019AA5\u0011\u001d\t9(\u0011a\u0001\u0003\u001b\u0003b!a\u0003\u0002|\u0005\u001d\u0015!\u0004;za\u0016\u0004&/\u001a3jG\u0006$X-\u0006\u0003\u0002\u0014\u0006\u0005F\u0003BAK\u00037#B!!\u0014\u0002\u0018\"1\u0011\u0011\u0014\"A\u0002e\fAA[:p]\"9\u0011q\u000f\"A\u0002\u0005u\u0005CBA\u0006\u0003w\ny\n\u0005\u0003\u0002d\u0005\u0005FaBA4\u0005\n\u0007\u0011\u0011N\u0001\u0005M>dG-\u0006\u0003\u0002(\u00065F\u0003BAU\u0003\u0003$B!a+\u00028B!\u00111MAW\t\u001d\t9g\u0011b\u0001\u0003_\u000bB!a\u001b\u00022B\u0019\u0011.a-\n\u0007\u0005U&NA\u0002B]fDq!!/D\u0001\u0004\tY,A\u0001g!!I\u0017QXAVs\u0006-\u0016bAA`U\nIa)\u001e8di&|gN\r\u0005\b\u0003\u0007\u001c\u0005\u0019AAV\u0003\u0005Q\u0018!\u00034pY\u00124\u0015.\u001a7e+\u0011\tI-a4\u0015\t\u0005-\u00171\u001e\u000b\u0005\u0003\u001b\f\t\u000e\u0005\u0003\u0002d\u0005=GaBA4\t\n\u0007\u0011q\u0016\u0005\b\u0003s#\u0005\u0019AAj!%I\u0017QXAg\u0003+\fi\r\u0005\u0003\u0002X\u0006\u0015h\u0002BAm\u0003CtA!a7\u0002`:!\u0011qBAo\u0013\u0005\u0011\u0017B\u00011b\u0013\r\t\u0019oX\u0001\b\u0015N|g.Q*U\u0013\u0011\t9/!;\u0003\r)3\u0015.\u001a7e\u0015\r\t\u0019o\u0018\u0005\b\u0003\u0007$\u0005\u0019AAg\u0003\ri\u0017\r\u001d\u000b\u0004s\u0006E\bbBA]\u000b\u0002\u0007\u00111\u001f\t\u0006S\u0006%\u00130_\u0001\t[\u0006\u0004h)[3mIR\u0019\u00110!?\t\u000f\u0005ef\t1\u0001\u0002|B9\u0011.!\u0013\u0002V\u0006U\u0017A\u0004;sC:\u001chm\u001c:n\r&,G\u000e\u001a\u000b\u0004s\n\u0005\u0001bBA]\u000f\u0002\u0007!1\u0001\t\bS\n\u0015\u0011Q[Ak\u0013\r\u00119A\u001b\u0002\u0010!\u0006\u0014H/[1m\rVt7\r^5p]\u0006IAO]1og\u001a|'/\u001c\u000b\u0004s\n5\u0001bBA]\u0011\u0002\u0007!q\u0002\t\u0006S\n\u0015\u00110_\u0001\be\u0016\u0004H.Y2f)\u0015I(Q\u0003B\u000e\u0011\u001d\u00119\"\u0013a\u0001\u00053\t\u0011\u0001\u001c\t\u0007\u0003K\ty#!\u0003\t\r\tu\u0011\n1\u0001z\u0003-\u0011X\r\u001d7bG\u0016lWM\u001c;\u0002\u0013\u0019Lg\u000e\u001a$jK2$G\u0003\u0002B\u0012\u0005S\u0001R!\u001bB\u0013\u0003+L1Aa\nk\u0005\u0019y\u0005\u000f^5p]\"9\u0011Q\t&A\u0002\t-\u0002cB5\u0002J\u0005U\u0017QJ\u0001\u0005M&tG\r\u0006\u0003\u00032\tM\u0002\u0003B5\u0003&eDq!!\u0012L\u0001\u0004\t9%A\u0006gS2$XM\u001d$jK2$G\u0003\u0002B\u001d\u0005w\u0001b!!\n\u00020\u0005U\u0007bBA#\u0019\u0002\u0007!1F\u0001\u0007M&dG/\u001a:\u0015\t\u0005\r\"\u0011\t\u0005\b\u0003\u000bj\u0005\u0019AA$\u0003)9\u0018\u000e\u001e5GS2$XM\u001d\u000b\u0005\u0005\u000f\u0012y\tE\u0002\u0003J9q!!\u001a\u0001\u0003!)3\u0016\r\\;f/&$\bNR5mi\u0016\u00148C\u0001\bi\u0003\u0011\u0019X\r\u001c4\u0015\r\tM#q\u000bB-!\r\u0011)FD\u0007\u0002\u0003!1!qJ\tA\u0002eDq!!\u0012\u0012\u0001\u0004\t9%\u0006\u0003\u0003^\t\rD\u0003\u0002B0\u0005O\u0002b!!\n\u00020\t\u0005\u0004\u0003BA2\u0005G\"qA!\u001a\u0013\u0005\u0004\tyKA\u0001U\u0011\u001d\tIL\u0005a\u0001\u0005S\u0002b![A%s\n\u0005\u0014a\u00024mCRl\u0015\r]\u000b\u0005\u0005_\u0012)\b\u0006\u0003\u0003r\t]\u0004CBA\u0013\u0003_\u0011\u0019\b\u0005\u0003\u0002d\tUDa\u0002B3'\t\u0007\u0011q\u0016\u0005\b\u0003s\u001b\u0002\u0019\u0001B=!\u0019I\u0017\u0011J=\u0003r\u00059am\u001c:fC\u000eDG\u0003\u0002B@\u0005\u000b\u00032!\u001bBA\u0013\r\u0011\u0019I\u001b\u0002\u0005+:LG\u000fC\u0004\u0002:R\u0001\rAa\"\u0011\r%\fI%\u001fB@)\u0011\u0011\u0019Fa#\t\u000f\t5U\u00031\u0001\u0002H\u0005\t\u0011\u000fC\u0004\u0002F9\u0003\r!a\u0012\u0002\u0017I,Wn\u001c<f\r&,G\u000e\u001a\u000b\u0004s\nU\u0005bBA#\u001f\u0002\u0007!1F\u0001\u0007e\u0016lwN^3\u0015\u0007e\u0014Y\nC\u0004\u0002FA\u0003\r!a\u0012\u0002\u0011\r\fW.\u001a7ju\u0016$B!!\u0003\u0003\"\"9!1U)A\u0002\u0005%\u0011\u0001B<pe\u0012\f\u0011\u0002]1tG\u0006d\u0017N_3\u0015\t\u0005%!\u0011\u0016\u0005\b\u0005G\u0013\u0006\u0019AA\u0005\u0003a)h\u000eZ3sg\u000e|'/Z\"b[\u0016d7)Y:fg>sG.\u001f\u000b\u0005\u0003\u0013\u0011y\u000bC\u0004\u0003$N\u0003\r!!\u0003\u0002\u0015UtG-\u001a:tG>\u0014X\r\u0006\u0003\u0002\n\tU\u0006b\u0002BR)\u0002\u0007\u0011\u0011B\u0001\rG\u0006lW\r\\5{K.+\u0017p]\u0001\u000ea\u0006\u001c8-\u00197ju\u0016\\U-_:\u0002\u0017Mt\u0017m[5{K.+\u0017p]\u0001\u001ck:$WM]:d_J,7)Y7fY\u000e\u000b7/Z&fsN|e\u000e\\=\u0002\u001dUtG-\u001a:tG>\u0014XmS3zg\u0006q!/Z<sSR,'j]8o\u0003N#FcA=\u0003F\"9!q\u0019.A\u0002\t%\u0017\u0001E6fs\u000e\u000b7/\u001a+sC:\u001chm\u001c:n!\u001dI\u0017\u0011JA\u0005\u0003\u0013\tqA\\8Ok2d7/\u0001\u0005iCND7i\u001c3f)\t\u0011\t\u000eE\u0002j\u0005'L1A!6k\u0005\rIe\u000e^\u0001\u0007KF,\u0018\r\\:\u0015\t\u00055#1\u001c\u0005\n\u0005;l\u0016\u0011!a\u0001\u0003c\u000b1\u0001\u001f\u00132\u0011\u0015y8\u00011\u0001z\u0003)\t%O]1z\u0013:$W\r\u001f\t\u0004\u0005+*!AC!se\u0006L\u0018J\u001c3fqN\u0011Q\u0001\u001b\u000b\u0003\u0005G\f\u0011A\u0015\t\u0005\u0005_\u0014I0\u0004\u0002\u0003r*!!1\u001fB{\u0003!i\u0017\r^2iS:<'b\u0001B|U\u0006!Q\u000f^5m\u0013\u0011\u0011YP!=\u0003\u000bI+w-\u001a=\u0002\u000fUt\u0017\r\u001d9msR!1\u0011AB\u0005!\u0015I'QEB\u0002!\u001dI7QAA\u0005\u0005#L1aa\u0002k\u0005\u0019!V\u000f\u001d7fe!911\u0002\u0005A\u0002\u0005%\u0011aA:ue\u0006I\u0011I\u001d:bs\u0016\u000b7\r\u001b\t\u0004\u0005+R!!C!se\u0006LX)Y2i'\tQ\u0001\u000e\u0006\u0002\u0004\u0010Q!1\u0011DB\u000e!\u0015I'QEA\u0005\u0011\u001d\u0019Y!\u0004a\u0001\u0003\u0013\t\u0011\u0003\n2tY\u0006\u001c\b\u000eJ3yi\u0016t7/[8o)\u0011\u0019\tc!\n\u0015\u0007e\u001c\u0019\u0003C\u0004\u0002\bY\u0001\r!!\u0003\t\r\r\u001db\u00031\u0001s\u0003\u0015!C\u000f[5t\u0003i1\u0017N\u001c3ESJ,7\r\u001e\"z\u001d\u0006lW\rJ3yi\u0016t7/[8o)\u0011\u0019ica\r\u0015\r\u0005\r2qFB\u0019\u0011\u001d\t9d\u0006a\u0001\u0003GAq!a\u000f\u0018\u0001\u0004\tI\u0001\u0003\u0004\u0004(]\u0001\rA]\u0001\u0015M&tG\rR5sK\u000e$H%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\re2q\b\u000b\u0007\u0003G\u0019Yd!\u0010\t\u000f\u0005]\u0002\u00041\u0001\u0002$!9\u0011Q\t\rA\u0002\u0005\u001d\u0003BBB\u00141\u0001\u0007!/\u0001\r%ENd\u0017m\u001d5%ENd\u0017m\u001d5%Kb$XM\\:j_:$Ba!\u0012\u0004JQ\u0019\u0011pa\u0012\t\u000f\u0005\u001d\u0011\u00041\u0001\u0002\n!11qE\rA\u0002I,Ba!\u0014\u0004XQ!1qJB/)\u0011\u0019\tf!\u0017\u0011\r\u0005\u0015\u0012qFB*!\u0011\u0019)&!\u001d\u0011\t\u0005\r4q\u000b\u0003\b\u0003OR\"\u0019AA5\u0011\u001d\t9H\u0007a\u0001\u00077\u0002b!a\u0003\u0002|\rU\u0003BBB\u00145\u0001\u0007!/\u0006\u0003\u0004b\r-D\u0003BB2\u0007c\"Ba!\u001a\u0004nA1\u0011QEA\u0018\u0007O\u0002Ba!\u001b\u0002rA!\u00111MB6\t\u001d\t9g\u0007b\u0001\u0003SBq!a\u001e\u001c\u0001\u0004\u0019y\u0007\u0005\u0004\u0002\f\u0005m4\u0011\u000e\u0005\u0007\u0007OY\u0002\u0019\u0001:\u0002/QL\b/\u001a)sK\u0012L7-\u0019;fI\u0015DH/\u001a8tS>tW\u0003BB<\u0007\u000b#Ba!\u001f\u0004\bR!11PB@)\u0011\tie! \t\r\u0005eE\u00041\u0001z\u0011\u001d\t9\b\ba\u0001\u0007\u0003\u0003b!a\u0003\u0002|\r\r\u0005\u0003BA2\u0007\u000b#q!a\u001a\u001d\u0005\u0004\tI\u0007\u0003\u0004\u0004(q\u0001\rA]\u0001\u000fM>dG\rJ3yi\u0016t7/[8o+\u0011\u0019ii!&\u0015\t\r=5Q\u0014\u000b\u0005\u0007#\u001bY\n\u0006\u0003\u0004\u0014\u000e]\u0005\u0003BA2\u0007+#q!a\u001a\u001e\u0005\u0004\ty\u000bC\u0004\u0002:v\u0001\ra!'\u0011\u0011%\fila%z\u0007'Cq!a1\u001e\u0001\u0004\u0019\u0019\n\u0003\u0004\u0004(u\u0001\rA]\u0001\u0014M>dGMR5fY\u0012$S\r\u001f;f]NLwN\\\u000b\u0005\u0007G\u001bY\u000b\u0006\u0003\u0004&\u000eMF\u0003BBT\u0007c#Ba!+\u0004.B!\u00111MBV\t\u001d\t9G\bb\u0001\u0003_Cq!!/\u001f\u0001\u0004\u0019y\u000bE\u0005j\u0003{\u001bI+!6\u0004*\"9\u00111\u0019\u0010A\u0002\r%\u0006BBB\u0014=\u0001\u0007!/A\u0007nCB$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0007s\u001bi\fF\u0002z\u0007wCq!!/ \u0001\u0004\t\u0019\u0010\u0003\u0004\u0004(}\u0001\rA]\u0001\u0013[\u0006\u0004h)[3mI\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0004D\u000e\u001dGcA=\u0004F\"9\u0011\u0011\u0018\u0011A\u0002\u0005m\bBBB\u0014A\u0001\u0007!/\u0001\rue\u0006t7OZ8s[\u001aKW\r\u001c3%Kb$XM\\:j_:$Ba!4\u0004RR\u0019\u0011pa4\t\u000f\u0005e\u0016\u00051\u0001\u0003\u0004!11qE\u0011A\u0002I\f1\u0003\u001e:b]N4wN]7%Kb$XM\\:j_:$Baa6\u0004\\R\u0019\u0011p!7\t\u000f\u0005e&\u00051\u0001\u0003\u0010!11q\u0005\u0012A\u0002I\f\u0011C]3qY\u0006\u001cW\rJ3yi\u0016t7/[8o)\u0011\u0019\toa:\u0015\u000be\u001c\u0019o!:\t\u000f\t]1\u00051\u0001\u0003\u001a!1!QD\u0012A\u0002eDaaa\n$\u0001\u0004\u0011\u0018a\u00054j]\u00124\u0015.\u001a7eI\u0015DH/\u001a8tS>tG\u0003BBw\u0007c$BAa\t\u0004p\"9\u0011Q\t\u0013A\u0002\t-\u0002BBB\u0014I\u0001\u0007!/\u0001\bgS:$G%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\r]81 \u000b\u0005\u0005c\u0019I\u0010C\u0004\u0002F\u0015\u0002\r!a\u0012\t\r\r\u001dR\u00051\u0001s\u0003U1\u0017\u000e\u001c;fe\u001aKW\r\u001c3%Kb$XM\\:j_:$B\u0001\"\u0001\u0005\u0006Q!!\u0011\bC\u0002\u0011\u001d\t)E\na\u0001\u0005WAaaa\n'\u0001\u0004\u0011\u0018\u0001\u00054jYR,'\u000fJ3yi\u0016t7/[8o)\u0011!Y\u0001b\u0004\u0015\t\u0005\rBQ\u0002\u0005\b\u0003\u000b:\u0003\u0019AA$\u0011\u0019\u00199c\na\u0001e\u0006!r/\u001b;i\r&dG/\u001a:%Kb$XM\\:j_:$B\u0001\"\u0006\u0005\u001aQ!!q\tC\f\u0011\u001d\t)\u0005\u000ba\u0001\u0003\u000fBaaa\n)\u0001\u0004\u0011\u0018!\u0006:f[>4XMR5fY\u0012$S\r\u001f;f]NLwN\u001c\u000b\u0005\t?!\u0019\u0003F\u0002z\tCAq!!\u0012*\u0001\u0004\u0011Y\u0003\u0003\u0004\u0004(%\u0002\rA]\u0001\u0011e\u0016lwN^3%Kb$XM\\:j_:$B\u0001\"\u000b\u0005.Q\u0019\u0011\u0010b\u000b\t\u000f\u0005\u0015#\u00061\u0001\u0002H!11q\u0005\u0016A\u0002I\f!cY1nK2L'0\u001a\u0013fqR,gn]5p]R!A1\u0007C\u001c)\u0011\tI\u0001\"\u000e\t\u000f\t\r6\u00061\u0001\u0002\n!11qE\u0016A\u0002I\f1\u0003]1tG\u0006d\u0017N_3%Kb$XM\\:j_:$B\u0001\"\u0010\u0005BQ!\u0011\u0011\u0002C \u0011\u001d\u0011\u0019\u000b\fa\u0001\u0003\u0013Aaaa\n-\u0001\u0004\u0011\u0018AI;oI\u0016\u00148oY8sK\u000e\u000bW.\u001a7DCN,7o\u00148ms\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0005H\u0011-C\u0003BA\u0005\t\u0013BqAa).\u0001\u0004\tI\u0001\u0003\u0004\u0004(5\u0002\rA]\u0001\u0015k:$WM]:d_J,G%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\u0011ECQ\u000b\u000b\u0005\u0003\u0013!\u0019\u0006C\u0004\u0003$:\u0002\r!!\u0003\t\r\r\u001db\u00061\u0001s\u0003Y\u0019\u0017-\\3mSj,7*Z=tI\u0015DH/\u001a8tS>tGcA=\u0005\\!11qE\u0018A\u0002I\fq\u0003]1tG\u0006d\u0017N_3LKf\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0007e$\t\u0007\u0003\u0004\u0004(A\u0002\rA]\u0001\u0016g:\f7.\u001b>f\u0017\u0016L8\u000fJ3yi\u0016t7/[8o)\rIHq\r\u0005\u0007\u0007O\t\u0004\u0019\u0001:\u0002KUtG-\u001a:tG>\u0014XmQ1nK2\u001c\u0015m]3LKf\u001cxJ\u001c7zI\u0015DH/\u001a8tS>tGcA=\u0005n!11q\u0005\u001aA\u0002I\f\u0001$\u001e8eKJ\u001c8m\u001c:f\u0017\u0016L8\u000fJ3yi\u0016t7/[8o)\rIH1\u000f\u0005\u0007\u0007O\u0019\u0004\u0019\u0001:\u00021I,wO]5uK*\u001bxN\\!T)\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0005z\u0011uDcA=\u0005|!9!q\u0019\u001bA\u0002\t%\u0007BBB\u0014i\u0001\u0007!/A\to_:+H\u000e\\:%Kb$XM\\:j_:$2!\u001fCB\u0011\u0019\u00199#\u000ea\u0001e\u0006\u0011\u0002.Y:i\u0007>$W\rJ3yi\u0016t7/[8o)\u0011\u0011y\r\"#\t\r\r\u001db\u00071\u0001s\u0003A)\u0017/^1mg\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0005\u0010\u0012ME\u0003BA'\t#C\u0011B!88\u0003\u0003\u0005\r!!-\t\r\r\u001dr\u00071\u0001s\u0001"
)
public final class MonadicJValue {
   private final JValue org$json4s$MonadicJValue$$jv;

   public static boolean equals$extension(final JValue $this, final Object x$1) {
      return MonadicJValue$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final JValue $this) {
      return MonadicJValue$.MODULE$.hashCode$extension($this);
   }

   public static JValue noNulls$extension(final JValue $this) {
      return MonadicJValue$.MODULE$.noNulls$extension($this);
   }

   public static JValue rewriteJsonAST$extension(final JValue $this, final Function1 keyCaseTransform) {
      return MonadicJValue$.MODULE$.rewriteJsonAST$extension($this, keyCaseTransform);
   }

   public static JValue underscoreKeys$extension(final JValue $this) {
      return MonadicJValue$.MODULE$.underscoreKeys$extension($this);
   }

   public static JValue underscoreCamelCaseKeysOnly$extension(final JValue $this) {
      return MonadicJValue$.MODULE$.underscoreCamelCaseKeysOnly$extension($this);
   }

   public static JValue snakizeKeys$extension(final JValue $this) {
      return MonadicJValue$.MODULE$.snakizeKeys$extension($this);
   }

   public static JValue pascalizeKeys$extension(final JValue $this) {
      return MonadicJValue$.MODULE$.pascalizeKeys$extension($this);
   }

   public static JValue camelizeKeys$extension(final JValue $this) {
      return MonadicJValue$.MODULE$.camelizeKeys$extension($this);
   }

   public static String underscore$extension(final JValue $this, final String word) {
      return MonadicJValue$.MODULE$.underscore$extension($this, word);
   }

   public static String underscoreCamelCasesOnly$extension(final JValue $this, final String word) {
      return MonadicJValue$.MODULE$.underscoreCamelCasesOnly$extension($this, word);
   }

   public static String pascalize$extension(final JValue $this, final String word) {
      return MonadicJValue$.MODULE$.pascalize$extension($this, word);
   }

   public static String camelize$extension(final JValue $this, final String word) {
      return MonadicJValue$.MODULE$.camelize$extension($this, word);
   }

   public static JValue remove$extension(final JValue $this, final Function1 p) {
      return MonadicJValue$.MODULE$.remove$extension($this, p);
   }

   public static JValue removeField$extension(final JValue $this, final Function1 p) {
      return MonadicJValue$.MODULE$.removeField$extension($this, p);
   }

   public static JValueWithFilter withFilter$extension(final JValue $this, final Function1 p) {
      return MonadicJValue$.MODULE$.withFilter$extension($this, p);
   }

   public static List filter$extension(final JValue $this, final Function1 p) {
      return MonadicJValue$.MODULE$.filter$extension($this, p);
   }

   public static List filterField$extension(final JValue $this, final Function1 p) {
      return MonadicJValue$.MODULE$.filterField$extension($this, p);
   }

   public static Option find$extension(final JValue $this, final Function1 p) {
      return MonadicJValue$.MODULE$.find$extension($this, p);
   }

   public static Option findField$extension(final JValue $this, final Function1 p) {
      return MonadicJValue$.MODULE$.findField$extension($this, p);
   }

   public static JValue replace$extension(final JValue $this, final List l, final JValue replacement) {
      return MonadicJValue$.MODULE$.replace$extension($this, l, replacement);
   }

   public static JValue transform$extension(final JValue $this, final PartialFunction f) {
      return MonadicJValue$.MODULE$.transform$extension($this, f);
   }

   public static JValue transformField$extension(final JValue $this, final PartialFunction f) {
      return MonadicJValue$.MODULE$.transformField$extension($this, f);
   }

   public static JValue mapField$extension(final JValue $this, final Function1 f) {
      return MonadicJValue$.MODULE$.mapField$extension($this, f);
   }

   public static JValue map$extension(final JValue $this, final Function1 f) {
      return MonadicJValue$.MODULE$.map$extension($this, f);
   }

   public static Object foldField$extension(final JValue $this, final Object z, final Function2 f) {
      return MonadicJValue$.MODULE$.foldField$extension($this, z, f);
   }

   public static Object fold$extension(final JValue $this, final Object z, final Function2 f) {
      return MonadicJValue$.MODULE$.fold$extension($this, z, f);
   }

   public static boolean typePredicate$extension(final JValue $this, final Class clazz, final JValue json) {
      return MonadicJValue$.MODULE$.typePredicate$extension($this, clazz, json);
   }

   public static List $bslash$bslash$extension(final JValue $this, final Class clazz) {
      return MonadicJValue$.MODULE$.$bslash$bslash$extension($this, clazz);
   }

   public static List $bslash$extension(final JValue $this, final Class clazz) {
      return MonadicJValue$.MODULE$.$bslash$extension($this, clazz);
   }

   public static JValue $bslash$bslash$extension(final JValue $this, final String nameToFind) {
      return MonadicJValue$.MODULE$.$bslash$bslash$extension($this, nameToFind);
   }

   public static List findDirect$extension(final JValue $this, final List xs, final Function1 p) {
      return MonadicJValue$.MODULE$.findDirect$extension($this, xs, p);
   }

   public static List findDirectByName$extension(final JValue $this, final List xs, final String name) {
      return MonadicJValue$.MODULE$.findDirectByName$extension($this, xs, name);
   }

   public static JValue $bslash$extension(final JValue $this, final String nameToFind) {
      return MonadicJValue$.MODULE$.$bslash$extension($this, nameToFind);
   }

   public static JValue jvalueToMonadic(final JValue jv) {
      return MonadicJValue$.MODULE$.jvalueToMonadic(jv);
   }

   public JValue org$json4s$MonadicJValue$$jv() {
      return this.org$json4s$MonadicJValue$$jv;
   }

   public JValue $bslash(final String nameToFind) {
      return MonadicJValue$.MODULE$.$bslash$extension(this.org$json4s$MonadicJValue$$jv(), nameToFind);
   }

   private List findDirectByName(final List xs, final String name) {
      return MonadicJValue$.MODULE$.findDirectByName$extension(this.org$json4s$MonadicJValue$$jv(), xs, name);
   }

   private List findDirect(final List xs, final Function1 p) {
      return MonadicJValue$.MODULE$.findDirect$extension(this.org$json4s$MonadicJValue$$jv(), xs, p);
   }

   public JValue $bslash$bslash(final String nameToFind) {
      return MonadicJValue$.MODULE$.$bslash$bslash$extension(this.org$json4s$MonadicJValue$$jv(), nameToFind);
   }

   public List $bslash(final Class clazz) {
      return MonadicJValue$.MODULE$.$bslash$extension(this.org$json4s$MonadicJValue$$jv(), clazz);
   }

   public List $bslash$bslash(final Class clazz) {
      return MonadicJValue$.MODULE$.$bslash$bslash$extension(this.org$json4s$MonadicJValue$$jv(), clazz);
   }

   private boolean typePredicate(final Class clazz, final JValue json) {
      return MonadicJValue$.MODULE$.typePredicate$extension(this.org$json4s$MonadicJValue$$jv(), clazz, json);
   }

   public Object fold(final Object z, final Function2 f) {
      return MonadicJValue$.MODULE$.fold$extension(this.org$json4s$MonadicJValue$$jv(), z, f);
   }

   public Object foldField(final Object z, final Function2 f) {
      return MonadicJValue$.MODULE$.foldField$extension(this.org$json4s$MonadicJValue$$jv(), z, f);
   }

   public JValue map(final Function1 f) {
      return MonadicJValue$.MODULE$.map$extension(this.org$json4s$MonadicJValue$$jv(), f);
   }

   public JValue mapField(final Function1 f) {
      return MonadicJValue$.MODULE$.mapField$extension(this.org$json4s$MonadicJValue$$jv(), f);
   }

   public JValue transformField(final PartialFunction f) {
      return MonadicJValue$.MODULE$.transformField$extension(this.org$json4s$MonadicJValue$$jv(), f);
   }

   public JValue transform(final PartialFunction f) {
      return MonadicJValue$.MODULE$.transform$extension(this.org$json4s$MonadicJValue$$jv(), f);
   }

   public JValue replace(final List l, final JValue replacement) {
      return MonadicJValue$.MODULE$.replace$extension(this.org$json4s$MonadicJValue$$jv(), l, replacement);
   }

   public Option findField(final Function1 p) {
      return MonadicJValue$.MODULE$.findField$extension(this.org$json4s$MonadicJValue$$jv(), p);
   }

   public Option find(final Function1 p) {
      return MonadicJValue$.MODULE$.find$extension(this.org$json4s$MonadicJValue$$jv(), p);
   }

   public List filterField(final Function1 p) {
      return MonadicJValue$.MODULE$.filterField$extension(this.org$json4s$MonadicJValue$$jv(), p);
   }

   public List filter(final Function1 p) {
      return MonadicJValue$.MODULE$.filter$extension(this.org$json4s$MonadicJValue$$jv(), p);
   }

   public JValueWithFilter withFilter(final Function1 p) {
      return MonadicJValue$.MODULE$.withFilter$extension(this.org$json4s$MonadicJValue$$jv(), p);
   }

   public JValue removeField(final Function1 p) {
      return MonadicJValue$.MODULE$.removeField$extension(this.org$json4s$MonadicJValue$$jv(), p);
   }

   public JValue remove(final Function1 p) {
      return MonadicJValue$.MODULE$.remove$extension(this.org$json4s$MonadicJValue$$jv(), p);
   }

   private String camelize(final String word) {
      return MonadicJValue$.MODULE$.camelize$extension(this.org$json4s$MonadicJValue$$jv(), word);
   }

   private String pascalize(final String word) {
      return MonadicJValue$.MODULE$.pascalize$extension(this.org$json4s$MonadicJValue$$jv(), word);
   }

   private String underscoreCamelCasesOnly(final String word) {
      return MonadicJValue$.MODULE$.underscoreCamelCasesOnly$extension(this.org$json4s$MonadicJValue$$jv(), word);
   }

   private String underscore(final String word) {
      return MonadicJValue$.MODULE$.underscore$extension(this.org$json4s$MonadicJValue$$jv(), word);
   }

   public JValue camelizeKeys() {
      return MonadicJValue$.MODULE$.camelizeKeys$extension(this.org$json4s$MonadicJValue$$jv());
   }

   public JValue pascalizeKeys() {
      return MonadicJValue$.MODULE$.pascalizeKeys$extension(this.org$json4s$MonadicJValue$$jv());
   }

   public JValue snakizeKeys() {
      return MonadicJValue$.MODULE$.snakizeKeys$extension(this.org$json4s$MonadicJValue$$jv());
   }

   public JValue underscoreCamelCaseKeysOnly() {
      return MonadicJValue$.MODULE$.underscoreCamelCaseKeysOnly$extension(this.org$json4s$MonadicJValue$$jv());
   }

   public JValue underscoreKeys() {
      return MonadicJValue$.MODULE$.underscoreKeys$extension(this.org$json4s$MonadicJValue$$jv());
   }

   private JValue rewriteJsonAST(final Function1 keyCaseTransform) {
      return MonadicJValue$.MODULE$.rewriteJsonAST$extension(this.org$json4s$MonadicJValue$$jv(), keyCaseTransform);
   }

   public JValue noNulls() {
      return MonadicJValue$.MODULE$.noNulls$extension(this.org$json4s$MonadicJValue$$jv());
   }

   public int hashCode() {
      return MonadicJValue$.MODULE$.hashCode$extension(this.org$json4s$MonadicJValue$$jv());
   }

   public boolean equals(final Object x$1) {
      return MonadicJValue$.MODULE$.equals$extension(this.org$json4s$MonadicJValue$$jv(), x$1);
   }

   public MonadicJValue(final JValue jv) {
      this.org$json4s$MonadicJValue$$jv = jv;
   }

   private static class ArrayIndex$ {
      public static final ArrayIndex$ MODULE$ = new ArrayIndex$();
      private static final Regex R;

      static {
         R = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("^([^\\[]+)\\[(\\d+)\\]"));
      }

      public Option unapply(final String str) {
         Object var2;
         if (str != null) {
            Option var4 = R.unapplySeq(str);
            if (!var4.isEmpty() && var4.get() != null && ((List)var4.get()).lengthCompare(2) == 0) {
               String name = (String)((LinearSeqOps)var4.get()).apply(0);
               String index = (String)((LinearSeqOps)var4.get()).apply(1);
               var2 = scala.Option..MODULE$.apply(new Tuple2(name, BoxesRunTime.boxToInteger(.MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(index)))));
               return (Option)var2;
            }
         }

         var2 = scala.None..MODULE$;
         return (Option)var2;
      }

      public ArrayIndex$() {
      }
   }

   private static class ArrayEach$ {
      public static final ArrayEach$ MODULE$ = new ArrayEach$();
      private static final Regex R;

      static {
         R = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("^([^\\[]+)\\[\\]"));
      }

      public Option unapply(final String str) {
         Object var2;
         if (str != null) {
            Option var4 = R.unapplySeq(str);
            if (!var4.isEmpty() && var4.get() != null && ((List)var4.get()).lengthCompare(1) == 0) {
               String name = (String)((LinearSeqOps)var4.get()).apply(0);
               var2 = scala.Option..MODULE$.apply(name);
               return (Option)var2;
            }
         }

         var2 = scala.None..MODULE$;
         return (Option)var2;
      }

      public ArrayEach$() {
      }
   }

   public static final class JValueWithFilter {
      private final JValue self;
      private final Function1 p;

      public List map(final Function1 f) {
         return MonadicJValue$.MODULE$.filter$extension(MonadicJValue$.MODULE$.jvalueToMonadic(this.self), this.p).map(f);
      }

      public List flatMap(final Function1 f) {
         return MonadicJValue$.MODULE$.filter$extension(MonadicJValue$.MODULE$.jvalueToMonadic(this.self), this.p).flatMap(f);
      }

      public void foreach(final Function1 f) {
         MonadicJValue$.MODULE$.filter$extension(MonadicJValue$.MODULE$.jvalueToMonadic(this.self), this.p).foreach(f);
      }

      public JValueWithFilter withFilter(final Function1 q) {
         return new JValueWithFilter(this.self, (x) -> BoxesRunTime.boxToBoolean($anonfun$withFilter$1(this, q, x)));
      }

      // $FF: synthetic method
      public static final boolean $anonfun$withFilter$1(final JValueWithFilter $this, final Function1 q$1, final JValue x) {
         return BoxesRunTime.unboxToBoolean($this.p.apply(x)) && BoxesRunTime.unboxToBoolean(q$1.apply(x));
      }

      public JValueWithFilter(final JValue self, final Function1 p) {
         this.self = self;
         this.p = p;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}

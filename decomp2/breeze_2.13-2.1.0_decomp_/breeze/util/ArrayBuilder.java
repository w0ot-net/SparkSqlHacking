package breeze.util;

import java.io.Serializable;
import java.util.Arrays;
import scala.Function1;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.collection.mutable.ReusableBuilder;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011\u0015c\u0001CA\u0007\u0003\u001f\t\t#!\u0007\t\u000f\u0005U\u0004\u0001\"\u0001\u0002x!I\u0011Q\u0010\u0001AB\u0013E\u0011q\u0010\u0005\n\u0003\u000f\u0003\u0001\u0019)C\t\u0003\u0013C\u0001\"!&\u0001A\u0003&\u0011\u0011\u0011\u0005\t\u0003/\u0003\u0001U\"\u0005\u0002\u001a\"I\u00111\u0014\u0001A\u0002\u0013E\u0011q\u0010\u0005\n\u0003;\u0003\u0001\u0019!C\t\u0003?C\u0001\"a)\u0001A\u0003&\u0011\u0011\u0011\u0005\b\u0003K\u0003A\u0011AA@\u0011\u001d\t9\u000b\u0001C!\u0003\u007fB\u0001\"!+\u0001A\u0013U\u00111\u0016\u0005\b\u0003_\u0003AQIAY\u0011\u001d\t)\f\u0001C\u0001\u0003oC\u0001\"!/\u0001A\u001bE\u00111\u0018\u0005\b\u0003\u007f\u0003A\u0011AAa\u0011\u001d\ty\f\u0001C\u0001\u0003+Dq!a0\u0001\t\u0003\nIo\u0002\u0005\u0005D\u0005=\u0001\u0012\u0001B\u0006\r!\ti!a\u0004\t\u0002\u0005e\bbBA;'\u0011\u0005!\u0011\u0002\u0005\b\u0005\u001b\u0019B\u0011\u0001B\b\r\u0019\u0011\td\u0005\u0002\u00034!Q!q\b\f\u0003\u0002\u0003\u0006YA!\u0011\t\u000f\u0005Ud\u0003\"\u0001\u0003D!Y\u0011q\u0013\fA\u0002\u0003\u0007I\u0011\u0003B'\u0011-\u0011\tF\u0006a\u0001\u0002\u0004%\tBa\u0015\t\u0017\t]c\u00031A\u0001B\u0003&!q\n\u0005\b\u000532B\u0011\u0002B.\u0011!\tIL\u0006Q\u0005\u0012\t}\u0003b\u0002B2-\u0011\u0005!Q\r\u0005\b\u0005[2B\u0011\u0001B8\u0011\u001d\t)L\u0006C!\u0003oCqA!\u001d\u0017\t\u0003\u0012\u0019\bC\u0004\u0003\u0000Y!\tE!!\u0007\r\tm5C\u0001BO\u0011\u001d\t)h\tC\u0001\u0005OC1\"a&$\u0001\u0004\u0005\r\u0011\"\u0005\u0003,\"Y!\u0011K\u0012A\u0002\u0003\u0007I\u0011\u0003BX\u0011-\u00119f\ta\u0001\u0002\u0003\u0006KA!,\t\u000f\te3\u0005\"\u0003\u00034\"A\u0011\u0011X\u0012!\n#\u00119\fC\u0004\u0003d\r\"\tAa/\t\u000f\t54\u0005\"\u0001\u0003B\"9!\u0011O\u0012\u0005B\t\r\u0007b\u0002B@G\u0011\u0005#\u0011\u0011\u0004\u0007\u0005\u0013\u001c\"Aa3\t\u000f\u0005Ud\u0006\"\u0001\u0003V\"Y\u0011q\u0013\u0018A\u0002\u0003\u0007I\u0011\u0003Bm\u0011-\u0011\tF\fa\u0001\u0002\u0004%\tB!8\t\u0017\t]c\u00061A\u0001B\u0003&!1\u001c\u0005\b\u00053rC\u0011\u0002Bq\u0011!\tIL\fQ\u0005\u0012\t\u0015\bb\u0002B2]\u0011\u0005!\u0011\u001e\u0005\b\u0005[rC\u0011\u0001Bx\u0011\u001d\u0011\tH\fC!\u0005cDqAa /\t\u0003\u0012\tI\u0002\u0004\u0003xN\u0011!\u0011 \u0005\b\u0003kJD\u0011AB\u0002\u0011-\t9*\u000fa\u0001\u0002\u0004%\tba\u0002\t\u0017\tE\u0013\b1AA\u0002\u0013E11\u0002\u0005\f\u0005/J\u0004\u0019!A!B\u0013\u0019I\u0001C\u0004\u0003Ze\"Iaa\u0004\t\u0011\u0005e\u0016\b)C\t\u0007'AqAa\u0019:\t\u0003\u00199\u0002C\u0004\u0003ne\"\ta!\b\t\u000f\tE\u0014\b\"\u0011\u0004 !9!qP\u001d\u0005B\t\u0005eABB\u0013'\t\u00199\u0003C\u0004\u0002v\u0011#\taa\u000b\t\u0017\u0005]E\t1AA\u0002\u0013E1q\u0006\u0005\f\u0005#\"\u0005\u0019!a\u0001\n#\u0019\u0019\u0004C\u0006\u0003X\u0011\u0003\r\u0011!Q!\n\rE\u0002b\u0002B-\t\u0012%1q\u0007\u0005\t\u0003s#\u0005\u0015\"\u0005\u0004<!9!1\r#\u0005\u0002\r}\u0002b\u0002B7\t\u0012\u00051Q\t\u0005\b\u0005c\"E\u0011IB$\u0011\u001d\u0011y\b\u0012C!\u0005\u00033aa!\u0014\u0014\u0005\r=\u0003bBA;\u001f\u0012\u00051\u0011\f\u0005\f\u0003/{\u0005\u0019!a\u0001\n#\u0019i\u0006C\u0006\u0003R=\u0003\r\u00111A\u0005\u0012\r\u0005\u0004b\u0003B,\u001f\u0002\u0007\t\u0011)Q\u0005\u0007?BqA!\u0017P\t\u0013\u0019)\u0007\u0003\u0005\u0002:>\u0003K\u0011CB5\u0011\u001d\u0011\u0019g\u0014C\u0001\u0007[BqA!\u001cP\t\u0003\u0019\u0019\bC\u0004\u0003r=#\te!\u001e\t\u000f\t}t\n\"\u0011\u0003\u0002\u001a111P\n\u0003\u0007{Bq!!\u001e[\t\u0003\u00199\tC\u0006\u0002\u0018j\u0003\r\u00111A\u0005\u0012\r-\u0005b\u0003B)5\u0002\u0007\t\u0019!C\t\u0007\u001fC1Ba\u0016[\u0001\u0004\u0005\t\u0015)\u0003\u0004\u000e\"9!\u0011\f.\u0005\n\rM\u0005\u0002CA]5\u0002&\tba&\t\u000f\t\r$\f\"\u0001\u0004\u001c\"9!Q\u000e.\u0005\u0002\r\u0005\u0006b\u0002B95\u0012\u000531\u0015\u0005\b\u0005\u007fRF\u0011\tBA\r\u0019\u0019Ik\u0005\u0002\u0004,\"9\u0011QO3\u0005\u0002\rU\u0006bCALK\u0002\u0007\t\u0019!C\t\u0007sC1B!\u0015f\u0001\u0004\u0005\r\u0011\"\u0005\u0004>\"Y!qK3A\u0002\u0003\u0005\u000b\u0015BB^\u0011\u001d\u0011I&\u001aC\u0005\u0007\u0003D\u0001\"!/fA\u0013E1Q\u0019\u0005\b\u0005G*G\u0011ABe\u0011\u001d\u0011i'\u001aC\u0001\u0007\u001fDqA!\u001df\t\u0003\u001a\t\u000eC\u0004\u0003\u0000\u0015$\tE!!\u0007\r\u0005]8\u0003\u0001C\u000e\u0011\u001d\t)\b\u001dC\u0001\t?A1\"a&q\u0001\u0004\u0005\r\u0011\"\u0005\u0005$!Y!\u0011\u000b9A\u0002\u0003\u0007I\u0011\u0003C\u0014\u0011-\u00119\u0006\u001da\u0001\u0002\u0003\u0006K\u0001\"\n\t\u000f\te\u0003\u000f\"\u0003\u0005,!A\u0011\u0011\u00189!\n#!y\u0003C\u0004\u0003dA$\t\u0001b\r\t\u000f\t5\u0004\u000f\"\u0001\u0005:!9!\u0011\u000f9\u0005B\u0011m\u0002b\u0002B@a\u0012\u0005#\u0011\u0011\u0004\u0007\u0007/\u001c\"a!7\t\u000f\u0005U4\u0010\"\u0001\u0004^\"9\u0011qS>\u0005\u0012\r\u0005\bb\u0002B2w\u0012\u00051Q\u001d\u0005\b\u0003\u007f[H\u0011IBv\u0011\u001d\tyl\u001fC!\u0007cDqA!\u001c|\t\u0003!)\u0001C\u0004\u0003rm$\t\u0005b\u0002\t\u0011\u0005e6\u0010)C\t\t\u0017AqAa |\t\u0003\u0012\t\tC\u0005\u0005\u0012M\t\t\u0011\"\u0003\u0005\u0014\ta\u0011I\u001d:bs\n+\u0018\u000e\u001c3fe*!\u0011\u0011CA\n\u0003\u0011)H/\u001b7\u000b\u0005\u0005U\u0011A\u00022sK\u0016TXm\u0001\u0001\u0016\t\u0005m\u0011QH\n\b\u0001\u0005u\u0011\u0011FA/!\u0011\ty\"!\n\u000e\u0005\u0005\u0005\"BAA\u0012\u0003\u0015\u00198-\u00197b\u0013\u0011\t9#!\t\u0003\r\u0005s\u0017PU3g!!\tY#!\u000e\u0002:\u0005]SBAA\u0017\u0015\u0011\ty#!\r\u0002\u000f5,H/\u00192mK*!\u00111GA\u0011\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003o\tiCA\bSKV\u001c\u0018M\u00197f\u0005VLG\u000eZ3s!\u0011\tY$!\u0010\r\u0001\u0011Y\u0011q\b\u0001!\u0002\u0003\u0005)\u0019AA!\u0005\u0005!\u0016\u0003BA\"\u0003\u0013\u0002B!a\b\u0002F%!\u0011qIA\u0011\u0005\u001dqu\u000e\u001e5j]\u001e\u0004B!a\b\u0002L%!\u0011QJA\u0011\u0005\r\te.\u001f\u0015\u0005\u0003{\t\t\u0006\u0005\u0003\u0002 \u0005M\u0013\u0002BA+\u0003C\u00111b\u001d9fG&\fG.\u001b>fIB1\u0011qDA-\u0003sIA!a\u0017\u0002\"\t)\u0011I\u001d:bsB!\u0011qLA8\u001d\u0011\t\t'a\u001b\u000f\t\u0005\r\u0014\u0011N\u0007\u0003\u0003KRA!a\u001a\u0002\u0018\u00051AH]8pizJ!!a\t\n\t\u00055\u0014\u0011E\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t\t(a\u001d\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\t\u00055\u0014\u0011E\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u0005e\u0004#BA>\u0001\u0005eRBAA\b\u0003!\u0019\u0017\r]1dSRLXCAAA!\u0011\ty\"a!\n\t\u0005\u0015\u0015\u0011\u0005\u0002\u0004\u0013:$\u0018\u0001D2ba\u0006\u001c\u0017\u000e^=`I\u0015\fH\u0003BAF\u0003#\u0003B!a\b\u0002\u000e&!\u0011qRA\u0011\u0005\u0011)f.\u001b;\t\u0013\u0005M5!!AA\u0002\u0005\u0005\u0015a\u0001=%c\u0005I1-\u00199bG&$\u0018\u0010I\u0001\u0006K2,Wn]\u000b\u0003\u0003/\nAa]5{K\u0006A1/\u001b>f?\u0012*\u0017\u000f\u0006\u0003\u0002\f\u0006\u0005\u0006\"CAJ\u000f\u0005\u0005\t\u0019AAA\u0003\u0015\u0019\u0018N_3!\u0003\u0019aWM\\4uQ\u0006I1N\\8x]NK'0Z\u0001\u000bK:\u001cXO]3TSj,G\u0003BAF\u0003[Cq!a'\f\u0001\u0004\t\t)\u0001\u0005tSj,\u0007*\u001b8u)\u0011\tY)a-\t\u000f\u0005mE\u00021\u0001\u0002\u0002\u0006)1\r\\3beR\u0011\u00111R\u0001\u0007e\u0016\u001c\u0018N_3\u0015\t\u0005-\u0015Q\u0018\u0005\b\u00037s\u0001\u0019AAA\u0003\u0019\tG\rZ!mYR!\u00111YAc\u001b\u0005\u0001\u0001bBAd\u001f\u0001\u0007\u0011\u0011Z\u0001\u0003qN\u0004D!a3\u0002PB1\u0011qDA-\u0003\u001b\u0004B!a\u000f\u0002P\u0012a\u0011\u0011[Ac\u0003\u0003\u0005\tQ!\u0001\u0002T\n\u0019q\fJ\u0019\u0012\t\u0005\r\u0013\u0011\b\u000b\t\u0003\u0007\f9.a9\u0002h\"9\u0011q\u0019\tA\u0002\u0005e\u0007\u0007BAn\u0003?\u0004b!a\b\u0002Z\u0005u\u0007\u0003BA\u001e\u0003?$A\"!9\u0002X\u0006\u0005\t\u0011!B\u0001\u0003'\u00141a\u0018\u00133\u0011\u001d\t)\u000f\u0005a\u0001\u0003\u0003\u000baa\u001c4gg\u0016$\bbBAS!\u0001\u0007\u0011\u0011\u0011\u000b\u0005\u0003\u0007\fY\u000fC\u0004\u0002HF\u0001\r!!<\u0011\r\u0005=\u0018\u0011_A\u001d\u001b\t\t\t$\u0003\u0003\u0002t\u0006E\"\u0001D%uKJ\f'\r\\3P]\u000e,\u0017f\u0003\u0001qGe*'\fR(\u0017]m\u0014\u0011b\u001c4C_>dW-\u00198\u0014\u000bM\ti\"a?\u0011\t\u0005u(qA\u0007\u0003\u0003\u007fTAA!\u0001\u0003\u0004\u0005\u0011\u0011n\u001c\u0006\u0003\u0005\u000b\tAA[1wC&!\u0011\u0011OA\u0000)\t\u0011Y\u0001E\u0002\u0002|M\tA!\\1lKV!!\u0011\u0003B\f)\u0011\u0011\u0019B!\u0007\u0011\u000b\u0005m\u0004A!\u0006\u0011\t\u0005m\"q\u0003\u0003\b\u0003\u007f)\"\u0019AA!\u0011%\u0011Y\"FA\u0001\u0002\b\u0011i\"\u0001\u0006fm&$WM\\2fIE\u0002bAa\b\u0003&\tUQB\u0001B\u0011\u0015\u0011\u0011\u0019#!\t\u0002\u000fI,g\r\\3di&!!q\u0005B\u0011\u0005!\u0019E.Y:t)\u0006<\u0007fA\u000b\u0003,A!\u0011q\u0004B\u0017\u0013\u0011\u0011y#!\t\u0003\r%tG.\u001b8f\u0005\u0015ygMU3g+\u0011\u0011)Da\u000f\u0014\u0007Y\u00119\u0004E\u0003\u0002|\u0001\u0011I\u0004\u0005\u0003\u0002<\tmBaBA -\t\u0007!QH\t\u0005\u0003\u0007\ni\"\u0001\u0002diB1!q\u0004B\u0013\u0005s!\"A!\u0012\u0015\t\t\u001d#1\n\t\u0006\u0005\u00132\"\u0011H\u0007\u0002'!9!q\b\rA\u0004\t\u0005SC\u0001B(!\u0019\ty\"!\u0017\u0003:\u0005IQ\r\\3ng~#S-\u001d\u000b\u0005\u0003\u0017\u0013)\u0006C\u0005\u0002\u0014j\t\t\u00111\u0001\u0003P\u00051Q\r\\3ng\u0002\nq!\\6BeJ\f\u0017\u0010\u0006\u0003\u0003P\tu\u0003bBAN9\u0001\u0007\u0011\u0011\u0011\u000b\u0005\u0003\u0017\u0013\t\u0007C\u0004\u0002\u001cv\u0001\r!!!\u0002\r\u0005$Gm\u00148f)\u0011\u00119G!\u001b\u000e\u0003YAqAa\u001b\u001f\u0001\u0004\u0011I$\u0001\u0003fY\u0016l\u0017A\u0002:fgVdG\u000f\u0006\u0002\u0003P\u00051Q-];bYN$BA!\u001e\u0003|A!\u0011q\u0004B<\u0013\u0011\u0011I(!\t\u0003\u000f\t{w\u000e\\3b]\"9!QP\u0011A\u0002\u0005%\u0013!B8uQ\u0016\u0014\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\t\r\u0005\u0003\u0002BC\u0005\u0017k!Aa\"\u000b\t\t%%1A\u0001\u0005Y\u0006tw-\u0003\u0003\u0003\u000e\n\u001d%AB*ue&tw\rK\u0004\u0017\u0005#\u00139J!'\u0011\t\u0005}!1S\u0005\u0005\u0005+\u000b\tC\u0001\tTKJL\u0017\r\u001c,feNLwN\\+J\t\u0006)a/\u00197vKz\t1A\u0001\u0004pM\nKH/Z\n\u0004G\t}\u0005#BA>\u0001\t\u0005\u0006\u0003BA\u0010\u0005GKAA!*\u0002\"\t!!)\u001f;f)\t\u0011I\u000bE\u0002\u0003J\r*\"A!,\u0011\r\u0005}\u0011\u0011\fBQ)\u0011\tYI!-\t\u0013\u0005Me%!AA\u0002\t5F\u0003\u0002BW\u0005kCq!a')\u0001\u0004\t\t\t\u0006\u0003\u0002\f\ne\u0006bBANS\u0001\u0007\u0011\u0011\u0011\u000b\u0005\u0005{\u0013y,D\u0001$\u0011\u001d\u0011YG\u000ba\u0001\u0005C#\"A!,\u0015\t\tU$Q\u0019\u0005\b\u0005{b\u0003\u0019AA%Q\u001d\u0019#\u0011\u0013BL\u00053\u0013qa\u001c4TQ>\u0014HoE\u0002/\u0005\u001b\u0004R!a\u001f\u0001\u0005\u001f\u0004B!a\b\u0003R&!!1[A\u0011\u0005\u0015\u0019\u0006n\u001c:u)\t\u00119\u000eE\u0002\u0003J9*\"Aa7\u0011\r\u0005}\u0011\u0011\fBh)\u0011\tYIa8\t\u0013\u0005M\u0015'!AA\u0002\tmG\u0003\u0002Bn\u0005GDq!a'4\u0001\u0004\t\t\t\u0006\u0003\u0002\f\n\u001d\bbBANi\u0001\u0007\u0011\u0011\u0011\u000b\u0005\u0005W\u0014i/D\u0001/\u0011\u001d\u0011Y'\u000ea\u0001\u0005\u001f$\"Aa7\u0015\t\tU$1\u001f\u0005\b\u0005{:\u0004\u0019AA%Q\u001dq#\u0011\u0013BL\u00053\u0013aa\u001c4DQ\u0006\u00148cA\u001d\u0003|B)\u00111\u0010\u0001\u0003~B!\u0011q\u0004B\u0000\u0013\u0011\u0019\t!!\t\u0003\t\rC\u0017M\u001d\u000b\u0003\u0007\u000b\u00012A!\u0013:+\t\u0019I\u0001\u0005\u0004\u0002 \u0005e#Q \u000b\u0005\u0003\u0017\u001bi\u0001C\u0005\u0002\u0014r\n\t\u00111\u0001\u0004\nQ!1\u0011BB\t\u0011\u001d\tYJ\u0010a\u0001\u0003\u0003#B!a#\u0004\u0016!9\u00111T A\u0002\u0005\u0005E\u0003BB\r\u00077i\u0011!\u000f\u0005\b\u0005W\u0002\u0005\u0019\u0001B\u007f)\t\u0019I\u0001\u0006\u0003\u0003v\r\u0005\u0002b\u0002B?\u0005\u0002\u0007\u0011\u0011\n\u0015\bs\tE%q\u0013BM\u0005\u0015yg-\u00138u'\r!5\u0011\u0006\t\u0006\u0003w\u0002\u0011\u0011\u0011\u000b\u0003\u0007[\u00012A!\u0013E+\t\u0019\t\u0004\u0005\u0004\u0002 \u0005e\u0013\u0011\u0011\u000b\u0005\u0003\u0017\u001b)\u0004C\u0005\u0002\u0014\u001e\u000b\t\u00111\u0001\u00042Q!1\u0011GB\u001d\u0011\u001d\tY*\u0013a\u0001\u0003\u0003#B!a#\u0004>!9\u00111\u0014&A\u0002\u0005\u0005E\u0003BB!\u0007\u0007j\u0011\u0001\u0012\u0005\b\u0005WZ\u0005\u0019AAA)\t\u0019\t\u0004\u0006\u0003\u0003v\r%\u0003b\u0002B?\u001b\u0002\u0007\u0011\u0011\n\u0015\b\t\nE%q\u0013BM\u0005\u0019yg\rT8oON\u0019qj!\u0015\u0011\u000b\u0005m\u0004aa\u0015\u0011\t\u0005}1QK\u0005\u0005\u0007/\n\tC\u0001\u0003M_:<GCAB.!\r\u0011IeT\u000b\u0003\u0007?\u0002b!a\b\u0002Z\rMC\u0003BAF\u0007GB\u0011\"a%S\u0003\u0003\u0005\raa\u0018\u0015\t\r}3q\r\u0005\b\u00037#\u0006\u0019AAA)\u0011\tYia\u001b\t\u000f\u0005mU\u000b1\u0001\u0002\u0002R!1qNB9\u001b\u0005y\u0005b\u0002B6-\u0002\u000711\u000b\u000b\u0003\u0007?\"BA!\u001e\u0004x!9!Q\u0010-A\u0002\u0005%\u0003fB(\u0003\u0012\n]%\u0011\u0014\u0002\b_\u001a4En\\1u'\rQ6q\u0010\t\u0006\u0003w\u00021\u0011\u0011\t\u0005\u0003?\u0019\u0019)\u0003\u0003\u0004\u0006\u0006\u0005\"!\u0002$m_\u0006$HCABE!\r\u0011IEW\u000b\u0003\u0007\u001b\u0003b!a\b\u0002Z\r\u0005E\u0003BAF\u0007#C\u0011\"a%^\u0003\u0003\u0005\ra!$\u0015\t\r55Q\u0013\u0005\b\u00037{\u0006\u0019AAA)\u0011\tYi!'\t\u000f\u0005m\u0005\r1\u0001\u0002\u0002R!1QTBP\u001b\u0005Q\u0006b\u0002B6C\u0002\u00071\u0011\u0011\u000b\u0003\u0007\u001b#BA!\u001e\u0004&\"9!QP2A\u0002\u0005%\u0003f\u0002.\u0003\u0012\n]%\u0011\u0014\u0002\t_\u001a$u.\u001e2mKN\u0019Qm!,\u0011\u000b\u0005m\u0004aa,\u0011\t\u0005}1\u0011W\u0005\u0005\u0007g\u000b\tC\u0001\u0004E_V\u0014G.\u001a\u000b\u0003\u0007o\u00032A!\u0013f+\t\u0019Y\f\u0005\u0004\u0002 \u0005e3q\u0016\u000b\u0005\u0003\u0017\u001by\fC\u0005\u0002\u0014\"\f\t\u00111\u0001\u0004<R!11XBb\u0011\u001d\tYJ\u001ba\u0001\u0003\u0003#B!a#\u0004H\"9\u00111T6A\u0002\u0005\u0005E\u0003BBf\u0007\u001bl\u0011!\u001a\u0005\b\u0005Wb\u0007\u0019ABX)\t\u0019Y\f\u0006\u0003\u0003v\rM\u0007b\u0002B?]\u0002\u0007\u0011\u0011\n\u0015\bK\nE%q\u0013BM\u0005\u0019yg-\u00168jiN\u00191pa7\u0011\u000b\u0005m\u0004!a#\u0015\u0005\r}\u0007c\u0001B%wV\u001111\u001d\t\u0007\u0003?\tI&a#\u0015\t\r\u001d8\u0011^\u0007\u0002w\"9!1\u000e@A\u0002\u0005-E\u0003BBt\u0007[Dq!a2\u0000\u0001\u0004\u0019y\u000f\u0005\u0004\u0002p\u0006E\u00181\u0012\u000b\t\u0007O\u001c\u0019\u0010\"\u0001\u0005\u0004!A\u0011qYA\u0001\u0001\u0004\u0019)\u0010\r\u0003\u0004x\u000em\bCBA\u0010\u00033\u001aI\u0010\u0005\u0003\u0002<\rmH\u0001DB\u007f\u0007g\f\t\u0011!A\u0003\u0002\r}(aA0%gE!\u00111IAF\u0011!\t)/!\u0001A\u0002\u0005\u0005\u0005\u0002CAS\u0003\u0003\u0001\r!!!\u0015\u0005\r\rH\u0003\u0002B;\t\u0013A\u0001B! \u0002\u0006\u0001\u0007\u0011\u0011\n\u000b\u0005\u0003\u0017#i\u0001\u0003\u0005\u0002\u001c\u0006\u001d\u0001\u0019AAAQ\u001dY(\u0011\u0013BL\u00053\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"\u0001\"\u0006\u0011\t\t\u0015EqC\u0005\u0005\t3\u00119I\u0001\u0004PE*,7\r^\n\u0004a\u0012u\u0001#BA>\u0001\tUDC\u0001C\u0011!\r\u0011I\u0005]\u000b\u0003\tK\u0001b!a\b\u0002Z\tUD\u0003BAF\tSA\u0011\"a%t\u0003\u0003\u0005\r\u0001\"\n\u0015\t\u0011\u0015BQ\u0006\u0005\b\u00037+\b\u0019AAA)\u0011\tY\t\"\r\t\u000f\u0005me\u000f1\u0001\u0002\u0002R!AQ\u0007C\u001c\u001b\u0005\u0001\bb\u0002B6o\u0002\u0007!Q\u000f\u000b\u0003\tK!BA!\u001e\u0005>!9!QP=A\u0002\u0005%\u0003f\u00029\u0003\u0012\n]%\u0011\u0014\u0015\b\u0001\tE%q\u0013BM\u00031\t%O]1z\u0005VLG\u000eZ3s\u0001"
)
public abstract class ArrayBuilder implements ReusableBuilder, Serializable {
   private static final long serialVersionUID = 3L;
   private int capacity;
   private int size;

   public static ArrayBuilder make(final ClassTag evidence$1) {
      return ArrayBuilder$.MODULE$.make(evidence$1);
   }

   public final void sizeHint(final IterableOnce coll, final int delta) {
      Builder.sizeHint$(this, coll, delta);
   }

   public final int sizeHint$default$2() {
      return Builder.sizeHint$default$2$(this);
   }

   public final void sizeHintBounded(final int size, final Iterable boundingColl) {
      Builder.sizeHintBounded$(this, size, boundingColl);
   }

   public Builder mapResult(final Function1 f) {
      return Builder.mapResult$(this, f);
   }

   public final Growable $plus$eq(final Object elem) {
      return Growable.$plus$eq$(this, elem);
   }

   /** @deprecated */
   public final Growable $plus$eq(final Object elem1, final Object elem2, final Seq elems) {
      return Growable.$plus$eq$(this, elem1, elem2, elems);
   }

   public final Growable $plus$plus$eq(final IterableOnce xs) {
      return Growable.$plus$plus$eq$(this, xs);
   }

   public int capacity() {
      return this.capacity;
   }

   public void capacity_$eq(final int x$1) {
      this.capacity = x$1;
   }

   public abstract Object elems();

   public int size() {
      return this.size;
   }

   public void size_$eq(final int x$1) {
      this.size = x$1;
   }

   public int length() {
      return this.size();
   }

   public int knownSize() {
      return this.size();
   }

   public final void ensureSize(final int size) {
      if (this.capacity() < size || this.capacity() == 0) {
         int newsize;
         for(newsize = this.capacity() == 0 ? 16 : this.capacity() * 2; newsize < size; newsize *= 2) {
         }

         this.resize(newsize);
      }

   }

   public final void sizeHint(final int size) {
      if (this.capacity() < size) {
         this.resize(size);
      }

   }

   public void clear() {
      this.size_$eq(0);
   }

   public abstract void resize(final int size);

   public ArrayBuilder addAll(final Object xs) {
      return this.addAll(xs, 0, .MODULE$.array_length(xs));
   }

   public ArrayBuilder addAll(final Object xs, final int offset, final int length) {
      this.ensureSize(this.size() + length);
      scala.Array..MODULE$.copy(xs, offset, this.elems(), this.size(), length);
      this.size_$eq(this.size() + length);
      return this;
   }

   public ArrayBuilder addAll(final IterableOnce xs) {
      int k = xs.knownSize();
      if (k > 0) {
         this.ensureSize(this.size() + k);
         if (xs instanceof Iterable) {
            Iterable var5 = (Iterable)xs;
            int var2 = var5.copyToArray(this.elems(), this.size());
         } else {
            int var6 = xs.iterator().copyToArray(this.elems(), this.size());
         }

         this.size_$eq(this.size() + k);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else if (k < 0) {
         Growable.addAll$(this, xs);
      } else {
         BoxedUnit var7 = BoxedUnit.UNIT;
      }

      return this;
   }

   public boolean[] elems$mcZ$sp() {
      return (boolean[])this.elems();
   }

   public byte[] elems$mcB$sp() {
      return (byte[])this.elems();
   }

   public char[] elems$mcC$sp() {
      return (char[])this.elems();
   }

   public double[] elems$mcD$sp() {
      return (double[])this.elems();
   }

   public float[] elems$mcF$sp() {
      return (float[])this.elems();
   }

   public int[] elems$mcI$sp() {
      return (int[])this.elems();
   }

   public long[] elems$mcJ$sp() {
      return (long[])this.elems();
   }

   public short[] elems$mcS$sp() {
      return (short[])this.elems();
   }

   public BoxedUnit[] elems$mcV$sp() {
      return (BoxedUnit[])this.elems();
   }

   public ArrayBuilder() {
      Growable.$init$(this);
      Builder.$init$(this);
      this.capacity = 0;
      this.size = 0;
   }

   public static final class ofRef extends ArrayBuilder {
      private static final long serialVersionUID = 3L;
      private final ClassTag ct;
      private Object[] elems;

      public Object[] elems() {
         return this.elems;
      }

      public void elems_$eq(final Object[] x$1) {
         this.elems = x$1;
      }

      private Object[] mkArray(final int size) {
         return this.capacity() == size && this.capacity() > 0 ? this.elems() : (this.elems() == null ? (Object[])this.ct.newArray(size) : Arrays.copyOf(this.elems(), size));
      }

      public void resize(final int size) {
         this.elems_$eq(this.mkArray(size));
         this.capacity_$eq(size);
      }

      public ofRef addOne(final Object elem) {
         this.ensureSize(this.size() + 1);
         this.elems()[this.size()] = elem;
         this.size_$eq(this.size() + 1);
         return this;
      }

      public Object[] result() {
         Object[] var10000;
         if (this.capacity() != 0 && this.capacity() == this.size()) {
            this.capacity_$eq(0);
            Object[] res = this.elems();
            this.elems_$eq((Object[])null);
            var10000 = res;
         } else {
            var10000 = this.mkArray(this.size());
         }

         return var10000;
      }

      public void clear() {
         super.clear();
         if (this.elems() != null) {
            Arrays.fill(this.elems(), (Object)null);
         }

      }

      public boolean equals(final Object other) {
         boolean var2;
         if (other instanceof ofRef) {
            ofRef var4 = (ofRef)other;
            var2 = this.size() == var4.size() && this.elems() == var4.elems();
         } else {
            var2 = false;
         }

         return var2;
      }

      public String toString() {
         return "ArrayBuilder.ofRef";
      }

      public ofRef(final ClassTag ct) {
         this.ct = ct;
      }
   }

   public static final class ofByte extends ArrayBuilder$mcB$sp {
      private static final long serialVersionUID = 3L;
      private byte[] elems;

      public byte[] elems() {
         return this.elems$mcB$sp();
      }

      public void elems_$eq(final byte[] x$1) {
         this.elems = x$1;
      }

      private byte[] mkArray(final int size) {
         byte[] newelems = new byte[size];
         if (this.size() > 0) {
            scala.Array..MODULE$.copy(this.elems$mcB$sp(), 0, newelems, 0, this.size());
         }

         return newelems;
      }

      public void resize(final int size) {
         this.elems_$eq(this.mkArray(size));
         this.capacity_$eq(size);
      }

      public ofByte addOne(final byte elem) {
         this.ensureSize(this.size() + 1);
         this.elems$mcB$sp()[this.size()] = elem;
         this.size_$eq(this.size() + 1);
         return this;
      }

      public byte[] result() {
         byte[] var10000;
         if (this.capacity() != 0 && this.capacity() == this.size()) {
            this.capacity_$eq(0);
            byte[] res = this.elems$mcB$sp();
            this.elems_$eq((byte[])null);
            var10000 = res;
         } else {
            var10000 = this.mkArray(this.size());
         }

         return var10000;
      }

      public boolean equals(final Object other) {
         boolean var2;
         if (other instanceof ofByte) {
            ofByte var4 = (ofByte)other;
            var2 = this.size() == var4.size() && this.elems$mcB$sp() == var4.elems$mcB$sp();
         } else {
            var2 = false;
         }

         return var2;
      }

      public String toString() {
         return "ArrayBuilder.ofByte";
      }

      public byte[] elems$mcB$sp() {
         return this.elems;
      }

      public boolean specInstance$() {
         return true;
      }
   }

   public static final class ofShort extends ArrayBuilder$mcS$sp {
      private static final long serialVersionUID = 3L;
      private short[] elems;

      public short[] elems() {
         return this.elems$mcS$sp();
      }

      public void elems_$eq(final short[] x$1) {
         this.elems = x$1;
      }

      private short[] mkArray(final int size) {
         short[] newelems = new short[size];
         if (this.size() > 0) {
            scala.Array..MODULE$.copy(this.elems$mcS$sp(), 0, newelems, 0, this.size());
         }

         return newelems;
      }

      public void resize(final int size) {
         this.elems_$eq(this.mkArray(size));
         this.capacity_$eq(size);
      }

      public ofShort addOne(final short elem) {
         this.ensureSize(this.size() + 1);
         this.elems$mcS$sp()[this.size()] = elem;
         this.size_$eq(this.size() + 1);
         return this;
      }

      public short[] result() {
         short[] var10000;
         if (this.capacity() != 0 && this.capacity() == this.size()) {
            this.capacity_$eq(0);
            short[] res = this.elems$mcS$sp();
            this.elems_$eq((short[])null);
            var10000 = res;
         } else {
            var10000 = this.mkArray(this.size());
         }

         return var10000;
      }

      public boolean equals(final Object other) {
         boolean var2;
         if (other instanceof ofShort) {
            ofShort var4 = (ofShort)other;
            var2 = this.size() == var4.size() && this.elems$mcS$sp() == var4.elems$mcS$sp();
         } else {
            var2 = false;
         }

         return var2;
      }

      public String toString() {
         return "ArrayBuilder.ofShort";
      }

      public short[] elems$mcS$sp() {
         return this.elems;
      }

      public boolean specInstance$() {
         return true;
      }
   }

   public static final class ofChar extends ArrayBuilder$mcC$sp {
      private static final long serialVersionUID = 3L;
      private char[] elems;

      public char[] elems() {
         return this.elems$mcC$sp();
      }

      public void elems_$eq(final char[] x$1) {
         this.elems = x$1;
      }

      private char[] mkArray(final int size) {
         char[] newelems = new char[size];
         if (this.size() > 0) {
            scala.Array..MODULE$.copy(this.elems$mcC$sp(), 0, newelems, 0, this.size());
         }

         return newelems;
      }

      public void resize(final int size) {
         this.elems_$eq(this.mkArray(size));
         this.capacity_$eq(size);
      }

      public ofChar addOne(final char elem) {
         this.ensureSize(this.size() + 1);
         this.elems$mcC$sp()[this.size()] = elem;
         this.size_$eq(this.size() + 1);
         return this;
      }

      public char[] result() {
         char[] var10000;
         if (this.capacity() != 0 && this.capacity() == this.size()) {
            this.capacity_$eq(0);
            char[] res = this.elems$mcC$sp();
            this.elems_$eq((char[])null);
            var10000 = res;
         } else {
            var10000 = this.mkArray(this.size());
         }

         return var10000;
      }

      public boolean equals(final Object other) {
         boolean var2;
         if (other instanceof ofChar) {
            ofChar var4 = (ofChar)other;
            var2 = this.size() == var4.size() && this.elems$mcC$sp() == var4.elems$mcC$sp();
         } else {
            var2 = false;
         }

         return var2;
      }

      public String toString() {
         return "ArrayBuilder.ofChar";
      }

      public char[] elems$mcC$sp() {
         return this.elems;
      }

      public boolean specInstance$() {
         return true;
      }
   }

   public static final class ofInt extends ArrayBuilder$mcI$sp {
      private static final long serialVersionUID = 3L;
      private int[] elems;

      public int[] elems() {
         return this.elems$mcI$sp();
      }

      public void elems_$eq(final int[] x$1) {
         this.elems = x$1;
      }

      private int[] mkArray(final int size) {
         int[] newelems = new int[size];
         if (this.size() > 0) {
            scala.Array..MODULE$.copy(this.elems$mcI$sp(), 0, newelems, 0, this.size());
         }

         return newelems;
      }

      public void resize(final int size) {
         this.elems_$eq(this.mkArray(size));
         this.capacity_$eq(size);
      }

      public ofInt addOne(final int elem) {
         this.ensureSize(this.size() + 1);
         this.elems$mcI$sp()[this.size()] = elem;
         this.size_$eq(this.size() + 1);
         return this;
      }

      public int[] result() {
         int[] var10000;
         if (this.capacity() != 0 && this.capacity() == this.size()) {
            this.capacity_$eq(0);
            int[] res = this.elems$mcI$sp();
            this.elems_$eq((int[])null);
            var10000 = res;
         } else {
            var10000 = this.mkArray(this.size());
         }

         return var10000;
      }

      public boolean equals(final Object other) {
         boolean var2;
         if (other instanceof ofInt) {
            ofInt var4 = (ofInt)other;
            var2 = this.size() == var4.size() && this.elems$mcI$sp() == var4.elems$mcI$sp();
         } else {
            var2 = false;
         }

         return var2;
      }

      public String toString() {
         return "ArrayBuilder.ofInt";
      }

      public int[] elems$mcI$sp() {
         return this.elems;
      }

      public boolean specInstance$() {
         return true;
      }
   }

   public static final class ofLong extends ArrayBuilder$mcJ$sp {
      private static final long serialVersionUID = 3L;
      private long[] elems;

      public long[] elems() {
         return this.elems$mcJ$sp();
      }

      public void elems_$eq(final long[] x$1) {
         this.elems = x$1;
      }

      private long[] mkArray(final int size) {
         long[] newelems = new long[size];
         if (this.size() > 0) {
            scala.Array..MODULE$.copy(this.elems$mcJ$sp(), 0, newelems, 0, this.size());
         }

         return newelems;
      }

      public void resize(final int size) {
         this.elems_$eq(this.mkArray(size));
         this.capacity_$eq(size);
      }

      public ofLong addOne(final long elem) {
         this.ensureSize(this.size() + 1);
         this.elems$mcJ$sp()[this.size()] = elem;
         this.size_$eq(this.size() + 1);
         return this;
      }

      public long[] result() {
         long[] var10000;
         if (this.capacity() != 0 && this.capacity() == this.size()) {
            this.capacity_$eq(0);
            long[] res = this.elems$mcJ$sp();
            this.elems_$eq((long[])null);
            var10000 = res;
         } else {
            var10000 = this.mkArray(this.size());
         }

         return var10000;
      }

      public boolean equals(final Object other) {
         boolean var2;
         if (other instanceof ofLong) {
            ofLong var4 = (ofLong)other;
            var2 = this.size() == var4.size() && this.elems$mcJ$sp() == var4.elems$mcJ$sp();
         } else {
            var2 = false;
         }

         return var2;
      }

      public String toString() {
         return "ArrayBuilder.ofLong";
      }

      public long[] elems$mcJ$sp() {
         return this.elems;
      }

      public boolean specInstance$() {
         return true;
      }
   }

   public static final class ofFloat extends ArrayBuilder$mcF$sp {
      private static final long serialVersionUID = 3L;
      private float[] elems;

      public float[] elems() {
         return this.elems$mcF$sp();
      }

      public void elems_$eq(final float[] x$1) {
         this.elems = x$1;
      }

      private float[] mkArray(final int size) {
         float[] newelems = new float[size];
         if (this.size() > 0) {
            scala.Array..MODULE$.copy(this.elems$mcF$sp(), 0, newelems, 0, this.size());
         }

         return newelems;
      }

      public void resize(final int size) {
         this.elems_$eq(this.mkArray(size));
         this.capacity_$eq(size);
      }

      public ofFloat addOne(final float elem) {
         this.ensureSize(this.size() + 1);
         this.elems$mcF$sp()[this.size()] = elem;
         this.size_$eq(this.size() + 1);
         return this;
      }

      public float[] result() {
         float[] var10000;
         if (this.capacity() != 0 && this.capacity() == this.size()) {
            this.capacity_$eq(0);
            float[] res = this.elems$mcF$sp();
            this.elems_$eq((float[])null);
            var10000 = res;
         } else {
            var10000 = this.mkArray(this.size());
         }

         return var10000;
      }

      public boolean equals(final Object other) {
         boolean var2;
         if (other instanceof ofFloat) {
            ofFloat var4 = (ofFloat)other;
            var2 = this.size() == var4.size() && this.elems$mcF$sp() == var4.elems$mcF$sp();
         } else {
            var2 = false;
         }

         return var2;
      }

      public String toString() {
         return "ArrayBuilder.ofFloat";
      }

      public float[] elems$mcF$sp() {
         return this.elems;
      }

      public boolean specInstance$() {
         return true;
      }
   }

   public static final class ofDouble extends ArrayBuilder$mcD$sp {
      private static final long serialVersionUID = 3L;
      private double[] elems;

      public double[] elems() {
         return this.elems$mcD$sp();
      }

      public void elems_$eq(final double[] x$1) {
         this.elems = x$1;
      }

      private double[] mkArray(final int size) {
         double[] newelems = new double[size];
         if (this.size() > 0) {
            scala.Array..MODULE$.copy(this.elems$mcD$sp(), 0, newelems, 0, this.size());
         }

         return newelems;
      }

      public void resize(final int size) {
         this.elems_$eq(this.mkArray(size));
         this.capacity_$eq(size);
      }

      public ofDouble addOne(final double elem) {
         this.ensureSize(this.size() + 1);
         this.elems$mcD$sp()[this.size()] = elem;
         this.size_$eq(this.size() + 1);
         return this;
      }

      public double[] result() {
         double[] var10000;
         if (this.capacity() != 0 && this.capacity() == this.size()) {
            this.capacity_$eq(0);
            double[] res = this.elems$mcD$sp();
            this.elems_$eq((double[])null);
            var10000 = res;
         } else {
            var10000 = this.mkArray(this.size());
         }

         return var10000;
      }

      public boolean equals(final Object other) {
         boolean var2;
         if (other instanceof ofDouble) {
            ofDouble var4 = (ofDouble)other;
            var2 = this.size() == var4.size() && this.elems$mcD$sp() == var4.elems$mcD$sp();
         } else {
            var2 = false;
         }

         return var2;
      }

      public String toString() {
         return "ArrayBuilder.ofDouble";
      }

      public double[] elems$mcD$sp() {
         return this.elems;
      }

      public boolean specInstance$() {
         return true;
      }
   }

   public static class ofBoolean extends ArrayBuilder$mcZ$sp {
      private static final long serialVersionUID = 3L;
      private boolean[] elems;

      public boolean[] elems() {
         return this.elems$mcZ$sp();
      }

      public void elems_$eq(final boolean[] x$1) {
         this.elems = x$1;
      }

      private boolean[] mkArray(final int size) {
         boolean[] newelems = new boolean[size];
         if (this.size() > 0) {
            scala.Array..MODULE$.copy(this.elems$mcZ$sp(), 0, newelems, 0, this.size());
         }

         return newelems;
      }

      public void resize(final int size) {
         this.elems_$eq(this.mkArray(size));
         this.capacity_$eq(size);
      }

      public ofBoolean addOne(final boolean elem) {
         this.ensureSize(this.size() + 1);
         this.elems$mcZ$sp()[this.size()] = elem;
         this.size_$eq(this.size() + 1);
         return this;
      }

      public boolean[] result() {
         boolean[] var10000;
         if (this.capacity() != 0 && this.capacity() == this.size()) {
            this.capacity_$eq(0);
            boolean[] res = this.elems$mcZ$sp();
            this.elems_$eq((boolean[])null);
            var10000 = res;
         } else {
            var10000 = this.mkArray(this.size());
         }

         return var10000;
      }

      public boolean equals(final Object other) {
         boolean var2;
         if (other instanceof ofBoolean) {
            ofBoolean var4 = (ofBoolean)other;
            var2 = this.size() == var4.size() && this.elems$mcZ$sp() == var4.elems$mcZ$sp();
         } else {
            var2 = false;
         }

         return var2;
      }

      public String toString() {
         return "ArrayBuilder.ofBoolean";
      }

      public boolean[] elems$mcZ$sp() {
         return this.elems;
      }

      public boolean specInstance$() {
         return true;
      }
   }

   public static final class ofUnit extends ArrayBuilder$mcV$sp {
      private static final long serialVersionUID = 3L;

      public BoxedUnit[] elems() {
         return this.elems$mcV$sp();
      }

      public ofUnit addOne(final BoxedUnit elem) {
         this.size_$eq(this.size() + 1);
         return this;
      }

      public ofUnit addAll(final IterableOnce xs) {
         this.size_$eq(this.size() + xs.iterator().size());
         return this;
      }

      public ofUnit addAll(final Object xs, final int offset, final int length) {
         this.size_$eq(this.size() + length);
         return this;
      }

      public BoxedUnit[] result() {
         BoxedUnit[] ans = new BoxedUnit[this.size()];

         for(int i = 0; i < this.size(); ++i) {
            ans[i] = BoxedUnit.UNIT;
         }

         return ans;
      }

      public boolean equals(final Object other) {
         boolean var2;
         if (other instanceof ofUnit) {
            ofUnit var4 = (ofUnit)other;
            var2 = this.size() == var4.size();
         } else {
            var2 = false;
         }

         return var2;
      }

      public void resize(final int size) {
      }

      public String toString() {
         return "ArrayBuilder.ofUnit";
      }

      public BoxedUnit[] elems$mcV$sp() {
         throw new UnsupportedOperationException();
      }
   }
}

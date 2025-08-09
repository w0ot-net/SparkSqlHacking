package breeze.signal;

import breeze.linalg.DenseVector;
import breeze.signal.support.CanConvolve;
import breeze.signal.support.CanDesignFilterDecimation;
import breeze.signal.support.CanFilter;
import breeze.signal.support.CanFilterBPBS;
import breeze.signal.support.CanFilterLPHP;
import breeze.signal.support.CanFilterMedian;
import breeze.signal.support.CanFirwin;
import breeze.signal.support.CanHaarTr;
import breeze.signal.support.CanIHaarTr;
import breeze.signal.support.FIRKernel1D;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011Ew!\u0002\"D\u0011\u0003Ae!\u0002&D\u0011\u0003Y\u0005\"\u0002*\u0002\t\u0003\u0019\u0006b\u0002+\u0002\u0005\u0004%\t!\u0016\u0005\u00073\u0006\u0001\u000b\u0011\u0002,\t\u000fi\u000b!\u0019!C\u00017\"1q,\u0001Q\u0001\nqCQ\u0001Y\u0001\u0005\u0002\u0005Dq!_\u0001\u0012\u0002\u0013\u0005!\u0010\u0003\u0005\u0002\f\u0005\t\n\u0011\"\u0001{\u0011%\ti!AI\u0001\n\u0003\ty\u0001C\u0004\u0002\u0014\u0005!\t!!\u0006\t\u0013\u0005u\u0014!%A\u0005\u0002\u0005}\u0004\"CAF\u0003E\u0005I\u0011AAG\u0011%\tI*AI\u0001\n\u0003\tY\nC\u0005\u0002(\u0006\t\n\u0011\"\u0001\u0002*\"9\u0011QW\u0001\u0005\u0002\u0005]\u0006\"CAm\u0003E\u0005I\u0011AAn\u0011%\t\u0019/AI\u0001\n\u0003\t)\u000fC\u0005\u0002n\u0006\t\n\u0011\"\u0001\u0002p\"I\u0011q_\u0001\u0012\u0002\u0013\u0005\u0011\u0011 \u0005\b\u0005\u0003\tA\u0011\u0001B\u0002\u0011%\u0011I#AI\u0001\n\u0003\u0011Y\u0003C\u0005\u00034\u0005\t\n\u0011\"\u0001\u00036!9!QH\u0001\u0005\u0002\t}\u0002\"\u0003B=\u0003E\u0005I\u0011\u0001B>\u0011%\u0011\t)AI\u0001\n\u0003\u0011\u0019\tC\u0005\u0003\u000e\u0006\t\n\u0011\"\u0001\u0003\u0010\"I!\u0011T\u0001\u0012\u0002\u0013\u0005!1\u0014\u0005\n\u0005C\u000b\u0011\u0013!C\u0001\u0005GCqA!+\u0002\t\u0003\u0011Y\u000bC\u0005\u0003L\u0006\t\n\u0011\"\u0001\u0003N\"I!1[\u0001\u0012\u0002\u0013\u0005!Q\u001b\u0005\n\u00057\f\u0011\u0013!C\u0001\u0005;D\u0011Ba9\u0002#\u0003%\tA!:\t\u0013\t-\u0018!%A\u0005\u0002\t5\bb\u0002Bz\u0003\u0011\u0005!Q\u001f\u0005\n\u0007;\t\u0011\u0013!C\u0001\u0007?A\u0011b!\n\u0002#\u0003%\taa\n\t\u0013\r5\u0012!%A\u0005\u0002\r=\u0002\"CB\u001b\u0003E\u0005I\u0011AB\u001c\u0011%\u0019i$AI\u0001\n\u0003\u0019y\u0004C\u0004\u0004F\u0005!\taa\u0012\t\u0013\r\u001d\u0014!%A\u0005\u0002\r%\u0004\"CB8\u0003E\u0005I\u0011AB9\u0011%\u00199(AI\u0001\n\u0003\u0019I\bC\u0005\u0004\u0000\u0005\t\n\u0011\"\u0001\u0004\u0002\"I1qQ\u0001\u0012\u0002\u0013\u00051\u0011\u0012\u0005\b\u0007\u001f\u000bA\u0011ABI\u0011%\u0019I-AI\u0001\n\u0003\u0019Y\rC\u0005\u0004P\u0006\t\n\u0011\"\u0001\u0004R\"I1Q[\u0001\u0012\u0002\u0013\u00051q\u001b\u0005\n\u00077\f\u0011\u0013!C\u0001\u0007;D\u0011b!9\u0002#\u0003%\taa9\t\u000f\r-\u0018\u0001\"\u0001\u0004n\"IAqC\u0001\u0012\u0002\u0013\u0005A\u0011\u0004\u0005\n\t;\t\u0011\u0013!C\u0001\t?A\u0011\u0002b\t\u0002#\u0003%\t\u0001\"\n\t\u0013\u0011%\u0012!%A\u0005\u0002\u0011-\u0002b\u0002C\u001a\u0003\u0011\u0005AQ\u0007\u0005\n\t#\n\u0011\u0013!C\u0001\t'Bq\u0001b\r\u0002\t\u0003!9\u0006C\u0004\u0005l\u0005!\t\u0001\"\u001c\t\u000f\u0011%\u0015\u0001\"\u0001\u0005\f\"9AqT\u0001\u0005\u0002\u0011\u0005\u0006b\u0002C^\u0003\u0011\u0005AQX\u0001\ba\u0006\u001c7.Y4f\u0015\t!U)\u0001\u0004tS\u001et\u0017\r\u001c\u0006\u0002\r\u00061!M]3fu\u0016\u001c\u0001\u0001\u0005\u0002J\u00035\t1IA\u0004qC\u000e\\\u0017mZ3\u0014\u0005\u0005a\u0005CA'Q\u001b\u0005q%\"A(\u0002\u000bM\u001c\u0017\r\\1\n\u0005Es%AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002\u0011\u0006\u0001bm\\;sS\u0016\u0014HK]1og\u001a|'/\\\u000b\u0002-:\u0011\u0011jV\u0005\u00031\u000e\u000b\u0011BZ8ve&,'\u000f\u0016:\u0002#\u0019|WO]5feR\u0013\u0018M\\:g_Jl\u0007%A\fj]Z,'o]3G_V\u0014\u0018.\u001a:Ue\u0006t7OZ8s[V\tAL\u0004\u0002J;&\u0011alQ\u0001\u000bS\u001a{WO]5feR\u0013\u0018\u0001G5om\u0016\u00148/\u001a$pkJLWM\u001d+sC:\u001chm\u001c:nA\u0005Yam\\;sS\u0016\u0014hI]3r)\u0015\u00117\u000e\u001d:u!\r\u0019g\r[\u0007\u0002I*\u0011Q-R\u0001\u0007Y&t\u0017\r\\4\n\u0005\u001d$'a\u0003#f]N,g+Z2u_J\u0004\"!T5\n\u0005)t%A\u0002#pk\ndW\rC\u0003m\u000f\u0001\u0007Q.\u0001\u0007xS:$wn\u001e'f]\u001e$\b\u000e\u0005\u0002N]&\u0011qN\u0014\u0002\u0004\u0013:$\bbB9\b!\u0003\u0005\r\u0001[\u0001\u0003MNDqa]\u0004\u0011\u0002\u0003\u0007\u0001.\u0001\u0002ei\"9Qo\u0002I\u0001\u0002\u00041\u0018aB:iS\u001a$X\r\u001a\t\u0003\u001b^L!\u0001\u001f(\u0003\u000f\t{w\u000e\\3b]\u0006)bm\\;sS\u0016\u0014hI]3rI\u0011,g-Y;mi\u0012\u0012T#A>+\u0005!d8&A?\u0011\u0007y\f9!D\u0001\u0000\u0015\u0011\t\t!a\u0001\u0002\u0013Ut7\r[3dW\u0016$'bAA\u0003\u001d\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0007\u0005%qPA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQCZ8ve&,'O\u0012:fc\u0012\"WMZ1vYR$3'A\u000bg_V\u0014\u0018.\u001a:Ge\u0016\fH\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\u0005E!F\u0001<}\u0003!\u0019wN\u001c<pYZ,W\u0003CA\f\u0003\u0007\nI%a\b\u0015\u001d\u0005e\u0011QJA)\u0003+\ny&!\u001b\u0002tQ!\u00111DA\u0019!\u0011\ti\"a\b\r\u0001\u00119\u0011\u0011E\u0006C\u0002\u0005\r\"AB(viB,H/\u0005\u0003\u0002&\u0005-\u0002cA'\u0002(%\u0019\u0011\u0011\u0006(\u0003\u000f9{G\u000f[5oOB\u0019Q*!\f\n\u0007\u0005=bJA\u0002B]fDq!a\r\f\u0001\b\t)$A\u0006dC:\u001cuN\u001c<pYZ,\u0007CCA\u001c\u0003{\t\t%a\u0012\u0002\u001c5\u0011\u0011\u0011\b\u0006\u0004\u0003w\u0019\u0015aB:vaB|'\u000f^\u0005\u0005\u0003\u007f\tIDA\u0006DC:\u001cuN\u001c<pYZ,\u0007\u0003BA\u000f\u0003\u0007\"q!!\u0012\f\u0005\u0004\t\u0019CA\u0003J]B,H\u000f\u0005\u0003\u0002\u001e\u0005%CaBA&\u0017\t\u0007\u00111\u0005\u0002\u000b\u0017\u0016\u0014h.\u001a7UsB,\u0007bBA(\u0017\u0001\u0007\u0011\u0011I\u0001\u0005I\u0006$\u0018\rC\u0004\u0002T-\u0001\r!a\u0012\u0002\r-,'O\\3m\u0011%\t9f\u0003I\u0001\u0002\u0004\tI&A\u0003sC:<W\rE\u0002J\u00037J1!!\u0018D\u0005!y\u0005\u000f\u001e*b]\u001e,\u0007\"CA1\u0017A\u0005\t\u0019AA2\u0003!yg/\u001a:iC:<\u0007cA%\u0002f%\u0019\u0011qM\"\u0003\u0017=\u0003Ho\u0014<fe\"\fgn\u001a\u0005\n\u0003WZ\u0001\u0013!a\u0001\u0003[\nq\u0001]1eI&tw\rE\u0002J\u0003_J1!!\u001dD\u0005)y\u0005\u000f\u001e)bI\u0012Lgn\u001a\u0005\n\u0003kZ\u0001\u0013!a\u0001\u0003o\na!\\3uQ>$\u0007cA%\u0002z%\u0019\u00111P\"\u0003\u0013=\u0003H/T3uQ>$\u0017AE2p]Z|GN^3%I\u00164\u0017-\u001e7uIM*\u0002\"!!\u0002\u0006\u0006\u001d\u0015\u0011R\u000b\u0003\u0003\u0007S3!!\u0017}\t\u001d\t)\u0005\u0004b\u0001\u0003G!q!a\u0013\r\u0005\u0004\t\u0019\u0003B\u0004\u0002\"1\u0011\r!a\t\u0002%\r|gN^8mm\u0016$C-\u001a4bk2$H\u0005N\u000b\t\u0003\u001f\u000b\u0019*!&\u0002\u0018V\u0011\u0011\u0011\u0013\u0016\u0004\u0003GbHaBA#\u001b\t\u0007\u00111\u0005\u0003\b\u0003\u0017j!\u0019AA\u0012\t\u001d\t\t#\u0004b\u0001\u0003G\t!cY8om>dg/\u001a\u0013eK\u001a\fW\u000f\u001c;%kUA\u0011QTAQ\u0003G\u000b)+\u0006\u0002\u0002 *\u001a\u0011Q\u000e?\u0005\u000f\u0005\u0015cB1\u0001\u0002$\u00119\u00111\n\bC\u0002\u0005\rBaBA\u0011\u001d\t\u0007\u00111E\u0001\u0013G>tgo\u001c7wK\u0012\"WMZ1vYR$c'\u0006\u0005\u0002,\u0006=\u0016\u0011WAZ+\t\tiKK\u0002\u0002xq$q!!\u0012\u0010\u0005\u0004\t\u0019\u0003B\u0004\u0002L=\u0011\r!a\t\u0005\u000f\u0005\u0005rB1\u0001\u0002$\u0005I1m\u001c:sK2\fG/Z\u000b\t\u0003s\u000b9-a3\u0002@Rq\u00111XAg\u0003\u001f\f\t.a5\u0002V\u0006]G\u0003BA_\u0003\u0003\u0004B!!\b\u0002@\u00129\u0011\u0011\u0005\tC\u0002\u0005\r\u0002bBA\u001a!\u0001\u000f\u00111\u0019\t\u000b\u0003o\ti$!2\u0002J\u0006u\u0006\u0003BA\u000f\u0003\u000f$q!!\u0012\u0011\u0005\u0004\t\u0019\u0003\u0005\u0003\u0002\u001e\u0005-GaBA&!\t\u0007\u00111\u0005\u0005\b\u0003\u001f\u0002\u0002\u0019AAc\u0011\u001d\t\u0019\u0006\u0005a\u0001\u0003\u0013D\u0011\"a\u0016\u0011!\u0003\u0005\r!!\u0017\t\u0013\u0005\u0005\u0004\u0003%AA\u0002\u0005\r\u0004\"CA6!A\u0005\t\u0019AA7\u0011%\t)\b\u0005I\u0001\u0002\u0004\t9(A\nd_J\u0014X\r\\1uK\u0012\"WMZ1vYR$3'\u0006\u0005\u0002\u0002\u0006u\u0017q\\Aq\t\u001d\t)%\u0005b\u0001\u0003G!q!a\u0013\u0012\u0005\u0004\t\u0019\u0003B\u0004\u0002\"E\u0011\r!a\t\u0002'\r|'O]3mCR,G\u0005Z3gCVdG\u000f\n\u001b\u0016\u0011\u0005=\u0015q]Au\u0003W$q!!\u0012\u0013\u0005\u0004\t\u0019\u0003B\u0004\u0002LI\u0011\r!a\t\u0005\u000f\u0005\u0005\"C1\u0001\u0002$\u0005\u00192m\u001c:sK2\fG/\u001a\u0013eK\u001a\fW\u000f\u001c;%kUA\u0011QTAy\u0003g\f)\u0010B\u0004\u0002FM\u0011\r!a\t\u0005\u000f\u0005-3C1\u0001\u0002$\u00119\u0011\u0011E\nC\u0002\u0005\r\u0012aE2peJ,G.\u0019;fI\u0011,g-Y;mi\u00122T\u0003CAV\u0003w\fi0a@\u0005\u000f\u0005\u0015CC1\u0001\u0002$\u00119\u00111\n\u000bC\u0002\u0005\rBaBA\u0011)\t\u0007\u00111E\u0001\u0007M&dG/\u001a:\u0016\u0011\t\u0015!\u0011\u0004B\u000f\u0005\u0017!\"Ba\u0002\u0003\"\t\r\"Q\u0005B\u0014)\u0011\u0011IA!\u0004\u0011\t\u0005u!1\u0002\u0003\b\u0003C)\"\u0019AA\u0012\u0011\u001d\u0011y!\u0006a\u0002\u0005#\t\u0011bY1o\r&dG/\u001a:\u0011\u0015\u0005]\"1\u0003B\f\u00057\u0011I!\u0003\u0003\u0003\u0016\u0005e\"!C\"b]\u001aKG\u000e^3s!\u0011\tiB!\u0007\u0005\u000f\u0005\u0015SC1\u0001\u0002$A!\u0011Q\u0004B\u000f\t\u001d\u0011y\"\u0006b\u0001\u0003G\u0011aaS3s]\u0016d\u0007bBA(+\u0001\u0007!q\u0003\u0005\b\u0003'*\u0002\u0019\u0001B\u000e\u0011%\t\t'\u0006I\u0001\u0002\u0004\t\u0019\u0007C\u0005\u0002lU\u0001\n\u00111\u0001\u0002n\u0005\u0001b-\u001b7uKJ$C-\u001a4bk2$HeM\u000b\t\u0003\u001f\u0013iCa\f\u00032\u00119\u0011Q\t\fC\u0002\u0005\rBa\u0002B\u0010-\t\u0007\u00111\u0005\u0003\b\u0003C1\"\u0019AA\u0012\u0003A1\u0017\u000e\u001c;fe\u0012\"WMZ1vYR$C'\u0006\u0005\u0002\u001e\n]\"\u0011\bB\u001e\t\u001d\t)e\u0006b\u0001\u0003G!qAa\b\u0018\u0005\u0004\t\u0019\u0003B\u0004\u0002\"]\u0011\r!a\t\u0002\u0011\u0019LG\u000e^3s\u0005B+bA!\u0011\u0003V\t\u001dC\u0003\u0005B\"\u0005/\u0012IFa\u0019\u0003h\t-$Q\u000fB<)\u0011\u0011)E!\u0013\u0011\t\u0005u!q\t\u0003\b\u0003CA\"\u0019AA\u0012\u0011\u001d\u0011Y\u0005\u0007a\u0002\u0005\u001b\nQbY1o\r&dG/\u001a:C!\n\u001b\u0006\u0003CA\u001c\u0005\u001f\u0012\u0019F!\u0012\n\t\tE\u0013\u0011\b\u0002\u000e\u0007\u0006tg)\u001b7uKJ\u0014\u0005KQ*\u0011\t\u0005u!Q\u000b\u0003\b\u0003\u000bB\"\u0019AA\u0012\u0011\u001d\ty\u0005\u0007a\u0001\u0005'BqAa\u0017\u0019\u0001\u0004\u0011i&\u0001\u0004p[\u0016<\u0017m\u001d\t\u0006\u001b\n}\u0003\u000e[\u0005\u0004\u0005Cr%A\u0002+va2,'\u0007\u0003\u0005\u0003fa\u0001\n\u00111\u0001i\u0003)\u0019\u0018-\u001c9mKJ\u000bG/\u001a\u0005\t\u0005SB\u0002\u0013!a\u0001[\u0006!A/\u00199t\u0011%\u0011i\u0007\u0007I\u0001\u0002\u0004\u0011y'\u0001\u0007lKJtW\r\u001c#fg&<g\u000eE\u0002J\u0005cJ1Aa\u001dD\u0005=y\u0005\u000f\u001e#fg&<g.T3uQ>$\u0007\"CA11A\u0005\t\u0019AA2\u0011%\tY\u0007\u0007I\u0001\u0002\u0004\ti'\u0001\ngS2$XM\u001d\"QI\u0011,g-Y;mi\u0012\u001aT#\u0002>\u0003~\t}DaBA#3\t\u0007\u00111\u0005\u0003\b\u0003CI\"\u0019AA\u0012\u0003I1\u0017\u000e\u001c;fe\n\u0003F\u0005Z3gCVdG\u000f\n\u001b\u0016\r\t\u0015%\u0011\u0012BF+\t\u00119I\u000b\u0002ny\u00129\u0011Q\t\u000eC\u0002\u0005\rBaBA\u00115\t\u0007\u00111E\u0001\u0013M&dG/\u001a:C!\u0012\"WMZ1vYR$S'\u0006\u0004\u0003\u0012\nU%qS\u000b\u0003\u0005'S3Aa\u001c}\t\u001d\t)e\u0007b\u0001\u0003G!q!!\t\u001c\u0005\u0004\t\u0019#\u0001\ngS2$XM\u001d\"QI\u0011,g-Y;mi\u00122TCBAH\u0005;\u0013y\nB\u0004\u0002Fq\u0011\r!a\t\u0005\u000f\u0005\u0005BD1\u0001\u0002$\u0005\u0011b-\u001b7uKJ\u0014\u0005\u000b\n3fM\u0006,H\u000e\u001e\u00138+\u0019\tiJ!*\u0003(\u00129\u0011QI\u000fC\u0002\u0005\rBaBA\u0011;\t\u0007\u00111E\u0001\tM&dG/\u001a:C'V1!Q\u0016B^\u0005g#\u0002Ca,\u0003>\n}&\u0011\u0019Bb\u0005\u000b\u00149M!3\u0015\t\tE&Q\u0017\t\u0005\u0003;\u0011\u0019\fB\u0004\u0002\"y\u0011\r!a\t\t\u000f\t-c\u0004q\u0001\u00038BA\u0011q\u0007B(\u0005s\u0013\t\f\u0005\u0003\u0002\u001e\tmFaBA#=\t\u0007\u00111\u0005\u0005\b\u0003\u001fr\u0002\u0019\u0001B]\u0011\u001d\u0011YF\ba\u0001\u0005;B\u0001B!\u001a\u001f!\u0003\u0005\r\u0001\u001b\u0005\t\u0005Sr\u0002\u0013!a\u0001[\"I!Q\u000e\u0010\u0011\u0002\u0003\u0007!q\u000e\u0005\n\u0003Cr\u0002\u0013!a\u0001\u0003GB\u0011\"a\u001b\u001f!\u0003\u0005\r!!\u001c\u0002%\u0019LG\u000e^3s\u0005N#C-\u001a4bk2$HeM\u000b\u0006u\n='\u0011\u001b\u0003\b\u0003\u000bz\"\u0019AA\u0012\t\u001d\t\tc\bb\u0001\u0003G\t!CZ5mi\u0016\u0014(i\u0015\u0013eK\u001a\fW\u000f\u001c;%iU1!Q\u0011Bl\u00053$q!!\u0012!\u0005\u0004\t\u0019\u0003B\u0004\u0002\"\u0001\u0012\r!a\t\u0002%\u0019LG\u000e^3s\u0005N#C-\u001a4bk2$H%N\u000b\u0007\u0005#\u0013yN!9\u0005\u000f\u0005\u0015\u0013E1\u0001\u0002$\u00119\u0011\u0011E\u0011C\u0002\u0005\r\u0012A\u00054jYR,'OQ*%I\u00164\u0017-\u001e7uIY*b!a$\u0003h\n%HaBA#E\t\u0007\u00111\u0005\u0003\b\u0003C\u0011#\u0019AA\u0012\u0003I1\u0017\u000e\u001c;fe\n\u001bF\u0005Z3gCVdG\u000fJ\u001c\u0016\r\u0005u%q\u001eBy\t\u001d\t)e\tb\u0001\u0003G!q!!\t$\u0005\u0004\t\u0019#\u0001\u0005gS2$XM\u001d'Q+\u0019\u00119pa\u0003\u0003~R\u0001\"\u0011`B\u0007\u0007\u001f\u0019\u0019b!\u0006\u0004\u0018\re11\u0004\u000b\u0005\u0005w\u0014y\u0010\u0005\u0003\u0002\u001e\tuHaBA\u0011I\t\u0007\u00111\u0005\u0005\b\u0007\u0003!\u00039AB\u0002\u00035\u0019\u0017M\u001c$jYR,'\u000f\u0014)I!BA\u0011qGB\u0003\u0007\u0013\u0011Y0\u0003\u0003\u0004\b\u0005e\"!D\"b]\u001aKG\u000e^3s\u0019BC\u0005\u000b\u0005\u0003\u0002\u001e\r-AaBA#I\t\u0007\u00111\u0005\u0005\b\u0003\u001f\"\u0003\u0019AB\u0005\u0011\u0019\u0019\t\u0002\na\u0001Q\u0006)q.\\3hC\"A!Q\r\u0013\u0011\u0002\u0003\u0007\u0001\u000e\u0003\u0005\u0003j\u0011\u0002\n\u00111\u0001n\u0011%\u0011i\u0007\nI\u0001\u0002\u0004\u0011y\u0007C\u0005\u0002b\u0011\u0002\n\u00111\u0001\u0002d!I\u00111\u000e\u0013\u0011\u0002\u0003\u0007\u0011QN\u0001\u0013M&dG/\u001a:M!\u0012\"WMZ1vYR$3'F\u0003{\u0007C\u0019\u0019\u0003B\u0004\u0002F\u0015\u0012\r!a\t\u0005\u000f\u0005\u0005RE1\u0001\u0002$\u0005\u0011b-\u001b7uKJd\u0005\u000b\n3fM\u0006,H\u000e\u001e\u00135+\u0019\u0011)i!\u000b\u0004,\u00119\u0011Q\t\u0014C\u0002\u0005\rBaBA\u0011M\t\u0007\u00111E\u0001\u0013M&dG/\u001a:M!\u0012\"WMZ1vYR$S'\u0006\u0004\u0003\u0012\u000eE21\u0007\u0003\b\u0003\u000b:#\u0019AA\u0012\t\u001d\t\tc\nb\u0001\u0003G\t!CZ5mi\u0016\u0014H\n\u0015\u0013eK\u001a\fW\u000f\u001c;%mU1\u0011qRB\u001d\u0007w!q!!\u0012)\u0005\u0004\t\u0019\u0003B\u0004\u0002\"!\u0012\r!a\t\u0002%\u0019LG\u000e^3s\u0019B#C-\u001a4bk2$HeN\u000b\u0007\u0003;\u001b\tea\u0011\u0005\u000f\u0005\u0015\u0013F1\u0001\u0002$\u00119\u0011\u0011E\u0015C\u0002\u0005\r\u0012\u0001\u00034jYR,'\u000f\u0013)\u0016\r\r%3qKB()A\u0019Ye!\u0017\u0004\\\ru3qLB1\u0007G\u001a)\u0007\u0006\u0003\u0004N\rE\u0003\u0003BA\u000f\u0007\u001f\"q!!\t+\u0005\u0004\t\u0019\u0003C\u0004\u0004\u0002)\u0002\u001daa\u0015\u0011\u0011\u0005]2QAB+\u0007\u001b\u0002B!!\b\u0004X\u00119\u0011Q\t\u0016C\u0002\u0005\r\u0002bBA(U\u0001\u00071Q\u000b\u0005\u0007\u0007#Q\u0003\u0019\u00015\t\u0011\t\u0015$\u0006%AA\u0002!D\u0001B!\u001b+!\u0003\u0005\r!\u001c\u0005\n\u0005[R\u0003\u0013!a\u0001\u0005_B\u0011\"!\u0019+!\u0003\u0005\r!a\u0019\t\u0013\u0005-$\u0006%AA\u0002\u00055\u0014A\u00054jYR,'\u000f\u0013)%I\u00164\u0017-\u001e7uIM*RA_B6\u0007[\"q!!\u0012,\u0005\u0004\t\u0019\u0003B\u0004\u0002\"-\u0012\r!a\t\u0002%\u0019LG\u000e^3s\u0011B#C-\u001a4bk2$H\u0005N\u000b\u0007\u0005\u000b\u001b\u0019h!\u001e\u0005\u000f\u0005\u0015CF1\u0001\u0002$\u00119\u0011\u0011\u0005\u0017C\u0002\u0005\r\u0012A\u00054jYR,'\u000f\u0013)%I\u00164\u0017-\u001e7uIU*bA!%\u0004|\ruDaBA#[\t\u0007\u00111\u0005\u0003\b\u0003Ci#\u0019AA\u0012\u0003I1\u0017\u000e\u001c;fe\"\u0003F\u0005Z3gCVdG\u000f\n\u001c\u0016\r\u0005=51QBC\t\u001d\t)E\fb\u0001\u0003G!q!!\t/\u0005\u0004\t\u0019#\u0001\ngS2$XM\u001d%QI\u0011,g-Y;mi\u0012:TCBAO\u0007\u0017\u001bi\tB\u0004\u0002F=\u0012\r!a\t\u0005\u000f\u0005\u0005rF1\u0001\u0002$\u0005\u0011B-Z:jO:4\u0015\u000e\u001c;fe\u001aK'o^5o+\u0011\u0019\u0019ja(\u0015!\rU51VBW\u0007_\u001b\u0019la.\u0004<\u000e}F\u0003BBL\u0007C\u0003b!a\u000e\u0004\u001a\u000eu\u0015\u0002BBN\u0003s\u00111BR%S\u0017\u0016\u0014h.\u001a72\tB!\u0011QDBP\t\u001d\t\t\u0003\rb\u0001\u0003GAqaa)1\u0001\b\u0019)+A\u0005dC:4\u0015N]<j]B1\u0011qGBT\u0007;KAa!+\u0002:\tI1)\u00198GSJ<\u0018N\u001c\u0005\u0007\u0005S\u0002\u0004\u0019A7\t\r\tm\u0003\u00071\u0001c\u0011!\u0019\t\f\rI\u0001\u0002\u0004A\u0017a\u00028zcVL7\u000f\u001e\u0005\t\u0007k\u0003\u0004\u0013!a\u0001m\u0006A!0\u001a:p!\u0006\u001c8\u000f\u0003\u0005\u0004:B\u0002\n\u00111\u0001w\u0003\u0015\u00198-\u00197f\u0011!\u0019i\f\rI\u0001\u0002\u0004A\u0017AC7vYRL\u0007\u000f\\5fe\"I1\u0011\u0019\u0019\u0011\u0002\u0003\u000711Y\u0001\n_B$x+\u001b8e_^\u00042!SBc\u0013\r\u00199m\u0011\u0002\u0012\u001fB$x+\u001b8e_^4UO\\2uS>t\u0017\u0001\b3fg&<gNR5mi\u0016\u0014h)\u001b:xS:$C-\u001a4bk2$HeM\u000b\u0004u\u000e5GaBA\u0011c\t\u0007\u00111E\u0001\u001dI\u0016\u001c\u0018n\u001a8GS2$XM\u001d$je^Lg\u000e\n3fM\u0006,H\u000e\u001e\u00135+\u0011\tyaa5\u0005\u000f\u0005\u0005\"G1\u0001\u0002$\u0005aB-Z:jO:4\u0015\u000e\u001c;fe\u001aK'o^5oI\u0011,g-Y;mi\u0012*T\u0003BA\b\u00073$q!!\t4\u0005\u0004\t\u0019#\u0001\u000feKNLwM\u001c$jYR,'OR5so&tG\u0005Z3gCVdG\u000f\n\u001c\u0016\u0007i\u001cy\u000eB\u0004\u0002\"Q\u0012\r!a\t\u00029\u0011,7/[4o\r&dG/\u001a:GSJ<\u0018N\u001c\u0013eK\u001a\fW\u000f\u001c;%oU!1Q]Bu+\t\u00199OK\u0002\u0004Dr$q!!\t6\u0005\u0004\t\u0019#\u0001\feKNLwM\u001c$jYR,'\u000fR3dS6\fG/[8o+\u0011\u0019yo!>\u0015\u0019\rEH\u0011\u0001C\u0003\t\u000f!Y\u0001\"\u0004\u0015\t\rM8q\u001f\t\u0005\u0003;\u0019)\u0010B\u0004\u0002\"Y\u0012\r!a\t\t\u000f\reh\u0007q\u0001\u0004|\u0006I2-\u00198EKNLwM\u001c$jYR,'\u000fR3dS6\fG/[8o!\u0019\t9d!@\u0004t&!1q`A\u001d\u0005e\u0019\u0015M\u001c#fg&<gNR5mi\u0016\u0014H)Z2j[\u0006$\u0018n\u001c8\t\r\u0011\ra\u00071\u0001n\u0003\u00191\u0017m\u0019;pe\"A1Q\u0018\u001c\u0011\u0002\u0003\u0007\u0001\u000eC\u0005\u0005\nY\u0002\n\u00111\u0001\u0003p\u0005yq\u000e\u001d;EKNLwM\\'fi\"|G\rC\u0005\u0004BZ\u0002\n\u00111\u0001\u0004D\"IAq\u0002\u001c\u0011\u0002\u0003\u0007A\u0011C\u0001\u000f_B$h)\u001b7uKJ|%\u000fZ3s!\rIE1C\u0005\u0004\t+\u0019%!D(qi\u001aKG\u000e^3s)\u0006\u00048/\u0001\u0011eKNLwM\u001c$jYR,'\u000fR3dS6\fG/[8oI\u0011,g-Y;mi\u0012\u0012Tc\u0001>\u0005\u001c\u00119\u0011\u0011E\u001cC\u0002\u0005\r\u0012\u0001\t3fg&<gNR5mi\u0016\u0014H)Z2j[\u0006$\u0018n\u001c8%I\u00164\u0017-\u001e7uIM*BA!%\u0005\"\u00119\u0011\u0011\u0005\u001dC\u0002\u0005\r\u0012\u0001\t3fg&<gNR5mi\u0016\u0014H)Z2j[\u0006$\u0018n\u001c8%I\u00164\u0017-\u001e7uIQ*Ba!:\u0005(\u00119\u0011\u0011E\u001dC\u0002\u0005\r\u0012\u0001\t3fg&<gNR5mi\u0016\u0014H)Z2j[\u0006$\u0018n\u001c8%I\u00164\u0017-\u001e7uIU*B\u0001\"\f\u00052U\u0011Aq\u0006\u0016\u0004\t#aHaBA\u0011u\t\u0007\u00111E\u0001\rM&dG/\u001a:NK\u0012L\u0017M\\\u000b\u0005\to!y\u0004\u0006\u0005\u0005:\u0011-CQ\nC()\u0011!Y\u0004\"\u0011\u0011\t\r4GQ\b\t\u0005\u0003;!y\u0004B\u0004\u0002Fm\u0012\r!a\t\t\u000f\u0011\r3\bq\u0001\u0005F\u0005y1-\u00198GS2$XM]'fI&\fg\u000e\u0005\u0004\u00028\u0011\u001dCQH\u0005\u0005\t\u0013\nIDA\bDC:4\u0015\u000e\u001c;fe6+G-[1o\u0011\u001d\tye\u000fa\u0001\twAQ\u0001\\\u001eA\u00025D\u0011\"!\u0019<!\u0003\u0005\r!a\u0019\u0002-\u0019LG\u000e^3s\u001b\u0016$\u0017.\u00198%I\u00164\u0017-\u001e7uIM*B!a$\u0005V\u00119\u0011Q\t\u001fC\u0002\u0005\rR\u0003\u0002C-\tC\"b\u0001b\u0017\u0005h\u0011%D\u0003\u0002C/\tG\u0002Ba\u00194\u0005`A!\u0011Q\u0004C1\t\u001d\t)%\u0010b\u0001\u0003GAq\u0001b\u0011>\u0001\b!)\u0007\u0005\u0004\u00028\u0011\u001dCq\f\u0005\b\u0003\u001fj\u0004\u0019\u0001C/\u0011\u0015aW\b1\u0001n\u0003\u0019A\u0017-\u0019:UeV1Aq\u000eCB\tk\"B\u0001\"\u001d\u0005\u0006R!A1\u000fC<!\u0011\ti\u0002\"\u001e\u0005\u000f\u0005\u0005bH1\u0001\u0002$!9A\u0011\u0010 A\u0004\u0011m\u0014\u0001E2b]\"\u000b\u0017M\u001d+sC:\u001chm\u001c:n!!\t9\u0004\" \u0005\u0002\u0012M\u0014\u0002\u0002C@\u0003s\u0011\u0011bQ1o\u0011\u0006\f'\u000f\u0016:\u0011\t\u0005uA1\u0011\u0003\b\u0003\u000br$\u0019AA\u0012\u0011\u001d!9I\u0010a\u0001\t\u0003\u000b\u0011A^\u0001\u000eQ\u0006\f'\u000f\u0016:b]N4wN]7\u0016\r\u00115E1\u0014CJ)\u0011!y\t\"(\u0015\t\u0011EEQ\u0013\t\u0005\u0003;!\u0019\nB\u0004\u0002\"}\u0012\r!a\t\t\u000f\u0011et\bq\u0001\u0005\u0018BA\u0011q\u0007C?\t3#\t\n\u0005\u0003\u0002\u001e\u0011mEaBA#\u007f\t\u0007\u00111\u0005\u0005\b\t\u000f{\u0004\u0019\u0001CM\u0003\u001dI\u0007*Y1s)J,b\u0001b)\u00058\u0012%F\u0003\u0002CS\ts#B\u0001b*\u0005,B!\u0011Q\u0004CU\t\u001d\t\t\u0003\u0011b\u0001\u0003GAq\u0001\",A\u0001\b!y+A\fdC:LeN^3sg\u0016D\u0015-\u0019:Ue\u0006t7OZ8s[BA\u0011q\u0007CY\tk#9+\u0003\u0003\u00054\u0006e\"AC\"b]&C\u0015-\u0019:UeB!\u0011Q\u0004C\\\t\u001d\t)\u0005\u0011b\u0001\u0003GAq\u0001b\"A\u0001\u0004!),\u0001\u000bj]Z,'o]3IC\u0006\u0014HK]1og\u001a|'/\\\u000b\u0007\t\u007f#i\r\"2\u0015\t\u0011\u0005Gq\u001a\u000b\u0005\t\u0007$9\r\u0005\u0003\u0002\u001e\u0011\u0015GaBA\u0011\u0003\n\u0007\u00111\u0005\u0005\b\t[\u000b\u00059\u0001Ce!!\t9\u0004\"-\u0005L\u0012\r\u0007\u0003BA\u000f\t\u001b$q!!\u0012B\u0005\u0004\t\u0019\u0003C\u0004\u0005\b\u0006\u0003\r\u0001b3"
)
public final class package {
   public static Object inverseHaarTransform(final Object v, final CanIHaarTr canInverseHaarTransform) {
      return package$.MODULE$.inverseHaarTransform(v, canInverseHaarTransform);
   }

   public static Object iHaarTr(final Object v, final CanIHaarTr canInverseHaarTransform) {
      return package$.MODULE$.iHaarTr(v, canInverseHaarTransform);
   }

   public static Object haarTransform(final Object v, final CanHaarTr canHaarTransform) {
      return package$.MODULE$.haarTransform(v, canHaarTransform);
   }

   public static Object haarTr(final Object v, final CanHaarTr canHaarTransform) {
      return package$.MODULE$.haarTr(v, canHaarTransform);
   }

   public static DenseVector filterMedian(final DenseVector data, final int windowLength, final CanFilterMedian canFilterMedian) {
      return package$.MODULE$.filterMedian(data, windowLength, canFilterMedian);
   }

   public static OptOverhang filterMedian$default$3() {
      return package$.MODULE$.filterMedian$default$3();
   }

   public static DenseVector filterMedian(final DenseVector data, final int windowLength, final OptOverhang overhang, final CanFilterMedian canFilterMedian) {
      return package$.MODULE$.filterMedian(data, windowLength, overhang, canFilterMedian);
   }

   public static OptFilterTaps designFilterDecimation$default$5() {
      return package$.MODULE$.designFilterDecimation$default$5();
   }

   public static OptWindowFunction designFilterDecimation$default$4() {
      return package$.MODULE$.designFilterDecimation$default$4();
   }

   public static OptDesignMethod designFilterDecimation$default$3() {
      return package$.MODULE$.designFilterDecimation$default$3();
   }

   public static double designFilterDecimation$default$2() {
      return package$.MODULE$.designFilterDecimation$default$2();
   }

   public static Object designFilterDecimation(final int factor, final double multiplier, final OptDesignMethod optDesignMethod, final OptWindowFunction optWindow, final OptFilterTaps optFilterOrder, final CanDesignFilterDecimation canDesignFilterDecimation) {
      return package$.MODULE$.designFilterDecimation(factor, multiplier, optDesignMethod, optWindow, optFilterOrder, canDesignFilterDecimation);
   }

   public static OptWindowFunction designFilterFirwin$default$7() {
      return package$.MODULE$.designFilterFirwin$default$7();
   }

   public static double designFilterFirwin$default$6() {
      return package$.MODULE$.designFilterFirwin$default$6();
   }

   public static boolean designFilterFirwin$default$5() {
      return package$.MODULE$.designFilterFirwin$default$5();
   }

   public static boolean designFilterFirwin$default$4() {
      return package$.MODULE$.designFilterFirwin$default$4();
   }

   public static double designFilterFirwin$default$3() {
      return package$.MODULE$.designFilterFirwin$default$3();
   }

   public static FIRKernel1D designFilterFirwin(final int taps, final DenseVector omegas, final double nyquist, final boolean zeroPass, final boolean scale, final double multiplier, final OptWindowFunction optWindow, final CanFirwin canFirwin) {
      return package$.MODULE$.designFilterFirwin(taps, omegas, nyquist, zeroPass, scale, multiplier, optWindow, canFirwin);
   }

   public static OptPadding filterHP$default$7() {
      return package$.MODULE$.filterHP$default$7();
   }

   public static OptOverhang filterHP$default$6() {
      return package$.MODULE$.filterHP$default$6();
   }

   public static OptDesignMethod filterHP$default$5() {
      return package$.MODULE$.filterHP$default$5();
   }

   public static int filterHP$default$4() {
      return package$.MODULE$.filterHP$default$4();
   }

   public static double filterHP$default$3() {
      return package$.MODULE$.filterHP$default$3();
   }

   public static Object filterHP(final Object data, final double omega, final double sampleRate, final int taps, final OptDesignMethod kernelDesign, final OptOverhang overhang, final OptPadding padding, final CanFilterLPHP canFilterLPHP) {
      return package$.MODULE$.filterHP(data, omega, sampleRate, taps, kernelDesign, overhang, padding, canFilterLPHP);
   }

   public static OptPadding filterLP$default$7() {
      return package$.MODULE$.filterLP$default$7();
   }

   public static OptOverhang filterLP$default$6() {
      return package$.MODULE$.filterLP$default$6();
   }

   public static OptDesignMethod filterLP$default$5() {
      return package$.MODULE$.filterLP$default$5();
   }

   public static int filterLP$default$4() {
      return package$.MODULE$.filterLP$default$4();
   }

   public static double filterLP$default$3() {
      return package$.MODULE$.filterLP$default$3();
   }

   public static Object filterLP(final Object data, final double omega, final double sampleRate, final int taps, final OptDesignMethod kernelDesign, final OptOverhang overhang, final OptPadding padding, final CanFilterLPHP canFilterLPHP) {
      return package$.MODULE$.filterLP(data, omega, sampleRate, taps, kernelDesign, overhang, padding, canFilterLPHP);
   }

   public static OptPadding filterBS$default$7() {
      return package$.MODULE$.filterBS$default$7();
   }

   public static OptOverhang filterBS$default$6() {
      return package$.MODULE$.filterBS$default$6();
   }

   public static OptDesignMethod filterBS$default$5() {
      return package$.MODULE$.filterBS$default$5();
   }

   public static int filterBS$default$4() {
      return package$.MODULE$.filterBS$default$4();
   }

   public static double filterBS$default$3() {
      return package$.MODULE$.filterBS$default$3();
   }

   public static Object filterBS(final Object data, final Tuple2 omegas, final double sampleRate, final int taps, final OptDesignMethod kernelDesign, final OptOverhang overhang, final OptPadding padding, final CanFilterBPBS canFilterBPBS) {
      return package$.MODULE$.filterBS(data, omegas, sampleRate, taps, kernelDesign, overhang, padding, canFilterBPBS);
   }

   public static OptPadding filterBP$default$7() {
      return package$.MODULE$.filterBP$default$7();
   }

   public static OptOverhang filterBP$default$6() {
      return package$.MODULE$.filterBP$default$6();
   }

   public static OptDesignMethod filterBP$default$5() {
      return package$.MODULE$.filterBP$default$5();
   }

   public static int filterBP$default$4() {
      return package$.MODULE$.filterBP$default$4();
   }

   public static double filterBP$default$3() {
      return package$.MODULE$.filterBP$default$3();
   }

   public static Object filterBP(final Object data, final Tuple2 omegas, final double sampleRate, final int taps, final OptDesignMethod kernelDesign, final OptOverhang overhang, final OptPadding padding, final CanFilterBPBS canFilterBPBS) {
      return package$.MODULE$.filterBP(data, omegas, sampleRate, taps, kernelDesign, overhang, padding, canFilterBPBS);
   }

   public static OptPadding filter$default$4() {
      return package$.MODULE$.filter$default$4();
   }

   public static OptOverhang filter$default$3() {
      return package$.MODULE$.filter$default$3();
   }

   public static Object filter(final Object data, final Object kernel, final OptOverhang overhang, final OptPadding padding, final CanFilter canFilter) {
      return package$.MODULE$.filter(data, kernel, overhang, padding, canFilter);
   }

   public static OptMethod correlate$default$6() {
      return package$.MODULE$.correlate$default$6();
   }

   public static OptPadding correlate$default$5() {
      return package$.MODULE$.correlate$default$5();
   }

   public static OptOverhang correlate$default$4() {
      return package$.MODULE$.correlate$default$4();
   }

   public static OptRange correlate$default$3() {
      return package$.MODULE$.correlate$default$3();
   }

   public static Object correlate(final Object data, final Object kernel, final OptRange range, final OptOverhang overhang, final OptPadding padding, final OptMethod method, final CanConvolve canConvolve) {
      return package$.MODULE$.correlate(data, kernel, range, overhang, padding, method, canConvolve);
   }

   public static OptMethod convolve$default$6() {
      return package$.MODULE$.convolve$default$6();
   }

   public static OptPadding convolve$default$5() {
      return package$.MODULE$.convolve$default$5();
   }

   public static OptOverhang convolve$default$4() {
      return package$.MODULE$.convolve$default$4();
   }

   public static OptRange convolve$default$3() {
      return package$.MODULE$.convolve$default$3();
   }

   public static Object convolve(final Object data, final Object kernel, final OptRange range, final OptOverhang overhang, final OptPadding padding, final OptMethod method, final CanConvolve canConvolve) {
      return package$.MODULE$.convolve(data, kernel, range, overhang, padding, method, canConvolve);
   }

   public static boolean fourierFreq$default$4() {
      return package$.MODULE$.fourierFreq$default$4();
   }

   public static double fourierFreq$default$3() {
      return package$.MODULE$.fourierFreq$default$3();
   }

   public static double fourierFreq$default$2() {
      return package$.MODULE$.fourierFreq$default$2();
   }

   public static DenseVector fourierFreq(final int windowLength, final double fs, final double dt, final boolean shifted) {
      return package$.MODULE$.fourierFreq(windowLength, fs, dt, shifted);
   }

   public static iFourierTr$ inverseFourierTransform() {
      return package$.MODULE$.inverseFourierTransform();
   }

   public static fourierTr$ fourierTransform() {
      return package$.MODULE$.fourierTransform();
   }
}

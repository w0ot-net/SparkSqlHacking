package scala.concurrent.impl;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.invoke.SerializedLambda;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import scala.$eq$colon$eq;
import scala.$less$colon$less;
import scala.$less$colon$less$;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Option$;
import scala.PartialFunction;
import scala.concurrent.Batchable;
import scala.concurrent.CanAwait;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.Future$;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.Duration$;
import scala.concurrent.duration.FiniteDuration;
import scala.math.Ordered;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;
import scala.util.control.NonFatal$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015]sA\u0002-Z\u0011\u0003YvL\u0002\u0004b3\"\u00051L\u0019\u0005\u0006O\u0006!\t!\u001b\u0004\u0006U\u0006\u00111l\u001b\u0005\n\tc\u001b!\u0011!Q\u0001\naDaaZ\u0002\u0005\u0002\u0011M\u0006b\u0002C]\u0007\u0011\u0015A1\u0018\u0005\u000f\t\u0003\u001cA\u0011!A\u0003\u0002\u0003\u0005KQ\u0002Cb\u0011!!9.\u0001Q\u0005\u000e\u0011eg\u0001\u0002>\u0002\u0001mD\u0011\"!\u000e\n\u0005\u0003\u0005\u000b\u0011B2\t\u000f\u001dL\u0001\u0015\"\u0003\u00028!1q-\u0003C\u0003\u0003{AaaZ\u0005\u0005\u0006\u0005\r\u0003bBA#\u0013\u0011\u0015\u0013q\t\u0005\b\u0003\u001bJAQIA(\u0011\u001d\t\t&\u0003C#\u0003'Bq!a\u001d\n\t\u000b\n)\bC\u0004\u0002\b&!)%!#\t\u000f\u0005=\u0016\u0002\"\u0012\u00022\"9\u0011\u0011Y\u0005\u0005F\u0005\r\u0007bBAk\u0013\u0011\u0015\u0013q\u001b\u0005\b\u0003SLAQIAv\u0011\u001d\ti0\u0003C#\u0003\u007fDqAa\u0006\n\t\u000b\u0012I\u0002C\u0004\u0003F%!)Ea\u0012\t\u000f\te\u0013\u0002\"\u0012\u0003\\!9!QO\u0005\u0005F\t]\u0004b\u0002BE\u0013\u0011\u0015#1\u0012\u0005\b\u0005\u001fKAQ\tBI\u0011!\u0011\t,\u0003Q\u0005\u000e\tM\u0006b\u0002Bc\u0013\u0011\u0015!q\u0019\u0005\b\u0003\u0003JAQ\u0001B\u0000\u0011\u001d\u0019\u0019\"\u0003C#\u0007+Aqaa\u0006\n\t\u000b\u001aI\u0002C\u0004\u0004\"%!iaa\t\t\u000f\r\u001d\u0012\u0002\"\u0012\u0004*!A1QF\u0005\u0005\u0006\u0005\u0019y\u0003C\u0004\u0004:%!)ea\u000f\t\u000f\r\u0005\u0013\u0002\"\u0004\u0004D!AAqB\u0005!\n\u001b!\t\u0002\u0003\u0005\u0004R&\u0001KQ\u0002C\u000f\u0011!!)#\u0003C\u00037\u0012\u001d\u0002\u0002\u0003C\u001b\u0013\u0011\u00151\fb\u000e\t\u000f\u0011u\u0012\u0002\"\u0003\u0005@!9AqP\u0005\u0005\n\u0011\u0005\u0005\"\u0003Cs\u0003\t\u0007IQ\u0001Ct\u0011!!i/\u0001Q\u0001\u000e\u0011%\b\"\u0003Cx\u0003\t\u0007IQ\u0001Cy\u0011!!90\u0001Q\u0001\u000e\u0011M\b\"\u0003C}\u0003\t\u0007IQ\u0001C~\u0011!)\t!\u0001Q\u0001\u000e\u0011u\b\"CC\u0002\u0003\t\u0007IQAC\u0003\u0011!)Y!\u0001Q\u0001\u000e\u0015\u001d\u0001\"CC\u0007\u0003\t\u0007IQAC\b\u0011!))\"\u0001Q\u0001\u000e\u0015E\u0001\"CC\f\u0003\t\u0007IQAC\r\u0011!)y\"\u0001Q\u0001\u000e\u0015m\u0001\"CC\u0011\u0003\t\u0007IQAC\u0012\u0011!)I#\u0001Q\u0001\u000e\u0015\u0015\u0002\"CC\u0016\u0003\t\u0007IQAC\u0017\u0011!)\u0019$\u0001Q\u0001\u000e\u0015=\u0002\"CC\u001b\u0003\t\u0007IQAC\u001c\u0011!)i$\u0001Q\u0001\u000e\u0015e\u0002\"CC \u0003\t\u0007IQAC!\u0011!)9%\u0001Q\u0001\u000e\u0015\r\u0003\"CC%\u0003\t\u0007IQAC&\u0011!)\t&\u0001Q\u0001\u000e\u00155c!CB)\u0003A\u0005\u0019\u0013EB*\r\u0019\u0019Y&\u0001\u0002\u0004^!Q1qM#\u0003\u0006\u0004%)a!\u001b\t\u0015\r=XI!A!\u0002\u001b\u0019Y\u0007\u0003\u0006\u0004r\u0016\u0013)\u0019!C\u0003\u0007gD!b!>F\u0005\u0003\u0005\u000bQBB1\u0011\u00199W\t\"\u0001\u0004x\"9!qR#\u0005F\tE\u0005\u0002CC*\u0003\u0001\u0006i!\"\u0016\u0007\r\r=\u0014AAB9\u0011)\u0019)*\u0014B\u0001B\u000361q\u0013\u0005\u000b\u00073k%\u0011!Q!\u000e\u0005\u0015\u0004BCBN\u001b\n\u0005\t\u0015)\u0004\u0004\u001e\"Q1qT'\u0003\u0002\u0003\u0006ia!)\t\u000f\u001dl\u0005\u0015\"\u0003\u0004(\"1q-\u0014C\u0003\u0007gCqaa4N\t\u000b\u0019)\u0002C\u0004\u0004R6#)aa5\t\u0011\reW\n)C\u0007\u00077Dqa!:N\t\u000b\u001a9/A\u0004Qe>l\u0017n]3\u000b\u0005i[\u0016\u0001B5na2T!\u0001X/\u0002\u0015\r|gnY;se\u0016tGOC\u0001_\u0003\u0015\u00198-\u00197b!\t\u0001\u0017!D\u0001Z\u0005\u001d\u0001&o\\7jg\u0016\u001c\"!A2\u0011\u0005\u0011,W\"A/\n\u0005\u0019l&AB!osJ+g-\u0001\u0004=S:LGOP\u0002\u0001)\u0005y&\u0001\u0002'j].,2\u0001\u001cCX'\t\u0019Q\u000eE\u0002ombl\u0011a\u001c\u0006\u0003aF\fa!\u0019;p[&\u001c'B\u0001/s\u0015\t\u0019H/\u0001\u0003vi&d'\"A;\u0002\t)\fg/Y\u0005\u0003o>\u0014q\"\u0011;p[&\u001c'+\u001a4fe\u0016t7-\u001a\t\u0005s&!i+D\u0001\u0002\u00059!UMZ1vYR\u0004&o\\7jg\u0016,2\u0001`A\u0004'\u001dIQP`A\r\u0003?\u00012A\u001c<d!\u0015y\u0018\u0011AA\u0002\u001b\u0005Y\u0016BA1\\!\u0011\t)!a\u0002\r\u0001\u00119\u0011\u0011B\u0005C\u0002\u0005-!!\u0001+\u0012\t\u00055\u00111\u0003\t\u0004I\u0006=\u0011bAA\t;\n9aj\u001c;iS:<\u0007c\u00013\u0002\u0016%\u0019\u0011qC/\u0003\u0007\u0005s\u0017\u0010E\u0003\u0000\u00037\t\u0019!C\u0002\u0002\u001em\u0013aAR;ukJ,\u0007c\u00023\u0002\"\u0005\u0015\u0012qF\u0005\u0004\u0003Gi&!\u0003$v]\u000e$\u0018n\u001c82!\u0019\t9#a\u000b\u0002\u00045\u0011\u0011\u0011\u0006\u0006\u0003gvKA!!\f\u0002*\t\u0019AK]=\u0011\u0007\u0011\f\t$C\u0002\u00024u\u0013A!\u00168ji\u00069\u0011N\\5uS\u0006dG\u0003BA\u001d\u0003w\u0001B!_\u0005\u0002\u0004!1\u0011QG\u0006A\u0002\r$B!!\u000f\u0002@!9\u0011\u0011\t\u0007A\u0002\u0005\u0015\u0012A\u0002:fgVdG\u000f\u0006\u0002\u0002:\u0005)\u0011\r\u001d9msR!\u0011qFA%\u0011\u001d\tYE\u0004a\u0001\u0003K\t\u0001B]3t_24X\rZ\u0001\u0007MV$XO]3\u0016\u0005\u0005e\u0011!\u0003;sC:\u001chm\u001c:n+\u0011\t)&!\u0018\u0015\t\u0005]\u00131\u000e\u000b\u0005\u00033\n\t\u0007E\u0003\u0000\u00037\tY\u0006\u0005\u0003\u0002\u0006\u0005uCaBA0!\t\u0007\u00111\u0002\u0002\u0002'\"9\u00111\r\tA\u0004\u0005\u0015\u0014\u0001C3yK\u000e,Ho\u001c:\u0011\u0007}\f9'C\u0002\u0002jm\u0013\u0001#\u0012=fGV$\u0018n\u001c8D_:$X\r\u001f;\t\u000f\u00055\u0004\u00031\u0001\u0002p\u0005\ta\rE\u0004e\u0003C\t)#!\u001d\u0011\r\u0005\u001d\u00121FA.\u00035!(/\u00198tM>\u0014XnV5uQV!\u0011qOA@)\u0011\tI(a!\u0015\t\u0005m\u0014\u0011\u0011\t\u0006\u007f\u0006m\u0011Q\u0010\t\u0005\u0003\u000b\ty\bB\u0004\u0002`E\u0011\r!a\u0003\t\u000f\u0005\r\u0014\u0003q\u0001\u0002f!9\u0011QN\tA\u0002\u0005\u0015\u0005c\u00023\u0002\"\u0005\u0015\u00121P\u0001\bu&\u0004x+\u001b;i+\u0019\tY)!*\u0002\u0016R!\u0011QRAU)\u0011\ty)a'\u0015\t\u0005E\u0015\u0011\u0014\t\u0006\u007f\u0006m\u00111\u0013\t\u0005\u0003\u000b\t)\nB\u0004\u0002\u0018J\u0011\r!a\u0003\u0003\u0003ICq!a\u0019\u0013\u0001\b\t)\u0007C\u0004\u0002nI\u0001\r!!(\u0011\u0013\u0011\fy*a\u0001\u0002$\u0006M\u0015bAAQ;\nIa)\u001e8di&|gN\r\t\u0005\u0003\u000b\t)\u000bB\u0004\u0002(J\u0011\r!a\u0003\u0003\u0003UCq!a+\u0013\u0001\u0004\ti+\u0001\u0003uQ\u0006$\b#B@\u0002\u001c\u0005\r\u0016a\u00024pe\u0016\f7\r[\u000b\u0005\u0003g\u000by\f\u0006\u0003\u00026\u0006eF\u0003BA\u0018\u0003oCq!a\u0019\u0014\u0001\b\t)\u0007C\u0004\u0002nM\u0001\r!a/\u0011\u000f\u0011\f\t#a\u0001\u0002>B!\u0011QAA`\t\u001d\t9k\u0005b\u0001\u0003\u0017\tqA\u001a7bi6\u000b\u0007/\u0006\u0003\u0002F\u00065G\u0003BAd\u0003#$B!!3\u0002PB)q0a\u0007\u0002LB!\u0011QAAg\t\u001d\ty\u0006\u0006b\u0001\u0003\u0017Aq!a\u0019\u0015\u0001\b\t)\u0007C\u0004\u0002nQ\u0001\r!a5\u0011\u000f\u0011\f\t#a\u0001\u0002J\u0006\u0019Q.\u00199\u0016\t\u0005e\u0017\u0011\u001d\u000b\u0005\u00037\f)\u000f\u0006\u0003\u0002^\u0006\r\b#B@\u0002\u001c\u0005}\u0007\u0003BA\u0003\u0003C$q!a\u0018\u0016\u0005\u0004\tY\u0001C\u0004\u0002dU\u0001\u001d!!\u001a\t\u000f\u00055T\u00031\u0001\u0002hB9A-!\t\u0002\u0004\u0005}\u0017A\u00024jYR,'\u000f\u0006\u0003\u0002n\u0006EH\u0003BA\r\u0003_Dq!a\u0019\u0017\u0001\b\t)\u0007C\u0004\u0002tZ\u0001\r!!>\u0002\u0003A\u0004r\u0001ZA\u0011\u0003\u0007\t9\u0010E\u0002e\u0003sL1!a?^\u0005\u001d\u0011un\u001c7fC:\fqaY8mY\u0016\u001cG/\u0006\u0003\u0003\u0002\t%A\u0003\u0002B\u0002\u0005\u001b!BA!\u0002\u0003\fA)q0a\u0007\u0003\bA!\u0011Q\u0001B\u0005\t\u001d\tyf\u0006b\u0001\u0003\u0017Aq!a\u0019\u0018\u0001\b\t)\u0007C\u0004\u0003\u0010]\u0001\rA!\u0005\u0002\u0005A4\u0007c\u00023\u0003\u0014\u0005\r!qA\u0005\u0004\u0005+i&a\u0004)beRL\u0017\r\u001c$v]\u000e$\u0018n\u001c8\u0002\u0017I,7m\u001c<fe^KG\u000f[\u000b\u0005\u00057\u0011\u0019\u0003\u0006\u0003\u0003\u001e\t%B\u0003\u0002B\u0010\u0005O\u0001Ra`A\u000e\u0005C\u0001B!!\u0002\u0003$\u00119\u0011q\u0015\rC\u0002\t\u0015\u0012\u0003BA\u0002\u0003'Aq!a\u0019\u0019\u0001\b\t)\u0007C\u0004\u0003\u0010a\u0001\rAa\u000b\u0011\u000f\u0011\u0014\u0019B!\f\u0003 A!!q\u0006B \u001d\u0011\u0011\tDa\u000f\u000f\t\tM\"\u0011H\u0007\u0003\u0005kQ1Aa\u000ei\u0003\u0019a$o\\8u}%\ta,C\u0002\u0003>u\u000bq\u0001]1dW\u0006<W-\u0003\u0003\u0003B\t\r#!\u0003+ie><\u0018M\u00197f\u0015\r\u0011i$X\u0001\be\u0016\u001cwN^3s+\u0011\u0011IE!\u0015\u0015\t\t-#Q\u000b\u000b\u0005\u0005\u001b\u0012\u0019\u0006E\u0003\u0000\u00037\u0011y\u0005\u0005\u0003\u0002\u0006\tECaBAT3\t\u0007!Q\u0005\u0005\b\u0003GJ\u00029AA3\u0011\u001d\u0011y!\u0007a\u0001\u0005/\u0002r\u0001\u001aB\n\u0005[\u0011y%A\u0003nCB$v.\u0006\u0003\u0003^\t\rD\u0003\u0002B0\u0005K\u0002Ra`A\u000e\u0005C\u0002B!!\u0002\u0003d\u00119\u0011q\f\u000eC\u0002\u0005-\u0001b\u0002B45\u0001\u000f!\u0011N\u0001\u0004i\u0006<\u0007C\u0002B6\u0005c\u0012\t'\u0004\u0002\u0003n)\u0019!qN/\u0002\u000fI,g\r\\3di&!!1\u000fB7\u0005!\u0019E.Y:t)\u0006<\u0017AC8o\u0007>l\u0007\u000f\\3uKV!!\u0011\u0010BD)\u0011\u0011YHa \u0015\t\u0005=\"Q\u0010\u0005\b\u0003GZ\u00029AA3\u0011\u001d\u0011\ti\u0007a\u0001\u0005\u0007\u000bAAZ;oGB9A-!\t\u0002&\t\u0015\u0005\u0003BA\u0003\u0005\u000f#q!a*\u001c\u0005\u0004\tY!\u0001\u0004gC&dW\rZ\u000b\u0003\u0005\u001b\u0003Ra`A\u000e\u0005[\t\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0005'\u0003BA!&\u0003\u001e:!!q\u0013BM!\r\u0011\u0019$X\u0005\u0004\u00057k\u0016A\u0002)sK\u0012,g-\u0003\u0003\u0003 \n\u0005&AB*ue&twMC\u0002\u0003\u001cvC3!\bBS!\u0011\u00119K!,\u000e\u0005\t%&b\u0001BV;\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\t=&\u0011\u0016\u0002\bi\u0006LGN]3d\u0003%!(/_!xC&$\b\u0007\u0006\u0003\u0002&\tU\u0006b\u0002B\\=\u0001\u0007!\u0011X\u0001\u0007CRlun\u001d;\u0011\t\tm&\u0011Y\u0007\u0003\u0005{S1Aa0\\\u0003!!WO]1uS>t\u0017\u0002\u0002Bb\u0005{\u0013\u0001\u0002R;sCRLwN\\\u0001\u0006e\u0016\fG-\u001f\u000b\u0005\u0005\u0013\u00149\u000e\u0006\u0003\u0003L\n5W\"A\u0005\t\u000f\t=w\u0004q\u0001\u0003R\u00061\u0001/\u001a:nSR\u00042a Bj\u0013\r\u0011)n\u0017\u0002\t\u0007\u0006t\u0017i^1ji\"9!qW\u0010A\u0002\te\u0006&B\u0010\u0003\\\nE\b#\u00023\u0003^\n\u0005\u0018b\u0001Bp;\n1A\u000f\u001b:poN\u0004BAa9\u0003l:!!Q\u001dBu\u001d\u0011\u0011\tDa:\n\u0005qk\u0016b\u0001B\u001f7&!!Q\u001eBx\u0005A!\u0016.\\3pkR,\u0005pY3qi&|gNC\u0002\u0003>m\u001b#A!9)\u000b}\u0011)P!@\u0011\u000b\u0011\u0014iNa>\u0011\t\t=\"\u0011`\u0005\u0005\u0005w\u0014\u0019E\u0001\u000bJ]R,'O];qi\u0016$W\t_2faRLwN\\\u0012\u0003\u0005o$Ba!\u0001\u0004\u0006Q!\u00111AB\u0002\u0011\u001d\u0011y\r\ta\u0002\u0005#DqAa.!\u0001\u0004\u0011I\fK\u0003!\u0007\u0013\u0019\t\u0002E\u0003e\u0005;\u001cY\u0001\u0005\u0003\u00030\r5\u0011\u0002BB\b\u0005\u0007\u0012\u0011\"\u0012=dKB$\u0018n\u001c8$\u0005\r-\u0011aC5t\u0007>l\u0007\u000f\\3uK\u0012,\"!a>\u0002\u000bY\fG.^3\u0016\u0005\rm\u0001#\u00023\u0004\u001e\u0005\u0015\u0012bAB\u0010;\n1q\n\u001d;j_:\faA^1mk\u0016\u0004TCAA\u0013Q\r\u0019#QU\u0001\fiJL8i\\7qY\u0016$X\r\u0006\u0003\u0002x\u000e-\u0002bBB\fI\u0001\u0007\u0011QE\u0001\riJL8i\\7qY\u0016$X\r\r\u000b\u0007\u0003o\u001c\td!\u000e\t\r\rMR\u00051\u0001d\u0003\u0015\u0019H/\u0019;f\u0011\u001d\tY%\na\u0001\u0003KA3!\nBS\u00031\u0019w.\u001c9mKR,w+\u001b;i)\u0011\u0011Ym!\u0010\t\u000f\r}b\u00051\u0001\u0002\u001a\u0005)q\u000e\u001e5fe\u00061B-[:qCR\u001c\u0007n\u0014:BI\u0012\u001c\u0015\r\u001c7cC\u000e\\7/\u0006\u0003\u0004F\r%CCBB$\t\u000f!I\u0001\u0005\u0003\u0002\u0006\r%CaBB&O\t\u00071Q\n\u0002\u0002\u0007F!\u0011QBB(!\u0011IH)a\u0001\u0003\u0013\r\u000bG\u000e\u001c2bG.\u001cX\u0003BB+\u0007/\u001a\"\u0001R2\u0005\u0011\u0005%A\t#b\u0001\u0003\u0017I3\u0001R#N\u00055i\u0015M\\=DC2d'-Y2lgV!1qLB3'\u0011)5m!\u0019\u0011\te$51\r\t\u0005\u0003\u000b\u0019)\u0007\u0002\u0005\u0002\n\u0015C)\u0019AA\u0006\u0003\u00151\u0017N]:u+\t\u0019Y\u0007\r\u0003\u0004n\r-\bCB=N\u0007G\u001aIO\u0001\bUe\u0006t7OZ8s[\u0006$\u0018n\u001c8\u0016\r\rM4qPB='%i5QOB>\u0007\u0007\u001by\t\u0005\u0003z\u0013\r]\u0004\u0003BA\u0003\u0007s\"q!!\u0003N\u0005\u0004\tY\u0001\u0005\u0003z\t\u000eu\u0004\u0003BA\u0003\u0007\u007f\"\u0001b!!N\u0011\u000b\u0007\u00111\u0002\u0002\u0002\rB!1QQBF\u001b\t\u00199IC\u0002\u0004\nR\fA\u0001\\1oO&!1QRBD\u0005!\u0011VO\u001c8bE2,\u0007cA@\u0004\u0012&\u001911S.\u0003\u0013\t\u000bGo\u00195bE2,\u0017\u0001B0gk:\u0004r\u0001ZA\u0011\u0003'\t\u0019\"A\u0002`K\u000e\fAaX1sOB1\u0011qEA\u0016\u0007{\naa\u0018=g_Jl\u0007c\u00013\u0004$&\u00191QU/\u0003\u0007%sG\u000f\u0006\u0006\u0004*\u000e-6QVBX\u0007c\u0003b!_'\u0004~\r]\u0004bBBK%\u0002\u00071q\u0013\u0005\b\u00073\u0013\u0006\u0019AA3\u0011\u001d\u0019YJ\u0015a\u0001\u0007;Cqaa(S\u0001\u0004\u0019\t\u000b\u0006\u0005\u0004*\u000eU6\u0011XBf\u0011\u001d\u00199l\u0015a\u0001\u0007C\u000bQ\u0001\u001f4pe6Dq!!\u001cT\u0001\u0004\u0019Y\f\r\u0004\u0004>\u000e\u00057q\u0019\t\bI\u0006\u00052qXBc!\u0011\t)a!1\u0005\u0019\r\r7\u0011XA\u0001\u0002\u0003\u0015\t!a\u0003\u0003\t}#3'\r\t\u0005\u0003\u000b\u00199\r\u0002\u0007\u0004J\u000ee\u0016\u0011!A\u0001\u0006\u0003\tYA\u0001\u0003`IM\u0012\u0004bBBg'\u0002\u0007\u0011QM\u0001\u0003K\u000e\fACY3oK\u001aLGo\u001d$s_6\u0014\u0015\r^2iS:<\u0017aD:vE6LGoV5uQZ\u000bG.^3\u0015\t\rU7q[\u0007\u0002\u001b\"9\u00111J+A\u0002\ru\u0015!\u00045b]\u0012dWMR1jYV\u0014X\r\u0006\u0004\u00020\ru7\u0011\u001d\u0005\b\u0007?4\u0006\u0019\u0001B\u0017\u0003\u0005!\bbBBr-\u0002\u0007\u0011QM\u0001\u0002K\u0006\u0019!/\u001e8\u0015\u0005\u0005=\u0002\u0003BA\u0003\u0007W$1b!<H\u0003\u0003\u0005\tQ!\u0001\u0002\f\t!q\fJ\u001a1\u0003\u00191\u0017N]:uA\u0005!!/Z:u+\t\u0019\t'A\u0003sKN$\b\u0005\u0006\u0004\u0004z\u000emHQ\u0001\t\u0005s\u0016\u001b\u0019\u0007C\u0004\u0004h)\u0003\ra!@1\t\r}H1\u0001\t\u0007s6\u001b\u0019\u0007\"\u0001\u0011\t\u0005\u0015A1\u0001\u0003\r\u0007[\u001cY0!A\u0001\u0002\u000b\u0005\u00111\u0002\u0005\b\u0007cT\u0005\u0019AB1\u0011\u0019\u0019\u0019d\na\u0001G\"9A1B\u0014A\u0002\r\u001d\u0013!C2bY2\u0014\u0017mY6tQ\r9#QU\u0001\u0010G>t7-\u0019;DC2d'-Y2lgR11q\nC\n\t/Aq\u0001\"\u0006)\u0001\u0004\u0019y%\u0001\u0003mK\u001a$\bb\u0002C\rQ\u0001\u00071qJ\u0001\u0006e&<\u0007\u000e\u001e\u0015\u0004Q\t\u0015FCBA\u0018\t?!\t\u0003C\u0004\u0005\f%\u0002\raa\u0014\t\u000f\u0005-\u0013\u00061\u0001\u0002&!\u001a\u0011F!*\u0002\u00151Lgn\u001b*p_R|e\r\u0006\u0004\u00020\u0011%BQ\u0006\u0005\b\tWQ\u0003\u0019AA\u001d\u0003\u0019!\u0018M]4fi\"9Aq\u0006\u0016A\u0002\u0011E\u0012\u0001\u00027j].\u0004B!_\u0002\u0002\u0004!\u001a!F!*\u0002\rUtG.\u001b8l)\u0011\ty\u0003\"\u000f\t\u000f\u0005-3\u00061\u0001\u0002&!\u001a1F!*\u0002\u0017]\u0014\u0018\u000e^3PE*,7\r\u001e\u000b\u0005\u0003_!\t\u0005C\u0004\u0005D1\u0002\r\u0001\"\u0012\u0002\u0007=,H\u000f\u0005\u0003\u0005H\u00115SB\u0001C%\u0015\r!Y\u0005^\u0001\u0003S>LA\u0001b\u0014\u0005J\t\u0011rJ\u00196fGR|U\u000f\u001e9viN#(/Z1nQ\u0015aC1\u000bC.!\u0015!'Q\u001cC+!\u0011!9\u0005b\u0016\n\t\u0011eC\u0011\n\u0002\f\u0013>+\u0005pY3qi&|g.M\u0004\u001f\u0005'#i\u0006\" 2\u0013\r\"y\u0006\"\u001a\u0005t\u0011\u001dT\u0003\u0002C1\tG*\"Aa%\u0005\u000f\u0005%\u0001A1\u0001\u0005n%!Aq\rC5\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%c)\u0019A1N/\u0002\rQD'o\\<t#\u0011\ti\u0001b\u001c\u0011\t\u0011E$q\b\b\u0004I\nm\u0012'C\u0012\u0005v\u0011]D\u0011\u0010C6\u001d\r!GqO\u0005\u0004\tWj\u0016'\u0002\u0012e;\u0012m$!B:dC2\f\u0017g\u0001\u0014\u0005V\u0005Q!/Z1e\u001f\nTWm\u0019;\u0015\t\u0005=B1\u0011\u0005\b\t\u000bk\u0003\u0019\u0001CD\u0003\tIg\u000e\u0005\u0003\u0005H\u0011%\u0015\u0002\u0002CF\t\u0013\u0012\u0011c\u00142kK\u000e$\u0018J\u001c9viN#(/Z1nQ\u0015iC1\u000bCHc\u001dq\"1\u0013CI\t/\u000b\u0014b\tC0\tK\"\u0019\nb\u001a2\u0013\r\")\bb\u001e\u0005\u0016\u0012-\u0014'\u0002\u0012e;\u0012m\u0014g\u0001\u0014\u0005V!*Q\u0006b'\u0005$B)AM!8\u0005\u001eB!1Q\u0011CP\u0013\u0011!\tka\"\u0003-\rc\u0017m]:O_R4u.\u001e8e\u000bb\u001cW\r\u001d;j_:\ftA\bBJ\tK#Y+M\u0005$\t?\")\u0007b*\u0005hEJ1\u0005\"\u001e\u0005x\u0011%F1N\u0019\u0006E\u0011lF1P\u0019\u0004M\u0011u\u0005\u0003BA\u0003\t_#q!!\u0003\u0004\u0005\u0004\tY!\u0001\u0002u_R!AQ\u0017C\\!\u0011I8\u0001\",\t\r\u0011EV\u00011\u0001y\u0003\u001d\u0001(o\\7jg\u0016$2\u0001\u001fC_\u0011\u0019!yL\u0002a\u0001q\u0006)qn\u001e8fe\u0006q3oY1mC\u0012\u001awN\\2veJ,g\u000e\u001e\u0013j[BdG\u0005\u0015:p[&\u001cX\r\n'j].$CeY8naJ,7o]3e)\u001dAHQ\u0019Ce\t\u0017Da\u0001b2\b\u0001\u0004A\u0018aB2veJ,g\u000e\u001e\u0005\u0007\tW9\u0001\u0019\u0001=\t\r\u0011}v\u00011\u0001yQ\r9Aq\u001a\t\u0004I\u0012E\u0017b\u0001Cj;\n1\u0011N\u001c7j]\u0016D3a\u0002BS\u0003\u001d\u0011Xm]8mm\u0016,B\u0001b7\u0005bR!AQ\u001cCr!\u0019\t9#a\u000b\u0005`B!\u0011Q\u0001Cq\t\u001d\tI\u0001\u0003b\u0001\u0003\u0017Aqaa\u0006\t\u0001\u0004!i.\u0001\u0006YM>\u0014Xn\u00188p_B,\"\u0001\";\u0010\u0005\u0011-X$\u0001\u0001\u0002\u0017a3wN]7`]>|\u0007\u000fI\u0001\n1\u001a|'/\\0nCB,\"\u0001b=\u0010\u0005\u0011UX$A\u0001\u0002\u0015a3wN]7`[\u0006\u0004\b%A\u0007YM>\u0014Xn\u00184mCRl\u0015\r]\u000b\u0003\t{|!\u0001b@\u001e\u0003\t\ta\u0002\u00174pe6|f\r\\1u\u001b\u0006\u0004\b%A\bYM>\u0014Xn\u0018;sC:\u001chm\u001c:n+\t)9a\u0004\u0002\u0006\nu\t1!\u0001\tYM>\u0014Xn\u0018;sC:\u001chm\u001c:nA\u0005\u0019\u0002LZ8s[~#(/\u00198tM>\u0014XnV5uQV\u0011Q\u0011C\b\u0003\u000b'i\u0012\u0001B\u0001\u00151\u001a|'/\\0ue\u0006t7OZ8s[^KG\u000f\u001b\u0011\u0002\u001ba3wN]7`M>\u0014X-Y2i+\t)Yb\u0004\u0002\u0006\u001eu\tQ!\u0001\bYM>\u0014Xn\u00184pe\u0016\f7\r\u001b\u0011\u0002!a3wN]7`_:\u001cu.\u001c9mKR,WCAC\u0013\u001f\t)9#H\u0001\u0007\u0003EAfm\u001c:n?>t7i\\7qY\u0016$X\rI\u0001\u000e1\u001a|'/\\0sK\u000e|g/\u001a:\u0016\u0005\u0015=rBAC\u0019;\u00059\u0011A\u0004-g_JlwL]3d_Z,'\u000fI\u0001\u00121\u001a|'/\\0sK\u000e|g/\u001a:XSRDWCAC\u001d\u001f\t)Y$H\u0001\t\u0003IAfm\u001c:n?J,7m\u001c<fe^KG\u000f\u001b\u0011\u0002\u0019a3wN]7`M&dG/\u001a:\u0016\u0005\u0015\rsBAC#;\u0005I\u0011!\u0004-g_JlwLZ5mi\u0016\u0014\b%A\u0007YM>\u0014XnX2pY2,7\r^\u000b\u0003\u000b\u001bz!!b\u0014\u001e\u0003)\ta\u0002\u00174pe6|6m\u001c7mK\u000e$\b%\u0001\u0003O_>\u0004\bCB=N\u0003\u001b\ti\u0001"
)
public final class Promise {
   public static int Xform_collect() {
      return Promise$.MODULE$.Xform_collect();
   }

   public static int Xform_filter() {
      return Promise$.MODULE$.Xform_filter();
   }

   public static int Xform_recoverWith() {
      return Promise$.MODULE$.Xform_recoverWith();
   }

   public static int Xform_recover() {
      return Promise$.MODULE$.Xform_recover();
   }

   public static int Xform_onComplete() {
      return Promise$.MODULE$.Xform_onComplete();
   }

   public static int Xform_foreach() {
      return Promise$.MODULE$.Xform_foreach();
   }

   public static int Xform_transformWith() {
      return Promise$.MODULE$.Xform_transformWith();
   }

   public static int Xform_transform() {
      return Promise$.MODULE$.Xform_transform();
   }

   public static int Xform_flatMap() {
      return Promise$.MODULE$.Xform_flatMap();
   }

   public static int Xform_map() {
      return Promise$.MODULE$.Xform_map();
   }

   public static int Xform_noop() {
      return Promise$.MODULE$.Xform_noop();
   }

   public static final class Link extends AtomicReference {
      public final DefaultPromise promise(final DefaultPromise owner) {
         DefaultPromise c = (DefaultPromise)this.get();
         DefaultPromise scala$concurrent$impl$Promise$Link$$compressed_target = c;
         DefaultPromise scala$concurrent$impl$Promise$Link$$compressed_current = c;

         while(true) {
            Object scala$concurrent$impl$Promise$Link$$compressed_value = scala$concurrent$impl$Promise$Link$$compressed_target.get();
            if (scala$concurrent$impl$Promise$Link$$compressed_value instanceof Callbacks) {
               if (this.compareAndSet(scala$concurrent$impl$Promise$Link$$compressed_current, scala$concurrent$impl$Promise$Link$$compressed_target)) {
                  return scala$concurrent$impl$Promise$Link$$compressed_target;
               }

               DefaultPromise var10000 = (DefaultPromise)this.get();
               scala$concurrent$impl$Promise$Link$$compressed_target = scala$concurrent$impl$Promise$Link$$compressed_target;
               scala$concurrent$impl$Promise$Link$$compressed_current = var10000;
            } else {
               if (!(scala$concurrent$impl$Promise$Link$$compressed_value instanceof Link)) {
                  owner.unlink((Try)scala$concurrent$impl$Promise$Link$$compressed_value);
                  return owner;
               }

               scala$concurrent$impl$Promise$Link$$compressed_target = (DefaultPromise)((Link)scala$concurrent$impl$Promise$Link$$compressed_value).get();
               scala$concurrent$impl$Promise$Link$$compressed_current = scala$concurrent$impl$Promise$Link$$compressed_current;
            }
         }
      }

      public final DefaultPromise scala$concurrent$impl$Promise$Link$$compressed(final DefaultPromise current, final DefaultPromise target, final DefaultPromise owner) {
         while(true) {
            Object value = target.get();
            if (value instanceof Callbacks) {
               if (this.compareAndSet(current, target)) {
                  return target;
               }

               DefaultPromise var10000 = (DefaultPromise)this.get();
               owner = owner;
               target = target;
               current = var10000;
            } else {
               if (!(value instanceof Link)) {
                  owner.unlink((Try)value);
                  return owner;
               }

               DefaultPromise var10001 = (DefaultPromise)((Link)value).get();
               owner = owner;
               target = var10001;
               current = current;
            }
         }
      }

      public Link(final DefaultPromise to) {
         super(to);
      }
   }

   public static class DefaultPromise extends AtomicReference implements scala.concurrent.Promise, Future, Function1 {
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

      public Future transform(final Function1 s, final Function1 f, final ExecutionContext executor) {
         return Future.transform$(this, s, f, executor);
      }

      public Future flatten(final $less$colon$less ev) {
         return Future.flatten$(this, ev);
      }

      public final Future withFilter(final Function1 p, final ExecutionContext executor) {
         return Future.withFilter$(this, p, executor);
      }

      public Future zip(final Future that) {
         return Future.zip$(this, that);
      }

      public Future fallbackTo(final Future that) {
         return Future.fallbackTo$(this, that);
      }

      public Future andThen(final PartialFunction pf, final ExecutionContext executor) {
         return Future.andThen$(this, pf, executor);
      }

      public scala.concurrent.Promise complete(final Try result) {
         return scala.concurrent.Promise.complete$(this, result);
      }

      /** @deprecated */
      public final scala.concurrent.Promise tryCompleteWith(final Future other) {
         return scala.concurrent.Promise.tryCompleteWith$(this, other);
      }

      public scala.concurrent.Promise success(final Object value) {
         return scala.concurrent.Promise.success$(this, value);
      }

      public boolean trySuccess(final Object value) {
         return scala.concurrent.Promise.trySuccess$(this, value);
      }

      public scala.concurrent.Promise failure(final Throwable cause) {
         return scala.concurrent.Promise.failure$(this, cause);
      }

      public boolean tryFailure(final Throwable cause) {
         return scala.concurrent.Promise.tryFailure$(this, cause);
      }

      public final void apply(final Try resolved) {
         this.tryComplete0(this.get(), resolved);
      }

      public final Future future() {
         return this;
      }

      public final Future transform(final Function1 f, final ExecutionContext executor) {
         return (Future)this.dispatchOrAddCallbacks(this.get(), new Transformation(3, f, executor));
      }

      public final Future transformWith(final Function1 f, final ExecutionContext executor) {
         return (Future)this.dispatchOrAddCallbacks(this.get(), new Transformation(4, f, executor));
      }

      public final Future zipWith(final Future that, final Function2 f, final ExecutionContext executor) {
         Object state = this.get();
         if (state instanceof Try) {
            if (((Try)state).isFailure()) {
               return this;
            } else {
               Success var10000 = (Success)state;
               if ((Success)state == null) {
                  throw null;
               } else {
                  Success get_this = var10000;
                  var10000 = (Success)get_this.value();
                  Object var11 = null;
                  Object l = var10000;
                  return that.map((r) -> f.apply(l, r), executor);
               }
            }
         } else {
            AtomicReference buffer = new AtomicReference();
            DefaultPromise zipped = new DefaultPromise();
            Function1 thisF = (x0$1) -> {
               $anonfun$zipWith$2(buffer, zipped, f, x0$1);
               return BoxedUnit.UNIT;
            };
            Function1 thatF = (x0$2) -> {
               $anonfun$zipWith$3(buffer, zipped, f, x0$2);
               return BoxedUnit.UNIT;
            };
            this.dispatchOrAddCallbacks(state, new Transformation(6, thisF, executor));
            that.onComplete(thatF, executor);
            return zipped;
         }
      }

      public final void foreach(final Function1 f, final ExecutionContext executor) {
         Object state = this.get();
         if (!(state instanceof Failure)) {
            this.dispatchOrAddCallbacks(state, new Transformation(5, f, executor));
         }
      }

      public final Future flatMap(final Function1 f, final ExecutionContext executor) {
         Object state = this.get();
         return (Future)(!(state instanceof Failure) ? (Future)this.dispatchOrAddCallbacks(state, new Transformation(2, f, executor)) : this);
      }

      public final Future map(final Function1 f, final ExecutionContext executor) {
         Object state = this.get();
         return (Future)(!(state instanceof Failure) ? (Future)this.dispatchOrAddCallbacks(state, new Transformation(1, f, executor)) : this);
      }

      public final Future filter(final Function1 p, final ExecutionContext executor) {
         Object state = this.get();
         return (Future)(!(state instanceof Failure) ? (Future)this.dispatchOrAddCallbacks(state, new Transformation(9, p, executor)) : this);
      }

      public final Future collect(final PartialFunction pf, final ExecutionContext executor) {
         Object state = this.get();
         return (Future)(!(state instanceof Failure) ? (Future)this.dispatchOrAddCallbacks(state, new Transformation(10, pf, executor)) : this);
      }

      public final Future recoverWith(final PartialFunction pf, final ExecutionContext executor) {
         Object state = this.get();
         return (Future)(!(state instanceof Success) ? (Future)this.dispatchOrAddCallbacks(state, new Transformation(8, pf, executor)) : this);
      }

      public final Future recover(final PartialFunction pf, final ExecutionContext executor) {
         Object state = this.get();
         return (Future)(!(state instanceof Success) ? (Future)this.dispatchOrAddCallbacks(state, new Transformation(7, pf, executor)) : this);
      }

      public final Future mapTo(final ClassTag tag) {
         return (Future)(!(this.get() instanceof Failure) ? Future.mapTo$(this, tag) : this);
      }

      public final void onComplete(final Function1 func, final ExecutionContext executor) {
         this.dispatchOrAddCallbacks(this.get(), new Transformation(6, func, executor));
      }

      public final Future failed() {
         return !(this.get() instanceof Success) ? Future.failed$(this) : Future$.MODULE$.failedFailureFuture();
      }

      public final String toString() {
         while(true) {
            Object state = this.get();
            if (state instanceof Try) {
               return (new StringBuilder(8)).append("Future(").append(state).append(")").toString();
            }

            if (!(state instanceof Link)) {
               return "Future(<not completed>)";
            }

            this = ((Link)state).promise(this);
         }
      }

      private final Try tryAwait0(final Duration atMost) {
         if (atMost != Duration$.MODULE$.Undefined()) {
            Try v = this.value0();
            if (v != null) {
               return v;
            } else {
               FiniteDuration $less$eq_that = Duration$.MODULE$.Zero();
               if (atMost == null) {
                  throw null;
               } else {
                  boolean var10000 = Ordered.$less$eq$(atMost, $less$eq_that);
                  $less$eq_that = null;
                  Try var7;
                  if (var10000) {
                     var7 = null;
                  } else {
                     CompletionLatch l = new CompletionLatch();
                     this.onComplete(l, ExecutionContext.parasitic$.MODULE$);
                     if (atMost.isFinite()) {
                        l.tryAcquireSharedNanos(1, atMost.toNanos());
                     } else {
                        l.acquireSharedInterruptibly(1);
                     }

                     var7 = l.result();
                  }

                  Try r = var7;
                  if (r != null) {
                     return r;
                  } else {
                     throw new TimeoutException((new StringBuilder(25)).append("Future timed out after [").append(atMost).append("]").toString());
                  }
               }
            }
         } else {
            throw new IllegalArgumentException("Cannot wait for Undefined duration of time");
         }
      }

      public final DefaultPromise ready(final Duration atMost, final CanAwait permit) throws TimeoutException, InterruptedException {
         this.tryAwait0(atMost);
         return this;
      }

      public final Object result(final Duration atMost, final CanAwait permit) throws Exception {
         return this.tryAwait0(atMost).get();
      }

      public final boolean isCompleted() {
         return this.value0() != null;
      }

      public final Option value() {
         return Option$.MODULE$.apply(this.value0());
      }

      private final Try value0() {
         while(true) {
            Object state = this.get();
            if (state instanceof Try) {
               return (Try)state;
            }

            if (!(state instanceof Link)) {
               return null;
            }

            this = ((Link)state).promise(this);
         }
      }

      public final boolean tryComplete(final Try value) {
         Object state = this.get();
         return state instanceof Try ? false : this.tryComplete0(state, Promise$.MODULE$.scala$concurrent$impl$Promise$$resolve(value));
      }

      public final boolean tryComplete0(final Object state, final Try resolved) {
         while(true) {
            if (state instanceof Callbacks) {
               if (this.compareAndSet(state, resolved)) {
                  if (state != Promise$.scala$concurrent$impl$Promise$$Noop) {
                     this.submitWithValue((Callbacks)state, resolved);
                  }

                  return true;
               }

               Object var10000 = this.get();
               resolved = resolved;
               state = var10000;
            } else {
               if (state instanceof Link) {
                  DefaultPromise p = ((Link)state).promise(this);
                  if (p != this) {
                     Object var10001 = p.get();
                     resolved = resolved;
                     state = var10001;
                     this = p;
                     continue;
                  }

                  return false;
               }

               return false;
            }
         }
      }

      public final DefaultPromise completeWith(final Future other) {
         if (other != this) {
            Object state = this.get();
            if (!(state instanceof Try)) {
               Try var10000;
               if (other instanceof DefaultPromise) {
                  var10000 = ((DefaultPromise)other).value0();
               } else {
                  Option var8 = other.value();
                  $eq$colon$eq orNull_ev = $less$colon$less$.MODULE$.refl();
                  if (var8 == null) {
                     throw null;
                  }

                  Option orNull_this = var8;
                  var8 = (Option)(orNull_this.isEmpty() ? (($less$colon$less)orNull_ev).apply((Object)null) : orNull_this.get());
                  Object var6 = null;
                  Object var7 = null;
                  var10000 = (Try)var8;
               }

               Try resolved = var10000;
               if (resolved != null) {
                  this.tryComplete0(state, resolved);
               } else {
                  other.onComplete(this, ExecutionContext.parasitic$.MODULE$);
               }
            }
         }

         return this;
      }

      private final Callbacks dispatchOrAddCallbacks(final Object state, final Callbacks callbacks) {
         while(!(state instanceof Try)) {
            if (state instanceof Callbacks) {
               if (this.compareAndSet(state, state != Promise$.scala$concurrent$impl$Promise$$Noop ? this.concatCallbacks(callbacks, (Callbacks)state) : callbacks)) {
                  return callbacks;
               }

               Object var10000 = this.get();
               callbacks = callbacks;
               state = var10000;
            } else {
               DefaultPromise p = ((Link)state).promise(this);
               Object var10001 = p.get();
               callbacks = callbacks;
               state = var10001;
               this = p;
            }
         }

         this.submitWithValue(callbacks, (Try)state);
         return callbacks;
      }

      private final Callbacks concatCallbacks(final Callbacks left, final Callbacks right) {
         while(!(left instanceof Transformation)) {
            ManyCallbacks m = (ManyCallbacks)left;
            Callbacks var10000 = m.rest();
            right = new ManyCallbacks(m.first(), right);
            left = var10000;
         }

         return new ManyCallbacks((Transformation)left, right);
      }

      private final void submitWithValue(final Callbacks callbacks, final Try resolved) {
         while(callbacks instanceof ManyCallbacks) {
            ManyCallbacks m = (ManyCallbacks)callbacks;
            m.first().submitWithValue(resolved);
            Callbacks var10000 = m.rest();
            resolved = resolved;
            callbacks = var10000;
         }

         ((Transformation)callbacks).submitWithValue(resolved);
      }

      public final void linkRootOf(final DefaultPromise target, final Link link) {
         while(true) {
            if (this != target) {
               Object state = this.get();
               if (state instanceof Try) {
                  if (!target.tryComplete0(target.get(), (Try)state)) {
                     throw new IllegalStateException("Cannot link completed promises together");
                  }
               } else {
                  if (!(state instanceof Callbacks)) {
                     DefaultPromise var10000 = ((Link)state).promise(this);
                     link = link;
                     target = target;
                     this = var10000;
                     continue;
                  }

                  Link l = link != null ? link : new Link(target);
                  DefaultPromise p = l.promise(this);
                  if (this == p || !this.compareAndSet(state, l)) {
                     link = l;
                     target = p;
                     continue;
                  }

                  if (state != Promise$.scala$concurrent$impl$Promise$$Noop) {
                     p.dispatchOrAddCallbacks(p.get(), (Callbacks)state);
                     return;
                  }
               }
            }

            return;
         }
      }

      public final void unlink(final Try resolved) {
         while(true) {
            Object state = this.get();
            if (!(state instanceof Link)) {
               this.tryComplete0(state, resolved);
               return;
            }

            DefaultPromise var10000 = this.compareAndSet(state, resolved) ? (DefaultPromise)((Link)state).get() : this;
            resolved = resolved;
            this = var10000;
         }
      }

      private void writeObject(final ObjectOutputStream out) throws IOException {
         throw new NotSerializableException("Promises and Futures cannot be serialized");
      }

      private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
         throw new NotSerializableException("Promises and Futures cannot be deserialized");
      }

      // $FF: synthetic method
      private static final Try liftedTree1$1(final Function2 f$1, final Success x2$1, final Success right$1) {
         try {
            Success var10000 = new Success;
            if (x2$1 == null) {
               throw null;
            } else {
               Object var10003 = x2$1.value();
               if (right$1 == null) {
                  throw null;
               } else {
                  var10000.<init>(f$1.apply(var10003, right$1.value()));
                  return var10000;
               }
            }
         } catch (Throwable var4) {
            if (NonFatal$.MODULE$.apply(var4)) {
               return new Failure(var4);
            } else {
               throw var4;
            }
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$zipWith$2(final AtomicReference buffer$1, final DefaultPromise zipped$1, final Function2 f$1, final Try x0$1) {
         if (x0$1 instanceof Success) {
            Success var4 = (Success)x0$1;
            Success right = (Success)buffer$1.getAndSet(var4);
            if (right != null) {
               zipped$1.tryComplete(liftedTree1$1(f$1, var4, right));
            }
         } else {
            zipped$1.tryComplete((Failure)x0$1);
         }
      }

      // $FF: synthetic method
      private static final Try liftedTree2$1(final Function2 f$1, final Success left$1, final Success x2$2) {
         try {
            Success var10000 = new Success;
            if (left$1 == null) {
               throw null;
            } else {
               Object var10003 = left$1.value();
               if (x2$2 == null) {
                  throw null;
               } else {
                  var10000.<init>(f$1.apply(var10003, x2$2.value()));
                  return var10000;
               }
            }
         } catch (Throwable var4) {
            if (NonFatal$.MODULE$.apply(var4)) {
               return new Failure(var4);
            } else {
               throw var4;
            }
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$zipWith$3(final AtomicReference buffer$1, final DefaultPromise zipped$1, final Function2 f$1, final Try x0$2) {
         if (x0$2 instanceof Success) {
            Success var4 = (Success)x0$2;
            Success left = (Success)buffer$1.getAndSet(var4);
            if (left != null) {
               zipped$1.tryComplete(liftedTree2$1(f$1, left, var4));
            }
         } else {
            zipped$1.tryComplete((Failure)x0$2);
         }
      }

      private DefaultPromise(final Object initial) {
         super(initial);
      }

      public DefaultPromise(final Try result) {
         this((Object)Promise$.MODULE$.scala$concurrent$impl$Promise$$resolve(result));
      }

      public DefaultPromise() {
         this((Object)Promise$.scala$concurrent$impl$Promise$$Noop);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static final class ManyCallbacks implements Callbacks {
      private final Transformation first;
      private final Callbacks rest;

      public final Transformation first() {
         return this.first;
      }

      public final Callbacks rest() {
         return this.rest;
      }

      public final String toString() {
         return "ManyCallbacks";
      }

      public ManyCallbacks(final Transformation first, final Callbacks rest) {
         this.first = first;
         this.rest = rest;
      }
   }

   public static final class Transformation extends DefaultPromise implements Callbacks, Runnable, Batchable {
      private Function1 _fun;
      private ExecutionContext _ec;
      private Try _arg;
      private final int _xform;

      public final boolean benefitsFromBatching() {
         return this._xform != 6 && this._xform != 5;
      }

      public final Transformation submitWithValue(final Try resolved) {
         this._arg = resolved;
         ExecutionContext e = this._ec;

         try {
            e.execute(this);
         } catch (Throwable var4) {
            this._fun = null;
            this._arg = null;
            this._ec = null;
            this.handleFailure(var4, e);
         }

         return this;
      }

      private final void handleFailure(final Throwable t, final ExecutionContext e) {
         boolean wasInterrupted = t instanceof InterruptedException;
         if (!wasInterrupted && !NonFatal$.MODULE$.apply(t)) {
            throw t;
         } else {
            boolean completed = this.tryComplete0(this.get(), Promise$.MODULE$.scala$concurrent$impl$Promise$$resolve(new Failure(t)));
            if (completed && wasInterrupted) {
               Thread.currentThread().interrupt();
            }

            if (this._xform == 5 || this._xform == 6 || !completed) {
               e.reportFailure(t);
            }
         }
      }

      public final void run() {
         Try v = this._arg;
         Function1 fun = this._fun;
         ExecutionContext ec = this._ec;
         this._fun = null;
         this._arg = null;
         this._ec = null;

         try {
            Object var10000;
            switch (this._xform) {
               case 0:
                  var10000 = null;
                  break;
               case 1:
                  var10000 = v instanceof Success ? new Success(fun.apply(v.get())) : v;
                  break;
               case 2:
                  if (v instanceof Success) {
                     Object f = fun.apply(v.get());
                     if (f instanceof DefaultPromise) {
                        ((DefaultPromise)f).linkRootOf(this, (Link)null);
                     } else {
                        this.completeWith((Future)f);
                     }

                     var10000 = null;
                  } else {
                     var10000 = v;
                  }
                  break;
               case 3:
                  var10000 = Promise$.MODULE$.scala$concurrent$impl$Promise$$resolve((Try)fun.apply(v));
                  break;
               case 4:
                  Object f = fun.apply(v);
                  if (f instanceof DefaultPromise) {
                     ((DefaultPromise)f).linkRootOf(this, (Link)null);
                  } else {
                     this.completeWith((Future)f);
                  }

                  var10000 = null;
                  break;
               case 5:
                  v.foreach(fun);
                  var10000 = null;
                  break;
               case 6:
                  fun.apply(v);
                  var10000 = null;
                  break;
               case 7:
                  var10000 = v instanceof Failure ? Promise$.MODULE$.scala$concurrent$impl$Promise$$resolve(v.recover((PartialFunction)fun)) : v;
                  break;
               case 8:
                  if (v instanceof Failure) {
                     Future f = (Future)((PartialFunction)fun).applyOrElse(((Failure)v).exception(), Future$.MODULE$.recoverWithFailed());
                     if (f != Future$.MODULE$.recoverWithFailedMarker()) {
                        if (f instanceof DefaultPromise) {
                           ((DefaultPromise)f).linkRootOf(this, (Link)null);
                        } else {
                           this.completeWith(f);
                        }

                        var10000 = null;
                     } else {
                        var10000 = v;
                     }
                  } else {
                     var10000 = v;
                  }
                  break;
               case 9:
                  if (!(v instanceof Failure) && !BoxesRunTime.unboxToBoolean(fun.apply(v.get()))) {
                     var10000 = Future$.MODULE$.filterFailure();
                     break;
                  }

                  var10000 = v;
                  break;
               case 10:
                  var10000 = v instanceof Success ? new Success(((PartialFunction)fun).applyOrElse(v.get(), Future$.MODULE$.collectFailed())) : v;
                  break;
               default:
                  var10000 = new Failure(new IllegalStateException((new StringBuilder(59)).append("BUG: encountered transformation promise with illegal type: ").append(this._xform).toString()));
            }

            Try resolvedResult = (Try)var10000;
            if (resolvedResult != null) {
               this.tryComplete0(this.get(), resolvedResult);
            }
         } catch (Throwable var9) {
            this.handleFailure(var9, ec);
         }

      }

      private Transformation(final Function1 _fun, final ExecutionContext _ec, final Try _arg, final int _xform) {
         this._fun = _fun;
         this._ec = _ec;
         this._arg = _arg;
         this._xform = _xform;
         super();
      }

      public Transformation(final int xform, final Function1 f, final ExecutionContext ec) {
         this(f, ec.prepare(), (Try)null, xform);
      }
   }

   public interface Callbacks {
   }
}

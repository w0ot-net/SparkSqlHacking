package scala.collection.immutable;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
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
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractIterator;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.View;
import scala.collection.View$;
import scala.collection.mutable.Builder;
import scala.math.Integral;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011]aa\u0002<x!\u0003\r\tA \u0005\b\u0003{\u0001A\u0011AA \u0011\u001d\t9\u0005\u0001C!\u0003\u0013:q!!\u0015x\u0011\u0003\t\u0019F\u0002\u0004wo\"\u0005\u0011Q\u000b\u0005\b\u0003/\"A\u0011AA-\u0011\u001d\tY\u0006\u0002C\u0001\u0003;Bq!a\u001a\u0005\t\u0003\tI\u0007C\u0004\u0002\u0000\u0011!\t!!!\b\u000f\u0005]E\u0001#\u0003\u0002\u001a\u001a9\u0011Q\u0014\u0003\t\n\u0005}\u0005bBA,\u0015\u0011\u0005\u0011Q\u0017\u0005\b\u0003oSA\u0011IA]\u0011\u001d\t\tM\u0003C!\u0003\u0007Dq!a3\u000b\t\u0003\nI\fC\u0004\u0002N*!\t%a4\t\u000f\u0005u'\u0002\"\u0011\u0002`\"9\u00111\u001d\u0006\u0005B\u0005\u0015\bbBAw\u0015\u0011\u0005\u0013q\u001e\u0005\b\u0003kTA\u0011IA|\u0011\u001d\tYP\u0003C!\u0003{DqA!\u0001\u000b\t\u0003\u0012\u0019\u0001C\u0004\u0003\f)!\tA!\u0004\t\u000f\tM!\u0002\"\u0001\u0003\u0016!9!\u0011\u0004\u0006\u0005\u0002\tm\u0001b\u0002B\u0010\u0015\u0011\u0005!\u0011\u0005\u0005\b\u0005SQA\u0011\tB\u0016\u0011%\u0011YDCA\u0001\n\u0013\u0011i\u0004\u0003\u0005\u0003^\u0011!\t!\u001fB0\r\u001d\u0011\t\u0007BA\u0005\u0005GB!B!\u001d\u001e\u0005\u0003\u0005\u000b\u0011BA^\u0011\u001d\t9&\bC\u0001\u0005gB\u0001B!\u001f\u001eA\u0003&\u00111\u0018\u0005\t\u0005wj\u0002\u0015)\u0003\u0002<\"9\u00111Z\u000f\u0005B\u0005e\u0006b\u0002B?;\u0011\u0005\u00111\u0019\u0005\b\u0005\u007fjb\u0011\u0001BA\u0011\u001d\u00119)\bC\u0001\u0005\u0013CqAa#\u001e\t\u0003\u0012iI\u0002\u0004\u0003\u0016\u0012\u0011!q\u0013\u0005\u000b\u0005S;#\u0011!Q\u0001\n\tu\u0005\u0002CA,O\u0011\u0005\u0011Pa+\t\u000f\u0005]v\u0005\"\u0011\u0002:\"9\u0011\u0011Y\u0014\u0005B\u0005\r\u0007bBAfO\u0011\u0005\u0013\u0011\u0018\u0005\b\u0005\u00179C\u0011\u0001BY\u0011\u001d\u0011\u0019b\nC\u0001\u0005kCqA!\u0007(\t\u0003\u0011I\fC\u0004\u0003 \u001d\"\tA!0\t\u000f\t%r\u0005\"\u0011\u0003B\"9!QZ\u0014\u0005B\t=\u0007b\u0002BlO\u0011\u0005#\u0011\u001c\u0005\t\u0005;<C\u0011K=\u0003`\"9!q]\u0014\u0005B\t%\bb\u0002BzO\u0011\u0005#Q\u001f\u0005\b\u0005o<C\u0011\tB}\r\u0019\u0011i\u0010\u0002\u0002\u0003\u0000\"Q!\u0011\u0016\u001d\u0003\u0002\u0003\u0006Ia!\u0002\t\u0015\r5\u0001H!A!\u0002\u0013\u0019)\u0001\u0003\u0005\u0002Xa\"\t!_B\b\u0011\u001d\t9\f\u000fC!\u0003sCq!!19\t\u0003\n\u0019\rC\u0004\u0002Lb\"\t%!/\t\u000f\t-\u0001\b\"\u0001\u0004\u0018!9!1\u0003\u001d\u0005\u0002\rm\u0001b\u0002B\rq\u0011\u00051q\u0004\u0005\b\u0005?AD\u0011AB\u0012\u0011\u001d\u00199\u0003\u000fC\u0005\u0007SAqA!\u000b9\t\u0003\u001ai\u0003C\u0004\u0003Nb\"\te!\u000f\t\u000f\t]\u0007\b\"\u0011\u0004@!A!Q\u001c\u001d\u0005Re\u001c\u0019\u0005C\u0004\u0003hb\"\te!\u0013\t\u000f\tM\b\b\"\u0011\u0004P!9!q\u001f\u001d\u0005B\rEcABB+\t\t\u00199\u0006\u0003\u0006\u0003*.\u0013\t\u0011)A\u0005\u0007;B!b!\u0004L\u0005\u0003\u0005\u000b\u0011BB/\u0011)\u0019)g\u0013B\u0001B\u0003%1Q\f\u0005\t\u0003/ZE\u0011A=\u0004h!9\u0011qW&\u0005B\u0005e\u0006bBAa\u0017\u0012\u0005\u00131\u0019\u0005\b\u0003\u0017\\E\u0011IA]\u0011\u001d\u0011Ya\u0013C\u0001\u0007cBqAa\u0005L\t\u0003\u0019)\bC\u0004\u0003\u001a-#\ta!\u001f\t\u000f\t}1\n\"\u0001\u0004~!91qE&\u0005\n\r\u0005\u0005b\u0002B\u0015\u0017\u0012\u00053Q\u0011\u0005\b\u0005\u001b\\E\u0011IBI\u0011\u001d\u00119n\u0013C!\u0007/C\u0001B!8L\t#J81\u0014\u0005\b\u0005O\\E\u0011IBQ\u0011\u001d\u0011\u0019p\u0013C!\u0007OCqAa>L\t\u0003\u001aIK\u0002\u0004\u0004.\u0012\u00111q\u0016\u0005\u000b\u0005S{&\u0011!Q\u0001\n\rU\u0006BCB\u0007?\n\u0005\t\u0015!\u0003\u00046\"Q1QM0\u0003\u0002\u0003\u0006Ia!.\t\u0015\ruvL!A!\u0002\u0013\u0019)\f\u0003\u0005\u0002X}#\t!_B`\u0011\u001d\t9l\u0018C!\u0003sCq!!1`\t\u0003\n\u0019\rC\u0004\u0002L~#\t%!/\t\u000f\t-q\f\"\u0001\u0004L\"9!1C0\u0005\u0002\r=\u0007b\u0002B\r?\u0012\u000511\u001b\u0005\b\u0005?yF\u0011ABl\u0011\u001d\u00199c\u0018C\u0005\u00077DqA!\u000b`\t\u0003\u001ay\u000eC\u0004\u0003N~#\tea;\t\u000f\t]w\f\"\u0011\u0004r\"A!Q\\0\u0005Re\u001c)\u0010C\u0004\u0003h~#\tea?\t\u000f\tMx\f\"\u0011\u0005\u0002!9!q_0\u0005B\u0011\r\u0001\u0002\u0003C\u0003?\u0012\u0005q\u000fb\u0002\t\u0013\tmB!!A\u0005\n\tu\"aA*fi*\u0011\u00010_\u0001\nS6lW\u000f^1cY\u0016T!A_>\u0002\u0015\r|G\u000e\\3di&|gNC\u0001}\u0003\u0015\u00198-\u00197b\u0007\u0001)2a`A\u000b'-\u0001\u0011\u0011AA\u0005\u0003O\ti#a\u000e\u0011\t\u0005\r\u0011QA\u0007\u0002w&\u0019\u0011qA>\u0003\r\u0005s\u0017PU3g!\u0019\tY!!\u0004\u0002\u00125\tq/C\u0002\u0002\u0010]\u0014\u0001\"\u0013;fe\u0006\u0014G.\u001a\t\u0005\u0003'\t)\u0002\u0004\u0001\u0005\u000f\u0005]\u0001A1\u0001\u0002\u001a\t\t\u0011)\u0005\u0003\u0002\u001c\u0005\u0005\u0002\u0003BA\u0002\u0003;I1!a\b|\u0005\u001dqu\u000e\u001e5j]\u001e\u0004B!a\u0001\u0002$%\u0019\u0011QE>\u0003\u0007\u0005s\u0017\u0010\u0005\u0004\u0002*\u0005-\u0012\u0011C\u0007\u0002s&\u0011a/\u001f\t\u000b\u0003\u0017\ty#!\u0005\u00024\u0005U\u0012bAA\u0019o\n11+\u001a;PaN\u00042!a\u0003\u0001!\u0015\tY\u0001AA\t!!\tI#!\u000f\u0002\u0012\u0005M\u0012bAA\u001es\n9\u0012\n^3sC\ndWMR1di>\u0014\u0018\u0010R3gCVdGo]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0005\u0005\u0005\u0003\u0003BA\u0002\u0003\u0007J1!!\u0012|\u0005\u0011)f.\u001b;\u0002\u001f%$XM]1cY\u00164\u0015m\u0019;pef,\"!a\u0013\u0011\r\u0005%\u0012QJA\u001a\u0013\r\ty%\u001f\u0002\u0010\u0013R,'/\u00192mK\u001a\u000b7\r^8ss\u0006\u00191+\u001a;\u0011\u0007\u0005-AaE\u0003\u0005\u0003\u0003\tY%\u0001\u0004=S:LGO\u0010\u000b\u0003\u0003'\nQ!Z7qif,B!a\u0018\u0002fU\u0011\u0011\u0011\r\t\u0006\u0003\u0017\u0001\u00111\r\t\u0005\u0003'\t)\u0007B\u0004\u0002\u0018\u0019\u0011\r!!\u0007\u0002\t\u0019\u0014x.\\\u000b\u0005\u0003W\n\t\b\u0006\u0003\u0002n\u0005U\u0004#BA\u0006\u0001\u0005=\u0004\u0003BA\n\u0003c\"q!a\u001d\b\u0005\u0004\tIBA\u0001F\u0011\u001d\t9h\u0002a\u0001\u0003s\n!!\u001b;\u0011\r\u0005%\u00121PA8\u0013\r\ti(\u001f\u0002\r\u0013R,'/\u00192mK>s7-Z\u0001\u000b]\u0016<()^5mI\u0016\u0014X\u0003BAB\u0003'+\"!!\"\u0011\u0011\u0005\u001d\u0015QRAI\u0003+k!!!#\u000b\u0007\u0005-\u00150A\u0004nkR\f'\r\\3\n\t\u0005=\u0015\u0011\u0012\u0002\b\u0005VLG\u000eZ3s!\u0011\t\u0019\"a%\u0005\u000f\u0005]\u0001B1\u0001\u0002\u001aA)\u00111\u0002\u0001\u0002\u0012\u0006AQ)\u001c9usN+G\u000fE\u0002\u0002\u001c*i\u0011\u0001\u0002\u0002\t\u000b6\u0004H/_*fiN)!\"!)\u0002(B1\u00111BAR\u0003CI1!!*x\u0005-\t%m\u001d;sC\u000e$8+\u001a;\u0011\t\u0005%\u0016q\u0016\b\u0005\u0003\u0007\tY+C\u0002\u0002.n\fq\u0001]1dW\u0006<W-\u0003\u0003\u00022\u0006M&\u0001D*fe&\fG.\u001b>bE2,'bAAWwR\u0011\u0011\u0011T\u0001\u0005g&TX-\u0006\u0002\u0002<B!\u00111AA_\u0013\r\tyl\u001f\u0002\u0004\u0013:$\u0018aB5t\u000b6\u0004H/_\u000b\u0003\u0003\u000b\u0004B!a\u0001\u0002H&\u0019\u0011\u0011Z>\u0003\u000f\t{w\u000e\\3b]\u0006I1N\\8x]NK'0Z\u0001\u0007M&dG/\u001a:\u0015\t\u0005E\u00171\u001b\t\u0006\u0003\u0017\u0001\u0011\u0011\u0005\u0005\b\u0003+|\u0001\u0019AAl\u0003\u0011\u0001(/\u001a3\u0011\u0011\u0005\r\u0011\u0011\\A\u0011\u0003\u000bL1!a7|\u0005%1UO\\2uS>t\u0017'A\u0005gS2$XM\u001d(piR!\u0011\u0011[Aq\u0011\u001d\t)\u000e\u0005a\u0001\u0003/\f!B]3n_Z,G-\u00117m)\u0011\t\t.a:\t\u000f\u0005%\u0018\u00031\u0001\u0002l\u0006!A\u000f[1u!\u0019\tI#a\u001f\u0002\"\u0005!A-\u001b4g)\u0011\t\t.!=\t\u000f\u0005%(\u00031\u0001\u0002tB1\u0011\u0011FA\u0016\u0003C\t\u0001b];cg\u0016$xJ\u001a\u000b\u0005\u0003\u000b\fI\u0010C\u0004\u0002jN\u0001\r!a=\u0002\u0013%tG/\u001a:tK\u000e$H\u0003BAi\u0003\u007fDq!!;\u0015\u0001\u0004\t\u00190\u0001\u0003wS\u0016<XC\u0001B\u0003!\u0019\tICa\u0002\u0002\"%\u0019!\u0011B=\u0003\tYKWm^\u0001\tG>tG/Y5ogR!\u0011Q\u0019B\b\u0011\u001d\u0011\tB\u0006a\u0001\u0003C\tA!\u001a7f[\u0006!\u0011N\\2m)\u0011\t\tNa\u0006\t\u000f\tEq\u00031\u0001\u0002\"\u0005!Q\r_2m)\u0011\t\tN!\b\t\u000f\tE\u0001\u00041\u0001\u0002\"\u0005A\u0011\u000e^3sCR|'/\u0006\u0002\u0003$A1\u0011\u0011\u0006B\u0013\u0003CI1Aa\nz\u0005!IE/\u001a:bi>\u0014\u0018a\u00024pe\u0016\f7\r[\u000b\u0005\u0005[\u00119\u0004\u0006\u0003\u0002B\t=\u0002b\u0002B\u00195\u0001\u0007!1G\u0001\u0002MBA\u00111AAm\u0003C\u0011)\u0004\u0005\u0003\u0002\u0014\t]Ba\u0002B\u001d5\t\u0007\u0011\u0011\u0004\u0002\u0002+\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011!q\b\t\u0005\u0005\u0003\u0012Y%\u0004\u0002\u0003D)!!Q\tB$\u0003\u0011a\u0017M\\4\u000b\u0005\t%\u0013\u0001\u00026bm\u0006LAA!\u0014\u0003D\t1qJ\u00196fGRDsA\u0003B)\u0005/\u0012I\u0006\u0005\u0003\u0002\u0004\tM\u0013b\u0001B+w\n\u00012+\u001a:jC24VM]:j_:,\u0016\nR\u0001\u0006m\u0006dW/\u001a\u0010\u0002\u0007!:\u0011B!\u0015\u0003X\te\u0013!D3naRL\u0018J\\:uC:\u001cW-\u0006\u0002\u0002R\na1+\u001a;O\u0013R,'/\u0019;peV!!Q\rB8'\u0015i\"qMAT!\u0019\tIC!\u001b\u0003n%\u0019!1N=\u0003!\u0005\u00137\u000f\u001e:bGRLE/\u001a:bi>\u0014\b\u0003BA\n\u0005_\"q!a\u0006\u001e\u0005\u0004\tI\"A\u0001o)\u0011\u0011)Ha\u001e\u0011\u000b\u0005mUD!\u001c\t\u000f\tEt\u00041\u0001\u0002<\u000691-\u001e:sK:$\u0018!\u0003:f[\u0006Lg\u000eZ3s\u0003\u001dA\u0017m\u001d(fqR\fQ!\u00199qYf$BA!\u001c\u0003\u0004\"9!Q\u0011\u0013A\u0002\u0005m\u0016!A5\u0002\t9,\u0007\u0010\u001e\u000b\u0003\u0005[\nA\u0001\u001a:paR!!q\u0012BI!\u0019\tIC!\n\u0003n!9!\u0011\u000f\u0014A\u0002\u0005m\u0006fB\u000f\u0003R\t]#\u0011\f\u0002\u0005'\u0016$\u0018'\u0006\u0003\u0003\u001a\n}5cB\u0014\u0003\u001c\n\u0005\u0016q\u0015\t\u0007\u0003\u0017\t\u0019K!(\u0011\t\u0005M!q\u0014\u0003\b\u0003/9#\u0019AA\r!)\tICa)\u0003\u001e\u0006M\"qU\u0005\u0004\u0005KK(AG*ue&\u001cGo\u00149uS6L'0\u001a3Ji\u0016\u0014\u0018M\u00197f\u001fB\u001c\b#BA\u0006\u0001\tu\u0015!B3mK6\fD\u0003\u0002BW\u0005_\u0003R!a'(\u0005;CqA!+*\u0001\u0004\u0011i\n\u0006\u0003\u0002F\nM\u0006b\u0002B\t[\u0001\u0007!Q\u0014\u000b\u0005\u0005O\u00139\fC\u0004\u0003\u00129\u0002\rA!(\u0015\t\t\u001d&1\u0018\u0005\b\u0005#y\u0003\u0019\u0001BO+\t\u0011y\f\u0005\u0004\u0002*\t\u0015\"QT\u000b\u0005\u0005\u0007\u0014Y\r\u0006\u0003\u0002B\t\u0015\u0007b\u0002B\u0019c\u0001\u0007!q\u0019\t\t\u0003\u0007\tIN!(\u0003JB!\u00111\u0003Bf\t\u001d\u0011I$\rb\u0001\u00033\ta!\u001a=jgR\u001cH\u0003BAc\u0005#DqAa53\u0001\u0004\u0011).A\u0001q!!\t\u0019!!7\u0003\u001e\u0006\u0015\u0017A\u00024pe\u0006dG\u000e\u0006\u0003\u0002F\nm\u0007b\u0002Bjg\u0001\u0007!Q[\u0001\u000bM&dG/\u001a:J[BdGC\u0002BT\u0005C\u0014\u0019\u000fC\u0004\u0002VR\u0002\rA!6\t\u000f\t\u0015H\u00071\u0001\u0002F\u0006I\u0011n\u001d$mSB\u0004X\rZ\u0001\u0005M&tG\r\u0006\u0003\u0003l\nE\bCBA\u0002\u0005[\u0014i*C\u0002\u0003pn\u0014aa\u00149uS>t\u0007b\u0002Bjk\u0001\u0007!Q[\u0001\u0005Q\u0016\fG-\u0006\u0002\u0003\u001e\u0006!A/Y5m+\t\u00119\u000bK\u0004(\u0005#\u00129F!\u0017\u0003\tM+GOM\u000b\u0005\u0007\u0003\u00199aE\u00049\u0007\u0007\u0019I!a*\u0011\r\u0005-\u00111UB\u0003!\u0011\t\u0019ba\u0002\u0005\u000f\u0005]\u0001H1\u0001\u0002\u001aAQ\u0011\u0011\u0006BR\u0007\u000b\t\u0019da\u0003\u0011\u000b\u0005-\u0001a!\u0002\u0002\u000b\u0015dW-\u001c\u001a\u0015\r\rE11CB\u000b!\u0015\tY\nOB\u0003\u0011\u001d\u0011Ik\u000fa\u0001\u0007\u000bAqa!\u0004<\u0001\u0004\u0019)\u0001\u0006\u0003\u0002F\u000ee\u0001b\u0002B\t\u007f\u0001\u00071Q\u0001\u000b\u0005\u0007\u0017\u0019i\u0002C\u0004\u0003\u0012\u0001\u0003\ra!\u0002\u0015\t\r-1\u0011\u0005\u0005\b\u0005#\t\u0005\u0019AB\u0003+\t\u0019)\u0003\u0005\u0004\u0002*\t\u00152QA\u0001\bO\u0016$X\t\\3n)\u0011\u0019)aa\u000b\t\u000f\t\u00155\t1\u0001\u0002<V!1qFB\u001c)\u0011\t\te!\r\t\u000f\tEB\t1\u0001\u00044AA\u00111AAm\u0007\u000b\u0019)\u0004\u0005\u0003\u0002\u0014\r]Ba\u0002B\u001d\t\n\u0007\u0011\u0011\u0004\u000b\u0005\u0003\u000b\u001cY\u0004C\u0004\u0003T\u0016\u0003\ra!\u0010\u0011\u0011\u0005\r\u0011\u0011\\B\u0003\u0003\u000b$B!!2\u0004B!9!1\u001b$A\u0002\ruBCBB\u0006\u0007\u000b\u001a9\u0005C\u0004\u0002V\u001e\u0003\ra!\u0010\t\u000f\t\u0015x\t1\u0001\u0002FR!11JB'!\u0019\t\u0019A!<\u0004\u0006!9!1\u001b%A\u0002\ruRCAB\u0003+\t\u0019Y\u0001K\u00049\u0005#\u00129F!\u0017\u0003\tM+GoM\u000b\u0005\u00073\u001ayfE\u0004L\u00077\u001a\t'a*\u0011\r\u0005-\u00111UB/!\u0011\t\u0019ba\u0018\u0005\u000f\u0005]1J1\u0001\u0002\u001aAQ\u0011\u0011\u0006BR\u0007;\n\u0019da\u0019\u0011\u000b\u0005-\u0001a!\u0018\u0002\u000b\u0015dW-\\\u001a\u0015\u0011\r%41NB7\u0007_\u0002R!a'L\u0007;BqA!+P\u0001\u0004\u0019i\u0006C\u0004\u0004\u000e=\u0003\ra!\u0018\t\u000f\r\u0015t\n1\u0001\u0004^Q!\u0011QYB:\u0011\u001d\u0011\tb\u0015a\u0001\u0007;\"Baa\u0019\u0004x!9!\u0011\u0003+A\u0002\ruC\u0003BB2\u0007wBqA!\u0005V\u0001\u0004\u0019i&\u0006\u0002\u0004\u0000A1\u0011\u0011\u0006B\u0013\u0007;\"Ba!\u0018\u0004\u0004\"9!QQ,A\u0002\u0005mV\u0003BBD\u0007\u001f#B!!\u0011\u0004\n\"9!\u0011\u0007-A\u0002\r-\u0005\u0003CA\u0002\u00033\u001cif!$\u0011\t\u0005M1q\u0012\u0003\b\u0005sA&\u0019AA\r)\u0011\t)ma%\t\u000f\tM\u0017\f1\u0001\u0004\u0016BA\u00111AAm\u0007;\n)\r\u0006\u0003\u0002F\u000ee\u0005b\u0002Bj5\u0002\u00071Q\u0013\u000b\u0007\u0007G\u001aija(\t\u000f\u0005U7\f1\u0001\u0004\u0016\"9!Q].A\u0002\u0005\u0015G\u0003BBR\u0007K\u0003b!a\u0001\u0003n\u000eu\u0003b\u0002Bj9\u0002\u00071QS\u000b\u0003\u0007;*\"aa\u0019)\u000f-\u0013\tFa\u0016\u0003Z\t!1+\u001a;5+\u0011\u0019\tla.\u0014\u000f}\u001b\u0019l!/\u0002(B1\u00111BAR\u0007k\u0003B!a\u0005\u00048\u00129\u0011qC0C\u0002\u0005e\u0001CCA\u0015\u0005G\u001b),a\r\u0004<B)\u00111\u0002\u0001\u00046\u0006)Q\r\\3niQQ1\u0011YBb\u0007\u000b\u001c9m!3\u0011\u000b\u0005mul!.\t\u000f\t%F\r1\u0001\u00046\"91Q\u00023A\u0002\rU\u0006bBB3I\u0002\u00071Q\u0017\u0005\b\u0007{#\u0007\u0019AB[)\u0011\t)m!4\t\u000f\tE\u0001\u000e1\u0001\u00046R!11XBi\u0011\u001d\u0011\t\"\u001ba\u0001\u0007k#Baa/\u0004V\"9!\u0011\u00036A\u0002\rUVCABm!\u0019\tIC!\n\u00046R!1QWBo\u0011\u001d\u0011)\t\u001ca\u0001\u0003w+Ba!9\u0004jR!\u0011\u0011IBr\u0011\u001d\u0011\t$\u001ca\u0001\u0007K\u0004\u0002\"a\u0001\u0002Z\u000eU6q\u001d\t\u0005\u0003'\u0019I\u000fB\u0004\u0003:5\u0014\r!!\u0007\u0015\t\u0005\u00157Q\u001e\u0005\b\u0005't\u0007\u0019ABx!!\t\u0019!!7\u00046\u0006\u0015G\u0003BAc\u0007gDqAa5p\u0001\u0004\u0019y\u000f\u0006\u0004\u0004<\u000e]8\u0011 \u0005\b\u0003+\u0004\b\u0019ABx\u0011\u001d\u0011)\u000f\u001da\u0001\u0003\u000b$Ba!@\u0004\u0000B1\u00111\u0001Bw\u0007kCqAa5r\u0001\u0004\u0019y/\u0006\u0002\u00046V\u001111X\u0001\bEVLG\u000e\u001a+p)\u0011!I\u0001b\u0003\u000f\t\u0005MA1\u0002\u0005\b\t\u001b!\b\u0019\u0001C\b\u0003\u001d\u0011W/\u001b7eKJ\u0004\u0002\"a\"\u0002\u000e\u000eU61\u0018\u0015\b?\nE#q\u000bB-Q\u001d!!\u0011\u000bB,\u00053Bsa\u0001B)\u0005/\u0012I\u0006"
)
public interface Set extends Iterable, scala.collection.Set, SetOps {
   static Builder newBuilder() {
      Set$ var10000 = Set$.MODULE$;
      return new SetBuilderImpl();
   }

   static Set from(final IterableOnce it) {
      return Set$.MODULE$.from(it);
   }

   static Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      Set$ tabulate_this = Set$.MODULE$;
      Function1 tabulate_f = IterableFactory::$anonfun$tabulate$7$adapted;
      IterableOnce from_source = new View.Tabulate(n1, tabulate_f);
      return tabulate_this.from(from_source);
   }

   static Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      Set$ tabulate_this = Set$.MODULE$;
      Function1 tabulate_f = IterableFactory::$anonfun$tabulate$5$adapted;
      IterableOnce from_source = new View.Tabulate(n1, tabulate_f);
      return tabulate_this.from(from_source);
   }

   static Object tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      Set$ tabulate_this = Set$.MODULE$;
      Function1 tabulate_f = IterableFactory::$anonfun$tabulate$3$adapted;
      IterableOnce from_source = new View.Tabulate(n1, tabulate_f);
      return tabulate_this.from(from_source);
   }

   static Object tabulate(final int n1, final int n2, final Function2 f) {
      Set$ tabulate_this = Set$.MODULE$;
      Function1 tabulate_f = IterableFactory::$anonfun$tabulate$1$adapted;
      IterableOnce from_source = new View.Tabulate(n1, tabulate_f);
      return tabulate_this.from(from_source);
   }

   static Object tabulate(final int n, final Function1 f) {
      Set$ tabulate_this = Set$.MODULE$;
      IterableOnce from_source = new View.Tabulate(n, f);
      return tabulate_this.from(from_source);
   }

   static Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      Set$ fill_this = Set$.MODULE$;
      Function0 fill_elem = IterableFactory::$anonfun$fill$4;
      IterableOnce from_source = new View.Fill(n1, fill_elem);
      return fill_this.from(from_source);
   }

   static Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      Set$ fill_this = Set$.MODULE$;
      Function0 fill_elem = IterableFactory::$anonfun$fill$3;
      IterableOnce from_source = new View.Fill(n1, fill_elem);
      return fill_this.from(from_source);
   }

   static Object fill(final int n1, final int n2, final int n3, final Function0 elem) {
      Set$ fill_this = Set$.MODULE$;
      Function0 fill_elem = IterableFactory::$anonfun$fill$2;
      IterableOnce from_source = new View.Fill(n1, fill_elem);
      return fill_this.from(from_source);
   }

   static Object fill(final int n1, final int n2, final Function0 elem) {
      Set$ fill_this = Set$.MODULE$;
      Function0 fill_elem = IterableFactory::$anonfun$fill$1;
      IterableOnce from_source = new View.Fill(n1, fill_elem);
      return fill_this.from(from_source);
   }

   static Object fill(final int n, final Function0 elem) {
      Set$ fill_this = Set$.MODULE$;
      IterableOnce from_source = new View.Fill(n, elem);
      return fill_this.from(from_source);
   }

   static Object range(final Object start, final Object end, final Object step, final Integral evidence$4) {
      return IterableFactory.range$(Set$.MODULE$, start, end, step, evidence$4);
   }

   static Object range(final Object start, final Object end, final Integral evidence$3) {
      return IterableFactory.range$(Set$.MODULE$, start, end, evidence$3);
   }

   static Object unfold(final Object init, final Function1 f) {
      Set$ unfold_this = Set$.MODULE$;
      IterableOnce from_source = new View.Unfold(init, f);
      return unfold_this.from(from_source);
   }

   static Object iterate(final Object start, final int len, final Function1 f) {
      Set$ iterate_this = Set$.MODULE$;
      IterableOnce from_source = new View.Iterate(start, len, f);
      return iterate_this.from(from_source);
   }

   // $FF: synthetic method
   static IterableFactory iterableFactory$(final Set $this) {
      return $this.iterableFactory();
   }

   default IterableFactory iterableFactory() {
      return Set$.MODULE$;
   }

   static void $init$(final Set $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private static class EmptySet$ extends AbstractSet implements Serializable {
      public static final EmptySet$ MODULE$ = new EmptySet$();
      private static final long serialVersionUID = 3L;

      public int size() {
         return 0;
      }

      public boolean isEmpty() {
         return true;
      }

      public int knownSize() {
         return 0;
      }

      public Set filter(final Function1 pred) {
         return this;
      }

      public Set filterNot(final Function1 pred) {
         return this;
      }

      public Set removedAll(final IterableOnce that) {
         return this;
      }

      public Set diff(final scala.collection.Set that) {
         return this;
      }

      public boolean subsetOf(final scala.collection.Set that) {
         return true;
      }

      public Set intersect(final scala.collection.Set that) {
         return this;
      }

      public View view() {
         View$ var10000 = View$.MODULE$;
         return View.Empty$.MODULE$;
      }

      public boolean contains(final Object elem) {
         return false;
      }

      public Set incl(final Object elem) {
         return new Set1(elem);
      }

      public Set excl(final Object elem) {
         return this;
      }

      public Iterator iterator() {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      }

      public void foreach(final Function1 f) {
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(EmptySet$.class);
      }

      public EmptySet$() {
      }
   }

   private abstract static class SetNIterator extends AbstractIterator implements Serializable {
      private static final long serialVersionUID = 3L;
      private int current = 0;
      private int remainder;

      public int knownSize() {
         return this.remainder;
      }

      public boolean hasNext() {
         return this.remainder > 0;
      }

      public abstract Object apply(final int i);

      public Object next() {
         if (this.hasNext()) {
            Object r = this.apply(this.current);
            ++this.current;
            --this.remainder;
            return r;
         } else {
            Iterator$ var10000 = Iterator$.MODULE$;
            return Iterator$.scala$collection$Iterator$$_empty.next();
         }
      }

      public Iterator drop(final int n) {
         if (n > 0) {
            this.current += n;
            this.remainder = Math.max(0, this.remainder - n);
         }

         return this;
      }

      public SetNIterator(final int n) {
         this.remainder = n;
      }
   }

   public static final class Set1 extends AbstractSet implements StrictOptimizedIterableOps, Serializable {
      private static final long serialVersionUID = 3L;
      private final Object elem1;

      public Tuple2 partition(final Function1 p) {
         return StrictOptimizedIterableOps.partition$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return StrictOptimizedIterableOps.span$(this, p);
      }

      public Tuple2 unzip(final Function1 asPair) {
         return StrictOptimizedIterableOps.unzip$(this, asPair);
      }

      public Tuple3 unzip3(final Function1 asTriple) {
         return StrictOptimizedIterableOps.unzip3$(this, asTriple);
      }

      public Object map(final Function1 f) {
         return StrictOptimizedIterableOps.map$(this, f);
      }

      public final Object strictOptimizedMap(final Builder b, final Function1 f) {
         return StrictOptimizedIterableOps.strictOptimizedMap$(this, b, f);
      }

      public Object flatMap(final Function1 f) {
         return StrictOptimizedIterableOps.flatMap$(this, f);
      }

      public final Object strictOptimizedFlatMap(final Builder b, final Function1 f) {
         return StrictOptimizedIterableOps.strictOptimizedFlatMap$(this, b, f);
      }

      public final Object strictOptimizedConcat(final IterableOnce that, final Builder b) {
         return StrictOptimizedIterableOps.strictOptimizedConcat$(this, that, b);
      }

      public Object collect(final PartialFunction pf) {
         return StrictOptimizedIterableOps.collect$(this, pf);
      }

      public final Object strictOptimizedCollect(final Builder b, final PartialFunction pf) {
         return StrictOptimizedIterableOps.strictOptimizedCollect$(this, b, pf);
      }

      public Object flatten(final Function1 toIterableOnce) {
         return StrictOptimizedIterableOps.flatten$(this, toIterableOnce);
      }

      public final Object strictOptimizedFlatten(final Builder b, final Function1 toIterableOnce) {
         return StrictOptimizedIterableOps.strictOptimizedFlatten$(this, b, toIterableOnce);
      }

      public Object zip(final IterableOnce that) {
         return StrictOptimizedIterableOps.zip$(this, that);
      }

      public final Object strictOptimizedZip(final IterableOnce that, final Builder b) {
         return StrictOptimizedIterableOps.strictOptimizedZip$(this, that, b);
      }

      public Object zipWithIndex() {
         return StrictOptimizedIterableOps.zipWithIndex$(this);
      }

      public Object scanLeft(final Object z, final Function2 op) {
         return StrictOptimizedIterableOps.scanLeft$(this, z, op);
      }

      public Object filter(final Function1 pred) {
         return StrictOptimizedIterableOps.filter$(this, pred);
      }

      public Object filterNot(final Function1 pred) {
         return StrictOptimizedIterableOps.filterNot$(this, pred);
      }

      public Tuple2 partitionMap(final Function1 f) {
         return StrictOptimizedIterableOps.partitionMap$(this, f);
      }

      public Object tapEach(final Function1 f) {
         return StrictOptimizedIterableOps.tapEach$(this, f);
      }

      public Object takeRight(final int n) {
         return StrictOptimizedIterableOps.takeRight$(this, n);
      }

      public Object dropRight(final int n) {
         return StrictOptimizedIterableOps.dropRight$(this, n);
      }

      public int size() {
         return 1;
      }

      public boolean isEmpty() {
         return false;
      }

      public int knownSize() {
         return 1;
      }

      public boolean contains(final Object elem) {
         return BoxesRunTime.equals(elem, this.elem1);
      }

      public Set incl(final Object elem) {
         return (Set)(this.contains(elem) ? this : new Set2(this.elem1, elem));
      }

      public Set excl(final Object elem) {
         if (BoxesRunTime.equals(elem, this.elem1)) {
            Set$ var10000 = Set$.MODULE$;
            return Set.EmptySet$.MODULE$;
         } else {
            return this;
         }
      }

      public Iterator iterator() {
         Iterator$ var10000 = Iterator$.MODULE$;
         Object single_a = this.elem1;
         return new AbstractIterator(single_a) {
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

      public void foreach(final Function1 f) {
         f.apply(this.elem1);
      }

      public boolean exists(final Function1 p) {
         return BoxesRunTime.unboxToBoolean(p.apply(this.elem1));
      }

      public boolean forall(final Function1 p) {
         return BoxesRunTime.unboxToBoolean(p.apply(this.elem1));
      }

      public Set filterImpl(final Function1 pred, final boolean isFlipped) {
         if (BoxesRunTime.unboxToBoolean(pred.apply(this.elem1)) != isFlipped) {
            return this;
         } else {
            Set$ var10000 = Set$.MODULE$;
            return Set.EmptySet$.MODULE$;
         }
      }

      public Option find(final Function1 p) {
         return (Option)(BoxesRunTime.unboxToBoolean(p.apply(this.elem1)) ? new Some(this.elem1) : None$.MODULE$);
      }

      public Object head() {
         return this.elem1;
      }

      public Set tail() {
         Set$ var10000 = Set$.MODULE$;
         return Set.EmptySet$.MODULE$;
      }

      public Set1(final Object elem1) {
         this.elem1 = elem1;
      }
   }

   public static final class Set2 extends AbstractSet implements StrictOptimizedIterableOps, Serializable {
      private static final long serialVersionUID = 3L;
      private final Object elem1;
      private final Object elem2;

      public Tuple2 partition(final Function1 p) {
         return StrictOptimizedIterableOps.partition$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return StrictOptimizedIterableOps.span$(this, p);
      }

      public Tuple2 unzip(final Function1 asPair) {
         return StrictOptimizedIterableOps.unzip$(this, asPair);
      }

      public Tuple3 unzip3(final Function1 asTriple) {
         return StrictOptimizedIterableOps.unzip3$(this, asTriple);
      }

      public Object map(final Function1 f) {
         return StrictOptimizedIterableOps.map$(this, f);
      }

      public final Object strictOptimizedMap(final Builder b, final Function1 f) {
         return StrictOptimizedIterableOps.strictOptimizedMap$(this, b, f);
      }

      public Object flatMap(final Function1 f) {
         return StrictOptimizedIterableOps.flatMap$(this, f);
      }

      public final Object strictOptimizedFlatMap(final Builder b, final Function1 f) {
         return StrictOptimizedIterableOps.strictOptimizedFlatMap$(this, b, f);
      }

      public final Object strictOptimizedConcat(final IterableOnce that, final Builder b) {
         return StrictOptimizedIterableOps.strictOptimizedConcat$(this, that, b);
      }

      public Object collect(final PartialFunction pf) {
         return StrictOptimizedIterableOps.collect$(this, pf);
      }

      public final Object strictOptimizedCollect(final Builder b, final PartialFunction pf) {
         return StrictOptimizedIterableOps.strictOptimizedCollect$(this, b, pf);
      }

      public Object flatten(final Function1 toIterableOnce) {
         return StrictOptimizedIterableOps.flatten$(this, toIterableOnce);
      }

      public final Object strictOptimizedFlatten(final Builder b, final Function1 toIterableOnce) {
         return StrictOptimizedIterableOps.strictOptimizedFlatten$(this, b, toIterableOnce);
      }

      public Object zip(final IterableOnce that) {
         return StrictOptimizedIterableOps.zip$(this, that);
      }

      public final Object strictOptimizedZip(final IterableOnce that, final Builder b) {
         return StrictOptimizedIterableOps.strictOptimizedZip$(this, that, b);
      }

      public Object zipWithIndex() {
         return StrictOptimizedIterableOps.zipWithIndex$(this);
      }

      public Object scanLeft(final Object z, final Function2 op) {
         return StrictOptimizedIterableOps.scanLeft$(this, z, op);
      }

      public Object filter(final Function1 pred) {
         return StrictOptimizedIterableOps.filter$(this, pred);
      }

      public Object filterNot(final Function1 pred) {
         return StrictOptimizedIterableOps.filterNot$(this, pred);
      }

      public Tuple2 partitionMap(final Function1 f) {
         return StrictOptimizedIterableOps.partitionMap$(this, f);
      }

      public Object tapEach(final Function1 f) {
         return StrictOptimizedIterableOps.tapEach$(this, f);
      }

      public Object takeRight(final int n) {
         return StrictOptimizedIterableOps.takeRight$(this, n);
      }

      public Object dropRight(final int n) {
         return StrictOptimizedIterableOps.dropRight$(this, n);
      }

      public int size() {
         return 2;
      }

      public boolean isEmpty() {
         return false;
      }

      public int knownSize() {
         return 2;
      }

      public boolean contains(final Object elem) {
         return BoxesRunTime.equals(elem, this.elem1) || BoxesRunTime.equals(elem, this.elem2);
      }

      public Set incl(final Object elem) {
         return (Set)(this.contains(elem) ? this : new Set3(this.elem1, this.elem2, elem));
      }

      public Set excl(final Object elem) {
         if (BoxesRunTime.equals(elem, this.elem1)) {
            return new Set1(this.elem2);
         } else {
            return (Set)(BoxesRunTime.equals(elem, this.elem2) ? new Set1(this.elem1) : this);
         }
      }

      public Iterator iterator() {
         return new SetNIterator() {
            // $FF: synthetic field
            private final Set2 $outer;

            public Object apply(final int i) {
               return this.$outer.scala$collection$immutable$Set$Set2$$getElem(i);
            }

            public {
               if (Set2.this == null) {
                  throw null;
               } else {
                  this.$outer = Set2.this;
               }
            }
         };
      }

      public Object scala$collection$immutable$Set$Set2$$getElem(final int i) {
         switch (i) {
            case 0:
               return this.elem1;
            case 1:
               return this.elem2;
            default:
               throw new MatchError(i);
         }
      }

      public void foreach(final Function1 f) {
         f.apply(this.elem1);
         f.apply(this.elem2);
      }

      public boolean exists(final Function1 p) {
         return BoxesRunTime.unboxToBoolean(p.apply(this.elem1)) || BoxesRunTime.unboxToBoolean(p.apply(this.elem2));
      }

      public boolean forall(final Function1 p) {
         return BoxesRunTime.unboxToBoolean(p.apply(this.elem1)) && BoxesRunTime.unboxToBoolean(p.apply(this.elem2));
      }

      public Set filterImpl(final Function1 pred, final boolean isFlipped) {
         Object r1 = null;
         int n = 0;
         if (BoxesRunTime.unboxToBoolean(pred.apply(this.elem1)) != isFlipped) {
            r1 = this.elem1;
            ++n;
         }

         if (BoxesRunTime.unboxToBoolean(pred.apply(this.elem2)) != isFlipped) {
            if (n == 0) {
               r1 = this.elem2;
            }

            ++n;
         }

         switch (n) {
            case 0:
               Set$ var10000 = Set$.MODULE$;
               return Set.EmptySet$.MODULE$;
            case 1:
               return new Set1(r1);
            case 2:
               return this;
            default:
               throw new MatchError(n);
         }
      }

      public Option find(final Function1 p) {
         if (BoxesRunTime.unboxToBoolean(p.apply(this.elem1))) {
            return new Some(this.elem1);
         } else {
            return (Option)(BoxesRunTime.unboxToBoolean(p.apply(this.elem2)) ? new Some(this.elem2) : None$.MODULE$);
         }
      }

      public Object head() {
         return this.elem1;
      }

      public Set tail() {
         return new Set1(this.elem2);
      }

      public Set2(final Object elem1, final Object elem2) {
         this.elem1 = elem1;
         this.elem2 = elem2;
      }
   }

   public static final class Set3 extends AbstractSet implements StrictOptimizedIterableOps, Serializable {
      private static final long serialVersionUID = 3L;
      private final Object elem1;
      private final Object elem2;
      private final Object elem3;

      public Tuple2 partition(final Function1 p) {
         return StrictOptimizedIterableOps.partition$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return StrictOptimizedIterableOps.span$(this, p);
      }

      public Tuple2 unzip(final Function1 asPair) {
         return StrictOptimizedIterableOps.unzip$(this, asPair);
      }

      public Tuple3 unzip3(final Function1 asTriple) {
         return StrictOptimizedIterableOps.unzip3$(this, asTriple);
      }

      public Object map(final Function1 f) {
         return StrictOptimizedIterableOps.map$(this, f);
      }

      public final Object strictOptimizedMap(final Builder b, final Function1 f) {
         return StrictOptimizedIterableOps.strictOptimizedMap$(this, b, f);
      }

      public Object flatMap(final Function1 f) {
         return StrictOptimizedIterableOps.flatMap$(this, f);
      }

      public final Object strictOptimizedFlatMap(final Builder b, final Function1 f) {
         return StrictOptimizedIterableOps.strictOptimizedFlatMap$(this, b, f);
      }

      public final Object strictOptimizedConcat(final IterableOnce that, final Builder b) {
         return StrictOptimizedIterableOps.strictOptimizedConcat$(this, that, b);
      }

      public Object collect(final PartialFunction pf) {
         return StrictOptimizedIterableOps.collect$(this, pf);
      }

      public final Object strictOptimizedCollect(final Builder b, final PartialFunction pf) {
         return StrictOptimizedIterableOps.strictOptimizedCollect$(this, b, pf);
      }

      public Object flatten(final Function1 toIterableOnce) {
         return StrictOptimizedIterableOps.flatten$(this, toIterableOnce);
      }

      public final Object strictOptimizedFlatten(final Builder b, final Function1 toIterableOnce) {
         return StrictOptimizedIterableOps.strictOptimizedFlatten$(this, b, toIterableOnce);
      }

      public Object zip(final IterableOnce that) {
         return StrictOptimizedIterableOps.zip$(this, that);
      }

      public final Object strictOptimizedZip(final IterableOnce that, final Builder b) {
         return StrictOptimizedIterableOps.strictOptimizedZip$(this, that, b);
      }

      public Object zipWithIndex() {
         return StrictOptimizedIterableOps.zipWithIndex$(this);
      }

      public Object scanLeft(final Object z, final Function2 op) {
         return StrictOptimizedIterableOps.scanLeft$(this, z, op);
      }

      public Object filter(final Function1 pred) {
         return StrictOptimizedIterableOps.filter$(this, pred);
      }

      public Object filterNot(final Function1 pred) {
         return StrictOptimizedIterableOps.filterNot$(this, pred);
      }

      public Tuple2 partitionMap(final Function1 f) {
         return StrictOptimizedIterableOps.partitionMap$(this, f);
      }

      public Object tapEach(final Function1 f) {
         return StrictOptimizedIterableOps.tapEach$(this, f);
      }

      public Object takeRight(final int n) {
         return StrictOptimizedIterableOps.takeRight$(this, n);
      }

      public Object dropRight(final int n) {
         return StrictOptimizedIterableOps.dropRight$(this, n);
      }

      public int size() {
         return 3;
      }

      public boolean isEmpty() {
         return false;
      }

      public int knownSize() {
         return 3;
      }

      public boolean contains(final Object elem) {
         return BoxesRunTime.equals(elem, this.elem1) || BoxesRunTime.equals(elem, this.elem2) || BoxesRunTime.equals(elem, this.elem3);
      }

      public Set incl(final Object elem) {
         return (Set)(this.contains(elem) ? this : new Set4(this.elem1, this.elem2, this.elem3, elem));
      }

      public Set excl(final Object elem) {
         if (BoxesRunTime.equals(elem, this.elem1)) {
            return new Set2(this.elem2, this.elem3);
         } else if (BoxesRunTime.equals(elem, this.elem2)) {
            return new Set2(this.elem1, this.elem3);
         } else {
            return (Set)(BoxesRunTime.equals(elem, this.elem3) ? new Set2(this.elem1, this.elem2) : this);
         }
      }

      public Iterator iterator() {
         return new SetNIterator() {
            // $FF: synthetic field
            private final Set3 $outer;

            public Object apply(final int i) {
               return this.$outer.scala$collection$immutable$Set$Set3$$getElem(i);
            }

            public {
               if (Set3.this == null) {
                  throw null;
               } else {
                  this.$outer = Set3.this;
               }
            }
         };
      }

      public Object scala$collection$immutable$Set$Set3$$getElem(final int i) {
         switch (i) {
            case 0:
               return this.elem1;
            case 1:
               return this.elem2;
            case 2:
               return this.elem3;
            default:
               throw new MatchError(i);
         }
      }

      public void foreach(final Function1 f) {
         f.apply(this.elem1);
         f.apply(this.elem2);
         f.apply(this.elem3);
      }

      public boolean exists(final Function1 p) {
         return BoxesRunTime.unboxToBoolean(p.apply(this.elem1)) || BoxesRunTime.unboxToBoolean(p.apply(this.elem2)) || BoxesRunTime.unboxToBoolean(p.apply(this.elem3));
      }

      public boolean forall(final Function1 p) {
         return BoxesRunTime.unboxToBoolean(p.apply(this.elem1)) && BoxesRunTime.unboxToBoolean(p.apply(this.elem2)) && BoxesRunTime.unboxToBoolean(p.apply(this.elem3));
      }

      public Set filterImpl(final Function1 pred, final boolean isFlipped) {
         Object r1 = null;
         Object r2 = null;
         int n = 0;
         if (BoxesRunTime.unboxToBoolean(pred.apply(this.elem1)) != isFlipped) {
            r1 = this.elem1;
            ++n;
         }

         if (BoxesRunTime.unboxToBoolean(pred.apply(this.elem2)) != isFlipped) {
            if (n == 0) {
               r1 = this.elem2;
            } else {
               r2 = this.elem2;
            }

            ++n;
         }

         if (BoxesRunTime.unboxToBoolean(pred.apply(this.elem3)) != isFlipped) {
            if (n == 0) {
               r1 = this.elem3;
            } else if (n == 1) {
               r2 = this.elem3;
            }

            ++n;
         }

         switch (n) {
            case 0:
               Set$ var10000 = Set$.MODULE$;
               return Set.EmptySet$.MODULE$;
            case 1:
               return new Set1(r1);
            case 2:
               return new Set2(r1, r2);
            case 3:
               return this;
            default:
               throw new MatchError(n);
         }
      }

      public Option find(final Function1 p) {
         if (BoxesRunTime.unboxToBoolean(p.apply(this.elem1))) {
            return new Some(this.elem1);
         } else if (BoxesRunTime.unboxToBoolean(p.apply(this.elem2))) {
            return new Some(this.elem2);
         } else {
            return (Option)(BoxesRunTime.unboxToBoolean(p.apply(this.elem3)) ? new Some(this.elem3) : None$.MODULE$);
         }
      }

      public Object head() {
         return this.elem1;
      }

      public Set tail() {
         return new Set2(this.elem2, this.elem3);
      }

      public Set3(final Object elem1, final Object elem2, final Object elem3) {
         this.elem1 = elem1;
         this.elem2 = elem2;
         this.elem3 = elem3;
      }
   }

   public static final class Set4 extends AbstractSet implements StrictOptimizedIterableOps, Serializable {
      private static final long serialVersionUID = 3L;
      private final Object elem1;
      private final Object elem2;
      private final Object elem3;
      private final Object elem4;

      public Tuple2 partition(final Function1 p) {
         return StrictOptimizedIterableOps.partition$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return StrictOptimizedIterableOps.span$(this, p);
      }

      public Tuple2 unzip(final Function1 asPair) {
         return StrictOptimizedIterableOps.unzip$(this, asPair);
      }

      public Tuple3 unzip3(final Function1 asTriple) {
         return StrictOptimizedIterableOps.unzip3$(this, asTriple);
      }

      public Object map(final Function1 f) {
         return StrictOptimizedIterableOps.map$(this, f);
      }

      public final Object strictOptimizedMap(final Builder b, final Function1 f) {
         return StrictOptimizedIterableOps.strictOptimizedMap$(this, b, f);
      }

      public Object flatMap(final Function1 f) {
         return StrictOptimizedIterableOps.flatMap$(this, f);
      }

      public final Object strictOptimizedFlatMap(final Builder b, final Function1 f) {
         return StrictOptimizedIterableOps.strictOptimizedFlatMap$(this, b, f);
      }

      public final Object strictOptimizedConcat(final IterableOnce that, final Builder b) {
         return StrictOptimizedIterableOps.strictOptimizedConcat$(this, that, b);
      }

      public Object collect(final PartialFunction pf) {
         return StrictOptimizedIterableOps.collect$(this, pf);
      }

      public final Object strictOptimizedCollect(final Builder b, final PartialFunction pf) {
         return StrictOptimizedIterableOps.strictOptimizedCollect$(this, b, pf);
      }

      public Object flatten(final Function1 toIterableOnce) {
         return StrictOptimizedIterableOps.flatten$(this, toIterableOnce);
      }

      public final Object strictOptimizedFlatten(final Builder b, final Function1 toIterableOnce) {
         return StrictOptimizedIterableOps.strictOptimizedFlatten$(this, b, toIterableOnce);
      }

      public Object zip(final IterableOnce that) {
         return StrictOptimizedIterableOps.zip$(this, that);
      }

      public final Object strictOptimizedZip(final IterableOnce that, final Builder b) {
         return StrictOptimizedIterableOps.strictOptimizedZip$(this, that, b);
      }

      public Object zipWithIndex() {
         return StrictOptimizedIterableOps.zipWithIndex$(this);
      }

      public Object scanLeft(final Object z, final Function2 op) {
         return StrictOptimizedIterableOps.scanLeft$(this, z, op);
      }

      public Object filter(final Function1 pred) {
         return StrictOptimizedIterableOps.filter$(this, pred);
      }

      public Object filterNot(final Function1 pred) {
         return StrictOptimizedIterableOps.filterNot$(this, pred);
      }

      public Tuple2 partitionMap(final Function1 f) {
         return StrictOptimizedIterableOps.partitionMap$(this, f);
      }

      public Object tapEach(final Function1 f) {
         return StrictOptimizedIterableOps.tapEach$(this, f);
      }

      public Object takeRight(final int n) {
         return StrictOptimizedIterableOps.takeRight$(this, n);
      }

      public Object dropRight(final int n) {
         return StrictOptimizedIterableOps.dropRight$(this, n);
      }

      public int size() {
         return 4;
      }

      public boolean isEmpty() {
         return false;
      }

      public int knownSize() {
         return 4;
      }

      public boolean contains(final Object elem) {
         return BoxesRunTime.equals(elem, this.elem1) || BoxesRunTime.equals(elem, this.elem2) || BoxesRunTime.equals(elem, this.elem3) || BoxesRunTime.equals(elem, this.elem4);
      }

      public Set incl(final Object elem) {
         if (this.contains(elem)) {
            return this;
         } else {
            HashSet var10000 = HashSet$.MODULE$.empty();
            Object $plus_elem = this.elem1;
            if (var10000 == null) {
               throw null;
            } else {
               var10000 = var10000.incl($plus_elem);
               $plus_elem = null;
               Object $plus_elem = this.elem2;
               if (var10000 == null) {
                  throw null;
               } else {
                  var10000 = var10000.incl($plus_elem);
                  $plus_elem = null;
                  Object $plus_elem = this.elem3;
                  if (var10000 == null) {
                     throw null;
                  } else {
                     var10000 = var10000.incl($plus_elem);
                     $plus_elem = null;
                     Object $plus_elem = this.elem4;
                     if (var10000 == null) {
                        throw null;
                     } else {
                        var10000 = var10000.incl($plus_elem);
                        $plus_elem = null;
                        if (var10000 == null) {
                           throw null;
                        } else {
                           return var10000.incl(elem);
                        }
                     }
                  }
               }
            }
         }
      }

      public Set excl(final Object elem) {
         if (BoxesRunTime.equals(elem, this.elem1)) {
            return new Set3(this.elem2, this.elem3, this.elem4);
         } else if (BoxesRunTime.equals(elem, this.elem2)) {
            return new Set3(this.elem1, this.elem3, this.elem4);
         } else if (BoxesRunTime.equals(elem, this.elem3)) {
            return new Set3(this.elem1, this.elem2, this.elem4);
         } else {
            return (Set)(BoxesRunTime.equals(elem, this.elem4) ? new Set3(this.elem1, this.elem2, this.elem3) : this);
         }
      }

      public Iterator iterator() {
         return new SetNIterator() {
            // $FF: synthetic field
            private final Set4 $outer;

            public Object apply(final int i) {
               return this.$outer.scala$collection$immutable$Set$Set4$$getElem(i);
            }

            public {
               if (Set4.this == null) {
                  throw null;
               } else {
                  this.$outer = Set4.this;
               }
            }
         };
      }

      public Object scala$collection$immutable$Set$Set4$$getElem(final int i) {
         switch (i) {
            case 0:
               return this.elem1;
            case 1:
               return this.elem2;
            case 2:
               return this.elem3;
            case 3:
               return this.elem4;
            default:
               throw new MatchError(i);
         }
      }

      public void foreach(final Function1 f) {
         f.apply(this.elem1);
         f.apply(this.elem2);
         f.apply(this.elem3);
         f.apply(this.elem4);
      }

      public boolean exists(final Function1 p) {
         return BoxesRunTime.unboxToBoolean(p.apply(this.elem1)) || BoxesRunTime.unboxToBoolean(p.apply(this.elem2)) || BoxesRunTime.unboxToBoolean(p.apply(this.elem3)) || BoxesRunTime.unboxToBoolean(p.apply(this.elem4));
      }

      public boolean forall(final Function1 p) {
         return BoxesRunTime.unboxToBoolean(p.apply(this.elem1)) && BoxesRunTime.unboxToBoolean(p.apply(this.elem2)) && BoxesRunTime.unboxToBoolean(p.apply(this.elem3)) && BoxesRunTime.unboxToBoolean(p.apply(this.elem4));
      }

      public Set filterImpl(final Function1 pred, final boolean isFlipped) {
         Object r1 = null;
         Object r2 = null;
         Object r3 = null;
         int n = 0;
         if (BoxesRunTime.unboxToBoolean(pred.apply(this.elem1)) != isFlipped) {
            r1 = this.elem1;
            ++n;
         }

         if (BoxesRunTime.unboxToBoolean(pred.apply(this.elem2)) != isFlipped) {
            if (n == 0) {
               r1 = this.elem2;
            } else {
               r2 = this.elem2;
            }

            ++n;
         }

         if (BoxesRunTime.unboxToBoolean(pred.apply(this.elem3)) != isFlipped) {
            if (n == 0) {
               r1 = this.elem3;
            } else if (n == 1) {
               r2 = this.elem3;
            } else {
               r3 = this.elem3;
            }

            ++n;
         }

         if (BoxesRunTime.unboxToBoolean(pred.apply(this.elem4)) != isFlipped) {
            if (n == 0) {
               r1 = this.elem4;
            } else if (n == 1) {
               r2 = this.elem4;
            } else if (n == 2) {
               r3 = this.elem4;
            }

            ++n;
         }

         switch (n) {
            case 0:
               Set$ var10000 = Set$.MODULE$;
               return Set.EmptySet$.MODULE$;
            case 1:
               return new Set1(r1);
            case 2:
               return new Set2(r1, r2);
            case 3:
               return new Set3(r1, r2, r3);
            case 4:
               return this;
            default:
               throw new MatchError(n);
         }
      }

      public Option find(final Function1 p) {
         if (BoxesRunTime.unboxToBoolean(p.apply(this.elem1))) {
            return new Some(this.elem1);
         } else if (BoxesRunTime.unboxToBoolean(p.apply(this.elem2))) {
            return new Some(this.elem2);
         } else if (BoxesRunTime.unboxToBoolean(p.apply(this.elem3))) {
            return new Some(this.elem3);
         } else {
            return (Option)(BoxesRunTime.unboxToBoolean(p.apply(this.elem4)) ? new Some(this.elem4) : None$.MODULE$);
         }
      }

      public Object head() {
         return this.elem1;
      }

      public Set tail() {
         return new Set3(this.elem2, this.elem3, this.elem4);
      }

      public Builder buildTo(final Builder builder) {
         return (Builder)builder.addOne(this.elem1).addOne(this.elem2).addOne(this.elem3).addOne(this.elem4);
      }

      public Set4(final Object elem1, final Object elem2, final Object elem3, final Object elem4) {
         this.elem1 = elem1;
         this.elem2 = elem2;
         this.elem3 = elem3;
         this.elem4 = elem4;
      }
   }
}

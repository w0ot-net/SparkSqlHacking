package scala.util.parsing.combinator;

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
import scala.None.;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;
import scala.runtime.Statics;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;
import scala.util.parsing.input.Position;
import scala.util.parsing.input.Reader;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015%haB;w!\u0003\r\ta \u0005\b\u0003#\u0001A\u0011AA\n\r\u0019\tY\u0002\u0001\u0001\u0002\u001e!Q\u00111\t\u0002\u0003\u0002\u0003\u0006I!!\t\t\u000f\u0005\u0015#\u0001\"\u0001\u0002H!Y\u0011q\n\u0002\u0003\u0002\u0003\u0005\u000b\u0011BA)\u0011-\u0019)I\u0001B\u0001\u0002\u0003\u0006Iaa\"\t\u0017\r%%A!A\u0001B\u0003&11\u0012\u0005\b\u0007\u001b\u0013A\u0011IBH\u0011\u001d\u00199J\u0001C!\u0005_Cqa!'\u0003\t\u0003\u0019Y\nC\u0004\u0004\u001e\n!\taa(\t\u000f\r\u0005&\u0001\"\u0001\u0004\f!911\u0015\u0002\u0005\u0002\r\u0015\u0006\u0002DBT\u0005\t\u0005)\u0019!C\u0001\u0001\r%\u0006\u0002DBV\u0005\t\u0005\t\u0011!C\u0001\u0001\r5\u0006\u0002DBa\u0005\t\u0005\t\u0011!C\u0001\u0001\r\r\u0007\u0002DBk\u0005\t\u0005)\u0019!C\u0001\u0001\r]\u0007\u0002DBm\u0005\t\u0005\t\u0019!C\u0001\u0001\rm\u0007\u0002DBo\u0005\t\u0005\t\u0019!C\u0001\u0001\r}\u0007bBBr\u0001\u0011\u00053Q\u001d\u0005\b\t\u0007\u0001A\u0011\u0002C\u0003\r\u0019\ty\b\u0001#\u0002\u0002\"Q\u00111\u0015\f\u0003\u0012\u0004%\t!!*\t\u0015\rmbC!a\u0001\n\u0003\u0019i\u0004\u0003\u0006\u0004:Y\u0011\t\u0012)Q\u0005\u0003OCq!!\u0012\u0017\t\u0003\u0019\t\u0005C\u0004\u0004VY!\taa\u0016\t\u0013\t-d#!A\u0005\u0002\rm\u0003\"\u0003B;-E\u0005I\u0011AB4\u0011%\u0011IJFA\u0001\n\u0003\u0012Y\nC\u0005\u0003.Z\t\t\u0011\"\u0001\u00030\"I!q\u0017\f\u0002\u0002\u0013\u00051q\u000e\u0005\n\u0005{3\u0012\u0011!C!\u0005\u007fC\u0011B!3\u0017\u0003\u0003%\taa\u001d\t\u0013\tUg#!A\u0005B\r]\u0004\"\u0003Bn-\u0005\u0005I\u0011\tBo\u0011%\u0011yNFA\u0001\n\u0003\u0012\t\u000fC\u0005\u0003dZ\t\t\u0011\"\u0011\u0004|\u001dIA1\u0003\u0001\u0002\u0002#%AQ\u0003\u0004\n\u0003\u007f\u0002\u0011\u0011!E\u0005\t/Aq!!\u0012)\t\u0003!\u0019\u0003C\u0005\u0003`\"\n\t\u0011\"\u0012\u0003b\"IAQ\u0005\u0015\u0002\u0002\u0013\u0005Eq\u0005\u0005\n\t{A\u0013\u0011!CA\t\u007f1a!!-\u0001\t\u0006M\u0006BCA[[\tE\r\u0011\"\u0001\u00028\"Q\u0011\u0011Z\u0017\u0003\u0002\u0004%\t!a3\t\u0015\u0005\u001dWF!E!B\u0013\tI\f\u0003\u0006\u0002R6\u0012\t\u001a!C\u0001\u0003'D!\"!9.\u0005\u0003\u0007I\u0011AAr\u0011)\ty.\fB\tB\u0003&\u0011Q\u001b\u0005\u000b\u0003Ol#\u00113A\u0005\u0002\u0005%\bB\u0003Bu[\t\u0005\r\u0011\"\u0001\u0003l\"Q!q^\u0017\u0003\u0012\u0003\u0006K!a;\t\u000f\u0005\u0015S\u0006\"\u0001\u0003r\"91\u0011B\u0017\u0005\u0002\r-\u0001\"\u0003B6[\u0005\u0005I\u0011AB\u0007\u0011%\u0011)(LI\u0001\n\u0003\u0019)\u0002C\u0005\u0003\u000e6\n\n\u0011\"\u0001\u0003x!I!1S\u0017\u0012\u0002\u0013\u000511\u0004\u0005\n\u00053k\u0013\u0011!C!\u00057C\u0011B!,.\u0003\u0003%\tAa,\t\u0013\t]V&!A\u0005\u0002\r}\u0001\"\u0003B_[\u0005\u0005I\u0011\tB`\u0011%\u0011I-LA\u0001\n\u0003\u0019\u0019\u0003C\u0005\u0003V6\n\t\u0011\"\u0011\u0004(!I!1\\\u0017\u0002\u0002\u0013\u0005#Q\u001c\u0005\n\u0005?l\u0013\u0011!C!\u0005CD\u0011Ba9.\u0003\u0003%\tea\u000b\b\u0013\u0011e\u0003!!A\t\n\u0011mc!CAY\u0001\u0005\u0005\t\u0012\u0002C/\u0011\u001d\t)e\u0012C\u0001\twB\u0011Ba8H\u0003\u0003%)E!9\t\u0013\u0011\u0015r)!A\u0005\u0002\u0012u\u0004\"\u0003C\u001f\u000f\u0006\u0005I\u0011\u0011CK\r\u0019\t\u0019\u0010\u0001#\u0002v\"Q\u0011q\u001f'\u0003\u0012\u0004%\t!!?\t\u0015\t\u001dAJ!a\u0001\n\u0003\u0011I\u0001\u0003\u0006\u0003\u00061\u0013\t\u0012)Q\u0005\u0003wD!B!\u0004M\u0005#\u0007I\u0011\u0001B\b\u0011)\u0011\u0019\u0003\u0014BA\u0002\u0013\u0005!Q\u0005\u0005\u000b\u0005Ca%\u0011#Q!\n\tE\u0001B\u0003B\u0015\u0019\nE\r\u0011\"\u0001\u0003,!Q!1\b'\u0003\u0002\u0004%\tA!\u0010\t\u0015\teBJ!E!B\u0013\u0011i\u0003C\u0004\u0002F1#\tA!\u0011\t\u000f\t\u0015D\n\"\u0001\u0003h!I!1\u000e'\u0002\u0002\u0013\u0005!Q\u000e\u0005\n\u0005kb\u0015\u0013!C\u0001\u0005oB\u0011B!$M#\u0003%\tAa$\t\u0013\tME*%A\u0005\u0002\tU\u0005\"\u0003BM\u0019\u0006\u0005I\u0011\tBN\u0011%\u0011i\u000bTA\u0001\n\u0003\u0011y\u000bC\u0005\u000382\u000b\t\u0011\"\u0001\u0003:\"I!Q\u0018'\u0002\u0002\u0013\u0005#q\u0018\u0005\n\u0005\u0013d\u0015\u0011!C\u0001\u0005\u0017D\u0011B!6M\u0003\u0003%\tEa6\t\u0013\tmG*!A\u0005B\tu\u0007\"\u0003Bp\u0019\u0006\u0005I\u0011\tBq\u0011%\u0011\u0019\u000fTA\u0001\n\u0003\u0012)oB\u0005\u00052\u0002\t\t\u0011#\u0003\u00054\u001aI\u00111\u001f\u0001\u0002\u0002#%AQ\u0017\u0005\b\u0003\u000b2G\u0011\u0001Ck\u0011%\u0011yNZA\u0001\n\u000b\u0012\t\u000fC\u0005\u0005&\u0019\f\t\u0011\"!\u0005X\"IAQ\b4\u0002\u0002\u0013\u0005E1 \u0004\b\u0007W\u0004\u0011\u0011ABw\u0011\u001d\t)e\u001bC\u0001\u0007oDq!b\b\u0001\t\u0007)\t\u0003C\u0004\u00066\u0001!I!b\u000e\t\u000f\u0015u\u0003\u0001\"\u0003\u0006`!9QQ\u0010\u0001\u0005\n\u0015}\u0004bBCJ\u0001\u0011\u0005QQ\u0013\u0005\b\u000bG\u0003A\u0011BCS\u00119)9\f\u0001I\u0001\u0004\u0003\u0005I\u0011BC]\u000b\u000bDa\"b2\u0001!\u0003\r\t\u0011!C\u0005\u000b\u0013,)O\u0001\bQC\u000e\\'/\u0019;QCJ\u001cXM]:\u000b\u0005]D\u0018AC2p[\nLg.\u0019;pe*\u0011\u0011P_\u0001\ba\u0006\u00148/\u001b8h\u0015\tYH0\u0001\u0003vi&d'\"A?\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M)\u0001!!\u0001\u0002\nA!\u00111AA\u0003\u001b\u0005a\u0018bAA\u0004y\n1\u0011I\\=SK\u001a\u0004B!a\u0003\u0002\u000e5\ta/C\u0002\u0002\u0010Y\u0014q\u0001U1sg\u0016\u00148/\u0001\u0004%S:LG\u000f\n\u000b\u0003\u0003+\u0001B!a\u0001\u0002\u0018%\u0019\u0011\u0011\u0004?\u0003\tUs\u0017\u000e\u001e\u0002\u000e!\u0006\u001c7N]1u%\u0016\fG-\u001a:\u0016\t\u0005}\u0011\u0011G\n\u0004\u0005\u0005\u0005\u0002CBA\u0012\u0003S\ti#\u0004\u0002\u0002&)\u0019\u0011q\u0005=\u0002\u000b%t\u0007/\u001e;\n\t\u0005-\u0012Q\u0005\u0002\u0007%\u0016\fG-\u001a:\u0011\t\u0005=\u0012\u0011\u0007\u0007\u0001\t!\t\u0019D\u0001CC\u0002\u0005U\"!\u0001+\u0012\t\u0005]\u0012Q\b\t\u0005\u0003\u0007\tI$C\u0002\u0002<q\u0014qAT8uQ&tw\r\u0005\u0003\u0002\u0004\u0005}\u0012bAA!y\n\u0019\u0011I\\=\u0002\u0015UtG-\u001a:ms&tw-\u0001\u0004=S:LGO\u0010\u000b\u0005\u0003\u0013\ni\u0005E\u0003\u0002L\t\ti#D\u0001\u0001\u0011\u001d\t\u0019\u0005\u0002a\u0001\u0003C\tAg]2bY\u0006$S\u000f^5mIA\f'o]5oO\u0012\u001aw.\u001c2j]\u0006$xN\u001d\u0013QC\u000e\\'/\u0019;QCJ\u001cXM]:%I\r\f7\r[3!!!\t\u0019&!\u0018\u0002b\u0005mTBAA+\u0015\u0011\t9&!\u0017\u0002\u000f5,H/\u00192mK*\u0019\u00111\f?\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002`\u0005U#a\u0002%bg\"l\u0015\r\u001d\t\t\u0003\u0007\t\u0019'a\u001a\u0002v%\u0019\u0011Q\r?\u0003\rQ+\b\u000f\\33a\u0011\tI'!\u001d\u0011\r\u0005-\u00131NA8\u0013\u0011\ti'!\u0004\u0003\rA\u000b'o]3s!\u0011\ty#!\u001d\u0005\u0017\u0005MT!!A\u0001\u0002\u000b\u0005\u0011Q\u0007\u0002\tIEl\u0017M]6%cA!\u00111EA<\u0013\u0011\tI(!\n\u0003\u0011A{7/\u001b;j_:\u0004D!! \u0004\u0002B)\u00111\n\f\u0004\u0000\tIQ*Z7p\u000b:$(/_\u000b\u0005\u0003\u0007\u001b9eE\u0004\u0017\u0003\u0003\t))a#\u0011\t\u0005\r\u0011qQ\u0005\u0004\u0003\u0013c(a\u0002)s_\u0012,8\r\u001e\t\u0005\u0003\u001b\u000biJ\u0004\u0003\u0002\u0010\u0006ee\u0002BAI\u0003/k!!a%\u000b\u0007\u0005Ue0\u0001\u0004=e>|GOP\u0005\u0002{&\u0019\u00111\u0014?\u0002\u000fA\f7m[1hK&!\u0011qTAQ\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\r\tY\n`\u0001\u0002eV\u0011\u0011q\u0015\t\t\u0003S\u000bY+a,\u000409!\u00111AAM\u0013\u0011\ti+!)\u0003\r\u0015KG\u000f[3s!\r\tY%\f\u0002\u0003\u0019J\u001br!LA\u0001\u0003\u000b\u000bY)\u0001\u0003tK\u0016$WCAA]a\u0011\tY,a1\u0011\r\u0005-\u0013QXAa\u0013\u0011\ty,!\u0004\u0003\u0017A\u000b'o]3SKN,H\u000e\u001e\t\u0005\u0003_\t\u0019\rB\u0006\u0002FB\n\t\u0011!A\u0003\u0002\u0005U\"\u0001\u0003\u0013r[\u0006\u00148\u000eJ\u001b\u0002\u000bM,W\r\u001a\u0011\u0002\u0011M,W\rZ0%KF$B!!\u0006\u0002N\"I\u0011qZ\u0018\u0002\u0002\u0003\u0007\u0011\u0011X\u0001\u0004q\u0012\n\u0014\u0001\u0002:vY\u0016,\"!!61\t\u0005]\u00171\u001c\t\u0007\u0003\u0017\nY'!7\u0011\t\u0005=\u00121\u001c\u0003\f\u0003;\u001c\u0014\u0011!A\u0001\u0006\u0003\t)D\u0001\u0005%c6\f'o\u001b\u00137\u0003\u0015\u0011X\u000f\\3!\u0003!\u0011X\u000f\\3`I\u0015\fH\u0003BA\u000b\u0003KD\u0011\"a43\u0003\u0003\u0005\r!!6\u0002\t!,\u0017\rZ\u000b\u0003\u0003W\u0004b!a\u0001\u0002n\u0006E\u0018bAAxy\n1q\n\u001d;j_:\u00042!a\u0013M\u0005\u0011AU-\u00193\u0014\u000f1\u000b\t!!\"\u0002\f\u0006Q\u0001.Z1e!\u0006\u00148/\u001a:\u0016\u0005\u0005m\b\u0007BA\u007f\u0005\u0003\u0001b!a\u0013\u0002l\u0005}\b\u0003BA\u0018\u0005\u0003!1Ba\u0001P\u0003\u0003\u0005\tQ!\u0001\u00026\tAA%]7be.$s'A\u0006iK\u0006$\u0007+\u0019:tKJ\u0004\u0013A\u00045fC\u0012\u0004\u0016M]:fe~#S-\u001d\u000b\u0005\u0003+\u0011Y\u0001C\u0005\u0002P:\u000b\t\u00111\u0001\u0002|\u0006Y\u0011N\u001c<pYZ,GmU3u+\t\u0011\t\u0002\u0005\u0004\u0002*\nM!qC\u0005\u0005\u0005+\t\tK\u0001\u0003MSN$\b\u0007\u0002B\r\u0005;\u0001b!a\u0013\u0002l\tm\u0001\u0003BA\u0018\u0005;!1Ba\bS\u0003\u0003\u0005\tQ!\u0001\u00026\tAA%]7be.$\u0003(\u0001\u0007j]Z|GN^3e'\u0016$\b%A\bj]Z|GN^3e'\u0016$x\fJ3r)\u0011\t)Ba\n\t\u0013\u0005=\u0017+!AA\u0002\tE\u0011aB3wC2\u001cV\r^\u000b\u0003\u0005[\u0001b!!+\u0003\u0014\t=\u0002\u0007\u0002B\u0019\u0005k\u0001b!a\u0013\u0002l\tM\u0002\u0003BA\u0018\u0005k!1Ba\u000eV\u0003\u0003\u0005\tQ!\u0001\u00026\tAA%]7be.$\u0013(\u0001\u0005fm\u0006d7+\u001a;!\u0003-)g/\u00197TKR|F%Z9\u0015\t\u0005U!q\b\u0005\n\u0003\u001f$\u0016\u0011!a\u0001\u0005[!\u0002\"!=\u0003D\t5#\u0011\f\u0005\b\u0003o4\u0006\u0019\u0001B#a\u0011\u00119Ea\u0013\u0011\r\u0005-\u00131\u000eB%!\u0011\tyCa\u0013\u0005\u0019\t\r!1IA\u0001\u0002\u0003\u0015\t!!\u000e\t\u000f\t5a\u000b1\u0001\u0003PA1\u0011\u0011\u0016B\n\u0005#\u0002DAa\u0015\u0003XA1\u00111JA6\u0005+\u0002B!a\f\u0003X\u0011a!q\u0004B'\u0003\u0003\u0005\tQ!\u0001\u00026!9!\u0011\u0006,A\u0002\tm\u0003CBAU\u0005'\u0011i\u0006\r\u0003\u0003`\t\r\u0004CBA&\u0003W\u0012\t\u0007\u0005\u0003\u00020\t\rD\u0001\u0004B\u001c\u00053\n\t\u0011!A\u0003\u0002\u0005U\u0012aB4fi\"+\u0017\rZ\u000b\u0003\u0005S\u0002b!a\u0013\u0002l\u0005u\u0012\u0001B2paf$\u0002\"!=\u0003p\tE$1\u000f\u0005\n\u0003oD\u0006\u0013!a\u0001\u0005\u000bB\u0011B!\u0004Y!\u0003\u0005\rAa\u0014\t\u0013\t%\u0002\f%AA\u0002\tm\u0013AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0005sRCA!\u001b\u0003|-\u0012!Q\u0010\t\u0005\u0005\u007f\u0012I)\u0004\u0002\u0003\u0002*!!1\u0011BC\u0003%)hn\u00195fG.,GMC\u0002\u0003\br\f!\"\u00198o_R\fG/[8o\u0013\u0011\u0011YI!!\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\tE%\u0006\u0002B\t\u0005w\nabY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u0003\u0018*\"!Q\u0006B>\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011!Q\u0014\t\u0005\u0005?\u0013I+\u0004\u0002\u0003\"*!!1\u0015BS\u0003\u0011a\u0017M\\4\u000b\u0005\t\u001d\u0016\u0001\u00026bm\u0006LAAa+\u0003\"\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\"A!-\u0011\t\u0005\r!1W\u0005\u0004\u0005kc(aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA\u001f\u0005wC\u0011\"a4_\u0003\u0003\u0005\rA!-\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"A!1\u0011\r\t\r'QYA\u001f\u001b\t\tI&\u0003\u0003\u0003H\u0006e#\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$BA!4\u0003TB!\u00111\u0001Bh\u0013\r\u0011\t\u000e \u0002\b\u0005>|G.Z1o\u0011%\ty\rYA\u0001\u0002\u0004\ti$\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003\u0002BO\u00053D\u0011\"a4b\u0003\u0003\u0005\rA!-\u0002\u0011!\f7\u000f[\"pI\u0016$\"A!-\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"A!(\u0002\r\u0015\fX/\u00197t)\u0011\u0011iMa:\t\u0013\u0005=G-!AA\u0002\u0005u\u0012\u0001\u00035fC\u0012|F%Z9\u0015\t\u0005U!Q\u001e\u0005\n\u0003\u001f,\u0014\u0011!a\u0001\u0003W\fQ\u0001[3bI\u0002\"\u0002\"a,\u0003t\nu8q\u0001\u0005\b\u0003k;\u0004\u0019\u0001B{a\u0011\u00119Pa?\u0011\r\u0005-\u0013Q\u0018B}!\u0011\tyCa?\u0005\u0019\u0005\u0015'1_A\u0001\u0002\u0003\u0015\t!!\u000e\t\u000f\u0005Ew\u00071\u0001\u0003\u0000B\"1\u0011AB\u0003!\u0019\tY%a\u001b\u0004\u0004A!\u0011qFB\u0003\t1\tiN!@\u0002\u0002\u0003\u0005)\u0011AA\u001b\u0011\u001d\t9o\u000ea\u0001\u0003W\faaZ3u!>\u001cXCAA;)!\tyka\u0004\u0004\u0012\rM\u0001\"CA[sA\u0005\t\u0019\u0001B{\u0011%\t\t.\u000fI\u0001\u0002\u0004\u0011y\u0010C\u0005\u0002hf\u0002\n\u00111\u0001\u0002lV\u00111q\u0003\u0016\u0005\u00073\u0011Y\b\u0005\u0004\u0002L\u0005u\u0016QH\u000b\u0003\u0007;QC!a;\u0003|Q!\u0011QHB\u0011\u0011%\tymPA\u0001\u0002\u0004\u0011\t\f\u0006\u0003\u0003N\u000e\u0015\u0002\"CAh\u0003\u0006\u0005\t\u0019AA\u001f)\u0011\u0011ij!\u000b\t\u0013\u0005=')!AA\u0002\tEF\u0003\u0002Bg\u0007[A\u0011\"a4F\u0003\u0003\u0005\r!!\u00101\t\rE2Q\u0007\t\u0007\u0003\u0017\nila\r\u0011\t\u0005=2Q\u0007\u0003\f\u0007oI\u0012\u0011!A\u0001\u0006\u0003\t)D\u0001\u0005%c6\f'o\u001b\u00135\u0003\t\u0011\b%A\u0003s?\u0012*\u0017\u000f\u0006\u0003\u0002\u0016\r}\u0002\"CAh1\u0005\u0005\t\u0019AAT)\u0011\u0019\u0019e!\u0013\u0011\u000b\u0005-cc!\u0012\u0011\t\u0005=2q\t\u0003\t\u0003g1BQ1\u0001\u00026!9\u00111\u0015\u000eA\u0002\r-\u0003\u0003CAU\u0003W\u000byk!\u00141\t\r=31\u000b\t\u0007\u0003\u0017\nil!\u0015\u0011\t\u0005=21\u000b\u0003\r\u0007o\u0019I%!A\u0001\u0002\u000b\u0005\u0011QG\u0001\nO\u0016$(+Z:vYR,\"a!\u0017\u0011\r\u0005-\u0013QXB#+\u0011\u0019ifa\u0019\u0015\t\r}3Q\r\t\u0006\u0003\u001722\u0011\r\t\u0005\u0003_\u0019\u0019\u0007B\u0004\u00024q\u0011\r!!\u000e\t\u0013\u0005\rF\u0004%AA\u0002\r-S\u0003BB5\u0007[*\"aa\u001b+\t\u0005\u001d&1\u0010\u0003\b\u0003gi\"\u0019AA\u001b)\u0011\tid!\u001d\t\u0013\u0005=\u0007%!AA\u0002\tEF\u0003\u0002Bg\u0007kB\u0011\"a4#\u0003\u0003\u0005\r!!\u0010\u0015\t\tu5\u0011\u0010\u0005\n\u0003\u001f\u001c\u0013\u0011!a\u0001\u0005c#BA!4\u0004~!I\u0011q\u001a\u0014\u0002\u0002\u0003\u0007\u0011Q\b\t\u0005\u0003_\u0019\t\tB\u0006\u0004\u0004\u0016\t\t\u0011!A\u0003\u0002\u0005U\"\u0001\u0003\u0013r[\u0006\u00148\u000e\n\u001a\u0002{M\u001c\u0017\r\\1%kRLG\u000e\n9beNLgn\u001a\u0013d_6\u0014\u0017N\\1u_J$\u0003+Y2le\u0006$\b+\u0019:tKJ\u001cH\u0005\n:fGV\u00148/[8o\u0011\u0016\fGm\u001d\u0011\u0011\u0011\u0005M\u0013QLA;\u0003c\fag]2bY\u0006$S\u000f^5mIA\f'o]5oO\u0012\u001aw.\u001c2j]\u0006$xN\u001d\u0013QC\u000e\\'/\u0019;QCJ\u001cXM]:%I1\u00148\u000b^1dW\u0002\u0002b!!+\u0003\u0014\u0005=\u0016AB:pkJ\u001cW-\u0006\u0002\u0004\u0012B!!qTBJ\u0013\u0011\u0019)J!)\u0003\u0019\rC\u0017M]*fcV,gnY3\u0002\r=4gm]3u\u0003\u00151\u0017N]:u+\t\ti#\u0001\u0003sKN$XCAA\u0011\u0003\r\u0001xn]\u0001\u0006CR,e\u000eZ\u000b\u0003\u0005\u001b\f1g]2bY\u0006$S\u000f^5mIA\f'o]5oO\u0012\u001aw.\u001c2j]\u0006$xN\u001d\u0013QC\u000e\\'/\u0019;QCJ\u001cXM]:%I\r\f7\r[3\u0016\u0005\u0005E\u0013AO:dC2\fG%\u001e;jY\u0012\u0002\u0018M]:j]\u001e$3m\\7cS:\fGo\u001c:%!\u0006\u001c7N]1u!\u0006\u00148/\u001a:tI\u0011:W\r\u001e$s_6\u001c\u0015m\u00195f+\u0011\u0019yka.\u0015\t\rE61\u0018\t\u0007\u0003\u0007\tioa-\u0011\u000b\u0005-cc!.\u0011\t\u0005=2q\u0017\u0003\b\u0007s{!\u0019AA\u001b\u0005\t!&\u0007C\u0004\u0004>>\u0001\raa0\u0002\u0003A\u0004b!a\u0013\u0002l\rU\u0016aP:dC2\fG%\u001e;jY\u0012\u0002\u0018M]:j]\u001e$3m\\7cS:\fGo\u001c:%!\u0006\u001c7N]1u!\u0006\u00148/\u001a:tI\u0011*\b\u000fZ1uK\u000e\u000b7\r[3B]\u0012<U\r^\u000b\u0005\u0007\u000b\u001cY\r\u0006\u0004\u0004H\u000e57\u0011\u001b\t\u0006\u0003\u001722\u0011\u001a\t\u0005\u0003_\u0019Y\rB\u0004\u0004:B\u0011\r!!\u000e\t\u000f\ru\u0006\u00031\u0001\u0004PB1\u00111JA6\u0007\u0013Dqaa5\u0011\u0001\u0004\u00199-A\u0001x\u0003q\u001a8-\u00197bIU$\u0018\u000e\u001c\u0013qCJ\u001c\u0018N\\4%G>l'-\u001b8bi>\u0014H\u0005U1dWJ\fG\u000fU1sg\u0016\u00148\u000f\n\u0013sK\u000e,(o]5p]\"+\u0017\rZ:\u0016\u0005\r\u001d\u0015!N:dC2\fG%\u001e;jY\u0012\u0002\u0018M]:j]\u001e$3m\\7cS:\fGo\u001c:%!\u0006\u001c7N]1u!\u0006\u00148/\u001a:tI\u0011b'o\u0015;bG.,\"aa#\u0002sM\u001c\u0017\r\\1%kRLG\u000e\n9beNLgn\u001a\u0013d_6\u0014\u0017N\\1u_J$\u0003+Y2le\u0006$\b+\u0019:tKJ\u001cH\u0005\n7s'R\f7m[0%KF$B!!\u0006\u0004b\"I\u0011qZ\n\u0002\u0002\u0003\u000711R\u0001\u0007a\"\u0014\u0018m]3\u0016\t\r\u001d8Q \u000b\u0005\u0007S\u001cy\u0010E\u0003\u0002L-\u001cYPA\u0007QC\u000e\\'/\u0019;QCJ\u001cXM]\u000b\u0005\u0007_\u001c)pE\u0002l\u0007c\u0004b!a\u0013\u0002l\rM\b\u0003BA\u0018\u0007k$\u0001\"a\rl\t\u000b\u0007\u0011Q\u0007\u000b\u0003\u0007s\u0004R!a\u0013l\u0007g\u0004B!a\f\u0004~\u00129\u00111\u0007\u000bC\u0002\u0005U\u0002bBB_)\u0001\u0007A\u0011\u0001\t\u0007\u0003\u0017\nYga?\u0002!\u001d,G\u000fU8t\rJ|WNU3tk2$H\u0003BA;\t\u000fAq!a)\u0016\u0001\u0004!I\u0001\r\u0003\u0005\f\u0011=\u0001CBA&\u0003{#i\u0001\u0005\u0003\u00020\u0011=A\u0001\u0004C\t\t\u000f\t\t\u0011!A\u0003\u0002\u0005U\"\u0001\u0003\u0013r[\u0006\u00148\u000eJ\u001a\u0002\u00135+Wn\\#oiJL\bcAA&QM)\u0001&!\u0001\u0005\u001aA!A1\u0004C\u0011\u001b\t!iB\u0003\u0003\u0005 \t\u0015\u0016AA5p\u0013\u0011\ty\n\"\b\u0015\u0005\u0011U\u0011!B1qa2LX\u0003\u0002C\u0015\t_!B\u0001b\u000b\u00052A)\u00111\n\f\u0005.A!\u0011q\u0006C\u0018\t\u001d\t\u0019d\u000bb\u0001\u0003kAq!a),\u0001\u0004!\u0019\u0004\u0005\u0005\u0002*\u0006-\u0016q\u0016C\u001ba\u0011!9\u0004b\u000f\u0011\r\u0005-\u0013Q\u0018C\u001d!\u0011\ty\u0003b\u000f\u0005\u0019\r]B\u0011GA\u0001\u0002\u0003\u0015\t!!\u000e\u0002\u000fUt\u0017\r\u001d9msV!A\u0011\tC,)\u0011!\u0019\u0005b\u0014\u0011\r\u0005\r\u0011Q\u001eC#!!\tI+a+\u00020\u0012\u001d\u0003\u0007\u0002C%\t\u001b\u0002b!a\u0013\u0002>\u0012-\u0003\u0003BA\u0018\t\u001b\"1ba\u000e-\u0003\u0003\u0005\tQ!\u0001\u00026!IA\u0011\u000b\u0017\u0002\u0002\u0003\u0007A1K\u0001\u0004q\u0012\u0002\u0004#BA&-\u0011U\u0003\u0003BA\u0018\t/\"q!a\r-\u0005\u0004\t)$\u0001\u0002M%B\u0019\u00111J$\u0014\u000b\u001d#y\u0006\"\u0007\u0011\u0019\u0011\u0005Dq\rC6\tg\nY/a,\u000e\u0005\u0011\r$b\u0001C3y\u00069!/\u001e8uS6,\u0017\u0002\u0002C5\tG\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c84a\u0011!i\u0007\"\u001d\u0011\r\u0005-\u0013Q\u0018C8!\u0011\ty\u0003\"\u001d\u0005\u0017\u0005\u0015w)!A\u0001\u0002\u000b\u0005\u0011Q\u0007\u0019\u0005\tk\"I\b\u0005\u0004\u0002L\u0005-Dq\u000f\t\u0005\u0003_!I\bB\u0006\u0002^\u001e\u000b\t\u0011!A\u0003\u0002\u0005UBC\u0001C.)!\ty\u000bb \u0005\n\u0012M\u0005bBA[\u0015\u0002\u0007A\u0011\u0011\u0019\u0005\t\u0007#9\t\u0005\u0004\u0002L\u0005uFQ\u0011\t\u0005\u0003_!9\t\u0002\u0007\u0002F\u0012}\u0014\u0011!A\u0001\u0006\u0003\t)\u0004C\u0004\u0002R*\u0003\r\u0001b#1\t\u00115E\u0011\u0013\t\u0007\u0003\u0017\nY\u0007b$\u0011\t\u0005=B\u0011\u0013\u0003\r\u0003;$I)!A\u0001\u0002\u000b\u0005\u0011Q\u0007\u0005\b\u0003OT\u0005\u0019AAv)\u0011!9\nb,\u0011\r\u0005\r\u0011Q\u001eCM!)\t\u0019\u0001b'\u0005 \u0012\u001d\u00161^\u0005\u0004\t;c(A\u0002+va2,7\u0007\r\u0003\u0005\"\u0012\u0015\u0006CBA&\u0003{#\u0019\u000b\u0005\u0003\u00020\u0011\u0015FaCAc\u0017\u0006\u0005\t\u0011!B\u0001\u0003k\u0001D\u0001\"+\u0005.B1\u00111JA6\tW\u0003B!a\f\u0005.\u0012Y\u0011Q\\&\u0002\u0002\u0003\u0005)\u0011AA\u001b\u0011%!\tfSA\u0001\u0002\u0004\ty+\u0001\u0003IK\u0006$\u0007cAA&MN)a\rb.\u0005\u001aAaA\u0011\rC4\ts#\t\rb3\u0002rB\"A1\u0018C`!\u0019\tY%a\u001b\u0005>B!\u0011q\u0006C`\t-\u0011\u0019AZA\u0001\u0002\u0003\u0015\t!!\u000e\u0011\r\u0005%&1\u0003Cba\u0011!)\r\"3\u0011\r\u0005-\u00131\u000eCd!\u0011\ty\u0003\"3\u0005\u0017\t}a-!A\u0001\u0002\u000b\u0005\u0011Q\u0007\t\u0007\u0003S\u0013\u0019\u0002\"41\t\u0011=G1\u001b\t\u0007\u0003\u0017\nY\u0007\"5\u0011\t\u0005=B1\u001b\u0003\f\u0005o1\u0017\u0011!A\u0001\u0006\u0003\t)\u0004\u0006\u0002\u00054RA\u0011\u0011\u001fCm\tG$y\u000fC\u0004\u0002x&\u0004\r\u0001b71\t\u0011uG\u0011\u001d\t\u0007\u0003\u0017\nY\u0007b8\u0011\t\u0005=B\u0011\u001d\u0003\r\u0005\u0007!I.!A\u0001\u0002\u000b\u0005\u0011Q\u0007\u0005\b\u0005\u001bI\u0007\u0019\u0001Cs!\u0019\tIKa\u0005\u0005hB\"A\u0011\u001eCw!\u0019\tY%a\u001b\u0005lB!\u0011q\u0006Cw\t1\u0011y\u0002b9\u0002\u0002\u0003\u0005)\u0011AA\u001b\u0011\u001d\u0011I#\u001ba\u0001\tc\u0004b!!+\u0003\u0014\u0011M\b\u0007\u0002C{\ts\u0004b!a\u0013\u0002l\u0011]\b\u0003BA\u0018\ts$ABa\u000e\u0005p\u0006\u0005\t\u0011!B\u0001\u0003k!B\u0001\"@\u0006\u001eA1\u00111AAw\t\u007f\u0004\"\"a\u0001\u0005\u001c\u0016\u0005Q\u0011BC\na\u0011)\u0019!b\u0002\u0011\r\u0005-\u00131NC\u0003!\u0011\ty#b\u0002\u0005\u0017\t\r!.!A\u0001\u0002\u000b\u0005\u0011Q\u0007\t\u0007\u0003S\u0013\u0019\"b\u00031\t\u00155Q\u0011\u0003\t\u0007\u0003\u0017\nY'b\u0004\u0011\t\u0005=R\u0011\u0003\u0003\f\u0005?Q\u0017\u0011!A\u0001\u0006\u0003\t)\u0004\u0005\u0004\u0002*\nMQQ\u0003\u0019\u0005\u000b/)Y\u0002\u0005\u0004\u0002L\u0005-T\u0011\u0004\t\u0005\u0003_)Y\u0002B\u0006\u00038)\f\t\u0011!A\u0003\u0002\u0005U\u0002\"\u0003C)U\u0006\u0005\t\u0019AAy\u00039\u0001\u0018M]:feJ\u0002\u0018mY6sCR,B!b\t\u0006*Q!QQEC\u0016!\u0015\tYe[C\u0014!\u0011\ty#\"\u000b\u0005\u000f\u0005MRN1\u0001\u00026!A1QX7\u0005\u0002\u0004)i\u0003\u0005\u0004\u0002\u0004\u0015=R1G\u0005\u0004\u000bca(\u0001\u0003\u001fcs:\fW.\u001a \u0011\r\u0005-\u00131NC\u0014\u0003\u0019\u0011XmY1mYR1Q\u0011HC#\u000b#\u0002b!a\u0001\u0002n\u0016m\u0002\u0007BC\u001f\u000b\u0003\u0002R!a\u0013\u0017\u000b\u007f\u0001B!a\f\u0006B\u0011YQ1\t8\u0002\u0002\u0003\u0005)\u0011AA\u001b\u0005%!\u0013/\\1sW\u0012\n\u0014\u0007C\u0004\u0004>:\u0004\r!b\u00121\t\u0015%SQ\n\t\u0007\u0003\u0017\nY'b\u0013\u0011\t\u0005=RQ\n\u0003\r\u000b\u001f*)%!A\u0001\u0002\u000b\u0005\u0011Q\u0007\u0002\nIEl\u0017M]6%cABq!b\u0015o\u0001\u0004))&\u0001\u0002j]B)\u00111\n\u0002\u0006XA!\u00111JC-\u0013\u0011)Y&!\u0004\u0003\t\u0015cW-\\\u0001\bg\u0016$X\u000f\u001d'S)!\t)\"\"\u0019\u0006n\u0015e\u0004bBB__\u0002\u0007Q1\r\u0019\u0005\u000bK*I\u0007\u0005\u0004\u0002L\u0005-Tq\r\t\u0005\u0003_)I\u0007\u0002\u0007\u0006l\u0015\u0005\u0014\u0011!A\u0001\u0006\u0003\t)DA\u0005%c6\f'o\u001b\u00132g!9Q1K8A\u0002\u0015=\u0004\u0007BC9\u000bk\u0002R!a\u0013\u0003\u000bg\u0002B!a\f\u0006v\u0011aQqOC7\u0003\u0003\u0005\tQ!\u0001\u00026\tIA%]7be.$\u0013\u0007\u000e\u0005\b\u000bwz\u0007\u0019AAX\u0003%\u0011Xm\u0019#fi\u0016\u001cG/\u0001\u0005me\u0006s7o^3s+\u0011)\t)b\"\u0015\u0011\u0015\rU\u0011RCG\u000b\u001f\u0003b!a\u0013\u0002>\u0016\u0015\u0005\u0003BA\u0018\u000b\u000f#q!a\rq\u0005\u0004\t)\u0004C\u0004\u0004>B\u0004\r!b#\u0011\r\u0005-\u00131NCC\u0011\u001d)\u0019\u0006\u001da\u0001\u000b+Bq!\"%q\u0001\u0004\ty+\u0001\u0005he><\u0018M\u00197f\u0003\u0011iW-\\8\u0016\t\u0015]UQ\u0014\u000b\u0005\u000b3+y\nE\u0003\u0002L-,Y\n\u0005\u0003\u00020\u0015uEaBA\u001ac\n\u0007\u0011Q\u0007\u0005\b\u0007{\u000b\b\u0019ACQ!\u0019\tY%a\u001b\u0006\u001c\u0006!qM]8x+\u0011)9+\",\u0015\u0011\u0015%VqVCZ\u000bk\u0003b!a\u0013\u0002>\u0016-\u0006\u0003BA\u0018\u000b[#q!a\rs\u0005\u0004\t)\u0004C\u0004\u0004>J\u0004\r!\"-\u0011\r\u0005-\u00131NCV\u0011\u001d\u0019iJ\u001da\u0001\u000b+Bq!a:s\u0001\u0004\t\t0\u0001\u0007tkB,'\u000f\n9ie\u0006\u001cX-\u0006\u0003\u0006<\u0016\u0005G\u0003BC_\u000b\u0007\u0004b!a\u0013\u0002l\u0015}\u0006\u0003BA\u0018\u000b\u0003$q!a\rt\u0005\u0004\t)\u0004C\u0004\u0004>N\u0004\r!\"0\n\t\r\r\u0018QB\u0001\rgV\u0004XM\u001d\u0013QCJ\u001cXM]\u000b\u0005\u000b\u0017,\t\u000e\u0006\u0003\u0006N\u0016M\u0007CBA&\u0003W*y\r\u0005\u0003\u00020\u0015EGaBA\u001ai\n\u0007\u0011Q\u0007\u0005\b\u000b+$\b\u0019ACl\u0003\u00051\u0007\u0003CA\u0002\u000b3,i.b9\n\u0007\u0015mGPA\u0005Gk:\u001cG/[8ocA!\u00111JCp\u0013\u0011)\t/!\u0004\u0003\u000b%s\u0007/\u001e;\u0011\r\u0005-\u0013QXCh\u0013\u0011)9/!\u0004\u0002\rA\u000b'o]3s\u0001"
)
public interface PackratParsers extends Parsers {
   MemoEntry$ scala$util$parsing$combinator$PackratParsers$$MemoEntry();

   LR$ scala$util$parsing$combinator$PackratParsers$$LR();

   Head$ scala$util$parsing$combinator$PackratParsers$$Head();

   // $FF: synthetic method
   Parsers.Parser scala$util$parsing$combinator$PackratParsers$$super$phrase(final Parsers.Parser p);

   // $FF: synthetic method
   Parsers.Parser scala$util$parsing$combinator$PackratParsers$$super$Parser(final Function1 f);

   // $FF: synthetic method
   static PackratParser phrase$(final PackratParsers $this, final Parsers.Parser p) {
      return $this.phrase(p);
   }

   default PackratParser phrase(final Parsers.Parser p) {
      Parsers.Parser q = this.scala$util$parsing$combinator$PackratParsers$$super$phrase(p);
      return new PackratParser(q) {
         // $FF: synthetic field
         private final PackratParsers $outer;
         private final Parsers.Parser q$1;

         public Parsers.ParseResult apply(final Reader in) {
            if (in instanceof PackratReader && ((PackratReader)in).scala$util$parsing$combinator$PackratParsers$PackratReader$$$outer() == this.$outer) {
               PackratReader var4 = (PackratReader)in;
               return this.q$1.apply(var4);
            } else {
               return this.q$1.apply(this.$outer.new PackratReader(in));
            }
         }

         public {
            if (PackratParsers.this == null) {
               throw null;
            } else {
               this.$outer = PackratParsers.this;
               this.q$1 = q$1;
            }
         }
      };
   }

   // $FF: synthetic method
   static Position scala$util$parsing$combinator$PackratParsers$$getPosFromResult$(final PackratParsers $this, final Parsers.ParseResult r) {
      return $this.scala$util$parsing$combinator$PackratParsers$$getPosFromResult(r);
   }

   default Position scala$util$parsing$combinator$PackratParsers$$getPosFromResult(final Parsers.ParseResult r) {
      return r.next().pos();
   }

   // $FF: synthetic method
   static PackratParser parser2packrat$(final PackratParsers $this, final Function0 p) {
      return $this.parser2packrat(p);
   }

   default PackratParser parser2packrat(final Function0 p) {
      LazyRef q$lzy = new LazyRef();
      return this.memo(this.scala$util$parsing$combinator$PackratParsers$$super$Parser((in) -> q$2(q$lzy, p).apply(in)));
   }

   // $FF: synthetic method
   static Option scala$util$parsing$combinator$PackratParsers$$recall$(final PackratParsers $this, final Parsers.Parser p, final PackratReader in) {
      return $this.scala$util$parsing$combinator$PackratParsers$$recall(p, in);
   }

   default Option scala$util$parsing$combinator$PackratParsers$$recall(final Parsers.Parser p, final PackratReader in) {
      Option cached = in.scala$util$parsing$combinator$PackratParsers$$getFromCache(p);
      Option head = in.scala$util$parsing$combinator$PackratParsers$$recursionHeads().get(in.pos());
      if (.MODULE$.equals(head)) {
         return cached;
      } else {
         if (head instanceof Some) {
            Some var7 = (Some)head;
            Head h = (Head)var7.value();
            if (h != null) {
               Parsers.Parser hp = h.headParser();
               List involved = h.involvedSet();
               List evalSet = h.evalSet();
               if (cached.isEmpty() && !involved.$colon$colon(hp).contains(p)) {
                  return new Some(new MemoEntry(new Right(new Parsers.Failure("dummy ", in))));
               }

               if (evalSet.contains(p)) {
                  h.evalSet_$eq(h.evalSet().filterNot((x$1) -> BoxesRunTime.boxToBoolean($anonfun$recall$1(p, x$1))));
                  Parsers.ParseResult tempRes = p.apply(in);
                  MemoEntry tempEntry = (MemoEntry)cached.get();
                  tempEntry.r_$eq(new Right(tempRes));
               }

               return cached;
            }
         }

         throw new MatchError(head);
      }
   }

   // $FF: synthetic method
   static void scala$util$parsing$combinator$PackratParsers$$setupLR$(final PackratParsers $this, final Parsers.Parser p, final PackratReader in, final LR recDetect) {
      $this.scala$util$parsing$combinator$PackratParsers$$setupLR(p, in, recDetect);
   }

   default void scala$util$parsing$combinator$PackratParsers$$setupLR(final Parsers.Parser p, final PackratReader in, final LR recDetect) {
      if (recDetect.head().isEmpty()) {
         recDetect.head_$eq(new Some(new Head(p, scala.collection.immutable.Nil..MODULE$, scala.collection.immutable.Nil..MODULE$)));
      }

      in.scala$util$parsing$combinator$PackratParsers$$lrStack().takeWhile((x$2) -> BoxesRunTime.boxToBoolean($anonfun$setupLR$1(p, x$2))).foreach((x) -> {
         x.head_$eq(recDetect.head());
         return recDetect.head().map((h) -> {
            $anonfun$setupLR$3(x, h);
            return BoxedUnit.UNIT;
         });
      });
   }

   // $FF: synthetic method
   static Parsers.ParseResult scala$util$parsing$combinator$PackratParsers$$lrAnswer$(final PackratParsers $this, final Parsers.Parser p, final PackratReader in, final LR growable) {
      return $this.scala$util$parsing$combinator$PackratParsers$$lrAnswer(p, in, growable);
   }

   default Parsers.ParseResult scala$util$parsing$combinator$PackratParsers$$lrAnswer(final Parsers.Parser p, final PackratReader in, final LR growable) {
      if (growable != null) {
         Parsers.ParseResult seed = growable.seed();
         Option var8 = growable.head();
         if (var8 instanceof Some) {
            Some var9 = (Some)var8;
            Head head = (Head)var9.value();
            Parsers.Parser var10000 = head.getHead();
            if (var10000 == null) {
               if (p != null) {
                  return seed;
               }
            } else if (!var10000.equals(p)) {
               return seed;
            }

            in.scala$util$parsing$combinator$PackratParsers$$updateCacheAndGet(p, new MemoEntry(new Right(seed)));
            if (seed instanceof Parsers.Failure) {
               Parsers.Failure var13 = (Parsers.Failure)seed;
               return var13;
            }

            if (seed instanceof Parsers.Error) {
               Parsers.Error var14 = (Parsers.Error)seed;
               return var14;
            }

            if (seed instanceof Parsers.Success) {
               return this.grow(p, in, head);
            }

            throw new MatchError(seed);
         }
      }

      throw new Exception("lrAnswer with no head !!");
   }

   // $FF: synthetic method
   static PackratParser memo$(final PackratParsers $this, final Parsers.Parser p) {
      return $this.memo(p);
   }

   default PackratParser memo(final Parsers.Parser p) {
      return new PackratParser(p) {
         // $FF: synthetic field
         private final PackratParsers $outer;
         private final Parsers.Parser p$4;

         public Parsers.ParseResult apply(final Reader in) {
            PackratReader inMem = (PackratReader)in;
            Option m = this.$outer.scala$util$parsing$combinator$PackratParsers$$recall(this.p$4, inMem);
            if (.MODULE$.equals(m)) {
               LR base = this.$outer.new LR(this.$outer.new Failure("Base Failure", in), this.p$4, .MODULE$);
               inMem.scala$util$parsing$combinator$PackratParsers$$lrStack_$eq(inMem.scala$util$parsing$combinator$PackratParsers$$lrStack().$colon$colon(base));
               inMem.scala$util$parsing$combinator$PackratParsers$$updateCacheAndGet(this.p$4, this.$outer.new MemoEntry(new Left(base)));
               Parsers.ParseResult tempRes = this.p$4.apply(in);
               inMem.scala$util$parsing$combinator$PackratParsers$$lrStack_$eq((List)inMem.scala$util$parsing$combinator$PackratParsers$$lrStack().tail());
               Option var12 = base.head();
               if (.MODULE$.equals(var12)) {
                  inMem.scala$util$parsing$combinator$PackratParsers$$updateCacheAndGet(this.p$4, this.$outer.new MemoEntry(new Right(tempRes)));
                  return tempRes;
               } else if (var12 instanceof Some) {
                  base.seed_$eq(tempRes);
                  Parsers.ParseResult res = this.$outer.scala$util$parsing$combinator$PackratParsers$$lrAnswer(this.p$4, inMem, base);
                  return res;
               } else {
                  throw new MatchError(var12);
               }
            } else if (m instanceof Some) {
               Some var14 = (Some)m;
               MemoEntry mEntry = (MemoEntry)var14.value();
               if (mEntry != null) {
                  Either var17 = mEntry.r();
                  if (var17 instanceof Left) {
                     Left var18 = (Left)var17;
                     LR recDetect = (LR)var18.value();
                     this.$outer.scala$util$parsing$combinator$PackratParsers$$setupLR(this.p$4, inMem, recDetect);
                     if (recDetect != null) {
                        Parsers.ParseResult seed = recDetect.seed();
                        return seed;
                     }

                     throw new MatchError(recDetect);
                  }
               }

               if (mEntry != null) {
                  Either var22 = mEntry.r();
                  if (var22 instanceof Right) {
                     Right var23 = (Right)var22;
                     Parsers.ParseResult res = (Parsers.ParseResult)var23.value();
                     if (res instanceof Parsers.ParseResult) {
                        return res;
                     }
                  }
               }

               throw new MatchError(mEntry);
            } else {
               throw new MatchError(m);
            }
         }

         public {
            if (PackratParsers.this == null) {
               throw null;
            } else {
               this.$outer = PackratParsers.this;
               this.p$4 = p$4;
            }
         }
      };
   }

   private Parsers.ParseResult grow(final Parsers.Parser p, final PackratReader rest, final Head head) {
      while(true) {
         rest.scala$util$parsing$combinator$PackratParsers$$recursionHeads().put(rest.pos(), head);
         MemoEntry var9 = (MemoEntry)rest.scala$util$parsing$combinator$PackratParsers$$getFromCache(p).get();
         if (var9 != null) {
            Either var10 = var9.r();
            if (var10 instanceof Right) {
               Right var11 = (Right)var10;
               Parsers.ParseResult x = (Parsers.ParseResult)var11.value();
               head.evalSet_$eq(head.involvedSet());
               Parsers.ParseResult tempRes = p.apply(rest);
               if (tempRes instanceof Parsers.Success) {
                  Parsers.Success var15 = (Parsers.Success)tempRes;
                  if (this.scala$util$parsing$combinator$PackratParsers$$getPosFromResult(x).$less(this.scala$util$parsing$combinator$PackratParsers$$getPosFromResult(tempRes))) {
                     rest.scala$util$parsing$combinator$PackratParsers$$updateCacheAndGet(p, new MemoEntry(new Right(var15)));
                     head = head;
                     rest = rest;
                     p = p;
                     continue;
                  }

                  rest.scala$util$parsing$combinator$PackratParsers$$recursionHeads().$minus$eq(rest.pos());
                  MemoEntry var16 = (MemoEntry)rest.scala$util$parsing$combinator$PackratParsers$$getFromCache(p).get();
                  if (var16 != null) {
                     Either var17 = var16.r();
                     if (var17 instanceof Right) {
                        Right var18 = (Right)var17;
                        Parsers.ParseResult x = (Parsers.ParseResult)var18.value();
                        if (x instanceof Parsers.ParseResult) {
                           return x;
                        }
                     }
                  }

                  throw new Exception("impossible match");
               }

               rest.scala$util$parsing$combinator$PackratParsers$$recursionHeads().$minus$eq(rest.pos());
               return x;
            }
         }

         throw new Exception("impossible match");
      }
   }

   // $FF: synthetic method
   private static Parsers.Parser q$lzycompute$1(final LazyRef q$lzy$1, final Function0 p$1) {
      synchronized(q$lzy$1){}

      Parsers.Parser var3;
      try {
         var3 = q$lzy$1.initialized() ? (Parsers.Parser)q$lzy$1.value() : (Parsers.Parser)q$lzy$1.initialize(p$1.apply());
      } catch (Throwable var5) {
         throw var5;
      }

      return var3;
   }

   private static Parsers.Parser q$2(final LazyRef q$lzy$1, final Function0 p$1) {
      return q$lzy$1.initialized() ? (Parsers.Parser)q$lzy$1.value() : q$lzycompute$1(q$lzy$1, p$1);
   }

   // $FF: synthetic method
   static boolean $anonfun$recall$1(final Parsers.Parser p$2, final Parsers.Parser x$1) {
      boolean var10000;
      label23: {
         if (x$1 == null) {
            if (p$2 == null) {
               break label23;
            }
         } else if (x$1.equals(p$2)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   static boolean $anonfun$setupLR$1(final Parsers.Parser p$3, final LR x$2) {
      boolean var3;
      label23: {
         Parsers.Parser var10000 = x$2.rule();
         if (var10000 == null) {
            if (p$3 != null) {
               break label23;
            }
         } else if (!var10000.equals(p$3)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   static void $anonfun$setupLR$3(final LR x$3, final Head h) {
      Parsers.Parser var2 = x$3.rule();
      h.involvedSet_$eq(h.involvedSet().$colon$colon(var2));
   }

   static void $init$(final PackratParsers $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class PackratReader extends Reader {
      public final Reader scala$util$parsing$combinator$PackratParsers$PackratReader$$underlying;
      private final HashMap scala$util$parsing$combinator$PackratParsers$$cache;
      private final HashMap scala$util$parsing$combinator$PackratParsers$$recursionHeads;
      private List scala$util$parsing$combinator$PackratParsers$$lrStack;
      // $FF: synthetic field
      public final PackratParsers $outer;

      public HashMap scala$util$parsing$combinator$PackratParsers$$cache() {
         return this.scala$util$parsing$combinator$PackratParsers$$cache;
      }

      public Option scala$util$parsing$combinator$PackratParsers$$getFromCache(final Parsers.Parser p) {
         return this.scala$util$parsing$combinator$PackratParsers$$cache().get(new Tuple2(p, this.pos()));
      }

      public MemoEntry scala$util$parsing$combinator$PackratParsers$$updateCacheAndGet(final Parsers.Parser p, final MemoEntry w) {
         this.scala$util$parsing$combinator$PackratParsers$$cache().put(new Tuple2(p, this.pos()), w);
         return w;
      }

      public HashMap scala$util$parsing$combinator$PackratParsers$$recursionHeads() {
         return this.scala$util$parsing$combinator$PackratParsers$$recursionHeads;
      }

      public List scala$util$parsing$combinator$PackratParsers$$lrStack() {
         return this.scala$util$parsing$combinator$PackratParsers$$lrStack;
      }

      public void scala$util$parsing$combinator$PackratParsers$$lrStack_$eq(final List x$1) {
         this.scala$util$parsing$combinator$PackratParsers$$lrStack = x$1;
      }

      public CharSequence source() {
         return this.scala$util$parsing$combinator$PackratParsers$PackratReader$$underlying.source();
      }

      public int offset() {
         return this.scala$util$parsing$combinator$PackratParsers$PackratReader$$underlying.offset();
      }

      public Object first() {
         return this.scala$util$parsing$combinator$PackratParsers$PackratReader$$underlying.first();
      }

      public Reader rest() {
         return new PackratReader() {
            private final HashMap scala$util$parsing$combinator$PackratParsers$$cache = PackratReader.this.scala$util$parsing$combinator$PackratParsers$$cache();
            private final HashMap scala$util$parsing$combinator$PackratParsers$$recursionHeads = PackratReader.this.scala$util$parsing$combinator$PackratParsers$$recursionHeads();

            public HashMap scala$util$parsing$combinator$PackratParsers$$cache() {
               return this.scala$util$parsing$combinator$PackratParsers$$cache;
            }

            public HashMap scala$util$parsing$combinator$PackratParsers$$recursionHeads() {
               return this.scala$util$parsing$combinator$PackratParsers$$recursionHeads;
            }

            public {
               this.scala$util$parsing$combinator$PackratParsers$$lrStack_$eq(PackratReader.this.scala$util$parsing$combinator$PackratParsers$$lrStack());
            }
         };
      }

      public Position pos() {
         return this.scala$util$parsing$combinator$PackratParsers$PackratReader$$underlying.pos();
      }

      public boolean atEnd() {
         return this.scala$util$parsing$combinator$PackratParsers$PackratReader$$underlying.atEnd();
      }

      // $FF: synthetic method
      public PackratParsers scala$util$parsing$combinator$PackratParsers$PackratReader$$$outer() {
         return this.$outer;
      }

      public PackratReader(final Reader underlying) {
         this.scala$util$parsing$combinator$PackratParsers$PackratReader$$underlying = underlying;
         if (PackratParsers.this == null) {
            throw null;
         } else {
            this.$outer = PackratParsers.this;
            super();
            this.scala$util$parsing$combinator$PackratParsers$$cache = scala.collection.mutable.HashMap..MODULE$.empty();
            this.scala$util$parsing$combinator$PackratParsers$$recursionHeads = scala.collection.mutable.HashMap..MODULE$.empty();
            this.scala$util$parsing$combinator$PackratParsers$$lrStack = scala.collection.immutable.Nil..MODULE$;
         }
      }
   }

   private class MemoEntry implements Product, Serializable {
      private Either r;
      // $FF: synthetic field
      public final PackratParsers $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Either r() {
         return this.r;
      }

      public void r_$eq(final Either x$1) {
         this.r = x$1;
      }

      public Parsers.ParseResult getResult() {
         Either var2 = this.r();
         if (var2 instanceof Left) {
            Left var3 = (Left)var2;
            LR var4 = (LR)var3.value();
            if (var4 != null) {
               Parsers.ParseResult res = var4.seed();
               return res;
            }
         }

         if (var2 instanceof Right) {
            Right var6 = (Right)var2;
            Parsers.ParseResult res = (Parsers.ParseResult)var6.value();
            return res;
         } else {
            throw new MatchError(var2);
         }
      }

      public MemoEntry copy(final Either r) {
         return this.scala$util$parsing$combinator$PackratParsers$MemoEntry$$$outer().new MemoEntry(r);
      }

      public Either copy$default$1() {
         return this.r();
      }

      public String productPrefix() {
         return "MemoEntry";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.r();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof MemoEntry;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "r";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label52: {
               if (x$1 instanceof MemoEntry && ((MemoEntry)x$1).scala$util$parsing$combinator$PackratParsers$MemoEntry$$$outer() == this.scala$util$parsing$combinator$PackratParsers$MemoEntry$$$outer()) {
                  label42: {
                     MemoEntry var4 = (MemoEntry)x$1;
                     Either var10000 = this.r();
                     Either var5 = var4.r();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label42;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label42;
                     }

                     if (var4.canEqual(this)) {
                        break label52;
                     }
                  }
               }

               var6 = false;
               return var6;
            }
         }

         var6 = true;
         return var6;
      }

      // $FF: synthetic method
      public PackratParsers scala$util$parsing$combinator$PackratParsers$MemoEntry$$$outer() {
         return this.$outer;
      }

      public MemoEntry(final Either r) {
         this.r = r;
         if (PackratParsers.this == null) {
            throw null;
         } else {
            this.$outer = PackratParsers.this;
            super();
            Product.$init$(this);
         }
      }
   }

   private class MemoEntry$ implements Serializable {
      // $FF: synthetic field
      private final PackratParsers $outer;

      public final String toString() {
         return "MemoEntry";
      }

      public MemoEntry apply(final Either r) {
         return this.$outer.new MemoEntry(r);
      }

      public Option unapply(final MemoEntry x$0) {
         return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.r()));
      }

      public MemoEntry$() {
         if (PackratParsers.this == null) {
            throw null;
         } else {
            this.$outer = PackratParsers.this;
            super();
         }
      }
   }

   private class LR implements Product, Serializable {
      private Parsers.ParseResult seed;
      private Parsers.Parser rule;
      private Option head;
      // $FF: synthetic field
      public final PackratParsers $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Parsers.ParseResult seed() {
         return this.seed;
      }

      public void seed_$eq(final Parsers.ParseResult x$1) {
         this.seed = x$1;
      }

      public Parsers.Parser rule() {
         return this.rule;
      }

      public void rule_$eq(final Parsers.Parser x$1) {
         this.rule = x$1;
      }

      public Option head() {
         return this.head;
      }

      public void head_$eq(final Option x$1) {
         this.head = x$1;
      }

      public Position getPos() {
         return this.scala$util$parsing$combinator$PackratParsers$LR$$$outer().scala$util$parsing$combinator$PackratParsers$$getPosFromResult(this.seed());
      }

      public LR copy(final Parsers.ParseResult seed, final Parsers.Parser rule, final Option head) {
         return this.scala$util$parsing$combinator$PackratParsers$LR$$$outer().new LR(seed, rule, head);
      }

      public Parsers.ParseResult copy$default$1() {
         return this.seed();
      }

      public Parsers.Parser copy$default$2() {
         return this.rule();
      }

      public Option copy$default$3() {
         return this.head();
      }

      public String productPrefix() {
         return "LR";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.seed();
            case 1:
               return this.rule();
            case 2:
               return this.head();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof LR;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "seed";
            case 1:
               return "rule";
            case 2:
               return "head";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10;
         if (this != x$1) {
            label68: {
               if (x$1 instanceof LR && ((LR)x$1).scala$util$parsing$combinator$PackratParsers$LR$$$outer() == this.scala$util$parsing$combinator$PackratParsers$LR$$$outer()) {
                  label58: {
                     LR var4 = (LR)x$1;
                     Parsers.ParseResult var10000 = this.seed();
                     Parsers.ParseResult var5 = var4.seed();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label58;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label58;
                     }

                     Parsers.Parser var8 = this.rule();
                     Parsers.Parser var6 = var4.rule();
                     if (var8 == null) {
                        if (var6 != null) {
                           break label58;
                        }
                     } else if (!var8.equals(var6)) {
                        break label58;
                     }

                     Option var9 = this.head();
                     Option var7 = var4.head();
                     if (var9 == null) {
                        if (var7 != null) {
                           break label58;
                        }
                     } else if (!var9.equals(var7)) {
                        break label58;
                     }

                     if (var4.canEqual(this)) {
                        break label68;
                     }
                  }
               }

               var10 = false;
               return var10;
            }
         }

         var10 = true;
         return var10;
      }

      // $FF: synthetic method
      public PackratParsers scala$util$parsing$combinator$PackratParsers$LR$$$outer() {
         return this.$outer;
      }

      public LR(final Parsers.ParseResult seed, final Parsers.Parser rule, final Option head) {
         this.seed = seed;
         this.rule = rule;
         this.head = head;
         if (PackratParsers.this == null) {
            throw null;
         } else {
            this.$outer = PackratParsers.this;
            super();
            Product.$init$(this);
         }
      }
   }

   private class LR$ extends AbstractFunction3 implements Serializable {
      // $FF: synthetic field
      private final PackratParsers $outer;

      public final String toString() {
         return "LR";
      }

      public LR apply(final Parsers.ParseResult seed, final Parsers.Parser rule, final Option head) {
         return this.$outer.new LR(seed, rule, head);
      }

      public Option unapply(final LR x$0) {
         return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.seed(), x$0.rule(), x$0.head())));
      }

      public LR$() {
         if (PackratParsers.this == null) {
            throw null;
         } else {
            this.$outer = PackratParsers.this;
            super();
         }
      }
   }

   private class Head implements Product, Serializable {
      private Parsers.Parser headParser;
      private List involvedSet;
      private List evalSet;
      // $FF: synthetic field
      public final PackratParsers $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Parsers.Parser headParser() {
         return this.headParser;
      }

      public void headParser_$eq(final Parsers.Parser x$1) {
         this.headParser = x$1;
      }

      public List involvedSet() {
         return this.involvedSet;
      }

      public void involvedSet_$eq(final List x$1) {
         this.involvedSet = x$1;
      }

      public List evalSet() {
         return this.evalSet;
      }

      public void evalSet_$eq(final List x$1) {
         this.evalSet = x$1;
      }

      public Parsers.Parser getHead() {
         return this.headParser();
      }

      public Head copy(final Parsers.Parser headParser, final List involvedSet, final List evalSet) {
         return this.scala$util$parsing$combinator$PackratParsers$Head$$$outer().new Head(headParser, involvedSet, evalSet);
      }

      public Parsers.Parser copy$default$1() {
         return this.headParser();
      }

      public List copy$default$2() {
         return this.involvedSet();
      }

      public List copy$default$3() {
         return this.evalSet();
      }

      public String productPrefix() {
         return "Head";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.headParser();
            case 1:
               return this.involvedSet();
            case 2:
               return this.evalSet();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Head;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "headParser";
            case 1:
               return "involvedSet";
            case 2:
               return "evalSet";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10;
         if (this != x$1) {
            label68: {
               if (x$1 instanceof Head && ((Head)x$1).scala$util$parsing$combinator$PackratParsers$Head$$$outer() == this.scala$util$parsing$combinator$PackratParsers$Head$$$outer()) {
                  label58: {
                     Head var4 = (Head)x$1;
                     Parsers.Parser var10000 = this.headParser();
                     Parsers.Parser var5 = var4.headParser();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label58;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label58;
                     }

                     List var8 = this.involvedSet();
                     List var6 = var4.involvedSet();
                     if (var8 == null) {
                        if (var6 != null) {
                           break label58;
                        }
                     } else if (!var8.equals(var6)) {
                        break label58;
                     }

                     var8 = this.evalSet();
                     List var7 = var4.evalSet();
                     if (var8 == null) {
                        if (var7 != null) {
                           break label58;
                        }
                     } else if (!var8.equals(var7)) {
                        break label58;
                     }

                     if (var4.canEqual(this)) {
                        break label68;
                     }
                  }
               }

               var10 = false;
               return var10;
            }
         }

         var10 = true;
         return var10;
      }

      // $FF: synthetic method
      public PackratParsers scala$util$parsing$combinator$PackratParsers$Head$$$outer() {
         return this.$outer;
      }

      public Head(final Parsers.Parser headParser, final List involvedSet, final List evalSet) {
         this.headParser = headParser;
         this.involvedSet = involvedSet;
         this.evalSet = evalSet;
         if (PackratParsers.this == null) {
            throw null;
         } else {
            this.$outer = PackratParsers.this;
            super();
            Product.$init$(this);
         }
      }
   }

   private class Head$ extends AbstractFunction3 implements Serializable {
      // $FF: synthetic field
      private final PackratParsers $outer;

      public final String toString() {
         return "Head";
      }

      public Head apply(final Parsers.Parser headParser, final List involvedSet, final List evalSet) {
         return this.$outer.new Head(headParser, involvedSet, evalSet);
      }

      public Option unapply(final Head x$0) {
         return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.headParser(), x$0.involvedSet(), x$0.evalSet())));
      }

      public Head$() {
         if (PackratParsers.this == null) {
            throw null;
         } else {
            this.$outer = PackratParsers.this;
            super();
         }
      }
   }

   public abstract class PackratParser extends Parsers.Parser {
      // $FF: synthetic method
      public PackratParsers scala$util$parsing$combinator$PackratParsers$PackratParser$$$outer() {
         return (PackratParsers)this.$outer;
      }
   }
}

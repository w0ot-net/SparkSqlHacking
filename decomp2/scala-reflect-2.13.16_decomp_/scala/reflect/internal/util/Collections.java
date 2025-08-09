package scala.reflect.internal.util;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Array;
import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.collection.AbstractIterator;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.MapFactory;
import scala.collection.SeqOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.MapBuilderImpl;
import scala.collection.immutable.Nil;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Nil.;
import scala.collection.mutable.BitSet;
import scala.collection.mutable.Builder;
import scala.collection.mutable.LinkedHashMap;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019}ba\u0002\u001a4!\u0003\r\t\u0001\u0010\u0005\u0006\u0003\u0002!\tA\u0011\u0005\u0006\r\u0002!)a\u0012\u0005\u0006s\u0002!)A\u001f\u0005\b\u0003#\u0001AQAA\n\u0011\u001d\t9\u0003\u0001C\u0003\u0003SAq!!\u0015\u0001\t\u000b\t\u0019\u0006C\u0004\u0002n\u0001!)!a\u001c\t\u000f\u00055\u0004\u0001\"\u0002\u0002\u0004\"9\u00111\u0014\u0001\u0005\u0006\u0005u\u0005bBA\\\u0001\u0011\u0015\u0011\u0011\u0018\u0005\b\u0003\u000b\u0004AQAAd\u0011\u001d\t)\u000f\u0001C\u0003\u0003ODqAa\u0003\u0001\t\u000b\u0011i\u0001C\u0004\u0003.\u0001!)Aa\f\t\u000f\te\u0003\u0001\"\u0002\u0003\\!9!1\u0010\u0001\u0005\u0006\tu\u0004b\u0002BS\u0001\u0011\u0015!q\u0015\u0005\b\u0005\u0007\u0004AQ\u0001Bc\u0011\u001d\u0011Y\u000e\u0001C\u0003\u0005;DqAa=\u0001\t\u000b\u0011)\u0010C\u0004\u0004\u000e\u0001!)aa\u0004\t\u000f\rE\u0002\u0001\"\u0002\u00044!91Q\r\u0001\u0005\u0006\r\u001d\u0004bBBH\u0001\u0011\u00151\u0011\u0013\u0005\b\u0007S\u0003AQABV\u0011\u001d\u0019I\r\u0001C\u0003\u0007\u0017Dqa!:\u0001\t\u000b\u00199\u000fC\u0004\u0005\n\u0001!)\u0001b\u0003\t\u000f\u0011\u0015\u0002\u0001\"\u0002\u0005(!9A\u0011\n\u0001\u0005\u0006\u0011-\u0003b\u0002C7\u0001\u0011\u0015Aq\u000e\u0005\b\t3\u0003AQ\u0001CN\u0011\u001d!)\r\u0001C\u0003\t\u000fDq\u0001\"9\u0001\t\u000b!\u0019\u000fC\u0004\u0005v\u0002!)\u0001b>\t\u000f\u0015M\u0001\u0001\"\u0002\u0006\u0016!9QQ\u0007\u0001\u0005\u0006\u0015]\u0002bBC(\u0001\u0011\u0015Q\u0011\u000b\u0005\b\u000bG\u0002AQAC3\u0011\u001d)y\b\u0001C\u0003\u000b\u0003Cq!\"(\u0001\t\u000b)y\nC\u0004\u00062\u0002!)!b-\t\u000f\u0015%\u0007\u0001\"\u0002\u0006L\"9A\u0011\u0014\u0001\u0005\u0006\u0015\u0005\b\"CC\u0000\u0001\t\u0007I\u0011\u0002D\u0001\u0011\u001d1Y\u0002\u0001C\u0003\r;9qA\"\r4\u0011\u00031\u0019D\u0002\u00043g!\u0005aq\u0007\u0005\b\rw\u0001D\u0011\u0001D\u001f\u0005-\u0019u\u000e\u001c7fGRLwN\\:\u000b\u0005Q*\u0014\u0001B;uS2T!AN\u001c\u0002\u0011%tG/\u001a:oC2T!\u0001O\u001d\u0002\u000fI,g\r\\3di*\t!(A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001i\u0004C\u0001 @\u001b\u0005I\u0014B\u0001!:\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012a\u0011\t\u0003}\u0011K!!R\u001d\u0003\tUs\u0017\u000e^\u0001\rG>\u0014(/Z:q_:$7oM\u000b\u0005\u0011Rs\u0016\r\u0006\u0003JG2|GC\u0001&N!\tq4*\u0003\u0002Ms\t9!i\\8mK\u0006t\u0007\"\u0002(\u0003\u0001\u0004y\u0015!\u00014\u0011\ry\u0002&+\u00181K\u0013\t\t\u0016HA\u0005Gk:\u001cG/[8ogA\u00111\u000b\u0016\u0007\u0001\t\u0015)&A1\u0001W\u0005\u0005\t\u0015CA,[!\tq\u0004,\u0003\u0002Zs\t9aj\u001c;iS:<\u0007C\u0001 \\\u0013\ta\u0016HA\u0002B]f\u0004\"a\u00150\u0005\u000b}\u0013!\u0019\u0001,\u0003\u0003\t\u0003\"aU1\u0005\u000b\t\u0014!\u0019\u0001,\u0003\u0003\rCQ\u0001\u001a\u0002A\u0002\u0015\f1\u0001_:2!\r1\u0017N\u0015\b\u0003}\u001dL!\u0001[\u001d\u0002\u000fA\f7m[1hK&\u0011!n\u001b\u0002\u0005\u0019&\u001cHO\u0003\u0002is!)QN\u0001a\u0001]\u0006\u0019\u0001p\u001d\u001a\u0011\u0007\u0019LW\fC\u0003q\u0005\u0001\u0007\u0011/A\u0002ygN\u00022AZ5aQ\t\u00111\u000f\u0005\u0002uo6\tQO\u0003\u0002ws\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005a,(a\u0002;bS2\u0014XmY\u0001\b[\u0016D\u0018n\u001d;t+\rY\u0018q\u0001\u000b\u0004y\u0006%AC\u0001&~\u0011\u0015q8\u00011\u0001\u0000\u0003\u0005\u0001\bC\u0002 \u0002\u0002\u0005\u0015!*C\u0002\u0002\u0004e\u0012\u0011BR;oGRLwN\\\u0019\u0011\u0007M\u000b9\u0001B\u0003V\u0007\t\u0007a\u000bC\u0004\u0002\f\r\u0001\r!!\u0004\u0002\u0007a\u001c8\u000f\u0005\u0003gS\u0006=\u0001\u0003\u00024j\u0003\u000b\tq!\u001c4pe\u0006dG.\u0006\u0003\u0002\u0016\u0005}A\u0003BA\f\u0003C!2ASA\r\u0011\u0019qH\u00011\u0001\u0002\u001cA1a(!\u0001\u0002\u001e)\u00032aUA\u0010\t\u0015)FA1\u0001W\u0011\u001d\tY\u0001\u0002a\u0001\u0003G\u0001BAZ5\u0002&A!a-[A\u000f\u0003\u0011iW.\u00199\u0016\r\u0005-\u0012\u0011JA!)\u0011\ti#a\u0013\u0015\t\u0005=\u00121\t\t\u0007\u0003c\tY$!\u0010\u000e\u0005\u0005M\"\u0002BA\u001b\u0003o\t\u0011\"[7nkR\f'\r\\3\u000b\u0007\u0005e\u0012(\u0001\u0006d_2dWm\u0019;j_:L1A[A\u001a!\u0019\t\t$a\u000f\u0002@A\u00191+!\u0011\u0005\u000b}+!\u0019\u0001,\t\r9+\u0001\u0019AA#!\u001dq\u0014\u0011AA$\u0003\u007f\u00012aUA%\t\u0015)VA1\u0001W\u0011\u001d\tY!\u0002a\u0001\u0003\u001b\u0002BAZ5\u0002PA!a-[A$\u0003\u0015ig-\u001b8e+\u0011\t)&!\u0019\u0015\t\u0005]\u0013q\r\u000b\u0005\u00033\n\u0019\u0007E\u0003?\u00037\ny&C\u0002\u0002^e\u0012aa\u00149uS>t\u0007cA*\u0002b\u0011)QK\u0002b\u0001-\"1aP\u0002a\u0001\u0003K\u0002bAPA\u0001\u0003?R\u0005bBA\u0006\r\u0001\u0007\u0011\u0011\u000e\t\u0005M&\fY\u0007\u0005\u0003gS\u0006}\u0013\u0001C7g_J,\u0017m\u00195\u0016\t\u0005E\u00141\u0010\u000b\u0005\u0003g\ni\bF\u0002D\u0003kBaAT\u0004A\u0002\u0005]\u0004C\u0002 \u0002\u0002\u0005e4\tE\u0002T\u0003w\"Q!V\u0004C\u0002YCq!a\u0003\b\u0001\u0004\ty\b\u0005\u0003gS\u0006\u0005\u0005\u0003\u00024j\u0003s*B!!\"\u0002\u0010R!\u0011qQAI)\r\u0019\u0015\u0011\u0012\u0005\u0007\u001d\"\u0001\r!a#\u0011\ry\n\t!!$D!\r\u0019\u0016q\u0012\u0003\u0006+\"\u0011\rA\u0016\u0005\b\u0003\u0017A\u0001\u0019AAJ!\u00151\u0017QSAM\u0013\r\t9j\u001b\u0002\t\u0013R,'/\u00192mKB)a-!&\u0002\u000e\u00069Q.\u00199MSN$XCBAP\u0003_\u000b9\u000b\u0006\u0003\u0002\"\u0006EF\u0003BAR\u0003S\u0003BAZ5\u0002&B\u00191+a*\u0005\u000b}K!\u0019\u0001,\t\r9K\u0001\u0019AAV!\u001dq\u0014\u0011AAW\u0003K\u00032aUAX\t\u0015)\u0016B1\u0001W\u0011\u001d\t\u0019,\u0003a\u0001\u0003k\u000b!!Y:\u0011\t\u0019L\u0017QV\u0001\u0013g\u0006lW-\u00127f[\u0016tGo]#rk\u0006d7\u000fF\u0003K\u0003w\u000b\t\rC\u0004\u0002>*\u0001\r!a0\u0002\u000bQD\u0017n]:\u0011\u0007\u0019LW\bC\u0004\u0002D*\u0001\r!a0\u0002\tQD\u0017\r^\u0001\rG>dG.Z2u\r&\u00148\u000f^\u000b\u0007\u0003\u0013\fy.!5\u0015\t\u0005-\u0017\u0011\u001d\u000b\u0005\u0003\u001b\f\u0019\u000eE\u0003?\u00037\ny\rE\u0002T\u0003#$QaX\u0006C\u0002YCq!!6\f\u0001\u0004\t9.\u0001\u0002qMB9a(!7\u0002^\u0006=\u0017bAAns\ty\u0001+\u0019:uS\u0006dg)\u001e8di&|g\u000eE\u0002T\u0003?$Q!V\u0006C\u0002YCq!a-\f\u0001\u0004\t\u0019\u000f\u0005\u0003gS\u0006u\u0017\u0001B7baJ*\u0002\"!;\u0002~\n\u0005\u0011\u0011\u001f\u000b\u0007\u0003W\u0014\u0019Aa\u0002\u0015\t\u00055\u00181\u001f\t\u0005M&\fy\u000fE\u0002T\u0003c$QA\u0019\u0007C\u0002YCaA\u0014\u0007A\u0002\u0005U\b#\u0003 \u0002x\u0006m\u0018q`Ax\u0013\r\tI0\u000f\u0002\n\rVt7\r^5p]J\u00022aUA\u007f\t\u0015)FB1\u0001W!\r\u0019&\u0011\u0001\u0003\u0006?2\u0011\rA\u0016\u0005\u0007I2\u0001\rA!\u0002\u0011\t\u0019L\u00171 \u0005\u0007[2\u0001\rA!\u0003\u0011\t\u0019L\u0017q`\u0001\r[\u0006\u0004(gQ8og\u0016\u0014h/Z\u000b\u0007\u0005\u001f\u00119B!\t\u0015\r\tE!1\u0005B\u0014)\u0011\u0011\u0019Ba\u0007\u0011\t\u0019L'Q\u0003\t\u0004'\n]AAB+\u000e\u0005\u0004\u0011I\"\u0005\u0002X{!1a*\u0004a\u0001\u0005;\u0001\u0012BPA|\u0005+\u0011yB!\u0006\u0011\u0007M\u0013\t\u0003B\u0003`\u001b\t\u0007a\u000bC\u0004\u0003&5\u0001\rAa\u0005\u0002\u0005a\u001c\bb\u0002B\u0015\u001b\u0001\u0007!1F\u0001\u0003sN\u0004BAZ5\u0003 \u0005!Q.\u001994+)\u0011\tDa\u0011\u0003H\t-#\u0011\b\u000b\t\u0005g\u0011iE!\u0015\u0003VQ!!Q\u0007B\u001f!\u00111\u0017Na\u000e\u0011\u0007M\u0013I\u0004\u0002\u0004\u0003<9\u0011\rA\u0016\u0002\u0002\t\"1aJ\u0004a\u0001\u0005\u007f\u0001\"B\u0010)\u0003B\t\u0015#\u0011\nB\u001c!\r\u0019&1\t\u0003\u0006+:\u0011\rA\u0016\t\u0004'\n\u001dC!B0\u000f\u0005\u00041\u0006cA*\u0003L\u0011)!M\u0004b\u0001-\"1AM\u0004a\u0001\u0005\u001f\u0002BAZ5\u0003B!1QN\u0004a\u0001\u0005'\u0002BAZ5\u0003F!1\u0001O\u0004a\u0001\u0005/\u0002BAZ5\u0003J\u0005Aa\r\\1u\u001b\u0006\u0004('\u0006\u0005\u0003^\t5$\u0011\u000fB3)\u0019\u0011yFa\u001d\u0003xQ!!\u0011\rB4!\u00111\u0017Na\u0019\u0011\u0007M\u0013)\u0007B\u0003c\u001f\t\u0007a\u000b\u0003\u0004O\u001f\u0001\u0007!\u0011\u000e\t\n}\u0005](1\u000eB8\u0005C\u00022a\u0015B7\t\u0015)vB1\u0001W!\r\u0019&\u0011\u000f\u0003\u0006?>\u0011\rA\u0016\u0005\u0007I>\u0001\rA!\u001e\u0011\t\u0019L'1\u000e\u0005\u0007[>\u0001\rA!\u001f\u0011\t\u0019L'qN\u0001\nM>dG\rT3giJ*\u0002Ba \u0003\u0010\nU%q\u0011\u000b\u0007\u0005\u0003\u0013iJ!)\u0015\t\t\r%\u0011\u0014\u000b\u0005\u0005\u000b\u0013I\tE\u0002T\u0005\u000f#Qa\u0018\tC\u0002YCaA\u0014\tA\u0002\t-\u0005C\u0003 Q\u0005\u000b\u0013iIa%\u0003\u0006B\u00191Ka$\u0005\r\tE\u0005C1\u0001W\u0005\t\t\u0015\u0007E\u0002T\u0005+#aAa&\u0011\u0005\u00041&AA!3\u0011\u001d\u0011Y\n\u0005a\u0001\u0005\u000b\u000b!A\u001f\u0019\t\r\u0011\u0004\u0002\u0019\u0001BP!\u00111\u0017N!$\t\r5\u0004\u0002\u0019\u0001BR!\u00111\u0017Na%\u0002\u0017\u0019d\u0017\r^\"pY2,7\r^\u000b\u0007\u0005S\u0013IL!-\u0015\t\t-&Q\u0018\u000b\u0005\u0005[\u0013\u0019\f\u0005\u0003gS\n=\u0006cA*\u00032\u0012)q,\u0005b\u0001-\"9\u0011Q[\tA\u0002\tU\u0006c\u0002 \u0002Z\n]&1\u0018\t\u0004'\neF!B+\u0012\u0005\u00041\u0006#\u00024\u0002\u0016\n=\u0006b\u0002B`#\u0001\u0007!\u0011Y\u0001\u0006K2,Wn\u001d\t\u0005M&\u00149,\u0001\u0006eSN$\u0018N\\2u\u0005f,bAa2\u0003P\n]G\u0003\u0002Be\u00053$BAa3\u0003RB!a-\u001bBg!\r\u0019&q\u001a\u0003\u0006+J\u0011\rA\u0016\u0005\u0007\u001dJ\u0001\rAa5\u0011\u000fy\n\tA!4\u0003VB\u00191Ka6\u0005\u000b}\u0013\"\u0019\u0001,\t\u000f\t\u0015\"\u00031\u0001\u0003L\u0006ya\r\\1ui\u0016t7\u000fV8F[B$\u0018\u0010F\u0002K\u0005?Dq!a\u0003\u0014\u0001\u0004\u0011\t\u000fE\u0003g\u0005G\u00149/C\u0002\u0003f.\u00141aU3ra\u0011\u0011IO!<\u0011\u000b\u0019\u0014\u0019Oa;\u0011\u0007M\u0013i\u000fB\u0006\u0003p\n}\u0017\u0011!A\u0001\u0006\u00031&aA0%c!\u00121c]\u0001\u0011M>\u0014X-Y2i/&$\b.\u00138eKb,BAa>\u0004\u0002Q!!\u0011`B\u0005)\r\u0019%1 \u0005\u0007\u001dR\u0001\rA!@\u0011\u0011y\n9Pa@\u0004\u0004\r\u00032aUB\u0001\t\u0015)FC1\u0001W!\rq4QA\u0005\u0004\u0007\u000fI$aA%oi\"9!Q\u0005\u000bA\u0002\r-\u0001\u0003\u00024j\u0005\u007f\f!BZ5oI>\u0013X\t\\:f+\u0011\u0019\tb!\u0007\u0015\t\rM1\u0011\u0006\u000b\u0005\u0007+\u0019)\u0003\u0006\u0003\u0004\u0018\rm\u0001cA*\u0004\u001a\u0011)Q+\u0006b\u0001-\"A1QD\u000b\u0005\u0002\u0004\u0019y\"\u0001\u0004pe\u0016c7/\u001a\t\u0006}\r\u00052qC\u0005\u0004\u0007GI$\u0001\u0003\u001fcs:\fW.\u001a \t\ry,\u0002\u0019AB\u0014!\u0019q\u0014\u0011AB\f\u0015\"9!QE\u000bA\u0002\r-\u0002#\u00024\u0004.\r]\u0011bAB\u0018W\na\u0011\n^3sC\ndWm\u00148dK\u00069Q.\u00199Ge>lW\u0003CB\u001b\u0007/\u001a\tfa\u0017\u0015\t\r]2\u0011\r\u000b\u0005\u0007s\u0019i\u0006\u0005\u0005\u0004<\r%3qJB-\u001d\u0011\u0019id!\u0012\u0011\u0007\r}\u0012(\u0004\u0002\u0004B)\u001911I\u001e\u0002\rq\u0012xn\u001c;?\u0013\r\u00199%O\u0001\u0007!J,G-\u001a4\n\t\r-3Q\n\u0002\u0004\u001b\u0006\u0004(bAB$sA\u00191k!\u0015\u0005\u000f\tEeC1\u0001\u0004TE\u00191Q\u000b.\u0011\u0007M\u001b9\u0006B\u0003V-\t\u0007a\u000bE\u0002T\u00077\"Qa\u0018\fC\u0002YCaA\u0014\fA\u0002\r}\u0003c\u0002 \u0002\u0002\rU3\u0011\f\u0005\b\u0005K1\u0002\u0019AB2!\u00111\u0017n!\u0016\u0002\u001b1Lgn[3e\u001b\u0006\u0004hI]8n+!\u0019Ig!!\u0004|\r\u0015E\u0003BB6\u0007\u0017#Ba!\u001c\u0004\bBA1qNB;\u0007s\u001a\u0019)\u0004\u0002\u0004r)!11OA\u001c\u0003\u001diW\u000f^1cY\u0016LAaa\u001e\u0004r\tiA*\u001b8lK\u0012D\u0015m\u001d5NCB\u00042aUB>\t\u001d\u0011\tj\u0006b\u0001\u0007{\n2aa [!\r\u00196\u0011\u0011\u0003\u0006+^\u0011\rA\u0016\t\u0004'\u000e\u0015E!B0\u0018\u0005\u00041\u0006B\u0002(\u0018\u0001\u0004\u0019I\tE\u0004?\u0003\u0003\u0019yha!\t\u000f\t\u0015r\u00031\u0001\u0004\u000eB!a-[B@\u00031i\u0017\r],ji\"Le\u000eZ3y+\u0019\u0019\u0019ja)\u0004\u001cR!1QSBS)\u0011\u00199j!(\u0011\t\u0019L7\u0011\u0014\t\u0004'\u000emE!B0\u0019\u0005\u00041\u0006B\u0002(\u0019\u0001\u0004\u0019y\nE\u0005?\u0003o\u001c\tka\u0001\u0004\u001aB\u00191ka)\u0005\u000bUC\"\u0019\u0001,\t\u000f\t\u0015\u0002\u00041\u0001\u0004(B!a-[BQ\u0003-\u0019w\u000e\u001c7fGRl\u0015\r\u001d\u001a\u0016\u0011\r56QWB]\u0007\u000f$baa,\u0004@\u000e\rG\u0003BBY\u0007w\u0003\u0002ba\u000f\u0004J\rM6q\u0017\t\u0004'\u000eUF!B+\u001a\u0005\u00041\u0006cA*\u0004:\u0012)q,\u0007b\u0001-\"1a0\u0007a\u0001\u0007{\u0003\u0002BPA|\u0007g\u001b9L\u0013\u0005\u0007If\u0001\ra!1\u0011\t\u0019L71\u0017\u0005\u0007[f\u0001\ra!2\u0011\t\u0019L7q\u0017\u0003\u0006Ef\u0011\rAV\u0001\tM>\u0014X-Y2ieU11QZBl\u00077$baa4\u0004^\u000e\u0005HcA\"\u0004R\"1aJ\u0007a\u0001\u0007'\u0004\u0002BPA|\u0007+\u001cIn\u0011\t\u0004'\u000e]G!B+\u001b\u0005\u00041\u0006cA*\u0004\\\u0012)qL\u0007b\u0001-\"1AM\u0007a\u0001\u0007?\u0004BAZ5\u0004V\"1QN\u0007a\u0001\u0007G\u0004BAZ5\u0004Z\u0006Aam\u001c:fC\u000eD7'\u0006\u0005\u0004j\u000eM8q_B~)!\u0019Yo!@\u0005\u0002\u0011\u0015AcA\"\u0004n\"1aj\u0007a\u0001\u0007_\u0004\u0012B\u0010)\u0004r\u000eU8\u0011`\"\u0011\u0007M\u001b\u0019\u0010B\u0003V7\t\u0007a\u000bE\u0002T\u0007o$QaX\u000eC\u0002Y\u00032aUB~\t\u0015\u00117D1\u0001W\u0011\u0019!7\u00041\u0001\u0004\u0000B!a-[By\u0011\u0019i7\u00041\u0001\u0005\u0004A!a-[B{\u0011\u0019\u00018\u00041\u0001\u0005\bA!a-[B}\u0003\u001d)\u00070[:ugJ*b\u0001\"\u0004\u0005\u0018\u0011mAC\u0002C\b\t;!\t\u0003F\u0002K\t#AaA\u0014\u000fA\u0002\u0011M\u0001\u0003\u0003 \u0002x\u0012UA\u0011\u0004&\u0011\u0007M#9\u0002B\u0003V9\t\u0007a\u000bE\u0002T\t7!Qa\u0018\u000fC\u0002YCa\u0001\u001a\u000fA\u0002\u0011}\u0001\u0003\u00024j\t+Aa!\u001c\u000fA\u0002\u0011\r\u0002\u0003\u00024j\t3\tq!\u001a=jgR\u001c8'\u0006\u0005\u0005*\u0011MBq\u0007C\u001e)!!Y\u0003\"\u0010\u0005B\u0011\u0015Cc\u0001&\u0005.!1a*\ba\u0001\t_\u0001\u0012B\u0010)\u00052\u0011UB\u0011\b&\u0011\u0007M#\u0019\u0004B\u0003V;\t\u0007a\u000bE\u0002T\to!QaX\u000fC\u0002Y\u00032a\u0015C\u001e\t\u0015\u0011WD1\u0001W\u0011\u0019!W\u00041\u0001\u0005@A!a-\u001bC\u0019\u0011\u0019iW\u00041\u0001\u0005DA!a-\u001bC\u001b\u0011\u0019\u0001X\u00041\u0001\u0005HA!a-\u001bC\u001d\u0003\u001d1wN]1mYN*\u0002\u0002\"\u0014\u0005X\u0011mCq\f\u000b\t\t\u001f\"\t\u0007\"\u001a\u0005jQ\u0019!\n\"\u0015\t\r9s\u0002\u0019\u0001C*!%q\u0004\u000b\"\u0016\u0005Z\u0011u#\nE\u0002T\t/\"Q!\u0016\u0010C\u0002Y\u00032a\u0015C.\t\u0015yfD1\u0001W!\r\u0019Fq\f\u0003\u0006Ez\u0011\rA\u0016\u0005\u0007Iz\u0001\r\u0001b\u0019\u0011\t\u0019LGQ\u000b\u0005\u0007[z\u0001\r\u0001b\u001a\u0011\t\u0019LG\u0011\f\u0005\u0007az\u0001\r\u0001b\u001b\u0011\t\u0019LGQL\u0001\u000b[\u0006\u0004h)\u001b7uKJ\u0014T\u0003\u0003C9\t\u000b#I\t\" \u0015\r\u0011MDQ\u0012CJ)\u0011!)\bb \u0011\u000b\u0019$9\bb\u001f\n\u0007\u0011e4N\u0001\u0005Ji\u0016\u0014\u0018\r^8s!\r\u0019FQ\u0010\u0003\u0006E~\u0011\rA\u0016\u0005\u0007\u001d~\u0001\r\u0001\"!\u0011\u0013y\n9\u0010b!\u0005\b\u0012-\u0005cA*\u0005\u0006\u0012)Qk\bb\u0001-B\u00191\u000b\"#\u0005\u000b}{\"\u0019\u0001,\u0011\u000by\nY\u0006b\u001f\t\u000f\u0011=u\u00041\u0001\u0005\u0012\u0006\u0019\u0011\u000e^!\u0011\u000b\u0019$9\bb!\t\u000f\u0011Uu\u00041\u0001\u0005\u0018\u0006\u0019\u0011\u000e\u001e\"\u0011\u000b\u0019$9\bb\"\u0002\u00155\f\u0007\u000fV8BeJ\f\u00170\u0006\u0004\u0005\u001e\u0012}F1\u0016\u000b\u0005\t?#\t\r\u0006\u0003\u0005\"\u0012eF\u0003\u0002CR\t[\u0003RA\u0010CS\tSK1\u0001b*:\u0005\u0015\t%O]1z!\r\u0019F1\u0016\u0003\u0006?\u0002\u0012\rA\u0016\u0005\n\t_\u0003\u0013\u0011!a\u0002\tc\u000b!\"\u001a<jI\u0016t7-\u001a\u00132!\u0019!\u0019\f\".\u0005*6\tq'C\u0002\u00058^\u0012\u0001b\u00117bgN$\u0016m\u001a\u0005\u0007\u001d\u0002\u0002\r\u0001b/\u0011\u000fy\n\t\u0001\"0\u0005*B\u00191\u000bb0\u0005\u000bU\u0003#\u0019\u0001,\t\u000f\t\u0015\u0002\u00051\u0001\u0005DB!a-\u001bC_\u00031i\u0017\r\u001d$s_6\f%O]1z+\u0019!I\r\"7\u0005RR!A1\u001aCn)\u0011!i\rb5\u0011\t\u0019LGq\u001a\t\u0004'\u0012EG!B0\"\u0005\u00041\u0006B\u0002(\"\u0001\u0004!)\u000eE\u0004?\u0003\u0003!9\u000eb4\u0011\u0007M#I\u000eB\u0003VC\t\u0007a\u000bC\u0004\u0005^\u0006\u0002\r\u0001b8\u0002\u0007\u0005\u0014(\u000fE\u0003?\tK#9.A\u0006tKF,XM\\2f\u001fB$X\u0003\u0002Cs\t[$B\u0001b:\u0005pB)a(a\u0017\u0005jB!a-\u001bCv!\r\u0019FQ\u001e\u0003\u0006+\n\u0012\rA\u0016\u0005\b\u0003g\u0013\u0003\u0019\u0001Cy!\u00111\u0017\u000eb=\u0011\u000by\nY\u0006b;\u0002\u0017Q\u0014\u0018M^3sg\u0016|\u0005\u000f^\u000b\u0007\ts,Y!b\u0001\u0015\t\u0011mXq\u0002\u000b\u0005\t{,)\u0001E\u0003?\u00037\"y\u0010\u0005\u0003gS\u0016\u0005\u0001cA*\u0006\u0004\u0011)ql\tb\u0001-\"1aj\ta\u0001\u000b\u000f\u0001rAPA\u0001\u000b\u0013)i\u0001E\u0002T\u000b\u0017!Q!V\u0012C\u0002Y\u0003RAPA.\u000b\u0003Aq!a-$\u0001\u0004)\t\u0002\u0005\u0003gS\u0016%\u0011!\u00049beRLG/[8o\u0013:$x.\u0006\u0003\u0006\u0018\u0015}A#C\"\u0006\u001a\u0015\u0005RqEC\u0019\u0011\u001d\u0011)\u0003\na\u0001\u000b7\u0001BAZ5\u0006\u001eA\u00191+b\b\u0005\u000bU##\u0019\u0001,\t\u000f\u0015\rB\u00051\u0001\u0006&\u0005!\u0001O]3e!\u0019q\u0014\u0011AC\u000f\u0015\"9Q\u0011\u0006\u0013A\u0002\u0015-\u0012\u0001B1zKN\u0004baa\u001c\u0006.\u0015u\u0011\u0002BC\u0018\u0007c\u0012!\u0002T5ti\n+hMZ3s\u0011\u001d)\u0019\u0004\na\u0001\u000bW\tAA\\1zg\u0006\t\"-\u001b;TKR\u0014\u0015\u0010\u0015:fI&\u001c\u0017\r^3\u0016\t\u0015eR\u0011\n\u000b\u0005\u000bw)Y\u0005\u0006\u0003\u0006>\u0015\r\u0003\u0003BB8\u000b\u007fIA!\"\u0011\u0004r\t1!)\u001b;TKRDq!b\t&\u0001\u0004))\u0005\u0005\u0004?\u0003\u0003)9E\u0013\t\u0004'\u0016%C!B+&\u0005\u00041\u0006b\u0002B\u0013K\u0001\u0007QQ\n\t\u0005M&,9%A\u0007ue\u0006t7\u000f]8tKN\u000bg-Z\u000b\u0005\u000b'*i\u0006\u0006\u0003\u0006V\u0015}\u0003#\u0002 \u0002\\\u0015]\u0003\u0003\u00024j\u000b3\u0002BAZ5\u0006\\A\u00191+\"\u0018\u0005\u000bU3#\u0019\u0001,\t\u000f\u0015\u0005d\u00051\u0001\u0006X\u0005\u0019\u0011m]:\u0002\u0015M\fW.\u001a'f]\u001e$\b\u000eF\u0003K\u000bO*\u0019\b\u0003\u0004eO\u0001\u0007Q\u0011\u000e\u0019\u0005\u000bW*y\u0007\u0005\u0003gS\u00165\u0004cA*\u0006p\u0011YQ\u0011OC4\u0003\u0003\u0005\tQ!\u0001W\u0005\ryFE\r\u0005\u0007[\u001e\u0002\r!\"\u001e1\t\u0015]T1\u0010\t\u0005M&,I\bE\u0002T\u000bw\"1\"\" \u0006t\u0005\u0005\t\u0011!B\u0001-\n\u0019q\fJ\u001a\u0002\u001d\r|W\u000e]1sK2+gn\u001a;igR111ACB\u000b\u001fCa\u0001\u001a\u0015A\u0002\u0015\u0015\u0005\u0007BCD\u000b\u0017\u0003BAZ5\u0006\nB\u00191+b#\u0005\u0017\u00155U1QA\u0001\u0002\u0003\u0015\tA\u0016\u0002\u0004?\u0012\"\u0004BB7)\u0001\u0004)\t\n\r\u0003\u0006\u0014\u0016]\u0005\u0003\u00024j\u000b+\u00032aUCL\t-)I*b$\u0002\u0002\u0003\u0005)\u0011\u0001,\u0003\u0007}#S\u0007\u000b\u0002)g\u0006I\u0001.Y:MK:<G\u000f\u001b\u000b\u0006\u0015\u0016\u0005VQ\u0016\u0005\b\u0005KI\u0003\u0019ACRa\u0011))+\"+\u0011\t\u0019LWq\u0015\t\u0004'\u0016%FaCCV\u000bC\u000b\t\u0011!A\u0003\u0002Y\u00131a\u0018\u00137\u0011\u001d)y+\u000ba\u0001\u0007\u0007\t1\u0001\\3o\u0003\u001d\u0019X/\\*ju\u0016$baa\u0001\u00066\u0016\r\u0007bBA\u0006U\u0001\u0007Qq\u0017\t\u0005M&,I\f\r\u0003\u0006<\u0016}\u0006\u0003\u00024j\u000b{\u00032aUC`\t-)\t-\".\u0002\u0002\u0003\u0005)\u0011\u0001,\u0003\u0007}#s\u0007C\u0004\u0006F*\u0002\raa\u0001\u0002\u0007\u0005\u001c7\r\u000b\u0002+g\u0006Aa-\u001b7m\u0019&\u001cH/\u0006\u0003\u0006N\u0016UG\u0003BCh\u000b;$B!\"5\u0006ZB!a-[Cj!\r\u0019VQ\u001b\u0003\u0007\u000b/\\#\u0019\u0001,\u0003\u0003QCq!b7,\u0001\u0004)\u0019.A\u0001u\u0011\u001d)yn\u000ba\u0001\u0007\u0007\t\u0011A\\\u000b\u0007\u000bG,i/\"=\u0015\u0011\u0015\u0015X1_C|\u000bw$2aQCt\u0011\u0019qE\u00061\u0001\u0006jB9a(!\u0001\u0006l\u0016=\bcA*\u0006n\u0012)Q\u000b\fb\u0001-B\u00191+\"=\u0005\u000b}c#\u0019\u0001,\t\u000f\u0005MF\u00061\u0001\u0006vB!a-[Cv\u0011\u001d!i\u000e\fa\u0001\u000bs\u0004RA\u0010CS\u000b_Dq!\"@-\u0001\u0004\u0019\u0019!A\u0001j\u0003)!V\u000f\u001d7f\u001f\u001at\u0015\u000e\\\u000b\u0003\r\u0007\u0001rA\u0010D\u0003\r\u00131I!C\u0002\u0007\be\u0012a\u0001V;qY\u0016\u0014d\u0002\u0002D\u0006\r/qAA\"\u0004\u0007\u00169!aq\u0002D\n\u001d\u0011\u0019yD\"\u0005\n\u0003iJ1!!\u000f:\u0013\u0011\t)$a\u000e\n\t\u0019e\u00111G\u0001\u0004\u001d&d\u0017!\u00059beRLG/[8o\u0007>t7/\u001a:wKV!aq\u0004D\u0015)\u00111\tCb\f\u0015\t\u0019\rb1\u0006\t\b}\u0019\u0015aQ\u0005D\u0013!\u00111\u0017Nb\n\u0011\u0007M3I\u0003B\u0003V]\t\u0007a\u000b\u0003\u0004\u007f]\u0001\u0007aQ\u0006\t\u0007}\u0005\u0005aq\u0005&\t\u000f\u0005Mf\u00061\u0001\u0007&\u0005Y1i\u001c7mK\u000e$\u0018n\u001c8t!\r1)\u0004M\u0007\u0002gM!\u0001'\u0010D\u001d!\r1)\u0004A\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u0019M\u0002"
)
public interface Collections {
   void scala$reflect$internal$util$Collections$_setter_$scala$reflect$internal$util$Collections$$TupleOfNil_$eq(final Tuple2 x$1);

   // $FF: synthetic method
   static boolean corresponds3$(final Collections $this, final List xs1, final List xs2, final List xs3, final Function3 f) {
      return $this.corresponds3(xs1, xs2, xs3, f);
   }

   default boolean corresponds3(final List xs1, final List xs2, final List xs3, final Function3 f) {
      while(!xs1.isEmpty()) {
         if (xs2.isEmpty() || xs3.isEmpty() || !BoxesRunTime.unboxToBoolean(f.apply(xs1.head(), xs2.head(), xs3.head()))) {
            return false;
         }

         List var10000 = (List)xs1.tail();
         List var10001 = (List)xs2.tail();
         List var10002 = (List)xs3.tail();
         f = f;
         xs3 = var10002;
         xs2 = var10001;
         xs1 = var10000;
      }

      if (xs2.isEmpty() && xs3.isEmpty()) {
         return true;
      } else {
         return false;
      }
   }

   // $FF: synthetic method
   static boolean mexists$(final Collections $this, final List xss, final Function1 p) {
      return $this.mexists(xss, p);
   }

   default boolean mexists(final List xss, final Function1 p) {
      if (xss == null) {
         throw null;
      } else {
         for(List exists_these = xss; !exists_these.isEmpty(); exists_these = (List)exists_these.tail()) {
            List var4 = (List)exists_these.head();
            if (var4 == null) {
               throw null;
            }

            List exists_these = var4;

            boolean var10000;
            while(true) {
               if (exists_these.isEmpty()) {
                  var10000 = false;
                  break;
               }

               if (BoxesRunTime.unboxToBoolean(p.apply(exists_these.head()))) {
                  var10000 = true;
                  break;
               }

               exists_these = (List)exists_these.tail();
            }

            Object var6 = null;
            if (var10000) {
               return true;
            }
         }

         return false;
      }
   }

   // $FF: synthetic method
   static boolean mforall$(final Collections $this, final List xss, final Function1 p) {
      return $this.mforall(xss, p);
   }

   default boolean mforall(final List xss, final Function1 p) {
      if (xss == null) {
         throw null;
      } else {
         for(List forall_these = xss; !forall_these.isEmpty(); forall_these = (List)forall_these.tail()) {
            List var4 = (List)forall_these.head();
            if (var4 == null) {
               throw null;
            }

            List forall_these = var4;

            boolean var10000;
            while(true) {
               if (forall_these.isEmpty()) {
                  var10000 = true;
                  break;
               }

               if (!BoxesRunTime.unboxToBoolean(p.apply(forall_these.head()))) {
                  var10000 = false;
                  break;
               }

               forall_these = (List)forall_these.tail();
            }

            Object var6 = null;
            if (!var10000) {
               return false;
            }
         }

         return true;
      }
   }

   // $FF: synthetic method
   static List mmap$(final Collections $this, final List xss, final Function1 f) {
      return $this.mmap(xss, f);
   }

   default List mmap(final List xss, final Function1 f) {
      if (xss == null) {
         throw null;
      } else if (xss == .MODULE$) {
         return .MODULE$;
      } else {
         scala.collection.immutable..colon.colon var10000 = new scala.collection.immutable..colon.colon;
         List var7 = (List)xss.head();
         if (var7 == null) {
            throw null;
         } else {
            Object var10002;
            if (var7 == .MODULE$) {
               var10002 = .MODULE$;
            } else {
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(f.apply(var7.head()), .MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)var7.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(f.apply(map_rest.head()), .MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var10002 = map_h;
            }

            Object var17 = null;
            Object var18 = null;
            Object var19 = null;
            Object var20 = null;
            var10000.<init>(var10002, .MODULE$);
            scala.collection.immutable..colon.colon map_h = var10000;
            scala.collection.immutable..colon.colon map_t = map_h;

            for(List map_rest = (List)xss.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
               var10000 = new scala.collection.immutable..colon.colon;
               var7 = (List)map_rest.head();
               if (var7 == null) {
                  throw null;
               }

               if (var7 == .MODULE$) {
                  var10002 = .MODULE$;
               } else {
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(f.apply(var7.head()), .MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)var7.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(f.apply(map_rest.head()), .MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var10002 = map_h;
               }

               Object var21 = null;
               Object var22 = null;
               Object var23 = null;
               Object var24 = null;
               var10000.<init>(var10002, .MODULE$);
               scala.collection.immutable..colon.colon map_nx = var10000;
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            return map_h;
         }
      }
   }

   // $FF: synthetic method
   static Option mfind$(final Collections $this, final List xss, final Function1 p) {
      return $this.mfind(xss, p);
   }

   default Option mfind(final List xss, final Function1 p) {
      Some var7 = null;
      if (xss == null) {
         throw null;
      } else {
         for(List mforeach_foreach_these = xss; !mforeach_foreach_these.isEmpty(); mforeach_foreach_these = (List)mforeach_foreach_these.tail()) {
            List var4 = (List)mforeach_foreach_these.head();
            if (var4 == null) {
               throw null;
            }

            for(List foreach_these = var4; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
               Object var6 = foreach_these.head();
               if ((Option)var7 == null && BoxesRunTime.unboxToBoolean(p.apply(var6))) {
                  var7 = new Some(var6);
               }
            }

            Object var9 = null;
         }

         Object var8 = null;
         if ((Option)var7 == null) {
            return scala.None..MODULE$;
         } else {
            return (Option)var7;
         }
      }
   }

   // $FF: synthetic method
   static void mforeach$(final Collections $this, final List xss, final Function1 f) {
      $this.mforeach(xss, f);
   }

   default void mforeach(final List xss, final Function1 f) {
      if (xss == null) {
         throw null;
      } else {
         for(List foreach_these = xss; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
            List var4 = (List)foreach_these.head();
            if (var4 == null) {
               throw null;
            }

            for(List foreach_these = var4; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
               f.apply(foreach_these.head());
            }

            Object var6 = null;
         }

      }
   }

   // $FF: synthetic method
   static void mforeach$(final Collections $this, final Iterable xss, final Function1 f) {
      $this.mforeach(xss, f);
   }

   default void mforeach(final Iterable xss, final Function1 f) {
      xss.foreach((x$5) -> {
         $anonfun$mforeach$2(f, x$5);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   static List mapList$(final Collections $this, final List as, final Function1 f) {
      return $this.mapList(as, f);
   }

   default List mapList(final List as, final Function1 f) {
      if (as == .MODULE$) {
         return .MODULE$;
      } else {
         scala.collection.immutable..colon.colon head = new scala.collection.immutable..colon.colon(f.apply(as.head()), .MODULE$);
         scala.collection.immutable..colon.colon tail = head;

         for(List rest = (List)as.tail(); rest != .MODULE$; rest = (List)rest.tail()) {
            scala.collection.immutable..colon.colon next = new scala.collection.immutable..colon.colon(f.apply(rest.head()), .MODULE$);
            tail.next_$eq(next);
            tail = next;
         }

         Statics.releaseFence();
         return head;
      }
   }

   // $FF: synthetic method
   static boolean sameElementsEquals$(final Collections $this, final List thiss, final List that) {
      return $this.sameElementsEquals(thiss, that);
   }

   default boolean sameElementsEquals(final List thiss, final List that) {
      if (thiss != that) {
         List these = thiss;

         List those;
         for(those = that; !these.isEmpty() && !those.isEmpty() && these.head().equals(those.head()); those = (List)those.tail()) {
            these = (List)these.tail();
         }

         if (!these.isEmpty() || !those.isEmpty()) {
            return false;
         }
      }

      return true;
   }

   // $FF: synthetic method
   static Option collectFirst$(final Collections $this, final List as, final PartialFunction pf) {
      return $this.collectFirst(as, pf);
   }

   default Option collectFirst(final List as, final PartialFunction pf) {
      return this.loop$1(as, pf);
   }

   // $FF: synthetic method
   static List map2$(final Collections $this, final List xs1, final List xs2, final Function2 f) {
      return $this.map2(xs1, xs2, f);
   }

   default List map2(final List xs1, final List xs2, final Function2 f) {
      ListBuffer lb = new ListBuffer();
      List ys1 = xs1;

      for(List ys2 = xs2; !ys1.isEmpty() && !ys2.isEmpty(); ys2 = (List)ys2.tail()) {
         Object $plus$eq_elem = f.apply(ys1.head(), ys2.head());
         lb.addOne($plus$eq_elem);
         $plus$eq_elem = null;
         ys1 = (List)ys1.tail();
      }

      return lb.toList();
   }

   // $FF: synthetic method
   static List map2Conserve$(final Collections $this, final List xs, final List ys, final Function2 f) {
      return $this.map2Conserve(xs, ys, f);
   }

   default List map2Conserve(final List xs, final List ys, final Function2 f) {
      List loop$2_pending1 = ys;
      List loop$2_pending0 = xs;
      List loop$2_unchanged = xs;
      ListBuffer loop$2_mapped = null;

      while(!loop$2_pending0.isEmpty() && !loop$2_pending1.isEmpty()) {
         Object loop$2_head00 = loop$2_pending0.head();
         Object loop$2_head01 = loop$2_pending1.head();
         Object loop$2_head1 = f.apply(loop$2_head00, loop$2_head01);
         if (loop$2_head1 == loop$2_head00) {
            List var10002 = (List)loop$2_pending0.tail();
            loop$2_pending1 = (List)loop$2_pending1.tail();
            loop$2_pending0 = var10002;
            loop$2_unchanged = loop$2_unchanged;
            loop$2_mapped = loop$2_mapped;
         } else {
            ListBuffer loop$2_b = loop$2_mapped == null ? new ListBuffer() : loop$2_mapped;

            for(List loop$2_xc = loop$2_unchanged; loop$2_xc != loop$2_pending0 && loop$2_xc != loop$2_pending1; loop$2_xc = (List)loop$2_xc.tail()) {
               Object loop$2_$plus$eq_elem = loop$2_xc.head();
               loop$2_b.addOne(loop$2_$plus$eq_elem);
               loop$2_$plus$eq_elem = null;
            }

            loop$2_b.addOne(loop$2_head1);
            List loop$2_tail0 = (List)loop$2_pending0.tail();
            List loop$2_tail1 = (List)loop$2_pending1.tail();
            loop$2_pending1 = loop$2_tail1;
            loop$2_pending0 = loop$2_tail0;
            loop$2_unchanged = loop$2_tail0;
            loop$2_mapped = loop$2_b;
         }
      }

      List var10000 = loop$2_mapped == null ? loop$2_unchanged : loop$2_mapped.prependToList(loop$2_unchanged);
      Object var17 = null;
      Object var18 = null;
      Object var19 = null;
      Object var20 = null;
      Object var21 = null;
      Object var22 = null;
      Object var23 = null;
      Object var24 = null;
      Object var25 = null;
      Object var26 = null;
      Object var27 = null;
      Object var29 = null;
      List result = var10000;
      Statics.releaseFence();
      return result;
   }

   // $FF: synthetic method
   static List map3$(final Collections $this, final List xs1, final List xs2, final List xs3, final Function3 f) {
      return $this.map3(xs1, xs2, xs3, f);
   }

   default List map3(final List xs1, final List xs2, final List xs3, final Function3 f) {
      if (!xs1.isEmpty() && !xs2.isEmpty() && !xs3.isEmpty()) {
         Object var5 = f.apply(xs1.head(), xs2.head(), xs3.head());
         List var10000 = this.map3((List)xs1.tail(), (List)xs2.tail(), (List)xs3.tail(), f);
         if (var10000 == null) {
            throw null;
         } else {
            List $colon$colon_this = var10000;
            return new scala.collection.immutable..colon.colon(var5, $colon$colon_this);
         }
      } else {
         return .MODULE$;
      }
   }

   // $FF: synthetic method
   static List flatMap2$(final Collections $this, final List xs1, final List xs2, final Function2 f) {
      return $this.flatMap2(xs1, xs2, f);
   }

   default List flatMap2(final List xs1, final List xs2, final Function2 f) {
      ListBuffer lb = null;
      List ys1 = xs1;

      for(List ys2 = xs2; !ys1.isEmpty() && !ys2.isEmpty(); ys2 = (List)ys2.tail()) {
         List cs = (List)f.apply(ys1.head(), ys2.head());
         if (cs != .MODULE$) {
            if (lb == null) {
               lb = new ListBuffer();
            }

            lb.$plus$plus$eq(cs);
         }

         ys1 = (List)ys1.tail();
      }

      return (List)(lb == null ? .MODULE$ : lb.result());
   }

   // $FF: synthetic method
   static Object foldLeft2$(final Collections $this, final List xs1, final List xs2, final Object z0, final Function3 f) {
      return $this.foldLeft2(xs1, xs2, z0, f);
   }

   default Object foldLeft2(final List xs1, final List xs2, final Object z0, final Function3 f) {
      List ys1 = xs1;
      List ys2 = xs2;

      Object res;
      for(res = z0; !ys1.isEmpty() && !ys2.isEmpty(); ys2 = (List)ys2.tail()) {
         res = f.apply(res, ys1.head(), ys2.head());
         ys1 = (List)ys1.tail();
      }

      return res;
   }

   // $FF: synthetic method
   static List flatCollect$(final Collections $this, final List elems, final PartialFunction pf) {
      return $this.flatCollect(elems, pf);
   }

   default List flatCollect(final List elems, final PartialFunction pf) {
      ListBuffer lb = new ListBuffer();
      elems.withFilter((x) -> BoxesRunTime.boxToBoolean($anonfun$flatCollect$1(pf, x))).foreach((x) -> {
         IterableOnce $plus$plus$eq_elems = (IterableOnce)pf.apply(x);
         return (ListBuffer)lb.addAll($plus$plus$eq_elems);
      });
      return lb.toList();
   }

   // $FF: synthetic method
   static List distinctBy$(final Collections $this, final List xs, final Function1 f) {
      return $this.distinctBy(xs, f);
   }

   default List distinctBy(final List xs, final Function1 f) {
      ListBuffer buf = new ListBuffer();
      scala.collection.mutable.Set seen = (scala.collection.mutable.Set)scala.collection.mutable.Set..MODULE$.apply(.MODULE$);
      if (xs == null) {
         throw null;
      } else {
         for(List foreach_these = xs; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
            Object var6 = foreach_these.head();
            Object $anonfun$distinctBy$1_y = f.apply(var6);
            if (seen == null) {
               throw null;
            }

            if (!seen.contains($anonfun$distinctBy$1_y)) {
               buf.addOne(var6);
               seen.addOne($anonfun$distinctBy$1_y);
            }

            $anonfun$distinctBy$1_y = null;
         }

         Object var8 = null;
         return buf.toList();
      }
   }

   // $FF: synthetic method
   static boolean flattensToEmpty$(final Collections $this, final Seq xss) {
      return $this.flattensToEmpty(xss);
   }

   default boolean flattensToEmpty(final Seq xss) {
      while(true) {
         if (!xss.isEmpty()) {
            if (((SeqOps)xss.head()).isEmpty()) {
               xss = (Seq)xss.tail();
               continue;
            }

            return false;
         }

         return true;
      }
   }

   // $FF: synthetic method
   static void foreachWithIndex$(final Collections $this, final List xs, final Function2 f) {
      $this.foreachWithIndex(xs, f);
   }

   default void foreachWithIndex(final List xs, final Function2 f) {
      int index = 0;

      for(List ys = xs; !ys.isEmpty(); ++index) {
         f.apply(ys.head(), index);
         ys = (List)ys.tail();
      }

   }

   // $FF: synthetic method
   static Object findOrElse$(final Collections $this, final IterableOnce xs, final Function1 p, final Function0 orElse) {
      return $this.findOrElse(xs, p, orElse);
   }

   default Object findOrElse(final IterableOnce xs, final Function1 p, final Function0 orElse) {
      Option var10000 = xs.iterator().find(p);
      if (var10000 == null) {
         throw null;
      } else {
         Option getOrElse_this = var10000;
         return getOrElse_this.isEmpty() ? orElse.apply() : getOrElse_this.get();
      }
   }

   // $FF: synthetic method
   static Map mapFrom$(final Collections $this, final List xs, final Function1 f) {
      return $this.mapFrom(xs, f);
   }

   default Map mapFrom(final List xs, final Function1 f) {
      Map var10000 = scala.Predef..MODULE$.Map();
      if (xs == null) {
         throw null;
      } else {
         Object var10001;
         if (xs == .MODULE$) {
            var10001 = .MODULE$;
         } else {
            Object var8 = xs.head();
            scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(new Tuple2(var8, f.apply(var8)), .MODULE$);
            scala.collection.immutable..colon.colon map_t = map_h;

            for(List map_rest = (List)xs.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
               var8 = map_rest.head();
               scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(new Tuple2(var8, f.apply(var8)), .MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            var10001 = map_h;
         }

         Object var9 = null;
         Object var10 = null;
         Object var11 = null;
         Object var12 = null;
         Object apply_elems = var10001;
         if (var10000 == null) {
            throw null;
         } else {
            return (Map)MapFactory.apply$(var10000, (Seq)apply_elems);
         }
      }
   }

   // $FF: synthetic method
   static LinkedHashMap linkedMapFrom$(final Collections $this, final List xs, final Function1 f) {
      return $this.linkedMapFrom(xs, f);
   }

   default LinkedHashMap linkedMapFrom(final List xs, final Function1 f) {
      LinkedHashMap var10000 = scala.collection.mutable.LinkedHashMap..MODULE$;
      if (xs == null) {
         throw null;
      } else {
         Object var10001;
         if (xs == .MODULE$) {
            var10001 = .MODULE$;
         } else {
            Object var7 = xs.head();
            scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(new Tuple2(var7, f.apply(var7)), .MODULE$);
            scala.collection.immutable..colon.colon map_t = map_h;

            for(List map_rest = (List)xs.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
               var7 = map_rest.head();
               scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(new Tuple2(var7, f.apply(var7)), .MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            var10001 = map_h;
         }

         Object var8 = null;
         Object var9 = null;
         Object var10 = null;
         Object var11 = null;
         return (LinkedHashMap)MapFactory.apply$(var10000, (Seq)var10001);
      }
   }

   // $FF: synthetic method
   static List mapWithIndex$(final Collections $this, final List xs, final Function2 f) {
      return $this.mapWithIndex(xs, f);
   }

   default List mapWithIndex(final List xs, final Function2 f) {
      ListBuffer lb = new ListBuffer();
      int index = 0;

      for(List ys = xs; !ys.isEmpty(); ++index) {
         Object $plus$eq_elem = f.apply(ys.head(), index);
         lb.addOne($plus$eq_elem);
         $plus$eq_elem = null;
         ys = (List)ys.tail();
      }

      return lb.toList();
   }

   // $FF: synthetic method
   static Map collectMap2$(final Collections $this, final List xs1, final List xs2, final Function2 p) {
      return $this.collectMap2(xs1, xs2, p);
   }

   default Map collectMap2(final List xs1, final List xs2, final Function2 p) {
      if (!xs1.isEmpty() && !xs2.isEmpty()) {
         Map var12 = scala.collection.immutable.Map..MODULE$;
         Builder buf = new MapBuilderImpl();
         List ys1 = xs1;

         for(List ys2 = xs2; !ys1.isEmpty() && !ys2.isEmpty(); ys2 = (List)ys2.tail()) {
            Object x1 = ys1.head();
            Object x2 = ys2.head();
            if (BoxesRunTime.unboxToBoolean(p.apply(x1, x2))) {
               Object $plus$eq_elem = new Tuple2(x1, x2);
               ((MapBuilderImpl)buf).addOne((Tuple2)$plus$eq_elem);
               $plus$eq_elem = null;
            }

            ys1 = (List)ys1.tail();
         }

         return ((MapBuilderImpl)buf).result();
      } else {
         Map var10000 = scala.Predef..MODULE$.Map();
         Nil apply_elems = .MODULE$;
         if (var10000 == null) {
            throw null;
         } else {
            return (Map)MapFactory.apply$(var10000, apply_elems);
         }
      }
   }

   // $FF: synthetic method
   static void foreach2$(final Collections $this, final List xs1, final List xs2, final Function2 f) {
      $this.foreach2(xs1, xs2, f);
   }

   default void foreach2(final List xs1, final List xs2, final Function2 f) {
      List ys1 = xs1;

      for(List ys2 = xs2; !ys1.isEmpty() && !ys2.isEmpty(); ys2 = (List)ys2.tail()) {
         f.apply(ys1.head(), ys2.head());
         ys1 = (List)ys1.tail();
      }

   }

   // $FF: synthetic method
   static void foreach3$(final Collections $this, final List xs1, final List xs2, final List xs3, final Function3 f) {
      $this.foreach3(xs1, xs2, xs3, f);
   }

   default void foreach3(final List xs1, final List xs2, final List xs3, final Function3 f) {
      List ys1 = xs1;
      List ys2 = xs2;

      for(List ys3 = xs3; !ys1.isEmpty() && !ys2.isEmpty() && !ys3.isEmpty(); ys3 = (List)ys3.tail()) {
         f.apply(ys1.head(), ys2.head(), ys3.head());
         ys1 = (List)ys1.tail();
         ys2 = (List)ys2.tail();
      }

   }

   // $FF: synthetic method
   static boolean exists2$(final Collections $this, final List xs1, final List xs2, final Function2 f) {
      return $this.exists2(xs1, xs2, f);
   }

   default boolean exists2(final List xs1, final List xs2, final Function2 f) {
      List ys1 = xs1;

      for(List ys2 = xs2; !ys1.isEmpty() && !ys2.isEmpty(); ys2 = (List)ys2.tail()) {
         if (BoxesRunTime.unboxToBoolean(f.apply(ys1.head(), ys2.head()))) {
            return true;
         }

         ys1 = (List)ys1.tail();
      }

      return false;
   }

   // $FF: synthetic method
   static boolean exists3$(final Collections $this, final List xs1, final List xs2, final List xs3, final Function3 f) {
      return $this.exists3(xs1, xs2, xs3, f);
   }

   default boolean exists3(final List xs1, final List xs2, final List xs3, final Function3 f) {
      List ys1 = xs1;
      List ys2 = xs2;

      for(List ys3 = xs3; !ys1.isEmpty() && !ys2.isEmpty() && !ys3.isEmpty(); ys3 = (List)ys3.tail()) {
         if (BoxesRunTime.unboxToBoolean(f.apply(ys1.head(), ys2.head(), ys3.head()))) {
            return true;
         }

         ys1 = (List)ys1.tail();
         ys2 = (List)ys2.tail();
      }

      return false;
   }

   // $FF: synthetic method
   static boolean forall3$(final Collections $this, final List xs1, final List xs2, final List xs3, final Function3 f) {
      return $this.forall3(xs1, xs2, xs3, f);
   }

   default boolean forall3(final List xs1, final List xs2, final List xs3, final Function3 f) {
      List ys1 = xs1;
      List ys2 = xs2;

      for(List ys3 = xs3; !ys1.isEmpty() && !ys2.isEmpty() && !ys3.isEmpty(); ys3 = (List)ys3.tail()) {
         if (!BoxesRunTime.unboxToBoolean(f.apply(ys1.head(), ys2.head(), ys3.head()))) {
            return false;
         }

         ys1 = (List)ys1.tail();
         ys2 = (List)ys2.tail();
      }

      return true;
   }

   // $FF: synthetic method
   static Iterator mapFilter2$(final Collections $this, final Iterator itA, final Iterator itB, final Function2 f) {
      return $this.mapFilter2(itA, itB, f);
   }

   default Iterator mapFilter2(final Iterator itA, final Iterator itB, final Function2 f) {
      return new AbstractIterator(itA, itB, f) {
         private Option head;
         private final Iterator itA$1;
         private final Iterator itB$1;
         private final Function2 f$8;

         private void advanceHead() {
            while(this.head.isEmpty() && this.itA$1.hasNext() && this.itB$1.hasNext()) {
               Object x = this.itA$1.next();
               Object y = this.itB$1.next();
               this.head = (Option)this.f$8.apply(x, y);
            }

         }

         public boolean hasNext() {
            this.advanceHead();
            return !this.head.isEmpty();
         }

         public Object next() {
            this.advanceHead();
            Option var10000 = this.head;
            if (var10000 == null) {
               throw null;
            } else {
               Option getOrElse_this = var10000;
               if (getOrElse_this.isEmpty()) {
                  throw new NoSuchElementException("next on empty Iterator");
               } else {
                  Object res = getOrElse_this.get();
                  this.head = scala.None..MODULE$;
                  return res;
               }
            }
         }

         // $FF: synthetic method
         public static final Nothing $anonfun$next$1() {
            throw new NoSuchElementException("next on empty Iterator");
         }

         public {
            this.itA$1 = itA$1;
            this.itB$1 = itB$1;
            this.f$8 = f$8;
            this.head = scala.None..MODULE$;
         }
      };
   }

   // $FF: synthetic method
   static Object mapToArray$(final Collections $this, final List xs, final Function1 f, final ClassTag evidence$1) {
      return $this.mapToArray(xs, f, evidence$1);
   }

   default Object mapToArray(final List xs, final Function1 f, final ClassTag evidence$1) {
      Object arr = evidence$1.newArray(xs.length());
      int ix = 0;

      for(List ys = xs; ix < Array.getLength(arr); ys = (List)ys.tail()) {
         scala.runtime.ScalaRunTime..MODULE$.array_update(arr, ix, f.apply(ys.head()));
         ++ix;
      }

      return arr;
   }

   // $FF: synthetic method
   static List mapFromArray$(final Collections $this, final Object arr, final Function1 f) {
      return $this.mapFromArray(arr, f);
   }

   default List mapFromArray(final Object arr, final Function1 f) {
      int ix = Array.getLength(arr);

      List xs;
      Object var5;
      for(xs = .MODULE$; ix > 0; xs = xs.$colon$colon(var5)) {
         --ix;
         var5 = f.apply(scala.runtime.ScalaRunTime..MODULE$.array_apply(arr, ix));
      }

      return xs;
   }

   // $FF: synthetic method
   static Option sequenceOpt$(final Collections $this, final List as) {
      return $this.sequenceOpt(as);
   }

   default Option sequenceOpt(final List as) {
      if (as == .MODULE$) {
         return package$.MODULE$.SomeOfNil();
      } else {
         ListBuffer traverseOpt_result = null;

         for(List traverseOpt_curr = as; traverseOpt_curr != .MODULE$; traverseOpt_curr = (List)traverseOpt_curr.tail()) {
            Option var5 = (Option)traverseOpt_curr.head();
            if (!(var5 instanceof Some)) {
               if (scala.None..MODULE$.equals(var5)) {
                  return scala.None..MODULE$;
               }

               throw new MatchError(var5);
            }

            Object traverseOpt_b = ((Some)var5).value();
            if (traverseOpt_result == null) {
               ListBuffer var10000 = scala.collection.mutable.ListBuffer..MODULE$;
               traverseOpt_result = new ListBuffer();
            }

            ListBuffer var6 = (ListBuffer)traverseOpt_result.$plus$eq(traverseOpt_b);
         }

         return new Some(traverseOpt_result.toList());
      }
   }

   // $FF: synthetic method
   static Option traverseOpt$(final Collections $this, final List as, final Function1 f) {
      return $this.traverseOpt(as, f);
   }

   default Option traverseOpt(final List as, final Function1 f) {
      if (as == .MODULE$) {
         return package$.MODULE$.SomeOfNil();
      } else {
         ListBuffer result = null;

         for(List curr = as; curr != .MODULE$; curr = (List)curr.tail()) {
            Option var5 = (Option)f.apply(curr.head());
            if (!(var5 instanceof Some)) {
               if (scala.None..MODULE$.equals(var5)) {
                  return scala.None..MODULE$;
               }

               throw new MatchError(var5);
            }

            Object b = ((Some)var5).value();
            if (result == null) {
               ListBuffer var10000 = scala.collection.mutable.ListBuffer..MODULE$;
               result = new ListBuffer();
            }

            ListBuffer var7 = (ListBuffer)result.$plus$eq(b);
         }

         return new Some(result.toList());
      }
   }

   // $FF: synthetic method
   static void partitionInto$(final Collections $this, final List xs, final Function1 pred, final ListBuffer ayes, final ListBuffer nays) {
      $this.partitionInto(xs, pred, ayes, nays);
   }

   default void partitionInto(final List xs, final Function1 pred, final ListBuffer ayes, final ListBuffer nays) {
      for(List ys = xs; !ys.isEmpty(); ys = (List)ys.tail()) {
         Object y = ys.head();
         if (BoxesRunTime.unboxToBoolean(pred.apply(y))) {
            ayes.addOne(y);
         } else {
            nays.addOne(y);
         }
      }

   }

   // $FF: synthetic method
   static BitSet bitSetByPredicate$(final Collections $this, final List xs, final Function1 pred) {
      return $this.bitSetByPredicate(xs, pred);
   }

   default BitSet bitSetByPredicate(final List xs, final Function1 pred) {
      BitSet bs = new BitSet();
      List ys = xs;

      for(int i = 0; !ys.isEmpty(); ++i) {
         if (BoxesRunTime.unboxToBoolean(pred.apply(ys.head()))) {
            bs.add(i);
         }

         ys = (List)ys.tail();
      }

      return bs;
   }

   // $FF: synthetic method
   static Option transposeSafe$(final Collections $this, final List ass) {
      return $this.transposeSafe(ass);
   }

   default Option transposeSafe(final List ass) {
      try {
         return new Some(ass.transpose(scala..less.colon.less..MODULE$.refl()));
      } catch (IllegalArgumentException var2) {
         return scala.None..MODULE$;
      }
   }

   // $FF: synthetic method
   static boolean sameLength$(final Collections $this, final List xs1, final List xs2) {
      return $this.sameLength(xs1, xs2);
   }

   default boolean sameLength(final List xs1, final List xs2) {
      return this.compareLengths(xs1, xs2) == 0;
   }

   // $FF: synthetic method
   static int compareLengths$(final Collections $this, final List xs1, final List xs2) {
      return $this.compareLengths(xs1, xs2);
   }

   default int compareLengths(final List xs1, final List xs2) {
      while(!xs1.isEmpty()) {
         if (xs2.isEmpty()) {
            return 1;
         }

         List var10000 = (List)xs1.tail();
         xs2 = (List)xs2.tail();
         xs1 = var10000;
      }

      if (xs2.isEmpty()) {
         return 0;
      } else {
         return -1;
      }
   }

   // $FF: synthetic method
   static boolean hasLength$(final Collections $this, final List xs, final int len) {
      return $this.hasLength(xs, len);
   }

   default boolean hasLength(final List xs, final int len) {
      return xs.lengthCompare(len) == 0;
   }

   // $FF: synthetic method
   static int sumSize$(final Collections $this, final List xss, final int acc) {
      return $this.sumSize(xss, acc);
   }

   default int sumSize(final List xss, final int acc) {
      while(!xss.isEmpty()) {
         List var10000 = (List)xss.tail();
         SeqOps var10002 = (SeqOps)xss.head();
         if (var10002 == null) {
            throw null;
         }

         acc += var10002.length();
         xss = var10000;
      }

      return acc;
   }

   // $FF: synthetic method
   static List fillList$(final Collections $this, final int n, final Object t) {
      return $this.fillList(n, t);
   }

   default List fillList(final int n, final Object t) {
      int i = 0;

      List result;
      for(result = .MODULE$; i < n; ++i) {
         result = result.$colon$colon(t);
      }

      return result;
   }

   // $FF: synthetic method
   static void mapToArray$(final Collections $this, final List as, final Object arr, final int i, final Function1 f) {
      $this.mapToArray(as, arr, i, f);
   }

   default void mapToArray(final List as, final Object arr, final int i, final Function1 f) {
      List these = as;

      for(int index = i; !these.isEmpty(); these = (List)these.tail()) {
         scala.runtime.ScalaRunTime..MODULE$.array_update(arr, index, f.apply(these.head()));
         ++index;
      }

   }

   Tuple2 scala$reflect$internal$util$Collections$$TupleOfNil();

   // $FF: synthetic method
   static Tuple2 partitionConserve$(final Collections $this, final List as, final Function1 p) {
      return $this.partitionConserve(as, p);
   }

   default Tuple2 partitionConserve(final List as, final Function1 p) {
      if (as.isEmpty()) {
         return this.scala$reflect$internal$util$Collections$$TupleOfNil();
      } else {
         boolean b0 = true;
         boolean canConserve = true;
         List ys = as;
         ListBuffer ayes = null;
         ListBuffer nays = null;

         for(int n = 0; !ys.isEmpty(); ys = (List)ys.tail()) {
            Object y = ys.head();
            boolean b = BoxesRunTime.unboxToBoolean(p.apply(y));
            if (!canConserve) {
               (b ? ayes : nays).$plus$eq(y);
            } else {
               if (n == 0) {
                  b0 = b;
               } else if (b != b0) {
                  canConserve = false;
                  ayes = new ListBuffer();
                  nays = new ListBuffer();
                  ListBuffer prefix = b0 ? ayes : nays;
                  int j = 0;

                  for(List zs = as; j < n; ++j) {
                     Object $plus$eq_elem = zs.head();
                     prefix.addOne($plus$eq_elem);
                     $plus$eq_elem = null;
                     zs = (List)zs.tail();
                  }

                  (b ? ayes : nays).addOne(y);
               }

               ++n;
            }
         }

         if (canConserve) {
            if (b0) {
               return new Tuple2(as, .MODULE$);
            } else {
               return new Tuple2(.MODULE$, as);
            }
         } else {
            return new Tuple2(ayes.toList(), nays.toList());
         }
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$mexists$1(final Function1 p$1, final List x$1) {
      return x$1.exists(p$1);
   }

   // $FF: synthetic method
   static boolean $anonfun$mforall$1(final Function1 p$2, final List x$2) {
      return x$2.forall(p$2);
   }

   // $FF: synthetic method
   static List $anonfun$mmap$1(final Function1 f$1, final List x$3) {
      return x$3.map(f$1);
   }

   // $FF: synthetic method
   static void $anonfun$mfind$1(final ObjectRef res$1, final Function1 p$3, final Object x) {
      if ((Option)res$1.elem == null && BoxesRunTime.unboxToBoolean(p$3.apply(x))) {
         res$1.elem = new Some(x);
      }
   }

   // $FF: synthetic method
   static void $anonfun$mforeach$1(final Function1 f$2, final List x$4) {
      x$4.foreach(f$2);
   }

   // $FF: synthetic method
   static void $anonfun$mforeach$2(final Function1 f$3, final Iterable x$5) {
      x$5.foreach(f$3);
   }

   private Option loop$1(final List as, final PartialFunction pf$1) {
      while(as instanceof scala.collection.immutable..colon.colon) {
         scala.collection.immutable..colon.colon var3 = (scala.collection.immutable..colon.colon)as;
         Object a = var3.head();
         List as = var3.next$access$1();
         if (pf$1.isDefinedAt(a)) {
            return new Some(pf$1.apply(a));
         }

         as = as;
      }

      return scala.None..MODULE$;
   }

   private List loop$2(final ListBuffer mapped, final List unchanged, final List pending0, final List pending1, final Function2 f$4) {
      while(!pending0.isEmpty() && !pending1.isEmpty()) {
         Object head00 = pending0.head();
         Object head01 = pending1.head();
         Object head1 = f$4.apply(head00, head01);
         if (head1 == head00) {
            List var10002 = (List)pending0.tail();
            pending1 = (List)pending1.tail();
            pending0 = var10002;
            unchanged = unchanged;
            mapped = mapped;
         } else {
            ListBuffer b = mapped == null ? new ListBuffer() : mapped;

            for(List xc = unchanged; xc != pending0 && xc != pending1; xc = (List)xc.tail()) {
               Object $plus$eq_elem = xc.head();
               b.addOne($plus$eq_elem);
               $plus$eq_elem = null;
            }

            b.addOne(head1);
            List tail0 = (List)pending0.tail();
            List tail1 = (List)pending1.tail();
            pending1 = tail1;
            pending0 = tail0;
            unchanged = tail0;
            mapped = b;
         }
      }

      if (mapped == null) {
         return unchanged;
      } else {
         return mapped.prependToList(unchanged);
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$flatCollect$1(final PartialFunction pf$2, final Object x) {
      return pf$2.isDefinedAt(x);
   }

   // $FF: synthetic method
   static Object $anonfun$distinctBy$1(final Function1 f$5, final scala.collection.mutable.Set seen$1, final ListBuffer buf$1, final Object x) {
      Object y = f$5.apply(x);
      if (seen$1 == null) {
         throw null;
      } else if (!seen$1.contains(y)) {
         if (buf$1 == null) {
            throw null;
         } else {
            buf$1.addOne(x);
            return seen$1.addOne(y);
         }
      } else {
         return BoxedUnit.UNIT;
      }
   }

   // $FF: synthetic method
   static Tuple2 $anonfun$mapFrom$1(final Function1 f$6, final Object x) {
      return new Tuple2(x, f$6.apply(x));
   }

   // $FF: synthetic method
   static Tuple2 $anonfun$linkedMapFrom$1(final Function1 f$7, final Object x) {
      return new Tuple2(x, f$7.apply(x));
   }

   // $FF: synthetic method
   static Option $anonfun$sequenceOpt$1(final Option x) {
      return (Option)scala.Predef..MODULE$.identity(x);
   }

   static void $init$(final Collections $this) {
      $this.scala$reflect$internal$util$Collections$_setter_$scala$reflect$internal$util$Collections$$TupleOfNil_$eq(new Tuple2(.MODULE$, .MODULE$));
   }

   // $FF: synthetic method
   static Object $anonfun$mexists$1$adapted(final Function1 p$1, final List x$1) {
      return BoxesRunTime.boxToBoolean($anonfun$mexists$1(p$1, x$1));
   }

   // $FF: synthetic method
   static Object $anonfun$mforall$1$adapted(final Function1 p$2, final List x$2) {
      return BoxesRunTime.boxToBoolean($anonfun$mforall$1(p$2, x$2));
   }

   // $FF: synthetic method
   static Object $anonfun$mfind$1$adapted(final ObjectRef res$1, final Function1 p$3, final Object x) {
      $anonfun$mfind$1(res$1, p$3, x);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   static Object $anonfun$mforeach$1$adapted(final Function1 f$2, final List x$4) {
      $anonfun$mforeach$1(f$2, x$4);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

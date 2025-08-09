package org.apache.spark.sql;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.annotation.Stable;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.sql.catalyst.parser.DataTypeParser$;
import org.apache.spark.sql.catalyst.trees.CurrentOrigin$;
import org.apache.spark.sql.expressions.Window$;
import org.apache.spark.sql.expressions.WindowSpec;
import org.apache.spark.sql.internal.Alias;
import org.apache.spark.sql.internal.Alias$;
import org.apache.spark.sql.internal.CaseWhenOtherwise;
import org.apache.spark.sql.internal.CaseWhenOtherwise$;
import org.apache.spark.sql.internal.Cast;
import org.apache.spark.sql.internal.Cast$;
import org.apache.spark.sql.internal.ColumnNode;
import org.apache.spark.sql.internal.LazyExpression;
import org.apache.spark.sql.internal.LazyExpression$;
import org.apache.spark.sql.internal.SortOrder;
import org.apache.spark.sql.internal.SortOrder$;
import org.apache.spark.sql.internal.TableValuedFunctionArgument;
import org.apache.spark.sql.internal.UnresolvedAttribute$;
import org.apache.spark.sql.internal.UnresolvedExtractValue;
import org.apache.spark.sql.internal.UnresolvedExtractValue$;
import org.apache.spark.sql.internal.UnresolvedStar;
import org.apache.spark.sql.internal.UnresolvedStar$;
import org.apache.spark.sql.internal.UpdateFields;
import org.apache.spark.sql.internal.UpdateFields$;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.Metadata;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Symbol;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005\u0011\u0005vAB5k\u0011\u0003a'O\u0002\u0004uU\"\u0005A.\u001e\u0005\u0006y\u0006!\tA \u0005\u0007\u007f\u0006!\t!!\u0001\t\r}\fA\u0011\u0001C*\u0011!!i&\u0001C\u0001U\u0012}\u0003\u0002CA/\u0003\u0011\u0005A\u000e\"\u001f\t\u0011\u0005u\u0013\u0001\"\u0001m\t\u0003C\u0001\u0002b#\u0002\t\u0003aGQ\u0012\u0005\b\u0003;\nA\u0011\u0002CJ\r\u0015!(\u000eAA\u0003\u0011)\tiB\u0003BC\u0002\u0013\u0005\u0011q\u0004\u0005\u000b\u0003OQ!\u0011!Q\u0001\n\u0005\u0005\u0002B\u0002?\u000b\t\u0003\tI\u0003C\u0004}\u0015\u0011\u0005!.!\f\t\rqTA\u0011AA-\u0011\u001d\tiF\u0003C\u0005\u0003?Bq!!\u0018\u000b\t\u0013\t\u0019\u0007C\u0004\u0002^)!I!a\u001b\t\u000f\u0005]$\u0002\"\u0011\u0002z!9\u00111\u0010\u0006\u0005B\u0005u\u0004bBAE\u0015\u0011\u0005\u00131\u0012\u0005\b\u0003'SA\u0011AAK\u0011\u0019y(\u0002\"\u0001\u0002:\"9\u0011q\u0018\u0006\u0005\u0002\u0005\u0005\u0007bBAb\u0015\u0011\u0005\u0011\u0011\u0019\u0005\b\u0003\u000bTA\u0011AAd\u0011\u001d\tYM\u0003C\u0005\u0003\u001bDq!!7\u000b\t\u0003\tY\u000eC\u0004\u0002`*!\t!!9\t\u000f\u0005\u0015(\u0002\"\u0001\u0002h\"9\u0011q \u0006\u0005\u0002\t\u0005\u0001b\u0002B\u0003\u0015\u0011\u0005!q\u0001\u0005\b\u0005\u0017QA\u0011\u0001B\u0007\u0011\u001d\u0011\tB\u0003C\u0001\u0005'AqAa\u0006\u000b\t\u0003\u0011I\u0002C\u0004\u0003\u001e)!\tAa\b\t\u000f\t\r\"\u0002\"\u0001\u0003&!9!\u0011\u0006\u0006\u0005\u0002\t-\u0002b\u0002B\u0018\u0015\u0011\u0005!\u0011\u0007\u0005\b\u0005kQA\u0011\u0001B\u001c\u0011\u001d\u0011YD\u0003C\u0001\u0005{AqA!\u0011\u000b\t\u0003\u0011\u0019\u0005C\u0004\u0003N)!\tAa\u0014\t\u000f\tM#\u0002\"\u0001\u0003V!9!q\f\u0006\u0005\u0002\u0005\u0005\u0007b\u0002B1\u0015\u0011\u0005\u0011\u0011\u0019\u0005\b\u0005GRA\u0011AAa\u0011\u001d\u0011)G\u0003C\u0001\u0005OBqAa\u001b\u000b\t\u0003\u0011i\u0007C\u0004\u0003r)!\tAa\u001d\t\u000f\t]$\u0002\"\u0001\u0003z!9!Q\u0010\u0006\u0005\u0002\t}\u0004b\u0002BB\u0015\u0011\u0005!Q\u0011\u0005\b\u0005\u0013SA\u0011\u0001BF\u0011\u001d\u0011yI\u0003C\u0001\u0005#CqA!&\u000b\t\u0003\u00119\nC\u0004\u0003\u001c*!\tA!(\t\u000f\t\u0005&\u0002\"\u0001\u0003$\"9!q\u0015\u0006\u0005\u0002\t%\u0006b\u0002BW\u0015\u0011\u0005!q\u0016\u0005\b\u0005gSA\u0011\u0001B[\u0011\u001d\u0011IL\u0003C\u0001\u0005wCqA!6\u000b\t\u0003\u00119\u000eC\u0004\u0003V*!\tA!=\t\u000f\r-!\u0002\"\u0001\u0004\u000e!911\u0003\u0006\u0005\u0002\rU\u0001bBB\r\u0015\u0011\u000511\u0004\u0005\b\u0007?QA\u0011AB\u0011\u0011\u001d\u00199C\u0003C\u0001\u0007SAqaa\r\u000b\t\u0003\u0019)\u0004C\u0004\u0004>)!\taa\u0010\t\u000f\r\r#\u0002\"\u0001\u0004F!911\t\u0006\u0005\u0002\r=\u0003bBB+\u0015\u0011\u00051q\u000b\u0005\b\u00077RA\u0011AB/\u0011\u001d\u0019YF\u0003C\u0001\u0007CBqa!\u001a\u000b\t\u0003\u00199\u0007C\u0004\u0004f)!\taa\u001b\t\u000f\r=$\u0002\"\u0001\u0004r!9\u00111\u0013\u0006\u0005\u0002\rU\u0004bBAJ\u0015\u0011\u00051\u0011\u0010\u0005\b\u0003'SA\u0011ABI\u0011\u001d\t\u0019J\u0003C\u0001\u00077Cq!a%\u000b\t\u0003\u0019)\u000bC\u0004\u00022)!\ta!/\t\u000f\ru&\u0002\"\u0001\u0004@\"91Q\u0018\u0006\u0005\u0002\r-\u0007bBBh\u0015\u0011\u00051\u0011\u001b\u0005\b\u0007\u001fTA\u0011ABk\u0011\u001d\u0019IN\u0003C\u0005\u00077D\u0001b!7\u000b\t\u0003Q7Q \u0005\b\t\u000bQA\u0011AAa\u0011\u001d!9A\u0003C\u0001\u0003\u0003Dq\u0001\"\u0003\u000b\t\u0003\t\t\rC\u0004\u0005\f)!\t!!1\t\u000f\u00115!\u0002\"\u0001\u0002B\"9Aq\u0002\u0006\u0005\u0002\u0005\u0005\u0007b\u0002C\t\u0015\u0011\u0005A1\u0003\u0005\b\t3QA\u0011\u0001C\u000e\u0011\u001d!yB\u0003C\u0001\tCAq\u0001\"\n\u000b\t\u0003!9\u0003C\u0004\u0005,)!\t\u0001\"\f\t\u000f\u0011-\"\u0002\"\u0001\u0005@!9A\u0011\t\u0006\u0005\u0002\u0011}\u0012AB\"pYVlgN\u0003\u0002lY\u0006\u00191/\u001d7\u000b\u00055t\u0017!B:qCJ\\'BA8q\u0003\u0019\t\u0007/Y2iK*\t\u0011/A\u0002pe\u001e\u0004\"a]\u0001\u000e\u0003)\u0014aaQ8mk6t7CA\u0001w!\t9(0D\u0001y\u0015\u0005I\u0018!B:dC2\f\u0017BA>y\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001s\u0003\u0015\t\u0007\u000f\u001d7z)\u0011\t\u0019\u0001b\u0014\u0011\u0005MT1C\u0002\u0006w\u0003\u000f\t\u0019\u0002\u0005\u0003\u0002\n\u0005=QBAA\u0006\u0015\r\ti\u0001\\\u0001\tS:$XM\u001d8bY&!\u0011\u0011CA\u0006\u0005\u001daunZ4j]\u001e\u0004B!!\u0006\u0002\u001a5\u0011\u0011q\u0003\u0006\u0004\u0003\u001bQ\u0017\u0002BA\u000e\u0003/\u00111\u0004V1cY\u00164\u0016\r\\;fI\u001a+hn\u0019;j_:\f%oZ;nK:$\u0018\u0001\u00028pI\u0016,\"!!\t\u0011\t\u0005U\u00111E\u0005\u0005\u0003K\t9B\u0001\u0006D_2,XN\u001c(pI\u0016\fQA\\8eK\u0002\"B!a\u0001\u0002,!9\u0011QD\u0007A\u0002\u0005\u0005BCBA\u0002\u0003_\tI\u0005C\u0004\u000229\u0001\r!a\r\u0002\t9\fW.\u001a\t\u0005\u0003k\t\u0019E\u0004\u0003\u00028\u0005}\u0002cAA\u001dq6\u0011\u00111\b\u0006\u0004\u0003{i\u0018A\u0002\u001fs_>$h(C\u0002\u0002Ba\fa\u0001\u0015:fI\u00164\u0017\u0002BA#\u0003\u000f\u0012aa\u0015;sS:<'bAA!q\"9\u00111\n\bA\u0002\u00055\u0013A\u00029mC:LE\rE\u0003x\u0003\u001f\n\u0019&C\u0002\u0002Ra\u0014aa\u00149uS>t\u0007cA<\u0002V%\u0019\u0011q\u000b=\u0003\t1{gn\u001a\u000b\u0005\u0003\u0007\tY\u0006C\u0004\u00022=\u0001\r!a\r\u0002\u0005\u0019tG\u0003BA\u0002\u0003CBq!!\r\u0011\u0001\u0004\t\u0019\u0004\u0006\u0004\u0002\u0004\u0005\u0015\u0014q\r\u0005\b\u0003c\t\u0002\u0019AA\u001a\u0011\u001d\tI'\u0005a\u0001\u0003\u0007\tQa\u001c;iKJ$b!a\u0001\u0002n\u0005=\u0004bBA\u0019%\u0001\u0007\u00111\u0007\u0005\b\u0003S\u0012\u0002\u0019AA9!\r9\u00181O\u0005\u0004\u0003kB(aA!os\u0006AAo\\*ue&tw\r\u0006\u0002\u00024\u00051Q-];bYN$B!a \u0002\u0006B\u0019q/!!\n\u0007\u0005\r\u0005PA\u0004C_>dW-\u00198\t\u000f\u0005\u001dE\u00031\u0001\u0002r\u0005!A\u000f[1u\u0003!A\u0017m\u001d5D_\u0012,GCAAG!\r9\u0018qR\u0005\u0004\u0003#C(aA%oi\u0006\u0011\u0011m]\u000b\u0005\u0003/\u000b\u0019\u000b\u0006\u0003\u0002\u001a\u0006=\u0006cB:\u0002\u001c\u0006E\u0014qT\u0005\u0004\u0003;S'a\u0003+za\u0016$7i\u001c7v[:\u0004B!!)\u0002$2\u0001AaBAS-\t\u0007\u0011q\u0015\u0002\u0002+F!\u0011\u0011VA9!\r9\u00181V\u0005\u0004\u0003[C(a\u0002(pi\"Lgn\u001a\u0005\n\u0003c3\u0012\u0011!a\u0002\u0003g\u000b!\"\u001a<jI\u0016t7-\u001a\u00132!\u0015\u0019\u0018QWAP\u0013\r\t9L\u001b\u0002\b\u000b:\u001cw\u000eZ3s)\u0011\t\u0019!a/\t\u000f\u0005uv\u00031\u0001\u0002r\u0005QQ\r\u001f;sC\u000e$\u0018n\u001c8\u0002\u0019Ut\u0017M]=`I5Lg.^:\u0016\u0005\u0005\r\u0011aC;oCJLx\f\n2b]\u001e\f\u0011\u0002J3rI\u0015\fH%Z9\u0015\t\u0005\r\u0011\u0011\u001a\u0005\b\u0003SR\u0002\u0019AA9\u0003U\u0019\u0007.Z2l)JLg/[1m!J,G-[2bi\u0016$B!a4\u0002VB\u0019q/!5\n\u0007\u0005M\u0007P\u0001\u0003V]&$\bbBAl7\u0001\u0007\u00111A\u0001\u0006e&<\u0007\u000e^\u0001\bKF,\u0018\r\u001c+p)\u0011\t\u0019!!8\t\u000f\u0005%D\u00041\u0001\u0002r\u0005YA%Z9%E\u0006tw\rJ3r)\u0011\t\u0019!a9\t\u000f\u0005%T\u00041\u0001\u0002r\u0005YAEY1oO\u0012*\u0017\u000fJ3r)\u0011\t\u0019!!;\t\u000f\u0005%d\u00041\u0001\u0002r!Za$!<\u0002t\u0006U\u0018\u0011`A~!\r9\u0018q^\u0005\u0004\u0003cD(A\u00033faJ,7-\u0019;fI\u00069Q.Z:tC\u001e,\u0017EAA|\u0003u\nS(\u0010\u0011e_\u0016\u001c\bE\\8uA!\fg/\u001a\u0011uQ\u0016\u00043/Y7fAA\u0014XmY3eK:\u001cW\rI1tAujT\b\f\u0011vg\u0016\u0004S(I\u001f!S:\u001cH/Z1e\u0003\u0015\u0019\u0018N\\2fC\t\ti0A\u00033]Ar\u0003'\u0001\u0005o_R,\u0015/^1m)\u0011\t\u0019Aa\u0001\t\u000f\u0005%t\u00041\u0001\u0002r\u0005AAe\u001a:fCR,'\u000f\u0006\u0003\u0002\u0004\t%\u0001bBA5A\u0001\u0007\u0011\u0011O\u0001\u0003OR$B!a\u0001\u0003\u0010!9\u0011\u0011N\u0011A\u0002\u0005E\u0014!\u0002\u0013mKN\u001cH\u0003BA\u0002\u0005+Aq!!\u001b#\u0001\u0004\t\t(\u0001\u0002miR!\u00111\u0001B\u000e\u0011\u001d\tIg\ta\u0001\u0003c\n\u0001\u0002\n7fgN$S-\u001d\u000b\u0005\u0003\u0007\u0011\t\u0003C\u0004\u0002j\u0011\u0002\r!!\u001d\u0002\u00071,\u0017\u000f\u0006\u0003\u0002\u0004\t\u001d\u0002bBA5K\u0001\u0007\u0011\u0011O\u0001\fI\u001d\u0014X-\u0019;fe\u0012*\u0017\u000f\u0006\u0003\u0002\u0004\t5\u0002bBA5M\u0001\u0007\u0011\u0011O\u0001\u0004O\u0016\fH\u0003BA\u0002\u0005gAq!!\u001b(\u0001\u0004\t\t(\u0001\t%Y\u0016\u001c8\u000fJ3rI\u001d\u0014X-\u0019;feR!\u00111\u0001B\u001d\u0011\u001d\tI\u0007\u000ba\u0001\u0003c\n!\"Z9Ok2d7+\u00194f)\u0011\t\u0019Aa\u0010\t\u000f\u0005%\u0014\u00061\u0001\u0002r\u0005!q\u000f[3o)\u0019\t\u0019A!\u0012\u0003J!9!q\t\u0016A\u0002\u0005\r\u0011!C2p]\u0012LG/[8o\u0011\u001d\u0011YE\u000ba\u0001\u0003c\nQA^1mk\u0016\f\u0011b\u001c;iKJ<\u0018n]3\u0015\t\u0005\r!\u0011\u000b\u0005\b\u0005\u0017Z\u0003\u0019AA9\u0003\u001d\u0011W\r^<fK:$b!a\u0001\u0003X\tm\u0003b\u0002B-Y\u0001\u0007\u0011\u0011O\u0001\u000bY><XM\u001d\"pk:$\u0007b\u0002B/Y\u0001\u0007\u0011\u0011O\u0001\u000bkB\u0004XM\u001d\"pk:$\u0017!B5t\u001d\u0006t\u0015AB5t\u001dVdG.A\u0005jg:{GOT;mY\u0006AAEY1sI\t\f'\u000f\u0006\u0003\u0002\u0004\t%\u0004bBA5a\u0001\u0007\u0011\u0011O\u0001\u0003_J$B!a\u0001\u0003p!9\u0011\u0011N\u0019A\u0002\u0005\r\u0011\u0001\u0003\u0013b[B$\u0013-\u001c9\u0015\t\u0005\r!Q\u000f\u0005\b\u0003S\u0012\u0004\u0019AA9\u0003\r\tg\u000e\u001a\u000b\u0005\u0003\u0007\u0011Y\bC\u0004\u0002jM\u0002\r!a\u0001\u0002\u000b\u0011\u0002H.^:\u0015\t\u0005\r!\u0011\u0011\u0005\b\u0003S\"\u0004\u0019AA9\u0003\u0011\u0001H.^:\u0015\t\u0005\r!q\u0011\u0005\b\u0003S*\u0004\u0019AA9\u0003\u0019!S.\u001b8vgR!\u00111\u0001BG\u0011\u001d\tIG\u000ea\u0001\u0003c\nQ!\\5okN$B!a\u0001\u0003\u0014\"9\u0011\u0011N\u001cA\u0002\u0005E\u0014A\u0002\u0013uS6,7\u000f\u0006\u0003\u0002\u0004\te\u0005bBA5q\u0001\u0007\u0011\u0011O\u0001\t[VdG/\u001b9msR!\u00111\u0001BP\u0011\u001d\tI'\u000fa\u0001\u0003c\nA\u0001\n3jmR!\u00111\u0001BS\u0011\u001d\tIG\u000fa\u0001\u0003c\na\u0001Z5wS\u0012,G\u0003BA\u0002\u0005WCq!!\u001b<\u0001\u0004\t\t(\u0001\u0005%a\u0016\u00148-\u001a8u)\u0011\t\u0019A!-\t\u000f\u0005%D\b1\u0001\u0002r\u0005\u0019Qn\u001c3\u0015\t\u0005\r!q\u0017\u0005\b\u0003Sj\u0004\u0019AA9\u0003\u0011I7/\u001b8\u0015\t\u0005\r!Q\u0018\u0005\b\u0005\u007fs\u0004\u0019\u0001Ba\u0003\u0011a\u0017n\u001d;\u0011\u000b]\u0014\u0019-!\u001d\n\u0007\t\u0015\u0007P\u0001\u0006=e\u0016\u0004X-\u0019;fIzB3A\u0010Be!\u0011\u0011YM!5\u000e\u0005\t5'b\u0001Bhq\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\tM'Q\u001a\u0002\bm\u0006\u0014\u0018M]4t\u00039I7/\u00138D_2dWm\u0019;j_:$B!a\u0001\u0003Z\"9!1\\ A\u0002\tu\u0017A\u0002<bYV,7\u000f\r\u0003\u0003`\n5\bC\u0002Bq\u0005O\u0014Y/\u0004\u0002\u0003d*\u0019!Q\u001d=\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0003j\n\r(\u0001C%uKJ\f'\r\\3\u0011\t\u0005\u0005&Q\u001e\u0003\r\u0005_\u0014I.!A\u0001\u0002\u000b\u0005\u0011q\u0015\u0002\u0004?\u0012\nD\u0003BA\u0002\u0005gDqAa7A\u0001\u0004\u0011)\u0010\r\u0003\u0003x\u000e\u001d\u0001C\u0002B}\u0007\u0007\u0019)!\u0004\u0002\u0003|*!!Q B\u0000\u0003\u0011a\u0017M\\4\u000b\u0005\r\u0005\u0011\u0001\u00026bm\u0006LAA!;\u0003|B!\u0011\u0011UB\u0004\t1\u0019IAa=\u0002\u0002\u0003\u0005)\u0011AAT\u0005\ryFEM\u0001\u0005Y&\\W\r\u0006\u0003\u0002\u0004\r=\u0001bBB\t\u0003\u0002\u0007\u00111G\u0001\bY&$XM]1m\u0003\u0015\u0011H.[6f)\u0011\t\u0019aa\u0006\t\u000f\rE!\t1\u0001\u00024\u0005)\u0011\u000e\\5lKR!\u00111AB\u000f\u0011\u001d\u0019\tb\u0011a\u0001\u0003g\tqaZ3u\u0013R,W\u000e\u0006\u0003\u0002\u0004\r\r\u0002bBB\u0013\t\u0002\u0007\u0011\u0011O\u0001\u0004W\u0016L\u0018!C<ji\"4\u0015.\u001a7e)\u0019\t\u0019aa\u000b\u00040!91QF#A\u0002\u0005M\u0012!\u00034jK2$g*Y7f\u0011\u001d\u0019\t$\u0012a\u0001\u0003\u0007\t1aY8m\u0003)!'o\u001c9GS\u0016dGm\u001d\u000b\u0005\u0003\u0007\u00199\u0004C\u0004\u0004:\u0019\u0003\raa\u000f\u0002\u0015\u0019LW\r\u001c3OC6,7\u000fE\u0003x\u0005\u0007\f\u0019$\u0001\u0005hKR4\u0015.\u001a7e)\u0011\t\u0019a!\u0011\t\u000f\r5r\t1\u0001\u00024\u000511/\u001e2tiJ$b!a\u0001\u0004H\r-\u0003bBB%\u0011\u0002\u0007\u00111A\u0001\tgR\f'\u000f\u001e)pg\"91Q\n%A\u0002\u0005\r\u0011a\u00017f]R1\u00111AB)\u0007'Bqa!\u0013J\u0001\u0004\ti\tC\u0004\u0004N%\u0003\r!!$\u0002\u0011\r|g\u000e^1j]N$B!a\u0001\u0004Z!9\u0011\u0011\u000e&A\u0002\u0005E\u0014AC:uCJ$8oV5uQR!\u00111AB0\u0011\u001d\tIg\u0013a\u0001\u0003\u0007!B!a\u0001\u0004d!91\u0011\u0003'A\u0002\u0005M\u0012\u0001C3oIN<\u0016\u000e\u001e5\u0015\t\u0005\r1\u0011\u000e\u0005\b\u0003Sj\u0005\u0019AA\u0002)\u0011\t\u0019a!\u001c\t\u000f\rEa\n1\u0001\u00024\u0005)\u0011\r\\5bgR!\u00111AB:\u0011\u001d\u0019yg\u0014a\u0001\u0003g!B!a\u0001\u0004x!91q\u000e)A\u0002\u0005MB\u0003BA\u0002\u0007wBqa! R\u0001\u0004\u0019y(A\u0004bY&\f7/Z:\u0011\r\r\u000551RA\u001a\u001d\u0011\u0019\u0019ia\"\u000f\t\u0005e2QQ\u0005\u0002s&\u00191\u0011\u0012=\u0002\u000fA\f7m[1hK&!1QRBH\u0005\r\u0019V-\u001d\u0006\u0004\u0007\u0013CH\u0003BA\u0002\u0007'Cqa! S\u0001\u0004\u0019)\nE\u0003x\u0007/\u000b\u0019$C\u0002\u0004\u001ab\u0014Q!\u0011:sCf$B!a\u0001\u0004\u001e\"91qN*A\u0002\r}\u0005cA<\u0004\"&\u001911\u0015=\u0003\rMKXNY8m)\u0019\t\u0019aa*\u0004*\"91q\u000e+A\u0002\u0005M\u0002bBBV)\u0002\u00071QV\u0001\t[\u0016$\u0018\rZ1uCB!1qVB[\u001b\t\u0019\tLC\u0002\u00044*\fQ\u0001^=qKNLAaa.\u00042\nAQ*\u001a;bI\u0006$\u0018\r\u0006\u0003\u0002\u0004\rm\u0006bBB8+\u0002\u0007\u00111G\u0001\u0005G\u0006\u001cH\u000f\u0006\u0003\u0002\u0004\r\u0005\u0007bBBb-\u0002\u00071QY\u0001\u0003i>\u0004Baa,\u0004H&!1\u0011ZBY\u0005!!\u0015\r^1UsB,G\u0003BA\u0002\u0007\u001bDqaa1X\u0001\u0004\t\u0019$\u0001\u0005uef|6-Y:u)\u0011\t\u0019aa5\t\u000f\r\r\u0007\f1\u0001\u0004FR!\u00111ABl\u0011\u001d\u0019\u0019-\u0017a\u0001\u0003g\t\u0011b]8si>\u0013H-\u001a:\u0015\r\u0005\r1Q\\Bz\u0011\u001d\u0019yN\u0017a\u0001\u0007C\fQb]8si\u0012K'/Z2uS>t\u0007\u0003BBr\u0007[tAa!:\u0004j:\u00191oa:\n\u0007\u00055!.\u0003\u0003\u0004l\u0006]\u0011!C*peR|%\u000fZ3s\u0013\u0011\u0019yo!=\u0003\u001bM{'\u000f\u001e#je\u0016\u001cG/[8o\u0015\u0011\u0019Y/a\u0006\t\u000f\rU(\f1\u0001\u0004x\u0006aa.\u001e7m\u001fJ$WM]5oOB!11]B}\u0013\u0011\u0019Yp!=\u0003\u00199+H\u000e\\(sI\u0016\u0014\u0018N\\4\u0016\u0005\r}\b\u0003BA\u000b\t\u0003IA\u0001b\u0001\u0002\u0018\tI1k\u001c:u\u001fJ$WM]\u0001\u0005I\u0016\u001c8-\u0001\teKN\u001cwL\\;mYN|f-\u001b:ti\u0006yA-Z:d?:,H\u000e\\:`Y\u0006\u001cH/A\u0002bg\u000e\fq\"Y:d?:,H\u000e\\:`M&\u00148\u000f^\u0001\u000fCN\u001cwL\\;mYN|F.Y:u\u0003\u001d)\u0007\u0010\u001d7bS:$B!a4\u0005\u0016!9Aq\u00032A\u0002\u0005}\u0014\u0001C3yi\u0016tG-\u001a3\u0002\u0013\tLGo^5tK>\u0013F\u0003BA\u0002\t;Aq!!\u001bd\u0001\u0004\t\t(\u0001\u0006cSR<\u0018n]3B\u001d\u0012#B!a\u0001\u0005$!9\u0011\u0011\u000e3A\u0002\u0005E\u0014A\u00032ji^L7/\u001a-P%R!\u00111\u0001C\u0015\u0011\u001d\tI'\u001aa\u0001\u0003c\nAa\u001c<feR!\u00111\u0001C\u0018\u0011\u001d!\tD\u001aa\u0001\tg\taa^5oI><\b\u0003\u0002C\u001b\twi!\u0001b\u000e\u000b\u0007\u0011e\".A\u0006fqB\u0014Xm]:j_:\u001c\u0018\u0002\u0002C\u001f\to\u0011!bV5oI><8\u000b]3d)\t\t\u0019!A\u0003pkR,'\u000fK\u0002\u000b\t\u000b\u0002B\u0001b\u0012\u0005L5\u0011A\u0011\n\u0006\u0004\u0005\u001fd\u0017\u0002\u0002C'\t\u0013\u0012aa\u0015;bE2,\u0007b\u0002C)\u0007\u0001\u0007\u00111G\u0001\bG>dg*Y7f)\u0011\t\u0019\u0001\"\u0016\t\u0011\u0005uA\u0001\"a\u0001\t/\u0002Ra\u001eC-\u0003CI1\u0001b\u0017y\u0005!a$-\u001f8b[\u0016t\u0014!\u00044o/&$\bn\u00149uS>t7\u000f\u0006\u0005\u0002\u0004\u0011\u0005D1\rC:\u0011\u001d\t\t$\u0002a\u0001\u0003gAq\u0001\"\u001a\u0006\u0001\u0004!9'A\u0004paRLwN\\:\u0011\r\r\u0005E\u0011\u000eC7\u0013\u0011!Yga$\u0003\u0011%#XM]1u_J\u0004ra\u001eC8\u0003g\t\u0019$C\u0002\u0005ra\u0014a\u0001V;qY\u0016\u0014\u0004b\u0002C;\u000b\u0001\u0007AqO\u0001\nCJ<W/\\3oiN\u0004Ra\u001eBb\u0003\u0007!b!a\u0001\u0005|\u0011u\u0004bBA\u0019\r\u0001\u0007\u00111\u0007\u0005\b\t\u007f2\u0001\u0019\u0001C<\u0003\u0019Ig\u000e];ugRA\u00111\u0001CB\t\u000b#I\tC\u0004\u00022\u001d\u0001\r!a\r\t\u000f\u0011\u001du\u00011\u0001\u0002\u0000\u0005Q\u0011n\u001d#jgRLgn\u0019;\t\u000f\u0011}t\u00011\u0001\u0005x\u0005Q\u0011N\u001c;fe:\fGN\u00128\u0015\r\u0005\rAq\u0012CI\u0011\u001d\t\t\u0004\u0003a\u0001\u0003gAq\u0001b \t\u0001\u0004!9\b\u0006\u0006\u0002\u0004\u0011UEq\u0013CM\t;Cq!!\r\n\u0001\u0004\t\u0019\u0004C\u0004\u0005\b&\u0001\r!a \t\u000f\u0011m\u0015\u00021\u0001\u0002\u0000\u0005Q\u0011n]%oi\u0016\u0014h.\u00197\t\u000f\u0011}\u0014\u00021\u0001\u0005 B11\u0011QBF\u0003\u0007\u0001"
)
public class Column implements Logging, TableValuedFunctionArgument {
   private final ColumnNode node;
   private transient Logger org$apache$spark$internal$Logging$$log_;

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

   public Column isin(final Object... list) {
      return this.isin((Seq).MODULE$.genericWrapArray(list));
   }

   public ColumnNode node() {
      return this.node;
   }

   private Column fn(final String name) {
      return Column$.MODULE$.fn(name, .MODULE$.wrapRefArray(new Column[]{this}));
   }

   private Column fn(final String name, final Column other) {
      return Column$.MODULE$.fn(name, .MODULE$.wrapRefArray(new Column[]{this, other}));
   }

   private Column fn(final String name, final Object other) {
      return Column$.MODULE$.fn(name, .MODULE$.wrapRefArray(new Column[]{this, functions$.MODULE$.lit(other)}));
   }

   public String toString() {
      return this.node().sql();
   }

   public boolean equals(final Object that) {
      if (!(that instanceof Column var4)) {
         return false;
      } else {
         boolean var6;
         label30: {
            ColumnNode var10000 = var4.node().normalized();
            ColumnNode var5 = this.node().normalized();
            if (var10000 == null) {
               if (var5 == null) {
                  break label30;
               }
            } else if (var10000.equals(var5)) {
               break label30;
            }

            var6 = false;
            return var6;
         }

         var6 = true;
         return var6;
      }
   }

   public int hashCode() {
      return this.node().normalized().hashCode();
   }

   public TypedColumn as(final Encoder evidence$1) {
      return new TypedColumn(this.node(), (Encoder)scala.Predef..MODULE$.implicitly(evidence$1));
   }

   public Column apply(final Object extraction) {
      return Column$.MODULE$.apply((Function0)(() -> new UnresolvedExtractValue(this.node(), functions$.MODULE$.lit(extraction).node(), UnresolvedExtractValue$.MODULE$.apply$default$3())));
   }

   public Column unary_$minus() {
      return this.fn("negative");
   }

   public Column unary_$bang() {
      return this.fn("!");
   }

   public Column $eq$eq$eq(final Object other) {
      Column right = functions$.MODULE$.lit(other);
      this.checkTrivialPredicate(right);
      return this.fn("=", other);
   }

   private void checkTrivialPredicate(final Column right) {
      label14: {
         if (this == null) {
            if (right == null) {
               break label14;
            }
         } else if (this.equals(right)) {
            break label14;
         }

         return;
      }

      this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Constructing trivially true equals predicate, "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"'", " == ", "'. "})))).log(.MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.LEFT_EXPR..MODULE$, this), new MDC(org.apache.spark.internal.LogKeys.RIGHT_EXPR..MODULE$, right)})))).$plus(this.LogStringContext(new StringContext(.MODULE$.wrapRefArray((Object[])(new String[]{"Perhaps you need to use aliases."})))).log(scala.collection.immutable.Nil..MODULE$))));
   }

   public Column equalTo(final Object other) {
      return this.$eq$eq$eq(other);
   }

   public Column $eq$bang$eq(final Object other) {
      return this.$eq$eq$eq(other).unary_$bang();
   }

   /** @deprecated */
   public Column $bang$eq$eq(final Object other) {
      return this.$eq$bang$eq(other);
   }

   public Column notEqual(final Object other) {
      return this.$eq$bang$eq(other);
   }

   public Column $greater(final Object other) {
      return this.fn(">", other);
   }

   public Column gt(final Object other) {
      return this.$greater(other);
   }

   public Column $less(final Object other) {
      return this.fn("<", other);
   }

   public Column lt(final Object other) {
      return this.$less(other);
   }

   public Column $less$eq(final Object other) {
      return this.fn("<=", other);
   }

   public Column leq(final Object other) {
      return this.$less$eq(other);
   }

   public Column $greater$eq(final Object other) {
      return this.fn(">=", other);
   }

   public Column geq(final Object other) {
      return this.$greater$eq(other);
   }

   public Column $less$eq$greater(final Object other) {
      Column right = functions$.MODULE$.lit(other);
      this.checkTrivialPredicate(right);
      return this.fn("<=>", right);
   }

   public Column eqNullSafe(final Object other) {
      return this.$less$eq$greater(other);
   }

   public Column when(final Column condition, final Object value) {
      return Column$.MODULE$.apply((Function0)(() -> {
         boolean var4 = false;
         CaseWhenOtherwise var5 = null;
         ColumnNode var6 = this.node();
         if (var6 instanceof CaseWhenOtherwise) {
            var4 = true;
            var5 = (CaseWhenOtherwise)var6;
            Seq branches = var5.branches();
            Option var8 = var5.otherwise();
            if (scala.None..MODULE$.equals(var8)) {
               return new CaseWhenOtherwise((Seq)branches.$colon$plus(new Tuple2(condition.node(), functions$.MODULE$.lit(value).node())), scala.None..MODULE$, CaseWhenOtherwise$.MODULE$.apply$default$3());
            }
         }

         if (var4) {
            Option var9 = var5.otherwise();
            if (var9 instanceof Some) {
               throw new IllegalArgumentException("when() cannot be applied once otherwise() is applied");
            }
         }

         throw new IllegalArgumentException("when() can only be applied on a Column previously generated by when() function");
      }));
   }

   public Column otherwise(final Object value) {
      return Column$.MODULE$.apply((Function0)(() -> {
         boolean var3 = false;
         CaseWhenOtherwise var4 = null;
         ColumnNode var5 = this.node();
         if (var5 instanceof CaseWhenOtherwise) {
            var3 = true;
            var4 = (CaseWhenOtherwise)var5;
            Seq branches = var4.branches();
            Option var7 = var4.otherwise();
            if (scala.None..MODULE$.equals(var7)) {
               return new CaseWhenOtherwise(branches, scala.Option..MODULE$.apply(functions$.MODULE$.lit(value).node()), CaseWhenOtherwise$.MODULE$.apply$default$3());
            }
         }

         if (var3) {
            Option var8 = var4.otherwise();
            if (var8 instanceof Some) {
               throw new IllegalArgumentException("otherwise() can only be applied once on a Column previously generated by when()");
            }
         }

         throw new IllegalArgumentException("otherwise() can only be applied on a Column previously generated by when()");
      }));
   }

   public Column between(final Object lowerBound, final Object upperBound) {
      return this.$greater$eq(lowerBound).$amp$amp(this.$less$eq(upperBound));
   }

   public Column isNaN() {
      return this.fn("isNaN");
   }

   public Column isNull() {
      return this.fn("isNull");
   }

   public Column isNotNull() {
      return this.fn("isNotNull");
   }

   public Column $bar$bar(final Object other) {
      return this.fn("or", other);
   }

   public Column or(final Column other) {
      return this.$bar$bar(other);
   }

   public Column $amp$amp(final Object other) {
      return this.fn("and", other);
   }

   public Column and(final Column other) {
      return this.$amp$amp(other);
   }

   public Column $plus(final Object other) {
      return this.fn("+", other);
   }

   public Column plus(final Object other) {
      return this.$plus(other);
   }

   public Column $minus(final Object other) {
      return this.fn("-", other);
   }

   public Column minus(final Object other) {
      return this.$minus(other);
   }

   public Column $times(final Object other) {
      return this.fn("*", other);
   }

   public Column multiply(final Object other) {
      return this.$times(other);
   }

   public Column $div(final Object other) {
      return this.fn("/", other);
   }

   public Column divide(final Object other) {
      return this.$div(other);
   }

   public Column $percent(final Object other) {
      return this.fn("%", other);
   }

   public Column mod(final Object other) {
      return this.$percent(other);
   }

   public Column isin(final Seq list) {
      return Column$.MODULE$.fn("in", (Seq)((SeqOps)list.map((literal) -> functions$.MODULE$.lit(literal))).$plus$colon(this));
   }

   public Column isInCollection(final Iterable values) {
      return this.isin(values.toSeq());
   }

   public Column isInCollection(final java.lang.Iterable values) {
      return this.isInCollection(scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala(values).asScala());
   }

   public Column like(final String literal) {
      return this.fn("like", (Object)literal);
   }

   public Column rlike(final String literal) {
      return this.fn("rlike", (Object)literal);
   }

   public Column ilike(final String literal) {
      return this.fn("ilike", (Object)literal);
   }

   public Column getItem(final Object key) {
      return this.apply(key);
   }

   public Column withField(final String fieldName, final Column col) {
      scala.Predef..MODULE$.require(fieldName != null, () -> "fieldName cannot be null");
      scala.Predef..MODULE$.require(col != null, () -> "col cannot be null");
      return Column$.MODULE$.apply((Function0)(() -> new UpdateFields(this.node(), fieldName, scala.Option..MODULE$.apply(col.node()), UpdateFields$.MODULE$.apply$default$4())));
   }

   public Column dropFields(final Seq fieldNames) {
      return Column$.MODULE$.apply((Function0)(() -> (UpdateFields)((IterableOnceOps)fieldNames.tail()).foldLeft(new UpdateFields(this.node(), (String)fieldNames.head(), UpdateFields$.MODULE$.apply$default$3(), UpdateFields$.MODULE$.apply$default$4()), (resExpr, fieldName) -> new UpdateFields(resExpr, fieldName, UpdateFields$.MODULE$.apply$default$3(), UpdateFields$.MODULE$.apply$default$4()))));
   }

   public Column getField(final String fieldName) {
      return this.apply(fieldName);
   }

   public Column substr(final Column startPos, final Column len) {
      return Column$.MODULE$.fn("substr", .MODULE$.wrapRefArray(new Column[]{this, startPos, len}));
   }

   public Column substr(final int startPos, final int len) {
      return this.substr(functions$.MODULE$.lit(BoxesRunTime.boxToInteger(startPos)), functions$.MODULE$.lit(BoxesRunTime.boxToInteger(len)));
   }

   public Column contains(final Object other) {
      return this.fn("contains", other);
   }

   public Column startsWith(final Column other) {
      return this.fn("startswith", other);
   }

   public Column startsWith(final String literal) {
      return this.startsWith(functions$.MODULE$.lit(literal));
   }

   public Column endsWith(final Column other) {
      return this.fn("endswith", other);
   }

   public Column endsWith(final String literal) {
      return this.endsWith(functions$.MODULE$.lit(literal));
   }

   public Column alias(final String alias) {
      return this.name(alias);
   }

   public Column as(final String alias) {
      return this.name(alias);
   }

   public Column as(final Seq aliases) {
      return Column$.MODULE$.apply((Function0)(() -> new Alias(this.node(), aliases, Alias$.MODULE$.apply$default$3(), Alias$.MODULE$.apply$default$4())));
   }

   public Column as(final String[] aliases) {
      return this.as((Seq)org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(aliases).toImmutableArraySeq());
   }

   public Column as(final Symbol alias) {
      return this.name(alias.name());
   }

   public Column as(final String alias, final Metadata metadata) {
      return Column$.MODULE$.apply((Function0)(() -> new Alias(this.node(), scala.collection.immutable.Nil..MODULE$.$colon$colon(alias), scala.Option..MODULE$.apply(metadata), Alias$.MODULE$.apply$default$4())));
   }

   public Column name(final String alias) {
      return Column$.MODULE$.apply((Function0)(() -> new Alias(this.node(), scala.collection.immutable.Nil..MODULE$.$colon$colon(alias), Alias$.MODULE$.apply$default$3(), Alias$.MODULE$.apply$default$4())));
   }

   public Column cast(final DataType to) {
      return Column$.MODULE$.apply((Function0)(() -> new Cast(this.node(), to, Cast$.MODULE$.apply$default$3(), Cast$.MODULE$.apply$default$4())));
   }

   public Column cast(final String to) {
      return this.cast(DataTypeParser$.MODULE$.parseDataType(to));
   }

   public Column try_cast(final DataType to) {
      return Column$.MODULE$.apply((Function0)(() -> new Cast(this.node(), to, scala.Option..MODULE$.apply(Cast.Try$.MODULE$), Cast$.MODULE$.apply$default$4())));
   }

   public Column try_cast(final String to) {
      return this.try_cast(DataTypeParser$.MODULE$.parseDataType(to));
   }

   private Column sortOrder(final SortOrder.SortDirection sortDirection, final SortOrder.NullOrdering nullOrdering) {
      return Column$.MODULE$.apply((Function0)(() -> new SortOrder(this.node(), sortDirection, nullOrdering, SortOrder$.MODULE$.apply$default$4())));
   }

   public SortOrder sortOrder() {
      ColumnNode var2 = this.node();
      if (var2 instanceof SortOrder var3) {
         return var3;
      } else {
         return (SortOrder)this.asc().node();
      }
   }

   public Column desc() {
      return this.desc_nulls_last();
   }

   public Column desc_nulls_first() {
      return this.sortOrder(SortOrder.Descending$.MODULE$, SortOrder.NullsFirst$.MODULE$);
   }

   public Column desc_nulls_last() {
      return this.sortOrder(SortOrder.Descending$.MODULE$, SortOrder.NullsLast$.MODULE$);
   }

   public Column asc() {
      return this.asc_nulls_first();
   }

   public Column asc_nulls_first() {
      return this.sortOrder(SortOrder.Ascending$.MODULE$, SortOrder.NullsFirst$.MODULE$);
   }

   public Column asc_nulls_last() {
      return this.sortOrder(SortOrder.Ascending$.MODULE$, SortOrder.NullsLast$.MODULE$);
   }

   public void explain(final boolean extended) {
      if (extended) {
         scala.Predef..MODULE$.println(this.node());
      } else {
         scala.Predef..MODULE$.println(this.node().sql());
      }
   }

   public Column bitwiseOR(final Object other) {
      return this.fn("|", other);
   }

   public Column bitwiseAND(final Object other) {
      return this.fn("&", other);
   }

   public Column bitwiseXOR(final Object other) {
      return this.fn("^", other);
   }

   public Column over(final WindowSpec window) {
      return (Column)CurrentOrigin$.MODULE$.withOrigin(() -> window.withAggregate(this));
   }

   public Column over() {
      return this.over(Window$.MODULE$.spec());
   }

   public Column outer() {
      return Column$.MODULE$.apply((Function0)(() -> new LazyExpression(this.node(), LazyExpression$.MODULE$.apply$default$2())));
   }

   public Column(final ColumnNode node) {
      this.node = node;
      Logging.$init$(this);
   }

   public Column(final String name, final Option planId) {
      this((ColumnNode)CurrentOrigin$.MODULE$.withOrigin(new Serializable(name, planId) {
         private static final long serialVersionUID = 0L;
         private final String name$2;
         private final Option planId$1;

         public final ColumnNode apply() {
            String var2 = this.name$2;
            switch (var2 == null ? 0 : var2.hashCode()) {
               case 42:
                  if ("*".equals(var2)) {
                     return new UnresolvedStar(scala.None..MODULE$, this.planId$1, UnresolvedStar$.MODULE$.apply$default$3());
                  }
               default:
                  return (ColumnNode)(this.name$2.endsWith(".*") ? new UnresolvedStar(scala.Option..MODULE$.apply(this.name$2), this.planId$1, UnresolvedStar$.MODULE$.apply$default$3()) : UnresolvedAttribute$.MODULE$.apply(this.name$2, this.planId$1));
            }
         }

         public {
            this.name$2 = name$2;
            this.planId$1 = planId$1;
         }
      }));
   }

   public Column(final String name) {
      this(name, scala.None..MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

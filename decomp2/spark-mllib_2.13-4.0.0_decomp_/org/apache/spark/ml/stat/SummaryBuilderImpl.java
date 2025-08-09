package org.apache.spark.ml.stat;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.internal.Logging;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.catalyst.analysis.TypeCheckResult;
import org.apache.spark.sql.catalyst.expressions.ExpectsInputTypes;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.catalyst.expressions.ImplicitCastInputTypes;
import org.apache.spark.sql.catalyst.expressions.aggregate.TypedImperativeAggregate;
import org.apache.spark.sql.catalyst.trees.BinaryLike;
import org.apache.spark.sql.catalyst.trees.TreeNode;
import org.apache.spark.sql.classic.ClassicConversions.;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DoubleType;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple6;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction6;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019Ec!\u0003B\u000e\u0005;\u0001!\u0011\u0005B\u0019\u0011)\u0011Y\u0004\u0001B\u0001B\u0003%!q\b\u0005\u000b\r_\u0001!\u0011!Q\u0001\n\u0019E\u0002b\u0002B=\u0001\u0011\u0005aQ\u0007\u0005\b\r{\u0001A\u0011\tD \u000f)\u0011yF!\b\t\u0002\t\u0015\"\u0011\r\u0004\u000b\u00057\u0011i\u0002#\u0001\u0003&\t\r\u0004b\u0002B=\r\u0011\u0005!1\u0010\u0005\b\u0005{2A\u0011\u0001B@\u0011\u001d\u0011\u0019J\u0002C\u0001\u0005+Cq\u0001b\u0014\u0007\t\u0003!\t\u0006C\u0004\u0005h\u0019!I\u0001\"\u001b\t\u0013\u0011udA1A\u0005\n\u0011}\u0004\u0002\u0003CG\r\u0001\u0006I\u0001\"!\t\u0013\u0011=eA1A\u0005\n\u0011E\u0005\u0002\u0003CQ\r\u0001\u0006I\u0001b%\u0007\u0013\t\rf\u0001%A\u0012\"\t\u0015v!\u0003CR\r!\u0005%QDB\u001b\r%\u0019yC\u0002EA\u0005;\u0019\t\u0004C\u0004\u0003zI!\taa\r\t\u0013\t-'#!A\u0005B\t5\u0007\"\u0003Bm%\u0005\u0005I\u0011\u0001Bn\u0011%\u0011\u0019OEA\u0001\n\u0003\u00199\u0004C\u0005\u0003rJ\t\t\u0011\"\u0011\u0003t\"I1\u0011\u0001\n\u0002\u0002\u0013\u000511\b\u0005\n\u0007\u001b\u0011\u0012\u0011!C!\u0007\u001fA\u0011b!\u0005\u0013\u0003\u0003%\tea\u0005\t\u0013\rU!#!A\u0005\n\r]q!\u0003CS\r!\u0005%QDBK\r%\u0019yI\u0002EA\u0005;\u0019\t\nC\u0004\u0003zu!\taa%\t\u0013\t-W$!A\u0005B\t5\u0007\"\u0003Bm;\u0005\u0005I\u0011\u0001Bn\u0011%\u0011\u0019/HA\u0001\n\u0003\u00199\nC\u0005\u0003rv\t\t\u0011\"\u0011\u0003t\"I1\u0011A\u000f\u0002\u0002\u0013\u000511\u0014\u0005\n\u0007\u001bi\u0012\u0011!C!\u0007\u001fA\u0011b!\u0005\u001e\u0003\u0003%\tea\u0005\t\u0013\rUQ$!A\u0005\n\r]q!\u0003CT\r!\u0005%QDBS\r%\u0019yJ\u0002EA\u0005;\u0019\t\u000bC\u0004\u0003z!\"\taa)\t\u0013\t-\u0007&!A\u0005B\t5\u0007\"\u0003BmQ\u0005\u0005I\u0011\u0001Bn\u0011%\u0011\u0019\u000fKA\u0001\n\u0003\u00199\u000bC\u0005\u0003r\"\n\t\u0011\"\u0011\u0003t\"I1\u0011\u0001\u0015\u0002\u0002\u0013\u000511\u0016\u0005\n\u0007\u001bA\u0013\u0011!C!\u0007\u001fA\u0011b!\u0005)\u0003\u0003%\tea\u0005\t\u0013\rU\u0001&!A\u0005\n\r]q!\u0003CU\r!\u0005%QDBC\r%\u0019yH\u0002EA\u0005;\u0019\t\tC\u0004\u0003zM\"\taa!\t\u0013\t-7'!A\u0005B\t5\u0007\"\u0003Bmg\u0005\u0005I\u0011\u0001Bn\u0011%\u0011\u0019oMA\u0001\n\u0003\u00199\tC\u0005\u0003rN\n\t\u0011\"\u0011\u0003t\"I1\u0011A\u001a\u0002\u0002\u0013\u000511\u0012\u0005\n\u0007\u001b\u0019\u0014\u0011!C!\u0007\u001fA\u0011b!\u00054\u0003\u0003%\tea\u0005\t\u0013\rU1'!A\u0005\n\r]q!\u0003CV\r!\u0005%Q\u0004Be\r%\u0011IL\u0002EA\u0005;\u0011Y\fC\u0004\u0003zy\"\tAa2\t\u0013\t-g(!A\u0005B\t5\u0007\"\u0003Bm}\u0005\u0005I\u0011\u0001Bn\u0011%\u0011\u0019OPA\u0001\n\u0003\u0011)\u000fC\u0005\u0003rz\n\t\u0011\"\u0011\u0003t\"I1\u0011\u0001 \u0002\u0002\u0013\u000511\u0001\u0005\n\u0007\u001bq\u0014\u0011!C!\u0007\u001fA\u0011b!\u0005?\u0003\u0003%\tea\u0005\t\u0013\rUa(!A\u0005\n\r]q!\u0003CW\r!\u0005%QDB;\r%\u0019yG\u0002EA\u0005;\u0019\t\bC\u0004\u0003z%#\taa\u001d\t\u0013\t-\u0017*!A\u0005B\t5\u0007\"\u0003Bm\u0013\u0006\u0005I\u0011\u0001Bn\u0011%\u0011\u0019/SA\u0001\n\u0003\u00199\bC\u0005\u0003r&\u000b\t\u0011\"\u0011\u0003t\"I1\u0011A%\u0002\u0002\u0013\u000511\u0010\u0005\n\u0007\u001bI\u0015\u0011!C!\u0007\u001fA\u0011b!\u0005J\u0003\u0003%\tea\u0005\t\u0013\rU\u0011*!A\u0005\n\r]q!\u0003CX\r!\u0005%QDB\u0013\r%\u0019yB\u0002EA\u0005;\u0019\t\u0003C\u0004\u0003zQ#\taa\t\t\u0013\t-G+!A\u0005B\t5\u0007\"\u0003Bm)\u0006\u0005I\u0011\u0001Bn\u0011%\u0011\u0019\u000fVA\u0001\n\u0003\u00199\u0003C\u0005\u0003rR\u000b\t\u0011\"\u0011\u0003t\"I1\u0011\u0001+\u0002\u0002\u0013\u000511\u0006\u0005\n\u0007\u001b!\u0016\u0011!C!\u0007\u001fA\u0011b!\u0005U\u0003\u0003%\tea\u0005\t\u0013\rUA+!A\u0005\n\r]q!\u0003CY\r!\u0005%QDB#\r%\u0019yD\u0002EA\u0005;\u0019\t\u0005C\u0004\u0003z}#\taa\u0011\t\u0013\t-w,!A\u0005B\t5\u0007\"\u0003Bm?\u0006\u0005I\u0011\u0001Bn\u0011%\u0011\u0019oXA\u0001\n\u0003\u00199\u0005C\u0005\u0003r~\u000b\t\u0011\"\u0011\u0003t\"I1\u0011A0\u0002\u0002\u0013\u000511\n\u0005\n\u0007\u001by\u0016\u0011!C!\u0007\u001fA\u0011b!\u0005`\u0003\u0003%\tea\u0005\t\u0013\rUq,!A\u0005\n\r]q!\u0003CZ\r!\u0005%QDB3\r%\u0019yF\u0002EA\u0005;\u0019\t\u0007C\u0004\u0003z)$\taa\u0019\t\u0013\t-'.!A\u0005B\t5\u0007\"\u0003BmU\u0006\u0005I\u0011\u0001Bn\u0011%\u0011\u0019O[A\u0001\n\u0003\u00199\u0007C\u0005\u0003r*\f\t\u0011\"\u0011\u0003t\"I1\u0011\u00016\u0002\u0002\u0013\u000511\u000e\u0005\n\u0007\u001bQ\u0017\u0011!C!\u0007\u001fA\u0011b!\u0005k\u0003\u0003%\tea\u0005\t\u0013\rU!.!A\u0005\n\r]q!\u0003C[\r!\u0005%QDB+\r%\u0019yE\u0002EA\u0005;\u0019\t\u0006C\u0004\u0003zU$\taa\u0015\t\u0013\t-W/!A\u0005B\t5\u0007\"\u0003Bmk\u0006\u0005I\u0011\u0001Bn\u0011%\u0011\u0019/^A\u0001\n\u0003\u00199\u0006C\u0005\u0003rV\f\t\u0011\"\u0011\u0003t\"I1\u0011A;\u0002\u0002\u0013\u000511\f\u0005\n\u0007\u001b)\u0018\u0011!C!\u0007\u001fA\u0011b!\u0005v\u0003\u0003%\tea\u0005\t\u0013\rUQ/!A\u0005\n\r]a!CBZ\rA\u0005\u0019\u0013EB[\u000f%!9L\u0002EA\u0005;\u0019yPB\u0005\u0004z\u001aA\tI!\b\u0004|\"A!\u0011PA\u0002\t\u0003\u0019i\u0010\u0003\u0006\u0003L\u0006\r\u0011\u0011!C!\u0005\u001bD!B!7\u0002\u0004\u0005\u0005I\u0011\u0001Bn\u0011)\u0011\u0019/a\u0001\u0002\u0002\u0013\u0005A\u0011\u0001\u0005\u000b\u0005c\f\u0019!!A\u0005B\tM\bBCB\u0001\u0003\u0007\t\t\u0011\"\u0001\u0005\u0006!Q1QBA\u0002\u0003\u0003%\tea\u0004\t\u0015\rE\u00111AA\u0001\n\u0003\u001a\u0019\u0002\u0003\u0006\u0004\u0016\u0005\r\u0011\u0011!C\u0005\u0007/9\u0011\u0002\"/\u0007\u0011\u0003\u0013iba8\u0007\u0013\reg\u0001#!\u0003\u001e\rm\u0007\u0002\u0003B=\u00033!\ta!8\t\u0015\t-\u0017\u0011DA\u0001\n\u0003\u0012i\r\u0003\u0006\u0003Z\u0006e\u0011\u0011!C\u0001\u00057D!Ba9\u0002\u001a\u0005\u0005I\u0011ABq\u0011)\u0011\t0!\u0007\u0002\u0002\u0013\u0005#1\u001f\u0005\u000b\u0007\u0003\tI\"!A\u0005\u0002\r\u0015\bBCB\u0007\u00033\t\t\u0011\"\u0011\u0004\u0010!Q1\u0011CA\r\u0003\u0003%\tea\u0005\t\u0015\rU\u0011\u0011DA\u0001\n\u0013\u00199bB\u0005\u0005<\u001aA\tI!\b\u0004P\u001aI1\u0011\u001a\u0004\t\u0002\nu11\u001a\u0005\t\u0005s\ny\u0003\"\u0001\u0004N\"Q!1ZA\u0018\u0003\u0003%\tE!4\t\u0015\te\u0017qFA\u0001\n\u0003\u0011Y\u000e\u0003\u0006\u0003d\u0006=\u0012\u0011!C\u0001\u0007#D!B!=\u00020\u0005\u0005I\u0011\tBz\u0011)\u0019\t!a\f\u0002\u0002\u0013\u00051Q\u001b\u0005\u000b\u0007\u001b\ty#!A\u0005B\r=\u0001BCB\t\u0003_\t\t\u0011\"\u0011\u0004\u0014!Q1QCA\u0018\u0003\u0003%Iaa\u0006\b\u0013\u0011uf\u0001#!\u0003\u001e\r}f!CB]\r!\u0005%QDB^\u0011!\u0011I(!\u0012\u0005\u0002\ru\u0006B\u0003Bf\u0003\u000b\n\t\u0011\"\u0011\u0003N\"Q!\u0011\\A#\u0003\u0003%\tAa7\t\u0015\t\r\u0018QIA\u0001\n\u0003\u0019\t\r\u0003\u0006\u0003r\u0006\u0015\u0013\u0011!C!\u0005gD!b!\u0001\u0002F\u0005\u0005I\u0011ABc\u0011)\u0019i!!\u0012\u0002\u0002\u0013\u00053q\u0002\u0005\u000b\u0007#\t)%!A\u0005B\rM\u0001BCB\u000b\u0003\u000b\n\t\u0011\"\u0003\u0004\u0018\u001dIAq\u0018\u0004\t\u0002\nuAq\u0006\u0004\n\tS1\u0001\u0012\u0011B\u000f\tWA\u0001B!\u001f\u0002\\\u0011\u0005AQ\u0006\u0005\u000b\u0005\u0017\fY&!A\u0005B\t5\u0007B\u0003Bm\u00037\n\t\u0011\"\u0001\u0003\\\"Q!1]A.\u0003\u0003%\t\u0001\"\r\t\u0015\tE\u00181LA\u0001\n\u0003\u0012\u0019\u0010\u0003\u0006\u0004\u0002\u0005m\u0013\u0011!C\u0001\tkA!b!\u0004\u0002\\\u0005\u0005I\u0011IB\b\u0011)\u0019\t\"a\u0017\u0002\u0002\u0013\u000531\u0003\u0005\u000b\u0007+\tY&!A\u0005\n\r]q!\u0003Ca\r!\u0005%Q\u0004C\u0010\r%!IB\u0002EA\u0005;!Y\u0002\u0003\u0005\u0003z\u0005ED\u0011\u0001C\u000f\u0011)\u0011Y-!\u001d\u0002\u0002\u0013\u0005#Q\u001a\u0005\u000b\u00053\f\t(!A\u0005\u0002\tm\u0007B\u0003Br\u0003c\n\t\u0011\"\u0001\u0005\"!Q!\u0011_A9\u0003\u0003%\tEa=\t\u0015\r\u0005\u0011\u0011OA\u0001\n\u0003!)\u0003\u0003\u0006\u0004\u000e\u0005E\u0014\u0011!C!\u0007\u001fA!b!\u0005\u0002r\u0005\u0005I\u0011IB\n\u0011)\u0019)\"!\u001d\u0002\u0002\u0013%1qC\u0004\n\t\u00074\u0001\u0012\u0011B\u000f\u0007_4\u0011b!;\u0007\u0011\u0003\u0013iba;\t\u0011\te\u0014q\u0011C\u0001\u0007[D!Ba3\u0002\b\u0006\u0005I\u0011\tBg\u0011)\u0011I.a\"\u0002\u0002\u0013\u0005!1\u001c\u0005\u000b\u0005G\f9)!A\u0005\u0002\rE\bB\u0003By\u0003\u000f\u000b\t\u0011\"\u0011\u0003t\"Q1\u0011AAD\u0003\u0003%\ta!>\t\u0015\r5\u0011qQA\u0001\n\u0003\u001ay\u0001\u0003\u0006\u0004\u0012\u0005\u001d\u0015\u0011!C!\u0007'A!b!\u0006\u0002\b\u0006\u0005I\u0011BB\f\u000f%!)M\u0002EA\u0005;!yAB\u0005\u0005\n\u0019A\tI!\b\u0005\f!A!\u0011PAO\t\u0003!i\u0001\u0003\u0006\u0003L\u0006u\u0015\u0011!C!\u0005\u001bD!B!7\u0002\u001e\u0006\u0005I\u0011\u0001Bn\u0011)\u0011\u0019/!(\u0002\u0002\u0013\u0005A\u0011\u0003\u0005\u000b\u0005c\fi*!A\u0005B\tM\bBCB\u0001\u0003;\u000b\t\u0011\"\u0001\u0005\u0016!Q1QBAO\u0003\u0003%\tea\u0004\t\u0015\rE\u0011QTA\u0001\n\u0003\u001a\u0019\u0002\u0003\u0006\u0004\u0016\u0005u\u0015\u0011!C\u0005\u0007/1\u0001\u0002b2\u0007\u0001\n\u0015B\u0011\u001a\u0005\f\u0005w\t\tL!f\u0001\n\u0003!y\u000fC\u0006\u0005r\u0006E&\u0011#Q\u0001\n\tu\u0005b\u0003Cz\u0003c\u0013)\u001a!C\u0001\tkD1\u0002b>\u00022\nE\t\u0015!\u0003\u00040\"YA\u0011`AY\u0005+\u0007I\u0011\u0001C~\u0011-!i0!-\u0003\u0012\u0003\u0006I\u0001\"\u001c\t\u0017\u0011}\u0018\u0011\u0017BK\u0002\u0013\u0005A1 \u0005\f\u000b\u0003\t\tL!E!\u0002\u0013!i\u0007C\u0006\u0006\u0004\u0005E&Q3A\u0005\u0002\tm\u0007bCC\u0003\u0003c\u0013\t\u0012)A\u0005\u0005;D1\"b\u0002\u00022\nU\r\u0011\"\u0001\u0003\\\"YQ\u0011BAY\u0005#\u0005\u000b\u0011\u0002Bo\u0011!\u0011I(!-\u0005\u0002\u0015-\u0001\u0002\u0003B=\u0003c#\t!b\u0007\t\u0011\te\u0014\u0011\u0017C\u0001\u000bGA\u0001B!\u001f\u00022\u0012\u0005Q1\u0006\u0005\t\u000bc\t\t\f\"\u0011\u00064!AQ\u0011HAY\t\u0003*Y\u0004\u0003\u0005\u0006@\u0005EF\u0011\tC~\u0011!)\t%!-\u0005B\u0011m\b\u0002CC\"\u0003c#\t&\"\u0012\t\u0011\u0015=\u0013\u0011\u0017C!\u000b#B\u0001\"\"\u0019\u00022\u0012\u0005S1\r\u0005\t\u000bW\n\t\f\"\u0011\u0006n!AQqNAY\t\u0003*\t\b\u0003\u0005\u0006t\u0005EF\u0011IC;\u0011!)))!-\u0005B\u0015\u001d\u0005\u0002CCG\u0003c#\t%b$\t\u0011\u0015U\u0015\u0011\u0017C!\u000b/C1\"\"(\u00022\"\u0015\r\u0011\"\u0011\u0006 \"AQ\u0011UAY\t\u0003*\u0019\u000b\u0003\u0006\u0006&\u0006E\u0016\u0011!C\u0001\u000bOC!\"\".\u00022F\u0005I\u0011AC\\\u0011))i-!-\u0012\u0002\u0013\u0005Qq\u001a\u0005\u000b\u000b'\f\t,%A\u0005\u0002\u0015U\u0007BCCm\u0003c\u000b\n\u0011\"\u0001\u0006V\"QQ1\\AY#\u0003%\t!\"8\t\u0015\u0015\u0005\u0018\u0011WI\u0001\n\u0003)i\u000e\u0003\u0006\u0003L\u0006E\u0016\u0011!C!\u0005\u001bD!B!7\u00022\u0006\u0005I\u0011\u0001Bn\u0011)\u0011\u0019/!-\u0002\u0002\u0013\u0005Q1\u001d\u0005\u000b\u0005c\f\t,!A\u0005B\tM\bBCB\u0001\u0003c\u000b\t\u0011\"\u0001\u0006h\"QQ1^AY\u0003\u0003%\t%\"<\t\u0015\u0015E\u0018\u0011WA\u0001\n\u0003*\u0019pB\u0006\u0006x\u001a\t\t\u0011#\u0001\u0003&\u0015eha\u0003Cd\r\u0005\u0005\t\u0012\u0001B\u0013\u000bwD\u0001B!\u001f\u0003\u0010\u0011\u0005a\u0011\u0002\u0005\u000b\u0007#\u0011y!!A\u0005F\rM\u0001B\u0003D\u0006\u0005\u001f\t\t\u0011\"!\u0007\u000e!Qa1\u0004B\b\u0003\u0003%\tI\"\b\t\u0015\rU!qBA\u0001\n\u0013\u00199B\u0001\nTk6l\u0017M]=Ck&dG-\u001a:J[Bd'\u0002\u0002B\u0010\u0005C\tAa\u001d;bi*!!1\u0005B\u0013\u0003\tiGN\u0003\u0003\u0003(\t%\u0012!B:qCJ\\'\u0002\u0002B\u0016\u0005[\ta!\u00199bG\",'B\u0001B\u0018\u0003\ry'oZ\n\u0004\u0001\tM\u0002\u0003\u0002B\u001b\u0005oi!A!\b\n\t\te\"Q\u0004\u0002\u000f'VlW.\u0019:z\u0005VLG\u000eZ3s\u0003A\u0011X-];fgR,G-T3ue&\u001c7o\u0001\u0001\u0011\r\t\u0005#Q\u000bB.\u001d\u0011\u0011\u0019Ea\u0014\u000f\t\t\u0015#1J\u0007\u0003\u0005\u000fRAA!\u0013\u0003>\u00051AH]8pizJ!A!\u0014\u0002\u000bM\u001c\u0017\r\\1\n\t\tE#1K\u0001\ba\u0006\u001c7.Y4f\u0015\t\u0011i%\u0003\u0003\u0003X\te#aA*fc*!!\u0011\u000bB*!\r\u0011i\u0006\u0005\b\u0004\u0005k)\u0011AE*v[6\f'/\u001f\"vS2$WM]%na2\u00042A!\u000e\u0007'\u00151!Q\rB7!\u0011\u00119G!\u001b\u000e\u0005\tM\u0013\u0002\u0002B6\u0005'\u0012a!\u00118z%\u00164\u0007\u0003\u0002B8\u0005kj!A!\u001d\u000b\t\tM$QE\u0001\tS:$XM\u001d8bY&!!q\u000fB9\u0005\u001daunZ4j]\u001e\fa\u0001P5oSRtDC\u0001B1\u0003IIW\u000e\u001d7f[\u0016tG/\u001a3NKR\u0014\u0018nY:\u0016\u0005\t\u0005\u0005C\u0002B!\u0005+\u0012\u0019\t\u0005\u0003\u0003\u0006\n5e\u0002\u0002BD\u0005\u0013\u0003BA!\u0012\u0003T%!!1\u0012B*\u0003\u0019\u0001&/\u001a3fM&!!q\u0012BI\u0005\u0019\u0019FO]5oO*!!1\u0012B*\u0003I9W\r\u001e*fY\u00164\u0018M\u001c;NKR\u0014\u0018nY:\u0015\t\t]E\u0011\b\t\t\u0005O\u0012IJ!(\u00040&!!1\u0014B*\u0005\u0019!V\u000f\u001d7feA1!\u0011\tB+\u0005?\u00032A!)\u0011\u001b\u00051!AB'fiJL7mE\u0003\u0011\u0005K\u00129\u000b\u0005\u0003\u0003*\nMVB\u0001BV\u0015\u0011\u0011iKa,\u0002\u0005%|'B\u0001BY\u0003\u0011Q\u0017M^1\n\t\tU&1\u0016\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0015\f!y\"&cX;k\u0013Nj\u0002FA\u0003D_VtGoE\u0005?\u0005K\u0012yJ!0\u0003DB!!q\rB`\u0013\u0011\u0011\tMa\u0015\u0003\u000fA\u0013x\u000eZ;diB!!\u0011\tBc\u0013\u0011\u0011)L!\u0017\u0015\u0005\t%\u0007c\u0001BQ}\u0005i\u0001O]8ek\u000e$\bK]3gSb,\"Aa4\u0011\t\tE'q[\u0007\u0003\u0005'TAA!6\u00030\u0006!A.\u00198h\u0013\u0011\u0011yIa5\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\tu\u0007\u0003\u0002B4\u0005?LAA!9\u0003T\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!!q\u001dBw!\u0011\u00119G!;\n\t\t-(1\u000b\u0002\u0004\u0003:L\b\"\u0003Bx\u0005\u0006\u0005\t\u0019\u0001Bo\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011!Q\u001f\t\u0007\u0005o\u0014iPa:\u000e\u0005\te(\u0002\u0002B~\u0005'\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\u0011yP!?\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0007\u000b\u0019Y\u0001\u0005\u0003\u0003h\r\u001d\u0011\u0002BB\u0005\u0005'\u0012qAQ8pY\u0016\fg\u000eC\u0005\u0003p\u0012\u000b\t\u00111\u0001\u0003h\u0006A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0003^\u0006AAo\\*ue&tw\r\u0006\u0002\u0003P\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u00111\u0011\u0004\t\u0005\u0005#\u001cY\"\u0003\u0003\u0004\u001e\tM'AB(cU\u0016\u001cGOA\u0002NCb\u001c\u0012\u0002\u0016B3\u0005?\u0013iLa1\u0015\u0005\r\u0015\u0002c\u0001BQ)R!!q]B\u0015\u0011%\u0011y\u000fWA\u0001\u0002\u0004\u0011i\u000e\u0006\u0003\u0004\u0006\r5\u0002\"\u0003Bx5\u0006\u0005\t\u0019\u0001Bt\u0005\u0011iU-\u00198\u0014\u0013I\u0011)Ga(\u0003>\n\rGCAB\u001b!\r\u0011\tK\u0005\u000b\u0005\u0005O\u001cI\u0004C\u0005\u0003pZ\t\t\u00111\u0001\u0003^R!1QAB\u001f\u0011%\u0011y\u000fGA\u0001\u0002\u0004\u00119OA\u0002NS:\u001c\u0012b\u0018B3\u0005?\u0013iLa1\u0015\u0005\r\u0015\u0003c\u0001BQ?R!!q]B%\u0011%\u0011yoYA\u0001\u0002\u0004\u0011i\u000e\u0006\u0003\u0004\u0006\r5\u0003\"\u0003BxK\u0006\u0005\t\u0019\u0001Bt\u0005\u0019quN]7McMIQO!\u001a\u0003 \nu&1\u0019\u000b\u0003\u0007+\u00022A!)v)\u0011\u00119o!\u0017\t\u0013\t=\u00180!AA\u0002\tuG\u0003BB\u0003\u0007;B\u0011Ba<|\u0003\u0003\u0005\rAa:\u0003\r9{'/\u001c'3'%Q'Q\rBP\u0005{\u0013\u0019\r\u0006\u0002\u0004fA\u0019!\u0011\u00156\u0015\t\t\u001d8\u0011\u000e\u0005\n\u0005_t\u0017\u0011!a\u0001\u0005;$Ba!\u0002\u0004n!I!q\u001e9\u0002\u0002\u0003\u0007!q\u001d\u0002\f\u001dVlgj\u001c8[KJ|7oE\u0005J\u0005K\u0012yJ!0\u0003DR\u00111Q\u000f\t\u0004\u0005CKE\u0003\u0002Bt\u0007sB\u0011Ba<N\u0003\u0003\u0005\rA!8\u0015\t\r\u00151Q\u0010\u0005\n\u0005_|\u0015\u0011!a\u0001\u0005O\u00141a\u0015;e'%\u0019$Q\rBP\u0005{\u0013\u0019\r\u0006\u0002\u0004\u0006B\u0019!\u0011U\u001a\u0015\t\t\u001d8\u0011\u0012\u0005\n\u0005_<\u0014\u0011!a\u0001\u0005;$Ba!\u0002\u0004\u000e\"I!q^\u001d\u0002\u0002\u0003\u0007!q\u001d\u0002\u0004'Vl7#C\u000f\u0003f\t}%Q\u0018Bb)\t\u0019)\nE\u0002\u0003\"v!BAa:\u0004\u001a\"I!q^\u0011\u0002\u0002\u0003\u0007!Q\u001c\u000b\u0005\u0007\u000b\u0019i\nC\u0005\u0003p\u000e\n\t\u00111\u0001\u0003h\nAa+\u0019:jC:\u001cWmE\u0005)\u0005K\u0012yJ!0\u0003DR\u00111Q\u0015\t\u0004\u0005CCC\u0003\u0002Bt\u0007SC\u0011Ba<-\u0003\u0003\u0005\rA!8\u0015\t\r\u00151Q\u0016\u0005\n\u0005_t\u0013\u0011!a\u0001\u0005O\u0004bA!\u0011\u0003V\rE\u0006c\u0001BQ\u007f\ni1i\\7qkR,W*\u001a;sS\u000e\u001cRa B3\u0005OK\u0013c`A#\u0003_\tI\"a\"\u0002\u0004\u0005u\u0015\u0011OA.\u0005%\u0019u.\u001c9vi\u0016d\u0015g\u0005\u0006\u0002F\t\u00154\u0011\u0017B_\u0005\u0007$\"aa0\u0011\t\t\u0005\u0016Q\t\u000b\u0005\u0005O\u001c\u0019\r\u0003\u0006\u0003p\u00065\u0013\u0011!a\u0001\u0005;$Ba!\u0002\u0004H\"Q!q^A)\u0003\u0003\u0005\rAa:\u0003\u0013\r{W\u000e];uK6\u00134CCA\u0018\u0005K\u001a\tL!0\u0003DR\u00111q\u001a\t\u0005\u0005C\u000by\u0003\u0006\u0003\u0003h\u000eM\u0007B\u0003Bx\u0003o\t\t\u00111\u0001\u0003^R!1QABl\u0011)\u0011y/a\u000f\u0002\u0002\u0003\u0007!q\u001d\u0002\u000b\u0007>l\u0007/\u001e;f\u001bJr7CCA\r\u0005K\u001a\tL!0\u0003DR\u00111q\u001c\t\u0005\u0005C\u000bI\u0002\u0006\u0003\u0003h\u000e\r\bB\u0003Bx\u0003C\t\t\u00111\u0001\u0003^R!1QABt\u0011)\u0011y/!\n\u0002\u0002\u0003\u0007!q\u001d\u0002\u000b\u0007>l\u0007/\u001e;f\u001b\u0006D8CCAD\u0005K\u001a\tL!0\u0003DR\u00111q\u001e\t\u0005\u0005C\u000b9\t\u0006\u0003\u0003h\u000eM\bB\u0003Bx\u0003\u001f\u000b\t\u00111\u0001\u0003^R!1QAB|\u0011)\u0011y/a%\u0002\u0002\u0003\u0007!q\u001d\u0002\f\u0007>l\u0007/\u001e;f\u001b\u0016\fgn\u0005\u0006\u0002\u0004\t\u00154\u0011\u0017B_\u0005\u0007$\"aa@\u0011\t\t\u0005\u00161\u0001\u000b\u0005\u0005O$\u0019\u0001\u0003\u0006\u0003p\u0006-\u0011\u0011!a\u0001\u0005;$Ba!\u0002\u0005\b!Q!q^A\b\u0003\u0003\u0005\rAa:\u0003\u0015\r{W\u000e];uK6Kgn\u0005\u0006\u0002\u001e\n\u00154\u0011\u0017B_\u0005\u0007$\"\u0001b\u0004\u0011\t\t\u0005\u0016Q\u0014\u000b\u0005\u0005O$\u0019\u0002\u0003\u0006\u0003p\u0006\u0015\u0016\u0011!a\u0001\u0005;$Ba!\u0002\u0005\u0018!Q!q^AU\u0003\u0003\u0005\rAa:\u0003\u0015\r{W\u000e];uK:s%l\u0005\u0006\u0002r\t\u00154\u0011\u0017B_\u0005\u0007$\"\u0001b\b\u0011\t\t\u0005\u0016\u0011\u000f\u000b\u0005\u0005O$\u0019\u0003\u0003\u0006\u0003p\u0006e\u0014\u0011!a\u0001\u0005;$Ba!\u0002\u0005(!Q!q^A?\u0003\u0003\u0005\rAa:\u0003!\r{W\u000e];uK^+\u0017n\u001a5u'Vl7CCA.\u0005K\u001a\tL!0\u0003DR\u0011Aq\u0006\t\u0005\u0005C\u000bY\u0006\u0006\u0003\u0003h\u0012M\u0002B\u0003Bx\u0003G\n\t\u00111\u0001\u0003^R!1Q\u0001C\u001c\u0011)\u0011y/a\u001a\u0002\u0002\u0003\u0007!q\u001d\u0005\b\twI\u0001\u0019\u0001BA\u0003%\u0011X-];fgR,G\rK\u0003\n\t\u007f!Y\u0005\u0005\u0004\u0003h\u0011\u0005CQI\u0005\u0005\t\u0007\u0012\u0019F\u0001\u0004uQJ|wo\u001d\t\u0005\u0005\u0003\"9%\u0003\u0003\u0005J\te#\u0001G%mY\u0016<\u0017\r\\!sOVlWM\u001c;Fq\u000e,\u0007\u000f^5p]\u0006\u0012AQJ\u00018/\",g\u000e\t;iK\u0002b\u0017n\u001d;!SN\u0004S-\u001c9us\u0002z'\u000f\t8pi\u0002\n\u0007e];cg\u0016$\be\u001c4!W:|wO\u001c\u0011nKR\u0014\u0018nY:\u0002'M$(/^2ukJ,gi\u001c:NKR\u0014\u0018nY:\u0015\t\u0011MC1\r\t\u0005\t+\"y&\u0004\u0002\u0005X)!A\u0011\fC.\u0003\u0015!\u0018\u0010]3t\u0015\u0011!iF!\n\u0002\u0007M\fH.\u0003\u0003\u0005b\u0011]#AC*ueV\u001cG\u000fV=qK\"9AQ\r\u0006A\u0002\tu\u0015aB7fiJL7m]\u0001\u0018Kb$(/Y2u%\u0016\fX/Z:uK\u0012lU\r\u001e:jGN$BAa&\u0005l!9AQM\u0006A\u0002\u00115\u0004\u0003\u0002C8\tsj!\u0001\"\u001d\u000b\t\u0011MDQO\u0001\fKb\u0004(/Z:tS>t7O\u0003\u0003\u0005x\u0011m\u0013\u0001C2bi\u0006d\u0017p\u001d;\n\t\u0011mD\u0011\u000f\u0002\u000b\u000bb\u0004(/Z:tS>t\u0017!\u0003<fGR|'/\u0016#U+\t!\t\t\u0005\u0003\u0005\u0004\u0012%UB\u0001CC\u0015\u0011!9I!\t\u0002\r1Lg.\u00197h\u0013\u0011!Y\t\"\"\u0003\u0013Y+7\r^8s+\u0012#\u0016A\u0003<fGR|'/\u0016#UA\u0005Q\u0011\r\u001c7NKR\u0014\u0018nY:\u0016\u0005\u0011M\u0005C\u0002B!\u0005+\")\n\u0005\u0007\u0003h\u0011]%1\u0011BP\t7\u001by+\u0003\u0003\u0005\u001a\nM#A\u0002+va2,G\u0007\u0005\u0003\u0005V\u0011u\u0015\u0002\u0002CP\t/\u0012\u0001\u0002R1uCRK\b/Z\u0001\fC2dW*\u001a;sS\u000e\u001c\b%\u0001\u0003NK\u0006t\u0017aA*v[\u0006Aa+\u0019:jC:\u001cW-A\u0002Ti\u0012\fQaQ8v]R\f1BT;n\u001d>t',\u001a:pg\u0006\u0019Q*\u0019=\u0002\u00075Kg.\u0001\u0004O_JlGJM\u0001\u0007\u001d>\u0014X\u000eT\u0019\u0002\u0017\r{W\u000e];uK6+\u0017M\\\u0001\u000b\u0007>l\u0007/\u001e;f\u001bJr\u0017!C\"p[B,H/Z'3\u0003%\u0019u.\u001c9vi\u0016d\u0015'\u0001\tD_6\u0004X\u000f^3XK&<\u0007\u000e^*v[\u0006Q1i\\7qkR,gJ\u0014.\u0002\u0015\r{W\u000e];uK6\u000b\u00070\u0001\u0006D_6\u0004X\u000f^3NS:\u0014\u0001#T3ue&\u001c7/Q4he\u0016<\u0017\r^3\u0014\u0019\u0005EF1\u001aCo\tG\u0014iLa1\u0011\r\u00115G1\u001bCl\u001b\t!yM\u0003\u0003\u0005R\u0012E\u0014!C1hOJ,w-\u0019;f\u0013\u0011!)\u000eb4\u00031QK\b/\u001a3J[B,'/\u0019;jm\u0016\fum\u001a:fO\u0006$X\r\u0005\u0003\u00036\u0011e\u0017\u0002\u0002Cn\u0005;\u0011\u0001cU;n[\u0006\u0014\u0018N_3s\u0005V4g-\u001a:\u0011\t\u0011=Dq\\\u0005\u0005\tC$\tH\u0001\fJ[Bd\u0017nY5u\u0007\u0006\u001cH/\u00138qkR$\u0016\u0010]3t!\u0019!)\u000fb;\u0005n5\u0011Aq\u001d\u0006\u0005\tS$)(A\u0003ue\u0016,7/\u0003\u0003\u0005n\u0012\u001d(A\u0003\"j]\u0006\u0014\u0018\u0010T5lKV\u0011!QT\u0001\u0012e\u0016\fX/Z:uK\u0012lU\r\u001e:jGN\u0004\u0013a\u0006:fcV,7\u000f^3e\u0007>l\u0007/\u001e;f\u001b\u0016$(/[2t+\t\u0019y+\u0001\rsKF,Xm\u001d;fI\u000e{W\u000e];uK6+GO]5dg\u0002\nABZ3biV\u0014Xm]#yaJ,\"\u0001\"\u001c\u0002\u001b\u0019,\u0017\r^;sKN,\u0005\u0010\u001d:!\u0003)9X-[4ii\u0016C\bO]\u0001\fo\u0016Lw\r\u001b;FqB\u0014\b%\u0001\fnkR\f'\r\\3BO\u001e\u0014UO\u001a4fe>3gm]3u\u0003]iW\u000f^1cY\u0016\fum\u001a\"vM\u001a,'o\u00144gg\u0016$\b%\u0001\u000bj]B,H/Q4h\u0005V4g-\u001a:PM\u001a\u001cX\r^\u0001\u0016S:\u0004X\u000f^!hO\n+hMZ3s\u001f\u001a47/\u001a;!)9)i!b\u0004\u0006\u0012\u0015MQQCC\f\u000b3\u0001BA!)\u00022\"A!1HAf\u0001\u0004\u0011i\n\u0003\u0005\u0005t\u0006-\u0007\u0019ABX\u0011!!I0a3A\u0002\u00115\u0004\u0002\u0003C\u0000\u0003\u0017\u0004\r\u0001\"\u001c\t\u0011\u0015\r\u00111\u001aa\u0001\u0005;D\u0001\"b\u0002\u0002L\u0002\u0007!Q\u001c\u000b\t\u000b\u001b)i\"b\b\u0006\"!AAQMAg\u0001\u0004\u00119\n\u0003\u0005\u0005z\u00065\u0007\u0019\u0001C7\u0011!!y0!4A\u0002\u00115D\u0003CC\u0007\u000bK)9#\"\u000b\t\u0011\tm\u0012q\u001aa\u0001\t[B\u0001\u0002\"?\u0002P\u0002\u0007AQ\u000e\u0005\t\t\u007f\fy\r1\u0001\u0005nQ1QQBC\u0017\u000b_A\u0001Ba\u000f\u0002R\u0002\u0007AQ\u000e\u0005\t\ts\f\t\u000e1\u0001\u0005n\u0005!QM^1m)\u0011\u00119/\"\u000e\t\u0011\u0015]\u00121\u001ba\u0001\t/\fQa\u001d;bi\u0016\f!\"\u001b8qkR$\u0016\u0010]3t+\t)i\u0004\u0005\u0004\u0003B\tUC1T\u0001\u0005Y\u00164G/A\u0003sS\u001eDG/A\fxSRDg*Z<DQ&dGM]3o\u0013:$XM\u001d8bYR1QQBC$\u000b\u0017B\u0001\"\"\u0013\u0002\\\u0002\u0007AQN\u0001\b]\u0016<H*\u001a4u\u0011!)i%a7A\u0002\u00115\u0014\u0001\u00038foJKw\r\u001b;\u0002\rU\u0004H-\u0019;f)\u0019!9.b\u0015\u0006V!AQqGAo\u0001\u0004!9\u000e\u0003\u0005\u0006X\u0005u\u0007\u0019AC-\u0003\r\u0011xn\u001e\t\u0005\u000b7*i&\u0004\u0002\u0005v%!Qq\fC;\u0005-Ie\u000e^3s]\u0006d'k\\<\u0002\u000b5,'oZ3\u0015\r\u0011]WQMC4\u0011!)9$a8A\u0002\u0011]\u0007\u0002CC5\u0003?\u0004\r\u0001b6\u0002\u000b=$\b.\u001a:\u0002\u00119,H\u000e\\1cY\u0016,\"a!\u0002\u0002/\r\u0014X-\u0019;f\u0003\u001e<'/Z4bi&|gNQ;gM\u0016\u0014HC\u0001Cl\u0003%\u0019XM]5bY&TX\r\u0006\u0003\u0006x\u0015\r\u0005C\u0002B4\u000bs*i(\u0003\u0003\u0006|\tM#!B!se\u0006L\b\u0003\u0002B4\u000b\u007fJA!\"!\u0003T\t!!)\u001f;f\u0011!)9$!:A\u0002\u0011]\u0017a\u00033fg\u0016\u0014\u0018.\u00197ju\u0016$B\u0001b6\u0006\n\"AQ1RAt\u0001\u0004)9(A\u0003csR,7/A\u000fxSRDg*Z<NkR\f'\r\\3BO\u001e\u0014UO\u001a4fe>3gm]3u)\u0011)i!\"%\t\u0011\u0015M\u0015\u0011\u001ea\u0001\u0005;\f\u0011D\\3x\u001bV$\u0018M\u00197f\u0003\u001e<')\u001e4gKJ|eMZ:fi\u0006Yr/\u001b;i\u001d\u0016<\u0018J\u001c9vi\u0006;wMQ;gM\u0016\u0014xJ\u001a4tKR$B!\"\u0004\u0006\u001a\"AQ1TAv\u0001\u0004\u0011i.A\foK^Le\u000e];u\u0003\u001e<')\u001e4gKJ|eMZ:fi\u0006AA-\u0019;b)f\u0004X-\u0006\u0002\u0005\u001c\u0006Q\u0001O]3uift\u0015-\\3\u0016\u0005\t\r\u0015\u0001B2paf$b\"\"\u0004\u0006*\u0016-VQVCX\u000bc+\u0019\f\u0003\u0006\u0003<\u0005E\b\u0013!a\u0001\u0005;C!\u0002b=\u0002rB\u0005\t\u0019ABX\u0011)!I0!=\u0011\u0002\u0003\u0007AQ\u000e\u0005\u000b\t\u007f\f\t\u0010%AA\u0002\u00115\u0004BCC\u0002\u0003c\u0004\n\u00111\u0001\u0003^\"QQqAAy!\u0003\u0005\rA!8\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011Q\u0011\u0018\u0016\u0005\u0005;+Yl\u000b\u0002\u0006>B!QqXCe\u001b\t)\tM\u0003\u0003\u0006D\u0016\u0015\u0017!C;oG\",7m[3e\u0015\u0011)9Ma\u0015\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0006L\u0016\u0005'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TCACiU\u0011\u0019y+b/\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\u0011Qq\u001b\u0016\u0005\t[*Y,\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001b\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%kU\u0011Qq\u001c\u0016\u0005\u0005;,Y,\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001c\u0015\t\t\u001dXQ\u001d\u0005\u000b\u0005_\u0014\u0019!!AA\u0002\tuG\u0003BB\u0003\u000bSD!Ba<\u0003\b\u0005\u0005\t\u0019\u0001Bt\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\t=Wq\u001e\u0005\u000b\u0005_\u0014I!!AA\u0002\tu\u0017AB3rk\u0006d7\u000f\u0006\u0003\u0004\u0006\u0015U\bB\u0003Bx\u0005\u0017\t\t\u00111\u0001\u0003h\u0006\u0001R*\u001a;sS\u000e\u001c\u0018iZ4sK\u001e\fG/\u001a\t\u0005\u0005C\u0013ya\u0005\u0004\u0003\u0010\u0015u(q\u0015\t\u0013\u000b\u007f4)A!(\u00040\u00125DQ\u000eBo\u0005;,i!\u0004\u0002\u0007\u0002)!a1\u0001B*\u0003\u001d\u0011XO\u001c;j[\u0016LAAb\u0002\u0007\u0002\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001c\u0015\u0005\u0015e\u0018!B1qa2LHCDC\u0007\r\u001f1\tBb\u0005\u0007\u0016\u0019]a\u0011\u0004\u0005\t\u0005w\u0011)\u00021\u0001\u0003\u001e\"AA1\u001fB\u000b\u0001\u0004\u0019y\u000b\u0003\u0005\u0005z\nU\u0001\u0019\u0001C7\u0011!!yP!\u0006A\u0002\u00115\u0004\u0002CC\u0002\u0005+\u0001\rA!8\t\u0011\u0015\u001d!Q\u0003a\u0001\u0005;\fq!\u001e8baBd\u0017\u0010\u0006\u0003\u0007 \u0019-\u0002C\u0002B4\rC1)#\u0003\u0003\u0007$\tM#AB(qi&|g\u000e\u0005\t\u0003h\u0019\u001d\"QTBX\t[\"iG!8\u0003^&!a\u0011\u0006B*\u0005\u0019!V\u000f\u001d7fm!QaQ\u0006B\f\u0003\u0003\u0005\r!\"\u0004\u0002\u0007a$\u0003'\u0001\u000bsKF,Xm\u001d;fI\u000e{W\u000e]'fiJL7m\u001d\t\u0007\u0005\u0003\u0012)Fb\r\u0011\u0007\tus\u0010\u0006\u0004\u00078\u0019eb1\b\t\u0004\u0005k\u0001\u0001b\u0002B\u001e\u0007\u0001\u0007!q\b\u0005\b\r_\u0019\u0001\u0019\u0001D\u0019\u0003\u001d\u0019X/\\7bef$bA\"\u0011\u0007J\u00195\u0003\u0003\u0002D\"\r\u000bj!\u0001b\u0017\n\t\u0019\u001dC1\f\u0002\u0007\u0007>dW/\u001c8\t\u000f\u0019-C\u00011\u0001\u0007B\u0005Ya-Z1ukJ,7oQ8m\u0011\u001d1y\u0005\u0002a\u0001\r\u0003\n\u0011b^3jO\"$8i\u001c7"
)
public class SummaryBuilderImpl extends SummaryBuilder {
   private final Seq requestedMetrics;
   private final Seq requestedCompMetrics;

   public static StructType structureForMetrics(final Seq metrics) {
      return SummaryBuilderImpl$.MODULE$.structureForMetrics(metrics);
   }

   public static Tuple2 getRelevantMetrics(final Seq requested) throws IllegalArgumentException {
      return SummaryBuilderImpl$.MODULE$.getRelevantMetrics(requested);
   }

   public static Seq implementedMetrics() {
      return SummaryBuilderImpl$.MODULE$.implementedMetrics();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return SummaryBuilderImpl$.MODULE$.LogStringContext(sc);
   }

   public Column summary(final Column featuresCol, final Column weightCol) {
      return .MODULE$.ColumnConstructorExt(org.apache.spark.sql.Column..MODULE$).apply(new MetricsAggregate(this.requestedMetrics, this.requestedCompMetrics, org.apache.spark.sql.classic.ExpressionUtils..MODULE$.expression(featuresCol), org.apache.spark.sql.classic.ExpressionUtils..MODULE$.expression(weightCol), 0, 0));
   }

   public SummaryBuilderImpl(final Seq requestedMetrics, final Seq requestedCompMetrics) {
      this.requestedMetrics = requestedMetrics;
      this.requestedCompMetrics = requestedCompMetrics;
   }

   public static class Mean$ implements Metric, Product {
      public static final Mean$ MODULE$ = new Mean$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Mean";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Mean$;
      }

      public int hashCode() {
         return 2394085;
      }

      public String toString() {
         return "Mean";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Mean$.class);
      }
   }

   public static class Sum$ implements Metric, Product {
      public static final Sum$ MODULE$ = new Sum$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Sum";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Sum$;
      }

      public int hashCode() {
         return 83499;
      }

      public String toString() {
         return "Sum";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Sum$.class);
      }
   }

   public static class Variance$ implements Metric, Product {
      public static final Variance$ MODULE$ = new Variance$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Variance";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Variance$;
      }

      public int hashCode() {
         return -1184931183;
      }

      public String toString() {
         return "Variance";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Variance$.class);
      }
   }

   public static class Std$ implements Metric, Product {
      public static final Std$ MODULE$ = new Std$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Std";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Std$;
      }

      public int hashCode() {
         return 83459;
      }

      public String toString() {
         return "Std";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Std$.class);
      }
   }

   public static class Count$ implements Metric, Product {
      public static final Count$ MODULE$ = new Count$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Count";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Count$;
      }

      public int hashCode() {
         return 65298671;
      }

      public String toString() {
         return "Count";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Count$.class);
      }
   }

   public static class NumNonZeros$ implements Metric, Product {
      public static final NumNonZeros$ MODULE$ = new NumNonZeros$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "NumNonZeros";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof NumNonZeros$;
      }

      public int hashCode() {
         return 2074789252;
      }

      public String toString() {
         return "NumNonZeros";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(NumNonZeros$.class);
      }
   }

   public static class Max$ implements Metric, Product {
      public static final Max$ MODULE$ = new Max$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Max";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Max$;
      }

      public int hashCode() {
         return 77124;
      }

      public String toString() {
         return "Max";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Max$.class);
      }
   }

   public static class Min$ implements Metric, Product {
      public static final Min$ MODULE$ = new Min$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Min";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Min$;
      }

      public int hashCode() {
         return 77362;
      }

      public String toString() {
         return "Min";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Min$.class);
      }
   }

   public static class NormL2$ implements Metric, Product {
      public static final NormL2$ MODULE$ = new NormL2$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "NormL2";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof NormL2$;
      }

      public int hashCode() {
         return -1955879358;
      }

      public String toString() {
         return "NormL2";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(NormL2$.class);
      }
   }

   public static class NormL1$ implements Metric, Product {
      public static final NormL1$ MODULE$ = new NormL1$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "NormL1";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof NormL1$;
      }

      public int hashCode() {
         return -1955879359;
      }

      public String toString() {
         return "NormL1";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(NormL1$.class);
      }
   }

   public static class ComputeMean$ implements ComputeMetric, Product {
      public static final ComputeMean$ MODULE$ = new ComputeMean$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "ComputeMean";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof ComputeMean$;
      }

      public int hashCode() {
         return 1882768508;
      }

      public String toString() {
         return "ComputeMean";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ComputeMean$.class);
      }
   }

   public static class ComputeM2n$ implements ComputeMetric, Product {
      public static final ComputeM2n$ MODULE$ = new ComputeM2n$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "ComputeM2n";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof ComputeM2n$;
      }

      public int hashCode() {
         return 1723300882;
      }

      public String toString() {
         return "ComputeM2n";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ComputeM2n$.class);
      }
   }

   public static class ComputeM2$ implements ComputeMetric, Product {
      public static final ComputeM2$ MODULE$ = new ComputeM2$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "ComputeM2";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof ComputeM2$;
      }

      public int hashCode() {
         return 609779676;
      }

      public String toString() {
         return "ComputeM2";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ComputeM2$.class);
      }
   }

   public static class ComputeL1$ implements ComputeMetric, Product {
      public static final ComputeL1$ MODULE$ = new ComputeL1$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "ComputeL1";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof ComputeL1$;
      }

      public int hashCode() {
         return 609779644;
      }

      public String toString() {
         return "ComputeL1";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ComputeL1$.class);
      }
   }

   public static class ComputeWeightSum$ implements ComputeMetric, Product {
      public static final ComputeWeightSum$ MODULE$ = new ComputeWeightSum$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "ComputeWeightSum";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof ComputeWeightSum$;
      }

      public int hashCode() {
         return -1474921892;
      }

      public String toString() {
         return "ComputeWeightSum";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ComputeWeightSum$.class);
      }
   }

   public static class ComputeNNZ$ implements ComputeMetric, Product {
      public static final ComputeNNZ$ MODULE$ = new ComputeNNZ$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "ComputeNNZ";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof ComputeNNZ$;
      }

      public int hashCode() {
         return 1723302691;
      }

      public String toString() {
         return "ComputeNNZ";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ComputeNNZ$.class);
      }
   }

   public static class ComputeMax$ implements ComputeMetric, Product {
      public static final ComputeMax$ MODULE$ = new ComputeMax$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "ComputeMax";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof ComputeMax$;
      }

      public int hashCode() {
         return 1723302349;
      }

      public String toString() {
         return "ComputeMax";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ComputeMax$.class);
      }
   }

   public static class ComputeMin$ implements ComputeMetric, Product {
      public static final ComputeMin$ MODULE$ = new ComputeMin$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "ComputeMin";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof ComputeMin$;
      }

      public int hashCode() {
         return 1723302587;
      }

      public String toString() {
         return "ComputeMin";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ComputeMin$.class);
      }
   }

   public static class MetricsAggregate extends TypedImperativeAggregate implements ImplicitCastInputTypes, BinaryLike, Serializable {
      private DataType dataType;
      private final Seq requestedMetrics;
      private final Seq requestedComputeMetrics;
      private final Expression featuresExpr;
      private final Expression weightExpr;
      private final int mutableAggBufferOffset;
      private final int inputAggBufferOffset;
      private transient Seq children;
      private volatile boolean bitmap$0;
      private transient volatile boolean bitmap$trans$0;

      public final TreeNode mapChildren(final Function1 f) {
         return BinaryLike.mapChildren$(this, f);
      }

      public final TreeNode withNewChildrenInternal(final IndexedSeq newChildren) {
         return BinaryLike.withNewChildrenInternal$(this, newChildren);
      }

      public TypeCheckResult checkInputDataTypes() {
         return ExpectsInputTypes.checkInputDataTypes$(this);
      }

      private Seq children$lzycompute() {
         synchronized(this){}

         try {
            if (!this.bitmap$trans$0) {
               this.children = BinaryLike.children$(this);
               this.bitmap$trans$0 = true;
            }
         } catch (Throwable var3) {
            throw var3;
         }

         return this.children;
      }

      public final Seq children() {
         return !this.bitmap$trans$0 ? this.children$lzycompute() : this.children;
      }

      public Seq requestedMetrics() {
         return this.requestedMetrics;
      }

      public Seq requestedComputeMetrics() {
         return this.requestedComputeMetrics;
      }

      public Expression featuresExpr() {
         return this.featuresExpr;
      }

      public Expression weightExpr() {
         return this.weightExpr;
      }

      public int mutableAggBufferOffset() {
         return this.mutableAggBufferOffset;
      }

      public int inputAggBufferOffset() {
         return this.inputAggBufferOffset;
      }

      public Object eval(final SummarizerBuffer state) {
         Seq metrics = (Seq)this.requestedMetrics().map((x0$1) -> {
            if (SummaryBuilderImpl.Mean$.MODULE$.equals(x0$1)) {
               return SummaryBuilderImpl$.MODULE$.org$apache$spark$ml$stat$SummaryBuilderImpl$$vectorUDT().serialize(state.mean());
            } else if (SummaryBuilderImpl.Sum$.MODULE$.equals(x0$1)) {
               return SummaryBuilderImpl$.MODULE$.org$apache$spark$ml$stat$SummaryBuilderImpl$$vectorUDT().serialize(state.sum());
            } else if (SummaryBuilderImpl.Variance$.MODULE$.equals(x0$1)) {
               return SummaryBuilderImpl$.MODULE$.org$apache$spark$ml$stat$SummaryBuilderImpl$$vectorUDT().serialize(state.variance());
            } else if (SummaryBuilderImpl.Std$.MODULE$.equals(x0$1)) {
               return SummaryBuilderImpl$.MODULE$.org$apache$spark$ml$stat$SummaryBuilderImpl$$vectorUDT().serialize(state.std());
            } else if (SummaryBuilderImpl.Count$.MODULE$.equals(x0$1)) {
               return BoxesRunTime.boxToLong(state.count());
            } else if (SummaryBuilderImpl.NumNonZeros$.MODULE$.equals(x0$1)) {
               return SummaryBuilderImpl$.MODULE$.org$apache$spark$ml$stat$SummaryBuilderImpl$$vectorUDT().serialize(state.numNonzeros());
            } else if (SummaryBuilderImpl.Max$.MODULE$.equals(x0$1)) {
               return SummaryBuilderImpl$.MODULE$.org$apache$spark$ml$stat$SummaryBuilderImpl$$vectorUDT().serialize(state.max());
            } else if (SummaryBuilderImpl.Min$.MODULE$.equals(x0$1)) {
               return SummaryBuilderImpl$.MODULE$.org$apache$spark$ml$stat$SummaryBuilderImpl$$vectorUDT().serialize(state.min());
            } else if (SummaryBuilderImpl.NormL2$.MODULE$.equals(x0$1)) {
               return SummaryBuilderImpl$.MODULE$.org$apache$spark$ml$stat$SummaryBuilderImpl$$vectorUDT().serialize(state.normL2());
            } else if (SummaryBuilderImpl.NormL1$.MODULE$.equals(x0$1)) {
               return SummaryBuilderImpl$.MODULE$.org$apache$spark$ml$stat$SummaryBuilderImpl$$vectorUDT().serialize(state.normL1());
            } else {
               throw new MatchError(x0$1);
            }
         });
         return org.apache.spark.sql.catalyst.InternalRow..MODULE$.apply(metrics);
      }

      public Seq inputTypes() {
         VectorUDT var1 = SummaryBuilderImpl$.MODULE$.org$apache$spark$ml$stat$SummaryBuilderImpl$$vectorUDT();
         DoubleType var2 = org.apache.spark.sql.types.DoubleType..MODULE$;
         return scala.collection.immutable.Nil..MODULE$.$colon$colon(var2).$colon$colon(var1);
      }

      public Expression left() {
         return this.featuresExpr();
      }

      public Expression right() {
         return this.weightExpr();
      }

      public MetricsAggregate withNewChildrenInternal(final Expression newLeft, final Expression newRight) {
         Seq x$3 = this.copy$default$1();
         Seq x$4 = this.copy$default$2();
         int x$5 = this.copy$default$5();
         int x$6 = this.copy$default$6();
         return this.copy(x$3, x$4, newLeft, newRight, x$5, x$6);
      }

      public SummarizerBuffer update(final SummarizerBuffer state, final InternalRow row) {
         Vector features = SummaryBuilderImpl$.MODULE$.org$apache$spark$ml$stat$SummaryBuilderImpl$$vectorUDT().deserialize(this.featuresExpr().eval(row));
         double weight = BoxesRunTime.unboxToDouble(this.weightExpr().eval(row));
         state.add(features, weight);
         return state;
      }

      public SummarizerBuffer merge(final SummarizerBuffer state, final SummarizerBuffer other) {
         return state.merge(other);
      }

      public boolean nullable() {
         return false;
      }

      public SummarizerBuffer createAggregationBuffer() {
         return new SummarizerBuffer(this.requestedMetrics(), this.requestedComputeMetrics());
      }

      public byte[] serialize(final SummarizerBuffer state) {
         return org.apache.spark.util.Utils..MODULE$.serialize(state);
      }

      public SummarizerBuffer deserialize(final byte[] bytes) {
         return (SummarizerBuffer)org.apache.spark.util.Utils..MODULE$.deserialize(bytes);
      }

      public MetricsAggregate withNewMutableAggBufferOffset(final int newMutableAggBufferOffset) {
         Seq x$2 = this.copy$default$1();
         Seq x$3 = this.copy$default$2();
         Expression x$4 = this.copy$default$3();
         Expression x$5 = this.copy$default$4();
         int x$6 = this.copy$default$6();
         return this.copy(x$2, x$3, x$4, x$5, newMutableAggBufferOffset, x$6);
      }

      public MetricsAggregate withNewInputAggBufferOffset(final int newInputAggBufferOffset) {
         Seq x$2 = this.copy$default$1();
         Seq x$3 = this.copy$default$2();
         Expression x$4 = this.copy$default$3();
         Expression x$5 = this.copy$default$4();
         int x$6 = this.copy$default$5();
         return this.copy(x$2, x$3, x$4, x$5, x$6, newInputAggBufferOffset);
      }

      private DataType dataType$lzycompute() {
         synchronized(this){}

         try {
            if (!this.bitmap$0) {
               this.dataType = SummaryBuilderImpl$.MODULE$.structureForMetrics(this.requestedMetrics());
               this.bitmap$0 = true;
            }
         } catch (Throwable var3) {
            throw var3;
         }

         return this.dataType;
      }

      public DataType dataType() {
         return !this.bitmap$0 ? this.dataType$lzycompute() : this.dataType;
      }

      public String prettyName() {
         return "aggregate_metrics";
      }

      public MetricsAggregate copy(final Seq requestedMetrics, final Seq requestedComputeMetrics, final Expression featuresExpr, final Expression weightExpr, final int mutableAggBufferOffset, final int inputAggBufferOffset) {
         return new MetricsAggregate(requestedMetrics, requestedComputeMetrics, featuresExpr, weightExpr, mutableAggBufferOffset, inputAggBufferOffset);
      }

      public Seq copy$default$1() {
         return this.requestedMetrics();
      }

      public Seq copy$default$2() {
         return this.requestedComputeMetrics();
      }

      public Expression copy$default$3() {
         return this.featuresExpr();
      }

      public Expression copy$default$4() {
         return this.weightExpr();
      }

      public int copy$default$5() {
         return this.mutableAggBufferOffset();
      }

      public int copy$default$6() {
         return this.inputAggBufferOffset();
      }

      public String productPrefix() {
         return "MetricsAggregate";
      }

      public int productArity() {
         return 6;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.requestedMetrics();
            }
            case 1 -> {
               return this.requestedComputeMetrics();
            }
            case 2 -> {
               return this.featuresExpr();
            }
            case 3 -> {
               return this.weightExpr();
            }
            case 4 -> {
               return BoxesRunTime.boxToInteger(this.mutableAggBufferOffset());
            }
            case 5 -> {
               return BoxesRunTime.boxToInteger(this.inputAggBufferOffset());
            }
            default -> {
               return Statics.ioobe(x$1);
            }
         }
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof MetricsAggregate;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "requestedMetrics";
            }
            case 1 -> {
               return "requestedComputeMetrics";
            }
            case 2 -> {
               return "featuresExpr";
            }
            case 3 -> {
               return "weightExpr";
            }
            case 4 -> {
               return "mutableAggBufferOffset";
            }
            case 5 -> {
               return "inputAggBufferOffset";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public boolean equals(final Object x$1) {
         boolean var12;
         if (this != x$1) {
            label79: {
               if (x$1 instanceof MetricsAggregate) {
                  MetricsAggregate var4 = (MetricsAggregate)x$1;
                  if (this.mutableAggBufferOffset() == var4.mutableAggBufferOffset() && this.inputAggBufferOffset() == var4.inputAggBufferOffset()) {
                     label72: {
                        Seq var10000 = this.requestedMetrics();
                        Seq var5 = var4.requestedMetrics();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label72;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label72;
                        }

                        var10000 = this.requestedComputeMetrics();
                        Seq var6 = var4.requestedComputeMetrics();
                        if (var10000 == null) {
                           if (var6 != null) {
                              break label72;
                           }
                        } else if (!var10000.equals(var6)) {
                           break label72;
                        }

                        Expression var10 = this.featuresExpr();
                        Expression var7 = var4.featuresExpr();
                        if (var10 == null) {
                           if (var7 != null) {
                              break label72;
                           }
                        } else if (!var10.equals(var7)) {
                           break label72;
                        }

                        var10 = this.weightExpr();
                        Expression var8 = var4.weightExpr();
                        if (var10 == null) {
                           if (var8 != null) {
                              break label72;
                           }
                        } else if (!var10.equals(var8)) {
                           break label72;
                        }

                        if (var4.canEqual(this)) {
                           break label79;
                        }
                     }
                  }
               }

               var12 = false;
               return var12;
            }
         }

         var12 = true;
         return var12;
      }

      public MetricsAggregate(final Seq requestedMetrics, final Seq requestedComputeMetrics, final Expression featuresExpr, final Expression weightExpr, final int mutableAggBufferOffset, final int inputAggBufferOffset) {
         this.requestedMetrics = requestedMetrics;
         this.requestedComputeMetrics = requestedComputeMetrics;
         this.featuresExpr = featuresExpr;
         this.weightExpr = weightExpr;
         this.mutableAggBufferOffset = mutableAggBufferOffset;
         this.inputAggBufferOffset = inputAggBufferOffset;
         ExpectsInputTypes.$init$(this);
         BinaryLike.$init$(this);
      }

      public MetricsAggregate(final Tuple2 metrics, final Expression featuresExpr, final Expression weightExpr) {
         this((Seq)metrics._1(), (Seq)metrics._2(), featuresExpr, weightExpr, 0, 0);
      }

      public MetricsAggregate(final Expression requestedMetrics, final Expression featuresExpr, final Expression weightExpr) {
         this(SummaryBuilderImpl$.MODULE$.org$apache$spark$ml$stat$SummaryBuilderImpl$$extractRequestedMetrics(requestedMetrics), featuresExpr, weightExpr);
      }

      public MetricsAggregate(final Expression requestedMetrics, final Expression featuresExpr) {
         this((Expression)requestedMetrics, featuresExpr, org.apache.spark.sql.catalyst.expressions.Literal..MODULE$.apply(BoxesRunTime.boxToDouble((double)1.0F)));
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class MetricsAggregate$ extends AbstractFunction6 implements Serializable {
      public static final MetricsAggregate$ MODULE$ = new MetricsAggregate$();

      public final String toString() {
         return "MetricsAggregate";
      }

      public MetricsAggregate apply(final Seq requestedMetrics, final Seq requestedComputeMetrics, final Expression featuresExpr, final Expression weightExpr, final int mutableAggBufferOffset, final int inputAggBufferOffset) {
         return new MetricsAggregate(requestedMetrics, requestedComputeMetrics, featuresExpr, weightExpr, mutableAggBufferOffset, inputAggBufferOffset);
      }

      public Option unapply(final MetricsAggregate x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple6(x$0.requestedMetrics(), x$0.requestedComputeMetrics(), x$0.featuresExpr(), x$0.weightExpr(), BoxesRunTime.boxToInteger(x$0.mutableAggBufferOffset()), BoxesRunTime.boxToInteger(x$0.inputAggBufferOffset()))));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(MetricsAggregate$.class);
      }
   }

   public interface ComputeMetric extends Serializable {
   }

   public interface Metric extends Serializable {
   }
}

package scala.collection.convert;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NavigableMap;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Option$;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractIterable;
import scala.collection.AbstractIterator;
import scala.collection.Iterable;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
import scala.collection.Map;
import scala.collection.Seq;
import scala.collection.Set;
import scala.collection.SetOps;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.StrictOptimizedMapOps;
import scala.collection.StrictOptimizedSeqOps;
import scala.collection.StrictOptimizedSetOps;
import scala.collection.mutable.AbstractBuffer;
import scala.collection.mutable.ArrayBuffer$;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.HashMap$;
import scala.collection.mutable.HashSet$;
import scala.collection.mutable.MapOps;
import scala.jdk.CollectionConverters$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.ObjectRef;
import scala.util.ChainingOps$;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;
import scala.util.Try$;
import scala.util.package;
import scala.util.control.ControlThrowable;
import scala.util.control.NonFatal$;

@ScalaSignature(
   bytes = "\u0006\u0005!uuACAv\u0003[D\t!!=\u0002z\u001aQ\u0011Q`Aw\u0011\u0003\t\t0a@\t\u000f\t]\u0011\u0001\"\u0001\u0003\u001c\u00191!QD\u0001\u0001\u0005?A!Ba\u0017\u0004\u0005\u000b\u0007I\u0011\u0001B/\u0011)\u0011)g\u0001B\u0001B\u0003%!q\f\u0005\b\u0005/\u0019A\u0011\u0001B4\u0011\u001d\u0011yg\u0001C\u0001\u0005cBqA!\u001f\u0004\t\u0003\u0011Y\bC\u0004\u0003~\r!\tA!\u001d\t\u000f\t}4\u0001\"\u0001\u0003|!9!\u0011Q\u0002\u0005B\t\r\u0005b\u0002BC\u0007\u0011\u0005#q\u0011\u0005\b\u0005\u001b\u001bA\u0011\tBH\r\u0019\u0011\u0019+\u0001\u0001\u0003&\"Q!1\f\b\u0003\u0006\u0004%\tAa-\t\u0015\t\u0015dB!A!\u0002\u0013\u0011)\fC\u0004\u0003\u00189!\tAa.\t\u000f\t=d\u0002\"\u0001\u0003>\"9!\u0011\u0010\b\u0005\u0002\t}\u0006b\u0002BC\u001d\u0011\u0005#\u0011\u0019\u0005\b\u0005\u001bsA\u0011\tBH\r\u0019\u00119-\u0001\u0001\u0003J\"Q!1\f\f\u0003\u0006\u0004%\tAa5\t\u0015\t\u0015dC!A!\u0002\u0013\u0011)\u000eC\u0004\u0003\u0018Y!\tAa6\t\u000f\t=d\u0003\"\u0001\u0003>\"9!\u0011\u0010\f\u0005\u0002\tu\u0007b\u0002BC-\u0011\u0005#q\u001c\u0005\b\u0005\u001b3B\u0011\tBH\r%\u0011)/\u0001I\u0001\u0004\u0003\u00119\u000fC\u0004\u0003vz!\tAa>\t\u0013\tmcD1A\u0007\u0002\t}\bbBB\u0004=\u0011\u0005!q\u0012\u0005\b\u0007\u0013qB\u0011IB\u0006\u0011\u001d\u0019yA\bC!\u0005c2aa!\u0005\u0002\u0001\rM\u0001B\u0003B.I\t\u0015\r\u0011\"\u0001\u0004 !Q!Q\r\u0013\u0003\u0002\u0003\u0006Ia!\t\t\u000f\t]A\u0005\"\u0001\u0004$!9!Q\u0011\u0013\u0005B\r%\u0002b\u0002BGI\u0011\u0005#q\u0012\u0004\u0007\u0007_\t\u0001a!\r\t\u0015\tm#F!b\u0001\n\u0003\u0019I\u0005\u0003\u0006\u0003f)\u0012\t\u0011)A\u0005\u0007\u0017BqAa\u0006+\t\u0003\u0019y\u0005C\u0004\u0004\n)\"\ta!\u0016\t\u000f\re#\u0006\"\u0011\u0004\\!91q\u0002\u0016\u0005B\tu\u0006b\u0002BCU\u0011\u000531\u000e\u0005\b\u0005\u001bSC\u0011\tBH\r\u0019\u0019\t(\u0001\u0001\u0004t!Q!1L\u001a\u0003\u0006\u0004%\ta!!\t\u0015\t\u00154G!A!\u0002\u0013\u0019\u0019\tC\u0004\u0003\u0018M\"\ta!#\t\u000f\r%1\u0007\"\u0001\u0004\u0010\"91qA\u001a\u0005B\rM\u0005bBBKg\u0011\u000531\u0013\u0005\b\u0007\u001f\u0019D\u0011\tB_\u0011\u001d\u0019If\rC!\u00077BqA!\"4\t\u0003\u001a9\nC\u0004\u0003\u000eN\"\tEa$\u0007\r\ru\u0015\u0001ABP\u0011)\u0011YF\u0010BC\u0002\u0013\u00051q\u0016\u0005\u000b\u0005Kr$\u0011!Q\u0001\n\rE\u0006b\u0002B\f}\u0011\u00051q\u0017\u0005\b\u0007{sD\u0011AB`\r\u0019\u00199-\u0001\u0001\u0004J\"Q!1L\"\u0003\u0006\u0004%\ta!6\t\u0015\t\u00154I!A!\u0002\u0013\u00199\u000eC\u0004\u0003\u0018\r#\ta!8\t\u000f\ru6\t\"\u0001\u0004d\"91q]\"\u0005B\r%hABBz\u0003\u0001\u0019)\u0010\u0003\u0006\u0003\\%\u0013)\u0019!C\u0001\t\u0003A!B!\u001aJ\u0005\u0003\u0005\u000b\u0011\u0002C\u0002\u0011\u001d\u00119\"\u0013C\u0001\t\u0013Aqa!0J\t\u0003!y\u0001C\u0004\u0004h&#\t\u0005b\u0005\t\u000f\u0011e\u0011\n\"\u0011\u0005\u001c!9!\u0011Q%\u0005B\u0011}aA\u0002C\u0013\u0003\u0001!9\u0003\u0003\u0006\u0003\\E\u0013)\u0019!C\u0001\t\u0017B!B!\u001aR\u0005\u0003\u0005\u000b\u0011\u0002C'\u0011\u001d\u00119\"\u0015C\u0001\t'Bq\u0001\"\u0017R\t\u0003\u0019\u0019\nC\u0004\u0004\u0016F#\tea%\t\u000f\r=\u0011\u000b\"\u0011\u0003>\"91\u0011B)\u0005B\u0011m\u0003b\u0002C0#\u0012\u0005A\u0011\r\u0005\b\tK\nF\u0011\u0001C4\u0011\u001d!i'\u0015C\u0001\t_Bq\u0001\"\u001eR\t\u0003!9\bC\u0004\u0005|E#\t\u0001\" \t\u000f\u0011\u0015\u0015\u000b\"\u0001\u0005\b\"9!\u0011Q)\u0005\u0002\u0011U\u0005b\u0002CM#\u0012\u0005!q\u001f\u0005\b\t7\u000bF\u0011\tCO\u0011\u001d!y*\u0015C\u0001\tCCqA!!R\t\u0003!y\u000bC\u0004\u0004ZE#\tea\u0017\t\u000f\u0011]\u0016\u000b\"\u0011\u0005:\u001a1AqX\u0001\u0001\t\u0003D!Ba\u0017g\u0005\u0003\u0005\u000b\u0011\u0002Ch\u0011\u001d\u00119B\u001aC\u0001\t+Dq\u0001b7g\t\u0003\"i\u000eC\u0004\u0004\u0010\u0019$\tE!\u001d\t\u000f\r\u001da\r\"\u0001\u0003\u0010\"91\u0011\u00024\u0005\u0002\u0011\rhA\u0002Cu\u0003\u0001!Y\u000f\u0003\u0006\u0003\\5\u0014)\u0019!C\u0001\tkD!B!\u001an\u0005\u0003\u0005\u000b\u0011\u0002C|\u0011\u001d\u00119\"\u001cC\u0001\twDq\u0001\"\u0007n\t\u0003*\t\u0001C\u0004\u0003\u00026$\t%\"\u0002\t\u000f\u0011eU\u000e\"\u0011\u0003x\u001a1Q1B\u0001\u0001\u000b\u001bA!Ba\u0017u\u0005\u000b\u0007I\u0011AC\u0015\u0011)\u0011)\u0007\u001eB\u0001B\u0003%Q1\u0006\u0005\b\u0005/!H\u0011AC\u0018\u0011\u001d\u00199\u0001\u001eC!\u0007'Cqaa\u0004u\t\u0003\u0012i\fC\u0004\u0004\u0016R$\tea%\t\u000f\r%A\u000f\"\u0001\u00066!9A1\u001c;\u0005\u0002\u0015e\u0002b\u0002C;i\u0012\u0005QQ\b\u0005\b\to#H\u0011AC\"\u0011\u001d\u0011\t\t\u001eC!\u000b\u000fBq\u0001\"'u\t\u0003\u00129\u0010C\u0004\u0006LQ$\t%\"\u0014\t\u000f\u0011mE\u000f\"\u0011\u0006P!91\u0011\f;\u0005B\u0015E\u0003bBC-i\u0012\u0005S1\f\u0004\u0007\u000bS\n\u0001!b\u001b\t\u0017\tm\u00131\u0002B\u0001B\u0003%Q\u0011\u0011\u0005\t\u0005/\tY\u0001\"\u0001\u0006\b\"A1qAA\u0006\t\u0003\u0012y\t\u0003\u0005\u0004>\u0006-A\u0011ICG\u0011!)\u0019*a\u0003\u0005B\u0015U\u0005\u0002CCW\u0003\u0017!\t%b,\u0007\r\u0015U\u0016\u0001AC\\\u0011-\u0011Y&!\u0007\u0003\u0006\u0004%\t!\"2\t\u0017\t\u0015\u0014\u0011\u0004B\u0001B\u0003%Qq\u0019\u0005\t\u0005/\tI\u0002\"\u0001\u0006L\"AQ\u0011[A\r\t\u0003*\u0019\u000e\u0003\u0005\u0003\u0002\u0006eA\u0011ICo\u0011!!I*!\u0007\u0005B\t]haBCr\u0003\u0005\u0005QQ\u001d\u0005\t\u0005/\t9\u0003\"\u0001\u0007.\u001aIQq_\u0001\u0011\u0002\u0007\u0005Q\u0011 \u0005\t\u0005k\fY\u0003\"\u0001\u0003x\"A!1LA\u0016\r\u00031)\u0005\u0003\u0005\u0004\b\u0005-B\u0011IBJ\u0011!\u0019i,a\u000b\u0005\u0002\u0019-\u0003\u0002\u0003D+\u0003W!\tEb\u0016\t\u0011\u0011U\u00141\u0006C\u0001\rKB\u0001\u0002b.\u0002,\u0011\u0005aQ\u000e\u0005\t\u000b#\fY\u0003\"\u0011\u0007r!AAQMA\u0016\t\u000329\b\u0003\u0005\u0007~\u0005-B\u0011\tD@\u0011!\u0011\t)a\u000b\u0005B\u0019-\u0005\u0002CB\u0005\u0003W!\tAb$\t\u0011\u0019M\u00151\u0006C!\r+C\u0001\u0002\"'\u0002,\u0011\u0005#q\u001f\u0004\u0007\rg\u000b\u0001A\".\t\u0017\tm\u0013\u0011\nBC\u0002\u0013\u0005a1\u0019\u0005\f\u0005K\nIE!A!\u0002\u00131)\r\u0003\u0005\u0003\u0018\u0005%C\u0011\u0001Dd\u0011!\u0019y!!\u0013\u0005B\tu\u0006\u0002CBK\u0003\u0013\"\tea%\t\u0011\u0015-\u0013\u0011\nC!\r\u001b4aA\"5\u0002\u0001\u0019M\u0007\"\u0004B.\u0003/\u0012\t\u0011)A\u0005\r[\fY\u0002\u0003\u0005\u0003\u0018\u0005]C\u0011\u0001D{\u0011!1Y0a\u0016\u0005\u0002\u0019u\b\u0002\u0003D\u0000\u0003/\"\te\"\u0001\t\u0011\t\u0005\u0015q\u000bC!\u000f\u000fA\u0001b\"\u0004\u0002X\u0011\u0005sq\u0002\u0005\t\u000f\u001b\t9\u0006\"\u0011\b\u0016\u00191q1E\u0001\u0001\u000fKA1Ba\u0017\u0002h\t\u0015\r\u0011\"\u0001\b6!Y!QMA4\u0005\u0003\u0005\u000b\u0011BD\u001c\u0011!\u00119\"a\u001a\u0005\u0002\u001de\u0002\u0002CB_\u0003O\"\teb\u0010\t\u0011\u0019U\u0013q\rC!\u000f\u000bB\u0001ba\u0004\u0002h\u0011\u0005#Q\u0018\u0005\t\u0007+\u000b9\u0007\"\u0011\u0004\u0014\"AQ1JA4\t\u0003:i\u0005\u0003\u0005\u0007\u0000\u0006\u001dD\u0011AD(\u0011!\u0011\t)a\u001a\u0005\u0002\u001dU\u0003\u0002CD\u0007\u0003O\"\tab\u0017\t\u0011\u001d5\u0011q\rC\u0001\u000fCB\u0001b\"\u001c\u0002h\u0011\u0005sq\u000e\u0005\t\r{\n9\u0007\"\u0011\bv\u00191q\u0011Q\u0001\u0001\u000f\u0007C1Ba\u0017\u0002\u0006\n\u0015\r\u0011\"\u0001\b\u0016\"Y!QMAC\u0005\u0003\u0005\u000b\u0011BDL\u0011!\u00119\"!\"\u0005\u0002\u001de\u0005\u0002CB\u0004\u0003\u000b#\tAa$\t\u0011\r=\u0011Q\u0011C\u0001\u0005cB\u0001bb(\u0002\u0006\u0012\u0005q\u0011\u0015\u0005\t\u000fK\u000b)\t\"\u0001\b(\"A1QXAC\t\u00039Y\u000b\u0003\u0005\u0006R\u0006\u0015E\u0011ADX\u0011!\u0011\t)!\"\u0005B\u001dU\u0006\u0002\u0003BC\u0003\u000b#\te\"/\t\u0011\t5\u0015Q\u0011C!\u0005\u001f3aab0\u0002\u0001\u001d\u0005\u0007b\u0003B.\u0003?\u0013)\u0019!C\u0001\u000f\u001fD1B!\u001a\u0002 \n\u0005\t\u0015!\u0003\bR\"A!qCAP\t\u00039\u0019\u000e\u0003\u0005\u0004\b\u0005}E\u0011IBJ\u0011!\u0019y!a(\u0005B\tu\u0006\u0002CBK\u0003?#\tea%\t\u0011\ru\u0016q\u0014C\u0001\u000f3D\u0001\u0002\"\u001e\u0002 \u0012\u0005qq\u001c\u0005\t\to\u000by\n\"\u0001\bh\"AQ\u0011[AP\t\u0003:Y\u000f\u0003\u0005\u0005f\u0005}E\u0011IDy\u0011!\u0011\t)a(\u0005B\u001d]\b\u0002CB\u0005\u0003?#\tab?\t\u0011\u0011e\u0015q\u0014C!\u0005oD\u0001bb@\u0002 \u0012\u0005\u0003\u0012\u0001\u0004\u0007\u0011\u0017\t\u0001\u0001#\u0004\t\u0017\tm\u0013q\u0018B\u0001B\u0003%\u0001\u0012\u0007\u0005\t\u0005/\ty\f\"\u0001\t8!A1qAA`\t\u0003\u001a\u0019\n\u0003\u0005\u0004\u0010\u0005}F\u0011\tB_\u0011!\u0019)*a0\u0005B\rM\u0005\u0002CB_\u0003\u007f#\t\u0001#\u0010\t\u0011\u0011U\u0014q\u0018C\u0001\u0011\u0007B\u0001\u0002b.\u0002@\u0012\u0005\u0001\u0012\n\u0005\t\u000b#\fy\f\"\u0011\tN!AAQMA`\t\u0003B\u0019\u0006\u0003\u0005\u0003\u0002\u0006}F\u0011\tE-\u0011!\u0019I!a0\u0005\u0002!u\u0003\u0002\u0003CM\u0003\u007f#\tEa>\t\u0011\u0015-\u0013q\u0018C!\u0011CB\u0001\u0002c\u0019\u0002@\u0012\u0005\u0001R\r\u0005\t\u0011G\ny\f\"\u0001\tn!A\u0001ROA`\t\u0003A9\b\u0003\u0005\b\u0000\u0006}F\u0011\tE\u0001\u0011%A\t)\u0001b\u0001\n\u0013A\u0019\t\u0003\u0005\t\u0014\u0006\u0001\u000b\u0011\u0002EC\u0011%A)*AA\u0001\n\u0013A9*\u0001\fKCZ\f7i\u001c7mK\u000e$\u0018n\u001c8Xe\u0006\u0004\b/\u001a:t\u0015\u0011\ty/!=\u0002\u000f\r|gN^3si*!\u00111_A{\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0003\u0003o\fQa]2bY\u0006\u00042!a?\u0002\u001b\t\tiO\u0001\fKCZ\f7i\u001c7mK\u000e$\u0018n\u001c8Xe\u0006\u0004\b/\u001a:t'\u0015\t!\u0011\u0001B\u0005!\u0011\u0011\u0019A!\u0002\u000e\u0005\u0005U\u0018\u0002\u0002B\u0004\u0003k\u0014a!\u00118z%\u00164\u0007\u0003\u0002B\u0006\u0005#qAAa\u0001\u0003\u000e%!!qBA{\u0003\u001d\u0001\u0018mY6bO\u0016LAAa\u0005\u0003\u0016\ta1+\u001a:jC2L'0\u00192mK*!!qBA{\u0003\u0019a\u0014N\\5u}\r\u0001ACAA}\u0005=IE/\u001a:bi>\u0014xK]1qa\u0016\u0014X\u0003\u0002B\u0011\u0005\u0007\u001a\u0012b\u0001B\u0012\u0005g\u0011)F!\u0003\u0011\t\t\u0015\"qF\u0007\u0003\u0005OQAA!\u000b\u0003,\u0005!A.\u00198h\u0015\t\u0011i#\u0001\u0003kCZ\f\u0017\u0002\u0002B\u0019\u0005O\u0011aa\u00142kK\u000e$\bC\u0002B\u001b\u0005w\u0011y$\u0004\u0002\u00038)!!\u0011\bB\u0016\u0003\u0011)H/\u001b7\n\t\tu\"q\u0007\u0002\t\u0013R,'/\u0019;peB!!\u0011\tB\"\u0019\u0001!qA!\u0012\u0004\u0005\u0004\u00119EA\u0001B#\u0011\u0011IEa\u0014\u0011\t\t\r!1J\u0005\u0005\u0005\u001b\n)PA\u0004O_RD\u0017N\\4\u0011\t\t\r!\u0011K\u0005\u0005\u0005'\n)PA\u0002B]f\u0004bA!\u000e\u0003X\t}\u0012\u0002\u0002B-\u0005o\u00111\"\u00128v[\u0016\u0014\u0018\r^5p]\u0006QQO\u001c3fe2L\u0018N\\4\u0016\u0005\t}\u0003C\u0002B1\u0005G\u0012y$\u0004\u0002\u0002r&!!QHAy\u0003-)h\u000eZ3sYfLgn\u001a\u0011\u0015\t\t%$Q\u000e\t\u0006\u0005W\u001a!qH\u0007\u0002\u0003!9!1\f\u0004A\u0002\t}\u0013a\u00025bg:+\u0007\u0010\u001e\u000b\u0003\u0005g\u0002BAa\u0001\u0003v%!!qOA{\u0005\u001d\u0011un\u001c7fC:\fAA\\3yiR\u0011!qH\u0001\u0010Q\u0006\u001cXj\u001c:f\u000b2,W.\u001a8ug\u0006Ya.\u001a=u\u000b2,W.\u001a8u\u0003\u0019\u0011X-\\8wKR\u0011!\u0011J\u0001\u0007KF,\u0018\r\\:\u0015\t\tM$\u0011\u0012\u0005\b\u0005\u0017c\u0001\u0019\u0001B(\u0003\u0015yG\u000f[3s\u0003!A\u0017m\u001d5D_\u0012,GC\u0001BI!\u0011\u0011\u0019Aa%\n\t\tU\u0015Q\u001f\u0002\u0004\u0013:$\bfB\u0002\u0003\u001a\n}%\u0011\u0015\t\u0005\u0005\u0007\u0011Y*\u0003\u0003\u0003\u001e\u0006U(\u0001E*fe&\fGNV3sg&|g.V%E\u0003\u00151\u0018\r\\;f=\u0005\u0019!\u0001\u0005&Ji\u0016\u0014\u0018\r^8s/J\f\u0007\u000f]3s+\u0011\u00119K!-\u0014\u000b9\u0011IK!\u0003\u0011\r\t\u0005$1\u0016BX\u0013\u0011\u0011i+!=\u0003!\u0005\u00137\u000f\u001e:bGRLE/\u001a:bi>\u0014\b\u0003\u0002B!\u0005c#qA!\u0012\u000f\u0005\u0004\u00119%\u0006\u0002\u00036B1!Q\u0007B\u001e\u0005_#BA!/\u0003<B)!1\u000e\b\u00030\"9!1L\tA\u0002\tUVC\u0001B:)\t\u0011y\u000b\u0006\u0003\u0003t\t\r\u0007b\u0002BF)\u0001\u0007!q\n\u0015\b\u001d\te%q\u0014BQ\u0005MQUI\\;nKJ\fG/[8o/J\f\u0007\u000f]3s+\u0011\u0011YM!5\u0014\u000bY\u0011iM!\u0003\u0011\r\t\u0005$1\u0016Bh!\u0011\u0011\tE!5\u0005\u000f\t\u0015cC1\u0001\u0003HU\u0011!Q\u001b\t\u0007\u0005k\u00119Fa4\u0015\t\te'1\u001c\t\u0006\u0005W2\"q\u001a\u0005\b\u00057J\u0002\u0019\u0001Bk)\t\u0011y\r\u0006\u0003\u0003t\t\u0005\bb\u0002BF9\u0001\u0007!q\n\u0015\b-\te%q\u0014BQ\u0005QIE/\u001a:bE2,wK]1qa\u0016\u0014HK]1jiV!!\u0011\u001eBz'\rq\"1\u001e\t\u0007\u0005k\u0011iO!=\n\t\t=(q\u0007\u0002\u0013\u0003\n\u001cHO]1di\u000e{G\u000e\\3di&|g\u000e\u0005\u0003\u0003B\tMHa\u0002B#=\t\u0007!qI\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0005\te\b\u0003\u0002B\u0002\u0005wLAA!@\u0002v\n!QK\\5u+\t\u0019\t\u0001\u0005\u0004\u0003b\r\r!\u0011_\u0005\u0005\u0007\u000b\t\tP\u0001\u0005Ji\u0016\u0014\u0018M\u00197f\u0003\u0011\u0019\u0018N_3\u0002\u0011%$XM]1u_J$\"a!\u0004\u0011\u000b\t-4A!=\u0002\u000f%\u001cX)\u001c9us\ny\u0011\n^3sC\ndWm\u0016:baB,'/\u0006\u0003\u0004\u0016\rm1c\u0002\u0013\u0004\u0018\ru!\u0011\u0002\t\u0007\u0005k\u0011io!\u0007\u0011\t\t\u000531\u0004\u0003\b\u0005\u000b\"#\u0019\u0001B$!\u0015\u0011YGHB\r+\t\u0019\t\u0003\u0005\u0004\u0003b\r\r1\u0011\u0004\u000b\u0005\u0007K\u00199\u0003E\u0003\u0003l\u0011\u001aI\u0002C\u0004\u0003\\\u001d\u0002\ra!\t\u0015\t\tM41\u0006\u0005\b\u0005\u0017C\u0003\u0019\u0001B(Q\u001d!#\u0011\u0014BP\u0005C\u0013\u0001CS%uKJ\f'\r\\3Xe\u0006\u0004\b/\u001a:\u0016\t\rM2QH\n\bU\rU2q\bB\u0005!\u0019\u0011\tga\u000e\u0004<%!1\u0011HAy\u0005A\t%m\u001d;sC\u000e$\u0018\n^3sC\ndW\r\u0005\u0003\u0003B\ruBa\u0002B#U\t\u0007!q\t\t\u000b\u0005C\u001a\tea\u000f\u0004F\r\u001d\u0013\u0002BB\"\u0003c\u0014!d\u0015;sS\u000e$x\n\u001d;j[&TX\rZ%uKJ\f'\r\\3PaN\u0004BA!\u0019\u0004\u0004A1!\u0011MB\u0002\u0007w)\"aa\u0013\u0011\r\t\u00152QJB\u001e\u0013\u0011\u0019)Aa\n\u0015\t\rE31\u000b\t\u0006\u0005WR31\b\u0005\b\u00057j\u0003\u0019AB&+\t\u00199\u0006\u0005\u0004\u0003b\t\r41H\u0001\u0010SR,'/\u00192mK\u001a\u000b7\r^8ssV\u00111Q\f\b\u0005\u0007?\u001a)G\u0004\u0003\u0003b\r\u0005\u0014\u0002BB2\u0003c\fq!\\;uC\ndW-\u0003\u0003\u0004h\r%\u0014aC!se\u0006L()\u001e4gKJTAaa\u0019\u0002rR!!1OB7\u0011\u001d\u0011Y)\ra\u0001\u0005\u001fBsA\u000bBM\u0005?\u0013\tK\u0001\nK\u0007>dG.Z2uS>twK]1qa\u0016\u0014X\u0003BB;\u0007w\u001araMB<\u0007{\u0012I\u0001\u0005\u0004\u0003b\r]2\u0011\u0010\t\u0005\u0005\u0003\u001aY\bB\u0004\u0003FM\u0012\rAa\u0012\u0011\u0015\t\u00054\u0011IB=\u0007\u000b\u001ay\b\u0005\u0004\u0003b\r\r1\u0011P\u000b\u0003\u0007\u0007\u0003bA!\u000e\u0004\u0006\u000ee\u0014\u0002BBD\u0005o\u0011!bQ8mY\u0016\u001cG/[8o)\u0011\u0019Yi!$\u0011\u000b\t-4g!\u001f\t\u000f\tmc\u00071\u0001\u0004\u0004V\u00111\u0011\u0013\t\u0007\u0005C\u0012\u0019g!\u001f\u0016\u0005\tE\u0015!C6o_^t7+\u001b>f)\u0011\u0011\u0019h!'\t\u000f\t-E\b1\u0001\u0003P!:1G!'\u0003 \n\u0005&AC*fc^\u0013\u0018\r\u001d9feV!1\u0011UBV'\u001dq41UBW\u0005\u0013\u0001bA!\u000e\u0004&\u000e%\u0016\u0002BBT\u0005o\u0011A\"\u00112tiJ\f7\r\u001e'jgR\u0004BA!\u0011\u0004,\u00129!Q\t C\u0002\t\u001d\u0003#\u0002B6=\r%VCABY!\u0019\u0011\tga-\u0004*&!1QWAy\u0005\r\u0019V-\u001d\u000b\u0005\u0007s\u001bY\fE\u0003\u0003ly\u001aI\u000bC\u0004\u0003\\\u0005\u0003\ra!-\u0002\u0007\u001d,G\u000f\u0006\u0003\u0004*\u000e\u0005\u0007bBBb\u0005\u0002\u0007!\u0011S\u0001\u0002S\":aH!'\u0003 \n\u0005&!E'vi\u0006\u0014G.Z*fc^\u0013\u0018\r\u001d9feV!11ZBi'\u001d\u00195QZBj\u0005\u0013\u0001bA!\u000e\u0004&\u000e=\u0007\u0003\u0002B!\u0007#$qA!\u0012D\u0005\u0004\u00119\u0005E\u0003\u0003ly\u0019y-\u0006\u0002\u0004XB11\u0011\\Bn\u0007\u001fl!a!\u001b\n\t\rU6\u0011\u000e\u000b\u0005\u0007?\u001c\t\u000fE\u0003\u0003l\r\u001by\rC\u0004\u0003\\\u0019\u0003\raa6\u0015\t\r=7Q\u001d\u0005\b\u0007\u0007<\u0005\u0019\u0001BI\u0003\r\u0019X\r\u001e\u000b\u0007\u0007\u001f\u001cYo!<\t\u000f\r\r\u0007\n1\u0001\u0003\u0012\"91q\u001e%A\u0002\r=\u0017\u0001B3mK6Dsa\u0011BM\u0005?\u0013\tK\u0001\u000bNkR\f'\r\\3Ck\u001a4WM],sCB\u0004XM]\u000b\u0005\u0007o\u001cipE\u0004J\u0007s\u001cyP!\u0003\u0011\r\tU2QUB~!\u0011\u0011\te!@\u0005\u000f\t\u0015\u0013J1\u0001\u0003HA)!1\u000e\u0010\u0004|V\u0011A1\u0001\t\u0007\u00073$)aa?\n\t\u0011\u001d1\u0011\u000e\u0002\u0007\u0005V4g-\u001a:\u0015\t\u0011-AQ\u0002\t\u0006\u0005WJ51 \u0005\b\u00057b\u0005\u0019\u0001C\u0002)\u0011\u0019Y\u0010\"\u0005\t\u000f\r\rW\n1\u0001\u0003\u0012R111 C\u000b\t/Aqaa1O\u0001\u0004\u0011\t\nC\u0004\u0004p:\u0003\raa?\u0002\u0007\u0005$G\r\u0006\u0003\u0003t\u0011u\u0001bBBx\u001f\u0002\u000711 \u000b\u0005\u0007w$\t\u0003C\u0004\u0004DB\u0003\rA!%)\u000f%\u0013IJa(\u0003\"\na!\nT5ti^\u0013\u0018\r\u001d9feV!A\u0011\u0006C\u001a'-\tF1\u0006C\u001b\t\u007f!)E!\u0003\u0011\r\reGQ\u0006C\u0019\u0013\u0011!yc!\u001b\u0003\u001d\u0005\u00137\u000f\u001e:bGR\u0014UO\u001a4feB!!\u0011\tC\u001a\t\u001d\u0011)%\u0015b\u0001\u0005\u000f\u0002\"B!\u0019\u00058\u0011EB1\bC\u001f\u0013\u0011!I$!=\u0003\rM+\u0017o\u00149t!\u0011\u0019I\u000e\"\u0002\u0011\r\reGQ\u0001C\u0019!)\u0011\t\u0007\"\u0011\u00052\u0011mBQH\u0005\u0005\t\u0007\n\tPA\u000bTiJL7\r^(qi&l\u0017N_3e'\u0016\fx\n]:\u0011\u0011\t\u0005Dq\tC\u0019\twIA\u0001\"\u0013\u0002r\n9\u0012\n^3sC\ndWMR1di>\u0014\u0018\u0010R3gCVdGo]\u000b\u0003\t\u001b\u0002bA!\u000e\u0005P\u0011E\u0012\u0002\u0002C)\u0005o\u0011A\u0001T5tiR!AQ\u000bC,!\u0015\u0011Y'\u0015C\u0019\u0011\u001d\u0011Y\u0006\u0016a\u0001\t\u001b\na\u0001\\3oORDWC\u0001C/!\u0019\u0011\tGa\u0019\u00052\u0005)\u0011\r\u001d9msR!A\u0011\u0007C2\u0011\u001d\u0019\u0019-\u0017a\u0001\u0005#\u000ba!\u001e9eCR,GC\u0002B}\tS\"Y\u0007C\u0004\u0004Dj\u0003\rA!%\t\u000f\r=(\f1\u0001\u00052\u00059\u0001O]3qK:$G\u0003\u0002C9\tgj\u0011!\u0015\u0005\b\u0007_\\\u0006\u0019\u0001C\u0019\u0003\u0019\tG\rZ(oKR!A\u0011\u000fC=\u0011\u001d\u0019y\u000f\u0018a\u0001\tc\ta!\u001b8tKJ$HC\u0002B}\t\u007f\"\u0019\tC\u0004\u0005\u0002v\u0003\rA!%\u0002\u0007%$\u0007\u0010C\u0004\u0004pv\u0003\r\u0001\"\r\u0002\u0013%t7/\u001a:u\u00032dGC\u0002B}\t\u0013#Y\tC\u0004\u0004Dz\u0003\rA!%\t\u000f\u00115e\f1\u0001\u0005\u0010\u0006)Q\r\\3ngB1!\u0011\rCI\tcIA\u0001b%\u0002r\na\u0011\n^3sC\ndWm\u00148dKR!A\u0011\u0007CL\u0011\u001d\u0019\u0019m\u0018a\u0001\u0005#\u000bQa\u00197fCJ\fQa\u00197p]\u0016$\"\u0001\"\u0016\u0002\u0019A\fGo\u00195J]Bc\u0017mY3\u0015\u0011\u0011ED1\u0015CT\tWCq\u0001\"*c\u0001\u0004\u0011\t*\u0001\u0003ge>l\u0007b\u0002CUE\u0002\u0007AqR\u0001\u0006a\u0006$8\r\u001b\u0005\b\t[\u0013\u0007\u0019\u0001BI\u0003!\u0011X\r\u001d7bG\u0016$GC\u0002B}\tc#\u0019\fC\u0004\u0005&\u000e\u0004\rA!%\t\u000f\u0011U6\r1\u0001\u0003\u0012\u0006\ta.A\u0006tk\n$(/Y2u\u001f:,G\u0003\u0002C9\twCqaa<f\u0001\u0004!\t\u0004K\u0004R\u00053\u0013yJ!)\u0003\u0015M+Go\u0016:baB,'/\u0006\u0003\u0005D\u001257#\u00024\u0005F\n%\u0001C\u0002B\u001b\t\u000f$Y-\u0003\u0003\u0005J\n]\"aC!cgR\u0014\u0018m\u0019;TKR\u0004BA!\u0011\u0005N\u00129!Q\t4C\u0002\t\u001d\u0003C\u0002B1\t#$Y-\u0003\u0003\u0005T\u0006E(aA*fiR!Aq\u001bCm!\u0015\u0011YG\u001aCf\u0011\u001d\u0011Y\u0006\u001ba\u0001\t\u001f\f\u0001bY8oi\u0006Lgn\u001d\u000b\u0005\u0005g\"y\u000eC\u0004\u0005b&\u0004\rAa\t\u0002\u0003=$\"\u0001\":\u0011\r\tU\"1\bCfQ\u001d1'\u0011\u0014BP\u0005C\u0013\u0011#T;uC\ndWmU3u/J\f\u0007\u000f]3s+\u0011!i\u000fb=\u0014\u000b5$yO!\u0003\u0011\u000b\t-d\r\"=\u0011\t\t\u0005C1\u001f\u0003\b\u0005\u000bj'\u0019\u0001B$+\t!9\u0010\u0005\u0004\u0004Z\u0012eH\u0011_\u0005\u0005\t'\u001cI\u0007\u0006\u0003\u0005~\u0012}\b#\u0002B6[\u0012E\bb\u0002B.a\u0002\u0007Aq\u001f\u000b\u0005\u0005g*\u0019\u0001C\u0004\u0004pF\u0004\r\u0001\"=\u0015\t\tMTq\u0001\u0005\b\u0007_\u0014\b\u0019\u0001B\u0001Q\u001di'\u0011\u0014BP\u0005C\u00131BS*fi^\u0013\u0018\r\u001d9feV!QqBC\f'%!X\u0011CC\r\u000bG\u0011I\u0001\u0005\u0004\u0004Z\u0016MQQC\u0005\u0005\t\u0013\u001cI\u0007\u0005\u0003\u0003B\u0015]Aa\u0002B#i\n\u0007!q\t\t\u000b\u00073,Y\"\"\u0006\u0006 \u0015\u0005\u0012\u0002BC\u000f\u0007S\u0012aaU3u\u001fB\u001c\b\u0003BBm\ts\u0004ba!7\u0005z\u0016U\u0001C\u0003B1\u000bK))\"b\b\u0006\"%!QqEAy\u0005U\u0019FO]5di>\u0003H/[7ju\u0016$7+\u001a;PaN,\"!b\u000b\u0011\r\tURQFC\u000b\u0013\u0011!\u0019Na\u000e\u0015\t\u0015ER1\u0007\t\u0006\u0005W\"XQ\u0003\u0005\b\u00057:\b\u0019AC\u0016+\t)9\u0004\u0005\u0004\u0003b\t\rTQ\u0003\u000b\u0005\u0005g*Y\u0004C\u0004\u0004pr\u0004\r!\"\u0006\u0015\t\u0015}R\u0011I\u0007\u0002i\"91q^?A\u0002\u0015UA\u0003BC \u000b\u000bBqaa<\u007f\u0001\u0004))\u0002\u0006\u0003\u0003t\u0015%\u0003bBBx\u007f\u0002\u0007QQC\u0001\u0006K6\u0004H/_\u000b\u0003\u000bC!\"!\"\t\u0016\u0005\u0015M\u0003C\u0002B1\u000b+*y\"\u0003\u0003\u0006X\u0005E(aD%uKJ\f'\r\\3GC\u000e$xN]=\u0002\u001b\u0019LG\u000e^3s\u0013:\u0004F.Y2f)\u0011)y$\"\u0018\t\u0011\u0015}\u0013\u0011\u0002a\u0001\u000bC\n\u0011\u0001\u001d\t\t\u0005\u0007)\u0019'\"\u0006\u0003t%!QQMA{\u0005%1UO\\2uS>t\u0017\u0007K\u0004u\u00053\u0013yJ!)\u0003\u00155\u000b\u0007o\u0016:baB,'/\u0006\u0004\u0006n\u0015]TQP\n\u0007\u0003\u0017)yG!\u0003\u0011\u0011\tUR\u0011OC;\u000bwJA!b\u001d\u00038\tY\u0011IY:ue\u0006\u001cG/T1q!\u0011\u0011\t%b\u001e\u0005\u0011\u0015e\u00141\u0002b\u0001\u0005\u000f\u0012\u0011a\u0013\t\u0005\u0005\u0003*i\b\u0002\u0005\u0006\u0000\u0005-!\u0019\u0001B$\u0005\u00051\u0006\u0003\u0003B1\u000b\u0007+)(b\u001f\n\t\u0015\u0015\u0015\u0011\u001f\u0002\u0004\u001b\u0006\u0004H\u0003BCE\u000b\u0017\u0003\u0002Ba\u001b\u0002\f\u0015UT1\u0010\u0005\t\u00057\ny\u00011\u0001\u0006\u0002R!Q1PCH\u0011!)\t*a\u0005A\u0002\t\u0005\u0011aA6fs\u0006AQM\u001c;ssN+G\u000f\u0006\u0002\u0006\u0018B1!QGC\u0017\u000b3\u0003\u0002\"b'\u0006(\u0016UT1\u0010\b\u0005\u000b;+\u0019K\u0004\u0003\u0006 \u0016\u0005VB\u0001B\u0016\u0013\u0011\u0011IDa\u000b\n\t\u0015\u0015&qG\u0001\u0004\u001b\u0006\u0004\u0018\u0002BCU\u000bW\u0013Q!\u00128uefTA!\"*\u00038\u0005Y1m\u001c8uC&t7oS3z)\u0011\u0011\u0019(\"-\t\u0011\u0015E\u0015q\u0003a\u0001\u0005\u0003A\u0003\"a\u0003\u0003\u001a\n}%\u0011\u0015\u0002\u0012\u001bV$\u0018M\u00197f\u001b\u0006\u0004xK]1qa\u0016\u0014XCBC]\u000b\u007f+\u0019m\u0005\u0003\u0002\u001a\u0015m\u0006\u0003\u0003B6\u0003\u0017)i,\"1\u0011\t\t\u0005Sq\u0018\u0003\t\u000bs\nIB1\u0001\u0003HA!!\u0011ICb\t!)y(!\u0007C\u0002\t\u001dSCACd!!\u0019I.\"3\u0006>\u0016\u0005\u0017\u0002BCC\u0007S\"B!\"4\u0006PBA!1NA\r\u000b{+\t\r\u0003\u0005\u0003\\\u0005}\u0001\u0019ACd\u0003\r\u0001X\u000f\u001e\u000b\u0007\u000b\u0003,).\"7\t\u0011\u0015]\u0017\u0011\u0005a\u0001\u000b{\u000b\u0011a\u001b\u0005\t\u000b7\f\t\u00031\u0001\u0006B\u0006\ta\u000f\u0006\u0003\u0006B\u0016}\u0007\u0002CCl\u0003G\u0001\rA!\u0001)\u0011\u0005e!\u0011\u0014BP\u0005C\u00131#\u00112tiJ\f7\r\u001e&NCB<&/\u00199qKJ,b!b:\u0006p\u0016M8\u0003CA\u0014\u000bS,)P!\u0003\u0011\u0011\reW1^Cw\u000bcLA!b\u001d\u0004jA!!\u0011ICx\t!)I(a\nC\u0002\t\u001d\u0003\u0003\u0002B!\u000bg$\u0001\"b \u0002(\t\u0007!q\t\t\r\u0005W\nY#\"<\u0006r\u001a%f1\u0016\u0002\u0010\u00156\u000b\u0007o\u0016:baB,'\u000fT5lKVQQ1 D\u0003\r\u00131iA\"\f\u0014\u0015\u0005-\"\u0011AC\u007f\rg1I\u0004\u0005\u0007\u0004Z\u0016}h1\u0001D\u0004\r\u00171Y#\u0003\u0003\u0007\u0002\r%$AB'ba>\u00038\u000f\u0005\u0003\u0003B\u0019\u0015A\u0001CC=\u0003W\u0011\rAa\u0012\u0011\t\t\u0005c\u0011\u0002\u0003\t\u000b\u007f\nYC1\u0001\u0003HA!!\u0011\tD\u0007\t%1y!a\u000b\u0005\u0006\u00041\tB\u0001\u0002D\u0007V1a1\u0003D\u000e\rC\tBA!\u0013\u0007\u0016A\"aq\u0003D\u0014!1\u0019I.b@\u0007\u001a\u0019}a1\u0002D\u0013!\u0011\u0011\tEb\u0007\u0005\u0011\u0019uaQ\u0002b\u0001\u0005\u000f\u0012\u0011\u0001\u0017\t\u0005\u0005\u00032\t\u0003\u0002\u0005\u0007$\u00195!\u0019\u0001B$\u0005\u0005I\u0006\u0003\u0002B!\rO!AB\"\u000b\u0007\u000e\u0005\u0005\t\u0011!B\u0001\u0005\u000f\u00121a\u0018\u00132!\u0011\u0011\tE\"\f\u0005\u0013\u0019=\u00121\u0006CC\u0002\u0019E\"!A\"\u0012\t\t%SQ \t\r\u0005C2)Db\u0001\u0007\b\u0019-a1F\u0005\u0005\ro\t\tPA\u000bTiJL7\r^(qi&l\u0017N_3e\u001b\u0006\u0004x\n]:\u0011\u0015\t\u00054\u0011\tD\u001e\r\u00032Y\u0003\u0005\u0005\u0003\u0004\u0019ub1\u0001D\u0004\u0013\u00111y$!>\u0003\rQ+\b\u000f\\33!\u0011\u0019INb\u0011\n\t\r\u00151\u0011N\u000b\u0003\r\u000f\u0002\u0002B!\u000e\u0007J\u0019\raqA\u0005\u0005\u000b\u000b\u00139\u0004\u0006\u0003\u0007N\u0019M\u0003C\u0002B\u0002\r\u001f29!\u0003\u0003\u0007R\u0005U(AB(qi&|g\u000e\u0003\u0005\u0006X\u0006M\u0002\u0019\u0001D\u0002\u0003=9W\r^(s\u000b2\u001cX-\u00169eCR,GC\u0002D\u0004\r32Y\u0006\u0003\u0005\u0006\u0012\u0006U\u0002\u0019\u0001D\u0002\u0011%1i&!\u000e\u0005\u0002\u00041y&\u0001\u0002paB1!1\u0001D1\r\u000fIAAb\u0019\u0002v\nAAHY=oC6,g\b\u0006\u0003\u0007h\u0019%TBAA\u0016\u0011!1Y'a\u000eA\u0002\u0019m\u0012AA6w)\u001119Gb\u001c\t\u0011\u0015E\u0015\u0011\ba\u0001\r\u0007!bA\"\u0014\u0007t\u0019U\u0004\u0002CCl\u0003w\u0001\rAb\u0001\t\u0011\u0015m\u00171\ba\u0001\r\u000f!bA!?\u0007z\u0019m\u0004\u0002CCl\u0003{\u0001\rAb\u0001\t\u0011\u0015m\u0017Q\ba\u0001\r\u000f\t!\"\u001e9eCR,w+\u001b;i)\u00111\tI\"#\u0015\t\u00195c1\u0011\u0005\t\r\u000b\u000by\u00041\u0001\u0007\b\u0006\t\"/Z7baBLgn\u001a$v]\u000e$\u0018n\u001c8\u0011\u0011\t\rQ1\rD'\r\u001bB\u0001\"\"%\u0002@\u0001\u0007a1\u0001\u000b\u0005\r\u001b2i\t\u0003\u0005\u0006X\u0006\u0005\u0003\u0019\u0001D\u0002+\t1\t\n\u0005\u0004\u0003b\t\rd1H\u0001\rM>\u0014X-Y2i\u000b:$(/_\u000b\u0005\r/3)\u000b\u0006\u0003\u0003z\u001ae\u0005\u0002\u0003DN\u0003\u000b\u0002\rA\"(\u0002\u0003\u0019\u0004\"Ba\u0001\u0007 \u001a\raq\u0001DR\u0013\u00111\t+!>\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004\u0003\u0002B!\rK#\u0001Bb*\u0002F\t\u0007!q\t\u0002\u0002+B!1\u0011\\Ce!!\u0019I.\"3\u0006n\u0016EHC\u0001DX!!\u0011Y'a\n\u0006n\u0016E\b\u0006CA\u0014\u00053\u0013yJ!)\u0003\u0017)k\u0015\r],sCB\u0004XM]\u000b\u0007\ro3iL\"1\u0014\r\u0005%c\u0011\u0018B\u0005!!\u0011Y'a\n\u0007<\u001a}\u0006\u0003\u0002B!\r{#\u0001\"\"\u001f\u0002J\t\u0007!q\t\t\u0005\u0005\u00032\t\r\u0002\u0005\u0006\u0000\u0005%#\u0019\u0001B$+\t1)\r\u0005\u0005\u00036\u0019%c1\u0018D`)\u00111IMb3\u0011\u0011\t-\u0014\u0011\nD^\r\u007fC\u0001Ba\u0017\u0002P\u0001\u0007aQY\u000b\u0003\r\u0013D\u0003\"!\u0013\u0003\u001a\n}%\u0011\u0015\u0002\u0015\u0007>t7-\u001e:sK:$X*\u00199Xe\u0006\u0004\b/\u001a:\u0016\r\u0019Ug1\u001cDp'\u0019\t9Fb6\u0007bBA!1NA\r\r34i\u000e\u0005\u0003\u0003B\u0019mG\u0001CC=\u0003/\u0012\rAa\u0012\u0011\t\t\u0005cq\u001c\u0003\t\u000b\u007f\n9F1\u0001\u0003HAAa1\u001dDu\r34i.\u0004\u0002\u0007f*!aq\u001dB\u001c\u0003)\u0019wN\\2veJ,g\u000e^\u0005\u0005\rW4)OA\u0007D_:\u001cWO\u001d:f]Rl\u0015\r\u001d\t\t\r_4\u0019P\"7\u0007^6\u0011a\u0011\u001f\u0006\u0005\rO\f\t0\u0003\u0003\u0006\u0006\u001aEH\u0003\u0002D|\rs\u0004\u0002Ba\u001b\u0002X\u0019egQ\u001c\u0005\t\u00057\nY\u00061\u0001\u0007n\u00069RO\u001c3fe2L\u0018N\\4D_:\u001cWO\u001d:f]Rl\u0015\r]\u000b\u0003\r[\f1\u0002];u\u0013\u001a\f%m]3oiR1aQ\\D\u0002\u000f\u000bA\u0001\"b6\u0002`\u0001\u0007a\u0011\u001c\u0005\t\u000b7\fy\u00061\u0001\u0007^R1!1OD\u0005\u000f\u0017A\u0001\"b6\u0002b\u0001\u0007!\u0011\u0001\u0005\t\u000b7\f\t\u00071\u0001\u0003\u0002\u00059!/\u001a9mC\u000e,GC\u0002Do\u000f#9\u0019\u0002\u0003\u0005\u0006X\u0006\r\u0004\u0019\u0001Dm\u0011!)Y.a\u0019A\u0002\u0019uG\u0003\u0003B:\u000f/9Ib\"\b\t\u0011\u0015]\u0017Q\ra\u0001\r3D\u0001bb\u0007\u0002f\u0001\u0007aQ\\\u0001\u0007_2$g/\u00197\t\u0011\u001d}\u0011Q\ra\u0001\r;\faA\\3xm\u0006d\u0007\u0006CA,\u00053\u0013yJ!)\u0003+)\u001buN\\2veJ,g\u000e^'ba^\u0013\u0018\r\u001d9feV1qqED\u0017\u000fc\u0019b!a\u001a\b*\u001dM\u0002\u0003\u0003B6\u0003O9Ycb\f\u0011\t\t\u0005sQ\u0006\u0003\t\u000bs\n9G1\u0001\u0003HA!!\u0011ID\u0019\t!)y(a\u001aC\u0002\t\u001d\u0003\u0003\u0003Dx\rg<Ycb\f\u0016\u0005\u001d]\u0002\u0003\u0003Dr\rS<Ycb\f\u0015\t\u001dmrQ\b\t\t\u0005W\n9gb\u000b\b0!A!1LA7\u0001\u000499\u0004\u0006\u0003\bB\u001d\r\u0003C\u0002B\u0002\r\u001f:y\u0003\u0003\u0005\u0006X\u0006=\u0004\u0019AD\u0016)\u00199ycb\u0012\bJ!AQ\u0011SA9\u0001\u00049Y\u0003C\u0005\u0007^\u0005ED\u00111\u0001\bLA1!1\u0001D1\u000f_)\"ab\u000f\u0015\r\u001d\u0005s\u0011KD*\u0011!)9.!\u001fA\u0002\u001d-\u0002\u0002CCn\u0003s\u0002\rab\f\u0015\r\tMtqKD-\u0011!)9.a\u001fA\u0002\u001d-\u0002\u0002CCn\u0003w\u0002\rab\f\u0015\r\u001d\u0005sQLD0\u0011!)9.! A\u0002\u001d-\u0002\u0002CCn\u0003{\u0002\rab\f\u0015\u0011\tMt1MD3\u000fSB\u0001\"b6\u0002\u0000\u0001\u0007q1\u0006\u0005\t\u000fO\ny\b1\u0001\b0\u0005Aq\u000e\u001c3wC2,X\r\u0003\u0005\bl\u0005}\u0004\u0019AD\u0018\u0003!qWm\u001e<bYV,\u0017A\u00037bgR|\u0005\u000f^5p]V\u0011q\u0011\u000f\t\u0007\u0005\u00071yeb\u001d\u0011\u0011\t\raQHD\u0016\u000f_!Bab\u001e\b~Q!q\u0011ID=\u0011!1))a!A\u0002\u001dm\u0004\u0003\u0003B\u0002\u000bG:\te\"\u0011\t\u0011\u0015E\u00151\u0011a\u0001\u000fWA\u0003\"a\u001a\u0003\u001a\n}%\u0011\u0015\u0002\u0012\t&\u001cG/[8oCJLxK]1qa\u0016\u0014XCBDC\u000f\u001f;\u0019j\u0005\u0004\u0002\u0006\u001e\u001d%\u0011\u0002\t\t\u0005k9Ii\"$\b\u0012&!q1\u0012B\u001c\u0005)!\u0015n\u0019;j_:\f'/\u001f\t\u0005\u0005\u0003:y\t\u0002\u0005\u0006z\u0005\u0015%\u0019\u0001B$!\u0011\u0011\teb%\u0005\u0011\u0015}\u0014Q\u0011b\u0001\u0005\u000f*\"ab&\u0011\u0011\reW\u0011ZDG\u000f##Bab'\b\u001eBA!1NAC\u000f\u001b;\t\n\u0003\u0005\u0003\\\u0005-\u0005\u0019ADL\u0003\u0011YW-_:\u0015\u0005\u001d\r\u0006C\u0002B\u001b\u0005/:i)\u0001\u0005fY\u0016lWM\u001c;t)\t9I\u000b\u0005\u0004\u00036\t]s\u0011\u0013\u000b\u0005\u000f#;i\u000b\u0003\u0005\u0006\u0012\u0006U\u0005\u0019\u0001B\u0001)\u00199\tj\"-\b4\"AQ\u0011SAL\u0001\u00049i\t\u0003\u0005\u0003 \u0006]\u0005\u0019ADI)\u00119\tjb.\t\u0011\u0015E\u0015\u0011\u0014a\u0001\u0005\u0003!BAa\u001d\b<\"A!1RAN\u0001\u0004\u0011y\u0005\u000b\u0005\u0002\u0006\ne%q\u0014BQ\u0005IQE)[2uS>t\u0017M]=Xe\u0006\u0004\b/\u001a:\u0016\r\u001d\rw\u0011ZDg'\u0019\tyj\"2\u0003\nAA1\u0011\\Cv\u000f\u000f<Y\r\u0005\u0003\u0003B\u001d%G\u0001CC=\u0003?\u0013\rAa\u0012\u0011\t\t\u0005sQ\u001a\u0003\t\u000b\u007f\nyJ1\u0001\u0003HU\u0011q\u0011\u001b\t\t\u0005k9Iib2\bLR!qQ[Dl!!\u0011Y'a(\bH\u001e-\u0007\u0002\u0003B.\u0003K\u0003\ra\"5\u0015\t\u001dmwQ\u001c\t\u0007\u0005\u00071yeb3\t\u0011\u0015]\u0017Q\u0016a\u0001\u000f\u000f$Ba\"9\bd6\u0011\u0011q\u0014\u0005\t\rW\ny\u000b1\u0001\bfBA!1\u0001D\u001f\u000f\u000f<Y\r\u0006\u0003\bb\u001e%\b\u0002CCI\u0003c\u0003\rab2\u0015\r\u001dmwQ^Dx\u0011!)9.a-A\u0002\u001d\u001d\u0007\u0002CCn\u0003g\u0003\rab3\u0015\r\tex1_D{\u0011!)9.!.A\u0002\u001d\u001d\u0007\u0002CCn\u0003k\u0003\rab3\u0015\t\u001dmw\u0011 \u0005\t\u000b/\f9\f1\u0001\bHV\u0011qQ \t\u0007\u0005C\u0012\u0019g\":\u0002\u00155\f\u0007OR1di>\u0014\u00180\u0006\u0002\t\u00049!1q\fE\u0003\u0013\u0011A9a!\u001b\u0002\u000f!\u000b7\u000f['ba\"B\u0011q\u0014BM\u0005?\u0013\tK\u0001\nK!J|\u0007/\u001a:uS\u0016\u001cxK]1qa\u0016\u00148\u0003DA`\u0011\u001fA9\u0003c\u000b\t.\t%\u0001\u0003CBm\u000bWD\t\u0002#\u0005\u0011\t!M\u0001\u0012\u0005\b\u0005\u0011+Ai\u0002\u0005\u0003\t\u0018\u0005UXB\u0001E\r\u0015\u0011AYB!\u0007\u0002\rq\u0012xn\u001c;?\u0013\u0011Ay\"!>\u0002\rA\u0013X\rZ3g\u0013\u0011A\u0019\u0003#\n\u0003\rM#(/\u001b8h\u0015\u0011Ay\"!>\u0011\u0019\reWq E\t\u0011#1I\u000b#\u000b\u0011\u0011\reW\u0011\u001aE\t\u0011#\u0001BB!\u0019\u00076!E\u0001\u0012\u0003DU\u0011S\u0001\"B!\u0019\u0004B!=b\u0011\tE\u0015!!\u0011\u0019A\"\u0010\t\u0012!E\u0001\u0003\u0002B\u001b\u0011gIA\u0001#\u000e\u00038\tQ\u0001K]8qKJ$\u0018.Z:\u0015\t!e\u00022\b\t\u0005\u0005W\ny\f\u0003\u0005\u0003\\\u0005\r\u0007\u0019\u0001E\u0019)\u0011Ay\u0004#\u0011\u0011\r\t\raq\nE\t\u0011!)9.a3A\u0002!EA\u0003\u0002E#\u0011\u000fj!!a0\t\u0011\u0019-\u0014Q\u001aa\u0001\u0011_!B\u0001#\u0012\tL!AQ\u0011SAh\u0001\u0004A\t\u0002\u0006\u0004\t@!=\u0003\u0012\u000b\u0005\t\u000b/\f\t\u000e1\u0001\t\u0012!AQ1\\Ai\u0001\u0004A\t\u0002\u0006\u0004\u0003z\"U\u0003r\u000b\u0005\t\u000b/\f\u0019\u000e1\u0001\t\u0012!AQ1\\Aj\u0001\u0004A\t\u0002\u0006\u0003\t@!m\u0003\u0002CCl\u0003+\u0004\r\u0001#\u0005\u0016\u0005!}\u0003C\u0002B1\u0005GBy#\u0006\u0002\t:\u0005Yq-\u001a;Qe>\u0004XM\u001d;z)\u0011A9\u0007c\u001b\u0011\t\t\u0015\u0002\u0012N\u0005\u0005\u0011G\u00119\u0003\u0003\u0005\u0006\u0012\u0006u\u0007\u0019\u0001E\t)\u0019A9\u0007c\u001c\tr!AQ\u0011SAp\u0001\u0004A\t\u0002\u0003\u0005\tt\u0005}\u0007\u0019\u0001E\t\u00031!WMZ1vYR4\u0016\r\\;f\u0003-\u0019X\r\u001e)s_B,'\u000f^=\u0015\r!e\u00042\u0010E?!\u0011\u0011)Ca\f\t\u0011\u0015E\u0015\u0011\u001da\u0001\u0011#A\u0001Ba(\u0002b\u0002\u0007\u0001\u0012\u0003\u0015\t\u0003\u007f\u0013IJa(\u0003\"\u00069\u0001+\u001e;Ok2dWC\u0001EC!\u0011A9\tc$\u000e\u0005!%%\u0002\u0002EF\u0011\u001b\u000bqaY8oiJ|GN\u0003\u0003\u0003:\u0005U\u0018\u0002\u0002EI\u0011\u0013\u0013\u0001cQ8oiJ|G\u000e\u00165s_^\f'\r\\3\u0002\u0011A+HOT;mY\u0002\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"Aa\t)\u000f\u0005\u0011IJa(\u0003\"\":\u0001A!'\u0003 \n\u0005\u0006"
)
public final class JavaCollectionWrappers {
   public static class IteratorWrapper implements Iterator, Enumeration, Serializable {
      private static final long serialVersionUID = 3L;
      private final scala.collection.Iterator underlying;

      public void forEachRemaining(final Consumer x$1) {
         super.forEachRemaining(x$1);
      }

      public scala.collection.Iterator underlying() {
         return this.underlying;
      }

      public boolean hasNext() {
         return this.underlying().hasNext();
      }

      public Object next() {
         return this.underlying().next();
      }

      public boolean hasMoreElements() {
         return this.underlying().hasNext();
      }

      public Object nextElement() {
         return this.underlying().next();
      }

      public Nothing$ remove() {
         throw new UnsupportedOperationException();
      }

      public boolean equals(final Object other) {
         if (!(other instanceof IteratorWrapper)) {
            return false;
         } else {
            IteratorWrapper var2 = (IteratorWrapper)other;
            scala.collection.Iterator var10000 = this.underlying();
            scala.collection.Iterator var3 = var2.underlying();
            if (var10000 == null) {
               if (var3 == null) {
                  return true;
               }
            } else if (var10000.equals(var3)) {
               return true;
            }

            return false;
         }
      }

      public int hashCode() {
         return this.underlying().hashCode();
      }

      public IteratorWrapper(final scala.collection.Iterator underlying) {
         this.underlying = underlying;
      }
   }

   public static class JIteratorWrapper extends AbstractIterator implements Serializable {
      private static final long serialVersionUID = 3L;
      private final Iterator underlying;

      public Iterator underlying() {
         return this.underlying;
      }

      public boolean hasNext() {
         return this.underlying().hasNext();
      }

      public Object next() {
         return this.underlying().next();
      }

      public boolean equals(final Object other) {
         if (!(other instanceof JIteratorWrapper)) {
            return false;
         } else {
            JIteratorWrapper var2 = (JIteratorWrapper)other;
            Iterator var10000 = this.underlying();
            Iterator var3 = var2.underlying();
            if (var10000 == null) {
               if (var3 == null) {
                  return true;
               }
            } else if (var10000.equals(var3)) {
               return true;
            }

            return false;
         }
      }

      public int hashCode() {
         return this.underlying().hashCode();
      }

      public JIteratorWrapper(final Iterator underlying) {
         this.underlying = underlying;
      }
   }

   public static class JEnumerationWrapper extends AbstractIterator implements Serializable {
      private static final long serialVersionUID = 3L;
      private final Enumeration underlying;

      public Enumeration underlying() {
         return this.underlying;
      }

      public boolean hasNext() {
         return this.underlying().hasMoreElements();
      }

      public Object next() {
         return this.underlying().nextElement();
      }

      public boolean equals(final Object other) {
         if (!(other instanceof JEnumerationWrapper)) {
            return false;
         } else {
            JEnumerationWrapper var2 = (JEnumerationWrapper)other;
            Enumeration var10000 = this.underlying();
            Enumeration var3 = var2.underlying();
            if (var10000 == null) {
               if (var3 == null) {
                  return true;
               }
            } else if (var10000.equals(var3)) {
               return true;
            }

            return false;
         }
      }

      public int hashCode() {
         return this.underlying().hashCode();
      }

      public JEnumerationWrapper(final Enumeration underlying) {
         this.underlying = underlying;
      }
   }

   public interface IterableWrapperTrait {
      Iterable underlying();

      default int size() {
         return this.underlying().size();
      }

      default IteratorWrapper iterator() {
         return new IteratorWrapper(this.underlying().iterator());
      }

      default boolean isEmpty() {
         return this.underlying().isEmpty();
      }

      static void $init$(final IterableWrapperTrait $this) {
      }
   }

   public static class IterableWrapper extends AbstractCollection implements IterableWrapperTrait, Serializable {
      private static final long serialVersionUID = 3L;
      private final Iterable underlying;

      public int size() {
         return JavaCollectionWrappers.IterableWrapperTrait.super.size();
      }

      public IteratorWrapper iterator() {
         return JavaCollectionWrappers.IterableWrapperTrait.super.iterator();
      }

      public boolean isEmpty() {
         return JavaCollectionWrappers.IterableWrapperTrait.super.isEmpty();
      }

      public Iterable underlying() {
         return this.underlying;
      }

      public boolean equals(final Object other) {
         if (!(other instanceof IterableWrapper)) {
            return false;
         } else {
            IterableWrapper var2 = (IterableWrapper)other;
            Iterable var10000 = this.underlying();
            Iterable var3 = var2.underlying();
            if (var10000 == null) {
               if (var3 == null) {
                  return true;
               }
            } else if (var10000.equals(var3)) {
               return true;
            }

            return false;
         }
      }

      public int hashCode() {
         return this.underlying().hashCode();
      }

      public IterableWrapper(final Iterable underlying) {
         this.underlying = underlying;
      }
   }

   public static class JIterableWrapper extends AbstractIterable implements StrictOptimizedIterableOps, Serializable {
      private static final long serialVersionUID = 3L;
      private final java.lang.Iterable underlying;

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

      public Object filterImpl(final Function1 pred, final boolean isFlipped) {
         return StrictOptimizedIterableOps.filterImpl$(this, pred, isFlipped);
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

      public java.lang.Iterable underlying() {
         return this.underlying;
      }

      public scala.collection.Iterator iterator() {
         CollectionConverters$ var10000 = CollectionConverters$.MODULE$;
         Iterator IteratorHasAsScala_i = this.underlying().iterator();
         CollectionConverters$ IteratorHasAsScala_this = var10000;
         AsScalaExtensions.IteratorHasAsScala var5 = IteratorHasAsScala_this.new IteratorHasAsScala(IteratorHasAsScala_i);
         IteratorHasAsScala_this = null;
         Object var4 = null;
         return var5.asScala();
      }

      public ArrayBuffer$ iterableFactory() {
         return ArrayBuffer$.MODULE$;
      }

      public boolean isEmpty() {
         return !this.underlying().iterator().hasNext();
      }

      public boolean equals(final Object other) {
         if (!(other instanceof JIterableWrapper)) {
            return false;
         } else {
            JIterableWrapper var2 = (JIterableWrapper)other;
            java.lang.Iterable var10000 = this.underlying();
            java.lang.Iterable var3 = var2.underlying();
            if (var10000 == null) {
               if (var3 == null) {
                  return true;
               }
            } else if (var10000.equals(var3)) {
               return true;
            }

            return false;
         }
      }

      public int hashCode() {
         return this.underlying().hashCode();
      }

      public JIterableWrapper(final java.lang.Iterable underlying) {
         this.underlying = underlying;
      }
   }

   public static class JCollectionWrapper extends AbstractIterable implements StrictOptimizedIterableOps, Serializable {
      private static final long serialVersionUID = 3L;
      private final Collection underlying;

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

      public Object filterImpl(final Function1 pred, final boolean isFlipped) {
         return StrictOptimizedIterableOps.filterImpl$(this, pred, isFlipped);
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

      public Collection underlying() {
         return this.underlying;
      }

      public scala.collection.Iterator iterator() {
         CollectionConverters$ var10000 = CollectionConverters$.MODULE$;
         Iterator IteratorHasAsScala_i = this.underlying().iterator();
         CollectionConverters$ IteratorHasAsScala_this = var10000;
         AsScalaExtensions.IteratorHasAsScala var5 = IteratorHasAsScala_this.new IteratorHasAsScala(IteratorHasAsScala_i);
         IteratorHasAsScala_this = null;
         Object var4 = null;
         return var5.asScala();
      }

      public int size() {
         return this.underlying().size();
      }

      public int knownSize() {
         return this.underlying().isEmpty() ? 0 : -1;
      }

      public boolean isEmpty() {
         return this.underlying().isEmpty();
      }

      public ArrayBuffer$ iterableFactory() {
         return ArrayBuffer$.MODULE$;
      }

      public boolean equals(final Object other) {
         if (!(other instanceof JCollectionWrapper)) {
            return false;
         } else {
            JCollectionWrapper var2 = (JCollectionWrapper)other;
            Collection var10000 = this.underlying();
            Collection var3 = var2.underlying();
            if (var10000 == null) {
               if (var3 == null) {
                  return true;
               }
            } else if (var10000.equals(var3)) {
               return true;
            }

            return false;
         }
      }

      public int hashCode() {
         return this.underlying().hashCode();
      }

      public JCollectionWrapper(final Collection underlying) {
         this.underlying = underlying;
      }
   }

   public static class SeqWrapper extends AbstractList implements IterableWrapperTrait, Serializable {
      private static final long serialVersionUID = 3L;
      private final Seq underlying;

      public int size() {
         return JavaCollectionWrappers.IterableWrapperTrait.super.size();
      }

      public IteratorWrapper iterator() {
         return JavaCollectionWrappers.IterableWrapperTrait.super.iterator();
      }

      public boolean isEmpty() {
         return JavaCollectionWrappers.IterableWrapperTrait.super.isEmpty();
      }

      public Seq underlying() {
         return this.underlying;
      }

      public Object get(final int i) {
         return this.underlying().apply(i);
      }

      public SeqWrapper(final Seq underlying) {
         this.underlying = underlying;
      }
   }

   public static class MutableSeqWrapper extends AbstractList implements IterableWrapperTrait, Serializable {
      private static final long serialVersionUID = 3L;
      private final scala.collection.mutable.Seq underlying;

      public int size() {
         return JavaCollectionWrappers.IterableWrapperTrait.super.size();
      }

      public IteratorWrapper iterator() {
         return JavaCollectionWrappers.IterableWrapperTrait.super.iterator();
      }

      public boolean isEmpty() {
         return JavaCollectionWrappers.IterableWrapperTrait.super.isEmpty();
      }

      public scala.collection.mutable.Seq underlying() {
         return this.underlying;
      }

      public Object get(final int i) {
         return this.underlying().apply(i);
      }

      public Object set(final int i, final Object elem) {
         Object p = this.underlying().apply(i);
         this.underlying().update(i, elem);
         return p;
      }

      public MutableSeqWrapper(final scala.collection.mutable.Seq underlying) {
         this.underlying = underlying;
      }
   }

   public static class MutableBufferWrapper extends AbstractList implements IterableWrapperTrait, Serializable {
      private static final long serialVersionUID = 3L;
      private final Buffer underlying;

      public int size() {
         return JavaCollectionWrappers.IterableWrapperTrait.super.size();
      }

      public IteratorWrapper iterator() {
         return JavaCollectionWrappers.IterableWrapperTrait.super.iterator();
      }

      public boolean isEmpty() {
         return JavaCollectionWrappers.IterableWrapperTrait.super.isEmpty();
      }

      public Buffer underlying() {
         return this.underlying;
      }

      public Object get(final int i) {
         return this.underlying().apply(i);
      }

      public Object set(final int i, final Object elem) {
         Object p = this.underlying().apply(i);
         this.underlying().update(i, elem);
         return p;
      }

      public boolean add(final Object elem) {
         Buffer var10000 = this.underlying();
         if (var10000 == null) {
            throw null;
         } else {
            var10000.addOne(elem);
            return true;
         }
      }

      public Object remove(final int i) {
         return this.underlying().remove(i);
      }

      public MutableBufferWrapper(final Buffer underlying) {
         this.underlying = underlying;
      }
   }

   public static class JListWrapper extends AbstractBuffer implements StrictOptimizedSeqOps, Serializable {
      private static final long serialVersionUID = 3L;
      private final List underlying;

      public Object distinctBy(final Function1 f) {
         return StrictOptimizedSeqOps.distinctBy$(this, f);
      }

      public Object prepended(final Object elem) {
         return StrictOptimizedSeqOps.prepended$(this, elem);
      }

      public Object appended(final Object elem) {
         return StrictOptimizedSeqOps.appended$(this, elem);
      }

      public Object appendedAll(final IterableOnce suffix) {
         return StrictOptimizedSeqOps.appendedAll$(this, suffix);
      }

      public Object prependedAll(final IterableOnce prefix) {
         return StrictOptimizedSeqOps.prependedAll$(this, prefix);
      }

      public Object padTo(final int len, final Object elem) {
         return StrictOptimizedSeqOps.padTo$(this, len, elem);
      }

      public Object diff(final Seq that) {
         return StrictOptimizedSeqOps.diff$(this, that);
      }

      public Object intersect(final Seq that) {
         return StrictOptimizedSeqOps.intersect$(this, that);
      }

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

      public Object filterImpl(final Function1 pred, final boolean isFlipped) {
         return StrictOptimizedIterableOps.filterImpl$(this, pred, isFlipped);
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

      public List underlying() {
         return this.underlying;
      }

      public int length() {
         return this.underlying().size();
      }

      public int knownSize() {
         return this.underlying().isEmpty() ? 0 : -1;
      }

      public boolean isEmpty() {
         return this.underlying().isEmpty();
      }

      public scala.collection.Iterator iterator() {
         CollectionConverters$ var10000 = CollectionConverters$.MODULE$;
         Iterator IteratorHasAsScala_i = this.underlying().iterator();
         CollectionConverters$ IteratorHasAsScala_this = var10000;
         AsScalaExtensions.IteratorHasAsScala var5 = IteratorHasAsScala_this.new IteratorHasAsScala(IteratorHasAsScala_i);
         IteratorHasAsScala_this = null;
         Object var4 = null;
         return var5.asScala();
      }

      public Object apply(final int i) {
         return this.underlying().get(i);
      }

      public void update(final int i, final Object elem) {
         this.underlying().set(i, elem);
      }

      public JListWrapper prepend(final Object elem) {
         this.underlying().subList(0, 0).add(elem);
         return this;
      }

      public JListWrapper addOne(final Object elem) {
         this.underlying().add(elem);
         return this;
      }

      public void insert(final int idx, final Object elem) {
         this.underlying().subList(0, idx).add(elem);
      }

      public void insertAll(final int i, final IterableOnce elems) {
         List ins = this.underlying().subList(0, i);
         elems.iterator().foreach((x$1) -> BoxesRunTime.boxToBoolean($anonfun$insertAll$1(ins, x$1)));
      }

      public Object remove(final int i) {
         return this.underlying().remove(i);
      }

      public void clear() {
         this.underlying().clear();
      }

      public JListWrapper clone() {
         return new JListWrapper(new ArrayList(this.underlying()));
      }

      public JListWrapper patchInPlace(final int from, final IterableOnce patch, final int replaced) {
         this.remove(from, replaced);
         this.insertAll(from, patch);
         return this;
      }

      public void remove(final int from, final int n) {
         this.underlying().subList(from, from + n).clear();
      }

      public ArrayBuffer$ iterableFactory() {
         return ArrayBuffer$.MODULE$;
      }

      public JListWrapper subtractOne(final Object elem) {
         this.underlying().remove(elem);
         return this;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$insertAll$1(final List ins$1, final Object x$1) {
         return ins$1.add(x$1);
      }

      public JListWrapper(final List underlying) {
         this.underlying = underlying;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class SetWrapper extends AbstractSet implements Serializable {
      private static final long serialVersionUID = 3L;
      public final Set scala$collection$convert$JavaCollectionWrappers$SetWrapper$$underlying;

      public boolean contains(final Object o) {
         try {
            return this.scala$collection$convert$JavaCollectionWrappers$SetWrapper$$underlying.contains(o);
         } catch (ClassCastException var2) {
            return false;
         }
      }

      public boolean isEmpty() {
         return this.scala$collection$convert$JavaCollectionWrappers$SetWrapper$$underlying.isEmpty();
      }

      public int size() {
         return this.scala$collection$convert$JavaCollectionWrappers$SetWrapper$$underlying.size();
      }

      public Iterator iterator() {
         return new Iterator() {
            private final scala.collection.Iterator ui;
            private Option prev;
            // $FF: synthetic field
            private final SetWrapper $outer;

            public void forEachRemaining(final Consumer x$1) {
               super.forEachRemaining(x$1);
            }

            private scala.collection.Iterator ui() {
               return this.ui;
            }

            private Option prev() {
               return this.prev;
            }

            private void prev_$eq(final Option x$1) {
               this.prev = x$1;
            }

            public boolean hasNext() {
               return this.ui().hasNext();
            }

            public Object next() {
               Object e = this.ui().next();
               this.prev_$eq(new Some(e));
               return e;
            }

            public void remove() {
               Option var1 = this.prev();
               if (var1 instanceof Some) {
                  Object e = ((Some)var1).value();
                  Set var3 = this.$outer.scala$collection$convert$JavaCollectionWrappers$SetWrapper$$underlying;
                  if (var3 instanceof scala.collection.mutable.Set) {
                     ((scala.collection.mutable.Set)var3).remove(e);
                     this.prev_$eq(None$.MODULE$);
                  } else {
                     throw new UnsupportedOperationException("remove");
                  }
               } else {
                  throw new IllegalStateException("next must be called at least once before remove");
               }
            }

            public {
               if (SetWrapper.this == null) {
                  throw null;
               } else {
                  this.$outer = SetWrapper.this;
                  this.ui = SetWrapper.this.scala$collection$convert$JavaCollectionWrappers$SetWrapper$$underlying.iterator();
                  this.prev = None$.MODULE$;
               }
            }
         };
      }

      public SetWrapper(final Set underlying) {
         this.scala$collection$convert$JavaCollectionWrappers$SetWrapper$$underlying = underlying;
      }
   }

   public static class MutableSetWrapper extends SetWrapper {
      private static final long serialVersionUID = 3L;
      private final scala.collection.mutable.Set underlying;

      public scala.collection.mutable.Set underlying() {
         return this.underlying;
      }

      public boolean add(final Object elem) {
         int sz = this.underlying().size();
         scala.collection.mutable.Set var10000 = this.underlying();
         if (var10000 == null) {
            throw null;
         } else {
            var10000.addOne(elem);
            return sz < this.underlying().size();
         }
      }

      public boolean remove(final Object elem) {
         try {
            return this.underlying().remove(elem);
         } catch (ClassCastException var2) {
            return false;
         }
      }

      public void clear() {
         this.underlying().clear();
      }

      public MutableSetWrapper(final scala.collection.mutable.Set underlying) {
         super(underlying);
         this.underlying = underlying;
      }
   }

   public static class JSetWrapper extends scala.collection.mutable.AbstractSet implements StrictOptimizedSetOps, Serializable {
      private static final long serialVersionUID = 3L;
      private final java.util.Set underlying;

      public SetOps concat(final IterableOnce that) {
         return StrictOptimizedSetOps.concat$(this, that);
      }

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

      public Object filterImpl(final Function1 pred, final boolean isFlipped) {
         return StrictOptimizedIterableOps.filterImpl$(this, pred, isFlipped);
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

      public java.util.Set underlying() {
         return this.underlying;
      }

      public int size() {
         return this.underlying().size();
      }

      public boolean isEmpty() {
         return this.underlying().isEmpty();
      }

      public int knownSize() {
         return this.underlying().isEmpty() ? 0 : -1;
      }

      public scala.collection.Iterator iterator() {
         CollectionConverters$ var10000 = CollectionConverters$.MODULE$;
         Iterator IteratorHasAsScala_i = this.underlying().iterator();
         CollectionConverters$ IteratorHasAsScala_this = var10000;
         AsScalaExtensions.IteratorHasAsScala var5 = IteratorHasAsScala_this.new IteratorHasAsScala(IteratorHasAsScala_i);
         IteratorHasAsScala_this = null;
         Object var4 = null;
         return var5.asScala();
      }

      public boolean contains(final Object elem) {
         return this.underlying().contains(elem);
      }

      public JSetWrapper addOne(final Object elem) {
         this.underlying().add(elem);
         return this;
      }

      public JSetWrapper subtractOne(final Object elem) {
         this.underlying().remove(elem);
         return this;
      }

      public boolean remove(final Object elem) {
         return this.underlying().remove(elem);
      }

      public void clear() {
         this.underlying().clear();
      }

      public scala.collection.mutable.Set empty() {
         return new JSetWrapper(new HashSet());
      }

      public scala.collection.mutable.Set clone() {
         return new JSetWrapper(new LinkedHashSet(this.underlying()));
      }

      public IterableFactory iterableFactory() {
         return HashSet$.MODULE$;
      }

      public JSetWrapper filterInPlace(final Function1 p) {
         if (this.underlying().size() > 0) {
            this.underlying().removeIf((x$2) -> !BoxesRunTime.unboxToBoolean(p.apply(x$2)));
         }

         return this;
      }

      public JSetWrapper(final java.util.Set underlying) {
         this.underlying = underlying;
      }
   }

   public static class MapWrapper extends AbstractMap implements Serializable {
      private static final long serialVersionUID = 3L;
      public final Map scala$collection$convert$JavaCollectionWrappers$MapWrapper$$underlying;

      public int size() {
         return this.scala$collection$convert$JavaCollectionWrappers$MapWrapper$$underlying.size();
      }

      public Object get(final Object key) {
         Object var10000;
         try {
            Option var2 = this.scala$collection$convert$JavaCollectionWrappers$MapWrapper$$underlying.get(key);
            if (None$.MODULE$.equals(var2)) {
               var10000 = null;
            } else {
               if (!(var2 instanceof Some)) {
                  throw new MatchError(var2);
               }

               var10000 = ((Some)var2).value();
            }
         } catch (ClassCastException var3) {
            var10000 = null;
         }

         return var10000;
      }

      public java.util.Set entrySet() {
         return new AbstractSet() {
            // $FF: synthetic field
            private final MapWrapper $outer;

            public int size() {
               return this.$outer.size();
            }

            public Iterator iterator() {
               return new Iterator() {
                  private final scala.collection.Iterator ui;
                  private Option prev;
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public void forEachRemaining(final Consumer x$1) {
                     super.forEachRemaining(x$1);
                  }

                  private scala.collection.Iterator ui() {
                     return this.ui;
                  }

                  private Option prev() {
                     return this.prev;
                  }

                  private void prev_$eq(final Option x$1) {
                     this.prev = x$1;
                  }

                  public boolean hasNext() {
                     return this.ui().hasNext();
                  }

                  public java.util.Map.Entry next() {
                     Tuple2 var1 = (Tuple2)this.ui().next();
                     if (var1 != null) {
                        Object k = var1._1();
                        Object v = var1._2();
                        this.prev_$eq(new Some(k));
                        return new java.util.Map.Entry(k, v) {
                           // $FF: synthetic field
                           private final <undefinedtype> $outer;
                           private final Object k$1;
                           private final Object v$1;

                           public Object getKey() {
                              return this.k$1;
                           }

                           public Object getValue() {
                              return this.v$1;
                           }

                           public Object setValue(final Object v1) {
                              return this.$outer.scala$collection$convert$JavaCollectionWrappers$MapWrapper$$anon$$anon$$$outer().scala$collection$convert$JavaCollectionWrappers$MapWrapper$$anon$$$outer().put(this.k$1, v1);
                           }

                           public int hashCode() {
                              return (this.k$1 == null ? 0 : this.k$1.hashCode()) ^ (this.v$1 == null ? 0 : this.v$1.hashCode());
                           }

                           public boolean equals(final Object other) {
                              if (other instanceof java.util.Map.Entry) {
                                 java.util.Map.Entry var2 = (java.util.Map.Entry)other;
                                 return BoxesRunTime.equals(this.k$1, var2.getKey()) && BoxesRunTime.equals(this.v$1, var2.getValue());
                              } else {
                                 return false;
                              }
                           }

                           public {
                              if (<VAR_NAMELESS_ENCLOSURE> == null) {
                                 throw null;
                              } else {
                                 this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                                 this.k$1 = k$1;
                                 this.v$1 = v$1;
                              }
                           }
                        };
                     } else {
                        throw new MatchError((Object)null);
                     }
                  }

                  public void remove() {
                     Option var1 = this.prev();
                     if (var1 instanceof Some) {
                        Object k = ((Some)var1).value();
                        Map var3 = this.$outer.scala$collection$convert$JavaCollectionWrappers$MapWrapper$$anon$$$outer().scala$collection$convert$JavaCollectionWrappers$MapWrapper$$underlying;
                        if (var3 instanceof scala.collection.mutable.Map) {
                           scala.collection.mutable.Map var4 = (scala.collection.mutable.Map)var3;
                           if (var4 == null) {
                              throw null;
                           } else {
                              var4.subtractOne(k);
                              this.prev_$eq(None$.MODULE$);
                           }
                        } else {
                           throw new UnsupportedOperationException("remove");
                        }
                     } else {
                        throw new IllegalStateException("next must be called at least once before remove");
                     }
                  }

                  // $FF: synthetic method
                  public <undefinedtype> scala$collection$convert$JavaCollectionWrappers$MapWrapper$$anon$$anon$$$outer() {
                     return this.$outer;
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                        this.ui = scala$collection$convert$JavaCollectionWrappers$MapWrapper$$anon$$$outer().scala$collection$convert$JavaCollectionWrappers$MapWrapper$$underlying.iterator();
                        this.prev = None$.MODULE$;
                     }
                  }
               };
            }

            // $FF: synthetic method
            public MapWrapper scala$collection$convert$JavaCollectionWrappers$MapWrapper$$anon$$$outer() {
               return this.$outer;
            }

            public {
               if (MapWrapper.this == null) {
                  throw null;
               } else {
                  this.$outer = MapWrapper.this;
               }
            }
         };
      }

      public boolean containsKey(final Object key) {
         try {
            return this.scala$collection$convert$JavaCollectionWrappers$MapWrapper$$underlying.contains(key);
         } catch (ClassCastException var2) {
            return false;
         }
      }

      public MapWrapper(final Map underlying) {
         this.scala$collection$convert$JavaCollectionWrappers$MapWrapper$$underlying = underlying;
      }
   }

   public static class MutableMapWrapper extends MapWrapper {
      private static final long serialVersionUID = 3L;
      private final scala.collection.mutable.Map underlying;

      public scala.collection.mutable.Map underlying() {
         return this.underlying;
      }

      public Object put(final Object k, final Object v) {
         Option var3 = this.underlying().put(k, v);
         if (var3 instanceof Some) {
            return ((Some)var3).value();
         } else if (None$.MODULE$.equals(var3)) {
            return null;
         } else {
            throw new MatchError(var3);
         }
      }

      public Object remove(final Object k) {
         Object var10000;
         try {
            Option var2 = this.underlying().remove(k);
            if (None$.MODULE$.equals(var2)) {
               var10000 = null;
            } else {
               if (!(var2 instanceof Some)) {
                  throw new MatchError(var2);
               }

               var10000 = ((Some)var2).value();
            }
         } catch (ClassCastException var3) {
            var10000 = null;
         }

         return var10000;
      }

      public void clear() {
         this.underlying().clear();
      }

      public MutableMapWrapper(final scala.collection.mutable.Map underlying) {
         super(underlying);
         this.underlying = underlying;
      }
   }

   public abstract static class AbstractJMapWrapper extends scala.collection.mutable.AbstractMap implements JMapWrapperLike, Serializable {
      private static final long serialVersionUID = 3L;

      public int size() {
         return JavaCollectionWrappers.JMapWrapperLike.super.size();
      }

      public Option get(final Object k) {
         return JavaCollectionWrappers.JMapWrapperLike.super.get(k);
      }

      public Object getOrElseUpdate(final Object key, final Function0 op) {
         return JavaCollectionWrappers.JMapWrapperLike.super.getOrElseUpdate(key, op);
      }

      public JMapWrapperLike addOne(final Tuple2 kv) {
         return JavaCollectionWrappers.JMapWrapperLike.super.addOne(kv);
      }

      public JMapWrapperLike subtractOne(final Object key) {
         return JavaCollectionWrappers.JMapWrapperLike.super.subtractOne(key);
      }

      public Option put(final Object k, final Object v) {
         return JavaCollectionWrappers.JMapWrapperLike.super.put(k, v);
      }

      public void update(final Object k, final Object v) {
         JavaCollectionWrappers.JMapWrapperLike.super.update(k, v);
      }

      public Option updateWith(final Object key, final Function1 remappingFunction) {
         return JavaCollectionWrappers.JMapWrapperLike.super.updateWith(key, remappingFunction);
      }

      public Option remove(final Object k) {
         return JavaCollectionWrappers.JMapWrapperLike.super.remove(k);
      }

      public scala.collection.Iterator iterator() {
         return JavaCollectionWrappers.JMapWrapperLike.super.iterator();
      }

      public void foreachEntry(final Function2 f) {
         JavaCollectionWrappers.JMapWrapperLike.super.foreachEntry(f);
      }

      public void clear() {
         JavaCollectionWrappers.JMapWrapperLike.super.clear();
      }

      public IterableOps map(final Function1 f) {
         return StrictOptimizedMapOps.map$(this, f);
      }

      public IterableOps flatMap(final Function1 f) {
         return StrictOptimizedMapOps.flatMap$(this, f);
      }

      public IterableOps concat(final IterableOnce suffix) {
         return StrictOptimizedMapOps.concat$(this, suffix);
      }

      public IterableOps collect(final PartialFunction pf) {
         return StrictOptimizedMapOps.collect$(this, pf);
      }

      /** @deprecated */
      public IterableOps $plus(final Tuple2 elem1, final Tuple2 elem2, final scala.collection.immutable.Seq elems) {
         return StrictOptimizedMapOps.$plus$(this, elem1, elem2, elems);
      }

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

      public Object filterImpl(final Function1 pred, final boolean isFlipped) {
         return StrictOptimizedIterableOps.filterImpl$(this, pred, isFlipped);
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
   }

   public interface JMapWrapperLike extends MapOps, StrictOptimizedMapOps {
      java.util.Map underlying();

      default int size() {
         return this.underlying().size();
      }

      default Option get(final Object k) {
         Object v = this.underlying().get(k);
         if (v != null) {
            return new Some(v);
         } else {
            return (Option)(this.underlying().containsKey(k) ? new Some((Object)null) : None$.MODULE$);
         }
      }

      default Object getOrElseUpdate(final Object key, final Function0 op) {
         Object var3 = this.underlying().computeIfAbsent(key, (x$4) -> op.apply());
         if (var3 == null) {
            this.update(key, (Object)null);
            return null;
         } else {
            return var3;
         }
      }

      default JMapWrapperLike addOne(final Tuple2 kv) {
         this.underlying().put(kv._1(), kv._2());
         return this;
      }

      default JMapWrapperLike subtractOne(final Object key) {
         this.underlying().remove(key);
         return this;
      }

      default Option put(final Object k, final Object v) {
         if (v == null) {
            boolean present = this.underlying().containsKey(k);
            Object result = this.underlying().put(k, (Object)null);
            return (Option)(present ? new Some(result) : None$.MODULE$);
         } else {
            U create_e = (U)None$.MODULE$;
            ObjectRef var10000 = new ObjectRef(create_e);
            create_e = (U)null;
            ObjectRef result = var10000;
            this.underlying().compute(k, (k0, v0) -> this.recompute$1(k0, v0, v, result));
            return (Option)result.elem;
         }
      }

      default void update(final Object k, final Object v) {
         this.underlying().put(k, v);
      }

      default Option updateWith(final Object key, final Function1 remappingFunction) {
         try {
            return Option$.MODULE$.apply(this.underlying().compute(key, (k, v) -> remap$1(k, v, remappingFunction)));
         } catch (Throwable var4) {
            label19: {
               ControlThrowable var10000 = JavaCollectionWrappers$.MODULE$.scala$collection$convert$JavaCollectionWrappers$$PutNull();
               if (var10000 == null) {
                  if (var4 == null) {
                     break label19;
                  }
               } else if (var10000.equals(var4)) {
                  break label19;
               }

               throw var4;
            }

            this.update(key, (Object)null);
            return new Some((Object)null);
         }
      }

      default Option remove(final Object k) {
         U create_e = (U)None$.MODULE$;
         ObjectRef var10000 = new ObjectRef(create_e);
         create_e = (U)null;
         ObjectRef result = var10000;
         this.underlying().compute(k, (k0, v0) -> this.recompute$2(k0, v0, result));
         return (Option)result.elem;
      }

      default scala.collection.Iterator iterator() {
         return new AbstractIterator() {
            private final Iterator ui = JMapWrapperLike.this.underlying().entrySet().iterator();

            private Iterator ui() {
               return this.ui;
            }

            public boolean hasNext() {
               return this.ui().hasNext();
            }

            public Tuple2 next() {
               java.util.Map.Entry e = (java.util.Map.Entry)this.ui().next();
               return new Tuple2(e.getKey(), e.getValue());
            }
         };
      }

      default void foreachEntry(final Function2 f) {
         for(java.util.Map.Entry entry : this.underlying().entrySet()) {
            f.apply(entry.getKey(), entry.getValue());
         }

      }

      default void clear() {
         this.underlying().clear();
      }

      // $FF: synthetic method
      static void $anonfun$put$1(final JMapWrapperLike $this, final Object v0$1, final ObjectRef result$1, final Object k0$1, final Object x$5) {
         if (v0$1 != null) {
            result$1.elem = new Some(v0$1);
         } else if ($this.underlying().containsKey(k0$1)) {
            result$1.elem = new Some((Object)null);
         }
      }

      private Object recompute$1(final Object k0, final Object v0, final Object v$2, final ObjectRef result$1) {
         ChainingOps$ var10000 = ChainingOps$.MODULE$;
         package.chaining$ var5 = package.chaining$.MODULE$;
         if (v0 != null) {
            result$1.elem = new Some(v0);
         } else if (this.underlying().containsKey(k0)) {
            result$1.elem = new Some((Object)null);
         }

         return v$2;
      }

      private static Object remap$1(final Object k, final Object v, final Function1 remappingFunction$1) {
         boolean var3 = false;
         Some var4 = null;
         Option var5 = (Option)remappingFunction$1.apply(Option$.MODULE$.apply(v));
         if (var5 instanceof Some) {
            var3 = true;
            var4 = (Some)var5;
            if (var4.value() == null) {
               throw JavaCollectionWrappers$.MODULE$.scala$collection$convert$JavaCollectionWrappers$$PutNull();
            }
         }

         if (var3) {
            return var4.value();
         } else if (None$.MODULE$.equals(var5)) {
            return null;
         } else {
            throw new MatchError(var5);
         }
      }

      private Object recompute$2(final Object k0, final Object v0, final ObjectRef result$2) {
         if (v0 != null) {
            result$2.elem = new Some(v0);
         } else if (this.underlying().containsKey(k0)) {
            result$2.elem = new Some((Object)null);
         }

         return null;
      }

      static void $init$(final JMapWrapperLike $this) {
      }

      // $FF: synthetic method
      static Object $anonfun$put$1$adapted(final JMapWrapperLike $this, final Object v0$1, final ObjectRef result$1, final Object k0$1, final Object x$5) {
         $anonfun$put$1($this, v0$1, result$1, k0$1, x$5);
         return BoxedUnit.UNIT;
      }
   }

   public static class JMapWrapper extends AbstractJMapWrapper {
      private static final long serialVersionUID = 3L;
      private final java.util.Map underlying;

      public java.util.Map underlying() {
         return this.underlying;
      }

      public boolean isEmpty() {
         return this.underlying().isEmpty();
      }

      public int knownSize() {
         return this.underlying().isEmpty() ? 0 : -1;
      }

      public JMapWrapper empty() {
         return new JMapWrapper(new HashMap());
      }

      public JMapWrapper(final java.util.Map underlying) {
         this.underlying = underlying;
      }
   }

   public static class ConcurrentMapWrapper extends MutableMapWrapper implements ConcurrentMap {
      private static final long serialVersionUID = 3L;

      public Object getOrDefault(final Object x$1, final Object x$2) {
         return super.getOrDefault(x$1, x$2);
      }

      public void forEach(final BiConsumer x$1) {
         super.forEach(x$1);
      }

      public void replaceAll(final BiFunction x$1) {
         super.replaceAll(x$1);
      }

      public Object computeIfAbsent(final Object x$1, final Function x$2) {
         return super.computeIfAbsent(x$1, x$2);
      }

      public Object computeIfPresent(final Object x$1, final BiFunction x$2) {
         return super.computeIfPresent(x$1, x$2);
      }

      public Object compute(final Object x$1, final BiFunction x$2) {
         return super.compute(x$1, x$2);
      }

      public Object merge(final Object x$1, final Object x$2, final BiFunction x$3) {
         return super.merge(x$1, x$2, x$3);
      }

      public scala.collection.concurrent.Map underlyingConcurrentMap() {
         return (scala.collection.concurrent.Map)super.underlying();
      }

      public Object putIfAbsent(final Object k, final Object v) {
         Option var10000 = ((scala.collection.concurrent.Map)super.underlying()).putIfAbsent(k, v);
         if (var10000 == null) {
            throw null;
         } else {
            Option getOrElse_this = var10000;
            return getOrElse_this.isEmpty() ? null : getOrElse_this.get();
         }
      }

      public boolean remove(final Object k, final Object v) {
         try {
            return ((scala.collection.concurrent.Map)super.underlying()).remove(k, v);
         } catch (ClassCastException var3) {
            return false;
         }
      }

      public Object replace(final Object k, final Object v) {
         Option var10000 = ((scala.collection.concurrent.Map)super.underlying()).replace(k, v);
         if (var10000 == null) {
            throw null;
         } else {
            Option getOrElse_this = var10000;
            return getOrElse_this.isEmpty() ? null : getOrElse_this.get();
         }
      }

      public boolean replace(final Object k, final Object oldval, final Object newval) {
         return ((scala.collection.concurrent.Map)super.underlying()).replace(k, oldval, newval);
      }

      // $FF: synthetic method
      public static final Object $anonfun$putIfAbsent$1() {
         return null;
      }

      // $FF: synthetic method
      public static final Object $anonfun$replace$1() {
         return null;
      }

      public ConcurrentMapWrapper(final scala.collection.concurrent.Map underlying) {
         super(underlying);
      }
   }

   public static class JConcurrentMapWrapper extends AbstractJMapWrapper implements scala.collection.concurrent.Map {
      private static final long serialVersionUID = 3L;
      private final ConcurrentMap underlying;

      public boolean removeRefEq(final Object k, final Object v) {
         return scala.collection.concurrent.Map.removeRefEq$(this, k, v);
      }

      public boolean replaceRefEq(final Object k, final Object oldValue, final Object newValue) {
         return scala.collection.concurrent.Map.replaceRefEq$(this, k, oldValue, newValue);
      }

      public scala.collection.concurrent.Map filterInPlaceImpl(final Function2 p) {
         return scala.collection.concurrent.Map.filterInPlaceImpl$(this, p);
      }

      public scala.collection.concurrent.Map mapValuesInPlaceImpl(final Function2 f) {
         return scala.collection.concurrent.Map.mapValuesInPlaceImpl$(this, f);
      }

      public ConcurrentMap underlying() {
         return this.underlying;
      }

      public Option get(final Object k) {
         return Option$.MODULE$.apply(this.underlying().get(k));
      }

      public Object getOrElseUpdate(final Object key, final Function0 op) {
         Object var3 = this.underlying().computeIfAbsent(key, (x$6) -> op.apply());
         if (var3 == null) {
            Option var4 = this.get(key);
            if (var4 instanceof Some) {
               return ((Some)var4).value();
            } else if (None$.MODULE$.equals(var4)) {
               Object getOrElseUpdate_v = op.apply();
               Option var6 = this.putIfAbsent(key, getOrElseUpdate_v);
               if (var6 instanceof Some) {
                  return ((Some)var6).value();
               } else if (None$.MODULE$.equals(var6)) {
                  return getOrElseUpdate_v;
               } else {
                  throw new MatchError(var6);
               }
            } else {
               throw new MatchError(var4);
            }
         } else {
            return var3;
         }
      }

      public boolean isEmpty() {
         return this.underlying().isEmpty();
      }

      public int knownSize() {
         return this.underlying().isEmpty() ? 0 : -1;
      }

      public JConcurrentMapWrapper empty() {
         return new JConcurrentMapWrapper(new ConcurrentHashMap());
      }

      public Option putIfAbsent(final Object k, final Object v) {
         return Option$.MODULE$.apply(this.underlying().putIfAbsent(k, v));
      }

      public boolean remove(final Object k, final Object v) {
         return this.underlying().remove(k, v);
      }

      public Option replace(final Object k, final Object v) {
         return Option$.MODULE$.apply(this.underlying().replace(k, v));
      }

      public boolean replace(final Object k, final Object oldvalue, final Object newvalue) {
         return this.underlying().replace(k, oldvalue, newvalue);
      }

      public Option lastOption() {
         ConcurrentMap var1 = this.underlying();
         if (var1 instanceof NavigableMap) {
            Option var8 = Option$.MODULE$.apply(((NavigableMap)var1).lastEntry());
            if (var8 == null) {
               throw null;
            } else {
               Option map_this = var8;
               return (Option)(map_this.isEmpty() ? None$.MODULE$ : new Some($anonfun$lastOption$1((java.util.Map.Entry)map_this.get())));
            }
         } else if (this.isEmpty()) {
            return None$.MODULE$;
         } else {
            Try$ var10000 = Try$.MODULE$;

            try {
               Object apply_r1 = (Tuple2)this.last();
               var10000 = new Success(apply_r1);
            } catch (Throwable var5) {
               if (var5 == null || !NonFatal$.MODULE$.apply(var5)) {
                  throw var5;
               }

               var10000 = new Failure(var5);
            }

            Object var6 = null;
            Object var4 = null;
            return ((Try)var10000).toOption();
         }
      }

      public Option updateWith(final Object key, final Function1 remappingFunction) {
         try {
            return Option$.MODULE$.apply(this.underlying().compute(key, (k, v) -> remap$2(k, v, remappingFunction)));
         } catch (Throwable var9) {
            ControlThrowable var10000 = JavaCollectionWrappers$.MODULE$.scala$collection$convert$JavaCollectionWrappers$$PutNull();
            if (var10000 == null) {
               if (var9 != null) {
                  throw var9;
               }
            } else if (!var10000.equals(var9)) {
               throw var9;
            }

            while(true) {
               Option updateWith_updateWithAux_previousValue = this.get(key);
               Option updateWith_updateWithAux_nextValue = (Option)remappingFunction.apply(updateWith_updateWithAux_previousValue);
               if (updateWith_updateWithAux_previousValue instanceof Some) {
                  Object updateWith_updateWithAux_prev = ((Some)updateWith_updateWithAux_previousValue).value();
                  if (updateWith_updateWithAux_nextValue instanceof Some) {
                     Object updateWith_updateWithAux_next = ((Some)updateWith_updateWithAux_nextValue).value();
                     if (this.replaceRefEq(key, updateWith_updateWithAux_prev, updateWith_updateWithAux_next)) {
                        return updateWith_updateWithAux_nextValue;
                     }
                  } else if (this.removeRefEq(key, updateWith_updateWithAux_prev)) {
                     return None$.MODULE$;
                  }
               } else {
                  if (!(updateWith_updateWithAux_nextValue instanceof Some)) {
                     return None$.MODULE$;
                  }

                  Object updateWith_updateWithAux_next = ((Some)updateWith_updateWithAux_nextValue).value();
                  if (this.putIfAbsent(key, updateWith_updateWithAux_next).isEmpty()) {
                     return updateWith_updateWithAux_nextValue;
                  }
               }
            }
         }
      }

      // $FF: synthetic method
      public static final Tuple2 $anonfun$lastOption$1(final java.util.Map.Entry e) {
         return new Tuple2(e.getKey(), e.getValue());
      }

      // $FF: synthetic method
      public static final Tuple2 $anonfun$lastOption$2(final JConcurrentMapWrapper $this) {
         return (Tuple2)$this.last();
      }

      private static final Object remap$2(final Object k, final Object v, final Function1 remappingFunction$2) {
         boolean var3 = false;
         Some var4 = null;
         Option var5 = (Option)remappingFunction$2.apply(Option$.MODULE$.apply(v));
         if (var5 instanceof Some) {
            var3 = true;
            var4 = (Some)var5;
            if (var4.value() == null) {
               throw JavaCollectionWrappers$.MODULE$.scala$collection$convert$JavaCollectionWrappers$$PutNull();
            }
         }

         if (var3) {
            return var4.value();
         } else if (None$.MODULE$.equals(var5)) {
            return null;
         } else {
            throw new MatchError(var5);
         }
      }

      public JConcurrentMapWrapper(final ConcurrentMap underlying) {
         this.underlying = underlying;
      }
   }

   public static class DictionaryWrapper extends Dictionary implements Serializable {
      private static final long serialVersionUID = 3L;
      private final scala.collection.mutable.Map underlying;

      public scala.collection.mutable.Map underlying() {
         return this.underlying;
      }

      public int size() {
         return this.underlying().size();
      }

      public boolean isEmpty() {
         return this.underlying().isEmpty();
      }

      public Enumeration keys() {
         CollectionConverters$ var10000 = CollectionConverters$.MODULE$;
         scala.collection.Iterator IteratorHasAsJava_i = this.underlying().keysIterator();
         CollectionConverters$ IteratorHasAsJava_this = var10000;
         AsJavaExtensions.IteratorHasAsJava var5 = IteratorHasAsJava_this.new IteratorHasAsJava(IteratorHasAsJava_i);
         IteratorHasAsJava_this = null;
         IteratorHasAsJava_i = null;
         return var5.asJavaEnumeration();
      }

      public Enumeration elements() {
         CollectionConverters$ var10000 = CollectionConverters$.MODULE$;
         scala.collection.Iterator IteratorHasAsJava_i = this.underlying().valuesIterator();
         CollectionConverters$ IteratorHasAsJava_this = var10000;
         AsJavaExtensions.IteratorHasAsJava var5 = IteratorHasAsJava_this.new IteratorHasAsJava(IteratorHasAsJava_i);
         IteratorHasAsJava_this = null;
         IteratorHasAsJava_i = null;
         return var5.asJavaEnumeration();
      }

      public Object get(final Object key) {
         Object var10000;
         try {
            Option var2 = this.underlying().get(key);
            if (None$.MODULE$.equals(var2)) {
               var10000 = null;
            } else {
               if (!(var2 instanceof Some)) {
                  throw new MatchError(var2);
               }

               var10000 = ((Some)var2).value();
            }
         } catch (ClassCastException var3) {
            var10000 = null;
         }

         return var10000;
      }

      public Object put(final Object key, final Object value) {
         Option var3 = this.underlying().put(key, value);
         if (var3 instanceof Some) {
            return ((Some)var3).value();
         } else if (None$.MODULE$.equals(var3)) {
            return null;
         } else {
            throw new MatchError(var3);
         }
      }

      public Object remove(final Object key) {
         Object var10000;
         try {
            Option var2 = this.underlying().remove(key);
            if (None$.MODULE$.equals(var2)) {
               var10000 = null;
            } else {
               if (!(var2 instanceof Some)) {
                  throw new MatchError(var2);
               }

               var10000 = ((Some)var2).value();
            }
         } catch (ClassCastException var3) {
            var10000 = null;
         }

         return var10000;
      }

      public boolean equals(final Object other) {
         if (!(other instanceof DictionaryWrapper)) {
            return false;
         } else {
            DictionaryWrapper var2 = (DictionaryWrapper)other;
            scala.collection.mutable.Map var10000 = this.underlying();
            scala.collection.mutable.Map var3 = var2.underlying();
            if (var10000 == null) {
               if (var3 == null) {
                  return true;
               }
            } else if (var10000.equals(var3)) {
               return true;
            }

            return false;
         }
      }

      public int hashCode() {
         return this.underlying().hashCode();
      }

      public DictionaryWrapper(final scala.collection.mutable.Map underlying) {
         this.underlying = underlying;
      }
   }

   public static class JDictionaryWrapper extends scala.collection.mutable.AbstractMap implements Serializable {
      private static final long serialVersionUID = 3L;
      private final Dictionary underlying;

      public Dictionary underlying() {
         return this.underlying;
      }

      public int size() {
         return this.underlying().size();
      }

      public boolean isEmpty() {
         return this.underlying().isEmpty();
      }

      public int knownSize() {
         return this.underlying().isEmpty() ? 0 : -1;
      }

      public Option get(final Object k) {
         return Option$.MODULE$.apply(this.underlying().get(k));
      }

      public JDictionaryWrapper addOne(final Tuple2 kv) {
         this.underlying().put(kv._1(), kv._2());
         return this;
      }

      public JDictionaryWrapper subtractOne(final Object key) {
         this.underlying().remove(key);
         return this;
      }

      public Option put(final Object k, final Object v) {
         return Option$.MODULE$.apply(this.underlying().put(k, v));
      }

      public void update(final Object k, final Object v) {
         this.underlying().put(k, v);
      }

      public Option remove(final Object k) {
         return Option$.MODULE$.apply(this.underlying().remove(k));
      }

      public scala.collection.Iterator iterator() {
         CollectionConverters$ var10000 = CollectionConverters$.MODULE$;
         Enumeration EnumerationHasAsScala_e = this.underlying().keys();
         CollectionConverters$ EnumerationHasAsScala_this = var10000;
         AsScalaExtensions.EnumerationHasAsScala var5 = EnumerationHasAsScala_this.new EnumerationHasAsScala(EnumerationHasAsScala_e);
         EnumerationHasAsScala_this = null;
         Object var4 = null;
         return var5.asScala().map((k) -> new Tuple2(k, this.underlying().get(k)));
      }

      public void clear() {
         this.iterator().foreach((entry) -> this.underlying().remove(entry._1()));
      }

      public HashMap$ mapFactory() {
         return HashMap$.MODULE$;
      }

      public JDictionaryWrapper(final Dictionary underlying) {
         this.underlying = underlying;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class JPropertiesWrapper extends scala.collection.mutable.AbstractMap implements StrictOptimizedMapOps, Serializable {
      private static final long serialVersionUID = 3L;
      public final Properties scala$collection$convert$JavaCollectionWrappers$JPropertiesWrapper$$underlying;

      public IterableOps map(final Function1 f) {
         return StrictOptimizedMapOps.map$(this, f);
      }

      public IterableOps flatMap(final Function1 f) {
         return StrictOptimizedMapOps.flatMap$(this, f);
      }

      public IterableOps concat(final IterableOnce suffix) {
         return StrictOptimizedMapOps.concat$(this, suffix);
      }

      public IterableOps collect(final PartialFunction pf) {
         return StrictOptimizedMapOps.collect$(this, pf);
      }

      /** @deprecated */
      public IterableOps $plus(final Tuple2 elem1, final Tuple2 elem2, final scala.collection.immutable.Seq elems) {
         return StrictOptimizedMapOps.$plus$(this, elem1, elem2, elems);
      }

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

      public Object filterImpl(final Function1 pred, final boolean isFlipped) {
         return StrictOptimizedIterableOps.filterImpl$(this, pred, isFlipped);
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
         return this.scala$collection$convert$JavaCollectionWrappers$JPropertiesWrapper$$underlying.size();
      }

      public boolean isEmpty() {
         return this.scala$collection$convert$JavaCollectionWrappers$JPropertiesWrapper$$underlying.isEmpty();
      }

      public int knownSize() {
         return this.size();
      }

      public Option get(final String k) {
         Object v = this.scala$collection$convert$JavaCollectionWrappers$JPropertiesWrapper$$underlying.get(k);
         return (Option)(v != null ? new Some((String)v) : None$.MODULE$);
      }

      public JPropertiesWrapper addOne(final Tuple2 kv) {
         this.scala$collection$convert$JavaCollectionWrappers$JPropertiesWrapper$$underlying.put(kv._1(), kv._2());
         return this;
      }

      public JPropertiesWrapper subtractOne(final String key) {
         this.scala$collection$convert$JavaCollectionWrappers$JPropertiesWrapper$$underlying.remove(key);
         return this;
      }

      public Option put(final String k, final String v) {
         Object r = this.scala$collection$convert$JavaCollectionWrappers$JPropertiesWrapper$$underlying.put(k, v);
         return (Option)(r != null ? new Some((String)r) : None$.MODULE$);
      }

      public void update(final String k, final String v) {
         this.scala$collection$convert$JavaCollectionWrappers$JPropertiesWrapper$$underlying.put(k, v);
      }

      public Option remove(final String k) {
         Object r = this.scala$collection$convert$JavaCollectionWrappers$JPropertiesWrapper$$underlying.remove(k);
         return (Option)(r != null ? new Some((String)r) : None$.MODULE$);
      }

      public scala.collection.Iterator iterator() {
         return new AbstractIterator() {
            private final Iterator ui;

            private Iterator ui() {
               return this.ui;
            }

            public boolean hasNext() {
               return this.ui().hasNext();
            }

            public Tuple2 next() {
               java.util.Map.Entry e = (java.util.Map.Entry)this.ui().next();
               return new Tuple2((String)e.getKey(), (String)e.getValue());
            }

            public {
               this.ui = JPropertiesWrapper.this.scala$collection$convert$JavaCollectionWrappers$JPropertiesWrapper$$underlying.entrySet().iterator();
            }
         };
      }

      public void clear() {
         this.scala$collection$convert$JavaCollectionWrappers$JPropertiesWrapper$$underlying.clear();
      }

      public JPropertiesWrapper empty() {
         return new JPropertiesWrapper(new Properties());
      }

      public String getProperty(final String key) {
         return this.scala$collection$convert$JavaCollectionWrappers$JPropertiesWrapper$$underlying.getProperty(key);
      }

      public String getProperty(final String key, final String defaultValue) {
         return this.scala$collection$convert$JavaCollectionWrappers$JPropertiesWrapper$$underlying.getProperty(key, defaultValue);
      }

      public Object setProperty(final String key, final String value) {
         return this.scala$collection$convert$JavaCollectionWrappers$JPropertiesWrapper$$underlying.setProperty(key, value);
      }

      public HashMap$ mapFactory() {
         return HashMap$.MODULE$;
      }

      public JPropertiesWrapper(final Properties underlying) {
         this.scala$collection$convert$JavaCollectionWrappers$JPropertiesWrapper$$underlying = underlying;
      }
   }
}

package scala.reflect.internal.tpe;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableFactory;
import scala.collection.IterableOnceOps;
import scala.collection.LinearSeq;
import scala.collection.LinearSeqOps;
import scala.collection.MapFactory;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.StrictOptimizedLinearSeqOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.ListBuffer;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.AnnotationInfos;
import scala.reflect.internal.Definitions;
import scala.reflect.internal.Names;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;
import scala.reflect.internal.Variance$;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.internal.util.Collections;
import scala.reflect.internal.util.Position;
import scala.reflect.internal.util.StatisticsStatics;
import scala.reflect.internal.util.StripMarginInterpolator;
import scala.reflect.internal.util.package;
import scala.reflect.internal.util.package$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.Nothing;
import scala.runtime.ObjectRef;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005!ufA\u0004B\u001c\u0005s\u0001\n1!\u0001\u0003>\t%\u0003r\u0017\u0005\b\u0005'\u0002A\u0011\u0001B,\u000f\u001d\u0011y\u0006\u0001E\u0001\u0005C2qA!\u001a\u0001\u0011\u0003\u00119\u0007C\u0004\u0003\u0000\r!\taa\u000f\t\u000f\t\r5\u0001\"\u0001\u0004>\u001d91\u0011\t\u0001\t\u0002\r\rcaBB#\u0001!\u00051q\t\u0005\b\u0005\u007f:A\u0011AB%\u0011\u001d\u0011\u0019i\u0002C\u0001\u0007\u0017:qaa\u0014\u0001\u0011\u0003\u0019\tFB\u0004\u0004T\u0001A\ta!\u0016\t\u000f\t}4\u0002\"\u0001\u0004X!9!1Q\u0006\u0005\u0002\resaBB6\u0001!\u00051Q\u000e\u0004\b\u0007_\u0002\u0001\u0012AB9\u0011\u001d\u0011yh\u0004C\u0001\u0007gBqAa!\u0010\t\u0003\u0019)HB\u0005\u0004z\u0001\u0001\n1!\u0001\u0004|!9!1\u000b\n\u0005\u0002\t]\u0003bBB?%\u0019\u00051q\u0010\u0005\b\u0005\u0017\u0013B\u0011IBE\u00115\u0019iI\u0005I\u0001\u0004\u0003\u0005I\u0011BBHE\u0019I11\u0013\u0001\u0011\u0002\u0007\u00051Q\u0013\u0005\b\u0005':B\u0011\u0001B,\u0011\u001d\u0019ih\u0006C\u0001\u000733qAa\u001b\u0001\u0003\u0003\u0011i\u0007C\u0004\u0003\u0000i!\tA!!\t\u000f\t\r%D\"\u0001\u0003\u0006\"9!1\u0012\u000e\u0005\u0002\t5\u0005b\u0002BI5\u0011%!1\u0013\u0005\b\u0005oSB\u0011\u0003B]\u0011\u001d\u0011YI\u0007C\u0001\u0005\u0007DqAa#\u001b\t\u0003\u0011\u0019\u000eC\u0004\u0003\fj!\tAa6\t\u000f\t\u001d(\u0004\"\u0001\u0003j\"9!\u0011\u001f\u000e\u0005\u0002\tM\bb\u0002BF5\u0011\u00051Q\u0001\u0005\b\u0005\u0017SB\u0011AB\u0006\r\u0019\u0019yB\u0007\u0001\u0004\"!9!qP\u0014\u0005\u0002\r=\u0002bBB\u001bO\u0011\u00053q\u0007\u0004\b\u0007;\u0003\u0011\u0011ABP\u0011\u001d\u0011yH\u000bC\u0001\u0007CC\u0001b!*+A\u0003&1q\u0015\u0005\b\u0007_SC\u0011ABY\u0011\u001d\u00199L\u000bC\u0001\u0007sCqaa/+\t\u000b\u0019i\fC\u0004\u0004j*\")aa;\t\u000f\re(\u0006\"\u0002\u0004|\"9!q\u0017\u0016\u0005V\u0011\u0015aa\u0002C\u0006\u0001\u0005\u0005AQ\u0002\u0005\b\u0005\u007f\u001aD\u0011\u0001C\t\u0011\u001d\u0011\u0019i\rD\u0001\t+Aq\u0001\"\u00074\t\u0003!Y\u0002C\u0004\u0005\u001aM\"\t\u0001\"\t\t\u000f\u0011\u00152\u0007\"\u0001\u0005(!9A\u0011D\u001a\u0005\u0002\u0011-\u0002b\u0002C\u0018g\u0011\u0005A\u0011\u0007\u0005\b\t3\u0019D\u0011\u0001C\u001b\r\u001d!I\u0004AA\u0001\twAqAa =\t\u0003!i\u0004C\u0004\u0005Bq2\t\u0001b\u0011\t\u000f\t\rE\b\"\u0001\u0005H\u00199A1\n\u0001\u0002\u0002\u00115\u0003B\u0003C)\u0001\n\u0005\t\u0015!\u0003\u0005T!9!q\u0010!\u0005\u0002\u0011]\u0003b\u0003C/\u0001\u0002\u0007\t\u0019!C\u0001\t?B1\u0002\"\u0019A\u0001\u0004\u0005\r\u0011\"\u0001\u0005d!YA\u0011\u000e!A\u0002\u0003\u0005\u000b\u0015\u0002C*\u0011\u001d!Y\u0007\u0011C\u0001\t[Bq\u0001\"\u001d\u0001\t\u0003!\u0019H\u0002\u0004\u0005v\u0001\u0001Aq\u000f\u0005\u000b\t\u0007A%\u0011!Q\u0001\n\t}\u0005b\u0002B@\u0011\u0012\u0005A\u0011\u0010\u0005\t\t\u007fB\u0005\u0015!\u0003\u0005\u0002\"AA\u0011\u0013%!\u0002\u0013!\u0019\nC\u0004\u0005p\"#I\u0001\"=\t\u000f\u0011U\b\n\"\u0001\u0005x\"9!1\u0011%\u0005\u0002\u0011m\bb\u0002BF\u0011\u0012\u0005Cq \u0005\b\u0005\u0017CE\u0011IC\u0002\u000f\u001d)9\u0001\u0001E\u0001\u000b\u00131q!b\u0003\u0001\u0011\u0003)i\u0001C\u0004\u0003\u0000M#\t!b\u0004\t\u000f\t\r5\u000b\"\u0001\u0006\u0012!9QQ\u0003\u0001\u0005\u0002\u0015]\u0001\"CC\u000f\u0001\u0011E!QHC\u0010\u0011\u001d)9\u0003\u0001C\u0003\u000bS1a!\"\f\u0001\u0001\u0015=\u0002BCC\u001a3\n\u0005\t\u0015!\u0003\u0003v!QQQG-\u0003\u0002\u0003\u0006IA!,\t\u000f\t}\u0014\f\"\u0001\u00068!AQQH-!\u0002\u0013\u0011)\bC\u0004\u0006@e#\t!\"\u0011\t\u000f\u0015\r\u0013\f\"\u0001\u0006B!9!1Q-\u0005\u0002\u0015\u0015\u0003\u0002CC%3\u0002\u0006KAa(\t\u0011\u0015-\u0013\f)Q\u0005\u0005?C\u0001\"\"\u0014ZA\u0003%1\u0011\u0011\u0005\b\u000b\u001fJF\u0011BC)\u0011\u001d)9&\u0017C\u0005\u000b3B\u0001\"\"\u0018ZA\u0003&!Q\u0013\u0005\b\u000b?JF\u0011BC1\u0011\u001d)\u0019'\u0017C\t\u000bKBq!b\u001bZ\t#)i\u0007C\u0004\u0006te#I!\"\u001e\t\u000f\u0015}\u0014\f\"\u0003\u0006\u0002\"9QQR-\u0005\n\u0015=\u0005\u0002CCN3\u0002\u0006Ka!!\b\u000f\u0015u\u0015\f#\u0003\u0006 \u001a9Q1U-\t\n\u0015\u0015\u0006b\u0002B@_\u0012\u0005Q\u0011\u0016\u0005\b\u000bW{G\u0011BCW\u0011\u001d)\u0019l\u001cC\u0005\u000bkCqa!\u000ep\t\u0003*9\fC\u0004\u0003\ff#\t%b/\t\u000f\u0015\u0005\u0017\f\"\u0003\u0006D\"9QQZ-\u0005\n\u0015=\u0007bBCm3\u0012\u0005S1\u001c\u0004\b\r\u000b\u0001\u0011\u0011\u0001D\u0004\u0011)1Y\u0001\u001fB\u0001B\u0003%!q\u0014\u0005\u000b\r\u001bA(\u0011!Q\u0001\n\u0019=\u0001b\u0002B@q\u0012\u0005aQ\u0004\u0005\t\rKA\b\u0015)\u0003\u0003 \"Aaq\u0005=!B\u00131y\u0001\u0003\u0005\u0007*a\u0004\u000b\u0015BBA\u0011!1Y\u0003\u001fQ!\n\tU\u0005\u0002\u0003D\u0017q\u0002\u0006KA!&\t\u0011\u0019=\u0002\u0010)Q\u0005\u0005+C\u0001B\"\ryA\u0013UQ\u0011\t\u0005\t\rgA\b\u0015\"\u0006\u00076!9aq\u0007=\u0005\u0002\u0019e\u0002b\u0002D!q\u0012Ea1\t\u0005\b\r\u0013Bh\u0011\u0003D&\u0011\u001d1\u0019\u0006\u001fC\t\r+BqA\"\u0017y\t\u00131Y\u0006C\u0004\u0007ha$IA\"\u001b\t\u000f\t\r\u0005\u0010\"\u0001\u0007n\u00191a1\u000f\u0001\u0001\rkB1Bb\u0003\u0002\u0018\t\u0005\t\u0015!\u0003\u0003 \"YaQBA\f\u0005\u0003\u0005\u000b\u0011\u0002BP\u0011!\u0011y(a\u0006\u0005\u0002\u0019e\u0004\u0002\u0003B@\u0003/!\tA\"!\t\u0013\u0019\u0015\u0012q\u0003Q\u0005\u000e\u0015\u0005\u0003\"\u0003D\u0014\u0003/\u0001KQBC!\u0011!1I%a\u0006\u0005\u0012\u0019M\u0005\u0002\u0003D-\u0003/!IAb'\t\u0011\u0019\u0015\u0016q\u0003C\u0005\rOC\u0001Ba!\u0002\u0018\u0011\u0005c1V\u0004\t\r_\u000b9\u0002#\u0001\u00072\u001aAaQWA\f\u0011\u000319\f\u0003\u0005\u0003\u0000\u0005=B\u0011\u0001D^\u0011)1i,a\fC\u0002\u0013\u0005aq\u0018\u0005\n\r\u000f\fy\u0003)A\u0005\r\u0003D\u0001B\"3\u00020\u0011\u0005a1\u001a\u0005\t\u0007k\ty\u0003\"\u0011\u0007X\"A!1RA\f\t\u00032YnB\u0004\u0007b\u0002A\tAb9\u0007\u000f\u0019M\u0004\u0001#\u0001\u0007f\"A!qPA \t\u000319\u000f\u0003\u0005\u0003\u0004\u0006}B\u0011\u0001Du\u0011!\u0011\u0019)a\u0010\u0005\u0002\u0019-\b\u0002\u0003BB\u0003\u007f!\tA\"=\u0007\r\u0019]\b\u0001\u0001D}\u0011-1Y!!\u0013\u0003\u0002\u0003\u0006IAa(\t\u0017\u00195\u0011\u0011\nB\u0001B\u0003%1Q \u0005\t\u0005\u007f\nI\u0005\"\u0001\u0007~\"AaQEA%\t\u000b)\t\u0005\u0003\u0005\u0007(\u0005%CQAD\u0003\u0011!1I%!\u0013\u0005R\u001d\u001d\u0001\u0002\u0003BF\u0003\u0013\"\te\"\u0004\u0007\r\u001dM\u0001\u0001AD\u000b\u0011-1)#!\u0017\u0003\u0002\u0003\u0006IA!,\t\u0017\u0019\u001d\u0012\u0011\fB\u0001B\u0003%!Q\u000f\u0005\t\u0005\u007f\nI\u0006\"\u0001\b\u0018!A!1QA-\t\u00039yB\u0002\u0004\b$\u0001\u0001qQ\u0005\u0005\f\rK\t\u0019G!A!\u0002\u0013\u0011y\n\u0003\u0005\u0003\u0000\u0005\rD\u0011AD\u0014\u0011!\u0011\u0019)a\u0019\u0005\u0002\u001d5raBD\u0019\u0001!\u0005q1\u0007\u0004\b\u000fk\u0001\u0001\u0012AD\u001c\u0011!\u0011y(!\u001c\u0005\u0002\u001de\u0002\u0002\u0003BB\u0003[\"\tab\u000f\b\u000f\u001d}\u0002\u0001#\u0001\bB\u00199q1\t\u0001\t\u0002\u001d\u0015\u0003\u0002\u0003B@\u0003k\"\tab\u0012\t\u0011\t\r\u0015Q\u000fC\u0001\u000f\u00132aa\"\u0014\u0001\u0001\u001d=\u0003bCD)\u0003w\u0012\t\u0011)A\u0005\u0005?C1bb\u0015\u0002|\t\u0005\t\u0015!\u0003\u0004~\"A!qPA>\t\u00039)\u0006\u0003\u0007\b^\u0005m\u0004\u0019!A!B\u00139y\u0006\u0003\u0007\bf\u0005m\u0004\u0019!A!B\u001399\u0007\u0003\u0005\bj\u0005mD\u0011BD6\u0011!9i'a\u001f\u0005\n\u001d=\u0004\u0002CD9\u0003w\"\t!\"\u0011\b\u0011\u001dM\u00141\u0010E\u0005\u000fk2\u0001b\"\u001f\u0002|!%q1\u0010\u0005\t\u0005\u007f\ny\t\"\u0001\b~!AqqPAH\t\u00039\t\t\u0003\u0005\b\u000e\u0006mD\u0011BDH\u000f!9)*a\u001f\t\n\u001d]e\u0001CDM\u0003wBIab'\t\u0011\t}\u0014\u0011\u0014C\u0001\u000f;C\u0001bb \u0002\u001a\u0012\u0005qqT\u0004\t\u000fO\u000bY\b#\u0003\b*\u001aAq1VA>\u0011\u00139i\u000b\u0003\u0005\u0003\u0000\u0005\u0005F\u0011ADX\u0011!9y(!)\u0005\u0002\u001dE\u0006\u0002\u0003BB\u0003w\"\ta\".\t\u0011\t-\u00151\u0010C!\u000fs;qab0\u0001\u0011\u00039\tMB\u0004\bD\u0002A\ta\"2\t\u0011\t}\u0014Q\u0016C\u0001\u000f\u000fD\u0001Ba!\u0002.\u0012\u0005q\u0011Z\u0004\b\u000f\u001b\u0004\u0001\u0012ADh\r\u001d9\t\u000e\u0001E\u0001\u000f'D\u0001Ba \u00026\u0012\u0005qQ\u001b\u0005\t\u0005\u0007\u000b)\f\"\u0001\bX\u001a9A1\u0014\u0001\u0002\u0002\u0011u\u0005\u0002\u0003B@\u0003w#\t\u0001\")\t\u0011\u0011\r\u00161\u0018D\t\tKC\u0001Ba!\u0002<\u0012\u0005A\u0011\u0016\u0004\b\t[\u000bY\f\u0002CX\u0011-!9,a1\u0003\u0002\u0003\u0006I\u0001\"/\t\u0011\t}\u00141\u0019C\u0001\twC\u0001\u0002b\u001b\u0002D\u0012\u0005A1\u0019\u0005\f\t\u0013\fY\f#b\u0001\n\u0013!Y\r\u0003\u0005\u0005\u001a\u0005mF\u0011\tCg\r\u00199Y\u000e\u0001\u0001\b^\"Y!QXAh\u0005\u0003\u0005\u000b\u0015\u0002BW\u0011!\u0011y(a4\u0005\u0002\u001d}\u0007\u0002\u0003D\u001c\u0003\u001f$\ta\":\t\u0011\u0011\r\u0016q\u001aC)\u000fW4a\u0001\"&\u0001\u0001\u0011]\u0005b\u0003Ci\u00033\u0014\t\u0011)A\u0005\t'D\u0001Ba \u0002Z\u0012\u0005AQ\u001c\u0005\t\tG\u000bI\u000e\"\u0015\u0005j\u001a1qq\u001e\u0001\u0001\u000fcD1\u0002b.\u0002b\n\u0005\t\u0015!\u0003\bv\"A!qPAq\t\u000399\u0010\u0003\u0005\u0005l\u0005\u0005H\u0011ID\u007f\u0011!\u0011\u0019)!9\u0005B!-aA\u0002E\b\u0001\u0001A\t\u0002C\u0006\t\u001e\u0005-(\u0011!Q\u0001\n!}\u0001\u0002\u0003B@\u0003W$\t\u0001#\n\t\u0015!-\u00121\u001eb\u0001\n\u0003Ai\u0003C\u0005\t6\u0005-\b\u0015!\u0003\t0!AA1NAv\t\u0003B9\u0004\u0003\u0005\u0003\u0004\u0006-H\u0011\tE\u001e\r\u0019Ay\u0004\u0001\u0001\tB!Y\u00012IA}\u0005\u0003\u0005\u000b\u0011\u0002C\b\u0011!\u0011y(!?\u0005\u0002!\u0015\u0003\u0002\u0003C!\u0003s$\t\u0001c\u0013\u0007\r!=\u0003\u0001\u0001E)\u0011-!9L!\u0001\u0003\u0002\u0003\u0006Ia\">\t\u0011\t}$\u0011\u0001C\u0001\u0011+B\u0001Ba!\u0003\u0002\u0011\u0005\u00012L\u0004\b\u0011?\u0002\u0001\u0012\u0001E1\r\u001dA\u0019\u0007\u0001E\u0001\u0011KB\u0001Ba \u0003\f\u0011\u0005\u0001r\r\u0005\t\u0005\u0007\u0013Y\u0001\"\u0001\tj\u001d9\u0001R\u000e\u0001\t\u0002!=da\u0002E9\u0001!\u0005\u00012\u000f\u0005\t\u0005\u007f\u0012\u0019\u0002\"\u0001\tv!A\u0001r\u000fB\n\t\u0013AI\b\u0003\u0005\u0003\u0004\nMA\u0011\u0001E@\u000f\u001dA\u0019\t\u0001E\u0001\u0011\u000b3q\u0001c\"\u0001\u0011\u0003AI\t\u0003\u0005\u0003\u0000\tuA\u0011\u0001EJ\u0011)A)J!\bA\u0002\u0013\u0005\u0001r\u0013\u0005\u000b\u00113\u0013i\u00021A\u0005\u0002!m\u0005\"\u0003EP\u0005;\u0001\u000b\u0015\u0002BK\u0011!\u0011\u0019I!\b\u0005B!\u0005va\u0002ES\u0001!\u0005\u0001r\u0015\u0004\b\u0011S\u0003\u0001\u0012\u0001EV\u0011!\u0011yHa\u000b\u0005\u0002!5\u0006B\u0003EK\u0005W\u0001\r\u0011\"\u0001\t\u0018\"Q\u0001\u0012\u0014B\u0016\u0001\u0004%\t\u0001c,\t\u0013!}%1\u0006Q!\n\tU\u0005\u0002\u0003BB\u0005W!\t\u0001c-\u0003\u0011QK\b/Z'baNTAAa\u000f\u0003>\u0005\u0019A\u000f]3\u000b\t\t}\"\u0011I\u0001\tS:$XM\u001d8bY*!!1\tB#\u0003\u001d\u0011XM\u001a7fGRT!Aa\u0012\u0002\u000bM\u001c\u0017\r\\1\u0014\u0007\u0001\u0011Y\u0005\u0005\u0003\u0003N\t=SB\u0001B#\u0013\u0011\u0011\tF!\u0012\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uI\r\u0001AC\u0001B-!\u0011\u0011iEa\u0017\n\t\tu#Q\t\u0002\u0005+:LG/\u0001\to_Jl\u0017\r\\5{K\u0006c\u0017.Y:fgB\u0019!1M\u0002\u000e\u0003\u0001\u0011\u0001C\\8s[\u0006d\u0017N_3BY&\f7/Z:\u0014\u0007\r\u0011I\u0007E\u0002\u0003di\u0011q\u0001V=qK6\u000b\u0007oE\u0003\u001b\u0005\u0017\u0012y\u0007\u0005\u0005\u0003N\tE$Q\u000fB;\u0013\u0011\u0011\u0019H!\u0012\u0003\u0013\u0019+hn\u0019;j_:\f\u0004\u0003\u0002B2\u0005oJAA!\u001f\u0003|\t!A+\u001f9f\u0013\u0011\u0011iH!\u0010\u0003\u000bQK\b/Z:\u0002\rqJg.\u001b;?)\t\u0011I'A\u0003baBd\u0017\u0010\u0006\u0003\u0003v\t\u001d\u0005b\u0002BE9\u0001\u0007!QO\u0001\u0003iB\fq!\\1q\u001fZ,'\u000f\u0006\u0003\u0003v\t=\u0005b\u0002BE;\u0001\u0007!QO\u0001\u0013M&\u00148\u000f^\"iC:<W\rZ*z[\n|G\u000e\u0006\u0003\u0003\u0016\nm\u0005\u0003\u0002B'\u0005/KAA!'\u0003F\t\u0019\u0011J\u001c;\t\u000f\tue\u00041\u0001\u0003 \u0006AqN]5h'fl7\u000f\u0005\u0004\u0003\"\n\u001d&Q\u0016\b\u0005\u0005\u001b\u0012\u0019+\u0003\u0003\u0003&\n\u0015\u0013a\u00029bG.\fw-Z\u0005\u0005\u0005S\u0013YK\u0001\u0003MSN$(\u0002\u0002BS\u0005\u000b\u0002BAa\u0019\u00030&!!\u0011\u0017BZ\u0005\u0019\u0019\u00160\u001c2pY&!!Q\u0017B\u001f\u0005\u001d\u0019\u00160\u001c2pYN\f\u0011#\u00199qYf$vnU=nE>d\u0017J\u001c4p)\u0019\u0011)Ha/\u0003@\"9!QX\u0010A\u0002\t5\u0016aA:z[\"9!\u0011Y\u0010A\u0002\tU\u0014\u0001B5oM>$BA!2\u0003PB!!1\rBd\u0013\u0011\u0011IMa3\u0003\u000bM\u001bw\u000e]3\n\t\t5'Q\b\u0002\u0007'\u000e|\u0007/Z:\t\u000f\tE\u0007\u00051\u0001\u0003F\u0006)1oY8qKR!!q\u0014Bk\u0011\u001d\u0011i*\ta\u0001\u0005?#BA!7\u0003dB!!1\rBn\u0013\u0011\u0011iNa8\u0003\u001d\u0005sgn\u001c;bi&|g.\u00138g_&!!\u0011\u001dB\u001f\u0005=\teN\\8uCRLwN\\%oM>\u001c\bb\u0002BsE\u0001\u0007!\u0011\\\u0001\u0006C:tw\u000e^\u0001\u0013[\u0006\u0004xJ^3s\u0003:tw\u000e^1uS>t7\u000f\u0006\u0003\u0003l\n5\bC\u0002BQ\u0005O\u0013I\u000eC\u0004\u0003p\u000e\u0002\rAa;\u0002\r\u0005tgn\u001c;t\u0003Ai\u0017\r](wKJ\feN\\8u\u0003J<7\u000f\u0006\u0003\u0003v\u000e\u0005\u0001C\u0002BQ\u0005O\u00139\u0010\u0005\u0003\u0003d\te\u0018\u0002\u0002B~\u0005{\u0014A\u0001\u0016:fK&!!q B\u001f\u0005\u0015!&/Z3t\u0011\u001d\u0019\u0019\u0001\na\u0001\u0005k\fA!\u0019:hgR!!q_B\u0004\u0011\u001d\u0019I!\na\u0001\u0005o\fA\u0001\u001e:fKR1!q_B\u0007\u0007\u001fAqa!\u0003'\u0001\u0004\u00119\u0010C\u0004\u0004\u0012\u0019\u0002\raa\u0005\u0002\r\u001dLg/Z;q!\u0019\u0011ie!\u0006\u0004\u001a%!1q\u0003B#\u0005%1UO\\2uS>t\u0007\u0007\u0005\u0003\u0003N\rm\u0011\u0002BB\u000f\u0005\u000b\u0012qAT8uQ&twM\u0001\nUsB,W*\u00199Ue\u0006t7OZ8s[\u0016\u00148cA\u0014\u0004$A!!1MB\u0013\u0013\u0011\u00199c!\u000b\u0003\u0017Q\u0013\u0018M\\:g_JlWM]\u0005\u0005\u0005\u007f\u001cYC\u0003\u0003\u0004.\t\u0005\u0013aA1qSR\u00111\u0011\u0007\t\u0004\u0007g9S\"\u0001\u000e\u0002\u0013Q\u0014\u0018M\\:g_JlG\u0003\u0002B|\u0007sAqa!\u0003*\u0001\u0004\u00119\u0010\u0006\u0002\u0003bQ!!QOB \u0011\u001d\u0011I)\u0002a\u0001\u0005k\n\u0011\u0003\u001a:paNKgn\u001a7fi>tG+\u001f9f!\r\u0011\u0019g\u0002\u0002\u0012IJ|\u0007oU5oO2,Go\u001c8UsB,7cA\u0004\u0003jQ\u001111\t\u000b\u0005\u0005k\u001ai\u0005C\u0004\u0003\n&\u0001\rA!\u001e\u0002+\u0005\u00147\u000f\u001e:bGR$\u0016\u0010]3t)>\u0014u.\u001e8egB\u0019!1M\u0006\u0003+\u0005\u00147\u000f\u001e:bGR$\u0016\u0010]3t)>\u0014u.\u001e8egN\u00191B!\u001b\u0015\u0005\rEC\u0003\u0002B;\u00077BqA!#\u000e\u0001\u0004\u0011)\bK\u0002\u000e\u0007?\u0002Ba!\u0019\u0004h5\u001111\r\u0006\u0005\u0007K\u0012)%\u0001\u0006b]:|G/\u0019;j_:LAa!\u001b\u0004d\t9A/Y5me\u0016\u001c\u0017\u0001\u00063s_BLE\u000e\\3hC2\u001cF/\u0019:UsB,7\u000fE\u0002\u0003d=\u0011A\u0003\u001a:pa&cG.Z4bYN#\u0018M\u001d+za\u0016\u001c8cA\b\u0003jQ\u00111Q\u000e\u000b\u0005\u0005k\u001a9\bC\u0004\u0003\nF\u0001\rA!\u001e\u0003!\u0005sgn\u001c;bi&|gNR5mi\u0016\u00148c\u0001\n\u0003j\u0005q1.Z3q\u0003:tw\u000e^1uS>tG\u0003BBA\u0007\u000f\u0003BA!\u0014\u0004\u0004&!1Q\u0011B#\u0005\u001d\u0011un\u001c7fC:DqA!:\u0015\u0001\u0004\u0011I\u000e\u0006\u0003\u0003Z\u000e-\u0005b\u0002Bs+\u0001\u0007!\u0011\\\u0001\u000egV\u0004XM\u001d\u0013nCB|e/\u001a:\u0015\t\te7\u0011\u0013\u0005\b\u0005K4\u0002\u0019\u0001Bm\u0005]YU-\u001a9P]2LH+\u001f9f\u0007>t7\u000f\u001e:bS:$8oE\u0003\u0018\u0005S\u001a9\nE\u0002\u0003dI!Ba!!\u0004\u001c\"9!Q]\rA\u0002\te'\u0001\u0005,be&\fgnY3e)f\u0004X-T1q'\rQ#\u0011\u000e\u000b\u0003\u0007G\u00032Aa\u0019+\u0003%yf/\u0019:jC:\u001cW\r\u0005\u0003\u0004*\u000e-VB\u0001B\u001f\u0013\u0011\u0019iK!\u0010\u0003\u0011Y\u000b'/[1oG\u0016\fAB^1sS\u0006t7-Z0%KF$BA!\u0017\u00044\"91QW\u0017A\u0002\r\u001d\u0016!\u0001=\u0002\u0011Y\f'/[1oG\u0016,\"aa*\u0002\u0019]LG\u000f\u001b,be&\fgnY3\u0016\t\r}6q\u0019\u000b\u0005\u0007\u0003\u001ci\u000e\u0006\u0003\u0004D\u000eM\u0007\u0003BBc\u0007\u000fd\u0001\u0001B\u0004\u0004J>\u0012\raa3\u0003\u0003Q\u000bBa!\u0007\u0004NB!!QJBh\u0013\u0011\u0019\tN!\u0012\u0003\u0007\u0005s\u0017\u0010\u0003\u0005\u0004V>\"\t\u0019ABl\u0003\u0011\u0011w\u000eZ=\u0011\r\t53\u0011\\Bb\u0013\u0011\u0019YN!\u0012\u0003\u0011q\u0012\u0017P\\1nKzBqaa80\u0001\u0004\u00199+A\u0001wQ\ry31\u001d\t\u0005\u0005\u001b\u001a)/\u0003\u0003\u0004h\n\u0015#AB5oY&tW-A\u0004gY&\u0004\b/\u001a3\u0016\t\r58\u0011\u001f\u000b\u0005\u0007_\u001c\u0019\u0010\u0005\u0003\u0004F\u000eEHaBBea\t\u000711\u001a\u0005\t\u0007+\u0004D\u00111\u0001\u0004vB1!QJBm\u0007_D3\u0001MBr\u0003-i\u0017\r](wKJ\f%oZ:\u0015\r\ru8q C\u0001!\u0019\u0011\tKa*\u0003v!911A\u0019A\u0002\ru\bb\u0002C\u0002c\u0001\u0007!qT\u0001\biB\f'/Y7t)\u0019\u0011)\bb\u0002\u0005\n!9!Q\u0018\u001aA\u0002\t5\u0006b\u0002Bae\u0001\u0007!Q\u000f\u0002\u000b)f\u0004XMR8mI\u0016\u00148#B\u001a\u0003L\u0011=\u0001\u0003\u0003B'\u0005c\u0012)H!\u0017\u0015\u0005\u0011M\u0001c\u0001B2gQ!!\u0011\fC\f\u0011\u001d\u0011I)\u000ea\u0001\u0005k\n\u0001BZ8mI>3XM\u001d\u000b\u0005\u00053\"i\u0002C\u0004\u0005 Y\u0002\rAa(\u0002\tMLXn\u001d\u000b\u0005\u00053\"\u0019\u0003C\u0004\u0003R^\u0002\rA!2\u0002'\u0019|G\u000eZ(wKJ\feN\\8uCRLwN\\:\u0015\t\teC\u0011\u0006\u0005\b\u0005_D\u0004\u0019\u0001Bv)\u0011\u0011I\u0006\"\f\t\u000f\t\u0015\u0018\b1\u0001\u0003Z\u0006\tbm\u001c7e\u001fZ,'/\u00118o_R\f%oZ:\u0015\t\teC1\u0007\u0005\b\u0007\u0007Q\u0004\u0019\u0001B{)\u0011\u0011I\u0006b\u000e\t\u000f\r%1\b1\u0001\u0003x\niA+\u001f9f)J\fg/\u001a:tKJ\u001c2\u0001\u0010B5)\t!y\u0004E\u0002\u0003dq\n\u0001\u0002\u001e:bm\u0016\u00148/\u001a\u000b\u0005\u00053\")\u0005C\u0004\u0003\nz\u0002\rA!\u001e\u0015\t\tUD\u0011\n\u0005\b\u0005\u0013{\u0004\u0019\u0001B;\u00055!\u0016\u0010]3D_2dWm\u0019;peV!Aq\nC+'\r\u0001E1C\u0001\bS:LG/[1m!\u0011\u0019)\r\"\u0016\u0005\u000f\r%\u0007I1\u0001\u0004LR!A\u0011\fC.!\u0015\u0011\u0019\u0007\u0011C*\u0011\u001d!\tF\u0011a\u0001\t'\naA]3tk2$XC\u0001C*\u0003)\u0011Xm];mi~#S-\u001d\u000b\u0005\u00053\")\u0007C\u0005\u0005h\u0011\u000b\t\u00111\u0001\u0005T\u0005\u0019\u0001\u0010J\u0019\u0002\u000fI,7/\u001e7uA\u000591m\u001c7mK\u000e$H\u0003\u0002C*\t_BqA!#G\u0001\u0004\u0011)(\u0001\tsC^$v.\u0012=jgR,g\u000e^5bYV\u0011!\u0011\u000e\u0002\u0019\u000bbL7\u000f^3oi&\fG.\u0012=ue\u0006\u0004x\u000e\\1uS>t7c\u0001%\u0004$R!A1\u0010C?!\r\u0011\u0019\u0007\u0013\u0005\b\t\u0007Q\u0005\u0019\u0001BP\u0003)y7mY;s\u0007>,h\u000e\u001e\t\t\t\u0007#iI!,\u0003\u00166\u0011AQ\u0011\u0006\u0005\t\u000f#I)A\u0004nkR\f'\r\\3\u000b\t\u0011-%QI\u0001\u000bG>dG.Z2uS>t\u0017\u0002\u0002CH\t\u000b\u0013q\u0001S1tQ6\u000b\u0007/A\u0006b]f\u001cuN\u001c;bS:\u001c\b\u0003\u0002B2\u00033\u0014qcQ8oi\u0006Lgn]!os.+\u0017pQ8mY\u0016\u001cGo\u001c:\u0014\t\u0005eG\u0011\u0014\t\u0005\u0005G\nYL\u0001\fFq&\u001cHo\u001d+za\u0016\u0014VMZ\"pY2,7\r^8s'\u0011\tY\fb(\u0011\u000b\t\r\u0004i!!\u0015\u0005\u0011e\u0015\u0001\u00029sK\u0012$Ba!!\u0005(\"A!QXA`\u0001\u0004\u0011i\u000b\u0006\u0003\u0003Z\u0011-\u0006\u0002\u0003BE\u0003\u0003\u0004\rA!\u001e\u0003'\r{G\u000e\\3di&tw\r\u0016:bm\u0016\u00148/\u001a:\u0014\t\u0005\rG\u0011\u0017\t\u0005\u0005G\"\u0019,\u0003\u0003\u00056\nu(!\u0005$j]\u0012$&/Z3Ue\u00064XM]:fe\u0006\t\u0001\u000f\u0005\u0005\u0003N\tE$q_BA)\u0011!i\f\"1\u0011\t\u0011}\u00161Y\u0007\u0003\u0003wC\u0001\u0002b.\u0002H\u0002\u0007A\u0011\u0018\u000b\u0005\u0007\u0003#)\r\u0003\u0005\u0005H\u0006%\u0007\u0019\u0001B|\u0003\r\t'oZ\u0001\u000bM&tG-\u00138Ue\u0016,WC\u0001C_)\u0011\u0011I\u0006b4\t\u0011\u0011\u001d\u0017Q\u001aa\u0001\u0005o\faa]=n\u001b\u0006\u0004\b\u0007\u0002Ck\t3\u0004\u0002\u0002b!\u0005\u000e\n5Fq\u001b\t\u0005\u0007\u000b$I\u000e\u0002\u0007\u0005\\\u0006m\u0017\u0011!A\u0001\u0006\u0003\u0019YMA\u0002`IE\"B\u0001b%\u0005`\"AA\u0011[Ao\u0001\u0004!\t\u000f\r\u0003\u0005d\u0012\u001d\b\u0003\u0003CB\t\u001b\u0013i\u000b\":\u0011\t\r\u0015Gq\u001d\u0003\r\t7$y.!A\u0001\u0002\u000b\u000511\u001a\u000b\u0005\u0007\u0003#Y\u000f\u0003\u0005\u0005n\u0006}\u0007\u0019\u0001BW\u0003\u0011\u0019\u00180\\\u0019\u0002\u0013\r|WO\u001c;PG\u000e\u001cH\u0003\u0002B-\tgDqA!#N\u0001\u0004\u0011)(A\u0006fqR\u0014\u0018\r]8mCR,G\u0003\u0002B;\tsDqAa\u000fO\u0001\u0004\u0011)\b\u0006\u0003\u0003v\u0011u\bb\u0002BE\u001f\u0002\u0007!Q\u000f\u000b\u0005\u0005k*\t\u0001C\u0004\u0003\nB\u0003\rA!\u001e\u0015\t\t]XQ\u0001\u0005\b\u0007\u0013\t\u0006\u0019\u0001B|\u0003U9\u0018\u000e\u001c3dCJ$W\t\u001f;sCB|G.\u0019;j_:\u00042Aa\u0019T\u0005U9\u0018\u000e\u001c3dCJ$W\t\u001f;sCB|G.\u0019;j_:\u001c2aUBR)\t)I\u0001\u0006\u0003\u0003v\u0015M\u0001b\u0002BE+\u0002\u0007!QO\u0001\u0011SN\u0004vn]:jE2,\u0007K]3gSb$Ba!!\u0006\u001a!9Q1\u0004,A\u0002\t5\u0016!B2mCjT\u0018\u0001D:lSB\u0004&/\u001a4jq>3GCBBA\u000bC))\u0003C\u0004\u0006$]\u0003\rA!\u001e\u0002\u0007A\u0014X\rC\u0004\u0006\u001c]\u0003\rA!,\u0002!9,w/Q:TK\u0016tgI]8n\u001b\u0006\u0004HCBC\u0016\u000b[,y\u000fE\u0002\u0003de\u0013Q\"Q:TK\u0016tgI]8n\u001b\u0006\u00048#B-\u0003j\u0015E\u0002c\u0001B2/\u0005y1/Z3o\rJ|W\u000e\u0015:fM&D\b'A\u0007tK\u0016tgI]8n\u00072\f7o\u001d\u000b\u0007\u000bW)I$b\u000f\t\u000f\u0015MB\f1\u0001\u0003v!9QQ\u0007/A\u0002\t5\u0016AD:fK:4%o\\7Qe\u00164\u0017\u000e_\u0001\u000fG\u0006\u0004H/\u001e:fIB\u000b'/Y7t+\t\u0011y*A\bdCB$XO]3e'.|G.Z7t)\u0011\u0011)(b\u0012\t\u000f\t%\u0005\r1\u0001\u0003v\u0005\u0001rlY1qiV\u0014X\rZ*l_2,Wn]\u0001\u0010?\u000e\f\u0007\u000f^;sK\u0012\u0004\u0016M]1ng\u0006q\u0011n]*uC\ndW\r\u0015:fM&D\u0018aG5t\u0005\u0006\u001cXm\u00117bgN|e-\u00128dY>\u001c\u0018N\\4DY\u0006\u001c8\u000f\u0006\u0003\u0004\u0002\u0016M\u0003bBC+I\u0002\u0007!QV\u0001\u0005E\u0006\u001cX-A\u000ejgRK\b/\u001a)be\u0006lwJZ#oG2|7/\u001b8h\u00072\f7o\u001d\u000b\u0005\u0007\u0003+Y\u0006C\u0004\u0003>\u0016\u0004\rA!,\u0002\u001f\r\f\u0007\u000f^;sK\u0012$\u0006.[:JIN\f!C\\3yi\u000e\u000b\u0007\u000f^;sK\u0012$\u0006.[:JIR\u0011!QS\u0001\fG\u0006\u0004H/\u001e:f)\"L7\u000f\u0006\u0004\u0003v\u0015\u001dT\u0011\u000e\u0005\b\u000bGA\u0007\u0019\u0001B;\u0011\u001d)Y\u0002\u001ba\u0001\u0005[\u000babY1qiV\u0014XmU6pY\u0016l7\u000f\u0006\u0003\u0003Z\u0015=\u0004bBC9S\u0002\u0007!qT\u0001\bg.|G.Z7t\u0003e\u0019wN\u001d:fgB|g\u000eZ5oORK\b/Z!sOVlWM\u001c;\u0015\r\tUTqOC>\u0011\u001d)IH\u001ba\u0001\u0005k\n1\u0001\u001c5t\u0011\u001d)iH\u001ba\u0001\u0005k\n1A\u001d5t\u0003Q\u0019G.Y:t!\u0006\u0014\u0018-\\3uKJ\f5oU3f]R!!QOCB\u0011\u001d))i\u001ba\u0001\u000b\u000f\u000b!b\u00197bgN\u0004\u0016M]1n!\u0011\u0011\u0019'\"#\n\t\u0015-%1\u0010\u0002\b)f\u0004XMU3g\u0003Ui\u0017\r^2iKN\u0004&/\u001a4jq\u0006sGm\u00117bgN$b!\"%\u0006\u0018\u0016eE\u0003BBA\u000b'Cq!\"&m\u0001\u0004\u0011i+A\u0005dC:$\u0017\u000eZ1uK\"9Q1\u00057A\u0002\tU\u0004bBC\u000eY\u0002\u0007!QV\u0001\u0010oJ|G/Z!o]>$\u0018\r^5p]\u0006)\u0012M\u001c8pi\u0006$\u0018n\u001c8Be\u001e\u0014Vm\u001e:ji\u0016\u0014\bcACQ_6\t\u0011LA\u000bb]:|G/\u0019;j_:\f%o\u001a*foJLG/\u001a:\u0014\u0007=,9\u000bE\u0002\u0006\"\u001e\"\"!b(\u0002\u00175\fGo\u00195fgRC\u0017n\u001d\u000b\u0005\u0007\u0003+y\u000bC\u0004\u00062F\u0004\rA!,\u0002\tQD\u0017N_\u0001\b]\u0016<H\u000b[5t)\t\u00119\u0010\u0006\u0003\u0003x\u0016e\u0006bBB\u0005g\u0002\u0007!q\u001f\u000b\u0007\u0005o,i,b0\t\u000f\r%A\u000f1\u0001\u0003x\"91\u0011\u0003;A\u0002\rM\u0011A\u0004;iSN$\u0016\u0010]3BgN+WM\u001c\u000b\u0005\u0005k*)\rC\u0004\u0003\nV\u0004\r!b2\u0011\t\t\rT\u0011Z\u0005\u0005\u000b\u0017\u0014YH\u0001\u0005UQ&\u001cH+\u001f9f\u0003A\u0019\u0018N\\4mKRK\b/Z!t'\u0016,g\u000e\u0006\u0003\u0003v\u0015E\u0007b\u0002BEm\u0002\u0007Q1\u001b\t\u0005\u0005G*).\u0003\u0003\u0006X\nm$AC*j]\u001edW\rV=qK\u0006AAo\\*ue&tw\r\u0006\u0002\u0006^B!Qq\\Cu\u001b\t)\tO\u0003\u0003\u0006d\u0016\u0015\u0018\u0001\u00027b]\u001eT!!b:\u0002\t)\fg/Y\u0005\u0005\u000bW,\tO\u0001\u0004TiJLgn\u001a\u0005\b\u000bGA\u0006\u0019\u0001B;\u0011\u001d)Y\u0002\u0017a\u0001\u0005[C3\u0002WCz\u000bs,Y0b@\u0007\u0002A!!QJC{\u0013\u0011)9P!\u0012\u0003\u0015\u0011,\u0007O]3dCR,G-A\u0004nKN\u001c\u0018mZ3\"\u0005\u0015u\u0018!H;tK\u0002rWm\u001e\u0011BgN+WM\u001c$s_6l\u0015\r\u001d\u0011j]N$X-\u00193\u0002\u000bMLgnY3\"\u0005\u0019\r\u0011A\u0002\u001a/cIr\u0003G\u0001\u0005Tk\n\u001cH/T1q+\u00111IAb\u0005\u0014\u0007a\u0014I'A\u0003ge>l\u0007'A\u0002u_B\u0002bA!)\u0003(\u001aE\u0001\u0003BBc\r'!qa!3y\u0005\u00041)\"\u0005\u0003\u0007\u0018\r5\u0007\u0003\u0002B'\r3IAAb\u0007\u0003F\t!a*\u001e7m)\u00191yB\"\t\u0007$A)!1\r=\u0007\u0012!9a1B>A\u0002\t}\u0005b\u0002D\u0007w\u0002\u0007aqB\u0001\u0005MJ|W.\u0001\u0002u_\u0006\tbM]8n\u0011\u0006\u001cH+\u001a:n'fl'm\u001c7\u0002\u000f\u0019\u0014x.\\'j]\u00069aM]8n\u001b\u0006D\u0018\u0001\u00034s_6\u001c\u0016N_3\u0002\u0015\u0005\u001c7-Z:t\rJ|W.\u0001\u0005bG\u000e,7o\u001d+p+\t1y!A\u0003sKN,G\u000f\u0006\u0004\u0007<\u0019ubqH\u0007\u0002q\"Aa1BA\u0005\u0001\u0004\u0011y\n\u0003\u0005\u0007\u000e\u0005%\u0001\u0019\u0001D\b\u0003\u001di\u0017\r^2iKN$ba!!\u0007F\u0019\u001d\u0003\u0002\u0003B_\u0003\u0017\u0001\rA!,\t\u0011\u00115\u00181\u0002a\u0001\u0005[\u000ba\u0001^8UsB,GC\u0002B;\r\u001b2\t\u0006\u0003\u0005\u0007P\u00055\u0001\u0019\u0001B;\u0003\u00191'o\\7ua\"A!\u0011RA\u0007\u0001\u00041\t\"A\bsK:\fW.\u001a\"pk:$7+_7t)\u0011\u0011)Hb\u0016\t\u0011\t%\u0015q\u0002a\u0001\u0005k\nQa];cgR$\"B!\u001e\u0007^\u0019}c\u0011\rD2\u0011!\u0011I)!\u0005A\u0002\tU\u0004\u0002\u0003B_\u0003#\u0001\rA!,\t\u0011\u0019\u0015\u0012\u0011\u0003a\u0001\u0005?C\u0001Bb\n\u0002\u0012\u0001\u0007aq\u0002\u0015\u0005\u0003#\u0019y&\u0001\u0007ge>l7i\u001c8uC&t7\u000f\u0006\u0003\u0004\u0002\u001a-\u0004\u0002\u0003C\u0010\u0003'\u0001\rAa(\u0015\t\tUdq\u000e\u0005\t\rc\n)\u00021\u0001\u0003v\u0005\u0019A\u000f\u001d\u0019\u0003\u0017M+(m\u001d;Ts6l\u0015\r]\n\u0005\u0003/19\bE\u0003\u0003da\u0014i\u000b\u0006\u0004\u0007|\u0019udq\u0010\t\u0005\u0005G\n9\u0002\u0003\u0005\u0007\f\u0005u\u0001\u0019\u0001BP\u0011!1i!!\bA\u0002\t}E\u0003\u0002D>\r\u0007C\u0001B\"\"\u0002 \u0001\u0007aqQ\u0001\u0006a\u0006L'o\u001d\t\u0007\u0005\u001b2II\"$\n\t\u0019-%Q\t\u0002\u000byI,\u0007/Z1uK\u0012t\u0004\u0003\u0003B'\r\u001f\u0013iK!,\n\t\u0019E%Q\t\u0002\u0007)V\u0004H.\u001a\u001a\u0015\r\tUdQ\u0013DM\u0011!19*!\nA\u0002\tU\u0014a\u00024s_6$\u0006/\u001a\u0005\t\u0005{\u000b)\u00031\u0001\u0003.RA!Q\u0016DO\r?3\t\u000b\u0003\u0005\u0003>\u0006\u001d\u0002\u0019\u0001BW\u0011!1)#a\nA\u0002\t}\u0005\u0002\u0003D\u0014\u0003O\u0001\rAa()\t\u0005\u001d2qL\u0001\tgV\u00147\u000f\u001e$peR!!Q\u0016DU\u0011!\u0011i,!\u000bA\u0002\t5F\u0003\u0002B;\r[C\u0001Ba\u000f\u0002,\u0001\u0007!QO\u0001\u000f[\u0006\u0004HK]3f'fl'm\u001c7t!\u00111\u0019,a\f\u000e\u0005\u0005]!AD7baR\u0013X-Z*z[\n|Gn]\n\u0005\u0003_1I\fE\u0002\u00074\u001e\"\"A\"-\u0002\u0015M$(/[2u\u0007>\u0004\u00180\u0006\u0002\u0007BB!!1\rDb\u0013\u00111)M!@\u0003\u0015Q\u0013X-Z\"pa&,'/A\u0006tiJL7\r^\"paf\u0004\u0013!\u0005;sC:\u001chm\u001c:n\u0013\u001al\u0015\r\u001d9fIR!aQ\u001aDk)\u0011\u00119Pb4\t\u0011\u0019E\u0017q\u0007a\u0001\r'\fQ\u0001\u001e:b]N\u0004\u0002B!\u0014\u0003r\t5&q\u001f\u0005\t\u0007\u0013\t9\u00041\u0001\u0003xR!!q\u001fDm\u0011!\u0019I!!\u000fA\u0002\t]HC\u0002B|\r;4y\u000e\u0003\u0005\u0004\n\u0005m\u0002\u0019\u0001B|\u0011!\u0019\t\"a\u000fA\u0002\rM\u0011aC*vEN$8+_7NCB\u0004BAa\u0019\u0002@M!\u0011q\bB&)\t1\u0019\u000f\u0006\u0002\u0007|Q1a1\u0010Dw\r_D\u0001B\"\n\u0002F\u0001\u0007!q\u0014\u0005\t\rO\t)\u00051\u0001\u0003 R!a1\u0010Dz\u0011!1)0a\u0012A\u0002\u00195\u0015A\u00024s_6$xN\u0001\u0007Tk\n\u001cH\u000fV=qK6\u000b\u0007o\u0005\u0003\u0002J\u0019m\b#\u0002B2q\nUDC\u0002D\u0000\u000f\u00039\u0019\u0001\u0005\u0003\u0003d\u0005%\u0003\u0002\u0003D\u0006\u0003\u001f\u0002\rAa(\t\u0011\u00195\u0011q\na\u0001\u0007{,\"a!@\u0015\r\tUt\u0011BD\u0006\u0011!1y%!\u0016A\u0002\tU\u0004\u0002\u0003BE\u0003+\u0002\rA!\u001e\u0015\r\t]xqBD\t\u0011!\u0019I!a\u0016A\u0002\t]\b\u0002CB\t\u0003/\u0002\raa\u0005\u0003\u0019M+(m\u001d;UQ&\u001cX*\u00199\u0014\t\u0005e#\u0011\u000e\u000b\u0007\u000f39Yb\"\b\u0011\t\t\r\u0014\u0011\f\u0005\t\rK\ty\u00061\u0001\u0003.\"AaqEA0\u0001\u0004\u0011)\b\u0006\u0003\u0003v\u001d\u0005\u0002\u0002\u0003BE\u0003C\u0002\rA!\u001e\u0003!M+(m\u001d;XS2$7-\u0019:e\u001b\u0006\u00048\u0003BA2\u0005S\"Ba\"\u000b\b,A!!1MA2\u0011!1)#a\u001aA\u0002\t}E\u0003\u0002B;\u000f_A\u0001B!#\u0002j\u0001\u0007!QO\u0001\u0015\u0013N$U\r]3oI\u0016tGoQ8mY\u0016\u001cGo\u001c:\u0011\t\t\r\u0014Q\u000e\u0002\u0015\u0013N$U\r]3oI\u0016tGoQ8mY\u0016\u001cGo\u001c:\u0014\t\u00055Dq\u0014\u000b\u0003\u000fg!BA!\u0017\b>!A!\u0011RA9\u0001\u0004\u0011)(A\fBaB\u0014x\u000e_5nCR,G)\u001a9f]\u0012,g\u000e^'baB!!1MA;\u0005]\t\u0005\u000f\u001d:pq&l\u0017\r^3EKB,g\u000eZ3oi6\u000b\u0007o\u0005\u0003\u0002v\t%DCAD!)\u0011\u0011)hb\u0013\t\u0011\t%\u0015\u0011\u0010a\u0001\u0005k\u0012q#\u00138ti\u0006tG/[1uK\u0012+\u0007/\u001a8eK:$X*\u00199\u0014\r\u0005m$\u0011NC\u0019\u0003\u0019\u0001\u0018M]1ng\u0006A\u0011m\u0019;vC2\u001c\b\u0007\u0006\u0004\bX\u001des1\f\t\u0005\u0005G\nY\b\u0003\u0005\bR\u0005\u0005\u0005\u0019\u0001BP\u0011!9\u0019&!!A\u0002\ru\u0018\u0001C0bGR,\u0018\r\\:\u0011\r\t5s\u0011\rB;\u0013\u00119\u0019G!\u0012\u0003\u000b\u0005\u0013(/Y=\u0002\u001b}+\u00070[:uK:$\u0018.\u00197t!\u0019\u0011ie\"\u0019\u0003.\u00069\u0011m\u0019;vC2\u001cXCAD0\u00031)\u00070[:uK:$\u0018.\u00197t+\t99'\u0001\nfq&\u001cH/\u001a8uS\u0006d7OT3fI\u0016$\u0017aC*uC\ndW-\u0011:h)B\u0004Bab\u001e\u0002\u00106\u0011\u00111\u0010\u0002\f'R\f'\r\\3Be\u001e$\u0006o\u0005\u0003\u0002\u0010\n-CCAD;\u0003\u001d)h.\u00199qYf$Bab!\b\nB1!QJDC\u0005kJAab\"\u0003F\t1q\n\u001d;j_:D\u0001bb#\u0002\u0014\u0002\u0007!QV\u0001\u0006a\u0006\u0014\u0018-\\\u0001\u000fKbL7\u000f^3oi&\fGNR8s)\u0011\u0011ik\"%\t\u0011\u001dM\u0015Q\u0013a\u0001\u0005+\u000b1\u0001]5e\u00035)fn\u001d;bE2,\u0017I]4UaB!qqOAM\u00055)fn\u001d;bE2,\u0017I]4UaN!\u0011\u0011\u0014B&)\t99\n\u0006\u0003\b\"\u001e\u0015\u0006C\u0002B'\u000f\u000b;\u0019\u000b\u0005\u0005\u0003N\u0019=%Q\u0016B;\u0011!9Y)!(A\u0002\t5\u0016aD*uC\nLG.\u001b>fI\u0006\u0013x\r\u00169\u0011\t\u001d]\u0014\u0011\u0015\u0002\u0010'R\f'-\u001b7ju\u0016$\u0017I]4UaN!\u0011\u0011\u0015B&)\t9I\u000b\u0006\u0003\b\u0004\u001eM\u0006\u0002CDF\u0003K\u0003\rA!,\u0015\t\tUtq\u0017\u0005\t\u0005\u0013\u000b9\u000b1\u0001\u0003vQ1!q_D^\u000f{C\u0001\u0002b2\u0002*\u0002\u0007!q\u001f\u0005\t\u0007#\tI\u000b1\u0001\u0004\u0014\u0005y\u0011\u000eZ3oi&$\u0018\u0010V=qK6\u000b\u0007\u000f\u0005\u0003\u0003d\u00055&aD5eK:$\u0018\u000e^=UsB,W*\u00199\u0014\t\u00055&\u0011\u000e\u000b\u0003\u000f\u0003$BA!\u001e\bL\"A!\u0011RAY\u0001\u0004\u0011)(\u0001\nusB,g+\u0019:U_>\u0013\u0018nZ5o\u001b\u0006\u0004\b\u0003\u0002B2\u0003k\u0013!\u0003^=qKZ\u000b'\u000fV8Pe&<\u0017N\\'baN!\u0011Q\u0017B5)\t9y\r\u0006\u0003\u0003v\u001de\u0007\u0002\u0003BE\u0003s\u0003\rA!\u001e\u0003#\r{g\u000e^1j]N\u001cu\u000e\u001c7fGR|'o\u0005\u0003\u0002P\u0012eE\u0003BDq\u000fG\u0004BAa\u0019\u0002P\"A!QXAj\u0001\u0004\u0011i\u000b\u0006\u0003\u0003Z\u001d\u001d\b\u0002CDu\u0003+\u0004\rA!,\u0002\t9\u001c\u00180\u001c\u000b\u0005\u0007\u0003;i\u000f\u0003\u0005\u0005n\u0006]\u0007\u0019\u0001BW\u0005M1\u0015\u000e\u001c;feRK\b/Z\"pY2,7\r^8s'\u0011\t\tob=\u0011\u000b\t\r\u0004i!@\u0011\u0011\t5#\u0011\u000fB;\u0007\u0003#Ba\"?\b|B!!1MAq\u0011!!9,!:A\u0002\u001dUH\u0003BD\u0000\u0011\u0013\u0001b\u0001#\u0001\t\b\tUTB\u0001E\u0002\u0015\u0011A)\u0001\"#\u0002\u0013%lW.\u001e;bE2,\u0017\u0002\u0002BU\u0011\u0007A\u0001B!#\u0002h\u0002\u0007!Q\u000f\u000b\u0005\u00053Bi\u0001\u0003\u0005\u0003\n\u0006%\b\u0019\u0001B;\u0005Q\u0019u\u000e\u001c7fGR$\u0016\u0010]3D_2dWm\u0019;peV!\u00012\u0003E\u000e'\u0011\tY\u000f#\u0006\u0011\u000b\t\r\u0004\tc\u0006\u0011\r\t\u0005&q\u0015E\r!\u0011\u0019)\rc\u0007\u0005\u0011\r%\u00171\u001eb\u0001\u0007\u0017\f!\u0001\u001d4\u0011\u0011\t5\u0003\u0012\u0005B;\u00113IA\u0001c\t\u0003F\ty\u0001+\u0019:uS\u0006dg)\u001e8di&|g\u000e\u0006\u0003\t(!%\u0002C\u0002B2\u0003WDI\u0002\u0003\u0005\t\u001e\u0005=\b\u0019\u0001E\u0010\u0003\u0019\u0011WO\u001a4feV\u0011\u0001r\u0006\t\u0007\t\u0007C\t\u0004#\u0007\n\t!MBQ\u0011\u0002\u000b\u0019&\u001cHOQ;gM\u0016\u0014\u0018a\u00022vM\u001a,'\u000f\t\u000b\u0005\u0011/AI\u0004\u0003\u0005\u0003\n\u0006U\b\u0019\u0001B;)\u0011\u0011I\u0006#\u0010\t\u0011\t%\u0015q\u001fa\u0001\u0005k\u0012ACR8s\u000b\u0006\u001c\u0007\u000eV=qKR\u0013\u0018M^3sg\u0016\u00148\u0003BA}\t\u007f\t\u0011A\u001a\u000b\u0005\u0011\u000fBI\u0005\u0005\u0003\u0003d\u0005e\b\u0002\u0003E\"\u0003{\u0004\r\u0001b\u0004\u0015\t\te\u0003R\n\u0005\t\u0005\u0013\u000by\u00101\u0001\u0003v\t\tb)\u001b8e)f\u0004XmQ8mY\u0016\u001cGo\u001c:\u0014\t\t\u0005\u00012\u000b\t\u0006\u0005G\u0002u1\u0011\u000b\u0005\u0011/BI\u0006\u0005\u0003\u0003d\t\u0005\u0001\u0002\u0003C\\\u0005\u000b\u0001\ra\">\u0015\t\te\u0003R\f\u0005\t\u0005\u0013\u00139\u00011\u0001\u0003v\u0005\u0011RI\u001d:p]\u0016|Wo]\"pY2,7\r^8s!\u0011\u0011\u0019Ga\u0003\u0003%\u0015\u0013(o\u001c8f_V\u001c8i\u001c7mK\u000e$xN]\n\u0005\u0005\u0017!y\n\u0006\u0002\tbQ!!\u0011\fE6\u0011!\u0011IIa\u0004A\u0002\tU\u0014\u0001E1eCB$Hk\u001c(foJ+h.T1q!\u0011\u0011\u0019Ga\u0005\u0003!\u0005$\u0017\r\u001d;U_:+wOU;o\u001b\u0006\u00048\u0003\u0002B\n\u0005S\"\"\u0001c\u001c\u0002\u001b\u0005$\u0017\r\u001d;U_:+wOU;o)\u0019\u0011i\u000bc\u001f\t~!AQ1\u0005B\f\u0001\u0004\u0011)\b\u0003\u0005\u0003>\n]\u0001\u0019\u0001BW)\u0011\u0011)\b#!\t\u0011\t%%\u0011\u0004a\u0001\u0005k\nA#\u00168sK2\fG/\u00192mK\u000e{G\u000e\\3di>\u0014\b\u0003\u0002B2\u0005;\u0011A#\u00168sK2\fG/\u00192mK\u000e{G\u000e\\3di>\u00148\u0003\u0002B\u000f\u0011\u0017\u0003bAa\u0019\u0002l\"5\u0005\u0003\u0002B2\u0011\u001fKA\u0001#%\u00034\nQA+\u001f9f'.|G.Z7\u0015\u0005!\u0015\u0015\u0001\u00032be2+g/\u001a7\u0016\u0005\tU\u0015\u0001\u00042be2+g/\u001a7`I\u0015\fH\u0003\u0002B-\u0011;C!\u0002b\u001a\u0003$\u0005\u0005\t\u0019\u0001BK\u0003%\u0011\u0017M\u001d'fm\u0016d\u0007\u0005\u0006\u0003\u0003Z!\r\u0006\u0002\u0003BE\u0005O\u0001\rA!\u001e\u0002)%\u001b(+\u001a7bi\u0006\u0014G.Z\"pY2,7\r^8s!\u0011\u0011\u0019Ga\u000b\u0003)%\u001b(+\u001a7bi\u0006\u0014G.Z\"pY2,7\r^8s'\u0011\u0011Y\u0003b(\u0015\u0005!\u001dF\u0003\u0002B-\u0011cC!\u0002b\u001a\u00032\u0005\u0005\t\u0019\u0001BK)\u0011\u0011I\u0006#.\t\u0011\t%%Q\u0007a\u0001\u0005k\u0002Ba!+\t:&!\u00012\u0018B\u001f\u0005-\u0019\u00160\u001c2pYR\u000b'\r\\3"
)
public interface TypeMaps {
   normalizeAliases$ normalizeAliases();

   dropSingletonType$ dropSingletonType();

   abstractTypesToBounds$ abstractTypesToBounds();

   dropIllegalStarTypes$ dropIllegalStarTypes();

   wildcardExtrapolation$ wildcardExtrapolation();

   SubstSymMap$ SubstSymMap();

   IsDependentCollector$ IsDependentCollector();

   ApproximateDependentMap$ ApproximateDependentMap();

   identityTypeMap$ identityTypeMap();

   typeVarToOriginMap$ typeVarToOriginMap();

   ErroneousCollector$ ErroneousCollector();

   adaptToNewRunMap$ adaptToNewRunMap();

   UnrelatableCollector$ UnrelatableCollector();

   IsRelatableCollector$ IsRelatableCollector();

   // $FF: synthetic method
   static TypeMap rawToExistential$(final TypeMaps $this) {
      return $this.rawToExistential();
   }

   default TypeMap rawToExistential() {
      return (SymbolTable)this.new TypeMap() {
         private Set expanded;
         // $FF: synthetic field
         private final SymbolTable $outer;

         public Types.Type apply(final Types.Type tp) {
            if (tp instanceof Types.TypeRef) {
               Types.TypeRef var2 = (Types.TypeRef)tp;
               Types.Type pre = var2.pre();
               Symbols.Symbol sym = var2.sym();
               List var5 = var2.args();
               if (var5 != null) {
                  List var10000 = .MODULE$.List();
                  if (var10000 == null) {
                     throw null;
                  }

                  List unapplySeq_this = var10000;
                  SeqOps var28 = SeqFactory.unapplySeq$(unapplySeq_this, var5);
                  Object var20 = null;
                  SeqOps var6 = var28;
                  SeqFactory.UnapplySeqWrapper var29 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  SeqFactory.UnapplySeqWrapper var10001 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  new SeqFactory.UnapplySeqWrapper(var6);
                  var29 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  var29 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                  int lengthCompare$extension_len = 0;
                  if (var6.lengthCompare(lengthCompare$extension_len) == 0 && this.$outer.isRawIfWithoutArgs(sym)) {
                     if (this.expanded.contains(sym)) {
                        return this.$outer.definitions().AnyRefTpe();
                     }

                     try {
                        Set var34 = this.expanded;
                        if (var34 == null) {
                           throw null;
                        }

                        this.expanded = (Set)var34.incl(sym);
                        List eparams = this.mapOver(this.$outer.typeParamsToExistentials(sym));
                        SymbolTable var32 = this.$outer;
                        SymbolTable var10002 = this.$outer;
                        Types.Type var10003 = this.apply(pre);
                        if (eparams == null) {
                           throw null;
                        }

                        Object var37;
                        if (eparams == scala.collection.immutable.Nil..MODULE$) {
                           var37 = scala.collection.immutable.Nil..MODULE$;
                        } else {
                           scala.collection.immutable..colon.colon var10004 = new scala.collection.immutable..colon.colon;
                           Symbols.Symbol var17 = (Symbols.Symbol)eparams.head();
                           if (var17 == null) {
                              throw null;
                           }

                           var10004.<init>(var17.tpe_$times(), scala.collection.immutable.Nil..MODULE$);
                           scala.collection.immutable..colon.colon map_h = var10004;
                           scala.collection.immutable..colon.colon map_t = map_h;

                           for(List map_rest = (List)eparams.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                              var10004 = new scala.collection.immutable..colon.colon;
                              var17 = (Symbols.Symbol)map_rest.head();
                              if (var17 == null) {
                                 throw null;
                              }

                              var10004.<init>(var17.tpe_$times(), scala.collection.immutable.Nil..MODULE$);
                              scala.collection.immutable..colon.colon map_nx = var10004;
                              map_t.next_$eq(map_nx);
                              map_t = map_nx;
                           }

                           Statics.releaseFence();
                           var37 = map_h;
                        }

                        Object var23 = null;
                        Object var24 = null;
                        Object var25 = null;
                        Object var26 = null;
                        Object typeRef_args = var37;
                        Types.Type typeRef_pre = var10003;
                        if (var10002 == null) {
                           throw null;
                        }

                        Types.Type var35 = Types.typeRef$(var10002, typeRef_pre, sym, (List)typeRef_args);
                        typeRef_pre = null;
                        typeRef_args = null;
                        var33 = var32.existentialAbstraction(eparams, var35, this.$outer.existentialAbstraction$default$3());
                     } finally {
                        Set var10007 = this.expanded;
                        if (var10007 == null) {
                           throw null;
                        }

                        this.expanded = (Set)var10007.excl(sym);
                     }

                     return var33;
                  }
               }
            }

            return tp.mapOver(this);
         }

         // $FF: synthetic method
         public static final Types.Type $anonfun$apply$3(final Symbols.Symbol x$7) {
            return x$7.tpe();
         }

         public {
            if (TypeMaps.this == null) {
               throw null;
            } else {
               this.$outer = TypeMaps.this;
               this.expanded = (Set)IterableFactory.apply$(scala.collection.immutable.Set..MODULE$, scala.collection.immutable.Nil..MODULE$);
            }
         }
      };
   }

   // $FF: synthetic method
   static boolean isPossiblePrefix$(final TypeMaps $this, final Symbols.Symbol clazz) {
      return $this.isPossiblePrefix(clazz);
   }

   default boolean isPossiblePrefix(final Symbols.Symbol clazz) {
      return clazz.isClass() && !clazz.isPackageClass();
   }

   // $FF: synthetic method
   static boolean skipPrefixOf$(final TypeMaps $this, final Types.Type pre, final Symbols.Symbol clazz) {
      return $this.skipPrefixOf(pre, clazz);
   }

   default boolean skipPrefixOf(final Types.Type pre, final Symbols.Symbol clazz) {
      return pre == ((Types)this).NoType() || pre == ((Types)this).NoPrefix() || !this.isPossiblePrefix(clazz);
   }

   // $FF: synthetic method
   static AsSeenFromMap newAsSeenFromMap$(final TypeMaps $this, final Types.Type pre, final Symbols.Symbol clazz) {
      return $this.newAsSeenFromMap(pre, clazz);
   }

   /** @deprecated */
   default AsSeenFromMap newAsSeenFromMap(final Types.Type pre, final Symbols.Symbol clazz) {
      return (SymbolTable)this.new AsSeenFromMap(pre, clazz);
   }

   static void $init$(final TypeMaps $this) {
   }

   public class normalizeAliases$ extends TypeMap {
      public Types.Type apply(final Types.Type tp) {
         boolean var2 = false;
         Types.TypeRef var3 = null;
         Types.Type var10000;
         if (tp instanceof Types.TypeRef) {
            var2 = true;
            var3 = (Types.TypeRef)tp;
            if (var3.sym().isAliasType() && tp.isHigherKinded()) {
               SymbolTable var8 = this.scala$reflect$internal$tpe$TypeMaps$normalizeAliases$$$outer();
               Function0 var10001 = () -> (new StringBuilder(31)).append("Normalized type alias function ").append(tp).toString();
               Types.Type logResult_result = tp.normalize();
               Function0 logResult_msg = var10001;
               if (var8 == null) {
                  throw null;
               }

               var8.log(SymbolTable::$anonfun$logResult$1);
               var10000 = logResult_result;
               Object var6 = null;
               logResult_result = null;
               return var10000.mapOver(this);
            }
         }

         var10000 = var2 && var3.sym().isAliasType() ? tp.normalize() : tp;
         return var10000.mapOver(this);
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$normalizeAliases$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class dropSingletonType$ extends TypeMap {
      public Types.Type apply(final Types.Type tp) {
         if (tp instanceof Types.TypeRef) {
            Symbols.Symbol var2 = ((Types.TypeRef)tp).sym();
            Symbols.ClassSymbol var10000 = this.scala$reflect$internal$tpe$TypeMaps$dropSingletonType$$$outer().definitions().SingletonClass();
            if (var10000 == null) {
               if (var2 == null) {
                  return this.scala$reflect$internal$tpe$TypeMaps$dropSingletonType$$$outer().definitions().AnyTpe();
               }
            } else if (var10000.equals(var2)) {
               return this.scala$reflect$internal$tpe$TypeMaps$dropSingletonType$$$outer().definitions().AnyTpe();
            }
         }

         if (!(tp instanceof Types.RefinedType)) {
            return tp.mapOver(this);
         } else {
            Types.RefinedType var3 = (Types.RefinedType)tp;
            List parents = var3.parents();
            Scopes.Scope decls = var3.decls();
            if (parents == null) {
               throw null;
            } else {
               boolean filter_filterCommon_isFlipped = false;
               List filter_filterCommon_noneIn$1_l = parents;

               Object var60;
               while(true) {
                  if (filter_filterCommon_noneIn$1_l.isEmpty()) {
                     var60 = scala.collection.immutable.Nil..MODULE$;
                     break;
                  }

                  Object filter_filterCommon_noneIn$1_h = filter_filterCommon_noneIn$1_l.head();
                  List filter_filterCommon_noneIn$1_t = (List)filter_filterCommon_noneIn$1_l.tail();
                  Types.Type var25 = (Types.Type)filter_filterCommon_noneIn$1_h;
                  if ($anonfun$apply$2(this, var25) != filter_filterCommon_isFlipped) {
                     List filter_filterCommon_noneIn$1_allIn$1_remaining = filter_filterCommon_noneIn$1_t;

                     while(true) {
                        if (filter_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                           var60 = filter_filterCommon_noneIn$1_l;
                           break;
                        }

                        Object filter_filterCommon_noneIn$1_allIn$1_x = filter_filterCommon_noneIn$1_allIn$1_remaining.head();
                        var25 = (Types.Type)filter_filterCommon_noneIn$1_allIn$1_x;
                        if ($anonfun$apply$2(this, var25) == filter_filterCommon_isFlipped) {
                           scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_l.head(), scala.collection.immutable.Nil..MODULE$);
                           List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filter_filterCommon_noneIn$1_l.tail();

                           scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                           for(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filter_filterCommon_noneIn$1_allIn$1_remaining; filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                              scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), scala.collection.immutable.Nil..MODULE$);
                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                           }

                           List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_remaining.tail();
                           List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                           while(!filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                              Object filter_filterCommon_noneIn$1_allIn$1_partialFill$1_head = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                              var25 = (Types.Type)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_head;
                              if ($anonfun$apply$2(this, var25) != filter_filterCommon_isFlipped) {
                                 filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              } else {
                                 while(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                    scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), scala.collection.immutable.Nil..MODULE$);
                                    filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                    filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                                    filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                                 }

                                 filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                                 filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              }
                           }

                           if (!filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                           }

                           var60 = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                           Object var34 = null;
                           Object var37 = null;
                           Object var40 = null;
                           Object var43 = null;
                           Object var46 = null;
                           Object var49 = null;
                           Object var52 = null;
                           Object var55 = null;
                           break;
                        }

                        filter_filterCommon_noneIn$1_allIn$1_remaining = (List)filter_filterCommon_noneIn$1_allIn$1_remaining.tail();
                     }

                     Object var30 = null;
                     Object var32 = null;
                     Object var35 = null;
                     Object var38 = null;
                     Object var41 = null;
                     Object var44 = null;
                     Object var47 = null;
                     Object var50 = null;
                     Object var53 = null;
                     Object var56 = null;
                     break;
                  }

                  filter_filterCommon_noneIn$1_l = filter_filterCommon_noneIn$1_t;
               }

               Object var27 = null;
               Object var28 = null;
               Object var29 = null;
               Object var31 = null;
               Object var33 = null;
               Object var36 = null;
               Object var39 = null;
               Object var42 = null;
               Object var45 = null;
               Object var48 = null;
               Object var51 = null;
               Object var54 = null;
               Object var57 = null;
               List filter_filterCommon_result = (List)var60;
               Statics.releaseFence();
               var60 = filter_filterCommon_result;
               filter_filterCommon_result = null;
               Object var6 = var60;
               if (scala.collection.immutable.Nil..MODULE$.equals(var6)) {
                  return this.scala$reflect$internal$tpe$TypeMaps$dropSingletonType$$$outer().definitions().AnyTpe();
               } else {
                  if (var6 instanceof scala.collection.immutable..colon.colon) {
                     scala.collection.immutable..colon.colon var7 = (scala.collection.immutable..colon.colon)var6;
                     Types.Type p = (Types.Type)var7.head();
                     List var9 = var7.next$access$1();
                     if (scala.collection.immutable.Nil..MODULE$.equals(var9) && decls.isEmpty()) {
                        return p.mapOver(this);
                     }
                  }

                  return this.scala$reflect$internal$tpe$TypeMaps$dropSingletonType$$$outer().copyRefinedType(var3, (List)var6, decls, this.scala$reflect$internal$tpe$TypeMaps$dropSingletonType$$$outer().copyRefinedType$default$4()).mapOver(this);
               }
            }
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$dropSingletonType$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$apply$2(final dropSingletonType$ $this, final Types.Type x$1) {
         Symbols.Symbol var10000 = x$1.typeSymbol();
         Symbols.ClassSymbol var2 = $this.scala$reflect$internal$tpe$TypeMaps$dropSingletonType$$$outer().definitions().SingletonClass();
         if (var10000 == null) {
            if (var2 != null) {
               return true;
            }
         } else if (!var10000.equals(var2)) {
            return true;
         }

         return false;
      }

      // $FF: synthetic method
      public static final Object $anonfun$apply$2$adapted(final dropSingletonType$ $this, final Types.Type x$1) {
         return BoxesRunTime.boxToBoolean($anonfun$apply$2($this, x$1));
      }
   }

   public class abstractTypesToBounds$ extends TypeMap {
      public Types.Type apply(final Types.Type tp) {
         while(true) {
            boolean var2 = false;
            Types.TypeRef var3 = null;
            if (tp instanceof Types.TypeRef) {
               var2 = true;
               var3 = (Types.TypeRef)tp;
               if (var3.sym().isAliasType()) {
                  tp = tp.dealias();
                  continue;
               }
            }

            if (!var2 || !var3.sym().isAbstractType()) {
               if (!(tp instanceof Types.RefinedType)) {
                  if (tp instanceof Types.AnnotatedType) {
                     return tp.mapOver(this);
                  }

                  return tp;
               }

               Types.RefinedType var4 = (Types.RefinedType)tp;
               List parents = var4.parents();
               Scopes.Scope decls = var4.decls();
               SymbolTable var10000 = this.scala$reflect$internal$tpe$TypeMaps$abstractTypesToBounds$$$outer();
               if (parents == null) {
                  throw null;
               }

               List mapConserve_loop$3_pending = parents;
               List mapConserve_loop$3_unchanged = parents;
               scala.collection.immutable..colon.colon mapConserve_loop$3_mappedLast = null;
               List mapConserve_loop$3_mappedHead = null;

               while(!mapConserve_loop$3_pending.isEmpty()) {
                  Object mapConserve_loop$3_head0 = mapConserve_loop$3_pending.head();
                  Object mapConserve_loop$3_head1 = this.apply((Types.Type)mapConserve_loop$3_head0);
                  if (mapConserve_loop$3_head1 == mapConserve_loop$3_head0) {
                     mapConserve_loop$3_pending = (List)mapConserve_loop$3_pending.tail();
                     mapConserve_loop$3_unchanged = mapConserve_loop$3_unchanged;
                     mapConserve_loop$3_mappedLast = mapConserve_loop$3_mappedLast;
                     mapConserve_loop$3_mappedHead = mapConserve_loop$3_mappedHead;
                  } else {
                     List mapConserve_loop$3_xc = mapConserve_loop$3_unchanged;
                     List mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_mappedHead;

                     scala.collection.immutable..colon.colon mapConserve_loop$3_mappedLast1;
                     for(mapConserve_loop$3_mappedLast1 = mapConserve_loop$3_mappedLast; mapConserve_loop$3_xc != mapConserve_loop$3_pending; mapConserve_loop$3_xc = (List)mapConserve_loop$3_xc.tail()) {
                        scala.collection.immutable..colon.colon mapConserve_loop$3_next = new scala.collection.immutable..colon.colon(mapConserve_loop$3_xc.head(), scala.collection.immutable.Nil..MODULE$);
                        if (mapConserve_loop$3_mappedHead1 == null) {
                           mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_next;
                        }

                        if (mapConserve_loop$3_mappedLast1 != null) {
                           mapConserve_loop$3_mappedLast1.next_$eq(mapConserve_loop$3_next);
                        }

                        mapConserve_loop$3_mappedLast1 = mapConserve_loop$3_next;
                     }

                     scala.collection.immutable..colon.colon mapConserve_loop$3_next = new scala.collection.immutable..colon.colon(mapConserve_loop$3_head1, scala.collection.immutable.Nil..MODULE$);
                     if (mapConserve_loop$3_mappedHead1 == null) {
                        mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_next;
                     }

                     if (mapConserve_loop$3_mappedLast1 != null) {
                        mapConserve_loop$3_mappedLast1.next_$eq(mapConserve_loop$3_next);
                     }

                     List mapConserve_loop$3_tail0 = (List)mapConserve_loop$3_pending.tail();
                     mapConserve_loop$3_pending = mapConserve_loop$3_tail0;
                     mapConserve_loop$3_unchanged = mapConserve_loop$3_tail0;
                     mapConserve_loop$3_mappedLast = mapConserve_loop$3_next;
                     mapConserve_loop$3_mappedHead = mapConserve_loop$3_mappedHead1;
                  }
               }

               Object var10002;
               if (mapConserve_loop$3_mappedHead == null) {
                  var10002 = mapConserve_loop$3_unchanged;
               } else {
                  mapConserve_loop$3_mappedLast.next_$eq(mapConserve_loop$3_unchanged);
                  var10002 = mapConserve_loop$3_mappedHead;
               }

               mapConserve_loop$3_mappedHead = null;
               Object var22 = null;
               Object var23 = null;
               Object var24 = null;
               Object var25 = null;
               Object var26 = null;
               Object var27 = null;
               Object var28 = null;
               Object var29 = null;
               Object var30 = null;
               Object var31 = null;
               Object var32 = null;
               List mapConserve_result = (List)var10002;
               Statics.releaseFence();
               var10002 = mapConserve_result;
               mapConserve_result = null;
               return var10000.copyRefinedType(var4, (List)var10002, decls, this.scala$reflect$internal$tpe$TypeMaps$abstractTypesToBounds$$$outer().copyRefinedType$default$4());
            }

            tp = tp.upperBound();
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$abstractTypesToBounds$$$outer() {
         return this.$outer;
      }
   }

   public class dropIllegalStarTypes$ extends TypeMap {
      public Types.Type apply(final Types.Type tp) {
         if (tp instanceof Types.MethodType) {
            Types.MethodType var2 = (Types.MethodType)tp;
            List params = var2.params();
            Types.Type restpe = var2.resultType();
            Types.Type restpe1 = this.apply(restpe);
            return (Types.Type)(restpe == restpe1 ? tp : this.scala$reflect$internal$tpe$TypeMaps$dropIllegalStarTypes$$$outer().new MethodType(params, restpe1));
         } else {
            if (tp instanceof Types.TypeRef) {
               Types.TypeRef var6 = (Types.TypeRef)tp;
               Symbols.Symbol var7 = var6.sym();
               List var8 = var6.args();
               Symbols.ClassSymbol var10000 = this.scala$reflect$internal$tpe$TypeMaps$dropIllegalStarTypes$$$outer().definitions().RepeatedParamClass();
               if (var10000 == null) {
                  if (var7 != null) {
                     return tp.mapOver(this);
                  }
               } else if (!var10000.equals(var7)) {
                  return tp.mapOver(this);
               }

               if (var8 instanceof scala.collection.immutable..colon.colon) {
                  scala.collection.immutable..colon.colon var9 = (scala.collection.immutable..colon.colon)var8;
                  Types.Type arg = (Types.Type)var9.head();
                  List var11 = var9.next$access$1();
                  if (scala.collection.immutable.Nil..MODULE$.equals(var11)) {
                     return this.scala$reflect$internal$tpe$TypeMaps$dropIllegalStarTypes$$$outer().definitions().seqType(arg);
                  }
               }
            }

            return tp.mapOver(this);
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$dropIllegalStarTypes$$$outer() {
         return this.$outer;
      }
   }

   public interface AnnotationFilter {
      // $FF: synthetic method
      AnnotationInfos.AnnotationInfo scala$reflect$internal$tpe$TypeMaps$AnnotationFilter$$super$mapOver(final AnnotationInfos.AnnotationInfo annot);

      boolean keepAnnotation(final AnnotationInfos.AnnotationInfo annot);

      default AnnotationInfos.AnnotationInfo mapOver(final AnnotationInfos.AnnotationInfo annot) {
         return (AnnotationInfos.AnnotationInfo)(this.keepAnnotation(annot) ? this.scala$reflect$internal$tpe$TypeMaps$AnnotationFilter$$super$mapOver(annot) : ((AnnotationInfos)this.scala$reflect$internal$tpe$TypeMaps$AnnotationFilter$$$outer()).UnmappableAnnotation());
      }

      // $FF: synthetic method
      TypeMaps scala$reflect$internal$tpe$TypeMaps$AnnotationFilter$$$outer();

      static void $init$(final AnnotationFilter $this) {
      }
   }

   public interface KeepOnlyTypeConstraints extends AnnotationFilter {
      default boolean keepAnnotation(final AnnotationInfos.AnnotationInfo annot) {
         return annot.matches(((Definitions)this.scala$reflect$internal$tpe$TypeMaps$KeepOnlyTypeConstraints$$$outer()).definitions().TypeConstraintClass());
      }

      // $FF: synthetic method
      TypeMaps scala$reflect$internal$tpe$TypeMaps$KeepOnlyTypeConstraints$$$outer();

      static void $init$(final KeepOnlyTypeConstraints $this) {
      }
   }

   public abstract class TypeMap implements Function1 {
      // $FF: synthetic field
      public final SymbolTable $outer;

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

      public String toString() {
         return Function1.toString$(this);
      }

      public abstract Types.Type apply(final Types.Type tp);

      public Types.Type mapOver(final Types.Type tp) {
         return tp == null ? null : tp.mapOver(this);
      }

      private int firstChangedSymbol(final List origSyms) {
         return this.loop$1(0, origSyms);
      }

      public Types.Type applyToSymbolInfo(final Symbols.Symbol sym, final Types.Type info) {
         return this.apply(info);
      }

      public Scopes.Scope mapOver(final Scopes.Scope scope) {
         List elems = scope.toList();
         List elems1 = this.mapOver(elems);
         return elems1 == elems ? scope : this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer().newScopeWith(elems1);
      }

      public List mapOver(final List origSyms) {
         int firstChange = this.firstChangedSymbol(origSyms);
         if (firstChange < 0) {
            return origSyms;
         } else {
            List cloned = this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer().cloneSymbols(origSyms);
            if (cloned == null) {
               throw null;
            } else {
               List var10000 = (List)StrictOptimizedLinearSeqOps.drop$(cloned, firstChange);
               if (var10000 == null) {
                  throw null;
               } else {
                  for(List foreach_these = var10000; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
                     ((Symbols.Symbol)foreach_these.head()).modifyInfo(this);
                  }

                  return cloned;
               }
            }
         }
      }

      public AnnotationInfos.AnnotationInfo mapOver(final AnnotationInfos.AnnotationInfo annot) {
         if (annot != null) {
            Some var2 = this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer().AnnotationInfo().unapply(annot);
            if (!var2.isEmpty()) {
               Types.Type atp = (Types.Type)((Tuple3)var2.value())._1();
               List args = (List)((Tuple3)var2.value())._2();
               List assocs = (List)((Tuple3)var2.value())._3();
               Types.Type atp1 = atp.mapOver(this);
               List args1 = this.mapOverAnnotArgs(args);
               if (args == args1 && atp == atp1) {
                  return annot;
               }

               if (args1.isEmpty() && args.nonEmpty()) {
                  return this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer().UnmappableAnnotation();
               }

               return this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer().AnnotationInfo().apply(atp1, args1, assocs).setPos(annot.pos());
            }
         }

         throw new MatchError(annot);
      }

      public List mapOverAnnotations(final List annots) {
         if (annots == null) {
            throw null;
         } else {
            List mapConserve_loop$3_pending = annots;
            List mapConserve_loop$3_unchanged = annots;
            scala.collection.immutable..colon.colon mapConserve_loop$3_mappedLast = null;
            List mapConserve_loop$3_mappedHead = null;

            while(!mapConserve_loop$3_pending.isEmpty()) {
               Object mapConserve_loop$3_head0 = mapConserve_loop$3_pending.head();
               AnnotationInfos.AnnotationInfo var31 = (AnnotationInfos.AnnotationInfo)mapConserve_loop$3_head0;
               Object mapConserve_loop$3_head1 = this.mapOver(var31);
               if (mapConserve_loop$3_head1 == mapConserve_loop$3_head0) {
                  mapConserve_loop$3_pending = (List)mapConserve_loop$3_pending.tail();
                  mapConserve_loop$3_unchanged = mapConserve_loop$3_unchanged;
                  mapConserve_loop$3_mappedLast = mapConserve_loop$3_mappedLast;
                  mapConserve_loop$3_mappedHead = mapConserve_loop$3_mappedHead;
               } else {
                  List mapConserve_loop$3_xc = mapConserve_loop$3_unchanged;
                  List mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_mappedHead;

                  scala.collection.immutable..colon.colon mapConserve_loop$3_mappedLast1;
                  for(mapConserve_loop$3_mappedLast1 = mapConserve_loop$3_mappedLast; mapConserve_loop$3_xc != mapConserve_loop$3_pending; mapConserve_loop$3_xc = (List)mapConserve_loop$3_xc.tail()) {
                     scala.collection.immutable..colon.colon mapConserve_loop$3_next = new scala.collection.immutable..colon.colon(mapConserve_loop$3_xc.head(), scala.collection.immutable.Nil..MODULE$);
                     if (mapConserve_loop$3_mappedHead1 == null) {
                        mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_next;
                     }

                     if (mapConserve_loop$3_mappedLast1 != null) {
                        mapConserve_loop$3_mappedLast1.next_$eq(mapConserve_loop$3_next);
                     }

                     mapConserve_loop$3_mappedLast1 = mapConserve_loop$3_next;
                  }

                  scala.collection.immutable..colon.colon mapConserve_loop$3_next = new scala.collection.immutable..colon.colon(mapConserve_loop$3_head1, scala.collection.immutable.Nil..MODULE$);
                  if (mapConserve_loop$3_mappedHead1 == null) {
                     mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_next;
                  }

                  if (mapConserve_loop$3_mappedLast1 != null) {
                     mapConserve_loop$3_mappedLast1.next_$eq(mapConserve_loop$3_next);
                  }

                  List mapConserve_loop$3_tail0 = (List)mapConserve_loop$3_pending.tail();
                  mapConserve_loop$3_pending = mapConserve_loop$3_tail0;
                  mapConserve_loop$3_unchanged = mapConserve_loop$3_tail0;
                  mapConserve_loop$3_mappedLast = mapConserve_loop$3_next;
                  mapConserve_loop$3_mappedHead = mapConserve_loop$3_mappedHead1;
               }
            }

            Object var10000;
            if (mapConserve_loop$3_mappedHead == null) {
               var10000 = mapConserve_loop$3_unchanged;
            } else {
               mapConserve_loop$3_mappedLast.next_$eq(mapConserve_loop$3_unchanged);
               var10000 = mapConserve_loop$3_mappedHead;
            }

            mapConserve_loop$3_mappedHead = null;
            Object var35 = null;
            Object var36 = null;
            Object var37 = null;
            Object var38 = null;
            Object var39 = null;
            Object var40 = null;
            Object var41 = null;
            Object var42 = null;
            Object var43 = null;
            Object var44 = null;
            Object var45 = null;
            List mapConserve_result = (List)var10000;
            Statics.releaseFence();
            var10000 = mapConserve_result;
            mapConserve_result = null;
            List annots1 = (List)var10000;
            if (annots1 == annots) {
               return annots;
            } else if (annots1 == null) {
               throw null;
            } else {
               boolean filterNot_filterCommon_isFlipped = true;
               List filterNot_filterCommon_noneIn$1_l = annots1;

               while(true) {
                  if (filterNot_filterCommon_noneIn$1_l.isEmpty()) {
                     var10000 = scala.collection.immutable.Nil..MODULE$;
                     break;
                  }

                  Object filterNot_filterCommon_noneIn$1_h = filterNot_filterCommon_noneIn$1_l.head();
                  List filterNot_filterCommon_noneIn$1_t = (List)filterNot_filterCommon_noneIn$1_l.tail();
                  AnnotationInfos.AnnotationInfo var32 = (AnnotationInfos.AnnotationInfo)filterNot_filterCommon_noneIn$1_h;
                  if ($anonfun$mapOverAnnotations$2(this, var32) != filterNot_filterCommon_isFlipped) {
                     List filterNot_filterCommon_noneIn$1_allIn$1_remaining = filterNot_filterCommon_noneIn$1_t;

                     while(true) {
                        if (filterNot_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                           var10000 = filterNot_filterCommon_noneIn$1_l;
                           break;
                        }

                        Object filterNot_filterCommon_noneIn$1_allIn$1_x = filterNot_filterCommon_noneIn$1_allIn$1_remaining.head();
                        var32 = (AnnotationInfos.AnnotationInfo)filterNot_filterCommon_noneIn$1_allIn$1_x;
                        if ($anonfun$mapOverAnnotations$2(this, var32) == filterNot_filterCommon_isFlipped) {
                           scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(filterNot_filterCommon_noneIn$1_l.head(), scala.collection.immutable.Nil..MODULE$);
                           List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_l.tail();

                           scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                           for(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filterNot_filterCommon_noneIn$1_allIn$1_remaining; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                              scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), scala.collection.immutable.Nil..MODULE$);
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                           }

                           List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                           List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                           while(!filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                              Object filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                              var32 = (AnnotationInfos.AnnotationInfo)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head;
                              if ($anonfun$mapOverAnnotations$2(this, var32) != filterNot_filterCommon_isFlipped) {
                                 filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              } else {
                                 while(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                    scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), scala.collection.immutable.Nil..MODULE$);
                                    filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                    filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                                    filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                                 }

                                 filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                                 filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              }
                           }

                           if (!filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                           }

                           var10000 = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                           Object var53 = null;
                           Object var56 = null;
                           Object var59 = null;
                           Object var62 = null;
                           Object var65 = null;
                           Object var68 = null;
                           Object var71 = null;
                           Object var74 = null;
                           break;
                        }

                        filterNot_filterCommon_noneIn$1_allIn$1_remaining = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                     }

                     Object var49 = null;
                     Object var51 = null;
                     Object var54 = null;
                     Object var57 = null;
                     Object var60 = null;
                     Object var63 = null;
                     Object var66 = null;
                     Object var69 = null;
                     Object var72 = null;
                     Object var75 = null;
                     break;
                  }

                  filterNot_filterCommon_noneIn$1_l = filterNot_filterCommon_noneIn$1_t;
               }

               filterNot_filterCommon_noneIn$1_l = null;
               Object var47 = null;
               Object var48 = null;
               Object var50 = null;
               Object var52 = null;
               Object var55 = null;
               Object var58 = null;
               Object var61 = null;
               Object var64 = null;
               Object var67 = null;
               Object var70 = null;
               Object var73 = null;
               Object var76 = null;
               List filterNot_filterCommon_result = (List)var10000;
               Statics.releaseFence();
               return filterNot_filterCommon_result;
            }
         }
      }

      public List mapOverAnnotArgs(final List args) {
         if (args == null) {
            throw null;
         } else {
            List mapConserve_loop$3_pending = args;
            List mapConserve_loop$3_unchanged = args;
            scala.collection.immutable..colon.colon mapConserve_loop$3_mappedLast = null;
            List mapConserve_loop$3_mappedHead = null;

            while(!mapConserve_loop$3_pending.isEmpty()) {
               Object mapConserve_loop$3_head0 = mapConserve_loop$3_pending.head();
               Trees.Tree var16 = (Trees.Tree)mapConserve_loop$3_head0;
               Object mapConserve_loop$3_head1 = this.mapOver(var16);
               if (mapConserve_loop$3_head1 == mapConserve_loop$3_head0) {
                  mapConserve_loop$3_pending = (List)mapConserve_loop$3_pending.tail();
                  mapConserve_loop$3_unchanged = mapConserve_loop$3_unchanged;
                  mapConserve_loop$3_mappedLast = mapConserve_loop$3_mappedLast;
                  mapConserve_loop$3_mappedHead = mapConserve_loop$3_mappedHead;
               } else {
                  List mapConserve_loop$3_xc = mapConserve_loop$3_unchanged;
                  List mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_mappedHead;

                  scala.collection.immutable..colon.colon mapConserve_loop$3_mappedLast1;
                  for(mapConserve_loop$3_mappedLast1 = mapConserve_loop$3_mappedLast; mapConserve_loop$3_xc != mapConserve_loop$3_pending; mapConserve_loop$3_xc = (List)mapConserve_loop$3_xc.tail()) {
                     scala.collection.immutable..colon.colon mapConserve_loop$3_next = new scala.collection.immutable..colon.colon(mapConserve_loop$3_xc.head(), scala.collection.immutable.Nil..MODULE$);
                     if (mapConserve_loop$3_mappedHead1 == null) {
                        mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_next;
                     }

                     if (mapConserve_loop$3_mappedLast1 != null) {
                        mapConserve_loop$3_mappedLast1.next_$eq(mapConserve_loop$3_next);
                     }

                     mapConserve_loop$3_mappedLast1 = mapConserve_loop$3_next;
                  }

                  scala.collection.immutable..colon.colon mapConserve_loop$3_next = new scala.collection.immutable..colon.colon(mapConserve_loop$3_head1, scala.collection.immutable.Nil..MODULE$);
                  if (mapConserve_loop$3_mappedHead1 == null) {
                     mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_next;
                  }

                  if (mapConserve_loop$3_mappedLast1 != null) {
                     mapConserve_loop$3_mappedLast1.next_$eq(mapConserve_loop$3_next);
                  }

                  List mapConserve_loop$3_tail0 = (List)mapConserve_loop$3_pending.tail();
                  mapConserve_loop$3_pending = mapConserve_loop$3_tail0;
                  mapConserve_loop$3_unchanged = mapConserve_loop$3_tail0;
                  mapConserve_loop$3_mappedLast = mapConserve_loop$3_next;
                  mapConserve_loop$3_mappedHead = mapConserve_loop$3_mappedHead1;
               }
            }

            Object var10000;
            if (mapConserve_loop$3_mappedHead == null) {
               var10000 = mapConserve_loop$3_unchanged;
            } else {
               mapConserve_loop$3_mappedLast.next_$eq(mapConserve_loop$3_unchanged);
               var10000 = mapConserve_loop$3_mappedHead;
            }

            mapConserve_loop$3_mappedHead = null;
            Object var19 = null;
            Object var20 = null;
            Object var21 = null;
            Object var22 = null;
            Object var23 = null;
            Object var24 = null;
            Object var25 = null;
            Object var26 = null;
            Object var27 = null;
            Object var28 = null;
            Object var29 = null;
            List mapConserve_result = (List)var10000;
            Statics.releaseFence();
            var10000 = mapConserve_result;
            mapConserve_result = null;
            List args1 = (List)var10000;
            if (args1.contains(this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer().UnmappableTree())) {
               return scala.collection.immutable.Nil..MODULE$;
            } else {
               return args1;
            }
         }
      }

      public Trees.Tree mapOver(final Trees.Tree tree) {
         Object var2 = new Object();

         try {
            return this.mapOver(tree, () -> {
               throw new NonLocalReturnControl(var2, this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer().UnmappableTree());
            });
         } catch (NonLocalReturnControl var4) {
            if (var4.key() == var2) {
               return (Trees.Tree)var4.value();
            } else {
               throw var4;
            }
         }
      }

      public Trees.Tree mapOver(final Trees.Tree tree, final Function0 giveup) {
         return (new TypeMapTransformer()).transform(tree);
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer() {
         return this.$outer;
      }

      private final int loop$1(final int i, final List syms) {
         while(true) {
            if (syms instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var3 = (scala.collection.immutable..colon.colon)syms;
               Symbols.Symbol x = (Symbols.Symbol)var3.head();
               List xs = var3.next$access$1();
               Types.Type info = x.info();
               if (this.applyToSymbolInfo(x, info) == info) {
                  int var10000 = i + 1;
                  syms = xs;
                  i = var10000;
                  continue;
               }

               return i;
            }

            return -1;
         }
      }

      // $FF: synthetic method
      public static final Symbols.Symbol $anonfun$mapOver$1(final TypeMap $this, final Symbols.Symbol x$2) {
         return x$2.modifyInfo($this);
      }

      // $FF: synthetic method
      public static final AnnotationInfos.AnnotationInfo $anonfun$mapOverAnnotations$1(final TypeMap $this, final AnnotationInfos.AnnotationInfo annot) {
         return $this.mapOver(annot);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$mapOverAnnotations$2(final TypeMap $this, final AnnotationInfos.AnnotationInfo x$4) {
         return x$4 == $this.scala$reflect$internal$tpe$TypeMaps$TypeMap$$$outer().UnmappableAnnotation();
      }

      // $FF: synthetic method
      public static final Trees.Tree $anonfun$mapOverAnnotArgs$1(final TypeMap $this, final Trees.Tree tree) {
         return $this.mapOver(tree);
      }

      public TypeMap() {
         if (TypeMaps.this == null) {
            throw null;
         } else {
            this.$outer = TypeMaps.this;
            super();
         }
      }

      // $FF: synthetic method
      public static final Object $anonfun$mapOverAnnotations$2$adapted(final TypeMap $this, final AnnotationInfos.AnnotationInfo x$4) {
         return BoxesRunTime.boxToBoolean($anonfun$mapOverAnnotations$2($this, x$4));
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }

      public class TypeMapTransformer extends scala.reflect.api.Trees.Transformer {
         // $FF: synthetic field
         public final TypeMap $outer;

         public Trees.Tree transform(final Trees.Tree tree) {
            Trees.Tree tree1 = (Trees.Tree)super.transform(tree);
            Types.Type tpe1 = this.scala$reflect$internal$tpe$TypeMaps$TypeMap$TypeMapTransformer$$$outer().apply(tree1.tpe());
            return tree == tree1 && tree.tpe() == tpe1 ? tree : tree1.shallowDuplicate().setType(tpe1);
         }

         // $FF: synthetic method
         public TypeMap scala$reflect$internal$tpe$TypeMaps$TypeMap$TypeMapTransformer$$$outer() {
            return this.$outer;
         }

         public TypeMapTransformer() {
            if (TypeMap.this == null) {
               throw null;
            } else {
               this.$outer = TypeMap.this;
               super();
            }
         }
      }
   }

   public abstract class VariancedTypeMap extends TypeMap {
      private int _variance;

      public void variance_$eq(final int x) {
         this._variance = x;
      }

      public int variance() {
         return this._variance;
      }

      public final Object withVariance(final int v, final Function0 body) {
         int saved = this.variance();
         this.variance_$eq(v);

         Object var10000;
         try {
            var10000 = body.apply();
         } finally {
            this.variance_$eq(saved);
         }

         return var10000;
      }

      public final Object flipped(final Function0 body) {
         this.variance_$eq(Variance$.MODULE$.flip$extension(this.variance()));

         Object var10000;
         try {
            var10000 = body.apply();
         } finally {
            this.variance_$eq(Variance$.MODULE$.flip$extension(this.variance()));
         }

         return var10000;
      }

      public final List mapOverArgs(final List args, final List tparams) {
         int oldVariance = this.variance();
         if (this.scala$reflect$internal$tpe$TypeMaps$VariancedTypeMap$$$outer() == null) {
            throw null;
         } else {
            List map2Conserve_loop$2_pending1 = tparams;
            List map2Conserve_loop$2_pending0 = args;
            List map2Conserve_loop$2_unchanged = args;
            ListBuffer map2Conserve_loop$2_mapped = null;

            while(!map2Conserve_loop$2_pending0.isEmpty() && !map2Conserve_loop$2_pending1.isEmpty()) {
               Object map2Conserve_loop$2_head00 = map2Conserve_loop$2_pending0.head();
               Object map2Conserve_loop$2_head01 = map2Conserve_loop$2_pending1.head();
               Symbols.Symbol var18 = (Symbols.Symbol)map2Conserve_loop$2_head01;
               Types.Type var17 = (Types.Type)map2Conserve_loop$2_head00;
               Object map2Conserve_loop$2_head1 = $anonfun$mapOverArgs$1(this, oldVariance, var17, var18);
               if (map2Conserve_loop$2_head1 == map2Conserve_loop$2_head00) {
                  List var10002 = (List)map2Conserve_loop$2_pending0.tail();
                  map2Conserve_loop$2_pending1 = (List)map2Conserve_loop$2_pending1.tail();
                  map2Conserve_loop$2_pending0 = var10002;
                  map2Conserve_loop$2_unchanged = map2Conserve_loop$2_unchanged;
                  map2Conserve_loop$2_mapped = map2Conserve_loop$2_mapped;
               } else {
                  ListBuffer map2Conserve_loop$2_b = map2Conserve_loop$2_mapped == null ? new ListBuffer() : map2Conserve_loop$2_mapped;

                  for(List map2Conserve_loop$2_xc = map2Conserve_loop$2_unchanged; map2Conserve_loop$2_xc != map2Conserve_loop$2_pending0 && map2Conserve_loop$2_xc != map2Conserve_loop$2_pending1; map2Conserve_loop$2_xc = (List)map2Conserve_loop$2_xc.tail()) {
                     Object map2Conserve_loop$2_$plus$eq_elem = map2Conserve_loop$2_xc.head();
                     map2Conserve_loop$2_b.addOne(map2Conserve_loop$2_$plus$eq_elem);
                     map2Conserve_loop$2_$plus$eq_elem = null;
                  }

                  map2Conserve_loop$2_b.addOne(map2Conserve_loop$2_head1);
                  List map2Conserve_loop$2_tail0 = (List)map2Conserve_loop$2_pending0.tail();
                  List map2Conserve_loop$2_tail1 = (List)map2Conserve_loop$2_pending1.tail();
                  map2Conserve_loop$2_pending1 = map2Conserve_loop$2_tail1;
                  map2Conserve_loop$2_pending0 = map2Conserve_loop$2_tail0;
                  map2Conserve_loop$2_unchanged = map2Conserve_loop$2_tail0;
                  map2Conserve_loop$2_mapped = map2Conserve_loop$2_b;
               }
            }

            List var10000 = map2Conserve_loop$2_mapped == null ? map2Conserve_loop$2_unchanged : map2Conserve_loop$2_mapped.prependToList(map2Conserve_loop$2_unchanged);
            Object var19 = null;
            Object var20 = null;
            Object var21 = null;
            Object var22 = null;
            Object var23 = null;
            Object var24 = null;
            Object var25 = null;
            Object var26 = null;
            Object var27 = null;
            Object var28 = null;
            Object var29 = null;
            Object var31 = null;
            List map2Conserve_result = var10000;
            Statics.releaseFence();
            return map2Conserve_result;
         }
      }

      public final Types.Type applyToSymbolInfo(final Symbols.Symbol sym, final Types.Type info) {
         if (!Variance$.MODULE$.isInvariant$extension(this.variance()) && sym.isAliasType()) {
            int withVariance_v = Variance$.MODULE$.Invariant();
            int withVariance_saved = this.variance();
            this.variance_$eq(withVariance_v);

            Types.Type var10000;
            try {
               var10000 = this.apply(info);
            } finally {
               this.variance_$eq(withVariance_saved);
            }

            return var10000;
         } else {
            return this.apply(info);
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$VariancedTypeMap$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final Types.Type $anonfun$mapOverArgs$2(final VariancedTypeMap $this, final Types.Type arg$1) {
         return $this.apply(arg$1);
      }

      // $FF: synthetic method
      public static final Types.Type $anonfun$mapOverArgs$1(final VariancedTypeMap $this, final int oldVariance$1, final Types.Type arg, final Symbols.Symbol tparam) {
         int withVariance_v = Variance$.MODULE$.$times$extension(oldVariance$1, tparam.variance());
         int withVariance_saved = $this.variance();
         $this.variance_$eq(withVariance_v);

         Types.Type var10000;
         try {
            var10000 = $this.apply(arg);
         } finally {
            $this.variance_$eq(withVariance_saved);
         }

         return var10000;
      }

      // $FF: synthetic method
      public static final Types.Type $anonfun$applyToSymbolInfo$1(final VariancedTypeMap $this, final Types.Type info$1) {
         return $this.apply(info$1);
      }

      public VariancedTypeMap() {
         this._variance = Variance$.MODULE$.Covariant();
      }
   }

   public abstract class TypeFolder implements Function1 {
      // $FF: synthetic field
      public final SymbolTable $outer;

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

      public String toString() {
         return Function1.toString$(this);
      }

      public abstract void apply(final Types.Type tp);

      public void foldOver(final List syms) {
         if (syms == null) {
            throw null;
         } else {
            for(List foreach_these = syms; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
               Symbols.Symbol var3 = (Symbols.Symbol)foreach_these.head();
               $anonfun$foldOver$1(this, var3);
            }

         }
      }

      public void foldOver(final Scopes.Scope scope) {
         List elems = scope.toList();
         this.foldOver(elems);
      }

      public void foldOverAnnotations(final List annots) {
         if (annots == null) {
            throw null;
         } else {
            for(List foreach_these = annots; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
               AnnotationInfos.AnnotationInfo var3 = (AnnotationInfos.AnnotationInfo)foreach_these.head();
               this.foldOver(var3);
            }

         }
      }

      public void foldOver(final AnnotationInfos.AnnotationInfo annot) {
         if (annot != null) {
            Some var2 = this.scala$reflect$internal$tpe$TypeMaps$TypeFolder$$$outer().AnnotationInfo().unapply(annot);
            if (!var2.isEmpty()) {
               Types.Type atp = (Types.Type)((Tuple3)var2.value())._1();
               List args = (List)((Tuple3)var2.value())._2();
               atp.foldOver(this);
               this.foldOverAnnotArgs(args);
               return;
            }
         }

         throw new MatchError(annot);
      }

      public void foldOverAnnotArgs(final List args) {
         if (args == null) {
            throw null;
         } else {
            for(List foreach_these = args; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
               Trees.Tree var3 = (Trees.Tree)foreach_these.head();
               this.foldOver(var3);
            }

         }
      }

      public void foldOver(final Trees.Tree tree) {
         this.apply(tree.tpe());
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$TypeFolder$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final void $anonfun$foldOver$1(final TypeFolder $this, final Symbols.Symbol sym) {
         $this.apply(sym.info());
      }

      // $FF: synthetic method
      public static final void $anonfun$foldOverAnnotations$1(final TypeFolder $this, final AnnotationInfos.AnnotationInfo annot) {
         $this.foldOver(annot);
      }

      // $FF: synthetic method
      public static final void $anonfun$foldOverAnnotArgs$1(final TypeFolder $this, final Trees.Tree tree) {
         $this.foldOver(tree);
      }

      public TypeFolder() {
         if (TypeMaps.this == null) {
            throw null;
         } else {
            this.$outer = TypeMaps.this;
            super();
         }
      }

      // $FF: synthetic method
      public static final Object $anonfun$foldOver$1$adapted(final TypeFolder $this, final Symbols.Symbol sym) {
         $anonfun$foldOver$1($this, sym);
         return BoxedUnit.UNIT;
      }

      // $FF: synthetic method
      public static final Object $anonfun$foldOverAnnotations$1$adapted(final TypeFolder $this, final AnnotationInfos.AnnotationInfo annot) {
         $anonfun$foldOverAnnotations$1($this, annot);
         return BoxedUnit.UNIT;
      }

      // $FF: synthetic method
      public static final Object $anonfun$foldOverAnnotArgs$1$adapted(final TypeFolder $this, final Trees.Tree tree) {
         $anonfun$foldOverAnnotArgs$1($this, tree);
         return BoxedUnit.UNIT;
      }
   }

   public abstract class TypeTraverser extends TypeMap {
      public abstract void traverse(final Types.Type tp);

      public Types.Type apply(final Types.Type tp) {
         this.traverse(tp);
         return tp;
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$TypeTraverser$$$outer() {
         return this.$outer;
      }
   }

   public abstract class TypeCollector extends TypeFolder {
      private final Object initial;
      private Object result;

      public Object result() {
         return this.result;
      }

      public void result_$eq(final Object x$1) {
         this.result = x$1;
      }

      public Object collect(final Types.Type tp) {
         Object saved = this.result();

         Object var10000;
         try {
            this.result_$eq(this.initial);
            this.apply(tp);
            var10000 = this.result();
         } finally {
            this.result_$eq(saved);
         }

         return var10000;
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$TypeCollector$$$outer() {
         return this.$outer;
      }

      public TypeCollector(final Object initial) {
         this.initial = initial;
      }
   }

   public class ExistentialExtrapolation extends VariancedTypeMap {
      private final List tparams;
      private final HashMap occurCount;
      private final ContainsAnyKeyCollector anyContains;

      private void countOccs(final Types.Type tp) {
         tp.foreach((x0$1) -> {
            $anonfun$countOccs$1(this, x0$1);
            return BoxedUnit.UNIT;
         });
      }

      public Types.Type extrapolate(final Types.Type tpe) {
         List var10000 = this.tparams;
         if (var10000 == null) {
            throw null;
         } else {
            for(List foreach_these = var10000; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
               Symbols.Symbol var4 = (Symbols.Symbol)foreach_these.head();
               $anonfun$extrapolate$1(this, var4);
            }

            Object var6 = null;
            this.countOccs(tpe);
            var10000 = this.tparams;
            if (var10000 == null) {
               throw null;
            } else {
               for(List foreach_these = var10000; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
                  Symbols.Symbol var5 = (Symbols.Symbol)foreach_these.head();
                  $anonfun$extrapolate$2(this, var5);
               }

               Object var7 = null;
               return this.apply(tpe);
            }
         }
      }

      public Types.Type apply(final Types.Type tp) {
         Types.Type tp1 = this.mapOver(tp);
         if (Variance$.MODULE$.isInvariant$extension(this.variance())) {
            return tp1;
         } else {
            if (tp1 instanceof Types.TypeRef) {
               Symbols.Symbol sym = ((Types.TypeRef)tp1).sym();
               if (this.tparams.contains(sym) && BoxesRunTime.unboxToInt(this.occurCount.apply(sym)) == 1) {
                  Types.Type repl = Variance$.MODULE$.isPositive$extension(this.variance()) ? this.scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$$outer().dropSingletonType().apply(tp1.upperBound()) : tp1.lowerBound();
                  if (!repl.typeSymbol().isBottomClass() && !BoxesRunTime.unboxToBoolean(this.anyContains.collect(repl))) {
                     SymbolTable var10000 = this.scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$$outer();
                     Function0 debuglogResult_msg = () -> this.msg$1(tp1);
                     if (var10000 == null) {
                        throw null;
                     }

                     var10000.debuglog(SymbolTable::$anonfun$debuglogResult$1);
                     return repl;
                  }

                  return tp1;
               }
            }

            return tp1;
         }
      }

      public Types.Type mapOver(final Types.Type tp) {
         if (tp instanceof Types.SingleType) {
            Types.SingleType var2 = (Types.SingleType)tp;
            Types.Type pre = var2.pre();
            Symbols.Symbol sym = var2.sym();
            if (sym.isPackageClass()) {
               return tp;
            } else {
               Types.Type pre1 = this.apply(pre);
               return pre1 != pre && pre1.isStable() ? this.scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$$outer().singleType(pre1, sym) : tp;
            }
         } else {
            return tp.mapOver(this);
         }
      }

      public Trees.Tree mapOver(final Trees.Tree tree) {
         return tree instanceof Trees.Ident && tree.tpe().isStable() ? tree : super.mapOver(tree);
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$ExistentialExtrapolation$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final void $anonfun$countOccs$1(final ExistentialExtrapolation $this, final Types.Type x0$1) {
         if (x0$1 instanceof Types.TypeRef) {
            Symbols.Symbol sym = ((Types.TypeRef)x0$1).sym();
            if ($this.occurCount.contains(sym)) {
               $this.occurCount.update(sym, BoxesRunTime.unboxToInt($this.occurCount.apply(sym)) + 1);
            }
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$extrapolate$1(final ExistentialExtrapolation $this, final Symbols.Symbol t) {
         $this.occurCount.update(t, 0);
      }

      // $FF: synthetic method
      public static final void $anonfun$extrapolate$2(final ExistentialExtrapolation $this, final Symbols.Symbol tparam) {
         $this.countOccs(tparam.info());
      }

      private final String msg$1(final Types.Type tp1$1) {
         String word = Variance$.MODULE$.isPositive$extension(this.variance()) ? "upper" : "lower";
         return (new StringBuilder(56)).append("Widened lone occurrence of ").append(tp1$1).append(" inside existential to ").append(word).append(" bound").toString();
      }

      public ExistentialExtrapolation(final List tparams) {
         this.tparams = tparams;
         this.occurCount = (HashMap)MapFactory.apply$(scala.collection.mutable.HashMap..MODULE$, scala.collection.immutable.Nil..MODULE$);
         this.anyContains = TypeMaps.this.new ContainsAnyKeyCollector(this.occurCount);
      }

      // $FF: synthetic method
      public static final Object $anonfun$extrapolate$1$adapted(final ExistentialExtrapolation $this, final Symbols.Symbol t) {
         $anonfun$extrapolate$1($this, t);
         return BoxedUnit.UNIT;
      }

      // $FF: synthetic method
      public static final Object $anonfun$extrapolate$2$adapted(final ExistentialExtrapolation $this, final Symbols.Symbol tparam) {
         $anonfun$extrapolate$2($this, tparam);
         return BoxedUnit.UNIT;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class wildcardExtrapolation$ extends VariancedTypeMap {
      public Types.Type apply(final Types.Type tp) {
         boolean var2 = false;
         Types.BoundedWildcardType var3 = null;
         if (tp instanceof Types.BoundedWildcardType) {
            var2 = true;
            var3 = (Types.BoundedWildcardType)tp;
            Types.TypeBounds var4 = var3.bounds();
            if (var4 != null) {
               label52: {
                  Types.Type lo = var4.lo();
                  Types.Type var6 = var4.hi();
                  Types.Type var10000 = this.scala$reflect$internal$tpe$TypeMaps$wildcardExtrapolation$$$outer().definitions().AnyTpe();
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label52;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label52;
                  }

                  if (Variance$.MODULE$.isContravariant$extension(this.variance())) {
                     return lo;
                  }
               }
            }
         }

         if (var2) {
            Types.TypeBounds var7 = var3.bounds();
            if (var7 != null) {
               label44: {
                  Types.Type lo = var7.lo();
                  Types.Type var9 = var7.hi();
                  Types.ObjectTpeJavaRef var13 = this.scala$reflect$internal$tpe$TypeMaps$wildcardExtrapolation$$$outer().definitions().ObjectTpeJava();
                  if (var13 == null) {
                     if (var9 != null) {
                        break label44;
                     }
                  } else if (!var13.equals(var9)) {
                     break label44;
                  }

                  if (Variance$.MODULE$.isContravariant$extension(this.variance())) {
                     return lo;
                  }
               }
            }
         }

         if (var2) {
            Types.TypeBounds var10 = var3.bounds();
            if (var10 != null) {
               Types.Type var11 = var10.lo();
               Types.Type hi = var10.hi();
               Types.Type var14 = this.scala$reflect$internal$tpe$TypeMaps$wildcardExtrapolation$$$outer().definitions().NothingTpe();
               if (var14 == null) {
                  if (var11 != null) {
                     return tp.mapOver(this);
                  }
               } else if (!var14.equals(var11)) {
                  return tp.mapOver(this);
               }

               if (Variance$.MODULE$.isCovariant$extension(this.variance())) {
                  return hi;
               }
            }
         }

         return tp.mapOver(this);
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$wildcardExtrapolation$$$outer() {
         return this.$outer;
      }
   }

   public class AsSeenFromMap extends TypeMap implements KeepOnlyTypeConstraints {
      private volatile annotationArgRewriter$ annotationArgRewriter$module;
      public final Symbols.Symbol scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromClass;
      public final Types.Type scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromPrefix;
      private List _capturedSkolems;
      private List _capturedParams;
      private final boolean isStablePrefix;
      private int capturedThisIds;
      public boolean scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$wroteAnnotation;

      public boolean keepAnnotation(final AnnotationInfos.AnnotationInfo annot) {
         return TypeMaps.KeepOnlyTypeConstraints.super.keepAnnotation(annot);
      }

      // $FF: synthetic method
      public AnnotationInfos.AnnotationInfo scala$reflect$internal$tpe$TypeMaps$AnnotationFilter$$super$mapOver(final AnnotationInfos.AnnotationInfo annot) {
         return super.mapOver(annot);
      }

      public AnnotationInfos.AnnotationInfo mapOver(final AnnotationInfos.AnnotationInfo annot) {
         return TypeMaps.AnnotationFilter.super.mapOver(annot);
      }

      private annotationArgRewriter$ annotationArgRewriter() {
         if (this.annotationArgRewriter$module == null) {
            this.annotationArgRewriter$lzycompute$1();
         }

         return this.annotationArgRewriter$module;
      }

      public List capturedParams() {
         return this._capturedParams;
      }

      public List capturedSkolems() {
         return this._capturedSkolems;
      }

      public Types.Type apply(final Types.Type tp) {
         if (tp instanceof Types.ThisType) {
            Types.ThisType var2 = (Types.ThisType)tp;
            return this.thisTypeAsSeen(var2);
         } else if (tp instanceof Types.SingleType) {
            Types.SingleType var3 = (Types.SingleType)tp;
            return (Types.Type)(var3.sym().isPackageClass() ? var3 : this.singleTypeAsSeen(var3));
         } else {
            if (tp instanceof Types.TypeRef) {
               Types.TypeRef var4 = (Types.TypeRef)tp;
               Symbols.Symbol sym = var4.sym();
               if (this.isTypeParamOfEnclosingClass(sym)) {
                  return this.classParameterAsSeen(var4);
               }
            }

            return tp.mapOver(this);
         }
      }

      private boolean isBaseClassOfEnclosingClass(final Symbols.Symbol base) {
         return !base.hasCompleteInfo() || this.loop$2(this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromClass, base);
      }

      private boolean isTypeParamOfEnclosingClass(final Symbols.Symbol sym) {
         return sym.isTypeParameter() && sym.owner().isClass() && this.isBaseClassOfEnclosingClass(sym.owner());
      }

      private int nextCapturedThisId() {
         ++this.capturedThisIds;
         return this.capturedThisIds;
      }

      public Types.Type captureThis(final Types.Type pre, final Symbols.Symbol clazz) {
         List var10000 = this.capturedParams();
         if (var10000 == null) {
            throw null;
         } else {
            List find_these = var10000;

            while(true) {
               if (find_these.isEmpty()) {
                  var11 = scala.None..MODULE$;
                  break;
               }

               Symbols.Symbol var8 = (Symbols.Symbol)find_these.head();
               if ($anonfun$captureThis$1(clazz, var8)) {
                  var11 = new Some(find_these.head());
                  break;
               }

               find_these = (List)find_these.tail();
            }

            Object var9 = null;
            Object var3 = var11;
            if (var3 instanceof Some) {
               Symbols.Symbol p = (Symbols.Symbol)((Some)var3).value();
               if (p == null) {
                  throw null;
               } else {
                  return p.tpe_$times();
               }
            } else {
               Symbols.TypeSymbol qvar = (Symbols.TypeSymbol)clazz.freshExistential(".type", this.nextCapturedThisId()).setInfo(this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().singletonBounds(pre));
               List var10001 = this._capturedParams;
               if (var10001 == null) {
                  throw null;
               } else {
                  List $colon$colon_this = var10001;
                  scala.collection.immutable..colon.colon var12 = new scala.collection.immutable..colon.colon(qvar, $colon$colon_this);
                  Object var10 = null;
                  this._capturedParams = var12;
                  this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().debuglog(() -> (new StringBuilder(28)).append("Captured This(").append(clazz.fullNameString()).append(") seen from ").append(this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromPrefix).append(": ").append(qvar.defString()).toString());
                  if (qvar == null) {
                     throw null;
                  } else {
                     return ((Symbols.Symbol)qvar).tpe_$times();
                  }
               }
            }
         }
      }

      public void captureSkolems(final List skolems) {
         skolems.withFilter((p) -> BoxesRunTime.boxToBoolean($anonfun$captureSkolems$1(this, p))).foreach((p) -> {
            $anonfun$captureSkolems$2(this, p);
            return BoxedUnit.UNIT;
         });
      }

      private Types.Type correspondingTypeArgument(final Types.Type lhs, final Types.Type rhs) {
         if (lhs instanceof Types.TypeRef) {
            Types.TypeRef var3 = (Types.TypeRef)lhs;
            Symbols.Symbol lhsSym = var3.sym();
            List lhsArgs = var3.args();
            if (!(rhs instanceof Types.TypeRef)) {
               throw new MatchError(rhs);
            } else {
               Symbols.Symbol rhsSym;
               List rhsArgs;
               boolean var68;
               SymbolTable var10000;
               label149: {
                  label148: {
                     Types.TypeRef var6 = (Types.TypeRef)rhs;
                     rhsSym = var6.sym();
                     rhsArgs = var6.args();
                     var10000 = this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer();
                     Symbols.Symbol var10001 = lhsSym.owner();
                     if (var10001 == null) {
                        if (rhsSym == null) {
                           break label148;
                        }
                     } else if (var10001.equals(rhsSym)) {
                        break label148;
                     }

                     var68 = false;
                     break label149;
                  }

                  var68 = true;
               }

               boolean require_requirement = var68;
               if (var10000 == null) {
                  throw null;
               } else {
                  SymbolTable require_this = var10000;
                  if (!require_requirement) {
                     throw require_this.throwRequirementError($anonfun$correspondingTypeArgument$1(lhsSym, rhsSym));
                  } else {
                     require_this = null;
                     int argIndex = rhsSym.typeParams().indexWhere((x$11) -> BoxesRunTime.boxToBoolean($anonfun$correspondingTypeArgument$2(lhsSym, x$11)));
                     if (argIndex < 0) {
                        List var55 = rhs.parents();
                        if (var55 == null) {
                           throw null;
                        }

                        List exists_these = var55;

                        while(true) {
                           if (exists_these.isEmpty()) {
                              var56 = false;
                              break;
                           }

                           if (((Types.Type)exists_these.head()).isErroneous()) {
                              var56 = true;
                              break;
                           }

                           exists_these = (List)exists_these.tail();
                        }

                        Object var54 = null;
                        if (var56) {
                           return this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().ErrorType();
                        }
                     }

                     if (rhsArgs == null) {
                        throw null;
                     } else if (!LinearSeqOps.isDefinedAt$(rhsArgs, argIndex)) {
                        throw this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().abort((new StringBuilder(50)).append("Something is wrong: cannot find ").append(lhs).append(" in applied type ").append(rhs).append("\n").append(explain$1(lhsSym, rhsSym)).toString());
                     } else {
                        Types.Type targ = (Types.Type)LinearSeqOps.apply$(rhsArgs, argIndex);
                        var10000 = this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer();
                        if (lhsArgs == null) {
                           throw null;
                        } else {
                           List mapConserve_loop$3_pending = lhsArgs;
                           List mapConserve_loop$3_unchanged = lhsArgs;
                           scala.collection.immutable..colon.colon mapConserve_loop$3_mappedLast = null;
                           List mapConserve_loop$3_mappedHead = null;

                           while(!mapConserve_loop$3_pending.isEmpty()) {
                              Object mapConserve_loop$3_head0 = mapConserve_loop$3_pending.head();
                              Object mapConserve_loop$3_head1 = this.apply(mapConserve_loop$3_head0);
                              if (mapConserve_loop$3_head1 == mapConserve_loop$3_head0) {
                                 mapConserve_loop$3_pending = (List)mapConserve_loop$3_pending.tail();
                                 mapConserve_loop$3_unchanged = mapConserve_loop$3_unchanged;
                                 mapConserve_loop$3_mappedLast = mapConserve_loop$3_mappedLast;
                                 mapConserve_loop$3_mappedHead = mapConserve_loop$3_mappedHead;
                              } else {
                                 List mapConserve_loop$3_xc = mapConserve_loop$3_unchanged;
                                 List mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_mappedHead;

                                 scala.collection.immutable..colon.colon mapConserve_loop$3_mappedLast1;
                                 for(mapConserve_loop$3_mappedLast1 = mapConserve_loop$3_mappedLast; mapConserve_loop$3_xc != mapConserve_loop$3_pending; mapConserve_loop$3_xc = (List)mapConserve_loop$3_xc.tail()) {
                                    scala.collection.immutable..colon.colon mapConserve_loop$3_next = new scala.collection.immutable..colon.colon(mapConserve_loop$3_xc.head(), scala.collection.immutable.Nil..MODULE$);
                                    if (mapConserve_loop$3_mappedHead1 == null) {
                                       mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_next;
                                    }

                                    if (mapConserve_loop$3_mappedLast1 != null) {
                                       mapConserve_loop$3_mappedLast1.next_$eq(mapConserve_loop$3_next);
                                    }

                                    mapConserve_loop$3_mappedLast1 = mapConserve_loop$3_next;
                                 }

                                 scala.collection.immutable..colon.colon mapConserve_loop$3_next = new scala.collection.immutable..colon.colon(mapConserve_loop$3_head1, scala.collection.immutable.Nil..MODULE$);
                                 if (mapConserve_loop$3_mappedHead1 == null) {
                                    mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_next;
                                 }

                                 if (mapConserve_loop$3_mappedLast1 != null) {
                                    mapConserve_loop$3_mappedLast1.next_$eq(mapConserve_loop$3_next);
                                 }

                                 List mapConserve_loop$3_tail0 = (List)mapConserve_loop$3_pending.tail();
                                 mapConserve_loop$3_pending = mapConserve_loop$3_tail0;
                                 mapConserve_loop$3_unchanged = mapConserve_loop$3_tail0;
                                 mapConserve_loop$3_mappedLast = mapConserve_loop$3_next;
                                 mapConserve_loop$3_mappedHead = mapConserve_loop$3_mappedHead1;
                              }
                           }

                           Object var10002;
                           if (mapConserve_loop$3_mappedHead == null) {
                              var10002 = mapConserve_loop$3_unchanged;
                           } else {
                              mapConserve_loop$3_mappedLast.next_$eq(mapConserve_loop$3_unchanged);
                              var10002 = mapConserve_loop$3_mappedHead;
                           }

                           mapConserve_loop$3_mappedHead = null;
                           Object var37 = null;
                           Object var38 = null;
                           Object var39 = null;
                           Object var40 = null;
                           Object var41 = null;
                           Object var42 = null;
                           Object var43 = null;
                           Object var44 = null;
                           Object var45 = null;
                           Object var46 = null;
                           Object var47 = null;
                           List mapConserve_result = (List)var10002;
                           Statics.releaseFence();
                           var10002 = mapConserve_result;
                           mapConserve_result = null;
                           Types.Type result = var10000.appliedType((Types.Type)targ, (List)var10002);
                           var10000 = this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer();
                           Function0 devWarningIf_msg = () -> (new StringBuilder(60)).append("Inconsistent tparam/owner views: had to fall back on names\n").append(msg$2(result, lhsSym, rhsSym)).append("\n").append(explain$1(lhsSym, rhsSym)).toString();
                           if (var10000 == null) {
                              throw null;
                           } else {
                              SymbolTable devWarningIf_this;
                              label107: {
                                 devWarningIf_this = var10000;
                                 MutableSettings.SettingsOps$ var59 = MutableSettings.SettingsOps$.MODULE$;
                                 MutableSettings$ var60 = MutableSettings$.MODULE$;
                                 MutableSettings devWarningIf_isDeveloper_SettingsOps_settings = devWarningIf_this.settings();
                                 MutableSettings var61 = devWarningIf_isDeveloper_SettingsOps_settings;
                                 devWarningIf_isDeveloper_SettingsOps_settings = null;
                                 MutableSettings devWarningIf_isDeveloper_isDebug$extension_$this = var61;
                                 boolean var62 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(devWarningIf_isDeveloper_isDebug$extension_$this.debug().value());
                                 devWarningIf_isDeveloper_isDebug$extension_$this = null;
                                 if (!var62) {
                                    MutableSettings.SettingsOps$ var63 = MutableSettings.SettingsOps$.MODULE$;
                                    MutableSettings$ var64 = MutableSettings$.MODULE$;
                                    MutableSettings devWarningIf_isDeveloper_SettingsOps_settings = devWarningIf_this.settings();
                                    MutableSettings var65 = devWarningIf_isDeveloper_SettingsOps_settings;
                                    devWarningIf_isDeveloper_SettingsOps_settings = null;
                                    MutableSettings devWarningIf_isDeveloper_isDeveloper$extension_$this = var65;
                                    boolean var66 = StatisticsStatics.DEVELOPER_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(devWarningIf_isDeveloper_isDeveloper$extension_$this.developer().value());
                                    devWarningIf_isDeveloper_isDeveloper$extension_$this = null;
                                    if (!var66) {
                                       var67 = false;
                                       break label107;
                                    }
                                 }

                                 var67 = true;
                              }

                              Object var50 = null;
                              Object var53 = null;
                              if (var67 && $anonfun$correspondingTypeArgument$5(rhsSym, lhsSym)) {
                                 devWarningIf_this.devWarning(devWarningIf_msg);
                              }

                              return result;
                           }
                        }
                     }
                  }
               }
            }
         } else {
            throw new MatchError(lhs);
         }
      }

      private Types.Type classParameterAsSeen(final Types.TypeRef classParam) {
         Symbols.Symbol tparam = classParam.sym();
         return this.loop$3(this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromPrefix, this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromClass, classParam, tparam);
      }

      public boolean scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$matchesPrefixAndClass(final Types.Type pre, final Symbols.Symbol clazz, final Symbols.Symbol candidate) {
         if (clazz == null) {
            if (candidate != null) {
               return false;
            }
         } else if (!clazz.equals(candidate)) {
            return false;
         }

         Types.Type pre1 = pre instanceof Types.TypeVar ? ((Types.TypeVar)pre).origin() : pre;
         if (clazz.isRefinementClass()) {
            if (pre1.widen().typeSymbol().isSubClass(clazz)) {
               return true;
            }
         } else if (pre1.widen().baseTypeIndex(clazz) != -1) {
            return true;
         }

         return false;
      }

      public Trees.Tree mapOver(final Trees.Tree tree, final Function0 giveup) {
         if (this.isStablePrefix) {
            return this.annotationArgRewriter().transform(tree);
         } else {
            boolean saved = this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$wroteAnnotation;
            this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$wroteAnnotation = false;

            Trees.Tree var10000;
            try {
               var10000 = this.annotationArgRewriter().transform(tree);
            } finally {
               if (this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$wroteAnnotation) {
                  giveup.apply();
               } else {
                  this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$wroteAnnotation = saved;
               }

            }

            return var10000;
         }
      }

      private Types.Type thisTypeAsSeen(final Types.ThisType tp) {
         return this.loop$4(this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromPrefix, this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromClass, tp);
      }

      private Types.Type singleTypeAsSeen(final Types.SingleType tp) {
         if (tp != null) {
            Types.Type pre = tp.pre();
            Symbols.Symbol sym = tp.sym();
            Types.Type pre1 = this.apply(pre);
            if (pre1 == pre) {
               return tp;
            } else {
               return pre1.isStable() ? this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().singleType(pre1, sym) : pre1.memberType(sym).resultType();
            }
         } else {
            throw new MatchError((Object)null);
         }
      }

      public String toString() {
         return (new StringBuilder(17)).append("AsSeenFromMap(").append(this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromPrefix).append(", ").append(this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromClass).append(")").toString();
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public TypeMaps scala$reflect$internal$tpe$TypeMaps$KeepOnlyTypeConstraints$$$outer() {
         return this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer();
      }

      // $FF: synthetic method
      public TypeMaps scala$reflect$internal$tpe$TypeMaps$AnnotationFilter$$$outer() {
         return this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer();
      }

      private final void annotationArgRewriter$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.annotationArgRewriter$module == null) {
               this.annotationArgRewriter$module = new annotationArgRewriter$();
            }
         } catch (Throwable var2) {
            throw var2;
         }

      }

      private final boolean loop$2(final Symbols.Symbol encl, final Symbols.Symbol base$1) {
         while(true) {
            if (this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().isPossiblePrefix(encl)) {
               if (!encl.isSubClass(base$1)) {
                  encl = encl.owner().enclClass();
                  continue;
               }

               return true;
            }

            return false;
         }
      }

      // $FF: synthetic method
      public static final boolean $anonfun$captureThis$1(final Symbols.Symbol clazz$1, final Symbols.Symbol x$8) {
         Symbols.Symbol var10000 = x$8.owner();
         if (var10000 == null) {
            if (clazz$1 == null) {
               return true;
            }
         } else if (var10000.equals(clazz$1)) {
            return true;
         }

         return false;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$captureSkolems$1(final AsSeenFromMap $this, final Symbols.Symbol p) {
         return !$this.capturedSkolems().contains(p);
      }

      // $FF: synthetic method
      public static final void $anonfun$captureSkolems$2(final AsSeenFromMap $this, final Symbols.Symbol p) {
         $this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().debuglog(() -> (new StringBuilder(20)).append("Captured ").append(p).append(" seen from ").append($this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromPrefix).toString());
         List var10001 = $this._capturedSkolems;
         if (var10001 == null) {
            throw null;
         } else {
            List $colon$colon_this = var10001;
            $this._capturedSkolems = new scala.collection.immutable..colon.colon(p, $colon$colon_this);
         }
      }

      // $FF: synthetic method
      public static final String $anonfun$correspondingTypeArgument$1(final Symbols.Symbol lhsSym$1, final Symbols.Symbol rhsSym$1) {
         return (new StringBuilder(28)).append(lhsSym$1).append(" is not a type parameter of ").append(rhsSym$1).toString();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$correspondingTypeArgument$2(final Symbols.Symbol lhsSym$1, final Symbols.Symbol x$11) {
         Names.Name var10000 = lhsSym$1.name();
         Names.Name var2 = x$11.name();
         if (var10000 == null) {
            if (var2 == null) {
               return true;
            }
         } else if (var10000.equals(var2)) {
            return true;
         }

         return false;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$correspondingTypeArgument$3(final Types.Type x$12) {
         return x$12.isErroneous();
      }

      private static final String own_s$1(final Symbols.Symbol s) {
         return (new StringBuilder(4)).append(s.nameString()).append(" in ").append(s.owner().nameString()).toString();
      }

      // $FF: synthetic method
      public static final String $anonfun$correspondingTypeArgument$4(final Symbols.Symbol s) {
         return own_s$1(s);
      }

      private static final String explain$1(final Symbols.Symbol lhsSym$1, final Symbols.Symbol rhsSym$1) {
         package$ var10000 = package$.MODULE$;
         StringContext StringContextStripMarginOps_stringContext = new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new String[]{"|   sought  ", "\n               | classSym  ", "\n               |  tparams  ", "\n               |"}));
         package.StringContextStripMarginOps var16 = new package.StringContextStripMarginOps(StringContextStripMarginOps_stringContext);
         Object var15 = null;
         ScalaRunTime var10001 = scala.runtime.ScalaRunTime..MODULE$;
         Object[] var10002 = new Object[]{own_s$1(lhsSym$1), own_s$1(rhsSym$1), null};
         List var10005 = rhsSym$1.typeParams();
         if (var10005 == null) {
            throw null;
         } else {
            List map_this = var10005;
            Object var17;
            if (map_this == scala.collection.immutable.Nil..MODULE$) {
               var17 = scala.collection.immutable.Nil..MODULE$;
            } else {
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(own_s$1((Symbols.Symbol)map_this.head()), scala.collection.immutable.Nil..MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(own_s$1((Symbols.Symbol)map_rest.head()), scala.collection.immutable.Nil..MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var17 = map_h;
            }

            Object var10 = null;
            Object var11 = null;
            Object var12 = null;
            Object var13 = null;
            Object var14 = null;
            String mkString_sep = ", ";
            String var18 = ((IterableOnceOps)var17).mkString("", mkString_sep, "");
            Object var9 = null;
            var10002[2] = var18;
            return StripMarginInterpolator.sm$(var16, var10001.genericWrapArray(var10002));
         }
      }

      private static final String msg$2(final Types.Type result$1, final Symbols.Symbol lhsSym$1, final Symbols.Symbol rhsSym$1) {
         return (new StringBuilder(50)).append("Created ").append(result$1).append(", though could not find ").append(own_s$1(lhsSym$1)).append(" among tparams of ").append(own_s$1(rhsSym$1)).toString();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$correspondingTypeArgument$5(final Symbols.Symbol rhsSym$1, final Symbols.Symbol lhsSym$1) {
         return !rhsSym$1.typeParams().contains(lhsSym$1);
      }

      private static final Types.Type nextBase$1(final Types.Type pre$1, final Symbols.Symbol clazz$2) {
         return pre$1.baseType(clazz$2).deconst();
      }

      private final Types.Type loop$3(final Types.Type pre, final Symbols.Symbol clazz, final Types.TypeRef classParam$1, final Symbols.Symbol tparam$1) {
         while(!this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().skipPrefixOf(pre, clazz)) {
            if (!this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$matchesPrefixAndClass(pre, clazz, tparam$1.owner())) {
               Types.Type var10 = nextBase$1(pre, clazz).prefix();
               clazz = clazz.owner();
               pre = var10;
            } else {
               Types.Type var5 = nextBase$1(pre, clazz);
               if (this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().NoType().equals(var5)) {
                  Types.NoType$ var10000 = this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().NoType();
                  clazz = clazz.owner();
                  pre = var10000;
               } else {
                  if (var5 instanceof Types.TypeRef) {
                     Types.TypeRef var6 = (Types.TypeRef)var5;
                     return this.correspondingTypeArgument(classParam$1, var6);
                  }

                  if (!(var5 instanceof Types.ExistentialType)) {
                     throw this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().abort((new StringBuilder(33)).append(tparam$1).append(" in ").append(tparam$1.owner()).append(" cannot be instantiated from ").append(this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromPrefix.widen()).toString());
                  }

                  Types.ExistentialType var7 = (Types.ExistentialType)var5;
                  List eparams = var7.quantified();
                  Types.Type qtpe = var7.underlying();
                  this.captureSkolems(eparams);
                  clazz = clazz;
                  pre = qtpe;
               }
            }
         }

         return classParam$1.mapOver(this);
      }

      private final Types.Type loop$4(final Types.Type pre, final Symbols.Symbol clazz, final Types.ThisType tp$2) {
         while(true) {
            Types.Type pre1 = pre instanceof Types.SuperType ? ((Types.SuperType)pre).thistpe() : pre;
            if (this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().skipPrefixOf(pre, clazz)) {
               return tp$2.mapOver(this);
            }

            if (this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$matchesPrefixAndClass(pre, clazz, tp$2.sym())) {
               if (pre1.isStable()) {
                  return pre1;
               }

               return this.captureThis(pre1, clazz);
            }

            Types.Type var10000 = pre.baseType(clazz).prefix();
            clazz = clazz.owner();
            pre = var10000;
         }
      }

      public AsSeenFromMap(final Types.Type seenFromPrefix0, final Symbols.Symbol seenFromClass) {
         this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromClass = seenFromClass;
         this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromPrefix = seenFromPrefix0.typeSymbolDirect().hasPackageFlag() && !seenFromClass.hasPackageFlag() ? seenFromPrefix0.packageObject().typeOfThis() : seenFromPrefix0;
         this._capturedSkolems = scala.collection.immutable.Nil..MODULE$;
         this._capturedParams = scala.collection.immutable.Nil..MODULE$;
         this.isStablePrefix = this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromPrefix.isStable();
         this.capturedThisIds = 0;
         this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$wroteAnnotation = false;
      }

      // $FF: synthetic method
      public static final Object $anonfun$captureThis$1$adapted(final Symbols.Symbol clazz$1, final Symbols.Symbol x$8) {
         return BoxesRunTime.boxToBoolean($anonfun$captureThis$1(clazz$1, x$8));
      }

      // $FF: synthetic method
      public static final Object $anonfun$correspondingTypeArgument$3$adapted(final Types.Type x$12) {
         return BoxesRunTime.boxToBoolean($anonfun$correspondingTypeArgument$3(x$12));
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }

      private class annotationArgRewriter$ extends TypeMap.TypeMapTransformer {
         private boolean matchesThis(final Symbols.Symbol thiz) {
            return this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$annotationArgRewriter$$$outer().scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$matchesPrefixAndClass(this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$annotationArgRewriter$$$outer().scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromPrefix, this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$annotationArgRewriter$$$outer().scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromClass, thiz);
         }

         private Trees.Tree newThis() {
            this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$annotationArgRewriter$$$outer().scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$wroteAnnotation = true;
            Symbols.Symbol presym = this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$annotationArgRewriter$$$outer().scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromPrefix.widen().typeSymbol();
            Symbols.Symbol qual$1 = presym.owner();
            Names.TermName x$1 = presym.name().toTermName();
            Position x$2 = presym.pos();
            if (qual$1 == null) {
               throw null;
            } else {
               long x$3 = 0L;
               Symbols.TermSymbol thisSym = (Symbols.TermSymbol)qual$1.newTermSymbol(x$1, x$2, x$3).setInfo(this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$annotationArgRewriter$$$outer().scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromPrefix);
               return this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$annotationArgRewriter$$$outer().scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$$outer().gen().mkAttributedQualifier(this.scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$annotationArgRewriter$$$outer().scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$$seenFromPrefix, thisSym);
            }
         }

         public Trees.Tree transform(final Trees.Tree tree) {
            Trees.Tree var2 = super.transform(tree);
            return var2 instanceof Trees.This && this.matchesThis(tree.symbol()) ? this.newThis() : var2;
         }

         // $FF: synthetic method
         public AsSeenFromMap scala$reflect$internal$tpe$TypeMaps$AsSeenFromMap$annotationArgRewriter$$$outer() {
            return (AsSeenFromMap)this.$outer;
         }

         public annotationArgRewriter$() {
         }
      }
   }

   public abstract class SubstMap extends TypeMap {
      private List from;
      private List to;
      private boolean fromHasTermSymbol;
      private int fromMin;
      private int fromMax;
      private int fromSize;

      public final List accessFrom() {
         return this.from;
      }

      public final List accessTo() {
         return this.to;
      }

      public SubstMap reset(final List from0, final List to0) {
         SymbolTable var10000 = this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            label44: {
               SymbolTable isDeveloper_this = var10000;
               MutableSettings.SettingsOps$ var22 = MutableSettings.SettingsOps$.MODULE$;
               MutableSettings$ var23 = MutableSettings$.MODULE$;
               MutableSettings isDeveloper_SettingsOps_settings = isDeveloper_this.settings();
               MutableSettings var24 = isDeveloper_SettingsOps_settings;
               isDeveloper_SettingsOps_settings = null;
               MutableSettings isDeveloper_isDebug$extension_$this = var24;
               boolean var25 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(isDeveloper_isDebug$extension_$this.debug().value());
               isDeveloper_isDebug$extension_$this = null;
               if (!var25) {
                  MutableSettings.SettingsOps$ var26 = MutableSettings.SettingsOps$.MODULE$;
                  MutableSettings$ var27 = MutableSettings$.MODULE$;
                  MutableSettings isDeveloper_SettingsOps_settings = isDeveloper_this.settings();
                  MutableSettings var28 = isDeveloper_SettingsOps_settings;
                  isDeveloper_SettingsOps_settings = null;
                  MutableSettings isDeveloper_isDeveloper$extension_$this = var28;
                  boolean var29 = StatisticsStatics.DEVELOPER_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(isDeveloper_isDeveloper$extension_$this.developer().value());
                  isDeveloper_isDeveloper$extension_$this = null;
                  if (!var29) {
                     var30 = false;
                     break label44;
                  }
               }

               var30 = true;
            }

            Object var12 = null;
            Object var15 = null;
            Object var18 = null;
            if (var30) {
               var10000 = this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$$outer();
               SymbolTable var10001 = this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$$outer();
               List sameLength_xs2 = this.to;
               List sameLength_xs1 = this.from;
               if (var10001 == null) {
                  throw null;
               }

               boolean var32 = Collections.sameLength$(var10001, sameLength_xs1, sameLength_xs2);
               Object var20 = null;
               Object var21 = null;
               boolean assert_assertion = var32;
               if (var10000 == null) {
                  throw null;
               }

               SymbolTable assert_this = var10000;
               if (!assert_assertion) {
                  throw assert_this.throwAssertionError($anonfun$reset$1(this));
               }

               assert_this = null;
            }

            this.from = from0;
            this.to = to0;
            this.fromHasTermSymbol = false;
            this.fromMin = Integer.MAX_VALUE;
            this.fromMax = Integer.MIN_VALUE;
            this.fromSize = 0;
            this.scanFrom$1(this.from);
            return this;
         }
      }

      public boolean matches(final Symbols.Symbol sym, final Symbols.Symbol sym1) {
         return sym == sym1;
      }

      public abstract Types.Type toType(final Types.Type fromtp, final Object tp);

      public Types.Type renameBoundSyms(final Types.Type tp) {
         if (tp instanceof Types.MethodType) {
            Types.MethodType var2 = (Types.MethodType)tp;
            List ps = var2.params();
            Types.Type restp = var2.resultType();
            if (this.fromHasTermSymbol && this.fromContains(ps)) {
               return (Types.Type)this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$$outer().createFromClonedSymbols(ps, restp, (ps1, tp1) -> this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$$outer().copyMethodType(tp, ps1, tp1));
            }
         }

         if (tp instanceof Types.PolyType) {
            Types.PolyType var5 = (Types.PolyType)tp;
            List bs = var5.typeParams();
            Types.Type restp = var5.resultType();
            if (this.fromContains(bs)) {
               return (Types.Type)this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$$outer().createFromClonedSymbols(bs, restp, (ps1, tp1) -> this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$$outer().new PolyType(ps1, tp1));
            }
         }

         if (tp instanceof Types.ExistentialType) {
            Types.ExistentialType var8 = (Types.ExistentialType)tp;
            List bs = var8.quantified();
            Types.Type restp = var8.underlying();
            if (this.fromContains(bs)) {
               return (Types.Type)this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$$outer().createFromClonedSymbols(bs, restp, (quantified, underlying) -> {
                  SymbolTable var10000 = this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$$outer();
                  if (var10000 == null) {
                     throw null;
                  } else {
                     return Types.newExistentialType$(var10000, quantified, underlying);
                  }
               });
            }
         }

         return tp;
      }

      private Types.Type subst(final Types.Type tp, final Symbols.Symbol sym, final List from, final List to) {
         while(!from.isEmpty()) {
            if (this.matches((Symbols.Symbol)from.head(), sym)) {
               return this.toType(tp, to.head());
            }

            List var10002 = (List)from.tail();
            to = (List)to.tail();
            from = var10002;
            sym = sym;
            tp = tp;
         }

         return tp;
      }

      private boolean fromContains(final List syms) {
         for(List syms1 = syms; syms1 != scala.collection.immutable.Nil..MODULE$; syms1 = (List)syms1.tail()) {
            Symbols.Symbol sym = (Symbols.Symbol)syms1.head();
            if (this.fromContains$1(sym)) {
               return true;
            }
         }

         return false;
      }

      public Types.Type apply(final Types.Type tp0) {
         if (this.from.isEmpty()) {
            return tp0;
         } else {
            Types.Type tp = this.renameBoundSyms(tp0).mapOver(this);
            if (tp instanceof Types.TypeRef) {
               Types.TypeRef var3 = (Types.TypeRef)tp;
               Types.Type var4 = var3.pre();
               Symbols.Symbol sym = var3.sym();
               List args = var3.args();
               if (this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$$outer().NoPrefix().equals(var4)) {
                  Types.Type tcon = this.substFor$1(sym, tp);
                  if (tp != tcon && !args.isEmpty()) {
                     return this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$$outer().appliedType(tcon.typeConstructor(), args);
                  }

                  return tcon;
               }
            }

            if (tp instanceof Types.SingleType) {
               Types.SingleType var8 = (Types.SingleType)tp;
               Types.Type var9 = var8.pre();
               Symbols.Symbol sym = var8.sym();
               if (this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$$outer().NoPrefix().equals(var9)) {
                  return this.substFor$1(sym, tp);
               }
            }

            if (!(tp instanceof Types.ClassInfoType)) {
               return tp;
            } else {
               Types.ClassInfoType var11 = (Types.ClassInfoType)tp;
               List parents = var11.parents();
               Scopes.Scope decls = var11.decls();
               Symbols.Symbol sym = var11.typeSymbol();
               if (parents == null) {
                  throw null;
               } else {
                  List mapConserve_loop$3_pending = parents;
                  List mapConserve_loop$3_unchanged = parents;
                  scala.collection.immutable..colon.colon mapConserve_loop$3_mappedLast = null;
                  List mapConserve_loop$3_mappedHead = null;

                  while(!mapConserve_loop$3_pending.isEmpty()) {
                     Object mapConserve_loop$3_head0 = mapConserve_loop$3_pending.head();
                     Object mapConserve_loop$3_head1 = this.apply(mapConserve_loop$3_head0);
                     if (mapConserve_loop$3_head1 == mapConserve_loop$3_head0) {
                        mapConserve_loop$3_pending = (List)mapConserve_loop$3_pending.tail();
                        mapConserve_loop$3_unchanged = mapConserve_loop$3_unchanged;
                        mapConserve_loop$3_mappedLast = mapConserve_loop$3_mappedLast;
                        mapConserve_loop$3_mappedHead = mapConserve_loop$3_mappedHead;
                     } else {
                        List mapConserve_loop$3_xc = mapConserve_loop$3_unchanged;
                        List mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_mappedHead;

                        scala.collection.immutable..colon.colon mapConserve_loop$3_mappedLast1;
                        for(mapConserve_loop$3_mappedLast1 = mapConserve_loop$3_mappedLast; mapConserve_loop$3_xc != mapConserve_loop$3_pending; mapConserve_loop$3_xc = (List)mapConserve_loop$3_xc.tail()) {
                           scala.collection.immutable..colon.colon mapConserve_loop$3_next = new scala.collection.immutable..colon.colon(mapConserve_loop$3_xc.head(), scala.collection.immutable.Nil..MODULE$);
                           if (mapConserve_loop$3_mappedHead1 == null) {
                              mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_next;
                           }

                           if (mapConserve_loop$3_mappedLast1 != null) {
                              mapConserve_loop$3_mappedLast1.next_$eq(mapConserve_loop$3_next);
                           }

                           mapConserve_loop$3_mappedLast1 = mapConserve_loop$3_next;
                        }

                        scala.collection.immutable..colon.colon mapConserve_loop$3_next = new scala.collection.immutable..colon.colon(mapConserve_loop$3_head1, scala.collection.immutable.Nil..MODULE$);
                        if (mapConserve_loop$3_mappedHead1 == null) {
                           mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_next;
                        }

                        if (mapConserve_loop$3_mappedLast1 != null) {
                           mapConserve_loop$3_mappedLast1.next_$eq(mapConserve_loop$3_next);
                        }

                        List mapConserve_loop$3_tail0 = (List)mapConserve_loop$3_pending.tail();
                        mapConserve_loop$3_pending = mapConserve_loop$3_tail0;
                        mapConserve_loop$3_unchanged = mapConserve_loop$3_tail0;
                        mapConserve_loop$3_mappedLast = mapConserve_loop$3_next;
                        mapConserve_loop$3_mappedHead = mapConserve_loop$3_mappedHead1;
                     }
                  }

                  Object var10000;
                  if (mapConserve_loop$3_mappedHead == null) {
                     var10000 = mapConserve_loop$3_unchanged;
                  } else {
                     mapConserve_loop$3_mappedLast.next_$eq(mapConserve_loop$3_unchanged);
                     var10000 = mapConserve_loop$3_mappedHead;
                  }

                  mapConserve_loop$3_mappedHead = null;
                  Object var31 = null;
                  Object var32 = null;
                  Object var33 = null;
                  Object var34 = null;
                  Object var35 = null;
                  Object var36 = null;
                  Object var37 = null;
                  Object var38 = null;
                  Object var39 = null;
                  Object var40 = null;
                  Object var41 = null;
                  List mapConserve_result = (List)var10000;
                  Statics.releaseFence();
                  var10000 = mapConserve_result;
                  mapConserve_result = null;
                  List parents1 = (List)var10000;
                  if (parents1 == parents) {
                     return tp;
                  } else {
                     return this.scala$reflect$internal$tpe$TypeMaps$SubstMap$$$outer().new ClassInfoType(parents1, decls, sym);
                  }
               }
            }
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$SubstMap$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final String $anonfun$reset$1(final SubstMap $this) {
         return (new StringBuilder(30)).append("Unsound substitution from ").append($this.from).append(" to ").append($this.to).toString();
      }

      private final void scanFrom$1(final List ss) {
         List rest;
         for(; ss instanceof scala.collection.immutable..colon.colon; ss = rest) {
            scala.collection.immutable..colon.colon var2 = (scala.collection.immutable..colon.colon)ss;
            Symbols.Symbol sym = (Symbols.Symbol)var2.head();
            rest = var2.next$access$1();
            scala.math.package var10001 = scala.math.package..MODULE$;
            this.fromMin = Math.min(this.fromMin, sym.id());
            var10001 = scala.math.package..MODULE$;
            this.fromMax = Math.max(this.fromMax, sym.id());
            ++this.fromSize;
            if (sym.isTerm()) {
               this.fromHasTermSymbol = true;
            }
         }

      }

      private final boolean fromContains$1(final Symbols.Symbol sym) {
         int symId = sym.id();
         return symId >= this.fromMin && symId <= this.fromMax && (symId == this.fromMin || symId == this.fromMax || this.fromSize > 2 && this.from.contains(sym));
      }

      private final Types.Type substFor$1(final Symbols.Symbol sym, final Types.Type tp$4) {
         return this.subst(tp$4, sym, this.from, this.to);
      }

      public SubstMap(final List from0, final List to0) {
         this.from = from0;
         this.to = to0;
         this.fromHasTermSymbol = false;
         this.fromMin = Integer.MAX_VALUE;
         this.fromMax = Integer.MIN_VALUE;
         this.fromSize = 0;
         this.reset(from0, to0);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class SubstSymMap extends SubstMap {
      private volatile mapTreeSymbols$ mapTreeSymbols$module;

      public mapTreeSymbols$ mapTreeSymbols() {
         if (this.mapTreeSymbols$module == null) {
            this.mapTreeSymbols$lzycompute$1();
         }

         return this.mapTreeSymbols$module;
      }

      public final List scala$reflect$internal$tpe$TypeMaps$SubstSymMap$$from() {
         return this.accessFrom();
      }

      public final List scala$reflect$internal$tpe$TypeMaps$SubstSymMap$$to() {
         return this.accessTo();
      }

      public Types.Type toType(final Types.Type fromTpe, final Symbols.Symbol sym) {
         if (fromTpe instanceof Types.TypeRef) {
            Types.TypeRef var3 = (Types.TypeRef)fromTpe;
            Types.Type pre = var3.pre();
            List args = var3.args();
            return this.scala$reflect$internal$tpe$TypeMaps$SubstSymMap$$$outer().copyTypeRef(fromTpe, pre, sym, args);
         } else if (fromTpe instanceof Types.SingleType) {
            Types.Type pre = ((Types.SingleType)fromTpe).pre();
            return this.scala$reflect$internal$tpe$TypeMaps$SubstSymMap$$$outer().singleType(pre, sym);
         } else {
            throw new MatchError(fromTpe);
         }
      }

      private Symbols.Symbol subst(final Symbols.Symbol sym, final List from, final List to) {
         while(!from.isEmpty()) {
            if (this.matches((Symbols.Symbol)from.head(), sym)) {
               return (Symbols.Symbol)to.head();
            }

            List var10001 = (List)from.tail();
            to = (List)to.tail();
            from = var10001;
            sym = sym;
         }

         return sym;
      }

      private Symbols.Symbol substFor(final Symbols.Symbol sym) {
         return this.subst(sym, this.accessFrom(), this.accessTo());
      }

      public Types.Type apply(final Types.Type tpe) {
         if (this.accessFrom().isEmpty()) {
            return tpe;
         } else {
            if (tpe instanceof Types.TypeRef) {
               Types.TypeRef var2 = (Types.TypeRef)tpe;
               Types.Type pre = var2.pre();
               Symbols.Symbol sym = var2.sym();
               List args = var2.args();
               if (pre != this.scala$reflect$internal$tpe$TypeMaps$SubstSymMap$$$outer().NoPrefix()) {
                  Symbols.Symbol newSym = this.substFor(sym);
                  return (sym == newSym ? tpe : this.scala$reflect$internal$tpe$TypeMaps$SubstSymMap$$$outer().copyTypeRef(tpe, pre, newSym, args)).mapOver(this);
               }
            }

            if (tpe instanceof Types.SingleType) {
               Types.SingleType var7 = (Types.SingleType)tpe;
               Types.Type pre = var7.pre();
               Symbols.Symbol sym = var7.sym();
               if (pre != this.scala$reflect$internal$tpe$TypeMaps$SubstSymMap$$$outer().NoPrefix()) {
                  Symbols.Symbol newSym = this.substFor(sym);
                  return (sym == newSym ? tpe : this.scala$reflect$internal$tpe$TypeMaps$SubstSymMap$$$outer().singleType(pre, newSym)).mapOver(this);
               }
            }

            if (tpe instanceof Types.RefinedType) {
               Types.RefinedType var11 = (Types.RefinedType)tpe;
               Symbols.Symbol owner = tpe.typeSymbol().owner();
               Symbols.Symbol newOwner = this.substFor(owner);
               return (newOwner == owner ? tpe : this.scala$reflect$internal$tpe$TypeMaps$SubstSymMap$$$outer().copyRefinedType(var11, var11.parents(), var11.decls(), newOwner)).mapOver(this);
            } else {
               return super.apply(tpe);
            }
         }
      }

      public Trees.Tree mapOver(final Trees.Tree tree, final Function0 giveup) {
         return this.mapTreeSymbols().transform(tree);
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$SubstSymMap$$$outer() {
         return this.$outer;
      }

      private final void mapTreeSymbols$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.mapTreeSymbols$module == null) {
               this.mapTreeSymbols$module = new mapTreeSymbols$();
            }
         } catch (Throwable var2) {
            throw var2;
         }

      }

      public SubstSymMap(final List from0, final List to0) {
         super(from0, to0);
      }

      public SubstSymMap(final Seq pairs) {
         this(pairs.toList().map(new Serializable() {
            private static final long serialVersionUID = 0L;

            public final Symbols.Symbol apply(final Tuple2 x$14) {
               return (Symbols.Symbol)x$14._1();
            }
         }), pairs.toList().map(new Serializable() {
            private static final long serialVersionUID = 0L;

            public final Symbols.Symbol apply(final Tuple2 x$15) {
               return (Symbols.Symbol)x$15._2();
            }
         }));
      }

      public class mapTreeSymbols$ extends TypeMap.TypeMapTransformer {
         private final Trees.InternalTreeCopierOps strictCopy = (Trees.InternalTreeCopierOps)SubstSymMap.this.scala$reflect$internal$tpe$TypeMaps$SubstSymMap$$$outer().newStrictTreeCopier();

         public Trees.InternalTreeCopierOps strictCopy() {
            return this.strictCopy;
         }

         public Trees.Tree transformIfMapped(final Trees.Tree tree, final Function1 trans) {
            SubstSymMap var10000 = this.scala$reflect$internal$tpe$TypeMaps$SubstSymMap$mapTreeSymbols$$$outer();
            if (var10000 == null) {
               throw null;
            } else {
               int var3 = var10000.accessFrom().indexOf(tree.symbol());
               switch (var3) {
                  case -1:
                     return tree;
                  default:
                     var10000 = this.scala$reflect$internal$tpe$TypeMaps$SubstSymMap$mapTreeSymbols$$$outer();
                     if (var10000 == null) {
                        throw null;
                     } else {
                        List var6 = var10000.accessTo();
                        if (var6 == null) {
                           throw null;
                        } else {
                           Symbols.Symbol toSym = (Symbols.Symbol)LinearSeqOps.apply$(var6, var3);
                           return ((Trees.Tree)trans.apply(toSym)).setSymbol(toSym).setType(tree.tpe());
                        }
                     }
               }
            }
         }

         public Trees.Tree transform(final Trees.Tree tree) {
            Trees.Tree var2 = super.transform(tree);
            if (var2 instanceof Trees.Ident) {
               Trees.Ident var3 = (Trees.Ident)var2;
               SubstSymMap var12 = this.scala$reflect$internal$tpe$TypeMaps$SubstSymMap$mapTreeSymbols$$$outer();
               if (var12 == null) {
                  throw null;
               } else {
                  int var6 = var12.accessFrom().indexOf(((Trees.Tree)var3).symbol());
                  switch (var6) {
                     case -1:
                        return var3;
                     default:
                        var12 = this.scala$reflect$internal$tpe$TypeMaps$SubstSymMap$mapTreeSymbols$$$outer();
                        if (var12 == null) {
                           throw null;
                        } else {
                           List var14 = var12.accessTo();
                           if (var14 == null) {
                              throw null;
                           } else {
                              Symbols.Symbol transformIfMapped_toSym = (Symbols.Symbol)LinearSeqOps.apply$(var14, var6);
                              return $anonfun$transform$1(this, var3, transformIfMapped_toSym).setSymbol(transformIfMapped_toSym).setType(((Trees.Tree)var3).tpe());
                           }
                        }
                  }
               }
            } else if (var2 instanceof Trees.Select) {
               Trees.Select var4 = (Trees.Select)var2;
               Trees.Tree qual = var4.qualifier();
               SubstSymMap var10000 = this.scala$reflect$internal$tpe$TypeMaps$SubstSymMap$mapTreeSymbols$$$outer();
               if (var10000 == null) {
                  throw null;
               } else {
                  int var8 = var10000.accessFrom().indexOf(((Trees.Tree)var4).symbol());
                  switch (var8) {
                     case -1:
                        return var4;
                     default:
                        var10000 = this.scala$reflect$internal$tpe$TypeMaps$SubstSymMap$mapTreeSymbols$$$outer();
                        if (var10000 == null) {
                           throw null;
                        } else {
                           List var11 = var10000.accessTo();
                           if (var11 == null) {
                              throw null;
                           } else {
                              Symbols.Symbol transformIfMapped_toSym = (Symbols.Symbol)LinearSeqOps.apply$(var11, var8);
                              return $anonfun$transform$2(this, var4, qual, transformIfMapped_toSym).setSymbol(transformIfMapped_toSym).setType(((Trees.Tree)var4).tpe());
                           }
                        }
                  }
               }
            } else {
               return var2;
            }
         }

         // $FF: synthetic method
         public SubstSymMap scala$reflect$internal$tpe$TypeMaps$SubstSymMap$mapTreeSymbols$$$outer() {
            return (SubstSymMap)this.$outer;
         }

         // $FF: synthetic method
         public static final Trees.Ident $anonfun$transform$1(final mapTreeSymbols$ $this, final Trees.Ident x2$1, final Symbols.Symbol toSym) {
            return (Trees.Ident)$this.strictCopy().Ident(x2$1, toSym.name());
         }

         // $FF: synthetic method
         public static final Trees.Select $anonfun$transform$2(final mapTreeSymbols$ $this, final Trees.Select x4$1, final Trees.Tree qual$1, final Symbols.Symbol toSym) {
            return (Trees.Select)$this.strictCopy().Select(x4$1, qual$1, toSym.name());
         }
      }
   }

   public class SubstSymMap$ {
      // $FF: synthetic field
      private final SymbolTable $outer;

      public SubstSymMap apply() {
         return this.$outer.new SubstSymMap(scala.collection.immutable.Nil..MODULE$);
      }

      public SubstSymMap apply(final List from, final List to) {
         return this.$outer.new SubstSymMap(from, to);
      }

      public SubstSymMap apply(final Tuple2 fromto) {
         return this.$outer.new SubstSymMap(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Tuple2[]{fromto}));
      }

      public SubstSymMap$() {
         if (TypeMaps.this == null) {
            throw null;
         } else {
            this.$outer = TypeMaps.this;
            super();
         }
      }
   }

   public class SubstTypeMap extends SubstMap {
      public final List from() {
         return this.accessFrom();
      }

      public final List to() {
         return this.accessTo();
      }

      public Types.Type toType(final Types.Type fromtp, final Types.Type tp) {
         return tp;
      }

      public Trees.Tree mapOver(final Trees.Tree tree, final Function0 giveup) {
         LazyRef trans$module = new LazyRef();
         trans$1$ var10000;
         if (trans$module.initialized()) {
            var10000 = (trans$1$)trans$module.value();
         } else {
            synchronized(trans$module){}

            trans$1$ var4;
            try {
               var4 = trans$module.initialized() ? (trans$1$)trans$module.value() : (trans$1$)trans$module.initialize(new trans$1$(giveup));
            } catch (Throwable var6) {
               throw var6;
            }

            var10000 = var4;
            var4 = null;
         }

         Object var8 = null;
         return var10000.transform(tree);
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$SubstTypeMap$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      private final trans$1$ trans$lzycompute$1(final LazyRef trans$module$1, final Function0 giveup$1) {
         synchronized(trans$module$1){}

         trans$1$ var3;
         try {
            var3 = trans$module$1.initialized() ? (trans$1$)trans$module$1.value() : (trans$1$)trans$module$1.initialize(new trans$1$(giveup$1));
         } catch (Throwable var5) {
            throw var5;
         }

         return var3;
      }

      private final trans$1$ trans$2(final LazyRef trans$module$1, final Function0 giveup$1) {
         class trans$1$ extends TypeMap.TypeMapTransformer {
            // $FF: synthetic field
            private final SubstTypeMap $outer;
            private final Function0 giveup$1;

            public Trees.Tree transform(final Trees.Tree tree) {
               if (tree instanceof Trees.Ident) {
                  SubstTypeMap var10000 = this.$outer;
                  if (var10000 == null) {
                     throw null;
                  } else {
                     int var2 = var10000.accessFrom().indexOf(tree.symbol());
                     switch (var2) {
                        case -1:
                           return super.transform(tree);
                        default:
                           var10000 = this.$outer;
                           if (var10000 == null) {
                              throw null;
                           } else {
                              List var5 = var10000.accessTo();
                              if (var5 == null) {
                                 throw null;
                              } else {
                                 Types.Type totpe = (Types.Type)LinearSeqOps.apply$(var5, var2);
                                 return totpe.isStable() ? tree.duplicate().setType(totpe) : (Trees.Tree)this.giveup$1.apply();
                              }
                           }
                     }
                  }
               } else {
                  return super.transform(tree);
               }
            }

            public trans$1$(final Function0 giveup$1) {
               if (SubstTypeMap.this == null) {
                  throw null;
               } else {
                  this.$outer = SubstTypeMap.this;
                  this.giveup$1 = giveup$1;
                  super();
               }
            }
         }

         if (trans$module$1.initialized()) {
            return (trans$1$)trans$module$1.value();
         } else {
            synchronized(trans$module$1){}

            trans$1$ var3;
            try {
               var3 = trans$module$1.initialized() ? (trans$1$)trans$module$1.value() : (trans$1$)trans$module$1.initialize(new trans$1$(giveup$1));
            } catch (Throwable var5) {
               throw var5;
            }

            return var3;
         }
      }

      public SubstTypeMap(final List from0, final List to0) {
         super(from0, to0);
      }
   }

   public class SubstThisMap extends TypeMap {
      private final Symbols.Symbol from;
      private final Types.Type to;

      public Types.Type apply(final Types.Type tp) {
         if (tp instanceof Types.ThisType) {
            Symbols.Symbol var10000 = ((Types.ThisType)tp).sym();
            Symbols.Symbol var2 = this.from;
            if (var10000 == null) {
               if (var2 == null) {
                  return this.to;
               }
            } else if (var10000.equals(var2)) {
               return this.to;
            }
         }

         return tp.mapOver(this);
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$SubstThisMap$$$outer() {
         return this.$outer;
      }

      public SubstThisMap(final Symbols.Symbol from, final Types.Type to) {
         this.from = from;
         this.to = to;
      }
   }

   public class SubstWildcardMap extends TypeMap {
      private final List from;

      public Types.Type apply(final Types.Type tp) {
         Object var10000;
         try {
            if (tp instanceof Types.TypeRef) {
               Symbols.Symbol sym = ((Types.TypeRef)tp).sym();
               if (this.from.contains(sym)) {
                  var10000 = this.scala$reflect$internal$tpe$TypeMaps$SubstWildcardMap$$$outer().new BoundedWildcardType(sym.info().bounds());
                  return (Types.Type)var10000;
               }
            }

            var10000 = tp.mapOver(this);
         } catch (Types.MalformedType var3) {
            var10000 = this.scala$reflect$internal$tpe$TypeMaps$SubstWildcardMap$$$outer().WildcardType();
         }

         return (Types.Type)var10000;
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$SubstWildcardMap$$$outer() {
         return this.$outer;
      }

      public SubstWildcardMap(final List from) {
         this.from = from;
      }
   }

   public class IsDependentCollector$ extends TypeCollector {
      public void apply(final Types.Type tp) {
         if (tp.isImmediatelyDependent()) {
            this.result_$eq(true);
         } else if (!BoxesRunTime.unboxToBoolean(this.result())) {
            tp.dealias().foldOver(this);
         }
      }

      public IsDependentCollector$() {
         super(false);
      }
   }

   public class ApproximateDependentMap$ extends TypeMap {
      public Types.Type apply(final Types.Type tp) {
         return (Types.Type)(tp.isImmediatelyDependent() ? this.scala$reflect$internal$tpe$TypeMaps$ApproximateDependentMap$$$outer().WildcardType() : tp.mapOver(this));
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$ApproximateDependentMap$$$outer() {
         return this.$outer;
      }
   }

   public class InstantiateDependentMap extends TypeMap implements KeepOnlyTypeConstraints {
      private volatile StableArgTp$ StableArgTp$module;
      private volatile UnstableArgTp$ UnstableArgTp$module;
      private volatile StabilizedArgTp$ StabilizedArgTp$module;
      public final List scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$params;
      private final List actuals0;
      private Types.Type[] _actuals;
      private Symbols.Symbol[] _existentials;

      public boolean keepAnnotation(final AnnotationInfos.AnnotationInfo annot) {
         return TypeMaps.KeepOnlyTypeConstraints.super.keepAnnotation(annot);
      }

      // $FF: synthetic method
      public AnnotationInfos.AnnotationInfo scala$reflect$internal$tpe$TypeMaps$AnnotationFilter$$super$mapOver(final AnnotationInfos.AnnotationInfo annot) {
         return super.mapOver(annot);
      }

      public AnnotationInfos.AnnotationInfo mapOver(final AnnotationInfos.AnnotationInfo annot) {
         return TypeMaps.AnnotationFilter.super.mapOver(annot);
      }

      public StableArgTp$ scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$StableArgTp() {
         if (this.StableArgTp$module == null) {
            this.StableArgTp$lzycompute$1();
         }

         return this.StableArgTp$module;
      }

      public UnstableArgTp$ scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$UnstableArgTp() {
         if (this.UnstableArgTp$module == null) {
            this.UnstableArgTp$lzycompute$1();
         }

         return this.UnstableArgTp$module;
      }

      private StabilizedArgTp$ StabilizedArgTp() {
         if (this.StabilizedArgTp$module == null) {
            this.StabilizedArgTp$lzycompute$1();
         }

         return this.StabilizedArgTp$module;
      }

      public Types.Type[] scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$actuals() {
         if (this._actuals == null) {
            List var10000 = this.actuals0;
            if (var10000 == null) {
               throw null;
            }

            Types.Type[] temp = new Types.Type[SeqOps.size$(var10000)];
            int i = 0;

            for(List l = this.actuals0; i < temp.length; ++i) {
               temp[i] = (Types.Type)l.head();
               l = (List)l.tail();
            }

            this._actuals = temp;
         }

         return this._actuals;
      }

      private Symbols.Symbol[] existentials() {
         if (this._existentials == null) {
            this._existentials = new Symbols.Symbol[this.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$actuals().length];
         }

         return this._existentials;
      }

      public List existentialsNeeded() {
         return (List)(this._existentials == null ? scala.collection.immutable.Nil..MODULE$ : scala.collection.ArrayOps..MODULE$.iterator$extension(this.existentials()).filter((x$18) -> BoxesRunTime.boxToBoolean($anonfun$existentialsNeeded$1(x$18))).toList());
      }

      public Symbols.Symbol scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$existentialFor(final int pid) {
         if (this.existentials()[pid] == null) {
            List var10000 = this.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$params;
            if (var10000 == null) {
               throw null;
            }

            Symbols.Symbol param = (Symbols.Symbol)LinearSeqOps.apply$(var10000, pid);
            Symbols.Symbol[] var13 = this.existentials();
            Symbols.TypeSymbol var10002 = param.owner();
            Names.TypeName var10003 = (Names.TypeName)param.name().toTypeName().append(".type");
            Position var10004 = param.pos();
            long newExistential_newFlags = param.flags();
            Position newExistential_pos = var10004;
            Names.TypeName newExistential_name = var10003;
            if (var10002 == null) {
               throw null;
            }

            Symbols.Symbol newExistential_this = var10002;
            long newExistential_newAbstractType_newFlags = 34359738368L | newExistential_newFlags;
            var10002 = newExistential_this.createAbstractTypeSymbol(newExistential_name, newExistential_pos, 16L | newExistential_newAbstractType_newFlags);
            Object var10 = null;
            newExistential_name = null;
            newExistential_pos = null;
            var13[pid] = var10002.setInfo(this.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$$outer().singletonBounds(this.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$actuals()[pid]));
         }

         return this.existentials()[pid];
      }

      public Types.Type apply(final Types.Type tp) {
         if (tp instanceof Types.SingleType) {
            Types.SingleType var2 = (Types.SingleType)tp;
            Types.Type var3 = var2.pre();
            Symbols.Symbol var4 = var2.sym();
            if (this.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$$outer().NoPrefix().equals(var3) && var4 != null) {
               Option var5 = this.StabilizedArgTp().unapply(var4);
               if (!var5.isEmpty()) {
                  return (Types.Type)var5.get();
               }
            }
         }

         return tp.mapOver(this);
      }

      public Trees.Tree mapOver(final Trees.Tree arg, final Function0 giveup) {
         LazyRef treeTrans$module = new LazyRef();
         return this.treeTrans$2(treeTrans$module).transform(arg);
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public TypeMaps scala$reflect$internal$tpe$TypeMaps$KeepOnlyTypeConstraints$$$outer() {
         return this.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$$outer();
      }

      // $FF: synthetic method
      public TypeMaps scala$reflect$internal$tpe$TypeMaps$AnnotationFilter$$$outer() {
         return this.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$$outer();
      }

      private final void StableArgTp$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.StableArgTp$module == null) {
               this.StableArgTp$module = new StableArgTp$();
            }
         } catch (Throwable var2) {
            throw var2;
         }

      }

      private final void UnstableArgTp$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.UnstableArgTp$module == null) {
               this.UnstableArgTp$module = new UnstableArgTp$();
            }
         } catch (Throwable var2) {
            throw var2;
         }

      }

      private final void StabilizedArgTp$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.StabilizedArgTp$module == null) {
               this.StabilizedArgTp$module = new StabilizedArgTp$();
            }
         } catch (Throwable var2) {
            throw var2;
         }

      }

      // $FF: synthetic method
      public static final boolean $anonfun$existentialsNeeded$1(final Symbols.Symbol x$18) {
         return x$18 != null;
      }

      // $FF: synthetic method
      private final treeTrans$1$ treeTrans$lzycompute$1(final LazyRef treeTrans$module$1) {
         synchronized(treeTrans$module$1){}

         treeTrans$1$ var2;
         try {
            class treeTrans$1$ extends scala.reflect.api.Trees.Transformer {
               // $FF: synthetic field
               private final InstantiateDependentMap $outer;

               public Trees.Tree transform(final Trees.Tree tree) {
                  Symbols.Symbol var2 = tree.symbol();
                  if (var2 != null) {
                     Option var3 = this.$outer.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$StableArgTp().unapply(var2);
                     if (!var3.isEmpty()) {
                        Types.Type tp = (Types.Type)var3.get();
                        return this.$outer.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$$outer().gen().mkAttributedQualifier(tp, tree.symbol());
                     }
                  }

                  if (var2 != null) {
                     Option var5 = this.$outer.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$UnstableArgTp().unapply(var2);
                     if (!var5.isEmpty()) {
                        Symbols.Symbol quant = (Symbols.Symbol)((Tuple2)var5.get())._1();
                        Types.Type tp = (Types.Type)((Tuple2)var5.get())._2();
                        return this.$outer.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$$outer().Ident(quant).copyAttrs(tree).setType(tp);
                     }
                  }

                  return (Trees.Tree)super.transform(tree);
               }

               public treeTrans$1$() {
                  if (InstantiateDependentMap.this == null) {
                     throw null;
                  } else {
                     this.$outer = InstantiateDependentMap.this;
                     super();
                  }
               }
            }

            var2 = treeTrans$module$1.initialized() ? (treeTrans$1$)treeTrans$module$1.value() : (treeTrans$1$)treeTrans$module$1.initialize(new treeTrans$1$());
         } catch (Throwable var4) {
            throw var4;
         }

         return var2;
      }

      private final treeTrans$1$ treeTrans$2(final LazyRef treeTrans$module$1) {
         return treeTrans$module$1.initialized() ? (treeTrans$1$)treeTrans$module$1.value() : this.treeTrans$lzycompute$1(treeTrans$module$1);
      }

      public InstantiateDependentMap(final List params, final List actuals0) {
         this.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$params = params;
         this.actuals0 = actuals0;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }

      private class StableArgTp$ {
         // $FF: synthetic field
         private final InstantiateDependentMap $outer;

         public Option unapply(final Symbols.Symbol param) {
            int var2 = this.$outer.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$params.indexOf(param);
            switch (var2) {
               case -1:
                  return scala.None..MODULE$;
               default:
                  Types.Type tp = this.$outer.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$actuals()[var2];
                  if (tp.isStable()) {
                     Symbols.Symbol var10000 = tp.typeSymbol();
                     Definitions.DefinitionsClass.NothingClass$ var4 = this.$outer.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$$outer().definitions().NothingClass();
                     if (var10000 == null) {
                        if (var4 != null) {
                           return new Some(tp);
                        }
                     } else if (!var10000.equals(var4)) {
                        return new Some(tp);
                     }
                  }

                  return scala.None..MODULE$;
            }
         }

         public StableArgTp$() {
            if (InstantiateDependentMap.this == null) {
               throw null;
            } else {
               this.$outer = InstantiateDependentMap.this;
               super();
            }
         }
      }

      private class UnstableArgTp$ {
         // $FF: synthetic field
         private final InstantiateDependentMap $outer;

         public Option unapply(final Symbols.Symbol param) {
            int var2 = this.$outer.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$params.indexOf(param);
            switch (var2) {
               case -1:
                  return scala.None..MODULE$;
               default:
                  Symbols.Symbol sym = this.$outer.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$existentialFor(var2);
                  return new Some(new Tuple2(sym, sym.tpe_$times()));
            }
         }

         public UnstableArgTp$() {
            if (InstantiateDependentMap.this == null) {
               throw null;
            } else {
               this.$outer = InstantiateDependentMap.this;
               super();
            }
         }
      }

      private class StabilizedArgTp$ {
         // $FF: synthetic field
         private final InstantiateDependentMap $outer;

         public Option unapply(final Symbols.Symbol param) {
            if (param != null) {
               Option var2 = this.$outer.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$StableArgTp().unapply(param);
               if (!var2.isEmpty()) {
                  Types.Type tp = (Types.Type)var2.get();
                  return new Some(tp);
               }
            }

            if (param != null) {
               Option var4 = this.$outer.scala$reflect$internal$tpe$TypeMaps$InstantiateDependentMap$$UnstableArgTp().unapply(param);
               if (!var4.isEmpty()) {
                  Types.Type tp = (Types.Type)((Tuple2)var4.get())._2();
                  return new Some(tp);
               }
            }

            return scala.None..MODULE$;
         }

         public StabilizedArgTp$() {
            if (InstantiateDependentMap.this == null) {
               throw null;
            } else {
               this.$outer = InstantiateDependentMap.this;
               super();
            }
         }
      }
   }

   public class identityTypeMap$ extends TypeMap {
      public Types.Type apply(final Types.Type tp) {
         return tp.mapOver(this);
      }
   }

   public class typeVarToOriginMap$ extends TypeMap {
      public Types.Type apply(final Types.Type tp) {
         return tp instanceof Types.TypeVar ? ((Types.TypeVar)tp).origin() : tp.mapOver(this);
      }
   }

   public abstract class ExistsTypeRefCollector extends TypeCollector {
      private CollectingTraverser findInTree;
      private volatile boolean bitmap$0;

      public abstract boolean pred(final Symbols.Symbol sym);

      public void apply(final Types.Type tp) {
         if (!BoxesRunTime.unboxToBoolean(this.result())) {
            if (tp instanceof Types.ExistentialType) {
               tp.foldOver(this);
            } else {
               if (tp instanceof Types.TypeRef) {
                  Symbols.Symbol sym1 = ((Types.TypeRef)tp).sym();
                  if (this.pred(sym1)) {
                     this.result_$eq(true);
                     return;
                  }
               }

               Types.Type var3 = tp.normalize();
               if (var3 instanceof Types.TypeRef) {
                  Symbols.Symbol sym1 = ((Types.TypeRef)var3).sym();
                  if (this.pred(sym1)) {
                     this.result_$eq(true);
                     return;
                  }
               }

               if (var3 instanceof Types.RefinedType) {
                  Types.RefinedType var5 = (Types.RefinedType)var3;
                  tp.prefix().foldOver(this);
                  var5.foldOver(this);
               } else {
                  if (var3 instanceof Types.SingleType) {
                     Symbols.Symbol sym1 = ((Types.SingleType)var3).sym();
                     if (this.pred(sym1)) {
                        this.result_$eq(true);
                        return;
                     }
                  }

                  tp.foldOver(this);
               }
            }
         }
      }

      private CollectingTraverser findInTree$lzycompute() {
         synchronized(this){}

         try {
            if (!this.bitmap$0) {
               this.findInTree = new CollectingTraverser((t) -> BoxesRunTime.boxToBoolean($anonfun$findInTree$1(this, t)));
               this.bitmap$0 = true;
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.findInTree;
      }

      private CollectingTraverser findInTree() {
         return !this.bitmap$0 ? this.findInTree$lzycompute() : this.findInTree;
      }

      public void foldOver(final Trees.Tree arg) {
         if (!BoxesRunTime.unboxToBoolean(this.result())) {
            this.findInTree().collect(arg);
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$ExistsTypeRefCollector$$$outer() {
         return this.$outer;
      }

      private final boolean inTree$1(final Trees.Tree t) {
         if (this.pred(t.symbol())) {
            this.result_$eq(true);
         } else {
            this.apply(t.tpe());
         }

         return BoxesRunTime.unboxToBoolean(this.result());
      }

      // $FF: synthetic method
      public static final boolean $anonfun$findInTree$1(final ExistsTypeRefCollector $this, final Trees.Tree t) {
         return $this.inTree$1(t);
      }

      public ExistsTypeRefCollector() {
         super(false);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }

      private class CollectingTraverser extends Trees.FindTreeTraverser {
         // $FF: synthetic field
         public final ExistsTypeRefCollector $outer;

         public boolean collect(final Trees.Tree arg) {
            this.result_$eq(scala.None..MODULE$);
            this.traverse(arg);
            return this.result().isDefined();
         }

         // $FF: synthetic method
         public ExistsTypeRefCollector scala$reflect$internal$tpe$TypeMaps$ExistsTypeRefCollector$CollectingTraverser$$$outer() {
            return this.$outer;
         }

         public CollectingTraverser(final Function1 p) {
            if (ExistsTypeRefCollector.this == null) {
               throw null;
            } else {
               this.$outer = ExistsTypeRefCollector.this;
               super(p);
            }
         }
      }
   }

   public class ContainsCollector extends ExistsTypeRefCollector {
      private Symbols.Symbol sym;

      public void reset(final Symbols.Symbol nsym) {
         this.result_$eq(false);
         this.sym = nsym;
      }

      public boolean pred(final Symbols.Symbol sym1) {
         Symbols.Symbol var2 = this.sym;
         if (sym1 == null) {
            if (var2 == null) {
               return true;
            }
         } else if (sym1.equals(var2)) {
            return true;
         }

         return false;
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$ContainsCollector$$$outer() {
         return this.$outer;
      }

      public ContainsCollector(final Symbols.Symbol sym) {
         this.sym = sym;
         super();
      }
   }

   public class ContainsAnyKeyCollector extends ExistsTypeRefCollector {
      private final HashMap symMap;

      public boolean pred(final Symbols.Symbol sym1) {
         return this.symMap.contains(sym1);
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$ContainsAnyKeyCollector$$$outer() {
         return this.$outer;
      }

      public ContainsAnyKeyCollector(final HashMap symMap) {
         this.symMap = symMap;
      }
   }

   public class FilterTypeCollector extends TypeCollector {
      private final Function1 p;

      public List collect(final Types.Type tp) {
         return ((List)super.collect(tp)).reverse();
      }

      public void apply(final Types.Type tp) {
         if (BoxesRunTime.unboxToBoolean(this.p.apply(tp))) {
            List var10001 = (List)this.result();
            if (var10001 == null) {
               throw null;
            }

            List $colon$colon_this = var10001;
            scala.collection.immutable..colon.colon var4 = new scala.collection.immutable..colon.colon(tp, $colon$colon_this);
            Object var3 = null;
            this.result_$eq(var4);
         }

         tp.foldOver(this);
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$FilterTypeCollector$$$outer() {
         return this.$outer;
      }

      public FilterTypeCollector(final Function1 p) {
         super(scala.collection.immutable.Nil..MODULE$);
         this.p = p;
      }
   }

   public class CollectTypeCollector extends TypeCollector {
      private final PartialFunction pf;
      private final ListBuffer buffer;

      public ListBuffer buffer() {
         return this.buffer;
      }

      public List collect(final Types.Type tp) {
         this.apply(tp);
         List result = this.buffer().result();
         this.buffer().clear();
         return result;
      }

      public void apply(final Types.Type tp) {
         if (this.pf.isDefinedAt(tp)) {
            ListBuffer var10000 = this.buffer();
            Object $plus$eq_elem = this.pf.apply(tp);
            if (var10000 == null) {
               throw null;
            }

            var10000.addOne($plus$eq_elem);
            $plus$eq_elem = null;
         }

         tp.foldOver(this);
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$CollectTypeCollector$$$outer() {
         return this.$outer;
      }

      public CollectTypeCollector(final PartialFunction pf) {
         super(scala.collection.immutable.Nil..MODULE$);
         this.pf = pf;
         ListBuffer var10001 = scala.collection.mutable.ListBuffer..MODULE$;
         this.buffer = new ListBuffer();
      }
   }

   public class ForEachTypeTraverser extends TypeTraverser {
      private final Function1 f;

      public void traverse(final Types.Type tp) {
         this.f.apply(tp);
         tp.mapOver(this);
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$ForEachTypeTraverser$$$outer() {
         return this.$outer;
      }

      public ForEachTypeTraverser(final Function1 f) {
         this.f = f;
      }
   }

   public class FindTypeCollector extends TypeCollector {
      private final Function1 p;

      public void apply(final Types.Type tp) {
         if (((Option)this.result()).isEmpty()) {
            if (BoxesRunTime.unboxToBoolean(this.p.apply(tp))) {
               this.result_$eq(new Some(tp));
            } else {
               tp.foldOver(this);
            }
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$FindTypeCollector$$$outer() {
         return this.$outer;
      }

      public FindTypeCollector(final Function1 p) {
         super(scala.None..MODULE$);
         this.p = p;
      }
   }

   public class ErroneousCollector$ extends TypeCollector {
      public void apply(final Types.Type tp) {
         if (!BoxesRunTime.unboxToBoolean(this.result())) {
            this.result_$eq(tp.isError());
            if (!BoxesRunTime.unboxToBoolean(this.result())) {
               tp.foldOver(this);
            }
         }
      }

      public ErroneousCollector$() {
         super(false);
      }
   }

   public class adaptToNewRunMap$ extends TypeMap {
      private Symbols.Symbol adaptToNewRun(final Types.Type pre, final Symbols.Symbol sym) {
         if (!this.scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer().phase().flatClasses() && !sym.isRootSymbol() && pre != this.scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer().NoPrefix() && pre != this.scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer().NoType() && !sym.isPackageClass()) {
            if (sym.isModuleClass()) {
               Symbols.Symbol sourceModule1 = this.adaptToNewRun(pre, sym.sourceModule());
               Symbols.Symbol var26 = sourceModule1.moduleClass();
               if (var26 == null) {
                  throw null;
               } else {
                  Symbols.Symbol orElse_this = var26;
                  var26 = orElse_this != orElse_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol() ? orElse_this : $anonfun$adaptToNewRun$1(sourceModule1);
                  orElse_this = null;
                  if (var26 == null) {
                     throw null;
                  } else {
                     Symbols.Symbol orElse_this = var26;
                     return orElse_this != orElse_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol() ? orElse_this : $anonfun$adaptToNewRun$2(this, sym, sourceModule1);
                  }
               }
            } else {
               Symbols.Symbol var10000 = pre.findMember(sym.name(), 67108864L, 0L, true);
               if (var10000 == null) {
                  throw null;
               } else {
                  Symbols.Symbol orElse_this = var10000;
                  if (orElse_this == orElse_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                     throw $anonfun$adaptToNewRun$4(this, sym, pre);
                  } else {
                     var10000 = orElse_this;
                     orElse_this = null;
                     Symbols.Symbol create_e = var10000;
                     ObjectRef var21 = new ObjectRef(create_e);
                     create_e = null;
                     ObjectRef rebind0 = var21;
                     if (!this.corresponds$1(sym.owner(), ((Symbols.Symbol)rebind0.elem).owner())) {
                        this.scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer().debuglog(() -> (new StringBuilder(32)).append("ADAPT1 pre = ").append(pre).append(", sym = ").append(sym.fullLocationString()).append(", rebind = ").append(((Symbols.Symbol)rebind0.elem).fullLocationString()).toString());
                        List var22 = pre.baseClasses();
                        if (var22 == null) {
                           throw null;
                        }

                        LinearSeq dropWhile_loop$3_s;
                        for(dropWhile_loop$3_s = (LinearSeq)var22.coll(); dropWhile_loop$3_s.nonEmpty(); dropWhile_loop$3_s = (LinearSeq)dropWhile_loop$3_s.tail()) {
                           Symbols.Symbol var14 = (Symbols.Symbol)dropWhile_loop$3_s.head();
                           if (!$anonfun$adaptToNewRun$7(this, sym, var14)) {
                              break;
                           }
                        }

                        LinearSeq var23 = dropWhile_loop$3_s;
                        Object var19 = null;
                        List bcs = (List)var23;
                        if (bcs.isEmpty()) {
                           SymbolTable var24 = this.scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer();
                           boolean assert_assertion = pre.typeSymbol().isRefinementClass();
                           if (var24 == null) {
                              throw null;
                           }

                           SymbolTable assert_this = var24;
                           if (!assert_assertion) {
                              throw assert_this.throwAssertionError(pre);
                           }

                           assert_this = null;
                        } else {
                           rebind0.elem = pre.baseType((Symbols.Symbol)bcs.head()).member(sym.name());
                        }

                        this.scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer().debuglog(() -> (new StringBuilder(45)).append("ADAPT2 pre = ").append(pre).append(", bcs.head = ").append(bcs.head()).append(", sym = ").append(sym.fullLocationString()).append(", rebind = ").append(((Symbols.Symbol)rebind0.elem).fullLocationString()).toString());
                     }

                     Symbols.Symbol var25 = ((Symbols.Symbol)rebind0.elem).suchThat((symx) -> BoxesRunTime.boxToBoolean($anonfun$adaptToNewRun$10(symx)));
                     if (var25 == null) {
                        throw null;
                     } else {
                        Symbols.Symbol orElse_this = var25;
                        if (orElse_this != orElse_this.scala$reflect$internal$Symbols$Symbol$$$outer().NoSymbol()) {
                           return orElse_this;
                        } else {
                           throw $anonfun$adaptToNewRun$11(this, sym, pre);
                        }
                     }
                  }
               }
            }
         } else {
            return sym;
         }
      }

      public Types.Type apply(final Types.Type tp) {
         while(!(tp instanceof Types.ThisType)) {
            if (tp instanceof Types.SingleType) {
               Types.SingleType var4 = (Types.SingleType)tp;
               Types.Type pre = var4.pre();
               Symbols.Symbol sym = var4.sym();
               if (sym.hasPackageFlag()) {
                  return tp;
               }

               Types.Type pre1 = this.apply(pre);

               Types.Type var124;
               try {
                  Symbols.Symbol sym1 = this.adaptToNewRun(pre1, sym);
                  if (pre1 != pre || sym1 != sym) {
                     return this.scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer().singleType(pre1, sym1);
                  }

                  var124 = tp;
               } catch (Types.MissingTypeControl var76) {
                  var124 = tp;
               }

               return var124;
            }

            if (tp instanceof Types.TypeRef) {
               Types.TypeRef var9 = (Types.TypeRef)tp;
               Types.Type pre = var9.pre();
               Symbols.Symbol sym = var9.sym();
               List args = var9.args();
               if (sym.isPackageClass()) {
                  return tp;
               }

               Types.Type pre1 = this.apply(pre);
               if (args == null) {
                  throw null;
               }

               List mapConserve_loop$3_pending = args;
               List mapConserve_loop$3_unchanged = args;
               scala.collection.immutable..colon.colon mapConserve_loop$3_mappedLast = null;
               List mapConserve_loop$3_mappedHead = null;

               while(!mapConserve_loop$3_pending.isEmpty()) {
                  Object mapConserve_loop$3_head0 = mapConserve_loop$3_pending.head();
                  Object mapConserve_loop$3_head1 = this.apply((Types.Type)mapConserve_loop$3_head0);
                  if (mapConserve_loop$3_head1 == mapConserve_loop$3_head0) {
                     mapConserve_loop$3_pending = (List)mapConserve_loop$3_pending.tail();
                     mapConserve_loop$3_unchanged = mapConserve_loop$3_unchanged;
                     mapConserve_loop$3_mappedLast = mapConserve_loop$3_mappedLast;
                     mapConserve_loop$3_mappedHead = mapConserve_loop$3_mappedHead;
                  } else {
                     List mapConserve_loop$3_xc = mapConserve_loop$3_unchanged;
                     List mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_mappedHead;

                     scala.collection.immutable..colon.colon mapConserve_loop$3_mappedLast1;
                     for(mapConserve_loop$3_mappedLast1 = mapConserve_loop$3_mappedLast; mapConserve_loop$3_xc != mapConserve_loop$3_pending; mapConserve_loop$3_xc = (List)mapConserve_loop$3_xc.tail()) {
                        scala.collection.immutable..colon.colon mapConserve_loop$3_next = new scala.collection.immutable..colon.colon(mapConserve_loop$3_xc.head(), scala.collection.immutable.Nil..MODULE$);
                        if (mapConserve_loop$3_mappedHead1 == null) {
                           mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_next;
                        }

                        if (mapConserve_loop$3_mappedLast1 != null) {
                           mapConserve_loop$3_mappedLast1.next_$eq(mapConserve_loop$3_next);
                        }

                        mapConserve_loop$3_mappedLast1 = mapConserve_loop$3_next;
                     }

                     scala.collection.immutable..colon.colon mapConserve_loop$3_next = new scala.collection.immutable..colon.colon(mapConserve_loop$3_head1, scala.collection.immutable.Nil..MODULE$);
                     if (mapConserve_loop$3_mappedHead1 == null) {
                        mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_next;
                     }

                     if (mapConserve_loop$3_mappedLast1 != null) {
                        mapConserve_loop$3_mappedLast1.next_$eq(mapConserve_loop$3_next);
                     }

                     List mapConserve_loop$3_tail0 = (List)mapConserve_loop$3_pending.tail();
                     mapConserve_loop$3_pending = mapConserve_loop$3_tail0;
                     mapConserve_loop$3_unchanged = mapConserve_loop$3_tail0;
                     mapConserve_loop$3_mappedLast = mapConserve_loop$3_next;
                     mapConserve_loop$3_mappedHead = mapConserve_loop$3_mappedHead1;
                  }
               }

               Object var121;
               if (mapConserve_loop$3_mappedHead == null) {
                  var121 = mapConserve_loop$3_unchanged;
               } else {
                  mapConserve_loop$3_mappedLast.next_$eq(mapConserve_loop$3_unchanged);
                  var121 = mapConserve_loop$3_mappedHead;
               }

               mapConserve_loop$3_mappedHead = null;
               Object var81 = null;
               Object var82 = null;
               Object var83 = null;
               Object var84 = null;
               Object var85 = null;
               Object var86 = null;
               Object var87 = null;
               Object var88 = null;
               Object var89 = null;
               Object var90 = null;
               Object var91 = null;
               List mapConserve_result = var121;
               Statics.releaseFence();
               var121 = mapConserve_result;
               mapConserve_result = null;
               List args1 = (List)var121;

               try {
                  Symbols.Symbol sym1 = this.adaptToNewRun(pre1, sym);
                  if (pre1 == pre && sym1 == sym && args1 == args) {
                     var121 = tp;
                  } else {
                     label243: {
                        Symbols.NoSymbol var16 = this.scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer().NoSymbol();
                        if (sym1 == null) {
                           if (var16 == null) {
                              break label243;
                           }
                        } else if (sym1.equals(var16)) {
                           break label243;
                        }

                        return this.scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer().copyTypeRef(tp, pre1, sym1, args1);
                     }

                     this.scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer().devWarning(() -> (new StringBuilder(40)).append("adapt to new run failed: pre=").append(pre).append(" pre1=").append(pre1).append(" sym=").append(sym).toString());
                     var121 = tp;
                  }
               } catch (Types.MissingAliasControl var77) {
                  tp = tp.dealias();
                  continue;
               } catch (Types.MissingTypeControl var78) {
                  var121 = tp;
               }

               return var121;
            }

            if (tp instanceof Types.MethodType) {
               Types.MethodType var17 = (Types.MethodType)tp;
               List params = var17.params();
               Types.Type restp = var17.resultType();
               Types.Type restp1 = this.apply(restp);
               if (restp1 == restp) {
                  return tp;
               }

               return this.scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer().copyMethodType(tp, params, restp1);
            }

            if (tp instanceof Types.NullaryMethodType) {
               Types.Type restp = ((Types.NullaryMethodType)tp).resultType();
               Types.Type restp1 = this.apply(restp);
               if (restp1 == restp) {
                  return tp;
               }

               return this.scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer().new NullaryMethodType(restp1);
            }

            if (tp instanceof Types.PolyType) {
               Types.PolyType var23 = (Types.PolyType)tp;
               List tparams = var23.typeParams();
               Types.Type restp = var23.resultType();
               Types.Type restp1 = this.apply(restp);
               if (restp1 == restp) {
                  return tp;
               }

               return this.scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer().new PolyType(tparams, restp1);
            }

            if (tp instanceof Types.ClassInfoType) {
               Types.ClassInfoType var27 = (Types.ClassInfoType)tp;
               List parents = var27.parents();
               Scopes.Scope decls = var27.decls();
               Symbols.Symbol clazz = var27.typeSymbol();
               if (clazz.isPackageClass()) {
                  return tp;
               }

               if (parents == null) {
                  throw null;
               }

               List mapConserve_loop$3_pendingx = parents;
               List mapConserve_loop$3_unchangedx = parents;
               scala.collection.immutable..colon.colon mapConserve_loop$3_mappedLastx = null;
               List mapConserve_loop$3_mappedHead = null;

               while(!mapConserve_loop$3_pendingx.isEmpty()) {
                  Object mapConserve_loop$3_head0 = mapConserve_loop$3_pendingx.head();
                  Object mapConserve_loop$3_head1 = this.apply((Types.Type)mapConserve_loop$3_head0);
                  if (mapConserve_loop$3_head1 == mapConserve_loop$3_head0) {
                     mapConserve_loop$3_pendingx = (List)mapConserve_loop$3_pendingx.tail();
                     mapConserve_loop$3_unchangedx = mapConserve_loop$3_unchangedx;
                     mapConserve_loop$3_mappedLastx = mapConserve_loop$3_mappedLastx;
                     mapConserve_loop$3_mappedHead = mapConserve_loop$3_mappedHead;
                  } else {
                     List mapConserve_loop$3_xc = mapConserve_loop$3_unchangedx;
                     List mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_mappedHead;

                     scala.collection.immutable..colon.colon mapConserve_loop$3_mappedLast1;
                     for(mapConserve_loop$3_mappedLast1 = mapConserve_loop$3_mappedLastx; mapConserve_loop$3_xc != mapConserve_loop$3_pendingx; mapConserve_loop$3_xc = (List)mapConserve_loop$3_xc.tail()) {
                        scala.collection.immutable..colon.colon mapConserve_loop$3_next = new scala.collection.immutable..colon.colon(mapConserve_loop$3_xc.head(), scala.collection.immutable.Nil..MODULE$);
                        if (mapConserve_loop$3_mappedHead1 == null) {
                           mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_next;
                        }

                        if (mapConserve_loop$3_mappedLast1 != null) {
                           mapConserve_loop$3_mappedLast1.next_$eq(mapConserve_loop$3_next);
                        }

                        mapConserve_loop$3_mappedLast1 = mapConserve_loop$3_next;
                     }

                     scala.collection.immutable..colon.colon mapConserve_loop$3_next = new scala.collection.immutable..colon.colon(mapConserve_loop$3_head1, scala.collection.immutable.Nil..MODULE$);
                     if (mapConserve_loop$3_mappedHead1 == null) {
                        mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_next;
                     }

                     if (mapConserve_loop$3_mappedLast1 != null) {
                        mapConserve_loop$3_mappedLast1.next_$eq(mapConserve_loop$3_next);
                     }

                     List mapConserve_loop$3_tail0 = (List)mapConserve_loop$3_pendingx.tail();
                     mapConserve_loop$3_pendingx = mapConserve_loop$3_tail0;
                     mapConserve_loop$3_unchangedx = mapConserve_loop$3_tail0;
                     mapConserve_loop$3_mappedLastx = mapConserve_loop$3_next;
                     mapConserve_loop$3_mappedHead = mapConserve_loop$3_mappedHead1;
                  }
               }

               Object var119;
               if (mapConserve_loop$3_mappedHead == null) {
                  var119 = mapConserve_loop$3_unchangedx;
               } else {
                  mapConserve_loop$3_mappedLastx.next_$eq(mapConserve_loop$3_unchangedx);
                  var119 = mapConserve_loop$3_mappedHead;
               }

               mapConserve_loop$3_mappedHead = null;
               Object var94 = null;
               Object var95 = null;
               Object var96 = null;
               Object var97 = null;
               Object var98 = null;
               Object var99 = null;
               Object var100 = null;
               Object var101 = null;
               Object var102 = null;
               Object var103 = null;
               Object var104 = null;
               List mapConserve_result = (List)var119;
               Statics.releaseFence();
               var119 = mapConserve_result;
               mapConserve_result = null;
               List parents1 = (List)var119;
               decls.foreach((decl) -> decl.hasAllFlags(320L) ? decl.resetFlag(4194368L) : BoxedUnit.UNIT);
               if (parents1 == parents) {
                  return tp;
               }

               return this.scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer().new ClassInfoType(parents1, decls, clazz);
            }

            if (!(tp instanceof Types.RefinedType)) {
               if (tp instanceof Types.SuperType) {
                  return tp.mapOver(this);
               }

               if (tp instanceof Types.TypeBounds) {
                  return tp.mapOver(this);
               }

               if (tp instanceof Types.TypeVar) {
                  return tp.mapOver(this);
               }

               if (tp instanceof Types.AnnotatedType) {
                  return tp.mapOver(this);
               }

               if (tp instanceof Types.ExistentialType) {
                  return tp.mapOver(this);
               }

               return tp;
            }

            Types.RefinedType var32 = (Types.RefinedType)tp;
            List parents = var32.parents();
            Scopes.Scope decls = var32.decls();
            if (parents == null) {
               throw null;
            }

            List mapConserve_loop$3_pendingxx = parents;
            List mapConserve_loop$3_unchangedxx = parents;
            scala.collection.immutable..colon.colon mapConserve_loop$3_mappedLastxx = null;
            List mapConserve_loop$3_mappedHead = null;

            while(!mapConserve_loop$3_pendingxx.isEmpty()) {
               Object mapConserve_loop$3_head0 = mapConserve_loop$3_pendingxx.head();
               Object mapConserve_loop$3_head1 = this.apply((Types.Type)mapConserve_loop$3_head0);
               if (mapConserve_loop$3_head1 == mapConserve_loop$3_head0) {
                  mapConserve_loop$3_pendingxx = (List)mapConserve_loop$3_pendingxx.tail();
                  mapConserve_loop$3_unchangedxx = mapConserve_loop$3_unchangedxx;
                  mapConserve_loop$3_mappedLastxx = mapConserve_loop$3_mappedLastxx;
                  mapConserve_loop$3_mappedHead = mapConserve_loop$3_mappedHead;
               } else {
                  List mapConserve_loop$3_xc = mapConserve_loop$3_unchangedxx;
                  List mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_mappedHead;

                  scala.collection.immutable..colon.colon mapConserve_loop$3_mappedLast1;
                  for(mapConserve_loop$3_mappedLast1 = mapConserve_loop$3_mappedLastxx; mapConserve_loop$3_xc != mapConserve_loop$3_pendingxx; mapConserve_loop$3_xc = (List)mapConserve_loop$3_xc.tail()) {
                     scala.collection.immutable..colon.colon mapConserve_loop$3_next = new scala.collection.immutable..colon.colon(mapConserve_loop$3_xc.head(), scala.collection.immutable.Nil..MODULE$);
                     if (mapConserve_loop$3_mappedHead1 == null) {
                        mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_next;
                     }

                     if (mapConserve_loop$3_mappedLast1 != null) {
                        mapConserve_loop$3_mappedLast1.next_$eq(mapConserve_loop$3_next);
                     }

                     mapConserve_loop$3_mappedLast1 = mapConserve_loop$3_next;
                  }

                  scala.collection.immutable..colon.colon mapConserve_loop$3_next = new scala.collection.immutable..colon.colon(mapConserve_loop$3_head1, scala.collection.immutable.Nil..MODULE$);
                  if (mapConserve_loop$3_mappedHead1 == null) {
                     mapConserve_loop$3_mappedHead1 = mapConserve_loop$3_next;
                  }

                  if (mapConserve_loop$3_mappedLast1 != null) {
                     mapConserve_loop$3_mappedLast1.next_$eq(mapConserve_loop$3_next);
                  }

                  List mapConserve_loop$3_tail0 = (List)mapConserve_loop$3_pendingxx.tail();
                  mapConserve_loop$3_pendingxx = mapConserve_loop$3_tail0;
                  mapConserve_loop$3_unchangedxx = mapConserve_loop$3_tail0;
                  mapConserve_loop$3_mappedLastxx = mapConserve_loop$3_next;
                  mapConserve_loop$3_mappedHead = mapConserve_loop$3_mappedHead1;
               }
            }

            Object var10000;
            if (mapConserve_loop$3_mappedHead == null) {
               var10000 = mapConserve_loop$3_unchangedxx;
            } else {
               mapConserve_loop$3_mappedLastxx.next_$eq(mapConserve_loop$3_unchangedxx);
               var10000 = mapConserve_loop$3_mappedHead;
            }

            mapConserve_loop$3_mappedHead = null;
            Object var107 = null;
            Object var108 = null;
            Object var109 = null;
            Object var110 = null;
            Object var111 = null;
            Object var112 = null;
            Object var113 = null;
            Object var114 = null;
            Object var115 = null;
            Object var116 = null;
            Object var117 = null;
            List mapConserve_result = (List)var10000;
            Statics.releaseFence();
            var10000 = mapConserve_result;
            mapConserve_result = null;
            List parents1 = (List)var10000;
            if (parents1 == parents) {
               return tp;
            }

            return this.scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer().refinedType(parents1, tp.typeSymbol().owner(), decls, tp.typeSymbol().owner().pos());
         }

         Symbols.Symbol sym = ((Types.ThisType)tp).sym();

         Types.Type var125;
         try {
            Symbols.Symbol sym1 = this.adaptToNewRun(sym.owner().thisType(), sym);
            if (sym1 != null) {
               if (sym1.equals(sym)) {
                  var125 = tp;
                  return var125;
               }
            }

            return this.scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer().ThisType().apply(sym1);
         } catch (Types.MissingTypeControl var75) {
            var125 = tp;
            return var125;
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final Symbols.Symbol $anonfun$adaptToNewRun$1(final Symbols.Symbol sourceModule1$1) {
         return sourceModule1$1.initialize().moduleClass();
      }

      // $FF: synthetic method
      public static final Symbols.Symbol $anonfun$adaptToNewRun$2(final adaptToNewRunMap$ $this, final Symbols.Symbol sym$1, final Symbols.Symbol sourceModule1$1) {
         String msg = "Cannot adapt module class; sym = %s, sourceModule = %s, sourceModule.moduleClass = %s => sourceModule1 = %s, sourceModule1.moduleClass = %s";
         $this.scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer().debuglog(() -> scala.collection.StringOps..MODULE$.format$extension(msg, scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{sym$1, sym$1.sourceModule(), sym$1.sourceModule().moduleClass(), sourceModule1$1, sourceModule1$1.moduleClass()})));
         return sym$1;
      }

      // $FF: synthetic method
      public static final Nothing $anonfun$adaptToNewRun$4(final adaptToNewRunMap$ $this, final Symbols.Symbol sym$1, final Types.Type pre$2) {
         if (sym$1.isAliasType()) {
            throw $this.scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer().missingAliasException();
         } else {
            $this.scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer().devWarning(() -> (new StringBuilder(27)).append(pre$2).append(".").append(sym$1).append(" no longer exist at phase ").append($this.scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer().phase()).toString());
            throw $this.scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer().new MissingTypeControl();
         }
      }

      private final boolean corresponds$1(final Symbols.Symbol sym1, final Symbols.Symbol sym2) {
         while(true) {
            Names.Name var10000 = sym1.name();
            Names.Name var3 = sym2.name();
            if (var10000 == null) {
               if (var3 != null) {
                  break;
               }
            } else if (!var10000.equals(var3)) {
               break;
            }

            if (sym1.isPackageClass()) {
               return true;
            }

            Symbols.Symbol var4 = sym1.owner();
            sym2 = sym2.owner();
            sym1 = var4;
         }

         return false;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$adaptToNewRun$7(final adaptToNewRunMap$ $this, final Symbols.Symbol sym$1, final Symbols.Symbol bc) {
         return !$this.corresponds$1(bc, sym$1.owner());
      }

      // $FF: synthetic method
      public static final Types.Type $anonfun$adaptToNewRun$8(final Types.Type pre$2) {
         return pre$2;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$adaptToNewRun$10(final Symbols.Symbol sym) {
         return sym.isType() || sym.isStable();
      }

      // $FF: synthetic method
      public static final Nothing $anonfun$adaptToNewRun$11(final adaptToNewRunMap$ $this, final Symbols.Symbol sym$1, final Types.Type pre$2) {
         $this.scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer().debuglog(() -> (new StringBuilder(2)).append($this.scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer().phase()).append(" ").append($this.scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer().phase().flatClasses()).append(sym$1.owner()).append(sym$1.name()).append(" ").append(sym$1.isType()).toString());
         throw $this.scala$reflect$internal$tpe$TypeMaps$adaptToNewRunMap$$$outer().new MalformedType(pre$2, sym$1.nameString());
      }

      // $FF: synthetic method
      public static final Object $anonfun$adaptToNewRun$7$adapted(final adaptToNewRunMap$ $this, final Symbols.Symbol sym$1, final Symbols.Symbol bc) {
         return BoxesRunTime.boxToBoolean($anonfun$adaptToNewRun$7($this, sym$1, bc));
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class UnrelatableCollector$ extends CollectTypeCollector {
      private int barLevel = 0;

      public int barLevel() {
         return this.barLevel;
      }

      public void barLevel_$eq(final int x$1) {
         this.barLevel = x$1;
      }

      public void apply(final Types.Type tp) {
         if (tp instanceof Types.TypeRef) {
            Symbols.Symbol ts = ((Types.TypeRef)tp).sym();
            if (ts instanceof Symbols.TypeSkolem) {
               Symbols.TypeSkolem var3 = (Symbols.TypeSkolem)ts;
               if (var3.level() > this.barLevel()) {
                  ListBuffer var10000 = this.buffer();
                  if (var10000 == null) {
                     throw null;
                  }

                  var10000.addOne(var3);
                  return;
               }
            }
         }

         tp.foldOver(this);
      }

      public UnrelatableCollector$() {
         super(scala.PartialFunction..MODULE$.empty());
      }
   }

   public class IsRelatableCollector$ extends TypeCollector {
      private int barLevel = 0;

      public int barLevel() {
         return this.barLevel;
      }

      public void barLevel_$eq(final int x$1) {
         this.barLevel = x$1;
      }

      public void apply(final Types.Type tp) {
         if (BoxesRunTime.unboxToBoolean(this.result())) {
            if (tp instanceof Types.TypeRef) {
               Symbols.Symbol ts = ((Types.TypeRef)tp).sym();
               if (ts instanceof Symbols.TypeSkolem && ((Symbols.TypeSkolem)ts).level() > this.barLevel()) {
                  this.result_$eq(false);
                  return;
               }
            }

            tp.foldOver(this);
         }
      }

      public IsRelatableCollector$() {
         super(true);
      }
   }
}

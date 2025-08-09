package org.apache.spark.internal.config;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.concurrent.TimeUnit;
import org.apache.spark.network.util.ByteUnit;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Option.;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\teg!\u0002\u001f>\u0001\u0006;\u0005\u0002\u00030\u0001\u0005+\u0007I\u0011A0\t\u0011!\u0004!\u0011#Q\u0001\n\u0001DQ!\u001b\u0001\u0005\u0002)D\u0001B\u001c\u0001A\u0002\u0013\u0005Qh\u001c\u0005\tg\u0002\u0001\r\u0011\"\u0001>i\"1!\u0010\u0001Q!\nAD\u0001b\u001f\u0001A\u0002\u0013\u0005Qh\u0018\u0005\ty\u0002\u0001\r\u0011\"\u0001>{\"1q\u0010\u0001Q!\n\u0001D!\"!\u0001\u0001\u0001\u0004%\t!PA\u0002\u0011)\tY\u0001\u0001a\u0001\n\u0003i\u0014Q\u0002\u0005\t\u0003#\u0001\u0001\u0015)\u0003\u0002\u0006!Q\u00111\u0003\u0001A\u0002\u0013\u0005Q(!\u0006\t\u0015\u0005\u0015\u0002\u00011A\u0005\u0002u\n9\u0003\u0003\u0005\u0002,\u0001\u0001\u000b\u0015BA\f\u0011)\ti\u0003\u0001a\u0001\n\u0003i\u0014Q\u0003\u0005\u000b\u0003_\u0001\u0001\u0019!C\u0001{\u0005E\u0002\u0002CA\u001b\u0001\u0001\u0006K!a\u0006\t\u0015\u0005]\u0002\u00011A\u0005\u0002u\nI\u0004\u0003\u0006\u0002d\u0001\u0001\r\u0011\"\u0001>\u0003KB\u0001\"a\u0015\u0001A\u0003&\u00111\b\u0005\u000b\u0003k\u0002\u0001\u0019!C\u0001{\u0005]\u0004BCAE\u0001\u0001\u0007I\u0011A\u001f\u0002\f\"A\u0011q\u0012\u0001!B\u0013\tI\b\u0003\u0004A\u0001\u0011\u0005\u0011\u0011\u0013\u0005\b\u0003'\u0003A\u0011AAK\u0011\u001d\tY\n\u0001C\u0001\u0003;Cq!a)\u0001\t\u0003\t)\u000bC\u0004\u00028\u0002!\t!!/\t\u0013\u0005\u0005\u0007!%A\u0005\u0002\u0005\r\u0007bBAm\u0001\u0011\u0005\u00111\u001c\u0005\b\u0003?\u0004A\u0011AAq\u0011\u001d\ty\u000f\u0001C\u0001\u0003cDq!a?\u0001\t\u0003\ti\u0010C\u0004\u0003\b\u0001!\tA!\u0003\t\u000f\t5\u0001\u0001\"\u0001\u0003\u0010!9!1\u0003\u0001\u0005\u0002\tU\u0001b\u0002B\u0016\u0001\u0011\u0005!Q\u0006\u0005\b\u0005\u007f\u0001A\u0011\u0001B!\u0011\u001d\u0011\t\u0006\u0001C\u0001\u0005'BqA!\u001a\u0001\t\u0013\u00119\u0007C\u0005\u0003j\u0001\t\t\u0011\"\u0001\u0003l!I!q\u000e\u0001\u0012\u0002\u0013\u0005\u00111\u0019\u0005\n\u0005c\u0002\u0011\u0011!C!\u0003+A\u0011Ba\u001d\u0001\u0003\u0003%\tA!\u001e\t\u0013\t]\u0004!!A\u0005\u0002\te\u0004\"\u0003B?\u0001\u0005\u0005I\u0011\tB@\u0011%\u0011I\tAA\u0001\n\u0003\u0011Y\tC\u0005\u0003\u0010\u0002\t\t\u0011\"\u0011\u0003\u0012\"I!Q\u0013\u0001\u0002\u0002\u0013\u0005#q\u0013\u0005\n\u00053\u0003\u0011\u0011!C!\u00057C\u0011B!(\u0001\u0003\u0003%\tEa(\b\u0015\t\rV(!A\t\u0002\u0005\u0013)KB\u0005={\u0005\u0005\t\u0012A!\u0003(\"1\u0011N\u000eC\u0001\u0005\u007fC\u0011B!'7\u0003\u0003%)Ea'\t\u0013\t\u0005g'!A\u0005\u0002\n\r\u0007\"\u0003Bdm\u0005\u0005I\u0011\u0011Be\u0011%\u0011yMNA\u0001\n\u0013\u0011\tNA\u0007D_:4\u0017n\u001a\"vS2$WM\u001d\u0006\u0003}}\naaY8oM&<'B\u0001!B\u0003!Ig\u000e^3s]\u0006d'B\u0001\"D\u0003\u0015\u0019\b/\u0019:l\u0015\t!U)\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\r\u0006\u0019qN]4\u0014\t\u0001Ae*\u0015\t\u0003\u00132k\u0011A\u0013\u0006\u0002\u0017\u0006)1oY1mC&\u0011QJ\u0013\u0002\u0007\u0003:L(+\u001a4\u0011\u0005%{\u0015B\u0001)K\u0005\u001d\u0001&o\u001c3vGR\u0004\"AU.\u000f\u0005MKfB\u0001+Y\u001b\u0005)&B\u0001,X\u0003\u0019a$o\\8u}\r\u0001\u0011\"A&\n\u0005iS\u0015a\u00029bG.\fw-Z\u0005\u00039v\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!A\u0017&\u0002\u0007-,\u00170F\u0001a!\t\tWM\u0004\u0002cGB\u0011AKS\u0005\u0003I*\u000ba\u0001\u0015:fI\u00164\u0017B\u00014h\u0005\u0019\u0019FO]5oO*\u0011AMS\u0001\u0005W\u0016L\b%\u0001\u0004=S:LGO\u0010\u000b\u0003W6\u0004\"\u0001\u001c\u0001\u000e\u0003uBQAX\u0002A\u0002\u0001\fQb\u00189sKB,g\u000eZ3e\u0017\u0016LX#\u00019\u0011\u0007%\u000b\b-\u0003\u0002s\u0015\n1q\n\u001d;j_:\f\u0011c\u00189sKB,g\u000eZ3e\u0017\u0016Lx\fJ3r)\t)\b\u0010\u0005\u0002Jm&\u0011qO\u0013\u0002\u0005+:LG\u000fC\u0004z\u000b\u0005\u0005\t\u0019\u00019\u0002\u0007a$\u0013'\u0001\b`aJ,\u0007/\u001a8eK\u0012\\U-\u001f\u0011\u0002#}\u0003(/\u001a9f]\u0012\u001cV\r]1sCR|'/A\u000b`aJ,\u0007/\u001a8e'\u0016\u0004\u0018M]1u_J|F%Z9\u0015\u0005Ut\bbB=\t\u0003\u0003\u0005\r\u0001Y\u0001\u0013?B\u0014X\r]3oIN+\u0007/\u0019:bi>\u0014\b%A\u0004`aV\u0014G.[2\u0016\u0005\u0005\u0015\u0001cA%\u0002\b%\u0019\u0011\u0011\u0002&\u0003\u000f\t{w\u000e\\3b]\u0006Yq\f];cY&\u001cw\fJ3r)\r)\u0018q\u0002\u0005\ts.\t\t\u00111\u0001\u0002\u0006\u0005Aq\f];cY&\u001c\u0007%\u0001\u0003`I>\u001cWCAA\f!\u0011\tI\"a\t\u000e\u0005\u0005m!\u0002BA\u000f\u0003?\tA\u0001\\1oO*\u0011\u0011\u0011E\u0001\u0005U\u00064\u0018-C\u0002g\u00037\t\u0001b\u00183pG~#S-\u001d\u000b\u0004k\u0006%\u0002\u0002C=\u000f\u0003\u0003\u0005\r!a\u0006\u0002\u000b}#wn\u0019\u0011\u0002\u0011}3XM]:j_:\fAb\u0018<feNLwN\\0%KF$2!^A\u001a\u0011!I\u0018#!AA\u0002\u0005]\u0011!C0wKJ\u001c\u0018n\u001c8!\u0003%yvN\\\"sK\u0006$X-\u0006\u0002\u0002<A!\u0011*]A\u001f!\u0019I\u0015qHA\"k&\u0019\u0011\u0011\t&\u0003\u0013\u0019+hn\u0019;j_:\f\u0004\u0007BA#\u0003\u001f\u0002R\u0001\\A$\u0003\u0017J1!!\u0013>\u0005-\u0019uN\u001c4jO\u0016sGO]=\u0011\t\u00055\u0013q\n\u0007\u0001\t-\t\t&FA\u0001\u0002\u0003\u0015\t!!\u0016\u0003\u0007}#\u0013'\u0001\u0006`_:\u001c%/Z1uK\u0002\nB!a\u0016\u0002^A\u0019\u0011*!\u0017\n\u0007\u0005m#JA\u0004O_RD\u0017N\\4\u0011\u0007%\u000by&C\u0002\u0002b)\u00131!\u00118z\u00035yvN\\\"sK\u0006$Xm\u0018\u0013fcR\u0019Q/a\u001a\t\u0011e$\u0012\u0011!a\u0001\u0003S\u0002B!S9\u0002lA1\u0011*a\u0010\u0002nU\u0004D!a\u001c\u0002tA)A.a\u0012\u0002rA!\u0011QJA:\t1\t\t&a\u001a\u0002\u0002\u0003\u0005)\u0011AA+\u00035y\u0016\r\u001c;fe:\fG/\u001b<fgV\u0011\u0011\u0011\u0010\t\u0006\u0003w\n)\tY\u0007\u0003\u0003{RA!a \u0002\u0002\u0006I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0004\u0003\u0007S\u0015AC2pY2,7\r^5p]&!\u0011qQA?\u0005\u0011a\u0015n\u001d;\u0002#}\u000bG\u000e^3s]\u0006$\u0018N^3t?\u0012*\u0017\u000fF\u0002v\u0003\u001bC\u0001\"_\f\u0002\u0002\u0003\u0007\u0011\u0011P\u0001\u000f?\u0006dG/\u001a:oCRLg/Z:!)\u0005Y\u0017a\u00013pGR\u00191.a&\t\r\u0005e%\u00041\u0001a\u0003\u0005\u0019\u0018a\u0002<feNLwN\u001c\u000b\u0004W\u0006}\u0005BBAQ7\u0001\u0007\u0001-A\u0001w\u0003!ygn\u0011:fCR,GcA6\u0002(\"9\u0011\u0011\u0016\u000fA\u0002\u0005-\u0016\u0001C2bY2\u0014\u0017mY6\u0011\r%\u000by$!,va\u0011\ty+a-\u0011\u000b1\f9%!-\u0011\t\u00055\u00131\u0017\u0003\r\u0003k\u000b9+!A\u0001\u0002\u000b\u0005\u0011Q\u000b\u0002\u0004?\u0012\u0012\u0014!D<ji\"\u0004&/\u001a9f]\u0012,G\rF\u0003l\u0003w\u000bi\fC\u0003_;\u0001\u0007\u0001\r\u0003\u0005\u0002@v\u0001\n\u00111\u0001a\u0003%\u0019X\r]1sCR|'/A\fxSRD\u0007K]3qK:$W\r\u001a\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0011Q\u0019\u0016\u0004A\u0006\u001d7FAAe!\u0011\tY-!6\u000e\u0005\u00055'\u0002BAh\u0003#\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005M'*\u0001\u0006b]:|G/\u0019;j_:LA!a6\u0002N\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001f]LG\u000f[!mi\u0016\u0014h.\u0019;jm\u0016$2a[Ao\u0011\u0015qv\u00041\u0001a\u0003\u001dIg\u000e^\"p]\u001a,\"!a9\u0011\u000b1\f)/!;\n\u0007\u0005\u001dXH\u0001\nUsB,GmQ8oM&<')^5mI\u0016\u0014\bcA%\u0002l&\u0019\u0011Q\u001e&\u0003\u0007%sG/\u0001\u0005m_:<7i\u001c8g+\t\t\u0019\u0010E\u0003m\u0003K\f)\u0010E\u0002J\u0003oL1!!?K\u0005\u0011auN\\4\u0002\u0015\u0011|WO\u00197f\u0007>tg-\u0006\u0002\u0002\u0000B)A.!:\u0003\u0002A\u0019\u0011Ja\u0001\n\u0007\t\u0015!J\u0001\u0004E_V\u0014G.Z\u0001\fE>|G.Z1o\u0007>tg-\u0006\u0002\u0003\fA)A.!:\u0002\u0006\u0005Q1\u000f\u001e:j]\u001e\u001cuN\u001c4\u0016\u0005\tE\u0001\u0003\u00027\u0002f\u0002\f\u0001\u0002^5nK\u000e{gN\u001a\u000b\u0005\u0003g\u00149\u0002C\u0004\u0003\u001a\u0015\u0002\rAa\u0007\u0002\tUt\u0017\u000e\u001e\t\u0005\u0005;\u00119#\u0004\u0002\u0003 )!!\u0011\u0005B\u0012\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0005\u0005K\ty\"\u0001\u0003vi&d\u0017\u0002\u0002B\u0015\u0005?\u0011\u0001\u0002V5nKVs\u0017\u000e^\u0001\nEf$Xm]\"p]\u001a$B!a=\u00030!9!\u0011\u0004\u0014A\u0002\tE\u0002\u0003\u0002B\u001a\u0005wi!A!\u000e\u000b\t\t\u0015\"q\u0007\u0006\u0004\u0005s\t\u0015a\u00028fi^|'o[\u0005\u0005\u0005{\u0011)D\u0001\u0005CsR,WK\\5u\u000311\u0017\r\u001c7cC\u000e\\7i\u001c8g+\u0011\u0011\u0019E!\u0013\u0015\t\t\u0015#Q\n\t\u0006Y\u0006\u001d#q\t\t\u0005\u0003\u001b\u0012I\u0005B\u0004\u0003L\u001d\u0012\r!!\u0016\u0003\u0003QCqAa\u0014(\u0001\u0004\u0011)%\u0001\u0005gC2d'-Y2l\u0003%\u0011XmZ3y\u0007>tg-\u0006\u0002\u0003VA)A.!:\u0003XA!!\u0011\fB1\u001b\t\u0011YF\u0003\u0003\u0003^\t}\u0013\u0001C7bi\u000eD\u0017N\\4\u000b\u0007\t\u0015\"*\u0003\u0003\u0003d\tm#!\u0002*fO\u0016D\u0018AE2iK\u000e\\\u0007K]3qK:$7i\u001c8gS\u001e,\u0012!^\u0001\u0005G>\u0004\u0018\u0010F\u0002l\u0005[BqA\u0018\u0016\u0011\u0002\u0003\u0007\u0001-\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\tI/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005u#1\u0010\u0005\ts:\n\t\u00111\u0001\u0002j\u0006y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0003\u0002B1!1\u0011BC\u0003;j!!!!\n\t\t\u001d\u0015\u0011\u0011\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\u0006\t5\u0005\u0002C=1\u0003\u0003\u0005\r!!\u0018\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003/\u0011\u0019\n\u0003\u0005zc\u0005\u0005\t\u0019AAu\u0003!A\u0017m\u001d5D_\u0012,GCAAu\u0003!!xn\u0015;sS:<GCAA\f\u0003\u0019)\u0017/^1mgR!\u0011Q\u0001BQ\u0011!IH'!AA\u0002\u0005u\u0013!D\"p]\u001aLwMQ;jY\u0012,'\u000f\u0005\u0002mmM)aG!+\u00036B1!1\u0016BYA.l!A!,\u000b\u0007\t=&*A\u0004sk:$\u0018.\\3\n\t\tM&Q\u0016\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003\u0002B\\\u0005{k!A!/\u000b\t\tm\u0016qD\u0001\u0003S>L1\u0001\u0018B])\t\u0011)+A\u0003baBd\u0017\u0010F\u0002l\u0005\u000bDQAX\u001dA\u0002\u0001\fq!\u001e8baBd\u0017\u0010F\u0002q\u0005\u0017D\u0001B!4;\u0003\u0003\u0005\ra[\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001Bj!\u0011\tIB!6\n\t\t]\u00171\u0004\u0002\u0007\u001f\nTWm\u0019;"
)
public class ConfigBuilder implements Product, Serializable {
   private final String key;
   private Option _prependedKey;
   private String _prependSeparator;
   private boolean _public;
   private String _doc;
   private String _version;
   private Option _onCreate;
   private List _alternatives;

   public static Option unapply(final ConfigBuilder x$0) {
      return ConfigBuilder$.MODULE$.unapply(x$0);
   }

   public static ConfigBuilder apply(final String key) {
      return ConfigBuilder$.MODULE$.apply(key);
   }

   public static Function1 andThen(final Function1 g) {
      return ConfigBuilder$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ConfigBuilder$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String key() {
      return this.key;
   }

   public Option _prependedKey() {
      return this._prependedKey;
   }

   public void _prependedKey_$eq(final Option x$1) {
      this._prependedKey = x$1;
   }

   public String _prependSeparator() {
      return this._prependSeparator;
   }

   public void _prependSeparator_$eq(final String x$1) {
      this._prependSeparator = x$1;
   }

   public boolean _public() {
      return this._public;
   }

   public void _public_$eq(final boolean x$1) {
      this._public = x$1;
   }

   public String _doc() {
      return this._doc;
   }

   public void _doc_$eq(final String x$1) {
      this._doc = x$1;
   }

   public String _version() {
      return this._version;
   }

   public void _version_$eq(final String x$1) {
      this._version = x$1;
   }

   public Option _onCreate() {
      return this._onCreate;
   }

   public void _onCreate_$eq(final Option x$1) {
      this._onCreate = x$1;
   }

   public List _alternatives() {
      return this._alternatives;
   }

   public void _alternatives_$eq(final List x$1) {
      this._alternatives = x$1;
   }

   public ConfigBuilder internal() {
      this._public_$eq(false);
      return this;
   }

   public ConfigBuilder doc(final String s) {
      this._doc_$eq(s);
      return this;
   }

   public ConfigBuilder version(final String v) {
      this._version_$eq(v);
      return this;
   }

   public ConfigBuilder onCreate(final Function1 callback) {
      this._onCreate_$eq(.MODULE$.apply(callback));
      return this;
   }

   public ConfigBuilder withPrepended(final String key, final String separator) {
      this._prependedKey_$eq(.MODULE$.apply(key));
      this._prependSeparator_$eq(separator);
      return this;
   }

   public String withPrepended$default$2() {
      return " ";
   }

   public ConfigBuilder withAlternative(final String key) {
      this._alternatives_$eq((List)this._alternatives().$colon$plus(key));
      return this;
   }

   public TypedConfigBuilder intConf() {
      this.checkPrependConfig();
      return new TypedConfigBuilder(this, (x$8) -> BoxesRunTime.boxToInteger($anonfun$intConf$1(this, x$8)));
   }

   public TypedConfigBuilder longConf() {
      this.checkPrependConfig();
      return new TypedConfigBuilder(this, (x$10) -> BoxesRunTime.boxToLong($anonfun$longConf$1(this, x$10)));
   }

   public TypedConfigBuilder doubleConf() {
      this.checkPrependConfig();
      return new TypedConfigBuilder(this, (x$12) -> BoxesRunTime.boxToDouble($anonfun$doubleConf$1(this, x$12)));
   }

   public TypedConfigBuilder booleanConf() {
      this.checkPrependConfig();
      return new TypedConfigBuilder(this, (x$14) -> BoxesRunTime.boxToBoolean($anonfun$booleanConf$1(this, x$14)));
   }

   public TypedConfigBuilder stringConf() {
      return new TypedConfigBuilder(this, (v) -> v);
   }

   public TypedConfigBuilder timeConf(final TimeUnit unit) {
      this.checkPrependConfig();
      return new TypedConfigBuilder(this, (x$15) -> BoxesRunTime.boxToLong($anonfun$timeConf$1(unit, x$15)), (x$16) -> $anonfun$timeConf$2(unit, BoxesRunTime.unboxToLong(x$16)));
   }

   public TypedConfigBuilder bytesConf(final ByteUnit unit) {
      this.checkPrependConfig();
      return new TypedConfigBuilder(this, (x$17) -> BoxesRunTime.boxToLong($anonfun$bytesConf$1(unit, x$17)), (x$18) -> $anonfun$bytesConf$2(unit, BoxesRunTime.unboxToLong(x$18)));
   }

   public ConfigEntry fallbackConf(final ConfigEntry fallback) {
      FallbackConfigEntry entry = new FallbackConfigEntry(this.key(), this._prependedKey(), this._prependSeparator(), this._alternatives(), this._doc(), this._public(), this._version(), fallback);
      this._onCreate().foreach((x$19) -> {
         $anonfun$fallbackConf$1(entry, x$19);
         return BoxedUnit.UNIT;
      });
      return entry;
   }

   public TypedConfigBuilder regexConf() {
      this.checkPrependConfig();
      return new TypedConfigBuilder(this, (x$20) -> ConfigHelpers$.MODULE$.regexFromString(x$20, this.key()), (x$21) -> x$21.toString());
   }

   private void checkPrependConfig() {
      if (this._prependedKey().isDefined()) {
         throw new IllegalArgumentException(this.key() + " type must be string if prepend used");
      }
   }

   public ConfigBuilder copy(final String key) {
      return new ConfigBuilder(key);
   }

   public String copy$default$1() {
      return this.key();
   }

   public String productPrefix() {
      return "ConfigBuilder";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.key();
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
      return x$1 instanceof ConfigBuilder;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "key";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
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
         label47: {
            if (x$1 instanceof ConfigBuilder) {
               label40: {
                  ConfigBuilder var4 = (ConfigBuilder)x$1;
                  String var10000 = this.key();
                  String var5 = var4.key();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label40;
                  }

                  if (var4.canEqual(this)) {
                     break label47;
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
   public static final int $anonfun$intConf$2(final String x$9) {
      return scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$9));
   }

   // $FF: synthetic method
   public static final int $anonfun$intConf$1(final ConfigBuilder $this, final String x$8) {
      return BoxesRunTime.unboxToInt(ConfigHelpers$.MODULE$.toNumber(x$8, (x$9) -> BoxesRunTime.boxToInteger($anonfun$intConf$2(x$9)), $this.key(), "int"));
   }

   // $FF: synthetic method
   public static final long $anonfun$longConf$2(final String x$11) {
      return scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(x$11));
   }

   // $FF: synthetic method
   public static final long $anonfun$longConf$1(final ConfigBuilder $this, final String x$10) {
      return BoxesRunTime.unboxToLong(ConfigHelpers$.MODULE$.toNumber(x$10, (x$11) -> BoxesRunTime.boxToLong($anonfun$longConf$2(x$11)), $this.key(), "long"));
   }

   // $FF: synthetic method
   public static final double $anonfun$doubleConf$2(final String x$13) {
      return scala.collection.StringOps..MODULE$.toDouble$extension(scala.Predef..MODULE$.augmentString(x$13));
   }

   // $FF: synthetic method
   public static final double $anonfun$doubleConf$1(final ConfigBuilder $this, final String x$12) {
      return BoxesRunTime.unboxToDouble(ConfigHelpers$.MODULE$.toNumber(x$12, (x$13) -> BoxesRunTime.boxToDouble($anonfun$doubleConf$2(x$13)), $this.key(), "double"));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$booleanConf$1(final ConfigBuilder $this, final String x$14) {
      return ConfigHelpers$.MODULE$.toBoolean(x$14, $this.key());
   }

   // $FF: synthetic method
   public static final long $anonfun$timeConf$1(final TimeUnit unit$1, final String x$15) {
      return ConfigHelpers$.MODULE$.timeFromString(x$15, unit$1);
   }

   // $FF: synthetic method
   public static final String $anonfun$timeConf$2(final TimeUnit unit$1, final long x$16) {
      return ConfigHelpers$.MODULE$.timeToString(x$16, unit$1);
   }

   // $FF: synthetic method
   public static final long $anonfun$bytesConf$1(final ByteUnit unit$2, final String x$17) {
      return ConfigHelpers$.MODULE$.byteFromString(x$17, unit$2);
   }

   // $FF: synthetic method
   public static final String $anonfun$bytesConf$2(final ByteUnit unit$2, final long x$18) {
      return ConfigHelpers$.MODULE$.byteToString(x$18, unit$2);
   }

   // $FF: synthetic method
   public static final void $anonfun$fallbackConf$1(final FallbackConfigEntry entry$5, final Function1 x$19) {
      x$19.apply(entry$5);
   }

   public ConfigBuilder(final String key) {
      this.key = key;
      Product.$init$(this);
      this._prependedKey = scala.None..MODULE$;
      this._prependSeparator = "";
      this._public = true;
      this._doc = "";
      this._version = "";
      this._onCreate = scala.None..MODULE$;
      this._alternatives = scala.package..MODULE$.List().empty();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

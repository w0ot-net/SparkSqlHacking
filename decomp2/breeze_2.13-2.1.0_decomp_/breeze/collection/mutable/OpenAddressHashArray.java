package breeze.collection.mutable;

import breeze.storage.ConfigurableDefault;
import breeze.storage.ConfigurableDefault$;
import breeze.storage.Storage;
import breeze.storage.Zero;
import breeze.util.ArrayUtil$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t]h\u0001B\u001d;\u0005\u0005C1\"!\u0002\u0001\u0005\u0003\u0007I\u0011\u0001\u001e\u0002\b!Y\u0011Q\u0003\u0001\u0003\u0002\u0004%\tAOA\f\u0011)\t\u0019\u0003\u0001B\u0001B\u0003&\u0011\u0011\u0002\u0005\f\u0003K\u0001!\u00111A\u0005\u0002i\n9\u0003C\u0006\u0002,\u0001\u0011\t\u0019!C\u0001u\u00055\u0002BCA\u0019\u0001\t\u0005\t\u0015)\u0003\u0002*!Y\u00111\u0007\u0001\u0003\u0002\u0004%\tAOA\u001b\u0011-\t9\u0004\u0001BA\u0002\u0013\u0005!(!\u000f\t\u0015\u0005u\u0002A!A!B\u0013\ty\u0001\u0003\u0006\u0002@\u0001\u0011)\u0019!C\u0001\u0003kA!\"!\u0011\u0001\u0005\u0003\u0005\u000b\u0011BA\b\u0011)\t\u0019\u0005\u0001BC\u0002\u0013\u0005\u0011Q\t\u0005\u000b\u0003\u001b\u0002!\u0011!Q\u0001\n\u0005\u001d\u0003BCA(\u0001\t\u0015\r\u0011b\u0005\u0002R!Q\u0011q\f\u0001\u0003\u0002\u0003\u0006I!a\u0015\t\u0015\u0005\u0005\u0004A!b\u0001\n\u0007\t\u0019\u0007\u0003\u0006\u0002l\u0001\u0011\t\u0011)A\u0005\u0003KB\u0001\"!\u001c\u0001\t\u0003Q\u0014q\u000e\u0005\b\u0003[\u0002A\u0011AAB\u0011\u001d\ti\u0007\u0001C\u0001\u0003'Cq!!\u001c\u0001\t\u0003\ty\nC\u0004\u0002*\u0002!\t!a\n\t\u000f\u0005-\u0006\u0001\"\u0001\u0002\b!9\u0011Q\u0016\u0001\u0005\u0002\u0005=\u0006bBAY\u0001\u0011\u0005\u00111\u0017\u0005\b\u0003\u007f\u0003A\u0011AAa\u0011\u001d\t9\r\u0001C\u0001\u0003\u0013Dq!!4\u0001\t\u0003\ty\rC\u0004\u0002T\u0002!\t!!\u000e\t\u000f\u0005U\u0007\u0001\"\u0001\u0002X\"9\u0011\u0011\u001d\u0001\u0005\u0002\u0005\r\bbBAt\u0001\u0011\u0005\u0011\u0011\u001e\u0005\b\u0003W\u0004AQAAw\u0011\u001d\t\t\u0010\u0001C\u0003\u0003gDq!a?\u0001\t\u0003\ty\rC\u0004\u0002~\u0002!\t!a-\t\u000f\u0005}\b\u0001\"\u0001\u0003\u0002!9!1\u0002\u0001\u0005\n\t5\u0001b\u0002B\t\u0001\u0011%!1\u0003\u0005\b\u0005/\u0001AQ\u0003B\r\u0011\u001d\u0011Y\u0002\u0001C!\u0003kAqA!\b\u0001\t\u0003\u0012y\u0002C\u0004\u00032\u0001!\tAa\r\t\u000f\tU\u0002\u0001\"\u0001\u00038!9!Q\b\u0001\u0005\u0002\te\u0001b\u0002B \u0001\u0011\u0005#\u0011\t\u0005\b\u0005\u0007\u0002A\u0011\tB#\u000f\u001d\u00119F\u000fE\u0001\u000532a!\u000f\u001e\t\u0002\tm\u0003bBA7c\u0011\u0005!1\u000e\u0005\b\u0003W\fD\u0011\u0001B7\u0011\u001d\u0011\u0019+\rC\u0005\u0005KCqA!+2\t\u0013\u0011Y\u000bC\u0004\u00030F\"IA!-\t\u0015\tU\u0016'%A\u0005\u0002i\u00129\fC\u0005\u0003hF\n\t\u0011\"\u0003\u0003j\n!r\n]3o\u0003\u0012$'/Z:t\u0011\u0006\u001c\b.\u0011:sCfT!a\u000f\u001f\u0002\u000f5,H/\u00192mK*\u0011QHP\u0001\u000bG>dG.Z2uS>t'\"A \u0002\r\t\u0014X-\u001a>f\u0007\u0001)\"AQ)\u0014\u000b\u0001\u0019\u0015j^>\u0011\u0005\u0011;U\"A#\u000b\u0003\u0019\u000bQa]2bY\u0006L!\u0001S#\u0003\r\u0005s\u0017PU3g!\rQUjT\u0007\u0002\u0017*\u0011AJP\u0001\bgR|'/Y4f\u0013\tq5JA\u0004Ti>\u0014\u0018mZ3\u0011\u0005A\u000bF\u0002\u0001\u0003\n%\u0002\u0001\u000b\u0011!AC\u0002M\u0013\u0011AV\t\u0003)^\u0003\"\u0001R+\n\u0005Y+%a\u0002(pi\"Lgn\u001a\t\u0003\tbK!!W#\u0003\u0007\u0005s\u0017\u0010\u000b\u0004R7zCWN\u001d\t\u0003\trK!!X#\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006G}\u0003'-\u0019\b\u0003\t\u0002L!!Y#\u0002\u0007%sG/\r\u0003%G\u001e4eB\u00013h\u001b\u0005)'B\u00014A\u0003\u0019a$o\\8u}%\ta)M\u0003$S*d7N\u0004\u0002EU&\u00111.R\u0001\u0006\r2|\u0017\r^\u0019\u0005I\r<g)M\u0003$]>\f\bO\u0004\u0002E_&\u0011\u0001/R\u0001\u0005\u0019>tw-\r\u0003%G\u001e4\u0015'B\u0012tiZ,hB\u0001#u\u0013\t)X)\u0001\u0004E_V\u0014G.Z\u0019\u0005I\r<g\tE\u0002ys>k\u0011AO\u0005\u0003uj\u0012qb\u00159beN,\u0017I\u001d:bs2K7.\u001a\t\u0003y~t!aY?\n\u0005y,\u0015a\u00029bG.\fw-Z\u0005\u0005\u0003\u0003\t\u0019A\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002\u007f\u000b\u00061q,\u001b8eKb,\"!!\u0003\u0011\u000b\u0011\u000bY!a\u0004\n\u0007\u00055QIA\u0003BeJ\f\u0017\u0010E\u0002E\u0003#I1!a\u0005F\u0005\rIe\u000e^\u0001\u000b?&tG-\u001a=`I\u0015\fH\u0003BA\r\u0003?\u00012\u0001RA\u000e\u0013\r\ti\"\u0012\u0002\u0005+:LG\u000fC\u0005\u0002\"\t\t\t\u00111\u0001\u0002\n\u0005\u0019\u0001\u0010J\u0019\u0002\u000f}Kg\u000eZ3yA\u0005)q\fZ1uCV\u0011\u0011\u0011\u0006\t\u0005\t\u0006-q*A\u0005`I\u0006$\u0018m\u0018\u0013fcR!\u0011\u0011DA\u0018\u0011%\t\t#BA\u0001\u0002\u0004\tI#\u0001\u0004`I\u0006$\u0018\rI\u0001\u0005Y>\fG-\u0006\u0002\u0002\u0010\u0005AAn\\1e?\u0012*\u0017\u000f\u0006\u0003\u0002\u001a\u0005m\u0002\"CA\u0011\u0011\u0005\u0005\t\u0019AA\b\u0003\u0015aw.\u00193!\u0003\u0011\u0019\u0018N_3\u0002\u000bML'0\u001a\u0011\u0002\u000f\u0011,g-Y;miV\u0011\u0011q\t\t\u0005\u0015\u0006%s*C\u0002\u0002L-\u00131cQ8oM&<WO]1cY\u0016$UMZ1vYR\f\u0001\u0002Z3gCVdG\u000fI\u0001\b[\u0006tW\t\\3n+\t\t\u0019\u0006E\u0003\u0002V\u0005ms*\u0004\u0002\u0002X)\u0019\u0011\u0011L#\u0002\u000fI,g\r\\3di&!\u0011QLA,\u0005!\u0019E.Y:t)\u0006<\u0017\u0001C7b]\u0016cW-\u001c\u0011\u0002\ti,'o\\\u000b\u0003\u0003K\u0002BASA4\u001f&\u0019\u0011\u0011N&\u0003\ti+'o\\\u0001\u0006u\u0016\u0014x\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0019\u0005E\u0014\u0011PA>\u0003{\ny(!!\u0015\r\u0005M\u0014QOA<!\rA\ba\u0014\u0005\b\u0003\u001f\u0012\u00029AA*\u0011\u001d\t\tG\u0005a\u0002\u0003KBq!!\u0002\u0013\u0001\u0004\tI\u0001C\u0004\u0002&I\u0001\r!!\u000b\t\u000f\u0005M\"\u00031\u0001\u0002\u0010!9\u0011q\b\nA\u0002\u0005=\u0001\"CA\"%A\u0005\t\u0019AA$)!\t))a#\u0002\u000e\u0006=ECBA:\u0003\u000f\u000bI\tC\u0004\u0002PM\u0001\u001d!a\u0015\t\u000f\u0005\u00054\u0003q\u0001\u0002f!9\u0011qH\nA\u0002\u0005=\u0001bBA\"'\u0001\u0007\u0011q\t\u0005\b\u0003#\u001b\u0002\u0019AA\b\u0003-Ig.\u001b;jC2\u001c\u0016N_3\u0015\r\u0005U\u00151TAO)\u0019\t\u0019(a&\u0002\u001a\"9\u0011q\n\u000bA\u0004\u0005M\u0003bBA1)\u0001\u000f\u0011Q\r\u0005\b\u0003\u007f!\u0002\u0019AA\b\u0011\u001d\t\u0019\u0005\u0006a\u0001\u0003\u000f\"B!!)\u0002(R1\u00111OAR\u0003KCq!a\u0014\u0016\u0001\b\t\u0019\u0006C\u0004\u0002bU\u0001\u001d!!\u001a\t\u000f\u0005}R\u00031\u0001\u0002\u0010\u0005!A-\u0019;b\u0003\u0015Ig\u000eZ3y\u00031!WMZ1vYR4\u0016\r\\;f+\u0005y\u0015A\u0004<bYV,7/\u0013;fe\u0006$xN]\u000b\u0003\u0003k\u0003R!a.\u0002<>k!!!/\u000b\u0005u*\u0015\u0002BA_\u0003s\u0013\u0001\"\u0013;fe\u0006$xN]\u0001\bm\u0006dW/Z!u)\ry\u00151\u0019\u0005\b\u0003\u000bT\u0002\u0019AA\b\u0003\u0005I\u0017aB5oI\u0016D\u0018\t\u001e\u000b\u0005\u0003\u001f\tY\rC\u0004\u0002Fn\u0001\r!a\u0004\u0002\u0019-,\u0017p]%uKJ\fGo\u001c:\u0016\u0005\u0005E\u0007CBA\\\u0003w\u000by!\u0001\u0006bGRLg/Z*ju\u0016\f\u0001bY8oi\u0006Lgn\u001d\u000b\u0005\u00033\fy\u000eE\u0002E\u00037L1!!8F\u0005\u001d\u0011un\u001c7fC:Dq!!2\u001f\u0001\u0004\ty!\u0001\u0005jg\u0006\u001bG/\u001b<f)\u0011\tI.!:\t\u000f\u0005\u0015w\u00041\u0001\u0002\u0010\u0005I\u0012\r\u001c7WSNLG/\u00192mK&sG-[2fg\u0006\u001bG/\u001b<f+\t\tI.A\u0003baBd\u0017\u0010F\u0002P\u0003_Dq!!2\"\u0001\u0004\ty!\u0001\u0004va\u0012\fG/\u001a\u000b\u0007\u00033\t)0a>\t\u000f\u0005\u0015'\u00051\u0001\u0002\u0010!1\u0011\u0011 \u0012A\u0002=\u000b\u0011A^\u0001\u0013C\u000e$\u0018N^3LKf\u001c\u0018\n^3sCR|'/\u0001\u000bbGRLg/\u001a,bYV,7/\u0013;fe\u0006$xN]\u0001\u000fC\u000e$\u0018N^3Ji\u0016\u0014\u0018\r^8s+\t\u0011\u0019\u0001\u0005\u0004\u00028\u0006m&Q\u0001\t\u0007\t\n\u001d\u0011qB(\n\u0007\t%QI\u0001\u0004UkBdWMM\u0001\u0007Y>\u001c\u0017\r^3\u0015\t\u0005=!q\u0002\u0005\b\u0003\u000b4\u0003\u0019AA\b\u0003-A\u0017m\u001d5D_\u0012,gi\u001c:\u0015\t\u0005=!Q\u0003\u0005\b\u0003\u000b<\u0003\u0019AA\b\u0003\u0019\u0011X\r[1tQR\u0011\u0011\u0011D\u0001\rSR,'/\u00192mKNK'0Z\u0001\ti>\u001cFO]5oOR\u0011!\u0011\u0005\t\u0005\u0005G\u0011YC\u0004\u0003\u0003&\t\u001d\u0002C\u00013F\u0013\r\u0011I#R\u0001\u0007!J,G-\u001a4\n\t\t5\"q\u0006\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\t%R)\u0001\u0003d_BLXCAA:\u0003\u0019\u0019w\u000e]=U_R!\u0011\u0011\u0004B\u001d\u0011\u001d\u0011Y\u0004\fa\u0001\u0003g\nQa\u001c;iKJ\fQa\u00197fCJ\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003\u001f\ta!Z9vC2\u001cH\u0003BAm\u0005\u000fBaA!\u00130\u0001\u00049\u0016\u0001\u0002;iCRDs\u0001\u0001B'\u0005'\u0012)\u0006E\u0002E\u0005\u001fJ1A!\u0015F\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XMH\u0001\u0002\u0003Qy\u0005/\u001a8BI\u0012\u0014Xm]:ICND\u0017I\u001d:bsB\u0011\u00010M\n\u0005c\r\u0013i\u0006\u0005\u0003\u0003`\t%TB\u0001B1\u0015\u0011\u0011\u0019G!\u001a\u0002\u0005%|'B\u0001B4\u0003\u0011Q\u0017M^1\n\t\u0005\u0005!\u0011\r\u000b\u0003\u00053*BAa\u001c\u0003xQ!!\u0011\u000fBM)\u0019\u0011\u0019H!$\u0003\u0014B!\u0001\u0010\u0001B;!\r\u0001&q\u000f\u0003\u000b\u0005s\u001a\u0004\u0015!A\u0001\u0006\u0004\u0019&!\u0001+)\u0017\t]4L! \u0003\u0002\n\u0015%\u0011R\u0019\u0007G}\u0003'qP12\t\u0011\u001awMR\u0019\u0007G%T'1Q62\t\u0011\u001awMR\u0019\u0007G9|'q\u001192\t\u0011\u001awMR\u0019\u0007GM$(1R;2\t\u0011\u001awM\u0012\u0005\n\u0005\u001f\u001b\u0014\u0011!a\u0002\u0005#\u000b!\"\u001a<jI\u0016t7-\u001a\u00132!\u0019\t)&a\u0017\u0003v!I!QS\u001a\u0002\u0002\u0003\u000f!qS\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004#\u0002&\u0002h\tU\u0004b\u0002BNg\u0001\u0007!QT\u0001\u0007m\u0006dW/Z:\u0011\u000b\u0011\u0013yJ!\u001e\n\u0007\t\u0005VI\u0001\u0006=e\u0016\u0004X-\u0019;fIz\nQbY1mGVd\u0017\r^3TSj,G\u0003BA\b\u0005OCq!a\u00105\u0001\u0004\ty!\u0001\boKb$\bk\\<fe>3Gk^8\u0015\t\u0005=!Q\u0016\u0005\b\u0003\u007f)\u0004\u0019AA\b\u0003=)W\u000e\u001d;z\u0013:$W\r_!se\u0006LH\u0003BA\u0005\u0005gCq!a\u00107\u0001\u0004\ty!A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%N\u000b\u0005\u0005s\u0013\t-\u0006\u0002\u0003<*\"!Q\u0018Bk!\u0015Q\u0015\u0011\nB`!\r\u0001&\u0011\u0019\u0003\n%^\u0002\u000b\u0011!AC\u0002MC3B!1\\\u0005\u000b\u0014IM!4\u0003RF21e\u00181\u0003H\u0006\fD\u0001J2h\rF21%\u001b6\u0003L.\fD\u0001J2h\rF21E\\8\u0003PB\fD\u0001J2h\rF21e\u001d;\u0003TV\fD\u0001J2h\r.\u0012!q\u001b\t\u0005\u00053\u0014\u0019/\u0004\u0002\u0003\\*!!Q\u001cBp\u0003%)hn\u00195fG.,GMC\u0002\u0003b\u0016\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\u0011)Oa7\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003lB!!Q\u001eBz\u001b\t\u0011yO\u0003\u0003\u0003r\n\u0015\u0014\u0001\u00027b]\u001eLAA!>\u0003p\n1qJ\u00196fGR\u0004"
)
public class OpenAddressHashArray implements Storage, SparseArrayLike, Serializable {
   private static final long serialVersionUID = 1L;
   private int[] _index;
   public Object _data;
   private int load;
   private final int size;
   public final ConfigurableDefault default;
   private final ClassTag manElem;
   public final Zero zero;

   public int length() {
      return SparseArrayLike.length$(this);
   }

   public void foreach(final Function1 f) {
      SparseArrayLike.foreach$(this, f);
   }

   public Iterator iterator() {
      return SparseArrayLike.iterator$(this);
   }

   public Object toArray(final ClassTag evidence$1) {
      return SparseArrayLike.toArray$(this, evidence$1);
   }

   public List toList() {
      return SparseArrayLike.toList$(this);
   }

   public List toIndexedSeq() {
      return SparseArrayLike.toIndexedSeq$(this);
   }

   public Map toMap() {
      return SparseArrayLike.toMap$(this);
   }

   public int[] _index() {
      return this._index;
   }

   public void _index_$eq(final int[] x$1) {
      this._index = x$1;
   }

   public Object _data() {
      return this._data;
   }

   public void _data_$eq(final Object x$1) {
      this._data = x$1;
   }

   public int load() {
      return this.load;
   }

   public void load_$eq(final int x$1) {
      this.load = x$1;
   }

   public int size() {
      return this.size;
   }

   public ConfigurableDefault default() {
      return this.default;
   }

   public ClassTag manElem() {
      return this.manElem;
   }

   public Zero zero() {
      return this.zero;
   }

   public Object data() {
      return this._data();
   }

   public int[] index() {
      return this._index();
   }

   public Object defaultValue() {
      return this.default().value(this.zero());
   }

   public Iterator valuesIterator() {
      return this.activeValuesIterator();
   }

   public Object valueAt(final int i) {
      return .MODULE$.array_apply(this.data(), i);
   }

   public int indexAt(final int i) {
      return this.index()[i];
   }

   public Iterator keysIterator() {
      return scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.intArrayOps(this.index())).filter((JFunction1.mcZI.sp)(x$1) -> x$1 >= 0);
   }

   public int activeSize() {
      return this.load();
   }

   public boolean contains(final int i) {
      return this.index()[this.breeze$collection$mutable$OpenAddressHashArray$$locate(i)] >= 0;
   }

   public boolean isActive(final int i) {
      return this.index()[i] >= 0;
   }

   public boolean allVisitableIndicesActive() {
      return false;
   }

   public Object apply(final int i) {
      if (i >= 0 && i < this.size()) {
         return this.index().length == 0 ? this.default().value(this.zero()) : .MODULE$.array_apply(this.data(), this.breeze$collection$mutable$OpenAddressHashArray$$locate(i));
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public void update(final int i, final Object v) {
      while(true) {
         if (i >= 0 && i < this.size()) {
            int pos = this.breeze$collection$mutable$OpenAddressHashArray$$locate(i);
            .MODULE$.array_update(this._data(), pos, v);
            if (this._index()[pos] != i && !BoxesRunTime.equals(v, this.defaultValue())) {
               this.load_$eq(this.load() + 1);
               if (this.load() * 4 > this._index().length * 3) {
                  this.rehash();
                  v = v;
                  i = i;
                  continue;
               }

               this._index()[pos] = i;
               BoxedUnit var5 = BoxedUnit.UNIT;
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }

            return;
         }

         throw new IndexOutOfBoundsException((new StringBuilder(27)).append(i).append(" is out of bounds for size ").append(this.size()).toString());
      }
   }

   public Iterator activeKeysIterator() {
      return this.keysIterator();
   }

   public Iterator activeValuesIterator() {
      return this.activeIterator().map((x$2) -> x$2._2());
   }

   public Iterator activeIterator() {
      return scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.intArrayOps(this.index())).zip(scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.genericArrayOps(this.data()))).filter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$activeIterator$1(x$3)));
   }

   public int breeze$collection$mutable$OpenAddressHashArray$$locate(final int i) {
      if (i >= this.size()) {
         throw new IndexOutOfBoundsException((new StringBuilder(22)).append(i).append(" greater than size of ").append(this.size()).toString());
      } else if (i < 0) {
         throw new IndexOutOfBoundsException((new StringBuilder(12)).append(i).append(" less than 0").toString());
      } else {
         int[] index = this.index();
         int len = index.length;
         int hash = this.hashCodeFor(i) & len - 1;

         while(index[hash] != i && index[hash] >= 0) {
            ++hash;
            if (hash >= len) {
               hash = 0;
            }
         }

         return hash;
      }
   }

   private int hashCodeFor(final int i) {
      int code = i * -1640532531;
      code = Integer.reverseBytes(code);
      code *= -1640532531;
      int rotated = code >>> 11 | code << 21;
      return rotated;
   }

   public final void rehash() {
      int[] oldIndex = this.index();
      Object oldValues = this.data();
      int newSize = OpenAddressHashArray$.MODULE$.breeze$collection$mutable$OpenAddressHashArray$$calculateSize(scala.collection.ArrayOps..MODULE$.size$extension(scala.Predef..MODULE$.intArrayOps(oldIndex)) + 1);
      this._index_$eq(new int[newSize]);
      Arrays.fill(this._index(), -1);
      this._data_$eq(this.manElem().newArray(newSize));
      this.default().fillArray(this._data(), this.default().value(this.zero()));
      this.load_$eq(0);

      for(int i = 0; i < oldIndex.length; ++i) {
         if (oldIndex[i] >= 0) {
            this.update(oldIndex[i], .MODULE$.array_apply(oldValues, i));
         }
      }

   }

   public int iterableSize() {
      return this.index().length;
   }

   public String toString() {
      return this.activeIterator().mkString("OpenAddressHashArray(", ", ", ")");
   }

   public OpenAddressHashArray copy() {
      return new OpenAddressHashArray(Arrays.copyOf(this._index(), this._index().length), ArrayUtil$.MODULE$.copyOf(this._data(), .MODULE$.array_length(this._data())), this.load(), this.size(), this.default(), this.manElem(), this.zero());
   }

   public void copyTo(final OpenAddressHashArray other) {
      scala.Predef..MODULE$.require(other.length() == other.length(), () -> "vectors must have the same length");
      scala.Predef..MODULE$.require(BoxesRunTime.equals(this.defaultValue(), other.defaultValue()), () -> "vectors must have the same default");
      other._index_$eq((int[])this._index().clone());
      other._data_$eq(.MODULE$.array_clone(this._data()));
      other.load_$eq(this.load());
   }

   public void clear() {
      this._data_$eq(this.default().makeArray(16, this.zero(), this.manElem()));
      this._index_$eq(OpenAddressHashArray$.MODULE$.breeze$collection$mutable$OpenAddressHashArray$$emptyIndexArray(16));
      this.load_$eq(0);
   }

   public int hashCode() {
      return scala.util.hashing.MurmurHash3..MODULE$.unorderedHash(this.iterator().filter((x$4) -> BoxesRunTime.boxToBoolean($anonfun$hashCode$1(this, x$4))), 43);
   }

   public boolean equals(final Object that) {
      boolean var2;
      if (that instanceof OpenAddressHashArray) {
         boolean var7;
         label34: {
            OpenAddressHashArray var4 = (OpenAddressHashArray)that;
            if (this != var4) {
               label33: {
                  if (this.size() == var4.size()) {
                     try {
                        var7 = this.iterator().forall((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$equals$1(var4, x0$1)));
                     } catch (ClassCastException var6) {
                        var7 = false;
                     }

                     if (var7) {
                        break label33;
                     }
                  }

                  var7 = false;
                  break label34;
               }
            }

            var7 = true;
         }

         var2 = var7;
      } else {
         var2 = false;
      }

      return var2;
   }

   public double[] _data$mcD$sp() {
      return (double[])this._data();
   }

   public float[] _data$mcF$sp() {
      return (float[])this._data();
   }

   public int[] _data$mcI$sp() {
      return (int[])this._data();
   }

   public long[] _data$mcJ$sp() {
      return (long[])this._data();
   }

   public void _data$mcD$sp_$eq(final double[] x$1) {
      this._data_$eq(x$1);
   }

   public void _data$mcF$sp_$eq(final float[] x$1) {
      this._data_$eq(x$1);
   }

   public void _data$mcI$sp_$eq(final int[] x$1) {
      this._data_$eq(x$1);
   }

   public void _data$mcJ$sp_$eq(final long[] x$1) {
      this._data_$eq(x$1);
   }

   public ConfigurableDefault default$mcD$sp() {
      return this.default();
   }

   public ConfigurableDefault default$mcF$sp() {
      return this.default();
   }

   public ConfigurableDefault default$mcI$sp() {
      return this.default();
   }

   public ConfigurableDefault default$mcJ$sp() {
      return this.default();
   }

   public Zero zero$mcD$sp() {
      return this.zero();
   }

   public Zero zero$mcF$sp() {
      return this.zero();
   }

   public Zero zero$mcI$sp() {
      return this.zero();
   }

   public Zero zero$mcJ$sp() {
      return this.zero();
   }

   public double[] data$mcD$sp() {
      return (double[])this.data();
   }

   public float[] data$mcF$sp() {
      return (float[])this.data();
   }

   public int[] data$mcI$sp() {
      return (int[])this.data();
   }

   public long[] data$mcJ$sp() {
      return (long[])this.data();
   }

   public double defaultValue$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.defaultValue());
   }

   public float defaultValue$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.defaultValue());
   }

   public int defaultValue$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.defaultValue());
   }

   public long defaultValue$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.defaultValue());
   }

   public double valueAt$mcD$sp(final int i) {
      return BoxesRunTime.unboxToDouble(this.valueAt(i));
   }

   public float valueAt$mcF$sp(final int i) {
      return BoxesRunTime.unboxToFloat(this.valueAt(i));
   }

   public int valueAt$mcI$sp(final int i) {
      return BoxesRunTime.unboxToInt(this.valueAt(i));
   }

   public long valueAt$mcJ$sp(final int i) {
      return BoxesRunTime.unboxToLong(this.valueAt(i));
   }

   public double apply$mcD$sp(final int i) {
      return BoxesRunTime.unboxToDouble(this.apply(i));
   }

   public float apply$mcF$sp(final int i) {
      return BoxesRunTime.unboxToFloat(this.apply(i));
   }

   public int apply$mcI$sp(final int i) {
      return BoxesRunTime.unboxToInt(this.apply(i));
   }

   public long apply$mcJ$sp(final int i) {
      return BoxesRunTime.unboxToLong(this.apply(i));
   }

   public void update$mcD$sp(final int i, final double v) {
      this.update(i, BoxesRunTime.boxToDouble(v));
   }

   public void update$mcF$sp(final int i, final float v) {
      this.update(i, BoxesRunTime.boxToFloat(v));
   }

   public void update$mcI$sp(final int i, final int v) {
      this.update(i, BoxesRunTime.boxToInteger(v));
   }

   public void update$mcJ$sp(final int i, final long v) {
      this.update(i, BoxesRunTime.boxToLong(v));
   }

   public OpenAddressHashArray copy$mcD$sp() {
      return this.copy();
   }

   public OpenAddressHashArray copy$mcF$sp() {
      return this.copy();
   }

   public OpenAddressHashArray copy$mcI$sp() {
      return this.copy();
   }

   public OpenAddressHashArray copy$mcJ$sp() {
      return this.copy();
   }

   public void copyTo$mcD$sp(final OpenAddressHashArray other) {
      this.copyTo(other);
   }

   public void copyTo$mcF$sp(final OpenAddressHashArray other) {
      this.copyTo(other);
   }

   public void copyTo$mcI$sp(final OpenAddressHashArray other) {
      this.copyTo(other);
   }

   public void copyTo$mcJ$sp(final OpenAddressHashArray other) {
      this.copyTo(other);
   }

   public boolean specInstance$() {
      return false;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$activeIterator$1(final Tuple2 x$3) {
      return x$3._1$mcI$sp() >= 0;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$hashCode$1(final OpenAddressHashArray $this, final Tuple2 x$4) {
      return !BoxesRunTime.equals(x$4._2(), $this.default().value($this.zero()));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$equals$1(final OpenAddressHashArray x2$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         int k = x0$1._1$mcI$sp();
         Object v = x0$1._2();
         Object var7 = x2$1.apply(k);
         boolean var3;
         if (BoxesRunTime.equals(v, var7)) {
            var3 = true;
         } else {
            var3 = false;
         }

         return var3;
      } else {
         throw new MatchError(x0$1);
      }
   }

   public OpenAddressHashArray(final int[] _index, final Object _data, final int load, final int size, final ConfigurableDefault default, final ClassTag manElem, final Zero zero) {
      this._index = _index;
      this._data = _data;
      this.load = load;
      this.size = size;
      this.default = default;
      this.manElem = manElem;
      this.zero = zero;
      super();
      Storage.$init$(this);
      SparseArrayLike.$init$(this);
      scala.Predef..MODULE$.require(size > 0, () -> (new StringBuilder(31)).append("Size must be positive, but got ").append(this.size()).toString());
   }

   public OpenAddressHashArray(final int size, final ConfigurableDefault default, final int initialSize, final ClassTag manElem, final Zero zero) {
      this(OpenAddressHashArray$.MODULE$.breeze$collection$mutable$OpenAddressHashArray$$emptyIndexArray(OpenAddressHashArray$.MODULE$.breeze$collection$mutable$OpenAddressHashArray$$calculateSize(initialSize)), default.makeArray(OpenAddressHashArray$.MODULE$.breeze$collection$mutable$OpenAddressHashArray$$calculateSize(initialSize), zero, manElem), 0, size, default, manElem, zero);
   }

   public OpenAddressHashArray(final int size, final ConfigurableDefault default, final ClassTag manElem, final Zero zero) {
      this(size, default, 16, manElem, zero);
   }

   public OpenAddressHashArray(final int size, final ClassTag manElem, final Zero zero) {
      this(size, ConfigurableDefault$.MODULE$.default(), manElem, zero);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

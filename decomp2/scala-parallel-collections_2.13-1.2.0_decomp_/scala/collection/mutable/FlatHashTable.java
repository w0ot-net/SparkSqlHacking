package scala.collection.mutable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.collection.AbstractIterator;
import scala.collection.Iterator;
import scala.collection.mutable.HashTable.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;
import scala.util.Random;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0005f\u0001\u0003*T!\u0003\r\t!V-\t\u000f\u0005\u0005\u0005\u0001\"\u0001\u0002\u0004\"9\u00111\u0017\u0001\u0005\n\u0005U\u0006BCA\u0011\u0001\u0001\u0007I\u0011A+\u0002\u0014!Q\u0011Q\u0018\u0001A\u0002\u0013\u0005Q+a0\t\u0013\u0005U\u0002\u00011A\u0005\u0002\u0005]\u0002\"CAc\u0001\u0001\u0007I\u0011AAd\u0011%\t\t\u0005\u0001a\u0001\n#\t\u0019\u0002C\u0005\u0002L\u0002\u0001\r\u0011\"\u0005\u0002N\"I\u0011Q\t\u0001A\u0002\u0013E\u00111\u0003\u0005\n\u0003#\u0004\u0001\u0019!C\t\u0003'D\u0011\"!\u0014\u0001\u0001\u0004%\t\"a\u0014\t\u0013\u0005]\u0007\u00011A\u0005\u0012\u0005e\u0007\"CA%\u0001\u0001\u0007I\u0011CA\n\u0011%\ti\u000e\u0001a\u0001\n#\ty\u000eC\u0004\u0002d\u0002!\t\"!:\t\u000f\u0005-\b\u0001\"\u0001\u0002\u0014!9\u0011Q\u0004\u0001\u0005\u0002\u0005M\u0001bBAw\u0001\u0011%\u00111\u0003\u0005\b\u0003_\u0004A\u0011CA\n\u0011\u001d\t\t\u0010\u0001C\t\u0003'Aq!a=\u0001\t\u0003\t)\u0010C\u0004\u0003\u0012\u0001!\tAa\u0005\t\u000f\t}\u0001\u0001\"\u0001\u0003\"!9!1\u0006\u0001\u0005\u0002\t5\u0002b\u0002B\u0019\u0001\u0011%!1\u0007\u0005\b\u0005o\u0001A\u0011\u0001B\u001d\u0011\u001d\u0011i\u0004\u0001C\t\u0005\u007fAqA!\u0012\u0001\t\u0003\u00119\u0005C\u0004\u0003L\u0001!\tA!\u0014\t\u000f\t]\u0003\u0001\"\u0003\u0002\u0004\"9!\u0011\f\u0001\u0005\n\u0005\r\u0005b\u0002B.\u0001\u0011U!Q\f\u0005\b\u0005G\u0002AQ\u0003B3\u0011\u001d\u0011I\u0007\u0001C\u000b\u0005WB\u0001B!\u001d\u0001\t\u000b)\u00161\u0003\u0005\b\u0005g\u0002AQ\u0003B;\u0011\u001d\u0011I\b\u0001C\u000b\u0005wBqAa \u0001\t+\t\u0019\t\u0003\u0005\u0003\u0002\u0002!\t!VAB\u0011!\u0011\u0019\t\u0001C\u0001+\u0006\r\u0005b\u0002BC\u0001\u0011E\u00111\u0011\u0005\b\u0005\u000f\u0003A\u0011CA[\u0011\u001d\u0011I\t\u0001C\t\u0003kCqAa#\u0001\t#\u0011i\tC\u0004\u0003\u0012\u0002!\t!a!\t\u0011\tM\u0005\u0001\"\u0001V\u0005+CqA!'\u0001\t#\u0011Yj\u0002\u0004c'\"\u0005Qk\u0019\u0004\u0007%NC\t!\u00163\t\u000b\u0015\fD\u0011A4\t\u000b!\fDQA5\b\u000ba\f\u0004\u0012B=\u0007\u000bm\f\u0004\u0012\u0002?\t\u000b\u0015,D\u0011A?\t\u000by,D\u0011I@\t\u000f\u0005\u001dQ\u0007\"\u0011\u0002\n!9\u0011\u0011C\u0019\u0005\u0002\u0005M\u0001bBA\u000bc\u0011\u0015\u00111\u0003\u0005\b\u0003/\tD\u0011AA\r\u0011\u001d\t\u0019#\rC\u0001\u0003K1a!a\u000b2\u0001\u00055\u0002BCA\u0019{\t\u0015\r\u0011\"\u0001\u0002\u0014!Q\u00111G\u001f\u0003\u0002\u0003\u0006I!!\u0001\t\u0015\u0005URH!b\u0001\n\u0003\t9\u0004\u0003\u0006\u0002@u\u0012\t\u0011)A\u0005\u0003sA!\"!\u0011>\u0005\u000b\u0007I\u0011AA\n\u0011)\t\u0019%\u0010B\u0001B\u0003%\u0011\u0011\u0001\u0005\u000b\u0003\u000bj$Q1A\u0005\u0002\u0005M\u0001BCA${\t\u0005\t\u0015!\u0003\u0002\u0002!Q\u0011\u0011J\u001f\u0003\u0006\u0004%\t!a\u0005\t\u0015\u0005-SH!A!\u0002\u0013\t\t\u0001\u0003\u0006\u0002Nu\u0012)\u0019!C\u0001\u0003\u001fB!\"a\u0015>\u0005\u0003\u0005\u000b\u0011BA)\u0011\u0019)W\b\"\u0001\u0002V\u0019I\u00111P\u0019\u0011\u0002\u0007\u0005\u0011Q\u0010\u0005\b\u0003\u0003[E\u0011AAB\u0011\u001d\tYi\u0013C\u000b\u0003'Aq!!$L\t+\t\u0019\u0002C\u0004\u0002\u0010.#)\"!%\t\u000f\u0005m5\n\"\u0006\u0002\u001e\"9\u0011qU&\u0005\u0006\u0005%&!\u0004$mCRD\u0015m\u001d5UC\ndWM\u0003\u0002U+\u00069Q.\u001e;bE2,'B\u0001,X\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u00021\u0006)1oY1mCV\u0019!,!-\u0014\u0007\u0001Yv\f\u0005\u0002];6\tq+\u0003\u0002_/\n1\u0011I\\=SK\u001a\u0004B\u0001Y&\u00020:\u0011\u0011\rM\u0007\u0002'\u0006ia\t\\1u\u0011\u0006\u001c\b\u000eV1cY\u0016\u0004\"!Y\u0019\u0014\u0005EZ\u0016A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003\r\fQb]3fI\u001e+g.\u001a:bi>\u0014X#\u00016\u0011\u0007-\u0004(/D\u0001m\u0015\tig.\u0001\u0003mC:<'\"A8\u0002\t)\fg/Y\u0005\u0003c2\u00141\u0002\u00165sK\u0006$Gj\\2bYB\u00111O^\u0007\u0002i*\u0011QoV\u0001\u0005kRLG.\u0003\u0002xi\n1!+\u00198e_6\fABT;mYN+g\u000e^5oK2\u0004\"A_\u001b\u000e\u0003E\u0012ABT;mYN+g\u000e^5oK2\u001c\"!N.\u0015\u0003e\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003\u0003\u00012\u0001XA\u0002\u0013\r\t)a\u0016\u0002\u0004\u0013:$\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005-\u0001cA6\u0002\u000e%\u0019\u0011q\u00027\u0003\rM#(/\u001b8h\u0003E!WMZ1vYRdu.\u00193GC\u000e$xN]\u000b\u0003\u0003\u0003\tq\u0002\\8bI\u001a\u000b7\r^8s\t\u0016tW/\\\u0001\u0011g&TXMR8s)\"\u0014Xm\u001d5pY\u0012$b!!\u0001\u0002\u001c\u0005}\u0001bBA\u000fw\u0001\u0007\u0011\u0011A\u0001\u0005g&TX\rC\u0004\u0002\"m\u0002\r!!\u0001\u0002\u0017}cw.\u00193GC\u000e$xN]\u0001\r]\u0016<H\u000b\u001b:fg\"|G\u000e\u001a\u000b\u0007\u0003\u0003\t9#!\u000b\t\u000f\u0005\u0005B\b1\u0001\u0002\u0002!9\u0011Q\u0004\u001fA\u0002\u0005\u0005!\u0001C\"p]R,g\u000e^:\u0016\t\u0005=\u0012QL\n\u0003{m\u000b!\u0002\\8bI\u001a\u000b7\r^8s\u0003-aw.\u00193GC\u000e$xN\u001d\u0011\u0002\u000bQ\f'\r\\3\u0016\u0005\u0005e\u0002\u0003\u0002/\u0002<mK1!!\u0010X\u0005\u0015\t%O]1z\u0003\u0019!\u0018M\u00197fA\u0005IA/\u00192mKNK'0Z\u0001\u000bi\u0006\u0014G.Z*ju\u0016\u0004\u0013!\u0003;ie\u0016\u001c\bn\u001c7e\u0003)!\bN]3tQ>dG\rI\u0001\ng\u0016,GM^1mk\u0016\f!b]3fIZ\fG.^3!\u0003\u001d\u0019\u0018N_3nCB,\"!!\u0015\u0011\u000bq\u000bY$!\u0001\u0002\u0011ML'0Z7ba\u0002\"b\"a\u0016\u0002p\u0005E\u00141OA;\u0003o\nI\b\u0005\u0003{{\u0005e\u0003\u0003BA.\u0003;b\u0001\u0001B\u0004\u0002`u\u0012\r!!\u0019\u0003\u0003\u0005\u000bB!a\u0019\u0002jA\u0019A,!\u001a\n\u0007\u0005\u001dtKA\u0004O_RD\u0017N\\4\u0011\u0007q\u000bY'C\u0002\u0002n]\u00131!\u00118z\u0011\u001d\t\tD\u0013a\u0001\u0003\u0003Aq!!\u000eK\u0001\u0004\tI\u0004C\u0004\u0002B)\u0003\r!!\u0001\t\u000f\u0005\u0015#\n1\u0001\u0002\u0002!9\u0011\u0011\n&A\u0002\u0005\u0005\u0001bBA'\u0015\u0002\u0007\u0011\u0011\u000b\u0002\n\u0011\u0006\u001c\b.\u0016;jYN,B!a \u0002&N\u00111jW\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0005\u0005\u0015\u0005c\u0001/\u0002\b&\u0019\u0011\u0011R,\u0003\tUs\u0017\u000e^\u0001\u0015g&TX-T1q\u0005V\u001c7.\u001a;CSR\u001c\u0016N_3\u0002#ML'0Z'ba\n+8m[3u'&TX-A\u0004j[B\u0014xN^3\u0015\r\u0005\u0005\u00111SAL\u0011\u001d\t)j\u0014a\u0001\u0003\u0003\tQ\u0001[2pI\u0016Dq!!'P\u0001\u0004\t\t!\u0001\u0003tK\u0016$\u0017aC3mK6$v.\u00128uef$2aWAP\u0011\u001d\t\t\u000b\u0015a\u0001\u0003G\u000bA!\u001a7f[B!\u00111LAS\t\u001d\tyf\u0013b\u0001\u0003C\n1\"\u001a8uef$v.\u00127f[R!\u00111UAV\u0011\u0019\ti+\u0015a\u00017\u0006)QM\u001c;ssB!\u00111LAY\t\u001d\ty\u0006\u0001b\u0001\u0003C\n!\u0002^1cY\u0016$UMY;h+\t\t9\fE\u0002]\u0003sK1!a/X\u0005\u001d\u0011un\u001c7fC:\fqb\u00187pC\u00124\u0015m\u0019;pe~#S-\u001d\u000b\u0005\u0003\u000b\u000b\t\rC\u0005\u0002D\u0012\t\t\u00111\u0001\u0002\u0002\u0005\u0019\u0001\u0010J\u0019\u0002\u0013Q\f'\r\\3`I\u0015\fH\u0003BAC\u0003\u0013D\u0011\"a1\u0007\u0003\u0003\u0005\r!!\u000f\u0002\u001bQ\f'\r\\3TSj,w\fJ3r)\u0011\t))a4\t\u0013\u0005\r\u0007\"!AA\u0002\u0005\u0005\u0011!\u0004;ie\u0016\u001c\bn\u001c7e?\u0012*\u0017\u000f\u0006\u0003\u0002\u0006\u0006U\u0007\"CAb\u0015\u0005\u0005\t\u0019AA\u0001\u0003-\u0019\u0018N_3nCB|F%Z9\u0015\t\u0005\u0015\u00151\u001c\u0005\n\u0003\u0007d\u0011\u0011!a\u0001\u0003#\nQb]3fIZ\fG.^3`I\u0015\fH\u0003BAC\u0003CD\u0011\"a1\u000f\u0003\u0003\u0005\r!!\u0001\u0002\u0011\r\f\u0007/Y2jif$B!!\u0001\u0002h\"9\u0011\u0011^\bA\u0002\u0005\u0005\u0011\u0001D3ya\u0016\u001cG/\u001a3TSj,\u0017aC5oSRL\u0017\r\\*ju\u0016\fq\"\u001b8ji&\fGnQ1qC\u000eLG/_\u0001\u000be\u0006tGm\\7TK\u0016$\u0017!\u0004;bE2,7+\u001b>f'\u0016,G-\u0001\u0003j]&$HCBAC\u0003o\u00149\u0001C\u0004\u0002zV\u0001\r!a?\u0002\u0005%t\u0007\u0003BA\u007f\u0005\u0007i!!a@\u000b\u0007\t\u0005a.\u0001\u0002j_&!!QAA\u0000\u0005Ey%M[3di&s\u0007/\u001e;TiJ,\u0017-\u001c\u0005\b\u0005\u0013)\u0002\u0019\u0001B\u0006\u0003\u00051\u0007c\u0002/\u0003\u000e\u0005=\u0016QQ\u0005\u0004\u0005\u001f9&!\u0003$v]\u000e$\u0018n\u001c82\u0003-\u0019XM]5bY&TX\rV8\u0015\t\u0005\u0015%Q\u0003\u0005\b\u0005/1\u0002\u0019\u0001B\r\u0003\ryW\u000f\u001e\t\u0005\u0003{\u0014Y\"\u0003\u0003\u0003\u001e\u0005}(AE(cU\u0016\u001cGoT;uaV$8\u000b\u001e:fC6\f\u0011BZ5oI\u0016sGO]=\u0015\t\t\r\"\u0011\u0006\t\u00069\n\u0015\u0012qV\u0005\u0004\u0005O9&AB(qi&|g\u000eC\u0004\u0002\"^\u0001\r!a,\u0002\u0019\r|g\u000e^1j]N,E.Z7\u0015\t\u0005]&q\u0006\u0005\b\u0003CC\u0002\u0019AAX\u000311\u0017N\u001c3FY\u0016l\u0017*\u001c9m)\rY&Q\u0007\u0005\b\u0003CK\u0002\u0019AAX\u0003\u001d\tG\rZ#mK6$B!a.\u0003<!9\u0011\u0011\u0015\u000eA\u0002\u0005=\u0016\u0001C1eI\u0016sGO]=\u0015\t\u0005]&\u0011\t\u0005\u0007\u0005\u0007Z\u0002\u0019A.\u0002\u00119,w/\u00128uef\f!B]3n_Z,W\t\\3n)\u0011\t9L!\u0013\t\u000f\u0005\u0005F\u00041\u0001\u00020\u0006A\u0011\u000e^3sCR|'/\u0006\u0002\u0003PA1!\u0011\u000bB*\u0003_k\u0011!V\u0005\u0004\u0005+*&\u0001C%uKJ\fGo\u001c:\u0002\u0013\u001d\u0014xn\u001e+bE2,\u0017aD2iK\u000e\\7i\u001c8tSN$XM\u001c;\u0002\u00199t7+\u001b>f\u001b\u0006\u0004\u0018\t\u001a3\u0015\t\u0005\u0015%q\f\u0005\b\u0005C\u0002\u0003\u0019AA\u0001\u0003\u0005A\u0017a\u00048o'&TX-T1q%\u0016lwN^3\u0015\t\u0005\u0015%q\r\u0005\b\u0005C\n\u0003\u0019AA\u0001\u00039qgnU5{K6\u000b\u0007OU3tKR$B!!\"\u0003n!9!q\u000e\u0012A\u0002\u0005\u0005\u0011a\u0003;bE2,G*\u001a8hi\"\f1\u0003^8uC2\u001c\u0016N_3NCB\u0014UoY6fiN\fqbY1mGNK'0Z'baNK'0\u001a\u000b\u0005\u0003\u0003\u00119\bC\u0004\u0003p\u0011\u0002\r!!\u0001\u0002\u0017ML'0Z'ba&s\u0017\u000e\u001e\u000b\u0005\u0003\u000b\u0013i\bC\u0004\u0003p\u0015\u0002\r!!\u0001\u0002+ML'0Z'ba&s\u0017\u000e^!oIJ+'-^5mI\u0006a\u0001O]5oiNK'0Z'ba\u0006i\u0001O]5oi\u000e{g\u000e^3oiN\fab]5{K6\u000b\u0007\u000fR5tC\ndW-\u0001\tjgNK'0Z'ba\u0012+g-\u001b8fI\u0006\t\u0012\r\\<bsNLe.\u001b;TSj,W*\u00199\u0002\u000b%tG-\u001a=\u0015\t\u0005\u0005!q\u0012\u0005\b\u0003+c\u0003\u0019AA\u0001\u0003)\u0019G.Z1s)\u0006\u0014G.Z\u0001\u0012Q\u0006\u001c\b\u000eV1cY\u0016\u001cuN\u001c;f]R\u001cXC\u0001BL!\u0011\u0001W(a,\u0002!%t\u0017\u000e^,ji\"\u001cuN\u001c;f]R\u001cH\u0003BAC\u0005;CqAa(0\u0001\u0004\u00119*A\u0001d\u0001"
)
public interface FlatHashTable extends HashUtils {
   static int newThreshold(final int _loadFactor, final int size) {
      return FlatHashTable$.MODULE$.newThreshold(_loadFactor, size);
   }

   static int sizeForThreshold(final int size, final int _loadFactor) {
      return FlatHashTable$.MODULE$.sizeForThreshold(size, _loadFactor);
   }

   static int loadFactorDenum() {
      return FlatHashTable$.MODULE$.loadFactorDenum();
   }

   static int defaultLoadFactor() {
      return FlatHashTable$.MODULE$.defaultLoadFactor();
   }

   static ThreadLocal seedGenerator() {
      return FlatHashTable$.MODULE$.seedGenerator();
   }

   private boolean tableDebug() {
      return false;
   }

   int _loadFactor();

   void _loadFactor_$eq(final int x$1);

   Object[] table();

   void table_$eq(final Object[] x$1);

   int tableSize();

   void tableSize_$eq(final int x$1);

   int threshold();

   void threshold_$eq(final int x$1);

   int[] sizemap();

   void sizemap_$eq(final int[] x$1);

   int seedvalue();

   void seedvalue_$eq(final int x$1);

   // $FF: synthetic method
   static int capacity$(final FlatHashTable $this, final int expectedSize) {
      return $this.capacity(expectedSize);
   }

   default int capacity(final int expectedSize) {
      return .MODULE$.nextPositivePowerOfTwo(expectedSize);
   }

   // $FF: synthetic method
   static int initialSize$(final FlatHashTable $this) {
      return $this.initialSize();
   }

   default int initialSize() {
      return 32;
   }

   // $FF: synthetic method
   static int size$(final FlatHashTable $this) {
      return $this.size();
   }

   default int size() {
      return this.tableSize();
   }

   private int initialCapacity() {
      return this.capacity(this.initialSize());
   }

   // $FF: synthetic method
   static int randomSeed$(final FlatHashTable $this) {
      return $this.randomSeed();
   }

   default int randomSeed() {
      return ((Random)FlatHashTable$.MODULE$.seedGenerator().get()).nextInt();
   }

   // $FF: synthetic method
   static int tableSizeSeed$(final FlatHashTable $this) {
      return $this.tableSizeSeed();
   }

   default int tableSizeSeed() {
      return Integer.bitCount(this.table().length - 1);
   }

   // $FF: synthetic method
   static void init$(final FlatHashTable $this, final ObjectInputStream in, final Function1 f) {
      $this.init(in, f);
   }

   default void init(final ObjectInputStream in, final Function1 f) {
      this._loadFactor_$eq(in.readInt());
      scala.Predef..MODULE$.assert(this._loadFactor() > 0);
      int size = in.readInt();
      this.tableSize_$eq(0);
      scala.Predef..MODULE$.assert(size >= 0);
      this.table_$eq(new Object[this.capacity(FlatHashTable$.MODULE$.sizeForThreshold(size, this._loadFactor()))]);
      this.threshold_$eq(FlatHashTable$.MODULE$.newThreshold(this._loadFactor(), this.table().length));
      this.seedvalue_$eq(in.readInt());
      boolean smDefined = in.readBoolean();
      if (smDefined) {
         this.sizeMapInit(this.table().length);
      } else {
         this.sizemap_$eq((int[])null);
      }

      for(int index = 0; index < size; ++index) {
         Object elem = this.entryToElem(in.readObject());
         f.apply(elem);
         this.addElem(elem);
      }

   }

   // $FF: synthetic method
   static void serializeTo$(final FlatHashTable $this, final ObjectOutputStream out) {
      $this.serializeTo(out);
   }

   default void serializeTo(final ObjectOutputStream out) {
      out.writeInt(this._loadFactor());
      out.writeInt(this.tableSize());
      out.writeInt(this.seedvalue());
      out.writeBoolean(this.isSizeMapDefined());
      this.iterator().foreach((x$1) -> {
         $anonfun$serializeTo$1(out, x$1);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   static Option findEntry$(final FlatHashTable $this, final Object elem) {
      return $this.findEntry(elem);
   }

   default Option findEntry(final Object elem) {
      Object var3 = this.findElemImpl(elem);
      return (Option)(var3 == null ? scala.None..MODULE$ : new Some(this.entryToElem(var3)));
   }

   // $FF: synthetic method
   static boolean containsElem$(final FlatHashTable $this, final Object elem) {
      return $this.containsElem(elem);
   }

   default boolean containsElem(final Object elem) {
      return this.findElemImpl(elem) != null;
   }

   private Object findElemImpl(final Object elem) {
      Object searchEntry = this.elemToEntry(elem);
      int h = this.index(searchEntry.hashCode());

      Object curEntry;
      for(curEntry = this.table()[h]; curEntry != null && !BoxesRunTime.equals(curEntry, searchEntry); curEntry = this.table()[h]) {
         h = (h + 1) % this.table().length;
      }

      return curEntry;
   }

   // $FF: synthetic method
   static boolean addElem$(final FlatHashTable $this, final Object elem) {
      return $this.addElem(elem);
   }

   default boolean addElem(final Object elem) {
      return this.addEntry(this.elemToEntry(elem));
   }

   // $FF: synthetic method
   static boolean addEntry$(final FlatHashTable $this, final Object newEntry) {
      return $this.addEntry(newEntry);
   }

   default boolean addEntry(final Object newEntry) {
      int h = this.index(newEntry.hashCode());

      for(Object curEntry = this.table()[h]; curEntry != null; curEntry = this.table()[h]) {
         if (BoxesRunTime.equals(curEntry, newEntry)) {
            return false;
         }

         h = (h + 1) % this.table().length;
      }

      this.table()[h] = newEntry;
      this.tableSize_$eq(this.tableSize() + 1);
      this.nnSizeMapAdd(h);
      if (this.tableSize() >= this.threshold()) {
         this.growTable();
      }

      return true;
   }

   // $FF: synthetic method
   static boolean removeElem$(final FlatHashTable $this, final Object elem) {
      return $this.removeElem(elem);
   }

   default boolean removeElem(final Object elem) {
      if (this.tableDebug()) {
         this.checkConsistent();
      }

      Object removalEntry = this.elemToEntry(elem);
      int h = this.index(removalEntry.hashCode());

      for(Object curEntry = this.table()[h]; curEntry != null; curEntry = this.table()[h]) {
         if (BoxesRunTime.equals(curEntry, removalEntry)) {
            int h0 = h;

            for(int h1 = (h + 1) % this.table().length; this.table()[h1] != null; h1 = (h1 + 1) % this.table().length) {
               int h2 = this.index(this.table()[h1].hashCode());
               if (h2 != h1 && this.precedes$1(h2, h0)) {
                  this.table()[h0] = this.table()[h1];
                  h0 = h1;
               }
            }

            this.table()[h0] = null;
            this.tableSize_$eq(this.tableSize() - 1);
            this.nnSizeMapRemove(h0);
            if (this.tableDebug()) {
               this.checkConsistent();
            }

            return true;
         }

         h = (h + 1) % this.table().length;
      }

      return false;
   }

   // $FF: synthetic method
   static Iterator iterator$(final FlatHashTable $this) {
      return $this.iterator();
   }

   default Iterator iterator() {
      return new AbstractIterator() {
         private int i;
         // $FF: synthetic field
         private final FlatHashTable $outer;

         public boolean hasNext() {
            while(this.i < this.$outer.table().length && this.$outer.table()[this.i] == null) {
               ++this.i;
            }

            return this.i < this.$outer.table().length;
         }

         public Object next() {
            if (this.hasNext()) {
               ++this.i;
               return this.$outer.entryToElem(this.$outer.table()[this.i - 1]);
            } else {
               return scala.collection.Iterator..MODULE$.empty().next();
            }
         }

         public {
            if (FlatHashTable.this == null) {
               throw null;
            } else {
               this.$outer = FlatHashTable.this;
               this.i = 0;
            }
         }
      };
   }

   private void growTable() {
      Object[] oldtable = this.table();
      this.table_$eq(new Object[this.table().length * 2]);
      this.tableSize_$eq(0);
      this.nnSizeMapReset(this.table().length);
      this.seedvalue_$eq(this.tableSizeSeed());
      this.threshold_$eq(FlatHashTable$.MODULE$.newThreshold(this._loadFactor(), this.table().length));

      for(int i = 0; i < oldtable.length; ++i) {
         Object entry = oldtable[i];
         if (entry != null) {
            BoxesRunTime.boxToBoolean(this.addEntry(entry));
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      }

      if (this.tableDebug()) {
         this.checkConsistent();
      }
   }

   private void checkConsistent() {
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.table().length).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
         if (this.table()[i] != null && !this.containsElem(this.entryToElem(this.table()[i]))) {
            scala.Predef..MODULE$.assert(false, () -> (new StringBuilder(2)).append(i).append(" ").append(this.table()[i]).append(" ").append(scala.Predef..MODULE$.wrapRefArray(this.table()).mkString()).toString());
         }
      });
   }

   // $FF: synthetic method
   static void nnSizeMapAdd$(final FlatHashTable $this, final int h) {
      $this.nnSizeMapAdd(h);
   }

   default void nnSizeMapAdd(final int h) {
      if (this.sizemap() != null) {
         int p = h >> this.sizeMapBucketBitSize();
         int[] var3 = this.sizemap();
         int var10002 = var3[p]++;
      }
   }

   // $FF: synthetic method
   static void nnSizeMapRemove$(final FlatHashTable $this, final int h) {
      $this.nnSizeMapRemove(h);
   }

   default void nnSizeMapRemove(final int h) {
      if (this.sizemap() != null) {
         int[] var2 = this.sizemap();
         int var3 = h >> this.sizeMapBucketBitSize();
         int var10002 = var2[var3]--;
      }
   }

   // $FF: synthetic method
   static void nnSizeMapReset$(final FlatHashTable $this, final int tableLength) {
      $this.nnSizeMapReset(tableLength);
   }

   default void nnSizeMapReset(final int tableLength) {
      if (this.sizemap() != null) {
         int nsize = this.calcSizeMapSize(tableLength);
         if (this.sizemap().length != nsize) {
            this.sizemap_$eq(new int[nsize]);
         } else {
            Arrays.fill(this.sizemap(), 0);
         }
      }
   }

   // $FF: synthetic method
   static int totalSizeMapBuckets$(final FlatHashTable $this) {
      return $this.totalSizeMapBuckets();
   }

   default int totalSizeMapBuckets() {
      return (this.table().length - 1) / this.sizeMapBucketSize() + 1;
   }

   // $FF: synthetic method
   static int calcSizeMapSize$(final FlatHashTable $this, final int tableLength) {
      return $this.calcSizeMapSize(tableLength);
   }

   default int calcSizeMapSize(final int tableLength) {
      return (tableLength >> this.sizeMapBucketBitSize()) + 1;
   }

   // $FF: synthetic method
   static void sizeMapInit$(final FlatHashTable $this, final int tableLength) {
      $this.sizeMapInit(tableLength);
   }

   default void sizeMapInit(final int tableLength) {
      this.sizemap_$eq(new int[this.calcSizeMapSize(tableLength)]);
   }

   // $FF: synthetic method
   static void sizeMapInitAndRebuild$(final FlatHashTable $this) {
      $this.sizeMapInitAndRebuild();
   }

   default void sizeMapInitAndRebuild() {
      this.sizeMapInit(this.table().length);
      int totalbuckets = this.totalSizeMapBuckets();
      int bucketidx = 0;
      int tableidx = 0;
      Object[] tbl = this.table();

      for(int tableuntil = scala.runtime.RichInt..MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(this.sizeMapBucketSize()), tbl.length); bucketidx < totalbuckets; ++bucketidx) {
         int currbucketsz;
         for(currbucketsz = 0; tableidx < tableuntil; ++tableidx) {
            if (tbl[tableidx] != null) {
               ++currbucketsz;
            }
         }

         this.sizemap()[bucketidx] = currbucketsz;
         tableuntil += this.sizeMapBucketSize();
      }

   }

   // $FF: synthetic method
   static void printSizeMap$(final FlatHashTable $this) {
      $this.printSizeMap();
   }

   default void printSizeMap() {
      scala.Predef..MODULE$.println(scala.Predef..MODULE$.wrapIntArray(this.sizemap()).mkString("szmap: [", ", ", "]"));
   }

   // $FF: synthetic method
   static void printContents$(final FlatHashTable $this) {
      $this.printContents();
   }

   default void printContents() {
      scala.Predef..MODULE$.println(scala.Predef..MODULE$.wrapRefArray(this.table()).mkString("[", ", ", "]"));
   }

   // $FF: synthetic method
   static void sizeMapDisable$(final FlatHashTable $this) {
      $this.sizeMapDisable();
   }

   default void sizeMapDisable() {
      this.sizemap_$eq((int[])null);
   }

   // $FF: synthetic method
   static boolean isSizeMapDefined$(final FlatHashTable $this) {
      return $this.isSizeMapDefined();
   }

   default boolean isSizeMapDefined() {
      return this.sizemap() != null;
   }

   // $FF: synthetic method
   static boolean alwaysInitSizeMap$(final FlatHashTable $this) {
      return $this.alwaysInitSizeMap();
   }

   default boolean alwaysInitSizeMap() {
      return false;
   }

   // $FF: synthetic method
   static int index$(final FlatHashTable $this, final int hcode) {
      return $this.index(hcode);
   }

   default int index(final int hcode) {
      int improved = this.improve(hcode, this.seedvalue());
      int ones = this.table().length - 1;
      return improved >>> 32 - Integer.bitCount(ones) & ones;
   }

   // $FF: synthetic method
   static void clearTable$(final FlatHashTable $this) {
      $this.clearTable();
   }

   default void clearTable() {
      for(int i = this.table().length - 1; i >= 0; --i) {
         this.table()[i] = null;
      }

      this.tableSize_$eq(0);
      this.nnSizeMapReset(this.table().length);
   }

   // $FF: synthetic method
   static Contents hashTableContents$(final FlatHashTable $this) {
      return $this.hashTableContents();
   }

   default Contents hashTableContents() {
      return new Contents(this._loadFactor(), this.table(), this.tableSize(), this.threshold(), this.seedvalue(), this.sizemap());
   }

   // $FF: synthetic method
   static void initWithContents$(final FlatHashTable $this, final Contents c) {
      $this.initWithContents(c);
   }

   default void initWithContents(final Contents c) {
      if (c != null) {
         this._loadFactor_$eq(c.loadFactor());
         this.table_$eq(c.table());
         this.tableSize_$eq(c.tableSize());
         this.threshold_$eq(c.threshold());
         this.seedvalue_$eq(c.seedvalue());
         this.sizemap_$eq(c.sizemap());
      }

      if (this.alwaysInitSizeMap() && this.sizemap() == null) {
         this.sizeMapInitAndRebuild();
      }
   }

   // $FF: synthetic method
   static void $anonfun$serializeTo$1(final ObjectOutputStream out$1, final Object x$1) {
      out$1.writeObject(x$1);
   }

   private boolean precedes$1(final int i, final int j) {
      int d = this.table().length >> 1;
      if (i <= j) {
         return j - i < d;
      } else {
         return i - j > d;
      }
   }

   static void $init$(final FlatHashTable $this) {
      $this._loadFactor_$eq(FlatHashTable$.MODULE$.defaultLoadFactor());
      $this.table_$eq(new Object[$this.initialCapacity()]);
      $this.tableSize_$eq(0);
      $this.threshold_$eq(FlatHashTable$.MODULE$.newThreshold($this._loadFactor(), $this.initialCapacity()));
      $this.sizemap_$eq((int[])null);
      $this.seedvalue_$eq($this.tableSizeSeed());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private static class NullSentinel$ {
      public static final NullSentinel$ MODULE$ = new NullSentinel$();

      public int hashCode() {
         return 0;
      }

      public String toString() {
         return "NullSentinel";
      }

      public NullSentinel$() {
      }
   }

   public static class Contents {
      private final int loadFactor;
      private final Object[] table;
      private final int tableSize;
      private final int threshold;
      private final int seedvalue;
      private final int[] sizemap;

      public int loadFactor() {
         return this.loadFactor;
      }

      public Object[] table() {
         return this.table;
      }

      public int tableSize() {
         return this.tableSize;
      }

      public int threshold() {
         return this.threshold;
      }

      public int seedvalue() {
         return this.seedvalue;
      }

      public int[] sizemap() {
         return this.sizemap;
      }

      public Contents(final int loadFactor, final Object[] table, final int tableSize, final int threshold, final int seedvalue, final int[] sizemap) {
         this.loadFactor = loadFactor;
         this.table = table;
         this.tableSize = tableSize;
         this.threshold = threshold;
         this.seedvalue = seedvalue;
         this.sizemap = sizemap;
      }
   }

   public interface HashUtils {
      // $FF: synthetic method
      static int sizeMapBucketBitSize$(final HashUtils $this) {
         return $this.sizeMapBucketBitSize();
      }

      default int sizeMapBucketBitSize() {
         return 5;
      }

      // $FF: synthetic method
      static int sizeMapBucketSize$(final HashUtils $this) {
         return $this.sizeMapBucketSize();
      }

      default int sizeMapBucketSize() {
         return 1 << this.sizeMapBucketBitSize();
      }

      // $FF: synthetic method
      static int improve$(final HashUtils $this, final int hcode, final int seed) {
         return $this.improve(hcode, seed);
      }

      default int improve(final int hcode, final int seed) {
         return Integer.rotateRight(scala.util.hashing.package..MODULE$.byteswap32(hcode), seed);
      }

      // $FF: synthetic method
      static Object elemToEntry$(final HashUtils $this, final Object elem) {
         return $this.elemToEntry(elem);
      }

      default Object elemToEntry(final Object elem) {
         return elem == null ? FlatHashTable.NullSentinel$.MODULE$ : elem;
      }

      // $FF: synthetic method
      static Object entryToElem$(final HashUtils $this, final Object entry) {
         return $this.entryToElem(entry);
      }

      default Object entryToElem(final Object entry) {
         return FlatHashTable.NullSentinel$.MODULE$ == entry ? null : entry;
      }

      static void $init$(final HashUtils $this) {
      }
   }
}

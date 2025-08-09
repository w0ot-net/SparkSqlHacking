package scala.collection.parallel.mutable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.mutable.HashEntry;
import scala.collection.mutable.HashTable;
import scala.collection.mutable.UnrolledBuffer;
import scala.collection.mutable.HashTable.HashUtils;
import scala.collection.parallel.BucketCombiner;
import scala.collection.parallel.Task;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;
import scala.runtime.Nothing;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005EhA\u0002\u0017.\u0003\u0003iS\u0007\u0003\u0005p\u0001\t\u0015\r\u0011\"\u0003q\u0011!!\bA!A!\u0002\u0013\t\b\"B;\u0001\t\u00031\bb\u0002=\u0001\u0005\u0004%I\u0001\u001d\u0005\u0007s\u0002\u0001\u000b\u0011B9\t\u000fi\u0004!\u0019!C\u0005a\"11\u0010\u0001Q\u0001\nEDQ\u0001 \u0001\u0005\u0002uDq!a\u0001\u0001\t\u0003\t)A\u0002\u0004\u0002\b\u0001\u0001\u0011\u0011\u0002\u0005\u000b\u00033Q!\u0011!Q\u0001\n\u0005m\u0001BCA\u0019\u0015\t\u0005\t\u0015!\u0003\u00024!I\u00111\u0010\u0006\u0003\u0002\u0003\u0006I!\u001d\u0005\n\u0003{R!\u0011!Q\u0001\nEDa!\u001e\u0006\u0005\u0002\u0005}\u0004\u0002CA\u0002\u0015\u0001\u0007I\u0011\u00019\t\u0013\u0005%%\u00021A\u0005\u0002\u0005-\u0005bBAI\u0015\u0001\u0006K!\u001d\u0005\b\u0003'SA\u0011AAK\u0011\u001d\t\tK\u0003C\u0005\u0003GCq!!,\u000b\t\u0003\ty\u000bC\u0004\u0002>*!\t%a0\t\u000f\u0005\u0015'\u0002\"\u0001\u0002H\u001a9\u0011Q\u0007\u0001\u0001\u0001\u0005]\u0002\"CA#1\t\u0005\t\u0015!\u0003r\u0011%\t9\u0005\u0007B\u0001B\u0003%\u0011\u000fC\u0005\u0002Ja\u0011\t\u0011)A\u0005c\"1Q\u000f\u0007C\u0001\u0003\u0017Bq!a\u0015\u0019\t\u0003\t)\u0006C\u0004\u0002ba!\t!a\u0019\t\u000f\u0005=\u0004\u0004\"\u0001\u0002r\u001dA\u0011\u0011Z\u0017\t\u0002=\nYMB\u0004-[!\u0005q&!4\t\rU\fC\u0011AAh\u0011%\t\t.\tb\u0001\n\u0003i\u0003\u000fC\u0004\u0002T\u0006\u0002\u000b\u0011B9\t\u0013\u0005U\u0017E1A\u0005\u00025\u0002\bbBAlC\u0001\u0006I!\u001d\u0005\n\u00033\f#\u0019!C\u0001[ADq!a7\"A\u0003%\u0011\u000fC\u0005\u0002^\u0006\u0012\r\u0011\"\u0001.a\"9\u0011q\\\u0011!\u0002\u0013\t\bbBAqC\u0011\u0005\u00111\u001d\u0002\u0013!\u0006\u0014\b*Y:i\u001b\u0006\u00048i\\7cS:,'O\u0003\u0002/_\u00059Q.\u001e;bE2,'B\u0001\u00192\u0003!\u0001\u0018M]1mY\u0016d'B\u0001\u001a4\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002i\u0005)1oY1mCV\u0019a'\u0011'\u0014\u0007\u00019T\r\u0005\u00049smr%\u000bZ\u0007\u0002_%\u0011!h\f\u0002\u000f\u0005V\u001c7.\u001a;D_6\u0014\u0017N\\3s!\u0011aThP&\u000e\u0003MJ!AP\u001a\u0003\rQ+\b\u000f\\33!\t\u0001\u0015\t\u0004\u0001\u0005\u000b\t\u0003!\u0019\u0001#\u0003\u0003-\u001b\u0001!\u0005\u0002F\u0011B\u0011AHR\u0005\u0003\u000fN\u0012qAT8uQ&tw\r\u0005\u0002=\u0013&\u0011!j\r\u0002\u0004\u0003:L\bC\u0001!M\t\u0015i\u0005A1\u0001E\u0005\u00051\u0006\u0003B(Q\u007f-k\u0011!L\u0005\u0003#6\u0012!\u0002U1s\u0011\u0006\u001c\b.T1q!\u0011\u0019\u0016mP&\u000f\u0005Q{fBA+_\u001d\t1VL\u0004\u0002X9:\u0011\u0001lW\u0007\u00023*\u0011!lQ\u0001\u0007yI|w\u000e\u001e \n\u0003QJ!AM\u001a\n\u0005A\n\u0014B\u0001\u00180\u0013\t\u0001W&\u0001\u0006QCJD\u0015m\u001d5NCBL!AY2\u0003\u0019\u0011+g-Y;mi\u0016sGO]=\u000b\u0005\u0001l\u0003\u0003B(\u0001\u007f-\u00032A\u001a7@\u001d\t9\u0017N\u0004\u0002WQ&\u0011a&M\u0005\u0003U.\f\u0011\u0002S1tQR\u000b'\r\\3\u000b\u00059\n\u0014BA7o\u0005%A\u0015m\u001d5Vi&d7O\u0003\u0002kW\u0006yA/\u00192mK2{\u0017\r\u001a$bGR|'/F\u0001r!\ta$/\u0003\u0002tg\t\u0019\u0011J\u001c;\u0002!Q\f'\r\\3M_\u0006$g)Y2u_J\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002eo\")qn\u0001a\u0001c\u0006Qan\u001c8nCN\\G.\u001a8\u0002\u00179|g.\\1tW2,g\u000eI\u0001\ng\u0016,GM^1mk\u0016\f!b]3fIZ\fG.^3!\u0003\u0019\tG\rZ(oKR\u0011ap`\u0007\u0002\u0001!1\u0011\u0011\u0001\u0005A\u0002m\nA!\u001a7f[\u00061!/Z:vYR$\u0012A\u0014\u0002\u000b\r&dGN\u00117pG.\u001c8#\u0002\u0006\u0002\f\u0005E\u0001c\u0001\u001f\u0002\u000e%\u0019\u0011qB\u001a\u0003\r\u0005s\u0017PU3g!\u0019A\u00141C9\u0002\u0018%\u0019\u0011QC\u0018\u0003\tQ\u000b7o\u001b\t\u0003}*\tqAY;dW\u0016$8\u000fE\u0003=\u0003;\t\t#C\u0002\u0002 M\u0012Q!\u0011:sCf\u0004R!a\t\u0002,IsA!!\n\u0002(5\t1.C\u0002\u0002*-\fa\"\u00168s_2dW\r\u001a\"vM\u001a,'/\u0003\u0003\u0002.\u0005=\"\u0001C+oe>dG.\u001a3\u000b\u0007\u0005%2.A\u0003uC\ndW\r\u0005\u0002\u007f1\ty\u0011\t\u001a3j]\u001eD\u0015m\u001d5UC\ndWmE\u0004\u0019\u0003\u0017\tI$a\u0010\u0011\u000f\u0005\u0015\u00121H L%&\u0019\u0011QH6\u0003\u0013!\u000b7\u000f\u001b+bE2,\u0007CB(\u0002B}Z%+C\u0002\u0002D5\u0012AbV5uQ\u000e{g\u000e^3oiN\f\u0001B\\;nK2,Wn]\u0001\u0003Y\u001a\f!bX:fK\u00124\u0018\r\\;f)!\t\u0019$!\u0014\u0002P\u0005E\u0003BBA#9\u0001\u0007\u0011\u000f\u0003\u0004\u0002Hq\u0001\r!\u001d\u0005\u0007\u0003\u0013b\u0002\u0019A9\u0002\u000fM,GoU5{KR!\u0011qKA/!\ra\u0014\u0011L\u0005\u0004\u00037\u001a$\u0001B+oSRDa!a\u0018\u001e\u0001\u0004\t\u0018AA:{\u0003-Ign]3si\u0016sGO]=\u0015\t\u0005\u0015\u00141\u000e\t\u0004y\u0005\u001d\u0014bAA5g\t9!i\\8mK\u0006t\u0007BBA7=\u0001\u0007!+A\u0001f\u00039\u0019'/Z1uK:+w/\u00128uef$R!RA:\u0003oBa!!\u001e \u0001\u0004y\u0014aA6fs\"1\u0011\u0011P\u0010A\u0002-\u000b\u0011\u0001_\u0001\u0007_\u001a47/\u001a;\u0002\u000f!|w/\\1osRQ\u0011qCAA\u0003\u0007\u000b))a\"\t\u000f\u0005eq\u00021\u0001\u0002\u001c!9\u0011\u0011G\bA\u0002\u0005M\u0002BBA>\u001f\u0001\u0007\u0011\u000f\u0003\u0004\u0002~=\u0001\r!]\u0001\u000be\u0016\u001cX\u000f\u001c;`I\u0015\fH\u0003BA,\u0003\u001bC\u0001\"a$\u0012\u0003\u0003\u0005\r!]\u0001\u0004q\u0012\n\u0014a\u0002:fgVdG\u000fI\u0001\u0005Y\u0016\fg\r\u0006\u0003\u0002X\u0005]\u0005bBAM'\u0001\u0007\u00111T\u0001\u0005aJ,g\u000f\u0005\u0003=\u0003;\u000b\u0018bAAPg\t1q\n\u001d;j_:\f\u0011BZ5mY\ncwnY6\u0015\u000bE\f)+!+\t\r\u0005\u001dF\u00031\u0001r\u0003\u0015\u0011Gn\\2l\u0011\u001d\tY\u000b\u0006a\u0001\u0003C\tQ!\u001a7f[N\fQa\u001d9mSR,\"!!-\u0011\r\u0005M\u0016\u0011XA\f\u001b\t\t)LC\u0002\u00028F\n\u0011\"[7nkR\f'\r\\3\n\t\u0005m\u0016Q\u0017\u0002\u0005\u0019&\u001cH/A\u0003nKJ<W\r\u0006\u0003\u0002X\u0005\u0005\u0007bBAb-\u0001\u0007\u0011qC\u0001\u0005i\"\fG/\u0001\ntQ>,H\u000eZ*qY&$h)\u001e:uQ\u0016\u0014XCAA3\u0003I\u0001\u0016M\u001d%bg\"l\u0015\r]\"p[\nLg.\u001a:\u0011\u0005=\u000b3cA\u0011\u0002\fQ\u0011\u00111Z\u0001\u0011I&\u001c8M]5nS:\fg\u000e\u001e2jiN\f\u0011\u0003Z5tGJLW.\u001b8b]R\u0014\u0017\u000e^:!\u0003%qW/\u001c2m_\u000e\\7/\u0001\u0006ok6\u0014Gn\\2lg\u0002\n\u0001\u0003Z5tGJLW.\u001b8b]Rl\u0017m]6\u0002#\u0011L7o\u0019:j[&t\u0017M\u001c;nCN\\\u0007%A\u0007o_:l\u0017m]6mK:<G\u000f[\u0001\u000f]>tW.Y:lY\u0016tw\r\u001e5!\u0003\u0015\t\u0007\u000f\u001d7z+\u0019\t)/a;\u0002pV\u0011\u0011q\u001d\t\u0007\u001f\u0002\tI/!<\u0011\u0007\u0001\u000bY\u000fB\u0003CW\t\u0007A\tE\u0002A\u0003_$Q!T\u0016C\u0002\u0011\u0003"
)
public abstract class ParHashMapCombiner extends BucketCombiner implements HashTable.HashUtils {
   private final int tableLoadFactor;
   private final int nonmasklen;
   private final int seedvalue;

   public static ParHashMapCombiner apply() {
      return ParHashMapCombiner$.MODULE$.apply();
   }

   public final int sizeMapBucketBitSize() {
      return HashUtils.sizeMapBucketBitSize$(this);
   }

   public final int sizeMapBucketSize() {
      return HashUtils.sizeMapBucketSize$(this);
   }

   public int elemHashCode(final Object key) {
      return HashUtils.elemHashCode$(this, key);
   }

   public final int improve(final int hcode, final int seed) {
      return HashUtils.improve$(this, hcode, seed);
   }

   private int tableLoadFactor() {
      return this.tableLoadFactor;
   }

   private int nonmasklen() {
      return this.nonmasklen;
   }

   private int seedvalue() {
      return this.seedvalue;
   }

   public ParHashMapCombiner addOne(final Tuple2 elem) {
      this.sz_$eq(this.sz() + 1);
      int hc = this.improve(this.elemHashCode(elem._1()), this.seedvalue());
      int pos = hc >>> this.nonmasklen();
      if (this.buckets()[pos] == null) {
         this.buckets()[pos] = new UnrolledBuffer(.MODULE$.apply(ParHashMap.DefaultEntry.class));
      }

      this.buckets()[pos].$plus$eq(new ParHashMap.DefaultEntry(elem._1(), elem._2()));
      return this;
   }

   public ParHashMap result() {
      if (this.size() >= ParHashMapCombiner$.MODULE$.numblocks() * this.sizeMapBucketSize()) {
         AddingHashTable table = new AddingHashTable(this.size(), this.tableLoadFactor(), this.seedvalue());
         UnrolledBuffer.Unrolled[] bucks = (UnrolledBuffer.Unrolled[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.buckets()), (b) -> b != null ? b.headPtr() : null, .MODULE$.apply(UnrolledBuffer.Unrolled.class));
         int insertcount = BoxesRunTime.unboxToInt(this.combinerTaskSupport().executeAndWaitResult(new FillBlocks(bucks, table, 0, bucks.length)));
         table.setSize(insertcount);
         ParHashTable.Contents c = table.hashTableContents();
         return new ParHashMap(c);
      } else {
         LazyRef newTable$module = new LazyRef();

         for(int i = 0; i < ParHashMapCombiner$.MODULE$.numblocks(); ++i) {
            if (this.buckets()[i] != null) {
               this.buckets()[i].foreach((elem) -> {
                  $anonfun$result$2(this, newTable$module, elem);
                  return BoxedUnit.UNIT;
               });
            }
         }

         return new ParHashMap(this.newTable$2(newTable$module).hashTableContents());
      }
   }

   // $FF: synthetic method
   private static final newTable$1$ newTable$lzycompute$1(final LazyRef newTable$module$1) {
      synchronized(newTable$module$1){}

      newTable$1$ var2;
      try {
         class newTable$1$ implements HashTable, WithContents {
            private int _loadFactor;
            private HashEntry[] table;
            private int tableSize;
            private int threshold;
            private int[] sizemap;
            private int seedvalue;

            public void initWithContents(final ParHashTable.Contents c) {
               WithContents.initWithContents$(this, c);
            }

            public ParHashTable.Contents hashTableContents() {
               return WithContents.hashTableContents$(this);
            }

            public final int size() {
               return HashTable.size$(this);
            }

            public int tableSizeSeed() {
               return HashTable.tableSizeSeed$(this);
            }

            public int initialSize() {
               return HashTable.initialSize$(this);
            }

            public void init(final ObjectInputStream in, final Function0 readEntry) {
               HashTable.init$(this, in, readEntry);
            }

            public void serializeTo(final ObjectOutputStream out, final Function1 writeEntry) {
               HashTable.serializeTo$(this, out, writeEntry);
            }

            public final HashEntry findEntry(final Object key) {
               return HashTable.findEntry$(this, key);
            }

            public final HashEntry findEntry0(final Object key, final int h) {
               return HashTable.findEntry0$(this, key, h);
            }

            public final void addEntry(final HashEntry e) {
               HashTable.addEntry$(this, e);
            }

            public final void addEntry0(final HashEntry e, final int h) {
               HashTable.addEntry0$(this, e, h);
            }

            public HashEntry findOrAddEntry(final Object key, final Object value) {
               return HashTable.findOrAddEntry$(this, key, value);
            }

            public final HashEntry removeEntry(final Object key) {
               return HashTable.removeEntry$(this, key);
            }

            public final HashEntry removeEntry0(final Object key, final int h) {
               return HashTable.removeEntry0$(this, key, h);
            }

            public Iterator entriesIterator() {
               return HashTable.entriesIterator$(this);
            }

            public void foreachEntry(final Function1 f) {
               HashTable.foreachEntry$(this, f);
            }

            public void clearTable() {
               HashTable.clearTable$(this);
            }

            public final void nnSizeMapAdd(final int h) {
               HashTable.nnSizeMapAdd$(this, h);
            }

            public final void nnSizeMapRemove(final int h) {
               HashTable.nnSizeMapRemove$(this, h);
            }

            public final void nnSizeMapReset(final int tableLength) {
               HashTable.nnSizeMapReset$(this, tableLength);
            }

            public final int totalSizeMapBuckets() {
               return HashTable.totalSizeMapBuckets$(this);
            }

            public final int calcSizeMapSize(final int tableLength) {
               return HashTable.calcSizeMapSize$(this, tableLength);
            }

            public void sizeMapInit(final int tableLength) {
               HashTable.sizeMapInit$(this, tableLength);
            }

            public final void sizeMapInitAndRebuild() {
               HashTable.sizeMapInitAndRebuild$(this);
            }

            public void printSizeMap() {
               HashTable.printSizeMap$(this);
            }

            public final void sizeMapDisable() {
               HashTable.sizeMapDisable$(this);
            }

            public final boolean isSizeMapDefined() {
               return HashTable.isSizeMapDefined$(this);
            }

            public boolean alwaysInitSizeMap() {
               return HashTable.alwaysInitSizeMap$(this);
            }

            public boolean elemEquals(final Object key1, final Object key2) {
               return HashTable.elemEquals$(this, key1, key2);
            }

            public final int index(final int hcode) {
               return HashTable.index$(this, hcode);
            }

            public final int sizeMapBucketBitSize() {
               return HashUtils.sizeMapBucketBitSize$(this);
            }

            public final int sizeMapBucketSize() {
               return HashUtils.sizeMapBucketSize$(this);
            }

            public int elemHashCode(final Object key) {
               return HashUtils.elemHashCode$(this, key);
            }

            public final int improve(final int hcode, final int seed) {
               return HashUtils.improve$(this, hcode, seed);
            }

            public int _loadFactor() {
               return this._loadFactor;
            }

            public void _loadFactor_$eq(final int x$1) {
               this._loadFactor = x$1;
            }

            public HashEntry[] table() {
               return this.table;
            }

            public void table_$eq(final HashEntry[] x$1) {
               this.table = x$1;
            }

            public int tableSize() {
               return this.tableSize;
            }

            public void tableSize_$eq(final int x$1) {
               this.tableSize = x$1;
            }

            public int threshold() {
               return this.threshold;
            }

            public void threshold_$eq(final int x$1) {
               this.threshold = x$1;
            }

            public int[] sizemap() {
               return this.sizemap;
            }

            public void sizemap_$eq(final int[] x$1) {
               this.sizemap = x$1;
            }

            public int seedvalue() {
               return this.seedvalue;
            }

            public void seedvalue_$eq(final int x$1) {
               this.seedvalue = x$1;
            }

            public void insertEntry(final ParHashMap.DefaultEntry e) {
               HashTable.findOrAddEntry$(this, e.key(), e);
            }

            public ParHashMap.DefaultEntry createNewEntry(final Object key, final ParHashMap.DefaultEntry entry) {
               return entry;
            }

            public newTable$1$() {
               HashUtils.$init$(this);
               HashTable.$init$(this);
               WithContents.$init$(this);
               this.sizeMapInit(this.table().length);
            }
         }

         var2 = newTable$module$1.initialized() ? (newTable$1$)newTable$module$1.value() : (newTable$1$)newTable$module$1.initialize(new newTable$1$());
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   private final newTable$1$ newTable$2(final LazyRef newTable$module$1) {
      return newTable$module$1.initialized() ? (newTable$1$)newTable$module$1.value() : newTable$lzycompute$1(newTable$module$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$result$2(final ParHashMapCombiner $this, final LazyRef newTable$module$1, final ParHashMap.DefaultEntry elem) {
      $this.newTable$2(newTable$module$1).insertEntry(elem);
   }

   public ParHashMapCombiner(final int tableLoadFactor) {
      super(ParHashMapCombiner$.MODULE$.numblocks());
      this.tableLoadFactor = tableLoadFactor;
      HashUtils.$init$(this);
      this.nonmasklen = ParHashMapCombiner$.MODULE$.nonmasklength();
      this.seedvalue = 27;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class AddingHashTable implements HashTable, WithContents {
      private int _loadFactor;
      private HashEntry[] table;
      private int tableSize;
      private int threshold;
      private int[] sizemap;
      private int seedvalue;
      // $FF: synthetic field
      public final ParHashMapCombiner $outer;

      public void initWithContents(final ParHashTable.Contents c) {
         WithContents.initWithContents$(this, c);
      }

      public ParHashTable.Contents hashTableContents() {
         return WithContents.hashTableContents$(this);
      }

      public final int size() {
         return HashTable.size$(this);
      }

      public int tableSizeSeed() {
         return HashTable.tableSizeSeed$(this);
      }

      public int initialSize() {
         return HashTable.initialSize$(this);
      }

      public void init(final ObjectInputStream in, final Function0 readEntry) {
         HashTable.init$(this, in, readEntry);
      }

      public void serializeTo(final ObjectOutputStream out, final Function1 writeEntry) {
         HashTable.serializeTo$(this, out, writeEntry);
      }

      public final HashEntry findEntry(final Object key) {
         return HashTable.findEntry$(this, key);
      }

      public final HashEntry findEntry0(final Object key, final int h) {
         return HashTable.findEntry0$(this, key, h);
      }

      public final void addEntry(final HashEntry e) {
         HashTable.addEntry$(this, e);
      }

      public final void addEntry0(final HashEntry e, final int h) {
         HashTable.addEntry0$(this, e, h);
      }

      public HashEntry findOrAddEntry(final Object key, final Object value) {
         return HashTable.findOrAddEntry$(this, key, value);
      }

      public final HashEntry removeEntry(final Object key) {
         return HashTable.removeEntry$(this, key);
      }

      public final HashEntry removeEntry0(final Object key, final int h) {
         return HashTable.removeEntry0$(this, key, h);
      }

      public Iterator entriesIterator() {
         return HashTable.entriesIterator$(this);
      }

      public void foreachEntry(final Function1 f) {
         HashTable.foreachEntry$(this, f);
      }

      public void clearTable() {
         HashTable.clearTable$(this);
      }

      public final void nnSizeMapAdd(final int h) {
         HashTable.nnSizeMapAdd$(this, h);
      }

      public final void nnSizeMapRemove(final int h) {
         HashTable.nnSizeMapRemove$(this, h);
      }

      public final void nnSizeMapReset(final int tableLength) {
         HashTable.nnSizeMapReset$(this, tableLength);
      }

      public final int totalSizeMapBuckets() {
         return HashTable.totalSizeMapBuckets$(this);
      }

      public final int calcSizeMapSize(final int tableLength) {
         return HashTable.calcSizeMapSize$(this, tableLength);
      }

      public void sizeMapInit(final int tableLength) {
         HashTable.sizeMapInit$(this, tableLength);
      }

      public final void sizeMapInitAndRebuild() {
         HashTable.sizeMapInitAndRebuild$(this);
      }

      public void printSizeMap() {
         HashTable.printSizeMap$(this);
      }

      public final void sizeMapDisable() {
         HashTable.sizeMapDisable$(this);
      }

      public final boolean isSizeMapDefined() {
         return HashTable.isSizeMapDefined$(this);
      }

      public boolean alwaysInitSizeMap() {
         return HashTable.alwaysInitSizeMap$(this);
      }

      public boolean elemEquals(final Object key1, final Object key2) {
         return HashTable.elemEquals$(this, key1, key2);
      }

      public final int index(final int hcode) {
         return HashTable.index$(this, hcode);
      }

      public final int sizeMapBucketBitSize() {
         return HashUtils.sizeMapBucketBitSize$(this);
      }

      public final int sizeMapBucketSize() {
         return HashUtils.sizeMapBucketSize$(this);
      }

      public int elemHashCode(final Object key) {
         return HashUtils.elemHashCode$(this, key);
      }

      public final int improve(final int hcode, final int seed) {
         return HashUtils.improve$(this, hcode, seed);
      }

      public int _loadFactor() {
         return this._loadFactor;
      }

      public void _loadFactor_$eq(final int x$1) {
         this._loadFactor = x$1;
      }

      public HashEntry[] table() {
         return this.table;
      }

      public void table_$eq(final HashEntry[] x$1) {
         this.table = x$1;
      }

      public int tableSize() {
         return this.tableSize;
      }

      public void tableSize_$eq(final int x$1) {
         this.tableSize = x$1;
      }

      public int threshold() {
         return this.threshold;
      }

      public void threshold_$eq(final int x$1) {
         this.threshold = x$1;
      }

      public int[] sizemap() {
         return this.sizemap;
      }

      public void sizemap_$eq(final int[] x$1) {
         this.sizemap = x$1;
      }

      public int seedvalue() {
         return this.seedvalue;
      }

      public void seedvalue_$eq(final int x$1) {
         this.seedvalue = x$1;
      }

      public void setSize(final int sz) {
         this.tableSize_$eq(sz);
      }

      public boolean insertEntry(final ParHashMap.DefaultEntry e) {
         int h = this.index(this.elemHashCode(e.key()));
         ParHashMap.DefaultEntry olde = (ParHashMap.DefaultEntry)this.table()[h];
         ParHashMap.DefaultEntry ce = olde;

         while(ce != null) {
            if (BoxesRunTime.equals(ce.key(), e.key())) {
               h = -1;
               ce = null;
            } else {
               ce = (ParHashMap.DefaultEntry)ce.next();
            }
         }

         if (h != -1) {
            e.next_$eq((HashEntry)olde);
            this.table()[h] = e;
            this.nnSizeMapAdd(h);
            return true;
         } else {
            return false;
         }
      }

      public Nothing createNewEntry(final Object key, final Object x) {
         return scala.Predef..MODULE$.$qmark$qmark$qmark();
      }

      // $FF: synthetic method
      public ParHashMapCombiner scala$collection$parallel$mutable$ParHashMapCombiner$AddingHashTable$$$outer() {
         return this.$outer;
      }

      public AddingHashTable(final int numelems, final int lf, final int _seedvalue) {
         if (ParHashMapCombiner.this == null) {
            throw null;
         } else {
            this.$outer = ParHashMapCombiner.this;
            super();
            HashUtils.$init$(this);
            HashTable.$init$(this);
            WithContents.$init$(this);
            this._loadFactor_$eq(lf);
            this.table_$eq(new HashEntry[scala.collection.mutable.HashTable..MODULE$.capacity(scala.collection.mutable.HashTable..MODULE$.sizeForThreshold(this._loadFactor(), numelems))]);
            this.tableSize_$eq(0);
            this.seedvalue_$eq(_seedvalue);
            this.threshold_$eq(scala.collection.mutable.HashTable..MODULE$.newThreshold(this._loadFactor(), this.table().length));
            this.sizeMapInit(this.table().length);
         }
      }
   }

   public class FillBlocks implements Task {
      private final UnrolledBuffer.Unrolled[] buckets;
      private final AddingHashTable table;
      private final int offset;
      private final int howmany;
      private int result;
      private volatile Throwable throwable;
      // $FF: synthetic field
      public final ParHashMapCombiner $outer;

      public Object repr() {
         return Task.repr$(this);
      }

      public void forwardThrowable() {
         Task.forwardThrowable$(this);
      }

      public void tryLeaf(final Option lastres) {
         Task.tryLeaf$(this, lastres);
      }

      public void tryMerge(final Object t) {
         Task.tryMerge$(this, t);
      }

      public void mergeThrowables(final Task that) {
         Task.mergeThrowables$(this, that);
      }

      public void signalAbort() {
         Task.signalAbort$(this);
      }

      public Throwable throwable() {
         return this.throwable;
      }

      public void throwable_$eq(final Throwable x$1) {
         this.throwable = x$1;
      }

      public int result() {
         return this.result;
      }

      public void result_$eq(final int x$1) {
         this.result = x$1;
      }

      public void leaf(final Option prev) {
         int i = this.offset;
         int until = this.offset + this.howmany;
         this.result_$eq(0);

         while(i < until) {
            this.result_$eq(this.result() + this.fillBlock(i, this.buckets[i]));
            ++i;
         }

      }

      private int fillBlock(final int block, final UnrolledBuffer.Unrolled elems) {
         int insertcount = 0;
         UnrolledBuffer.Unrolled unrolled = elems;
         int i = 0;

         for(AddingHashTable t = this.table; unrolled != null; unrolled = unrolled.next()) {
            ParHashMap.DefaultEntry[] chunkarr = (ParHashMap.DefaultEntry[])unrolled.array();

            for(int chunksz = unrolled.size(); i < chunksz; ++i) {
               ParHashMap.DefaultEntry elem = chunkarr[i];
               if (t.insertEntry(elem)) {
                  ++insertcount;
               }
            }

            i = 0;
         }

         return insertcount;
      }

      public List split() {
         int fp = this.howmany / 2;
         return new scala.collection.immutable..colon.colon(this.scala$collection$parallel$mutable$ParHashMapCombiner$FillBlocks$$$outer().new FillBlocks(this.buckets, this.table, this.offset, fp), new scala.collection.immutable..colon.colon(this.scala$collection$parallel$mutable$ParHashMapCombiner$FillBlocks$$$outer().new FillBlocks(this.buckets, this.table, this.offset + fp, this.howmany - fp), scala.collection.immutable.Nil..MODULE$));
      }

      public void merge(final FillBlocks that) {
         this.result_$eq(this.result() + that.result());
      }

      public boolean shouldSplitFurther() {
         return this.howmany > scala.collection.parallel.package$.MODULE$.thresholdFromSize(ParHashMapCombiner$.MODULE$.numblocks(), this.scala$collection$parallel$mutable$ParHashMapCombiner$FillBlocks$$$outer().combinerTaskSupport().parallelismLevel());
      }

      // $FF: synthetic method
      public ParHashMapCombiner scala$collection$parallel$mutable$ParHashMapCombiner$FillBlocks$$$outer() {
         return this.$outer;
      }

      public FillBlocks(final UnrolledBuffer.Unrolled[] buckets, final AddingHashTable table, final int offset, final int howmany) {
         this.buckets = buckets;
         this.table = table;
         this.offset = offset;
         this.howmany = howmany;
         if (ParHashMapCombiner.this == null) {
            throw null;
         } else {
            this.$outer = ParHashMapCombiner.this;
            super();
            Task.$init$(this);
            this.result = Integer.MIN_VALUE;
         }
      }
   }
}

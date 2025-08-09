package scala.collection.parallel.mutable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.mutable.FlatHashTable;
import scala.collection.mutable.FlatHashTable$;
import scala.collection.mutable.UnrolledBuffer;
import scala.collection.parallel.BucketCombiner;
import scala.collection.parallel.Task;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;

@ScalaSignature(
   bytes = "\u0006\u0005\t}aA\u0002\u001c8\u0003\u00039t\b\u0003\u0005l\u0001\t\u0015\r\u0011\"\u0003m\u0011!\u0001\bA!A!\u0002\u0013i\u0007\"B9\u0001\t\u0003\u0011\bb\u0002;\u0001\u0005\u0004%I\u0001\u001c\u0005\u0007k\u0002\u0001\u000b\u0011B7\t\u000fY\u0004!\u0019!C\u0005Y\"1q\u000f\u0001Q\u0001\n5DQ\u0001\u001f\u0001\u0005\u0002eDQ! \u0001\u0005\u0002yDaa \u0001\u0005\n\u0005\u0005\u0001bBA\u0007\u0001\u0011%\u0011\u0011\u0001\u0004\u0007\u0003\u001f\u0001\u0001!!\u0005\t\u0013\u0005eAB!A!\u0002\u0013i\u0007\"CA\u000e\u0019\t\u0005\t\u0015!\u0003n\u0011%\ti\u0002\u0004B\u0001B\u0003%Q\u000e\u0003\u0004r\u0019\u0011\u0005\u0011q\u0004\u0005\b\u0003SaA\u0011IA\u0016\u0011\u0019\ti\u0004\u0004C\u0001Y\"9\u0011q\b\u0007\u0005\u0002\u0005\u0005\u0003bBA'\u0019\u0011\u0005\u0011q\n\u0004\u0007\u0003;\u0002\u0001!a\u0018\t\u0015\u0005UTC!A!\u0002\u0013\t9\b\u0003\u0006\u0002~U\u0011\t\u0011)A\u0005\u0003CA\u0011\"a \u0016\u0005\u000b\u0007I\u0011\u00017\t\u0013\u0005\u0005UC!A!\u0002\u0013i\u0007\"CAB+\t\u0015\r\u0011\"\u0001m\u0011%\t))\u0006B\u0001B\u0003%Q\u000e\u0003\u0004r+\u0011\u0005\u0011q\u0011\u0005\t{V\u0001\r\u0011\"\u0001\u0002\u0012\"I\u00111S\u000bA\u0002\u0013\u0005\u0011Q\u0013\u0005\t\u00037+\u0002\u0015)\u0003\u0002h!9\u0011QT\u000b\u0005\u0002\u0005}\u0005\u0002CAV+\t\u0007I\u0011\u00027\t\u000f\u00055V\u0003)A\u0005[\"9\u0011qV\u000b\u0005\n\u0005E\u0006bBA\\+\u0011%\u0011\u0011\u0018\u0005\b\u0003{+B\u0011BA`\u0011\u001d\tY-\u0006C\u0005\u0003\u001bDq!!7\u0016\t\u0003\tY\u000eC\u0004\u0002jV!\t%a;\t\u000f\u0005EX\u0003\"\u0001\u0002t\u001eA\u00111`\u001c\t\u0002e\niPB\u00047o!\u0005\u0011(a@\t\rE\\C\u0011\u0001B\u0001\u0011%\u0011\u0019a\u000bb\u0001\n\u00039D\u000eC\u0004\u0003\u0006-\u0002\u000b\u0011B7\t\u0013\t\u001d1F1A\u0005\u0002]b\u0007b\u0002B\u0005W\u0001\u0006I!\u001c\u0005\n\u0005\u0017Y#\u0019!C\u0001o1DqA!\u0004,A\u0003%Q\u000eC\u0005\u0003\u0010-\u0012\r\u0011\"\u00018Y\"9!\u0011C\u0016!\u0002\u0013i\u0007b\u0002B\nW\u0011\u0005!Q\u0003\u0002\u0013!\u0006\u0014\b*Y:i'\u0016$8i\\7cS:,'O\u0003\u00029s\u00059Q.\u001e;bE2,'B\u0001\u001e<\u0003!\u0001\u0018M]1mY\u0016d'B\u0001\u001f>\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002}\u0005)1oY1mCV\u0011\u0001iR\n\u0004\u0001\u0005S\u0006C\u0002\"D\u000bJ3\u0016,D\u0001:\u0013\t!\u0015H\u0001\bCk\u000e\\W\r^\"p[\nLg.\u001a:\u0011\u0005\u0019;E\u0002\u0001\u0003\u0006\u0011\u0002\u0011\rA\u0013\u0002\u0002)\u000e\u0001\u0011CA&P!\taU*D\u0001>\u0013\tqUHA\u0004O_RD\u0017N\\4\u0011\u00051\u0003\u0016BA)>\u0005\r\te.\u001f\t\u0004'R+U\"A\u001c\n\u0005U;$A\u0003)be\"\u000b7\u000f[*fiB\u0011AjV\u0005\u00031v\u0012a!\u00118z%\u00164\u0007cA*\u0001\u000bB\u00191\f[#\u000f\u0005q+gBA/e\u001d\tq6M\u0004\u0002`E6\t\u0001M\u0003\u0002b\u0013\u00061AH]8pizJ\u0011AP\u0005\u0003yuJ!\u0001O\u001e\n\u0005\u0019<\u0017!\u0004$mCRD\u0015m\u001d5UC\ndWM\u0003\u00029w%\u0011\u0011N\u001b\u0002\n\u0011\u0006\u001c\b.\u0016;jYNT!AZ4\u0002\u001fQ\f'\r\\3M_\u0006$g)Y2u_J,\u0012!\u001c\t\u0003\u0019:L!a\\\u001f\u0003\u0007%sG/\u0001\tuC\ndW\rT8bI\u001a\u000b7\r^8sA\u00051A(\u001b8jiz\"\"!W:\t\u000b-\u001c\u0001\u0019A7\u0002\u00159|g.\\1tW2,g.A\u0006o_:l\u0017m]6mK:\u0004\u0013!C:fK\u00124\u0018\r\\;f\u0003)\u0019X-\u001a3wC2,X\rI\u0001\u0007C\u0012$wJ\\3\u0015\u0005i\\X\"\u0001\u0001\t\u000bqD\u0001\u0019A#\u0002\t\u0015dW-\\\u0001\u0007e\u0016\u001cX\u000f\u001c;\u0015\u0003I\u000b1\u0002]1s!>\u0004X\u000f\\1uKV\u0011\u00111\u0001\t\u0006\u0003\u000b\tI!\u0012\b\u0004\u0003\u000f)W\"A4\n\u0007\u0005-!N\u0001\u0005D_:$XM\u001c;t\u0003-\u0019X-\u001d)paVd\u0017\r^3\u0003'\u0005#G-\u001b8h\r2\fG\u000fS1tQR\u000b'\r\\3\u0014\t11\u00161\u0003\t\u0006\u0003\u000f\t)\"R\u0005\u0004\u0003/9'!\u0004$mCRD\u0015m\u001d5UC\ndW-\u0001\u0005ok6,G.Z7t\u0003\tag-A\u0006j]N,W\r\u001a<bYV,G\u0003CA\u0011\u0003G\t)#a\n\u0011\u0005id\u0001BBA\r!\u0001\u0007Q\u000e\u0003\u0004\u0002\u001cA\u0001\r!\u001c\u0005\u0007\u0003;\u0001\u0002\u0019A7\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!\f\u0011\t\u0005=\u0012q\u0007\b\u0005\u0003c\t\u0019\u0004\u0005\u0002`{%\u0019\u0011QG\u001f\u0002\rA\u0013X\rZ3g\u0013\u0011\tI$a\u000f\u0003\rM#(/\u001b8h\u0015\r\t)$P\u0001\fi\u0006\u0014G.\u001a'f]\u001e$\b.A\u0004tKR\u001c\u0016N_3\u0015\t\u0005\r\u0013\u0011\n\t\u0004\u0019\u0006\u0015\u0013bAA${\t!QK\\5u\u0011\u0019\tYe\u0005a\u0001[\u0006\u00111O_\u0001\fS:\u001cXM\u001d;F]R\u0014\u0018\u0010F\u0004n\u0003#\n)&!\u0017\t\r\u0005MC\u00031\u0001n\u0003!Ign]3si\u0006#\bBBA,)\u0001\u0007Q.A\u0006d_6,7OQ3g_J,\u0007BBA.)\u0001\u0007a+\u0001\u0005oK^,e\u000e\u001e:z\u0005)1\u0015\u000e\u001c7CY>\u001c7n]\n\u0005+Y\u000b\t\u0007E\u0004C\u0003G\n9'a\u001d\n\u0007\u0005\u0015\u0014H\u0001\u0003UCN\\\u0007C\u0002'\u0002j5\fi'C\u0002\u0002lu\u0012a\u0001V;qY\u0016\u0014\u0004#BA\u0004\u0003_2\u0016bAA9O\nqQK\u001c:pY2,GMQ;gM\u0016\u0014\bC\u0001>\u0016\u0003\u001d\u0011WoY6fiN\u0004R\u0001TA=\u0003[J1!a\u001f>\u0005\u0015\t%O]1z\u0003\u0015!\u0018M\u00197f\u0003\u0019ygMZ:fi\u00069qN\u001a4tKR\u0004\u0013a\u00025po6\fg._\u0001\tQ><X.\u00198zAQQ\u00111OAE\u0003\u0017\u000bi)a$\t\u000f\u0005UD\u00041\u0001\u0002x!9\u0011Q\u0010\u000fA\u0002\u0005\u0005\u0002BBA@9\u0001\u0007Q\u000e\u0003\u0004\u0002\u0004r\u0001\r!\\\u000b\u0003\u0003O\n!B]3tk2$x\fJ3r)\u0011\t\u0019%a&\t\u0013\u0005ee$!AA\u0002\u0005\u001d\u0014a\u0001=%c\u00059!/Z:vYR\u0004\u0013\u0001\u00027fC\u001a$B!a\u0011\u0002\"\"9\u00111\u0015\u0011A\u0002\u0005\u0015\u0016\u0001\u00029sKZ\u0004R\u0001TAT\u0003OJ1!!+>\u0005\u0019y\u0005\u000f^5p]\u0006I!\r\\8dWNL'0Z\u0001\u000bE2|7m[:ju\u0016\u0004\u0013A\u00032m_\u000e\\7\u000b^1siR\u0019Q.a-\t\r\u0005U6\u00051\u0001n\u0003\u0015\u0011Gn\\2l\u00039qW\r\u001f;CY>\u001c7n\u0015;beR$2!\\A^\u0011\u0019\t)\f\na\u0001[\u0006Ia-\u001b7m\u00052|7m\u001b\u000b\t\u0003O\n\t-a1\u0002H\"1\u0011QW\u0013A\u00025Dq!!2&\u0001\u0004\ti'A\u0003fY\u0016l7\u000fC\u0004\u0002J\u0016\u0002\r!!\u001c\u0002\u00131,g\r^8wKJ\u001c\u0018!C5og\u0016\u0014H/\u00117m)!\t9'a4\u0002T\u0006]\u0007BBAiM\u0001\u0007Q.A\u0003biB{7\u000f\u0003\u0004\u0002V\u001a\u0002\r!\\\u0001\nE\u00164wN]3Q_NDq!!2'\u0001\u0004\ti'A\u0003ta2LG/\u0006\u0002\u0002^B1\u0011q\\As\u0003gj!!!9\u000b\u0007\u0005\r8(A\u0005j[6,H/\u00192mK&!\u0011q]Aq\u0005\u0011a\u0015n\u001d;\u0002\u000b5,'oZ3\u0015\t\u0005\r\u0013Q\u001e\u0005\b\u0003_D\u0003\u0019AA:\u0003\u0011!\b.\u0019;\u0002%MDw.\u001e7e'Bd\u0017\u000e\u001e$veRDWM]\u000b\u0003\u0003k\u00042\u0001TA|\u0013\r\tI0\u0010\u0002\b\u0005>|G.Z1o\u0003I\u0001\u0016M\u001d%bg\"\u001cV\r^\"p[\nLg.\u001a:\u0011\u0005M[3CA\u0016W)\t\ti0\u0001\teSN\u001c'/[7j]\u0006tGOY5ug\u0006\tB-[:de&l\u0017N\\1oi\nLGo\u001d\u0011\u0002\u00139,XN\u00197pG.\u001c\u0018A\u00038v[\ndwnY6tA\u0005\u0001B-[:de&l\u0017N\\1oi6\f7o[\u0001\u0012I&\u001c8M]5nS:\fg\u000e^7bg.\u0004\u0013!\u00048p]6\f7o\u001b7f]\u001e$\b.\u0001\bo_:l\u0017m]6mK:<G\u000f\u001b\u0011\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\t\t]!QD\u000b\u0003\u00053\u0001Ba\u0015\u0001\u0003\u001cA\u0019aI!\b\u0005\u000b!+$\u0019\u0001&"
)
public abstract class ParHashSetCombiner extends BucketCombiner implements FlatHashTable.HashUtils {
   private final int tableLoadFactor;
   private final int nonmasklen;
   private final int scala$collection$parallel$mutable$ParHashSetCombiner$$seedvalue;

   public static ParHashSetCombiner apply() {
      return ParHashSetCombiner$.MODULE$.apply();
   }

   public final int sizeMapBucketBitSize() {
      return FlatHashTable.HashUtils.sizeMapBucketBitSize$(this);
   }

   public final int sizeMapBucketSize() {
      return FlatHashTable.HashUtils.sizeMapBucketSize$(this);
   }

   public final int improve(final int hcode, final int seed) {
      return FlatHashTable.HashUtils.improve$(this, hcode, seed);
   }

   public final Object elemToEntry(final Object elem) {
      return FlatHashTable.HashUtils.elemToEntry$(this, elem);
   }

   public final Object entryToElem(final Object entry) {
      return FlatHashTable.HashUtils.entryToElem$(this, entry);
   }

   private int tableLoadFactor() {
      return this.tableLoadFactor;
   }

   private int nonmasklen() {
      return this.nonmasklen;
   }

   public int scala$collection$parallel$mutable$ParHashSetCombiner$$seedvalue() {
      return this.scala$collection$parallel$mutable$ParHashSetCombiner$$seedvalue;
   }

   public ParHashSetCombiner addOne(final Object elem) {
      Object entry = this.elemToEntry(elem);
      this.sz_$eq(this.sz() + 1);
      int hc = this.improve(entry.hashCode(), this.scala$collection$parallel$mutable$ParHashSetCombiner$$seedvalue());
      int pos = hc >>> this.nonmasklen();
      if (this.buckets()[pos] == null) {
         this.buckets()[pos] = new UnrolledBuffer(.MODULE$.AnyRef());
      }

      this.buckets()[pos].$plus$eq(entry);
      return this;
   }

   public ParHashSet result() {
      FlatHashTable.Contents contents = this.size() >= ParHashSetCombiner$.MODULE$.numblocks() * this.sizeMapBucketSize() ? this.parPopulate() : this.seqPopulate();
      return new ParHashSet(contents);
   }

   private FlatHashTable.Contents parPopulate() {
      AddingFlatHashTable table = new AddingFlatHashTable(this.size(), this.tableLoadFactor(), this.scala$collection$parallel$mutable$ParHashSetCombiner$$seedvalue());
      Tuple2 var4 = (Tuple2)this.combinerTaskSupport().executeAndWaitResult(new FillBlocks(this.buckets(), table, 0, this.buckets().length));
      if (var4 != null) {
         int inserted = var4._1$mcI$sp();
         UnrolledBuffer leftovers = (UnrolledBuffer)var4._2();
         Tuple2 var3 = new Tuple2(BoxesRunTime.boxToInteger(inserted), leftovers);
         int inserted = var3._1$mcI$sp();
         UnrolledBuffer leftovers = (UnrolledBuffer)var3._2();
         IntRef leftinserts = IntRef.create(0);
         leftovers.foreach((entry) -> {
            $anonfun$parPopulate$1(leftinserts, table, entry);
            return BoxedUnit.UNIT;
         });
         table.setSize(leftinserts.elem + inserted);
         return table.hashTableContents();
      } else {
         throw new MatchError(var4);
      }
   }

   private FlatHashTable.Contents seqPopulate() {
      FlatHashTable tbl = new FlatHashTable() {
         private int _loadFactor;
         private Object[] table;
         private int tableSize;
         private int threshold;
         private int[] sizemap;
         private int seedvalue;

         public int capacity(final int expectedSize) {
            return FlatHashTable.capacity$(this, expectedSize);
         }

         public int initialSize() {
            return FlatHashTable.initialSize$(this);
         }

         public int size() {
            return FlatHashTable.size$(this);
         }

         public int randomSeed() {
            return FlatHashTable.randomSeed$(this);
         }

         public int tableSizeSeed() {
            return FlatHashTable.tableSizeSeed$(this);
         }

         public void init(final ObjectInputStream in, final Function1 f) {
            FlatHashTable.init$(this, in, f);
         }

         public void serializeTo(final ObjectOutputStream out) {
            FlatHashTable.serializeTo$(this, out);
         }

         public Option findEntry(final Object elem) {
            return FlatHashTable.findEntry$(this, elem);
         }

         public boolean containsElem(final Object elem) {
            return FlatHashTable.containsElem$(this, elem);
         }

         public boolean addElem(final Object elem) {
            return FlatHashTable.addElem$(this, elem);
         }

         public boolean addEntry(final Object newEntry) {
            return FlatHashTable.addEntry$(this, newEntry);
         }

         public boolean removeElem(final Object elem) {
            return FlatHashTable.removeElem$(this, elem);
         }

         public Iterator iterator() {
            return FlatHashTable.iterator$(this);
         }

         public final void nnSizeMapAdd(final int h) {
            FlatHashTable.nnSizeMapAdd$(this, h);
         }

         public final void nnSizeMapRemove(final int h) {
            FlatHashTable.nnSizeMapRemove$(this, h);
         }

         public final void nnSizeMapReset(final int tableLength) {
            FlatHashTable.nnSizeMapReset$(this, tableLength);
         }

         public final int totalSizeMapBuckets() {
            return FlatHashTable.totalSizeMapBuckets$(this);
         }

         public final int calcSizeMapSize(final int tableLength) {
            return FlatHashTable.calcSizeMapSize$(this, tableLength);
         }

         public final void sizeMapInit(final int tableLength) {
            FlatHashTable.sizeMapInit$(this, tableLength);
         }

         public final void sizeMapInitAndRebuild() {
            FlatHashTable.sizeMapInitAndRebuild$(this);
         }

         public void printSizeMap() {
            FlatHashTable.printSizeMap$(this);
         }

         public void printContents() {
            FlatHashTable.printContents$(this);
         }

         public void sizeMapDisable() {
            FlatHashTable.sizeMapDisable$(this);
         }

         public boolean isSizeMapDefined() {
            return FlatHashTable.isSizeMapDefined$(this);
         }

         public boolean alwaysInitSizeMap() {
            return FlatHashTable.alwaysInitSizeMap$(this);
         }

         public int index(final int hcode) {
            return FlatHashTable.index$(this, hcode);
         }

         public void clearTable() {
            FlatHashTable.clearTable$(this);
         }

         public FlatHashTable.Contents hashTableContents() {
            return FlatHashTable.hashTableContents$(this);
         }

         public void initWithContents(final FlatHashTable.Contents c) {
            FlatHashTable.initWithContents$(this, c);
         }

         public final int sizeMapBucketBitSize() {
            return FlatHashTable.HashUtils.sizeMapBucketBitSize$(this);
         }

         public final int sizeMapBucketSize() {
            return FlatHashTable.HashUtils.sizeMapBucketSize$(this);
         }

         public final int improve(final int hcode, final int seed) {
            return FlatHashTable.HashUtils.improve$(this, hcode, seed);
         }

         public final Object elemToEntry(final Object elem) {
            return FlatHashTable.HashUtils.elemToEntry$(this, elem);
         }

         public final Object entryToElem(final Object entry) {
            return FlatHashTable.HashUtils.entryToElem$(this, entry);
         }

         public int _loadFactor() {
            return this._loadFactor;
         }

         public void _loadFactor_$eq(final int x$1) {
            this._loadFactor = x$1;
         }

         public Object[] table() {
            return this.table;
         }

         public void table_$eq(final Object[] x$1) {
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

         // $FF: synthetic method
         public static final boolean $anonfun$new$1(final UnrolledBuffer buffer) {
            return buffer != null;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$new$3(final Object $this, final Object entry) {
            return $this.addEntry(entry);
         }

         // $FF: synthetic method
         public static final void $anonfun$new$2(final Object $this, final UnrolledBuffer buffer) {
            buffer.foreach((entry) -> BoxesRunTime.boxToBoolean($anonfun$new$3($this, entry)));
         }

         public {
            FlatHashTable.HashUtils.$init$(this);
            FlatHashTable.$init$(this);
            this.sizeMapInit(this.table().length);
            this.seedvalue_$eq(ParHashSetCombiner.this.scala$collection$parallel$mutable$ParHashSetCombiner$$seedvalue());
            scala.collection.ArrayOps..MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps((Object[])ParHashSetCombiner.this.buckets()), (buffer) -> BoxesRunTime.boxToBoolean($anonfun$new$1(buffer))).foreach((buffer) -> {
               $anonfun$new$2(this, buffer);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      return tbl.hashTableContents();
   }

   // $FF: synthetic method
   public static final void $anonfun$parPopulate$1(final IntRef leftinserts$1, final AddingFlatHashTable table$1, final Object entry) {
      leftinserts$1.elem += table$1.insertEntry(0, table$1.tableLength(), entry);
   }

   public ParHashSetCombiner(final int tableLoadFactor) {
      super(ParHashSetCombiner$.MODULE$.numblocks());
      this.tableLoadFactor = tableLoadFactor;
      FlatHashTable.HashUtils.$init$(this);
      this.nonmasklen = ParHashSetCombiner$.MODULE$.nonmasklength();
      this.scala$collection$parallel$mutable$ParHashSetCombiner$$seedvalue = 27;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public class AddingFlatHashTable implements FlatHashTable {
      private int _loadFactor;
      private Object[] table;
      private int tableSize;
      private int threshold;
      private int[] sizemap;
      private int seedvalue;
      // $FF: synthetic field
      public final ParHashSetCombiner $outer;

      public int capacity(final int expectedSize) {
         return FlatHashTable.capacity$(this, expectedSize);
      }

      public int initialSize() {
         return FlatHashTable.initialSize$(this);
      }

      public int size() {
         return FlatHashTable.size$(this);
      }

      public int randomSeed() {
         return FlatHashTable.randomSeed$(this);
      }

      public int tableSizeSeed() {
         return FlatHashTable.tableSizeSeed$(this);
      }

      public void init(final ObjectInputStream in, final Function1 f) {
         FlatHashTable.init$(this, in, f);
      }

      public void serializeTo(final ObjectOutputStream out) {
         FlatHashTable.serializeTo$(this, out);
      }

      public Option findEntry(final Object elem) {
         return FlatHashTable.findEntry$(this, elem);
      }

      public boolean containsElem(final Object elem) {
         return FlatHashTable.containsElem$(this, elem);
      }

      public boolean addElem(final Object elem) {
         return FlatHashTable.addElem$(this, elem);
      }

      public boolean addEntry(final Object newEntry) {
         return FlatHashTable.addEntry$(this, newEntry);
      }

      public boolean removeElem(final Object elem) {
         return FlatHashTable.removeElem$(this, elem);
      }

      public Iterator iterator() {
         return FlatHashTable.iterator$(this);
      }

      public final void nnSizeMapAdd(final int h) {
         FlatHashTable.nnSizeMapAdd$(this, h);
      }

      public final void nnSizeMapRemove(final int h) {
         FlatHashTable.nnSizeMapRemove$(this, h);
      }

      public final void nnSizeMapReset(final int tableLength) {
         FlatHashTable.nnSizeMapReset$(this, tableLength);
      }

      public final int totalSizeMapBuckets() {
         return FlatHashTable.totalSizeMapBuckets$(this);
      }

      public final int calcSizeMapSize(final int tableLength) {
         return FlatHashTable.calcSizeMapSize$(this, tableLength);
      }

      public final void sizeMapInit(final int tableLength) {
         FlatHashTable.sizeMapInit$(this, tableLength);
      }

      public final void sizeMapInitAndRebuild() {
         FlatHashTable.sizeMapInitAndRebuild$(this);
      }

      public void printSizeMap() {
         FlatHashTable.printSizeMap$(this);
      }

      public void printContents() {
         FlatHashTable.printContents$(this);
      }

      public void sizeMapDisable() {
         FlatHashTable.sizeMapDisable$(this);
      }

      public boolean isSizeMapDefined() {
         return FlatHashTable.isSizeMapDefined$(this);
      }

      public boolean alwaysInitSizeMap() {
         return FlatHashTable.alwaysInitSizeMap$(this);
      }

      public int index(final int hcode) {
         return FlatHashTable.index$(this, hcode);
      }

      public void clearTable() {
         FlatHashTable.clearTable$(this);
      }

      public FlatHashTable.Contents hashTableContents() {
         return FlatHashTable.hashTableContents$(this);
      }

      public void initWithContents(final FlatHashTable.Contents c) {
         FlatHashTable.initWithContents$(this, c);
      }

      public final int sizeMapBucketBitSize() {
         return FlatHashTable.HashUtils.sizeMapBucketBitSize$(this);
      }

      public final int sizeMapBucketSize() {
         return FlatHashTable.HashUtils.sizeMapBucketSize$(this);
      }

      public final int improve(final int hcode, final int seed) {
         return FlatHashTable.HashUtils.improve$(this, hcode, seed);
      }

      public final Object elemToEntry(final Object elem) {
         return FlatHashTable.HashUtils.elemToEntry$(this, elem);
      }

      public final Object entryToElem(final Object entry) {
         return FlatHashTable.HashUtils.entryToElem$(this, entry);
      }

      public int _loadFactor() {
         return this._loadFactor;
      }

      public void _loadFactor_$eq(final int x$1) {
         this._loadFactor = x$1;
      }

      public Object[] table() {
         return this.table;
      }

      public void table_$eq(final Object[] x$1) {
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

      public String toString() {
         return scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("AFHT(%s)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(this.table().length)}));
      }

      public int tableLength() {
         return this.table().length;
      }

      public void setSize(final int sz) {
         this.tableSize_$eq(sz);
      }

      public int insertEntry(final int insertAt, final int comesBefore, final Object newEntry) {
         int h = insertAt;
         if (insertAt == -1) {
            h = this.index(newEntry.hashCode());
         }

         for(Object curEntry = this.table()[h]; curEntry != null; curEntry = this.table()[h]) {
            if (BoxesRunTime.equals(curEntry, newEntry)) {
               return 0;
            }

            ++h;
            if (h >= comesBefore) {
               return -1;
            }
         }

         this.table()[h] = newEntry;
         this.nnSizeMapAdd(h);
         return 1;
      }

      // $FF: synthetic method
      public ParHashSetCombiner scala$collection$parallel$mutable$ParHashSetCombiner$AddingFlatHashTable$$$outer() {
         return this.$outer;
      }

      public AddingFlatHashTable(final int numelems, final int lf, final int inseedvalue) {
         if (ParHashSetCombiner.this == null) {
            throw null;
         } else {
            this.$outer = ParHashSetCombiner.this;
            super();
            FlatHashTable.HashUtils.$init$(this);
            FlatHashTable.$init$(this);
            this._loadFactor_$eq(lf);
            this.table_$eq(new Object[this.capacity(FlatHashTable$.MODULE$.sizeForThreshold(numelems, this._loadFactor()))]);
            this.tableSize_$eq(0);
            this.threshold_$eq(FlatHashTable$.MODULE$.newThreshold(this._loadFactor(), this.table().length));
            this.seedvalue_$eq(inseedvalue);
            this.sizeMapInit(this.table().length);
         }
      }
   }

   public class FillBlocks implements Task {
      private final UnrolledBuffer[] buckets;
      private final AddingFlatHashTable table;
      private final int offset;
      private final int howmany;
      private Tuple2 result;
      private final int blocksize;
      private volatile Throwable throwable;
      // $FF: synthetic field
      public final ParHashSetCombiner $outer;

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

      public int offset() {
         return this.offset;
      }

      public int howmany() {
         return this.howmany;
      }

      public Tuple2 result() {
         return this.result;
      }

      public void result_$eq(final Tuple2 x$1) {
         this.result = x$1;
      }

      public void leaf(final Option prev) {
         int i = this.offset();
         int totalinserts = 0;

         UnrolledBuffer leftover;
         for(leftover = new UnrolledBuffer(.MODULE$.AnyRef()); i < this.offset() + this.howmany(); ++i) {
            Tuple2 var7 = this.fillBlock(i, this.buckets[i], leftover);
            if (var7 == null) {
               throw new MatchError(var7);
            }

            int inserted = var7._1$mcI$sp();
            UnrolledBuffer intonextblock = (UnrolledBuffer)var7._2();
            Tuple2 var6 = new Tuple2(BoxesRunTime.boxToInteger(inserted), intonextblock);
            int insertedx = var6._1$mcI$sp();
            UnrolledBuffer intonextblock = (UnrolledBuffer)var6._2();
            totalinserts += insertedx;
            leftover = intonextblock;
         }

         this.result_$eq(new Tuple2(BoxesRunTime.boxToInteger(totalinserts), leftover));
      }

      private int blocksize() {
         return this.blocksize;
      }

      private int blockStart(final int block) {
         return block * this.blocksize();
      }

      private int nextBlockStart(final int block) {
         return (block + 1) * this.blocksize();
      }

      private Tuple2 fillBlock(final int block, final UnrolledBuffer elems, final UnrolledBuffer leftovers) {
         int beforePos = this.nextBlockStart(block);
         Tuple2 var8 = elems != null ? this.insertAll(-1, beforePos, elems) : new Tuple2(BoxesRunTime.boxToInteger(0), scala.collection.mutable.UnrolledBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$, .MODULE$.AnyRef()));
         if (var8 != null) {
            int elemsIn = var8._1$mcI$sp();
            UnrolledBuffer elemsLeft = (UnrolledBuffer)var8._2();
            Tuple2 var7 = new Tuple2(BoxesRunTime.boxToInteger(elemsIn), elemsLeft);
            int elemsIn = var7._1$mcI$sp();
            UnrolledBuffer elemsLeft = (UnrolledBuffer)var7._2();
            Tuple2 var14 = this.insertAll(this.blockStart(block), beforePos, leftovers);
            if (var14 != null) {
               int leftoversIn = var14._1$mcI$sp();
               UnrolledBuffer leftoversLeft = (UnrolledBuffer)var14._2();
               Tuple2 var13 = new Tuple2(BoxesRunTime.boxToInteger(leftoversIn), leftoversLeft);
               int leftoversInx = var13._1$mcI$sp();
               UnrolledBuffer leftoversLeftx = (UnrolledBuffer)var13._2();
               return new Tuple2(BoxesRunTime.boxToInteger(elemsIn + leftoversInx), elemsLeft.concat(leftoversLeftx));
            } else {
               throw new MatchError(var14);
            }
         } else {
            throw new MatchError(var8);
         }
      }

      private Tuple2 insertAll(final int atPos, final int beforePos, final UnrolledBuffer elems) {
         UnrolledBuffer leftovers = new UnrolledBuffer(.MODULE$.AnyRef());
         int inserted = 0;
         UnrolledBuffer.Unrolled unrolled = elems.headPtr();
         int i = 0;

         for(AddingFlatHashTable t = this.table; unrolled != null; unrolled = unrolled.next()) {
            Object[] chunkarr = unrolled.array();

            for(int chunksz = unrolled.size(); i < chunksz; ++i) {
               Object entry = chunkarr[i];
               int res = t.insertEntry(atPos, beforePos, entry);
               if (res >= 0) {
                  inserted += res;
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  leftovers.$plus$eq(entry);
               }
            }

            i = 0;
         }

         return new Tuple2(BoxesRunTime.boxToInteger(inserted), leftovers);
      }

      public List split() {
         int fp = this.howmany() / 2;
         return new scala.collection.immutable..colon.colon(this.scala$collection$parallel$mutable$ParHashSetCombiner$FillBlocks$$$outer().new FillBlocks(this.buckets, this.table, this.offset(), fp), new scala.collection.immutable..colon.colon(this.scala$collection$parallel$mutable$ParHashSetCombiner$FillBlocks$$$outer().new FillBlocks(this.buckets, this.table, this.offset() + fp, this.howmany() - fp), scala.collection.immutable.Nil..MODULE$));
      }

      public void merge(final FillBlocks that) {
         int atPos = this.blockStart(that.offset());
         int beforePos = this.blockStart(that.offset() + that.howmany());
         Tuple2 var6 = this.insertAll(atPos, beforePos, (UnrolledBuffer)this.result()._2());
         if (var6 != null) {
            int inserted = var6._1$mcI$sp();
            UnrolledBuffer remainingLeftovers = (UnrolledBuffer)var6._2();
            Tuple2 var5 = new Tuple2(BoxesRunTime.boxToInteger(inserted), remainingLeftovers);
            int insertedx = var5._1$mcI$sp();
            UnrolledBuffer remainingLeftoversx = (UnrolledBuffer)var5._2();
            this.result_$eq(new Tuple2(BoxesRunTime.boxToInteger(this.result()._1$mcI$sp() + that.result()._1$mcI$sp() + insertedx), remainingLeftoversx.concat((UnrolledBuffer)that.result()._2())));
         } else {
            throw new MatchError(var6);
         }
      }

      public boolean shouldSplitFurther() {
         return this.howmany() > scala.collection.parallel.package$.MODULE$.thresholdFromSize(ParHashMapCombiner$.MODULE$.numblocks(), this.scala$collection$parallel$mutable$ParHashSetCombiner$FillBlocks$$$outer().combinerTaskSupport().parallelismLevel());
      }

      // $FF: synthetic method
      public ParHashSetCombiner scala$collection$parallel$mutable$ParHashSetCombiner$FillBlocks$$$outer() {
         return this.$outer;
      }

      public FillBlocks(final UnrolledBuffer[] buckets, final AddingFlatHashTable table, final int offset, final int howmany) {
         this.buckets = buckets;
         this.table = table;
         this.offset = offset;
         this.howmany = howmany;
         if (ParHashSetCombiner.this == null) {
            throw null;
         } else {
            this.$outer = ParHashSetCombiner.this;
            super();
            Task.$init$(this);
            this.result = new Tuple2(BoxesRunTime.boxToInteger(Integer.MIN_VALUE), new UnrolledBuffer(.MODULE$.AnyRef()));
            this.blocksize = table.tableLength() >> ParHashSetCombiner$.MODULE$.discriminantbits();
         }
      }
   }
}

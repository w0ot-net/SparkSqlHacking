package org.apache.spark.rdd;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partition;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Growable;
import scala.collection.mutable.LinkedHashMap;
import scala.collection.mutable.Map;
import scala.collection.mutable.Set;
import scala.math.Ordering;
import scala.math.PartialOrdering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt.;
import scala.runtime.java8.JFunction1;
import scala.util.Random;

@ScalaSignature(
   bytes = "\u0006\u0005\t5c\u0001B\u0014)\tEB\u0001\u0002\u0010\u0001\u0003\u0006\u0004%\t!\u0010\u0005\t\u0003\u0002\u0011\t\u0011)A\u0005}!)!\t\u0001C\u0001\u0007\u001e)a\t\u0001E\u0002\u000f\u001a)\u0011\n\u0001E\u0001\u0015\")!)\u0002C\u0001E\")1-\u0002C!I\"9A\u000e\u0001b\u0001\n\u0003i\u0007B\u0002;\u0001A\u0003%a\u000eC\u0004v\u0001\t\u0007I\u0011\u0001<\t\r}\u0004\u0001\u0015!\u0003x\u0011%\t\t\u0001\u0001b\u0001\n\u0003\t\u0019\u0001\u0003\u0005\u0002\u001c\u0001\u0001\u000b\u0011BA\u0003\u0011%\ti\u0002\u0001b\u0001\n\u0003\ty\u0002\u0003\u0005\u00020\u0001\u0001\u000b\u0011BA\u0011\u0011%\t\t\u0004\u0001a\u0001\n\u0003\t\u0019\u0004C\u0005\u0002<\u0001\u0001\r\u0011\"\u0001\u0002>!A\u0011\u0011\n\u0001!B\u0013\t)\u0004C\u0004\u0002L\u0001!\t!!\u0014\u0007\r\u0005m\u0004\u0001BA?\u0011)\tY\u0006\u0006B\u0001B\u0003%\u0011q\u0010\u0005\u0007\u0005R!\t!!#\t\u0013\u0005]EC1A\u0005\u0002\u0005e\u0005\u0002CAO)\u0001\u0006I!a'\t\u0013\u0005}EC1A\u0005\u0002\u0005\u0005\u0006\u0002CAV)\u0001\u0006I!a)\t\u000f\u00055F\u0003\"\u0001\u00020\"9\u0011Q\u0018\u0001\u0005\u0002\u0005}\u0006bBAf\u0001\u0011\u0005\u0011Q\u001a\u0005\b\u0003+\u0004A\u0011AAl\u0011\u001d\t\t\u000f\u0001C\u0001\u0003GDq!!?\u0001\t\u0003\tY\u0010C\u0004\u0003\u0012\u0001!\tAa\u0005\t\u000f\tm\u0001\u0001\"\u0001\u0003\u001e\u001dI!Q\u0006\u0015\u0002\u0002#%!q\u0006\u0004\tO!\n\t\u0011#\u0003\u00032!1!\t\nC\u0001\u0005gA\u0011B!\u000e%#\u0003%\tAa\u000e\u00033\u0011+g-Y;miB\u000b'\u000f^5uS>t7i\\1mKN\u001cWM\u001d\u0006\u0003S)\n1A\u001d3e\u0015\tYC&A\u0003ta\u0006\u00148N\u0003\u0002.]\u00051\u0011\r]1dQ\u0016T\u0011aL\u0001\u0004_J<7\u0001A\n\u0004\u0001IB\u0004CA\u001a7\u001b\u0005!$\"A\u001b\u0002\u000bM\u001c\u0017\r\\1\n\u0005]\"$AB!osJ+g\r\u0005\u0002:u5\t\u0001&\u0003\u0002<Q\t\u0011\u0002+\u0019:uSRLwN\\\"pC2,7oY3s\u00031\u0011\u0017\r\\1oG\u0016\u001cF.Y2l+\u0005q\u0004CA\u001a@\u0013\t\u0001EG\u0001\u0004E_V\u0014G.Z\u0001\u000eE\u0006d\u0017M\\2f'2\f7m\u001b\u0011\u0002\rqJg.\u001b;?)\t!U\t\u0005\u0002:\u0001!9Ah\u0001I\u0001\u0002\u0004q\u0014A\u00069beRLG/[8o\u000fJ|W\u000f](sI\u0016\u0014\u0018N\\4\u0011\u0005!+Q\"\u0001\u0001\u0003-A\f'\u000f^5uS>twI]8va>\u0013H-\u001a:j]\u001e\u001c2!B&T!\ta\u0015+D\u0001N\u0015\tqu*\u0001\u0003mC:<'\"\u0001)\u0002\t)\fg/Y\u0005\u0003%6\u0013aa\u00142kK\u000e$\bc\u0001+]?:\u0011QK\u0017\b\u0003-fk\u0011a\u0016\u0006\u00031B\na\u0001\u0010:p_Rt\u0014\"A\u001b\n\u0005m#\u0014a\u00029bG.\fw-Z\u0005\u0003;z\u0013\u0001b\u0014:eKJLgn\u001a\u0006\u00037R\u0002\"!\u000f1\n\u0005\u0005D#A\u0004)beRLG/[8o\u000fJ|W\u000f\u001d\u000b\u0002\u000f\u000691m\\7qCJ,GcA3iUB\u00111GZ\u0005\u0003OR\u00121!\u00138u\u0011\u0015Iw\u00011\u0001`\u0003\ty\u0017\u0007C\u0003l\u000f\u0001\u0007q,\u0001\u0002pe\u0005\u0019!O\u001c3\u0016\u00039\u0004\"a\u001c:\u000e\u0003AT!!\u001d\u001b\u0002\tU$\u0018\u000e\\\u0005\u0003gB\u0014aAU1oI>l\u0017\u0001\u0002:oI\u0002\n\u0001b\u001a:pkB\f%O]\u000b\u0002oB\u0019\u00010`0\u000e\u0003eT!A_>\u0002\u000f5,H/\u00192mK*\u0011A\u0010N\u0001\u000bG>dG.Z2uS>t\u0017B\u0001@z\u0005-\t%O]1z\u0005V4g-\u001a:\u0002\u0013\u001d\u0014x.\u001e9BeJ\u0004\u0013!C4s_V\u0004\b*Y:i+\t\t)\u0001\u0005\u0004y\u0003\u000f\tYa^\u0005\u0004\u0003\u0013I(aA'baB!\u0011QBA\u000b\u001d\u0011\ty!!\u0005\u0011\u0005Y#\u0014bAA\ni\u00051\u0001K]3eK\u001aLA!a\u0006\u0002\u001a\t11\u000b\u001e:j]\u001eT1!a\u00055\u0003)9'o\\;q\u0011\u0006\u001c\b\u000eI\u0001\fS:LG/[1m\u0011\u0006\u001c\b.\u0006\u0002\u0002\"A)\u00010a\t\u0002(%\u0019\u0011QE=\u0003\u0007M+G\u000f\u0005\u0003\u0002*\u0005-R\"\u0001\u0016\n\u0007\u00055\"FA\u0005QCJ$\u0018\u000e^5p]\u0006a\u0011N\\5uS\u0006d\u0007*Y:iA\u0005Qan\u001c'pG\u0006d\u0017\u000e^=\u0016\u0005\u0005U\u0002cA\u001a\u00028%\u0019\u0011\u0011\b\u001b\u0003\u000f\t{w\u000e\\3b]\u0006qan\u001c'pG\u0006d\u0017\u000e^=`I\u0015\fH\u0003BA \u0003\u000b\u00022aMA!\u0013\r\t\u0019\u0005\u000e\u0002\u0005+:LG\u000fC\u0005\u0002HE\t\t\u00111\u0001\u00026\u0005\u0019\u0001\u0010J\u0019\u0002\u00179|Gj\\2bY&$\u0018\u0010I\u0001\rGV\u0014(\u000f\u0015:fM2{7m\u001d\u000b\u0007\u0003\u001f\n)&!\u0017\u0011\u000bQ\u000b\t&a\u0003\n\u0007\u0005McLA\u0002TKFDq!a\u0016\u0014\u0001\u0004\t9#\u0001\u0003qCJ$\bbBA.'\u0001\u0007\u0011QL\u0001\u0005aJ,g\u000f\r\u0003\u0002`\u0005%\u0004#B\u001d\u0002b\u0005\u0015\u0014bAA2Q\t\u0019!\u000b\u0012#\u0011\t\u0005\u001d\u0014\u0011\u000e\u0007\u0001\t1\tY'!\u0017\u0002\u0002\u0003\u0005)\u0011AA7\u0005\ryFeM\t\u0005\u0003_\n)\bE\u00024\u0003cJ1!a\u001d5\u0005\u001dqu\u000e\u001e5j]\u001e\u00042aMA<\u0013\r\tI\b\u000e\u0002\u0004\u0003:L(A\u0005)beRLG/[8o\u0019>\u001c\u0017\r^5p]N\u001c\"\u0001\u0006\u001a1\t\u0005\u0005\u0015Q\u0011\t\u0006s\u0005\u0005\u00141\u0011\t\u0005\u0003O\n)\tB\u0006\u0002\bV\t\t\u0011!A\u0003\u0002\u00055$aA0%iQ!\u00111RAG!\tAE\u0003C\u0004\u0002\\Y\u0001\r!a$1\t\u0005E\u0015Q\u0013\t\u0006s\u0005\u0005\u00141\u0013\t\u0005\u0003O\n)\n\u0002\u0007\u0002\b\u00065\u0015\u0011!A\u0001\u0006\u0003\ti'\u0001\tqCJ$8oV5uQ>,H\u000fT8dgV\u0011\u00111\u0014\t\u0005qv\f9#A\tqCJ$8oV5uQ>,H\u000fT8dg\u0002\nQ\u0002]1siN<\u0016\u000e\u001e5M_\u000e\u001cXCAAR!\u0011AX0!*\u0011\u000fM\n9+a\u0003\u0002(%\u0019\u0011\u0011\u0016\u001b\u0003\rQ+\b\u000f\\33\u00039\u0001\u0018M\u001d;t/&$\b\u000eT8dg\u0002\nabZ3u\u00032d\u0007K]3g\u0019>\u001c7\u000f\u0006\u0003\u0002@\u0005E\u0006bBA.7\u0001\u0007\u00111\u0017\u0019\u0005\u0003k\u000bI\fE\u0003:\u0003C\n9\f\u0005\u0003\u0002h\u0005eF\u0001DA^\u0003c\u000b\t\u0011!A\u0003\u0002\u00055$aA0%k\u0005\tr-\u001a;MK\u0006\u001cHo\u0012:pkBD\u0015m\u001d5\u0015\t\u0005\u0005\u0017q\u0019\t\u0005g\u0005\rw,C\u0002\u0002FR\u0012aa\u00149uS>t\u0007bBAe9\u0001\u0007\u00111B\u0001\u0004W\u0016L\u0018aD1eIB\u000b'\u000f\u001e+p!\u001e\u0013x.\u001e9\u0015\r\u0005U\u0012qZAi\u0011\u001d\t9&\ba\u0001\u0003OAa!a5\u001e\u0001\u0004y\u0016A\u00029he>,\b/A\u0006tKR,\bo\u0012:pkB\u001cHCBA \u00033\fi\u000e\u0003\u0004\u0002\\z\u0001\r!Z\u0001\ni\u0006\u0014x-\u001a;MK:Dq!a8\u001f\u0001\u0004\tY)A\u0007qCJ$\u0018\u000e^5p]2{7m]\u0001\ba&\u001c7NQ5o)%y\u0016Q]Au\u0003k\f9\u0010C\u0004\u0002h~\u0001\r!a\n\u0002\u0003ADq!a\u0017 \u0001\u0004\tY\u000f\r\u0003\u0002n\u0006E\b#B\u001d\u0002b\u0005=\b\u0003BA4\u0003c$A\"a=\u0002j\u0006\u0005\t\u0011!B\u0001\u0003[\u00121a\u0018\u00137\u0011\u0015at\u00041\u0001?\u0011\u001d\tyn\ba\u0001\u0003\u0017\u000b!\u0002\u001e5s_^\u0014\u0015\r\u001c7t))\ty$!@\u0003\u0002\t5!q\u0002\u0005\u0007\u0003\u007f\u0004\u0003\u0019A3\u0002\u001b5\f\u0007\u0010U1si&$\u0018n\u001c8t\u0011\u001d\tY\u0006\ta\u0001\u0005\u0007\u0001DA!\u0002\u0003\nA)\u0011(!\u0019\u0003\bA!\u0011q\rB\u0005\t1\u0011YA!\u0001\u0002\u0002\u0003\u0005)\u0011AA7\u0005\ryFe\u000e\u0005\u0006y\u0001\u0002\rA\u0010\u0005\b\u0003?\u0004\u0003\u0019AAF\u000359W\r\u001e)beRLG/[8ogV\u0011!Q\u0003\t\u0005g\t]q,C\u0002\u0003\u001aQ\u0012Q!\u0011:sCf\f\u0001bY8bY\u0016\u001c8-\u001a\u000b\u0007\u0005+\u0011yB!\t\t\r\u0005}(\u00051\u0001f\u0011\u001d\tYF\ta\u0001\u0005G\u0001DA!\n\u0003*A)\u0011(!\u0019\u0003(A!\u0011q\rB\u0015\t1\u0011YC!\t\u0002\u0002\u0003\u0005)\u0011AA7\u0005\ryF\u0005O\u0001\u001a\t\u00164\u0017-\u001e7u!\u0006\u0014H/\u001b;j_:\u001cu.\u00197fg\u000e,'\u000f\u0005\u0002:IM\u0011AE\r\u000b\u0003\u0005_\t1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\nTC\u0001B\u001dU\rq$1H\u0016\u0003\u0005{\u0001BAa\u0010\u0003J5\u0011!\u0011\t\u0006\u0005\u0005\u0007\u0012)%A\u0005v]\u000eDWmY6fI*\u0019!q\t\u001b\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0003L\t\u0005#!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0002"
)
public class DefaultPartitionCoalescer implements PartitionCoalescer {
   private volatile partitionGroupOrdering$ partitionGroupOrdering$module;
   private final double balanceSlack;
   private final Random rnd;
   private final ArrayBuffer groupArr;
   private final Map groupHash;
   private final Set initialHash;
   private boolean noLocality;

   public static double $lessinit$greater$default$1() {
      return DefaultPartitionCoalescer$.MODULE$.$lessinit$greater$default$1();
   }

   public partitionGroupOrdering$ partitionGroupOrdering() {
      if (this.partitionGroupOrdering$module == null) {
         this.partitionGroupOrdering$lzycompute$1();
      }

      return this.partitionGroupOrdering$module;
   }

   public double balanceSlack() {
      return this.balanceSlack;
   }

   public Random rnd() {
      return this.rnd;
   }

   public ArrayBuffer groupArr() {
      return this.groupArr;
   }

   public Map groupHash() {
      return this.groupHash;
   }

   public Set initialHash() {
      return this.initialHash;
   }

   public boolean noLocality() {
      return this.noLocality;
   }

   public void noLocality_$eq(final boolean x$1) {
      this.noLocality = x$1;
   }

   public Seq currPrefLocs(final Partition part, final RDD prev) {
      return (Seq)prev.context().getPreferredLocs(prev, part.index()).map((tl) -> tl.host());
   }

   public Option getLeastGroupHash(final String key) {
      return this.groupHash().get(key).filter((x$5) -> BoxesRunTime.boxToBoolean($anonfun$getLeastGroupHash$1(x$5))).map((x$6) -> (PartitionGroup)x$6.min(this.partitionGroupOrdering()));
   }

   public boolean addPartToPGroup(final Partition part, final PartitionGroup pgroup) {
      if (!this.initialHash().contains(part)) {
         pgroup.partitions().$plus$eq(part);
         this.initialHash().$plus$eq(part);
         return true;
      } else {
         return false;
      }
   }

   public void setupGroups(final int targetLen, final PartitionLocations partitionLocs) {
      if (partitionLocs.partsWithLocs().isEmpty()) {
         .MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(1), targetLen).foreach((x$7) -> $anonfun$setupGroups$1(this, BoxesRunTime.unboxToInt(x$7)));
      } else {
         this.noLocality_$eq(false);
         int expectedCoupons2 = 2 * (int)(scala.math.package..MODULE$.log((double)targetLen) * (double)targetLen + (double)targetLen + (double)0.5F);
         int numCreated = 0;
         int tries = 0;
         int numPartsToLookAt = scala.math.package..MODULE$.min(expectedCoupons2, partitionLocs.partsWithLocs().length());

         while(numCreated < targetLen && tries < numPartsToLookAt) {
            Tuple2 var10 = (Tuple2)partitionLocs.partsWithLocs().apply(tries);
            if (var10 == null) {
               throw new MatchError(var10);
            }

            String nxt_replica = (String)var10._1();
            Partition nxt_part = (Partition)var10._2();
            Tuple2 var9 = new Tuple2(nxt_replica, nxt_part);
            String nxt_replica = (String)var9._1();
            Partition nxt_part = (Partition)var9._2();
            ++tries;
            if (!this.groupHash().contains(nxt_replica)) {
               PartitionGroup pgroup = new PartitionGroup(new Some(nxt_replica));
               this.groupArr().$plus$eq(pgroup);
               this.addPartToPGroup(nxt_part, pgroup);
               this.groupHash().put(nxt_replica, scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new PartitionGroup[]{pgroup})));
               ++numCreated;
            }
         }

         while(numCreated < targetLen) {
            Tuple2 var17 = (Tuple2)partitionLocs.partsWithLocs().apply(this.rnd().nextInt(partitionLocs.partsWithLocs().length()));
            if (var17 == null) {
               throw new MatchError(var17);
            }

            String nxt_replica = (String)var17._1();
            Partition nxt_part = (Partition)var17._2();
            Tuple2 var16 = new Tuple2(nxt_replica, nxt_part);
            String nxt_replica = (String)var16._1();
            Partition nxt_part = (Partition)var16._2();
            PartitionGroup pgroup = new PartitionGroup(new Some(nxt_replica));
            this.groupArr().$plus$eq(pgroup);
            ((Growable)this.groupHash().getOrElseUpdate(nxt_replica, () -> (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$))).$plus$eq(pgroup);
            this.addPartToPGroup(nxt_part, pgroup);
            ++numCreated;
         }

      }
   }

   public PartitionGroup pickBin(final Partition p, final RDD prev, final double balanceSlack, final PartitionLocations partitionLocs) {
      int slack = (int)(balanceSlack * (double)prev.partitions().length);
      Seq pref = (Seq)this.currPrefLocs(p, prev).flatMap((key) -> this.getLeastGroupHash(key));
      Option prefPart = (Option)(pref.isEmpty() ? scala.None..MODULE$ : new Some(pref.min(this.partitionGroupOrdering())));
      int r1 = this.rnd().nextInt(this.groupArr().size());
      int r2 = this.rnd().nextInt(this.groupArr().size());
      PartitionGroup minPowerOfTwo = ((PartitionGroup)this.groupArr().apply(r1)).numPartitions() < ((PartitionGroup)this.groupArr().apply(r2)).numPartitions() ? (PartitionGroup)this.groupArr().apply(r1) : (PartitionGroup)this.groupArr().apply(r2);
      if (prefPart.isEmpty()) {
         return minPowerOfTwo;
      } else {
         PartitionGroup prefPartActual = (PartitionGroup)prefPart.get();
         return minPowerOfTwo.numPartitions() + slack <= prefPartActual.numPartitions() ? minPowerOfTwo : prefPartActual;
      }
   }

   public void throwBalls(final int maxPartitions, final RDD prev, final double balanceSlack, final PartitionLocations partitionLocs) {
      if (this.noLocality()) {
         if (maxPartitions > this.groupArr().size()) {
            scala.collection.ArrayOps..MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps(prev.partitions()))), (check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$throwBalls$1(check$ifrefutable$1))).foreach((x$10) -> {
               if (x$10 != null) {
                  Partition p = (Partition)x$10._1();
                  int i = x$10._2$mcI$sp();
                  return (ArrayBuffer)((PartitionGroup)this.groupArr().apply(i)).partitions().$plus$eq(p);
               } else {
                  throw new MatchError(x$10);
               }
            });
         } else {
            .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), maxPartitions).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
               int rangeStart = (int)((long)i * (long)prev.partitions().length / (long)maxPartitions);
               int rangeEnd = (int)(((long)i + 1L) * (long)prev.partitions().length / (long)maxPartitions);
               .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(rangeStart), rangeEnd).foreach((j) -> $anonfun$throwBalls$4(this, i, prev, BoxesRunTime.unboxToInt(j)));
            });
         }
      } else {
         Iterator partIter = partitionLocs.partsWithLocs().iterator();
         ((IterableOnceOps)this.groupArr().filter((pg) -> BoxesRunTime.boxToBoolean($anonfun$throwBalls$5(pg)))).foreach((pg) -> {
            $anonfun$throwBalls$6(this, partIter, pg);
            return BoxedUnit.UNIT;
         });
         Iterator partNoLocIter = partitionLocs.partsWithoutLocs().iterator();
         ((IterableOnceOps)this.groupArr().filter((pg) -> BoxesRunTime.boxToBoolean($anonfun$throwBalls$7(pg)))).foreach((pg) -> {
            $anonfun$throwBalls$8(this, partNoLocIter, pg);
            return BoxedUnit.UNIT;
         });
         scala.collection.ArrayOps..MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps(prev.partitions()), (p) -> BoxesRunTime.boxToBoolean($anonfun$throwBalls$9(this, p))).foreach((p) -> (ArrayBuffer)this.pickBin(p, prev, balanceSlack, partitionLocs).partitions().$plus$eq(p));
      }
   }

   public PartitionGroup[] getPartitions() {
      return (PartitionGroup[])((IterableOnceOps)this.groupArr().filter((pg) -> BoxesRunTime.boxToBoolean($anonfun$getPartitions$4(pg)))).toArray(scala.reflect.ClassTag..MODULE$.apply(PartitionGroup.class));
   }

   public PartitionGroup[] coalesce(final int maxPartitions, final RDD prev) {
      PartitionLocations partitionLocs = new PartitionLocations(prev);
      this.setupGroups(scala.math.package..MODULE$.min(prev.partitions().length, maxPartitions), partitionLocs);
      this.throwBalls(maxPartitions, prev, this.balanceSlack(), partitionLocs);
      return this.getPartitions();
   }

   private final void partitionGroupOrdering$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.partitionGroupOrdering$module == null) {
            this.partitionGroupOrdering$module = new partitionGroupOrdering$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   // $FF: synthetic method
   public static final boolean $anonfun$getLeastGroupHash$1(final ArrayBuffer x$5) {
      return x$5.nonEmpty();
   }

   // $FF: synthetic method
   public static final ArrayBuffer $anonfun$setupGroups$1(final DefaultPartitionCoalescer $this, final int x$7) {
      return (ArrayBuffer)$this.groupArr().$plus$eq(new PartitionGroup(PartitionGroup$.MODULE$.$lessinit$greater$default$1()));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$throwBalls$1(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final ArrayBuffer $anonfun$throwBalls$4(final DefaultPartitionCoalescer $this, final int i$1, final RDD prev$2, final int j) {
      return (ArrayBuffer)((PartitionGroup)$this.groupArr().apply(i$1)).partitions().$plus$eq(prev$2.partitions()[j]);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$throwBalls$5(final PartitionGroup pg) {
      return pg.numPartitions() == 0;
   }

   // $FF: synthetic method
   public static final void $anonfun$throwBalls$6(final DefaultPartitionCoalescer $this, final Iterator partIter$1, final PartitionGroup pg) {
      while(true) {
         if (partIter$1.hasNext() && pg.numPartitions() == 0) {
            Tuple2 var5 = (Tuple2)partIter$1.next();
            if (var5 != null) {
               Partition nxt_part = (Partition)var5._2();
               if (!$this.initialHash().contains(nxt_part)) {
                  pg.partitions().$plus$eq(nxt_part);
                  $this.initialHash().$plus$eq(nxt_part);
               } else {
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }
               continue;
            }

            throw new MatchError(var5);
         }

         return;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$throwBalls$7(final PartitionGroup pg) {
      return pg.numPartitions() == 0;
   }

   // $FF: synthetic method
   public static final void $anonfun$throwBalls$8(final DefaultPartitionCoalescer $this, final Iterator partNoLocIter$1, final PartitionGroup pg) {
      while(partNoLocIter$1.hasNext() && pg.numPartitions() == 0) {
         Partition nxt_part = (Partition)partNoLocIter$1.next();
         if (!$this.initialHash().contains(nxt_part)) {
            pg.partitions().$plus$eq(nxt_part);
            $this.initialHash().$plus$eq(nxt_part);
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      }

   }

   // $FF: synthetic method
   public static final boolean $anonfun$throwBalls$9(final DefaultPartitionCoalescer $this, final Partition p) {
      return !$this.initialHash().contains(p);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getPartitions$4(final PartitionGroup pg) {
      return pg.numPartitions() > 0;
   }

   public DefaultPartitionCoalescer(final double balanceSlack) {
      this.balanceSlack = balanceSlack;
      this.rnd = new Random(7919);
      this.groupArr = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      this.groupHash = (Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      this.initialHash = (Set)scala.collection.mutable.Set..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      this.noLocality = true;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class partitionGroupOrdering$ implements Ordering {
      public Some tryCompare(final Object x, final Object y) {
         return Ordering.tryCompare$(this, x, y);
      }

      public boolean lteq(final Object x, final Object y) {
         return Ordering.lteq$(this, x, y);
      }

      public boolean gteq(final Object x, final Object y) {
         return Ordering.gteq$(this, x, y);
      }

      public boolean lt(final Object x, final Object y) {
         return Ordering.lt$(this, x, y);
      }

      public boolean gt(final Object x, final Object y) {
         return Ordering.gt$(this, x, y);
      }

      public boolean equiv(final Object x, final Object y) {
         return Ordering.equiv$(this, x, y);
      }

      public Object max(final Object x, final Object y) {
         return Ordering.max$(this, x, y);
      }

      public Object min(final Object x, final Object y) {
         return Ordering.min$(this, x, y);
      }

      public Ordering reverse() {
         return Ordering.reverse$(this);
      }

      public boolean isReverseOf(final Ordering other) {
         return Ordering.isReverseOf$(this, other);
      }

      public Ordering on(final Function1 f) {
         return Ordering.on$(this, f);
      }

      public Ordering orElse(final Ordering other) {
         return Ordering.orElse$(this, other);
      }

      public Ordering orElseBy(final Function1 f, final Ordering ord) {
         return Ordering.orElseBy$(this, f, ord);
      }

      public Ordering.OrderingOps mkOrderingOps(final Object lhs) {
         return Ordering.mkOrderingOps$(this, lhs);
      }

      public int compare(final PartitionGroup o1, final PartitionGroup o2) {
         return Integer.compare(o1.numPartitions(), o2.numPartitions());
      }

      public partitionGroupOrdering$() {
         PartialOrdering.$init$(this);
         Ordering.$init$(this);
      }
   }

   private class PartitionLocations {
      private final ArrayBuffer partsWithoutLocs;
      private final ArrayBuffer partsWithLocs;
      // $FF: synthetic field
      public final DefaultPartitionCoalescer $outer;

      public ArrayBuffer partsWithoutLocs() {
         return this.partsWithoutLocs;
      }

      public ArrayBuffer partsWithLocs() {
         return this.partsWithLocs;
      }

      public void getAllPrefLocs(final RDD prev) {
         LinkedHashMap tmpPartsWithLocs = (LinkedHashMap)scala.collection.mutable.LinkedHashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(prev.partitions()), (p) -> {
            Seq locs = this.org$apache$spark$rdd$DefaultPartitionCoalescer$PartitionLocations$$$outer().currPrefLocs(p, prev);
            return locs.nonEmpty() ? tmpPartsWithLocs.put(p, locs) : this.partsWithoutLocs().$plus$eq(p);
         });
         .MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(0), 2).foreach$mVc$sp((JFunction1.mcVI.sp)(x) -> tmpPartsWithLocs.foreach((parts) -> {
               Partition p = (Partition)parts._1();
               Seq locs = (Seq)parts._2();
               return locs.size() > x ? this.partsWithLocs().$plus$eq(new Tuple2(locs.apply(x), p)) : BoxedUnit.UNIT;
            }));
      }

      // $FF: synthetic method
      public DefaultPartitionCoalescer org$apache$spark$rdd$DefaultPartitionCoalescer$PartitionLocations$$$outer() {
         return this.$outer;
      }

      public PartitionLocations(final RDD prev) {
         if (DefaultPartitionCoalescer.this == null) {
            throw null;
         } else {
            this.$outer = DefaultPartitionCoalescer.this;
            super();
            this.partsWithoutLocs = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
            this.partsWithLocs = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
            this.getAllPrefLocs(prev);
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}

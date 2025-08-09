package org.apache.spark.scheduler;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkException;
import org.apache.spark.resource.ResourceAmountUtils$;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.resource.TaskResourceRequest;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Predef.ArrowAssoc.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.NonLocalReturnControl;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015b!B\t\u0013\u0001QQ\u0002\u0002\u0003\u0018\u0001\u0005\u000b\u0007I\u0011B\u0018\t\u0011}\u0002!\u0011!Q\u0001\nABQ\u0001\u0011\u0001\u0005\u0002\u0005Cq!\u0012\u0001C\u0002\u0013%a\t\u0003\u0004Q\u0001\u0001\u0006Ia\u0012\u0005\t#\u0002A)\u0019!C\u0001%\"1q\u000b\u0001C\u0001)aCQA\u0018\u0001\u0005\u0002}CQ!\u001a\u0001\u0005\u0002\u0019DQ\u0001\u001b\u0001\u0005\u0002%<a!\u001e\n\t\u0002Q1hAB\t\u0013\u0011\u0003!r\u000fC\u0003A\u0019\u0011\u0005q\u0010C\u0004\u0002\u00021!\t!a\u0001\t\u000f\u0005\u0015A\u0002\"\u0001\u0002\b!I\u0011Q\u0003\u0007\u0002\u0002\u0013%\u0011q\u0003\u0002\u0019\u000bb,7-\u001e;peJ+7o\\;sG\u0016\u001c\u0018)\\8v]R\u001c(BA\n\u0015\u0003%\u00198\r[3ek2,'O\u0003\u0002\u0016-\u0005)1\u000f]1sW*\u0011q\u0003G\u0001\u0007CB\f7\r[3\u000b\u0003e\t1a\u001c:h'\r\u00011$\t\t\u00039}i\u0011!\b\u0006\u0002=\u0005)1oY1mC&\u0011\u0001%\b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\tZcBA\u0012*\u001d\t!\u0003&D\u0001&\u0015\t1s%\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005q\u0012B\u0001\u0016\u001e\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001L\u0017\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005)j\u0012!\u0003:fg>,(oY3t+\u0005\u0001\u0004\u0003B\u00196qmr!AM\u001a\u0011\u0005\u0011j\u0012B\u0001\u001b\u001e\u0003\u0019\u0001&/\u001a3fM&\u0011ag\u000e\u0002\u0004\u001b\u0006\u0004(B\u0001\u001b\u001e!\t\t\u0014(\u0003\u0002;o\t11\u000b\u001e:j]\u001e\u0004B!M\u001b9yA\u0011A$P\u0005\u0003}u\u0011A\u0001T8oO\u0006Q!/Z:pkJ\u001cWm\u001d\u0011\u0002\rqJg.\u001b;?)\t\u0011E\t\u0005\u0002D\u00015\t!\u0003C\u0003/\u0007\u0001\u0007\u0001'A\tj]R,'O\\1m%\u0016\u001cx.\u001e:dKN,\u0012a\u0012\t\u0005cUB\u0004\n\u0005\u0003J\u001dbbT\"\u0001&\u000b\u0005-c\u0015aB7vi\u0006\u0014G.\u001a\u0006\u0003\u001bv\t!bY8mY\u0016\u001cG/[8o\u0013\ty%JA\u0004ICNDW*\u00199\u0002%%tG/\u001a:oC2\u0014Vm]8ve\u000e,7\u000fI\u0001\u0016e\u0016\u001cx.\u001e:dK\u0006#GM]3tg\u0006kw.\u001e8u+\u0005\u0019\u0006\u0003B\u00196qQ\u0003\"\u0001H+\n\u0005Yk\"aA%oi\u0006\u0011\u0012M^1jY\u0006\u0014G.\u001a*fg>,(oY3t+\u0005I\u0006\u0003B\u00196qi\u0003B!M\u001b97B\u0011A\u0004X\u0005\u0003;v\u0011a\u0001R8vE2,\u0017aB1dcVL'/\u001a\u000b\u0003A\u000e\u0004\"\u0001H1\n\u0005\tl\"\u0001B+oSRDQ\u0001\u001a\u0005A\u0002A\n\u0001#Y:tS\u001etW\r\u001a*fg>,(oY3\u0002\u000fI,G.Z1tKR\u0011\u0001m\u001a\u0005\u0006I&\u0001\r\u0001M\u0001\u001fCN\u001c\u0018n\u001a8BI\u0012\u0014Xm]:fg\u000e+8\u000f^8n%\u0016\u001cx.\u001e:dKN$\"A[7\u0011\u0007qY\u0007'\u0003\u0002m;\t1q\n\u001d;j_:DQA\u001c\u0006A\u0002=\f1\u0002^1tWN+G\u000f\u0015:pMB\u0011\u0001o]\u0007\u0002c*\u0011!\u000fF\u0001\te\u0016\u001cx.\u001e:dK&\u0011A/\u001d\u0002\u0010%\u0016\u001cx.\u001e:dKB\u0013xNZ5mK\u0006AR\t_3dkR|'OU3t_V\u00148-Z:B[>,h\u000e^:\u0011\u0005\rc1c\u0001\u0007\u001cqB\u0011\u0011P`\u0007\u0002u*\u00111\u0010`\u0001\u0003S>T\u0011!`\u0001\u0005U\u00064\u0018-\u0003\u0002-uR\ta/A\u0003f[B$\u00180F\u0001C\u0003\u0015\t\u0007\u000f\u001d7z)\r\u0011\u0015\u0011\u0002\u0005\b\u0003\u0017y\u0001\u0019AA\u0007\u00035)\u00070Z2vi>\u0014\u0018J\u001c4pgB)\u0011'\u000e\u001d\u0002\u0010A\u00191)!\u0005\n\u0007\u0005M!C\u0001\u000bFq\u0016\u001cW\u000f^8s%\u0016\u001cx.\u001e:dK&sgm\\\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u00033\u0001B!a\u0007\u0002\"5\u0011\u0011Q\u0004\u0006\u0004\u0003?a\u0018\u0001\u00027b]\u001eLA!a\t\u0002\u001e\t1qJ\u00196fGR\u0004"
)
public class ExecutorResourcesAmounts implements Serializable {
   private Map resourceAddressAmount;
   private final Map resources;
   private final Map internalResources;
   private volatile boolean bitmap$0;

   public static ExecutorResourcesAmounts apply(final Map executorInfos) {
      return ExecutorResourcesAmounts$.MODULE$.apply(executorInfos);
   }

   public static ExecutorResourcesAmounts empty() {
      return ExecutorResourcesAmounts$.MODULE$.empty();
   }

   private Map resources() {
      return this.resources;
   }

   private Map internalResources() {
      return this.internalResources;
   }

   private Map resourceAddressAmount$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.resourceAddressAmount = (Map)this.internalResources().map((x0$1) -> {
               if (x0$1 != null) {
                  String rName = (String)x0$1._1();
                  HashMap addressMap = (HashMap)x0$1._2();
                  return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(rName), BoxesRunTime.boxToInteger(addressMap.size()));
               } else {
                  throw new MatchError(x0$1);
               }
            });
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.resourceAddressAmount;
   }

   public Map resourceAddressAmount() {
      return !this.bitmap$0 ? this.resourceAddressAmount$lzycompute() : this.resourceAddressAmount;
   }

   public Map availableResources() {
      return (Map)this.internalResources().map((x0$1) -> {
         if (x0$1 != null) {
            String rName = (String)x0$1._1();
            HashMap addressMap = (HashMap)x0$1._2();
            return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(rName), addressMap.map((x0$2) -> {
               if (x0$2 != null) {
                  String address = (String)x0$2._1();
                  long amount = x0$2._2$mcJ$sp();
                  return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(address), BoxesRunTime.boxToDouble(ResourceAmountUtils$.MODULE$.toFractionalResource(amount)));
               } else {
                  throw new MatchError(x0$2);
               }
            }).toMap(scala..less.colon.less..MODULE$.refl()));
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public void acquire(final Map assignedResource) {
      assignedResource.foreach((x0$1) -> {
         $anonfun$acquire$1(this, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   public void release(final Map assignedResource) {
      assignedResource.foreach((x0$1) -> {
         $anonfun$release$1(this, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   public Option assignAddressesCustomResources(final ResourceProfile taskSetProf) {
      Object var2 = new Object();

      Object var10000;
      try {
         Map tsResources = taskSetProf.getCustomTaskResources();
         if (tsResources.isEmpty()) {
            return new Some(scala.Predef..MODULE$.Map().empty());
         }

         HashMap allocatedAddresses = (HashMap)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
         tsResources.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$assignAddressesCustomResources$1(check$ifrefutable$1))).foreach((x$1) -> {
            if (x$1 != null) {
               String rName = (String)x$1._1();
               TaskResourceRequest taskReqs = (TaskResourceRequest)x$1._2();
               DoubleRef taskAmount = DoubleRef.create(taskReqs.amount());
               Option var10 = this.internalResources().get(rName);
               if (var10 instanceof Some) {
                  Some var11 = (Some)var10;
                  HashMap addressesAmountMap = (HashMap)var11.value();
                  HashMap allocatedAddressesMap = (HashMap)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
                  Seq addresses = (Seq)addressesAmountMap.keys().toSeq().sorted(scala.math.Ordering.String..MODULE$);
                  if (taskAmount.elem >= (double)1.0F) {
                     addresses.withFilter((address) -> BoxesRunTime.boxToBoolean($anonfun$assignAddressesCustomResources$3(taskAmount, address))).foreach((address) -> {
                        $anonfun$assignAddressesCustomResources$4(addressesAmountMap, taskAmount, allocatedAddressesMap, address);
                        return BoxedUnit.UNIT;
                     });
                  } else if (taskAmount.elem > (double)0.0F) {
                     long internalTaskAmount = ResourceAmountUtils$.MODULE$.toInternalResource(taskAmount.elem);
                     addresses.withFilter((address) -> BoxesRunTime.boxToBoolean($anonfun$assignAddressesCustomResources$5(taskAmount, address))).foreach((address) -> {
                        $anonfun$assignAddressesCustomResources$6(addressesAmountMap, internalTaskAmount, allocatedAddressesMap, taskAmount, address);
                        return BoxedUnit.UNIT;
                     });
                  }

                  if (taskAmount.elem == (double)0 && allocatedAddressesMap.size() > 0) {
                     return allocatedAddresses.put(rName, allocatedAddressesMap.toMap(scala..less.colon.less..MODULE$.refl()));
                  } else {
                     throw new NonLocalReturnControl(var2, scala.None..MODULE$);
                  }
               } else if (scala.None..MODULE$.equals(var10)) {
                  throw new NonLocalReturnControl(var2, scala.None..MODULE$);
               } else {
                  throw new MatchError(var10);
               }
            } else {
               throw new MatchError(x$1);
            }
         });
         var10000 = new Some(allocatedAddresses.toMap(scala..less.colon.less..MODULE$.refl()));
      } catch (NonLocalReturnControl var6) {
         if (var6.key() != var2) {
            throw var6;
         }

         var10000 = (Option)var6.value();
      }

      return (Option)var10000;
   }

   // $FF: synthetic method
   public static final void $anonfun$acquire$3(final ExecutorResourcesAmounts $this, final HashMap availableResourceAmounts$1, final String rName$1, final Tuple2 x0$2) {
      if (x0$2 != null) {
         String address = (String)x0$2._1();
         long amount = x0$2._2$mcJ$sp();
         long prevInternalTotalAmount = BoxesRunTime.unboxToLong(availableResourceAmounts$1.getOrElse(address, () -> {
            throw new SparkException("Try to acquire an address that doesn't exist. " + rName$1 + " address " + address + " doesn't exist.");
         }));
         long left = prevInternalTotalAmount - amount;
         if (left < 0L) {
            throw new SparkException("The total amount " + ResourceAmountUtils$.MODULE$.toFractionalResource(left) + " after acquiring " + rName$1 + " address " + address + " should be >= 0");
         } else {
            ((HashMap)$this.internalResources().apply(rName$1)).update(address, BoxesRunTime.boxToLong(left));
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$acquire$1(final ExecutorResourcesAmounts $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String rName = (String)x0$1._1();
         Map taskResAmounts = (Map)x0$1._2();
         HashMap availableResourceAmounts = (HashMap)$this.internalResources().getOrElse(rName, () -> {
            throw new SparkException("Try to acquire an address from " + rName + " that doesn't exist");
         });
         taskResAmounts.foreach((x0$2) -> {
            $anonfun$acquire$3($this, availableResourceAmounts, rName, x0$2);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$release$3(final ExecutorResourcesAmounts $this, final HashMap availableResourceAmounts$2, final String rName$2, final Tuple2 x0$2) {
      if (x0$2 != null) {
         String address = (String)x0$2._1();
         long amount = x0$2._2$mcJ$sp();
         long prevInternalTotalAmount = BoxesRunTime.unboxToLong(availableResourceAmounts$2.getOrElse(address, () -> {
            throw new SparkException("Try to release an address that is not assigned. " + rName$2 + " address " + address + " is not assigned.");
         }));
         long total = prevInternalTotalAmount + amount;
         if (total > ResourceAmountUtils$.MODULE$.ONE_ENTIRE_RESOURCE()) {
            throw new SparkException("The total amount " + ResourceAmountUtils$.MODULE$.toFractionalResource(total) + " after releasing " + rName$2 + " address " + address + " should be <= 1.0");
         } else {
            ((HashMap)$this.internalResources().apply(rName$2)).update(address, BoxesRunTime.boxToLong(total));
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$release$1(final ExecutorResourcesAmounts $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String rName = (String)x0$1._1();
         Map taskResAmounts = (Map)x0$1._2();
         HashMap availableResourceAmounts = (HashMap)$this.internalResources().getOrElse(rName, () -> {
            throw new SparkException("Try to release an address from " + rName + " that doesn't exist");
         });
         taskResAmounts.foreach((x0$2) -> {
            $anonfun$release$3($this, availableResourceAmounts, rName, x0$2);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$assignAddressesCustomResources$1(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$assignAddressesCustomResources$3(final DoubleRef taskAmount$1, final String address) {
      return taskAmount$1.elem > (double)0;
   }

   // $FF: synthetic method
   public static final void $anonfun$assignAddressesCustomResources$4(final HashMap addressesAmountMap$1, final DoubleRef taskAmount$1, final HashMap allocatedAddressesMap$1, final String address) {
      if (ResourceAmountUtils$.MODULE$.isOneEntireResource(BoxesRunTime.unboxToLong(addressesAmountMap$1.apply(address)))) {
         --taskAmount$1.elem;
         allocatedAddressesMap$1.update(address, BoxesRunTime.boxToLong(ResourceAmountUtils$.MODULE$.ONE_ENTIRE_RESOURCE()));
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$assignAddressesCustomResources$5(final DoubleRef taskAmount$1, final String address) {
      return taskAmount$1.elem > (double)0;
   }

   // $FF: synthetic method
   public static final void $anonfun$assignAddressesCustomResources$6(final HashMap addressesAmountMap$1, final long internalTaskAmount$1, final HashMap allocatedAddressesMap$1, final DoubleRef taskAmount$1, final String address) {
      if (BoxesRunTime.unboxToLong(addressesAmountMap$1.apply(address)) >= internalTaskAmount$1) {
         allocatedAddressesMap$1.update(address, BoxesRunTime.boxToLong(internalTaskAmount$1));
         taskAmount$1.elem = (double)0.0F;
      }
   }

   public ExecutorResourcesAmounts(final Map resources) {
      this.resources = resources;
      this.internalResources = (Map)resources.map((x0$1) -> {
         if (x0$1 != null) {
            String rName = (String)x0$1._1();
            Map addressAmounts = (Map)x0$1._2();
            return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(rName), scala.collection.mutable.HashMap..MODULE$.apply(addressAmounts.toSeq()));
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

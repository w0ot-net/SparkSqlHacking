package org.apache.spark.deploy.master;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.resource.ResourceAllocator;
import org.apache.spark.resource.ResourceAmountUtils$;
import org.apache.spark.resource.ResourceInformation;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055e!\u0002\u000f\u001e\u0001\u0006:\u0003\u0002\u0003#\u0001\u0005+\u0007I\u0011A#\t\u00119\u0003!\u0011#Q\u0001\n\u0019C\u0001b\u0014\u0001\u0003\u0016\u0004%\t\u0001\u0015\u0005\t)\u0002\u0011\t\u0012)A\u0005#\")Q\u000b\u0001C\u0001-\")1\f\u0001C)\u000b\")A\f\u0001C)!\")Q\f\u0001C\u0001=\"9q\rAA\u0001\n\u0003A\u0007bB6\u0001#\u0003%\t\u0001\u001c\u0005\bo\u0002\t\n\u0011\"\u0001y\u0011\u001dQ\b!!A\u0005BmD\u0011\"a\u0002\u0001\u0003\u0003%\t!!\u0003\t\u0013\u0005-\u0001!!A\u0005\u0002\u00055\u0001\"CA\r\u0001\u0005\u0005I\u0011IA\u000e\u0011%\tI\u0003AA\u0001\n\u0003\tY\u0003C\u0005\u00026\u0001\t\t\u0011\"\u0011\u00028!I\u00111\b\u0001\u0002\u0002\u0013\u0005\u0013Q\b\u0005\n\u0003\u007f\u0001\u0011\u0011!C!\u0003\u0003B\u0011\"a\u0011\u0001\u0003\u0003%\t%!\u0012\b\u0015\u0005%S$!A\t\u0002\u0005\nYEB\u0005\u001d;\u0005\u0005\t\u0012A\u0011\u0002N!1QK\u0006C\u0001\u0003KB\u0011\"a\u0010\u0017\u0003\u0003%)%!\u0011\t\u0013\u0005\u001dd#!A\u0005\u0002\u0006%\u0004\"CA8-\u0005\u0005I\u0011QA9\u0011%\t\u0019IFA\u0001\n\u0013\t)I\u0001\nX_J\\WM\u001d*fg>,(oY3J]\u001a|'B\u0001\u0010 \u0003\u0019i\u0017m\u001d;fe*\u0011\u0001%I\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005\t\u001a\u0013!B:qCJ\\'B\u0001\u0013&\u0003\u0019\t\u0007/Y2iK*\ta%A\u0002pe\u001e\u001cR\u0001\u0001\u0015/w\u0005\u0003\"!\u000b\u0017\u000e\u0003)R\u0011aK\u0001\u0006g\u000e\fG.Y\u0005\u0003[)\u0012a!\u00118z%\u00164\u0007CA\u00189\u001d\t\u0001dG\u0004\u00022k5\t!G\u0003\u00024i\u00051AH]8piz\u001a\u0001!C\u0001,\u0013\t9$&A\u0004qC\u000e\\\u0017mZ3\n\u0005eR$\u0001D*fe&\fG.\u001b>bE2,'BA\u001c+!\tat(D\u0001>\u0015\tq\u0014%\u0001\u0005sKN|WO]2f\u0013\t\u0001UHA\tSKN|WO]2f\u00032dwnY1u_J\u0004\"!\u000b\"\n\u0005\rS#a\u0002)s_\u0012,8\r^\u0001\u0005]\u0006lW-F\u0001G!\t95J\u0004\u0002I\u0013B\u0011\u0011GK\u0005\u0003\u0015*\na\u0001\u0015:fI\u00164\u0017B\u0001'N\u0005\u0019\u0019FO]5oO*\u0011!JK\u0001\u0006]\u0006lW\rI\u0001\nC\u0012$'/Z:tKN,\u0012!\u0015\t\u0004_I3\u0015BA*;\u0005\r\u0019V-]\u0001\u000bC\u0012$'/Z:tKN\u0004\u0013A\u0002\u001fj]&$h\bF\u0002X3j\u0003\"\u0001\u0017\u0001\u000e\u0003uAQ\u0001R\u0003A\u0002\u0019CQaT\u0003A\u0002E\u000bAB]3t_V\u00148-\u001a(b[\u0016\f\u0011C]3t_V\u00148-Z!eIJ,7o]3t\u0003\u001d\t7-];je\u0016$\"a\u00182\u0011\u0005q\u0002\u0017BA1>\u0005M\u0011Vm]8ve\u000e,\u0017J\u001c4pe6\fG/[8o\u0011\u0015\u0019\u0007\u00021\u0001e\u0003\u0019\tWn\\;oiB\u0011\u0011&Z\u0005\u0003M*\u00121!\u00138u\u0003\u0011\u0019w\u000e]=\u0015\u0007]K'\u000eC\u0004E\u0013A\u0005\t\u0019\u0001$\t\u000f=K\u0001\u0013!a\u0001#\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#A7+\u0005\u0019s7&A8\u0011\u0005A,X\"A9\u000b\u0005I\u001c\u0018!C;oG\",7m[3e\u0015\t!(&\u0001\u0006b]:|G/\u0019;j_:L!A^9\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003eT#!\u00158\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005a\bcA?\u0002\u00065\taPC\u0002\u0000\u0003\u0003\tA\u0001\\1oO*\u0011\u00111A\u0001\u0005U\u00064\u0018-\u0003\u0002M}\u0006a\u0001O]8ek\u000e$\u0018I]5usV\tA-\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005=\u0011Q\u0003\t\u0004S\u0005E\u0011bAA\nU\t\u0019\u0011I\\=\t\u0011\u0005]a\"!AA\u0002\u0011\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u000f!\u0019\ty\"!\n\u0002\u00105\u0011\u0011\u0011\u0005\u0006\u0004\u0003GQ\u0013AC2pY2,7\r^5p]&!\u0011qEA\u0011\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u00055\u00121\u0007\t\u0004S\u0005=\u0012bAA\u0019U\t9!i\\8mK\u0006t\u0007\"CA\f!\u0005\u0005\t\u0019AA\b\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007q\fI\u0004\u0003\u0005\u0002\u0018E\t\t\u00111\u0001e\u0003!A\u0017m\u001d5D_\u0012,G#\u00013\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012\u0001`\u0001\u0007KF,\u0018\r\\:\u0015\t\u00055\u0012q\t\u0005\n\u0003/!\u0012\u0011!a\u0001\u0003\u001f\t!cV8sW\u0016\u0014(+Z:pkJ\u001cW-\u00138g_B\u0011\u0001LF\n\u0006-\u0005=\u00131\f\t\b\u0003#\n9FR)X\u001b\t\t\u0019FC\u0002\u0002V)\nqA];oi&lW-\u0003\u0003\u0002Z\u0005M#!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeA!\u0011QLA2\u001b\t\tyF\u0003\u0003\u0002b\u0005\u0005\u0011AA5p\u0013\rI\u0014q\f\u000b\u0003\u0003\u0017\nQ!\u00199qYf$RaVA6\u0003[BQ\u0001R\rA\u0002\u0019CQaT\rA\u0002E\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002t\u0005}\u0004#B\u0015\u0002v\u0005e\u0014bAA<U\t1q\n\u001d;j_:\u0004R!KA>\rFK1!! +\u0005\u0019!V\u000f\u001d7fe!A\u0011\u0011\u0011\u000e\u0002\u0002\u0003\u0007q+A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\"\u0011\u0007u\fI)C\u0002\u0002\fz\u0014aa\u00142kK\u000e$\b"
)
public class WorkerResourceInfo implements Serializable, ResourceAllocator, Product {
   private final String name;
   private final Seq addresses;
   private HashMap org$apache$spark$resource$ResourceAllocator$$addressAvailabilityMap;
   private volatile boolean bitmap$0;

   public static Option unapply(final WorkerResourceInfo x$0) {
      return WorkerResourceInfo$.MODULE$.unapply(x$0);
   }

   public static WorkerResourceInfo apply(final String name, final Seq addresses) {
      return WorkerResourceInfo$.MODULE$.apply(name, addresses);
   }

   public static Function1 tupled() {
      return WorkerResourceInfo$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return WorkerResourceInfo$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Map resourcesAmounts() {
      return ResourceAllocator.resourcesAmounts$(this);
   }

   public Seq availableAddrs() {
      return ResourceAllocator.availableAddrs$(this);
   }

   public Seq assignedAddrs() {
      return ResourceAllocator.assignedAddrs$(this);
   }

   public void acquire(final Map addressesAmounts) {
      ResourceAllocator.acquire$(this, addressesAmounts);
   }

   public void release(final Map addressesAmounts) {
      ResourceAllocator.release$(this, addressesAmounts);
   }

   private HashMap org$apache$spark$resource$ResourceAllocator$$addressAvailabilityMap$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.org$apache$spark$resource$ResourceAllocator$$addressAvailabilityMap = ResourceAllocator.org$apache$spark$resource$ResourceAllocator$$addressAvailabilityMap$(this);
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.org$apache$spark$resource$ResourceAllocator$$addressAvailabilityMap;
   }

   public HashMap org$apache$spark$resource$ResourceAllocator$$addressAvailabilityMap() {
      return !this.bitmap$0 ? this.org$apache$spark$resource$ResourceAllocator$$addressAvailabilityMap$lzycompute() : this.org$apache$spark$resource$ResourceAllocator$$addressAvailabilityMap;
   }

   public String name() {
      return this.name;
   }

   public Seq addresses() {
      return this.addresses;
   }

   public String resourceName() {
      return this.name();
   }

   public Seq resourceAddresses() {
      return this.addresses();
   }

   public ResourceInformation acquire(final int amount) {
      Seq addresses = (Seq)this.availableAddrs().take(amount);
      .MODULE$.assert(addresses.length() == amount);
      this.acquire(((IterableOnceOps)addresses.map((addr) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(addr), BoxesRunTime.boxToLong(ResourceAmountUtils$.MODULE$.ONE_ENTIRE_RESOURCE())))).toMap(scala..less.colon.less..MODULE$.refl()));
      return new ResourceInformation(this.resourceName(), (String[])addresses.toArray(scala.reflect.ClassTag..MODULE$.apply(String.class)));
   }

   public WorkerResourceInfo copy(final String name, final Seq addresses) {
      return new WorkerResourceInfo(name, addresses);
   }

   public String copy$default$1() {
      return this.name();
   }

   public Seq copy$default$2() {
      return this.addresses();
   }

   public String productPrefix() {
      return "WorkerResourceInfo";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.name();
         }
         case 1 -> {
            return this.addresses();
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
      return x$1 instanceof WorkerResourceInfo;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "name";
         }
         case 1 -> {
            return "addresses";
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
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof WorkerResourceInfo) {
               label48: {
                  WorkerResourceInfo var4 = (WorkerResourceInfo)x$1;
                  String var10000 = this.name();
                  String var5 = var4.name();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  Seq var7 = this.addresses();
                  Seq var6 = var4.addresses();
                  if (var7 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var7.equals(var6)) {
                     break label48;
                  }

                  if (var4.canEqual(this)) {
                     break label55;
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public WorkerResourceInfo(final String name, final Seq addresses) {
      this.name = name;
      this.addresses = addresses;
      ResourceAllocator.$init$(this);
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}

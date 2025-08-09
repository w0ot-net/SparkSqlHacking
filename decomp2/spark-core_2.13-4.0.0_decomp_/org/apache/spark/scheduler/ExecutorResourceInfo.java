package org.apache.spark.scheduler;

import org.apache.spark.resource.ResourceAllocator;
import org.apache.spark.resource.ResourceInformation;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00113Qa\u0002\u0005\u0001\u0015AA\u0011B\u0007\u0001\u0003\u0002\u0003\u0006I\u0001H\u0015\t\u0011)\u0002!\u0011!Q\u0001\n-BQ\u0001\u000e\u0001\u0005\u0002UBQA\u000f\u0001\u0005RmBQ\u0001\u0010\u0001\u0005RuBQA\u0010\u0001\u0005\u0002}\u0012A#\u0012=fGV$xN\u001d*fg>,(oY3J]\u001a|'BA\u0005\u000b\u0003%\u00198\r[3ek2,'O\u0003\u0002\f\u0019\u0005)1\u000f]1sW*\u0011QBD\u0001\u0007CB\f7\r[3\u000b\u0003=\t1a\u001c:h'\r\u0001\u0011c\u0006\t\u0003%Ui\u0011a\u0005\u0006\u0003))\t\u0001B]3t_V\u00148-Z\u0005\u0003-M\u00111CU3t_V\u00148-Z%oM>\u0014X.\u0019;j_:\u0004\"A\u0005\r\n\u0005e\u0019\"!\u0005*fg>,(oY3BY2|7-\u0019;pe\u0006!a.Y7f\u0007\u0001\u0001\"!\b\u0014\u000f\u0005y!\u0003CA\u0010#\u001b\u0005\u0001#BA\u0011\u001c\u0003\u0019a$o\\8u})\t1%A\u0003tG\u0006d\u0017-\u0003\u0002&E\u00051\u0001K]3eK\u001aL!a\n\u0015\u0003\rM#(/\u001b8h\u0015\t)#%\u0003\u0002\u001b+\u0005I\u0011\r\u001a3sKN\u001cXm\u001d\t\u0004YEbbBA\u00170\u001d\tyb&C\u0001$\u0013\t\u0001$%A\u0004qC\u000e\\\u0017mZ3\n\u0005I\u001a$aA*fc*\u0011\u0001GI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007YB\u0014\b\u0005\u00028\u00015\t\u0001\u0002C\u0003\u001b\u0007\u0001\u0007A\u0004C\u0003+\u0007\u0001\u00071&\u0001\u0007sKN|WO]2f\u001d\u0006lW-F\u0001\u001d\u0003E\u0011Xm]8ve\u000e,\u0017\t\u001a3sKN\u001cXm]\u000b\u0002W\u0005!Bo\u001c;bY\u0006#GM]3tg\u0016\u001c\u0018)\\8v]R,\u0012\u0001\u0011\t\u0003\u0003\nk\u0011AI\u0005\u0003\u0007\n\u00121!\u00138u\u0001"
)
public class ExecutorResourceInfo extends ResourceInformation implements ResourceAllocator {
   private final Seq addresses;
   private HashMap org$apache$spark$resource$ResourceAllocator$$addressAvailabilityMap;
   private volatile boolean bitmap$0;

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

   public String resourceName() {
      return super.name();
   }

   public Seq resourceAddresses() {
      return this.addresses;
   }

   public int totalAddressesAmount() {
      return this.addresses.length();
   }

   public ExecutorResourceInfo(final String name, final Seq addresses) {
      super(name, (String[])addresses.toArray(.MODULE$.apply(String.class)));
      this.addresses = addresses;
      ResourceAllocator.$init$(this);
   }
}

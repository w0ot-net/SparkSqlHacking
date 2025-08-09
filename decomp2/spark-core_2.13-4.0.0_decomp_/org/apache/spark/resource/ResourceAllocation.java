package org.apache.spark.resource;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]d!\u0002\u000e\u001c\u0001v\u0019\u0003\u0002\u0003\u001e\u0001\u0005+\u0007I\u0011A\u001e\t\u0011\u0001\u0003!\u0011#Q\u0001\nqB\u0001\"\u0011\u0001\u0003\u0016\u0004%\tA\u0011\u0005\t\u001d\u0002\u0011\t\u0012)A\u0005\u0007\")q\n\u0001C\u0001!\")A\u000b\u0001C\u0001+\"9\u0011\fAA\u0001\n\u0003Q\u0006bB/\u0001#\u0003%\tA\u0018\u0005\bS\u0002\t\n\u0011\"\u0001k\u0011\u001da\u0007!!A\u0005B5Dq!\u001e\u0001\u0002\u0002\u0013\u0005a\u000fC\u0004{\u0001\u0005\u0005I\u0011A>\t\u0013\u0005\r\u0001!!A\u0005B\u0005\u0015\u0001\"CA\n\u0001\u0005\u0005I\u0011AA\u000b\u0011%\ty\u0002AA\u0001\n\u0003\n\t\u0003C\u0005\u0002&\u0001\t\t\u0011\"\u0011\u0002(!I\u0011\u0011\u0006\u0001\u0002\u0002\u0013\u0005\u00131\u0006\u0005\n\u0003[\u0001\u0011\u0011!C!\u0003_9!\"a\r\u001c\u0003\u0003E\t!HA\u001b\r%Q2$!A\t\u0002u\t9\u0004\u0003\u0004P)\u0011\u0005\u0011q\n\u0005\n\u0003S!\u0012\u0011!C#\u0003WA\u0011\"!\u0015\u0015\u0003\u0003%\t)a\u0015\t\u0013\u0005eC#!A\u0005\u0002\u0006m\u0003\"CA7)\u0005\u0005I\u0011BA8\u0005I\u0011Vm]8ve\u000e,\u0017\t\u001c7pG\u0006$\u0018n\u001c8\u000b\u0005qi\u0012\u0001\u0003:fg>,(oY3\u000b\u0005yy\u0012!B:qCJ\\'B\u0001\u0011\"\u0003\u0019\t\u0007/Y2iK*\t!%A\u0002pe\u001e\u001cB\u0001\u0001\u0013+[A\u0011Q\u0005K\u0007\u0002M)\tq%A\u0003tG\u0006d\u0017-\u0003\u0002*M\t1\u0011I\\=SK\u001a\u0004\"!J\u0016\n\u000512#a\u0002)s_\u0012,8\r\u001e\t\u0003]]r!aL\u001b\u000f\u0005A\"T\"A\u0019\u000b\u0005I\u001a\u0014A\u0002\u001fs_>$hh\u0001\u0001\n\u0003\u001dJ!A\u000e\u0014\u0002\u000fA\f7m[1hK&\u0011\u0001(\u000f\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003m\u0019\n!!\u001b3\u0016\u0003q\u0002\"!\u0010 \u000e\u0003mI!aP\u000e\u0003\u0015I+7o\\;sG\u0016LE)A\u0002jI\u0002\n\u0011\"\u00193ee\u0016\u001c8/Z:\u0016\u0003\r\u00032A\f#G\u0013\t)\u0015HA\u0002TKF\u0004\"aR&\u000f\u0005!K\u0005C\u0001\u0019'\u0013\tQe%\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u00196\u0013aa\u0015;sS:<'B\u0001&'\u0003)\tG\r\u001a:fgN,7\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007E\u00136\u000b\u0005\u0002>\u0001!)!(\u0002a\u0001y!)\u0011)\u0002a\u0001\u0007\u0006)Bo\u001c*fg>,(oY3J]\u001a|'/\\1uS>tW#\u0001,\u0011\u0005u:\u0016B\u0001-\u001c\u0005M\u0011Vm]8ve\u000e,\u0017J\u001c4pe6\fG/[8o\u0003\u0011\u0019w\u000e]=\u0015\u0007E[F\fC\u0004;\u000fA\u0005\t\u0019\u0001\u001f\t\u000f\u0005;\u0001\u0013!a\u0001\u0007\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#A0+\u0005q\u00027&A1\u0011\u0005\t<W\"A2\u000b\u0005\u0011,\u0017!C;oG\",7m[3e\u0015\t1g%\u0001\u0006b]:|G/\u0019;j_:L!\u0001[2\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003-T#a\u00111\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005q\u0007CA8u\u001b\u0005\u0001(BA9s\u0003\u0011a\u0017M\\4\u000b\u0003M\fAA[1wC&\u0011A\n]\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002oB\u0011Q\u0005_\u0005\u0003s\u001a\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"\u0001`@\u0011\u0005\u0015j\u0018B\u0001@'\u0005\r\te.\u001f\u0005\t\u0003\u0003a\u0011\u0011!a\u0001o\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a\u0002\u0011\u000b\u0005%\u0011q\u0002?\u000e\u0005\u0005-!bAA\u0007M\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005E\u00111\u0002\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\u0018\u0005u\u0001cA\u0013\u0002\u001a%\u0019\u00111\u0004\u0014\u0003\u000f\t{w\u000e\\3b]\"A\u0011\u0011\u0001\b\u0002\u0002\u0003\u0007A0\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,Gc\u00018\u0002$!A\u0011\u0011A\b\u0002\u0002\u0003\u0007q/\u0001\u0005iCND7i\u001c3f)\u00059\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u00039\fa!Z9vC2\u001cH\u0003BA\f\u0003cA\u0001\"!\u0001\u0013\u0003\u0003\u0005\r\u0001`\u0001\u0013%\u0016\u001cx.\u001e:dK\u0006cGn\\2bi&|g\u000e\u0005\u0002>)M)A#!\u000f\u0002FA9\u00111HA!y\r\u000bVBAA\u001f\u0015\r\tyDJ\u0001\beVtG/[7f\u0013\u0011\t\u0019%!\u0010\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t'\u0007\u0005\u0003\u0002H\u00055SBAA%\u0015\r\tYE]\u0001\u0003S>L1\u0001OA%)\t\t)$A\u0003baBd\u0017\u0010F\u0003R\u0003+\n9\u0006C\u0003;/\u0001\u0007A\bC\u0003B/\u0001\u00071)A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005u\u0013\u0011\u000e\t\u0006K\u0005}\u00131M\u0005\u0004\u0003C2#AB(qi&|g\u000eE\u0003&\u0003Kb4)C\u0002\u0002h\u0019\u0012a\u0001V;qY\u0016\u0014\u0004\u0002CA61\u0005\u0005\t\u0019A)\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002rA\u0019q.a\u001d\n\u0007\u0005U\u0004O\u0001\u0004PE*,7\r\u001e"
)
public class ResourceAllocation implements Product, Serializable {
   private final ResourceID id;
   private final Seq addresses;

   public static Option unapply(final ResourceAllocation x$0) {
      return ResourceAllocation$.MODULE$.unapply(x$0);
   }

   public static ResourceAllocation apply(final ResourceID id, final Seq addresses) {
      return ResourceAllocation$.MODULE$.apply(id, addresses);
   }

   public static Function1 tupled() {
      return ResourceAllocation$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ResourceAllocation$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public ResourceID id() {
      return this.id;
   }

   public Seq addresses() {
      return this.addresses;
   }

   public ResourceInformation toResourceInformation() {
      return new ResourceInformation(this.id().resourceName(), (String[])this.addresses().toArray(.MODULE$.apply(String.class)));
   }

   public ResourceAllocation copy(final ResourceID id, final Seq addresses) {
      return new ResourceAllocation(id, addresses);
   }

   public ResourceID copy$default$1() {
      return this.id();
   }

   public Seq copy$default$2() {
      return this.addresses();
   }

   public String productPrefix() {
      return "ResourceAllocation";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.id();
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
      return x$1 instanceof ResourceAllocation;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "id";
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
            if (x$1 instanceof ResourceAllocation) {
               label48: {
                  ResourceAllocation var4 = (ResourceAllocation)x$1;
                  ResourceID var10000 = this.id();
                  ResourceID var5 = var4.id();
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

   public ResourceAllocation(final ResourceID id, final Seq addresses) {
      this.id = id;
      this.addresses = addresses;
      Product.$init$(this);
   }
}

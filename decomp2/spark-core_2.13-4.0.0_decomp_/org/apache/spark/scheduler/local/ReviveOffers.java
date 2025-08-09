package org.apache.spark.scheduler.local;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\ra\u0001B\n\u0015\t~AQ!\u000e\u0001\u0005\u0002YBq!\u000f\u0001\u0002\u0002\u0013\u0005a\u0007C\u0004;\u0001\u0005\u0005I\u0011I\u001e\t\u000f\u0011\u0003\u0011\u0011!C\u0001\u000b\"9\u0011\nAA\u0001\n\u0003Q\u0005b\u0002)\u0001\u0003\u0003%\t%\u0015\u0005\b1\u0002\t\t\u0011\"\u0001Z\u0011\u001dq\u0006!!A\u0005B}Cq!\u0019\u0001\u0002\u0002\u0013\u0005#\rC\u0004d\u0001\u0005\u0005I\u0011\t3\t\u000f\u0015\u0004\u0011\u0011!C!M\u001e9\u0001\u000eFA\u0001\u0012\u0013IgaB\n\u0015\u0003\u0003EIA\u001b\u0005\u0006k5!\tA\u001e\u0005\bG6\t\t\u0011\"\u0012e\u0011\u001d9X\"!A\u0005\u0002ZBq\u0001_\u0007\u0002\u0002\u0013\u0005\u0015\u0010C\u0004}\u001b\u0005\u0005I\u0011B?\u0003\u0019I+g/\u001b<f\u001f\u001a4WM]:\u000b\u0005U1\u0012!\u00027pG\u0006d'BA\f\u0019\u0003%\u00198\r[3ek2,'O\u0003\u0002\u001a5\u0005)1\u000f]1sW*\u00111\u0004H\u0001\u0007CB\f7\r[3\u000b\u0003u\t1a\u001c:h\u0007\u0001\u0019B\u0001\u0001\u0011'SA\u0011\u0011\u0005J\u0007\u0002E)\t1%A\u0003tG\u0006d\u0017-\u0003\u0002&E\t1\u0011I\\=SK\u001a\u0004\"!I\u0014\n\u0005!\u0012#a\u0002)s_\u0012,8\r\u001e\t\u0003UIr!a\u000b\u0019\u000f\u00051zS\"A\u0017\u000b\u00059r\u0012A\u0002\u001fs_>$h(C\u0001$\u0013\t\t$%A\u0004qC\u000e\\\u0017mZ3\n\u0005M\"$\u0001D*fe&\fG.\u001b>bE2,'BA\u0019#\u0003\u0019a\u0014N\\5u}Q\tq\u0007\u0005\u00029\u00015\tA#\u0001\u0003d_BL\u0018!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001=!\ti$)D\u0001?\u0015\ty\u0004)\u0001\u0003mC:<'\"A!\u0002\t)\fg/Y\u0005\u0003\u0007z\u0012aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#\u0001$\u0011\u0005\u0005:\u0015B\u0001%#\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\tYe\n\u0005\u0002\"\u0019&\u0011QJ\t\u0002\u0004\u0003:L\bbB(\u0006\u0003\u0003\u0005\rAR\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003I\u00032a\u0015,L\u001b\u0005!&BA+#\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003/R\u0013\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0011!,\u0018\t\u0003CmK!\u0001\u0018\u0012\u0003\u000f\t{w\u000e\\3b]\"9qjBA\u0001\u0002\u0004Y\u0015A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$\"\u0001\u00101\t\u000f=C\u0011\u0011!a\u0001\r\u0006A\u0001.Y:i\u0007>$W\rF\u0001G\u0003!!xn\u0015;sS:<G#\u0001\u001f\u0002\r\u0015\fX/\u00197t)\tQv\rC\u0004P\u0017\u0005\u0005\t\u0019A&\u0002\u0019I+g/\u001b<f\u001f\u001a4WM]:\u0011\u0005aj1cA\u0007lcB\u0019An\\\u001c\u000e\u00035T!A\u001c\u0012\u0002\u000fI,h\u000e^5nK&\u0011\u0001/\u001c\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0004\u0004C\u0001:v\u001b\u0005\u0019(B\u0001;A\u0003\tIw.\u0003\u00024gR\t\u0011.A\u0003baBd\u00170A\u0004v]\u0006\u0004\b\u000f\\=\u0015\u0005iS\bbB>\u0012\u0003\u0003\u0005\raN\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,G#\u0001@\u0011\u0005uz\u0018bAA\u0001}\t1qJ\u00196fGR\u0004"
)
public class ReviveOffers implements Product, Serializable {
   public static boolean unapply(final ReviveOffers x$0) {
      return ReviveOffers$.MODULE$.unapply(x$0);
   }

   public static ReviveOffers apply() {
      return ReviveOffers$.MODULE$.apply();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public ReviveOffers copy() {
      return new ReviveOffers();
   }

   public String productPrefix() {
      return "ReviveOffers";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      return Statics.ioobe(x$1);
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ReviveOffers;
   }

   public String productElementName(final int x$1) {
      return (String)Statics.ioobe(x$1);
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      return x$1 instanceof ReviveOffers && ((ReviveOffers)x$1).canEqual(this);
   }

   public ReviveOffers() {
      Product.$init$(this);
   }
}

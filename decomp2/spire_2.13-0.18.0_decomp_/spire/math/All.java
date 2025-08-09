package spire.math;

import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import spire.math.interval.Unbound;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mb\u0001\u0002\u000b\u0016\u0001jAa!\u0010\u0001\u0005\u0002]q\u0004\"\u0002!\u0001\t\u0003\t\u0005\"\u0002%\u0001\t\u0003\t\u0005bB%\u0001\u0003\u0003%\tA\u0013\u0005\b\u001f\u0002\t\t\u0011\"\u0011Q\u0011\u001dI\u0006!!A\u0005\u0002iCqA\u0018\u0001\u0002\u0002\u0013\u0005q\fC\u0004c\u0001\u0005\u0005I\u0011I2\t\u000f)\u0004\u0011\u0011!C\u0001W\"9\u0001\u000fAA\u0001\n\u0003\n\bbB:\u0001\u0003\u0003%\t\u0005\u001e\u0005\bk\u0002\t\t\u0011\"\u0011w\u000f\u001dAX#!A\t\u0002e4q\u0001F\u000b\u0002\u0002#\u0005!\u0010\u0003\u0004>\u001d\u0011\u0005\u0011q\u0001\u0005\n\u0003\u0013q\u0011\u0011!C#\u0003\u0017A\u0011\"!\u0004\u000f\u0003\u0003%\t)a\u0004\t\u0013\u0005ea\"!A\u0005\u0002\u0006m\u0001\"CA\u0015\u001d\u0005\u0005I\u0011BA\u0016\u0005\r\tE\u000e\u001c\u0006\u0003-]\tA!\\1uQ*\t\u0001$A\u0003ta&\u0014Xm\u0001\u0001\u0016\u0005m\u00113\u0003\u0002\u0001\u001d]E\u00022!\b\u0010!\u001b\u0005)\u0012BA\u0010\u0016\u0005!Ie\u000e^3sm\u0006d\u0007CA\u0011#\u0019\u0001!Qa\t\u0001C\u0002\u0011\u0012\u0011!Q\t\u0003K-\u0002\"AJ\u0015\u000e\u0003\u001dR\u0011\u0001K\u0001\u0006g\u000e\fG.Y\u0005\u0003U\u001d\u0012qAT8uQ&tw\r\u0005\u0002'Y%\u0011Qf\n\u0002\u0004\u0003:L\bC\u0001\u00140\u0013\t\u0001tEA\u0004Qe>$Wo\u0019;\u0011\u0005IRdBA\u001a9\u001d\t!t'D\u00016\u0015\t1\u0014$\u0001\u0004=e>|GOP\u0005\u0002Q%\u0011\u0011hJ\u0001\ba\u0006\u001c7.Y4f\u0013\tYDH\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002:O\u00051A(\u001b8jiz\"\u0012a\u0010\t\u0004;\u0001\u0001\u0013A\u00037po\u0016\u0014(i\\;oIV\t!\tE\u0002D\r\u0002j\u0011\u0001\u0012\u0006\u0003\u000bV\t\u0001\"\u001b8uKJ4\u0018\r\\\u0005\u0003\u000f\u0012\u0013q!\u00168c_VtG-\u0001\u0006vaB,'OQ8v]\u0012\fAaY8qsV\u00111J\u0014\u000b\u0002\u0019B\u0019Q\u0004A'\u0011\u0005\u0005rE!B\u0012\u0005\u0005\u0004!\u0013!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001R!\t\u0011v+D\u0001T\u0015\t!V+\u0001\u0003mC:<'\"\u0001,\u0002\t)\fg/Y\u0005\u00031N\u0013aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#A.\u0011\u0005\u0019b\u0016BA/(\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\tY\u0003\rC\u0004b\u000f\u0005\u0005\t\u0019A.\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005!\u0007cA3iW5\taM\u0003\u0002hO\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005%4'\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$\"\u0001\\8\u0011\u0005\u0019j\u0017B\u00018(\u0005\u001d\u0011un\u001c7fC:Dq!Y\u0005\u0002\u0002\u0003\u00071&\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,GCA)s\u0011\u001d\t'\"!AA\u0002m\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u00027\u00061Q-];bYN$\"\u0001\\<\t\u000f\u0005d\u0011\u0011!a\u0001W\u0005\u0019\u0011\t\u001c7\u0011\u0005uq1c\u0001\b|}B\u0011a\u0005`\u0005\u0003{\u001e\u0012a!\u00118z%\u00164\u0007cA@\u0002\u00065\u0011\u0011\u0011\u0001\u0006\u0004\u0003\u0007)\u0016AA5p\u0013\rY\u0014\u0011\u0001\u000b\u0002s\u0006AAo\\*ue&tw\rF\u0001R\u0003\u0015\t\u0007\u000f\u001d7z+\u0011\t\t\"a\u0006\u0015\u0005\u0005M\u0001\u0003B\u000f\u0001\u0003+\u00012!IA\f\t\u0015\u0019\u0013C1\u0001%\u0003\u001d)h.\u00199qYf,B!!\b\u0002(Q\u0019A.a\b\t\u0013\u0005\u0005\"#!AA\u0002\u0005\r\u0012a\u0001=%aA!Q\u0004AA\u0013!\r\t\u0013q\u0005\u0003\u0006GI\u0011\r\u0001J\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003[\u00012AUA\u0018\u0013\r\t\td\u0015\u0002\u0007\u001f\nTWm\u0019;"
)
public class All extends Interval implements Product {
   public static boolean unapply(final All x$0) {
      return All$.MODULE$.unapply(x$0);
   }

   public static All apply() {
      return All$.MODULE$.apply();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Unbound lowerBound() {
      return new Unbound();
   }

   public Unbound upperBound() {
      return new Unbound();
   }

   public All copy() {
      return new All();
   }

   public String productPrefix() {
      return "All";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      Object var2 = Statics.ioobe(x$1);
      return var2;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof All;
   }

   public String productElementName(final int x$1) {
      String var2 = (String)Statics.ioobe(x$1);
      return var2;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var2;
      if (x$1 instanceof All) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2 && ((All)x$1).canEqual(this);
   }

   public All() {
      Product.$init$(this);
   }
}

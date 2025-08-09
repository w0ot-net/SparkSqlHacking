package org.json4s;

import scala.Function1;
import scala.Option;
import scala.collection.Iterator;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uc\u0001B\u000e\u001d\u0001\u0006B\u0001\u0002\u000f\u0001\u0003\u0016\u0004%\t!\u000f\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005u!)1\t\u0001C\u0001\t\u0016!q\t\u0001\u0001;\u0011\u0015A\u0005\u0001\"\u0001:\u0011\u0015I\u0005\u0001\"\u0001K\u0011\u0015i\u0005\u0001\"\u0001O\u0011\u0015\u0001\u0006\u0001\"\u0001R\u0011\u001d\u0019\u0006!!A\u0005\u0002QCqA\u0016\u0001\u0012\u0002\u0013\u0005q\u000bC\u0004c\u0001\u0005\u0005I\u0011I2\t\u000f1\u0004\u0011\u0011!C\u0001[\"9\u0011\u000fAA\u0001\n\u0003\u0011\bb\u0002=\u0001\u0003\u0003%\t%\u001f\u0005\n\u0003\u0003\u0001\u0011\u0011!C\u0001\u0003\u0007A\u0011\"!\u0004\u0001\u0003\u0003%\t%a\u0004\t\u0013\u0005M\u0001!!A\u0005B\u0005U\u0001\"CA\f\u0001\u0005\u0005I\u0011IA\r\u0011%\tY\u0002AA\u0001\n\u0003\nibB\u0005\u0002\"q\t\t\u0011#\u0001\u0002$\u0019A1\u0004HA\u0001\u0012\u0003\t)\u0003\u0003\u0004D+\u0011\u0005\u0011Q\b\u0005\n\u0003/)\u0012\u0011!C#\u00033A\u0011\"a\u0010\u0016\u0003\u0003%\t)!\u0011\t\u0013\u0005\u0015S#!A\u0005\u0002\u0006\u001d\u0003\"CA*+\u0005\u0005I\u0011BA+\u0005\u0011Q5+\u001a;\u000b\u0005uq\u0012A\u00026t_:$4OC\u0001 \u0003\ry'oZ\u0002\u0001'\u0011\u0001!E\n\u0017\u0011\u0005\r\"S\"\u0001\u000f\n\u0005\u0015b\"A\u0002&WC2,X\r\u0005\u0002(U5\t\u0001FC\u0001*\u0003\u0015\u00198-\u00197b\u0013\tY\u0003FA\u0004Qe>$Wo\u0019;\u0011\u00055*dB\u0001\u00184\u001d\ty#'D\u00011\u0015\t\t\u0004%\u0001\u0004=e>|GOP\u0005\u0002S%\u0011A\u0007K\u0001\ba\u0006\u001c7.Y4f\u0013\t1tG\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00025Q\u0005\u00191/\u001a;\u0016\u0003i\u00022aO #\u001d\taT\b\u0005\u00020Q%\u0011a\bK\u0001\u0007!J,G-\u001a4\n\u0005\u0001\u000b%aA*fi*\u0011a\bK\u0001\u0005g\u0016$\b%\u0001\u0004=S:LGO\u0010\u000b\u0003\u000b\u001a\u0003\"a\t\u0001\t\u000ba\u001a\u0001\u0019\u0001\u001e\u0003\rY\u000bG.^3t\u0003\u00191\u0018\r\\;fg\u0006I\u0011N\u001c;feN,7\r\u001e\u000b\u0003\u000b.CQ\u0001\u0014\u0004A\u0002\u0015\u000b\u0011a\\\u0001\u0006k:LwN\u001c\u000b\u0003\u000b>CQ\u0001T\u0004A\u0002\u0015\u000b!\u0002Z5gM\u0016\u0014XM\\2f)\t)%\u000bC\u0003M\u0011\u0001\u0007Q)\u0001\u0003d_BLHCA#V\u0011\u001dA\u0014\u0002%AA\u0002i\nabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001YU\tQ\u0014lK\u0001[!\tY\u0006-D\u0001]\u0015\tif,A\u0005v]\u000eDWmY6fI*\u0011q\fK\u0001\u000bC:tw\u000e^1uS>t\u0017BA1]\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003\u0011\u0004\"!\u001a6\u000e\u0003\u0019T!a\u001a5\u0002\t1\fgn\u001a\u0006\u0002S\u0006!!.\u0019<b\u0013\tYgM\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002]B\u0011qe\\\u0005\u0003a\"\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"a\u001d<\u0011\u0005\u001d\"\u0018BA;)\u0005\r\te.\u001f\u0005\bo6\t\t\u00111\u0001o\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\t!\u0010E\u0002|}Nl\u0011\u0001 \u0006\u0003{\"\n!bY8mY\u0016\u001cG/[8o\u0013\tyHP\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u0003\u0003\u0017\u00012aJA\u0004\u0013\r\tI\u0001\u000b\u0002\b\u0005>|G.Z1o\u0011\u001d9x\"!AA\u0002M\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019A-!\u0005\t\u000f]\u0004\u0012\u0011!a\u0001]\u0006A\u0001.Y:i\u0007>$W\rF\u0001o\u0003!!xn\u0015;sS:<G#\u00013\u0002\r\u0015\fX/\u00197t)\u0011\t)!a\b\t\u000f]\u001c\u0012\u0011!a\u0001g\u0006!!jU3u!\t\u0019ScE\u0003\u0016\u0003O\t\u0019\u0004\u0005\u0004\u0002*\u0005=\"(R\u0007\u0003\u0003WQ1!!\f)\u0003\u001d\u0011XO\u001c;j[\u0016LA!!\r\u0002,\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0011\t\u0005U\u00121H\u0007\u0003\u0003oQ1!!\u000fi\u0003\tIw.C\u00027\u0003o!\"!a\t\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007\u0015\u000b\u0019\u0005C\u000391\u0001\u0007!(A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005%\u0013q\n\t\u0005O\u0005-#(C\u0002\u0002N!\u0012aa\u00149uS>t\u0007\u0002CA)3\u0005\u0005\t\u0019A#\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002XA\u0019Q-!\u0017\n\u0007\u0005mcM\u0001\u0004PE*,7\r\u001e"
)
public class JSet extends JValue {
   private final Set set;

   public static Option unapply(final JSet x$0) {
      return JSet$.MODULE$.unapply(x$0);
   }

   public static Function1 andThen(final Function1 g) {
      return JSet$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return JSet$.MODULE$.compose(g);
   }

   public Set set() {
      return this.set;
   }

   public Set values() {
      return this.set();
   }

   public JSet intersect(final JSet o) {
      return new JSet((Set)o.values().intersect(this.values()));
   }

   public JSet union(final JSet o) {
      return new JSet((Set)o.values().union(this.values()));
   }

   public JSet difference(final JSet o) {
      return new JSet((Set)this.values().diff(o.values()));
   }

   public JSet copy(final Set set) {
      return new JSet(set);
   }

   public Set copy$default$1() {
      return this.set();
   }

   public String productPrefix() {
      return "JSet";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.set();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof JSet;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "set";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var7;
      if (this != x$1) {
         label53: {
            boolean var2;
            if (x$1 instanceof JSet) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     JSet var4 = (JSet)x$1;
                     Set var10000 = this.set();
                     Set var5 = var4.set();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label35;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label35;
                     }

                     if (var4.canEqual(this)) {
                        var7 = true;
                        break label36;
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label53;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public JSet(final Set set) {
      this.set = set;
   }
}

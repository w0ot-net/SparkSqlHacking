package org.apache.spark.deploy.yarn;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rd!B\r\u001b\u0001j!\u0003\u0002C\u001e\u0001\u0005+\u0007I\u0011\u0001\u001f\t\u0011!\u0003!\u0011#Q\u0001\nuB\u0001\"\u0013\u0001\u0003\u0016\u0004%\t\u0001\u0010\u0005\t\u0015\u0002\u0011\t\u0012)A\u0005{!)1\n\u0001C\u0001\u0019\"9\u0011\u000bAA\u0001\n\u0003\u0011\u0006bB+\u0001#\u0003%\tA\u0016\u0005\bC\u0002\t\n\u0011\"\u0001W\u0011\u001d\u0011\u0007!!A\u0005B\rDqa\u001b\u0001\u0002\u0002\u0013\u0005A\u000eC\u0004q\u0001\u0005\u0005I\u0011A9\t\u000f]\u0004\u0011\u0011!C!q\"Aq\u0010AA\u0001\n\u0003\t\t\u0001C\u0005\u0002\f\u0001\t\t\u0011\"\u0011\u0002\u000e!I\u0011\u0011\u0003\u0001\u0002\u0002\u0013\u0005\u00131\u0003\u0005\n\u0003+\u0001\u0011\u0011!C!\u0003/A\u0011\"!\u0007\u0001\u0003\u0003%\t%a\u0007\b\u0015\u0005}!$!A\t\u0002i\t\tCB\u0005\u001a5\u0005\u0005\t\u0012\u0001\u000e\u0002$!11j\u0005C\u0001\u0003wA\u0011\"!\u0006\u0014\u0003\u0003%)%a\u0006\t\u0013\u0005u2#!A\u0005\u0002\u0006}\u0002\"CA#'\u0005\u0005I\u0011QA$\u0011%\tIfEA\u0001\n\u0013\tYF\u0001\u000fD_:$\u0018-\u001b8fe2{7-\u00197jif\u0004&/\u001a4fe\u0016t7-Z:\u000b\u0005ma\u0012\u0001B=be:T!!\b\u0010\u0002\r\u0011,\u0007\u000f\\8z\u0015\ty\u0002%A\u0003ta\u0006\u00148N\u0003\u0002\"E\u00051\u0011\r]1dQ\u0016T\u0011aI\u0001\u0004_J<7\u0003\u0002\u0001&W9\u0002\"AJ\u0015\u000e\u0003\u001dR\u0011\u0001K\u0001\u0006g\u000e\fG.Y\u0005\u0003U\u001d\u0012a!\u00118z%\u00164\u0007C\u0001\u0014-\u0013\tisEA\u0004Qe>$Wo\u0019;\u0011\u0005=BdB\u0001\u00197\u001d\t\tT'D\u00013\u0015\t\u0019D'\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005A\u0013BA\u001c(\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u000f\u001e\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005]:\u0013!\u00028pI\u0016\u001cX#A\u001f\u0011\u0007\u0019r\u0004)\u0003\u0002@O\t)\u0011I\u001d:bsB\u0011\u0011)\u0012\b\u0003\u0005\u000e\u0003\"!M\u0014\n\u0005\u0011;\u0013A\u0002)sK\u0012,g-\u0003\u0002G\u000f\n11\u000b\u001e:j]\u001eT!\u0001R\u0014\u0002\r9|G-Z:!\u0003\u0015\u0011\u0018mY6t\u0003\u0019\u0011\u0018mY6tA\u00051A(\u001b8jiz\"2!T(Q!\tq\u0005!D\u0001\u001b\u0011\u0015YT\u00011\u0001>\u0011\u0015IU\u00011\u0001>\u0003\u0011\u0019w\u000e]=\u0015\u00075\u001bF\u000bC\u0004<\rA\u0005\t\u0019A\u001f\t\u000f%3\u0001\u0013!a\u0001{\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#A,+\u0005uB6&A-\u0011\u0005i{V\"A.\u000b\u0005qk\u0016!C;oG\",7m[3e\u0015\tqv%\u0001\u0006b]:|G/\u0019;j_:L!\u0001Y.\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005!\u0007CA3k\u001b\u00051'BA4i\u0003\u0011a\u0017M\\4\u000b\u0003%\fAA[1wC&\u0011aIZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002[B\u0011aE\\\u0005\u0003_\u001e\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"A];\u0011\u0005\u0019\u001a\u0018B\u0001;(\u0005\r\te.\u001f\u0005\bm.\t\t\u00111\u0001n\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\t\u0011\u0010E\u0002{{Jl\u0011a\u001f\u0006\u0003y\u001e\n!bY8mY\u0016\u001cG/[8o\u0013\tq8P\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u0002\u0003\u0013\u00012AJA\u0003\u0013\r\t9a\n\u0002\b\u0005>|G.Z1o\u0011\u001d1X\"!AA\u0002I\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019A-a\u0004\t\u000fYt\u0011\u0011!a\u0001[\u0006A\u0001.Y:i\u0007>$W\rF\u0001n\u0003!!xn\u0015;sS:<G#\u00013\u0002\r\u0015\fX/\u00197t)\u0011\t\u0019!!\b\t\u000fY\f\u0012\u0011!a\u0001e\u0006a2i\u001c8uC&tWM\u001d'pG\u0006d\u0017\u000e^=Qe\u00164WM]3oG\u0016\u001c\bC\u0001(\u0014'\u0015\u0019\u0012QEA\u0019!\u001d\t9#!\f>{5k!!!\u000b\u000b\u0007\u0005-r%A\u0004sk:$\u0018.\\3\n\t\u0005=\u0012\u0011\u0006\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003BA\u001a\u0003si!!!\u000e\u000b\u0007\u0005]\u0002.\u0001\u0002j_&\u0019\u0011(!\u000e\u0015\u0005\u0005\u0005\u0012!B1qa2LH#B'\u0002B\u0005\r\u0003\"B\u001e\u0017\u0001\u0004i\u0004\"B%\u0017\u0001\u0004i\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003\u0013\n)\u0006E\u0003'\u0003\u0017\ny%C\u0002\u0002N\u001d\u0012aa\u00149uS>t\u0007#\u0002\u0014\u0002Ruj\u0014bAA*O\t1A+\u001e9mKJB\u0001\"a\u0016\u0018\u0003\u0003\u0005\r!T\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA/!\r)\u0017qL\u0005\u0004\u0003C2'AB(cU\u0016\u001cG\u000f"
)
public class ContainerLocalityPreferences implements Product, Serializable {
   private final String[] nodes;
   private final String[] racks;

   public static Option unapply(final ContainerLocalityPreferences x$0) {
      return ContainerLocalityPreferences$.MODULE$.unapply(x$0);
   }

   public static ContainerLocalityPreferences apply(final String[] nodes, final String[] racks) {
      return ContainerLocalityPreferences$.MODULE$.apply(nodes, racks);
   }

   public static Function1 tupled() {
      return ContainerLocalityPreferences$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ContainerLocalityPreferences$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String[] nodes() {
      return this.nodes;
   }

   public String[] racks() {
      return this.racks;
   }

   public ContainerLocalityPreferences copy(final String[] nodes, final String[] racks) {
      return new ContainerLocalityPreferences(nodes, racks);
   }

   public String[] copy$default$1() {
      return this.nodes();
   }

   public String[] copy$default$2() {
      return this.racks();
   }

   public String productPrefix() {
      return "ContainerLocalityPreferences";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.nodes();
         }
         case 1 -> {
            return this.racks();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ContainerLocalityPreferences;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "nodes";
         }
         case 1 -> {
            return "racks";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label38: {
            if (x$1 instanceof ContainerLocalityPreferences) {
               ContainerLocalityPreferences var4 = (ContainerLocalityPreferences)x$1;
               if (this.nodes() == var4.nodes() && this.racks() == var4.racks() && var4.canEqual(this)) {
                  break label38;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public ContainerLocalityPreferences(final String[] nodes, final String[] racks) {
      this.nodes = nodes;
      this.racks = racks;
      Product.$init$(this);
   }
}

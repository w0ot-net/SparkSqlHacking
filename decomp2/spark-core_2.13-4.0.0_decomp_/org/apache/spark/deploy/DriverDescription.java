package org.apache.spark.deploy;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eg!B\u0014)\u0001\"\u0002\u0004\u0002C$\u0001\u0005+\u0007I\u0011\u0001%\t\u0011E\u0003!\u0011#Q\u0001\n%C\u0001B\u0015\u0001\u0003\u0016\u0004%\ta\u0015\u0005\t/\u0002\u0011\t\u0012)A\u0005)\"A\u0001\f\u0001BK\u0002\u0013\u00051\u000b\u0003\u0005Z\u0001\tE\t\u0015!\u0003U\u0011!Q\u0006A!f\u0001\n\u0003Y\u0006\u0002C0\u0001\u0005#\u0005\u000b\u0011\u0002/\t\u0011\u0001\u0004!Q3A\u0005\u0002\u0005D\u0001B\u001a\u0001\u0003\u0012\u0003\u0006IA\u0019\u0005\tO\u0002\u0011)\u001a!C\u0001Q\"A!\u000f\u0001B\tB\u0003%\u0011\u000eC\u0003t\u0001\u0011\u0005A\u000fC\u0003}\u0001\u0011\u0005S\u0010C\u0004\u007f\u0001\u0005\u0005I\u0011A@\t\u0013\u00055\u0001!%A\u0005\u0002\u0005=\u0001\"CA\u0013\u0001E\u0005I\u0011AA\u0014\u0011%\tY\u0003AI\u0001\n\u0003\t9\u0003C\u0005\u0002.\u0001\t\n\u0011\"\u0001\u00020!I\u00111\u0007\u0001\u0012\u0002\u0013\u0005\u0011Q\u0007\u0005\n\u0003s\u0001\u0011\u0013!C\u0001\u0003wA\u0011\"a\u0010\u0001\u0003\u0003%\t%!\u0011\t\u0011\u0005E\u0003!!A\u0005\u0002MC\u0011\"a\u0015\u0001\u0003\u0003%\t!!\u0016\t\u0013\u0005\u0005\u0004!!A\u0005B\u0005\r\u0004\"CA9\u0001\u0005\u0005I\u0011AA:\u0011%\t9\bAA\u0001\n\u0003\nI\bC\u0005\u0002~\u0001\t\t\u0011\"\u0011\u0002\u0000!I\u0011\u0011\u0011\u0001\u0002\u0002\u0013\u0005\u00131Q\u0004\u000b\u0003\u000fC\u0013\u0011!E\u0001Q\u0005%e!C\u0014)\u0003\u0003E\t\u0001KAF\u0011\u0019\u0019x\u0004\"\u0001\u0002$\"AApHA\u0001\n\u000b\n)\u000bC\u0005\u0002(~\t\t\u0011\"!\u0002*\"I\u0011qW\u0010\u0012\u0002\u0013\u0005\u00111\b\u0005\n\u0003s{\u0012\u0011!CA\u0003wC\u0011\"!4 #\u0003%\t!a\u000f\t\u0013\u0005=w$!A\u0005\n\u0005E'!\u0005#sSZ,'\u000fR3tGJL\u0007\u000f^5p]*\u0011\u0011FK\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005-b\u0013!B:qCJ\\'BA\u0017/\u0003\u0019\t\u0007/Y2iK*\tq&A\u0002pe\u001e\u001cB\u0001A\u00198uA\u0011!'N\u0007\u0002g)\tA'A\u0003tG\u0006d\u0017-\u0003\u00027g\t1\u0011I\\=SK\u001a\u0004\"A\r\u001d\n\u0005e\u001a$a\u0002)s_\u0012,8\r\u001e\t\u0003w\u0011s!\u0001\u0010\"\u000f\u0005u\nU\"\u0001 \u000b\u0005}\u0002\u0015A\u0002\u001fs_>$hh\u0001\u0001\n\u0003QJ!aQ\u001a\u0002\u000fA\f7m[1hK&\u0011QI\u0012\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\u0007N\naA[1s+JdW#A%\u0011\u0005)seBA&M!\ti4'\u0003\u0002Ng\u00051\u0001K]3eK\u001aL!a\u0014)\u0003\rM#(/\u001b8h\u0015\ti5'A\u0004kCJ,&\u000f\u001c\u0011\u0002\u00075,W.F\u0001U!\t\u0011T+\u0003\u0002Wg\t\u0019\u0011J\u001c;\u0002\t5,W\u000eI\u0001\u0006G>\u0014Xm]\u0001\u0007G>\u0014Xm\u001d\u0011\u0002\u0013M,\b/\u001a:wSN,W#\u0001/\u0011\u0005Ij\u0016B\u000104\u0005\u001d\u0011un\u001c7fC:\f!b];qKJ4\u0018n]3!\u0003\u001d\u0019w.\\7b]\u0012,\u0012A\u0019\t\u0003G\u0012l\u0011\u0001K\u0005\u0003K\"\u0012qaQ8n[\u0006tG-\u0001\u0005d_6l\u0017M\u001c3!\u00031\u0011Xm]8ve\u000e,'+Z9t+\u0005I\u0007cA\u001ekY&\u00111N\u0012\u0002\u0004'\u0016\f\bCA7q\u001b\u0005q'BA8+\u0003!\u0011Xm]8ve\u000e,\u0017BA9o\u0005M\u0011Vm]8ve\u000e,'+Z9vSJ,W.\u001a8u\u00035\u0011Xm]8ve\u000e,'+Z9tA\u00051A(\u001b8jiz\"r!\u001e<xqfT8\u0010\u0005\u0002d\u0001!)q)\u0004a\u0001\u0013\")!+\u0004a\u0001)\")\u0001,\u0004a\u0001)\")!,\u0004a\u00019\")\u0001-\u0004a\u0001E\"9q-\u0004I\u0001\u0002\u0004I\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003%\u000bAaY8qsRiQ/!\u0001\u0002\u0004\u0005\u0015\u0011qAA\u0005\u0003\u0017AqaR\b\u0011\u0002\u0003\u0007\u0011\nC\u0004S\u001fA\u0005\t\u0019\u0001+\t\u000fa{\u0001\u0013!a\u0001)\"9!l\u0004I\u0001\u0002\u0004a\u0006b\u00021\u0010!\u0003\u0005\rA\u0019\u0005\bO>\u0001\n\u00111\u0001j\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"!!\u0005+\u0007%\u000b\u0019b\u000b\u0002\u0002\u0016A!\u0011qCA\u0011\u001b\t\tIB\u0003\u0003\u0002\u001c\u0005u\u0011!C;oG\",7m[3e\u0015\r\tybM\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u0012\u00033\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\"!!\u000b+\u0007Q\u000b\u0019\"\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%iU\u0011\u0011\u0011\u0007\u0016\u00049\u0006M\u0011AD2paf$C-\u001a4bk2$H%N\u000b\u0003\u0003oQ3AYA\n\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIY*\"!!\u0010+\u0007%\f\u0019\"A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003\u0007\u0002B!!\u0012\u0002P5\u0011\u0011q\t\u0006\u0005\u0003\u0013\nY%\u0001\u0003mC:<'BAA'\u0003\u0011Q\u0017M^1\n\u0007=\u000b9%\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005]\u0013Q\f\t\u0004e\u0005e\u0013bAA.g\t\u0019\u0011I\\=\t\u0011\u0005}\u0003$!AA\u0002Q\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA3!\u0019\t9'!\u001c\u0002X5\u0011\u0011\u0011\u000e\u0006\u0004\u0003W\u001a\u0014AC2pY2,7\r^5p]&!\u0011qNA5\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0007q\u000b)\bC\u0005\u0002`i\t\t\u00111\u0001\u0002X\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\t\u0019%a\u001f\t\u0011\u0005}3$!AA\u0002Q\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002)\u00061Q-];bYN$2\u0001XAC\u0011%\ty&HA\u0001\u0002\u0004\t9&A\tEe&4XM\u001d#fg\u000e\u0014\u0018\u000e\u001d;j_:\u0004\"aY\u0010\u0014\u000b}\ti)!'\u0011\u0017\u0005=\u0015QS%U)r\u0013\u0017.^\u0007\u0003\u0003#S1!a%4\u0003\u001d\u0011XO\u001c;j[\u0016LA!a&\u0002\u0012\n\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001c\u0011\t\u0005m\u0015\u0011U\u0007\u0003\u0003;SA!a(\u0002L\u0005\u0011\u0011n\\\u0005\u0004\u000b\u0006uECAAE)\t\t\u0019%A\u0003baBd\u0017\u0010F\u0007v\u0003W\u000bi+a,\u00022\u0006M\u0016Q\u0017\u0005\u0006\u000f\n\u0002\r!\u0013\u0005\u0006%\n\u0002\r\u0001\u0016\u0005\u00061\n\u0002\r\u0001\u0016\u0005\u00065\n\u0002\r\u0001\u0018\u0005\u0006A\n\u0002\rA\u0019\u0005\bO\n\u0002\n\u00111\u0001j\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u00122\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003{\u000bI\rE\u00033\u0003\u007f\u000b\u0019-C\u0002\u0002BN\u0012aa\u00149uS>t\u0007#\u0003\u001a\u0002F&#F\u000b\u00182j\u0013\r\t9m\r\u0002\u0007)V\u0004H.\u001a\u001c\t\u0011\u0005-G%!AA\u0002U\f1\u0001\u001f\u00131\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%m\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u001b\t\u0005\u0003\u000b\n).\u0003\u0003\u0002X\u0006\u001d#AB(cU\u0016\u001cG\u000f"
)
public class DriverDescription implements Product, Serializable {
   private final String jarUrl;
   private final int mem;
   private final int cores;
   private final boolean supervise;
   private final Command command;
   private final Seq resourceReqs;

   public static Seq $lessinit$greater$default$6() {
      return DriverDescription$.MODULE$.$lessinit$greater$default$6();
   }

   public static Option unapply(final DriverDescription x$0) {
      return DriverDescription$.MODULE$.unapply(x$0);
   }

   public static Seq apply$default$6() {
      return DriverDescription$.MODULE$.apply$default$6();
   }

   public static DriverDescription apply(final String jarUrl, final int mem, final int cores, final boolean supervise, final Command command, final Seq resourceReqs) {
      return DriverDescription$.MODULE$.apply(jarUrl, mem, cores, supervise, command, resourceReqs);
   }

   public static Function1 tupled() {
      return DriverDescription$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return DriverDescription$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String jarUrl() {
      return this.jarUrl;
   }

   public int mem() {
      return this.mem;
   }

   public int cores() {
      return this.cores;
   }

   public boolean supervise() {
      return this.supervise;
   }

   public Command command() {
      return this.command;
   }

   public Seq resourceReqs() {
      return this.resourceReqs;
   }

   public String toString() {
      return "DriverDescription (" + this.command().mainClass() + ")";
   }

   public DriverDescription copy(final String jarUrl, final int mem, final int cores, final boolean supervise, final Command command, final Seq resourceReqs) {
      return new DriverDescription(jarUrl, mem, cores, supervise, command, resourceReqs);
   }

   public String copy$default$1() {
      return this.jarUrl();
   }

   public int copy$default$2() {
      return this.mem();
   }

   public int copy$default$3() {
      return this.cores();
   }

   public boolean copy$default$4() {
      return this.supervise();
   }

   public Command copy$default$5() {
      return this.command();
   }

   public Seq copy$default$6() {
      return this.resourceReqs();
   }

   public String productPrefix() {
      return "DriverDescription";
   }

   public int productArity() {
      return 6;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.jarUrl();
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.mem());
         }
         case 2 -> {
            return BoxesRunTime.boxToInteger(this.cores());
         }
         case 3 -> {
            return BoxesRunTime.boxToBoolean(this.supervise());
         }
         case 4 -> {
            return this.command();
         }
         case 5 -> {
            return this.resourceReqs();
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
      return x$1 instanceof DriverDescription;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "jarUrl";
         }
         case 1 -> {
            return "mem";
         }
         case 2 -> {
            return "cores";
         }
         case 3 -> {
            return "supervise";
         }
         case 4 -> {
            return "command";
         }
         case 5 -> {
            return "resourceReqs";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.jarUrl()));
      var1 = Statics.mix(var1, this.mem());
      var1 = Statics.mix(var1, this.cores());
      var1 = Statics.mix(var1, this.supervise() ? 1231 : 1237);
      var1 = Statics.mix(var1, Statics.anyHash(this.command()));
      var1 = Statics.mix(var1, Statics.anyHash(this.resourceReqs()));
      return Statics.finalizeHash(var1, 6);
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label75: {
            if (x$1 instanceof DriverDescription) {
               DriverDescription var4 = (DriverDescription)x$1;
               if (this.mem() == var4.mem() && this.cores() == var4.cores() && this.supervise() == var4.supervise()) {
                  label68: {
                     String var10000 = this.jarUrl();
                     String var5 = var4.jarUrl();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label68;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label68;
                     }

                     Command var8 = this.command();
                     Command var6 = var4.command();
                     if (var8 == null) {
                        if (var6 != null) {
                           break label68;
                        }
                     } else if (!var8.equals(var6)) {
                        break label68;
                     }

                     Seq var9 = this.resourceReqs();
                     Seq var7 = var4.resourceReqs();
                     if (var9 == null) {
                        if (var7 != null) {
                           break label68;
                        }
                     } else if (!var9.equals(var7)) {
                        break label68;
                     }

                     if (var4.canEqual(this)) {
                        break label75;
                     }
                  }
               }
            }

            var10 = false;
            return var10;
         }
      }

      var10 = true;
      return var10;
   }

   public DriverDescription(final String jarUrl, final int mem, final int cores, final boolean supervise, final Command command, final Seq resourceReqs) {
      this.jarUrl = jarUrl;
      this.mem = mem;
      this.cores = cores;
      this.supervise = supervise;
      this.command = command;
      this.resourceReqs = resourceReqs;
      Product.$init$(this);
   }
}

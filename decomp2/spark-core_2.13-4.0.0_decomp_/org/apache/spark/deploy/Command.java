package org.apache.spark.deploy;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ef!B\u0013'\u0001\"r\u0003\u0002C#\u0001\u0005+\u0007I\u0011\u0001$\t\u0011=\u0003!\u0011#Q\u0001\n\u001dC\u0001\u0002\u0015\u0001\u0003\u0016\u0004%\t!\u0015\u0005\t+\u0002\u0011\t\u0012)A\u0005%\"Aa\u000b\u0001BK\u0002\u0013\u0005q\u000b\u0003\u0005_\u0001\tE\t\u0015!\u0003Y\u0011!y\u0006A!f\u0001\n\u0003\t\u0006\u0002\u00031\u0001\u0005#\u0005\u000b\u0011\u0002*\t\u0011\u0005\u0004!Q3A\u0005\u0002EC\u0001B\u0019\u0001\u0003\u0012\u0003\u0006IA\u0015\u0005\tG\u0002\u0011)\u001a!C\u0001#\"AA\r\u0001B\tB\u0003%!\u000bC\u0003f\u0001\u0011\u0005a\rC\u0004p\u0001\u0005\u0005I\u0011\u00019\t\u000f]\u0004\u0011\u0013!C\u0001q\"I\u0011q\u0001\u0001\u0012\u0002\u0013\u0005\u0011\u0011\u0002\u0005\n\u0003\u001b\u0001\u0011\u0013!C\u0001\u0003\u001fA\u0011\"a\u0005\u0001#\u0003%\t!!\u0003\t\u0013\u0005U\u0001!%A\u0005\u0002\u0005%\u0001\"CA\f\u0001E\u0005I\u0011AA\u0005\u0011%\tI\u0002AA\u0001\n\u0003\nY\u0002C\u0005\u0002,\u0001\t\t\u0011\"\u0001\u0002.!I\u0011Q\u0007\u0001\u0002\u0002\u0013\u0005\u0011q\u0007\u0005\n\u0003\u0007\u0002\u0011\u0011!C!\u0003\u000bB\u0011\"!\u0014\u0001\u0003\u0003%\t!a\u0014\t\u0013\u0005e\u0003!!A\u0005B\u0005m\u0003\"CA0\u0001\u0005\u0005I\u0011IA1\u0011%\t\u0019\u0007AA\u0001\n\u0003\n)\u0007C\u0005\u0002h\u0001\t\t\u0011\"\u0011\u0002j\u001dQ\u0011Q\u000e\u0014\u0002\u0002#\u0005\u0001&a\u001c\u0007\u0013\u00152\u0013\u0011!E\u0001Q\u0005E\u0004BB3 \t\u0003\tI\tC\u0005\u0002d}\t\t\u0011\"\u0012\u0002f!I\u00111R\u0010\u0002\u0002\u0013\u0005\u0015Q\u0012\u0005\n\u00037{\u0012\u0011!CA\u0003;C\u0011\"a, \u0003\u0003%I!!-\u0003\u000f\r{W.\\1oI*\u0011q\u0005K\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005%R\u0013!B:qCJ\\'BA\u0016-\u0003\u0019\t\u0007/Y2iK*\tQ&A\u0002pe\u001e\u001cB\u0001A\u00186qA\u0011\u0001gM\u0007\u0002c)\t!'A\u0003tG\u0006d\u0017-\u0003\u00025c\t1\u0011I\\=SK\u001a\u0004\"\u0001\r\u001c\n\u0005]\n$a\u0002)s_\u0012,8\r\u001e\t\u0003s\ts!A\u000f!\u000f\u0005mzT\"\u0001\u001f\u000b\u0005ur\u0014A\u0002\u001fs_>$hh\u0001\u0001\n\u0003IJ!!Q\u0019\u0002\u000fA\f7m[1hK&\u00111\t\u0012\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\u0003F\n\u0011\"\\1j]\u000ec\u0017m]:\u0016\u0003\u001d\u0003\"\u0001\u0013'\u000f\u0005%S\u0005CA\u001e2\u0013\tY\u0015'\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u001b:\u0013aa\u0015;sS:<'BA&2\u0003)i\u0017-\u001b8DY\u0006\u001c8\u000fI\u0001\nCJ<W/\\3oiN,\u0012A\u0015\t\u0004sM;\u0015B\u0001+E\u0005\r\u0019V-]\u0001\u000bCJ<W/\\3oiN\u0004\u0013aC3om&\u0014xN\\7f]R,\u0012\u0001\u0017\t\u00053r;u)D\u0001[\u0015\tY\u0016'\u0001\u0006d_2dWm\u0019;j_:L!!\u0018.\u0003\u00075\u000b\u0007/\u0001\u0007f]ZL'o\u001c8nK:$\b%\u0001\tdY\u0006\u001c8\u000fU1uQ\u0016sGO]5fg\u0006\t2\r\\1tgB\u000bG\u000f[#oiJLWm\u001d\u0011\u0002%1L'M]1ssB\u000bG\u000f[#oiJLWm]\u0001\u0014Y&\u0014'/\u0019:z!\u0006$\b.\u00128ue&,7\u000fI\u0001\tU\u00064\u0018m\u00149ug\u0006I!.\u0019<b\u001fB$8\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000f\u001dL'n\u001b7n]B\u0011\u0001\u000eA\u0007\u0002M!)Q)\u0004a\u0001\u000f\")\u0001+\u0004a\u0001%\")a+\u0004a\u00011\")q,\u0004a\u0001%\")\u0011-\u0004a\u0001%\")1-\u0004a\u0001%\u0006!1m\u001c9z)\u001d9\u0017O]:ukZDq!\u0012\b\u0011\u0002\u0003\u0007q\tC\u0004Q\u001dA\u0005\t\u0019\u0001*\t\u000fYs\u0001\u0013!a\u00011\"9qL\u0004I\u0001\u0002\u0004\u0011\u0006bB1\u000f!\u0003\u0005\rA\u0015\u0005\bG:\u0001\n\u00111\u0001S\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012!\u001f\u0016\u0003\u000fj\\\u0013a\u001f\t\u0004y\u0006\rQ\"A?\u000b\u0005y|\u0018!C;oG\",7m[3e\u0015\r\t\t!M\u0001\u000bC:tw\u000e^1uS>t\u0017bAA\u0003{\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u00111\u0002\u0016\u0003%j\fabY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u0002\u0012)\u0012\u0001L_\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIU\nabY8qs\u0012\"WMZ1vYR$c'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003;\u0001B!a\b\u0002*5\u0011\u0011\u0011\u0005\u0006\u0005\u0003G\t)#\u0001\u0003mC:<'BAA\u0014\u0003\u0011Q\u0017M^1\n\u00075\u000b\t#\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u00020A\u0019\u0001'!\r\n\u0007\u0005M\u0012GA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002:\u0005}\u0002c\u0001\u0019\u0002<%\u0019\u0011QH\u0019\u0003\u0007\u0005s\u0017\u0010C\u0005\u0002B]\t\t\u00111\u0001\u00020\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a\u0012\u0011\u000be\u000bI%!\u000f\n\u0007\u0005-#L\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA)\u0003/\u00022\u0001MA*\u0013\r\t)&\r\u0002\b\u0005>|G.Z1o\u0011%\t\t%GA\u0001\u0002\u0004\tI$\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA\u000f\u0003;B\u0011\"!\u0011\u001b\u0003\u0003\u0005\r!a\f\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a\f\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!\b\u0002\r\u0015\fX/\u00197t)\u0011\t\t&a\u001b\t\u0013\u0005\u0005S$!AA\u0002\u0005e\u0012aB\"p[6\fg\u000e\u001a\t\u0003Q~\u0019RaHA:\u0003\u007f\u00022\"!\u001e\u0002|\u001d\u0013\u0006L\u0015*SO6\u0011\u0011q\u000f\u0006\u0004\u0003s\n\u0014a\u0002:v]RLW.Z\u0005\u0005\u0003{\n9HA\tBEN$(/Y2u\rVt7\r^5p]Z\u0002B!!!\u0002\b6\u0011\u00111\u0011\u0006\u0005\u0003\u000b\u000b)#\u0001\u0002j_&\u00191)a!\u0015\u0005\u0005=\u0014!B1qa2LH#D4\u0002\u0010\u0006E\u00151SAK\u0003/\u000bI\nC\u0003FE\u0001\u0007q\tC\u0003QE\u0001\u0007!\u000bC\u0003WE\u0001\u0007\u0001\fC\u0003`E\u0001\u0007!\u000bC\u0003bE\u0001\u0007!\u000bC\u0003dE\u0001\u0007!+A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005}\u00151\u0016\t\u0006a\u0005\u0005\u0016QU\u0005\u0004\u0003G\u000b$AB(qi&|g\u000eE\u00051\u0003O;%\u000b\u0017*S%&\u0019\u0011\u0011V\u0019\u0003\rQ+\b\u000f\\37\u0011!\tikIA\u0001\u0002\u00049\u0017a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u0017\t\u0005\u0003?\t),\u0003\u0003\u00028\u0006\u0005\"AB(cU\u0016\u001cG\u000f"
)
public class Command implements Product, Serializable {
   private final String mainClass;
   private final Seq arguments;
   private final Map environment;
   private final Seq classPathEntries;
   private final Seq libraryPathEntries;
   private final Seq javaOpts;

   public static Option unapply(final Command x$0) {
      return Command$.MODULE$.unapply(x$0);
   }

   public static Command apply(final String mainClass, final Seq arguments, final Map environment, final Seq classPathEntries, final Seq libraryPathEntries, final Seq javaOpts) {
      return Command$.MODULE$.apply(mainClass, arguments, environment, classPathEntries, libraryPathEntries, javaOpts);
   }

   public static Function1 tupled() {
      return Command$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return Command$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String mainClass() {
      return this.mainClass;
   }

   public Seq arguments() {
      return this.arguments;
   }

   public Map environment() {
      return this.environment;
   }

   public Seq classPathEntries() {
      return this.classPathEntries;
   }

   public Seq libraryPathEntries() {
      return this.libraryPathEntries;
   }

   public Seq javaOpts() {
      return this.javaOpts;
   }

   public Command copy(final String mainClass, final Seq arguments, final Map environment, final Seq classPathEntries, final Seq libraryPathEntries, final Seq javaOpts) {
      return new Command(mainClass, arguments, environment, classPathEntries, libraryPathEntries, javaOpts);
   }

   public String copy$default$1() {
      return this.mainClass();
   }

   public Seq copy$default$2() {
      return this.arguments();
   }

   public Map copy$default$3() {
      return this.environment();
   }

   public Seq copy$default$4() {
      return this.classPathEntries();
   }

   public Seq copy$default$5() {
      return this.libraryPathEntries();
   }

   public Seq copy$default$6() {
      return this.javaOpts();
   }

   public String productPrefix() {
      return "Command";
   }

   public int productArity() {
      return 6;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.mainClass();
         }
         case 1 -> {
            return this.arguments();
         }
         case 2 -> {
            return this.environment();
         }
         case 3 -> {
            return this.classPathEntries();
         }
         case 4 -> {
            return this.libraryPathEntries();
         }
         case 5 -> {
            return this.javaOpts();
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
      return x$1 instanceof Command;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "mainClass";
         }
         case 1 -> {
            return "arguments";
         }
         case 2 -> {
            return "environment";
         }
         case 3 -> {
            return "classPathEntries";
         }
         case 4 -> {
            return "libraryPathEntries";
         }
         case 5 -> {
            return "javaOpts";
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
      boolean var16;
      if (this != x$1) {
         label87: {
            if (x$1 instanceof Command) {
               label80: {
                  Command var4 = (Command)x$1;
                  String var10000 = this.mainClass();
                  String var5 = var4.mainClass();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label80;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label80;
                  }

                  Seq var11 = this.arguments();
                  Seq var6 = var4.arguments();
                  if (var11 == null) {
                     if (var6 != null) {
                        break label80;
                     }
                  } else if (!var11.equals(var6)) {
                     break label80;
                  }

                  Map var12 = this.environment();
                  Map var7 = var4.environment();
                  if (var12 == null) {
                     if (var7 != null) {
                        break label80;
                     }
                  } else if (!var12.equals(var7)) {
                     break label80;
                  }

                  Seq var13 = this.classPathEntries();
                  Seq var8 = var4.classPathEntries();
                  if (var13 == null) {
                     if (var8 != null) {
                        break label80;
                     }
                  } else if (!var13.equals(var8)) {
                     break label80;
                  }

                  var13 = this.libraryPathEntries();
                  Seq var9 = var4.libraryPathEntries();
                  if (var13 == null) {
                     if (var9 != null) {
                        break label80;
                     }
                  } else if (!var13.equals(var9)) {
                     break label80;
                  }

                  var13 = this.javaOpts();
                  Seq var10 = var4.javaOpts();
                  if (var13 == null) {
                     if (var10 != null) {
                        break label80;
                     }
                  } else if (!var13.equals(var10)) {
                     break label80;
                  }

                  if (var4.canEqual(this)) {
                     break label87;
                  }
               }
            }

            var16 = false;
            return var16;
         }
      }

      var16 = true;
      return var16;
   }

   public Command(final String mainClass, final Seq arguments, final Map environment, final Seq classPathEntries, final Seq libraryPathEntries, final Seq javaOpts) {
      this.mainClass = mainClass;
      this.arguments = arguments;
      this.environment = environment;
      this.classPathEntries = classPathEntries;
      this.libraryPathEntries = libraryPathEntries;
      this.javaOpts = javaOpts;
      Product.$init$(this);
   }
}

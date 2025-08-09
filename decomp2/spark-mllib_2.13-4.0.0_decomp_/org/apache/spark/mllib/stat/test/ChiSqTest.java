package org.apache.spark.mllib.stat.test;

import java.io.Serializable;
import org.apache.spark.internal.Logging;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.rdd.RDD;
import scala.Enumeration;
import scala.Function2;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\t-vA\u0002\u001d:\u0011\u0003yTI\u0002\u0004Hs!\u0005q\b\u0013\u0005\u0006+\u0006!\ta\u0016\u0004\u00051\u0006\u0001\u0015\f\u0003\u0005j\u0007\tU\r\u0011\"\u0001k\u0011!\u00198A!E!\u0002\u0013Y\u0007\u0002\u0003;\u0004\u0005+\u0007I\u0011A;\t\u0011q\u001c!\u0011#Q\u0001\nYDQ!V\u0002\u0005\u0002uD\u0011\"!\u0002\u0004\u0003\u0003%\t!a\u0002\t\u0013\u000551!%A\u0005\u0002\u0005=\u0001\"CA\u0013\u0007E\u0005I\u0011AA\u0014\u0011%\tYcAA\u0001\n\u0003\ni\u0003C\u0005\u0002>\r\t\t\u0011\"\u0001\u0002@!I\u0011qI\u0002\u0002\u0002\u0013\u0005\u0011\u0011\n\u0005\n\u0003+\u001a\u0011\u0011!C!\u0003/B\u0011\"!\u001a\u0004\u0003\u0003%\t!a\u001a\t\u0013\u0005E4!!A\u0005B\u0005M\u0004\"CA<\u0007\u0005\u0005I\u0011IA=\u0011%\tYhAA\u0001\n\u0003\ni\bC\u0005\u0002\u0000\r\t\t\u0011\"\u0011\u0002\u0002\u001eI\u0011QQ\u0001\u0002\u0002#\u0005\u0011q\u0011\u0004\t1\u0006\t\t\u0011#\u0001\u0002\n\"1QK\u0006C\u0001\u0003CC\u0011\"a\u001f\u0017\u0003\u0003%)%! \t\u0013\u0005\rf#!A\u0005\u0002\u0006\u0015\u0006\"CAV-\u0005\u0005I\u0011QAW\u0011%\tyLFA\u0001\n\u0013\t\t\rC\u0005\u0002J\u0006\u0011\r\u0011\"\u0001\u0002L\"9\u0011QZ\u0001!\u0002\u0013qxaBAh\u0003!\u0005\u0011\u0011\u001b\u0004\b\u0003'\f\u0001\u0012AAk\u0011\u0019)v\u0004\"\u0001\u0002^\u00161\u00111[\u0010\u0001\u0003?D\u0011\"a: \u0005\u0004%\t!!;\t\u0011\u0005-x\u0004)A\u0005\u0003?D\u0011\"!< \u0005\u0004%\t!!;\t\u0011\u0005=x\u0004)A\u0005\u0003?D\u0011\"a0 \u0003\u0003%I!!1\t\u000f\u0005E\u0018\u0001\"\u0003\u0002t\"Q\u0011\u0011`\u0001C\u0002\u0013\u0005q(a\u0010\t\u0011\u0005m\u0018\u0001)A\u0005\u0003\u0003Bq!!@\u0002\t\u0003\ty\u0010C\u0005\u0003,\u0005\t\n\u0011\"\u0001\u0002\u0010!A!QF\u0001\u0005\u0002}\u0012y\u0003\u0003\u0006\u0003N\u0005\t\n\u0011\"\u0001@\u0003\u001fAqAa\u0014\u0002\t\u0013\u0011\t\u0006C\u0005\u0003\\\u0005\t\n\u0011\"\u0003\u0002\u0010!9!QL\u0001\u0005\n\t}\u0003\"\u0003B4\u0003E\u0005I\u0011BA\b\u0011\u001d\u0011I'\u0001C\u0005\u0005WBqA!\"\u0002\t\u0003\u00119\tC\u0005\u0003\u0014\u0006\t\n\u0011\"\u0001\u0003\u0016\"I!\u0011T\u0001\u0012\u0002\u0013\u0005\u0011q\u0002\u0005\b\u00057\u000bA\u0011\u0001BO\u0011%\u0011I+AI\u0001\n\u0003\ty!A\u0005DQ&\u001c\u0016\u000fV3ti*\u0011!hO\u0001\u0005i\u0016\u001cHO\u0003\u0002={\u0005!1\u000f^1u\u0015\tqt(A\u0003nY2L'M\u0003\u0002A\u0003\u0006)1\u000f]1sW*\u0011!iQ\u0001\u0007CB\f7\r[3\u000b\u0003\u0011\u000b1a\u001c:h!\t1\u0015!D\u0001:\u0005%\u0019\u0005.[*r)\u0016\u001cHoE\u0002\u0002\u0013>\u0003\"AS'\u000e\u0003-S\u0011\u0001T\u0001\u0006g\u000e\fG.Y\u0005\u0003\u001d.\u0013a!\u00118z%\u00164\u0007C\u0001)T\u001b\u0005\t&B\u0001*@\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001+R\u0005\u001daunZ4j]\u001e\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002\u000b\n1Q*\u001a;i_\u0012\u001cBaA%[;B\u0011!jW\u0005\u00039.\u0013q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002_M:\u0011q\f\u001a\b\u0003A\u000el\u0011!\u0019\u0006\u0003EZ\u000ba\u0001\u0010:p_Rt\u0014\"\u0001'\n\u0005\u0015\\\u0015a\u00029bG.\fw-Z\u0005\u0003O\"\u0014AbU3sS\u0006d\u0017N_1cY\u0016T!!Z&\u0002\t9\fW.Z\u000b\u0002WB\u0011A\u000e\u001d\b\u0003[:\u0004\"\u0001Y&\n\u0005=\\\u0015A\u0002)sK\u0012,g-\u0003\u0002re\n11\u000b\u001e:j]\u001eT!a\\&\u0002\u000b9\fW.\u001a\u0011\u0002\u0013\rD\u0017nU9Gk:\u001cW#\u0001<\u0011\u000b);\u00180_=\n\u0005a\\%!\u0003$v]\u000e$\u0018n\u001c83!\tQ%0\u0003\u0002|\u0017\n1Ai\\;cY\u0016\f!b\u00195j'F4UO\\2!)\u0015q\u0018\u0011AA\u0002!\ty8!D\u0001\u0002\u0011\u0015I\u0007\u00021\u0001l\u0011\u0015!\b\u00021\u0001w\u0003\u0011\u0019w\u000e]=\u0015\u000by\fI!a\u0003\t\u000f%L\u0001\u0013!a\u0001W\"9A/\u0003I\u0001\u0002\u00041\u0018AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0003#Q3a[A\nW\t\t)\u0002\u0005\u0003\u0002\u0018\u0005\u0005RBAA\r\u0015\u0011\tY\"!\b\u0002\u0013Ut7\r[3dW\u0016$'bAA\u0010\u0017\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005\r\u0012\u0011\u0004\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0003SQ3A^A\n\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011q\u0006\t\u0005\u0003c\tY$\u0004\u0002\u00024)!\u0011QGA\u001c\u0003\u0011a\u0017M\\4\u000b\u0005\u0005e\u0012\u0001\u00026bm\u0006L1!]A\u001a\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\t\t\u0005E\u0002K\u0003\u0007J1!!\u0012L\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\tY%!\u0015\u0011\u0007)\u000bi%C\u0002\u0002P-\u00131!\u00118z\u0011%\t\u0019FDA\u0001\u0002\u0004\t\t%A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u00033\u0002b!a\u0017\u0002b\u0005-SBAA/\u0015\r\tyfS\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA2\u0003;\u0012\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011\u0011NA8!\rQ\u00151N\u0005\u0004\u0003[Z%a\u0002\"p_2,\u0017M\u001c\u0005\n\u0003'\u0002\u0012\u0011!a\u0001\u0003\u0017\n!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u0011qFA;\u0011%\t\u0019&EA\u0001\u0002\u0004\t\t%\u0001\u0005iCND7i\u001c3f)\t\t\t%\u0001\u0005u_N#(/\u001b8h)\t\ty#\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003S\n\u0019\tC\u0005\u0002TQ\t\t\u00111\u0001\u0002L\u00051Q*\u001a;i_\u0012\u0004\"a \f\u0014\u000bY\tY)a&\u0011\u000f\u00055\u00151S6w}6\u0011\u0011q\u0012\u0006\u0004\u0003#[\u0015a\u0002:v]RLW.Z\u0005\u0005\u0003+\u000byIA\tBEN$(/Y2u\rVt7\r^5p]J\u0002B!!'\u0002 6\u0011\u00111\u0014\u0006\u0005\u0003;\u000b9$\u0001\u0002j_&\u0019q-a'\u0015\u0005\u0005\u001d\u0015!B1qa2LH#\u0002@\u0002(\u0006%\u0006\"B5\u001a\u0001\u0004Y\u0007\"\u0002;\u001a\u0001\u00041\u0018aB;oCB\u0004H.\u001f\u000b\u0005\u0003_\u000bY\fE\u0003K\u0003c\u000b),C\u0002\u00024.\u0013aa\u00149uS>t\u0007#\u0002&\u00028.4\u0018bAA]\u0017\n1A+\u001e9mKJB\u0001\"!0\u001b\u0003\u0003\u0005\rA`\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAAb!\u0011\t\t$!2\n\t\u0005\u001d\u00171\u0007\u0002\u0007\u001f\nTWm\u0019;\u0002\u000fA+\u0015IU*P\u001dV\ta0\u0001\u0005Q\u000b\u0006\u00136k\u0014(!\u00039qU\u000f\u001c7IsB|G\u000f[3tSN\u0004\"a`\u0010\u0003\u001d9+H\u000e\u001c%za>$\b.Z:jgN\u0019q$a6\u0011\u0007)\u000bI.C\u0002\u0002\\.\u00131\"\u00128v[\u0016\u0014\u0018\r^5p]R\u0011\u0011\u0011\u001b\t\u0005\u0003C\f\u0019/D\u0001 \u0013\u0011\t)/!7\u0003\u000bY\u000bG.^3\u0002\u001b\u001d|w\u000e\u001a8fgN|eMR5u+\t\ty.\u0001\bh_>$g.Z:t\u001f\u001a4\u0015\u000e\u001e\u0011\u0002\u0019%tG-\u001a9f]\u0012,gnY3\u0002\u001b%tG-\u001a9f]\u0012,gnY3!\u0003AiW\r\u001e5pI\u001a\u0013x.\\*ue&tw\rF\u0002\u007f\u0003kDa!a>(\u0001\u0004Y\u0017AC7fi\"|GMT1nK\u0006iQ.\u0019=DCR,wm\u001c:jKN\fa\"\\1y\u0007\u0006$XmZ8sS\u0016\u001c\b%\u0001\ndQ&\u001c\u0016/^1sK\u00124U-\u0019;ve\u0016\u001cHC\u0002B\u0001\u0005\u001b\u0011I\u0003E\u0003K\u0005\u0007\u00119!C\u0002\u0003\u0006-\u0013Q!\u0011:sCf\u00042A\u0012B\u0005\u0013\r\u0011Y!\u000f\u0002\u0010\u0007\"L7+\u001d+fgR\u0014Vm];mi\"9!q\u0002\u0016A\u0002\tE\u0011\u0001\u00023bi\u0006\u0004bAa\u0005\u0003\u001a\tuQB\u0001B\u000b\u0015\r\u00119bP\u0001\u0004e\u0012$\u0017\u0002\u0002B\u000e\u0005+\u00111A\u0015#E!\u0011\u0011yB!\n\u000e\u0005\t\u0005\"b\u0001B\u0012{\u0005Q!/Z4sKN\u001c\u0018n\u001c8\n\t\t\u001d\"\u0011\u0005\u0002\r\u0019\u0006\u0014W\r\\3e!>Lg\u000e\u001e\u0005\t\u0003oT\u0003\u0013!a\u0001W\u0006a2\r[5TcV\f'/\u001a3GK\u0006$XO]3tI\u0011,g-Y;mi\u0012\u0012\u0014!E2p[B,H/Z\"iSN\u000bX/\u0019:fIR1!\u0011\u0007B\u001d\u0005\u0017\u0002bAa\u0005\u0003\u001a\tM\u0002C\u0003&\u00036\u0005\u0005\u00130!\u0011zW&\u0019!qG&\u0003\rQ+\b\u000f\\36\u0011\u001d\u0011y\u0001\fa\u0001\u0005w\u0001bAa\u0005\u0003\u001a\tu\u0002C\u0002&\u00028f\u0014y\u0004\u0005\u0003\u0003B\t\u001dSB\u0001B\"\u0015\r\u0011)%P\u0001\u0007Y&t\u0017\r\\4\n\t\t%#1\t\u0002\u0007-\u0016\u001cGo\u001c:\t\u0011\u0005]H\u0006%AA\u0002-\f1dY8naV$Xm\u00115j'F,\u0018M]3eI\u0011,g-Y;mi\u0012\u0012\u0014aF2iSN\u000bX/\u0019:fI\u0012+gn]3GK\u0006$XO]3t)!\u0011\tDa\u0015\u0003V\te\u0003b\u0002B\b]\u0001\u0007!1\b\u0005\b\u0005/r\u0003\u0019AA!\u0003-qW/\u001c$fCR,(/Z:\t\u0011\u0005]h\u0006%AA\u0002-\f\u0011e\u00195j'F,\u0018M]3e\t\u0016t7/\u001a$fCR,(/Z:%I\u00164\u0017-\u001e7uIM\n\u0001d\u00195j'F,\u0018M]3e'B\f'o]3GK\u0006$XO]3t)!\u0011\tD!\u0019\u0003d\t\u0015\u0004b\u0002B\ba\u0001\u0007!1\b\u0005\b\u0005/\u0002\u0004\u0019AA!\u0011!\t9\u0010\rI\u0001\u0002\u0004Y\u0017AI2iSN\u000bX/\u0019:fIN\u0003\u0018M]:f\r\u0016\fG/\u001e:fg\u0012\"WMZ1vYR$3'\u0001\u0007d_6\u0004X\u000f^3DQ&\u001c\u0016\u000f\u0006\u0005\u0003\b\t5$q\u0010BA\u0011\u001d\u0011yG\ra\u0001\u0005c\naaY8v]R\u001c\bc\u00027\u0003t\t]$\u0011P\u0005\u0004\u0005k\u0012(aA'baB)!*a.zsB\u0019!Ja\u001f\n\u0007\tu4J\u0001\u0003M_:<\u0007BBA|e\u0001\u00071\u000eC\u0004\u0003\u0004J\u0002\r!!\u0011\u0002\u0007\r|G.\u0001\u0006dQ&\u001c\u0016/^1sK\u0012$\u0002Ba\u0002\u0003\n\n5%\u0011\u0013\u0005\b\u0005\u0017\u001b\u0004\u0019\u0001B \u0003!y'm]3sm\u0016$\u0007\"\u0003BHgA\u0005\t\u0019\u0001B \u0003!)\u0007\u0010]3di\u0016$\u0007\u0002CA|gA\u0005\t\u0019A6\u0002)\rD\u0017nU9vCJ,G\r\n3fM\u0006,H\u000e\u001e\u00133+\t\u00119J\u000b\u0003\u0003@\u0005M\u0011\u0001F2iSN\u000bX/\u0019:fI\u0012\"WMZ1vYR$3'\u0001\tdQ&\u001c\u0016/^1sK\u0012l\u0015\r\u001e:jqR1!q\u0001BP\u0005OCqAa\u001c7\u0001\u0004\u0011\t\u000b\u0005\u0003\u0003B\t\r\u0016\u0002\u0002BS\u0005\u0007\u0012a!T1ue&D\b\u0002CA|mA\u0005\t\u0019A6\u00025\rD\u0017nU9vCJ,G-T1ue&DH\u0005Z3gCVdG\u000f\n\u001a"
)
public final class ChiSqTest {
   public static String chiSquaredMatrix$default$2() {
      return ChiSqTest$.MODULE$.chiSquaredMatrix$default$2();
   }

   public static ChiSqTestResult chiSquaredMatrix(final Matrix counts, final String methodName) {
      return ChiSqTest$.MODULE$.chiSquaredMatrix(counts, methodName);
   }

   public static String chiSquared$default$3() {
      return ChiSqTest$.MODULE$.chiSquared$default$3();
   }

   public static Vector chiSquared$default$2() {
      return ChiSqTest$.MODULE$.chiSquared$default$2();
   }

   public static ChiSqTestResult chiSquared(final Vector observed, final Vector expected, final String methodName) {
      return ChiSqTest$.MODULE$.chiSquared(observed, expected, methodName);
   }

   public static String chiSquaredFeatures$default$2() {
      return ChiSqTest$.MODULE$.chiSquaredFeatures$default$2();
   }

   public static ChiSqTestResult[] chiSquaredFeatures(final RDD data, final String methodName) {
      return ChiSqTest$.MODULE$.chiSquaredFeatures(data, methodName);
   }

   public static Method PEARSON() {
      return ChiSqTest$.MODULE$.PEARSON();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return ChiSqTest$.MODULE$.LogStringContext(sc);
   }

   public static class Method implements Product, Serializable {
      private final String name;
      private final Function2 chiSqFunc;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String name() {
         return this.name;
      }

      public Function2 chiSqFunc() {
         return this.chiSqFunc;
      }

      public Method copy(final String name, final Function2 chiSqFunc) {
         return new Method(name, chiSqFunc);
      }

      public String copy$default$1() {
         return this.name();
      }

      public Function2 copy$default$2() {
         return this.chiSqFunc();
      }

      public String productPrefix() {
         return "Method";
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
               return this.chiSqFunc();
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
         return x$1 instanceof Method;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "name";
            }
            case 1 -> {
               return "chiSqFunc";
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
         boolean var8;
         if (this != x$1) {
            label55: {
               if (x$1 instanceof Method) {
                  label48: {
                     Method var4 = (Method)x$1;
                     String var10000 = this.name();
                     String var5 = var4.name();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label48;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label48;
                     }

                     Function2 var7 = this.chiSqFunc();
                     Function2 var6 = var4.chiSqFunc();
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

      public Method(final String name, final Function2 chiSqFunc) {
         this.name = name;
         this.chiSqFunc = chiSqFunc;
         Product.$init$(this);
      }
   }

   public static class Method$ extends AbstractFunction2 implements Serializable {
      public static final Method$ MODULE$ = new Method$();

      public final String toString() {
         return "Method";
      }

      public Method apply(final String name, final Function2 chiSqFunc) {
         return new Method(name, chiSqFunc);
      }

      public Option unapply(final Method x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.name(), x$0.chiSqFunc())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Method$.class);
      }
   }

   public static class NullHypothesis$ extends Enumeration {
      public static final NullHypothesis$ MODULE$ = new NullHypothesis$();
      private static final Enumeration.Value goodnessOfFit;
      private static final Enumeration.Value independence;

      static {
         goodnessOfFit = MODULE$.Value("observed follows the same distribution as expected.");
         independence = MODULE$.Value("the occurrence of the outcomes is statistically independent.");
      }

      public Enumeration.Value goodnessOfFit() {
         return goodnessOfFit;
      }

      public Enumeration.Value independence() {
         return independence;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(NullHypothesis$.class);
      }
   }
}

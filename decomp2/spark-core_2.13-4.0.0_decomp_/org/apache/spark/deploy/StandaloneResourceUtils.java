package org.apache.spark.deploy;

import java.io.File;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.internal.Logging;
import org.apache.spark.resource.ResourceInformation;
import scala.Option;
import scala.Predef;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashSet;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t=wAB!C\u0011\u0003!%J\u0002\u0004M\u0005\"\u0005A)\u0014\u0005\u00065\u0006!\t\u0001\u0018\u0004\u0006;\u0006\u0001EI\u0018\u0005\t]\u000e\u0011)\u001a!C\u0001_\"A\u0001p\u0001B\tB\u0003%\u0001\u000f\u0003\u0005z\u0007\tU\r\u0011\"\u0001{\u0011%\t9a\u0001B\tB\u0003%1\u0010\u0003\u0004[\u0007\u0011\u0005\u0011\u0011\u0002\u0005\b\u0003'\u0019A\u0011AA\u000b\u0011\u001d\t\u0019b\u0001C\u0001\u0003;Aq!!\f\u0004\t\u0003\ty\u0003C\u0004\u00024\r!\t!!\u000e\t\u0013\u0005]2!!A\u0005\u0002\u0005e\u0002\"CA \u0007E\u0005I\u0011AA!\u0011%\t9fAI\u0001\n\u0003\tI\u0006C\u0005\u0002^\r\t\t\u0011\"\u0011\u0002`!I\u0011qN\u0002\u0002\u0002\u0013\u0005\u0011\u0011\u000f\u0005\n\u0003s\u001a\u0011\u0011!C\u0001\u0003wB\u0011\"a\"\u0004\u0003\u0003%\t%!#\t\u0013\u0005M5!!A\u0005\u0002\u0005U\u0005\"CAP\u0007\u0005\u0005I\u0011IAQ\u0011%\t)kAA\u0001\n\u0003\n9\u000bC\u0005\u0002*\u000e\t\t\u0011\"\u0011\u0002,\"I\u0011QV\u0002\u0002\u0002\u0013\u0005\u0013qV\u0004\u000b\u0003g\u000b\u0011\u0011!E\u0001\t\u0006Uf!C/\u0002\u0003\u0003E\t\u0001RA\\\u0011\u0019Q&\u0004\"\u0001\u0002P\"I\u0011\u0011\u0016\u000e\u0002\u0002\u0013\u0015\u00131\u0016\u0005\n\u0003#T\u0012\u0011!CA\u0003'D\u0011\"!7\u001b\u0003\u0003%\t)a7\t\u0013\u00055($!A\u0005\n\u0005=hABA|\u0003\u0001\u000bI\u0010\u0003\u0006\u0002|\u0002\u0012)\u001a!C\u0001\u0003cB!\"!@!\u0005#\u0005\u000b\u0011BA:\u0011)\ty\u0010\tBK\u0002\u0013\u0005!\u0011\u0001\u0005\u000b\u0005\u001f\u0001#\u0011#Q\u0001\n\t\r\u0001B\u0002.!\t\u0003\u0011\t\u0002C\u0004\u0003\u001a\u0001\"\tAa\u0007\t\u0013\u0005]\u0002%!A\u0005\u0002\t\r\u0002\"CA AE\u0005I\u0011\u0001B\u0015\u0011%\t9\u0006II\u0001\n\u0003\u0011i\u0003C\u0005\u0002^\u0001\n\t\u0011\"\u0011\u0002`!I\u0011q\u000e\u0011\u0002\u0002\u0013\u0005\u0011\u0011\u000f\u0005\n\u0003s\u0002\u0013\u0011!C\u0001\u0005cA\u0011\"a\"!\u0003\u0003%\t%!#\t\u0013\u0005M\u0005%!A\u0005\u0002\tU\u0002\"CAPA\u0005\u0005I\u0011\tB\u001d\u0011%\t)\u000bIA\u0001\n\u0003\n9\u000bC\u0005\u0002*\u0002\n\t\u0011\"\u0011\u0002,\"I\u0011Q\u0016\u0011\u0002\u0002\u0013\u0005#QH\u0004\n\u0005\u0003\n\u0011\u0011!E\u0001\u0005\u00072\u0011\"a>\u0002\u0003\u0003E\tA!\u0012\t\ri#D\u0011\u0001B%\u0011%\tI\u000bNA\u0001\n\u000b\nY\u000bC\u0005\u0002RR\n\t\u0011\"!\u0003L!I\u0011\u0011\u001c\u001b\u0002\u0002\u0013\u0005%\u0011\u000b\u0005\n\u0003[$\u0014\u0011!C\u0005\u0003_DqA!\u0017\u0002\t\u0003\u0011Y\u0006C\u0004\u0003r\u0005!IAa\u001d\t\u000f\tU\u0015\u0001\"\u0001\u0003\u0018\"9!qT\u0001\u0005\u0002\t\u0005\u0006b\u0002BV\u0003\u0011\u0005!Q\u0016\u0005\b\u0005c\u000bA\u0011\u0001BZ\u0011\u001d\u0011y,\u0001C\u0001\u0005\u0003\fqc\u0015;b]\u0012\fGn\u001c8f%\u0016\u001cx.\u001e:dKV#\u0018\u000e\\:\u000b\u0005\r#\u0015A\u00023fa2|\u0017P\u0003\u0002F\r\u0006)1\u000f]1sW*\u0011q\tS\u0001\u0007CB\f7\r[3\u000b\u0003%\u000b1a\u001c:h!\tY\u0015!D\u0001C\u0005]\u0019F/\u00198eC2|g.\u001a*fg>,(oY3Vi&d7oE\u0002\u0002\u001dR\u0003\"a\u0014*\u000e\u0003AS\u0011!U\u0001\u0006g\u000e\fG.Y\u0005\u0003'B\u0013a!\u00118z%\u00164\u0007CA+Y\u001b\u00051&BA,E\u0003!Ig\u000e^3s]\u0006d\u0017BA-W\u0005\u001daunZ4j]\u001e\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002\u0015\n\u0019R*\u001e;bE2,'+Z:pkJ\u001cW-\u00138g_N!1AT0c!\ty\u0005-\u0003\u0002b!\n9\u0001K]8ek\u000e$\bCA2l\u001d\t!\u0017N\u0004\u0002fQ6\taM\u0003\u0002h7\u00061AH]8pizJ\u0011!U\u0005\u0003UB\u000bq\u0001]1dW\u0006<W-\u0003\u0002m[\na1+\u001a:jC2L'0\u00192mK*\u0011!\u000eU\u0001\u0005]\u0006lW-F\u0001q!\t\tXO\u0004\u0002sgB\u0011Q\rU\u0005\u0003iB\u000ba\u0001\u0015:fI\u00164\u0017B\u0001<x\u0005\u0019\u0019FO]5oO*\u0011A\u000fU\u0001\u0006]\u0006lW\rI\u0001\nC\u0012$'/Z:tKN,\u0012a\u001f\t\u0005y\u0006\r\u0001/D\u0001~\u0015\tqx0A\u0004nkR\f'\r\\3\u000b\u0007\u0005\u0005\u0001+\u0001\u0006d_2dWm\u0019;j_:L1!!\u0002~\u0005\u001dA\u0015m\u001d5TKR\f!\"\u00193ee\u0016\u001c8/Z:!)\u0019\tY!a\u0004\u0002\u0012A\u0019\u0011QB\u0002\u000e\u0003\u0005AQA\u001c\u0005A\u0002ADQ!\u001f\u0005A\u0002m\fQ\u0001\n9mkN$B!a\u0006\u0002\u001a5\t1\u0001C\u0004\u0002\u001c%\u0001\r!a\u0003\u0002\u000b=$\b.\u001a:\u0015\t\u0005]\u0011q\u0004\u0005\b\u00037Q\u0001\u0019AA\u0011!\u0011\t\u0019#!\u000b\u000e\u0005\u0005\u0015\"bAA\u0014\t\u0006A!/Z:pkJ\u001cW-\u0003\u0003\u0002,\u0005\u0015\"a\u0005*fg>,(oY3J]\u001a|'/\\1uS>t\u0017A\u0002\u0013nS:,8\u000f\u0006\u0003\u0002\u0018\u0005E\u0002bBA\u000e\u0017\u0001\u0007\u0011\u0011E\u0001\u0016i>\u0014Vm]8ve\u000e,\u0017J\u001c4pe6\fG/[8o+\t\t\t#\u0001\u0003d_BLHCBA\u0006\u0003w\ti\u0004C\u0004o\u001bA\u0005\t\u0019\u00019\t\u000fel\u0001\u0013!a\u0001w\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCAA\"U\r\u0001\u0018QI\u0016\u0003\u0003\u000f\u0002B!!\u0013\u0002T5\u0011\u00111\n\u0006\u0005\u0003\u001b\ny%A\u0005v]\u000eDWmY6fI*\u0019\u0011\u0011\u000b)\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002V\u0005-#!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TCAA.U\rY\u0018QI\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005\u0005\u0004\u0003BA2\u0003[j!!!\u001a\u000b\t\u0005\u001d\u0014\u0011N\u0001\u0005Y\u0006twM\u0003\u0002\u0002l\u0005!!.\u0019<b\u0013\r1\u0018QM\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003g\u00022aTA;\u0013\r\t9\b\u0015\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003{\n\u0019\tE\u0002P\u0003\u007fJ1!!!Q\u0005\r\te.\u001f\u0005\n\u0003\u000b\u0013\u0012\u0011!a\u0001\u0003g\n1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAAF!\u0019\ti)a$\u0002~5\tq0C\u0002\u0002\u0012~\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011qSAO!\ry\u0015\u0011T\u0005\u0004\u00037\u0003&a\u0002\"p_2,\u0017M\u001c\u0005\n\u0003\u000b#\u0012\u0011!a\u0001\u0003{\n!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u0011\u0011MAR\u0011%\t))FA\u0001\u0002\u0004\t\u0019(\u0001\u0005iCND7i\u001c3f)\t\t\u0019(\u0001\u0005u_N#(/\u001b8h)\t\t\t'\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003/\u000b\t\fC\u0005\u0002\u0006b\t\t\u00111\u0001\u0002~\u0005\u0019R*\u001e;bE2,'+Z:pkJ\u001cW-\u00138g_B\u0019\u0011Q\u0002\u000e\u0014\u000bi\tI,!2\u0011\u0011\u0005m\u0016\u0011\u00199|\u0003\u0017i!!!0\u000b\u0007\u0005}\u0006+A\u0004sk:$\u0018.\\3\n\t\u0005\r\u0017Q\u0018\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003BAd\u0003\u001bl!!!3\u000b\t\u0005-\u0017\u0011N\u0001\u0003S>L1\u0001\\Ae)\t\t),A\u0003baBd\u0017\u0010\u0006\u0004\u0002\f\u0005U\u0017q\u001b\u0005\u0006]v\u0001\r\u0001\u001d\u0005\u0006sv\u0001\ra_\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\ti.!;\u0011\u000b=\u000by.a9\n\u0007\u0005\u0005\bK\u0001\u0004PaRLwN\u001c\t\u0006\u001f\u0006\u0015\bo_\u0005\u0004\u0003O\u0004&A\u0002+va2,'\u0007C\u0005\u0002lz\t\t\u00111\u0001\u0002\f\u0005\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005E\b\u0003BA2\u0003gLA!!>\u0002f\t1qJ\u00196fGR\u0014Ad\u0015;b]\u0012\fGn\u001c8f%\u0016\u001cx.\u001e:dK\u0006cGn\\2bi&|gn\u0005\u0003!\u001d~\u0013\u0017a\u00019jI\u0006!\u0001/\u001b3!\u0003-\tG\u000e\\8dCRLwN\\:\u0016\u0005\t\r\u0001#B2\u0003\u0006\t%\u0011b\u0001B\u0004[\n\u00191+Z9\u0011\t\u0005\r\"1B\u0005\u0005\u0005\u001b\t)C\u0001\nSKN|WO]2f\u00032dwnY1uS>t\u0017\u0001D1mY>\u001c\u0017\r^5p]N\u0004CC\u0002B\n\u0005+\u00119\u0002E\u0002\u0002\u000e\u0001Bq!a?&\u0001\u0004\t\u0019\bC\u0004\u0002\u0000\u0016\u0002\rAa\u0001\u00021Q|'+Z:pkJ\u001cW-\u00138g_Jl\u0017\r^5p]6\u000b\u0007/\u0006\u0002\u0003\u001eA1\u0011Oa\bq\u0003CI1A!\tx\u0005\ri\u0015\r\u001d\u000b\u0007\u0005'\u0011)Ca\n\t\u0013\u0005mx\u0005%AA\u0002\u0005M\u0004\"CA\u0000OA\u0005\t\u0019\u0001B\u0002+\t\u0011YC\u000b\u0003\u0002t\u0005\u0015SC\u0001B\u0018U\u0011\u0011\u0019!!\u0012\u0015\t\u0005u$1\u0007\u0005\n\u0003\u000bc\u0013\u0011!a\u0001\u0003g\"B!a&\u00038!I\u0011Q\u0011\u0018\u0002\u0002\u0003\u0007\u0011Q\u0010\u000b\u0005\u0003C\u0012Y\u0004C\u0005\u0002\u0006>\n\t\u00111\u0001\u0002tQ!\u0011q\u0013B \u0011%\t)IMA\u0001\u0002\u0004\ti(\u0001\u000fTi\u0006tG-\u00197p]\u0016\u0014Vm]8ve\u000e,\u0017\t\u001c7pG\u0006$\u0018n\u001c8\u0011\u0007\u00055AgE\u00035\u0005\u000f\n)\r\u0005\u0006\u0002<\u0006\u0005\u00171\u000fB\u0002\u0005'!\"Aa\u0011\u0015\r\tM!Q\nB(\u0011\u001d\tYp\u000ea\u0001\u0003gBq!a@8\u0001\u0004\u0011\u0019\u0001\u0006\u0003\u0003T\t]\u0003#B(\u0002`\nU\u0003cB(\u0002f\u0006M$1\u0001\u0005\n\u0003WD\u0014\u0011!a\u0001\u0005'\tA\u0003\u001d:fa\u0006\u0014XMU3t_V\u00148-Z:GS2,G\u0003\u0003B/\u0005K\u0012IG!\u001c\u0011\u000b=\u000byNa\u0018\u0011\t\u0005\u001d'\u0011M\u0005\u0005\u0005G\nIM\u0001\u0003GS2,\u0007B\u0002B4u\u0001\u0007\u0001/A\u0007d_6\u0004xN\\3oi:\u000bW.\u001a\u0005\b\u0005WR\u0004\u0019\u0001B\u000f\u0003%\u0011Xm]8ve\u000e,7\u000fC\u0004\u0003pi\u0002\rAa\u0018\u0002\u0007\u0011L'/A\u000exe&$XMU3t_V\u00148-Z!mY>\u001c\u0017\r^5p]*\u001bxN\\\u000b\u0005\u0005k\u0012)\t\u0006\u0004\u0003x\tu$\u0011\u0013\t\u0004\u001f\ne\u0014b\u0001B>!\n!QK\\5u\u0011\u001d\typ\u000fa\u0001\u0005\u007f\u0002Ra\u0019B\u0003\u0005\u0003\u0003BAa!\u0003\u00062\u0001Aa\u0002BDw\t\u0007!\u0011\u0012\u0002\u0002)F!!1RA?!\ry%QR\u0005\u0004\u0005\u001f\u0003&a\u0002(pi\"Lgn\u001a\u0005\b\u0005'[\u0004\u0019\u0001B0\u0003!Q7o\u001c8GS2,\u0017!\u0003;p\u001bV$\u0018M\u00197f)\u0011\u0011IJa'\u0011\rE\u0014y\u0002]A\u0006\u0011\u001d\u0011i\n\u0010a\u0001\u0005;\t!#[7nkR\f'\r\\3SKN|WO]2fg\u00061bm\u001c:nCR\u0014Vm]8ve\u000e,7\u000fR3uC&d7\u000fF\u0003q\u0005G\u00139\u000bC\u0004\u0003&v\u0002\rA!\b\u0002\u0011U\u001cX\rZ%oM>DqA!+>\u0001\u0004\u0011i\"\u0001\u0005ge\u0016,\u0017J\u001c4p\u0003a1wN]7biJ+7o\\;sG\u0016\u001c\u0018\t\u001a3sKN\u001cXm\u001d\u000b\u0004a\n=\u0006b\u0002B6}\u0001\u0007!QD\u0001\u0014M>\u0014X.\u0019;SKN|WO]2fgV\u001bX\r\u001a\u000b\u0006a\nU&1\u0018\u0005\b\u0005o{\u0004\u0019\u0001B]\u00039\u0011Xm]8ve\u000e,7\u000fV8uC2\u0004b!\u001dB\u0010a\u0006M\u0004b\u0002B_\u007f\u0001\u0007!\u0011X\u0001\u000ee\u0016\u001cx.\u001e:dKN,6/\u001a3\u00025\u0019|'/\\1u%\u0016\u001cx.\u001e:dKJ+\u0017/^5sK6,g\u000e^:\u0015\u0007A\u0014\u0019\rC\u0004\u0003F\u0002\u0003\rAa2\u0002\u0019I,\u0017/^5sK6,g\u000e^:\u0011\u000b\r\u0014)A!3\u0011\t\u0005\r\"1Z\u0005\u0005\u0005\u001b\f)CA\nSKN|WO]2f%\u0016\fX/\u001b:f[\u0016tG\u000f"
)
public final class StandaloneResourceUtils {
   public static String formatResourceRequirements(final Seq requirements) {
      return StandaloneResourceUtils$.MODULE$.formatResourceRequirements(requirements);
   }

   public static String formatResourcesUsed(final Map resourcesTotal, final Map resourcesUsed) {
      return StandaloneResourceUtils$.MODULE$.formatResourcesUsed(resourcesTotal, resourcesUsed);
   }

   public static String formatResourcesAddresses(final Map resources) {
      return StandaloneResourceUtils$.MODULE$.formatResourcesAddresses(resources);
   }

   public static String formatResourcesDetails(final Map usedInfo, final Map freeInfo) {
      return StandaloneResourceUtils$.MODULE$.formatResourcesDetails(usedInfo, freeInfo);
   }

   public static Map toMutable(final Map immutableResources) {
      return StandaloneResourceUtils$.MODULE$.toMutable(immutableResources);
   }

   public static Option prepareResourcesFile(final String componentName, final Map resources, final File dir) {
      return StandaloneResourceUtils$.MODULE$.prepareResourcesFile(componentName, resources, dir);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return StandaloneResourceUtils$.MODULE$.LogStringContext(sc);
   }

   public static class MutableResourceInfo implements Product, Serializable {
      private final String name;
      private final HashSet addresses;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String name() {
         return this.name;
      }

      public HashSet addresses() {
         return this.addresses;
      }

      public MutableResourceInfo $plus(final MutableResourceInfo other) {
         boolean var3;
         Predef var10000;
         label17: {
            label16: {
               var10000 = .MODULE$;
               String var10001 = this.name();
               String var2 = other.name();
               if (var10001 == null) {
                  if (var2 == null) {
                     break label16;
                  }
               } else if (var10001.equals(var2)) {
                  break label16;
               }

               var3 = false;
               break label17;
            }

            var3 = true;
         }

         var10000.assert(var3, () -> {
            String var10000 = this.name();
            return "Inconsistent resource name, expected " + var10000 + ", but got " + other.name();
         });
         other.addresses().foreach((elem) -> BoxesRunTime.boxToBoolean($anonfun$$plus$2(this, elem)));
         return this;
      }

      public MutableResourceInfo $plus(final ResourceInformation other) {
         boolean var3;
         Predef var10000;
         label17: {
            label16: {
               var10000 = .MODULE$;
               String var10001 = this.name();
               String var2 = other.name();
               if (var10001 == null) {
                  if (var2 == null) {
                     break label16;
                  }
               } else if (var10001.equals(var2)) {
                  break label16;
               }

               var3 = false;
               break label17;
            }

            var3 = true;
         }

         var10000.assert(var3, () -> {
            String var10000 = this.name();
            return "Inconsistent resource name, expected " + var10000 + ", but got " + other.name();
         });
         scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.refArrayOps((Object[])other.addresses()), (elem) -> BoxesRunTime.boxToBoolean($anonfun$$plus$4(this, elem)));
         return this;
      }

      public MutableResourceInfo $minus(final ResourceInformation other) {
         boolean var3;
         Predef var10000;
         label17: {
            label16: {
               var10000 = .MODULE$;
               String var10001 = this.name();
               String var2 = other.name();
               if (var10001 == null) {
                  if (var2 == null) {
                     break label16;
                  }
               } else if (var10001.equals(var2)) {
                  break label16;
               }

               var3 = false;
               break label17;
            }

            var3 = true;
         }

         var10000.assert(var3, () -> {
            String var10000 = this.name();
            return "Inconsistent resource name, expected " + var10000 + ", but got " + other.name();
         });
         scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.refArrayOps((Object[])other.addresses()), (elem) -> BoxesRunTime.boxToBoolean($anonfun$$minus$2(this, elem)));
         return this;
      }

      public ResourceInformation toResourceInformation() {
         return new ResourceInformation(this.name(), (String[])this.addresses().toArray(scala.reflect.ClassTag..MODULE$.apply(String.class)));
      }

      public MutableResourceInfo copy(final String name, final HashSet addresses) {
         return new MutableResourceInfo(name, addresses);
      }

      public String copy$default$1() {
         return this.name();
      }

      public HashSet copy$default$2() {
         return this.addresses();
      }

      public String productPrefix() {
         return "MutableResourceInfo";
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
         return x$1 instanceof MutableResourceInfo;
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
               if (x$1 instanceof MutableResourceInfo) {
                  label48: {
                     MutableResourceInfo var4 = (MutableResourceInfo)x$1;
                     String var10000 = this.name();
                     String var5 = var4.name();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label48;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label48;
                     }

                     HashSet var7 = this.addresses();
                     HashSet var6 = var4.addresses();
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

      // $FF: synthetic method
      public static final boolean $anonfun$$plus$2(final MutableResourceInfo $this, final String elem) {
         return $this.addresses().add(elem);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$$plus$4(final MutableResourceInfo $this, final String elem) {
         return $this.addresses().add(elem);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$$minus$2(final MutableResourceInfo $this, final String elem) {
         return $this.addresses().remove(elem);
      }

      public MutableResourceInfo(final String name, final HashSet addresses) {
         this.name = name;
         this.addresses = addresses;
         Product.$init$(this);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class MutableResourceInfo$ extends AbstractFunction2 implements Serializable {
      public static final MutableResourceInfo$ MODULE$ = new MutableResourceInfo$();

      public final String toString() {
         return "MutableResourceInfo";
      }

      public MutableResourceInfo apply(final String name, final HashSet addresses) {
         return new MutableResourceInfo(name, addresses);
      }

      public Option unapply(final MutableResourceInfo x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.name(), x$0.addresses())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(MutableResourceInfo$.class);
      }
   }

   public static class StandaloneResourceAllocation implements Product, Serializable {
      private final int pid;
      private final Seq allocations;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int pid() {
         return this.pid;
      }

      public Seq allocations() {
         return this.allocations;
      }

      public Map toResourceInformationMap() {
         return ((IterableOnceOps)this.allocations().map((allocation) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(allocation.id().resourceName()), allocation.toResourceInformation()))).toMap(scala..less.colon.less..MODULE$.refl());
      }

      public StandaloneResourceAllocation copy(final int pid, final Seq allocations) {
         return new StandaloneResourceAllocation(pid, allocations);
      }

      public int copy$default$1() {
         return this.pid();
      }

      public Seq copy$default$2() {
         return this.allocations();
      }

      public String productPrefix() {
         return "StandaloneResourceAllocation";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return BoxesRunTime.boxToInteger(this.pid());
            }
            case 1 -> {
               return this.allocations();
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
         return x$1 instanceof StandaloneResourceAllocation;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "pid";
            }
            case 1 -> {
               return "allocations";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.pid());
         var1 = Statics.mix(var1, Statics.anyHash(this.allocations()));
         return Statics.finalizeHash(var1, 2);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label51: {
               if (x$1 instanceof StandaloneResourceAllocation) {
                  StandaloneResourceAllocation var4 = (StandaloneResourceAllocation)x$1;
                  if (this.pid() == var4.pid()) {
                     label44: {
                        Seq var10000 = this.allocations();
                        Seq var5 = var4.allocations();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label44;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label44;
                        }

                        if (var4.canEqual(this)) {
                           break label51;
                        }
                     }
                  }
               }

               var6 = false;
               return var6;
            }
         }

         var6 = true;
         return var6;
      }

      public StandaloneResourceAllocation(final int pid, final Seq allocations) {
         this.pid = pid;
         this.allocations = allocations;
         Product.$init$(this);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class StandaloneResourceAllocation$ extends AbstractFunction2 implements Serializable {
      public static final StandaloneResourceAllocation$ MODULE$ = new StandaloneResourceAllocation$();

      public final String toString() {
         return "StandaloneResourceAllocation";
      }

      public StandaloneResourceAllocation apply(final int pid, final Seq allocations) {
         return new StandaloneResourceAllocation(pid, allocations);
      }

      public Option unapply(final StandaloneResourceAllocation x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToInteger(x$0.pid()), x$0.allocations())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(StandaloneResourceAllocation$.class);
      }
   }
}

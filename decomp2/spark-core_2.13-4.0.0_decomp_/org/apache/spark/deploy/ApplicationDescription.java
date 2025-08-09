package org.apache.spark.deploy;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.resource.ResourceUtils$;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.math.Ordering.String.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\tUc!B\u001d;\u0001r\u0012\u0005\u0002C-\u0001\u0005+\u0007I\u0011\u0001.\t\u0011\r\u0004!\u0011#Q\u0001\nmC\u0001\u0002\u001a\u0001\u0003\u0016\u0004%\t!\u001a\u0005\tY\u0002\u0011\t\u0012)A\u0005M\"AQ\u000e\u0001BK\u0002\u0013\u0005a\u000e\u0003\u0005t\u0001\tE\t\u0015!\u0003p\u0011!!\bA!f\u0001\n\u0003Q\u0006\u0002C;\u0001\u0005#\u0005\u000b\u0011B.\t\u0011Y\u0004!Q3A\u0005\u0002]D\u0001B \u0001\u0003\u0012\u0003\u0006I\u0001\u001f\u0005\n\u007f\u0002\u0011)\u001a!C\u0001\u0003\u0003A!\"!\u0006\u0001\u0005#\u0005\u000b\u0011BA\u0002\u0011)\t9\u0002\u0001BK\u0002\u0013\u0005\u0011\u0011\u0004\u0005\u000b\u0003;\u0001!\u0011#Q\u0001\n\u0005m\u0001\"CA\u0010\u0001\tU\r\u0011\"\u0001f\u0011%\t\t\u0003\u0001B\tB\u0003%a\rC\u0005\u0002$\u0001\u0011)\u001a!C\u00015\"I\u0011Q\u0005\u0001\u0003\u0012\u0003\u0006Ia\u0017\u0005\b\u0003O\u0001A\u0011AA\u0015\u0011\u001d\ty\u0004\u0001C\u0001\u0003\u0003Ba!a\u0011\u0001\t\u0003)\u0007bBA#\u0001\u0011\u0005\u0011q\t\u0005\b\u0003+\u0002A\u0011IA,\u0011%\tI\u0006AA\u0001\n\u0003\tY\u0006C\u0005\u0002p\u0001\t\n\u0011\"\u0001\u0002r!I\u0011q\u0011\u0001\u0012\u0002\u0013\u0005\u0011\u0011\u0012\u0005\n\u0003\u001b\u0003\u0011\u0013!C\u0001\u0003\u001fC\u0011\"a%\u0001#\u0003%\t!!\u001d\t\u0013\u0005U\u0005!%A\u0005\u0002\u0005]\u0005\"CAN\u0001E\u0005I\u0011AAO\u0011%\t\t\u000bAI\u0001\n\u0003\t\u0019\u000bC\u0005\u0002(\u0002\t\n\u0011\"\u0001\u0002\n\"I\u0011\u0011\u0016\u0001\u0012\u0002\u0013\u0005\u0011\u0011\u000f\u0005\n\u0003W\u0003\u0011\u0011!C!\u0003[C\u0011\"!/\u0001\u0003\u0003%\t!!\u0011\t\u0013\u0005m\u0006!!A\u0005\u0002\u0005u\u0006\"CAe\u0001\u0005\u0005I\u0011IAf\u0011%\tI\u000eAA\u0001\n\u0003\tY\u000eC\u0005\u0002f\u0002\t\t\u0011\"\u0011\u0002h\"I\u00111\u001e\u0001\u0002\u0002\u0013\u0005\u0013Q\u001e\u0005\n\u0003_\u0004\u0011\u0011!C!\u0003c<!\"!>;\u0003\u0003E\t\u0001PA|\r%I$(!A\t\u0002q\nI\u0010C\u0004\u0002(-\"\tA!\u0005\t\u0013\u0005U3&!A\u0005F\tM\u0001\"\u0003B\u000bW\u0005\u0005I\u0011\u0011B\f\u0011%\u0011YcKI\u0001\n\u0003\ti\nC\u0005\u0003.-\n\n\u0011\"\u0001\u0002$\"I!qF\u0016\u0012\u0002\u0013\u0005\u0011\u0011\u0012\u0005\n\u0005cY\u0013\u0013!C\u0001\u0003cB\u0011Ba\r,\u0003\u0003%\tI!\u000e\t\u0013\t\r3&%A\u0005\u0002\u0005u\u0005\"\u0003B#WE\u0005I\u0011AAR\u0011%\u00119eKI\u0001\n\u0003\tI\tC\u0005\u0003J-\n\n\u0011\"\u0001\u0002r!I!1J\u0016\u0002\u0002\u0013%!Q\n\u0002\u0017\u0003B\u0004H.[2bi&|g\u000eR3tGJL\u0007\u000f^5p]*\u00111\bP\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005ur\u0014!B:qCJ\\'BA A\u0003\u0019\t\u0007/Y2iK*\t\u0011)A\u0002pe\u001e\u001cB\u0001A\"J\u0019B\u0011AiR\u0007\u0002\u000b*\ta)A\u0003tG\u0006d\u0017-\u0003\u0002I\u000b\n1\u0011I\\=SK\u001a\u0004\"\u0001\u0012&\n\u0005-+%a\u0002)s_\u0012,8\r\u001e\t\u0003\u001bZs!A\u0014+\u000f\u0005=\u001bV\"\u0001)\u000b\u0005E\u0013\u0016A\u0002\u001fs_>$hh\u0001\u0001\n\u0003\u0019K!!V#\u0002\u000fA\f7m[1hK&\u0011q\u000b\u0017\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003+\u0016\u000bAA\\1nKV\t1\f\u0005\u0002]A:\u0011QL\u0018\t\u0003\u001f\u0016K!aX#\u0002\rA\u0013X\rZ3g\u0013\t\t'M\u0001\u0004TiJLgn\u001a\u0006\u0003?\u0016\u000bQA\\1nK\u0002\n\u0001\"\\1y\u0007>\u0014Xm]\u000b\u0002MB\u0019AiZ5\n\u0005!,%AB(qi&|g\u000e\u0005\u0002EU&\u00111.\u0012\u0002\u0004\u0013:$\u0018!C7bq\u000e{'/Z:!\u0003\u001d\u0019w.\\7b]\u0012,\u0012a\u001c\t\u0003aFl\u0011AO\u0005\u0003ej\u0012qaQ8n[\u0006tG-\u0001\u0005d_6l\u0017M\u001c3!\u0003!\t\u0007\u000f]+j+Jd\u0017!C1qaVKWK\u001d7!\u00039!WMZ1vYR\u0004&o\u001c4jY\u0016,\u0012\u0001\u001f\t\u0003srl\u0011A\u001f\u0006\u0003wr\n\u0001B]3t_V\u00148-Z\u0005\u0003{j\u0014qBU3t_V\u00148-\u001a)s_\u001aLG.Z\u0001\u0010I\u00164\u0017-\u001e7u!J|g-\u001b7fA\u0005YQM^3oi2{w\rR5s+\t\t\u0019\u0001\u0005\u0003EO\u0006\u0015\u0001\u0003BA\u0004\u0003#i!!!\u0003\u000b\t\u0005-\u0011QB\u0001\u0004]\u0016$(BAA\b\u0003\u0011Q\u0017M^1\n\t\u0005M\u0011\u0011\u0002\u0002\u0004+JK\u0015\u0001D3wK:$Hj\\4ESJ\u0004\u0013!D3wK:$Hj\\4D_\u0012,7-\u0006\u0002\u0002\u001cA\u0019AiZ.\u0002\u001d\u00154XM\u001c;M_\u001e\u001cu\u000eZ3dA\u0005!\u0012N\\5uS\u0006dW\t_3dkR|'\u000fT5nSR\fQ#\u001b8ji&\fG.\u0012=fGV$xN\u001d'j[&$\b%\u0001\u0003vg\u0016\u0014\u0018!B;tKJ\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u000b\u0002,\u00055\u0012qFA\u0019\u0003g\t)$a\u000e\u0002:\u0005m\u0012Q\b\t\u0003a\u0002AQ!W\nA\u0002mCQ\u0001Z\nA\u0002\u0019DQ!\\\nA\u0002=DQ\u0001^\nA\u0002mCQA^\nA\u0002aD\u0001b`\n\u0011\u0002\u0003\u0007\u00111\u0001\u0005\n\u0003/\u0019\u0002\u0013!a\u0001\u00037A\u0001\"a\b\u0014!\u0003\u0005\rA\u001a\u0005\t\u0003G\u0019\u0002\u0013!a\u00017\u0006\u0019R.Z7pef\u0004VM]#yK\u000e,Ho\u001c:N\u0005V\t\u0011.\u0001\td_J,7\u000fU3s\u000bb,7-\u001e;pe\u00069\"/Z:pkJ\u001cWMU3rgB+'/\u0012=fGV$xN]\u000b\u0003\u0003\u0013\u0002R!TA&\u0003\u001fJ1!!\u0014Y\u0005\r\u0019V-\u001d\t\u0004s\u0006E\u0013bAA*u\n\u0019\"+Z:pkJ\u001cWMU3rk&\u0014X-\\3oi\u0006AAo\\*ue&tw\rF\u0001\\\u0003\u0011\u0019w\u000e]=\u0015)\u0005-\u0012QLA0\u0003C\n\u0019'!\u001a\u0002h\u0005%\u00141NA7\u0011\u001dI\u0006\u0004%AA\u0002mCq\u0001\u001a\r\u0011\u0002\u0003\u0007a\rC\u0004n1A\u0005\t\u0019A8\t\u000fQD\u0002\u0013!a\u00017\"9a\u000f\u0007I\u0001\u0002\u0004A\b\u0002C@\u0019!\u0003\u0005\r!a\u0001\t\u0013\u0005]\u0001\u0004%AA\u0002\u0005m\u0001\u0002CA\u00101A\u0005\t\u0019\u00014\t\u0011\u0005\r\u0002\u0004%AA\u0002m\u000babY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002t)\u001a1,!\u001e,\u0005\u0005]\u0004\u0003BA=\u0003\u0007k!!a\u001f\u000b\t\u0005u\u0014qP\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!!F\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003\u000b\u000bYHA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0006\u0002\u0002\f*\u001aa-!\u001e\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\u0011\u0011\u0011\u0013\u0016\u0004_\u0006U\u0014AD2paf$C-\u001a4bk2$H\u0005N\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00136+\t\tIJK\u0002y\u0003k\nabY8qs\u0012\"WMZ1vYR$c'\u0006\u0002\u0002 *\"\u00111AA;\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uI]*\"!!*+\t\u0005m\u0011QO\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00139\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIe\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAAX!\u0011\t\t,a.\u000e\u0005\u0005M&\u0002BA[\u0003\u001b\tA\u0001\\1oO&\u0019\u0011-a-\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011qXAc!\r!\u0015\u0011Y\u0005\u0004\u0003\u0007,%aA!os\"A\u0011q\u0019\u0013\u0002\u0002\u0003\u0007\u0011.A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u001b\u0004b!a4\u0002V\u0006}VBAAi\u0015\r\t\u0019.R\u0001\u000bG>dG.Z2uS>t\u0017\u0002BAl\u0003#\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011Q\\Ar!\r!\u0015q\\\u0005\u0004\u0003C,%a\u0002\"p_2,\u0017M\u001c\u0005\n\u0003\u000f4\u0013\u0011!a\u0001\u0003\u007f\u000b!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u0011qVAu\u0011!\t9mJA\u0001\u0002\u0004I\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003%\fa!Z9vC2\u001cH\u0003BAo\u0003gD\u0011\"a2*\u0003\u0003\u0005\r!a0\u0002-\u0005\u0003\b\u000f\\5dCRLwN\u001c#fg\u000e\u0014\u0018\u000e\u001d;j_:\u0004\"\u0001]\u0016\u0014\u000b-\nYPa\u0002\u0011#\u0005u(1A.g_nC\u00181AA\u000eMn\u000bY#\u0004\u0002\u0002\u0000*\u0019!\u0011A#\u0002\u000fI,h\u000e^5nK&!!QAA\u0000\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\u000f\t\u0005\u0005\u0013\u0011y!\u0004\u0002\u0003\f)!!QBA\u0007\u0003\tIw.C\u0002X\u0005\u0017!\"!a>\u0015\u0005\u0005=\u0016!B1qa2LH\u0003FA\u0016\u00053\u0011YB!\b\u0003 \t\u0005\"1\u0005B\u0013\u0005O\u0011I\u0003C\u0003Z]\u0001\u00071\fC\u0003e]\u0001\u0007a\rC\u0003n]\u0001\u0007q\u000eC\u0003u]\u0001\u00071\fC\u0003w]\u0001\u0007\u0001\u0010\u0003\u0005\u0000]A\u0005\t\u0019AA\u0002\u0011%\t9B\fI\u0001\u0002\u0004\tY\u0002\u0003\u0005\u0002 9\u0002\n\u00111\u0001g\u0011!\t\u0019C\fI\u0001\u0002\u0004Y\u0016aD1qa2LH\u0005Z3gCVdG\u000f\n\u001c\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uI]\nq\"\u00199qYf$C-\u001a4bk2$H\u0005O\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%s\u00059QO\\1qa2LH\u0003\u0002B\u001c\u0005\u007f\u0001B\u0001R4\u0003:AqAIa\u000f\\M>\\\u00060a\u0001\u0002\u001c\u0019\\\u0016b\u0001B\u001f\u000b\n1A+\u001e9mKfB\u0011B!\u00114\u0003\u0003\u0005\r!a\u000b\u0002\u0007a$\u0003'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEN\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001c\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00139\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%s\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011!q\n\t\u0005\u0003c\u0013\t&\u0003\u0003\u0003T\u0005M&AB(cU\u0016\u001cG\u000f"
)
public class ApplicationDescription implements Product, Serializable {
   private final String name;
   private final Option maxCores;
   private final Command command;
   private final String appUiUrl;
   private final ResourceProfile defaultProfile;
   private final Option eventLogDir;
   private final Option eventLogCodec;
   private final Option initialExecutorLimit;
   private final String user;

   public static String $lessinit$greater$default$9() {
      return ApplicationDescription$.MODULE$.$lessinit$greater$default$9();
   }

   public static Option $lessinit$greater$default$8() {
      return ApplicationDescription$.MODULE$.$lessinit$greater$default$8();
   }

   public static Option $lessinit$greater$default$7() {
      return ApplicationDescription$.MODULE$.$lessinit$greater$default$7();
   }

   public static Option $lessinit$greater$default$6() {
      return ApplicationDescription$.MODULE$.$lessinit$greater$default$6();
   }

   public static Option unapply(final ApplicationDescription x$0) {
      return ApplicationDescription$.MODULE$.unapply(x$0);
   }

   public static String apply$default$9() {
      return ApplicationDescription$.MODULE$.apply$default$9();
   }

   public static Option apply$default$8() {
      return ApplicationDescription$.MODULE$.apply$default$8();
   }

   public static Option apply$default$7() {
      return ApplicationDescription$.MODULE$.apply$default$7();
   }

   public static Option apply$default$6() {
      return ApplicationDescription$.MODULE$.apply$default$6();
   }

   public static ApplicationDescription apply(final String name, final Option maxCores, final Command command, final String appUiUrl, final ResourceProfile defaultProfile, final Option eventLogDir, final Option eventLogCodec, final Option initialExecutorLimit, final String user) {
      return ApplicationDescription$.MODULE$.apply(name, maxCores, command, appUiUrl, defaultProfile, eventLogDir, eventLogCodec, initialExecutorLimit, user);
   }

   public static Function1 tupled() {
      return ApplicationDescription$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ApplicationDescription$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String name() {
      return this.name;
   }

   public Option maxCores() {
      return this.maxCores;
   }

   public Command command() {
      return this.command;
   }

   public String appUiUrl() {
      return this.appUiUrl;
   }

   public ResourceProfile defaultProfile() {
      return this.defaultProfile;
   }

   public Option eventLogDir() {
      return this.eventLogDir;
   }

   public Option eventLogCodec() {
      return this.eventLogCodec;
   }

   public Option initialExecutorLimit() {
      return this.initialExecutorLimit;
   }

   public String user() {
      return this.user;
   }

   public int memoryPerExecutorMB() {
      return BoxesRunTime.unboxToInt(this.defaultProfile().getExecutorMemory().map((JFunction1.mcIJ.sp)(x$1) -> (int)x$1).getOrElse((JFunction0.mcI.sp)() -> 1024));
   }

   public Option coresPerExecutor() {
      return this.defaultProfile().getExecutorCores();
   }

   public Seq resourceReqsPerExecutor() {
      return ResourceUtils$.MODULE$.executorResourceRequestToRequirement((Seq)this.defaultProfile().getCustomExecutorResources().values().toSeq().sortBy((x$2) -> x$2.resourceName(), .MODULE$));
   }

   public String toString() {
      return "ApplicationDescription(" + this.name() + ")";
   }

   public ApplicationDescription copy(final String name, final Option maxCores, final Command command, final String appUiUrl, final ResourceProfile defaultProfile, final Option eventLogDir, final Option eventLogCodec, final Option initialExecutorLimit, final String user) {
      return new ApplicationDescription(name, maxCores, command, appUiUrl, defaultProfile, eventLogDir, eventLogCodec, initialExecutorLimit, user);
   }

   public String copy$default$1() {
      return this.name();
   }

   public Option copy$default$2() {
      return this.maxCores();
   }

   public Command copy$default$3() {
      return this.command();
   }

   public String copy$default$4() {
      return this.appUiUrl();
   }

   public ResourceProfile copy$default$5() {
      return this.defaultProfile();
   }

   public Option copy$default$6() {
      return this.eventLogDir();
   }

   public Option copy$default$7() {
      return this.eventLogCodec();
   }

   public Option copy$default$8() {
      return this.initialExecutorLimit();
   }

   public String copy$default$9() {
      return this.user();
   }

   public String productPrefix() {
      return "ApplicationDescription";
   }

   public int productArity() {
      return 9;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.name();
         }
         case 1 -> {
            return this.maxCores();
         }
         case 2 -> {
            return this.command();
         }
         case 3 -> {
            return this.appUiUrl();
         }
         case 4 -> {
            return this.defaultProfile();
         }
         case 5 -> {
            return this.eventLogDir();
         }
         case 6 -> {
            return this.eventLogCodec();
         }
         case 7 -> {
            return this.initialExecutorLimit();
         }
         case 8 -> {
            return this.user();
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
      return x$1 instanceof ApplicationDescription;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "name";
         }
         case 1 -> {
            return "maxCores";
         }
         case 2 -> {
            return "command";
         }
         case 3 -> {
            return "appUiUrl";
         }
         case 4 -> {
            return "defaultProfile";
         }
         case 5 -> {
            return "eventLogDir";
         }
         case 6 -> {
            return "eventLogCodec";
         }
         case 7 -> {
            return "initialExecutorLimit";
         }
         case 8 -> {
            return "user";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var22;
      if (this != x$1) {
         label111: {
            if (x$1 instanceof ApplicationDescription) {
               label104: {
                  ApplicationDescription var4 = (ApplicationDescription)x$1;
                  String var10000 = this.name();
                  String var5 = var4.name();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label104;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label104;
                  }

                  Option var14 = this.maxCores();
                  Option var6 = var4.maxCores();
                  if (var14 == null) {
                     if (var6 != null) {
                        break label104;
                     }
                  } else if (!var14.equals(var6)) {
                     break label104;
                  }

                  Command var15 = this.command();
                  Command var7 = var4.command();
                  if (var15 == null) {
                     if (var7 != null) {
                        break label104;
                     }
                  } else if (!var15.equals(var7)) {
                     break label104;
                  }

                  String var16 = this.appUiUrl();
                  String var8 = var4.appUiUrl();
                  if (var16 == null) {
                     if (var8 != null) {
                        break label104;
                     }
                  } else if (!var16.equals(var8)) {
                     break label104;
                  }

                  ResourceProfile var17 = this.defaultProfile();
                  ResourceProfile var9 = var4.defaultProfile();
                  if (var17 == null) {
                     if (var9 != null) {
                        break label104;
                     }
                  } else if (!var17.equals(var9)) {
                     break label104;
                  }

                  Option var18 = this.eventLogDir();
                  Option var10 = var4.eventLogDir();
                  if (var18 == null) {
                     if (var10 != null) {
                        break label104;
                     }
                  } else if (!var18.equals(var10)) {
                     break label104;
                  }

                  var18 = this.eventLogCodec();
                  Option var11 = var4.eventLogCodec();
                  if (var18 == null) {
                     if (var11 != null) {
                        break label104;
                     }
                  } else if (!var18.equals(var11)) {
                     break label104;
                  }

                  var18 = this.initialExecutorLimit();
                  Option var12 = var4.initialExecutorLimit();
                  if (var18 == null) {
                     if (var12 != null) {
                        break label104;
                     }
                  } else if (!var18.equals(var12)) {
                     break label104;
                  }

                  String var21 = this.user();
                  String var13 = var4.user();
                  if (var21 == null) {
                     if (var13 != null) {
                        break label104;
                     }
                  } else if (!var21.equals(var13)) {
                     break label104;
                  }

                  if (var4.canEqual(this)) {
                     break label111;
                  }
               }
            }

            var22 = false;
            return var22;
         }
      }

      var22 = true;
      return var22;
   }

   public ApplicationDescription(final String name, final Option maxCores, final Command command, final String appUiUrl, final ResourceProfile defaultProfile, final Option eventLogDir, final Option eventLogCodec, final Option initialExecutorLimit, final String user) {
      this.name = name;
      this.maxCores = maxCores;
      this.command = command;
      this.appUiUrl = appUiUrl;
      this.defaultProfile = defaultProfile;
      this.eventLogDir = eventLogDir;
      this.eventLogCodec = eventLogCodec;
      this.initialExecutorLimit = initialExecutorLimit;
      this.user = user;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

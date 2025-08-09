package scala.util;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import scala.Console$;
import scala.Function0;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Option$;
import scala.Tuple2;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.Nil$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null$;
import scala.runtime.StructuralCallSite;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uh\u0001\u0003 @!\u0003\r\t!Q\"\t\u000b!\u0003A\u0011\u0001&\t\u000b9\u0003a\u0011C(\t\u000bm\u0003a\u0011\u0003/\t\u000f1\u0004!\u0019!C\t[\"AQ\u000f\u0001EC\u0002\u0013Ea\u000fC\u0003}\u0001\u0011%Q\u0010C\u0004\u0002\f\u0001!\t!!\u0004\t\u000f\u0005e\u0001\u0001\"\u0001\u0002\u001c!9\u00111\u0005\u0001\u0005\u0002\u0005\u0015\u0002bBA\u0018\u0001\u0011\u0005\u0011\u0011\u0007\u0005\b\u0003k\u0001A\u0011AA\u001c\u0011\u001d\tY\u0004\u0001C\u0001\u0003{Aq!a\u0012\u0001\t\u0003\tI\u0005C\u0004\u0002N\u0001!\t!a\u0014\t\u000f\u0005U\u0003\u0001\"\u0001\u0002X!9\u00111\f\u0001\u0005\u0002\u0005u\u0003bBA2\u0001\u0011\u0005\u0011Q\r\u0005\b\u0003S\u0002A\u0011AA6\u0011\u001d\t)\b\u0001C\u0001\u0003oBq!! \u0001\t\u0003\ty\bC\u0004\u0002\u0004\u0002!\t!!\"\t\u0013\u0005%\u0005A1A\u0005\u0002\u0005-\u0005\"CAG\u0001\t\u0007I\u0011AAF\u0011\u0019\ty\t\u0001C\u0001\u001f\"A\u0011\u0011\u0013\u0001C\u0002\u0013\u0005Q\u000e\u0003\u0005\u0002\u0014\u0002\u0011\r\u0011\"\u0001P\u0011\u0019\t)\n\u0001C\u0001\u001f\"1\u0011q\u0013\u0001\u0005\u0002=Ca!!'\u0001\t\u0003i\u0007BBAN\u0001\u0011\u0005Q\u000e\u0003\u0004\u0002\u001e\u0002!\t!\u001c\u0005\u0007\u0003?\u0003A\u0011A7\t\r\u0005\u0005\u0006\u0001\"\u0001n\u0011\u0019\t\u0019\u000b\u0001C\u0001[\"1\u0011Q\u0015\u0001\u0005\u00025Da!a*\u0001\t\u0003i\u0007BBAU\u0001\u0011\u0005Q\u000e\u0003\u0004\u0002,\u0002!\t!\u001c\u0005\u0007\u0003[\u0003A\u0011A7\t\r\u0005=\u0006\u0001\"\u0001n\u0011\u0019\t\t\f\u0001C\u0001[\"1\u00111\u0017\u0001\u0005\u00025Da!!.\u0001\t\u0003i\u0007BBA\\\u0001\u0011\u0005Q\u000e\u0003\u0004\u0002:\u0002!\t!\u001c\u0005\u0007\u0003w\u0003A\u0011A7\t\r\u0005u\u0006\u0001\"\u0001n\u0011)\ty\f\u0001EC\u0002\u0013\u0005\u0011\u0011\u0019\u0005\u000b\u0003\u0007\u0004\u0001R1A\u0005\u0002\u0005\u0005\u0007BCAc\u0001!\u0015\r\u0011\"\u0001\u0002B\"Y\u0011q\u0019\u0001\t\u0006\u0004%\t!QAa\u0011!\tI\r\u0001C\u0001\u0003\u0006\u0005\u0007bCAf\u0001!\u0015\r\u0011\"\u0001B\u0003\u0003Da!!4\u0001\t\u0003i\u0007\u0002CAh\u0001\u0011\u0005\u0011)!5\t\r\u0005]\u0007\u0001\"\u0001n\u0011\u0019\tI\u000e\u0001C\u0001[\"1\u00111\u001c\u0001\u0005\u00025Dq!!8\u0001\t\u0003\ty\u000eC\u0004\u0002^\u0002!\t!!:\t\u000f\u0005=\b\u0001\"\u0001\u0002r\ny\u0001K]8qKJ$\u0018.Z:Ue\u0006LGO\u0003\u0002A\u0003\u0006!Q\u000f^5m\u0015\u0005\u0011\u0015!B:dC2\f7C\u0001\u0001E!\t)e)D\u0001B\u0013\t9\u0015I\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\t1\n\u0005\u0002F\u0019&\u0011Q*\u0011\u0002\u0005+:LG/\u0001\u0007qe>\u00048)\u0019;fO>\u0014\u00180F\u0001Q!\t\t\u0006L\u0004\u0002S-B\u00111+Q\u0007\u0002)*\u0011Q+S\u0001\u0007yI|w\u000e\u001e \n\u0005]\u000b\u0015A\u0002)sK\u0012,g-\u0003\u0002Z5\n11\u000b\u001e:j]\u001eT!aV!\u0002\u001dAL7m\u001b&be\n\u000b7/\u001a3P]V\tQ\f\r\u0002_GB\u0019\u0011kX1\n\u0005\u0001T&!B\"mCN\u001c\bC\u00012d\u0019\u0001!\u0011\u0002Z\u0002\u0002\u0002\u0003\u0005)\u0011A3\u0003\u0007}#3'\u0005\u0002gSB\u0011QiZ\u0005\u0003Q\u0006\u0013qAT8uQ&tw\r\u0005\u0002FU&\u00111.\u0011\u0002\u0004\u0003:L\u0018\u0001\u00049s_B4\u0015\u000e\\3oC6,W#\u00018\u0011\u0005=$X\"\u00019\u000b\u0005E\u0014\u0018\u0001\u00027b]\u001eT\u0011a]\u0001\u0005U\u00064\u0018-\u0003\u0002Za\u0006Q1oY1mCB\u0013x\u000e]:\u0016\u0003]\u0004\"\u0001\u001f>\u000e\u0003eT!\u0001\u0011:\n\u0005mL(A\u0003)s_B,'\u000f^5fg\u0006q\u0011/^5fi2LH)[:q_N,G\u0003B&\u007f\u0003\u000fAqa \u0004\u0005\u0002\u0004\t\t!\u0001\u0004bGRLwN\u001c\t\u0005\u000b\u0006\r1*C\u0002\u0002\u0006\u0005\u0013\u0001\u0002\u00102z]\u0006lWM\u0010\u0005\t\u0003\u00131A\u00111\u0001\u0002\u0002\u0005AA-[:q_N\fG.A\u0005qe>\u0004\u0018j]*fiR!\u0011qBA\u000b!\r)\u0015\u0011C\u0005\u0004\u0003'\t%a\u0002\"p_2,\u0017M\u001c\u0005\u0007\u0003/9\u0001\u0019\u0001)\u0002\t9\fW.Z\u0001\faJ|\u0007/S:TKR$v\u000e\u0006\u0004\u0002\u0010\u0005u\u0011q\u0004\u0005\u0007\u0003/A\u0001\u0019\u0001)\t\r\u0005\u0005\u0002\u00021\u0001Q\u0003\u00151\u0018\r\\;f\u0003)\u0001(o\u001c9Pe\u0016c7/\u001a\u000b\u0006]\u0006\u001d\u0012\u0011\u0006\u0005\u0007\u0003/I\u0001\u0019\u0001)\t\u0011\u0005-\u0012\u0002\"a\u0001\u0003[\t1!\u00197u!\u0011)\u00151\u0001)\u0002\u0017A\u0014x\u000e](s\u000b6\u0004H/\u001f\u000b\u0004]\u0006M\u0002BBA\f\u0015\u0001\u0007\u0001+\u0001\u0006qe>\u0004xJ\u001d(vY2$2A\\A\u001d\u0011\u0019\t9b\u0003a\u0001!\u0006Q\u0001O]8q\u001fJtuN\\3\u0015\t\u0005}\u0012Q\t\t\u0005\u000b\u0006\u0005c.C\u0002\u0002D\u0005\u0013aa\u00149uS>t\u0007BBA\f\u0019\u0001\u0007\u0001+A\u0006qe>\u0004xJ\u001d$bYN,G\u0003BA\b\u0003\u0017Ba!a\u0006\u000e\u0001\u0004\u0001\u0016aB:fiB\u0013x\u000e\u001d\u000b\u0006]\u0006E\u00131\u000b\u0005\u0007\u0003/q\u0001\u0019\u0001)\t\r\u0005\u0005b\u00021\u0001Q\u0003%\u0019G.Z1s!J|\u0007\u000fF\u0002o\u00033Ba!a\u0006\u0010\u0001\u0004\u0001\u0016!C3om>\u0013X\t\\:f)\u0015q\u0017qLA1\u0011\u0019\t9\u0002\u0005a\u0001!\"A\u00111\u0006\t\u0005\u0002\u0004\ti#A\u0005f]Z|%OT8oKR!\u0011qHA4\u0011\u0019\t9\"\u0005a\u0001!\u0006IQM\u001c<PeN{W.\u001a\u000b\u0007\u0003\u007f\ti'a\u001c\t\r\u0005]!\u00031\u0001Q\u0011!\tYC\u0005CA\u0002\u0005E\u0004#B#\u0002\u0004\u0005M\u0004\u0003B#\u0002BA\u000bqb]2bY\u0006\u0004&o\u001c9Pe\u0016c7/\u001a\u000b\u0006!\u0006e\u00141\u0010\u0005\u0007\u0003/\u0019\u0002\u0019\u0001)\t\u0011\u0005-2\u0003\"a\u0001\u0003[\t\u0001c]2bY\u0006\u0004&o\u001c9Pe\u0016k\u0007\u000f^=\u0015\u0007A\u000b\t\t\u0003\u0004\u0002\u0018Q\u0001\r\u0001U\u0001\u0010g\u000e\fG.\u0019)s_B|%OT8oKR!\u00111OAD\u0011\u0019\t9\"\u0006a\u0001!\u0006q!/\u001a7fCN,g+\u001a:tS>tWCAA:\u0003I!WM^3m_BlWM\u001c;WKJ\u001c\u0018n\u001c8\u0002'Y,'o]5p]:+XNY3s'R\u0014\u0018N\\4\u0002\u001bY,'o]5p]N#(/\u001b8h\u0003=\u0019w\u000e]=sS\u001eDGo\u0015;sS:<\u0017AD:pkJ\u001cW-\u00128d_\u0012LgnZ\u0001\rg>,(oY3SK\u0006$WM]\u0001\u000fK:\u001cw\u000eZ5oON#(/\u001b8h\u00035a\u0017N\\3TKB\f'/\u0019;pe\u0006i!.\u0019<b\u00072\f7o\u001d)bi\"\f\u0001B[1wC\"{W.Z\u0001\u000bU\u00064\u0018MV3oI>\u0014\u0018a\u00036bm\u00064VM]:j_:\f!B[1wCZk\u0017J\u001c4p\u0003)Q\u0017M^1W[:\u000bW.Z\u0001\rU\u00064\u0018MV7WK:$wN]\u0001\u000eU\u00064\u0018MV7WKJ\u001c\u0018n\u001c8\u0002\u001f)\fg/Y*qK\u000e4VM]:j_:\faB[1wCN\u0003Xm\u0019,f]\u0012|'/\u0001\u0007kCZ\f7\u000b]3d\u001d\u0006lW-\u0001\u0004pg:\u000bW.Z\u0001\ng\u000e\fG.\u0019%p[\u0016\fa\u0001^7q\t&\u0014\u0018aB;tKJ$\u0015N]\u0001\tkN,'\u000fS8nK\u0006AQo]3s\u001d\u0006lW-A\u0003jg^Kg.\u0006\u0002\u0002\u0010\u0005)\u0011n]'bG\u00069\u0011n\u001d'j]VD\u0018aB5t\u0003ZL\u0017M\\\u0001\u0015G>dwN]3e\u001fV$\b/\u001e;F]\u0006\u0014G.\u001a3\u0002#\r|gn]8mK&\u001bH+\u001a:nS:\fG.A\u0004kI.Du.\\3\u0002\u0015Y,'o]5p]\u001a{'\u000fF\u0002o\u0003'Da!!68\u0001\u0004\u0001\u0016aB2p[6\fg\u000eZ\u0001\u000bm\u0016\u00148/[8o\u001bN<\u0017\u0001C:dC2\f7)\u001c3\u0002\u0013M\u001c\u0017\r\\1d\u00076$\u0017!D5t\u0015\u00064\u0018-\u0011;MK\u0006\u001cH\u000f\u0006\u0003\u0002\u0010\u0005\u0005\bBBArw\u0001\u0007\u0001+A\u0004wKJ\u001c\u0018n\u001c8\u0015\t\u0005=\u0011q\u001d\u0005\b\u0003Gd\u0004\u0019AAu!\r)\u00151^\u0005\u0004\u0003[\f%aA%oi\u0006!Q.Y5o)\rY\u00151\u001f\u0005\b\u0003kl\u0004\u0019AA|\u0003\u0011\t'oZ:\u0011\t\u0015\u000bI\u0010U\u0005\u0004\u0003w\f%!B!se\u0006L\b"
)
public interface PropertiesTrait {
   static Method reflMethod$Method1(final Class x$1) {
      StructuralCallSite methodCache1 = apply<invokedynamic>();
      Method method1 = methodCache1.find(x$1);
      if (method1 != null) {
         return method1;
      } else {
         Method ensureAccessible_m = x$1.getMethod("isTerminal", methodCache1.parameterTypes());
         Method var10000 = (Method)scala.reflect.package$.MODULE$.ensureAccessible(ensureAccessible_m);
         Object var5 = null;
         method1 = var10000;
         methodCache1.add(x$1, method1);
         return method1;
      }
   }

   void scala$util$PropertiesTrait$_setter_$propFilename_$eq(final String x$1);

   void scala$util$PropertiesTrait$_setter_$releaseVersion_$eq(final Option x$1);

   void scala$util$PropertiesTrait$_setter_$developmentVersion_$eq(final Option x$1);

   void scala$util$PropertiesTrait$_setter_$versionString_$eq(final String x$1);

   void scala$util$PropertiesTrait$_setter_$copyrightString_$eq(final String x$1);

   String propCategory();

   Class pickJarBasedOn();

   String propFilename();

   // $FF: synthetic method
   static java.util.Properties scalaProps$(final PropertiesTrait $this) {
      return $this.scalaProps();
   }

   default java.util.Properties scalaProps() {
      java.util.Properties props = new java.util.Properties();
      InputStream stream = this.pickJarBasedOn().getResourceAsStream(this.propFilename());
      if (stream != null) {
         try {
            props.load(stream);
         } finally {
            try {
               stream.close();
            } catch (IOException var7) {
            }

         }
      }

      return props;
   }

   private void quietlyDispose(final Function0 action, final Function0 disposal) {
      try {
         action.apply$mcV$sp();
      } finally {
         try {
            disposal.apply$mcV$sp();
         } catch (IOException var7) {
         }

      }

   }

   // $FF: synthetic method
   static boolean propIsSet$(final PropertiesTrait $this, final String name) {
      return $this.propIsSet(name);
   }

   default boolean propIsSet(final String name) {
      return System.getProperty(name) != null;
   }

   // $FF: synthetic method
   static boolean propIsSetTo$(final PropertiesTrait $this, final String name, final String value) {
      return $this.propIsSetTo(name, value);
   }

   default boolean propIsSetTo(final String name, final String value) {
      String var10000 = this.propOrNull(name);
      if (var10000 == null) {
         if (value == null) {
            return true;
         }
      } else if (var10000.equals(value)) {
         return true;
      }

      return false;
   }

   // $FF: synthetic method
   static String propOrElse$(final PropertiesTrait $this, final String name, final Function0 alt) {
      return $this.propOrElse(name, alt);
   }

   default String propOrElse(final String name, final Function0 alt) {
      Option var10000 = Option$.MODULE$.apply(System.getProperty(name));
      if (var10000 == null) {
         throw null;
      } else {
         Option getOrElse_this = var10000;
         return (String)(getOrElse_this.isEmpty() ? alt.apply() : getOrElse_this.get());
      }
   }

   // $FF: synthetic method
   static String propOrEmpty$(final PropertiesTrait $this, final String name) {
      return $this.propOrEmpty(name);
   }

   default String propOrEmpty(final String name) {
      return this.propOrElse(name, () -> "");
   }

   // $FF: synthetic method
   static String propOrNull$(final PropertiesTrait $this, final String name) {
      return $this.propOrNull(name);
   }

   default String propOrNull(final String name) {
      return this.propOrElse(name, () -> null);
   }

   // $FF: synthetic method
   static Option propOrNone$(final PropertiesTrait $this, final String name) {
      return $this.propOrNone(name);
   }

   default Option propOrNone(final String name) {
      return Option$.MODULE$.apply(this.propOrNull(name));
   }

   // $FF: synthetic method
   static boolean propOrFalse$(final PropertiesTrait $this, final String name) {
      return $this.propOrFalse(name);
   }

   default boolean propOrFalse(final String name) {
      Option var10000 = this.propOrNone(name);
      if (var10000 == null) {
         throw null;
      } else {
         Option exists_this = var10000;
         return !exists_this.isEmpty() && $anonfun$propOrFalse$1((String)exists_this.get());
      }
   }

   // $FF: synthetic method
   static String setProp$(final PropertiesTrait $this, final String name, final String value) {
      return $this.setProp(name, value);
   }

   default String setProp(final String name, final String value) {
      return System.setProperty(name, value);
   }

   // $FF: synthetic method
   static String clearProp$(final PropertiesTrait $this, final String name) {
      return $this.clearProp(name);
   }

   default String clearProp(final String name) {
      return System.clearProperty(name);
   }

   // $FF: synthetic method
   static String envOrElse$(final PropertiesTrait $this, final String name, final Function0 alt) {
      return $this.envOrElse(name, alt);
   }

   default String envOrElse(final String name, final Function0 alt) {
      Option var10000 = Option$.MODULE$.apply(System.getenv(name));
      if (var10000 == null) {
         throw null;
      } else {
         Option getOrElse_this = var10000;
         return (String)(getOrElse_this.isEmpty() ? alt.apply() : getOrElse_this.get());
      }
   }

   // $FF: synthetic method
   static Option envOrNone$(final PropertiesTrait $this, final String name) {
      return $this.envOrNone(name);
   }

   default Option envOrNone(final String name) {
      return Option$.MODULE$.apply(System.getenv(name));
   }

   // $FF: synthetic method
   static Option envOrSome$(final PropertiesTrait $this, final String name, final Function0 alt) {
      return $this.envOrSome(name, alt);
   }

   default Option envOrSome(final String name, final Function0 alt) {
      Option var10000 = this.envOrNone(name);
      if (var10000 == null) {
         throw null;
      } else {
         Option orElse_this = var10000;
         return orElse_this.isEmpty() ? (Option)alt.apply() : orElse_this;
      }
   }

   // $FF: synthetic method
   static String scalaPropOrElse$(final PropertiesTrait $this, final String name, final Function0 alt) {
      return $this.scalaPropOrElse(name, alt);
   }

   default String scalaPropOrElse(final String name, final Function0 alt) {
      Option var10000 = this.scalaPropOrNone(name);
      if (var10000 == null) {
         throw null;
      } else {
         Option getOrElse_this = var10000;
         return (String)(getOrElse_this.isEmpty() ? alt.apply() : getOrElse_this.get());
      }
   }

   // $FF: synthetic method
   static String scalaPropOrEmpty$(final PropertiesTrait $this, final String name) {
      return $this.scalaPropOrEmpty(name);
   }

   default String scalaPropOrEmpty(final String name) {
      return this.scalaPropOrElse(name, () -> "");
   }

   // $FF: synthetic method
   static Option scalaPropOrNone$(final PropertiesTrait $this, final String name) {
      return $this.scalaPropOrNone(name);
   }

   default Option scalaPropOrNone(final String name) {
      Option var10000 = Option$.MODULE$.apply(this.scalaProps().getProperty(name));
      if (var10000 == null) {
         throw null;
      } else {
         Option orElse_this = var10000;
         return orElse_this.isEmpty() ? $anonfun$scalaPropOrNone$1(this, name) : orElse_this;
      }
   }

   Option releaseVersion();

   Option developmentVersion();

   // $FF: synthetic method
   static String versionNumberString$(final PropertiesTrait $this) {
      return $this.versionNumberString();
   }

   default String versionNumberString() {
      return this.scalaPropOrEmpty("version.number");
   }

   String versionString();

   String copyrightString();

   // $FF: synthetic method
   static String sourceEncoding$(final PropertiesTrait $this) {
      return $this.sourceEncoding();
   }

   default String sourceEncoding() {
      return this.scalaPropOrElse("file.encoding", () -> "UTF-8");
   }

   // $FF: synthetic method
   static String sourceReader$(final PropertiesTrait $this) {
      return $this.sourceReader();
   }

   default String sourceReader() {
      return this.scalaPropOrElse("source.reader", () -> "scala.tools.nsc.io.SourceReader");
   }

   // $FF: synthetic method
   static String encodingString$(final PropertiesTrait $this) {
      return $this.encodingString();
   }

   default String encodingString() {
      return this.propOrElse("file.encoding", () -> "UTF-8");
   }

   // $FF: synthetic method
   static String lineSeparator$(final PropertiesTrait $this) {
      return $this.lineSeparator();
   }

   default String lineSeparator() {
      return System.lineSeparator();
   }

   // $FF: synthetic method
   static String javaClassPath$(final PropertiesTrait $this) {
      return $this.javaClassPath();
   }

   default String javaClassPath() {
      return this.propOrEmpty("java.class.path");
   }

   // $FF: synthetic method
   static String javaHome$(final PropertiesTrait $this) {
      return $this.javaHome();
   }

   default String javaHome() {
      return this.propOrEmpty("java.home");
   }

   // $FF: synthetic method
   static String javaVendor$(final PropertiesTrait $this) {
      return $this.javaVendor();
   }

   default String javaVendor() {
      return this.propOrEmpty("java.vendor");
   }

   // $FF: synthetic method
   static String javaVersion$(final PropertiesTrait $this) {
      return $this.javaVersion();
   }

   default String javaVersion() {
      return this.propOrEmpty("java.version");
   }

   // $FF: synthetic method
   static String javaVmInfo$(final PropertiesTrait $this) {
      return $this.javaVmInfo();
   }

   default String javaVmInfo() {
      return this.propOrEmpty("java.vm.info");
   }

   // $FF: synthetic method
   static String javaVmName$(final PropertiesTrait $this) {
      return $this.javaVmName();
   }

   default String javaVmName() {
      return this.propOrEmpty("java.vm.name");
   }

   // $FF: synthetic method
   static String javaVmVendor$(final PropertiesTrait $this) {
      return $this.javaVmVendor();
   }

   default String javaVmVendor() {
      return this.propOrEmpty("java.vm.vendor");
   }

   // $FF: synthetic method
   static String javaVmVersion$(final PropertiesTrait $this) {
      return $this.javaVmVersion();
   }

   default String javaVmVersion() {
      return this.propOrEmpty("java.vm.version");
   }

   // $FF: synthetic method
   static String javaSpecVersion$(final PropertiesTrait $this) {
      return $this.javaSpecVersion();
   }

   default String javaSpecVersion() {
      return this.propOrEmpty("java.specification.version");
   }

   // $FF: synthetic method
   static String javaSpecVendor$(final PropertiesTrait $this) {
      return $this.javaSpecVendor();
   }

   default String javaSpecVendor() {
      return this.propOrEmpty("java.specification.vendor");
   }

   // $FF: synthetic method
   static String javaSpecName$(final PropertiesTrait $this) {
      return $this.javaSpecName();
   }

   default String javaSpecName() {
      return this.propOrEmpty("java.specification.name");
   }

   // $FF: synthetic method
   static String osName$(final PropertiesTrait $this) {
      return $this.osName();
   }

   default String osName() {
      return this.propOrEmpty("os.name");
   }

   // $FF: synthetic method
   static String scalaHome$(final PropertiesTrait $this) {
      return $this.scalaHome();
   }

   default String scalaHome() {
      return this.propOrEmpty("scala.home");
   }

   // $FF: synthetic method
   static String tmpDir$(final PropertiesTrait $this) {
      return $this.tmpDir();
   }

   default String tmpDir() {
      return this.propOrEmpty("java.io.tmpdir");
   }

   // $FF: synthetic method
   static String userDir$(final PropertiesTrait $this) {
      return $this.userDir();
   }

   default String userDir() {
      return this.propOrEmpty("user.dir");
   }

   // $FF: synthetic method
   static String userHome$(final PropertiesTrait $this) {
      return $this.userHome();
   }

   default String userHome() {
      return this.propOrEmpty("user.home");
   }

   // $FF: synthetic method
   static String userName$(final PropertiesTrait $this) {
      return $this.userName();
   }

   default String userName() {
      return this.propOrEmpty("user.name");
   }

   // $FF: synthetic method
   static boolean isWin$(final PropertiesTrait $this) {
      return $this.isWin();
   }

   default boolean isWin() {
      return this.osName().startsWith("Windows");
   }

   // $FF: synthetic method
   static boolean isMac$(final PropertiesTrait $this) {
      return $this.isMac();
   }

   default boolean isMac() {
      return this.osName().startsWith("Mac OS X");
   }

   // $FF: synthetic method
   static boolean isLinux$(final PropertiesTrait $this) {
      return $this.isLinux();
   }

   default boolean isLinux() {
      return this.osName().startsWith("Linux");
   }

   // $FF: synthetic method
   static boolean isAvian$(final PropertiesTrait $this) {
      return $this.isAvian();
   }

   default boolean isAvian() {
      return this.javaVmName().contains("Avian");
   }

   // $FF: synthetic method
   static boolean coloredOutputEnabled$(final PropertiesTrait $this) {
      return $this.coloredOutputEnabled();
   }

   default boolean coloredOutputEnabled() {
      String var1 = this.propOrElse("scala.color", () -> "auto");
      switch (var1 == null ? 0 : var1.hashCode()) {
         case 3005871:
            if ("auto".equals(var1)) {
               if (!this.isWin() && this.consoleIsTerminal()) {
                  return true;
               }

               return false;
            }
         default:
            return "".equals(var1) || "true".equalsIgnoreCase(var1);
      }
   }

   // $FF: synthetic method
   static boolean consoleIsTerminal$(final PropertiesTrait $this) {
      return $this.consoleIsTerminal();
   }

   default boolean consoleIsTerminal() {
      Console console = System.console();
      return console != null && (!this.isJavaAtLeast("22") || this.isTerminal$1(console));
   }

   // $FF: synthetic method
   static String jdkHome$(final PropertiesTrait $this) {
      return $this.jdkHome();
   }

   default String jdkHome() {
      return this.envOrElse("JDK_HOME", () -> this.envOrElse("JAVA_HOME", () -> this.javaHome()));
   }

   // $FF: synthetic method
   static String versionFor$(final PropertiesTrait $this, final String command) {
      return $this.versionFor(command);
   }

   default String versionFor(final String command) {
      return (new StringBuilder(11)).append("Scala ").append(command).append(" ").append(this.versionString()).append(" -- ").append(this.copyrightString()).toString();
   }

   // $FF: synthetic method
   static String versionMsg$(final PropertiesTrait $this) {
      return $this.versionMsg();
   }

   default String versionMsg() {
      return this.versionFor(this.propCategory());
   }

   // $FF: synthetic method
   static String scalaCmd$(final PropertiesTrait $this) {
      return $this.scalaCmd();
   }

   default String scalaCmd() {
      return this.isWin() ? "scala.bat" : "scala";
   }

   // $FF: synthetic method
   static String scalacCmd$(final PropertiesTrait $this) {
      return $this.scalacCmd();
   }

   default String scalacCmd() {
      return this.isWin() ? "scalac.bat" : "scalac";
   }

   // $FF: synthetic method
   static boolean isJavaAtLeast$(final PropertiesTrait $this, final String version) {
      return $this.isJavaAtLeast(version);
   }

   default boolean isJavaAtLeast(final String version) {
      int var2 = this.compareVersions$1(this.javaSpecVersion(), version, 0);
      switch (var2) {
         case -2:
            throw new NumberFormatException((new StringBuilder(15)).append("Not a version: ").append(version).toString());
         default:
            return var2 >= 0;
      }
   }

   // $FF: synthetic method
   static boolean isJavaAtLeast$(final PropertiesTrait $this, final int version) {
      return $this.isJavaAtLeast(version);
   }

   default boolean isJavaAtLeast(final int version) {
      scala.math.package$ var10001 = scala.math.package$.MODULE$;
      int max_y = 0;
      return this.isJavaAtLeast(Integer.toString(Math.max(version, max_y)));
   }

   // $FF: synthetic method
   static void main$(final PropertiesTrait $this, final String[] args) {
      $this.main(args);
   }

   default void main(final String[] args) {
      (new PrintWriter(Console$.MODULE$.err(), true)).println(this.versionMsg());
   }

   // $FF: synthetic method
   static void $anonfun$scalaProps$1(final java.util.Properties props$1, final InputStream stream$1) {
      props$1.load(stream$1);
   }

   // $FF: synthetic method
   static void $anonfun$scalaProps$2(final InputStream stream$1) {
      stream$1.close();
   }

   // $FF: synthetic method
   static boolean $anonfun$propOrFalse$1(final String x) {
      return (new $colon$colon("yes", new $colon$colon("on", new $colon$colon("true", Nil$.MODULE$)))).contains(x.toLowerCase());
   }

   // $FF: synthetic method
   static Option $anonfun$scalaPropOrNone$1(final PropertiesTrait $this, final String name$1) {
      return $this.propOrNone((new StringBuilder(6)).append("scala.").append(name$1).toString());
   }

   // $FF: synthetic method
   static boolean $anonfun$releaseVersion$1(final String x$1) {
      return x$1.endsWith("-SNAPSHOT");
   }

   // $FF: synthetic method
   static boolean $anonfun$developmentVersion$1(final String x$2) {
      return x$2.endsWith("-SNAPSHOT");
   }

   // $FF: synthetic method
   static Option $anonfun$developmentVersion$2(final PropertiesTrait $this, final String x$3) {
      return $this.scalaPropOrNone("version.number");
   }

   private boolean isTerminal$1(final Console console$1) {
      try {
         Object var10000;
         try {
            var10000 = reflMethod$Method1(console$1.getClass()).invoke(console$1);
         } catch (InvocationTargetException var2) {
            throw var2.getCause();
         }

         return BoxesRunTime.unboxToBoolean((Boolean)var10000);
      } catch (NoSuchMethodException var3) {
         return false;
      }
   }

   private static Tuple2 versionOf$1(final String s, final int depth) {
      int var2 = s.indexOf(46);
      switch (var2) {
         case -1:
            int n = !s.isEmpty() ? Integer.parseInt(s) : (depth == 0 ? -2 : 0);
            return new Tuple2(n, "");
         case 0:
            return new Tuple2(-2, s.substring(1));
         case 1:
            if (depth == 0 && s.charAt(0) == '1') {
               String r0 = s.substring(2);
               Tuple2 var4 = versionOf$1(r0, 1);
               if (var4 == null) {
                  throw new MatchError((Object)null);
               }

               int v = var4._1$mcI$sp();
               String r = (String)var4._2();
               int n = v <= 8 && !r0.isEmpty() ? v : -2;
               return new Tuple2(n, r);
            }
      }

      String r = s.substring(var2 + 1);
      int var10000;
      if (depth < 2 && r.isEmpty()) {
         var10000 = -2;
      } else {
         String toInt$extension_$this = s.substring(0, var2);
         var10000 = Integer.parseInt(toInt$extension_$this);
         Object var12 = null;
      }

      int n = var10000;
      return new Tuple2(n, r);
   }

   private int compareVersions$1(final String s, final String v, final int depth) {
      while(depth < 3) {
         Tuple2 var4 = versionOf$1(s, depth);
         if (var4 != null) {
            int sn = var4._1$mcI$sp();
            String srest = (String)var4._2();
            Tuple2 var7 = versionOf$1(v, depth);
            if (var7 != null) {
               int vn = var7._1$mcI$sp();
               String vrest = (String)var7._2();
               if (vn < 0) {
                  return -2;
               }

               if (sn < vn) {
                  return -1;
               }

               if (sn > vn) {
                  return 1;
               }

               ++depth;
               v = vrest;
               s = srest;
               continue;
            }

            throw new MatchError((Object)null);
         }

         throw new MatchError((Object)null);
      }

      return 0;
   }

   static void $init$(final PropertiesTrait $this) {
      $this.scala$util$PropertiesTrait$_setter_$propFilename_$eq((new StringBuilder(12)).append("/").append($this.propCategory()).append(".properties").toString());
      Option var10001 = $this.scalaPropOrNone("maven.version.number");
      if (var10001 == null) {
         throw null;
      } else {
         Option filterNot_this = var10001;
         var10001 = (Option)(!filterNot_this.isEmpty() && ((String)filterNot_this.get()).endsWith("-SNAPSHOT") ? None$.MODULE$ : filterNot_this);
         Object var4 = null;
         $this.scala$util$PropertiesTrait$_setter_$releaseVersion_$eq(var10001);
         var10001 = $this.scalaPropOrNone("maven.version.number");
         if (var10001 == null) {
            throw null;
         } else {
            Option filter_this = var10001;
            var10001 = (Option)(!filter_this.isEmpty() && !((String)filter_this.get()).endsWith("-SNAPSHOT") ? None$.MODULE$ : filter_this);
            Object var5 = null;
            Option flatMap_this = var10001;
            if (flatMap_this.isEmpty()) {
               var10001 = None$.MODULE$;
            } else {
               String var11 = (String)flatMap_this.get();
               var10001 = $this.scalaPropOrNone("version.number");
            }

            flatMap_this = null;
            $this.scala$util$PropertiesTrait$_setter_$developmentVersion_$eq(var10001);
            $this.scala$util$PropertiesTrait$_setter_$versionString_$eq((new StringBuilder(8)).append("version ").append($this.scalaPropOrElse("version.number", () -> "(unknown)")).toString());
            $this.scala$util$PropertiesTrait$_setter_$copyrightString_$eq($this.scalaPropOrElse("copyright.string", () -> "Copyright 2002-2025, LAMP/EPFL and Lightbend, Inc. dba Akka"));
         }
      }
   }

   // $FF: synthetic method
   static Object $anonfun$propOrFalse$1$adapted(final String x) {
      return BoxesRunTime.boxToBoolean($anonfun$propOrFalse$1(x));
   }

   // $FF: synthetic method
   static Object $anonfun$releaseVersion$1$adapted(final String x$1) {
      return BoxesRunTime.boxToBoolean($anonfun$releaseVersion$1(x$1));
   }

   // $FF: synthetic method
   static Object $anonfun$developmentVersion$1$adapted(final String x$2) {
      return BoxesRunTime.boxToBoolean($anonfun$developmentVersion$1(x$2));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

package org.apache.spark.sql.hive.execution;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.apache.hadoop.hive.ql.plan.TableDesc;
import org.apache.spark.sql.catalyst.util.CaseInsensitiveMap;
import org.apache.spark.sql.internal.SQLConf;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.Predef.ArrowAssoc.;
import scala.collection.MapOps;
import scala.collection.SetOps;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ee\u0001B\u0012%\u0001EB\u0001\u0002\u0012\u0001\u0003\u0006\u0004%I!\u0012\u0005\t-\u0002\u0011\t\u0011)A\u0005\r\")1\f\u0001C\u00019\")1\f\u0001C\u0001A\"9Q\r\u0001b\u0001\n\u00031\u0007BB9\u0001A\u0003%q\rC\u0004s\u0001\t\u0007I\u0011A:\t\rU\u0004\u0001\u0015!\u0003u\u0011\u001d1\bA1A\u0005\u0002MDaa\u001e\u0001!\u0002\u0013!\b\"\u0002=\u0001\t\u0003I\bbB?\u0001\u0005\u0004%\ta\u001d\u0005\u0007}\u0002\u0001\u000b\u0011\u0002;\t\u000f}\u0004!\u0019!C\u0001s\"9\u0011\u0011\u0001\u0001!\u0002\u0013Q\bbBA\u0002\u0001\u0011\u0005\u0011QA\u0004\b\u0003\u000f!\u0003\u0012AA\u0005\r\u0019\u0019C\u0005#\u0001\u0002\f!11L\u0005C\u0001\u0003/A\u0011\"!\u0007\u0013\u0005\u0004%I!a\u0007\t\u0011\u00055\"\u0003)A\u0005\u0003;Aq!a\f\u0013\t\u0013\t\t\u0004C\u0005\u00028I\u0011\r\u0011\"\u0001\u0002:!9\u00111\b\n!\u0002\u0013q\u0005\"CA\u001f%\t\u0007I\u0011AA\u001d\u0011\u001d\tyD\u0005Q\u0001\n9C\u0011\"!\u0011\u0013\u0005\u0004%\t!!\u000f\t\u000f\u0005\r#\u0003)A\u0005\u001d\"I\u0011Q\t\nC\u0002\u0013\u0005\u0011\u0011\b\u0005\b\u0003\u000f\u0012\u0002\u0015!\u0003O\u0011%\tIE\u0005b\u0001\n\u0003\tY\u0005\u0003\u0005\u0002XI\u0001\u000b\u0011BA'\u0011\u001d\tIF\u0005C\u0001\u00037B\u0011\"a$\u0013\u0003\u0003%I!!%\u0003\u0017!Kg/Z(qi&|gn\u001d\u0006\u0003K\u0019\n\u0011\"\u001a=fGV$\u0018n\u001c8\u000b\u0005\u001dB\u0013\u0001\u00025jm\u0016T!!\u000b\u0016\u0002\u0007M\fHN\u0003\u0002,Y\u0005)1\u000f]1sW*\u0011QFL\u0001\u0007CB\f7\r[3\u000b\u0003=\n1a\u001c:h\u0007\u0001\u00192\u0001\u0001\u001a9!\t\u0019d'D\u00015\u0015\u0005)\u0014!B:dC2\f\u0017BA\u001c5\u0005\u0019\te.\u001f*fMB\u0011\u0011(\u0011\b\u0003u}r!a\u000f \u000e\u0003qR!!\u0010\u0019\u0002\rq\u0012xn\u001c;?\u0013\u0005)\u0014B\u0001!5\u0003\u001d\u0001\u0018mY6bO\u0016L!AQ\"\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005\u0001#\u0014A\u00039be\u0006lW\r^3sgV\ta\tE\u0002H\u0019:k\u0011\u0001\u0013\u0006\u0003\u0013*\u000bA!\u001e;jY*\u00111\nK\u0001\tG\u0006$\u0018\r\\=ti&\u0011Q\n\u0013\u0002\u0013\u0007\u0006\u001cX-\u00138tK:\u001c\u0018\u000e^5wK6\u000b\u0007\u000f\u0005\u0002P':\u0011\u0001+\u0015\t\u0003wQJ!A\u0015\u001b\u0002\rA\u0013X\rZ3g\u0013\t!VK\u0001\u0004TiJLgn\u001a\u0006\u0003%R\n1\u0002]1sC6,G/\u001a:tA!\u0012!\u0001\u0017\t\u0003geK!A\u0017\u001b\u0003\u0013Q\u0014\u0018M\\:jK:$\u0018A\u0002\u001fj]&$h\b\u0006\u0002^?B\u0011a\fA\u0007\u0002I!)Ai\u0001a\u0001\rR\u0011Q,\u0019\u0005\u0006\t\u0012\u0001\rA\u0019\t\u0005\u001f\u000ete*\u0003\u0002e+\n\u0019Q*\u00199\u0002\u0015\u0019LG.\u001a$pe6\fG/F\u0001h!\r\u0019\u0004N[\u0005\u0003SR\u0012aa\u00149uS>t\u0007CA6q\u001b\u0005a'BA7o\u0003\u0011a\u0017M\\4\u000b\u0003=\fAA[1wC&\u0011A\u000b\\\u0001\fM&dWMR8s[\u0006$\b%A\u0006j]B,HOR8s[\u0006$X#\u0001;\u0011\u0007MBg*\u0001\u0007j]B,HOR8s[\u0006$\b%\u0001\u0007pkR\u0004X\u000f\u001e$pe6\fG/A\u0007pkR\u0004X\u000f\u001e$pe6\fG\u000fI\u0001\u0015Q\u0006\u001c\u0018J\u001c9vi>+H\u000f];u\r>\u0014X.\u0019;\u0016\u0003i\u0004\"aM>\n\u0005q$$a\u0002\"p_2,\u0017M\\\u0001\u0006g\u0016\u0014H-Z\u0001\u0007g\u0016\u0014H-\u001a\u0011\u0002%\r|g\u000e^1j]N$U\r\\5nSR,'o]\u0001\u0014G>tG/Y5og\u0012+G.[7ji\u0016\u00148\u000fI\u0001\u0010g\u0016\u0014H-\u001a)s_B,'\u000f^5fgV\t!-A\u0006ISZ,w\n\u001d;j_:\u001c\bC\u00010\u0013'\u0011\u0011\"'!\u0004\u0011\t\u0005=\u0011QC\u0007\u0003\u0003#Q1!a\u0005o\u0003\tIw.C\u0002C\u0003#!\"!!\u0003\u0002+1|w/\u001a:DCN,Gm\u00149uS>tg*Y7fgV\u0011\u0011Q\u0004\t\u0006\u0003?\tICT\u0007\u0003\u0003CQA!a\t\u0002&\u00059Q.\u001e;bE2,'bAA\u0014i\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005-\u0012\u0011\u0005\u0002\u0004'\u0016$\u0018A\u00067po\u0016\u00148)Y:fI>\u0003H/[8o\u001d\u0006lWm\u001d\u0011\u0002\u00139,wo\u00149uS>tGc\u0001(\u00024!1\u0011Q\u0007\fA\u00029\u000bAA\\1nK\u0006Ya)\u0013'F?\u001a{%+T!U+\u0005q\u0015\u0001\u0004$J\u0019\u0016{fi\u0014*N\u0003R\u0003\u0013\u0001D%O!V#vLR(S\u001b\u0006#\u0016!D%O!V#vLR(S\u001b\u0006#\u0006%A\u0007P+R\u0003V\u000bV0G\u001fJk\u0015\tV\u0001\u000f\u001fV#\u0006+\u0016+`\r>\u0013V*\u0011+!\u0003\u0015\u0019VI\u0015#F\u0003\u0019\u0019VI\u0015#FA\u0005\u0001B-\u001a7j[&$XM](qi&|gn]\u000b\u0003\u0003\u001b\u0002b!a\u0014\u0002V)TWBAA)\u0015\u0011\t\u0019&!\n\u0002\u0013%lW.\u001e;bE2,\u0017b\u00013\u0002R\u0005\tB-\u001a7j[&$XM](qi&|gn\u001d\u0011\u0002/\u001d,G\u000fS5wK^\u0013\u0018\u000e^3D_6\u0004(/Z:tS>tGCBA/\u0003K\ny\b\u0005\u00034Q\u0006}\u0003#B\u001a\u0002b9s\u0015bAA2i\t1A+\u001e9mKJBq!a\u001a\"\u0001\u0004\tI'A\u0005uC\ndW-\u00138g_B!\u00111NA>\u001b\t\tiG\u0003\u0003\u0002p\u0005E\u0014\u0001\u00029mC:TA!a\u001d\u0002v\u0005\u0011\u0011\u000f\u001c\u0006\u0004O\u0005]$bAA=Y\u00051\u0001.\u00193p_BLA!! \u0002n\tIA+\u00192mK\u0012+7o\u0019\u0005\b\u0003\u0003\u000b\u0003\u0019AAB\u0003\u001d\u0019\u0018\u000f\\\"p]\u001a\u0004B!!\"\u0002\f6\u0011\u0011q\u0011\u0006\u0004\u0003\u0013C\u0013\u0001C5oi\u0016\u0014h.\u00197\n\t\u00055\u0015q\u0011\u0002\b'Fc5i\u001c8g\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\u0019\nE\u0002l\u0003+K1!a&m\u0005\u0019y%M[3di\u0002"
)
public class HiveOptions implements Serializable {
   private final transient CaseInsensitiveMap parameters;
   private final Option fileFormat;
   private final Option inputFormat;
   private final Option outputFormat;
   private final Option serde;
   private final boolean containsDelimiters;

   public static Option getHiveWriteCompression(final TableDesc tableInfo, final SQLConf sqlConf) {
      return HiveOptions$.MODULE$.getHiveWriteCompression(tableInfo, sqlConf);
   }

   public static Map delimiterOptions() {
      return HiveOptions$.MODULE$.delimiterOptions();
   }

   public static String SERDE() {
      return HiveOptions$.MODULE$.SERDE();
   }

   public static String OUTPUT_FORMAT() {
      return HiveOptions$.MODULE$.OUTPUT_FORMAT();
   }

   public static String INPUT_FORMAT() {
      return HiveOptions$.MODULE$.INPUT_FORMAT();
   }

   public static String FILE_FORMAT() {
      return HiveOptions$.MODULE$.FILE_FORMAT();
   }

   private CaseInsensitiveMap parameters() {
      return this.parameters;
   }

   public Option fileFormat() {
      return this.fileFormat;
   }

   public Option inputFormat() {
      return this.inputFormat;
   }

   public Option outputFormat() {
      return this.outputFormat;
   }

   public boolean hasInputOutputFormat() {
      return this.inputFormat().isDefined();
   }

   public Option serde() {
      return this.serde;
   }

   public boolean containsDelimiters() {
      return this.containsDelimiters;
   }

   public Map serdeProperties() {
      return (Map)((MapOps)this.parameters().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$serdeProperties$1(x0$1)))).map((x0$2) -> {
         if (x0$2 != null) {
            String k = (String)x0$2._1();
            String v = (String)x0$2._2();
            return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(HiveOptions$.MODULE$.delimiterOptions().getOrElse(k, () -> k)), v);
         } else {
            throw new MatchError(x0$2);
         }
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$containsDelimiters$1(final HiveOptions $this, final String k) {
      return $this.parameters().contains(k);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$new$1(final String lineDelim) {
      boolean var10000;
      label23: {
         String var1 = "\n";
         if (lineDelim == null) {
            if (var1 != null) {
               break label23;
            }
         } else if (!lineDelim.equals(var1)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$serdeProperties$1(final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         return !HiveOptions$.MODULE$.org$apache$spark$sql$hive$execution$HiveOptions$$lowerCasedOptionNames().contains(k.toLowerCase(Locale.ROOT));
      } else {
         throw new MatchError(x0$1);
      }
   }

   public HiveOptions(final CaseInsensitiveMap parameters) {
      this.parameters = parameters;
      this.fileFormat = parameters.get(HiveOptions$.MODULE$.FILE_FORMAT()).map((x$1) -> x$1.toLowerCase(Locale.ROOT));
      this.inputFormat = parameters.get(HiveOptions$.MODULE$.INPUT_FORMAT());
      this.outputFormat = parameters.get(HiveOptions$.MODULE$.OUTPUT_FORMAT());
      if (this.inputFormat().isDefined() != this.outputFormat().isDefined()) {
         throw new IllegalArgumentException("Cannot specify only inputFormat or outputFormat, you have to specify both of them.");
      } else if (this.fileFormat().isDefined() && this.inputFormat().isDefined()) {
         throw new IllegalArgumentException("Cannot specify fileFormat and inputFormat/outputFormat together for Hive data source.");
      } else {
         this.serde = parameters.get(HiveOptions$.MODULE$.SERDE());
         if (this.fileFormat().isDefined() && this.serde().isDefined() && !((SetOps)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"sequencefile", "textfile", "rcfile"})))).contains(this.fileFormat().get())) {
            throw new IllegalArgumentException("fileFormat '" + this.fileFormat().get() + "' already specifies a serde.");
         } else {
            this.containsDelimiters = HiveOptions$.MODULE$.delimiterOptions().keys().exists((k) -> BoxesRunTime.boxToBoolean($anonfun$containsDelimiters$1(this, k)));
            if (this.containsDelimiters()) {
               if (this.serde().isDefined()) {
                  throw new IllegalArgumentException("Cannot specify delimiters with a custom serde.");
               }

               if (this.fileFormat().isEmpty()) {
                  throw new IllegalArgumentException("Cannot specify delimiters without fileFormat.");
               }

               Object var10000 = this.fileFormat().get();
               String var2 = "textfile";
               if (var10000 == null) {
                  if (var2 != null) {
                     throw new IllegalArgumentException("Cannot specify delimiters as they are only compatible with fileFormat 'textfile', not " + this.fileFormat().get() + ".");
                  }
               } else if (!var10000.equals(var2)) {
                  throw new IllegalArgumentException("Cannot specify delimiters as they are only compatible with fileFormat 'textfile', not " + this.fileFormat().get() + ".");
               }
            }

            parameters.get("lineDelim").withFilter((lineDelim) -> BoxesRunTime.boxToBoolean($anonfun$new$1(lineDelim))).foreach((lineDelim) -> {
               throw new IllegalArgumentException("Hive data source only support newline '\\n' as line delimiter, but given: " + lineDelim + ".");
            });
         }
      }
   }

   public HiveOptions(final Map parameters) {
      this(org.apache.spark.sql.catalyst.util.CaseInsensitiveMap..MODULE$.apply(parameters));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

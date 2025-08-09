package org.apache.spark.deploy.k8s;

import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Stable;
import org.apache.spark.internal.Logging;
import scala.Enumeration;
import scala.Function2;
import scala.Option;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;

@Stable
@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\t]w!B\u001a5\u0011\u0003yd!B!5\u0011\u0003\u0011\u0005\"B(\u0002\t\u0003\u0001\u0006\"B)\u0002\t\u0003\u0011fA\u0002B \u0003\u001d\u0011\t\u0005\u0003\u0006\u0003J\u0011\u0011)\u0019!C\u0001\u0005\u0017B!Ba\u0015\u0005\u0005\u0003\u0005\u000b\u0011\u0002B'\u0011\u0019yE\u0001\"\u0001\u0003V!9!1\f\u0003\u0005\u0002\tu\u0003\"\u0003BB\t\u0005\u0005I\u0011\tBC\u0011%\u00119\tBA\u0001\n\u0003\u0012IiB\u0005\u0003\u000e\u0006\t\t\u0011#\u0003\u0003\u0010\u001aI!qH\u0001\u0002\u0002#%!\u0011\u0013\u0005\u0007\u001f2!\tAa%\t\u000f\tUE\u0002\"\u0002\u0003\u0018\"I!q\u0016\u0007\u0002\u0002\u0013\u0015!\u0011\u0017\u0005\n\u0005kc\u0011\u0011!C\u0003\u0005oC\u0011B!$\u0002\u0003\u0003%YAa0\b\u000ba\f\u0001\u0012A=\u0007\u000bi\f\u0001\u0012A>\t\u000b=\u001bB\u0011A@\t\u0013\u0005\u00051C1A\u0005\u0002\u0005\r\u0001\u0002CAe'\u0001\u0006I!!\u0002\t\u0013\u0005-7C1A\u0005\u0002\u0005\r\u0001\u0002CAg'\u0001\u0006I!!\u0002\u0007\r\u0005%1\u0003SA\u0006\u0011)\tI#\u0007BK\u0002\u0013\u0005\u00111\u0006\u0005\u000b\u0003\u007fI\"\u0011#Q\u0001\n\u00055\u0002BCA!3\tU\r\u0011\"\u0001\u0002,!Q\u00111I\r\u0003\u0012\u0003\u0006I!!\f\t\r=KB\u0011AA#\u0011\u001d\tY%\u0007C\u0001\u0003\u001bBq!a\u0017\u001a\t\u0003\ti\u0006C\u0005\u0002be\t\t\u0011\"\u0001\u0002d!I\u0011\u0011N\r\u0012\u0002\u0013\u0005\u00111\u000e\u0005\n\u0003\u0003K\u0012\u0013!C\u0001\u0003WB\u0011\"a!\u001a\u0003\u0003%\t%!\"\t\u0013\u0005U\u0015$!A\u0005\u0002\u0005]\u0005\"CAM3\u0005\u0005I\u0011AAN\u0011%\t9+GA\u0001\n\u0003\nI\u000bC\u0005\u00028f\t\t\u0011\"\u0001\u0002:\"I\u00111Y\r\u0002\u0002\u0013\u0005\u0013QY\u0004\n\u0003\u001f\u001c\u0012\u0011!E\t\u0003#4\u0011\"!\u0003\u0014\u0003\u0003E\t\"a5\t\r=[C\u0011AAu\u0011%\tYoKA\u0001\n\u000b\ni\u000fC\u0005\u0002p.\n\t\u0011\"!\u0002r\"I\u0011q_\u0016\u0002\u0002\u0013\u0005\u0015\u0011 \u0005\n\u0005\u000fY\u0013\u0011!C\u0005\u0005\u0013AqA!\u0005\u0014\t\u0007\u0011\u0019\u0002C\u0005\u0003\bM\t\t\u0011\"\u0003\u0003\n\u0005a2\u000b]1sW.+(-\u001a:oKR,7o\u00117jK:$h)Y2u_JL(BA\u001b7\u0003\rY\u0007h\u001d\u0006\u0003oa\na\u0001Z3qY>L(BA\u001d;\u0003\u0015\u0019\b/\u0019:l\u0015\tYD(\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002{\u0005\u0019qN]4\u0004\u0001A\u0011\u0001)A\u0007\u0002i\ta2\u000b]1sW.+(-\u001a:oKR,7o\u00117jK:$h)Y2u_JL8cA\u0001D\u0013B\u0011AiR\u0007\u0002\u000b*\ta)A\u0003tG\u0006d\u0017-\u0003\u0002I\u000b\n1\u0011I\\=SK\u001a\u0004\"AS'\u000e\u0003-S!\u0001\u0014\u001d\u0002\u0011%tG/\u001a:oC2L!AT&\u0003\u000f1{wmZ5oO\u00061A(\u001b8jiz\"\u0012aP\u0001\u0017GJ,\u0017\r^3Lk\n,'O\\3uKN\u001cE.[3oiRI1k\u00187rg\n}!1\u0005\t\u0003)vk\u0011!\u0016\u0006\u0003-^\u000baa\u00197jK:$(B\u0001-Z\u0003)YWOY3s]\u0016$Xm\u001d\u0006\u00035n\u000bqAZ1ce&\u001c\u0007HC\u0001]\u0003\tIw.\u0003\u0002_+\n\u00012*\u001e2fe:,G/Z:DY&,g\u000e\u001e\u0005\u0006A\u000e\u0001\r!Y\u0001\u0007[\u0006\u001cH/\u001a:\u0011\u0005\tLgBA2h!\t!W)D\u0001f\u0015\t1g(\u0001\u0004=e>|GOP\u0005\u0003Q\u0016\u000ba\u0001\u0015:fI\u00164\u0017B\u00016l\u0005\u0019\u0019FO]5oO*\u0011\u0001.\u0012\u0005\u0006[\u000e\u0001\rA\\\u0001\n]\u0006lWm\u001d9bG\u0016\u00042\u0001R8b\u0013\t\u0001XI\u0001\u0004PaRLwN\u001c\u0005\u0006e\u000e\u0001\r!Y\u0001\u0019WV\u0014WM\u001d8fi\u0016\u001c\u0018)\u001e;i\u0007>tg\r\u0015:fM&D\b\"\u0002;\u0004\u0001\u0004)\u0018AC2mS\u0016tG\u000fV=qKB\u0019aOa\u0007\u000f\u0005]\u0014R\"A\u0001\u0002\u0015\rc\u0017.\u001a8u)f\u0004X\r\u0005\u0002x'\tQ1\t\\5f]R$\u0016\u0010]3\u0014\u0005Ma\bC\u0001#~\u0013\tqXIA\u0006F]VlWM]1uS>tG#A=\u0002\r\u0011\u0013\u0018N^3s+\t\t)\u0001E\u0002\u0002\bei\u0011a\u0005\u0002\u0004-\u0006d7cB\r\u0002\u000e\u0005E\u0011q\u0003\t\u0005\u0003\u000f\ty!C\u0002\u0002\nu\u00042\u0001RA\n\u0013\r\t)\"\u0012\u0002\b!J|G-^2u!\u0011\tI\"a\t\u000f\t\u0005m\u0011q\u0004\b\u0004I\u0006u\u0011\"\u0001$\n\u0007\u0005\u0005R)A\u0004qC\u000e\\\u0017mZ3\n\t\u0005\u0015\u0012q\u0005\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0004\u0003C)\u0015a\u0005:fcV,7\u000f\u001e+j[\u0016|W\u000f^#oiJLXCAA\u0017!\u0019\ty#!\u000e\u0002:5\u0011\u0011\u0011\u0007\u0006\u0004\u0003gY\u0015AB2p]\u001aLw-\u0003\u0003\u00028\u0005E\"aC\"p]\u001aLw-\u00128uef\u00042\u0001RA\u001e\u0013\r\ti$\u0012\u0002\u0004\u0013:$\u0018\u0001\u0006:fcV,7\u000f\u001e+j[\u0016|W\u000f^#oiJL\b%\u0001\fd_:tWm\u0019;j_:$\u0016.\\3pkR,e\u000e\u001e:z\u0003]\u0019wN\u001c8fGRLwN\u001c+j[\u0016|W\u000f^#oiJL\b\u0005\u0006\u0004\u0002\u0006\u0005\u001d\u0013\u0011\n\u0005\b\u0003Sq\u0002\u0019AA\u0017\u0011\u001d\t\tE\ba\u0001\u0003[\taB]3rk\u0016\u001cH\u000fV5nK>,H\u000f\u0006\u0003\u0002:\u0005=\u0003bBA)?\u0001\u0007\u00111K\u0001\u0005G>tg\r\u0005\u0003\u0002V\u0005]S\"\u0001\u001d\n\u0007\u0005e\u0003HA\u0005Ta\u0006\u00148nQ8oM\u0006\t2m\u001c8oK\u000e$\u0018n\u001c8US6,w.\u001e;\u0015\t\u0005e\u0012q\f\u0005\b\u0003#\u0002\u0003\u0019AA*\u0003\u0011\u0019w\u000e]=\u0015\r\u0005\u0015\u0011QMA4\u0011%\tI#\tI\u0001\u0002\u0004\ti\u0003C\u0005\u0002B\u0005\u0002\n\u00111\u0001\u0002.\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCAA7U\u0011\ti#a\u001c,\u0005\u0005E\u0004\u0003BA:\u0003{j!!!\u001e\u000b\t\u0005]\u0014\u0011P\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a\u001fF\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003\u007f\n)HA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003\u000f\u0003B!!#\u0002\u00146\u0011\u00111\u0012\u0006\u0005\u0003\u001b\u000by)\u0001\u0003mC:<'BAAI\u0003\u0011Q\u0017M^1\n\u0007)\fY)\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002:\u0005q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BAO\u0003G\u00032\u0001RAP\u0013\r\t\t+\u0012\u0002\u0004\u0003:L\b\"CASM\u0005\u0005\t\u0019AA\u001d\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u00111\u0016\t\u0007\u0003[\u000b\u0019,!(\u000e\u0005\u0005=&bAAY\u000b\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005U\u0016q\u0016\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002<\u0006\u0005\u0007c\u0001#\u0002>&\u0019\u0011qX#\u0003\u000f\t{w\u000e\\3b]\"I\u0011Q\u0015\u0015\u0002\u0002\u0003\u0007\u0011QT\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002\b\u0006\u001d\u0007\"CASS\u0005\u0005\t\u0019AA\u001d\u0003\u001d!%/\u001b<fe\u0002\n!bU;c[&\u001c8/[8o\u0003-\u0019VOY7jgNLwN\u001c\u0011\u0002\u0007Y\u000bG\u000eE\u0002\u0002\b-\u001aRaKAk\u0003C\u0004\"\"a6\u0002^\u00065\u0012QFA\u0003\u001b\t\tINC\u0002\u0002\\\u0016\u000bqA];oi&lW-\u0003\u0003\u0002`\u0006e'!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeA!\u00111]At\u001b\t\t)OC\u0002]\u0003\u001fKA!!\n\u0002fR\u0011\u0011\u0011[\u0001\ti>\u001cFO]5oOR\u0011\u0011qQ\u0001\u0006CB\u0004H.\u001f\u000b\u0007\u0003\u000b\t\u00190!>\t\u000f\u0005%b\u00061\u0001\u0002.!9\u0011\u0011\t\u0018A\u0002\u00055\u0012aB;oCB\u0004H.\u001f\u000b\u0005\u0003w\u0014\u0019\u0001\u0005\u0003E_\u0006u\bc\u0002#\u0002\u0000\u00065\u0012QF\u0005\u0004\u0005\u0003)%A\u0002+va2,'\u0007C\u0005\u0003\u0006=\n\t\u00111\u0001\u0002\u0006\u0005\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\t-\u0001\u0003BAE\u0005\u001bIAAa\u0004\u0002\f\n1qJ\u00196fGR\fqaY8om\u0016\u0014H\u000f\u0006\u0003\u0002\u0006\tU\u0001b\u0002B\fc\u0001\u0007!\u0011D\u0001\u0006m\u0006dW/\u001a\t\u0005\u0003\u000f\u0011Y\"C\u0002\u0003\u001eu\u0014QAV1mk\u0016DqA!\t\u0004\u0001\u0004\t\u0019&A\u0005ta\u0006\u00148nQ8oM\"9!QE\u0002A\u0002\t\u001d\u0012a\u00073fM\u0006,H\u000e^*feZL7-Z!dG>,h\u000e^\"b\u0007\u0016\u0014H\u000f\u0005\u0003E_\n%\u0002\u0003BAr\u0005WIAA!\f\u0002f\n!a)\u001b7fQ\u0015\u0019!\u0011\u0007B\u001e!\u0011\u0011\u0019Da\u000e\u000e\u0005\tU\"bAA>q%!!\u0011\bB\u001b\u0005\u0015\u0019\u0016N\\2fC\t\u0011i$A\u00035]Ar\u0003GA\u0010PaRLwN\\\"p]\u001aLw-\u001e:bE2,7i\u001c8gS\u001e\u0014U/\u001b7eKJ\u001c2\u0001\u0002B\"!\r!%QI\u0005\u0004\u0005\u000f*%AB!osZ\u000bG.A\u0007d_:4\u0017n\u001a\"vS2$WM]\u000b\u0003\u0005\u001b\u00022\u0001\u0016B(\u0013\r\u0011\t&\u0016\u0002\u000e\u0007>tg-[4Ck&dG-\u001a:\u0002\u001d\r|gNZ5h\u0005VLG\u000eZ3sAQ!!q\u000bB-!\t9H\u0001C\u0004\u0003J\u001d\u0001\rA!\u0014\u0002\u0015]LG\u000f[(qi&|g.\u0006\u0003\u0003`\tED\u0003\u0002B1\u0005{\"BA!\u0014\u0003d!9!Q\r\u0005A\u0002\t\u001d\u0014\u0001D2p]\u001aLw-\u001e:bi>\u0014\b#\u0003#\u0003j\t5$Q\nB'\u0013\r\u0011Y'\u0012\u0002\n\rVt7\r^5p]J\u0002BAa\u001c\u0003r1\u0001Aa\u0002B:\u0011\t\u0007!Q\u000f\u0002\u0002)F!!qOAO!\r!%\u0011P\u0005\u0004\u0005w*%a\u0002(pi\"Lgn\u001a\u0005\b\u0005\u007fB\u0001\u0019\u0001BA\u0003\u0019y\u0007\u000f^5p]B!Ai\u001cB7\u0003!A\u0017m\u001d5D_\u0012,GCAA\u001d\u0003\u0019)\u0017/^1mgR!\u00111\u0018BF\u0011%\t)KCA\u0001\u0002\u0004\ti*A\u0010PaRLwN\\\"p]\u001aLw-\u001e:bE2,7i\u001c8gS\u001e\u0014U/\u001b7eKJ\u0004\"a\u001e\u0007\u0014\u00051\u0019EC\u0001BH\u0003Q9\u0018\u000e\u001e5PaRLwN\u001c\u0013fqR,gn]5p]V!!\u0011\u0014BS)\u0011\u0011YJa+\u0015\t\tu%q\u0015\u000b\u0005\u0005\u001b\u0012y\nC\u0004\u0003f9\u0001\rA!)\u0011\u0013\u0011\u0013IGa)\u0003N\t5\u0003\u0003\u0002B8\u0005K#qAa\u001d\u000f\u0005\u0004\u0011)\bC\u0004\u0003\u00009\u0001\rA!+\u0011\t\u0011{'1\u0015\u0005\b\u0005[s\u0001\u0019\u0001B,\u0003\u0015!C\u000f[5t\u0003IA\u0017m\u001d5D_\u0012,G%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\t\u0015%1\u0017\u0005\b\u0005[{\u0001\u0019\u0001B,\u0003A)\u0017/^1mg\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0003\u0003:\nuF\u0003BA^\u0005wC\u0011\"!*\u0011\u0003\u0003\u0005\r!!(\t\u000f\t5\u0006\u00031\u0001\u0003XQ!!q\u000bBa\u0011\u001d\u0011I%\u0005a\u0001\u0005\u001bB3!\u0001Bc!\u0011\u0011\u0019Da2\n\t\t%'Q\u0007\u0002\u0007'R\f'\r\\3)\u0007\u0005\u0011i\r\u0005\u0003\u00034\t=\u0017\u0002\u0002Bi\u0005k\u0011A\u0002R3wK2|\u0007/\u001a:Ba&D3\u0001\u0001BcQ\r\u0001!Q\u001a"
)
public final class SparkKubernetesClientFactory {
   public static KubernetesClient createKubernetesClient(final String master, final Option namespace, final String kubernetesAuthConfPrefix, final Enumeration.Value clientType, final SparkConf sparkConf, final Option defaultServiceAccountCaCert) {
      return SparkKubernetesClientFactory$.MODULE$.createKubernetesClient(master, namespace, kubernetesAuthConfPrefix, clientType, sparkConf, defaultServiceAccountCaCert);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return SparkKubernetesClientFactory$.MODULE$.LogStringContext(sc);
   }

   private static final class OptionConfigurableConfigBuilder {
      private final ConfigBuilder configBuilder;

      public ConfigBuilder configBuilder() {
         return this.configBuilder;
      }

      public ConfigBuilder withOption(final Option option, final Function2 configurator) {
         return SparkKubernetesClientFactory.OptionConfigurableConfigBuilder$.MODULE$.withOption$extension(this.configBuilder(), option, configurator);
      }

      public int hashCode() {
         return SparkKubernetesClientFactory.OptionConfigurableConfigBuilder$.MODULE$.hashCode$extension(this.configBuilder());
      }

      public boolean equals(final Object x$1) {
         return SparkKubernetesClientFactory.OptionConfigurableConfigBuilder$.MODULE$.equals$extension(this.configBuilder(), x$1);
      }

      public OptionConfigurableConfigBuilder(final ConfigBuilder configBuilder) {
         this.configBuilder = configBuilder;
      }
   }

   private static class OptionConfigurableConfigBuilder$ {
      public static final OptionConfigurableConfigBuilder$ MODULE$ = new OptionConfigurableConfigBuilder$();

      public final ConfigBuilder withOption$extension(final ConfigBuilder $this, final Option option, final Function2 configurator) {
         return (ConfigBuilder)option.map((opt) -> (ConfigBuilder)configurator.apply(opt, $this)).getOrElse(() -> $this);
      }

      public final int hashCode$extension(final ConfigBuilder $this) {
         return $this.hashCode();
      }

      public final boolean equals$extension(final ConfigBuilder $this, final Object x$1) {
         boolean var10000;
         label41: {
            if (x$1 instanceof OptionConfigurableConfigBuilder) {
               ConfigBuilder var5 = x$1 == null ? null : ((OptionConfigurableConfigBuilder)x$1).configBuilder();
               if ($this == null) {
                  if (var5 == null) {
                     break label41;
                  }
               } else if ($this.equals(var5)) {
                  break label41;
               }
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }

      public OptionConfigurableConfigBuilder$() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class ClientType$ extends Enumeration {
      public static final ClientType$ MODULE$ = new ClientType$();
      private static final SparkKubernetesClientFactory$ClientType$Val Driver;
      private static final SparkKubernetesClientFactory$ClientType$Val Submission;

      static {
         Driver = new SparkKubernetesClientFactory$ClientType$Val(Config$.MODULE$.DRIVER_CLIENT_REQUEST_TIMEOUT(), Config$.MODULE$.DRIVER_CLIENT_CONNECTION_TIMEOUT());
         Submission = new SparkKubernetesClientFactory$ClientType$Val(Config$.MODULE$.SUBMISSION_CLIENT_REQUEST_TIMEOUT(), Config$.MODULE$.SUBMISSION_CLIENT_CONNECTION_TIMEOUT());
      }

      public SparkKubernetesClientFactory$ClientType$Val Driver() {
         return Driver;
      }

      public SparkKubernetesClientFactory$ClientType$Val Submission() {
         return Submission;
      }

      public SparkKubernetesClientFactory$ClientType$Val convert(final Enumeration.Value value) {
         return (SparkKubernetesClientFactory$ClientType$Val)value;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ClientType$.class);
      }
   }
}

package org.apache.spark.scheduler.cluster.k8s;

import java.io.Serializable;
import org.apache.spark.internal.Logging;
import scala.Function5;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple10;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction10;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\t5vAB\"E\u0011\u0003Q\u0005K\u0002\u0004S\t\"\u0005!j\u0015\u0005\u0006A\u0006!\tAY\u0004\u0006G\u0006A\t\t\u001a\u0004\u0006M\u0006A\ti\u001a\u0005\u0006A\u0012!\ta\u001e\u0005\bq\u0012\t\t\u0011\"\u0011z\u0011%\t)\u0001BA\u0001\n\u0003\t9\u0001C\u0005\u0002\u0010\u0011\t\t\u0011\"\u0001\u0002\u0012!I\u0011Q\u0004\u0003\u0002\u0002\u0013\u0005\u0013q\u0004\u0005\n\u0003[!\u0011\u0011!C\u0001\u0003_A\u0011\"!\u000f\u0005\u0003\u0003%\t%a\u000f\t\u0013\u0005uB!!A\u0005B\u0005}\u0002\"CA!\t\u0005\u0005I\u0011BA\"\r\u0019\tY%\u0001!\u0002N!Q\u0011q\n\b\u0003\u0016\u0004%\t!!\u0015\t\u0015\u0005\u0005dB!E!\u0002\u0013\t\u0019\u0006\u0003\u0006\u0002d9\u0011)\u001a!C\u0001\u0003#B!\"!\u001a\u000f\u0005#\u0005\u000b\u0011BA*\u0011)\t9G\u0004BK\u0002\u0013\u0005\u0011\u0011\u000b\u0005\u000b\u0003Sr!\u0011#Q\u0001\n\u0005M\u0003BCA6\u001d\tU\r\u0011\"\u0001\u0002R!Q\u0011Q\u000e\b\u0003\u0012\u0003\u0006I!a\u0015\t\u0015\u0005=dB!f\u0001\n\u0003\t9\u0001\u0003\u0006\u0002r9\u0011\t\u0012)A\u0005\u0003\u0013A!\"a\u001d\u000f\u0005+\u0007I\u0011AA)\u0011)\t)H\u0004B\tB\u0003%\u00111\u000b\u0005\u000b\u0003or!Q3A\u0005\u0002\u0005e\u0004BCAA\u001d\tE\t\u0015!\u0003\u0002|!Q\u00111\u0011\b\u0003\u0016\u0004%\t!!\u001f\t\u0015\u0005\u0015eB!E!\u0002\u0013\tY\b\u0003\u0006\u0002\b:\u0011)\u001a!C\u0001\u0003\u000fA!\"!#\u000f\u0005#\u0005\u000b\u0011BA\u0005\u0011)\tYI\u0004BK\u0002\u0013\u0005\u0011\u0011\u000b\u0005\u000b\u0003\u001bs!\u0011#Q\u0001\n\u0005M\u0003B\u00021\u000f\t\u0003\ty\tC\u0005\u0002(:\t\t\u0011\"\u0001\u0002*\"I\u0011q\u0018\b\u0012\u0002\u0013\u0005\u0011\u0011\u0019\u0005\n\u0003/t\u0011\u0013!C\u0001\u0003\u0003D\u0011\"!7\u000f#\u0003%\t!!1\t\u0013\u0005mg\"%A\u0005\u0002\u0005\u0005\u0007\"CAo\u001dE\u0005I\u0011AAp\u0011%\t\u0019ODI\u0001\n\u0003\t\t\rC\u0005\u0002f:\t\n\u0011\"\u0001\u0002h\"I\u00111\u001e\b\u0012\u0002\u0013\u0005\u0011q\u001d\u0005\n\u0003[t\u0011\u0013!C\u0001\u0003?D\u0011\"a<\u000f#\u0003%\t!!1\t\u000fat\u0011\u0011!C!s\"I\u0011Q\u0001\b\u0002\u0002\u0013\u0005\u0011q\u0001\u0005\n\u0003\u001fq\u0011\u0011!C\u0001\u0003cD\u0011\"!\b\u000f\u0003\u0003%\t%a\b\t\u0013\u00055b\"!A\u0005\u0002\u0005U\b\"CA}\u001d\u0005\u0005I\u0011IA~\u0011%\tIDDA\u0001\n\u0003\nY\u0004C\u0005\u0002>9\t\t\u0011\"\u0011\u0002@!I\u0011q \b\u0002\u0002\u0013\u0005#\u0011A\u0004\n\u0005\u000b\t\u0011\u0011!E\u0001\u0005\u000f1\u0011\"a\u0013\u0002\u0003\u0003E\tA!\u0003\t\r\u0001LD\u0011\u0001B\u0011\u0011%\ti$OA\u0001\n\u000b\ny\u0004C\u0005\u0003$e\n\t\u0011\"!\u0003&!I!1H\u001d\u0002\u0002\u0013\u0005%Q\b\u0005\n\u0003\u0003J\u0014\u0011!C\u0005\u0003\u0007BqAa\u0013\u0002\t\u0003\u0011i\u0005C\u0004\u0003`\u0005!\tA!\u0019\t\u000f\tu\u0015\u0001\"\u0001\u0003 \"9!qU\u0001\u0005\n\t%\u0016!G&vE\u0016\u0014h.\u001a;fg\u0016CXmY;u_J\u0014\u0015mY6f]\u0012T!!\u0012$\u0002\u0007-D4O\u0003\u0002H\u0011\u000691\r\\;ti\u0016\u0014(BA%K\u0003%\u00198\r[3ek2,'O\u0003\u0002L\u0019\u0006)1\u000f]1sW*\u0011QJT\u0001\u0007CB\f7\r[3\u000b\u0003=\u000b1a\u001c:h!\t\t\u0016!D\u0001E\u0005eYUOY3s]\u0016$Xm]#yK\u000e,Ho\u001c:CC\u000e\\WM\u001c3\u0014\u0007\u0005!&\f\u0005\u0002V16\taKC\u0001X\u0003\u0015\u00198-\u00197b\u0013\tIfK\u0001\u0004B]f\u0014VM\u001a\t\u00037zk\u0011\u0001\u0018\u0006\u0003;*\u000b\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003?r\u0013q\u0001T8hO&tw-\u0001\u0004=S:LGOP\u0002\u0001)\u0005\u0001\u0016A\u0005*fO&\u001cH/\u001a:fI\u0016CXmY;u_J\u0004\"!\u001a\u0003\u000e\u0003\u0005\u0011!CU3hSN$XM]3e\u000bb,7-\u001e;peN!A\u0001\u00165l!\t)\u0016.\u0003\u0002k-\n9\u0001K]8ek\u000e$\bC\u00017u\u001d\ti'O\u0004\u0002oc6\tqN\u0003\u0002qC\u00061AH]8pizJ\u0011aV\u0005\u0003gZ\u000bq\u0001]1dW\u0006<W-\u0003\u0002vm\na1+\u001a:jC2L'0\u00192mK*\u00111O\u0016\u000b\u0002I\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012A\u001f\t\u0004w\u0006\u0005Q\"\u0001?\u000b\u0005ut\u0018\u0001\u00027b]\u001eT\u0011a`\u0001\u0005U\u00064\u0018-C\u0002\u0002\u0004q\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLXCAA\u0005!\r)\u00161B\u0005\u0004\u0003\u001b1&aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA\n\u00033\u00012!VA\u000b\u0013\r\t9B\u0016\u0002\u0004\u0003:L\b\"CA\u000e\u0011\u0005\u0005\t\u0019AA\u0005\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011\u0011\u0005\t\u0007\u0003G\tI#a\u0005\u000e\u0005\u0005\u0015\"bAA\u0014-\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005-\u0012Q\u0005\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u00022\u0005]\u0002cA+\u00024%\u0019\u0011Q\u0007,\u0003\u000f\t{w\u000e\\3b]\"I\u00111\u0004\u0006\u0002\u0002\u0003\u0007\u00111C\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011\u0011B\u0001\ti>\u001cFO]5oOR\t!0\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002FA\u001910a\u0012\n\u0007\u0005%CP\u0001\u0004PE*,7\r\u001e\u0002\n\u0003J<W/\\3oiN\u001cBA\u0004+iW\u0006IAM]5wKJ,&\u000f\\\u000b\u0003\u0003'\u0002B!!\u0016\u0002^9!\u0011qKA-!\tqg+C\u0002\u0002\\Y\u000ba\u0001\u0015:fI\u00164\u0017\u0002BA\u0002\u0003?R1!a\u0017W\u0003)!'/\u001b<feV\u0013H\u000eI\u0001\u000bKb,7-\u001e;pe&#\u0017aC3yK\u000e,Ho\u001c:JI\u0002\n1BY5oI\u0006#GM]3tg\u0006a!-\u001b8e\u0003\u0012$'/Z:tA\u0005A\u0001n\\:u]\u0006lW-A\u0005i_N$h.Y7fA\u0005)1m\u001c:fg\u000611m\u001c:fg\u0002\nQ!\u00199q\u0013\u0012\fa!\u00199q\u0013\u0012\u0004\u0013!C<pe.,'/\u0016:m+\t\tY\bE\u0003V\u0003{\n\u0019&C\u0002\u0002\u0000Y\u0013aa\u00149uS>t\u0017AC<pe.,'/\u0016:mA\u0005\u0001\"/Z:pkJ\u001cWm\u001d$jY\u0016|\u0005\u000f^\u0001\u0012e\u0016\u001cx.\u001e:dKN4\u0015\u000e\\3PaR\u0004\u0013!\u0005:fg>,(oY3Qe>4\u0017\u000e\\3JI\u0006\u0011\"/Z:pkJ\u001cW\r\u0015:pM&dW-\u00133!\u0003\u001d\u0001x\u000e\u001a(b[\u0016\f\u0001\u0002]8e\u001d\u0006lW\r\t\u000b\u0017\u0003#\u000b\u0019*!&\u0002\u0018\u0006e\u00151TAO\u0003?\u000b\t+a)\u0002&B\u0011QM\u0004\u0005\b\u0003\u001f\u001a\u0003\u0019AA*\u0011\u001d\t\u0019g\ta\u0001\u0003'Bq!a\u001a$\u0001\u0004\t\u0019\u0006C\u0004\u0002l\r\u0002\r!a\u0015\t\u000f\u0005=4\u00051\u0001\u0002\n!9\u00111O\u0012A\u0002\u0005M\u0003bBA<G\u0001\u0007\u00111\u0010\u0005\b\u0003\u0007\u001b\u0003\u0019AA>\u0011\u001d\t9i\ta\u0001\u0003\u0013Aq!a#$\u0001\u0004\t\u0019&\u0001\u0003d_BLHCFAI\u0003W\u000bi+a,\u00022\u0006M\u0016QWA\\\u0003s\u000bY,!0\t\u0013\u0005=C\u0005%AA\u0002\u0005M\u0003\"CA2IA\u0005\t\u0019AA*\u0011%\t9\u0007\nI\u0001\u0002\u0004\t\u0019\u0006C\u0005\u0002l\u0011\u0002\n\u00111\u0001\u0002T!I\u0011q\u000e\u0013\u0011\u0002\u0003\u0007\u0011\u0011\u0002\u0005\n\u0003g\"\u0003\u0013!a\u0001\u0003'B\u0011\"a\u001e%!\u0003\u0005\r!a\u001f\t\u0013\u0005\rE\u0005%AA\u0002\u0005m\u0004\"CADIA\u0005\t\u0019AA\u0005\u0011%\tY\t\nI\u0001\u0002\u0004\t\u0019&\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005\r'\u0006BA*\u0003\u000b\\#!a2\u0011\t\u0005%\u00171[\u0007\u0003\u0003\u0017TA!!4\u0002P\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003#4\u0016AC1o]>$\u0018\r^5p]&!\u0011Q[Af\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM\nabY8qs\u0012\"WMZ1vYR$C'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001b\u0016\u0005\u0005\u0005(\u0006BA\u0005\u0003\u000b\fabY8qs\u0012\"WMZ1vYR$c'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001c\u0016\u0005\u0005%(\u0006BA>\u0003\u000b\fabY8qs\u0012\"WMZ1vYR$\u0003(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001d\u0002\u001f\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cA\"B!a\u0005\u0002t\"I\u00111D\u0019\u0002\u0002\u0003\u0007\u0011\u0011\u0002\u000b\u0005\u0003c\t9\u0010C\u0005\u0002\u001cM\n\t\u00111\u0001\u0002\u0014\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\rQ\u0018Q \u0005\n\u00037!\u0014\u0011!a\u0001\u0003\u0013\ta!Z9vC2\u001cH\u0003BA\u0019\u0005\u0007A\u0011\"a\u00078\u0003\u0003\u0005\r!a\u0005\u0002\u0013\u0005\u0013x-^7f]R\u001c\bCA3:'\u0015I$1\u0002B\f!i\u0011iAa\u0005\u0002T\u0005M\u00131KA*\u0003\u0013\t\u0019&a\u001f\u0002|\u0005%\u00111KAI\u001b\t\u0011yAC\u0002\u0003\u0012Y\u000bqA];oi&lW-\u0003\u0003\u0003\u0016\t=!AE!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ocA\u0002BA!\u0007\u0003 5\u0011!1\u0004\u0006\u0004\u0005;q\u0018AA5p\u0013\r)(1\u0004\u000b\u0003\u0005\u000f\tQ!\u00199qYf$b#!%\u0003(\t%\"1\u0006B\u0017\u0005_\u0011\tDa\r\u00036\t]\"\u0011\b\u0005\b\u0003\u001fb\u0004\u0019AA*\u0011\u001d\t\u0019\u0007\u0010a\u0001\u0003'Bq!a\u001a=\u0001\u0004\t\u0019\u0006C\u0004\u0002lq\u0002\r!a\u0015\t\u000f\u0005=D\b1\u0001\u0002\n!9\u00111\u000f\u001fA\u0002\u0005M\u0003bBA<y\u0001\u0007\u00111\u0010\u0005\b\u0003\u0007c\u0004\u0019AA>\u0011\u001d\t9\t\u0010a\u0001\u0003\u0013Aq!a#=\u0001\u0004\t\u0019&A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\t}\"q\t\t\u0006+\u0006u$\u0011\t\t\u0018+\n\r\u00131KA*\u0003'\n\u0019&!\u0003\u0002T\u0005m\u00141PA\u0005\u0003'J1A!\u0012W\u0005\u001d!V\u000f\u001d7fcAB\u0011B!\u0013>\u0003\u0003\u0005\r!!%\u0002\u0007a$\u0003'\u0001\u0003nC&tG\u0003\u0002B(\u0005+\u00022!\u0016B)\u0013\r\u0011\u0019F\u0016\u0002\u0005+:LG\u000fC\u0004\u0003X}\u0002\rA!\u0017\u0002\t\u0005\u0014xm\u001d\t\u0006+\nm\u00131K\u0005\u0004\u0005;2&!B!se\u0006L\u0018a\u0001:v]R1!q\nB2\u0005OBqA!\u001aA\u0001\u0004\t\t*A\u0005be\u001e,X.\u001a8ug\"9!\u0011\u000e!A\u0002\t-\u0014a\u00042bG.,g\u000eZ\"sK\u0006$XM\u00128\u0011\u001fU\u0013iG!\u001d\u0002\u0012\nu$QQA*\u0005#K1Aa\u001cW\u0005%1UO\\2uS>tW\u0007\u0005\u0003\u0003t\teTB\u0001B;\u0015\r\u00119HS\u0001\u0004eB\u001c\u0017\u0002\u0002B>\u0005k\u0012aA\u00159d\u000b:4\b\u0003\u0002B@\u0005\u0003k\u0011AS\u0005\u0004\u0005\u0007S%\u0001C*qCJ\\WI\u001c<\u0011\t\t\u001d%QR\u0007\u0003\u0005\u0013S1Aa#K\u0003!\u0011Xm]8ve\u000e,\u0017\u0002\u0002BH\u0005\u0013\u0013qBU3t_V\u00148-\u001a)s_\u001aLG.\u001a\t\u0005\u0005'\u0013I*\u0004\u0002\u0003\u0016*\u0019!q\u0013&\u0002\u0011\u0015DXmY;u_JLAAa'\u0003\u0016\na2i\\1sg\u0016<%/Y5oK\u0012,\u00050Z2vi>\u0014()Y2lK:$\u0017A\u00049beN,\u0017I]4v[\u0016tGo\u001d\u000b\u0007\u0003#\u0013\tKa)\t\u000f\t]\u0013\t1\u0001\u0003Z!9!QU!A\u0002\u0005M\u0013!E2mCN\u001ch*Y7f\r>\u0014XI\u001c;ss\u0006\t\u0002O]5oiV\u001b\u0018mZ3B]\u0012,\u00050\u001b;\u0015\t\t=#1\u0016\u0005\b\u0005K\u0013\u0005\u0019AA*\u0001"
)
public final class KubernetesExecutorBackend {
   public static Arguments parseArguments(final String[] args, final String classNameForEntry) {
      return KubernetesExecutorBackend$.MODULE$.parseArguments(args, classNameForEntry);
   }

   public static void run(final Arguments arguments, final Function5 backendCreateFn) {
      KubernetesExecutorBackend$.MODULE$.run(arguments, backendCreateFn);
   }

   public static void main(final String[] args) {
      KubernetesExecutorBackend$.MODULE$.main(args);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return KubernetesExecutorBackend$.MODULE$.LogStringContext(sc);
   }

   public static class RegisteredExecutor$ implements Product, Serializable {
      public static final RegisteredExecutor$ MODULE$ = new RegisteredExecutor$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "RegisteredExecutor";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof RegisteredExecutor$;
      }

      public int hashCode() {
         return 280073621;
      }

      public String toString() {
         return "RegisteredExecutor";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(RegisteredExecutor$.class);
      }
   }

   public static class Arguments implements Product, Serializable {
      private final String driverUrl;
      private final String executorId;
      private final String bindAddress;
      private final String hostname;
      private final int cores;
      private final String appId;
      private final Option workerUrl;
      private final Option resourcesFileOpt;
      private final int resourceProfileId;
      private final String podName;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String driverUrl() {
         return this.driverUrl;
      }

      public String executorId() {
         return this.executorId;
      }

      public String bindAddress() {
         return this.bindAddress;
      }

      public String hostname() {
         return this.hostname;
      }

      public int cores() {
         return this.cores;
      }

      public String appId() {
         return this.appId;
      }

      public Option workerUrl() {
         return this.workerUrl;
      }

      public Option resourcesFileOpt() {
         return this.resourcesFileOpt;
      }

      public int resourceProfileId() {
         return this.resourceProfileId;
      }

      public String podName() {
         return this.podName;
      }

      public Arguments copy(final String driverUrl, final String executorId, final String bindAddress, final String hostname, final int cores, final String appId, final Option workerUrl, final Option resourcesFileOpt, final int resourceProfileId, final String podName) {
         return new Arguments(driverUrl, executorId, bindAddress, hostname, cores, appId, workerUrl, resourcesFileOpt, resourceProfileId, podName);
      }

      public String copy$default$1() {
         return this.driverUrl();
      }

      public String copy$default$10() {
         return this.podName();
      }

      public String copy$default$2() {
         return this.executorId();
      }

      public String copy$default$3() {
         return this.bindAddress();
      }

      public String copy$default$4() {
         return this.hostname();
      }

      public int copy$default$5() {
         return this.cores();
      }

      public String copy$default$6() {
         return this.appId();
      }

      public Option copy$default$7() {
         return this.workerUrl();
      }

      public Option copy$default$8() {
         return this.resourcesFileOpt();
      }

      public int copy$default$9() {
         return this.resourceProfileId();
      }

      public String productPrefix() {
         return "Arguments";
      }

      public int productArity() {
         return 10;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.driverUrl();
            }
            case 1 -> {
               return this.executorId();
            }
            case 2 -> {
               return this.bindAddress();
            }
            case 3 -> {
               return this.hostname();
            }
            case 4 -> {
               return BoxesRunTime.boxToInteger(this.cores());
            }
            case 5 -> {
               return this.appId();
            }
            case 6 -> {
               return this.workerUrl();
            }
            case 7 -> {
               return this.resourcesFileOpt();
            }
            case 8 -> {
               return BoxesRunTime.boxToInteger(this.resourceProfileId());
            }
            case 9 -> {
               return this.podName();
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
         return x$1 instanceof Arguments;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "driverUrl";
            }
            case 1 -> {
               return "executorId";
            }
            case 2 -> {
               return "bindAddress";
            }
            case 3 -> {
               return "hostname";
            }
            case 4 -> {
               return "cores";
            }
            case 5 -> {
               return "appId";
            }
            case 6 -> {
               return "workerUrl";
            }
            case 7 -> {
               return "resourcesFileOpt";
            }
            case 8 -> {
               return "resourceProfileId";
            }
            case 9 -> {
               return "podName";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.anyHash(this.driverUrl()));
         var1 = Statics.mix(var1, Statics.anyHash(this.executorId()));
         var1 = Statics.mix(var1, Statics.anyHash(this.bindAddress()));
         var1 = Statics.mix(var1, Statics.anyHash(this.hostname()));
         var1 = Statics.mix(var1, this.cores());
         var1 = Statics.mix(var1, Statics.anyHash(this.appId()));
         var1 = Statics.mix(var1, Statics.anyHash(this.workerUrl()));
         var1 = Statics.mix(var1, Statics.anyHash(this.resourcesFileOpt()));
         var1 = Statics.mix(var1, this.resourceProfileId());
         var1 = Statics.mix(var1, Statics.anyHash(this.podName()));
         return Statics.finalizeHash(var1, 10);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var20;
         if (this != x$1) {
            label111: {
               if (x$1 instanceof Arguments) {
                  Arguments var4 = (Arguments)x$1;
                  if (this.cores() == var4.cores() && this.resourceProfileId() == var4.resourceProfileId()) {
                     label104: {
                        String var10000 = this.driverUrl();
                        String var5 = var4.driverUrl();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label104;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label104;
                        }

                        var10000 = this.executorId();
                        String var6 = var4.executorId();
                        if (var10000 == null) {
                           if (var6 != null) {
                              break label104;
                           }
                        } else if (!var10000.equals(var6)) {
                           break label104;
                        }

                        var10000 = this.bindAddress();
                        String var7 = var4.bindAddress();
                        if (var10000 == null) {
                           if (var7 != null) {
                              break label104;
                           }
                        } else if (!var10000.equals(var7)) {
                           break label104;
                        }

                        var10000 = this.hostname();
                        String var8 = var4.hostname();
                        if (var10000 == null) {
                           if (var8 != null) {
                              break label104;
                           }
                        } else if (!var10000.equals(var8)) {
                           break label104;
                        }

                        var10000 = this.appId();
                        String var9 = var4.appId();
                        if (var10000 == null) {
                           if (var9 != null) {
                              break label104;
                           }
                        } else if (!var10000.equals(var9)) {
                           break label104;
                        }

                        Option var17 = this.workerUrl();
                        Option var10 = var4.workerUrl();
                        if (var17 == null) {
                           if (var10 != null) {
                              break label104;
                           }
                        } else if (!var17.equals(var10)) {
                           break label104;
                        }

                        var17 = this.resourcesFileOpt();
                        Option var11 = var4.resourcesFileOpt();
                        if (var17 == null) {
                           if (var11 != null) {
                              break label104;
                           }
                        } else if (!var17.equals(var11)) {
                           break label104;
                        }

                        String var19 = this.podName();
                        String var12 = var4.podName();
                        if (var19 == null) {
                           if (var12 != null) {
                              break label104;
                           }
                        } else if (!var19.equals(var12)) {
                           break label104;
                        }

                        if (var4.canEqual(this)) {
                           break label111;
                        }
                     }
                  }
               }

               var20 = false;
               return var20;
            }
         }

         var20 = true;
         return var20;
      }

      public Arguments(final String driverUrl, final String executorId, final String bindAddress, final String hostname, final int cores, final String appId, final Option workerUrl, final Option resourcesFileOpt, final int resourceProfileId, final String podName) {
         this.driverUrl = driverUrl;
         this.executorId = executorId;
         this.bindAddress = bindAddress;
         this.hostname = hostname;
         this.cores = cores;
         this.appId = appId;
         this.workerUrl = workerUrl;
         this.resourcesFileOpt = resourcesFileOpt;
         this.resourceProfileId = resourceProfileId;
         this.podName = podName;
         Product.$init$(this);
      }
   }

   public static class Arguments$ extends AbstractFunction10 implements Serializable {
      public static final Arguments$ MODULE$ = new Arguments$();

      public final String toString() {
         return "Arguments";
      }

      public Arguments apply(final String driverUrl, final String executorId, final String bindAddress, final String hostname, final int cores, final String appId, final Option workerUrl, final Option resourcesFileOpt, final int resourceProfileId, final String podName) {
         return new Arguments(driverUrl, executorId, bindAddress, hostname, cores, appId, workerUrl, resourcesFileOpt, resourceProfileId, podName);
      }

      public Option unapply(final Arguments x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple10(x$0.driverUrl(), x$0.executorId(), x$0.bindAddress(), x$0.hostname(), BoxesRunTime.boxToInteger(x$0.cores()), x$0.appId(), x$0.workerUrl(), x$0.resourcesFileOpt(), BoxesRunTime.boxToInteger(x$0.resourceProfileId()), x$0.podName())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Arguments$.class);
      }
   }
}

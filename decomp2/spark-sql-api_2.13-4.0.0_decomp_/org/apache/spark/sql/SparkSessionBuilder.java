package org.apache.spark.sql;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.annotation.ClassicOnly;
import org.apache.spark.annotation.Stable;
import scala.Function1;
import scala.Predef.ArrowAssoc.;
import scala.collection.immutable.Map;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005\u0005ufAB\u0014)\u0003\u0003A\u0003\u0007C\u00038\u0001\u0011\u0005\u0011\bC\u0004=\u0001\t\u0007I\u0011C\u001f\t\rE\u0003\u0001\u0015!\u0003?\u0011\u0015\u0011\u0006\u0001\"\u0001T\u0011\u0015q\u0006\u0001\"\u0001`\u0011\u0015\u0011\u0007\u0001\"\u0001d\u0011\u0015)\u0007\u0001\"\u0001g\u0011\u0015I\u0007\u0001\"\u0003k\u0011\u0015y\u0007\u0001\"\u0003q\u0011\u0015\u0019\bA\"\u0005u\u0011\u0015Q\b\u0001\"\u0001|\u0011\u0015Q\b\u0001\"\u0001\u007f\u0011\u0019Q\b\u0001\"\u0001\u0002\n!1!\u0010\u0001C\u0001\u0003+AaA\u001f\u0001\u0005\u0002\u0005m\u0001B\u0002>\u0001\t\u0003\ti\u0003\u0003\u0004{\u0001\u0011\u0005\u0011q\b\u0005\b\u0003\u001b\u0002a\u0011AA(\u0011!\tI\u0007\u0001D\u0001U\u0005-\u0004bBA<\u0001\u0019\u0005\u0011\u0011\u0010\u0005\b\u0003\u0003\u0003a\u0011AA=\u000f!\tY\t\u000bE\u0001Q\u00055eaB\u0014)\u0011\u0003A\u0013q\u0012\u0005\u0007o]!\t!!%\t\u0013\u0005MuC1A\u0005\u0002\u0005U\u0005\u0002CAQ/\u0001\u0006I!a&\t\u0013\u0005\rvC1A\u0005\u0002\u0005U\u0005\u0002CAS/\u0001\u0006I!a&\t\u0013\u0005\u001dvC1A\u0005\u0002\u0005U\u0005\u0002CAU/\u0001\u0006I!a&\t\u0013\u0005-vC1A\u0005\u0002\u0005U\u0005\u0002CAW/\u0001\u0006I!a&\t\u0013\u0005=vC1A\u0005\u0002\u0005E\u0006bBAZ/\u0001\u0006IA\u0012\u0005\n\u0003k;\"\u0019!C\u0001\u0003cCq!a.\u0018A\u0003%a\tC\u0005\u0002:^\u0011\r\u0011\"\u0001\u00022\"9\u00111X\f!\u0002\u00131%aE*qCJ\\7+Z:tS>t')^5mI\u0016\u0014(BA\u0015+\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003W1\nQa\u001d9be.T!!\f\u0018\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0013aA8sON\u0011\u0001!\r\t\u0003eUj\u0011a\r\u0006\u0002i\u0005)1oY1mC&\u0011ag\r\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?\u0007\u0001!\u0012A\u000f\t\u0003w\u0001i\u0011\u0001K\u0001\b_B$\u0018n\u001c8t+\u0005q\u0004\u0003B E\r\u001ak\u0011\u0001\u0011\u0006\u0003\u0003\n\u000bq!\\;uC\ndWM\u0003\u0002Dg\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005\u0015\u0003%a\u0002%bg\"l\u0015\r\u001d\t\u0003\u000f:s!\u0001\u0013'\u0011\u0005%\u001bT\"\u0001&\u000b\u0005-C\u0014A\u0002\u001fs_>$h(\u0003\u0002Ng\u00051\u0001K]3eK\u001aL!a\u0014)\u0003\rM#(/\u001b8h\u0015\ti5'\u0001\u0005paRLwN\\:!\u0003\u001d\t\u0007\u000f\u001d(b[\u0016$\"\u0001V+\u000e\u0003\u0001AQA\u0016\u0003A\u0002\u0019\u000bAA\\1nK\"\u0012A\u0001\u0017\t\u00033rk\u0011A\u0017\u0006\u00037*\n!\"\u00198o_R\fG/[8o\u0013\ti&LA\u0006DY\u0006\u001c8/[2P]2L\u0018AB7bgR,'\u000f\u0006\u0002UA\")a,\u0002a\u0001\r\"\u0012Q\u0001W\u0001\u0012K:\f'\r\\3ISZ,7+\u001e9q_J$H#\u0001+)\u0005\u0019A\u0016A\u0002:f[>$X\r\u0006\u0002UO\")\u0001n\u0002a\u0001\r\u0006\u00012m\u001c8oK\u000e$\u0018n\u001c8TiJLgnZ\u0001\naV$8i\u001c8gS\u001e$2\u0001V6n\u0011\u0015a\u0007\u00021\u0001G\u0003\rYW-\u001f\u0005\u0006]\"\u0001\rAR\u0001\u0006m\u0006dW/Z\u0001\u000eg\u00064W\rU;u\u0007>tg-[4\u0015\u0007Q\u000b(\u000fC\u0003m\u0013\u0001\u0007a\tC\u0003o\u0013\u0001\u0007a)A\niC:$G.\u001a\"vS2$WM]\"p]\u001aLw\rF\u0002vqf\u0004\"A\r<\n\u0005]\u001c$a\u0002\"p_2,\u0017M\u001c\u0005\u0006Y*\u0001\rA\u0012\u0005\u0006]*\u0001\rAR\u0001\u0007G>tg-[4\u0015\u0007QcX\u0010C\u0003m\u0017\u0001\u0007a\tC\u0003o\u0017\u0001\u0007a\t\u0006\u0003U\u007f\u0006\u0005\u0001\"\u00027\r\u0001\u00041\u0005B\u00028\r\u0001\u0004\t\u0019\u0001E\u00023\u0003\u000bI1!a\u00024\u0005\u0011auN\\4\u0015\u000bQ\u000bY!!\u0004\t\u000b1l\u0001\u0019\u0001$\t\r9l\u0001\u0019AA\b!\r\u0011\u0014\u0011C\u0005\u0004\u0003'\u0019$A\u0002#pk\ndW\rF\u0003U\u0003/\tI\u0002C\u0003m\u001d\u0001\u0007a\tC\u0003o\u001d\u0001\u0007Q\u000fF\u0002U\u0003;Aq!a\b\u0010\u0001\u0004\t\t#A\u0002nCB\u0004baRA\u0012\r\u0006\u001d\u0012bAA\u0013!\n\u0019Q*\u00199\u0011\u0007I\nI#C\u0002\u0002,M\u00121!\u00118z)\r!\u0016q\u0006\u0005\b\u0003?\u0001\u0002\u0019AA\u0019!\u001d\t\u0019$!\u0010G\u0003Oi!!!\u000e\u000b\t\u0005]\u0012\u0011H\u0001\u0005kRLGN\u0003\u0002\u0002<\u0005!!.\u0019<b\u0013\u0011\t)#!\u000e\u0015\u0007Q\u000b\t\u0005C\u0004\u0002DE\u0001\r!!\u0012\u0002\t\r|gN\u001a\t\u0005\u0003\u000f\nI%D\u0001+\u0013\r\tYE\u000b\u0002\n'B\f'o[\"p]\u001a\fab^5uQ\u0016CH/\u001a8tS>t7\u000fF\u0002U\u0003#Bq!a\u0015\u0013\u0001\u0004\t)&A\u0001g!\u001d\u0011\u0014qKA.\u0003CJ1!!\u00174\u0005%1UO\\2uS>t\u0017\u0007E\u0002<\u0003;J1!a\u0018)\u0005Y\u0019\u0006/\u0019:l'\u0016\u001c8/[8o\u000bb$XM\\:j_:\u001c\bc\u0001\u001a\u0002d%\u0019\u0011QM\u001a\u0003\tUs\u0017\u000e\u001e\u0015\u0003%a\u000bAb\u001d9be.\u001cuN\u001c;fqR$2\u0001VA7\u0011\u001d\tIg\u0005a\u0001\u0003_\u0002B!a\u0012\u0002r%\u0019\u00111\u000f\u0016\u0003\u0019M\u0003\u0018M]6D_:$X\r\u001f;)\u0005MA\u0016aC4fi>\u00138I]3bi\u0016$\"!a\u001f\u0011\u0007m\ni(C\u0002\u0002\u0000!\u0012Ab\u00159be.\u001cVm]:j_:\faa\u0019:fCR,\u0007f\u0001\u0001\u0002\u0006B\u0019\u0011,a\"\n\u0007\u0005%%L\u0001\u0004Ti\u0006\u0014G.Z\u0001\u0014'B\f'o[*fgNLwN\u001c\"vS2$WM\u001d\t\u0003w]\u0019\"aF\u0019\u0015\u0005\u00055\u0015AC'B'R+%kX&F3V\u0011\u0011q\u0013\t\u0005\u00033\u000by*\u0004\u0002\u0002\u001c*!\u0011QTA\u001d\u0003\u0011a\u0017M\\4\n\u0007=\u000bY*A\u0006N\u0003N#VIU0L\u000bf\u0003\u0013\u0001D!Q!~s\u0015)T#`\u0017\u0016K\u0016!D!Q!~s\u0015)T#`\u0017\u0016K\u0006%\u0001\tD\u0003R\u000bEjT$`\u00136\u0003FjX&F3\u0006\t2)\u0011+B\u0019>;u,S'Q\u0019~[U)\u0017\u0011\u0002%\r{eJT#D)~\u0013V)T(U\u000b~[U)W\u0001\u0014\u0007>se*R\"U?J+Uj\u0014+F?.+\u0015\fI\u0001\r\u0003BKu,T(E\u000b~[U)W\u000b\u0002\r\u0006i\u0011\tU%`\u001b>#UiX&F3\u0002\n\u0001#\u0011)J?6{E)R0D\u0019\u0006\u001b6+S\"\u0002#\u0005\u0003\u0016jX'P\t\u0016{6\tT!T'&\u001b\u0005%\u0001\tB!&{Vj\u0014#F?\u000e{eJT#D)\u0006\t\u0012\tU%`\u001b>#UiX\"P\u001d:+5\t\u0016\u0011"
)
public abstract class SparkSessionBuilder {
   private final HashMap options = new HashMap();

   public static String API_MODE_CONNECT() {
      return SparkSessionBuilder$.MODULE$.API_MODE_CONNECT();
   }

   public static String API_MODE_CLASSIC() {
      return SparkSessionBuilder$.MODULE$.API_MODE_CLASSIC();
   }

   public static String API_MODE_KEY() {
      return SparkSessionBuilder$.MODULE$.API_MODE_KEY();
   }

   public static String CONNECT_REMOTE_KEY() {
      return SparkSessionBuilder$.MODULE$.CONNECT_REMOTE_KEY();
   }

   public static String CATALOG_IMPL_KEY() {
      return SparkSessionBuilder$.MODULE$.CATALOG_IMPL_KEY();
   }

   public static String APP_NAME_KEY() {
      return SparkSessionBuilder$.MODULE$.APP_NAME_KEY();
   }

   public static String MASTER_KEY() {
      return SparkSessionBuilder$.MODULE$.MASTER_KEY();
   }

   public HashMap options() {
      return this.options;
   }

   @ClassicOnly
   public SparkSessionBuilder appName(final String name) {
      return this.config(SparkSessionBuilder$.MODULE$.APP_NAME_KEY(), name);
   }

   @ClassicOnly
   public SparkSessionBuilder master(final String master) {
      return this.config(SparkSessionBuilder$.MODULE$.MASTER_KEY(), master);
   }

   @ClassicOnly
   public SparkSessionBuilder enableHiveSupport() {
      return this.config(SparkSessionBuilder$.MODULE$.CATALOG_IMPL_KEY(), "hive");
   }

   public SparkSessionBuilder remote(final String connectionString) {
      return this.config(SparkSessionBuilder$.MODULE$.CONNECT_REMOTE_KEY(), connectionString);
   }

   private SparkSessionBuilder putConfig(final String key, final String value) {
      if (!this.handleBuilderConfig(key, value)) {
         this.options().$plus$eq(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(key), value));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return this;
   }

   private synchronized SparkSessionBuilder safePutConfig(final String key, final String value) {
      return this.putConfig(key, value);
   }

   public abstract boolean handleBuilderConfig(final String key, final String value);

   public SparkSessionBuilder config(final String key, final String value) {
      return this.safePutConfig(key, value);
   }

   public SparkSessionBuilder config(final String key, final long value) {
      return this.safePutConfig(key, Long.toString(value));
   }

   public SparkSessionBuilder config(final String key, final double value) {
      return this.safePutConfig(key, Double.toString(value));
   }

   public SparkSessionBuilder config(final String key, final boolean value) {
      return this.safePutConfig(key, Boolean.toString(value));
   }

   public synchronized SparkSessionBuilder config(final Map map) {
      map.foreach((kv) -> this.putConfig((String)kv._1(), kv._2().toString()));
      return this;
   }

   public synchronized SparkSessionBuilder config(final java.util.Map map) {
      return this.config(scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(map).asScala().toMap(scala..less.colon.less..MODULE$.refl()));
   }

   public synchronized SparkSessionBuilder config(final SparkConf conf) {
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])conf.getAll()), (kv) -> this.putConfig((String)kv._1(), (String)kv._2()));
      return this;
   }

   @ClassicOnly
   public abstract SparkSessionBuilder withExtensions(final Function1 f);

   @ClassicOnly
   public abstract SparkSessionBuilder sparkContext(final SparkContext sparkContext);

   public abstract SparkSession getOrCreate();

   public abstract SparkSession create();

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}

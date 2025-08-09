package spire.random.rng;

import scala.reflect.ScalaSignature;
import spire.random.IntBasedGenerator;
import spire.util.Pack.;

@ScalaSignature(
   bytes = "\u0006\u000594Aa\u0005\u000b\u00017!A\u0001\u0005\u0001B\u0001B\u0003%\u0011\u0005C\u0003(\u0001\u0011\u0005\u0001\u0006C\u0004-\u0001\u0001\u0007I\u0011B\u0017\t\u000f9\u0002\u0001\u0019!C\u0005_!1Q\u0007\u0001Q!\n\u0005BQA\u000e\u0001\u0005\u0002]BQ\u0001\u000f\u0001\u0005\u0002eBQ\u0001\u0011\u0001\u0005\u0002\u0005CQ\u0001\u0012\u0001\u0005\u0002\u0015;QA\u0012\u000b\t\u0002\u001d3Qa\u0005\u000b\t\u0002!CQaJ\u0006\u0005\u0002=CQ\u0001U\u0006\u0005\u0002\u0015CQ!U\u0006\u0005\u0002ICQ\u0001V\u0006\u0005\u0002UCQaV\u0006\u0005\u0002aCqAX\u0006\u0012\u0002\u0013\u0005q\fC\u0003k\u0017\u0011\u00051NA\u0003MG\u001e\u001c$G\u0003\u0002\u0016-\u0005\u0019!O\\4\u000b\u0005]A\u0012A\u0002:b]\u0012|WNC\u0001\u001a\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u0019\"\u0001\u0001\u000f\u0011\u0005uqR\"\u0001\f\n\u0005}1\"!E%oi\n\u000b7/\u001a3HK:,'/\u0019;pe\u0006)1/Z3eaA\u0011!%J\u0007\u0002G)\tA%A\u0003tG\u0006d\u0017-\u0003\u0002'G\t\u0019\u0011J\u001c;\u0002\rqJg.\u001b;?)\tI3\u0006\u0005\u0002+\u00015\tA\u0003C\u0003!\u0005\u0001\u0007\u0011%\u0001\u0003tK\u0016$W#A\u0011\u0002\u0011M,W\rZ0%KF$\"\u0001M\u001a\u0011\u0005\t\n\u0014B\u0001\u001a$\u0005\u0011)f.\u001b;\t\u000fQ\"\u0011\u0011!a\u0001C\u0005\u0019\u0001\u0010J\u0019\u0002\u000bM,W\r\u001a\u0011\u0002\u0011\r|\u0007/_%oSR,\u0012!K\u0001\rO\u0016$8+Z3e\u0005f$Xm]\u000b\u0002uA\u0019!eO\u001f\n\u0005q\u001a#!B!se\u0006L\bC\u0001\u0012?\u0013\ty4E\u0001\u0003CsR,\u0017\u0001D:fiN+W\r\u001a\"zi\u0016\u001cHC\u0001\u0019C\u0011\u0015\u0019\u0005\u00021\u0001;\u0003\u0015\u0011\u0017\u0010^3t\u0003\u001dqW\r\u001f;J]R$\u0012!I\u0001\u0006\u0019\u000e<7G\r\t\u0003U-\u00192aC%M!\t\u0011#*\u0003\u0002LG\t1\u0011I\\=SK\u001a\u0004B!H'*C%\u0011aJ\u0006\u0002\u0013\u000f\u0016tWM]1u_J\u001cu.\u001c9b]&|g\u000eF\u0001H\u0003)\u0011\u0018M\u001c3p[N+W\rZ\u0001\nMJ|WNQ=uKN$\"!K*\t\u000b\rs\u0001\u0019\u0001\u001e\u0002\u0011\u0019\u0014x.\\*fK\u0012$\"!\u000b,\t\u000b1z\u0001\u0019A\u0011\u0002\u0011\u0019\u0014x.\u001c+j[\u0016$\"!K-\t\u000fi\u0003\u0002\u0013!a\u00017\u0006!A/[7f!\t\u0011C,\u0003\u0002^G\t!Aj\u001c8h\u0003I1'o\\7US6,G\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003\u0001T#aW1,\u0003\t\u0004\"a\u00195\u000e\u0003\u0011T!!\u001a4\u0002\u0013Ut7\r[3dW\u0016$'BA4$\u0003)\tgN\\8uCRLwN\\\u0005\u0003S\u0012\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003\u0011\u0019H/\u001a9\u0015\u0005\u0005b\u0007\"B7\u0013\u0001\u0004\t\u0013!\u00018"
)
public class Lcg32 extends IntBasedGenerator {
   private int seed;

   public static int step(final int n) {
      return Lcg32$.MODULE$.step(n);
   }

   public static long fromTime$default$1() {
      return Lcg32$.MODULE$.fromTime$default$1();
   }

   public static Lcg32 fromTime(final long time) {
      return Lcg32$.MODULE$.fromTime(time);
   }

   public static Lcg32 fromSeed(final int seed) {
      return Lcg32$.MODULE$.fromSeed(seed);
   }

   public static Lcg32 fromBytes(final byte[] bytes) {
      return Lcg32$.MODULE$.fromBytes(bytes);
   }

   public static int randomSeed() {
      return Lcg32$.MODULE$.randomSeed();
   }

   public static Object apply(final Object seed) {
      return Lcg32$.MODULE$.apply(seed);
   }

   public static Object apply() {
      return Lcg32$.MODULE$.apply();
   }

   private int seed() {
      return this.seed;
   }

   private void seed_$eq(final int x$1) {
      this.seed = x$1;
   }

   public Lcg32 copyInit() {
      return new Lcg32(this.seed());
   }

   public byte[] getSeedBytes() {
      return .MODULE$.intToBytes(this.seed());
   }

   public void setSeedBytes(final byte[] bytes) {
      this.seed_$eq(.MODULE$.intFromBytes(bytes));
   }

   public int nextInt() {
      this.seed_$eq(1664525 * this.seed() + 1013904223);
      return this.seed();
   }

   public Lcg32(final int seed0) {
      this.seed = seed0;
   }
}

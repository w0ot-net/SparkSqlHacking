package com.twitter.chill;

import java.io.Serializable;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!<Qa\u0004\t\t\u0002]1Q!\u0007\t\t\u0002iAQ!K\u0001\u0005\u0002)BQaK\u0001\u0005\u00021Bq\u0001Y\u0001\u0002\u0002\u0013%\u0011M\u0002\u0003\u001a!\u0001y\u0003\u0002C\u0019\u0006\u0005\u0003\u0007I\u0011\u0003\u001a\t\u0011y*!\u00111A\u0005\u0012}B\u0001\"R\u0003\u0003\u0002\u0003\u0006Ka\r\u0005\u0006S\u0015!\tA\u0013\u0005\u0006\u001b\u0016!\tB\u0014\u0005\b%\u0016\u0011\r\u0011\"\u0005T\u0011\u0019QV\u0001)A\u0005)\")1,\u0002C\u0001e!)A,\u0002C\u0001e\u0005QQ*Z1u\u0019>\u001c7.\u001a:\u000b\u0005E\u0011\u0012!B2iS2d'BA\n\u0015\u0003\u001d!x/\u001b;uKJT\u0011!F\u0001\u0004G>l7\u0001\u0001\t\u00031\u0005i\u0011\u0001\u0005\u0002\u000b\u001b\u0016\fG\u000fT8dW\u0016\u00148cA\u0001\u001cCA\u0011AdH\u0007\u0002;)\ta$A\u0003tG\u0006d\u0017-\u0003\u0002!;\t1\u0011I\\=SK\u001a\u0004\"AI\u0014\u000e\u0003\rR!\u0001J\u0013\u0002\u0005%|'\"\u0001\u0014\u0002\t)\fg/Y\u0005\u0003Q\r\u0012AbU3sS\u0006d\u0017N_1cY\u0016\fa\u0001P5oSRtD#A\f\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\u00055rFC\u0001\u0018`!\rAR!X\u000b\u0003aU\u001a2!B\u000e\"\u0003\u0005!X#A\u001a\u0011\u0005Q*D\u0002\u0001\u0003\u0006m\u0015\u0011\ra\u000e\u0002\u0002)F\u0011\u0001h\u000f\t\u00039eJ!AO\u000f\u0003\u000f9{G\u000f[5oOB\u0011A\u0004P\u0005\u0003{u\u00111!\u00118z\u0003\u0015!x\fJ3r)\t\u00015\t\u0005\u0002\u001d\u0003&\u0011!)\b\u0002\u0005+:LG\u000fC\u0004E\u000f\u0005\u0005\t\u0019A\u001a\u0002\u0007a$\u0013'\u0001\u0002uA!\u0012\u0001b\u0012\t\u00039!K!!S\u000f\u0003\u0013Q\u0014\u0018M\\:jK:$HCA&M!\rARa\r\u0005\u0006c%\u0001\raM\u0001\u0005a>|G.F\u0001P!\tA\u0002+\u0003\u0002R!\tA1J]=p!>|G.\u0001\u0004u\u0005f$Xm]\u000b\u0002)B\u0019A$V,\n\u0005Yk\"!B!se\u0006L\bC\u0001\u000fY\u0013\tIVD\u0001\u0003CsR,\u0017a\u0002;CsR,7\u000fI\u0001\u0004O\u0016$\u0018\u0001B2paf\u0004\"\u0001\u000e0\u0005\u000bY\u001a!\u0019A\u001c\t\u000bE\u001a\u0001\u0019A/\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0003\t\u0004\"a\u00194\u000e\u0003\u0011T!!Z\u0013\u0002\t1\fgnZ\u0005\u0003O\u0012\u0014aa\u00142kK\u000e$\b"
)
public class MeatLocker implements Serializable {
   private transient Object t;
   private final byte[] tBytes;

   public static MeatLocker apply(final Object t) {
      return MeatLocker$.MODULE$.apply(t);
   }

   public Object t() {
      return this.t;
   }

   public void t_$eq(final Object x$1) {
      this.t = x$1;
   }

   public KryoPool pool() {
      return ScalaKryoInstantiator$.MODULE$.defaultPool();
   }

   public byte[] tBytes() {
      return this.tBytes;
   }

   public Object get() {
      if (this.t() == null) {
         this.t_$eq(this.copy());
      }

      return this.t();
   }

   public Object copy() {
      return this.pool().fromBytes(this.tBytes());
   }

   public MeatLocker(final Object t) {
      this.t = t;
      super();
      this.tBytes = this.pool().toBytesWithClass(this.t());
   }
}

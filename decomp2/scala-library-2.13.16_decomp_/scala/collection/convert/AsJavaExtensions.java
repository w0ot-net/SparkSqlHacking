package scala.collection.convert;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Map;
import scala.collection.mutable.Seq;
import scala.collection.mutable.Set;
import scala.jdk.javaapi.CollectionConverters$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\tmfaB\u001c9!\u0003\r\ta\u0010\u0005\u0006\t\u0002!\t!\u0012\u0004\u0005\u0013\u0002\t!\n\u0003\u0005M\u0005\t\u0005\t\u0015!\u0003N\u0011\u0015a&\u0001\"\u0001^\u0011\u0015\t'\u0001\"\u0001c\u0011\u0015Q'\u0001\"\u0001l\u0011\u001dy\u0007!!A\u0005\u0004A4Aa\u001e\u0001\u0002q\"AA\n\u0003B\u0001B\u0003%!\u0010C\u0003]\u0011\u0011\u0005q\u0010\u0003\u0004b\u0011\u0011\u0005\u0011Q\u0001\u0005\b\u0003#AA\u0011AA\n\u0011%\tY\u0002AA\u0001\n\u0007\tiB\u0002\u0004\u0002,\u0001\t\u0011Q\u0006\u0005\u000b\u0003cq!\u0011!Q\u0001\n\u0005M\u0002B\u0002/\u000f\t\u0003\t\u0019\u0005\u0003\u0004b\u001d\u0011\u0005\u0011\u0011\n\u0005\n\u0003#\u0002\u0011\u0011!C\u0002\u0003'2a!!\u0019\u0001\u0003\u0005\r\u0004BCA4'\t\u0005\t\u0015!\u0003\u0002j!1Al\u0005C\u0001\u0003gBa!Y\n\u0005\u0002\u0005e\u0004\"CA?\u0001\u0005\u0005I1AA@\r\u0019\ti\tA\u0001\u0002\u0010\"Q\u0011q\r\r\u0003\u0002\u0003\u0006I!a%\t\rqCB\u0011AAN\u0011\u0019\t\u0007\u0004\"\u0001\u0002\"\"I\u0011Q\u0015\u0001\u0002\u0002\u0013\r\u0011q\u0015\u0004\u0007\u0003k\u0003\u0011!a.\t\u0015\u0005\u001dTD!A!\u0002\u0013\tY\f\u0003\u0004];\u0011\u0005\u0011Q\u0019\u0005\u0007Cv!\t!a3\t\u0013\u0005E\u0007!!A\u0005\u0004\u0005MgABAq\u0001\u0005\t\u0019\u000f\u0003\u0006\u0002h\t\u0012\t\u0011)A\u0005\u0003ODa\u0001\u0018\u0012\u0005\u0002\u0005=\bBB1#\t\u0003\t)\u0010C\u0005\u0002z\u0002\t\t\u0011b\u0001\u0002|\u001a1!\u0011\u0002\u0001\u0002\u0005\u0017A!Ba\u0004(\u0005\u0003\u0005\u000b\u0011\u0002B\t\u0011\u0019av\u0005\"\u0001\u0003$!1\u0011m\nC\u0001\u0005SAqAa\f(\t\u0003\u0011\t\u0004C\u0005\u0003:\u0001\t\t\u0011b\u0001\u0003<\u00191!Q\n\u0001\u0002\u0005\u001fB!Ba\u0004.\u0005\u0003\u0005\u000b\u0011\u0002B*\u0011\u0019aV\u0006\"\u0001\u0003`!1\u0011-\fC\u0001\u0005KB\u0011B!\u001b\u0001\u0003\u0003%\u0019Aa\u001b\u0007\r\tu\u0004!\u0001B@\u0011)\u0011yA\rB\u0001B\u0003%!1\u0011\u0005\u00079J\"\tA!&\t\r\u0005\u0014D\u0011\u0001BN\u0011%\u00119\u000bAA\u0001\n\u0007\u0011IK\u0001\tBg*\u000bg/Y#yi\u0016t7/[8og*\u0011\u0011HO\u0001\bG>tg/\u001a:u\u0015\tYD(\u0001\u0006d_2dWm\u0019;j_:T\u0011!P\u0001\u0006g\u000e\fG.Y\u0002\u0001'\t\u0001\u0001\t\u0005\u0002B\u00056\tA(\u0003\u0002Dy\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#\u0001$\u0011\u0005\u0005;\u0015B\u0001%=\u0005\u0011)f.\u001b;\u0003#%#XM]1u_JD\u0015m]!t\u0015\u00064\u0018-\u0006\u0002L'N\u0011!\u0001Q\u0001\u0002SB\u0019ajT)\u000e\u0003iJ!\u0001\u0015\u001e\u0003\u0011%#XM]1u_J\u0004\"AU*\r\u0001\u0011)AK\u0001b\u0001+\n\t\u0011)\u0005\u0002W3B\u0011\u0011iV\u0005\u00031r\u0012qAT8uQ&tw\r\u0005\u0002B5&\u00111\f\u0010\u0002\u0004\u0003:L\u0018A\u0002\u001fj]&$h\b\u0006\u0002_AB\u0019qLA)\u000e\u0003\u0001AQ\u0001\u0014\u0003A\u00025\u000ba!Y:KCZ\fW#A2\u0011\u0007\u0011L\u0017+D\u0001f\u0015\t1w-\u0001\u0003vi&d'\"\u00015\u0002\t)\fg/Y\u0005\u0003!\u0016\f\u0011#Y:KCZ\fWI\\;nKJ\fG/[8o+\u0005a\u0007c\u00013n#&\u0011a.\u001a\u0002\f\u000b:,X.\u001a:bi&|g.A\tJi\u0016\u0014\u0018\r^8s\u0011\u0006\u001c\u0018i\u001d&bm\u0006,\"!\u001d;\u0015\u0005I,\bcA0\u0003gB\u0011!\u000b\u001e\u0003\u0006)\u001e\u0011\r!\u0016\u0005\u0006\u0019\u001e\u0001\rA\u001e\t\u0004\u001d>\u001b(!E%uKJ\f'\r\\3ICN\f5OS1wCV\u0011\u0011P`\n\u0003\u0011\u0001\u00032AT>~\u0013\ta(H\u0001\u0005Ji\u0016\u0014\u0018M\u00197f!\t\u0011f\u0010B\u0003U\u0011\t\u0007Q\u000b\u0006\u0003\u0002\u0002\u0005\r\u0001cA0\t{\")AJ\u0003a\u0001uV\u0011\u0011q\u0001\t\u0006\u0003\u0013\ty!`\u0007\u0003\u0003\u0017Q1!!\u0004h\u0003\u0011a\u0017M\\4\n\u0007q\fY!\u0001\tbg*\u000bg/Y\"pY2,7\r^5p]V\u0011\u0011Q\u0003\t\u0005I\u0006]Q0C\u0002\u0002\u001a\u0015\u0014!bQ8mY\u0016\u001cG/[8o\u0003EIE/\u001a:bE2,\u0007*Y:Bg*\u000bg/Y\u000b\u0005\u0003?\t)\u0003\u0006\u0003\u0002\"\u0005\u001d\u0002\u0003B0\t\u0003G\u00012AUA\u0013\t\u0015!VB1\u0001V\u0011\u0019aU\u00021\u0001\u0002*A!aj_A\u0012\u0005=\u0011UO\u001a4fe\"\u000b7/Q:KCZ\fW\u0003BA\u0018\u0003\u0003\u001a\"A\u0004!\u0002\u0003\t\u0004b!!\u000e\u0002<\u0005}RBAA\u001c\u0015\r\tIDO\u0001\b[V$\u0018M\u00197f\u0013\u0011\ti$a\u000e\u0003\r\t+hMZ3s!\r\u0011\u0016\u0011\t\u0003\u0006):\u0011\r!\u0016\u000b\u0005\u0003\u000b\n9\u0005\u0005\u0003`\u001d\u0005}\u0002bBA\u0019!\u0001\u0007\u00111G\u000b\u0003\u0003\u0017\u0002R\u0001ZA'\u0003\u007fI1!a\u0014f\u0005\u0011a\u0015n\u001d;\u0002\u001f\t+hMZ3s\u0011\u0006\u001c\u0018i\u001d&bm\u0006,B!!\u0016\u0002\\Q!\u0011qKA/!\u0011yf\"!\u0017\u0011\u0007I\u000bY\u0006B\u0003U%\t\u0007Q\u000bC\u0004\u00022I\u0001\r!a\u0018\u0011\r\u0005U\u00121HA-\u0005MiU\u000f^1cY\u0016\u001cV-\u001d%bg\u0006\u001b(*\u0019<b+\u0011\t)'!\u001d\u0014\u0005M\u0001\u0015!A:\u0011\r\u0005U\u00121NA8\u0013\u0011\ti'a\u000e\u0003\u0007M+\u0017\u000fE\u0002S\u0003c\"Q\u0001V\nC\u0002U#B!!\u001e\u0002xA!qlEA8\u0011\u001d\t9'\u0006a\u0001\u0003S*\"!a\u001f\u0011\u000b\u0011\fi%a\u001c\u0002'5+H/\u00192mKN+\u0017\u000fS1t\u0003NT\u0015M^1\u0016\t\u0005\u0005\u0015q\u0011\u000b\u0005\u0003\u0007\u000bI\t\u0005\u0003`'\u0005\u0015\u0005c\u0001*\u0002\b\u0012)Ak\u0006b\u0001+\"9\u0011qM\fA\u0002\u0005-\u0005CBA\u001b\u0003W\n)I\u0001\u0007TKFD\u0015m]!t\u0015\u00064\u0018-\u0006\u0003\u0002\u0012\u0006e5C\u0001\rA!\u0015q\u0015QSAL\u0013\r\tiG\u000f\t\u0004%\u0006eE!\u0002+\u0019\u0005\u0004)F\u0003BAO\u0003?\u0003Ba\u0018\r\u0002\u0018\"9\u0011q\r\u000eA\u0002\u0005MUCAAR!\u0015!\u0017QJAL\u00031\u0019V-\u001d%bg\u0006\u001b(*\u0019<b+\u0011\tI+a,\u0015\t\u0005-\u0016\u0011\u0017\t\u0005?b\ti\u000bE\u0002S\u0003_#Q\u0001\u0016\u000fC\u0002UCq!a\u001a\u001d\u0001\u0004\t\u0019\fE\u0003O\u0003+\u000biKA\nNkR\f'\r\\3TKRD\u0015m]!t\u0015\u00064\u0018-\u0006\u0003\u0002:\u0006\r7CA\u000fA!\u0019\t)$!0\u0002B&!\u0011qXA\u001c\u0005\r\u0019V\r\u001e\t\u0004%\u0006\rG!\u0002+\u001e\u0005\u0004)F\u0003BAd\u0003\u0013\u0004BaX\u000f\u0002B\"9\u0011qM\u0010A\u0002\u0005mVCAAg!\u0015!\u0017qZAa\u0013\r\ty,Z\u0001\u0014\u001bV$\u0018M\u00197f'\u0016$\b*Y:Bg*\u000bg/Y\u000b\u0005\u0003+\fY\u000e\u0006\u0003\u0002X\u0006u\u0007\u0003B0\u001e\u00033\u00042AUAn\t\u0015!\u0016E1\u0001V\u0011\u001d\t9'\ta\u0001\u0003?\u0004b!!\u000e\u0002>\u0006e'\u0001D*fi\"\u000b7/Q:KCZ\fW\u0003BAs\u0003[\u001c\"A\t!\u0011\u000b9\u000bI/a;\n\u0007\u0005}&\bE\u0002S\u0003[$Q\u0001\u0016\u0012C\u0002U#B!!=\u0002tB!qLIAv\u0011\u001d\t9\u0007\na\u0001\u0003O,\"!a>\u0011\u000b\u0011\fy-a;\u0002\u0019M+G\u000fS1t\u0003NT\u0015M^1\u0016\t\u0005u(1\u0001\u000b\u0005\u0003\u007f\u0014)\u0001\u0005\u0003`E\t\u0005\u0001c\u0001*\u0003\u0004\u0011)AK\nb\u0001+\"9\u0011q\r\u0014A\u0002\t\u001d\u0001#\u0002(\u0002j\n\u0005!aE'vi\u0006\u0014G.Z'ba\"\u000b7/Q:KCZ\fWC\u0002B\u0007\u00053\u0011yb\u0005\u0002(\u0001\u0006\tQ\u000e\u0005\u0005\u00026\tM!q\u0003B\u000f\u0013\u0011\u0011)\"a\u000e\u0003\u00075\u000b\u0007\u000fE\u0002S\u00053!aAa\u0007(\u0005\u0004)&!A&\u0011\u0007I\u0013y\u0002\u0002\u0004\u0003\"\u001d\u0012\r!\u0016\u0002\u0002-R!!Q\u0005B\u0014!\u0019yvEa\u0006\u0003\u001e!9!qB\u0015A\u0002\tEQC\u0001B\u0016!\u001d!'Q\u0006B\f\u0005;I1A!\u0006f\u0003A\t7OS1wC\u0012K7\r^5p]\u0006\u0014\u00180\u0006\u0002\u00034A9AM!\u000e\u0003\u0018\tu\u0011b\u0001B\u001cK\nQA)[2uS>t\u0017M]=\u0002'5+H/\u00192mK6\u000b\u0007\u000fS1t\u0003NT\u0015M^1\u0016\r\tu\"1\tB$)\u0011\u0011yD!\u0013\u0011\r};#\u0011\tB#!\r\u0011&1\t\u0003\u0007\u00057a#\u0019A+\u0011\u0007I\u00139\u0005\u0002\u0004\u0003\"1\u0012\r!\u0016\u0005\b\u0005\u001fa\u0003\u0019\u0001B&!!\t)Da\u0005\u0003B\t\u0015#\u0001D'ba\"\u000b7/Q:KCZ\fWC\u0002B)\u00053\u0012if\u0005\u0002.\u0001B9aJ!\u0016\u0003X\tm\u0013b\u0001B\u000buA\u0019!K!\u0017\u0005\r\tmQF1\u0001V!\r\u0011&Q\f\u0003\u0007\u0005Ci#\u0019A+\u0015\t\t\u0005$1\r\t\u0007?6\u00129Fa\u0017\t\u000f\t=q\u00061\u0001\u0003TU\u0011!q\r\t\bI\n5\"q\u000bB.\u00031i\u0015\r\u001d%bg\u0006\u001b(*\u0019<b+\u0019\u0011iGa\u001d\u0003xQ!!q\u000eB=!\u0019yVF!\u001d\u0003vA\u0019!Ka\u001d\u0005\r\tm\u0011G1\u0001V!\r\u0011&q\u000f\u0003\u0007\u0005C\t$\u0019A+\t\u000f\t=\u0011\u00071\u0001\u0003|A9aJ!\u0016\u0003r\tU$AF\"p]\u000e,(O]3oi6\u000b\u0007\u000fS1t\u0003NT\u0015M^1\u0016\r\t\u0005%q\u0012BJ'\t\u0011\u0004\t\u0005\u0005\u0003\u0006\n-%Q\u0012BI\u001b\t\u00119IC\u0002\u0003\nj\n!bY8oGV\u0014(/\u001a8u\u0013\u0011\u0011)Ba\"\u0011\u0007I\u0013y\t\u0002\u0004\u0003\u001cI\u0012\r!\u0016\t\u0004%\nMEA\u0002B\u0011e\t\u0007Q\u000b\u0006\u0003\u0003\u0018\ne\u0005CB03\u0005\u001b\u0013\t\nC\u0004\u0003\u0010Q\u0002\rAa!\u0016\u0005\tu\u0005\u0003\u0003BP\u0005G\u0013iI!%\u000e\u0005\t\u0005&b\u0001BEK&!!Q\u0015BQ\u00055\u0019uN\\2veJ,g\u000e^'ba\u000612i\u001c8dkJ\u0014XM\u001c;NCBD\u0015m]!t\u0015\u00064\u0018-\u0006\u0004\u0003,\nE&Q\u0017\u000b\u0005\u0005[\u00139\f\u0005\u0004`e\t=&1\u0017\t\u0004%\nEFA\u0002B\u000em\t\u0007Q\u000bE\u0002S\u0005k#aA!\t7\u0005\u0004)\u0006b\u0002B\bm\u0001\u0007!\u0011\u0018\t\t\u0005\u000b\u0013YIa,\u00034\u0002"
)
public interface AsJavaExtensions {
   // $FF: synthetic method
   static IteratorHasAsJava IteratorHasAsJava$(final AsJavaExtensions $this, final Iterator i) {
      return $this.IteratorHasAsJava(i);
   }

   default IteratorHasAsJava IteratorHasAsJava(final Iterator i) {
      return new IteratorHasAsJava(i);
   }

   // $FF: synthetic method
   static IterableHasAsJava IterableHasAsJava$(final AsJavaExtensions $this, final Iterable i) {
      return $this.IterableHasAsJava(i);
   }

   default IterableHasAsJava IterableHasAsJava(final Iterable i) {
      return new IterableHasAsJava(i);
   }

   // $FF: synthetic method
   static BufferHasAsJava BufferHasAsJava$(final AsJavaExtensions $this, final Buffer b) {
      return $this.BufferHasAsJava(b);
   }

   default BufferHasAsJava BufferHasAsJava(final Buffer b) {
      return new BufferHasAsJava(b);
   }

   // $FF: synthetic method
   static MutableSeqHasAsJava MutableSeqHasAsJava$(final AsJavaExtensions $this, final Seq s) {
      return $this.MutableSeqHasAsJava(s);
   }

   default MutableSeqHasAsJava MutableSeqHasAsJava(final Seq s) {
      return new MutableSeqHasAsJava(s);
   }

   // $FF: synthetic method
   static SeqHasAsJava SeqHasAsJava$(final AsJavaExtensions $this, final scala.collection.Seq s) {
      return $this.SeqHasAsJava(s);
   }

   default SeqHasAsJava SeqHasAsJava(final scala.collection.Seq s) {
      return new SeqHasAsJava(s);
   }

   // $FF: synthetic method
   static MutableSetHasAsJava MutableSetHasAsJava$(final AsJavaExtensions $this, final Set s) {
      return $this.MutableSetHasAsJava(s);
   }

   default MutableSetHasAsJava MutableSetHasAsJava(final Set s) {
      return new MutableSetHasAsJava(s);
   }

   // $FF: synthetic method
   static SetHasAsJava SetHasAsJava$(final AsJavaExtensions $this, final scala.collection.Set s) {
      return $this.SetHasAsJava(s);
   }

   default SetHasAsJava SetHasAsJava(final scala.collection.Set s) {
      return new SetHasAsJava(s);
   }

   // $FF: synthetic method
   static MutableMapHasAsJava MutableMapHasAsJava$(final AsJavaExtensions $this, final Map m) {
      return $this.MutableMapHasAsJava(m);
   }

   default MutableMapHasAsJava MutableMapHasAsJava(final Map m) {
      return new MutableMapHasAsJava(m);
   }

   // $FF: synthetic method
   static MapHasAsJava MapHasAsJava$(final AsJavaExtensions $this, final scala.collection.Map m) {
      return $this.MapHasAsJava(m);
   }

   default MapHasAsJava MapHasAsJava(final scala.collection.Map m) {
      return new MapHasAsJava(m);
   }

   // $FF: synthetic method
   static ConcurrentMapHasAsJava ConcurrentMapHasAsJava$(final AsJavaExtensions $this, final scala.collection.concurrent.Map m) {
      return $this.ConcurrentMapHasAsJava(m);
   }

   default ConcurrentMapHasAsJava ConcurrentMapHasAsJava(final scala.collection.concurrent.Map m) {
      return new ConcurrentMapHasAsJava(m);
   }

   static void $init$(final AsJavaExtensions $this) {
   }

   public class IteratorHasAsJava {
      private final Iterator i;
      // $FF: synthetic field
      public final AsJavaExtensions $outer;

      public java.util.Iterator asJava() {
         return AsJavaConverters.asJava$(CollectionConverters$.MODULE$, (Iterator)this.i);
      }

      public Enumeration asJavaEnumeration() {
         return AsJavaConverters.asJavaEnumeration$(CollectionConverters$.MODULE$, this.i);
      }

      // $FF: synthetic method
      public AsJavaExtensions scala$collection$convert$AsJavaExtensions$IteratorHasAsJava$$$outer() {
         return this.$outer;
      }

      public IteratorHasAsJava(final Iterator i) {
         this.i = i;
         if (AsJavaExtensions.this == null) {
            throw null;
         } else {
            this.$outer = AsJavaExtensions.this;
            super();
         }
      }
   }

   public class IterableHasAsJava {
      private final Iterable i;
      // $FF: synthetic field
      public final AsJavaExtensions $outer;

      public java.lang.Iterable asJava() {
         return AsJavaConverters.asJava$(CollectionConverters$.MODULE$, (Iterable)this.i);
      }

      public Collection asJavaCollection() {
         return AsJavaConverters.asJavaCollection$(CollectionConverters$.MODULE$, this.i);
      }

      // $FF: synthetic method
      public AsJavaExtensions scala$collection$convert$AsJavaExtensions$IterableHasAsJava$$$outer() {
         return this.$outer;
      }

      public IterableHasAsJava(final Iterable i) {
         this.i = i;
         if (AsJavaExtensions.this == null) {
            throw null;
         } else {
            this.$outer = AsJavaExtensions.this;
            super();
         }
      }
   }

   public class BufferHasAsJava {
      private final Buffer b;
      // $FF: synthetic field
      public final AsJavaExtensions $outer;

      public List asJava() {
         return AsJavaConverters.asJava$(CollectionConverters$.MODULE$, (Buffer)this.b);
      }

      // $FF: synthetic method
      public AsJavaExtensions scala$collection$convert$AsJavaExtensions$BufferHasAsJava$$$outer() {
         return this.$outer;
      }

      public BufferHasAsJava(final Buffer b) {
         this.b = b;
         if (AsJavaExtensions.this == null) {
            throw null;
         } else {
            this.$outer = AsJavaExtensions.this;
            super();
         }
      }
   }

   public class MutableSeqHasAsJava {
      private final Seq s;
      // $FF: synthetic field
      public final AsJavaExtensions $outer;

      public List asJava() {
         return AsJavaConverters.asJava$(CollectionConverters$.MODULE$, (Seq)this.s);
      }

      // $FF: synthetic method
      public AsJavaExtensions scala$collection$convert$AsJavaExtensions$MutableSeqHasAsJava$$$outer() {
         return this.$outer;
      }

      public MutableSeqHasAsJava(final Seq s) {
         this.s = s;
         if (AsJavaExtensions.this == null) {
            throw null;
         } else {
            this.$outer = AsJavaExtensions.this;
            super();
         }
      }
   }

   public class SeqHasAsJava {
      private final scala.collection.Seq s;
      // $FF: synthetic field
      public final AsJavaExtensions $outer;

      public List asJava() {
         return AsJavaConverters.asJava$(CollectionConverters$.MODULE$, (scala.collection.Seq)this.s);
      }

      // $FF: synthetic method
      public AsJavaExtensions scala$collection$convert$AsJavaExtensions$SeqHasAsJava$$$outer() {
         return this.$outer;
      }

      public SeqHasAsJava(final scala.collection.Seq s) {
         this.s = s;
         if (AsJavaExtensions.this == null) {
            throw null;
         } else {
            this.$outer = AsJavaExtensions.this;
            super();
         }
      }
   }

   public class MutableSetHasAsJava {
      private final Set s;
      // $FF: synthetic field
      public final AsJavaExtensions $outer;

      public java.util.Set asJava() {
         return AsJavaConverters.asJava$(CollectionConverters$.MODULE$, (Set)this.s);
      }

      // $FF: synthetic method
      public AsJavaExtensions scala$collection$convert$AsJavaExtensions$MutableSetHasAsJava$$$outer() {
         return this.$outer;
      }

      public MutableSetHasAsJava(final Set s) {
         this.s = s;
         if (AsJavaExtensions.this == null) {
            throw null;
         } else {
            this.$outer = AsJavaExtensions.this;
            super();
         }
      }
   }

   public class SetHasAsJava {
      private final scala.collection.Set s;
      // $FF: synthetic field
      public final AsJavaExtensions $outer;

      public java.util.Set asJava() {
         return AsJavaConverters.asJava$(CollectionConverters$.MODULE$, (scala.collection.Set)this.s);
      }

      // $FF: synthetic method
      public AsJavaExtensions scala$collection$convert$AsJavaExtensions$SetHasAsJava$$$outer() {
         return this.$outer;
      }

      public SetHasAsJava(final scala.collection.Set s) {
         this.s = s;
         if (AsJavaExtensions.this == null) {
            throw null;
         } else {
            this.$outer = AsJavaExtensions.this;
            super();
         }
      }
   }

   public class MutableMapHasAsJava {
      private final Map m;
      // $FF: synthetic field
      public final AsJavaExtensions $outer;

      public java.util.Map asJava() {
         return AsJavaConverters.asJava$(CollectionConverters$.MODULE$, (Map)this.m);
      }

      public Dictionary asJavaDictionary() {
         return AsJavaConverters.asJavaDictionary$(CollectionConverters$.MODULE$, this.m);
      }

      // $FF: synthetic method
      public AsJavaExtensions scala$collection$convert$AsJavaExtensions$MutableMapHasAsJava$$$outer() {
         return this.$outer;
      }

      public MutableMapHasAsJava(final Map m) {
         this.m = m;
         if (AsJavaExtensions.this == null) {
            throw null;
         } else {
            this.$outer = AsJavaExtensions.this;
            super();
         }
      }
   }

   public class MapHasAsJava {
      private final scala.collection.Map m;
      // $FF: synthetic field
      public final AsJavaExtensions $outer;

      public java.util.Map asJava() {
         return AsJavaConverters.asJava$(CollectionConverters$.MODULE$, (scala.collection.Map)this.m);
      }

      // $FF: synthetic method
      public AsJavaExtensions scala$collection$convert$AsJavaExtensions$MapHasAsJava$$$outer() {
         return this.$outer;
      }

      public MapHasAsJava(final scala.collection.Map m) {
         this.m = m;
         if (AsJavaExtensions.this == null) {
            throw null;
         } else {
            this.$outer = AsJavaExtensions.this;
            super();
         }
      }
   }

   public class ConcurrentMapHasAsJava {
      private final scala.collection.concurrent.Map m;
      // $FF: synthetic field
      public final AsJavaExtensions $outer;

      public ConcurrentMap asJava() {
         return AsJavaConverters.asJava$(CollectionConverters$.MODULE$, (scala.collection.concurrent.Map)this.m);
      }

      // $FF: synthetic method
      public AsJavaExtensions scala$collection$convert$AsJavaExtensions$ConcurrentMapHasAsJava$$$outer() {
         return this.$outer;
      }

      public ConcurrentMapHasAsJava(final scala.collection.concurrent.Map m) {
         this.m = m;
         if (AsJavaExtensions.this == null) {
            throw null;
         } else {
            this.$outer = AsJavaExtensions.this;
            super();
         }
      }
   }
}

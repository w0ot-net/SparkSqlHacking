package spire.algebra;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.Ring;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005aa\u0002\u0005\n!\u0003\r\nA\u0004\u0005\u0006M\u00011\u0019a\n\u0005\u0006\u0017\u00021\t\u0001T\u0004\u0006#&A\tA\u0015\u0004\u0006\u0011%A\ta\u0015\u0005\u0006?\u0012!\t\u0001\u0019\u0005\u0006C\u0012!)A\u0019\u0005\bq\u0012\t\t\u0011\"\u0003z\u0005)aUM\u001a;N_\u0012,H.\u001a\u0006\u0003\u0015-\tq!\u00197hK\n\u0014\u0018MC\u0001\r\u0003\u0015\u0019\b/\u001b:f\u0007\u0001)2a\u0004\u0011-'\r\u0001\u0001C\u0006\t\u0003#Qi\u0011A\u0005\u0006\u0002'\u0005)1oY1mC&\u0011QC\u0005\u0002\u0004\u0003:L\bcA\f\u001c=9\u0011\u0001$G\u0007\u0002\u0013%\u0011!$C\u0001\ba\u0006\u001c7.Y4f\u0013\taRDA\bBI\u0012LG/\u001b<f\u0003\n<%o\\;q\u0015\tQ\u0012\u0002\u0005\u0002 A1\u0001A!B\u0011\u0001\u0005\u0004\u0011#!\u0001,\u0012\u0005\r\u0002\u0002CA\t%\u0013\t)#CA\u0004O_RD\u0017N\\4\u0002\rM\u001c\u0017\r\\1s+\u0005A\u0003cA\f*W%\u0011!&\b\u0002\u0005%&tw\r\u0005\u0002 Y\u0011IQ\u0006\u0001Q\u0001\u0002\u0003\u0015\rA\t\u0002\u0002%\"2Af\f\u001a=\u0003\u001a\u0003\"!\u0005\u0019\n\u0005E\u0012\"aC:qK\u000eL\u0017\r\\5{K\u0012\fTaI\u001a5mUr!!\u0005\u001b\n\u0005U\u0012\u0012aA%oiF\"AeN\u001e\u0014\u001d\tA4(D\u0001:\u0015\tQT\"\u0001\u0004=e>|GOP\u0005\u0002'E*1%\u0010 A\u007f9\u0011\u0011CP\u0005\u0003\u007fI\tA\u0001T8oOF\"AeN\u001e\u0014c\u0015\u0019#iQ#E\u001d\t\t2)\u0003\u0002E%\u0005)a\t\\8biF\"AeN\u001e\u0014c\u0015\u0019s\t\u0013&J\u001d\t\t\u0002*\u0003\u0002J%\u00051Ai\\;cY\u0016\fD\u0001J\u001c<'\u00051A/[7fg2$2AH'P\u0011\u0015q%\u00011\u0001,\u0003\u0005\u0011\b\"\u0002)\u0003\u0001\u0004q\u0012!\u0001<\u0002\u00151+g\r^'pIVdW\r\u0005\u0002\u0019\tM\u0019A\u0001V,\u0011\u0005E)\u0016B\u0001,\u0013\u0005\u0019\te.\u001f*fMB\u0011\u0001,X\u0007\u00023*\u0011!lW\u0001\u0003S>T\u0011\u0001X\u0001\u0005U\u00064\u0018-\u0003\u0002_3\na1+\u001a:jC2L'0\u00192mK\u00061A(\u001b8jiz\"\u0012AU\u0001\u0006CB\u0004H._\u000b\u0004G\u001aDGC\u00013s!\u0011A\u0002!Z4\u0011\u0005}1G!B\u0011\u0007\u0005\u0004\u0011\u0003CA\u0010i\t%ic\u0001)A\u0001\u0002\u000b\u0007!\u0005\u000b\u0004i_)dg\u000e]\u0019\u0006GM\"4.N\u0019\u0005I]Z4#M\u0003${yjw(\r\u0003%om\u001a\u0012'B\u0012C\u0007>$\u0015\u0007\u0002\u00138wM\tTaI$Ic&\u000bD\u0001J\u001c<'!)1O\u0002a\u0002I\u0006\ta\u000b\u000b\u0002\u0007kB\u0011\u0011C^\u0005\u0003oJ\u0011a!\u001b8mS:,\u0017\u0001D<sSR,'+\u001a9mC\u000e,G#\u0001>\u0011\u0005mtX\"\u0001?\u000b\u0005u\\\u0016\u0001\u00027b]\u001eL!a ?\u0003\r=\u0013'.Z2u\u0001"
)
public interface LeftModule extends AdditiveCommutativeGroup {
   static LeftModule apply(final LeftModule V) {
      return LeftModule$.MODULE$.apply(V);
   }

   Ring scalar();

   Object timesl(final Object r, final Object v);

   // $FF: synthetic method
   static Ring scalar$mcD$sp$(final LeftModule $this) {
      return $this.scalar$mcD$sp();
   }

   default Ring scalar$mcD$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static Ring scalar$mcF$sp$(final LeftModule $this) {
      return $this.scalar$mcF$sp();
   }

   default Ring scalar$mcF$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static Ring scalar$mcI$sp$(final LeftModule $this) {
      return $this.scalar$mcI$sp();
   }

   default Ring scalar$mcI$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static Ring scalar$mcJ$sp$(final LeftModule $this) {
      return $this.scalar$mcJ$sp();
   }

   default Ring scalar$mcJ$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static Object timesl$mcD$sp$(final LeftModule $this, final double r, final Object v) {
      return $this.timesl$mcD$sp(r, v);
   }

   default Object timesl$mcD$sp(final double r, final Object v) {
      return this.timesl(BoxesRunTime.boxToDouble(r), v);
   }

   // $FF: synthetic method
   static Object timesl$mcF$sp$(final LeftModule $this, final float r, final Object v) {
      return $this.timesl$mcF$sp(r, v);
   }

   default Object timesl$mcF$sp(final float r, final Object v) {
      return this.timesl(BoxesRunTime.boxToFloat(r), v);
   }

   // $FF: synthetic method
   static Object timesl$mcI$sp$(final LeftModule $this, final int r, final Object v) {
      return $this.timesl$mcI$sp(r, v);
   }

   default Object timesl$mcI$sp(final int r, final Object v) {
      return this.timesl(BoxesRunTime.boxToInteger(r), v);
   }

   // $FF: synthetic method
   static Object timesl$mcJ$sp$(final LeftModule $this, final long r, final Object v) {
      return $this.timesl$mcJ$sp(r, v);
   }

   default Object timesl$mcJ$sp(final long r, final Object v) {
      return this.timesl(BoxesRunTime.boxToLong(r), v);
   }
}

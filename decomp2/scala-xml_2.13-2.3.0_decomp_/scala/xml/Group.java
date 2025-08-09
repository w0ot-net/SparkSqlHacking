package scala.xml;

import scala.Product;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001de\u0001\u0002\u000f\u001e\u0005\nB\u0001b\u000e\u0001\u0003\u0016\u0004%\t\u0001\u000f\u0005\t\u007f\u0001\u0011\t\u0012)A\u0005s!)\u0001\t\u0001C\u0001\u0003\")A\t\u0001C!q!)Q\t\u0001C!\r\")q\n\u0001C!!\")Q\u000b\u0001C)q!)a\u000b\u0001C\u0005/\")Q\r\u0001C!M\")q\r\u0001C!M\")\u0001\u000e\u0001C!M\")\u0011\u000e\u0001C!U\")!\u000f\u0001C\u0001g\"9!\u0010AA\u0001\n\u0003Y\bbB?\u0001#\u0003%\tA \u0005\n\u0003'\u0001\u0011\u0011!C!\u0003+A\u0011\"!\n\u0001\u0003\u0003%\t!a\n\t\u0013\u0005=\u0002!!A\u0005\u0002\u0005E\u0002\"CA\u001c\u0001\u0005\u0005I\u0011IA\u001d\u0011%\t\t\u0005AA\u0001\n\u0003\n\u0019eB\u0005\u0002Hu\t\t\u0011#\u0001\u0002J\u0019AA$HA\u0001\u0012\u0003\tY\u0005\u0003\u0004A-\u0011\u0005\u00111\r\u0005\n\u0003K2\u0012\u0011!C#\u0003OB\u0011\"!\u001b\u0017\u0003\u0003%\t)a\u001b\t\u0013\u0005=d#!A\u0005\u0002\u0006E\u0004\"CA?-\u0005\u0005I\u0011BA@\u0005\u00159%o\\;q\u0015\tqr$A\u0002y[2T\u0011\u0001I\u0001\u0006g\u000e\fG.Y\u0002\u0001'\u0011\u00011eJ\u0016\u0011\u0005\u0011*S\"A\u000f\n\u0005\u0019j\"\u0001\u0002(pI\u0016\u0004\"\u0001K\u0015\u000e\u0003}I!AK\u0010\u0003\u000fA\u0013x\u000eZ;diB\u0011A\u0006\u000e\b\u0003[Ir!AL\u0019\u000e\u0003=R!\u0001M\u0011\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0001\u0013BA\u001a \u0003\u001d\u0001\u0018mY6bO\u0016L!!\u000e\u001c\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005Mz\u0012!\u00028pI\u0016\u001cX#A\u001d\u0011\u0007ij4%D\u0001<\u0015\tat$\u0001\u0006d_2dWm\u0019;j_:L!AP\u001e\u0003\u0007M+\u0017/\u0001\u0004o_\u0012,7\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\t\u001b\u0005C\u0001\u0013\u0001\u0011\u001594\u00011\u0001:\u0003\u0019!\b.Z*fc\u0006A1-\u00198FcV\fG\u000e\u0006\u0002H\u0015B\u0011\u0001\u0006S\u0005\u0003\u0013~\u0011qAQ8pY\u0016\fg\u000eC\u0003L\u000b\u0001\u0007A*A\u0003pi\",'\u000f\u0005\u0002)\u001b&\u0011aj\b\u0002\u0004\u0003:L\u0018!D:ue&\u001cGo\u0018\u0013fc\u0012*\u0017\u000f\u0006\u0002H#\")1J\u0002a\u0001%B\u0011AeU\u0005\u0003)v\u0011\u0001\"R9vC2LG/_\u0001\u0011E\u0006\u001c\u0018n\u001d$pe\"\u000b7\u000f[\"pI\u0016\fAAZ1jYR\u0011\u0001l\u0017\t\u0003QeK!AW\u0010\u0003\u000f9{G\u000f[5oO\")A\f\u0003a\u0001;\u0006\u0019Qn]4\u0011\u0005y\u0013gBA0a!\tqs$\u0003\u0002b?\u00051\u0001K]3eK\u001aL!a\u00193\u0003\rM#(/\u001b8h\u0015\t\tw$A\u0003mC\n,G.F\u0001Y\u0003)\tG\u000f\u001e:jEV$Xm]\u0001\n]\u0006lWm\u001d9bG\u0016\fQa\u00195jY\u0012,\u0012a\u001b\t\u0003Y>t!\u0001J7\n\u00059l\u0012aH*dC2\fg+\u001a:tS>t7\u000b]3dS\u001aL7MU3ukJtG+\u001f9fg&\u0011\u0001/\u001d\u0002\u000b\u000fJ|W\u000f]\"iS2$'B\u00018\u001e\u0003-\u0011W/\u001b7e'R\u0014\u0018N\\4\u0015\u0005a#\b\"B;\u000e\u0001\u00041\u0018AA:c!\t9\bP\u0004\u0002)e%\u0011\u0011P\u000e\u0002\u000e'R\u0014\u0018N\\4Ck&dG-\u001a:\u0002\t\r|\u0007/\u001f\u000b\u0003\u0005rDqa\u000e\b\u0011\u0002\u0003\u0007\u0011(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003}T3!OA\u0001W\t\t\u0019\u0001\u0005\u0003\u0002\u0006\u0005=QBAA\u0004\u0015\u0011\tI!a\u0003\u0002\u0013Ut7\r[3dW\u0016$'bAA\u0007?\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005E\u0011q\u0001\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002\u0018A!\u0011\u0011DA\u0012\u001b\t\tYB\u0003\u0003\u0002\u001e\u0005}\u0011\u0001\u00027b]\u001eT!!!\t\u0002\t)\fg/Y\u0005\u0004G\u0006m\u0011\u0001\u00049s_\u0012,8\r^!sSRLXCAA\u0015!\rA\u00131F\u0005\u0004\u0003[y\"aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$Hc\u0001'\u00024!I\u0011Q\u0007\n\u0002\u0002\u0003\u0007\u0011\u0011F\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005m\u0002\u0003\u0002\u001e\u0002>1K1!a\u0010<\u0005!IE/\u001a:bi>\u0014\u0018A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!a\u0006\u0002F!I\u0011Q\u0007\u000b\u0002\u0002\u0003\u0007\u0011\u0011F\u0001\u0006\u000fJ|W\u000f\u001d\t\u0003IY\u0019RAFA'\u00033\u0002b!a\u0014\u0002Ve\u0012UBAA)\u0015\r\t\u0019fH\u0001\beVtG/[7f\u0013\u0011\t9&!\u0015\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002\\\u0005\u0005TBAA/\u0015\u0011\ty&a\b\u0002\u0005%|\u0017bA\u001b\u0002^Q\u0011\u0011\u0011J\u0001\ti>\u001cFO]5oOR\u0011\u0011qC\u0001\u0006CB\u0004H.\u001f\u000b\u0004\u0005\u00065\u0004\"B\u001c\u001a\u0001\u0004I\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003g\nI\b\u0005\u0003)\u0003kJ\u0014bAA<?\t1q\n\u001d;j_:D\u0001\"a\u001f\u001b\u0003\u0003\u0005\rAQ\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAAA!\u0011\tI\"a!\n\t\u0005\u0015\u00151\u0004\u0002\u0007\u001f\nTWm\u0019;"
)
public final class Group extends Node implements Product {
   private final Seq nodes;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Seq nodes() {
      return this.nodes;
   }

   public Seq theSeq() {
      return this.nodes();
   }

   public boolean canEqual(final Object other) {
      return other instanceof Group;
   }

   public boolean strict_$eq$eq(final Equality other) {
      if (other instanceof Group) {
         Group var4 = (Group)other;
         Seq xs = var4.nodes();
         return this.nodes().sameElements(xs);
      } else {
         return false;
      }
   }

   public Seq basisForHashCode() {
      return this.nodes();
   }

   private Nothing fail(final String msg) {
      throw new UnsupportedOperationException((new StringBuilder(38)).append("class Group does not support method '").append(msg).append("'").toString());
   }

   public Nothing label() {
      return this.fail("label");
   }

   public Nothing attributes() {
      return this.fail("attributes");
   }

   public Nothing namespace() {
      return this.fail("namespace");
   }

   public Nothing child() {
      return this.fail("child");
   }

   public Nothing buildString(final scala.collection.mutable.StringBuilder sb) {
      return this.fail("toString(StringBuilder)");
   }

   public Group copy(final Seq nodes) {
      return new Group(nodes);
   }

   public Seq copy$default$1() {
      return this.nodes();
   }

   public String productPrefix() {
      return "Group";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.nodes();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "nodes";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public Group(final Seq nodes) {
      this.nodes = nodes;
      Product.$init$(this);
   }
}

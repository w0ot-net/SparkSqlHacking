package org.json4s;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d4A!\u0004\b\u0003'!a1\u0004\u0001C\u0001\u0002\u000b\u0015)\u0019!C\u00059!I\u0001\u0006\u0001B\u0003\u0002\u0003\u0006I!\b\u0005\u0006S\u0001!\tA\u000b\u0005\u0006_\u0001!\t\u0001\r\u0005\bs\u0001\t\t\u0011\"\u0011;\u0011\u001dq\u0004!!A\u0005B}:q!\u0012\b\u0002\u0002#\u0005aIB\u0004\u000e\u001d\u0005\u0005\t\u0012A$\t\u000b%BA\u0011A&\t\u000b1CAQA'\t\u000f]C\u0011\u0011!C\u00031\"9a\fCA\u0001\n\u000by&A\u0004+p\u0015N|gn\u0016:ji\u0006\u0014G.\u001a\u0006\u0003\u001fA\taA[:p]R\u001a(\"A\t\u0002\u0007=\u0014xm\u0001\u0001\u0016\u0005Qy2C\u0001\u0001\u0016!\t1\u0012$D\u0001\u0018\u0015\u0005A\u0012!B:dC2\f\u0017B\u0001\u000e\u0018\u0005\u0019\te.\u001f,bY\u0006arN]4%UN|g\u000eN:%)>T5o\u001c8Xe&$\u0018M\u00197fI\u0011\nW#A\u000f\u0011\u0005yyB\u0002\u0001\u0003\u0006A\u0001\u0011\r!\t\u0002\u0002)F\u0011!%\n\t\u0003-\rJ!\u0001J\f\u0003\u000f9{G\u000f[5oOB\u0011aCJ\u0005\u0003O]\u00111!\u00118z\u0003uy'o\u001a\u0013kg>tGg\u001d\u0013U_*\u001bxN\\,sSR\f'\r\\3%I\u0005\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002,[A\u0019A\u0006A\u000f\u000e\u00039AQAL\u0002A\u0002u\t\u0011!Y\u0001\tCNTe+\u00197vKR\u0011\u0011\u0007\u000e\t\u0003YIJ!a\r\b\u0003\r)3\u0016\r\\;f\u0011\u0015)D\u0001q\u00017\u0003\u00199(/\u001b;feB\u0019AfN\u000f\n\u0005ar!AB,sSR,'/\u0001\u0005iCND7i\u001c3f)\u0005Y\u0004C\u0001\f=\u0013\titCA\u0002J]R\fa!Z9vC2\u001cHC\u0001!D!\t1\u0012)\u0003\u0002C/\t9!i\\8mK\u0006t\u0007b\u0002#\u0007\u0003\u0003\u0005\r!J\u0001\u0004q\u0012\n\u0014A\u0004+p\u0015N|gn\u0016:ji\u0006\u0014G.\u001a\t\u0003Y!\u0019\"\u0001\u0003%\u0011\u0005YI\u0015B\u0001&\u0018\u0005\u0019\te.\u001f*fMR\ta)\u0001\nbg*3\u0016\r\\;fI\u0015DH/\u001a8tS>tWC\u0001(T)\tyE\u000b\u0006\u00022!\")QG\u0003a\u0002#B\u0019Af\u000e*\u0011\u0005y\u0019F!\u0002\u0011\u000b\u0005\u0004\t\u0003\"B+\u000b\u0001\u00041\u0016!\u0002\u0013uQ&\u001c\bc\u0001\u0017\u0001%\u0006\u0011\u0002.Y:i\u0007>$W\rJ3yi\u0016t7/[8o+\tIV\f\u0006\u0002;5\")Qk\u0003a\u00017B\u0019A\u0006\u0001/\u0011\u0005yiF!\u0002\u0011\f\u0005\u0004\t\u0013\u0001E3rk\u0006d7\u000fJ3yi\u0016t7/[8o+\t\u0001g\r\u0006\u0002bGR\u0011\u0001I\u0019\u0005\b\t2\t\t\u00111\u0001&\u0011\u0015)F\u00021\u0001e!\ra\u0003!\u001a\t\u0003=\u0019$Q\u0001\t\u0007C\u0002\u0005\u0002"
)
public final class ToJsonWritable {
   private final Object org$json4s$ToJsonWritable$$a;

   public static boolean equals$extension(final Object $this, final Object x$1) {
      return ToJsonWritable$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final Object $this) {
      return ToJsonWritable$.MODULE$.hashCode$extension($this);
   }

   public static JValue asJValue$extension(final Object $this, final Writer writer) {
      return ToJsonWritable$.MODULE$.asJValue$extension($this, writer);
   }

   public Object org$json4s$ToJsonWritable$$a() {
      return this.org$json4s$ToJsonWritable$$a;
   }

   public JValue asJValue(final Writer writer) {
      return ToJsonWritable$.MODULE$.asJValue$extension(this.org$json4s$ToJsonWritable$$a(), writer);
   }

   public int hashCode() {
      return ToJsonWritable$.MODULE$.hashCode$extension(this.org$json4s$ToJsonWritable$$a());
   }

   public boolean equals(final Object x$1) {
      return ToJsonWritable$.MODULE$.equals$extension(this.org$json4s$ToJsonWritable$$a(), x$1);
   }

   public ToJsonWritable(final Object a) {
      this.org$json4s$ToJsonWritable$$a = a;
   }
}

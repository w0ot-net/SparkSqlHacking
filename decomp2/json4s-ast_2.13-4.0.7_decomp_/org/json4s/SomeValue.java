package org.json4s;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u3A!\u0004\b\u0003'!A1\u0004\u0001BC\u0002\u0013\u0005A\u0004\u0003\u0005)\u0001\t\u0005\t\u0015!\u0003\u001e\u0011\u0015I\u0003\u0001\"\u0001+\u0011\u0015q\u0003\u0001\"\u00010\u0011\u001d\u0011\u0004!!A\u0005BMBqa\u000e\u0001\u0002\u0002\u0013\u0005\u0003hB\u0004?\u001d\u0005\u0005\t\u0012A \u0007\u000f5q\u0011\u0011!E\u0001\u0001\")\u0011\u0006\u0003C\u0001\t\")Q\t\u0003C\u0003\r\"9Q\nCA\u0001\n\u000bq\u0005b\u0002+\t\u0003\u0003%)!\u0016\u0002\n'>lWMV1mk\u0016T!a\u0004\t\u0002\r)\u001cxN\u001c\u001bt\u0015\u0005\t\u0012aA8sO\u000e\u0001QC\u0001\u000b '\t\u0001Q\u0003\u0005\u0002\u001735\tqCC\u0001\u0019\u0003\u0015\u00198-\u00197b\u0013\tQrC\u0001\u0004B]f4\u0016\r\\\u0001\u0004O\u0016$X#A\u000f\u0011\u0005yyB\u0002\u0001\u0003\u0006A\u0001\u0011\r!\t\u0002\u0002\u0003F\u0011!%\n\t\u0003-\rJ!\u0001J\f\u0003\u000f9{G\u000f[5oOB\u0011aCJ\u0005\u0003O]\u00111!\u00118z\u0003\u00119W\r\u001e\u0011\u0002\rqJg.\u001b;?)\tYS\u0006E\u0002-\u0001ui\u0011A\u0004\u0005\u00067\r\u0001\r!H\u0001\bSN,U\u000e\u001d;z+\u0005\u0001t\"A\u0019\u001a\u0003\u0001\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002iA\u0011a#N\u0005\u0003m]\u00111!\u00138u\u0003\u0019)\u0017/^1mgR\u0011\u0011\b\u0010\t\u0003-iJ!aO\f\u0003\u000f\t{w\u000e\\3b]\"9QHBA\u0001\u0002\u0004)\u0013a\u0001=%c\u0005I1k\\7f-\u0006dW/\u001a\t\u0003Y!\u0019\"\u0001C!\u0011\u0005Y\u0011\u0015BA\"\u0018\u0005\u0019\te.\u001f*fMR\tq(A\tjg\u0016k\u0007\u000f^=%Kb$XM\\:j_:,\"a\u0012'\u0015\u0005AB\u0005\"B%\u000b\u0001\u0004Q\u0015!\u0002\u0013uQ&\u001c\bc\u0001\u0017\u0001\u0017B\u0011a\u0004\u0014\u0003\u0006A)\u0011\r!I\u0001\u0013Q\u0006\u001c\bnQ8eK\u0012*\u0007\u0010^3og&|g.\u0006\u0002P'R\u00111\u0007\u0015\u0005\u0006\u0013.\u0001\r!\u0015\t\u0004Y\u0001\u0011\u0006C\u0001\u0010T\t\u0015\u00013B1\u0001\"\u0003A)\u0017/^1mg\u0012*\u0007\u0010^3og&|g.\u0006\u0002W9R\u0011q+\u0017\u000b\u0003saCq!\u0010\u0007\u0002\u0002\u0003\u0007Q\u0005C\u0003J\u0019\u0001\u0007!\fE\u0002-\u0001m\u0003\"A\b/\u0005\u000b\u0001b!\u0019A\u0011"
)
public final class SomeValue {
   private final Object get;

   public static boolean equals$extension(final Object $this, final Object x$1) {
      return SomeValue$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final Object $this) {
      return SomeValue$.MODULE$.hashCode$extension($this);
   }

   public static boolean isEmpty$extension(final Object $this) {
      return SomeValue$.MODULE$.isEmpty$extension($this);
   }

   public Object get() {
      return this.get;
   }

   public boolean isEmpty() {
      return SomeValue$.MODULE$.isEmpty$extension(this.get());
   }

   public int hashCode() {
      return SomeValue$.MODULE$.hashCode$extension(this.get());
   }

   public boolean equals(final Object x$1) {
      return SomeValue$.MODULE$.equals$extension(this.get(), x$1);
   }

   public SomeValue(final Object get) {
      this.get = get;
   }
}

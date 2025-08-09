package org.json4s;

import scala.Function0;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}a\u0001B\t\u0013\u0005]AAB\b\u0001\u0005\u0002\u0003\u0015)Q1A\u0005\n}A\u0011\u0002\n\u0001\u0003\u0006\u0003\u0005\u000b\u0011\u0002\u0011\t\u000b\u0015\u0002A\u0011\u0001\u0014\t\u000b)\u0002A\u0011A\u0016\t\u000bu\u0002A\u0011\u0001 \t\u000b\u001d\u0003A\u0011\u0001%\t\u000fQ\u0003\u0011\u0011!C!+\"9\u0011\fAA\u0001\n\u0003Rva\u00021\u0013\u0003\u0003E\t!\u0019\u0004\b#I\t\t\u0011#\u0001c\u0011\u0015)#\u0002\"\u0001g\u0011\u00159'\u0002\"\u0002i\u0011\u0015\t(\u0002\"\u0002s\u0011\u0015Y(\u0002\"\u0002}\u0011%\tyACA\u0001\n\u000b\t\t\u0002C\u0005\u0002\u0016)\t\t\u0011\"\u0002\u0002\u0018\ta!+Z1eKJ\u001c\u0016P\u001c;bq*\u00111\u0003F\u0001\u0007UN|g\u000eN:\u000b\u0003U\t1a\u001c:h\u0007\u0001\u0019\"\u0001\u0001\r\u0011\u0005eaR\"\u0001\u000e\u000b\u0003m\tQa]2bY\u0006L!!\b\u000e\u0003\r\u0005s\u0017PV1m\u0003my'o\u001a\u0013kg>tGg\u001d\u0013SK\u0006$WM]*z]R\f\u0007\u0010\n\u0013kmV\t\u0001\u0005\u0005\u0002\"E5\t!#\u0003\u0002$%\t1!JV1mk\u0016\fAd\u001c:hI)\u001cxN\u001c\u001btII+\u0017\rZ3s'ftG/\u0019=%I)4\b%\u0001\u0004=S:LGO\u0010\u000b\u0003O!\u0002\"!\t\u0001\t\u000b%\u001a\u0001\u0019\u0001\u0011\u0002\u0005)4\u0018AA1t+\tas\u0006\u0006\u0002.qA\u0011af\f\u0007\u0001\t\u0015\u0001DA1\u00012\u0005\u0005\t\u0015C\u0001\u001a6!\tI2'\u0003\u000255\t9aj\u001c;iS:<\u0007CA\r7\u0013\t9$DA\u0002B]fDQ!\u000f\u0003A\u0004i\naA]3bI\u0016\u0014\bcA\u0011<[%\u0011AH\u0005\u0002\u0007%\u0016\fG-\u001a:\u0002\u000b\u001d,G/Q:\u0016\u0005}\"EC\u0001!F!\rI\u0012iQ\u0005\u0003\u0005j\u0011aa\u00149uS>t\u0007C\u0001\u0018E\t\u0015\u0001TA1\u00012\u0011\u0015IT\u0001q\u0001G!\r\t3hQ\u0001\fO\u0016$\u0018i](s\u000b2\u001cX-\u0006\u0002J\u0019R\u0011!j\u0014\u000b\u0003\u00176\u0003\"A\f'\u0005\u000bA2!\u0019A\u0019\t\u000be2\u00019\u0001(\u0011\u0007\u0005Z4\n\u0003\u0004Q\r\u0011\u0005\r!U\u0001\bI\u00164\u0017-\u001e7u!\rI\"kS\u0005\u0003'j\u0011\u0001\u0002\u00102z]\u0006lWMP\u0001\tQ\u0006\u001c\bnQ8eKR\ta\u000b\u0005\u0002\u001a/&\u0011\u0001L\u0007\u0002\u0004\u0013:$\u0018AB3rk\u0006d7\u000f\u0006\u0002\\=B\u0011\u0011\u0004X\u0005\u0003;j\u0011qAQ8pY\u0016\fg\u000eC\u0004`\u0011\u0005\u0005\t\u0019A\u001b\u0002\u0007a$\u0013'\u0001\u0007SK\u0006$WM]*z]R\f\u0007\u0010\u0005\u0002\"\u0015M\u0011!b\u0019\t\u00033\u0011L!!\u001a\u000e\u0003\r\u0005s\u0017PU3g)\u0005\t\u0017\u0001D1tI\u0015DH/\u001a8tS>tWCA5m)\tQw\u000e\u0006\u0002l[B\u0011a\u0006\u001c\u0003\u0006a1\u0011\r!\r\u0005\u0006s1\u0001\u001dA\u001c\t\u0004CmZ\u0007\"\u00029\r\u0001\u00049\u0013!\u0002\u0013uQ&\u001c\u0018aD4fi\u0006\u001bH%\u001a=uK:\u001c\u0018n\u001c8\u0016\u0005M<HC\u0001;{)\t)\b\u0010E\u0002\u001a\u0003Z\u0004\"AL<\u0005\u000bAj!\u0019A\u0019\t\u000bej\u00019A=\u0011\u0007\u0005Zd\u000fC\u0003q\u001b\u0001\u0007q%A\u000bhKR\f5o\u0014:FYN,G%\u001a=uK:\u001c\u0018n\u001c8\u0016\u0007u\f\u0019\u0001F\u0002\u007f\u0003\u001b!2a`A\u0005)\u0011\t\t!!\u0002\u0011\u00079\n\u0019\u0001B\u00031\u001d\t\u0007\u0011\u0007\u0003\u0004:\u001d\u0001\u000f\u0011q\u0001\t\u0005Cm\n\t\u0001C\u0004Q\u001d\u0011\u0005\r!a\u0003\u0011\te\u0011\u0016\u0011\u0001\u0005\u0006a:\u0001\raJ\u0001\u0013Q\u0006\u001c\bnQ8eK\u0012*\u0007\u0010^3og&|g\u000eF\u0002V\u0003'AQ\u0001]\bA\u0002\u001d\n\u0001#Z9vC2\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\u0005e\u0011Q\u0004\u000b\u00047\u0006m\u0001bB0\u0011\u0003\u0003\u0005\r!\u000e\u0005\u0006aB\u0001\ra\n"
)
public final class ReaderSyntax {
   private final JValue org$json4s$ReaderSyntax$$jv;

   public static boolean equals$extension(final JValue $this, final Object x$1) {
      return ReaderSyntax$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final JValue $this) {
      return ReaderSyntax$.MODULE$.hashCode$extension($this);
   }

   public static Object getAsOrElse$extension(final JValue $this, final Function0 default, final Reader reader) {
      return ReaderSyntax$.MODULE$.getAsOrElse$extension($this, default, reader);
   }

   public static Option getAs$extension(final JValue $this, final Reader reader) {
      return ReaderSyntax$.MODULE$.getAs$extension($this, reader);
   }

   public static Object as$extension(final JValue $this, final Reader reader) {
      return ReaderSyntax$.MODULE$.as$extension($this, reader);
   }

   public JValue org$json4s$ReaderSyntax$$jv() {
      return this.org$json4s$ReaderSyntax$$jv;
   }

   public Object as(final Reader reader) {
      return ReaderSyntax$.MODULE$.as$extension(this.org$json4s$ReaderSyntax$$jv(), reader);
   }

   public Option getAs(final Reader reader) {
      return ReaderSyntax$.MODULE$.getAs$extension(this.org$json4s$ReaderSyntax$$jv(), reader);
   }

   public Object getAsOrElse(final Function0 default, final Reader reader) {
      return ReaderSyntax$.MODULE$.getAsOrElse$extension(this.org$json4s$ReaderSyntax$$jv(), default, reader);
   }

   public int hashCode() {
      return ReaderSyntax$.MODULE$.hashCode$extension(this.org$json4s$ReaderSyntax$$jv());
   }

   public boolean equals(final Object x$1) {
      return ReaderSyntax$.MODULE$.equals$extension(this.org$json4s$ReaderSyntax$$jv(), x$1);
   }

   public ReaderSyntax(final JValue jv) {
      this.org$json4s$ReaderSyntax$$jv = jv;
   }
}

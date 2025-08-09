package scala.collection.generic;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d2A!\u0002\u0004\u0001\u001b!Aa\u0003\u0001BA\u0002\u0013\u0005q\u0003\u0003\u0005\u001c\u0001\t\u0005\r\u0011\"\u0001\u001d\u0011!\u0011\u0003A!A!B\u0013A\u0002\"B\u0012\u0001\t\u0003!#\u0001\u0005#fY\u0016<\u0017\r^3e\u0007>tG/\u001a=u\u0015\t9\u0001\"A\u0004hK:,'/[2\u000b\u0005%Q\u0011AC2pY2,7\r^5p]*\t1\"A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0007\u0001q!\u0003\u0005\u0002\u0010!5\t!\"\u0003\u0002\u0012\u0015\t1\u0011I\\=SK\u001a\u0004\"a\u0005\u000b\u000e\u0003\u0019I!!\u0006\u0004\u0003'\u0011+G.Z4bi\u0016$7+[4oC2d\u0017N\\4\u0002\u001dMLwM\\1m\t\u0016dWmZ1uKV\t\u0001\u0004\u0005\u0002\u00143%\u0011!D\u0002\u0002\u000b'&<g.\u00197mS:<\u0017AE:jO:\fG\u000eR3mK\u001e\fG/Z0%KF$\"!\b\u0011\u0011\u0005=q\u0012BA\u0010\u000b\u0005\u0011)f.\u001b;\t\u000f\u0005\u0012\u0011\u0011!a\u00011\u0005\u0019\u0001\u0010J\u0019\u0002\u001fMLwM\\1m\t\u0016dWmZ1uK\u0002\na\u0001P5oSRtDCA\u0013'!\t\u0019\u0002\u0001C\u0003\u0017\t\u0001\u0007\u0001\u0004"
)
public class DelegatedContext implements DelegatedSignalling {
   private Signalling signalDelegate;

   public boolean isAborted() {
      return DelegatedSignalling.isAborted$(this);
   }

   public void abort() {
      DelegatedSignalling.abort$(this);
   }

   public int indexFlag() {
      return DelegatedSignalling.indexFlag$(this);
   }

   public void setIndexFlag(final int f) {
      DelegatedSignalling.setIndexFlag$(this, f);
   }

   public void setIndexFlagIfGreater(final int f) {
      DelegatedSignalling.setIndexFlagIfGreater$(this, f);
   }

   public void setIndexFlagIfLesser(final int f) {
      DelegatedSignalling.setIndexFlagIfLesser$(this, f);
   }

   public int tag() {
      return DelegatedSignalling.tag$(this);
   }

   public Signalling signalDelegate() {
      return this.signalDelegate;
   }

   public void signalDelegate_$eq(final Signalling x$1) {
      this.signalDelegate = x$1;
   }

   public DelegatedContext(final Signalling signalDelegate) {
      this.signalDelegate = signalDelegate;
      super();
      DelegatedSignalling.$init$(this);
   }
}

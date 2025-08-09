package scala.util.parsing.input;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%3Q!\u0003\u0006\u0002\u0002MAQ!\u0007\u0001\u0005\u0002iAQ\u0001\u000b\u0001\u0005\u0002%BQA\r\u0001\u0005\u0002MBQa\u000e\u0001\u0007\u0002aBQ!\u000f\u0001\u0007\u0002iBQa\u000f\u0001\u0005\u0002qBQa\u0010\u0001\u0007\u0002\u0001CQ\u0001\u0012\u0001\u0007\u0002\u0015\u0013aAU3bI\u0016\u0014(BA\u0006\r\u0003\u0015Ig\u000e];u\u0015\tia\"A\u0004qCJ\u001c\u0018N\\4\u000b\u0005=\u0001\u0012\u0001B;uS2T\u0011!E\u0001\u0006g\u000e\fG.Y\u0002\u0001+\t!rd\u0005\u0002\u0001+A\u0011acF\u0007\u0002!%\u0011\u0001\u0004\u0005\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005Y\u0002c\u0001\u000f\u0001;5\t!\u0002\u0005\u0002\u001f?1\u0001AA\u0002\u0011\u0001\t\u000b\u0007\u0011EA\u0001U#\t\u0011S\u0005\u0005\u0002\u0017G%\u0011A\u0005\u0005\u0002\b\u001d>$\b.\u001b8h!\t1b%\u0003\u0002(!\t\u0019\u0011I\\=\u0002\rM|WO]2f+\u0005Q\u0003CA\u00161\u001b\u0005a#BA\u0017/\u0003\u0011a\u0017M\\4\u000b\u0003=\nAA[1wC&\u0011\u0011\u0007\f\u0002\r\u0007\"\f'oU3rk\u0016t7-Z\u0001\u0007_\u001a47/\u001a;\u0016\u0003Q\u0002\"AF\u001b\n\u0005Y\u0002\"aA%oi\u0006)a-\u001b:tiV\tQ$\u0001\u0003sKN$X#A\u000e\u0002\t\u0011\u0014x\u000e\u001d\u000b\u00037uBQA\u0010\u0004A\u0002Q\n\u0011A\\\u0001\u0004a>\u001cX#A!\u0011\u0005q\u0011\u0015BA\"\u000b\u0005!\u0001vn]5uS>t\u0017!B1u\u000b:$W#\u0001$\u0011\u0005Y9\u0015B\u0001%\u0011\u0005\u001d\u0011un\u001c7fC:\u0004"
)
public abstract class Reader {
   public CharSequence source() {
      throw new NoSuchMethodError("not a char sequence reader");
   }

   public int offset() {
      throw new NoSuchMethodError("not a char sequence reader");
   }

   public abstract Object first();

   public abstract Reader rest();

   public Reader drop(final int n) {
      Reader r = this;

      for(int cnt = n; cnt > 0; --cnt) {
         r = r.rest();
      }

      return r;
   }

   public abstract Position pos();

   public abstract boolean atEnd();
}

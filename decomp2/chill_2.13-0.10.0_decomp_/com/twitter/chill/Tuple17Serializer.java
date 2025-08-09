package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple17;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ea\u0001\u0002\u0003\u0006\u00011AQa\u0018\u0001\u0005\u0002\u0001DQA\u0019\u0001\u0005\u0002\rDQa\u001d\u0001\u0005\u0002Q\u0014\u0011\u0003V;qY\u0016\ftgU3sS\u0006d\u0017N_3s\u0015\t1q!A\u0003dQ&dGN\u0003\u0002\t\u0013\u00059Ao^5ui\u0016\u0014(\"\u0001\u0006\u0002\u0007\r|Wn\u0001\u0001\u0016%5q\u0002f\u000b\u00182i]RT\bQ\"G\u00132{%+V\n\u0004\u000199\u0006cA\b\u0014-9\u0011\u0001#E\u0007\u0002\u000b%\u0011!#B\u0001\ba\u0006\u001c7.Y4f\u0013\t!RCA\u0006L'\u0016\u0014\u0018.\u00197ju\u0016\u0014(B\u0001\n\u0006!M9\"\u0004H\u0014+[A\u001ad'\u000f\u001f@\u0005\u0016C5JT)U\u001b\u0005A\"\"A\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005mA\"a\u0002+va2,\u0017g\u000e\t\u0003;ya\u0001\u0001B\u0003 \u0001\t\u0007\u0001EA\u0001B#\t\tC\u0005\u0005\u0002\u0018E%\u00111\u0005\u0007\u0002\b\u001d>$\b.\u001b8h!\t9R%\u0003\u0002'1\t\u0019\u0011I\\=\u0011\u0005uAC!B\u0015\u0001\u0005\u0004\u0001#!\u0001\"\u0011\u0005uYC!\u0002\u0017\u0001\u0005\u0004\u0001#!A\"\u0011\u0005uqC!B\u0018\u0001\u0005\u0004\u0001#!\u0001#\u0011\u0005u\tD!\u0002\u001a\u0001\u0005\u0004\u0001#!A#\u0011\u0005u!D!B\u001b\u0001\u0005\u0004\u0001#!\u0001$\u0011\u0005u9D!\u0002\u001d\u0001\u0005\u0004\u0001#!A$\u0011\u0005uQD!B\u001e\u0001\u0005\u0004\u0001#!\u0001%\u0011\u0005uiD!\u0002 \u0001\u0005\u0004\u0001#!A%\u0011\u0005u\u0001E!B!\u0001\u0005\u0004\u0001#!\u0001&\u0011\u0005u\u0019E!\u0002#\u0001\u0005\u0004\u0001#!A&\u0011\u0005u1E!B$\u0001\u0005\u0004\u0001#!\u0001'\u0011\u0005uIE!\u0002&\u0001\u0005\u0004\u0001#!A'\u0011\u0005uaE!B'\u0001\u0005\u0004\u0001#!\u0001(\u0011\u0005uyE!\u0002)\u0001\u0005\u0004\u0001#!A(\u0011\u0005u\u0011F!B*\u0001\u0005\u0004\u0001#!\u0001)\u0011\u0005u)F!\u0002,\u0001\u0005\u0004\u0001#!A)\u0011\u0005akV\"A-\u000b\u0005i[\u0016AA5p\u0015\u0005a\u0016\u0001\u00026bm\u0006L!AX-\u0003\u0019M+'/[1mSj\f'\r\\3\u0002\rqJg.\u001b;?)\u0005\t\u0007c\u0005\t\u00019\u001dRS\u0006M\u001a7sqz$)\u0012%L\u001dF#\u0016!B<sSR,G\u0003\u00023hYF\u0004\"aF3\n\u0005\u0019D\"\u0001B+oSRDQ\u0001\u001b\u0002A\u0002%\fAa[:feB\u0011qB[\u0005\u0003WV\u0011Aa\u0013:z_\")QN\u0001a\u0001]\u0006\u0019q.\u001e;\u0011\u0005=y\u0017B\u00019\u0016\u0005\u0019yU\u000f\u001e9vi\")!O\u0001a\u0001-\u0005\u0019qN\u00196\u0002\tI,\u0017\r\u001a\u000b\u0005-U48\u0010C\u0003i\u0007\u0001\u0007\u0011\u000eC\u0003x\u0007\u0001\u0007\u00010\u0001\u0002j]B\u0011q\"_\u0005\u0003uV\u0011Q!\u00138qkRDQ\u0001`\u0002A\u0002u\f1a\u00197t!\u0011q\u00181\u0002\f\u000f\u0007}\f9\u0001E\u0002\u0002\u0002ai!!a\u0001\u000b\u0007\u0005\u00151\"\u0001\u0004=e>|GOP\u0005\u0004\u0003\u0013A\u0012A\u0002)sK\u0012,g-\u0003\u0003\u0002\u000e\u0005=!!B\"mCN\u001c(bAA\u00051\u0001"
)
public class Tuple17Serializer extends Serializer implements Serializable {
   public void write(final Kryo kser, final Output out, final Tuple17 obj) {
      kser.writeClassAndObject(out, obj._1());
      out.flush();
      kser.writeClassAndObject(out, obj._2());
      out.flush();
      kser.writeClassAndObject(out, obj._3());
      out.flush();
      kser.writeClassAndObject(out, obj._4());
      out.flush();
      kser.writeClassAndObject(out, obj._5());
      out.flush();
      kser.writeClassAndObject(out, obj._6());
      out.flush();
      kser.writeClassAndObject(out, obj._7());
      out.flush();
      kser.writeClassAndObject(out, obj._8());
      out.flush();
      kser.writeClassAndObject(out, obj._9());
      out.flush();
      kser.writeClassAndObject(out, obj._10());
      out.flush();
      kser.writeClassAndObject(out, obj._11());
      out.flush();
      kser.writeClassAndObject(out, obj._12());
      out.flush();
      kser.writeClassAndObject(out, obj._13());
      out.flush();
      kser.writeClassAndObject(out, obj._14());
      out.flush();
      kser.writeClassAndObject(out, obj._15());
      out.flush();
      kser.writeClassAndObject(out, obj._16());
      out.flush();
      kser.writeClassAndObject(out, obj._17());
      out.flush();
   }

   public Tuple17 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple17(kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in));
   }

   public Tuple17Serializer() {
      this.setImmutable(true);
   }
}

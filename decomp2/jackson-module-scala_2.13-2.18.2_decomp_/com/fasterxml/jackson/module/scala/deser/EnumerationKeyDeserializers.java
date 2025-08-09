package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.KeyDeserializer;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u00051;QAB\u0004\t\nQ1QAF\u0004\t\n]AQaJ\u0001\u0005\u0002!Bq!K\u0001C\u0002\u0013%!\u0006\u0003\u00047\u0003\u0001\u0006Ia\u000b\u0005\u0006o\u0005!\t\u0001O\u0001\u001c\u000b:,X.\u001a:bi&|gnS3z\t\u0016\u001cXM]5bY&TXM]:\u000b\u0005!I\u0011!\u00023fg\u0016\u0014(B\u0001\u0006\f\u0003\u0015\u00198-\u00197b\u0015\taQ\"\u0001\u0004n_\u0012,H.\u001a\u0006\u0003\u001d=\tqA[1dWN|gN\u0003\u0002\u0011#\u0005Ia-Y:uKJDX\u000e\u001c\u0006\u0002%\u0005\u00191m\\7\u0004\u0001A\u0011Q#A\u0007\u0002\u000f\tYRI\\;nKJ\fG/[8o\u0017\u0016LH)Z:fe&\fG.\u001b>feN\u001c2!\u0001\r!!\tIb$D\u0001\u001b\u0015\tYB$\u0001\u0003mC:<'\"A\u000f\u0002\t)\fg/Y\u0005\u0003?i\u0011aa\u00142kK\u000e$\bCA\u0011&\u001b\u0005\u0011#B\u0001\u0005$\u0015\t!S\"\u0001\u0005eCR\f'-\u001b8e\u0013\t1#E\u0001\tLKf$Um]3sS\u0006d\u0017N_3sg\u00061A(\u001b8jiz\"\u0012\u0001F\u0001\f\u000b:+V*\u0012*B)&{e*F\u0001,!\rIBFL\u0005\u0003[i\u0011Qa\u00117bgN\u0004\"a\f\u001b\u0011\u0005A\u0012T\"A\u0019\u000b\u0003)I!aM\u0019\u0003\u0017\u0015sW/\\3sCRLwN\\\u0005\u0003kI\u0012QAV1mk\u0016\fA\"\u0012(V\u001b\u0016\u0013\u0016\tV%P\u001d\u0002\n1CZ5oI.+\u0017\u0010R3tKJL\u0017\r\\5{KJ$B!O\u001fC\u000fB\u0011!hO\u0007\u0002G%\u0011Ah\t\u0002\u0010\u0017\u0016LH)Z:fe&\fG.\u001b>fe\")a(\u0002a\u0001\u007f\u0005\u0011A\u000f\u001d\t\u0003u\u0001K!!Q\u0012\u0003\u0011)\u000bg/\u0019+za\u0016DQaQ\u0003A\u0002\u0011\u000b1a\u00194h!\tQT)\u0003\u0002GG\t)B)Z:fe&\fG.\u001b>bi&|gnQ8oM&<\u0007\"\u0002%\u0006\u0001\u0004I\u0015\u0001\u00023fg\u000e\u0004\"A\u000f&\n\u0005-\u001b#a\u0004\"fC:$Um]2sSB$\u0018n\u001c8"
)
public final class EnumerationKeyDeserializers {
   public static KeyDeserializer findKeyDeserializer(final JavaType tp, final DeserializationConfig cfg, final BeanDescription desc) {
      return EnumerationKeyDeserializers$.MODULE$.findKeyDeserializer(tp, cfg, desc);
   }
}

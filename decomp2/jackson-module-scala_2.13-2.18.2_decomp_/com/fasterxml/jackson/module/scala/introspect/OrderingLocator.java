package com.fasterxml.jackson.module.scala.introspect;

import com.fasterxml.jackson.databind.JavaType;
import scala.collection.immutable.Map;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005<QAB\u0004\t\u0002Q1QAF\u0004\t\u0002]AQ!H\u0001\u0005\u0002yAqaH\u0001C\u0002\u0013\u0005\u0001\u0005\u0003\u00047\u0003\u0001\u0006I!\t\u0005\u0006\u0011\u0006!\t!S\u0001\u0010\u001fJ$WM]5oO2{7-\u0019;pe*\u0011\u0001\"C\u0001\u000bS:$(o\\:qK\u000e$(B\u0001\u0006\f\u0003\u0015\u00198-\u00197b\u0015\taQ\"\u0001\u0004n_\u0012,H.\u001a\u0006\u0003\u001d=\tqA[1dWN|gN\u0003\u0002\u0011#\u0005Ia-Y:uKJDX\u000e\u001c\u0006\u0002%\u0005\u00191m\\7\u0004\u0001A\u0011Q#A\u0007\u0002\u000f\tyqJ\u001d3fe&tw\rT8dCR|'o\u0005\u0002\u00021A\u0011\u0011dG\u0007\u00025)\t!\"\u0003\u0002\u001d5\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#\u0001\u000b\u0002\u0013=\u0013F)\u0012*J\u001d\u001e\u001bV#A\u0011\u0011\t\t:\u0013FP\u0007\u0002G)\u0011A%J\u0001\nS6lW\u000f^1cY\u0016T!A\n\u000e\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002)G\t\u0019Q*\u001991\u0005)\"\u0004cA\u00161e5\tAF\u0003\u0002.]\u0005!A.\u00198h\u0015\u0005y\u0013\u0001\u00026bm\u0006L!!\r\u0017\u0003\u000b\rc\u0017m]:\u0011\u0005M\"D\u0002\u0001\u0003\nk\u0011\t\t\u0011!A\u0003\u0002]\u00121a\u0018\u00132\u0003)y%\u000bR#S\u0013:;5\u000bI\t\u0003qm\u0002\"!G\u001d\n\u0005iR\"a\u0002(pi\"Lgn\u001a\t\u00033qJ!!\u0010\u000e\u0003\u0007\u0005s\u0017\u0010\r\u0002@\rB\u0019\u0001iQ#\u000e\u0003\u0005S!A\u0011\u000e\u0002\t5\fG\u000f[\u0005\u0003\t\u0006\u0013\u0001b\u0014:eKJLgn\u001a\t\u0003g\u0019#\u0011b\u0012\u0003\u0002\u0002\u0003\u0005)\u0011A\u001c\u0003\u0007}##'\u0001\u0004m_\u000e\fG/Z\u000b\u0003\u0015^#\"aS-\u0011\u00071#fK\u0004\u0002N%:\u0011a*U\u0007\u0002\u001f*\u0011\u0001kE\u0001\u0007yI|w\u000e\u001e \n\u0003)I!a\u0015\u000e\u0002\u000fA\f7m[1hK&\u0011A)\u0016\u0006\u0003'j\u0001\"aM,\u0005\u000ba+!\u0019A\u001c\u0003\u0003QCQAW\u0003A\u0002m\u000b\u0001B[1wCRK\b/\u001a\t\u00039~k\u0011!\u0018\u0006\u0003=6\t\u0001\u0002Z1uC\nLg\u000eZ\u0005\u0003Av\u0013\u0001BS1wCRK\b/\u001a"
)
public final class OrderingLocator {
   public static Ordering locate(final JavaType javaType) {
      return OrderingLocator$.MODULE$.locate(javaType);
   }

   public static Map ORDERINGS() {
      return OrderingLocator$.MODULE$.ORDERINGS();
   }
}

package org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive;

import java.util.List;
import org.apache.hadoop.io.Text;

public interface LazyObjectInspectorParameters {
   boolean isEscaped();

   byte getEscapeChar();

   boolean isExtendedBooleanLiteral();

   List getTimestampFormats();

   byte[] getSeparators();

   Text getNullSequence();

   boolean isLastColumnTakesRest();
}

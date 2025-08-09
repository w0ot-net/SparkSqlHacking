package org.apache.orc.impl;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.hadoop.io.Text;

public class VisitorContextImpl implements Dictionary.VisitorContext {
   private int originalPosition;
   private int start;
   private int end;
   private final DynamicIntArray keyOffsets;
   private final DynamicByteArray byteArray;
   private final Text text = new Text();

   public VisitorContextImpl(DynamicByteArray byteArray, DynamicIntArray keyOffsets) {
      this.byteArray = byteArray;
      this.keyOffsets = keyOffsets;
   }

   public int getOriginalPosition() {
      return this.originalPosition;
   }

   public Text getText() {
      this.byteArray.setText(this.text, this.start, this.end - this.start);
      return this.text;
   }

   public void writeBytes(OutputStream out) throws IOException {
      this.byteArray.write(out, this.start, this.end - this.start);
   }

   public int getLength() {
      return this.end - this.start;
   }

   public void setPosition(int position) {
      this.originalPosition = position;
      this.start = this.keyOffsets.get(this.originalPosition);
      if (position + 1 == this.keyOffsets.size()) {
         this.end = this.byteArray.size();
      } else {
         this.end = this.keyOffsets.get(this.originalPosition + 1);
      }

   }
}

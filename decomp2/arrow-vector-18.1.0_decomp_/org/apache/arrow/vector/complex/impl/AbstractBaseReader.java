package org.apache.arrow.vector.complex.impl;

import java.util.Iterator;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.holders.DenseUnionHolder;
import org.apache.arrow.vector.holders.UnionHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class AbstractBaseReader implements FieldReader {
   static final Logger logger = LoggerFactory.getLogger(AbstractBaseReader.class);
   private int index;

   public AbstractBaseReader() {
   }

   public int getPosition() {
      return this.index;
   }

   public void setPosition(int index) {
      this.index = index;
   }

   protected int idx() {
      return this.index;
   }

   public void reset() {
      this.index = 0;
   }

   public Iterator iterator() {
      throw new IllegalStateException("The current reader doesn't support reading as a map.");
   }

   public boolean next() {
      throw new IllegalStateException("The current reader doesn't support getting next information.");
   }

   public int size() {
      throw new IllegalStateException("The current reader doesn't support getting size information.");
   }

   public void read(UnionHolder holder) {
      holder.reader = this;
      holder.isSet = this.isSet() ? 1 : 0;
   }

   public void read(int index, UnionHolder holder) {
      throw new IllegalStateException("The current reader doesn't support reading union type");
   }

   public void copyAsValue(UnionWriter writer) {
      throw new IllegalStateException("The current reader doesn't support reading union type");
   }

   public void read(DenseUnionHolder holder) {
      holder.reader = this;
      holder.isSet = this.isSet() ? 1 : 0;
   }

   public void read(int index, DenseUnionHolder holder) {
      throw new IllegalStateException("The current reader doesn't support reading dense union type");
   }

   public void copyAsValue(DenseUnionWriter writer) {
      throw new IllegalStateException("The current reader doesn't support reading dense union type");
   }

   public void copyAsValue(BaseWriter.ListWriter writer) {
      ComplexCopier.copy(this, (FieldWriter)writer);
   }

   public void copyAsValue(BaseWriter.MapWriter writer) {
      ComplexCopier.copy(this, (FieldWriter)writer);
   }
}

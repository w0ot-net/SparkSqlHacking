package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.complex.ListVector;
import org.apache.arrow.vector.complex.ListViewVector;
import org.apache.arrow.vector.complex.MapVector;
import org.apache.arrow.vector.complex.NonNullableStructVector;
import org.apache.arrow.vector.complex.StateTool;
import org.apache.arrow.vector.complex.StructVector;
import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.types.pojo.Field;

public class ComplexWriterImpl extends AbstractFieldWriter implements BaseWriter.ComplexWriter {
   private NullableStructWriter structRoot;
   private UnionListWriter listRoot;
   private UnionListViewWriter listViewRoot;
   private UnionMapWriter mapRoot;
   private final NonNullableStructVector container;
   Mode mode;
   private final String name;
   private final boolean unionEnabled;
   private final NullableStructWriterFactory nullableStructWriterFactory;

   public ComplexWriterImpl(String name, NonNullableStructVector container, boolean unionEnabled, boolean caseSensitive) {
      this.mode = ComplexWriterImpl.Mode.INIT;
      this.name = name;
      this.container = container;
      this.unionEnabled = unionEnabled;
      this.nullableStructWriterFactory = caseSensitive ? NullableStructWriterFactory.getNullableCaseSensitiveStructWriterFactoryInstance() : NullableStructWriterFactory.getNullableStructWriterFactoryInstance();
   }

   public ComplexWriterImpl(String name, NonNullableStructVector container, boolean unionEnabled) {
      this(name, container, unionEnabled, false);
   }

   public ComplexWriterImpl(String name, NonNullableStructVector container) {
      this(name, container, false);
   }

   public Field getField() {
      return this.container.getField();
   }

   public int getValueCapacity() {
      return this.container.getValueCapacity();
   }

   private void check(Mode... modes) {
      StateTool.check(this.mode, modes);
   }

   public void reset() {
      this.setPosition(0);
   }

   public void close() throws Exception {
      this.clear();
      this.structRoot.close();
      if (this.listRoot != null) {
         this.listRoot.close();
      }

      if (this.listViewRoot != null) {
         this.listViewRoot.close();
      }

   }

   public void clear() {
      switch (this.mode.ordinal()) {
         case 1:
            this.structRoot.clear();
            break;
         case 2:
            this.listRoot.clear();
            break;
         case 3:
            this.listViewRoot.clear();
            break;
         case 4:
            this.mapRoot.clear();
      }

   }

   public void setValueCount(int count) {
      switch (this.mode.ordinal()) {
         case 1:
            this.structRoot.setValueCount(count);
            break;
         case 2:
            this.listRoot.setValueCount(count);
            break;
         case 3:
            this.listViewRoot.setValueCount(count);
            break;
         case 4:
            this.mapRoot.setValueCount(count);
      }

   }

   public void setPosition(int index) {
      super.setPosition(index);
      switch (this.mode.ordinal()) {
         case 1:
            this.structRoot.setPosition(index);
            break;
         case 2:
            this.listRoot.setPosition(index);
            break;
         case 3:
            this.listViewRoot.setPosition(index);
            break;
         case 4:
            this.mapRoot.setPosition(index);
      }

   }

   public BaseWriter.StructWriter directStruct() {
      Preconditions.checkArgument(this.name == null);
      switch (this.mode.ordinal()) {
         case 0:
            this.structRoot = this.nullableStructWriterFactory.build((StructVector)this.container);
            this.structRoot.setPosition(this.idx());
            this.mode = ComplexWriterImpl.Mode.STRUCT;
         case 1:
            break;
         default:
            this.check(ComplexWriterImpl.Mode.INIT, ComplexWriterImpl.Mode.STRUCT);
      }

      return this.structRoot;
   }

   public BaseWriter.StructWriter rootAsStruct() {
      switch (this.mode.ordinal()) {
         case 0:
            StructVector struct = this.container.addOrGetStruct(this.name);
            this.structRoot = this.nullableStructWriterFactory.build(struct);
            this.structRoot.setPosition(this.idx());
            this.mode = ComplexWriterImpl.Mode.STRUCT;
         case 1:
            break;
         default:
            this.check(ComplexWriterImpl.Mode.INIT, ComplexWriterImpl.Mode.STRUCT);
      }

      return this.structRoot;
   }

   public void allocate() {
      if (this.structRoot != null) {
         this.structRoot.allocate();
      } else if (this.listRoot != null) {
         this.listRoot.allocate();
      }

   }

   public BaseWriter.ListWriter rootAsList() {
      switch (this.mode.ordinal()) {
         case 0:
            int vectorCount = this.container.size();
            ListVector listVector = this.container.addOrGetList(this.name);
            if (this.container.size() > vectorCount) {
               listVector.allocateNew();
            }

            this.listRoot = new UnionListWriter(listVector, this.nullableStructWriterFactory);
            this.listRoot.setPosition(this.idx());
            this.mode = ComplexWriterImpl.Mode.LIST;
         case 2:
            break;
         default:
            this.check(ComplexWriterImpl.Mode.INIT, ComplexWriterImpl.Mode.STRUCT);
      }

      return this.listRoot;
   }

   public BaseWriter.ListWriter rootAsListView() {
      switch (this.mode.ordinal()) {
         case 0:
            int vectorCount = this.container.size();
            ListViewVector listVector = this.container.addOrGetListView(this.name);
            if (this.container.size() > vectorCount) {
               listVector.allocateNew();
            }

            this.listViewRoot = new UnionListViewWriter(listVector, this.nullableStructWriterFactory);
            this.listViewRoot.setPosition(this.idx());
            this.mode = ComplexWriterImpl.Mode.LISTVIEW;
         case 3:
            break;
         default:
            this.check(ComplexWriterImpl.Mode.INIT, ComplexWriterImpl.Mode.STRUCT);
      }

      return this.listViewRoot;
   }

   public BaseWriter.MapWriter rootAsMap(boolean keysSorted) {
      switch (this.mode.ordinal()) {
         case 0:
            int vectorCount = this.container.size();
            MapVector mapVector = this.container.addOrGetMap(this.name, keysSorted);
            if (this.container.size() > vectorCount) {
               mapVector.allocateNew();
            }

            this.mapRoot = new UnionMapWriter(mapVector);
            this.mapRoot.setPosition(this.idx());
            this.mode = ComplexWriterImpl.Mode.MAP;
         case 4:
            break;
         default:
            this.check(ComplexWriterImpl.Mode.INIT, ComplexWriterImpl.Mode.STRUCT);
      }

      return this.mapRoot;
   }

   private static enum Mode {
      INIT,
      STRUCT,
      LIST,
      LISTVIEW,
      MAP;

      // $FF: synthetic method
      private static Mode[] $values() {
         return new Mode[]{INIT, STRUCT, LIST, LISTVIEW, MAP};
      }
   }
}

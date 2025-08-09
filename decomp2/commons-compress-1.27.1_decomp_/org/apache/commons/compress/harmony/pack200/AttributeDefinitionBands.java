package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.objectweb.asm.Attribute;

public class AttributeDefinitionBands extends BandSet {
   public static final int CONTEXT_CLASS = 0;
   public static final int CONTEXT_CODE = 3;
   public static final int CONTEXT_FIELD = 1;
   public static final int CONTEXT_METHOD = 2;
   private final List classAttributeLayouts = new ArrayList();
   private final List methodAttributeLayouts = new ArrayList();
   private final List fieldAttributeLayouts = new ArrayList();
   private final List codeAttributeLayouts = new ArrayList();
   private final List attributeDefinitions = new ArrayList();
   private final CpBands cpBands;
   private final Segment segment;

   public AttributeDefinitionBands(Segment segment, int effort, Attribute[] attributePrototypes) {
      super(effort, segment.getSegmentHeader());
      this.cpBands = segment.getCpBands();
      this.segment = segment;
      Map<String, String> classLayouts = new HashMap();
      Map<String, String> methodLayouts = new HashMap();
      Map<String, String> fieldLayouts = new HashMap();
      Map<String, String> codeLayouts = new HashMap();

      for(Attribute attributePrototype : attributePrototypes) {
         NewAttribute newAttribute = (NewAttribute)attributePrototype;
         if (!(newAttribute instanceof NewAttribute.ErrorAttribute) && !(newAttribute instanceof NewAttribute.PassAttribute) && !(newAttribute instanceof NewAttribute.StripAttribute)) {
            if (newAttribute.isContextClass()) {
               classLayouts.put(newAttribute.type, newAttribute.getLayout());
            }

            if (newAttribute.isContextMethod()) {
               methodLayouts.put(newAttribute.type, newAttribute.getLayout());
            }

            if (newAttribute.isContextField()) {
               fieldLayouts.put(newAttribute.type, newAttribute.getLayout());
            }

            if (newAttribute.isContextCode()) {
               codeLayouts.put(newAttribute.type, newAttribute.getLayout());
            }
         }
      }

      if (classLayouts.size() > 7) {
         this.segmentHeader.setHave_class_flags_hi(true);
      }

      if (methodLayouts.size() > 6) {
         this.segmentHeader.setHave_method_flags_hi(true);
      }

      if (fieldLayouts.size() > 10) {
         this.segmentHeader.setHave_field_flags_hi(true);
      }

      if (codeLayouts.size() > 15) {
         this.segmentHeader.setHave_code_flags_hi(true);
      }

      int[] availableClassIndices = new int[]{25, 26, 27, 28, 29, 30, 31};
      if (classLayouts.size() > 7) {
         availableClassIndices = this.addHighIndices(availableClassIndices);
      }

      this.addAttributeDefinitions(classLayouts, availableClassIndices, 0);
      int[] availableMethodIndices = new int[]{26, 27, 28, 29, 30, 31};
      if (this.methodAttributeLayouts.size() > 6) {
         availableMethodIndices = this.addHighIndices(availableMethodIndices);
      }

      this.addAttributeDefinitions(methodLayouts, availableMethodIndices, 2);
      int[] availableFieldIndices = new int[]{18, 23, 24, 25, 26, 27, 28, 29, 30, 31};
      if (this.fieldAttributeLayouts.size() > 10) {
         availableFieldIndices = this.addHighIndices(availableFieldIndices);
      }

      this.addAttributeDefinitions(fieldLayouts, availableFieldIndices, 1);
      int[] availableCodeIndices = new int[]{17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
      if (this.codeAttributeLayouts.size() > 15) {
         availableCodeIndices = this.addHighIndices(availableCodeIndices);
      }

      this.addAttributeDefinitions(codeLayouts, availableCodeIndices, 3);
   }

   private void addAttributeDefinitions(Map layoutMap, int[] availableIndices, int contextType) {
      int i = 0;
      layoutMap.forEach((name, layout) -> {
         int index = availableIndices[0];
         AttributeDefinition definition = new AttributeDefinition(index, contextType, this.cpBands.getCPUtf8(name), this.cpBands.getCPUtf8(layout));
         this.attributeDefinitions.add(definition);
         switch (contextType) {
            case 0:
               this.classAttributeLayouts.add(definition);
               break;
            case 1:
               this.fieldAttributeLayouts.add(definition);
               break;
            case 2:
               this.methodAttributeLayouts.add(definition);
               break;
            case 3:
               this.codeAttributeLayouts.add(definition);
         }

      });
   }

   private int[] addHighIndices(int[] availableIndices) {
      int[] temp = Arrays.copyOf(availableIndices, availableIndices.length + 32);
      int j = 32;

      for(int i = availableIndices.length; i < temp.length; ++i) {
         temp[i] = j++;
      }

      return temp;
   }

   private void addSyntheticDefinitions() {
      boolean anySytheticClasses = this.segment.getClassBands().isAnySyntheticClasses();
      boolean anySyntheticMethods = this.segment.getClassBands().isAnySyntheticMethods();
      boolean anySyntheticFields = this.segment.getClassBands().isAnySyntheticFields();
      if (anySytheticClasses || anySyntheticMethods || anySyntheticFields) {
         CPUTF8 syntheticUTF = this.cpBands.getCPUtf8("Synthetic");
         CPUTF8 emptyUTF = this.cpBands.getCPUtf8("");
         if (anySytheticClasses) {
            this.attributeDefinitions.add(new AttributeDefinition(12, 0, syntheticUTF, emptyUTF));
         }

         if (anySyntheticMethods) {
            this.attributeDefinitions.add(new AttributeDefinition(12, 2, syntheticUTF, emptyUTF));
         }

         if (anySyntheticFields) {
            this.attributeDefinitions.add(new AttributeDefinition(12, 1, syntheticUTF, emptyUTF));
         }
      }

   }

   public void finaliseBands() {
      this.addSyntheticDefinitions();
      this.segmentHeader.setAttribute_definition_count(this.attributeDefinitions.size());
   }

   public List getClassAttributeLayouts() {
      return this.classAttributeLayouts;
   }

   public List getCodeAttributeLayouts() {
      return this.codeAttributeLayouts;
   }

   public List getFieldAttributeLayouts() {
      return this.fieldAttributeLayouts;
   }

   public List getMethodAttributeLayouts() {
      return this.methodAttributeLayouts;
   }

   public void pack(OutputStream out) throws IOException, Pack200Exception {
      PackingUtils.log("Writing attribute definition bands...");
      int[] attributeDefinitionHeader = new int[this.attributeDefinitions.size()];
      int[] attributeDefinitionName = new int[this.attributeDefinitions.size()];
      int[] attributeDefinitionLayout = new int[this.attributeDefinitions.size()];

      for(int i = 0; i < attributeDefinitionLayout.length; ++i) {
         AttributeDefinition def = (AttributeDefinition)this.attributeDefinitions.get(i);
         attributeDefinitionHeader[i] = def.contextType | def.index + 1 << 2;
         attributeDefinitionName[i] = def.name.getIndex();
         attributeDefinitionLayout[i] = def.layout.getIndex();
      }

      byte[] encodedBand = this.encodeBandInt("attributeDefinitionHeader", attributeDefinitionHeader, Codec.BYTE1);
      out.write(encodedBand);
      PackingUtils.log("Wrote " + encodedBand.length + " bytes from attributeDefinitionHeader[" + attributeDefinitionHeader.length + "]");
      encodedBand = this.encodeBandInt("attributeDefinitionName", attributeDefinitionName, Codec.UNSIGNED5);
      out.write(encodedBand);
      PackingUtils.log("Wrote " + encodedBand.length + " bytes from attributeDefinitionName[" + attributeDefinitionName.length + "]");
      encodedBand = this.encodeBandInt("attributeDefinitionLayout", attributeDefinitionLayout, Codec.UNSIGNED5);
      out.write(encodedBand);
      PackingUtils.log("Wrote " + encodedBand.length + " bytes from attributeDefinitionLayout[" + attributeDefinitionLayout.length + "]");
   }

   public static class AttributeDefinition {
      public int index;
      public int contextType;
      public CPUTF8 name;
      public CPUTF8 layout;

      public AttributeDefinition(int index, int contextType, CPUTF8 name, CPUTF8 layout) {
         this.index = index;
         this.contextType = contextType;
         this.name = name;
         this.layout = layout;
      }
   }
}

package org.apache.orc.protobuf;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

@CheckReturnValue
public final class Descriptors {
   private static final Logger logger = Logger.getLogger(Descriptors.class.getName());
   private static final int[] EMPTY_INT_ARRAY = new int[0];
   private static final Descriptor[] EMPTY_DESCRIPTORS = new Descriptor[0];
   private static final FieldDescriptor[] EMPTY_FIELD_DESCRIPTORS = new FieldDescriptor[0];
   private static final EnumDescriptor[] EMPTY_ENUM_DESCRIPTORS = new EnumDescriptor[0];
   private static final ServiceDescriptor[] EMPTY_SERVICE_DESCRIPTORS = new ServiceDescriptor[0];
   private static final OneofDescriptor[] EMPTY_ONEOF_DESCRIPTORS = new OneofDescriptor[0];

   private static String computeFullName(final FileDescriptor file, final Descriptor parent, final String name) {
      if (parent != null) {
         return parent.getFullName() + '.' + name;
      } else {
         String packageName = file.getPackage();
         return !packageName.isEmpty() ? packageName + '.' + name : name;
      }
   }

   private static Object binarySearch(Object[] array, int size, NumberGetter getter, int number) {
      int left = 0;
      int right = size - 1;

      while(left <= right) {
         int mid = (left + right) / 2;
         T midValue = (T)array[mid];
         int midValueNumber = getter.getNumber(midValue);
         if (number < midValueNumber) {
            right = mid - 1;
         } else {
            if (number <= midValueNumber) {
               return midValue;
            }

            left = mid + 1;
         }
      }

      return null;
   }

   public static final class FileDescriptor extends GenericDescriptor {
      private DescriptorProtos.FileDescriptorProto proto;
      private final Descriptor[] messageTypes;
      private final EnumDescriptor[] enumTypes;
      private final ServiceDescriptor[] services;
      private final FieldDescriptor[] extensions;
      private final FileDescriptor[] dependencies;
      private final FileDescriptor[] publicDependencies;
      private final DescriptorPool pool;

      public DescriptorProtos.FileDescriptorProto toProto() {
         return this.proto;
      }

      public String getName() {
         return this.proto.getName();
      }

      public FileDescriptor getFile() {
         return this;
      }

      public String getFullName() {
         return this.proto.getName();
      }

      public String getPackage() {
         return this.proto.getPackage();
      }

      public DescriptorProtos.FileOptions getOptions() {
         return this.proto.getOptions();
      }

      public List getMessageTypes() {
         return Collections.unmodifiableList(Arrays.asList(this.messageTypes));
      }

      public List getEnumTypes() {
         return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
      }

      public List getServices() {
         return Collections.unmodifiableList(Arrays.asList(this.services));
      }

      public List getExtensions() {
         return Collections.unmodifiableList(Arrays.asList(this.extensions));
      }

      public List getDependencies() {
         return Collections.unmodifiableList(Arrays.asList(this.dependencies));
      }

      public List getPublicDependencies() {
         return Collections.unmodifiableList(Arrays.asList(this.publicDependencies));
      }

      /** @deprecated */
      @Deprecated
      public Syntax getSyntax() {
         if (Descriptors.FileDescriptor.Syntax.PROTO3.name.equals(this.proto.getSyntax())) {
            return Descriptors.FileDescriptor.Syntax.PROTO3;
         } else {
            return Descriptors.FileDescriptor.Syntax.EDITIONS.name.equals(this.proto.getSyntax()) ? Descriptors.FileDescriptor.Syntax.EDITIONS : Descriptors.FileDescriptor.Syntax.PROTO2;
         }
      }

      public DescriptorProtos.Edition getEdition() {
         return this.proto.getEdition();
      }

      public String getEditionName() {
         return this.proto.getEdition().equals(DescriptorProtos.Edition.EDITION_UNKNOWN) ? "" : this.proto.getEdition().name().substring("EDITION_".length());
      }

      public void copyHeadingTo(DescriptorProtos.FileDescriptorProto.Builder protoBuilder) {
         protoBuilder.setName(this.getName()).setSyntax(this.getSyntax().name);
         if (!this.getPackage().isEmpty()) {
            protoBuilder.setPackage(this.getPackage());
         }

         if (this.getSyntax().equals(Descriptors.FileDescriptor.Syntax.EDITIONS)) {
            protoBuilder.setEdition(this.getEdition());
         }

         if (!this.getOptions().equals(DescriptorProtos.FileOptions.getDefaultInstance())) {
            protoBuilder.setOptions(this.getOptions());
         }

      }

      public Descriptor findMessageTypeByName(String name) {
         if (name.indexOf(46) != -1) {
            return null;
         } else {
            String packageName = this.getPackage();
            if (!packageName.isEmpty()) {
               name = packageName + '.' + name;
            }

            GenericDescriptor result = this.pool.findSymbol(name);
            return result instanceof Descriptor && result.getFile() == this ? (Descriptor)result : null;
         }
      }

      public EnumDescriptor findEnumTypeByName(String name) {
         if (name.indexOf(46) != -1) {
            return null;
         } else {
            String packageName = this.getPackage();
            if (!packageName.isEmpty()) {
               name = packageName + '.' + name;
            }

            GenericDescriptor result = this.pool.findSymbol(name);
            return result instanceof EnumDescriptor && result.getFile() == this ? (EnumDescriptor)result : null;
         }
      }

      public ServiceDescriptor findServiceByName(String name) {
         if (name.indexOf(46) != -1) {
            return null;
         } else {
            String packageName = this.getPackage();
            if (!packageName.isEmpty()) {
               name = packageName + '.' + name;
            }

            GenericDescriptor result = this.pool.findSymbol(name);
            return result instanceof ServiceDescriptor && result.getFile() == this ? (ServiceDescriptor)result : null;
         }
      }

      public FieldDescriptor findExtensionByName(String name) {
         if (name.indexOf(46) != -1) {
            return null;
         } else {
            String packageName = this.getPackage();
            if (!packageName.isEmpty()) {
               name = packageName + '.' + name;
            }

            GenericDescriptor result = this.pool.findSymbol(name);
            return result instanceof FieldDescriptor && result.getFile() == this ? (FieldDescriptor)result : null;
         }
      }

      public static FileDescriptor buildFrom(DescriptorProtos.FileDescriptorProto proto, FileDescriptor[] dependencies) throws DescriptorValidationException {
         return buildFrom(proto, dependencies, false);
      }

      public static FileDescriptor buildFrom(DescriptorProtos.FileDescriptorProto proto, FileDescriptor[] dependencies, boolean allowUnknownDependencies) throws DescriptorValidationException {
         DescriptorPool pool = new DescriptorPool(dependencies, allowUnknownDependencies);
         FileDescriptor result = new FileDescriptor(proto, dependencies, pool, allowUnknownDependencies);
         result.crossLink();
         return result;
      }

      private static byte[] latin1Cat(final String[] strings) {
         if (strings.length == 1) {
            return strings[0].getBytes(Internal.ISO_8859_1);
         } else {
            StringBuilder descriptorData = new StringBuilder();

            for(String part : strings) {
               descriptorData.append(part);
            }

            return descriptorData.toString().getBytes(Internal.ISO_8859_1);
         }
      }

      private static FileDescriptor[] findDescriptors(final Class descriptorOuterClass, final String[] dependencyClassNames, final String[] dependencyFileNames) {
         List<FileDescriptor> descriptors = new ArrayList();

         for(int i = 0; i < dependencyClassNames.length; ++i) {
            try {
               Class<?> clazz = descriptorOuterClass.getClassLoader().loadClass(dependencyClassNames[i]);
               descriptors.add((FileDescriptor)clazz.getField("descriptor").get((Object)null));
            } catch (Exception var6) {
               Descriptors.logger.warning("Descriptors for \"" + dependencyFileNames[i] + "\" can not be found.");
            }
         }

         return (FileDescriptor[])descriptors.toArray(new FileDescriptor[0]);
      }

      /** @deprecated */
      @Deprecated
      public static void internalBuildGeneratedFileFrom(final String[] descriptorDataParts, final FileDescriptor[] dependencies, final InternalDescriptorAssigner descriptorAssigner) {
         byte[] descriptorBytes = latin1Cat(descriptorDataParts);

         DescriptorProtos.FileDescriptorProto proto;
         try {
            proto = DescriptorProtos.FileDescriptorProto.parseFrom(descriptorBytes);
         } catch (InvalidProtocolBufferException e) {
            throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
         }

         FileDescriptor result;
         try {
            result = buildFrom(proto, dependencies, true);
         } catch (DescriptorValidationException e) {
            throw new IllegalArgumentException("Invalid embedded descriptor for \"" + proto.getName() + "\".", e);
         }

         ExtensionRegistry registry = descriptorAssigner.assignDescriptors(result);
         if (registry != null) {
            try {
               proto = DescriptorProtos.FileDescriptorProto.parseFrom((byte[])descriptorBytes, registry);
            } catch (InvalidProtocolBufferException e) {
               throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
            }

            result.setProto(proto);
         }

      }

      public static FileDescriptor internalBuildGeneratedFileFrom(final String[] descriptorDataParts, final FileDescriptor[] dependencies) {
         byte[] descriptorBytes = latin1Cat(descriptorDataParts);

         DescriptorProtos.FileDescriptorProto proto;
         try {
            proto = DescriptorProtos.FileDescriptorProto.parseFrom(descriptorBytes);
         } catch (InvalidProtocolBufferException e) {
            throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
         }

         try {
            return buildFrom(proto, dependencies, true);
         } catch (DescriptorValidationException e) {
            throw new IllegalArgumentException("Invalid embedded descriptor for \"" + proto.getName() + "\".", e);
         }
      }

      /** @deprecated */
      @Deprecated
      public static void internalBuildGeneratedFileFrom(final String[] descriptorDataParts, final Class descriptorOuterClass, final String[] dependencyClassNames, final String[] dependencyFileNames, final InternalDescriptorAssigner descriptorAssigner) {
         FileDescriptor[] dependencies = findDescriptors(descriptorOuterClass, dependencyClassNames, dependencyFileNames);
         internalBuildGeneratedFileFrom(descriptorDataParts, dependencies, descriptorAssigner);
      }

      public static FileDescriptor internalBuildGeneratedFileFrom(final String[] descriptorDataParts, final Class descriptorOuterClass, final String[] dependencyClassNames, final String[] dependencyFileNames) {
         FileDescriptor[] dependencies = findDescriptors(descriptorOuterClass, dependencyClassNames, dependencyFileNames);
         return internalBuildGeneratedFileFrom(descriptorDataParts, dependencies);
      }

      public static void internalUpdateFileDescriptor(FileDescriptor descriptor, ExtensionRegistry registry) {
         ByteString bytes = descriptor.proto.toByteString();

         try {
            DescriptorProtos.FileDescriptorProto proto = DescriptorProtos.FileDescriptorProto.parseFrom((ByteString)bytes, registry);
            descriptor.setProto(proto);
         } catch (InvalidProtocolBufferException e) {
            throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
         }
      }

      private FileDescriptor(final DescriptorProtos.FileDescriptorProto proto, final FileDescriptor[] dependencies, final DescriptorPool pool, boolean allowUnknownDependencies) throws DescriptorValidationException {
         this.pool = pool;
         this.proto = proto;
         this.dependencies = (FileDescriptor[])(([Lorg.apache.orc.protobuf.Descriptors.FileDescriptor;)dependencies).clone();
         HashMap<String, FileDescriptor> nameToFileMap = new HashMap();

         for(FileDescriptor file : dependencies) {
            nameToFileMap.put(file.getName(), file);
         }

         List<FileDescriptor> publicDependencies = new ArrayList();

         for(int i = 0; i < proto.getPublicDependencyCount(); ++i) {
            int index = proto.getPublicDependency(i);
            if (index < 0 || index >= proto.getDependencyCount()) {
               throw new DescriptorValidationException(this, "Invalid public dependency index.");
            }

            String name = proto.getDependency(index);
            FileDescriptor file = (FileDescriptor)nameToFileMap.get(name);
            if (file == null) {
               if (!allowUnknownDependencies) {
                  throw new DescriptorValidationException(this, "Invalid public dependency: " + name);
               }
            } else {
               publicDependencies.add(file);
            }
         }

         this.publicDependencies = new FileDescriptor[publicDependencies.size()];
         publicDependencies.toArray(this.publicDependencies);
         pool.addPackage(this.getPackage(), this);
         this.messageTypes = proto.getMessageTypeCount() > 0 ? new Descriptor[proto.getMessageTypeCount()] : Descriptors.EMPTY_DESCRIPTORS;

         for(int i = 0; i < proto.getMessageTypeCount(); ++i) {
            this.messageTypes[i] = new Descriptor(proto.getMessageType(i), this, (Descriptor)null, i);
         }

         this.enumTypes = proto.getEnumTypeCount() > 0 ? new EnumDescriptor[proto.getEnumTypeCount()] : Descriptors.EMPTY_ENUM_DESCRIPTORS;

         for(int i = 0; i < proto.getEnumTypeCount(); ++i) {
            this.enumTypes[i] = new EnumDescriptor(proto.getEnumType(i), this, (Descriptor)null, i);
         }

         this.services = proto.getServiceCount() > 0 ? new ServiceDescriptor[proto.getServiceCount()] : Descriptors.EMPTY_SERVICE_DESCRIPTORS;

         for(int i = 0; i < proto.getServiceCount(); ++i) {
            this.services[i] = new ServiceDescriptor(proto.getService(i), this, i);
         }

         this.extensions = proto.getExtensionCount() > 0 ? new FieldDescriptor[proto.getExtensionCount()] : Descriptors.EMPTY_FIELD_DESCRIPTORS;

         for(int i = 0; i < proto.getExtensionCount(); ++i) {
            this.extensions[i] = new FieldDescriptor(proto.getExtension(i), this, (Descriptor)null, i, true);
         }

      }

      FileDescriptor(String packageName, Descriptor message) throws DescriptorValidationException {
         this.pool = new DescriptorPool(new FileDescriptor[0], true);
         this.proto = DescriptorProtos.FileDescriptorProto.newBuilder().setName(message.getFullName() + ".placeholder.proto").setPackage(packageName).addMessageType(message.toProto()).build();
         this.dependencies = new FileDescriptor[0];
         this.publicDependencies = new FileDescriptor[0];
         this.messageTypes = new Descriptor[]{message};
         this.enumTypes = Descriptors.EMPTY_ENUM_DESCRIPTORS;
         this.services = Descriptors.EMPTY_SERVICE_DESCRIPTORS;
         this.extensions = Descriptors.EMPTY_FIELD_DESCRIPTORS;
         this.pool.addPackage(packageName, this);
         this.pool.addSymbol(message);
      }

      private void crossLink() throws DescriptorValidationException {
         for(Descriptor messageType : this.messageTypes) {
            messageType.crossLink();
         }

         for(ServiceDescriptor service : this.services) {
            service.crossLink();
         }

         for(FieldDescriptor extension : this.extensions) {
            extension.crossLink();
         }

      }

      private void setProto(final DescriptorProtos.FileDescriptorProto proto) {
         this.proto = proto;

         for(int i = 0; i < this.messageTypes.length; ++i) {
            this.messageTypes[i].setProto(proto.getMessageType(i));
         }

         for(int i = 0; i < this.enumTypes.length; ++i) {
            this.enumTypes[i].setProto(proto.getEnumType(i));
         }

         for(int i = 0; i < this.services.length; ++i) {
            this.services[i].setProto(proto.getService(i));
         }

         for(int i = 0; i < this.extensions.length; ++i) {
            this.extensions[i].setProto(proto.getExtension(i));
         }

      }

      /** @deprecated */
      @Deprecated
      public static enum Syntax {
         UNKNOWN("unknown"),
         PROTO2("proto2"),
         PROTO3("proto3"),
         EDITIONS("editions");

         private final String name;

         private Syntax(String name) {
            this.name = name;
         }
      }

      /** @deprecated */
      @Deprecated
      public interface InternalDescriptorAssigner {
         ExtensionRegistry assignDescriptors(FileDescriptor root);
      }
   }

   public static final class Descriptor extends GenericDescriptor {
      private final int index;
      private DescriptorProtos.DescriptorProto proto;
      private final String fullName;
      private final FileDescriptor file;
      private final Descriptor containingType;
      private final Descriptor[] nestedTypes;
      private final EnumDescriptor[] enumTypes;
      private final FieldDescriptor[] fields;
      private final FieldDescriptor[] fieldsSortedByNumber;
      private final FieldDescriptor[] extensions;
      private final OneofDescriptor[] oneofs;
      private final int realOneofCount;
      private final int[] extensionRangeLowerBounds;
      private final int[] extensionRangeUpperBounds;

      public int getIndex() {
         return this.index;
      }

      public DescriptorProtos.DescriptorProto toProto() {
         return this.proto;
      }

      public String getName() {
         return this.proto.getName();
      }

      public String getFullName() {
         return this.fullName;
      }

      public FileDescriptor getFile() {
         return this.file;
      }

      public Descriptor getContainingType() {
         return this.containingType;
      }

      public DescriptorProtos.MessageOptions getOptions() {
         return this.proto.getOptions();
      }

      public List getFields() {
         return Collections.unmodifiableList(Arrays.asList(this.fields));
      }

      public List getOneofs() {
         return Collections.unmodifiableList(Arrays.asList(this.oneofs));
      }

      public List getRealOneofs() {
         return Collections.unmodifiableList(Arrays.asList(this.oneofs).subList(0, this.realOneofCount));
      }

      public List getExtensions() {
         return Collections.unmodifiableList(Arrays.asList(this.extensions));
      }

      public List getNestedTypes() {
         return Collections.unmodifiableList(Arrays.asList(this.nestedTypes));
      }

      public List getEnumTypes() {
         return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
      }

      public boolean isExtensionNumber(final int number) {
         int index = Arrays.binarySearch(this.extensionRangeLowerBounds, number);
         if (index < 0) {
            index = ~index - 1;
         }

         return index >= 0 && number < this.extensionRangeUpperBounds[index];
      }

      public boolean isReservedNumber(final int number) {
         for(DescriptorProtos.DescriptorProto.ReservedRange range : this.proto.getReservedRangeList()) {
            if (range.getStart() <= number && number < range.getEnd()) {
               return true;
            }
         }

         return false;
      }

      public boolean isReservedName(final String name) {
         Internal.checkNotNull(name);

         for(String reservedName : this.proto.getReservedNameList()) {
            if (reservedName.equals(name)) {
               return true;
            }
         }

         return false;
      }

      public boolean isExtendable() {
         return !this.proto.getExtensionRangeList().isEmpty();
      }

      public FieldDescriptor findFieldByName(final String name) {
         GenericDescriptor result = this.file.pool.findSymbol(this.fullName + '.' + name);
         return result instanceof FieldDescriptor ? (FieldDescriptor)result : null;
      }

      public FieldDescriptor findFieldByNumber(final int number) {
         return (FieldDescriptor)Descriptors.binarySearch(this.fieldsSortedByNumber, this.fieldsSortedByNumber.length, Descriptors.FieldDescriptor.NUMBER_GETTER, number);
      }

      public Descriptor findNestedTypeByName(final String name) {
         GenericDescriptor result = this.file.pool.findSymbol(this.fullName + '.' + name);
         return result instanceof Descriptor ? (Descriptor)result : null;
      }

      public EnumDescriptor findEnumTypeByName(final String name) {
         GenericDescriptor result = this.file.pool.findSymbol(this.fullName + '.' + name);
         return result instanceof EnumDescriptor ? (EnumDescriptor)result : null;
      }

      Descriptor(final String fullname) throws DescriptorValidationException {
         String name = fullname;
         String packageName = "";
         int pos = fullname.lastIndexOf(46);
         if (pos != -1) {
            name = fullname.substring(pos + 1);
            packageName = fullname.substring(0, pos);
         }

         this.index = 0;
         this.proto = DescriptorProtos.DescriptorProto.newBuilder().setName(name).addExtensionRange(DescriptorProtos.DescriptorProto.ExtensionRange.newBuilder().setStart(1).setEnd(536870912).build()).build();
         this.fullName = fullname;
         this.containingType = null;
         this.nestedTypes = Descriptors.EMPTY_DESCRIPTORS;
         this.enumTypes = Descriptors.EMPTY_ENUM_DESCRIPTORS;
         this.fields = Descriptors.EMPTY_FIELD_DESCRIPTORS;
         this.fieldsSortedByNumber = Descriptors.EMPTY_FIELD_DESCRIPTORS;
         this.extensions = Descriptors.EMPTY_FIELD_DESCRIPTORS;
         this.oneofs = Descriptors.EMPTY_ONEOF_DESCRIPTORS;
         this.realOneofCount = 0;
         this.file = new FileDescriptor(packageName, this);
         this.extensionRangeLowerBounds = new int[]{1};
         this.extensionRangeUpperBounds = new int[]{536870912};
      }

      private Descriptor(final DescriptorProtos.DescriptorProto proto, final FileDescriptor file, final Descriptor parent, final int index) throws DescriptorValidationException {
         this.index = index;
         this.proto = proto;
         this.fullName = Descriptors.computeFullName(file, parent, proto.getName());
         this.file = file;
         this.containingType = parent;
         this.oneofs = proto.getOneofDeclCount() > 0 ? new OneofDescriptor[proto.getOneofDeclCount()] : Descriptors.EMPTY_ONEOF_DESCRIPTORS;

         for(int i = 0; i < proto.getOneofDeclCount(); ++i) {
            this.oneofs[i] = new OneofDescriptor(proto.getOneofDecl(i), file, this, i);
         }

         this.nestedTypes = proto.getNestedTypeCount() > 0 ? new Descriptor[proto.getNestedTypeCount()] : Descriptors.EMPTY_DESCRIPTORS;

         for(int i = 0; i < proto.getNestedTypeCount(); ++i) {
            this.nestedTypes[i] = new Descriptor(proto.getNestedType(i), file, this, i);
         }

         this.enumTypes = proto.getEnumTypeCount() > 0 ? new EnumDescriptor[proto.getEnumTypeCount()] : Descriptors.EMPTY_ENUM_DESCRIPTORS;

         for(int i = 0; i < proto.getEnumTypeCount(); ++i) {
            this.enumTypes[i] = new EnumDescriptor(proto.getEnumType(i), file, this, i);
         }

         this.fields = proto.getFieldCount() > 0 ? new FieldDescriptor[proto.getFieldCount()] : Descriptors.EMPTY_FIELD_DESCRIPTORS;

         for(int i = 0; i < proto.getFieldCount(); ++i) {
            this.fields[i] = new FieldDescriptor(proto.getField(i), file, this, i, false);
         }

         this.fieldsSortedByNumber = proto.getFieldCount() > 0 ? (FieldDescriptor[])this.fields.clone() : Descriptors.EMPTY_FIELD_DESCRIPTORS;
         this.extensions = proto.getExtensionCount() > 0 ? new FieldDescriptor[proto.getExtensionCount()] : Descriptors.EMPTY_FIELD_DESCRIPTORS;

         for(int i = 0; i < proto.getExtensionCount(); ++i) {
            this.extensions[i] = new FieldDescriptor(proto.getExtension(i), file, this, i, true);
         }

         for(int i = 0; i < proto.getOneofDeclCount(); ++i) {
            this.oneofs[i].fields = new FieldDescriptor[this.oneofs[i].getFieldCount()];
            this.oneofs[i].fieldCount = 0;
         }

         for(int i = 0; i < proto.getFieldCount(); ++i) {
            OneofDescriptor oneofDescriptor = this.fields[i].getContainingOneof();
            if (oneofDescriptor != null) {
               oneofDescriptor.fields[oneofDescriptor.fieldCount++] = this.fields[i];
            }
         }

         int syntheticOneofCount = 0;

         for(OneofDescriptor oneof : this.oneofs) {
            if (oneof.isSynthetic()) {
               ++syntheticOneofCount;
            } else if (syntheticOneofCount > 0) {
               throw new DescriptorValidationException(this, "Synthetic oneofs must come last.");
            }
         }

         this.realOneofCount = this.oneofs.length - syntheticOneofCount;
         file.pool.addSymbol(this);
         if (proto.getExtensionRangeCount() > 0) {
            this.extensionRangeLowerBounds = new int[proto.getExtensionRangeCount()];
            this.extensionRangeUpperBounds = new int[proto.getExtensionRangeCount()];
            int i = 0;

            for(DescriptorProtos.DescriptorProto.ExtensionRange range : proto.getExtensionRangeList()) {
               this.extensionRangeLowerBounds[i] = range.getStart();
               this.extensionRangeUpperBounds[i] = range.getEnd();
               ++i;
            }

            Arrays.sort(this.extensionRangeLowerBounds);
            Arrays.sort(this.extensionRangeUpperBounds);
         } else {
            this.extensionRangeLowerBounds = Descriptors.EMPTY_INT_ARRAY;
            this.extensionRangeUpperBounds = Descriptors.EMPTY_INT_ARRAY;
         }

      }

      private void crossLink() throws DescriptorValidationException {
         for(Descriptor nestedType : this.nestedTypes) {
            nestedType.crossLink();
         }

         for(FieldDescriptor field : this.fields) {
            field.crossLink();
         }

         Arrays.sort(this.fieldsSortedByNumber);
         this.validateNoDuplicateFieldNumbers();

         for(FieldDescriptor extension : this.extensions) {
            extension.crossLink();
         }

      }

      private void validateNoDuplicateFieldNumbers() throws DescriptorValidationException {
         for(int i = 0; i + 1 < this.fieldsSortedByNumber.length; ++i) {
            FieldDescriptor old = this.fieldsSortedByNumber[i];
            FieldDescriptor field = this.fieldsSortedByNumber[i + 1];
            if (old.getNumber() == field.getNumber()) {
               throw new DescriptorValidationException(field, "Field number " + field.getNumber() + " has already been used in \"" + field.getContainingType().getFullName() + "\" by field \"" + old.getName() + "\".");
            }
         }

      }

      private void setProto(final DescriptorProtos.DescriptorProto proto) {
         this.proto = proto;

         for(int i = 0; i < this.nestedTypes.length; ++i) {
            this.nestedTypes[i].setProto(proto.getNestedType(i));
         }

         for(int i = 0; i < this.oneofs.length; ++i) {
            this.oneofs[i].setProto(proto.getOneofDecl(i));
         }

         for(int i = 0; i < this.enumTypes.length; ++i) {
            this.enumTypes[i].setProto(proto.getEnumType(i));
         }

         for(int i = 0; i < this.fields.length; ++i) {
            this.fields[i].setProto(proto.getField(i));
         }

         for(int i = 0; i < this.extensions.length; ++i) {
            this.extensions[i].setProto(proto.getExtension(i));
         }

      }
   }

   public static final class FieldDescriptor extends GenericDescriptor implements Comparable, FieldSet.FieldDescriptorLite {
      private static final NumberGetter NUMBER_GETTER = new NumberGetter() {
         public int getNumber(FieldDescriptor fieldDescriptor) {
            return fieldDescriptor.getNumber();
         }
      };
      private static final WireFormat.FieldType[] table = WireFormat.FieldType.values();
      private final int index;
      private DescriptorProtos.FieldDescriptorProto proto;
      private final String fullName;
      private String jsonName;
      private final FileDescriptor file;
      private final Descriptor extensionScope;
      private final boolean isProto3Optional;
      private Type type;
      private Descriptor containingType;
      private Descriptor messageType;
      private OneofDescriptor containingOneof;
      private EnumDescriptor enumType;
      private Object defaultValue;

      public int getIndex() {
         return this.index;
      }

      public DescriptorProtos.FieldDescriptorProto toProto() {
         return this.proto;
      }

      public String getName() {
         return this.proto.getName();
      }

      public int getNumber() {
         return this.proto.getNumber();
      }

      public String getFullName() {
         return this.fullName;
      }

      public String getJsonName() {
         String result = this.jsonName;
         if (result != null) {
            return result;
         } else {
            return this.proto.hasJsonName() ? (this.jsonName = this.proto.getJsonName()) : (this.jsonName = fieldNameToJsonName(this.proto.getName()));
         }
      }

      public JavaType getJavaType() {
         return this.type.getJavaType();
      }

      public WireFormat.JavaType getLiteJavaType() {
         return this.getLiteType().getJavaType();
      }

      public FileDescriptor getFile() {
         return this.file;
      }

      public Type getType() {
         return this.type;
      }

      public WireFormat.FieldType getLiteType() {
         return table[this.type.ordinal()];
      }

      public boolean needsUtf8Check() {
         if (this.type != Descriptors.FieldDescriptor.Type.STRING) {
            return false;
         } else if (this.getContainingType().getOptions().getMapEntry()) {
            return true;
         } else {
            return this.getFile().getSyntax() == Descriptors.FileDescriptor.Syntax.PROTO3 ? true : this.getFile().getOptions().getJavaStringCheckUtf8();
         }
      }

      public boolean isMapField() {
         return this.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && this.isRepeated() && this.getMessageType().getOptions().getMapEntry();
      }

      public boolean isRequired() {
         return this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_REQUIRED;
      }

      public boolean isOptional() {
         return this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_OPTIONAL;
      }

      public boolean isRepeated() {
         return this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_REPEATED;
      }

      public boolean isPacked() {
         if (!this.isPackable()) {
            return false;
         } else if (this.getFile().getSyntax() == Descriptors.FileDescriptor.Syntax.PROTO2) {
            return this.getOptions().getPacked();
         } else {
            return !this.getOptions().hasPacked() || this.getOptions().getPacked();
         }
      }

      public boolean isPackable() {
         return this.isRepeated() && this.getLiteType().isPackable();
      }

      public boolean hasDefaultValue() {
         return this.proto.hasDefaultValue();
      }

      public Object getDefaultValue() {
         if (this.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            throw new UnsupportedOperationException("FieldDescriptor.getDefaultValue() called on an embedded message field.");
         } else {
            return this.defaultValue;
         }
      }

      public DescriptorProtos.FieldOptions getOptions() {
         return this.proto.getOptions();
      }

      public boolean isExtension() {
         return this.proto.hasExtendee();
      }

      public Descriptor getContainingType() {
         return this.containingType;
      }

      public OneofDescriptor getContainingOneof() {
         return this.containingOneof;
      }

      public OneofDescriptor getRealContainingOneof() {
         return this.containingOneof != null && !this.containingOneof.isSynthetic() ? this.containingOneof : null;
      }

      /** @deprecated */
      @Deprecated
      public boolean hasOptionalKeyword() {
         return this.isProto3Optional || this.file.getSyntax() == Descriptors.FileDescriptor.Syntax.PROTO2 && this.isOptional() && this.getContainingOneof() == null;
      }

      public boolean hasPresence() {
         if (this.isRepeated()) {
            return false;
         } else {
            return this.getType() == Descriptors.FieldDescriptor.Type.MESSAGE || this.getType() == Descriptors.FieldDescriptor.Type.GROUP || this.getContainingOneof() != null || this.file.getSyntax() == Descriptors.FileDescriptor.Syntax.PROTO2;
         }
      }

      public Descriptor getExtensionScope() {
         if (!this.isExtension()) {
            throw new UnsupportedOperationException(String.format("This field is not an extension. (%s)", this.fullName));
         } else {
            return this.extensionScope;
         }
      }

      public Descriptor getMessageType() {
         if (this.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            throw new UnsupportedOperationException(String.format("This field is not of message type. (%s)", this.fullName));
         } else {
            return this.messageType;
         }
      }

      public EnumDescriptor getEnumType() {
         if (this.getJavaType() != Descriptors.FieldDescriptor.JavaType.ENUM) {
            throw new UnsupportedOperationException(String.format("This field is not of enum type. (%s)", this.fullName));
         } else {
            return this.enumType;
         }
      }

      public boolean legacyEnumFieldTreatedAsClosed() {
         return this.getType() == Descriptors.FieldDescriptor.Type.ENUM && this.getFile().getSyntax() == Descriptors.FileDescriptor.Syntax.PROTO2;
      }

      public int compareTo(final FieldDescriptor other) {
         if (other.containingType != this.containingType) {
            throw new IllegalArgumentException("FieldDescriptors can only be compared to other FieldDescriptors for fields of the same message type.");
         } else {
            return this.getNumber() - other.getNumber();
         }
      }

      public String toString() {
         return this.getFullName();
      }

      private static String fieldNameToJsonName(String name) {
         int length = name.length();
         StringBuilder result = new StringBuilder(length);
         boolean isNextUpperCase = false;

         for(int i = 0; i < length; ++i) {
            char ch = name.charAt(i);
            if (ch == '_') {
               isNextUpperCase = true;
            } else if (isNextUpperCase) {
               if ('a' <= ch && ch <= 'z') {
                  ch = (char)(ch - 97 + 65);
               }

               result.append(ch);
               isNextUpperCase = false;
            } else {
               result.append(ch);
            }
         }

         return result.toString();
      }

      private FieldDescriptor(final DescriptorProtos.FieldDescriptorProto proto, final FileDescriptor file, final Descriptor parent, final int index, final boolean isExtension) throws DescriptorValidationException {
         this.index = index;
         this.proto = proto;
         this.fullName = Descriptors.computeFullName(file, parent, proto.getName());
         this.file = file;
         if (proto.hasType()) {
            this.type = Descriptors.FieldDescriptor.Type.valueOf(proto.getType());
         }

         this.isProto3Optional = proto.getProto3Optional();
         if (this.getNumber() <= 0) {
            throw new DescriptorValidationException(this, "Field numbers must be positive integers.");
         } else {
            if (isExtension) {
               if (!proto.hasExtendee()) {
                  throw new DescriptorValidationException(this, "FieldDescriptorProto.extendee not set for extension field.");
               }

               this.containingType = null;
               if (parent != null) {
                  this.extensionScope = parent;
               } else {
                  this.extensionScope = null;
               }

               if (proto.hasOneofIndex()) {
                  throw new DescriptorValidationException(this, "FieldDescriptorProto.oneof_index set for extension field.");
               }

               this.containingOneof = null;
            } else {
               if (proto.hasExtendee()) {
                  throw new DescriptorValidationException(this, "FieldDescriptorProto.extendee set for non-extension field.");
               }

               this.containingType = parent;
               if (proto.hasOneofIndex()) {
                  if (proto.getOneofIndex() < 0 || proto.getOneofIndex() >= parent.toProto().getOneofDeclCount()) {
                     throw new DescriptorValidationException(this, "FieldDescriptorProto.oneof_index is out of range for type " + parent.getName());
                  }

                  this.containingOneof = (OneofDescriptor)parent.getOneofs().get(proto.getOneofIndex());
                  this.containingOneof.fieldCount++;
               } else {
                  this.containingOneof = null;
               }

               this.extensionScope = null;
            }

            file.pool.addSymbol(this);
         }
      }

      private void crossLink() throws DescriptorValidationException {
         if (this.proto.hasExtendee()) {
            GenericDescriptor extendee = this.file.pool.lookupSymbol(this.proto.getExtendee(), this, Descriptors.DescriptorPool.SearchFilter.TYPES_ONLY);
            if (!(extendee instanceof Descriptor)) {
               throw new DescriptorValidationException(this, '"' + this.proto.getExtendee() + "\" is not a message type.");
            }

            this.containingType = (Descriptor)extendee;
            if (!this.getContainingType().isExtensionNumber(this.getNumber())) {
               throw new DescriptorValidationException(this, '"' + this.getContainingType().getFullName() + "\" does not declare " + this.getNumber() + " as an extension number.");
            }
         }

         if (this.proto.hasTypeName()) {
            GenericDescriptor typeDescriptor = this.file.pool.lookupSymbol(this.proto.getTypeName(), this, Descriptors.DescriptorPool.SearchFilter.TYPES_ONLY);
            if (!this.proto.hasType()) {
               if (typeDescriptor instanceof Descriptor) {
                  this.type = Descriptors.FieldDescriptor.Type.MESSAGE;
               } else {
                  if (!(typeDescriptor instanceof EnumDescriptor)) {
                     throw new DescriptorValidationException(this, '"' + this.proto.getTypeName() + "\" is not a type.");
                  }

                  this.type = Descriptors.FieldDescriptor.Type.ENUM;
               }
            }

            if (this.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
               if (!(typeDescriptor instanceof Descriptor)) {
                  throw new DescriptorValidationException(this, '"' + this.proto.getTypeName() + "\" is not a message type.");
               }

               this.messageType = (Descriptor)typeDescriptor;
               if (this.proto.hasDefaultValue()) {
                  throw new DescriptorValidationException(this, "Messages can't have default values.");
               }
            } else {
               if (this.getJavaType() != Descriptors.FieldDescriptor.JavaType.ENUM) {
                  throw new DescriptorValidationException(this, "Field with primitive type has type_name.");
               }

               if (!(typeDescriptor instanceof EnumDescriptor)) {
                  throw new DescriptorValidationException(this, '"' + this.proto.getTypeName() + "\" is not an enum type.");
               }

               this.enumType = (EnumDescriptor)typeDescriptor;
            }
         } else if (this.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE || this.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
            throw new DescriptorValidationException(this, "Field with message or enum type missing type_name.");
         }

         if (this.proto.getOptions().getPacked() && !this.isPackable()) {
            throw new DescriptorValidationException(this, "[packed = true] can only be specified for repeated primitive fields.");
         } else {
            if (this.proto.hasDefaultValue()) {
               if (this.isRepeated()) {
                  throw new DescriptorValidationException(this, "Repeated fields cannot have default values.");
               }

               try {
                  switch (this.getType()) {
                     case INT32:
                     case SINT32:
                     case SFIXED32:
                        this.defaultValue = TextFormat.parseInt32(this.proto.getDefaultValue());
                        break;
                     case UINT32:
                     case FIXED32:
                        this.defaultValue = TextFormat.parseUInt32(this.proto.getDefaultValue());
                        break;
                     case INT64:
                     case SINT64:
                     case SFIXED64:
                        this.defaultValue = TextFormat.parseInt64(this.proto.getDefaultValue());
                        break;
                     case UINT64:
                     case FIXED64:
                        this.defaultValue = TextFormat.parseUInt64(this.proto.getDefaultValue());
                        break;
                     case FLOAT:
                        if (this.proto.getDefaultValue().equals("inf")) {
                           this.defaultValue = Float.POSITIVE_INFINITY;
                        } else if (this.proto.getDefaultValue().equals("-inf")) {
                           this.defaultValue = Float.NEGATIVE_INFINITY;
                        } else if (this.proto.getDefaultValue().equals("nan")) {
                           this.defaultValue = Float.NaN;
                        } else {
                           this.defaultValue = Float.valueOf(this.proto.getDefaultValue());
                        }
                        break;
                     case DOUBLE:
                        if (this.proto.getDefaultValue().equals("inf")) {
                           this.defaultValue = Double.POSITIVE_INFINITY;
                        } else if (this.proto.getDefaultValue().equals("-inf")) {
                           this.defaultValue = Double.NEGATIVE_INFINITY;
                        } else if (this.proto.getDefaultValue().equals("nan")) {
                           this.defaultValue = Double.NaN;
                        } else {
                           this.defaultValue = Double.valueOf(this.proto.getDefaultValue());
                        }
                        break;
                     case BOOL:
                        this.defaultValue = Boolean.valueOf(this.proto.getDefaultValue());
                        break;
                     case STRING:
                        this.defaultValue = this.proto.getDefaultValue();
                        break;
                     case BYTES:
                        try {
                           this.defaultValue = TextFormat.unescapeBytes(this.proto.getDefaultValue());
                           break;
                        } catch (TextFormat.InvalidEscapeSequenceException e) {
                           throw new DescriptorValidationException(this, "Couldn't parse default value: " + e.getMessage(), e);
                        }
                     case ENUM:
                        this.defaultValue = this.enumType.findValueByName(this.proto.getDefaultValue());
                        if (this.defaultValue == null) {
                           throw new DescriptorValidationException(this, "Unknown enum default value: \"" + this.proto.getDefaultValue() + '"');
                        }
                        break;
                     case MESSAGE:
                     case GROUP:
                        throw new DescriptorValidationException(this, "Message type had default value.");
                  }
               } catch (NumberFormatException e) {
                  throw new DescriptorValidationException(this, "Could not parse default value: \"" + this.proto.getDefaultValue() + '"', e);
               }
            } else if (this.isRepeated()) {
               this.defaultValue = Collections.emptyList();
            } else {
               switch (this.getJavaType()) {
                  case ENUM:
                     this.defaultValue = this.enumType.getValues().get(0);
                     break;
                  case MESSAGE:
                     this.defaultValue = null;
                     break;
                  default:
                     this.defaultValue = this.getJavaType().defaultDefault;
               }
            }

            if (this.containingType != null && this.containingType.getOptions().getMessageSetWireFormat()) {
               if (!this.isExtension()) {
                  throw new DescriptorValidationException(this, "MessageSets cannot have fields, only extensions.");
               }

               if (!this.isOptional() || this.getType() != Descriptors.FieldDescriptor.Type.MESSAGE) {
                  throw new DescriptorValidationException(this, "Extensions of MessageSets must be optional messages.");
               }
            }

         }
      }

      private void setProto(final DescriptorProtos.FieldDescriptorProto proto) {
         this.proto = proto;
      }

      public MessageLite.Builder internalMergeFrom(MessageLite.Builder to, MessageLite from) {
         return ((Message.Builder)to).mergeFrom((Message)from);
      }

      static {
         if (Descriptors.FieldDescriptor.Type.types.length != DescriptorProtos.FieldDescriptorProto.Type.values().length) {
            throw new RuntimeException("descriptor.proto has a new declared type but Descriptors.java wasn't updated.");
         }
      }

      public static enum Type {
         DOUBLE(Descriptors.FieldDescriptor.JavaType.DOUBLE),
         FLOAT(Descriptors.FieldDescriptor.JavaType.FLOAT),
         INT64(Descriptors.FieldDescriptor.JavaType.LONG),
         UINT64(Descriptors.FieldDescriptor.JavaType.LONG),
         INT32(Descriptors.FieldDescriptor.JavaType.INT),
         FIXED64(Descriptors.FieldDescriptor.JavaType.LONG),
         FIXED32(Descriptors.FieldDescriptor.JavaType.INT),
         BOOL(Descriptors.FieldDescriptor.JavaType.BOOLEAN),
         STRING(Descriptors.FieldDescriptor.JavaType.STRING),
         GROUP(Descriptors.FieldDescriptor.JavaType.MESSAGE),
         MESSAGE(Descriptors.FieldDescriptor.JavaType.MESSAGE),
         BYTES(Descriptors.FieldDescriptor.JavaType.BYTE_STRING),
         UINT32(Descriptors.FieldDescriptor.JavaType.INT),
         ENUM(Descriptors.FieldDescriptor.JavaType.ENUM),
         SFIXED32(Descriptors.FieldDescriptor.JavaType.INT),
         SFIXED64(Descriptors.FieldDescriptor.JavaType.LONG),
         SINT32(Descriptors.FieldDescriptor.JavaType.INT),
         SINT64(Descriptors.FieldDescriptor.JavaType.LONG);

         private static final Type[] types = values();
         private final JavaType javaType;

         private Type(JavaType javaType) {
            this.javaType = javaType;
         }

         public DescriptorProtos.FieldDescriptorProto.Type toProto() {
            return DescriptorProtos.FieldDescriptorProto.Type.forNumber(this.ordinal() + 1);
         }

         public JavaType getJavaType() {
            return this.javaType;
         }

         public static Type valueOf(final DescriptorProtos.FieldDescriptorProto.Type type) {
            return types[type.getNumber() - 1];
         }
      }

      public static enum JavaType {
         INT(0),
         LONG(0L),
         FLOAT(0.0F),
         DOUBLE((double)0.0F),
         BOOLEAN(false),
         STRING(""),
         BYTE_STRING(ByteString.EMPTY),
         ENUM((Object)null),
         MESSAGE((Object)null);

         private final Object defaultDefault;

         private JavaType(final Object defaultDefault) {
            this.defaultDefault = defaultDefault;
         }
      }
   }

   public static final class EnumDescriptor extends GenericDescriptor implements Internal.EnumLiteMap {
      private final int index;
      private DescriptorProtos.EnumDescriptorProto proto;
      private final String fullName;
      private final FileDescriptor file;
      private final Descriptor containingType;
      private final EnumValueDescriptor[] values;
      private final EnumValueDescriptor[] valuesSortedByNumber;
      private final int distinctNumbers;
      private Map unknownValues;
      private ReferenceQueue cleanupQueue;

      public int getIndex() {
         return this.index;
      }

      public DescriptorProtos.EnumDescriptorProto toProto() {
         return this.proto;
      }

      public String getName() {
         return this.proto.getName();
      }

      public String getFullName() {
         return this.fullName;
      }

      public FileDescriptor getFile() {
         return this.file;
      }

      public boolean isClosed() {
         return this.getFile().getSyntax() != Descriptors.FileDescriptor.Syntax.PROTO3;
      }

      public Descriptor getContainingType() {
         return this.containingType;
      }

      public DescriptorProtos.EnumOptions getOptions() {
         return this.proto.getOptions();
      }

      public List getValues() {
         return Collections.unmodifiableList(Arrays.asList(this.values));
      }

      public boolean isReservedNumber(final int number) {
         for(DescriptorProtos.EnumDescriptorProto.EnumReservedRange range : this.proto.getReservedRangeList()) {
            if (range.getStart() <= number && number <= range.getEnd()) {
               return true;
            }
         }

         return false;
      }

      public boolean isReservedName(final String name) {
         Internal.checkNotNull(name);

         for(String reservedName : this.proto.getReservedNameList()) {
            if (reservedName.equals(name)) {
               return true;
            }
         }

         return false;
      }

      public EnumValueDescriptor findValueByName(final String name) {
         GenericDescriptor result = this.file.pool.findSymbol(this.fullName + '.' + name);
         return result instanceof EnumValueDescriptor ? (EnumValueDescriptor)result : null;
      }

      public EnumValueDescriptor findValueByNumber(final int number) {
         return (EnumValueDescriptor)Descriptors.binarySearch(this.valuesSortedByNumber, this.distinctNumbers, Descriptors.EnumValueDescriptor.NUMBER_GETTER, number);
      }

      public EnumValueDescriptor findValueByNumberCreatingIfUnknown(final int number) {
         EnumValueDescriptor result = this.findValueByNumber(number);
         if (result != null) {
            return result;
         } else {
            synchronized(this) {
               if (this.cleanupQueue == null) {
                  this.cleanupQueue = new ReferenceQueue();
                  this.unknownValues = new HashMap();
               } else {
                  while(true) {
                     UnknownEnumValueReference toClean = (UnknownEnumValueReference)this.cleanupQueue.poll();
                     if (toClean == null) {
                        break;
                     }

                     this.unknownValues.remove(toClean.number);
                  }
               }

               WeakReference<EnumValueDescriptor> reference = (WeakReference)this.unknownValues.get(number);
               result = reference == null ? null : (EnumValueDescriptor)reference.get();
               if (result == null) {
                  result = new EnumValueDescriptor(this, number);
                  this.unknownValues.put(number, new UnknownEnumValueReference(number, result));
               }

               return result;
            }
         }
      }

      int getUnknownEnumValueDescriptorCount() {
         return this.unknownValues.size();
      }

      private EnumDescriptor(final DescriptorProtos.EnumDescriptorProto proto, final FileDescriptor file, final Descriptor parent, final int index) throws DescriptorValidationException {
         this.unknownValues = null;
         this.cleanupQueue = null;
         this.index = index;
         this.proto = proto;
         this.fullName = Descriptors.computeFullName(file, parent, proto.getName());
         this.file = file;
         this.containingType = parent;
         if (proto.getValueCount() == 0) {
            throw new DescriptorValidationException(this, "Enums must contain at least one value.");
         } else {
            this.values = new EnumValueDescriptor[proto.getValueCount()];

            for(int i = 0; i < proto.getValueCount(); ++i) {
               this.values[i] = new EnumValueDescriptor(proto.getValue(i), file, this, i);
            }

            this.valuesSortedByNumber = (EnumValueDescriptor[])this.values.clone();
            Arrays.sort(this.valuesSortedByNumber, Descriptors.EnumValueDescriptor.BY_NUMBER);
            int j = 0;

            for(int i = 1; i < proto.getValueCount(); ++i) {
               EnumValueDescriptor oldValue = this.valuesSortedByNumber[j];
               EnumValueDescriptor newValue = this.valuesSortedByNumber[i];
               if (oldValue.getNumber() != newValue.getNumber()) {
                  ++j;
                  this.valuesSortedByNumber[j] = newValue;
               }
            }

            this.distinctNumbers = j + 1;
            Arrays.fill(this.valuesSortedByNumber, this.distinctNumbers, proto.getValueCount(), (Object)null);
            file.pool.addSymbol(this);
         }
      }

      private void setProto(final DescriptorProtos.EnumDescriptorProto proto) {
         this.proto = proto;

         for(int i = 0; i < this.values.length; ++i) {
            this.values[i].setProto(proto.getValue(i));
         }

      }

      private static class UnknownEnumValueReference extends WeakReference {
         private final int number;

         private UnknownEnumValueReference(int number, EnumValueDescriptor descriptor) {
            super(descriptor);
            this.number = number;
         }
      }
   }

   public static final class EnumValueDescriptor extends GenericDescriptor implements Internal.EnumLite {
      static final Comparator BY_NUMBER = new Comparator() {
         public int compare(EnumValueDescriptor o1, EnumValueDescriptor o2) {
            return Integer.valueOf(o1.getNumber()).compareTo(o2.getNumber());
         }
      };
      static final NumberGetter NUMBER_GETTER = new NumberGetter() {
         public int getNumber(EnumValueDescriptor enumValueDescriptor) {
            return enumValueDescriptor.getNumber();
         }
      };
      private final int index;
      private DescriptorProtos.EnumValueDescriptorProto proto;
      private final String fullName;
      private final EnumDescriptor type;

      public int getIndex() {
         return this.index;
      }

      public DescriptorProtos.EnumValueDescriptorProto toProto() {
         return this.proto;
      }

      public String getName() {
         return this.proto.getName();
      }

      public int getNumber() {
         return this.proto.getNumber();
      }

      public String toString() {
         return this.proto.getName();
      }

      public String getFullName() {
         return this.fullName;
      }

      public FileDescriptor getFile() {
         return this.type.file;
      }

      public EnumDescriptor getType() {
         return this.type;
      }

      public DescriptorProtos.EnumValueOptions getOptions() {
         return this.proto.getOptions();
      }

      private EnumValueDescriptor(final DescriptorProtos.EnumValueDescriptorProto proto, final FileDescriptor file, final EnumDescriptor parent, final int index) throws DescriptorValidationException {
         this.index = index;
         this.proto = proto;
         this.type = parent;
         this.fullName = parent.getFullName() + '.' + proto.getName();
         file.pool.addSymbol(this);
      }

      private EnumValueDescriptor(final EnumDescriptor parent, final Integer number) {
         String name = "UNKNOWN_ENUM_VALUE_" + parent.getName() + "_" + number;
         DescriptorProtos.EnumValueDescriptorProto proto = DescriptorProtos.EnumValueDescriptorProto.newBuilder().setName(name).setNumber(number).build();
         this.index = -1;
         this.proto = proto;
         this.type = parent;
         this.fullName = parent.getFullName() + '.' + proto.getName();
      }

      private void setProto(final DescriptorProtos.EnumValueDescriptorProto proto) {
         this.proto = proto;
      }
   }

   public static final class ServiceDescriptor extends GenericDescriptor {
      private final int index;
      private DescriptorProtos.ServiceDescriptorProto proto;
      private final String fullName;
      private final FileDescriptor file;
      private MethodDescriptor[] methods;

      public int getIndex() {
         return this.index;
      }

      public DescriptorProtos.ServiceDescriptorProto toProto() {
         return this.proto;
      }

      public String getName() {
         return this.proto.getName();
      }

      public String getFullName() {
         return this.fullName;
      }

      public FileDescriptor getFile() {
         return this.file;
      }

      public DescriptorProtos.ServiceOptions getOptions() {
         return this.proto.getOptions();
      }

      public List getMethods() {
         return Collections.unmodifiableList(Arrays.asList(this.methods));
      }

      public MethodDescriptor findMethodByName(final String name) {
         GenericDescriptor result = this.file.pool.findSymbol(this.fullName + '.' + name);
         return result instanceof MethodDescriptor ? (MethodDescriptor)result : null;
      }

      private ServiceDescriptor(final DescriptorProtos.ServiceDescriptorProto proto, final FileDescriptor file, final int index) throws DescriptorValidationException {
         this.index = index;
         this.proto = proto;
         this.fullName = Descriptors.computeFullName(file, (Descriptor)null, proto.getName());
         this.file = file;
         this.methods = new MethodDescriptor[proto.getMethodCount()];

         for(int i = 0; i < proto.getMethodCount(); ++i) {
            this.methods[i] = new MethodDescriptor(proto.getMethod(i), file, this, i);
         }

         file.pool.addSymbol(this);
      }

      private void crossLink() throws DescriptorValidationException {
         for(MethodDescriptor method : this.methods) {
            method.crossLink();
         }

      }

      private void setProto(final DescriptorProtos.ServiceDescriptorProto proto) {
         this.proto = proto;

         for(int i = 0; i < this.methods.length; ++i) {
            this.methods[i].setProto(proto.getMethod(i));
         }

      }
   }

   public static final class MethodDescriptor extends GenericDescriptor {
      private final int index;
      private DescriptorProtos.MethodDescriptorProto proto;
      private final String fullName;
      private final FileDescriptor file;
      private final ServiceDescriptor service;
      private Descriptor inputType;
      private Descriptor outputType;

      public int getIndex() {
         return this.index;
      }

      public DescriptorProtos.MethodDescriptorProto toProto() {
         return this.proto;
      }

      public String getName() {
         return this.proto.getName();
      }

      public String getFullName() {
         return this.fullName;
      }

      public FileDescriptor getFile() {
         return this.file;
      }

      public ServiceDescriptor getService() {
         return this.service;
      }

      public Descriptor getInputType() {
         return this.inputType;
      }

      public Descriptor getOutputType() {
         return this.outputType;
      }

      public boolean isClientStreaming() {
         return this.proto.getClientStreaming();
      }

      public boolean isServerStreaming() {
         return this.proto.getServerStreaming();
      }

      public DescriptorProtos.MethodOptions getOptions() {
         return this.proto.getOptions();
      }

      private MethodDescriptor(final DescriptorProtos.MethodDescriptorProto proto, final FileDescriptor file, final ServiceDescriptor parent, final int index) throws DescriptorValidationException {
         this.index = index;
         this.proto = proto;
         this.file = file;
         this.service = parent;
         this.fullName = parent.getFullName() + '.' + proto.getName();
         file.pool.addSymbol(this);
      }

      private void crossLink() throws DescriptorValidationException {
         GenericDescriptor input = this.getFile().pool.lookupSymbol(this.proto.getInputType(), this, Descriptors.DescriptorPool.SearchFilter.TYPES_ONLY);
         if (!(input instanceof Descriptor)) {
            throw new DescriptorValidationException(this, '"' + this.proto.getInputType() + "\" is not a message type.");
         } else {
            this.inputType = (Descriptor)input;
            GenericDescriptor output = this.getFile().pool.lookupSymbol(this.proto.getOutputType(), this, Descriptors.DescriptorPool.SearchFilter.TYPES_ONLY);
            if (!(output instanceof Descriptor)) {
               throw new DescriptorValidationException(this, '"' + this.proto.getOutputType() + "\" is not a message type.");
            } else {
               this.outputType = (Descriptor)output;
            }
         }
      }

      private void setProto(final DescriptorProtos.MethodDescriptorProto proto) {
         this.proto = proto;
      }
   }

   public abstract static class GenericDescriptor {
      private GenericDescriptor() {
      }

      public abstract Message toProto();

      public abstract String getName();

      public abstract String getFullName();

      public abstract FileDescriptor getFile();
   }

   public static class DescriptorValidationException extends Exception {
      private static final long serialVersionUID = 5750205775490483148L;
      private final String name;
      private final Message proto;
      private final String description;

      public String getProblemSymbolName() {
         return this.name;
      }

      public Message getProblemProto() {
         return this.proto;
      }

      public String getDescription() {
         return this.description;
      }

      private DescriptorValidationException(final GenericDescriptor problemDescriptor, final String description) {
         super(problemDescriptor.getFullName() + ": " + description);
         this.name = problemDescriptor.getFullName();
         this.proto = problemDescriptor.toProto();
         this.description = description;
      }

      private DescriptorValidationException(final GenericDescriptor problemDescriptor, final String description, final Throwable cause) {
         this(problemDescriptor, description);
         this.initCause(cause);
      }

      private DescriptorValidationException(final FileDescriptor problemDescriptor, final String description) {
         super(problemDescriptor.getName() + ": " + description);
         this.name = problemDescriptor.getName();
         this.proto = problemDescriptor.toProto();
         this.description = description;
      }
   }

   private static final class DescriptorPool {
      private final Set dependencies;
      private final boolean allowUnknownDependencies;
      private final Map descriptorsByName = new HashMap();

      DescriptorPool(final FileDescriptor[] dependencies, boolean allowUnknownDependencies) {
         this.dependencies = Collections.newSetFromMap(new IdentityHashMap(dependencies.length));
         this.allowUnknownDependencies = allowUnknownDependencies;

         for(FileDescriptor dependency : dependencies) {
            this.dependencies.add(dependency);
            this.importPublicDependencies(dependency);
         }

         for(FileDescriptor dependency : this.dependencies) {
            try {
               this.addPackage(dependency.getPackage(), dependency);
            } catch (DescriptorValidationException e) {
               throw new AssertionError(e);
            }
         }

      }

      private void importPublicDependencies(final FileDescriptor file) {
         for(FileDescriptor dependency : file.getPublicDependencies()) {
            if (this.dependencies.add(dependency)) {
               this.importPublicDependencies(dependency);
            }
         }

      }

      GenericDescriptor findSymbol(final String fullName) {
         return this.findSymbol(fullName, Descriptors.DescriptorPool.SearchFilter.ALL_SYMBOLS);
      }

      GenericDescriptor findSymbol(final String fullName, final SearchFilter filter) {
         GenericDescriptor result = (GenericDescriptor)this.descriptorsByName.get(fullName);
         if (result == null || filter != Descriptors.DescriptorPool.SearchFilter.ALL_SYMBOLS && (filter != Descriptors.DescriptorPool.SearchFilter.TYPES_ONLY || !this.isType(result)) && (filter != Descriptors.DescriptorPool.SearchFilter.AGGREGATES_ONLY || !this.isAggregate(result))) {
            for(FileDescriptor dependency : this.dependencies) {
               result = (GenericDescriptor)dependency.pool.descriptorsByName.get(fullName);
               if (result != null && (filter == Descriptors.DescriptorPool.SearchFilter.ALL_SYMBOLS || filter == Descriptors.DescriptorPool.SearchFilter.TYPES_ONLY && this.isType(result) || filter == Descriptors.DescriptorPool.SearchFilter.AGGREGATES_ONLY && this.isAggregate(result))) {
                  return result;
               }
            }

            return null;
         } else {
            return result;
         }
      }

      boolean isType(GenericDescriptor descriptor) {
         return descriptor instanceof Descriptor || descriptor instanceof EnumDescriptor;
      }

      boolean isAggregate(GenericDescriptor descriptor) {
         return descriptor instanceof Descriptor || descriptor instanceof EnumDescriptor || descriptor instanceof PackageDescriptor || descriptor instanceof ServiceDescriptor;
      }

      GenericDescriptor lookupSymbol(final String name, final GenericDescriptor relativeTo, final SearchFilter filter) throws DescriptorValidationException {
         GenericDescriptor result;
         String fullname;
         if (name.startsWith(".")) {
            fullname = name.substring(1);
            result = this.findSymbol(fullname, filter);
         } else {
            int firstPartLength = name.indexOf(46);
            String firstPart;
            if (firstPartLength == -1) {
               firstPart = name;
            } else {
               firstPart = name.substring(0, firstPartLength);
            }

            StringBuilder scopeToTry = new StringBuilder(relativeTo.getFullName());

            while(true) {
               int dotpos = scopeToTry.lastIndexOf(".");
               if (dotpos == -1) {
                  fullname = name;
                  result = this.findSymbol(name, filter);
                  break;
               }

               scopeToTry.setLength(dotpos + 1);
               scopeToTry.append(firstPart);
               result = this.findSymbol(scopeToTry.toString(), Descriptors.DescriptorPool.SearchFilter.AGGREGATES_ONLY);
               if (result != null) {
                  if (firstPartLength != -1) {
                     scopeToTry.setLength(dotpos + 1);
                     scopeToTry.append(name);
                     result = this.findSymbol(scopeToTry.toString(), filter);
                  }

                  fullname = scopeToTry.toString();
                  break;
               }

               scopeToTry.setLength(dotpos);
            }
         }

         if (result == null) {
            if (this.allowUnknownDependencies && filter == Descriptors.DescriptorPool.SearchFilter.TYPES_ONLY) {
               Descriptors.logger.warning("The descriptor for message type \"" + name + "\" cannot be found and a placeholder is created for it");
               result = new Descriptor(fullname);
               this.dependencies.add(result.getFile());
               return result;
            } else {
               throw new DescriptorValidationException(relativeTo, '"' + name + "\" is not defined.");
            }
         } else {
            return result;
         }
      }

      void addSymbol(final GenericDescriptor descriptor) throws DescriptorValidationException {
         validateSymbolName(descriptor);
         String fullName = descriptor.getFullName();
         GenericDescriptor old = (GenericDescriptor)this.descriptorsByName.put(fullName, descriptor);
         if (old != null) {
            this.descriptorsByName.put(fullName, old);
            if (descriptor.getFile() == old.getFile()) {
               int dotpos = fullName.lastIndexOf(46);
               if (dotpos == -1) {
                  throw new DescriptorValidationException(descriptor, '"' + fullName + "\" is already defined.");
               } else {
                  throw new DescriptorValidationException(descriptor, '"' + fullName.substring(dotpos + 1) + "\" is already defined in \"" + fullName.substring(0, dotpos) + "\".");
               }
            } else {
               throw new DescriptorValidationException(descriptor, '"' + fullName + "\" is already defined in file \"" + old.getFile().getName() + "\".");
            }
         }
      }

      void addPackage(final String fullName, final FileDescriptor file) throws DescriptorValidationException {
         int dotpos = fullName.lastIndexOf(46);
         String name;
         if (dotpos == -1) {
            name = fullName;
         } else {
            this.addPackage(fullName.substring(0, dotpos), file);
            name = fullName.substring(dotpos + 1);
         }

         GenericDescriptor old = (GenericDescriptor)this.descriptorsByName.put(fullName, new PackageDescriptor(name, fullName, file));
         if (old != null) {
            this.descriptorsByName.put(fullName, old);
            if (!(old instanceof PackageDescriptor)) {
               throw new DescriptorValidationException(file, '"' + name + "\" is already defined (as something other than a package) in file \"" + old.getFile().getName() + "\".");
            }
         }

      }

      static void validateSymbolName(final GenericDescriptor descriptor) throws DescriptorValidationException {
         String name = descriptor.getName();
         if (name.length() == 0) {
            throw new DescriptorValidationException(descriptor, "Missing name.");
         } else {
            for(int i = 0; i < name.length(); ++i) {
               char c = name.charAt(i);
               if (('a' > c || c > 'z') && ('A' > c || c > 'Z') && c != '_' && ('0' > c || c > '9' || i <= 0)) {
                  throw new DescriptorValidationException(descriptor, '"' + name + "\" is not a valid identifier.");
               }
            }

         }
      }

      static enum SearchFilter {
         TYPES_ONLY,
         AGGREGATES_ONLY,
         ALL_SYMBOLS;
      }

      private static final class PackageDescriptor extends GenericDescriptor {
         private final String name;
         private final String fullName;
         private final FileDescriptor file;

         public Message toProto() {
            return this.file.toProto();
         }

         public String getName() {
            return this.name;
         }

         public String getFullName() {
            return this.fullName;
         }

         public FileDescriptor getFile() {
            return this.file;
         }

         PackageDescriptor(final String name, final String fullName, final FileDescriptor file) {
            this.file = file;
            this.fullName = fullName;
            this.name = name;
         }
      }
   }

   public static final class OneofDescriptor extends GenericDescriptor {
      private final int index;
      private DescriptorProtos.OneofDescriptorProto proto;
      private final String fullName;
      private final FileDescriptor file;
      private Descriptor containingType;
      private int fieldCount;
      private FieldDescriptor[] fields;

      public int getIndex() {
         return this.index;
      }

      public String getName() {
         return this.proto.getName();
      }

      public FileDescriptor getFile() {
         return this.file;
      }

      public String getFullName() {
         return this.fullName;
      }

      public Descriptor getContainingType() {
         return this.containingType;
      }

      public int getFieldCount() {
         return this.fieldCount;
      }

      public DescriptorProtos.OneofOptions getOptions() {
         return this.proto.getOptions();
      }

      public List getFields() {
         return Collections.unmodifiableList(Arrays.asList(this.fields));
      }

      public FieldDescriptor getField(int index) {
         return this.fields[index];
      }

      public DescriptorProtos.OneofDescriptorProto toProto() {
         return this.proto;
      }

      /** @deprecated */
      @Deprecated
      public boolean isSynthetic() {
         return this.fields.length == 1 && this.fields[0].isProto3Optional;
      }

      private void setProto(final DescriptorProtos.OneofDescriptorProto proto) {
         this.proto = proto;
      }

      private OneofDescriptor(final DescriptorProtos.OneofDescriptorProto proto, final FileDescriptor file, final Descriptor parent, final int index) {
         this.proto = proto;
         this.fullName = Descriptors.computeFullName(file, parent, proto.getName());
         this.file = file;
         this.index = index;
         this.containingType = parent;
         this.fieldCount = 0;
      }
   }

   private interface NumberGetter {
      int getNumber(Object t);
   }
}

package org.apache.commons.compress.archivers.zip;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;
import java.util.zip.ZipException;

public class ExtraFieldUtils {
   private static final int WORD = 4;
   private static final ConcurrentMap IMPLEMENTATIONS = new ConcurrentHashMap();
   static final ZipExtraField[] EMPTY_ZIP_EXTRA_FIELD_ARRAY;

   public static ZipExtraField createExtraField(ZipShort headerId) {
      ZipExtraField field = createExtraFieldNoDefault(headerId);
      if (field != null) {
         return field;
      } else {
         UnrecognizedExtraField u = new UnrecognizedExtraField();
         u.setHeaderId(headerId);
         return u;
      }
   }

   public static ZipExtraField createExtraFieldNoDefault(ZipShort headerId) {
      Supplier<ZipExtraField> provider = (Supplier)IMPLEMENTATIONS.get(headerId);
      return provider != null ? (ZipExtraField)provider.get() : null;
   }

   public static ZipExtraField fillExtraField(ZipExtraField ze, byte[] data, int off, int len, boolean local) throws ZipException {
      try {
         if (local) {
            ze.parseFromLocalFileData(data, off, len);
         } else {
            ze.parseFromCentralDirectoryData(data, off, len);
         }

         return ze;
      } catch (ArrayIndexOutOfBoundsException e) {
         throw (ZipException)(new ZipException("Failed to parse corrupt ZIP extra field of type " + Integer.toHexString(ze.getHeaderId().getValue()))).initCause(e);
      }
   }

   public static byte[] mergeCentralDirectoryData(ZipExtraField[] data) {
      int dataLength = data.length;
      boolean lastIsUnparseableHolder = dataLength > 0 && data[dataLength - 1] instanceof UnparseableExtraFieldData;
      int regularExtraFieldCount = lastIsUnparseableHolder ? dataLength - 1 : dataLength;
      int sum = 4 * regularExtraFieldCount;

      for(ZipExtraField element : data) {
         sum += element.getCentralDirectoryLength().getValue();
      }

      byte[] result = new byte[sum];
      int start = 0;

      for(int i = 0; i < regularExtraFieldCount; ++i) {
         System.arraycopy(data[i].getHeaderId().getBytes(), 0, result, start, 2);
         System.arraycopy(data[i].getCentralDirectoryLength().getBytes(), 0, result, start + 2, 2);
         start += 4;
         byte[] central = data[i].getCentralDirectoryData();
         if (central != null) {
            System.arraycopy(central, 0, result, start, central.length);
            start += central.length;
         }
      }

      if (lastIsUnparseableHolder) {
         byte[] central = data[dataLength - 1].getCentralDirectoryData();
         if (central != null) {
            System.arraycopy(central, 0, result, start, central.length);
         }
      }

      return result;
   }

   public static byte[] mergeLocalFileDataData(ZipExtraField[] data) {
      int dataLength = data.length;
      boolean lastIsUnparseableHolder = dataLength > 0 && data[dataLength - 1] instanceof UnparseableExtraFieldData;
      int regularExtraFieldCount = lastIsUnparseableHolder ? dataLength - 1 : dataLength;
      int sum = 4 * regularExtraFieldCount;

      for(ZipExtraField element : data) {
         sum += element.getLocalFileDataLength().getValue();
      }

      byte[] result = new byte[sum];
      int start = 0;

      for(int i = 0; i < regularExtraFieldCount; ++i) {
         System.arraycopy(data[i].getHeaderId().getBytes(), 0, result, start, 2);
         System.arraycopy(data[i].getLocalFileDataLength().getBytes(), 0, result, start + 2, 2);
         start += 4;
         byte[] local = data[i].getLocalFileDataData();
         if (local != null) {
            System.arraycopy(local, 0, result, start, local.length);
            start += local.length;
         }
      }

      if (lastIsUnparseableHolder) {
         byte[] local = data[dataLength - 1].getLocalFileDataData();
         if (local != null) {
            System.arraycopy(local, 0, result, start, local.length);
         }
      }

      return result;
   }

   public static ZipExtraField[] parse(byte[] data) throws ZipException {
      return parse(data, true, ExtraFieldUtils.UnparseableExtraField.THROW);
   }

   public static ZipExtraField[] parse(byte[] data, boolean local) throws ZipException {
      return parse(data, local, ExtraFieldUtils.UnparseableExtraField.THROW);
   }

   public static ZipExtraField[] parse(byte[] data, boolean local, ExtraFieldParsingBehavior parsingBehavior) throws ZipException {
      List<ZipExtraField> v = new ArrayList();
      int start = 0;
      int dataLength = data.length;

      while(start <= dataLength - 4) {
         ZipShort headerId = new ZipShort(data, start);
         int length = (new ZipShort(data, start + 2)).getValue();
         if (start + 4 + length > dataLength) {
            ZipExtraField field = parsingBehavior.onUnparseableExtraField(data, start, dataLength - start, local, length);
            if (field != null) {
               v.add(field);
            }
            break;
         }

         try {
            ZipExtraField ze = (ZipExtraField)Objects.requireNonNull(parsingBehavior.createExtraField(headerId), "createExtraField must not return null");
            v.add((ZipExtraField)Objects.requireNonNull(parsingBehavior.fill(ze, data, start + 4, length, local), "fill must not return null"));
            start += length + 4;
         } catch (IllegalAccessException | InstantiationException e) {
            throw (ZipException)(new ZipException(((ReflectiveOperationException)e).getMessage())).initCause(e);
         }
      }

      return (ZipExtraField[])v.toArray(EMPTY_ZIP_EXTRA_FIELD_ARRAY);
   }

   public static ZipExtraField[] parse(byte[] data, boolean local, final UnparseableExtraField onUnparseableData) throws ZipException {
      return parse(data, local, new ExtraFieldParsingBehavior() {
         public ZipExtraField createExtraField(ZipShort headerId) {
            return ExtraFieldUtils.createExtraField(headerId);
         }

         public ZipExtraField fill(ZipExtraField field, byte[] data, int off, int len, boolean local) throws ZipException {
            return ExtraFieldUtils.fillExtraField(field, data, off, len, local);
         }

         public ZipExtraField onUnparseableExtraField(byte[] data, int off, int len, boolean local, int claimedLength) throws ZipException {
            return onUnparseableData.onUnparseableExtraField(data, off, len, local, claimedLength);
         }
      });
   }

   /** @deprecated */
   @Deprecated
   public static void register(Class clazz) {
      try {
         Constructor<? extends ZipExtraField> constructor = clazz.asSubclass(ZipExtraField.class).getConstructor();
         ZipExtraField zef = (ZipExtraField)clazz.asSubclass(ZipExtraField.class).getConstructor().newInstance();
         IMPLEMENTATIONS.put(zef.getHeaderId(), (Supplier)() -> {
            try {
               return (ZipExtraField)constructor.newInstance();
            } catch (ReflectiveOperationException e) {
               throw new IllegalStateException(clazz.toString(), e);
            }
         });
      } catch (ReflectiveOperationException e) {
         throw new IllegalArgumentException(clazz.toString(), e);
      }
   }

   static {
      IMPLEMENTATIONS.put(AsiExtraField.HEADER_ID, AsiExtraField::new);
      IMPLEMENTATIONS.put(X5455_ExtendedTimestamp.HEADER_ID, X5455_ExtendedTimestamp::new);
      IMPLEMENTATIONS.put(X7875_NewUnix.HEADER_ID, X7875_NewUnix::new);
      IMPLEMENTATIONS.put(JarMarker.ID, JarMarker::new);
      IMPLEMENTATIONS.put(UnicodePathExtraField.UPATH_ID, UnicodePathExtraField::new);
      IMPLEMENTATIONS.put(UnicodeCommentExtraField.UCOM_ID, UnicodeCommentExtraField::new);
      IMPLEMENTATIONS.put(Zip64ExtendedInformationExtraField.HEADER_ID, Zip64ExtendedInformationExtraField::new);
      IMPLEMENTATIONS.put(X000A_NTFS.HEADER_ID, X000A_NTFS::new);
      IMPLEMENTATIONS.put(X0014_X509Certificates.HEADER_ID, X0014_X509Certificates::new);
      IMPLEMENTATIONS.put(X0015_CertificateIdForFile.HEADER_ID, X0015_CertificateIdForFile::new);
      IMPLEMENTATIONS.put(X0016_CertificateIdForCentralDirectory.HEADER_ID, X0016_CertificateIdForCentralDirectory::new);
      IMPLEMENTATIONS.put(X0017_StrongEncryptionHeader.HEADER_ID, X0017_StrongEncryptionHeader::new);
      IMPLEMENTATIONS.put(X0019_EncryptionRecipientCertificateList.HEADER_ID, X0019_EncryptionRecipientCertificateList::new);
      IMPLEMENTATIONS.put(ResourceAlignmentExtraField.ID, ResourceAlignmentExtraField::new);
      EMPTY_ZIP_EXTRA_FIELD_ARRAY = new ZipExtraField[0];
   }

   public static final class UnparseableExtraField implements UnparseableExtraFieldBehavior {
      public static final int THROW_KEY = 0;
      public static final int SKIP_KEY = 1;
      public static final int READ_KEY = 2;
      public static final UnparseableExtraField THROW = new UnparseableExtraField(0);
      public static final UnparseableExtraField SKIP = new UnparseableExtraField(1);
      public static final UnparseableExtraField READ = new UnparseableExtraField(2);
      private final int key;

      private UnparseableExtraField(int k) {
         this.key = k;
      }

      public int getKey() {
         return this.key;
      }

      public ZipExtraField onUnparseableExtraField(byte[] data, int off, int len, boolean local, int claimedLength) throws ZipException {
         switch (this.key) {
            case 0:
               throw new ZipException("Bad extra field starting at " + off + ".  Block length of " + claimedLength + " bytes exceeds remaining data of " + (len - 4) + " bytes.");
            case 1:
               return null;
            case 2:
               UnparseableExtraFieldData field = new UnparseableExtraFieldData();
               if (local) {
                  field.parseFromLocalFileData(data, off, len);
               } else {
                  field.parseFromCentralDirectoryData(data, off, len);
               }

               return field;
            default:
               throw new ZipException("Unknown UnparseableExtraField key: " + this.key);
         }
      }
   }
}

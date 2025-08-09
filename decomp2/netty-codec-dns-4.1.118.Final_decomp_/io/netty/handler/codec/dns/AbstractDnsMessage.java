package io.netty.handler.codec.dns;

import io.netty.util.AbstractReferenceCounted;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.ResourceLeakDetectorFactory;
import io.netty.util.ResourceLeakTracker;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDnsMessage extends AbstractReferenceCounted implements DnsMessage {
   private static final ResourceLeakDetector leakDetector = ResourceLeakDetectorFactory.instance().newResourceLeakDetector(DnsMessage.class);
   private static final int SECTION_QUESTION;
   private static final int SECTION_COUNT = 4;
   private final ResourceLeakTracker leak;
   private short id;
   private DnsOpCode opCode;
   private boolean recursionDesired;
   private byte z;
   private Object questions;
   private Object answers;
   private Object authorities;
   private Object additionals;

   protected AbstractDnsMessage(int id) {
      this(id, DnsOpCode.QUERY);
   }

   protected AbstractDnsMessage(int id, DnsOpCode opCode) {
      this.leak = leakDetector.track(this);
      this.setId(id);
      this.setOpCode(opCode);
   }

   public int id() {
      return this.id & '\uffff';
   }

   public DnsMessage setId(int id) {
      this.id = (short)id;
      return this;
   }

   public DnsOpCode opCode() {
      return this.opCode;
   }

   public DnsMessage setOpCode(DnsOpCode opCode) {
      this.opCode = (DnsOpCode)ObjectUtil.checkNotNull(opCode, "opCode");
      return this;
   }

   public boolean isRecursionDesired() {
      return this.recursionDesired;
   }

   public DnsMessage setRecursionDesired(boolean recursionDesired) {
      this.recursionDesired = recursionDesired;
      return this;
   }

   public int z() {
      return this.z;
   }

   public DnsMessage setZ(int z) {
      this.z = (byte)(z & 7);
      return this;
   }

   public int count(DnsSection section) {
      return this.count(sectionOrdinal(section));
   }

   private int count(int section) {
      Object records = this.sectionAt(section);
      if (records == null) {
         return 0;
      } else if (records instanceof DnsRecord) {
         return 1;
      } else {
         List<DnsRecord> recordList = (List)records;
         return recordList.size();
      }
   }

   public int count() {
      int count = 0;

      for(int i = 0; i < 4; ++i) {
         count += this.count(i);
      }

      return count;
   }

   public DnsRecord recordAt(DnsSection section) {
      return this.recordAt(sectionOrdinal(section));
   }

   private DnsRecord recordAt(int section) {
      Object records = this.sectionAt(section);
      if (records == null) {
         return null;
      } else if (records instanceof DnsRecord) {
         return castRecord(records);
      } else {
         List<DnsRecord> recordList = (List)records;
         return recordList.isEmpty() ? null : castRecord(recordList.get(0));
      }
   }

   public DnsRecord recordAt(DnsSection section, int index) {
      return this.recordAt(sectionOrdinal(section), index);
   }

   private DnsRecord recordAt(int section, int index) {
      Object records = this.sectionAt(section);
      if (records == null) {
         throw new IndexOutOfBoundsException("index: " + index + " (expected: none)");
      } else if (records instanceof DnsRecord) {
         if (index == 0) {
            return castRecord(records);
         } else {
            throw new IndexOutOfBoundsException("index: " + index + "' (expected: 0)");
         }
      } else {
         List<DnsRecord> recordList = (List)records;
         return castRecord(recordList.get(index));
      }
   }

   public DnsMessage setRecord(DnsSection section, DnsRecord record) {
      this.setRecord(sectionOrdinal(section), record);
      return this;
   }

   private void setRecord(int section, DnsRecord record) {
      this.clear(section);
      this.setSection(section, checkQuestion(section, record));
   }

   public DnsRecord setRecord(DnsSection section, int index, DnsRecord record) {
      return this.setRecord(sectionOrdinal(section), index, record);
   }

   private DnsRecord setRecord(int section, int index, DnsRecord record) {
      checkQuestion(section, record);
      Object records = this.sectionAt(section);
      if (records == null) {
         throw new IndexOutOfBoundsException("index: " + index + " (expected: none)");
      } else if (records instanceof DnsRecord) {
         if (index == 0) {
            this.setSection(section, record);
            return castRecord(records);
         } else {
            throw new IndexOutOfBoundsException("index: " + index + " (expected: 0)");
         }
      } else {
         List<DnsRecord> recordList = (List)records;
         return castRecord(recordList.set(index, record));
      }
   }

   public DnsMessage addRecord(DnsSection section, DnsRecord record) {
      this.addRecord(sectionOrdinal(section), record);
      return this;
   }

   private void addRecord(int section, DnsRecord record) {
      checkQuestion(section, record);
      Object records = this.sectionAt(section);
      if (records == null) {
         this.setSection(section, record);
      } else if (records instanceof DnsRecord) {
         List<DnsRecord> recordList = newRecordList();
         recordList.add(castRecord(records));
         recordList.add(record);
         this.setSection(section, recordList);
      } else {
         List<DnsRecord> recordList = (List)records;
         recordList.add(record);
      }
   }

   public DnsMessage addRecord(DnsSection section, int index, DnsRecord record) {
      this.addRecord(sectionOrdinal(section), index, record);
      return this;
   }

   private void addRecord(int section, int index, DnsRecord record) {
      checkQuestion(section, record);
      Object records = this.sectionAt(section);
      if (records == null) {
         if (index != 0) {
            throw new IndexOutOfBoundsException("index: " + index + " (expected: 0)");
         } else {
            this.setSection(section, record);
         }
      } else if (records instanceof DnsRecord) {
         List<DnsRecord> recordList;
         if (index == 0) {
            recordList = newRecordList();
            recordList.add(record);
            recordList.add(castRecord(records));
         } else {
            if (index != 1) {
               throw new IndexOutOfBoundsException("index: " + index + " (expected: 0 or 1)");
            }

            recordList = newRecordList();
            recordList.add(castRecord(records));
            recordList.add(record);
         }

         this.setSection(section, recordList);
      } else {
         List<DnsRecord> recordList = (List)records;
         recordList.add(index, record);
      }
   }

   public DnsRecord removeRecord(DnsSection section, int index) {
      return this.removeRecord(sectionOrdinal(section), index);
   }

   private DnsRecord removeRecord(int section, int index) {
      Object records = this.sectionAt(section);
      if (records == null) {
         throw new IndexOutOfBoundsException("index: " + index + " (expected: none)");
      } else if (records instanceof DnsRecord) {
         if (index != 0) {
            throw new IndexOutOfBoundsException("index: " + index + " (expected: 0)");
         } else {
            T record = (T)castRecord(records);
            this.setSection(section, (Object)null);
            return record;
         }
      } else {
         List<DnsRecord> recordList = (List)records;
         return castRecord(recordList.remove(index));
      }
   }

   public DnsMessage clear(DnsSection section) {
      this.clear(sectionOrdinal(section));
      return this;
   }

   public DnsMessage clear() {
      for(int i = 0; i < 4; ++i) {
         this.clear(i);
      }

      return this;
   }

   private void clear(int section) {
      Object recordOrList = this.sectionAt(section);
      this.setSection(section, (Object)null);
      if (recordOrList instanceof ReferenceCounted) {
         ((ReferenceCounted)recordOrList).release();
      } else if (recordOrList instanceof List) {
         List<DnsRecord> list = (List)recordOrList;
         if (!list.isEmpty()) {
            for(Object r : list) {
               ReferenceCountUtil.release(r);
            }
         }
      }

   }

   public DnsMessage touch() {
      return (DnsMessage)super.touch();
   }

   public DnsMessage touch(Object hint) {
      if (this.leak != null) {
         this.leak.record(hint);
      }

      return this;
   }

   public DnsMessage retain() {
      return (DnsMessage)super.retain();
   }

   public DnsMessage retain(int increment) {
      return (DnsMessage)super.retain(increment);
   }

   protected void deallocate() {
      this.clear();
      ResourceLeakTracker<DnsMessage> leak = this.leak;
      if (leak != null) {
         boolean closed = leak.close(this);

         assert closed;
      }

   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof DnsMessage)) {
         return false;
      } else {
         DnsMessage that = (DnsMessage)obj;
         if (this.id() != that.id()) {
            return false;
         } else {
            if (this instanceof DnsQuery) {
               if (!(that instanceof DnsQuery)) {
                  return false;
               }
            } else if (that instanceof DnsQuery) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      return this.id() * 31 + (this instanceof DnsQuery ? 0 : 1);
   }

   private Object sectionAt(int section) {
      switch (section) {
         case 0:
            return this.questions;
         case 1:
            return this.answers;
         case 2:
            return this.authorities;
         case 3:
            return this.additionals;
         default:
            throw new Error();
      }
   }

   private void setSection(int section, Object value) {
      switch (section) {
         case 0:
            this.questions = value;
            return;
         case 1:
            this.answers = value;
            return;
         case 2:
            this.authorities = value;
            return;
         case 3:
            this.additionals = value;
            return;
         default:
            throw new Error();
      }
   }

   private static int sectionOrdinal(DnsSection section) {
      return ((DnsSection)ObjectUtil.checkNotNull(section, "section")).ordinal();
   }

   private static DnsRecord checkQuestion(int section, DnsRecord record) {
      if (section == SECTION_QUESTION && !(ObjectUtil.checkNotNull(record, "record") instanceof DnsQuestion)) {
         throw new IllegalArgumentException("record: " + record + " (expected: " + StringUtil.simpleClassName(DnsQuestion.class) + ')');
      } else {
         return record;
      }
   }

   private static DnsRecord castRecord(Object record) {
      return (DnsRecord)record;
   }

   private static ArrayList newRecordList() {
      return new ArrayList(2);
   }

   static {
      SECTION_QUESTION = DnsSection.QUESTION.ordinal();
   }
}

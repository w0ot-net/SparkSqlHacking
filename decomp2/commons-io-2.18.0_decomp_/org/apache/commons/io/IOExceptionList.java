package org.apache.commons.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class IOExceptionList extends IOException implements Iterable {
   private static final long serialVersionUID = 1L;
   private final List causeList;

   public static void checkEmpty(List causeList, Object message) throws IOExceptionList {
      if (!isEmpty(causeList)) {
         throw new IOExceptionList(Objects.toString(message, (String)null), causeList);
      }
   }

   private static boolean isEmpty(List causeList) {
      return size(causeList) == 0;
   }

   private static int size(List causeList) {
      return causeList != null ? causeList.size() : 0;
   }

   private static String toMessage(List causeList) {
      return String.format("%,d exception(s): %s", size(causeList), causeList);
   }

   public IOExceptionList(List causeList) {
      this(toMessage(causeList), causeList);
   }

   public IOExceptionList(String message, List causeList) {
      super(message != null ? message : toMessage(causeList), isEmpty(causeList) ? null : (Throwable)causeList.get(0));
      this.causeList = causeList == null ? Collections.emptyList() : causeList;
   }

   public Throwable getCause(int index) {
      return (Throwable)this.causeList.get(index);
   }

   public Throwable getCause(int index, Class clazz) {
      return (Throwable)clazz.cast(this.getCause(index));
   }

   public List getCauseList() {
      return new ArrayList(this.causeList);
   }

   public List getCauseList(Class clazz) {
      return new ArrayList(this.causeList);
   }

   public Iterator iterator() {
      return this.getCauseList().iterator();
   }
}

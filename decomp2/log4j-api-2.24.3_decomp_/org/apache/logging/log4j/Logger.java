package org.apache.logging.log4j;

import org.apache.logging.log4j.message.EntryMessage;
import org.apache.logging.log4j.message.FlowMessageFactory;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.util.MessageSupplier;
import org.apache.logging.log4j.util.Supplier;

public interface Logger {
   void catching(Level level, Throwable throwable);

   void catching(Throwable throwable);

   void debug(Marker marker, Message message);

   void debug(Marker marker, Message message, Throwable throwable);

   void debug(Marker marker, MessageSupplier messageSupplier);

   void debug(Marker marker, MessageSupplier messageSupplier, Throwable throwable);

   void debug(Marker marker, CharSequence message);

   void debug(Marker marker, CharSequence message, Throwable throwable);

   void debug(Marker marker, Object message);

   void debug(Marker marker, Object message, Throwable throwable);

   void debug(Marker marker, String message);

   void debug(Marker marker, String message, Object... params);

   void debug(Marker marker, String message, Supplier... paramSuppliers);

   void debug(Marker marker, String message, Throwable throwable);

   void debug(Marker marker, Supplier messageSupplier);

   void debug(Marker marker, Supplier messageSupplier, Throwable throwable);

   void debug(Message message);

   void debug(Message message, Throwable throwable);

   void debug(MessageSupplier messageSupplier);

   void debug(MessageSupplier messageSupplier, Throwable throwable);

   void debug(CharSequence message);

   void debug(CharSequence message, Throwable throwable);

   void debug(Object message);

   void debug(Object message, Throwable throwable);

   void debug(String message);

   void debug(String message, Object... params);

   void debug(String message, Supplier... paramSuppliers);

   void debug(String message, Throwable throwable);

   void debug(Supplier messageSupplier);

   void debug(Supplier messageSupplier, Throwable throwable);

   void debug(Marker marker, String message, Object p0);

   void debug(Marker marker, String message, Object p0, Object p1);

   void debug(Marker marker, String message, Object p0, Object p1, Object p2);

   void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3);

   void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4);

   void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5);

   void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6);

   void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7);

   void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8);

   void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9);

   void debug(String message, Object p0);

   void debug(String message, Object p0, Object p1);

   void debug(String message, Object p0, Object p1, Object p2);

   void debug(String message, Object p0, Object p1, Object p2, Object p3);

   void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4);

   void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5);

   void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6);

   void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7);

   void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8);

   void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9);

   /** @deprecated */
   @Deprecated
   void entry();

   /** @deprecated */
   @Deprecated
   void entry(Object... params);

   void error(Marker marker, Message message);

   void error(Marker marker, Message message, Throwable throwable);

   void error(Marker marker, MessageSupplier messageSupplier);

   void error(Marker marker, MessageSupplier messageSupplier, Throwable throwable);

   void error(Marker marker, CharSequence message);

   void error(Marker marker, CharSequence message, Throwable throwable);

   void error(Marker marker, Object message);

   void error(Marker marker, Object message, Throwable throwable);

   void error(Marker marker, String message);

   void error(Marker marker, String message, Object... params);

   void error(Marker marker, String message, Supplier... paramSuppliers);

   void error(Marker marker, String message, Throwable throwable);

   void error(Marker marker, Supplier messageSupplier);

   void error(Marker marker, Supplier messageSupplier, Throwable throwable);

   void error(Message message);

   void error(Message message, Throwable throwable);

   void error(MessageSupplier messageSupplier);

   void error(MessageSupplier messageSupplier, Throwable throwable);

   void error(CharSequence message);

   void error(CharSequence message, Throwable throwable);

   void error(Object message);

   void error(Object message, Throwable throwable);

   void error(String message);

   void error(String message, Object... params);

   void error(String message, Supplier... paramSuppliers);

   void error(String message, Throwable throwable);

   void error(Supplier messageSupplier);

   void error(Supplier messageSupplier, Throwable throwable);

   void error(Marker marker, String message, Object p0);

   void error(Marker marker, String message, Object p0, Object p1);

   void error(Marker marker, String message, Object p0, Object p1, Object p2);

   void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3);

   void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4);

   void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5);

   void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6);

   void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7);

   void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8);

   void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9);

   void error(String message, Object p0);

   void error(String message, Object p0, Object p1);

   void error(String message, Object p0, Object p1, Object p2);

   void error(String message, Object p0, Object p1, Object p2, Object p3);

   void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4);

   void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5);

   void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6);

   void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7);

   void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8);

   void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9);

   /** @deprecated */
   @Deprecated
   void exit();

   /** @deprecated */
   @Deprecated
   Object exit(Object result);

   void fatal(Marker marker, Message message);

   void fatal(Marker marker, Message message, Throwable throwable);

   void fatal(Marker marker, MessageSupplier messageSupplier);

   void fatal(Marker marker, MessageSupplier messageSupplier, Throwable throwable);

   void fatal(Marker marker, CharSequence message);

   void fatal(Marker marker, CharSequence message, Throwable throwable);

   void fatal(Marker marker, Object message);

   void fatal(Marker marker, Object message, Throwable throwable);

   void fatal(Marker marker, String message);

   void fatal(Marker marker, String message, Object... params);

   void fatal(Marker marker, String message, Supplier... paramSuppliers);

   void fatal(Marker marker, String message, Throwable throwable);

   void fatal(Marker marker, Supplier messageSupplier);

   void fatal(Marker marker, Supplier messageSupplier, Throwable throwable);

   void fatal(Message message);

   void fatal(Message message, Throwable throwable);

   void fatal(MessageSupplier messageSupplier);

   void fatal(MessageSupplier messageSupplier, Throwable throwable);

   void fatal(CharSequence message);

   void fatal(CharSequence message, Throwable throwable);

   void fatal(Object message);

   void fatal(Object message, Throwable throwable);

   void fatal(String message);

   void fatal(String message, Object... params);

   void fatal(String message, Supplier... paramSuppliers);

   void fatal(String message, Throwable throwable);

   void fatal(Supplier messageSupplier);

   void fatal(Supplier messageSupplier, Throwable throwable);

   void fatal(Marker marker, String message, Object p0);

   void fatal(Marker marker, String message, Object p0, Object p1);

   void fatal(Marker marker, String message, Object p0, Object p1, Object p2);

   void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3);

   void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4);

   void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5);

   void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6);

   void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7);

   void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8);

   void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9);

   void fatal(String message, Object p0);

   void fatal(String message, Object p0, Object p1);

   void fatal(String message, Object p0, Object p1, Object p2);

   void fatal(String message, Object p0, Object p1, Object p2, Object p3);

   void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4);

   void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5);

   void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6);

   void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7);

   void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8);

   void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9);

   Level getLevel();

   MessageFactory getMessageFactory();

   FlowMessageFactory getFlowMessageFactory();

   String getName();

   void info(Marker marker, Message message);

   void info(Marker marker, Message message, Throwable throwable);

   void info(Marker marker, MessageSupplier messageSupplier);

   void info(Marker marker, MessageSupplier messageSupplier, Throwable throwable);

   void info(Marker marker, CharSequence message);

   void info(Marker marker, CharSequence message, Throwable throwable);

   void info(Marker marker, Object message);

   void info(Marker marker, Object message, Throwable throwable);

   void info(Marker marker, String message);

   void info(Marker marker, String message, Object... params);

   void info(Marker marker, String message, Supplier... paramSuppliers);

   void info(Marker marker, String message, Throwable throwable);

   void info(Marker marker, Supplier messageSupplier);

   void info(Marker marker, Supplier messageSupplier, Throwable throwable);

   void info(Message message);

   void info(Message message, Throwable throwable);

   void info(MessageSupplier messageSupplier);

   void info(MessageSupplier messageSupplier, Throwable throwable);

   void info(CharSequence message);

   void info(CharSequence message, Throwable throwable);

   void info(Object message);

   void info(Object message, Throwable throwable);

   void info(String message);

   void info(String message, Object... params);

   void info(String message, Supplier... paramSuppliers);

   void info(String message, Throwable throwable);

   void info(Supplier messageSupplier);

   void info(Supplier messageSupplier, Throwable throwable);

   void info(Marker marker, String message, Object p0);

   void info(Marker marker, String message, Object p0, Object p1);

   void info(Marker marker, String message, Object p0, Object p1, Object p2);

   void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3);

   void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4);

   void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5);

   void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6);

   void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7);

   void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8);

   void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9);

   void info(String message, Object p0);

   void info(String message, Object p0, Object p1);

   void info(String message, Object p0, Object p1, Object p2);

   void info(String message, Object p0, Object p1, Object p2, Object p3);

   void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4);

   void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5);

   void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6);

   void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7);

   void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8);

   void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9);

   boolean isDebugEnabled();

   boolean isDebugEnabled(Marker marker);

   boolean isEnabled(Level level);

   boolean isEnabled(Level level, Marker marker);

   boolean isErrorEnabled();

   boolean isErrorEnabled(Marker marker);

   boolean isFatalEnabled();

   boolean isFatalEnabled(Marker marker);

   boolean isInfoEnabled();

   boolean isInfoEnabled(Marker marker);

   boolean isTraceEnabled();

   boolean isTraceEnabled(Marker marker);

   boolean isWarnEnabled();

   boolean isWarnEnabled(Marker marker);

   void log(Level level, Marker marker, Message message);

   void log(Level level, Marker marker, Message message, Throwable throwable);

   void log(Level level, Marker marker, MessageSupplier messageSupplier);

   void log(Level level, Marker marker, MessageSupplier messageSupplier, Throwable throwable);

   void log(Level level, Marker marker, CharSequence message);

   void log(Level level, Marker marker, CharSequence message, Throwable throwable);

   void log(Level level, Marker marker, Object message);

   void log(Level level, Marker marker, Object message, Throwable throwable);

   void log(Level level, Marker marker, String message);

   void log(Level level, Marker marker, String message, Object... params);

   void log(Level level, Marker marker, String message, Supplier... paramSuppliers);

   void log(Level level, Marker marker, String message, Throwable throwable);

   void log(Level level, Marker marker, Supplier messageSupplier);

   void log(Level level, Marker marker, Supplier messageSupplier, Throwable throwable);

   void log(Level level, Message message);

   void log(Level level, Message message, Throwable throwable);

   void log(Level level, MessageSupplier messageSupplier);

   void log(Level level, MessageSupplier messageSupplier, Throwable throwable);

   void log(Level level, CharSequence message);

   void log(Level level, CharSequence message, Throwable throwable);

   void log(Level level, Object message);

   void log(Level level, Object message, Throwable throwable);

   void log(Level level, String message);

   void log(Level level, String message, Object... params);

   void log(Level level, String message, Supplier... paramSuppliers);

   void log(Level level, String message, Throwable throwable);

   void log(Level level, Supplier messageSupplier);

   void log(Level level, Supplier messageSupplier, Throwable throwable);

   void log(Level level, Marker marker, String message, Object p0);

   void log(Level level, Marker marker, String message, Object p0, Object p1);

   void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2);

   void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3);

   void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4);

   void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5);

   void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6);

   void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7);

   void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8);

   void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9);

   void log(Level level, String message, Object p0);

   void log(Level level, String message, Object p0, Object p1);

   void log(Level level, String message, Object p0, Object p1, Object p2);

   void log(Level level, String message, Object p0, Object p1, Object p2, Object p3);

   void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4);

   void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5);

   void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6);

   void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7);

   void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8);

   void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9);

   void printf(Level level, Marker marker, String format, Object... params);

   void printf(Level level, String format, Object... params);

   Throwable throwing(Level level, Throwable throwable);

   Throwable throwing(Throwable throwable);

   void trace(Marker marker, Message message);

   void trace(Marker marker, Message message, Throwable throwable);

   void trace(Marker marker, MessageSupplier messageSupplier);

   void trace(Marker marker, MessageSupplier messageSupplier, Throwable throwable);

   void trace(Marker marker, CharSequence message);

   void trace(Marker marker, CharSequence message, Throwable throwable);

   void trace(Marker marker, Object message);

   void trace(Marker marker, Object message, Throwable throwable);

   void trace(Marker marker, String message);

   void trace(Marker marker, String message, Object... params);

   void trace(Marker marker, String message, Supplier... paramSuppliers);

   void trace(Marker marker, String message, Throwable throwable);

   void trace(Marker marker, Supplier messageSupplier);

   void trace(Marker marker, Supplier messageSupplier, Throwable throwable);

   void trace(Message message);

   void trace(Message message, Throwable throwable);

   void trace(MessageSupplier messageSupplier);

   void trace(MessageSupplier messageSupplier, Throwable throwable);

   void trace(CharSequence message);

   void trace(CharSequence message, Throwable throwable);

   void trace(Object message);

   void trace(Object message, Throwable throwable);

   void trace(String message);

   void trace(String message, Object... params);

   void trace(String message, Supplier... paramSuppliers);

   void trace(String message, Throwable throwable);

   void trace(Supplier messageSupplier);

   void trace(Supplier messageSupplier, Throwable throwable);

   void trace(Marker marker, String message, Object p0);

   void trace(Marker marker, String message, Object p0, Object p1);

   void trace(Marker marker, String message, Object p0, Object p1, Object p2);

   void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3);

   void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4);

   void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5);

   void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6);

   void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7);

   void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8);

   void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9);

   void trace(String message, Object p0);

   void trace(String message, Object p0, Object p1);

   void trace(String message, Object p0, Object p1, Object p2);

   void trace(String message, Object p0, Object p1, Object p2, Object p3);

   void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4);

   void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5);

   void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6);

   void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7);

   void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8);

   void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9);

   EntryMessage traceEntry();

   EntryMessage traceEntry(String format, Object... params);

   EntryMessage traceEntry(Supplier... paramSuppliers);

   EntryMessage traceEntry(String format, Supplier... paramSuppliers);

   EntryMessage traceEntry(Message message);

   void traceExit();

   Object traceExit(Object result);

   Object traceExit(String format, Object result);

   void traceExit(EntryMessage message);

   Object traceExit(EntryMessage message, Object result);

   Object traceExit(Message message, Object result);

   void warn(Marker marker, Message message);

   void warn(Marker marker, Message message, Throwable throwable);

   void warn(Marker marker, MessageSupplier messageSupplier);

   void warn(Marker marker, MessageSupplier messageSupplier, Throwable throwable);

   void warn(Marker marker, CharSequence message);

   void warn(Marker marker, CharSequence message, Throwable throwable);

   void warn(Marker marker, Object message);

   void warn(Marker marker, Object message, Throwable throwable);

   void warn(Marker marker, String message);

   void warn(Marker marker, String message, Object... params);

   void warn(Marker marker, String message, Supplier... paramSuppliers);

   void warn(Marker marker, String message, Throwable throwable);

   void warn(Marker marker, Supplier messageSupplier);

   void warn(Marker marker, Supplier messageSupplier, Throwable throwable);

   void warn(Message message);

   void warn(Message message, Throwable throwable);

   void warn(MessageSupplier messageSupplier);

   void warn(MessageSupplier messageSupplier, Throwable throwable);

   void warn(CharSequence message);

   void warn(CharSequence message, Throwable throwable);

   void warn(Object message);

   void warn(Object message, Throwable throwable);

   void warn(String message);

   void warn(String message, Object... params);

   void warn(String message, Supplier... paramSuppliers);

   void warn(String message, Throwable throwable);

   void warn(Supplier messageSupplier);

   void warn(Supplier messageSupplier, Throwable throwable);

   void warn(Marker marker, String message, Object p0);

   void warn(Marker marker, String message, Object p0, Object p1);

   void warn(Marker marker, String message, Object p0, Object p1, Object p2);

   void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3);

   void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4);

   void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5);

   void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6);

   void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7);

   void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8);

   void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9);

   void warn(String message, Object p0);

   void warn(String message, Object p0, Object p1);

   void warn(String message, Object p0, Object p1, Object p2);

   void warn(String message, Object p0, Object p1, Object p2, Object p3);

   void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4);

   void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5);

   void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6);

   void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7);

   void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8);

   void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9);

   default void logMessage(final Level level, final Marker marker, final String fqcn, final StackTraceElement location, final Message message, final Throwable throwable) {
   }

   default LogBuilder atTrace() {
      return LogBuilder.NOOP;
   }

   default LogBuilder atDebug() {
      return LogBuilder.NOOP;
   }

   default LogBuilder atInfo() {
      return LogBuilder.NOOP;
   }

   default LogBuilder atWarn() {
      return LogBuilder.NOOP;
   }

   default LogBuilder atError() {
      return LogBuilder.NOOP;
   }

   default LogBuilder atFatal() {
      return LogBuilder.NOOP;
   }

   default LogBuilder always() {
      return LogBuilder.NOOP;
   }

   default LogBuilder atLevel(final Level level) {
      return LogBuilder.NOOP;
   }
}

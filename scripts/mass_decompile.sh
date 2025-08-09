#!/bin/bash
# mass_decompile.sh
# Usage:
#   /bin/bash mass_decompile.sh <input_dir_with_jars> <output_dir> [optional_path_to_fernflower_jar]
#   root@ubuntu:~/SparkSqlHacking# bash scripts/mass_decompile.sh /root/spark-4.0.0-bin-hadoop3/jars/ ./decomp2
#
# Output format in /tmp/decompilation_results.txt:
#   [-] file.jar -- FAILED: <reason>
#   [+] file.jar -- COMPLETED
#
# Requires: java, unzip, find, xargs, nproc, timeout, flock, basename, mkdir, rm, touch, cat.

set -euo pipefail

LOG_FILE="/tmp/decompilation_results.txt"
STATUS_DIR="/tmp/decomp_status_markers"
DEFAULT_FF="/usr/bin/fernflower.jar"

log_result() {
  local status="$1"
  local jar_path="$2"
  local reason="${3:-}"
  local base=$(/usr/bin/basename "$jar_path")
  if [ "$status" = "COMPLETED" ]; then
    /usr/bin/flock -x "$LOG_FILE".lock /bin/sh -c "/bin/echo \"[+] $base -- COMPLETED\" >> \"$LOG_FILE\""
  else
    /usr/bin/flock -x "$LOG_FILE".lock /bin/sh -c "/bin/echo \"[-] $base -- FAILED: $reason\" >> \"$LOG_FILE\""
  fi
}

if [ "${1-}" = "--worker" ]; then
  INPUT_JAR="$2"
  OUT_DIR="$3"
  FERNFLOWER_JAR="$4"
  LOG_PATH="$5"
  STATUS_DIR_PATH="$6"

  /bin/mkdir -p "$OUT_DIR"

  BASE=$(/usr/bin/basename "$INPUT_JAR")
  STEM="${BASE%.jar}"
  DEST_DIR="${OUT_DIR}/${STEM}_decomp_"
  TMP_OUT="${DEST_DIR}/__ff_out__"

  /bin/mkdir -p "$DEST_DIR" "$TMP_OUT"

  MARKER="${STATUS_DIR_PATH}/${BASE}.status"
  /bin/echo "STARTED $INPUT_JAR" > "$MARKER"

  /usr/bin/timeout --preserve-status 300 /usr/bin/java -jar "$FERNFLOWER_JAR" "$INPUT_JAR" "$TMP_OUT" >/dev/null 2>&1
  STATUS=$?

  if [ $STATUS -eq 124 ]; then
    /bin/echo "TIMED_OUT $INPUT_JAR" > "$MARKER"
    log_result "FAILED" "$INPUT_JAR" "TIMEOUT"
    /bin/rm -rf "$DEST_DIR"
    exit 0
  fi

  if [ $STATUS -ne 0 ]; then
    /bin/echo "FAILED $INPUT_JAR" > "$MARKER"
    log_result "FAILED" "$INPUT_JAR" "EXIT_CODE_$STATUS"
    /bin/rm -rf "$DEST_DIR"
    exit 0
  fi

  OUT_JAR="${TMP_OUT}/${BASE}"
  if [ ! -f "$OUT_JAR" ]; then
    OUT_JAR=$(/usr/bin/find "$TMP_OUT" -maxdepth 1 -type f -name "*.jar" | /usr/bin/head -n 1 || true)
  fi

  if [ -z "${OUT_JAR:-}" ] || [ ! -f "$OUT_JAR" ]; then
    /bin/echo "NO_OUTPUT $INPUT_JAR" > "$MARKER"
    log_result "FAILED" "$INPUT_JAR" "NO_OUTPUT"
    /bin/rm -rf "$DEST_DIR"
    exit 0
  fi

  if ! /usr/bin/unzip -qq -o "$OUT_JAR" -d "$DEST_DIR" >/dev/null 2>&1; then
    /bin/echo "EXTRACT_FAILED $INPUT_JAR" > "$MARKER"
    log_result "FAILED" "$INPUT_JAR" "EXTRACT_FAILED"
    /bin/rm -rf "$DEST_DIR"
    exit 0
  fi

  /bin/rm -rf "$TMP_OUT" "$OUT_JAR" >/dev/null 2>&1 || true

  /bin/echo "COMPLETED $INPUT_JAR" > "$MARKER"
  log_result "COMPLETED" "$INPUT_JAR"
  exit 0
fi

# Main mode
if [ "$#" -lt 2 ] || [ "$#" -gt 3 ]; then
  /bin/echo "Usage: $0 <input_dir_with_jars> <output_dir> [optional_path_to_fernflower_jar]" >&2
  exit 1
fi

INPUT_DIR="$1"
OUTPUT_DIR="$2"
FERNFLOWER="${3:-$DEFAULT_FF}"

for CMD in /usr/bin/java /usr/bin/unzip /usr/bin/find /usr/bin/xargs /usr/bin/nproc /usr/bin/timeout /usr/bin/flock /usr/bin/basename /bin/mkdir /bin/rm /bin/touch /bin/cat; do
  if [ ! -x "$CMD" ]; then
    /bin/echo "Required command not found: $CMD" >&2
    exit 1
  fi
done

if [ ! -f "$FERNFLOWER" ]; then
  /bin/echo "Fernflower jar not found: $FERNFLOWER" >&2
  exit 1
fi

if [ ! -d "$INPUT_DIR" ]; then
  /bin/echo "Input directory does not exist: $INPUT_DIR" >&2
  exit 1
fi

/bin/mkdir -p "$OUTPUT_DIR"
/bin/rm -f "$LOG_FILE" "$LOG_FILE".lock
/bin/touch "$LOG_FILE"
/bin/mkdir -p "$STATUS_DIR"
/bin/rm -f "$STATUS_DIR"/*.status 2>/dev/null || true

PAR="$(( $(/usr/bin/nproc 2>/dev/null || /bin/echo 1) ))"
if [ -n "${PARALLELISM-}" ]; then
  PAR="$PARALLELISM"
fi
if [ -z "$PAR" ] || [ "$PAR" -le 0 ]; then
  PAR=1
fi

/usr/bin/find "$INPUT_DIR" -maxdepth 1 -type f -name "*.jar" -print0 \
| /usr/bin/xargs -0 -I{} -P "$PAR" /bin/bash "$0" --worker "{}" "$OUTPUT_DIR" "$FERNFLOWER" "$LOG_FILE" "$STATUS_DIR"

# Post-process: detect KILLED_OR_OOM
/usr/bin/find "$STATUS_DIR" -type f -name "*.status" -print0 \
| /usr/bin/xargs -0 -I% /bin/sh -c '
  STATUS_LINE="$(/bin/cat "%")"
  WORD=$(echo "$STATUS_LINE" | /usr/bin/awk "{print \$1}")
  JAR_PATH=$(echo "$STATUS_LINE" | /usr/bin/awk "{\$1=\"\"; sub(/^[[:space:]]+/,\"\"); print}")
  if [ "$WORD" = "STARTED" ]; then
    base=$(/usr/bin/basename "$JAR_PATH")
    /usr/bin/flock -x "'"$LOG_FILE"'.lock" /bin/sh -c "/bin/echo \"[-] $base -- FAILED: KILLED_OR_OOM\" >> \"'"$LOG_FILE"'\""
    echo "KILLED_OR_OOM $JAR_PATH" > "%"
  fi
'

/bin/echo "Decompilation complete. See log: $LOG_FILE"


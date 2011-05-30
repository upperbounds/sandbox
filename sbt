#!/bin/sh
SBT_OPTS="-XX:MaxPermSize=512M -Xmx1056M -noverify -Djava.awt.headless=true -Xss2M -XX:+CMSClassUnloadingEnabled"

cygterm=false
if $cygwin ; then
  case "$TERM" in
      rxvt* | xterm*) cygterm=true ;;
  esac
fi

EXEC=exec

if $cygterm == "bub" ; then
  echo "running cgterm"
  #EXEC=
  #JAVA_OPTS="$JAVA_OPTS -Djline.terminal=jline.UnixTerminal"
  stty -icanon min 1 -echo > /dev/null 2>&1
fi
  echo "running $EXEC "${JAVACMD:=java}" $JAVA_OPTS $SBT_OPTS -jar `dirname $0`/sbt-launcher.jar "$@""
$EXEC "${JAVACMD:=java}" $JAVA_OPTS $SBT_OPTS -jar `dirname $0`/sbt-launcher.jar "$@"

if $cygterm ; then
  # Record the exit status immediately, or it will be overridden.
  SCALA_STATUS=$?
  stty icanon echo > /dev/null 2>&1
  exit $SCALA_STATUS
fi

#!/bin/sh
if test -f ~/.sbtconfig; then
  . ~/.sbtconfig
fi
exec java -Xmx2048M -XX:MaxPermSize=512M ${SBT_OPTS} -jar `dirname $0`/sbt-launch.jar "$@"

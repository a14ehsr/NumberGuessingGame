#!/bin/sh

if [ $# -ne 2 ]; then
  echo "指定された引数は$#個です。" 1>&2
  echo "実行するには2個の引数が必要です。" 1>&2
  exit 1
fi
echo $1
echo $2
java -classpath java/src ac.a14ehsr.platform.NumberGuessingGame -a "$1" -d "$2"

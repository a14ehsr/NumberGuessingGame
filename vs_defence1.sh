#!bin/sh

if [ $# -ne 1 ]; then
  echo "指定された引数は$#個です。" 1>&2
  echo "実行するには1個の引数が必要です。" 1>&2
  exit 1
fi

echo $1

java -classpath java/src ac.a14ehsr.platform.NumberGuessingGame -a "$1" -d "java -classpath java/src/ ac.a14ehsr.sample_ai.defence.D_SameDeclare" -olevel 2

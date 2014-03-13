#!/bin/bash

while read word; do
  file="./speech/$word.mp3"
  if [ -e $file ]; then
    echo "We already have $file"
  else
    echo "Downloading $word"
    ./textToSpeech.sh $word
  fi
done < words.txt

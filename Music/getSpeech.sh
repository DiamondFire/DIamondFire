#!/bin/bash

while read word; do
  ./textToSpeech.sh $word
done < words.txt

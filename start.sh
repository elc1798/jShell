#!/bin/bash

javac jsh/*.java
mv jsh/*.class jsh/COMPILED
java -cp jsh/COMPILED Exec

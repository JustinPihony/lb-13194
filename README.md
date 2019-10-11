
Run with : 

mvn exec:java@node1
mvn exec:java@node2
mvn exec:java@node3

It uses 127.0.0.1, 127.0.0.2 and 127.0.0.3, so those need to exist (yes on Linux, on OSX not)

On OSX execute : 

sudo ifconfig lo0 alias 127.0.0.2 up
sudo ifconfig lo0 alias 127.0.0.3 up
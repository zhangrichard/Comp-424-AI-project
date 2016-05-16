
inside folder 260560802:

javac -cp ../jar/omweso.jar:./src/ ./src/s260560802/s260560802Player.java

You can launch the client using this player by using the following UNIX based command line:

java -cp ../jar/omweso.jar:./src/ boardgame.Client s260560802.s260560802Player localhost 8123





server start same folder
java -cp ../jar/omweso.jar boardgame.Server -ng -k


run autoplayer script: (for vs random bot)

javac Autoplay2.java
java Autoplay2

(for vs itself)
javac Autoplay3.java
java Autoplay3

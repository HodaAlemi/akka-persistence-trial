name := "akka-persistence"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka"          %% "akka-persistence" % "2.5.22",
  "org.iq80.leveldb"            % "leveldb"          % "0.7",
  "org.fusesource.leveldbjni"   % "leveldbjni-all"   % "1.8"
)



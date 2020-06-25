name := "elastic-api"

version := "0.1"

scalaVersion := "2.12.9"

libraryDependencies +="com.typesafe.akka" %% "akka-actor" % "2.5.26"
libraryDependencies +="com.typesafe.akka" %% "akka-http" % "10.1.9"
libraryDependencies +="com.typesafe.akka" %% "akka-stream" % "2.5.26"
libraryDependencies +="com.typesafe.play" %% "play-json" % "2.7.4"
libraryDependencies +="de.heikoseeberger" %% "akka-http-play-json" % "1.27.0"
libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.11"
libraryDependencies +="org.elasticsearch" % "elasticsearch" % "7.7.1"
libraryDependencies +="org.elasticsearch.client" % "elasticsearch-rest-high-level-client" % "7.7.1"


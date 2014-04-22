name := "agendatech"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  filters,
  "commons-io" % "commons-io" % "2.4",
  "org.glassfish.web" % "javax.el" % "2.2.6",
  "mysql" % "mysql-connector-java" % "5.1.20",
  "javax.mail" % "mail" % "1.4.1",
  "com.typesafe" %% "play-plugins-mailer" % "2.1-RC2",
  "com.github.asouza.play" %% "play-paperclip" % "1.0-SNAPSHOT"
)  
   
play.Project.playJavaSettings

javaOptions in Test += "-Dconfig.file=conf/test.conf"

templatesImport ++= Seq("infra.FormatadorDeDatas._") 

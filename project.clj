(defproject org.pathirage.clojure.magic "0.1.0-SNAPSHOT"
  :description "Microbenchmarks and fun things related to Clojure"
  :url "https://github.com/milinda/clojure-magic"
  :license {:name "Apache License, Version 2.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :source-paths      ["src/clj"]
  :java-source-paths ["src/java"]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.apache.storm/storm-core "0.9.2-incubating-SNAPSHOT"]]
  :main org.pathirage.clojure.magic.proxy.core)

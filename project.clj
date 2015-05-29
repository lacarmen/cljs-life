(defproject cljs-life "0.1.0-SNAPSHOT"
            :description "FIXME: write description"
            :url "http://example.com/FIXME"
            :license {:name "Eclipse Public License"
                      :url  "http://www.eclipse.org/legal/epl-v10.html"}
            :dependencies [[org.clojure/clojure "1.6.0"]
                           [quil "2.2.5"]
                           [org.clojure/clojurescript "0.0-3211"]]
            :plugins [[lein-cljsbuild "1.0.5"]
                      [lein-figwheel "0.3.1"]]

            :hooks [leiningen.cljsbuild]

            :cljsbuild {:builds [{:source-paths ["src/"]
                                  :figwheel     true
                                  :compiler     {:main       "cljs-life.core"
                                                 :asset-path "js/out"
                                                 :output-to  "resources/public/js/cljs-life.js"
                                                 :output-dir "resources/public/js/out"}}]})

(defproject org.clojars.borkdude/cljs-react-reload "0.1.2-SNAPSHOT"
  :description "A library to define React classes that maintain local state on reload."
  :url "https://github.com/bhauman/cljs-react-reload"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :scm { :name "git"
         :url "https://github.com/bhauman/cljs-react-reload.git" }
  
  :dependencies [[org.clojure/clojure "1.7.0"]]

  :clean-targets ^{:protect false} ["example-resources/public/js/compiled"
                                    "target"]

  :source-paths ["src"]

  :cljsbuild {
              :builds [{:id "devcards"
                        :source-paths ["src" "example_src"]

                        :figwheel { :devcards true }
                        :compiler { :main       "react-reload-devcards.core"
                                   :asset-path "js/compiled/devcards_out"
                                   :output-to  "example-resources/public/js/compiled/cljs_react_reload_devcards.js"
                                   :output-dir "example-resources/public/js/compiled/devcards_out"
                                   :source-map-timestamp true }}]}

  :figwheel { :css-dirs ["resources/public/css"]
              :open-file-command "emacsclient"}
  
  :profiles { 
             :dev {
                   :resource-paths ["example-resources"]
                   :dependencies [[devcards "0.2.0-SNAPSHOT"]
                                  [sablono "0.3.6"]
                                  [org.clojure/clojurescript "1.7.122"]]
                   :plugins [[lein-cljsbuild "1.1.0"]
                             [lein-figwheel "0.4.0"]]}})

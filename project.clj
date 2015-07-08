(defproject cljs-react-reload "0.1.0"
  :description "A library to define React classes that maintain local state on reload."
  :url "https://github.com/bhauman/cljs-react-reload"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :scm { :name "git"
         :url "https://github.com/bhauman/cljs-react-reload.git" }
  
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "0.0-3308"]]

  :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                    "target"]

  :plugins [[lein-cljsbuild "1.0.5"]
            [lein-figwheel "0.3.7"]]
  
  :profiles { 
    :dev {
      :dependencies [[devcards "0.2.0-SNAPSHOT"]
                     [sablono "0.3.4"]]
    }
  }
  
  :source-paths ["src"]

  :cljsbuild {
              :builds [{:id "devcards"
                        :source-paths ["src" "example_src"]
                        :figwheel { :devcards true }
                        :compiler { :main       "react-reload-devcards.core"
                                    :asset-path "js/compiled/devcards_out"
                                    :output-to  "resources/public/js/compiled/cljs_react_reload_devcards.js"
                                    :output-dir "resources/public/js/compiled/devcards_out"
                                    :source-map-timestamp true }}
                       {:id "dev"
                        :source-paths ["src"]
                        :figwheel true
                        :compiler {:main       "cljs-react-reload.core"
                                   :asset-path "js/compiled/out"
                                   :output-to  "resources/public/js/compiled/cljs_react_reload.js"
                                   :output-dir "resources/public/js/compiled/out"
                                   :source-map-timestamp true }}]}

  :figwheel { :css-dirs ["resources/public/css"]
              :open-file-command "emacsclient"})

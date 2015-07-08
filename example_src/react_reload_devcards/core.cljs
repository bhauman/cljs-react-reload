(ns react-reload-devcards.core
  (:require
   [sablono.core :as sab :include-macros true])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]
   [cljs-react-reload.core :refer [def-react-class defonce-react-class]]))

(enable-console-print!)

(defcard
  "## We are going to try and create a reloadable react element.")

(def-react-class my-class
  #js {:getInitialState
       (fn [] #js {:count 0})
       :render
       (fn []
         (this-as
          this
          (sab/html
           [:div
            [:h1 (.. this -props -name)]
            [:div (.. this -state -count)]
            [:button
             {:onClick (fn []
                         (.setState
                          this
                          #js {:count (inc (.. this -state -count))}))}
             "inc"]])))})

(defcard def-react-class-example
  "When you redefine a React class the React diffing algorithm blows
   away the local state of the component. If increment the counter and
   you edit the `my-class` component you will see this in action."
  (js/React.createElement my-class #js {:name "Mike"}))

(defonce-react-class my-classyy
  #js {:getInitialState
       (fn [] #js {:count 0})
       :shouldComponentUpdate
       (fn [a b]
         (prn "I'm here yep")
         true)
       :componentWillReceiveProps
       (fn [a b]
         (prn "Receiving props now"))
       :render
       (fn []
         (this-as
          this
          (sab/html
           [:div
            [:h3 "And now the local state is maintained."]
            [:h1 (.. this -props -name)]
            [:div (.. this -state -count)]
            [:button
             {:onClick (fn []
                         (.setState
                          this
                          #js {:count (inc (.. this -state -count))}))}
             "inc"]])))})

(defcard defonce-react-class-example
  "The solution is to define the React class once and then patch it's protoype."
  (js/React.createElement my-classyy #js {:name "Margaret"}))

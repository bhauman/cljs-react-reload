(ns cljs-react-reload.core
  (:require
   [cljs.env :as env]))

(defmacro def-react-class [vname body]
  `(def ~vname (js/createReactClass ~body)))

(defmacro defonce-react-class
  "Creates a  stable base React class and then patches it on reload."
  [vname body]
  (if (and env/*compiler*
           (let [{:keys [optimizations]} (get env/*compiler* :options)]
             (or (nil? optimizations) (= optimizations :none))))
    `(let [base# ~body]
       (defonce ~vname (js/createReactClass base#))
       (doseq [property# (map
                          name
                          '(shouldComponentUpdate
                            componentWillReceiveProps
                            componentWillMount
                            componentDidMount
                            componentWillUpdate
                            componentDidUpdate
                            componentWillUnmount
                            render))]
         (when (aget base# property#)
           (aset (.-prototype ~vname) property# (aget base# property#)))))
    `(defonce ~vname (js/createReactClass ~body))))

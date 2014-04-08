(ns org.pathirage.clojure.magic.proxy.core
  (:import [org.pathirage.clojure.magic.proxy ClojureProxy ClojureWrapper])
  (:require [backtype.storm.clojure :as storm]))

(defn create-wrapper [prep-fn-var args]
  (ClojureWrapper. (storm/to-spec prep-fn-var) args))

(defn prep-proxy []
  (fn []
    (proxy [ClojureProxy] []
      (execute []
               (proxy-super testProxySuper)))))

(defn test-proxying []
  (let [wrapper (create-wrapper (var prep-proxy) [])]
    (.prepare wrapper)
    (.execute wrapper)))

(defn -main []
  (test-proxying))

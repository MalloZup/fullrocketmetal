(ns fullrocketmetal.core)

(defn read-config [config-file]
  (slurp config-file))

(def config-map 
   (clojure.edn/read-string (read-config "fullrocketmetal.edn")))


(defn -main
  [& args]
  (println (-> config-map :credentials :rocketchat :username )))

(ns fullrocketmetal.core
  ;; todo this will become the rocketchat lib in future.
  (:require [fullrocketmetal.http :as rocket]))

(defn read-config [config-file]
  (slurp config-file))

(def config-map 
   (clojure.edn/read-string (read-config "fullrocketmetal.edn")))

(def get-rocket-user
  (-> config-map :credentials :rocketchat :username))

(def get-rocket-password
  (-> config-map :credentials :rocketchat :password))
  
(def get-rocket-server
  (-> config-map :credentials :rocketchat :servername))
    
(defn -main
  [& args]
  ( println rocket/init-client get-rocket-server get-rocket-user get-rocket-password))
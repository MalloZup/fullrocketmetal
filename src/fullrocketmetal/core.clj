(ns fullrocketmetal.core
  (:require [missile.channels :as channels]
            [missile.config :as config]
            [fullrocketmetal.reminders :as reminders]
            [missile.chat :as chat])
   (:gen-class))
  
(defn init-rocketchat-client  []
  (config/set-config-from-file ".rocketchat.edn"))

;; we might call this in future with core.async for moment is ok.
(defn daemonize []
  (while true
    ;; todo watch if a file changed (conf) and perfom some operation
    ;; sleep 5 min
    (let [interval (* 2 60 1000)]
      (Thread/sleep interval))
    (println "sleeping 2 min")
    ;; print debug infos  ;; TODO
    (flush)
))
  ;; TODO: setup the time logic for scheduling message at given time. ( before 5 min etc)

  ;; TODO: in the main we should act like a daemon, f
  ;; 1) refresh/re-read configuration if something hafs changed
  ;; 2) send reminders and do other actions periodically. 
  ;; 3) print debug infos ( rate-limiting)
(defn -main []
  (init-rocketchat-client) 

  (daemonize)
)

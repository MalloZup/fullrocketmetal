(ns fullrocketmetal.core
  (:require [missile.channels :as channels]
            [missile.config :as config]
            [timely.core :as timely]
            [fullrocketmetal.reminders :as reminders]
            [missile.chat :as chat])
   (:gen-class))

(defn init-rocketchat-client  []
  (config/set-config-from-file ".rocketchat.edn"))

(defn test-print-fn []
  (fn []
    (println "Task scheduled at: " (java.util.Date.))))

;; we might call this in future with core.async for moment is ok.
(defn daemonize []
  (while true
    ;; todo watch if a file changed (conf) and perfom some operation
    
    ;; perform some operation
    ;; TODO add task to scheduler using timely.

    ;; sleep 1 min
    (let [interval (* 1 60 1000)]
      (Thread/sleep interval))
  
    ;; print debug infos
    ;; TODO

    (println "sleeping 1 min")
    (flush)
))
  ;; TODO: setup the time logic for scheduling message at given time. ( before 5 min etc)

  ;; TODO: in the main we should act like a daemon, f
  ;; 1) refresh/re-read configuration if something hafs changed
  ;; 2) send reminders and do other actions periodically. 
  ;; 3) print debug infos ( rate-limiting)
(defn -main []
  (init-rocketchat-client) 
)

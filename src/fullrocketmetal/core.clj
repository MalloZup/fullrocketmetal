(ns fullrocketmetal.core
  (:require [missile.channels :as channels]
            [missile.config :as config]
            [fullrocketmetal.reminders :as reminders]
            [clojurewerkz.quartzite.scheduler :as qs]
            [missile.chat :as chat]))

(defn init-rocketchat-client  []
  (config/set-config-from-file ".rocketchat.edn"))


  ;; TODO: setup the time logic for scheduling message at given time. ( before 5 min etc)
    

  ;; TODO: in the main we should act like a daemon, 
  ;; 1) refresh/re-read configuration if something has changed
  ;; 2) send reminders and do other actions periodically. 
  ;; 3) print debug infos ( rate-limiting)
  (defn -main []
    (init-rocketchat-client)
    (reminders/send-reminders))
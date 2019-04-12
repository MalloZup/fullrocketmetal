(ns fullrocketmetal.core
  (:require [missile.channels :as channels]
    [missile.config :as config]
    [missile.chat :as chat]))

;; TODO this should be called in a iteration
;;(def channel-id (channels/get-channel-id "rock"))

(defn events []
(clojure.edn/read-string (slurp "events.edn")))

(defn init-rocketchat-client  []
  (config/set-config-from-file ".rocketchat.edn")
  (println (channels/list!)))
  ;; read channels name, message etc.
  ;; iterate here over a list of channelsf
  ;; (chat/sendMessage channnel-id message))


(defn send-reminder []
  "dispatch message/reminder to rocketchat based on configuration"
   (println "WIP"))

  (defn -main []
    ;; read reminders map configuration
    (apply send-reminder (get-in (events) [:reminders]))
  
    (println "main"))
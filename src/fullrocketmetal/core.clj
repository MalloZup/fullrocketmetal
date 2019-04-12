(ns fullrocketmetal.core
  (:require [missile.channels :as channels]
    [missile.config :as config]
    [missile.chat :as chat]))

;; TODO this should be called in a iteration
;;(def channel-id (channels/get-channel-id "rock"))

(defn events []
(clojure.edn/read-string (slurp "events.edn")))

(defn init-rocketchat-client  []
  (config/set-config-from-file ".rocketchat.edn"))
  ;; read channels name, message etc.
  ;; iterate here over a list of channelsf
  ;; (chat/sendMessage channnel-id message))

(defn get-reminders []
  (get-in (events) [:reminders]))

(defn send-reminder []
  "dispatch message/reminder to rocketchat based on configuration"
  (init-rocketchat-client)
  
  ;; assemble channel-id and message so we can send it later
  (let [channel-id-and-message
        (interleave 
  ;; get channel-id as sequence  
         (map (fn [channel-name] (channels/get-channel-id channel-name)) (map :channel-name (get-reminders)))
  ;; get all messages as sequence
         (map :message (get-reminders))
         )]
    
  ;; TODO: setup the time logic for scheduling message at given time. ( before 5 min etc)
    
    
  ;; TODO: would be nice to send this messages in parallel (futures)
  ;; send message to the channel name given
    (map (fn [channel-id message] (chat/sendMessage channel-id message)) channel-id-and-message)))

  ;; TODO: in the main we should act like a daemon, 
  ;; 1) refresh/re-read configuration if something has changed
  ;; 2) send reminders and do other actions periodically. 
  ;; 3) print debug infos ( rate-limiting)
  (defn -main []
    ;; read reminders map configuration
    (send-reminder)
    (println "main"))